package com.zombieclothing.pageObjects;

import com.beust.jcommander.Parameter;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.Var;
import com.zombieclothing.testCases.BaseClass;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;

import java.util.List;
import java.util.Random;

public class PaymentPage extends BaseClass {

    public boolean checkPoint;
    public Actions actions;
    public WebDriverWait wait;
    WebDriver ldriver;

    @Var
    private   int pageNumber;
    transient String BTN_NEXT_LOCATOR= "//following::a[@class=\"next\"]";
    private   By nextButtonLocator= By.xpath(BTN_NEXT_LOCATOR);
    private   String PAR_LIST_PRODUCT_LOCATOR= "//div[contains(normalize-space(@class),\"filter clearfix\")]";
    private   String CHI_LIST_PRODUCT_NAME_LOCATOR= "//h3//child::a[@title][1]";


    public PaymentPage(WebDriver rdriver) {
        ldriver= rdriver;
        PageFactory.initElements(rdriver, this);
        actions= new Actions(rdriver);
        wait= new WebDriverWait(rdriver, 50);
    }

    @FindBy (xpath= "//div[@id=\"nav\"]//nav")
    @CacheLookup
    WebElement NAV_PARENT;

    @FindBy(xpath= "//nav//descendant::a[contains(@href,\"/\") and @title=\"Trang chủ\" or @title=\"Sản phẩm\" or @title=\"Giới thiệu\" or @title=\"Blog\"]")
    @CacheLookup
    WebElement NAV_CHILDREN;

    @FindBy(xpath = "//a[@title=\"Sản phẩm\"]")
    @CacheLookup
    WebElement NAV_SAN_PHAM;

    @FindBy(xpath = "//div//button[@id=\"add-to-cart\" and @type]")
    @CacheLookup
    WebElement BTN_ADD_TO_CART_LOCATOR;

    @FindBy(xpath = "//div[@class=\"cart-view-total\"]//td//a[contains(@class, \"checkout\")]")
    @Parameter
    @CacheLookup
    WebElement BTN_CHECK_OUT_LOCATOR;

    @FindBy(xpath = "//div//child::input[@id=\"billing_address_full_name\"]")
    @CacheLookup
    WebElement TXT_FULL_NAME_LOCATOR;

    @FindBy(xpath = "//div//child::input[@type=\"email\" and contains(@id, \"checkout\")]")
    @CacheLookup
    WebElement TXT_EMAIL_LOCATOR;

    @FindBy(xpath = "//div//child::input[@type=\"tel\" and contains(@id, \"phone\")]")
    @CacheLookup
    WebElement TXT_PHONE_LOCATOR;

    @FindBy(xpath = "//div//child::input[@type=\"text\" and contains(@id, \"address\") and contains(@name,\"address1\")]")
    @CacheLookup
    WebElement TXT_ADDRESS_LOCATOR;

    @FindBy(xpath = "//*[@id=\"customer_shipping_province\"]")
    @CacheLookup
    WebElement PAR_DDL_PROVINCE_LOCATOR;

    @FindBy(xpath = "//select[contains(@id, \"province\") and contains(@name, \"province\")]//child::option")
    @CacheLookup
    WebElement CHILD_DDI_PROVINCE_LOCATOR;

    @FindBy(xpath = "//select[contains(@id, \"district\") and contains(@name, \"district\")]")
    @CacheLookup
    WebElement PAR_DDL_DISTRICT_LOCATOR;

    @FindBy(xpath = "//select[contains(@id, \"district\") and contains(@name, \"district\")]//option")
    @CacheLookup
    WebElement CHILD_DDI_DISTRICT_LOCATOR;

    @FindBy(xpath = "//div//select[contains(@id, \"ward\") and contains(@name, \"ward\")]")
    @CacheLookup
    WebElement PAR_DDL_WARD_LOCATOR;

    @FindBy(xpath = "//select[contains(@id, \"ward\") and contains(@name, \"ward\")]//option")
    @CacheLookup
    WebElement CHILD_DDI_WARD_LOCATOR;

    @FindBy(xpath = "//form[@id=\"form_next_step\"]//span//ancestor::button")
    @CacheLookup
    WebElement BTN_CONTINUE_CHECKOUT_LOCATOR;

    @FindBy(xpath = "//form[@id=\"form_next_step\"]//following::a[normalize-space(text())=\"Giỏ hàng\"]")
    @CacheLookup
    WebElement BTN_BACK_TO_CART;

