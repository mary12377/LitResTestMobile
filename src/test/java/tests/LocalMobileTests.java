package tests;

import com.codeborne.selenide.Condition;
import helpers.ClickTheAd;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class LocalMobileTests extends TestBase {
    @Test
    void theBookWasNotFound() {
        ClickTheAd.clickAllPopUp();
        step("Ищем книгу котрой нет на сайте ", () ->
                $(AppiumBy.xpath("//android.widget.ImageView[contains(@resource-id,'icon')]")).click());
                $(AppiumBy.xpath("//android.widget.ImageView[contains(@resource-id,'search')]")).click();


    }


}


