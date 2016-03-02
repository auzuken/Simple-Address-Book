package controllers;

public class Person {

	private String fname;
	private String lname;
	private String address;
	private String city;
	private String state;
	private String zip; 
	private String phone;
	
	public Person(String[] person){
		this.fname = person[0];
		this.lname = person[1];
		this.address = person[2];
		this.city = person[3];
		this.state = person[4];
		this.zip = person[5];
		this.phone = person[6]; 
	}
	
	public Person(String fname, String lname, String address, String city, String state, String zip,String phone){
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
	}
	
	// added to string method
	public String toString(){
		return fname+", " +lname+", "+ address+", "+city+", "+state+", "+zip+", "+phone;		
	}
	
	public String getFname() {
		return fname;
	}
	public String getLname() {
		return lname;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getZip() {
		return zip;
	}
	public String getPhone() {
		return phone;
	}
	
	public String[] getArray(){
		return new String[]{fname, lname, address, city, state, zip, phone};
	}
}
