package don.leo.yugiapp.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loja")
public class Loja {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loja_seq")
    @SequenceGenerator(name = "loja_seq", sequenceName = "loja_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "nome")
    private String nome;

}
