package vmbears.com.br.xmlimport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vmbears.com.br.xmlimport.entity.RegiaoEntity;

@Repository
public interface RegiaoRepository extends JpaRepository<RegiaoEntity, Integer> {
}
