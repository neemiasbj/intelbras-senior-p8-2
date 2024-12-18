 package br.com.intelbras.enums;
 
 public enum MinMoeVersion
 {
   DS_K1T341("DS-K1T341"), DS_K1T342("DS-K1T342"), DS_K1T671("DS-K1T671"), DS_K1T673("DS-K1T673");
   
   private final String version;
   
   MinMoeVersion(String version) {
     this.version = version;
   }
   
   public String getVersion() {
     return this.version;
   }
 }


