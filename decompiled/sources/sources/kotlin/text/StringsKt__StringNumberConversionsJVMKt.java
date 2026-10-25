package kotlin.text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0002\u0010\n\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\f\n\u0002\b\u000e\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004\u001a\u0010\u0010\u0007\u001a\u00020\b*\u0004\u0018\u00010\u0001H\u0087\u0088\u0004\u001a\u000e\u0010\t\u001a\u00020\u0002*\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u0010\t\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004\u001a\u000e\u0010\n\u001a\u00020\u0005*\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u0010\n\u001a\u00020\u0005*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004\u001a\u000e\u0010\u000b\u001a\u00020\u0004*\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u0010\u000b\u001a\u00020\u0004*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004\u001a\u000e\u0010\f\u001a\u00020\u0006*\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u0010\f\u001a\u00020\u0006*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004\u001a\u000e\u0010\r\u001a\u00020\u000e*\u00020\u0001H\u0087\u0088\u0004\u001a\u000e\u0010\u000f\u001a\u00020\u0010*\u00020\u0001H\u0087\u0088\u0004\u001a\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u000e*\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0012\u001a\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u0010*\u00020\u0001H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0014\u001a\u000e\u0010\u0015\u001a\u00020\u0016*\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u0010\u0015\u001a\u00020\u0016*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004\u001a\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0016*\u00020\u0001H\u0087\u0080\u0004\u001a\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u0016*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0080\u0004\u001a\u000e\u0010\u0018\u001a\u00020\u0019*\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u0010\u0018\u001a\u00020\u0019*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u001bH\u0087\u0088\u0004\u001a\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0019*\u00020\u0001H\u0087\u0080\u0004\u001a\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u0019*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u001bH\u0087\u0080\u0004\u001a5\u0010\u001d\u001a\u0004\u0018\u0001H\u001e\"\u0004\b\u0000\u0010\u001e2\u0006\u0010\u001f\u001a\u00020\u00012\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\u001e0!H\u0082\u0088\u0004¢\u0006\u0004\b\"\u0010#\u001a5\u0010$\u001a\u0004\u0018\u0001H\u001e\"\u0004\b\u0000\u0010\u001e2\u0006\u0010\u001f\u001a\u00020\u00012\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\u001e0!H\u0082\u0088\u0004¢\u0006\u0004\b%\u0010#\u001a\u0017\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00020\u0001H\u0082\u0080\u0004¢\u0006\u0002\b(\u001a\u0017\u0010)\u001a\u00020\b2\u0006\u0010'\u001a\u00020\u0001H\u0082\u0080\u0004¢\u0006\u0002\b*\u001a!\u0010+\u001a\u0004\u0018\u00010\u00012\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u0004H\u0083\u0088\u0004¢\u0006\u0002\b.\u001a\u0013\u0010/\u001a\u00020\b*\u000200H\u0083\u0088\u0004¢\u0006\u0002\b1\u001a\u0013\u00102\u001a\u00020\b*\u000200H\u0083\u0088\u0004¢\u0006\u0002\b3\u001a\u0013\u00104\u001a\u00020\u0004*\u000200H\u0083\u0088\u0004¢\u0006\u0002\b5\u001a7\u00106\u001a\u00020\u0004*\u00020\u00012\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b0!H\u0083\u0088\u0004¢\u0006\u0002\b8\u001a7\u00109\u001a\u00020\u0004*\u00020\u00012\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b0!H\u0083\u0088\u0004¢\u0006\u0002\b:\u001a?\u0010;\u001a\u00020\u0004*\u00020\u00012\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\b2\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\b0!H\u0083\u0088\u0004¢\u0006\u0002\b=¨\u0006>"}, d2 = {"toString", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "radix", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "toBoolean", _UrlKt.FRAGMENT_ENCODE_SET, "toByte", "toShort", "toInt", "toLong", "toFloat", _UrlKt.FRAGMENT_ENCODE_SET, "toDouble", _UrlKt.FRAGMENT_ENCODE_SET, "toFloatOrNull", "(Ljava/lang/String;)Ljava/lang/Float;", "toDoubleOrNull", "(Ljava/lang/String;)Ljava/lang/Double;", "toBigInteger", "Ljava/math/BigInteger;", "toBigIntegerOrNull", "toBigDecimal", "Ljava/math/BigDecimal;", "mathContext", "Ljava/math/MathContext;", "toBigDecimalOrNull", "screenFloatValue", "T", "str", "parse", "Lkotlin/Function1;", "screenFloatValue$StringsKt__StringNumberConversionsJVMKt", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "screenBigDecimalValue", "screenBigDecimalValue$StringsKt__StringNumberConversionsJVMKt", "isValidFloat", "s", "isValidFloat$StringsKt__StringNumberConversionsJVMKt", "isValidBigDecimal", "isValidBigDecimal$StringsKt__StringNumberConversionsJVMKt", "guessNamedFloatConstant", "start", "endInclusive", "guessNamedFloatConstant$StringsKt__StringNumberConversionsJVMKt", "isAsciiDigit", _UrlKt.FRAGMENT_ENCODE_SET, "isAsciiDigit$StringsKt__StringNumberConversionsJVMKt", "isHexLetter", "isHexLetter$StringsKt__StringNumberConversionsJVMKt", "asciiLetterToLowerCaseCode", "asciiLetterToLowerCaseCode$StringsKt__StringNumberConversionsJVMKt", "advanceWhile", "predicate", "advanceWhile$StringsKt__StringNumberConversionsJVMKt", "backtrackWhile", "backtrackWhile$StringsKt__StringNumberConversionsJVMKt", "advanceAndValidateMantissa", "hexFormat", "advanceAndValidateMantissa$StringsKt__StringNumberConversionsJVMKt", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/text/StringsKt")
@SourceDebugExtension({"SMAP\nStringNumberConversionsJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringNumberConversionsJVM.kt\nkotlin/text/StringsKt__StringNumberConversionsJVMKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Strings.kt\nkotlin/text/StringsKt__StringsKt\n*L\n1#1,569:1\n267#1,7:570\n267#1,7:577\n278#1,8:584\n278#1,8:592\n1#2:600\n1656#3,3:601\n1656#3,3:604\n1656#3,3:607\n*S KotlinDebug\n*F\n+ 1 StringNumberConversionsJVM.kt\nkotlin/text/StringsKt__StringNumberConversionsJVMKt\n*L\n166#1:570,7\n173#1:577,7\n253#1:584,8\n264#1:592,8\n430#1:601,3\n439#1:604,3\n452#1:607,3\n*E\n"})
/* loaded from: classes.dex */
class StringsKt__StringNumberConversionsJVMKt extends StringsKt__StringBuilderKt {
    public StringsKt__StringNumberConversionsJVMKt() {
    }

    @InlineOnly
    private static final int advanceAndValidateMantissa$StringsKt__StringNumberConversionsJVMKt(String r6, int r7, int r8, boolean r9, Function1<? super Character, Boolean> r10) {
        int r0 = r7;
    L3:
        if (r0 > r8) goto L8;
        if (r10.invoke(Character.valueOf(r6.charAt(r0))).booleanValue() == false) goto L8;
        r0 = r0 + 1;
    L8:
        if (r7 == r0) goto L10;
        boolean r72 = true;
    L12:
        if (r0 <= r8) goto L17;
        if (r9 == false) goto L15;
        return -1;
    L15:
        return r0;
    L17:
        if (r6.charAt(r0) != '.') goto L27;
        int r02 = r0 + 1;
        int r4 = r02;
    L19:
        if (r4 > r8) goto L23;
        if (r10.invoke(Character.valueOf(r6.charAt(r4))).booleanValue() == false) goto L23;
        r4 = r4 + 1;
    L23:
        if (r02 == r4) goto L25;
        boolean r102 = true;
    L26:
        r0 = r4;
    L28:
        if (r72 == true) goto L46;
        if (r102 == true) goto L46;
        if (r9 == false) goto L33;
        return -1;
    L33:
        if (r8 != (r0 + 2)) goto L36;
        String r73 = "NaN";
    L39:
        if (r73 != null) goto L42;
        return -1;
    L42:
        if (StringsKt__StringsKt.indexOf(r6, r73, r0, false) == r0) goto L44;
        return -1;
    L44:
        return r8 + 1;
    L36:
        if (r8 != (r0 + 7)) goto L38;
        r73 = "Infinity";
        goto L39
    L38:
        r73 = null;
    L46:
        return r0;
    L25:
        r102 = false;
        goto L26
    L27:
        r102 = false;
        goto L28
    L10:
        r72 = false;
        goto L12
    }

    @InlineOnly
    private static final int advanceWhile$StringsKt__StringNumberConversionsJVMKt(String r1, int r2, int r3, Function1<? super Character, Boolean> r4) {
    L2:
        if (r2 > r3) goto L6;
        if (r4.invoke(Character.valueOf(r1.charAt(r2))).booleanValue() == false) goto L6;
        r2 = r2 + 1;
    L6:
        return r2;
    }

    @InlineOnly
    private static final int asciiLetterToLowerCaseCode$StringsKt__StringNumberConversionsJVMKt(char r0) {
        return r0 | ' ';
    }

    @InlineOnly
    private static final int backtrackWhile$StringsKt__StringNumberConversionsJVMKt(String r1, int r2, int r3, Function1<? super Character, Boolean> r4) {
    L2:
        if (r3 <= r2) goto L6;
        if (r4.invoke(Character.valueOf(r1.charAt(r3))).booleanValue() == false) goto L6;
        r3 = r3 - 1;
    L6:
        return r3;
    }

    @InlineOnly
    private static final String guessNamedFloatConstant$StringsKt__StringNumberConversionsJVMKt(int r1, int r2) {
        if (r2 != (r1 + 2)) goto L7;
        return "NaN";
    L7:
        if (r2 != (r1 + 7)) goto L10;
        return "Infinity";
    L10:
        return null;
    }

    @InlineOnly
    private static final boolean isAsciiDigit$StringsKt__StringNumberConversionsJVMKt(char r1) {
        if (((r1 - '0') & 65535) >= 10) goto L6;
        return true;
    L6:
        return false;
    }

    @InlineOnly
    private static final boolean isHexLetter$StringsKt__StringNumberConversionsJVMKt(char r1) {
        if ((((r1 | ' ') - 97) & 65535) >= 6) goto L6;
        return true;
    L6:
        return false;
    }

    private static final boolean isValidBigDecimal$StringsKt__StringNumberConversionsJVMKt(String r8) {
        if (r8.length() != 0) goto L6;
        return false;
    L6:
        if (r8.charAt(0) != '-') goto L8;
    L11:
        int r0 = 1;
    L12:
        int r5 = r0;
    L14:
        if (r5 >= r8.length()) goto L19;
        if (Character.isDigit(r8.charAt(r5)) == false) goto L19;
        r5 = r5 + 1;
    L19:
        if (r5 != r8.length()) goto L25;
        if ((r5 - r0) <= 0) goto L23;
        return true;
    L23:
        return false;
    L25:
        if (r8.charAt(r5) != '.') goto L38;
        r5 = r5 + 1;
        if (r5 != r8.length()) goto L33;
        if ((r5 - r0) <= 1) goto L31;
        return true;
    L31:
        return false;
    L33:
        if (r5 >= r8.length()) goto L38;
        if (Character.isDigit(r8.charAt(r5)) == false) goto L38;
        r5 = r5 + 1;
    L38:
        if (r5 != r8.length()) goto L41;
        return true;
    L41:
        if (r8.charAt(r5) != 'e') goto L43;
    L45:
        int r02 = r5 + 1;
        if (r02 != r8.length()) goto L49;
        return false;
    L49:
        if (r8.charAt(r02) != '+') goto L51;
    L52:
        r02 = r5 + 2;
    L54:
        if (r02 != r8.length()) goto L57;
        return false;
    L57:
        if (r02 >= r8.length()) goto L62;
        if (Character.isDigit(r8.charAt(r02)) == false) goto L62;
        r02 = r02 + 1;
    L62:
        if (r02 != r8.length()) goto L64;
        return true;
    L64:
        return false;
    L51:
        if (r8.charAt(r02) != '-') goto L54;
    L43:
        if (r8.charAt(r5) == 'E') goto L45;
        return false;
    L8:
        if (r8.charAt(0) == '+') goto L11;
        r0 = 0;
        goto L12
    }

    private static final boolean isValidFloat$StringsKt__StringNumberConversionsJVMKt(String r19) {
        boolean r2 = true;
        int r1 = r19.length() - 1;
        int r4 = 0;
    L3:
        char r5 = ' ';
        if (r4 > r1) goto L8;
        if (r19.charAt(r4) > ' ') goto L8;
        r4 = r4 + 1;
    L8:
        if (r4 <= r1) goto L10;
        return false;
    L10:
        if (r1 <= r4) goto L15;
        if (r19.charAt(r1) > ' ') goto L15;
        r1 = r1 - 1;
    L15:
        if (r19.charAt(r4) != '+') goto L17;
    L18:
        r4 = r4 + 1;
    L19:
        if (r4 <= r1) goto L22;
        return false;
    L22:
        if (r19.charAt(r4) != '0') goto L69;
        int r6 = r4 + 1;
        if (r6 <= r1) goto L27;
        return true;
    L27:
        if ((r19.charAt(r6) | ' ') != 120) goto L69;
        int r42 = r4 + 2;
        int r62 = r42;
    L30:
        if (r62 > r1) goto L37;
        boolean r17 = r2;
        if (((r19.charAt(r62) - '0') & 65535) < 10) goto L36;
        if ((((r15 | ' ') - 97) & 65535) < 6) goto L36;
    L38:
        if (r42 == r62) goto L40;
        boolean r22 = r17;
    L41:
        if (r62 <= r1) goto L45;
    L43:
        r4 = -1;
    L64:
        if (r4 == (-1)) goto L68;
        if (r4 > r1) goto L68;
        boolean r23 = r17;
    L70:
        if (r23 == true) goto L111;
        int r52 = r4;
    L72:
        if (r52 > r1) goto L76;
        if (((r19.charAt(r52) - '0') & 65535) >= 10) goto L76;
        r52 = r52 + 1;
    L76:
        if (r4 == r52) goto L78;
        boolean r43 = r17;
    L79:
        if (r52 <= r1) goto L82;
        r4 = r52;
    L107:
        if (r4 != (-1)) goto L109;
        return false;
    L109:
        if (r4 <= r1) goto L111;
        return r17;
    L82:
        if (r19.charAt(r52) != '.') goto L90;
        int r53 = r52 + 1;
        int r63 = r53;
    L84:
        if (r63 > r1) goto L88;
        if (((r19.charAt(r63) - '0') & 65535) >= 10) goto L88;
        r63 = r63 + 1;
    L88:
        if (r53 == r63) goto L91;
        boolean r54 = r17;
    L92:
        if (r43 == true) goto L106;
        if (r54 == true) goto L106;
        if (r1 != (r63 + 2)) goto L98;
        String r44 = "NaN";
    L101:
        if (r44 != null) goto L104;
    L102:
        r4 = -1;
        goto L107
    L104:
        if (StringsKt__StringsKt.indexOf(r19, r44, r63, false) != r63) goto L102;
        r4 = r1 + 1;
        goto L107
    L98:
        if (r1 != (r63 + 7)) goto L100;
        r44 = "Infinity";
        goto L101
    L100:
        r44 = null;
    L106:
        r4 = r63;
    L91:
        r54 = false;
        goto L92
    L90:
        r63 = r52;
        goto L91
    L78:
        r43 = false;
    L111:
        int r55 = r4 + 1;
        int r64 = r19.charAt(r4) | ' ';
        if (r23 == false) goto L114;
        int r9 = 112;
    L116:
        if (r64 == r9) goto L123;
        if (r23 == true) goto L122;
        if (r64 == 102) goto L120;
        if (r64 != 100) goto L122;
    L120:
        if (r55 <= r1) goto L122;
        return r17;
    L122:
        return false;
    L123:
        if (r55 <= r1) goto L126;
        return false;
    L126:
        if (r19.charAt(r55) != '+') goto L128;
    L129:
        r55 = r4 + 2;
        if (r55 <= r1) goto L132;
        return false;
    L132:
        if (r55 > r1) goto L136;
        if (((r19.charAt(r55) - '0') & 65535) >= 10) goto L136;
        r55 = r55 + 1;
    L136:
        if (r55 <= r1) goto L138;
        return r17;
    L138:
        if (r55 != r1) goto L145;
        int r0 = r19.charAt(r55) | ' ';
        if (r0 == 102) goto L144;
        if (r0 == 100) goto L144;
        return false;
    L144:
        return r17;
    L145:
        return false;
    L128:
        if (r19.charAt(r55) != '-') goto L132;
    L114:
        r9 = 101;
    L68:
        return false;
    L45:
        if (r19.charAt(r62) != '.') goto L59;
        int r65 = r62 + 1;
        int r45 = r65;
    L47:
        if (r45 > r1) goto L55;
        char r18 = r5;
        if (((r19.charAt(r45) - '0') & 65535) < 10) goto L53;
        if ((((r15 | ' ') - 97) & 65535) >= 6) goto L55;
    L53:
        r45 = r45 + 1;
        r5 = r18;
    L55:
        if (r65 == r45) goto L57;
        boolean r56 = r17;
    L58:
        r62 = r45;
    L60:
        if (r22 == true) goto L63;
        if (r56 == false) goto L43;
    L63:
        r4 = r62;
        goto L64
    L57:
        r56 = false;
        goto L58
    L59:
        r56 = false;
        goto L60
    L40:
        r22 = false;
    L36:
        r62 = r62 + 1;
        r2 = r17;
        goto L30
    L37:
        r17 = r2;
    L69:
        r17 = true;
        r23 = false;
        goto L70
    L17:
        if (r19.charAt(r4) != '-') goto L19;
        goto L18
    }

    private static final <T> T screenBigDecimalValue$StringsKt__StringNumberConversionsJVMKt(String r2, Function1<? super String, ? extends T> r3) {
        if (isValidBigDecimal$StringsKt__StringNumberConversionsJVMKt(r2) == false) goto L7;
        return r3.invoke(r2);
    L7:
        return null;
    }

    private static final <T> T screenFloatValue$StringsKt__StringNumberConversionsJVMKt(String r2, Function1<? super String, ? extends T> r3) {
        if (isValidFloat$StringsKt__StringNumberConversionsJVMKt(r2) == false) goto L7;
        return r3.invoke(r2);
    L7:
        return null;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(String r1) {
        return new BigDecimal(r1);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(String r1, MathContext r2) {
        return new BigDecimal(r1, r2);
    }

    @SinceKotlin(version = "1.2")
    public static final BigDecimal toBigDecimalOrNull(String r2) {
        if (isValidBigDecimal$StringsKt__StringNumberConversionsJVMKt(r2) == false) goto L7;
        return new BigDecimal(r2);
    L7:
        return null;
    }

    @SinceKotlin(version = "1.2")
    public static final BigDecimal toBigDecimalOrNull(String r2, MathContext r3) {
        if (isValidBigDecimal$StringsKt__StringNumberConversionsJVMKt(r2) == false) goto L7;
        return new BigDecimal(r2, r3);
    L7:
        return null;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(String r1) {
        return new BigInteger(r1);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(String r1, int r2) {
        return new BigInteger(r1, CharsKt__CharJVMKt.checkRadix(r2));
    }

    @SinceKotlin(version = "1.2")
    public static final BigInteger toBigIntegerOrNull(String r1) {
        return toBigIntegerOrNull(r1, 10);
    }

    @SinceKotlin(version = "1.2")
    public static final BigInteger toBigIntegerOrNull(String r6, int r7) {
        CharsKt__CharJVMKt.checkRadix(r7);
        int r0 = r6.length();
        if (r0 == 0) goto L19;
        int r2 = 0;
        if (r0 == 1) goto L15;
        if (r6.charAt(0) != '-') goto L9;
        r2 = 1;
    L9:
        if (r2 >= r0) goto L18;
        if (CharsKt__CharJVMKt.digitOf(r6.charAt(r2), r7) < 0) goto L12;
        r2 = r2 + 1;
        goto L9
    L12:
        return null;
    L18:
        return new BigInteger(r6, CharsKt__CharJVMKt.checkRadix(r7));
    L15:
        if (CharsKt__CharJVMKt.digitOf(r6.charAt(0), r7) >= 0) goto L18;
        return null;
    L19:
        return null;
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final boolean toBoolean(String r0) {
        return Boolean.parseBoolean(r0);
    }

    @InlineOnly
    private static final byte toByte(String r0) {
        return Byte.parseByte(r0);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte toByte(String r0, int r1) {
        return Byte.parseByte(r0, CharsKt__CharJVMKt.checkRadix(r1));
    }

    @InlineOnly
    private static final double toDouble(String r2) {
        return Double.parseDouble(r2);
    }

    @SinceKotlin(version = "1.1")
    public static Double toDoubleOrNull(String r3) {
        if (isValidFloat$StringsKt__StringNumberConversionsJVMKt(r3) == false) goto L7;
        return Double.valueOf(Double.parseDouble(r3));
    L7:
        return null;
    }

    @InlineOnly
    private static final float toFloat(String r0) {
        return Float.parseFloat(r0);
    }

    @SinceKotlin(version = "1.1")
    public static final Float toFloatOrNull(String r2) {
        if (isValidFloat$StringsKt__StringNumberConversionsJVMKt(r2) == false) goto L7;
        return Float.valueOf(Float.parseFloat(r2));
    L7:
        return null;
    }

    @InlineOnly
    private static final int toInt(String r0) {
        return Integer.parseInt(r0);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int toInt(String r0, int r1) {
        return Integer.parseInt(r0, CharsKt__CharJVMKt.checkRadix(r1));
    }

    @InlineOnly
    private static final long toLong(String r2) {
        return Long.parseLong(r2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final long toLong(String r0, int r1) {
        return Long.parseLong(r0, CharsKt__CharJVMKt.checkRadix(r1));
    }

    @InlineOnly
    private static final short toShort(String r0) {
        return Short.parseShort(r0);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short toShort(String r0, int r1) {
        return Short.parseShort(r0, CharsKt__CharJVMKt.checkRadix(r1));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(byte r0, int r1) {
        return Integer.toString(r0, CharsKt__CharJVMKt.checkRadix(r1));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(int r0, int r1) {
        return Integer.toString(r0, CharsKt__CharJVMKt.checkRadix(r1));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(long r0, int r2) {
        return Long.toString(r0, CharsKt__CharJVMKt.checkRadix(r2));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(short r0, int r1) {
        return Integer.toString(r0, CharsKt__CharJVMKt.checkRadix(r1));
    }
}
