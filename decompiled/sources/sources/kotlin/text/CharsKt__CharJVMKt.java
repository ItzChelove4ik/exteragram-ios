package kotlin.text;

import java.util.Locale;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\u001a\u000e\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u0007\u001a\u00020\u0006*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\b\u001a\u00020\u0006*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\t\u001a\u00020\u0006*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\n\u001a\u00020\u0006*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u000b\u001a\u00020\u0006*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\f\u001a\u00020\u0006*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\r\u001a\u00020\u0006*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u000e\u001a\u00020\u0006*\u00020\u0002H\u0086\u0080\u0004\u001a\u000e\u0010\u000f\u001a\u00020\u0006*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u0010\u001a\u00020\u0006*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u0011\u001a\u00020\u0002*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u0012\u001a\u00020\u0002*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u0013\u001a\u00020\u0014*\u00020\u0002H\u0087\u0088\u0004\u001a\u0016\u0010\u0013\u001a\u00020\u0014*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0087\u0080\u0004\u001a\u000e\u0010\u0017\u001a\u00020\u0002*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u0018\u001a\u00020\u0002*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u0019\u001a\u00020\u0014*\u00020\u0002H\u0087\u0088\u0004\u001a\u0016\u0010\u0019\u001a\u00020\u0014*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0087\u0080\u0004\u001a\u000e\u0010\u001a\u001a\u00020\u0006*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u001b\u001a\u00020\u0002*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u001c\u001a\u00020\u0002*\u00020\u0002H\u0087\u0088\u0004\u001a\u0016\u0010\u001d\u001a\u00020\u0014*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0087\u0080\u0004\u001a\u000e\u0010\"\u001a\u00020\u0006*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010#\u001a\u00020\u0006*\u00020\u0002H\u0087\u0088\u0004\u001a\u001a\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00022\u0006\u0010'\u001a\u00020%H\u0080\u0080\u0004\u001a\u0012\u0010(\u001a\u00020%2\u0006\u0010'\u001a\u00020%H\u0081\u0080\b\"\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0019\u0010\u001e\u001a\u00020\u001f*\u00020\u00028FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006)"}, d2 = {"category", "Lkotlin/text/CharCategory;", _UrlKt.FRAGMENT_ENCODE_SET, "getCategory", "(C)Lkotlin/text/CharCategory;", "isDefined", _UrlKt.FRAGMENT_ENCODE_SET, "isLetter", "isLetterOrDigit", "isDigit", "isIdentifierIgnorable", "isISOControl", "isJavaIdentifierPart", "isJavaIdentifierStart", "isWhitespace", "isUpperCase", "isLowerCase", "toUpperCase", "uppercaseChar", "uppercase", _UrlKt.FRAGMENT_ENCODE_SET, "locale", "Ljava/util/Locale;", "toLowerCase", "lowercaseChar", "lowercase", "isTitleCase", "toTitleCase", "titlecaseChar", "titlecase", "directionality", "Lkotlin/text/CharDirectionality;", "getDirectionality", "(C)Lkotlin/text/CharDirectionality;", "isHighSurrogate", "isLowSurrogate", "digitOf", _UrlKt.FRAGMENT_ENCODE_SET, "char", "radix", "checkRadix", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/text/CharsKt")
/* loaded from: classes.dex */
class CharsKt__CharJVMKt {
    public CharsKt__CharJVMKt() {
    }

    @PublishedApi
    @IgnorableReturnValue
    public static int checkRadix(int r4) {
        if (2 > r4) goto L8;
        if (r4 >= 37) goto L8;
        return r4;
    L8:
        throw new IllegalArgumentException("radix " + r4 + " was not in valid range " + new IntRange(2, 36));
    }

    public static final int digitOf(char r0, int r1) {
        return Character.digit(r0, r1);
    }

    public static final CharCategory getCategory(char r1) {
        return CharCategory.Companion.valueOf(Character.getType(r1));
    }

    public static final CharDirectionality getDirectionality(char r1) {
        return CharDirectionality.Companion.valueOf(Character.getDirectionality(r1));
    }

    @InlineOnly
    private static final boolean isDefined(char r0) {
        return Character.isDefined(r0);
    }

    @InlineOnly
    private static final boolean isDigit(char r0) {
        return Character.isDigit(r0);
    }

    @InlineOnly
    private static final boolean isHighSurrogate(char r0) {
        return Character.isHighSurrogate(r0);
    }

    @InlineOnly
    private static final boolean isISOControl(char r0) {
        return Character.isISOControl(r0);
    }

    @InlineOnly
    private static final boolean isIdentifierIgnorable(char r0) {
        return Character.isIdentifierIgnorable(r0);
    }

    @InlineOnly
    private static final boolean isJavaIdentifierPart(char r0) {
        return Character.isJavaIdentifierPart(r0);
    }

    @InlineOnly
    private static final boolean isJavaIdentifierStart(char r0) {
        return Character.isJavaIdentifierStart(r0);
    }

    @InlineOnly
    private static final boolean isLetter(char r0) {
        return Character.isLetter(r0);
    }

    @InlineOnly
    private static final boolean isLetterOrDigit(char r0) {
        return Character.isLetterOrDigit(r0);
    }

    @InlineOnly
    private static final boolean isLowSurrogate(char r0) {
        return Character.isLowSurrogate(r0);
    }

    @InlineOnly
    private static final boolean isLowerCase(char r0) {
        return Character.isLowerCase(r0);
    }

    @InlineOnly
    private static final boolean isTitleCase(char r0) {
        return Character.isTitleCase(r0);
    }

    @InlineOnly
    private static final boolean isUpperCase(char r0) {
        return Character.isUpperCase(r0);
    }

    public static boolean isWhitespace(char r1) {
        if (Character.isWhitespace(r1) == false) goto L5;
        return true;
    L5:
        if (Character.isSpaceChar(r1) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final String lowercase(char r1) {
        return String.valueOf(r1).toLowerCase(Locale.ROOT);
    }

    @SinceKotlin(version = "1.5")
    public static final String lowercase(char r0, Locale r1) {
        return String.valueOf(r0).toLowerCase(r1);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final char lowercaseChar(char r0) {
        return Character.toLowerCase(r0);
    }

    @SinceKotlin(version = "1.5")
    public static final String titlecase(char r2, Locale r3) {
        String r32 = uppercase(r2, r3);
        if (r32.length() <= 1) goto L10;
        if (r2 != 329) goto L8;
    L11:
        return r32;
    L8:
        return r32.charAt(0) + r32.substring(1).toLowerCase(Locale.ROOT);
    L10:
        if (Intrinsics.areEqual(r32, String.valueOf(r2).toUpperCase(Locale.ROOT)) == false) goto L11;
        return String.valueOf(Character.toTitleCase(r2));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final char titlecaseChar(char r0) {
        return Character.toTitleCase(r0);
    }

    @Deprecated(message = "Use lowercaseChar() instead.", replaceWith = @ReplaceWith(expression = "lowercaseChar()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.5")
    @InlineOnly
    private static final char toLowerCase(char r0) {
        return Character.toLowerCase(r0);
    }

    @Deprecated(message = "Use titlecaseChar() instead.", replaceWith = @ReplaceWith(expression = "titlecaseChar()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.5")
    @InlineOnly
    private static final char toTitleCase(char r0) {
        return Character.toTitleCase(r0);
    }

    @Deprecated(message = "Use uppercaseChar() instead.", replaceWith = @ReplaceWith(expression = "uppercaseChar()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.5")
    @InlineOnly
    private static final char toUpperCase(char r0) {
        return Character.toUpperCase(r0);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final String uppercase(char r1) {
        return String.valueOf(r1).toUpperCase(Locale.ROOT);
    }

    @SinceKotlin(version = "1.5")
    public static final String uppercase(char r0, Locale r1) {
        return String.valueOf(r0).toUpperCase(r1);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final char uppercaseChar(char r0) {
        return Character.toUpperCase(r0);
    }
}
