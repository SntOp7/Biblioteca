package co.edu.uniquindio.poo;

import java.time.LocalDate;

public class Libro {
    private String codigo;
    private String autor;
    private String titulo;
    private String editorial;
    private LocalDate fecha;
    private int copias;
    
    /**Metodo Constructor de Libro
     * @param codigo
     * @param autor
     * @param titulo
     * @param editorial
     * @param fecha
     * @param copias
     */
    public Libro(String codigo, String autor, String titulo, String editorial, LocalDate fecha, int copias) {
        this.codigo = codigo;
        this.autor = autor;
        this.titulo = titulo;
        this.editorial = editorial;
        this.fecha = fecha;
        this.copias=copias;
    }

    /**Metodo para verificar unidades
     * @param unidades
     * @return existe
     */
    public boolean verificarUnidades(int unidades){
        boolean existe= false;
        if (getCopias()>=unidades){
            existe=true;
        }
        return existe;
    }

    /**Metodo para reducir unidades disponibles
     * @param unidades
     */
    public void reducirUnidadesDisponibles(int unidades){
        copias=copias-unidades;     
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
    /**Metodo Get
     * @return autor
     */
    public String getAutor() {
        return autor;
    }
    /**Metodo Set
     * @param autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }
    /**Metodo Get
     * @return titulo
     */
    public String getTitulo() {
        return titulo;
    }
    /**Metodo Set
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    /**Metodo Get
     * @return editorial
     */
    public String getEditorial() {
        return editorial;
    }
    /**Metodo Set
     * @param editorial
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    /**Metodo Get
     * @return fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }
    /**Metodo Set
     * @param fecha
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    /**Metodo Get
     * @return copias
     */
    public int getCopias(){
        return copias;
    }
    /**Metodo Set
     * @param copias
     */
    public void setCopias(int copias){
        this.copias=copias;
    }

    /**Metodo ToString
     * @return toString
     */
    @Override
    public String toString() {
        return "\n----------------------------\n"+"Codigo: " + codigo + "\nAutor: " + autor + "\nTitulo: " + titulo + "\nEditorial: " + editorial
                + "\nFecha: " + fecha +"\nCopias disponibles: "+copias+ "\n-------------------------------\n";
    }
}