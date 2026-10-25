package kotlin.text;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.internal.InlineOnly;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0019\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0081\u0088\u0004\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0001H\u0081\u0088\u0004\u001a\u001e\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0081\u0088\u0004\u001a\u001e\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0001H\u0081\u0088\u0004\u001a$\u0010\b\u001a\u00020\t*\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u000b\u001a\u00020\tH\u0086\u0080\u0004\u001a(\u0010\f\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\tH\u0086\u0080\u0004\u001a(\u0010\f\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\tH\u0086\u0080\u0004\u001a(\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\tH\u0086\u0080\u0004\u001a(\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\tH\u0086\u0080\u0004\u001a\u000e\u0010\u0012\u001a\u00020\u0002*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u0013\u001a\u00020\u0002*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u0014\u001a\u00020\u0002*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u0015\u001a\u00020\u0002*\u00020\u0002H\u0087\u0088\u0004\u001a\u000e\u0010\u0016\u001a\u00020\u0002*\u00020\u0017H\u0087\u0080\u0004\u001a\"\u0010\u0016\u001a\u00020\u0002*\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00012\b\b\u0002\u0010\u0019\u001a\u00020\u0001H\u0087\u0080\u0004\u001a\"\u0010\u001a\u001a\u00020\u0017*\u00020\u00022\b\b\u0002\u0010\u0018\u001a\u00020\u00012\b\b\u0002\u0010\u0019\u001a\u00020\u0001H\u0087\u0080\u0004\u001a\u000e\u0010\u001b\u001a\u00020\u0002*\u00020\u001cH\u0087\u0080\u0004\u001a,\u0010\u001b\u001a\u00020\u0002*\u00020\u001c2\b\b\u0002\u0010\u0018\u001a\u00020\u00012\b\b\u0002\u0010\u0019\u001a\u00020\u00012\b\b\u0002\u0010\u001d\u001a\u00020\tH\u0087\u0080\u0004\u001a\u000e\u0010\u001e\u001a\u00020\u001c*\u00020\u0002H\u0087\u0080\u0004\u001a,\u0010\u001e\u001a\u00020\u001c*\u00020\u00022\b\b\u0002\u0010\u0018\u001a\u00020\u00012\b\b\u0002\u0010\u0019\u001a\u00020\u00012\b\b\u0002\u0010\u001d\u001a\u00020\tH\u0087\u0080\u0004\u001a\u000e\u0010\u001a\u001a\u00020\u0017*\u00020\u0002H\u0087\u0088\u0004\u001a4\u0010\u001a\u001a\u00020\u0017*\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u00172\b\b\u0002\u0010 \u001a\u00020\u00012\b\b\u0002\u0010\u0018\u001a\u00020\u00012\b\b\u0002\u0010\u0019\u001a\u00020\u0001H\u0087\u0088\b\u001a+\u0010!\u001a\u00020\u0002*\u00020\u00022\u0016\u0010\"\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010$0#\"\u0004\u0018\u00010$H\u0087\u0088\u0004¢\u0006\u0002\u0010%\u001a3\u0010!\u001a\u00020\u0002*\u00020&2\u0006\u0010!\u001a\u00020\u00022\u0016\u0010\"\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010$0#\"\u0004\u0018\u00010$H\u0087\u0088\u0004¢\u0006\u0002\u0010'\u001a5\u0010!\u001a\u00020\u0002*\u00020\u00022\b\u0010(\u001a\u0004\u0018\u00010)2\u0016\u0010\"\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010$0#\"\u0004\u0018\u00010$H\u0087\u0088\u0004¢\u0006\u0002\u0010*\u001a=\u0010!\u001a\u00020\u0002*\u00020&2\b\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010!\u001a\u00020\u00022\u0016\u0010\"\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010$0#\"\u0004\u0018\u00010$H\u0087\u0088\u0004¢\u0006\u0002\u0010+\u001a&\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00020-*\u00020.2\u0006\u0010/\u001a\u0002002\b\b\u0002\u00101\u001a\u00020\u0001H\u0086\u0080\u0004\u001a\u0016\u00102\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u001e\u00102\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\u0088\u0004\u001a \u00103\u001a\u00020\t*\u00020\u00022\u0006\u00104\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\tH\u0086\u0080\u0004\u001a(\u00103\u001a\u00020\t*\u00020\u00022\u0006\u00104\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\tH\u0086\u0080\u0004\u001a \u00105\u001a\u00020\t*\u00020\u00022\u0006\u00106\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\tH\u0086\u0080\u0004\u001a*\u00107\u001a\u00020\u00022\u0006\u00108\u001a\u00020\u001c2\u0006\u00109\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\u00012\u0006\u0010;\u001a\u00020<H\u0087\u0088\u0004\u001a\u001a\u00107\u001a\u00020\u00022\u0006\u00108\u001a\u00020\u001c2\u0006\u0010;\u001a\u00020<H\u0087\u0088\u0004\u001a\"\u00107\u001a\u00020\u00022\u0006\u00108\u001a\u00020\u001c2\u0006\u00109\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u00107\u001a\u00020\u00022\u0006\u00108\u001a\u00020\u001cH\u0087\u0088\u0004\u001a\u0012\u00107\u001a\u00020\u00022\u0006\u0010=\u001a\u00020\u0017H\u0087\u0088\u0004\u001a\"\u00107\u001a\u00020\u00022\u0006\u0010=\u001a\u00020\u00172\u0006\u00109\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\"\u00107\u001a\u00020\u00022\u0006\u0010>\u001a\u00020?2\u0006\u00109\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u00107\u001a\u00020\u00022\u0006\u0010@\u001a\u00020AH\u0087\u0088\u0004\u001a\u0012\u00107\u001a\u00020\u00022\u0006\u0010B\u001a\u00020CH\u0087\u0088\u0004\u001a\u0016\u0010D\u001a\u00020\u0001*\u00020\u00022\u0006\u0010E\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0016\u0010F\u001a\u00020\u0001*\u00020\u00022\u0006\u0010E\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u001e\u0010G\u001a\u00020\u0001*\u00020\u00022\u0006\u0010H\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0001H\u0087\u0088\u0004\u001a \u0010I\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\tH\u0086\u0080\u0004\u001a\u0016\u0010J\u001a\u00020\t*\u00020\u00022\u0006\u0010K\u001a\u00020.H\u0087\u0088\u0004\u001a\u0016\u0010J\u001a\u00020\t*\u00020\u00022\u0006\u0010B\u001a\u00020AH\u0087\u0088\u0004\u001a\u001a\u0010J\u001a\u00020\t*\u0004\u0018\u00010.2\b\u0010\n\u001a\u0004\u0018\u00010.H\u0087\u0084\u0004\u001a\"\u0010J\u001a\u00020\t*\u0004\u0018\u00010.2\b\u0010\n\u001a\u0004\u0018\u00010.2\u0006\u0010\u000b\u001a\u00020\tH\u0087\u0080\u0004\u001a\u000e\u0010L\u001a\u00020\u0002*\u00020\u0002H\u0087\u0088\u0004\u001a\u001e\u0010M\u001a\u00020\u0001*\u00020\u00022\u0006\u0010E\u001a\u00020\u00012\u0006\u0010N\u001a\u00020\u0001H\u0087\u0088\u0004\u001a8\u0010O\u001a\u00020\t*\u00020.2\u0006\u0010P\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020.2\u0006\u0010Q\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\tH\u0086\u0080\u0004\u001a8\u0010O\u001a\u00020\t*\u00020\u00022\u0006\u0010P\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010Q\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\tH\u0086\u0080\u0004\u001a\u0016\u0010\u0014\u001a\u00020\u0002*\u00020\u00022\u0006\u0010(\u001a\u00020)H\u0087\u0088\u0004\u001a\u0016\u0010\u0015\u001a\u00020\u0002*\u00020\u00022\u0006\u0010(\u001a\u00020)H\u0087\u0088\u0004\u001a\u0016\u0010\u0012\u001a\u00020\u0002*\u00020\u00022\u0006\u0010(\u001a\u00020)H\u0087\u0088\u0004\u001a\u0016\u0010\u0013\u001a\u00020\u0002*\u00020\u00022\u0006\u0010(\u001a\u00020)H\u0087\u0088\u0004\u001a\u0018\u0010R\u001a\u00020\u001c*\u00020\u00022\b\b\u0002\u0010;\u001a\u00020<H\u0087\u0088\u0004\u001a\u0018\u0010S\u001a\u000200*\u00020\u00022\b\b\u0002\u0010T\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u000e\u0010U\u001a\u00020\u0002*\u00020\u0002H\u0087\u0080\u0004\u001a\u0016\u0010U\u001a\u00020\u0002*\u00020\u00022\u0006\u0010(\u001a\u00020)H\u0087\u0080\u0004\u001a\u000e\u0010V\u001a\u00020\u0002*\u00020\u0002H\u0087\u0080\u0004\u001a\u0016\u0010V\u001a\u00020\u0002*\u00020\u00022\u0006\u0010(\u001a\u00020)H\u0087\u0080\u0004\u001a\u0016\u0010W\u001a\u00020\u0002*\u00020.2\u0006\u0010X\u001a\u00020\u0001H\u0086\u0080\u0004\")\u0010Y\u001a\u0012\u0012\u0004\u0012\u00020\u00020Zj\b\u0012\u0004\u0012\u00020\u0002`[*\u00020&8FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\\\u0010]¨\u0006^"}, d2 = {"nativeIndexOf", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "ch", _UrlKt.FRAGMENT_ENCODE_SET, "fromIndex", "str", "nativeLastIndexOf", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "ignoreCase", "replace", "oldChar", "newChar", "oldValue", "newValue", "replaceFirst", "toUpperCase", "uppercase", "toLowerCase", "lowercase", "concatToString", _UrlKt.FRAGMENT_ENCODE_SET, "startIndex", "endIndex", "toCharArray", "decodeToString", _UrlKt.FRAGMENT_ENCODE_SET, "throwOnInvalidSequence", "encodeToByteArray", "destination", "destinationOffset", "format", "args", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "Lkotlin/String$Companion;", "(Lkotlin/jvm/internal/StringCompanionObject;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "locale", "Ljava/util/Locale;", "(Ljava/lang/String;Ljava/util/Locale;[Ljava/lang/Object;)Ljava/lang/String;", "(Lkotlin/jvm/internal/StringCompanionObject;Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "split", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "regex", "Ljava/util/regex/Pattern;", "limit", "substring", "startsWith", "prefix", "endsWith", "suffix", "String", "bytes", "offset", "length", "charset", "Ljava/nio/charset/Charset;", "chars", "codePoints", _UrlKt.FRAGMENT_ENCODE_SET, "stringBuffer", "Ljava/lang/StringBuffer;", "stringBuilder", "Ljava/lang/StringBuilder;", "codePointAt", "index", "codePointBefore", "codePointCount", "beginIndex", "compareTo", "contentEquals", "charSequence", "intern", "offsetByCodePoints", "codePointOffset", "regionMatches", "thisOffset", "otherOffset", "toByteArray", "toPattern", "flags", "capitalize", "decapitalize", "repeat", "n", "CASE_INSENSITIVE_ORDER", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "getCASE_INSENSITIVE_ORDER", "(Lkotlin/jvm/internal/StringCompanionObject;)Ljava/util/Comparator;", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/text/StringsKt")
@SourceDebugExtension({"SMAP\nStringsJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringsJVM.kt\nkotlin/text/StringsKt__StringsJVMKt\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,894:1\n1198#2,2:895\n1#3:897\n*S KotlinDebug\n*F\n+ 1 StringsJVM.kt\nkotlin/text/StringsKt__StringsJVMKt\n*L\n73#1:895,2\n*E\n"})
/* loaded from: classes.dex */
class StringsKt__StringsJVMKt extends StringsKt__StringNumberConversionsKt {
    public StringsKt__StringsJVMKt() {
    }

    @InlineOnly
    private static final String String(StringBuffer r1) {
        return new String(r1);
    }

    @InlineOnly
    private static final String String(StringBuilder r1) {
        return new String(r1);
    }

    @InlineOnly
    private static final String String(byte[] r2) {
        return new String(r2, Charsets.UTF_8);
    }

    @InlineOnly
    private static final String String(byte[] r2, int r3, int r4) {
        return new String(r2, r3, r4, Charsets.UTF_8);
    }

    @InlineOnly
    private static final String String(byte[] r1, int r2, int r3, Charset r4) {
        return new String(r1, r2, r3, r4);
    }

    @InlineOnly
    private static final String String(byte[] r1, Charset r2) {
        return new String(r1, r2);
    }

    @InlineOnly
    private static final String String(char[] r1) {
        return new String(r1);
    }

    @InlineOnly
    private static final String String(char[] r1, int r2, int r3) {
        return new String(r1, r2, r3);
    }

    @InlineOnly
    private static final String String(int[] r1, int r2, int r3) {
        return new String(r1, r2, r3);
    }

    @Deprecated(message = "Use replaceFirstChar instead.", replaceWith = @ReplaceWith(expression = "replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }", imports = {"java.util.Locale"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final String capitalize(String r1) {
        return capitalize(r1, Locale.getDefault());
    }

    @SinceKotlin(version = "1.4")
    @Deprecated(message = "Use replaceFirstChar instead.", replaceWith = @ReplaceWith(expression = "replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }", imports = {}))
    @LowPriorityInOverloadResolution
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final String capitalize(String r5, Locale r6) {
        if (r5.length() <= 0) goto L12;
        char r1 = r5.charAt(0);
        if (Character.isLowerCase(r1) == false) goto L13;
        StringBuilder r2 = new StringBuilder();
        char r3 = Character.toTitleCase(r1);
        if (r3 == Character.toUpperCase(r1)) goto L9;
        r2.append(r3);
    L10:
        r2.append(r5.substring(1));
        return r2.toString();
    L9:
        r2.append(r5.substring(0, 1).toUpperCase(r6));
        goto L10
    L13:
        return r5;
    L12:
        return r5;
    }

    @InlineOnly
    private static final int codePointAt(String r0, int r1) {
        return r0.codePointAt(r1);
    }

    @InlineOnly
    private static final int codePointBefore(String r0, int r1) {
        return r0.codePointBefore(r1);
    }

    @InlineOnly
    private static final int codePointCount(String r0, int r1, int r2) {
        return r0.codePointCount(r1, r2);
    }

    public static final int compareTo(String r0, String r1, boolean r2) {
        if (r2 == false) goto L6;
        return r0.compareToIgnoreCase(r1);
    L6:
        return r0.compareTo(r1);
    }

    public static /* synthetic */ int compareTo$default(String r0, String r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        return compareTo(r0, r1, r2);
    }

    @SinceKotlin(version = "1.4")
    public static String concatToString(char[] r1) {
        return new String(r1);
    }

    @SinceKotlin(version = "1.4")
    public static String concatToString(char[] r2, int r3, int r4) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(r3, r4, r2.length);
        return new String(r2, r3, r4 - r3);
    }

    public static /* synthetic */ String concatToString$default(char[] r0, int r1, int r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = 0;
    L6:
        if ((r3 & 2) == 0) goto L9;
        r2 = r0.length;
    L9:
        return concatToString(r0, r1, r2);
    }

    @SinceKotlin(version = "1.5")
    public static final boolean contentEquals(CharSequence r1, CharSequence r2) {
        if ((r1 instanceof String) == false) goto L8;
        if (r2 == null) goto L8;
        return ((String) r1).contentEquals(r2);
    L8:
        return StringsKt__StringsKt.contentEqualsImpl(r1, r2);
    }

    @SinceKotlin(version = "1.5")
    public static final boolean contentEquals(CharSequence r0, CharSequence r1, boolean r2) {
        if (r2 == false) goto L6;
        return StringsKt__StringsKt.contentEqualsIgnoreCaseImpl(r0, r1);
    L6:
        return contentEquals(r0, r1);
    }

    @InlineOnly
    private static final boolean contentEquals(String r0, CharSequence r1) {
        return r0.contentEquals(r1);
    }

    @InlineOnly
    private static final boolean contentEquals(String r0, StringBuffer r1) {
        return r0.contentEquals(r1);
    }

    @Deprecated(message = "Use replaceFirstChar instead.", replaceWith = @ReplaceWith(expression = "replaceFirstChar { it.lowercase(Locale.getDefault()) }", imports = {"java.util.Locale"}))
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final String decapitalize(String r4) {
        if (r4.length() > 0) goto L5;
        return r4;
    L5:
        if (Character.isLowerCase(r4.charAt(0)) == false) goto L7;
        return r4;
    L7:
        return r4.substring(0, 1).toLowerCase(Locale.getDefault()) + r4.substring(1);
    }

    @SinceKotlin(version = "1.4")
    @Deprecated(message = "Use replaceFirstChar instead.", replaceWith = @ReplaceWith(expression = "replaceFirstChar { it.lowercase(locale) }", imports = {}))
    @LowPriorityInOverloadResolution
    @DeprecatedSinceKotlin(warningSince = "1.5")
    public static final String decapitalize(String r3, Locale r4) {
        if (r3.length() > 0) goto L5;
        return r3;
    L5:
        if (Character.isLowerCase(r3.charAt(0)) == false) goto L7;
        return r3;
    L7:
        return r3.substring(0, 1).toLowerCase(r4) + r3.substring(1);
    }

    @SinceKotlin(version = "1.4")
    public static String decodeToString(byte[] r2) {
        return new String(r2, Charsets.UTF_8);
    }

    @SinceKotlin(version = "1.4")
    public static final String decodeToString(byte[] r2, int r3, int r4, boolean r5) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(r3, r4, r2.length);
        if (r5 == false) goto L5;
        CharsetDecoder r52 = Charsets.UTF_8.newDecoder();
        CodingErrorAction r0 = CodingErrorAction.REPORT;
        return r52.onMalformedInput(r0).onUnmappableCharacter(r0).decode(ByteBuffer.wrap(r2, r3, r4 - r3)).toString();
    L5:
        return new String(r2, r3, r4 - r3, Charsets.UTF_8);
    }

    public static /* synthetic */ String decodeToString$default(byte[] r1, int r2, int r3, boolean r4, int r5, Object r6) {
        if ((r5 & 1) == 0) goto L6;
        r2 = 0;
    L6:
        if ((r5 & 2) == 0) goto L9;
        r3 = r1.length;
    L9:
        if ((r5 & 4) == 0) goto L12;
        r4 = false;
    L12:
        return decodeToString(r1, r2, r3, r4);
    }

    @SinceKotlin(version = "1.4")
    public static byte[] encodeToByteArray(String r1) {
        return r1.getBytes(Charsets.UTF_8);
    }

    @SinceKotlin(version = "1.4")
    public static final byte[] encodeToByteArray(String r2, int r3, int r4, boolean r5) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(r3, r4, r2.length());
        if (r5 == false) goto L5;
        CharsetEncoder r52 = Charsets.UTF_8.newEncoder();
        CodingErrorAction r0 = CodingErrorAction.REPORT;
        ByteBuffer r22 = r52.onMalformedInput(r0).onUnmappableCharacter(r0).encode(CharBuffer.wrap(r2, r3, r4));
        if (r22.hasArray() == true) goto L9;
    L14:
        byte[] r32 = new byte[r22.remaining()];
        r22.get(r32);
        return r32;
    L9:
        if (r22.arrayOffset() != 0) goto L14;
        if (r22.remaining() != r22.array().length) goto L14;
        return r22.array();
    L5:
        return r2.substring(r3, r4).getBytes(Charsets.UTF_8);
    }

    public static /* synthetic */ byte[] encodeToByteArray$default(String r1, int r2, int r3, boolean r4, int r5, Object r6) {
        if ((r5 & 1) == 0) goto L6;
        r2 = 0;
    L6:
        if ((r5 & 2) == 0) goto L9;
        r3 = r1.length();
    L9:
        if ((r5 & 4) == 0) goto L12;
        r4 = false;
    L12:
        return encodeToByteArray(r1, r2, r3, r4);
    }

    public static boolean endsWith(String r7, String r8, boolean r9) {
        if (r9 == true) goto L6;
        return r7.endsWith(r8);
    L6:
        return regionMatches(r7, r7.length() - r8.length(), r8, 0, r8.length(), true);
    }

    public static /* synthetic */ boolean endsWith$default(String r0, String r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        return endsWith(r0, r1, r2);
    }

    public static boolean equals(String r0, String r1, boolean r2) {
        if (r0 != null) goto L8;
        if (r1 != null) goto L6;
        return true;
    L6:
        return false;
    L8:
        if (r2 == true) goto L12;
        return r0.equals(r1);
    L12:
        return r0.equalsIgnoreCase(r1);
    }

    public static /* synthetic */ boolean equals$default(String r0, String r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        return equals(r0, r1, r2);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final String format(String r1, Locale r2, Object... r3) {
        return String.format(r2, r1, Arrays.copyOf(r3, r3.length));
    }

    @InlineOnly
    private static final String format(String r1, Object... r2) {
        return String.format(r1, Arrays.copyOf(r2, r2.length));
    }

    @InlineOnly
    private static final String format(StringCompanionObject r0, String r1, Object... r2) {
        return String.format(r1, Arrays.copyOf(r2, r2.length));
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final String format(StringCompanionObject r0, Locale r1, String r2, Object... r3) {
        return String.format(r1, r2, Arrays.copyOf(r3, r3.length));
    }

    public static Comparator<String> getCASE_INSENSITIVE_ORDER(StringCompanionObject r0) {
        return String.CASE_INSENSITIVE_ORDER;
    }

    @InlineOnly
    private static final String intern(String r0) {
        return r0.intern();
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final String lowercase(String r1) {
        return r1.toLowerCase(Locale.ROOT);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final String lowercase(String r0, Locale r1) {
        return r0.toLowerCase(r1);
    }

    @InlineOnly
    private static final int nativeIndexOf(String r0, char r1, int r2) {
        return r0.indexOf(r1, r2);
    }

    @InlineOnly
    private static final int nativeIndexOf(String r0, String r1, int r2) {
        return r0.indexOf(r1, r2);
    }

    @InlineOnly
    private static final int nativeLastIndexOf(String r0, char r1, int r2) {
        return r0.lastIndexOf(r1, r2);
    }

    @InlineOnly
    private static final int nativeLastIndexOf(String r0, String r1, int r2) {
        return r0.lastIndexOf(r1, r2);
    }

    @InlineOnly
    private static final int offsetByCodePoints(String r0, int r1, int r2) {
        return r0.offsetByCodePoints(r1, r2);
    }

    public static final boolean regionMatches(CharSequence r6, int r7, CharSequence r8, int r9, int r10, boolean r11) {
        if ((r6 instanceof String) == false) goto L9;
        if ((r8 instanceof String) == false) goto L9;
        return regionMatches((String) r6, r7, (String) r8, r9, r10, r11);
    L9:
        return StringsKt__StringsKt.regionMatchesImpl(r6, r7, r8, r9, r10, r11);
    }

    public static boolean regionMatches(String r6, int r7, String r8, int r9, int r10, boolean r11) {
        if (r11 == true) goto L6;
        return r6.regionMatches(r7, r8, r9, r10);
    L6:
        return r6.regionMatches(r11, r7, r8, r9, r10);
    }

    public static /* synthetic */ boolean regionMatches$default(CharSequence r6, int r7, CharSequence r8, int r9, int r10, boolean r11, int r12, Object r13) {
        if ((r12 & 16) == 0) goto L6;
        r11 = false;
    L6:
        return regionMatches(r6, r7, r8, r9, r10, r11);
    }

    public static /* synthetic */ boolean regionMatches$default(String r6, int r7, String r8, int r9, int r10, boolean r11, int r12, Object r13) {
        if ((r12 & 16) == 0) goto L6;
        r11 = false;
    L6:
        return regionMatches(r6, r7, r8, r9, r10, r11);
    }

    public static String repeat(CharSequence r3, int r4) {
        if (r4 >= 0) goto L4;
        ByteString$$ExternalSyntheticBUOutline1.m("Count 'n' must be non-negative, but was ", r4, 46);
        return null;
    L4:
        if (r4 == 0) goto L25;
        int r1 = 1;
        if (r4 == 1) goto L24;
        int r2 = r3.length();
        if (r2 == 0) goto L22;
        if (r2 == 1) goto L17;
        StringBuilder r0 = new StringBuilder(r3.length() * r4);
        if (1 > r4) goto L16;
    L12:
        r0.append(r3);
        if (r1 == r4) goto L16;
        r1 = r1 + 1;
    L16:
        return r0.toString();
    L17:
        int r02 = 0;
        char r32 = r3.charAt(0);
        char[] r12 = new char[r4];
    L18:
        if (r02 >= r4) goto L21;
        r12[r02] = r32;
        r02 = r02 + 1;
        goto L18
    L21:
        return new String(r12);
    L22:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L24:
        return r3.toString();
    L25:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static final String replace(String r4, char r5, char r6, boolean r7) {
        if (r7 == false) goto L4;
        StringBuilder r1 = new StringBuilder(r4.length());
        int r0 = 0;
    L7:
        if (r0 >= r4.length()) goto L13;
        char r2 = r4.charAt(r0);
        if (CharsKt__CharKt.equals(r2, r5, r7) == false) goto L11;
        r2 = r6;
    L11:
        r1.append(r2);
        r0 = r0 + 1;
        goto L7
    L13:
        return r1.toString();
    L4:
        return r4.replace(r5, r6);
    }

    public static final String replace(String r6, String r7, String r8, boolean r9) {
        int r0 = 0;
        int r1 = StringsKt__StringsKt.indexOf(r6, r7, 0, r9);
        if (r1 >= 0) goto L5;
        return r6;
    L5:
        int r2 = r7.length();
        int r3 = RangesKt.coerceAtLeast(r2, 1);
        int r4 = (r6.length() - r2) + r8.length();
        if (r4 < 0) goto L15;
        StringBuilder r5 = new StringBuilder(r4);
    L8:
        r5.append(r6, r0, r1);
        r5.append(r8);
        r0 = r1 + r2;
        if (r1 >= r6.length()) goto L12;
        r1 = StringsKt__StringsKt.indexOf(r6, r7, r1 + r3, r9);
        if (r1 > 0) goto L8;
    L12:
        r5.append(r6, r0, r6.length());
        return r5.toString();
    L15:
        throw new OutOfMemoryError();
    }

    public static /* synthetic */ String replace$default(String r0, char r1, char r2, boolean r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r3 = false;
    L6:
        return replace(r0, r1, r2, r3);
    }

    public static /* synthetic */ String replace$default(String r0, String r1, String r2, boolean r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r3 = false;
    L6:
        return replace(r0, r1, r2, r3);
    }

    public static final String replaceFirst(String r6, char r7, char r8, boolean r9) {
        int r62 = StringsKt__StringsKt.indexOf$default(r6, r7, 0, r9, 2, null);
        if (r62 >= 0) goto L6;
        return r6;
    L6:
        return StringsKt__StringsKt.replaceRange(r6, r62, r62 + 1, String.valueOf(r8)).toString();
    }

    public static final String replaceFirst(String r6, String r7, String r8, boolean r9) {
        int r62 = StringsKt__StringsKt.indexOf$default(r6, r7, 0, r9, 2, null);
        if (r62 >= 0) goto L6;
        return r6;
    L6:
        return StringsKt__StringsKt.replaceRange(r6, r62, r7.length() + r62, r8).toString();
    }

    public static /* synthetic */ String replaceFirst$default(String r0, char r1, char r2, boolean r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r3 = false;
    L6:
        return replaceFirst(r0, r1, r2, r3);
    }

    public static /* synthetic */ String replaceFirst$default(String r0, String r1, String r2, boolean r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r3 = false;
    L6:
        return replaceFirst(r0, r1, r2, r3);
    }

    public static final List<String> split(CharSequence r0, Pattern r1, int r2) {
        StringsKt__StringsKt.requireNonNegativeLimit(r2);
        if (r2 != 0) goto L6;
        r2 = -1;
    L6:
        return ArraysKt.asList(r1.split(r0, r2));
    }

    public static /* synthetic */ List split$default(CharSequence r0, Pattern r1, int r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = 0;
    L6:
        return split(r0, r1, r2);
    }

    public static boolean startsWith(String r6, String r7, int r8, boolean r9) {
        if (r9 == true) goto L6;
        return r6.startsWith(r7, r8);
    L6:
        return regionMatches(r6, r8, r7, 0, r7.length(), r9);
    }

    public static boolean startsWith(String r6, String r7, boolean r8) {
        if (r8 == true) goto L6;
        return r6.startsWith(r7);
    L6:
        return regionMatches(r6, 0, r7, 0, r7.length(), r8);
    }

    public static /* synthetic */ boolean startsWith$default(String r0, String r1, int r2, boolean r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r3 = false;
    L6:
        return startsWith(r0, r1, r2, r3);
    }

    public static /* synthetic */ boolean startsWith$default(String r0, String r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        return startsWith(r0, r1, r2);
    }

    @InlineOnly
    private static final String substring(String r0, int r1) {
        return r0.substring(r1);
    }

    @InlineOnly
    private static final String substring(String r0, int r1, int r2) {
        return r0.substring(r1, r2);
    }

    @InlineOnly
    private static final byte[] toByteArray(String r0, Charset r1) {
        return r0.getBytes(r1);
    }

    public static /* synthetic */ byte[] toByteArray$default(String r0, Charset r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = Charsets.UTF_8;
    L6:
        return r0.getBytes(r1);
    }

    @InlineOnly
    private static final char[] toCharArray(String r0) {
        return r0.toCharArray();
    }

    @SinceKotlin(version = "1.4")
    public static final char[] toCharArray(String r2, int r3, int r4) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(r3, r4, r2.length());
        char[] r0 = new char[r4 - r3];
        r2.getChars(r3, r4, r0, 0);
        return r0;
    }

    @IgnorableReturnValue
    @InlineOnly
    private static final char[] toCharArray(String r0, char[] r1, int r2, int r3, int r4) {
        r0.getChars(r3, r4, r1, r2);
        return r1;
    }

    public static /* synthetic */ char[] toCharArray$default(String r0, int r1, int r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = 0;
    L6:
        if ((r3 & 2) == 0) goto L9;
        r2 = r0.length();
    L9:
        return toCharArray(r0, r1, r2);
    }

    public static /* synthetic */ char[] toCharArray$default(String r1, char[] r2, int r3, int r4, int r5, int r6, Object r7) {
        if ((r6 & 2) == 0) goto L6;
        r3 = 0;
    L6:
        if ((r6 & 4) == 0) goto L9;
        r4 = 0;
    L9:
        if ((r6 & 8) == 0) goto L11;
        r5 = r1.length();
    L11:
        r1.getChars(r4, r5, r2, r3);
        return r2;
    }

    @Deprecated(message = "Use lowercase() instead.", replaceWith = @ReplaceWith(expression = "lowercase(Locale.getDefault())", imports = {"java.util.Locale"}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.5")
    @InlineOnly
    private static final String toLowerCase(String r0) {
        return r0.toLowerCase();
    }

    @Deprecated(message = "Use lowercase() instead.", replaceWith = @ReplaceWith(expression = "lowercase(locale)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.5")
    @InlineOnly
    private static final String toLowerCase(String r0, Locale r1) {
        return r0.toLowerCase(r1);
    }

    @InlineOnly
    private static final Pattern toPattern(String r0, int r1) {
        return Pattern.compile(r0, r1);
    }

    public static /* synthetic */ Pattern toPattern$default(String r0, int r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = 0;
    L6:
        return Pattern.compile(r0, r1);
    }

    @Deprecated(message = "Use uppercase() instead.", replaceWith = @ReplaceWith(expression = "uppercase(Locale.getDefault())", imports = {"java.util.Locale"}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.5")
    @InlineOnly
    private static final String toUpperCase(String r0) {
        return r0.toUpperCase();
    }

    @Deprecated(message = "Use uppercase() instead.", replaceWith = @ReplaceWith(expression = "uppercase(locale)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.5")
    @InlineOnly
    private static final String toUpperCase(String r0, Locale r1) {
        return r0.toUpperCase(r1);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final String uppercase(String r1) {
        return r1.toUpperCase(Locale.ROOT);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final String uppercase(String r0, Locale r1) {
        return r0.toUpperCase(r1);
    }
}
