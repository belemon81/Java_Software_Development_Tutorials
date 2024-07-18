package lects.lect06;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A JavaBean which notifies a View when its data changes.
 * This is achieved through the use of PropertyChangeListener.
 */
public class MyClass {

    private String label;
    private PropertyChangeSupport changes =
            new PropertyChangeSupport(this);

    public String getLabel() {
        return label;
    }

    public void setLabel(String newLabel) {
        String oldLabel = label;
        label = newLabel;
        changes.firePropertyChange("label", oldLabel, newLabel);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }
}
