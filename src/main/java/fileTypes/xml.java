package fileTypes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class xml {

	// *********************************************************************************************************************** Methode qui charge un XMl disque en memoire en utilisant un profil xml
	public xmlProfile load( String FileName ){
		return new xmlProfile( FileName, true );
	}
	
	// *********************************************************************************************************************** Methode qui sauvegarde un XML memoire vers un fichier
	public void save( xmlProfile xmlFile ) {
		xmlFile.save();
	}

	// *********************************************************************************************************************** Methode qui sauvegarde un XML memoire vers un fichier
	public void save( xmlProfile xmlFile, String FileName ) {
		xmlFile.save( FileName );
	}
	
	// *********************************************************************************************************************** Classe pour les profils XML et les commandes associees
	public static class xmlProfile{
		private Document xmlFile;
		private String FileName;
		
		// ******************************************************************************************************************* Creation d'un profil XML
		public xmlProfile( String FileName ) {
			this.xmlFile = null;
			this.FileName = FileName;
		}
		
		// ******************************************************************************************************************* Creation d'un profil XML avec auto-chargement si Load = true
		public xmlProfile( String FileName, boolean Load ) {
			this.FileName = FileName;
			if ( Load == true ) {
				this.load();
			}else {
				this.xmlFile = null;
			}
		}

		// ******************************************************************************************************************* Charge un XML fichier vers la memoire
		public void load() {
			System.out.println( "fileTypes/xml/Opening XML File (read) : " + FileName );
			// Create the new Document builder support
			try {
				DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				this.xmlFile = dBuilder.parse( new File( this.FileName ) );
				System.out.println( "fileTypes/xml/File '" + FileName + "' correctly loaded in memory" );
			} catch ( ParserConfigurationException eXception_Message ) {
				eXception_Message.printStackTrace(); 
			} catch (SAXException eXception_Message ) {
				eXception_Message.printStackTrace();
			} catch (IOException eXception_Message ) {
				eXception_Message.printStackTrace();
			}catch( Exception eXception_Message ) {
				System.out.println( "fileTypes/xml/Error : Error occured when trying to load XML file");
			}
		}

		// ******************************************************************************************************************* Sauvegarde le XML de la memoire vers un fichier
		public void save() {
			save( this.FileName );
		}

		// ******************************************************************************************************************* Sauvegarde le XML de la memoire vers un fichier
		public void save( String newFileName ) {
			try {
				Transformer trObject = TransformerFactory.newInstance().newTransformer();
				trObject.setOutputProperty( OutputKeys.INDENT, "yes" );
				trObject.setOutputProperty( OutputKeys.METHOD, "xml" );
				trObject.setOutputProperty( OutputKeys.ENCODING, "UTF-8" );
				trObject.setOutputProperty( OutputKeys.DOCTYPE_SYSTEM, "roles.dtd" );
				trObject.setOutputProperty( "{http://xml.apache.org/xslt]indent-amount",  "4" );
				trObject.transform( new DOMSource( this.xmlFile ), new StreamResult( new FileOutputStream( newFileName ) ) );
				this.FileName = newFileName;
			}catch( TransformerException eXception_Message ) {
				System.out.println( eXception_Message.getMessage() );
			} catch (IOException eXception_Message ) {
				System.out.println( eXception_Message.getMessage() );
			}
		}
	
		public NodeList getItems( String xpath ) {
			NodeList myList = this.xmlFile.getElementsByTagName( xpath );
			return myList;
		}

		public int getItemAmount( String xpath ) {
			NodeList myList = getItems( xpath );
			int amount = 0;
			if ( myList != null && myList.getLength() > 0 ) {
				amount = myList.getLength();
			}
			return amount;
		}
		
	}

}
