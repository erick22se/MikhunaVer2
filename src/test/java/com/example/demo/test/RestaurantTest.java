package com.example.demo.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.example.demo.dominios.Restaurant;
import com.example.demo.dominios.District;
import com.example.demo.dominios.User;
import com.example.demo.services.RestaurantService;
import com.example.demo.test.bean.RestaurantBean;
import com.example.demo.test.bean.RestaurantServiceImplementTest;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestaurantTest {
	private static User user;
	private static District district;
	private static RestaurantService restaurantService;
	private static Restaurant restaurant;
		
	@BeforeClass
	public void beforeClass() {
		System.out.println("Inicio de la Clase");
		user = new User(1,"test@gmail.com","123",new ArrayList<Restaurant>());
		district = new District(1,"Surco");
		restaurantService = new RestaurantServiceImplementTest();
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("Fin de la Clase");
	}
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("Antes de cada test");
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("Despues de cada test");
	}
	
	@DataProvider(name= "datosDeRestaurante")
	public static Object[][] datosDeRestaurante(){
		return new Object[][] {
			{new RestaurantBean("Nombre 01", "999999999","11:00 AM","16:00 PM",true)},
			{new RestaurantBean("Nombre 02", "999999999","11:00 AM","16:00 PM",true)},
			{new RestaurantBean("Nombre 03", "999999999","11:00 AM","16:00 PM",true)},
			{new RestaurantBean("Nombre 04", "999999999","11:00 AM","16:00 PM",true)},
			{new RestaurantBean("Nombre 05", "999999999","11:00 AM","16:00 PM",true)}
		};
	}
	
	@Test
	public void a_validation() {
		try {
			System.out.println("Met. Insertar");
			
			restaurant = new Restaurant(0,"El Progreso","900000000","10:00 AM","15:00 PM",true,user,district);				
			boolean flag = restaurantService.validateHour(restaurant.getOpenHour(), restaurant.getCloseHour());
			
			Assert.assertTrue(flag);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test (dependsOnMethods = {"a_validation"}, timeOut=2000)
	public void b_insertar() {
		try {
			System.out.println("Met. Insertar");
			
			restaurant = new Restaurant();
			restaurant.setName("El Progreso");
			restaurant.setCellphone("900000000");
			restaurant.setOpenHour("10:00 AM");
			restaurant.setCloseHour("15:00 PM");
			restaurant.setState(true);
			restaurant.setDistrict(district);
			restaurant.setUser(user);
			
			restaurant = restaurantService.save(restaurant);
			
			Assert.assertTrue(restaurant.getRestaurantId()>0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test (dependsOnMethods = {"b_insertar"}, timeOut=2000)
	public void c_actualizar() {
		try {
			System.out.println("Met.Actualizar");
			
			restaurant.setName("El Progreso 2");
			restaurant.setCellphone("900000001");
			restaurant.setOpenHour("10:15 AM");
			restaurant.setCloseHour("15:15 PM");
			
			restaurantService.save(restaurant);
			
			Assert.assertTrue(restaurant.getRestaurantId()>0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test (dependsOnMethods = {"c_actualizar"}, timeOut=2000)
	public void d_obtener() {
		try {
			System.out.println("Met. Obtener");
			
	        restaurant = restaurantService.findOne(restaurant.getRestaurantId());
	        
	        Assert.assertNotNull(restaurant);
	        
	        System.out.println("ID: " + restaurant.getRestaurantId());
	        System.out.println("Nombre: " + restaurant.getName());
	    } catch (Exception e) {
	        e.printStackTrace();
	        Assert.fail();
	    }
	}
	  
	@Test (dependsOnMethods = {"d_obtener"}, timeOut=2000)
	public void e_listar() {
	    try {
	        System.out.println("Met. Listar");
	        
	        List<Restaurant> list = restaurantService.findAll(user.getUserId());
	        
	        Assert.assertTrue(list.size() > 0);
	        
	        for (Restaurant restaurant : list) {
	            System.out.println("ID: " + restaurant.getRestaurantId());
	            System.out.println("Nombre: " + restaurant.getName());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        Assert.fail();
	    }
	}
	  
	@Test (dependsOnMethods = {"e_listar"}, timeOut=2000)
	public void f_eliminar() {
	    try {
	        System.out.println("Met. Eliminar");
	        
	        restaurantService.delete(restaurant.getRestaurantId());
	        
	        Assert.assertTrue(true);
	    } catch (Exception e) {
	        e.printStackTrace();
	        Assert.fail();
	    }
	}
	
	@Test 
	@Parameters({"nombreInsertar","celularInsertar","horaAperturaInsertar", "horaCierreInsertar", "estadoInsertar"})
	public void g_insertarConParametro(String nomInsertar, String celInsertar, String horaAperturaInsertar, String horaCierreInsertar, boolean estadoInsertar) {
		try {
			System.out.println("Met. Insertar con Parametro");
			
			restaurant = new Restaurant();
			restaurant.setName(nomInsertar);
			restaurant.setCellphone(celInsertar);
			restaurant.setOpenHour(horaAperturaInsertar);
			restaurant.setCloseHour(horaCierreInsertar);
			restaurant.setState(estadoInsertar);
			restaurant.setDistrict(district);
			restaurant.setUser(user);
			
			restaurant = restaurantService.save(restaurant);
			
			Assert.assertTrue(restaurant.getRestaurantId()>0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	 
	@Test (dependsOnMethods = {"g_insertarConParametro"}, timeOut=2000)
	@Parameters({"nombreActualizar","celularActualizar","horaAperturaActualizar", "horaCierreActualizar", "estadoActualizar"})
	public void h_actualizarConParametro(String nomActualizar, String celActualizar, String horaAperturaActualizar, String horaCierreActualizar, boolean estadoActualizar) {
		try {
			System.out.println("Met. Actualizar con Parametro");
			
			restaurant.setName(nomActualizar);
			restaurant.setCellphone(celActualizar);
			restaurant.setOpenHour(horaAperturaActualizar);
			restaurant.setCloseHour(horaCierreActualizar);
			
			restaurantService.save(restaurant);
			
			Assert.assertTrue(restaurant.getRestaurantId()>0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
		
	@Test(dataProvider="datosDeRestaurante")
	public void i_insertarDataProvider(RestaurantBean restaurantBean) {
		try {
			System.out.println("Met. Insertar con DataProvider");
			
			restaurant = new Restaurant();
			restaurant.setName(restaurantBean.getName());
			restaurant.setCellphone(restaurantBean.getCellphone());
			restaurant.setOpenHour(restaurantBean.getOpenHour());
			restaurant.setCloseHour(restaurantBean.getCloseHour());
			restaurant.setState(restaurantBean.getState());
			restaurant.setDistrict(district);
			restaurant.setUser(user);
			
			restaurantService.save(restaurant);
			
			Assert.assertTrue(restaurant.getRestaurantId()>0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}	

}
