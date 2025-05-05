package heros;

import java.util.Objects;

public class MethodRepresentation {
    private final String methodName;
    private final String className;
    private final String fullyQualifiedName;
    private final String methodSignature;
    private final int numberOfStatements;

    public MethodRepresentation(String methodName, String className, int numberOfStatements, String methodSignature) {
        this.methodName = methodName;
        this.className = className;
        this.fullyQualifiedName = className + "." + methodName;
        this.numberOfStatements = numberOfStatements;
        this.methodSignature = methodSignature;
    }

    public static MethodRepresentation from(String methodName, String className, int numberOfStatements) {
        return new MethodRepresentation(methodName, className, numberOfStatements, methodName + "signature");
    }


    public String getMethodName() {
        return methodName;
    }

    public String getClassName() {
        return className;
    }

    public String getFullyQualifiedName() {
        return fullyQualifiedName;
    }

    public String getMethodSignature() {
        return methodSignature;
    }

    public int getNumberOfStatements() {
        return numberOfStatements;
    }

    @Override
    public String toString() {
        return fullyQualifiedName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Same reference check
        if (obj == null || getClass() != obj.getClass()) return false; // Null & Class check
        MethodRepresentation that = (MethodRepresentation) obj;
        return Objects.equals(fullyQualifiedName, that.fullyQualifiedName); // Key-based equality
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullyQualifiedName);
    }
}
