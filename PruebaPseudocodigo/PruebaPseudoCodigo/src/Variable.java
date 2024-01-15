
public class Variable {
    private String type;
    private double value;

    public Variable(String type, double value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = (double) value;
    }

    
}
