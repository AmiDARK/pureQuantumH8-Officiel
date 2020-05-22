package fileTypes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


public class zip {

	public static boolean unZip( String Source, String Target ) {
		boolean success = false;
		int length = 0;
		try {
			byte[] fbuffer = new byte[1024 ];
			ZipInputStream sourceStream = new ZipInputStream( new FileInputStream( Source ) );
			ZipEntry sourceEntry = sourceStream.getNextEntry();
			while( sourceEntry != null ) {
				String fileToOutput = sourceEntry.getName();                                    // Nom du prochain fichier � extraire
				if ( fileToOutput.endsWith( "/" ) ) {
					new File( Target + fileToOutput ).mkdirs();
				}else {
					File newOutputFile = new File ( Target + fileToOutput );
					FileOutputStream StreamToOutput = new FileOutputStream( newOutputFile );
					while( ( length = sourceStream.read( fbuffer ) ) > 0 ) {
						StreamToOutput.write( fbuffer, 0, length );
					}
					StreamToOutput.close();
				}
				sourceEntry = sourceStream.getNextEntry();                                     // R�cup�re le nom du prochain fichier � extraire sinon null.
				success = true;
			}
			sourceStream.close();
		}catch( IOException e ) {
			
		}
		return success;
	}

	public static boolean Zip( List<String> sourceFiles, String targetFile ) {
		boolean success = false;
		if ( sourceFiles != null && sourceFiles.size() > 0 ) {
			try {
				FileOutputStream outputStream = new FileOutputStream( targetFile );
				ZipOutputStream zipOutput = new ZipOutputStream( outputStream );
				for( String sourceFile : sourceFiles ) {
					File fileToZip = new File( sourceFile );
					FileInputStream inputStream = new FileInputStream( fileToZip );
					ZipEntry zipEntry = new ZipEntry( fileToZip.getName() );
					zipOutput.putNextEntry( zipEntry );
					byte[] bytes = new byte[ 1024 ];
					int length;
					while( ( length = inputStream.read( bytes ) ) >= 0 ) {
						zipOutput.write( bytes, 0, length );;
					}
					inputStream.close();
				}
				zipOutput.close();
				outputStream.close();
				success = true;
			}catch( IOException e) {
				
			}			
		}
		return success;
	}
	
}
