package org.example.logica;

import org.example.persistencia.ControladoraPersistencia;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {
    //Inicializamos ControladoraPersistencia y el Scanner
    private ControladoraPersistencia controladoraPersistencia;
    private Scanner sc;

    //Constructor
    public Menu(ControladoraPersistencia controladoraPersistencia) {
        this.controladoraPersistencia = controladoraPersistencia;
        this.sc = new Scanner(System.in);
    }

    //Método para desplegar el menú
    public void mostrarMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n\n\n----------------------------------------");
            System.out.println("------MENU GESTIÓN DE EMPLEADOS------");
            System.out.println("----------------------------------------");
            System.out.println("1. Agregar un nuevo empleado.");
            System.out.println("2. Listar empleados.");
            System.out.println("3. Actualizar información de un empleado.");
            System.out.println("4. Eliminar un empleado.");
            System.out.println("5. Buscar empleado por cargo.");
            System.out.println("6. Salir");

            int opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    agregarEmpleado();
                    break;
                case 2:
                    listarEmpleados();
                    break;
                case 3:
                    actualizarEmpleado();
                    break;
                case 4:
                    eliminarEmpleado();
                    break;
                case 5:
                    buscarPorCargo();
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    exit = true;
                    break;
                default:
                    System.out.println("Por favor, elija un número del 1 al 6");
            }
        }
    }

    //Método para gestionar la opción del menú
    private int obtenerOpcion() {
        int opcion = 0;
        boolean opcionValida = false;

        while (!opcionValida) {
            System.out.println("Elija una opción: ");

            try {
                opcion = sc.nextInt();
                opcionValida = opcion >= 1 && opcion <= 6;
            } catch (Exception e) {
                System.out.println("Por favor, ingrese un número válido.");
                sc.next();
            }
        }

        return opcion;
    }

    // Métodos para la gestión de empleados
    private void agregarEmpleado() {
        //AGREGAR EMPLEADO
        System.out.println("--------------------");
        System.out.println("--AGREGAR EMPLEADO--");
        System.out.println("--------------------");
        sc.nextLine(); // Limpia el búfer
        //Pedir datos del nuevo empleado al usuario
        System.out.println("Ingrese nombre del nuevo empleado:");
        String nombreEmpleado = sc.nextLine();
        System.out.println("Ingrese apellido del nuevo empleado:");
        String apellidoEmpleado = sc.nextLine();
        System.out.println("Ingrese cargo del nuevo empleado:");
        String cargoEmpleado = sc.nextLine();
        System.out.println("Ingrese salario del nuevo empleado:");
        double salarioEmpleado = sc.nextDouble();
        System.out.println("Ingrese año de inicio: ");
        int year = sc.nextInt();
        System.out.println("Ingrese mes de inicio (1-12): ");
        int month = sc.nextInt();
        //Evitamos que el usuario no añada un número de mes inválido
        if (month < 1 || month > 12) {
            System.out.println("Mes no válido. Debe estar en el rango 1-12.");
            return;
        }
        System.out.println("Ingrese día de inicio (1-31): ");
        int day = sc.nextInt();
        //Evitamos que el usuario no añada un número de día inválido
        if (day < 1 || day > 31) {
            System.out.println("Día no válido. Debe estar en el rango 1-31.");
            return;
        }
        //Crear un objeto clase Empleado
        Empleado nuevoEmpleado = new Empleado(nombreEmpleado, apellidoEmpleado, cargoEmpleado, salarioEmpleado, new Date(year - 1900, month, day));

        //Persistir el objeto creado (ALTA)
        controladoraPersistencia.crearEmpleado(nuevoEmpleado);
        System.out.println("Empleado agregado con éxito: ");
        System.out.println("ID: " + nuevoEmpleado.getId() +
                " \tNombre: " + nuevoEmpleado.getNombre() +
                " \tApellidos: " + nuevoEmpleado.getApellido() +
                " \tCargo: " + nuevoEmpleado.getCargo() +
                " \tSalario: " + nuevoEmpleado.getSalario() +
                " \tFecha de inicio: " + nuevoEmpleado.getFechaInicio()

        );
    }

    private void listarEmpleados() {
        //LISTAR EMPLEADOS
        System.out.println("----------------------");
        System.out.println("--LISTA DE EMPLEADOS--");
        System.out.println("----------------------");

        //Crear una lista empleados
        List<Empleado> listEmpleados = controladoraPersistencia.listarEmpleados();

        //Recorrer la lista mostrando los datos de cada empleado
        for (Empleado empleado : listEmpleados) {
            System.out.println("ID: " + empleado.getId() +
                    " \tNombre: " + empleado.getNombre() +
                    " \tApellidos: " + empleado.getApellido() +
                    " \tCargo: " + empleado.getCargo() +
                    " \tSalario: " + empleado.getSalario() +
                    " \tFecha de inicio: " + empleado.getFechaInicio()

            );
        }
    }

    private void actualizarEmpleado() {
        //ACTUALIZAR INFO DE UN EMPLEADO
        System.out.println("----------------------");
        System.out.println("--MODIFICAR EMPLEADO--");
        System.out.println("----------------------");

        //Pedir ID del empleado a modificar al usuario
        System.out.println("Seleccione ID del empleado que desea modificar:");
        Long idEmpleadoModificar = sc.nextLong();

        // Buscar el empleado seleccionado por ID
        Empleado empleadoSeleccionado = controladoraPersistencia.obtenerEmpleado(idEmpleadoModificar);

        //Comprobar que exista el empleado
        if (empleadoSeleccionado != null) {
            System.out.println("Empleado seleccionado: " + empleadoSeleccionado.getNombre() + " " + empleadoSeleccionado.getApellido());

            // Menú para elegir el atributo a modificar
            System.out.println("Seleccione el atributo a modificar:");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Cargo");
            System.out.println("4. Salario");
            System.out.println("5. Fecha de Inicio");
            int opcionAtributo = sc.nextInt();

            // Dependiendo de la opción seleccionada, solicitar al usuario los nuevos datos
            switch (opcionAtributo) {
                case 1:
                    System.out.println("Ingrese el nuevo nombre del empleado:");
                    sc.nextLine(); // Limpia el búfer
                    String nombreEmpleado = sc.nextLine();
                    empleadoSeleccionado.setNombre(nombreEmpleado);
                    break;
                case 2:
                    System.out.println("Ingrese el nuevo apellido del empleado:");
                    sc.nextLine(); // Limpia el búfer
                    String apellidoEmpleado = sc.nextLine();
                    empleadoSeleccionado.setApellido(apellidoEmpleado);
                    break;
                case 3:
                    System.out.println("Ingrese el nuevo cargo del empleado:");
                    sc.nextLine(); // Limpia el búfer
                    String cargoEmpleado = sc.nextLine();
                    empleadoSeleccionado.setCargo(cargoEmpleado);
                    break;
                case 4:
                    System.out.println("Ingrese el nuevo salario del empleado:");
                    double salarioEmpleado = sc.nextDouble();
                    empleadoSeleccionado.setSalario(salarioEmpleado);
                    break;
                case 5:
                    System.out.println("Ingrese el nuevo año de inicio: ");
                    int year = sc.nextInt();
                    System.out.println("Ingrese el nuevo mes de inicio (1-12): ");
                    int month = sc.nextInt();
                    System.out.println("Ingrese el nuevo día de inicio (1-31): ");
                    int day = sc.nextInt();
                    empleadoSeleccionado.setFechaInicio(new Date(year - 1900, month, day));
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

            // Persistir la actualización (MODIFICACIÓN)
            controladoraPersistencia.editarEmpleado(empleadoSeleccionado);

            System.out.println("Empleado modificado con éxito.");
        } else {
            System.out.println("No se encontró un empleado con el ID proporcionado.");
        }
    }

    private void eliminarEmpleado() {
        // ELIMINAR EMPLEADO
        System.out.println("----------------------");
        System.out.println("-- ELIMINAR EMPLEADO --");
        System.out.println("----------------------");

        //Pedir al usuario el ID del empleado a eliminar
        System.out.println("Seleccione el ID del empleado que desea eliminar:");
        Long idEmpleadoEliminar = sc.nextLong();

        // Buscar el empleado seleccionado por ID
        Empleado empleadoEliminar = controladoraPersistencia.obtenerEmpleado(idEmpleadoEliminar);

        if (empleadoEliminar != null) {
            System.out.println("Empleado seleccionado: " + empleadoEliminar.getNombre() + " " + empleadoEliminar.getApellido());

            // Confirmar la eliminación con el usuario
            System.out.println("¿Está seguro de que desea eliminar este empleado? (S/N):");
            sc.nextLine(); // Limpia el búfer
            String confirmacion = sc.nextLine().trim();

            if (confirmacion.equalsIgnoreCase("S")) {
                // Eliminar el empleado seleccionado
                controladoraPersistencia.borrarEmpleado(idEmpleadoEliminar);
                System.out.println("Empleado eliminado con éxito.");
            } else {
                System.out.println("La eliminación ha sido cancelada.");
            }
        } else {
            System.out.println("No se encontró un empleado con el ID proporcionado.");
        }
    }

    private void buscarPorCargo() {
        // BUSCAR EMPLEADO POR CARGO
        System.out.println("----------------------");
        System.out.println("-- BUSCAR EMPLEADOS POR CARGO --");
        System.out.println("----------------------");

        // Pedir al usuario el cargo a buscar
        System.out.println("Ingrese el cargo que desea buscar:");
        sc.nextLine(); // Limpia el búfer
        String cargoBuscado = sc.nextLine().trim();

        // Realizar la búsqueda de empleados por cargo
        List<Empleado> empleadosPorCargo = controladoraPersistencia.buscarPorCargo(cargoBuscado);

        if (!empleadosPorCargo.isEmpty()) {
            System.out.println("Empleados con cargo '" + cargoBuscado + "':");
            for (Empleado empleado : empleadosPorCargo) {
                System.out.println("ID: " + empleado.getId() +
                        " \tNombre: " + empleado.getNombre() +
                        " \tApellidos: " + empleado.getApellido() +
                        " \tCargo: " + empleado.getCargo() +
                        " \tSalario: " + empleado.getSalario() +
                        " \tFecha de inicio: " + empleado.getFechaInicio());
            }
        } else {
            System.out.println("No se encontraron empleados con el cargo especificado.");
        }
    }
}