package ru.sviridoff.parsers;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ParserMephi {

    private WebDriver driver;
    private List<String> resultYES = new ArrayList<String>();
    private List<String> resultNO = new ArrayList<String>();

    @Before
    public void init_driver(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        this.driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(-2000, 0));
    }

    @After
    public void destruct_driver(){
        this.driver.close();
        this.driver = null;
    }

    private void parseMephi(){
        String urlYES = "https://org.mephi.ru/pupil-rating/get-rating/entity/6862/original/yes";
        String urlNO = "https://org.mephi.ru/pupil-rating/get-rating/entity/6862/original/no";
        this.driver.get(urlYES);
        WebElement element = this.driver.findElement(By.id("ratingTable"));
        Pattern pattern1 = Pattern.compile("Свиридов");
        Pattern pattern2 = Pattern.compile("Хаперский");
        Pattern pattern3 = Pattern.compile("Дудов");
        for (String elem: element.getText().split("\n")) {
            if (pattern1.matcher(elem).find() || pattern2.matcher(elem).find() || pattern3.matcher(elem).find()) {
                resultYES.add(elem);
            }
        }

        this.driver.get(urlNO);
        element = this.driver.findElement(By.id("ratingTable"));
        for (String elem: element.getText().split("\n")) {
            if (pattern1.matcher(elem).find() || pattern2.matcher(elem).find() || pattern3.matcher(elem).find()) {
                resultNO.add(elem);
            }
        }
        System.out.println("По оригиналам");
        for (String elem: resultYES) {
            System.out.println(elem);
        }
        System.out.println("По оригиналам и копиям");
        for (String elem: resultNO) {
            System.out.println(elem);
        }
    }

    @Test
    public void testParser(){

        parseMephi();

    }


}
