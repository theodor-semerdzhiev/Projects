package shooter_game;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box.Filler;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SettingsMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	public JSlider MusicVolume,  SoundEffectsVolume;
	JLabel Title_MusicVolume, Title_SoundEffectsVolume;
	ImageIcon optionsGraphics;
	

	public SettingsMenu(){
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setVisible(false);
		setBackground(new Color(0,100,0,0));
		
		Filler filler1 = new Filler(new Dimension(20,20), new Dimension(35,35), new Dimension(50,50));
		add(filler1);
		
		Title_MusicVolume = new JLabel("Music Volume");
		Title_MusicVolume.setFont(StartMenu.font.deriveFont(30f));
		Title_MusicVolume.setForeground(Color.YELLOW);
		add(Title_MusicVolume);
		 
		MusicVolume= new JSlider(JSlider.HORIZONTAL, 0 ,50 ,50);
		MusicVolume.setBackground(new Color(0,0,0,0));
		MusicVolume.setPaintLabels(true);
		MusicVolume.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				SoundPlayer.setVolume(SoundPlayer.clip, MusicVolume);
			}
		}); 
		add(MusicVolume);
		
		Filler filler2 = new Filler(new Dimension(50,50), new Dimension(75,75), new Dimension(100,100));
		add(filler2);
		
		Title_SoundEffectsVolume = new JLabel("Sound Effects Volume");
		Title_SoundEffectsVolume.setFont(StartMenu.font.deriveFont(30f));
		Title_SoundEffectsVolume.setForeground(Color.YELLOW);
		add(Title_SoundEffectsVolume);
		
		SoundEffectsVolume= new JSlider(JSlider.HORIZONTAL, 0 ,100 ,100);
		SoundEffectsVolume.setBackground(new Color(0,0,0,0));
		SoundEffectsVolume.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				SoundPlayer.setVolume(SoundPlayer.clip, SoundEffectsVolume);
			}
		});
		add(SoundEffectsVolume);
		
		Filler filler3 = new Filler(new Dimension(50,50), new Dimension(75,75), new Dimension(120,120));
		add(filler3);
	}
}
