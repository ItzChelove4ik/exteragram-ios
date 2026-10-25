package okhttp3.internal.idn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.ByteString;
import org.mvel2.asm.signature.SignatureVisitor;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0015\u001a\u00020\u0005J(\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0015\u001a\u00020\u0005J(\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J \u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\u0017H\u0002J\u001c\u0010\"\u001a\u00020\u0017*\u00020\u00052\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\rH\u0002J\"\u0010#\u001a\b\u0012\u0004\u0012\u00020\r0$*\u00020\u00052\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\rH\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u0018\u0010%\u001a\u00020\r*\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u0006("}, d2 = {"Lokhttp3/internal/idn/Punycode;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "PREFIX_STRING", _UrlKt.FRAGMENT_ENCODE_SET, "getPREFIX_STRING", "()Ljava/lang/String;", "PREFIX", "Lokio/ByteString;", "getPREFIX", "()Lokio/ByteString;", "BASE", _UrlKt.FRAGMENT_ENCODE_SET, "TMIN", "TMAX", "SKEW", "DAMP", "INITIAL_BIAS", "INITIAL_N", "encode", "string", "encodeLabel", _UrlKt.FRAGMENT_ENCODE_SET, "pos", "limit", "result", "Lokio/Buffer;", "decode", "decodeLabel", "adapt", "delta", "numpoints", "first", "requiresEncode", "codePoints", _UrlKt.FRAGMENT_ENCODE_SET, "punycodeDigit", "getPunycodeDigit", "(I)I", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPunycode.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Punycode.kt\nokhttp3/internal/idn/Punycode\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,329:1\n2393#2,14:330\n*S KotlinDebug\n*F\n+ 1 Punycode.kt\nokhttp3/internal/idn/Punycode\n*L\n108#1:330,14\n*E\n"})
/* loaded from: classes.dex */
public final class Punycode {
    private static final int BASE = 36;
    private static final int DAMP = 700;
    private static final int INITIAL_BIAS = 72;
    private static final int INITIAL_N = 128;
    public static final Punycode INSTANCE = null;
    private static final ByteString PREFIX = null;
    private static final String PREFIX_STRING = null;
    private static final int SKEW = 38;
    private static final int TMAX = 26;
    private static final int TMIN = 1;

    static {
        INSTANCE = new Punycode();
        PREFIX_STRING = "xn--";
        PREFIX = ByteString.Companion.encodeUtf8("xn--");
    }

    private Punycode() {
    }

