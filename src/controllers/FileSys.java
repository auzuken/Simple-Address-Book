package controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileSys {

	BufferedWriter writer;
	AddressBook addressBook;
	
	public FileSys(){
		writer = null;
	}
	
	public AddressBook readFile(File file){
		Scanner sc = null;
		try {
			sc = new Scanner(file);
			AddressBook addressBook = new AddressBook();
			while(sc.hasNextLine()){
				String[] str = sc.nextLine().split(",");
				if(str.length==7){
					addressBook.addPerson(new Person(str));
					System.out.println(str[0]);
				}
			}
			addressBook.setFile(file);
			return addressBook;
		} catch (FileNotFoundException e) {
			System.out.println("File " + file.getName() + " not found at " + file.getPath());
		} finally{
			if(sc!=null)sc.close();
		}
		return null;
	}
	
	public boolean saveFile(AddressBook addressBook, File file){
		
		try{
			writer = new BufferedWriter(new FileWriter(file));
			ArrayList<Person> persons = addressBook.getPersons();
			for(Person person: persons){
				writer.write(person.getFname() + "," + person.getLname() + "," + 
							person.getAddress() + "," + person.getCity() + "," + 
							person.getState() + "," + person.getZip() + "," + 
							person.getPhone() + ",");
				writer.newLine();
			}
			writer.close();
			return true;
		}catch(Exception e){
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
}