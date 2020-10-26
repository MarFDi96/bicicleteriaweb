package bicicleteria;

public class Bicicletero extends Persona {

    public Bicicletero(String usuario, String password, String nombre) {
        super(usuario, password, nombre);
    }

    @Override
    public String getVista() {
        return "bicicletero.jsp";
    }

    @Override
    public Object getRecursos() {
        PiezaRepMySQL piezas = new PiezaRepMySQL();
        return piezas.getCodigos();
    }
    
}
