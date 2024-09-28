/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carpurchase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author QP
 */

public class Cars {
    static int currentCarNumber = 0;
    
    public static String[][] carInformations = new String[100][5];
    Object[] row = new Object[5];
    
    public void initCar() throws FileNotFoundException
    {
        FileReader reader = new FileReader("cars.txt");
        Scanner in = new Scanner(reader);
        int iObjectIndex = 0;
                            
        while (in.hasNextLine())
        {        
            String line = in.nextLine();            
            Scanner lineScanner = new Scanner(line);

            iObjectIndex = 0;
            while (lineScanner.hasNext())
            {
                carInformations[currentCarNumber][iObjectIndex] = lineScanner.next();   
                iObjectIndex++;               
            }      
            currentCarNumber++;
        }        
    }
    
    public void writeCarInfoToFile() throws IOException
    {
        new FileWriter("cars.txt", false).close();

        FileWriter writer = new FileWriter("cars.txt",true);
        
        for (int iCarIndex = 0; iCarIndex < currentCarNumber; iCarIndex++)
        {
            for (int iObjectIndex = 0; iObjectIndex < 5; iObjectIndex++)
            {
                writer.write(carInformations[iCarIndex][iObjectIndex]);   
                writer.write(" ");
            }
            writer.write("\n");
        }
        writer.close();
    }
    
    public void addCar(Object[] carInformation) throws IOException
    {
        for (int i = 0; i < 5; i++)
        {
            carInformations[currentCarNumber][i] = carInformation[i].toString();
        }
        currentCarNumber++;
        
        writeCarInfoToFile();
    }
    
    public void editCar(Object[] carInformation, int iCarIndex) throws IOException
    {
        for (int i = 0; i < 5; i++)
        {
            carInformations[iCarIndex][i] = carInformation[i].toString();
        }
        
        writeCarInfoToFile();
    }
    
    public void deleteCar(int iCarIndex) throws IOException
    {
        currentCarNumber--;
        for (int i = iCarIndex; i < currentCarNumber; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                carInformations[i][j] = carInformations[(i + 1)][j];
            }           
        }
        
        writeCarInfoToFile();
    }
    
    public String[][] getCarInformation()
    {
        return carInformations;
    }
    public int getCurrentCarNumber()
    {
        return currentCarNumber;
    }
}
