package eksam;


public class hamburger {
    private String nimi;
    private String pihv;
    private double hind;
    private String sai;

    private String lisandi1Nimi;
    private double lisandi1Hind;

    private String lisandi2Nimi;
    private double lisandi2Hind;

    private String lisandi3Nimi;
    private double lisandi3Hind;

    private String lisandi4Nimi;
    private double lisandi4Hind;

    //konstruktor
    public hamburger(String nimi, String pihv, String sai, double hind) {
        this.nimi = nimi;
        this.pihv = pihv;
        this.sai = sai;
        this.hind = hind;
    }
//meetodid
    public void lisaHamburgeriLisand1(String nimi, double hind) {
        this.lisandi1Nimi = nimi;
        this.lisandi1Hind = hind;
    }

    public void lisaHamburgeriLisand2(String nimi, double hind) {
        this.lisandi2Nimi = nimi;
        this.lisandi2Hind = hind;
    }
    public void lisaHamburgeriLisand3(String nimi, double hind) {
        this.lisandi3Nimi = nimi;
        this.lisandi3Hind = hind;
    }
    public void lisaHamburgeriLisand4(String nimi, double hind) {
        this.lisandi4Nimi = nimi;
        this.lisandi4Hind = hind;
    }

    //hinnakalkulaator, kasutatakn ka mujal
        public double arvutaHind() {
        double hamburgerHind = this.hind;
        System.out.println(this.nimi + " hamburger" + " " + this.sai + " saiaga "
                    + "ja " + this.pihv + " pihviga, maksab "  + this.hind);
        if(this.lisandi1Nimi != null) {
            hamburgerHind += this.lisandi1Hind;
            System.out.println("Tavaliseks lisandiks nr1: " + this.lisandi1Nimi + ", hinnale lisandub " + this.lisandi1Hind);
        }
        if(this.lisandi2Nimi != null) {
            hamburgerHind += this.lisandi2Hind;
            System.out.println("Tavaliseks lisandiks nr2: " + this.lisandi2Nimi + ", hinnale lisandub " + this.lisandi2Hind);
        }
        if(this.lisandi3Nimi != null) {
            hamburgerHind += this.lisandi3Hind;
            System.out.println("Tavaliseks lisandiks nr3: " + this.lisandi3Nimi + ", hinnale lisandub " + this.lisandi3Hind);
        }
        if(this.lisandi4Nimi != null) {
            hamburgerHind += this.lisandi4Hind;
            System.out.println("Ekstra lisandiks " + this.lisandi4Nimi + ", hinnale lisandub " + this.lisandi4Hind);
        }
        
        return hamburgerHind;
    }

    













}
