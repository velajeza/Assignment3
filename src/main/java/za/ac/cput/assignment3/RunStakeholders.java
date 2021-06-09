/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.assignment3;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 *
 * @author Velenkosini Prince Jeza 218191669
 */
public class RunStakeholders {
    
    
    
    ObjectInputStream input;
    BufferedWriter writingToFile;
    Customer customer;
    Supplier supplier;
    Object obj;
    
    ArrayList<Customer> arrayListCustomer = new ArrayList();
    ArrayList<Supplier> arrayListSupplier = new ArrayList();
    
    int canRent = 0;
    int canNotRent = 0;
    
    public void openSerializedFile(){     
        try{
            input = new ObjectInputStream( new FileInputStream( "stakeholder.ser" )); 
            System.out.println("Succesfully Opened stakeholder.ser");            
        }
        catch (IOException ioe){
            System.out.println("error opening stakeholder.ser: " + ioe.getMessage());
            System.exit(1);
        }
    
    }
    public void readingFromSerializedFile(){
        try{
            openSerializedFile();
            
            System.out.println("Reading From Serialized File");   
           
            
            while(true){
                try{
                    obj = (Object) input.readObject();  
                    
                    if(obj instanceof Customer){
                        customer = (Customer) obj;
                        arrayListCustomer.add(customer);
                    }else if(obj instanceof Supplier){
                        supplier = (Supplier) obj;
                        arrayListSupplier.add(supplier);     
                    }
                }catch(ClassNotFoundException e) {
                    System.out.println("ClassNotFound: " + e.getMessage( )); 
      
                }catch(EOFException e){
                    
                    break;
                }  
            } 
           
            System.out.println("Successfully Read Data From Serialized File");
            
        }catch (IOException e) {
            System.out.println("IO: " + e.getMessage( )); 
        } finally{
            closeSerializedFile();
        }
    }
    
     public void closeSerializedFile(){
        try{
            input.close();
            System.out.println("Successfully Closed stakeholder.ser");
        }catch (IOException e) 
            { System.out.println("Error closing stakeholder.ser" + e.getMessage( )); }
    }
    
    public void sortCustomerArrayList(){
        Collections.sort(arrayListCustomer, (Customer o1, Customer o2) -> o1.getStHolderId().compareTo(o2.getStHolderId()));
         
        
    }
    
    public void sortSupplierArrayList(){
        Collections.sort(arrayListSupplier, (Supplier o1, Supplier o2) -> o1.getName().compareTo(o2.getName()));
    }
    
    public static int ageOfCustomer(String s){
        int years = 0;
        try{    
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date d = sdf.parse(s);
                Calendar c = Calendar.getInstance();
                c.setTime(d);
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH) + 1;
                int date = c.get(Calendar.DATE);
                LocalDate dob = LocalDate.of(year, month, date);
                LocalDate now = LocalDate.now();
                Period diff = Period.between(dob, now);
                years = diff.getYears();
                
                
            
        }catch(ParseException e){
            System.out.println(" "+ e.getMessage());
        }
        