    public void clickSanPhamNav(String str, WebDriver dr) {
        WebElement listElement= dr.findElement(By.xpath("//div[@id=\"nav\"]//nav"));
        List<WebElement> childNavElements= listElement.findElements(By.xpath("//nav//descendant::a[contains(@href,\"/\") and @title=\"Trang chủ\" or @title=\"Sản phẩm\" or @title=\"Giới thiệu\" or @title=\"Blog\"]"));
        for (int index=0; index< childNavElements.size(); index++) {
            if (childNavElements.get(index).getText().equals(str) || childNavElements.get(index).getText().contains(str)) {
                childNavElements.get(index).click();
                break;
            }
        }
    }



    public void clickToSanPhamNav(WebDriver dr) {
        if (NAV_SAN_PHAM.isDisplayed()) {
            actions.moveToElement(NAV_SAN_PHAM).click().perform();
        }
    }

    public void pauseWithTryCatch(int timeSecond) {
        try {
            Thread.sleep(timeSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public enum pageIndex {
        firstPage,
        secondPage,
        thirdPage,
        fourthPage,
        fifthPage,
        sixthPage,
        seventhPage,
        eighthPage,
        ninthPage,
        tenthPage,
                    eleventhPage,
                    twelfthPage,
                    thirteenPage,
                    fourteenthPage,
                    fifteenthPage,
                    sixteenthPage,
                    seventeenthPage,
                    eighteenthPage,
                    nineteenthPage
    }

    public void getPageFromEnumeration(int lottoResult) {
        if (1<= lottoResult && lottoResult<= 20) {
            pageIndex firstPage= pageIndex.firstPage;
            this.pageNumber= 1;
        }
        if (21<= lottoResult && lottoResult<= 40) {
            pageIndex secondPage= pageIndex.secondPage;
            this.pageNumber= 2;
        }
        if (41<= lottoResult && lottoResult<= 60) {
            this.pageNumber= 3;
        }
        if (61<= lottoResult && lottoResult<= 80) {
            pageIndex fourthPage= pageIndex.fourthPage;
            this.pageNumber= 4;
        }
        if (81<= lottoResult && lottoResult<= 100) {
            pageIndex fifthPage= pageIndex.fifthPage;
            this.pageNumber= 5;
        }
        if (101<= lottoResult && lottoResult<= 120) {
            pageIndex sixthPage= pageIndex.sixthPage;
            this.pageNumber= 6;
        }
        if (121<= lottoResult && lottoResult<= 140) {
            pageIndex seventhPage= pageIndex.seventhPage;
            this.pageNumber= 7;
        }
        if (141<= lottoResult && lottoResult<= 160) {
            pageIndex eighthPage= pageIndex.eighthPage;
            this.pageNumber= 8;
        }
        if (161<= lottoResult && lottoResult<= 180) {
            pageIndex ninthPage= pageIndex.ninthPage;
            this.pageNumber= 9;
        }
        if (181<= lottoResult && lottoResult<= 200) {
            pageIndex tenthPage= pageIndex.tenthPage;
            this.pageNumber= 10;
        }
        if (201<= lottoResult && lottoResult<= 220) {
            pageIndex eleventhPage= pageIndex.eleventhPage;
            this.pageNumber= 11;
        }
        if (221<= lottoResult && lottoResult<= 240) {
            pageIndex twelfthPage= pageIndex.twelfthPage;
            this.pageNumber= 12;
        }
        if (241<= lottoResult && lottoResult<= 260) {
            pageIndex thirteenPage= pageIndex.thirteenPage;
            this.pageNumber= 13;
        }
        if (261<= lottoResult && lottoResult<= 280) {
            pageIndex fourteenthPage= pageIndex.fourteenthPage;
            this.pageNumber= 14;
        }
        if (281<= lottoResult && lottoResult<= 300) {
            pageIndex fifteenthPage= pageIndex.fifteenthPage;
            this.pageNumber= 15;
        }
        if (301<= lottoResult && lottoResult<= 320) {
            pageIndex sixteenthPage= pageIndex.sixteenthPage;
            this.pageNumber= 16;
        }
        if (321<= lottoResult && lottoResult<= 340) {
            pageIndex seventeenthPage= pageIndex.seventeenthPage;
            this.pageNumber= 17;
        }
        if (341<= lottoResult && lottoResult<= 360) {
            pageIndex eighteenthPage= pageIndex.eighteenthPage;
            this.pageNumber= 18;
        }
        if (361<= lottoResult && lottoResult<= 371) {
            pageIndex nineteenthPage= pageIndex.nineteenthPage;
            this.pageNumber= 19;
        }
    }

    public static void executeScrollingDown(WebDriver dr, JavascriptExecutor js, int scrollUnit) {
        ((JavascriptExecutor)dr).executeScript("scroll(0,"+scrollUnit+")");
    }

    public void clickAddToCartButton() {
//        if (BTN_ADD_TO_CART_LOCATOR.isEnabled() || BTN_ADD_TO_CART_LOCATOR.isDisplayed()) {
            actions.moveToElement(BTN_ADD_TO_CART_LOCATOR).click().perform();
//        }
    }

    public void clickCheckOutButton() {
//        if (BTN_CHECK_OUT_LOCATOR.isDisplayed() || BTN_CHECK_OUT_LOCATOR.isEnabled()) {
            actions.moveToElement(BTN_CHECK_OUT_LOCATOR).click().perform();
//        }
    }

    public void clickBackToCartButton() {
        if (BTN_BACK_TO_CART.isDisplayed() || BTN_BACK_TO_CART.isEnabled()) {
            actions.moveToElement(BTN_BACK_TO_CART).click().perform();
        }
    }

    public void clickContinueCheckOutButton() {
        if (BTN_CONTINUE_CHECKOUT_LOCATOR.isDisplayed() || BTN_CONTINUE_CHECKOUT_LOCATOR.isEnabled()) {
            actions.moveToElement(BTN_CONTINUE_CHECKOUT_LOCATOR).click().perform();
        }
    }

    public void setFullName(String fullName) {
        if (TXT_FULL_NAME_LOCATOR.isDisplayed()) {
            actions.click(TXT_FULL_NAME_LOCATOR).sendKeys(fullName).perform();
            TXT_FULL_NAME_LOCATOR.clear();
        }
    }

    public void setName(String fullName) {
        if (TXT_FULL_NAME_LOCATOR.isDisplayed()) {
            actions.click(TXT_FULL_NAME_LOCATOR).sendKeys(fullName).perform();
        }
    }

    public void setEmail(String email) {
        if (TXT_EMAIL_LOCATOR.isDisplayed()) {
            actions.click(TXT_EMAIL_LOCATOR).sendKeys(email).perform();
        }
    }

    public void setPhone(String phone) {
        if (TXT_PHONE_LOCATOR.isDisplayed()) {
            actions.click(TXT_PHONE_LOCATOR).sendKeys(phone).perform();
        }
    }

    public void setAddress(String address) {
        if (TXT_ADDRESS_LOCATOR.isDisplayed()) {
            actions.click(TXT_ADDRESS_LOCATOR).sendKeys(address).perform();
        }
    }

    public void selectProvince(String str, WebDriver dr) {
        WebElement listProvinceElement= dr.findElement(By.xpath("//select[contains(@id, \"province\") and contains(@name, \"province\")]"));
        List<WebElement> childProvinceElements= listProvinceElement.findElements(By.xpath("//select[contains(@id, \"province\") and contains(@name, \"province\")]//option"));
        for (int index=0; index< childProvinceElements.size(); index++) {
            if (childProvinceElements.get(index).getText().equals(str) || childProvinceElements.get(index).getText().contains(str)) {
                System.out.println("\nYour province: "+ childProvinceElements.get(index).getText().toUpperCase());
                childProvinceElements.get(index).click();
                break;
            }
        }
    }

    public void selectDistrict(String str, WebDriver dr) {
        WebElement listDistrictElement= dr.findElement(By.xpath("//select[contains(@id, \"district\") and contains(@name, \"district\")]"));
        List<WebElement> childDistrictElements= listDistrictElement.findElements(By.xpath("//select[contains(@id, \"district\") and contains(@name, \"district\")]//option"));
        for (int index=0; index< childDistrictElements.size(); index++) {
            if (childDistrictElements.get(index).getText().equals(str) || childDistrictElements.get(index).getText().contains(str)) {
                System.out.println("\nYour district: "+ childDistrictElements.get(index).getText().toUpperCase());
                childDistrictElements.get(index).click();
                break;
            }
        }
    }

    public void selectWard(String str, WebDriver dr) {
        WebElement listWardElement= dr.findElement(By.xpath("//div//select[contains(@id, \"ward\") and contains(@name, \"ward\")]"));
        List<WebElement> childWardElements= listWardElement.findElements(By.xpath("//select[contains(@id, \"ward\") and contains(@name, \"ward\")]//option"));
        for (int index=0; index< childWardElements.size(); index++) {
            if (childWardElements.get(index).getText().equals(str) || childWardElements.get(index).getText().contains(str)) {
                System.out.println("\nYour ward: "+ childWardElements.get(index).getText().toUpperCase());
                childWardElements.get(index).click();
                break;
            }
        }
    }




    public void relocateToThePage(int lottoResult) {
        switch (pageNumber) {
            case 1:     /*      01->20      */
                System.out.println("---//-----##-------the first page------//-----##---".toUpperCase());
                executeScrollingDown(driver, javascript, 1000);
                WebElement listResultsElementFrst= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                List<WebElement> childResultsElementsFrst= listResultsElementFrst.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                System.out.println("\n"+ "Product name: "+ childResultsElementsFrst.get(lottoResult-1).getText().toUpperCase());
                actions.moveToElement(childResultsElementsFrst.get(lottoResult-1)).click().perform();
                break;
            case 2:     /*      21->40      */
                System.out.println("---//-----##-------the second page------//-----##---".toUpperCase());
                    WebElement nextButtonSec= driver.findElement(nextButtonLocator);
                    executeScrollingDown(driver, javascript, 6000);
                    pauseWithTryCatch(500);
                    actions.moveToElement(nextButtonSec).click().perform();
                    WebElement listResultsElementSec= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                    List<WebElement> childResultsElementsSec= listResultsElementSec.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                    if (lottoResult<= 29) {
                        String lottoResultParseStringSec= String.valueOf(lottoResult);
                        String lastLottoResultCharSec= lottoResultParseStringSec.substring(lottoResultParseStringSec.length()-1);
                        int lottoResultParseIntSec= Integer.parseInt(lastLottoResultCharSec);
                        System.out.println("\n"+ "Product name: "+ childResultsElementsSec.get(lottoResultParseIntSec-1).getText().toUpperCase());
                        pauseWithTryCatch(4500);
//                        driver.navigate().refresh();
                        childResultsElementsSec.get(lottoResultParseIntSec-1).click();
                    }
                        else if (lottoResult>=30) {
                        System.out.println("\n"+ "Product name: "+ childResultsElementsSec.get(lottoResult-21).getText().toUpperCase());
                        actions.moveToElement(childResultsElementsSec.get(lottoResult-21)).click().perform();
                    }

                break;
            case 3:     /*      41->60      */
                System.out.println("---//-----##-------the third page------//-----##---".toUpperCase());
                clickNextButton: for (int index=1; index<3; index++) {
                    WebElement nextButton= driver.findElement(nextButtonLocator);
                    executeScrollingDown(driver, javascript, 4000);
                    pauseWithTryCatch(500);
                    actions.moveToElement(nextButton).click().perform();
                    if (index==2) {
                        WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                        List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                        if (lottoResult<=49) {
                            String lottoResultParseString= String.valueOf(lottoResult);
                            String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                            int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResultParseInt-1).getText().toUpperCase());
                            childResultsElements.get(lottoResultParseInt-1).click();
                        }
                        else if (lottoResult>=50) {
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResult-41).getText().toUpperCase());
                            actions.moveToElement(childResultsElements.get(lottoResult-41)).click().perform();
                        }
//                        clickSanPham: for (index=0; index<lottoResultParseInt; index++) {
//                            if (index== lottoResultParseInt - 1) {
//                                System.out.println("\n"+ "Product name: "+ childResultsElements.get(index).getText().toUpperCase());
//                                childResultsElements.get(index).click();
//                                break clickSanPham;
//                            }
//                        }
                        break clickNextButton;
                    }
                }
                break;
            case 4:     /*      61->80      */
                System.out.println("---//-----##-------the fourth page------//-----##---".toUpperCase());
//                for (int index=1; index<4; index++) {
//                    WebElement nextButton= driver.findElement(nextButtonLocator);
//                    executeScrollingDown(driver, javascript, 4000);
//                    pauseWithTryCatch(500);
//                    actions.moveToElement(nextButton).click().perform();
//                    WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
//                    List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
//                    String lottoResultParseString= String.valueOf(lottoResult);
//                    String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
//                    int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
//                    for (index=0; index<lottoResultParseInt; index++) {
//                        if (index== lottoResultParseInt - 1) {
//                            childResultsElements.get(index).click();
//                            break;
//                        }
//                    }
//                }
                clickNextButton: for (int index=1; index<4; index++) {
                    WebElement nextButton= driver.findElement(nextButtonLocator);
                    executeScrollingDown(driver, javascript, 4000);
                    pauseWithTryCatch(2000);
                    actions.moveToElement(nextButton).click().perform();
                    if (index==3) {
                        WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                        List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                        if (lottoResult<=69) {
                            String lottoResultParseString= String.valueOf(lottoResult);
                            String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                            int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResultParseInt-1).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            childResultsElements.get(lottoResultParseInt-1).click();
                        }
                        else if (lottoResult>=70) {
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResult-61).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            actions.moveToElement(childResultsElements.get(lottoResult-61)).click().perform();
                        }
                        break clickNextButton;
                    }
                }
                break;
            case 5:     /*      81->100      */
                System.out.println("---//-----##-------the fifth page------//-----##---".toUpperCase());
