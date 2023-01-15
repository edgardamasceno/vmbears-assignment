package vmbears.com.br.xmlimport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vmbears.com.br.xmlimport.model.Agentes;
import vmbears.com.br.xmlimport.service.XMLService;

@Controller
@CrossOrigin(origins = "*")
public class XMLController {

    @Autowired
    private XMLService xmlService;

    @PostMapping("/upload")
    public ResponseEntity<Integer> uploadFile(@RequestParam("file") MultipartFile file) {

        String fileName = file.getOriginalFilename();

        try {
            Agentes agentes = xmlService.parse(file);

            xmlService.printCodigoAgentes(agentes);

            int importId = xmlService.importAgentes(agentes, fileName);

            return ResponseEntity.status(HttpStatus.OK).body(importId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(0);
        }
    }
}
