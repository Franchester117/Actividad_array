package edu.cecar.controladores;

import edu.cecar.componentes.singletons.*;
import edu.cecar.modelo.Examples;
import edu.cecar.vista.Cli;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  Clase que contiene los métodos para acceder y manipular la información de la base de datos.
 **/
public class ControladorExamples {

    public int numeroRegistro(){

        System.out.println(Cli.ANSI_BLUE+"Contando registros, espere..."+Cli.ANSI_RESET);
        ResultSet respuesta = null;
        PreparedStatement sentenciaSQL = null;
        int n=0;
        try {
            sentenciaSQL = ConexionSingleton
                    .getInstance()
                    .prepareStatement(
                        "SELECT " +
                            "* " +
                            "FROM " +
                            "manpower.examples"
                    );
            respuesta = sentenciaSQL.executeQuery();
            while(respuesta.next()) n++;
            System.out.println(n);
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
        }
        System.out.println(Cli.ANSI_BLUE+"Numero de registros encontrados: " + n+Cli.ANSI_RESET);
        return n;
    }

    public Examples[] obtenerRegistros(int n){
        Examples[] registros = new Examples[n];
        PreparedStatement sentenciaSQL = null;
        ResultSet respuesta = null;
        int index=0;
        try {
            long t1=System.currentTimeMillis();
            sentenciaSQL = ConexionSingleton
                    .getInstance()
                    .prepareStatement(
                        "SELECT " +
                            "Date_Of_Stop," +
                            "Fatal," +
                            "Alcohol," +
                            "Gender " +
                            "FROM " +
                            "manpower.examples"
                    );
            respuesta = sentenciaSQL.executeQuery();
            System.out.println(Cli.ANSI_BLUE+"Cargando registros, espere..."+Cli.ANSI_RESET);
            while(respuesta.next()){
                registros[index] = new Examples(
                        respuesta.getString(1),
                        respuesta.getString(2),
                        respuesta.getString(3),
                        respuesta.getString(4)
                );
                ++index;
            }
            long t2=System.currentTimeMillis();
            System.out.println(Cli.ANSI_BLUE+"Registros cargados"+Cli.ANSI_RESET);
            System.out.println(Cli.ANSI_BLUE+"Tiempo de ejecucion total: " + (double)((t2-t1)/1000) + " segundos." + Cli.ANSI_RESET);
        }catch (SQLException e){
            System.out.println("Error " + e.getMessage());
        }
        return registros;
    }

    public void buscarRegistrosDate(Examples[] registro, String date){
        Examples[] resultado = new Examples[registro.length];
        boolean exito = false;
        System.out.println(Cli.ANSI_BLUE+"\n\nBuscando registros con date of stop = "+date+", espere..."+Cli.ANSI_RESET);
        long t1 = System.currentTimeMillis();
        int c=0;
        for (int index=0; index<registro.length; ++index){
            if(registro[index].getDateOfStop().equals(date)){
                ++c;
                resultado[index] = new Examples(
                        registro[index].getDateOfStop(),
                        registro[index].getFatal(),
                        registro[index].getAlcohol(),
                        registro[index].getGender()
                );
                System.out.println(
                       c
                       +" Date of stop: " + resultado[index].getDateOfStop()
                       +" Fatal: " + resultado[index].getFatal()
                       +" Alcohol: " + resultado[index].getAlcohol()
                       +" Gender: " + resultado[index].getGender()
                );
            }
        }

        long t2 = System.currentTimeMillis();
        System.out.println(Cli.ANSI_BLUE+"\n\nNumero de registros encontrados = " + (c+1)+Cli.ANSI_RESET);
        System.out.println(Cli.ANSI_BLUE+"Tiempo de ejecucion total: " + (double)((t2-t1)/1000) + " segundos." + Cli.ANSI_RESET);
    }

