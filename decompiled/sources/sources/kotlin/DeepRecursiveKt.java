package kotlin;

import kotlin.Result;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a3\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u0087\u0082\u0004¢\u0006\u0002\u0010\u0005\"\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\rX\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\u000e*r\b\u0002\u0010\u0006\"5\b\u0001\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007¢\u0006\u0002\b\u000b25\b\u0001\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007¢\u0006\u0002\b\u000b¨\u0006\u000f"}, d2 = {"invoke", "R", "T", "Lkotlin/DeepRecursiveFunction;", "value", "(Lkotlin/DeepRecursiveFunction;Ljava/lang/Object;)Ljava/lang/Object;", "DeepRecursiveFunctionBlock", "Lkotlin/Function3;", "Lkotlin/DeepRecursiveScope;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "UNDEFINED_RESULT", "Lkotlin/Result;", "Ljava/lang/Object;", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class DeepRecursiveKt {
    private static final Object UNDEFINED_RESULT = null;

    static {
        Result.Companion r0 = Result.Companion;
        UNDEFINED_RESULT = Result.m2018constructorimpl(IntrinsicsKt.getCOROUTINE_SUSPENDED());
    }

    public static final /* synthetic */ Object access$getUNDEFINED_RESULT$p() {
        return UNDEFINED_RESULT;
    }

    @SinceKotlin(version = "1.7")
    public static final <T, R> R invoke(DeepRecursiveFunction<T, R> r1, T r2) {
        return (R) new DeepRecursiveScopeImpl(r1.getBlock$kotlin_stdlib(), r2).runCallLoop();
    }
}