        return years;
    }
    
    public static String changeDateFormat(String oldDate){
        String newDate=""; 
        
        try{    
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date d = sdf.parse(oldDate);
                
                
                SimpleDateFormat newDateFormatter = new SimpleDateFormat("dd MMM yyyy");
                newDate = newDateFormatter.format(d);
        
        }catch(ParseException e){
            System.out.println(" "+ e.getMessage());
        }
        
        return newDate;
    }
    
    public void canRentOrNot(){
        
        for(int i = 0; i < arrayListCustomer.size(); i++){
            
            if(arrayListCustomer.get(i).getCanRent() == true){
                canRent++;
            }else if(arrayListCustomer.get(i).getCanRent() == false){
                canNotRent++;
            }
        }
    }
    
    public void openCustWriteFile(){
        try{
            writingToFile = new BufferedWriter(new FileWriter("customerOutFile.txt"));
            System.out.println("Successfully created and opened custmerOutFile.txt");
        }
        catch (IOException ioe){            
            System.out.println("error closing ser file: " + ioe.getMessage());
            System.exit(1);
        }
    }
    
     public void openSuppWriteFile(){
        try{
            writingToFile = new BufferedWriter(new FileWriter("supplierOutFile.txt"));
            System.out.println("Successfully created and opened supplierOutFile.txt");
        }
        catch (IOException ioe){            
            System.out.println("error opening ser file: " + ioe.getMessage());
            System.exit(1);
        }
    }
    
    public void closeWriteFile(){
        try{
            writingToFile.close();
            System.out.println("Successfully closed writingToFile");
        }
        catch (IOException ioe){            
            System.out.println("error closing ser file: " + ioe.getMessage());
            System.exit(1);
        }
    }
    
    public void writingToCustomerFile(){
        try{
            
            openCustWriteFile();
             
            String heading = "============================================ CUSTOMERS ===========================================";
            String subHeading = "ID  \tName \tSurname \tAddress \tDate of Birth \tAge \tCredit \t\tCanRent";
            String border = "==================================================================================================";

            writingToFile.write(heading);
            writingToFile.newLine();
            writingToFile.write(subHeading);
            writingToFile.newLine();
            writingToFile.write(border);
            writingToFile.newLine();

            for(int i = 0;i< arrayListCustomer.size();i++){
                
                String oldDateFormat = arrayListCustomer.get(i).getDateOfBirth();
                String dob = arrayListCustomer.get(i).getDateOfBirth();
                
                String str = String.format("%-8s%-8s%-16s%-16s%-16s%-8dR %-14.2f%-6s",
                            arrayListCustomer.get(i).getStHolderId(),
                            arrayListCustomer.get(i).getFirstName(),
                            arrayListCustomer.get(i).getSurName(),
                            arrayListCustomer.get(i).getAddress(),
                            changeDateFormat(oldDateFormat),
                            ageOfCustomer(dob),   
                            arrayListCustomer.get(i).getCredit(),
                            arrayListCustomer.get(i).getCanRent());
                
                writingToFile.write(str);
                writingToFile.newLine();
            }
            
            canRentOrNot();
            String rentYes = "Number of customers who can rent: " + canRent;
            String rentNo = "Number of customers who cannot rent: " + canNotRent;
            writingToFile.write(border);
            writingToFile.newLine();
            writingToFile.write(rentYes);
            writingToFile.newLine();
            writingToFile.write(rentNo);
            writingToFile.newLine();
            writingToFile.write(border);
            
            System.out.println("Successfully written Customer Data");
            
            
        }catch(FileNotFoundException e){ 
            System.out.println("" + e.getMessage( )); 
        }catch(IOException e){ 
            System.out.println("" + e.getMessage( )); 
        }finally{
            closeWriteFile();
        }
        
    }
    
    public void writingToSupplierFile(){
        try{
            
            openSuppWriteFile();
             
            String heading = "============================ Suppliers ===============================";
            String subHeading = String.format("%-8s%-20s%-17s%-18s","ID","Name","Product Type","Product Description");
            String border = "======================================================================";

            writingToFile.write(heading);
            writingToFile.newLine();
            writingToFile.write(subHeading);
            writingToFile.newLine();
            writingToFile.write(border);
            writingToFile.newLine();

            for(int i = 0;i< arrayListSupplier.size();i++){
                String str = String.format("%-8s%-20s%-17s%-18s",
                            arrayListSupplier.get(i).getStHolderId(),
                            arrayListSupplier.get(i).getName(),
                            arrayListSupplier.get(i).getProductType(),
                            arrayListSupplier.get(i).getProductDescription()
                            );
                
                writingToFile.write(str);
                writingToFile.newLine();
            }
            
            
            writingToFile.write(border);
            System.out.println("Successfully written Supplier Data");
            
            
        }catch(FileNotFoundException e){ 
            System.out.println("" + e.getMessage( )); 
        }catch(IOException e){ 
            System.out.println("" + e.getMessage( )); 
        }finally{
            closeWriteFile();
        }
    
    }

}
