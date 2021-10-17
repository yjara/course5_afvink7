package biotools.bioapp;

public class InvalidFileException extends Exception{
    public InvalidFileException(String reason,String statement){
        //call superclass
        super(reason+": "+statement);
    }
    public InvalidFileException(String reason, String statement, Throwable cause){
        super(reason+": "+statement,cause);
    }
}
