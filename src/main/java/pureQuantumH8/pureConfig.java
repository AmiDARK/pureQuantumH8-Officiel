package pureQuantumH8;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import fileTypes.json;

public class pureConfig {

	public static List<pureCapability> loadCapabilitiesFromFile( String fileName ){
		return loadJsonDataFromFile( fileName, "capabilities" );
	}
	
	public static List<pureCapability> loadJsonDataFromFile( String fileName, String ObjectType ){
		List<pureCapability> newCapabilities = null; // new ArrayList<pureCapability>();
		JSONObject jsonCaps = json.loadJSONObjectFromFile( fileName );
		if ( jsonCaps != null) {
			// Now we search for the capabilities list to extract it
			JSONObject capabilities = jsonCaps.getJSONObject( ObjectType ); 
			JSONArray capsArray = capabilities.names();
			if ( capsArray.length() > 0 ) {
				newCapabilities = new ArrayList<pureCapability>();
				for( int cLoop = 0; cLoop < capsArray.length(); cLoop++ ){
					String capsToAdd = capsArray.getString( cLoop );
					String capsValue = capabilities.getString( capsToAdd );
					newCapabilities.add( new pureCapability( capsToAdd, capsValue ) );
					System.out.println( "new capability to add : " + capsToAdd + " = " + capsValue );
				}
			}else {
				pureErrorHandler.castError( -1, "[pureQuantumH8] File %CONTENT1% does not contains empty capabilities data", fileName, null );
			}
		}else {
			pureErrorHandler.castError( -1, "[pureQuantumH8] FileNotExistException : pureConfig.loadCapabilitiesfromFile cannot load file %CONTENT1%", fileName, null );
		}
		return newCapabilities;
	}

	public static pureDeviceCfg loadDeviceConfigFromFile( String fileName ) {
		pureDeviceCfg newDeviceCfg = null;
		JSONObject jsonDeviceCfg = json.loadJSONObjectFromFile( fileName );
		if ( jsonDeviceCfg != null ) {
			// newDeviceCfg = new pureDeviceCfg( );
		}
		return newDeviceCfg;	
	}

	public static String findCapabilityByName( String capabilityName, List<pureCapability> capabilityList ) {
		String feedback = null;
		if ( capabilityList != null && capabilityList.size() > 0 ) {
			for( int cLoop = 0; cLoop < capabilityList.size(); cLoop++ ) {
				if ( capabilityList.get( cLoop ).capName.equals( capabilityName ) ) {
					feedback = capabilityList.get( cLoop ).capValue;
					break;
				}
			}
		}
		return feedback;
	}
	
	/** *********************************************************************************************************************
	 *  Class Name : pureDeviceCfg
	 *  
	 *   Description : Structure to define a device for an android/ios driver
	 *   
	 *   @author : Frédéric Cordier
	 *
	 ********************************************************************************************************************** */
	public static class pureDeviceCfg{
		
		protected String               deviceName            = null;
		protected String               deviceOSVersion       = null;
		protected String               deviceUDID            = null;
		protected List<pureCapability> deviceCaps            = null;
		protected String               deviceAVDFile         = null;
		
		pureDeviceCfg( String deviceName, String deviceOSVersion, String deviceUDID, List<pureCapability> deviceCaps, String deviceAVDFileName ){
			this.deviceName = deviceName;
			this.deviceOSVersion = deviceOSVersion;
			this.deviceUDID = deviceUDID;
			this.deviceCaps = deviceCaps;
			this.deviceAVDFile = deviceAVDFileName;
		}
		
		pureDeviceCfg( String deviceName, String deviceOSVersion, String deviceUDID, List<pureCapability> deviceCaps ){
			this.deviceName = deviceName;
			this.deviceOSVersion = deviceOSVersion;
			this.deviceUDID = deviceUDID;
			this.deviceCaps = deviceCaps;
			this.deviceAVDFile = null;
		}

		pureDeviceCfg( String deviceName, String deviceOSVersion, String deviceUDID ){
			this.deviceName = deviceName;
			this.deviceOSVersion = deviceOSVersion;
			this.deviceUDID = deviceUDID;
			this.deviceCaps = null;
			this.deviceAVDFile = null;
		}

	}
		
	/** *********************************************************************************************************************
	 *  Class Name : Capabilities
	 *  
	 *   Description : Driver to define capabilities that will be used inside with the driver
	 *   
	 *   @author : Frédéric Cordier
	 *
	 ********************************************************************************************************************** */
	public static class pureCapability {
		public String capName                           = null;
		public String capValue                          = null;
		
		public pureCapability( String capName, String capValue ) {
			this.capName = capName;
			this.capValue = capValue;
		}
		
		public boolean capValueToBoolean() {
			return this.capValue.toLowerCase().equals( "true" );
		}

		public int capValueToInteger() {
			return Integer.valueOf( this.capValue );
		}

	}

}
