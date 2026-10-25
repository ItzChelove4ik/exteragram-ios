package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.lifecycle.MutableLiveData;
import kotlin.jvm.functions.Function0;

/* loaded from: classes.dex */
public final /* synthetic */ class OperationKt$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ Tracer f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Function0 f$2;
    public final /* synthetic */ MutableLiveData f$3;
    public final /* synthetic */ CallbackToFutureAdapter.Completer f$4;

    public /* synthetic */ OperationKt$$ExternalSyntheticLambda1(Tracer r1, String r2, Function0 r3, MutableLiveData r4, CallbackToFutureAdapter.Completer r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        OperationKt.$r8$lambda$XKAkIiEN7OgIvwuLUZRQpJhjmyE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
