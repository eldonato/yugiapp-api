package don.leo.yugiapp.service.loja;

import don.leo.yugiapp.data.entities.Loja;

public record LojaRecord(
        Integer id,
        String nome
) {
    public LojaRecord(Loja loja) {
        this(
                loja.getId(),
                loja.getNome()
        );
    }
}
