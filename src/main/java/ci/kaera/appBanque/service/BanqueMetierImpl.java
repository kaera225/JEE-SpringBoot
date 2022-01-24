package ci.kaera.appBanque.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ci.kaera.appBanque.dao.CompteRepository;
import ci.kaera.appBanque.dao.OperationRepository;
import ci.kaera.appBanque.entities.Compte;
import ci.kaera.appBanque.entities.CompteCourant;
import ci.kaera.appBanque.entities.Operation;
import ci.kaera.appBanque.entities.Retrait;
import ci.kaera.appBanque.entities.Versement;
import javassist.NotFoundException;

@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {

	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(String codeCpte) throws NotFoundException {
		Optional<Compte> cp = compteRepository.findById(codeCpte);
		return cp.orElseThrow(() -> new NotFoundException("Unable to get Account with Code = " + codeCpte));
	}

	@Override
	public void verser(String codeCpte, double montant) {
		Compte cp;
		try {
			cp = consulterCompte(codeCpte);
			Versement v = new Versement(new Date(), montant, cp);
			operationRepository.save(v);
			cp.setSolde(cp.getSolde() + montant);
			compteRepository.save(cp);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void retier(String codeCpte, double montant) {
		Compte cp;
		try {
			cp = consulterCompte(codeCpte);
			double faciliteDeCaisee = 0;
			if (cp instanceof CompteCourant) {
				faciliteDeCaisee = ((CompteCourant) cp).getDecouvert();
			}
			if (cp.getSolde() + faciliteDeCaisee < 0) {
				throw new RuntimeException("Solde Insuiffisant");
			}
			if (cp.getSolde() < montant) {
				throw new RuntimeException("Solde Insuffisant");
			}
			Retrait r = new Retrait(new Date(), montant, cp);
			operationRepository.save(r);
			cp.setSolde(cp.getSolde() - montant);
			compteRepository.save(cp);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		if (codeCpte1.equals(codeCpte2)) {
			throw new RuntimeException("Impossible virement sur le meme compte");
		}
		retier(codeCpte1, montant);
		verser(codeCpte2, montant);

	}

	@Override
	public Page<Operation> listeOperation(String codeCpte, int page, int size) {
		return operationRepository.listeOperation(codeCpte, new QPageRequest(page, size));
	}

}
