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
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MethodRepresentation that = (MethodRepresentation) obj;
        return numberOfStatements == that.numberOfStatements &&
                Objects.equals(methodName, that.methodName) &&
                Objects.equals(className, that.className) &&
                Objects.equals(fullyQualifiedName, that.fullyQualifiedName) &&
                Objects.equals(methodSignature, that.methodSignature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodName, className, fullyQualifiedName, numberOfStatements, methodSignature);
    }
}
