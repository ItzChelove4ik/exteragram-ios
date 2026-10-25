package androidx.datastore.core;

import androidx.datastore.core.MulticastFileObserver;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.DisposableHandle;

/* loaded from: classes.dex */
public final /* synthetic */ class MulticastFileObserver$Companion$$ExternalSyntheticLambda0 implements DisposableHandle {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ Function1 f$1;

    public /* synthetic */ MulticastFileObserver$Companion$$ExternalSyntheticLambda0(String r1, Function1 r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlinx.coroutines.DisposableHandle
    public final void dispose() {
        MulticastFileObserver.Companion.$r8$lambda$RJDrtcZMUHSzp9sK3YDnx03MGSE(this.f$0, this.f$1);
    }
}
