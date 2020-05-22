package fileTypes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;

// import System.Reporting;

public class raw {

	@SuppressWarnings("unused")
	public static class rawFileProfile{
		private boolean AppendMode;
	

		public String FileName;
		private boolean AccessMode;
		private FileWriter filewriter;
		private BufferedWriter bufferedWriter;
		private FileReader filereader;
		private BufferedReader bufferedReader;
		
	
		public rawFileProfile( String FileName ) {
			this.AppendMode = false; // False = new file & Trye = append to existing file
			
			this.FileName = FileName;
			this.filereader = null;
			this.bufferedReader = null;
			this.filewriter = null;
			this.bufferedWriter = null;
			}

		public void OpenToWrite() {
			if ( this.filereader == null && this.filewriter == null ) {
				try {
					// this.FileAccess = new FileOutputStream( System.getProperty("user.dir") + "/" + this.FileName );
					// this.WriteAccess = new DataOutputStream( this.FileAccess );
					this.filewriter = new FileWriter( this.FileName );
					this.bufferedWriter = new BufferedWriter( this.filewriter );
					this.AccessMode = true;
				}catch( Exception e ) {
					System.out.println( "FilesAccess API Error : Cannot Open To Write File '" + this.FileName + "'" );
				}
				
			}
		}

		public void PutString( String rvalue ) {
			if ( this.filewriter != null && this.bufferedWriter != null ) {
				try {
					// this.WriteAccess.writeBytes( rvalue + "\n" );
					this.bufferedWriter.write( rvalue );


				}catch( Exception e ) {
					System.out.println( "raw->PutString : " + e.getLocalizedMessage() );
				}
			}
		}
		
		public void OpenToRead(){
			if ( this.filereader == null && this.filewriter == null ) {
				try {
					// this.ReadAccess = new Scanner( new File( System.getProperty("user.dir") + "/" + this.FileName ) );
					this.filereader = new FileReader( this.FileName );
					this.bufferedReader = new BufferedReader( this.filereader );
					this.AccessMode = false;
				}catch( Exception e ) {
					System.out.println( "FilesAccess API Error : Cannot Open To Read File '" + this.FileName + "'" );
				}
			}
		}

		public boolean exist(){
			boolean exists = false;
			if ( this.filereader == null && this.filewriter == null ) {
				this.AccessMode = false;
				try {
					this.OpenToRead();
					exists = ( this.bufferedReader != null );
					if ( exists == true ) {
						this.Closefile();
					}
				}catch( Exception e ) {
					exists = false;
				}
			}
			return exists;
		}

		public String GetString() {
			String rValue = null;
			// if ( this.ReadAccess.hasNextLine() ) {
			// 	rValue = this.ReadAccess.nextLine();
			// }
			try {
				rValue = this.bufferedReader.readLine();
			}catch( Exception e ){
				System.out.println( e.getLocalizedMessage() );
			}
			return rValue;
		}

		public String GetFileAsString() {
			String rValue = null;
			String tempVal = null;
			// if ( this.ReadAccess.hasNextLine() ) {
			// 	rValue = this.ReadAccess.nextLine();
			// }
			try {
				rValue = "";
				boolean success = false;
				while( success == false ) {
					tempVal = this.bufferedReader.readLine();
					if (tempVal != null ) {
						rValue = rValue.concat( tempVal );
					}else {
						success = true;
						
					}
				}
					
			}catch( Exception e ){
				System.out.println( e.getLocalizedMessage() );
			}
			return rValue;
		}

		public void Closefile() {
			if ( this.bufferedReader != null ) {
				try {
					this.bufferedReader.close();
					this.filereader.close();
					this.bufferedReader = null;
					this.filereader = null;
				}catch( Exception e ) {
					System.out.println( e.getLocalizedMessage() );
				}
			}else {
				try {
					this.bufferedWriter.close();
					this.filewriter.close();
					this.bufferedWriter = null;
					this.filewriter = null;
				}catch( Exception e ) {
					System.out.println( e.getLocalizedMessage() );
				}
			}
		}
	}


	public rawFileProfile RawFile( String FileName ) {
		return new rawFileProfile( FileName );
	}
	
	public static String[] getDirectoryContent( String xPath ) {
		File Folder = new File( xPath );
		String[] list = Folder.list( new FilenameFilter()
			{
				@Override
				public boolean accept(File Folder, String name) {
					return name.length() > 0;
				}
			}
		);
		return list;
	}

	
}
