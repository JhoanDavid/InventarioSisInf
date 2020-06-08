/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.GlobalClass;
import Controladores.MovimientoJpaController;
import Controladores.ProductoJpaController;
import Controladores.ProductoMovimientoJpaController;
import Entidades.Movimiento;
import Entidades.Producto;
import Entidades.ProductoMovimiento;
import Entidades.Usuario;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author warriors
 */
public class reporte_ventas extends javax.swing.JFrame {

    MovimientoJpaController controlmovimiento = new MovimientoJpaController();
    ProductoMovimientoJpaController controlproductoM = new ProductoMovimientoJpaController();
    DefaultTableModel modeloCarrito;
    List<Movimiento> listaMovimiento;
    List<ProductoMovimiento> listaProductoMovimiento;
    List<Producto> listaProducto;
    DefaultTableModel modelo;
    double ventatotal = 0;
    Usuario user = null;
    private final String ruta = System.getProperties().getProperty("user.dir");

    public reporte_ventas() {
        initComponents();
        LlenarTabla();
        txtgenerando.setVisible(false);
        Progressbar_entradas.setVisible(false);
        setTitle("EasyStock");
        setResizable(false);
        setLocationRelativeTo(null);
        ventatotal = calcularTotalVentas();
        txtvalortotal.setText(String.valueOf(ventatotal));
        txtvalortotal.setEditable(false);

    }

    public reporte_ventas(Usuario user) {
        initComponents();
        this.user = user;
        LlenarTablaVendedor();
        txtgenerando.setVisible(false);
        Progressbar_entradas.setVisible(false);
        setTitle("EasyStock");
        setResizable(false);
        setLocationRelativeTo(null);
        ventatotal = calcularTotalVentas();
        txtvalortotal.setText(String.valueOf(ventatotal));
        txtvalortotal.setEditable(false);

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
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaReporteventas = new javax.swing.JTable();
        btn_reporte = new javax.swing.JButton();
        Progressbar_entradas = new javax.swing.JProgressBar();
        txtgenerando = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtvalortotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Reporte de Salidas");

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tablaReporteventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Compra", "Fecha", "Descripción Movimiento", "Vendedor", "Tipo Movimiento", "Cliente", "ID Producto", "Descripción Producto", "Cantidad", "Unidad de Medida", "Valor venta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaReporteventas);
        tablaReporteventas.getTableHeader().setReorderingAllowed(false) ;

