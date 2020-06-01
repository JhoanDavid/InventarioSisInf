package Entidades;

import Entidades.Movimiento;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-01T17:06:57")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> barrio;
    public static volatile SingularAttribute<Cliente, String> ciudad;
    public static volatile SingularAttribute<Cliente, String> direccion;
    public static volatile ListAttribute<Cliente, Movimiento> movimientoList;
    public static volatile SingularAttribute<Cliente, Long> id;
    public static volatile SingularAttribute<Cliente, BigInteger> telefono;
    public static volatile SingularAttribute<Cliente, String> nombre;

}