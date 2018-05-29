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
       float arvutamine(String tehe){
           /* int markCounter = 0;
            Var arvutusRida = [];
            Var operaator = [];
            int numbridTehtes = [];
            int nrCounter = 0;
            int counter = 0;
       for (int i = 0; i < tehe.length(); i++) {
            if (Character.isDigit(tehe.charAt(i))) {
            return 1;
            } else {
            return 2;
            }
            arvutusRida.splice(tehe.charAt(i));
            if(Characer.isDigit(tehe.charAt(i)) == true){
                String number = number + tehe.charAt(i);
            } else if {
                counter = counter + 1;
                nrCounter = nrCounter + 1;
                operaator[counter] = tehe.charAt(i);
                int numberInt = String.parseInt(number);
                numbridTehtes[nrCounter] = numberInt;
            }
       }*/
       return tehe;
    }
    public static void main(String[] args) {
		//System.getProperties().put("server.port", 40305);
        SpringApplication.run(Rakendus.class, args);
    }
}
