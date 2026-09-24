package model;

public enum OrderState {
    PENDING("PENDIENTE"),
    CANCELLED("CANCELADO"),
    PROCESSED("PROCESADO");

    private final String description;

    OrderState(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
