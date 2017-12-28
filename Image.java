
public class Image {
  
  private String metadata;
  private int maxRange;
  private Pixel[][] data;
  
  //Constructor initialising the attibutes and a copy of the pixel array
  public Image(String metadata, int maxRange, Pixel[][] inputData){
    if (maxRange < 0) {
      throw new IllegalArgumentException();
    }
    this.metadata = metadata;
    this.maxRange = maxRange;
    this.data = new Pixel[inputData.length][inputData[0].length];
    for (int i = 0; i < inputData.length; i++){
      for (int j = 0; j < inputData[i].length; j++){
        this.data[i][j] = inputData[i][j];
      }
    }
  }
  
  //Getter for Metadata
  public String getMetadata(){
    return this.metadata;
  }
  
  //Getter for MaxRange
  public int getMaxRange(){
    return this.maxRange;
  }
  
  //Getter for Width
  public int getWidth(){
    //length of sub-array = width of image
    return this.data[0].length;
  }
  
  //Getter for height
  public int getHeight(){
    //length of 2d array = height of image
    return this.data.length;
  }
  
  //Getter for Pixel from the 'data' array at index (i,j)
  public Pixel getPixel(int i, int j){
    Pixel p = this.data[i][j];
    return p;
  }
  
  //Flip image horizontally if boolean is true or vertically if boolean is false
  public void flip(boolean horizontal){
    Pixel[][] flippedData = new Pixel[this.data.length][this.data[0].length];

    if (horizontal){
      for (int i = 0; i < this.data.length; i++){
        for(int j = 0; j < this.data[0].length; j++){
          flippedData[i][j] = this.data[i][this.data[0].length - (j+1)];
        }
      }
      this.data = flippedData;
    }           
    else {
      for (int i = 0; i < this.data.length; i++){
        for(int j = 0; j < this.data[0].length; j++){            
           flippedData[i][j] = this.data[this.data.length - (i+1)][j];
        }
      }
      this.data = flippedData;
    }
  }
  
  // Convert the image to a greyscale image
  public void toGrey(){
    
    for (int i = 0; i < this.data.length; i++){
      for (int j = 0; j < this.data[i].length; j++){
        //Get the greyscale number for each pixel
        int intensity = this.data[i][j].grey();
        //Save that number to the array with a pixel constructor
        this.data[i][j] = new Pixel(intensity);
      }
    }    
  }
  
  //Method that crops an Image with given horizontal and vertical bounds respectively
  public void crop(int startX, int startY, int endX, int endY){
    //Check that the input for rows is valid
    if (startX < 0 || startX > this.data.length || endX < 0 || endX > this.data.length){
      throw new IllegalArgumentException();
    }
    
    //Check that the input for columns is valid
    if (startY < 0 || startY > this.data[0].length ||endY < 0 || endY > this.data[0].length){
      throw new IllegalArgumentException();
    }
    
    Pixel[][] croppedData = new Pixel[endX-startX][endY-startY];
    for (int i = startX; i < endX; i++){
      for (int j = startY; j < endY; j++){
        croppedData[i-startX][j-startY] = this.data[i][j];
      }
    }
    this.data = croppedData;
  }     
}
