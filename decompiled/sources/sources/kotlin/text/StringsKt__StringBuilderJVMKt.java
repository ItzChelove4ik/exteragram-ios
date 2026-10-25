package kotlin.text;

import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000h\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0007\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u001e\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\b\u001a\u001e\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\u0088\b\u001a&\u0010\u0006\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\b\u001a&\u0010\u0006\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\u0088\b\u001a\u0016\u0010\t\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002H\u0087\u0080\b\u001a\"\u0010\n\u001a\u00020\u000b*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\fH\u0087\u008a\u0004\u001a.\u0010\r\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0010H\u0087\u0088\b\u001a\u001e\u0010\u0011\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0087\u0088\b\u001a&\u0010\u0012\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0087\u0088\b\u001a8\u0010\u0013\u001a\u00020\u000b*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\bH\u0087\u0088\u0004\u001a.\u0010\u0017\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0087\u0088\b\u001a.\u0010\u0017\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0087\u0088\b\u001a6\u0010\u0019\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0087\u0088\b\u001a6\u0010\u0019\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0087\u0088\b\u001a \u0010\u001a\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u001bH\u0087\u0088\b\u001a&\u0010\u001a\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u000e\u0010\u0003\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002H\u0087\u0088\b\u001a\u001e\u0010\u001a\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\bH\u0087\u0088\b\u001a\u001e\u0010\u001a\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\u0088\b\u001a\u001e\u0010\u001a\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\b\u001a\u001e\u0010\u001a\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u001cH\u0087\u0088\b\u001a\u001e\u0010\u001a\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u001dH\u0087\u0088\b\u001a\u001e\u0010\u001a\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u001eH\u0087\u0088\b\u001a\u0016\u0010\u001f\u001a\u00060 j\u0002`!*\u00060 j\u0002`!H\u0087\u0080\u0004\u001a \u0010\u001f\u001a\u00060 j\u0002`!*\u00060 j\u0002`!2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0018H\u0087\u0088\u0004\u001a\u001e\u0010\u001f\u001a\u00060 j\u0002`!*\u00060 j\u0002`!2\u0006\u0010\u0003\u001a\u00020\fH\u0087\u0088\u0004\u001a\u0016\u0010\u001f\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002H\u0087\u0080\u0004\u001a \u0010\u001f\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u001bH\u0087\u0088\u0004\u001a \u0010\u001f\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0018H\u0087\u0088\u0004\u001a \u0010\u001f\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0010H\u0087\u0088\u0004\u001a \u0010\u001f\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\"H\u0087\u0088\u0004\u001a&\u0010\u001f\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u000e\u0010\u0003\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002H\u0087\u0088\u0004\u001a\u001e\u0010\u001f\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0015H\u0087\u0088\u0004\u001a\u001e\u0010\u001f\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\fH\u0087\u0088\u0004\u001a\u001e\u0010\u001f\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020#H\u0087\u0088\u0004\u001a\u001e\u0010\u001f\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\bH\u0087\u0088\u0004\u001a\u001e\u0010\u001f\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0005H\u0087\u0088\u0004\u001a\u001e\u0010\u001f\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004\u001a\u001e\u0010\u001f\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u001cH\u0087\u0088\u0004\u001a\u001e\u0010\u001f\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u001dH\u0087\u0088\u0004\u001a\u001e\u0010\u001f\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u001eH\u0087\u0088\u0004¨\u0006$"}, d2 = {"append", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "value", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "insert", "index", _UrlKt.FRAGMENT_ENCODE_SET, "clear", "set", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "setRange", "startIndex", "endIndex", _UrlKt.FRAGMENT_ENCODE_SET, "deleteAt", "deleteRange", "toCharArray", "destination", _UrlKt.FRAGMENT_ENCODE_SET, "destinationOffset", "appendRange", _UrlKt.FRAGMENT_ENCODE_SET, "insertRange", "appendLine", "Ljava/lang/StringBuffer;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "appendln", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/text/StringsKt")
@SourceDebugExtension({"SMAP\nStringBuilderJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringBuilderJVM.kt\nkotlin/text/StringsKt__StringBuilderJVMKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,437:1\n1#2:438\n*E\n"})
/* loaded from: classes.dex */
class StringsKt__StringBuilderJVMKt extends StringsKt__RegexExtensionsKt {
    public StringsKt__StringBuilderJVMKt() {
    }

    @SinceKotlin(version = "1.9")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder append(StringBuilder r0, byte r1) {
        r0.append(r1);
        return r0;
    }

