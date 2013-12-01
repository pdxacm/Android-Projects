package org.acm.windowreplacement;

public class Customer {
	
	private String firstName;
	private String lastName;
	private String email;
		
	private String state;
	private String heatingType;
	private double monthlyHeatingCost;
	private double windowReplacementCost;
	private double savingsPerYear;
	private double returnOnInvestment;
	
	
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
}
