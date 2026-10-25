package androidx.constraintlayout.core;

/* loaded from: classes3.dex */
public class Cache {
    Pools$Pool<ArrayRow> mArrayRowPool;
    SolverVariable[] mIndexedVariables;
    Pools$Pool<ArrayRow> mOptimizedArrayRowPool;
    Pools$Pool<SolverVariable> mSolverVariablePool;

    public Cache() {
        this.mOptimizedArrayRowPool = new Pools$SimplePool(256);
        this.mArrayRowPool = new Pools$SimplePool(256);
        this.mSolverVariablePool = new Pools$SimplePool(256);
        this.mIndexedVariables = new SolverVariable[32];
    }
}
