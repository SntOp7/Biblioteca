package co.edu.uniquindio.poo;

import java.util.LinkedList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prestamo {

    private String codigo;
    private Estudiante estudiante;
    private Bibliotecario bibliotecario;
    private LinkedList<DetallePrestamo> detalles;
    private LocalDate fechaPrestamo, fechaEntrega;
    private double costoDia;

    /**Metodo Constructor
     * @param codigo
     * @param estudiante
     * @param bibliotecario
     * @param costoDia
     * @param fechaPrestamo
     * @param fechaEntrega
     */
    public Prestamo(String codigo, Estudiante estudiante, Bibliotecario bibliotecario, double costoDia,
            LocalDate fechaPrestamo, LocalDate fechaEntrega) {
        this.fechaPrestamo = fechaPrestamo;
        this.fechaEntrega = fechaEntrega;
        this.costoDia = costoDia;
        this.codigo = codigo;
        this.estudiante = estudiante;
        this.bibliotecario = bibliotecario;
        detalles = new LinkedList<>();
    }

    /**Metodo para Crear detalle Prestamo
     * @param libro
     * @param cantidad
     * @param costoDia
     * @return boolean
     */
    public boolean crearDetallePrestamo(Libro libro, int cantidad, double costoDia) {

        if (libro.verificarUnidades(cantidad)) {

            long diasPrestamo = calcularDias();
            // Crear el DetallePrestamo
            DetallePrestamo detalle = new DetallePrestamo(libro, cantidad);

            double subTotal = detalle.calcularSubTotal(diasPrestamo, costoDia);
            detalle.setSubTotal(subTotal);

            // Aqui se agrega el DetallePrestamo a la lista de detalles
            agregarDetalle(detalle);

            // Tambien hay que hacer el metodo para reducir la cantidad de libro,
            libro.reducirUnidadesDisponibles(cantidad);

            return true;
        } else {
            return false;
        }

    }

    /**Metodo para calcular los dias del prestamo
     * @return dias
     */
    public long calcularDias() {
        long dias = ChronoUnit.DAYS.between(fechaPrestamo, fechaEntrega);
        return dias;
    }

    /**Metodo para calcular elttal
     * @return total
     */
    public double calcularTotal() {
        double total = 0;
        for (DetallePrestamo detalle : detalles) {
            total += detalle.getSubTotal();
        }
        return total;
    }

    /**Metodo para agregar detalles
     * @param detalle
     */
    public void agregarDetalle(DetallePrestamo detalle) {
        detalles.add(detalle);
    }

    /**Metodo cadenaDetalles
     * @return detalles
     */
    public String cadenaDetalles() {
        String details = "";
        if (detalles.isEmpty()) {
            details = "El prestamo no tiene libros agregados";
        } else {
            for (DetallePrestamo detalle : detalles) {
                details += detalle.toString() + "\n";
            }
        }
        return details;
    }

    /**Metodo ToString
     * @return toString
     */
    @Override
    public String toString() {
        return "Codigo: " + codigo  + "\n--------------------\n" + "Bibliotecario encargado: \n"
                + bibliotecario.toStringBasico() + "\n--------------------" + "\nEstudiante: \n"
                + estudiante.toStringBasico() + "\n--------------------\n" + "Fecha de prestamo: " + fechaPrestamo
                + "\nFecha de Entrega: " +
                fechaEntrega + "\n--------------------" + "\nItems: \n" + cadenaDetalles()
                + "\nCosto por dia: " + costoDia;
    }

    /**Metodo ToString
     * @return toString
     */
    public String toStringEstudiante() {
        return "Codigo: " + codigo + "\nBibliotecario encargado: " + bibliotecario.getNombre() + "\nFecha de prestamo: "
                + fechaPrestamo + "\nFecha de Entrega: " +
                fechaEntrega + "\nLibros: " + cadenaDetalles();
    }

    /**Metodo ToString
     * @return toString
     */
    public String toStringEntrega() {
        return "Codigo: " + codigo + "\nBibliotecario encargado: " + bibliotecario.getNombre() + "\nFecha de prestamo: "
                + fechaPrestamo + "\nFecha de Entrega: " +
                fechaEntrega + "\nLibro: " + cadenaDetalles() + "\nCosto por dia: "+costoDia+"\nTotal: "+calcularTotal();
    }

    /**Metodo Get
     * @return detalles
     */
    public LinkedList<DetallePrestamo> getDetalles() {
        return detalles;
    }

    /**Metodo Set
     * @param detalles
     */
    public void setDetalles(LinkedList<DetallePrestamo> detalles) {
        this.detalles = detalles;
    }

    /**Metodo Get
     * @return fechaPrestamo
     */
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    /**Metodo Set
     * @param fechaPrestamo
     */
    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    /**Metodo Get
     * @return fechaEntrega
     */
    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    /**Metodo Set
     * @param fechaEntrega
     */
    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    /**Metodo Get
     * @return costoDia
     */
    public double getCostoDia() {
        return costoDia;
    }

    /**Metodo Set
     * @param costoDia
     */
    public void setCostoDia(double costoDia) {
        this.costoDia = costoDia;
    }

    /**Metodo Get
     * @return estudiante
     */
    public Estudiante getEstudiante() {
        return estudiante;
    }

    /**Metodo Set
     * @param estudiante
     */
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    /**Metodo Get
     * @return bibliotecario
     */
    public Bibliotecario getBibliotecario() {
        return bibliotecario;
    }

    /**Metodo Set
     * @param bibliotecario
     */
    public void setBibliotecario(Bibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
    }

    /**Metodo Get
     * @return codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**Metodo Set
     * @param codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}