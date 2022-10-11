package autotests.tests;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("android")
public class LocalMobileTests extends TestBase {

    @Test
    @DisplayName("Проверка ввода при поиске")
    void searchTest() {
        //  switchTo().alert().accept();
        back();
        step("Search BrowserStack in Wikipedia", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("BrowserStack");
        });

        step("Проверка результатов", () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                    .shouldHave(CollectionCondition.sizeGreaterThan(0));
        });

    }

    @Test
    @DisplayName("Проверка очистки поля поиска")
    void checkEmptySearch() {
        // switchTo().alert().accept();
        back();
        step("Поиск", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Bitcoin");
        });
        step("Кликаем кнопку очистки поля поиска", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/search_close_btn")).click());
        step("Проверяем текст пустого сообщения", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/search_empty_message")).shouldHave(text("Search Wikipedia in more languages")));
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

    @Test
    @DisplayName("Проверка онбординга")
    void chektestOnboarding() {
        switchTo().alert().accept();

        $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                .shouldHave(text("The Free Encyclopedia …in over 300 languages"));
        $(AppiumBy.xpath("//android.widget.LinearLayout[2]")).click();
        $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                .shouldHave(text("New ways to explore"));
        $(AppiumBy.id("org.wikipedia.alpha:id/secondaryTextView"))
                .shouldHave(text("Dive down the Wikipedia rabbit hole with a constantly updating Explore feed. " +
                        "Customize the feed to your interests – whether it’s learning about historical events On this day, " +
                        "or rolling the dice with Random."));
        $(AppiumBy.xpath("//android.widget.LinearLayout[3]")).click();
        $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                .shouldHave(text("Reading lists with sync"));
        $(AppiumBy.xpath("//android.widget.LinearLayout[4]")).click();
        $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView"))
                .shouldHave(text("Send anonymous data"));
    }


    @Test
    @DisplayName("Проверка текста на стартовом экране")
    void checkTextStartScreen() {
        // switchTo().alert().accept();
        back();

        step("check text", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/view_announcement_text"))
                    .shouldHave(text("Customize your Explore feed You can now choose what to show on your feed," +
                            " and also prioritize your favorite types of content."));
        });
    }

    @Test
    @DisplayName("Проверка кнопки Log in / Join Wikipedia на экране Saved  ")
    void moreMenuContainsLoginItem() {
        switchTo().alert().accept();
        back();
        step("Open menu", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/nav_tab_reading_lists")).click());
                $(AppiumBy.id("org.wikipedia.alpha:id/positiveButton")).click();
        step("Verify Menu ", () ->
                $(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget." +
                        "LinearLayout/android.widget.FrameLayout/" +
                        "android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView"))
                        .shouldHave(text("Create an account")));
    }

    @Test
    @DisplayName("Проверка иконки Saved")
    void moreMenuContainsDonateItem() {
        // switchTo().alert().accept();
        back();
        step("", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id/nav_tab_reading_lists")).click());
        step("", () ->
                $(AppiumBy.id("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/" +
                        "android.widget.FrameLayout/android.widget.FrameLayout/android.widget" +
                        ".FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView"))
                        .shouldHave(text("Saved")));
    }
}


