
package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.domain.CategorieVehicule;
import tn.esprit.autoloc.autolocapi.domain.StatutVehicule;

import java.math.BigDecimal;
import java.util.List;

public interface VehiculeKeywordRepository
        extends JpaRepository<Vehicule, Long> {

    // Q1 : Rechercher par marque exacte
    List<Vehicule> findByMarque(String marque);

    // Q2 : Rechercher par catégorie
    List<Vehicule> findByCategorie(CategorieVehicule categorie);

    // Q3 : Rechercher par statut
    List<Vehicule> findByStatut(StatutVehicule statut);

    // Q5 : Rechercher une marque contenant un texte
    List<Vehicule> findByMarqueContainingIgnoreCase(String texte);

    // Q8 : Rechercher les véhicules avec tarif > 100 DT
    List<Vehicule> findByTarifJournalierGreaterThan(
            BigDecimal tarif
    );

    // Q11 : Marque exacte ET statut
    List<Vehicule> findByMarqueAndStatut(
            String marque,
            StatutVehicule statut
    );
}
