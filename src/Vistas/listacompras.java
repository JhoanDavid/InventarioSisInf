/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//aja
package Vistas;

import Controladores.GlobalClass;
import Controladores.ProductoJpaController;
import Controladores.MovimientoJpaController;
import Controladores.ProductoMovimientoJpaController;
import Entidades.Producto;
import Entidades.Movimiento;
import Entidades.ProductoMovimiento;
import Entidades.Usuario;
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
        modelo = (DefaultTableModel) tablaProductoInventario.getModel();
        modeloCarrito = (DefaultTableModel) tablaCarritoVenta.getModel();
        txtFecha.setText(getFechaActual());
        setTitle("EasyStock");
        setResizable(false);
        setLocationRelativeTo(null);
        LlenarTabla();
    }

    public listacompras(Usuario user) {
        initComponents();
        modelo = (DefaultTableModel) tablaProductoInventario.getModel();
        modeloCarrito = (DefaultTableModel) tablaCarritoVenta.getModel();
        txtFecha.setText(getFechaActual());
        setTitle("EasyStock");
        setResizable(false);
        setLocationRelativeTo(null);
        LlenarTablaVendedor();
    }

    public void LlenarTabla() {
        long UsuarioTrans;
        listaMovimiento = controlmovimiento.findMovimientoEntities();
        for (Movimiento obj : listaMovimiento) {
            if (obj.getTipoMov().contains("Compra") || obj.getTipoMov().contains("PrestamoEntrada") || obj.getTipoMov().contains("DevolucionEntrada")) {

                if (obj.getUsuarioTrans() == null) {
                    UsuarioTrans = 0;
                } else {
                    UsuarioTrans = obj.getUsuarioTrans().getId();
                }
                modelo.addRow(new Object[]{obj.getId(), obj.getFechaMovimiento(), obj.getDescripcion(), obj.getIdRemitente(),
                    UsuarioTrans, obj.getTipoMov()});
            }
        }
        calcularTotalCompras();
    }

    public void LlenarTablaVendedor() {
        long UsuarioTrans;
        listaMovimiento = controlmovimiento.findMovimientoEntities();
        for (Movimiento obj : listaMovimiento) {
            if (obj.getTipoMov().contains("Compra") || obj.getTipoMov().contains("PrestamoEntrada") || obj.getTipoMov().contains("DevolucionEntrada")) {
                if (obj.getUsuarioTrans() != null) {
                    if (obj.getUsuarioTrans().getId() == GlobalClass.usuario.getId()) {
                        if (obj.getUsuarioTrans() == null) {
                            UsuarioTrans = 0;
                        } else {
                            UsuarioTrans = obj.getUsuarioTrans().getId();
                        }
                        modelo.addRow(new Object[]{obj.getId(), obj.getFechaMovimiento(), obj.getDescripcion(), obj.getIdRemitente(),
                            UsuarioTrans, obj.getTipoMov()});
                    }
                }
            }
        }
        calcularTotalCompras();
    }

    public void agregarProductoCarrito() {
        try {
            double valorTotal = 0;
            int id_movi = new Integer(tablaProductoInventario.getValueAt(tablaProductoInventario.getSelectedRow(), 0).toString());
            listaProductoMovimiento = controlproductomovimiento.findProductoMovimientoEntities();
            for (ProductoMovimiento obj : listaProductoMovimiento) {
                if (obj.getIdMov().getId() == id_movi) {
                    modeloCarrito.addRow(new Object[]{obj.getIdProducto().getId(), obj.getIdProducto().getDescripcion(), obj.getCantTrans(),
                        obj.getValorTrans()});
                }
            }
            for (int i = 0; i < tablaCarritoVenta.getColumnCount(); i++) {
                double valor = (double) tablaCarritoVenta.getValueAt(i, 3) + valorTotal;
                valorTotal = (valor);
                txtTotal.setText(String.valueOf(valorTotal));
            }
        } catch (Exception e) {
        }
    }

    public void quitarProductoCarrito() {
        double valor = (double) tablaCarritoVenta.getValueAt(tablaCarritoVenta.getSelectedRow(), 3);
        double valorTotal = new Double(txtTotal.getText());
        txtTotal.setText(String.valueOf(valorTotal - valor));
        modeloCarrito.removeRow(tablaCarritoVenta.getSelectedRow());
    }

    public void filtrarTabla() {
        long UsuarioTrans;
        try {
            if (calendario.getDate() == null) {
                limpiarTabla();
                LlenarTabla();
                limpiarTablaProductos();
                JOptionPane.showMessageDialog(null, "Debe escoger una fecha en el calendario");

            } else {
                limpiarTabla();
                limpiarTablaProductos();
                int a = 0;
                DefaultTableModel modelo = (DefaultTableModel) tablaProductoInventario.getModel();
                listaMovimiento = controlmovimiento.findMovimientoEntities();

                if (GlobalClass.usuario != null) {
                    if (GlobalClass.usuario.getRol().equalsIgnoreCase("Vendedor")) {

                        for (Movimiento obj : listaMovimiento) {
                            for (int i = 0; i < 1; i++) {
                                Date B = calendario.getDate();
                                Date A = obj.getFechaMovimiento();
                                a = A.compareTo(B);
                                if ((obj.getTipoMov().contains("Compra") || obj.getTipoMov().contains("PrestamoEntrada") || obj.getTipoMov().contains("DevolucionEntrada")) && a >= 0) {
                                    if (obj.getUsuarioTrans() != null) {
                                        if (obj.getUsuarioTrans().getId() == GlobalClass.usuario.getId()) {
                                            if (obj.getUsuarioTrans() == null) {
                                                UsuarioTrans = 0;
                                            } else {
                                                UsuarioTrans = obj.getUsuarioTrans().getId();
                                            }
                                            modelo.addRow(new Object[]{obj.getId(), obj.getFechaMovimiento(), obj.getDescripcion(), obj.getIdRemitente(),
                                                UsuarioTrans, obj.getTipoMov()});
                                            calcularTotalCompras();
                                        }
                                    }
                                }
                            }
                        }

                    } else {
                        for (Movimiento obj : listaMovimiento) {
                            for (int i = 0; i < 1; i++) {
                                Date B = calendario.getDate();
                                Date A = obj.getFechaMovimiento();
                                a = A.compareTo(B);
                                if ((obj.getTipoMov().contains("Compra") || obj.getTipoMov().contains("PrestamoEntrada") || obj.getTipoMov().contains("DevolucionEntrada")) && a >= 0) {
                                    if (obj.getUsuarioTrans() == null) {
                                        UsuarioTrans = 0;
                                    } else {
                                        UsuarioTrans = obj.getUsuarioTrans().getId();
                                    }
                                    modelo.addRow(new Object[]{obj.getId(), obj.getFechaMovimiento(), obj.getDescripcion(), obj.getIdRemitente(),
                                        UsuarioTrans, obj.getTipoMov()});
                                    calcularTotalCompras();
                                }
                            }
                        }

                    }

                } else {
                    for (Movimiento obj : listaMovimiento) {
                        for (int i = 0; i < 1; i++) {
                            Date B = calendario.getDate();
                            Date A = obj.getFechaMovimiento();
                            a = A.compareTo(B);
                            if ((obj.getTipoMov().contains("Compra") || obj.getTipoMov().contains("PrestamoEntrada") || obj.getTipoMov().contains("DevolucionEntrada")) && a >= 0) {
                                if (obj.getUsuarioTrans() == null) {
                                    UsuarioTrans = 0;
                                } else {
                                    UsuarioTrans = obj.getUsuarioTrans().getId();
                                }
                                modelo.addRow(new Object[]{obj.getId(), obj.getFechaMovimiento(), obj.getDescripcion(), obj.getIdRemitente(),
                                    UsuarioTrans, obj.getTipoMov()});
                                calcularTotalCompras();
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
        }
    }

    public void limpiarTabla() {
        int a = modelo.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }

    public void limpiarTablaProductos() {
        int a = modeloCarrito.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            modeloCarrito.removeRow(i);
        }
    }

    public String getFechaActual() {
        Date fechaActual = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        return formateador.format(fechaActual);
    }

    public void calcularTotalCompras() {
        double ValorTotalCompras = 0;
        double valorTotal = 0;
        double total = 0;

        listaProductoMovimiento = controlproductomovimiento.findProductoMovimientoEntities();
        for (ProductoMovimiento obj : listaProductoMovimiento) {

            for (int i = 0; i < tablaProductoInventario.getRowCount(); i++) {

                if (tablaProductoInventario.getValueAt(i, 0) == obj.getIdMov().getId()) {
                    valorTotal = obj.getValorTrans() + ValorTotalCompras;
                    ValorTotalCompras = valorTotal;
                    total = ValorTotalCompras;
                    txtTotalCompras.setText(String.valueOf(total));
                    //JOptionPane.showMessageDialog(null, tablaProductoInventario.getValueAt(i, 0));

                }
            }

        }
    }

    public void prueba() {
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cancelar = new javax.swing.JButton();
        txtFecha = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProductoInventario = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTotalCompras = new javax.swing.JTextField();
        calendario = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaCarritoVenta = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 400));

        cancelar.setText("Regresar");
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
        jLabel1.setText("Reporte de Entradas");

        tablaProductoInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Fecha", "Descripcion", "Id Remitente", "Usuario", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Long.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
        tablaProductoInventario.getTableHeader().setReorderingAllowed(false);

        jLabel2.setText("Fecha Inicial");

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

        jButton2.setText("buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE))
                        .addGap(21, 21, 21))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(jLabel9)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(calendario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addContainerGap())))
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
        tablaCarritoVenta.getTableHeader().setReorderingAllowed(false);

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
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(529, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(440, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFecha)
                .addGap(327, 327, 327)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(0, 403, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 336, Short.MAX_VALUE)
                .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(47, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(48, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
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

        }           // TODO add your handling code here:
    }//GEN-LAST:event_cancelarActionPerformed

    private void tablaProductoInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoInventarioMouseClicked
        //txtCantidad.setText("1");

        limpiarTablaProductos();
        txtTotal.setText(String.valueOf(0));
        agregarProductoCarrito();


    }//GEN-LAST:event_tablaProductoInventarioMouseClicked

    private void txtTotalComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalComprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalComprasActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        txtTotalCompras.setText("0");
        txtTotal.setText("0");
        filtrarTabla();     // TODO add your handling code here:

    }//GEN-LAST:event_jButton2ActionPerformed

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
    private com.toedter.calendar.JDateChooser calendario;
    private javax.swing.JButton cancelar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaCarritoVenta;
    private javax.swing.JTable tablaProductoInventario;
    private javax.swing.JLabel txtFecha;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotalCompras;
    // End of variables declaration//GEN-END:variables
}
