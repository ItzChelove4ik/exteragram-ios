package okhttp3;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\tJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rJ\r\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\b\u0010J\u001b\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00030\u0005H\u0007¢\u0006\u0002\b\u0011J\u000f\u0010\b\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\b\u0012J\r\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0002\b\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0003H\u0016R\u0013\u0010\u0002\u001a\u00020\u00038\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\nR!\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00030\u00058\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0013\u0010\b\u001a\u0004\u0018\u00010\u00038G¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u0011\u0010\f\u001a\u00020\r8G¢\u0006\u0006\u001a\u0004\b\f\u0010\u000e¨\u0006\u001a"}, d2 = {"Lokhttp3/Challenge;", _UrlKt.FRAGMENT_ENCODE_SET, "scheme", _UrlKt.FRAGMENT_ENCODE_SET, "authParams", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;Ljava/util/Map;)V", "realm", "(Ljava/lang/String;Ljava/lang/String;)V", "()Ljava/lang/String;", "()Ljava/util/Map;", "charset", "Ljava/nio/charset/Charset;", "()Ljava/nio/charset/Charset;", "withCharset", "-deprecated_scheme", "-deprecated_authParams", "-deprecated_realm", "-deprecated_charset", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nChallenge.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Challenge.kt\nokhttp3/Challenge\n+ 2 -UtilJvm.kt\nokhttp3/internal/_UtilJvmKt\n*L\n1#1,124:1\n250#2:125\n*S KotlinDebug\n*F\n+ 1 Challenge.kt\nokhttp3/Challenge\n*L\n68#1:125\n*E\n"})
/* loaded from: classes5.dex */
public final class Challenge {
    private final Map<String, String> authParams;
    private final String scheme;

    public Challenge(String r2, String r3) {
        this(r2, Collections.singletonMap("realm", r3));
    }

    public Challenge(String r4, Map<String, String> r5) {
        this.scheme = r4;
        LinkedHashMap r42 = new LinkedHashMap();
        Iterator<Map.Entry<String, String>> r52 = r5.entrySet().iterator();
    L4:
        if (r52.hasNext() == false) goto L10;
        Map.Entry<String, String> r0 = r52.next();
        String r1 = r0.getKey();
        String r02 = r0.getValue();
        if (r1 == null) goto L8;
        String r12 = r1.toLowerCase(Locale.US);
    L9:
        r42.put(r12, r02);
        goto L4
    L8:
        r12 = null;
        goto L9
    L10:
        this.authParams = Collections.unmodifiableMap(r42);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "authParams", imports = {}))
    @JvmName(name = "-deprecated_authParams")
    /* renamed from: -deprecated_authParams, reason: not valid java name */
    public final Map<String, String> m3624deprecated_authParams() {
        return this.authParams;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "charset", imports = {}))
    @JvmName(name = "-deprecated_charset")
    /* renamed from: -deprecated_charset, reason: not valid java name */
    public final Charset m3625deprecated_charset() {
        return charset();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "realm", imports = {}))
    @JvmName(name = "-deprecated_realm")
    /* renamed from: -deprecated_realm, reason: not valid java name */
    public final String m3626deprecated_realm() {
        return realm();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "scheme", imports = {}))
    @JvmName(name = "-deprecated_scheme")
    /* renamed from: -deprecated_scheme, reason: not valid java name */
    public final String m3627deprecated_scheme() {
        return this.scheme;
    }

    @JvmName(name = "authParams")
    public final Map<String, String> authParams() {
        return this.authParams;
    }

    @JvmName(name = "charset")
    public final Charset charset() {
        String r1 = this.authParams.get("charset");
        if (r1 == null) goto L7;
        return Charset.forName(r1);
    L7:
        return Charsets.ISO_8859_1;
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof Challenge) == false) goto L10;
        Challenge r32 = (Challenge) r3;
        if (Intrinsics.areEqual(r32.scheme, this.scheme) == true) goto L7;
        return false;
    L7:
        if (Intrinsics.areEqual(r32.authParams, this.authParams) == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public int hashCode() {
        return ((899 + this.scheme.hashCode()) * 31) + this.authParams.hashCode();
    }

    @JvmName(name = "realm")
    public final String realm() {
        return this.authParams.get("realm");
    }

    @JvmName(name = "scheme")
    public final String scheme() {
        return this.scheme;
    }

    public String toString() {
        return this.scheme + " authParams=" + this.authParams;
    }

    public final Challenge withCharset(Charset r3) {
        Map r0 = MapsKt.toMutableMap(this.authParams);
        r0.put("charset", r3.name());
        return new Challenge(this.scheme, r0);
    }
}
