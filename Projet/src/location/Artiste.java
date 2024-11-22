package location;

import java.util.HashSet;
import java.util.Set;

/**
 * Représente un artiste avec un nom, un prénom et un ensemble de films
 * associés.
 */
public class Artiste {
	private String nom;
	private String prenom;
	private Set<Film> films;

	/**
	 * Constructeur de la classe Artiste.
	 *
	 * @param nom
	 *            le nom de l'artiste
	 * @param prenom
	 *            le prénom de l'artiste
	 */
	public Artiste(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		// Initialisation de l'ensemble des films
		this.films = new HashSet<>();
	}

	/**
	 * Ajoute un film à l'ensemble des films de l'artiste.
	 *
	 * @param film
	 *            le film à ajouter
	 */
	public void addFilm(Film film) {
		films.add(film);
	}

	/**
	 * Retourne le nom de l'artiste.
	 *
	 * @return le nom de l'artiste
	 */
	public String getNom() {
		return nom;
	}

	/** 	
	 * Retourne le prénom de l'artiste.
	 *
	 * @return le prénom de l'artiste
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Retourne l'ensemble des films associés à l'artiste.
	 *
	 * @return un ensemble de films
	 */
	public Set<Film> getFilm() {
		return films;
	}

	/**
	 * Retourne une représentation textuelle de l'artiste sous la forme "nom
	 * prénom".
	 *
	 * @return la représentation textuelle de l'artiste
	 */
	@Override
	public String toString() {
		return nom + " " + prenom;
	}
}
