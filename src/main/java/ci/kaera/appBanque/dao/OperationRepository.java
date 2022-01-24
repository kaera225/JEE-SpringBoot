package ci.kaera.appBanque.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ci.kaera.appBanque.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {

	@Query("select o from Operation o where o.compte.codeCompte=:X order by o.dateOperation desc")
	public Page<Operation> listeOperation(@Param("X") String codeCpte, Pageable pageable);

}
