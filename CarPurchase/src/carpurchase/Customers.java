/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carpurchase;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author QP
 */
public class Customers {
    static int currentCustomerNumber = 0;
    
    public static String[][] customerInformations = new String[100][3];
    Object[] row = new Object[3];
    
    public void initCustomer() throws FileNotFoundException
    {
        FileReader reader = new FileReader("customers.txt");
        Scanner in = new Scanner(reader);
        int iObjectIndex = 0;
                            
        while (in.hasNextLine())
        {        
            String line = in.nextLine();            
            Scanner lineScanner = new Scanner(line);

            iObjectIndex = 0;
            while (lineScanner.hasNext())
            {
                customerInformations[currentCustomerNumber][iObjectIndex] = lineScanner.next();   
                iObjectIndex++;               
            }      
            currentCustomerNumber++;
        }        
    }
    
    public void writeCustomerInfoToFile() throws IOException
    {
        new FileWriter("customers.txt", false).close();

        FileWriter writer = new FileWriter("customers.txt",true);
        
        for (int iCustomerIndex = 0; iCustomerIndex < currentCustomerNumber; iCustomerIndex++)
        {
            for (int iObjectIndex = 0; iObjectIndex < 3; iObjectIndex++)
            {
                writer.write(customerInformations[iCustomerIndex][iObjectIndex]);   
                writer.write(" ");
            }
            writer.write("\n");
        }
        writer.close();
    }
    
    public void addCustomer(Object[] customerInformation) throws IOException
    {
        for (int i = 0; i < 3; i++)
        {
            customerInformations[currentCustomerNumber][i] = customerInformation[i].toString();
        }
        currentCustomerNumber++;
        
        writeCustomerInfoToFile();
    }
    
    public void deleteCustomer(int iCustomerIndex) throws IOException
    {
        for (int i = iCustomerIndex; i < currentCustomerNumber; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                customerInformations[iCustomerIndex][j] = customerInformations[(iCustomerIndex + 1)][j];
            }           
        }
        currentCustomerNumber--;
        
        writeCustomerInfoToFile();
    }
    
    public String[][] getCustomerInformations()
    {
        return customerInformations;
    }
    public int getCurrentCustomerNumber()
    {
        return currentCustomerNumber;
    }
}
