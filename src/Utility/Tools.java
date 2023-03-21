package Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Tools {


    public static void Bekle(int sn) {

        try {
            Thread.sleep(1000 * sn); // milisaniye  cinsinden beklediği
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}





