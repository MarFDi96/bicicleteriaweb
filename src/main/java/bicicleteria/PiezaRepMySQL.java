/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bicicleteria;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PiezaRepMySQL implements PiezaRepository {

    private String jdbcDriver;
    private String dbName;
    private String urlRoot;
    private ActionListener listener;

    public PiezaRepMySQL() {
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

    @Override
    public List<Pieza> getPiezas() {
        List<Pieza> piezas = new ArrayList<>();
        //Pieza p = new Pieza();

        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.execute("SELECT pieza, codigo FROM piezas;");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Pieza p = new Pieza();
                p.setPieza(rs.getString(1));
                p.setCodigo(rs.getString(2));
                piezas.add(p);
            }
            con.close();
        } catch (SQLException e) {
            reportException(e.getMessage());
        }
        return piezas;
    }

    @Override
    public List<Pieza> getPiezasByCodigo(String codigo) {
        List<Pieza> piezas = new ArrayList<>();
        Pieza p = null;

        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.execute("SELECT pieza, codigo FROM piezas WHERE codigo='" + codigo + "';");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                p.setPieza(rs.getString(1));
                p.setCodigo(rs.getString(2));
                piezas.add(p);
            }
            con.close();
        } catch (SQLException e) {
            reportException(e.getMessage());
        }
        return piezas;
    }

    @Override
    public List<String> getCodigos() {
        ArrayList<String> resultado = new ArrayList<String>();
        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.execute("SELECT DISTINCT codigo FROM piezas;");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                resultado.add(rs.getString(1));
            }rs.close();
            con.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultado;
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
