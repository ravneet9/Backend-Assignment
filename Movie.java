package newProject;

import java.util.ArrayList;
import java.util.List;

public class Movie{
    
      String movieName;//Name of movie
      int releasedYear;//year of release
      List<String> genres;//List of genres
      int score;//total score given to this movie
      int usersRated;//no. of users who gave score to this movie
      Movie(String name,int year,List<String> g){
          movieName=name;
          releasedYear=year;
          g=new ArrayList<String>(g);
          score=0;
          usersRated=0;
      }
	public List<String> getGeneres() {
		return genres;
	}
	public void setGeneres(List<String> generes) {
		this.genres = generes;
	}
	public int getUsersRated() {
		return usersRated;
	}
	public void setUsersRated(int usersRated) {
		this.usersRated = usersRated;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public int getReleasedYear() {
		return releasedYear;
	}
	public void setReleasedYear(int releasedYear) {
		this.releasedYear = releasedYear;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
      
}