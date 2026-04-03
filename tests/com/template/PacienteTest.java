package com.template;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;

public class PacienteTest {

    private Paciente pacienteA;
    private Paciente pacienteB;
    private Paciente pacienteC;
    private Paciente pacienteD;
    private Paciente pacienteE;

    @BeforeEach
    void setUp() {
        pacienteA = new Paciente("Maria Ramirez", "apendicitis", 'A');
        pacienteB = new Paciente("Carmen Sarimientos", "dolores de parto", 'B');
        pacienteC = new Paciente("Juan Perez", "fractura de pierna", 'C');
        pacienteD = new Paciente("Luis Gonzalez", "gripe", 'D');
        pacienteE = new Paciente("Lorenzo Toledo", "chikunguya", 'E');
    }

    @Test
    @DisplayName("Test de constructor y getters")
    @Order(1)
    void testConstructorYGetters() {
        assertEquals("Maria Ramirez", pacienteA.getNombre());
        assertEquals("apendicitis", pacienteA.getSintoma());
        assertEquals('A', pacienteA.getCodigoEmergencia());
        
        assertEquals("Lorenzo Toledo", pacienteE.getNombre());
        assertEquals("chikunguya", pacienteE.getSintoma());
        assertEquals('E', pacienteE.getCodigoEmergencia());
    }

    @Test
    @DisplayName("Test de compareTo - prioridad A mayor que B")
    @Order(2)
    void testCompareToPrioridadAMayorQueB() {
        assertTrue(pacienteA.compareTo(pacienteB) < 0, 
                   "A debería tener mayor prioridad (menor número) que B");
    }

    @Test
    @DisplayName("Test de compareTo - prioridad B mayor que C")
    @Order(3)
    void testCompareToPrioridadBMayorQueC() {
        assertTrue(pacienteB.compareTo(pacienteC) < 0,
                   "B debería tener mayor prioridad que C");
    }

    @Test
    @DisplayName("Test de compareTo - prioridad E menor que todas")
    @Order(4)
    void testCompareToPrioridadEMenor() {
        assertTrue(pacienteE.compareTo(pacienteA) > 0,
                   "E debería tener menor prioridad (mayor número) que A");
        assertTrue(pacienteE.compareTo(pacienteB) > 0,
                   "E debería tener menor prioridad que B");
        assertTrue(pacienteE.compareTo(pacienteC) > 0,
                   "E debería tener menor prioridad que C");
        assertTrue(pacienteE.compareTo(pacienteD) > 0,
                   "E debería tener menor prioridad que D");
    }

    @Test
    @DisplayName("Test de compareTo - igualdad de prioridad")
    @Order(5)
    void testCompareToIgualPrioridad() {
        Paciente otroPacienteA = new Paciente("Otro nombre", "otro síntoma", 'A');
        assertEquals(0, pacienteA.compareTo(otroPacienteA),
                     "Dos pacientes con misma prioridad deberían ser iguales");
    }

    @Test
    @DisplayName("Test de toString")
    @Order(6)
    void testToString() {
        String expected = "Maria Ramirez, apendicitis, A";
        assertEquals(expected, pacienteA.toString());
        
        String expectedE = "Lorenzo Toledo, chikunguya, E";
        assertEquals(expectedE, pacienteE.toString());
    }

    @Test
    @DisplayName("Test de prioridades en cadena")
    @Order(7)
    void testCadenaPrioridades() {
        assertAll("Cadena de prioridades",
            () -> assertTrue(pacienteA.compareTo(pacienteB) < 0),
            () -> assertTrue(pacienteB.compareTo(pacienteC) < 0),
            () -> assertTrue(pacienteC.compareTo(pacienteD) < 0),
            () -> assertTrue(pacienteD.compareTo(pacienteE) < 0)
        );
    }
}