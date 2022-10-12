package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.Project;
import helpers.Attach;
import helpers.DriverSettings;
import helpers.DriverUtils;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;


public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DriverSettings.configure();
    }

    @AfterEach
    public void afterEach() {
        String sessionId = DriverUtils.getSessionId();

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();

        if (Project.isVideoOn()) {
            Attach.video(sessionId);
        }
        Selenide.closeWebDriver();
    }
}
