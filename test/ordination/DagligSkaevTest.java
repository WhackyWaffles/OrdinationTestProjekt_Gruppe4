package ordination;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {

    @Test
    @DisplayName("Test at getDoser() returnerer de korrekte doser")
    void getDoser() {
        // Arrange
        DagligSkaev dagligSkaev = new DagligSkaev(LocalDate.of(2025,3,17),LocalDate.of(2025,3,24));

        // Act - Tilknyt doser
        dagligSkaev.opretDosis(LocalTime.of(8,00),2.0);
        dagligSkaev.opretDosis(LocalTime.of(12,00),1.5);
        dagligSkaev.opretDosis(LocalTime.of(18,00),2.5);

        // Assert
        assertEquals(3, dagligSkaev.getDoser().size(), "Der skal være 3 doser i listen!");

        assertEquals(2.0, dagligSkaev.getDoser().get(0).getAntal(), "Første dosis antal er forkert!");
        assertEquals(LocalTime.of(8,00), dagligSkaev.getDoser().get(0).getTid(), "Første dosis tidspunkt er forkert!");

        assertEquals(1.5,dagligSkaev.getDoser().get(1).getAntal(), "Anden dosis antal er forkert!");
        assertEquals(LocalTime.of(12,00), dagligSkaev.getDoser().get(1).getTid(), "Anden dosis tidspunkt er forkert!");

        assertEquals(2.5, dagligSkaev.getDoser().get(2).getAntal(), "Tredje dosis antal er forkert!");
        assertEquals(LocalTime.of(18,00), dagligSkaev.getDoser().get(2).getTid(), "Tredje dosis tidspunkt er forkert!");
    }

    @Test
    @DisplayName("Test at opretDosis() kan oprette doser")
    void opretDosis() {
        // Arrange
        DagligSkaev dagligSkaev = new DagligSkaev(LocalDate.of(2025,3,10), LocalDate.of(2025,3,16));
        // Act
        dagligSkaev.opretDosis(LocalTime.of(7,30),1.0);
        // Assert
        assertEquals(1, dagligSkaev.getDoser().size(), "Der burde være 1 dosis i listen!");
        assertEquals(1.0, dagligSkaev.getDoser().get(0).getAntal(), "Dosis1 antal er forkert!");
        assertEquals(LocalTime.of(7,30), dagligSkaev.getDoser().get(0).getTid(),"Dosis1 tidspunkt er forkert!");
        // Act
        dagligSkaev.opretDosis(LocalTime.of(12,30), 2.5);
        // Assert - Tjek om der er oprettet to doser
        assertEquals(2, dagligSkaev.getDoser().size(), "Der burde være 2 doser i listen!");
        assertEquals(2.5, dagligSkaev.getDoser().get(1).getAntal(), "Dosis2 antal er forkert!");
        assertEquals(LocalTime.of(12,30), dagligSkaev.getDoser().get(1).getTid(),"Dosis2 tidspunkt er forkert!");
    }

    @Test
    @DisplayName("Test at den samlede dosis kan regnes ud for hele perioden")
    void samletDosis() {
        // Arrange
        DagligSkaev dagligSkaev = new DagligSkaev(LocalDate.of(2025,2,17), LocalDate.of(2025,2,23));
        // Act
        dagligSkaev.opretDosis(LocalTime.of(7,00),2.0);
        dagligSkaev.opretDosis(LocalTime.of(12,30),1.5);
        dagligSkaev.opretDosis(LocalTime.of(19,00),2.5);
        // Forventet værdi
        double forventetSamletDosis = 42.0;
        // Assert
        assertEquals(forventetSamletDosis, dagligSkaev.samletDosis(), 0.001, "Samlet dosis returnerer ikke korrekt antal!");
    }

    @Test
    @DisplayName("Test for at doegnDosis() returnerer korrekt antal doser for et døgn")
    void doegnDosis() {
        // Arrange
        DagligSkaev dagligSkaev = new DagligSkaev(LocalDate.of(2025,2,17), LocalDate.of(2025,2,23));
        // Act
        dagligSkaev.opretDosis(LocalTime.of(7,00),2.0);
        dagligSkaev.opretDosis(LocalTime.of(12,30),1.5);
        dagligSkaev.opretDosis(LocalTime.of(19,00),2.5);
        // Forventet værdi
        double forventetDoegnDosis = 6.0;
        // Assert
        assertEquals(forventetDoegnDosis, dagligSkaev.doegnDosis(), 0.001, "Doegndosis returnerer ikke korrekt antal!");
    }

    @Test
    @DisplayName("Vi skal teste, om getType() returnerer den rigtige type ordination")
    void getType() {
            // Arrange
            DagligSkaev ordination = new DagligSkaev(LocalDate.of(2025,3,17),
                    LocalDate.of(2025,3,24));
            // Act
            String faktiskType = ordination.getType();
            // Assert
            assertEquals("DagligSkaev", faktiskType, "getType() returnerer forkert ordinationstype!");
    }
}
