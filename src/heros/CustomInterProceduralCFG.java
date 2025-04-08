package heros;

public interface CustomInterProceduralCFG<N,M> extends InterproceduralCFG<N,M> {
    MethodRepresentation getMethodRepresentation(M method);
}
