package kotlin.sequences;

import java.util.Iterator;
import kotlin.BuilderInference;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aL\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022/\b\u0001\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\tH\u0087\u0080\u0004¢\u0006\u0002\u0010\n\u001aL\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\f\"\u0004\b\u0000\u0010\u00022/\b\u0001\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\tH\u0087\u0080\u0004¢\u0006\u0002\u0010\r\"\u0013\u0010\u0010\u001a\u00060\u000fj\u0002`\u0011X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u0013\u0010\u0012\u001a\u00060\u000fj\u0002`\u0011X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u0013\u0010\u0013\u001a\u00060\u000fj\u0002`\u0011X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u0013\u0010\u0014\u001a\u00060\u000fj\u0002`\u0011X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u0013\u0010\u0015\u001a\u00060\u000fj\u0002`\u0011X\u0082Ô\b¢\u0006\u0002\n\u0000\"\u0013\u0010\u0016\u001a\u00060\u000fj\u0002`\u0011X\u0082Ô\b¢\u0006\u0002\n\u0000*\f\b\u0002\u0010\u000e\"\u00020\u000f2\u00020\u000f¨\u0006\u0017"}, d2 = {"sequence", "Lkotlin/sequences/Sequence;", "T", "block", "Lkotlin/Function2;", "Lkotlin/sequences/SequenceScope;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)Lkotlin/sequences/Sequence;", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "(Lkotlin/jvm/functions/Function2;)Ljava/util/Iterator;", "State", _UrlKt.FRAGMENT_ENCODE_SET, "State_NotReady", "Lkotlin/sequences/State;", "State_ManyNotReady", "State_ManyReady", "State_Ready", "State_Done", "State_Failed", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/sequences/SequencesKt")
/* loaded from: classes.dex */
class SequencesKt__SequenceBuilderKt {
    private static final int State_Done = 4;
    private static final int State_Failed = 5;
    private static final int State_ManyNotReady = 1;
    private static final int State_ManyReady = 2;
    private static final int State_NotReady = 0;
    private static final int State_Ready = 3;

    public SequencesKt__SequenceBuilderKt() {
    }

    @SinceKotlin(version = "1.3")
    public static <T> Iterator<T> iterator(@BuilderInference Function2<? super SequenceScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> r1) {
        SequenceBuilderIterator r0 = new SequenceBuilderIterator();
        r0.setNextStep(IntrinsicsKt.createCoroutineUnintercepted(r1, r0, r0));
        return r0;
    }

    @SinceKotlin(version = "1.3")
    public static <T> Sequence<T> sequence(@BuilderInference Function2<? super SequenceScope<? super T>, ? super Continuation<? super Unit>, ? extends Object> r1) {
        return new SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1(r1);
    }
}
