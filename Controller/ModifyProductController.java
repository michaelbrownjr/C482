package Controller;

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
import javafx.stage.Stage;

import static Model.Inventory.*;
import static Model.Inventory.updateProduct;
/**
 *
 * @author
 * Michael Brown
 * mbro549@my.wgu.edu
 * Student ID: 000861887
 */

/** Creates an ModifyProductController class for the ModifyProduct XML Scene */

public class ModifyProductController implements Initializable{

    Product newProduct;
    Product selectedProduct;
    int selectedIndex;
    
    @FXML
    private TextField AddProductIDTextField;

    @FXML
    private TextField AddNameIDTextField;

    @FXML
    private TextField AddInventoryTextField;

    @FXML
    private TextField AddPriceTextField;

    @FXML
    private TextField AddMaxTextField;

    @FXML
    private TextField AddMinTextField;

    @FXML
    private TextField SaveProductTextField;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> AddPartID;

    @FXML
    private TableColumn<Part, String> AddPartName;

    @FXML
    private TableColumn<Part, Integer> AddInventoryLevel;

    @FXML
    private TableColumn<Part, Double> AddPricePerUnit;

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
    private Button SearchPart;

    /**Initializes the Add Product Scene with the Column, Fields, and Table View*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        updatePartTable();
        
        AddPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        AddPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AddPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        newProduct = new Product(0, null, 0.0, 0, 0, 0);
        associatedPartTable.setItems(newProduct.getAssociatedParts());

        associatedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
             
    }
    /** @param event is not used in this function
     * */
    @FXML
    void AddInventoryTextField(ActionEvent event) {

    }
    /** @param event is not used in this function
     * */
    @FXML
    void AddMaxTextField(ActionEvent event) {

    }
    /** @param event is not used in this function
     * */
    @FXML
    void AddMinTextField(ActionEvent event) {

    }
    /** @param event is not used in this function
     * */
    @FXML
    void AddNameIDTextField(ActionEvent event) {

    }
    /** @param event is not used in this function
     * */
    @FXML
    void AddPriceTextField(ActionEvent event) {

    }

    @FXML
    /**This function runs when a selected Part from the parts list is being added to the Products
     * object
     * associatedParts list.
     * @param event nothing happens with this parameter.**/
    public void AddProductHandler(ActionEvent event) {

        Part singlePart = partsTable.getSelectionModel().getSelectedItem();
        selectedProduct.setAssociatedParts(singlePart);
    }

    @FXML
    /** @param event is not used in this function
     * */
    void AddProductIDTextField(ActionEvent event) {

    }

