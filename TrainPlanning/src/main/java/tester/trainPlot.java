package tester;

import java.awt.Color;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import dominio.MalhaFerroviariaComLoops;
import dominio.Trem;

/**
 * A simple demonstration application showing how to create a line chart using data from an
 * {@link XYDataset}.
 *
 */
public class trainPlot extends ApplicationFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	XYSeries series;
	Trem tremN1;
	XYSeriesCollection dataset = null;

	
	public trainPlot(final String title,List<Trem> trensAPlanejar, MalhaFerroviariaComLoops loopsDaMalha) {
		super(title);
		
		
			
			XYDataset dataset = createDataset(trensAPlanejar,loopsDaMalha);
			final JFreeChart chart = createChart(dataset);
			final ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new java.awt.Dimension(1500, 1270));
			setContentPane(chartPanel);
		
	}


    /**
     * Creates a sample dataset.
     * @param trensAPlanejar 
     * 
     * @return a sample dataset.
     */
    private XYDataset createDataset(List<Trem> trensAPlanejar, MalhaFerroviariaComLoops loopsDaMalha) {
        
    	
    	XYSeriesCollection dataset = null;
    	dataset = new XYSeriesCollection();
    	Collections.sort(trensAPlanejar);
		int listaTrens=trensAPlanejar.size();
		for (int i=0; i < listaTrens ; i++) {
			
			tremN1 = trensAPlanejar.get(i);
			series = new XYSeries(tremN1.getNome());
			tremN1.getPercursoDoTrem().forEach(p -> {
				// horario do trem na posicao , getpercurso
				//series.add(horaValue(p.getHorarioDoTremNaPosicao()),p.getPosicao());
			});
			
			dataset.addSeries((XYSeries) series);
		}
		 
        return dataset;
        
    }
    
    private int horaValue(LocalDateTime hora) {
 
		return (hora.getMinute());
	}


	/**
     * Creates a chart.
     * 
     * @param dataset  the data for the chart.
     * 
     * @return a chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
            "Train Scheduling",      // chart title
            "Hour",                      // x axis label
            "Positon",                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

//        final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setDisplaySeriesShapes(true);
        
        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
    //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.
                
        return chart;
        
    }

    
  
}
