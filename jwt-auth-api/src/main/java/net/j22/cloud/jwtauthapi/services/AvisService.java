package net.j22.cloud.jwtauthapi.services;

import net.j22.cloud.jwtauthapi.dao.entities.Avis;
import net.j22.cloud.jwtauthapi.dao.entities.Customer;
import net.j22.cloud.jwtauthapi.dao.repository.AvisRepository;
import net.j22.cloud.jwtauthapi.dao.repository.CustomerRepository;
import net.j22.cloud.jwtauthapi.dtos.AvisRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AvisService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AvisRepository avisRepository;

    public Avis addAvis(Integer customerId, AvisRequest avisRequest) {
        Optional<Customer> utilisateurOptional = customerRepository.findById(customerId);
        if (utilisateurOptional.isPresent()) {
            Customer customer = utilisateurOptional.get();

            // Créez une nouvelle instance d'Avis avec les données du DTO
            Avis avis = Avis.builder()
                    .content(avisRequest.getContent())  // Utilisez les données du DTO
                    .customer(customer)  // Associez le client trouvé à l'avis
                    .build();

            // Sauvegardez l'avis dans la base de données
            return avisRepository.save(avis);
        } else {
            throw new RuntimeException("Utilisateur non trouvé avec l'ID : " + customerId);
        }
    }

}
