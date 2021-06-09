/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.assignment3;

import java.io.FileInputStream;
import java.io.ObjectInputStream;


public class RunStakeholder {
    
    public static void main(String[] args){
        
        CreateSupplier g = new CreateSupplier();
        g.openFile();
        g.addRecords();
        g.closeFile();
        
        CreateCustomer c = new CreateCustomer();
        c.openFile();
        c.addRecords();
        c.closeFile();
        
       
       
        try {
            FileInputStream fis = new FileInputStream("stakeholder.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Stakeholder stakeholder = (Stakeholder) ois.readObject();
            //Customer c = (Customer) ois.readObject();
           // Supplier supplier = (Supplier) ois.readObject();
            ois.close();
            fis.close();
            System.out.println(stakeholder);
          //  System.out.println(c);
           // System.out.println(supplier);
            } catch(Exception e) {
                e.printStackTrace();
            }
        
        
}
    
    
    
  
 
}


