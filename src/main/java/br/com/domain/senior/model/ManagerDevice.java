package br.com.domain.senior.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import br.com.util.Utils;

public final class ManagerDevice extends ModuleController {
    private static final short OPERATION_UPDATE_CARD_CODE = 0;
    
    private static final short OPERATION_DELETE_CARD_CODE = 1;
    
    private static final short OPERATION_DELETE_PERSON_CODE = 2;
    
    private String ip;
    
    private Date date;
    
    private boolean dateTimeStatus = false;
    
    private boolean started = false;
    
    private boolean automaticalySendEvents = true;
    
    private ScheduledExecutorService executor;
    
    private List<SimpleCard> cardAllowedList = new ArrayList<>();
    
    private List<SimpleCard> cardBlockedList = new ArrayList<>();
    
    private Collection<Date> holidays = new ArrayList<>();
    
    private List<CardPassword> passwords = new ArrayList<>();
    
    private final Map<Integer, ReaderPermissions> readerPermissions = new HashMap<>();
    
    private List<Person> peopleList = new ArrayList<>();
    
    private long factorationNumber;
    
    private String locale;
    
    private String corporationName;
    
    private long ceiNumber;
    
    private long nationalNumber;
    
    private long dstStart;
    
    private long dstEnd;
    
    private short gmtMin;
    
    @Override
    public String toString() {
        return "ManagerDevice [id=" + getId() + ", ip=" + Utils.formatIPAddress(ip) + ", description=" + getDescription() + ", date=" + date
               + ", status=" + getStatus() + "]";
    }
    
    public boolean isDateTimeStatus() {
        return dateTimeStatus;
    }
    
    public void setDateTimeStatus(boolean dateTimeStatus) {
        this.dateTimeStatus = dateTimeStatus;
    }
    
    public int getCardAllowedListSize() {
        return this.cardAllowedList.size();
    }
    
    public List<SimpleCard> getCardAllowedList() {
        return this.cardAllowedList;
    }
    
    public Collection<SimpleCard> getCardAllowedList(int readerId) {
        --readerId;
        ArrayList<SimpleCard> allowedList = new ArrayList<>();
        Iterator<SimpleCard> var3 = this.cardAllowedList.iterator();
        
        while (var3.hasNext()) {
            SimpleCard card = var3.next();
            byte[] readers = card.getReaders();
            if (readers[readerId] == 1) {
                allowedList.add(card);
            }
        }
        
        return allowedList;
    }
    
    public int getCardBlockedListSize() {
        return this.cardBlockedList.size();
    }
    
    public Collection<SimpleCard> getCardBlockedList(int readerId) {
        --readerId;
        ArrayList<SimpleCard> blockedList = new ArrayList<>();
        Iterator<SimpleCard> var3 = this.cardBlockedList.iterator();
        
        while (var3.hasNext()) {
            SimpleCard card = var3.next();
            byte[] readers = card.getReaders();
            if (readers[readerId] == 1) {
                blockedList.add(card);
            }
        }
        
        return blockedList;
    }
    
    public Collection<Date> getHolidays() {
        return this.holidays;
    }
    
    public List<CardPassword> getPasswords() {
        return this.passwords;
    }
    
    public void setPasswords(List<CardPassword> passwords) {
        this.passwords = passwords;
    }
    
    public void removePassword(long card) {
        Iterator<CardPassword> it = this.passwords.iterator();
        CardPassword cardPassword = null;
        
        while (it.hasNext()) {
            cardPassword = it.next();
            if (cardPassword.getCardNumber() == card) {
                it.remove();
            }
        }
        
    }
    
    public void setCardAllowedList(List<SimpleCard> cardAllowedList) {
        if (cardAllowedList == null) {
            throw new NullPointerException("Lista de cartões permitidos não pode ser nula. ManagerDevice.");
        } else {
            this.cardAllowedList = cardAllowedList;
        }
    }
    
    public void setCardBlockedList(List<SimpleCard> cardBlockedList) {
        if (cardBlockedList == null) {
            throw new NullPointerException("Lista de cartões bloqueados não pode ser nula. ManagerDevice.");
        } else {
            this.cardBlockedList = cardBlockedList;
        }
    }
    
    public void setHolidays(Collection<Date> holidays) {
        if (holidays == null) {
            throw new NullPointerException("Coleção de feriados não pode ser nula. ManagerDevice.");
        } else {
            this.holidays = holidays;
        }
    }
    
    public void setReaderPermissions(Collection<ReaderPermissions> readerPermissions) {
        if (readerPermissions == null) {
            throw new NullPointerException("Coleção de permissões de leitoras não pode ser nula. ManagerDevice.");
        } else {
            this.readerPermissions.clear();
            Iterator<ReaderPermissions> var2 = readerPermissions.iterator();
            
            while (var2.hasNext()) {
                ReaderPermissions rp = var2.next();
                this.readerPermissions.put(rp.getReaderID(), rp);
            }
            
        }
    }
    
    public ReaderPermissions getReaderPermission(int idReader) {
        return this.readerPermissions.get(idReader);
    }
    
