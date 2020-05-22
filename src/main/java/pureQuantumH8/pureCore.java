package pureQuantumH8;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

public class pureCore {
	
	public static int defRefreshMode = 0;

	//**************************************************************************************************************************
	/** Description : The hearth of the system is there. This method uses JAVA Reflection to check if a specified method, with specific parameters exists or not
	    In the driver that was opened (as instance of the class) and unidentified (put as Object) for unification principle.
	*/
	public static Object callMethod( Object theDriverInstance, Class<?> myClass, String myMethodName, Class<?> Params[], String theCall, Object trueParam[] ) {
		System.out.println( "Method name = " + myMethodName );
		Object feedback = null;
		try {
			Method myMethod;
			// Here, we check if the required method exists or not.
			if ( Params == null ) {
				myMethod = myClass.getMethod( myMethodName );
			}else {
				myMethod = myClass.getMethod( myMethodName, Params );			
			}
			// Here we call the method. If it does not exists in this driver, the Catch exception will tell us.
			if ( trueParam == null ) {
				feedback = myMethod.invoke( theDriverInstance );
			}else {
				feedback = myMethod.invoke( theDriverInstance, trueParam );
			}
		// Catch exception will allow to output error, whatever it is.
		}catch( Exception except ) {
			pureErrorHandler.castError( pureErrorHandler.exceptions.getType( except ), null, theCall, myMethodName );
		}
		return feedback;
	}

	//**************************************************************************************************************************
	/** Description : In complementarity with the previous method, this one is dedicaced especially to methods that own only 1 parameters.
	    For a simpler use it cast the single parameter as an array with only 1 element. It allow us to directly uses the previous methods in all case
	    This mean that if an update must be done in the search for the method and its call, it will always be done in the previous method.
	*/
	public static Object callMethod( Object theDriverInstance, Class<?> myClass, String myMethodName, Class<?> Params, String theCall, Object trueParam ) {
		Class<?>[] myParam = new Class[1];
		myParam[ 0 ] = Params;
		Object[] myTrueParam = new Object[1];
		myTrueParam[ 0 ] = trueParam;
		return callMethod( theDriverInstance, myClass, myMethodName, myParam, theCall, myTrueParam );
	}

	public static void demoWait(int DurationMS ) {
		sleep( DurationMS );
	}

	public static void sleep(int DurationMS ) {
		try {
			Thread.sleep( DurationMS );
		} catch (Exception e) {
			pureErrorHandler.castError( -1, "ToolKIT : problem occurend when calling during Thread.sleep() call", null, null );
		}
	}

	// ************************************************************************************************************************ ELEMENTS LINKED METHODS
	public static class elementMethod{
	  	private elementMethod(){}                                                                         // Prevents instantiation
	  	public final static int id = 1;
	    public final static int xpath = 2;
	    public final static int className = 3;
	    public final static int name = 4;
	    public final static int cssSelector = 5;
	    public final static int linkText = 6;
	    public final static int partialLinkText = 7;
	    public final static int tagNamei = 8;
	    public final static int href = 9;
	    public final static int text = 10;
	    public final static int containsText = 11;
	    public final static int containsName = 12;
	    public final static int containsId = 13;
	    public final static int containsHref = 14;
	    public final static int startwithText = 15;
	    public final static int startwithName = 16;
	    public final static int startwithId = 17;
	    public final static int startwithHref = 18;
		public final static int uiAutomator_resourceid = 19;
		public final static int uiAutomator_text = 20;
		public final static int AccessibilityId = 21;
		public final static int IosNsPredicate = 22;
		public final static int IosClassChain = 23;
		public final static int IosUIAutomation = 24;
		public final static int AndroidUIAutomator = 25;
	}

	public static int MethodFromStringLocator( String locator ) {
		int methodID = -1;
		switch ( locator ) {
			case "by.id" : 						methodID = elementMethod.id; 						break;
			case "by.xpath" : 					methodID = elementMethod.xpath; 					break;
			case "by.ClassName" : 				methodID = elementMethod.className; 				break;
			case "by.name" : 					methodID = elementMethod.name; 						break;
			case "by.cssSelector" : 			methodID = elementMethod.cssSelector; 				break;
			case "by.linkText" : 				methodID = elementMethod.linkText; 					break;
			case "by.partialLinkText" : 		methodID = elementMethod.partialLinkText; 			break;
			case "by.tagNamei" : 				methodID = elementMethod.tagNamei; 					break;
			case "by.href" : 					methodID = elementMethod.href; 						break;
			case "by.text" : 					methodID = elementMethod.text; 						break;
			case "by.containsText" : 			methodID = elementMethod.containsText; 				break;
			case "by.containsName" : 			methodID = elementMethod.containsName; 				break;
			case "by.containsId" : 				methodID = elementMethod.containsId; 				break;
			case "by.containsHref" : 			methodID = elementMethod.containsHref; 				break;
			case "by.startwithText" : 			methodID = elementMethod.startwithText; 			break;
			case "by.startwithName" : 			methodID = elementMethod.startwithName; 			break;
			case "by.startwithId" : 			methodID = elementMethod.startwithId; 				break;
			case "by.startwithHref" : 			methodID = elementMethod.startwithHref; 			break;
			case "by.uiAutomator_resourceid" :	methodID = elementMethod.uiAutomator_resourceid; 	break;
			case "by.uiAutomator_text" :		methodID = elementMethod.uiAutomator_text; 			break;
			case "by.AccessibilityId" : 		methodID = elementMethod.AccessibilityId; 			break;
			case "by.IosNsPredicate" :          methodID = elementMethod.IosNsPredicate;            break;
			case "by.IosClassChain" :           methodID = elementMethod.IosClassChain;             break;
			case "by.IosUIAutomation" :         methodID = elementMethod.IosUIAutomation;           break;
			case "by.AndroidUIAutomator" :      methodID = elementMethod.AndroidUIAutomator;        break;     
		}
		return methodID;
	}
	
