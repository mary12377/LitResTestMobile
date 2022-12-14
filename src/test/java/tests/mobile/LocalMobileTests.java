package tests.mobile;

import helpers.ClickTheAd;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class LocalMobileTests extends TestBase {
    @Tag("mobile")
    @Test
    void theBookWasNotFound() {
        ClickTheAd.clickAllPopUp();
        step("Поиск книги которой нет на сайте ", () ->
                $(AppiumBy.id("ru.litres.android:id/nav_search")).click());
                $(AppiumBy.id("ru.litres.android:id/et_search_query")).sendKeys("Савин тестирование dot com");
        step("Проверка результатов поиска", () ->
                $(AppiumBy.id("ru.litres.android:id/title")).shouldHave(text("Nothing found")));
    }

    @Test
    @DisplayName("Проверка онбординга")
    void testOnboarding() {
      ClickTheAd.clickAllPopUp();
        $(AppiumBy.id("ru.litres.android:id/nav_store")).click();
        $(AppiumBy.id("ru.litres.android:id/nav_search")).click();
        $(AppiumBy.id("ru.litres.android:id/tv_search_most_frequent"))
                .shouldHave(text("Most frequent search:"));
        $(AppiumBy.id("ru.litres.android:id/nav_my_audiobooks")).click();
        $(AppiumBy.id("ru.litres.android:id/nav_profile")).click();
        $(AppiumBy.id("ru.litres.android:id/user_name"))
                .shouldHave(text("Anonymous reader"));

    }
}


