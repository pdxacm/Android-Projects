package com.example.kwh;


import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;

public class Graph {

	Bundle kwhData;

	public Intent getIntent(Context context, double [] kwh_cst, double [] kwh_yrs, int kwh_len){
		

		XYMultipleSeriesDataset dataset = createData(kwh_cst, kwh_yrs, kwh_len);
		XYMultipleSeriesRenderer render = renderGraph();
		Intent intent = ChartFactory.getLineChartIntent(context, dataset, render, "See KWH!");
		return intent;
		
		
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

	
	public XYMultipleSeriesDataset createData(double [] kwh_c, double [] kwh_y, int kwh_len){
		
		
	    List<double[]> xVals = new ArrayList<double[]>();
	    xVals.add(kwh_y);


		
		
		//look into this more List<double[]>
	    List<double[]> yVals = new ArrayList<double[]>();
	    
	    
	     
	   // double[] kwhDataSet = kwhData.getDoubleArray("kwh_data");
	    
	    yVals.add( kwh_c );

	    
	    
	    //titles [] had elements "series1" and "series2"
	    //but was modified because we only have one series
	    // in this test
	    String[] titles = new String[]{"series1"};

	    
		return buildDataset(titles, xVals, yVals);
	    
		
	}
	
	
	public XYMultipleSeriesRenderer renderGraph(){
		
		XYMultipleSeriesRenderer r = new XYMultipleSeriesRenderer();
		String [] desc = new String[] {"Title", "Xaxsis", "Yaxsis"};
		int [] margins = new int[] {40,30,30,30};
		r.setXAxisMin(2000);
		r.setXAxisMax(2020);
		r.setYAxisMin(0.0);
		r.setYAxisMax(30.0);
		
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
	    
	    
		
	    //var i was changed from 2 to 1
	    //because we only have one series
	    for (int i = 0; i < 1; i++) {
	        XYSeriesRenderer s = new XYSeriesRenderer();
	        s.setColor(colors[i]);
	        s.setPointStyle(styles[i]);
	        r.addSeriesRenderer(s);
	      }
		
		
		return r;
	}
	}
	





