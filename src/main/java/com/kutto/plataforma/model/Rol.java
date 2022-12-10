package com.kutto.plataforma.model;

import com.kutto.plataforma.enums.RolNombre;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "tb_rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_ID_ROL", nullable = false)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "V_ROL_NOMBRE", nullable = false)
    private RolNombre rolNombre;

    public Rol() {
    }

    public Rol(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RolNombre getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
}
