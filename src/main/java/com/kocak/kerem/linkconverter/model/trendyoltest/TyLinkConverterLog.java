package com.kocak.kerem.linkconverter.model.trendyoltest;

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
@Table(name = "TY_LINK_CONVERTER_LOG")
public class TyLinkConverterLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @NotNull
    private int id;

    @Column(name = "URL")
    @Size(max = 450)
    @NotNull
    private String url;

    @Column(name = "DEEPLINK")
    @Size(max = 450)
    @NotNull
    private String deeplink;

    @Column(name = "CONVERSION_TYPE")
    @NotNull
    private int conversionType;

    @Column(name = "MATCH_TYPE")
    @NotNull
    private int matchType;

    @Column(name = "DATETIME")
    @Type(type = "timestamp")
    @NotNull
    private Date datetime;

    @Column(name = "USERNAME")
    @Size(max = 45)
    @NotNull
    private String username;

}