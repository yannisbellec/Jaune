package location;

import java.io.IOException;
import java.util.Set;

public class FonctionsAdministrateur implements InterAdministration {

	public FonctionsArtistes foncArtiste;
	public FonctionsRecherche foncRecherche;
	public FonctionsFilms foncFilmes;
	public FonctionsUtilisateur foncUtilisateur;
	
	public FonctionsAdministrateur() {
		
		this.foncFilmes = new FonctionsFilms();
		this.foncArtiste = new FonctionsArtistes(foncFilmes);
		this.foncFilmes.setFoncArtiste(foncArtiste);
		
		this.foncRecherche = new FonctionsRecherche(this.foncFilmes, this.foncArtiste);
		this.foncUtilisateur = new FonctionsUtilisateur(this.foncFilmes, this.foncRecherche, this);
	}
	public void setFoncUtilisateur(FonctionsUtilisateur foncUtilisateur) {
		this.foncUtilisateur = foncUtilisateur;
	}
	
	@Override
	public Artiste creerArtiste(String nom, String prenom, String nationalite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supprimerArtiste(Artiste artiste) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Film creerFilm(String titre, Artiste realisateur, int annee, int ageLimite) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean ajouterActeurs(Film film, Artiste... acteurs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ajouterGenres(Film film, Genre... genres) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ajouterAffiche(Film film, String file) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimerFilm(Film film) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Film> ensembleFilms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Artiste> ensembleActeurs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Artiste> ensembleRealisateurs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Film> ensembleFilmsRealisateur(Artiste realisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Film> ensembleFilmsActeur(Artiste acteur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Artiste getArtiste(String nom, String prenom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Film getFilm(String titre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean ouvrirLocation(Film film) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fermerLocation(Film film) {
		// TODO Auto-generated method stub
		return false;
	}

}
