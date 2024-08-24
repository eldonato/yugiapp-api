package don.leo.yugiapp.data.repositories;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
interface BaseRepository<T> extends JpaRepository<T, Integer>, ListQuerydslPredicateExecutor<T> {

    default List<T> listar(Predicate predicate) {
        return findAll(predicate);
    }

    default List<T> listar(Predicate predicate, OrderSpecifier<?>... orders) {
        return findAll(predicate, orders);
    }

    default Optional<T> buscarUm(Predicate predicate) {
        return findOne(predicate);
    }
}
