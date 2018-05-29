package springalgus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
public class Rakendus {
	@RequestMapping("/algus")
    String tervitusfunktsioon() {
        return "Ahoi!";
    }
    
    @RequestMapping("/tervitus")
    String tervitus2(String eesnimi){
        return "Tere, "+eesnimi;
    }
    
    @RequestMapping("/liitmine")
       float liitmine(float arv1, float arv2){
           return arv1+arv2;
       }

    @RequestMapping("/lahutamine")
       float lahutamine(float arv1, float arv2){
           return arv1-arv2;
       }

    @RequestMapping("/korrutamine")
       float korrutamine(float arv1, float arv2){
           return arv1*arv2;
       }

    @RequestMapping("/jagamine")
       float jagamine(float arv1, float arv2){
           return arv1/arv2;
       }

    @RequestMapping("/arvutamine")
       float arvutamine(float nr1, String operator, float nr2){
           float vastus = 0;
           if(operator == "*"){
               vastus = nr1 * nr2;
           } else if (operator == "/"){
               vastus = nr1 / nr2;
           } else if (operator == "+"){
               vastus = nr1 + nr2;
           } else if (operator == "-"){
               vastus = nr1 - nr2;
           }
           return vastus;
            
    }
    public static void main(String[] args) {
		//System.getProperties().put("server.port", 40305);
        SpringApplication.run(Rakendus.class, args);
    }
}
