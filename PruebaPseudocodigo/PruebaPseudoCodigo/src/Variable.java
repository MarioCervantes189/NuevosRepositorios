
public class Variable {
    private String type;
    private Object value;

    public Variable(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return  (int) value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getName() {
        return null;
    }
}
