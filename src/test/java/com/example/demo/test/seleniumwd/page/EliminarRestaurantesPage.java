package com.example.demo.test.seleniumwd.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.demo.test.seleniumwd.driver.MikhunaDriver;

public class EliminarRestaurantesPage {

	private By linkLIstar = By.id("listRestaurant");
	private By linkDeleteRestaurant = By.xpath("//*[@id=\"deleteRestaurant\"]");
	private By cajaClave = By.id("comfirmPassword");	
	private By checkboxConfirmar = By.id("comfirm");	
	private By buttonDelete = By.id("buttonDelete");		
	private By textMessage = By.id("message");

	private WebDriver webDriver = null;
	
	public EliminarRestaurantesPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public String eliminarRestaurante(String clave, String checkbox) throws Exception{
		webDriver.findElement(linkLIstar).click();
		Thread.sleep(2000);
		
		webDriver.findElement(linkDeleteRestaurant).click();
		Thread.sleep(2000);
		
		webDriver.findElement(cajaClave).clear();
		webDriver.findElement(cajaClave).sendKeys(clave);
		Thread.sleep(2000);

		if(!checkbox.isEmpty()) {
			webDriver.findElement(checkboxConfirmar).click();
			Thread.sleep(2000);	
		}

		webDriver.findElement(buttonDelete).click();
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
