package eksam;

/**
 * Created by dev on 11/08/15.
 */


//hardcode'tud burger koos kahe lisandiga, midagi muuta ei saa!
public class DeluxeBurger extends hamburger {
    public DeluxeBurger() {
        super("Deluxe", "lihaveise", "seemne", 14.54);
        super.lisaHamburgeriLisand1("Friikartulid", 2.75);
        super.lisaHamburgeriLisand2("Jook", 1.81);
    }
//overrride, et ei saaks sellele burgerile anda kaasa lisandeid (v.a need, mis on juba hardcode'itud)
    @Override
    public void lisaHamburgeriLisand1(String name, double price) {
        System.out.println("Cannot add additional items to a deluxe burger");
    }

    @Override
    public void lisaHamburgeriLisand2(String name, double price) {
        System.out.println("Cannot add additional items to a deluxe burger");
    }

    @Override
    public void lisaHamburgeriLisand3(String name, double price) {
        System.out.println("Cannot add additional items to a deluxe burger");
    }

    @Override
    public void lisaHamburgeriLisand4(String name, double price) {
        System.out.println("Cannot add additional items to a deluxe burger");
    }
}
