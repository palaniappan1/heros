package heros.utilities;

import lombok.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

// For every method, some basic profiling information
@Getter
@Setter
@AllArgsConstructor
@Data
public class MethodStats {
    private final AtomicInteger factsGen = new AtomicInteger(0);
    @Getter
    private final String methodSignature;
    @Getter
    private int numberOfStatements = 0;
    private final AtomicInteger numberOfCallEdges = new AtomicInteger(0);
    private final AtomicInteger numberOfJumpFunctions = new AtomicInteger(0);
    private final AtomicInteger numberOfInterEdges = new AtomicInteger(0); // number of inter procedural edges
    @Getter
    private final AtomicInteger FFQueries = new AtomicInteger(0);
    private final AtomicInteger EFQueries = new AtomicInteger(0);
    private final LongAdder cpuTimeNanos = new LongAdder();
    @Getter
    @Setter
    private String methodName;
    private final AtomicInteger numberOfIntraEdges = new AtomicInteger(0);
    private boolean callGraphValueSet = false;
    @Getter
    private int numberOfTimeThisMethodCalled = 0;
    private final AtomicInteger numberOfTimesCPUTimeAdded = new AtomicInteger(0);

    public MethodStats(String methodName, int numberOfStatements, String methodSignature) {
        this.methodName = methodName;
        this.numberOfStatements = numberOfStatements;
        this.methodSignature = methodSignature;
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

    @Override
    public String toString() {
        return "MethodStats{" +
                "methodName='" + methodName + '\'' +
                ", methodSignature='" + methodSignature + '\'' +
                ", factsGen=" + factsGen +
                ", numberOfStatements=" + numberOfStatements +
                ", numberOfJumpFns=" + numberOfJumpFunctions +
                ", numberOfInterEdges=" + numberOfInterEdges +
                ", numberOfIntraEdges=" + numberOfIntraEdges +
                ", numberOfCallEdges=" + numberOfCallEdges +
                ", FFQueries=" + FFQueries +
                ", EFQueries=" + EFQueries +
                ", numberOfTimeThisMethodCalled=" + numberOfTimeThisMethodCalled +
                ", CPUTime=" + TimeUnit.MICROSECONDS.convert(getCpuTimeNanos(), TimeUnit.NANOSECONDS) +
                ", numberOfTimesCPUTimeAdded=" + numberOfTimesCPUTimeAdded +
                '}';
    }

    public long getCpuTimeNanos() {
        return cpuTimeNanos.sum();
    }

    public void addCpuTime(long nanos){
        cpuTimeNanos.add(nanos);
        numberOfTimesCPUTimeAdded.incrementAndGet();
    }

    public void setNumberOfTimeThisMethodCalled(int numberOfTimeThisMethodCalled){
        if(!this.callGraphValueSet) {
            this.numberOfTimeThisMethodCalled = numberOfTimeThisMethodCalled;
            this.callGraphValueSet = true;
        }
    }
}
