/**
 * 
 */
package com.ewhale.points.web.managedbean.main;

/**
 * @author Ayman Yosry
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class ErrorHandler extends AbsoluteBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getStatusCode()
	{
		String val = String
				.valueOf((Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("javax.servlet.error.statusCode"));
		return val;
	}

	public String getMessage()
	{
		String val = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("javax.servlet.error.message");
		return val;
	}

	public String getExceptionTrace()
	{
		String val = FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("javax.servlet.error.trace").toString();
		return val;
	}

	public String getException()
	{
		Object val = ((Exception) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("javax.servlet.error.exception"));
		return (val!=null)?val.toString():null;
	}
}