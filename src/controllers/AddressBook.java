package controllers;

import java.io.File;
import java.util.ArrayList;

public class AddressBook {

	private ArrayList<Person> persons;
	private File file;
	
	public AddressBook(){
		persons = new ArrayList<Person>();
		file = null;
	}
	
	public void setFile(File file){
		this.file = file;
	}
	
	public File getFile(){
		return this.file;
	}
	
	public ArrayList<Person> getPersons() {
		return this.persons;
	}

	public boolean existsPerson(Person person){
		return this.persons.contains(person);
	}
	
	public void setPersons(ArrayList<Person> persons) {
		this.persons = persons;
	}

	public boolean addPerson(Person person){
		if(this.persons.contains(person)) return false;
		else {
			this.persons.add(person);
			return true;
		}
	}
	
	public boolean deletePerson(Person person){
		if(!this.persons.contains(person)) return false;
		else {
			this.persons.remove(person);
			return true;
		}
	}
	
	public boolean editPerson(Person person, Person edittedPerson){
		if(!this.persons.contains(person)) return false;
		else {
			this.persons.remove(person);
			this.persons.add(edittedPerson);
			return true;
		}
	}
	
	public String[][] toArray(){
		String[][] result = new String[this.persons.size()][];
		int i = 0;
		for(Person person: persons){
			result[i] = person.getArray();
			i++;
		}
		return result;
	}
}
