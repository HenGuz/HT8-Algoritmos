package com.template;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PriorityQueueTest {

    private PriorityQueue<Paciente> cola;

    @BeforeEach
    void setUp() {
        cola = new VectorHeap<>();
    }

    @Test
    @DisplayName("Una cola nueva debe estar vacía")
    void testNuevaColaVacia() {
        assertTrue(cola.isEmpty());
        assertEquals(0, cola.size());
        assertNull(cola.peek());
    }

    @Test
    @DisplayName("Después de agregar, la cola no está vacía")
    void testAgregarNoVacia() {
        Paciente p = new Paciente("Test", "síntoma", 'A');
        cola.add(p);
        
        assertFalse(cola.isEmpty());
        assertEquals(1, cola.size());
        assertNotNull(cola.peek());
    }

    @Test
    @DisplayName("remove debe lanzar excepción si la cola está vacía")
    void testRemoveEnVacioLanzaExcepcion() {
        assertThrows(IllegalStateException.class, () -> cola.remove());
    }

    @Test
    @DisplayName("peek en cola vacía debe retornar null")
    void testPeekEnVacioRetornaNull() {
        assertNull(cola.peek());
    }

    @Test
    @DisplayName("Agregar múltiples elementos mantiene orden de prioridad")
    void testOrdenPrioridad() {
        Paciente pA = new Paciente("A", "a", 'A');
        Paciente pC = new Paciente("C", "c", 'C');
        Paciente pB = new Paciente("B", "b", 'B');
        
        cola.add(pC);
        cola.add(pA);
        cola.add(pB);
        
        assertEquals(pA, cola.peek());
        assertEquals(pA, cola.remove());
        assertEquals(pB, cola.remove());
        assertEquals(pC, cola.remove());
        assertTrue(cola.isEmpty());
    }
}
