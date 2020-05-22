package sourceDrivers;

import pureQuantumH8.pureDrivers.pureDriverDetails;

public class driverTemplate {

	// ********************************************************************************
	/** 
	 * Description : startDriver( pureDriverDetails ) open the driver configured inside pureDriver
	 * 
	 * @param myDriver : the driver structure to use to open new driver
	 * 
	 * @return -
	 */
	public static void startDriver( pureDriverDetails myDriverDetails ) {
		
	}

	// ********************************************************************************
	/** 
	 * Description : quitDriver( pureDriverDetails ) close the specified driver
	 * 
	 * @param myDriver : the driver structure to use
	 * 
	 * @return -
	 */
	public void quitDriver( pureDriverDetails myDriverDetails ) {

	}

	// ********************************************************************************
	/** 
	 * Description : updateDriver( pureDriverDetails ) try to see if a newer version of the driver is available (or not).
	 * 
	 * @param myDriver : the driver structure to use
	 * 
	 * @return -
	 */
	 public static void updateDriver( pureDriverDetails myDriverDetails ) {
		 
	 }

	private String pageDetails = null;           // the Page DOM
	private int pageSize = -1;                   // The page size in bytes
	private Long LastLoadedTime = 0L;            // Last time (clock) the page was updated

	public static void GetDisplayArea( pureDriverDetails myDriver ) {
	}

	public boolean isPageUpdated( pureDriverDetails myDriver ) {
		boolean isUpdated = false;
		return isUpdated;
	}

	public int getPageSize() {
		return pageSize;
	}
	
	public Long getLastLoadingTime() {
		return LastLoadedTime;
	}

	public String getLastDOMDetails() {
		return pageDetails;
	}

}