    public Collection<ReaderPermissions> getReaderPermissions() {
        return Collections.unmodifiableCollection(this.readerPermissions.values());
    }
    
    public Collection<CardReaderDevice> getAllCardReaderDevices() {
        ArrayList<CardReaderDevice> arrayList = new ArrayList<>();
        arrayList.addAll(this.getCardReaderDevices());
        Iterator<ModuleController> var2 = this.getControllerModules().iterator();
        
        while (var2.hasNext()) {
            ModuleController module = var2.next();
            arrayList.addAll(module.getCardReaderDevices());
        }
        
        return arrayList;
    }
    
    public Collection<OutputDevice> getAllOutputDevices() {
        ArrayList<OutputDevice> arrayList = new ArrayList<>();
        arrayList.addAll(this.getOutputDevices());
        Iterator<ModuleController> var2 = this.getControllerModules().iterator();
        
        while (var2.hasNext()) {
            ModuleController module = var2.next();
            arrayList.addAll(module.getOutputDevices());
        }
        
        return arrayList;
    }
    
    @Override
    public CardReaderDevice getCardReaderById(int cardReaderId) {
        CardReaderDevice cardReader = super.getCardReaderById(cardReaderId);
        if (cardReader == null) {
            Collection<ModuleController> controllers = this.getControllerModules();
            Iterator<ModuleController> var4 = controllers.iterator();
            
            do {
                if (!var4.hasNext()) {
                    return null;
                }
                
                ModuleController controllerModule = var4.next();
                cardReader = controllerModule.getCardReaderById(cardReaderId);
            } while (cardReader == null);
            
            return cardReader;
        } else {
            return cardReader;
        }
    }
    
    public String getIp() {
        return this.ip;
    }
    
