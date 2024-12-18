package br.com.senior.usecase;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.domain.senior.abstration.Device;
import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.model.BiometricData;
import br.com.domain.senior.model.BiometricTechnology;
import br.com.domain.senior.model.BiometricTemplate;
import br.com.domain.senior.model.Card;
import br.com.domain.senior.model.Person;
import br.com.domain.senior.model.SimpleCard;
import br.com.domain.senior.request.AddSetBiometricData;
import br.com.domain.senior.request.AddSetDeviceCardListRep;
import br.com.domain.senior.request.DeleteCardListCommand;
import br.com.domain.senior.request.DeletePersonBiometry;
import br.com.domain.senior.request.PersonUpdateRep;
import br.com.domain.senior.request.SetCardListCommand;
import br.com.domain.senior.response.BiometricDataResponse;
import br.com.domain.senior.response.UpdateDeleteBiometricTechnology;
import br.com.util.LogUtils;
import br.com.util.Utils;

public class DecodeListDataUseCase {
    
    private static final Logger logger = LoggerFactory.getLogger("DecodeListDataUseCase");
    
    public static HeaderMessage decodeResponseBiometricData(ByteBuffer buffer, byte messageNumber) {
        buffer.getShort();
        List<Person> personList = new ArrayList<>();
        int personCount = buffer.getInt();
        BiometricData bioData = null;
        
        for (int iPerson = 0; iPerson < personCount; ++iPerson) {
            byte[] personId = new byte[23];
            buffer.get(personId);
            Person person = new Person(new String(personId));
            int biometricCount = buffer.get() & 255;
            HashMap<BiometricTechnology, Byte> biometricSecurityLevels = new HashMap<>();
            
            for (int iBiometric = 0; iBiometric < biometricCount; ++iBiometric) {
                byte biometricTecnology = buffer.get();
                short securityLevel = buffer.getShort();
                BiometricTechnology bioTech = new BiometricTechnology(biometricTecnology);
                bioData = new BiometricData(bioTech);
                int templateCount = buffer.get() & 255;
                
                for (int j = 0; j < templateCount; ++j) {
                    int templateSize = buffer.getShort() & 255;
                    byte[] template = new byte[templateSize];
                    buffer.get(template);
                    bioData.addTemplate(new BiometricTemplate(bioTech, template));
                }
                
                person.addBiometricData(bioData);
                biometricSecurityLevels.put(bioTech, (byte) securityLevel);
            }
            
            personList.add(person);
        }
        
        return new BiometricDataResponse(messageNumber, personList);
    }
    
    public static HeaderMessage decodeAddSetDeviceCardListRep(ByteBuffer buffer, byte messageNumber) {
        AddSetDeviceCardListRep response = new AddSetDeviceCardListRep(messageNumber);
        buffer.getShort();
        response.setOverwrite(buffer.get() == 0);
        response.setDeviceId(buffer.getInt());
        int peopleCount = buffer.getInt();
        List<Person> people = new ArrayList<>(peopleCount);
        
        for (int i = 0; i < peopleCount; ++i) {
            Person person = new Person(Utils.getString(buffer, 23));
            byte nameLength = buffer.get();
            person.setName(Utils.getString(buffer, nameLength));
            String personPisStr = Utils.getString(buffer, 12);
            personPisStr = Utils.completePersonPis(personPisStr);
            person.setPis(Long.parseLong(personPisStr));
            person.setVerifyBiometry(buffer.get());
            int cardCount = buffer.getInt();
            
            for (int j = 0; j < cardCount; ++j) {
                Card card = new Card(buffer.getLong(), person);
                card.setTechnology(buffer.get());
                card.setType(buffer.get());
            }
            
            byte bioTechCount = buffer.get();
            
            for (int j = 0; j < bioTechCount; ++j) {
                BiometricTechnology bioTech = new BiometricTechnology(buffer.get());
                BiometricData bioData = new BiometricData(bioTech);
                bioData.setBioSecurityLevel(buffer.get());
                int templateCount = buffer.get();
                
                for (int k = 0; k < templateCount; ++k) {
                    byte[] template = new byte[buffer.getShort()];
                    buffer.get(template);
                    BiometricTemplate bioTemplate = new BiometricTemplate(bioTech, template);
                    bioData.addTemplate(bioTemplate);
                }
                
                person.addBiometricData(bioData);
            }
            
            // int personDataLenght = buffer.getInt();
            // byte[] template = new byte[personDataLenght];
            // byte[] personId = buffer.get(template).array();
            // logger.info("Person ID (byte)= {}", personId);
            String responsibleCpf = Utils.getString(buffer, 11);
            logger.info("Responsible Cpf = {}", responsibleCpf);
            person.setResponsibleCpf(Long.parseLong(responsibleCpf));
            people.add(person);
        }
        
        response.setPeople(people);
        
        // Verificar como o CPF está chegando no Buffer
        return response;
    }
    
