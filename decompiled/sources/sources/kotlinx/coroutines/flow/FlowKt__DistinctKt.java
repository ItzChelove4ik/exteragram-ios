package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\u001a#\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\u0004\b\u0002\u0010\u0003\u001aw\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042:\u0010\r\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0007H\u0002¢\u0006\u0004\b\u000e\u0010\u000f\"$\u0010\u0011\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012\"*\u0010\u0013\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\f0\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"T", "Lkotlinx/coroutines/flow/Flow;", "distinctUntilChanged", "(Lkotlinx/coroutines/flow/Flow;)Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "keySelector", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "old", "new", _UrlKt.FRAGMENT_ENCODE_SET, "areEquivalent", "distinctUntilChangedBy$FlowKt__DistinctKt", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "distinctUntilChangedBy", "defaultKeySelector", "Lkotlin/jvm/functions/Function1;", "defaultAreEquivalent", "Lkotlin/jvm/functions/Function2;", "kotlinx-coroutines-core"}, k = 5, mv = {2, 2, 0}, xi = 48, xs = "kotlinx/coroutines/flow/FlowKt")
/* loaded from: classes.dex */
abstract /* synthetic */ class FlowKt__DistinctKt {
    private static final Function2<Object, Object, Boolean> defaultAreEquivalent = null;
    private static final Function1<Object, Object> defaultKeySelector = null;

    public static /* synthetic */ boolean $r8$lambda$HyNbwFSSIUsDpEldrfJmu04QWWQ(Object r0, Object r1) {
        return defaultAreEquivalent$lambda$0$FlowKt__DistinctKt(r0, r1);
    }

    public static /* synthetic */ Object $r8$lambda$mgidQTCZEwPL4AoZywC2sds_69k(Object r0) {
        return defaultKeySelector$lambda$0$FlowKt__DistinctKt(r0);
    }

    static {
        defaultKeySelector = new FlowKt__DistinctKt$$ExternalSyntheticLambda0();
        defaultAreEquivalent = new FlowKt__DistinctKt$$ExternalSyntheticLambda1();
    }

    private static final boolean defaultAreEquivalent$lambda$0$FlowKt__DistinctKt(Object r0, Object r1) {
        return Intrinsics.areEqual(r0, r1);
    }

    private static final Object defaultKeySelector$lambda$0$FlowKt__DistinctKt(Object r0) {
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Flow<T> distinctUntilChanged(Flow<? extends T> r2) {
        if ((r2 instanceof StateFlow) == false) goto L6;
        return r2;
    L6:
        return distinctUntilChangedBy$FlowKt__DistinctKt(r2, defaultKeySelector, defaultAreEquivalent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> Flow<T> distinctUntilChangedBy$FlowKt__DistinctKt(Flow<? extends T> r2, Function1<? super T, ? extends Object> r3, Function2<Object, Object, Boolean> r4) {
        if ((r2 instanceof DistinctFlowImpl) == false) goto L10;
        DistinctFlowImpl r0 = (DistinctFlowImpl) r2;
        if (r0.keySelector != r3) goto L10;
        if (r0.areEquivalent != r4) goto L10;
        return r2;
    L10:
        return new DistinctFlowImpl(r2, r3, r4);
    }
}
