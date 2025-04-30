package heros.utilities;

import heros.MethodRepresentation;

import java.util.concurrent.ConcurrentHashMap;

// Api's to add/register the given method, and do some basic profiling arithmetic operations
public class MethodTracker {
    public final ConcurrentHashMap<MethodRepresentation, MethodStats> methodStatsMap = new ConcurrentHashMap<>();


    private MethodTracker() {
        // private constructor to prevent instantiation
    }

    // === Singleton Holder ===
    private static class Holder {
        private static final MethodTracker INSTANCE = new MethodTracker();
    }

    // === Public Accessor ===
    @SuppressWarnings("unchecked")
    public static <T extends MethodRepresentation> MethodTracker getInstance() {
        return Holder.INSTANCE;
    }

    public MethodStats getOrregisterMethod(MethodRepresentation methodInstance) {
        return methodStatsMap.computeIfAbsent(
                methodInstance, m -> new MethodStats(m.getMethodName(), methodInstance.getNumberOfStatements())
        );

    }

    public MethodStats getStats(MethodRepresentation methodInstance) {
        return methodStatsMap.get(methodInstance);
    }
}

