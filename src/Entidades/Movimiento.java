/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jramirez
 */
@Entity
@Table(name = "movimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimiento.findAll", query = "SELECT m FROM Movimiento m")
    , @NamedQuery(name = "Movimiento.findById", query = "SELECT m FROM Movimiento m WHERE m.id = :id")
    , @NamedQuery(name = "Movimiento.findByFechaMovimiento", query = "SELECT m FROM Movimiento m WHERE m.fechaMovimiento = :fechaMovimiento")
    , @NamedQuery(name = "Movimiento.findByDescripcion", query = "SELECT m FROM Movimiento m WHERE m.descripcion = :descripcion")
    , @NamedQuery(name = "Movimiento.findByTipoMov", query = "SELECT m FROM Movimiento m WHERE m.tipoMov = :tipoMov")
    , @NamedQuery(name = "Movimiento.findByIdRemitente", query = "SELECT m FROM Movimiento m WHERE m.idRemitente = :idRemitente")
    , @NamedQuery(name = "Movimiento.findByIdDestino", query = "SELECT m FROM Movimiento m WHERE m.idDestino = :idDestino")
    , @NamedQuery(name = "Movimiento.findByDescuentoAplicado", query = "SELECT m FROM Movimiento m WHERE m.descuentoAplicado = :descuentoAplicado")})
public class Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha_movimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimiento;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "tipo_mov")
    private String tipoMov;
    @Column(name = "id_remitente")
    private Integer idRemitente;
    @Column(name = "id_destino")
    private Integer idDestino;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "descuento_aplicado")
    private Double descuentoAplicado;
    @OneToMany(mappedBy = "idMov")
    private List<ProductoMovimiento> productoMovimientoList;
    @JoinColumn(name = "usuario_trans", referencedColumnName = "id")
    @ManyToOne
    private Usuario usuarioTrans;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne
    private Cliente idCliente;

    public Movimiento() {
    }

    public Movimiento(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoMov() {
        return tipoMov;
    }

    public void setTipoMov(String tipoMov) {
        this.tipoMov = tipoMov;
    }

    public Integer getIdRemitente() {
        return idRemitente;
    }

    public void setIdRemitente(Integer idRemitente) {
        this.idRemitente = idRemitente;
    }

    public Integer getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Integer idDestino) {
        this.idDestino = idDestino;
    }

    public Double getDescuentoAplicado() {
        return descuentoAplicado;
    }

    public void setDescuentoAplicado(Double descuentoAplicado) {
        this.descuentoAplicado = descuentoAplicado;
    }

    @XmlTransient
    public List<ProductoMovimiento> getProductoMovimientoList() {
        return productoMovimientoList;
    }

    public void setProductoMovimientoList(List<ProductoMovimiento> productoMovimientoList) {
        this.productoMovimientoList = productoMovimientoList;
    }

    public Usuario getUsuarioTrans() {
        return usuarioTrans;
    }

    public void setUsuarioTrans(Usuario usuarioTrans) {
        this.usuarioTrans = usuarioTrans;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
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
        if (!(object instanceof Movimiento)) {
            return false;
        }
        Movimiento other = (Movimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Movimiento[ id=" + id + " ]";
    }
    
}
