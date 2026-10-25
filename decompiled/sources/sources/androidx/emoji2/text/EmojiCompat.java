package androidx.emoji2.text;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.collection.ArraySet;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes.dex */
public class EmojiCompat {
    private static final Object CONFIG_LOCK = null;
    private static final Object INSTANCE_LOCK = null;
    private static volatile EmojiCompat sInstance;
    final int[] mEmojiAsDefaultStyleExceptions;
    private final int mEmojiSpanIndicatorColor;
    private final boolean mEmojiSpanIndicatorEnabled;
    private final GlyphChecker mGlyphChecker;
    private final CompatInternal mHelper;
    private final Set<InitCallback> mInitCallbacks;
    private final ReadWriteLock mInitLock;
    private volatile int mLoadState;
    private final Handler mMainHandler;
    private final int mMetadataLoadStrategy;
    final MetadataRepoLoader mMetadataLoader;
    final boolean mReplaceAll;
    private final SpanFactory mSpanFactory;
    final boolean mUseEmojiAsDefaultStyle;

    public static class CompatInternal {
        final EmojiCompat mEmojiCompat;

        public CompatInternal(EmojiCompat r1) {
            this.mEmojiCompat = r1;
        }

        public abstract void loadMetadata();

        public abstract CharSequence process(CharSequence r1, int r2, int r3, int r4, boolean r5);

        public abstract void updateEditorInfoAttrs(EditorInfo r1);
    }

    public static final class CompatInternal19 extends CompatInternal {
        private volatile MetadataRepo mMetadataRepo;
        private volatile EmojiProcessor mProcessor;

        /* renamed from: androidx.emoji2.text.EmojiCompat$CompatInternal19$1, reason: invalid class name */
        public class AnonymousClass1 extends MetadataRepoLoaderCallback {
            final /* synthetic */ CompatInternal19 this$0;

            public AnonymousClass1(CompatInternal19 r1) {
                this.this$0 = r1;
            }

            @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoaderCallback
            public void onFailed(Throwable r1) {
                this.this$0.mEmojiCompat.onMetadataLoadFailed(r1);
            }

            @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoaderCallback
            public void onLoaded(MetadataRepo r1) {
                this.this$0.onMetadataLoadSuccess(r1);
            }
        }

        public CompatInternal19(EmojiCompat r1) {
            super(r1);
        }

        @Override // androidx.emoji2.text.EmojiCompat.CompatInternal
        public void loadMetadata() {
            AnonymousClass1 r0 = new AnonymousClass1(this);     // Catch: Throwable -> L4
            this.mEmojiCompat.mMetadataLoader.load(r0);     // Catch: Throwable -> L4
            return;
        L4:
            th = move-exception;
            this.mEmojiCompat.onMetadataLoadFailed(th);
        }

        public void onMetadataLoadSuccess(MetadataRepo r9) {
            if (r9 != null) goto L5;
            this.mEmojiCompat.onMetadataLoadFailed(new IllegalArgumentException("metadataRepo cannot be null"));
            return;
        L5:
            this.mMetadataRepo = r9;
            MetadataRepo r2 = this.mMetadataRepo;
            SpanFactory r3 = EmojiCompat.access$000(this.mEmojiCompat);
            GlyphChecker r4 = EmojiCompat.access$100(this.mEmojiCompat);
            EmojiCompat r92 = this.mEmojiCompat;
            this.mProcessor = new EmojiProcessor(r2, r3, r4, r92.mUseEmojiAsDefaultStyle, r92.mEmojiAsDefaultStyleExceptions, EmojiExclusions.getEmojiExclusions());
            this.mEmojiCompat.onMetadataLoadSuccess();
        }

        @Override // androidx.emoji2.text.EmojiCompat.CompatInternal
        public CharSequence process(CharSequence r1, int r2, int r3, int r4, boolean r5) {
            return this.mProcessor.process(r1, r2, r3, r4, r5);
        }

        @Override // androidx.emoji2.text.EmojiCompat.CompatInternal
        public void updateEditorInfoAttrs(EditorInfo r4) {
            r4.extras.putInt("android.support.text.emoji.emojiCompat_metadataVersion", this.mMetadataRepo.getMetadataVersion());
            r4.extras.putBoolean("android.support.text.emoji.emojiCompat_replaceAll", this.mEmojiCompat.mReplaceAll);
        }
    }

    public static abstract class Config {
        int[] mEmojiAsDefaultStyleExceptions;
        int mEmojiSpanIndicatorColor;
        boolean mEmojiSpanIndicatorEnabled;
        GlyphChecker mGlyphChecker;
        Set<InitCallback> mInitCallbacks;
        int mMetadataLoadStrategy;
        final MetadataRepoLoader mMetadataLoader;
        boolean mReplaceAll;
        SpanFactory mSpanFactory;
        boolean mUseEmojiAsDefaultStyle;