//                for (int index=1; index<5; index++) {
//                    WebElement nextButton= driver.findElement(nextButtonLocator);
//                    executeScrollingDown(driver, javascript, 4000);
//                    pauseWithTryCatch(500);
//                    actions.moveToElement(nextButton).click().perform();
//                    WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
//                    List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
//                    String lottoResultParseString= String.valueOf(lottoResult);
//                    String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
//                    int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
//                    for (index=0; index<lottoResultParseInt; index++) {
//                        if (index== lottoResultParseInt - 1) {
//                            childResultsElements.get(index).click();
//                            break;
//                        }
//                    }
//                }
//                break;
                clickNextButton: for (int index=1; index<5; index++) {
                    WebElement nextButton= driver.findElement(nextButtonLocator);
                    executeScrollingDown(driver, javascript, 6000);
                    executeScrollingDown(driver, javascript, 6000);
                    pauseWithTryCatch(500);
                    actions.moveToElement(nextButton).click().perform();
                    if (index==4) {
                        WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                        List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                        if (lottoResult<=89) {
                            String lottoResultParseString= String.valueOf(lottoResult);
                            String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                            int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResultParseInt-1).getText().toUpperCase());
                            childResultsElements.get(lottoResultParseInt-1).click();
                        }
                        else if (lottoResult>=90) {
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResult-81).getText().toUpperCase());
                            actions.moveToElement(childResultsElements.get(lottoResult-81)).click().perform();
                        }
                        break clickNextButton;
                    }
                }
                break;
            case 6:     /*      101->120      */
                System.out.println("---//-----##-------the sixth page------//-----##---".toUpperCase());
                clickNextButton: for (int index=1; index<=6; index++) {
                    WebElement nextButton= driver.findElement(nextButtonLocator);
                    executeScrollingDown(driver, javascript, 3000);
                    pauseWithTryCatch(2000);
                    actions.moveToElement(nextButton).click().perform();
                    if (index==5) {
                        WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                        List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                        if (lottoResult<=109) {
                            String lottoResultParseString= String.valueOf(lottoResult);
                            String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                            int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResultParseInt-1).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            childResultsElements.get(lottoResultParseInt-1).click();
                        }
                        else if (lottoResult>=110) {
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResult-101).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            actions.moveToElement(childResultsElements.get(lottoResult-101)).click().perform();
                        }
                        break clickNextButton;
                    }
                }
                break;
            case 7:     /*      121->140      */
                System.out.println("---//-----##-------the seventh page------//-----##---".toUpperCase());
                clickNextButton: for (int index=1; index<=7; index++) {
                    WebElement nextButton= driver.findElement(nextButtonLocator);
                    executeScrollingDown(driver, javascript, 3000);
                    pauseWithTryCatch(2000);
                    actions.moveToElement(nextButton).click().perform();
                    if (index==6) {
                        WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                        List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                        if (lottoResult<=129) {
                            String lottoResultParseString= String.valueOf(lottoResult);
                            String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                            int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResultParseInt-1).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            childResultsElements.get(lottoResultParseInt-1).click();
                        }
                        else if (lottoResult>=130) {
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResult-121).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            actions.moveToElement(childResultsElements.get(lottoResult-121)).click().perform();
                        }
                        break clickNextButton;
                    }
                }
                break;
            case 8:     /*      141->160      */
                System.out.println("---//-----##-------the eighth page------//-----##---".toUpperCase());
                clickNextButton: for (int index=1; index<=8; index++) {
                    WebElement nextButton= driver.findElement(nextButtonLocator);
                    executeScrollingDown(driver, javascript, 3000);
                    pauseWithTryCatch(2000);
                    actions.moveToElement(nextButton).click().perform();
                    if (index==7) {
                        WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                        List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                        if (lottoResult<=149) {
                            String lottoResultParseString= String.valueOf(lottoResult);
                            String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                            int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResultParseInt-1).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            childResultsElements.get(lottoResultParseInt-1).click();
                        }
                        else if (lottoResult>=150) {
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResult-141).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            actions.moveToElement(childResultsElements.get(lottoResult-141)).click().perform();
                        }
                        break clickNextButton;
                    }
                }
                break;
            case 9:     /*      161->180      */
                System.out.println("---//-----##-------the ninth page------//-----##---".toUpperCase());
