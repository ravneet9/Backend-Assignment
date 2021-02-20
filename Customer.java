package newProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class Customer{
    private
        String userName;
        int type;//type=0(Viewer),1(Critic),2(Expert),3(Admin)
        List<ArrayList<Movie>> typeOfUserWhenRatedMovie;/* list of movies for 4 different type of this customer
        e.g List[0]-->the list of movies rated by user when he/she was "Viewer"
        List[1]-->the list of movies rated by user when he/she was "Critic"
        List[2] and List[3].
        */
		HashMap<Movie,Integer> ratedMovies;//rating given by this user for a particular movie
        
		Customer(String Name){
            ratedMovies=new HashMap<Movie,Integer>();
            type=0;
            userName=Name;
            typeOfUserWhenRatedMovie=new  ArrayList<ArrayList<Movie>>();
            ArrayList<Movie> res=new ArrayList<Movie>();
            while(typeOfUserWhenRatedMovie.size()<4) {
    			typeOfUserWhenRatedMovie.add(res);
    		}
        }
        public List<ArrayList<Movie>> getTypeOfUserWhenRatedMovie() {
			return typeOfUserWhenRatedMovie;
		}
		public void setTypeOfUserWhenRatedMovie(List<ArrayList<Movie>> typeOfUserWhenRatedMovie) {
			this.typeOfUserWhenRatedMovie = typeOfUserWhenRatedMovie;
		}
		public String getID() {
			return userName;
		}
		public void setID(String iD) {
			userName = iD;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		public HashMap<Movie, Integer> getRatedMovies() {
			return ratedMovies;
		}
		public void setRatedMovies(HashMap<Movie, Integer> ratedMovies) {
			this.ratedMovies = ratedMovies;
		}
}
