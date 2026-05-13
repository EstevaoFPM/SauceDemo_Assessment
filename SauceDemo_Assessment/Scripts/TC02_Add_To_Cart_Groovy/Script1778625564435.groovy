import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import internal.GlobalVariable

// Object Repository — login objects reused from TC01.
def TO_USER     = findTestObject('Object Repository/Page_Swag Labs/input_Username')
def TO_PASS     = findTestObject('Object Repository/Page_Swag Labs/input_Password')
def TO_LOGIN    = findTestObject('Object Repository/Page_Swag Labs/input_login-button')
def TO_PRODUCTS = findTestObject('Object Repository/Page_Swag Labs/span_Products')

// Inline objects — backpack button and cart line item name.
TestObject addBackpack = new TestObject('addToCart_SauceLabsBackpack')
addBackpack.addProperty('xpath', ConditionType.EQUALS, '//button[@id="add-to-cart-sauce-labs-backpack"]')

TestObject cartItemName = new TestObject('cartLineItemName')
cartItemName.addProperty('xpath', ConditionType.EQUALS, '//div[contains(@class,"cart_item")]//div[@class="inventory_item_name"]')

WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl(GlobalVariable.BASE_URL as String)

// Credentials from env_demo profile.
WebUI.setText(TO_USER, GlobalVariable.SAUCE_USER as String)
WebUI.setText(TO_PASS, GlobalVariable.SAUCE_PASSWORD as String)
WebUI.click(TO_LOGIN)

CustomKeywords.'keywords.ChromePopupUtils.fecharPopupSenhaChrome'()

// Sync: inventory loaded before add-to-cart.
WebUI.waitForElementPresent(TO_PRODUCTS, 10, FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementClickable(addBackpack, 10, FailureHandling.STOP_ON_FAILURE)
WebUI.click(addBackpack)

WebUI.navigateToUrl((GlobalVariable.BASE_URL as String) + '/cart.html')

// Task 2 assertions: one cart row and correct product name.
def driver = DriverFactory.getWebDriver()
def cartRows = driver.findElements(By.className('cart_item'))
WebUI.verifyEqual(cartRows.size(), 1, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementText(cartItemName, 'Sauce Labs Backpack', FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()