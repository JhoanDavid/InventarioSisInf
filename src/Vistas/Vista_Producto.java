/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.ProductoJpaController;
import Entidades.Producto;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author warriors
 */
public class Vista_Producto extends javax.swing.JFrame {

    ProductoJpaController controlproducto = new ProductoJpaController();
    Producto producto = new Producto();
    DefaultTableModel modelo2;
    List<Producto> listaProducto;

    public Vista_Producto() {
        initComponents();
        setTitle("Inventario SisInf");
        setResizable(false);
        setLocationRelativeTo(null);
        LlenarTabla();
        TextPrompt texto = new TextPrompt("Buscar Producto", buscar);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        btn_agregar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_Inhabilitar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtdesc = new javax.swing.JTextField();
        txtcantidad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbx_unidad = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtvalor_compra = new javax.swing.JTextField();
        txtvalor_venta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAgregarproducto = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        buscar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(900, 500));
        setMinimumSize(new java.awt.Dimension(900, 500));
        setPreferredSize(new java.awt.Dimension(900, 500));

        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        btn_Inhabilitar.setText("Inhabilitar");
        btn_Inhabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InhabilitarActionPerformed(evt);
            }
        });

        txtdesc.setMaximumSize(new java.awt.Dimension(6, 20));

        txtcantidad.setMaximumSize(new java.awt.Dimension(6, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Descripción:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Unidad_medida:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Valor_compra:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Valor_venta:");

        cbx_unidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kg", "Und" }));
        cbx_unidad.setMaximumSize(new java.awt.Dimension(6, 20));
        cbx_unidad.setMinimumSize(new java.awt.Dimension(6, 20));
        cbx_unidad.setPreferredSize(new java.awt.Dimension(6, 20));
        cbx_unidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_unidadActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Cantidad_stock:\t");

        txtvalor_compra.setMaximumSize(new java.awt.Dimension(6, 20));

        txtvalor_venta.setMaximumSize(new java.awt.Dimension(6, 20));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtvalor_compra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtvalor_venta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtdesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbx_unidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtcantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtdesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbx_unidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtvalor_compra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtvalor_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tablaAgregarproducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descripción", "Unidad_medida", "Valor_Compra", "Valor_Venta", "Ganancia", "Cantidad", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaAgregarproducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAgregarproductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAgregarproducto);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText(" Producto");

        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(buscar)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(143, 143, 143))))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Inhabilitar)
                .addGap(18, 18, 18)
                .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_editar, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(btn_Inhabilitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(411, 411, 411))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void LlenarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tablaAgregarproducto.getModel();
        listaProducto = controlproducto.findProductoEntities();
        for (Producto obj : listaProducto) {
            modelo.addRow(new Object[]{obj.getId(), obj.getDescripcion(), obj.getUnidadMedida(), obj.getValorCompra(), obj.getValorVenta(), obj.getGanancia(), obj.getCantidadStock(), obj.getEstado()});

        }
    }


    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed

        if (btn_agregar.getText().equalsIgnoreCase("Agregar")) {
            try {
                producto.setDescripcion(txtdesc.getText());
                producto.setUnidadMedida(cbx_unidad.getSelectedItem().toString());
                producto.setValorCompra(Double.parseDouble(txtvalor_compra.getText()));
                producto.setValorVenta(Double.parseDouble(txtvalor_venta.getText()));
                producto.setGanancia((producto.getValorVenta() - producto.getValorCompra()));
                producto.setCantidadStock(Double.parseDouble(txtcantidad.getText()));
                if (producto.getCantidadStock() > 0) {
                    producto.setEstado(true);
                } else {
                    producto.setEstado(false);
                }
                controlproducto.create(producto);
                JOptionPane.showMessageDialog(null, "Producto creado exitosamente");
                limpiarTabla();
                LlenarTabla();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
            }
            txtdesc.setText("");
            txtvalor_compra.setText("");
            txtvalor_venta.setText("");
            txtcantidad.setText("");

        } else {
            try {
                producto.setDescripcion(txtdesc.getText());
                producto.setUnidadMedida(cbx_unidad.getSelectedItem().toString());
                producto.setValorCompra(Double.parseDouble(txtvalor_compra.getText()));
                producto.setValorVenta(Double.parseDouble(txtvalor_venta.getText()));
                producto.setGanancia((producto.getValorVenta() - producto.getValorCompra()));
                producto.setCantidadStock(Double.parseDouble(txtcantidad.getText()));
                if (producto.getCantidadStock() > 0) {
                    producto.setEstado(true);
                } else {
                    producto.setEstado(false);
                }
                controlproducto.edit(producto);
                JOptionPane.showMessageDialog(null, "Producto editado exitosamente!");
                limpiarTabla();
                LlenarTabla();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, " " + e.getMessage());
            }
        }
        txtdesc.setText("");
        txtvalor_compra.setText("");
        txtvalor_venta.setText("");
        txtcantidad.setText("");
        btn_agregar.setText("Agregar");

    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_InhabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InhabilitarActionPerformed
        producto = controlproducto.findProducto((Integer) tablaAgregarproducto.getValueAt(tablaAgregarproducto.getSelectedRow(), 0));
        try {
            if(btn_Inhabilitar.getText().equals("Inhabilitar")){
                producto.setEstado(false);
                controlproducto.edit(producto);
                JOptionPane.showMessageDialog(null, "Producto Deshabilitado correctamente");
                limpiarTabla();
                LlenarTabla();
                
            }else{
               producto.setEstado(true);
                controlproducto.edit(producto);
                JOptionPane.showMessageDialog(null, "Producto Habilitado correctamente");
                limpiarTabla();
                LlenarTabla(); 
            }
               
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, " " + e.getMessage());
            }


    }//GEN-LAST:event_btn_InhabilitarActionPerformed

    private void cbx_unidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_unidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_unidadActionPerformed


    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        btn_agregar.setText("Actualizar");
        txtdesc.setText(tablaAgregarproducto.getValueAt(tablaAgregarproducto.getSelectedRow(), 1).toString());
        cbx_unidad.setSelectedItem(tablaAgregarproducto.getValueAt(tablaAgregarproducto.getSelectedRow(), 2).toString());
        txtvalor_compra.setText(tablaAgregarproducto.getValueAt(tablaAgregarproducto.getSelectedRow(), 3).toString());
        txtvalor_venta.setText(tablaAgregarproducto.getValueAt(tablaAgregarproducto.getSelectedRow(), 4).toString());
        txtcantidad.setText(tablaAgregarproducto.getValueAt(tablaAgregarproducto.getSelectedRow(), 6).toString());

        producto = controlproducto.findProducto((Integer) tablaAgregarproducto.getValueAt(tablaAgregarproducto.getSelectedRow(), 0));


    }//GEN-LAST:event_btn_editarActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarActionPerformed

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        filtrarTabla();
    }//GEN-LAST:event_buscarKeyReleased

    private void tablaAgregarproductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAgregarproductoMouseClicked
        producto = controlproducto.findProducto((Integer) tablaAgregarproducto.getValueAt(tablaAgregarproducto.getSelectedRow(), 0));
        if(producto.getEstado().equals(true)){
            btn_Inhabilitar.setText("Inhabilitar");
        }else{
             btn_Inhabilitar.setText("Habilitar");
        }

    }//GEN-LAST:event_tablaAgregarproductoMouseClicked

    public void limpiarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tablaAgregarproducto.getModel();
        int a = modelo.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }

    public void filtrarTabla() {
        if (buscar.getText().equals("")) {
            limpiarTabla();
            LlenarTabla();

        } else {
            limpiarTabla();
            DefaultTableModel modelo = (DefaultTableModel) tablaAgregarproducto.getModel();
            for (Producto obj : listaProducto) {
                if (obj.getDescripcion().contains(buscar.getText())) {
                    modelo.addRow(new Object[]{obj.getId(), obj.getDescripcion(), obj.getUnidadMedida(), obj.getValorCompra(), obj.getValorVenta(), obj.getGanancia(), obj.getCantidadStock(), obj.getEstado()});
                }
            }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vista_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista_Producto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Inhabilitar;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JTextField buscar;
    private javax.swing.JComboBox<String> cbx_unidad;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaAgregarproducto;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtdesc;
    private javax.swing.JTextField txtvalor_compra;
    private javax.swing.JTextField txtvalor_venta;
    // End of variables declaration//GEN-END:variables
}
