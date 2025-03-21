package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
// JOBS DONE + TEST COVERAGE GOOD --- Tobias 21-02-2025
public class PN extends Ordination {

    private double antalEnheder;
    private ArrayList<LocalDate> dosisdatoer = new ArrayList<>();

    public PN(LocalDate startDato, LocalDate slutDato, double antalEnheder) {
        super(startDato, slutDato);
        this.antalEnheder = antalEnheder;
    }

    /** Registrerer at der er givet en dosis paa dagen givetDato.
     * Returnerer true hvis givetDato er inden for ordinationens gyldighedsperiode og datoen huskes.
     * Returnerer false ellers, og datoen givetDato ignoreres.
     * @param givetDato
     * @return {@code boolean} */
    public boolean givDosis(LocalDate givetDato) {
        // Tjekker, om givetDato er inden for ordinationens gyldighedsperiode.
        if (!givetDato.isBefore(super.getStartDato()) && !givetDato.isAfter(super.getSlutDato())) {
            // Hvis den (ikke) er; tilføjer givetDato til ArrayListen på en plads, sådan at ArrayListen forbliver sorteret.
            if (dosisdatoer.isEmpty()) {
                dosisdatoer.add(givetDato);
                return true;
            }
            // Indsæt givetDato i den rigtige rækkefølge
            for (int i = 0; i < dosisdatoer.size(); i++) {
                // Tjekker dato for, om den er først i datolisten.
                if (givetDato.isBefore(dosisdatoer.get(i))) {
                    dosisdatoer.add(i, givetDato); // Indsæt i sorteret rækkefølge
                    return true;
                }
            }
            // Hvis vi når hertil, er givetDato den nyeste, så tilføj den til sidst
            dosisdatoer.add(givetDato);
            return true;
        }
        return false;
        }

    public double doegnDosis() {
        if (dosisdatoer.isEmpty()) {
            return 0; // Skal kunne håndtere ingen doser givet
        }
        long antalDage = ChronoUnit.DAYS.between(dosisdatoer.getFirst(), dosisdatoer.getLast()) + 1;
//        (antal gange ordinationen er anvendt * antal enheder) / (antal dage mellem første og sidste givning)
        return (double) (dosisdatoer.size() * antalEnheder) / antalDage;
    }

    public double samletDosis() {
        return (double) ((double) dosisdatoer.size()) * antalEnheder;
    }

    /**
     *  @return {@code int} antal gange ordinationen er anvendt*/
    public int getAntalGangeGivet() {
        return dosisdatoer.size();
    }

    /**
     * @return {@code double} enheder per dosis
     */
    public double getAntalEnheder() {
        return antalEnheder;
    }

    public ArrayList<LocalDate> getDosisdatoer() { // Har behov for en getter til test
        return dosisdatoer;
    }

    @Override
    public String getType() {
        return "PN";
    }

}
