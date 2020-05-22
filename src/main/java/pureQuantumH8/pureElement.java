package pureQuantumH8;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import pureQuantumH8.pureDrivers.pureDriverDetails;

// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;

public class pureElement {

	public String            eName         = null;                    // Nom de l'element
	public String            eType         = null;                    // Type d'element
	public String            ePath         = null;                    // Le xPath ou definition de l'objet
	public int               eMethod       = -1;                      // le type de methode e utiliser pour rechercher l'objet ( pureCore.elementMethod.xxx )
	public int               refreshMethod = 0;                       // 0 = manual (no update once found), 1 = forceUpdate (rediscover on each call),
	                                                                  // 2 = logCat update (memorize logcat to check if update is required or not)
	public String            refrehCode    = null;                    // Last logcat when refreshed ( used with refreshMethod = 2)
	public pureDriverDetails driverToUse   = null;                    // Store the currentDriver when object is created so it's the driver that will be used for detection/discover/update
	public Object            theElement    = null;                    // The xElement (Web/Mobile/Android/IOS)
	
	public pureElement       parent        = null;                    // Used from pureElement.findElement when "Maintain real hierarchy" is set
	public int               driverLink    = -1;                      // if > -1 then represents the Id of the pureDriverDetails the object is linked to
	
	public pureElement( String elName, String elType, int elMethod, String elPath, int elRefreshMode, pureElement parent, int driverLink ) {
		this.eName = elName;
		this.eType = elType;
		this.ePath = elPath;
		this.eMethod = elMethod;
		this.refreshMethod = elRefreshMode;
		this.refrehCode = null;
		this.parent = parent;
		if ( driverLink > -1 ) {
			this.driverToUse = pureDrivers.getDriverDetails( driverLink );
		}else {
			this.driverToUse = null;
		}
		this.driverLink = driverLink;
	}

	public pureElement( String elName, String elType, int elMethod, String elPath, int elRefreshMode, pureElement parent ) {
		this.eName = elName;
		this.eType = elType;
		this.ePath = elPath;
		this.eMethod = elMethod;
		this.refreshMethod = elRefreshMode;
		this.refrehCode = null;
		this.parent = parent;
		this.driverToUse = null;
		this.driverLink = -1;
	}

	public pureElement( String elName, String elType, int elMethod, String elPath, int elRefreshMode ) {
		this.eName = elName;
		this.eType = elType;
		this.ePath = elPath;
		this.eMethod = elMethod;
		this.refreshMethod = elRefreshMode;
		this.refrehCode = null;
		this.parent = null;
		this.driverToUse = null;
		this.driverLink = -1;
	}
	
	public void setName( String newName ) {
		this.eName = newName;
	}

	public String getName() {
		return this.eName;
	}
	
	public void setType( String newType ) {
		this.eType = newType;
	}
	
	public String getType() {
		return this.eType;
	}
	
	public void setMethod( int newMethod ) {
		this.eMethod = newMethod;
		this.theElement = null;
	}
	
	public int getMethod() {
		return this.eMethod;
	}
	
	public void setPath( String newPath ) {
		this.ePath = newPath;
		this.theElement = null;
	}
	
	public String getPath() {
		return this.ePath;
	}
	
	public void setBy( int newMethod, String newPath ) {
		this.eMethod = newMethod;
		this.ePath = newPath;
		this.theElement = null;
	}

	public void setRefreshMode( int newRMode ) {
		this.refreshMethod = newRMode;
	}
	
	public int getRefreshMode() {
		return this.refreshMethod;
	}
	
	public void pureDriverDetailsToLink( pureDriverDetails newDriverToUse ) {
		this.driverToUse = newDriverToUse;
		this.driverLink = pureDrivers.getDriverDetailsID( newDriverToUse );
	}

	public int getDriverID() {
		return this.driverLink;
	}

	public Object getElement() {
		return this.theElement;
	}
	
	public void clearElement() {
		this.theElement = null;
	}

	
	
	// ************************************************************************************************************************ clear
	// WebElement              [0]  = public abstract void org.openqa.selenium.WebElement.clear()
	// AndroidElement          [31] = public void org.openqa.selenium.remote.RemoteWebElement.clear()
	// IOSElement              [30] = public void org.openqa.selenium.remote.RemoteWebElement.clear()
	// MobileElement           [30] = public void org.openqa.selenium.remote.RemoteWebElement.clear()
	public void clear() {
		this.refresh();        // Method to detect, update and centre the object on screen if existing.
		this.centerOnScreen(); // Method to put object in center of the screen if partially or not visible
		this.pureElementMethodCall( "clear" );
	}

	// ************************************************************************************************************************ clear
	// WebElement              [1]  = public abstract org.openqa.selenium.Point org.openqa.selenium.WebElement.getLocation()
	// AndroidElement          [32] = public org.openqa.selenium.Point org.openqa.selenium.remote.RemoteWebElement.getLocation()
	// IOSElement              [31] = public org.openqa.selenium.Point org.openqa.selenium.remote.RemoteWebElement.getLocation()
	// MobileElement           [31] = public org.openqa.selenium.Point org.openqa.selenium.remote.RemoteWebElement.getLocation()
	public org.openqa.selenium.Point getLocation(){
		this.refresh();        // Method to detect, update and centre the object on screen if existing.
		return ( org.openqa.selenium.Point )this.pureElementMethodCall( "getLocation" );
	}

	// ************************************************************************************************************************ clear
	// WebElement              [2]  = public abstract org.openqa.selenium.Dimension org.openqa.selenium.WebElement.getSize()
	// AndroidElement          [34] = public org.openqa.selenium.Dimension org.openqa.selenium.remote.RemoteWebElement.getSize()
	// IOSElement              [33] = public org.openqa.selenium.Dimension org.openqa.selenium.remote.RemoteWebElement.getSize()
	// MobileElement           [33] = public org.openqa.selenium.Dimension org.openqa.selenium.remote.RemoteWebElement.getSize()
	public org.openqa.selenium.Dimension getSize(){
		this.refresh();        // Method to detect, update and centre the object on screen if existing.
		return ( org.openqa.selenium.Dimension )this.pureElementMethodCall( "getSize" );
	}

