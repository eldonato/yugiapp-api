package don.leo.yugiapp.service.loja;

import com.querydsl.core.BooleanBuilder;
import don.leo.yugiapp.data.entities.QLoja;

public class LojaPredicate {

    public static BooleanBuilder criarPredicate(FiltroLoja filtro) {
        var predicate = new BooleanBuilder();

        if (filtro.nome() != null) {
            predicate.and(comNome(filtro.nome()));
        }

        return predicate;
    }

    public static BooleanBuilder comNome(String nome) {
        return new BooleanBuilder().and(QLoja.loja.nome.containsIgnoreCase(nome));
    }
}
