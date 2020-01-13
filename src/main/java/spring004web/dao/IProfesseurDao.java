package spring004web.dao;

import java.util.List;

import spring004web.Beans.Professeur;

public interface IProfesseurDao {
	List<Professeur> recupererProfesseurs();
	void creerProfesseur(final Professeur pProf);
	void supprimerProfesseur(final Professeur pProf);
	void modifierProfesseur(final Professeur pProf);

}
