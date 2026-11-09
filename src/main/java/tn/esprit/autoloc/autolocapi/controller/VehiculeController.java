
package tn.esprit.autoloc.autolocapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.service.IVehiculeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Vehicule")
@Tag(
        name = "Gestion des véhicules",
        description = "API de gestion des véhicules et des affectations"
)
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    // 1. Ajouter un véhicule
    @Operation(
            summary = "Ajouter un véhicule",
            description = "Permet d'enregistrer un nouveau véhicule"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Véhicule enregistré avec succès"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Données invalides"
    )
    @PostMapping("/addVehicule")
    public Vehicule addVehicule(@RequestBody Vehicule v) {
        return vehiculeService.ajouterVehicule(v);
    }

    // 2. Afficher tous les véhicules
    @Operation(summary = "Afficher tous les véhicules")
    @ApiResponse(
            responseCode = "200",
            description = "Liste des véhicules récupérée"
    )
    @GetMapping("/retrieveAllVehicules")
    public List<Vehicule> retrieveAllVehicules() {
        return vehiculeService.getAllVehicules();
    }

    // 3. Rechercher un véhicule par ID
    @Operation(summary = "Rechercher un véhicule par ID")
    @ApiResponse(
            responseCode = "200",
            description = "Recherche exécutée"
    )
    @GetMapping("/retrieveVehicule/{id}")
    public Vehicule retrieveVehicule(@PathVariable Long id) {
        return vehiculeService.getVehiculeById(id);
    }

    // 4. Modifier un véhicule
    @Operation(summary = "Modifier un véhicule")
    @ApiResponse(
            responseCode = "200",
            description = "Enregistrement effectué"
    )
    @PutMapping("/updateVehicule")
    public Vehicule updateVehicule(@RequestBody Vehicule v) {
        return vehiculeService.modifierVehicule(v);
    }

    // 5. Supprimer un véhicule
    @Operation(summary = "Supprimer un véhicule")
    @ApiResponse(
            responseCode = "200",
            description = "Suppression effectuée"
    )
    @DeleteMapping("/deleteVehicule/{id}")
    public void deleteVehicule(@PathVariable Long id) {
        vehiculeService.supprimerVehicule(id);
    }

    // 6. Affecter un véhicule à une agence
    @Operation(
            summary = "Affecter un véhicule à une agence",
            description = "Rattache un véhicule à une agence"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Véhicule affecté avec succès"
    )
    @PutMapping("/affecterVehicule/{idVehicule}/{idAgence}")
    public Vehicule affecterVehicule(
            @PathVariable Long idVehicule,
            @PathVariable Long idAgence) {

        return vehiculeService.affecterVehicule(
                idVehicule, idAgence
        );
    }

    // 7. Affecter un équipement à un véhicule
    @Operation(
            summary = "Affecter un équipement à un véhicule",
            description = "Associe un équipement existant à un véhicule"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Équipement affecté avec succès"
    )
    @PutMapping("/affecterEquipement/{idVehicule}/{idEquipement}")
    public Vehicule affecterEquipement(
            @PathVariable Long idVehicule,
            @PathVariable Long idEquipement) {

        return vehiculeService.affecterEquipement(
                idVehicule, idEquipement
        );
    }

    // 8. Désaffecter un équipement d'un véhicule
    @Operation(
            summary = "Désaffecter un équipement d'un véhicule",
            description = "Retire l'association entre un équipement et un véhicule"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Équipement désaffecté avec succès"
    )
    @PutMapping("/desaffecterEquipement/{idVehicule}/{idEquipement}")
    public Vehicule desaffecterEquipement(
            @PathVariable Long idVehicule,
            @PathVariable Long idEquipement) {

        return vehiculeService.desaffecterEquipement(
                idVehicule, idEquipement
        );
    }
}
