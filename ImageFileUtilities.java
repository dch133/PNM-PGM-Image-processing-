import java.util.Scanner;
import java.io.*;
public class ImageFileUtilities {
  
    //Method that reads the image file and converts to color/greyscale Image object
    public static Image read(String filename) throws IOException{
      Scanner sc = new Scanner(new File(filename));
      
      String format = sc.nextLine(); //Scan for the P2/P3 header
      String metadata = "";
      
      //Add comments to metadata that start with '#'
      while(sc.hasNext("#")){
        // sc.nextLine scans for the next line of comment
        metadata = metadata + sc.nextLine() + "\n";
      }
      
      //Get the width height and maxrange and initialize the pixel array
      int width = sc.nextInt();
      int height = sc.nextInt();
      int maxRange = sc.nextInt();
      
      Pixel[][] pixelArray = new Pixel[height][width];
      
      //Check format is P2 or P3
     // if(!format.equals("P3") && !format.equals("P2")){
      //  throw new IOException();
      //}
      
      //Filling the image with the respective format
      //P3 means color pixels and P2 means greyscae pixels
      if(format.equals("P3")){
        
        for(int i=0; i < pixelArray.length; i++){
          for(int j=0; j < pixelArray[i].length; j++){
            int red = sc.nextInt();
            int green = sc.nextInt();
            int blue = sc.nextInt();
            Pixel pixel = new Pixel(red,green,blue);
            pixelArray[i][j] = pixel;
          }
        }
      } else {
        for(int i=0; i < pixelArray.length; i++){
          for(int j=0; j < pixelArray[i].length; j++){
            int intensity = sc.nextInt();
            Pixel greyScalePixel = new Pixel(intensity);
            pixelArray[i][j] = greyScalePixel;
          }
        }
      }
      sc.close();
      Image image = new Image(metadata, maxRange, pixelArray);
      return image;
    }
    
  //Method that writes a pnm (color) image file
  public static void writePnm(Image colorImage, String filename) throws IOException{
    FileWriter fw = new FileWriter(filename); //creates a file writer
    BufferedWriter bw = new BufferedWriter(fw); //creates a buffered writer
    
    //write the format
    bw.write("P3 \n");
    // Get the metadata and write it into the image
    bw.write(colorImage.getMetadata());
    // Get the height and width and write it into the image
    int height = colorImage.getHeight();
    int width = colorImage.getWidth();
    bw.write(width + " " + height + "\n");
    //Get the max range and go to the next line
    bw.write(colorImage.getMaxRange() + "\n");
    //Get the colored pixels and write them into the image
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        //write each color into pnm file
        bw.write(colorImage.getPixel(i,j).getRed() + " " );
        bw.write(colorImage.getPixel(i,j).getGreen() + " " );
        bw.write(colorImage.getPixel(i,j).getBlue() + " " );
      }
      bw.write("\n"); //add a new line to the file
    }
    bw.close();
    fw.close();
  }
  
  //Method that writes a pgm (greyscale) image file
  public static void writePgm(Image greyImage, String filename) throws IOException{
    FileWriter fw = new FileWriter(filename); //creates a file writer
    BufferedWriter bw = new BufferedWriter(fw); //creates a buffered writer
    
    //write the format
    bw.write("P2 \n");
    // Get the metadata and write it into the image
    bw.write(greyImage.getMetadata());
    // Get the height and width and write it into the image
    int height = greyImage.getHeight();
    int width = greyImage.getWidth();
    bw.write(width + " " + height + "\n");
    //Get the max Range and go to the next line
    bw.write(greyImage.getMaxRange() + "\n");
    //Get the greyscale pixels and write them into the image
    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        bw.write(greyImage.getPixel(i,j).grey()+ " " ); //convert each pixel to greyscale and write into pgm file
      }
      bw.write("\n"); //add a new line to the file
    }
    bw.close();
    fw.close();
  }
      
}