        public Config(MetadataRepoLoader r2) {
            this.mEmojiSpanIndicatorColor = -16711936;
            this.mMetadataLoadStrategy = 0;
            this.mGlyphChecker = new DefaultGlyphChecker();
            Preconditions.checkNotNull(r2, "metadataLoader cannot be null.");
            this.mMetadataLoader = r2;
        }

        public final MetadataRepoLoader getMetadataRepoLoader() {
            return this.mMetadataLoader;
        }

        public Config setMetadataLoadStrategy(int r1) {
            this.mMetadataLoadStrategy = r1;
            return this;
        }
    }

    public static class DefaultSpanFactory implements SpanFactory {
        public DefaultSpanFactory() {
        }

        @Override // androidx.emoji2.text.EmojiCompat.SpanFactory
        public EmojiSpan createSpan(TypefaceEmojiRasterizer r1) {
            return new TypefaceEmojiSpan(r1);
        }
    }

    public interface GlyphChecker {
        boolean hasGlyph(CharSequence r1, int r2, int r3, int r4);
    }

    /* loaded from: classes3.dex */
    public static abstract class InitCallback {
        public InitCallback() {
        }

        public void onFailed(Throwable r1) {
        }

        public void onInitialized() {
        }
    }

    public static class ListenerDispatcher implements Runnable {
        private final List<InitCallback> mInitCallbacks;
        private final int mLoadState;
        private final Throwable mThrowable;

        public ListenerDispatcher(InitCallback r2, int r3) {
            this(Arrays.asList(new InitCallback[]{(InitCallback) Preconditions.checkNotNull(r2, "initCallback cannot be null")}), r3, null);
        }

        public ListenerDispatcher(Collection<InitCallback> r2, int r3) {
            this(r2, r3, null);
        }

        public ListenerDispatcher(Collection<InitCallback> r2, int r3, Throwable r4) {
            Preconditions.checkNotNull(r2, "initCallbacks cannot be null");
            this.mInitCallbacks = new ArrayList(r2);
            this.mLoadState = r3;
            this.mThrowable = r4;
        }

        @Override // java.lang.Runnable
        public void run() {
            int r0 = this.mInitCallbacks.size();
            int r3 = 0;
            if (this.mLoadState == 1) goto L6;
        L4:
            if (r3 >= r0) goto L8;
            this.mInitCallbacks.get(r3).onFailed(this.mThrowable);
            r3 = r3 + 1;
            goto L4
        L8:
            return;
        L6:
            if (r3 >= r0) goto L11;
            this.mInitCallbacks.get(r3).onInitialized();
            r3 = r3 + 1;
            goto L6
        }
    }

    public interface MetadataRepoLoader {
        void load(MetadataRepoLoaderCallback r1);
    }

    public static abstract class MetadataRepoLoaderCallback {
        public MetadataRepoLoaderCallback() {
        }

        public abstract void onFailed(Throwable r1);

        public abstract void onLoaded(MetadataRepo r1);
    }

    public interface SpanFactory {
        EmojiSpan createSpan(TypefaceEmojiRasterizer r1);
    }

    static {
        INSTANCE_LOCK = new Object();
        CONFIG_LOCK = new Object();
    }

    private EmojiCompat(Config r3) {
        this.mInitLock = new ReentrantReadWriteLock();
        this.mLoadState = 3;
        this.mReplaceAll = r3.mReplaceAll;
        this.mUseEmojiAsDefaultStyle = r3.mUseEmojiAsDefaultStyle;
        this.mEmojiAsDefaultStyleExceptions = r3.mEmojiAsDefaultStyleExceptions;
        this.mEmojiSpanIndicatorEnabled = r3.mEmojiSpanIndicatorEnabled;
        this.mEmojiSpanIndicatorColor = r3.mEmojiSpanIndicatorColor;
        this.mMetadataLoader = r3.mMetadataLoader;
        this.mMetadataLoadStrategy = r3.mMetadataLoadStrategy;
        this.mGlyphChecker = r3.mGlyphChecker;
        this.mMainHandler = new Handler(Looper.getMainLooper());
        ArraySet r0 = new ArraySet();
        this.mInitCallbacks = r0;
        SpanFactory r1 = r3.mSpanFactory;
        if (r1 != null) goto L6;
        r1 = new DefaultSpanFactory();
    L6:
        this.mSpanFactory = r1;
        Set<InitCallback> r12 = r3.mInitCallbacks;
        if (r12 != null) goto L9;
    L11:
        this.mHelper = new CompatInternal19(this);
        loadMetadata();
        return;
    L9:
        if (r12.isEmpty() == true) goto L11;
        r0.addAll(r3.mInitCallbacks);
        goto L11
    }

    public static /* synthetic */ SpanFactory access$000(EmojiCompat r0) {
        return r0.mSpanFactory;
    }

