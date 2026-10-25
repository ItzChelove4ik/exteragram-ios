package org.webrtc;

import okio.Segment$$ExternalSyntheticBUOutline1;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes6.dex */
public class RtpReceiver {
    private MediaStreamTrack cachedTrack;
    private long nativeObserver;
    private long nativeRtpReceiver;

    public interface Observer {
        @CalledByNative("Observer")
        void onFirstPacketReceived(MediaStreamTrack.MediaType r1);
    }

    @CalledByNative
    public RtpReceiver(long r1) {
        this.nativeRtpReceiver = r1;
        this.cachedTrack = MediaStreamTrack.createMediaStreamTrack(nativeGetTrack(r1));
    }

    private void checkRtpReceiverExists() {
        if (this.nativeRtpReceiver == 0) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("RtpReceiver has been disposed.");
    }

    private static native String nativeGetId(long r0);

    private static native RtpParameters nativeGetParameters(long r0);

    private static native long nativeGetTrack(long r0);

    private static native void nativeSetFrameDecryptor(long r0, long r2);

    private static native long nativeSetObserver(long r0, Observer r2);

    private static native void nativeUnsetObserver(long r0, long r2);

    public void SetObserver(Observer r5) {
        checkRtpReceiverExists();
        long r0 = this.nativeObserver;
        if (r0 == 0) goto L5;
        nativeUnsetObserver(this.nativeRtpReceiver, r0);
    L5:
        this.nativeObserver = nativeSetObserver(this.nativeRtpReceiver, r5);
    }

    @CalledByNative
    public void dispose() {
        checkRtpReceiverExists();
        this.cachedTrack.dispose();
        long r0 = this.nativeObserver;
        if (r0 == 0) goto L5;
        nativeUnsetObserver(this.nativeRtpReceiver, r0);
        this.nativeObserver = 0;
    L5:
        JniCommon.nativeReleaseRef(this.nativeRtpReceiver);
        this.nativeRtpReceiver = 0;
    }

    public long getNativeRtpReceiver() {
        checkRtpReceiverExists();
        return this.nativeRtpReceiver;
    }

    public RtpParameters getParameters() {
        checkRtpReceiverExists();
        return nativeGetParameters(this.nativeRtpReceiver);
    }

    public String id() {
        checkRtpReceiverExists();
        return nativeGetId(this.nativeRtpReceiver);
    }

    public void setFrameDecryptor(FrameDecryptor r3) {
        checkRtpReceiverExists();
        nativeSetFrameDecryptor(this.nativeRtpReceiver, r3.getNativeFrameDecryptor());
    }

    public MediaStreamTrack track() {
        return this.cachedTrack;
    }
}
