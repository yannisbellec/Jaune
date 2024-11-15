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

}
