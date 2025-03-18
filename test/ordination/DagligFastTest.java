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
        LocalDate startDato = LocalDate.of(2025,3,17);
        LocalDate slutDato = LocalDate.of(2025, 3,24);
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
    @DisplayName("Test at setDoser() korrekt opdaterer doser-array")
    void setDoser() {
        // Arrange
        LocalDate startDato = LocalDate.of(2025,3,17);
        LocalDate slutDato = LocalDate.of(2025,03,24);
        DagligFast ordination = new DagligFast(startDato, slutDato);

        Dosis[] nyeDoser = new Dosis[] {
                new Dosis(LocalTime.of(7,00), 1.0),
                new Dosis(LocalTime.of(12,00),1.5),
                new Dosis(LocalTime.of(18,00), 2.0),
                new Dosis(LocalTime.of(23,00), 2.5)
        };
        // Act
        ordination.setDoser(nyeDoser);
        // Assert
        assertArrayEquals(nyeDoser, ordination.getDoser(), "setDoser() opdaterer ikke doserne korrekt!");
    } // Vi kan også lave en test, hvor vi kalder setDoser() to gange og sikrer, at den sidste værdi overskriver den første.

    @Test
    @DisplayName("Test at samletDosis() returner samlet værdi")
    void testSamletDosis() {
        // Arrange
        LocalDate startDato = LocalDate.of(2025,3,17);
        LocalDate slutDato = LocalDate.of(2025,3,23);
        DagligFast ordination = new DagligFast(startDato, slutDato);

        Dosis[] doser = new Dosis[] {
                new Dosis(LocalTime.of(7,00),1.0),
                new Dosis(LocalTime.of(12,00), 1.0),
                new Dosis(LocalTime.of(18,00), 1.0),
                new Dosis(LocalTime.of(23,00), 1.0)
        };
        ordination.setDoser(doser);
        // Act
        double faktiskDosis = ordination.samletDosis();
        double forventetDosis = (1.0 + 1.0 + 1.0 + 1.0) * 7; // Doegnsdosis * antal dage
        // Assert
        assertEquals(forventetDosis, faktiskDosis, 0.001, "samletDosis() beregner forkert total dosis!");
    }

    @Test
    @DisplayName("Test at doegnDosis() beregner den rigtige mængde pr. døgn")
    void testDoegnDosis() {
        // Arrange
        LocalDate startDato = LocalDate.of(2025,3,17);
        LocalDate slutDato = LocalDate.of(2025,3,23);
        DagligFast ordination = new DagligFast(startDato, slutDato);

        Dosis[] doser = new Dosis[] {
                new Dosis(LocalTime.of(7,00),1.0),
                new Dosis(LocalTime.of(12,00), 1.0),
                new Dosis(LocalTime.of(18,00), 1.0),
                new Dosis(LocalTime.of(23,00), 1.0)
        };
        ordination.setDoser(doser);
        double forventetDosis = (1.0 + 1.0 + 1.0 + 1.0); // Doegnsdosis for det døgn
        // Act
        double faktiskDosis = ordination.doegnDosis();
        // Assert
        assertEquals(forventetDosis, faktiskDosis, 0.001, "DoegnDosis() beregner forkert dosis pr. døgn");
    }

    @Test
    @DisplayName("Vi skal teste, om getType() returnerer den rigtige type ordination")
    void testGetType() {
        // Arrange
        DagligFast ordination = new DagligFast(LocalDate.of(2025,3,17),
                LocalDate.of(2025,3,24));
        // Act
        String faktiskType = ordination.getType();
        // Assert
        assertEquals("DagligFast", faktiskType, "getType() returnerer forkert ordinationstype!");
    }
}