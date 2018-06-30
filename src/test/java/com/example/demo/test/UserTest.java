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

import com.example.demo.dominios.User;
import com.example.demo.dominios.Utility;
import com.example.demo.services.UserService;
import com.example.demo.test.bean.UserServiceImplementTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {
	
	private static UserService userService;
	private static User user;
	 
  @BeforeClass
  public static void beforeClass() {
	  System.out.println("Inicio de la clase");
	  userService = new UserServiceImplementTest();
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
                    
          user = new User();
          user.setEmail("Prueba");
          user.setPassword("Prueba");
          
          user = userService.save(user);
                    
          Assert.assertTrue(user.getUserId()>0);      
      } catch (Exception e) {
          e.printStackTrace();
          Assert.fail();
      }
  }
  @Test (dependsOnMethods = {"a_insertar"}, timeOut=2000)
  public void b_validate_password() {
      try {
          System.out.println("Método Insertar");
          Utility utility = new Utility(user.getPassword(), true);                   
          boolean flag = userService.validatePassword(user.getUserId(), utility.getPassword());                    
          Assert.assertTrue(flag);      
      } catch (Exception e) {
          e.printStackTrace();
          Assert.fail();
      }
  }
  @Test (dependsOnMethods = {"b_validate_password"}, timeOut=2000)
  public void c_invalidate_password() {
      try {
          System.out.println("Método Insertar");
          Utility utility = new Utility(user.getPassword(), true);   
          utility.setPassword("123");
          utility.setConfirmation(false);
          boolean flag = userService.validatePassword(user.getUserId(), utility.getPassword());                    
          Assert.assertTrue(!flag);      
      } catch (Exception e) {
          e.printStackTrace();
          Assert.fail();
      }
  }
  @Test (dependsOnMethods = {"c_invalidate_password"}, timeOut=2000)
  public void d_actualizar() {
      try {
          System.out.println("Método Actualiza");
          
          user.setEmail("Prueba 2");
          user.setPassword("Prueba 2");
          
          userService.save(user);
          
          Assert.assertTrue(user.getUserId()>0);
      } catch (Exception e) {
          e.printStackTrace();
          Assert.fail();
      }
  }
  
  @Test (dependsOnMethods = {"d_actualizar"}, timeOut=2000)
  public void e_obtener() {
      try {
          System.out.println("Método Obtener");
          
          user = userService.findOne(user.getUserId());
          
          Assert.assertNotNull(user);
          
          System.out.println("ID: " + user.getUserId());
          System.out.println("Nombre: " + user.getEmail());
      } catch (Exception e) {
          e.printStackTrace();
          Assert.fail();
      }
  }

  @Test (dependsOnMethods = {"e_obtener"}, timeOut=2000)
  public void f_listar() {
      try {
          System.out.println("Método Listar");
          
          List<User> list = userService.findAll();
          
          Assert.assertTrue(list.size() > 0);
          
          for (User user : list) {
              System.out.println("ID: " + user.getUserId());
              System.out.println("Nombre: " + user.getEmail());
          }
      } catch (Exception e) {
          e.printStackTrace();
          Assert.fail();
      }
  }

  @Test (dependsOnMethods = {"f_listar"}, timeOut=2000)
  public void g_eliminar() {
      try {
          System.out.println("Método Eliminar");
          
          userService.delete(user.getUserId());
          
          Assert.assertTrue(true);
      } catch (Exception e) {
          e.printStackTrace();
          Assert.fail();
      }
  }
   
}