package newProject;

public class UserException extends Exception{
    String message;
    UserException(String str){
        message=str;
    }
    public String toString() {
      return ("Custom Exception Occurred : " + message);
    }
}