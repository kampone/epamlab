package by.bsu.textcomposit.textpart;

import by.bsu.textcomposit.typepart.TypePart;


import java.util.List;

/**
 * Created by note on 25.02.2015.
 */
public interface Component {

    public List<Component> getComponents();
    public boolean addComponent(Component component);
    public TypePart getType();

}
