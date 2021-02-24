package ua.com.conductor.model.dto;

import javax.validation.constraints.NotNull;

public class SessionRequestDto {
    @NotNull
    private String showTime;
    @NotNull
    private Long eventId;
    @NotNull
    private Long locationId;

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }
}
