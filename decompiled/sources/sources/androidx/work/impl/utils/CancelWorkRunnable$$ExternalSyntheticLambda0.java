package androidx.work.impl.utils;

import androidx.work.impl.WorkManagerImpl;
import java.util.UUID;
import kotlin.jvm.functions.Function0;

/* loaded from: classes4.dex */
public final /* synthetic */ class CancelWorkRunnable$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ WorkManagerImpl f$0;
    public final /* synthetic */ UUID f$1;

    public /* synthetic */ CancelWorkRunnable$$ExternalSyntheticLambda0(WorkManagerImpl r1, UUID r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return CancelWorkRunnable.$r8$lambda$8EsI_obE8bQExJ_QYqMsYGJK8YY(this.f$0, this.f$1);
    }
}