/*                for (int index=1; index<9; index++) {
                    WebElement nextButton= driver.findElement(nextButtonLocator);
                    executeScrollingDown(driver, javascript, 4000);
                    pauseWithTryCatch(500);
                    actions.moveToElement(nextButton).click().perform();
                    WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                    List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                    String lottoResultParseString= String.valueOf(lottoResult);
                    String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                    int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                    for (index=0; index<lottoResultParseInt; index++) {
                        if (index== lottoResultParseInt - 1) {
                            childResultsElements.get(index).click();
                            break;
                        }
                    }
                }
                break;                                                                                                  */
                clickNextButton: for (int index=1; index<=9; index++) {
                    WebElement nextButton= driver.findElement(nextButtonLocator);
                    executeScrollingDown(driver, javascript, 3000);
                    pauseWithTryCatch(2000);
                    actions.moveToElement(nextButton).click().perform();
                    if (index==8) {
                        WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                        List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                        if (lottoResult<=169) {
                            String lottoResultParseString= String.valueOf(lottoResult);
                            String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                            int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResultParseInt-1).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            childResultsElements.get(lottoResultParseInt-1).click();
                        }
                        else if (lottoResult>=170) {
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResult-161).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            actions.moveToElement(childResultsElements.get(lottoResult-161)).click().perform();
                        }
                        break clickNextButton;
                    }
                }
                break;
            case 10:     /*      181->200      */
                System.out.println("---//-----##-------the tenth page------//-----##---".toUpperCase());
