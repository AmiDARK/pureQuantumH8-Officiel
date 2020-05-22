package fileTypes;

import org.json.JSONObject;

import fileTypes.raw.rawFileProfile;

public class json {

	public static JSONObject loadJSONObjectFromFile( String fileName ) {
		rawFileProfile jsonToLoad = new rawFileProfile( fileName );
		JSONObject jsonFile = null;
		if ( jsonToLoad.exist() == true ) {
			jsonToLoad.OpenToRead();
				String fileContent = jsonToLoad.GetFileAsString();
				if ( fileContent!= null && fileContent.length() > 0 ) {
					jsonFile = new JSONObject( fileContent );
				}
			jsonToLoad.Closefile();
		}
		return jsonFile;
	}
	
}
