package GUI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewPerson extends JPanel {

	private JTextField textField_Fname;
	private JTextField textField_Lname;
	private JTextField textField_Address;
	private JTextField textField_City;
	private JTextField textField_State;
	private JTextField textField_ZIP;
	private JTextField textField_Phone;
	
	public String[] getArray(){
		String[] result = {textField_Fname.getText(), textField_Lname.getText(), textField_Address.getText(), 
				textField_City.getText(), textField_State.getText(), textField_ZIP.getText(), 
				textField_Phone.getText(), };
		return result;
	}
	
	/**
	 * Create the panel.
	 */
	public NewPerson() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel_form = new JPanel();
		this.add(panel_form);
		panel_form.setLayout(new BoxLayout(panel_form, BoxLayout.X_AXIS));
		
		JPanel panel_labels = new JPanel();
		panel_form.add(panel_labels);
		panel_labels.setLayout(new BoxLayout(panel_labels, BoxLayout.Y_AXIS));
		
		JPanel panel_fname = new JPanel();
		panel_labels.add(panel_fname);
		FlowLayout fl_panel_fname = (FlowLayout) panel_fname.getLayout();
		fl_panel_fname.setAlignment(FlowLayout.LEFT);
		
		JLabel lblFname = new JLabel("First Name: ");
		panel_fname.add(lblFname);
		
		JPanel panel_lname = new JPanel();
		panel_labels.add(panel_lname);
		FlowLayout fl_panel_lname = (FlowLayout) panel_lname.getLayout();
		fl_panel_lname.setAlignment(FlowLayout.LEFT);
		
		JLabel lblLastName = new JLabel("Last Name: ");
		panel_lname.add(lblLastName);
		
		JPanel panel_address = new JPanel();
		panel_labels.add(panel_address);
		FlowLayout flowLayout = (FlowLayout) panel_address.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JLabel lblAddress = new JLabel("Address: ");
		panel_address.add(lblAddress);
		
		JPanel panel_city = new JPanel();
		panel_labels.add(panel_city);
		FlowLayout flowLayout_1 = (FlowLayout) panel_city.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		
		JLabel lblCity = new JLabel("City: ");
		panel_city.add(lblCity);
		
		JPanel panel_state = new JPanel();
		panel_labels.add(panel_state);
		FlowLayout flowLayout_2 = (FlowLayout) panel_state.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		
		JLabel lblState = new JLabel("State: ");
		panel_state.add(lblState);
		
		JPanel panel_ZIP = new JPanel();
		panel_labels.add(panel_ZIP);
		FlowLayout fl_panel_ZIP = (FlowLayout) panel_ZIP.getLayout();
		fl_panel_ZIP.setAlignment(FlowLayout.LEFT);
		
		JLabel lblZip = new JLabel("ZIP: ");
		panel_ZIP.add(lblZip);
		
		JPanel panel_Phone = new JPanel();
		panel_labels.add(panel_Phone);
		FlowLayout flowLayout_3 = (FlowLayout) panel_Phone.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		
		JLabel lblPhone = new JLabel("Phone: ");
		panel_Phone.add(lblPhone);
		
		JPanel panel_inputs = new JPanel();
		panel_form.add(panel_inputs);
		panel_inputs.setLayout(new BoxLayout(panel_inputs, BoxLayout.Y_AXIS));
		
		JPanel paneli_Fname = new JPanel();
		panel_inputs.add(paneli_Fname);
		
		textField_Fname = new JTextField();
		paneli_Fname.add(textField_Fname);
		textField_Fname.setColumns(20);
		
		JPanel paneli_Lname = new JPanel();
		panel_inputs.add(paneli_Lname);
		
		textField_Lname = new JTextField();
		paneli_Lname.add(textField_Lname);
		textField_Lname.setColumns(20);
		
		JPanel paneli_Address = new JPanel();
		panel_inputs.add(paneli_Address);
		
		textField_Address = new JTextField();
		paneli_Address.add(textField_Address);
		textField_Address.setColumns(20);
		
		JPanel paneli_City = new JPanel();
		panel_inputs.add(paneli_City);
		
		textField_City = new JTextField();
		paneli_City.add(textField_City);
		textField_City.setColumns(20);
		
		JPanel paneli_State = new JPanel();
		panel_inputs.add(paneli_State);
		
		textField_State = new JTextField();
		paneli_State.add(textField_State);
		textField_State.setColumns(20);
		
		JPanel paneli_ZIP = new JPanel();
		panel_inputs.add(paneli_ZIP);
		
		textField_ZIP = new JTextField();
		paneli_ZIP.add(textField_ZIP);
		textField_ZIP.setColumns(20);
		
		JPanel paneli_Phone = new JPanel();
		panel_inputs.add(paneli_Phone);
		
		textField_Phone = new JTextField();
		paneli_Phone.add(textField_Phone);
		textField_Phone.setColumns(20);
		
	}

}
