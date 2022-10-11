package tests;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class LocalMobileTests extends TestBase {
    @Test
    void ClickAllPopUp() {

        step("Кликаем все всплывающие окна", () ->
                $(AppiumBy.xpath("//android.widget.ImageView[contains(@resource-id,'dialog_close_btn')]")).click());
                $(AppiumBy.xpath("//android.widget.Button[@text='MORE' and @index='10']")).click();
                $(AppiumBy.xpath("//android.widget.Button[@text='CLEAR' and @index='9']")).click();
                $(AppiumBy.xpath("//android.widget.Button[@text='NO' and @index='1']")).click();
    }

    @Test
    @DisplayName("Проверка поля ввода Bitcoin и проверка Description")
    void searchTestBitcoin() {
        String valueTitle = "Bitcoin";
        String valueText = "Decentralized digital currency";
        //switchTo().alert().accept();
        back();
        step("Поиск", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys(valueTitle);
        });
        step("Проверка найденного содержимого", () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                    .shouldHave(CollectionCondition.sizeGreaterThan(0));
            $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                    .shouldHave(text(valueTitle));
            $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_description"))
                    .shouldHave(text(valueText));
        });
    }

}


