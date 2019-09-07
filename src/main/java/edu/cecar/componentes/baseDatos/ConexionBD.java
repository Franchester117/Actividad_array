package edu.cecar.componentes.baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *  Clase que contiene los métodos para establecer conexión con la base de datos.
 **/
public class ConexionBD {
    private Connection connection;

    public ConexionBD(String servidorBD, String nombreBD,String port, String usuario, String password) {

        try {
            Class
                .forName("com.mysql.cj.jdbc.Driver")
                .newInstance();
            String url=
                    "jdbc:mysql://"
                    + servidorBD
                    + ":"+port+"/"
                    + nombreBD
                    + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

            connection=
                    DriverManager
                    .getConnection(url,usuario,password);

        }catch  (Exception e) {
            System.out.println("Error de conexión " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void cerrarConexion()  {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}
