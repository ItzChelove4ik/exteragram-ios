package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import androidx.core.net.ConnectivityManagerCompat;
import androidx.work.Logger;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a%\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001f\u0010\f\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0004\b\f\u0010\r\"\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010\"\u0018\u0010\u0011\u001a\u00020\n*\u00020\b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroid/content/Context;", "context", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "taskExecutor", "Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "Landroidx/work/impl/constraints/NetworkState;", "NetworkStateTracker", "(Landroid/content/Context;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;)Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "Landroid/net/ConnectivityManager;", "connectivityManager", _UrlKt.FRAGMENT_ENCODE_SET, "isBlocked", "getActiveNetworkState", "(Landroid/net/ConnectivityManager;Z)Landroidx/work/impl/constraints/NetworkState;", _UrlKt.FRAGMENT_ENCODE_SET, "TAG", "Ljava/lang/String;", "isActiveNetworkValidated", "(Landroid/net/ConnectivityManager;)Z", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public abstract class NetworkStateTrackerKt {
    private static final String TAG = null;

    static {
        TAG = Logger.tagWithPrefix("NetworkStateTracker");
    }

    public static final ConstraintTracker<NetworkState> NetworkStateTracker(Context r1, TaskExecutor r2) {
        return new NetworkStateTrackerPre28(r1, r2);
    }

    public static final /* synthetic */ String access$getTAG$p() {
        return TAG;
    }

    public static final NetworkState getActiveNetworkState(ConnectivityManager r7, boolean r8) {
        NetworkInfo r0 = r7.getActiveNetworkInfo();     // Catch: SecurityException -> L23
        boolean r1 = false;
        if (r0 != null) goto L29;
    L10:
        boolean r3 = false;
        boolean r4 = true;
    L11:
        boolean r2 = isActiveNetworkValidated(r7);     // Catch: SecurityException -> L23
        boolean r72 = ConnectivityManagerCompat.isActiveNetworkMetered(r7);     // Catch: SecurityException -> L23
        if (r0 == null) goto L16;
        if (r0.isRoaming() == true) goto L16;
    L17:
        boolean r5 = r8;
        return new NetworkState(r1, r2, r72, r4, r5);
    L21:
        e = e;
    L22:
        SecurityException r73 = e;
    L25:
        Logger.get().error(TAG, "Unable to get active network state", r73);
        return new NetworkState(false, false, false, true, r5);
    L8:
        e = move-exception;
        r73 = e;
        r5 = r8;
    L16:
        r4 = r3;
        goto L17
    L29:
        if (r0.isConnected() == false) goto L10;
        r3 = false;
        r1 = true;
        r4 = true;
    L23:
        e = e;
        r5 = r8;
        goto L22
    }

    public static final boolean isActiveNetworkValidated(ConnectivityManager r4) {
        NetworkCapabilities r42 = r4.getNetworkCapabilities(r4.getActiveNetwork());     // Catch: SecurityException -> L7
        if (r42 == null) goto L9;
        return r42.hasCapability(16);
    L9:
        return false;
    L7:
        e = move-exception;
        Logger.get().error(TAG, "Unable to validate active network", e);
        return false;
    }
}
