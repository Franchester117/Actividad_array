package edu.cecar.vista;

import edu.cecar.componentes.singletons.ControladorExampleSingleton;
import edu.cecar.modelo.Examples;
import java.util.Scanner;

public class Cli {
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {

        System.out.println(ANSI_RED+"Subiendo informacion a memoria principal"+ANSI_RESET);
        Scanner e = new Scanner(System.in);
        String entrada="";
        int opc=0;
        int n=0;
        Examples[] array;
        n=ControladorExampleSingleton.getcontroladorExamples().numeroRegistro();                   
        array=new Examples[n];
        array=ControladorExampleSingleton.getcontroladorExamples().obtenerRegistros(n);
        
        do {
            System.out.println();

            System.out.print(
                    "---OPERACIONES CON UN ARRAY ESTATICO---\nMenu de opciones:\n"
                    +"1. Buscar registros por Date.\n"
                    +"2. Buscar registros por Gender. \n"
                    +"3. Buscar registros por Date & Gender. \n"
                    +"4. Mostrar registros ordenados por Gender. \n"
                    +"5. Mostrar registros ordenados por Date. \n"
                    +"6. Salir del programa.\nopcion>"
            );

            try {
                opc=Integer.parseInt(entrada = e.nextLine());
            }catch (Exception ex){
                System.out.println("Error -> no ingreso ningun numero, vuelva a intentarlo: " + ex.getMessage());
            }

            switch (opc){
                case 1:                    
                    System.out.print("\n\nEscriba la fecha en formato [dd/mm/yyyy]>"); entrada=e.nextLine();
                    ControladorExampleSingleton.getcontroladorExamples().buscarRegistrosDate(array, entrada);
                break;
                case 2:
                    entrada="";
                    System.out.print("\n\nEscriba el genero [F o M]>"); entrada=e.nextLine();                                                            
                    ControladorExampleSingleton.getcontroladorExamples().buscarregistroGender(array, entrada);
                break;
                case 3:
                    String gender="", date="";
                    System.out.println("\n\nEscriba el genero [F o M] & la fecha en formato [dd/mm/yyyy]>");
                    System.out.print("genero>"); gender=e.nextLine(); System.out.println("date>"); date=e.nextLine();
                    ControladorExampleSingleton.getcontroladorExamples().buscarRegistroGenderDate(array, gender, date);
                break;
                case 4:
                    String ordenG="";
                    System.out.print("\n\nEscriba asc o desc para organizar por Gender>");ordenG=e.nextLine();
                    ControladorExampleSingleton.getcontroladorExamples().mostrarRegistrosPorGender(array,ordenG);
                break;
                case 5:
                    String ordenD="";
                    System.out.print("\n\nEscriba asc o desc para organizar por Date>");ordenD=e.nextLine();
                    ControladorExampleSingleton.getcontroladorExamples().mostrarRegistrosPorDate(array,ordenD);
                break;
            }
        }while(opc!=6);
    }
}
