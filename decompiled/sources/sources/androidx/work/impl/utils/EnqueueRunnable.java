package androidx.work.impl.utils;

import android.text.TextUtils;
import androidx.lifecycle.LiveData$$ExternalSyntheticBUOutline0;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Logger;
import androidx.work.WorkInfo$State;
import androidx.work.WorkRequest;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkContinuationImpl;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.Dependency;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkName;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes4.dex */
public abstract class EnqueueRunnable {
    private static final String TAG = null;

    static {
        TAG = Logger.tagWithPrefix("EnqueueRunnable");
    }

    public static boolean addToDatabase(WorkContinuationImpl r2) {
        WorkManagerImpl r0 = r2.getWorkManagerImpl();
        WorkDatabase r1 = r0.getWorkDatabase();
        r1.beginTransaction();
        EnqueueUtilsKt.checkContentUriTriggerWorkerLimits(r1, r0.getConfiguration(), r2);     // Catch: Throwable -> L6
        boolean r22 = processContinuation(r2);     // Catch: Throwable -> L6
        r1.setTransactionSuccessful();     // Catch: Throwable -> L6
        r1.endTransaction();
        return r22;
    L6:
        th = move-exception;
        r1.endTransaction();
        throw th;
    }

    public static void enqueue(WorkContinuationImpl r2) {
        if (r2.hasCycles() == false) goto L5;
        LiveData$$ExternalSyntheticBUOutline0.m("WorkContinuation has cycles (", r2, ")");
        return;
    L5:
        if (addToDatabase(r2) == false) goto L10;
        scheduleWorkInBackground(r2);
        return;
    }

    private static boolean enqueueContinuation(WorkContinuationImpl r5) {
        Set<String> r0 = WorkContinuationImpl.prerequisitesFor(r5);
        boolean r02 = enqueueWorkWithPrerequisites(r5.getWorkManagerImpl(), r5.getWork(), (String[]) r0.toArray(new String[0]), r5.getName(), r5.getExistingWorkPolicy());
        r5.markEnqueued();
        return r02;
    }

