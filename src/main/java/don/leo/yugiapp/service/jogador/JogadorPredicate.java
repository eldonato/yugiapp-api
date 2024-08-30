package don.leo.yugiapp.service.jogador;

import com.querydsl.core.BooleanBuilder;
import don.leo.yugiapp.data.entities.QJogador;

public class JogadorPredicate {

    public static BooleanBuilder criarPredicate(FiltroJogador filtro) {
        var predicate = new BooleanBuilder();

        if (filtro.kossy() != null) {
            predicate.and(comKossy(filtro.kossy()));
        }

        if (filtro.nome() != null) {
            predicate.and(comNome(filtro.nome()));
        }

        return predicate;
    }

    public static BooleanBuilder comKossy(String kossy) {
        return new BooleanBuilder().and(QJogador.jogador.kossy.containsIgnoreCase(kossy));
    }

    public static BooleanBuilder comNome(String nome) {
        return new BooleanBuilder().and(QJogador.jogador.pessoa.nome.containsIgnoreCase(nome));
    }
}
