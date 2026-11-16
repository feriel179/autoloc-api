
package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.domain.agence;
import tn.esprit.autoloc.autolocapi.domain.reservation;
import tn.esprit.autoloc.autolocapi.domain.CategorieVehicule;
import tn.esprit.autoloc.autolocapi.domain.StatutVehicule;

import tn.esprit.autoloc.autolocapi.repository.VehiculeKeywordRepository;
import tn.esprit.autoloc.autolocapi.repository.AgenceKeywordRepository;
import tn.esprit.autoloc.autolocapi.repository.ReservationKeywordRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class KeywordRechercheService {

    private final VehiculeKeywordRepository vehiculeRepo;
    private final AgenceKeywordRepository agenceRepo;
    private final ReservationKeywordRepository reservationRepo;

    public KeywordRechercheService(
            VehiculeKeywordRepository vehiculeRepo,
            AgenceKeywordRepository agenceRepo,
            ReservationKeywordRepository reservationRepo) {

        this.vehiculeRepo = vehiculeRepo;
        this.agenceRepo = agenceRepo;
        this.reservationRepo = reservationRepo;
    }

    // Question 1
    public List<Vehicule> rechercherParMarque(String marque) {
        return vehiculeRepo.findByMarque(marque);
    }

    // Question 2
    public List<Vehicule> rechercherParCategorie(
            CategorieVehicule categorie) {

        return vehiculeRepo.findByCategorie(categorie);
    }

    // Question 3
    public List<Vehicule> rechercherDisponibles() {
        return vehiculeRepo.findByStatut(
                StatutVehicule.DISPONIBLE
        );
    }

    // Question 4
    public List<agence> rechercherAgencesParVille(String ville) {
        return agenceRepo.findByVille(ville);
    }

    // Question 5
    public List<Vehicule> rechercherMarqueContenant(
            String texte) {

        return vehiculeRepo.findByMarqueContainingIgnoreCase(
                texte
        );
    }

    // Question 8
    public List<Vehicule> rechercherTarifSuperieur100() {
        return vehiculeRepo.findByTarifJournalierGreaterThan(
                new BigDecimal("100")
        );
    }

    // Question 11
    public List<Vehicule> rechercherMarqueDisponible(
            String marque) {

        return vehiculeRepo.findByMarqueAndStatut(
                marque,
                StatutVehicule.DISPONIBLE
        );
    }

    // Question 14
    public List<reservation> rechercherReservationsApres(
            LocalDate dateDebut) {

        return reservationRepo.findByDateDebutAfter(
                dateDebut
        );
    }
}
