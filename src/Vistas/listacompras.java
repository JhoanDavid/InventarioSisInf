/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Vistas;


import Controladores.ProductoJpaController;
import Controladores.MovimientoJpaController;
import Controladores.ProductoMovimientoJpaController;
import Entidades.Producto;
import Entidades.Movimiento;
import Entidades.ProductoMovimiento;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author esteban
 */
public class listacompras extends javax.swing.JFrame {
    MovimientoJpaController controlmovimiento = new MovimientoJpaController();
    ProductoMovimientoJpaController controlproductomovimiento = new ProductoMovimientoJpaController();
    ProductoJpaController controlproducto = new ProductoJpaController();
    Movimiento movimiento = new Movimiento();
    Producto producto = new Producto();
    ProductoMovimiento productomovimiento = new ProductoMovimiento();
    DefaultTableModel modeloCarrito;
    List<Movimiento> listaMovimiento;
    List<ProductoMovimiento> listaProductoMovimiento;
    List<Producto> listaProducto;
    DefaultTableModel modelo;

    
    public listacompras() {
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
        listaMovimiento = controlmovimiento.findMovimientoEntities();
      for (Movimiento obj : listaMovimiento) {
          if (obj.getTipoMov().contains("Compra")) {
              modelo.addRow(new Object[]{obj.getId(), obj.getFechaMovimiento(), obj.getDescripcion(), obj.getIdRemitente(),
                  obj.getUsuarioTrans()});
          }
      }
      calcularTotalCompras();
    }

  public void agregarProductoCarrito(){
      double valorTotal= 0;
        int id_movi= new Integer(tablaProductoInventario.getValueAt(tablaProductoInventario.getSelectedRow(),0).toString());
       listaProductoMovimiento=controlproductomovimiento.findProductoMovimientoEntities();
        for(ProductoMovimiento obj:listaProductoMovimiento){  
        if(obj.getIdMov().getId() == id_movi){
        modeloCarrito.addRow(new Object[]{obj.getIdProducto().getId(), obj.getIdProducto().getDescripcion(), obj.getCantTrans(),
        obj.getValorTrans()});
        }}
        for (int i = 0; i < tablaCarritoVenta.getColumnCount(); i++) {
       double valor=(double)tablaCarritoVenta.getValueAt(i, 3)+valorTotal; 
       valorTotal= (valor);
       txtTotal.setText(String.valueOf(valorTotal));
  }}
  
    public void quitarProductoCarrito(){
       double valor=(double)tablaCarritoVenta.getValueAt(tablaCarritoVenta.getSelectedRow(),3);
       double valorTotal=new Double(txtTotal.getText());
       txtTotal.setText(String.valueOf(valorTotal-valor));
       modeloCarrito.removeRow(tablaCarritoVenta.getSelectedRow());
  }
  
  
  public void filtrarTabla(){
      limpiarTabla();
      if (txtBusqueda.getText().equals("")) {
            limpiarTabla();
            LlenarTabla();

        } else {
            limpiarTabla();
            DefaultTableModel modelo = (DefaultTableModel) tablaProductoInventario.getModel();
            for(Movimiento obj:listaMovimiento){
          if (obj.getTipoMov().contains("Compra") && obj.getId().toString().contains(txtBusqueda.getText())) {
        modelo.addRow(new Object[]{obj.getId(), obj.getFechaMovimiento(), obj.getDescripcion(), obj.getIdRemitente(),
        obj.getUsuarioTrans()});
          }
        }
      
        }
  }
  
  public void limpiarTabla(){
        int a =modelo.getRowCount()-1;
        for(int i=a; i>=0; i--){
        modelo.removeRow(i );
        }
  }
  
  public void limpiarTablaProductos(){
        int a =modeloCarrito.getRowCount()-1;
        for(int i=a; i>=0; i--){
        modeloCarrito.removeRow(i );
        }
  }
  