    public void buscarregistroGender(Examples[] registro, String gender){
        Examples[] resultado = new Examples[registro.length];
        System.out.println(Cli.ANSI_BLUE+"\n\nBuscando registros con Gender = "+gender+", espere..."+Cli.ANSI_RESET);
        long t1 = System.currentTimeMillis();
        int c=0;
        for (int index=0; index<registro.length; ++index){
            if(registro[index].getGender().equalsIgnoreCase(gender)){
                ++c;
                resultado[index] = new Examples(
                        registro[index].getDateOfStop(),
                        registro[index].getFatal(),
                        registro[index].getAlcohol(),
                        registro[index].getGender()
                );
                System.out.println(
                       c
                       +" Date of stop: " + resultado[index].getDateOfStop()
                       +" Fatal: " + resultado[index].getFatal()
                       +" Alcohol: " + resultado[index].getAlcohol()
                       +" Gender: " + resultado[index].getGender()
                );
            }
        }

        long t2 = System.currentTimeMillis();
        System.out.println(Cli.ANSI_BLUE+"\n\nNumero de registros encontrados = " + (c+1)+Cli.ANSI_RESET);
        System.out.println(Cli.ANSI_BLUE+"Tiempo de ejecucion total: " + (double)((t2-t1)/1000) + " segundos." + Cli.ANSI_RESET);
    }

    public void buscarRegistroGenderDate(Examples[] registro, String gender, String date){
        Examples[] resultado = new Examples[registro.length];
        System.out.println(Cli.ANSI_BLUE+"\n\nBuscando registros con Date of stop = "+date+" & Gender = "+gender+", espere..."+Cli.ANSI_RESET);
        long t1 = System.currentTimeMillis();
        int c=0;
        for (int index=0; index<registro.length; ++index){
            try {
                if((registro[index].getGender().equalsIgnoreCase(gender)) && (registro[index].getDateOfStop().equals(date))){
                    ++c;
                    resultado[index] = new Examples(
                            registro[index].getDateOfStop(),
                            registro[index].getFatal(),
                            registro[index].getAlcohol(),
                            registro[index].getGender()
                    );
                    System.out.println(
                            c
                            +" Date of stop: " + resultado[index].getDateOfStop()
                            +" Fatal: " + resultado[index].getFatal()
                            +" Alcohol: " + resultado[index].getAlcohol()
                            +" Gender: " + resultado[index].getGender()
                    );
                }
            }catch (NullPointerException e){

            }
        }

        long t2 = System.currentTimeMillis();
        System.out.println(Cli.ANSI_BLUE+"\n\nNumero de registros encontrados = " + (c+1)+Cli.ANSI_RESET);
        System.out.println(Cli.ANSI_BLUE+"Tiempo de ejecucion total: " + (double)((t2-t1)/1000) + " segundos." + Cli.ANSI_RESET);
    }

