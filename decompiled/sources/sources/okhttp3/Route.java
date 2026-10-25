package okhttp3;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal._HostnamesCommonKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\rJ\r\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\b\u000eJ\r\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\b\u000fJ\u0006\u0010\u0010\u001a\u00020\u0011J\u0013\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\nR\u0013\u0010\u0004\u001a\u00020\u00058\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0013\u0010\u0006\u001a\u00020\u00078\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\f¨\u0006\u0018"}, d2 = {"Lokhttp3/Route;", _UrlKt.FRAGMENT_ENCODE_SET, "address", "Lokhttp3/Address;", "proxy", "Ljava/net/Proxy;", "socketAddress", "Ljava/net/InetSocketAddress;", "<init>", "(Lokhttp3/Address;Ljava/net/Proxy;Ljava/net/InetSocketAddress;)V", "()Lokhttp3/Address;", "()Ljava/net/Proxy;", "()Ljava/net/InetSocketAddress;", "-deprecated_address", "-deprecated_proxy", "-deprecated_socketAddress", "requiresTunnel", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Route {
    private final Address address;
    private final Proxy proxy;
    private final InetSocketAddress socketAddress;

    public Route(Address r1, Proxy r2, InetSocketAddress r3) {
        this.address = r1;
        this.proxy = r2;
        this.socketAddress = r3;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "address", imports = {}))
    @JvmName(name = "-deprecated_address")
    /* renamed from: -deprecated_address, reason: not valid java name */
    public final Address m3739deprecated_address() {
        return this.address;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "proxy", imports = {}))
    @JvmName(name = "-deprecated_proxy")
    /* renamed from: -deprecated_proxy, reason: not valid java name */
    public final Proxy m3740deprecated_proxy() {
        return this.proxy;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "socketAddress", imports = {}))
    @JvmName(name = "-deprecated_socketAddress")
    /* renamed from: -deprecated_socketAddress, reason: not valid java name */
    public final InetSocketAddress m3741deprecated_socketAddress() {
        return this.socketAddress;
    }

    @JvmName(name = "address")
    public final Address address() {
        return this.address;
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof Route) == false) goto L12;
        Route r32 = (Route) r3;
        if (Intrinsics.areEqual(r32.address, this.address) == true) goto L7;
        return false;
    L7:
        if (Intrinsics.areEqual(r32.proxy, this.proxy) == true) goto L9;
        return false;
    L9:
        if (Intrinsics.areEqual(r32.socketAddress, this.socketAddress) == false) goto L16;
        return true;
    L16:
        return false;
    L12:
        return false;
    }

    public int hashCode() {
        return ((((527 + this.address.hashCode()) * 31) + this.proxy.hashCode()) * 31) + this.socketAddress.hashCode();
    }

    @JvmName(name = "proxy")
    public final Proxy proxy() {
        return this.proxy;
    }

    public final boolean requiresTunnel() {
        if (this.proxy.type() == Proxy.Type.HTTP) goto L6;
        return false;
    L6:
        if (this.address.sslSocketFactory() == null) goto L8;
        return true;
    L8:
        if (this.address.protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE) == true) goto L13;
        return false;
    L13:
        return true;
    }

    @JvmName(name = "socketAddress")
    public final InetSocketAddress socketAddress() {
        return this.socketAddress;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        String r1 = this.address.url().host();
        InetAddress r2 = this.socketAddress.getAddress();
        if (r2 == null) goto L7;
        String r22 = r2.getHostAddress();
        if (r22 == null) goto L7;
        String r23 = _HostnamesCommonKt.toCanonicalHost(r22);
    L9:
        if (StringsKt.contains$default(r1, ':', false, 2, null) == false) goto L11;
        r0.append("[");
        r0.append(r1);
        r0.append("]");
    L13:
        if (this.address.url().port() == this.socketAddress.getPort()) goto L15;
    L16:
        r0.append(":");
        r0.append(this.address.url().port());
    L18:
        if (Intrinsics.areEqual(r1, r23) == true) goto L31;
        if (Intrinsics.areEqual(this.proxy, Proxy.NO_PROXY) == false) goto L22;
        r0.append(" at ");
    L23:
        if (r23 != null) goto L26;
        r0.append("<unresolved>");
    L29:
        r0.append(":");
        r0.append(this.socketAddress.getPort());
        goto L31
    L26:
        if (StringsKt.contains$default(r23, ':', false, 2, null) == false) goto L28;
        r0.append("[");
        r0.append(r23);
        r0.append("]");
        goto L29
    L28:
        r0.append(r23);
        goto L29
    L22:
        r0.append(" via proxy ");
    L31:
        return r0.toString();
    L15:
        if (Intrinsics.areEqual(r1, r23) == false) goto L18;
    L11:
        r0.append(r1);
    L7:
        r23 = null;
        goto L9
    }
}
