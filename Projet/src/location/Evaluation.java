package location;

// A COMPLETER

/**
 * Evaluation avec une note ( sur 5 ) et un commentaire sur un film
 *
 * @author De Brito Cerqueira Alexandre
 */

public class Evaluation 
{
	private int note;
	private String commentaire;
	//private InterUtilisateur utilisateur;
	private Film film; 
	
	public Evaluation(int note, String commentaire,/* InterUtilisateur utilisateur,*/ Film film)
	{
		setNote(note);
		this.commentaire = commentaire;
		/*this.utilisateur = utilisateur;*/
		this.film = film;
		film.setEvaluation(this);
		film.calculeMoyenne();
	}
	
	public Evaluation(int note,/*InterUtilisateur utilisateur,*/ Film film)
	{
		this(note,"",/*utilisateur,*/film);
	}
	
	public void setNote(int note)
	{
		if(note > 5)
			this.note = 5;
		else if (note < 0 )
			this.note = 0;
		else
			this.note = note;
	}
	
	public int getNote()
	{
		return this.note;
	}
	
	public String getCommentaire()
	{
		return this.commentaire;
	}
	
	public void setCommentaire(String commentaire)
	{
		this.commentaire = commentaire;
	}
	
	public void setFilm(Film film)
	{
		this.film = film;
	}
	
	public Film getFilm()
	{
		return this.film;
	}
	
}


