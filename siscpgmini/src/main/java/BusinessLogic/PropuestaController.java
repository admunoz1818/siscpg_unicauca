package BusinessLogic;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.camunda.bpm.engine.cdi.BusinessProcess;
import Controllers.PropuestaBL;
import Entities.PropuestaEntity;


@Named
@ConversationScoped
public class PropuestaController implements Serializable{
	private static final long serialVersionUID = 1L;
	@Inject
	private BusinessProcess businessProcess;
	@PersistenceContext
	private EntityManager entityManager;
	@Inject
	private PropuestaBL propuestaBL;
	private PropuestaEntity propuestaEntity;
	
	public PropuestaEntity getPropuestaEntity(){
		if(propuestaEntity == null){
			propuestaEntity = propuestaBL.getPropuesta((Long) businessProcess.getVariable("propuestaId"));
		}
		return propuestaEntity;
	}
	
}
