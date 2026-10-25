package androidx.camera.camera2.pipe.core;

import java.util.concurrent.ThreadFactory;
import kotlinx.atomicfu.AtomicInt;

/* loaded from: classes3.dex */
public final /* synthetic */ class AndroidThreads$$ExternalSyntheticLambda1 implements ThreadFactory {
    public final /* synthetic */ ThreadFactory f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ AtomicInt f$2;

    public /* synthetic */ AndroidThreads$$ExternalSyntheticLambda1(ThreadFactory r1, String r2, AtomicInt r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable r3) {
        return AndroidThreads.$r8$lambda$faLdKNGt__5T6WxQVV9GLghIoxw(this.f$0, this.f$1, this.f$2, r3);
    }
}
