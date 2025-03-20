package controller;

import ordination.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    @DisplayName("Test for at se, om der oprettes en PNOrdination")
    void opretPNOrdination() {
        // Arrange
        Controller controller = new Controller();
        LocalDate startDato = LocalDate.of(2025, 3, 10);
        LocalDate slutDato = LocalDate.of(2025, 3, 20);
        Patient patient = new Patient("123456-7890", "Test Person1", 70);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1.0, 1.5, 2.0, "mg");
        double antal = 2.5;

        // Act
        PN pnOrdination = controller.opretPNOrdination(startDato, slutDato, patient, laegemiddel, antal);

        // Assert
        assertNotNull(pnOrdination, "Ordinationen bør ikke være null");
        assertEquals(startDato, pnOrdination.getStartDato(), "Startdatoen stemmer ikke overens");
        assertEquals(slutDato, pnOrdination.getSlutDato(), "Slutdatoen stemmer ikke overens");
        assertEquals(laegemiddel, pnOrdination.getLaegemiddel(), "Laegemidlet stemmer ikke overens");
        assertEquals(antal, pnOrdination.getAntalEnheder(), "Antallet stemmer ikke overens");
        assertTrue(patient.getOrdinationer().contains(pnOrdination), "Ordinationen er ikke tilføjet til patienten");
    }

    @Test
    @DisplayName("Test for, om der oprettes en DagligFastOrdination")
    void opretDagligFastOrdination() {
        // Arrange
        Controller controller = new Controller();
        LocalDate startDato = LocalDate.of(2025, 3, 10);
        LocalDate slutDato = LocalDate.of(2025, 3, 20);
        Patient patient = new Patient("234567-8910", "Test Person2", 75);
        Laegemiddel laegemiddel = new Laegemiddel("Acylsalicylsyre", 2.5, 4.5, 6.5, "kg");
        double morgenAntal = 2.0;
        double middagAntal = 2.5;
        double aftenAntal = 3.0;
        double natAntal = 3.5;

        // Act
        DagligFast dfOrdination = controller.opretDagligFastOrdination(startDato, slutDato, patient, laegemiddel, morgenAntal, middagAntal, aftenAntal, natAntal);

        // Assert
        assertNotNull(dfOrdination, "Ordinationen bør ikke være null");
        assertEquals(startDato, dfOrdination.getStartDato(), "Startdatoen stemmer ikke overens");
        assertEquals(slutDato, dfOrdination.getSlutDato(), "Slutdatoen stemmer ikke overens");
        assertEquals(laegemiddel, dfOrdination.getLaegemiddel(), "Laegemidlet stemmer ikke overens");
        assertTrue(patient.getOrdinationer().contains(dfOrdination), "Ordinationen er ikke tilføjet til patienten");
        // Kontrollér at doser er korrekt tilføjet
        Dosis[] doser = dfOrdination.getDoser();
        assertNotNull(doser, "Doser må ikke være null");
        assertEquals(4, doser.length, "Der skal være præcis 4 doser");
    }

    @Test
    @DisplayName("Test for, om der oprettes en DagligSkaevOrdination")
    void opretDagligSkaevOrdination() {
        // Arrange
        Controller controller = new Controller();
        LocalDate startDato = LocalDate.of(2025, 3, 10);
        LocalDate slutDato = LocalDate.of(2025, 3, 20);
        Patient patient = new Patient("234567-8910", "Test Person2", 75);
        Laegemiddel laegemiddel = new Laegemiddel("Fucidin", 2.5, 4.5, 6.5, "kg");
        LocalTime[] klokkeSlet = {LocalTime.of(8, 0), LocalTime.of(14, 0), LocalTime.of(22, 0)};
        double[] antalEnheder = {2.0, 1.5, 3.0};

        // Act
        DagligSkaev dsOrdination = controller.opretDagligSkaevOrdination(startDato, slutDato, patient, laegemiddel, klokkeSlet, antalEnheder);

        // Assert
        assertNotNull(dsOrdination, "Ordinationen bør ikke være null");
        assertEquals(startDato, dsOrdination.getStartDato(), "Startdatoen er forkert");
        assertEquals(slutDato, dsOrdination.getSlutDato(), "Slutdatoen er forkert");
        assertEquals(laegemiddel, dsOrdination.getLaegemiddel(), "Lægemidlet er forkert");
        assertTrue(patient.getOrdinationer().contains(dsOrdination), "Ordinationen bør være tilføjet til patientens liste");
        assertEquals(klokkeSlet.length, dsOrdination.getDoser().size(), "Antallet af doser er forkert");
    }

    @Test
    @DisplayName("Test for en gyldig anvendelse af PNordinationsdato")
    void ordinationPNAnvendt() {
        // Arrange
        Controller controller = new Controller();
        LocalDate startDato = LocalDate.of(2025, 3, 1);
        LocalDate slutDato = LocalDate.of(2025, 3, 10);
        Patient patient = new Patient("345678-9101", "Test Person4", 90);
        Laegemiddel laegemiddel = new Laegemiddel("Ibuprofen", 2.0, 3.0, 4.0, "kg");
        // Opret en PN-ordination
        PN ordination = new PN(startDato, slutDato, 2.0);
        ordination.setLaegemiddel(laegemiddel);
        patient.addOrdination(ordination);
        LocalDate anvendelsesDato = LocalDate.of(2025, 3, 5); // Gyldig dato

        // Act
        controller.ordinationPNAnvendt(ordination, anvendelsesDato);

        // Assert
        assertTrue(ordination.getDosisdatoer().contains(anvendelsesDato), "Anvendelsesdatoen bør være registreret i ordinationen");
    }

    @Test
    @DisplayName("Test for ugyldig anvendelse af PN-ordination - for tidlig dato")
    void ordinationPNAnvendt_UgyldigDato() {
        // Arrange
        Controller controller = new Controller();
        LocalDate startDato = LocalDate.of(2025, 3, 1);
        LocalDate slutDato = LocalDate.of(2025, 3, 10);
        Patient patient = new Patient("456789-1011", "Test Person5", 100);
        Laegemiddel laegemiddel = new Laegemiddel("Ibuprofen", 2.0, 3.0, 4.0, "kg");

        // Opret en PN-ordination
        PN ordination = new PN(startDato, slutDato, 2.0);
        ordination.setLaegemiddel(laegemiddel);
        patient.addOrdination(ordination);

        LocalDate ugyldigDato = LocalDate.of(2025, 2, 28); // Dato før startDato

        // Act & Assert (forvent en exception)
        assertThrows(IllegalArgumentException.class, () -> {
            controller.ordinationPNAnvendt(ordination, ugyldigDato);
        }, "Forventet IllegalArgumentException, da datoen er uden for gyldighedsperioden");
    }

    @Test
    @DisplayName("Test af anbefalet dosis for let patient (< 25 kg)")
    void anbefaletDosisPrDoegn() {
        // Arrange
        Controller controller = new Controller();
        Patient patient = new Patient("010101-1234", "Lille Lars", 20); // Let patient
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 2.0, 3.0, 4.0, "kg"); // Faktor for let patient: 2.0 mg/kg

        // Act
        double dosis = controller.anbefaletDosisPrDoegn(patient, laegemiddel);

        // Assert
        assertEquals(40.0, dosis, 0.01, "Forventet dosis: 20 kg * 2.0 mg/kg");
    }

    @Test
    @DisplayName("Test af antal ordinationer for et vægtinterval og lægemiddel")
    void antalOrdinationerPrVaegtPrLaegemiddel() {
        // Arrange
        Controller controller = new Controller();
        controller.createSomeObjects(); // Skal bruge de oprettede patienter
        double vaegtStart = 50.0;
        double vaegtSlut = 100.0;
        Laegemiddel laegemiddel = controller.getAllLaegemidler().get(1); // 3 med den vægt bliver behandlet med Paracetamol

        // Act
        int antalOrdinationer = controller.antalOrdinationerPrVaegtPrLaegemiddel(vaegtStart, vaegtSlut, laegemiddel);

        // Assert
        assertEquals(3, antalOrdinationer, "Forventet antal ordinationer for vægtinterval 50-100 kg med Paracetamol");
    }


    @Test
    void getAllPatienter() {
    }

    @Test
    void getAllLaegemidler() {
    }

    @Test
    void opretPatient() {
    }

    @Test
    @DisplayName("Test af opretLaegemiddel-metoden")
    void opretLaegemiddel() {
        // Arrange
        Controller controller = new Controller();
        String navn = "Ibuprofen";
        double let = 0.1;
        double normal = 0.15;
        double tung = 0.2;
        String enhed = "mg";
        // Act
        Laegemiddel laegemiddel = controller.opretLaegemiddel(navn, let, normal, tung, enhed);
        // Assert
        assertNotNull(laegemiddel, "Lægemiddel må ikke være null!");
        assertEquals(navn, laegemiddel.getNavn(), "Lægemiddels navn skal være korrekt!");
        assertEquals(let, laegemiddel.getEnhedPrKgPrDoegnLet(), 0.001, "Dosis for let patient skal være korrekt!");
        assertEquals(enhed, laegemiddel.getEnhed(), "Enhed skal være korrekt");
    }

    @Test
    void createSomeObjects() { // Metoden bliver testet i antalOrdinationerPrVaegtPrLaegemiddel()
    }
}