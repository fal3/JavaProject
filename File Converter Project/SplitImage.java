import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class SplitImage {

	public static void main( String [] args) 

	{

		try { // test if there is a valid png file,
			File importFile = new File("ScriptTemplate.png"); // import/use png file 
			if(importFile.exists())
			{
				Split(importFile);
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("There is no file found, please input a file first");  
			e.printStackTrace(); // location of error 
		} catch (IOException e) { // exception for Split 
			e.printStackTrace();
		}
	}


	@SuppressWarnings("unused")
	public static void Split(File changeImportFile) throws IOException
	{	
		changeImportFile = new File("ScriptTemplate.png"); // image, same as image in main, just needs to be filled with something, if they were different, it would use image in main 
		if(changeImportFile.exists())
		{
			System.out.println("Your image has been located at: "+ changeImportFile.getAbsolutePath());
			System.out.println("Process started");
		}
		else{
			System.out.println("Your file could not be found, please check again to ensure your file exist");
			System.exit(0);
		}
			
		FileInputStream picture = new FileInputStream(changeImportFile);; // actually read the file in bytes
		
		BufferedImage readPicture = ImageIO.read(picture); // reads in picture from bytes 
		try{
			readPicture=ImageIO.read(new File("ScriptTemplate.png"));
			System.out.println("Your image has been successful rendered");
		}
		catch(IOException e){ 
			System.out.println("Image can not be read, please try a differnt image");
			}
		
		int number =0; // need a value for the array, initialize 
		int rows = 9;
		int columns = 10;
		int numberOfSquares = rows * columns; // number of boxes
		int SquaresWidth =  readPicture.getWidth()/ columns; // height of the original full picture, we want all columns to be equal 
		int SquaresHeight = readPicture.getHeight()/ rows;// width of the original full picture , we want all rows to be equal 
		
		/*System.out.println(readPicture.getWidth()); save for testing height and width of FULL picture, 
		System.out.println(readPicture.getHeight());
		System.exit(0);*/
		
		BufferedImage[] arrayOfPicture = new BufferedImage [numberOfSquares]; // array of squares 
		try{
			if(arrayOfPicture == null)
			{
				System.out.println("Error, your image could not be captured");
			}
		
		}
		catch(NegativeArraySizeException e){ 
			System.out.println("Please try again");
			}
		
		System.out.println("Splitting has now began");
		//put all the squares into an array, so later we can determine the coordinates and split into squares 
		for( int i = 0; i<rows;i++){
			for( int j = 0; j<columns;j++){

				arrayOfPicture[number] = readPicture.getSubimage(SquaresWidth*j,SquaresHeight*i,SquaresWidth,SquaresHeight); // gets object for each row and column, 
				number ++;                             // (SquaresWidth*j,SquaresHeight*i) = ( X, Y ), (SquaresWidth,SquaresHeight) == Height and width of each box 
			}

		}

		try{

			File outputFile = null;
			for( int i =0; i<arrayOfPicture.length; i++){ // creating output for length of array, i.e columns * rows // 140 squares 
				
				outputFile = new File("ScriptTemplate" + i + ".png"); // output file 
				ImageIO.write(arrayOfPicture[i],"png", outputFile); // writes to output screen 
				
			}
		
				
				System.out.println("Finished! Image has been successful split.");
				System.out.println("The location of your files are at: " + outputFile.getAbsolutePath());
		}
		catch (FileNotFoundException e) {
			System.out.println("There is no file found, please select an output file first");  
			e.printStackTrace(); //  
		}

	}

}	

