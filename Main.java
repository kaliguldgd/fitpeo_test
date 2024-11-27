package testpackage;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws InterruptedException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // launch the home page of fitpeo
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.fitpeo.com/");
        Thread.sleep(2000);

        // find revenue calculator button in the home page
        driver.navigate().to("https://www.fitpeo.com/revenue-calculator");
        Thread.sleep(2000);

        // find revenue slider in the revenue calculator page
        WebElement revenueSlider = driver.findElement(By.xpath("//input[@type='range']"));


        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(arguments[0],arguments[1])", revenueSlider.getLocation().x, revenueSlider.getLocation().y);
        Thread.sleep(5000);

        // set revenue slider value to 820
        // move the sliver to reach value 820
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(revenueSlider, 93, 0).release().build().perform();

        Thread.sleep(3000);

        driver.findElement(By.xpath("//*[@id=\":R57alklff9da:\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\":R57alklff9da:\"]")).sendKeys("value", "1560");
        Thread.sleep(5000);

        // // Validate the slider value
        String sliderValue = revenueSlider.getAttribute("value");
        System.out.println("Slider Value: " + sliderValue); // Print the slider value to console

        // Validate the slider value
        if (sliderValue.equals("560")) {
            System.out.println("Slider value validation successful!");
        } else {
            System.out.println("Slider value validation failed. Expected: 560, Found: " + sliderValue);
        }

        //Select CPT Codes:
        WebElement element = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]"));
        jsExecutor.executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
        Thread.sleep(5000);
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[1]/label/span[1]/input")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/label/span[1]/input")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[3]/label/span[1]/input")).click();
        Thread.sleep(5000);

        //Validate Total Recurring Reimbursement:
        WebElement recurringReimbursement = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[1]/div/div[3]/p[2]"));
        String totalRecurringReimbursement = recurringReimbursement.getText();
        System.out.println("Total Recurring Reimbursement: " + totalRecurringReimbursement);
        Thread.sleep(2000);

        //Verify that the header displaying Total Recurring Reimbursement for all Patients Per Month: shows the value $110700.
        driver.findElement(By.xpath("//*[@id=\":R57alklff9da:\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\":R57alklff9da:\"]")).sendKeys("value", "1820");
        Thread.sleep(5000);
        totalRecurringReimbursement = recurringReimbursement.getText();

        if (totalRecurringReimbursement.equals("$110700")) {
            System.out.println("Total Recurring Reimbursement value validation successful!");
        } else {
            System.out.println("Total Recurring Reimbursement value validation failed. Expected:$110700 , Found: " + totalRecurringReimbursement);
        }

        driver.quit();
    }
}