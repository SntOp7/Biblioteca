package co.edu.uniquindio.poo;

import java.util.Collection;
import java.util.LinkedList;

public class Estudiante extends Persona {

    private int edad;
     Collection<Prestamo> prestamos;

    /**Metodo Constructor
     * @param nombre
     * @param cedula
     * @param telefono
     * @param correo
     * @param edad
     */
    public Estudiante(String nombre, String cedula, String telefono, String correo, int edad) {
        super(nombre, cedula, telefono, correo); // Super llama al constructor de la clase Persona
        this.edad = edad;
        prestamos = new LinkedList<>();
    }

    /**Metodo Seleccionar Prestamo
     * @param codigo
     * @return loan
     */
    public Prestamo seleccionarPrestamoEstudiante(String codigo) {
        Prestamo loan = null;
            for (Prestamo prestamo : prestamos) {
                if (prestamo.getCodigo().equals(codigo)) {
                    loan = prestamo;
                    break;
                }
            }
        return loan;
    }

    /**Metodo Registrar Prestamo
     * @param prestamo
     */
    public void registrarPrestamoEstudiante(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    /**Metodo Verificar Prestamos
     * @return existen
     */
    public boolean verificarPrestamos(){
        boolean existen= false;
        if (prestamos.size()>0){
            existen=true;
        } 
        return existen;

    }

    /**Metodo Mostrar Prestamos
     * 
     */
    public void mostrarPrestamosEstudiante() {
        for (Prestamo prestamo : prestamos) {
            System.out.println(prestamo.toStringEstudiante());
        }
    }

    /**Metodo Cadena Prestamos
     * @return loans
     */
    public String cadenaPrestamosEstudiante() {
        String loans = "";
        if (prestamos.isEmpty()) {
            loans = "El estudiante no tiene prestamos";
        } else {
            for (Prestamo prestamo : prestamos) {
                loans += (prestamo.toStringEstudiante()) + "\n";
            }
        }
        return loans;
    }
    
    /**Metodo Numero de prestamos
     * @return numeroPrestamos
     */
    public int numeroPrestamos(){
        int numeroPrestamos=prestamos.size();
        return numeroPrestamos;
    }

    /**Metodo para eliminar un prestamo
     * @param prestamo
     */
    public void eliminarPrestamo(Prestamo prestamo){
        prestamos.remove(prestamo);
    }

    /**Metodo Get
     * @return edad
     */
    public int getEdad() {
        return edad;
    }

    /**Metodo Set
     * @param edad
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**Metodo Get
     * @return prestamos
     */
    public Collection<Prestamo> getPrestamos() {
        return prestamos;
    }

    /**Metodo Set
     * @param prestamos
     */
    public void setPrestamos(Collection<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    /**Metodo ToString
     * @param prestamos
     */
    @Override
    public String toString() {
        return super.toString() + "Edad: " + edad + "\nPrestamos: \n" + cadenaPrestamosEstudiante();
    }

    /**Metodo return toString
     * @return super.toString();
     */
    public String toStringBasico(){
        return super.toString();
    }
}