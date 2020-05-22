package fileTypes;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;

public class aFiles {

	public static boolean getPathExist( String pathToCheck ) {
		return Files.exists( Paths.get( pathToCheck ), LinkOption.NOFOLLOW_LINKS ); 
		}

	public static boolean getFileExist( String fileName ) {
		File fileToCheck = new File( fileName );
		return fileToCheck.exists();
	}
	
    public static void MakeDir( String newFolder ) {
    	new File( newFolder ).mkdirs();
   	}

	public static String[] getFolderContent( String xPath, final String Filter ) {
		File Folder = new File( System.getProperty("user.dir") + xPath );
		String[] list = null;
		if ( Filter != null && Filter.length() > 0 ) {
			list = Folder.list( new FilenameFilter() {
					@Override
					public boolean accept(File Folder, String name) {
						return name.toLowerCase().endsWith( Filter );
					}
				}
			);
		}else {
			list = Folder.list(new FilenameFilter() {
					@Override
					public boolean accept(File Folder, String name) {
						return name.length() > 0;
					}
	  			}
			);
		}
		return list;
	}

	public static String[] getFolderContent( String xPath ) {
		return getFolderContent( xPath, null );
	}
	
	public static void DeleteFile( String SourceFile ) {
		File newFile = new File( SourceFile );
		newFile.delete();
	}

}
