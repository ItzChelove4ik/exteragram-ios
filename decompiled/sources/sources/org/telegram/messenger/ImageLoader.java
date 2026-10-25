package org.telegram.messenger;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import androidx.core.graphics.ColorUtils;
import j$.io.FileRetargetClass;
import j$.nio.file.CopyOption;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.DispatchQueuePriority;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FilePathDatabase;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.secretmedia.EncryptedFileInputStream;
import org.telegram.messenger.utils.BitmapsCache;
import org.telegram.messenger.wallpaper.WallpaperGiftBitmapDrawable;
import org.telegram.messenger.wallpaper.WallpaperGiftPatternPosition;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.AnimatedFileDrawable;
import org.telegram.ui.Components.BackgroundGradientDrawable;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.RLottieDiceDrawable;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.SlotsDrawable;
import org.telegram.ui.Components.ThemePreviewDrawable;
import org.telegram.ui.web.WebInstantView;

/* loaded from: classes.dex */
public class ImageLoader {
    public static final String AUTOPLAY_FILTER = "g";
    public static final String AUTOPLAY_FILTER_NONLOOP = "gl";
    public static final int CACHE_TYPE_CACHE = 1;
    public static final int CACHE_TYPE_ENCRYPTED = 2;
    public static final int CACHE_TYPE_NONE = 0;
    private static final boolean DEBUG_MODE = false;
    private static volatile ImageLoader Instance;
    private static ThreadLocal<byte[]> bytesLocal;
    private static ThreadLocal<byte[]> bytesThumbLocal;
    private static byte[] header;
    private static byte[] headerThumb;
    private LinkedList<ArtworkLoadTask> artworkTasks;
    private HashMap<String, Integer> bitmapUseCounts;
    private DispatchQueuePriority cacheOutQueue;
    private DispatchQueue cacheThumbOutQueue;
    ArrayList<AnimatedFileDrawable> cachedAnimatedFileDrawables;
    private boolean canForce8888;
    private int currentArtworkTasksCount;
    private int currentHttpFileLoadTasksCount;
    private int currentHttpTasksCount;
    private ConcurrentHashMap<String, long[]> fileProgresses;
    private HashMap<String, Integer> forceLoadingImages;
    private LinkedList<HttpFileTask> httpFileLoadTasks;
    private HashMap<String, HttpFileTask> httpFileLoadTasksByKeys;
    private LinkedList<HttpImageTask> httpTasks;
    private String ignoreRemoval;
    private DispatchQueue imageLoadQueue;
    public ConcurrentHashMap<String, CacheImage> imageLoadingByKeys;
    private SparseArray<CacheImage> imageLoadingByTag;
    private HashMap<String, CacheImage> imageLoadingByUrl;
    private HashMap<String, CacheImage> imageLoadingByUrlPframe;
    public HashSet<String> imageLoadingKeys;
    private volatile long lastCacheOutTime;
    private int lastImageNum;
    private LruCache<BitmapDrawable> lottieMemCache;
    private LruCache<BitmapDrawable> memCache;
    private HashMap<String, String> replacedBitmaps;
    private HashMap<String, Runnable> retryHttpsTasks;
    private LruCache<BitmapDrawable> smallImagesMemCache;
    private File telegramPath;
    private ConcurrentHashMap<String, WebFile> testWebFile;
    private HashMap<String, ThumbGenerateTask> thumbGenerateTasks;
    private DispatchQueue thumbGeneratingQueue;
    private HashMap<String, ThumbGenerateInfo> waitingForQualityThumb;
    private SparseArray<String> waitingForQualityThumbByTag;
    private LruCache<BitmapDrawable> wallpaperMemCache;

    /* renamed from: org.telegram.messenger.ImageLoader$1, reason: invalid class name */
    public class AnonymousClass1 extends LruCache<BitmapDrawable> {
        final /* synthetic */ ImageLoader this$0;

        public AnonymousClass1(ImageLoader r1, int r2) {
            this.this$0 = r1;
            super(r2);
        }

        /* renamed from: entryRemoved, reason: avoid collision after fix types in other method */
        public void entryRemoved2(boolean r1, String r2, BitmapDrawable r3, BitmapDrawable r4) {
            if (ImageLoader.m4144$$Nest$fgetignoreRemoval(this.this$0) != null) goto L5;
        L7:
            Integer r0 = (Integer) ImageLoader.m4137$$Nest$fgetbitmapUseCounts(this.this$0).get(r2);
            if (r0 != null) goto L10;
        L11:
            Bitmap r02 = r3.getBitmap();
            if (r02.isRecycled() == true) goto L16;
            ArrayList r12 = new ArrayList();
            r12.add(r02);
            AndroidUtilities.recycleBitmaps(r12);
            return;
        L16:
            return;
        L10:
            if (r0.intValue() == 0) goto L11;
            return;
        L5:
            if (ImageLoader.m4144$$Nest$fgetignoreRemoval(this.this$0).equals(r2) == false) goto L7;
        }

        @Override // org.telegram.messenger.LruCache
        public /* bridge */ /* synthetic */ void entryRemoved(boolean r1, String r2, BitmapDrawable r3, BitmapDrawable r4) {
            entryRemoved2(r1, r2, r3, r4);
        }

        /* renamed from: sizeOf, reason: avoid collision after fix types in other method */
        public int sizeOf2(String r1, BitmapDrawable r2) {
            return ImageLoader.m4165$$Nest$msizeOfBitmapDrawable(this.this$0, r2);
        }

        @Override // org.telegram.messenger.LruCache
        public /* bridge */ /* synthetic */ int sizeOf(String r1, BitmapDrawable r2) {
            return sizeOf2(r1, r2);
        }
    }

    /* renamed from: org.telegram.messenger.ImageLoader$2, reason: invalid class name */
    public class AnonymousClass2 extends LruCache<BitmapDrawable> {
        final /* synthetic */ ImageLoader this$0;

        public AnonymousClass2(ImageLoader r1, int r2) {
            this.this$0 = r1;
            super(r2);
        }

        /* renamed from: entryRemoved, reason: avoid collision after fix types in other method */
        public void entryRemoved2(boolean r1, String r2, BitmapDrawable r3, BitmapDrawable r4) {
            if (ImageLoader.m4144$$Nest$fgetignoreRemoval(this.this$0) != null) goto L5;
        L7:
            Integer r0 = (Integer) ImageLoader.m4137$$Nest$fgetbitmapUseCounts(this.this$0).get(r2);
            if (r0 != null) goto L10;
        L11:
            Bitmap r02 = r3.getBitmap();
            if (r02.isRecycled() == true) goto L16;
            ArrayList r12 = new ArrayList();
            r12.add(r02);
            AndroidUtilities.recycleBitmaps(r12);
            return;
        L16:
            return;
        L10:
            if (r0.intValue() == 0) goto L11;
            return;
        L5:
            if (ImageLoader.m4144$$Nest$fgetignoreRemoval(this.this$0).equals(r2) == false) goto L7;
        }

        @Override // org.telegram.messenger.LruCache
        public /* bridge */ /* synthetic */ void entryRemoved(boolean r1, String r2, BitmapDrawable r3, BitmapDrawable r4) {
            entryRemoved2(r1, r2, r3, r4);
        }

        /* renamed from: sizeOf, reason: avoid collision after fix types in other method */
        public int sizeOf2(String r1, BitmapDrawable r2) {
            return ImageLoader.m4165$$Nest$msizeOfBitmapDrawable(this.this$0, r2);
        }

        @Override // org.telegram.messenger.LruCache
        public /* bridge */ /* synthetic */ int sizeOf(String r1, BitmapDrawable r2) {
            return sizeOf2(r1, r2);
        }
    }

    /* renamed from: org.telegram.messenger.ImageLoader$3, reason: invalid class name */
    public class AnonymousClass3 extends LruCache<BitmapDrawable> {
        final /* synthetic */ ImageLoader this$0;

        public AnonymousClass3(ImageLoader r1, int r2) {
            this.this$0 = r1;
            super(r2);
        }

        /* renamed from: sizeOf, reason: avoid collision after fix types in other method */
        public int sizeOf2(String r1, BitmapDrawable r2) {
            return ImageLoader.m4165$$Nest$msizeOfBitmapDrawable(this.this$0, r2);
        }

        @Override // org.telegram.messenger.LruCache
        public /* bridge */ /* synthetic */ int sizeOf(String r1, BitmapDrawable r2) {
            return sizeOf2(r1, r2);
        }
    }

    /* renamed from: org.telegram.messenger.ImageLoader$4, reason: invalid class name */
    public class AnonymousClass4 extends LruCache<BitmapDrawable> {
        final /* synthetic */ ImageLoader this$0;

        public AnonymousClass4(ImageLoader r1, int r2) {
            this.this$0 = r1;
            super(r2);
        }

        /* renamed from: entryRemoved, reason: avoid collision after fix types in other method */
        public void entryRemoved2(boolean r1, String r2, BitmapDrawable r3, BitmapDrawable r4) {
            Integer r12 = (Integer) ImageLoader.m4137$$Nest$fgetbitmapUseCounts(this.this$0).get(r2);
            boolean r22 = r3 instanceof AnimatedFileDrawable;
            if (r22 == false) goto L5;
            this.this$0.cachedAnimatedFileDrawables.remove((AnimatedFileDrawable) r3);
        L5:
            if (r12 != null) goto L7;
        L8:
            if (r22 == false) goto L11;
            ((AnimatedFileDrawable) r3).recycle();
        L11:
            if ((r3 instanceof RLottieDrawable) == false) goto L15;
            ((RLottieDrawable) r3).recycle(false);
            return;
        L15:
            return;
        L7:
            if (r12.intValue() == 0) goto L8;
        }

        @Override // org.telegram.messenger.LruCache
        public /* bridge */ /* synthetic */ void entryRemoved(boolean r1, String r2, BitmapDrawable r3, BitmapDrawable r4) {
            entryRemoved2(r1, r2, r3, r4);
        }

        /* renamed from: put, reason: avoid collision after fix types in other method */
        public BitmapDrawable put2(String r3, BitmapDrawable r4) {
            if ((r4 instanceof AnimatedFileDrawable) == false) goto L6;
            this.this$0.cachedAnimatedFileDrawables.add((AnimatedFileDrawable) r4);
        L6:
            return (BitmapDrawable) super.put(r3, r4);
        }

        @Override // org.telegram.messenger.LruCache
        public /* bridge */ /* synthetic */ BitmapDrawable put(String r1, BitmapDrawable r2) {
            return put2(r1, r2);
        }

        /* renamed from: sizeOf, reason: avoid collision after fix types in other method */
        public int sizeOf2(String r1, BitmapDrawable r2) {
            return ImageLoader.m4165$$Nest$msizeOfBitmapDrawable(this.this$0, r2);
        }

        @Override // org.telegram.messenger.LruCache
        public /* bridge */ /* synthetic */ int sizeOf(String r1, BitmapDrawable r2) {
            return sizeOf2(r1, r2);
        }
    }

    /* renamed from: org.telegram.messenger.ImageLoader$5, reason: invalid class name */
    public class AnonymousClass5 implements FileLoader.FileLoaderDelegate {
        final /* synthetic */ ImageLoader this$0;
        final /* synthetic */ int val$currentAccount;

        public static /* synthetic */ void $r8$lambda$9IF48TRkTZ9Rd7pg1RG69iHkZ08(int r1, String r2, boolean r3) {
            NotificationCenter.getInstance(r1).postNotificationName(NotificationCenter.fileUploadFailed, new Object[]{r2, Boolean.valueOf(r3)});
        }

        public static /* synthetic */ void $r8$lambda$Bsqqpu4ROY59Lf02mMMvM7KgGCU(AnonymousClass5 r0, int r1, String r2, TLRPC.InputFile r3, TLRPC.InputEncryptedFile r4, byte[] r5, byte[] r6, long r7) {
            r0.lambda$fileDidUploaded$2(r1, r2, r3, r4, r5, r6, r7);
        }

