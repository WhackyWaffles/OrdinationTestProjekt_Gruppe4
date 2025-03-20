package ordination;

import java.awt.*;
import java.time.LocalDate;

class TestOrdination extends Ordination {
    public TestOrdination(LocalDate startDato, LocalDate slutDato) {
        super(startDato, slutDato);
    }

    @Override
    public double samletDosis() {
        return 0;
    }

    @Override
    public double doegnDosis() {
        return 0;
    }

    @Override
    public String getType() {
        return "";
    }
}
