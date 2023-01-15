package vmbears.com.br.xmlimport.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vmbears.com.br.xmlimport.repository.ImportacaoAgenteRegiaoDetalheRepository;

@Service
public class ConsolidacaoService {

    @Autowired
    private ImportacaoAgenteRegiaoDetalheRepository repository;

    public List<Map<String, Object>> consolicacaoDeGeracaoCompraGroupedByRegiao(int importacaoId) {
        return repository.consolicacaoDeGeracaoCompraGroupedByRegiao(importacaoId);
    }

    public List<Map<String, Object>> consolicacaoDeGeracaoCompraGroupedByRegiao() {
        return repository.consolicacaoDeGeracaoCompraGroupedByRegiao();
    }
}