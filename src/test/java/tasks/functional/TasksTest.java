package tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TasksTest {
	
	public WebDriver acederApp()
	{
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	@Test
	public void deveGravarTarefaComSucesso()
	{
		WebDriver driver = acederApp();
		try {
			//clicar em add
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descriçao
			driver.findElement(By.id("task")).sendKeys("selenium");
			
			//escrever data
			driver.findElement(By.id("dueDate")).sendKeys("12/12/2030");
	
			//clicar add
			driver.findElement(By.id("saveButton")).click();
				
			//validar mensagem
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Sucess!", message);
		}
		finally {
			//fechar
			driver.quit();			
		}
	}
	
	@Test
	public void naodeveGravarSemDesc()
	{
		WebDriver driver = acederApp();
		try {
			//clicar em add
			driver.findElement(By.id("addTodo")).click();
					
			//escrever data
			driver.findElement(By.id("dueDate")).sendKeys("12/12/2030");
	
			//clicar add
			driver.findElement(By.id("saveButton")).click();
				
			//validar mensagem
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
		}
		finally {
			//fechar
			driver.quit();			
		}
	}
	
	@Test
	public void naoDeveGravarTarefaSemData()
	{
		WebDriver driver = acederApp();
		try {
			//clicar em add
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descriçao
			driver.findElement(By.id("task")).sendKeys("selenium");
			
			//clicar add
			driver.findElement(By.id("saveButton")).click();
				
			//validar mensagem
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
		}
		finally {
			//fechar
			driver.quit();			
		}
	}
	
	@Test
	public void deveGravarTarefaComDataAntiga()
	{
		WebDriver driver = acederApp();
		try {
			//clicar em add
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descriçao
			driver.findElement(By.id("task")).sendKeys("selenium");
			
			//escrever data
			driver.findElement(By.id("dueDate")).sendKeys("12/12/2010");
	
			//clicar add
			driver.findElement(By.id("saveButton")).click();
				
			//validar mensagem
			String message = driver.findElement(By.id("message")).getText();

			Assert.assertEquals("Due date must not be in past", message);
		}
		finally {
			//fechar
			driver.quit();			
		}
	}

}
