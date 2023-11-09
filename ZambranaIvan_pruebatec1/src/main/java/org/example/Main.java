package org.example;

import org.example.logica.Empleado;
import org.example.logica.Menu;
import org.example.persistencia.ControladoraPersistencia;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Crear controladora de persistencia
        ControladoraPersistencia controladoraPersistencia = new ControladoraPersistencia();

        //Llamamos a la clase Menú para invocar el método mostrarMenu
        Menu menu = new Menu(controladoraPersistencia);
        menu.mostrarMenu();

    }
}