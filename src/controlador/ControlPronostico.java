/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.sun.nio.file.ExtendedWatchEventModifier;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.PronosticoVentas;
import vista.VentanaPronostico;

/**
 *
 * @author gomex
 */
public class ControlPronostico {
    
    private PronosticoVentas modelo;
    private  VentanaPronostico vista;
    private ArrayList<Double> ventasHistorico;
    
    public ControlPronostico(VentanaPronostico vista, PronosticoVentas modelo)
    {
        this.modelo = modelo;
        this.vista = vista;
        
        ManejadorDeEventos listener = new ManejadorDeEventos();
        
        this.vista.btnAgregarAddActionListener(listener);
        this.vista.btnBorrarAddActionListener(listener);
        this.vista.btnModificaAddActionListener(listener);
        this.vista.btnNuevoAddActionListener(listener);
        
        for (int i = 0; i < this.vista.numFilasHistorico(); i++) {
            ventasHistorico.add(this.vista.getVentaHistorico(i+1));
        }
    }
    private void reHcerHistorico()
    {
        Object[][] registroH = new Object[ventasHistorico.size()+1][4];
            for(int i=1;i<=ventasHistorico.size();i++)
            {
                registroH[i][1]= i;
                registroH[i][3]= ventasHistorico.get(i-1);
                registroH[i][4]= null;
                registroH[i][5]= null;

            }
            registroH[ventasHistorico.size()+1][1]=null;
            registroH[ventasHistorico.size()+1][2]=null;
            registroH[ventasHistorico.size()+1][3]="Total: ";
            registroH[ventasHistorico.size()+1][4]=null;
            
         vista.llenarHistorico(registroH);
    }
    private void agregar()
    {
        if(vista.numFilasHistorico()>2)
        {
            ventasHistorico.add(vista.obtenerTotalHistorico());
            reHcerHistorico();
            hacerCuentas();
        }
        else
        {
            vista.mensaje("No se puede hacer esta acción porque no hay suficientes años en el Historico de ventas");
        }
    }
    
    private void borrar()
    {
        int fila = vista.filaSeleccionada();
        if(fila == -1)
        {
            vista.mensaje("No hay ninguna fila seleccionada. Si quieres borrar, seleciona una fila e intentalo de nuevo");
        }
        else
        {
            vista.quitarFilaHistorico(fila);
            ventasHistorico.remove(fila-1);
            reHcerHistorico();
            hacerCuentas();
        }
    }
    
    private void modificar()
    {
        int fila = vista.filaSeleccionada();
        if(fila == -1)
        {
            vista.mensaje("No hay ninguna fila seleccionada. Si quieres que el valor de venta se actualize, seleciona una fila e intentalo de nuevo");
        }
        else
        {
            hacerCuentas();
        }
    }
    
    private void nuevo()
    {
        vista.setCantidadVenta("");
        vista.setCantidad("");
        vista.setPromedio("");
        vista.limpiarHistorico();
        vista.limpiarPronostico();
        //quizá es mejor idea cerrar y volver a instanciar
    }
    
    private void hacerCuentas()
    {
        Object[][] cuentasHistorico= new Object[ventasHistorico.size()+1][4];
        for (int i = 2; i <= ventasHistorico.size(); i++) 
        {
            double actual = vista.getVentaHistorico(i);
            double anterior = vista.getVentaHistorico(i-1);
            
            cuentasHistorico[i][3] = modelo.calcularResta(actual, anterior);
            cuentasHistorico[i][4] = modelo.calcularVariacion(actual, anterior);         
        }
        cuentasHistorico[ventasHistorico.size()+1][3]="Total: ";
        cuentasHistorico[ventasHistorico.size()+1][4]= modelo.calcularSuma();
        
        double promedioSum = modelo.promedioSum();
        vista.setPromedio(promedioSum+"");
        int filas = Integer.parseInt(vista.getCantidad());
        
        Object[][] cuentasPronostico = new Object[filas][2];
        cuentasPronostico [1][2]= Double.parseDouble(vista.getCantidadVenta());
        for (int i = 1; i <= filas; i++) 
        {
            double anterior = (double)cuentasPronostico[i-1][2];
            cuentasPronostico[i][1]= i;
            cuentasPronostico[i][2]= anterior+anterior*promedioSum;
        }
        vista.llenarHistorico(cuentasHistorico);
        vista.llenarPronostico(cuentasPronostico);
    }
    
    private class ManejadorDeEventos implements ActionListener
    {
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            System.out.println("asdf");
            if(vista.getCantidad().trim().equals(""))
            {
                vista.mensaje("el campo de los años a pronosticar esta vacio");
            }else
            {
                if(Integer.parseInt(vista.getCantidad())<2)
            {
                vista.mensaje("el numero de años de pronostico debe de ser por lo menos 2");
            }
            else
            {
                if(e.getActionCommand().equalsIgnoreCase("Agregar Año"))
                {
                    agregar();
                }
                if(e.getActionCommand().equalsIgnoreCase("Borrar Año"))
                {
                    borrar();
                }
                if(e.getActionCommand().equalsIgnoreCase("Modifica Año"))
                {
                    modificar();
                }
                if(e.getActionCommand().equalsIgnoreCase("Nuevo Pronostico"))
                {
                    nuevo();
                }
            }
            }
            
            
        }
    }
}
