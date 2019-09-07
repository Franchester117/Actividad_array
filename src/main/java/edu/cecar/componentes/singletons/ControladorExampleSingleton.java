package edu.cecar.componentes.singletons;

import edu.cecar.controladores.ControladorExamples;

/**
 *  Clase que contiene los métodos para realizar una instancia de un objeto de tipo ControladorExamples.
 **/
public class ControladorExampleSingleton {
    private static ControladorExamples cExamples;
    public static ControladorExamples getcontroladorExamples(){
        if(cExamples==null){
            cExamples = new ControladorExamples();
        }
        return cExamples;
    }
}