	// ************************************************************************************************************************ clear
	// WebElement              [3]  = public abstract org.openqa.selenium.WebElement org.openqa.selenium.WebElement.findElement(org.openqa.selenium.By)
	// AndroidElement          [2]  = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidElement.findElement(org.openqa.selenium.By)
	// IOSElement              [3]  = public org.openqa.selenium.WebElement io.appium.java_client.ios.IOSElement.findElement(org.openqa.selenium.By)
	// MobileElement           [5]  = public java.util.List io.appium.java_client.MobileElement.findElements(org.openqa.selenium.By)
	public pureElement findElement( Object by ){
		this.refresh();        // Method to detect, update and centre the object on screen if existing.
		Object SourceObject = this.pureElementMethodCall( "findElement", by );
		if ( SourceObject != null ) {
			pureCore.peMethod myMethod = pureCore.peMethodFromBy( by.toString() );
			pureElement newPureElement = new pureElement( "The Name", "The Type", myMethod.peMethodid, myMethod.peSearch, pureCore.defRefreshMode, this );
			newPureElement.theElement = SourceObject;
			newPureElement.refrehCode = "0xFFFFFFFF";
			return newPureElement;
		}else {
			return null;
		}	
	}

	// ************************************************************************************************************************ clear
	// WebElement              [4]  = public abstract java.util.List org.openqa.selenium.WebElement.findElements(org.openqa.selenium.By)
	// AndroidElement          [16] = public java.util.List io.appium.java_client.MobileElement.findElements(org.openqa.selenium.By)
	// IOSElement              [15] = public java.util.List io.appium.java_client.MobileElement.findElements(org.openqa.selenium.By)
	// MobileElement           [4]  = public org.openqa.selenium.WebElement io.appium.java_client.MobileElement.findElement(org.openqa.selenium.By)
	public pureElements findElements( Object by ){
		this.refresh();        // Method to detect, update and centre the object on screen if existing.
		Object feedback = this.pureElementMethodCall( "findElements", by );
		List<Object> myObjectList = pureCore.CastObjecttoList( feedback	);
		if ( myObjectList != null ) {
			pureCore.peMethod myMethod = pureCore.peMethodFromBy( by.toString() );
			pureElements newPureElements = new pureElements( "-", "-", myMethod.peMethodid, myMethod.peSearch, pureCore.defRefreshMode, this );
			newPureElements.theElements = myObjectList;
			newPureElements.refrehCode = "0xFFFFFFFF";
			return newPureElements;
		}else {
			return null;
		}
	}
	
	// ************************************************************************************************************************ clear
	// WebElement              [5]  = public abstract void org.openqa.selenium.WebElement.click()
	// AndroidElement          [38] = public void org.openqa.selenium.remote.RemoteWebElement.click()
	// IOSElement              [37] = public void org.openqa.selenium.remote.RemoteWebElement.click()
	// MobileElement           [37] = public void org.openqa.selenium.remote.RemoteWebElement.click()
	public void click() {
		this.refresh();        // Method to detect, update and centre the object on screen if existing.
		// this.centerOnScreen(); // Method to put object in center of the screen if partially or not visible
		this.pureElementMethodCall( "click" );
	}
	
	// ************************************************************************************************************************ clear
	// WebElement              [6]  = public abstract void org.openqa.selenium.WebElement.sendKeys(java.lang.CharSequence[])
	// AndroidElement          [39] = public void org.openqa.selenium.remote.RemoteWebElement.sendKeys(java.lang.CharSequence[])
	// IOSElement              [38] = public void org.openqa.selenium.remote.RemoteWebElement.sendKeys(java.lang.CharSequence[])
	// MobileElement           [38] = public void org.openqa.selenium.remote.RemoteWebElement.sendKeys(java.lang.CharSequence[])
	public void sendKeys( String charSequence ) {
		this.refresh();        // Method to detect, update and centre the object on screen if existing.
		this.centerOnScreen(); // Method to put object in center of the screen if partially or not visible
		pureDriverDetails myDriver = pureDrivers.getCurrentDriverDetails();

		java.lang.CharSequence mySequency[] = new java.lang.CharSequence[ charSequence.length() ];
		for( int iLoop = 0; iLoop < charSequence.length(); iLoop++ ) {
			mySequency[ iLoop ] = charSequence.substring( iLoop, iLoop +1 );
		}
		pureCore.callMethod( this.theElement, myDriver.elementClass, "sendKeys", java.lang.CharSequence[].class, "pureElement.sendKeys", mySequency );
	}
	// ************************************************************************************************************************ clear
	// WebElement              [7]  = public abstract void org.openqa.selenium.WebElement.submit()
	// AndroidElement          [12] = public void io.appium.java_client.android.AndroidElement.submit() throws org.openqa.selenium.WebDriverException
	// IOSElement              [12] = public void io.appium.java_client.ios.IOSElement.submit() throws org.openqa.selenium.WebDriverException
	// MobileElement           [23] = public void io.appium.java_client.MobileElement.submit() throws org.openqa.selenium.WebDriverException
	public void submit() {
		this.refresh();        // Method to detect, update and centre the object on screen if existing.
		this.centerOnScreen(); // Method to put object in center of the screen if partially or not visible
		this.pureElementMethodCall( "submit" );
	}
	
	// ************************************************************************************************************************ clear
	// WebElement              [8]  = public abstract java.lang.String org.openqa.selenium.WebElement.getTagName()
	// AndroidElement          [40] = public java.lang.String org.openqa.selenium.remote.RemoteWebElement.getTagName()
	// IOSElement              [39] = public java.lang.String org.openqa.selenium.remote.RemoteWebElement.getTagName()
	// MobileElement           [39] = public java.lang.String org.openqa.selenium.remote.RemoteWebElement.getTagName()
	public String getTagName() {
		this.refresh();        // Method to detect, update and centre the object on screen if existing.
		return (String)this.pureElementMethodCall( "getTagName" );
	}
	
	// ************************************************************************************************************************ clear
	// WebElement              [9]  = public abstract java.lang.String org.openqa.selenium.WebElement.getAttribute(java.lang.String)
	// AndroidElement          [41] = public java.lang.String org.openqa.selenium.remote.RemoteWebElement.getAttribute(java.lang.String)
	// IOSElement              [40] = public java.lang.String org.openqa.selenium.remote.RemoteWebElement.getAttribute(java.lang.String)
	// MobileElement           [40] = public java.lang.String org.openqa.selenium.remote.RemoteWebElement.getAttribute(java.lang.String)
	public String getAttribute( String AttributeName ) {
		this.refresh();        // Method to detect, update and centre the object on screen if existing.
		return (String)this.pureElementMethodCall( "getAttribute", (Object)AttributeName );
	}
	
	// ************************************************************************************************************************ clear
	// WebElement              [10] = public abstract boolean org.openqa.selenium.WebElement.isSelected()
	// AndroidElement          [42] = public boolean org.openqa.selenium.remote.RemoteWebElement.isSelected()
	// IOSElement              [41] = public boolean org.openqa.selenium.remote.RemoteWebElement.isSelected()
	// MobileElement           [41] = public boolean org.openqa.selenium.remote.RemoteWebElement.isSelected()
	public boolean isSelected() {
		this.refresh();
		return (boolean)this.pureElementMethodCall( "isSelected" );
	}
	
