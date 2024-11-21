package location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FonctionsCompte {
    private Set<Utilisateur> listUtilisateur;
    private Utilisateur utilisateurConnecte;
    
    public FonctionsCompte() {
    	this.listUtilisateur = (Set<Utilisateur>) new ArrayList<Utilisateur>();
    	this.utilisateurConnecte = null;
    }
    
    public Utilisateur getUtilisateurConnecte() {
    	return this.utilisateurConnecte;
    }
    
    public Set<Utilisateur> getListUtilisateur(){
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
		Iterator <Utilisateur> it = this.listUtilisateur.iterator();
		
		for(int i = 0; i < this.listUtilisateur.size(); i++) {
			Utilisateur ut1 = it.next();
			if( ut1.getPseudo() == pseudo && ut1.getMdp() == mdp) {
				this.utilisateurConnecte = ut1;
				return true;
			}
		}
		return false;
	}
	
	public void deconnexion() {
		this.utilisateurConnecte = null;
	}
	
	
	
}
