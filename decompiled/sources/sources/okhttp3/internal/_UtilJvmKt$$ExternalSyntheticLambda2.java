package okhttp3.internal;

import okhttp3.Call;
import okhttp3.EventListener;

/* loaded from: classes.dex */
public final /* synthetic */ class _UtilJvmKt$$ExternalSyntheticLambda2 implements EventListener.Factory {
    public final /* synthetic */ EventListener f$0;

    public /* synthetic */ _UtilJvmKt$$ExternalSyntheticLambda2(EventListener r1) {
        this.f$0 = r1;
    }

    @Override // okhttp3.EventListener.Factory
    public final EventListener create(Call r1) {
        return _UtilJvmKt.$r8$lambda$K99WxJwzokotbMqA93v0Qgw0Pe0(this.f$0, r1);
    }
}
