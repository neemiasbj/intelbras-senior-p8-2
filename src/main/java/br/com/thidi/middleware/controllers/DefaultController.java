 package br.com.thidi.middleware.controllers;
 
 import io.swagger.v3.oas.annotations.Hidden;
 import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.RestController;
 import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 import org.springframework.web.servlet.view.RedirectView;
 
 @RestController
 @Hidden
 public class DefaultController
 {
   @GetMapping({"/"})
   public RedirectView redirectWithUsingRedirectView(RedirectAttributes attributes) {
     return new RedirectView("/swagger-ui/index.html");
   }
 }


