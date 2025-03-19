package ordination;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {

    @Test
    @DisplayName("Test at givDosis() registrerer dosis korrekt")
    void givDosis() {
        // Arrange
        PN pn = new PN(LocalDate.of(2025,3,10), LocalDate.of(2025,3,17), 2.0);
        // Act
        boolean resultatStartDato = pn.givDosis(LocalDate.of(2025,3,10)); // Gyldig (startdato)
        boolean resultatMidtDato = pn.givDosis(LocalDate.of(2025,3,13)); // Gyldig
        boolean resultatSlutDato = pn.givDosis(LocalDate.of(2025,3,17)); // Gyldig (slutdato)
        boolean resultatSammeDato = pn.givDosis(LocalDate.of(2025,3,13)); // Gyldig, men samme dato som før
        boolean resultatUdenfor = pn.givDosis(LocalDate.of(2025,3,18)); // Ugyldig (efter slutdato)
        // Assert
        assertTrue(resultatStartDato, "GivDosis() burde returnere true for en gyldig startdato!");
        assertTrue(resultatMidtDato, "GivDosis() burde returnere true for en gyldig dato!");
        assertTrue(resultatSlutDato, "GivDosis() burde returnere true for slutdato!");
        assertTrue(resultatSammeDato, "GivDosis() burde returnere true selvom samme dato vælges igen!");
        assertFalse(resultatUdenfor, "GivDosis() burde returnere false for en ugyldig dato!");
        assertEquals(4, pn.getAntalGangeGivet(), "Dosisdatoer listen burde indeholde 4 elementer efter registreringer!");
        // Måske skulle man tjekke om givDosis() tilføjer datoer korrekt
    }

    @Test
    @DisplayName("Test for at der returneres den korrekte døgndosis")
    void testDoegnDosisIngenDoser() {
        PN pn = new PN(LocalDate.of(2025,3,13),LocalDate.of(2025,3,18),2.0);
        // Act
        double result = pn.doegnDosis();
        // Assert
        assertEquals(0.0, result, 0.001, "Døgndosis bør være 0, når ingen doser er givet!");
    }
    @Test
    void testDoegnDosisEenDosis() {
        PN pn = new PN(LocalDate.of(2025,3,13),LocalDate.of(2025,3,18),2.0);
        // Act
        pn.givDosis(LocalDate.of(2025,3,17));
        double result = pn.doegnDosis();
        // Assert
        assertEquals(2.0, result, 0.001, "Døgndosis bør være antalEnheder, når een dosis er givet!");
    }
    @Test
    void testDoegnDosisFlereDoser() {
        // Arrange
        PN pn = new PN(LocalDate.of(2025,3,10), LocalDate.of(2025,3,19),3.0);
        // Act
        pn.givDosis(LocalDate.of(2025,3,13));
        pn.givDosis(LocalDate.of(2025,3,16));
        pn.givDosis(LocalDate.of(2025,3,17));
        double result = pn.doegnDosis();
        // Assert (9/4)
        assertEquals(2.25, result, 0.001, "Døgndosis er ikke korrekt udregnet ud fra antal doser og antal dage!");
    }

    @Test
    @DisplayName("Test for at det samlede antal doser udregnes korrekt")
    void samletDosis() {
        // Arrange
        PN pn = new PN(LocalDate.of(2025,3,10), LocalDate.of(2025,3,19),3.0);
        // Act
        pn.givDosis(LocalDate.of(2025,3,13));
        pn.givDosis(LocalDate.of(2025,3,16));
        pn.givDosis(LocalDate.of(2025,3,17));
        double antalDage = pn.antalDage();
        double result = pn.samletDosis();
        // Assert (9)
        assertEquals(pn.getDosisdatoer().size() * pn.getAntalEnheder(), result, 0.001, "Samlet dosis er ikke korrekt udregnet ud fra antal doser og antal dage!");
    }

    @Test
    @DisplayName("Test for at få antal gange ordination er anvendt")
    void getAntalGangeGivet() {
        // Arrange
        PN pn = new PN(LocalDate.of(2025,3,10), LocalDate.of(2025,3,17),2.0);
        // Act
        pn.givDosis(LocalDate.of(2025,3,14));
        pn.givDosis(LocalDate.of(2025,3,16));
        int antalDatoer = pn.getDosisdatoer().size(); // (2)
        // Assert
        assertEquals(antalDatoer, pn.getAntalGangeGivet(), "Antallet af gange ordinationene er anvendt, skal passe til antal datoer!");
    }

    @Test
    @DisplayName("Test for om vi kan hente antal enheder fra ordination")
    void getAntalEnheder() {
        // Arrange
        PN pn = new PN(LocalDate.of(2025,3,10), LocalDate.of(2025,3,17),2.0);
        // Act unødvendig
        // Assert
        assertEquals(2.0, pn.getAntalEnheder(), "Antallet af enheder, skal svare til angivne antal i ordinationen!");
    }

    @Test
    @DisplayName("Vi skal teste, om getType() returnerer den rigtige type ordination")
    void getType() {
        // Arrange
        PN ordination = new PN(LocalDate.of(2025,3,17),
                LocalDate.of(2025,3,24), 4.0);
        // Act
        String faktiskType = ordination.getType();
        // Assert
        assertEquals("PN", faktiskType, "getType() returnerer forkert ordinationstype!");
    }
}