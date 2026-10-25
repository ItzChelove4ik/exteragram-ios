package androidx.media3.exoplayer.dash.manifest;

/* loaded from: classes4.dex */
public final class UtcTimingElement {
    public final String schemeIdUri;
    public final String value;

    public UtcTimingElement(String r1, String r2) {
        this.schemeIdUri = r1;
        this.value = r2;
    }

    public String toString() {
        return this.schemeIdUri + ", " + this.value;
    }
}
