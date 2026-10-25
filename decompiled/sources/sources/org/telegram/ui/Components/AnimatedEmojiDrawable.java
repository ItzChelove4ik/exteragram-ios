package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import com.exteragram.messenger.utils.ui.TextPaint;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.SQLite.SQLiteException;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.utils.Choreographer60FpsContent;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.SelectAnimatedEmojiDialog;
import org.telegram.ui.Stars.StarsReactionsSheet;

/* loaded from: classes3.dex */
public class AnimatedEmojiDrawable extends Drawable {
    private static boolean LOG_MEMORY_LEAK = false;
    public static int attachedCount;
    public static ArrayList<AnimatedEmojiDrawable> attachedDrawable;
    private static final Runnable cleanup = null;
    private static boolean disabledToggleableAnimations;
    private static HashMap<Long, Integer> dominantColors;
    private static HashMap<Integer, EmojiDocumentFetcher> fetchers;
    private static SparseArray<LongSparseArray<AnimatedEmojiDrawable>> globalEmojiCache;
    private static boolean liteModeKeyboard;
    private static boolean liteModeReactions;
    private String absolutePath;
    private float alpha;
    private boolean attached;
    private int cacheType;
    private Boolean canOverrideColorCached;
    private ColorFilter colorFilterToSet;
    private int currentAccount;
    private TLRPC.Document document;
    private long documentId;
    private ArrayList<AnimatedEmojiSpan.InvalidateHolder> holders;
    private ImageReceiver imageReceiver;
    private boolean imageReceiverEmojiThumb;
    private Boolean isDefaultStatusEmojiCached;
    public boolean preloading;
    public int sizedp;
    private ArrayList<View> views;

    /* renamed from: org.telegram.ui.Components.AnimatedEmojiDrawable$1, reason: invalid class name */
    public class AnonymousClass1 extends ImageReceiver {
        final /* synthetic */ AnimatedEmojiDrawable this$0;

        public static /* synthetic */ void $r8$lambda$cRdmjdGzxQ0g_fL3Puv2GrEbsNE(AnimatedEmojiDrawable r0) {
            AnimatedEmojiDrawable.m8790$$Nest$mupdateAttachState(r0);
        }

