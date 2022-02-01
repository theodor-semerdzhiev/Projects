package world_generator;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MapGeneratorFrame extends JFrame implements ActionListener{

	MapGenerator mapgen;
	Renderer renderer;
	static int HEIGHT=1000, WIDTH=1000;
	
	private static final long serialVersionUID = 1L;

	public MapGeneratorFrame(TopoMap topomap) {
		mapgen = new MapGenerator(topomap);
		Timer timer = new Timer(200, this);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(0,0,WIDTH, HEIGHT);
		renderer = new Renderer();
		add(renderer);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Console.display.append("Closed JFrame\n");
			}
		});
		timer.start();
	}

	public void actionPerformed(ActionEvent arg0) {
		renderer.repaint();
	}
	
	private class Renderer extends JPanel{

		private static final long serialVersionUID = 1L;

		public Renderer() {
			setVisible(true);
			setFocusable(false);
			setBounds(0,0,HEIGHT,WIDTH);
		}
		
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		mapgen.paintFinalMap(g);
		System.out.print("Painting Map\n");
		}
	}
}
