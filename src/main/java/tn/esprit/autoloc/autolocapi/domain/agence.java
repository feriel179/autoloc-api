
package tn.esprit.autoloc.autolocapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "agence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 100)
    private String ville;

    @Column(length = 255)
    private String adresse;

    @Column(length = 20)
    private String telephone;

    // Relation avec les employés
    // Empêche une boucle JSON : agence -> employes -> agence
    @JsonIgnore
    @OneToMany(mappedBy = "agence")
    private List<employe> employes;

    // Relation avec les véhicules
    // Empêche une boucle JSON : agence -> vehicules -> agence
    @JsonIgnore
    @OneToMany(mappedBy = "agence")
    private List<Vehicule> vehicules;
}
