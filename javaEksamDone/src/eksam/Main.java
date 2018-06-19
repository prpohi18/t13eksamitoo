package eksam;

import java.util.Scanner;


public class Main {
    double tellimusHamburger;
    public static void main(String[] args) {
        
        
        //loon hamburgeri ja arvutan hinna koos lisanditega
        hamburger hamburger = new hamburger("Tavaline", "sealiha", "nisu", 3.56);
        hamburger.lisaHamburgeriLisand1("Tomat", 0.27);
        hamburger.lisaHamburgeriLisand2("Salat", 0.75);
        hamburger.lisaHamburgeriLisand3("Juust", 1.13);
        System.out.println("Tavalise burgeri maksumus on kokku: " + hamburger.arvutaHind()+"\n");
     
        
        
        // terviseburgeri osa
        terviseBurger terviseBurger = new terviseBurger("sea sisefilee", 6);
        terviseBurger.lisaHamburgeriLisand1("Muna", 1);
        terviseBurger.lisaTerviseLisand1("Läätsed", 2);
        System.out.println("Terviseburgeri maksumus on kokku:  " + terviseBurger.arvutaHind()+"\n");
        //double tellimusterviseBurger = terviseBurger.arvutaHind();
        
        

       
        //deluxeBurgeri osa
        DeluxeBurger db = new DeluxeBurger();
        db.lisaHamburgeriLisand4("Should not do this", 50.53);
        db.arvutaHind();
       
    }
}
