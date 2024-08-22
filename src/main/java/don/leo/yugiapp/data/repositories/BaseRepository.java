package don.leo.yugiapp.data.repositories;

import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@NoRepositoryBean
interface BaseRepository<T> extends JpaRepository<T, Integer>, QuerydslPredicateExecutor<T> {

    default List<T> listar(Predicate predicate) {

        return StreamSupport
                .stream(findAll(predicate).spliterator(), false)
                .toList();
    }

    default Optional<T> buscarUm(Predicate predicate) {
        return findOne(predicate);
    }
}
