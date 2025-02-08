/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sol_AD03;

import Controlador.CtrlMatriculas;
import Modelo.Consultas;
import Modelo.Estudiante;
import Vista.vistaMatriculas;

/**
 *
 * @author Usuario
 */
public class Sol_AD03 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Crear los objetos de las clases necesarios para trabajar en la gestión de los datos.
         Estudiante estudiante = new Estudiante();
         Consultas consul = new Consultas();
         vistaMatriculas mVista = new vistaMatriculas();
         
         //Creamos la estructura necesaria para cosntruir nuestra vista y trabajar con ella.
         CtrlMatriculas ctrl = new CtrlMatriculas(estudiante, consul, mVista);
         ctrl.iniciar();
         ctrl.listarUniversidades(consul.obtenerUniversidades());
         ctrl.listarEstudiantes(consul.obtenerEstudiantes());
         mVista.setVisible(true); 
    }
    
}
