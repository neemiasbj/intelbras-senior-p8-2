package br.com.domain.senior.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Person {
    private final byte[] idPerson;
    
    private long startRemoval;
    
    private long finishRemoval;
    
    private boolean isVerifyValidityCard;
    
    private boolean isVerifyPermission;
    
    private boolean isVerifyRemoval;
    
    private boolean isVerifyLevelAndAntipassback;
    
    private byte biometricValidation;
    
    private byte verifyTimeKeepAtteRange;
    
    private byte level;
    
    private String name;
    
    private long pis;
    
    private long responsibleCpf;
    
    private String cpf;
    
    private final Collection<Card> cards = new CopyOnWriteArrayList<>();
    
    private final Collection<String> accompanies = new ArrayList<>();
    
    private final List<BiometricData> biometricDataList = new CopyOnWriteArrayList<>();
    
    private final TimeRange defaultPersonTimeRange = new TimeRange(
                                    new short[] { 1440, 1440, 1440, 1440, 1440, 1440, 1440, 1440, 1440, 1440, 1440, 1440, 1440, 1440 });
    
    private final TimeRange[] ranges;
    
    private final Permission[] permissions;
    
    public static final byte WEEK_DAY_PERMISSION = 0;
    
    public static final byte SATURDAY_PERMISSION = 1;
    
    public static final byte SUNDAY_PERMISSION = 2;
    
    public static final byte HOLIDAY_PERMISSION = 3;
    
    public static final byte ACCESS_RANGE_01 = 0;
    
    public static final byte ACCESS_RANGE_02 = 1;
    
    public static final byte ACCESS_RANGE_03 = 2;
    
    public static final byte ACCESS_RANGE_04 = 3;
    
    public static final byte ACCESS_RANGE_05 = 4;
    
    public static final byte ACCESS_RANGE_06 = 5;
    
    public static final byte ACCESS_RANGE_07 = 6;
    
    public static final byte TIME_KEEPING_RANGE_01 = 7;
    
    public static final byte TIME_KEEPING_RANGE_02 = 8;
    
    public static final byte TIME_KEEPING_RANGE_03 = 9;
    
    public static final byte TIME_KEEPING_RANGE_04 = 10;
    
    public static final byte TIME_KEEPING_RANGE_05 = 11;
    
    public static final byte TIME_KEEPING_RANGE_06 = 12;
    
    public static final byte TIME_KEEPING_RANGE_07 = 13;
    
    public Person(String idPerson) {
        this.ranges = new TimeRange[] { this.defaultPersonTimeRange,
                                        this.defaultPersonTimeRange,
                                        this.defaultPersonTimeRange,
                                        this.defaultPersonTimeRange,
                                        this.defaultPersonTimeRange,
                                        this.defaultPersonTimeRange,
                                        this.defaultPersonTimeRange,
                                        this.defaultPersonTimeRange,
                                        this.defaultPersonTimeRange,
                                        this.defaultPersonTimeRange,
                                        this.defaultPersonTimeRange,
                                        this.defaultPersonTimeRange,
                                        this.defaultPersonTimeRange,
                                        this.defaultPersonTimeRange };
        this.permissions = new Permission[4];
        if (idPerson == null) {
            throw new IllegalArgumentException("Id da pessoa não pode ser nulo.");
        } else if (idPerson.length() != 23) {
            throw new IllegalArgumentException("Id da pessoa deve possuir 23 dígitos");
        } else {
            this.idPerson = idPerson.getBytes();
        }
    }
    
    public String getIdPerson() {
        return new String(this.idPerson);
    }
    
    public void setStartRemoval(long startRemoval) {
        if (startRemoval < 0L) {
            throw new IllegalArgumentException("Data inicial do afastamento não pode ser negativo. " + startRemoval);
        } else {
            this.startRemoval = startRemoval;
        }
    }
    
    public long getStartRemoval() {
        return this.startRemoval;
    }
    
    public void setFinishRemoval(long finishRemoval) {
        if (finishRemoval < 0L) {
            throw new IllegalArgumentException("Data final do afastamento não pode ser negativo. " + finishRemoval);
        } else {
            this.finishRemoval = finishRemoval;
        }
    }
    
    public long getFinishRemoval() {
        return this.finishRemoval;
    }
    
    public void setVerifyValidityCard(boolean verifyValidityCard) {
        this.isVerifyValidityCard = verifyValidityCard;
    }
    
    public boolean isVerifyValidityCard() {
        return this.isVerifyValidityCard;
    }
    
    public void setVerifyPermission(boolean verifyPermission) {
        this.isVerifyPermission = verifyPermission;
    }
    
    public boolean isVerifyPermission() {
        return this.isVerifyPermission;
    }
    
    public void setVerifyRemoval(boolean verifyRemoval) {
        this.isVerifyRemoval = verifyRemoval;
    }
    
    public boolean isVerifyRemoval() {
        return this.isVerifyRemoval;
    }
    
    public void setVerifyLevelAndAntipassback(boolean verifyLevelAndAntipassback) {
        this.isVerifyLevelAndAntipassback = verifyLevelAndAntipassback;
    }
    
    public boolean isVerifyLevelAndAntipassback() {
        return this.isVerifyLevelAndAntipassback;
    }
    
    public void setVerifyBiometry(byte verifyBiometry) {
        this.biometricValidation = verifyBiometry;
    }
    
    public byte getVerifyBiometry() {
        return this.biometricValidation;
    }
    
    public void setVerifyTimeKeepAtteRange(byte verifyTimeAttendanceRange) {
        if (verifyTimeAttendanceRange >= 0 && verifyTimeAttendanceRange <= 3) {
            this.verifyTimeKeepAtteRange = verifyTimeAttendanceRange;
        } else {
            throw new IllegalArgumentException("Tipo de verificação de faixa de acesso e ponto de estar entre 0 e 3. " + verifyTimeAttendanceRange);
        }
    }
    
    public byte getVerifyTimeKeepAtteRange() {
        return this.verifyTimeKeepAtteRange;
    }
    
    public boolean addCard(Card card) {
        if (card == null) {
            throw new NullPointerException("Cartão não pode ser nulo. Person.");
        } else {
            return this.getCard(card.getId()) == null && this.cards.add(card);
        }
    }
    
    public boolean removeCard(Card card) {
        if (card == null) {
            throw new NullPointerException("Cartão não pode ser nulo. Person.");
        } else {
            return this.cards.remove(card);
        }
    }
    
    private Card getCard(long cardId) {
        Iterator<Card> var3 = this.cards.iterator();
        
        Card card;
        do {
            if (!var3.hasNext()) {
                return null;
            }
            
            card = var3.next();
        } while (card.getId() != cardId);
        
        return card;
    }
    
    public Collection<Card> getCards() {
        return this.cards;
    }
    
    public void addAccompany(String personId) {
        if (personId == null) {
            throw new NullPointerException("Id da pessoa não pode ser nulo. Person.");
        } else if (personId.length() != 23) {
            throw new IllegalArgumentException("Id da pessoa deve ter 23 bytes." + personId.length());
        } else {
            this.accompanies.add(personId);
        }
    }
    
    public Collection<String> getAccompanies() {
        return Collections.unmodifiableCollection(this.accompanies);
    }
    
    public void setRange(byte rangeId, TimeRange ranges) {
        if (ranges == null) {
            throw new IllegalArgumentException("Faixa horária não pode ser nula.");
        } else {
            this.ranges[rangeId] = ranges;
        }
    }
    
    public TimeRange getRange(byte rangeId) {
        return this.ranges[rangeId];
    }
    
    public Permission getPermission(int index) {
        return this.permissions[index];
    }
    
    public void setPermission(int index, Permission permission) {
        if (index >= 0 && index < this.permissions.length) {
            if (permission == null) {
                throw new IllegalArgumentException("Permissão não pode ser nula.");
            } else {
                this.permissions[index] = permission;
            }
        } else {
            throw new IllegalArgumentException("Índice da permissão inválido. " + index);
        }
    }
    
    public void setPermissionNull(int index) {
        if (index >= 0 && index < this.permissions.length) {
            this.permissions[index] = null;
        } else {
            throw new IllegalArgumentException("Índice da permissão inválido. " + index);
        }
    }
    
    public void setLevel(byte level) {
        this.level = level;
    }
    
    public byte getLevel() {
        return this.level;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setPis(long pis) {
        this.pis = pis;
    }
    
    public long getPis() {
        return this.pis;
    }
    
    public long getResponsibleCpf() {
        return this.responsibleCpf;
    }
    
    public void setResponsibleCpf(long responsibleCpf) {
        this.responsibleCpf = responsibleCpf;
    }
    
    public void addBiometricData(BiometricData bioData) {
        this.biometricDataList.add(bioData);
    }
    
    public void removeBiometricData(BiometricData bioData) {
        this.biometricDataList.remove(bioData);
    }
    
    public void clearBiometricData() {
        this.biometricDataList.clear();
    }
    
    public int hashCode() {
        // int prime = true;
        int result = 1;
        result = 31 * result + Arrays.hashCode(this.idPerson);
        return result;
    }
    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Person other = (Person) obj;
            return Arrays.equals(this.idPerson, other.idPerson);
        }
    }
    
    public String toString() {
        return "Person(" + (new String(this.idPerson)).trim() + ")";
    }
    
    public Collection<BiometricData> getAllBiometricData() {
        return Collections.unmodifiableList(this.biometricDataList);
    }
    
    public BiometricData getBiometricData(BiometricTechnology technology) {
        if (technology == null) {
            return null;
        } else {
            Iterator<BiometricData> var2 = this.biometricDataList.iterator();
            
            BiometricData data;
            do {
                if (!var2.hasNext()) {
                    return null;
                }
                
                data = var2.next();
            } while (!technology.equals(data.getTechnology()));
            
            return data;
        }
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
}
