package don.leo.yugiapp.service.pessoa;

import com.querydsl.core.BooleanBuilder;
import don.leo.yugiapp.data.entities.QPessoa;

public class PessoaPredicate {

    public static BooleanBuilder criarPredicate(FiltroPessoa filtro) {
        var predicate = new BooleanBuilder();

        if (filtro.nome() != null) {
            predicate.and(comNome(filtro.nome()));
        }

        return predicate;
    }

    public static BooleanBuilder comNome(String nome) {
        return new BooleanBuilder().and(QPessoa.pessoa.nome.containsIgnoreCase(nome));
    }
}
