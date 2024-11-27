package com.shivamingale.backend.exception;

public class AppException extends RuntimeException {
    private final String resourceName;
    private final String action;
    private final String errorCode;

    public AppException(String message, String resourceName, String action, String errorCode) {
        super(message);
        this.resourceName = resourceName;
        this.action = action;
        this.errorCode = errorCode;
    }

    // Static factory methods for common scenarios
    public static AppException notFound(String resourceName) {
        return new AppException(resourceName + " not found", resourceName, "READ", "NOT_FOUND");
    }

    public static AppException creationFailed(String resourceName) {
        return new AppException(resourceName + " creation failed", resourceName, "CREATE", "CREATION_FAILED");
    }

    public static AppException updateFailed(String resourceName) {
        return new AppException(resourceName + " update failed", resourceName, "UPDATE", "UPDATE_FAILED");
    }

    public static AppException deletionFailed(String resourceName) {
        return new AppException(resourceName + " deletion failed", resourceName, "DELETE", "DELETION_FAILED");
    }

    public static AppException authenticationFailed(String message) {
        return new AppException(message, "Authentication", "LOGIN", "AUTH_FAILED");
    }

    // New static method for "Already Exists" exception
    public static AppException alreadyExists(String resourceName) {
        return new AppException(resourceName + " already exists", resourceName, "CREATE", "ALREADY_EXISTS");
    }

    public static AppException doNotHaveAccess(String resourceName) {
        return new AppException("You do not have access to " + resourceName, resourceName, "ACCESS", "DENIED");
    }


    // Getters for resource name, action, and error code
    public String getResourceName() {
        return resourceName;
    }

    public String getAction() {
        return action;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return String.format("Error [%s]: %s action on %s [Code: %s]", errorCode, action, resourceName, getMessage());
    }
}