package org.webrtc;

import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public class DtmfSender {
    private long nativeDtmfSender;

    public DtmfSender(long r1) {
        this.nativeDtmfSender = r1;
    }

    private void checkDtmfSenderExists() {
        if (this.nativeDtmfSender == 0) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("DtmfSender has been disposed.");
    }

    private static native boolean nativeCanInsertDtmf(long r0);

    private static native int nativeDuration(long r0);

    private static native boolean nativeInsertDtmf(long r0, String r2, int r3, int r4);

    private static native int nativeInterToneGap(long r0);

    private static native String nativeTones(long r0);

    public boolean canInsertDtmf() {
        checkDtmfSenderExists();
        return nativeCanInsertDtmf(this.nativeDtmfSender);
    }

    public void dispose() {
        checkDtmfSenderExists();
        JniCommon.nativeReleaseRef(this.nativeDtmfSender);
        this.nativeDtmfSender = 0;
    }

    public int duration() {
        checkDtmfSenderExists();
        return nativeDuration(this.nativeDtmfSender);
    }

    public boolean insertDtmf(String r3, int r4, int r5) {
        checkDtmfSenderExists();
        return nativeInsertDtmf(this.nativeDtmfSender, r3, r4, r5);
    }

    public int interToneGap() {
        checkDtmfSenderExists();
        return nativeInterToneGap(this.nativeDtmfSender);
    }

    public String tones() {
        checkDtmfSenderExists();
        return nativeTones(this.nativeDtmfSender);
    }
}
