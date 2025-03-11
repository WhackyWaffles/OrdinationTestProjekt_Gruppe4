package ordination;

import java.time.LocalDate;
import java.util.ArrayList;

public class PN extends Ordination {

    private double antalEnheder;
    private ArrayList<LocalDate> dosisdatoer = new ArrayList<>();

    public PN(LocalDate startDato, LocalDate slutDato, double antalEnheder) {
        super(startDato, slutDato);
        this.antalEnheder = antalEnheder;
    }

    /**
     * <p>Registrerer at der er givet en dosis paa dagen givetDato.
     * <p>Returnerer true hvis givetDato er inden for ordinationens gyldighedsperiode og datoen huskes.
     * <p>Returnerer false ellers, og datoen givetDato ignoreres.
     *
     * @param givetDato
     * @return {@code boolean}
     */
    public boolean givDosis(LocalDate givetDato) {
        // Tjekker, om givetDato er inden for ordinationens gyldighedsperiode.
        if (givetDato.isAfter(super.getStartDato()) && givetDato.isBefore(super.getSlutDato())) {
            // Hvis den er; tilføjer givetDato til ArrayListen på en plads, sådan at ArrayListen forbliver sorteret.
            for (int i = 0; i < dosisdatoer.size() - 1; i++) {
                // Tjekker dato for, om den er først i datolisten.
                if (givetDato.isBefore(dosisdatoer.get(i))) {
                    dosisdatoer.addFirst(givetDato);
                // Tjekker dato for, om den er i midten af datolisten.
                } else if (givetDato.isAfter(dosisdatoer.get(i)) && givetDato.isBefore(dosisdatoer.get(i + 1))) {
                    dosisdatoer.add(i + 1, givetDato);
                // Tjekker dato for, om den er til sidst i datolisten.
                } else if (givetDato.isAfter(dosisdatoer.get(i + 1))) {
                    dosisdatoer.add(givetDato);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public double doegnDosis() {
//        (antal gange ordinationen er anvendt * antal enheder) / (antal dage mellem første og sidste givning)
        return (double) (((double) dosisdatoer.size()) * antalEnheder) / ((double) (dosisdatoer.getFirst().compareTo(dosisdatoer.getLast())));
    }


    public double samletDosis() {
        return (double) ((double) dosisdatoer.size()) * antalEnheder;
    }

    /**
     * <p>Returnerer antal gange ordinationen er anvendt
     *
     * @return {@code int}
     */
    public int getAntalGangeGivet() {
        return dosisdatoer.size();
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

    @Override
    public String getType() {
        return "PN";
    }

}
