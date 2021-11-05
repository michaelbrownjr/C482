package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import static Model.Inventory.*;
import static Model.Inventory.getAllParts;

/**
 *
 * @author
 * Michael Brown
 * mbro549@my.wgu.edu
 * Student ID: 000861887
 */

/** Creates an AddProductController class for the AddPart XML Scene */
public class AddProductController implements Initializable{

    Product newProduct;
    ObservableList<Part> assocPartList = FXCollections.observableArrayList();
    @FXML
    private TextField IDAddProductText;

    @FXML
    private TextField NameAddProductText;

    @FXML
    private TextField InventoryAddProductText;

    @FXML
    private TextField AddPriceTextField;

    @FXML
    private TextField MaxAddProductText;

    @FXML
    private TextField MinAddProductText;

    @FXML
    private TextField SearchPartTextField;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> PartID;

    @FXML
    private TableColumn<Part, String> PartName;

    @FXML
    private TableColumn<Part, Integer> PartInventoryLevel;

    @FXML
    private TableColumn<Part, Double> PriceCostPerUnit;

    @FXML
    private Button AddProduct;

    @FXML
    private TableView<Part> associatedPartTable;

    @FXML
    private TableColumn<Part, Integer> associatedPartID;

    @FXML
    private TableColumn<Part, String> associatedPartName;

    @FXML
    private TableColumn<Part, Integer> associatedPartInventory;

    @FXML
    private TableColumn<Part, Double> associatedPartPrice;

    @FXML
    private Button DeleteProduct;

    @FXML
    private Button SaveProduct;

    @FXML
    private Button CancelButton;

    @FXML
    private Button SearchProduct;
    /** Empty Add Product Controller added automatically from scene builder
     * */
    public AddProductController() {
    }

    /**Initializes the Add Product Scene with the Column, Fields, and Table View*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        updatePartTable();
        
        PartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PriceCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        newProduct = new Product(0, null, 0.0, 0, 0, 0);
        assocPartList = newProduct.getAssociatedParts();
        associatedPartTable.setItems(newProduct.getAssociatedParts());
        
        associatedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
       
        
    }

    /** @param event is not used in this function
     * */
    @FXML
    void AddPriceTextField(ActionEvent event) {

    }

