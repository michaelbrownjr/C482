    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

    /**
     * @author
     * Michael Brown
     * mbro549@my.wgu.edu
     * Student ID: 000861887
     */

    /** Creates an Product class for the Product object with no inheritance */
    public class Product  {
    
        private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
        private int productID;
        private String name;
        private double price;
        private int stock;
        private int min;
        private int max;

    public Product(int productID, String name, double price, int stock, int min, int max) {

        this.productID = productID;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    /** @return the list of associatedParts
     * */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

        /** Adds a part to the Associated Parts list
         *
         * @param part the associated part that will be added.
         */
    public void setAssociatedParts (Part part) {
        associatedParts.add(part);
    }
/** @return the productId a Product object
 * */
    public int getProductID() {
        return productID;
    }

    /** @param productID is the id of the product to be set
     * */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /** @return the name of the Product object
     * */
    public String getName() {
        return name;
    }

    /**@param name is the name of the Product object to be set
     * */
    public void setName(String name) {
        this.name = name;
    }
    /**@return the price of the Product object
     * */
    public double getPrice() {
        return price;
    }

    /**@param price is the price of the Product object to be set
     * */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * @return the stock amount of the Product object
     * */
    public int getStock() {
        return stock;
    }
    /**
     * @param stock is the inventory ammount of the Product object
     * */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**@return the minimum amount of stock to be held of the Product object
     *
     * */
    public int getMin() {
        return min;
    }

    /** @param min is the minimum amount of stock to be held of the Product object
     * */
    public void setMin(int min) {
        this.min = min;
    }
        /** @return  the maximum amount of stock to be held of the Product object
         *
         * */
    public int getMax() {
        return max;
    }
        /** @param max the maximum amount of stock set to the Product object
         * */
    public void setMax(int max) {
        this.max = max;
    }
        /** Adds a part to the Associated Parts list
         *
         * @param part the assocated part that will be added to the associatedPart list
         */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);

    }
    /** This function deleted an selected associated part from the list of associatedParts to the
     * Product
     * @param selectedAssociatedPart is passed as the associatedPart that will be deleted from
     *                               the list of associatedParts of the Product object
     * @return true if selectedAssociatedPart is found and removed from the list of
     * associatedParts otherwise, return false.
     *
     * I ran into a logical error when first attempting to remove an associatedPart from a Product.
     * Each time I pressed the delete button to call this function, associatedParts would be null
     * . I resolved this by initiating "singlePart" in the deleteAssociatedPartHandler in
     * ModifyProductController class
     * */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        for (Part p : associatedParts) {
            if (p.getId() == selectedAssociatedPart.getId()) {
                associatedParts.remove(p);
                return true;
            }
        }
        return false;
    }
    
    }


