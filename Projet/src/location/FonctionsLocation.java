package location;

public class FonctionsLocation {
	private FonctionsCompte foncCompte;
	private FonctionsFilms foncFilm;
	
	
	public FonctionsLocation(FonctionsFilms foncFilmes, FonctionsCompte foncCompte) {
		this.foncCompte = foncCompte;
		this.foncFilm = foncFilmes;
	}

	public void louerFilm(Film film) throws NonConnecteException, LocationException {
		//Verifier si l'age de l'utilisateur correspond
		//Verifier si le filme a moins de 3 locations
		if (!foncCompte.estConnecte()) {
	        throw new NonConnecteException();
	    }

	    if (!film.estDisponible()) {
	        throw new LocationException();
	    }
	    
	    Utilisateur utilConnecte = this.foncCompte.getUtilisateurConnecte();
	    
	    if(utilConnecte.getInfo().getAge() < film.getAgeMin()) {
	    	
	    }
	}
}
