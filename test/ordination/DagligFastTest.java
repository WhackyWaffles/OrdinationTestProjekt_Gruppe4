package ordination;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {

    @Test
    @DisplayName("Test at getDoser() returnerer de korrekte doser")
    void getDoser() {
        //Arrange
        LocalDate startDato = LocalDate.of(2024,3,17);
        LocalDate slutDato = LocalDate.of(2024, 3,24);
        DagligFast ordination = new DagligFast(startDato, slutDato);

        Dosis[] forventedeDoser = new Dosis[] {
                new Dosis(LocalTime.of(7,00), 2.0),
                new Dosis(LocalTime.of(12,00), 1.0),
                new Dosis(LocalTime.of(18,00), 1.0),
                new Dosis(LocalTime.of(23,00),2.0)
        };

        ordination.setDoser(forventedeDoser);

        //Act
        Dosis[] faktiskeDoser = ordination.getDoser();

        //Assert
        assertArrayEquals(forventedeDoser, faktiskeDoser, "getDoser() returnerer ikke de rigtige doser");
    }

    @Test
    @DisplayName("")
    void setDoser() {
    }

    @Test
    @DisplayName("")
    void testSamletDosis() {
    }

    @Test
    @DisplayName("")
    void testDoegnDosis() {
    }

    @Test
    @DisplayName("")
    void testGetType() {
    }
}