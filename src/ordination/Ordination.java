package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
// JOBS DONE + TEST COVERAGE GOOD --- Tobias 21-02-2025
public abstract class Ordination {
    private LocalDate startDato;
    private LocalDate slutDato;
    private Laegemiddel laegemiddel;

    /**
     * Basic Constructor
     * @param startDato
     * @param slutDato
     */
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
     * @return {@code int} antal dage ordinationen gælder for
     */
    public int antalDage() {
        return (int) ChronoUnit.DAYS.between(startDato, slutDato) + 1;
    }

    /**
     * Returnerer startdatoen for ordinationen som {@code String}
     */
    @Override
    public String toString() {
        return startDato.toString();
    }

    /**
     * Returnerer den totale dosis der er givet i den periode ordinationen er gyldig som {@code double}
     */
    public abstract double samletDosis();

    /**
     * Returnerer den gennemsnitlige dosis givet pr dag i den periode ordinationen er gyldig
     */
    public abstract double doegnDosis();

    /**
     * Returnerer laegemidlet for ordinationen
     */
    public Laegemiddel getLaegemiddel() {
        return laegemiddel;
    }

    /**
     * Sætter laegemidlet for ordinationen
     */
    public void setLaegemiddel(Laegemiddel laegemiddel) {
        this.laegemiddel = laegemiddel;
    }

    /**
     * Returnerer ordinationstypen som en String (PN, DagligFast eller Dagliskaev)
     */
    public abstract String getType();
}
