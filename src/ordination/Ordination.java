package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Ordination {
    private LocalDate startDato;
    private LocalDate slutDato;

    // TODO Link til Laegemiddel

    private Laegemiddel laegemiddel;
    // TODO constructor

    public Ordination(LocalDate startDato, LocalDate slutDato) {
        this.startDato = startDato;
        this.slutDato = slutDato;
    }
    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    /**
     * Antal hele dage mellem startdato og slutdato. Begge dage inklusive.
     * @return antal dage ordinationen gælder for
     */
    public int antalDage() {
        return (int) ChronoUnit.DAYS.between(startDato, slutDato) + 1;
    }

    @Override
    public String toString() {
        return startDato.toString();
    }

    /**
     * Returnerer den totale dosis der er givet i den periode ordinationen er gyldig
     * @return
     */
    public abstract double samletDosis();

    /**
     * Returnerer den gennemsnitlige dosis givet pr dag i den periode ordinationen er gyldig
     * @return
     */
    public abstract double doegnDosis();

    public Laegemiddel getLaegemiddel() {
        return laegemiddel;
    }

    public void setLaegemiddel(Laegemiddel laegemiddel) {
        this.laegemiddel = laegemiddel;
    }

    /**
     * Returnerer ordinationstypen som en String
     * @return
     */
    public abstract String getType();
}
