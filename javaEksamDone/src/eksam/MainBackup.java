

package eksam;

import java.util.Scanner;


public class MainBackup {
    double tellimusHamburger;
    public static void main(String[] args) {
        
        Scanner algus = new Scanner(System.in);
        System.out.println("Kas Sinu soov on burger või terviseburger? (burger/terviseburger)");
        String valik = algus.nextLine(); 
        
        
        if(valik.equals("burger")){
            Scanner start = new Scanner(System.in);
            System.out.println("Sisesta burgeri liha");
            String liha = start.nextLine();  
            System.out.println("Sisesta burgeri kukkel");
            String kukkel = start.nextLine();  
            System.out.println("Sisesta burgeri hind");
            double hind = start.nextDouble();  

            hamburger hamburger = new hamburger("Tavaline", liha,  kukkel, hind);
            


            //double HIND = hamburger.arvutaHind();
                //  LISANDID          
            Scanner lisandid1 = new Scanner(System.in);
            System.out.println("Kas tahad lisandeid (tomat, kurk, juust, salat)");
            String lisand1 = lisandid1.nextLine(); 
            if(lisand1.equals("jah")){
                Scanner lisandid11 = new Scanner(System.in);
                System.out.println("Sisesta lisand (tomat, kurk, juust, salat)");
                String lisand11 = lisandid11.nextLine(); 
               // hamburger.lisaHamburgeriLisand1(lisand1, 0.50);
            }
                // PREMIUM-LISANDID
            Scanner lisandid2 = new Scanner(System.in);
            System.out.println("Kas tahad premium-lisandeid (kirsstomat, Luunja kurk, Cheddar-juust, vutimuna)");
            String lisand2 = lisandid2.nextLine(); 
            if(lisand2.equals("jah")){
                Scanner lisandid22 = new Scanner(System.in);
                System.out.println("Sisesta lisand (kirsstomat, Luunja kurk, Cheddar-juust, vutimuna)");
                String lisand22 = lisandid22.nextLine(); 
                hamburger.lisaHamburgeriLisand1(lisand1, 1.50);
            }    
                
                //hamburger.lisaHamburgeriLisand2("Salat", 0.75);
                //hamburger.lisaHamburgeriLisand3("Juust", 1.13);
                System.out.println("Tavalise burgeri maksumus on kokku: " + hamburger.arvutaHind()+"\n");
            
            
            
      
            System.out.println("Tavalise burgeri maksumus on kokku: " + hamburger.arvutaHind()+"\n");
            
            
        //  TERVISEBURGER    
        } else if (valik.equals("terviseburger")){
            terviseBurger terviseBurger = new terviseBurger("sea sisefilee", 6);
            terviseBurger.lisaHamburgeriLisand1("Muna", 1);
            terviseBurger.lisaTerviseLisand1("Läätsed", 2);
            System.out.println("Terviseburgeri maksumus on kokku:  " + terviseBurger.arvutaHind()+"\n");
            //double tellimusterviseBurger = terviseBurger.arvutaHind();
        
        } else {
            System.out.println("Sisestasid vale valiku!");
        }
        
        
        
        
        
        // terviseburgeri osa
        /*
        terviseBurger terviseBurger = new terviseBurger("sea sisefilee", 6);
        terviseBurger.lisaHamburgeriLisand1("Muna", 1);
        terviseBurger.lisaTerviseLisand1("Läätsed", 2);
        System.out.println("Terviseburgeri maksumus on kokku:  " + terviseBurger.arvutaHind()+"\n");
        //double tellimusterviseBurger = terviseBurger.arvutaHind();
        
        */

        // System.out.println("TELLIMUS:  "+   (tellimusterviseBurger+tellimusHamburger));

        //deluxeBurgeri osa
        /*
        DeluxeBurger db = new DeluxeBurger();
        db.lisaHamburgeriLisand4("Should not do this", 50.53);
        db.arvutaHind();
        */
    }
}
