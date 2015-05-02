package personalWebSite;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="localeHandler")
@SessionScoped
public class LocaleHandler {
	
	//langue de l'utilisateur
	Locale browserLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	
	//Ensemble des messages de l'application
	private ResourceBundle bundle;

	private static final String FILENAME = "\\messages\\label";
	
	@PostConstruct
	public void init(){
		File dir = new File("/messages");
		dir.mkdir();
		File test = new File(FILENAME+".properties"); 
		try {
			test.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.bundle = ResourceBundle.getBundle(FILENAME,browserLocale);
	}
	
	public String translate(String elementToTranslate){
		try{
			return bundle.getString(elementToTranslate);
		}catch(MissingResourceException e){
			//might happen if ressource unknown
			return elementToTranslate;
		}
	}
	
	public void setLocale(String locale){
		browserLocale = new Locale(locale);
		init();
	}
}
