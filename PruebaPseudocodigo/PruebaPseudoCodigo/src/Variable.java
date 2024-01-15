
public class Variable {
    private String type;
    private Integer value;

    public Variable(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = (Integer) value;
    }

    
}
