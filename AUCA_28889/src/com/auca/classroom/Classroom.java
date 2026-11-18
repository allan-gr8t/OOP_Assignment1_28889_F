
package com.auca.classroom;

import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;
import com.auca.exceptions.ValidationException;

public class Classroom implements Identifiable, Registrable {

    private String classroomId;
    private String buildingName;
    private String roomNumber;
    private int capacity;
    private int floorNumber;
    private boolean available;

    public Classroom(String classroomId, String buildingName,
                     String roomNumber, int capacity,
                     int floorNumber, boolean available) {

        this.classroomId = classroomId;
        this.buildingName = buildingName;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.floorNumber = floorNumber;
        this.available = available;
    }

    @Override
    public String getId() {
        return classroomId;
    }

    @Override
    public String getDisplayName() {
        return buildingName + " - Room " + roomNumber;
    }

    @Override
    public boolean isActive() {
        return available;
    }

    @Override
    public void validate() throws ValidationException {

        if (classroomId == null || classroomId.trim().isEmpty())
            throw new ValidationException("Classroom ID is required.");

        if (buildingName == null || buildingName.trim().length() < 2)
            throw new ValidationException("Invalid building name.");

        if (roomNumber == null || roomNumber.trim().isEmpty())
            throw new ValidationException("Room number is required.");

        if (capacity <= 0)
            throw new ValidationException("Capacity must be greater than zero.");

        if (floorNumber < 0)
            throw new ValidationException("Floor cannot be negative.");
    }

    @Override
    public void printDetails() {
        System.out.println(this);
    }

    @Override
    public String getRegistrationSummary() {
        return getDisplayName() +
                " | Capacity: " + capacity +
                " | Available: " + available;
    }

    // Getters
    public String getClassroomId() { return classroomId; }
    public String getBuildingName() { return buildingName; }
    public String getRoomNumber() { return roomNumber; }
    public int getCapacity() { return capacity; }
    public int getFloorNumber() { return floorNumber; }
    public boolean isAvailable() { return available; }

    // Setters
    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "classroomId='" + classroomId + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", capacity=" + capacity +
                ", floorNumber=" + floorNumber +
                ", available=" + available +
                '}';
    }
}
