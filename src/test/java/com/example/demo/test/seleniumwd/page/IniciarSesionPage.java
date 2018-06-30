package com.example.demo.test.seleniumwd.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.example.demo.test.seleniumwd.driver.MikhunaDriver;

public class IniciarSesionPage {

	private By cajaUsuario = By.id("txtEmail");
	private By cajaClave = By.id("txtPassword");
	private By botonIniciarSesion = By.id("buttonSingIn");
	private String urlInicial;
	private WebDriver webDriver = null;
	
	public IniciarSesionPage(String navegador, String urlInicial, boolean remoto){
		System.out.println("Llega 3");
		this.webDriver = MikhunaDriver.inicializarDriver(navegador, remoto);
		this.urlInicial = urlInicial;
	}
	
	public void iniciarSesion(String usuario, String clave) throws Exception{
		System.out.println("Llega 4");
		webDriver.get(urlInicial);
		Thread.sleep(2000);
		webDriver.findElement(cajaUsuario).clear();
		webDriver.findElement(cajaUsuario).sendKeys(usuario);
		webDriver.findElement(cajaClave).clear();
		webDriver.findElement(cajaClave).sendKeys(clave);
		webDriver.findElement(botonIniciarSesion).click();
		Thread.sleep(2000);
	}
	
	public void cerrarPagina(){
		MikhunaDriver.cerrarPagina(webDriver);
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}
	
}
