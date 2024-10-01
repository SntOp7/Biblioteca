package co.edu.uniquindio.poo;

public class Persona {
    private String nombre;
    private String cedula;
    private String telefono;
    private String correo;

    /**Metodo Constructor de Persona
     * @param nombre
     * @param cedula
     * @param telefono
     * @param correo
     */
    public Persona(String nombre, String cedula, String telefono, String correo) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correo = correo;
    }

    /**Metodo ToString
     * @return toString
     */
    public String toString(){
        return "Nombre: "+nombre+"\nCedula: "+cedula+"\nTelefono: "+telefono+"\nCorreo: "+correo;
    }

    /**Metodo Get
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**Metodo Set
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**Metodo Get
     * @return cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**Metodo Set
     * @param cedula
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**Metodo Get
     * @return telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**Metodo Set
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**Metodo Get
     * @return correo
     */
    public String getCorreo() {
        return correo;
    }

    /**Metodo Set
     * @param correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

}