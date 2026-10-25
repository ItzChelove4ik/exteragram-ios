package okhttp3.internal;

import java.util.concurrent.ThreadFactory;

/* loaded from: classes.dex */
public final /* synthetic */ class _UtilJvmKt$$ExternalSyntheticLambda3 implements ThreadFactory {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ _UtilJvmKt$$ExternalSyntheticLambda3(String r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable r2) {
        return _UtilJvmKt.m3744$r8$lambda$_J6QpgacuLmRBBtfmidf27cGU(this.f$0, this.f$1, r2);
    }
}
