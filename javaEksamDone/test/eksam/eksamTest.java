package eksam;

import org.junit.Test;
import static org.junit.Assert.*;


public class eksamTest {
   
    // Testin tavalise burgeri loomist + lisandite lisamist
    @Test
    public void hamburgerTest() throws Exception {
        //teen burgeri
        hamburger hamburger = new hamburger ("Tavaline", "kana", "nisu", 3.5); 
        // lisan kaks tavalist lisandit
        hamburger.lisaHamburgeriLisand1("Tomat", 0.5);
        hamburger.lisaHamburgeriLisand2("Juust", 1.5);
        hamburger.lisaHamburgeriLisand3("Muna", 1.0);
        

        assertEquals(6.5, hamburger.arvutaHind(), 0.1);
    }
    
    // Testin terviseburgeri loomist + terviselisandite lisamist
    @Test
    public void terviseBurgerTest() throws Exception {
        //teen burgeri
        hamburger terviseBurger = new terviseBurger ("Veiseliha", 5.5); 
        // lisan terviselisandid
        terviseBurger.lisaHamburgeriLisand1("Luunja kurk", 0.75);
        terviseBurger.lisaHamburgeriLisand2("Cheddar juust", 1.5);

        assertEquals(7.75, terviseBurger.arvutaHind(), 0.1);
    }


}
