package vmbears.com.br.xmlimport.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vmbears.com.br.xmlimport.entity.ImportacaoAgenteRegiaoDetalheEntity;

@Repository
public interface ImportacaoAgenteRegiaoDetalheRepository
                extends JpaRepository<ImportacaoAgenteRegiaoDetalheEntity, Integer> {

        @Query(value = "SELECT regiao.sigla as regiao, importacao_agente_regiao_detalhe.geracao as geracao, importacao_agente_regiao_detalhe.compra as compra, "
                        + "importacao_agente.agente_codigo as codigo_agente, importacao_agente.data as data "
                        + "FROM public.importacao_agente_regiao_detalhe "
                        + "JOIN public.importacao_agente_regiao ON importacao_agente_regiao.id = importacao_agente_regiao_detalhe.importacao_agente_regiao_id "
                        + "JOIN public.importacao_agente ON importacao_agente.id = importacao_agente_regiao.importacao_agente_id "
                        + "JOIN public.importacao ON importacao.id = importacao_agente.importacao_id "
                        + "JOIN public.regiao ON regiao.sigla = importacao_agente_regiao.regiao_sigla "
                        + "WHERE importacao.id = :importacaoId "
                        + "ORDER BY regiao.sigla ASC, data ASC", nativeQuery = true)
        List<Map<String, Object>> consolicacaoDeGeracaoCompraGroupedByRegiao(@Param("importacaoId") int importacaoId);

        @Query(value = "SELECT regiao.sigla as regiao, importacao_agente_regiao_detalhe.geracao as geracao, importacao_agente_regiao_detalhe.compra as compra, "
                        + "importacao_agente.agente_codigo as codigo_agente, importacao_agente.data as data "
                        + "FROM public.importacao_agente_regiao_detalhe "
                        + "JOIN public.importacao_agente_regiao ON importacao_agente_regiao.id = importacao_agente_regiao_detalhe.importacao_agente_regiao_id "
                        + "JOIN public.regiao ON regiao.sigla = importacao_agente_regiao.regiao_sigla "
                        + "JOIN public.importacao_agente ON importacao_agente.id = importacao_agente_regiao.importacao_agente_id "
                        + "ORDER BY regiao.sigla ASC, data ASC ", nativeQuery = true)
        List<Map<String, Object>> consolicacaoDeGeracaoCompraGroupedByRegiao();

}
