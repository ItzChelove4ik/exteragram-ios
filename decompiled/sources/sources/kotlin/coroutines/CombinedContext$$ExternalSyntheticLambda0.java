package kotlin.coroutines;

import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* loaded from: classes5.dex */
public final /* synthetic */ class CombinedContext$$ExternalSyntheticLambda0 implements Function2 {
    public final /* synthetic */ CoroutineContext[] f$0;
    public final /* synthetic */ Ref.IntRef f$1;

    public /* synthetic */ CombinedContext$$ExternalSyntheticLambda0(CoroutineContext[] r1, Ref.IntRef r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object r2, Object r3) {
        return CombinedContext.m3235$r8$lambda$VpvYuysrEyElHo8MIXshfDY23U(this.f$0, this.f$1, (Unit) r2, (CoroutineContext.Element) r3);
    }
}
