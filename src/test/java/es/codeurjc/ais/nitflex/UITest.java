package es.codeurjc.ais.nitflex;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UITest {

    @LocalServerPort
    int port;

    WebDriver driver;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
    }
    @AfterEach
    public void teardown(){
        if(driver != null)
            driver.quit();
    }

    @Test
    public void addFilmTest(){
        driver.get("http://localhost:"+this.port+"/");

        int num = driver.findElements(By.className("film-details")).size();

        driver.findElement(By.id("create-film")).click();

        driver.findElement(By.name("title")).sendKeys("Casino Royale");
        driver.findElement(By.name("releaseYear")).sendKeys("2006");
        driver.findElement(By.name("url")).sendKeys("https://www.zonanegativa.com/imagenes/2016/04/cubierta_james_bond_casino_royal.jpg");
        driver.findElement(By.name("synopsis")).sendKeys("Esta pelicula trata sobre un espia . Esta to guapa");

        driver.findElement(By.id("Save")).click();
        driver.findElement(By.id("all-films")).click();

        assertEquals(num+1, driver.findElements(By.className("film-details")).size());

    }

    @Test
    public void removeFilmTest(){
        driver.get("http://localhost:"+this.port+"/");

        int num = driver.findElements(By.className("film-details")).size();

        driver.findElement(By.id("create-film")).click();

        driver.findElement(By.name("title")).sendKeys("Casino Royale");
        driver.findElement(By.name("releaseYear")).sendKeys("2006");
        driver.findElement(By.name("url")).sendKeys("https://www.zonanegativa.com/imagenes/2016/04/cubierta_james_bond_casino_royal.jpg");
        driver.findElement(By.name("synopsis")).sendKeys("Esta pelicula trata sobre un espia . Esta to guapa");

        driver.findElement(By.id("Save")).click();
        driver.findElement(By.id("remove-film")).click();



    }
}