    public static /* synthetic */ GlyphChecker access$100(EmojiCompat r0) {
        return r0.mGlyphChecker;
    }

    public static EmojiCompat get() {
        Object r0 = INSTANCE_LOCK;
        monitor-enter(r0);
        EmojiCompat r1 = sInstance;     // Catch: Throwable -> L11
        if (r1 == null) goto L7;
        boolean r2 = true;
    L8:
        Preconditions.checkState(r2, "EmojiCompat is not initialized.\n\nYou must initialize EmojiCompat prior to referencing the EmojiCompat instance.\n\nThe most likely cause of this error is disabling the EmojiCompatInitializer\neither explicitly in AndroidManifest.xml, or by including\nandroidx.emoji2:emoji2-bundled.\n\nAutomatic initialization is typically performed by EmojiCompatInitializer. If\nyou are not expecting to initialize EmojiCompat manually in your application,\nplease check to ensure it has not been removed from your APK's manifest. You can\ndo this in Android Studio using Build > Analyze APK.\n\nIn the APK Analyzer, ensure that the startup entry for\nEmojiCompatInitializer and InitializationProvider is present in\n AndroidManifest.xml. If it is missing or contains tools:node=\"remove\", and you\nintend to use automatic configuration, verify:\n\n  1. Your application does not include emoji2-bundled\n  2. All modules do not contain an exclusion manifest rule for\n     EmojiCompatInitializer or InitializationProvider. For more information\n     about manifest exclusions see the documentation for the androidx startup\n     library.\n\nIf you intend to use emoji2-bundled, please call EmojiCompat.init. You can\nlearn more in the documentation for BundledEmojiCompatConfig.\n\nIf you intended to perform manual configuration, it is recommended that you call\nEmojiCompat.init immediately on application startup.\n\nIf you still cannot resolve this issue, please open a bug with your specific\nconfiguration to help improve error message.");     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r1;
    L7:
        r2 = false;
    L11:
        th = move-exception;
        throw th;
    }

    public static boolean handleDeleteSurroundingText(InputConnection r0, Editable r1, int r2, int r3, boolean r4) {
        return EmojiProcessor.handleDeleteSurroundingText(r0, r1, r2, r3, r4);
    }

    public static boolean handleOnKeyDown(Editable r0, int r1, KeyEvent r2) {
        return EmojiProcessor.handleOnKeyDown(r0, r1, r2);
    }

    public static EmojiCompat init(Config r2) {
        EmojiCompat r0 = sInstance;
        if (r0 != null) goto L15;
        Object r1 = INSTANCE_LOCK;
        monitor-enter(r1);
        EmojiCompat r02 = sInstance;     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        r02 = new EmojiCompat(r2);     // Catch: Throwable -> L9
        sInstance = r02;     // Catch: Throwable -> L9
    L11:
        monitor-exit(r1);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    L15:
        return r0;
    }

