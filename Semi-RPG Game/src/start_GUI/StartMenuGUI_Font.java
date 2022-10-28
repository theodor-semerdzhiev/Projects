package start_GUI;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;


public class StartMenuGUI_Font {

	public Font font;
	//Creates font that will be used in start menu
	//Takes in a string input for a file location
	public StartMenuGUI_Font(String File) {
		try {
		    font = Font.createFont(Font.TRUETYPE_FONT, new File(File));
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(font);
		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.print("Unable to retrieve Font file");
		} catch(FontFormatException e) {
		    e.printStackTrace();
		    System.out.print("Font File invalid");
		}  
	}
	
}
