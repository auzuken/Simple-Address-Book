package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddressBookController {

	private AddressBook currentBook;
	private FileSys fileSys;

	public AddressBookController() {
		currentBook = null;
		fileSys = new FileSys();
	}

	/**
	 * 
	 */
	public void newAddressBook(){
		currentBook = new AddressBook();
	}
	
	/**
	 * @return file that the addressbook is saved at
	 */
	public File getFile(){
		return this.currentBook.getFile();
	} 
	
	/**
	 * @return a list of persons from the current addressBook
	 */
	public ArrayList<Person> getAddressBookInfo() {
		return this.currentBook.getPersons();
	}

	/**
	 * @param file
	 *            enter the csv file and this will load the data into an object
	 *            of type AddressBook
	 * @return returns true if reading the file is successful returns false if
	 *         it isn't.
	 */
	public boolean loadAddressBook(File file) {
		currentBook = fileSys.readFile(file);
		if (currentBook == null)
			return false;
		return true;
	}

	/**
	 * @param file
	 *            enter a new file object to write to
	 * @return returns true if this worked and false otherwise
	 */
	public boolean createAddressBook(File file) {
		if (fileSys.saveFile(new AddressBook(), file))
			return true;
		else
			return false;
	}

	/**
	 * @param strPerson
	 *            creates a new person object and adds it to the current address
	 *            book
	 */
	public void addPerson(String[] strPerson) {
		Person person = new Person(strPerson);
		currentBook.addPerson(person);
	}

	/**
	 * @param origPerson
	 *            This is the information from the person that needs to be
	 *            edited
	 * @param editPerson
	 *            THis is the person object to replace the old object
	 * @return true if it worked and false if it didn't
	 */
	public boolean editPerson(Person origPerson, Person editPerson) {

		if (currentBook.existsPerson(origPerson)) {
			currentBook.deletePerson(origPerson);
			currentBook.addPerson(editPerson);
			return true;
		} else
			return false;
	}

	/**
	 * @param strPerson
	 *            This is the person object to delete from the address book
	 * @return
	 */
	public boolean deletePerson(Person personToDelete) {
		if (!currentBook.existsPerson(personToDelete))
			return false;
		currentBook.deletePerson(personToDelete);
		return true;
	}

	/**
	 * @param name
	 *            This is the name that needs to be searched for through the
	 *            list of people in the current addressbook
	 * @return return all the people in the address book that match the search
	 *         name
	 */
	public Person[] searchName(String name) {

		// create Person list to add all the people that match the search name
		List<Person> peopleMatching = new ArrayList<Person>();
		// iterate through the people in the current addressbook
		for (Person p : currentBook.getPersons()) {
			if (name.contains(p.getFname()) || name.contains(p.getLname())) {
				// add person to list
				peopleMatching.add(p);
			}
		}
		return (Person[]) peopleMatching.toArray();
	}

	/**
	 * @param zip
	 *            This is the zip that needs to be searched for
	 * @return returns the array of persons that have the matching zip code
	 */
	public Person[] searchZIP(String zip) {
		// create Person list to add all the people that match the search name
		List<Person> peopleMatching = new ArrayList<Person>();
		// iterate through the people in the current addressbook
		for (Person p : currentBook.getPersons()) {
			if (p.getZip().contains(zip)) {
				// add person to list
				peopleMatching.add(p);
			}
		}
		return (Person[]) peopleMatching.toArray();
	}
}
