package bicicleteria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloStockPieza {
    
    private String jdbcDriver;
    private String dbName;
    private String urlRoot;
    private ActionListener listener;

    public ModeloStockPieza() {
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

    public Boolean alta(String pieza, String codigo) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO piezas(pieza,codigo) VALUES('"+pieza+"','"+codigo+"');");
            stmt.close();
            return true;
        } catch(SQLException ex) {
            reportException(ex.getMessage());
            return false;
        }
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

    void baja(String pieza, String codigo) {
                try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM piezas WHERE pieza = '"+ pieza + "' AND codigo ='"+ codigo +"';");
            stmt.close();
        } catch(SQLException ex) {
            reportException(ex.getMessage());
        }
    }
}
