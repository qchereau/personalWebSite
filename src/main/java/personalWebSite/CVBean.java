package personalWebSite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.tomcat.util.http.fileupload.IOUtils;

@ManagedBean(name="cVBean")
@RequestScoped
public class CVBean {

	public void returnCV() throws IOException {
		File cvFile = new File(System.getProperty("user.dir")+"\\..\\WebContent\\cv.Pdf");

		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();

		ec.responseReset();
		ec.setResponseContentType("application/pdf");
		ec.setResponseContentLength((int) cvFile.length()); // Set it with the file size. This header is optional. It will work if it's omitted, but the download progress will be unknown.
		ec.setResponseHeader("Content-Disposition", "attachment; filename=\"cv.pdf\""); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.

		try(OutputStream output = ec.getResponseOutputStream();FileInputStream is = new FileInputStream(cvFile);){
			IOUtils.copy(is, output);
			is.close();
			output.close();
		}

		fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
	}
}
