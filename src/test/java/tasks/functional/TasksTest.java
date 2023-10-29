package tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class TasksTest {
	
	public WebDriver acederApp() throws MalformedURLException
	{
		System.setProperty("webdriver.chrome.driver","C:\\Local\\devops\\selenium\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.31.79:4444/wd/hub"), cap);

		driver.navigate().to("http://192.168.31.79:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	@Test
	public void deveGravarTarefaComSucesso() throws MalformedURLException
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
	public void naodeveGravarSemDesc() throws MalformedURLException
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
	public void naoDeveGravarTarefaSemData() throws MalformedURLException
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
	public void deveGravarTarefaComDataAntiga() throws MalformedURLException
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
