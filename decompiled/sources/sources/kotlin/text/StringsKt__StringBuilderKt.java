package kotlin.text;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0002\b\u0005\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a%\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0087\u0088\b¢\u0006\u0002\u0010\u0005\u001a7\u0010\u0006\u001a\u00020\u00072\u001b\u0010\b\u001a\u0017\u0012\b\u0012\u00060\u0001j\u0002`\u0002\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000bH\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001a?\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u001b\u0010\b\u001a\u0017\u0012\b\u0012\u00060\u0001j\u0002`\u0002\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000bH\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001a3\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00070\u000f\"\u0004\u0018\u00010\u0007H\u0087\u0080\b¢\u0006\u0002\u0010\u0010\u001a3\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00040\u000f\"\u0004\u0018\u00010\u0004H\u0087\u0080\b¢\u0006\u0002\u0010\u0011\u001a3\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\rH\u0087\u0088\b¢\u0006\u0002\u0010\u0016\u001a\u001b\u0010\u0017\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002H\u0087\u0088\b¢\u0006\u0002\u0010\u0018\u001a%\u0010\u0017\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\u0019H\u0087\u0088\b¢\u0006\u0002\u0010\u001a\u001a%\u0010\u0017\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\u0007H\u0087\u0088\b¢\u0006\u0002\u0010\u001b\u001a%\u0010\u0017\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004H\u0087\u0088\b¢\u0006\u0002\u0010\u0005\u001a#\u0010\u0017\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u000e\u001a\u00020\u0013H\u0087\u0088\b¢\u0006\u0002\u0010\u001c\u001a#\u0010\u0017\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u000e\u001a\u00020\u001dH\u0087\u0088\b¢\u0006\u0002\u0010\u001e\u001a#\u0010\u0017\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u000e\u001a\u00020\u001fH\u0087\u0088\b¢\u0006\u0002\u0010 \u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006!"}, d2 = {"append", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "obj", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/StringBuilder;Ljava/lang/Object;)Ljava/lang/StringBuilder;", "buildString", _UrlKt.FRAGMENT_ENCODE_SET, "builderAction", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "capacity", _UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/StringBuilder;[Ljava/lang/String;)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;[Ljava/lang/Object;)Ljava/lang/StringBuilder;", "str", _UrlKt.FRAGMENT_ENCODE_SET, "offset", "len", "(Ljava/lang/StringBuilder;[CII)Ljava/lang/StringBuilder;", "appendLine", "(Ljava/lang/StringBuilder;)Ljava/lang/StringBuilder;", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/StringBuilder;Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;[C)Ljava/lang/StringBuilder;", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/StringBuilder;C)Ljava/lang/StringBuilder;", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/StringBuilder;Z)Ljava/lang/StringBuilder;", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/text/StringsKt")
/* loaded from: classes.dex */
class StringsKt__StringBuilderKt extends StringsKt__StringBuilderJVMKt {
    public StringsKt__StringBuilderKt() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use append(value: Any?) instead", replaceWith = @ReplaceWith(expression = "append(value = obj)", imports = {}))
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder append(StringBuilder r0, Object r1) {
        r0.append(r1);
        return r0;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use appendRange instead.", replaceWith = @ReplaceWith(expression = "this.appendRange(str, offset, offset + len)", imports = {}))
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder append(StringBuilder r0, char[] r1, int r2, int r3) {
        throw new NotImplementedError(null, 1, null);
    }

    @IgnorableReturnValue
    public static final StringBuilder append(StringBuilder r3, Object... r4) {
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3.append(r4[r1]);
        r1 = r1 + 1;
        goto L3
    L5:
        return r3;
    }

    @IgnorableReturnValue
    public static final StringBuilder append(StringBuilder r3, String... r4) {
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3.append(r4[r1]);
        r1 = r1 + 1;
        goto L3
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder r1) {
        r1.append('\n');
        return r1;
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder r0, char r1) {
        r0.append(r1);
        r0.append('\n');
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder r0, CharSequence r1) {
        r0.append(r1);
        r0.append('\n');
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder r0, Object r1) {
        r0.append(r1);
        r0.append('\n');
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder r0, String r1) {
        r0.append(r1);
        r0.append('\n');
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder r0, boolean r1) {
        r0.append(r1);
        r0.append('\n');
        return r0;
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final StringBuilder appendLine(StringBuilder r0, char[] r1) {
        r0.append(r1);
        r0.append('\n');
        return r0;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String buildString(int r1, Function1<? super StringBuilder, Unit> r2) {
        StringBuilder r0 = new StringBuilder(r1);
        r2.invoke(r0);
        return r0.toString();
    }

    @InlineOnly
    private static final String buildString(Function1<? super StringBuilder, Unit> r1) {
        StringBuilder r0 = new StringBuilder();
        r1.invoke(r0);
        return r0.toString();
    }
}
