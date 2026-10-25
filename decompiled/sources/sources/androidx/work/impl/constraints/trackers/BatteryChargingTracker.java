package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.work.Logger;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\nJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0014"}, d2 = {"Landroidx/work/impl/constraints/trackers/BatteryChargingTracker;", "Landroidx/work/impl/constraints/trackers/BroadcastReceiverConstraintTracker;", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Landroid/content/Context;", "taskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "<init>", "(Landroid/content/Context;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;)V", "readSystemState", "()Ljava/lang/Boolean;", "intentFilter", "Landroid/content/IntentFilter;", "getIntentFilter", "()Landroid/content/IntentFilter;", "onBroadcastReceive", _UrlKt.FRAGMENT_ENCODE_SET, "intent", "Landroid/content/Intent;", "isBatteryChangedIntentCharging", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BatteryChargingTracker extends BroadcastReceiverConstraintTracker<Boolean> {
    public BatteryChargingTracker(Context r1, TaskExecutor r2) {
        super(r1, r2);
    }

    private final boolean isBatteryChangedIntentCharging(Intent r2) {
        int r1 = r2.getIntExtra("status", -1);
        if (r1 != 2) goto L5;
        return true;
    L5:
        if (r1 == 5) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public IntentFilter getIntentFilter() {
        IntentFilter r1 = new IntentFilter();
        r1.addAction("android.os.action.CHARGING");
        r1.addAction("android.os.action.DISCHARGING");
        return r1;
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public void onBroadcastReceive(Intent r4) {
        String r42 = r4.getAction();
        if (r42 == null) goto L28;
        Logger.get().debug(BatteryChargingTrackerKt.access$getTAG$p(), "Received ".concat(r42));
        switch(r42.hashCode()) {
            case -1886648615: goto L24;
            case -54942926: goto L19;
            case 948344062: goto L14;
            case 1019184907: goto L9;
            default: goto L29;
        };
    L29:
        return;
    L9:
        if (r42.equals("android.intent.action.ACTION_POWER_CONNECTED") == false) goto L30;
        setState(Boolean.TRUE);
        return;
    L30:
        return;
    L14:
        if (r42.equals("android.os.action.CHARGING") == false) goto L31;
        setState(Boolean.TRUE);
        return;
    L31:
        return;
    L19:
        if (r42.equals("android.os.action.DISCHARGING") == false) goto L32;
        setState(Boolean.FALSE);
        return;
    L32:
        return;
    L24:
        if (r42.equals("android.intent.action.ACTION_POWER_DISCONNECTED") == true) goto L26;
        return;
    L26:
        setState(Boolean.FALSE);
        return;
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public Boolean readSystemState() {
        IntentFilter r0 = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        Intent r02 = getAppContext().registerReceiver(null, r0);
        if (r02 != null) goto L7;
        Logger.get().error(BatteryChargingTrackerKt.access$getTAG$p(), "getInitialState - null intent received");
        return Boolean.FALSE;
    L7:
        return Boolean.valueOf(isBatteryChangedIntentCharging(r02));
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public /* bridge */ /* synthetic */ Object readSystemState() {
        return readSystemState();
    }
}
