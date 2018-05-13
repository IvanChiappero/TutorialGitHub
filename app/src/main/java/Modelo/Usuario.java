package Modelo;

public class Usuario {

    //Creamos todos los datos que existen en la clase Usuario
    private String id;
    private String nombre;
    private String dni;
    private String email;

    public Usuario() {
    }

    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Usuario(String id, String nombre, String dni, String email) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
