package keywords

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import internal.GlobalVariable
import java.awt.Robot
import java.awt.event.KeyEvent

class CommonActions {

	// Workaround: Chrome password-manager dialog is not a DOM element (ENTER dismisses when shown).
	private void fecharPopupSenhaChromeSeExistir() {
		try {
			Robot robot = new Robot()
			Thread.sleep(1000)
			robot.keyPress(KeyEvent.VK_ENTER)
			robot.keyRelease(KeyEvent.VK_ENTER)
		} catch (Exception ignored) {
		}
	}

	// Reusable login (Task 3): navigate, fill credentials, submit, optional Chrome popup handling.
	@Keyword
	def loginAs(String username, String password) {
		def TO_USER  = findTestObject('Object Repository/Page_Swag Labs/input_Username')
		def TO_PASS  = findTestObject('Object Repository/Page_Swag Labs/input_Password')
		def TO_LOGIN = findTestObject('Object Repository/Page_Swag Labs/input_login-button')

		WebUI.navigateToUrl(GlobalVariable.BASE_URL as String)
		WebUI.setText(TO_USER, username)
		WebUI.setText(TO_PASS, password)
		WebUI.click(TO_LOGIN)

		fecharPopupSenhaChromeSeExistir()
	}

	// Bonus (Task 3): add Sauce Labs Backpack from the inventory page.
	@Keyword
	def addItemToCartBackpack() {
		TestObject addBackpack = new TestObject('addToCart_SauceLabsBackpack')
		addBackpack.addProperty('xpath', ConditionType.EQUALS, '//button[@id="add-to-cart-sauce-labs-backpack"]')

		WebUI.waitForElementClickable(addBackpack, 10, FailureHandling.STOP_ON_FAILURE)
		WebUI.click(addBackpack)
	}
}