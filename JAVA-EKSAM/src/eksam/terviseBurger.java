package eksam;

public class terviseBurger extends hamburger {

    public String terviseLisandi1Nimi;
    public double terviseLisandi1Hind;

    private String terviseLisandi2Nimi;
    private double terviseLisandi2Hind;

    public terviseBurger(String meat, double price) {
        super("Tervise", meat, "täistera", price);
    }

    public void lisaTerviseLisand1(String nimi, double hind) {
        this.terviseLisandi1Nimi = nimi;
        this.terviseLisandi1Hind = hind;
    }

    public void lisaTerviseLisand2(String nimi, double hind) {
        this.terviseLisandi2Nimi = nimi;
        this.terviseLisandi2Hind = hind;
    }

    //saadan hinnakalkulaatorisse ja lisan veel olemasolevatele lisanditele terviseLisandite hinnad
    @Override
    public double arvutaHind() {
        double hamburgerHind = super.arvutaHind();
        if(this.terviseLisandi1Nimi != null) {
            hamburgerHind += this.terviseLisandi1Hind;
            System.out.println("Terviseburgeri extra lisand nr 1: " + this.terviseLisandi1Nimi + ", hinnale lisandub " + this.terviseLisandi1Hind);
        }
        if(this.terviseLisandi2Nimi != null) {
            hamburgerHind += this.terviseLisandi2Hind;
            System.out.println("Terviseburgeri extra lisand nr 2: " + this.terviseLisandi2Nimi + ", hinnale lisandub " + this.terviseLisandi2Hind);
        }

        return hamburgerHind;
    }
}
