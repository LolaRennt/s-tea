package com.github.lmm.browser;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.github.lmm.core.ConfigParser;
import com.github.lmm.exception.ConfigWrongException;
import com.github.lmm.exception.NodeBrowserNotConnectException;
import com.opera.core.systems.OperaDriver;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;


/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午5:39
 * To change this template use File | Settings | File Templates.
 */
public enum Browser{
    PHANTOMJS(){
		@Override
		public PhantomJSDriver browser() {
            return new PhantomJSDriver(DesiredCapabilities.phantomjs());
        }

        @Override
		public RemoteWebDriver browser(URL url) {
            return new RemoteWebDriver(url,DesiredCapabilities.phantomjs());
        }

		@Override
		public RemoteWebDriver remoteBrowser() {
			if(ConfigParser.getPhantomJSNode()!=null&&ConfigParser.getPhantomJSNode()!=""){
				try {
					return new RemoteWebDriver(new URL(ConfigParser.getPhantomJSNode()),DesiredCapabilities.phantomjs());
				} catch (MalformedURLException e) {
					throw new NodeBrowserNotConnectException("在连接远程浏览器的时候出现了错误！", e);
				}
			}else{
				throw new ConfigWrongException("配置文件读取远程浏览器node地址的时候出现了错误，请检查配置。");
			}
		}
    },
    IE(){
        public InternetExplorerDriver browser(){
            return new InternetExplorerDriver();
        }
        public RemoteWebDriver browser(URL url){
            return new RemoteWebDriver(url,DesiredCapabilities.internetExplorer());
        }
        public RemoteWebDriver remoteBrowser() {
			if(ConfigParser.getIENode()!=null&&ConfigParser.getIENode()!=""){
				try {
					return new RemoteWebDriver(new URL(ConfigParser.getIENode()),DesiredCapabilities.internetExplorer());
				} catch (MalformedURLException e) {
					throw new NodeBrowserNotConnectException("在连接远程浏览器的时候出现了错误！", e);
				}
			}else{
				throw new ConfigWrongException("配置文件读取远程浏览器node地址的时候出现了错误，请检查配置。");
			}
		}
    },
    FIREFOX(){
        public FirefoxDriver browser(){
              return new FirefoxDriver();
          }
        public RemoteWebDriver browser(URL url){
            return new RemoteWebDriver(url,DesiredCapabilities.firefox());
        }
        public RemoteWebDriver remoteBrowser() {
			if(ConfigParser.getFirefoxNode()!=null&&ConfigParser.getFirefoxNode()!=""){
				try {
					return new RemoteWebDriver(new URL(ConfigParser.getFirefoxNode()),DesiredCapabilities.firefox());
				} catch (MalformedURLException e) {
					throw new NodeBrowserNotConnectException("在连接远程浏览器的时候出现了错误！", e);
				}
			}else{
				throw new ConfigWrongException("配置文件读取远程浏览器node地址的时候出现了错误，请检查配置。");
			}
		}
    },
    CHROME(){
        public ChromeDriver browser(){
                  return new ChromeDriver();
        }
        public RemoteWebDriver browser(URL url){
            return new RemoteWebDriver(url,DesiredCapabilities.chrome());
        }
        public RemoteWebDriver remoteBrowser() {
			if(ConfigParser.getChromeNode()!=null&&ConfigParser.getChromeNode()!=""){
				try {
					return new RemoteWebDriver(new URL(ConfigParser.getChromeNode()),DesiredCapabilities.chrome());
				} catch (MalformedURLException e) {
					throw new NodeBrowserNotConnectException("在连接远程浏览器的时候出现了错误！", e);
				}
			}else{
				throw new ConfigWrongException("配置文件读取远程浏览器node地址的时候出现了错误，请检查配置。");
			}
		}
    },
    SAFARI(){
        public SafariDriver browser(){
            return new SafariDriver();
        }
        public RemoteWebDriver browser(URL url){
            return new RemoteWebDriver(url,DesiredCapabilities.safari());
        }
        public RemoteWebDriver remoteBrowser() {
			if(ConfigParser.getSafariNode()!=null&&ConfigParser.getSafariNode()!=""){
				try {
					return new RemoteWebDriver(new URL(ConfigParser.getSafariNode()),DesiredCapabilities.safari());
				} catch (MalformedURLException e) {
					throw new NodeBrowserNotConnectException("在连接远程浏览器的时候出现了错误！", e);
				}
			}else{
				throw new ConfigWrongException("配置文件读取远程浏览器node地址的时候出现了错误，请检查配置。");
			}
		}
    },
    OPERA(){
        public OperaDriver browser(){
            return new OperaDriver();
        }
        public RemoteWebDriver browser(URL url){
            return new RemoteWebDriver(url,DesiredCapabilities.opera());
        }
        public RemoteWebDriver remoteBrowser() {
			if(ConfigParser.getOperaNode()!=null&&ConfigParser.getOperaNode()!=""){
				try {
					return new RemoteWebDriver(new URL(ConfigParser.getOperaNode()),DesiredCapabilities.opera());
				} catch (MalformedURLException e) {
					throw new NodeBrowserNotConnectException("在连接远程浏览器的时候出现了错误！", e);
				}
			}else{
				throw new ConfigWrongException("配置文件读取远程浏览器node地址的时候出现了错误，请检查配置。");
			}
		}
    },
    HTMLUNIT(){
        public HtmlUnitDriver browser(){
            HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
            driver.setJavascriptEnabled(true);
            return driver;
        }
        public RemoteWebDriver browser(URL url){
            RemoteWebDriver driver= new RemoteWebDriver(url,DesiredCapabilities.htmlUnit(),new DesiredCapabilities(){
                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
                public boolean isJavascriptEnabled() {
                    return true;
                }
            });
            return driver;
        }
        public RemoteWebDriver remoteBrowser() {
			if(ConfigParser.getHtmlUnitNode()!=null&&ConfigParser.getHtmlUnitNode()!=""){
				try {
					return new RemoteWebDriver(new URL(ConfigParser.getHtmlUnitNode()),DesiredCapabilities.htmlUnit());
				} catch (MalformedURLException e) {
					throw new NodeBrowserNotConnectException("在连接远程浏览器的时候出现了错误！", e);
				}
			}else{
				throw new ConfigWrongException("配置文件读取远程浏览器node地址的时候出现了错误，请检查配置。");
			}
		}
    },
    HTMLUNIT_FIRFOR_3_6(){
        @Override
		public HtmlUnitDriver browser() {
            HtmlUnitDriver driver = new HtmlUnitDriver((BrowserVersion.FIREFOX_3_6));
            driver.setJavascriptEnabled(true);
            return driver;
        }

        @Override
		public RemoteWebDriver browser(URL url) {
            RemoteWebDriver driver= new RemoteWebDriver(url,DesiredCapabilities.htmlUnit(),new DesiredCapabilities(){
                @Override
                public boolean isJavascriptEnabled() {
                    return true;
                }
            });
            return driver;
        }


		@Override
		public RemoteWebDriver remoteBrowser() {
			// TODO Auto-generated method stub
			return null;
		}
    },
    HTMLUNIT_FIRFOX_10(){
        @Override
		public HtmlUnitDriver browser() {
            HtmlUnitDriver driver = new HtmlUnitDriver((BrowserVersion.FIREFOX_10));
            driver.setJavascriptEnabled(true);
            return driver;
        }

        @Override
		public RemoteWebDriver browser(URL url) {
            RemoteWebDriver driver= new RemoteWebDriver(url,DesiredCapabilities.htmlUnit(),new DesiredCapabilities(){
                @Override
                public boolean isJavascriptEnabled() {
                    return true;
                }
            });
            return driver;
        }


		@Override
		public RemoteWebDriver remoteBrowser() {
			// TODO Auto-generated method stub
			return null;
		}
    },
    HTMLUNIT_CHROME(){
        @Override
		public HtmlUnitDriver browser() {
            HtmlUnitDriver driver = new HtmlUnitDriver((BrowserVersion.CHROME));
            driver.setJavascriptEnabled(true);
            return driver;
        }

        @Override
		public RemoteWebDriver browser(URL url) {
            RemoteWebDriver driver= new RemoteWebDriver(url,DesiredCapabilities.htmlUnit(),new DesiredCapabilities(){
                @Override
                public boolean isJavascriptEnabled() {
                    return true;
                }
            });
            return driver;
        }


		@Override
		public RemoteWebDriver remoteBrowser() {
			// TODO Auto-generated method stub
			return null;
		}
    },
    HTMLUNIT_CHROME_16(){
        @Override
		public HtmlUnitDriver browser() {
            HtmlUnitDriver driver = new HtmlUnitDriver((BrowserVersion.CHROME_16));
            driver.setJavascriptEnabled(true);
            return driver;
        }

        @Override
		public RemoteWebDriver browser(URL url) {
            RemoteWebDriver driver= new RemoteWebDriver(url,DesiredCapabilities.htmlUnit(),new DesiredCapabilities(){
                @Override
                public boolean isJavascriptEnabled() {
                    return true;
                }
            });
            return driver;
        }

		@Override
		public RemoteWebDriver remoteBrowser() {
			// TODO Auto-generated method stub
			return null;
		}
    },
    HTMLUNIT_INTERNET_EXPLORER_6{
        @Override
		public HtmlUnitDriver browser() {
            HtmlUnitDriver driver = new HtmlUnitDriver((BrowserVersion.INTERNET_EXPLORER_6));
            driver.setJavascriptEnabled(true);
            return driver;
        }

        @Override
		public RemoteWebDriver browser(URL url) {
            RemoteWebDriver driver= new RemoteWebDriver(url,DesiredCapabilities.htmlUnit(),new DesiredCapabilities(){
                @Override
                public boolean isJavascriptEnabled() {
                    return true;
                }
            });
            return driver;
        }
		@Override
		public RemoteWebDriver remoteBrowser() {
			// TODO Auto-generated method stub
			return null;
		}
    },
    HTMLUNIT_INTERNET_EXPLORER_7{
        @Override
		public HtmlUnitDriver browser() {
            HtmlUnitDriver driver = new HtmlUnitDriver((BrowserVersion.INTERNET_EXPLORER_7));
            driver.setJavascriptEnabled(true);
            return driver;
        }

        @Override
		public RemoteWebDriver browser(URL url) {
            RemoteWebDriver driver= new RemoteWebDriver(url,DesiredCapabilities.htmlUnit(),new DesiredCapabilities(){
                @Override
                public boolean isJavascriptEnabled() {
                    return true;
                }
            });
            return driver;
        }


		@Override
		public RemoteWebDriver remoteBrowser() {
			// TODO Auto-generated method stub
			return null;
		}
    },
    HTMLUNIT_INTERNET_EXPLORER_8{
        @Override
		public HtmlUnitDriver browser() {
            HtmlUnitDriver driver = new HtmlUnitDriver((BrowserVersion.INTERNET_EXPLORER_8));
            driver.setJavascriptEnabled(true);
            return driver;
        }

        @Override
		public RemoteWebDriver browser(URL url) {
            RemoteWebDriver driver= new RemoteWebDriver(url,DesiredCapabilities.htmlUnit(),new DesiredCapabilities(){
                @Override
                public boolean isJavascriptEnabled() {
                    return true;
                }
            });
            return driver;
        }

		@Override
		public RemoteWebDriver remoteBrowser() {
			// TODO Auto-generated method stub
			return null;
		}
    },
    HTMLUNIT_INTERNET_EXPLORER_9{
        @Override
		public HtmlUnitDriver browser() {
            HtmlUnitDriver driver = new HtmlUnitDriver((BrowserVersion.INTERNET_EXPLORER_9));
            driver.setJavascriptEnabled(true);
            return driver;
        }

        @Override
		public RemoteWebDriver browser(URL url) {
            RemoteWebDriver driver= new RemoteWebDriver(url,DesiredCapabilities.htmlUnit(),new DesiredCapabilities(){
                @Override
                public boolean isJavascriptEnabled() {
                    return true;
                }
            });
            return driver;
        }

		@Override
		public RemoteWebDriver remoteBrowser() {
			// TODO Auto-generated method stub
			return null;
		}
    };

    public abstract <T> T browser();

    public abstract RemoteWebDriver browser(URL url);
    
    public abstract RemoteWebDriver remoteBrowser();

}
