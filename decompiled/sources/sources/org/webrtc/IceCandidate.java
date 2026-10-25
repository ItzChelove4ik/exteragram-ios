package org.webrtc;

import java.util.Arrays;
import okhttp3.internal.url._UrlKt;
import org.webrtc.PeerConnection;

/* loaded from: classes6.dex */
public class IceCandidate {
    public final PeerConnection.AdapterType adapterType;
    public final String sdp;
    public final int sdpMLineIndex;
    public final String sdpMid;
    public final String serverUrl;

    public IceCandidate(String r1, int r2, String r3) {
        this.sdpMid = r1;
        this.sdpMLineIndex = r2;
        this.sdp = r3;
        this.serverUrl = _UrlKt.FRAGMENT_ENCODE_SET;
        this.adapterType = PeerConnection.AdapterType.UNKNOWN;
    }

    @CalledByNative
    public IceCandidate(String r1, int r2, String r3, String r4, PeerConnection.AdapterType r5) {
        this.sdpMid = r1;
        this.sdpMLineIndex = r2;
        this.sdp = r3;
        this.serverUrl = r4;
        this.adapterType = r5;
    }

    private static boolean objectEquals(Object r0, Object r1) {
        if (r0 != null) goto L9;
        if (r1 != null) goto L6;
        return true;
    L6:
        return false;
    L9:
        return r0.equals(r1);
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof IceCandidate) == true) goto L5;
        return false;
    L5:
        IceCandidate r42 = (IceCandidate) r4;
        if (objectEquals(this.sdpMid, r42.sdpMid) == true) goto L8;
    L13:
        return false;
    L8:
        if (this.sdpMLineIndex != r42.sdpMLineIndex) goto L13;
        if (objectEquals(this.sdp, r42.sdp) == false) goto L13;
        return true;
    }

    @CalledByNative
    public String getSdp() {
        return this.sdp;
    }

    @CalledByNative
    public String getSdpMid() {
        return this.sdpMid;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.sdpMid, Integer.valueOf(this.sdpMLineIndex), this.sdp});
    }

    public String toString() {
        return this.sdpMid + ":" + this.sdpMLineIndex + ":" + this.sdp + ":" + this.serverUrl + ":" + this.adapterType.toString();
    }
}
