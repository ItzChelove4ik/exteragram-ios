package androidx.emoji2.text;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.graphics.Typeface;
import android.os.Handler;
import androidx.core.graphics.TypefaceCompatUtil;
import androidx.core.os.TraceCompat;
import androidx.core.provider.FontRequest;
import androidx.core.provider.FontsContractCompat;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.EmojiCompat;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public class FontRequestEmojiCompatConfig extends EmojiCompat.Config {
    private static final FontProviderHelper DEFAULT_FONTS_CONTRACT = null;

    public static class FontProviderHelper {
        public FontProviderHelper() {
        }

        public Typeface buildTypeface(Context r1, FontsContractCompat.FontInfo r2) {
            return FontsContractCompat.buildTypeface(r1, null, new FontsContractCompat.FontInfo[]{r2});
        }

        public FontsContractCompat.FontFamilyResult fetchFonts(Context r1, FontRequest r2) {
            return FontsContractCompat.fetchFonts(r1, null, r2);
        }

        public void unregisterObserver(Context r1, ContentObserver r2) {
            r1.getContentResolver().unregisterContentObserver(r2);
        }
    }

    public static class FontRequestMetadataLoader implements EmojiCompat.MetadataRepoLoader {
        EmojiCompat.MetadataRepoLoaderCallback mCallback;
        private final Context mContext;
        private Executor mExecutor;
        private final FontProviderHelper mFontProviderHelper;
        private final Object mLock;
        private Handler mMainHandler;
        private Runnable mMainHandlerLoadCallback;
        private ThreadPoolExecutor mMyThreadPoolExecutor;
        private ContentObserver mObserver;
        private final FontRequest mRequest;

        public FontRequestMetadataLoader(Context r2, FontRequest r3, FontProviderHelper r4) {
            this.mLock = new Object();
            Preconditions.checkNotNull(r2, "Context cannot be null");
            Preconditions.checkNotNull(r3, "FontRequest cannot be null");
            this.mContext = r2.getApplicationContext();
            this.mRequest = r3;
            this.mFontProviderHelper = r4;
        }

        private void cleanUp() {
            Object r0 = this.mLock;
            monitor-enter(r0);
            this.mCallback = null;     // Catch: Throwable -> L8
            ContentObserver r2 = this.mObserver;     // Catch: Throwable -> L8
            if (r2 == null) goto L10;
            this.mFontProviderHelper.unregisterObserver(this.mContext, r2);     // Catch: Throwable -> L8
            this.mObserver = null;     // Catch: Throwable -> L8
        L10:
            Handler r22 = this.mMainHandler;     // Catch: Throwable -> L8
            if (r22 == null) goto L13;
            r22.removeCallbacks(this.mMainHandlerLoadCallback);     // Catch: Throwable -> L8
        L13:
            this.mMainHandler = null;     // Catch: Throwable -> L8
            ThreadPoolExecutor r23 = this.mMyThreadPoolExecutor;     // Catch: Throwable -> L8
            if (r23 == null) goto L16;
            r23.shutdown();     // Catch: Throwable -> L8
        L16:
            this.mExecutor = null;     // Catch: Throwable -> L8
            this.mMyThreadPoolExecutor = null;     // Catch: Throwable -> L8
            monitor-exit(r0);     // Catch: Throwable -> L8
            return;
        L8:
            th = move-exception;
            throw th;
        }

        private FontsContractCompat.FontInfo retrieveFontInfo() {
            FontsContractCompat.FontFamilyResult r3 = this.mFontProviderHelper.fetchFonts(this.mContext, this.mRequest);     // Catch: PackageManager.NameNotFoundException -> L16
            if (r3.getStatusCode() != 0) goto L15;
            FontsContractCompat.FontInfo[] r32 = r3.getFonts();
            if (r32 != null) goto L9;
        L12:
            GlShader$$ExternalSyntheticBUOutline1.m("fetchFonts failed (empty result)");
            return null;
        L9:
            if (r32.length == 0) goto L12;
            return r32[0];
        L15:
            throw new RuntimeException("fetchFonts failed (" + r3.getStatusCode() + ")");
        L16:
            e = move-exception;
            Make$Map$$ExternalSyntheticBUOutline0.m("provider not found", e);
            return null;
        }

        public void createMetadata() {
            Object r1 = this.mLock;
            monitor-enter(r1);
        L8:
            th = move-exception;
            throw th;
        L5:
            if (this.mCallback != null) goto L10;
            monitor-exit(r1);     // Catch: Throwable -> L8
            return;
        L10:
            monitor-exit(r1);     // Catch: Throwable -> L8
            FontsContractCompat.FontInfo r12 = retrieveFontInfo();     // Catch: Throwable -> L20
            int r2 = r12.getResultCode();     // Catch: Throwable -> L20
            if (r2 != 2) goto L22;
            Object r3 = this.mLock;     // Catch: Throwable -> L20
            monitor-enter(r3);     // Catch: Throwable -> L20
            monitor-exit(r3);     // Catch: Throwable -> L17
        L17:
            th = move-exception;
            throw th;     // Catch: Throwable -> L20
        L22:
            if (r2 != 0) goto L46;
            TraceCompat.beginSection("EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface");     // Catch: Throwable -> L39
            Typeface r0 = this.mFontProviderHelper.buildTypeface(this.mContext, r12);     // Catch: Throwable -> L39
            ByteBuffer r13 = TypefaceCompatUtil.mmap(this.mContext, null, r12.getUri());     // Catch: Throwable -> L39
            if (r13 == null) goto L42;
            if (r0 == null) goto L42;
            MetadataRepo r02 = MetadataRepo.create(r0, r13);     // Catch: Throwable -> L39
            TraceCompat.endSection();     // Catch: Throwable -> L20
            Object r14 = this.mLock;     // Catch: Throwable -> L20
            monitor-enter(r14);     // Catch: Throwable -> L20
            EmojiCompat.MetadataRepoLoaderCallback r22 = this.mCallback;     // Catch: Throwable -> L32
            if (r22 == null) goto L34;
            r22.onLoaded(r02);     // Catch: Throwable -> L32
        L34:
            monitor-exit(r14);     // Catch: Throwable -> L32
            cleanUp();     // Catch: Throwable -> L20
            return;
        L32:
            th = move-exception;
            throw th;     // Catch: Throwable -> L20
        L42:
            throw new RuntimeException("Unable to open file.");     // Catch: Throwable -> L39
        L39:
            th = move-exception;
            TraceCompat.endSection();     // Catch: Throwable -> L20
            throw th;     // Catch: Throwable -> L20
        L46:
            throw new RuntimeException("fetchFonts result is not OK. (" + r2 + ")");     // Catch: Throwable -> L20
        L20:
            th = move-exception;
            monitor-enter(this.mLock);
            EmojiCompat.MetadataRepoLoaderCallback r15 = this.mCallback;     // Catch: Throwable -> L52
            if (r15 == null) goto L54;
            r15.onFailed(th);     // Catch: Throwable -> L52
        L54:
            cleanUp();
            return;
        L52:
            th = move-exception;
            throw th;
        }

        @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoader
        public void load(EmojiCompat.MetadataRepoLoaderCallback r2) {
            Preconditions.checkNotNull(r2, "LoaderCallback cannot be null");
            Object r0 = this.mLock;
            monitor-enter(r0);
            this.mCallback = r2;     // Catch: Throwable -> L8
            monitor-exit(r0);     // Catch: Throwable -> L8
            loadInternal();
            return;
        L8:
            th = move-exception;
            throw th;
        }

        public void loadInternal() {
            Object r0 = this.mLock;
            monitor-enter(r0);
        L8:
            th = move-exception;
            throw th;
        L5:
            if (this.mCallback != null) goto L11;
            monitor-exit(r0);     // Catch: Throwable -> L8
            return;
        L11:
            if (this.mExecutor != null) goto L13;
            ThreadPoolExecutor r1 = ConcurrencyHelpers.createBackgroundPriorityExecutor("emojiCompat");     // Catch: Throwable -> L8
            this.mMyThreadPoolExecutor = r1;     // Catch: Throwable -> L8
            this.mExecutor = r1;     // Catch: Throwable -> L8
        L13:
            this.mExecutor.execute(new FontRequestEmojiCompatConfig$FontRequestMetadataLoader$$ExternalSyntheticLambda0(this));     // Catch: Throwable -> L8
            monitor-exit(r0);     // Catch: Throwable -> L8
        }

        public void setExecutor(Executor r2) {
            Object r0 = this.mLock;
            monitor-enter(r0);
            this.mExecutor = r2;     // Catch: Throwable -> L7
            monitor-exit(r0);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }
    }

    static {
        DEFAULT_FONTS_CONTRACT = new FontProviderHelper();
    }

    public FontRequestEmojiCompatConfig(Context r3, FontRequest r4) {
        super(new FontRequestMetadataLoader(r3, r4, DEFAULT_FONTS_CONTRACT));
    }

    public FontRequestEmojiCompatConfig setLoadingExecutor(Executor r2) {
        ((FontRequestMetadataLoader) getMetadataRepoLoader()).setExecutor(r2);
        return this;
    }
}
