package okhttp3;

import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\fR\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\u000e"}, d2 = {"Lokhttp3/TlsVersion;", _UrlKt.FRAGMENT_ENCODE_SET, "javaName", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "()Ljava/lang/String;", "TLS_1_3", "TLS_1_2", "TLS_1_1", "TLS_1_0", "SSL_3_0", "-deprecated_javaName", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public enum TlsVersion extends Enum<TlsVersion> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ TlsVersion[] $VALUES = null;
    public static final Companion Companion = null;
    public static final TlsVersion SSL_3_0 = null;
    public static final TlsVersion TLS_1_0 = null;
    public static final TlsVersion TLS_1_1 = null;
    public static final TlsVersion TLS_1_2 = null;
    public static final TlsVersion TLS_1_3 = null;
    private final String javaName;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lokhttp3/TlsVersion$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "forJavaName", "Lokhttp3/TlsVersion;", "javaName", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final TlsVersion forJavaName(String r2) {
            int r1 = r2.hashCode();
            if (r1 == 79201641) goto L25;
            if (r1 == 79923350) goto L21;
            switch(r1) {
                case -503070503: goto L17;
                case -503070502: goto L13;
                case -503070501: goto L9;
                default: goto L28;
            };
        L28:
            f$$ExternalSyntheticBUOutline1.m("Unexpected TLS version: ".concat(r2));
            return null;
        L9:
            if (r2.equals("TLSv1.3") == false) goto L28;
            return TlsVersion.TLS_1_3;
        L13:
            if (r2.equals("TLSv1.2") == false) goto L28;
            return TlsVersion.TLS_1_2;
        L17:
            if (r2.equals("TLSv1.1") == false) goto L28;
            return TlsVersion.TLS_1_1;
        L21:
            if (r2.equals("TLSv1") == false) goto L28;
            return TlsVersion.TLS_1_0;
        L25:
            if (r2.equals("SSLv3") == false) goto L28;
            return TlsVersion.SSL_3_0;
        }
    }

    private static final /* synthetic */ TlsVersion[] $values() {
        return new TlsVersion[]{TLS_1_3, TLS_1_2, TLS_1_1, TLS_1_0, SSL_3_0};
    }

    static {
        TLS_1_3 = new TlsVersion("TLS_1_3", 0, "TLSv1.3");
        TLS_1_2 = new TlsVersion("TLS_1_2", 1, "TLSv1.2");
        TLS_1_1 = new TlsVersion("TLS_1_1", 2, "TLSv1.1");
        TLS_1_0 = new TlsVersion("TLS_1_0", 3, "TLSv1");
        SSL_3_0 = new TlsVersion("SSL_3_0", 4, "SSLv3");
        TlsVersion[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
        Companion = new Companion(null);
    }

    TlsVersion(String r1, int r2, String r3) {
        this.javaName = r3;
    }

    @JvmStatic
    public static final TlsVersion forJavaName(String r1) {
        return Companion.forJavaName(r1);
    }

    public static EnumEntries<TlsVersion> getEntries() {
        return $ENTRIES;
    }

    public static TlsVersion valueOf(String r1) {
        return (TlsVersion) Enum.valueOf(TlsVersion.class, r1);
    }

    public static TlsVersion[] values() {
        return (TlsVersion[]) $VALUES.clone();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "javaName", imports = {}))
    @JvmName(name = "-deprecated_javaName")
    /* renamed from: -deprecated_javaName, reason: not valid java name */
    public final String m3742deprecated_javaName() {
        return this.javaName;
    }

    @JvmName(name = "javaName")
    public final String javaName() {
        return this.javaName;
    }
}
