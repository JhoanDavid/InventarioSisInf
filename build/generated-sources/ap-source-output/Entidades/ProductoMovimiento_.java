package Entidades;

import Entidades.Movimiento;
import Entidades.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-29T16:35:33")
@StaticMetamodel(ProductoMovimiento.class)
public class ProductoMovimiento_ { 

    public static volatile SingularAttribute<ProductoMovimiento, Double> cantTrans;
    public static volatile SingularAttribute<ProductoMovimiento, Integer> id;
    public static volatile SingularAttribute<ProductoMovimiento, Producto> idProducto;
    public static volatile SingularAttribute<ProductoMovimiento, Double> valorTrans;
    public static volatile SingularAttribute<ProductoMovimiento, Movimiento> idMov;

}