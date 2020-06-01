package Entidades;

import Entidades.ProductoMovimiento;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-01T17:06:57")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, String> descripcion;
    public static volatile SingularAttribute<Producto, Boolean> estado;
    public static volatile SingularAttribute<Producto, Double> cantidadStock;
    public static volatile SingularAttribute<Producto, String> unidadMedida;
    public static volatile SingularAttribute<Producto, Double> valorVenta;
    public static volatile ListAttribute<Producto, ProductoMovimiento> productoMovimientoList;
    public static volatile SingularAttribute<Producto, Double> valorCompra;
    public static volatile SingularAttribute<Producto, Integer> id;
    public static volatile SingularAttribute<Producto, Double> ganancia;

}