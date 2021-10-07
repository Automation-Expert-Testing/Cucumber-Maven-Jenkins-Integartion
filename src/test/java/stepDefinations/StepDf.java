package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import webPages.AmazonHomePage;

public class StepDf extends AmazonHomePage {

    @Given("the user lands to the Amazon home page")
    public void the_user_lands_to_the_amazon_home_page() {

        //System.out.println("User lands on : "+driver.getTitle());
    }
    @When("user enter {string}")
    public void user_enter(String input) {
        System.out.println("User enter "+input);
//        SearchBox.sendKeys(input);
//        SearchBoxButton.click();
    }
    @Then("user should see a corespending {string}")
    public void user_should_see_a_corespending(String corespending) {
        System.out.println("User should see : "+ corespending);
    }

    @Given("the user lands to the google home page")
    public void the_user_lands_to_the_google_home_page() {
    }
    @Given("the user lands to the youtube home page")
    public void the_user_lands_to_the_youtube_home_page() {
    }
}
