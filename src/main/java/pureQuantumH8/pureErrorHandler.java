package pureQuantumH8;

//*********************************************************************
/** Description : Cette classe gere tout ce qui est lie aux erreurs et
 *  par extensions aux exceptions
 */
public class pureErrorHandler {

	//*********************************************************************
	/** Description : Cette classe gere la liste des types d'erreurs gerees
	 *  par la sous-classe exceptions et les methodes de la classe
	 *  pureErrorHandler
	 */
	public class Type{
		// Java classic exceptions
		public static final int Exception = 0;
		public static final int ClassNotFoundException = 1;
		public static final int InstantiationException = 2;
		public static final int NoSuchMethodException = 3;
		public static final int SecurityException = 4;
		public static final int IllegalAccessException = 5;
		public static final int IllegalArgumentException = 6;
		public static final int InvocationTargetException = 7;
		// Web Element(s) exceptions
		public static final int NullPointerException = 11;
		public static final int NoSuchElementException = 12;
		public static final int StaleElementReferenceException = 13;
		public static final int ElementNotVisibleException = 14;
		public static final int ElementNotSelectableException = 15;
		public static final int TimeoutException = 16;
		// AndroidElement(s) exceptions
		
		// Other Exception
		public static final int MalformedURLException = 31;
		// Selenium
		public static final int UnsupportedCommandException = 21;
		// Special Exceptions
		public static final int ValueIsNotABooleanException = 61;
		public static final int invalidCapabilityException = 62;
		public static final int fileDoesNotExistException = 63;
		
		// Custom modules exceptions
		public static final int ZipException = 101;
	}
	
	
	//*********************************************************************
	/** Description : Cette classe gere tout ce qui est lie aux Exceptions
	 */
	public static class exceptions{
		//*********************************************************************
		/** Description : Cette methode renvoie un ID de type pureErrorHandler.Type
		 * 
		 * @param myException : L'exception
		 * @return int        : le type d'erreur selon la liste pureErrorHandler.Type
		 */
		public static int getType( Exception myException ) {
			int thisError = -1;
			if ( myException != null ) {
				System.out.println( "Exception type read : " + myException.getStackTrace().toString() );
				if ( myException.getCause() != null ) {
					System.out.println( "get Cause : " + myException.getCause().toString() );
				}
				System.out.println( "Stack Trace : " + myException.fillInStackTrace() );
				System.out.println( myException.toString() );
				String eXceptionMessage = myException.toString(); 
				if ( eXceptionMessage != null ) {
					int doubleDot = eXceptionMessage.indexOf( ":" );
					if ( doubleDot > -1 ) {
						String exceptionType = eXceptionMessage.substring( 0, doubleDot );
						switch( exceptionType ) {
						// Default java.lang.**** exceptions
						case "java.lang.Exception" : thisError = 0;
						case "java.lang.ClassNotFoundException" : thisError = 1; break;
						case "java.lang.InstantiationException" : thisError = 2; break;
						case "java.lang.NoSuchMethodException" : thisError = 3; break;
						case "java.lang.SecurityException" : thisError = 4; break;
						case "java.lang.IllegalAccessException" : thisError = 5; break;
						case "java.lang.IllegalArgumentException" : thisError = 6; break;
						case "java.lang.reflect.InvocationTargetException" : thisError = 7; break;
						case "java.lang.NullPointerException" : thisError = 11; break;
						case "java.lang.NoSuchElementException" : thisError = 12; break;
						case "java.lang.StaleElementReferenceException" : thisError = 13; break;
						case "java.lang.ElementNotVisibleException" : thisError = 14; break;
						case "java.lang.ElementNotSelectableException" : thisError = 15; break;
						case "java.lang.TimeoutException" : thisError = 16; break;
						case "org.openqa.selenium.UnsupportedCommandException" : thisError = 21; break;
						case "java.net.MalformedURLException" : thisError = 31; break;
						case "pq8.lang.ValueIsNotABooleanException" : thisError = 61; break;
						case "pq8.lang.invalidCapabilityException" : thisError = 62; break;
						case "pq8.files.fileDoesNotExistException" : thisError = 63; break;
						// pureQuantumH8 exceptions
						// External class(es) exceptions
						case "java.util.zip.ZipException" : thisError = 101; break;
						default :
							System.out.println( "pureErrorHandler encountered an unknown exception message : \n" + eXceptionMessage );
							thisError = 0; break;
						}
					}
				}else {
					System.out.println( "Exception message empty, full exception : " + myException );
				}
			}
			return thisError;
		}
	}
	
	//*********************************************************************
	/** Description : Cette classe gere tout ce qui est lie aux Exceptions
	 */
	public static void castError( int errorType, String methodCasted, String Content1, String Content2 ) {
		String castMessage = "";
		switch( errorType ) {
			case Type.ClassNotFoundException : 
				castMessage = "The class %OBJECT1% was not found";
				break;
			case Type.InstantiationException :
				castMessage = "Instantiation of the class %CONTENT1% Exception";
				break;
			case Type.NoSuchMethodException :
				castMessage = "The method %CONTENT2% was not found in the %CONTENT1%";
				break;
			case Type.SecurityException :
				castMessage = "Security Exception reached for %CONTENT1%";
				break;
			case Type.IllegalAccessException :
				castMessage = "Illegal Access Exception for %CONTENT1%";
				break;
			case Type.IllegalArgumentException :
				castMessage = "Illegal Argument Exception for method %CONTENT2% of the class %CONTENT1%";
				break;
			case Type.InvocationTargetException :
				castMessage = "Illegal Argument Exception for the class %CONTENT1%";
				break;
			case Type.NullPointerException :
				castMessage = "Null pointer value for object %CONTENT1%";
				break;
			case Type.NoSuchElementException :
				castMessage = "Requested Element %CONTENT1% ( %CONTENT2% ) does not exists.";
				break;
			case Type.StaleElementReferenceException :
				castMessage = "Requested Element %CONTENT1% ( %CONTENT2% ) does no more exists.";
				break;
			case Type.ElementNotVisibleException :
				castMessage = "Requested Element %CONTENT1% ( %CONTENT2% ) exists but is not visible.";
				break;
			case Type.ElementNotSelectableException :
				castMessage = "Requested Element %CONTENT1% ( %CONTENT2% ) exists but is not selectable.";
				break;
			case Type.TimeoutException :
				castMessage = "Requested operation on element %CONTENT1% ( %CONTENT2% ) cannot be accomplished in the requested time.";
				break;
			case Type.ValueIsNotABooleanException :
				castMessage = "The string value entered %CONTENT1% is not the representation of a boolean (true/false).";
				break;
			case Type.invalidCapabilityException :
				castMessage = "Illegal Capability %CONTENT1%=%CONTENT2% given for the driver will be ignored.";
				break;
			case Type.fileDoesNotExistException :
				castMessage = "The file %CONTENT1% does not exist.";
				break;
			case Type.ZipException :
				castMessage = "";
				break;
			case Type.MalformedURLException :
				castMessage = "the URL entered '%CONTENT1%' is malformed and casted an Exception";
				break;
			case Type.Exception :
				castMessage = "Unknown Error";
		}
		if ( Content1 != null ) { castMessage = castMessage.replace( "%CONTENT1%",  Content1 ); }
		if ( Content2 != null ) { castMessage = castMessage.replace( "%CONTENT2%",  Content2 ); }
		if ( methodCasted != null ) { castMessage = methodCasted + " : " + castMessage;	}
		System.out.println( "ERROR : " + castMessage );
		System.exit( 0 );
	}


}
