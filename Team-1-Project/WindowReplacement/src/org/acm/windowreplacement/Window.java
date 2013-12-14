package org.acm.windowreplacement;

import android.os.Parcel;
import android.os.Parcelable;

public class Window implements Parcelable {
	
	private String windowPaneType;
	private String windowFrameType;
	private int windowWidth;
	private int windowHeight;
	private int windowQty;
	
	//-------------------------------------------
	//Function: Constructor
	public Window(){
		
		this.windowPaneType = "";
		this.windowFrameType = "";
		this.windowWidth = 0;
		this.windowHeight = 0;
		this.windowQty = 0;
	}
	
	//---------------------------------------------
	//Function: Parcel Constructor
	public Window(Parcel parcel){
		
		readFromParcel(parcel);
	}
	
	//----------------------------------------------
	//Function: copy Constructor
	public Window(Window toCopy){
		
		this.windowPaneType = toCopy.windowPaneType;
		this.windowFrameType = toCopy.windowFrameType;
		this.windowWidth = toCopy.windowWidth;
		this.windowHeight = toCopy.windowHeight;
		this.windowQty = toCopy.windowQty;
	}
	
	//----------------------------------------------
	//Function: set_window_pane_type
	public void set_window_pane_type(String type){
		
		this.windowPaneType = type;
	}

	//-----------------------------------------------
	//Function: get_window_pane_type
	public String get_window_pane_type(){
		
		return this.windowPaneType;
	}
	
	//-----------------------------------------------
	//Function: set_window_frame_type
	public void set_window_frame_type(String type){
		
		this.windowFrameType = type;
	}
	
	//------------------------------------------------
	//Function: get_window_frame_type
	public String get_window_frame_type(){
		
		return this.windowFrameType;
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

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
		dest.writeString(this.windowPaneType);
		dest.writeString(this.windowFrameType);
		dest.writeInt(this.windowWidth);
		dest.writeInt(this.windowHeight);
		dest.writeInt(this.windowQty);
	}
	
	private void readFromParcel(Parcel in){
		
		this.windowPaneType = in.readString();
		this.windowFrameType = in.readString();
		this.windowWidth = in.readInt();
		this.windowHeight = in.readInt();
		this.windowQty = in.readInt();
	}
	
	public static final Parcelable.Creator<Window> CREATOR = new Parcelable.Creator<Window>() {

		@Override
		public Window createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Window(source);
		}

		@Override
		public Window[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Window[size];
		}
	};
}
