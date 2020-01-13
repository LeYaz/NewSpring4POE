package spring004web.services;

import java.util.Date;
import java.util.List;

import spring004web.Beans.Professeur;
import spring004web.enumerations.eSexe;

public interface IServiceProfesseur {
	List<Professeur> recupererProfesseurs();
	void creerProfesseur(final String pNom, final String pPrenom, final Date pDate, final String pAdresse, final eSexe pSexe);
	void supprimerProfesseur(final Professeur pProf);
	void modifierProfesseur(final Professeur pProf);
}
