package world_generator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;


public class Console extends JPanel {
	private static final long serialVersionUID = 1L;
	private int UserInputHistory_IndexTracker;
	public static Display display;
	public static TextArea textarea;
	private TopoMap topomap;
	public ArrayList <String> list_of_commands= new ArrayList<String>(Arrays.asList("Create Topo Array", 
																					"Clear", "Map Generate", "Display Topo Array", "List Commands", "Get Input History", "Get Parameters", "Set Sea Elevation", 
																					"Set Max Elevation"));
	
	public ArrayList <String> keywords = new ArrayList<String>(Arrays.asList("get", "create", "topo", "array", "cl", "cle", "clea","generate", "map", "Display", "list", "command", "input", "history", "parameters"));
	
	public ArrayList<String> UserInput_History;

	public Console() {
		UserInput_History = new ArrayList<String>();

		textarea = new TextArea();
		display = new Display();

		setLayout(new BorderLayout());
		setBounds(0, 0, WIDTH, HEIGHT);
		setVisible(true);
		add(new JScrollPane(display), BorderLayout.CENTER);
		add(textarea, BorderLayout.SOUTH);

		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "ENTER");
		getActionMap().put("ENTER", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				UserInput_History.add(textarea.getText().trim().replaceAll("\\s+", " "));
				UserInputHistory_IndexTracker = UserInput_History.size() - 1;
				getRequestInfo();
				textarea.setText("");
			}
		});
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "UP");
		getActionMap().put("UP", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				try {
					LoopAcrossHistoryArray(1);
				}catch(Exception ex) {}
			}
		});
		getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "DOWN");
		getActionMap().put("DOWN", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				try {
					LoopAcrossHistoryArray(1);
				}catch(Exception ex) {}
			}
		});
	}
	//gets console response to user input
	public void getRequestInfo() {
		ArrayList<String> command_parameters;
		switch (textarea.getText().trim().replaceAll("\\s", "").replaceAll("[0-9]", "").replaceAll("-", "").toLowerCase()) {
		
		//sets sea Elevation live which will update map generator frame live
		case "setseaelevation":
			command_parameters = getCommandParameters(textarea.getText(), "Set Sea Elevation", 1);
			SetSeaElevation_Command(command_parameters.get(1));
			return;
			
		// returns the dimensions and Max/Min Elevation of the topography array
		case "getparameters":
			Parameters_Command();
			return;

		// creates a new topograhy array
		// can be used before Map Generate command
		case "createtopoarray":
			try {
				command_parameters = getCommandParameters(textarea.getText(), "Create Topo Array", 3);
				CreateTopographieArray_Command(Integer.valueOf(command_parameters.get(0)),Integer.valueOf(command_parameters.get(1)), Float.valueOf(command_parameters.get(2)));
				return;
			} catch (NullPointerException e) {
				display.append(
						"Invalid Parameters\nProper Syntax: Create Topo Array {Size Modifier} {MaxElevation} {SeaElevation}\n");
				return;
			}

			// delete all text in console
		case "clear":
			display.setText(null);
			textarea.setText("");
			return;

		// Generates map and opens new JFrame displaying the map
		// New topography map is created if hasn't been created yet
		// If topography as been created it uses that instead
		case "mapgenerate":
			try {
				command_parameters = getCommandParameters(textarea.getText(), "Map Generate", 3);
				MapGenerate_Command(Integer.valueOf(command_parameters.get(0)),Integer.valueOf(command_parameters.get(1)), Float.valueOf(command_parameters.get(2)));
				return;
			} catch (NullPointerException e) {
				if (topomap == null) {
					display.append("Can not generate map due to Uninitialized topography array\nProper Syntax: Map Generate {Height} {Width} {MaxElevation} {SeaElevation}\n");
					return;
				} else {
					MapGenerate_Command(topomap.getSizeModifier(),topomap.getMaxElevation(), topomap.getSeaElevation());
				}
			}

			// displays currently initialized topographie array onto console
		case "displaytopoarray":
			try {
				topomap.DisplayTopoMapInfo();
			} catch (NullPointerException e) {
				display.append("Topo Array not initialized");
			}
			return;

		// Displays all commands that can be used on console
		case "listcommands":
			display.append("All Commands:\n");
			DisplayArrayInfo(list_of_commands);
			return;

		// displays UserInput_History Array
		case "getinputhistory":
			display.append("Input Log:\n");
			for (int i = 0; i < UserInput_History.size(); i++) {
				display.append(i + "- " + UserInput_History.get(i) + "\n");
			}
			return;
		// in case enter key is pressed while TextArea is empty to prevent spam
		case "":
			return;
		}
		// if user input does not match any commands, a list of similar commands are
		// proposed if possible
		if (GetRecommendedCommand(textarea.getText().trim().replaceAll("\\s", "").toLowerCase()).size() == 0) {
			display.append("'" + textarea.getText().trim().toLowerCase() + "' is not an existing command\n");
		} else {
			display.append("'" + textarea.getText().trim().toLowerCase() + "' is not an existing command\n");
			display.append("Similar Commands: ");
			DisplayArrayInfo(GetRecommendedCommand(textarea.getText().trim().replaceAll("\\s", "").toLowerCase()));
		}
	}

	// Returns recommended commands depending on user input
	public ArrayList<String> GetRecommendedCommand(String UserInput) {
		ArrayList<String> UserInput_InParts = new ArrayList<String>();
		for (int i = 0; i < keywords.size(); i++) {
			if (UserInput.trim().replaceAll("\\s", "").toLowerCase().matches(".*" + keywords.get(i) + ".*")) {
				for (int j = 0; j < list_of_commands.size(); j++) {
					if (list_of_commands.get(j).replaceAll("\\s", "").toLowerCase().matches(".*" + keywords.get(i) + ".*")) {
						if (!UserInput_InParts.contains(list_of_commands.get(j))) {
							UserInput_InParts.add(list_of_commands.get(j));
							break;
						}
					}
				}
			}
		}
		return UserInput_InParts;
	}

	// DownOrUp will either be 1 or -1
	public void LoopAcrossHistoryArray(int DownOrUp) {

		textarea.setText(UserInput_History.get(UserInputHistory_IndexTracker));
		UserInputHistory_IndexTracker += DownOrUp;
		if (UserInput_History.size() == UserInputHistory_IndexTracker) {
			UserInputHistory_IndexTracker = 0;
		} else if (UserInputHistory_IndexTracker == -1) {
			UserInputHistory_IndexTracker = UserInput_History.size() - 1;
		}
	}

	//gets Parameters for a command
	public ArrayList <String> getCommandParameters(String UserInput, String Command, int NumberOfParameters) { //Command string should be written with spaces in between words
		ArrayList <String> Parameters = new ArrayList<String>(Arrays.asList(UserInput.trim().toLowerCase().split("\\s")));
		if(Parameters.size()-Command.split("\\s+").length==NumberOfParameters) {
			for(int i=0; i<Parameters.size();i++) {
				if(Command.toLowerCase().contains(Parameters.get(0))) {
					Parameters.remove(0);
					continue;
				} else if(i==0) {
					return null;
				} else {
					break;
				}
			}
		} else {
			return null;
		}
		return Parameters;
	}

	public void MapGenerate_Command(int SizeModifier, float MaxElevation, float SeaElevation) {
		try {
			if(topomap==null) {
				if(SizeModifier<12 && SeaElevation > 0) {
					topomap= new TopoMap(SizeModifier, MaxElevation, SeaElevation);
					display.append("Topography map not initialized\n");
					display.append("Topography map now initialized\n");
					new MapGeneratorFrame(topomap);
					return;
				} else {
					display.append("Improper parameters:\n");
					display.append("Size Modifier to large\n" + "Map Generate {SizeModifier} {MaxElevation} {Sea Elevation}\n");
					return;
				}
			} 
			display.append("Topography map already initialized\n");
			new MapGeneratorFrame(topomap);
			display.append("Map Generated... \n");
			textarea.setText("");

		} catch(Exception e) {
			display.append("Unproper Syntax\n");
		}
	}

	public void DisplayArrayInfo(ArrayList <String> list) {
		for(int i=0; i<list.size();i++) {
			display.append(list.get(i)+", ");
			}
			display.append("\n");
	}
	
	public void Parameters_Command() {
		try {
			display.append("Get Parameters of loaded topography map:\n");
			display.append("Height: " + topomap.getTopoMapHeight() + "\nWidth: " + topomap.getTopoMapWidth()+"\nMax Elevation: "+topomap.getMaxElevation()+"\nSea Elevation: "+topomap.getSeaElevation()+"\n");
			textarea.setText("");
			return;
			}catch(NullPointerException e){
				display.append("ERROR: Topography map as not been initialized\n");
			}
	}
	
	public void SetSeaElevation_Command(String SeaElevation) {
		try {
			topomap.setSeaElevation(Float.valueOf(SeaElevation));
			} catch (NullPointerException e) {
				if(topomap==null) {
				display.append("Topo Array as not been initialized");
				} else {
					display.append("Improper syntax: Set Sea Elevation {Sea Elevation}");
				}
			} 
	}
	
	public void CreateTopographieArray_Command(int SizeModifier, float MaxElevation, float SeaElevation) {
		try {
			if(SizeModifier < 11 && SizeModifier > 0 && SeaElevation > 0) {
			topomap = new TopoMap(SizeModifier, MaxElevation, SeaElevation);
			topomap.DisplayTopoMapInfo();
			textarea.setText("");
			return;
			} else {
				display.append("Size modifier too large or is negative\n"
						+ "Create Topo Array {SizeModifier} {MaxElevation} {Sea Elevation}\n");
				}
			}catch(Exception e) {
				display.append("Error loading Topography map due to: "+ e.toString()+"\n");
			}
	}

	static class Display extends JTextArea {
		private static final long serialVersionUID = 1L;

		public Display() {

			setFont(new Font("Plain", 13, 13));
			setLineWrap(true);
			setSize(WIDTH, HEIGHT);
			setFocusable(true);
			setEditable(false);
			setBackground(Color.BLACK);
			setForeground(Color.green);
		}
	}

	static class TextArea extends JTextField {
		private static final long serialVersionUID = 1L;

		public TextArea() {
			setFont(new Font("Plain", 15, 15));
			setSize(100, WIDTH);
			setFocusable(true);
			setEditable(true);
			setBackground(Color.BLACK);
			setForeground(Color.GREEN);
			setCaretColor(Color.GREEN);
		}
	}
}
