package heros.utilities;

import java.util.concurrent.atomic.AtomicInteger;

// For every method, some basic profiling information
public class MethodStats {
    private final AtomicInteger factsGen = new AtomicInteger(0);
    private final AtomicInteger factsKilled = new AtomicInteger(0);
    private int numberOfStatements = 0;
    private final AtomicInteger numberOfPropagations = new AtomicInteger(0);
    private final AtomicInteger numberOfCallEdges = new AtomicInteger(0);
    private boolean isMethodExited = false;
    private String methodName;

    public MethodStats(String methodName, int numberOfStatements) {
        this.methodName = methodName;
        this.numberOfStatements = numberOfStatements;
    }

    // Increment methods
    public void incrementFactsGen() {
        factsGen.incrementAndGet();
    }

    public void incrementNumberOfCallEdgesInTheMethod(){
        numberOfCallEdges.incrementAndGet();
    }

    public void incrementFactsKilled() {
        factsKilled.incrementAndGet();
    }

    public void incrementNumberOfPropagations() {
        numberOfPropagations.incrementAndGet();
    }

    public void setMethodExited(){
        isMethodExited = true;
    }

    // Getters
    public int getFactsGen() {
        return factsGen.get();
    }

    public int getFactsKilled() {
        return factsKilled.get();
    }

    public int getNumberOfPropagations() {
        return numberOfPropagations.get();
    }

    public boolean isMethodExited() {
        return isMethodExited;
    }

    public int getNumberOfCallEdges() {
        return numberOfCallEdges.get();
    }

    @Override
    public String toString() {
        return "MethodStats{" +
                "methodName='" + methodName + '\'' +
                "factsGen=" + factsGen +
                ", factsKilled=" + factsKilled +
                ", numberOfStatements=" + numberOfStatements +
                ", numberOfPropagations=" + numberOfPropagations +
                '}';
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
