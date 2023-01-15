package vmbears.com.br.xmlimport.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vmbears.com.br.xmlimport.entity.AgenteEntity;
import vmbears.com.br.xmlimport.entity.ImportacaoAgenteEntity;
import vmbears.com.br.xmlimport.entity.ImportacaoAgenteRegiaoDetalheEntity;
import vmbears.com.br.xmlimport.entity.ImportacaoAgenteRegiaoEntity;
import vmbears.com.br.xmlimport.entity.ImportacaoEntity;
import vmbears.com.br.xmlimport.entity.RegiaoEntity;
import vmbears.com.br.xmlimport.model.Agente;
import vmbears.com.br.xmlimport.model.Agentes;
import vmbears.com.br.xmlimport.model.Regiao;
import vmbears.com.br.xmlimport.repository.AgenteRepository;
import vmbears.com.br.xmlimport.repository.ImportacaoAgenteRegiaoRepository;
import vmbears.com.br.xmlimport.repository.ImportacaoAgenteRepository;
import vmbears.com.br.xmlimport.repository.ImportacaoRepository;
import vmbears.com.br.xmlimport.repository.RegiaoRepository;

@Service
public class XMLService {

    @Autowired
    private AgenteRepository agenteRepository;

    @Autowired
    private RegiaoRepository regiaoRepository;

    @Autowired
    private ImportacaoRepository importacaoRepository;

    @Autowired
    private ImportacaoAgenteRepository importacaoAgenteRepository;

    @Autowired
    private ImportacaoAgenteRegiaoRepository importacaoAgenteRegiaoRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Agentes parse(MultipartFile file) throws IOException {
        XStream xstream = new XStream();
        xstream.autodetectAnnotations(false);
        xstream.addPermission(NoTypePermission.NONE);
        xstream.allowTypesByWildcard(new String[] { "vmbears.com.br.xmlimport.model.**" });

        xstream.processAnnotations(Agentes.class);
        return (Agentes) xstream.fromXML(file.getInputStream());
    }

    public void printCodigoAgentes(Agentes agentes) {
        agentes.getAgentes().forEach(agente -> {
            System.out.println("Código do agente: " + agente.getCodigo());
        });
    }

    public int importAgentes(Agentes agentes, String fileName) {
        List<ImportacaoAgenteRegiaoDetalheEntity> importacaoAgenteRegiaoDetalheEntities = new ArrayList<>();

        int fileCount = agentes.getAgentes().size();
        LocalDateTime agora = LocalDateTime.now();

        ImportacaoEntity importacaoEntity = importacaoRepository.save(new ImportacaoEntity(fileName, fileCount, agora));

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        for (Agente agente : agentes.getAgentes()) {
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(agente.getData());
            LocalDateTime data = zonedDateTime.toLocalDateTime();

            AgenteEntity agenteEntity = agenteRepository.save(new AgenteEntity(agente));
            ImportacaoAgenteEntity importacaoAgenteEntity = importacaoAgenteRepository
                    .save(new ImportacaoAgenteEntity(importacaoEntity, agenteEntity, data));

            for (Regiao regiao : agente.getRegiao()) {
                RegiaoEntity regiaoEntity = regiaoRepository.save(new RegiaoEntity(regiao));
                ImportacaoAgenteRegiaoEntity importacaoAgenteRegiaoEntity = importacaoAgenteRegiaoRepository
                        .save(new ImportacaoAgenteRegiaoEntity(importacaoAgenteEntity, regiaoEntity));
                for (int i = 0; i < 7; i++) {
                    Double compra = regiao.getCompras().get(0).getValores().get(i);
                    Double geracao = regiao.getGeracoes().get(0).getValores().get(i);
                    importacaoAgenteRegiaoDetalheEntities.add(
                            new ImportacaoAgenteRegiaoDetalheEntity(importacaoAgenteRegiaoEntity, compra, geracao));
                }
            }
        }

        importacaoAgenteRegiaoDetalheEntities.forEach(em::persist);

        em.getTransaction().commit();

        em.close();

        return importacaoEntity.getId();
    }

}