        btn_reporte.setText("Generar Reporte");
        btn_reporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reporteActionPerformed(evt);
            }
        });

        txtgenerando.setText("Generando:");

        jLabel1.setText("Valor total de las ventas:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtgenerando, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Progressbar_entradas, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_reporte, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtvalortotal, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Progressbar_entradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtgenerando))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtvalortotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(btn_reporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

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

        } // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    public void generarExcel() {

        try {
            if (tablaReporteventas.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "No hay reportes de Ventas en la Tabla");
            } else {
                Thread t = new Thread() {
                    public void run() {
                        XSSFWorkbook workbook = new XSSFWorkbook();
                        XSSFSheet hoja = workbook.createSheet();

                        txtgenerando.setVisible(true);
                        Progressbar_entradas.setVisible(true);

                        XSSFRow fila = hoja.createRow(0);
                        fila.createCell(0).setCellValue("ID");
                        fila.createCell(1).setCellValue("Fecha_Compra");
                        fila.createCell(2).setCellValue("Descripcion");
                        fila.createCell(3).setCellValue("Vendedor");
                        fila.createCell(4).setCellValue("Tipo_movimiento");
                        fila.createCell(5).setCellValue("Cliente");
                        fila.createCell(6).setCellValue("ID_Producto");
                        fila.createCell(7).setCellValue("Descripcion");
                        fila.createCell(8).setCellValue("Cantidad");
                        fila.createCell(9).setCellValue("Unidad_Medida");
                        fila.createCell(10).setCellValue("Valor_Venta");
                        fila.createCell(11).setCellValue("Valor_VentaTotal");

                        Progressbar_entradas.setMaximum(tablaReporteventas.getRowCount());
                        XSSFRow filas;
                        Rectangle rect;
                        int cont = 0;
                        for (int i = 0; i < tablaReporteventas.getRowCount(); i++) {
                            rect = tablaReporteventas.getCellRect(i, 0, true);
                            try {
                                tablaReporteventas.scrollRectToVisible(rect);
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Error " + e);
                            }
                            tablaReporteventas.setRowSelectionInterval(i, i);
                            Progressbar_entradas.setValue((i + 1));

                            filas = hoja.createRow((i + 1));
                            filas.createCell(0).setCellValue(tablaReporteventas.getValueAt(i, 0).toString());
                            filas.createCell(1).setCellValue(tablaReporteventas.getValueAt(i, 1).toString());
                            filas.createCell(2).setCellValue(tablaReporteventas.getValueAt(i, 2).toString());
                            filas.createCell(3).setCellValue(tablaReporteventas.getValueAt(i, 3).toString());
                            filas.createCell(4).setCellValue(tablaReporteventas.getValueAt(i, 4).toString());
                            filas.createCell(5).setCellValue(tablaReporteventas.getValueAt(i, 5).toString());
                            filas.createCell(6).setCellValue(tablaReporteventas.getValueAt(i, 6).toString());
                            filas.createCell(7).setCellValue(tablaReporteventas.getValueAt(i, 7).toString());
                            filas.createCell(8).setCellValue(tablaReporteventas.getValueAt(i, 8).toString());
                            filas.createCell(9).setCellValue(tablaReporteventas.getValueAt(i, 9).toString());
                            filas.createCell(10).setCellValue(tablaReporteventas.getValueAt(i, 10).toString());
                            cont++;
                        }
                        filas = hoja.createRow(cont + 1);
                        filas.createCell(11).setCellValue(ventatotal);

                        Progressbar_entradas.setString("Abriendo Excel...");
                        try {
                            workbook.write(new FileOutputStream(new File(ruta + "//reporteSalida.xlsx")));
                            Desktop.getDesktop().open(new File(ruta + "//reporteSalida.xlsx"));
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, " Error" + e);
                        }
                    }

                };
                t.start();
            }

        } catch (Exception e) {
        }

    }

    public double calcularTotalVentas() {

        for (int i = 0; i < tablaReporteventas.getRowCount(); i++) {
            ventatotal += (Double) tablaReporteventas.getValueAt(i, 10);
        }
        return ventatotal;
    }


    private void btn_reporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reporteActionPerformed
        generarExcel();


    }//GEN-LAST:event_btn_reporteActionPerformed

    public void LlenarTabla() {
        String UsuarioTrans;
        String cliente;
        listaMovimiento = controlmovimiento.findMovimientoEntities();
        listaProductoMovimiento = controlproductoM.findProductoMovimientoEntities();
        DefaultTableModel modelo = (DefaultTableModel) tablaReporteventas.getModel();
        if (listaMovimiento != null && listaProductoMovimiento != null) {
            for (Movimiento obj : listaMovimiento) {
                for (ProductoMovimiento obj1 : listaProductoMovimiento) {
                    if (obj.getId() == obj1.getIdMov().getId() && (obj.getTipoMov().equalsIgnoreCase("Venta") || obj.getTipoMov().equalsIgnoreCase("PrestamoSalida")
                            || obj.getTipoMov().equalsIgnoreCase("DevolucionSalida"))) {
                        if (obj.getUsuarioTrans() == null) {
                            UsuarioTrans = "Admin";
                        } else {
                            UsuarioTrans = obj.getUsuarioTrans().getNombre();
                        }
                        if (obj.getIdCliente() == null) {
                            cliente = "Cliente no registrado";
                        } else {
                            cliente = obj.getIdCliente().getNombre();
                        }

                        modelo.addRow(new Object[]{obj1.getId(), obj.getFechaMovimiento(), obj.getDescripcion(),
                            UsuarioTrans, obj.getTipoMov(), cliente, obj1.getIdProducto().getId(),
                            obj1.getIdProducto().getDescripcion(), obj1.getCantTrans(), obj1.getIdProducto().getUnidadMedida(), obj1.getValorTrans()});
                    } else {

                    }

                }

            }

        } else {
            JOptionPane.showMessageDialog(null, "Tablas sin registros");
        }

    }

    public void LlenarTablaVendedor() {
        String UsuarioTrans;
        String cliente;
        listaMovimiento = controlmovimiento.findMovimientoEntities();
        listaProductoMovimiento = controlproductoM.findProductoMovimientoEntities();
        DefaultTableModel modelo = (DefaultTableModel) tablaReporteventas.getModel();
        if (listaMovimiento != null && listaProductoMovimiento != null) {
            for (Movimiento obj : listaMovimiento) {
                for (ProductoMovimiento obj1 : listaProductoMovimiento) {
                    if (obj.getId() == obj1.getIdMov().getId() && (obj.getTipoMov().equalsIgnoreCase("Venta") || obj.getTipoMov().equalsIgnoreCase("PrestamoSalida")
                            || obj.getTipoMov().equalsIgnoreCase("DevolucionSalida"))) {
                        if(obj.getUsuarioTrans()!=null){
                        if (obj.getUsuarioTrans().getId() == GlobalClass.usuario.getId()) {

                            if (obj.getUsuarioTrans() == null) {
                                UsuarioTrans = "Admin";
                            } else {
                                UsuarioTrans = obj.getUsuarioTrans().getNombre();
                            }
                            if (obj.getIdCliente() == null) {
                                cliente = "Cliente no registrado";
                            } else {
                                cliente = obj.getIdCliente().getNombre();
                            }

                            modelo.addRow(new Object[]{obj1.getId(), obj.getFechaMovimiento(), obj.getDescripcion(),
                                UsuarioTrans, obj.getTipoMov(), cliente, obj1.getIdProducto().getId(),
                                obj1.getIdProducto().getDescripcion(), obj1.getCantTrans(), obj1.getIdProducto().getUnidadMedida(), obj1.getValorTrans()});
                        }

                        }

                    }

                }

            }

        } else {
            JOptionPane.showMessageDialog(null, "Tablas sin registros");
        }

    }

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
            java.util.logging.Logger.getLogger(reporte_ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reporte_ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reporte_ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reporte_ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reporte_ventas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar Progressbar_entradas;
    private javax.swing.JButton btn_reporte;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaReporteventas;
    private javax.swing.JLabel txtgenerando;
    private javax.swing.JTextField txtvalortotal;
    // End of variables declaration//GEN-END:variables
}
