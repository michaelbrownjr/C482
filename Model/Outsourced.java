/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author
 * Michael Brown
 * mbro549@my.wgu.edu
 * Student ID: 000861887
 */

/** Creates an Outsourced class for the Outsourced object that extends Part  */
public class Outsourced extends Part {
    
    private String companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    /** @return companyName
     * */
    public String getCompanyName() {
        return companyName;
    }
    /** @param companyName the companyName to set
     * */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
   
    
}
