package Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import java.io.IOException;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author
 * Michael Brown
 * mbro549@my.wgu.edu
 * Student ID: 000861887
 */

/** Creates an AddPartController class for the AddPart XML Scene */
public class AddPartController {

    InHouse addInHousePart;
    Outsourced addOutsourcedPart;

    @FXML
    private RadioButton InhouseRadioButton;

    @FXML
    private RadioButton OutsourcedRadioButton;

    @FXML
    private TextField NameAddPartText;

    @FXML
    private TextField InventoryAddPartText;

    @FXML
    private TextField PriceCostAddPartText;

    @FXML
    private TextField IDAddPartText;

    @FXML
    private TextField MaxPartText;

    @FXML
    private TextField MinPartText;

    @FXML
    private TextField MachineIDAddPartText;
    
    @FXML
    private TextField CompanyNameAddPartText;

    @FXML
    private Button CancelButton;

    @FXML
    private Button SaveButton;

    private boolean isOutsourced = true;
        
    @FXML
    private Label MACIDLbl;

    /**@param event is not used*/
    @FXML
    void IDAddPartText(ActionEvent event) {

       
    }
    /**@param event is not used*/
    @FXML
    void InhouseHandler(ActionEvent event) {

        isOutsourced = false;
        MACIDLbl.setText("Machine ID");
        
    }
    /**@param event is not used*/
    @FXML
    void InventoryAddPartText(ActionEvent event) {

    }
    /**@param event is not used*/
    @FXML
    void MachineIDAddPartText(ActionEvent event) {

    }
    /**@param event is not used*/
    @FXML
    void MaxAddPartText(ActionEvent event) {

    }
    /**@param event is not used*/
    @FXML
    void MinAddPartText(ActionEvent event) {

    }
    /**@param event is not used*/
    @FXML
    void NameAddPartText(ActionEvent event) {

    }
    /**@param event is not used*/
    @FXML
    void OutsourcedHandler(ActionEvent event) {
        
        isOutsourced = true;
        MACIDLbl.setText("Company Name");
    }
    /**@param event is not used*/
    @FXML
    void PriceCostAddPartText(ActionEvent event) {

    }

    /** This function cancels any changes that are done in the Add Part Scene when the "Cancel"
     * button is pressed.
     * @param event is used to close the AddPart Scene and open the MainScreen Scene
     * @throws IOException if an I/O error occurs.
     * */
    @FXML
    public void cancelHandler(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all text field " +
                "values, do you want to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
        
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();

        }
    }
    /**This function gathers checks to see if a valid value in all fields have been inserted.
     * If they values have been inserted correctly, values are all saved and depending on if the
     * InHouse or Outsourced Radio button is clicked a new
     * InHousePart or OutsourcedPart will be
     * added to the Inventory.
     * The AddPart Scene is closed upon completion.
     *
     * I ran into a logical error where the Min stock value was swapping with the Max stock value
     * . I would only see it when I go to modify the Part. I was able to fix it by defining the
     * Min stock with the text set by the Min field and the Max stock with the text from the Max
     * field. Initially I had them swapped on accident.
     * @param event is used to close the AddPart Scene and open the MainScreen Scene
     * @throws IOException if an I/O error occurs.
     * */
    @FXML
    public void saveHandler(ActionEvent event) throws IOException {

        int ID = 0;
        ObservableList<Part> allParts = Inventory.getAllParts();
        for (int i = 0, allPartsSize = allParts.size(); i < allPartsSize; i++) {
            Part part = allParts.get(i);

            if (part.getId() > ID)

                ID = part.getId();

        }

          
            try{
                if (!(Integer.class.isInstance(Integer.parseInt(InventoryAddPartText.getText())))){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory value you chose needs to be a number.");
                    alert.showAndWait();
                } else if (!(Double.class.isInstance(Double.parseDouble(PriceCostAddPartText.getText())))){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Price value you chose needs to be a number.");
                    alert.showAndWait();
                }
                else if (Integer.parseInt(MinPartText.getText()) > Integer.parseInt(MaxPartText.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Min value cannot be greater than Max value.");
                    alert.showAndWait();
                } else if (Integer.parseInt(InventoryAddPartText.getText()) > Integer.parseInt(MaxPartText.getText()) || Integer.parseInt(InventoryAddPartText.getText()) < Integer.parseInt(MinPartText.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory amount must be between minimum and maximum values.");
                    alert.showAndWait();
                }else {
                    IDAddPartText.setText(String.valueOf(++ID));
                    int id = Integer.parseInt(IDAddPartText.getText());
                    String name = NameAddPartText.getText();
                    int inventory = Integer.parseInt(InventoryAddPartText.getText());
                    double priceCost = Double.parseDouble(PriceCostAddPartText.getText());
                    int max = Integer.parseInt(MaxPartText.getText());
                    int min = Integer.parseInt(MinPartText.getText());
                
                if (InhouseRadioButton.isSelected()) {
                    int machineID = Integer.parseInt(MachineIDAddPartText.getText());
                    InHouse addInHousePart = new InHouse(id, name, priceCost, inventory, min, max
                            , machineID);

                    Inventory.addPart(addInHousePart);
            }
                if (OutsourcedRadioButton.isSelected()) {
                    String companyName = MachineIDAddPartText.getText();
                    Outsourced addOutsourcedPart = new Outsourced(id, name, priceCost, inventory,
                            min, max, companyName);

                    Inventory.addPart(addOutsourcedPart);
            } 
           
                
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
                }
 
        }
        
        catch(NumberFormatException e){
       
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please enter a valid value for each text field.");
            alert.showAndWait();
            
        }    
    }
}


