package don.leo.yugiapp.service.liga;

import com.querydsl.core.BooleanBuilder;
import don.leo.yugiapp.data.entities.QLiga;

import java.time.LocalDate;

public class LigaPredicate {

    public static BooleanBuilder criarPredicate(FiltroLiga filtro) {
        var predicate = new BooleanBuilder();

        if (filtro.descricao() != null) {
            predicate.and(comDescricao(filtro.descricao()));
        }

        if (filtro.data() != null) {
            predicate.and(emAndamento(filtro.data()));
        }

        if (filtro.id() != null) {
            predicate.and(comId(filtro.id()));
        }

        return predicate;
    }

    private static BooleanBuilder comId(Integer id) {
        return new BooleanBuilder().and(QLiga.liga.id.eq(id));
    }

    public static BooleanBuilder comDescricao(String descricao) {
        return new BooleanBuilder().and(QLiga.liga.descricao.containsIgnoreCase(descricao));
    }

    public static BooleanBuilder emAndamento(LocalDate data) {
        return new BooleanBuilder().and(
                        QLiga.liga.dataInicio.loe(data)
                        .and(QLiga.liga.dataFim.goe(data))
        );
    }
}
