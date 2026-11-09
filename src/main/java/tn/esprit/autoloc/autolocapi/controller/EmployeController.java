
package tn.esprit.autoloc.autolocapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc.autolocapi.domain.employe;
import tn.esprit.autoloc.autolocapi.service.IEmployeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Employe")
public class EmployeController {

    private final IEmployeService employeService;

    // 1. Ajouter un employé
    @PostMapping("/addEmploye")
    public employe addEmploye(@RequestBody employe e) {
        return employeService.addEmploye(e);
    }

    // 2. Afficher tous les employés
    @GetMapping("/retrieveAllEmployes")
    public List<employe> retrieveAllEmployes() {
        return employeService.retrieveAllEmployes();
    }

    // 3. Afficher un employé par ID
    @GetMapping("/retrieveEmploye/{idEmploye}")
    public employe retrieveEmploye(@PathVariable Long idEmploye) {
        return employeService.retrieveEmploye(idEmploye);
    }

    // 4. Modifier un employé
    @PutMapping("/updateEmploye")
    public employe updateEmploye(@RequestBody employe e) {
        return employeService.updateEmploye(e);
    }

    // 5. Supprimer un employé
    @DeleteMapping("/deleteEmploye/{idEmploye}")
    public void deleteEmploye(@PathVariable Long idEmploye) {
        employeService.removeEmploye(idEmploye);
    }

    // 6. Ajouter plusieurs employés
    @PostMapping("/addEmployes")
    public List<employe> addEmployes(@RequestBody List<employe> employes) {
        return employeService.addEmployes(employes);
    }

    // 7. Désaffecter un employé de son agence
    @PutMapping("/desaffecterEmploye/{idEmploye}")
    public employe desaffecterEmploye(
            @PathVariable Long idEmploye) {

        return employeService.desaffecterEmploye(idEmploye);
    }

    // 8. Affecter un employé à une agence
    @PutMapping("/affecterEmploye/{idEmploye}/{idAgence}")
    public employe affecterEmploye(
            @PathVariable Long idEmploye,
            @PathVariable Long idAgence) {

        return employeService.affecterEmploye(idEmploye, idAgence);
    }
}
