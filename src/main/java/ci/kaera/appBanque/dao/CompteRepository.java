package ci.kaera.appBanque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.kaera.appBanque.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, String> {

}