    private static boolean enqueueWorkWithPrerequisites(WorkManagerImpl r18, List<? extends WorkRequest> r19, String[] r20, String r21, ExistingWorkPolicy r22) {
        String[] r0 = r20;
        long r3 = r18.getConfiguration().getClock().currentTimeMillis();
        WorkDatabase r5 = r18.getWorkDatabase();
        if (r0 != null) goto L5;
    L7:
        boolean r8 = false;
    L8:
        if (r8 == false) goto L26;
        int r9 = r0.length;
        int r10 = 0;
        boolean r12 = false;
        boolean r13 = false;
        boolean r11 = true;
    L10:
        if (r10 >= r9) goto L27;
        String r14 = r0[r10];
        WorkSpec r15 = r5.workSpecDao().getWorkSpec(r14);
        if (r15 == null) goto L13;
        WorkInfo$State r142 = r15.state;
        if (r142 != WorkInfo$State.SUCCEEDED) goto L18;
        boolean r152 = true;
    L19:
        r11 = r11 & r152;
        if (r142 != WorkInfo$State.FAILED) goto L23;
        r13 = true;
    L25:
        r10 = r10 + 1;
        goto L10
    L23:
        if (r142 != WorkInfo$State.CANCELLED) goto L25;
        r12 = true;
        goto L25
    L18:
        r152 = false;
        goto L19
    L13:
        Logger.get().error(TAG, "Prerequisite " + r14 + " doesn't exist; not enqueuing");
        return false;
    L27:
        boolean r92 = TextUtils.isEmpty(r21);
        if (r92 == true) goto L84;
        if (r8 == true) goto L84;
        List<WorkSpec.IdAndState> r102 = r5.workSpecDao().getWorkSpecIdAndStatesForName(r21);
        if (r102.isEmpty() == true) goto L84;
        if (r22 != ExistingWorkPolicy.APPEND) goto L35;
    L36:
        WorkManagerImpl r143 = r18;
        DependencyDao r82 = r5.dependencyDao();
        List r153 = new ArrayList();
        Iterator<WorkSpec.IdAndState> r103 = r102.iterator();
    L54:
        if (r103.hasNext() == false) goto L70;
        WorkSpec.IdAndState r6 = r103.next();
        if (r82.hasDependents(r6.id) == true) goto L68;
        WorkInfo$State r7 = r6.state;
        WorkDatabase r17 = r5;
        if (r7 != WorkInfo$State.SUCCEEDED) goto L60;
        boolean r52 = true;
    L61:
        boolean r53 = r52 & r11;
        if (r7 != WorkInfo$State.FAILED) goto L65;
        r13 = true;
    L67:
        r153.add(r6.id);
        r11 = r53;
    L69:
        r5 = r17;
        goto L54
    L65:
        if (r7 != WorkInfo$State.CANCELLED) goto L67;
        r12 = true;
        goto L67
    L60:
        r52 = false;
        goto L61
    L68:
        r17 = r5;
        goto L69
    L70:
        WorkDatabase r172 = r5;
        if (r22 != ExistingWorkPolicy.APPEND_OR_REPLACE) goto L79;
        if (r12 == true) goto L74;
        if (r13 == false) goto L79;
    L74:
        WorkSpecDao r2 = r172.workSpecDao();
        Iterator<WorkSpec.IdAndState> r54 = r2.getWorkSpecIdAndStatesForName(r21).iterator();
    L76:
        if (r54.hasNext() == false) goto L78;
        r2.delete(r54.next().id);
        goto L76
    L78:
        r153 = Collections.EMPTY_LIST;
        r12 = false;
        r13 = false;
    L79:
        r0 = (String[]) r153.toArray(r0);
        if (r0.length <= 0) goto L82;
        r8 = true;
    L83:
        boolean r62 = false;
    L85:
        Iterator<? extends WorkRequest> r23 = r19.iterator();
    L87:
        if (r23.hasNext() == false) goto L109;
        WorkRequest r55 = r23.next();
        WorkSpec r72 = r55.getWorkSpec();
        if (r8 == false) goto L96;
        if (r11 == true) goto L96;
        if (r13 == false) goto L93;
        r72.state = WorkInfo$State.FAILED;
    L98:
        if (r72.state != WorkInfo$State.ENQUEUED) goto L100;
        r62 = true;
    L100:
        r172.workSpecDao().insertWorkSpec(EnqueueUtilsKt.wrapWorkSpecIfNeeded(r143.getSchedulers(), r72));
        if (r8 == false) goto L105;
        int r73 = r0.length;
        int r104 = 0;
    L103:
        if (r104 >= r73) goto L105;
        String[] r202 = r0;
        r172.dependencyDao().insertDependency(new Dependency(r55.getStringId(), r0[r104]));
        r104 = r104 + 1;
        r23 = r23;
        r0 = r202;
    L105:
        String[] r203 = r0;
        Iterator<? extends WorkRequest> r192 = r23;
        r172.workTagDao().insertTags(r55.getStringId(), r55.getTags());
        if (r92 == true) goto L108;
        r172.workNameDao().insert(new WorkName(r21, r55.getStringId()));
    L108:
        r23 = r192;
        r0 = r203;
        goto L87
    L93:
        if (r12 == false) goto L95;
        r72.state = WorkInfo$State.CANCELLED;
        goto L98
    L95:
        r72.state = WorkInfo$State.BLOCKED;
    L96:
        r72.lastEnqueueTime = r3;
        goto L98
    L109:
        return r62;
    L82:
        r8 = false;
        goto L83
    L35:
        if (r22 == ExistingWorkPolicy.APPEND_OR_REPLACE) goto L36;
        if (r22 != ExistingWorkPolicy.KEEP) goto L47;
        Iterator<WorkSpec.IdAndState> r24 = r102.iterator();
    L41:
        if (r24.hasNext() == false) goto L47;
        WorkInfo$State r144 = r24.next().state;
        if (r144 == WorkInfo$State.ENQUEUED) goto L46;
        if (r144 != WorkInfo$State.RUNNING) goto L41;
    L46:
        return false;
    L47:
        r143 = r18;
        CancelWorkRunnable.forNameInline(r21, r143);
        WorkSpecDao r25 = r5.workSpecDao();
        Iterator<WorkSpec.IdAndState> r105 = r102.iterator();
    L49:
        if (r105.hasNext() == false) goto L51;
        r25.delete(r105.next().id);
        goto L49
    L51:
        r172 = r5;
        r62 = true;
    L84:
        r143 = r18;
        r172 = r5;
        goto L83
    L26:
        r12 = false;
        r13 = false;
        r11 = true;
        goto L27
    L5:
        if (r0.length <= 0) goto L7;
        r8 = true;
        goto L8
    }

    private static boolean processContinuation(WorkContinuationImpl r7) {
        List<WorkContinuationImpl> r0 = r7.getParents();
        boolean r1 = false;
        if (r0 == null) goto L12;
        Iterator<WorkContinuationImpl> r02 = r0.iterator();
    L6:
        if (r02.hasNext() == false) goto L12;
        WorkContinuationImpl r2 = r02.next();
        if (r2.isEnqueued() == false) goto L9;
        Logger.get().warning(TAG, "Already enqueued work ids (" + TextUtils.join(", ", r2.getIds()) + ")");
        goto L6
    L9:
        r1 = r1 | processContinuation(r2);
    L12:
        return enqueueContinuation(r7) | r1;
    }

    public static void scheduleWorkInBackground(WorkContinuationImpl r2) {
        WorkManagerImpl r22 = r2.getWorkManagerImpl();
        Schedulers.schedule(r22.getConfiguration(), r22.getWorkDatabase(), r22.getSchedulers());
    }
}