	// ************************************************************************************************************************ clear
	// WebElement              [11] = public abstract boolean org.openqa.selenium.WebElement.isEnabled()
	// AndroidElement          [43] = public boolean org.openqa.selenium.remote.RemoteWebElement.isEnabled()
	// IOSElement              [42] = public boolean org.openqa.selenium.remote.RemoteWebElement.isEnabled()
	// MobileElement           [42] = public boolean org.openqa.selenium.remote.RemoteWebElement.isEnabled()
	public boolean isEnabled() {
		this.refresh();
		return (boolean)this.pureElementMethodCall( "isEnabled" );
	}
	
	// ************************************************************************************************************************ clear
	// WebElement              [12] = public abstract java.lang.String org.openqa.selenium.WebElement.getText()
	// AndroidElement          [44] = public java.lang.String org.openqa.selenium.remote.RemoteWebElement.getText()
	// IOSElement              [43] = public java.lang.String org.openqa.selenium.remote.RemoteWebElement.getText()
	// MobileElement           [43] = public java.lang.String org.openqa.selenium.remote.RemoteWebElement.getText()
	public String getText() {
		this.refresh();
		return (String)this.pureElementMethodCall( "getText" );
	}
	
	// ************************************************************************************************************************ clear
	// WebElement              [13] = public abstract boolean org.openqa.selenium.WebElement.isDisplayed()
	// AndroidElement          [45] = public boolean org.openqa.selenium.remote.RemoteWebElement.isDisplayed()
	// IOSElement              [44] = public boolean org.openqa.selenium.remote.RemoteWebElement.isDisplayed()
	// MobileElement           [44] = public boolean org.openqa.selenium.remote.RemoteWebElement.isDisplayed()
	public boolean isDisplayed() {
		this.refresh();
		return (boolean)this.pureElementMethodCall( "isDisplayed" );
	}

	// ************************************************************************************************************************ clear
	// WebElement              [14] = public abstract org.openqa.selenium.Rectangle org.openqa.selenium.WebElement.getRect()
	// AndroidElement          [46] = public org.openqa.selenium.Rectangle org.openqa.selenium.remote.RemoteWebElement.getRect()
	// IOSElement              [45] = public org.openqa.selenium.Rectangle org.openqa.selenium.remote.RemoteWebElement.getRect()
	// MobileElement           [45] = public org.openqa.selenium.Rectangle org.openqa.selenium.remote.RemoteWebElement.getRect()
	public org.openqa.selenium.Rectangle getRect() {
		this.refresh();
		return (org.openqa.selenium.Rectangle)this.pureElementMethodCall( "getRect" );
	}

	// ************************************************************************************************************************ clear
	// WebElement              [15] = public abstract java.lang.String org.openqa.selenium.WebElement.getCssValue(java.lang.String)
	// AndroidElement          [13] = public java.lang.String io.appium.java_client.android.AndroidElement.getCssValue(java.lang.String) throws org.openqa.selenium.WebDriverException
	// IOSElement              [13] = public java.lang.String io.appium.java_client.ios.IOSElement.getCssValue(java.lang.String) throws org.openqa.selenium.WebDriverException
	// MobileElement           [24] = public java.lang.String io.appium.java_client.MobileElement.getCssValue(java.lang.String) throws org.openqa.selenium.WebDriverException
	public String getCssValue( String cssValue ) {
		this.refresh();
		return (String)this.pureElementMethodCall( "getCssValue", (Object) cssValue );
	}

	// ************************************************************************************************************************ clear
	// WebElement              [16] = public abstract java.lang.Object org.openqa.selenium.TakesScreenshot.getScreenshotAs(org.openqa.selenium.OutputType) throws org.openqa.selenium.WebDriverException
	// AndroidElement          [37] = public java.lang.Object org.openqa.selenium.remote.RemoteWebElement.getScreenshotAs(org.openqa.selenium.OutputType) throws org.openqa.selenium.WebDriverException
	// IOSElement              [36] = public java.lang.Object org.openqa.selenium.remote.RemoteWebElement.getScreenshotAs(org.openqa.selenium.OutputType) throws org.openqa.selenium.WebDriverException
	// MobileElement           [36] = public java.lang.Object org.openqa.selenium.remote.RemoteWebElement.getScreenshotAs(org.openqa.selenium.OutputType) throws org.openqa.selenium.WebDriverException
	public Object getScreenshotAs( org.openqa.selenium.OutputType<?> outputType ) {
		this.refresh();
		this.centerOnScreen(); // Method to put object in center of the screen if partially or not visible
		return this.pureElementMethodCall( "getScreenshotAs", (Object) outputType );
	}

	// ************************************************************************************************************************ 
	// AndroidElement          [0]  = public org.openqa.selenium.remote.Response io.appium.java_client.android.AndroidElement.execute(java.lang.String,java.util.Map)
	// IOSElement              [0]  = public org.openqa.selenium.remote.Response io.appium.java_client.ios.IOSElement.execute(java.lang.String,java.util.Map)
	// MobileElement           [1] = public org.openqa.selenium.remote.Response io.appium.java_client.MobileElement.execute(java.lang.String,java.util.Map)
	public org.openqa.selenium.remote.Response execute( java.lang.String execute, java.util.Map<?,?> map ){
		this.refresh();
		this.centerOnScreen(); // Method to put object in center of the screen if partially or not visible
		pureDriverDetails myDriver = pureDrivers.getCurrentDriverDetails();
		//
		Class<?>[] myClasses = new Class[2];
		myClasses[ 0 ] = java.lang.String.class;
		myClasses[ 1 ] = java.util.Map.class;
		//
		Object[] myTrueParam = new Object[2];
		myTrueParam[ 0 ] = (Object)execute;
		myTrueParam[ 1 ] = (Object)map;
		//
		return (org.openqa.selenium.remote.Response)pureCore.callMethod(
				this.theElement, myDriver.elementClass, "execute", myClasses, "pureElement.execute", myTrueParam );
	}

	// ************************************************************************************************************************ 
	// AndroidElement          [1]  = public org.openqa.selenium.remote.Response io.appium.java_client.android.AndroidElement.execute(java.lang.String)
	// IOSElement              [1]  = public org.openqa.selenium.remote.Response io.appium.java_client.ios.IOSElement.execute(java.lang.String)
	// MobileElement           [0]  = public org.openqa.selenium.remote.Response io.appium.java_client.MobileElement.execute(java.lang.String)
	public org.openqa.selenium.remote.Response execute( java.lang.String execute ){
		this.refresh();
		this.centerOnScreen(); // Method to put object in center of the screen if partially or not visible
		pureDriverDetails myDriver = pureDrivers.getCurrentDriverDetails();
		return (org.openqa.selenium.remote.Response)pureCore.callMethod(
				this.theElement, myDriver.elementClass, "execute", java.lang.String.class, "pureElement.execute", execute );
	}
	


