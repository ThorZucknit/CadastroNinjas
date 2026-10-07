package dev.java10x.CadastroDeNinjas.Ninjas;
import java.util.List;
import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//Entity transforma uma classe em entidade do BD
@Entity
@Table(name = "tb_cadastro_de_ninjas")
@NoArgsConstructor//Cria o construtor sem os ARGS
@AllArgsConstructor//Cria o construtor com todos os ARGS
@Data //Cria todos os Getter/Setter
public class NinjaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column (unique = true) //Coluna é unica, não pode ter emails duplicados
    private String email;
    private int idade;

    @ManyToOne // @ManyToOne um Ninja têm uma unica missão
    @JoinColumn(name = "missoes_id")//Foreing Key ou Chave estrangeira
    private MissoesModel missoes;



}
