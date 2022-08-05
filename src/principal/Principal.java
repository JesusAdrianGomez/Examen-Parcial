/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import controlador.ControlPronostico;
import modelo.PronosticoVentas;
import vista.VentanaPronostico;

/**
 *
 * @author gomex
 */
public class Principal {
    
    public static void main(String[] args) {
        
        VentanaPronostico ventana = new VentanaPronostico();
        PronosticoVentas p = new PronosticoVentas();
        ControlPronostico controlPronostico = new ControlPronostico(ventana,p);
    }
}
