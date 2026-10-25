package kotlin;

import c.f$$ExternalSyntheticBUOutline1;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.RandomKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\u0088\u0004\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a.\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a0\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\bH\u0087\u0088\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\t\u001aA\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\u0088\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\n\u001a\u001d\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\u0088\u0004\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a.\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\b\n\u0006\b\u0000\u001a\u0002\u0010\u0001\u001a0\u0010\f\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\bH\u0087\u0088\b\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\t\u001aA\u0010\f\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001a\u0004\u0018\u0001H\b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\u0088\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0000\u001a\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\n\u001a\u0012\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H\u0087\u0088\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0010"}, d2 = {"require", _UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, "lazyMessage", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "requireNotNull", "T", "(Ljava/lang/Object;)Ljava/lang/Object;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "check", "checkNotNull", "error", _UrlKt.FRAGMENT_ENCODE_SET, "message", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/PreconditionsKt")
@SourceDebugExtension({"SMAP\nPreconditions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Preconditions.kt\nkotlin/PreconditionsKt__PreconditionsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,150:1\n1#2:151\n*E\n"})
/* loaded from: classes5.dex */
class PreconditionsKt__PreconditionsKt extends PreconditionsKt__AssertionsJVMKt {
    public PreconditionsKt__PreconditionsKt() {
    }

    @InlineOnly
    private static final void check(boolean r0) {
        if (r0 == false) goto L4;
        return;
    L4:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
    }

    @InlineOnly
    private static final void check(boolean r0, Function0<? extends Object> r1) {
        if (r0 == false) goto L5;
        return;
    L5:
        throw new IllegalStateException(r1.invoke().toString());
    }

    @IgnorableReturnValue
    @InlineOnly
    private static final <T> T checkNotNull(T r0) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    }

    @IgnorableReturnValue
    @InlineOnly
    private static final <T> T checkNotNull(T r0, Function0<? extends Object> r1) {
        if (r0 == null) goto L5;
        return r0;
    L5:
        throw new IllegalStateException(r1.invoke().toString());
    }

    @InlineOnly
    private static final Void error(Object r1) {
        throw new IllegalStateException(r1.toString());
    }

    @InlineOnly
    private static final void require(boolean r0) {
        if (r0 == false) goto L4;
        return;
    L4:
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
    }

    @InlineOnly
    private static final void require(boolean r0, Function0<? extends Object> r1) {
        if (r0 == false) goto L4;
        return;
    L4:
        RandomKt$$ExternalSyntheticBUOutline0.m(r1.invoke());
    }

    @IgnorableReturnValue
    @InlineOnly
    private static final <T> T requireNotNull(T r0) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        f$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    }

    @IgnorableReturnValue
    @InlineOnly
    private static final <T> T requireNotNull(T r0, Function0<? extends Object> r1) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        RandomKt$$ExternalSyntheticBUOutline0.m(r1.invoke());
        return null;
    }
}