    @Override
    public boolean isStarted() {
        return this.started;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    @Override
    public Date getDate() {
        if (this.date == null) {
            GregorianCalendar cal = new GregorianCalendar(this.getTimeZone());
            cal.set(1970, 0, 1, 0, 0, 0);
            cal.set(14, 0);
            this.setDate(cal.getTime());
        }
        
        return this.date;
    }
    
    private void relogioChangeStatus() {
        if (this.isStarted()) {
            if (this.executor != null) {
                this.executor.shutdownNow();
                this.executor = null;
            }
            
            this.executor = new ScheduledThreadPoolExecutor(1, r -> {
                Thread thread = new Thread(r, "IncreasesASecond - SampleDriver/src/com/senior/sampledriver/vo/ManagerDevice.java");
                thread.setDaemon(true);
                return thread;
            });
            Runnable increasesASecond = () -> {
                long newDate = ManagerDevice.this.getDate().getTime() + 1000L;
                ManagerDevice.this.setDate(new Date(newDate));
            };
            this.executor.scheduleAtFixedRate(increasesASecond, 1L, 1L, TimeUnit.SECONDS);
        } else {
            this.executor.shutdownNow();
            this.executor = null;
        }
        
    }
    
    public void setIp(int ip) {
        this.setIp(Utils.getStringIpFromInteger(ip));
    }
    
    public void setIp(String ip) {
        if (ip == null) {
            throw new NullPointerException("Ip não pode ser nulo.");
        } else {
            this.ip = ip;
        }
    }
    
    public void setStarted(boolean flag) {
        this.started = flag;
        if (flag) {
            this.setDate((Date) null);
            this.getDate();
        }
        
        this.relogioChangeStatus();
    }
    
    public boolean sendEventsAutomaticaly() {
        return this.automaticalySendEvents;
    }
    
    public void setAutomaticalySendEventsFlag(boolean b) {
        this.automaticalySendEvents = b;
    }
    
    public void clearCardAllowedList(int readerId) {
        --readerId;
        
        byte[] readers;
        for (Iterator<SimpleCard> var2 = this.cardAllowedList.iterator(); var2.hasNext(); readers[readerId] = 0) {
            SimpleCard card = var2.next();
            readers = card.getReaders();
        }
        
    }
    
    public void clearCardBlockedList(int readerId) {
        --readerId;
        
        byte[] readers;
        for (Iterator<SimpleCard> var2 = this.cardBlockedList.iterator(); var2.hasNext(); readers[readerId] = 0) {
            SimpleCard card = var2.next();
            readers = card.getReaders();
        }
        
    }
    
    public void setNationalNumber(long pNationalNumber) {
        this.nationalNumber = pNationalNumber;
    }
    
    public void setCEINumber(long pCeiNumber) {
        this.ceiNumber = pCeiNumber;
    }
    
    public void setCorporationName(String pCorporationName) {
        this.corporationName = pCorporationName;
    }
    
    public void setLocale(String pLocale) {
        this.locale = pLocale;
    }
    
    public void setFactorationNumber(long pFactorationNumber) {
        this.factorationNumber = pFactorationNumber;
    }
    
    public long getFactorationNumber() {
        return this.factorationNumber;
    }
    
    public String getLocale() {
        return this.locale;
    }
    
    public String getCorporationName() {
        return this.corporationName;
    }
    
    public long getCeiNumber() {
        return this.ceiNumber;
    }
    
    public long getNationalNumber() {
        return this.nationalNumber;
    }
    
    public void setPeople(Collection<Person> people) {
        if (people == null) {
            throw new NullPointerException("Coleção de pessoas não pode ser nula. FrmMainModel.");
        } else {
            this.peopleList = new ArrayList<>(people);
        }
    }
    
    public Collection<Person> getPeople() {
        return this.peopleList;
    }
    
    public Person getPerson(String id) {
        Iterator<Person> var2 = this.peopleList.iterator();
        
        Person person;
        do {
            if (!var2.hasNext()) {
                return null;
            }
            
            person = var2.next();
        } while (!person.getIdPerson().equals(id));
        
        return person;
    }
    
    public Card getCardById(long cardId) {
        Iterator<Person> var3 = this.peopleList.iterator();
        
        while (var3.hasNext()) {
            Person person = var3.next();
            Iterator<Card> var5 = person.getCards().iterator();
            
            while (var5.hasNext()) {
                Card card = var5.next();
                if (card.getId() == cardId) {
                    return card;
                }
            }
        }
        
        return null;
    }
    
    public boolean replacePerson(Person person) {
        if (person == null) {
            throw new NullPointerException("Pessoa não pode ser nula. FrmMainModel.");
        } else {
            Iterator<Person> var2 = this.peopleList.iterator();
            
            Person per;
            do {
                if (!var2.hasNext()) {
                    return false;
                }
                
                per = var2.next();
            } while (!per.getIdPerson().equals(person.getIdPerson()));
            
            Iterator<BiometricData> var4 = per.getAllBiometricData().iterator();
            
            while (var4.hasNext()) {
                BiometricData bioData = var4.next();
                person.addBiometricData(bioData);
            }
            
            this.peopleList.remove(per);
            this.peopleList.add(person);
            return true;
        }
    }
    
    public long getDstStart() {
        return this.dstStart;
    }
    
    public long getDstEnd() {
        return this.dstEnd;
    }
    
    public void setDstStart(long dstStart) {
        this.dstStart = dstStart;
    }
    
    public void setDstEnd(long dstEnd) {
        this.dstEnd = dstEnd;
    }
    
    public boolean addPerson(Person person) {
        if (person == null) {
            throw new NullPointerException("Pessoa a ser adicionada não pode ser nula. FrmMainModel.");
        } else {
            return !this.peopleList.contains(person) && this.peopleList.add(person);
        }
    }
    
    public boolean deletePerson(Person person) {
        if (person == null) {
            throw new NullPointerException("Pessoa a ser removida não pode ser nula. FrmMainModel.");
        } else {
            return this.peopleList.contains(person) && this.peopleList.remove(person);
        }
    }
    
    public void mergePerson(Person person, byte bioOperation, byte operation) {
        Person oldPerson = this.getPerson(person.getIdPerson());
        if (oldPerson != null) {
            if (bioOperation == 0) {
                this.replacePerson(person);
            } else {
                Iterator<BiometricData> var5;
                BiometricData biometricData;
                if (bioOperation != 1) {
                    var5 = person.getAllBiometricData().iterator();
                    
                    while (var5.hasNext()) {
                        biometricData = var5.next();
                        oldPerson.removeBiometricData(biometricData);
                    }
                } else {
                    var5 = oldPerson.getAllBiometricData().iterator();
                    
                    while (var5.hasNext()) {
                        biometricData = var5.next();
                        oldPerson.removeBiometricData(biometricData);
                    }
                    
                    var5 = person.getAllBiometricData().iterator();
                    
                    while (var5.hasNext()) {
                        biometricData = var5.next();
                        oldPerson.addBiometricData(biometricData);
                    }
                }
            }
            
            oldPerson.setName(person.getName());
            oldPerson.setPis(person.getPis());
            switch (operation) {
                case OPERATION_UPDATE_CARD_CODE:
                    oldPerson.addCard(person.getCards().iterator().next());
                    break;
                    
                case OPERATION_DELETE_CARD_CODE:
                    oldPerson.removeCard(person.getCards().iterator().next());
                    break;
                    
                case OPERATION_DELETE_PERSON_CODE:
                    this.deletePerson(person);
                    
            }
        } else {
            this.addPerson(person);
        }
        
    }
    
    public short getGmtMinutes() {
        return this.gmtMin;
    }
    
    public TimeZone getTimeZone() {
        byte horaGmt = (byte) (this.gmtMin / 60);
        byte minGmt = (byte) Math.abs(this.gmtMin % 60);
        StringBuilder sb = new StringBuilder();
        sb.append("GMT");
        if (horaGmt > 0) {
            sb.append("+");
        }
        
        sb.append(horaGmt);
        if (minGmt != 0) {
            sb.append(":");
            if (minGmt < 10) {
                sb.append("0");
            }
            
            sb.append(minGmt);
        }
        
        return TimeZone.getTimeZone(sb.toString());
    }
    
    public void setGmtMinutes(short gmtMinutes) {
        if (gmtMinutes >= -720 && gmtMinutes <= 720) {
            this.gmtMin = gmtMinutes;
        } else {
            throw new IllegalArgumentException("GMT inválido: " + gmtMinutes);
        }
    }
    
}
