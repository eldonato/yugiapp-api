package don.leo.yugiapp.data.repositories;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import don.leo.yugiapp.data.entities.Classificacao;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassificacaoRepository extends BaseRepository<Classificacao> {

    @Nonnull
    @EntityGraph(attributePaths = {"torneio", "jogador.pessoa"})
    List<Classificacao> findAll(@Nonnull Predicate predicate, @Nonnull OrderSpecifier<?>... orders);
}
