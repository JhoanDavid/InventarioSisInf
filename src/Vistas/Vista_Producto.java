/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.GlobalClass;
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
       setTitle("EasyStock");
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
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAgregarproducto = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btn_Inhabilitar = new javax.swing.JButton();
        txtCancelar1 = new javax.swing.JButton();
        buscar = new javax.swing.JTextField();
        btn_editar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(null);

        tablaAgregarproducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descripción", "Unidad medida", "Valor Compra", "Valor Venta", "Ganancia", "Cantidad", "Estado"
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
        tablaAgregarproducto.getTableHeader().setReorderingAllowed(false) ;
        if (tablaAgregarproducto.getColumnModel().getColumnCount() > 0) {
            tablaAgregarproducto.getColumnModel().getColumn(0).setMinWidth(60);
            tablaAgregarproducto.getColumnModel().getColumn(0).setPreferredWidth(60);
            tablaAgregarproducto.getColumnModel().getColumn(0).setMaxWidth(60);
        }

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Lista de Productos");

        btn_Inhabilitar.setText("Inhabilitar");
        btn_Inhabilitar.setMaximumSize(new java.awt.Dimension(61, 23));
        btn_Inhabilitar.setMinimumSize(new java.awt.Dimension(61, 23));
        btn_Inhabilitar.setPreferredSize(new java.awt.Dimension(61, 23));
        btn_Inhabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InhabilitarActionPerformed(evt);
            }
        });

        txtCancelar1.setText("Regresar");
        txtCancelar1.setMaximumSize(new java.awt.Dimension(61, 23));
        txtCancelar1.setMinimumSize(new java.awt.Dimension(61, 23));
        txtCancelar1.setPreferredSize(new java.awt.Dimension(61, 23));
        txtCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCancelar1ActionPerformed(evt);
            }
        });

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

        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(txtCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Inhabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Inhabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(454, 454, 454)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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


    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed

        if (tablaAgregarproducto.getSelectedRow() == (-1)) {
            JOptionPane.showMessageDialog(null, "Debe de selecionar un valor de la tabla");
        } else {
            producto = controlproducto.findProducto((Integer) tablaAgregarproducto.getValueAt(tablaAgregarproducto.getSelectedRow(), 0));
            Agregar_producto vp = new Agregar_producto(producto);
            vp.setVisible(true);
            this.dispose();
        }

    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_InhabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InhabilitarActionPerformed

        if (tablaAgregarproducto.getSelectedRow() == (-1)) {
            JOptionPane.showMessageDialog(null, "Debe de selecionar un valor de la tabla");
        } else {
            int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de ejecutar la acción?", "Alerta!", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {
                producto = controlproducto.findProducto((Integer) tablaAgregarproducto.getValueAt(tablaAgregarproducto.getSelectedRow(), 0));
                try {
                    if (btn_Inhabilitar.getText().equals("Inhabilitar")) {
                        producto.setEstado(false);
                        controlproducto.edit(producto);
                        JOptionPane.showMessageDialog(null, "Producto Deshabilitado correctamente");
                        limpiarTabla();
                        LlenarTabla();

                    } else {
                        producto.setEstado(true);
                        controlproducto.edit(producto);
                        JOptionPane.showMessageDialog(null, "Producto Habilitado correctamente");
                        limpiarTabla();
                        LlenarTabla();
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, " " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Operacion cancelada");
                limpiarTabla();
                LlenarTabla();
            }

        }

    }//GEN-LAST:event_btn_InhabilitarActionPerformed

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        filtrarTabla();
    }//GEN-LAST:event_buscarKeyReleased

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarActionPerformed

    private void tablaAgregarproductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAgregarproductoMouseClicked
        producto = controlproducto.findProducto((Integer) tablaAgregarproducto.getValueAt(tablaAgregarproducto.getSelectedRow(), 0));
        if (producto.getEstado().equals(true)) {
            btn_Inhabilitar.setText("Inhabilitar");
        } else {
            btn_Inhabilitar.setText("Habilitar");
        }
    }//GEN-LAST:event_tablaAgregarproductoMouseClicked

    private void txtCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCancelar1ActionPerformed
       if (GlobalClass.usuario != null) {

            if (GlobalClass.usuario.getRol().equalsIgnoreCase("Administrador")) {
                InicioAdministrador i = new InicioAdministrador();
                i.setVisible(true);
                this.dispose();
            } else {
                InicioVendedor i = new InicioVendedor();
                i.setVisible(true);
                this.dispose();
            }

        } else {
            InicioAdmonSupremo i = new InicioAdmonSupremo();
            i.setVisible(true);
            this.dispose();

        }             // TODO add your handling code here:
    }//GEN-LAST:event_txtCancelar1ActionPerformed

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
    private javax.swing.JButton btn_editar;
    private javax.swing.JTextField buscar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaAgregarproducto;
    private javax.swing.JButton txtCancelar1;
    // End of variables declaration//GEN-END:variables
}
