package edu.cecar.componentes.singletons;

import edu.cecar.componentes.baseDatos.ConexionBD;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;


/**
 *  Clase que contiene los métodos para realizar una instancia de un objeto de tipo Connection.
 **/
public class ConexionSingleton {

    private static Connection conexion;

    public static Connection getInstance(){
        if(conexion==null){
            try {
                Properties pr = new Properties();
                pr.load(new FileInputStream("recursos\\conexion.properties"));
                /*System.out.println(pr.getProperty("host"));
                System.out.println(pr.getProperty("baseDatos"));
                System.out.println(pr.getProperty("usuario"));*/
                conexion = new ConexionBD(
                        pr.getProperty("host"),
                        pr.getProperty("baseDatos"),
                        pr.getProperty("port"),
                        pr.getProperty("usuario"),
                        ""
                ).getConnection();
            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }
        }
        return conexion;
    }

}
