/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bicicleteria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Skynet
 */
class ModeloStockBicicletas {

    private String jdbcDriver;
    private String dbName;
    private String urlRoot;
    private ActionListener listener;

    public ModeloStockBicicletas() {
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

    Boolean ensamblar(String codigo) {
        boolean fAsiento = false, fCuadro = false, fKit = false, fManubrio = false, fPedales = false, fRuedas = false;
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            //testear DISTINCT para la prox
            stmt.executeQuery("SELECT * FROM piezas WHERE codigo ='" + codigo + "';");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {

                switch (rs.getString("pieza")) {
                    case "Asiento":
                        fAsiento = true;
                        break;
                    case "Cuadro":
                        fCuadro = true;
                        break;
                    case "KitMecanico":
                        fKit = true;
                        break;
                    case "Manubrio":
                        fManubrio = true;
                        break;
                    case "Pedales":
                        fPedales = true;
                        break;
                    case "Ruedas":
                        fRuedas = true;
                        break;
                }

            }
            stmt.close();
            if (fAsiento && fCuadro && fKit && fManubrio && fPedales && fRuedas) {
                con = DriverManager.getConnection(urlRoot + dbName, "", "");
                stmt = con.createStatement();
                stmt.executeUpdate("INSERT INTO bicicletas(codigo) VALUES('" + codigo + "');");
                stmt.close();

                // TODO: TESTEAR QUE ESTO ANDE BIEN
                //si, anda
                ModeloStockPieza model = new ModeloStockPieza();
                model.baja("Asiento", codigo);
                model.baja("Cuadro", codigo);
                model.baja("KitMecanico", codigo);
                model.baja("Manubrio", codigo);
                model.baja("Pedales", codigo);
                model.baja("Ruedas", codigo);
                
                return true;
            }
        } catch (SQLException ex) {
            reportException(ex.getMessage());
        }
        return false;
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

    Boolean baja(String codigo) {
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM bicicletas WHERE codigo ='" + codigo + "';");
            stmt.close();
            return true;
        } catch (SQLException ex) {
            reportException(ex.getMessage());
        }
        return false;
    }
}
