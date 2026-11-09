
package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.domain.agence;
import tn.esprit.autoloc.autolocapi.domain.equipement;

import tn.esprit.autoloc.autolocapi.repository.VehiculeRepository;
import tn.esprit.autoloc.autolocapi.repository.AgenceRepository;
import tn.esprit.autoloc.autolocapi.repository.EquipementRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehiculeService implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceRepository agenceRepository;
    private final EquipementRepository equipementRepository;

    public VehiculeService(
            VehiculeRepository vehiculeRepository,
            AgenceRepository agenceRepository,
            EquipementRepository equipementRepository) {

        this.vehiculeRepository = vehiculeRepository;
        this.agenceRepository = agenceRepository;
        this.equipementRepository = equipementRepository;
    }

    // 1. Ajouter un véhicule
    @Override
    public Vehicule ajouterVehicule(Vehicule v) {
        return vehiculeRepository.save(v);
    }

    // 2. Afficher tous les véhicules
    @Override
    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    // 3. Rechercher un véhicule par ID
    @Override
    public Vehicule getVehiculeById(Long id) {
        return vehiculeRepository.findById(id).orElse(null);
    }

    // 4. Modifier un véhicule
    @Override
    public Vehicule modifierVehicule(Vehicule v) {
        return vehiculeRepository.save(v);
    }

    // 5. Supprimer un véhicule
    @Override
    public void supprimerVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }

    // 6. Affecter un véhicule à une agence
    @Override
    @Transactional
    public Vehicule affecterVehicule(
            Long idVehicule, Long idAgence) {

        Vehicule v = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() ->
                        new RuntimeException("Véhicule introuvable"));

        agence a = agenceRepository.findById(idAgence)
                .orElseThrow(() ->
                        new RuntimeException("Agence introuvable"));

        v.setAgence(a);

        return vehiculeRepository.save(v);
    }

    // 7. Affecter un équipement à un véhicule
    @Override
    @Transactional
    public Vehicule affecterEquipement(
            Long idVehicule, Long idEquipement) {

        Vehicule v = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() ->
                        new RuntimeException("Véhicule introuvable"));

        equipement e = equipementRepository.findById(idEquipement)
                .orElseThrow(() ->
                        new RuntimeException("Équipement introuvable"));

        if (v.getEquipements() == null) {
            v.setEquipements(new ArrayList<>());
        }

        boolean dejaAffecte = v.getEquipements().stream()
                .anyMatch(eq ->
                        eq.getIdEquipement().equals(idEquipement));

        if (!dejaAffecte) {
            v.getEquipements().add(e);
        }

        return vehiculeRepository.save(v);
    }

    // 8. Désaffecter un équipement d'un véhicule
    @Override
    @Transactional
    public Vehicule desaffecterEquipement(
            Long idVehicule, Long idEquipement) {

        Vehicule v = vehiculeRepository.findById(idVehicule)
                .orElseThrow(() ->
                        new RuntimeException("Véhicule introuvable"));

        if (!equipementRepository.existsById(idEquipement)) {
            throw new RuntimeException("Équipement introuvable");
        }

        if (v.getEquipements() != null) {
            v.getEquipements().removeIf(eq ->
                    eq.getIdEquipement().equals(idEquipement));
        }

        return vehiculeRepository.save(v);
    }
}
