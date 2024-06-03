import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarGraph extends Graphics{

	private static final long serialVersionUID = 1L;

	//Draws bar graph
	public static void drawBar() {
		//resets
		slider1.setVisible(false);
		slider2.setVisible(false);
		pieLabel1.setVisible(false);
		pieLabel2.setVisible(false);
		avgButton1.setVisible(false);
		lowButton2.setVisible(false);
		highButton3.setVisible(false);
		
		final DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
		
		//Generates 2 Text Fields and a Button
		label.setText("Time Difference Between Blocks");
		block2.setVisible(true);
		block1.setVisible(true);
		button.setVisible(true);
		ex.add(block2, BorderLayout.CENTER);
		ex.add(block1, BorderLayout.EAST);
		ex.add(button);
		
		//Logic for when button is pressed (generates chart)
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				
				try {
					ArrayList<Integer> time = Blocks.timeDiff(Blocks.getBlockByNumber(Integer.parseInt(block1.getText())), 
							Blocks.getBlockByNumber(Integer.parseInt(block2.getText())));
					if(!time.get(0).equals(0)) {
						categoryDataset.addValue(time.get(0), "Hours", block2.getText() + "-" + block1.getText());
						categoryDataset.addValue(time.get(1), "Minutes", block2.getText() + "-" + block1.getText());
						categoryDataset.addValue(time.get(2), "Seconds", block2.getText() + "-" + block1.getText());
					}
					else if(!time.get(1).equals(0)) {
						categoryDataset.addValue(time.get(1), "Minutes", block2.getText() + "-" + block1.getText());
						categoryDataset.addValue(time.get(2), "Seconds", block2.getText() + "-" + block1.getText());
					}
					else{
						categoryDataset.addValue(time.get(2), "Seconds", block2.getText() + "-" + block1.getText());
					}
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
				//Generates new chart with data
				JFreeChart chart = ChartFactory.createBarChart("Time Difference Between Blocks", "Blocks" ,
						"Time Units (hrs, mins, secs)", categoryDataset);
				ChartFrame frame = new ChartFrame("Project 5 - Brenden Wiley", chart);	        
				frame.setVisible(true);
				frame.setSize(1100, 1100);
			}
		});
	}
}
