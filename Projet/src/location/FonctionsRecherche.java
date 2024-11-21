package location;

import java.util.*;

public class FonctionsRecherche 
{
    private FonctionsFilms foncFilms;
    private FonctionsArtistes foncArtistes;

    /**
     * Constructeur de la classe {@code FonctionsRecherche}.
     * Initialise les instances des classes {@code FonctionsFilms} et {@code FonctionsArtistes}.
     *
     * @param foncFilms l'instance de {@code FonctionsFilms} pour accéder aux films
     * @param foncArtistes l'instance de {@code FonctionsArtistes} pour accéder aux artistes
     */
    public FonctionsRecherche(FonctionsFilms foncFilms, FonctionsArtistes foncArtistes)
    {
        this.foncArtistes = foncArtistes;
        this.foncFilms = foncFilms;
    }

    /**
     * Cherche un film à partir de son titre.
     *
     * @param titre le titre du film
     * @return le film s'il a été trouvé ou <code>null</code> sinon
     */
    Film getFilm(String titre)
    {    	
        if(titre == null || this.foncFilms.getSetFilm() == null)
            return null;
        
        for(Film film : this.foncFilms.getSetFilm())
            if(film.getTitre() != null && film.getTitre().equals(titre))
                return film;
        
        return null;
    }

    /**
     * Renvoie l'ensemble des films.
     *
     * @return l'ensemble des films ou <code>null</code> si aucun film n'existe
     */
    Set<Film> ensembleFilms()
    {
        if(this.foncFilms.getSetFilm() == null || this.foncFilms.getSetFilm().isEmpty() )
            return null;
        else
            return this.foncFilms.getSetFilm();
    }

    /**
     * Renvoie l'ensemble des évaluations d'un film.
     *
     * @param film le film dont on veut les évaluations
     * @return toutes les évaluations d'un film ou <code>null</code> si aucune
     *         évaluation n'existe pour que le film ou que le film était invalide
     *         (valeur <code>null</code> par exemple)
     */
    Set<Evaluation> ensembleEvaluationsFilm(Film film)
    {
        if (film == null || film.getSetEvaluations().isEmpty())
            return null;
        else
            return film.getSetEvaluations();	  
    }

    /**
     * Renvoie l'ensemble des évaluations d'un film.
     *
     * @param titre le titre du film dont on veut les évaluations
     * @return toutes les évaluations d'un film ou <code>null</code> si aucune
     *         évaluation n'existe pour le film ou que le titre du film était
     *         inconnu ou invalide (valeur <code>null</code> par exemple)
     */
    Set<Evaluation> ensembleEvaluationsFilm(String titre)		
    {
        if (titre == null) 
            return null;  // Si le titre est null, retourne directement null

        Film bonFilm = null;
        for(Film film : foncFilms.getSetFilm())
        {
            if(film.getTitre().equals(titre))
            {
                bonFilm = film;
                break;
            }
        }

        return ensembleEvaluationsFilm(bonFilm);
    }

    /**
     * Renvoie l'ensemble des films d'un certain acteur.
     *
     * @param acteur l'acteur
     * @return l'ensemble des films de l'acteur ou <code>null</code> si aucun film
     *         n'a été trouvé ou que le paramètre était invalide
     */
    Set<Film> ensembleFilmsActeur(Artiste acteur) {
        if (acteur == null) {
            return null;
        }

        Set<Film> filmsActeur = new HashSet<>();

        for (Film film : foncFilms.getSetFilm()) {
            if (film.getSetArtistes() != null && film.getSetArtistes().contains(acteur)) {
                filmsActeur.add(film);
            }
        }

        return filmsActeur.isEmpty() ? null : filmsActeur;
    }

