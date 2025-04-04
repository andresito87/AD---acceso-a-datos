package persona;

import java.beans.*;

/**
 *
 * @author andres
 */
public class EditorComponenteDireccion extends PropertyEditorSupport {

    private final PanelComponenteDireccion panelEditor = new PanelComponenteDireccion();

    @Override
    public boolean supportsCustomEditor() {
        return true;
    }

    @Override
    public java.awt.Component getCustomEditor() {
        return panelEditor;
    }

     @Override
    public Object getValue() {
        return new ComponenteDireccion(
                panelEditor.txtDireccion.getText(),
                panelEditor.txtPoblacion.getText(),
                panelEditor.txtProvincia.getText()
        );
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof ComponenteDireccion direccion) {
            panelEditor.txtDireccion.setText(direccion.getDireccion());
            panelEditor.txtPoblacion.setText(direccion.getPoblacion());
            panelEditor.txtProvincia.setText(direccion.getProvincia());
            super.setValue(direccion);
        } else {
            super.setValue(null);
        }
    }

    @Override
    public String getJavaInitializationString() {
        ComponenteDireccion direccion = (ComponenteDireccion) getValue();
        return "new ComponenteDireccion(\"" +
                direccion.getDireccion() + "\", \"" +
                direccion.getPoblacion() + "\", \"" +
                direccion.getProvincia() + "\")";
    }
}
