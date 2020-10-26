/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bicicleteria;

/**
 *
 * @author Skynet
 */
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BicicletaRepMySQL implements BicicletaRepository {

    private String jdbcDriver;
    private String dbName;
    private String urlRoot;
    private ActionListener listener;

    public BicicletaRepMySQL() {
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
    public List<String> getBicicletas() {
                List<String> bicicletas = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection(urlRoot + dbName, "", "");
            Statement stmt = con.createStatement();
            stmt.execute("SELECT * FROM bicicletas;");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                bicicletas.add(rs.getString("codigo"));
            }
            con.close();
        } catch (SQLException e) {
            reportException(e.getMessage());
        }
        return bicicletas;
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
