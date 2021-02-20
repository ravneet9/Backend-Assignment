package newProject;

import java.util.List;

public class UI {
	
	public static void main(String[] args) throws UserException {
	    
	    DB db=new DB();//This is in-memory database class' object
	    /*Various methods of DB class
	     add_Movie(movieName)-->to add a new movie to database
	     add_User(userName)--->to add a new User to database
	     add_review(userName,movieName,reviewScore)-->to add a review score by user for a movie
	     averageReviewScoreOfMovie(movieName)--->to retrieve score for a particular movie
	     averageReviewScoreInYear(year)-->to fetch average rating for a particular year
	     top_Movies(topN,usertype)-->to fetch top n movies with rating given by particular type of User
	     
	     Type of User:typeNumber 0 :typeName Viewer(score)
	     			  typeNumber 1 :typeName Critic(score*2)
	     			  typeNumber 2 :typeName Expert(score*3)
	     			  typeNumber 3 :typeName Admin(score*4)
	     			  
	     setRatingByWeightage(score,userType)-->it converts rating according to the Current type of User			  
	     typeNameOfUser(typeNumber)-->returns typeName from typeNumber
	     typeNumberOfUser(typeName)-->returns typeNumber from typeName
	     */
        db.add_Movie("Don released in Year 2006 for Genres Action & Comedy");
        db.add_Movie("Tiger released in Year 2008 for Genre Drama");
        db.add_Movie("Padmaavat released in Year 2006 for Genre Comedy");
        db.add_Movie("Lunchbox released in Year 2006 for Genre Drama");
        db.add_Movie("Guru released in Year 2006 for Genre Drama");
        db.add_Movie("on released in Year 2006 for Genres Action & Comedy");
        db.add_Movie("iger released in Year 2006 for Genre Drama");
        db.add_Movie("admaavat released in Year 2006 for Genre Comedy");
        db.add_Movie("Lunchbx released in Year 2006 for Genre Drama");
        db.add_Movie("Gur released in Year 2006 for Genre Drama");
//      db.add_Movie("Guru released in Year 2006 for Genre Drama");
        db.add_Movie("Metro released in Year 2006 for Genre Romance");
       
        db.add_User("SRK");
        db.add_User("Salman");
        db.add_User("Deepika");
//        db.add_User("Deepika");
        
        db.add_review("SRK", "Don", 2);
//          db.add_review("SRK", "Padmavaat", 8);
        db.add_review("Salman", "Don", 5);
        db.add_review("Deepika", "Don", 9);
        db.add_review("Deepika", "Guru", 6);
        db.add_review("SRK","on", 10); 
        db.add_review("SRK","Gur", 10); 
        db.add_review("SRK","Lunchbx", 10); 
        db.add_review("SRK","iger", 10); 
        db.add_review("Deepika", "Lunchbox", 5);
        db.add_review("Deepika", "Tiger", 5);
        db.add_review("Deepika", "Metro", 7);
        db.add_review("Deepika", "Lunchbx", 7);
        db.add_review("Deepika", "Gur", 7);
        db.add_review("SRK", "Padmaavat", 7);
        
		db.averageReviewScoreOfMovie("Padmaavat");
	
	    db.averageReviewScoreInYear(2006);
		
		db.top_Movies(2, "Critic");
	
	}

}
