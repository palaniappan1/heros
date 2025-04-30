package heros.utilities;

import java.util.concurrent.atomic.AtomicInteger;

// For every method, some basic profiling information
public class MethodStats {
    private final AtomicInteger factsGen = new AtomicInteger(0);
    private int numberOfStatements = 0;
    private final AtomicInteger numberOfCallEdges = new AtomicInteger(0);
    private final AtomicInteger numberOfJumpFunctions = new AtomicInteger(0);
    private final AtomicInteger numberOfInterEdges = new AtomicInteger(0); // number of inter procedural edges
    private final AtomicInteger FFQueries = new AtomicInteger(0);
    private final AtomicInteger EFQueries = new AtomicInteger(0);
    private String methodName;
    private final AtomicInteger numberOfIntraEdges = new AtomicInteger(0);

    public MethodStats(String methodName, int numberOfStatements) {
        this.methodName = methodName;
        this.numberOfStatements = numberOfStatements;
    }

    // Increment methods
    public void incrementFactsGen(int numberOfFacts) {
        factsGen.addAndGet(numberOfFacts);
    }

    public void incrementNumberOfCallEdgesInTheMethod(){
        numberOfCallEdges.incrementAndGet();
    }


    public void incrementNumberOfIntraEdges(int intraEdges) {
        numberOfIntraEdges.getAndAdd(intraEdges);
    }


    public void incrementNumberOfJumpFunctions(){
        numberOfJumpFunctions.incrementAndGet();
    }

    public void incrementNumberOfInterEdges(int number) {
        numberOfInterEdges.addAndGet(number);
    }

    public void incrementNumberOfFFQueries(){
        FFQueries.incrementAndGet();
    }

    public void incrementNumberOfEFQueries(int number){
        EFQueries.addAndGet(number);
    }

    // Getters
    public int getFactsGen() {
        return factsGen.get();
    }

    public int getNumberOfIntraEdges() {
        return numberOfIntraEdges.get();
    }


    public int getNumberOfCallEdges() {
        return numberOfCallEdges.get();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getNumberOfJumpFunctions() {
        return numberOfJumpFunctions.get();
    }

    public int getNumberOfInterEdges() {
        return numberOfInterEdges.get();
    }

    public int getNumberOfFFQueries() {
        return FFQueries.get();
    }

    public int getNumberOfEFQueries() {
        return EFQueries.get();
    }

    @Override
    public String toString() {
        return "MethodStats{" +
                "methodName='" + methodName + '\'' +
                ", factsGen=" + factsGen +
                ", numberOfStatements=" + numberOfStatements +
                ", numberOfJumpFns=" + numberOfJumpFunctions +
                ", numberOfInterEdges=" + numberOfInterEdges +
                ", numberOfIntraEdges=" + numberOfIntraEdges +
                ", FFQueries=" + FFQueries +
                ", EFQueries=" + EFQueries +
                '}';
    }

    public AtomicInteger getFFQueries() {
        return FFQueries;
    }
}
