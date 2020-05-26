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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jramirez
 */
@Entity
@Table(name = "administradorsupremo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administradorsupremo.findAll", query = "SELECT a FROM Administradorsupremo a")
    , @NamedQuery(name = "Administradorsupremo.findById", query = "SELECT a FROM Administradorsupremo a WHERE a.id = :id")
    , @NamedQuery(name = "Administradorsupremo.findByUser", query = "SELECT a FROM Administradorsupremo a WHERE a.user = :user")
    , @NamedQuery(name = "Administradorsupremo.findByPassword", query = "SELECT a FROM Administradorsupremo a WHERE a.password = :password")})
public class Administradorsupremo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user")
    private String user;
    @Column(name = "password")
    private String password;

    public Administradorsupremo() {
    }

    public Administradorsupremo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(object instanceof Administradorsupremo)) {
            return false;
        }
        Administradorsupremo other = (Administradorsupremo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Administradorsupremo[ id=" + id + " ]";
    }
    
}
