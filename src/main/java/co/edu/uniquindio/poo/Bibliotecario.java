package co.edu.uniquindio.poo;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Bibliotecario extends Persona {
    private double salario;
    private int antiguedad;
    private Collection<Prestamo> prestamos;

    /**Constructor de Bibliotecario
     * @param nombre
     * @param cedula
     * @param telefono
     * @param correo
     * @param salario
     * @param antiguedad
     */
    public Bibliotecario(String nombre, String cedula, String telefono, String correo, double salario, int antiguedad) {
        super(nombre, cedula, telefono, correo);
        this.salario = 0;
        this.antiguedad = antiguedad;
        prestamos = new LinkedList<>();
    }

    
    /**Metodo para calcular numero de prestamos
     * @return numeroPrestamos
     */
    public int  numeroPrestamosBibliotecario(){
        int numeroPrestamos=prestamos.size();
        return numeroPrestamos;

    }

    /**Metodo para registrar el prestamo
     * @param prestamo
     */
    public void registrarPrestamoBibliotecario(Prestamo prestamo){
        prestamos.add(prestamo);
    }

    /**Metodo para calcular el sueldo
     * @return Sueldo base + bonificaciones
     */
    public double calcularSueldo(){
        double porcentajePrestamo = 0.20;
        double bonificacionAntiguedad = 0.02 * antiguedad;
        double sueldoPrestamo= 0;

        for (Prestamo prestamo : prestamos){
            double subtotal = prestamo.calcularTotal();
            sueldoPrestamo += (subtotal * porcentajePrestamo);
        }

        double bonificacion = sueldoPrestamo * bonificacionAntiguedad;

        return sueldoPrestamo + bonificacion;
    }

    /**Metodo Get
     * @return antiguedad
     */
    public int getAntiguedad() {
        return antiguedad;
    }

    /**Metodo Set
     * @param antiguedad
     */
    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    /**Metodo Get
     * @return prestamos
     */
    public Collection<Prestamo> getPrestamos() {
        return prestamos;
    }

    /**Metodo Get
     * @return salario
     */
    public double getSalario() {
        return salario;
    }

    /**Metodo Set
     * @param salario
     */
    public void setSalario(double salario){
        this.salario=calcularSueldo();
    }

    /**Metodo ToString
     * @return toString
     */
    @Override
    public String toString() {
        return super.toString()+"\nSalario:" + calcularSueldo() + "\nAntiguedad: " + antiguedad;
    }

    /**Metodo retrun ToString
     * @return super.toString
     */
    public String toStringBasico(){
        return super.toString();
    }
}