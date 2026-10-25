package androidx.emoji2.text;

import java.util.concurrent.ThreadFactory;

/* loaded from: classes.dex */
public final /* synthetic */ class ConcurrencyHelpers$$ExternalSyntheticLambda0 implements ThreadFactory {
    public final /* synthetic */ String f$0;

    public /* synthetic */ ConcurrencyHelpers$$ExternalSyntheticLambda0(String r1) {
        this.f$0 = r1;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable r1) {
        return ConcurrencyHelpers.$r8$lambda$hDWNjMgTS47ccxPkL8ebwFGVHg4(this.f$0, r1);
    }
}
