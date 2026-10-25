package okhttp3;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, d2 = {"okhttp3/TrailersSource$Companion$EMPTY$1", "Lokhttp3/TrailersSource;", "peek", "Lokhttp3/Headers;", "get", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TrailersSource$Companion$EMPTY$1 implements TrailersSource {
    public TrailersSource$Companion$EMPTY$1() {
    }

    @Override // okhttp3.TrailersSource
    public Headers get() {
        return Headers.EMPTY;
    }

    @Override // okhttp3.TrailersSource
    public Headers peek() {
        return Headers.EMPTY;
    }
}
