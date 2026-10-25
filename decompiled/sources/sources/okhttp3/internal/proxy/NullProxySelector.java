package okhttp3.internal.proxy;

import c.f$$ExternalSyntheticBUOutline1;
import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J&\u0010\t\u001a\u00020\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016¨\u0006\u000f"}, d2 = {"Lokhttp3/internal/proxy/NullProxySelector;", "Ljava/net/ProxySelector;", "<init>", "()V", "select", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/net/Proxy;", "uri", "Ljava/net/URI;", "connectFailed", _UrlKt.FRAGMENT_ENCODE_SET, "sa", "Ljava/net/SocketAddress;", "ioe", "Ljava/io/IOException;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nNullProxySelector.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NullProxySelector.kt\nokhttp3/internal/proxy/NullProxySelector\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,40:1\n1#2:41\n*E\n"})
/* loaded from: classes5.dex */
public final class NullProxySelector extends ProxySelector {
    public static final NullProxySelector INSTANCE = null;

    static {
        INSTANCE = new NullProxySelector();
    }

    private NullProxySelector() {
    }

    @Override // java.net.ProxySelector
    public void connectFailed(URI r1, SocketAddress r2, IOException r3) {
    }

    @Override // java.net.ProxySelector
    public List<Proxy> select(URI r1) {
        if (r1 != null) goto L4;
        f$$ExternalSyntheticBUOutline1.m("uri must not be null");
        return null;
    L4:
        return CollectionsKt.listOf(Proxy.NO_PROXY);
    }
}
