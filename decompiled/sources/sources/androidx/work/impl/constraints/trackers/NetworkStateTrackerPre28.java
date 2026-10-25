package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.net.ConnectivityManager;
import androidx.work.Logger;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.utils.NetworkApi24;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000?\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0011\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u000f\u001a\u00020\u0002H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012¨\u0006\u0016"}, d2 = {"Landroidx/work/impl/constraints/trackers/NetworkStateTrackerPre28;", "Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "Landroidx/work/impl/constraints/NetworkState;", "context", "Landroid/content/Context;", "taskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "<init>", "(Landroid/content/Context;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;)V", "connectivityManager", "Landroid/net/ConnectivityManager;", "lock", _UrlKt.FRAGMENT_ENCODE_SET, "isBlocked", _UrlKt.FRAGMENT_ENCODE_SET, "readSystemState", "networkCallback", "androidx/work/impl/constraints/trackers/NetworkStateTrackerPre28$networkCallback$1", "Landroidx/work/impl/constraints/trackers/NetworkStateTrackerPre28$networkCallback$1;", "startTracking", _UrlKt.FRAGMENT_ENCODE_SET, "stopTracking", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class NetworkStateTrackerPre28 extends ConstraintTracker<NetworkState> {
    private final ConnectivityManager connectivityManager;
    private volatile boolean isBlocked;
    private final Object lock;
    private final NetworkStateTrackerPre28$networkCallback$1 networkCallback;

    public NetworkStateTrackerPre28(Context r1, TaskExecutor r2) {
        super(r1, r2);
        this.connectivityManager = (ConnectivityManager) getAppContext().getSystemService("connectivity");
        this.lock = new Object();
        this.networkCallback = new NetworkStateTrackerPre28$networkCallback$1(this);
    }

    public static final /* synthetic */ ConnectivityManager access$getConnectivityManager$p(NetworkStateTrackerPre28 r0) {
        return r0.connectivityManager;
    }

    public static final /* synthetic */ Object access$getLock$p(NetworkStateTrackerPre28 r0) {
        return r0.lock;
    }

    public static final /* synthetic */ boolean access$isBlocked$p(NetworkStateTrackerPre28 r0) {
        return r0.isBlocked;
    }

    public static final /* synthetic */ void access$setBlocked$p(NetworkStateTrackerPre28 r0, boolean r1) {
        r0.isBlocked = r1;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public NetworkState readSystemState() {
        return NetworkStateTrackerKt.getActiveNetworkState(this.connectivityManager, this.isBlocked);
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public /* bridge */ /* synthetic */ NetworkState readSystemState() {
        return readSystemState();
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public void startTracking() {
        Logger.get().debug(NetworkStateTrackerKt.access$getTAG$p(), "Registering network callback");     // Catch: SecurityException -> L5 IllegalArgumentException -> L7
        NetworkApi24.registerDefaultNetworkCallbackCompat(this.connectivityManager, this.networkCallback);     // Catch: SecurityException -> L5 IllegalArgumentException -> L7
        return;
    L7:
        e = move-exception;
        Logger.get().error(NetworkStateTrackerKt.access$getTAG$p(), "Received exception while registering network callback", e);
        return;
    L5:
        e = move-exception;
        Logger.get().error(NetworkStateTrackerKt.access$getTAG$p(), "Received exception while registering network callback", e);
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public void stopTracking() {
        Logger.get().debug(NetworkStateTrackerKt.access$getTAG$p(), "Unregistering network callback");     // Catch: SecurityException -> L5 IllegalArgumentException -> L7
        this.connectivityManager.unregisterNetworkCallback(this.networkCallback);     // Catch: SecurityException -> L5 IllegalArgumentException -> L7
        return;
    L7:
        e = move-exception;
        Logger.get().error(NetworkStateTrackerKt.access$getTAG$p(), "Received exception while unregistering network callback", e);
        return;
    L5:
        e = move-exception;
        Logger.get().error(NetworkStateTrackerKt.access$getTAG$p(), "Received exception while unregistering network callback", e);
    }
}
