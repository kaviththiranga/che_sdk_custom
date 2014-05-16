/*
 * CODENVY CONFIDENTIAL
 * __________________
 *
 * [2014] Codenvy, S.A.
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.sdk.qa.itests.datasource;

import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.codenvy.sdk.qa.AbstractIntegrationTest;
import com.codenvy.sdk.qa.selenium.pages.IDEMainPage;
import com.codenvy.sdk.qa.selenium.pages.datasource.NewDatasourceWizardPage;

/**
 * Test datasource creation. See specs reports in src/test/resources/com/codenvy/sdk/qa/itests/datasource/NewDatasourceWizard.html
 */
public class NewDatasourceWizard extends AbstractIntegrationTest {


    protected IDEMainPage             mainPage;
    protected NewDatasourceWizardPage newDatasourceWizard;

    public String access(String url) {
        driver.get(url);
        mainPage = PageFactory.initElements(driver, IDEMainPage.class);
        return "access";
    }


    public String displayDatasourceMenu() {
        return mainPage.getMainMenuItem("DatasourceMainMenu").getText();
    }

    public String displayDatasourceNewDatasourceAction() {
        mainPage.getMainMenuItem("DatasourceMainMenu").click();
        return mainPage.getMainMenuAction("Datasource/New Datasource Connection").getText();
    }

    public String clickOnNewDatasourceAction() {
        mainPage.getMainMenuAction("Datasource/New Datasource Connection").click();
        newDatasourceWizard = new NewDatasourceWizardPage(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), newDatasourceWizard);
        return "clicks";
    }


    public String displayNewDatasourceWizard() {
        return newDatasourceWizard.getWizardTitle();
    }

    public String postgresDsTypeIsAvailable() {
        if (newDatasourceWizard.isDatasourceTypeAvailable("postgres")) {
            return "is enabled";
        }
        return "is not enabled";
    }


    public String mySqlDsTypeIsAvailable() {
        if (newDatasourceWizard.isDatasourceTypeAvailable("mysql")) {
            return "is enabled";
        }
        return "is not enabled";
    }

    public String msSQLServerDsTypeIsAvailable() {
        if (newDatasourceWizard.isDatasourceTypeAvailable("sqlserver")) {
            return "is enabled";
        }
        return "is not enabled";
    }

    public String oracleDsTypeIsNotAvailable() {
        try {
            new WebDriverWait(driver, 3).until(gwtToogleButtonToBeEnable(
                                                                  By.cssSelector("#gwt-debug-datasource-wizard-ds-type-oracle")
                                                                  ));
            fail("Oracle shouldn't be enabled by default");
        } catch (Exception e) {
            // success
        }
        return "is disabled";
    }

}