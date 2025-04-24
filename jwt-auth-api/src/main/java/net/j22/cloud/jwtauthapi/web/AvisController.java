package net.j22.cloud.jwtauthapi.web;

import net.j22.cloud.jwtauthapi.dao.entities.Avis;
import net.j22.cloud.jwtauthapi.dtos.AvisRequest;
import net.j22.cloud.jwtauthapi.services.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AvisController {

    @Autowired
    private AvisService avisService;

    @PostMapping("/{customerId}")
    public ResponseEntity<Avis> addAvis(@PathVariable Integer customerId, @RequestBody AvisRequest avis) {
        // Ajout de l'avis et association à l'utilisateur
        return ResponseEntity.ok(avisService.addAvis(customerId, avis));
    }
}