    public void mostrarRegistrosPorGender(Examples[] registros, String orden)throws NullPointerException{
        int c=0;
        long t1 = System.currentTimeMillis();

        if(orden.equalsIgnoreCase("asc")){
            System.out.println(Cli.ANSI_BLUE+"Ordenamiento por Gender - Modo ascendente"+Cli.ANSI_RESET);
            for(int i=0; i<registros.length-1; ++i){
                for(int j=i+1; j<registros.length; ++j){
                    if((registros[i].getGender().compareTo(registros[j].getGender()))>0){
                        String auxDate = registros[i].getDateOfStop();
                        String auxFatal = registros[i].getFatal();
                        String auxAlcohol = registros[i].getAlcohol();
                        String auxGender = registros[i].getGender();

                        registros[i].setDateOfStop(registros[j].getDateOfStop());
                        registros[j].setDateOfStop(auxDate);

                        registros[i].setFatal(registros[j].getFatal());
                        registros[j].setFatal(auxFatal);

                        registros[i].setAlcohol(registros[j].getAlcohol());
                        registros[j].setAlcohol(auxAlcohol);

                        registros[i].setGender(registros[j].getGender());
                        registros[j].setGender(auxGender);
                    }
                }
                c++;
            }
        }else if(orden.equalsIgnoreCase("desc")){
            System.out.println(Cli.ANSI_BLUE+"Ordenamiento por Gender - Modo descendente"+Cli.ANSI_RESET);
            for(int i=0; i<registros.length-1; ++i){
                for(int j=i+1; j<registros.length; ++j){
                    if((registros[j].getGender().compareTo(registros[i].getGender()))>0){
                        String auxDate = registros[i].getDateOfStop();
                        String auxFatal = registros[i].getFatal();
                        String auxAlcohol = registros[i].getAlcohol();
                        String auxGender = registros[i].getGender();

                        registros[i].setDateOfStop(registros[j].getDateOfStop());
                        registros[j].setDateOfStop(auxDate);

                        registros[i].setFatal(registros[j].getFatal());
                        registros[j].setFatal(auxFatal);

                        registros[i].setAlcohol(registros[j].getAlcohol());
                        registros[j].setAlcohol(auxAlcohol);

                        registros[i].setGender(registros[j].getGender());
                        registros[j].setGender(auxGender);
                    }
                }
                c++;
            }
        }

        for (int index=0; index<registros.length; ++index){
            System.out.println(index+
                 " Date of stop: " + registros[index].getDateOfStop()
                +" Fatal: " + registros[index].getFatal()
                +" Alcohol: " + registros[index].getAlcohol()
                +" Gender: " + registros[index].getGender()
            );
        }

        long t2 = System.currentTimeMillis();
        System.out.println(Cli.ANSI_BLUE+"\n\nNumero de registros ordenados = " + (c+1)+Cli.ANSI_RESET);
        System.out.println(Cli.ANSI_BLUE+"Tiempo de ejecucion total: " + (double)((t2-t1)/1000) + " segundos." + Cli.ANSI_RESET);
    }

