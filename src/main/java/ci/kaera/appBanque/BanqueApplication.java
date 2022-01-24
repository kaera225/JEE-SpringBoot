package ci.kaera.appBanque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ci.kaera.appBanque.dao.ClientRepository;
import ci.kaera.appBanque.dao.CompteRepository;
import ci.kaera.appBanque.dao.OperationRepository;
import ci.kaera.appBanque.service.IBanqueMetier;

@SpringBootApplication

public class BanqueApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private IBanqueMetier banqueMetier;

	public static void main(String[] args) {
		SpringApplication.run(BanqueApplication.class, args);
//		ClientRepository clientRepository = ctx.getBean(ClientRepository.class);
//		clientRepository.save(new Client("SOw", "kaera@gmail.com"));
	}

	@Override
	public void run(String... args) throws Exception {

		/*
		 * Client c1 = clientRepository.save(new Client("PAULINE", "kaera@gmail.com"));
		 * Client c2 = clientRepository.save(new Client("KOFFI", "kaera@gmail.com"));
		 * Client c3 = clientRepository.save(new Client("JEAN", "kaera@gmail.com"));
		 * Client c4 = clientRepository.save(new Client("KONE", "kaera@gmail.com"));
		 * 
		 * Compte cpt0 = compteRepository.save(new CompteCourant("COMPTE0001", new
		 * Date(), 50000, c1, 20)); Compte cpt1 = compteRepository.save(new
		 * CompteEpargne("COMPTE0002", new Date(), 5.5, c2, 18.05)); Compte cpt2 =
		 * compteRepository.save(new CompteCourant("COMPTE0003", new Date(), 30000, c3,
		 * 0)); Compte cpt3 = compteRepository.save(new CompteEpargne("COMPTE0004", new
		 * Date(), 100000, c4, 6.6));
		 */

	}

}
