package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.work.Logger;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\nJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Landroidx/work/impl/constraints/trackers/StorageNotLowTracker;", "Landroidx/work/impl/constraints/trackers/BroadcastReceiverConstraintTracker;", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Landroid/content/Context;", "taskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "<init>", "(Landroid/content/Context;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;)V", "readSystemState", "()Ljava/lang/Boolean;", "intentFilter", "Landroid/content/IntentFilter;", "getIntentFilter", "()Landroid/content/IntentFilter;", "onBroadcastReceive", _UrlKt.FRAGMENT_ENCODE_SET, "intent", "Landroid/content/Intent;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class StorageNotLowTracker extends BroadcastReceiverConstraintTracker<Boolean> {
    public StorageNotLowTracker(Context r1, TaskExecutor r2) {
        super(r1, r2);
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public IntentFilter getIntentFilter() {
        IntentFilter r1 = new IntentFilter();
        r1.addAction("android.intent.action.DEVICE_STORAGE_OK");
        r1.addAction("android.intent.action.DEVICE_STORAGE_LOW");
        return r1;
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public void onBroadcastReceive(Intent r5) {
        if (r5.getAction() == null) goto L23;
        Logger.get().debug(StorageNotLowTrackerKt.access$getTAG$p(), "Received " + r5.getAction());
        String r52 = r5.getAction();
        if (r52 == null) goto L22;
        int r0 = r52.hashCode();
        if (r0 == (-1181163412)) goto L18;
        if (r0 == (-730838620)) goto L13;
        return;
    L13:
        if (r52.equals("android.intent.action.DEVICE_STORAGE_OK") == false) goto L25;
        setState(Boolean.TRUE);
        return;
    L25:
        return;
    L18:
        if (r52.equals("android.intent.action.DEVICE_STORAGE_LOW") == false) goto L26;
        setState(Boolean.FALSE);
        return;
    L26:
        return;
    L22:
        return;
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public Boolean readSystemState() {
        Intent r4 = getAppContext().registerReceiver(null, getIntentFilter());
        boolean r0 = true;
        if (r4 == null) goto L20;
        if (r4.getAction() == null) goto L20;
        String r42 = r4.getAction();
        if (r42 == null) goto L18;
        int r2 = r42.hashCode();
        if (r2 != (-1181163412)) goto L12;
        r42.equals("android.intent.action.DEVICE_STORAGE_LOW");
        goto L18
    L12:
        if (r2 != (-730838620)) goto L18;
        if (r42.equals("android.intent.action.DEVICE_STORAGE_OK") == true) goto L20;
    L18:
        r0 = false;
    L20:
        return Boolean.valueOf(r0);
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public /* bridge */ /* synthetic */ Object readSystemState() {
        return readSystemState();
    }
}
