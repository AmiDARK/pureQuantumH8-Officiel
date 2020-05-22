package pureQuantumH8;

import java.util.List;

import pureQuantumH8.pureDrivers.pureDriverDetails;

public class pureElements{

	public String             eName          = null;                    // Nom de l'element
	public String             eType          = null;                    // Type d'element
	public String             ePath          = null;                    // Le xPath ou definition de l'objet
	public int                eMethod        = -1;                      // le type de methode e utiliser pour rechercher l'objet ( pureCore.elementMethod.xxx )
	public int                refreshMethod  = 0;                       // 0 = manual (no update once found), 1 = forceUpdate (rediscover on each call),
	                                                                    // 2 = logCat update (memorize logcat to check if update is required or not)
	public String             refrehCode     = null;                    // Last logcat when refreshed ( used with refreshMethod = 2)
	public pureDriverDetails  driverToUse    = null;                    // Store the currentDriver when object is created so it's the driver that will be used for detection/discover/update
	public int                driverLink     = -1;

	public Object             parent         = null;
	
	public List<Object> theElements    = null;                          // The xElement (Web/Mobile/Android/IOS)
	
	public pureElements( String elName, String elType, int elMethod, String elPath, int elRefreshMode, Object parent, int driverLink ) {
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

	public pureElements( String elName, String elType, int elMethod, String elPath, int elRefreshMode, Object parent ) {
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

	public pureElements( String elName, String elType, int elMethod, String elPath, int elRefreshMode, int driverLink ) {
		this.eName = elName;
		this.eType = elType;
		this.ePath = elPath;
		this.eMethod = elMethod;
		this.refreshMethod = elRefreshMode;
		this.refrehCode = null;
		if ( driverLink > -1 ) {
			this.driverToUse = pureDrivers.getDriverDetails( driverLink );
		}else {
			this.driverToUse = null;
		}
		this.driverLink = driverLink;
	}

	public pureElements( String elName, String elType, int elMethod, String elPath, int elRefreshMode ) {
		this.eName = elName;
		this.eType = elType;
		this.ePath = elPath;
		this.eMethod = elMethod;
		this.refreshMethod = elRefreshMode;
		this.refrehCode = null;
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
		this.theElements = null;
	}
	
	public int getMethod() {
		return this.eMethod;
	}
	
	public void setPath( String newPath ) {
		this.ePath = newPath;
		this.theElements = null;
	}
	
	public String getPath() {
		return this.ePath;
	}
	
	public void setBy( int newMethod, String newPath ) {
		this.eMethod = newMethod;
		this.ePath = newPath;
		this.theElements = null;
	}

	public void setRefreshMode( int newRMode ) {
		this.refreshMethod = newRMode;
	}
	
	public int getRefreshMode() {
		return this.refreshMethod;
	}
	
	public void setPureDriver( pureDriverDetails newDriverToUse ) {
		this.driverToUse = newDriverToUse;
		this.driverLink = pureDrivers.getDriverDetailsID( newDriverToUse );
	}

	public int getDriverID() {
		return this.driverLink;
	}

	public List<Object> getElement() {
		return this.theElements;
	}
	
	public void clearElement() {
		this.theElements = null;
	}

	
	
	
	@SuppressWarnings("unused")
	private void refresh() {
//		if ( this.theElement == null || this.refreshMethod == 1 || this.refrehCode != pureCore.getLastLogCat() ) {
			
//		}
		
		
		
	}
}

