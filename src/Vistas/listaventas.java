/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class listaventas extends javax.swing.JFrame {

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

    public listaventas() {
        initComponents();
        modelo = (DefaultTableModel) tablaProductoInventario.getModel();
        modeloCarrito = (DefaultTableModel) tablaCarritoVenta.getModel();
        txtFecha.setText(getFechaActual());
        setTitle("EasyStock");
        setResizable(false);
        setLocationRelativeTo(null);
        LlenarTabla();
    }

    public listaventas(Usuario user) {
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
        String cliente;
        listaMovimiento = controlmovimiento.findMovimientoEntities();
        for (Movimiento obj : listaMovimiento) {
            if (obj.getTipoMov().contains("Venta") || obj.getTipoMov().contains("PrestamoSalida") || obj.getTipoMov().contains("DevolucionSalida")) {
                if (obj.getUsuarioTrans() == null) {
                    UsuarioTrans = 0;
                } else {
                    UsuarioTrans = obj.getUsuarioTrans().getId();
                }
                if (obj.getIdCliente() == null) {
                    cliente = "Cliente no registrado";
                } else {
                    cliente = obj.getIdCliente().getNombre();
                }
                modelo.addRow(new Object[]{obj.getId(), obj.getFechaMovimiento(), obj.getDescripcion(), UsuarioTrans,
                    obj.getDescuentoAplicado(), cliente, obj.getTipoMov()});
            }
        }
        calcularTotalCompras();
    }

    public void LlenarTablaVendedor() {
        long UsuarioTrans;
        String cliente;
        listaMovimiento = controlmovimiento.findMovimientoEntities();
        for (Movimiento obj : listaMovimiento) {
            if (obj.getTipoMov().contains("Venta") || obj.getTipoMov().contains("PrestamoSalida") || obj.getTipoMov().contains("DevolucionSalida")) {
                if (obj.getUsuarioTrans() != null) {
                    if (obj.getUsuarioTrans().getId() == GlobalClass.usuario.getId()) {
                        if (obj.getUsuarioTrans() == null) {
                            UsuarioTrans = 0;
                        } else {
                            UsuarioTrans = obj.getUsuarioTrans().getId();
                        }
                        if (obj.getIdCliente() == null) {
                            cliente = "Cliente no registrado";
                        } else {
                            cliente = obj.getIdCliente().getNombre();
                        }
                        modelo.addRow(new Object[]{obj.getId(), obj.getFechaMovimiento(), obj.getDescripcion(), UsuarioTrans,
                            obj.getDescuentoAplicado(), cliente, obj.getTipoMov()});
                    }
                }
            }
        }
        calcularTotalCompras();
    }

    public void agregarProductoCarrito() {
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
    }

    public void quitarProductoCarrito() {
        double valor = (double) tablaCarritoVenta.getValueAt(tablaCarritoVenta.getSelectedRow(), 3);
        double valorTotal = new Double(txtTotal.getText());
        txtTotal.setText(String.valueOf(valorTotal - valor));
        modeloCarrito.removeRow(tablaCarritoVenta.getSelectedRow());
    }

    public void filtrarTabla() {
        long UsuarioTrans;
        String cliente;
        try {
            limpiarTabla();
            if (calendario.getDate() == null) {
                limpiarTabla();
                LlenarTabla();
                JOptionPane.showMessageDialog(null, "Debe escoger una fecha en el calendario");

            } else {
                limpiarTabla();
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
                                if ((obj.getTipoMov().contains("Venta") || obj.getTipoMov().contains("PrestamoSalida") || obj.getTipoMov().contains("DevolucionSalida")) && a >= 0) {
                                    if (obj.getUsuarioTrans() == null) {
                                        UsuarioTrans = 0;
                                    } else {
                                        UsuarioTrans = obj.getUsuarioTrans().getId();
                                    }
                                    if (obj.getIdCliente() == null) {
                                        cliente = "Cliente no registrado";
                                    } else {
                                        cliente = obj.getIdCliente().getNombre();
                                    }
                                    if (obj.getUsuarioTrans() != null) {
                                        if (obj.getUsuarioTrans().getId() == GlobalClass.usuario.getId()) {
                                            modelo.addRow(new Object[]{obj.getId(), obj.getFechaMovimiento(), obj.getDescripcion(), UsuarioTrans,
                                                obj.getDescuentoAplicado(), cliente, obj.getTipoMov()});
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
                                if ((obj.getTipoMov().contains("Venta") || obj.getTipoMov().contains("PrestamoSalida") || obj.getTipoMov().contains("DevolucionSalida")) && a >= 0) {
                                    if (obj.getUsuarioTrans() == null) {
                                        UsuarioTrans = 0;
                                    } else {
                                        UsuarioTrans = obj.getUsuarioTrans().getId();
                                    }
                                    if (obj.getIdCliente() == null) {
                                        cliente = "Cliente no registrado";
                                    } else {
                                        cliente = obj.getIdCliente().getNombre();
                                    }
                                    modelo.addRow(new Object[]{obj.getId(), obj.getFechaMovimiento(), obj.getDescripcion(), UsuarioTrans,
                                        obj.getDescuentoAplicado(), cliente, obj.getTipoMov()});
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
                            if ((obj.getTipoMov().contains("Venta") || obj.getTipoMov().contains("PrestamoSalida") || obj.getTipoMov().contains("DevolucionSalida")) && a >= 0) {
                                if (obj.getUsuarioTrans() == null) {
                                    UsuarioTrans = 0;
                                } else {
                                    UsuarioTrans = obj.getUsuarioTrans().getId();
                                }
                                if (obj.getIdCliente() == null) {
                                    cliente = "Cliente no registrado";
                                } else {
                                    cliente = obj.getIdCliente().getNombre();
                                }
                                modelo.addRow(new Object[]{obj.getId(), obj.getFechaMovimiento(), obj.getDescripcion(), UsuarioTrans,
                                    obj.getDescuentoAplicado(), cliente, obj.getTipoMov()});
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
        listaMovimiento = controlmovimiento.findMovimientoEntities();
        int a = controlmovimiento.getMovimientoCount();
        JOptionPane.showMessageDialog(null, a);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        cancelar = new javax.swing.JButton();
        txtFecha = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProductoInventario = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTotalCompras = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        calendario = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaCarritoVenta = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 400));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Reporte de Salida");

        cancelar.setText("Regresar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        txtFecha.setText("00-00-2020");

        tablaProductoInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "fecha", "descripcion", "id vendedor", "descuento", "cliente", "tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        tablaProductoInventario.getTableHeader().setReorderingAllowed(false);

        jLabel2.setText("Fecha Inicial");

        jLabel3.setText("Total de todas las ventas");

        txtTotalCompras.setEditable(false);
        txtTotalCompras.setText("0");
        txtTotalCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalComprasActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Listado de Ventas");

        jButton1.setText("buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(206, 206, 206))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(37, 37, 37)
                                .addComponent(txtTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE))
                        .addGap(21, 21, 21))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(calendario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
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
        tablaCarritoVenta.getTableHeader().setReorderingAllowed(false);

        jLabel11.setText("Total de venta");

        txtTotal.setEditable(false);
        txtTotal.setText("0");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Productos de la Venta");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(25, 25, 25)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(183, 183, 183))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(51, 51, 51)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(txtFecha)
                .addGap(396, 396, 396)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(449, Short.MAX_VALUE)
                .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(444, 444, 444))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtFecha)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 308, Short.MAX_VALUE)
                .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(41, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(77, Short.MAX_VALUE)))
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

        }         // TODO add your handling code here:
    }//GEN-LAST:event_cancelarActionPerformed

    private void tablaProductoInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoInventarioMouseClicked
        //txtCantidad.setText("1");
        try {
            limpiarTablaProductos();
            txtTotal.setText(String.valueOf(0));
            agregarProductoCarrito();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tablaProductoInventarioMouseClicked

    private void txtTotalComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalComprasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalComprasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        txtTotalCompras.setText("0");
        txtTotal.setText("0");
        limpiarTablaProductos();
        filtrarTabla(); // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(listaventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(listaventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(listaventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(listaventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new listaventas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser calendario;
    private javax.swing.JButton cancelar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
