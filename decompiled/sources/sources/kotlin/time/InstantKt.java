package kotlin.time;

import com.android.dx.AppDataDirGuesser;
import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Typography;
import kotlin.time.InstantParseResult;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.mvel2.asm.signature.SignatureVisitor;

@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\f\n\u0002\u0010\u0015\n\u0002\b\u0006\u001a\u0012\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0082\u0080\u0004\u001a\u0012\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u0082\u0080\u0004\u001a(\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0082\u0088\u0004\u001a(\u0010\u001d\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0082\u0088\u0004\u001a\u0012\u0010%\u001a\u00020\u00012\u0006\u0010&\u001a\u00020\u0015H\u0080\u0080\u0004\u001a\u0016\u0010'\u001a\u00020\u0015*\u00020\u00152\u0006\u0010%\u001a\u00020\u0001H\u0082\u0080\u0004\u001a\u0016\u0010-\u001a\u00020\u0012*\u00020\u00102\u0006\u0010.\u001a\u00020\u0015H\u0082\u0080\u0004\" \u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0000\u0010\u0005\" \u0010\u0006\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b\u0007\u0010\u0004\u001a\u0004\b\u0006\u0010\u0005\"\u000f\u0010\b\u001a\u00020\tX\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\n\u001a\u00020\tX\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u000b\u001a\u00020\tX\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\f\u001a\u00020\tX\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u0014\u001a\u00020\u0015X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u0016\u001a\u00020\u0015X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u001e\u001a\u00020\u0015X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u001f\u001a\u00020\u0015X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010 \u001a\u00020\u0015X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010!\u001a\u00020\u0015X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010\"\u001a\u00020\u0015X\u0080Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010#\u001a\u00020\u0015X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010$\u001a\u00020\u0015X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u000f\u0010(\u001a\u00020)X\u0082\u0084\b¢\u0006\u0002\n\u0000\"\u000f\u0010*\u001a\u00020)X\u0082\u0084\b¢\u0006\u0002\n\u0000\"\u000f\u0010+\u001a\u00020)X\u0082\u0084\b¢\u0006\u0002\n\u0000\"\u000f\u0010,\u001a\u00020)X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"isDistantPast", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/time/Instant;", "isDistantPast$annotations", "(Lkotlin/time/Instant;)V", "(Lkotlin/time/Instant;)Z", "isDistantFuture", "isDistantFuture$annotations", "DISTANT_PAST_SECONDS", _UrlKt.FRAGMENT_ENCODE_SET, "DISTANT_FUTURE_SECONDS", "MIN_SECOND", "MAX_SECOND", "parseIso", "Lkotlin/time/InstantParseResult;", "isoString", _UrlKt.FRAGMENT_ENCODE_SET, "formatIso", _UrlKt.FRAGMENT_ENCODE_SET, "instant", "DAYS_PER_CYCLE", _UrlKt.FRAGMENT_ENCODE_SET, "DAYS_0000_TO_1970", "safeAddOrElse", "a", "b", "action", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "safeMultiplyOrElse", "SECONDS_PER_HOUR", "SECONDS_PER_MINUTE", "HOURS_PER_DAY", "SECONDS_PER_DAY", "NANOS_PER_SECOND", "NANOS_PER_MILLI", "MILLIS_PER_SECOND", "isLeapYear", "year", "monthLength", "POWERS_OF_TEN", _UrlKt.FRAGMENT_ENCODE_SET, "asciiDigitPositionsInIsoStringAfterYear", "colonsInIsoOffsetString", "asciiDigitsInIsoOffsetString", "truncateForErrorMessage", "maxLength", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nInstant.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Instant.kt\nkotlin/time/InstantKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Instant.kt\nkotlin/time/UnboundLocalDateTime\n*L\n1#1,871:1\n1#2:872\n491#3,28:873\n*S KotlinDebug\n*F\n+ 1 Instant.kt\nkotlin/time/InstantKt\n*L\n700#1:873,28\n*E\n"})
/* loaded from: classes5.dex */
public final class InstantKt {
    private static final int DAYS_0000_TO_1970 = 719528;
    private static final int DAYS_PER_CYCLE = 146097;
    private static final long DISTANT_FUTURE_SECONDS = 3093527980800L;
    private static final long DISTANT_PAST_SECONDS = -3217862419201L;
    private static final int HOURS_PER_DAY = 24;
    private static final long MAX_SECOND = 31556889864403199L;
    private static final int MILLIS_PER_SECOND = 1000;
    private static final long MIN_SECOND = -31557014167219200L;
    private static final int NANOS_PER_MILLI = 1000000;
    public static final int NANOS_PER_SECOND = 1000000000;
    private static final int[] POWERS_OF_TEN = null;
    private static final int SECONDS_PER_DAY = 86400;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int[] asciiDigitPositionsInIsoStringAfterYear = null;
    private static final int[] asciiDigitsInIsoOffsetString = null;
    private static final int[] colonsInIsoOffsetString = null;

    /* renamed from: $r8$lambda$IkUn2NROTCX2lBvTn4A-V5RmZMI, reason: not valid java name */
    public static boolean m3481$r8$lambda$IkUn2NROTCX2lBvTn4AV5RmZMI(char r1) {
        if (r1 != ':') goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean $r8$lambda$Ue47iscnRYF1ET4bm4NsD_78ozQ(char r1) {
        if (r1 != '-') goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean $r8$lambda$VQuIyPyOaYjo_HSMWZ5md5B5WS4(char r1) {
        if (r1 != 'T') goto L5;
        return true;
    L5:
        if (r1 == 't') goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean $r8$lambda$YedMVjhOc1GtI8LPd5fqhvafh8s(char r1) {
        if ('0' <= r1) goto L5;
        return false;
    L5:
        if (r1 >= ':') goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean $r8$lambda$_13rfjrKJh1j1Bat0IdAkLKuo8Q(char r1) {
        if (r1 != '-') goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean $r8$lambda$sUF6YZWJqa7URjuTZna1EDa75cQ(char r1) {
        if (r1 != ':') goto L6;
        return true;
    L6:
        return false;
    }

    static {
        POWERS_OF_TEN = new int[]{1, 10, 100, 1000, 10000, AppDataDirGuesser.PER_USER_RANGE, 1000000, 10000000, 100000000, 1000000000};
        asciiDigitPositionsInIsoStringAfterYear = new int[]{1, 2, 4, 5, 7, 8, 10, 11, 13, 14};
        colonsInIsoOffsetString = new int[]{3, 6};
        asciiDigitsInIsoOffsetString = new int[]{1, 2, 4, 5, 7, 8};
    }

    public static final /* synthetic */ String access$formatIso(Instant r0) {
        return formatIso(r0);
    }

    public static final /* synthetic */ InstantParseResult access$parseIso(CharSequence r0) {
        return parseIso(r0);
    }

    public static final /* synthetic */ String access$truncateForErrorMessage(CharSequence r0, int r1) {
        return truncateForErrorMessage(r0, r1);
    }

    private static final String formatIso(Instant r7) {
        StringBuilder r0 = new StringBuilder();
        UnboundLocalDateTime r72 = UnboundLocalDateTime.Companion.fromInstant(r7);
        int r1 = r72.getYear();
        int r4 = 0;
        if (Math.abs(r1) >= 1000) goto L9;
        StringBuilder r2 = new StringBuilder();
        if (r1 < 0) goto L7;
        r2.append(r1 + 10000);
        r2.deleteCharAt(0);
    L8:
        r0.append(r2);
    L12:
        r0.append(SignatureVisitor.SUPER);
        formatIso$lambda$0$appendTwoDigits(r0, r0, r72.getMonth());
        r0.append(SignatureVisitor.SUPER);
        formatIso$lambda$0$appendTwoDigits(r0, r0, r72.getDay());
        r0.append('T');
        formatIso$lambda$0$appendTwoDigits(r0, r0, r72.getHour());
        r0.append(':');
        formatIso$lambda$0$appendTwoDigits(r0, r0, r72.getMinute());
        r0.append(':');
        formatIso$lambda$0$appendTwoDigits(r0, r0, r72.getSecond());
        if (r72.getNanosecond() == 0) goto L19;
        r0.append('.');
    L15:
        int r12 = r72.getNanosecond();
        int[] r22 = POWERS_OF_TEN;
        int r3 = r4 + 1;
        if ((r12 % r22[r3]) != 0) goto L18;
        r4 = r3;
        goto L15
    L18:
        int r42 = r4 - (r4 % 3);
        r0.append(String.valueOf((r72.getNanosecond() / r22[r42]) + r22[9 - r42]).substring(1));
    L19:
        r0.append('Z');
        return r0.toString();
    L7:
        r2.append(r1 - 10000);
        r2.deleteCharAt(1);
        goto L8
    L9:
        if (r1 < 10000) goto L11;
        r0.append(SignatureVisitor.EXTENDS);
    L11:
        r0.append(r1);
        goto L12
    }

    private static final void formatIso$lambda$0$appendTwoDigits(Appendable r1, StringBuilder r2, int r3) {
        if (r3 >= 10) goto L5;
        r1.append('0');
    L5:
        r2.append(r3);
    }

    private static final boolean isDistantFuture(Instant r1) {
        if (r1.compareTo2(Instant.Companion.getDISTANT_FUTURE()) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    @SinceKotlin(version = MVEL.VERSION)
    @WasExperimental(markerClass = {ExperimentalTime.class})
    @InlineOnly
    public static /* synthetic */ void isDistantFuture$annotations(Instant r0) {
    }

    private static final boolean isDistantPast(Instant r1) {
        if (r1.compareTo2(Instant.Companion.getDISTANT_PAST()) > 0) goto L6;
        return true;
    L6:
        return false;
    }

    @SinceKotlin(version = MVEL.VERSION)
    @WasExperimental(markerClass = {ExperimentalTime.class})
    @InlineOnly
    public static /* synthetic */ void isDistantPast$annotations(Instant r0) {
    }

    public static final boolean isLeapYear(int r1) {
        if ((r1 & 3) == 0) goto L5;
        return false;
    L5:
        if ((r1 % 100) == 0) goto L7;
        return true;
    L7:
        if ((r1 % MediaError.DetailedErrorCode.MANIFEST_UNKNOWN) != 0) goto L13;
        return true;
    L13:
        return false;
    }

    private static final int monthLength(int r1, boolean r2) {
        if (r1 != 2) goto L5;
        if (r2 == false) goto L19;
        return 29;
    L19:
        return 28;
    L5:
        if (r1 != 4) goto L7;
        return 30;
    L7:
        if (r1 != 6) goto L9;
        return 30;
    L9:
        if (r1 != 9) goto L11;
        return 30;
    L11:
        if (r1 == 11) goto L23;
        return 31;
    L23:
        return 30;
    }

    private static final InstantParseResult parseIso(CharSequence r25) {
        if (r25.length() == 0) goto L5;
        char r2 = r25.charAt(0);
        if (r2 == '+') goto L10;
        if (r2 == '-') goto L10;
        int r7 = 0;
        r2 = ' ';
    L11:
        int r9 = 0;
        int r8 = r7;
    L13:
        if (r8 >= r25.length()) goto L18;
        char r10 = r25.charAt(r8);
        if ('0' > r10) goto L18;
        if (r10 >= ':') goto L18;
        r9 = (r9 * 10) + (r25.charAt(r8) - '0');
        r8 = r8 + 1;
    L18:
        int r102 = r8 - r7;
        if (r102 > 10) goto L21;
        if (r102 != 10) goto L28;
        if (Intrinsics.compare(r25.charAt(r7), 50) < 0) goto L28;
        return parseIso$parseFailure(r25, "Expected at most 9 digits for the year number or year 1000000000, got " + r102 + " digits");
    L28:
        if (r102 < 4) goto L30;
        if (r2 != '+') goto L35;
        if (r102 != 4) goto L35;
        return parseIso$parseFailure(r25, "The '+' sign at the start is only valid for year numbers longer than 4 digits");
    L35:
        if (r2 != ' ') goto L39;
        if (r102 == 4) goto L39;
        return parseIso$parseFailure(r25, "A '+' or '-' sign is required for year numbers longer than 4 digits");
    L39:
        if (r2 != '-') goto L41;
        r9 = -r9;
    L41:
        int r16 = r9;
        int r3 = r8 + 16;
        if (r25.length() < r3) goto L44;
        InstantParseResult.Failure r22 = parseIso$expect(r25, "'-'", r8, new InstantKt$$ExternalSyntheticLambda1());
        if (r22 == null) goto L48;
        return r22;
    L48:
        InstantParseResult.Failure r23 = parseIso$expect(r25, "'-'", r8 + 3, new InstantKt$$ExternalSyntheticLambda2());
        if (r23 == null) goto L51;
        return r23;
    L51:
        InstantParseResult.Failure r24 = parseIso$expect(r25, "'T' or 't'", r8 + 6, new InstantKt$$ExternalSyntheticLambda3());
        if (r24 == null) goto L54;
        return r24;
    L54:
        InstantParseResult.Failure r26 = parseIso$expect(r25, "':'", r8 + 9, new InstantKt$$ExternalSyntheticLambda4());
        if (r26 == null) goto L57;
        return r26;
    L57:
        InstantParseResult.Failure r27 = parseIso$expect(r25, "':'", r8 + 12, new InstantKt$$ExternalSyntheticLambda5());
        if (r27 == null) goto L60;
        return r27;
    L60:
        int[] r28 = asciiDigitPositionsInIsoStringAfterYear;
        int r72 = r28.length;
        int r92 = 0;
    L61:
        if (r92 >= r72) goto L66;
        InstantParseResult.Failure r1 = parseIso$expect(r25, "an ASCII digit", r28[r92] + r8, new InstantKt$$ExternalSyntheticLambda6());
        if (r1 != null) goto L64;
        r92 = r92 + 1;
        goto L61
    L64:
        return r1;
    L66:
        int r12 = parseIso$twoDigitNumber(r25, r8 + 1);
        int r29 = parseIso$twoDigitNumber(r25, r8 + 4);
        int r73 = parseIso$twoDigitNumber(r25, r8 + 7);
        int r93 = parseIso$twoDigitNumber(r25, r8 + 10);
        int r103 = parseIso$twoDigitNumber(r25, r8 + 13);
        int r82 = r8 + 15;
        if (r25.charAt(r82) != '.') goto L81;
        r82 = r3;
        int r5 = 0;
    L70:
        if (r82 >= r25.length()) goto L75;
        char r15 = r25.charAt(r82);
        if ('0' > r15) goto L75;
        if (r15 >= ':') goto L75;
        r5 = (r5 * 10) + (r25.charAt(r82) - '0');
        r82 = r82 + 1;
    L75:
        int r32 = r82 - r3;
        if (1 > r32) goto L80;
        if (r32 >= 10) goto L80;
        int r222 = r5 * POWERS_OF_TEN[9 - r32];
    L83:
        if (r82 >= r25.length()) goto L85;
        char r33 = r25.charAt(r82);
        if (r33 != '+') goto L89;
    L101:
        int r152 = r25.length() - r82;
        if (r152 <= 9) goto L106;
        return parseIso$parseFailure(r25, "The UTC offset string \"" + truncateForErrorMessage(r25.subSequence(r82, r25.length()).toString(), 16) + "\" is too long");
    L106:
        if ((r152 % 3) != 0) goto L108;
        int[] r4 = colonsInIsoOffsetString;
        int r6 = r4.length;
        int r14 = 0;
    L110:
        if (r14 >= r6) goto L119;
        int r122 = r82 + r4[r14];
        if (r122 >= r25.length()) goto L119;
        if (r25.charAt(r122) != ':') goto L117;
        r14 = r14 + 1;
        goto L110
    L117:
        return parseIso$parseFailure(r25, "Expected ':' at index " + r122 + ", got '" + r25.charAt(r122) + '\'');
    L119:
        int[] r42 = asciiDigitsInIsoOffsetString;
        int r52 = r42.length;
        int r62 = 0;
    L120:
        if (r62 >= r52) goto L130;
        int r123 = r42[r62] + r82;
        if (r123 >= r25.length()) goto L130;
        char r142 = r25.charAt(r123);
        int[] r242 = r42;
        if ('0' > r142) goto L129;
        if (r142 >= ':') goto L129;
        r62 = r62 + 1;
        r42 = r242;
    L129:
        return parseIso$parseFailure(r25, "Expected an ASCII digit at index " + r123 + ", got '" + r25.charAt(r123) + '\'');
    L130:
        int r43 = parseIso$twoDigitNumber(r25, r82 + 1);
        if (r152 <= 3) goto L133;
        int r53 = parseIso$twoDigitNumber(r25, r82 + 4);
    L135:
        if (r152 <= 6) goto L138;
        int r63 = parseIso$twoDigitNumber(r25, r82 + 7);
    L139:
        if (r53 > 59) goto L141;
        if (r63 <= 59) goto L146;
        return parseIso$parseFailure(r25, "Expected offset-second-of-minute in 0..59, got " + r63);
    L146:
        if (r43 > 17) goto L148;
    L153:
        int r44 = ((r43 * SECONDS_PER_HOUR) + (r53 * 60)) + r63;
        if (r33 != '-') goto L156;
        int r34 = -1;
    L157:
        int r35 = r34 * r44;
        int r45 = 1;
    L158:
        if (r45 > r12) goto L190;
        if (r12 >= 13) goto L190;
        if (r45 > r29) goto L188;
        if (r29 > monthLength(r12, isLeapYear(r16))) goto L188;
        if (r73 <= 23) goto L169;
        return parseIso$parseFailure(r25, "Expected hour in 0..23, got " + r73);
    L169:
        if (r93 > 59) goto L171;
        if (r103 > 59) goto L174;
        UnboundLocalDateTime r153 = new UnboundLocalDateTime(r16, r12, r29, r73, r93, r103, r222);
        long r0 = r153.getYear();
        long r46 = 365 * r0;
        if (r0 < 0) goto L178;
        long r47 = r46 + ((((3 + r0) / 4) - ((99 + r0) / 100)) + ((r0 + 399) / 400));
    L179:
        long r48 = (r47 + (((r153.getMonth() * 367) - 362) / 12)) + (r153.getDay() - 1);
        if (r153.getMonth() <= 2) goto L186;
        long r02 = (-1) + r48;
        if (isLeapYear(r153.getYear()) == true) goto L184;
        r48 = r48 - 2;
        goto L186
    L184:
        r48 = r02;
    L186:
        return new InstantParseResult.Success((((r48 - 719528) * 86400) + (((r153.getHour() * SECONDS_PER_HOUR) + (r153.getMinute() * 60)) + r153.getSecond())) - r35, r153.getNanosecond());
    L178:
        r47 = r46 - (((r0 / (-4)) - (r0 / (-100))) + (r0 / (-400)));
        goto L179
    L174:
        return parseIso$parseFailure(r25, "Expected second-of-minute in 0..59, got " + r103);
    L171:
        return parseIso$parseFailure(r25, "Expected minute-of-hour in 0..59, got " + r93);
    L188:
        return parseIso$parseFailure(r25, "Expected a valid day-of-month for month " + r12 + " of year " + r16 + ", got " + r29);
    L190:
        return parseIso$parseFailure(r25, "Expected a month number in 1..12, got " + r12);
    L156:
        r34 = 1;
        goto L157
    L148:
        if (r43 != 18) goto L152;
        if (r53 != 0) goto L152;
        if (r63 == 0) goto L153;
    L152:
        return parseIso$parseFailure(r25, "Expected an offset in -18:00..+18:00, got " + r25.subSequence(r82, r25.length()).toString());
    L141:
        return parseIso$parseFailure(r25, "Expected offset-minute-of-hour in 0..59, got " + r53);
    L138:
        r63 = 0;
        goto L139
    L133:
        r53 = 0;
        goto L135
    L108:
        return parseIso$parseFailure(r25, "Invalid UTC offset string \"" + r25.subSequence(r82, r25.length()).toString() + Typography.quote);
    L89:
        if (r33 == '-') goto L101;
        if (r33 != 'Z') goto L93;
    L96:
        int r83 = r82 + 1;
        if (r25.length() != r83) goto L100;
        r45 = 1;
        r35 = 0;
        goto L158
    L100:
        return parseIso$parseFailure(r25, "Extra text after the instant at position " + r83);
    L93:
        if (r33 == 'z') goto L96;
        return parseIso$parseFailure(r25, "Expected the UTC offset at position " + r82 + ", got '" + r33 + '\'');
    L85:
        return parseIso$parseFailure(r25, "The UTC offset at the end of the string is missing");
    L80:
        return parseIso$parseFailure(r25, "1..9 digits are supported for the fraction of the second, got " + r32 + " digits");
    L81:
        r222 = 0;
        goto L83
    L44:
        return parseIso$parseFailure(r25, "The input string is too short");
    L30:
        return parseIso$parseFailure(r25, "The year number must be padded to 4 digits, got " + r102 + " digits");
    L21:
        return parseIso$parseFailure(r25, "Expected at most 10 digits for the year number, got " + r102 + " digits");
    L10:
        r7 = 1;
        goto L11
    L5:
        return new InstantParseResult.Failure("An empty string is not a valid Instant", r25);
    }

    private static final InstantParseResult.Failure parseIso$expect(CharSequence r2, String r3, int r4, Function1<? super Character, Boolean> r5) {
        char r0 = r2.charAt(r4);
        if (r5.invoke(Character.valueOf(r0)).booleanValue() == false) goto L7;
        return null;
    L7:
        return parseIso$parseFailure(r2, "Expected " + r3 + ", but got '" + r0 + "' at position " + r4);
    }

    private static final InstantParseResult.Failure parseIso$parseFailure(CharSequence r2, String r3) {
        return new InstantParseResult.Failure(r3 + " when parsing an Instant from \"" + truncateForErrorMessage(r2, 64) + Typography.quote, r2);
    }

    private static final int parseIso$twoDigitNumber(CharSequence r1, int r2) {
        return ((r1.charAt(r2) - '0') * 10) + (r1.charAt(r2 + 1) - '0');
    }

    private static final long safeAddOrElse(long r6, long r8, Function0 r10) {
        long r0 = r6 + r8;
        if ((r6 ^ r0) < 0) goto L5;
    L9:
        return r0;
    L5:
        if ((r6 ^ r8) < 0) goto L9;
        r10.invoke();
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    private static final long safeMultiplyOrElse(long r7, long r9, Function0 r11) {
        if (r9 != 1) goto L6;
        return r7;
    L6:
        if (r7 != 1) goto L9;
        return r9;
    L9:
        if (r7 != 0) goto L11;
    L26:
        return 0;
    L11:
        if (r9 == 0) goto L26;
        long r0 = r7 * r9;
        if ((r0 / r9) == r7) goto L16;
    L24:
        r11.invoke();
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    L16:
        if (r7 != Long.MIN_VALUE) goto L20;
        if (r9 == (-1)) goto L24;
    L20:
        if (r9 == Long.MIN_VALUE) goto L22;
    L23:
        return r0;
    L22:
        if (r7 == (-1)) goto L24;
        goto L23
    }

    private static final String truncateForErrorMessage(CharSequence r2, int r3) {
        if (r2.length() > r3) goto L7;
        return r2.toString();
    L7:
        return r2.subSequence(0, r3).toString() + "...";
    }
}