    @SinceKotlin(version = "1.9")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder append(StringBuilder r0, short r1) {
        r0.append(r1);
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder r0, byte r1) {
        r0.append(r1);
        r0.append('\n');
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder r0, double r1) {
        r0.append(r1);
        r0.append('\n');
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder r0, float r1) {
        r0.append(r1);
        r0.append('\n');
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder r0, int r1) {
        r0.append(r1);
        r0.append('\n');
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder r0, long r1) {
        r0.append(r1);
        r0.append('\n');
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder r0, StringBuffer r1) {
        r0.append(r1);
        r0.append('\n');
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder r0, StringBuilder r1) {
        r0.append(r1);
        r0.append('\n');
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder r0, short r1) {
        r0.append(r1);
        r0.append('\n');
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder appendRange(StringBuilder r0, CharSequence r1, int r2, int r3) {
        r0.append(r1, r2, r3);
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder appendRange(StringBuilder r0, char[] r1, int r2, int r3) {
        r0.append(r1, r2, r3 - r2);
        return r0;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    public static final Appendable appendln(Appendable r1) {
        return r1.append(SystemProperties.LINE_SEPARATOR);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    private static final Appendable appendln(Appendable r0, char r1) {
        return appendln(r0.append(r1));
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    private static final Appendable appendln(Appendable r0, CharSequence r1) {
        return appendln(r0.append(r1));
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    public static final StringBuilder appendln(StringBuilder r1) {
        r1.append(SystemProperties.LINE_SEPARATOR);
        return r1;
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder r0, byte r1) {
        r0.append(r1);
        return appendln(r0);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder r0, char r1) {
        r0.append(r1);
        return appendln(r0);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder r0, double r1) {
        r0.append(r1);
        return appendln(r0);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder r0, float r1) {
        r0.append(r1);
        return appendln(r0);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder r0, int r1) {
        r0.append(r1);
        return appendln(r0);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder r0, long r1) {
        r0.append(r1);
        return appendln(r0);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder r0, CharSequence r1) {
        r0.append(r1);
        return appendln(r0);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder r0, Object r1) {
        r0.append(r1);
        return appendln(r0);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder r0, String r1) {
        r0.append(r1);
        return appendln(r0);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder r0, StringBuffer r1) {
        r0.append(r1);
        return appendln(r0);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder r0, StringBuilder r1) {
        r0.append(r1);
        return appendln(r0);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder r0, short r1) {
        r0.append(r1);
        return appendln(r0);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder r0, boolean r1) {
        r0.append(r1);
        return appendln(r0);
    }

    @Deprecated(message = "Use appendLine instead. Note that the new method always appends the line feed character '\\n' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.4")
    @InlineOnly
    private static final StringBuilder appendln(StringBuilder r0, char[] r1) {
        r0.append(r1);
        return appendln(r0);
    }

    @SinceKotlin(version = "1.3")
    @IgnorableReturnValue
    public static final StringBuilder clear(StringBuilder r1) {
        r1.setLength(0);
        return r1;
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder deleteAt(StringBuilder r0, int r1) {
        return r0.deleteCharAt(r1);
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder deleteRange(StringBuilder r0, int r1, int r2) {
        return r0.delete(r1, r2);
    }

    @SinceKotlin(version = "1.9")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder insert(StringBuilder r0, int r1, byte r2) {
        return r0.insert(r1, r2);
    }

    @SinceKotlin(version = "1.9")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder insert(StringBuilder r0, int r1, short r2) {
        return r0.insert(r1, r2);
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder insertRange(StringBuilder r0, int r1, CharSequence r2, int r3, int r4) {
        return r0.insert(r1, r2, r3, r4);
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder insertRange(StringBuilder r0, int r1, char[] r2, int r3, int r4) {
        return r0.insert(r1, r2, r3, r4 - r3);
    }

    @InlineOnly
    private static final void set(StringBuilder r0, int r1, char r2) {
        r0.setCharAt(r1, r2);
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder setRange(StringBuilder r0, int r1, int r2, String r3) {
        return r0.replace(r1, r2, r3);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    private static final void toCharArray(StringBuilder r0, char[] r1, int r2, int r3, int r4) {
        r0.getChars(r3, r4, r1, r2);
    }

    public static /* synthetic */ void toCharArray$default(StringBuilder r1, char[] r2, int r3, int r4, int r5, int r6, Object r7) {
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
    }
}
