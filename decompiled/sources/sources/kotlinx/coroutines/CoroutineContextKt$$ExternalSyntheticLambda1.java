package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* loaded from: classes.dex */
public final /* synthetic */ class CoroutineContextKt$$ExternalSyntheticLambda1 implements Function2 {
    public final /* synthetic */ Ref.ObjectRef f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ CoroutineContextKt$$ExternalSyntheticLambda1(Ref.ObjectRef r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object r2, Object r3) {
        return CoroutineContextKt.$r8$lambda$cAutOdCeS_RpPuLeN9mZ0Ij5g4E(this.f$0, this.f$1, (CoroutineContext) r2, (CoroutineContext.Element) r3);
    }
}
