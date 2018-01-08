package com.Finsys;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Testtest {
	@Test(invocationCount = 1)
	public void countiFrame() {
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.ganeshbagal.com/weddings");
		driver.manage().window().maximize();
		System.out.println(driver.findElements(By.xpath("//iframe")).size());
		List<WebElement> ele = driver.findElements(By.xpath("//iframe"));
		System.out.println("Number of frames in a page :" + ele.size());
		for (WebElement el : ele) {
			// Returns the Id of a frame.
			System.out.println("Frame Id :" + el.getAttribute("id"));
			// Returns the Name of a frame.
			System.out.println("Frame name :" + el.getAttribute("name"));
		}
		driver.switchTo().frame("comp-iz2knkrhiframe");
		System.out.println(driver.findElements(By.cssSelector(".gallery-item-social-love.block-fullscreen.progallery-svg-font-icons-love_empty")).size());
		System.out.println(driver.findElements(By.xpath("//button[@data-hook='love-icon']")).size());
		//List<WebElement> hearticon = driver.findElements(By.xpath("//button[@data-hook='love-icon']"));
		List<WebElement> hearticon = driver.findElements(By.cssSelector(".gallery-item-social-love.block-fullscreen.progallery-svg-font-icons-love_empty"));
		int count = 0;
		for (WebElement hi : hearticon) {
			hi.click();
			count++;
			System.out.println("click " + count);
		}
		driver.quit();
	}

}
