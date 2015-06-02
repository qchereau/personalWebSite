package util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class PrimefacesUtil {

	/**
	 * Transmet le message et l'affice
	 * 
	 * @param message message à afficer
	 */
	public static void displayMessage(String message){
		displayMessage(FacesMessage.SEVERITY_INFO, message);
	}
	
	/**
	 * Transmet le message
	 * 
	 * @param severity severityé du message
	 * @param message message à afficher
	 */
	public static void displayMessage(Severity severity, String message){
        FacesMessage fMessage = new FacesMessage(severity, message,  null);
        FacesContext.getCurrentInstance().addMessage(null, fMessage);		
	}
}
