package ordination;

import java.time.LocalDate;
 // FÆRDIG ARBEJD
public class DagligFast extends Ordination {
    private Dosis[] doser = new Dosis[4];

    public DagligFast(LocalDate startDato, LocalDate slutDato) {
        super(startDato, slutDato);
    }

    public Dosis[] getDoser() {
        return doser;
    }

    public void setDoser(Dosis[] doser) {
        this.doser = doser;
    }

    @Override
    public double samletDosis() {
        return this.doegnDosis() * super.antalDage();
    }

    @Override
    public double doegnDosis() {
        double dagligSamletDoser = 0.0;
        for (int i = 0; i < doser.length; i++) {
            dagligSamletDoser += doser[i].getAntal();
        }
        return dagligSamletDoser;
    }

    @Override
    public String getType() {
        return "DagligFast";
    }
}
