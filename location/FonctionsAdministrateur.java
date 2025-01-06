package location;

import java.io.IOException;

/**
 * Classe gérant les fonctionnalités administratives, permettant de gérer les
 * artistes, les films et les utilisateurs.
 *
 * @author Alexandre De Brito
 */
public class FonctionsAdministrateur {
  
  /** Gestionnaire des fonctionnalités liées aux artistes. */
  public FonctionsArtistes foncArtiste;
  
  /** Gestionnaire des fonctionnalités liées aux films. */
  public FonctionsFilms foncFilm;
  
  /** Gestionnaire des fonctionnalités liées aux utilisateurs. */
  private FonctionsUtilisateur foncUtilisateur;
  
  /**
   * Constructeur de la classe FonctionsAdministrateur. Initialise les
   * gestionnaires des fonctionnalités des films et des artistes. Le
   * gestionnaire des artistes est lié au gestionnaire des films.
   */
  public FonctionsAdministrateur() {
    this.foncFilm = new FonctionsFilms();
    this.foncArtiste = new FonctionsArtistes(foncFilm);
  }
  
  public void setFoncUtilisateur(FonctionsUtilisateur foncUtilisateur) {
    this.foncUtilisateur = foncUtilisateur;
  }
  
  /**
   * Création d'un nouvel artiste. Il ne doit pas déjà exister un artiste avec
   * le même nom et le même prénom.
   *
   * @param nom le nom de l'artiste (chaine non vide)
   * @param prenom le prénom de l'artiste (chaine vide "" si l'artiste n'a qu'un
   *        nom et pas de prénom)
   * @param nationalite la nationalité de l'artiste
   * @return l'artiste créé ou <code>null</code> en cas d'erreur (les paramètres
   *         sont invalides ou il existe déjà un artiste avec les mêmes valeurs)
   */
  public Artiste creerArtiste(String nom, String prenom, String nationalite) {
    // Vérification de la nullité des paramètres
    if (nom == null || prenom == null || nationalite == null || nom.isBlank()
        || prenom.isBlank() || nationalite.isBlank()) {
      System.out.println(
          "Paramètres invalides : nom, prénom ou nationalité est null");
      return null;
    }
    
    Artiste artiste = new Artiste(nom, prenom, nationalite);
    
    Boolean a = this.foncArtiste.addArtiste(artiste);
    
    this.foncArtiste.addArtiste(artiste);
    
    return a ? artiste : null;
  }
  
  
  /**
   * Supprime un artiste de l'ensemble des artistes. Ne peut pas être réalisé si
   * l'artiste est associé à au moins un film en tant qu'acteur ou réalisateur.
   *
   * @param artiste l'artiste à supprimer
   * @return <code>true</code> si la suppression a été réalisée,
   *         <code>false</code> sinon
   */
  public boolean supprimerArtiste(Artiste artiste) {
    if (artiste == null
        || artiste.getFilms() != null && !artiste.getFilms().isEmpty()) {
      return false;
    }
    
    this.foncArtiste.removeArtiste(artiste);
    this.foncArtiste.removeActeur(artiste);
    this.foncArtiste.removeRealisateur(artiste);
    
    return true;
  }
  
  /**
   * Création d'un nouveau film. Il ne doit pas déjà exister un film avec le
   * même titre.
   *
   * @param titre le titre du film (chaine non vide)
   * @param realisateur le réalisateur du film (non <code>null</code>)
   * @param annee l'année de réalisation du film
   * @param ageLimite l'âge minimum pour pouvoir regarder le film (0 si pas
   *        d'âge limite)
   * @return le film créé ou <code>null</code> en cas de problème (il existe
   *         déjà un film au même titre ou des paramètres n'étaient pas valides)
   */
  public Film creerFilm(String titre, Artiste realisateur, int annee,
      int ageLimite, boolean location) {
    if (titre == null || titre.isBlank() || this.foncFilm.getFilm(titre) != null
        || realisateur == null) {
      return null;
    }
    
    Film film = new Film(titre, realisateur, annee, ageLimite);
    
    this.foncArtiste.addRealisateur(realisateur);
    
    realisateur.addFilm(film);
    
    this.foncFilm.setFilm(film);
    
    return film;
  }
  
