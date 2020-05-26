/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jramirez
 */
@Entity
@Table(name = "producto_movimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoMovimiento.findAll", query = "SELECT p FROM ProductoMovimiento p")
    , @NamedQuery(name = "ProductoMovimiento.findById", query = "SELECT p FROM ProductoMovimiento p WHERE p.id = :id")
    , @NamedQuery(name = "ProductoMovimiento.findByCantTrans", query = "SELECT p FROM ProductoMovimiento p WHERE p.cantTrans = :cantTrans")
    , @NamedQuery(name = "ProductoMovimiento.findByValorTrans", query = "SELECT p FROM ProductoMovimiento p WHERE p.valorTrans = :valorTrans")})
public class ProductoMovimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cant_trans")
    private Double cantTrans;
    @Column(name = "valor_trans")
    private Double valorTrans;
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    @ManyToOne
    private Producto idProducto;
    @JoinColumn(name = "id_mov", referencedColumnName = "id")
    @ManyToOne
    private Movimiento idMov;

    public ProductoMovimiento() {
    }

    public ProductoMovimiento(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCantTrans() {
        return cantTrans;
    }

    public void setCantTrans(Double cantTrans) {
        this.cantTrans = cantTrans;
    }

    public Double getValorTrans() {
        return valorTrans;
    }

    public void setValorTrans(Double valorTrans) {
        this.valorTrans = valorTrans;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Movimiento getIdMov() {
        return idMov;
    }

    public void setIdMov(Movimiento idMov) {
        this.idMov = idMov;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoMovimiento)) {
            return false;
        }
        ProductoMovimiento other = (ProductoMovimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ProductoMovimiento[ id=" + id + " ]";
    }
    
}
