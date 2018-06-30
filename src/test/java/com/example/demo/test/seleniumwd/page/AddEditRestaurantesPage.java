package com.example.demo.test.seleniumwd.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.example.demo.test.seleniumwd.driver.MikhunaDriver;


public class AddEditRestaurantesPage {
	private Select select;
	private By linkNewRestaurant = By.id("newRestaurant");
	private By linkEditRestaurant = By.xpath("//*[@id=\"editRestaurant\"]");
	private By cajaName = By.id("txtName");
	private By cajaCellphone = By.id("txtCellphone");
	private By cajaOpenHour = By.id("txtOpenHour");
	private By cajaCloseHour = By.id("txtCloseHour");
	private By cajaDistrict = By.id("dropdownDistrict");
	private By radioStateActive = By.id("radioActive");
	private By linkLIstar = By.id("listRestaurant");
	private By buttonSave = By.id("buttonSave");		
	private By textMessage = By.id("message");

	private WebDriver webDriver = null;
	
	 
	
	public AddEditRestaurantesPage(WebDriver webDriver) {
		System.out.println("Llega 6");
		this.webDriver = webDriver;
		System.out.println("Llega 7");
	}
	
	public String insertar(String name, String cellphone, String openHOure, String closeHour, String District) throws Exception {
		
		webDriver.findElement(linkNewRestaurant).click();
		Thread.sleep(2000);

		
		webDriver.findElement(cajaName).clear();
		webDriver.findElement(cajaName).sendKeys(name);

		Thread.sleep(2000);
		webDriver.findElement(cajaCellphone).clear();
		webDriver.findElement(cajaCellphone).sendKeys(cellphone);

		Thread.sleep(2000);
		webDriver.findElement(cajaOpenHour).clear();
		webDriver.findElement(cajaOpenHour).sendKeys(openHOure);

		Thread.sleep(2000);
		webDriver.findElement(cajaCloseHour).clear();
		webDriver.findElement(cajaCloseHour).sendKeys(closeHour);	
		
		Thread.sleep(2000);
		select = new Select(webDriver.findElement(cajaDistrict));
		select.selectByVisibleText("Comas");


		Thread.sleep(2000);
		WebElement radioBtn1 = webDriver.findElement(radioStateActive);	
		((JavascriptExecutor) webDriver).executeScript("arguments[0].checked = true;", radioBtn1);	
		//((JavascriptExecutor)webDriver).executeScript("var clickEvent = document.createEvent('MouseEvents');clickEvent.initEvent ('click', true, true);arguments[0].dispatchEvent (clickEvent);", radioBtn1);
		
		Thread.sleep(2000);		
		webDriver.findElement(buttonSave).click();
		Thread.sleep(2000);
		return webDriver.findElement(textMessage).getText();
	}
	
	public String edit(String name, String cellphone, String openHour, String closeHour, String District) throws Exception {
		webDriver.findElement(linkLIstar).click();
		Thread.sleep(2000);
		webDriver.findElement(linkEditRestaurant).click();
		Thread.sleep(2000);
		
		
		webDriver.findElement(cajaName).clear();
		webDriver.findElement(cajaName).sendKeys(name);

		Thread.sleep(2000);
		webDriver.findElement(cajaCellphone).clear();
		webDriver.findElement(cajaCellphone).sendKeys(cellphone);

		Thread.sleep(2000);
		webDriver.findElement(cajaOpenHour).clear();
		webDriver.findElement(cajaOpenHour).sendKeys(openHour);

		Thread.sleep(2000);
		webDriver.findElement(cajaCloseHour).clear();
		webDriver.findElement(cajaCloseHour).sendKeys(closeHour);	

		Thread.sleep(2000);
		select = new Select(webDriver.findElement(cajaDistrict));
		select.selectByVisibleText("Comas");

		Thread.sleep(2000);
		WebElement radioBtn1 = webDriver.findElement(radioStateActive);
		((JavascriptExecutor) webDriver).executeScript("arguments[0].checked = true;", radioBtn1);
		//((JavascriptExecutor)webDriver).executeScript("var clickEvent = document.createEvent('MouseEvents');clickEvent.initEvent ('click', true, true);arguments[0].dispatchEvent (clickEvent);", radioBtn1);

		Thread.sleep(2000);
		webDriver.findElement(buttonSave).click();
		Thread.sleep(2000);
		return webDriver.findElement(textMessage).getText();
		
	}
	
	public void cerrarPagina(){
		MikhunaDriver.cerrarPagina(webDriver);
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}

}