    public static HeaderMessage decodePersonUpdateRep(ByteBuffer buffer, byte messageNumber) {
        PersonUpdateRep response = new PersonUpdateRep(messageNumber);
        buffer.getShort();
        response.setDeviceId(buffer.getInt());
        byte operation = buffer.get();
        response.setOperation(operation);
        long cardId = buffer.getLong();
        byte cardTech = buffer.get();
        String personId = Utils.getString(buffer, 23);
        byte nameLength = buffer.get();
        String name = Utils.getString(buffer, nameLength);
        String pis = Utils.getString(buffer, 12);
        Person person = new Person(personId);
        person.setName(name);
        person.setPis(Long.parseLong(Utils.completePersonPis(pis)));
        Card card = new Card(cardId, person);
        card.setTechnology(cardTech);
        byte bioOperation = buffer.get();
        response.setBioOperation(bioOperation);
        if (bioOperation != 0) {
            person.setVerifyBiometry(buffer.get());
            byte bioTechCount = buffer.get();
            
            for (int i = 0; i < bioTechCount; ++i) {
                BiometricTechnology bioTech = new BiometricTechnology(buffer.get());
                BiometricData bioData = new BiometricData(bioTech);
                bioData.setBioSecurityLevel(buffer.get());
                int templateCount = buffer.get();
                
                for (int j = 0; j < templateCount; ++j) {
                    byte[] template = new byte[buffer.getShort()];
                    buffer.get(template);
                    BiometricTemplate bioTemplate = new BiometricTemplate(bioTech, template);
                    bioData.addTemplate(bioTemplate);
                }
                
                person.addBiometricData(bioData);
            }
        }
        String responsibleCpf = Utils.getString(buffer, 11);
        logger.info("Responsible Cpf = {}", responsibleCpf);
        String cpfPerson = Utils.getString(buffer, 12);
        logger.info("Cpf = {}", responsibleCpf);
        person.setCpf(cpfPerson);
        response.setCpfResponsible(responsibleCpf);
        response.setPerson(person);
        
        // Verificar como o CPF está chegando no Buffer
        return response;
    }
    
    public static HeaderMessage decodeUpdateDeleteBiometricTechnology(ByteBuffer buffer, byte messageNumber) {
        UpdateDeleteBiometricTechnology response = new UpdateDeleteBiometricTechnology(messageNumber);
        buffer.getShort();
        response.setOperation(buffer.get());
        BiometricTechnology biometricTechnology = new BiometricTechnology(buffer.get());
        biometricTechnology.setMaxSecurityLevel(buffer.getShort());
        biometricTechnology.setMinSecurityLevel(buffer.getShort());
        response.setBioTechnology(biometricTechnology);
        int devicesCount = buffer.getShort();
        
        for (int i = 0; i < devicesCount; ++i) {
            response.addDeviceIds(buffer.getInt());
        }
        
        return response;
    }
    
