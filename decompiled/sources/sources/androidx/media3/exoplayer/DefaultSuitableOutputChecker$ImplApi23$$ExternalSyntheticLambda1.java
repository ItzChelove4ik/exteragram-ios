package androidx.media3.exoplayer;

import androidx.media3.common.util.BackgroundThreadStateHandler;
import androidx.media3.exoplayer.DefaultSuitableOutputChecker;
import androidx.media3.exoplayer.SuitableOutputChecker;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultSuitableOutputChecker$ImplApi23$$ExternalSyntheticLambda1 implements BackgroundThreadStateHandler.StateChangeListener {
    public final /* synthetic */ SuitableOutputChecker.Callback f$0;

    public /* synthetic */ DefaultSuitableOutputChecker$ImplApi23$$ExternalSyntheticLambda1(SuitableOutputChecker.Callback r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.common.util.BackgroundThreadStateHandler.StateChangeListener
    public final void onStateChanged(Object r1, Object r2) {
        DefaultSuitableOutputChecker.ImplApi23.$r8$lambda$K0yPnH7TYWkiAnvFwq9ZTUz0tso(this.f$0, (Boolean) r1, (Boolean) r2);
    }
}
