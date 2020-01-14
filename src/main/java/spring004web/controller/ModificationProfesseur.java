package spring004web.controller;

import java.util.List;

import javax.validation.Valid;

public class ModificationProfesseur {

	@Valid
	private List<CreationProfesseurForm> listeProfesseurs;

	public List<CreationProfesseurForm> getListeProfesseurs() {
		return listeProfesseurs;
	}

	public void setListeProfesseurs(List<CreationProfesseurForm> listeProfesseurs) {
		this.listeProfesseurs = listeProfesseurs;
	}
	
	
}
