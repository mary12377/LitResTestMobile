package tests.web;

import config.Project;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
@Tag("web")
public class LitResTest extends TestBase{

    @Test
    void testOnboarding() {

        step("Открываем сайт", () -> {
            open(Project.config.baseUrl());
        });
        step("Кликаем онбординг", () -> {
            $$(".LowerMenu-module__item").findBy(text("Новинки")).click();
            $(".book_ratings").shouldHave(text("Новинки книг"));
            $$(".LowerMenu-module__item").findBy(text("Популярное")).click();
            $(".book_ratings").shouldHave(text("Книжные бестселлеры"));
            $$(".LowerMenu-module__item").findBy(text("Аудиокниги")).click();
            $(".www.page-content.new-container").shouldHave(text("Что послушать?"));
            $$(".LowerMenu-module__item").findBy(text("Что почитать?")).click();
            $(".www.page-content.new-container").shouldHave(text("Что почитать?"));
            $$(".LowerMenu-module__item").findBy(text("Самиздат")).click();
            $(".selfpublishing-top").shouldHave(text("Самиздат"));
            $$(".LowerMenu-module__item").findBy(text("Промокод")).click();
            $(".h2.putmoney_h2").shouldHave(text("Активация промокода ЛитРес"));

        });
    }

    @Test
    void testRecommendations() {

        step("Открываем сайт", () -> {
            open(Project.config.baseUrl());
        });
        step("Кликаем на рекомендации и выбираем книгу", () -> {
            $$(".LowerMenu-module__item").findBy(text("Популярное")).click();
            $$(".sorting-block__link").findBy(text("Новинки")).click();
            $$(".art__name").findBy(text("Последняя жертва")).click();
        });
            step("Проверяем что появилось название книги", () -> {
                $(".biblio_book_name.biblio-book__title-block").shouldHave(text("Последняя жертва"));
            });


    }
}
