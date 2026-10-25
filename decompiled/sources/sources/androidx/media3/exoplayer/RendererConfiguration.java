package androidx.media3.exoplayer;

/* loaded from: classes4.dex */
public final class RendererConfiguration {
    public static final RendererConfiguration DEFAULT = null;
    public final int offloadModePreferred;
    public final boolean tunneling;

    static {
        DEFAULT = new RendererConfiguration(0, false);
    }

    public RendererConfiguration(int r1, boolean r2) {
        this.offloadModePreferred = r1;
        this.tunneling = r2;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L15:
        return false;
    L8:
        if (RendererConfiguration.class != r5.getClass()) goto L15;
        RendererConfiguration r52 = (RendererConfiguration) r5;
        if (this.offloadModePreferred != r52.offloadModePreferred) goto L15;
        if (this.tunneling != r52.tunneling) goto L15;
        return true;
    }

    public int hashCode() {
        return (this.offloadModePreferred << 1) + (this.tunneling ? 1 : 0);
    }
}
