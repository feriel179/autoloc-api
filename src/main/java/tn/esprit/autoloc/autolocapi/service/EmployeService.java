
package tn.esprit.autoloc.autolocapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.autoloc.autolocapi.domain.employe;
import tn.esprit.autoloc.autolocapi.domain.agence;
import tn.esprit.autoloc.autolocapi.repository.EmployeRepository;
import tn.esprit.autoloc.autolocapi.repository.AgenceRepository;

import java.util.List;

@Service
public class EmployeService implements IEmployeService {

    private final EmployeRepository employeRepo;
    private final AgenceRepository agenceRepo;

    public EmployeService(
            EmployeRepository employeRepo,
            AgenceRepository agenceRepo) {

        this.employeRepo = employeRepo;
        this.agenceRepo = agenceRepo;
    }

    @Override
    public List<employe> retrieveAllEmployes() {
        return (List<employe>) employeRepo.findAll();
    }

    @Override
    public employe addEmploye(employe e) {
        return employeRepo.save(e);
    }

    @Override
    public employe updateEmploye(employe e) {
        return employeRepo.save(e);
    }

    @Override
    public employe retrieveEmploye(Long idEmploye) {
        return employeRepo.findById(idEmploye).orElse(null);
    }

    @Override
    public void removeEmploye(Long idEmploye) {
        employeRepo.deleteById(idEmploye);
    }

    @Override
    public List<employe> addEmployes(List<employe> employes) {
        return (List<employe>) employeRepo.saveAll(employes);
    }

    // 1. Désaffecter un employé
    @Override
    @Transactional
    public employe desaffecterEmploye(Long idEmploye) {

        employe e = employeRepo.findById(idEmploye)
                .orElseThrow(() ->
                        new RuntimeException("Employé introuvable"));

        e.setAgence(null);

        return employeRepo.save(e);
    }

    // 2. Affecter un employé à une agence
    @Override
    @Transactional
    public employe affecterEmploye(
            Long idEmploye, Long idAgence) {

        employe e = employeRepo.findById(idEmploye)
                .orElseThrow(() ->
                        new RuntimeException("Employé introuvable"));

        agence a = agenceRepo.findById(idAgence)
                .orElseThrow(() ->
                        new RuntimeException("Agence introuvable"));

        if (e.getAgence() != null) {
            throw new IllegalStateException(
                    "Employé déjà affecté. Désaffectez-le d'abord."
            );
        }

        e.setAgence(a);

        return employeRepo.save(e);
    }
}
