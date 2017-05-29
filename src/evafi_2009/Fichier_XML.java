package evafi_2009;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Classe h�ritant de la classe File. Elle permet de manipuler les fichiers XML
 * @see File
 * @version   $Revision: 1.00, $Date: 2007/10/01
 * @author    BIRK Arnaud
 */

public class Fichier_XML extends File {

	private static final long serialVersionUID = 4697556987877651910L;
	/**Champs repr�sentant un objet Document repr�sentant le contenu d'un Fichier XMl sous forme d'objet
	 * @see Document*/
	private org.jdom.Document documentXML = null;

	/**Ce constructeur cr�er un objet repr�sentant un fichier xml � partir de son chemin
	 * Ce fichier est vide de tout �l�ment.
	 * @param _path le chemin vers le fichier
	 * @throws JDOMException
	 * @throws IOException*/
	public Fichier_XML(String _path) throws JDOMException, IOException {
		super(_path);
		SAXBuilder sax = new SAXBuilder();
		documentXML = sax.build(this);
	}

	/**
	 * Constructeur permettant de cr�er un objet repr�sentant un fichier xml � partir de son chemin
	 * Ce fichier est initialis� avec un �l�ment root.
	 * @param _path le chemin vers le fichier
	 * @param rootElement le noeud racine @see Element*/
	public Fichier_XML(String _path, Element rootElement) {
		super(_path);
		this.documentXML = new Document(rootElement);
	}
	
	/**Constructeur permet de cr�er un objet repr�sentant un fichier xml � partir d'une url
	 * @param url l'url du fichier
	 * @see URL
	 * @throws URISyntaxException
	 * @throws JDOMException
	 * @throws IOException*/
	public Fichier_XML(URL url) throws URISyntaxException, JDOMException, IOException{
		super(url.getFile());
		SAXBuilder sxb = new SAXBuilder();
		
			try {
				this.documentXML = sxb.build(url);
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				throw e;
				//e.printStackTrace();
			}
	}
	
	/**M�thode permettant d'enregistrer le fichier
	 * Cette m�thode ne fonctionne si le constructeur a �t� initialis� � partir d'une url*/
	public void enregistreFichier() {
		enregistreFichier(this.getAbsolutePath());
	}

	/**M�thode enregistre le fichier � l'ndroite sp�cifi�
	 * @param path le chemin ou le fichier doit-�tre enregistr� */
	public void enregistreFichier(String path) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			
			sortie.output(documentXML, fos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * M�thode renvoyant l'objet repr�sentant le document XML
	 * @return  Document
	 * @see  Document
	 * @uml.property  name="documentXML"
	 */
	public org.jdom.Document getDocumentXML() {
		return documentXML;
	}
	
	/**M�thode renvoyant la racine du document
	 * @return Element
	 * @see Element*/
	public org.jdom.Element getRacine() {
		return documentXML.getRootElement();
	}
	
	/**
	 * M�thode permettant de configurer le document du fichier XML Ce document remplace l'ancien 
	 * @param documentXML  le nouveau document
	 * @see  Document
	 * @uml.property  name="documentXML"
	 */
	public void setDocumentXML(org.jdom.Document documentXML) {
		this.documentXML = documentXML;
	}

	/**M�thode permettant de configurer la noeud du document XML
	 @param racine l'�l�ment rempla�ant
	 @see Element*/
	public void setRacine(org.jdom.Element racine) {
		this.getDocumentXML().setRootElement(racine);
	}
}
