package GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controllers.AddressBook;
import controllers.AddressBookController;
import controllers.FileSys;
import controllers.Person;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.border.EtchedBorder;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class GUI extends JFrame {

	private JPanel contentPane;
	private AddressBookController abc;
	private boolean isSaved;
	
	////Initialize Components
	//File
	JMenuBar menuBar;
	JMenu mnFile;
	JMenuItem mntmNewAddressbook;
	JMenuItem mnOpenAddressbook;
	JSeparator separator_1;
	JMenuItem mntmSaveAddressbook;
	JMenuItem mntmSaveAddressbookAs;
	JSeparator separator;
	JMenuItem mntmExit;
	
	//Search
	JMenu mnSearch;
	JMenuItem mntmByZip;
	JMenuItem mntmByName;
	JMenuItem mntmByPhone;
	
	//Workspace
	JPanel workSpace;
	
	//Person
	JPanel panel;
	JButton btnAddPerson;
	JButton btnEditPerson;
	JButton btnDeletePerson;
	JList list_persons;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		
		//Initialize Controller
		abc = new AddressBookController();
		isSaved = true;
		
		setResizable(false);
		setTitle("");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		////Initialize Components
		//File
		menuBar = new JMenuBar();
		mnFile = new JMenu("File");
		mntmNewAddressbook = new JMenuItem("New");
		mnOpenAddressbook = new JMenuItem("Open...");
		separator_1 = new JSeparator();
		mntmSaveAddressbook = new JMenuItem("Save");
		mntmSaveAddressbookAs = new JMenuItem("Save As...");
		mntmSaveAddressbookAs.setEnabled(false);
		separator = new JSeparator();
		mntmExit = new JMenuItem("Exit");
		
		//Search
		mnSearch = new JMenu("Search");
		mntmByZip = new JMenuItem("by ZIP");
		mntmByName = new JMenuItem("by Name");
		mntmByPhone = new JMenuItem("by Phone");
		
		//Workspace
		workSpace = new JPanel();
		list_persons = new JList();
		
		//Person
		panel = new JPanel();
		btnAddPerson = new JButton("Add Person...");
		btnEditPerson = new JButton("Edit Person...");
		btnDeletePerson = new JButton("Delete Person");
		
		////Adding and Configuration
		menuBar.setBounds(0, 0, 444, 21);
		contentPane.add(menuBar);
		//File
		menuBar.add(mnFile);
		mnFile.add(mntmNewAddressbook);
		mnFile.add(mnOpenAddressbook);
		mnFile.add(separator_1);
		mntmSaveAddressbook.setEnabled(false);
		mnFile.add(mntmSaveAddressbook);
		mnFile.add(mntmSaveAddressbookAs);
		mnFile.add(separator);
		mnFile.add(mntmExit);
		
		//Search
		menuBar.add(mnSearch);
		mntmByZip.setEnabled(false);
		mnSearch.add(mntmByZip);
		mntmByName.setEnabled(false);
		mnSearch.add(mntmByName);
		mntmByPhone.setEnabled(false);
		mnSearch.add(mntmByPhone);
		
		//Workspace
		workSpace.setBounds(10, 32, 424, 428);
		contentPane.add(workSpace);
		workSpace.setLayout(new BorderLayout(0, 0));
		
		list_persons.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_persons.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		workSpace.add(list_persons, BorderLayout.CENTER);
		
		//Person
		workSpace.add(panel, BorderLayout.SOUTH);
		btnAddPerson.setEnabled(false);
		panel.add(btnAddPerson);
		btnEditPerson.setEnabled(false);
		panel.add(btnEditPerson);
		btnDeletePerson.setEnabled(false);
		panel.add(btnDeletePerson);
		
		/////Action Listeners
		
		//Mouse Clicked File->New
		mntmNewAddressbook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				//Ask if the user really wants to make a new Address Book and lose changes
				if(!isSaved){
					int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null, "Creating a new Address Book will delete unsaved changed! "
							+ "Would you like to create a new Address Book?", "Warning", dialogButton);
					
					if(dialogResult != JOptionPane.YES_OPTION) return;
				}
				
				//Create a new AdressBook
				abc.newAddressBook();
				changeTitle("Untitled");
				
				//Deal with the JList
				list_persons.removeAll();
				ArrayList<Person> peoArray = abc.getAddressBookInfo();
				list_persons.setListData(peoArray.toArray());
				
				//Enable buttons
				btnAddPerson.setEnabled(true);
				mntmSaveAddressbookAs.setEnabled(true);
				
				isSaved = true;
			}
		});
		
		//Mouse Clicked File->Open
		mnOpenAddressbook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("File->Open");
				try {
					//Use JFileChooser to open a CSV file
					JFileChooser fileChooser = new JFileChooser(new File("").getAbsolutePath());
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
					        "Csv Files", "csv");
					fileChooser.setFileFilter(filter);
					int returnValue = fileChooser.showOpenDialog(null);
					
					//If we are successful dump the Jlist
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						list_persons.removeAll();
						File selectedFile = fileChooser.getSelectedFile();
						
						//Load the AddressBook
						if( !abc.loadAddressBook(selectedFile) ) return;
						changeTitle(abc.getFile().getName());
						ArrayList<Person> peoArray = abc.getAddressBookInfo();
						list_persons.setListData(peoArray.toArray());

						//Enable buttons
						btnAddPerson.setEnabled(true);
						mntmSaveAddressbookAs.setEnabled(true);
						mntmByZip.setEnabled(true);
						mntmByName.setEnabled(true);
						mntmByPhone.setEnabled(true);
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//Clicked File->Save
		mntmSaveAddressbook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(isSaved){
					mntmSaveAddressbook.setEnabled(false);
					return;
				}
				if(abc.createAddressBook(abc.getFile())){
					isSaved = true;
					changeTitle(abc.getFile().getName());
				}
			}
		});
		
		//Clicked File->Save As
		mntmSaveAddressbookAs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				try {
					//Use JFileChooser to open a CSV file
					JFileChooser fileChooser; 
					if(abc.getFile()==null) fileChooser = new JFileChooser(new File("").getAbsolutePath());
					else fileChooser = new JFileChooser(abc.getFile().getAbsolutePath());
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
					        "Csv Files", "csv");
					fileChooser.setSelectedFile(abc.getFile());
					fileChooser.setFileFilter(filter);
					int returnValue = fileChooser.showSaveDialog(null);
					
					//If we are successful dump the Jlist
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						list_persons.removeAll();
						File selectedFile = fileChooser.getSelectedFile();
						abc.saveAddressBook(selectedFile);
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//Clicked File->Exit
		mntmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		
		//Clicked AddPerson
		btnAddPerson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//Custom Prompt for Person information
				NewPerson newPerson = new NewPerson(); 
				int returnValue = JOptionPane.showConfirmDialog(null, newPerson, "Add new Person", 2);
				
				if(returnValue==JOptionPane.OK_OPTION){
					abc.addPerson(newPerson.getArray());
					list_persons.removeAll();
					ArrayList<Person> peoArray = abc.getAddressBookInfo();
					list_persons.setListData(peoArray.toArray());
				}
			}
		});
		
		//JList Selection changes
		list_persons.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list_persons.getSelectedValue()==null){
					btnEditPerson.setEnabled(false);
					btnDeletePerson.setEnabled(false);
				}else {
					btnEditPerson.setEnabled(true);
					btnDeletePerson.setEnabled(true);
				}
			}
		});
		
		//Clicked Delete Person
		btnDeletePerson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				abc.deletePerson((Person)list_persons.getSelectedValue());
				list_persons.removeAll();
				ArrayList<Person> peoArray = abc.getAddressBookInfo();
				list_persons.setListData(peoArray.toArray());
				
				btnEditPerson.setEnabled(false);
				btnDeletePerson.setEnabled(false);
			}
		});
	}
	
	private void changeTitle(String s){
		this.setTitle(s);
	}
}
