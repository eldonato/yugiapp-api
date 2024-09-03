package don.leo.yugiapp.data.repositories;

import com.querydsl.core.types.Predicate;
import don.leo.yugiapp.data.entities.Usuario;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID>,
        ListQuerydslPredicateExecutor<Usuario> {

    @EntityGraph(attributePaths = {"pessoa", "permissoes"})
    Optional<Usuario> findByUsername(String username);

    @Nonnull
    @EntityGraph(attributePaths = {"pessoa", "permissoes"})
    Optional<Usuario> findById(@Nonnull UUID id);

    @Nonnull
    @EntityGraph(attributePaths = {"pessoa", "permissoes"})
    List<Usuario> findAll(@Nonnull Predicate predicate);
}
