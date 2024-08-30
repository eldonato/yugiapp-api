package don.leo.yugiapp.data.repositories;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import don.leo.yugiapp.data.entities.Jogador;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogadorRepository extends BaseRepository<Jogador> {

    @Nonnull
    @EntityGraph(attributePaths = {"pessoa"})
    List<Jogador> findAll(@Nonnull Predicate predicate, @Nonnull OrderSpecifier<?>... orders);
}
