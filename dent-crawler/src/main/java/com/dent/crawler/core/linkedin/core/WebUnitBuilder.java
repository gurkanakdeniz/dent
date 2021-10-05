package com.dent.crawler.core.linkedin.core;

import static com.dent.crawler.infrastructure.common.util.UrlUtil.auth;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.CommandInfo;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dent.crawler.core.linkedin.model.AuthenticationModel;
import com.dent.crawler.core.proxy.core.ProxyClient;
import com.dent.crawler.core.proxy.core.ProxyClientBuilder;
import com.dent.crawler.infrastructure.common.core.AppContext;
import com.dent.crawler.infrastructure.common.model.Constant;
import com.dent.crawler.infrastructure.config.ApplicationConfig;
import com.dent.crawler.service.linkedin.ILinkedinService;
import com.dent.crawler.service.linkedin.model.AccountRequest;
import com.dent.crawler.service.linkedin.model.RequestUpdate;

@Component
public class WebUnitBuilder {

    private static ApplicationConfig applicationConfig;

    private final static Logger logger = LoggerFactory.getLogger(WebUnitBuilder.class);

    private static ILinkedinService linkedinService;

    private static WebUnit create() {
        return create(null);
    }

    private static WebUnit create(List<String> arguments) {
        System.setProperty(applicationConfig.getWebDriver(), applicationConfig.getWebDriverPath());

        ChromeOptions chromeOptions = new ChromeOptions();
        String binaryPath = applicationConfig.getWebDriverBinary();
        if (binaryPath != null) {
            chromeOptions.setBinary(binaryPath);
        }

        chromeOptions.addArguments(applicationConfig.getWebDriverArgs());
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        if (arguments != null && arguments.size() > 0) {
            chromeOptions.addArguments(arguments);
        }

        return new WebUnit(chromeOptions);
    }

    public WebUnitBuilder init() {
        if (WebUnitBuilder.linkedinService == null) {
            WebUnitBuilder.linkedinService = AppContext.getContext().getBean(ILinkedinService.class);
        }

        if (WebUnitBuilder.applicationConfig == null) {
            WebUnitBuilder.applicationConfig = AppContext.getContext().getBean(ApplicationConfig.class);
        }

        return this;
    }

    public WebUnitBuilder init(ILinkedinService linkedinService) {
        WebUnitBuilder.linkedinService = linkedinService;

        return this;
    }

    public WebUnit build() {
        return build(null, null, false, null);
    }

    public WebUnit build(boolean proxy) {
        return build(null, null, proxy, null);
    }

    public WebUnit build(boolean proxy, String url) {
        return build(null, null, proxy, url);
    }

    public WebUnit build(String username, String password, boolean proxy, String url) {
        WebUnit webUnit = null;
        if (proxy) {
            webUnit = buildWithProxy();
        } else {
            webUnit = buildDefault();
        }

        webUnit = checkAuth(webUnit);

        if (webUnit != null && StringUtils.isNotBlank(url)) {
            webUnit.navigate().to(url);
        }

        return webUnit;
    }

    private WebUnit buildDefault() {
        return create();
    }

    private WebUnit buildWithProxy() {
        ProxyClient proxyClient = ProxyClientBuilder.build();
        if (proxyClient == null) {
            logger.error("--- proxy not available continue default ---");
            return buildDefault();
        }

        // INFO:GA: driver not support socks auth
        // option 1 : http proxy
        // option 2 : non secure socks
        // Arrays.asList("--proxy-server=socks5://" + "174.76.48.233" + ":" + "4145");
        List<String> args = Arrays.asList(String.format("--proxy-server=%s://%s:%s", proxyClient.getType(),
                proxyClient.getProxyHost(), proxyClient.getProxyPort()));

        WebUnit webUnit = create(args);

        webUnit.proxy(proxyClient);
        return webUnit;
    }

    private WebUnit checkAuth(WebUnit webUnit) {
        String username = null;
        String password = null;

        AuthenticationModel linkedinAccount = linkedinService.getAccount(new AccountRequest()).getAuthenticationModel();
        do {
            if (linkedinAccount != null) {
                username = linkedinAccount.getEmail();
                password = linkedinAccount.getPassword();

                boolean account = prepareAccount(webUnit, linkedinAccount);
                if (account) {
                    logger.info("--- check auth true ---");
                    break;
                } else {
                    username = null;
                    password = null;
                    linkedinAccount = linkedinService.getAccount(new AccountRequest()).getAuthenticationModel();
                }
            }
        } while (linkedinAccount != null);

        if (username == null || password == null) {
            logger.error("--- not found active account ---");
        }

        webUnit.auth(username, password);
        return webUnit;
    }

