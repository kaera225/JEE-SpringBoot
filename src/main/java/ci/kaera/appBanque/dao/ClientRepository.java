package ci.kaera.appBanque.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.kaera.appBanque.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
