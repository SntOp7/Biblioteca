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

    public void autocarga() {
        Estudiante alu1 = new Estudiante("JUAN", "1010", "321", "juan@uqvirtual", 18);
        Estudiante alu2 = new Estudiante("ANA", "1011", "321", "ana@uqvirtual", 20);
        Estudiante alu3 = new Estudiante("Sofia", "1012", "321", "sofia@uqvirtual", 45);
        ingresarEstudiante(alu1);
        ingresarEstudiante(alu2);
        ingresarEstudiante(alu3);

        Bibliotecario biblio1 = new Bibliotecario("Maria", "1012", "321",
                "maria@uqvirtual", 0, 4);
        Bibliotecario biblio2 = new Bibliotecario("Pedro", "1013", "321",
                "jose@uqvirtual", 0, 5);
        ingresarBibliotecario(biblio2);
        ingresarBibliotecario(biblio1);

        Libro lib1 = new Libro("23", "Marco", "Meditaciones", "Mestas", LocalDate.of(2020, 12, 03),
                5);
        Libro lib2 = new Libro("24", "Kant", "Critica de la Razon", "Mestas",
                LocalDate.of(1980, 8, 15), 19);
        Libro lib3 = new Libro("25", "Camus", "Sisifo", "alianza",
                LocalDate.of(1995, 8, 15), 5);
        ingresarLibro(lib1);
        ingresarLibro(lib2);
        ingresarLibro(lib3);

        Prestamo prestamo = new Prestamo("1034", alu1, biblio1, 1000, LocalDate.now(), LocalDate.of(2024, 9, 30));
        prestamo.crearDetallePrestamo(lib1, 3, 1000);
        Prestamo prestamo1 = new Prestamo("1035", alu1, biblio1, 5000, LocalDate.now(), LocalDate.of(2024, 10, 30));
        prestamo1.crearDetallePrestamo(lib1, 3, 1000);
        Prestamo prestamo2 = new Prestamo("1036", alu2, biblio2, 3000, LocalDate.now(), LocalDate.of(2024, 11, 30));
        prestamo2.crearDetallePrestamo(lib1, 3, 1000);
        ingresarPrestamo(prestamo);
        ingresarPrestamo(prestamo1);
        ingresarPrestamo(prestamo2);

    }

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

    public void librosBiblioteca() {
        int numLibros = libros.size();
        int copiasBiblioteca = copiasBiblioteca();
        imprimir("El total de títulos en el inventario es: " + numLibros);
        imprimir("El total de copias en el inventario es: " + copiasBiblioteca);
        submenu(5);
    }

    public int copiasBiblioteca() {
        int copiasBiblioteca = 0;
        for (Libro libro : libros) {
            copiasBiblioteca += libro.getCopias();
        }
        setCantidadLibros(copiasBiblioteca);
        return copiasBiblioteca;
    }

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

    public void imprimir(String texto) {
        System.out.println(texto);
    }

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

    public void ingresarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);

    }

    public boolean verificarEstudiante(String cedula) {
        boolean centinela = false;
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCedula().equals(cedula)) {
                centinela = true;
            }
        }
        return centinela;
    }

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

    public void ingresarBibliotecario(Bibliotecario bibliotecario) {
        bibliotecarios.add(bibliotecario);
    }

    public Bibliotecario buscarBibliotecario() {
        String cedula = ingresarCadena("Ingrese la cedula del bibliotecario a seleccionar: ");
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

    public boolean verificarBibliotecario(String cedula) {
        boolean centinela = false;
        for (Bibliotecario bibliotecario : bibliotecarios) {
            if (bibliotecario.getCedula().equals(cedula)) {
                centinela = true;
            }
        }
        return centinela;
    }

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

    public void mostrarBibliotecarios() {
        for (Bibliotecario bibliotecario : bibliotecarios) {
            imprimir(bibliotecario.toStringBasico() + "\n");
        }
    }

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

    public void ingresarLibro(Libro libro) {
        libros.add(libro);
    }

    public boolean verificarLibro(String codigo) {
        boolean centinela = false;
        for (Libro libro : libros) {
            if (libro.getCodigo().equals(codigo)) {
                centinela = true;
            }
        }
        return centinela;
    }

    public Libro verificarLibroTitulo(String nombre) {
        Libro book = null;
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(nombre)) {
                book = libro;
            }
        }

        return book;
    }

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

    public void mostrarLibros() {
        for (Libro libro : libros) {
            imprimir(libro.toString());
        }
    }

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
                    imprimir("La fecha de entrega no puede ser anterior a la fecha de prestamo. Por favor, ingrese una fecha válida.");
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

    public void entregarPrestamo() {
        imprimir("------Entrega Prestamos------");
        Estudiante alumno = buscarEstudiante();
        if (alumno != null) {

            imprimir("\n---Prestamos activos del estudiante---\n");
            if (alumno.verificarPrestamos()) {
                alumno.mostrarPrestamosEstudiante();
                String codigo =ingresarCadena("Ingrese el codigo del prestamo a entregar");
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

    public void mostrarPrestamo() {
        imprimir("------Consulta Prestamos------");
        Prestamo prestamo = seleccionarPrestamo("Ingrese el codigo del prestamo a consultar: ");
        if (prestamo != null) {
            imprimir(prestamo.toString());
        }
        submenu(4);
    }

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

    

    public void eliminarPrestamo() {
        Prestamo prestamo = seleccionarPrestamo("Ingrese el codigo del prestamo a eliminar: ");
        if (prestamo != null) {
            prestamos.remove(prestamo);
            imprimir("Prestamo eliminado...");
        }
    submenu(4);
    }

    public void ingresarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    public boolean verificarPrestamo(String codigo) {
        boolean centinela = false;
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getCodigo().equals(codigo)) {
                centinela = true;
            }
        }
        return centinela;
    }

    public static String ingresarCadena(String texto) {
        @SuppressWarnings("resource")
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
        @SuppressWarnings("resource")
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

    public static double ingresarReal(String texto) {
        @SuppressWarnings("resource")
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

    public LocalDate ingresarFecha(String texto) {
        @SuppressWarnings("resource")
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LinkedList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(LinkedList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public LinkedList<Bibliotecario> getBibliotecarios() {
        return bibliotecarios;
    }

    public void setBibliotecarios(LinkedList<Bibliotecario> bibliotecarios) {
        this.bibliotecarios = bibliotecarios;
    }

    public LinkedList<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(LinkedList<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public LinkedList<Libro> getLibros() {
        return libros;
    }

    public void setLibros(LinkedList<Libro> libros) {
        this.libros = libros;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    public int getCantidadLibros() {
        return cantidadLibros;
    }

    public void setCantidadLibros(int cantidadLibros) {
        this.cantidadLibros = cantidadLibros;
    }

}