//                for (int index=1; index<10; index++) {
//                    WebElement nextButton= driver.findElement(nextButtonLocator);
//                    executeScrollingDown(driver, javascript, 4000);
//                    pauseWithTryCatch(500);
//                    actions.moveToElement(nextButton).click().perform();
//                    WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
//                    List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
//                    String lottoResultParseString= String.valueOf(lottoResult);
//                    String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
//                    int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
//                    for (index=0; index<lottoResultParseInt; index++) {
//                        if (index== lottoResultParseInt - 1) {
//                            childResultsElements.get(index).click();
//                            break;
//                        }
//                    }
//                }
//                break;
            clickNextButton: for (int index=1; index<=10; index++) {
                WebElement nextButton= driver.findElement(nextButtonLocator);
                executeScrollingDown(driver, javascript, 3000);
                pauseWithTryCatch(2000);
                actions.moveToElement(nextButton).click().perform();
                if (index==9) {
                    WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                    List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                    if (lottoResult<=189) {
                        String lottoResultParseString= String.valueOf(lottoResult);
                        String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                        int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                        System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResultParseInt-1).getText().toUpperCase());
                        pauseWithTryCatch(1000);
                        childResultsElements.get(lottoResultParseInt-1).click();
                    }
                    else if (lottoResult>=190) {
                        System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResult-181).getText().toUpperCase());
                        pauseWithTryCatch(1000);
                        actions.moveToElement(childResultsElements.get(lottoResult-181)).click().perform();
                    }
                    break clickNextButton;
                }
            }
            break;
            case 11:     /*      201->220      */
                System.out.println("---//-----##-------the eleventh page------//-----##---".toUpperCase());
                /*for (int index=1; index<11; index++) {
                    WebElement nextButton= driver.findElement(nextButtonLocator);
                    executeScrollingDown(driver, javascript, 4000);
                    pauseWithTryCatch(500);
                    actions.moveToElement(nextButton).click().perform();
                    WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                    List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                    String lottoResultParseString= String.valueOf(lottoResult);
                    String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                    int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                    for (index=0; index<lottoResultParseInt; index++) {
                        if (index== lottoResultParseInt - 1) {
                            childResultsElements.get(index).click();
                            break;
                        }
                    }
                }
                break;*/
            clickNextButton: for (int index=1; index<=11; index++) {
                WebElement nextButton= driver.findElement(nextButtonLocator);
                executeScrollingDown(driver, javascript, 3000);
                pauseWithTryCatch(2000);
                actions.moveToElement(nextButton).click().perform();
                if (index==10) {
                    WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                    List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                    if (lottoResult<=209) {
                        String lottoResultParseString= String.valueOf(lottoResult);
                        String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                        int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                        System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResultParseInt-1).getText().toUpperCase());
                        pauseWithTryCatch(1000);
                        childResultsElements.get(lottoResultParseInt-1).click();
                    }
                    else if (lottoResult>=210) {
                        System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResult-201).getText().toUpperCase());
                        pauseWithTryCatch(1000);
                        actions.moveToElement(childResultsElements.get(lottoResult-201)).click().perform();
                    }
                    break clickNextButton;
                }
            }
            break;
            case 12:     /*      221->240      */
                System.out.println("---//-----##-------the twelfth page------//-----##---".toUpperCase());
            /*    for (int index=1; index<12; index++) {
                    WebElement nextButton= driver.findElement(nextButtonLocator);
                    executeScrollingDown(driver, javascript, 4000);
                    pauseWithTryCatch(500);
                    actions.moveToElement(nextButton).click().perform();
                    WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                    List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                    String lottoResultParseString= String.valueOf(lottoResult);
                    String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                    int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                    for (index=0; index<lottoResultParseInt; index++) {
                        if (index== lottoResultParseInt - 1) {
                            childResultsElements.get(index).click();
                            break;
                        }
                    }
                }
                break;                                                                                              */
            clickNextButton: for (int index=1; index<=12; index++) {
                WebElement nextButton= driver.findElement(nextButtonLocator);
                executeScrollingDown(driver, javascript, 3000);
                pauseWithTryCatch(2000);
                actions.moveToElement(nextButton).click().perform();
                if (index==11) {
                    WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                    List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                    if (lottoResult<=229) {
                        String lottoResultParseString= String.valueOf(lottoResult);
                        String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                        int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                        System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResultParseInt-1).getText().toUpperCase());
                        pauseWithTryCatch(1000);
                        childResultsElements.get(lottoResultParseInt-1).click();
                    }
                    else if (lottoResult>=230) {
                        System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResult-221).getText().toUpperCase());
                        pauseWithTryCatch(1000);
                        actions.moveToElement(childResultsElements.get(lottoResult-221)).click().perform();
                    }
                    break clickNextButton;
                }
            }
            break;
            case 13:     /*      241->260      */
                System.out.println("---//-----##-------the thirteenth page------//-----##---".toUpperCase());
