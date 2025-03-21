package ordination;

import java.time.LocalTime;
// JOBS DONE + TEST COVERAGE GOOD --- Tobias 21-02-2025
public class Dosis {
    private LocalTime tid;
    private double antal;

    public Dosis(LocalTime tid, double antal) {
        this.tid = tid;
        this.antal = antal;
    }

    public double getAntal() {
        return antal;
    }

    public void setAntal(double antal) {
        this.antal = antal;
    }

    public LocalTime getTid() {
        return tid;
    }

    public void setTid(LocalTime tid) {
        this.tid = tid;
    }

    @Override
    public String toString(){
        return "Kl: " + tid + "   antal:  " + antal;
    }

}
