import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

WebUI.openBrowser('')
WebUI.maximizeWindow()

// Task 3 — login via custom keyword (PDF credentials).
CustomKeywords.'keywords.CommonActions.loginAs'('standard_user', 'secret_sauce')

// Required: inventory page visible after login.
def TO_PRODUCTS = findTestObject('Object Repository/Page_Swag Labs/span_Products')
WebUI.verifyElementPresent(TO_PRODUCTS, 10, FailureHandling.STOP_ON_FAILURE)

// Bonus: add backpack on shelf; Remove button confirms add on inventory page.
CustomKeywords.'keywords.CommonActions.addItemToCartBackpack'()

TestObject removeBackpack = new TestObject('btn_remove_sauce_labs_backpack')
removeBackpack.addProperty('xpath', ConditionType.EQUALS, '//button[@data-test="remove-sauce-labs-backpack"]')
WebUI.verifyElementPresent(removeBackpack, 10, FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()