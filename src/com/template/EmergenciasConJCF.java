package com.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class EmergenciasConJCF {
    public static void main(String[] args) {
        PriorityQueue<Paciente> colaPacientes = new PriorityQueue<>();
        cargarPacientes("pacientes.txt", colaPacientes);

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 3) {
            System.out.println("\n--- SISTEMA DE EMERGENCIAS (JCF PriorityQueue) ---");
            System.out.println("1. Ver siguiente paciente");
            System.out.println("2. Atender siguiente paciente");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número.");
                continue;
            }

            switch (opcion) {
                case 1:
                    Paciente siguiente = colaPacientes.peek();
                    if (siguiente != null) {
                        System.out.println("Siguiente paciente: " + siguiente);
                    } else {
                        System.out.println("No hay pacientes en espera.");
                    }
                    break;
                case 2:
                    Paciente atendido = colaPacientes.poll();
                    if (atendido != null) {
                        System.out.println("Atendiendo a: " + atendido);
                    } else {
                        System.out.println("No hay pacientes en espera.");
                    }
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }

    private static void cargarPacientes(String archivo, PriorityQueue<Paciente> cola) {
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
                } else {
                    System.err.println("Línea mal formada: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
