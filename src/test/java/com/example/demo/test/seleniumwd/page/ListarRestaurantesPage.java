package com.example.demo.test.seleniumwd.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.demo.test.seleniumwd.driver.MikhunaDriver;
public class ListarRestaurantesPage {

	private By linkLIstar = By.id("listRestaurant");
	private WebDriver webDriver = null;
	
	public ListarRestaurantesPage(WebDriver webDriver) {
		System.out.println("Llega 5");
		this.webDriver = webDriver;
	}
	
	public String listarRestaurantes() throws Exception{
		webDriver.findElement(linkLIstar).click();
		Thread.sleep(2000);
		return "";
	}
	
	public void cerrarPagina(){
		MikhunaDriver.cerrarPagina(webDriver);
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}	
}
