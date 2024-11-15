package location;

import java.util.HashSet;
import java.util.Set;

public class Utilisateur {
	
	private static Set<String> listPseudo;
	private InformationPersonnelle info;
	private Set<Film> histFilm;
	private String pseudo;
	private Set<Film> locFilm;
	private String mdp;
	
	public Utilisateur(String pseudo, String mdp, InformationPersonnelle info) {
        if (listPseudo.contains(pseudo)) {
            throw new IllegalArgumentException("Ce pseudo est déjà pris.");
        }
        
        // Initialisation des attributs
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.info = info;
        this.histFilm = new HashSet<Film>();  // Historique de films, initialisé vide
        this.locFilm = new HashSet<Film>();   // Films en location, initialisé vide

        // Ajout du pseudo à la liste statique
        listPseudo.add(pseudo);
    }
	
	public InformationPersonnelle getInfo() {
		return this.info;
	}
	public Set<Film> getHistoFilm(){
		return this.histFilm;
	}
	public String getPseudo() {
		return this.pseudo;
	}
	public Set<Film> getLocFilm(){
		return this.locFilm;
	}
	public int getNbLocFilm() {
		return this.locFilm.size();
	}
	public String getMdp() {
		return this.mdp;
	}	
}
