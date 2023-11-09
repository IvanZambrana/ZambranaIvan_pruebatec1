package org.example.persistencia;

import org.example.logica.Empleado;

import java.util.List;

public class ControladoraPersistencia {
    EmpleadoJpaController empleadoJpa = new EmpleadoJpaController();

    public void crearEmpleado(Empleado empleado) {
        empleadoJpa.create(empleado);
    }

    public void borrarEmpleado(Long id) {
        empleadoJpa.destroy(id);
    }

    public void editarEmpleado(Empleado empleado) {
        empleadoJpa.edit(empleado);
    }

    public Empleado obtenerEmpleado(Long id) {
        return empleadoJpa.findEmpleado(id);
    }

    public List<Empleado> listarEmpleados() {
        return empleadoJpa.findEmpleadoEntities();
    }

    public List<Empleado> buscarPorCargo(String cargo) {
        return empleadoJpa.searchByCharge(cargo);
    }
}
