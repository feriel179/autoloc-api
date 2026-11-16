
package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.autoloc.autolocapi.domain.reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationKeywordRepository
        extends JpaRepository<reservation, Long> {

    // Q14 : Date de début postérieure à la date indiquée
    List<reservation> findByDateDebutAfter(
            LocalDate dateDebut
    );
}
