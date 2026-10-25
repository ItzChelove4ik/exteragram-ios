package androidx.media3.exoplayer.dash.manifest;

/* loaded from: classes4.dex */
public final class ServiceDescriptionElement {
    public final long maxOffsetMs;
    public final float maxPlaybackSpeed;
    public final long minOffsetMs;
    public final float minPlaybackSpeed;
    public final long targetOffsetMs;

    public ServiceDescriptionElement(long r1, long r3, long r5, float r7, float r8) {
        this.targetOffsetMs = r1;
        this.minOffsetMs = r3;
        this.maxOffsetMs = r5;
        this.minPlaybackSpeed = r7;
        this.maxPlaybackSpeed = r8;
    }
}
