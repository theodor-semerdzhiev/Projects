package shooter_game;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JSlider;

public class SoundPlayer {

	public static Clip clip;
	AudioInputStream audioInputStream;

	public SoundPlayer() {
		 try {
			audioInputStream=AudioSystem.getAudioInputStream(new File("2021-08-16_-_8_Bit_Adventure_-_www.FesliyanStudios.com.wav").getAbsoluteFile());
	        try {
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
			} catch (LineUnavailableException e) {
				System.out.print("Could not Retrieve Music file due to: " + e);
				e.printStackTrace();
			} 
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
		 } catch (UnsupportedAudioFileException | IOException e) {
				System.out.print("Could not Retrieve Music file due to: " + e);
				e.printStackTrace();
			}
	}
	
	public static void setVolume(Clip clip, JSlider slider) {
		FloatControl volume1 = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		volume1.setValue(-50f + slider.getValue());
	}
}
