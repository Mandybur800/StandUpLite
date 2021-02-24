package ua.com.conductor.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class LocationRequestDto {
    @Min(10)
    private int capacity;
    private String description;
    @NotNull
    private String address;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
