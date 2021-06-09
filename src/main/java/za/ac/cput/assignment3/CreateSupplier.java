/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.assignment3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Formatter;

/**
 *
 * @author Velenkosini
 */
public class CreateSupplier {
    
    private Formatter x;
    
    public void openFile(){
        try{
            x = new Formatter("supplierOutFile.txt");
        
        }
        catch(Exception e){
            System.out.println("you have an error");
        }
    
    }
    
    public void addRecords(){
        x.format("S270", "Grand Theft Auto", "Toyota", "Mid-size sedan");
        x.format("S400", "Prime Motors", "Lexus", "Luxury sedan");
        x.format("S300", "We got Cars", "Toyota", "10-seater minibus");
        x.format("S350", "Auto Delight", "BMW", "Luxury SUV");
        x.format("S290", "MotorMania", "Hyundai", "compact budget");
        
    }
    
    public void closeFile(){
        x.close();
    }
    
}