  /**
   * Ajoute des acteurs à un film.
   *
   * @param film le film à modifier
   * @param acteurs la liste des acteurs à ajouter. Si des acteurs de la liste
   *        sont déjà associés au film, ils ne sont pas ajoutés en double.
   * @return <code>true</code> si au moins un acteur de la liste a été ajouté
   *         aux acteurs du film, <code>false</code> sinon
   */
  public boolean ajouterActeurs(Film film, Artiste... acteurs) {
    if (film == null || acteurs == null) {
      return false;
    }
    
    boolean acteurAjoute = false;
    
    for (Artiste acteur : acteurs) {
      if (acteur != null && !film.getSetActeur().contains(acteur)) {
        film.getSetActeur().add(acteur);
        acteurAjoute = true;
      }
    }
    
    return acteurAjoute;
  }
  
  
  /**
   * Ajoute des genres à un film.
   *
   * @param film le film à modifier
   * @param genres la liste des genres à ajouter. Si des genres de la liste sont
   *        déjà associés au film, ils ne sont pas ajoutés en double.
   * @return <code>true</code> si au moins un genre de la liste a été ajouté aux
   *         genres du film, <code>false</code> sinon
   */
  public boolean ajouterGenres(Film film, Genre... genres) {
    if (film == null || genres == null) {
      return false;
    }
    
    boolean ajout = false;
    
    for (Genre genre : genres) {
      if (genre != null && !film.getSetGenre().contains(genre)) {
        film.setGenre(genre);
        ajout = true; // Indique que le genre a été ajouté
      }
    }
    
    return ajout; // Retourne true uniquement si au moins un genre a été ajouté
  }
  
  /**
   * Ajoute une affiche à un film. Si le film avait déjà une affiche, elle est
   * remplacée par la nouvelle.
   *
   * @param film le film auquel ajouter une affiche
   * @param file le chemin du fichier qui contient l'image de l'affiche (au
   *        format JPG, PNG ...)
   * @return <code>true</code> si l'affiche a été modifiée (le format et la
   *         taille étaient valides)
   * @throws IOException en cas d'erreur de lecture du fichier
   */
  public boolean ajouterAffiche(Film film, String file) throws IOException {
    return true;
  }
  
  /**
   * Supprime un film de l'ensemble des films.
   *
   * @param film le film à supprimer
   * @return <code>true</code> si le film a été supprimé ou <code>false</code>
   *         en cas de problème (le film n'existait pas ou le paramètre était
   *         égal à <code>null</code>)
   */
  public boolean supprimerFilm(Film film) {
    if (film == null || !this.foncFilm.getSetFilm().contains(film)) {
      return false;
    }
    
    this.foncFilm.removeFilm(film);
    this.foncArtiste.removeRealisateur(film.getRealisateur());
    film.getRealisateur().removeFilm(film);
    
    for (Artiste art : film.getSetActeur()) {
      this.foncArtiste.removeActeur(art);
      art.removeFilm(film);
    }
    
    film.clearSetEvaluations();
    
    return true;
  }
  
  
  /**
   * Ouvre un film à la location. Ne fait rien si le film était déjà ouvert à la
   * location.
   *
   * @param film le film à ouvrir à la location
   * @return <code>true</code> si le film est ouvert à la location,
   *         <code>false</code> en cas de problème (le film n'a pas été trouvé
   *         ou valeur <code>null</code>)
   */
  public boolean ouvrirLocation(Film film) {
    if (film == null) {
      return false;
    }
    film.setLocation(true);
    return true;
  }
  
  /**
   * Ferme la location d'un film. Ne fait rien si le film n'était pas ouvert à
   * la location.
   *
   * @param film le film dont il faut fermer la location
   * @return <code>true</code> si le film est fermé à la location,
   *         <code>false</code> en cas de problème (le film n'a pas été trouvé
   *         ou valeur <code>null</code>)
   */
  public boolean fermerLocation(Film film) {
    if (film == null) {
      return false;
    }
    film.setLocation(false);
    return true;
  }
  
}
