package bicicleteria;

public abstract class Persona {

    private final String nombre;
    private final String usuario;
    private final String password;

    public Persona(String usuario, String password, String nombre) {
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public abstract String getVista();
    
    public abstract Object getRecursos();

}
