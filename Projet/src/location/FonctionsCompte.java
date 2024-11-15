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
    
    public int inscription(String pseudo, String mdp, InformationPersonnelle info) {
		if(listUtilisateur.add(new Utilisateur(pseudo, mdp, info))) {
			return 1;
		}
		else {
			return 0;
		}
		
	}

}
