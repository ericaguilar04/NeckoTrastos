package nt.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import nt.makery.address.model.EmpVO;
import nt.makery.address.util.DateUtil;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class PersonEditDialogController {

    @FXML
    private TextField empnoField;
    @FXML
    private TextField enameField;

    private Stage dialogStage;
    private EmpVO empleat;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setPerson(EmpVO empleat) {
        this.empleat = empleat;

        empnoField.setText(String.valueOf(empleat.getEmpno()));
        enameField.setText(empleat.getEname());
        
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
   /* @FXML
    private void handleOk() {
        if (isInputValid()) {
            empleat.setEmpno(String.valueOf(empnoField.getText()));
            empleat.setEname(enameField.getText());


            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    //@FXML
    /*private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    /*private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n"; 
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n"; 
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n"; 
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
        	Dialogs.create()
		        .title("Invalid Fields")
		        .masthead("Please correct invalid fields")
		        .message(errorMessage)
		        .showError();
            return false;
        }
    }*/
}