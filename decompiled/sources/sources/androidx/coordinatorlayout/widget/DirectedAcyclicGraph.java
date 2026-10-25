package androidx.coordinatorlayout.widget;

import androidx.collection.SimpleArrayMap;
import androidx.core.util.Pools$Pool;
import androidx.core.util.Pools$SimplePool;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public final class DirectedAcyclicGraph<T> {
    private final SimpleArrayMap<T, ArrayList<T>> mGraph;
    private final Pools$Pool<ArrayList<T>> mListPool;
    private final ArrayList<T> mSortResult;
    private final HashSet<T> mSortTmpMarked;

    public DirectedAcyclicGraph() {
        this.mListPool = new Pools$SimplePool(10);
        this.mGraph = new SimpleArrayMap();
        this.mSortResult = new ArrayList();
        this.mSortTmpMarked = new HashSet();
    }

    private void dfs(T r5, ArrayList<T> r6, HashSet<T> r7) {
        if (r6.contains(r5) == false) goto L6;
        return;
    L6:
        if (r7.contains(r5) == true) goto L14;
        r7.add(r5);
        ArrayList<T> r0 = this.mGraph.get(r5);
        if (r0 == null) goto L12;
        int r1 = r0.size();
        int r2 = 0;
    L10:
        if (r2 >= r1) goto L12;
        dfs(r0.get(r2), r6, r7);
        r2 = r2 + 1;
    L12:
        r7.remove(r5);
        r6.add(r5);
        return;
    L14:
        GlShader$$ExternalSyntheticBUOutline1.m("This graph contains cyclic dependencies");
    }

    private ArrayList<T> getEmptyList() {
        ArrayList<T> r0 = this.mListPool.acquire();
        if (r0 == null) goto L5;
        return r0;
    L5:
        return new ArrayList();
    }

    private void poolList(ArrayList<T> r1) {
        r1.clear();
        this.mListPool.release(r1);
    }

    public void addEdge(T r2, T r3) {
        if (this.mGraph.containsKey(r2) == true) goto L5;
    L11:
        f$$ExternalSyntheticBUOutline1.m("All nodes must be present in the graph before being added as an edge");
        return;
    L5:
        if (this.mGraph.containsKey(r3) == false) goto L11;
        ArrayList<T> r0 = this.mGraph.get(r2);
        if (r0 != null) goto L9;
        r0 = getEmptyList();
        this.mGraph.put(r2, r0);
    L9:
        r0.add(r3);
    }

    public void addNode(T r2) {
        if (this.mGraph.containsKey(r2) == true) goto L6;
        this.mGraph.put(r2, null);
        return;
    }

    public void clear() {
        int r0 = this.mGraph.size();
        int r1 = 0;
    L3:
        SimpleArrayMap<T, ArrayList<T>> r2 = this.mGraph;
        if (r1 >= r0) goto L9;
        ArrayList<T> r22 = r2.valueAt(r1);
        if (r22 == null) goto L8;
        poolList(r22);
    L8:
        r1 = r1 + 1;
        goto L3
    L9:
        r2.clear();
    }

    public boolean contains(T r1) {
        return this.mGraph.containsKey(r1);
    }

    public List getIncomingEdges(T r1) {
        return this.mGraph.get(r1);
    }

    public List<T> getOutgoingEdges(T r5) {
        int r0 = this.mGraph.size();
        ArrayList r1 = null;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L12;
        ArrayList<T> r3 = this.mGraph.valueAt(r2);
        if (r3 == null) goto L11;
        if (r3.contains(r5) == false) goto L11;
        if (r1 != null) goto L10;
        r1 = new ArrayList();
    L10:
        r1.add(this.mGraph.keyAt(r2));
    L11:
        r2 = r2 + 1;
        goto L3
    L12:
        return r1;
    }

    public ArrayList<T> getSortedList() {
        this.mSortResult.clear();
        this.mSortTmpMarked.clear();
        int r0 = this.mGraph.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L6;
        dfs(this.mGraph.keyAt(r1), this.mSortResult, this.mSortTmpMarked);
        r1 = r1 + 1;
        goto L3
    L6:
        return this.mSortResult;
    }

    public boolean hasOutgoingEdges(T r5) {
        int r0 = this.mGraph.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L11;
        ArrayList<T> r3 = this.mGraph.valueAt(r2);
        if (r3 == null) goto L10;
        if (r3.contains(r5) == false) goto L10;
        return true;
    L10:
        r2 = r2 + 1;
        goto L3
    L11:
        return false;
    }
}
