package newProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.Date;

public class DB {
	HashMap<String, Movie> movies;
	HashMap<String, Customer> customers;

	DB() {
		movies = new HashMap<String, Movie>();
		customers = new HashMap<String, Customer>();
	}

	public void add_Movie(String movieName) throws UserException {

		List<String> genres = new ArrayList<String>();

		// extract movie name,year,genre from given string
		String[] movieString = movieName.split(" ");
		String name = movieString[0];
		// check if movie exists already
		if (movies.containsKey(name.toLowerCase()) == true) {
			throw new UserException(name + " is already a Movie!!");
		}
		int year = Integer.parseInt(movieString[4]);
		for (int i = 7; i < movieString.length; i += 2) {
			genres.add(movieString[i]);
		}
		Movie movie = new Movie(name, year, genres);

		// add movie in the list
		movies.put(name.toLowerCase(), movie);
		System.out.println("Movie added successfully " + movie.getMovieName() + " " + movie.getReleasedYear());
	}

	public void add_review(String user, String movie, int review) throws UserException {

		// check if customer exists or not
		if (!customers.containsKey(user.toLowerCase())) {
			throw new UserException(user + " does not exist");
		}
		Customer currentUser = customers.get(user.toLowerCase());
		// check if movie exists or not
		if (!movies.containsKey(movie.toLowerCase())) {
			throw new UserException(movie + " is not in the list");
		}
		Movie currentMovie = movies.get(movie.toLowerCase());

		// check if customer is rating upcoming movie
		Date d = new Date();

		int year = d.getYear() + 1900;

		if (currentMovie.getReleasedYear() > year) {
			throw new UserException("You Can't review for upcoming movies");
		}

		// check if user has already rated movie
		if (currentUser.ratedMovies.containsKey(currentMovie)) {
			throw new UserException(user + " has already rated " + movie + " with score="
					+ currentUser.getRatedMovies().get(currentMovie));
		}

		// check if rating is within range(1-10)
		if (review < 1 || review > 10) {
			throw new UserException("Review Score should be 1-10");
		}

		// add rating for this movie for this user

		review = setRatingByWeightage(review, currentUser.getType());
		currentUser.ratedMovies.put(currentMovie, review);
		currentMovie.setScore(currentMovie.getScore() + review);
		currentMovie.setUsersRated(currentMovie.getUsersRated() + 1);
		List<ArrayList<Movie>> typeOfUserWhenRatedMovie=currentUser.getTypeOfUserWhenRatedMovie();

		typeOfUserWhenRatedMovie.get(currentUser.getType()).add(currentMovie);
		currentUser.setTypeOfUserWhenRatedMovie(typeOfUserWhenRatedMovie);



		int numberOfMoviesRated = currentUser.ratedMovies.size();
		switch (numberOfMoviesRated) {
		case 3:
			currentUser.setType(1);
			break;
		case 6:
			currentUser.setType(2);
			break;
		case 12:
			currentUser.setType(3);
		}
		System.out.println("Review score=" + currentUser.ratedMovies.get(currentMovie) + " is added successfully for "
				+ currentMovie.getMovieName() + "(" + currentMovie.getReleasedYear() + ") by " + currentUser.getID()
				+ "(" + typeNameOfUser(currentUser.getType()) + ")");
	}


	public void add_User(String userName) throws UserException {

		// check if user exists
		if (customers.containsKey(userName.toLowerCase())) {
			throw new UserException(userName + " is already a User!!");
		}

		// if not, then add user
		Customer newUser = new Customer(userName);
		customers.put(userName.toLowerCase(), newUser);
		System.out.println(newUser.getID() + "(" + typeNameOfUser(newUser.getType()) + ") is added successfully");
	}


	public void averageReviewScoreOfMovie(String name) throws UserException {
		if (!movies.containsKey(name.toLowerCase()) == true) {
			throw new UserException(name + " does not exist");
		}
		Movie currentMovie = movies.get(name.toLowerCase());
		float avg_score = currentMovie.getScore() / (float) currentMovie.getUsersRated();
		avg_score=(avg_score >= 10) ? 10 : avg_score;
		System.out.println("Averge Review Score of "+name+" is "+avg_score);
	}

	public void averageReviewScoreInYear(int n) {
		float total_score = 0;
		int total_users = 0;
		float result;
		for (Map.Entry<String, Movie> entry : movies.entrySet()) {
			Movie movie = entry.getValue();
			if (movie.getReleasedYear() == n) {
				total_score += movie.getScore();
				total_users += movie.getUsersRated();

			}
		}
		result = total_score / total_users;
		result=result >= 10 ? 10 : result;
		System.out.println("Average Review Score in "+n+" is "+result);
	}

	public void top_Movies(int n, String typeName) {
		List<Movie> top_movies = new ArrayList<Movie>();
		int type = typeNumberOfUser(typeName);

		for (Map.Entry<String, Customer> entry : customers.entrySet()) {
			Customer currentUser = entry.getValue();

			if (currentUser.getType() >= type) {
				List<ArrayList<Movie>> typeOfUserWhenRatedMovie=currentUser.getTypeOfUserWhenRatedMovie();
				for (int i=0;i<typeOfUserWhenRatedMovie.size();i++) {

					if (i >= type) {
						top_movies.addAll(typeOfUserWhenRatedMovie.get(i));
					}

				}

			}

		}
		Collections.sort(top_movies, new Comparator<Movie>() {
			public int compare(Movie a1, Movie a2) {
				float a = a1.getScore() / a1.getUsersRated();
				float b = a2.getScore() / a2.getUsersRated();
				return (int) (a > b ? a : b);
			}
		});

		top_movies= (top_movies.size() >= n) ? top_movies.subList(0, n) : top_movies;
		for(int i=0;i<top_movies.size();i++) {
			System.out.println(top_movies.get(i).movieName+" released in "+top_movies.get(i).getReleasedYear()+ " has review score="+top_movies.get(i).score);
		}
	}
	public String typeNameOfUser(int n) {
		if (n == 0) {
			return "Viewer";
		} else if (n == 1) {
			return "Critic";
		} else if (n == 2) {
			return "Expert";
		} else {
			return "Admin";
		}
	}

	public int typeNumberOfUser(String type) {
		if (type == "Viewer") {
			return 1;
		} else if (type == "Critic") {
			return 2;
		} else if (type == "Expert") {
			return 3;
		} else {
			return 4;
		}
	}

	public int setRatingByWeightage(int score, int type) {
		if (type == 1) {
			return score;
		} else if (type == 2) {
			return 2 * score;
		} else if (type == 3) {
			return 3 * score;
		} else {
			return 4 * score;
		}
	}

}