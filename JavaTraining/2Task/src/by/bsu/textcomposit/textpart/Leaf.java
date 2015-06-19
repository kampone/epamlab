package by.bsu.textcomposit.textpart;

import by.bsu.textcomposit.typepart.TypePart;

import java.util.List;

/**
 * Created by note on 25.02.2015.
 */
public class Leaf implements Component {

    private TypePart type;
    private String content;


    public Leaf(String content, TypePart type) {
        this.type = type;
        this.content = content;
    }

    @Override
    public boolean addComponent(Component component) {
        return false;
    }

    public TypePart getType() {
        return type;
    }

    public void setType(TypePart type) {
        this.type = type;
    }
    public List<Component> getComponents(){
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Leaf)) return false;

        Leaf leaf = (Leaf) o;

        if (content != null ? !content.equals(leaf.content) : leaf.content != null) return false;
        if (type != leaf.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return content;
    }
}
