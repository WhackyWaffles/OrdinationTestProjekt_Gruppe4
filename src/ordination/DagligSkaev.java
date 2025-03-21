package ordination;

// Mojn, sku' ås' vær' fær' - Henrik
// JOBS DONE + TEST COVERAGE GOOD --- Tobias 21-02-2025
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {
    private ArrayList<Dosis> doser = new ArrayList<>();

    public DagligSkaev(LocalDate startDato, LocalDate slutDato) {
        super(startDato, slutDato);
    }

    public ArrayList<Dosis> getDoser() {
        return doser;
    }

    public void opretDosis(LocalTime tid, double antal) {
        doser.add(new Dosis(tid, antal));
    }

    @Override
    public double samletDosis() {
        return doegnDosis() * (double) super.antalDage();
    }

    @Override
    public double doegnDosis() {
            double sumDosis = 0;
            for (Dosis dskaev : doser) {
                sumDosis += dskaev.getAntal();
            }
        return sumDosis;
    }

    @Override
    public String getType() {
        return "DagligSkaev";
    }
}
