package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.os.Build;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001BU\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013¨\u0006\u0018"}, d2 = {"Landroidx/work/impl/constraints/trackers/Trackers;", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Landroid/content/Context;", "taskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "batteryChargingTracker", "Landroidx/work/impl/constraints/trackers/ConstraintTracker;", _UrlKt.FRAGMENT_ENCODE_SET, "batteryNotLowTracker", "Landroidx/work/impl/constraints/trackers/BatteryNotLowTracker;", "networkStateTracker", "Landroidx/work/impl/constraints/NetworkState;", "storageNotLowTracker", "<init>", "(Landroid/content/Context;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;Landroidx/work/impl/constraints/trackers/ConstraintTracker;Landroidx/work/impl/constraints/trackers/BatteryNotLowTracker;Landroidx/work/impl/constraints/trackers/ConstraintTracker;Landroidx/work/impl/constraints/trackers/ConstraintTracker;)V", "getContext", "()Landroid/content/Context;", "getBatteryChargingTracker", "()Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "getBatteryNotLowTracker", "()Landroidx/work/impl/constraints/trackers/BatteryNotLowTracker;", "getNetworkStateTracker", "getStorageNotLowTracker", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Trackers {
    private final ConstraintTracker<Boolean> batteryChargingTracker;
    private final BatteryNotLowTracker batteryNotLowTracker;
    private final Context context;
    private final ConstraintTracker<NetworkState> networkStateTracker;
    private final ConstraintTracker<Boolean> storageNotLowTracker;

    @JvmOverloads
    public Trackers(Context r1, TaskExecutor r2, ConstraintTracker<Boolean> r3, BatteryNotLowTracker r4, ConstraintTracker<NetworkState> r5, ConstraintTracker<Boolean> r6) {
        this.context = r1;
        this.batteryChargingTracker = r3;
        this.batteryNotLowTracker = r4;
        this.networkStateTracker = r5;
        this.storageNotLowTracker = r6;
    }

    public /* synthetic */ Trackers(Context r8, TaskExecutor r9, ConstraintTracker r10, BatteryNotLowTracker r11, ConstraintTracker r12, ConstraintTracker r13, int r14, DefaultConstructorMarker r15) {
        if ((r14 & 4) == 0) goto L5;
        r10 = new BatteryChargingTracker(r8.getApplicationContext(), r9);
    L5:
        ConstraintTracker r3 = r10;
        if ((r14 & 8) == 0) goto L8;
        r11 = new BatteryNotLowTracker(r8.getApplicationContext(), r9);
    L8:
        BatteryNotLowTracker r4 = r11;
        if ((r14 & 16) != 0) goto L11;
    L15:
        ConstraintTracker r5 = r12;
        if ((r14 & 32) == 0) goto L18;
        r13 = new StorageNotLowTracker(r8.getApplicationContext(), r9);
    L18:
        this(r8, r9, r3, r4, r5, r13);
        return;
    L11:
        if (Build.VERSION.SDK_INT >= 28) goto L14;
        ConstraintTracker<NetworkState> r102 = NetworkStateTrackerKt.NetworkStateTracker(r8.getApplicationContext(), r9);
    L13:
        r12 = r102;
        goto L15
    L14:
        r102 = null;
        goto L13
    }

    public final ConstraintTracker<Boolean> getBatteryChargingTracker() {
        return this.batteryChargingTracker;
    }

    public final BatteryNotLowTracker getBatteryNotLowTracker() {
        return this.batteryNotLowTracker;
    }

    public final Context getContext() {
        return this.context;
    }

    public final ConstraintTracker<NetworkState> getNetworkStateTracker() {
        return this.networkStateTracker;
    }

    public final ConstraintTracker<Boolean> getStorageNotLowTracker() {
        return this.storageNotLowTracker;
    }
}
