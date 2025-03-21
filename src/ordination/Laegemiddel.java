package ordination;
// JOBS DONE + TEST COVERAGE GOOD --- Tobias 21-02-2025
public class Laegemiddel {
    private String navn;
    private double enhedPrKgPrDoegnLet;   // faktor der anvendes hvis patient vejer < 25 kg
    private double enhedPrKgPrDoegnNormal;// faktor der anvendes hvis 25 kg <= patient vægt <= 120 kg
    private double enhedPrKgPrDoegnTung;  // faktor der anvendes hvis patient vægt > 120 kg 
    private String enhed;

    public Laegemiddel(String navn, double enhedPrKgPrDoegnLet, double enhedPrKgPrDoegnNormal, 
            double enhedPrKgPrDoegnTung, String enhed) {
        this.navn = navn;
        this.enhedPrKgPrDoegnLet = enhedPrKgPrDoegnLet;
        this.enhedPrKgPrDoegnNormal = enhedPrKgPrDoegnNormal;
        this.enhedPrKgPrDoegnTung = enhedPrKgPrDoegnTung;
        this.enhed = enhed;
    }

    public double getEnhedPrKgPrDoegnLet() { return enhedPrKgPrDoegnLet; }

    public String getEnhed() {
        return enhed;
    }

    public String getNavn() {
        return navn;
    }

    public double anbefaletDosisPrDoegn(int vaegt) {
        if (vaegt <= 0) {
            return 0.0;
        } else if (vaegt < 25) {
            return vaegt * enhedPrKgPrDoegnLet;
        } else if (vaegt <= 120) {
            return vaegt * enhedPrKgPrDoegnNormal;
        } else { // if (vaegt > 120)
            return vaegt * enhedPrKgPrDoegnTung;
        }
    }

    @Override
    public String toString(){
        return navn;
    }
}
