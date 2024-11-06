package location;

import java.util.HashSet;
import java.util.Set;

public class Artiste {
	private String nom;
	private String prenom;
	private Set<Film> films;
	
	//Constructeur de la classe Artiste
	public Artiste(String nom, String prenom) {
		this.nom = nom ;
		this.prenom = prenom;
		//Initialisation de la l'ensemble des films
		this.films = new HashSet<>();
	}
	
	//Methode permettant d'ajouter un film
	public void addFilm(Film film) {
		films.add(film);		
	}
	
	//Getter pour nom de l'artiste
	public String getNom() {
		return nom;
	}
	
	//Getter pour prenom de l'artiste
	public String getPrenom() {
		return prenom;
	}
	
	//Getter pour les films de l'artiste
	public Set<Film> getFilm() {
		return films;
	}
	
	//Représentation textuelle de l'artiste
	@Override
	public String toString() {
		return nom + " " + prenom;
	}
		
}

//Sous classe représentant un réalisateur 
class Realisateur extends Artiste {
	
	// Constructeur de la classe Realisateur
    public Realisateur(String nom, String prenom) {
        super(nom, prenom);
    }
    
    // Méthode spécifique pour afficher le rôle de réalisateur
    @Override
    public String toString() {
    	return "Realisateur : " + super.toString();
    }
    
}

//Sous-classe représentant un acteur
class Acteur extends Artiste {
	
	// Constructeur de la classe Acteur
	public Acteur(String nom, String prenom) {
		super(nom, prenom);
	}

	// Méthode spécifique pour afficher le rôle d'acteur
	@Override
	public String toString() {
		return "Acteur : " + super.toString();
	}
}