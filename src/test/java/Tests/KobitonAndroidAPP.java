package Tests;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;

import io.appium.java_client.android.AndroidDriver;
import pureQuantumH8.pureConfig;
import pureQuantumH8.pureConfig.pureCapability;
import pureQuantumH8.pureCore.elementMethod;
import pureQuantumH8.pureCore;
import pureQuantumH8.pureDrivers;
import pureQuantumH8.pureElement;
import pureQuantumH8.pureDrivers.pureDriverDetails;

public class KobitonAndroidAPP{

	public static void main( String[] args ){
		
		List<pureCapability> driverCaps = pureConfig.loadCapabilitiesFromFile( "KobitonAndroidAPP.json" );		
		
		pureDriverDetails myDriver = pureDrivers.newPureDriver( "kobitonandroidapp", driverCaps, false );
		myDriver.startDriver();
		myDriver.waitUntilStarted();
		
		pureElement number3 =   new pureElement( "Number 3", "button", elementMethod.xpath , "//android.widget.Button[@text='3']", 0, null );
		pureElement number5 =   new pureElement( "Number 5", "button", elementMethod.xpath , "//android.widget.Button[@text='5']", 0, null );
		pureElement addition =  new pureElement( "Addition", "button", elementMethod.xpath , "//android.widget.Button[@text='+']", 0, null );
		pureElement equal =     new pureElement( "ask for result (=)", "button", elementMethod.xpath , "//android.widget.Button[@text='=']", 0, null );
		pureElement getResult = new pureElement( "get Result", "text area", elementMethod.xpath, "//*[@resource-id='calculator.innovit.com.calculatrice:id/MainCalculator']//android.widget.TextView[1]", 0, null, 0 );
		
		number5.click();
		addition.click();
		number3.click();
		equal.click();
		if ( getResult.getText().equals( "8" ) == true ) {
			System.out.println( "5 + 3 = 8 obtained successfully" );
		}
		pureCore.sleep( 5000 );
		
		myDriver.quit();
	}
	
	
	
}
