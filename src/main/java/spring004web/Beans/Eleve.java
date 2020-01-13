package spring004web.Beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import spring004web.enumerations.eSexe;

@Entity
@Table(name="t_eleve")
public class Eleve {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	private String nom;
	
	private String prenom;
	
	@ManyToOne 
	@JoinColumn(name="classe")
	private Classe classe;
	
	@Column(name="date_naissance")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datenaissance;
	
	private String adresse;
	
	
	@Column(name="sexe", nullable=false, columnDefinition = "enum('HOMME', 'FEMME')")
	@Enumerated(EnumType.STRING)
	private eSexe sexe;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public Date getDatenaissance() {
		return datenaissance;
	}

	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public eSexe getSexe() {
		return sexe;
	}

	public void setSexe(eSexe sexe) {
		this.sexe = sexe;
	}
	

}
