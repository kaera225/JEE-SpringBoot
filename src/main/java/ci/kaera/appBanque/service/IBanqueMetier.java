package ci.kaera.appBanque.service;

import org.springframework.data.domain.Page;

import ci.kaera.appBanque.entities.Compte;
import ci.kaera.appBanque.entities.Operation;
import javassist.NotFoundException;

public interface IBanqueMetier {

	public Compte consulterCompte(String codeCpte) throws NotFoundException;

	public void verser(String codeCpte, double montant);

	public void retier(String codeCpte, double montant);

	public void virement(String codeCpte1, String codeCpte2, double montant);

	public Page<Operation> listeOperation(String codeCpte, int page, int size);

}
