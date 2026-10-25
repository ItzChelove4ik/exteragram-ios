package kotlinx.coroutines.flow.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public final /* synthetic */ class SafeCollector_commonKt$$ExternalSyntheticLambda0 implements Function2 {
    public final /* synthetic */ SafeCollector f$0;

    public /* synthetic */ SafeCollector_commonKt$$ExternalSyntheticLambda0(SafeCollector r1) {
        this.f$0 = r1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object r1, Object r2) {
        return Integer.valueOf(SafeCollector_commonKt.m3553$r8$lambda$0mv1vIMKs5GqhpegvxZ5k4J09Y(this.f$0, ((Integer) r1).intValue(), (CoroutineContext.Element) r2));
    }
}
