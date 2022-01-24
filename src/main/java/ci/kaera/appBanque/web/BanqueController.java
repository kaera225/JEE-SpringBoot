package ci.kaera.appBanque.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ci.kaera.appBanque.entities.Compte;
import ci.kaera.appBanque.entities.Operation;
import ci.kaera.appBanque.service.IBanqueMetier;

@Controller
public class BanqueController {

	@Autowired
	private IBanqueMetier banqueMetier;

	@RequestMapping("/operations")
	public String index() {
		return "comptes";
	}

	@RequestMapping(value = "/consultercompte", method = RequestMethod.GET)
	public String consulter(Model model, String codeCompte, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "size", defaultValue = "4") int s) {
		model.addAttribute("codeCompte", codeCompte);
		try {
			Compte cp = banqueMetier.consulterCompte(codeCompte);
			Page<Operation> pageOperation = banqueMetier.listeOperation(codeCompte, p, s);
			int[] pages = new int[pageOperation.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("listeOperation", pageOperation.getContent());
			model.addAttribute("compte", cp);
		} catch (Exception e) {
			model.addAttribute("exception", e);
		}
		return "comptes";
	}

	@RequestMapping(value = "/saveOperation", method = RequestMethod.POST)
	public String saveOperation(Model model, String typeOperation, String codeCompte, double montant,
			String codeCompte2) {
		try {
			if (typeOperation.equals("VERS")) {
				banqueMetier.verser(codeCompte, montant);
			} else if (typeOperation.equals("RETRAIT")) {
				banqueMetier.retier(codeCompte, montant);
			} else {
				banqueMetier.virement(codeCompte, codeCompte2, montant);
			}

		} catch (Exception e) {
			model.addAttribute("error", e);
			return "redirect:/consultercompte?codeCompte=" + codeCompte + "&error=" + e.getMessage();
		}

		return "redirect:/consultercompte?codeCompte=" + codeCompte;
	}
}
