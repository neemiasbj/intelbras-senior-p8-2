 package br.com.intelbras.enums;
 
 public enum RemoteCheckCfgPassmode {
   PASS("pass"), NOT_PASS("notpass");
   
   private final String value;
   
   RemoteCheckCfgPassmode(String value) {
     this.value = value;
   }
   
   public String getValue() {
     return this.value; } public static RemoteCheckCfgPassmode fromValue(String value) {
     byte b;
     int i;
     RemoteCheckCfgPassmode[] arrayOfRemoteCheckCfgPassmode;
     for (i = (arrayOfRemoteCheckCfgPassmode = values()).length, b = 0; b < i; ) { RemoteCheckCfgPassmode mode = arrayOfRemoteCheckCfgPassmode[b];
       if (mode.value.equals(value))
         return mode; 
       b++; }
     
     throw new IllegalArgumentException("Unexpected value: " + value);
   }
 }


