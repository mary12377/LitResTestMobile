package drivers;



import com.codeborne.selenide.WebDriverProvider;
import config.LocalConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class MobileDriver implements WebDriverProvider {
    static LocalConfig configLocal = ConfigFactory.create(LocalConfig.class, System.getProperties());
    @Override
    public WebDriver createDriver(Capabilities capabilities) {

        File app = getApp();

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setPlatformName(configLocal.platformName());
        options.setDeviceName(configLocal.device());
//       options.setPlatformVersion("11.0");
        options.setApp(app.getAbsolutePath());

        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    public static URL getAppiumServerUrl() {
        try {
            return new URL(configLocal.appURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    private File getApp() {
        String appUrl = "https://apkpure.com/ru/litres-read-and-listen/ru.litres.android/download";
        String appPath = "src/test/resources/apps/LitRes Read and listen_v3.64.0(2)-gp_apkpure.com.apk";

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app;
    }

}

