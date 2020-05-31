/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;


import Controladores.ClienteJpaController;
import Controladores.GlobalClass;
import Controladores.MovimientoJpaController;
import Controladores.ProductoJpaController;
import Controladores.ProductoMovimientoJpaController;
import Entidades.Cliente;
import Entidades.Movimiento;
import Entidades.Producto;
import Entidades.ProductoMovimiento;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author esteban
 */
public class compra extends javax.swing.JFrame {
    ProductoJpaController controlproducto = new ProductoJpaController();
    Producto producto = new Producto();
    MovimientoJpaController controlMovimiento = new MovimientoJpaController();
    Movimiento movimientoCompra = new Movimiento();
    DefaultTableModel modeloCarrito;
    List<Producto> listaProducto;
    DefaultTableModel modelo;
    ClienteJpaController controlCliente= new ClienteJpaController();
    Cliente cliente=null;

    
    public compra() {
        initComponents();
        modelo=(DefaultTableModel)tablaProductoInventario.getModel();
        modeloCarrito=(DefaultTableModel)tablaCarritoVenta.getModel();
        txtFecha.setText(getFechaActual());
        setTitle("Inventario SisInf");
        setResizable(false);
        setLocationRelativeTo(null);
        LlenarTabla();
    }


  public void LlenarTabla(){
        listaProducto=controlproducto.findProductoEntities();
        for(Producto obj:listaProducto){
                modelo.addRow(new Object[]{obj.getId(),obj.getDescripcion(),obj.getCantidadStock(),obj.getValorCompra()});
        }
    }

  public boolean agregarProductoCarrito(){
      int id=(int) tablaProductoInventario.getValueAt(tablaProductoInventario.getSelectedRow(),0);
      double cantidad=new Double(txtCantidad.getText());
      double valor=new Double(txtValor.getText());
      double valorTotal=new Double(txtTotal.getText());
      for (int i = 0; i < tablaCarritoVenta.getRowCount(); i++) {
         int idProducto=new Integer(tablaCarritoVenta.getValueAt(i,0).toString());
         if(id==idProducto){
             double stockCarrito=new Double(tablaCarritoVenta.getValueAt(i,2).toString());
             double valorCarrito=new Double(tablaCarritoVenta.getValueAt(i,3).toString());
             tablaCarritoVenta.setValueAt(stockCarrito+cantidad, i, 2);
             double stock=new Double(tablaProductoInventario.getValueAt(tablaProductoInventario.getSelectedRow(),2).toString());
             tablaProductoInventario.setValueAt(stock+cantidad, tablaProductoInventario.getSelectedRow(), 2);
             tablaCarritoVenta.setValueAt(valorCarrito+valor, i, 3);
             txtTotal.setText(String.valueOf(valor+valorTotal));
             return false;
         }
      }
       String descripcion=tablaProductoInventario.getValueAt(tablaProductoInventario.getSelectedRow(),1).toString();   // TODO add your handling code here:
       modeloCarrito.addRow(new Object[]{id, descripcion, cantidad,(valor)});
       txtTotal.setText(String.valueOf(valor+valorTotal));
       double stock=new Double(tablaProductoInventario.getValueAt(tablaProductoInventario.getSelectedRow(),2).toString());
       tablaProductoInventario.setValueAt(stock+cantidad, tablaProductoInventario.getSelectedRow(), 2);
       return true;
  }
  
    public void quitarProductoCarrito(){
       double valor=(double)tablaCarritoVenta.getValueAt(tablaCarritoVenta.getSelectedRow(),3);
       double valorTotal=new Double(txtTotal.getText());
       txtTotal.setText(String.valueOf(valorTotal-valor));
       double cantidad=new Double(tablaCarritoVenta.getValueAt(tablaCarritoVenta.getSelectedRow(),2).toString());
       double stock=new Double(tablaProductoInventario.getValueAt(tablaProductoInventario.getSelectedRow(),2).toString());
       int id=new Integer(tablaCarritoVenta.getValueAt(tablaCarritoVenta.getSelectedRow(),0).toString());
       for (int i = 0; i < tablaProductoInventario.getRowCount(); i++) {
           int idProducto=new Integer(tablaProductoInventario.getValueAt(i,0).toString());
           if(idProducto==id){
               tablaProductoInventario.setValueAt(stock-cantidad, i, 2);
           }
       }
       modeloCarrito.removeRow(tablaCarritoVenta.getSelectedRow());
  }
  
  
  public void filtrarTabla(){
      limpiarTabla();
      for(Producto obj:listaProducto){
            if(obj.getDescripcion().contains(txtBusqueda.getText())){
                modelo.addRow(new Object[]{obj.getId(),obj.getDescripcion(),obj.getCantidadStock(),obj.getValorVenta()});
            }
        }
  }
  
