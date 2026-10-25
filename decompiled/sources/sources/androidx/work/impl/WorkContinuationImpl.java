package androidx.work.impl;

import android.text.TextUtils;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Logger;
import androidx.work.Operation;
import androidx.work.OperationKt;
import androidx.work.WorkContinuation;
import androidx.work.WorkRequest;
import androidx.work.impl.utils.EnqueueRunnable;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes4.dex */
public class WorkContinuationImpl extends WorkContinuation {
    private static final String TAG = null;
    private final List<String> mAllIds;
    private boolean mEnqueued;
    private final ExistingWorkPolicy mExistingWorkPolicy;
    private final List<String> mIds;
    private final String mName;
    private Operation mOperation;
    private final List<WorkContinuationImpl> mParents;
    private final List<? extends WorkRequest> mWork;
    private final WorkManagerImpl mWorkManagerImpl;

    /* renamed from: $r8$lambda$DjqAsqOCg9-k11Nb5y7XPUJB0yc, reason: not valid java name */
    public static /* synthetic */ Unit m974$r8$lambda$DjqAsqOCg9k11Nb5y7XPUJB0yc(WorkContinuationImpl r0) {
        r0.getClass();
        EnqueueRunnable.enqueue(r0);
        return Unit.INSTANCE;
    }

    static {
        TAG = Logger.tagWithPrefix("WorkContinuationImpl");
    }

    public WorkContinuationImpl(WorkManagerImpl r7, String r8, ExistingWorkPolicy r9, List<? extends WorkRequest> r10) {
        this(r7, r8, r9, r10, null);
    }

    public WorkContinuationImpl(WorkManagerImpl r5, String r6, ExistingWorkPolicy r7, List<? extends WorkRequest> r8, List<WorkContinuationImpl> r9) {
        this.mWorkManagerImpl = r5;
        this.mName = r6;
        this.mExistingWorkPolicy = r7;
        this.mWork = r8;
        this.mParents = r9;
        this.mIds = new ArrayList(r8.size());
        this.mAllIds = new ArrayList();
        if (r9 == null) goto L8;
        Iterator<WorkContinuationImpl> r52 = r9.iterator();
    L6:
        if (r52.hasNext() == false) goto L8;
        WorkContinuationImpl r62 = r52.next();
        this.mAllIds.addAll(r62.mAllIds);
    L8:
        int r53 = 0;
    L10:
        if (r53 >= r8.size()) goto L19;
        if (r7 != ExistingWorkPolicy.REPLACE) goto L18;
        if (r8.get(r53).getWorkSpec().getNextScheduleTimeOverride() == LongCompanionObject.MAX_VALUE) goto L18;
        f$$ExternalSyntheticBUOutline1.m("Next Schedule Time Override must be used with ExistingPeriodicWorkPolicyUPDATE (preferably) or KEEP");
        throw null;
    L18:
        String r63 = r8.get(r53).getStringId();
        this.mIds.add(r63);
        this.mAllIds.add(r63);
        r53 = r53 + 1;
        goto L10
    }

    public WorkContinuationImpl(WorkManagerImpl r7, List<? extends WorkRequest> r8) {
        this(r7, null, ExistingWorkPolicy.KEEP, r8, null);
    }

    private static boolean hasCycles(WorkContinuationImpl r4, Set<String> r5) {
        r5.addAll(r4.getIds());
        Set<String> r0 = prerequisitesFor(r4);
        Iterator<String> r1 = r5.iterator();
    L4:
        if (r1.hasNext() == false) goto L8;
        if (r0.contains(r1.next()) == false) goto L4;
        return true;
    L8:
        List<WorkContinuationImpl> r02 = r4.getParents();
        if (r02 != null) goto L11;
    L18:
        r5.removeAll(r4.getIds());
        return false;
    L11:
        if (r02.isEmpty() == true) goto L18;
        Iterator<WorkContinuationImpl> r03 = r02.iterator();
    L14:
        if (r03.hasNext() == false) goto L18;
        if (hasCycles(r03.next(), r5) == false) goto L14;
        return true;
    }

    public static Set<String> prerequisitesFor(WorkContinuationImpl r2) {
        HashSet r0 = new HashSet();
        List<WorkContinuationImpl> r22 = r2.getParents();
        if (r22 != null) goto L5;
    L10:
        return r0;
    L5:
        if (r22.isEmpty() == true) goto L10;
        Iterator<WorkContinuationImpl> r23 = r22.iterator();
    L8:
        if (r23.hasNext() == false) goto L10;
        r0.addAll(r23.next().getIds());
        goto L8
    }

    public Operation enqueue() {
        if (this.mEnqueued == true) goto L5;
        this.mOperation = OperationKt.launchOperation(this.mWorkManagerImpl.getConfiguration().getTracer(), "EnqueueRunnable_" + getExistingWorkPolicy().name(), this.mWorkManagerImpl.getWorkTaskExecutor().getSerialTaskExecutor(), new WorkContinuationImpl$$ExternalSyntheticLambda0(this));
    L7:
        return this.mOperation;
    L5:
        Logger.get().warning(TAG, "Already enqueued work ids (" + TextUtils.join(", ", this.mIds) + ")");
        goto L7
    }

    public ExistingWorkPolicy getExistingWorkPolicy() {
        return this.mExistingWorkPolicy;
    }

    public List<String> getIds() {
        return this.mIds;
    }

    public String getName() {
        return this.mName;
    }

    public List<WorkContinuationImpl> getParents() {
        return this.mParents;
    }

    public List<? extends WorkRequest> getWork() {
        return this.mWork;
    }

    public WorkManagerImpl getWorkManagerImpl() {
        return this.mWorkManagerImpl;
    }

    public boolean hasCycles() {
        return hasCycles(this, new HashSet());
    }

    public boolean isEnqueued() {
        return this.mEnqueued;
    }

    public void markEnqueued() {
        this.mEnqueued = true;
    }
}
