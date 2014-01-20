package com.example.linegraph;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		XYMultipleSeriesDataset dataset = createData();
		XYMultipleSeriesRenderer render = renderGraph();
		Intent intent = ChartFactory.getLineChartIntent(this, dataset, render);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	  public void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles, List<double[]> xValues,
		      List<double[]> yValues, int scale) {
		    int length = titles.length;
		    for (int i = 0; i < length; i++) {
		      XYSeries series = new XYSeries(titles[i], scale);
		      double[] xV = xValues.get(i);
		      double[] yV = yValues.get(i);
		      int seriesLength = xV.length;
		      for (int k = 0; k < seriesLength; k++) {
		        series.add(xV[k], yV[k]);
		      }
		      dataset.addSeries(series);
		    }
		    
	  }
	
	  public XYMultipleSeriesDataset buildDataset(String[] titles, List<double[]> xValues,
		      List<double[]> yValues) {
		    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		    addXYSeries(dataset, titles, xValues, yValues, 0);
		    return dataset;
		  }

	
	public XYMultipleSeriesDataset createData(){
		
		
	    List<double[]> xVals = new ArrayList<double[]>();
	    xVals.add( new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
	    xVals.add( new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});

		
		
		//look into this more List<double[]>
	    List<double[]> yVals = new ArrayList<double[]>();
	    yVals.add( new double[]{5.5, 12, 13.5, 16, 20, 30, 40, 42, 43, 45});
	    yVals.add( new double[]{1.2, 3, 10, 12, 13, 25, 27, 30, 35, 40,});
	    
	    
	    String[] titles = new String[]{"series1", "series2"};

	    
		return buildDataset(titles, xVals, yVals);
		
	}
	
	
	public XYMultipleSeriesRenderer renderGraph(){
		
		XYMultipleSeriesRenderer r = new XYMultipleSeriesRenderer();
		String [] desc = new String[] {"Title", "Xaxsis", "Yaxsis"};
		int [] margins = new int[] {40,30,30,30};
		r.setXAxisMin(0);
		r.setXAxisMax(10);
		r.setYAxisMin(0);
		r.setYAxisMax(50);
		
		r.setChartTitle(desc[0]);
		r.setXTitle(desc[1]);
		r.setYTitle(desc[2]);
		
		r.setAxisTitleTextSize(20);
		r.setChartTitleTextSize(25);
		r.setLabelsTextSize(10);
		r.setLegendTextSize(15);
		r.setXRoundedLabels(true);

	
		r.setMargins(margins);
		r.setPointSize(5);
	
		r.setGridColor(Color.DKGRAY);
		r.setShowGrid(true);
		r.setAxesColor(Color.BLACK);
		r.setExternalZoomEnabled(true);
		
		int[] colors = new int[] { Color.BLUE, Color.GREEN };
	    PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND };
	    
	    
		
	    for (int i = 0; i < 2; i++) {
	        XYSeriesRenderer s = new XYSeriesRenderer();
	        s.setColor(colors[i]);
	        s.setPointStyle(styles[i]);
	        r.addSeriesRenderer(s);
	      }
		
		
		return r;
	}
	}
	
