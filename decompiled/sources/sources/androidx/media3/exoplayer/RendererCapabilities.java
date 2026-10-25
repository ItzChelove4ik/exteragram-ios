package androidx.media3.exoplayer;

import android.annotation.SuppressLint;
import androidx.media3.common.Format;

/* loaded from: classes4.dex */
public interface RendererCapabilities {

    public interface Listener {
        void onRendererCapabilitiesChanged(Renderer r1);
    }

    static int create(int r1) {
        return create(r1, 0, 0, 0);
    }

    static int create(int r6, int r7, int r8) {
        return create(r6, r7, r8, 0, 128, 0);
    }

    static int create(int r6, int r7, int r8, int r9) {
        return create(r6, r7, r8, 0, 128, r9);
    }

    static int create(int r6, int r7, int r8, int r9, int r10) {
        return create(r6, r7, r8, r9, r10, 0);
    }

    @SuppressLint({"WrongConstant"})
    static int create(int r0, int r1, int r2, int r3, int r4, int r5) {
        return ((((r0 | r1) | r2) | r3) | r4) | r5;
    }

    @SuppressLint({"WrongConstant"})
    static int getAdaptiveSupport(int r0) {
        return r0 & 24;
    }

    @SuppressLint({"WrongConstant"})
    static int getAudioOffloadSupport(int r0) {
        return r0 & 3584;
    }

    @SuppressLint({"WrongConstant"})
    static int getDecoderSupport(int r0) {
        return r0 & 384;
    }

    @SuppressLint({"WrongConstant"})
    static int getFormatSupport(int r0) {
        return r0 & 7;
    }

    @SuppressLint({"WrongConstant"})
    static int getHardwareAccelerationSupport(int r0) {
        return r0 & 64;
    }

    @SuppressLint({"WrongConstant"})
    static int getTunnelingSupport(int r0) {
        return r0 & 32;
    }

    static boolean isFormatSupported(int r1, boolean r2) {
        int r12 = getFormatSupport(r1);
        if (r12 == 4) goto L10;
        if (r2 == true) goto L6;
        return false;
    L6:
        if (r12 == 3) goto L13;
        return false;
    L13:
        return true;
    L10:
        return true;
    }

    void clearListener();

    String getName();

    int getTrackType();

    void setListener(Listener r1);

    int supportsFormat(Format r1);

    int supportsMixedMimeTypeAdaptation();
}
