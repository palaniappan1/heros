package heros.utilities;

import heros.CustomInterProceduralCFG;
import heros.MethodRepresentation;
import heros.SynchronizedBy;
import heros.solver.PathEdge;

public class HerosProfiler<N,D,M,V,I extends CustomInterProceduralCFG<N, M>> {

    private final I interProceduralCFG;

    @SynchronizedBy
    private final MethodTracker methodTracker = MethodTracker.getInstance();

    public enum PROFILING_INFO {
        FACTS_GEN {
            @Override
            void update(MethodStats stats, long number){
                stats.incrementFactsGen((int) number);
            }
        }, NO_CALL_EDGES {
            @Override
            void update(MethodStats stats, long number) {
                stats.incrementNumberOfCallEdgesInTheMethod();
            }
        }, NO_JUMP_FNS {
            @Override
            void update(MethodStats stats, long number) {
                stats.incrementNumberOfJumpFunctions();
            }
        }, NO_INTER_EDGES {
            @Override
            void update(MethodStats stats, long number) {
                stats.incrementNumberOfInterEdges((int)number);
            }
        }, NO_INTRA_EDGES {
            @Override
            void update(MethodStats stats, long number) {
                stats.incrementNumberOfIntraEdges((int) number);
            }
        }, NO_FF_QUERIES {
            @Override
            void update(MethodStats stats, long number) {
                stats.incrementNumberOfFFQueries();
            }
        }, NO_EF_QUERIES {
            @Override
            void update(MethodStats stats, long number) {
                stats.incrementNumberOfEFQueries((int)number);
            }
        }, NO_THIS_METHOD_CALLED {
            @Override
            void update(MethodStats stats, long number) {
                stats.setNumberOfTimeThisMethodCalled((int)number);
            }
        }, CPU_TIME {
            @Override
            void update(MethodStats stats, long number) {
                stats.addCpuTime(number);
            }
        };


        abstract void update(MethodStats stats, long number);
    }

    public HerosProfiler(I interproceduralCFG){
        this.interProceduralCFG = interproceduralCFG;
    }

    public void updateProfilingInfo(PROFILING_INFO profilingInfo, MethodStats methodRegistered, long number){
       profilingInfo.update(methodRegistered, number);
    }


    private void registerAndUpdate(M method, PROFILING_INFO profilingInfo, long number) {
        int callersCount = interProceduralCFG.getCallersOf(method).size();

        if(profilingInfo == PROFILING_INFO.NO_THIS_METHOD_CALLED){
            number = callersCount;
        }

        MethodStats methodStats = getOrRegisterMethodStats(method);
        updateProfilingInfo(profilingInfo, methodStats, number);
    }

    private MethodStats getOrRegisterMethodStats(M method) {
        MethodRepresentation methodRepresentation = interProceduralCFG.getMethodRepresentation(method);
        return methodTracker.getOrregisterMethod(methodRepresentation);
    }


    public void registerMethodAndUpdateInfo(PathEdge<N,D> edge, PROFILING_INFO profilingInfo, long number){
        M method = interProceduralCFG.getMethodOf(edge.getTarget());
        registerAndUpdate(method, profilingInfo, number);
    }

    public void registerMethodAndUpdateInfo(N edge, PROFILING_INFO profilingInfo, long number){
        M method = interProceduralCFG.getMethodOf(edge);
        registerAndUpdate(method, profilingInfo, number);
    }

}
