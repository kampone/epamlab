package by.bsu.textcomposit.textpart;

import by.bsu.textcomposit.typepart.TypePart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by note on 25.02.2015.
 */
public class Composite implements Component {
    private List<Component> components;
    private TypePart type;


    public Composite() {
        components = new ArrayList<Component>();
    }

    public Composite(TypePart type) {
        this();
        this.type = type;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public TypePart getType() {
        return type;
    }

    public void setType(TypePart type) {
        this.type = type;
    }


    @Override
    public boolean addComponent(Component component) {
        return components.add(component);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Composite)) return false;

        Composite composite = (Composite) o;

        if (components != null ? !components.equals(composite.components) : composite.components != null) return false;
        if (type != composite.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = components != null ? components.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Component component : components) {
            str.append(component.toString() + " ");
        }
        return str.toString();
    }


}

