/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gomex
 */
public class VentanaPronostico extends JFrame{
    
    private Container panel;
    
    private JLabel lblDatosVenta;
    private JLabel lblCantidadVenta;
    private JTextField txtCantidadVenta;
    
    private JLabel lblAgnosAPronostricar;
    private JLabel lblCantidad;
    private JTextField txtCantidad;
    
    private JLabel lblControloes;
    private JButton btnAgregar;
    private JButton btnBorrar;
    private JButton btnModifica;
    private JButton btnNuevo;
    
    private JLabel lblHistoricoVentas;
    private JTable tblHistoricoVentas;
    private Object [][] datosHistorico;
    
    private JLabel lblPronostico;
    private JLabel lblPromedioVariacion;    
    private JTextField txtPromedioVariacion;
    private JTable tblPronostico;
    private Object[][] datosPronostico;

    public VentanaPronostico() throws HeadlessException 
    {
        iniciarComponentes();
        setVisible(true);
        setSize(500,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    @SuppressWarnings("empty-statement")
    private void iniciarComponentes()
    {
        panel = new JPanel();
        panel.setBounds(0,0,500,700);
        panel.setBackground(Color.white);
        panel.setLayout(null);
        
        lblDatosVenta = new JLabel("Datos de Venta");
        lblDatosVenta.setBounds(10,10,100,20);
        
        lblCantidadVenta = new JLabel("Cantidad de Venta:");
        lblCantidadVenta.setBounds(10,40,120,20);
        
        txtCantidadVenta = new JTextField();
        txtCantidadVenta.setBounds(130,40,100,20);
        
        lblAgnosAPronostricar = new JLabel("Años a Pronosticar");
        lblAgnosAPronostricar.setBounds(10,85,120,20);
        
        lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(10,115,100,20);
        
        txtCantidad = new JTextField();
        txtCantidad.setBounds(110,115,100,20);
        
        lblControloes = new JLabel("Controles");
        lblControloes.setBounds(350,10,100,20);
        
        btnAgregar = new JButton("Agregar Año");
        btnAgregar.setBounds(350,40,110,20);
        
        btnBorrar = new JButton("Borrar Año");
        btnBorrar.setBounds(350,65,110,20);
        
        btnModifica = new JButton("Modifica Año");
        btnModifica.setBounds(350,90,110,20);
        
        btnNuevo = new JButton("Nuevo Pronostico");
        btnNuevo.setBounds(350,115,110,20);
        
        lblHistoricoVentas = new JLabel("Historico de Ventas");
        lblHistoricoVentas.setBounds(30,180,150,20);
        
        datosHistorico = new Object[][]
        {
            {1,220,null,null},
            {2,240,null,null},
            {3,255,null,null},
           {null,null,"Total: ",null}
        };
        tblHistoricoVentas = new JTable();
        tblHistoricoVentas.setBounds(10,210,460,180);
        llenarHistorico(datosHistorico);
        

        
        lblPronostico = new JLabel("Pronostico deVentas");
        lblPronostico.setBounds(30,400,150,20);
        
        lblPromedioVariacion = new JLabel("promedio de Variación");
        lblPromedioVariacion.setBounds(10,430,150,20);
        
        txtPromedioVariacion = new JTextField();
        txtPromedioVariacion.setBounds(160,430,100,20);
        txtPromedioVariacion.setEditable(false);
        
        datosPronostico = new Object[][]
        {
            {null,null},
            {null,null},
            {null,null},
            {null,null}
        };
        Object[] nombresP = new Object[]{"Año","Pronóstico Venta"};
        tblPronostico = new JTable(datosPronostico, nombresP);
        tblPronostico.setBounds(50,460,390,200);     
        
        llenarPronostico(datosPronostico);
        
        
        panel.add(lblDatosVenta);
        panel.add(lblCantidadVenta);
        panel.add(txtCantidadVenta);
        panel.add(lblAgnosAPronostricar);
        panel.add(lblCantidad);
        panel.add(txtCantidad);
        panel.add(lblControloes);
        panel.add(btnAgregar);
        panel.add(btnBorrar);
        panel.add(btnModifica);
        panel.add(btnNuevo);
        panel.add(lblHistoricoVentas);
        panel.add(tblHistoricoVentas);
        panel.add(lblPronostico);
        panel.add(lblPromedioVariacion);
        panel.add(txtPromedioVariacion);
        panel.add(tblPronostico);
        add(panel);
    }
    
    public void llenarHistorico (Object[][] registros)
    {
        tblHistoricoVentas.setModel(new DefaultTableModel(
            registros,new String [] {"Año","Cantidad de ventas", "Yn- Yn-1","(Yn- Yn-1)/(Yn-1)"}
        )
        {
            boolean[] puedoeditar = new boolean [] {
                false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return puedoeditar [columnIndex];
            }
        });
    
    }
    
    public void llenarPronostico (Object[][] registros)
    {
        tblPronostico.setModel(new DefaultTableModel(
            registros,new String [] {"Año","Pronóstico Venta"}
        )
        {
            boolean[] puedoeditar = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return puedoeditar [columnIndex];
            }
        });
    
    }  
    
    public void setCantidadVenta(String cantidadVenta)
    {
        txtCantidadVenta.setText(cantidadVenta);
    }
    
    public String getCantidadVenta()
    {
        return txtCantidadVenta.getText();        
    }
    
    public void setCantidad(String cantidad)
    {
        txtCantidad.setText(cantidad);
    }
    
    public String getCantidad()
    {
        return txtCantidad.getText();
    }
    
    public void setPromedio(String promedio)
    {
        txtPromedioVariacion.setText(promedio);
    }
    
    public String getPromedio()
    {
        return txtPromedioVariacion.getText();
    }
    
    public void btnAgregarAddActionListener(ActionListener listener)
    {
        btnAgregar.addActionListener(listener);
    }
    public void btnBorrarAddActionListener(ActionListener listener)
    {
        btnBorrar.addActionListener(listener);
    }
    public void btnModificaAddActionListener(ActionListener listener)
    {
        btnModifica.addActionListener(listener);
    }
    public void btnNuevoAddActionListener(ActionListener listener)
    {
        btnNuevo.addActionListener(listener);
    }
    
    public int filaSeleccionada()
    {
        return tblHistoricoVentas.getSelectedRow();
    }
    
    public void quitarFilaHistorico(int i)
    {
        tblHistoricoVentas.removeRowSelectionInterval(i-1, i);
    }
    
    public double getVentaHistorico()
    {
        return (double)tblHistoricoVentas.getValueAt(tblHistoricoVentas.getSelectedRow(), 2);        
    }
    
    public double getVentaHistorico(int i)
    {
        return Double.parseDouble((String) tblHistoricoVentas.getValueAt(i, 2));
    }
    public void limpiarHistorico()
    {        
        tblHistoricoVentas.removeRowSelectionInterval(2,tblHistoricoVentas.getRowCount());      
    }
    
    public void limpiarPronostico()
    {
        tblPronostico.removeRowSelectionInterval(1, tblPronostico.getRowCount());
    }
    
    public int numFilasHistorico()
    {
        System.out.println(tblHistoricoVentas.getRowCount());
        return tblHistoricoVentas.getRowCount();
        
    }
    
    public double obtenerTotalHistorico()
    {
        return  Double.parseDouble((String) tblHistoricoVentas.getValueAt(tblHistoricoVentas.getRowCount(),4));
    }
    
    public double getCelda(int fila, int columna)
    {
        return (double)tblHistoricoVentas.getValueAt(fila, columna);
    }
    
    public void setCelda(double valor, int fila, int columna)
    {
        tblHistoricoVentas.setValueAt(valor, fila, columna);
    }
    
    public void mensaje(String mensaje)
    {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