    /** This function initializes the selected Part in the parts table to be added.
     *  When this function is called upon the selected part will be added as an AssociatedPart.
     *  Once the associated part is added, the associated part list will refresh with the new added associated part.
     * @param event is not used in this function.
     * @throws IOException if an I/O error occurs.
     * */
    @FXML
    public void AddPartHandler(ActionEvent event) throws IOException {
  
        Part singlePart = partsTable.getSelectionModel().getSelectedItem();
        newProduct.addAssociatedPart(singlePart);
        assocPartList = newProduct.getAssociatedParts();

       
    }
    /** This function cancels any changes that are done in the Add Product Scene when the "Cancel" button is pressed.
     * @param event runs when Cancel button is pressed. Focus will leave the AddProduct Scene
     *              and will return to the MainScreen Scene.
     * @throws IOException if an I/O error occurs
     * */
    @FXML
    void CancelButton(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all text field values, do you want to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
        
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        }
    }

    /** This function deletes a part from the associated parts list of the product after confirming in the alert window.
     * @param event is not used on this function
     * */
    @FXML
    public void DeletePartHandler(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the entire Part, do you want to continue?");
        alert.setTitle("Confirmation of Deletion");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {

            Part singlePart = associatedPartTable.getSelectionModel().getSelectedItem();
            if ( newProduct.deleteAssociatedPart(singlePart)){
                newProduct.getAssociatedParts().remove(associatedPartTable.getSelectionModel().getSelectedItem());
            }
            assocPartList = newProduct.getAssociatedParts();

        }
        
    }
    /**@param event*/
    @FXML
    void IDAddProductText(ActionEvent event) {

    }
    /**@param event*/
    @FXML
    void InventoryAddProductText(ActionEvent event) {

    }
    /**@param event*/
    @FXML
    void MaxAddProductText(ActionEvent event) {

    }
    /**@param event*/
    @FXML
    void MinAddProductText(ActionEvent event) {

    }
    /**@param event*/
    @FXML
    void NameAddProductText(ActionEvent event) {

    }
    /**This function checks to see if a valid value is in all fields that have been inserted.
     * If they values have been inserted correctly, values are all saved and a new Product will be added to the Inventory.
     * The AddProduct Scene is closed upon completion.
     * @param event is used to close the AddProduct Scene and open the MainScreen Scene
     * @throws IOException if an I/O error occurs*/
    @FXML
    public void SaveProductHandler(ActionEvent event) throws IOException {

        int ID = 0;
        for(Product product : Inventory.getAllProducts()) {

        if(product.getProductID() > ID)
        ID = product.getProductID();

        }

            try{
                if (!(Integer.class.isInstance(Integer.parseInt(InventoryAddProductText.getText())))){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory value you chose needs to be a number.");
                    alert.showAndWait();
                } else if (!(Double.class.isInstance(Double.parseDouble(AddPriceTextField.getText())))){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Price value you chose needs to be a number.");
                    alert.showAndWait();
                }
                else if (Integer.parseInt(MinAddProductText.getText()) > Integer.parseInt(MaxAddProductText.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Min value cannot be greater than Max value.");
                    alert.showAndWait();
                } else if (Integer.parseInt(InventoryAddProductText.getText()) > Integer.parseInt(MaxAddProductText.getText()) || Integer.parseInt(InventoryAddProductText.getText()) < Integer.parseInt(MinAddProductText.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory amount must be between minimum and maximum values.");
                    alert.showAndWait();
                } else {
                    String name = NameAddProductText.getText();
                    int inventory = Integer.parseInt(InventoryAddProductText.getText());
                    double priceCost = Double.parseDouble(AddPriceTextField.getText());
                    int max = Integer.parseInt(MaxAddProductText.getText());
                    int min = Integer.parseInt(MinAddProductText.getText());
                    IDAddProductText.setText(String.valueOf(++ID));

                    newProduct.setProductID(ID);
                    newProduct.setName(name);
                    newProduct.setPrice(priceCost);
                    newProduct.setMax(max);
                    newProduct.setMin(min);
                    newProduct.setStock(inventory);


                    Inventory.addProduct(newProduct);


                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
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

    /**This function handles the "Enter" key event when pressed while searhing for a part.
     * If "Enter" is pressed, this function will call the searchPartHandler.
     * @param event "Enter" key event that's passed
     * */
    public void handleProductKeyEvent(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)) {
            searchPartHandler();
        }
    }

    /**This function will search the list of parts for the Name or Part Id that is entered in the Text Field.
     * An alert message will be provided for
     * the following: Nothing is in the search
     * field, no such part name is in the
     * inventory, no such part ID is in the
     * inventory, or if there are no parts in
     * the parts Inventory.
     * After each search, the Search Text Field will be cleared.
     * */
    @FXML
    public void searchPartHandler() {


        String searchedPart = SearchPartTextField.getText();


        if(searchedPart.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Part Search Warning");
            alert.setHeaderText("There were no parts found!");
            alert.setContentText("You did not enter a part ID or name to search for!");
            alert.showAndWait();
            partsTable.setItems(getAllParts());
        } else {
            boolean found = false;
            try {
                Part foundParts = lookupPart(Integer.parseInt(searchedPart));
                if (foundParts != null) {
                    ObservableList<Part> parts = FXCollections.observableArrayList();
                    parts.add(foundParts);
                    partsTable.setItems(parts);
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Part Search Warning");
                    alert.setHeaderText("There were no parts found!");
                    alert.setContentText("The search term entered does not match any part ID!");
                    alert.showAndWait();
                    updatePartTable();
                }
            } catch (NumberFormatException e) {
                ObservableList<Part> allParts = getAllParts();
                if(allParts.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Part Search Warning");
                    alert.setHeaderText("There were no parts found!");
                    alert.setContentText("There are no parts in the parts list to search\nAdd parts first.");
                    alert.showAndWait();
                    updatePartTable();

                } else {
                    for (int i = 0, allPartsSize = allParts.size(); i < allPartsSize; i++) {
                        Part p = allParts.get(i);
                        if (p.getName().equals(searchedPart)) {
                            found = true;
                            ObservableList parts = lookUpPart(searchedPart);
                            partsTable.setItems(parts);
                        }
                    }   if (found == false) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Part Search Warning");
                        alert.setHeaderText("There were no parts found!");
                        alert.setContentText("The search term entered does not match any part name!");
                        alert.showAndWait();
                        updatePartTable();
                    }
                }
            }
        }

        SearchPartTextField.setText("");

    }

    /** This function updates the Part table with the entire list of parts in view
     * */
    public void updatePartTable() {
        partsTable.setItems(Inventory.getAllParts());
        
    }
}
