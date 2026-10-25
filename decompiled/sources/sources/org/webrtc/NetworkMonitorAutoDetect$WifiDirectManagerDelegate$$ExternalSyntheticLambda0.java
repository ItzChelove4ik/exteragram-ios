package org.webrtc;

import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pManager;
import org.webrtc.NetworkMonitorAutoDetect;

/* loaded from: classes6.dex */
public final /* synthetic */ class NetworkMonitorAutoDetect$WifiDirectManagerDelegate$$ExternalSyntheticLambda0 implements WifiP2pManager.GroupInfoListener {
    public final /* synthetic */ NetworkMonitorAutoDetect.WifiDirectManagerDelegate f$0;

    public /* synthetic */ NetworkMonitorAutoDetect$WifiDirectManagerDelegate$$ExternalSyntheticLambda0(NetworkMonitorAutoDetect.WifiDirectManagerDelegate r1) {
        this.f$0 = r1;
    }

    @Override // android.net.wifi.p2p.WifiP2pManager.GroupInfoListener
    public final void onGroupInfoAvailable(WifiP2pGroup r1) {
        NetworkMonitorAutoDetect.WifiDirectManagerDelegate.$r8$lambda$6gq2ENfpxuwxkjTLhcSsLus4oHM(this.f$0, r1);
    }
}
