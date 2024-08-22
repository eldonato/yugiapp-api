package don.leo.yugiapp.service.jogador;

import com.querydsl.core.BooleanBuilder;
import don.leo.yugiapp.data.entities.QJogador;

public class JogadorPredicate {

    public static BooleanBuilder criarPredicate(FiltroJogador filtro) {
        var predicate = new BooleanBuilder();

        if (filtro.kossy() != null) {
            predicate.and(comKossy(filtro.kossy()));
        }

        return predicate;
    }

    public static BooleanBuilder comKossy(String kossy) {
        return new BooleanBuilder().and(QJogador.jogador.kossy.containsIgnoreCase(kossy));
    }
}