    private final int adapt(int r1, int r2, boolean r3) {
        if (r3 == false) goto L4;
        int r12 = r1 / DAMP;
    L5:
        int r13 = r12 + (r12 / r2);
        int r0 = 0;
    L7:
        if (r13 <= 455) goto L10;
        r13 = r13 / 35;
        r0 = r0 + 36;
        goto L7
    L10:
        return r0 + ((r13 * 36) / (r13 + 38));
    L4:
        r12 = r1 / 2;
        goto L5
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [char] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v6, types: [int] */
    private final List<Integer> codePoints(String r5, int r6, int r7) {
        ArrayList r4 = new ArrayList();
    L3:
        if (r6 >= r7) goto L18;
        int r0 = r5.charAt(r6);
        if (CharsKt.isSurrogate(r0) == false) goto L17;
        int r1 = r6 + 1;
        if (r1 >= r7) goto L9;
        char r2 = r5.charAt(r1);
    L11:
        if (Character.isLowSurrogate(r0) == false) goto L13;
    L16:
        r0 = 63;
        goto L17
    L13:
        if (Character.isLowSurrogate(r2) == false) goto L16;
        r0 = 65536 + (((r0 & 1023) << 10) | (r2 & 1023));
        r6 = r1;
        goto L17
    L9:
        r2 = 0;
    L17:
        r4.add(Integer.valueOf(r0));
        r6 = r6 + 1;
        goto L3
    L18:
        return r4;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.util.ArrayList, java.util.Collection, java.util.List] */
    /* JADX WARN: Type inference failed for: r13v2, types: [int] */
    /* JADX WARN: Type inference failed for: r13v8 */
    /* JADX WARN: Type inference failed for: r13v9 */
    /* JADX WARN: Type inference failed for: r16v0, types: [boolean] */
    private final boolean decodeLabel(String r22, int r23, int r24, Buffer r25) {
        int r8 = 1;
        if (StringsKt.regionMatches(r22, r23, PREFIX_STRING, 0, 4, true) == true) goto L6;
        r25.writeUtf8(r22, r23, r24);
        return true;
    L6:
        int r9 = r23 + 4;
        ?? r10 = new ArrayList();
        int r1 = StringsKt.lastIndexOf$default(r22, SignatureVisitor.SUPER, r24, false, 4, null);
        char r4 = '0';
        char r5 = '[';
        char r6 = '{';
        boolean r13 = false;
        if (r1 < r9) goto L24;
    L8:
        if (r9 >= r1) goto L23;
        int r14 = r9 + 1;
        char r92 = r22.charAt(r9);
        if ('a' > r92) goto L13;
        if (r92 >= '{') goto L13;
    L21:
        r10.add(Integer.valueOf(r92));
        r9 = r14;
    L13:
        if ('A' > r92) goto L16;
        if (r92 < '[') goto L21;
    L16:
        if ('0' > r92) goto L20;
        if (r92 < ':') goto L21;
    L20:
        if (r92 == '-') goto L21;
        return false;
    L23:
        r9 = r9 + 1;
    L24:
        int r12 = 128;
        int r142 = 72;
        int r15 = 0;
    L25:
        if (r9 >= r24) goto L78;
        int r16 = r8;
        boolean r232 = r13;
        IntProgression r3 = RangesKt.step(RangesKt.until(36, Integer.MAX_VALUE), 36);
        int r82 = r3.getFirst();
        int r132 = r3.getLast();
        int r32 = r3.getStep();
        if (r32 <= 0) goto L29;
        if (r82 > r132) goto L29;
    L31:
        int r18 = r15;
        int r19 = r16;
    L32:
        if (r9 == r24) goto L33;
        int r20 = r9 + 1;
        char r93 = r22.charAt(r9);
        if ('a' > r93) goto L39;
        if (r93 >= r6) goto L39;
        int r94 = r93 - 'a';
    L38:
        int r42 = r19;
        int r192 = r94 * r42;
        int r62 = r18;
        if (r62 > (Integer.MAX_VALUE - r192)) goto L48;
        r18 = r62 + r192;
        if (r82 > r142) goto L53;
        int r52 = r16;
    L56:
        if (r94 < r52) goto L63;
        int r53 = 36 - r52;
        if (r42 > (Integer.MAX_VALUE / r53)) goto L59;
        r19 = r42 * r53;
        if (r82 == r132) goto L63;
        r82 = r82 + r32;
        r9 = r20;
        r4 = '0';
        r5 = '[';
        r6 = '{';
        goto L32
    L59:
        return r232;
    L63:
        r9 = r20;
    L66:
        int r33 = r18 - r15;
        int r43 = r10.size() + 1;
        if (r15 != 0) goto L70;
        boolean r63 = r16;
    L71:
        r142 = adapt(r33, r43, r63);
        int r34 = r18 / (r10.size() + 1);
        if (r12 > (Integer.MAX_VALUE - r34)) goto L73;
        r12 = r12 + r34;
        int r35 = r18 % (r10.size() + 1);
        if (r12 > 1114111) goto L76;
        r10.add(r35, Integer.valueOf(r12));
        r15 = r35 + 1;
        r13 = r232;
        r8 = r16;
        r4 = '0';
        r5 = '[';
        r6 = '{';
        goto L25
    L76:
        return r232;
    L73:
        return r232;
    L70:
        r63 = r232;
        goto L71
    L53:
        if (r82 < (r142 + 26)) goto L55;
        r52 = 26;
        goto L56
    L55:
        r52 = r82 - r142;
        goto L56
    L48:
        return r232;
    L39:
        if ('A' > r93) goto L42;
        if (r93 >= r5) goto L42;
        r94 = r93 - 'A';
    L42:
        if (r4 > r93) goto L64;
        if (r93 >= ':') goto L64;
        r94 = r93 - 22;
    L64:
        return r232;
    L33:
        return r232;
    L29:
        if (r32 >= 0) goto L65;
        if (r132 <= r82) goto L31;
    L65:
        r18 = r15;
        goto L66
    L78:
        ?? r162 = r8;
        int r0 = r10.size();
        ?? r133 = r13;
    L79:
        if (r133 >= r0) goto L81;
        r25.writeUtf8CodePoint(((Number) r10.get(r133)).intValue());
        r133 = r133 + 1;
        goto L79
    L81:
        return r162;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean encodeLabel(String r20, int r21, int r22, Buffer r23) {
        int r3 = 1;
        if (requiresEncode(r20, r21, r22) == true) goto L6;
        r23.writeUtf8(r20, r21, r22);
        return true;
    L6:
        r23.write(PREFIX);
        List<Integer> r2 = codePoints(r20, r21, r22);
        Iterator<Integer> r4 = r2.iterator();
        int r6 = 0;
    L7:
        int r8 = 128;
        if (r4.hasNext() == false) goto L12;
        int r7 = r4.next().intValue();
        if (r7 >= 128) goto L7;
        r23.writeByte(r7);
        r6 = r6 + 1;
        goto L7
    L12:
        if (r6 <= 0) goto L14;
        r23.writeByte(45);
    L14:
        int r42 = 72;
        int r9 = 0;
        int r72 = r6;
    L16:
        if (r72 >= r2.size()) goto L72;
        Iterator<T> r10 = r2.iterator();
        if (r10.hasNext() == false) goto L69;
        Object r11 = r10.next();
        if (r10.hasNext() == false) goto L34;
        int r12 = ((Number) r11).intValue();
        if (r12 >= r8) goto L26;
        r12 = Integer.MAX_VALUE;
    L26:
        Object r14 = r10.next();
        int r15 = ((Number) r14).intValue();
        if (r15 >= r8) goto L30;
        r15 = Integer.MAX_VALUE;
    L30:
        if (r12 <= r15) goto L33;
        r11 = r14;
        r12 = r15;
    L33:
        if (r10.hasNext() == true) goto L26;
    L34:
        int r102 = ((Number) r11).intValue();
        int r82 = (r102 - r8) * (r72 + 1);
        if (r9 > (Integer.MAX_VALUE - r82)) goto L36;
        int r92 = r9 + r82;
        Iterator<Integer> r83 = r2.iterator();
    L39:
        if (r83.hasNext() == false) goto L68;
        int r112 = r83.next().intValue();
        if (r112 < r102) goto L42;
        if (r112 != r102) goto L39;
        IntProgression r113 = RangesKt.step(RangesKt.until(36, Integer.MAX_VALUE), 36);
        int r122 = r113.getFirst();
        int r142 = r113.getLast();
        int r114 = r113.getStep();
        if (r114 <= 0) goto L49;
        if (r122 > r142) goto L49;
    L51:
        int r152 = r92;
    L52:
        if (r122 > r42) goto L54;
        int r16 = r3;
    L58:
        if (r152 < r3) goto L63;
        int r153 = r152 - r3;
        int r17 = 36 - r3;
        r23.writeByte(getPunycodeDigit(r3 + (r153 % r17)));
        r152 = r153 / r17;
        if (r122 == r142) goto L63;
        r122 = r122 + r114;
        r3 = r16;
    L63:
        r23.writeByte(getPunycodeDigit(r152));
        int r32 = r72 + 1;
        if (r72 != r6) goto L66;
        boolean r43 = r16;
    L67:
        r42 = adapt(r92, r32, r43);
        r72 = r32;
        r92 = 0;
        r3 = r16;
        goto L39
    L66:
        r43 = false;
        goto L67
    L54:
        r16 = r3;
        if (r122 < (r42 + 26)) goto L57;
        r3 = 26;
        goto L58
    L57:
        r3 = r122 - r42;
    L49:
        if (r114 >= 0) goto L62;
        if (r142 <= r122) goto L51;
    L62:
        r16 = r3;
        r152 = r92;
        goto L63
    L42:
        if (r92 == Integer.MAX_VALUE) goto L43;
        r92 = r92 + 1;
        goto L39
    L43:
        return false;
    L68:
        r9 = r92 + 1;
        r8 = r102 + 1;
        goto L16
    L36:
        return false;
    L69:
        Utils$$ExternalSyntheticBUOutline0.m();
        return false;
    L72:
        return r3;
    }

    private final int getPunycodeDigit(int r3) {
        if (r3 >= 26) goto L7;
        return r3 + 97;
    L7:
        if (r3 >= 36) goto L11;
        return r3 + 22;
    L11:
        throw new IllegalStateException(("unexpected digit: " + r3).toString());
    }

    private final boolean requiresEncode(String r2, int r3, int r4) {
    L2:
        if (r3 >= r4) goto L8;
        if (r2.charAt(r3) >= 128) goto L5;
        r3 = r3 + 1;
        goto L2
    L5:
        return true;
    L8:
        return false;
    }

    public final String decode(String r10) {
        int r0 = r10.length();
        Buffer r1 = new Buffer();
        int r5 = 0;
    L3:
        if (r5 >= r0) goto L14;
        String r3 = r10;
        int r102 = StringsKt.indexOf$default(r3, '.', r5, false, 4, null);
        if (r102 != (-1)) goto L8;
        r102 = r0;
    L8:
        if (decodeLabel(r3, r5, r102, r1) == false) goto L9;
        if (r102 >= r0) goto L14;
        r1.writeByte(46);
        r5 = r102 + 1;
        r10 = r3;
        goto L3
    L9:
        return null;
    L14:
        return r1.readUtf8();
    }

    public final String encode(String r10) {
        int r0 = r10.length();
        Buffer r1 = new Buffer();
        int r5 = 0;
    L3:
        if (r5 >= r0) goto L14;
        String r3 = r10;
        int r102 = StringsKt.indexOf$default(r3, '.', r5, false, 4, null);
        if (r102 != (-1)) goto L8;
        r102 = r0;
    L8:
        if (encodeLabel(r3, r5, r102, r1) == false) goto L9;
        if (r102 >= r0) goto L14;
        r1.writeByte(46);
        r5 = r102 + 1;
        r10 = r3;
        goto L3
    L9:
        return null;
    L14:
        return r1.readUtf8();
    }

    public final ByteString getPREFIX() {
        return PREFIX;
    }

    public final String getPREFIX_STRING() {
        return PREFIX_STRING;
    }
}
