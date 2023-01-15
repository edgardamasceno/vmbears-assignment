package vmbears.com.br.xmlimport.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vmbears.com.br.xmlimport.service.ConsolidacaoService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/consolidacao")
public class ConsolidacaoController {

    @Autowired
    private ConsolidacaoService service;

    @GetMapping(path = { "", "/" })
    public ResponseEntity<List<Map<String, Object>>> getGeracaoPrecoByImportacaoId() {
        List<Map<String, Object>> geracaoPrecoData = service.consolicacaoDeGeracaoCompraGroupedByRegiao();
        return ResponseEntity.ok(geracaoPrecoData);
    }

    @GetMapping("/{importacaoId}")
    public ResponseEntity<List<Map<String, Object>>> getGeracaoPrecoByImportacaoId(@PathVariable int importacaoId) {
        List<Map<String, Object>> geracaoPrecoData = service.consolicacaoDeGeracaoCompraGroupedByRegiao(importacaoId);
        return ResponseEntity.ok(geracaoPrecoData);
    }
}
