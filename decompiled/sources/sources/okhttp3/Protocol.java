package okhttp3;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import okio.ZipFileSystem$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\r\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000f"}, d2 = {"Lokhttp3/Protocol;", _UrlKt.FRAGMENT_ENCODE_SET, "protocol", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "HTTP_1_0", "HTTP_1_1", "SPDY_3", "HTTP_2", "H2_PRIOR_KNOWLEDGE", "QUIC", "HTTP_3", "toString", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public enum Protocol extends Enum<Protocol> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ Protocol[] $VALUES = null;
    public static final Companion Companion = null;
    public static final Protocol H2_PRIOR_KNOWLEDGE = null;
    public static final Protocol HTTP_1_0 = null;
    public static final Protocol HTTP_1_1 = null;
    public static final Protocol HTTP_2 = null;
    public static final Protocol HTTP_3 = null;
    public static final Protocol QUIC = null;

    @Deprecated(message = "OkHttp has dropped support for SPDY. Prefer {@link #HTTP_2}.")
    public static final Protocol SPDY_3 = null;
    private final String protocol;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lokhttp3/Protocol$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "get", "Lokhttp3/Protocol;", "protocol", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final Protocol get(String r5) {
            Protocol r4 = Protocol.HTTP_1_0;
            if (Intrinsics.areEqual(r5, Protocol.access$getProtocol$p(r4)) == false) goto L5;
            return r4;
        L5:
            Protocol r42 = Protocol.HTTP_1_1;
            if (Intrinsics.areEqual(r5, Protocol.access$getProtocol$p(r42)) == false) goto L8;
            return r42;
        L8:
            Protocol r43 = Protocol.H2_PRIOR_KNOWLEDGE;
            if (Intrinsics.areEqual(r5, Protocol.access$getProtocol$p(r43)) == false) goto L11;
            return r43;
        L11:
            Protocol r44 = Protocol.HTTP_2;
            if (Intrinsics.areEqual(r5, Protocol.access$getProtocol$p(r44)) == false) goto L14;
            return r44;
        L14:
            Protocol r45 = Protocol.SPDY_3;
            if (Intrinsics.areEqual(r5, Protocol.access$getProtocol$p(r45)) == false) goto L17;
            return r45;
        L17:
            Protocol r46 = Protocol.QUIC;
            if (Intrinsics.areEqual(r5, Protocol.access$getProtocol$p(r46)) == false) goto L20;
            return r46;
        L20:
            Protocol r47 = Protocol.HTTP_3;
            if (StringsKt.startsWith$default(r5, Protocol.access$getProtocol$p(r47), false, 2, null) == false) goto L23;
            return r47;
        L23:
            ZipFileSystem$$ExternalSyntheticBUOutline0.m("Unexpected protocol: ", r5);
            return null;
        }
    }

    private static final /* synthetic */ Protocol[] $values() {
        return new Protocol[]{HTTP_1_0, HTTP_1_1, SPDY_3, HTTP_2, H2_PRIOR_KNOWLEDGE, QUIC, HTTP_3};
    }

    static {
        HTTP_1_0 = new Protocol("HTTP_1_0", 0, "http/1.0");
        HTTP_1_1 = new Protocol("HTTP_1_1", 1, "http/1.1");
        SPDY_3 = new Protocol("SPDY_3", 2, "spdy/3.1");
        HTTP_2 = new Protocol("HTTP_2", 3, "h2");
        H2_PRIOR_KNOWLEDGE = new Protocol("H2_PRIOR_KNOWLEDGE", 4, "h2_prior_knowledge");
        QUIC = new Protocol("QUIC", 5, "quic");
        HTTP_3 = new Protocol("HTTP_3", 6, "h3");
        Protocol[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
        Companion = new Companion(null);
    }

    Protocol(String r1, int r2, String r3) {
        this.protocol = r3;
    }

    public static final /* synthetic */ String access$getProtocol$p(Protocol r0) {
        return r0.protocol;
    }

    @JvmStatic
    public static final Protocol get(String r1) {
        return Companion.get(r1);
    }

    public static EnumEntries<Protocol> getEntries() {
        return $ENTRIES;
    }

    public static Protocol valueOf(String r1) {
        return (Protocol) Enum.valueOf(Protocol.class, r1);
    }

    public static Protocol[] values() {
        return (Protocol[]) $VALUES.clone();
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.protocol;
    }
}
