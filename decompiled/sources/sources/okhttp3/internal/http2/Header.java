package okhttp3.internal.http2;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;
import okio.ByteString;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\bB\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\tJ\b\u0010\f\u001a\u00020\u0007H\u0016J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u000bHÖ\u0001R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lokhttp3/internal/http2/Header;", _UrlKt.FRAGMENT_ENCODE_SET, "name", "Lokio/ByteString;", "value", "<init>", "(Lokio/ByteString;Lokio/ByteString;)V", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/String;Ljava/lang/String;)V", "(Lokio/ByteString;Ljava/lang/String;)V", "hpackSize", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "component1", "component2", "copy", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "hashCode", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Header {
    public static final Companion Companion = null;

    @JvmField
    public static final ByteString PSEUDO_PREFIX = null;

    @JvmField
    public static final ByteString RESPONSE_STATUS = null;
    public static final String RESPONSE_STATUS_UTF8 = ":status";

    @JvmField
    public static final ByteString TARGET_AUTHORITY = null;
    public static final String TARGET_AUTHORITY_UTF8 = ":authority";

    @JvmField
    public static final ByteString TARGET_METHOD = null;
    public static final String TARGET_METHOD_UTF8 = ":method";

    @JvmField
    public static final ByteString TARGET_PATH = null;
    public static final String TARGET_PATH_UTF8 = ":path";

    @JvmField
    public static final ByteString TARGET_SCHEME = null;
    public static final String TARGET_SCHEME_UTF8 = ":scheme";

    @JvmField
    public final int hpackSize;

    @JvmField
    public final ByteString name;

    @JvmField
    public final ByteString value;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lokhttp3/internal/http2/Header$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "PSEUDO_PREFIX", "Lokio/ByteString;", "RESPONSE_STATUS_UTF8", _UrlKt.FRAGMENT_ENCODE_SET, "TARGET_METHOD_UTF8", "TARGET_PATH_UTF8", "TARGET_SCHEME_UTF8", "TARGET_AUTHORITY_UTF8", "RESPONSE_STATUS", "TARGET_METHOD", "TARGET_PATH", "TARGET_SCHEME", "TARGET_AUTHORITY", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
        ByteString.Companion r0 = ByteString.Companion;
        PSEUDO_PREFIX = r0.encodeUtf8(":");
        RESPONSE_STATUS = r0.encodeUtf8(RESPONSE_STATUS_UTF8);
        TARGET_METHOD = r0.encodeUtf8(TARGET_METHOD_UTF8);
        TARGET_PATH = r0.encodeUtf8(TARGET_PATH_UTF8);
        TARGET_SCHEME = r0.encodeUtf8(TARGET_SCHEME_UTF8);
        TARGET_AUTHORITY = r0.encodeUtf8(TARGET_AUTHORITY_UTF8);
    }

    public Header(String r2, String r3) {
        ByteString.Companion r0 = ByteString.Companion;
        this(r0.encodeUtf8(r2), r0.encodeUtf8(r3));
    }

    public Header(ByteString r2, String r3) {
        this(r2, ByteString.Companion.encodeUtf8(r3));
    }

    public Header(ByteString r1, ByteString r2) {
        this.name = r1;
        this.value = r2;
        this.hpackSize = (r1.size() + 32) + r2.size();
    }

    public static /* synthetic */ Header copy$default(Header r0, ByteString r1, ByteString r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = r0.name;
    L6:
        if ((r3 & 2) == 0) goto L9;
        r2 = r0.value;
    L9:
        return r0.copy(r1, r2);
    }

    public final ByteString component1() {
        return this.name;
    }

    public final ByteString component2() {
        return this.value;
    }

    public final Header copy(ByteString r1, ByteString r2) {
        return new Header(r1, r2);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Header) == true) goto L8;
        return false;
    L8:
        Header r52 = (Header) r5;
        if (Intrinsics.areEqual(this.name, r52.name) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.value, r52.value) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.value.hashCode();
    }

    public String toString() {
        return this.name.utf8() + ": " + this.value.utf8();
    }
}
