package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {
    private String nombre;
    private LinkedList<Estudiante> estudiantes;
    private LinkedList<Bibliotecario> bibliotecarios;
    private LinkedList<Prestamo> prestamos;
    private LinkedList<Libro> libros;
    private double ganancia;
    private int cantidadLibros;

    /**
     * Metodo Constructor de la clase biblioteca
     * 
     * @param nombre
     */
    public Biblioteca(String nombre) {
        this.nombre = "Virgilio Barco";
        this.ganancia = 0;
        estudiantes = new LinkedList<>();
        bibliotecarios = new LinkedList<>();
        prestamos = new LinkedList<>();
        libros = new LinkedList<>();

    }


    
    public void menu() {
        imprimir("------ BIBLIOTECA VIRGILIO BARCO ------");

        imprimir("1. Acceso a Estudiantes.");
        imprimir("2. Acceso a Bibliotecarios.");
        imprimir("3. Acceso a Libros.");
        imprimir("4. Acceso a Prestamos.");
        imprimir("5.Consulta biblioteca.");
        imprimir("6. Salir");
        int num = ingresarEntero("¿Qué quieres consultar?:");
        switch (num) {
            case 1:
                submenu(num);
                break;
            case 2:
                submenu(num);
                break;
            case 3:
                submenu(num);
                break;
            case 4:
                submenu(num);
                break;
            case 5:
                submenu(num);
                break;
            case 6:
                imprimir("Saliendo del Aplicativo...");
                System.exit(0);
                break;
            default:
                imprimir("La opción ingresada es incorrecta.");
                menu();
                break;
        }

    }
/**
 * Metodo auxiliar que controla la recursividad de los métodos principales
 * @param num
 */
    public void submenu(int num) {
        switch (num) {
            case 1:
                operacionEstudiante();
                break;
            case 2:
                operacionBibliotecario();
                break;
            case 3:
                operacionLibro();
                break;
            case 4:
                operacionPrestamo();
                break;
            case 5:
                operacionConsultaBiblioteca();
            default:
                break;
        }
    }

    

    /**
     * Metodo que regula todas las acciones referentes a la información de la biblioteca
     */
    public void operacionConsultaBiblioteca() {
        imprimir("------Consulta Biblioteca------");
        imprimir("1.Consultar numero de libros en el inventario.");
        imprimir("2.Calcular total ganancias.");
        imprimir("3.Calcular nomina.");
        imprimir("4.Regresar.");
        int opcion = ingresarEntero("Ingrese una opcion");
        switch (opcion) {
            case 1:
                librosBiblioteca();
                break;
            case 2:
                calcularTotalGanancias();
                break;
            case 3:
                calcularTotalPagos();
                break;
            case 4:
                menu();
                break;
            default:
                imprimir("Opcion invalida");
                operacionConsultaBiblioteca();
        }

    }

    /**
     * Metodo que muestra en la consola el total de títulos y copias que hay en la biblioteca 
    */
    public void librosBiblioteca() {
        int numLibros = libros.size();
        int copiasBiblioteca = copiasBiblioteca();
        imprimir("El total de títulos en el inventario es: " + numLibros);
        imprimir("El total de copias en el inventario es: " + copiasBiblioteca);
        submenu(5);
    }

    /**
     * Metodo que cuenta el total de copias disponibles que hay en la biblioteca.
     * @return El numero de copias de libros que hay en la biblioteca.
     */
    public int copiasBiblioteca() {
        int copiasBiblioteca = 0;
        for (Libro libro : libros) {
            copiasBiblioteca += libro.getCopias();
        }
        setCantidadLibros(copiasBiblioteca);
        return copiasBiblioteca;
    }

    /**
     * Metodo que imprime en la consola el total de las ganancias de la biblioteca.
     */
    public void calcularTotalGanancias() {
        imprimir("------Ganancias------");
        double ingresos = 0;
        for (Prestamo prestamo : prestamos) {
            ingresos += prestamo.calcularTotal();
        }
        setGanancia(ingresos);
        imprimir("El total de las ganancias es: " + ingresos);
        submenu(5);
    }

    /**
     * Metodo que imprime en la consola el total de la nomina de la biblioteca.
     */
    public void calcularTotalPagos() {
        imprimir("------Calcular Nomina------");
        double nomina = 0;
        for (Bibliotecario bibliotecario : bibliotecarios) {
            nomina += bibliotecario.calcularSueldo();
        }
        imprimir("El total de la nomina es: $" + nomina);
        submenu(5);
    }

    // Metodos Estudiante

    /**
     * Metodo que gestiona todas las  operaciones referentes a los estudiantes.
     */
    public void operacionEstudiante() {
        imprimir("------Operacion Estudiante------");
        imprimir("1. Crear Estudiante.");
        imprimir("2. Eliminar Estudiante.");
        imprimir("3. Mostrar datos Estudiante.");
        imprimir("4. Mostrar Estudiante con mas prestamos.");
        imprimir("5. Regresar.");

        int num = ingresarEntero("Seleccione una opción:");
        switch (num) {
            case 1:
                crearEstudiante();
                break;
            case 2:
                eliminarEstudiante();
                break;
            case 3:
                mostrarEstudiante();
                break;
            case 4:
                estudianteMasPrestamos();
                break;
            case 5:
                menu();
                break;
            default:
                imprimir("Opción Incorrecta.");
                operacionEstudiante();
                break;
        }
    }

    
    /**
     * Metodo que invoca al metodo constructor de la clase estudiante, para crear estudiantes con datos ingresados por la consola.
     */
    public void crearEstudiante() {
        imprimir("------Creacion de Estudiantes------");
        String nombre = ingresarCadena("Nombre: ");
        String cedula = ingresarCadena("Cedula: ");

        if (verificarEstudiante(cedula)) {
            imprimir("El estudiante ya existe.");
            submenu(1);
        } else {
            String correo = ingresarCadena("Correo: ");
            String telefono = ingresarCadena("Telefono: ");
            int edad = ingresarEntero("Edad:");
            Estudiante estudiante = new Estudiante(nombre, cedula, telefono, correo, edad);
            ingresarEstudiante(estudiante);
            imprimir("Se ha creado el estudiante.");
            submenu(1);
        }
    }

    /**
     * Metodo que agrega un estudiante a la lista de estudiantes de la biblioteca.
     * @param estudiante
     */
    public void ingresarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);

    }

    /**
     * Metodo que verifica con su cedula la existencia de un estudiante en la lista de estudiantes de biblioteca.
     * @param cedula
     * @return Falso si el estudiante no esta en la lista, verdadero si lo esta.
     */
    public boolean verificarEstudiante(String cedula) {
        boolean centinela = false;
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCedula().equals(cedula)) {
                centinela = true;
            }
        }
        return centinela;
    }

    /**
     * Metodo que elimina un estudiante de la lista de estudiantes de biblioteca.
     */
    public void eliminarEstudiante() {
        imprimir("-------Eliminacion de Estudiantes------");
        String cedula = ingresarCadena("Ingrese el cedula: ");
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCedula().equals(cedula)) {
                estudiantes.remove(estudiante);
                imprimir("El estudiante se ha eliminado con exito.");
                submenu(1);
            }
        }
        imprimir("No existe el estudiante.");
        submenu(1);
    }

    /**
     * Metodo que imprime los datos de un estudiante en la consola.
     */
    public void mostrarEstudiante() {
        String cedula = ingresarCadena("Ingrese la cedula: ");
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCedula().equals(cedula)) {
                imprimir("------------------------");
                imprimir(estudiante.toString());
                submenu(1);
            }
        }
        imprimir("No existe el estudiante.");
        submenu(1);
    }

        /**
         * Metodo que recupera un estudiante de la lista de estudiantes de biblioteca.
         * @return El estudiante en caso de que exista, null en caso contrario.
         */
    public Estudiante buscarEstudiante() {
        String cedula = ingresarCadena("Ingrese la cedula del estudiante a buscar: ");
        Estudiante student = null;
        if (verificarEstudiante(cedula)) {
            for (Estudiante estudiante : estudiantes) {
                if (estudiante.getCedula().equals(cedula)) {
                    student = estudiante;
                }
            }
        } else {
            imprimir("La cedula ingresada no esta asociada a ningún estudiante");
        }
        return student;
    }

    /**
     * Metodo que imprime en la consola los datos de el/los estudiante/s con mas prestamos.
     */
    public void estudianteMasPrestamos() {
        imprimir("------Consulta Estudiante con mas Prestamos------");
        int max = 0;
        List<Estudiante> estudiantesMasPrestamos = new LinkedList<>();

        for (Estudiante estudiante : estudiantes) {
            int prestamosActuales = estudiante.numeroPrestamos();
            if (prestamosActuales > max) {
                max = prestamosActuales;
                estudiantesMasPrestamos.clear();
                estudiantesMasPrestamos.add(estudiante);
            } else if (prestamosActuales == max) {
                estudiantesMasPrestamos.add(estudiante);
            }
        }

        if (max > 0 && !estudiantesMasPrestamos.isEmpty()) {
            imprimir("El/Los estudiante(s) con más préstamos son:");
            for (Estudiante estudiante : estudiantesMasPrestamos) {
                imprimir(estudiante.toStringBasico() + "\nTiene un total de " + max
                        + " préstamos.\n--------------------");
            }
        } else {
            imprimir("No hay estudiantes con préstamos.");
        }

        submenu(1);
    }

    // Metodos Bibliotecario


    /**
     * Metodo que gestiona todas las operacion referentes a los bibliotecarios.
     */
    public void operacionBibliotecario() {
        imprimir("-------OPeracion Bibliotecario------");
        imprimir("1. Crear Bibliotecario");
        imprimir("2. Eliminar Bibliotecario");
        imprimir("3. Mostrar Bibliotecario");
        imprimir("4.Consultar el numero de prestamos de un bibliotecario");
        imprimir("5. Regresar.");

        int num = ingresarEntero("Seleccione una opción:");
        switch (num) {
            case 1:
                crearBibliotecario();
                break;
            case 2:
                eliminarBibliotecario();
                break;
            case 3:
                mostrarBibliotecario();
                break;
            case 4:
                prestamosBibliotecario();
            case 5:
                menu();
                break;
            default:
                imprimir("Opción Incorrecta.");
                operacionBibliotecario();
                break;
        }
    }

    /**
     * Metodo que invoca al metodo constructor de la clase bibliotecario, para crear un nuevo bibliotecario con datos ingresados por la consola.
     */
    public void crearBibliotecario() {
        imprimir("------Creacion Bibliotecarios------");
        String nombre = ingresarCadena("Nombre: ");
        String cedula = ingresarCadena("Cedula: ");

        if (verificarBibliotecario(cedula)) {
            imprimir("El bibliotecario ya existe.");
            submenu(2);
        } else {
            String correo = ingresarCadena("Correo: ");
            String telefono = ingresarCadena("Telefono: ");
            int antiguedad = ingresarEntero("Antiguedad:");
            double salario = 0;
            Bibliotecario bibliotecario = new Bibliotecario(nombre, cedula, telefono, correo, salario, antiguedad);
            ingresarBibliotecario(bibliotecario);
            imprimir("Se ha creado el bibliotecario.");
            submenu(2);
        }
    }

    /**
     * Metodo que agrega un bibliotecario a la lista de bibliotecarios de la biblioteca.
     * @param bibliotecario
     */
    public void ingresarBibliotecario(Bibliotecario bibliotecario) {
        bibliotecarios.add(bibliotecario);
    }


    /**
     * Metodo que recupera un bibliotecario de la lista de bibliotecarios de la biblioteca.
     * @return
     */
    public Bibliotecario buscarBibliotecario() {
        String cedula = ingresarCadena("Ingrese la cedula del bibliotecario a recuperar: ");
        Bibliotecario librarian = null;
        if (verificarBibliotecario(cedula)) {
            for (Bibliotecario bibliotecario : bibliotecarios) {
                if (bibliotecario.getCedula().equals(cedula)) {
                    librarian = bibliotecario;
                }
            }
        } else {
            imprimir("La cedula ingresada no esta asociada a ningún bibliotecario");
        }
        return librarian;
    }

    /**
     * Metodo que verifica con su cedula la existencia de un bibliotecario en la lista de bibliotecarios de biblioteca. 
     * @param cedula
     * @return True en caso de que exista el bibliotecario, false en caso contrario.
     */
    public boolean verificarBibliotecario(String cedula) {
        boolean centinela = false;
        for (Bibliotecario bibliotecario : bibliotecarios) {
            if (bibliotecario.getCedula().equals(cedula)) {
                centinela = true;
            }
        }
        return centinela;
    }

    /**
     * Metodo que elimina con su cedula a un bibliotecario de la lista de bibliotecarios de biblioteca.
     */
    public void eliminarBibliotecario() {
        imprimir("------Eliminar Bibliotecario------");
        String cedula = ingresarCadena("Ingrese la cedula: ");
        if (verificarBibliotecario(cedula)) {
            for (Bibliotecario bibliotecario : bibliotecarios) {
                if (bibliotecario.getCedula().equals(cedula)) {
                    bibliotecarios.remove(bibliotecario);
                    imprimir("El bibliotecario se ha eliminado con exito.");
                    break;
                }
            }
        } else {
            imprimir("No existe el bibliotecario, eliminacion cancelada.");
        }
        submenu(2);
    }

    /**
     * Metodo que imprime en la consola el numero de prestamos realizados por un bibliotecario.
     */
    public void prestamosBibliotecario() {
        imprimir("------Consulta Numero Prestamos Bibliotecario------");
        mostrarBibliotecarios();
        Bibliotecario bibliotecario = buscarBibliotecario();
        if (bibliotecario != null) {
            imprimir("\n------Bibliotecario seleccionado------ \n");
            imprimir(bibliotecario.toStringBasico());
            int numeroPrestamos = bibliotecario.numeroPrestamosBibliotecario();
            imprimir("El numero de prestamos realizados por el bibliotecario es: " + numeroPrestamos);
        }
        submenu(2);
    }

    /**
     * Metodo que imprime en la consola, los datos de un bibliotecario con base en su cedula.
     */
    public void mostrarBibliotecario() {
        String cedula = ingresarCadena("Ingrese la cedula: ");
        for (Bibliotecario bibliotecario : bibliotecarios) {
            if (bibliotecario.getCedula().equals(cedula)) {
                imprimir("------------------------");
                imprimir(bibliotecario.toString());
                submenu(2);
            }
        }
        imprimir("No existe el bibliotecario.");
        submenu(2);
    }

    /**
     * Metodo que imprime en la consola los datos de un bibliotecario, sin incluir su suelda y antigüedad. 
     */
    public void mostrarBibliotecarios() {
        for (Bibliotecario bibliotecario : bibliotecarios) {
            imprimir(bibliotecario.toStringBasico() + "\n");
        }
    }


    /**
     * Metodo que gestiona todas las operaciones referentes a libros.
     */
    public void operacionLibro() {
        imprimir("------Operacion Libro------");
        imprimir("1. Crear Libro ");
        imprimir("2. Agregar copias");
        imprimir("3. Eliminar Libro.");
        imprimir("4. Mostrar Libro.");
        imprimir("5. Consulta numero de prestamos de un libro.");
        imprimir("6. Reemplazar libro");
        imprimir("7. Regresar.");

        int num = ingresarEntero("Seleccione una opción:");
        switch (num) {
            case 1:
                crearLibro();
                break;
            case 2:
                actualizacionCopias();
                break;
            case 3:
                eliminarLibro();
                break;
            case 4:
                mostrarLibro();
                break;
            case 5:
                numeroPrestamosLibro();
                break;
            case 6:
                reemplazoLibro();
                break;
            case 7:
                menu();
                break;
            default:
                imprimir("Opción Incorrecta.");
                operacionLibro();
                break;
        }
    }

    /**
     * Metodo que invoca al metodo constructor de la clase libro, para crear un libro nuevo con datos ingresados por la consola.
     */
    public void crearLibro() {
        imprimir("------Creacion de Libros------");
        String codigo = ingresarCadena("Codigo: ");

        if (verificarLibro(codigo)) {
            imprimir(
                    "El Libro ya existe, puede ir al apartado -Agregar Unidades- para aumentar las copias del libro .");

        } else {
            String autor = ingresarCadena("Autor: ");
            String titulo = ingresarCadena("Titulo: ");
            String editorial = ingresarCadena("Editorial: ");
            LocalDate fecha = ingresarFecha("Fecha: ");
            int copias = ingresarEntero("Numero de copias: ");
            Libro libro = new Libro(codigo, autor, titulo, editorial, fecha, copias);
            ingresarLibro(libro);
            imprimir("Se ha creado el libro.");
        }
        submenu(3);
    }

    /**
     * Metodo que agrega un libro a lista de libros de biblioteca.
     * @param libro
     */
    public void ingresarLibro(Libro libro) {
        libros.add(libro);
    }

    /**
     * Metodo que verifica la existencia de un libro en la lista de libros de biblioteca.
     * @param codigo
     * @return True en caso de que el libro exista, false en caso contrario.
     */
    public boolean verificarLibro(String codigo) {
        boolean centinela = false;
        for (Libro libro : libros) {
            if (libro.getCodigo().equals(codigo)) {
                centinela = true;
            }
        }
        return centinela;
    }

    /**
     * Metodo que recupera con base en su titulo, un libro de la lista de libros de biblioteca.
     * @param nombre
     * @return El libro en caso de que exista, null, en caso contrario.
     */
    public Libro verificarLibroTitulo(String nombre) {
        Libro book = null;
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(nombre)) {
                book = libro;
            }
        }

        return book;
    }

    /**
     * Metodo que permite reemplazar o sobreescribir los datos de un libro.
     */
    public void reemplazoLibro() {
        imprimir("------Reemplazo de libro------");
        Libro libro = seleccionarLibro();
        if (libro != null) {
            imprimir("A continuacion ingresara los datos del libro nuevo.");
            String codigo = ingresarCadena("Codigo: ");
            if (verificarLibro(codigo)) {
                imprimir("El codigo ya esta en uso.\nReemplazo rechazado.");
            } else {
                String autor = ingresarCadena("Autor: ");
                String titulo = ingresarCadena("Titulo: ");
                String editorial = ingresarCadena("Editorial: ");
                LocalDate fecha = ingresarFecha("Fecha: ");
                int copias = ingresarEntero("Numero de copias: ");
                Libro nuevoLibro = new Libro(codigo, autor, titulo, editorial, fecha, copias);
                libros.remove(libro);
                libros.add(nuevoLibro);
                imprimir("Reemplazo exitoso");
            }
        } else {
            imprimir("Reemplazo rechazado");
        }
        submenu(3);
    }


    /**
     * Metodo que recupera con base en su codigo un libro de la lista de libros de biblioteca.
     * @return El libro en caso de que existe, null en caso contrario.
     */
    public Libro seleccionarLibro() {
        String codigo = ingresarCadena("Ingrese el codigo del libro a seleccionar");
        Libro book = null;
        if (verificarLibro(codigo)) {
            for (Libro libro : libros) {
                if (libro.getCodigo().equals(codigo)) {
                    book = libro;
                }
            }
        } else {
            imprimir("El codigo ingresado no esta asociado a ningún libro");
        }
        return book;
    }

    /**
     * Metodo que permite modificar el numero de copias de un libro.
     */
    public void actualizacionCopias() {
        imprimir("------Actualización de Copias------");
        String codigo = ingresarCadena("Codigo: ");
        if (verificarLibro(codigo)) {
            int copias = ingresarEntero("Numero de copias a guardar: ");
            for (Libro libro : libros) {
                if (libro.getCodigo().equals(codigo)) {
                    libro.setCopias(libro.getCopias() + copias);
                    imprimir("El numero de copias se ha actualizado.");
                    imprimir("El numero de copias es: " + libro.getCopias());
                }
            }

        } else {
            imprimir("El libro no existe.");
        }
        submenu(3);
    }

    /**
     * Metodo que permite eliminar con base en su codigo un libro de la lista de libros de biblioteca.
     */
    public void eliminarLibro() {
        imprimir("------------------------");
        String codigo = ingresarCadena("Ingrese el codigo: ");
        for (Libro libro : libros) {
            if (libro.getCodigo().equals(codigo)) {
                libros.remove(libro);
                imprimir("El libro se ha eliminado con éxito.");
                submenu(3);
            }
        }
        imprimir("No existe el libro.");
        submenu(3);
    }

    /**
     * Metodo que permite imprimir en la consola los datos de un libro, con base en su codigo.
     */
    public void mostrarLibro() {
        imprimir("------Mostrar Libro------");
        String codigo = ingresarCadena("Ingrese el codigo: ");
        for (Libro libro : libros) {
            if (libro.getCodigo().equals(codigo)) {
                imprimir("------------------------");
                imprimir(libro.toString());
                submenu(3);
            }
        }
        imprimir("No existe el libro.");
        submenu(3);
    }

    /**
     * Metodo que imprimir en la consola los libros que en la lista de libros de biblioteca.
     */
    public void mostrarLibros() {
        for (Libro libro : libros) {
            imprimir(libro.toString());
        }
    }

    /**
     * Metodo que imprime en la consola el numero de prestamos en los que un libro esta involucrado.
     */
    public void numeroPrestamosLibro() {
        imprimir("------Consulta Numero de Prestamo por Libro------");
        String titulo = ingresarCadena("Ingrese el nombre del libro a consultar:");
        String respuesta = "";
        Libro book = verificarLibroTitulo(titulo);
        if (book != null) {
            int numeroPrestamos = 0;
            for (Prestamo prestamo : prestamos) {
                for (DetallePrestamo detalle : prestamo.getDetalles()) {
                    Libro libro = detalle.getLibro();
                    if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                        numeroPrestamos += 1;
                    }

                }
            }
            respuesta = "El numero de prestamos asociados al libro: " + book.getTitulo() + " es " + numeroPrestamos;
        } else {
            respuesta = "No hay ningun libro asociado al nombre ingresado.";
        }
        imprimir(respuesta);
        submenu(3);
    }

    // Metodos para prestamo

    /**
     * Metodo que gestiona todas las operaciones referentes a los prestamos.
     */
    public void operacionPrestamo() {
        imprimir("-------Operacion Prestamo------");
        imprimir("1. Crear Prestamo");
        imprimir("2. Eliminar Prestamo");
        imprimir("3. Entregar Prestamo");
        imprimir("4. Mostrar  Datos Prestamo");
        imprimir("5. Regresar.");

        int num = ingresarEntero("Seleccione una opción:");
        switch (num) {
            case 1:
                crearPrestamo();
                break;
            case 2:
                eliminarPrestamo();
                break;
            case 3:
                entregarPrestamo();
                break;
            case 4:
                mostrarPrestamo();
                break;
            case 5:
                menu();
                break;
            default:
                imprimir("Opción Incorrecta.");
                operacionPrestamo();
                break;
        }
    }

    /**
     * Metodo que invoca el metodo constructor de la clase prestamo, para crear un nuevo prestamo con datos ingresados por la consola.
     */
    public void crearPrestamo() {
        imprimir("-------Creacion Prestamos------");
        String codigo = ingresarCadena("Codigo: ");

        if (verificarPrestamo(codigo)) {
            imprimir("El codigo ya esta en uso.");
            submenu(4);
            return;
        } else {
            LocalDate fecha = LocalDate.now();
            imprimir(("Fecha inicio del prestamo: " + fecha));
            LocalDate fechaEntrega;

            do {
                fechaEntrega = ingresarFecha("Fecha entrega del prestamo: ");
                if (ChronoUnit.DAYS.between(fecha, fechaEntrega) < 0) {
                    imprimir(
                            "La fecha de entrega no puede ser anterior a la fecha de prestamo. Por favor, ingrese una fecha válida.");
                }
            } while (ChronoUnit.DAYS.between(fecha, fechaEntrega) < 0);

            double costoDia = ingresarReal("Ingrese el costo por dia del prestamo: ");
            Estudiante estudiante = buscarEstudiante();
            if (estudiante == null) {
                submenu(4);
                return;
            }
            Bibliotecario bibliotecario = buscarBibliotecario();
            if (bibliotecario == null) {
                submenu(4);
                return;
            }
            Prestamo prestamo = new Prestamo(codigo, estudiante, bibliotecario, costoDia, fecha, fechaEntrega);
            imprimir("\n------Libros disponibles------");
            mostrarLibros();
            boolean prestamoCreado = (operacionLibroPrestamo(prestamo, costoDia));
            if (prestamoCreado) {
                ingresarPrestamo(prestamo);
                bibliotecario.registrarPrestamoBibliotecario(prestamo);
                estudiante.registrarPrestamoEstudiante(prestamo);
                imprimir("El prestamo se creo exitosamente");
            } else {
                imprimir("No se agrego ningun libro, el prestamo no fue creado.");
            }
            submenu(4);
        }
    }

    /**
     * Metodo que permite agregar libros a un prestamo
     * @param prestamo
     * @param costoDia
     * @return True en caso de que se haya agregado al menos un libro en el prestamo, false en caso contrario.
     */
    public boolean operacionLibroPrestamo(Prestamo prestamo, double costoDia) {

        boolean agregado = false;
        while (true) {

            imprimir("\n---Ingrese una opcion:---");
            int opcion = ingresarEntero("1. Agregar libro.\n2. Terminar");

            switch (opcion) {
                case 1:
                    Libro libro = seleccionarLibro();

                    if (libro != null) {
                        int copias = ingresarEntero("Ingrese el numero de copias a prestar:");
                        boolean exitoso = prestamo.crearDetallePrestamo(libro, copias, costoDia);

                        if (exitoso) {
                            imprimir("Libro agregado exitosamente");
                            agregado = true;

                            int decision = ingresarEntero("\n---¿Desea agregar otro libro?-- (1/2)\n1. Si \n2. No");
                            if (decision == 2) {
                                return agregado;
                            }

                        } else {
                            imprimir("\n-----La cantidad de unidades disponibles es insuficiente-----\n");
                        }
                    } else {
                        imprimir("Libro no seleccionado, intente nuevamente.");
                    }
                    break;

                case 2:
                    return agregado;

                default:
                    imprimir("Opcion invalida.");
                    break;
            }
        }

    }

    /**
     * Metodo que permite entregar un prestamo y muestra el valor total de esta ultimo.
     */
    public void entregarPrestamo() {
        imprimir("------Entrega Prestamos------");
        Estudiante alumno = buscarEstudiante();
        if (alumno != null) {

            imprimir("\n---Prestamos activos del estudiante---\n");
            if (alumno.verificarPrestamos()) {
                alumno.mostrarPrestamosEstudiante();
                String codigo = ingresarCadena("Ingrese el codigo del prestamo a entregar");
                Prestamo prestamo = alumno.seleccionarPrestamoEstudiante(codigo);
                if (prestamo != null) {
                    imprimir("\n------Prestamo seleccionado:------\n");
                    imprimir(prestamo.toStringEntrega());

                    boolean repetir = true;
                    while (repetir) {
                        int opcion = ingresarEntero("\n\t1.Confirmar entrega\n\t2.Cancelar entrega");
                        switch (opcion) {
                            case 1:
                                alumno.eliminarPrestamo(prestamo);
                                imprimir("Prestamo entregado exitosamente...");
                                repetir = false;
                                break;
                            case 2:
                                imprimir("Entrega cancelada...");
                                repetir = false;
                                break;
                            default:
                                imprimir("Opcion invalida...");
                        }
                    }
                } else {
                    imprimir("El estudiante no tiene ningun prestamo asociado al codigo ingresado.");
                    imprimir("Entrega rechazada...");
                }
            } else {
                imprimir("\n---El Estudiante no tiene prestamos activos.---");
            }
        }
        submenu(4);
    }

    /**
     * Metodo que imprime en la consola los datos de un prestamo sin incluir su total.
     */
    public void mostrarPrestamo() {
        imprimir("------Consulta Prestamos------");
        Prestamo prestamo = seleccionarPrestamo("Ingrese el codigo del prestamo a consultar: ");
        if (prestamo != null) {
            imprimir(prestamo.toString());
        }
        submenu(4);
    }

    /**
     * Metodo que permite recuperar un prestamo de la lista de prestamos de la biblioteca.
     * @param mensaje
     * @return El prestamo en caso de que existe, null en caso contrario.
     */
    public Prestamo seleccionarPrestamo(String mensaje) {
        String codigo = ingresarCadena(mensaje);
        Prestamo loan = null;
        boolean existe = verificarPrestamo(codigo);
        if (existe) {
            for (Prestamo prestamo : prestamos) {
                if (prestamo.getCodigo().equals(codigo)) {
                    loan = prestamo;
                    break;
                }
            }

        } else {
            imprimir("El codigo ingresado no esta asociado a ningun prestamo");
        }
        return loan;
    }

    /**
     * Metodo que permite eliminar un prestamo de la lista de prestamos de biblioteca.
     */
    public void eliminarPrestamo() {
        Prestamo prestamo = seleccionarPrestamo("Ingrese el codigo del prestamo a eliminar: ");
        if (prestamo != null) {
            prestamos.remove(prestamo);
            imprimir("Prestamo eliminado...");
        }
        submenu(4);
    }

    /**
     * Metodo que permite agregar un prestamo a lista de prestamos de biblioteca.
     * @param prestamo
     */
    public void ingresarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    /**
     * Metodo que permite verificar la existencia de un prestamo en la lista de prestamos de biblioteca.
     * @param codigo
     * @return True en caso de que el prestamo exista, false en caso contrario.
     */
    public boolean verificarPrestamo(String codigo) {
        boolean centinela = false;
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getCodigo().equals(codigo)) {
                centinela = true;
            }
        }
        return centinela;
    }

    /**
     * Metodo que permite ingresar un cadena de datos por la consola.
     * @param texto
     * @return La cadena ingresada,
     */
    public static String ingresarCadena(String texto) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(texto);
        String cadena = scanner.nextLine();
        return cadena;
    }

    /**
     * Metodo para ingresar un numero entero, evitando cualquier dato invalido
     * 
     * @param texto
     * @return num
     */
    public static int ingresarEntero(String texto) {
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        boolean repetir = true;
        while (repetir) {
            try {
                System.out.println(texto);
                num = Integer.parseInt(scanner.nextLine());
                repetir = false;
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado es invalido.");
            }
        }
        return num;
    }

    /**
     * Metodo que permite ingrese un numero real, evitando cualquier dato invalido.
     * @param texto
     * @return El numero ingresado
     */
    public static double ingresarReal(String texto) {
        Scanner scanner = new Scanner(System.in);
        double num = 0;
        boolean repetir = true;
        while (repetir) {
            try {
                System.out.println(texto);
                num = Double.parseDouble(scanner.nextLine());
                repetir = false;
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado es invalido.");
            }
        }
        return num;
    }

    /**
     * Metodo que permite ingresar un fecha con el formato yyyy-MM-dd, evitando cualquier dato invalido
     * @param texto
     * @return La fecha ingresada.
     */
    public LocalDate ingresarFecha(String texto) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = null;
        boolean repetir = true;
        while (repetir) {
            try {
                imprimir(texto + ": (yyyy-MM-dd)");
                String fechaIngresada = scanner.nextLine();
                fecha = LocalDate.parse(fechaIngresada, formatter);
                repetir = false;
            } catch (DateTimeParseException e) {
                imprimir("Formato invalido");

            }
        }
        return fecha;
    }

    /**
     * Metodo que permite imprimir en la consola un cadena.
     * @param texto
     */
    public void imprimir(String texto) {
        System.out.println(texto);
    }

    /**
     *  Metodo que permite recuperar el nombre de la biblioteca.
     * @return El nombre de la biblioteca,}.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que permite modificar el nombre de la biblioteca.
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que permite recuperar la lista de estudiantes de la biblioteca.
     * @return La lista de estudiantes de la biblioteca.
     */
    public LinkedList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    /**
     * Metodo que permite modificar la lista de estudiantes de la biblioteca.
     * @param estudiantes
     */
    public void setEstudiantes(LinkedList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

 /**
  * Metodo que permite recuperar la lista de bibliotecarios de la biblioteca.
  * @return La lista de bibliotecarios de biblioteca.
  */
    public LinkedList<Bibliotecario> getBibliotecarios() {
        return bibliotecarios;
    }

    /**
     * Metodo que permite modificar la lista de bibliotecarios de biblioteca.
     * @param bibliotecarios
     */
    public void setBibliotecarios(LinkedList<Bibliotecario> bibliotecarios) {
        this.bibliotecarios = bibliotecarios;
    }

    /**
     * Metodo qu permite recuperar la lista de prestamos de biblioteca.
     * @return La lista de prestamos de biblioteca
     */
    public LinkedList<Prestamo> getPrestamos() {
        return prestamos;
    }

    /**
     * Metodo que permite modificar la lista de prestamos de biblioteca.
     * @param prestamos
     */
    public void setPrestamos(LinkedList<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    /**
     * Metodo que permite recuperar la lista del libros de biblioteca.
     * @return La lista de libros de biblioteca
     */
    public LinkedList<Libro> getLibros() {
        return libros;
    }

    /**
     * Metodo que permite modificar la lista de libros de biblioteca.
     * @param libros
     */
    public void setLibros(LinkedList<Libro> libros) {
        this.libros = libros;
    }

    /**
     * Metodo que permite recuperar las ganacias de la biblioteca.
     * @return Las ganancias de la biblioteca.
     */
    public double getGanancia() {
        return ganancia;
    }

    /**
     * Metodo que permite modificar las ganancias de la biblioteca.
     * @param ganancia
     */
    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    /**
     * Metodo que permite recuperar la cantidad de libros de la biblioteca.
     * @return La cantidad de libros de la biblioteca
     */
    public int getCantidadLibros() {
        return cantidadLibros;
    }

    /**
     * Metodo que permite modificar la cantidad libros de biblioteca.
     * @param cantidadLibros
     */
    public void setCantidadLibros(int cantidadLibros) {
        this.cantidadLibros = cantidadLibros;
    }

}