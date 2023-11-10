package org.example;

import org.example.logica.Menu;
import org.example.persistencia.ControladoraPersistencia;

public class Main {
    public static void main(String[] args) {
        //Crear controladora de persistencia
        ControladoraPersistencia controladoraPersistencia = new ControladoraPersistencia();

        //Llamamos a la clase Menú para invocar el método mostrarMenu
        Menu menu = new Menu(controladoraPersistencia);
        menu.mostrarMenu();

    }
}