        public AnonymousClass1(AnimatedEmojiDrawable r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.messenger.ImageReceiver, org.telegram.ui.Components.AnimatedEmojiSpan.InvalidateHolder
        public void invalidate() {
            this.this$0.invalidate();
            super.invalidate();
        }

        @Override // org.telegram.messenger.ImageReceiver
        public boolean setImageBitmapByKey(Drawable r2, String r3, int r4, boolean r5, int r6) {
            this.this$0.invalidate();
            boolean r22 = super.setImageBitmapByKey(r2, r3, r4, r5, r6);
            if (this.this$0.preloading == true) goto L5;
        L7:
            return r22;
        L5:
            if (hasImageLoaded() == false) goto L7;
            AnimatedEmojiDrawable r1 = this.this$0;
            r1.preloading = false;
            AndroidUtilities.runOnUIThread(new AnimatedEmojiDrawable$1$$ExternalSyntheticLambda0(r1));
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Components.AnimatedEmojiDrawable$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 implements View.OnAttachStateChangeListener {
        final /* synthetic */ AnimatedEmojiDrawable this$0;

        public AnonymousClass2(AnimatedEmojiDrawable r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
            this.this$0.addView(r1);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
            this.this$0.removeView(r1);
        }
    }

    public static class EmojiDocumentFetcher {
        private final int currentAccount;
        private HashMap<Long, TLRPC.Document> emojiDocumentsCache;
        private Runnable fetchRunnable;
        private HashMap<Long, ArrayList<ReceivedDocument>> loadingDocuments;
        private HashSet<Long> toFetchDocuments;
        private Runnable uiDbCallback;

        public static /* synthetic */ void $r8$lambda$5NjlHdFwIMtQzsYsDRnjB9s0uF4(EmojiDocumentFetcher r0, ArrayList r1, HashSet r2) {
            r0.lambda$processDatabaseResult$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$BqjvhHA8TSQs33_mef9sSYFAAns(EmojiDocumentFetcher r0, ArrayList r1, TLObject r2) {
            r0.lambda$loadFromServer$4(r1, r2);
        }

        /* renamed from: $r8$lambda$EmwTSDmwyiNCFjpMYn-d16UxG34, reason: not valid java name */
        public static /* synthetic */ void m8793$r8$lambda$EmwTSDmwyiNCFjpMYnd16UxG34(EmojiDocumentFetcher r0, ArrayList r1, HashSet r2) {
            r0.lambda$processDatabaseResult$2(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$dOk2i7jWVGJLqWceJIku6tmURLs(EmojiDocumentFetcher r0, ArrayList r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$loadFromServer$6(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$lOn5N5dTm8AJtH56m-TpjY0g6yI, reason: not valid java name */
        public static /* synthetic */ void m8794$r8$lambda$lOn5N5dTm8AJtH56mTpjY0g6yI(EmojiDocumentFetcher r0, ArrayList r1) {
            r0.lambda$loadFromDatabase$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$p6sRfFDr0MgN5TB3Co74lf_a_XU(EmojiDocumentFetcher r0, ArrayList r1, TLObject r2) {
            r0.lambda$loadFromServer$5(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$s43hkRqipTZfI3gQSjbM2QhTvrk(EmojiDocumentFetcher r0, ArrayList r1) {
            r0.lambda$putToStorage$7(r1);
        }

        public static /* synthetic */ void $r8$lambda$yEa9vVxSzGRKs0xrV9T9tC5uh80(EmojiDocumentFetcher r0) {
            r0.lambda$fetchDocument$0();
        }

        /* renamed from: -$$Nest$fgetemojiDocumentsCache, reason: not valid java name */
        public static /* bridge */ /* synthetic */ HashMap m8795$$Nest$fgetemojiDocumentsCache(EmojiDocumentFetcher r0) {
            return r0.emojiDocumentsCache;
        }

        public EmojiDocumentFetcher(int r1) {
            this.currentAccount = r1;
        }

        private boolean checkThread() {
            if (Thread.currentThread() != Looper.getMainLooper().getThread()) goto L5;
            return true;
        L5:
            if (BuildVars.DEBUG_VERSION == false) goto L11;
            FileLog.e("EmojiDocumentFetcher", new IllegalStateException("Wrong thread"));
            return false;
        L11:
            return false;
        }

        private /* synthetic */ void lambda$fetchDocument$0() {
            ArrayList<Long> r0 = new ArrayList(this.toFetchDocuments);
            this.toFetchDocuments.clear();
            if (this.uiDbCallback != null) goto L5;
            boolean r1 = true;
        L6:
            loadFromDatabase(r0, r1);
            this.fetchRunnable = null;
            return;
        L5:
            r1 = false;
            goto L6
        }

        private /* synthetic */ void lambda$loadFromDatabase$1(ArrayList r1) {
            loadFromDatabase(r1);
        }

        private /* synthetic */ void lambda$loadFromServer$4(ArrayList r4, TLObject r5) {
            HashSet r0 = new HashSet(r4);
            if ((r5 instanceof Vector) == false) goto L18;
            ArrayList<Object> r42 = ((Vector) r5).objects;
            putToStorage(r42);
            processDocuments(r42);
            int r52 = 0;
        L6:
            if (r52 >= r42.size()) goto L12;
            if ((r42.get(r52) instanceof TLRPC.Document) == false) goto L10;
            r0.remove(Long.valueOf(((TLRPC.Document) r42.get(r52)).id));
        L10:
            r52 = r52 + 1;
            goto L6
        L12:
            if (r0.isEmpty() == true) goto L19;
            loadFromServer(new ArrayList(r0));
            return;
        L19:
            return;
        }

        private /* synthetic */ void lambda$loadFromServer$5(ArrayList r2, TLObject r3) {
            AndroidUtilities.runOnUIThread(new AnimatedEmojiDrawable$EmojiDocumentFetcher$$ExternalSyntheticLambda6(this, r2, r3));
        }

        private /* synthetic */ void lambda$loadFromServer$6(ArrayList r2, TLObject r3, TLRPC.TL_error r4) {
            NotificationCenter.getInstance(this.currentAccount).doOnIdle(new AnimatedEmojiDrawable$EmojiDocumentFetcher$$ExternalSyntheticLambda5(this, r2, r3));
        }

        private /* synthetic */ void lambda$processDatabaseResult$2(ArrayList r1, HashSet r2) {
            processDocumentsAndLoadMore(r1, r2);
        }

        private /* synthetic */ void lambda$processDatabaseResult$3(ArrayList r2, HashSet r3) {
            AndroidUtilities.runOnUIThread(new AnimatedEmojiDrawable$EmojiDocumentFetcher$$ExternalSyntheticLambda4(this, r2, r3));
        }

        private /* synthetic */ void lambda$putToStorage$7(ArrayList r6) {
            SQLitePreparedStatement r5 = MessagesStorage.getInstance(this.currentAccount).getDatabase().executeFast("REPLACE INTO animated_emoji VALUES(?, ?)");     // Catch: SQLiteException -> L22
            int r0 = 0;
        L5:
            if (r0 >= r6.size()) goto L20;
            if ((r6.get(r0) instanceof TLRPC.Document) == false) goto L19;
            TLRPC.Document r1 = (TLRPC.Document) r6.get(r0);     // Catch: SQLiteException -> L22
            NativeByteBuffer r2 = null;
            NativeByteBuffer r3 = new NativeByteBuffer(r1.getObjectSize());     // Catch: Exception -> L15
            r1.serializeToStream(r3);     // Catch: Exception -> L13
            r5.requery();     // Catch: Exception -> L13
            r5.bindLong(1, r1.id);     // Catch: Exception -> L13
            r5.bindByteBuffer(2, r3);     // Catch: Exception -> L13
            r5.step();     // Catch: Exception -> L13
        L17:
            if (r3 == null) goto L19;
            r3.reuse();     // Catch: SQLiteException -> L22
        L13:
            e = e;
            r2 = r3;
        L16:
            e.printStackTrace();     // Catch: SQLiteException -> L22
            r3 = r2;
        L15:
            e = e;
        L19:
            r0 = r0 + 1;     // Catch: SQLiteException -> L22
            goto L5
        L20:
            r5.dispose();     // Catch: SQLiteException -> L22
            return;
        L22:
            e = move-exception;
            FileLog.e(e);
        }

        private void loadFromDatabase(ArrayList<Long> r11) {
            MessagesStorage r0 = MessagesStorage.getInstance(this.currentAccount);
            SQLiteDatabase r1 = r0.getDatabase();
            if (r1 == null) goto L38;
            String r2 = TextUtils.join(",", r11);     // Catch: SQLiteException -> L20
            SQLiteCursor r12 = r1.queryFinalized(String.format(Locale.US, "SELECT data FROM animated_emoji WHERE document_id IN (%s)", new Object[]{r2}), new Object[0]);     // Catch: SQLiteException -> L20
            ArrayList<Object> r22 = new ArrayList();     // Catch: SQLiteException -> L20
            HashSet<Long> r4 = new HashSet(r11);     // Catch: SQLiteException -> L20
        L7:
            if (r12.next() == false) goto L22;
            NativeByteBuffer r112 = r12.byteBufferValue(0);     // Catch: SQLiteException -> L20
            TLRPC.Document r5 = TLRPC.Document.TLdeserialize(r112, r112.readInt32(true), true);     // Catch: Exception -> L16
            if (r5 != null) goto L13;
        L18:
            if (r112 == null) goto L7;
            r112.reuse();     // Catch: SQLiteException -> L20
            goto L7
        L13:
            if (r5.id == 0) goto L18;
            r22.add(r5);     // Catch: Exception -> L16
            r4.remove(Long.valueOf(r5.id));     // Catch: Exception -> L16
            goto L18
        L16:
            e = move-exception;
            FileLog.e(e);     // Catch: SQLiteException -> L20
            goto L18
        L22:
            processDatabaseResult(r22, r4);     // Catch: SQLiteException -> L20
            r12.dispose();     // Catch: SQLiteException -> L20
            Runnable r113 = this.uiDbCallback;     // Catch: SQLiteException -> L20
            if (r113 == null) goto L37;
            r113.run();     // Catch: SQLiteException -> L20
            this.uiDbCallback = null;     // Catch: SQLiteException -> L20
            return;
        L37:
            return;
        L20:
            e = move-exception;
            r0.checkSQLException(e);
            return;
        }

        private void loadFromDatabase(ArrayList<Long> r2, boolean r3) {
            if (r3 == false) goto L5;
            MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new AnimatedEmojiDrawable$EmojiDocumentFetcher$$ExternalSyntheticLambda1(this, r2));
            return;
        L5:
            loadFromDatabase(r2);
        }

        private void loadFromServer(ArrayList<Long> r4) {
            TLRPC.TL_messages_getCustomEmojiDocuments r0 = new TLRPC.TL_messages_getCustomEmojiDocuments();
            r0.document_id = r4;
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new AnimatedEmojiDrawable$EmojiDocumentFetcher$$ExternalSyntheticLambda3(this, r4));
        }

        private void processDatabaseResult(ArrayList<Object> r3, HashSet<Long> r4) {
            if (Thread.currentThread() != Looper.getMainLooper().getThread()) goto L6;
            processDocumentsAndLoadMore(r3, r4);
            return;
        L6:
            NotificationCenter.getInstance(this.currentAccount).doOnIdle(new AnimatedEmojiDrawable$EmojiDocumentFetcher$$ExternalSyntheticLambda2(this, r3, r4));
        }

        private void processDocumentsAndLoadMore(ArrayList<Object> r1, HashSet<Long> r2) {
            processDocuments(r1);
            if (r2.isEmpty() == true) goto L6;
            loadFromServer(new ArrayList(r2));
            return;
        }

        private void putToStorage(ArrayList<Object> r3) {
            MessagesStorage.getInstance(this.currentAccount).getStorageQueue().postRunnable(new AnimatedEmojiDrawable$EmojiDocumentFetcher$$ExternalSyntheticLambda7(this, r3));
        }

        public void fetchDocument(long r3, ReceivedDocument r5) {
            if (r3 == 0) goto L38;
            monitor-enter(this);
            HashMap<Long, TLRPC.Document> r0 = this.emojiDocumentsCache;     // Catch: Throwable -> L12
            if (r0 == null) goto L16;
            TLRPC.Document r02 = r0.get(Long.valueOf(r3));     // Catch: Throwable -> L12
            if (r02 == null) goto L16;
            if (r5 == null) goto L14;
            r5.run(r02);     // Catch: Throwable -> L12
        L14:
            monitor-exit(this);     // Catch: Throwable -> L12
            return;
        L16:
            monitor-exit(this);     // Catch: Throwable -> L12
            if (checkThread() == true) goto L21;
            return;
        L21:
            if (this.loadingDocuments != null) goto L23;
            this.loadingDocuments = new HashMap();
        L23:
            ArrayList<ReceivedDocument> r03 = this.loadingDocuments.get(Long.valueOf(r3));
            if (r03 == null) goto L27;
            r03.add(r5);
            return;
        L27:
            ArrayList<ReceivedDocument> r04 = new ArrayList(1);
            r04.add(r5);
            this.loadingDocuments.put(Long.valueOf(r3), r04);
            if (this.toFetchDocuments != null) goto L30;
            this.toFetchDocuments = new HashSet();
        L30:
            this.toFetchDocuments.add(Long.valueOf(r3));
            if (this.fetchRunnable == null) goto L33;
            return;
        L33:
            AnimatedEmojiDrawable$EmojiDocumentFetcher$$ExternalSyntheticLambda0 r32 = new AnimatedEmojiDrawable$EmojiDocumentFetcher$$ExternalSyntheticLambda0(this);
            this.fetchRunnable = r32;
            AndroidUtilities.runOnUIThread(r32);
            return;
        L12:
            th = move-exception;
            throw th;
        }

        public TLRPC.InputStickerSet findStickerSet(long r3) {
            monitor-enter(this);
            HashMap<Long, TLRPC.Document> r0 = this.emojiDocumentsCache;     // Catch: Throwable -> L7
            if (r0 != null) goto L9;
            monitor-exit(this);     // Catch: Throwable -> L7
            return null;
        L9:
            TLRPC.Document r32 = r0.get(Long.valueOf(r3));     // Catch: Throwable -> L7
            if (r32 != null) goto L13;
            monitor-exit(this);     // Catch: Throwable -> L7
            return null;
        L13:
            TLRPC.InputStickerSet r33 = MessageObject.getInputStickerSet(r32);     // Catch: Throwable -> L7
            monitor-exit(this);     // Catch: Throwable -> L7
            return r33;
        L7:
            th = move-exception;
            throw th;
        }

        public void processDocuments(ArrayList<?> r7) {
            if (checkThread() == false) goto L23;
            AnimatedEmojiDrawable.m8792$$Nest$smupdateLiteModeValues();
            int r1 = 0;
        L7:
            if (r1 >= r7.size()) goto L32;
            if ((r7.get(r1) instanceof TLRPC.Document) == false) goto L22;
            TLRPC.Document r2 = (TLRPC.Document) r7.get(r1);
            putDocument(r2);
            HashMap<Long, ArrayList<ReceivedDocument>> r3 = this.loadingDocuments;
            if (r3 == null) goto L22;
            ArrayList<ReceivedDocument> r32 = r3.remove(Long.valueOf(r2.id));
            if (r32 == null) goto L22;
            int r4 = 0;
        L16:
            if (r4 >= r32.size()) goto L21;
            ReceivedDocument r5 = r32.get(r4);
            if (r5 == null) goto L20;
            r5.run(r2);
        L20:
            r4 = r4 + 1;
            goto L16
        L21:
            r32.clear();
        L22:
            r1 = r1 + 1;
            goto L7
        L32:
            return;
        }

        public void putDocument(TLRPC.Document r4) {
            if (r4 != null) goto L4;
            return;
        L4:
            monitor-enter(this);
        L8:
            th = move-exception;
            throw th;
        L6:
            if (this.emojiDocumentsCache != null) goto L10;
            this.emojiDocumentsCache = new HashMap();     // Catch: Throwable -> L8
        L10:
            this.emojiDocumentsCache.put(Long.valueOf(r4.id), r4);     // Catch: Throwable -> L8
            monitor-exit(this);     // Catch: Throwable -> L8
        }

        public void putDocuments(ArrayList<TLRPC.Document> r7) {
            if (r7 != null) goto L4;
            return;
        L4:
            monitor-enter(this);
        L8:
            th = move-exception;
            throw th;
        L6:
            if (this.emojiDocumentsCache != null) goto L10;
            this.emojiDocumentsCache = new HashMap();     // Catch: Throwable -> L8
        L10:
            int r0 = r7.size();     // Catch: Throwable -> L8
            int r1 = 0;
        L11:
            if (r1 >= r0) goto L13;
            TLRPC.Document r2 = r7.get(r1);     // Catch: Throwable -> L8
            r1 = r1 + 1;     // Catch: Throwable -> L8
            TLRPC.Document r22 = r2;     // Catch: Throwable -> L8
            this.emojiDocumentsCache.put(Long.valueOf(r22.id), r22);     // Catch: Throwable -> L8
            goto L11
        L13:
            monitor-exit(this);     // Catch: Throwable -> L8
        }
    }

    public interface ReceivedDocument {
        void run(TLRPC.Document r1);
    }

    public static class SwapAnimatedEmojiDrawable extends Drawable implements AnimatedEmojiSpan.InvalidateHolder {
        private Integer account;
        private int alpha;
        boolean attached;
        private final Rect bounds;
        private int cacheType;
        public boolean center;
        private final AnimatedFloat changeProgress;
        private ColorFilter colorFilter;
        private int colorFilterLastColor;
        private final Drawable[] drawables;
        private boolean hasParticles;
        private boolean invalidateParent;
        private final Runnable invalidateRunnable;
        private Integer lastColor;
        private int offsetX;
        private int offsetY;
        private final OvershootInterpolator overshootInterpolator;
        private View parentView;
        private StarsReactionsSheet.Particles particles;
        private final AnimatedFloat particlesAlpha;
        private View secondParent;
        private int size;

        public SwapAnimatedEmojiDrawable(View r3, int r4) {
            this(r3, false, r4, 7);
        }

        public SwapAnimatedEmojiDrawable(View r2, int r3, int r4) {
            this(r2, false, r3, r4);
        }

        public SwapAnimatedEmojiDrawable(View r2, boolean r3, int r4) {
            this(r2, r3, r4, 7);
        }

        public SwapAnimatedEmojiDrawable(View r7, boolean r8, int r9, int r10) {
            this.center = false;
            this.overshootInterpolator = new OvershootInterpolator(2.0f);
            CubicBezierInterpolator r1 = CubicBezierInterpolator.EASE_OUT;
            AnimatedFloat r0 = new AnimatedFloat(null, 300, r1);
            this.changeProgress = r0;
            AnimatedFloat r5 = new AnimatedFloat(null, 300, r1);
            this.particlesAlpha = r5;
            this.drawables = new Drawable[2];
            this.alpha = 255;
            this.bounds = new Rect();
            this.invalidateRunnable = new AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable$$ExternalSyntheticLambda0(this);
            this.parentView = r7;
            r0.setParent(r7);
            this.parentView = r7;
            r5.setParent(r7);
            this.size = r9;
            this.cacheType = r10;
            this.invalidateParent = r8;
        }

        public void attach() {
            if (this.attached == true) goto L13;
            this.attached = true;
            Drawable r1 = this.drawables[0];
            if ((r1 instanceof AnimatedEmojiDrawable) == false) goto L8;
            ((AnimatedEmojiDrawable) r1).addView(this);
        L8:
            Drawable r0 = this.drawables[1];
            if ((r0 instanceof AnimatedEmojiDrawable) == false) goto L12;
            ((AnimatedEmojiDrawable) r0).addView(this);
            return;
        L12:
            return;
        }

        public void detach() {
            if (this.attached == false) goto L13;
            this.attached = false;
            Drawable r0 = this.drawables[0];
            if ((r0 instanceof AnimatedEmojiDrawable) == false) goto L8;
            ((AnimatedEmojiDrawable) r0).removeView(this);
        L8:
            Drawable r02 = this.drawables[1];
            if ((r02 instanceof AnimatedEmojiDrawable) == false) goto L12;
            ((AnimatedEmojiDrawable) r02).removeView(this);
            return;
        L12:
            return;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r12) {
            float r0 = this.changeProgress.set(1.0f);
            this.bounds.set(getBounds());
            this.bounds.offset(this.offsetX, this.offsetY);
            float r2 = this.particlesAlpha.set(this.hasParticles);
            if (r2 <= 0.0f) goto L11;
            StarsReactionsSheet.Particles r3 = this.particles;
            if (r3 == null) goto L11;
            r3.setBounds(this.bounds);
            this.particles.process();
            StarsReactionsSheet.Particles r32 = this.particles;
            Integer r4 = this.lastColor;
            if (r4 != null) goto L9;
            int r42 = -1;
        L10:
            r32.draw(r12, Theme.multAlpha(r42, r2));
            Choreographer60FpsContent.getInstance().addFrameCallback(this.invalidateRunnable, 15);
        L12:
            Drawable r22 = this.drawables[1];
            if (r22 == null) goto L33;
            if (r0 >= 1.0f) goto L33;
            r22.setAlpha((int) (this.alpha * (1.0f - r0)));
            if (this.drawables[1].getIntrinsicWidth() >= 0) goto L19;
            int r23 = getIntrinsicWidth();
        L21:
            if (this.drawables[1].getIntrinsicHeight() >= 0) goto L23;
            int r5 = getIntrinsicHeight();
        L24:
            Drawable r6 = this.drawables[1];
            if ((r6 instanceof AnimatedEmojiDrawable) == false) goto L27;
            r6.setBounds(this.bounds);
        L31:
            this.drawables[1].setColorFilter(this.colorFilter);
            this.drawables[1].draw(r12);
            this.drawables[1].setColorFilter(null);
            goto L33
        L27:
            boolean r7 = this.center;
            Rect r8 = this.bounds;
            if (r7 == false) goto L30;
            int r24 = r23 / 2;
            int r52 = r5 / 2;
            r6.setBounds(r8.centerX() - r24, this.bounds.centerY() - r52, this.bounds.centerX() + r24, this.bounds.centerY() + r52);
            goto L31
        L30:
            int r72 = r8.left;
            int r53 = r5 / 2;
            int r82 = r8.centerY() - r53;
            Rect r9 = this.bounds;
            r6.setBounds(r72, r82, r9.left + r23, r9.centerY() + r53);
            goto L31
        L23:
            r5 = this.drawables[1].getIntrinsicHeight();
            goto L24
        L19:
            r23 = this.drawables[1].getIntrinsicWidth();
        L33:
            if (this.drawables[0] == null) goto L63;
            r12.save();
            if (this.drawables[0].getIntrinsicWidth() >= 0) goto L37;
            int r25 = getIntrinsicWidth();
        L39:
            if (this.drawables[0].getIntrinsicHeight() >= 0) goto L41;
            int r54 = getIntrinsicHeight();
        L42:
            Drawable r62 = this.drawables[0];
            if ((r62 instanceof AnimatedEmojiDrawable) == false) goto L52;
            if (AnimatedEmojiDrawable.m8789$$Nest$fgetimageReceiver((AnimatedEmojiDrawable) r62) == null) goto L48;
            AnimatedEmojiDrawable.m8789$$Nest$fgetimageReceiver((AnimatedEmojiDrawable) this.drawables[0]).setRoundRadius(AndroidUtilities.dp(4.0f));
        L48:
            if (r0 >= 1.0f) goto L50;
            float r02 = this.overshootInterpolator.getInterpolation(r0);
            r12.scale(r02, r02, this.bounds.centerX(), this.bounds.centerY());
        L50:
            this.drawables[0].setBounds(this.bounds);
        L61:
            this.drawables[0].setAlpha(this.alpha);
            this.drawables[0].setColorFilter(this.colorFilter);
            this.drawables[0].draw(r12);
            this.drawables[0].setColorFilter(null);
            r12.restore();
            return;
        L52:
            if (this.center == false) goto L58;
            if (r0 >= 1.0f) goto L56;
            float r03 = this.overshootInterpolator.getInterpolation(r0);
            r12.scale(r03, r03, this.bounds.centerX(), this.bounds.centerY());
        L56:
            int r26 = r25 / 2;
            int r55 = r54 / 2;
            this.drawables[0].setBounds(this.bounds.centerX() - r26, this.bounds.centerY() - r55, this.bounds.centerX() + r26, this.bounds.centerY() + r55);
            goto L61
        L58:
            if (r0 >= 1.0f) goto L60;
            float r04 = this.overshootInterpolator.getInterpolation(r0);
            Rect r1 = this.bounds;
            r12.scale(r04, r04, r1.left + (r25 / 2.0f), r1.centerY());
        L60:
            Drawable r05 = this.drawables[0];
            Rect r13 = this.bounds;
            int r63 = r13.left;
            int r56 = r54 / 2;
            int r14 = r13.centerY() - r56;
            Rect r73 = this.bounds;
            r05.setBounds(r63, r14, r73.left + r25, r73.centerY() + r56);
            goto L61
        L41:
            r54 = this.drawables[0].getIntrinsicHeight();
            goto L42
        L37:
            r25 = this.drawables[0].getIntrinsicWidth();
            goto L39
        L63:
            return;
        L9:
            r42 = r4.intValue();
        L11:
            Choreographer60FpsContent.getInstance().removeFrameCallback(this.invalidateRunnable);
            goto L12
        }

        public Integer getColor() {
            return this.lastColor;
        }

        public Drawable getDrawable() {
            return this.drawables[0];
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.size;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.size;
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // org.telegram.ui.Components.AnimatedEmojiSpan.InvalidateHolder
        public void invalidate() {
            View r0 = this.parentView;
            if (r0 != null) goto L5;
        L10:
            View r02 = this.secondParent;
            if (r02 == null) goto L13;
            r02.invalidate();
        L13:
            invalidateSelf();
            return;
        L5:
            if (this.invalidateParent == true) goto L7;
        L9:
            this.parentView.invalidate();
            goto L10
        L7:
            if ((r0.getParent() instanceof View) == false) goto L9;
            ((View) this.parentView.getParent()).invalidate();
            goto L10
        }

        public boolean isEmpty() {
            if (this.drawables[0] != null) goto L6;
            return true;
        L6:
            return false;
        }

        public float isNotEmpty() {
            float r1 = 0.0f;
            if (this.drawables[1] == null) goto L5;
            float r2 = 1.0f - this.changeProgress.get();
        L7:
            if (this.drawables[0] == null) goto L10;
            r1 = this.changeProgress.get();
        L10:
            return r2 + r1;
        L5:
            r2 = 0.0f;
            goto L7
        }

        public boolean isStable() {
            if (this.drawables[0] != null) goto L5;
        L8:
            return false;
        L5:
            if (this.changeProgress.get() != 1.0f) goto L8;
            return true;
        }

        public void offset(int r1, int r2) {
            this.offsetX = r1;
            this.offsetY = r2;
        }

        public void play() {
            if ((getDrawable() instanceof AnimatedEmojiDrawable) == false) goto L8;
            AnimatedEmojiDrawable r1 = (AnimatedEmojiDrawable) getDrawable();
            ImageReceiver r0 = r1.getImageReceiver();
            if (r0 == null) goto L9;
            AnimatedEmojiDrawable.m8791$$Nest$mupdateAutoRepeat(r1, r0);
            r0.startAnimation();
            return;
        L9:
            return;
        }

        public void removeOldDrawable() {
            Drawable r0 = this.drawables[1];
            if (r0 != null) goto L5;
            return;
        L5:
            if ((r0 instanceof AnimatedEmojiDrawable) == false) goto L7;
            ((AnimatedEmojiDrawable) r0).removeView(this);
        L7:
            this.drawables[1] = null;
        }

        public void resetAnimation() {
            this.changeProgress.set(1.0f, true);
        }

        public void set(Drawable r5, boolean r6) {
            if (this.drawables[0] != r5) goto L5;
            return;
        L5:
            AnimatedFloat r0 = this.changeProgress;
            if (r6 == false) goto L16;
            r0.set(0.0f, true);
            Drawable r62 = this.drawables[1];
            if (r62 != null) goto L10;
        L15:
            Drawable[] r63 = this.drawables;
            r63[1] = r63[0];
            r63[0] = r5;
        L22:
            this.lastColor = null;
            this.colorFilter = null;
            this.colorFilterLastColor = 0;
            play();
            invalidate();
            return;
        L10:
            if (this.attached == true) goto L12;
        L14:
            this.drawables[1] = null;
            goto L15
        L12:
            if ((r62 instanceof AnimatedEmojiDrawable) == false) goto L14;
            ((AnimatedEmojiDrawable) r62).removeView(this);
            goto L14
        L16:
            r0.set(1.0f, true);
            boolean r64 = this.attached;
            if (r64 == false) goto L19;
            detach();
        L19:
            this.drawables[0] = r5;
            if (r64 == false) goto L22;
            attach();
            goto L22
        }

        public void set(TLRPC.Document r7, int r8, boolean r9) {
            Drawable r0 = this.drawables[0];
            if ((r0 instanceof AnimatedEmojiDrawable) == false) goto L8;
            if (r7 == null) goto L8;
            if (((AnimatedEmojiDrawable) r0).getDocumentId() != r7.id) goto L8;
            return;
        L8:
            AnimatedFloat r02 = this.changeProgress;
            if (r9 == false) goto L26;
            r02.set(0.0f, true);
            Drawable r92 = this.drawables[1];
            if (r92 != null) goto L13;
        L16:
            Drawable[] r93 = this.drawables;
            r93[1] = r93[0];
            if (r7 == null) goto L25;
            Integer r03 = this.account;
            if (r03 == null) goto L21;
            int r04 = r03.intValue();
        L22:
            r93[0] = AnimatedEmojiDrawable.make(r04, r8, r7);
            if (this.attached == false) goto L39;
            ((AnimatedEmojiDrawable) this.drawables[0]).addView(this);
        L39:
            this.lastColor = null;
            this.colorFilter = null;
            this.colorFilterLastColor = 0;
            play();
            invalidate();
            return;
        L21:
            r04 = UserConfig.selectedAccount;
            goto L22
        L25:
            r93[0] = null;
            goto L39
        L13:
            if ((r92 instanceof AnimatedEmojiDrawable) == false) goto L15;
            ((AnimatedEmojiDrawable) r92).removeView(this);
        L15:
            this.drawables[1] = null;
            goto L16
        L26:
            r02.set(1.0f, true);
            boolean r94 = this.attached;
            if (r94 == false) goto L29;
            detach();
        L29:
            Drawable[] r05 = this.drawables;
            if (r7 == null) goto L36;
            Integer r3 = this.account;
            if (r3 == null) goto L34;
            int r32 = r3.intValue();
        L35:
            r05[0] = AnimatedEmojiDrawable.make(r32, r8, r7);
        L37:
            if (r94 == false) goto L39;
            attach();
            goto L39
        L34:
            r32 = UserConfig.selectedAccount;
            goto L35
        L36:
            r05[0] = null;
            goto L37
        }

        public void set(TLRPC.Document r2, boolean r3) {
            set(r2, this.cacheType, r3);
        }

        public boolean set(long r6, int r8, boolean r9) {
            Drawable r0 = this.drawables[0];
            if ((r0 instanceof AnimatedEmojiDrawable) == true) goto L5;
        L7:
            AnimatedFloat r02 = this.changeProgress;
            if (r9 == false) goto L24;
            r02.set(0.0f, true);
            Drawable r92 = this.drawables[1];
            if (r92 != null) goto L12;
        L17:
            Drawable[] r93 = this.drawables;
            r93[1] = r93[0];
            Integer r03 = this.account;
            if (r03 == null) goto L20;
            int r04 = r03.intValue();
        L21:
            r93[0] = AnimatedEmojiDrawable.make(r04, r8, r6);
            if (this.attached == false) goto L34;
            ((AnimatedEmojiDrawable) this.drawables[0]).addView(this);
        L34:
            this.lastColor = null;
            this.colorFilter = null;
            this.colorFilterLastColor = 0;
            play();
            invalidate();
            return true;
        L20:
            r04 = UserConfig.selectedAccount;
            goto L21
        L12:
            if (this.attached == true) goto L14;
        L16:
            this.drawables[1] = null;
            goto L17
        L14:
            if ((r92 instanceof AnimatedEmojiDrawable) == false) goto L16;
            ((AnimatedEmojiDrawable) r92).removeView(this);
            goto L16
        L24:
            r02.set(1.0f, true);
            boolean r94 = this.attached;
            if (r94 == false) goto L27;
            detach();
        L27:
            Drawable[] r05 = this.drawables;
            Integer r4 = this.account;
            if (r4 == null) goto L30;
            int r42 = r4.intValue();
        L31:
            r05[0] = AnimatedEmojiDrawable.make(r42, r8, r6);
            if (r94 == false) goto L34;
            attach();
            goto L34
        L30:
            r42 = UserConfig.selectedAccount;
            goto L31
        L5:
            if (((AnimatedEmojiDrawable) r0).getDocumentId() != r6) goto L7;
            return false;
        }

        public boolean set(long r2, boolean r4) {
            return set(r2, this.cacheType, r4);
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.alpha = r1;
        }

        public void setColor(Integer r3) {
            Integer r0 = this.lastColor;
            if (r0 != null) goto L5;
            if (r3 != null) goto L5;
            return;
        L5:
            if (r0 != null) goto L7;
        L9:
            this.lastColor = r3;
            if (r3 != null) goto L12;
        L15:
            if (r3 == null) goto L17;
            int r32 = r3.intValue();
            this.colorFilterLastColor = r32;
            PorterDuffColorFilter r02 = new PorterDuffColorFilter(r32, PorterDuff.Mode.SRC_IN);
        L18:
            this.colorFilter = r02;
            return;
        L17:
            r02 = null;
            goto L18
        L12:
            if (this.colorFilterLastColor != r3.intValue()) goto L15;
            return;
        L7:
            if (r0.equals(r3) == false) goto L9;
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }

        public void setCurrentAccount(int r1) {
            this.account = Integer.valueOf(r1);
        }

        public void setParentView(View r2) {
            this.changeProgress.setParent(r2);
            this.particlesAlpha.setParent(r2);
            this.parentView = r2;
        }

        public void setParticles(boolean r2, boolean r3) {
            setParticles(r2, r3, 8);
        }

        public void setParticles(boolean r2, boolean r3, int r4) {
            if (this.hasParticles != r2) goto L6;
            return;
        L6:
            if (r3 == true) goto L8;
            this.hasParticles = r2;
            if (r2 == true) goto L15;
        L17:
            if (r2 == false) goto L19;
        L21:
            this.particlesAlpha.set(r2, true);
            invalidate();
            return;
        L19:
            if (this.particles == null) goto L21;
            this.particles = null;
            goto L21
        L15:
            if (this.particles != null) goto L17;
            this.particles = new StarsReactionsSheet.Particles(1, r4);
            goto L21
        L8:
            if (this.particles != null) goto L10;
            this.particles = new StarsReactionsSheet.Particles(1, r4);
        L10:
            this.hasParticles = r2;
            invalidate();
        }

        public void setSecondParent(View r1) {
            this.secondParent = r1;
        }
    }

    /* loaded from: classes7.dex */
    public static class WrapSizeDrawable extends Drawable {
        private int alpha;
        private Drawable drawable;
        int height;
        int width;

        public WrapSizeDrawable(Drawable r2, int r3, int r4) {
            this.alpha = 255;
            this.drawable = r2;
            this.width = r3;
            this.height = r4;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r3) {
            Drawable r0 = this.drawable;
            if (r0 == null) goto L6;
            r0.setBounds(getBounds());
            this.drawable.setAlpha(this.alpha);
            this.drawable.draw(r3);
            return;
        }

        public Drawable getDrawable() {
            return this.drawable;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return this.height;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return this.width;
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            Drawable r0 = this.drawable;
            if (r0 != null) goto L5;
            return -2;
        L5:
            return r0.getOpacity();
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.alpha = r1;
            Drawable r0 = this.drawable;
            if (r0 == null) goto L6;
            r0.setAlpha(r1);
            return;
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
            Drawable r0 = this.drawable;
            if (r0 == null) goto L6;
            r0.setColorFilter(r1);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$38Fbn7_6zT0EnPnT6cxViuoM8mY(AnimatedEmojiDrawable r0, TLRPC.Document r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$StO9BqnN4jH_eSHzNAWRB2X05VQ(AnimatedEmojiDrawable r0, TLRPC.Document r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$nzDBvSt6sCG6mQHTRhz9iH2Exwc() {
        AndroidUtilities.cancelRunOnUIThread(cleanup);
    L17:
        e = move-exception;
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L29;
        FileLog.e(e);
        return;
    L29:
        return;
    L4:
        if (globalEmojiCache == null) goto L28;
        int r1 = 0;
    L6:
        if (r1 >= globalEmojiCache.size()) goto L30;
        LongSparseArray<AnimatedEmojiDrawable> r2 = globalEmojiCache.valueAt(r1);     // Catch: Exception -> L17
        int r3 = 0;
    L10:
        if (r3 >= r2.size()) goto L16;
        if (r2.valueAt(r3).attached == true) goto L15;
        r2.removeAt(r3);     // Catch: Exception -> L17
        r3 = r3 - 1;
    L15:
        r3 = r3 + 1;
        goto L10
    L16:
        r1 = r1 + 1;
        goto L6
    L30:
        return;
    }

    /* renamed from: -$$Nest$fgetimageReceiver, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageReceiver m8789$$Nest$fgetimageReceiver(AnimatedEmojiDrawable r0) {
        return r0.imageReceiver;
    }

    /* renamed from: -$$Nest$mupdateAttachState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8790$$Nest$mupdateAttachState(AnimatedEmojiDrawable r0) {
        r0.updateAttachState();
    }

    /* renamed from: -$$Nest$mupdateAutoRepeat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8791$$Nest$mupdateAutoRepeat(AnimatedEmojiDrawable r0, ImageReceiver r1) {
        r0.updateAutoRepeat(r1);
    }

    /* renamed from: -$$Nest$smupdateLiteModeValues, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8792$$Nest$smupdateLiteModeValues() {
        updateLiteModeValues();
    }

    static {
        cleanup = new AnimatedEmojiDrawable$$ExternalSyntheticLambda0();
    }

    @Deprecated
    public AnimatedEmojiDrawable(int r2, int r3) {
        this.alpha = 1.0f;
        this.canOverrideColorCached = null;
        this.isDefaultStatusEmojiCached = null;
        this.cacheType = r2;
        this.currentAccount = r3;
        updateSize();
        updateLiteModeValues();
    }

    public AnimatedEmojiDrawable(int r2, int r3, long r4) {
        this.alpha = 1.0f;
        this.canOverrideColorCached = null;
        this.isDefaultStatusEmojiCached = null;
        this.currentAccount = r3;
        this.cacheType = r2;
        updateSize();
        this.documentId = r4;
        getDocumentFetcher(r3).fetchDocument(r4, new AnimatedEmojiDrawable$$ExternalSyntheticLambda1(this));
    }

    public AnimatedEmojiDrawable(int r2, int r3, long r4, String r6) {
        this.alpha = 1.0f;
        this.canOverrideColorCached = null;
        this.isDefaultStatusEmojiCached = null;
        this.currentAccount = r3;
        this.cacheType = r2;
        updateSize();
        this.documentId = r4;
        this.absolutePath = r6;
        getDocumentFetcher(r3).fetchDocument(r4, new AnimatedEmojiDrawable$$ExternalSyntheticLambda2(this));
    }

    public AnimatedEmojiDrawable(int r2, int r3, TLRPC.Document r4) {
        this.alpha = 1.0f;
        this.canOverrideColorCached = null;
        this.isDefaultStatusEmojiCached = null;
        this.cacheType = r2;
        this.currentAccount = r3;
        this.document = r4;
        updateSize();
        updateLiteModeValues();
        initDocument(false);
    }

    private void createImageReceiver() {
        if (this.imageReceiver != null) goto L8;
        AnonymousClass1 r0 = new AnonymousClass1(this);
        this.imageReceiver = r0;
        r0.setCurrentAccount(this.currentAccount);
        this.imageReceiver.setAllowLoadingOnAttachedOnly(true);
        if (this.cacheType != 12) goto L9;
        this.imageReceiver.ignoreNotifications = true;
        return;
    L9:
        return;
    }

    public static void dropGlobalEmojiCache() {
        SparseArray<LongSparseArray<AnimatedEmojiDrawable>> r0 = globalEmojiCache;
        if (r0 == null) goto L6;
        r0.clear();
        return;
    }

    public static TLRPC.Document findDocument(int r1, long r2) {
        EmojiDocumentFetcher r12 = getDocumentFetcher(r1);
        if (r12 != null) goto L5;
        return null;
    L5:
        if (EmojiDocumentFetcher.m8795$$Nest$fgetemojiDocumentsCache(r12) != null) goto L8;
        return null;
    L8:
        return (TLRPC.Document) EmojiDocumentFetcher.m8795$$Nest$fgetemojiDocumentsCache(r12).get(Long.valueOf(r2));
    }

    public static TLRPC.InputStickerSet findStickerSet(int r1, long r2) {
        EmojiDocumentFetcher r12 = getDocumentFetcher(r1);
        if (r12 != null) goto L5;
        return null;
    L5:
        if (EmojiDocumentFetcher.m8795$$Nest$fgetemojiDocumentsCache(r12) != null) goto L8;
        return null;
    L8:
        return r12.findStickerSet(r2);
    }

    public static int getCacheTypeForEnterView() {
        if (SharedConfig.getDevicePerformanceClass() != 0) goto L6;
        return 0;
    L6:
        return 2;
    }

    public static EmojiDocumentFetcher getDocumentFetcher(int r3) {
        if (fetchers != null) goto L5;
        fetchers = new HashMap();
    L5:
        EmojiDocumentFetcher r0 = fetchers.get(Integer.valueOf(r3));
        if (r0 != null) goto L9;
        HashMap<Integer, EmojiDocumentFetcher> r02 = fetchers;
        Integer r1 = Integer.valueOf(r3);
        EmojiDocumentFetcher r2 = new EmojiDocumentFetcher(r3);
        r02.put(r1, r2);
        return r2;
    L9:
        return r0;
    }

    public static int getDominantColor(AnimatedEmojiDrawable r5) {
        if (r5 != null) goto L5;
        return 0;
    L5:
        long r1 = r5.getDocumentId();
        if (r1 != 0) goto L9;
        return 0;
    L9:
        if (dominantColors != null) goto L11;
        dominantColors = new HashMap();
    L11:
        Integer r3 = dominantColors.get(Long.valueOf(r1));
        if (r3 == null) goto L14;
    L18:
        if (r3 != null) goto L21;
        return 0;
    L21:
        return r3.intValue();
    L14:
        if (r5.getImageReceiver() == null) goto L18;
        if (r5.getImageReceiver().getBitmap() == null) goto L18;
        HashMap<Long, Integer> r32 = dominantColors;
        Long r12 = Long.valueOf(r1);
        Integer r52 = Integer.valueOf(AndroidUtilities.getDominantColor(r5.getImageReceiver().getBitmap()));
        r32.put(r12, r52);
        r3 = r52;
        goto L18
    }

    private void initDocument(boolean r34) {
        TLRPC.Document r1 = this.document;
        if (r1 != null) goto L5;
        return;
    L5:
        if (this.imageReceiver != null) goto L7;
    L9:
        int r2 = this.cacheType;
        if (r2 == 20) goto L13;
        if (r2 == 21) goto L13;
    L15:
        this.imageReceiverEmojiThumb = false;
        createImageReceiver();
        if (this.colorFilterToSet != null) goto L18;
    L20:
        int r22 = this.cacheType;
        if (r22 == 0) goto L25;
        if (r22 != 12) goto L24;
        r22 = 2;
    L24:
        this.imageReceiver.setUniqKeyPrefix(r22 + "_");
    L25:
        this.imageReceiver.setVideoThumbIsSame(true);
        if (SharedConfig.getDevicePerformanceClass() == 0) goto L28;
    L29:
        int r23 = this.cacheType;
        if (r23 == 2) goto L33;
        if (r23 == 25) goto L33;
    L34:
        if (r23 == 3) goto L36;
    L38:
        boolean r24 = false;
    L40:
        int r12 = this.cacheType;
        if (r12 == 13) goto L43;
        if (r12 == 16) goto L43;
    L45:
        if (r12 == 24) goto L47;
        if (r12 == 27) goto L47;
    L48:
        String r122 = this.sizedp + "_" + this.sizedp;
        if (this.cacheType != 12) goto L51;
        r122 = r122.concat("_d_nostream");
    L51:
        int r15 = this.cacheType;
        if (r15 == 17) goto L62;
        if (r15 == 15) goto L62;
        if (r15 == 14) goto L62;
        if (r15 == 8) goto L62;
        if (r15 != 1) goto L60;
        if (SharedConfig.getDevicePerformanceClass() >= 2) goto L62;
    L60:
        if (this.cacheType == 12) goto L62;
        r122 = r122.concat("_pcache");
    L62:
        int r152 = this.cacheType;
        if (r152 == 17) goto L75;
        if (r152 == 0) goto L75;
        if (r152 == 26) goto L75;
        if (r152 == 1) goto L75;
        if (r152 == 14) goto L75;
        if (r152 == 15) goto L75;
        if (r152 == 19) goto L75;
        if (r152 == 20) goto L75;
        if (r152 == 21) goto L75;
        r122 = r122.concat("_compress");
    L75:
        if (this.cacheType != 8) goto L77;
        r122 = r122.concat("firstframe");
    L77:
        TLRPC.PhotoSize r13 = FileLoader.getClosestPhotoSizeWithSize(this.document.thumbs, 90);
        boolean r153 = "video/webm".equals(this.document.mime_type);
        TLRPC.Document r14 = this.document;
        float r9 = 0.2f;
        if (r153 == false) goto L82;
        ImageLocation r5 = ImageLocation.getForDocument(r14);
        r122 = r122.concat("_g");
        SvgHelper.SvgDrawable r92 = DocumentObject.getSvgThumb(this.document.thumbs, Theme.key_windowBackgroundWhiteGrayIcon, 0.2f, true);
    L80:
        ImageLocation r18 = r5;
        String r19 = r122;
    L113:
        int r3 = this.cacheType;
        if (r3 != 20) goto L116;
    L119:
        ImageLocation r4 = null;
        Drawable r32 = Emoji.getEmojiDrawable(MessageObject.findAnimatedEmojiEmoticon(this.document, null));
        if (r32 == null) goto L122;
        Drawable r242 = r32;
    L124:
        if (this.absolutePath == null) goto L126;
        this.imageReceiver.setImageBitmap(new AnimatedFileDrawable(new File(this.absolutePath), true, 0, 0, null, null, null, 0, this.currentAccount, true, 512, 512, null));
    L151:
        updateAutoRepeat(this.imageReceiver);
        int r16 = this.cacheType;
        if (r16 != 13) goto L154;
    L163:
        this.imageReceiver.setLayerNum(7);
    L164:
        int r17 = this.cacheType;
        if (r17 != 9) goto L167;
    L170:
        this.imageReceiver.setLayerNum(6656);
    L171:
        this.imageReceiver.setAspectFit(true);
        int r110 = this.cacheType;
        if (r110 != 12) goto L174;
    L187:
        int r33 = 0;
        this.imageReceiver.setAllowStartAnimation(false);
        this.imageReceiver.setAllowStartLottieAnimation(false);
        this.imageReceiver.setAutoRepeat(0);
    L188:
        this.imageReceiver.setAllowDecodeSingleFrame(true);
        int r111 = this.cacheType;
        if (r111 == 5) goto L198;
        if (r111 == 6) goto L198;
        if (r111 == 27) goto L198;
        if (r111 != 24) goto L197;
        int r112 = AndroidUtilities.dp(14.0f);
    L199:
        this.imageReceiver.setRoundRadius(r112);
        updateAttachState();
        invalidate();
        return;
    L197:
        r112 = r33;
    L198:
        r112 = AndroidUtilities.dp(6.0f);
        goto L199
    L174:
        if (r110 == 26) goto L187;
        if (r110 == 18) goto L187;
        if (r110 == 8) goto L187;
        if (r110 == 6) goto L187;
        if (r110 == 5) goto L187;
        if (r110 == 25) goto L184;
    L186:
        this.imageReceiver.setAllowStartLottieAnimation(true);
        this.imageReceiver.setAllowStartAnimation(true);
        this.imageReceiver.setAutoRepeat(1);
        r33 = 0;
        goto L188
    L184:
        if (disabledToggleableAnimations == false) goto L186;
    L167:
        if (r17 == 21) goto L170;
        if (r17 != 27) goto L171;
    L154:
        if (r16 == 16) goto L163;
        if (r16 == 3) goto L163;
        if (r16 == 5) goto L163;
        if (r16 == 4) goto L163;
        if (r16 != 24) goto L164;
    L126:
        int r35 = this.cacheType;
        if (r35 != 8) goto L129;
        ImageReceiver r113 = this.imageReceiver;
        TLRPC.Document r25 = this.document;
        r113.setImage(null, null, r18, r19, null, null, r242, r25.size, null, r25, 1);
        goto L151
    L129:
        ImageLocation r20 = r18;
        String r21 = r19;
        if (r24 == true) goto L141;
        if (liteModeKeyboard == true) goto L137;
        if (r35 != 14) goto L141;
    L137:
        if (r35 != 17) goto L139;
        ImageLocation r222 = ImageLocation.getForDocument(r13, this.document);
    L140:
        ImageReceiver r26 = this.imageReceiver;
        ImageLocation r114 = ImageLocation.getForDocument(r13, this.document);
        String r36 = this.sizedp + "_" + this.sizedp;
        TLRPC.Document r42 = this.document;
        r26.setImage(r20, r21, r114, r36, r222, null, r242, r42.size, null, r42, 1);
        goto L151
    L139:
        r222 = r4;
    L141:
        if (r35 != 16) goto L143;
        ImageLocation r232 = ImageLocation.getForDocument(r13, this.document);
    L145:
        if ("video/webm".equals(this.document.mime_type) == false) goto L147;
        ImageReceiver r27 = this.imageReceiver;
        ImageLocation r115 = ImageLocation.getForDocument(r13, this.document);
        String r37 = this.sizedp + "_" + this.sizedp;
        TLRPC.Document r43 = this.document;
        r27.setImage(null, null, r115, r37, r232, null, r242, r43.size, null, r43, 1);
        goto L151
    L147:
        boolean r28 = MessageObject.isAnimatedStickerDocument(this.document, true);
        ImageReceiver r38 = this.imageReceiver;
        if (r28 == false) goto L150;
        String r192 = r21.concat("_firstframe");
        TLRPC.Document r116 = this.document;
        r38.setImage(r20, r192, r232, null, r242, r116.size, null, r116, 1);
        goto L151
    L150:
        ImageLocation r212 = ImageLocation.getForDocument(r13, this.document);
        String r223 = this.sizedp + "_" + this.sizedp;
        TLRPC.Document r117 = this.document;
        r38.setImage(r212, r223, r232, null, r242, r117.size, null, r117, 1);
        goto L151
    L143:
        r232 = r4;
    L122:
        r242 = r92;
        goto L124
    L116:
        if (r3 == 21) goto L119;
        r4 = null;
        goto L122
    L82:
        if ("application/x-tgsticker".equals(r14.mime_type) == false) goto L107;
        StringBuilder r142 = new StringBuilder();
        if (this.cacheType == 0) goto L86;
        String r11 = this.cacheType + "_";
    L87:
        r142.append(r11);
        r142.append(this.documentId);
        r142.append("@");
        r142.append(r122);
        String r39 = r142.toString();
        if (SharedConfig.getDevicePerformanceClass() != 0) goto L98;
        int r44 = this.cacheType;
        if (r44 == 2) goto L98;
        if (r44 == 25) goto L98;
        if (r44 == 22) goto L98;
        if (ImageLoader.getInstance().hasLottieMemCache(r39) == false) goto L98;
        r92 = null;
    L106:
        r5 = ImageLocation.getForDocument(this.document);
    L98:
        if (this.cacheType != 22) goto L100;
        r9 = 0.8f;
    L100:
        SvgHelper.SvgDrawable r310 = DocumentObject.getSvgThumb(this.document.thumbs, Theme.key_windowBackgroundWhiteGrayIcon, r9);
        if (r310 != null) goto L103;
    L105:
        r92 = r310;
        goto L106
    L103:
        if (MessageObject.isAnimatedStickerDocument(this.document, true) == false) goto L105;
        r310.overrideWidthAndHeight(512, 512);
        goto L105
    L86:
        r11 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L87
    L107:
        r92 = DocumentObject.getSvgThumb(this.document.thumbs, Theme.key_windowBackgroundWhiteGrayIcon, 0.2f, true);
        if (r92 != null) goto L110;
    L112:
        r19 = r122;
        r18 = null;
        goto L113
    L110:
        if (MessageObject.isAnimatedStickerDocument(this.document, true) == false) goto L112;
        r92.overrideWidthAndHeight(512, 512);
    L47:
        r24 = false;
    L43:
        r24 = true;
        goto L45
    L36:
        if (liteModeReactions == true) goto L38;
    L39:
        r24 = true;
    L33:
        if (liteModeKeyboard == false) goto L39;
    L28:
        if (this.cacheType == 5) goto L39;
    L18:
        if (canOverrideColor() == false) goto L20;
        this.imageReceiver.setColorFilter(this.colorFilterToSet);
    L13:
        if ((r1 instanceof TLRPC.TL_documentEmpty) == false) goto L15;
        return;
    L7:
        if (this.imageReceiverEmojiThumb == true) goto L9;
        if (r34 == true) goto L9;
    }

    public static boolean isDefaultStatusEmoji(AnimatedEmojiDrawable r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (r0.isDefaultStatusEmoji() == false) goto L9;
        return true;
    L9:
        return false;
    }

    private /* synthetic */ void lambda$new$0(TLRPC.Document r1) {
        this.document = r1;
        initDocument(false);
    }

    private /* synthetic */ void lambda$new$1(TLRPC.Document r1) {
        this.document = r1;
        initDocument(false);
    }

    public static AnimatedEmojiDrawable make(int r1, int r2, long r3) {
        return make(r1, r2, r3, null);
    }

    public static AnimatedEmojiDrawable make(int r8, int r9, long r10, String r12) {
        if (globalEmojiCache != null) goto L5;
        globalEmojiCache = new SparseArray();
    L5:
        int r0 = Objects.hash(new Object[]{Integer.valueOf(r8), Integer.valueOf(r9)});
        LongSparseArray<AnimatedEmojiDrawable> r1 = globalEmojiCache.get(r0);
        if (r1 != null) goto L8;
        SparseArray<LongSparseArray<AnimatedEmojiDrawable>> r13 = globalEmojiCache;
        LongSparseArray<AnimatedEmojiDrawable> r2 = new LongSparseArray();
        r13.put(r0, r2);
        r1 = r2;
    L8:
        AnimatedEmojiDrawable r02 = r1.get(r10);
        if (r02 != null) goto L12;
        AnimatedEmojiDrawable r22 = new AnimatedEmojiDrawable(r9, r8, r10, r12);
        r1.put(r10, r22);
        return r22;
    L12:
        return r02;
    }

    public static AnimatedEmojiDrawable make(int r4, int r5, TLRPC.Document r6) {
        if (globalEmojiCache != null) goto L5;
        globalEmojiCache = new SparseArray();
    L5:
        int r0 = Objects.hash(new Object[]{Integer.valueOf(r4), Integer.valueOf(r5)});
        LongSparseArray<AnimatedEmojiDrawable> r1 = globalEmojiCache.get(r0);
        if (r1 != null) goto L8;
        SparseArray<LongSparseArray<AnimatedEmojiDrawable>> r12 = globalEmojiCache;
        LongSparseArray<AnimatedEmojiDrawable> r2 = new LongSparseArray();
        r12.put(r0, r2);
        r1 = r2;
    L8:
        AnimatedEmojiDrawable r02 = r1.get(r6.id);
        if (r02 != null) goto L12;
        long r22 = r6.id;
        AnimatedEmojiDrawable r03 = new AnimatedEmojiDrawable(r5, r4, r6);
        r1.put(r22, r03);
        return r03;
    L12:
        return r02;
    }

    public static void toggleAnimations(int r4, boolean r5) {
        if (disabledToggleableAnimations == (!r5)) goto L36;
        disabledToggleableAnimations = !r5;
        if (globalEmojiCache == null) goto L37;
        LongSparseArray<AnimatedEmojiDrawable> r42 = globalEmojiCache.get(Objects.hash(new Object[]{Integer.valueOf(r4), 25}));
        if (r42 == null) goto L28;
        int r1 = 0;
    L12:
        if (r1 >= r42.size()) goto L38;
        AnimatedEmojiDrawable r2 = r42.valueAt(r1);
        if (r2 == null) goto L27;
        ImageReceiver r22 = r2.getImageReceiver();
        if (r22 == null) goto L27;
        if (r5 == false) goto L26;
        r22.setAllowStartLottieAnimation(true);
        r22.setAllowStartAnimation(true);
        r22.setAutoRepeat(1);
        AnimatedFileDrawable r3 = r22.getAnimation();
        if (r3 == null) goto L23;
        r3.setUseSharedQueue(r22.useSharedAnimationQueue);
        r3.start();
        goto L27
    L23:
        RLottieDrawable r23 = r22.getLottieAnimation();
        if (r23 == null) goto L27;
        r23.start();
        goto L27
    L26:
        r22.setAllowStartAnimation(false);
        r22.setAllowStartLottieAnimation(false);
        r22.setAutoRepeat(0);
        r22.stopAnimation();
    L27:
        r1 = r1 + 1;
        goto L12
    L38:
        return;
    L28:
        return;
    L37:
        return;
    }

    public static void updateAll() {
        if (globalEmojiCache == null) goto L19;
        updateLiteModeValues();
        int r1 = 0;
    L7:
        if (r1 >= globalEmojiCache.size()) goto L24;
        LongSparseArray<AnimatedEmojiDrawable> r2 = globalEmojiCache.valueAt(r1);
        int r3 = 0;
    L10:
        if (r3 >= r2.size()) goto L18;
        long r4 = r2.keyAt(r3);
        AnimatedEmojiDrawable r6 = r2.get(r4);
        if (r6 != null) goto L14;
    L16:
        r2.remove(r4);
    L17:
        r3 = r3 + 1;
        goto L10
    L14:
        if (r6.attached == false) goto L16;
        r6.initDocument(true);
        goto L17
    L18:
        r1 = r1 + 1;
        goto L7
    L24:
        return;
    }

    private void updateAttachState() {
        if (this.imageReceiver == null) goto L39;
        ArrayList<View> r0 = this.views;
        if (r0 != null) goto L8;
    L9:
        ArrayList<AnimatedEmojiSpan.InvalidateHolder> r02 = this.holders;
        if (r02 == null) goto L14;
        if (r02.size() <= 0) goto L14;
    L15:
        boolean r03 = true;
    L18:
        if (r03 == this.attached) goto L37;
        this.attached = r03;
        ImageReceiver r2 = this.imageReceiver;
        if (r03 == false) goto L22;
        r2.onAttachedToWindow();
    L24:
        if (LOG_MEMORY_LEAK == false) goto L34;
        if (attachedDrawable != null) goto L29;
        attachedDrawable = new ArrayList();
    L29:
        if (this.attached == false) goto L31;
        attachedCount++;
        attachedDrawable.add(this);
    L32:
        Log.d("animatedDrawable", "attached count " + attachedCount);
        goto L34
    L31:
        attachedCount--;
        attachedDrawable.remove(this);
    L34:
        if (this.attached == true) goto L38;
        Runnable r3 = cleanup;
        AndroidUtilities.cancelRunOnUIThread(r3);
        AndroidUtilities.runOnUIThread(r3, 5000);
        return;
    L38:
        return;
    L22:
        r2.onDetachedFromWindow();
        goto L24
    L37:
        return;
    L14:
        if (this.preloading == true) goto L15;
        r03 = false;
        goto L18
    L8:
        if (r0.size() > 0) goto L15;
    }

    private void updateAutoRepeat(ImageReceiver r2) {
        int r1 = this.cacheType;
        if (r1 != 7) goto L5;
    L28:
        r2.setAutoRepeatCount(2);
        return;
    L5:
        if (r1 == 9) goto L28;
        if (r1 == 10) goto L28;
        if (r1 != 11) goto L12;
    L26:
        r2.setAutoRepeatCount(1);
        return;
    L12:
        if (r1 == 18) goto L26;
        if (r1 == 14) goto L26;
        if (r1 == 6) goto L26;
        if (r1 == 5) goto L26;
        if (r1 == 22) goto L26;
        if (r1 != 17) goto L30;
        r2.setAutoRepeatCount(0);
        return;
    }

    private static void updateLiteModeValues() {
        liteModeKeyboard = LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_KEYBOARD);
        liteModeReactions = LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_REACTIONS);
    }

    private void updateSize() {
        int r0 = this.cacheType;
        if (r0 != 0) goto L5;
    L58:
        this.sizedp = (int) (((Math.abs(Theme.chat_msgTextPaint.ascent()) + Math.abs(Theme.chat_msgTextPaint.descent())) * 1.15f) / AndroidUtilities.density);
        return;
    L5:
        if (r0 == 26) goto L58;
        TextPaint[] r2 = Theme.chat_msgTextPaintEmoji;
        if (r2 != null) goto L10;
    L19:
        if (r2 == null) goto L25;
        if (r0 != 8) goto L25;
        this.sizedp = (int) (((Math.abs(r2[0].ascent()) + Math.abs(Theme.chat_msgTextPaintEmoji[0].descent())) * 1.15f) / AndroidUtilities.density);
        return;
    L25:
        if (r0 != 14) goto L27;
    L56:
        this.sizedp = 100;
        return;
    L27:
        if (r0 == 15) goto L56;
        if (r0 == 17) goto L56;
        if (r0 != 11) goto L34;
    L54:
        this.sizedp = 56;
        return;
    L34:
        if (r0 == 22) goto L54;
        if (r0 != 27) goto L41;
        this.sizedp = 50;
        return;
    L41:
        if (r0 != 24) goto L45;
        this.sizedp = 140;
        return;
    L45:
        if (r0 != 23) goto L49;
        this.sizedp = 14;
        return;
    L49:
        if (r0 != 21) goto L52;
        this.sizedp = 90;
        return;
    L52:
        this.sizedp = 34;
        return;
    L10:
        if (r0 != 1) goto L12;
    L17:
        this.sizedp = (int) (((Math.abs(r2[2].ascent()) + Math.abs(Theme.chat_msgTextPaintEmoji[2].descent())) * 1.15f) / AndroidUtilities.density);
        return;
    L12:
        if (r0 == 4) goto L17;
        if (r0 == 19) goto L17;
        if (r0 != 20) goto L19;
        goto L17
    }

    public void addView(View r3) {
        if ((r3 instanceof SelectAnimatedEmojiDialog.EmojiListView) == true) goto L13;
        this.preloading = false;
        if (this.views != null) goto L8;
        this.views = new ArrayList(10);
    L8:
        if (this.views.contains(r3) == true) goto L10;
        this.views.add(r3);
    L10:
        updateAttachState();
        return;
    L13:
        throw new RuntimeException();
    }

    public void addView(AnimatedEmojiSpan.InvalidateHolder r3) {
        if (this.holders != null) goto L5;
        this.holders = new ArrayList(10);
    L5:
        this.preloading = false;
        if (this.holders.contains(r3) == true) goto L8;
        this.holders.add(r3);
    L8:
        updateAttachState();
    }

    public void addViewListening(View r2) {
        r2.addOnAttachStateChangeListener(new AnonymousClass2(this));
    }

    public boolean canOverrideColor() {
        boolean r2 = true;
        if (this.cacheType != 19) goto L5;
        return true;
    L5:
        Boolean r0 = this.canOverrideColorCached;
        if (r0 == null) goto L10;
        return r0.booleanValue();
    L10:
        if (this.document != null) goto L12;
        return false;
    L12:
        if (isDefaultStatusEmoji() == false) goto L14;
    L17:
        this.canOverrideColorCached = Boolean.valueOf(r2);
        return r2;
    L14:
        if (MessageObject.isTextColorEmoji(this.document) == true) goto L17;
        r2 = false;
        goto L17
    }

    public void clear() {
        ArrayList<AnimatedEmojiSpan.InvalidateHolder> r0 = this.holders;
        if (r0 == null) goto L5;
        r0.clear();
    L5:
        ArrayList<View> r02 = this.views;
        if (r02 == null) goto L8;
        r02.clear();
    L8:
        this.preloading = false;
        updateAttachState();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r3) {
        ImageReceiver r0 = this.imageReceiver;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setImageCoords(getBounds());
        this.imageReceiver.setAlpha(this.alpha);
        this.imageReceiver.draw(r3);
    }

    public void draw(Canvas r2, Rect r3, float r4) {
        ImageReceiver r0 = this.imageReceiver;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setImageCoords(r3);
        this.imageReceiver.setAlpha(r4);
        this.imageReceiver.draw(r2);
    }

    public void draw(Canvas r2, ImageReceiver.BackgroundThreadDrawHolder r3, boolean r4) {
        ImageReceiver r42 = this.imageReceiver;
        if (r42 != null) goto L5;
        return;
    L5:
        r42.setAlpha(this.alpha);
        this.imageReceiver.draw(r2, r3);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return (int) (this.alpha * 255.0f);
    }

    public TLRPC.Document getDocument() {
        return this.document;
    }

    public long getDocumentId() {
        TLRPC.Document r0 = this.document;
        if (r0 == null) goto L7;
        return r0.id;
    L7:
        return this.documentId;
    }

    public ImageReceiver getImageReceiver() {
        return this.imageReceiver;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(this.sizedp);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(this.sizedp);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public void invalidate() {
        int r1 = 0;
        if (this.views == null) goto L12;
        int r0 = 0;
    L6:
        if (r0 >= this.views.size()) goto L12;
        View r2 = this.views.get(r0);
        if (r2 == null) goto L10;
        r2.invalidate();
    L10:
        r0 = r0 + 1;
    L12:
        if (this.holders != null) goto L14;
        return;
    L14:
        if (r1 >= this.holders.size()) goto L26;
        AnimatedEmojiSpan.InvalidateHolder r02 = this.holders.get(r1);
        if (r02 == null) goto L18;
        r02.invalidate();
    L18:
        r1 = r1 + 1;
        goto L14
    }

    public boolean isDefaultStatusEmoji() {
        Boolean r0 = this.isDefaultStatusEmojiCached;
        if (r0 != null) goto L5;
        TLRPC.Document r02 = this.document;
        boolean r1 = false;
        if (r02 == null) goto L18;
        TLRPC.InputStickerSet r03 = MessageObject.getInputStickerSet(r02);
        if ((r03 instanceof TLRPC.TL_inputStickerSetEmojiDefaultStatuses) == false) goto L11;
    L16:
        r1 = true;
    L17:
        this.isDefaultStatusEmojiCached = Boolean.valueOf(r1);
        goto L18
    L11:
        if ((r03 instanceof TLRPC.TL_inputStickerSetID) == false) goto L17;
        long r2 = r03.id;
        if (r2 == 773947703670341676L) goto L16;
        if (r2 != 2964141614563343L) goto L17;
    L18:
        return r1;
    L5:
        return r0.booleanValue();
    }

    public void preload() {
        this.preloading = true;
        updateAttachState();
    }

    public void removeView(View r2) {
        ArrayList<View> r0 = this.views;
        if (r0 == null) goto L5;
        r0.remove(r2);
    L5:
        this.preloading = false;
        updateAttachState();
    }

    public void removeView(AnimatedEmojiSpan.InvalidateHolder r2) {
        ArrayList<AnimatedEmojiSpan.InvalidateHolder> r0 = this.holders;
        if (r0 == null) goto L5;
        r0.remove(r2);
    L5:
        this.preloading = false;
        updateAttachState();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        float r22 = r2 / 255.0f;
        this.alpha = r22;
        ImageReceiver r1 = this.imageReceiver;
        if (r1 == null) goto L6;
        r1.setAlpha(r22);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        if (this.imageReceiver != null) goto L5;
    L11:
        this.colorFilterToSet = r2;
        return;
    L5:
        if (this.document == null) goto L11;
        if (canOverrideColor() == false) goto L13;
        this.imageReceiver.setColorFilter(r2);
        return;
    }

    public void setTime(long r3) {
        ImageReceiver r0 = this.imageReceiver;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.cacheType != 8) goto L7;
        r3 = 0;
    L7:
        r0.setCurrentTime(r3);
    }

    public void setupDocument(TLRPC.Document r1) {
        this.document = r1;
        initDocument(false);
    }

    public void setupEmojiThumb(String r3) {
        int r0 = this.cacheType;
        if (r0 == 20) goto L8;
        if (r0 == 21) goto L8;
        return;
    L8:
        if (TextUtils.isEmpty(r3) == false) goto L11;
        return;
    L11:
        if (this.imageReceiver == null) goto L13;
        return;
    L13:
        createImageReceiver();
        this.imageReceiverEmojiThumb = true;
        this.imageReceiver.setImageBitmap(Emoji.getEmojiDrawable(r3));
        this.imageReceiver.setCrossfadeWithOldImage(true);
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("AnimatedEmojiDrawable{");
        TLRPC.Document r2 = this.document;
        if (r2 != null) goto L5;
        String r22 = "null";
    L6:
        r0.append(r22);
        r0.append("}");
        return r0.toString();
    L5:
        r22 = MessageObject.findAnimatedEmojiEmoticon(r2, null);
        goto L6
    }

    public void update(long r4) {
        ImageReceiver r0 = this.imageReceiver;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.cacheType != 8) goto L8;
        r4 = 0;
    L8:
        if (r0.getLottieAnimation() == null) goto L11;
        this.imageReceiver.getLottieAnimation().updateCurrentFrame(r4, true);
    L11:
        if (this.imageReceiver.getAnimation() == null) goto L15;
        this.imageReceiver.getAnimation().updateCurrentFrame(r4, true);
        return;
    }
}
