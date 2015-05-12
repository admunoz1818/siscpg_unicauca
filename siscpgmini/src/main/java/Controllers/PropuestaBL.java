package Controllers;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import Entities.PropuestaEntity;

@Stateless
@Named
public class PropuestaBL {
	@PersistenceContext
	private EntityManager entityManager;

	private static Logger LOGGER = Logger
			.getLogger(PropuestaBL.class.getName());
	//LOGGER.log(Level.INFO, "\n\n\n {0} \n\n\n", new String[]{(String) variables.get("titulo")});
	
	public void registrarPropuesta(DelegateExecution delegateExecution){
		PropuestaEntity propuestaEntity = new PropuestaEntity();
		Map<String, Object> variables = delegateExecution.getVariables();
		propuestaEntity.setTitulo((String) variables.get("titulo"));
		propuestaEntity.setNumEstudiantes(Integer.valueOf(variables.get("numEstudiantes").toString()));
		propuestaEntity.setObjetivos((String) variables.get("objetivos"));
		propuestaEntity.setAportes((String) variables.get("aportes"));
		propuestaEntity.setTiempoEstimado(Integer.valueOf(variables.get("tiempoEstimado").toString()));
		propuestaEntity.setCondicionesEntrega((String) variables.get("condicionesEntrega"));
		propuestaEntity.setRecursosRequeridos((String) variables.get("recursosRequeridos"));
		propuestaEntity.setFuentesFinanciacion((String) variables.get("fuentesFinanciacion"));
		propuestaEntity.setObjetivos((String) variables.get("observaciones"));
		entityManager.persist(propuestaEntity);
		entityManager.flush();
		//delegateExecution.removeVariables(variables.keySet());
		//delegateExecution.setVariable("propuestaId", propuestaEntity.getId());
	}
	
	public void aprobarPropuesta(DelegateExecution delegateExecution){
		PropuestaEntity propuestaEntity = new PropuestaEntity();
		Map<String, Object> variables = delegateExecution.getVariables();
		//LOGGER.log(Level.INFO, "\n\n\n {0} \n\n\n", new String[]{(String) variables.get("titulo")});
		//PropuestaEntity propuestaEntity = getPropuesta((String) variables.get("titulo"));
	}
	
	public PropuestaEntity getPropuesta(Long propuestaId){
		return entityManager.find(PropuestaEntity.class, propuestaId);
	}

}
