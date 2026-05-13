# SauceDemo_Assessment

Katalon Studio assessment project for [Sauce Demo](https://www.saucedemo.com): recorded login, Groovy cart flow, custom keywords, and organized test suites.

## Prerequisites

- Katalon Studio (Web UI project)
- Google Chrome
- Stable internet access
- Katalon account (sign-in when prompted)

## Execution profile: `env_demo`

Groovy tests use **GlobalVariable** for environment data. Variables are defined in the **`env_demo`** profile:

| Variable        | Purpose              | Example value                      |
|-----------------|----------------------|------------------------------------|
| `BASE_URL`      | Application base URL | `https://www.saucedemo.com`        |
| `SAUCE_USER`    | Login username       | `standard_user`                    |
| `SAUCE_PASSWORD`| Login password       | `secret_sauce`                     |

**Important:** Before running **TC02**, **TC03**, or any suite that includes them, select profile **`env_demo`** in the Katalon toolbar (or set it as the default execution profile).  
If **`default`** is used instead, `GlobalVariable.BASE_URL` may be empty and Groovy tests will fail.

**TC01_Login_Valid** is record-and-playback and does not require `env_demo`.

Values should be entered **without** surrounding quotes in the profile editor.

## Test cases

| ID   | Name                     | Type        | Description |
|------|--------------------------|-------------|-------------|
| TC01 | `TC01_Login_Valid`       | Recorded    | Login via Web Recorder; post-login checkpoint on inventory |
| TC02 | `TC02_Add_To_Cart_Groovy`| Groovy      | Login, add Sauce Labs Backpack, open cart, assert 1 item and product name |
| TC03 | `TC03_Keyword_Login`     | Groovy      | Login via `CommonActions.loginAs`; verify inventory; bonus add-to-cart on shelf |

## Custom keywords

| Class               | Method                    | Purpose |
|---------------------|---------------------------|---------|
| `CommonActions`     | `loginAs(user, pass)`     | Reusable login (Task 3) |
| `CommonActions`     | `addItemToCartBackpack()` | Bonus: add backpack from inventory |
| `ChromePopupUtils`  | `fecharPopupSenhaChrome()`| Optional: dismiss Chrome native password dialog (ENTER) |

Package: `keywords` → `Keywords/keywords/`

## Test suites

| Suite / Collection      | Contents |
|-------------------------|----------|
| `TS_Smoke_Login`        | TC01 + TC03 |
| `TS_Cart_Flow`          | TC02 (+ TC03 optional) |
| `TSC_Full_Regression`   | Runs smoke then cart flow sequentially |

## How to run

1. Open project **SauceDemo_Assessment** in Katalon Studio.
2. Select execution profile **`env_demo`**.
3. Run **`TSC_Full_Regression`** (or individual test cases / suites).
4. Review results under **Reports**.

## Chrome password popup

If Chrome shows a native “change password” / breach warning after login:

- Disable password breach notifications in Chrome settings (recommended), and/or
- Rely on `ChromePopupUtils` / popup handling in keywords (environment-dependent).

## Project structure
