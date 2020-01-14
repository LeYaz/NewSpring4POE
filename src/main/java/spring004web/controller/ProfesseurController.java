package spring004web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping(value = "/afficherCreerProfesseur", method = RequestMethod.GET)
	public String afficherCreer(ModelMap pModel) {
		final List<Professeur> lListeProfesseur = pService.recupererProfesseurs();
		pModel.addAttribute("listeProfesseurs",lListeProfesseur);
		if (pModel.get("creation") == null) {
			pModel.addAttribute("creation", new CreationProfesseurForm());
		}
		
		return "creerprofesseur";
	}
	
	@RequestMapping(value = "/creerProfesseur", method = RequestMethod.POST)
	public String creer(@Valid @ModelAttribute(value = "creation") final CreationProfesseurForm pCreation,
			final BindingResult pBindingResult, final ModelMap pModel) {
		if (!pBindingResult.hasErrors()) {
			
			//Tester la Date
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat sd = new SimpleDateFormat(pattern);
			sd.setLenient(false);
			Date date =null;
			try {
				date = sd.parse(pCreation.getDatenaissance());
			}catch(Exception e) {
				e.getStackTrace();
			}
			
			//Tester l'enum
			
			eSexe sexe = eSexe.FEMME;
			if( !pCreation.getSexe().equalsIgnoreCase(eSexe.FEMME.toString())) sexe = eSexe.HOMME;
			
			pService.creerProfesseur(pCreation.getNom(), pCreation.getPrenom(), date, pCreation.getAdresse(), sexe);
		}
		return afficherCreer(pModel);
	}
	
	@RequestMapping(value = "/afficherSupprimerProfesseur", method = RequestMethod.GET)
	public String afficherSupprimer(ModelMap pModel) {
		final List<Professeur> lListeProfesseur = pService.recupererProfesseurs();
		pModel.addAttribute("listeProfesseurs",lListeProfesseur);
		return "supprimerprofesseur";
	}
	
	@RequestMapping(value = "/supprimerProfesseur", method = RequestMethod.GET)
	public String supprimer(@RequestParam(value = "idProf") final Integer pIdProf, final ModelMap pModel) {
		
		pService.supprimerProfesseur(pIdProf);
		return afficherSupprimer(pModel);
	}
	
	@RequestMapping(value = "/afficherModificationProfesseur", method = RequestMethod.GET)
	public String afficherModification(final ModelMap pModel) {
		if (pModel.get("modification") == null) {
			final List<Professeur> lListeProf = pService.recupererProfesseurs();
			final ModificationProfesseur lModificationForm = new ModificationProfesseur();
			final List<CreationProfesseurForm> lListe = new LinkedList<CreationProfesseurForm>();
			for (final Professeur prof : lListeProf) {
				final CreationProfesseurForm lModificationProf = new CreationProfesseurForm();
				lModificationProf.setId(prof.getId());
				lModificationProf.setNom(prof.getNom());
				lModificationProf.setPrenom(prof.getPrenom());
				lModificationProf.setAdresse(prof.getAdresse());
				lModificationProf.setDatenaissance(prof.getDatenaissance().toString());
				lModificationProf.setSexe(prof.getSexe().toString());
				lListe.add(lModificationProf);
			}
			lModificationForm.setListeProfesseurs(lListe);
			pModel.addAttribute("modification", lModificationForm);
		}
		return "modification";
	}

	@RequestMapping(value = "/modifierProfesseur", method = RequestMethod.POST)
	public String modifier(@Valid @ModelAttribute(value = "modification") final ModificationProfesseur pModification,
			final BindingResult pBindingResult, final ModelMap pModel) {
		if (!pBindingResult.hasErrors()) {
			final List<Professeur> lListeCourses = new LinkedList<Professeur>();
			for (final CreationProfesseurForm lProfForm : pModification.getListeProfesseurs()) {
				final Professeur lProf = new Professeur();
				lProf.setId(lProfForm.getId());
				lProf.setAdresse(lProfForm.getAdresse());
				lProf.setNom(lProfForm.getNom());
				lProf.setPrenom(lProfForm.getPrenom());
				String pattern = "yyyy-MM-dd";
				SimpleDateFormat sd = new SimpleDateFormat(pattern);
				sd.setLenient(false);
				try {
					lProf.setDatenaissance(sd.parse(lProfForm.getDatenaissance()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				eSexe sexe = eSexe.FEMME;
				if( !lProfForm.getSexe().equalsIgnoreCase(eSexe.FEMME.toString())) sexe = eSexe.HOMME;
				lProf.setSexe(sexe);
				lListeCourses.add(lProf);
			}
			pService.modifierProfesseur(lListeCourses);
		}
		return afficherModification(pModel);
	}

}
