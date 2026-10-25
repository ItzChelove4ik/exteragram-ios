package okhttp3.internal;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0010$\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0001H\u0000\u001a\u001e\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u000b0\n*\u00020\u0002H\u0000\u001a\f\u0010\f\u001a\u00020\r*\u00020\u0002H\u0000\u001a\u0016\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0000\u001a\f\u0010\u0012\u001a\u00020\u0004*\u00020\u0002H\u0000\u001a\f\u0010\u0013\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a%\u0010\u0014\u001a\u0004\u0018\u00010\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u00162\u0006\u0010\b\u001a\u00020\u0001H\u0000¢\u0006\u0002\u0010\u0017\u001a\u001c\u0010\u0018\u001a\u00020\r*\u00020\r2\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u001a\u001a\u00020\r*\u00020\r2\u0006\u0010\u001b\u001a\u00020\u0002H\u0000\u001a\u001c\u0010\u001c\u001a\u00020\r*\u00020\r2\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u001d\u001a\u00020\r*\u00020\r2\u0006\u0010\b\u001a\u00020\u0001H\u0000\u001a\u001c\u0010\u001e\u001a\u00020\r*\u00020\r2\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0001H\u0000\u001a\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u0001*\u00020\r2\u0006\u0010\b\u001a\u00020\u0001H\u0000\u001a\f\u0010 \u001a\u00020\u0002*\u00020\rH\u0000\u001a\u0010\u0010!\u001a\u00020\"2\u0006\u0010\b\u001a\u00020\u0001H\u0000\u001a\u0018\u0010#\u001a\u00020\"2\u0006\u0010\u0019\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0001H\u0000\u001a\f\u0010$\u001a\u00020\u0001*\u00020%H\u0002\u001a!\u0010&\u001a\u00020\u00022\u0012\u0010'\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0016\"\u00020\u0001H\u0000¢\u0006\u0002\u0010(\u001a\u0018\u0010)\u001a\u00020\u0002*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010*H\u0000¨\u0006+"}, d2 = {"commonName", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/Headers;", "index", _UrlKt.FRAGMENT_ENCODE_SET, "commonValue", "commonValues", _UrlKt.FRAGMENT_ENCODE_SET, "name", "commonIterator", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Pair;", "commonNewBuilder", "Lokhttp3/Headers$Builder;", "commonEquals", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "commonHashCode", "commonToString", "commonHeadersGet", "namesAndValues", _UrlKt.FRAGMENT_ENCODE_SET, "([Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "commonAdd", "value", "commonAddAll", "headers", "commonAddLenient", "commonRemoveAll", "commonSet", "commonGet", "commonBuild", "headersCheckName", _UrlKt.FRAGMENT_ENCODE_SET, "headersCheckValue", "charCode", _UrlKt.FRAGMENT_ENCODE_SET, "commonHeadersOf", "inputNamesAndValues", "([Ljava/lang/String;)Lokhttp3/Headers;", "commonToHeaders", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\n-HeadersCommon.kt\nKotlin\n*S Kotlin\n*F\n+ 1 -HeadersCommon.kt\nokhttp3/internal/_HeadersCommonKt\n+ 2 -UtilJvm.kt\nokhttp3/internal/_UtilJvmKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,207:1\n242#2:208\n37#3,2:209\n1#4:211\n*S KotlinDebug\n*F\n+ 1 -HeadersCommon.kt\nokhttp3/internal/_HeadersCommonKt\n*L\n36#1:208\n136#1:209,2\n*E\n"})
/* loaded from: classes.dex */
public final class _HeadersCommonKt {
    private static final String charCode(char r2) {
        String r22 = Integer.toString(r2, CharsKt.checkRadix(16));
        if (r22.length() < 2) goto L5;
        return r22;
    L5:
        return MVEL.VERSION_SUB.concat(r22);
    }

    public static final Headers.Builder commonAdd(Headers.Builder r0, String r1, String r2) {
        headersCheckName(r1);
        headersCheckValue(r2, r1);
        commonAddLenient(r0, r1, r2);
        return r0;
    }

    public static final Headers.Builder commonAddAll(Headers.Builder r4, Headers r5) {
        int r0 = r5.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        commonAddLenient(r4, r5.name(r1), r5.value(r1));
        r1 = r1 + 1;
        goto L3
    L5:
        return r4;
    }

