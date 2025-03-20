package ordination;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrdinationTest {

    @Test
    @DisplayName("Test for at antalDage() returner både start- og slut-dato")
    void antalDage() {
        // Arrange
        LocalDate start = LocalDate.of(2025,1,1);
        LocalDate slut = LocalDate.of(2025,1,10); // 10 dage inklusive start og slut
        Ordination ordination = new TestOrdination(start, slut); // Vi bruger test-subklassen, da Ordination er abstract

        // Act
        int dage = ordination.antalDage();

        // Assert
        assertEquals(10, dage, "Antal dage skal inkludere både start- og slut-dato!");
    }

    @Test
    void testToString() { // Ikke nødvendigt at teste
    }

    @Test
    void samletDosis() { // Bliver testet i Fast, Skaev og PN
    }

    @Test
    void doegnDosis() { // Bliver testet i Fast, Skaev og PN
    }

    @Test
    void getLaegemiddel() { // Bliver testet flere steder
    }

    @Test
    @DisplayName("Test af setLaegemiddel")
    void setLaegemiddel() {
        // Arrange
        LocalDate start = LocalDate.of(2024, 3, 1);
        LocalDate slut = LocalDate.of(2024, 3, 10);
        TestOrdination ordination = new TestOrdination(start, slut);
        Laegemiddel ibuprofen = new Laegemiddel("Ibuprofen", 0.2, 0.3, 0.4, "mg");
        // Act
        ordination.setLaegemiddel(ibuprofen);
        // Assert
        assertEquals(ibuprofen, ordination.getLaegemiddel(), "Laegemiddel skal være sat korrekt");
    }

    @Test
    void getType() { // Bliver testet flere steder
    }
}