  public void limpiarTabla(){
        int a =modelo.getRowCount()-1;
        for(int i=a; i>=0; i--){
        modelo.removeRow(i );
        }
  }
  
  public void calcularValor(){
        double valor=new Double(tablaProductoInventario.getValueAt(tablaProductoInventario.getSelectedRow(), 3).toString());
        double cantidad=new Double(txtCantidad.getText());
        txtValor.setText(String.valueOf(valor*cantidad));
  }
   public void validarVacios(){
        if (txtIdProveedor.getText().equalsIgnoreCase("") || tablaCarritoVenta.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,"Por favor llene todos los campos");
       } else  {
            registrarCompra();
            JOptionPane.showMessageDialog(null, "Compra realizada exitosamente!");
            limpiarTablaCarrito();
        }
  
               
   }
   public void limpiarTablaCarrito(){
        int a =modeloCarrito.getRowCount()-1;
        for(int i=a; i>=0; i--){
        modeloCarrito.removeRow(i );
        }
  }
  
  

  
  
   public boolean validarCantidad(KeyEvent evt){
      char validar = evt.getKeyChar();
      double stock=new Double(tablaProductoInventario.getValueAt(tablaProductoInventario.getSelectedRow(), 2).toString());
      double cantidad=new Double(txtCantidad.getText()+validar);
      if(cantidad>stock){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Esta cantidad no se encuentra en inventario");
            return false;
     }
      return true;
   }
  
  
  
  public void registrarCompra(){
      Date fechaActual = new Date();
      movimientoCompra.setFechaMovimiento(fechaActual);
      movimientoCompra.setDescripcion(txtDescripcion.getText());
      movimientoCompra.setTipoMov("Compra");
      movimientoCompra.setIdRemitente(new Integer(txtIdProveedor.getText()));
      movimientoCompra.setIdDestino(new Integer("1234"));
      movimientoCompra.setUsuarioTrans(null);
      controlMovimiento.create(movimientoCompra);
      
      List<Movimiento> lstMovimientos =controlMovimiento.findMovimientoEntities();
      Movimiento ultimoMovimiento=lstMovimientos.get(lstMovimientos.size()-1);
      
      ProductoMovimientoJpaController ControllerPM= new ProductoMovimientoJpaController();
      
      
      for (int i = 0; i < tablaCarritoVenta.getRowCount(); i++) {
          try {
              ProductoMovimiento productoMovimiento =new ProductoMovimiento();
              Producto resultP=controlproducto.findProducto(new Integer(tablaCarritoVenta.getValueAt(i,0).toString()));
              productoMovimiento.setIdProducto(resultP);
              productoMovimiento.setCantTrans(new Double(tablaCarritoVenta.getValueAt(i,2).toString()));
              productoMovimiento.setValorTrans(new Double(tablaCarritoVenta.getValueAt(i,3).toString()));
              productoMovimiento.setIdMov(ultimoMovimiento);
              ControllerPM.create(productoMovimiento);
          } catch (Exception ex) {
              Logger.getLogger(compra.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      for (int i = 0; i < tablaCarritoVenta.getRowCount(); i++) {
          try {
              ProductoMovimiento productoMovimiento =new ProductoMovimiento();
              Producto resultP=controlproducto.findProducto(new Integer(tablaCarritoVenta.getValueAt(i,0).toString()));
              Producto resulTP=controlproducto.findProducto(new Integer(txtValor.getText()));
              productoMovimiento.setIdProducto(resultP);
              productoMovimiento.setCantTrans(new Double(tablaCarritoVenta.getValueAt(i,2).toString()));
              productoMovimiento.setValorTrans(new Double(tablaCarritoVenta.getValueAt(i,3).toString()));
              productoMovimiento.setIdMov(ultimoMovimiento);
              resultP.setCantidadStock(resultP.getCantidadStock()+productoMovimiento.getCantTrans());
              controlproducto.edit(resultP);
          } catch (Exception ex) {
              Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    
  }
  
  
  
  public String getFechaActual() {
    Date fechaActual = new Date();
    SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
    return formateador.format(fechaActual);
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor. 
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProductoInventario = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        txtFecha = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtValor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaCarritoVenta = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtIdProveedor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        crear = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(975, 380));
        setResizable(false);

        cancelar.setText("cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        tablaProductoInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "producto", "cant exist", "valor unidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductoInventario.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaProductoInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductoInventarioMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProductoInventario);
        if (tablaProductoInventario.getColumnModel().getColumnCount() > 0) {
            tablaProductoInventario.getColumnModel().getColumn(0).setMaxWidth(60);
            tablaProductoInventario.getColumnModel().getColumn(2).setMinWidth(70);
            tablaProductoInventario.getColumnModel().getColumn(2).setMaxWidth(70);
            tablaProductoInventario.getColumnModel().getColumn(3).setMinWidth(100);
            tablaProductoInventario.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        jLabel2.setText("Nombre del producto");

        txtBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusquedaActionPerformed(evt);
            }
        });
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        txtFecha.setText("00-00-2020");

        jLabel10.setText("Descripción:");

        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtFecha)))
                        .addGap(0, 80, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFecha)
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelar))
        );

        jButton2.setText("quitar producto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("añadir producto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtValor.setText("0");
        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });

        jLabel4.setText("valor");

        jLabel3.setText("cantidad");

        txtCantidad.setText("0");
        txtCantidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCantidadMouseClicked(evt);
            }
        });
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Compra");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValor, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(11, 11, 11))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaCarritoVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "producto", "cantidad", "valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCarritoVenta.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(tablaCarritoVenta);
        if (tablaCarritoVenta.getColumnModel().getColumnCount() > 0) {
            tablaCarritoVenta.getColumnModel().getColumn(0).setMinWidth(60);
            tablaCarritoVenta.getColumnModel().getColumn(0).setMaxWidth(60);
            tablaCarritoVenta.getColumnModel().getColumn(2).setMinWidth(70);
            tablaCarritoVenta.getColumnModel().getColumn(2).setMaxWidth(70);
            tablaCarritoVenta.getColumnModel().getColumn(3).setMinWidth(100);
            tablaCarritoVenta.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        jLabel7.setText("Id Proveedor");

        jLabel11.setText("Total:");

        txtTotal.setText("0");

        crear.setText("Crear compra");
        crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearActionPerformed(evt);
            }
        });

        jLabel12.setText("productos a comprar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(crear)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(83, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal)
                    .addComponent(jLabel11))
                .addGap(26, 26, 26)
                .addComponent(crear))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void crearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearActionPerformed
        validarVacios();
        txtIdProveedor.setText("");    
        limpiarTabla();
        LlenarTabla();
                 // TODO add your handling code here:
    }//GEN-LAST:event_crearActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
    InicioAdmonSupremo i=new InicioAdmonSupremo();
        i.setVisible(true);
        this.dispose();            // TODO add your handling code here:
    }//GEN-LAST:event_cancelarActionPerformed

    private void tablaProductoInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoInventarioMouseClicked
        txtCantidad.setText("1");
        calcularValor();        
    }//GEN-LAST:event_tablaProductoInventarioMouseClicked

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorActionPerformed

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased

        calcularValor();        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    double cantidad=new Double(txtCantidad.getText());
    double stock=new Double(tablaProductoInventario.getValueAt(tablaProductoInventario.getSelectedRow(),2).toString());
    agregarProductoCarrito();     
    txtCantidad.setText("1");
    txtValor.setText(tablaProductoInventario.getValueAt(tablaProductoInventario.getSelectedRow(), 3).toString());
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    quitarProductoCarrito();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
    // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
            // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtCantidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCantidadMouseClicked
    txtCantidad.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadMouseClicked

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        filtrarTabla();// TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed

    }//GEN-LAST:event_txtBusquedaKeyPressed

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaKeyTyped

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
       
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    try {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(compra.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(compra.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(compra.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedLookAndFeelException ex) {
                        Logger.getLogger(compra.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
    
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new compra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelar;
    private javax.swing.JButton crear;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaCarritoVenta;
    private javax.swing.JTable tablaProductoInventario;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JLabel txtFecha;
    private javax.swing.JTextField txtIdProveedor;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
