import java.util.ArrayList;
import java.util.Collections;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart extends Graphics{

	private static final long serialVersionUID = 1L;

	//Draws line graph
	public static void drawLine() {
		//resets
		slider1.setVisible(false);
		slider2.setVisible(false);
		pieLabel1.setVisible(false);
		pieLabel2.setVisible(false);
		block1.setVisible(false);
		block2.setVisible(false);
		button.setVisible(false);

		ArrayList<Blocks> blocks = Blocks.getBlocks();
		DefaultCategoryDataset defaultDataset = new DefaultCategoryDataset();
		
		//Generates CheckBoxes
		label.setText("Transaction Cost of Blocks");
		avgButton1.setName("Average Transaction Cost");
		lowButton2.setName("Lowest Transaction Cost");
		highButton3.setName("Highest Transaction Cost");
		avgButton1.setVisible(true);
		lowButton2.setVisible(true);
		highButton3.setVisible(true);
		ex.add(avgButton1);
		ex.add(lowButton2);
		ex.add(highButton3);

		//Logic for state of box when checked
		boolean selectedAverage = avgButton1.isSelected();
		if (selectedAverage) {
			int counter = 308;
			for(int i = 0; i <=  14; ++i) {
				defaultDataset.addValue(blocks.get(i).avgTransactionCost(), "Average", Integer.toString(counter));
				counter = counter + 1;
			}
		} 
		//Logic for state of box when checked
		boolean selectedLow = lowButton2.isSelected();
		if (selectedLow) {
			int counter = 308;
			for(int i = 0; i <=  14; ++i) {
				ArrayList<Double> low = new ArrayList<Double>();
				for(int k = 0; k < blocks.get(i).getTransactions().size(); ++k) {
					low.add(blocks.get(i).getTransactions().get(k).transactionCost());
				}
				Collections.sort(low);
				defaultDataset.addValue(low.get(0), "Lowest", Integer.toString(counter));
				counter = counter + 1;
			}
		} 
		//Logic for state of box when checked
		boolean selectedHigh = highButton3.isSelected();
		if (selectedHigh) {
			int counter = 308;
			for(int i = 0; i <= 14; ++i) {
				ArrayList<Double> high = new ArrayList<Double>();
				for(int k = 0; k < blocks.get(i).getTransactions().size(); ++k) {
					high.add(blocks.get(i).getTransactions().get(k).transactionCost());
				}
				Collections.sort(high);
				defaultDataset.addValue(high.get(high.size() - 1), "Highest", Integer.toString(counter));
				counter = counter + 1;
			}
		} 
		
		//Draws chart with data
		JFreeChart lineChart = ChartFactory.createLineChart("Transaction Cost of Blocks", "Block Number", "Transaction Cost (ETH)", 
				defaultDataset, PlotOrientation.VERTICAL, true, true, false);
		ChartFrame frame = new ChartFrame("Brenden Wiley - Project 5", lineChart);	     
		frame.setVisible(true);
		frame.setSize(1600, 1100);
	}
}