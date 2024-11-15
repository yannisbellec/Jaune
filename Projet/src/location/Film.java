package location;

import java.util.*;

// A COMPLETER

public class Film 
{
	private Set<Genre> setGenre;
	private Set<Artiste> setArtistes;	
	private Set<Evaluation> setEvaluations;
	private String titre;
	private boolean location;
	private double noteMoyenne;
	private int ageMin;
	private int anneeReal;

	// temp a suppr 
	private Set<Film> setFilms;

	// à implémenter dans interUtilisateur
	double evaluationMoyenne(Film film)
	{
		if (film == null)
			return -2;
		else if (film.setEvaluations.isEmpty())
			return -1;
		else
			return film.noteMoyenne;
	}

	double evaluationMoyenne(String titre)
	{
		Film bonFilm = null;
		for(Film film : setFilms)
		{
			if(film.titre.equals(titre))
			{
				bonFilm = film;
				break;
			}
		}

		return evaluationMoyenne(bonFilm);
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
		if (film == null || film.setEvaluations.isEmpty())
			return null;
		else
			return film.setEvaluations;	  
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
		if (titre == null) {
			return null;  // Si le titre est null, retourne directement null
		}

		Film bonFilm = null;
		for(Film film : setFilms)
		{
			if(film.titre.equals(titre))
			{
				bonFilm = film;
				break;
			}
		}

		return ensembleEvaluationsFilm(bonFilm);
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
		if( genre == null )
			return null;
		
		Set<Film> setFilmsGenre = new HashSet<>();

	    for (Film film : this.setFilms) 
	    {
	        if (film.setGenre != null && film.setGenre.contains(genre)) 
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


	// Constructeur avec tous les paramètres
	public Film(Set<Genre> genre, Set<Artiste> setArtistes, Set<Evaluation> setEvaluations, boolean location, int anneeReal, int ageMin, String titre) {
		this.setGenre = setGenre != null ? setGenre : new HashSet<>();  // Utilise le paramètre ou crée un HashSet vide
		this.setArtistes = setArtistes != null ? setArtistes : new HashSet<>();  // Idem pour les autres ensembles
		this.setEvaluations = setEvaluations != null ? setEvaluations : new HashSet<>();
		this.location = location;
		this.calculeMoyenne();  // Calcule la moyenne des évaluations
		this.anneeReal = anneeReal;
		this.ageMin = ageMin;
		this.titre = titre;
	}

	// Constructeur sans le paramètre 'location'
	public Film(Set<Genre> setGenre, Set<Artiste> setArtistes, Set<Evaluation> setEvaluations, int anneeReal, int ageMin, String titre) 
	{
		this(setGenre, setArtistes, setEvaluations, false, anneeReal, ageMin, titre);  // Appelle le premier constructeur avec 'location' par défaut à false
	}

	public String getTitre()
	{
		return this.titre;
	}


	public void setAgeMin(int ageMin)
	{
		this.ageMin = ageMin;
	}

	public void setAnneeReal(int anneeReal)
	{
		this.anneeReal = anneeReal;
	}

	public int getAgeMin()
	{
		return this.ageMin;
	}

	public int getAnneeReal()
	{
		return this.anneeReal;
	}

	public void ajouterGenre(Genre genre)
	{
		this.setGenre.add(genre);
	}

	public void ajouterGenre(Set<Genre> setGenre)
	{
		for (Genre g : setGenre) 
			this.setGenre.add(g);
	}

	public void ajouterArtiste(Artiste artiste) 
	{
		this.setArtistes.add(artiste);
	}

	public void ajouterEvaluation(Evaluation evaluation) 
	{
		this.setEvaluations.add(evaluation);
	}

	public void ajouterEvaluation(Set<Evaluation> eval)
	{
		for (Evaluation e : eval) 
			this.setEvaluations.add(e);
	}

	public void setLocation(boolean location)
	{
		this.location = location;
	}

	public void calculeMoyenne() 
	{
		// Vérifie si le set est vide pour éviter une division par zéro
		if (setEvaluations.isEmpty()) {
			this.noteMoyenne = 0.0;
			return;
		}

		// Calcule la somme des notes
		double somme = 0.0;

		for (Evaluation eval : setEvaluations) 
			somme += eval.getNote(); // Ajoute la note de chaque évaluation à la somme


		// Calcule la moyenne
		this.noteMoyenne = somme / setEvaluations.size();
	}

}
