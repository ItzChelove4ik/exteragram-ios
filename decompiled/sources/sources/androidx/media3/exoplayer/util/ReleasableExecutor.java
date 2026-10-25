package androidx.media3.exoplayer.util;

import androidx.media3.common.util.Consumer;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public interface ReleasableExecutor extends Executor {

    /* renamed from: androidx.media3.exoplayer.util.ReleasableExecutor$1, reason: invalid class name */
    public class AnonymousClass1 implements ReleasableExecutor {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ Consumer val$releaseCallback;

        public AnonymousClass1(Executor r1, Consumer r2) {
            this.val$executor = r1;
            this.val$releaseCallback = r2;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable r1) {
            this.val$executor.execute(r1);
        }

        @Override // androidx.media3.exoplayer.util.ReleasableExecutor
        public void release() {
            this.val$releaseCallback.accept(this.val$executor);
        }
    }

    static <T extends Executor> ReleasableExecutor from(T r1, Consumer<T> r2) {
        return new AnonymousClass1(r1, r2);
    }

    void release();
}