    public static final Headers.Builder commonAddLenient(Headers.Builder r1, String r2, String r3) {
        r1.getNamesAndValues$okhttp().add(r2);
        r1.getNamesAndValues$okhttp().add(StringsKt.trim(r3).toString());
        return r1;
    }

    public static final Headers commonBuild(Headers.Builder r2) {
        return new Headers((String[]) r2.getNamesAndValues$okhttp().toArray(new String[0]));
    }

    public static final boolean commonEquals(Headers r1, Object r2) {
        if ((r2 instanceof Headers) == true) goto L5;
        return false;
    L5:
        if (Arrays.equals(r1.getNamesAndValues$okhttp(), ((Headers) r2).getNamesAndValues$okhttp()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static final String commonGet(Headers.Builder r4, String r5) {
        int r0 = r4.getNamesAndValues$okhttp().size() - 2;
        int r1 = ProgressionUtilKt.getProgressionLastElement(r0, 0, -2);
        if (r1 <= r0) goto L5;
        return null;
    L5:
        if (StringsKt.equals(r5, r4.getNamesAndValues$okhttp().get(r0), true) == true) goto L7;
        if (r0 == r1) goto L14;
        r0 = r0 - 2;
        goto L5
    L14:
        return null;
    L7:
        return r4.getNamesAndValues$okhttp().get(r0 + 1);
    }

    public static final int commonHashCode(Headers r0) {
        return Arrays.hashCode(r0.getNamesAndValues$okhttp());
    }

    public static final String commonHeadersGet(String[] r4, String r5) {
        int r0 = r4.length - 2;
        int r1 = ProgressionUtilKt.getProgressionLastElement(r0, 0, -2);
        if (r1 <= r0) goto L5;
        return null;
    L5:
        if (StringsKt.equals(r5, r4[r0], true) == true) goto L7;
        if (r0 == r1) goto L14;
        r0 = r0 - 2;
        goto L5
    L14:
        return null;
    L7:
        return r4[r0 + 1];
    }

    public static final Headers commonHeadersOf(String... r7) {
        if ((r7.length % 2) != 0) goto L18;
        String[] r0 = (String[]) Arrays.copyOf(r7, r7.length);
        int r3 = r0.length;
        int r4 = 0;
        int r5 = 0;
    L5:
        if (r5 >= r3) goto L11;
        if (r0[r5] == null) goto L9;
        r0[r5] = StringsKt.trim(r7[r5]).toString();
        r5 = r5 + 1;
        goto L5
    L9:
        f$$ExternalSyntheticBUOutline1.m("Headers cannot be null");
        return null;
    L11:
        int r72 = ProgressionUtilKt.getProgressionLastElement(0, r0.length - 1, 2);
        if (r72 < 0) goto L17;
    L13:
        String r1 = r0[r4];
        String r2 = r0[r4 + 1];
        headersCheckName(r1);
        headersCheckValue(r2, r1);
        if (r4 == r72) goto L17;
        r4 = r4 + 2;
    L17:
        return new Headers(r0);
    L18:
        f$$ExternalSyntheticBUOutline1.m("Expected alternating header names and values");
        return null;
    }

    public static final Iterator<Pair<String, String>> commonIterator(Headers r5) {
        int r0 = r5.size();
        Pair[] r1 = new Pair[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L6;
        r1[r2] = TuplesKt.to(r5.name(r2), r5.value(r2));
        r2 = r2 + 1;
        goto L3
    L6:
        return ArrayIteratorKt.iterator(r1);
    }

    public static final String commonName(Headers r2, int r3) {
        String r22 = (String) ArraysKt.getOrNull(r2.getNamesAndValues$okhttp(), r3 * 2);
        if (r22 == null) goto L6;
        return r22;
    L6:
        throw new IndexOutOfBoundsException("name[" + r3 + ']');
    }

    public static final Headers.Builder commonNewBuilder(Headers r2) {
        Headers.Builder r0 = new Headers.Builder();
        CollectionsKt.addAll(r0.getNamesAndValues$okhttp(), r2.getNamesAndValues$okhttp());
        return r0;
    }

    public static final Headers.Builder commonRemoveAll(Headers.Builder r3, String r4) {
        int r0 = 0;
    L4:
        if (r0 >= r3.getNamesAndValues$okhttp().size()) goto L9;
        if (StringsKt.equals(r4, r3.getNamesAndValues$okhttp().get(r0), true) == false) goto L8;
        r3.getNamesAndValues$okhttp().remove(r0);
        r3.getNamesAndValues$okhttp().remove(r0);
        r0 = r0 - 2;
    L8:
        r0 = r0 + 2;
        goto L4
    L9:
        return r3;
    }

    public static final Headers.Builder commonSet(Headers.Builder r0, String r1, String r2) {
        headersCheckName(r1);
        headersCheckValue(r2, r1);
        r0.removeAll(r1);
        commonAddLenient(r0, r1, r2);
        return r0;
    }

    public static final Headers commonToHeaders(Map<String, String> r4) {
        String[] r0 = new String[r4.size() * 2];
        Iterator<Map.Entry<String, String>> r42 = r4.entrySet().iterator();
        int r1 = 0;
    L4:
        if (r42.hasNext() == false) goto L7;
        Map.Entry<String, String> r2 = r42.next();
        String r3 = r2.getKey();
        String r22 = r2.getValue();
        String r32 = StringsKt.trim(r3).toString();
        String r23 = StringsKt.trim(r22).toString();
        headersCheckName(r32);
        headersCheckValue(r23, r32);
        r0[r1] = r32;
        r0[r1 + 1] = r23;
        r1 = r1 + 2;
        goto L4
    L7:
        return new Headers(r0);
    }

    public static final String commonToString(Headers r6) {
        StringBuilder r0 = new StringBuilder();
        int r1 = r6.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L9;
        String r3 = r6.name(r2);
        String r4 = r6.value(r2);
        r0.append(r3);
        r0.append(": ");
        if (_UtilCommonKt.isSensitiveHeader(r3) == false) goto L7;
        r4 = "██";
    L7:
        r0.append(r4);
        r0.append("\n");
        r2 = r2 + 1;
        goto L3
    L9:
        return r0.toString();
    }

    public static final String commonValue(Headers r2, int r3) {
        String r22 = (String) ArraysKt.getOrNull(r2.getNamesAndValues$okhttp(), (r3 * 2) + 1);
        if (r22 == null) goto L6;
        return r22;
    L6:
        throw new IndexOutOfBoundsException("value[" + r3 + ']');
    }

    public static final List<String> commonValues(Headers r6, String r7) {
        int r0 = r6.size();
        List<String> r1 = null;
        int r2 = 0;
        ArrayList r3 = null;
    L3:
        if (r2 >= r0) goto L10;
        if (StringsKt.equals(r7, r6.name(r2), true) == false) goto L9;
        if (r3 != null) goto L8;
        r3 = new ArrayList(2);
    L8:
        r3.add(r6.value(r2));
    L9:
        r2 = r2 + 1;
        goto L3
    L10:
        if (r3 == null) goto L12;
        r1 = Collections.unmodifiableList(r3);
    L12:
        if (r1 == null) goto L14;
        return r1;
    L14:
        return CollectionsKt.emptyList();
    }

    public static final void headersCheckName(String r4) {
        if (r4.length() <= 0) goto L14;
        int r0 = r4.length();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L13;
        char r2 = r4.charAt(r1);
        if ('!' > r2) goto L12;
        if (r2 >= 127) goto L12;
        r1 = r1 + 1;
    L12:
        throw new IllegalArgumentException(("Unexpected char 0x" + charCode(r2) + " at " + r1 + " in header name: " + r4).toString());
    L13:
        return;
    L14:
        f$$ExternalSyntheticBUOutline1.m("name is empty");
    }

    public static final void headersCheckValue(String r4, String r5) {
        int r0 = r4.length();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L18;
        char r2 = r4.charAt(r1);
        if (r2 == '\t') goto L17;
        if (' ' > r2) goto L11;
        if (r2 < 127) goto L17;
    L11:
        StringBuilder r02 = new StringBuilder("Unexpected char 0x");
        r02.append(charCode(r2));
        r02.append(" at ");
        r02.append(r1);
        r02.append(" in ");
        r02.append(r5);
        r02.append(" value");
        if (_UtilCommonKt.isSensitiveHeader(r5) == false) goto L14;
        String r42 = _UrlKt.FRAGMENT_ENCODE_SET;
    L15:
        r02.append(r42);
        throw new IllegalArgumentException(r02.toString().toString());
    L14:
        r42 = ": ".concat(r4);
    L17:
        r1 = r1 + 1;
        goto L3
    }
}
