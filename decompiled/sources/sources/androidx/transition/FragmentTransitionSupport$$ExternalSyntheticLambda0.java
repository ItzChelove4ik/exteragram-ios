package androidx.transition;

import androidx.core.os.CancellationSignal;

/* loaded from: classes4.dex */
public final /* synthetic */ class FragmentTransitionSupport$$ExternalSyntheticLambda0 implements CancellationSignal.OnCancelListener {
    public final /* synthetic */ Runnable f$0;
    public final /* synthetic */ Transition f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ FragmentTransitionSupport$$ExternalSyntheticLambda0(Runnable r1, Transition r2, Runnable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.core.os.CancellationSignal.OnCancelListener
    public final void onCancel() {
        FragmentTransitionSupport.$r8$lambda$SbK2lDyNgZgEoNSyIM4zVfrsDvM(this.f$0, this.f$1, this.f$2);
    }
}
