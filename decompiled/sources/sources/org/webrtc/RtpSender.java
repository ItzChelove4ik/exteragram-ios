package org.webrtc;

import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public class RtpSender {
    private MediaStreamTrack cachedTrack;
    private final DtmfSender dtmfSender;
    private long nativeRtpSender;
    private boolean ownsTrack;

    @CalledByNative
    public RtpSender(long r5) {
        this.ownsTrack = true;
        this.nativeRtpSender = r5;
        this.cachedTrack = MediaStreamTrack.createMediaStreamTrack(nativeGetTrack(r5));
        DtmfSender r1 = null;
        if (nativeGetMediaType(r5).equalsIgnoreCase(MediaStreamTrack.AUDIO_TRACK_KIND) == false) goto L9;
        long r52 = nativeGetDtmfSender(r5);
        if (r52 == 0) goto L7;
        r1 = new DtmfSender(r52);
    L7:
        this.dtmfSender = r1;
        return;
    L9:
        this.dtmfSender = null;
    }

    private void checkRtpSenderExists() {
        if (this.nativeRtpSender == 0) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("RtpSender has been disposed.");
    }

    private static native long nativeGetDtmfSender(long r0);

    private static native String nativeGetId(long r0);

    private static native String nativeGetMediaType(long r0);

    private static native RtpParameters nativeGetParameters(long r0);

    private static native List<String> nativeGetStreams(long r0);

    private static native long nativeGetTrack(long r0);

    private static native void nativeSetFrameEncryptor(long r0, long r2);

    private static native boolean nativeSetParameters(long r0, RtpParameters r2);

    private static native void nativeSetStreams(long r0, List<String> r2);

    private static native boolean nativeSetTrack(long r0, long r2);

    public void dispose() {
        checkRtpSenderExists();
        DtmfSender r0 = this.dtmfSender;
        if (r0 == null) goto L5;
        r0.dispose();
    L5:
        MediaStreamTrack r02 = this.cachedTrack;
        if (r02 != null) goto L8;
    L10:
        JniCommon.nativeReleaseRef(this.nativeRtpSender);
        this.nativeRtpSender = 0;
        return;
    L8:
        if (this.ownsTrack == false) goto L10;
        r02.dispose();
        goto L10
    }

    public DtmfSender dtmf() {
        return this.dtmfSender;
    }

    public long getNativeRtpSender() {
        checkRtpSenderExists();
        return this.nativeRtpSender;
    }

    public RtpParameters getParameters() {
        checkRtpSenderExists();
        return nativeGetParameters(this.nativeRtpSender);
    }

    public List<String> getStreams() {
        checkRtpSenderExists();
        return nativeGetStreams(this.nativeRtpSender);
    }

    public String id() {
        checkRtpSenderExists();
        return nativeGetId(this.nativeRtpSender);
    }

    public void setFrameEncryptor(FrameEncryptor r3) {
        checkRtpSenderExists();
        nativeSetFrameEncryptor(this.nativeRtpSender, r3.getNativeFrameEncryptor());
    }

    public boolean setParameters(RtpParameters r3) {
        checkRtpSenderExists();
        return nativeSetParameters(this.nativeRtpSender, r3);
    }

    public void setStreams(List<String> r3) {
        checkRtpSenderExists();
        nativeSetStreams(this.nativeRtpSender, r3);
    }

    public boolean setTrack(MediaStreamTrack r5, boolean r6) {
        checkRtpSenderExists();
        long r0 = this.nativeRtpSender;
        if (r5 != null) goto L5;
        long r2 = 0;
    L7:
        if (nativeSetTrack(r0, r2) == true) goto L10;
        return false;
    L10:
        MediaStreamTrack r02 = this.cachedTrack;
        if (r02 != null) goto L13;
    L15:
        this.cachedTrack = r5;
        this.ownsTrack = r6;
        return true;
    L13:
        if (this.ownsTrack == false) goto L15;
        r02.dispose();
        goto L15
    L5:
        r2 = r5.getNativeMediaStreamTrack();
        goto L7
    }

    public MediaStreamTrack track() {
        return this.cachedTrack;
    }
}
