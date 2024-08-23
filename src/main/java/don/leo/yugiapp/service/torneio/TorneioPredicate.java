package don.leo.yugiapp.service.torneio;

import com.querydsl.core.BooleanBuilder;
import don.leo.yugiapp.data.entities.QTorneio;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDate;

public class TorneioPredicate {

    public static BooleanBuilder criarPredicate(FiltroTorneio filtro) {
        var predicate = new BooleanBuilder();

        if (filtro.descricao() != null) {
            predicate.and(comDescricao(filtro.descricao()));
        }

        if (filtro.idLiga() != null) {
            predicate.and(daLiga(filtro.idLiga()));
        }

        if (filtro.idLoja() != null) {
            predicate.and(daLoja(filtro.idLoja()));
        }

        if (filtro.dataRealizacao() != null) {
            predicate.and(naData(filtro.dataRealizacao()));
        }

        if (ObjectUtils.anyNotNull(filtro.periodoInicio(), filtro.periodoFim())) {
            predicate.and(noPeriodo(filtro.periodoInicio(), filtro.periodoFim()));
        }

        return predicate;
    }

    private static BooleanBuilder comDescricao(String descricao) {
        return new BooleanBuilder().and(QTorneio.torneio.descricao.containsIgnoreCase(descricao));
    }

    public static BooleanBuilder daLiga(Integer idLiga) {
        return new BooleanBuilder().and(QTorneio.torneio.liga.id.eq(idLiga));
    }

    public static BooleanBuilder daLoja(Integer idLoja) {
        return new BooleanBuilder().and(QTorneio.torneio.loja.id.eq(idLoja));
    }

    public static BooleanBuilder naData(LocalDate data) {
        return new BooleanBuilder().and(QTorneio.torneio.data.eq(data));
    }

    private static BooleanBuilder noPeriodo(LocalDate inicio, LocalDate fim) {
        inicio = inicio != null ? inicio : LocalDate.MIN;
        fim = fim != null ? fim : LocalDate.MAX;

        return new BooleanBuilder().and(QTorneio.torneio.data.between(inicio, fim));
    }
}