//                for (int index=1; index<13; index++) {
//                    WebElement nextButton= driver.findElement(nextButtonLocator);
//                    executeScrollingDown(driver, javascript, 4000);
//                    pauseWithTryCatch(500);
//                    actions.moveToElement(nextButton).click().perform();
//                    WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
//                    List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
//                    String lottoResultParseString= String.valueOf(lottoResult);
//                    String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
//                    int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
//                    for (index=0; index<lottoResultParseInt; index++) {
//                        if (index== lottoResultParseInt - 1) {
//                            childResultsElements.get(index).click();
//                            break;
//                        }
//                    }
//                }
//                break;
                clickNextButton: for (int index=1; index<=13; index++) {
                    WebElement nextButton= driver.findElement(nextButtonLocator);
                    executeScrollingDown(driver, javascript, 3000);
                    pauseWithTryCatch(2000);
                    actions.moveToElement(nextButton).click().perform();
                    if (index==12) {
                        WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                        List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                        if (lottoResult<=249) {
                            String lottoResultParseString= String.valueOf(lottoResult);
                            String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                            int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResultParseInt-1).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            childResultsElements.get(lottoResultParseInt-1).click();
                        }
                        else if (lottoResult>=250) {
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResult-241).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            actions.moveToElement(childResultsElements.get(lottoResult-241)).click().perform();
                        }
                        break clickNextButton;
                    }
                }
                break;
            case 14:     /*      261->280      */
                System.out.println("---//-----##-------the fourteenth page------//-----##---".toUpperCase());
                                clickNextButton: for (int index=1; index<=14; index++) {
                                    WebElement nextButton= driver.findElement(nextButtonLocator);
                                    executeScrollingDown(driver, javascript, 3000);
                                    pauseWithTryCatch(2000);
                                    actions.moveToElement(nextButton).click().perform();
                                    if (index==13) {
                                        WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                                        List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                                        if (lottoResult<=269) {
                                            String lottoResultParseString= String.valueOf(lottoResult);
                                            String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                                            int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResultParseInt-1).getText().toUpperCase());
                                            pauseWithTryCatch(1000);
                                            childResultsElements.get(lottoResultParseInt-1).click();
                                        }
                                        else if (lottoResult>=270) {
                                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResult-261).getText().toUpperCase());
                                            pauseWithTryCatch(1000);
                                            actions.moveToElement(childResultsElements.get(lottoResult-261)).click().perform();
                                        }
                                        break clickNextButton;
                                    }
                                }
                                break;
            case 15:     /*      281->300      */
                System.out.println("---//-----##-------the fifteenth page------//-----##---".toUpperCase());
                clickNextButton: for (int index=1; index<=15; index++) {
                    WebElement nextButton= driver.findElement(nextButtonLocator);
                    executeScrollingDown(driver, javascript, 3000);
                    pauseWithTryCatch(2000);
                    actions.moveToElement(nextButton).click().perform();
                    if (index==14) {
                        WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                        List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                        if (lottoResult<=289) {
                            String lottoResultParseString= String.valueOf(lottoResult);
                            String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                            int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResultParseInt-1).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            childResultsElements.get(lottoResultParseInt-1).click();
                        }
                        else if (lottoResult>=290) {
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResult-281).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            actions.moveToElement(childResultsElements.get(lottoResult-281)).click().perform();
                        }
                        break clickNextButton;
                    }
                }
                break;
            case 16:     /*      301->320      */
                System.out.println("---//-----##-------the sixteenth page------//-----##---".toUpperCase());
