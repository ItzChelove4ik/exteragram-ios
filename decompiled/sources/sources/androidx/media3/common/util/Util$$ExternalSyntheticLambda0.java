package androidx.media3.common.util;

import java.util.concurrent.ThreadFactory;

/* loaded from: classes3.dex */
public final /* synthetic */ class Util$$ExternalSyntheticLambda0 implements ThreadFactory {
    public final /* synthetic */ String f$0;

    public /* synthetic */ Util$$ExternalSyntheticLambda0(String r1) {
        this.f$0 = r1;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable r1) {
        return Util.$r8$lambda$wGFACl6UrrkGCKSFfJQ56NYMqbg(this.f$0, r1);
    }
}
