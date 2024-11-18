package location;

import java.util.ArrayList;
import java.util.List;

public class FonctionsCompte {
    private List<Utilisateur> listUtilisateur;
    private Utilisateur utilisateurConnecte;
    
    public FonctionsCompte() {
    	this.listUtilisateur = new ArrayList<Utilisateur>();
    	this.utilisateurConnecte = null;
    }
    
    public Utilisateur getUtilisateurConnecte() {
    	return this.utilisateurConnecte;
    }
    public List<Utilisateur> getListUtilisateur(){
    	return this.listUtilisateur;
    }
    
    public boolean estConnecte() {
    	if(this.utilisateurConnecte != null) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public int inscription(String pseudo, String mdp, InformationPersonnelle info) {
		if(listUtilisateur.add(new Utilisateur(pseudo, mdp, info))) {
			return 1;
		}
		else {
			return 0;
		}
	}
    
	public boolean connexion(String pseudo, String mdp) {
		for(int i = 0; i < this.listUtilisateur.size(); i++) {
			if((this.listUtilisateur.get(i).getPseudo() == pseudo) && (this.listUtilisateur.get(i).getMdp() == mdp)) {
				this.utilisateurConnecte = this.listUtilisateur.get(i);
				return true;
			}
		}
		return false;
	}
	
	public void deconnexion() {
		this.utilisateurConnecte = null;
	}
	
	
	
}
