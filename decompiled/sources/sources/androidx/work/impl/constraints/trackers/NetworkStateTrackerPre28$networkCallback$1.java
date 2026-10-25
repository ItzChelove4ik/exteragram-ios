package androidx.work.impl.constraints.trackers;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import androidx.work.Logger;
import androidx.work.impl.constraints.NetworkState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"androidx/work/impl/constraints/trackers/NetworkStateTrackerPre28$networkCallback$1", "Landroid/net/ConnectivityManager$NetworkCallback;", "onCapabilitiesChanged", _UrlKt.FRAGMENT_ENCODE_SET, "network", "Landroid/net/Network;", "capabilities", "Landroid/net/NetworkCapabilities;", "onLost", "onBlockedStatusChanged", "blocked", _UrlKt.FRAGMENT_ENCODE_SET, "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class NetworkStateTrackerPre28$networkCallback$1 extends ConnectivityManager.NetworkCallback {
    final /* synthetic */ NetworkStateTrackerPre28 this$0;

    public NetworkStateTrackerPre28$networkCallback$1(NetworkStateTrackerPre28 r1) {
        this.this$0 = r1;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onBlockedStatusChanged(Network r9, boolean r10) {
        if (Intrinsics.areEqual(r9, NetworkStateTrackerPre28.access$getConnectivityManager$p(this.this$0).getActiveNetwork()) == false) goto L18;
        Logger.get().debug(NetworkStateTrackerKt.access$getTAG$p(), "Network blocked status changed: " + r10);
        NetworkState r0 = this.this$0.getState();
        Object r92 = NetworkStateTrackerPre28.access$getLock$p(this.this$0);
        NetworkStateTrackerPre28 r1 = this.this$0;
        monitor-enter(r92);
    L14:
        th = move-exception;
        throw th;
    L7:
        if (NetworkStateTrackerPre28.access$isBlocked$p(r1) != r10) goto L10;
        monitor-exit(r92);
        return;
    L10:
        NetworkStateTrackerPre28.access$setBlocked$p(r1, r10);     // Catch: Throwable -> L14
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L14
        monitor-exit(r92);
        this.this$0.setState(NetworkState.copy$default(r0, false, false, false, false, r10, 15, null));
        return;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onCapabilitiesChanged(Network r4, NetworkCapabilities r5) {
        Logger.get().debug(NetworkStateTrackerKt.access$getTAG$p(), "Network capabilities changed: " + r5);
        NetworkStateTrackerPre28 r42 = this.this$0;
        r42.setState(NetworkStateTrackerKt.getActiveNetworkState(NetworkStateTrackerPre28.access$getConnectivityManager$p(r42), NetworkStateTrackerPre28.access$isBlocked$p(this.this$0)));
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLost(Network r7) {
        Logger.get().debug(NetworkStateTrackerKt.access$getTAG$p(), "Network connection lost");
        this.this$0.setState(new NetworkState(false, false, false, false, false));
    }
}