    public void mostrarRegistrosPorDate(Examples[] registros, String orden){
        int c=0;
        long t1 = System.currentTimeMillis();

        if(orden.equalsIgnoreCase("asc")){
            System.out.println(Cli.ANSI_BLUE+"Ordenamiento por Date - Modo ascendente"+Cli.ANSI_RESET);
            for(int i=0; i<registros.length-1; ++i){
                for(int j=i+1; j<registros.length; ++j){
                    if((registros[i].getDateOfStop().compareTo(registros[j].getDateOfStop()))>0){
                        String auxDate = registros[i].getDateOfStop();
                        String auxFatal = registros[i].getFatal();
                        String auxAlcohol = registros[i].getAlcohol();
                        String auxGender = registros[i].getGender();

                        registros[i].setDateOfStop(registros[j].getDateOfStop());
                        registros[j].setDateOfStop(auxDate);

                        registros[i].setFatal(registros[j].getFatal());
                        registros[j].setFatal(auxFatal);

                        registros[i].setAlcohol(registros[j].getAlcohol());
                        registros[j].setAlcohol(auxAlcohol);

                        registros[i].setGender(registros[j].getGender());
                        registros[j].setGender(auxGender);
                    }
                }
                c++;
            }
        }else if(orden.equalsIgnoreCase("desc")){
            System.out.println(Cli.ANSI_BLUE+"Ordenamiento por Date - Modo descendente"+Cli.ANSI_RESET);
            for(int i=0; i<registros.length-1; ++i){
                for(int j=i+1; j<registros.length; ++j){
                    if((registros[j].getDateOfStop().compareTo(registros[i].getDateOfStop()))>0){
                        String auxDate = registros[i].getDateOfStop();
                        String auxFatal = registros[i].getFatal();
                        String auxAlcohol = registros[i].getAlcohol();
                        String auxGender = registros[i].getGender();

                        registros[i].setDateOfStop(registros[j].getDateOfStop());
                        registros[j].setDateOfStop(auxDate);

                        registros[i].setFatal(registros[j].getFatal());
                        registros[j].setFatal(auxFatal);

                        registros[i].setAlcohol(registros[j].getAlcohol());
                        registros[j].setAlcohol(auxAlcohol);

                        registros[i].setGender(registros[j].getGender());
                        registros[j].setGender(auxGender);
                    }
                }
                c++;
            }
        }

        for (int index=0; index<registros.length; ++index){
            System.out.println(index+
                     " Date of stop: " + registros[index].getDateOfStop()
                    +" Fatal: " + registros[index].getFatal()
                    +" Alcohol: " + registros[index].getAlcohol()
                    +" Gender: " + registros[index].getGender()
            );
        }

        long t2 = System.currentTimeMillis();
        System.out.println(Cli.ANSI_BLUE+"\n\nNumero de registros ordenados = " + (c+1)+Cli.ANSI_RESET);
        System.out.println(Cli.ANSI_BLUE+"Tiempo de ejecucion total: " + (double)((t2-t1)/1000) + " segundos."+Cli.ANSI_RESET);
    }

public void mostrarRegistrosPorFatal(Examples[] registros, String orden){
        int c=0;
        long t1 = System.currentTimeMillis();

        if(orden.equalsIgnoreCase("asc")){
            System.out.println(Cli.ANSI_BLUE+"Ordenamiento por Fatal - Modo ascendente"+Cli.ANSI_RESET);
            for(int i=0; i<registros.length-1; ++i){
                for(int j=i+1; j<registros.length; ++j){
                    try {
                        if((registros[i].getFatal().compareTo(registros[j].getFatal()))>0){
                            String auxDate = registros[i].getDateOfStop();
                            String auxFatal = registros[i].getFatal();
                            String auxAlcohol = registros[i].getAlcohol();
                            String auxGender = registros[i].getGender();

                            registros[i].setDateOfStop(registros[j].getDateOfStop());
                            registros[j].setDateOfStop(auxDate);

                            registros[i].setFatal(registros[j].getFatal());
                            registros[j].setFatal(auxFatal);

                            registros[i].setAlcohol(registros[j].getAlcohol());
                            registros[j].setAlcohol(auxAlcohol);

                            registros[i].setGender(registros[j].getGender());
                            registros[j].setGender(auxGender);
                        }else{}
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage()); break;
                    }
                }
                c++;
            }
        }else if(orden.equalsIgnoreCase("desc")){
            System.out.println(Cli.ANSI_BLUE+"Ordenamiento por Fatal - Modo descendente"+Cli.ANSI_RESET);
            for(int i=0; i<registros.length-1; ++i){
                for(int j=i+1; j<registros.length; ++j){
                    try {
                        if((registros[j].getFatal().compareTo(registros[i].getFatal()))>0){
                            String auxDate = registros[i].getDateOfStop();
                            String auxFatal = registros[i].getFatal();
                            String auxAlcohol = registros[i].getAlcohol();
                            String auxGender = registros[i].getGender();

                            registros[i].setDateOfStop(registros[j].getDateOfStop());
                            registros[j].setDateOfStop(auxDate);

                            registros[i].setFatal(registros[j].getFatal());
                            registros[j].setFatal(auxFatal);

                            registros[i].setAlcohol(registros[j].getAlcohol());
                            registros[j].setAlcohol(auxAlcohol);

                            registros[i].setGender(registros[j].getGender());
                            registros[j].setGender(auxGender);
                        }else{}
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage()); break;
                    }
                }
                c++;
            }
        }




        for (int index=0; index<registros.length; ++index){
            System.out.println(index+
                     " Date of stop: " + registros[index].getDateOfStop()
                    +" Fatal: " + registros[index].getFatal()
                    +" Alcohol: " + registros[index].getAlcohol()
                    +" Gender: " + registros[index].getGender()
            );
        }

        long t2 = System.currentTimeMillis();
        System.out.println(Cli.ANSI_BLUE+"\n\nNumero de registros ordenados = " + (c+1)+Cli.ANSI_RESET);
        System.out.println(Cli.ANSI_BLUE+"Tiempo de ejecucion total: " + (double)((t2-t1)/1000) + " segundos."+Cli.ANSI_RESET);
    }
}
    
