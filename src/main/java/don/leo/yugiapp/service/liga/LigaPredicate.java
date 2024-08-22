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
            predicate.and(emAndamentoEm(filtro.data()));
        }

        return predicate;
    }

    public static BooleanBuilder comDescricao(String descricao) {
        return new BooleanBuilder().and(QLiga.liga.descricao.containsIgnoreCase(descricao));
    }

    public static BooleanBuilder emAndamentoEm(LocalDate data) {
        return new BooleanBuilder().and(
                        QLiga.liga.dataInicio.before(data)
                        .and(QLiga.liga.dataFim.after(data))
        );
    }
}
