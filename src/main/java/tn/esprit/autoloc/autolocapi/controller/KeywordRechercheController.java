
package tn.esprit.autoloc.autolocapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.domain.agence;
import tn.esprit.autoloc.autolocapi.domain.reservation;
import tn.esprit.autoloc.autolocapi.domain.CategorieVehicule;

import tn.esprit.autoloc.autolocapi.service.KeywordRechercheService;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Keywords")
@Tag(
        name = "Atelier Keywords Spring Data JPA",
        description = "Questions 1, 2, 3, 4, 5, 8, 11 et 14"
)
public class KeywordRechercheController {

    private final KeywordRechercheService service;

    // Question 1
    @Operation(summary = "Q1 - Véhicules par marque")
    @GetMapping("/q1/marque/{marque}")
    public List<Vehicule> question1(
            @PathVariable String marque) {

        return service.rechercherParMarque(marque);
    }

    // Question 2
    @Operation(summary = "Q2 - Véhicules par catégorie")
    @GetMapping("/q2/categorie/{categorie}")
    public List<Vehicule> question2(
            @PathVariable String categorie) {

        CategorieVehicule categorieEnum =
                CategorieVehicule.valueOf(
                        categorie.toUpperCase(Locale.ROOT)
                );

        return service.rechercherParCategorie(categorieEnum);
    }

    // Question 3
    @Operation(summary = "Q3 - Véhicules disponibles")
    @GetMapping("/q3/disponibles")
    public List<Vehicule> question3() {

        return service.rechercherDisponibles();
    }

    // Question 4
    @Operation(summary = "Q4 - Agences par ville")
    @GetMapping("/q4/ville/{ville}")
    public List<agence> question4(
            @PathVariable String ville) {

        return service.rechercherAgencesParVille(ville);
    }

    // Question 5
    @Operation(summary = "Q5 - Marque contenant un texte")
    @GetMapping("/q5/marque-contient/{texte}")
    public List<Vehicule> question5(
            @PathVariable String texte) {

        return service.rechercherMarqueContenant(texte);
    }

    // Question 8
    @Operation(summary = "Q8 - Tarif supérieur à 100 DT")
    @GetMapping("/q8/tarif-superieur-100")
    public List<Vehicule> question8() {

        return service.rechercherTarifSuperieur100();
    }

    // Question 11
    @Operation(summary = "Q11 - Marque et statut DISPONIBLE")
    @GetMapping("/q11/marque-disponible/{marque}")
    public List<Vehicule> question11(
            @PathVariable String marque) {

        return service.rechercherMarqueDisponible(marque);
    }

    // Question 14
    @Operation(summary = "Q14 - Réservations après une date")
    @GetMapping("/q14/reservations-apres/{dateDebut}")
    public List<reservation> question14(
            @PathVariable
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dateDebut) {

        return service.rechercherReservationsApres(dateDebut);
    }
}
