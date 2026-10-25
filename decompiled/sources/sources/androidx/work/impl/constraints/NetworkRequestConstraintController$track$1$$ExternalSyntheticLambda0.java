package androidx.work.impl.constraints;

import androidx.work.impl.constraints.NetworkRequestConstraintController;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ProducerScope;

/* loaded from: classes4.dex */
public final /* synthetic */ class NetworkRequestConstraintController$track$1$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ Job f$0;
    public final /* synthetic */ ProducerScope f$1;

    public /* synthetic */ NetworkRequestConstraintController$track$1$$ExternalSyntheticLambda0(Job r1, ProducerScope r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return NetworkRequestConstraintController.AnonymousClass1.$r8$lambda$U9H9NeuZSZvBxANXmk1Y2QUyWkY(this.f$0, this.f$1, (ConstraintsState) r2);
    }
}
