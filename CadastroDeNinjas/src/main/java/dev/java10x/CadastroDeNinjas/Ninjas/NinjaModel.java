package dev.java10x.CadastroDeNinjas.Ninjas;
import java.util.List;
import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


//Entity transforma uma classe em entidade do BD
@Entity
@Table(name = "tb_cadastro_de_ninjas")
@AllArgsConstructor
@NoArgsConstructor

public class NinjaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nome")
    private String nome;
    @Column (unique = true) //Coluna é unica, não pode ter emails duplicados
    private String email;

    @Column(name="img_url")
    private String imgUrl;
    @Column(name="idade")
    private int idade;
    @Column(name="rank")
    private String rank;

    @ManyToOne // @ManyToOne um Ninja têm uma unica missão
    @JoinColumn(name = "missoes_id")//Foreing Key ou Chave estrangeira
    private MissoesModel missoes;


    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getIdade() {
        return idade;
    }

    public MissoesModel getMissoes() {
        return missoes;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setMissoes(MissoesModel missoes) {
        this.missoes = missoes;
    }
}
