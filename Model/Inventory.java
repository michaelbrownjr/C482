/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author
 * Michael Brown
 * mbro549@my.wgu.edu
 * Student ID: 000861887
 */

/** Creates an Inventory class for all items in the inventory management system with different functions for
 * manipulating  the inventory */
public class Inventory {

    /**Initializes two objects of type ObservableList*/
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /** This method adds a part to the array list of parts in the Inventory
     * The expected item being passed through is of type Part
     * @param part Part to be added
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }
    /** This method adds a product to the array list of product in the Inventory.
     * The expected item being passed through is of type Product.
     * @param product Part to be added.
     */
    public static void addProduct(Product product) {
        allProducts.add(product);
    }
    /** This method does a search to look up a specific part based on the ID of the part.
     * @param partId Part to be searched.
     * @return p if the part is found, if not, the method returns null.
     */
    public static Part lookupPart(int partId) {

        for (int i = 0, partsSize = allParts.size(); i < partsSize; i++) {
            Part p = allParts.get(i);
            if (p.getId() == partId)
                return p;
        }
        return null;
    }

    /** This method does a search to look up a specific part based on the ID of the part.
     * @param productId Product to be searched.
     * @return p if the product is found, if not, the method returns null.
     */
    public static Product lookupProduct(int productId) {

        for (int i = 0, productSize = allProducts.size(); i < productSize; i++) {
            Product p = allProducts.get(i);
            if (p.getProductID() == productId)
                return p;
        }
        return null;

    }
    /** This method does a search to look up a specific part based on the name of the part searched.
     * @param partName Part name to be searched.
     * @return Returns the part if the part's name is found.
     */
    public static ObservableList<Part> lookUpPart(String partName) {
        ObservableList<Part> parts = FXCollections.observableArrayList();
            for (Part part : getAllParts()) {
                if (part.getName().contains(partName)) {
                    parts.add(part);
                }
            }
            return parts;
    }
    /** This method does a search to look up a specific product based on the name of the product searched.
     * @param productName Product name to be searched.
     * @return Returns the product if the part's name is found.
     */
        public static ObservableList<Product> lookupProduct(String productName) {

        ObservableList<Product> products = FXCollections.observableArrayList();

            for (Product product : getAllProducts()){
                if(product.getName().contains(productName)){
                    products.add(product);
                }
            }
            return products;
        }

    /** This updates the Part list with the modified part.
     * @param index Passes in index of the selected part.
     * @param selectedPart Passes in the index of the selected part to be modified and the Part object.
     * */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);

    }
    /** This updates the Part list with the modified Product.
     * @param index Passes in the index of the selected part
     * @param newProduct Passes in the index of the selected part to be modified and the Part object.
     * */
    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }
    /**This deletes a selected Part in the list.
     * @param selectedPart Passes in the part that is to be deleted.
     * @return Returns true when part is deleted. Returns falls if the part wasn't found. */
    public static boolean deletePart(Part selectedPart) {
        for (Part p : allParts) {
            if (p.getId() == selectedPart.getId()) {
                allParts.remove(p);
                return true;
            }
        }
        return false;
    }
    /**This deletes a selected Product in the list.
     * @param selectedProduct Passes in the product that is to be deleted.
     *@return Returns true when product is deleted. Returns false if the product wasn't found. */
    public static boolean deleteProduct(Product selectedProduct) {
        for (Product p : allProducts) {
            if (p.getProductID() == selectedProduct.getProductID()) {
                allProducts.remove(p);
                return true;
            }
        }
        return false;
    }
    /**This gets the entire list of parts.
     *@return Returns all of the parts in the list. */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    /**This gets the entire list of products.
     *@return Returns all of the products in the list. */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    
    

    
}

