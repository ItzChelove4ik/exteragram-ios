package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\u0088\u0004\u001a3\u0010\u0004\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0007H\u0087\u0088\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\b\u001aH\u0010\u0004\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\u0005*\u0002H\t2\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u00050\n¢\u0006\u0002\b\u000bH\u0087\u0088\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\f\u001aL\u0010\r\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u000e\u001a\u0002H\t2\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u00050\n¢\u0006\u0002\b\u000bH\u0087\u0088\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\f\u001aB\u0010\u000f\u001a\u0002H\t\"\u0004\b\u0000\u0010\t*\u0002H\t2\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\u00100\n¢\u0006\u0002\b\u000bH\u0087\u0088\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\f\u001a=\u0010\u0011\u001a\u0002H\t\"\u0004\b\u0000\u0010\t*\u0002H\t2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\u00100\nH\u0087\u0088\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\f\u001aC\u0010\u0012\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\t\"\u0004\b\u0001\u0010\u0005*\u0002H\t2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u00050\nH\u0087\u0088\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\f\u001a?\u0010\u0013\u001a\u0004\u0018\u0001H\t\"\u0004\b\u0000\u0010\t*\u0002H\t2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\u00150\nH\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\f\u001a?\u0010\u0016\u001a\u0004\u0018\u0001H\t\"\u0004\b\u0000\u0010\t*\u0002H\t2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\u00150\nH\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\f\u001a4\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00192\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00100\nH\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001b"}, d2 = {"TODO", _UrlKt.FRAGMENT_ENCODE_SET, "reason", _UrlKt.FRAGMENT_ENCODE_SET, "run", "R", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "T", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "with", "receiver", "apply", _UrlKt.FRAGMENT_ENCODE_SET, "also", "let", "takeIf", "predicate", _UrlKt.FRAGMENT_ENCODE_SET, "takeUnless", "repeat", "times", _UrlKt.FRAGMENT_ENCODE_SET, "action", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/StandardKt")
/* loaded from: classes5.dex */
class StandardKt__StandardKt {
    public StandardKt__StandardKt() {
    }

    @InlineOnly
    private static final Void TODO() {
        throw new NotImplementedError(null, 1, null);
    }

    @InlineOnly
    private static final Void TODO(String r3) {
        throw new NotImplementedError("An operation is not implemented: " + r3);
    }

    @SinceKotlin(version = "1.1")
    @IgnorableReturnValue
    @InlineOnly
    private static final <T> T also(T r0, Function1<? super T, Unit> r1) {
        r1.invoke(r0);
        return r0;
    }

    @IgnorableReturnValue
    @InlineOnly
    private static final <T> T apply(T r0, Function1<? super T, Unit> r1) {
        r1.invoke(r0);
        return r0;
    }

    @IgnorableReturnValue
    @InlineOnly
    private static final <T, R> R let(T r0, Function1<? super T, ? extends R> r1) {
        return r1.invoke(r0);
    }

    @InlineOnly
    private static final void repeat(int r2, Function1<? super Integer, Unit> r3) {
        int r0 = 0;
    L3:
        if (r0 >= r2) goto L5;
        r3.invoke(Integer.valueOf(r0));
        r0 = r0 + 1;
        goto L3
    }

    @IgnorableReturnValue
    @InlineOnly
    private static final <T, R> R run(T r0, Function1<? super T, ? extends R> r1) {
        return r1.invoke(r0);
    }

    @IgnorableReturnValue
    @InlineOnly
    private static final <R> R run(Function0<? extends R> r0) {
        return r0.invoke();
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> T takeIf(T r0, Function1<? super T, Boolean> r1) {
        if (r1.invoke(r0).booleanValue() == false) goto L5;
        return r0;
    L5:
        return null;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> T takeUnless(T r0, Function1<? super T, Boolean> r1) {
        if (r1.invoke(r0).booleanValue() == true) goto L5;
        return r0;
    L5:
        return null;
    }

    @IgnorableReturnValue
    @InlineOnly
    private static final <T, R> R with(T r0, Function1<? super T, ? extends R> r1) {
        return r1.invoke(r0);
    }
}
