package vmbears.com.br.xmlimport.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "importacao")
public class ImportacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fileName;

    private int fileCount;

    private LocalDateTime fileImportedAt;

    public ImportacaoEntity(String fileName, int fileCount, LocalDateTime fileImportedAt) {
        this.fileName = fileName;
        this.fileCount = fileCount;
        this.fileImportedAt = fileImportedAt;
    }

}
