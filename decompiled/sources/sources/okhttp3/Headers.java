package okhttp3;

import j$.time.Instant;
import j$.util.DateRetargetClass;
import j$.util.DesugarDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.text.StringsKt;
import okhttp3.internal._HeadersCommonKt;
import okhttp3.internal.http.DateFormattingKt;
import okhttp3.internal.url._UrlKt;
import okio.Options$Companion$$ExternalSyntheticBUOutline0;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\b\u0018\u0000 52\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u000265B\u0017\b\u0000\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\t\u001a\u0004\u0018\u00010\u00032\u0006\u0010\b\u001a\u00020\u0003H\u0086\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\b\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0014\u001a\u00020\u0011H\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\b\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0011¢\u0006\u0004\b\b\u0010\u0016J\u0015\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0011¢\u0006\u0004\b\u0017\u0010\u0016J\u0013\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\u001b\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\u001b2\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001f\u001a\u00020\u001e¢\u0006\u0004\b\u001f\u0010 J\"\u0010\"\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00020!H\u0096\u0002¢\u0006\u0004\b\"\u0010#J\r\u0010%\u001a\u00020$¢\u0006\u0004\b%\u0010&J\u001a\u0010*\u001a\u00020)2\b\u0010(\u001a\u0004\u0018\u00010'H\u0096\u0002¢\u0006\u0004\b*\u0010+J\u000f\u0010,\u001a\u00020\u0011H\u0016¢\u0006\u0004\b,\u0010\u0013J\u000f\u0010-\u001a\u00020\u0003H\u0016¢\u0006\u0004\b-\u0010.J\u001f\u00100\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u001b0/¢\u0006\u0004\b0\u00101R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0005\u00102\u001a\u0004\b3\u00104R\u0011\u0010\u0014\u001a\u00020\u00118G¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0013¨\u00067"}, d2 = {"Lokhttp3/Headers;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Pair;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "namesAndValues", "<init>", "([Ljava/lang/String;)V", "name", "get", "(Ljava/lang/String;)Ljava/lang/String;", "Ljava/util/Date;", "getDate", "(Ljava/lang/String;)Ljava/util/Date;", "j$/time/Instant", "getInstant", "(Ljava/lang/String;)Lj$/time/Instant;", _UrlKt.FRAGMENT_ENCODE_SET, "-deprecated_size", "()I", "size", "index", "(I)Ljava/lang/String;", "value", _UrlKt.FRAGMENT_ENCODE_SET, "names", "()Ljava/util/Set;", _UrlKt.FRAGMENT_ENCODE_SET, "values", "(Ljava/lang/String;)Ljava/util/List;", _UrlKt.FRAGMENT_ENCODE_SET, "byteCount", "()J", _UrlKt.FRAGMENT_ENCODE_SET, "iterator", "()Ljava/util/Iterator;", "Lokhttp3/Headers$Builder;", "newBuilder", "()Lokhttp3/Headers$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "toMultimap", "()Ljava/util/Map;", "[Ljava/lang/String;", "getNamesAndValues$okhttp", "()[Ljava/lang/String;", "Companion", "Builder", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nHeaders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Headers.kt\nokhttp3/Headers\n+ 2 -UtilJvm.kt\nokhttp3/internal/_UtilJvmKt\n*L\n1#1,359:1\n246#2:360\n*S KotlinDebug\n*F\n+ 1 Headers.kt\nokhttp3/Headers\n*L\n110#1:360\n*E\n"})
/* loaded from: classes.dex */
public final class Headers implements Iterable<Pair<? extends String, ? extends String>>, KMappedMarker {
    public static final Companion Companion = null;

    @JvmField
    public static final Headers EMPTY = null;
    private final String[] namesAndValues;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\b\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\t\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\u0007J\u001d\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\fJ\u001d\u0010\r\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004¢\u0006\u0004\b\r\u0010\fJ\u0015\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0012¢\u0006\u0004\b\t\u0010\u0013J\u001f\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0014H\u0007¢\u0006\u0004\b\t\u0010\u0015J \u0010\u0016\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0012H\u0086\u0002¢\u0006\u0004\b\u0016\u0010\u0013J \u0010\u0016\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0014H\u0087\u0002¢\u0006\u0004\b\u0016\u0010\u0015J\u001f\u0010\b\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0006\u0010\fJ\u0015\u0010\u0017\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0004¢\u0006\u0004\b\u0017\u0010\u0007J \u0010\u0016\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H\u0086\u0002¢\u0006\u0004\b\u0016\u0010\fJ\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0086\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\u000e¢\u0006\u0004\b\u001a\u0010\u001bR \u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00040\u001c8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Lokhttp3/Headers$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "line", "addLenient$okhttp", "(Ljava/lang/String;)Lokhttp3/Headers$Builder;", "addLenient", "add", "name", "value", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Headers$Builder;", "addUnsafeNonAscii", "Lokhttp3/Headers;", "headers", "addAll", "(Lokhttp3/Headers;)Lokhttp3/Headers$Builder;", "Ljava/util/Date;", "(Ljava/lang/String;Ljava/util/Date;)Lokhttp3/Headers$Builder;", "j$/time/Instant", "(Ljava/lang/String;Lj$/time/Instant;)Lokhttp3/Headers$Builder;", "set", "removeAll", "get", "(Ljava/lang/String;)Ljava/lang/String;", "build", "()Lokhttp3/Headers;", _UrlKt.FRAGMENT_ENCODE_SET, "namesAndValues", "Ljava/util/List;", "getNamesAndValues$okhttp", "()Ljava/util/List;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nHeaders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Headers.kt\nokhttp3/Headers$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,359:1\n1#2:360\n*E\n"})
    public static final class Builder {
        private final List<String> namesAndValues;

        public Builder() {
            this.namesAndValues = new ArrayList(20);
        }

        public final Builder add(String r7) {
            int r72 = StringsKt.indexOf$default(r7, ':', 0, false, 6, null);
            if (r72 == (-1)) goto L6;
            add(StringsKt.trim(r7.substring(0, r72)).toString(), r7.substring(r72 + 1));
            return this;
        L6:
            Options$Companion$$ExternalSyntheticBUOutline0.m("Unexpected header: ", r7);
            return null;
        }

        @IgnoreJRERequirement
        public final Builder add(String r1, Instant r2) {
            return add(r1, DesugarDate.from(r2));
        }

        public final Builder add(String r1, String r2) {
            return _HeadersCommonKt.commonAdd(this, r1, r2);
        }

        public final Builder add(String r1, Date r2) {
            return add(r1, DateFormattingKt.toHttpDateString(r2));
        }

        public final Builder addAll(Headers r1) {
            return _HeadersCommonKt.commonAddAll(this, r1);
        }

        public final Builder addLenient$okhttp(String r7) {
            int r72 = StringsKt.indexOf$default(r7, ':', 1, false, 4, null);
            if (r72 == (-1)) goto L7;
            addLenient$okhttp(r7.substring(0, r72), r7.substring(r72 + 1));
            return this;
        L7:
            if (r7.charAt(0) != ':') goto L10;
            addLenient$okhttp(_UrlKt.FRAGMENT_ENCODE_SET, r7.substring(1));
            return this;
        L10:
            addLenient$okhttp(_UrlKt.FRAGMENT_ENCODE_SET, r7);
            return this;
        }

        public final Builder addLenient$okhttp(String r1, String r2) {
            return _HeadersCommonKt.commonAddLenient(this, r1, r2);
        }

        public final Builder addUnsafeNonAscii(String r1, String r2) {
            _HeadersCommonKt.headersCheckName(r1);
            addLenient$okhttp(r1, r2);
            return this;
        }

        public final Headers build() {
            return _HeadersCommonKt.commonBuild(this);
        }

        public final String get(String r1) {
            return _HeadersCommonKt.commonGet(this, r1);
        }

        public final List<String> getNamesAndValues$okhttp() {
            return this.namesAndValues;
        }

        public final Builder removeAll(String r1) {
            return _HeadersCommonKt.commonRemoveAll(this, r1);
        }

        @IgnoreJRERequirement
        public final Builder set(String r1, Instant r2) {
            return set(r1, DesugarDate.from(r2));
        }

        public final Builder set(String r1, String r2) {
            return _HeadersCommonKt.commonSet(this, r1, r2);
        }

        public final Builder set(String r1, Date r2) {
            return set(r1, DateFormattingKt.toHttpDateString(r2));
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\u0006\u001a\u00020\u00052\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\tH\u0007¢\u0006\u0004\b\n\u0010\u000bJ#\u0010\n\u001a\u00020\u00052\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\tH\u0007¢\u0006\u0004\b\f\u0010\u000bJ\u001d\u0010\r\u001a\u00020\u0005*\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u000eH\u0007¢\u0006\u0002\b\nJ!\u0010\n\u001a\u00020\u00052\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u000eH\u0007¢\u0006\u0002\b\fR\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lokhttp3/Headers$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "EMPTY", "Lokhttp3/Headers;", "headersOf", "namesAndValues", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "of", "([Ljava/lang/String;)Lokhttp3/Headers;", "-deprecated_of", "toHeaders", _UrlKt.FRAGMENT_ENCODE_SET, "headers", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "function moved to extension", replaceWith = @ReplaceWith(expression = "headers.toHeaders()", imports = {}))
        @JvmName(name = "-deprecated_of")
        /* renamed from: -deprecated_of, reason: not valid java name */
        public final Headers m3652deprecated_of(Map<String, String> r1) {
            return of(r1);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "function name changed", replaceWith = @ReplaceWith(expression = "headersOf(*namesAndValues)", imports = {}))
        @JvmName(name = "-deprecated_of")
        /* renamed from: -deprecated_of, reason: not valid java name */
        public final Headers m3653deprecated_of(String... r2) {
            return of((String[]) Arrays.copyOf(r2, r2.length));
        }

        @JvmStatic
        @JvmName(name = "of")
        public final Headers of(Map<String, String> r1) {
            return _HeadersCommonKt.commonToHeaders(r1);
        }

        @JvmStatic
        @JvmName(name = "of")
        public final Headers of(String... r1) {
            return _HeadersCommonKt.commonHeadersOf((String[]) Arrays.copyOf(r1, r1.length));
        }
    }

    static {
        Companion = new Companion(null);
        EMPTY = new Headers(new String[0]);
    }

    public Headers(String[] r1) {
        this.namesAndValues = r1;
    }

    @JvmStatic
    @JvmName(name = "of")
    public static final Headers of(Map<String, String> r1) {
        return Companion.of(r1);
    }

    @JvmStatic
    @JvmName(name = "of")
    public static final Headers of(String... r1) {
        return Companion.of(r1);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = {}))
    @JvmName(name = "-deprecated_size")
    /* renamed from: -deprecated_size, reason: not valid java name */
    public final int m3651deprecated_size() {
        return size();
    }

    public final long byteCount() {
        String[] r0 = this.namesAndValues;
        long r1 = r0.length * 2;
        int r3 = 0;
    L3:
        if (r3 >= r0.length) goto L5;
        r1 = r1 + this.namesAndValues[r3].length();
        r3 = r3 + 1;
        goto L3
    L5:
        return r1;
    }

    public boolean equals(Object r1) {
        return _HeadersCommonKt.commonEquals(this, r1);
    }

    public final String get(String r1) {
        return _HeadersCommonKt.commonHeadersGet(this.namesAndValues, r1);
    }

    public final Date getDate(String r1) {
        String r0 = get(r1);
        if (r0 != null) goto L5;
        return null;
    L5:
        return DateFormattingKt.toHttpDateOrNull(r0);
    }

    @IgnoreJRERequirement
    public final Instant getInstant(String r1) {
        Date r0 = getDate(r1);
        if (r0 != null) goto L5;
        return null;
    L5:
        return DateRetargetClass.toInstant(r0);
    }

    public final String[] getNamesAndValues$okhttp() {
        return this.namesAndValues;
    }

    public int hashCode() {
        return _HeadersCommonKt.commonHashCode(this);
    }

    @Override // java.lang.Iterable
    public Iterator<Pair<? extends String, ? extends String>> iterator() {
        return _HeadersCommonKt.commonIterator(this);
    }

    public final String name(int r1) {
        return _HeadersCommonKt.commonName(this, r1);
    }

    public final Set<String> names() {
        TreeSet r0 = new TreeSet(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
        int r1 = size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L6;
        r0.add(name(r2));
        r2 = r2 + 1;
        goto L3
    L6:
        return Collections.unmodifiableSet(r0);
    }

    public final Builder newBuilder() {
        return _HeadersCommonKt.commonNewBuilder(this);
    }

    @JvmName(name = "size")
    public final int size() {
        return this.namesAndValues.length / 2;
    }

    public final Map<String, List<String>> toMultimap() {
        TreeMap r0 = new TreeMap(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
        int r1 = size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L8;
        String r3 = name(r2).toLowerCase(Locale.US);
        List r4 = (List) r0.get(r3);
        if (r4 != null) goto L7;
        r4 = new ArrayList(2);
        r0.put(r3, r4);
    L7:
        r4.add(value(r2));
        r2 = r2 + 1;
        goto L3
    L8:
        return r0;
    }

    public String toString() {
        return _HeadersCommonKt.commonToString(this);
    }

    public final String value(int r1) {
        return _HeadersCommonKt.commonValue(this, r1);
    }

    public final List<String> values(String r1) {
        return _HeadersCommonKt.commonValues(this, r1);
    }
}
