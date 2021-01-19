package com.kocak.kerem.mavidev.model.mavidev;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "MD_USER")
public class MdUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @NotNull
    private int id;

    @Column(name = "NAME")
    @Size(max = 45)
    @NotNull
    private String name;

    @Column(name = "SURNAME")
    @Size(max = 45)
    @NotNull
    private String surname;

    @Column(name = "BIRTH_CITY")
    @Size(max = 45)
    @NotNull
    private String birthCity;

    @Column(name = "CREATE_DATE")
    @Type(type = "timestamp")
    @NotNull
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @Type(type = "timestamp")
    private Date updateDate;

    @Column(name = "CREATE_USER")
    @Size(max = 45)
    @NotNull
    private String createUser;

    @Column(name = "UPDATE_USER")
    @Size(max = 45)
    private String updateUser;

    @Column(name = "DELETE_DATE")
    @Type(type = "timestamp")
    private Date deleteDate;

    @Column(name = "DELETE_USER")
    @Size(max = 45)
    private String deleteUser;

    @Column(name = "STATUS")
    @NotNull
    private Integer status;
}