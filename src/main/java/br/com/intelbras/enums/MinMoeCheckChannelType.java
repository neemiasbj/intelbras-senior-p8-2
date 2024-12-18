 package br.com.intelbras.enums;
 
 public enum MinMoeCheckChannelType
 {
   PRIVATE_SDK("PrivateSDK"), ISAPI_LISTEN("ISAPIListen");
   
   private final String version;
   
   MinMoeCheckChannelType(String version) {
     this.version = version;
   }
   
   public String getVersion() {
     return this.version;
   }
 }


