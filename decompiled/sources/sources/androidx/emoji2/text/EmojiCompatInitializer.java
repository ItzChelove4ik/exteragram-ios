package androidx.emoji2.text;

import android.content.Context;
import androidx.core.os.TraceCompat;
import androidx.emoji2.text.EmojiCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleInitializer;
import androidx.startup.AppInitializer;
import androidx.startup.Initializer;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/* loaded from: classes.dex */
public class EmojiCompatInitializer implements Initializer<Boolean> {

    /* renamed from: androidx.emoji2.text.EmojiCompatInitializer$1, reason: invalid class name */
    public class AnonymousClass1 implements DefaultLifecycleObserver {
        final /* synthetic */ EmojiCompatInitializer this$0;
        final /* synthetic */ Lifecycle val$lifecycle;

        public AnonymousClass1(EmojiCompatInitializer r1, Lifecycle r2) {
            this.this$0 = r1;
            this.val$lifecycle = r2;
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onResume(LifecycleOwner r1) {
            this.this$0.loadEmojiCompatAfterDelay();
            this.val$lifecycle.removeObserver(this);
        }
    }

    public static class BackgroundDefaultConfig extends EmojiCompat.Config {
        public BackgroundDefaultConfig(Context r2) {
            super(new BackgroundDefaultLoader(r2));
            setMetadataLoadStrategy(1);
        }
    }

    public static class BackgroundDefaultLoader implements EmojiCompat.MetadataRepoLoader {
        private final Context mContext;

        /* renamed from: androidx.emoji2.text.EmojiCompatInitializer$BackgroundDefaultLoader$1, reason: invalid class name */
        public class AnonymousClass1 extends EmojiCompat.MetadataRepoLoaderCallback {
            final /* synthetic */ BackgroundDefaultLoader this$0;
            final /* synthetic */ ThreadPoolExecutor val$executor;
            final /* synthetic */ EmojiCompat.MetadataRepoLoaderCallback val$loaderCallback;

            public AnonymousClass1(BackgroundDefaultLoader r1, EmojiCompat.MetadataRepoLoaderCallback r2, ThreadPoolExecutor r3) {
                this.this$0 = r1;
                this.val$loaderCallback = r2;
                this.val$executor = r3;
            }

            @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoaderCallback
            public void onFailed(Throwable r2) {
                this.val$loaderCallback.onFailed(r2);     // Catch: Throwable -> L5
                this.val$executor.shutdown();
                return;
            L5:
                th = move-exception;
                this.val$executor.shutdown();
                throw th;
            }

            @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoaderCallback
            public void onLoaded(MetadataRepo r2) {
                this.val$loaderCallback.onLoaded(r2);     // Catch: Throwable -> L5
                this.val$executor.shutdown();
                return;
            L5:
                th = move-exception;
                this.val$executor.shutdown();
                throw th;
            }
        }

        /* renamed from: $r8$lambda$55Ivg7v_3m1eazHc6HmoU-ZdULY, reason: not valid java name */
        public static /* synthetic */ void m724$r8$lambda$55Ivg7v_3m1eazHc6HmoUZdULY(BackgroundDefaultLoader r0, EmojiCompat.MetadataRepoLoaderCallback r1, ThreadPoolExecutor r2) {
            r0.doLoad(r1, r2);
        }

        public BackgroundDefaultLoader(Context r1) {
            this.mContext = r1.getApplicationContext();
        }

        public void doLoad(EmojiCompat.MetadataRepoLoaderCallback r3, ThreadPoolExecutor r4) {
            FontRequestEmojiCompatConfig r0 = DefaultEmojiCompatConfig.create(this.mContext);     // Catch: Throwable -> L6
            if (r0 == null) goto L9;
            r0.setLoadingExecutor(r4);     // Catch: Throwable -> L6
            r0.getMetadataRepoLoader().load(new AnonymousClass1(this, r3, r4));     // Catch: Throwable -> L6
            return;
        L9:
            throw new RuntimeException("EmojiCompat font provider not available on this device.");     // Catch: Throwable -> L6
        L6:
            th = move-exception;
            r3.onFailed(th);
            r4.shutdown();
        }

        @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoader
        public void load(EmojiCompat.MetadataRepoLoaderCallback r3) {
            ThreadPoolExecutor r0 = ConcurrencyHelpers.createBackgroundPriorityExecutor("EmojiCompatInitializer");
            r0.execute(new EmojiCompatInitializer$BackgroundDefaultLoader$$ExternalSyntheticLambda0(this, r3, r0));
        }
    }

    public static class LoadEmojiCompatRunnable implements Runnable {
        public LoadEmojiCompatRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TraceCompat.beginSection("EmojiCompat.EmojiCompatInitializer.run");     // Catch: Throwable -> L7
            if (EmojiCompat.isConfigured() == false) goto L5;
            EmojiCompat.get().load();     // Catch: Throwable -> L7
        L5:
            TraceCompat.endSection();
            return;
        L7:
            th = move-exception;
            TraceCompat.endSection();
            throw th;
        }
    }

    public EmojiCompatInitializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.startup.Initializer
    public Boolean create(Context r2) {
        EmojiCompat.init(new BackgroundDefaultConfig(r2));
        delayUntilFirstResume(r2);
        return Boolean.TRUE;
    }

    @Override // androidx.startup.Initializer
    public /* bridge */ /* synthetic */ Boolean create(Context r1) {
        return create(r1);
    }

    public void delayUntilFirstResume(Context r2) {
        Lifecycle r22 = ((LifecycleOwner) AppInitializer.getInstance(r2).initializeComponent(ProcessLifecycleInitializer.class)).getLifecycle();
        r22.addObserver(new AnonymousClass1(this, r22));
    }

    @Override // androidx.startup.Initializer
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.singletonList(ProcessLifecycleInitializer.class);
    }

    public void loadEmojiCompatAfterDelay() {
        ConcurrencyHelpers.mainHandlerAsync().postDelayed(new LoadEmojiCompatRunnable(), 500);
    }
}
