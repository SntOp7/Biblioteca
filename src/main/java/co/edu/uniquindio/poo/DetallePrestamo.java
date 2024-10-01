package co.edu.uniquindio.poo;

public class DetallePrestamo {
    private Libro libro;
    private double subTotal;
    private int cantidad;

    /**Constructor
     * @param libro
     * @param cantidad
     */
    public DetallePrestamo (Libro libro, int cantidad ){
        this.libro=libro;
        this.cantidad=cantidad;
        this.subTotal = 0;
    }  

    /**Calcular Subtotal
     * @param diasPrestamo
     * @param costoDia
     * @return subtotal
     */
    public double calcularSubTotal(double diasPrestamo, double costoDia) {
        double subTotal=cantidad*diasPrestamo*costoDia;
        return subTotal;
    }

    /**Metodo Get
     * @return libro
     */
    public Libro getLibro() {
        return libro;
    }

    /**Metodo set
     * @param libro
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**Metodo Get
     * @return cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**Metodo Set
     * @param cantidad
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**Metodo Get
     * @return subTotal
     */
    public double getSubTotal() {
        return subTotal;
    }

    /**Metodo set
     * @param subTotal
     */
    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    /**Metodo ToString
     * @return toString
     */
    @Override
    public String toString() {
        return "Libro: " + libro.getTitulo() + " Unidades: "+cantidad+ " Subtotal: "+subTotal;
    }
}