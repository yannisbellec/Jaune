package location;

public class Administrateur extends Utilisateur {
	InterAdministration inter;
	
	public Administrateur(String pseudo, String mdp, InformationPersonnelle info) {
		super(pseudo, mdp, info);
	}

}
