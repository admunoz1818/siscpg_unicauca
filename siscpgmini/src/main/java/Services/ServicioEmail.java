package Services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.context.Context;

/**
 *
 * @author pcblanco
 */
public class ServicioEmail implements TaskListener{
    
    private String remitente;
    private String contrasena; 
    private String destinatario;
    private String cuerpoMensaje;
    private String mensaje;
    private String asunto;
    
    private final String formatoCuerpo = "<div style='background-color:#18457C; width:459px;'><img src='http://imageshack.com/a/img537/148/ODwI2A.png'>"
       + "<div style='color:black; font-size:16px;padding:15px'><br><h4 style='color:white;'>Cordial Saludo,</h4><br>"
       + "<p style='color:white; font-family: sans-serif; font-size: 14px;'> %s </p><br>"
       + "</div> <div style='color:white;font-size:11px; padding:15px'> <i>Por favor no responda a este correo electrónico.</i>" 
       + " <p>Este correo y sus anexos contienen información confidencial de la Universidad del Cauca, la cual solo está dirigida "
       + "a la persona o entidad listada arriba. Cualquier uso indebido de la información contenida en este correo por personas "
       + "diferentes a las dirigidas, es prohibido. Si recibe este correo por error, favor notificar al remitente y borrarlo.</p>"
       + "</div> <img src='http://imageshack.com/a/img913/3982/U2td48.png'> </div>";
    private final static Logger LOGGER = Logger.getLogger(ServicioEmail.class.getName());
	

    public ServicioEmail() {
    	remitente = "admunoz1818@gmail.com";
    	contrasena = "adrian@121";
    	cuerpoMensaje = "PRUEBA";
    	asunto = "prueba";    	
    }
    
    public ServicioEmail(String to, String asunto) {
        this.remitente = "admunoz1818@gmail.com";
        this.contrasena = "adrian@121";
        this.asunto = asunto;        
    }
    
    public void notify(DelegateTask tareaDelegada) {
    	
		Properties properties = System.getProperties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.user", remitente);
		Session session = Session.getDefaultInstance(properties);
    	
    	String assignee = tareaDelegada.getAssignee();
	    //String taskId = delegateTask.getId();

	    if (assignee != null) {
	      
	      // Get User Profile from User Management
	      IdentityService servicioIdentidad = Context.getProcessEngineConfiguration().getIdentityService();
	      User usuario = servicioIdentidad.createUserQuery().userId(assignee).singleResult();

	      if (usuario != null) {
	        
	        // Get Email Address from User Profile
	        destinatario = usuario.getEmail();
	        
	        if (destinatario != null && !destinatario.isEmpty()) {
	        	
	        	try {
	                Message message = new MimeMessage(session);
	                message.setFrom(new InternetAddress(this.remitente));
	                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
	                
	                //Identificando que tipo de mensaje enviar
	                if(tareaDelegada.getName().equals("Enviar Correo de Rechazo"))
	                	this.notificacionPropuestaNoAprobada();
	                else if(tareaDelegada.getName().equals("Realizar Anteproyecto"))
	                	this.notificacionPropuestaAprobada();
	                
	                
	                message.setSubject(this.asunto);
	                message.setContent(this.mensaje, "text/html");                
	                
	                Transport t = session.getTransport("smtp");
	                t.connect(remitente,contrasena);
	                t.sendMessage(message,message.getAllRecipients());
	                
	                t.close();
	                
	            System.out.println("Enviar Correo");
	            LOGGER.info("Task Assignment Email successfully sent to user '" + assignee + "' with address '" + destinatario + "'.");            

	          } catch (Exception e) {
	        	  System.out.println("Exception: "+e.getStackTrace());
	            LOGGER.log(Level.WARNING, "Could not send email to assignee", e);
	          }

	        } else {
	          System.out.println("Recipient null or empty");
	          LOGGER.warning("Not sending email to user " + assignee + "', user has no email address.");
	        }

	      } else {
	    	  System.out.println("User null");
	        LOGGER.warning("Not sending email to user " + assignee + "', user is not enrolled with identity service.");
	      }

	    }//Added lines
	    else{
	    	System.out.println("Assigne null");
	    }
    }

    public void notificacionPropuestaNoAprobada() {
    	setAsunto("Propuesta de formato A");
    	setCuerpoMensaje("Su propuesta de ante-proyecto NO ha sido aprobada, por favor comunicarse con el docente encargado");
    	setMensaje(String.format(formatoCuerpo, cuerpoMensaje));
    }

    public void notificacionPropuestaAprobada() {
    	setAsunto("Propuesta de formato A");
    	setCuerpoMensaje("Su propuesta para anteProyecto ha sido aprobada.");
    	setMensaje(String.format(formatoCuerpo, cuerpoMensaje));
    }
    
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}  

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getCuerpoMensaje() {
        return cuerpoMensaje;
    }

    public void setCuerpoMensaje(String cuerpoMensaje) {
        this.cuerpoMensaje = cuerpoMensaje;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