    public String getFechaActual() {
    Date fechaActual = new Date();
    SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
    return formateador.format(fechaActual);
}
    public void calcularTotalCompras(){
        double ValorTotalCompras = 0;
        double valorTotal = 0;
        double total = 0;
        listaMovimiento=controlmovimiento.findMovimientoEntities();
        for (int i = 0; i < controlmovimiento.getMovimientoCount(); i++) {
        for (Movimiento obj1:listaMovimiento) {                  
        listaProductoMovimiento=controlproductomovimiento.findProductoMovimientoEntities();
        for(ProductoMovimiento obj:listaProductoMovimiento){  
        if(obj.getIdMov().getId() == obj1.getId() && obj1.getTipoMov().contains("Compra")){ 
        for (int j = 0; j < 1; j++) {    
        valorTotal= obj.getValorTrans()+ValorTotalCompras;
        ValorTotalCompras=valorTotal;
        total = ValorTotalCompras/controlmovimiento.getMovimientoCount();
        txtTotalCompras.setText(String.valueOf(total));
        }}
        }
        
        
        }
       
  }}
    
  
 
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProductoInventario = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTotalCompras = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaCarritoVenta = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        cancelar = new javax.swing.JButton();
        txtFecha = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 400));

        tablaProductoInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "fecha", "descripcion", "id_remitente", "usuario_trans"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductoInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductoInventarioMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProductoInventario);
        if (tablaProductoInventario.getColumnModel().getColumnCount() > 0) {
            tablaProductoInventario.getColumnModel().getColumn(0).setMaxWidth(60);
            tablaProductoInventario.getColumnModel().getColumn(1).setMinWidth(100);
            tablaProductoInventario.getColumnModel().getColumn(2).setMinWidth(100);
            tablaProductoInventario.getColumnModel().getColumn(2).setMaxWidth(100);
            tablaProductoInventario.getColumnModel().getColumn(3).setMaxWidth(60);
        }

        jLabel2.setText("codigo de compra");

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

        jLabel3.setText("Total de todas las compras:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Listado de Compras");

        txtTotalCompras.setEditable(false);
        txtTotalCompras.setText("0");
        txtTotalCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalComprasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(16, 16, 16)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
                        .addGap(21, 21, 21))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(jLabel9)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        tablaCarritoVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "producto", "cantidad", "valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablaCarritoVenta);
        if (tablaCarritoVenta.getColumnModel().getColumnCount() > 0) {
            tablaCarritoVenta.getColumnModel().getColumn(0).setMinWidth(60);
            tablaCarritoVenta.getColumnModel().getColumn(0).setPreferredWidth(60);
            tablaCarritoVenta.getColumnModel().getColumn(0).setMaxWidth(60);
        }

        jLabel11.setText("Total de compra:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Productos de la Compra");

        txtTotal.setEditable(false);
        txtTotal.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jLabel8)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        cancelar.setText("cancelar");
        cancelar.setMaximumSize(new java.awt.Dimension(61, 23));
        cancelar.setMinimumSize(new java.awt.Dimension(61, 23));
        cancelar.setPreferredSize(new java.awt.Dimension(61, 23));
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        txtFecha.setText("00-00-2020");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Reporte de Compras");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(386, 386, 386)
                .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFecha)
                .addGap(274, 274, 274)
                .addComponent(jLabel1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        InicioAdmonSupremo i=new InicioAdmonSupremo();
        i.setVisible(true);
        this.dispose();            // TODO add your handling code here:
    }//GEN-LAST:event_cancelarActionPerformed

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed

    }//GEN-LAST:event_txtBusquedaKeyPressed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        filtrarTabla();// TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaKeyTyped

    private void tablaProductoInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoInventarioMouseClicked
        //txtCantidad.setText("1");
        
        limpiarTablaProductos();
        txtTotal.setText(String.valueOf(0));
        agregarProductoCarrito();
        
        
    }//GEN-LAST:event_tablaProductoInventarioMouseClicked

    private void txtTotalComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalComprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalComprasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(listacompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(listacompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(listacompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(listacompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new listacompras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaCarritoVenta;
    private javax.swing.JTable tablaProductoInventario;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JLabel txtFecha;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalCompras;
    // End of variables declaration//GEN-END:variables
}
