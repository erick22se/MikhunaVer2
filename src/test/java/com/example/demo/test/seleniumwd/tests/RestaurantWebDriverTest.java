package com.example.demo.test.seleniumwd.tests;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.example.demo.test.seleniumwd.fuenteDatos.Excel;
import com.example.demo.test.seleniumwd.fuenteDatos.MySql;
import com.example.demo.test.seleniumwd.page.AddEditRestaurantesPage;
import com.example.demo.test.seleniumwd.page.EliminarRestaurantesPage;
import com.example.demo.test.seleniumwd.page.IniciarSesionPage;
import com.example.demo.test.seleniumwd.page.ListarRestaurantesPage;



public class RestaurantWebDriverTest {
	
	private String urlInicial = "http://localhost:8081";
	private ListarRestaurantesPage listarRestaurantesPage;
	private IniciarSesionPage iniciarSesionPage;
	private AddEditRestaurantesPage addEditRestaurantPage;
	private EliminarRestaurantesPage eliminarRestaurantPage;
	
	@BeforeTest
	@Parameters({ "navegador", "remoto" })
	public void inicioClase(String navegador, int remoto) {
		this.iniciarSesionPage = new IniciarSesionPage(navegador, this.urlInicial ,remoto == 1);
		this.listarRestaurantesPage = new ListarRestaurantesPage(iniciarSesionPage.getWebDriver());
		this.addEditRestaurantPage = new AddEditRestaurantesPage(iniciarSesionPage.getWebDriver());
		this.eliminarRestaurantPage = new EliminarRestaurantesPage(iniciarSesionPage.getWebDriver());
	}
		
	@DataProvider(name = "datosCreate")
	public static Object[][] datosCreate(ITestContext context) {
		Object[][] datos = null;
		String fuenteDatos = context.getCurrentXmlTest().getParameter("fuenteDatos");
		System.out.println("Fuente de Datos: " + fuenteDatos);
		switch(fuenteDatos){
			case "BD":
				datos = MySql.leerCategoriaMysql();
				break;
			case "Excel":
				String rutaArchivo = context.getCurrentXmlTest().getParameter("rutaArchivoAdd");
				datos = Excel.leerExcel(rutaArchivo);
				break;
		}
		return datos;
	}
	
	@DataProvider(name = "datosEdit")
	public static Object[][] datosEdit(ITestContext context) {
		Object[][] datos = null;
		String fuenteDatos = context.getCurrentXmlTest().getParameter("fuenteDatos");
		System.out.println("Fuente de Datos: " + fuenteDatos);
		switch(fuenteDatos){
			case "BD":
				datos = MySql.leerCategoriaMysql();
				break;
			case "Excel":
				String rutaArchivo = context.getCurrentXmlTest().getParameter("rutaArchivoEdit");
				datos = Excel.leerExcel(rutaArchivo);
				break;
		}
		return datos;
	}
	
	@DataProvider(name = "datosList")
	public static Object[][] datosLista(ITestContext context) {
		Object[][] datos = null;
		String fuenteDatos = context.getCurrentXmlTest().getParameter("fuenteDatos");
		System.out.println("Fuente de Datos: " + fuenteDatos);
		switch(fuenteDatos){
			case "BD":
				datos = MySql.leerCategoriaMysql();
				break;
			case "Excel":
				String rutaArchivo = context.getCurrentXmlTest().getParameter("rutaArchivoList");
				datos = Excel.leerExcel(rutaArchivo);
				break;
		}
		return datos;
	}
	
	@DataProvider(name = "datosDelete")
	public static Object[][] datosDelete(ITestContext context) {
		Object[][] datos = null;
		String fuenteDatos = context.getCurrentXmlTest().getParameter("fuenteDatos");
		System.out.println("Fuente de Datos: " + fuenteDatos);
		switch(fuenteDatos){
			case "BD":
				datos = MySql.leerCategoriaMysql();
				break;
			case "Excel":
				String rutaArchivo = context.getCurrentXmlTest().getParameter("rutaArchivoDelete");
				datos = Excel.leerExcel(rutaArchivo);
				break;
		}
		return datos;
	}
	
	@Test(dataProvider="datosList")
	public void a_ListarRestaurantes(String usuario,String clave) throws Exception {
		
		try {
			System.out.println("Llega 8");
			iniciarSesionPage.iniciarSesion(usuario, clave);
			String valorObtenido = listarRestaurantesPage.listarRestaurantes();
			Assert.assertEquals(valorObtenido, "");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(dataProvider = "datosCreate")
	public void b_crearRestaurantes(String usuario, String clave, String restaurant,  
									String cellphone, String openHoure, String closeHoure, String Distrito,String valorEsperado) throws Exception {
		try {
			Distrito="Comas";
			iniciarSesionPage.iniciarSesion(usuario, clave);
			String valorObtenido = addEditRestaurantPage.insertar(restaurant, cellphone, openHoure, closeHoure, Distrito);
			Assert.assertEquals(valorObtenido, valorEsperado);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(dataProvider = "datosEdit")
	public void c_editRestaurantes(String usuario, String clave, String restaurant,  
									String cellphone, String openHoure, String closeHoure, String Distrito,String valorEsperado) throws Exception {
		try {
			iniciarSesionPage.iniciarSesion(usuario, clave);
			String valorObtenido = addEditRestaurantPage.edit(restaurant, cellphone, openHoure, closeHoure, Distrito);
			Assert.assertEquals(valorObtenido, valorEsperado);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test(dataProvider = "datosDelete")
	public void d_eliminarRestaurantes(String usuario,String clave,String confirmation,String secondconfirmation,String valorEsperados) throws Exception {
		try {
			iniciarSesionPage.iniciarSesion(usuario, clave);
			String valorObtenido = eliminarRestaurantPage.eliminarRestaurante(confirmation, secondconfirmation);
			Assert.assertEquals(valorObtenido, valorEsperados);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		listarRestaurantesPage.cerrarPagina();
	}

}
