package com.example.demo.test;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.example.demo.dominios.District;
import com.example.demo.services.DistrictService;
import com.example.demo.test.bean.DistrictServiceImplementTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DistrictTest {

	private static DistrictService districtService;
	private static District district;
	
	 @BeforeClass
	  public static void beforeClass() {
		  System.out.println("Inicio de la clase");
		  districtService = new DistrictServiceImplementTest();
	  }

	  @AfterClass
	  public static void afterClass() {
		  System.out.println("Fin de la clase");
	  }

	  @BeforeTest
	  public void beforeTest() {
		  System.out.println("Inicio Metodo");
	  }

	  @AfterTest
	  public void afterTest() {
		  System.out.println("Fin Metodo");
	  }
	  
	  @Test
	  public void a_insertar() {
	      try {
	          System.out.println("Método Insertar");
	                    
	          district = new District();
	          district.setName("Prueba");	          
	          
	          district = districtService.save(district);
	                    
	          Assert.assertTrue(district.getDistrictId()>0);	      
	      } catch (Exception e) {
	          e.printStackTrace();
	          Assert.fail();
	      }
	  }
	  
	  @Test (dependsOnMethods = {"a_insertar"}, timeOut=2000)
	  public void b_actualizar() {
		  try {
			  System.out.println("Método Actualiza");
	           
	          district.setName("Prueba 2");
	            
	          districtService.save(district);
	            
	          Assert.assertTrue(district.getDistrictId() > 0);
	      } catch (Exception e) {
	          e.printStackTrace();
	          Assert.fail();
	      }
	  }
	  
	  @Test (dependsOnMethods = {"b_actualizar"}, timeOut=2000)
	  public void c_obtener() {
		  try {
			  System.out.println("Método Obtener");
	          
	          district = districtService.findOne(district.getDistrictId());
	            
	          Assert.assertNotNull(district);
	            
	          System.out.println("ID: " + district.getDistrictId());
	          System.out.println("Nombre: " + district.getName());
	      } catch (Exception e) {
	          e.printStackTrace();
	          Assert.fail();
	      }
	  }

	  @Test (dependsOnMethods = {"c_obtener"}, timeOut=2000)
	  public void d_listar() {
	      try {
	          System.out.println("Método Listar");
	          
	          List<District> list = districtService.findAll();
	            
	          Assert.assertTrue(list.size() > 0);
	            
	          for (District district : list) {
	              System.out.println("ID: " + district.getDistrictId());
	              System.out.println("Nombre: " + district.getName());
	          }
	      } catch (Exception e) {
	          e.printStackTrace();
	          Assert.fail();
	      }
	  }

	  @Test (dependsOnMethods = {"d_listar"}, timeOut=2000)
	  public void e_eliminar() {
	      try {
	          System.out.println("Método Eliminar");
	          
	          districtService.delete(district.getDistrictId());
	          
	          Assert.assertTrue(true);
	      } catch (Exception e) {
	          e.printStackTrace();
	          Assert.fail();
	      }
	  }	 
}