    /** This function cancels any changes that are done in the Add Product Scene when the "Cancel" button is pressed.
     * @param event runs when Cancel button is pressed. Focus will leave the ModifyProduct Scene
     *              and will return to the MainScreen Scene.
     * @throws IOException if an I/O error occurs.
     * */
    @FXML
    public void CancelButton(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all text field values, do you want to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
          
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
        }
    }

    @FXML
    /**I originally ran into a logical error than turned into a runtime error everytime I tried to save a modified
     * Product after deleting a associated part from the Associated Part list. After realizing I had to call the
     * "deleteAssociatedPart" function from the Product class, that resolved my logical and runtime error.
     *
     * Logical Error: Associated Parts wasn't being deleted from the Associated Parts list in the
     * Product
     *
     * Runtime Error: Code would error out because I was removing a product from a list instead of an associatedPart
     * I initially had "deleteProduct" in the Inventory class remove Product from an allParts list.
     * */
    public void DeleteAssociatedPartHandler(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the entire Part, do you want to continue?");
        alert.setTitle("Confirmation of Deletion");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            Part singlePart = associatedPartTable.getSelectionModel().getSelectedItem();
            if ( selectedProduct.deleteAssociatedPart(singlePart)){
                selectedProduct.getAssociatedParts().remove(associatedPartTable.getSelectionModel().getSelectedItem());
            }

        }
        
    }
    /**This function checks to see if a valid value is in all fields that have been inserted.
     * If they values have been inserted correctly, values are all saved and a new Product will be added to the Inventory.
     * The AddProduct Scene is closed upon completion.
     *
     * I ran into a runtime error when an invalid value was entered into a field. Specifically
     * in the InventoryTextField. I resolved this issue by first checking if the value in each
     * TextField was valid. If it were not, I'd push add an Exception handling with an alert to
     * the user to inform them to enter
     * a correct value. Once doing this, I was able to cover all basis of error.
     *
     * A future feature that can be added is that once the Product is saved. The AssociatedParts
     * prices could be added up to show how much the product costs in parts.
     * @param event runs when the Save button is clicked. The ModifyProduct Scene will end and go
     *             back to the MainScreen Scene.
     * @throws IOException if an I/O error occurs.*/
    @FXML
    public void SaveProductHandler(ActionEvent event) throws IOException {
       try {
           if (!(Integer.class.isInstance(Integer.parseInt(AddInventoryTextField.getText())))){
               Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory value you chose needs to be a number.");
               alert.showAndWait();
           } else if (!(Double.class.isInstance(Double.parseDouble(AddPriceTextField.getText())))){
               Alert alert = new Alert(Alert.AlertType.ERROR, "Price value you chose needs to be a number.");
               alert.showAndWait();
           }
           else if (Integer.parseInt(AddMinTextField.getText()) > Integer.parseInt(AddMaxTextField.getText())) {
               Alert alert = new Alert(Alert.AlertType.ERROR, "Min value cannot be greater than Max value.");
               alert.showAndWait();
           } else if (Integer.parseInt(AddInventoryTextField.getText()) > Integer.parseInt(AddMaxTextField.getText()) || Integer.parseInt(AddInventoryTextField.getText()) < Integer.parseInt(AddMinTextField.getText())) {
               Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory amount must be between minimum and maximum values.");
               alert.showAndWait();
           } else {
               String name = AddNameIDTextField.getText();
               int inventory = Integer.parseInt(AddInventoryTextField.getText());
               double priceCost = Double.parseDouble(AddPriceTextField.getText());
               int max = Integer.parseInt(AddMaxTextField.getText());
               int min = Integer.parseInt(AddMinTextField.getText());


               selectedProduct.setName(name);
               selectedProduct.setPrice(priceCost);
               selectedProduct.setMax(max);
               selectedProduct.setMin(min);
               selectedProduct.setStock(inventory);



               updateProduct(selectedIndex, selectedProduct);



               Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
               Object scene = FXMLLoader.load(getClass().getResource("/View/MainScreen.fxml"));
               stage.setScene(new Scene((Parent) scene));
               stage.show();
           }
       }
       catch (NumberFormatException e){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Warning Dialog");
           alert.setContentText("Please enter a valid value for each text field.");
           alert.showAndWait();
       }
    }

    @FXML
    public void SaveProductTextField(ActionEvent event) {

    }

    /**This function will search the list of parts for the Name or Part Id that is entered in the Text Field.
     * An alert message will be provided for the following: Nothing is in the search field, no
     * such part name is in the inventory, no such part ID is in the
     * inventory, or if there are no parts in the parts Inventory. After each search, the Search
     * Text Field will be cleared.
     *
     * */
    @FXML
    public void searchPartEvent() {

        String searchedPart = SaveProductTextField.getText();


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
                    partsTable.setItems(getAllParts());
                }
            } catch (NumberFormatException e) {
                ObservableList<Part> allParts = getAllParts();
                if(allParts.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Part Search Warning");
                    alert.setHeaderText("There were no parts found!");
                    alert.setContentText("There are no parts in the parts list to search\nAdd parts first.");
                    alert.showAndWait();
                    partsTable.setItems(getAllParts());

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
                        partsTable.setItems(getAllParts());
                    }
                }
            }
        }

        SaveProductTextField.setText("");
    }

    /** This function is called to set each property that has been modified in the
     * selectedProduct and is called when the modifications were saved in the SaveProductHandler
     *
     * I ran into a logical error when setProduct was called. Despite the function setting all of
     * the properties onto the selectedProduct, the modifications weren't being shown in the
     * Product pane on the main screen. I ran a debugger on this function and found that I needed
     * to call the "updateProduct" function which updates the product in the Product pane on the
     * main screen.
     *
     * I also initially ran into a runtime error anytime a part, not in the parts list, was
     * ed. The searched part would return a null value because that part wasn't in the list of
     * parts. Therefore, I created a condition that shows an Alert when a part that is not in the list has been searched.
     *
     * A future feature request would be to have Product property values derived from a database
     * that automatically updates the properties when products have price changes, name changes,
     * and more.
     * @param product the product object that will be modified
     * @param index the index of the object to pass through any changes
     * */
    public void setProduct(Product product, int index) {
        selectedProduct = product;
        selectedIndex = index;
        
    if (product instanceof Product) {
            Product newProduct = selectedProduct;

            this.AddNameIDTextField.setText(newProduct.getName());
            this.AddInventoryTextField.setText((Integer.toString(newProduct.getStock())));
            this.AddPriceTextField.setText((Double.toString(newProduct.getPrice())));
            this.AddMaxTextField.setText((Integer.toString(newProduct.getMax())));
            this.AddMinTextField.setText((Integer.toString(newProduct.getMin())));
            associatedPartTable.setItems(newProduct.getAssociatedParts());
            updateProduct(selectedIndex, newProduct);

        
        }    
    }
    /** This function updates the Part table with the entire list of parts in view
     * */
    public void updatePartTable() {
        partsTable.setItems(getAllParts());
        
    }

}

