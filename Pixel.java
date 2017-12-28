
public class Pixel {
  
  private int red;
  private int green;
  private int blue;
  
  //Constructor taking as input the 3 colors and initialiseing them
  public Pixel(int red, int green, int blue) { 
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255){
      throw new IllegalArgumentException();
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }
  //Constructor taking as input the intensity and initialising the 3 colors to that intensity
  public Pixel(int intensity) {
    if (intensity < 0 || intensity > 255 ) {
      throw new IllegalArgumentException();
    }
    this.red = intensity;
    this.green = intensity;
    this.blue = intensity;
  }
  
  //Getter for Red
  public int getRed(){
    return this.red;
  }
  //Getter for Green
  public int getGreen(){
    return this.green;
  }
  //Getter for Blue
  public int getBlue(){
    return this.blue;
  }
  
  //Method returning an int representing the average of 3 properties
  public int grey(){
    int average = (this.red + this.green + this.blue)/3;
    return average;
  }
  
  
}