	// ************************************************************************************************************************ 
	// AndroidElement          [3]  = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidElement.findElement(java.lang.String,java.lang.String)
	// IOSElement              [2]  = public org.openqa.selenium.WebElement io.appium.java_client.ios.IOSElement.findElement(java.lang.String,java.lang.String)
	// MobileElement           [3]  = public org.openqa.selenium.WebElement io.appium.java_client.MobileElement.findElement(java.lang.String,java.lang.String)
	public pureElement findElement( String method, String path ){
		this.refresh();        // Method to detect, update and centre the object on screen if existing.
		pureDriverDetails myDriver = pureDrivers.getCurrentDriverDetails();
		//
		Class<?>[] myClasses = new Class[2];
		myClasses[ 0 ] = java.lang.String.class;
		myClasses[ 1 ] = java.lang.String.class;
		//
		Object[] myTrueParam = new Object[2];
		myTrueParam[ 0 ] = (Object)method;
		myTrueParam[ 1 ] = (Object)path;
		//
		Object SourceObject = (Object)pureCore.callMethod( this.theElement, myDriver.elementClass, "findElement", myClasses, "pureElement.findElement", myTrueParam );
		if ( SourceObject != null ) {
			int elemMethod = pureCore.MethodFromStringLocator( method );
			pureElement newPureElement = new pureElement( "The Name", "The Type", elemMethod, path, pureCore.defRefreshMode, this );
			newPureElement.theElement = SourceObject;
			newPureElement.refrehCode = "0xFFFFFFFF";
			return newPureElement;
		}else {
			return null;
		}	
	}
	
	// ************************************************************************************************************************ 
	// Internal optimisation for following findElement methods.
	private pureElement intFindElementXXX( String MethodName, int elementMethod, String Description ) {
		this.refresh();
		Object SourceObject = (Object) this.pureElementMethodCall( MethodName, (Object)Description );
		if ( SourceObject != null ) {
			pureElement newPureElement = new pureElement( "The Name", "The Type", elementMethod, Description, pureCore.defRefreshMode, this );
			newPureElement.theElement = SourceObject;
			newPureElement.refrehCode = "0xFFFFFFFF";
			return newPureElement;
		}else {
			return (pureElement)null;
		}		
	}	
	
	// ************************************************************************************************************************ 
	// AndroidElement          [4]  = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidElement.findElementByLinkText(java.lang.String) throws org.openqa.selenium.WebDriverException
	// IOSElement              [4]  = public org.openqa.selenium.WebElement io.appium.java_client.ios.IOSElement.findElementByLinkText(java.lang.String) throws org.openqa.selenium.WebDriverException
	// MobileElement           [7]  = public org.openqa.selenium.WebElement io.appium.java_client.MobileElement.findElementByLinkText(java.lang.String) throws org.openqa.selenium.WebDriverException
	public pureElement findElementByLinkText( String Description ){
		return this.intFindElementXXX( "findElementByLinkText", pureCore.elementMethod.linkText, Description );
	}

