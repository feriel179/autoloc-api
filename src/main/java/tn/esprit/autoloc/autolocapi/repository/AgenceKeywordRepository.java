
package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.autoloc.autolocapi.domain.agence;

import java.util.List;

public interface AgenceKeywordRepository
        extends JpaRepository<agence, Long> {

    // Q4 : Rechercher les agences d'une ville
    List<agence> findByVille(String ville);
}
