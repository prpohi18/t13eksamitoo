package springalgus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;
import java.util.Calendar;
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
	
	@RequestMapping("/korrutus")
	int korrutamine(int arv1, int arv2){
		return arv1*arv2;
		
	}
	@RequestMapping("/trapetsipindala")
	int trapets(int alus1, int alus2, int kõrgus){
		return (alus1 + alus2) /2 * kõrgus;
	}	
	@RequestMapping("/elektrihind")
	String elekter(float kwh, float hind_kwh, float tundide_arv, String tarbija){ 
	Calendar calendar = Calendar.getInstance();
	int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return "Teie prognoositav kuuajane elektrikulu seadmele "+ tarbija + " on " + (kwh * tundide_arv * days * hind_kwh) + " eurot";
	}
 
    public static void main(String[] args) {
		//System.getProperties().put("server.port", 40305);
        SpringApplication.run(Rakendus.class, args);
    }
}
