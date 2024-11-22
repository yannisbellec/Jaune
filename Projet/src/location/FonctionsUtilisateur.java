package location;

import java.util.Set;

/**
 * Implémentation de l'interface Utilisateur (InterAdministration)
 * Définition des différentes fonction de l'interface utilisateur
 */
public class FonctionsUtilisateur implements InterUtilisateur {
	
	private FonctionsCompte foncCompte;
	private FonctionsFilms foncFilmes;
	private FonctionsLocation foncLocation;
	private FonctionsRecherche foncRech;
	private FonctionsAdministrateur foncAdmin;
	
	public FonctionsUtilisateur(FonctionsFilms foncFilms, FonctionsRecherche foncRech, FonctionsAdministrateur foncAdmin) {
		this.foncCompte = new FonctionsCompte();
		this.foncAdmin = foncAdmin;
		this.foncFilmes = foncFilms;
		this.foncRech = foncRech;
		this.foncLocation = new FonctionsLocation(this.foncFilmes, this.foncCompte);
	}
	
	/**
	 * Fonction inscription permetant de créer un nouveau compte
	 * 
	 */
	@Override
	public int inscription(String pseudo, String mdp, InformationPersonnelle info) {
		return foncCompte.inscription(pseudo, mdp, info);
	}

	@Override
	public boolean connexion(String pseudo, String mdp) {
		return foncCompte.connexion(pseudo, mdp);
	}

	@Override
	public void deconnexion() throws NonConnecteException {
		foncCompte.deconnexion();
		
	}

	@Override
	public void louerFilm(Film film) throws NonConnecteException, LocationException {
		foncLocation.louerFilm(film);
	}

	@Override
	public void finLocationFilm(Film film) throws NonConnecteException, LocationException {
		foncLocation.finLocationFilm(film);
		
	}

	@Override
	public boolean estLouable(Film film) throws NonConnecteException {
		return foncFilmes.estLouable(film);
	}

	@Override
	public Set<Film> filmsEnLocation() throws NonConnecteException {
		foncFilmes.filmsEnLocation();
		return null;
	}

	@Override
	public void ajouterEvaluation(Film film, Evaluation eval) throws NonConnecteException, LocationException {
		/foncFilmes.ajouterEvaluation();
		
	}

	@Override
	public void modifierEvaluation(Film film, Evaluation eval) throws NonConnecteException, LocationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Film> ensembleFilms() {
		return foncRech.ensembleFilms();
	}

	@Override
	public Set<Artiste> ensembleActeurs() {
		// TODO Auto-generated method stub
		return foncRech.ensembleActeurs();
	}

	@Override
	public Set<Artiste> ensembleRealisateurs() {
		// TODO Auto-generated method stub
		return foncRech.ensembleRealisateurs();
	}

	@Override
	public Artiste getActeur(String nom, String prenom) {
		// TODO Auto-generated method stub
		return foncRech.getActeur(nom, prenom);
	}

	@Override
	public Artiste getRealisateur(String nom, String prenom) {
		// TODO Auto-generated method stub
		return foncRech.getRealisateur(nom, prenom);
	}

	@Override
	public Film getFilm(String titre) {
		// TODO Auto-generated method stub
		return foncRech.getFilm(titre);
	}

	@Override
	public Set<Film> ensembleFilmsRealisateur(Artiste realisateur) {
		// TODO Auto-generated method stub
		return foncRech.ensembleFilmsRealisateur(realisateur);
	}

	@Override
	public Set<Film> ensembleFilmsRealisateur(String nom, String prenom) {
		// TODO Auto-generated method stub
		return foncRech.ensembleFilmsRealisateur(nom, prenom);
	}

	@Override
	public Set<Film> ensembleFilmsActeur(Artiste acteur) {
		// TODO Auto-generated method stub
		return foncRech.ensembleFilmsActeur(acteur);
	}

	@Override
	public Set<Film> ensembleFilmsActeur(String nom, String prenom) {
		// TODO Auto-generated method stub
		return foncRech.ensembleFilmsActeur(nom,prenom);
	}

	@Override
	public Set<Film> ensembleFilmsGenre(Genre genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Film> ensembleFilmsGenre(String genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Evaluation> ensembleEvaluationsFilm(Film film) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Evaluation> ensembleEvaluationsFilm(String titre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double evaluationMoyenne(Film film) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double evaluationMoyenne(String titre) {
		// TODO Auto-generated method stub
		return 0;
	}

}
