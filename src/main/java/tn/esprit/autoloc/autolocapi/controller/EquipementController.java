
package tn.esprit.autoloc.autolocapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc.autolocapi.domain.equipement;
import tn.esprit.autoloc.autolocapi.service.IEquipementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Equipement")
@Tag(
        name = "Gestion des équipements",
        description = "API de gestion des équipements des véhicules"
)
public class EquipementController {

    private final IEquipementService equipementService;

    // 1. Ajouter un équipement
    @Operation(summary = "Ajouter un équipement")
    @ApiResponse(
            responseCode = "200",
            description = "Équipement ajouté avec succès"
    )
    @PostMapping("/addEquipement")
    public equipement addEquipement(@RequestBody equipement e) {
        return equipementService.addEquipement(e);
    }

    // 2. Afficher tous les équipements
    @Operation(summary = "Afficher tous les équipements")
    @ApiResponse(responseCode = "200", description = "Liste récupérée")
    @GetMapping("/retrieveAllEquipements")
    public List<equipement> retrieveAllEquipements() {
        return equipementService.retrieveAllEquipements();
    }

    // 3. Afficher un équipement par ID
    @Operation(summary = "Rechercher un équipement par ID")
    @ApiResponse(responseCode = "200", description = "Recherche effectuée")
    @GetMapping("/retrieveEquipement/{idEquipement}")
    public equipement retrieveEquipement(
            @PathVariable Long idEquipement) {

        return equipementService.retrieveEquipement(idEquipement);
    }

    // 4. Modifier un équipement
    @Operation(summary = "Modifier un équipement")
    @ApiResponse(responseCode = "200", description = "Modification effectuée")
    @PutMapping("/updateEquipement")
    public equipement updateEquipement(@RequestBody equipement e) {
        return equipementService.updateEquipement(e);
    }

    // 5. Supprimer un équipement
    @Operation(summary = "Supprimer un équipement")
    @ApiResponse(responseCode = "200", description = "Suppression effectuée")
    @DeleteMapping("/deleteEquipement/{idEquipement}")
    public void deleteEquipement(@PathVariable Long idEquipement) {
        equipementService.removeEquipement(idEquipement);
    }

    // 6. Ajouter plusieurs équipements
    @Operation(summary = "Ajouter plusieurs équipements")
    @ApiResponse(responseCode = "200", description = "Équipements ajoutés")
    @PostMapping("/addEquipements")
    public List<equipement> addEquipements(
            @RequestBody List<equipement> equipements) {

        return equipementService.addEquipements(equipements);
    }
}
