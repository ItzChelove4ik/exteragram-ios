package okhttp3.internal;

import kotlin.Metadata;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0000\u001a\f\u0010\u0006\u001a\u00020\u0002*\u00020\u0007H\u0000\u001a\f\u0010\b\u001a\u00020\u0002*\u00020\u0007H\u0000\u001a\f\u0010\t\u001a\u00020\u0002*\u00020\nH\u0000\u001a\f\u0010\u000b\u001a\u00020\n*\u00020\nH\u0000\u001a\f\u0010\f\u001a\u00020\n*\u00020\nH\u0000\u001a\f\u0010\r\u001a\u00020\n*\u00020\nH\u0000\u001a\f\u0010\u000e\u001a\u00020\n*\u00020\nH\u0000\u001a\f\u0010\u000f\u001a\u00020\n*\u00020\nH\u0000\u001a\u0014\u0010\u0010\u001a\u00020\u0002*\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012H\u0000\u001a\u001e\u0010\u0013\u001a\u00020\u0004*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00012\b\b\u0002\u0010\u0015\u001a\u00020\u0004H\u0002¨\u0006\u0016"}, d2 = {"commonToString", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/CacheControl;", "commonClampToInt", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "commonForceNetwork", "Lokhttp3/CacheControl$Companion;", "commonForceCache", "commonBuild", "Lokhttp3/CacheControl$Builder;", "commonNoCache", "commonNoStore", "commonOnlyIfCached", "commonNoTransform", "commonImmutable", "commonParse", "headers", "Lokhttp3/Headers;", "indexOfElement", "characters", "startIndex", "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class _CacheControlCommonKt {
    public static final CacheControl commonBuild(CacheControl.Builder r14) {
        return new CacheControl(r14.getNoCache$okhttp(), r14.getNoStore$okhttp(), r14.getMaxAgeSeconds$okhttp(), -1, false, false, false, r14.getMaxStaleSeconds$okhttp(), r14.getMinFreshSeconds$okhttp(), r14.getOnlyIfCached$okhttp(), r14.getNoTransform$okhttp(), r14.getImmutable$okhttp(), null);
    }

    public static final int commonClampToInt(long r2) {
        if (r2 <= 2147483647L) goto L7;
        return Integer.MAX_VALUE;
    L7:
        return (int) r2;
    }

    public static final CacheControl commonForceCache(CacheControl.Companion r2) {
        CacheControl.Builder r22 = new CacheControl.Builder().onlyIfCached();
        Duration.Companion r0 = Duration.Companion;
        return r22.m3622maxStaleLRDsOJo(DurationKt.toDuration(Integer.MAX_VALUE, DurationUnit.SECONDS)).build();
    }

    public static final CacheControl commonForceNetwork(CacheControl.Companion r0) {
        return new CacheControl.Builder().noCache().build();
    }

    public static final CacheControl.Builder commonImmutable(CacheControl.Builder r1) {
        r1.setImmutable$okhttp(true);
        return r1;
    }

    public static final CacheControl.Builder commonNoCache(CacheControl.Builder r1) {
        r1.setNoCache$okhttp(true);
        return r1;
    }

    public static final CacheControl.Builder commonNoStore(CacheControl.Builder r1) {
        r1.setNoStore$okhttp(true);
        return r1;
    }

    public static final CacheControl.Builder commonNoTransform(CacheControl.Builder r1) {
        r1.setNoTransform$okhttp(true);
        return r1;
    }

    public static final CacheControl.Builder commonOnlyIfCached(CacheControl.Builder r1) {
        r1.setOnlyIfCached$okhttp(true);
        return r1;
    }

    public static final CacheControl commonParse(CacheControl.Companion r29, Headers r30) {
        Headers r0 = r30;
        int r1 = r0.size();
        boolean r4 = true;
        boolean r7 = true;
        int r6 = 0;
        String r8 = null;
        boolean r9 = false;
        boolean r10 = false;
        int r11 = -1;
        int r12 = -1;
        boolean r13 = false;
        boolean r14 = false;
        boolean r15 = false;
        int r16 = -1;
        int r17 = -1;
        boolean r18 = false;
        boolean r19 = false;
        boolean r20 = false;
    L3:
        if (r6 >= r1) goto L68;
        String r2 = r0.name(r6);
        String r5 = r0.value(r6);
        if (StringsKt.equals(r2, "Cache-Control", r4) == false) goto L10;
        if (r8 == null) goto L8;
    L7:
        r7 = false;
    L12:
        int r22 = 0;
    L14:
        if (r22 >= r5.length()) goto L67;
        int r3 = indexOfElement(r5, "=,;", r22);
        String r23 = StringsKt.trim(r5.substring(r22, r3)).toString();
        boolean r27 = r4;
        if (r3 != r5.length()) goto L18;
    L21:
        String r42 = r5;
        int r32 = r3 + 1;
        String r02 = null;
    L29:
        int r21 = r1;
        if (StringsKt.equals("no-cache", r23, r27) == false) goto L34;
        r9 = r27;
        r22 = r32;
        r5 = r42;
        r4 = r9;
    L32:
        r1 = r21;
        goto L14
    L34:
        if (StringsKt.equals("no-store", r23, r27) == false) goto L37;
        r10 = r27;
        r22 = r32;
        r5 = r42;
        r4 = r10;
        goto L32
    L37:
        if (StringsKt.equals("max-age", r23, r27) == false) goto L41;
        r11 = _UtilCommonKt.toNonNegativeInt(r02, -1);
    L39:
        r22 = r32;
        r5 = r42;
        r4 = r27;
        goto L32
    L41:
        if (StringsKt.equals("s-maxage", r23, r27) == false) goto L44;
        r12 = _UtilCommonKt.toNonNegativeInt(r02, -1);
        goto L39
    L44:
        if (StringsKt.equals("private", r23, r27) == false) goto L47;
        r13 = r27;
        r22 = r32;
        r5 = r42;
        r4 = r13;
        goto L32
    L47:
        if (StringsKt.equals("public", r23, r27) == false) goto L50;
        r14 = r27;
        r22 = r32;
        r5 = r42;
        r4 = r14;
        goto L32
    L50:
        if (StringsKt.equals("must-revalidate", r23, r27) == false) goto L53;
        r15 = r27;
        r22 = r32;
        r5 = r42;
        r4 = r15;
        goto L32
    L53:
        if (StringsKt.equals("max-stale", r23, r27) == false) goto L56;
        r16 = _UtilCommonKt.toNonNegativeInt(r02, Integer.MAX_VALUE);
        goto L39
    L56:
        if (StringsKt.equals("min-fresh", r23, r27) == false) goto L59;
        r17 = _UtilCommonKt.toNonNegativeInt(r02, -1);
        goto L39
    L59:
        if (StringsKt.equals("only-if-cached", r23, r27) == false) goto L62;
        r18 = r27;
        r22 = r32;
        r5 = r42;
        r4 = r18;
        goto L32
    L62:
        if (StringsKt.equals("no-transform", r23, r27) == false) goto L65;
        r19 = r27;
        r22 = r32;
        r5 = r42;
        r4 = r19;
        goto L32
    L65:
        if (StringsKt.equals("immutable", r23, r27) == false) goto L39;
        r20 = r27;
        r22 = r32;
        r5 = r42;
        r4 = r20;
        goto L32
    L18:
        if (r5.charAt(r3) == ',') goto L21;
        if (r5.charAt(r3) == ';') goto L21;
        int r03 = _UtilCommonKt.indexOfNonWhitespace(r5, r3 + 1);
        if (r03 < r5.length()) goto L25;
    L27:
        r42 = r5;
        r32 = indexOfElement(r42, ",;", r03);
        r02 = StringsKt.trim(r42.substring(r03, r32)).toString();
        goto L29
    L25:
        if (r5.charAt(r03) != '\"') goto L27;
        int r232 = r03 + 1;
        String r212 = r5;
        int r04 = StringsKt.indexOf$default(r212, Typography.quote, r232, false, 4, null);
        r42 = r212;
        r32 = r04 + 1;
        r02 = r42.substring(r232, r04);
    L67:
        r6 = r6 + 1;
        r0 = r30;
        r4 = r4;
        r1 = r1;
        goto L3
    L8:
        r8 = r5;
        goto L12
    L10:
        if (StringsKt.equals(r2, "Pragma", r4) == false) goto L67;
    L68:
        if (r7 == true) goto L70;
        String r213 = null;
    L72:
        return new CacheControl(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r213);
    L70:
        r213 = r8;
        goto L72
    }

    public static final String commonToString(CacheControl r4) {
        String r0 = r4.getHeaderValue$okhttp();
        if (r0 != null) goto L46;
        StringBuilder r02 = new StringBuilder();
        if (r4.noCache() == false) goto L8;
        r02.append("no-cache, ");
    L8:
        if (r4.noStore() == false) goto L11;
        r02.append("no-store, ");
    L11:
        if (r4.maxAgeSeconds() == (-1)) goto L14;
        r02.append("max-age=");
        r02.append(r4.maxAgeSeconds());
        r02.append(", ");
    L14:
        if (r4.sMaxAgeSeconds() == (-1)) goto L17;
        r02.append("s-maxage=");
        r02.append(r4.sMaxAgeSeconds());
        r02.append(", ");
    L17:
        if (r4.isPrivate() == false) goto L20;
        r02.append("private, ");
    L20:
        if (r4.isPublic() == false) goto L23;
        r02.append("public, ");
    L23:
        if (r4.mustRevalidate() == false) goto L26;
        r02.append("must-revalidate, ");
    L26:
        if (r4.maxStaleSeconds() == (-1)) goto L29;
        r02.append("max-stale=");
        r02.append(r4.maxStaleSeconds());
        r02.append(", ");
    L29:
        if (r4.minFreshSeconds() == (-1)) goto L32;
        r02.append("min-fresh=");
        r02.append(r4.minFreshSeconds());
        r02.append(", ");
    L32:
        if (r4.onlyIfCached() == false) goto L35;
        r02.append("only-if-cached, ");
    L35:
        if (r4.noTransform() == false) goto L38;
        r02.append("no-transform, ");
    L38:
        if (r4.immutable() == false) goto L41;
        r02.append("immutable, ");
    L41:
        if (r02.length() != 0) goto L44;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L44:
        r02.delete(r02.length() - 2, r02.length());
        String r03 = r02.toString();
        r4.setHeaderValue$okhttp(r03);
        return r03;
    L46:
        return r0;
    }

    private static final int indexOfElement(String r5, String r6, int r7) {
        int r0 = r5.length();
    L3:
        if (r7 >= r0) goto L9;
        if (StringsKt.contains$default(r6, r5.charAt(r7), false, 2, null) == true) goto L6;
        r7 = r7 + 1;
        goto L3
    L6:
        return r7;
    L9:
        return r5.length();
    }

    public static /* synthetic */ int indexOfElement$default(String r0, String r1, int r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = 0;
    L6:
        return indexOfElement(r0, r1, r2);
    }
}
