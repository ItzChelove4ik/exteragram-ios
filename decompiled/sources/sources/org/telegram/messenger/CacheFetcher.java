package org.telegram.messenger;

import android.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.Utilities;

/* loaded from: classes.dex */
public abstract class CacheFetcher<Args, R> {
    private HashMap<Pair<Integer, Args>, R> cachedResults;
    private HashMap<Pair<Integer, Args>, Long> lastRequestedRemotely;
    private HashMap<Pair<Integer, Args>, ArrayList<Utilities.Callback<R>>> loadingCallbacks;
    private final long requestRemotelyTimeout;

    public static /* synthetic */ void $r8$lambda$E3ktrRt3YF_f_Y1tRdHweZKTe0g(CacheFetcher r0, Pair r1, Object r2, boolean r3) {
        r0.lambda$callCallbacks$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$IWIvOFf7gBxBooFzWi8zv8eFXuI(CacheFetcher r0, Pair r1, Object r2, int r3, Long r4, Object r5) {
        r0.lambda$fetch$1(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$MqIhig14yVI8VD7JuZf7K1fBqec(CacheFetcher r0, Pair r1, Object r2, int r3, Object r4, Boolean r5, Object r6, Long r7, Boolean r8) {
        r0.lambda$fetch$0(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$UhzYRZnjT2ty7B00NIMsApNSMBM(CacheFetcher r0, Pair r1, Utilities.Callback r2) {
        r0.lambda$saveCallback$2(r1, r2);
    }

    public CacheFetcher() {
        this(240000);
    }

    public CacheFetcher(int r3) {
        this.requestRemotelyTimeout = r3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void cacheResult(Pair<Integer, Args> r2, R r3) {
        if (useCache(r2.second) == true) goto L6;
        return;
    L6:
        if (this.cachedResults != null) goto L8;
        this.cachedResults = new HashMap();
    L8:
        this.cachedResults.put(r2, r3);
    }

    private void callCallbacks(Pair<Integer, Args> r2, R r3, boolean r4) {
        AndroidUtilities.runOnUIThread(new CacheFetcher$$ExternalSyntheticLambda0(this, r2, r3, r4));
    }

    private R getCachedResult(Pair<Integer, Args> r1) {
        HashMap<Pair<Integer, Args>, R> r0 = this.cachedResults;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.get(r1);
    }

    private boolean isLoading(Pair<Integer, Args> r1) {
        HashMap<Pair<Integer, Args>, ArrayList<Utilities.Callback<R>>> r0 = this.loadingCallbacks;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.get(r1) == null) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$callCallbacks$3(Pair r5, Object r6, boolean r7) {
        HashMap<Pair<Integer, Args>, ArrayList<Utilities.Callback<R>>> r0 = this.loadingCallbacks;
        if (r0 == null) goto L18;
        ArrayList<Utilities.Callback<R>> r02 = r0.get(r5);
        if (r02 == null) goto L19;
        int r1 = r02.size();
        int r2 = 0;
    L9:
        if (r2 >= r1) goto L11;
        Utilities.Callback<R> r3 = r02.get(r2);
        r2 = r2 + 1;
        r3.run(r6);
        goto L9
    L11:
        if (r7 == false) goto L13;
        r02.clear();
    L13:
        if (r7 == false) goto L17;
        this.loadingCallbacks.remove(r5);
        return;
    L17:
        return;
    L19:
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private /* synthetic */ void lambda$fetch$0(Pair r7, Object r8, int r9, Object r10, Boolean r11, Object r12, Long r13, Boolean r14) {
        if (r14.booleanValue() == false) goto L6;
        saveLastRequested(r7);
    L6:
        if (r11.booleanValue() == false) goto L9;
        cacheResult(r7, r8);
        callCallbacks(r7, r8, true);
        return;
    L9:
        if (r12 == 0) goto L11;
        CacheFetcher<Args, R> r0 = this;
        R r3 = r12;
        r0.setLocal(r9, r10, r3, r13.longValue());
        r0.cacheResult(r7, r3);
    L12:
        r0.callCallbacks(r7, r3, true);
        return;
    L11:
        r0 = this;
        r3 = r12;
        goto L12
    }

    /* JADX WARN: Multi-variable type inference failed */
    private /* synthetic */ void lambda$fetch$1(Pair r3, Object r4, int r5, Long r6, Object r7) {
        if (shouldRequest(r3) == false) goto L10;
        if (r7 != 0) goto L6;
    L8:
        getRemote(r5, r4, r6.longValue(), new CacheFetcher$$ExternalSyntheticLambda1(this, r3, r7, r5, r4));
        return;
    L6:
        if (emitLocal(r4) == false) goto L8;
        cacheResult(r3, r7);
        callCallbacks(r3, r7, false);
        goto L8
    L10:
        cacheResult(r3, r7);
        callCallbacks(r3, r7, true);
    }

    private /* synthetic */ void lambda$saveCallback$2(Pair r2, Utilities.Callback r3) {
        if (this.loadingCallbacks != null) goto L5;
        this.loadingCallbacks = new HashMap();
    L5:
        ArrayList<Utilities.Callback<R>> r0 = this.loadingCallbacks.get(r2);
        if (r0 != null) goto L8;
        HashMap<Pair<Integer, Args>, ArrayList<Utilities.Callback<R>>> r1 = this.loadingCallbacks;
        r0 = new ArrayList();
        r1.put(r2, r0);
    L8:
        r0.add(r3);
    }

    private void saveCallback(Pair<Integer, Args> r2, Utilities.Callback<R> r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        AndroidUtilities.runOnUIThread(new CacheFetcher$$ExternalSyntheticLambda2(this, r2, r3));
    }

    private void saveLastRequested(Pair<Integer, Args> r5) {
        if (this.lastRequestedRemotely != null) goto L5;
        this.lastRequestedRemotely = new HashMap();
    L5:
        long r0 = System.currentTimeMillis();
        this.lastRequestedRemotely.put(r5, Long.valueOf(r0));
        if (saveLastTimeRequested() == false) goto L9;
        setSavedLastTimeRequested(r5.hashCode(), r0);
        return;
    }

    private boolean shouldRequest(Pair<Integer, Args> r6) {
        HashMap<Pair<Integer, Args>, Long> r0 = this.lastRequestedRemotely;
        if (r0 == null) goto L5;
        Long r02 = r0.get(r6);
    L7:
        if (saveLastTimeRequested() == false) goto L10;
        if (r02 != null) goto L10;
        r02 = Long.valueOf(getSavedLastTimeRequested(r6.hashCode()));
    L10:
        if (r02 != null) goto L12;
        return true;
    L12:
        if ((System.currentTimeMillis() - r02.longValue()) >= this.requestRemotelyTimeout) goto L18;
        return false;
    L18:
        return true;
    L5:
        r02 = null;
        goto L7
    }

    public boolean emitLocal(Args r1) {
        return false;
    }

    public void fetch(int r4, Args r5, Utilities.Callback<R> r6) {
        Pair<Integer, Args> r0 = new Pair(Integer.valueOf(r4), r5);
        if (isLoading(r0) == false) goto L6;
        saveCallback(r0, r6);
        return;
    L6:
        R r1 = getCachedResult(r0);
        if (r1 != null) goto L9;
    L13:
        saveCallback(r0, r6);
        getLocal(r4, r5, new CacheFetcher$$ExternalSyntheticLambda3(this, r0, r5, r4));
        return;
    L9:
        if (shouldRequest(r0) == true) goto L13;
        if (r6 == null) goto L15;
        r6.run(r1);
        return;
    }

    public void forceRequest(int r3, Args r4) {
        if (this.lastRequestedRemotely == null) goto L10;
        Pair r0 = new Pair(Integer.valueOf(r3), r4);
        this.lastRequestedRemotely.remove(r0);
        if (saveLastTimeRequested() == false) goto L9;
        setSavedLastTimeRequested(r0.hashCode(), 0);
        return;
    L9:
        return;
    }

    public void getLocal(int r1, Args r2, Utilities.Callback2<Long, R> r3) {
        r3.run(0L, null);
    }

    public void getRemote(int r1, Args r2, long r3, Utilities.Callback4<Boolean, R, Long, Boolean> r5) {
    }

    public long getSavedLastTimeRequested(int r1) {
        return 0;
    }

    public boolean saveLastTimeRequested() {
        return false;
    }

    public void setLocal(int r1, Args r2, R r3, long r4) {
    }

    public void setSavedLastTimeRequested(int r1, long r2) {
    }

    public boolean useCache(Args r1) {
        return true;
    }
}
