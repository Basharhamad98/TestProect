import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class tenthproj {
	
	WebDriver driver = new ChromeDriver();

	String myWebsite = "https://automationteststore.com/";
	String[] firstNames = { "ahmad", "ali", "anas", "omar", "ayat", "alaa", "sawsan", "Rama" };
	String[] LastNames = { "Khaled", "mustafa", "Mohammad", "abdullah", "malek", "omar" };

	Random rand = new Random();
	
	String GlobalUserName = "";
	
	String GlobalUserNameForTheLogin = "";
	
	String Password="iLoveMyMom123@";


	@BeforeTest
	public void mySetup() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); /* to wait 5 seconds to show the all elements*/

		driver.manage().window().maximize();
		driver.get(myWebsite);

	}

	@Test(priority = 1, enabled=false)
	public void signUp() throws InterruptedException {

		int RandomIndexForTheFirstName = rand.nextInt(firstNames.length);
		int RandomIndexForTheLastName = rand.nextInt(LastNames.length);

		String UserFirstName = firstNames[RandomIndexForTheFirstName];
		String UserLastName = LastNames[RandomIndexForTheLastName];

		int randomNumberForTheEmail = rand.nextInt(564548);

		String domainName = "@gmail.com";

		String email = UserFirstName + UserLastName + randomNumberForTheEmail + domainName;
		

		;

		// take the full text only work with the (a)tag
		driver.findElement(By.linkText("Login or register")).click();

		WebElement SignUpButton = driver.findElement(By.xpath("//button[@title='Continue']"));

		SignUpButton.click();

		Thread.sleep(2000);

		WebElement FirstNameInput = driver.findElement(By.id("AccountFrm_firstname"));
		FirstNameInput.sendKeys(UserFirstName);
		
		GlobalUserName= UserFirstName;
		WebElement LastNameInput = driver.findElement(By.id("AccountFrm_lastname"));
		LastNameInput.sendKeys(UserLastName);
		WebElement EmailInput = driver.findElement(By.id("AccountFrm_email"));
		EmailInput.sendKeys(email);
		WebElement AdressInput = driver.findElement(By.id("AccountFrm_address_1"));
		AdressInput.sendKeys("amman city - tlaa al ali");
		WebElement CityInput = driver.findElement(By.id("AccountFrm_city"));
		CityInput.sendKeys("capital city");

		WebElement CountryInput = driver.findElement(By.id("AccountFrm_country_id"));

		Select selector2 = new Select(CountryInput);
		
		int randomCountry = rand.nextInt(1, 240);

		selector2.selectByIndex(randomCountry);
		
		Thread.sleep(3000);

		WebElement ZoneIdInput = driver.findElement(By.id("AccountFrm_zone_id"));
		Select selector = new Select(ZoneIdInput);
		int randomState = rand.nextInt(1, 6);

		selector.selectByIndex(randomState);

		WebElement PostalCodeInput = driver.findElement(By.id("AccountFrm_postcode"));
		PostalCodeInput.sendKeys("13310");
WebElement LoginNameInput = driver.findElement(By.id("AccountFrm_loginname"));

String LocalUserName =UserFirstName+UserLastName+randomNumberForTheEmail;

LoginNameInput.sendKeys(LocalUserName);

GlobalUserNameForTheLogin= LocalUserName;
WebElement PasswordInput = driver.findElement(By.id("AccountFrm_password"));
PasswordInput.sendKeys(Password);
WebElement ConfirmPasswordInput = driver.findElement(By.id("AccountFrm_confirm"));
ConfirmPasswordInput.sendKeys(Password);


WebElement AgreeCheckBox = driver.findElement(By.id("AccountFrm_agree"));
AgreeCheckBox.click();

WebElement ContinueButton = driver.findElement(By.xpath("//button[@title='Continue']"));

ContinueButton.click();



	}

	@Test(priority = 2, enabled=false)

	public void logout() throws InterruptedException {
		
		Thread.sleep(2000);

		WebElement UserNav = driver.findElement(By.id("customernav"));

		Actions action = new Actions(driver);

		action.moveToElement(UserNav).perform();   /* All the previous code do hover in mouse to show the hidden list*/

    	driver.findElement(By.partialLinkText("Not "+GlobalUserName+"? Logoff")).click(); /*this is how to make a automatic Logoff*/
		
		// Thread.sleep(5000);
		// String LogoutURL
		// ="https://automationteststore.com/index.php?rt=account/logout";

		// driver.get(LogoutURL);

	}
	
	@Test (priority =3)
	
	public void Login() {
		
		driver.findElement(By.linkText("Login or register")).click();
        
		WebElement UserLogin = driver.findElement(By.id("loginFrm_loginname"));
		
		UserLogin.sendKeys(GlobalUserNameForTheLogin);
		
WebElement UserLoginPassword = driver.findElement(By.id("loginFrm_password"));
		
		UserLoginPassword.sendKeys(Password);
		
		WebElement Loginbutton= driver.findElement(By.xpath("//button[@title= 'Login']"));
		
		Loginbutton.click();
		
		
		
	}
	
	
	@Test (priority = 4)
	
	public void AddItemToTheCart () throws InterruptedException {
		
		
		String[] WebSitesForTheItems = {"https://automationteststore.com/index.php?rt=product/category&path=68",
				"https://automationteststore.com/index.php?rt=product/category&path=36",
				"https://automationteststore.com/index.php?rt=product/category&path=43",
				"https://automationteststore.com/index.php?rt=product/category&path=49",
				"https://automationteststore.com/index.php?rt=product/category&path=58",
				"https://automationteststore.com/index.php?rt=product/category&path=52",
				"https://automationteststore.com/index.php?rt=product/category&path=65" };

		int randomIndex = rand.nextInt(WebSitesForTheItems.length);
		driver.get(WebSitesForTheItems[randomIndex]);
		
		// define for the webelement which is a UL tag
		WebElement ListOfITem = driver.findElement(By.cssSelector(".thumbnails.row"));

		// each UL tag has always a li items (list items ) here i am getting the total
		// number of li inside the ul
		int totalNumberOfItems = ListOfITem.findElements(By.tagName("li")).size();
		// create a random index based on the total number of items that i got in the
		// previous line
		int RandomIdexForTheItem = rand.nextInt(totalNumberOfItems);

		// sleep just to see the results before click on the sub category
		Thread.sleep(3000);

		// inside the sub category i need selenium to click on a random sub category
		ListOfITem.findElements(By.tagName("li")).get(RandomIdexForTheItem).click();

		// i defined the container of all items in a Container variable to loop over all
		// items inside using the css selector
		WebElement Container = driver.findElement(By.cssSelector(".thumbnails.grid.row.list-inline"));
		// we need to see how many items that selenium found inside the container
		int numberOfPRoducts = Container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).size();
		// random index for the item to randomly select one item each time
		int randomIndexForTheproduct = rand.nextInt(numberOfPRoducts);

		// the randomly item that we generated using rand.nextInt we need to click on
		// that item
		Container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).get(randomIndexForTheproduct).click();
		
		

	Thread.sleep(5000);
	
	// here i have an UL tag which contains either the Add to cart or out of the stock 
			WebElement ULList = driver.findElement(By.className("productpagecart"));
			
			// inside the UL that i found in the previous line of code i am searching about the a tag noting:-
			//(a) tag if the item in the stock and span tag if the item out of the stock
			int LiItem = ULList.findElements(By.tagName("li")).get(0).findElements(By.tagName("span")).size(); 
			
			
			// this liItem will give me either 0 or 1 ( 0 if the item out of the stock so it will go back to the home page and print a message says that the item out of the stock , if it gives 1 that means the item inside the stock and i can press on the add to cart button by using it's name which is cart 
			if(LiItem>0) {
				driver.get(myWebsite);
				
				System.out.println("sorry the item out of the stock ");
				
				String Expectedrsult = "https://automationteststore.com/";
				
				String Actualresult= driver.getCurrentUrl();
				
				Assert.assertEquals(Actualresult, Expectedrsult);
				
				
				
			}else {
				
				
				
				driver.findElement(By.className("cart")).click();;
				
				Thread.sleep(2000); 

				
				String Atualresult = driver.findElement(By.className("heading1")).getText();
				
				String Expctedresult= "Shopping cart";
				
				Assert.assertEquals(Atualresult, Expctedresult.toUpperCase());
				
				boolean ExpectedValueForCheckOut = true;
				
				boolean  ActualValueForCheckOut= driver.findElement(By.id("cart_checkout1")).isDisplayed();
    
				Assert.assertEquals(ActualValueForCheckOut, ExpectedValueForCheckOut);


		
	
		 
		
		
	}

	}

}
