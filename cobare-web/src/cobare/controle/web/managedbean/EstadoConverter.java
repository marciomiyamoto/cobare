package cobare.controle.web.managedbean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import cobare.dominio.Estado;

//@FacesConverter(value = "classeConverter")    
@FacesConverter(forClass = Estado.class)
public class EstadoConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Estado) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
//        if (value instanceof Estado) {
//        	Estado entity= (Estado) value;
//            if (entity != null && entity instanceof Estado && entity.getId() != 0) {
//                uiComponent.getAttributes().put( entity.getId().toString(), entity);
//                return entity.getId().toString();
//            }
//        }
        return "";
    }
}