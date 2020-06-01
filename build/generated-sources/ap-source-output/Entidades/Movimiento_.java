package Entidades;

import Entidades.Cliente;
import Entidades.ProductoMovimiento;
import Entidades.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-01T17:06:57")
@StaticMetamodel(Movimiento.class)
public class Movimiento_ { 

    public static volatile SingularAttribute<Movimiento, String> descripcion;
    public static volatile SingularAttribute<Movimiento, Integer> idRemitente;
    public static volatile SingularAttribute<Movimiento, Usuario> usuarioTrans;
    public static volatile SingularAttribute<Movimiento, Cliente> idCliente;
    public static volatile ListAttribute<Movimiento, ProductoMovimiento> productoMovimientoList;
    public static volatile SingularAttribute<Movimiento, Double> descuentoAplicado;
    public static volatile SingularAttribute<Movimiento, Integer> id;
    public static volatile SingularAttribute<Movimiento, Date> fechaMovimiento;
    public static volatile SingularAttribute<Movimiento, String> tipoMov;
    public static volatile SingularAttribute<Movimiento, Integer> idDestino;

}