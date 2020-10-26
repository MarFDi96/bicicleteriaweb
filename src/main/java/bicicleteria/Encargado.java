package bicicleteria;

import java.util.ArrayList;
import java.util.List;

public class Encargado extends Persona {

    public Encargado(String usuario, String password, String nombre) {
        super(usuario, password, nombre);
    }

    @Override
    public String getVista() {
        return "encargado.jsp";
    }

    @Override
    public Object getRecursos() {
        List<String> piezas = new ArrayList<String>();
        piezas.add("Asiento");
        piezas.add("Cuadro");
        piezas.add("KitMecanico");
        piezas.add("Manubrio");
        piezas.add("Ruedas");
        piezas.add("Pedales");
        return piezas;
    }
    
}

