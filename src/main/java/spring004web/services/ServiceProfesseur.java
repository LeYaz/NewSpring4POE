package spring004web.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring004web.Beans.Professeur;
import spring004web.dao.IProfesseurDao;
import spring004web.enumerations.eSexe;

@Service
public class ServiceProfesseur implements IServiceProfesseur {
	
	@Autowired
	private IProfesseurDao dao;
	
	@Transactional(readOnly=true)
	public List<Professeur> recupererProfesseurs() {
		// TODO Auto-generated method stub
		return dao.recupererProfesseurs();
	}

	@Transactional
	public void creerProfesseur(String pNom, String pPrenom, Date pDate, String pAdresse, eSexe pSexe) {
		// TODO Auto-generated method stub
		final Professeur pProf = new Professeur();
		pProf.setNom(pNom);
		pProf.setPrenom(pPrenom);
		pProf.setAdresse(pAdresse);
		pProf.setDatenaissance(pDate);
		pProf.setSexe(pSexe);
		
		dao.creerProfesseur(pProf);
	}

	@Transactional
	public void supprimerProfesseur(Integer pProfId) {
		// TODO Auto-generated method stub
		final Professeur pProf = new Professeur();
		pProf.setId(pProfId);
		dao.supprimerProfesseur(pProf);

	}
	
	@Transactional
	public void modifierProfesseur(List<Professeur> plisteProf) {
		// TODO Auto-generated method stub
		for(final Professeur pProf : plisteProf) {
			dao.modifierProfesseur(pProf);
		}
		
	}

	

}
