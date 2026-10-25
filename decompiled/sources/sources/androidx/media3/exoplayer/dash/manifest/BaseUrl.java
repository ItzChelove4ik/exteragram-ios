package androidx.media3.exoplayer.dash.manifest;

import java.util.Objects;

/* loaded from: classes4.dex */
public final class BaseUrl {
    public final int priority;
    public final String serviceLocation;
    public final String url;
    public final int weight;

    public BaseUrl(String r1, String r2, int r3, int r4) {
        this.url = r1;
        this.serviceLocation = r2;
        this.priority = r3;
        this.weight = r4;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof BaseUrl) == true) goto L8;
        return false;
    L8:
        BaseUrl r52 = (BaseUrl) r5;
        if (this.priority == r52.priority) goto L11;
    L17:
        return false;
    L11:
        if (this.weight != r52.weight) goto L17;
        if (Objects.equals(this.url, r52.url) == false) goto L17;
        if (Objects.equals(this.serviceLocation, r52.serviceLocation) == false) goto L17;
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.url, this.serviceLocation, Integer.valueOf(this.priority), Integer.valueOf(this.weight)});
    }
}
