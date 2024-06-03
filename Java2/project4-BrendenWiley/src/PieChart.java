import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart extends Graphics {

	private static final long serialVersionUID = 1L;

	public static void drawPie() {
		//resets
		pieLabel1.setVisible(true);
		pieLabel2.setVisible(true);
		avgButton1.setVisible(false);
		lowButton2.setVisible(false);
		highButton3.setVisible(false);
		block1.setVisible(false);
		block2.setVisible(false);
		button.setVisible(false);
		label.setText("Each Unique Miner and its Frequency");
		
		//Fills blocks arraylist with info and calculates the unique blocks with linkedhashset that gets turned into an array.
		ArrayList<Blocks> blocks = Blocks.getBlocks();
		LinkedHashSet<Blocks> unique = new LinkedHashSet<Blocks>();

		for(int i = 0; i < blocks.size(); ++ i) {
			unique.add(blocks.get(i));
		}

		Blocks arr[] = new Blocks[unique.size()];
		arr = unique.toArray(arr);

		//Create 1st slider
		ex.add(slider1);
		slider1.setVisible(true);
		slider1.setMajorTickSpacing(5);
		slider1.setPaintTicks(true);
		slider1.addChangeListener(e);
		ex.add(pieLabel1);

		//Create 2nd slider
		ex.add(slider2);
		slider2.setVisible(true);
		slider2.setMajorTickSpacing(5);
		slider2.setPaintTicks(true);
		slider2.addChangeListener(k);
		ex.add(pieLabel2);

		//Calculates pie chart information from slider lower/upper bounds
		DefaultPieDataset defaultPieDataset = new DefaultPieDataset();
		int lower = slider1.getValue() - 15049308;
		int upper = slider2.getValue() - 15049309;
		for(int i = lower; i < upper; ++ i) {
			int counter = 0;
			Blocks b = arr[i];
			for(int k = lower; k < upper; ++k) {
				if(blocks.get(k).getMiner().equals(b.getMiner())) {
					++counter;
				}
			}
				defaultPieDataset.setValue(b.getMiner() + ": " + counter, counter);
		}

		//prints piechart
		JFreeChart chart = ChartFactory.createPieChart("Each Unique Miner and its Frequency", defaultPieDataset, true, true, true);
		ChartFrame frame = new ChartFrame("Project 5 - Brenden Wiley", chart);	        
		frame.setVisible(true);
		frame.setSize(1100, 1100);
	}
}

