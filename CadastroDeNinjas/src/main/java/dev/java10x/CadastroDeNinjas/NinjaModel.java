package dev.java10x.CadastroDeNinjas;

import jakarta.persistence.*;


//Entity transforma uma classe em entidade do BD
@Entity
@Table(name = "tb_cadastro_de_ninjas")

public class NinjaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    String email;
    int idade;

    public NinjaModel() {

    }

    public NinjaModel(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }


}