    public static HeaderMessage decodeSetCardList(ByteBuffer buffer, byte messageNumber) {
        LogUtils.logInfoMessageSendBytesToHexFormatt(buffer.array(), logger);
        buffer.getShort();
        byte listType = buffer.get();
        buffer.get();
        int deviceId = buffer.getInt();
        int cardCount = buffer.getInt();
        SetCardListCommand cmd = new SetCardListCommand(messageNumber, deviceId, listType);
        
        for (int i = 0; i < cardCount; ++i) {
            long cardId = buffer.getLong();
            String personId = Utils.getString(buffer, 23);
            byte cardTechnology = buffer.get();
            byte cardType = buffer.get();
            byte readerCount = buffer.get();
            byte[] readers32bytes = new byte[readerCount];
            buffer.get(readers32bytes);
            byte[] readers255 = new byte[255];
            
            for (int byteIndex = 0; byteIndex < readers32bytes.length; ++byteIndex) {
                for (int bitIndex = 7; bitIndex >= 0; --bitIndex) {
                    if (byteIndex < readers32bytes.length - 1 || bitIndex != 0) {
                        int index = byteIndex * 8 + 7 - bitIndex;
                        byte value = (byte) (readers32bytes[byteIndex] >>> bitIndex & 1);
                        readers255[index] = value;
                    }
                }
            }
            
            SimpleCard card = new SimpleCard(cardId, personId, cardType, readers255, cardTechnology);
            cmd.addCard(card);
        }
        
        return cmd;
    }
    
    public static HeaderMessage decodeDeleteCardList(ByteBuffer buffer, byte msgNumber) {
        buffer.getShort();
        int devID = buffer.getInt();
        return new DeleteCardListCommand(msgNumber, devID);
    }
    
    public static HeaderMessage decodeAddSetBiometricData(ByteBuffer buffer, Byte messageNumber) {
        buffer.getShort();
        boolean overwrite = buffer.get() == 1;
        List<Person> personList = new ArrayList<>();
        List<Device> deviceList = new ArrayList<>();
        int deviceCount = buffer.getInt();
        
        int personCount;
        for (personCount = 0; personCount < deviceCount; ++personCount) {
            Device device = DecodeDeviceDataResponseUseCase.createDevice((byte) 3);
            device.setId(buffer.getInt());
            deviceList.add(device);
        }
        
        personCount = buffer.getInt();
        
        for (int i = 0; i < personCount; ++i) {
            Person person = new Person(Utils.getString(buffer, 23));
            byte techBioCount = buffer.get();
            
            for (int k = 0; k < techBioCount; ++k) {
                byte techBio = buffer.get();
                byte conferenceLevel = buffer.get();
                BiometricTechnology biometricTech = new BiometricTechnology(techBio);
                BiometricData bioData = new BiometricData(biometricTech);
                bioData.setBioSecurityLevel(conferenceLevel);
                byte bioDataCount = buffer.get();
                
                for (int l = 0; l < bioDataCount; ++l) {
                    short templateSize = buffer.getShort();
                    byte[] template = new byte[templateSize];
                    buffer.get(template);
                    BiometricTemplate bioTemplate = new BiometricTemplate(biometricTech, template);
                    bioData.addTemplate(bioTemplate);
                }
            }
            
            personList.add(person);
        }
        
        return new AddSetBiometricData(messageNumber, personList, overwrite, deviceList);
    }
    
    public static HeaderMessage decodeDeletePersonBiometry(ByteBuffer buffer, Byte messageNumber) {
        buffer.getShort();
        int deviceId = buffer.getInt();
        int personCount = buffer.getInt();
        List<Person> personList = new ArrayList<>();
        
        for (int i = 0; i < personCount; ++i) {
            new Person(Utils.getString(buffer, 23));
        }
        
        return new DeletePersonBiometry(messageNumber, deviceId, personList);
    }
    
}
