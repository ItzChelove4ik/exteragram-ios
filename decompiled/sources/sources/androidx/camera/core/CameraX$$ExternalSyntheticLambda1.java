package androidx.camera.core;

import android.content.Context;
import androidx.concurrent.futures.CallbackToFutureAdapter;

/* loaded from: classes3.dex */
public final /* synthetic */ class CameraX$$ExternalSyntheticLambda1 implements CallbackToFutureAdapter.Resolver {
    public final /* synthetic */ CameraX f$0;
    public final /* synthetic */ Context f$1;

    public /* synthetic */ CameraX$$ExternalSyntheticLambda1(CameraX r1, Context r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
    public final Object attachCompleter(CallbackToFutureAdapter.Completer r2) {
        return CameraX.$r8$lambda$gLtygYQ6ncqgp9GL3DZrb6RJSyE(this.f$0, this.f$1, r2);
    }
}
