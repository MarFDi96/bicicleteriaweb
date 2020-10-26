package bicicleteria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloLogin {

    private String jdbcDriver;
    private String dbName;
    private String urlRoot;
    private ActionListener listener;

    public ModeloLogin() {
        jdbcDriver = "com.mysql.cj.jdbc.Driver";
        urlRoot = "jdbc:mysql://127.0.0.1/";
        dbName = "bicicleteria";
        listener = null;
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            reportException(e.getMessage());
        }
    }

    public Persona autenticar(String usuario, String password) {
        Persona p = null;
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.execute("SELECT rol, nombre FROM personas WHERE usuario='" + usuario
                    + "' AND password='" + password + "';");
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                /** @TODO: Agregar Factory **/
                switch (rs.getString(1)) {
                    case "bicicletero":
                        p = new Bicicletero(usuario, password, rs.getString(2));
                        break;
                    case "encargado":
                        p = new Encargado(usuario, password, rs.getString(2));
                        break;
                    case "vendedor":
                        p = new Vendedor(usuario, password, rs.getString(2));
                        break;
                }
            }
            con.close();
        } catch (SQLException e) {
            reportException(e.getMessage());
        }
        return p;
    }

    public void addExceptionListener(ActionListener listener) {
        this.listener = listener;
    }

    private void reportException(String exception) {
        if (listener != null) {
            ActionEvent evt = new ActionEvent(this, 0, exception);
            listener.actionPerformed(evt);
        }
    }
}
