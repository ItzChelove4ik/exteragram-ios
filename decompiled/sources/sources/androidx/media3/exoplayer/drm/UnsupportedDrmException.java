package androidx.media3.exoplayer.drm;

/* loaded from: classes4.dex */
public final class UnsupportedDrmException extends Exception {
    public final int reason;

    public UnsupportedDrmException(int r1) {
        this.reason = r1;
    }

    public UnsupportedDrmException(int r1, Exception r2) {
        super(r2);
        this.reason = r1;
    }
}
