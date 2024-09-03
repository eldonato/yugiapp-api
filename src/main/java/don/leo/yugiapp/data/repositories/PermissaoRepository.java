package don.leo.yugiapp.data.repositories;

import don.leo.yugiapp.data.entities.Permissao;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends BaseRepository<Permissao> {
    Permissao findByDescricao(String descricao);
}
