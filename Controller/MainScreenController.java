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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import static Model.Inventory.*;
/**
 *
 * @author
 * Michael Brown
 * mbro549@my.wgu.edu
 * Student ID: 000861887
 */

/** Creates an MainScreenController class for MainScreen FXML Scene */
public class MainScreenController implements Initializable {

/**Initializing the XML fields*/
    @FXML
    private TextField SearchPartText;

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
    private Button AddPart;

    @FXML
    private Button ModifyPart;

    @FXML
    private TextField SearchProductText;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, Integer> ProductID;

    @FXML
    private TableColumn<Product, String> ProductName;

    @FXML
    private TableColumn<Product, Integer> ProductInventoryLevel;

    @FXML
    private TableColumn<Product, Double> PricePerUnit;

    @FXML
    private Button AddProduct;

    @FXML
    private Button ModifyProdcut;

ObservableList parts;
ObservableList products;
Part foundParts;
Product foundProducts;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
        productTable.setItems(getAllProducts());
              
        ProductID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        ProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
     
        partsTable.setItems(getAllParts());
        
        PartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PriceCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
       
    }

    public void handlePartKeyEvent(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)) {
            searchPartEvent();
        }
    }

    @FXML
    void searchPartEvent() {
        String searchedPart = SearchPartText.getText();


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
                foundParts = lookupPart(Integer.parseInt(searchedPart));
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
                                parts = lookUpPart(searchedPart);
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



        SearchPartText.setText("");

    }

    public void handleProductKeyEvent(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)) {
            searchProductEvent();
        }
    }

    @FXML
    void searchProductEvent() {

        String searchedProduct = SearchProductText.getText();

        if (searchedProduct.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Product Search Warning");
            alert.setHeaderText("There were no products found!");
            alert.setContentText("You did not enter a Product ID or Name to search for!");
            alert.showAndWait();
            productTable.setItems(getAllProducts());
        } else {
            boolean found = false;
            try {
                    foundProducts = lookupProduct(Integer.parseInt(searchedProduct));
                if (foundProducts != null) {
                    ObservableList<Product> products = FXCollections.observableArrayList();
                    products.add(foundProducts);
                    productTable.setItems(products);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Part Search Warning");
                    alert.setHeaderText("There were no Products found!");
                    alert.setContentText("The search term entered does not match any Product ID!");
                    alert.showAndWait();
                    productTable.setItems(getAllProducts());
                }
            } catch (NumberFormatException e) {
                ObservableList<Product> allProducts = getAllProducts();
                if (allProducts.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Part Search Warning");
                    alert.setHeaderText("There were no Products found!");
                    alert.setContentText("There are no Products in the list of Products to search\nAdd Products first.");
                    alert.showAndWait();
                    productTable.setItems(getAllProducts());

                } else {
                    for (int i = 0, allProductsSize = allProducts.size(); i < allProductsSize; i++) {
                        Product p = allProducts.get(i);
                        if (p.getName().equals(searchedProduct)) {
                            found = true;
                            products = lookupProduct(searchedProduct);
                            productTable.setItems(products);
                        }
                    }
                    if (found == false) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Part Search Warning");
                        alert.setHeaderText("There were no Products found!");
                        alert.setContentText("The search term entered does not match any Product name!");
                        alert.showAndWait();
                        productTable.setItems(getAllProducts());
                    }

                }
            }
        }
        SearchProductText.setText("");
    }

    @FXML
    void addPartHandler() throws IOException {



        Stage stage = (Stage) AddPart.getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/View/AddPart.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
        

    }

    @FXML
    void addProductHandler() throws IOException {
        
        Stage stage = (Stage)AddProduct.getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/View/AddProduct.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();

    }

    @FXML
    void deletePartHandler() {
         
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the entire Part, do you want to continue?");
        alert.setTitle("Confirmation of Deletion");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
        

            Part singlePart;
            singlePart = partsTable.getSelectionModel().getSelectedItem();
            deletePart(singlePart);

        }
    }

    @FXML
    void deleteProductHandler() {


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the entire Product, do you want to continue?");
        alert.setTitle("Confirmation of Deletion");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {

            try {
                Product product = productTable.getSelectionModel().getSelectedItem();
                if(product.getAssociatedParts().isEmpty()) {
                    deleteProduct(product);
                }
                else {
                    alert = new Alert(Alert.AlertType.ERROR, "You cannot delete a product that has a part associated with it.\n Please remove parts before deleting product.");
                    alert.showAndWait();
                }
            }
            catch (UnsupportedOperationException e){
                alert.setTitle("Product Deletion Error");
                alert.setHeaderText("The Product was NOT deleted!");
                alert.setContentText("There was no product selected");
                alert.showAndWait();
            }

        }
    }

    @FXML
    void exitHandler() {
        
        System.exit(0);

    }

    @FXML
    void modifyPartHandler(ActionEvent event) throws IOException {

        
        Stage stage; 
        Parent root;       
        stage=(Stage) ModifyPart.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader=new FXMLLoader(getClass().getResource(
                "/View/ModifyPart.fxml"));
        
        root =loader.load();
        ModifyPartController controller = loader.getController();
        Part part=partsTable.getSelectionModel().getSelectedItem();
        int index = partsTable.getSelectionModel().getSelectedIndex();
        
        if(part != null) {
            controller.setPart(part, index);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        
       
        
        
    }

    @FXML
    void modifyProductHandler(ActionEvent event) throws IOException {
        
        Stage stage; 
        Parent root;       
        stage=(Stage) ModifyProdcut.getScene().getWindow();
        //load up OTHER FXML document
        FXMLLoader loader=new FXMLLoader(getClass().getResource(
                "/View/ModifyProduct.fxml"));
        
        root =loader.load();
        ModifyProductController controller = loader.getController();
        Product product=productTable.getSelectionModel().getSelectedItem();
        int index = productTable.getSelectionModel().getSelectedIndex();
        
        if(product != null) {
            controller.setProduct(product, index);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}
