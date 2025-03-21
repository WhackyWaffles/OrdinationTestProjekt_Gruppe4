package ordination;

import java.util.ArrayList;
// JOBS DONE + TEST COVERAGE GOOD --- Tobias 21-02-2025
public class Patient {
    private String cprnr;
    private String navn;
    private double vaegt;
    private ArrayList<Ordination> ordinationer = new ArrayList<>();

    /**
     * Basic Constructor
     * @param cprnr
     * @param navn
     * @param vaegt
     */
    public Patient(String cprnr, String navn, double vaegt) {
        this.cprnr = cprnr;
        this.navn = navn;
        this.vaegt = vaegt;
    }

    public String getCprnr() {
        return cprnr;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getVaegt(){
        return vaegt;
    }

    public void setVaegt(double vaegt){
        this.vaegt = vaegt;
    }

    public ArrayList<Ordination> getOrdinationer() {
        return ordinationer;
    }

    public void addOrdination(Ordination ordination) {
        if (ordination != null && !ordinationer.contains(ordination)) {
            ordinationer.add(ordination);
        }
    }

    // Så vidt vi ved, en unødvendig metode.
//    public void removeOrdination(Ordination ordination) {
//        if (ordination != null && ordinationer.contains(ordination)) {
//            ordinationer.remove(ordination);
//        }
//    }

    @Override
    public String toString(){
        return navn + "  " + cprnr;
    }

}
