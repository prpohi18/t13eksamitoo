package baas;

import java.util.*;
import java.text.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
@SpringBootApplication
public class Rakendus {
	@Autowired
	private AutoDAO AutoDao;

	@RequestMapping("/lisa")
	public String lisa(String nrmark) {
		Auto parkija=new Auto();
		if (AutoDao.findOne(nrmark)!=null) {
			parkija=AutoDao.findOne(nrmark);
			parkija.mitu=parkija.mitu+1;
		} else {
			//Optional<Auto> vanaAuto = AutoDao.findById(nrmark);
			//parkija.mitu=vanaAuto.mitu+1;
		parkija.mitu=1;
		}
		parkija.nrmark=nrmark;
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		Date algusDate = new Date();
		Date loppDate = new Date(System.currentTimeMillis() + 10800 * 1000);
		parkija.algus=dateFormat.format(algusDate);
		parkija.lopp=dateFormat.format(loppDate);
		parkija.loppjs=System.currentTimeMillis() + 10800 * 1000;
		AutoDao.save(parkija);
		return nrmark+" salvestatud";
	}
	@RequestMapping("/loetelu")
	public Iterable<Auto> loetelu(){
		return AutoDao.findAll();
	}
    public static void main(String[] args) {
        SpringApplication.run(Rakendus.class, args);
    }
}