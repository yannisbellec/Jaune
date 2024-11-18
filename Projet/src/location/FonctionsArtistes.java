package location;

import java.util.ArrayList;
import java.util.List;

public class FonctionsArtistes {
    private List<Artiste> listArtistes;
	private FonctionsFilms foncFilms;
	
	public FonctionsArtistes(FonctionsFilms foncFilms) {
		this.listArtistes = new ArrayList<Artiste>();
		this.foncFilms = foncFilms;
	}
	
	
}
