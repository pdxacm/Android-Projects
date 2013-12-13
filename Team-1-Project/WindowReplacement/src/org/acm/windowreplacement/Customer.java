package org.acm.windowreplacement;

import java.util.LinkedList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;


public class Customer implements Parcelable{
	
	private String firstName;
	private String lastName;
	private String email;
		
	private String state;
	private String heatingType;
	private double monthlyHeatingCost;
	private double windowReplacementCost;
	private double savingsPerYear;
	private double returnOnInvestment;

	List<Window> windows;
	
	//Constructor
	public Customer(){
		
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.state = "";
		this.heatingType = "";
		this.monthlyHeatingCost = 0;
		this.windowReplacementCost = 0;
		this.savingsPerYear = 0;
		this.returnOnInvestment = 0;
		
		windows = new LinkedList<Window>();
	}
	
	//---------------------------------------------------
	public Customer(Parcel parcel){
		readFromParcel(parcel);
	}
	//---------------------------------------------------
	//Copy Constructor
	public Customer(Customer toCopy){
		
		this.firstName = toCopy.firstName;
		this.lastName = toCopy.lastName;
		this.email = toCopy.email;
		this.state = toCopy.state;
		this.heatingType = toCopy.heatingType;
		this.monthlyHeatingCost = toCopy.monthlyHeatingCost;
		this.windowReplacementCost = toCopy.windowReplacementCost;
		this.savingsPerYear = toCopy.savingsPerYear;
		this.returnOnInvestment = toCopy.returnOnInvestment;

		//get the length of the list to copy
		int listLength = toCopy.windows.size();
		
		//copy each element of the toCopy list and then add it to
		// to the new list.
		for(int ndx = 0; ndx < listLength; ndx++)
		{
			Window copy = new Window(toCopy.windows.get(ndx));
			this.windows.add(copy);
		}
	}
	
	//-------------------------------------------------
	//Function: add_window
	public boolean add_window(Window toAdd){
		
		return this.windows.add(toAdd);
	}
	
	//-------------------------------------------------
	//Function: get_all_windows
	//Desc: Creates an array of all window objects in the list and
	//	then passes the array back to the caller.
	public Window [] get_all_windows(){
		
		Window [] toReturn = this.windows.toArray(new Window[this.windows.size()]);
		
		return toReturn;
		
	}
	//-------------------------------------------------
	//Function: set_first_name
	public void set_first_name(String firstName){
		
		this.firstName = firstName;
	}
	
	//-------------------------------------------------
	//Function: get_first_name
	public String get_first_name(){
		
		return this.firstName;
	}
	
	//-------------------------------------------------
	//Function: set_last_name
	public void set_last_name(String lastName){
		
		this.lastName = lastName;
	}
	
	//-------------------------------------------------------
	//Function: get_last_name
	public String get_last_name(){
		
		return this.lastName;
	}
	
	//-----------------------------------------------------
	//Function: set_email
	public void set_email(String emailAddress){
		
		this.email = emailAddress;
	
	}
	
	//-------------------------------------------------------
	//Function: get_email
	public String get_email(){
		
		return this.email;
	}
	
	//--------------------------------------------------------
	//Function: set_state
	public void set_state(String state){
		
		this.state = state;
	}
	
	//--------------------------------------------------------
	//Function get_state
	public String get_state(){
		
		return this.state;
		
	}
	
	//------------------------------------------------------
	//Function: set_heating_type
	public void set_heating_type(String heatingType){
		
		this.heatingType = heatingType;
	}
	
	//-------------------------------------------------------
	//Function: get_heating_type
	public String get_heating_type(){
		
		return this.heatingType;
	}
	
	//--------------------------------------------------------
	//Function: set_monthly_heating_cost
	public void set_monthly_heating_cost(double monthlyHeatingCost){
		
		this.monthlyHeatingCost = monthlyHeatingCost;
	}
	
	//--------------------------------------------------------------
	//Function: get_monthly_heating_cost
	public double get_monthly_heating_cost(){
			
		return this.monthlyHeatingCost;
	}
	
	//-----------------------------------------------------------
	//Function: set_window_replacement_cost
	public void set_window_replacement_cost(double windowReplacementCost){
		
		this.windowReplacementCost = windowReplacementCost;
	}
	
	//--------------------------------------------------------------------
	//Function: get_window_replacement_cost
	public double get_window_replacement_cost(){
		
		return this.windowReplacementCost;
	}
	
	//--------------------------------------------------------------
	//Function: set_savings_per_year
	public void set_savings_per_year(double savingsPerYear){
		
		this.savingsPerYear = savingsPerYear;
	}
	
	//----------------------------------------------------------------
	//Function get_savings_per_year
	public double get_savings_per_year(){
		return this.savingsPerYear;
	}
	
	//-------------------------------------------------------------
	//Function: set_return_on_investment
	public void set_return_on_investment(double roi){
		
		this.returnOnInvestment = roi;
	}
	
	//--------------------------------------------------------------
	//Function: get_return_on_investment
	public double get_return_on_investment(){
		
		return this.returnOnInvestment;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int arg1) {
		
		dest.writeString(this.firstName);
		dest.writeString(this.lastName);
		dest.writeString(this.email);
		dest.writeString(this.state);
		dest.writeString(this.heatingType);
		dest.writeDouble(this.monthlyHeatingCost);
		dest.writeDouble(this.windowReplacementCost);
		dest.writeDouble(this.savingsPerYear);
		dest.writeDouble(this.returnOnInvestment);
		dest.writeList(this.windows);
	}
	
	public void readFromParcel(Parcel in){
		
		this.firstName = in.readString();
		this.lastName = in.readString();
		this.email = in.readString();
		this.state = in.readString();
		this.heatingType = in.readString();
		this.monthlyHeatingCost = in.readDouble();
		this.windowReplacementCost = in.readDouble();
		this.savingsPerYear = in.readDouble();
		this.returnOnInvestment = in.readDouble();
		in.readList(this.windows, getClass().getClassLoader());
	}
	
	public static final Parcelable.Creator<Customer> creator = new Parcelable.Creator<Customer>() {

		@Override
		public Customer createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Customer(source);
		}

		@Override
		public Customer[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Customer[size];
		}
	};
}
