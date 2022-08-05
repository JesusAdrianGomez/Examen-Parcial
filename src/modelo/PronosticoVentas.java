/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author gomex
 */
public class PronosticoVentas {
    
    private ArrayList<Double> porcentajesVariacion;
    
    public PronosticoVentas()
    {
        porcentajesVariacion = new ArrayList<Double>();
    }
    
    public double calcularResta(double actual, double anterior)
    {
        double resultado =0;
        return resultado = actual-anterior;
    }
    
    public double calcularVariacion(double actual, double anterior)
    {
        double resultado = 0;
        resultado = calcularResta(actual, anterior)/anterior;
        porcentajesVariacion.add(resultado);
        return resultado;
    }
    
    public double calcularSuma()
    {
        double suma=0;
        for(int i=0; i<porcentajesVariacion.size();i++)
        {
            suma+=porcentajesVariacion.get(i);
        }
        
        return suma;
    }
    
    public double promedioSum()
    {
        return calcularSuma()/porcentajesVariacion.size()-1;
    }
    
    public void borrarVaricion(int i)
    {
        porcentajesVariacion.remove(i);
    }
    
    public void limpiarVariacion()
    {
        porcentajesVariacion.clear();
    }
    
}
