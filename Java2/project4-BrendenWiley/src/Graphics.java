import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.event.*;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;

public class Graphics extends JFrame implements ActionListener{

	//Global variables
	private static final long serialVersionUID = 1L;
	String[] select = {"--select chart--", "Unique Miners", "Transaction Cost", "Time Difference"};
	JComboBox comboBox = new JComboBox(select);
	static JLabel label = new JLabel("Project 5 - Brenden Wiley");
	static JLabel pieLabel1 = new JLabel();
	static JLabel pieLabel2 = new JLabel();
	static Graphics ex = new Graphics();
	static JSlider slider1 = new JSlider(JSlider.HORIZONTAL, 15049308, 15049407, 15049308);
	static JSlider slider2 = new JSlider(JSlider.HORIZONTAL, slider1.getValue() + 1, 15049408, 15049309);
	static event e = new event();
	static event k = new event();
	
	static JCheckBox avgButton1 = new JCheckBox("Average");
	static JCheckBox lowButton2 = new JCheckBox("Lowest");
	static JCheckBox highButton3 = new JCheckBox("Highest");
	
	static JTextField block2 = new JTextField("", 15);
	static JTextField block1 = new JTextField("", 15);
	static JButton button = new JButton("Add");
	
	//Main method
	public static void main(String[] args) throws FileNotFoundException, IOException{
		//Reads data and sets window to be visible
		Blocks.readFile("ethereumP1data.csv");
		Blocks.sortBlocksByNumber();
		ex.setVisible(true);
		slider1.setValue(15049308);
		slider2.setValue(15049408);
	}

	public Graphics(){
		//Draws frame
		setLayout(new FlowLayout());
		setSize(1500, 1500);
		setTitle("Project 5 - Brenden Wiley");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		comboBox.addActionListener(this);
		add(label);
		add(comboBox);
	}

	//Determines state of combobox and initiates corresponding function
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == comboBox) {
			JComboBox jbox = (JComboBox)e.getSource();
			String string = (String)jbox.getSelectedItem();
			switch (string) {
			case "--select chart--": label.setText("Please select");
			break;
			case "Unique Miners": PieChart.drawPie();
			break;
			case "Transaction Cost": LineChart.drawLine();
			break;
			case "Time Difference": BarGraph.drawBar();
			break;
			}
		}
	}
	//Determines state of pie sliders and prints values
		public static class event implements ChangeListener{
			public void stateChanged(ChangeEvent e) {
				int value = slider1.getValue();
				pieLabel1.setText("Lower Bound: " + value);
				slider2.setMinimum(slider1.getValue() + 1);
				value = slider2.getValue();
				pieLabel2.setText("Upper Bound: " + value);
			}
		}
}