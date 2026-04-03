package com.template;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;

public class VectorHeapTest {

    private VectorHeap<Paciente> colaVacia;
    private VectorHeap<Paciente> colaConPacientes;
    private Paciente pacienteA;
    private Paciente pacienteB;
    private Paciente pacienteC;
    private Paciente pacienteD;
    private Paciente pacienteE;

    @BeforeEach
    void setUp() {
        colaVacia = new VectorHeap<>();
        colaConPacientes = new VectorHeap<>();
        
        pacienteA = new Paciente("Maria Ramirez", "apendicitis", 'A');
        pacienteB = new Paciente("Carmen Sarimientos", "dolores de parto", 'B');
        pacienteC = new Paciente("Juan Perez", "fractura de pierna", 'C');
        pacienteD = new Paciente("Luis Gonzalez", "gripe", 'D');
        pacienteE = new Paciente("Lorenzo Toledo", "chikunguya", 'E');
        
        colaConPacientes.add(pacienteC);
        colaConPacientes.add(pacienteA);
        colaConPacientes.add(pacienteE);
        colaConPacientes.add(pacienteB);
        colaConPacientes.add(pacienteD);
    }

    @Test
    @DisplayName("Test de agregar y tamaño")
    @Order(1)
    void testAddYSize() {
        assertEquals(0, colaVacia.size());
        colaVacia.add(pacienteA);
        assertEquals(1, colaVacia.size());
        colaVacia.add(pacienteB);
        assertEquals(2, colaVacia.size());
        colaVacia.add(pacienteC);
        assertEquals(3, colaVacia.size());
    }

    @Test
    @DisplayName("Test de remove en orden de prioridad")
    @Order(2)
    void testRemoveEnOrdenPrioridad() {
        assertEquals(5, colaConPacientes.size());
        
        // Deberían salir en orden: A, B, C, D, E
        assertEquals(pacienteA, colaConPacientes.remove());
        assertEquals(pacienteB, colaConPacientes.remove());
        assertEquals(pacienteC, colaConPacientes.remove());
        assertEquals(pacienteD, colaConPacientes.remove());
        assertEquals(pacienteE, colaConPacientes.remove());
        
        assertTrue(colaConPacientes.isEmpty());
    }

    @Test
    @DisplayName("Test de isEmpty")
    @Order(3)
    void testIsEmpty() {
        assertTrue(colaVacia.isEmpty());
        assertFalse(colaConPacientes.isEmpty());
        
        colaConPacientes.remove();
        colaConPacientes.remove();
        colaConPacientes.remove();
        colaConPacientes.remove();
        colaConPacientes.remove();
        
        assertTrue(colaConPacientes.isEmpty());
    }

    @Test
    @DisplayName("Test de peek sin eliminar")
    @Order(4)
    void testPeek() {
        assertEquals(pacienteA, colaConPacientes.peek());
        assertEquals(5, colaConPacientes.size()); // Sigue teniendo 5 elementos
        
        colaConPacientes.remove();
        assertEquals(pacienteB, colaConPacientes.peek());
        assertEquals(4, colaConPacientes.size());
    }

    @Test
    @DisplayName("Test de peek en cola vacía")
    @Order(5)
    void testPeekEnColaVacia() {
        assertNull(colaVacia.peek());
    }

    @Test
    @DisplayName("Test de remove en cola vacía lanza excepción")
    @Order(6)
    void testRemoveEnColaVaciaLanzaExcepcion() {
        assertThrows(IllegalStateException.class, () -> colaVacia.remove(),
                    "remove() en cola vacía debería lanzar IllegalStateException");
    }

    @Test
    @DisplayName("Test de constructor con Vector inicial")
    @Order(7)
    void testConstructorConVectorInicial() {
        Vector<Paciente> vector = new Vector<>();
        vector.add(pacienteC);
        vector.add(pacienteA);
        vector.add(pacienteB);
        
        VectorHeap<Paciente> heapDesdeVector = new VectorHeap<>(vector);
        
        // Debería estar ordenado como heap
        assertEquals(pacienteA, heapDesdeVector.peek());
        assertEquals(3, heapDesdeVector.size());
    }

    @Test
    @DisplayName("Test de inserción y extracción múltiple")
    @Order(8)
    void testInsercionYExtraccionMultiple() {
        VectorHeap<Paciente> cola = new VectorHeap<>();
        
        // Insertar en desorden
        cola.add(pacienteE);
        cola.add(pacienteC);
        cola.add(pacienteA);
        cola.add(pacienteD);
        cola.add(pacienteB);
        
        // Extraer en orden
        assertEquals(pacienteA, cola.remove());
        assertEquals(pacienteB, cola.remove());
        assertEquals(pacienteC, cola.remove());
        assertEquals(pacienteD, cola.remove());
        assertEquals(pacienteE, cola.remove());
    }

    @Test
    @DisplayName("Test de percolate up con prioridades iguales")
    @Order(9)
    void testPercolateUpConPrioridadesIguales() {
        VectorHeap<Paciente> cola = new VectorHeap<>();
        Paciente pacienteA2 = new Paciente("Ana Lopez", "fiebre", 'A');
        
        cola.add(pacienteA);
        cola.add(pacienteA2);
        
        // Ambos tienen misma prioridad, el orden no importa
        assertNotNull(cola.remove());
        assertNotNull(cola.remove());
        assertTrue(cola.isEmpty());
    }

    @Test
    @DisplayName("Test de que peek no modifica la estructura")
    @Order(10)
    void testPeekNoModificaEstructura() {
        Paciente primero = colaConPacientes.peek();
        Paciente segundo = colaConPacientes.peek();
        
        assertSame(primero, segundo, "peek() debería retornar el mismo objeto");
        assertEquals(5, colaConPacientes.size(), "El tamaño no debería cambiar");
    }
}