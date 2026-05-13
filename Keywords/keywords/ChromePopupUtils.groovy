package keywords

import com.kms.katalon.core.annotation.Keyword
import java.awt.Robot
import java.awt.event.KeyEvent

class ChromePopupUtils {

	// Chrome native password UI is not in the DOM; brief delay then ENTER if dialog is shown.
	@Keyword
	def fecharPopupSenhaChrome() {
		try {
			Robot robot = new Robot()

			Thread.sleep(1000)

			robot.keyPress(KeyEvent.VK_ENTER)
			robot.keyRelease(KeyEvent.VK_ENTER)

			println('Popup do Chrome fechado com ENTER.')
		} catch (Exception e) {
			println('Nenhum popup do Chrome foi fechado ou ocorreu erro: ' + e.getMessage())
		}
	}
}