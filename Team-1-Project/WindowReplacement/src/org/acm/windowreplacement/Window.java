package org.acm.windowreplacement;

public class Window {
	
	private String windowType;
	private int windowWidth;
	private int windowHeight;
	private int windowQty;
	
	//-------------------------------------------
	//Function: Constructor
	public Window(){
		
		this.windowType = "";
		this.windowWidth = 0;
		this.windowHeight = 0;
		this.windowQty = 0;
	}
	
	//----------------------------------------------
	//Function: copy Constructor
	public Window(Window toCopy){
		
		this.windowType = toCopy.windowType;
		this.windowWidth = toCopy.windowWidth;
		this.windowHeight = toCopy.windowHeight;
		this.windowQty = toCopy.windowQty;
	}
	
	//----------------------------------------------
	//Function: set_window_type
	public void set_window_type(String type){
		
		this.windowType = type;
	}

	//-----------------------------------------------
	//Function: get_window_type
	public String get_window_type(){
		
		return this.windowType;
	}
	
	//-----------------------------------------------
	//Function: set_windowWidth
	public void set_window_width(int width){
		
		this.windowWidth = width;
	}
	
	//------------------------------------------------
	//Function: get_window_width
	public int get_window_width(){
		
		return this.windowWidth;
	}
	
	//------------------------------------------------
	//Function: set_window_height
	public void set_window_height(int height){
		
		this.windowHeight = height;
	}
	
	//------------------------------------------------
	//Function: get_window_height
	public int get_window_height(){
		
		return this.windowHeight;
	}
	
	//-------------------------------------------------
	//Function: set_window_quantity
	public void set_window_quantity(int qty){
		
		this.windowQty = qty;
	}
	
	//-------------------------------------------------
	//Function: get_window_quantity
	public int get_window_quantity(){
		
		return this.windowQty;
	}
	
}
