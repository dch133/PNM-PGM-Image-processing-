import java.io.*;
public class Comp202Photoshop {
  
  public static void main(String[] args) { 
    
    //Check there is enough input
    if(args.length < 4){
      throw new NullPointerException("There is not enough input. Minimum 4 Strings are required");
    }
    //Command-line arguments     
    String filename = args[0];
    String outname = args[1];
    String format = args[2];
    String operation = args[3];
    
    try{
      Image image = ImageFileUtilities.read(filename);
      
      if(operation.equals("-fh")){
        image.flip(true); 
      }
      
      if(operation.equals("-fv")){
        image.flip(false);
      }
      
      if(operation.equals("-gs")){
        image.toGrey();
      }
      
      if(operation.equals("-cr")){
        int startX = Integer.parseInt(args[4]);
        int startY = Integer.parseInt(args[5]);
        int endX = Integer.parseInt(args[6]);
        int endY = Integer.parseInt(args[7]);
        image.crop(startX, startY, endX, endY);
      }
      //Creates the image with desired output name
      ImageFileUtilities.writePnm(image, outname);
      
    }catch(IllegalArgumentException e){
      System.out.println("The input bounds for the crop method were missing, negative or greater than the size of the image. Please try again.");
    } 
    catch(IOException e){
      e.printStackTrace();
      System.out.println("The file is not found, or the file does not match the proper format. Please try again.");
    }
    //Check the format requested is valid, and that the method called is valid and works
    if((!format.equals("pnm")) && (!format.equals("pgm")) && operation == null
         && !operation.equals("-fh") && !operation.equals("-fv") && !operation.equals("-gs") && !operation.equals("-cr")){
      throw new IllegalArgumentException("One or many input value(s) have the wrong format or the method called was misspelled! Try again!");
    }    
  }
}
