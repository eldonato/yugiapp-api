package don.leo.yugiapp.service.classificacao.records;

import java.util.List;

public record ClassificacaoEmLoteRecord (
    Integer idTorneio,
    List<PontuacaoJogador> resultados
) { }