        /* renamed from: $r8$lambda$O-hF9zrrow6ehW6FrQqF55m4ivM, reason: not valid java name */
        public static /* synthetic */ void m4171$r8$lambda$OhF9zrrow6ehW6FrQqF55m4ivM(AnonymousClass5 r0, String r1, FileLoadOperation r2) {
            r0.lambda$fileLoadProgressChanged$7(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$SnQAL7BXfHJwbFVKLbwdrhWLqn0(AnonymousClass5 r0, int r1, String r2, boolean r3) {
            r0.lambda$fileDidFailedUpload$4(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$ZEmXmc7AzdAM5-DLHl8zJVuWgeI, reason: not valid java name */
        public static /* synthetic */ void m4172$r8$lambda$ZEmXmc7AzdAM5DLHl8zJVuWgeI(AnonymousClass5 r0, File r1, String r2, int r3, Object r4, int r5) {
            r0.lambda$fileDidLoaded$5(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$bH0hUPpVEi6rZd3xwmsKt6LUGmU(int r1, String r2, TLRPC.InputFile r3, TLRPC.InputEncryptedFile r4, byte[] r5, byte[] r6, long r7) {
            NotificationCenter.getInstance(r1).postNotificationName(NotificationCenter.fileUploaded, new Object[]{r2, r3, r4, r5, r6, Long.valueOf(r7)});
        }

        public static /* synthetic */ void $r8$lambda$rbgw7BzVmXiPjX5xFH7hC_RdhyE(int r1, String r2, long r3, long r5, boolean r7) {
            NotificationCenter.getInstance(r1).postNotificationName(NotificationCenter.fileUploadProgressChanged, new Object[]{r2, Long.valueOf(r3), Long.valueOf(r5), Boolean.valueOf(r7)});
        }

        public static /* synthetic */ void $r8$lambda$xK5tgcSi_XcqleFYssNAHWlpQVw(int r1, String r2, long r3, long r5) {
            NotificationCenter.getInstance(r1).postNotificationName(NotificationCenter.fileLoadProgressChanged, new Object[]{r2, Long.valueOf(r3), Long.valueOf(r5)});
        }

        public static /* synthetic */ void $r8$lambda$y_V3inH5vC_zefByI4vhQvioVc4(AnonymousClass5 r0, String r1, int r2, int r3) {
            r0.lambda$fileDidFailedLoad$6(r1, r2, r3);
        }

        public AnonymousClass5(ImageLoader r1, int r2) {
            this.this$0 = r1;
            this.val$currentAccount = r2;
        }

        private /* synthetic */ void lambda$fileDidFailedLoad$6(String r1, int r2, int r3) {
            ImageLoader.m4157$$Nest$mfileDidFailedLoad(this.this$0, r1, r2);
            NotificationCenter.getInstance(r3).postNotificationName(NotificationCenter.fileLoadFailed, new Object[]{r1, Integer.valueOf(r2)});
        }

        private /* synthetic */ void lambda$fileDidFailedUpload$4(int r2, String r3, boolean r4) {
            AndroidUtilities.runOnUIThread(new ImageLoader$5$$ExternalSyntheticLambda0(r2, r3, r4));
            ImageLoader.m4141$$Nest$fgetfileProgresses(this.this$0).remove(r3);
        }

        private /* synthetic */ void lambda$fileDidLoaded$5(File r6, String r7, int r8, Object r9, int r10) {
            if (r6 != null) goto L4;
        L23:
            NotificationCenter.getInstance(r8).postNotificationName(NotificationCenter.fileLoaded, new Object[]{r7, r6});
            ImageLoader.m4158$$Nest$mfileDidLoaded(this.this$0, r7, r6, r10);
            return;
        L4:
            if (r7.endsWith(".mp4") == false) goto L6;
        L7:
            FilePathDatabase.FileMeta r0 = FileLoader.getFileMetadataFromParent(r8, r9);
            if (r0 == null) goto L23;
            if ((r9 instanceof MessageObject) == false) goto L12;
            MessageObject r92 = (MessageObject) r9;
        L13:
            long r1 = r0.dialogId;
            if (r1 < 0) goto L17;
            int r12 = 1;
        L21:
            if (SaveToGallerySettingsHelper.needSave(r12, r0, r92, r8) == false) goto L23;
            AndroidUtilities.addMediaToGallery(r6.toString());
            goto L23
        L17:
            if (ChatObject.isChannelAndNotMegaGroup(MessagesController.getInstance(r8).getChat(Long.valueOf(-r1))) == false) goto L19;
            r12 = 4;
            goto L21
        L19:
            r12 = 2;
            goto L21
        L12:
            r92 = null;
            goto L13
        L6:
            if (r7.endsWith(".jpg") == false) goto L23;
            goto L7
        }

        private /* synthetic */ void lambda$fileDidUploaded$2(int r10, String r11, TLRPC.InputFile r12, TLRPC.InputEncryptedFile r13, byte[] r14, byte[] r15, long r16) {
            AndroidUtilities.runOnUIThread(new ImageLoader$5$$ExternalSyntheticLambda7(r10, r11, r12, r13, r14, r15, r16));
            ImageLoader.m4141$$Nest$fgetfileProgresses(this.this$0).remove(r11);
        }

        private /* synthetic */ void lambda$fileLoadProgressChanged$7(String r12, FileLoadOperation r13) {
            CacheImage r0 = (CacheImage) ImageLoader.m4148$$Nest$fgetimageLoadingByUrlPframe(this.this$0).remove(r12);
            if (r0 == null) goto L19;
            ImageLoader.m4147$$Nest$fgetimageLoadingByUrl(this.this$0).remove(r12);
            ArrayList r122 = new ArrayList();
            int r1 = 0;
            int r2 = 0;
        L7:
            if (r2 >= r0.imageReceiverArray.size()) goto L13;
            String r6 = r0.keys.get(r2);
            String r7 = r0.filters.get(r2);
            int r8 = r0.types.get(r2).intValue();
            ImageReceiver r5 = r0.imageReceiverArray.get(r2);
            int r9 = r0.imageReceiverGuidsArray.get(r2).intValue();
            CacheImage r3 = this.this$0.imageLoadingByKeys.get(r6);
            if (r3 != null) goto L11;
            r3 = new CacheImage(this.this$0, null);
            r3.priority = r0.priority;
            r3.secureDocument = r0.secureDocument;
            r3.currentAccount = r0.currentAccount;
            r3.finalFilePath = r13.getCurrentFile();
            r3.parentObject = r0.parentObject;
            r3.isPFrame = r0.isPFrame;
            r3.key = r6;
            r3.imageLocation = r0.imageLocation;
            r3.type = r8;
            r3.ext = r0.ext;
            r3.encryptionKeyPath = r0.encryptionKeyPath;
            r3.cacheTask = new CacheOutTask(this.this$0, r3);
            r3.filter = r7;
            r3.imageType = r0.imageType;
            r3.cacheType = r0.cacheType;
            this.this$0.imageLoadingByKeys.put(r6, r3);
            this.this$0.imageLoadingKeys.add(ImageLoader.m4170$$Nest$smcutFilter(r6));
            r122.add(r3.cacheTask);
        L11:
            r3.addImageReceiver(r5, r6, r7, r8, r9);
            r2 = r2 + 1;
        L13:
            if (r1 >= r122.size()) goto L26;
            CacheOutTask r132 = (CacheOutTask) r122.get(r1);
            int r02 = CacheOutTask.m4176$$Nest$fgetcacheImage(r132).type;
            ImageLoader r22 = this.this$0;
            if (r02 != 1) goto L17;
            ImageLoader.m4139$$Nest$fgetcacheThumbOutQueue(r22).postRunnable(r132);
        L18:
            r1 = r1 + 1;
            goto L13
        L17:
            ImageLoader.m4138$$Nest$fgetcacheOutQueue(r22).postRunnable(r132, CacheOutTask.m4176$$Nest$fgetcacheImage(r132).priority);
            goto L18
        L26:
            return;
        }

        @Override // org.telegram.messenger.FileLoader.FileLoaderDelegate
        public void fileDidFailedLoad(String r3, int r4) {
            ImageLoader.m4141$$Nest$fgetfileProgresses(this.this$0).remove(r3);
            AndroidUtilities.runOnUIThread(new ImageLoader$5$$ExternalSyntheticLambda6(this, r3, r4, this.val$currentAccount));
        }

        @Override // org.telegram.messenger.FileLoader.FileLoaderDelegate
        public void fileDidFailedUpload(String r4, boolean r5) {
            Utilities.stageQueue.postRunnable(new ImageLoader$5$$ExternalSyntheticLambda2(this, this.val$currentAccount, r4, r5));
        }

        @Override // org.telegram.messenger.FileLoader.FileLoaderDelegate
        public void fileDidLoaded(String r9, File r10, Object r11, int r12) {
            ImageLoader.m4141$$Nest$fgetfileProgresses(this.this$0).remove(r9);
            AndroidUtilities.runOnUIThread(new ImageLoader$5$$ExternalSyntheticLambda1(this, r10, r9, this.val$currentAccount, r11, r12));
        }

        @Override // org.telegram.messenger.FileLoader.FileLoaderDelegate
        public void fileDidUploaded(String r12, TLRPC.InputFile r13, TLRPC.InputEncryptedFile r14, byte[] r15, byte[] r16, long r17) {
            Utilities.stageQueue.postRunnable(new ImageLoader$5$$ExternalSyntheticLambda8(this, this.val$currentAccount, r12, r13, r14, r15, r16, r17));
        }

        @Override // org.telegram.messenger.FileLoader.FileLoaderDelegate
        public void fileLoadProgressChanged(FileLoadOperation r9, String r10, long r11, long r13) {
            ImageLoader.m4141$$Nest$fgetfileProgresses(this.this$0).put(r10, new long[]{r11, r13});
            if (ImageLoader.m4148$$Nest$fgetimageLoadingByUrlPframe(this.this$0).isEmpty() == false) goto L5;
        L7:
            long r0 = SystemClock.elapsedRealtime();
            long r2 = r9.lastProgressUpdateTime;
            if (r2 != 0) goto L10;
        L15:
            r9.lastProgressUpdateTime = r0;
            AndroidUtilities.runOnUIThread(new ImageLoader$5$$ExternalSyntheticLambda5(this.val$currentAccount, r10, r11, r13));
            return;
        L10:
            if (r2 < (r0 - 500)) goto L15;
            if (r11 == 0) goto L15;
            return;
        L5:
            if (r9.checkPrefixPreloadFinished() == false) goto L7;
            ImageLoader.m4145$$Nest$fgetimageLoadQueue(this.this$0).postRunnable(new ImageLoader$5$$ExternalSyntheticLambda4(this, r10, r9));
            goto L7
        }

        @Override // org.telegram.messenger.FileLoader.FileLoaderDelegate
        public void fileUploadProgressChanged(FileUploadOperation r7, String r8, long r9, long r11, boolean r13) {
            ImageLoader.m4141$$Nest$fgetfileProgresses(this.this$0).put(r8, new long[]{r9, r11});
            long r0 = SystemClock.elapsedRealtime();
            long r2 = r7.lastProgressUpdateTime;
            if (r2 != 0) goto L5;
        L10:
            r7.lastProgressUpdateTime = r0;
            AndroidUtilities.runOnUIThread(new ImageLoader$5$$ExternalSyntheticLambda3(this.val$currentAccount, r8, r9, r11, r13));
            return;
        L5:
            if (r2 < (r0 - 100)) goto L10;
            if (r9 == r11) goto L10;
        }
    }

    /* renamed from: org.telegram.messenger.ImageLoader$6, reason: invalid class name */
    public class AnonymousClass6 extends BroadcastReceiver {
        final /* synthetic */ ImageLoader this$0;

        public static /* synthetic */ void $r8$lambda$AoOE3daVqWElNJBUL1awpYN12Lc(AnonymousClass6 r0) {
            r0.lambda$onReceive$0();
        }

        public AnonymousClass6(ImageLoader r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onReceive$0() {
            this.this$0.checkMediaPaths();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r3, Intent r4) {
            if (BuildVars.LOGS_ENABLED == false) goto L5;
            FileLog.d("file system changed");
        L5:
            ImageLoader$6$$ExternalSyntheticLambda0 r32 = new ImageLoader$6$$ExternalSyntheticLambda0(this);
            if ("android.intent.action.MEDIA_UNMOUNTED".equals(r4.getAction()) == false) goto L9;
            AndroidUtilities.runOnUIThread(r32, 1000);
            return;
        L9:
            r32.run();
        }
    }

    /* renamed from: org.telegram.messenger.ImageLoader$7, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$CompressFormat = null;

        static {
            int[] r0 = new int[Bitmap.CompressFormat.values().length];
            $SwitchMap$android$graphics$Bitmap$CompressFormat = r0;
            r0[Bitmap.CompressFormat.WEBP.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L12:
            $SwitchMap$android$graphics$Bitmap$CompressFormat[ImageLoader$7$$ExternalSyntheticApiModelOutline0.m().ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L10:
            $SwitchMap$android$graphics$Bitmap$CompressFormat[ImageLoader$7$$ExternalSyntheticApiModelOutline1.m().ordinal()] = 3;     // Catch: NoSuchFieldError -> L9
            return;
        }
    }

    /* loaded from: classes5.dex */
    public class ArtworkLoadTask extends AsyncTask<Void, Void, String> {
        private CacheImage cacheImage;
        private boolean canRetry;
        private HttpURLConnection httpConnection;
        private boolean small;
        final /* synthetic */ ImageLoader this$0;

        /* renamed from: $r8$lambda$NUBNzbZZlWeQqeFY-fFvGP4TV7o, reason: not valid java name */
        public static /* synthetic */ void m4173$r8$lambda$NUBNzbZZlWeQqeFYfFvGP4TV7o(ArtworkLoadTask r0, String r1) {
            r0.lambda$onPostExecute$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$SCTXkfdulIc2F0MN_11foOxO8fw(ArtworkLoadTask r0) {
            r0.lambda$onPostExecute$1();
        }

        public static /* synthetic */ void $r8$lambda$iiFUQrpQ47WMcpKB0kltcR3ksbk(ArtworkLoadTask r0) {
            r0.lambda$onCancelled$2();
        }

        /* renamed from: -$$Nest$fgetcacheImage, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CacheImage m4174$$Nest$fgetcacheImage(ArtworkLoadTask r0) {
            return r0.cacheImage;
        }

        public ArtworkLoadTask(ImageLoader r2, CacheImage r3) {
            this.this$0 = r2;
            boolean r22 = true;
            this.canRetry = true;
            this.cacheImage = r3;
            if (Uri.parse(r3.imageLocation.path).getQueryParameter("s") != null) goto L6;
            r22 = false;
        L6:
            this.small = r22;
        }

        private /* synthetic */ void lambda$onCancelled$2() {
            ImageLoader.m4162$$Nest$mrunArtworkTasks(this.this$0, true);
        }

        private /* synthetic */ void lambda$onPostExecute$0(String r5) {
            CacheImage r0 = this.cacheImage;
            r0.httpTask = new HttpImageTask(this.this$0, r0, 0, r5);
            ImageLoader.m4143$$Nest$fgethttpTasks(this.this$0).add(this.cacheImage.httpTask);
            ImageLoader.m4164$$Nest$mrunHttpTasks(this.this$0, false);
        }

        private /* synthetic */ void lambda$onPostExecute$1() {
            ImageLoader.m4162$$Nest$mrunArtworkTasks(this.this$0, true);
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ String doInBackground(Void[] r1) {
            return doInBackground2(r1);
        }

        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public String doInBackground2(Void... r8) {
            HttpURLConnection r1 = (HttpURLConnection) new URL(this.cacheImage.imageLocation.path.replace("athumb://", "https://")).openConnection();     // Catch: Throwable -> L14
            this.httpConnection = r1;     // Catch: Throwable -> L14
            r1.setConnectTimeout(5000);     // Catch: Throwable -> L14
            this.httpConnection.setReadTimeout(5000);     // Catch: Throwable -> L14
            this.httpConnection.connect();     // Catch: Throwable -> L14
            HttpURLConnection r12 = this.httpConnection;     // Catch: Throwable -> L14 Exception -> L16
            if (r12 == null) goto L18;
            int r13 = r12.getResponseCode();     // Catch: Throwable -> L14 Exception -> L16
            if (r13 == 200) goto L18;
            if (r13 == 202) goto L18;
            if (r13 == 304) goto L18;
            this.canRetry = false;     // Catch: Throwable -> L14 Exception -> L16
        L18:
            InputStream r14 = this.httpConnection.getInputStream();     // Catch: Throwable -> L14
            ByteArrayOutputStream r2 = new ByteArrayOutputStream();     // Catch: Throwable -> L65
            byte[] r3 = new byte[32768];     // Catch: Throwable -> L28
        L23:
            if (isCancelled() == true) goto L30;
            int r4 = r14.read(r3);     // Catch: Throwable -> L28
            if (r4 <= 0) goto L30;
            r2.write(r3, 0, r4);     // Catch: Throwable -> L28
        L30:
            this.canRetry = false;     // Catch: Throwable -> L28
            JSONArray r32 = new JSONObject(new String(r2.toByteArray())).getJSONArray("results");     // Catch: Throwable -> L28
            if (r32.length() <= 0) goto L152;
            String r33 = r32.getJSONObject(0).getString("artworkUrl100");     // Catch: Throwable -> L28
            if (this.small == true) goto L129;
            String r82 = r33.replace("100x100", "600x600");     // Catch: Throwable -> L28
            HttpURLConnection r7 = this.httpConnection;     // Catch: Throwable -> L110
            if (r7 == null) goto L48;
            r7.disconnect();     // Catch: Throwable -> L110
        L48:
            if (r14 != null) goto L138;
        L123:
            r2.close();     // Catch: Exception -> L111
        L54:
            return r82;
        L138:
            r14.close();     // Catch: Throwable -> L51
        L51:
            th = move-exception;
            FileLog.e(th);
            goto L123
        L129:
            HttpURLConnection r72 = this.httpConnection;     // Catch: Throwable -> L112
            if (r72 == null) goto L37;
            r72.disconnect();     // Catch: Throwable -> L112
        L37:
            if (r14 == null) goto L146;
            r14.close();     // Catch: Throwable -> L40
        L40:
            th = move-exception;
            FileLog.e(th);
        L146:
            r2.close();     // Catch: Exception -> L113
        L43:
            return r33;
        L152:
            HttpURLConnection r73 = this.httpConnection;     // Catch: Throwable -> L114
            if (r73 == null) goto L58;
            r73.disconnect();     // Catch: Throwable -> L114
        L58:
            if (r14 != null) goto L156;
        L63:
            r2.close();     // Catch: Exception -> L115
        L135:
            goto L98
        L156:
            r14.close();     // Catch: Throwable -> L61
        L61:
            th = move-exception;
            FileLog.e(th);
        L28:
            th = move-exception;
            InputStream r22 = r14;
            th = th;
            ByteArrayOutputStream r34 = r2;
        L150:
        L72:
            th = move-exception;
            HttpURLConnection r74 = this.httpConnection;     // Catch: Throwable -> L117
            if (r74 == null) goto L102;
            r74.disconnect();     // Catch: Throwable -> L117
        L102:
            if (r22 != null) goto L133;
        L107:
            if (r34 == null) goto L160;
            r34.close();     // Catch: Exception -> L118
            throw th;
        L161:
            throw th;
        L160:
            throw th;
        L133:
            r22.close();     // Catch: Throwable -> L105
        L105:
            th = move-exception;
            FileLog.e(th);
            goto L107
        L68:
            if ((th instanceof SocketTimeoutException) == false) goto L75;
            if (ApplicationLoader.isNetworkOnline() == false) goto L87;
            this.canRetry = false;     // Catch: Throwable -> L72
        L87:
            FileLog.e(th);     // Catch: Throwable -> L72
            HttpURLConnection r75 = this.httpConnection;     // Catch: Throwable -> L116
            if (r75 == null) goto L91;
            r75.disconnect();     // Catch: Throwable -> L116
        L91:
            if (r22 != null) goto L148;
        L96:
            if (r34 == null) goto L98;
            r34.close();     // Catch: Exception -> L115
            goto L135
        L148:
            r22.close();     // Catch: Throwable -> L94
        L94:
            th = move-exception;
            FileLog.e(th);
            goto L96
        L75:
            if ((th instanceof UnknownHostException) == false) goto L78;
            this.canRetry = false;     // Catch: Throwable -> L72
            goto L87
        L78:
            if ((th instanceof SocketException) == false) goto L85;
            if (th.getMessage() == null) goto L87;
            if (th.getMessage().contains("ECONNRESET") == false) goto L87;
            this.canRetry = false;     // Catch: Throwable -> L72
            goto L87
        L85:
            if ((th instanceof FileNotFoundException) == false) goto L87;
            this.canRetry = false;     // Catch: Throwable -> L72
        L65:
            th = move-exception;
            r22 = r14;
            th = th;
            r34 = null;
        L16:
            e = move-exception;
            FileLog.e(e);     // Catch: Throwable -> L14
        L14:
            th = th;
            r22 = null;
            r34 = null;
        L98:
            return null;
        }

        @Override // android.os.AsyncTask
        public void onCancelled() {
            ImageLoader.m4145$$Nest$fgetimageLoadQueue(this.this$0).postRunnable(new ImageLoader$ArtworkLoadTask$$ExternalSyntheticLambda0(this));
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(String r1) {
            onPostExecute2(r1);
        }

        /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        public void onPostExecute2(String r3) {
            if (r3 == null) goto L5;
            ImageLoader.m4145$$Nest$fgetimageLoadQueue(this.this$0).postRunnable(new ImageLoader$ArtworkLoadTask$$ExternalSyntheticLambda1(this, r3));
        L7:
            ImageLoader.m4145$$Nest$fgetimageLoadQueue(this.this$0).postRunnable(new ImageLoader$ArtworkLoadTask$$ExternalSyntheticLambda2(this));
            return;
        L5:
            if (this.canRetry == false) goto L7;
            ImageLoader.m4156$$Nest$martworkLoadError(this.this$0, this.cacheImage.url);
            goto L7
        }
    }

    public class CacheImage {
        protected ArtworkLoadTask artworkTask;
        protected CacheOutTask cacheTask;
        protected int cacheType;
        protected int currentAccount;
        protected File encryptionKeyPath;
        protected String ext;
        protected String filter;
        protected ArrayList<String> filters;
        protected File finalFilePath;
        protected HttpImageTask httpTask;
        protected ImageLocation imageLocation;
        protected ArrayList<ImageReceiver> imageReceiverArray;
        protected ArrayList<Integer> imageReceiverGuidsArray;
        protected int imageType;
        public boolean isPFrame;
        protected String key;
        protected ArrayList<String> keys;
        protected Object parentObject;
        public int priority;
        public Runnable runningTask;
        protected SecureDocument secureDocument;
        protected long size;
        protected File tempFilePath;
        final /* synthetic */ ImageLoader this$0;
        protected int type;
        protected ArrayList<Integer> types;
        protected String url;

        public static /* synthetic */ void $r8$lambda$IV0AZLYKqLah5g8Cpkpzou3ub6Q(CacheImage r0, Drawable r1, ArrayList r2, ArrayList r3, String r4) {
            r0.lambda$setImageAndClear$0(r1, r2, r3, r4);
        }

        private CacheImage(ImageLoader r1) {
            this.this$0 = r1;
            this.priority = 1;
            this.imageReceiverArray = new ArrayList();
            this.imageReceiverGuidsArray = new ArrayList();
            this.keys = new ArrayList();
            this.filters = new ArrayList();
            this.types = new ArrayList();
        }

        public /* synthetic */ CacheImage(ImageLoader r1, ImageLoaderIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$setImageAndClear$0(Drawable r10, ArrayList r11, ArrayList r12, String r13) {
            int r1 = 0;
            if ((r10 instanceof AnimatedFileDrawable) == false) goto L23;
            AnimatedFileDrawable r0 = (AnimatedFileDrawable) r10;
            if (r0.isWebmSticker == true) goto L23;
            boolean r102 = false;
        L8:
            if (r1 >= r11.size()) goto L20;
            ImageReceiver r3 = (ImageReceiver) r11.get(r1);
            if (r1 != 0) goto L12;
            AnimatedFileDrawable r4 = r0;
        L14:
            if (r3.setImageBitmapByKey(r4, this.key, this.type, false, ((Integer) r12.get(r1)).intValue()) == false) goto L17;
            if (r4 != r0) goto L19;
            r102 = true;
        L19:
            r1 = r1 + 1;
            goto L8
        L17:
            if (r4 == r0) goto L19;
            r4.recycle();
            goto L19
        L12:
            r4 = r0.makeCopy();
            goto L14
        L20:
            if (r102 == true) goto L25;
            r0.recycle();
        L25:
            if (r13 == null) goto L35;
            this.this$0.decrementUseCount(r13);
            return;
        L35:
            return;
        L23:
            if (r1 >= r11.size()) goto L25;
            ((ImageReceiver) r11.get(r1)).setImageBitmapByKey(r10, this.key, this.types.get(r1).intValue(), false, ((Integer) r12.get(r1)).intValue());
            r1 = r1 + 1;
            goto L23
        }

        public void addImageReceiver(ImageReceiver r3, String r4, String r5, int r6, int r7) {
            int r0 = this.imageReceiverArray.indexOf(r3);
            if (r0 >= 0) goto L5;
        L8:
            this.imageReceiverArray.add(r3);
            this.imageReceiverGuidsArray.add(Integer.valueOf(r7));
            this.keys.add(r4);
            this.filters.add(r5);
            this.types.add(Integer.valueOf(r6));
            ImageLoader.m4146$$Nest$fgetimageLoadingByTag(this.this$0).put(r3.getTag(r6), this);
            return;
        L5:
            if (Objects.equals(this.imageReceiverArray.get(r0).getImageKey(), r4) == false) goto L8;
            this.imageReceiverGuidsArray.set(r0, Integer.valueOf(r7));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v0, types: [org.telegram.messenger.FileLoader] */
        /* JADX WARN: Type inference failed for: r6v0 */
        /* JADX WARN: Type inference failed for: r6v1 */
        /* JADX WARN: Type inference failed for: r6v2 */
        /* JADX WARN: Type inference failed for: r6v3 */
        /* JADX WARN: Type inference failed for: r6v4 */
        /* JADX WARN: Type inference failed for: r6v5 */
        /* JADX WARN: Type inference failed for: r6v6 */
        /* JADX WARN: Type inference failed for: r6v7, types: [org.telegram.messenger.SecureDocument] */
        /* JADX WARN: Type inference failed for: r6v8 */
        /* JADX WARN: Type inference failed for: r7v5, types: [org.telegram.messenger.WebFile] */
        /* JADX WARN: Type inference failed for: r7v6 */
        /* JADX WARN: Type inference failed for: r7v7 */
        /* JADX WARN: Type inference failed for: r9v0 */
        /* JADX WARN: Type inference failed for: r9v1, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r9v2 */
        public void changePriority(int r12) {
            ImageLocation r0 = this.imageLocation;
            if (r0 == null) goto L22;
            TLRPC.TL_fileLocationToBeDeprecated r1 = r0.location;
            if (r1 == null) goto L7;
            ?? r9 = this.ext;
            TLRPC.TL_fileLocationToBeDeprecated r8 = r1;
            TLRPC.Document r5 = null;
            ?? r6 = null;
            ?? r7 = 0;
        L20:
            FileLoader.getInstance(this.currentAccount).changePriority(r12, r5, r6, r7, r8, r9, null);
            return;
        L7:
            TLRPC.Document r13 = r0.document;
            if (r13 == null) goto L13;
            r5 = r13;
            r6 = null;
        L10:
            TLRPC.TL_fileLocationToBeDeprecated r72 = r6;
        L11:
            r8 = r72;
            TLObject r73 = r72;
        L12:
            r9 = r8;
            r7 = r73;
            goto L20
        L13:
            SecureDocument r14 = r0.secureDocument;
            if (r14 == null) goto L16;
            r6 = r14;
            r5 = null;
            r72 = null;
            goto L11
        L16:
            TLObject r02 = r0.webFile;
            if (r02 == null) goto L19;
            r73 = r02;
            r5 = null;
            r6 = null;
            r8 = null;
            goto L12
        L19:
            r5 = null;
            r6 = null;
            goto L10
        }

        public void removeImageReceiver(ImageReceiver r6) {
            int r0 = this.type;
            int r1 = 0;
        L3:
            int r2 = this.imageReceiverArray.size();
            ArrayList<ImageReceiver> r3 = this.imageReceiverArray;
            if (r1 >= r2) goto L14;
            ImageReceiver r22 = r3.get(r1);
            if (r22 == null) goto L8;
            if (r22 == r6) goto L8;
        L12:
            r1 = r1 + 1;
        L8:
            this.imageReceiverArray.remove(r1);
            this.imageReceiverGuidsArray.remove(r1);
            this.keys.remove(r1);
            this.filters.remove(r1);
            r0 = this.types.remove(r1).intValue();
            if (r22 == null) goto L11;
            ImageLoader.m4146$$Nest$fgetimageLoadingByTag(this.this$0).remove(r22.getTag(r0));
        L11:
            r1 = r1 - 1;
            goto L12
        L14:
            if (r3.isEmpty() == true) goto L16;
            return;
        L16:
            if (this.imageLocation == null) goto L32;
            if (ImageLoader.m4142$$Nest$fgetforceLoadingImages(this.this$0).containsKey(this.key) == true) goto L32;
            ImageLocation r62 = this.imageLocation;
            if (r62.location == null) goto L23;
            FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.imageLocation.location, this.ext);
            goto L32
        L23:
            if (r62.document == null) goto L26;
            FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.imageLocation.document);
            goto L32
        L26:
            if (r62.secureDocument == null) goto L29;
            FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.imageLocation.secureDocument);
            goto L32
        L29:
            if (r62.webFile == null) goto L32;
            FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.imageLocation.webFile);
        L32:
            if (this.cacheTask == null) goto L39;
            ImageLoader r63 = this.this$0;
            if (r0 != 1) goto L36;
            ImageLoader.m4139$$Nest$fgetcacheThumbOutQueue(r63).cancelRunnable(this.cacheTask);
        L37:
            this.cacheTask.cancel();
            this.cacheTask = null;
            goto L39
        L36:
            ImageLoader.m4138$$Nest$fgetcacheOutQueue(r63).cancelRunnable(this.cacheTask);
            ImageLoader.m4138$$Nest$fgetcacheOutQueue(this.this$0).cancelRunnable(this.runningTask);
        L39:
            if (this.httpTask == null) goto L42;
            ImageLoader.m4143$$Nest$fgethttpTasks(this.this$0).remove(this.httpTask);
            this.httpTask.cancel(true);
            this.httpTask = null;
        L42:
            if (this.artworkTask == null) goto L45;
            ImageLoader.m4136$$Nest$fgetartworkTasks(this.this$0).remove(this.artworkTask);
            this.artworkTask.cancel(true);
            this.artworkTask = null;
        L45:
            if (this.url == null) goto L48;
            ImageLoader.m4147$$Nest$fgetimageLoadingByUrl(this.this$0).remove(this.url);
        L48:
            if (this.url == null) goto L50;
            ImageLoader.m4148$$Nest$fgetimageLoadingByUrlPframe(this.this$0).remove(this.url);
        L50:
            String r64 = this.key;
            if (r64 == null) goto L58;
            this.this$0.imageLoadingByKeys.remove(r64);
            this.this$0.imageLoadingKeys.remove(ImageLoader.m4170$$Nest$smcutFilter(this.key));
            return;
        }

        public void replaceImageReceiver(ImageReceiver r4, String r5, String r6, int r7, int r8) {
            int r0 = this.imageReceiverArray.indexOf(r4);
            if (r0 != (-1)) goto L6;
            return;
        L6:
            if (this.types.get(r0).intValue() == r7) goto L10;
            ArrayList<ImageReceiver> r72 = this.imageReceiverArray;
            r0 = r72.subList(r0 + 1, r72.size()).indexOf(r4);
            if (r0 != (-1)) goto L10;
            return;
        L10:
            this.imageReceiverGuidsArray.set(r0, Integer.valueOf(r8));
            this.keys.set(r0, r5);
            this.filters.set(r0, r6);
        }

        public void setImageAndClear(Drawable r7, String r8) {
            if (r7 == null) goto L4;
            CacheImage r1 = this;
            AndroidUtilities.runOnUIThread(new ImageLoader$CacheImage$$ExternalSyntheticLambda0(r1, r7, new ArrayList(this.imageReceiverArray), new ArrayList(this.imageReceiverGuidsArray), r8));
        L5:
            int r6 = 0;
        L6:
            int r72 = r1.imageReceiverArray.size();
            ArrayList<ImageReceiver> r82 = r1.imageReceiverArray;
            if (r6 >= r72) goto L9;
            ImageReceiver r73 = r82.get(r6);
            ImageLoader.m4146$$Nest$fgetimageLoadingByTag(r1.this$0).remove(r73.getTag(r1.type));
            r6 = r6 + 1;
            goto L6
        L9:
            r82.clear();
            r1.imageReceiverGuidsArray.clear();
            if (r1.url == null) goto L13;
            ImageLoader.m4147$$Nest$fgetimageLoadingByUrl(r1.this$0).remove(r1.url);
        L13:
            if (r1.url == null) goto L15;
            ImageLoader.m4148$$Nest$fgetimageLoadingByUrlPframe(r1.this$0).remove(r1.url);
        L15:
            String r62 = r1.key;
            if (r62 == null) goto L20;
            r1.this$0.imageLoadingByKeys.remove(r62);
            r1.this$0.imageLoadingKeys.remove(ImageLoader.m4170$$Nest$smcutFilter(r1.key));
            return;
        L20:
            return;
        L4:
            r1 = this;
            goto L5
        }

        public void setImageReceiverGuid(ImageReceiver r2, int r3) {
            int r22 = this.imageReceiverArray.indexOf(r2);
            if (r22 != (-1)) goto L5;
            return;
        L5:
            this.imageReceiverGuidsArray.set(r22, Integer.valueOf(r3));
        }
    }

    public class CacheOutTask implements Runnable {
        private CacheImage cacheImage;
        private boolean isCancelled;
        private Thread runningThread;
        private final Object sync;
        final /* synthetic */ ImageLoader this$0;

        /* renamed from: $r8$lambda$IfkHX80OfIhnqccKgD6M-9yPd-s, reason: not valid java name */
        public static /* synthetic */ void m4175$r8$lambda$IfkHX80OfIhnqccKgD6M9yPds(CacheOutTask r0, Drawable r1, String r2) {
            r0.lambda$onPostExecute$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$NKBdKhDt3PnyYzQSjAx6RqRNVvo(CacheOutTask r0, Drawable r1) {
            r0.lambda$onPostExecute$1(r1);
        }

        /* renamed from: -$$Nest$fgetcacheImage, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CacheImage m4176$$Nest$fgetcacheImage(CacheOutTask r0) {
            return r0.cacheImage;
        }

        public CacheOutTask(ImageLoader r1, CacheImage r2) {
            this.this$0 = r1;
            this.sync = new Object();
            this.cacheImage = r2;
        }

        private Bitmap applyWallpaperSetting(Bitmap r9, TLRPC.WallPaper r10) {
            if (r10.pattern == true) goto L5;
        L21:
            TLRPC.WallPaperSettings r8 = r10.settings;
            if (r8 != null) goto L24;
        L27:
            return r9;
        L24:
            if (r8.blur == false) goto L27;
            return Utilities.blurWallpaper(r9);
        L5:
            if (r10.settings == null) goto L21;
            Bitmap r82 = Bitmap.createBitmap(r9.getWidth(), r9.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas r0 = new Canvas(r82);
            TLRPC.WallPaperSettings r1 = r10.settings;
            boolean r4 = true;
            if (r1.second_background_color != 0) goto L11;
            int r12 = AndroidUtilities.getPatternColor(r1.background_color);
            r0.drawColor(ColorUtils.setAlphaComponent(r10.settings.background_color, 255));
        L18:
            if (r4 == false) goto L20;
            Paint r2 = new Paint(2);
            r2.setColorFilter(new PorterDuffColorFilter(r12, PorterDuff.Mode.SRC_IN));
            r2.setAlpha((int) ((r10.settings.intensity / 100.0f) * 255.0f));
            r0.drawBitmap(r9, 0.0f, 0.0f, r2);
        L20:
            return r82;
        L11:
            if (r1.third_background_color != 0) goto L13;
            int r13 = ColorUtils.setAlphaComponent(r1.background_color, 255);
            int r22 = ColorUtils.setAlphaComponent(r10.settings.second_background_color, 255);
            int r3 = AndroidUtilities.getAverageColor(r13, r22);
            GradientDrawable r6 = new GradientDrawable(BackgroundGradientDrawable.getGradientOrientation(r10.settings.rotation), new int[]{r13, r22});
            r6.setBounds(0, 0, r82.getWidth(), r82.getHeight());
            r6.draw(r0);
            r12 = r3;
            goto L18
        L13:
            int r14 = ColorUtils.setAlphaComponent(r1.background_color, 255);
            int r23 = ColorUtils.setAlphaComponent(r10.settings.second_background_color, 255);
            int r42 = ColorUtils.setAlphaComponent(r10.settings.third_background_color, 255);
            int r62 = r10.settings.fourth_background_color;
            if (r62 != 0) goto L16;
            int r32 = 0;
        L17:
            int r63 = MotionBackgroundDrawable.getPatternColor(r14, r23, r42, r32);
            MotionBackgroundDrawable r7 = new MotionBackgroundDrawable();
            r7.setColors(r14, r23, r42, r32);
            r7.setBounds(0, 0, r82.getWidth(), r82.getHeight());
            r7.setPatternBitmap(r10.settings.intensity, r9);
            r7.draw(r0);
            r4 = false;
            r12 = r63;
            goto L18
        L16:
            r32 = ColorUtils.setAlphaComponent(r62, 255);
            goto L17
        }

        private /* synthetic */ void lambda$onPostExecute$0(Drawable r1, String r2) {
            this.cacheImage.setImageAndClear(r1, r2);
        }

        private /* synthetic */ void lambda$onPostExecute$1(Drawable r7) {
            boolean r1 = false;
            if ((r7 instanceof RLottieDrawable) == false) goto L10;
            RLottieDrawable r72 = (RLottieDrawable) r7;
            Drawable r0 = (Drawable) ImageLoader.m4149$$Nest$fgetlottieMemCache(this.this$0).get(this.cacheImage.key);
            if (r0 != null) goto L7;
            ImageLoader.m4149$$Nest$fgetlottieMemCache(this.this$0).put(this.cacheImage.key, r72);
            Drawable r73 = r72;
        L8:
            this.this$0.incrementUseCount(this.cacheImage.key);
            String r02 = this.cacheImage.key;
            Drawable r74 = r73;
        L44:
            ImageLoader.m4145$$Nest$fgetimageLoadQueue(this.this$0).postRunnable(new ImageLoader$CacheOutTask$$ExternalSyntheticLambda0(this, r74, r02), this.cacheImage.priority);
            return;
        L7:
            r72.recycle(false);
            r73 = r0;
            goto L8
        L10:
            if ((r7 instanceof AnimatedFileDrawable) == false) goto L19;
            AnimatedFileDrawable r03 = (AnimatedFileDrawable) r7;
            if (r03.isWebmSticker == false) goto L42;
            BitmapDrawable r75 = ImageLoader.m4159$$Nest$mgetFromLottieCache(this.this$0, this.cacheImage.key);
            if (r75 != null) goto L16;
            ImageLoader.m4149$$Nest$fgetlottieMemCache(this.this$0).put(this.cacheImage.key, r03);
            BitmapDrawable r76 = r03;
        L17:
            this.this$0.incrementUseCount(this.cacheImage.key);
            r02 = this.cacheImage.key;
            r74 = r76;
            goto L44
        L16:
            r03.recycle();
            r76 = r75;
        L42:
            r02 = null;
            r74 = r7;
            goto L44
        L19:
            if ((r7 instanceof BitmapDrawable) == false) goto L43;
            BitmapDrawable r77 = (BitmapDrawable) r7;
            BitmapDrawable r04 = this.this$0.getFromMemCache(this.cacheImage.key);
            boolean r3 = true;
            if (r04 == null) goto L23;
            AndroidUtilities.recycleBitmap(r77.getBitmap());
            r7 = r04;
        L40:
            if (r3 == false) goto L42;
            this.this$0.incrementUseCount(this.cacheImage.key);
            r02 = this.cacheImage.key;
            r74 = r7;
            goto L44
        L23:
            if (this.cacheImage.key.endsWith("_f") == false) goto L26;
            ImageLoader.m4154$$Nest$fgetwallpaperMemCache(this.this$0).put(this.cacheImage.key, r77);
        L38:
            r3 = r1;
            r7 = r77;
            goto L40
        L26:
            if (this.cacheImage.key.endsWith("_isc") == true) goto L35;
            if (this.cacheImage.key.endsWith("_nocache") == true) goto L35;
            if (r77.getBitmap().getWidth() > (AndroidUtilities.density * 80.0f)) goto L35;
            if (r77.getBitmap().getHeight() > (AndroidUtilities.density * 80.0f)) goto L35;
            ImageLoader.m4151$$Nest$fgetsmallImagesMemCache(this.this$0).put(this.cacheImage.key, r77);
        L37:
            r1 = true;
        L35:
            if (this.cacheImage.key.endsWith("_nocache") == true) goto L37;
            ImageLoader.m4150$$Nest$fgetmemCache(this.this$0).put(this.cacheImage.key, r77);
            goto L37
        L43:
            r74 = null;
            r02 = null;
            goto L44
        }

        private void loadLastFrame(RLottieDrawable r9, int r10, int r11, boolean r12, boolean r13) {
            if (r12 == false) goto L6;
            if (r13 == false) goto L6;
            float r1 = r10 * 1.2f;
            float r4 = r11 * 1.2f;
            Bitmap r2 = Bitmap.createBitmap((int) r1, (int) r4, Bitmap.Config.ARGB_8888);
            Canvas r3 = new Canvas(r2);
            r3.scale(2.0f, 2.0f, r1 / 2.0f, r4 / 2.0f);
        L7:
            r9.prepareForGenerateCache();
            Bitmap r14 = Bitmap.createBitmap(r9.getIntrinsicWidth(), r9.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            if (r12 == false) goto L10;
            int r6 = r9.getFramesCount() - 1;
        L11:
            r9.setGeneratingFrame(r6);
            r9.getNextFrame(r14);
            r9.releaseForGenerateCache();
            r3.save();
            if (r12 == false) goto L14;
            if (r13 == false) goto L14;
        L15:
            Paint r102 = new Paint(1);
            r102.setFilterBitmap(true);
            if (r12 == false) goto L19;
            if (r13 == false) goto L19;
            r3.drawBitmap(r14, (r2.getWidth() - r14.getWidth()) / 2.0f, (r2.getHeight() - r14.getHeight()) / 2.0f, r102);
            Drawable r103 = new ImageReceiver.ReactionLastFrame(r2);
        L20:
            r9.recycle(false);
            r14.recycle();
            onPostExecute(r103);
            return;
        L19:
            r3.drawBitmap(r14, 0.0f, 0.0f, r102);
            r103 = new BitmapDrawable(r2);
        L14:
            r3.scale(r14.getWidth() / r10, r14.getHeight() / r11, r10 / 2.0f, r11 / 2.0f);
            goto L15
        L10:
            r6 = 0;
        L6:
            r2 = Bitmap.createBitmap(r10, r11, Bitmap.Config.ARGB_8888);
            r3 = new Canvas(r2);
            goto L7
        }

        private void onPostExecute(Drawable r2) {
            AndroidUtilities.runOnUIThread(new ImageLoader$CacheOutTask$$ExternalSyntheticLambda1(this, r2));
        }

        public void cancel() {
            Object r0 = this.sync;
            monitor-enter(r0);
            this.isCancelled = true;     // Catch: Throwable -> L9 Exception -> L15
            Thread r2 = this.runningThread;     // Catch: Throwable -> L9 Exception -> L15
            if (r2 == null) goto L11;
            r2.interrupt();     // Catch: Throwable -> L9 Exception -> L15
        L9:
            th = move-exception;
            throw th;
        L11:
            monitor-exit(r0);     // Catch: Throwable -> L9
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 22, insn: 0x06e4: MOVE (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r22 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:428:0x06e4 */
        /* JADX WARN: Type inference failed for: r0v142 */
        /* JADX WARN: Type inference failed for: r0v143 */
        /* JADX WARN: Type inference failed for: r0v156 */
        /* JADX WARN: Type inference failed for: r0v159 */
        /* JADX WARN: Type inference failed for: r0v195 */
        /* JADX WARN: Type inference failed for: r0v48, types: [android.graphics.Bitmap] */
        /* JADX WARN: Type inference failed for: r11v12 */
        /* JADX WARN: Type inference failed for: r11v13 */
        /* JADX WARN: Type inference failed for: r11v14 */
        /* JADX WARN: Type inference failed for: r11v15 */
        /* JADX WARN: Type inference failed for: r11v16 */
        /* JADX WARN: Type inference failed for: r11v17 */
        /* JADX WARN: Type inference failed for: r11v18, types: [android.graphics.Bitmap] */
        /* JADX WARN: Type inference failed for: r11v19 */
        /* JADX WARN: Type inference failed for: r11v20 */
        /* JADX WARN: Type inference failed for: r11v21 */
        /* JADX WARN: Type inference failed for: r11v22 */
        /* JADX WARN: Type inference failed for: r11v23 */
        /* JADX WARN: Type inference failed for: r11v26, types: [android.graphics.Bitmap] */
        /* JADX WARN: Type inference failed for: r11v27 */
        /* JADX WARN: Type inference failed for: r11v31 */
        /* JADX WARN: Type inference failed for: r11v35 */
        /* JADX WARN: Type inference failed for: r11v36, types: [android.graphics.Bitmap] */
        /* JADX WARN: Type inference failed for: r18v13, types: [org.telegram.ui.Components.AnimatedFileDrawable] */
        /* JADX WARN: Type inference failed for: r2v111 */
        /* JADX WARN: Type inference failed for: r2v112 */
        /* JADX WARN: Type inference failed for: r2v113 */
        /* JADX WARN: Type inference failed for: r2v114 */
        /* JADX WARN: Type inference failed for: r2v116 */
        /* JADX WARN: Type inference failed for: r2v117 */
        /* JADX WARN: Type inference failed for: r2v118 */
        /* JADX WARN: Type inference failed for: r2v26 */
        /* JADX WARN: Type inference failed for: r2v27 */
        /* JADX WARN: Type inference failed for: r2v28 */
        /* JADX WARN: Type inference failed for: r2v32 */
        /* JADX WARN: Type inference failed for: r2v33 */
        /* JADX WARN: Type inference failed for: r2v34 */
        /* JADX WARN: Type inference failed for: r2v35 */
        /* JADX WARN: Type inference failed for: r2v36 */
        /* JADX WARN: Type inference failed for: r2v39, types: [org.telegram.messenger.ImageLoader$CacheImage] */
        /* JADX WARN: Type inference failed for: r2v40 */
        /* JADX WARN: Type inference failed for: r2v41 */
        /* JADX WARN: Type inference failed for: r2v43 */
        /* JADX WARN: Type inference failed for: r2v46, types: [int] */
        /* JADX WARN: Type inference failed for: r2v47 */
        /* JADX WARN: Type inference failed for: r2v49 */
        /* JADX WARN: Type inference failed for: r2v82 */
        /* JADX WARN: Type inference failed for: r3v163 */
        /* JADX WARN: Type inference failed for: r3v164 */
        /* JADX WARN: Type inference failed for: r3v165 */
        /* JADX WARN: Type inference failed for: r3v175 */
        /* JADX WARN: Type inference failed for: r3v176 */
        /* JADX WARN: Type inference failed for: r3v177 */
        /* JADX WARN: Type inference failed for: r3v178 */
        /* JADX WARN: Type inference failed for: r3v179 */
        /* JADX WARN: Type inference failed for: r3v27, types: [android.graphics.Bitmap] */
        /* JADX WARN: Type inference failed for: r3v28, types: [android.graphics.Bitmap, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v31 */
        /* JADX WARN: Type inference failed for: r41v0, types: [org.telegram.messenger.ImageLoader$CacheOutTask] */
        /* JADX WARN: Type inference failed for: r4v13, types: [android.graphics.Canvas] */
        /* JADX WARN: Type inference failed for: r4v28, types: [android.graphics.Bitmap] */
        /* JADX WARN: Type inference failed for: r5v15 */
        /* JADX WARN: Type inference failed for: r5v21, types: [android.graphics.Bitmap] */
        /* JADX WARN: Type inference failed for: r5v22, types: [android.graphics.Bitmap] */
        /* JADX WARN: Type inference failed for: r5v29, types: [android.graphics.Bitmap] */
        /* JADX WARN: Type inference failed for: r5v30 */
        /* JADX WARN: Type inference failed for: r5v31 */
        /* JADX WARN: Type inference failed for: r5v32 */
        /* JADX WARN: Type inference failed for: r5v33, types: [android.graphics.Bitmap] */
        /* JADX WARN: Type inference failed for: r5v34, types: [android.graphics.Bitmap, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r5v35 */
        /* JADX WARN: Type inference failed for: r5v37 */
        /* JADX WARN: Type inference failed for: r5v41, types: [android.graphics.Bitmap] */
        /* JADX WARN: Type inference failed for: r5v42 */
        /* JADX WARN: Type inference failed for: r5v43 */
        /* JADX WARN: Type inference failed for: r5v45, types: [android.graphics.Bitmap] */
        /* JADX WARN: Type inference failed for: r5v46 */
        /* JADX WARN: Type inference failed for: r5v47 */
        @Override // java.lang.Runnable
        public void run() {
            Object r2 = this.sync;
            monitor-enter(r2);
            this.runningThread = Thread.currentThread();     // Catch: Throwable -> L8
            Thread.interrupted();     // Catch: Throwable -> L8
            if (this.isCancelled == false) goto L10;
            monitor-exit(r2);     // Catch: Throwable -> L8
            return;
        L10:
            monitor-exit(r2);     // Catch: Throwable -> L8
            CacheImage r0 = this.cacheImage;
            ImageLocation r22 = r0.imageLocation;
            TLRPC.PhotoSize r3 = r22.photoSize;
            if ((r3 instanceof TLRPC.TL_photoStrippedSize) == false) goto L19;
            Bitmap r02 = ImageLoader.getStrippedPhotoBitmap(((TLRPC.TL_photoStrippedSize) r3).bytes, "b");
            if (r02 == null) goto L16;
            BitmapDrawable r5 = new BitmapDrawable(r02);
        L17:
            onPostExecute(r5);
            return;
        L16:
            r5 = null;
            goto L17
        L19:
            int r32 = r0.imageType;
            if (r32 == 5) goto L948;
            boolean r9 = true;
            if (r32 == 3) goto L865;
            if (r32 == 4) goto L865;
            if (r32 != 1) goto L183;
            int r23 = Math.min(512, AndroidUtilities.dp(170.6f));
            int r03 = Math.min(512, AndroidUtilities.dp(170.6f));
            String r13 = this.cacheImage.filter;
            if (r13 == null) goto L100;
            String[] r132 = r13.split("_");
            if (r132.length < 2) goto L64;
            float r04 = Float.parseFloat(r132[0]);
            float r24 = Float.parseFloat(r132[1]);
            int r14 = Math.min(512, (int) (AndroidUtilities.density * r04));
            int r33 = Math.min(512, (int) (AndroidUtilities.density * r24));
            if (r04 <= 90.0f) goto L39;
        L43:
            r03 = r33;
            boolean r34 = false;
            int r25 = r14;
        L45:
            if (r132.length < 3) goto L50;
            if ("pcache".equals(r132[2]) == false) goto L50;
        L48:
            boolean r12 = true;
        L57:
            boolean r142 = this.cacheImage.filter.contains("lastframe");
            int r16 = 4;
            boolean r7 = this.cacheImage.filter.contains("lastreactframe");
            if (r7 == false) goto L61;
            r142 = true;
        L61:
            if (this.cacheImage.filter.contains("firstframe") == false) goto L63;
            boolean r52 = true;
            int r26 = r25;
        L66:
            if (r132.length < 3) goto L77;
            int r17 = 3;
            if ("nr".equals(r132[2]) == false) goto L72;
            int r15 = 2;
        L70:
            String r6 = null;
        L80:
            if (r132.length >= 5) goto L82;
        L99:
            int r4 = r26;
            boolean r252 = r34;
            int r27 = 0;
        L89:
            int r35 = r03;
        L85:
            String r05 = r6;
            boolean r62 = r7;
            ?? r28 = r26;
        L101:
            if (r05 != null) goto L103;
            File r06 = this.cacheImage.finalFilePath;
            RandomAccessFile r29 = new RandomAccessFile(this.cacheImage.finalFilePath, "r");     // Catch: Throwable -> L128 Exception -> L130
        L114:
            e = e;
        L132:
            FileLog.e(e);     // Catch: Throwable -> L112
            if (r29 != null) goto L909;
        L138:
            boolean r72 = false;
        L139:
            if (r142 == true) goto L141;
            if (r52 == true) goto L141;
        L142:
            if (r12 == true) goto L147;
            if (r142 == true) goto L147;
            if (r52 == true) goto L147;
            BitmapsCache.CacheOptions r242 = null;
        L162:
            ImageLocation r07 = this.cacheImage.imageLocation;
            if (r07 != null) goto L165;
        L167:
            boolean r282 = false;
        L168:
            if (r72 == false) goto L171;
            File r08 = this.cacheImage.finalFilePath;
            int r232 = r35;
            int r222 = r4;
            RLottieDrawable r19 = new RLottieDrawable(r08, ImageLoader.decompressGzip(r08), r222, r232, r242, r252, null, r27, r282);
        L170:
            RLottieDrawable r210 = r19;
        L172:
            if (r142 == true) goto L176;
            if (r52 == true) goto L176;
            r210.setAutoRepeat(r15);
            onPostExecute(r210);
            return;
        L176:
            loadLastFrame(r210, r232, r222, r142, r62);
            return;
        L171:
            r232 = r35;
            r222 = r4;
            r19 = new RLottieDrawable(this.cacheImage.finalFilePath, null, r222, r232, r242, r252, null, r27, r282);
            goto L170
        L165:
            if (MessageObject.isTextColorEmoji(r07.document) == false) goto L167;
            r282 = true;
        L147:
            BitmapsCache.CacheOptions r09 = new BitmapsCache.CacheOptions();
            if (r142 == true) goto L160;
            if (r52 == true) goto L160;
            String r211 = this.cacheImage.filter;
            if (r211 != null) goto L153;
        L155:
            String r212 = this.cacheImage.filter;
            if (r212 != null) goto L158;
        L161:
            r242 = r09;
            goto L162
        L158:
            if (r212.contains("flbk") == false) goto L161;
            r09.fallback = true;
            goto L161
        L153:
            if (r211.contains("compress") == false) goto L155;
            r09.compressQuality = 60;
        L160:
            r09.firstFrame = true;
        L141:
            r12 = false;
            goto L142
        L909:
            r29.close();     // Catch: Exception -> L136
        L136:
            e = move-exception;
            FileLog.e(e);
            goto L138
        L110:
            if (this.cacheImage.type != 1) goto L116;
            byte[] r010 = ImageLoader.m4169$$Nest$sfgetheaderThumb();     // Catch: Throwable -> L112 Exception -> L114
        L117:
            r29.readFully(r010, 0, 2);     // Catch: Throwable -> L112 Exception -> L114
            if (r010[0] != 31) goto L123;
            if (r010[1] != (-117)) goto L123;
            r72 = true;
        L936:
            r29.close();     // Catch: Exception -> L126
        L126:
            e = move-exception;
            FileLog.e(e);
        L123:
            r72 = false;
            goto L936
        L116:
            r010 = ImageLoader.m4168$$Nest$sfgetheader();     // Catch: Throwable -> L112 Exception -> L114
            goto L117
        L112:
            th = move-exception;
            Throwable r1 = th;
            RandomAccessFile r53 = r28;
        L177:
            if (r53 == null) goto L978;
            r53.close();     // Catch: Exception -> L180
            throw r1;
        L180:
            e = move-exception;
            FileLog.e(e);
            throw r1;
        L978:
            throw r1;
        L130:
            e = e;
            r29 = null;
        L128:
            th = move-exception;
            r1 = th;
            r53 = null;
            goto L177
        L103:
            if ("🎰".equals(r05) == false) goto L106;
            r210 = new SlotsDrawable(r05, r4, r35);
        L105:
            r232 = r35;
            r222 = r4;
            goto L172
        L106:
            r210 = new RLottieDiceDrawable(r05, r4, r35);
            goto L105
        L82:
            if ("c1".equals(r132[r16]) == false) goto L87;
            int r42 = 12;
        L84:
            r252 = r34;
            r27 = r42;
            r35 = r03;
            r4 = r26;
            goto L85
        L87:
            if ("c2".equals(r132[r16]) == false) goto L91;
            r4 = r26;
            r252 = r34;
            r27 = r17;
            goto L89
        L91:
            if ("c3".equals(r132[r16]) == false) goto L94;
            r4 = r26;
            r252 = r34;
            r27 = r16;
            goto L89
        L94:
            if ("c4".equals(r132[r16]) == false) goto L97;
            r4 = r26;
            r252 = r34;
            r27 = 5;
            goto L89
        L97:
            if ("c5".equals(r132[r16]) == false) goto L99;
            r42 = 6;
            goto L84
        L72:
            if ("nrs".equals(r132[2]) == false) goto L75;
            r15 = 3;
            goto L70
        L75:
            if ("dice".equals(r132[2]) == false) goto L78;
            r6 = r132[3];
            r15 = 2;
        L78:
            r15 = 1;
            goto L70
        L77:
            r17 = 3;
            goto L78
        L63:
            r52 = false;
            r26 = r25;
        L50:
            if (this.cacheImage.filter.contains("pcache") == true) goto L48;
            if (this.cacheImage.filter.contains("nolimit") == false) goto L54;
        L56:
            r12 = false;
            goto L57
        L54:
            if (SharedConfig.getDevicePerformanceClass() == 2) goto L56;
        L39:
            if (r24 > 90.0f) goto L43;
            if (this.cacheImage.filter.contains("nolimit") == true) goto L43;
            int r213 = Math.min(r14, 160);
            r03 = Math.min(r33, 160);
            r34 = true;
            r25 = r213;
            goto L45
        L64:
            r16 = 4;
            r34 = false;
            r52 = false;
            r7 = false;
            r12 = false;
            r142 = false;
            r26 = r23;
            goto L66
        L100:
            r35 = r03;
            r4 = r23;
            r15 = 1;
            r52 = false;
            r62 = false;
            r12 = false;
            r142 = false;
            r252 = false;
            r27 = 0;
            r05 = null;
            r28 = r23;
            goto L101
        L183:
            int r162 = 4;
            if (r32 != 2) goto L336;
            if (r22 == null) goto L187;
            long r283 = r22.videoSeekTo;
        L188:
            String r011 = r0.filter;
            if (r011 == null) goto L219;
            String[] r012 = r011.split("_");
            if (r012.length < 2) goto L199;
            float r214 = Float.parseFloat(r012[0]);
            float r36 = Float.parseFloat(r012[1]);
            if (r214 > 90.0f) goto L199;
            if (r36 > 90.0f) goto L199;
            if (this.cacheImage.filter.contains("nolimit") == true) goto L199;
            boolean r215 = true;
        L200:
            int r37 = 0;
            boolean r63 = false;
            boolean r73 = false;
            boolean r11 = false;
            boolean r122 = false;
        L202:
            if (r37 >= r012.length) goto L216;
            if ("pcache".equals(r012[r37]) == false) goto L207;
            r73 = true;
        L207:
            if ("firstframe".equals(r012[r37]) == false) goto L210;
            r63 = true;
        L210:
            if ("nostream".equals(r012[r37]) == false) goto L213;
            r122 = true;
        L213:
            if ("pframe".equals(r012[r37]) == false) goto L215;
            r11 = true;
        L215:
            r37 = r37 + 1;
            goto L202
        L216:
            boolean r21 = r63;
            if (r63 == false) goto L220;
            r122 = true;
        L220:
            if (r11 == true) goto L965;
            if (r73 == false) goto L241;
            if (r21 == true) goto L241;
            BitmapsCache.CacheOptions r013 = new BitmapsCache.CacheOptions();
            String r38 = this.cacheImage.filter;
            if (r38 != null) goto L238;
        L240:
            BitmapsCache.CacheOptions r342 = r013;
        L243:
            if (ImageLoader.m4161$$Nest$misAnimatedAvatar(this.this$0, this.cacheImage.filter) == false) goto L245;
        L250:
            TLRPC.Document r014 = this.cacheImage.imageLocation.document;
            if ((r014 instanceof TLRPC.TL_documentEncrypted) == false) goto L252;
        L249:
            boolean r64 = r21;
            String r015 = this.cacheImage.filter;
            if (r015 == null) goto L293;
            String[] r016 = r015.split("_");
            if (r016.length < 2) goto L293;
            float r39 = Float.parseFloat(r016[0]);
            float r017 = Float.parseFloat(r016[1]);
            float r74 = AndroidUtilities.density;
            int r332 = (int) (r017 * r74);
            int r322 = (int) (r39 * r74);
        L294:
            if (r64 == true) goto L303;
            String r018 = this.cacheImage.filter;
            if (r018 != null) goto L298;
        L302:
            boolean r216 = false;
        L304:
            if (r122 == false) goto L306;
            TLRPC.Document r019 = null;
        L307:
            if (r019 == null) goto L309;
            int r020 = 1;
        L310:
            int r310 = this.cacheImage.cacheType;
            if (r310 <= 1) goto L313;
            int r352 = r310;
        L314:
            CacheImage r021 = this.cacheImage;
            File r311 = r021.finalFilePath;
            int r75 = r021.priority;
            if (r122 == false) goto L317;
            TLRPC.Document r253 = null;
        L318:
            AnimatedFileDrawable r022 = new AnimatedFileDrawable(r311, r216, 0, r75, r253, null, null, r283, r021.currentAccount, false, r322, r332, r342, r352, true);
            if (MessageObject.isWebM(this.cacheImage.imageLocation.document) == false) goto L321;
        L326:
            r022.setIsWebmSticker(r9);
        L327:
            if (r64 == false) goto L334;
            Bitmap r217 = r022.getFrameAtTime(0, false);
            r022.recycle();
            Thread.interrupted();
            if (r217 != null) goto L332;
            onPostExecute(null);
            return;
        L332:
            onPostExecute(new BitmapDrawable(r217));
            return;
        L334:
            r022.setLimitFps(r215);
            Thread.interrupted();
            onPostExecute(r022);
            return;
        L321:
            if (MessageObject.isVideoSticker(this.cacheImage.imageLocation.document) == true) goto L326;
            if (ImageLoader.m4161$$Nest$misAnimatedAvatar(this.this$0, this.cacheImage.filter) == true) goto L326;
            r9 = false;
            goto L326
        L317:
            r253 = r021.imageLocation.document;
            goto L318
        L313:
            r352 = r020;
            goto L314
        L309:
            r020 = 0;
            goto L310
        L306:
            r019 = this.cacheImage.imageLocation.document;
            goto L307
        L298:
            if ("d".equals(r018) == true) goto L303;
            if (this.cacheImage.filter.contains("_d") == false) goto L302;
        L303:
            r216 = true;
        L293:
            r322 = 0;
            r332 = 0;
            goto L294
        L252:
            if (r73 == true) goto L249;
            if (r014 == null) goto L255;
            TLRPC.Document r023 = this.cacheImage.imageLocation.document;
        L256:
            CacheImage r312 = this.cacheImage;
            if (r023 == null) goto L259;
            long r65 = r312.size;
        L260:
            if (r023 == null) goto L262;
            int r313 = 1;
        L263:
            int r8 = this.cacheImage.cacheType;
            if (r8 <= 1) goto L266;
            int r353 = r8;
        L267:
            CacheImage r314 = this.cacheImage;
            File r82 = r314.finalFilePath;
            if (r122 == false) goto L270;
            long r223 = 0;
        L271:
            int r66 = r314.priority;
            if (r122 == false) goto L274;
            TLRPC.Document r254 = null;
        L275:
            if (r023 != null) goto L278;
            if (r122 == true) goto L278;
            ImageLocation r262 = r314.imageLocation;
        L279:
            AnimatedFileDrawable r192 = new AnimatedFileDrawable(r82, r21, r223, r66, r254, r262, r314.parentObject, r283, r314.currentAccount, false, 0, 0, r342, r353, !ImageLoader.AUTOPLAY_FILTER_NONLOOP.equals(r314.filter));
            r64 = r21;
            if (MessageObject.isWebM(r023) == false) goto L282;
        L287:
            r192.setIsWebmSticker(r9);
            r022 = r192;
            goto L327
        L282:
            if (MessageObject.isVideoSticker(r023) == true) goto L287;
            if (ImageLoader.m4161$$Nest$misAnimatedAvatar(this.this$0, this.cacheImage.filter) == true) goto L287;
            r9 = false;
        L278:
            r262 = null;
            goto L279
        L274:
            r254 = r023;
            goto L275
        L270:
            r223 = r65;
            goto L271
        L266:
            r353 = r313;
            goto L267
        L262:
            r313 = 0;
            goto L263
        L259:
            r65 = r312.imageLocation.currentSize;
            goto L260
        L255:
            r023 = null;
            goto L256
        L245:
            if (ImageLoader.AUTOPLAY_FILTER.equals(this.cacheImage.filter) == true) goto L250;
            if (ImageLoader.AUTOPLAY_FILTER_NONLOOP.equals(this.cacheImage.filter) == false) goto L249;
        L238:
            if (r38.contains("compress") == false) goto L240;
            r013.compressQuality = 60;
        L241:
            r342 = null;
            goto L243
        L965:
            MediaMetadataRetriever r024 = new MediaMetadataRetriever();     // Catch: Exception -> L226
            r024.setDataSource(this.cacheImage.finalFilePath.getAbsolutePath());     // Catch: Exception -> L226
            Bitmap r218 = r024.getFrameAtTime(2);     // Catch: Exception -> L226
            r024.release();     // Catch: Exception -> L224
        L229:
            Thread.interrupted();
            if (r218 != null) goto L232;
            onPostExecute(null);
            return;
        L232:
            onPostExecute(new BitmapDrawable(r218));
            return;
        L224:
            e = e;
        L228:
            e.printStackTrace();
        L226:
            e = e;
            r218 = null;
        L199:
            r215 = false;
            goto L200
        L219:
            r215 = false;
            r73 = false;
            r11 = false;
            r122 = false;
            r21 = false;
            goto L220
        L187:
            r283 = 0;
            goto L188
        L336:
            File r219 = r0.finalFilePath;
            if (r0.secureDocument == null) goto L339;
        L345:
            String r315 = 1;
        L346:
            CacheImage r025 = this.cacheImage;
            SecureDocument r67 = r025.secureDocument;
            if (r67 == null) goto L354;
            SecureDocumentKey r76 = r67.secureDocumentKey;
            TLRPC.TL_secureFile r112 = r67.secureFile;
            if (r112 == null) goto L353;
            byte[] r113 = r112.file_hash;
            if (r113 == null) goto L353;
            byte[] r68 = r113;
        L355:
            String r026 = r025.imageLocation.path;
            if (r026 != null) goto L358;
        L375:
            boolean r143 = true;
            Bitmap r133 = null;
        L374:
            String r114 = null;
            Long r123 = null;
        L376:
            BitmapFactory.Options r152 = new BitmapFactory.Options();
            r152.inSampleSize = 1;
            boolean r18 = ImageLoader.m4140$$Nest$fgetcanForce8888(this.this$0);
            String r027 = this.cacheImage.filter;     // Catch: Throwable -> L531
            if (r027 == null) goto L503;
            float r354 = 0.0f;
            String[] r028 = r027.split("_");     // Catch: Throwable -> L500
            if (r028.length >= 2) goto L941;
            float r54 = 0.0f;
            float r193 = 0.0f;
        L913:
            float r20 = 1.2f;
        L494:
            th = th;
            String r382 = r315;
            String r233 = r114;
        L496:
            File r316 = null;
        L497:
            char r43 = 0;
            File r317 = r316;
        L493:
            boolean r83 = false;
            File r318 = r317;
            boolean r182 = r18;
        L533:
            FileLog.e(th);
            File r319 = r318;
            boolean r183 = r182;
        L530:
            float r92 = r193;
            int r029 = this.cacheImage.type;
            ImageLoader r10 = this.this$0;
            if (r029 == 1) goto L952;
            ?? r115 = r319;
            ImageLoader.m4155$$Nest$fputlastCacheOutTime(r10, SystemClock.elapsedRealtime());     // Catch: Throwable -> L643
            Object r220 = this.sync;     // Catch: Throwable -> L643
            monitor-enter(r220);     // Catch: Throwable -> L643
        L660:
            th = th;
        L645:
            ?? r221 = 0;
            boolean r93 = false;
            r162 = 0;
            r319 = r319;
        L830:
            FileLog.e(th);
            ?? r55 = r115;
        L825:
            int r030 = r162;
            int r224 = r221;
            File r320 = r319;
        L831:
            Thread.interrupted();
            if (BuildVars.LOGS_ENABLED == false) goto L839;
            if (r382 == null) goto L839;
            StringBuilder r44 = new StringBuilder("Image Loader image is empty = ");
            if (r55 != null) goto L837;
            boolean r69 = true;
        L838:
            r44.append(r69);
            r44.append(" ");
            r44.append(r320);
            FileLog.e(r44.toString());
            goto L839
        L837:
            r69 = false;
        L839:
            if (r55 != null) goto L841;
        L847:
            CacheImage r321 = this.cacheImage;
            if (r321 == null) goto L853;
            String r323 = r321.filter;
            if (r323 == null) goto L853;
            if (r323.contains("ignoreOrientation") == false) goto L853;
        L857:
            if (r55 == null) goto L859;
            BitmapDrawable r56 = new BitmapDrawable(r55);
        L860:
            onPostExecute(r56);
            return;
        L859:
            r56 = null;
        L853:
            if (r93 == true) goto L861;
            if (r030 != 0) goto L861;
            if (r224 == 0) goto L857;
        L861:
            if (r55 == null) goto L863;
            ExtendedBitmapDrawable r57 = new ExtendedBitmapDrawable(r55, r030, r224);
        L864:
            onPostExecute(r57);
            return;
        L863:
            r57 = null;
            goto L864
        L841:
            if (TextUtils.isEmpty(this.cacheImage.filter) == true) goto L847;
            if (this.cacheImage.filter.contains("wallpaper") == false) goto L847;
            Object r324 = this.cacheImage.parentObject;
            if ((r324 instanceof TLRPC.WallPaper) == false) goto L847;
            r55 = applyWallpaperSetting(r55, (TLRPC.WallPaper) r324);
        L631:
            th = th;
        L956:
            throw th;     // Catch: Throwable -> L660
        L828:
            th = th;
            goto L956
        L628:
            if (this.isCancelled == false) goto L633;
            monitor-exit(r220);     // Catch: Throwable -> L631
            return;
        L633:
            monitor-exit(r220);     // Catch: Throwable -> L631
            if (r183 == true) goto L646;
            CacheImage r031 = this.cacheImage;     // Catch: Throwable -> L643
            if (r031.filter == null) goto L646;
            if (r43 != 0) goto L646;
            if (r031.imageLocation.path != null) goto L646;
            r152.inPreferredConfig = Bitmap.Config.RGB_565;     // Catch: Throwable -> L643
        L647:
            r152.inDither = false;     // Catch: Throwable -> L643
            if (r123 == null) goto L667;
            if (r233 != null) goto L667;
            if (r133 != null) goto L652;
            File r325 = r219;
            Bitmap r032 = MediaStore.Images.Thumbnails.getThumbnail(ApplicationLoader.applicationContext.getContentResolver(), r123.longValue(), 1, r152);     // Catch: Throwable -> L660
        L664:
            Bitmap r102 = r032;
            r319 = r325;
            boolean r184 = r183;
        L668:
            if (r102 != null) goto L754;
            if (r102 != null) goto L716;
            if (r76 == null) goto L672;
            FileInputStream r033 = new EncryptedFileInputStream(r319, r76);     // Catch: Throwable -> L658
        L676:
            r221 = this.cacheImage;     // Catch: Throwable -> L714
            if ((r221.imageLocation.document instanceof TLRPC.TL_document) == true) goto L684;
            String r225 = r221.filter;     // Catch: Throwable -> L658
            if (r225 == null) goto L683;
            if (r225.contains("exif") == false) goto L683;
        L658:
            th = th;
            r115 = r102;
        L683:
            r133 = r102;
            r221 = 0;
            Rect r103 = null;
            r162 = 0;
        L705:
            r115 = BitmapFactory.decodeStream(r033, r103, r152);     // Catch: Throwable -> L694
            r033.close();     // Catch: Throwable -> L708
            r221 = r221;
        L717:
            if (r115 == null) goto L961;
            r184 = r83;
        L755:
            if (r115 != null) goto L763;
            if (r143 == true) goto L758;
        L824:
            r55 = r115;
        L823:
            r93 = false;
            goto L825
        L758:
            if (r319.length() != 0) goto L760;
        L761:
            r319.delete();     // Catch: Throwable -> L708
            goto L824
        L760:
            if (this.cacheImage.filter != null) goto L824;
        L763:
            if (this.cacheImage.filter == null) goto L824;
            float r034 = r115.getWidth();     // Catch: Throwable -> L708
            float r610 = r115.getHeight();     // Catch: Throwable -> L708
            if (r54 != 0.0f) goto L767;
        L784:
            r55 = r115;
        L785:
            if (r55 == null) goto L823;
            if (r184 == true) goto L934;
            r93 = false;
        L805:
            if (r43 != 0) goto L807;
        L814:
            if (r43 == 0) goto L825;
            if (r610 >= 100.0f) goto L825;
            if (r034 >= 100.0f) goto L825;
            if (r55.getConfig() != Bitmap.Config.ARGB_8888) goto L825;
            Utilities.blurBitmap(r55, 3);     // Catch: Throwable -> L812
        L812:
            th = th;
            r115 = r55;
            r221 = r221;
            r319 = r319;
            goto L830
        L807:
            if (r610 > 100.0f) goto L922;
            if (r034 <= 100.0f) goto L814;
        L922:
            r610 = 80.0f;
            r55 = Bitmaps.createScaledBitmap(r55, 80, 80, false);     // Catch: Throwable -> L812
            r034 = 80.0f;
            goto L814
        L934:
        L790:
            th = th;
            r115 = r55;
        L696:
            r93 = false;
            r221 = r221;
            r319 = r319;
            goto L830
        L788:
            if ((r55.getWidth() * r55.getHeight()) <= 22500) goto L792;
            Bitmap r77 = Bitmaps.createScaledBitmap(r55, 100, 100, false);     // Catch: Throwable -> L790
        L793:
            if (Utilities.needInvert(r77) == 0) goto L796;
            boolean r84 = true;
        L797:
            if (r77 != r55) goto L950;
        L802:
            r93 = r84;
            goto L805
        L950:
            r77.recycle();     // Catch: Throwable -> L800
        L800:
            th = th;
            r115 = r55;
            r93 = r84;
            r221 = r221;
            r319 = r319;
            goto L830
        L796:
            r84 = false;
            goto L797
        L792:
            r77 = r55;
            goto L793
        L767:
            if (r034 == r54) goto L784;
            if (r034 <= (r54 + 20.0f)) goto L784;
            if (r034 > r610) goto L773;
        L777:
            float r58 = r610 / r92;     // Catch: Throwable -> L708
            if (r58 <= 1.0f) goto L780;
            r55 = Bitmaps.createScaledBitmap(r115, (int) (r034 / r58), (int) r92, true);     // Catch: Throwable -> L708
        L781:
            if (r115 == r55) goto L784;
            r115.recycle();     // Catch: Throwable -> L708
        L780:
            r55 = r115;
            goto L781
        L773:
            if (r54 <= r92) goto L777;
            float r78 = r034 / r54;     // Catch: Throwable -> L708
            if (r78 <= 1.0f) goto L780;
            r55 = Bitmaps.createScaledBitmap(r115, (int) r54, (int) (r610 / r78), true);     // Catch: Throwable -> L708
            goto L781
        L961:
            RandomAccessFile r035 = new RandomAccessFile(r319, "r");     // Catch: Throwable -> L723
            int r124 = (int) r035.length();     // Catch: Throwable -> L723
            byte[] r134 = (byte[]) ImageLoader.m4166$$Nest$sfgetbytesLocal().get();     // Catch: Throwable -> L723
            if (r134 != null) goto L721;
        L725:
            r134 = null;
        L726:
            if (r134 != null) goto L730;
            r134 = new byte[r124];     // Catch: Throwable -> L723
            if (r124 >= 52428800) goto L730;
            ImageLoader.m4166$$Nest$sfgetbytesLocal().set(r134);     // Catch: Throwable -> L723
        L730:
            r035.readFully(r134, 0, r124);     // Catch: Throwable -> L723
            r035.close();     // Catch: Throwable -> L723
            if (r76 == null) goto L745;
            EncryptedFileInputStream.decryptBytesWithKeyFile(r134, 0, r124, r76);     // Catch: Throwable -> L723
            r184 = r83;
            byte[] r036 = Utilities.computeSHA256(r134, 0, r124);     // Catch: Throwable -> L741
            if (r68 != null) goto L737;
        L743:
            boolean r037 = true;
        L744:
            int r611 = r134[0] & UByte.MAX_VALUE;     // Catch: Throwable -> L741
            r124 = r124 - r611;     // Catch: Throwable -> L741
        L749:
            if (r037 == true) goto L755;
            r115 = BitmapFactory.decodeByteArray(r134, r611, r124, r152);     // Catch: Throwable -> L741
            goto L755
        L737:
            if (Arrays.equals(r036, r68) == false) goto L743;
            r037 = false;
            goto L744
        L745:
            r184 = r83;
            if (r382 == null) goto L748;
            EncryptedFileInputStream.decryptBytesWithKeyFile(r134, 0, r124, this.cacheImage.encryptionKeyPath);     // Catch: Throwable -> L741
        L748:
            r037 = false;
            r611 = 0;
        L741:
            th = th;
            r184 = r184;
        L752:
            FileLog.e(th);     // Catch: Throwable -> L708
            goto L755
        L721:
            if (r134.length < r124) goto L725;
        L723:
            th = th;
            r184 = r83;
        L708:
            th = th;
        L684:
            Pair<Integer, Integer> r226 = AndroidUtilities.getImageOrientation(r033);     // Catch: Throwable -> L714
            int r116 = ((Integer) r226.first).intValue();     // Catch: Throwable -> L714
            r221 = ((Integer) r226.second).intValue();     // Catch: Throwable -> L712
            if (r76 == null) goto L932;
        L689:
            r133 = r102;
            r162 = r116;
            r033.close();     // Catch: Throwable -> L710
            if (r76 == null) goto L703;
            r033 = new EncryptedFileInputStream(r319, r76);     // Catch: Throwable -> L694
        L702:
            r103 = null;
            r221 = r221;
            goto L705
        L703:
            if (r382 == null) goto L702;
            r033 = new EncryptedFileInputStream(r319, this.cacheImage.encryptionKeyPath);     // Catch: Throwable -> L694
        L710:
            th = th;
        L695:
            r115 = r133;
            goto L696
        L932:
        L697:
            th = th;
            r133 = r102;
            r162 = r116;
            goto L695
        L688:
            if (this.cacheImage.encryptionKeyPath != null) goto L689;
            r133 = r102;
            r162 = r116;
            r033.getChannel().position(0);     // Catch: Throwable -> L694
        L712:
            th = th;
            r162 = r116;
            r115 = r102;
            r221 = 0;
        L714:
            th = th;
            r115 = r102;
        L694:
            th = th;
            goto L695
        L672:
            if (r382 == null) goto L919;
            r033 = new EncryptedFileInputStream(r319, this.cacheImage.encryptionKeyPath);     // Catch: Throwable -> L658
            goto L676
        L919:
            r033 = new FileInputStream(r319);     // Catch: Throwable -> L714
            goto L676
        L716:
            r115 = r102;
            r221 = 0;
            r162 = 0;
            goto L717
        L754:
            r184 = r83;
            r115 = r102;
            r221 = 0;
            r162 = 0;
            goto L755
        L652:
            if (r123.longValue() != 0) goto L662;
            ?? r185 = new AnimatedFileDrawable(r219, true, 0, 0, null, null, null, 0, 0, true, null);     // Catch: Throwable -> L643
            r319 = r219;
            r102 = r185.getFrameAtTime(0, true);     // Catch: Throwable -> L660
            r185.recycle();     // Catch: Throwable -> L658
            r319 = r319;
            r184 = r185;
            goto L668
        L662:
            r325 = r219;
            r032 = MediaStore.Video.Thumbnails.getThumbnail(ApplicationLoader.applicationContext.getContentResolver(), r123.longValue(), 1, r152);     // Catch: Throwable -> L660
        L667:
            r319 = r219;
            r102 = r115;
            r184 = r183;
        L646:
            r152.inPreferredConfig = Bitmap.Config.ARGB_8888;     // Catch: Throwable -> L643
        L643:
            th = th;
            r319 = r219;
            goto L645
        L952:
            ImageLoader.m4155$$Nest$fputlastCacheOutTime(r10, SystemClock.elapsedRealtime());     // Catch: Throwable -> L551
            Object r94 = this.sync;     // Catch: Throwable -> L551
            monitor-enter(r94);     // Catch: Throwable -> L551
        L542:
            th = move-exception;
            throw th;     // Catch: Throwable -> L551
        L539:
            if (this.isCancelled == false) goto L544;
            monitor-exit(r94);     // Catch: Throwable -> L542
            return;
        L544:
            monitor-exit(r94);     // Catch: Throwable -> L542
            if (r76 == null) goto L567;
            RandomAccessFile r038 = new RandomAccessFile(r219, "r");     // Catch: Throwable -> L551
            int r95 = (int) r038.length();     // Catch: Throwable -> L551
            byte[] r104 = (byte[]) ImageLoader.m4167$$Nest$sfgetbytesThumbLocal().get();     // Catch: Throwable -> L551
            if (r104 != null) goto L549;
        L553:
            r104 = null;
        L554:
            if (r104 != null) goto L556;
            r104 = new byte[r95];     // Catch: Throwable -> L551
            ImageLoader.m4167$$Nest$sfgetbytesThumbLocal().set(r104);     // Catch: Throwable -> L551
        L556:
            r038.readFully(r104, 0, r95);     // Catch: Throwable -> L551
            r038.close();     // Catch: Throwable -> L551
            EncryptedFileInputStream.decryptBytesWithKeyFile(r104, 0, r95, r76);     // Catch: Throwable -> L551
            byte[] r039 = Utilities.computeSHA256(r104, 0, r95);     // Catch: Throwable -> L551
            if (r68 != null) goto L559;
        L563:
            boolean r040 = true;
        L564:
            int r612 = r104[0] & UByte.MAX_VALUE;     // Catch: Throwable -> L551
            int r96 = r95 - r612;     // Catch: Throwable -> L551
            ?? r326 = r319;
            if (r040 == true) goto L572;
            r326 = BitmapFactory.decodeByteArray(r104, r612, r96, r152);     // Catch: Throwable -> L551
        L572:
            if (r326 == 0) goto L574;
            ?? r327 = r326;
            if (this.cacheImage.filter == null) goto L590;
            float r041 = r326.getWidth();     // Catch: Throwable -> L551
            float r613 = r326.getHeight();     // Catch: Throwable -> L551
            r327 = r326;
            if (r54 == r354) goto L590;
            r327 = r326;
            if (r041 == r54) goto L590;
            r327 = r326;
            if (r041 <= (r54 + 20.0f)) goto L590;
            Bitmap r042 = Bitmaps.createScaledBitmap(r326, (int) r54, (int) (r613 / (r041 / r54)), true);     // Catch: Throwable -> L551
            r327 = r326;
            if (r326 == r042) goto L590;
            r326.recycle();     // Catch: Throwable -> L551
            r327 = r042;
        L590:
            if (r83 == true) goto L592;
            boolean r59 = false;
        L945:
            if (r43 != 1) goto L605;
            Bitmap.Config r043 = r327.getConfig();     // Catch: Throwable -> L602
            File r328 = r327;
            if (r043 != Bitmap.Config.ARGB_8888) goto L620;
            Utilities.blurBitmap(r327, 3);     // Catch: Throwable -> L602
            r328 = r327;
        L620:
            r93 = r59;
            r030 = 0;
            r55 = r328;
            r320 = r219;
            r224 = 0;
            goto L831
        L605:
            if (r43 != 2) goto L610;
            Bitmap.Config r044 = r327.getConfig();     // Catch: Throwable -> L602
            r328 = r327;
            if (r044 != Bitmap.Config.ARGB_8888) goto L620;
            Utilities.blurBitmap(r327, 1);     // Catch: Throwable -> L602
            r328 = r327;
            goto L620
        L610:
            if (r43 == 3) goto L614;
            char r614 = 4;
            r328 = r327;
            if (r43 != 4) goto L620;
        L615:
            Bitmap.Config r045 = r327.getConfig();     // Catch: Throwable -> L602
            r328 = r327;
            File r329 = r327;
            if (r045 != Bitmap.Config.ARGB_8888) goto L620;
            if (r43 != r614) goto L619;
            ?? r046 = Bitmap.createBitmap(r327.getWidth(), r327.getHeight(), r327.getConfig());     // Catch: Throwable -> L602
            ?? r45 = new Canvas(r046);     // Catch: Throwable -> L602
            r45.save();     // Catch: Throwable -> L602
            float r97 = r20;
            r45.scale(r97, r97, r327.getWidth() / 2.0f, r327.getHeight() / 2.0f);     // Catch: Throwable -> L602
            float r615 = r354;
            r45.drawBitmap(r327, r615, r615, null);     // Catch: Throwable -> L602
            r45.restore();     // Catch: Throwable -> L602
            Path r616 = new Path();     // Catch: Throwable -> L602
            r616.addCircle(r327.getWidth() / 2.0f, r327.getHeight() / 2.0f, Math.min(r327.getWidth(), r327.getHeight()) / 2.0f, Path.Direction.CW);     // Catch: Throwable -> L602
            r45.clipPath(r616);     // Catch: Throwable -> L602
            r45.drawBitmap(r327, 0.0f, 0.0f, null);     // Catch: Throwable -> L602
            r327.recycle();     // Catch: Throwable -> L602
            r329 = r046;
        L619:
            Utilities.blurBitmap(r329, 7);     // Catch: Throwable -> L602
            Utilities.blurBitmap(r329, 7);     // Catch: Throwable -> L602
            Utilities.blurBitmap(r329, 7);     // Catch: Throwable -> L602
            r328 = r329;
            goto L620
        L614:
            r614 = 4;
        L602:
            th = th;
            File r330 = r327;
        L623:
            FileLog.e(th);
            r328 = r330;
            goto L620
        L592:
            if (Utilities.needInvert(r327) == 0) goto L594;
            boolean r047 = true;
        L595:
            r59 = r047;
            goto L945
        L594:
            r047 = false;
            goto L595
        L574:
            if (r219.length() != 0) goto L576;
        L577:
            r219.delete();     // Catch: Throwable -> L551
        L578:
            r59 = false;
            r328 = r326;
            goto L620
        L576:
            if (this.cacheImage.filter != null) goto L578;
        L559:
            if (Arrays.equals(r039, r68) == false) goto L563;
            r040 = false;
            goto L564
        L549:
            if (r104.length < r95) goto L553;
        L567:
            if (r382 == null) goto L570;
            FileInputStream r048 = new EncryptedFileInputStream(r219, this.cacheImage.encryptionKeyPath);     // Catch: Throwable -> L551
        L571:
            Bitmap r331 = BitmapFactory.decodeStream(r048, null, r152);     // Catch: Throwable -> L551
            r048.close();     // Catch: Throwable -> L551
            r326 = r331;
            goto L572
        L570:
            r048 = new FileInputStream(r219);     // Catch: Throwable -> L551
        L551:
            th = th;
            r59 = false;
            r330 = r319;
            goto L623
        L395:
            if (this.cacheImage.filter.contains("b2r") == false) goto L398;
            r43 = 4;
        L959:
            r83 = this.cacheImage.filter.contains("i");     // Catch: Throwable -> L491
            String r105 = "f";
            if (this.cacheImage.filter.contains("f") == false) goto L411;
            r18 = true;
        L415:
            if (r54 != 0.0f) goto L417;
        L489:
            r382 = r315;
            char r227 = r43;
            r233 = r114;
        L490:
            r43 = r227;
            ?? r049 = null;
            boolean r186 = r18;
        L529:
            r319 = r049;
            r183 = r186;
            goto L530
        L417:
            if (r193 == 0.0f) goto L489;
            r152.inJustDecodeBounds = true;     // Catch: Throwable -> L487
            if (r123 == null) goto L434;
            if (r114 != null) goto L434;
            if (r133 != null) goto L911;
            String r106 = r315;
            r227 = r43;
            MediaStore.Images.Thumbnails.getThumbnail(ApplicationLoader.applicationContext.getContentResolver(), r123.longValue(), 1, r152);     // Catch: Throwable -> L426
        L425:
            r382 = r106;
            r233 = r114;
        L465:
            float r050 = r152.outWidth;     // Catch: Throwable -> L453
            float r333 = r152.outHeight;     // Catch: Throwable -> L453
            if (r54 >= r193) goto L468;
        L470:
            float r46 = Math.min(r050 / r54, r333 / r193);     // Catch: Throwable -> L453
        L472:
            if (r46 >= 1.2f) goto L474;
            r46 = 1.0f;
        L474:
            r152.inJustDecodeBounds = false;     // Catch: Throwable -> L453
            if (r46 > 1.0f) goto L477;
        L485:
            r152.inSampleSize = (int) r46;     // Catch: Throwable -> L453
            goto L490
        L477:
            if (r050 <= r54) goto L479;
        L480:
            int r051 = 1;
        L481:
            int r334 = r051 * 2;     // Catch: Throwable -> L453
            if ((r051 * 4) >= r46) goto L483;
            r051 = r334;
            goto L481
        L483:
            r152.inSampleSize = r334;     // Catch: Throwable -> L453
            goto L490
        L479:
            if (r333 <= r193) goto L485;
        L468:
            if (r050 <= r333) goto L470;
            r46 = Math.max(r050 / r54, r333 / r193);     // Catch: Throwable -> L453
            goto L472
        L911:
            r106 = r315;
            r227 = r43;
            MediaStore.Video.Thumbnails.getThumbnail(ApplicationLoader.applicationContext.getContentResolver(), r123.longValue(), 1, r152);     // Catch: Throwable -> L426
        L430:
            th = th;
            r382 = r315;
            boolean r187 = r18;
        L432:
            r233 = r114;
            boolean r188 = r187;
        L429:
            r318 = null;
            r182 = r188;
        L434:
            String r107 = r315;
            r227 = r43;
            if (r76 == null) goto L459;
            RandomAccessFile r052 = new RandomAccessFile(r219, "r");     // Catch: Throwable -> L426
            int r335 = (int) r052.length();     // Catch: Throwable -> L426
            byte[] r47 = (byte[]) ImageLoader.m4166$$Nest$sfgetbytesLocal().get();     // Catch: Throwable -> L426
            if (r47 != null) goto L439;
        L441:
            r47 = null;
        L442:
            if (r47 != null) goto L444;
            r47 = new byte[r335];     // Catch: Throwable -> L426
            ImageLoader.m4166$$Nest$sfgetbytesLocal().set(r47);     // Catch: Throwable -> L426
        L444:
            r052.readFully(r47, 0, r335);     // Catch: Throwable -> L426
            r052.close();     // Catch: Throwable -> L426
            EncryptedFileInputStream.decryptBytesWithKeyFile(r47, 0, r335, r76);     // Catch: Throwable -> L426
            r382 = r107;
            r233 = r114;
            byte[] r053 = Utilities.computeSHA256(r47, 0, r335);     // Catch: Throwable -> L453
            if (r68 != null) goto L449;
        L455:
            boolean r054 = true;
        L456:
            int r98 = r47[0] & UByte.MAX_VALUE;     // Catch: Throwable -> L453
            int r336 = r335 - r98;     // Catch: Throwable -> L453
            if (r054 == true) goto L465;
            BitmapFactory.decodeByteArray(r47, r98, r336, r152);     // Catch: Throwable -> L453
            goto L465
        L449:
            if (Arrays.equals(r053, r68) == false) goto L455;
            r054 = false;
            goto L456
        L439:
            if (r47.length < r335) goto L441;
        L459:
            r382 = r107;
            r233 = r114;
            if (r382 == null) goto L463;
            FileInputStream r055 = new EncryptedFileInputStream(r219, this.cacheImage.encryptionKeyPath);     // Catch: Throwable -> L453
        L464:
            BitmapFactory.decodeStream(r055, null, r152);     // Catch: Throwable -> L453
            r055.close();     // Catch: Throwable -> L453
            goto L465
        L463:
            r055 = new FileInputStream(r219);     // Catch: Throwable -> L453
        L453:
            th = th;
        L428:
            r43 = r22;
            r188 = r18;
        L426:
            th = th;
            r382 = r105;
            r233 = r114;
            goto L428
        L411:
            r105 = "F";
            r18 = r18;
            if (this.cacheImage.filter.contains("F") == false) goto L415;
            r18 = false;
        L487:
            th = th;
            r382 = r315;
            r187 = r18;
        L491:
            th = th;
            r382 = r315;
            r233 = r114;
            r317 = null;
            goto L493
        L398:
            if (this.cacheImage.filter.contains("b2") == false) goto L401;
            r43 = 3;
            goto L959
        L401:
            if (this.cacheImage.filter.contains("b1") == false) goto L403;
            r43 = 2;
            goto L959
        L403:
            if (this.cacheImage.filter.contains("b") == false) goto L406;
            r43 = 1;
            goto L959
        L406:
            r43 = 0;
        L498:
            th = th;
            r382 = r315;
            r233 = r114;
            r20 = 1.2f;
            goto L496
        L941:
            r54 = Float.parseFloat(r028[0]) * AndroidUtilities.density;
            r193 = Float.parseFloat(r028[1]) * AndroidUtilities.density;
        L386:
            th = th;
            r382 = r315;
            r43 = 0;
            r83 = false;
            r233 = r114;
            r193 = 0.0f;
        L388:
            r318 = null;
            r20 = 1.2f;
            r182 = r18;
        L389:
            th = th;
            r382 = r315;
            r43 = 0;
            r83 = false;
            r233 = r114;
            r54 = 0.0f;
            r193 = 0.0f;
        L500:
            th = th;
            r382 = r315;
            r233 = r114;
            r20 = 1.2f;
        L502:
            r54 = r354;
            r193 = r54;
            goto L496
        L503:
            r382 = r315;
            r233 = r114;
            r20 = 1.2f;
            r354 = 0.0f;
            if (r233 != null) goto L954;
            r54 = 0.0f;
            r193 = 0.0f;
            r049 = null;
        L527:
            r43 = 0;
            r83 = false;
            r186 = r18;
            goto L529
        L954:
            r152.inJustDecodeBounds = true;     // Catch: Throwable -> L509
            if (r18 == false) goto L511;
            Bitmap.Config r056 = Bitmap.Config.ARGB_8888;     // Catch: Throwable -> L509
        L512:
            r152.inPreferredConfig = r056;     // Catch: Throwable -> L509
            FileInputStream r057 = new FileInputStream(r219);     // Catch: Throwable -> L509
            ?? r48 = BitmapFactory.decodeStream(r057, null, r152);     // Catch: Throwable -> L509
            r057.close();     // Catch: Throwable -> L522
            int r058 = r152.outWidth;     // Catch: Throwable -> L522
            int r337 = r152.outHeight;     // Catch: Throwable -> L522
            r152.inJustDecodeBounds = false;     // Catch: Throwable -> L522
            float r059 = (Math.min(r337, r058) / Math.max(66, Math.min(AndroidUtilities.getRealScreenSize().x, AndroidUtilities.getRealScreenSize().y))) * 6.0f;     // Catch: Throwable -> L522
            if (r059 >= 1.0f) goto L517;
            r059 = 1.0f;
        L517:
            if (r059 <= 1.0f) goto L525;
            int r338 = 1;
        L519:
            int r510 = r338 * 2;     // Catch: Throwable -> L522
            if ((r338 * 4) > r059) goto L521;
            r338 = r510;
            goto L519
        L521:
            r152.inSampleSize = r510;     // Catch: Throwable -> L522
        L526:
            r049 = r48;
            r54 = 0.0f;
            r193 = 0.0f;
            goto L527
        L525:
            r152.inSampleSize = (int) r059;     // Catch: Throwable -> L522
        L522:
            th = th;
            r316 = r48;
            r54 = 0.0f;
            r193 = 0.0f;
            goto L497
        L511:
            r056 = Bitmap.Config.RGB_565;     // Catch: Throwable -> L509
        L509:
            th = th;
        L531:
            th = th;
            r382 = r315;
            r233 = r114;
            r20 = 1.2f;
            r354 = 0.0f;
            goto L502
        L358:
            if (r026.startsWith("thumb://") == false) goto L365;
            int r117 = r026.indexOf(":", 8);
            if (r117 < 0) goto L362;
            r123 = Long.valueOf(Long.parseLong(r026.substring(8, r117)));
            String r060 = r026.substring(r117 + 1);
        L363:
            r114 = r060;
            r133 = null;
            r143 = false;
            goto L376
        L362:
            r060 = null;
            r123 = null;
            goto L363
        L365:
            if (r026.startsWith("vthumb://") == false) goto L372;
            int r118 = r026.indexOf(":", 9);
            if (r118 < 0) goto L369;
            Long r061 = Long.valueOf(Long.parseLong(r026.substring(9, r118)));
            Bitmap r119 = 1;
        L370:
            r123 = r061;
            r143 = false;
            r133 = r119;
            r114 = null;
            goto L376
        L369:
            r119 = null;
            r061 = null;
            goto L370
        L372:
            if (r026.startsWith("http") == true) goto L375;
            r133 = null;
            r143 = false;
        L353:
            r68 = r67.fileHash;
            goto L355
        L354:
            r68 = null;
            r76 = null;
            goto L355
        L339:
            if (r0.encryptionKeyPath == null) goto L344;
            if (r219 == null) goto L344;
            if (r219.getAbsolutePath().endsWith(".enc") == true) goto L345;
        L344:
            r315 = null;
        L865:
            Point r228 = AndroidUtilities.displaySize;
            int r339 = r228.x;
            int r229 = r228.y;
            String r062 = r0.filter;
            if (r062 == null) goto L870;
            String[] r063 = r062.split("_");
            if (r063.length < 2) goto L870;
            boolean r362 = false;
            float r230 = Float.parseFloat(r063[0]);
            boolean r372 = true;
            float r064 = Float.parseFloat(r063[1]);
            float r340 = AndroidUtilities.density;
            int r065 = (int) (r064 * r340);
            r339 = (int) (r230 * r340);
            r229 = r065;
        L917:
            CacheImage r066 = this.cacheImage;     // Catch: Throwable -> L880
            File r49 = r066.finalFilePath;     // Catch: Throwable -> L880
            if (r066.imageType != 4) goto L874;
            boolean r99 = r372;
        L875:
            SvgHelper.SvgResult r341 = SvgHelper.getSvgBitmap(r49, r339, r229, r99);     // Catch: Throwable -> L880
            Bitmap r343 = r341.getBitmap();     // Catch: Throwable -> L878
            SvgHelper.SvgResult r067 = r341;
        L883:
            if (r343 != null) goto L885;
        L891:
            if (r067 == null) goto L893;
            List<WallpaperGiftPatternPosition> r511 = r067.getGiftPatternPositions();
        L894:
            onPostExecute(WallpaperGiftBitmapDrawable.create(r343, r511));
            return;
        L893:
            r511 = null;
            goto L894
        L885:
            if (TextUtils.isEmpty(this.cacheImage.filter) == true) goto L891;
            if (this.cacheImage.filter.contains("wallpaper") == false) goto L891;
            Object r231 = this.cacheImage.parentObject;
            if ((r231 instanceof TLRPC.WallPaper) == false) goto L891;
            r343 = applyWallpaperSetting(r343, (TLRPC.WallPaper) r231);
        L878:
            th = th;
        L882:
            FileLog.e(th);
            r067 = r341;
            r343 = null;
            goto L883
        L874:
            r99 = r362;
        L880:
            th = th;
            r341 = null;
        L870:
            r362 = false;
            r372 = true;
            goto L917
        L948:
            CacheImage r234 = this.cacheImage;     // Catch: Throwable -> L23
            ThemePreviewDrawable r512 = new ThemePreviewDrawable(r234.finalFilePath, (DocumentObject.ThemeDocument) r234.imageLocation.document);     // Catch: Throwable -> L23
        L25:
            onPostExecute(r512);
            return;
        L23:
            th = move-exception;
            FileLog.e(th);
            r512 = null;
        L8:
            th = move-exception;
            throw th;
        }
    }

    /* loaded from: classes5.dex */
    public class HttpFileTask extends AsyncTask<Void, Void, Boolean> {
        private boolean canRetry;
        private int currentAccount;
        private String ext;
        private RandomAccessFile fileOutputStream;
        private int fileSize;
        private long lastProgressTime;
        private File tempFile;
        final /* synthetic */ ImageLoader this$0;
        private String url;

        /* renamed from: $r8$lambda$8jj-gMzR5MccZ4c_cEnVax0eldg, reason: not valid java name */
        public static /* synthetic */ void m4177$r8$lambda$8jjgMzR5MccZ4c_cEnVax0eldg(HttpFileTask r0, long r1, long r3) {
            r0.lambda$reportProgress$0(r1, r3);
        }

        public static /* synthetic */ void $r8$lambda$sWD45zG32r6F6MoMD3YSAH8xq80(HttpFileTask r0, long r1, long r3) {
            r0.lambda$reportProgress$1(r1, r3);
        }

        /* renamed from: -$$Nest$fgetcanRetry, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m4178$$Nest$fgetcanRetry(HttpFileTask r0) {
            return r0.canRetry;
        }

        /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m4179$$Nest$fgetcurrentAccount(HttpFileTask r0) {
            return r0.currentAccount;
        }

        /* renamed from: -$$Nest$fgetext, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m4180$$Nest$fgetext(HttpFileTask r0) {
            return r0.ext;
        }

        /* renamed from: -$$Nest$fgettempFile, reason: not valid java name */
        public static /* bridge */ /* synthetic */ File m4181$$Nest$fgettempFile(HttpFileTask r0) {
            return r0.tempFile;
        }

        /* renamed from: -$$Nest$fgeturl, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m4182$$Nest$fgeturl(HttpFileTask r0) {
            return r0.url;
        }

        public HttpFileTask(ImageLoader r1, String r2, File r3, String r4, int r5) {
            this.this$0 = r1;
            this.fileOutputStream = null;
            this.canRetry = true;
            this.url = r2;
            this.tempFile = r3;
            this.ext = r4;
            this.currentAccount = r5;
        }

        private /* synthetic */ void lambda$reportProgress$0(long r3, long r5) {
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.fileLoadProgressChanged, new Object[]{this.url, Long.valueOf(r3), Long.valueOf(r5)});
        }

        private /* synthetic */ void lambda$reportProgress$1(long r11, long r13) {
            ImageLoader.m4141$$Nest$fgetfileProgresses(this.this$0).put(this.url, new long[]{r11, r13});
            AndroidUtilities.runOnUIThread(new ImageLoader$HttpFileTask$$ExternalSyntheticLambda0(this, r11, r13));
        }

        private void reportProgress(long r8, long r10) {
            long r0 = SystemClock.elapsedRealtime();
            if (r8 == r10) goto L10;
            long r2 = this.lastProgressTime;
            if (r2 == 0) goto L10;
            if (r2 < (r0 - 100)) goto L10;
            return;
        L10:
            this.lastProgressTime = r0;
            Utilities.stageQueue.postRunnable(new ImageLoader$HttpFileTask$$ExternalSyntheticLambda1(this, r8, r10));
        }

        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public Boolean doInBackground2(Void... r12) {
            boolean r1 = true;
            boolean r3 = false;
            URLConnection r4 = new URL(this.url).openConnection();     // Catch: Throwable -> L20
            r4.addRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_0 like Mac OS X) AppleWebKit/602.1.38 (KHTML, like Gecko) Version/10.0 Mobile/14A5297c Safari/602.1");     // Catch: Throwable -> L13
            r4.setConnectTimeout(5000);     // Catch: Throwable -> L13
            r4.setReadTimeout(5000);     // Catch: Throwable -> L13
            if ((r4 instanceof HttpURLConnection) == false) goto L15;
            HttpURLConnection r5 = (HttpURLConnection) r4;     // Catch: Throwable -> L13
            r5.setInstanceFollowRedirects(true);     // Catch: Throwable -> L13
            int r6 = r5.getResponseCode();     // Catch: Throwable -> L13
            if (r6 != 302) goto L9;
        L12:
            String r62 = r5.getHeaderField("Location");     // Catch: Throwable -> L13
            String r52 = r5.getHeaderField("Set-Cookie");     // Catch: Throwable -> L13
            r4 = new URL(r62).openConnection();     // Catch: Throwable -> L13
            r4.setRequestProperty("Cookie", r52);     // Catch: Throwable -> L13
            r4.addRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_0 like Mac OS X) AppleWebKit/602.1.38 (KHTML, like Gecko) Version/10.0 Mobile/14A5297c Safari/602.1");     // Catch: Throwable -> L13
            goto L15
        L9:
            if (r6 == 301) goto L12;
            if (r6 == 303) goto L12;
        L15:
            r4.connect();     // Catch: Throwable -> L13
            InputStream r122 = r4.getInputStream();     // Catch: Throwable -> L13
            this.fileOutputStream = new RandomAccessFile(this.tempFile, "rws");     // Catch: Throwable -> L18
        L42:
            if (this.canRetry == false) goto L110;
        L53:
            e = move-exception;
            FileLog.e(e);
        L55:
            if (r4 != null) goto L127;
        L68:
            if (r122 != null) goto L125;
        L119:
            RandomAccessFile r0 = this.fileOutputStream;     // Catch: Throwable -> L102
            if (r0 == null) goto L104;
            r0.close();     // Catch: Throwable -> L102
            this.fileOutputStream = null;     // Catch: Throwable -> L102
        L104:
            if (r122 == null) goto L110;
            r122.close();     // Catch: Throwable -> L107
        L107:
            th = move-exception;
            FileLog.e(th);
        L102:
            th = move-exception;
            FileLog.e(th);
            goto L104
        L125:
            byte[] r02 = new byte[32768];     // Catch: Throwable -> L80
            int r42 = 0;
        L71:
            if (isCancelled() == true) goto L94;
            int r53 = r122.read(r02);     // Catch: Throwable -> L80 Exception -> L82
            if (r53 <= 0) goto L85;
            this.fileOutputStream.write(r02, 0, r53);     // Catch: Throwable -> L80 Exception -> L82
            r42 = r42 + r53;     // Catch: Throwable -> L80 Exception -> L82
            int r54 = this.fileSize;     // Catch: Throwable -> L80 Exception -> L82
            if (r54 <= 0) goto L71;
            reportProgress(r42, r54);     // Catch: Throwable -> L80 Exception -> L82
            goto L71
        L85:
            if (r53 != (-1)) goto L94;
            int r03 = this.fileSize;     // Catch: Throwable -> L90 Exception -> L92
            if (r03 == 0) goto L96;
            reportProgress(r03, r03);     // Catch: Throwable -> L90 Exception -> L92
        L96:
            r3 = r1;
        L92:
            e = e;
        L95:
            FileLog.e(e);     // Catch: Throwable -> L90
        L90:
            th = th;
        L97:
            FileLog.e(th);
        L82:
            e = e;
            r1 = false;
        L94:
            r1 = false;
        L80:
            th = th;
            r1 = false;
            goto L97
        L127:
            Map<String, List<String>> r04 = r4.getHeaderFields();     // Catch: Exception -> L66
            if (r04 == null) goto L68;
            List<String> r05 = r04.get("content-Length");     // Catch: Exception -> L66
            if (r05 == null) goto L68;
            if (r05.isEmpty() == true) goto L68;
            String r06 = r05.get(0);     // Catch: Exception -> L66
            if (r06 == null) goto L68;
            this.fileSize = Utilities.parseInt(r06);     // Catch: Exception -> L66
        L66:
            e = move-exception;
            FileLog.e(e);
            goto L68
        L44:
            if ((r4 instanceof HttpURLConnection) == false) goto L55;
            int r07 = ((HttpURLConnection) r4).getResponseCode();     // Catch: Exception -> L53
            if (r07 == 200) goto L55;
            if (r07 == 202) goto L55;
            if (r07 == 304) goto L55;
            this.canRetry = false;     // Catch: Exception -> L53
        L110:
            return Boolean.valueOf(r3);
        L18:
            th = move-exception;
            InputStream r08 = r122;
            th = th;
        L23:
            if ((th instanceof SocketTimeoutException) == false) goto L28;
            if (ApplicationLoader.isNetworkOnline() == false) goto L40;
            this.canRetry = false;
        L40:
            FileLog.e(th);
            r122 = r08;
            goto L42
        L28:
            if ((th instanceof UnknownHostException) == false) goto L31;
            this.canRetry = false;
            goto L40
        L31:
            if ((th instanceof SocketException) == false) goto L38;
            if (th.getMessage() == null) goto L40;
            if (th.getMessage().contains("ECONNRESET") == false) goto L40;
            this.canRetry = false;
            goto L40
        L38:
            if ((th instanceof FileNotFoundException) == false) goto L40;
            this.canRetry = false;
        L13:
            th = th;
            r08 = null;
        L20:
            th = th;
            r08 = null;
            r4 = null;
            goto L23
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ Boolean doInBackground(Void[] r1) {
            return doInBackground2(r1);
        }

        @Override // android.os.AsyncTask
        public void onCancelled() {
            ImageLoader.m4163$$Nest$mrunHttpFileLoadTasks(this.this$0, this, 2);
        }

        /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        public void onPostExecute2(Boolean r2) {
            ImageLoader r0 = this.this$0;
            if (r2.booleanValue() == false) goto L5;
            int r22 = 2;
        L6:
            ImageLoader.m4163$$Nest$mrunHttpFileLoadTasks(r0, this, r22);
            return;
        L5:
            r22 = 1;
            goto L6
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(Boolean r1) {
            onPostExecute2(r1);
        }
    }

    /* loaded from: classes5.dex */
    public class HttpImageTask extends AsyncTask<Void, Void, Boolean> {
        private CacheImage cacheImage;
        private boolean canRetry;
        private RandomAccessFile fileOutputStream;
        private HttpURLConnection httpConnection;
        private long imageSize;
        private long lastProgressTime;
        private String overrideUrl;
        final /* synthetic */ ImageLoader this$0;

        public static /* synthetic */ void $r8$lambda$2ibETjvWPQbLnbX8pEYY_4TZgRE(HttpImageTask r0, Boolean r1) {
            r0.lambda$onPostExecute$3(r1);
        }

        /* renamed from: $r8$lambda$QcaNox61PUe2uTHX-Ru86epn9wA, reason: not valid java name */
        public static /* synthetic */ void m4183$r8$lambda$QcaNox61PUe2uTHXRu86epn9wA(HttpImageTask r0, Boolean r1) {
            r0.lambda$onPostExecute$4(r1);
        }

        public static /* synthetic */ void $r8$lambda$VtZ9pgxHq5p9zHdMk_lGj0j0CMY(HttpImageTask r0, long r1, long r3) {
            r0.lambda$reportProgress$0(r1, r3);
        }

        public static /* synthetic */ void $r8$lambda$aiFrr36SyeLyAuo3X7DjiwWCFXo(HttpImageTask r0) {
            r0.lambda$onCancelled$7();
        }

        public static /* synthetic */ void $r8$lambda$bZZbVwJfxBEXQ4bRqW35mKQhaoY(HttpImageTask r0) {
            r0.lambda$onCancelled$6();
        }

        /* renamed from: $r8$lambda$cv6vUIEcOP54Auh2HI4OW-QaCg0, reason: not valid java name */
        public static /* synthetic */ void m4184$r8$lambda$cv6vUIEcOP54Auh2HI4OWQaCg0(TLObject r0, TLRPC.TL_error r1) {
        }

        public static /* synthetic */ void $r8$lambda$de0XAT7lJ8xcuT3Df9Yi5Yv5zn8(HttpImageTask r0) {
            r0.lambda$onCancelled$8();
        }

        /* renamed from: $r8$lambda$hsNQ1-xwrb00Ue5B_FsbgECjuJk, reason: not valid java name */
        public static /* synthetic */ void m4185$r8$lambda$hsNQ1xwrb00Ue5B_FsbgECjuJk(HttpImageTask r0) {
            r0.lambda$onPostExecute$5();
        }

        public static /* synthetic */ void $r8$lambda$sqyNugrXRUiGpHiglfIruDMQ93k(HttpImageTask r0, long r1, long r3) {
            r0.lambda$reportProgress$1(r1, r3);
        }

        /* renamed from: -$$Nest$fgetcacheImage, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CacheImage m4186$$Nest$fgetcacheImage(HttpImageTask r0) {
            return r0.cacheImage;
        }

        /* renamed from: -$$Nest$fgetimageSize, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m4187$$Nest$fgetimageSize(HttpImageTask r2) {
            return r2.imageSize;
        }

        public HttpImageTask(ImageLoader r1, CacheImage r2, int r3, String r4) {
            this.this$0 = r1;
            this.canRetry = true;
            this.cacheImage = r2;
            this.imageSize = r3;
            this.overrideUrl = r4;
        }

        public HttpImageTask(ImageLoader r1, CacheImage r2, long r3) {
            this.this$0 = r1;
            this.canRetry = true;
            this.cacheImage = r2;
            this.imageSize = r3;
        }

        private /* synthetic */ void lambda$onCancelled$6() {
            ImageLoader.m4164$$Nest$mrunHttpTasks(this.this$0, true);
        }

        private /* synthetic */ void lambda$onCancelled$7() {
            NotificationCenter.getInstance(this.cacheImage.currentAccount).postNotificationName(NotificationCenter.fileLoadFailed, new Object[]{this.cacheImage.url, 1});
        }

        private /* synthetic */ void lambda$onCancelled$8() {
            ImageLoader.m4141$$Nest$fgetfileProgresses(this.this$0).remove(this.cacheImage.url);
            AndroidUtilities.runOnUIThread(new ImageLoader$HttpImageTask$$ExternalSyntheticLambda8(this));
        }

        private /* synthetic */ void lambda$onPostExecute$3(Boolean r3) {
            boolean r32 = r3.booleanValue();
            CacheImage r0 = this.cacheImage;
            if (r32 == false) goto L6;
            NotificationCenter r33 = NotificationCenter.getInstance(r0.currentAccount);
            int r02 = NotificationCenter.fileLoaded;
            CacheImage r2 = this.cacheImage;
            r33.postNotificationName(r02, new Object[]{r2.url, r2.finalFilePath});
            return;
        L6:
            NotificationCenter.getInstance(r0.currentAccount).postNotificationName(NotificationCenter.fileLoadFailed, new Object[]{this.cacheImage.url, 2});
        }

        private /* synthetic */ void lambda$onPostExecute$4(Boolean r3) {
            ImageLoader.m4141$$Nest$fgetfileProgresses(this.this$0).remove(this.cacheImage.url);
            AndroidUtilities.runOnUIThread(new ImageLoader$HttpImageTask$$ExternalSyntheticLambda3(this, r3));
        }

        private /* synthetic */ void lambda$onPostExecute$5() {
            ImageLoader.m4164$$Nest$mrunHttpTasks(this.this$0, true);
        }

        private /* synthetic */ void lambda$reportProgress$0(long r3, long r5) {
            NotificationCenter.getInstance(this.cacheImage.currentAccount).postNotificationName(NotificationCenter.fileLoadProgressChanged, new Object[]{this.cacheImage.url, Long.valueOf(r3), Long.valueOf(r5)});
        }

        private /* synthetic */ void lambda$reportProgress$1(long r11, long r13) {
            ImageLoader.m4141$$Nest$fgetfileProgresses(this.this$0).put(this.cacheImage.url, new long[]{r11, r13});
            AndroidUtilities.runOnUIThread(new ImageLoader$HttpImageTask$$ExternalSyntheticLambda6(this, r11, r13));
        }

        private void reportProgress(long r8, long r10) {
            long r0 = SystemClock.elapsedRealtime();
            if (r8 == r10) goto L10;
            long r2 = this.lastProgressTime;
            if (r2 == 0) goto L10;
            if (r2 < (r0 - 100)) goto L10;
            return;
        L10:
            this.lastProgressTime = r0;
            Utilities.stageQueue.postRunnable(new ImageLoader$HttpImageTask$$ExternalSyntheticLambda7(this, r8, r10));
        }

        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public Boolean doInBackground2(Void... r13) {
            boolean r0 = true;
            boolean r2 = false;
            if (isCancelled() == false) goto L132;
        L47:
            InputStream r132 = null;
        L49:
            if (isCancelled() == false) goto L148;
        L134:
            RandomAccessFile r02 = this.fileOutputStream;     // Catch: Throwable -> L113
            if (r02 == null) goto L144;
            r02.close();     // Catch: Throwable -> L113
            this.fileOutputStream = null;     // Catch: Throwable -> L113
        L144:
            HttpURLConnection r03 = this.httpConnection;     // Catch: Throwable -> L131
            if (r03 == null) goto L118;
            r03.disconnect();     // Catch: Throwable -> L131
        L118:
            if (r132 != null) goto L138;
        L123:
            if (r2 == false) goto L130;
            CacheImage r133 = this.cacheImage;
            File r04 = r133.tempFilePath;
            if (r04 == null) goto L130;
            if (r04.renameTo(r133.finalFilePath) == true) goto L130;
            CacheImage r12 = this.cacheImage;
            r12.finalFilePath = r12.tempFilePath;
        L130:
            return Boolean.valueOf(r2);
        L138:
            r132.close();     // Catch: Throwable -> L121
        L121:
            th = move-exception;
            FileLog.e(th);
        L113:
            th = move-exception;
            FileLog.e(th);
            goto L144
        L148:
            HttpURLConnection r3 = this.httpConnection;     // Catch: Exception -> L60
            if (r3 == null) goto L63;
            int r32 = r3.getResponseCode();     // Catch: Exception -> L60
            if (r32 == 200) goto L63;
            if (r32 == 202) goto L63;
            if (r32 == 304) goto L63;
            this.canRetry = false;     // Catch: Exception -> L60
        L63:
            if (this.imageSize != 0) goto L78;
            HttpURLConnection r33 = this.httpConnection;
            if (r33 == null) goto L78;
            Map<String, List<String>> r34 = r33.getHeaderFields();     // Catch: Exception -> L76
            if (r34 == null) goto L78;
            List<String> r35 = r34.get("content-Length");     // Catch: Exception -> L76
            if (r35 == null) goto L78;
            if (r35.isEmpty() == true) goto L78;
            if (r35.get(0) == null) goto L78;
            this.imageSize = Utilities.parseInt(r3);     // Catch: Exception -> L76
        L76:
            e = move-exception;
            FileLog.e(e);
        L78:
            if (r132 == null) goto L134;
            byte[] r36 = new byte[8192];     // Catch: Throwable -> L90
            int r4 = 0;
        L82:
            if (isCancelled() == true) goto L104;
            int r7 = r132.read(r36);     // Catch: Throwable -> L90 Exception -> L92
            if (r7 <= 0) goto L95;
            r4 = r4 + r7;     // Catch: Throwable -> L90 Exception -> L92
            this.fileOutputStream.write(r36, 0, r7);     // Catch: Throwable -> L90 Exception -> L92
            long r72 = this.imageSize;     // Catch: Throwable -> L90 Exception -> L92
            if (r72 == 0) goto L82;
            reportProgress(r4, r72);     // Catch: Throwable -> L90 Exception -> L92
            goto L82
        L95:
            if (r7 != (-1)) goto L104;
            long r22 = this.imageSize;     // Catch: Throwable -> L100 Exception -> L102
            if (r22 == 0) goto L107;
            reportProgress(r22, r22);     // Catch: Throwable -> L100 Exception -> L102
        L107:
            r2 = r0;
        L102:
            e = move-exception;
            r2 = true;
            e = e;
        L105:
            FileLog.e(e);     // Catch: Throwable -> L90
        L100:
            th = move-exception;
            r2 = true;
            th = th;
        L108:
            FileLog.e(th);
        L92:
            e = e;
        L104:
            r0 = r2;
        L90:
            th = th;
        L60:
            e = move-exception;
            FileLog.e(e);
            goto L63
        L132:
            String r134 = this.cacheImage.imageLocation.path;     // Catch: Throwable -> L9
            if (r134.startsWith("https://static-maps") == false) goto L7;
        L11:
            int r37 = MessagesController.getInstance(this.cacheImage.currentAccount).mapProvider;     // Catch: Throwable -> L9
            if (r37 != 3) goto L14;
        L15:
            WebFile r38 = (WebFile) ImageLoader.m4152$$Nest$fgettestWebFile(this.this$0).get(r134);     // Catch: Throwable -> L9
            if (r38 == null) goto L18;
            TLRPC.TL_upload_getWebFile r42 = new TLRPC.TL_upload_getWebFile();     // Catch: Throwable -> L9
            r42.location = r38.location;     // Catch: Throwable -> L9
            r42.offset = 0;     // Catch: Throwable -> L9
            r42.limit = 0;     // Catch: Throwable -> L9
            ConnectionsManager.getInstance(this.cacheImage.currentAccount).sendRequest(r42, new ImageLoader$HttpImageTask$$ExternalSyntheticLambda0());     // Catch: Throwable -> L9
        L18:
            String r43 = this.overrideUrl;     // Catch: Throwable -> L9
            if (r43 == null) goto L21;
            r134 = r43;
        L21:
            HttpURLConnection r135 = (HttpURLConnection) new URL(r134).openConnection();     // Catch: Throwable -> L9
            this.httpConnection = r135;     // Catch: Throwable -> L9
            r135.addRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_0 like Mac OS X) AppleWebKit/602.1.38 (KHTML, like Gecko) Version/10.0 Mobile/14A5297c Safari/602.1");     // Catch: Throwable -> L9
            this.httpConnection.setConnectTimeout(5000);     // Catch: Throwable -> L9
            this.httpConnection.setReadTimeout(5000);     // Catch: Throwable -> L9
            this.httpConnection.setInstanceFollowRedirects(true);     // Catch: Throwable -> L9
            if (isCancelled() == true) goto L47;
            this.httpConnection.connect();     // Catch: Throwable -> L9
            r132 = this.httpConnection.getInputStream();     // Catch: Throwable -> L9
            this.fileOutputStream = new RandomAccessFile(this.cacheImage.tempFilePath, "rws");     // Catch: Throwable -> L26
        L26:
            th = move-exception;
            InputStream r39 = r132;
            th = th;
        L29:
            if ((th instanceof SocketTimeoutException) == false) goto L34;
            if (ApplicationLoader.isNetworkOnline() == false) goto L46;
            this.canRetry = false;
        L46:
            FileLog.e(th);
            r132 = r39;
            goto L49
        L34:
            if ((th instanceof UnknownHostException) == false) goto L37;
            this.canRetry = false;
            goto L46
        L37:
            if ((th instanceof SocketException) == false) goto L44;
            if (th.getMessage() == null) goto L46;
            if (th.getMessage().contains("ECONNRESET") == false) goto L46;
            this.canRetry = false;
            goto L46
        L44:
            if ((th instanceof FileNotFoundException) == false) goto L46;
            this.canRetry = false;
            goto L46
        L14:
            if (r37 != 4) goto L18;
        L7:
            if (r134.startsWith("https://maps.googleapis") == false) goto L18;
        L9:
            th = th;
            r39 = null;
            goto L29
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ Boolean doInBackground(Void[] r1) {
            return doInBackground2(r1);
        }

        @Override // android.os.AsyncTask
        public void onCancelled() {
            ImageLoader.m4145$$Nest$fgetimageLoadQueue(this.this$0).postRunnable(new ImageLoader$HttpImageTask$$ExternalSyntheticLambda1(this), this.cacheImage.priority);
            Utilities.stageQueue.postRunnable(new ImageLoader$HttpImageTask$$ExternalSyntheticLambda2(this));
        }

        /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        public void onPostExecute2(Boolean r5) {
            if (r5.booleanValue() == false) goto L5;
        L8:
            ImageLoader r0 = this.this$0;
            CacheImage r1 = this.cacheImage;
            ImageLoader.m4158$$Nest$mfileDidLoaded(r0, r1.url, r1.finalFilePath, 0);
        L9:
            Utilities.stageQueue.postRunnable(new ImageLoader$HttpImageTask$$ExternalSyntheticLambda4(this, r5));
            ImageLoader.m4145$$Nest$fgetimageLoadQueue(this.this$0).postRunnable(new ImageLoader$HttpImageTask$$ExternalSyntheticLambda5(this), this.cacheImage.priority);
            return;
        L5:
            if (this.canRetry == false) goto L8;
            ImageLoader.m4160$$Nest$mhttpFileLoadError(this.this$0, this.cacheImage.url);
            goto L9
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(Boolean r1) {
            onPostExecute2(r1);
        }
    }

    /* loaded from: classes5.dex */
    public static class MessageThumb {
        BitmapDrawable drawable;
        String key;

        public MessageThumb(String r1, BitmapDrawable r2) {
            this.key = r1;
            this.drawable = r2;
        }
    }

    /* loaded from: classes5.dex */
    public static class PhotoSizeFromPhoto extends TLRPC.PhotoSize {
        public final TLRPC.InputPhoto inputPhoto;
        public final TLRPC.Photo photo;

        public PhotoSizeFromPhoto(TLRPC.Photo r4) {
            this.photo = r4;
            TLRPC.TL_inputPhoto r0 = new TLRPC.TL_inputPhoto();
            r0.id = r4.id;
            r0.file_reference = r4.file_reference;
            r0.access_hash = r4.access_hash;
            this.inputPhoto = r0;
        }
    }

    public static class ThumbGenerateInfo {
        private boolean big;
        private String filter;
        private ArrayList<ImageReceiver> imageReceiverArray;
        private ArrayList<Integer> imageReceiverGuidsArray;
        private TLRPC.Document parentDocument;

        /* renamed from: -$$Nest$fgetbig, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m4188$$Nest$fgetbig(ThumbGenerateInfo r0) {
            return r0.big;
        }

        /* renamed from: -$$Nest$fgetfilter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m4189$$Nest$fgetfilter(ThumbGenerateInfo r0) {
            return r0.filter;
        }

        /* renamed from: -$$Nest$fgetimageReceiverArray, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m4190$$Nest$fgetimageReceiverArray(ThumbGenerateInfo r0) {
            return r0.imageReceiverArray;
        }

        /* renamed from: -$$Nest$fgetimageReceiverGuidsArray, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m4191$$Nest$fgetimageReceiverGuidsArray(ThumbGenerateInfo r0) {
            return r0.imageReceiverGuidsArray;
        }

        /* renamed from: -$$Nest$fgetparentDocument, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.Document m4192$$Nest$fgetparentDocument(ThumbGenerateInfo r0) {
            return r0.parentDocument;
        }

        /* renamed from: -$$Nest$fputbig, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4193$$Nest$fputbig(ThumbGenerateInfo r0, boolean r1) {
            r0.big = r1;
        }

        /* renamed from: -$$Nest$fputfilter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4194$$Nest$fputfilter(ThumbGenerateInfo r0, String r1) {
            r0.filter = r1;
        }

        /* renamed from: -$$Nest$fputparentDocument, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4195$$Nest$fputparentDocument(ThumbGenerateInfo r0, TLRPC.Document r1) {
            r0.parentDocument = r1;
        }

        private ThumbGenerateInfo() {
            this.imageReceiverArray = new ArrayList();
            this.imageReceiverGuidsArray = new ArrayList();
        }

        public /* synthetic */ ThumbGenerateInfo(ImageLoaderIA r1) {
            this();
        }
    }

    /* loaded from: classes5.dex */
    public class ThumbGenerateTask implements Runnable {
        private ThumbGenerateInfo info;
        private int mediaType;
        private File originalPath;
        final /* synthetic */ ImageLoader this$0;

        /* renamed from: $r8$lambda$loz4IIPCiFJ8XLLrCL4xOIfAL-A, reason: not valid java name */
        public static /* synthetic */ void m4196$r8$lambda$loz4IIPCiFJ8XLLrCL4xOIfALA(ThumbGenerateTask r0, String r1, ArrayList r2, BitmapDrawable r3, ArrayList r4) {
            r0.lambda$run$1(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$p43qv6yMsK1lo3VmxSxq8oG3Ayg(ThumbGenerateTask r0, String r1) {
            r0.lambda$removeTask$0(r1);
        }

        public ThumbGenerateTask(ImageLoader r1, int r2, File r3, ThumbGenerateInfo r4) {
            this.this$0 = r1;
            this.mediaType = r2;
            this.originalPath = r3;
            this.info = r4;
        }

        private /* synthetic */ void lambda$removeTask$0(String r1) {
            ImageLoader.m4153$$Nest$fgetthumbGenerateTasks(this.this$0).remove(r1);
        }

        private /* synthetic */ void lambda$run$1(String r7, ArrayList r8, BitmapDrawable r9, ArrayList r10) {
            removeTask();
            if (ThumbGenerateInfo.m4189$$Nest$fgetfilter(this.info) == null) goto L5;
            r7 = r7 + "@" + ThumbGenerateInfo.m4189$$Nest$fgetfilter(this.info);
        L5:
            String r2 = r7;
            int r72 = 0;
        L7:
            if (r72 >= r8.size()) goto L10;
            ((ImageReceiver) r8.get(r72)).setImageBitmapByKey(r9, r2, 0, false, ((Integer) r10.get(r72)).intValue());
            r72 = r72 + 1;
            goto L7
        L10:
            if (r2.contains("nocache") == true) goto L14;
            ImageLoader.m4150$$Nest$fgetmemCache(this.this$0).put(r2, r9);
            return;
        }

        private void removeTask() {
            ThumbGenerateInfo r0 = this.info;
            if (r0 != null) goto L5;
            return;
        L5:
            String r02 = FileLoader.getAttachFileName(ThumbGenerateInfo.m4192$$Nest$fgetparentDocument(r0));
            ImageLoader.m4145$$Nest$fgetimageLoadQueue(this.this$0).postRunnable(new ImageLoader$ThumbGenerateTask$$ExternalSyntheticLambda1(this, r02));
        }

        @Override // java.lang.Runnable
        public void run() {
        L7:
            th = th;
        L76:
            FileLog.e(th);
            removeTask();
            return;
        L4:
            if (this.info != null) goto L9;
            removeTask();     // Catch: Throwable -> L7
            return;
        L9:
            String r7 = "q_" + ThumbGenerateInfo.m4192$$Nest$fgetparentDocument(this.info).dc_id + "_" + ThumbGenerateInfo.m4192$$Nest$fgetparentDocument(this.info).id;     // Catch: Throwable -> L7
            File r1 = new File(FileLoader.getDirectory(4), r7.concat(".jpg"));     // Catch: Throwable -> L7
            if (r1.exists() == true) goto L74;
            if (this.originalPath.exists() == false) goto L74;
            if (ThumbGenerateInfo.m4188$$Nest$fgetbig(this.info) == false) goto L17;
            Point r2 = AndroidUtilities.displaySize;     // Catch: Throwable -> L7
            int r22 = Math.max(r2.x, r2.y);     // Catch: Throwable -> L7
        L18:
            int r3 = this.mediaType;     // Catch: Throwable -> L7
            Bitmap r6 = null;
            if (r3 != 0) goto L21;
            float r32 = r22;
            r6 = ImageLoader.loadBitmap(this.originalPath.toString(), null, r32, r32, false);     // Catch: Throwable -> L7
        L46:
            if (r6 != null) goto L49;
            removeTask();     // Catch: Throwable -> L7
            return;
        L49:
            int r0 = r6.getWidth();     // Catch: Throwable -> L7
            int r33 = r6.getHeight();     // Catch: Throwable -> L7
            if (r0 == 0) goto L72;
            if (r33 == 0) goto L72;
            float r02 = r0;
            float r23 = r22;
            float r34 = r33;
            float r24 = Math.min(r02 / r23, r34 / r23);     // Catch: Throwable -> L7
            if (r24 <= 1.0f) goto L58;
            Bitmap r03 = Bitmaps.createScaledBitmap(r6, (int) (r02 / r24), (int) (r34 / r24), true);     // Catch: Throwable -> L7
            if (r03 == r6) goto L58;
            r6.recycle();     // Catch: Throwable -> L7
            r6 = r03;
        L58:
            FileOutputStream r04 = new FileOutputStream(r1);     // Catch: Throwable -> L7
            Bitmap.CompressFormat r12 = Bitmap.CompressFormat.JPEG;     // Catch: Throwable -> L7
            if (ThumbGenerateInfo.m4188$$Nest$fgetbig(this.info) == false) goto L61;
            int r25 = 83;
        L62:
            r6.compress(r12, r25, r04);     // Catch: Throwable -> L7
            r04.close();     // Catch: Throwable -> L7 Exception -> L65
        L69:
            AndroidUtilities.runOnUIThread(new ImageLoader$ThumbGenerateTask$$ExternalSyntheticLambda0(this, r7, new ArrayList(ThumbGenerateInfo.m4190$$Nest$fgetimageReceiverArray(this.info)), new BitmapDrawable(r6), new ArrayList(ThumbGenerateInfo.m4191$$Nest$fgetimageReceiverGuidsArray(this.info))));     // Catch: Throwable -> L70
            return;
        L65:
            e = move-exception;
            FileLog.e(e);     // Catch: Throwable -> L7
            goto L69
        L61:
            r25 = 60;
        L72:
            removeTask();     // Catch: Throwable -> L70
            return;
        L21:
            int r8 = 2;
            if (r3 != 2) goto L29;
            String r05 = this.originalPath.toString();     // Catch: Throwable -> L7
            if (ThumbGenerateInfo.m4188$$Nest$fgetbig(this.info) == true) goto L27;
            r8 = 1;
        L27:
            r6 = SendMessagesHelper.createVideoThumbnail(r05, r8);     // Catch: Throwable -> L7
            goto L46
        L29:
            if (r3 != 3) goto L46;
            String r35 = this.originalPath.toString().toLowerCase();     // Catch: Throwable -> L7
            if (r35.endsWith("mp4") == false) goto L38;
            String r06 = this.originalPath.toString();     // Catch: Throwable -> L7
            if (ThumbGenerateInfo.m4188$$Nest$fgetbig(this.info) == true) goto L36;
            r8 = 1;
        L36:
            r6 = SendMessagesHelper.createVideoThumbnail(r06, r8);     // Catch: Throwable -> L7
            goto L46
        L38:
            if (r35.endsWith(".jpg") == false) goto L40;
        L45:
            float r07 = r22;
            r6 = ImageLoader.loadBitmap(r35, null, r07, r07, false);     // Catch: Throwable -> L7
            goto L46
        L40:
            if (r35.endsWith(".jpeg") == true) goto L45;
            if (r35.endsWith(".png") == true) goto L45;
            if (r35.endsWith(".gif") == false) goto L46;
        L17:
            Point r36 = AndroidUtilities.displaySize;     // Catch: Throwable -> L7
            r22 = Math.min(180, Math.min(r36.x, r36.y) / 4);     // Catch: Throwable -> L7
        L74:
            removeTask();     // Catch: Throwable -> L70
            return;
        L70:
            th = th;
            goto L76
        }
    }

    /* renamed from: $r8$lambda$5pzOn-4mlc2UB3b-035Y4LK8wxI, reason: not valid java name */
    public static /* synthetic */ void m4127$r8$lambda$5pzOn4mlc2UB3b035Y4LK8wxI(ImageLoader r0, String r1) {
        r0.lambda$preloadArtwork$8(r1);
    }

    /* renamed from: $r8$lambda$CQzXsHWYo-3PUc-sph8Gz5xwNqA, reason: not valid java name */
    public static /* synthetic */ void m4128$r8$lambda$CQzXsHWYo3PUcsph8Gz5xwNqA(ImageLoader r0, boolean r1, ImageReceiver r2) {
        r0.lambda$cancelLoadingForImageReceiver$4(r1, r2);
    }

    /* renamed from: $r8$lambda$DXVNzMGCigWZUD9ipC-PDKVggJ4, reason: not valid java name */
    public static /* synthetic */ void m4129$r8$lambda$DXVNzMGCigWZUD9ipCPDKVggJ4(ImageLoader r0, String r1) {
        r0.lambda$artworkLoadError$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$Ij_XXAnzLBfK49N6uILSLD4NzjQ(ImageLoader r0, HttpFileTask r1, int r2) {
        r0.lambda$runHttpFileLoadTasks$14(r1, r2);
    }

    /* renamed from: $r8$lambda$IqkU83djE7dWgU-VLbegjgYcBoI, reason: not valid java name */
    public static /* synthetic */ void m4130$r8$lambda$IqkU83djE7dWgUVLbegjgYcBoI(ImageLoader r0, HttpFileTask r1) {
        r0.lambda$runHttpFileLoadTasks$13(r1);
    }

    /* renamed from: $r8$lambda$KZJO5poeWd6YUYLdP_Jwk4-i6hs, reason: not valid java name */
    public static /* synthetic */ void m4131$r8$lambda$KZJO5poeWd6YUYLdP_Jwk4i6hs(ImageLoader r0, Runnable r1) {
        r0.lambda$checkMediaPaths$1(r1);
    }

    /* renamed from: $r8$lambda$T2-rfu6NFDT0o0J9HOzai6sGFR4, reason: not valid java name */
    public static /* synthetic */ void m4132$r8$lambda$T2rfu6NFDT0o0J9HOzai6sGFR4(ImageLoader r0, String r1) {
        r0.lambda$httpFileLoadError$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$ZYFLfE8erkgwTCFu6aSay0gFacM(ImageLoader r0, String r1) {
        r0.lambda$fileDidFailedLoad$12(r1);
    }

    /* renamed from: $r8$lambda$bac-e6vELOUCoq4DLR0nHnQHBEk, reason: not valid java name */
    public static /* synthetic */ void m4133$r8$lambda$bace6vELOUCoq4DLR0nHnQHBEk(SparseArray r0, Runnable r1) {
        FileLoader.setMediaDirs(r0);
        if (r1 == null) goto L6;
        r1.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$dSNTQmbibPowcNwdjJHmATMrmj0(ImageLoader r0, int r1, String r2, String r3, int r4, ImageReceiver r5, int r6, String r7, int r8, ImageLocation r9, boolean r10, Object r11, int r12, TLRPC.Document r13, boolean r14, boolean r15, String r16, int r17, long r18) {
        r0.lambda$createLoadOperationForImageReceiver$7(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18);
    }

    /* renamed from: $r8$lambda$dtuIA-oVbqpFjquXLnginNz_XsY, reason: not valid java name */
    public static /* synthetic */ void m4134$r8$lambda$dtuIAoVbqpFjquXLnginNz_XsY(ImageLoader r0, String r1, String r2, ImageLocation r3) {
        r0.lambda$replaceImageInCache$5(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$gOJ-HwLnGci070vSdd2ZIzBwZVo, reason: not valid java name */
    public static /* synthetic */ void m4135$r8$lambda$gOJHwLnGci070vSdd2ZIzBwZVo(File r2, j$.nio.file.Path r3) {
        File r0 = new File(r2, r3.getFileName().toString());
        if (Files.isDirectory(r3, new LinkOption[0]) == false) goto L11;
        moveDirectory(r3.toFile(), r0);
        return;
    L11:
        Files.move(r3, FileRetargetClass.toPath(r0), new CopyOption[0]);     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$hLjMfyTb6fmrcWLtjkMA4djFTZA(ImageLoader r0, ImageReceiver r1, int r2) {
        r0.lambda$changeFileLoadingPriorityForImageReceiver$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$kr9qTFMbiSokVkBVqczS3tzn6Xc(ImageLoader r0, String r1, int r2, File r3) {
        r0.lambda$fileDidLoaded$11(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$zDj_e1y_Q_6UOqUHMH9mM4MT_1s(ImageLoader r0, String r1) {
        r0.lambda$cancelForceLoadingForImageReceiver$6(r1);
    }

    /* renamed from: -$$Nest$fgetartworkTasks, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinkedList m4136$$Nest$fgetartworkTasks(ImageLoader r0) {
        return r0.artworkTasks;
    }

    /* renamed from: -$$Nest$fgetbitmapUseCounts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m4137$$Nest$fgetbitmapUseCounts(ImageLoader r0) {
        return r0.bitmapUseCounts;
    }

    /* renamed from: -$$Nest$fgetcacheOutQueue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DispatchQueuePriority m4138$$Nest$fgetcacheOutQueue(ImageLoader r0) {
        return r0.cacheOutQueue;
    }

    /* renamed from: -$$Nest$fgetcacheThumbOutQueue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DispatchQueue m4139$$Nest$fgetcacheThumbOutQueue(ImageLoader r0) {
        return r0.cacheThumbOutQueue;
    }

    /* renamed from: -$$Nest$fgetcanForce8888, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m4140$$Nest$fgetcanForce8888(ImageLoader r0) {
        return r0.canForce8888;
    }

    /* renamed from: -$$Nest$fgetfileProgresses, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ConcurrentHashMap m4141$$Nest$fgetfileProgresses(ImageLoader r0) {
        return r0.fileProgresses;
    }

    /* renamed from: -$$Nest$fgetforceLoadingImages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m4142$$Nest$fgetforceLoadingImages(ImageLoader r0) {
        return r0.forceLoadingImages;
    }

    /* renamed from: -$$Nest$fgethttpTasks, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinkedList m4143$$Nest$fgethttpTasks(ImageLoader r0) {
        return r0.httpTasks;
    }

    /* renamed from: -$$Nest$fgetignoreRemoval, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m4144$$Nest$fgetignoreRemoval(ImageLoader r0) {
        return r0.ignoreRemoval;
    }

    /* renamed from: -$$Nest$fgetimageLoadQueue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DispatchQueue m4145$$Nest$fgetimageLoadQueue(ImageLoader r0) {
        return r0.imageLoadQueue;
    }

    /* renamed from: -$$Nest$fgetimageLoadingByTag, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SparseArray m4146$$Nest$fgetimageLoadingByTag(ImageLoader r0) {
        return r0.imageLoadingByTag;
    }

    /* renamed from: -$$Nest$fgetimageLoadingByUrl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m4147$$Nest$fgetimageLoadingByUrl(ImageLoader r0) {
        return r0.imageLoadingByUrl;
    }

    /* renamed from: -$$Nest$fgetimageLoadingByUrlPframe, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m4148$$Nest$fgetimageLoadingByUrlPframe(ImageLoader r0) {
        return r0.imageLoadingByUrlPframe;
    }

    /* renamed from: -$$Nest$fgetlottieMemCache, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LruCache m4149$$Nest$fgetlottieMemCache(ImageLoader r0) {
        return r0.lottieMemCache;
    }

    /* renamed from: -$$Nest$fgetmemCache, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LruCache m4150$$Nest$fgetmemCache(ImageLoader r0) {
        return r0.memCache;
    }

    /* renamed from: -$$Nest$fgetsmallImagesMemCache, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LruCache m4151$$Nest$fgetsmallImagesMemCache(ImageLoader r0) {
        return r0.smallImagesMemCache;
    }

    /* renamed from: -$$Nest$fgettestWebFile, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ConcurrentHashMap m4152$$Nest$fgettestWebFile(ImageLoader r0) {
        return r0.testWebFile;
    }

    /* renamed from: -$$Nest$fgetthumbGenerateTasks, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m4153$$Nest$fgetthumbGenerateTasks(ImageLoader r0) {
        return r0.thumbGenerateTasks;
    }

    /* renamed from: -$$Nest$fgetwallpaperMemCache, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LruCache m4154$$Nest$fgetwallpaperMemCache(ImageLoader r0) {
        return r0.wallpaperMemCache;
    }

    /* renamed from: -$$Nest$fputlastCacheOutTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4155$$Nest$fputlastCacheOutTime(ImageLoader r0, long r1) {
        r0.lastCacheOutTime = r1;
    }

    /* renamed from: -$$Nest$martworkLoadError, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4156$$Nest$martworkLoadError(ImageLoader r0, String r1) {
        r0.artworkLoadError(r1);
    }

    /* renamed from: -$$Nest$mfileDidFailedLoad, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4157$$Nest$mfileDidFailedLoad(ImageLoader r0, String r1, int r2) {
        r0.fileDidFailedLoad(r1, r2);
    }

    /* renamed from: -$$Nest$mfileDidLoaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4158$$Nest$mfileDidLoaded(ImageLoader r0, String r1, File r2, int r3) {
        r0.fileDidLoaded(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mgetFromLottieCache, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BitmapDrawable m4159$$Nest$mgetFromLottieCache(ImageLoader r0, String r1) {
        return r0.getFromLottieCache(r1);
    }

    /* renamed from: -$$Nest$mhttpFileLoadError, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4160$$Nest$mhttpFileLoadError(ImageLoader r0, String r1) {
        r0.httpFileLoadError(r1);
    }

    /* renamed from: -$$Nest$misAnimatedAvatar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m4161$$Nest$misAnimatedAvatar(ImageLoader r0, String r1) {
        return r0.isAnimatedAvatar(r1);
    }

    /* renamed from: -$$Nest$mrunArtworkTasks, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4162$$Nest$mrunArtworkTasks(ImageLoader r0, boolean r1) {
        r0.runArtworkTasks(r1);
    }

    /* renamed from: -$$Nest$mrunHttpFileLoadTasks, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4163$$Nest$mrunHttpFileLoadTasks(ImageLoader r0, HttpFileTask r1, int r2) {
        r0.runHttpFileLoadTasks(r1, r2);
    }

    /* renamed from: -$$Nest$mrunHttpTasks, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4164$$Nest$mrunHttpTasks(ImageLoader r0, boolean r1) {
        r0.runHttpTasks(r1);
    }

    /* renamed from: -$$Nest$msizeOfBitmapDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m4165$$Nest$msizeOfBitmapDrawable(ImageLoader r0, BitmapDrawable r1) {
        return r0.sizeOfBitmapDrawable(r1);
    }

    /* renamed from: -$$Nest$sfgetbytesLocal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ThreadLocal m4166$$Nest$sfgetbytesLocal() {
        return bytesLocal;
    }

    /* renamed from: -$$Nest$sfgetbytesThumbLocal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ThreadLocal m4167$$Nest$sfgetbytesThumbLocal() {
        return bytesThumbLocal;
    }

    /* renamed from: -$$Nest$sfgetheader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ byte[] m4168$$Nest$sfgetheader() {
        return header;
    }

    /* renamed from: -$$Nest$sfgetheaderThumb, reason: not valid java name */
    public static /* bridge */ /* synthetic */ byte[] m4169$$Nest$sfgetheaderThumb() {
        return headerThumb;
    }

    /* renamed from: -$$Nest$smcutFilter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m4170$$Nest$smcutFilter(String r0) {
        return cutFilter(r0);
    }

    static {
        bytesLocal = new ThreadLocal();
        bytesThumbLocal = new ThreadLocal();
        header = new byte[12];
        headerThumb = new byte[12];
        Instance = null;
    }

    public ImageLoader() {
        this.bitmapUseCounts = new HashMap();
        this.cachedAnimatedFileDrawables = new ArrayList();
        this.imageLoadingByUrl = new HashMap();
        this.imageLoadingByUrlPframe = new HashMap();
        this.imageLoadingByKeys = new ConcurrentHashMap();
        this.imageLoadingKeys = new HashSet();
        this.imageLoadingByTag = new SparseArray();
        this.waitingForQualityThumb = new HashMap();
        this.waitingForQualityThumbByTag = new SparseArray();
        this.httpTasks = new LinkedList();
        this.artworkTasks = new LinkedList();
        this.cacheOutQueue = new DispatchQueuePriority("cacheOutQueue");
        this.cacheThumbOutQueue = new DispatchQueue("cacheThumbOutQueue");
        this.thumbGeneratingQueue = new DispatchQueue("thumbGeneratingQueue");
        this.imageLoadQueue = new DispatchQueue("imageLoadQueue");
        this.replacedBitmaps = new HashMap();
        this.fileProgresses = new ConcurrentHashMap();
        this.thumbGenerateTasks = new HashMap();
        this.forceLoadingImages = new HashMap();
        int r0 = 0;
        this.currentHttpTasksCount = 0;
        this.currentArtworkTasksCount = 0;
        this.testWebFile = new ConcurrentHashMap();
        this.httpFileLoadTasks = new LinkedList();
        this.httpFileLoadTasksByKeys = new HashMap();
        this.retryHttpsTasks = new HashMap();
        this.currentHttpFileLoadTasksCount = 0;
        this.ignoreRemoval = null;
        this.lastCacheOutTime = 0;
        this.lastImageNum = 0;
        this.telegramPath = null;
        boolean r2 = true;
        this.thumbGeneratingQueue.setPriority(1);
        int r1 = ((ActivityManager) ApplicationLoader.applicationContext.getSystemService("activity")).getMemoryClass();
        if (r1 >= 192) goto L6;
        r2 = false;
    L6:
        this.canForce8888 = r2;
        if (r2 == false) goto L9;
        int r22 = 30;
    L10:
        int r12 = Math.min(r22, r1 / 7) * 1048576;
        float r23 = r12;
        this.memCache = new AnonymousClass1(this, (int) (0.8f * r23));
        this.smallImagesMemCache = new AnonymousClass2(this, (int) (r23 * 0.2f));
        this.wallpaperMemCache = new AnonymousClass3(this, r12 / 4);
        this.lottieMemCache = new AnonymousClass4(this, 10485760);
        SparseArray r13 = new SparseArray();
        File r24 = AndroidUtilities.getCacheDir();
        if (r24.isDirectory() == false) goto L28;
    L16:
        AndroidUtilities.createEmptyFile(new File(r24, ".nomedia"));
        r13.put(4, r24);
    L18:
        if (r0 >= 16) goto L20;
        FileLoader.getInstance(r0).setDelegate(new AnonymousClass5(this, r0));
        r0 = r0 + 1;
        goto L18
    L20:
        FileLoader.setMediaDirs(r13);
        AnonymousClass6 r02 = new AnonymousClass6(this);
        IntentFilter r14 = new IntentFilter();
        r14.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
        r14.addAction("android.intent.action.MEDIA_CHECKING");
        r14.addAction("android.intent.action.MEDIA_EJECT");
        r14.addAction("android.intent.action.MEDIA_MOUNTED");
        r14.addAction("android.intent.action.MEDIA_NOFS");
        r14.addAction("android.intent.action.MEDIA_REMOVED");
        r14.addAction("android.intent.action.MEDIA_SHARED");
        r14.addAction("android.intent.action.MEDIA_UNMOUNTABLE");
        r14.addAction("android.intent.action.MEDIA_UNMOUNTED");
        r14.addDataScheme("file");
        if (Build.VERSION.SDK_INT < 33) goto L24;
        ApplicationLoader.applicationContext.registerReceiver(r02, r14, 4);     // Catch: Throwable -> L27
    L30:
        goto L25
    L24:
        ApplicationLoader.applicationContext.registerReceiver(r02, r14);     // Catch: Throwable -> L27
    L25:
        checkMediaPaths();
        return;
    L28:
        r24.mkdirs();     // Catch: Exception -> L14
    L14:
        e = move-exception;
        FileLog.e(e);
        goto L16
    L9:
        r22 = 15;
        goto L10
    }

    private void artworkLoadError(String r3) {
        this.imageLoadQueue.postRunnable(new ImageLoader$$ExternalSyntheticLambda12(this, r3));
    }

    private boolean canMoveFiles(File r5, File r6, int r7) {
        RandomAccessFile r2 = null;
        if (r7 != 0) goto L10;
        File r72 = new File(r5, "000000000_999999_temp.f");     // Catch: Throwable -> L5 Exception -> L7
        File r52 = new File(r6, "000000000_999999.f");     // Catch: Throwable -> L5 Exception -> L7
    L23:
        byte[] r62 = new byte[1024];     // Catch: Throwable -> L5 Exception -> L7
        r72.createNewFile();     // Catch: Throwable -> L5 Exception -> L7
        RandomAccessFile r0 = new RandomAccessFile(r72, "rws");     // Catch: Throwable -> L5 Exception -> L7
        r0.write(r62);     // Catch: Throwable -> L28 Exception -> L30
        r0.close();     // Catch: Throwable -> L28 Exception -> L30
        boolean r63 = r72.renameTo(r52);     // Catch: Throwable -> L5 Exception -> L7
        r72.delete();     // Catch: Throwable -> L5 Exception -> L7
        r52.delete();     // Catch: Throwable -> L5 Exception -> L7
        if (r63 == false) goto L54;
        return true;
    L54:
        return false;
    L30:
        e = e;
        r2 = r0;
    L32:
        FileLog.e(e);     // Catch: Throwable -> L5
        if (r2 == null) goto L55;
        r2.close();     // Catch: Exception -> L36
        return false;
    L36:
        e = move-exception;
        FileLog.e(e);
        return false;
    L55:
        return false;
    L28:
        th = th;
        r2 = r0;
    L40:
        if (r2 != null) goto L49;
    L45:
        throw th;
    L49:
        r2.close();     // Catch: Exception -> L43
    L43:
        e = move-exception;
        FileLog.e(e);
        goto L45
    L10:
        if (r7 != 3) goto L12;
    L22:
        r72 = new File(r5, "000000000_999999_temp.f");     // Catch: Throwable -> L5 Exception -> L7
        r52 = new File(r6, "000000000_999999.f");     // Catch: Throwable -> L5 Exception -> L7
        goto L23
    L12:
        if (r7 == 5) goto L22;
        if (r7 == 6) goto L22;
        if (r7 != 1) goto L19;
        r72 = new File(r5, "000000000_999999_temp.f");     // Catch: Throwable -> L5 Exception -> L7
        r52 = new File(r6, "000000000_999999.f");     // Catch: Throwable -> L5 Exception -> L7
        goto L23
    L19:
        if (r7 != 2) goto L21;
        r72 = new File(r5, "000000000_999999_temp.f");     // Catch: Throwable -> L5 Exception -> L7
        r52 = new File(r6, "000000000_999999.f");     // Catch: Throwable -> L5 Exception -> L7
        goto L23
    L21:
        r52 = null;
        r72 = null;
    L5:
        th = th;
    L7:
        e = e;
        goto L32
    }

    private void createLoadOperationForImageReceiver(ImageReceiver r22, String r23, String r24, String r25, ImageLocation r26, String r27, long r28, int r30, int r31, int r32, int r33) {
        if (r22 == null) goto L25;
        if (r24 == null) goto L26;
        if (r23 == null) goto L27;
        if (r26 == null) goto L28;
        int r0 = r22.getTag(r31);
        if (r0 != 0) goto L13;
        r0 = this.lastImageNum;
        r22.setTag(r0, r31);
        int r4 = this.lastImageNum + 1;
        this.lastImageNum = r4;
        if (r4 != Integer.MAX_VALUE) goto L13;
        this.lastImageNum = 0;
    L13:
        int r5 = r0;
        boolean r15 = r22.isNeedsQualityThumb();
        Object r12 = r22.getParentObject();
        TLRPC.Document r14 = r22.getQualityThumbDocument();
        boolean r16 = r22.isShouldGenerateQualityThumb();
        int r13 = r22.getCurrentAccount();
        if (r31 == 0) goto L16;
    L18:
        boolean r11 = false;
    L19:
        ImageLoader$$ExternalSyntheticLambda6 r02 = new ImageLoader$$ExternalSyntheticLambda6(this, r32, r24, r23, r5, r22, r33, r27, r31, r26, r11, r12, r13, r14, r15, r16, r25, r30, r28);
        DispatchQueue r1 = this.imageLoadQueue;
        if (r22.getFileLoadingPriority() != 0) goto L22;
        long r2 = 0;
    L23:
        r1.postRunnable(r02, r2);
        r22.addLoadingImageRunnable(r02);
        return;
    L22:
        r2 = 1;
        goto L23
    L16:
        if (r22.isCurrentKeyQuality() == false) goto L18;
        r11 = true;
        goto L19
    L28:
        return;
    L27:
        return;
    L26:
        return;
    }

    public static Drawable createStripedBitmap(ArrayList<TLRPC.PhotoSize> r3) {
        int r0 = 0;
    L4:
        if (r0 >= r3.size()) goto L10;
        if ((r3.get(r0) instanceof TLRPC.TL_photoStrippedSize) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        TLRPC.TL_photoStrippedSize r32 = (TLRPC.TL_photoStrippedSize) r3.get(r0);
        return new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), getStrippedPhotoBitmap(r32.bytes, "b"));
    L10:
        return null;
    }

    private static String cutFilter(String r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        int r0 = r2.indexOf(64);
        if (r0 >= 0) goto L8;
        return r2;
    L8:
        return r2.substring(0, r0);
    }

    public static String decompressGzip(File r5) {
        StringBuilder r0 = new StringBuilder();
        if (r5 != null) goto L29;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L29:
        GZIPInputStream r2 = new GZIPInputStream(new FileInputStream(r5));     // Catch: Exception -> L28
        BufferedReader r52 = new BufferedReader(new InputStreamReader(r2, "UTF-8"));     // Catch: Throwable -> L16
    L33:
        String r3 = r52.readLine();     // Catch: Throwable -> L10
        if (r3 == null) goto L12;
        r0.append(r3);     // Catch: Throwable -> L10
        goto L33
    L12:
        String r02 = r0.toString();     // Catch: Throwable -> L10
        r52.close();     // Catch: Throwable -> L16
        r2.close();     // Catch: Exception -> L28
        return r02;
    L10:
        th = move-exception;
        r52.close();     // Catch: Throwable -> L20
    L22:
        throw th;     // Catch: Throwable -> L16
    L20:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        r2.close();     // Catch: Throwable -> L25
    L27:
        throw th;     // Catch: Exception -> L28
    L25:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L28
    L28:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private void fileDidFailedLoad(String r2, int r3) {
        if (r3 != 1) goto L5;
        return;
    L5:
        this.imageLoadQueue.postRunnable(new ImageLoader$$ExternalSyntheticLambda9(this, r2));
    }

    private void fileDidLoaded(String r3, File r4, int r5) {
        this.imageLoadQueue.postRunnable(new ImageLoader$$ExternalSyntheticLambda14(this, r3, r5, r4));
    }

    public static TLRPC.PhotoSize fileToSize(String r9, boolean r10) {
        if (r9 != null) goto L35;
        return null;
    L35:
        BitmapFactory.Options r1 = new BitmapFactory.Options();     // Catch: Exception -> L32
        r1.inJustDecodeBounds = true;     // Catch: Exception -> L32
        BitmapFactory.decodeFile(r9, r1);     // Catch: Exception -> L32
        int r2 = r1.outWidth;     // Catch: Exception -> L32
        int r0 = r1.outHeight;     // Catch: Exception -> L32
        TLRPC.TL_fileLocationToBeDeprecated r12 = new TLRPC.TL_fileLocationToBeDeprecated();
        r12.volume_id = -2147483648L;
        r12.dc_id = Integer.MIN_VALUE;
        r12.local_id = SharedConfig.getLastLocalId();
        r12.file_reference = new byte[0];
        TLRPC.TL_photoSize_layer127 r6 = new TLRPC.TL_photoSize_layer127();
        r6.location = r12;
        r6.w = r2;
        r6.h = r0;
        if (r2 > 100) goto L11;
        if (r0 > 100) goto L11;
        r6.type = "s";
    L23:
        String r02 = r12.volume_id + "_" + r12.local_id + ".jpg";
        if (r10 == false) goto L27;
        File r102 = FileLoader.getDirectory(4);
    L30:
        File r13 = new File(r102, r02);
        new File(r9).renameTo(r13);
        r6.size = (int) r13.length();
        return r6;
    L27:
        if (r12.volume_id == (-2147483648L)) goto L29;
        r102 = FileLoader.getDirectory(0);
        goto L30
    L29:
        r102 = FileLoader.getDirectory(4);
    L11:
        if (r2 > 320) goto L15;
        if (r0 > 320) goto L15;
        r6.type = "m";
    L15:
        if (r2 > 800) goto L19;
        if (r0 > 800) goto L19;
        r6.type = "x";
    L19:
        if (r2 > 1280) goto L22;
        if (r0 > 1280) goto L22;
        r6.type = "y";
    L22:
        r6.type = "w";
    L32:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static void fillPhotoSizeWithBytes(TLRPC.PhotoSize r4) {
        if (r4 == null) goto L18;
        byte[] r0 = r4.bytes;
        if (r0 == null) goto L16;
        if (r0.length == 0) goto L16;
        return;
    L16:
        RandomAccessFile r1 = new RandomAccessFile(FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r4, true), "r");     // Catch: Throwable -> L13
        if (((int) r1.length()) >= 20000) goto L19;
        byte[] r02 = new byte[(int) r1.length()];     // Catch: Throwable -> L13
        r4.bytes = r02;     // Catch: Throwable -> L13
        r1.readFully(r02, 0, r02.length);     // Catch: Throwable -> L13
        return;
    L19:
        return;
    L13:
        th = move-exception;
        FileLog.e(th);
        return;
    }

    private Drawable findInPreloadImageReceivers(String r4, List<ImageReceiver> r5) {
        if (r5 != null) goto L5;
        return null;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= r5.size()) goto L17;
        ImageReceiver r1 = r5.get(r0);
        if (r4.equals(r1.getImageKey()) == true) goto L11;
        if (r4.equals(r1.getMediaKey()) == true) goto L15;
        r0 = r0 + 1;
        goto L7
    L15:
        return r1.getMediaDrawable();
    L11:
        return r1.getImageDrawable();
    L17:
        return null;
    }

    private static TLRPC.PhotoSize findPhotoCachedSize(TLRPC.Message r4) {
        TLRPC.MessageMedia r0 = r4.media;
        int r2 = 0;
        if ((r0 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L11;
        int r02 = r0.photo.sizes.size();
    L5:
        if (r2 >= r02) goto L50;
        TLRPC.PhotoSize r1 = r4.media.photo.sizes.get(r2);
        if ((r1 instanceof TLRPC.TL_photoCachedSize) == true) goto L8;
        r2 = r2 + 1;
        goto L5
    L8:
        return r1;
    L50:
        return null;
    L11:
        if ((r0 instanceof TLRPC.TL_messageMediaDocument) == false) goto L21;
        TLRPC.Document r03 = r0.document;
        if (r03 == null) goto L38;
        int r04 = r03.thumbs.size();
    L15:
        if (r2 >= r04) goto L51;
        TLRPC.PhotoSize r12 = r4.media.document.thumbs.get(r2);
        if ((r12 instanceof TLRPC.TL_photoCachedSize) == true) goto L18;
        r2 = r2 + 1;
        goto L15
    L18:
        return r12;
    L51:
        return null;
    L38:
        return null;
    L21:
        if ((r0 instanceof TLRPC.TL_messageMediaWebPage) == false) goto L31;
        TLRPC.Photo r05 = r0.webpage.photo;
        if (r05 == null) goto L46;
        int r06 = r05.sizes.size();
    L25:
        if (r2 >= r06) goto L52;
        TLRPC.PhotoSize r13 = r4.media.webpage.photo.sizes.get(r2);
        if ((r13 instanceof TLRPC.TL_photoCachedSize) == true) goto L28;
        r2 = r2 + 1;
        goto L25
    L28:
        return r13;
    L52:
        return null;
    L46:
        return null;
    L31:
        if ((r0 instanceof TLRPC.TL_messageMediaInvoice) == true) goto L33;
        return null;
    L33:
        if (r0.extended_media.isEmpty() == false) goto L35;
        return null;
    L35:
        if ((r4.media.extended_media.get(0) instanceof TLRPC.TL_messageExtendedMediaPreview) == true) goto L37;
        return null;
    L37:
        return ((TLRPC.TL_messageExtendedMediaPreview) r4.media.extended_media.get(0)).thumb;
    }

    private static TLRPC.PhotoSize findPhotoCachedSize(TLRPC.MessageMedia r4) {
        int r1 = 0;
        if ((r4 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L11;
        int r0 = r4.photo.sizes.size();
    L5:
        if (r1 >= r0) goto L50;
        TLRPC.PhotoSize r2 = r4.photo.sizes.get(r1);
        if ((r2 instanceof TLRPC.TL_photoCachedSize) == true) goto L8;
        r1 = r1 + 1;
        goto L5
    L8:
        return r2;
    L50:
        return null;
    L11:
        if ((r4 instanceof TLRPC.TL_messageMediaDocument) == false) goto L21;
        TLRPC.Document r02 = r4.document;
        if (r02 == null) goto L38;
        int r03 = r02.thumbs.size();
    L15:
        if (r1 >= r03) goto L51;
        TLRPC.PhotoSize r22 = r4.document.thumbs.get(r1);
        if ((r22 instanceof TLRPC.TL_photoCachedSize) == true) goto L18;
        r1 = r1 + 1;
        goto L15
    L18:
        return r22;
    L51:
        return null;
    L38:
        return null;
    L21:
        if ((r4 instanceof TLRPC.TL_messageMediaWebPage) == false) goto L31;
        TLRPC.Photo r04 = r4.webpage.photo;
        if (r04 == null) goto L46;
        int r05 = r04.sizes.size();
    L25:
        if (r1 >= r05) goto L52;
        TLRPC.PhotoSize r23 = r4.webpage.photo.sizes.get(r1);
        if ((r23 instanceof TLRPC.TL_photoCachedSize) == true) goto L28;
        r1 = r1 + 1;
        goto L25
    L28:
        return r23;
    L52:
        return null;
    L46:
        return null;
    L31:
        if ((r4 instanceof TLRPC.TL_messageMediaInvoice) == true) goto L33;
        return null;
    L33:
        if (r4.extended_media.isEmpty() == false) goto L35;
        return null;
    L35:
        if ((r4.extended_media.get(0) instanceof TLRPC.TL_messageExtendedMediaPreview) == true) goto L37;
        return null;
    L37:
        return ((TLRPC.TL_messageExtendedMediaPreview) r4.extended_media.get(0)).thumb;
    }

    public static MessageThumb generateMessageThumb(TLRPC.Message r13) {
        TLRPC.PhotoSize r0 = findPhotoCachedSize(r13);
        if (r0 == null) goto L21;
        byte[] r5 = r0.bytes;
        if (r5 == null) goto L21;
        if (r5.length == 0) goto L21;
        File r52 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r0, true);
        TLRPC.TL_photoSize_layer127 r6 = new TLRPC.TL_photoSize_layer127();
        r6.w = r0.w;
        r6.h = r0.h;
        r6.location = r0.location;
        r6.size = r0.size;
        r6.type = r0.type;
        if (r52.exists() == true) goto L11;
    L48:
        return null;
    L11:
        if (r13.grouped_id != 0) goto L48;
        PointF r132 = ChatMessageCell.getMessageSize(r0.w, r0.h);
        String r02 = String.format(Locale.US, "%d_%d@%d_%d_b", new Object[]{Long.valueOf(r0.location.volume_id), Integer.valueOf(r0.location.local_id), Integer.valueOf((int) (r132.x / AndroidUtilities.density)), Integer.valueOf((int) (r132.y / AndroidUtilities.density))});
        if (getInstance().isInMemCache(r02, false) == true) goto L48;
        String r53 = r52.getPath();
        float r62 = r132.x;
        float r7 = AndroidUtilities.density;
        Bitmap r4 = loadBitmap(r53, null, (int) (r62 / r7), (int) (r132.y / r7), false);
        if (r4 == null) goto L48;
        Utilities.blurBitmap(r4, 3);
        float r1 = r132.x;
        float r2 = AndroidUtilities.density;
        Bitmap r133 = Bitmaps.createScaledBitmap(r4, (int) (r1 / r2), (int) (r132.y / r2), true);
        if (r133 == r4) goto L20;
        r4.recycle();
        r4 = r133;
    L20:
        return new MessageThumb(r02, new BitmapDrawable(r4));
    L21:
        TLRPC.MessageMedia r03 = r13.media;
        if ((r03 instanceof TLRPC.TL_messageMediaDocument) == false) goto L48;
        int r04 = r03.document.thumbs.size();
        int r54 = 0;
    L24:
        if (r54 >= r04) goto L48;
        TLRPC.PhotoSize r63 = r13.media.document.thumbs.get(r54);
        if ((r63 instanceof TLRPC.TL_photoStrippedSize) == false) goto L47;
        TLRPC.PhotoSize r72 = FileLoader.getClosestPhotoSizeWithSize(r13.media.document.thumbs, 320);
        if (r72 == null) goto L30;
        int r8 = r72.h;
        int r73 = r72.w;
    L38:
        PointF r74 = ChatMessageCell.getMessageSize(r73, r8);
        String r82 = String.format(Locale.US, "%s_false@%d_%d_b", new Object[]{ImageLocation.getStrippedKey(r13, r13, r63), Integer.valueOf((int) (r74.x / AndroidUtilities.density)), Integer.valueOf((int) (r74.y / AndroidUtilities.density))});
        if (getInstance().isInMemCache(r82, false) == true) goto L47;
        Bitmap r64 = getStrippedPhotoBitmap(r63.bytes, null);
        if (r64 == null) goto L47;
        Utilities.blurBitmap(r64, 3);
        float r134 = r74.x;
        float r05 = AndroidUtilities.density;
        Bitmap r135 = Bitmaps.createScaledBitmap(r64, (int) (r134 / r05), (int) (r74.y / r05), true);
        if (r135 == r64) goto L46;
        r64.recycle();
        r64 = r135;
    L46:
        return new MessageThumb(r82, new BitmapDrawable(r64));
    L30:
        int r75 = 0;
    L32:
        if (r75 >= r13.media.document.attributes.size()) goto L37;
        if ((r13.media.document.attributes.get(r75) instanceof TLRPC.TL_documentAttributeVideo) == true) goto L35;
        r75 = r75 + 1;
        goto L32
    L35:
        TLRPC.TL_documentAttributeVideo r76 = (TLRPC.TL_documentAttributeVideo) r13.media.document.attributes.get(r75);
        r8 = r76.h;
        r73 = r76.w;
        goto L38
    L37:
        r73 = 0;
        r8 = 0;
    L47:
        r54 = r54 + 1;
        goto L24
    }

    private void generateThumb(int r3, File r4, ThumbGenerateInfo r5) {
        if (r3 != 0) goto L4;
    L7:
        if (r4 == null) goto L15;
        if (r5 == null) goto L17;
        String r0 = FileLoader.getAttachFileName(ThumbGenerateInfo.m4192$$Nest$fgetparentDocument(r5));
        if (this.thumbGenerateTasks.get(r0) != null) goto L16;
        ThumbGenerateTask r02 = new ThumbGenerateTask(this, r3, r4, r5);
        this.thumbGeneratingQueue.postRunnable(r02);
        return;
    L16:
        return;
    L17:
        return;
    L15:
        return;
    L4:
        if (r3 == 2) goto L7;
        if (r3 == 3) goto L7;
    }

    private BitmapDrawable getFromLottieCache(String r3) {
        BitmapDrawable r0 = this.lottieMemCache.get(r3);
        if ((r0 instanceof AnimatedFileDrawable) == true) goto L5;
    L8:
        return r0;
    L5:
        if (((AnimatedFileDrawable) r0).isRecycled() == false) goto L8;
        this.lottieMemCache.remove(r3);
        return null;
    }

    public static String getHttpFileName(String r0) {
        return Utilities.MD5(r0);
    }

    public static File getHttpFilePath(String r3, String r4) {
        String r42 = getHttpUrlExtension(r3, r4);
        return new File(FileLoader.getDirectory(4), Utilities.MD5(r3) + "." + r42);
    }

    public static String getHttpUrlExtension(String r3, String r4) {
        String r0 = Uri.parse(r3).getLastPathSegment();
        if (TextUtils.isEmpty(r0) == false) goto L5;
    L7:
        int r02 = r3.lastIndexOf(46);
        if (r02 == (-1)) goto L10;
        String r32 = r3.substring(r02 + 1);
    L11:
        if (r32 != null) goto L13;
    L18:
        return r4;
    L13:
        if (r32.length() == 0) goto L18;
        if (r32.length() > 4) goto L18;
        return r32;
    L10:
        r32 = null;
        goto L11
    L5:
        if (r0.length() <= 1) goto L7;
        r3 = r0;
        goto L7
    }

    public static ImageLoader getInstance() {
        ImageLoader r0 = Instance;
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(ImageLoader.class);
        ImageLoader r02 = Instance;     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        r02 = new ImageLoader();     // Catch: Throwable -> L9
        Instance = r02;     // Catch: Throwable -> L9
    L11:
        monitor-exit(ImageLoader.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    private File getPublicStorageDir() {
        int r0 = 0;
        File r3 = ApplicationLoader.applicationContext.getExternalMediaDirs()[0];
        if (TextUtils.isEmpty(SharedConfig.storageCacheDir) == false) goto L5;
    L12:
        return r3;
    L5:
        if (r0 >= ApplicationLoader.applicationContext.getExternalMediaDirs().length) goto L12;
        File r1 = ApplicationLoader.applicationContext.getExternalMediaDirs()[r0];
        if (r1 == null) goto L11;
        if (r1.getPath().startsWith(SharedConfig.storageCacheDir) == false) goto L11;
        r3 = ApplicationLoader.applicationContext.getExternalMediaDirs()[r0];
    L11:
        r0 = r0 + 1;
        goto L5
    }

    public static Bitmap getStrippedPhotoBitmap(byte[] r11, String r12) {
        int r0 = ((r11.length - 3) + Bitmaps.header.length) + Bitmaps.footer.length;
        byte[] r2 = bytesLocal.get();
        if (r2 != null) goto L5;
    L7:
        r2 = null;
    L8:
        if (r2 != null) goto L10;
        r2 = new byte[r0];
        bytesLocal.set(r2);
    L10:
        byte[] r4 = Bitmaps.header;
        System.arraycopy(r4, 0, r2, 0, r4.length);
        System.arraycopy(r11, 3, r2, Bitmaps.header.length, r11.length - 3);
        System.arraycopy(Bitmaps.footer, 0, r2, (Bitmaps.header.length + r11.length) - 3, Bitmaps.footer.length);
        boolean r5 = true;
        r2[164(0xa4, float:2.3E-43)] = r11[1];
        r2[166(0xa6, float:2.33E-43)] = r11[2];
        BitmapFactory.Options r112 = new BitmapFactory.Options();
        if (TextUtils.isEmpty(r12) == false) goto L13;
    L15:
        r5 = false;
    L17:
        if (SharedConfig.deviceIsHigh() == true) goto L21;
        if (r5 == true) goto L21;
        Bitmap.Config r42 = Bitmap.Config.RGB_565;
    L22:
        r112.inPreferredConfig = r42;
        Bitmap r113 = BitmapFactory.decodeByteArray(r2, 0, r0, r112);
        if (r5 == false) goto L25;
        Bitmap r02 = Bitmap.createBitmap(r113.getWidth(), r113.getHeight(), r113.getConfig());
        Canvas r22 = new Canvas(r02);
        r22.save();
        r22.scale(1.2f, 1.2f, r113.getWidth() / 2.0f, r113.getHeight() / 2.0f);
        r22.drawBitmap(r113, 0.0f, 0.0f, null);
        r22.restore();
        Path r6 = new Path();
        r6.addCircle(r113.getWidth() / 2.0f, r113.getHeight() / 2.0f, Math.min(r113.getWidth(), r113.getHeight()) / 2.0f, Path.Direction.CW);
        r22.clipPath(r6);
        r22.drawBitmap(r113, 0.0f, 0.0f, null);
        r113.recycle();
        r113 = r02;
    L25:
        if (r113 != null) goto L27;
    L31:
        return r113;
    L27:
        if (TextUtils.isEmpty(r12) == true) goto L31;
        if (r12.contains("b") == false) goto L31;
        Utilities.blurBitmap(r113, 3);
    L21:
        r42 = Bitmap.Config.ARGB_8888;
        goto L22
    L13:
        if (r12.contains("r") == false) goto L15;
    L5:
        if (r2.length < r0) goto L7;
        goto L7
    }

    public static boolean hasAutoplayFilter(String r4) {
        if (r4 != null) goto L5;
        return false;
    L5:
        String[] r42 = r4.split("_");
        int r1 = 0;
    L7:
        if (r1 >= r42.length) goto L18;
        if (AUTOPLAY_FILTER.equals(r42[r1]) == true) goto L16;
        if (AUTOPLAY_FILTER_NONLOOP.equals(r42[r1]) == true) goto L23;
        if ("pframe".equals(r42[r1]) == true) goto L24;
        r1 = r1 + 1;
        goto L7
    L24:
        return true;
    L23:
        return true;
    L16:
        return true;
    L18:
        return false;
    }

    private void httpFileLoadError(String r3) {
        this.imageLoadQueue.postRunnable(new ImageLoader$$ExternalSyntheticLambda4(this, r3));
    }

    private boolean isAnimatedAvatar(String r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.endsWith("avatar") == false) goto L9;
        return true;
    L9:
        return false;
    }

    private boolean isPFrame(String r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.endsWith("pframe") == false) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isSdCardPath(File r1) {
        if (TextUtils.isEmpty(SharedConfig.storageCacheDir) == false) goto L5;
        return false;
    L5:
        if (r1.getAbsolutePath().startsWith(SharedConfig.storageCacheDir) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$artworkLoadError$10(String r3) {
        CacheImage r32 = this.imageLoadingByUrl.get(r3);
        if (r32 != null) goto L5;
        return;
    L5:
        ArtworkLoadTask r0 = r32.artworkTask;
        if (r0 == null) goto L8;
        ArtworkLoadTask r1 = new ArtworkLoadTask(this, ArtworkLoadTask.m4174$$Nest$fgetcacheImage(r0));
        r32.artworkTask = r1;
        this.artworkTasks.add(r1);
    L8:
        runArtworkTasks(false);
    }

    private /* synthetic */ void lambda$cancelForceLoadingForImageReceiver$6(String r1) {
        this.forceLoadingImages.remove(r1);
    }

    private /* synthetic */ void lambda$cancelLoadingForImageReceiver$4(boolean r5, ImageReceiver r6) {
        int r1 = 0;
    L3:
        int r2 = 3;
        if (r1 >= 3) goto L19;
        if (r1 <= 0) goto L9;
        if (r5 == true) goto L9;
        return;
    L9:
        if (r1 != 0) goto L11;
        r2 = 1;
    L13:
        int r22 = r6.getTag(r2);
        if (r22 == 0) goto L18;
        removeFromWaitingForThumb(r22, r6);
        CacheImage r23 = this.imageLoadingByTag.get(r22);
        if (r23 == null) goto L18;
        r23.removeImageReceiver(r6);
    L18:
        r1 = r1 + 1;
        goto L3
    L11:
        if (r1 != 1) goto L13;
        r2 = 0;
        goto L13
    }

    private /* synthetic */ void lambda$changeFileLoadingPriorityForImageReceiver$3(ImageReceiver r5, int r6) {
        int r1 = 0;
    L3:
        int r2 = 3;
        if (r1 >= 3) goto L16;
        if (r1 != 0) goto L8;
        r2 = 1;
    L10:
        int r22 = r5.getTag(r2);
        if (r22 == 0) goto L15;
        CacheImage r23 = this.imageLoadingByTag.get(r22);
        if (r23 == null) goto L15;
        r23.changePriority(r6);
    L15:
        r1 = r1 + 1;
        goto L3
    L8:
        if (r1 != 1) goto L10;
        r2 = 0;
        goto L10
    }

    private /* synthetic */ void lambda$checkMediaPaths$1(Runnable r2) {
        AndroidUtilities.runOnUIThread(new ImageLoader$$ExternalSyntheticLambda8(createMediaPaths(), r2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r18v0 */
    /* JADX WARN: Type inference failed for: r18v1, types: [int] */
    /* JADX WARN: Type inference failed for: r18v12 */
    /* JADX WARN: Type inference failed for: r18v13 */
    private /* synthetic */ void lambda$createLoadOperationForImageReceiver$7(int r26, String r27, String r28, int r29, ImageReceiver r30, int r31, String r32, int r33, ImageLocation r34, boolean r35, Object r36, int r37, TLRPC.Document r38, boolean r39, boolean r40, String r41, int r42, long r43) {
        ImageReceiver r4 = r30;
        TLRPC.Document r12 = r38;
        if (r26 == 2) goto L24;
        CacheImage r7 = this.imageLoadingByUrl.get(r27);
        CacheImage r8 = this.imageLoadingByKeys.get(r28);
        CacheImage r3 = this.imageLoadingByTag.get(r29);
        if (r3 == null) goto L15;
        if (r3 != r8) goto L10;
        r3.setImageReceiverGuid(r4, r31);
        CacheImage r16 = r7;
        CacheImage r17 = r8;
        boolean r18 = false;
    L8:
        boolean r20 = true;
        boolean r182 = r18;
    L17:
        if (r20 == true) goto L20;
        if (r17 == null) goto L20;
        r17.addImageReceiver(r4, r28, r32, r33, r31);
        r20 = true;
    L20:
        if (r20 == true) goto L23;
        if (r16 == null) goto L23;
        r4 = r30;
        String r6 = r32;
        r16.addImageReceiver(r4, r28, r6, r33, r31);
        boolean r310 = true;
        boolean r183 = r182;
    L25:
        if (r310 == true) goto L328;
        String r311 = r34.path;
        if (r311 != null) goto L29;
        if (r26 != 0) goto L89;
        if (r35 == false) goto L89;
        if ((r36 instanceof MessageObject) == false) goto L53;
        MessageObject r312 = (MessageObject) r36;
        TLRPC.Document r122 = r312.getDocument();
        String r15 = r312.messageOwner.attachPath;
        String r22 = "athumb";
        r12 = r122;
        File r23 = FileLoader.getInstance(r37).getPathToMessage(r312.messageOwner);
        int r72 = r312.getMediaType();
        boolean r313 = r183;
    L61:
        if (r12 == null) goto L87;
        if (r39 == false) goto L65;
        File r11 = new File(FileLoader.getDirectory(4), "q_" + r12.dc_id + "_" + r12.id + ".jpg");
        if (r11.exists() == false) goto L65;
        int r1 = 1;
    L68:
        if (TextUtils.isEmpty(r15) == true) goto L71;
        File r2 = new File(r15);
        if (r2.exists() == false) goto L71;
    L72:
        if (r2 != null) goto L74;
        r2 = r23;
    L74:
        if (r11 != null) goto L86;
        String r13 = FileLoader.getAttachFileName(r12);
        ThumbGenerateInfo r5 = this.waitingForQualityThumb.get(r13);
        if (r5 != null) goto L79;
        r5 = new ThumbGenerateInfo(null);
        ThumbGenerateInfo.m4195$$Nest$fputparentDocument(r5, r12);
        ThumbGenerateInfo.m4194$$Nest$fputfilter(r5, r6);
        ThumbGenerateInfo.m4193$$Nest$fputbig(r5, r313);
        this.waitingForQualityThumb.put(r13, r5);
    L79:
        if (ThumbGenerateInfo.m4190$$Nest$fgetimageReceiverArray(r5).contains(r4) == true) goto L81;
        ThumbGenerateInfo.m4190$$Nest$fgetimageReceiverArray(r5).add(r4);
        ThumbGenerateInfo.m4191$$Nest$fgetimageReceiverGuidsArray(r5).add(Integer.valueOf(r31));
    L81:
        this.waitingForQualityThumbByTag.put(r29, r13);
        if (r2.exists() == false) goto L329;
        if (r40 == false) goto L330;
        generateThumb(r72, r2, r5);
        return;
    L330:
        return;
    L329:
        return;
    L86:
        int r24 = r26;
        File r123 = r11;
        int r314 = 1;
    L47:
        int r52 = 2;
    L90:
        if (r24 == r52) goto L331;
        boolean r73 = r34.isEncrypted();
        int r9 = r314;
        CacheImage r315 = new CacheImage(this, null);
        if (r4.getFileLoadingPriority() != 0) goto L94;
        int r112 = r183;
    L95:
        r315.priority = r112;
        if (r35 == false) goto L98;
    L126:
        if (r123 != null) goto L269;
        TLRPC.PhotoSize r113 = r34.photoSize;
        long r382 = 0;
        if ((r113 instanceof TLRPC.TL_photoStrippedSize) == false) goto L130;
    L131:
        String r114 = r27;
        int r74 = r42;
        int r292 = r1;
        String r44 = AUTOPLAY_FILTER;
        String r82 = AUTOPLAY_FILTER_NONLOOP;
        r1 = r292;
        File r53 = r123;
        int r25 = 1;
    L173:
        long r124 = 0;
    L257:
        if (hasAutoplayFilter(r6) == false) goto L259;
    L260:
        r315.imageType = 2;
        r315.size = r124;
        r315.isPFrame = isPFrame(r6);
        if (r44.equals(r6) == false) goto L263;
    L268:
        int r45 = r33;
        r123 = r53;
        r25 = 1;
    L270:
        r315.type = r45;
        r315.key = r28;
        r315.cacheType = r74;
        r315.filter = r6;
        r315.imageLocation = r34;
        r315.ext = r41;
        r315.currentAccount = r37;
        r315.parentObject = r36;
        int r83 = r34.imageType;
        if (r83 == 0) goto L274;
        r315.imageType = r83;
    L274:
        if (r74 != 2) goto L277;
        File r152 = FileLoader.getInternalCacheDir();
        int r293 = r1;
        StringBuilder r14 = new StringBuilder();
        r14.append(r114);
        int r352 = r25;
        r14.append(".enc.key");
        r315.encryptionKeyPath = new File(r152, r14.toString());
    L276:
        int r19 = r74;
        String r210 = r22;
        r315.addImageReceiver(r30, r28, r6, r45, r31);
        if (r352 != 0) goto L322;
        if (r293 != 0) goto L322;
        if (r123.exists() == true) goto L322;
        r315.url = r114;
        this.imageLoadingByUrl.put(r114, r315);
        if (r315.isPFrame == false) goto L287;
        this.imageLoadingByUrlPframe.put(r114, r315);
    L287:
        String r316 = r34.path;
        if (r316 == null) goto L295;
        String r110 = Utilities.MD5(r316);
        r315.tempFilePath = new File(FileLoader.getDirectory(4), r110 + "_temp.jpg");
        r315.finalFilePath = r123;
        if (r34.path.startsWith(r210) == false) goto L293;
        ArtworkLoadTask r111 = new ArtworkLoadTask(this, r315);
        r315.artworkTask = r111;
        this.artworkTasks.add(r111);
        runArtworkTasks(r183);
        return;
    L293:
        HttpImageTask r115 = new HttpImageTask(this, r315, r43);
        r315.httpTask = r115;
        this.httpTasks.add(r115);
        runHttpTasks(r183);
        return;
    L295:
        int r84 = r183;
        if (r26 == 0) goto L298;
        int r54 = 3;
    L300:
        if (r34.location == null) goto L310;
        if (r19 == 0) goto L303;
    L307:
        int r62 = r19;
    L308:
        FileLoader.getInstance(r37).loadFile(r34, r36, r41, r54, r62);
    L319:
        if (r30.isForceLoding() == false) goto L332;
        this.forceLoadingImages.put(r315.key, Integer.valueOf(r84));
        return;
    L332:
        return;
    L303:
        if (r43 > r382) goto L305;
    L306:
        r62 = 1;
        goto L308
    L305:
        if (r34.key == null) goto L307;
    L310:
        if (r34.document == null) goto L313;
        FileLoader.getInstance(r37).loadFile(r34.document, r36, r54, r19);
        goto L319
    L313:
        if (r34.secureDocument == null) goto L316;
        FileLoader.getInstance(r37).loadFile(r34.secureDocument, r54);
        goto L319
    L316:
        if (r34.webFile == null) goto L319;
        FileLoader.getInstance(r37).loadFile(r34.webFile, r54, r19);
        goto L319
    L298:
        r54 = r30.getFileLoadingPriority();
    L322:
        r315.finalFilePath = r123;
        r315.imageLocation = r34;
        r315.cacheTask = new CacheOutTask(this, r315);
        this.imageLoadingByKeys.put(r28, r315);
        this.imageLoadingKeys.add(cutFilter(r28));
        if (r26 == 0) goto L326;
        this.cacheThumbOutQueue.postRunnable(r315.cacheTask);
        return;
    L326:
        r315.runningTask = this.cacheOutQueue.postRunnable(r315.cacheTask, r315.priority);
        return;
    L277:
        r293 = r1;
        r352 = r25;
        goto L276
    L263:
        if (r82.equals(r6) == true) goto L268;
        if (isAnimatedAvatar(r6) == true) goto L268;
    L267:
        r45 = r33;
        r123 = r53;
        goto L270
    L259:
        if (isAnimatedAvatar(r6) == false) goto L267;
    L130:
        if ((r113 instanceof TLRPC.TL_photoPathSize) == true) goto L131;
        SecureDocument r116 = r34.secureDocument;
        if (r116 == null) goto L139;
        r315.secureDocument = r116;
        if (r116.secureFile.dc_id != Integer.MIN_VALUE) goto L137;
        int r75 = 1;
    L138:
        r114 = r27;
        File r125 = new File(FileLoader.getDirectory(4), r114);
        r44 = AUTOPLAY_FILTER;
        r25 = r75;
        r53 = r125;
        r82 = AUTOPLAY_FILTER_NONLOOP;
        r124 = 0;
        r74 = r42;
        goto L257
    L137:
        r75 = r183;
        goto L138
    L139:
        r114 = r27;
        int r294 = r1;
        if (AUTOPLAY_FILTER.equals(r6) == false) goto L142;
    L187:
        r74 = r42;
        String r21 = "_";
    L188:
        TLRPC.Document r85 = r34.document;
        if (r85 != null) goto L191;
        String r55 = r21;
        if (r34.webFile == null) goto L235;
        r1 = r294;
        r6 = r32;
        r25 = r9;
        r53 = new File(FileLoader.getDirectory(3), r114);
        r82 = AUTOPLAY_FILTER_NONLOOP;
        r44 = AUTOPLAY_FILTER;
        goto L173
    L235:
        if (r74 != 1) goto L238;
        File r46 = new File(FileLoader.getDirectory(4), r114);
    L237:
        r6 = r32;
        File r126 = r46;
        if (isAnimatedAvatar(r6) == true) goto L253;
        r44 = AUTOPLAY_FILTER;
        if (r44.equals(r6) == true) goto L246;
        r82 = AUTOPLAY_FILTER_NONLOOP;
        if (r82.equals(r6) == true) goto L248;
    L252:
        r1 = r294;
        r25 = r9;
        r53 = r126;
    L248:
        if (r34.location == null) goto L252;
        if (r126.exists() == true) goto L252;
    L254:
        r126 = new File(FileLoader.getDirectory(4), r34.location.volume_id + r55 + r34.location.local_id + ".temp");
        goto L252
    L246:
        r82 = AUTOPLAY_FILTER_NONLOOP;
        goto L248
    L253:
        r82 = AUTOPLAY_FILTER_NONLOOP;
        r44 = AUTOPLAY_FILTER;
        goto L254
    L238:
        r46 = new File(FileLoader.getDirectory(r183), r114);
        goto L237
    L191:
        if ((r85 instanceof TLRPC.TL_documentEncrypted) == false) goto L194;
        File r127 = new File(FileLoader.getDirectory(4), r114);
    L198:
        if (isAnimatedAvatar(r6) == true) goto L206;
        if (AUTOPLAY_FILTER.equals(r6) == true) goto L206;
        if (AUTOPLAY_FILTER_NONLOOP.equals(r6) == true) goto L206;
    L204:
        String r242 = AUTOPLAY_FILTER;
        String r232 = AUTOPLAY_FILTER_NONLOOP;
    L209:
        if ((r85 instanceof DocumentObject.ThemeDocument) == false) goto L218;
        if (((DocumentObject.ThemeDocument) r85).wallpaper != null) goto L214;
        int r117 = 1;
    L215:
        r315.imageType = 5;
        int r47 = r117;
    L230:
        r53 = r127;
        r124 = r85.size;
        r1 = r294;
        r6 = r32;
        r25 = r47;
        r82 = r232;
        r44 = r242;
        goto L257
    L214:
        r117 = r9;
        goto L215
    L218:
        if ("application/x-tgsdice".equals(r34.document.mime_type) == false) goto L221;
        r47 = 1;
        r315.imageType = 1;
        goto L230
    L221:
        if ("application/x-tgsticker".equals(r85.mime_type) == false) goto L224;
        r315.imageType = 1;
    L229:
        r47 = r9;
        goto L230
    L224:
        if ("application/x-tgwallpattern".equals(r85.mime_type) == false) goto L227;
        r315.imageType = 3;
        goto L229
    L227:
        if (FileLoader.getDocumentFileName(r34.document).endsWith(".svg") == false) goto L229;
        r315.imageType = 3;
    L206:
        if (r127.exists() == true) goto L204;
        File r132 = FileLoader.getDirectory(4);
        r232 = AUTOPLAY_FILTER_NONLOOP;
        StringBuilder r153 = new StringBuilder();
        r242 = AUTOPLAY_FILTER;
        r153.append(r85.dc_id);
        r153.append(r21);
        r153.append(r85.id);
        r153.append(".temp");
        r127 = new File(r132, r153.toString());
        goto L209
    L194:
        if (MessageObject.isVideoDocument(r85) == false) goto L196;
        r127 = new File(FileLoader.getDirectory(2), r114);
        goto L198
    L196:
        r127 = new File(FileLoader.getDirectory(3), r114);
        goto L198
    L142:
        if (AUTOPLAY_FILTER_NONLOOP.equals(r6) == true) goto L187;
        if (isAnimatedAvatar(r6) == true) goto L187;
        if (r42 != 0) goto L154;
        r21 = "_";
        if (r43 <= 0) goto L154;
        if (r34.path != null) goto L154;
        if (r73 == true) goto L154;
        r74 = r42;
    L154:
        File r128 = new File(FileLoader.getDirectory(4), r114);
        if (r128.exists() == false) goto L157;
        r74 = r42;
        int r402 = r9;
        int r86 = 1;
    L162:
        TLRPC.Document r92 = r34.document;
        if (r92 != null) goto L165;
    L186:
        r25 = r402;
    L172:
        r44 = AUTOPLAY_FILTER;
        r1 = r86;
        r53 = r128;
        r82 = AUTOPLAY_FILTER_NONLOOP;
        goto L173
    L165:
        if ((r92 instanceof DocumentObject.ThemeDocument) == false) goto L175;
        if (((DocumentObject.ThemeDocument) r92).wallpaper != null) goto L170;
        int r118 = 1;
    L171:
        r315.imageType = 5;
        r25 = r118;
        goto L172
    L170:
        r118 = r402;
        goto L171
    L175:
        if ("application/x-tgsdice".equals(r92.mime_type) == false) goto L178;
        r315.imageType = 1;
        r25 = 1;
        goto L172
    L178:
        if ("application/x-tgsticker".equals(r34.document.mime_type) == false) goto L181;
        r315.imageType = 1;
        goto L186
    L181:
        if ("application/x-tgwallpattern".equals(r34.document.mime_type) == false) goto L184;
        r315.imageType = 3;
        goto L186
    L184:
        if (FileLoader.getDocumentFileName(r34.document).endsWith(".svg") == false) goto L186;
        r315.imageType = 3;
        goto L186
    L157:
        r74 = r42;
        if (r74 != 2) goto L161;
        r402 = r9;
        r128 = new File(FileLoader.getDirectory(4), r114 + ".enc");
    L160:
        r86 = r294;
        goto L162
    L161:
        r402 = r9;
        goto L160
    L269:
        r114 = r27;
        r74 = r42;
        r382 = 0;
        r45 = r33;
        r25 = r9;
        goto L270
    L98:
        if (r34.imageType != r52) goto L100;
    L125:
        r315.imageType = r52;
        goto L126
    L100:
        if (MessageObject.isGifDocument(r34.webFile) == true) goto L125;
        if (MessageObject.isGifDocument(r34.document) == true) goto L125;
        if (MessageObject.isRoundVideoDocument(r34.document) == true) goto L125;
        if (MessageObject.isVideoSticker(r34.document) == true) goto L125;
        String r119 = r34.path;
        if (r119 == null) goto L126;
        if (r119.startsWith("vthumb") == true) goto L126;
        if (r119.startsWith("thumb") == true) goto L126;
        String r1110 = getHttpUrlExtension(r119, "jpg");
        if (r1110.equalsIgnoreCase("webm") == false) goto L117;
    L124:
        r315.imageType = r52;
        goto L126
    L117:
        if (r1110.equalsIgnoreCase("mp4") == true) goto L124;
        if (r1110.equalsIgnoreCase("gif") == true) goto L124;
        if ("tgs".equals(r41) == false) goto L126;
        r315.imageType = 1;
        goto L126
    L94:
        r112 = 1;
        goto L95
    L331:
        return;
    L71:
        r2 = null;
    L65:
        r1 = r183;
        r11 = null;
        goto L68
    L87:
        r24 = r26;
        r1 = r183;
        r314 = 1;
    L88:
        r52 = 2;
        r123 = null;
        goto L90
    L53:
        r22 = "athumb";
        if (r12 == null) goto L60;
        File r317 = FileLoader.getInstance(r37).getPathToAttach(r12, true);
        if (MessageObject.isVideoDocument(r12) == false) goto L58;
        r72 = 2;
    L59:
        r23 = r317;
        r313 = true;
        r15 = null;
        goto L61
    L58:
        r72 = 3;
        goto L59
    L60:
        r313 = r183;
        r72 = r313 ? 1 : 0;
        r12 = null;
        r15 = null;
        r23 = null;
    L89:
        r22 = "athumb";
        r24 = r26;
        r1 = r183;
        r314 = r1;
        goto L88
    L29:
        if (r311.startsWith("http") == false) goto L31;
    L45:
        r314 = r183;
        r123 = null;
    L46:
        r24 = r26;
        r22 = "athumb";
        r1 = r183;
        goto L47
    L31:
        if (r311.startsWith("athumb") == true) goto L45;
        if (r311.startsWith("thumb://") == false) goto L40;
        int r93 = r311.indexOf(":", 8);
        if (r93 < 0) goto L37;
        r123 = new File(r311.substring(r93 + 1));
    L38:
        r314 = 1;
    L37:
        r123 = null;
        goto L38
    L40:
        if (r311.startsWith("vthumb://") == false) goto L44;
        int r94 = r311.indexOf(":", 9);
        if (r94 < 0) goto L37;
        r123 = new File(r311.substring(r94 + 1));
        goto L38
    L44:
        r123 = new File(r311);
        goto L38
    L328:
        return;
    L23:
        r4 = r30;
        r6 = r32;
        r310 = r20;
        r183 = r182;
        goto L25
    L10:
        if (r3 != r7) goto L14;
        r16 = r7;
        r17 = r8;
        r18 = false;
        r18 = false;
        if (r8 != null) goto L8;
        r3.replaceImageReceiver(r4, r28, r32, r33, r31);
        goto L8
    L14:
        r16 = r7;
        r17 = r8;
        boolean r184 = false;
        r3.removeImageReceiver(r4);
    L16:
        r20 = r184;
        r182 = r184;
        goto L17
    L15:
        r16 = r7;
        r17 = r8;
        r184 = false;
        goto L16
    L24:
        r6 = r32;
        r183 = 0;
        r310 = false;
        goto L25
    }

    private /* synthetic */ void lambda$fileDidFailedLoad$12(String r1) {
        CacheImage r0 = this.imageLoadingByUrl.get(r1);
        if (r0 == null) goto L6;
        r0.setImageAndClear(null, null);
        return;
    }

    private /* synthetic */ void lambda$fileDidLoaded$11(String r11, int r12, File r13) {
        ThumbGenerateInfo r0 = this.waitingForQualityThumb.get(r11);
        if (r0 != null) goto L5;
    L7:
        CacheImage r122 = this.imageLoadingByUrl.get(r11);
        if (r122 == null) goto L24;
        this.imageLoadingByUrl.remove(r11);
        this.imageLoadingByUrlPframe.remove(r11);
        ArrayList r112 = new ArrayList();
        int r02 = 0;
        int r1 = 0;
    L12:
        if (r1 >= r122.imageReceiverArray.size()) goto L18;
        String r5 = r122.keys.get(r1);
        String r6 = r122.filters.get(r1);
        int r7 = r122.types.get(r1).intValue();
        ImageReceiver r4 = r122.imageReceiverArray.get(r1);
        int r8 = r122.imageReceiverGuidsArray.get(r1).intValue();
        CacheImage r2 = this.imageLoadingByKeys.get(r5);
        if (r2 != null) goto L16;
        r2 = new CacheImage(this, null);
        r2.priority = r122.priority;
        r2.secureDocument = r122.secureDocument;
        r2.currentAccount = r122.currentAccount;
        r2.finalFilePath = r13;
        r2.parentObject = r122.parentObject;
        r2.isPFrame = r122.isPFrame;
        r2.key = r5;
        r2.cacheType = r122.cacheType;
        r2.imageLocation = r122.imageLocation;
        r2.type = r7;
        r2.ext = r122.ext;
        r2.encryptionKeyPath = r122.encryptionKeyPath;
        r2.cacheTask = new CacheOutTask(this, r2);
        r2.filter = r6;
        r2.imageType = r122.imageType;
        this.imageLoadingByKeys.put(r5, r2);
        this.imageLoadingKeys.add(cutFilter(r5));
        r112.add(r2.cacheTask);
    L16:
        r2.addImageReceiver(r4, r5, r6, r7, r8);
        r1 = r1 + 1;
    L18:
        if (r02 >= r112.size()) goto L31;
        CacheOutTask r123 = (CacheOutTask) r112.get(r02);
        if (CacheOutTask.m4176$$Nest$fgetcacheImage(r123).type != 1) goto L22;
        this.cacheThumbOutQueue.postRunnable(r123);
    L23:
        r02 = r02 + 1;
        goto L18
    L22:
        this.cacheOutQueue.postRunnable(r123, CacheOutTask.m4176$$Nest$fgetcacheImage(r123).priority);
        goto L23
    L31:
        return;
    L24:
        return;
    L5:
        if (ThumbGenerateInfo.m4192$$Nest$fgetparentDocument(r0) == null) goto L7;
        generateThumb(r12, r13, r0);
        this.waitingForQualityThumb.remove(r11);
        goto L7
    }

    private /* synthetic */ void lambda$httpFileLoadError$9(String r6) {
        CacheImage r62 = this.imageLoadingByUrl.get(r6);
        if (r62 != null) goto L5;
        return;
    L5:
        HttpImageTask r0 = r62.httpTask;
        if (r0 == null) goto L8;
        HttpImageTask r1 = new HttpImageTask(this, HttpImageTask.m4186$$Nest$fgetcacheImage(r0), HttpImageTask.m4187$$Nest$fgetimageSize(r0));
        r62.httpTask = r1;
        this.httpTasks.add(r1);
    L8:
        runHttpTasks(false);
    }

    private /* synthetic */ void lambda$preloadArtwork$8(String r9) {
        String r0 = getHttpUrlExtension(r9, "jpg");
        String r1 = Utilities.MD5(r9) + "." + r0;
        File r2 = new File(FileLoader.getDirectory(4), r1);
        if (r2.exists() == false) goto L5;
        return;
    L5:
        ImageLocation r4 = ImageLocation.getForPath(r9);
        CacheImage r5 = new CacheImage(this, null);
        r5.type = 1;
        r5.key = Utilities.MD5(r9);
        r5.filter = null;
        r5.imageLocation = r4;
        r5.ext = r0;
        r5.parentObject = null;
        int r92 = r4.imageType;
        if (r92 == 0) goto L8;
        r5.imageType = r92;
    L8:
        r5.url = r1;
        this.imageLoadingByUrl.put(r1, r5);
        String r93 = Utilities.MD5(r4.path);
        r5.tempFilePath = new File(FileLoader.getDirectory(4), r93 + "_temp.jpg");
        r5.finalFilePath = r2;
        ArtworkLoadTask r94 = new ArtworkLoadTask(this, r5);
        r5.artworkTask = r94;
        this.artworkTasks.add(r94);
        runArtworkTasks(false);
    }

    private /* synthetic */ void lambda$replaceImageInCache$5(String r1, String r2, ImageLocation r3) {
        replaceImageInCacheInternal(r1, r2, r3);
    }

    private /* synthetic */ void lambda$runHttpFileLoadTasks$13(HttpFileTask r2) {
        this.httpFileLoadTasks.add(r2);
        runHttpFileLoadTasks(null, 0);
    }

    private /* synthetic */ void lambda$runHttpFileLoadTasks$14(HttpFileTask r9, int r10) {
        if (r9 == null) goto L6;
        this.currentHttpFileLoadTasksCount--;
    L6:
        if (r9 == null) goto L19;
        if (r10 == 1) goto L9;
        ImageLoader r3 = this;
        if (r10 != 2) goto L21;
        r3.httpFileLoadTasksByKeys.remove(HttpFileTask.m4182$$Nest$fgeturl(r9));
        File r8 = new File(FileLoader.getDirectory(4), Utilities.MD5(HttpFileTask.m4182$$Nest$fgeturl(r9)) + "." + HttpFileTask.m4180$$Nest$fgetext(r9));
        if (HttpFileTask.m4181$$Nest$fgettempFile(r9).renameTo(r8) == true) goto L18;
        r8 = HttpFileTask.m4181$$Nest$fgettempFile(r9);
    L18:
        NotificationCenter.getInstance(HttpFileTask.m4179$$Nest$fgetcurrentAccount(r9)).postNotificationName(NotificationCenter.httpFileDidLoad, new Object[]{HttpFileTask.m4182$$Nest$fgeturl(r9), r8.toString()});
    L21:
        if (r3.currentHttpFileLoadTasksCount >= 2) goto L25;
        if (r3.httpFileLoadTasks.isEmpty() == true) goto L29;
        r3.httpFileLoadTasks.poll().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});
        r3.currentHttpFileLoadTasksCount++;
        goto L21
    L29:
        return;
    L25:
        return;
    L9:
        if (HttpFileTask.m4178$$Nest$fgetcanRetry(r9) == false) goto L11;
        r3 = this;
        ImageLoader$$ExternalSyntheticLambda5 r82 = new ImageLoader$$ExternalSyntheticLambda5(r3, new HttpFileTask(r3, HttpFileTask.m4182$$Nest$fgeturl(r9), HttpFileTask.m4181$$Nest$fgettempFile(r9), HttpFileTask.m4180$$Nest$fgetext(r9), HttpFileTask.m4179$$Nest$fgetcurrentAccount(r9)));
        r3.retryHttpsTasks.put(HttpFileTask.m4182$$Nest$fgeturl(r9), r82);
        AndroidUtilities.runOnUIThread(r82, 1000);
        goto L21
    L11:
        r3 = this;
        r3.httpFileLoadTasksByKeys.remove(HttpFileTask.m4182$$Nest$fgeturl(r9));
        NotificationCenter.getInstance(HttpFileTask.m4179$$Nest$fgetcurrentAccount(r9)).postNotificationName(NotificationCenter.httpFileDidFailedLoad, new Object[]{HttpFileTask.m4182$$Nest$fgeturl(r9), 0});
        goto L21
    L19:
        r3 = this;
        goto L21
    }

    public static Bitmap loadBitmap(String r18, Uri r19, float r20, float r21, boolean r22) {
        BitmapFactory.Options r2 = new BitmapFactory.Options();
        r2.inJustDecodeBounds = true;
        if (r18 != null) goto L19;
        if (r19 == null) goto L19;
        if (r19.getScheme() == null) goto L19;
        if (r19.getScheme().contains("file") == false) goto L12;
        String r0 = r19.getPath();
    L10:
        String r4 = r0;
    L20:
        Bitmap r5 = null;
        if (r4 == null) goto L23;
        BitmapFactory.decodeFile(r4, r2);
    L29:
        InputStream r6 = null;
    L30:
        float r02 = r2.outWidth / r20;
        float r7 = r2.outHeight / r21;
        if (r22 == false) goto L33;
        float r03 = Math.max(r02, r7);
    L35:
        if (r03 >= 1.0f) goto L37;
        r03 = 1.0f;
    L37:
        r2.inJustDecodeBounds = false;
        int r8 = (int) r03;
        r2.inSampleSize = r8;
        if ((r8 % 2) == 0) goto L137;
        int r82 = 1;
    L40:
        int r10 = r82 * 2;
        if (r10 >= r2.inSampleSize) goto L43;
        r82 = r10;
        goto L40
    L43:
        r2.inSampleSize = r82;
    L137:
        Pair<Integer, Integer> r83 = AndroidUtilities.getImageOrientation(r4);     // Catch: Throwable -> L134
        if (((Integer) r83.first).intValue() != 0) goto L56;
        if (((Integer) r83.second).intValue() != 0) goto L56;
        InputStream r102 = ApplicationLoader.applicationContext.getContentResolver().openInputStream(r19);     // Catch: Throwable -> L52
        r83 = AndroidUtilities.getImageOrientation(r102);     // Catch: Throwable -> L132
        if (r102 == null) goto L56;
    L51:
        r102.close();     // Catch: Throwable -> L134
    L53:
        if (r102 == null) goto L56;
    L52:
        r102 = null;
    L56:
        if (((Integer) r83.first).intValue() == 0) goto L58;
    L61:
        Matrix r103 = new Matrix();     // Catch: Throwable -> L134
        if (((Integer) r83.second).intValue() == 0) goto L74;
        float r12 = -1.0f;
        if (((Integer) r83.second).intValue() != 1) goto L67;
        float r3 = -1.0f;
    L69:
        if (((Integer) r83.second).intValue() == 2) goto L72;
        r12 = 1.0f;
    L72:
        r103.postScale(r3, r12);     // Catch: Throwable -> L133
        goto L74
    L67:
        r3 = 1.0f;
    L74:
        if (((Integer) r83.first).intValue() == 0) goto L76;
        r103.postRotate(((Integer) r83.first).intValue());     // Catch: Throwable -> L133
    L76:
        float r04 = r03 / r2.inSampleSize;
        if (r04 <= 1.0f) goto L81;
        if (r103 != null) goto L80;
        r103 = new Matrix();
    L80:
        float r72 = 1.0f / r04;
        r103.postScale(r72, r72);
    L81:
        Matrix r16 = r103;
        if (r4 != null) goto L141;
        if (r19 == null) goto L169;
        Bitmap r11 = BitmapFactory.decodeStream(r6, null, r2);     // Catch: Throwable -> L121
        if (r11 != null) goto L157;
    L116:
        r5 = r11;
    L135:
        r6.close();     // Catch: Throwable -> L119
        return r5;
    L119:
        th = move-exception;
        FileLog.e(th);
        return r5;
    L157:
        Bitmap r05 = Bitmaps.createBitmap(r11, 0, 0, r11.getWidth(), r11.getHeight(), r16, true);     // Catch: Throwable -> L114
        if (r05 == r11) goto L116;
        r11.recycle();     // Catch: Throwable -> L114
        r5 = r05;
    L114:
        th = th;
        r5 = r11;
    L147:
        FileLog.e(th);     // Catch: Throwable -> L124
    L124:
        th = move-exception;
        r6.close();     // Catch: Throwable -> L128
        throw th;
    L128:
        th = move-exception;
        FileLog.e(th);
        throw th;
    L121:
        th = th;
        goto L147
    L169:
        return null;
    L141:
        Bitmap r112 = BitmapFactory.decodeFile(r4, r2);     // Catch: Throwable -> L92
        if (r112 == null) goto L167;
        Bitmap r06 = Bitmaps.createBitmap(r112, 0, 0, r112.getWidth(), r112.getHeight(), r16, true);     // Catch: Throwable -> L89
        if (r06 == r112) goto L167;
        r112.recycle();     // Catch: Throwable -> L89
        return r06;
    L89:
        th = th;
        r5 = r112;
    L93:
        FileLog.e(th);
        getInstance().clearMemory();
        if (r5 == null) goto L159;
    L96:
        r112 = r5;
        if (r112 == null) goto L167;
        Bitmap r07 = Bitmaps.createBitmap(r112, 0, 0, r112.getWidth(), r112.getHeight(), r16, true);     // Catch: Throwable -> L104
        if (r07 == r112) goto L167;
        r112.recycle();     // Catch: Throwable -> L104
        r112 = r07;
    L104:
        th = th;
        r5 = r112;
    L106:
        FileLog.e(th);
        return r5;
    L159:
        r5 = BitmapFactory.decodeFile(r4, r2);     // Catch: Throwable -> L97
    L97:
        th = th;
    L167:
        return r112;
    L92:
        th = th;
        goto L93
    L58:
        if (((Integer) r83.second).intValue() != 0) goto L61;
    L60:
        r103 = null;
        goto L76
    L33:
        r03 = Math.min(r02, r7);
        goto L35
    L23:
        if (r19 == null) goto L29;
        InputStream r08 = ApplicationLoader.applicationContext.getContentResolver().openInputStream(r19);     // Catch: Throwable -> L26
        BitmapFactory.decodeStream(r08, null, r2);     // Catch: Throwable -> L26
        r08.close();     // Catch: Throwable -> L26
        r6 = ApplicationLoader.applicationContext.getContentResolver().openInputStream(r19);     // Catch: Throwable -> L26
    L26:
        th = move-exception;
        FileLog.e(th);
        return null;
    L12:
        if (Build.VERSION.SDK_INT >= 30) goto L14;
    L161:
        r0 = AndroidUtilities.getPath(r19);     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        FileLog.e(th);
        goto L19
    L14:
        if ("content".equals(r19.getScheme()) == false) goto L161;
    L19:
        r4 = r18;
        goto L20
    }

    @TargetApi(26)
    private static void moveDirectory(File r1, File r2) {
        if (r1.exists() == true) goto L5;
        return;
    L5:
        if (r2.exists() == false) goto L7;
    L25:
        Stream<j$.nio.file.Path> r12 = Files.list(FileRetargetClass.toPath(r1));     // Catch: Exception -> L20
        r12.forEach(new ImageLoader$$ExternalSyntheticLambda11(r2));     // Catch: Throwable -> L13
        r12.close();     // Catch: Exception -> L20
        return;
    L13:
        th = move-exception;
        if (r12 != null) goto L26;
    L19:
        throw th;     // Catch: Exception -> L20
    L26:
        r12.close();     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L20
    L20:
        e = move-exception;
        FileLog.e(e);
        return;
    L7:
        if (r2.mkdir() == true) goto L25;
    }

    private void performReplace(String r7, String r8) {
        LruCache<BitmapDrawable> r0 = this.memCache;
        BitmapDrawable r1 = r0.get(r7);
        if (r1 != null) goto L5;
        r0 = this.smallImagesMemCache;
        r1 = r0.get(r7);
    L5:
        this.replacedBitmaps.put(r7, r8);
        if (r1 == null) goto L19;
        BitmapDrawable r2 = r0.get(r8);
        if (r2 != null) goto L10;
    L18:
        this.ignoreRemoval = r7;
        r0.remove(r7);
        r0.put(r8, r1);
        this.ignoreRemoval = null;
        goto L19
    L10:
        if (r2.getBitmap() == null) goto L18;
        if (r1.getBitmap() == null) goto L18;
        Bitmap r22 = r2.getBitmap();
        Bitmap r3 = r1.getBitmap();
        if (r22.getWidth() <= r3.getWidth()) goto L16;
    L17:
        r0.remove(r7);
        goto L19
    L16:
        if (r22.getHeight() <= r3.getHeight()) goto L18;
    L19:
        Integer r02 = this.bitmapUseCounts.get(r7);
        if (r02 == null) goto L23;
        this.bitmapUseCounts.put(r8, r02);
        this.bitmapUseCounts.remove(r7);
        return;
    }

    private void removeFromWaitingForThumb(int r4, ImageReceiver r5) {
        String r0 = this.waitingForQualityThumbByTag.get(r4);
        if (r0 == null) goto L14;
        ThumbGenerateInfo r1 = this.waitingForQualityThumb.get(r0);
        if (r1 == null) goto L12;
        int r52 = ThumbGenerateInfo.m4190$$Nest$fgetimageReceiverArray(r1).indexOf(r5);
        if (r52 < 0) goto L10;
        ThumbGenerateInfo.m4190$$Nest$fgetimageReceiverArray(r1).remove(r52);
        ThumbGenerateInfo.m4191$$Nest$fgetimageReceiverGuidsArray(r1).remove(r52);
    L10:
        if (ThumbGenerateInfo.m4190$$Nest$fgetimageReceiverArray(r1).isEmpty() == false) goto L12;
        this.waitingForQualityThumb.remove(r0);
    L12:
        this.waitingForQualityThumbByTag.remove(r4);
        return;
    }

    private void replaceImageInCacheInternal(String r9, String r10, ImageLocation r11) {
        int r1 = 0;
    L4:
        if (r1 >= 2) goto L15;
        if (r1 != 0) goto L7;
        ArrayList<String> r2 = this.memCache.getFilterKeys(r9);
    L8:
        if (r2 == null) goto L13;
        int r3 = 0;
    L11:
        if (r3 >= r2.size()) goto L14;
        String r4 = r2.get(r3);
        String r5 = r9 + "@" + r4;
        String r42 = r10 + "@" + r4;
        performReplace(r5, r42);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didReplacedPhotoInMemCache, new Object[]{r5, r42, r11});
        r3 = r3 + 1;
    L14:
        r1 = r1 + 1;
        goto L4
    L13:
        performReplace(r9, r10);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didReplacedPhotoInMemCache, new Object[]{r9, r10, r11});
        goto L14
    L7:
        r2 = this.smallImagesMemCache.getFilterKeys(r9);
        goto L8
    }

    private void runArtworkTasks(boolean r3) {
        if (r3 == false) goto L5;
        this.currentArtworkTasksCount--;
    L5:
        if (this.currentArtworkTasksCount >= 4) goto L11;
        if (this.artworkTasks.isEmpty() == true) goto L20;
        this.artworkTasks.poll().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});     // Catch: Throwable -> L10
        this.currentArtworkTasksCount++;
    L10:
        runArtworkTasks(false);
        goto L5
    L20:
        return;
    }

    private void runHttpFileLoadTasks(HttpFileTask r2, int r3) {
        AndroidUtilities.runOnUIThread(new ImageLoader$$ExternalSyntheticLambda1(this, r2, r3));
    }

    private void runHttpTasks(boolean r3) {
        if (r3 == false) goto L5;
        this.currentHttpTasksCount--;
    L5:
        if (this.currentHttpTasksCount >= 4) goto L11;
        if (this.httpTasks.isEmpty() == true) goto L19;
        HttpImageTask r32 = this.httpTasks.poll();
        if (r32 == null) goto L5;
        r32.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[]{null, null, null});
        this.currentHttpTasksCount++;
        goto L5
    L19:
        return;
    }

    public static void saveMessageThumbs(TLRPC.Message r15) {
        TLRPC.MessageMedia r0 = r15.media;
        if (r0 == null) goto L93;
        int r2 = 0;
        if ((r0 instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L14;
        TLRPC.TL_messageMediaPaidMedia r02 = (TLRPC.TL_messageMediaPaidMedia) r0;
    L9:
        if (r2 >= r02.extended_media.size()) goto L94;
        TLRPC.MessageExtendedMedia r1 = r02.extended_media.get(r2);
        if ((r1 instanceof TLRPC.TL_messageExtendedMedia) == false) goto L13;
        saveMessageThumbs(r15, ((TLRPC.TL_messageExtendedMedia) r1).media);
    L13:
        r2 = r2 + 1;
        goto L9
    L94:
        return;
    L14:
        TLRPC.PhotoSize r12 = findPhotoCachedSize(r15);
        if (r12 == null) goto L75;
        byte[] r03 = r12.bytes;
        if (r03 != null) goto L19;
        return;
    L19:
        if (r03.length == 0) goto L88;
        TLRPC.FileLocation r04 = r12.location;
        if (r04 != null) goto L23;
    L24:
        TLRPC.TL_fileLocationToBeDeprecated r05 = new TLRPC.TL_fileLocationToBeDeprecated();
        r12.location = r05;
        r05.volume_id = -2147483648L;
        r05.local_id = SharedConfig.getLastLocalId();
    L26:
        if (r12.h <= 50) goto L28;
    L30:
        boolean r3 = true;
        File r06 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r12, true);
        if (MessageObject.shouldEncryptPhotoOrVideo(UserConfig.selectedAccount, r15) == false) goto L33;
        r06 = new File(r06.getAbsolutePath() + ".enc");
    L35:
        if (r06.exists() == false) goto L37;
    L50:
        TLRPC.PhotoSize r07 = new TLRPC.TL_photoSize_layer127();
        r07.w = r12.w;
        r07.h = r12.h;
        r07.location = r12.location;
        r07.size = r12.size;
        r07.type = r12.type;
    L51:
        TLRPC.MessageMedia r13 = r15.media;
        if ((r13 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L60;
        int r14 = r13.photo.sizes.size();
    L54:
        if (r2 >= r14) goto L95;
        if ((r15.media.photo.sizes.get(r2) instanceof TLRPC.TL_photoCachedSize) == true) goto L57;
        r2 = r2 + 1;
        goto L54
    L57:
        r15.media.photo.sizes.set(r2, r07);
        return;
    L95:
        return;
    L60:
        if ((r13 instanceof TLRPC.TL_messageMediaDocument) == false) goto L68;
        int r16 = r13.document.thumbs.size();
    L62:
        if (r2 >= r16) goto L96;
        if ((r15.media.document.thumbs.get(r2) instanceof TLRPC.TL_photoCachedSize) == true) goto L65;
        r2 = r2 + 1;
        goto L62
    L65:
        r15.media.document.thumbs.set(r2, r07);
        return;
    L96:
        return;
    L68:
        if ((r13 instanceof TLRPC.TL_messageMediaWebPage) == false) goto L91;
        int r17 = r13.webpage.photo.sizes.size();
    L70:
        if (r2 >= r17) goto L97;
        if ((r15.media.webpage.photo.sizes.get(r2) instanceof TLRPC.TL_photoCachedSize) == true) goto L73;
        r2 = r2 + 1;
        goto L70
    L73:
        r15.media.webpage.photo.sizes.set(r2, r07);
        return;
    L97:
        return;
    L91:
        return;
    L37:
        if (r3 == true) goto L76;
    L47:
        RandomAccessFile r32 = new RandomAccessFile(r06, "rws");     // Catch: Exception -> L43
        r32.write(r12.bytes);     // Catch: Exception -> L43
        r32.close();     // Catch: Exception -> L43
    L43:
        e = move-exception;
        FileLog.e(e);
        goto L50
    L76:
        RandomAccessFile r5 = new RandomAccessFile(new File(FileLoader.getInternalCacheDir(), r06.getName() + ".key"), "rws");     // Catch: Exception -> L43
        long r6 = r5.length();     // Catch: Exception -> L43
        byte[] r9 = new byte[32];     // Catch: Exception -> L43
        byte[] r10 = new byte[16];     // Catch: Exception -> L43
        if (r6 > 0) goto L41;
    L45:
        Utilities.random.nextBytes(r9);     // Catch: Exception -> L43
        Utilities.random.nextBytes(r10);     // Catch: Exception -> L43
        r5.write(r9);     // Catch: Exception -> L43
        r5.write(r10);     // Catch: Exception -> L43
    L46:
        r5.close();     // Catch: Exception -> L43
        Utilities.aesCtrDecryptionByteArray(r12.bytes, r9, r10, 0, r8.length, 0);     // Catch: Exception -> L43
        goto L47
    L41:
        if ((r6 % 48) != 0) goto L45;
        r5.read(r9, 0, 32);     // Catch: Exception -> L43
        r5.read(r10, 0, 16);     // Catch: Exception -> L43
        goto L46
    L33:
        r3 = false;
        goto L35
    L28:
        if (r12.w > 50) goto L30;
        r07 = new TLRPC.TL_photoStrippedSize();
        r07.location = r12.location;
        r07.bytes = r12.bytes;
        r07.h = r12.h;
        r07.w = r12.w;
        goto L51
    L23:
        if ((r04 instanceof TLRPC.TL_fileLocationUnavailable) == false) goto L26;
    L88:
        return;
    L75:
        return;
    }

    public static void saveMessageThumbs(TLRPC.Message r14, TLRPC.MessageMedia r15) {
        if (r14 == null) goto L66;
        if (r15 == null) goto L82;
        TLRPC.PhotoSize r1 = findPhotoCachedSize(r15);
        if (r1 == null) goto L75;
        byte[] r0 = r1.bytes;
        if (r0 != null) goto L10;
        return;
    L10:
        if (r0.length == 0) goto L77;
        TLRPC.FileLocation r02 = r1.location;
        if (r02 != null) goto L14;
    L15:
        TLRPC.TL_fileLocationToBeDeprecated r03 = new TLRPC.TL_fileLocationToBeDeprecated();
        r1.location = r03;
        r03.volume_id = -2147483648L;
        r03.local_id = SharedConfig.getLastLocalId();
    L16:
        int r3 = 0;
        if (r1.h <= 50) goto L19;
    L21:
        boolean r2 = true;
        File r04 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r1, true);
        if (MessageObject.shouldEncryptPhotoOrVideo(UserConfig.selectedAccount, r14) == false) goto L24;
        r04 = new File(r04.getAbsolutePath() + ".enc");
    L26:
        if (r04.exists() == false) goto L28;
    L41:
        TLRPC.PhotoSize r142 = new TLRPC.TL_photoSize_layer127();
        r142.w = r1.w;
        r142.h = r1.h;
        r142.location = r1.location;
        r142.size = r1.size;
        r142.type = r1.type;
    L43:
        if ((r15 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L51;
        int r05 = r15.photo.sizes.size();
    L45:
        if (r3 >= r05) goto L83;
        if ((r15.photo.sizes.get(r3) instanceof TLRPC.TL_photoCachedSize) == true) goto L48;
        r3 = r3 + 1;
        goto L45
    L48:
        r15.photo.sizes.set(r3, r142);
        return;
    L83:
        return;
    L51:
        if ((r15 instanceof TLRPC.TL_messageMediaDocument) == false) goto L59;
        int r06 = r15.document.thumbs.size();
    L53:
        if (r3 >= r06) goto L84;
        if ((r15.document.thumbs.get(r3) instanceof TLRPC.TL_photoCachedSize) == true) goto L56;
        r3 = r3 + 1;
        goto L53
    L56:
        r15.document.thumbs.set(r3, r142);
        return;
    L84:
        return;
    L59:
        if ((r15 instanceof TLRPC.TL_messageMediaWebPage) == false) goto L80;
        int r07 = r15.webpage.photo.sizes.size();
    L61:
        if (r3 >= r07) goto L85;
        if ((r15.webpage.photo.sizes.get(r3) instanceof TLRPC.TL_photoCachedSize) == true) goto L64;
        r3 = r3 + 1;
        goto L61
    L64:
        r15.webpage.photo.sizes.set(r3, r142);
        return;
    L85:
        return;
    L80:
        return;
    L28:
        if (r2 == true) goto L67;
    L38:
        RandomAccessFile r22 = new RandomAccessFile(r04, "rws");     // Catch: Exception -> L34
        r22.write(r1.bytes);     // Catch: Exception -> L34
        r22.close();     // Catch: Exception -> L34
    L34:
        e = move-exception;
        FileLog.e(e);
        goto L41
    L67:
        RandomAccessFile r4 = new RandomAccessFile(new File(FileLoader.getInternalCacheDir(), r04.getName() + ".key"), "rws");     // Catch: Exception -> L34
        long r5 = r4.length();     // Catch: Exception -> L34
        byte[] r8 = new byte[32];     // Catch: Exception -> L34
        byte[] r9 = new byte[16];     // Catch: Exception -> L34
        if (r5 > 0) goto L32;
    L36:
        Utilities.random.nextBytes(r8);     // Catch: Exception -> L34
        Utilities.random.nextBytes(r9);     // Catch: Exception -> L34
        r4.write(r8);     // Catch: Exception -> L34
        r4.write(r9);     // Catch: Exception -> L34
    L37:
        r4.close();     // Catch: Exception -> L34
        Utilities.aesCtrDecryptionByteArray(r1.bytes, r8, r9, 0, r7.length, 0);     // Catch: Exception -> L34
        goto L38
    L32:
        if ((r5 % 48) != 0) goto L36;
        r4.read(r8, 0, 32);     // Catch: Exception -> L34
        r4.read(r9, 0, 16);     // Catch: Exception -> L34
        goto L37
    L24:
        r2 = false;
        goto L26
    L19:
        if (r1.w > 50) goto L21;
        r142 = new TLRPC.TL_photoStrippedSize();
        r142.location = r1.location;
        r142.bytes = r1.bytes;
        r142.h = r1.h;
        r142.w = r1.w;
        goto L43
    L14:
        if ((r02 instanceof TLRPC.TL_fileLocationUnavailable) == false) goto L16;
    L77:
        return;
    L75:
        return;
    L82:
        return;
    }

    public static void saveMessagesThumbs(ArrayList<TLRPC.Message> r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L12;
        int r0 = 0;
    L8:
        if (r0 >= r2.size()) goto L13;
        saveMessageThumbs(r2.get(r0));
        r0 = r0 + 1;
        goto L8
    L13:
        return;
    }

    public static TLRPC.PhotoSize scaleAndSaveImage(Bitmap r11, float r12, float r13, int r14, boolean r15) {
        return scaleAndSaveImage(null, r11, Bitmap.CompressFormat.JPEG, false, r12, r13, r14, r15, 0, 0, false);
    }

    public static TLRPC.PhotoSize scaleAndSaveImage(Bitmap r11, float r12, float r13, int r14, boolean r15, int r16, int r17) {
        return scaleAndSaveImage(null, r11, Bitmap.CompressFormat.JPEG, false, r12, r13, r14, r15, r16, r17, false);
    }

    public static TLRPC.PhotoSize scaleAndSaveImage(Bitmap r11, float r12, float r13, boolean r14, int r15, boolean r16, int r17, int r18) {
        return scaleAndSaveImage(null, r11, Bitmap.CompressFormat.JPEG, r14, r12, r13, r15, r16, r17, r18, false);
    }

    public static TLRPC.PhotoSize scaleAndSaveImage(Bitmap r11, Bitmap.CompressFormat r12, float r13, float r14, int r15, boolean r16, int r17, int r18) {
        return scaleAndSaveImage(null, r11, r12, false, r13, r14, r15, r16, r17, r18, false);
    }

    public static TLRPC.PhotoSize scaleAndSaveImage(TLRPC.PhotoSize r11, Bitmap r12, float r13, float r14, int r15, boolean r16, boolean r17) {
        return scaleAndSaveImage(r11, r12, Bitmap.CompressFormat.JPEG, false, r13, r14, r15, r16, 0, 0, r17);
    }

    public static TLRPC.PhotoSize scaleAndSaveImage(TLRPC.PhotoSize r17, Bitmap r18, Bitmap.CompressFormat r19, boolean r20, float r21, float r22, int r23, boolean r24, int r25, int r26, boolean r27) {
        if (r18 != null) goto L5;
        return null;
    L5:
        float r10 = r18.getWidth();
        float r11 = r18.getHeight();
        if (r10 != 0.0f) goto L8;
    L40:
        return null;
    L8:
        if (r11 == 0.0f) goto L40;
        float r3 = Math.max(r10 / r21, r11 / r22);
        if (r25 == 0) goto L30;
        if (r26 == 0) goto L30;
        float r0 = r25;
        if (r10 >= r0) goto L16;
    L17:
        if (r10 >= r0) goto L23;
        if (r11 <= r26) goto L23;
        float r02 = r10 / r0;
    L21:
        r3 = r02;
        boolean r03 = true;
    L29:
        boolean r15 = r03;
        float r12 = r3;
        int r8 = (int) (r10 / r12);
        int r9 = (int) (r11 / r12);
        if (r9 == 0) goto L40;
        if (r8 == 0) goto L40;
        return scaleAndSaveImageInternal(r17, r18, r19, r20, r8, r9, r10, r11, r12, r23, r24, r15, r27);
    L38:
        th = move-exception;
        FileLog.e(th);
        getInstance().clearMemory();
    L23:
        if (r10 <= r0) goto L27;
        float r32 = r26;
        if (r11 >= r32) goto L27;
        r02 = r11 / r32;
    L27:
        r02 = Math.max(r10 / r0, r11 / r26);
        goto L21
    L16:
        if (r11 < r26) goto L17;
    L30:
        r03 = false;
        goto L29
    }

    private static TLRPC.PhotoSize scaleAndSaveImageInternal(TLRPC.PhotoSize r2, Bitmap r3, Bitmap.CompressFormat r4, boolean r5, int r6, int r7, float r8, float r9, float r10, int r11, boolean r12, boolean r13, boolean r14) {
        if (r10 > 1.0f) goto L7;
        if (r13 == true) goto L7;
        Bitmap r52 = r3;
    L9:
        if (r2 == null) goto L14;
        TLRPC.FileLocation r72 = r2.location;
        if ((r72 instanceof TLRPC.TL_fileLocationToBeDeprecated) == false) goto L14;
        TLRPC.TL_fileLocationToBeDeprecated r73 = (TLRPC.TL_fileLocationToBeDeprecated) r72;
    L31:
        int r132 = AnonymousClass7.$SwitchMap$android$graphics$Bitmap$CompressFormat[r4.ordinal()];
        if (r132 != 1) goto L34;
    L38:
        String r82 = ".webp";
    L39:
        String r83 = r73.volume_id + "_" + r73.local_id + r82;
        if (r14 == false) goto L43;
        File r62 = FileLoader.getDirectory(4);
    L46:
        FileOutputStream r63 = new FileOutputStream(new File(r62, r83));
        r52.compress(r4, r11, r63);
        if (r12 == true) goto L49;
        r2.size = (int) r63.getChannel().size();
    L49:
        r63.close();
        if (r12 == false) goto L52;
        ByteArrayOutputStream r64 = new ByteArrayOutputStream();
        r52.compress(r4, r11, r64);
        byte[] r42 = r64.toByteArray();
        r2.bytes = r42;
        r2.size = r42.length;
        r64.close();
    L52:
        if (r52 == r3) goto L54;
        r52.recycle();
    L54:
        return r2;
    L43:
        if (r73.volume_id == (-2147483648L)) goto L45;
        r62 = FileLoader.getDirectory(0);
        goto L46
    L45:
        r62 = FileLoader.getDirectory(4);
        goto L46
    L34:
        if (r132 == 2) goto L38;
        if (r132 == 3) goto L38;
        r82 = ".jpg";
    L14:
        r73 = new TLRPC.TL_fileLocationToBeDeprecated();
        r73.volume_id = -2147483648L;
        r73.dc_id = Integer.MIN_VALUE;
        r73.local_id = SharedConfig.getLastLocalId();
        r73.file_reference = new byte[0];
        r2 = new TLRPC.TL_photoSize_layer127();
        r2.location = r73;
        r2.w = r52.getWidth();
        int r133 = r52.getHeight();
        r2.h = r133;
        int r0 = r2.w;
        if (r0 > 100) goto L19;
        if (r133 > 100) goto L19;
        r2.type = "s";
    L19:
        if (r0 > 320) goto L23;
        if (r133 > 320) goto L23;
        r2.type = "m";
    L23:
        if (r0 > 800) goto L27;
        if (r133 > 800) goto L27;
        r2.type = "x";
    L27:
        if (r0 > 1280) goto L30;
        if (r133 > 1280) goto L30;
        r2.type = "y";
    L30:
        r2.type = "w";
    L7:
        r52 = Bitmaps.createScaledBitmap(r3, r6, r7, true);
        goto L9
    }

    public static boolean shouldSendImageAsDocument(String r4, Uri r5) {
        BitmapFactory.Options r0 = new BitmapFactory.Options();
        r0.inJustDecodeBounds = true;
        if (r4 != null) goto L15;
        if (r5 == null) goto L15;
        if (r5.getScheme() == null) goto L15;
        if (r5.getScheme().contains("file") == false) goto L32;
        r4 = r5.getPath();
        goto L15
    L32:
        r4 = AndroidUtilities.getPath(r5);     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        FileLog.e(th);
    L15:
        if (r4 == null) goto L17;
        BitmapFactory.decodeFile(r4, r0);
    L23:
        float r42 = r0.outWidth;
        float r52 = r0.outHeight;
        if ((r42 / r52) <= 10.0f) goto L26;
        return true;
    L26:
        if ((r52 / r42) <= 10.0f) goto L29;
        return true;
    L29:
        return false;
    L17:
        if (r5 == null) goto L23;
        InputStream r43 = ApplicationLoader.applicationContext.getContentResolver().openInputStream(r5);     // Catch: Throwable -> L20
        BitmapFactory.decodeStream(r43, null, r0);     // Catch: Throwable -> L20
        r43.close();     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        FileLog.e(th);
        return false;
    }

    private int sizeOfBitmapDrawable(BitmapDrawable r1) {
        if ((r1 instanceof AnimatedFileDrawable) == false) goto L7;
        return ((AnimatedFileDrawable) r1).estimateSizeInCache();
    L7:
        if ((r1 instanceof RLottieDrawable) == false) goto L11;
        return ((RLottieDrawable) r1).estimateSizeInCache();
    L11:
        return r1.getBitmap().getByteCount();
    }

    private boolean useLottieMemCache(ImageLocation r4, String r5) {
        if (r5.endsWith("_firstframe") == false) goto L5;
    L18:
        return false;
    L5:
        if (r5.endsWith("_lastframe") == true) goto L18;
        if (r4 == null) goto L16;
        if (MessageObject.isAnimatedStickerDocument(r4.document, true) == false) goto L12;
    L17:
        return true;
    L12:
        if (r4.imageType == 1) goto L17;
        if (MessageObject.isVideoSticker(r4.document) == true) goto L17;
    L16:
        if (isAnimatedAvatar(r5) == false) goto L18;
        goto L17
    }

    public void addTestWebFile(String r1, WebFile r2) {
        if (r1 == null) goto L7;
        if (r2 == null) goto L8;
        this.testWebFile.put(r1, r2);
        return;
    L8:
        return;
    }

    public void cancelForceLoadingForImageReceiver(ImageReceiver r3) {
        if (r3 == null) goto L9;
        String r32 = r3.getImageKey();
        if (r32 != null) goto L7;
        return;
    L7:
        this.imageLoadQueue.postRunnable(new ImageLoader$$ExternalSyntheticLambda13(this, r32));
        return;
    }

    public void cancelLoadHttpFile(String r3) {
        HttpFileTask r0 = this.httpFileLoadTasksByKeys.get(r3);
        if (r0 == null) goto L5;
        r0.cancel(true);
        this.httpFileLoadTasksByKeys.remove(r3);
        this.httpFileLoadTasks.remove(r0);
    L5:
        Runnable r32 = this.retryHttpsTasks.get(r3);
        if (r32 == null) goto L8;
        AndroidUtilities.cancelRunOnUIThread(r32);
    L8:
        runHttpFileLoadTasks(null, 0);
    }

    public void cancelLoadingForImageReceiver(ImageReceiver r5, boolean r6) {
        if (r5 != null) goto L4;
        return;
    L4:
        WebInstantView.cancelLoadPhoto(r5);
        ArrayList<Runnable> r0 = r5.getLoadingOperations();
        monitor-enter(r0);
    L12:
        th = move-exception;
        throw th;
    L7:
        if (r0.isEmpty() == true) goto L15;
        int r1 = 0;
    L10:
        if (r1 >= r0.size()) goto L14;
        this.imageLoadQueue.cancelRunnable(r0.get(r1));     // Catch: Throwable -> L12
        r1 = r1 + 1;     // Catch: Throwable -> L12
        goto L10
    L14:
        r0.clear();     // Catch: Throwable -> L12
    L15:
        monitor-exit(r0);     // Catch: Throwable -> L12
        r5.addLoadingImageRunnable(null);
        this.imageLoadQueue.postRunnable(new ImageLoader$$ExternalSyntheticLambda3(this, r6, r5));
    }

    public void changeFileLoadingPriorityForImageReceiver(ImageReceiver r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        int r0 = r4.getFileLoadingPriority();
        this.imageLoadQueue.postRunnable(new ImageLoader$$ExternalSyntheticLambda0(this, r4, r0));
    }

    public void checkMediaPaths() {
        checkMediaPaths(null);
    }

    public void checkMediaPaths(Runnable r3) {
        this.cacheOutQueue.postRunnable(new ImageLoader$$ExternalSyntheticLambda7(this, r3));
    }

    public void clearMemory() {
        this.smallImagesMemCache.evictAll();
        this.memCache.evictAll();
        this.lottieMemCache.evictAll();
    }

    public SparseArray<File> createMediaPaths() {
        SparseArray<File> r0 = new SparseArray();
        File r1 = AndroidUtilities.getCacheDir();
        if (r1.isDirectory() == false) goto L184;
    L8:
        AndroidUtilities.createEmptyFile(new File(r1, ".nomedia"));
        r0.put(4, r1);
        if (BuildVars.LOGS_ENABLED == false) goto L11;
        FileLog.d("cache path = " + r1);
    L11:
        FileLog.d("selected SD card = " + SharedConfig.storageCacheDir);
    L25:
        e = move-exception;
        FileLog.e(e);
    L177:
        return r0;
    L13:
        if ("mounted".equals(Environment.getExternalStorageState()) == false) goto L172;
        File r2 = Environment.getExternalStorageDirectory();     // Catch: Exception -> L25
        File r5 = null;
        if (TextUtils.isEmpty(SharedConfig.storageCacheDir) == true) goto L39;
        ArrayList<File> r4 = AndroidUtilities.getRootDirs();     // Catch: Exception -> L25
        if (r4 == null) goto L28;
        int r8 = r4.size();     // Catch: Exception -> L25
        int r9 = 0;
    L20:
        if (r9 >= r8) goto L28;
        File r10 = r4.get(r9);     // Catch: Exception -> L25
        FileLog.d("root dir " + r9 + " " + r10);     // Catch: Exception -> L25
        if (r10.getAbsolutePath().startsWith(SharedConfig.storageCacheDir) == true) goto L23;
        r9 = r9 + 1;     // Catch: Exception -> L25
        goto L20
    L23:
        r2 = r10;
    L28:
        if (r2.getAbsolutePath().startsWith(SharedConfig.storageCacheDir) == true) goto L39;
        File[] r42 = ApplicationLoader.applicationContext.getExternalFilesDirs(null);     // Catch: Exception -> L25
        if (r42 == null) goto L39;
        int r82 = 0;
    L33:
        if (r82 >= r42.length) goto L39;
        if (r42[r82] == null) goto L38;
        FileLog.d("dirsDebug " + r82 + " " + r42[r82]);     // Catch: Exception -> L25
    L38:
        r82 = r82 + 1;     // Catch: Exception -> L25
    L39:
        FileLog.d("external storage = " + r2);     // Catch: Exception -> L25
        if (Build.VERSION.SDK_INT < 30) goto L58;
    L52:
        e = e;
        File r43 = null;
    L55:
        FileLog.e(e);     // Catch: Exception -> L25
    L56:
        this.telegramPath = new File(ApplicationLoader.applicationContext.getExternalFilesDir(null), "exteraGram");     // Catch: Exception -> L25
        r5 = r43;
    L73:
        this.telegramPath.mkdirs();     // Catch: Exception -> L25
        if (this.telegramPath.isDirectory() == true) goto L87;
        ArrayList<File> r22 = AndroidUtilities.getDataDirs();     // Catch: Exception -> L25
        int r44 = r22.size();     // Catch: Exception -> L25
        int r7 = 0;
    L76:
        if (r7 >= r44) goto L87;
        File r92 = r22.get(r7);     // Catch: Exception -> L25
        if (r92 == null) goto L84;
        if (TextUtils.isEmpty(SharedConfig.storageCacheDir) == true) goto L84;
        if (r92.getAbsolutePath().startsWith(SharedConfig.storageCacheDir) == false) goto L84;
        File r23 = new File(r92, "exteraGram");     // Catch: Exception -> L25
        this.telegramPath = r23;     // Catch: Exception -> L25
        r23.mkdirs();     // Catch: Exception -> L25
    L84:
        r7 = r7 + 1;     // Catch: Exception -> L25
    L87:
        if (this.telegramPath.isDirectory() == true) goto L186;
    L148:
        if (r5 != null) goto L150;
    L174:
        SharedConfig.checkSaveToGalleryFiles();     // Catch: Exception -> L25
        goto L177
    L150:
        if (r5.isDirectory() == false) goto L174;
        File r24 = new File(r5, "exteraGram Images");     // Catch: Exception -> L159
        r24.mkdir();     // Catch: Exception -> L159
        if (r24.isDirectory() == true) goto L154;
    L190:
        File r25 = new File(r5, "exteraGram Video");     // Catch: Exception -> L169
        r25.mkdir();     // Catch: Exception -> L169
        if (r25.isDirectory() == false) goto L174;
        if (canMoveFiles(r1, r25, 2) == false) goto L174;
        r0.put(101, r25);     // Catch: Exception -> L169
        if (BuildVars.LOGS_ENABLED == false) goto L174;
        FileLog.d("video path = " + r25);     // Catch: Exception -> L169
    L169:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L25
        goto L174
    L154:
        if (canMoveFiles(r1, r24, 0) == false) goto L190;
        r0.put(100, r24);     // Catch: Exception -> L159
        if (BuildVars.LOGS_ENABLED == false) goto L190;
        FileLog.d("image path = " + r24);     // Catch: Exception -> L159
    L159:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L25
        goto L190
    L186:
        File r26 = new File(this.telegramPath, "exteraGram Images");     // Catch: Exception -> L96
        r26.mkdir();     // Catch: Exception -> L96
        if (r26.isDirectory() == true) goto L91;
    L200:
        File r27 = new File(this.telegramPath, "exteraGram Video");     // Catch: Exception -> L106
        r27.mkdir();     // Catch: Exception -> L106
        if (r27.isDirectory() == true) goto L101;
    L198:
        File r28 = new File(this.telegramPath, "exteraGram Audio");     // Catch: Exception -> L116
        r28.mkdir();     // Catch: Exception -> L116
        if (r28.isDirectory() == true) goto L111;
    L194:
        File r29 = new File(this.telegramPath, "exteraGram Documents");     // Catch: Exception -> L126
        r29.mkdir();     // Catch: Exception -> L126
        if (r29.isDirectory() == true) goto L121;
    L182:
        File r210 = new File(this.telegramPath, "exteraGram Files");     // Catch: Exception -> L136
        r210.mkdir();     // Catch: Exception -> L136
        if (r210.isDirectory() == true) goto L131;
    L178:
        File r211 = new File(this.telegramPath, "exteraGram Stories");     // Catch: Exception -> L146
        r211.mkdir();     // Catch: Exception -> L146
        if (r211.isDirectory() == false) goto L148;
        if (canMoveFiles(r1, r211, 6) == false) goto L148;
        AndroidUtilities.createEmptyFile(new File(r211, ".nomedia"));     // Catch: Exception -> L146
        r0.put(6, r211);     // Catch: Exception -> L146
        if (BuildVars.LOGS_ENABLED == false) goto L148;
        FileLog.d("stories path = " + r211);     // Catch: Exception -> L146
    L146:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L25
        goto L148
    L131:
        if (canMoveFiles(r1, r210, 5) == false) goto L178;
        AndroidUtilities.createEmptyFile(new File(r210, ".nomedia"));     // Catch: Exception -> L136
        r0.put(5, r210);     // Catch: Exception -> L136
        if (BuildVars.LOGS_ENABLED == false) goto L178;
        FileLog.d("files path = " + r210);     // Catch: Exception -> L136
    L136:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L25
        goto L178
    L121:
        if (canMoveFiles(r1, r29, 3) == false) goto L182;
        AndroidUtilities.createEmptyFile(new File(r29, ".nomedia"));     // Catch: Exception -> L126
        r0.put(3, r29);     // Catch: Exception -> L126
        if (BuildVars.LOGS_ENABLED == false) goto L182;
        FileLog.d("documents path = " + r29);     // Catch: Exception -> L126
    L126:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L25
        goto L182
    L111:
        if (canMoveFiles(r1, r28, 1) == false) goto L194;
        AndroidUtilities.createEmptyFile(new File(r28, ".nomedia"));     // Catch: Exception -> L116
        r0.put(1, r28);     // Catch: Exception -> L116
        if (BuildVars.LOGS_ENABLED == false) goto L194;
        FileLog.d("audio path = " + r28);     // Catch: Exception -> L116
    L116:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L25
        goto L194
    L101:
        if (canMoveFiles(r1, r27, 2) == false) goto L198;
        r0.put(2, r27);     // Catch: Exception -> L106
        if (BuildVars.LOGS_ENABLED == false) goto L198;
        FileLog.d("video path = " + r27);     // Catch: Exception -> L106
    L106:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L25
        goto L198
    L91:
        if (canMoveFiles(r1, r26, 0) == false) goto L200;
        r0.put(0, r26);     // Catch: Exception -> L96
        if (BuildVars.LOGS_ENABLED == false) goto L200;
        FileLog.d("image path = " + r26);     // Catch: Exception -> L96
    L96:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L25
        goto L200
    L43:
        if (ApplicationLoader.applicationContext.getExternalMediaDirs().length <= 0) goto L54;
        File r212 = getPublicStorageDir();     // Catch: Exception -> L52
        r43 = new File(r212, "exteraGram");     // Catch: Exception -> L50
        r43.mkdirs();     // Catch: Exception -> L48
    L48:
        e = e;
    L50:
        e = move-exception;
        r43 = r212;
        e = e;
        goto L55
    L54:
        r43 = null;
        goto L56
    L58:
        if (TextUtils.isEmpty(SharedConfig.storageCacheDir) == true) goto L63;
        if (r2.getAbsolutePath().startsWith(SharedConfig.storageCacheDir) == false) goto L63;
    L72:
        this.telegramPath = new File(r2, "exteraGram");     // Catch: Exception -> L25
    L63:
        if (r2.exists() == false) goto L68;
        if (r2.isDirectory() == true) goto L70;
    L71:
        FileLog.d("can't write to this directory = " + r2 + " use files dir");     // Catch: Exception -> L25
        r2 = ApplicationLoader.applicationContext.getExternalFilesDir(null);     // Catch: Exception -> L25
    L70:
        if (r2.canWrite() == true) goto L72;
    L68:
        if (r2.mkdirs() == false) goto L71;
    L172:
        if (BuildVars.LOGS_ENABLED == false) goto L174;
        FileLog.d("this Android can't rename files");     // Catch: Exception -> L25
        goto L174
    L184:
        r1.mkdirs();     // Catch: Exception -> L6
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    public boolean decrementUseCount(String r4) {
        Integer r0 = this.bitmapUseCounts.get(r4);
        if (r0 != null) goto L5;
        return true;
    L5:
        int r2 = r0.intValue();
        HashMap<String, Integer> r3 = this.bitmapUseCounts;
        if (r2 != 1) goto L9;
        r3.remove(r4);
        return true;
    L9:
        r3.put(r4, Integer.valueOf(r0.intValue() - 1));
        return false;
    }

    public DispatchQueuePriority getCacheOutQueue() {
        return this.cacheOutQueue;
    }

    public Float getFileProgress(String r5) {
        if (r5 != null) goto L5;
        return null;
    L5:
        long[] r4 = this.fileProgresses.get(r5);
        if (r4 != null) goto L8;
        return null;
    L8:
        long r0 = r4[1];
        if (r0 != 0) goto L13;
        return Float.valueOf(0.0f);
    L13:
        return Float.valueOf(Math.min(1.0f, r4[0] / r0));
    }

    public long[] getFileProgressSizes(String r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return this.fileProgresses.get(r1);
    }

    public BitmapDrawable getFromMemCache(String r2) {
        BitmapDrawable r0 = this.memCache.get(r2);
        if (r0 != null) goto L5;
        r0 = this.smallImagesMemCache.get(r2);
    L5:
        if (r0 != null) goto L7;
        r0 = this.wallpaperMemCache.get(r2);
    L7:
        if (r0 == null) goto L9;
        return r0;
    L9:
        return getFromLottieCache(r2);
    }

    public BitmapDrawable getImageFromMemory(TLObject r5, String r6, String r7) {
        String r0 = null;
        if (r5 != null) goto L6;
        if (r6 != null) goto L6;
        return null;
    L6:
        if (r6 == null) goto L9;
        r0 = Utilities.MD5(r6);
    L20:
        if (r7 == null) goto L23;
        r0 = r0 + "@" + r7;
    L23:
        return getFromMemCache(r0);
    L9:
        if ((r5 instanceof TLRPC.FileLocation) == false) goto L12;
        TLRPC.FileLocation r52 = (TLRPC.FileLocation) r5;
        r0 = r52.volume_id + "_" + r52.local_id;
        goto L20
    L12:
        if ((r5 instanceof TLRPC.Document) == false) goto L15;
        TLRPC.Document r53 = (TLRPC.Document) r5;
        r0 = r53.dc_id + "_" + r53.id;
        goto L20
    L15:
        if ((r5 instanceof SecureDocument) == false) goto L18;
        SecureDocument r54 = (SecureDocument) r5;
        r0 = r54.secureFile.dc_id + "_" + r54.secureFile.id;
        goto L20
    L18:
        if ((r5 instanceof WebFile) == false) goto L20;
        r0 = Utilities.MD5(((WebFile) r5).url);
        goto L20
    }

    public LruCache<BitmapDrawable> getLottieMemCahce() {
        return this.lottieMemCache;
    }

    public String getReplacedKey(String r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return this.replacedBitmaps.get(r1);
    }

    public boolean hasLottieMemCache(String r1) {
        LruCache<BitmapDrawable> r0 = this.lottieMemCache;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.contains(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void incrementUseCount(String r3) {
        Integer r0 = this.bitmapUseCounts.get(r3);
        HashMap<String, Integer> r2 = this.bitmapUseCounts;
        if (r0 != null) goto L6;
        r2.put(r3, 1);
        return;
    L6:
        r2.put(r3, Integer.valueOf(r0.intValue() + 1));
    }

    public boolean isInMemCache(String r3, boolean r4) {
        if (r4 == false) goto L9;
        if (getFromLottieCache(r3) == null) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (getFromMemCache(r3) == null) goto L11;
        return true;
    L11:
        return false;
    }

    public boolean isLoadingHttpFile(String r1) {
        return this.httpFileLoadTasksByKeys.containsKey(r1);
    }

    public void loadHttpFile(String r8, String r9, int r10) {
        if (r8 != null) goto L4;
        return;
    L4:
        if (r8.length() != 0) goto L6;
        return;
    L6:
        if (this.httpFileLoadTasksByKeys.containsKey(r8) == true) goto L12;
        String r5 = getHttpUrlExtension(r8, r9);
        File r4 = new File(FileLoader.getDirectory(4), Utilities.MD5(r8) + "_temp." + r5);
        r4.delete();
        HttpFileTask r1 = new HttpFileTask(this, r8, r4, r5, r10);
        this.httpFileLoadTasks.add(r1);
        this.httpFileLoadTasksByKeys.put(r8, r1);
        runHttpFileLoadTasks(null, 0);
        return;
    }

    public void loadImageForImageReceiver(ImageReceiver r2) {
        loadImageForImageReceiver(r2, null);
    }

    public void loadImageForImageReceiver(ImageReceiver r37, List<ImageReceiver> r38) {
        ImageReceiver r1 = r37;
        if (r1 == null) goto L306;
        String r3 = r1.getMediaKey();
        int r12 = r1.getNewGuid();
        if (r3 == null) goto L41;
        ImageLocation r2 = r1.getMediaLocation();
        BitmapDrawable r4 = findInPreloadImageReceivers(r3, r38);
        if (r4 == null) goto L10;
    L12:
        Drawable r22 = r4;
        if ((r22 instanceof RLottieDrawable) == false) goto L29;
        boolean r42 = ((RLottieDrawable) r22).hasBitmap();
    L32:
        if (r42 == false) goto L38;
        if (r22 == null) goto L38;
        cancelLoadingForImageReceiver(r1, true);
        r1.setImageBitmapByKey(r22, r3, 3, true, r12);
        if (r37.isForcePreview() == false) goto L307;
        r1 = r37;
        String r10 = r3;
        boolean r13 = false;
        boolean r23 = true;
    L42:
        String r32 = r1.getImageKey();
        if (r23 == true) goto L70;
        if (r32 == null) goto L70;
        ImageLocation r43 = r1.getImageLocation();
        BitmapDrawable r5 = findInPreloadImageReceivers(r32, r38);
        if (r5 == null) goto L48;
    L50:
        if (r5 != null) goto L63;
        BitmapDrawable r44 = this.memCache.get(r32);
        if (r44 == null) goto L54;
        this.memCache.moveToFront(r32);
    L54:
        if (r44 != null) goto L58;
        r44 = this.smallImagesMemCache.get(r32);
        if (r44 == null) goto L58;
        this.smallImagesMemCache.moveToFront(r32);
    L58:
        r5 = r44;
        if (r5 != null) goto L63;
        r5 = this.wallpaperMemCache.get(r32);
        if (r5 == null) goto L63;
        this.wallpaperMemCache.moveToFront(r32);
    L63:
        if (r5 == null) goto L70;
        cancelLoadingForImageReceiver(r1, true);
        r1.setImageBitmapByKey(r5, r32, 0, true, r12);
        if (r37.isForcePreview() == true) goto L69;
        if (r10 == null) goto L304;
        if (r13 == false) goto L69;
        return;
    L304:
        return;
    L69:
        boolean r14 = true;
    L71:
        String r33 = r37.getThumbKey();
        if (r33 != null) goto L74;
    L95:
        ImageReceiver r15 = r37;
        boolean r24 = false;
    L96:
        Object r34 = r15.getParentObject();
        TLRPC.Document r45 = r15.getQualityThumbDocument();
        ImageLocation r52 = r15.getThumbLocation();
        String r6 = r15.getThumbFilter();
        ImageLocation r7 = r15.getMediaLocation();
        String r152 = r15.getMediaFilter();
        ImageLocation r102 = r15.getImageLocation();
        String r11 = r15.getImageFilter();
        if (r102 == null) goto L99;
    L108:
        boolean r9 = false;
        ImageLocation r46 = r102;
    L109:
        String r17 = "mp4";
        String r19 = null;
        if (r46 != null) goto L112;
    L114:
        String r0 = null;
    L115:
        String r382 = r0;
        if (r7 != null) goto L118;
    L120:
        r17 = null;
    L121:
        String r02 = r15.getExt();
        if (r02 != null) goto L124;
        r02 = "jpg";
    L124:
        if (r382 != null) goto L126;
        String r21 = r02;
    L127:
        if (r17 != null) goto L129;
        r17 = r02;
    L129:
        boolean r383 = r24;
        ImageLocation r222 = r7;
        boolean r26 = r9;
        String r16 = null;
        String r25 = null;
        String r242 = null;
        String r252 = null;
        int r72 = 0;
        boolean r232 = false;
    L130:
        ImageLocation r27 = r102;
        if (r72 >= 2) goto L219;
        if (r72 != 0) goto L134;
        ImageLocation r103 = r46;
        int r28 = r72;
        String r73 = r21;
    L135:
        if (r103 != null) goto L137;
        int r29 = r12;
        boolean r30 = r13;
    L144:
        boolean r31 = r14;
    L218:
        r72 = r28 + 1;
        r102 = r27;
        r12 = r29;
        r13 = r30;
        r14 = r31;
        goto L130
    L137:
        r29 = r12;
        if (r222 == null) goto L141;
        ImageLocation r122 = r222;
    L140:
        r30 = r13;
        String r123 = r103.getKey(r34, r122, false);
        if (r123 == null) goto L144;
        if (r222 == null) goto L148;
        ImageLocation r132 = r222;
    L147:
        r31 = r14;
        String r133 = r103.getKey(r34, r132, true);
        if (r103.path == null) goto L152;
        r133 = r133 + "." + getHttpUrlExtension(r103.path, "jpg");
        ImageLocation r322 = r46;
    L210:
        if (r28 != 0) goto L212;
        r25 = r123;
        r242 = r133;
    L213:
        if (r103 != r52) goto L217;
        if (r28 != 0) goto L216;
        r25 = null;
        r46 = null;
        r242 = null;
        goto L218
    L216:
        r16 = null;
        r222 = null;
        r252 = null;
    L217:
        r46 = r322;
        goto L218
    L212:
        r16 = r123;
        r252 = r133;
        goto L213
    L152:
        TLRPC.PhotoSize r142 = r103.photoSize;
        r322 = r46;
        if ((r142 instanceof TLRPC.TL_photoStrippedSize) == false) goto L155;
    L209:
        r133 = r133 + "." + r73;
        goto L210
    L155:
        if ((r142 instanceof TLRPC.TL_photoPathSize) == true) goto L209;
        if (r103.location == null) goto L172;
        String r134 = r133 + "." + r73;
        if (r37.getExt() != null) goto L169;
        TLRPC.TL_fileLocationToBeDeprecated r47 = r103.location;
        if (r47.key != null) goto L169;
        String r74 = r134;
        if (r47.volume_id == (-2147483648L)) goto L166;
    L168:
        r133 = r74;
        goto L210
    L166:
        if (r47.local_id >= 0) goto L168;
    L170:
        r133 = r74;
    L171:
        r232 = true;
    L169:
        r74 = r134;
        goto L170
    L172:
        WebFile r48 = r103.webFile;
        if (r48 == null) goto L176;
        r133 = r133 + "." + getHttpUrlExtension(r103.webFile.url, FileLoader.getMimeTypePart(r48.mime_type));
        goto L210
    L176:
        if (r103.secureDocument == null) goto L179;
        r133 = r133 + "." + r73;
        goto L210
    L179:
        if (r103.document == null) goto L207;
        if (r28 != 0) goto L183;
        if (r26 == false) goto L183;
        r123 = "q_".concat(r123);
    L183:
        String r49 = FileLoader.getDocumentFileName(r103.document);
        int r75 = r49.lastIndexOf(46);
        String r143 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r75 != (-1)) goto L186;
        String r410 = _UrlKt.FRAGMENT_ENCODE_SET;
    L188:
        if (r410.length() <= 1) goto L190;
        r143 = r410;
    L196:
        r133 = r133 + r143;
        if (MessageObject.isVideoDocument(r103.document) == false) goto L199;
    L205:
        r232 = false;
        goto L210
    L199:
        if (MessageObject.isGifDocument(r103.document) == true) goto L205;
        if (MessageObject.isRoundVideoDocument(r103.document) == true) goto L205;
        if (MessageObject.canPreviewDocument(r103.document) == true) goto L205;
    L190:
        if ("video/mp4".equals(r103.document.mime_type) == false) goto L193;
        r143 = ".mp4";
        goto L196
    L193:
        if ("video/x-matroska".equals(r103.document.mime_type) == false) goto L196;
        r143 = ".mkv";
        goto L196
    L186:
        r410 = r49.substring(r75);
        goto L188
    L207:
        if ((r34 instanceof TLRPC.StickerSet) == false) goto L210;
        r133 = r133 + "." + r73;
        goto L210
    L148:
        r132 = r46;
        goto L147
    L141:
        r122 = r46;
        goto L140
    L134:
        r28 = r72;
        r73 = r17;
        r103 = r222;
        goto L135
    L219:
        ImageLocation r323 = r46;
        int r292 = r12;
        boolean r302 = r13;
        boolean r312 = r14;
        if (r52 == null) goto L240;
        ImageLocation r411 = r37.getStrippedLocation();
        if (r411 != null) goto L227;
        if (r222 == null) goto L225;
        ImageLocation r104 = r222;
    L226:
        r411 = r104;
        goto L227
    L225:
        r104 = r27;
    L227:
        String r192 = r52.getKey(r34, r411, false);
        int r144 = 1;
        String r35 = r52.getKey(r34, r411, true);
        if (r52.path == null) goto L231;
        r35 = r35 + "." + getHttpUrlExtension(r52.path, "jpg");
    L230:
        r19 = r35;
        String r36 = r192;
    L242:
        if (r16 == null) goto L245;
        if (r152 == null) goto L245;
        r16 = r16 + "@" + r152;
    L245:
        if (r25 == null) goto L248;
        if (r11 == null) goto L248;
        r25 = r25 + "@" + r11;
    L248:
        if (r36 == null) goto L252;
        if (r6 == null) goto L252;
        r36 = r36 + "@" + r6;
    L252:
        if (r37.getUniqKeyPrefix() == null) goto L255;
        if (r25 == null) goto L255;
        r25 = r37.getUniqKeyPrefix() + r25;
    L255:
        String r135 = r25;
        if (r37.getUniqKeyPrefix() == null) goto L259;
        if (r16 == null) goto L259;
        r16 = r37.getUniqKeyPrefix() + r16;
    L259:
        String r162 = r16;
        if (r323 != null) goto L262;
        String r412 = r02;
        String r210 = r36;
        String r39 = r19;
        String r18 = r21;
        String r193 = r242;
        int r124 = r292;
    L271:
        if (r222 == null) goto L287;
        int r03 = r37.getCacheType();
        if (r03 != 0) goto L276;
        if (r232 == false) goto L276;
        int r20 = r144;
    L277:
        if (r20 != 0) goto L279;
        int r92 = r144;
    L280:
        if (r383 == true) goto L282;
        createLoadOperationForImageReceiver(r37, r210, r39, r412, r52, r6, 0, r92, 1, 1, r124);
    L282:
        if (r312 == true) goto L284;
        createLoadOperationForImageReceiver(r37, r135, r193, r18, r323, r11, 0, 1, 0, 0, r124);
    L284:
        if (r302 == true) goto L305;
        String r310 = r252;
        createLoadOperationForImageReceiver(r37, r162, r310, r17, r222, r152, r37.getSize(), r20, 3, 0, r124);
        return;
    L305:
        return;
    L279:
        r92 = r20;
    L276:
        r20 = r03;
        goto L277
    L287:
        int r04 = r37.getCacheType();
        if (r04 != 0) goto L291;
        if (r232 == false) goto L291;
        int r153 = r144;
    L292:
        if (r153 != 0) goto L294;
        int r93 = r144;
    L295:
        if (r383 == false) goto L297;
        int r112 = 2;
    L298:
        createLoadOperationForImageReceiver(r37, r210, r39, r412, r52, r6, 0, r93, 1, r112, r124);
        String r311 = r193;
        createLoadOperationForImageReceiver(r37, r135, r311, r18, r323, r11, r37.getSize(), r153, 0, 0, r124);
        return;
    L297:
        r112 = r144;
        goto L298
    L294:
        r93 = r153;
    L291:
        r153 = r04;
        goto L292
    L262:
        if (r323.path == null) goto L268;
        if (r383 == false) goto L265;
        int r94 = 2;
    L266:
        createLoadOperationForImageReceiver(r37, r36, r19, r02, r52, r6, 0, 1, 1, r94, r292);
        String r313 = r242;
        createLoadOperationForImageReceiver(r37, r135, r313, r21, r323, r11, r37.getSize(), 1, 0, 0, r292);
        return;
    L265:
        r94 = r144;
        goto L266
    L268:
        r323 = r323;
        r210 = r36;
        r39 = r19;
        r18 = r21;
        r193 = r242;
        r124 = r292;
        r412 = r02;
        goto L271
    L231:
        TLRPC.PhotoSize r413 = r52.photoSize;
        if ((r413 instanceof TLRPC.TL_photoStrippedSize) == false) goto L234;
    L239:
        r35 = r35 + "." + r02;
        goto L230
    L234:
        if ((r413 instanceof TLRPC.TL_photoPathSize) == true) goto L239;
        if (r52.location == null) goto L230;
        r35 = r35 + "." + r02;
        goto L230
    L240:
        r144 = 1;
        r36 = null;
        goto L242
    L126:
        r21 = r382;
        goto L127
    L118:
        if (r7.imageType != 2) goto L120;
    L112:
        if (r46.imageType != 2) goto L114;
        r0 = "mp4";
        goto L115
    L99:
        if (r15.isNeedsQualityThumb() == false) goto L108;
        if (r15.isCurrentKeyQuality() == false) goto L108;
        if ((r34 instanceof MessageObject) == false) goto L106;
        r46 = ImageLocation.getForDocument(((MessageObject) r34).getDocument());
    L105:
        r9 = true;
        goto L109
    L106:
        if (r45 == null) goto L108;
        r46 = ImageLocation.getForDocument(r45);
        goto L105
    L74:
        if (useLottieMemCache(r37.getThumbLocation(), r33) == false) goto L77;
        BitmapDrawable r110 = getFromLottieCache(r33);
    L76:
        BitmapDrawable r211 = r110;
        if (r211 == null) goto L95;
        r15 = r37;
        r15.setImageBitmapByKey(r211, r33, 1, true, r12);
        cancelLoadingForImageReceiver(r15, false);
        if (r14 == true) goto L92;
    L94:
        r24 = true;
        goto L96
    L92:
        if (r15.isForcePreview() == false) goto L94;
        return;
    L77:
        r110 = this.memCache.get(r33);
        if (r110 == null) goto L80;
        this.memCache.moveToFront(r33);
    L80:
        if (r110 != null) goto L84;
        r110 = this.smallImagesMemCache.get(r33);
        if (r110 == null) goto L84;
        this.smallImagesMemCache.moveToFront(r33);
    L84:
        if (r110 != null) goto L76;
        r110 = this.wallpaperMemCache.get(r33);
        if (r110 == null) goto L76;
        this.wallpaperMemCache.moveToFront(r33);
        goto L76
    L48:
        if (useLottieMemCache(r43, r32) == false) goto L50;
        r5 = getFromLottieCache(r32);
    L70:
        r14 = r23;
        goto L71
    L307:
        return;
    L38:
        if (r22 == null) goto L40;
        r1 = r37;
        r1.setImageBitmapByKey(r22, r3, 3, true, r12);
        r10 = r3;
        r23 = false;
        r13 = true;
        goto L42
    L40:
        r1 = r37;
        goto L41
    L29:
        if ((r22 instanceof AnimatedFileDrawable) == false) goto L31;
        r42 = ((AnimatedFileDrawable) r22).hasBitmap();
        goto L32
    L31:
        r42 = true;
        goto L32
    L10:
        if (useLottieMemCache(r2, r3) == false) goto L13;
        r4 = getFromLottieCache(r3);
        goto L12
    L13:
        BitmapDrawable r212 = this.memCache.get(r3);
        if (r212 == null) goto L16;
        this.memCache.moveToFront(r3);
    L16:
        if (r212 != null) goto L20;
        r212 = this.smallImagesMemCache.get(r3);
        if (r212 == null) goto L20;
        this.smallImagesMemCache.moveToFront(r3);
    L20:
        r4 = r212;
        if (r4 != null) goto L12;
        r4 = this.wallpaperMemCache.get(r3);
        if (r4 == null) goto L12;
        this.wallpaperMemCache.moveToFront(r3);
    L41:
        r10 = r3;
        r23 = false;
        r13 = false;
        goto L42
    }

    public void moveToFront(String r2) {
        if (r2 != null) goto L5;
        return;
    L5:
        if (this.lottieMemCache.get(r2) == null) goto L8;
        this.lottieMemCache.moveToFront(r2);
    L8:
        if (this.memCache.get(r2) == null) goto L11;
        this.memCache.moveToFront(r2);
    L11:
        if (this.smallImagesMemCache.get(r2) == null) goto L14;
        this.smallImagesMemCache.moveToFront(r2);
        return;
    }

    public void onFragmentStackChanged() {
        int r1 = 0;
    L4:
        if (r1 >= this.cachedAnimatedFileDrawables.size()) goto L6;
        this.cachedAnimatedFileDrawables.get(r1).repeatCount = 0;
        r1 = r1 + 1;
        goto L4
    }

    public void preloadArtwork(String r3) {
        this.imageLoadQueue.postRunnable(new ImageLoader$$ExternalSyntheticLambda2(this, r3));
    }

    public void putImageToCache(BitmapDrawable r2, String r3, boolean r4) {
        if (r3.endsWith("_nocache") == false) goto L5;
        return;
    L5:
        if (r4 == false) goto L8;
        this.smallImagesMemCache.put(r3, r2);
        return;
    L8:
        this.memCache.put(r3, r2);
    }

    public void putThumbsToCache(ArrayList<MessageThumb> r5) {
        int r0 = 0;
    L4:
        if (r0 >= r5.size()) goto L6;
        putImageToCache(r5.get(r0).drawable, r5.get(r0).key, true);
        r0 = r0 + 1;
        goto L4
    }

    public void removeImage(String r2) {
        this.bitmapUseCounts.remove(r2);
        this.memCache.remove(r2);
        this.smallImagesMemCache.remove(r2);
    }

    public void removeTestWebFile(String r1) {
        if (r1 != null) goto L4;
        return;
    L4:
        this.testWebFile.remove(r1);
    }

    public void replaceImageInCache(String r1, String r2, ImageLocation r3, boolean r4) {
        if (r4 == false) goto L5;
        AndroidUtilities.runOnUIThread(new ImageLoader$$ExternalSyntheticLambda10(this, r1, r2, r3));
        return;
    L5:
        replaceImageInCacheInternal(r1, r2, r3);
    }
}
