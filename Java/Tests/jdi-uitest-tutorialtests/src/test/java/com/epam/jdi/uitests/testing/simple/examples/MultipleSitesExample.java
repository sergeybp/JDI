package com.epam.jdi.uitests.testing.simple.examples;

import com.codeborne.selenide.webdriver.WebDriverFactory;
import com.epam.jdi.uitests.testing.TestsBase;
import com.epam.jdi.uitests.testing.career.page_objects.site.google.GoogleSite;
import com.epam.jdi.uitests.testing.career.page_objects.site.yandex.YandexSite;
import com.epam.jdi.uitests.web.selenium.driver.DriverTypes;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.settings.WebSettings;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.epam.jdi.uitests.core.interfaces.complex.interfaces.Column.inColumn;
import static com.epam.jdi.uitests.testing.career.page_objects.site.epam.EpamSite.jobListingPage;
import static com.epam.jdi.uitests.web.selenium.driver.DriverTypes.CHROME;
import static com.epam.jdi.uitests.web.selenium.driver.WebDriverUtils.killAllRunWebDrivers;
import static com.epam.jdi.uitests.web.settings.WebSettings.initFromProperties;
import static com.epam.jdi.uitests.web.settings.WebSettings.useDriver;


public class MultipleSitesExample {
    @BeforeMethod
    public void before(Method method) throws IOException {
        initFromProperties();
        WebSite.init(YandexSite.class, useDriver(CHROME));
        WebSite.init(GoogleSite.class, useDriver(CHROME));
    }
    @Test
    public void twoSiteExample() {
        YandexSite.homePage.open();
        Assert.areEquals(YandexSite.homePage.getDriver().getCurrentUrl(),
            "https://ya.ru/");
        GoogleSite.homePage.open();
        Assert.contains(GoogleSite.homePage.getDriver().getCurrentUrl(),
                "https://www.google.ru/");
    }
}