	public static By MethodToLocator(int uMethodVal, String ObjectxPath) {
		By myLocator = null;
		switch (uMethodVal) {
		case elementMethod.id:					myLocator = By.id( ObjectxPath );											break;
		case elementMethod.xpath:				myLocator = By.xpath( ObjectxPath );										break;
		case elementMethod.className:			myLocator = By.className( ObjectxPath );									break;
		case elementMethod.name:				myLocator = By.name( ObjectxPath ); 										break;
		case elementMethod.cssSelector:			myLocator = By.cssSelector( ObjectxPath ); 									break;
		case elementMethod.linkText:			myLocator = By.linkText( ObjectxPath ); 									break;
		case elementMethod.partialLinkText:		myLocator = By.partialLinkText( ObjectxPath ); 								break;
		case elementMethod.tagNamei:			myLocator = By.tagName( ObjectxPath );										break;
		case elementMethod.href:				myLocator = By.xpath("//*[@href,'" + ObjectxPath + "']");					break;
		// Extension "contains"
		case elementMethod.text:				myLocator = By.xpath("//*[text()='" + ObjectxPath + "']");					break;
		case elementMethod.containsText:		myLocator = By.xpath("//*[contains(text(),'" + ObjectxPath + "')]");		break;
		case elementMethod.containsName:		myLocator = By.xpath("//*[contains(@name,'" + ObjectxPath + "')]");			break;
		case elementMethod.containsId:			myLocator = By.xpath("//*[contains(@id,'" + ObjectxPath + "')]");			break;
		case elementMethod.containsHref:		myLocator = By.xpath("//*[contains(@href,'" + ObjectxPath + "')]");			break;
		// Extension *starts-with"
		case elementMethod.startwithText:		myLocator = By.xpath("//*[starts-with(text(),'" + ObjectxPath + "')]");		break;
		case elementMethod.startwithName:		myLocator = By.xpath("//*[starts-with(@name,'" + ObjectxPath + "')]");		break;
		case elementMethod.startwithId:			myLocator = By.xpath("//*[starts-with(@id,'" + ObjectxPath + "')]");		break;
		case elementMethod.startwithHref:		myLocator = By.xpath("//*[starts-with(@href,'" + ObjectxPath + "')]");		break;
		// Extension uiAutomator & AccessibilityID (not yet implemented)
		case elementMethod.uiAutomator_resourceid : myLocator = null;                                                       break;
		case elementMethod.uiAutomator_text :   myLocator = null;                                                           break;
		case elementMethod.AccessibilityId :    myLocator = null;                                                           break;
		case elementMethod.IosNsPredicate :     myLocator = null;                                                           break;
		case elementMethod.IosClassChain :      myLocator = null;                                                           break;
		case elementMethod.IosUIAutomation :    myLocator = null;                                                           break;
		default:								myLocator = By.xpath(" + ObjectxPath + ");									break;
		// XPath locators can use OR or AND statements, ex //*[@type='submit' OR @name='btnreset']
		}
		return myLocator;
	}

	public static class peMethod{
		public int peMethodid;
		public String peSearch;
		public peMethod(){
			this.peMethodid = -1;
			this.peSearch = null;
		}
	}

	public static peMethod peMethodFromBy( String byContent ) {
		peMethod myMethod = new peMethod();
		myMethod.peMethodid = -1;
		if ( byContent != null && byContent.length() > 0 ) {
			int doubleDot = byContent.indexOf( ":" );
			if (doubleDot > 0 ) {
				myMethod.peSearch = byContent.substring( doubleDot + 1 );
				switch( byContent.substring( 0, doubleDot ).toLowerCase() ) {
					case "by.id" : myMethod.peMethodid = pureCore.elementMethod.id; break;
					case "by.xpath" : myMethod.peMethodid = pureCore.elementMethod.xpath; break;
					case "by.classname" : myMethod.peMethodid = pureCore.elementMethod.className; break;
					case "by.name" : myMethod.peMethodid =  pureCore.elementMethod.name; break;
					case "by.cssSelector" : myMethod.peMethodid = pureCore.elementMethod.cssSelector; break;
					case "by.linkText" : myMethod.peMethodid = pureCore.elementMethod.linkText ; break;
					case "by.partialLinkText" : myMethod.peMethodid = pureCore.elementMethod.partialLinkText; break;
					case "by.tagName" : myMethod.peMethodid = pureCore.elementMethod.tagNamei; break;
				}
			}
		}
		return myMethod;
	}

	public static List<Object> CastObjecttoList( Object input ){
	    List<Object> output = new ArrayList<Object>();
	    if ( input instanceof List ){
	        for( int iLoop = 0; iLoop < ( ( List<?> )input ).size(); iLoop++ ){
	            Object item = ( (List<?>) input ).get( iLoop );
	            if(item instanceof Object ){
	            	output.add( ( Object ) item);
	            }
	        }
	    }
	    return output;
	}

}
