package com.template;

/**
 * Clase que representa a un paciente para ser ingresado al sistema.
 * @author Henry Guzmán
 * @version 2.0
 * @since 2026-04-02
 */

public class Paciente implements Comparable<Paciente> {
	/**
	 * Atributo que representa al nombre del paciente.
	 */
    private String nombre;
    
    /**
     * Atributo que representa la enfermedad o sintoma del paciente.
     */
    private String sintoma;
    
    /**
     * Atributo que indica la gravedad del paciente (se representa por letras).
     * A indica la prioridad más alta.
     * E indica la prioridad más baja.
     */
    private char codigoEmergencia;

    /**
     * Constructor principal de la clase.
     * @param nombre Nombre completo del paciente.
     * @param sintoma Síntoma del paciente.
     * @param codigoEmergencia Letra (A a la E) que indica prioridad (A más urgente y E menos urgente).
     */
    public Paciente(String nombre, String sintoma, char codigoEmergencia) {
        this.nombre = nombre;
        this.sintoma = sintoma;
        this.codigoEmergencia = codigoEmergencia;
    }

    /**
     * Método para retornar el nombre del paciente.
     * @return Nombre del paciente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para retornar el síntoma del paciente.
     * @return Síntoma o enfermedad del paciente.
     */
    public String getSintoma() {
        return sintoma;
    }

    /**
     * Método para retornar el código de prioridad del paciente.
     * @return Letra que indica la prioridad del paciente (A a la E).
     */
    public char getCodigoEmergencia() {
        return codigoEmergencia;
    }

    /**
     * Método para comparar la letra de prioridad entre dos pacientes. Y ver a quién es más urgente atender.
     * @param otro Paciente con el que se hara la comparación.
     * @return Un valor númerico que indica cuál de ambos pacientes tiene prioridad.
     */
    @Override
    public int compareTo(Paciente otro) {
        return Character.compare(this.codigoEmergencia, otro.codigoEmergencia);
    }

    /**
     * Método para imprimir información de un paciente.
     * @return Cadena con el mensaje que contiene la información del paciente para que sea visible al usuario.
     */
    @Override
    public String toString() {
        return nombre + ", " + sintoma + ", " + codigoEmergencia;
    }
}