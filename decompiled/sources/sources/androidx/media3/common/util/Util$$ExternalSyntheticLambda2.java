package androidx.media3.common.util;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

/* loaded from: classes3.dex */
public final /* synthetic */ class Util$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ SettableFuture f$0;
    public final /* synthetic */ ListenableFuture f$1;

    public /* synthetic */ Util$$ExternalSyntheticLambda2(SettableFuture r1, ListenableFuture r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Util.$r8$lambda$KzvOxWgSiy5ysENJ2aWOycyi_3s(this.f$0, this.f$1);
    }
}
