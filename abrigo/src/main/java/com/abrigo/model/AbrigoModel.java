package com.abrigo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AbrigoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer numeroMorador;

    @Column
    private Integer armarioPertences;


}
