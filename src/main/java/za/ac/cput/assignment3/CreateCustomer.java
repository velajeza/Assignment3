/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.assignment3;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
/**
 *
 * @author Velenkosini
 */
public class CreateCustomer {
    
    private Formatter x;
    
    public void openFile(){
        try{
            x = new Formatter("customerOutFile.txt");
        
        }
        catch(Exception e){
            System.out.println("you have an error");
        }
    
    }
    
    public void addRecords(){
        x.format("C150", "Luke", "Atmyass", "Bellville", "1981-01-27", 1520.50, false);
        x.format("C130", "Stu", "Padassol", "Sea Point", "1987-05-18", 645.25, true);
        x.format("C100", "Mike", "Rohsopht", "Bellville", "1993-01-24", 975.10, true);
        x.format("C300", "Ivana.B", "Withew", "Langa", "1998-07-16", 1190.50, false);
        x.format("C250", "Eileen", "Sideways", "Nyanga", "1999-11-27", 190.85, true);
        x.format("C260", "Ima", "Stewpidas", "Atlantis", "2001-01-27", 1890.70, true);
    }
    
    public void closeFile(){
        x.close();
    }
            
    
}
