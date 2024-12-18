 package br.com.thidi.middleware.utils;
 
 import com.google.gson.Gson;
 import java.util.List;
 import java.util.Map;
 import java.util.Properties;
 import java.util.stream.Collectors;
 
 public class MiddlewareUtilPropertiesValid
 {
   private String prop;
   private String value;
   
   public MiddlewareUtilPropertiesValid(String prop, String value) {
     this.prop = prop;
     this.value = value;
   }
 
   
   public String toString() {
     return "UtilPropertiesValid [prop:" + this.prop + ", value:" + this.value + "]";
   }
   
   public String getProp() {
     return this.prop;
   }
   
   public void setProp(String prop) {
     this.prop = prop;
   }
   
   public String getValue() {
     return this.value;
   }
   
   public void setValue(String value) {
     this.value = value;
   }
   
   public static String personalizeMessageAsJSON() {
     Gson gson = new Gson();
     Properties prop = MiddlewarePropertiesUtilImpl.getProperties();
     
     List<MiddlewareUtilPropertiesValid> propriedadesList = (List<MiddlewareUtilPropertiesValid>)prop.entrySet().stream()
       .map(entry -> new MiddlewareUtilPropertiesValid(entry.getKey().toString(), entry.getValue().toString()))
       .collect(Collectors.toList());
     return gson.toJson(propriedadesList);
   }
 }


