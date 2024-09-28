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
public class Cart {
    static int currentCartNumber = 0;
    
    public static String[][] cartInformations = new String[100][5];
    Object[] row = new Object[5];
    
    public void initCart() throws FileNotFoundException
    {
        FileReader reader = new FileReader("cart.txt");
        Scanner in = new Scanner(reader);
        int iObjectIndex = 0;
                            
        while (in.hasNextLine())
        {        
            String line = in.nextLine();            
            Scanner lineScanner = new Scanner(line);

            iObjectIndex = 0;
            while (lineScanner.hasNext())
            {
                cartInformations[currentCartNumber][iObjectIndex] = lineScanner.next();   
                iObjectIndex++;               
            }      
            currentCartNumber++;
        }
    }
    
    public void writeCartInfoToFile() throws IOException
    {
        new FileWriter("cart.txt", false).close();

        FileWriter writer = new FileWriter("cart.txt",true);
        
        for (int iCartIndex = 0; iCartIndex < currentCartNumber; iCartIndex++)
        {
            for (int iObjectIndex = 0; iObjectIndex < 5; iObjectIndex++)
            {
                writer.write(cartInformations[iCartIndex][iObjectIndex]);   
                writer.write(" ");
            }
            writer.write("\n");
        }
        writer.close();
    }
    
    public void addCart(Object[] cartInformation) throws IOException
    {
        for (int i = 0; i < 5; i++)
        {
            if (i == 0)
                cartInformations[currentCartNumber][i] = Integer.toString(currentCartNumber + 1);
            else
                cartInformations[currentCartNumber][i] = cartInformation[i].toString();
        }
        currentCartNumber++;
        
        writeCartInfoToFile();
    }
    
    public void deleteCart(int iCartIndex) throws IOException
    {
        currentCartNumber--;
        for (int i = iCartIndex; i < currentCartNumber; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                if (j == 0)
                    cartInformations[i][j] = Integer.toString(i + 1);
                else
                    cartInformations[i][j] = cartInformations[(i + 1)][j];
            }           
        }
        
        writeCartInfoToFile();
    }
    
    public String[][] getCartInformations()
    {
        return cartInformations;
    }
    public int getCurrentCartNumber()
    {
        return currentCartNumber;
    }
    
    public int getTotalCartPrice()
    {
        int iTotalCartPrice = 0;
        
        for (int iIndex = 0; iIndex < currentCartNumber; iIndex++)
        {
            iTotalCartPrice = iTotalCartPrice + Integer.parseInt(cartInformations[iIndex][4]);
        }
        
        return iTotalCartPrice;
    }
}
