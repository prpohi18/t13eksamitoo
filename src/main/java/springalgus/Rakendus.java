package springalgus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

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
       int arvutamine(String tehe){
           /*float vastus = 0;
           if(operator == "*"){
               vastus = nr1 * nr2;
           } else if (operator == "/"){
               vastus = nr1 / nr2;
           } else if (operator == "+"){
               vastus = nr1 + nr2;
           } else if (operator == "-"){
               vastus = nr1 - nr2;
           }*/
        String[] numbrid = tehe.split("\\*|\\/|\\+|\\-");
        int number1 = Integer.parseInt(numbrid[0]);
        int number2 = Integer.parseInt(numbrid[1]);
        int vastus = 0;
        for (int i=0; i<tehe.length(); i++) {
            if (tehe.charAt(i) == '*'){
                vastus = number1 * number2;
            } else if(tehe.charAt(i) == '/'){
                vastus = number1 / number2;
            } else if(tehe.charAt(i) == '+'){
                vastus = number1 + number2;
            } else if(tehe.charAt(i) == '-'){
                vastus = number1 - number2;
            }
        } 
        return vastus;
            
    }
    public static void main(String[] args) {
		//System.getProperties().put("server.port", 40305);
        SpringApplication.run(Rakendus.class, args);
    }
}
