package net.j22.cloud.jwtauthapi.services;

import net.j22.cloud.jwtauthapi.dao.entities.Avis;
import net.j22.cloud.jwtauthapi.dao.entities.Customer;
import net.j22.cloud.jwtauthapi.dao.repository.AvisRepository;
import net.j22.cloud.jwtauthapi.dao.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AvisService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AvisRepository avisRepository;

    public Avis addAvis(Integer customerId, Avis avis) {
        Optional<Customer> utilisateurOptional = customerRepository.findById(customerId);
        if (utilisateurOptional.isPresent()) {
            Customer customer = utilisateurOptional.get();
            avis.setCustomer(customer);  // Associe l'avis à l'utilisateur trouvé
            return avisRepository.save(avis);  // Sauvegarde l'avis dans la base de données
        } else {
            throw new RuntimeException("Utilisateur non trouvé avec l'ID : " + customerId);
        }
    }

}
