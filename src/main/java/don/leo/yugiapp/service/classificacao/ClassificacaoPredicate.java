package don.leo.yugiapp.service.classificacao;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import don.leo.yugiapp.data.entities.QClassificacao;

public class ClassificacaoPredicate {

    public static BooleanBuilder criarPredicate(FiltroClassificacao filtro) {
        var predicate = new BooleanBuilder();

        if (filtro.deck() != null) {
            predicate.and(comDeck(filtro.deck()));
        }
        if (filtro.idTorneio() != null) {
            predicate.and(doTorneio(filtro.idTorneio()));
        }
        if (filtro.idJogador() != null) {
            predicate.and(doJogador(filtro.idJogador()));
        }

        return predicate;
    }

    public static BooleanExpression doJogador(Integer idJogador) {
        return QClassificacao.classificacao.jogador.id.eq(idJogador);
    }

    public static BooleanExpression doTorneio(Integer idTorneio) {
        return QClassificacao.classificacao.torneio.id.eq(idTorneio);
    }

    public static BooleanExpression comDeck(String deck) {
        return QClassificacao.classificacao.deck.containsIgnoreCase(deck);
    }
}