    /**
     * Renvoie l'ensemble des films d'un certain acteur.
     *
     * @param nom le nom de l'acteur
     * @param prenom le prénom de l'acteur
     * @return l'ensemble des films de l'acteur ou <code>null</code> si aucun film
     *         n'a été trouvé ou que les paramètres étaient invalides
     */
    Set<Film> ensembleFilmsActeur(String nom, String prenom) {
        if (nom == null || prenom == null || nom.isEmpty() || prenom.isEmpty()) {
            return null;
        }

        for (Artiste acteur : this.foncArtistes.getSetArtistes()) {
            if (acteur.getNom().equals(nom) && acteur.getPrenom().equals(prenom)) {
                return ensembleFilmsActeur(acteur);
            }
        }

        return null;
    }

    /**
     * Renvoie l'ensemble des films d'un certain genre.
     *
     * @param genre le genre du film
     * @return l'ensemble des films du genre ou <code>null</code> si aucun film
     *         n'a été trouvé
     */
    Set<Film> ensembleFilmsGenre(Genre genre)
    {
        if(genre == null)
            return null;

        Set<Film> setFilmsGenre = new HashSet<>();

        for (Film film : foncFilms.getSetFilm()) 
        {
            if (film.getSetGenre() != null && film.getSetGenre().contains(genre)) 
            {
                setFilmsGenre.add(film);
            }
        }

        // Retourne null si aucun film ne correspond
        return setFilmsGenre.isEmpty() ? null : setFilmsGenre;	
    }

    /**
     * Renvoie l'ensemble des films d'un certain genre.
     *
     * @param genre le genre du film (doit correspondre à un élément de
     *        l'énumération {@link location.Genre Genre})
     * @return l'ensemble des films du genre ou <code>null</code> si aucun film
     *         n'a été trouvé ou que le genre était invalide
     * @see location.Genre
     */
    Set<Film> ensembleFilmsGenre(String genre)
    {
        if(genre == null)
            return null;

        try 
        {
            // Convertit le String en un élément de Genre
            Genre genreEnum = Genre.valueOf(genre.toUpperCase());

            // Appelle la méthode avec le type Genre
            return ensembleFilmsGenre(genreEnum);
        } 
        catch (IllegalArgumentException e) 
        {
            // Retourne null si le String ne correspond à aucun élément de l'énumération Genre
            return null;
        }
    }

    /**
     * Renvoie l'évaluation moyenne d'un film (la moyenne des notes de toutes les
     * évaluations sur le film).
     *
     * @param film le film dont on récupère l'évaluation moyenne
     * @return l'évaluation moyenne du film ou -1 si le film n'a aucune évaluation
     *         ou -2 en cas de film invalide (n'existant pas ou valeur
     *         <code>null</code>)
     */	
    double evaluationMoyenne(Film film)
    {
        if (film == null)
            return -2;
        else if (film.getSetEvaluations().isEmpty())
            return -1;
        else
            return film.getNoteMoyenne();
    }

    /**
     * Renvoie l'évaluation moyenne d'un film (la moyenne des notes de toutes les
     * évaluations sur le film).
     *
     * @param titre le titre du film dont on récupère l'évaluation moyenne
     * @return l'évaluation moyenne du film ou -1 si le film n'a aucune évaluation
     *         ou -2 en cas de titre de film invalide (il n'existe pas de film
     *         avec ce titre ou valeur <code>null</code>)
     */
    double evaluationMoyenne(String titre)
    {
        Film bonFilm = null;
        for(Film film : this.foncFilms.getSetFilm())
        {
            if(film.getTitre().equals(titre))
            {
                bonFilm = film;
                break;
            }
        }

        return evaluationMoyenne(bonFilm);
    }

    /**
     * Vérifie si un film est disponible à la location.
     *
     * @param film le film à vérifier
     * @return {@code false} si le film est déjà loué ou que le nombre de locations dépasse 3,
     *         sinon {@code true}
     */
    public boolean estDispoLoc(Film film) 
    {
        if(film.getNbLoc() >= 3 && film.getLocation()) 
        {
            return false;
        }
        else {
            return true;
        }
    }
}