    public static boolean isConfigured() {
        if (sInstance == null) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isInitialized() {
        if (getLoadState() != 1) goto L5;
        return true;
    L5:
        return false;
    }

    private void loadMetadata() {
        this.mInitLock.writeLock().lock();
    L7:
        th = move-exception;
        this.mInitLock.writeLock().unlock();
        throw th;
    L4:
        if (this.mMetadataLoadStrategy != 0) goto L9;
        this.mLoadState = 0;     // Catch: Throwable -> L7
    L9:
        this.mInitLock.writeLock().unlock();
        if (getLoadState() != 0) goto L17;
        this.mHelper.loadMetadata();
        return;
    }

    public int getEmojiSpanIndicatorColor() {
        return this.mEmojiSpanIndicatorColor;
    }

    public int getLoadState() {
        this.mInitLock.readLock().lock();
        int r0 = this.mLoadState;     // Catch: Throwable -> L6
        this.mInitLock.readLock().unlock();
        return r0;
    L6:
        th = move-exception;
        this.mInitLock.readLock().unlock();
        throw th;
    }

    public boolean isEmojiSpanIndicatorEnabled() {
        return this.mEmojiSpanIndicatorEnabled;
    }

    public void load() {
        boolean r2 = true;
        if (this.mMetadataLoadStrategy == 1) goto L6;
        r2 = false;
    L6:
        Preconditions.checkState(r2, "Set metadataLoadStrategy to LOAD_STRATEGY_MANUAL to execute manual loading");
        if (isInitialized() == false) goto L9;
        return;
    L9:
        this.mInitLock.writeLock().lock();
    L17:
        th = move-exception;
        this.mInitLock.writeLock().unlock();
        throw th;
    L11:
        if (this.mLoadState != 0) goto L14;
        this.mInitLock.writeLock().unlock();
        return;
    L14:
        this.mLoadState = 0;     // Catch: Throwable -> L17
        this.mInitLock.writeLock().unlock();
        this.mHelper.loadMetadata();
    }

    public void onMetadataLoadFailed(Throwable r4) {
        ArrayList r0 = new ArrayList();
        this.mInitLock.writeLock().lock();
        this.mLoadState = 2;     // Catch: Throwable -> L6
        r0.addAll(this.mInitCallbacks);     // Catch: Throwable -> L6
        this.mInitCallbacks.clear();     // Catch: Throwable -> L6
        this.mInitLock.writeLock().unlock();
        this.mMainHandler.post(new ListenerDispatcher(r0, this.mLoadState, r4));
        return;
    L6:
        th = move-exception;
        this.mInitLock.writeLock().unlock();
        throw th;
    }

    public void onMetadataLoadSuccess() {
        ArrayList r0 = new ArrayList();
        this.mInitLock.writeLock().lock();
        this.mLoadState = 1;     // Catch: Throwable -> L6
        r0.addAll(this.mInitCallbacks);     // Catch: Throwable -> L6
        this.mInitCallbacks.clear();     // Catch: Throwable -> L6
        this.mInitLock.writeLock().unlock();
        this.mMainHandler.post(new ListenerDispatcher(r0, this.mLoadState));
        return;
    L6:
        th = move-exception;
        this.mInitLock.writeLock().unlock();
        throw th;
    }

    public CharSequence process(CharSequence r3) {
        if (r3 != null) goto L5;
        int r1 = 0;
    L7:
        return process(r3, 0, r1);
    L5:
        r1 = r3.length();
        goto L7
    }

    public CharSequence process(CharSequence r2, int r3, int r4) {
        return process(r2, r3, r4, Integer.MAX_VALUE);
    }

    public CharSequence process(CharSequence r7, int r8, int r9, int r10) {
        return process(r7, r8, r9, r10, 0);
    }

    public CharSequence process(CharSequence r5, int r6, int r7, int r8, int r9) {
        Preconditions.checkState(isInitialized(), "Not initialized yet");
        Preconditions.checkArgumentNonnegative(r6, "start cannot be negative");
        Preconditions.checkArgumentNonnegative(r7, "end cannot be negative");
        Preconditions.checkArgumentNonnegative(r8, "maxEmojiCount cannot be negative");
        boolean r0 = false;
        if (r6 > r7) goto L5;
        boolean r2 = true;
    L6:
        Preconditions.checkArgument(r2, "start should be <= than end");
        if (r5 != null) goto L11;
        return null;
    L11:
        if (r6 > r5.length()) goto L13;
        boolean r22 = true;
    L14:
        Preconditions.checkArgument(r22, "start should be < than charSequence length");
        if (r7 > r5.length()) goto L17;
        boolean r23 = true;
    L18:
        Preconditions.checkArgument(r23, "end should be < than charSequence length");
        if (r5.length() == 0) goto L30;
        if (r6 == r7) goto L30;
        if (r9 != 1) goto L24;
        boolean r92 = true;
    L29:
        return this.mHelper.process(r5, r6, r7, r8, r92);
    L24:
        if (r9 == 2) goto L26;
        r0 = this.mReplaceAll;
    L26:
        r92 = r0;
    L30:
        return r5;
    L17:
        r23 = false;
        goto L18
    L13:
        r22 = false;
        goto L14
    L5:
        r2 = false;
        goto L6
    }

    public void registerInitCallback(InitCallback r4) {
        Preconditions.checkNotNull(r4, "initCallback cannot be null");
        this.mInitLock.writeLock().lock();
    L9:
        th = move-exception;
        this.mInitLock.writeLock().unlock();
        throw th;
    L4:
        if (this.mLoadState != 1) goto L6;
    L11:
        this.mMainHandler.post(new ListenerDispatcher(r4, this.mLoadState));     // Catch: Throwable -> L9
    L12:
        this.mInitLock.writeLock().unlock();
        return;
    L6:
        if (this.mLoadState == 2) goto L11;
        this.mInitCallbacks.add(r4);     // Catch: Throwable -> L9
        goto L12
    }

    public void unregisterInitCallback(InitCallback r2) {
        Preconditions.checkNotNull(r2, "initCallback cannot be null");
        this.mInitLock.writeLock().lock();
        this.mInitCallbacks.remove(r2);     // Catch: Throwable -> L6
        this.mInitLock.writeLock().unlock();
        return;
    L6:
        th = move-exception;
        this.mInitLock.writeLock().unlock();
        throw th;
    }

    public void updateEditorInfo(EditorInfo r2) {
        if (isInitialized() == false) goto L11;
        if (r2 != null) goto L7;
        return;
    L7:
        if (r2.extras != null) goto L9;
        r2.extras = new Bundle();
    L9:
        this.mHelper.updateEditorInfoAttrs(r2);
        return;
    }
}
