package com.aws.lambda_to_lambda_service.domain;

public class Runner {
    
    private String runnerName;
    private long totalDistance;
    private int age;
    private String bloodGroup;
    private String emergencyContactName;
    private long emergencyContactNumber;
    
    public String getRunnerName() {
        return runnerName;
    }
    public void setRunnerName(String runnerName) {
        this.runnerName = runnerName;
    }
    public long getTotalDistance() {
        return totalDistance;
    }
    public void setTotalDistance(long totalDistance) {
        this.totalDistance = totalDistance;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getBloodGroup() {
        return bloodGroup;
    }
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    public String getEmergencyContactName() {
        return emergencyContactName;
    }
    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }
    public long getEmergencyContactNumber() {
        return emergencyContactNumber;
    }
    public void setEmergencyContactNumber(long emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }
    
    public Runner(String runnerName, long totalDistance, int age, String bloodGroup,
            String emergencyContactName, long emergencyContactNumber) {
        super();
        this.runnerName = runnerName;
        this.totalDistance = totalDistance;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactNumber = emergencyContactNumber;
    }
    
    public Runner() {
        
    }
    
    
}