    private boolean prepareAccount(WebUnit webUnit, AuthenticationModel linkedinAccount) {
        String username = null;
        String password = null;

        boolean flag = false;
        do {
            try {
                username = linkedinAccount.getEmail();
                password = linkedinAccount.getPassword();

                webUnit.navigate().to(auth());
                webUnit.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

                List<WebElement> elements = webUnit.findElementsByXPath("//*/form/section/p/a");
                if (elements.size() > 0) {
                    elements.get(0).click();
                }

                elements = webUnit.findElementsByName("session_key");
                if (elements.size() > 0) {
                    elements.get(0).sendKeys(username);
                } else {
                    throw new Exception("Username Element Empty");
                }

                elements = webUnit.findElementsByName("session_password");
                if (elements.size() > 0) {
                    elements.get(0).sendKeys(password);
                } else {
                    throw new Exception("Password Element Empty");
                }

                elements = webUnit.findElementsById("login-submit");
                if (elements.size() > 0) {
                    elements.get(0).click();
                } else {
                    throw new Exception("Submit Element Empty");
                }

                webUnit.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

                logger.info("--- home page --- \n" + webUnit.getPageSource());

                String currentUrl = webUnit.getCurrentUrl();
                if (!StringUtils.containsIgnoreCase(currentUrl, Constant.FEED)) {
                    throw new Exception("Home Page Error");
                }

                return true;
            } catch (Exception e) {
                logger.error("--- prepare account ---", e);
                try {
                    webUnit.quit();
                } catch (Exception ex) {
                    // ignore
                }

                try {
                    webUnit = buildWithProxy();
                } catch (Exception ex) {
                    // ignore
                }
            }
        } while (flag = !flag);

        if (linkedinAccount != null && linkedinService != null) {
            linkedinService.update(new RequestUpdate(linkedinAccount.getEmail(), Boolean.FALSE));
        }

        return false;
    }

    public WebUnit failover(WebUnit webUnit) {
        return failover(webUnit, false);
    }

    public WebUnit failover(WebUnit webUnit, boolean checkProxy) {
        WebUnit response = this.build(checkProxy, webUnit.getCurrentUrl());
        return response != null ? response : webUnit;
    }

    // INFO:GA: only devs block not use
    public static void chrome() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        ChromeDriver chromeDriver = null;

        try {
            try {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--proxy-server=socks5://" + "174.76.48.233" + ":" + "4145");
                options.addArguments("--headless");
                options.addArguments("--no-sandbox");
                chromeDriver = new ChromeDriver(options);
                chromeDriver.quit();
            } catch (Exception e) {
                e.printStackTrace();
                chromeDriver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // INFO:GA: only devs block not use
    public static void remote() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        RemoteWebDriver remoteDriver = null;
        ChromeDriver chromeDriver = null;

        try {
            try {

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--proxy-server=socks5://" + "174.76.48.233" + ":" + "4145");
                options.addArguments("--headless");
                options.addArguments("--no-sandbox");
                chromeDriver = new ChromeDriver(options);
                chromeDriver.quit();
                chromeDriver.getCommandExecutor();

                URL addressOfRemoteServer = null;
                Map<String, CommandInfo> additionalCommands = null;
                CommandExecutor commandExc = new HttpCommandExecutor(additionalCommands, addressOfRemoteServer);
                remoteDriver = new RemoteWebDriver(commandExc, chromeDriver.getCapabilities());

                remoteDriver.quit();

            } catch (Exception e) {
                e.printStackTrace();
                remoteDriver.quit();
                chromeDriver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // INFO:GA: only devs block not use
    public static void firefox() {
        FirefoxDriver firefoxDriver = null;

        try {
            try {
                System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");

                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("network.proxy.type", 1);
                profile.setPreference("network.proxy.socks", "174.76.48.233");
                profile.setPreference("network.proxy.socks_port", 4145);

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("--no-sandbox");
                firefoxOptions.setProfile(profile);
                firefoxDriver = new FirefoxDriver(firefoxOptions);

                System.out.println("");
                firefoxDriver.quit();
            } catch (Exception e) {
                e.printStackTrace();
                firefoxDriver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
