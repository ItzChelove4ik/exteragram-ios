package kotlinx.coroutines.android;

import kotlinx.coroutines.DisposableHandle;

/* loaded from: classes5.dex */
public final /* synthetic */ class HandlerContext$$ExternalSyntheticLambda2 implements DisposableHandle {
    public final /* synthetic */ HandlerContext f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ HandlerContext$$ExternalSyntheticLambda2(HandlerContext r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlinx.coroutines.DisposableHandle
    public final void dispose() {
        HandlerContext.$r8$lambda$iEf6rkPOckkawr7Fyn6goXhizTg(this.f$0, this.f$1);
    }
}
