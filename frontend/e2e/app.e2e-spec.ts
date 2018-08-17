import { AppPage } from './app.po';
import { browser, by, element } from 'protractor';
import { protractor } from 'protractor/built/ptor';
import { async } from '@angular/core/testing';

describe('movie-cruiser-app-frontend App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('MovieCruiserAppFrontend');  	
  });

  it('should be redirected to /login url on load', () => {
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be redirected to register route', () => {
    browser.element(by.css('.register-button')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should be able to register user', () => {
    browser.element(by.id('firstname')).sendKeys('teste2e');
    browser.element(by.id('lastname')).sendKeys('teste2e');
    browser.element(by.id('userId')).sendKeys('teste2e');
    browser.element(by.id('password')).sendKeys('teste2e');
    browser.element(by.css('.register-user')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be able to login user and navigate to popular movies', () => {
    browser.element(by.id('userId')).sendKeys('teste2e');
    browser.element(by.id('password')).sendKeys('teste2e');
    browser.element(by.css('.login-user')).click();
    expect(browser.getCurrentUrl()).toContain('/movies/popular');
  });

  it('should search movie', () => {
    browser.element(by.css('.search-button')).click();
    expect(browser.getCurrentUrl()).toContain('/movies/search');
    browser.element(by.id('search-button-input')).sendKeys('Super');
    browser.element(by.id('search-button-input')).sendKeys(protractor.Key.ENTER);

    const count = element.all(by.css('.movieTitle')).count();
    expect(count).toBe(20);
    for(let i = 0; i < 20; i++) {
      expect(element.all(by.css('.movieTitle')).get(i).getText()).toContain('Super');
    }
  });

  it('adding movie to watch list', async () => {
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    browser.element(by.css('.topRated')).click();
    const count = element.all(by.css('.card')).count();
    expect(count).toBe(20);
    element.all(by.css('.card')).get(0).element(by.css('.addButton')).click();
    //element(by.css('.addButton')).click();
  })
});
