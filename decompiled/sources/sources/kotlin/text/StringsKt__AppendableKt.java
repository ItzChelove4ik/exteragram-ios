package kotlin.text;

import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000<\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a9\u0010\u0000\u001a\u0002H\u0001\"\f\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u0003*\u0002H\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0087\u0080\b¢\u0006\u0002\u0010\t\u001a9\u0010\n\u001a\u0002H\u0001\"\f\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u0003*\u0002H\u00012\u0016\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u000b\"\u0004\u0018\u00010\u0005H\u0087\u0080\b¢\u0006\u0002\u0010\f\u001a\u001b\u0010\r\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u0003H\u0087\u0088\b¢\u0006\u0002\u0010\u000e\u001a%\u0010\r\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0087\u0088\b¢\u0006\u0002\u0010\u000f\u001a#\u0010\r\u001a\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u00032\u0006\u0010\u0004\u001a\u00020\u0010H\u0087\u0088\b¢\u0006\u0002\u0010\u0011\u001a;\u0010\u0012\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u00032\u0006\u0010\u0014\u001a\u0002H\u00012\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0016H\u0080\u0080\u0004¢\u0006\u0002\u0010\u0017¨\u0006\u0018"}, d2 = {"appendRange", "T", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "value", _UrlKt.FRAGMENT_ENCODE_SET, "startIndex", _UrlKt.FRAGMENT_ENCODE_SET, "endIndex", "(Ljava/lang/Appendable;Ljava/lang/CharSequence;II)Ljava/lang/Appendable;", "append", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/Appendable;[Ljava/lang/CharSequence;)Ljava/lang/Appendable;", "appendLine", "(Ljava/lang/Appendable;)Ljava/lang/Appendable;", "(Ljava/lang/Appendable;Ljava/lang/CharSequence;)Ljava/lang/Appendable;", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/Appendable;C)Ljava/lang/Appendable;", "appendElement", _UrlKt.FRAGMENT_ENCODE_SET, "element", "transform", "Lkotlin/Function1;", "(Ljava/lang/Appendable;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/text/StringsKt")
/* loaded from: classes.dex */
class StringsKt__AppendableKt {
    public StringsKt__AppendableKt() {
    }

    @IgnorableReturnValue
    public static final <T extends Appendable> T append(T r3, CharSequence... r4) {
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

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> void appendElement(Appendable r0, T r1, Function1<? super T, ? extends CharSequence> r2) {
        if (r2 == null) goto L5;
        r0.append(r2.invoke(r1));
        return;
    L5:
        if (r1 != 0) goto L7;
        boolean r22 = true;
    L8:
        if (r22 == false) goto L12;
        r0.append((CharSequence) r1);
        return;
    L12:
        if ((r1 instanceof Character) == false) goto L15;
        r0.append(((Character) r1).charValue());
        return;
    L15:
        r0.append(r1.toString());
        return;
    L7:
        r22 = r1 instanceof CharSequence;
        goto L8
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final Appendable appendLine(Appendable r1) {
        return r1.append('\n');
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final Appendable appendLine(Appendable r0, char r1) {
        return r0.append(r1).append('\n');
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    @InlineOnly
    private static final Appendable appendLine(Appendable r0, CharSequence r1) {
        return r0.append(r1).append('\n');
    }

    @SinceKotlin(version = "1.4")
    @IgnorableReturnValue
    public static final <T extends Appendable> T appendRange(T r0, CharSequence r1, int r2, int r3) {
        return (T) r0.append(r1, r2, r3);
    }
}