/*                for (int index=1; index<16; index++) {
                    WebElement nextButton= driver.findElement(nextButtonLocator);
                    executeScrollingDown(driver, javascript, 4000);
                    pauseWithTryCatch(500);
                    actions.moveToElement(nextButton).click().perform();
                    WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                    List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                    String lottoResultParseString= String.valueOf(lottoResult);
                    String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                    int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                    for (index=0; index<lottoResultParseInt; index++) {
                        if (index== lottoResultParseInt - 1) {
                            childResultsElements.get(index).click();
                            break;
                        }
                    }
                }
                break;                                                                                                                                                                                              */
                clickNextButton: for (int index=1; index<=16; index++) {
                    WebElement nextButton= driver.findElement(nextButtonLocator);
                    executeScrollingDown(driver, javascript, 3000);
                    pauseWithTryCatch(2000);
                    actions.moveToElement(nextButton).click().perform();
                    if (index==15) {
                        WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                        List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                        if (lottoResult<=309) {
                            String lottoResultParseString= String.valueOf(lottoResult);
                            String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                            int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResultParseInt-1).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            childResultsElements.get(lottoResultParseInt-1).click();
                        }
                        else if (lottoResult>=310) {
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResult-301).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            actions.moveToElement(childResultsElements.get(lottoResult-301)).click().perform();
                        }
                        break clickNextButton;
                    }
                }
                break;
            case 17:     /*      321->340      */
                clickNextButton: for (int index=1; index<=17; index++) {
                    WebElement nextButton= driver.findElement(nextButtonLocator);
                    executeScrollingDown(driver, javascript, 3000);
                    pauseWithTryCatch(2000);
                    actions.moveToElement(nextButton).click().perform();
                    if (index==16) {
                        WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                        List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                        if (lottoResult<=329) {
                            String lottoResultParseString= String.valueOf(lottoResult);
                            String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                            int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResultParseInt-1).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            childResultsElements.get(lottoResultParseInt-1).click();
                        }
                        else if (lottoResult>=330) {
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResult-321).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            actions.moveToElement(childResultsElements.get(lottoResult-321)).click().perform();
                        }
                        break clickNextButton;
                    }
                }
                break;
            case 18:     /*      341->360      */
                System.out.println("---//-----##-------the eighteenth page------//-----##---".toUpperCase());
                clickNextButton: for (int index=1; index<=18; index++) {
                    WebElement nextButton= driver.findElement(nextButtonLocator);
                    executeScrollingDown(driver, javascript, 3000);
                    pauseWithTryCatch(2000);
                    actions.moveToElement(nextButton).click().perform();
                    if (index==17) {
                        WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                        List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                        if (lottoResult<=349) {
                            String lottoResultParseString= String.valueOf(lottoResult);
                            String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                            int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResultParseInt-1).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            childResultsElements.get(lottoResultParseInt-1).click();
                        }
                        else if (lottoResult>=350) {
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResult-341).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            actions.moveToElement(childResultsElements.get(lottoResult-341)).click().perform();
                        }
                        break clickNextButton;
                    }
                }
                break;
            case 19:     /*      361->369      */
                System.out.println("---//-----##-------the nineteenth page------//-----##---".toUpperCase());
                clickNextButton: for (int index=1; index<=19; index++) {
                    WebElement nextButton= driver.findElement(nextButtonLocator);
                    executeScrollingDown(driver, javascript, 3000);
                    pauseWithTryCatch(2000);
                    actions.moveToElement(nextButton).click().perform();
                    if (index==18) {
                        WebElement listResultsElement= driver.findElement(By.xpath(PAR_LIST_PRODUCT_LOCATOR));
                        List<WebElement> childResultsElements= listResultsElement.findElements(By.xpath(CHI_LIST_PRODUCT_NAME_LOCATOR));
                        if (lottoResult<=369) {
                            String lottoResultParseString= String.valueOf(lottoResult);
                            String lastLottoResultChar= lottoResultParseString.substring(lottoResultParseString.length()-1);
                            int lottoResultParseInt= Integer.parseInt(lastLottoResultChar);
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResultParseInt-1).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            childResultsElements.get(lottoResultParseInt-1).click();
                        }
                        else if (lottoResult>=370) {
                            System.out.println("\n"+ "Product name: "+ childResultsElements.get(lottoResult-361).getText().toUpperCase());
                            pauseWithTryCatch(1000);
                            actions.moveToElement(childResultsElements.get(lottoResult-361)).click().perform();
                        }
                        break clickNextButton;
                    }
                }
                break;
        }
    }
}
