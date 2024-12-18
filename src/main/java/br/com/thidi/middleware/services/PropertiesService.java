 package br.com.thidi.middleware.services;
 
 import org.springframework.beans.factory.annotation.Value;
 
 public class PropertiesService
 {
   @Value("${pom.version}")
   private String pomVersion;
   
   public String getPomVersion() {
     return this.pomVersion;
   }
   
   public void setPomVersion(String pomVersion) {
     this.pomVersion = pomVersion;
   }
 }


