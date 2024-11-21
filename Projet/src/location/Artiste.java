package location;

import java.util.HashSet;
import java.util.Set;

/**
 * Représente un artiste avec un nom, un prénom, un ensemble de films associés
 * et des rôles (acteur, réalisateur, etc.).
 */
public class Artiste {
  private String nom;
  private String prenom;
  private Set<Film> films;
  private Set<Role> roles;

  /**
   * Constructeur de la classe Artiste.
   *
   * @param nom le nom de l'artiste
   * @param prenom le prénom de l'artiste
   */
  public Artiste(String nom, String prenom) {
    this.nom = nom;
    this.prenom = prenom;
    this.films = new HashSet<>();
    this.roles = new HashSet<>();
  }

  /**
   * Ajoute un film à l'ensemble des films de l'artiste.
   *
   * @param film le film à ajouter
   */
  public void addFilm(Film film) {
    films.add(film);
  }

  /**
  * Ajoute un rôle à l'artiste.
  *
  * @param role le rôle à ajouter (ACTEUR, REALISATEUR, etc.)
  */
  public void addRole(Role role) {
    roles.add(role);
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
  public Set<Film> getFilms() {
    return films;
  }

  /**
   * Retourne les rôles joués par l'artiste.
   *
   * @return un ensemble des rôles de l'artiste
   */
  public Set<Role> getRoles() {
    return roles;
  }

  /**
   * Retourne une représentation textuelle de l'artiste.
   *
   * @return une chaîne indiquant les rôles et le nom complet de l'artiste
   */
  @Override
  public String toString() {
    // Concaténation des titres des films
    StringBuilder filmsStr = new StringBuilder();
    for (Film film : films) {
      filmsStr.append(film.toString()).append(", ");
    }

    // Supprimer la dernière virgule et l'espace (si nécessaire)
    if (filmsStr.length() > 0) {
      filmsStr.setLength(filmsStr.length() - 2);
    }

    // Retourne une chaîne incluant nom, prénom, rôles, et films
    return "Artiste : " + nom + " " + prenom 
             + "\nRôles : " + roles 
             + "\nFilms : " + (filmsStr.length() > 0 ? filmsStr.toString() : "Aucun film associé");
  }

}