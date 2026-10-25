package androidx.media3.common.util;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

/* loaded from: classes3.dex */
public final /* synthetic */ class Util$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ ListenableFuture f$0;
    public final /* synthetic */ SettableFuture f$1;
    public final /* synthetic */ AsyncFunction f$2;

    public /* synthetic */ Util$$ExternalSyntheticLambda3(ListenableFuture r1, SettableFuture r2, AsyncFunction r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Util.m759$r8$lambda$c_pOe3I0ViFZgMkkm3VzBGAMjI(this.f$0, this.f$1, this.f$2);
    }
}