	// ************************************************************************************************************************ 
	// AndroidElement          [5]  = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidElement.findElementByPartialLinkText(java.lang.String) throws org.openqa.selenium.WebDriverException
	// IOSElement              [5]  = public org.openqa.selenium.WebElement io.appium.java_client.ios.IOSElement.findElementByPartialLinkText(java.lang.String) throws org.openqa.selenium.WebDriverException
	// MobileElement           [8]  = public org.openqa.selenium.WebElement io.appium.java_client.MobileElement.findElementByPartialLinkText(java.lang.String) throws org.openqa.selenium.WebDriverException
	public pureElement findElementByPartialLinkText( String Description ){
		return this.intFindElementXXX( "findElementByPartialLinkText", pureCore.elementMethod.partialLinkText, Description );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [6]  = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidElement.findElementByTagName(java.lang.String)
	// IOSElement              [6]  = public org.openqa.selenium.WebElement io.appium.java_client.ios.IOSElement.findElementByTagName(java.lang.String)
	// MobileElement           [9]  = public org.openqa.selenium.WebElement io.appium.java_client.MobileElement.findElementByTagName(java.lang.String)
	public pureElement findElementByTagName( String Description ){
		return this.intFindElementXXX( "findElementByTagName", pureCore.elementMethod.tagNamei, Description );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [7]  = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidElement.findElementByName(java.lang.String)
	// IOSElement              [7]  = public org.openqa.selenium.WebElement io.appium.java_client.ios.IOSElement.findElementByName(java.lang.String)
	// MobileElement           [10] = public org.openqa.selenium.WebElement io.appium.java_client.MobileElement.findElementByName(java.lang.String)
	public pureElement findElementByName( String Description ){
		return this.intFindElementXXX( "findElementByName", pureCore.elementMethod.name, Description );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [8]  = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidElement.findElementByClassName(java.lang.String)
	// IOSElement              [8]  = public org.openqa.selenium.WebElement io.appium.java_client.ios.IOSElement.findElementByClassName(java.lang.String)
	// MobileElement           [11] = public org.openqa.selenium.WebElement io.appium.java_client.MobileElement.findElementByClassName(java.lang.String)
	public pureElement findElementByClassName( String Description ){
		return this.intFindElementXXX( "findElementByClassName", pureCore.elementMethod.className, Description );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [9]  = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidElement.findElementByCssSelector(java.lang.String) throws org.openqa.selenium.WebDriverException
	// IOSElement              [9]  = public org.openqa.selenium.WebElement io.appium.java_client.ios.IOSElement.findElementByCssSelector(java.lang.String) throws org.openqa.selenium.WebDriverException
	// MobileElement           [12] = public org.openqa.selenium.WebElement io.appium.java_client.MobileElement.findElementByCssSelector(java.lang.String) throws org.openqa.selenium.WebDriverException
	public pureElement findElementByCssSelector( String Description ){
		return this.intFindElementXXX( "findElementByCssSelector", pureCore.elementMethod.cssSelector, Description );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [10] = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidElement.findElementByXPath(java.lang.String)
	// IOSElement              [10] = public org.openqa.selenium.WebElement io.appium.java_client.ios.IOSElement.findElementByXPath(java.lang.String)
	// MobileElement           [13] = public org.openqa.selenium.WebElement io.appium.java_client.MobileElement.findElementByXPath(java.lang.String)
	public pureElement findElementByXPath( String Description ){
		return this.intFindElementXXX( "findElementByXPath", pureCore.elementMethod.xpath, Description );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [11] = public org.openqa.selenium.WebElement io.appium.java_client.android.AndroidElement.findElementById(java.lang.String)
	// IOSElement              [11] = public org.openqa.selenium.WebElement io.appium.java_client.ios.IOSElement.findElementById(java.lang.String)
	// MobileElement           [21] = public org.openqa.selenium.WebElement io.appium.java_client.MobileElement.findElementById(java.lang.String)
	public pureElement findElementById( String Description ){
		return this.intFindElementXXX( "findElementById", pureCore.elementMethod.id, Description );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [14] = public void io.appium.java_client.android.AndroidElement.replaceValue(java.lang.String)
	public void replaceValue( java.lang.String value ) {
		this.refresh();
		this.centerOnScreen(); // Method to put object in center of the screen if partially or not visible
		this.pureElementMethodCall( "replaceValue", (Object)value );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [15] = public void io.appium.java_client.MobileElement.setValue(java.lang.String)
	// IOSElement              [14] = public void io.appium.java_client.MobileElement.setValue(java.lang.String)
	// MobileElement           [2]  = public void io.appium.java_client.MobileElement.setValue(java.lang.String)
	public void setValue( java.lang.String value ) {
		this.refresh();
		this.centerOnScreen(); // Method to put object in center of the screen if partially or not visible
		this.pureElementMethodCall( "setValue", (Object)value );
	}

	// ************************************************************************************************************************ 
	// AndroidElement          [17] = public java.util.List io.appium.java_client.MobileElement.findElements(java.lang.String,java.lang.String)
	// IOSElement              [16] = public java.util.List io.appium.java_client.MobileElement.findElements(java.lang.String,java.lang.String)
	// MobileElement           [6]  = public java.util.List io.appium.java_client.MobileElement.findElements(java.lang.String,java.lang.String)
	

	// ************************************************************************************************************************ 
	// Internal optimisation for the following findElementsXXX methods
	private pureElements intFindElementsXXX( String MethodName, int elementMethod, String Description ) {
		this.refresh();        // Method to detect, update and centre the object on screen if existing.
		Object feedback = this.pureElementMethodCall( MethodName, (Object)Description );
		List<Object> myObjectList = pureCore.CastObjecttoList( feedback	);
		if ( myObjectList != null ) {
			pureElements newPureElements = new pureElements( "-", "-", elementMethod, Description, pureCore.defRefreshMode, this );
			newPureElements.theElements = myObjectList;
			newPureElements.refrehCode = "0xFFFFFFFF";
			return newPureElements;
		}else {
			return null;
		}
	}
	// ************************************************************************************************************************ 
	// AndroidElement          [18] = public java.util.List io.appium.java_client.MobileElement.findElementsByLinkText(java.lang.String)
	// IOSElement              [17] = public java.util.List io.appium.java_client.MobileElement.findElementsByLinkText(java.lang.String)
	// MobileElement           [14] = public java.util.List io.appium.java_client.MobileElement.findElementsByLinkText(java.lang.String)
	public pureElements findElementsByLinkText( java.lang.String Description ){
		return intFindElementsXXX( "findElementsByLinkText", pureCore.elementMethod.linkText, Description );
	}

	// ************************************************************************************************************************ 
	// AndroidElement          [19] = public java.util.List io.appium.java_client.MobileElement.findElementsByPartialLinkText(java.lang.String)
	// IOSElement              [18] = public java.util.List io.appium.java_client.MobileElement.findElementsByPartialLinkText(java.lang.String)
	// MobileElement           [15] = public java.util.List io.appium.java_client.MobileElement.findElementsByPartialLinkText(java.lang.String)
	public pureElements findElementsByPartialLinkText( java.lang.String Description ){
		return intFindElementsXXX( "findElementsByPartialLinkText", pureCore.elementMethod.partialLinkText, Description );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [20] = public java.util.List io.appium.java_client.MobileElement.findElementsByTagName(java.lang.String)
	// IOSElement              [19] = public java.util.List io.appium.java_client.MobileElement.findElementsByTagName(java.lang.String)
	// MobileElement           [16] = public java.util.List io.appium.java_client.MobileElement.findElementsByTagName(java.lang.String)
	public pureElements findElementsByTagName( java.lang.String Description ){
		return intFindElementsXXX( "findElementsByTagName", pureCore.elementMethod.tagNamei, Description );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [21] = public java.util.List io.appium.java_client.MobileElement.findElementsByName(java.lang.String)
	// IOSElement              [20] = public java.util.List io.appium.java_client.MobileElement.findElementsByName(java.lang.String)
	// MobileElement           [17] = public java.util.List io.appium.java_client.MobileElement.findElementsByName(java.lang.String)
	public pureElements findElementsByName( java.lang.String Description ){
		return intFindElementsXXX( "findElementsByName", pureCore.elementMethod.name, Description );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [22] = public java.util.List io.appium.java_client.MobileElement.findElementsByClassName(java.lang.String)
	// IOSElement              [21] = public java.util.List io.appium.java_client.MobileElement.findElementsByClassName(java.lang.String)
	// MobileElement           [18] = public java.util.List io.appium.java_client.MobileElement.findElementsByClassName(java.lang.String)
	public pureElements findElementsByClassName( java.lang.String Description ){
		return intFindElementsXXX( "findElementsByClassName", pureCore.elementMethod.className, Description );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [23] = public java.util.List io.appium.java_client.MobileElement.findElementsByCssSelector(java.lang.String)
	// IOSElement              [22] = public java.util.List io.appium.java_client.MobileElement.findElementsByCssSelector(java.lang.String)
	// MobileElement           [19] = public java.util.List io.appium.java_client.MobileElement.findElementsByCssSelector(java.lang.String)
	public pureElements findElementsByCssSelector( java.lang.String Description ){
		return intFindElementsXXX( "findElementsByCssSelector", pureCore.elementMethod.cssSelector, Description );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [24] = public java.util.List io.appium.java_client.MobileElement.findElementsByXPath(java.lang.String)
	// IOSElement              [23] = public java.util.List io.appium.java_client.MobileElement.findElementsByXPath(java.lang.String)
	// MobileElement           [20] = public java.util.List io.appium.java_client.MobileElement.findElementsByXPath(java.lang.String)
	public pureElements findElementsByXPath( java.lang.String Description ){
		return intFindElementsXXX( "findElementsByXPath", pureCore.elementMethod.xpath, Description );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [25] = public java.util.List io.appium.java_client.MobileElement.findElementsById(java.lang.String)
	// IOSElement              [24] = public java.util.List io.appium.java_client.MobileElement.findElementsById(java.lang.String)
	// MobileElement           [22] = public java.util.List io.appium.java_client.MobileElement.findElementsById(java.lang.String)
	public pureElements findElementsById( java.lang.String Description ){
		return intFindElementsXXX( "findElementsById", pureCore.elementMethod.id, Description );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [26] = public java.util.List io.appium.java_client.MobileElement.findElementsByAccessibilityId(java.lang.String)
	// IOSElement              [25] = public java.util.List io.appium.java_client.MobileElement.findElementsByAccessibilityId(java.lang.String)
	// MobileElement           [26] = public java.util.List io.appium.java_client.MobileElement.findElementsByAccessibilityId(java.lang.String)
	public pureElements findElementsByAccessibilityId( java.lang.String Description ){
		return intFindElementsXXX( "findElementsByAccessibilityId", pureCore.elementMethod.AccessibilityId, Description );
	}
		
	// ************************************************************************************************************************ 
	// AndroidElement          [27] = public org.openqa.selenium.Point io.appium.java_client.MobileElement.getCenter()
	// IOSElement              [26] = public org.openqa.selenium.Point io.appium.java_client.MobileElement.getCenter()
	// MobileElement           [25] = public org.openqa.selenium.Point io.appium.java_client.MobileElement.getCenter()
	public org.openqa.selenium.Point getCenter(){
		this.refresh();
		return (org.openqa.selenium.Point)this.pureElementMethodCall( "getCenter" );
	}
	// ************************************************************************************************************************ 
	// AndroidElement          [28] = public boolean org.openqa.selenium.remote.RemoteWebElement.equals(java.lang.Object)
	// IOSElement              [27] = public boolean org.openqa.selenium.remote.RemoteWebElement.equals(java.lang.Object)
	// MobileElement           [27] = public boolean org.openqa.selenium.remote.RemoteWebElement.equals(java.lang.Object)
	public boolean equalsL( Object equalsto ){
		this.refresh();
		return (boolean)this.pureElementMethodCall( "equals", equalsto );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [29] = public java.lang.String org.openqa.selenium.remote.RemoteWebElement.toString()
	// IOSElement              [28] = public java.lang.String org.openqa.selenium.remote.RemoteWebElement.toString()
	// MobileElement           [28] = public java.lang.String org.openqa.selenium.remote.RemoteWebElement.toString()
	public java.lang.String toStringL(){
		this.refresh();
		return (String)this.pureElementMethodCall( "toString" );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [30] = public int org.openqa.selenium.remote.RemoteWebElement.hashCode()
	// IOSElement              [29] = public int org.openqa.selenium.remote.RemoteWebElement.hashCode()
	// MobileElement           [29] = public int org.openqa.selenium.remote.RemoteWebElement.hashCode()
	public int hashCodeL(){
		this.refresh();
		return (int)this.pureElementMethodCall( "hashCode" );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [33] = public java.lang.String org.openqa.selenium.remote.RemoteWebElement.getId()
	// IOSElement              [32] = public java.lang.String org.openqa.selenium.remote.RemoteWebElement.getId()
	// MobileElement           [32] = public java.lang.String org.openqa.selenium.remote.RemoteWebElement.getId()
	public java.lang.String getId(){
		this.refresh();
		return (java.lang.String)this.pureElementMethodCall( "getId" );
	}

	// ************************************************************************************************************************ 
	// AndroidElement          [35] = public void org.openqa.selenium.remote.RemoteWebElement.setParent(org.openqa.selenium.remote.RemoteWebDriver)
	// IOSElement              [34] = public void org.openqa.selenium.remote.RemoteWebElement.setParent(org.openqa.selenium.remote.RemoteWebDriver)
	// MobileElement           [34] = public void org.openqa.selenium.remote.RemoteWebElement.setParent(org.openqa.selenium.remote.RemoteWebDriver)
	public void setParent( org.openqa.selenium.remote.RemoteWebDriver remoteWebDriver ){
		this.refresh();
		this.pureElementMethodCall( "setParent", remoteWebDriver );
	}

	// ************************************************************************************************************************ 
	// AndroidElement          [36] = public void org.openqa.selenium.remote.RemoteWebElement.setFileDetector(org.openqa.selenium.remote.FileDetector)
	// IOSElement              [35] = public void org.openqa.selenium.remote.RemoteWebElement.setFileDetector(org.openqa.selenium.remote.FileDetector)
	// MobileElement           [35] = public void org.openqa.selenium.remote.RemoteWebElement.setFileDetector(org.openqa.selenium.remote.FileDetector)
	public void setFileDetector( org.openqa.selenium.remote.FileDetector fileSelector ) {
		this.refresh();
		this.pureElementMethodCall( "setFileDetector", fileSelector );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [47] = public org.openqa.selenium.WebDriver org.openqa.selenium.remote.RemoteWebElement.getWrappedDriver()
	// IOSElement              [48] = public org.openqa.selenium.WebDriver org.openqa.selenium.remote.RemoteWebElement.getWrappedDriver()
	// MobileElement           [46] = public org.openqa.selenium.WebDriver org.openqa.selenium.remote.RemoteWebElement.getWrappedDriver()
/*	public pureDrivers getWrappedDriver(){
		this.refresh();
		Object SourceObject = this.pureElementMethodCall( "getWrappedDriver" );
		if ( SourceObject != null ) {
			pureDrivers newDriver = new pureDrivers();
			newDriver.DriverName = "WrappedDriver";
			newDriver.subDriverName = "";
			newDriver.mainDriver = SourceObject;
			return newDriver;
		}else {
			return null;
		}
	} */
	
	// ************************************************************************************************************************ 
	// AndroidElement          [48] = public org.openqa.selenium.interactions.internal.Coordinates org.openqa.selenium.remote.RemoteWebElement.getCoordinates()
	// IOSElement              [49] = public org.openqa.selenium.interactions.internal.Coordinates org.openqa.selenium.remote.RemoteWebElement.getCoordinates()
	// MobileElement           [47] = public org.openqa.selenium.interactions.internal.Coordinates org.openqa.selenium.remote.RemoteWebElement.getCoordinates()
	public org.openqa.selenium.interactions.internal.Coordinates getCoordinates(){
		this.refresh();
		return (org.openqa.selenium.interactions.internal.Coordinates)this.pureElementMethodCall( "getCoordinates" );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [49] = public java.util.Map org.openqa.selenium.remote.RemoteWebElement.toJson()
	// IOSElement              [46] = public java.util.Map org.openqa.selenium.remote.RemoteWebElement.toJson()
	// MobileElement           [48] = public java.util.Map org.openqa.selenium.remote.RemoteWebElement.toJson()
	public java.util.Map<?,?> toJson(){
		this.refresh();
		return (java.util.Map<?,?>)this.pureElementMethodCall( "toJson" );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [50] = public void org.openqa.selenium.remote.RemoteWebElement.setId(java.lang.String)
	// IOSElement              [47] = public void org.openqa.selenium.remote.RemoteWebElement.setId(java.lang.String)
	// MobileElement           [49] = public void org.openqa.selenium.remote.RemoteWebElement.setId(java.lang.String)
	public void setId( java.lang.String Value ) {
		this.refresh();
		this.pureElementMethodCall( "setId", Value );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [51] = public final void java.lang.Object.wait() throws java.lang.InterruptedException
	// IOSElement              [50] = public final void java.lang.Object.wait() throws java.lang.InterruptedException
	// MobileElement           [50] = public final void java.lang.Object.wait() throws java.lang.InterruptedException
	public void waitL(){
		this.refresh();
		this.pureElementMethodCall( "wait" );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [52] = public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
	// IOSElement              [51] = public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
	// MobileElement           [51] = public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
	public void waitL( long waitdelay, int waitdelayms ){
		this.refresh();
		this.pureElementMethodCall( "wait", waitdelay, waitdelayms );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [53] = public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
	// IOSElement              [52] = public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
	// MobileElement           [52] = public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
	public void waitL( long waitdelay ){
		this.refresh();
		this.pureElementMethodCall( "wait", waitdelay );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [54] = public final native java.lang.Class java.lang.Object.getClass()
	// IOSElement              [53] = public final native java.lang.Class java.lang.Object.getClass()
	// MobileElement           [53] = public final native java.lang.Class java.lang.Object.getClass()
	public java.lang.Class<?> getClassL(){
		this.refresh();
		return (java.lang.Class<?>)this.pureElementMethodCall( "getClass" );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [55] = public final native void java.lang.Object.notify()
	// IOSElement              [54] = public final native void java.lang.Object.notify()
	// MobileElement           [54] = public final native void java.lang.Object.notify()
	public void notifyL(){
		this.refresh();
		this.pureElementMethodCall( "notify" );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [56] = public final native void java.lang.Object.notifyAll()
	// IOSElement              [55] = public final native void java.lang.Object.notifyAll()
	// MobileElement           [55] = public final native void java.lang.Object.notifyAll()
	public void notifyAllL(){
		this.refresh();
		this.pureElementMethodCall( "notifyAll" );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [57] = public default org.openqa.selenium.WebElement io.appium.java_client.FindsByAccessibilityId.findElementByAccessibilityId(java.lang.String)
	// IOSElement              [56] = public default org.openqa.selenium.WebElement io.appium.java_client.FindsByAccessibilityId.findElementByAccessibilityId(java.lang.String)
	// MobileElement           [56] = public default org.openqa.selenium.WebElement io.appium.java_client.FindsByAccessibilityId.findElementByAccessibilityId(java.lang.String)
	public pureElement findElementByAccessibilityId( String Description ){
		return this.intFindElementXXX( "findElementByAccessibilityId", pureCore.elementMethod.AccessibilityId, Description );
	}
	
	// ************************************************************************************************************************ 
	// AndroidElement          [58] = public default org.openqa.selenium.WebElement io.appium.java_client.FindsByAndroidUIAutomator.findElementByAndroidUIAutomator(java.lang.String)
	public pureElement findElementByAndroidUIAutomator( String Description ){
		return this.intFindElementXXX( "findElementByAndroidUIAutomator", pureCore.elementMethod.AndroidUIAutomator, Description );
	}

	// ************************************************************************************************************************ 
	// AndroidElement          [59] = public default java.util.List io.appium.java_client.FindsByAndroidUIAutomator.findElementsByAndroidUIAutomator(java.lang.String)
	public pureElements findElementsByAndroidUIAutomator( String Description ) {
		return this.intFindElementsXXX( "findElementsByAndroidUIAutomator", pureCore.elementMethod.AndroidUIAutomator, Description );
	}

	// ************************************************************************************************************************ 
	// IOSElement              [57] = public default org.openqa.selenium.WebElement io.appium.java_client.FindsByIosUIAutomation.findElementByIosUIAutomation(java.lang.String)
	public pureElement findElementByIosUIAutomation( String Description ){
		return this.intFindElementXXX( "findElementByIosUIAutomation", pureCore.elementMethod.IosUIAutomation, Description );
	}

	// ************************************************************************************************************************ 
	// IOSElement              [58] = public default java.util.List io.appium.java_client.FindsByIosUIAutomation.findElementsByIosUIAutomation(java.lang.String)
	public pureElements findElementsByIosUIAutomation( String Description ) {
		return this.intFindElementsXXX( "findElementsByIosUIAutomation", pureCore.elementMethod.IosUIAutomation, Description );
	}

	// ************************************************************************************************************************ 
	// IOSElement              [59] = public default org.openqa.selenium.WebElement io.appium.java_client.FindsByIosNSPredicate.findElementByIosNsPredicate(java.lang.String)
	public pureElement findElementByIosNsPredicate( String Description ){
		return this.intFindElementXXX( "findElementByIosNsPredicate", pureCore.elementMethod.IosNsPredicate, Description );
	}

	// ************************************************************************************************************************ 
	// IOSElement              [60] = public default java.util.List io.appium.java_client.FindsByIosNSPredicate.findElementsByIosNsPredicate(java.lang.String)
	public pureElements findElementsByIosNsPredicate( String Description ) {
		return this.intFindElementsXXX( "findElementsByIosNsPredicate", pureCore.elementMethod.IosNsPredicate, Description );
	}

	// ************************************************************************************************************************ 
	// IOSElement              [61] = public default org.openqa.selenium.WebElement io.appium.java_client.FindsByIosClassChain.findElementByIosClassChain(java.lang.String)
	public pureElement findElementByIosClassChain( String Description ){
		return this.intFindElementXXX( "findElementByIosClassChain", pureCore.elementMethod.IosClassChain, Description );
	}

	// ************************************************************************************************************************ 
	// IOSElement              [62] = public default java.util.List io.appium.java_client.FindsByIosClassChain.findElementsByIosClassChain(java.lang.String)
	public pureElements findElementsByIosClassChain( String Description ) {
		return this.intFindElementsXXX( "findElementsByIosClassChain", pureCore.elementMethod.IosClassChain, Description );
	}

	// ************************************************************************************************************************ 
	private void refresh() {
		// Si l'Element n'a pas encore ete cherche, ou que son rafraichissement doit etre realise.
		if ( this.theElement == null || this.refreshMethod == 1 ) { // || this.refrehCode != pureCore.getLastLogCat() ) {
			pureDriverDetails myDriver = null;
			if ( this.driverLink == -1 ) {
				myDriver = pureDrivers.getCurrentDriverDetails();
				// this.driverToUse = myDriver;
			}else {
				myDriver = this.driverToUse;
			}
			this.theElement = this.detection();
			// Tout d'abord on réalise une attente pour trouver l'élément.
			/*try {
				this.theElement = (Object)(new WebDriverWait( (WebDriver)pureCore.getCurrentDriver(), pureCore.getMaxWaitDelay() )
							.until( ExpectedConditions.presenceOfElementLocated( By.xpath( pureCore.MethodToLocator( this.eMethod, this.ePath ) ) ) ) );
			}catch( Exception e ) {
				pureErrorHandler.castError( pureErrorHandler.exceptions.getType( e ), "pureElement.refresh()", this.eName, pureCore.MethodToLocator( this.eMethod, this.ePath ) );
			} */
			if ( this.parent == null ) {
				this.theElement = (Object)pureDrivers.findElementWE( pureCore.MethodToLocator( this.eMethod, this.ePath ), myDriver );
			}else {
				this.theElement = (Object)this.parent.findElement( pureCore.MethodToLocator( this.eMethod,  this.ePath ) );
			}
			// this.centerOnScreen();
		}
	}

	public Object myObject = null;
	private Object detection() {
		pureDriverDetails myDriver = null;
		if ( this.driverLink == -1 ) {
			myDriver = pureDrivers.getCurrentDriverDetails();
		}else {
			myDriver = this.driverToUse;
		}
		try {
			WebDriverWait objWait = new WebDriverWait( (WebDriver)myDriver.mainDriver, 20, 500 );
			objWait.until( new ExpectedCondition<Boolean>() {
				private pureElement myElement;
				private pureDriverDetails myDriver;
				private ExpectedCondition<Boolean> init( pureElement myElement, pureDriverDetails myDriver ){
					this.myElement = myElement;
					this.myDriver = myDriver;
					return this;
				}
				public Boolean apply(WebDriver wDriver) {
					if( this.myElement.parent == null ) {
						this.myElement.theElement = (Object)pureDrivers.findElementWE( pureCore.MethodToLocator( this.myElement.eMethod, this.myElement.ePath ), this.myDriver );
					}else {
						this.myElement.theElement = (Object)this.myElement.parent.findElement( pureCore.MethodToLocator( this.myElement.eMethod,  this.myElement.ePath ) );
					}
					return ( this.myElement.theElement != null );
				}
			}.init( this, myDriver ) );
		
		}catch( Exception e ) {
			pureErrorHandler.castError( pureErrorHandler.exceptions.getType( e ), "pureElement.refresh()", this.eName, pureCore.MethodToLocator( this.eMethod, this.ePath ).toString() );
		}
		return myObject;
	}
	// ************************************************************************************************************************ 
private void centerOnScreen() {
	pureDriverDetails myDriver = pureDrivers.getCurrentDriverDetails();
	// Positionner l'objet au milieu de l'ecran si possible
	if ( this.theElement !=null ) {
		// On recupere d'abord les coordonnees et dimensions de l'objet
		Point pePosition = this.getLocation();
		Dimension peDimension = this.getSize();
				// On recupere ensuite les coordonnees et dimensions de l'ecran visible
		Rectangle cdRectangle = pureDrivers.GetDisplayArea();
		// Si l'objet est plus haut que l'ecran visible (et partiellement ou totalement hors ecran) :
		if ( pePosition.getY()  < cdRectangle.getY() ) {
			int yShift = ( cdRectangle.getY() - pePosition.getY() ) + ( ( cdRectangle.getHeight() / 2 ) - ( peDimension.getHeight() / 2 ) );
			// Scroller vers le haut de la page, d'une valeur de yShift pixels
	      	JavascriptExecutor jse = (JavascriptExecutor)myDriver.mainDriver;
	        jse.executeScript("window.scrollTo( " + ( cdRectangle.getY() - yShift ) + "," + "0" + ")" );
		}
		// Si l'objet est plus bas que l'ecran visible (et partiellement ou totalement hors ecran) :
		if ( ( pePosition.getY() + peDimension.getHeight() ) > ( cdRectangle.getY() + cdRectangle.getHeight() ) ){
			int yShift = ( ( pePosition.getY() + peDimension.getHeight() ) - ( cdRectangle.getY() + cdRectangle.getHeight() ) ) + 
				( ( cdRectangle.getHeight() / 2 ) - ( peDimension.getHeight() / 2 ) );
			// Scroller vers le bas de la page, d'une valeur de yShift pixels
	      	JavascriptExecutor jse = (JavascriptExecutor)myDriver.mainDriver;
	        jse.executeScript("window.scrollTo( " + ( cdRectangle.getY() + yShift ) + "," + "0" + ")" );
		}
	}
	
}
	
	// ************************************************************************************************************************ 
	private Object pureElementMethodCall( String MethodToCall, Object Param1, Object Param2, Object Param3 ) {
		pureDriverDetails myDriver = null;
		if ( this.driverToUse != null ) {
			myDriver = this.driverToUse;
		}else {
			myDriver = pureDrivers.getCurrentDriverDetails();
		}
		int modSize = ( ( Param1 != null ) ? 1: 0 ) + ( ( Param2 != null ) ? 1: 0 ) + ( ( Param2 != null ) ? 1: 0 );
		Class<?>[] myParam = new Class[ modSize ];
		// myParam[ 0 ] = myDriver.elementClass; // <---- L'objet e placer
		if ( Param1 != null ) { myParam[ 0 ] = Param1.getClass(); }
		if ( Param2 != null ) { myParam[ 1 ] = Param2.getClass(); }
		if ( Param3 != null ) { myParam[ 2 ] = Param3.getClass(); }
		Object[] myTrueParam = new Object[ modSize ];
		// myTrueParam[ 0 ] = this.theElement;
		if ( Param1 != null ) { myTrueParam[ 0 ] = Param1; }
		if ( Param2 != null ) { myTrueParam[ 1 ] = Param2; }
		if ( Param3 != null ) { myTrueParam[ 2 ] = Param3; }
		//            xxxElementInstance, xxxElementClass,       Element.method(), ParamClass[], CommentForErrorMessage,  TrueParamsObjects[]
		return pureCore.callMethod( this.theElement, myDriver.elementClass, MethodToCall, myParam, "pureElement" + MethodToCall, myTrueParam );
	}

	// ************************************************************************************************************************ 
	private Object pureElementMethodCall( String MethodToCall, Object Param1, Object Param2 ) {
		return pureElementMethodCall( MethodToCall, Param1, Param2, (Object)null );
	}
	
	// ************************************************************************************************************************ 
	private Object pureElementMethodCall( String MethodToCall, Object Param1 ) {
		return pureElementMethodCall( MethodToCall, Param1, (Object)null, (Object)null );
	}
	
	private Object pureElementMethodCall( String MethodToCall ) {
		return pureElementMethodCall( MethodToCall, (Object)null, (Object)null, (Object)null );
	}	
}

