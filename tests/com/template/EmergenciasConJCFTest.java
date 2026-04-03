package com.template;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.file.*;
import java.util.PriorityQueue;

public class EmergenciasConJCFTest {

    private static final String ARCHIVO_PRUEBA = "test_pacientes_jcf.txt";
    private PriorityQueue<Paciente> cola;

    @BeforeEach
    void setUp() throws IOException {
        cola = new PriorityQueue<>();
        crearArchivoPrueba();
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(ARCHIVO_PRUEBA));
    }

    private void crearArchivoPrueba() throws IOException {
        String contenido = "PacienteA, sintomaA, A\n" +
                          "PacienteC, sintomaC, C\n" +
                          "PacienteE, sintomaE, E\n" +
                          "PacienteB, sintomaB, B\n" +
                          "PacienteD, sintomaD, D\n";
        
        Files.write(Paths.get(ARCHIVO_PRUEBA), contenido.getBytes());
    }

    @Test
    @DisplayName("Cargar pacientes desde archivo con JCF")
    void testCargarPacientes() {
        cargarPacientesJCF(ARCHIVO_PRUEBA, cola);
        
        assertEquals(5, cola.size());
        assertEquals('A', cola.peek().getCodigoEmergencia());
    }

    @Test
    @DisplayName("JCF PriorityQueue mantiene orden correcto")
    void testOrdenCorrectoJCF() {
        cargarPacientesJCF(ARCHIVO_PRUEBA, cola);
        
        assertEquals('A', cola.poll().getCodigoEmergencia());
        assertEquals('B', cola.poll().getCodigoEmergencia());
        assertEquals('C', cola.poll().getCodigoEmergencia());
        assertEquals('D', cola.poll().getCodigoEmergencia());
        assertEquals('E', cola.poll().getCodigoEmergencia());
    }

    @Test
    @DisplayName("JCF con archivo vacío")
    void testArchivoVacioJCF() throws IOException {
        Files.write(Paths.get(ARCHIVO_PRUEBA), "".getBytes());
        
        cargarPacientesJCF(ARCHIVO_PRUEBA, cola);
        
        assertTrue(cola.isEmpty());
    }

    @Test
    @DisplayName("JCF con archivo que no existe")
    void testArchivoInexistenteJCF() {
        cargarPacientesJCF("no_existe.txt", cola);
        assertTrue(cola.isEmpty());
    }

    @Test
    @DisplayName("JCF con líneas mal formadas")
    void testLineasMalFormadasJCF() throws IOException {
        String contenidoMal = "valido1, sintoma, A\n" +
                              "invalido\n" +
                              "valido2, sintoma, B\n" +
                              "invalido, falta\n";
        
        Files.write(Paths.get(ARCHIVO_PRUEBA), contenidoMal.getBytes());
        
        cargarPacientesJCF(ARCHIVO_PRUEBA, cola);
        
        assertEquals(2, cola.size());
    }

    @Test
    @DisplayName("JCF poll en cola vacía retorna null")
    void testPollEnVacio() {
        assertNull(cola.poll());
        assertNull(cola.peek());
    }

    private void cargarPacientesJCF(String archivo, PriorityQueue<Paciente> cola) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    String sintoma = partes[1].trim();
                    char codigo = partes[2].trim().charAt(0);
                    Paciente p = new Paciente(nombre, sintoma, codigo);
                    cola.add(p);
                }
            }
        } catch (IOException e) {
        }
    }
}
