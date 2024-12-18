 package br.com.intelbras.enums;
 
 public enum RemoteCheckCfgUserType {
   NORMAL("normal"), NORMAL_VISITO_UNKNOW("normalOrvisitorOrunknow");
   
   private final String value;
   
   RemoteCheckCfgUserType(String value) {
     this.value = value;
   }
   
   public String getValue() {
     return this.value; } public static RemoteCheckCfgUserType fromValue(String value) {
     byte b;
     int i;
     RemoteCheckCfgUserType[] arrayOfRemoteCheckCfgUserType;
     for (i = (arrayOfRemoteCheckCfgUserType = values()).length, b = 0; b < i; ) { RemoteCheckCfgUserType mode = arrayOfRemoteCheckCfgUserType[b];
       if (mode.value.equals(value))
         return mode; 
       b++; }
     
     throw new IllegalArgumentException("Unexpected value: " + value);
   }
 }


