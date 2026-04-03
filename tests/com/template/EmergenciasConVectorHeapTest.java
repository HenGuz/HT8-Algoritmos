package com.template;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.nio.file.*;

public class EmergenciasConVectorHeapTest {

    private static final String ARCHIVO_PRUEBA = "test_pacientes.txt";
    private VectorHeap<Paciente> cola;

    @BeforeEach
    void setUp() throws IOException {
        cola = new VectorHeap<>();
        crearArchivoPrueba();
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(ARCHIVO_PRUEBA));
    }

    private void crearArchivoPrueba() throws IOException {
        String contenido = "Juan Perez, fractura de pierna, C\n" +
                          "Maria Ramirez, apendicitis, A\n" +
                          "Lorenzo Toledo, chikunguya, E\n" +
                          "Carmen Sarimientos, dolores de parto, B\n" +
                          "Ana Lopez, fiebre alta, D\n";
        
        Files.write(Paths.get(ARCHIVO_PRUEBA), contenido.getBytes());
    }

    @Test
    @DisplayName("Cargar pacientes desde archivo")
    void testCargarPacientes() {
        cargarPacientesDesdeArchivo(ARCHIVO_PRUEBA, cola);
        
        assertEquals(5, cola.size());
        assertEquals('A', cola.peek().getCodigoEmergencia());
    }

    @Test
    @DisplayName("Orden correcto de atención")
    void testOrdenAtencionCorrecto() {
        cargarPacientesDesdeArchivo(ARCHIVO_PRUEBA, cola);
        
        // Deberían salir en orden: A, B, C, D, E
        assertEquals('A', cola.remove().getCodigoEmergencia());
        assertEquals('B', cola.remove().getCodigoEmergencia());
        assertEquals('C', cola.remove().getCodigoEmergencia());
        assertEquals('D', cola.remove().getCodigoEmergencia());
        assertEquals('E', cola.remove().getCodigoEmergencia());
    }

    @Test
    @DisplayName("Archivo con líneas mal formadas no rompe el programa")
    void testArchivoMalFormado() throws IOException {
        String contenidoMal = "Juan Perez, fractura de pierna, C\n" +
                              "Linea mal formada sin comas\n" +
                              "Maria, apendicitis\n" +
                              "Lorenzo Toledo, chikunguya, E\n";
        
        Files.write(Paths.get(ARCHIVO_PRUEBA), contenidoMal.getBytes());
        
        // No debería lanzar excepción
        cargarPacientesDesdeArchivo(ARCHIVO_PRUEBA, cola);
        
        // Solo deberían cargarse las líneas válidas
        assertEquals(2, cola.size());
    }

    @Test
    @DisplayName("Archivo inexistente maneja excepción sin crashear")
    void testArchivoInexistente() {
        // No debería lanzar excepción
        cargarPacientesDesdeArchivo("archivo_que_no_existe.txt", cola);
        
        // La cola debe seguir vacía
        assertTrue(cola.isEmpty());
    }

    @Test
    @DisplayName("Códigos de emergencia inválidos se manejan correctamente")
    void testCodigosInvalidos() throws IOException {
        String contenidoInvalido = "Paciente1, sintoma1, Z\n" +
                                   "Paciente2, sintoma2, 1\n" +
                                   "Paciente3, sintoma3, A\n";
        
        Files.write(Paths.get(ARCHIVO_PRUEBA), contenidoInvalido.getBytes());
        
        cargarPacientesDesdeArchivo(ARCHIVO_PRUEBA, cola);
        
        // El código Z no es válido pero se carga igual (como caracter)
        // El programa no debe crashear
        assertEquals(3, cola.size());
    }

    private void cargarPacientesDesdeArchivo(String archivo, VectorHeap<Paciente> cola) {
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
