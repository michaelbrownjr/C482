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

/** Creates an InHouse class for the InHouse object which extends Part */
public class InHouse extends Part {
    
    private int machineID;

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /** @return the machineID
     * */
    public int getMachineID() {
        return machineID;
    }

    /** @param machineID the machineID that is to be set.
     *
     * */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

   
    
    
    
}
    
    
    
   