package ci.kaera.appBanque.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client implements Serializable {

	@Id
	@GeneratedValue
	public Long codeClient;
	public String nom;
	public String email;
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	public Collection<Compte> comptes;

	public Client() {
		super();
	}

	public Client(String nom, String email) {
		this.nom = nom;
		this.email = email;
	}

	public Client(Long codeClient, String nom, String email, Collection<Compte> comptes) {
		super();
		this.codeClient = codeClient;
		this.nom = nom;
		this.email = email;
		this.comptes = comptes;
	}

	public Long getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(Long codeClient) {
		this.codeClient = codeClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}

}
