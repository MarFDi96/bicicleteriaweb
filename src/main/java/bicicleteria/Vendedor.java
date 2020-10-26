package bicicleteria;

public class Vendedor extends Persona {

    public Vendedor(String usuario, String password, String nombre) {
        super(usuario, password, nombre);
    }

    @Override
    public String getVista() {
        return "vendedor.jsp";
    }

    @Override
    public Object getRecursos() {
        BicicletaRepMySQL bicis = new BicicletaRepMySQL();
        return bicis.getBicicletas();
    }
    
}
