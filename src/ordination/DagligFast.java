package ordination;


public class DagligFast extends Ordination {
    private Dosis[] doser = new Dosis[4];

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
        return "";
    }
}
