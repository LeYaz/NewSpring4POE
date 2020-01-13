package spring004web.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring004web.Beans.Professeur;
import spring004web.enumerations.eSexe;
import spring004web.services.IServiceProfesseur;

@Controller

public class ProfesseurController {
	@Autowired
	private IServiceProfesseur pService;
	
	@RequestMapping(value = "/afficherProfesseur", method = RequestMethod.GET)
	public String afficher(ModelMap pModel) {
		final List<Professeur> lListeProfesseur = pService.recupererProfesseurs();
		pModel.addAttribute("listeProfesseurs",lListeProfesseur);
		return "professeurs";
	}
	
	
	@RequestMapping(value = "/creerProfesseur", method = RequestMethod.POST)
	public String creer(@Valid @ModelAttribute(value = "creation") final CreationProfesseurForm pCreation,
			final BindingResult pBindingResult, final ModelMap pModel) {
		if (!pBindingResult.hasErrors()) {
			
			//Tester la Date
			
			Date date = new Date();
			
			//Tester l'enum
			
			eSexe sexe = eSexe.FEMME;
			if( !pCreation.getSexe().equalsIgnoreCase(eSexe.FEMME.toString())) sexe = eSexe.HOMME;
			
			pService.creerProfesseur(pCreation.getNom(), pCreation.getPrenom(), date, pCreation.getAdresse(), sexe);
		}
		return afficher(pModel);
	}

}
