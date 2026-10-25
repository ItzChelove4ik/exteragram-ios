package org.telegram.messenger;

import android.text.TextUtils;
import android.util.SparseArray;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.UByte;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.FileLoadOperation;
import org.telegram.messenger.FilePathDatabase;
import org.telegram.messenger.FileUploadOperation;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes.dex */
public class FileLoader extends BaseController {
    public static final long DEFAULT_MAX_FILE_SIZE = 2098200576;
    public static final long DEFAULT_MAX_FILE_SIZE_PREMIUM = 4196401152L;
    public static final int IMAGE_TYPE_ANIMATION = 2;
    public static final int IMAGE_TYPE_LOTTIE = 1;
    public static final int IMAGE_TYPE_SVG = 3;
    public static final int IMAGE_TYPE_SVG_WHITE = 4;
    public static final int IMAGE_TYPE_THEME_PREVIEW = 5;
    private static final FileLoader[] Instance = null;
    public static final int MEDIA_DIR_AUDIO = 1;
    public static final int MEDIA_DIR_CACHE = 4;
    public static final int MEDIA_DIR_DOCUMENT = 3;
    public static final int MEDIA_DIR_FILES = 5;
    public static final int MEDIA_DIR_IMAGE = 0;
    public static final int MEDIA_DIR_IMAGE_PUBLIC = 100;
    public static final int MEDIA_DIR_STORIES = 6;
    public static final int MEDIA_DIR_VIDEO = 2;
    public static final int MEDIA_DIR_VIDEO_PUBLIC = 101;
    public static final int PRELOAD_CACHE_TYPE = 11;
    public static final int PRIORITY_HIGH = 3;
    public static final int PRIORITY_LOW = 0;
    public static final int PRIORITY_NORMAL = 1;
    public static final int PRIORITY_NORMAL_UP = 2;
    private static final int PRIORITY_STREAM = 4;
    private static volatile DispatchQueue fileLoaderQueue;
    private static SparseArray<File> mediaDirs;
    private static Pattern sentPattern;
    private int currentUploadOperationsCount;
    private int currentUploadSmallOperationsCount;
    private FileLoaderDelegate delegate;
    Runnable dumpFilesQueueRunnable;
    private final FilePathDatabase filePathDatabase;
    private String forceLoadingFile;
    private final FileLoaderPriorityQueue[] largeFilesQueue;
    private int lastReferenceId;
    private final ConcurrentHashMap<String, FileLoadOperation> loadOperationPaths;
    private final ConcurrentHashMap<String, LoadOperationUIObject> loadOperationPathsUI;
    private final HashMap<String, Boolean> loadingVideos;
    private final ConcurrentHashMap<Integer, WeakReference<Object>> parentObjectReferences;
    private int priorityIncreasePointer;
    private final FileLoaderPriorityQueue[] smallFilesQueue;
    private final ConcurrentHashMap<String, FileUploadOperation> uploadOperationPaths;
    private final ConcurrentHashMap<String, FileUploadOperation> uploadOperationPathsEnc;
    private final LinkedList<FileUploadOperation> uploadOperationQueue;
    private final HashMap<String, Long> uploadSizes;
    private final LinkedList<FileUploadOperation> uploadSmallOperationQueue;

    /* renamed from: org.telegram.messenger.FileLoader$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1 implements FileUploadOperation.FileUploadOperationDelegate {
        final /* synthetic */ FileLoader this$0;
        final /* synthetic */ boolean val$encrypted;
        final /* synthetic */ String val$location;
        final /* synthetic */ boolean val$small;

        public static /* synthetic */ void $r8$lambda$HMnMMxtRvKDn1hEQYH6QEfm66eU(AnonymousClass1 r0, boolean r1, String r2, boolean r3, TLRPC.InputFile r4, TLRPC.InputEncryptedFile r5, byte[] r6, byte[] r7, FileUploadOperation r8) {
            r0.lambda$didFinishUploadingFile$0(r1, r2, r3, r4, r5, r6, r7, r8);
        }

        /* renamed from: $r8$lambda$PbJDpq-eM0Wp5zi1W884DiWtyN4, reason: not valid java name */
        public static /* synthetic */ void m4051$r8$lambda$PbJDpqeM0Wp5zi1W884DiWtyN4(AnonymousClass1 r0, boolean r1, String r2, boolean r3) {
            r0.lambda$didFailedUploadingFile$1(r1, r2, r3);
        }

        public AnonymousClass1(FileLoader r1, boolean r2, String r3, boolean r4) {
            this.this$0 = r1;
            this.val$encrypted = r2;
            this.val$location = r3;
            this.val$small = r4;
        }

        private /* synthetic */ void lambda$didFailedUploadingFile$1(boolean r2, String r3, boolean r4) {
            FileLoader r0 = this.this$0;
            if (r2 == false) goto L5;
            FileLoader.m4042$$Nest$fgetuploadOperationPathsEnc(r0).remove(r3);
        L7:
            if (FileLoader.m4039$$Nest$fgetdelegate(this.this$0) == null) goto L9;
            FileLoader.m4039$$Nest$fgetdelegate(this.this$0).fileDidFailedUpload(r3, r2);
        L9:
            FileLoader r22 = this.this$0;
            if (r4 == false) goto L17;
            FileLoader.m4046$$Nest$fputcurrentUploadSmallOperationsCount(r22, FileLoader.m4038$$Nest$fgetcurrentUploadSmallOperationsCount(r22) - 1);
            if (FileLoader.m4038$$Nest$fgetcurrentUploadSmallOperationsCount(this.this$0) >= 1) goto L23;
            FileUploadOperation r23 = (FileUploadOperation) FileLoader.m4044$$Nest$fgetuploadSmallOperationQueue(this.this$0).poll();
            if (r23 == null) goto L24;
            FileLoader r1 = this.this$0;
            FileLoader.m4046$$Nest$fputcurrentUploadSmallOperationsCount(r1, FileLoader.m4038$$Nest$fgetcurrentUploadSmallOperationsCount(r1) + 1);
            r23.start();
            return;
        L24:
            return;
        L23:
            return;
        L17:
            FileLoader.m4045$$Nest$fputcurrentUploadOperationsCount(r22, FileLoader.m4037$$Nest$fgetcurrentUploadOperationsCount(r22) - 1);
            if (FileLoader.m4037$$Nest$fgetcurrentUploadOperationsCount(this.this$0) >= 1) goto L25;
            FileUploadOperation r24 = (FileUploadOperation) FileLoader.m4043$$Nest$fgetuploadOperationQueue(this.this$0).poll();
            if (r24 == null) goto L26;
            FileLoader r12 = this.this$0;
            FileLoader.m4045$$Nest$fputcurrentUploadOperationsCount(r12, FileLoader.m4037$$Nest$fgetcurrentUploadOperationsCount(r12) + 1);
            r24.start();
            return;
        L26:
            return;
        L25:
            return;
        L5:
            FileLoader.m4041$$Nest$fgetuploadOperationPaths(r0).remove(r3);
            goto L7
        }

        private /* synthetic */ void lambda$didFinishUploadingFile$0(boolean r3, String r4, boolean r5, TLRPC.InputFile r6, TLRPC.InputEncryptedFile r7, byte[] r8, byte[] r9, FileUploadOperation r10) {
            FileLoader r0 = this.this$0;
            if (r3 == false) goto L5;
            FileLoader.m4042$$Nest$fgetuploadOperationPathsEnc(r0).remove(r4);
        L6:
            FileLoader r32 = this.this$0;
            if (r5 == false) goto L13;
            FileLoader.m4046$$Nest$fputcurrentUploadSmallOperationsCount(r32, FileLoader.m4038$$Nest$fgetcurrentUploadSmallOperationsCount(r32) - 1);
            if (FileLoader.m4038$$Nest$fgetcurrentUploadSmallOperationsCount(this.this$0) >= 1) goto L19;
            FileUploadOperation r33 = (FileUploadOperation) FileLoader.m4044$$Nest$fgetuploadSmallOperationQueue(this.this$0).poll();
            if (r33 == null) goto L19;
            FileLoader r52 = this.this$0;
            FileLoader.m4046$$Nest$fputcurrentUploadSmallOperationsCount(r52, FileLoader.m4038$$Nest$fgetcurrentUploadSmallOperationsCount(r52) + 1);
            r33.start();
        L19:
            if (FileLoader.m4039$$Nest$fgetdelegate(this.this$0) == null) goto L22;
            FileLoader.m4039$$Nest$fgetdelegate(this.this$0).fileDidUploaded(r4, r6, r7, r8, r9, r10.getTotalFileSize());
            return;
        L22:
            return;
        L13:
            FileLoader.m4045$$Nest$fputcurrentUploadOperationsCount(r32, FileLoader.m4037$$Nest$fgetcurrentUploadOperationsCount(r32) - 1);
            if (FileLoader.m4037$$Nest$fgetcurrentUploadOperationsCount(this.this$0) >= 1) goto L19;
            FileUploadOperation r34 = (FileUploadOperation) FileLoader.m4043$$Nest$fgetuploadOperationQueue(this.this$0).poll();
            if (r34 == null) goto L19;
            FileLoader r53 = this.this$0;
            FileLoader.m4045$$Nest$fputcurrentUploadOperationsCount(r53, FileLoader.m4037$$Nest$fgetcurrentUploadOperationsCount(r53) + 1);
            r34.start();
            goto L19
        L5:
            FileLoader.m4041$$Nest$fgetuploadOperationPaths(r0).remove(r4);
            goto L6
        }

        @Override // org.telegram.messenger.FileUploadOperation.FileUploadOperationDelegate
        public void didChangedUploadProgress(FileUploadOperation r10, long r11, long r13) {
            if (FileLoader.m4039$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            FileLoader.m4039$$Nest$fgetdelegate(this.this$0).fileUploadProgressChanged(r10, this.val$location, r11, r13, this.val$encrypted);
            return;
        }

        @Override // org.telegram.messenger.FileUploadOperation.FileUploadOperationDelegate
        public void didFailedUploadingFile(FileUploadOperation r5) {
            FileLoader.m4049$$Nest$sfgetfileLoaderQueue().postRunnable(new FileLoader$1$$ExternalSyntheticLambda0(this, this.val$encrypted, this.val$location, this.val$small));
        }

        @Override // org.telegram.messenger.FileUploadOperation.FileUploadOperationDelegate
        public void didFinishUploadingFile(FileUploadOperation r12, TLRPC.InputFile r13, TLRPC.InputEncryptedFile r14, byte[] r15, byte[] r16) {
            FileLoader.m4049$$Nest$sfgetfileLoaderQueue().postRunnable(new FileLoader$1$$ExternalSyntheticLambda1(this, this.val$encrypted, this.val$location, this.val$small, r13, r14, r15, r16, r12));
        }
    }

    /* renamed from: org.telegram.messenger.FileLoader$2, reason: invalid class name */
    public class AnonymousClass2 implements FileLoadOperation.FileLoadOperationDelegate {
        final /* synthetic */ FileLoader this$0;
        final /* synthetic */ TLRPC.Document val$document;
        final /* synthetic */ String val$fileName;
        final /* synthetic */ int val$finalType;
        final /* synthetic */ Object val$parentObject;

        /* renamed from: $r8$lambda$mxbBfe-FTETIsHMKtk_6I_m-mOw, reason: not valid java name */
        public static /* synthetic */ void m4052$r8$lambda$mxbBfeFTETIsHMKtk_6I_mmOw(FileLoadOperation r1, FileLoaderPriorityQueue r2) {
            r1.preFinished = true;
            r2.checkLoadingOperations();
        }

        public AnonymousClass2(FileLoader r1, Object r2, TLRPC.Document r3, String r4, int r5) {
            this.this$0 = r1;
            this.val$parentObject = r2;
            this.val$document = r3;
            this.val$fileName = r4;
            this.val$finalType = r5;
        }

        @Override // org.telegram.messenger.FileLoadOperation.FileLoadOperationDelegate
        public void didChangedLoadProgress(FileLoadOperation r9, long r10, long r12) {
            if (FileLoader.m4039$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            FileLoader.m4039$$Nest$fgetdelegate(this.this$0).fileLoadProgressChanged(r9, this.val$fileName, r10, r12);
            return;
        }

        @Override // org.telegram.messenger.FileLoadOperation.FileLoadOperationDelegate
        public void didFailedLoadingFile(FileLoadOperation r3, int r4) {
            FileLoader.m4040$$Nest$fgetloadOperationPathsUI(this.this$0).remove(this.val$fileName);
            FileLoader.m4047$$Nest$mcheckDownloadQueue(this.this$0, r3, r3.getQueue());
            if (FileLoader.m4039$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            FileLoader.m4039$$Nest$fgetdelegate(this.this$0).fileDidFailedLoad(this.val$fileName, r4);
        L6:
            if (this.val$document == null) goto L20;
            Object r32 = this.val$parentObject;
            if ((r32 instanceof MessageObject) == false) goto L20;
            MessageObject r33 = (MessageObject) r32;
            if (r4 != 0) goto L20;
            if (r33.putInDownloadsStore == true) goto L14;
            return;
        L14:
            if (r33.isAnyKindOfSticker() == false) goto L16;
            return;
        L16:
            if (FileLoader.m4050$$Nest$smisOwnMediaDocument(r33, this.val$document) == false) goto L25;
            this.this$0.getDownloadController().onDownloadFail(r33, r4);
            return;
        L25:
            return;
        L20:
            if (r4 != (-1)) goto L26;
            LaunchActivity.checkFreeDiscSpaceStatic(2);
            return;
        }

        @Override // org.telegram.messenger.FileLoadOperation.FileLoadOperationDelegate
        public void didFinishLoadingFile(FileLoadOperation r7, File r8) {
            if (r7.isPreloadVideoOperation() == false) goto L5;
        L8:
            FilePathDatabase.FileMeta r0 = FileLoader.getFileMetadataFromParent(this.this$0.currentAccount, this.val$parentObject);
            if (r0 == null) goto L11;
            this.this$0.getFileLoader().getFileDatabase().saveFileDialogId(r8, r0);
        L11:
            Object r02 = this.val$parentObject;
            if ((r02 instanceof MessageObject) == false) goto L23;
            MessageObject r03 = (MessageObject) r02;
            if (this.val$document == null) goto L23;
            if (r03.putInDownloadsStore == false) goto L23;
            if (r03.isAnyKindOfSticker() == true) goto L23;
            if (FileLoader.m4050$$Nest$smisOwnMediaDocument(r03, this.val$document) == false) goto L23;
            this.this$0.getDownloadController().onDownloadComplete(r03);
        L23:
            if (r7.isPreloadVideoOperation() == true) goto L27;
            FileLoader.m4040$$Nest$fgetloadOperationPathsUI(this.this$0).remove(this.val$fileName);
            if (FileLoader.m4039$$Nest$fgetdelegate(this.this$0) == null) goto L27;
            FileLoader.m4039$$Nest$fgetdelegate(this.this$0).fileDidLoaded(this.val$fileName, r8, this.val$parentObject, this.val$finalType);
        L27:
            FileLoader.m4048$$Nest$mcheckDownloadQueue(this.this$0, r7, r7.getQueue(), 0);
            return;
        L5:
            if (r7.isPreloadFinished() == false) goto L8;
            FileLoader.m4048$$Nest$mcheckDownloadQueue(this.this$0, r7, r7.getQueue(), 0);
        }

        @Override // org.telegram.messenger.FileLoadOperation.FileLoadOperationDelegate
        public void didPreFinishLoading(FileLoadOperation r2, File r3) {
            FileLoaderPriorityQueue r1 = r2.getQueue();
            FileLoader.m4049$$Nest$sfgetfileLoaderQueue().postRunnable(new FileLoader$2$$ExternalSyntheticLambda0(r2, r1));
        }

        @Override // org.telegram.messenger.FileLoadOperation.FileLoadOperationDelegate
        public boolean hasAnotherRefOnFile(String r1) {
            return this.this$0.getFileDatabase().hasAnotherRefOnFile(r1);
        }

        @Override // org.telegram.messenger.FileLoadOperation.FileLoadOperationDelegate
        public boolean isLocallyCreatedFile(String r1) {
            return this.this$0.getFileDatabase().isLocallyCreated(r1);
        }

        @Override // org.telegram.messenger.FileLoadOperation.FileLoadOperationDelegate
        public void saveFilePath(FilePathDatabase.PathData r8, File r9) {
            FilePathDatabase r0 = this.this$0.getFileDatabase();
            long r1 = r8.id;
            int r3 = r8.dc;
            int r4 = r8.type;
            if (r9 == null) goto L6;
            String r7 = r9.toString();
        L7:
            r0.putPath(r1, r3, r4, 0, r7);
            return;
        L6:
            r7 = null;
            goto L7
        }
    }

    public interface FileLoaderDelegate {
        void fileDidFailedLoad(String r1, int r2);

        void fileDidFailedUpload(String r1, boolean r2);

        void fileDidLoaded(String r1, File r2, Object r3, int r4);

        void fileDidUploaded(String r1, TLRPC.InputFile r2, TLRPC.InputEncryptedFile r3, byte[] r4, byte[] r5, long r6);

        void fileLoadProgressChanged(FileLoadOperation r1, String r2, long r3, long r5);

        void fileUploadProgressChanged(FileUploadOperation r1, String r2, long r3, long r5, boolean r7);
    }

    /* loaded from: classes5.dex */
    public interface FileResolver {
        File getFile();
    }

    public static class LoadOperationUIObject {
        Runnable loadInternalRunnable;

        private LoadOperationUIObject() {
        }

        public /* synthetic */ LoadOperationUIObject(FileLoaderIA r1) {
            this();
        }
    }

    public static /* synthetic */ void $r8$lambda$3vVXjF3p4I0xKaa3OsJT5cLVnJQ(FileLoader r0, boolean r1, String r2, long r3, long r5, Float r7) {
        r0.lambda$checkUploadNewDataAvailable$3(r1, r2, r3, r5, r7);
    }

    public static /* synthetic */ void $r8$lambda$5vpJRXwWLYZc6_P5XjDMoacmNrs(FileLoader r0, String r1, int r2) {
        r0.lambda$changePriority$11(r1, r2);
    }

    /* renamed from: $r8$lambda$8TcIwtIyKwMGt7-98KYPAi8qh_k, reason: not valid java name */
    public static /* synthetic */ void m4032$r8$lambda$8TcIwtIyKwMGt798KYPAi8qh_k(FileLoader r0, TLRPC.Document r1, SecureDocument r2, WebFile r3, TLRPC.TL_fileLocationToBeDeprecated r4, ImageLocation r5, Object r6, String r7, long r8, int r10, int r11) {
        r0.lambda$loadFile$13(r1, r2, r3, r4, r5, r6, r7, r8, r10, r11);
    }

    public static /* synthetic */ void $r8$lambda$9XRTWjr5doaAISswS6YAQkL3dHA(FileLoader r0, boolean r1) {
        r0.lambda$onNetworkChanged$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$AQR1Ltt0vPhutdRJ2SANxiIgRPQ(FileLoader r0, TLRPC.Document r1, boolean r2) {
        r0.lambda$setLoadingVideo$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$GrZ_xZ3ksWbfLKuEi6tUd1wTTTs(FileLoader r0) {
        r0.lambda$cancel$10();
    }

    public static /* synthetic */ void $r8$lambda$KA67kCAIDOxJDUdMEbQ1hkojpxE(FileLoader r0, FileLoaderPriorityQueue r1, FileLoadOperation r2) {
        r0.lambda$checkDownloadQueue$15(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$L8ftmeidbtRGx3JPN5LZ3bVZO6A(FileLoader r0, String r1) {
        r0.lambda$cancel$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$VWYVSuRExIoFYraf6h1d_9REt8E(FileLoader r0, boolean r1, String r2) {
        r0.lambda$cancelFileUpload$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$aznnlSjB__e8IovJEzDI8QCbiag(ArrayList r6, int r7) {
        int r0 = 0;
    L4:
        if (r0 >= r6.size()) goto L37;
        File r1 = (File) r6.get(r0);
        File r2 = new File(r1.getAbsolutePath() + ".enc");
        if (r2.exists() == false) goto L20;
        if (r2.delete() == true) goto L46;
        r2.deleteOnExit();     // Catch: Exception -> L11
    L46:
        File r22 = new File(getInternalCacheDir(), r1.getName() + ".enc.key");     // Catch: Exception -> L17
        if (r22.delete() == true) goto L40;
        r22.deleteOnExit();     // Catch: Exception -> L17
    L40:
        File r23 = new File(r1.getParentFile(), "q_" + r1.getName());     // Catch: Exception -> L33
        if (r23.exists() == false) goto L35;
        if (r23.delete() == true) goto L35;
        r23.deleteOnExit();     // Catch: Exception -> L33
    L35:
        r0 = r0 + 1;
    L33:
        e = move-exception;
        FileLog.e(e);
    L17:
        e = move-exception;
        FileLog.e(e);
    L11:
        e = move-exception;
        FileLog.e(e);
        goto L46
    L20:
        if (r1.exists() == false) goto L40;
        if (r1.delete() == true) goto L40;
        r1.deleteOnExit();     // Catch: Exception -> L25
    L25:
        e = move-exception;
        FileLog.e(e);
        goto L40
    L37:
        if (r7 != 2) goto L53;
        ImageLoader.getInstance().clearMemory();
        return;
    }

    public static /* synthetic */ void $r8$lambda$bUVGRvdiNP5wXjEf43X7rWqjr2U(FileLoader r0, boolean r1, String r2, long r3, int r5, boolean r6, boolean r7) {
        r0.lambda$uploadFile$5(r1, r2, r3, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$cdRKzIFkeFI6qhXp2qTwwNKNYHU(FileLoader r0, TLRPC.FileLocation r1, String r2) {
        r0.lambda$setForceStreamLoadingFile$6(r1, r2);
    }

    /* renamed from: $r8$lambda$gH_-Ezy7T7v6EfvzbadPMowo2-c, reason: not valid java name */
    public static /* synthetic */ void m4033$r8$lambda$gH_Ezy7T7v6EfvzbadPMowo2c(FileLoader r0, String r1) {
        r0.lambda$cancelLoadFile$7(r1);
    }

    /* renamed from: $r8$lambda$gKJsZClCE1qk51wAobZ3v-hvknU, reason: not valid java name */
    public static /* synthetic */ void m4034$r8$lambda$gKJsZClCE1qk51wAobZ3vhvknU(String r1, Utilities.Callback r2, Runnable r3, int r4, int r5, Object[] r6) {
        if (r4 != NotificationCenter.fileUploaded) goto L9;
        if (r6[0] != r1) goto L14;
        r2.run((TLRPC.InputFile) r6[1]);
        r3.run();
        return;
    L14:
        return;
    L9:
        if (r4 == NotificationCenter.fileUploadFailed) goto L11;
        return;
    L11:
        if (r6[0] != r1) goto L16;
        r2.run(null);
        r3.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$i4OvadzAbHwIFJ_692SihZzn3xQ(FileLoader r0, FileLoadOperation[] r1, TLRPC.Document r2, ImageLocation r3, Object r4, int r5, FileLoadOperationStream r6, long r7, boolean r9, int r10, CountDownLatch r11) {
        r0.lambda$loadStreamFile$14(r1, r2, r3, r4, r5, r6, r7, r9, r10, r11);
    }

    /* renamed from: $r8$lambda$iE-JrXQhQtTi7LWnfGvoQyovlWc, reason: not valid java name */
    public static /* synthetic */ void m4035$r8$lambda$iEJrXQhQtTi7LWnfGvoQyovlWc(FileLoader r0) {
        r0.lambda$new$18();
    }

    /* renamed from: $r8$lambda$mP-9lr0l0Zh1E3j5BLb41Lrxcp0, reason: not valid java name */
    public static /* synthetic */ void m4036$r8$lambda$mP9lr0l0Zh1E3j5BLb41Lrxcp0(FileLoader r0, String r1) {
        r0.lambda$cancelLoadAllFiles$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$merNVy9FeGkLCE3LdIqzXy4We34(FileLoader r0) {
        r0.lambda$cancelLoadFile$8();
    }

    public static /* synthetic */ void $r8$lambda$t5SvuW1z9prKHsZ6Im77fxRdDH8(FileLoader r0, NotificationCenter.NotificationCenterDelegate[] r1) {
        r0.lambda$uploadFile$19(r1);
    }

    public static /* synthetic */ void $r8$lambda$xFHfaeWJjbzVZoC0OBemLH9Ntc8(FileLoader r0, TLRPC.Document r1, boolean r2) {
        r0.lambda$removeLoadingVideo$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ywkSOEBo5hfrIXHky8r0jthQWIM(FileLoader r0, ArrayList r1) {
        r0.lambda$checkCurrentDownloadsFiles$17(r1);
    }

    /* renamed from: -$$Nest$fgetcurrentUploadOperationsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m4037$$Nest$fgetcurrentUploadOperationsCount(FileLoader r0) {
        return r0.currentUploadOperationsCount;
    }

    /* renamed from: -$$Nest$fgetcurrentUploadSmallOperationsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m4038$$Nest$fgetcurrentUploadSmallOperationsCount(FileLoader r0) {
        return r0.currentUploadSmallOperationsCount;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FileLoaderDelegate m4039$$Nest$fgetdelegate(FileLoader r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetloadOperationPathsUI, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ConcurrentHashMap m4040$$Nest$fgetloadOperationPathsUI(FileLoader r0) {
        return r0.loadOperationPathsUI;
    }

    /* renamed from: -$$Nest$fgetuploadOperationPaths, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ConcurrentHashMap m4041$$Nest$fgetuploadOperationPaths(FileLoader r0) {
        return r0.uploadOperationPaths;
    }

    /* renamed from: -$$Nest$fgetuploadOperationPathsEnc, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ConcurrentHashMap m4042$$Nest$fgetuploadOperationPathsEnc(FileLoader r0) {
        return r0.uploadOperationPathsEnc;
    }

    /* renamed from: -$$Nest$fgetuploadOperationQueue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinkedList m4043$$Nest$fgetuploadOperationQueue(FileLoader r0) {
        return r0.uploadOperationQueue;
    }

    /* renamed from: -$$Nest$fgetuploadSmallOperationQueue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinkedList m4044$$Nest$fgetuploadSmallOperationQueue(FileLoader r0) {
        return r0.uploadSmallOperationQueue;
    }

    /* renamed from: -$$Nest$fputcurrentUploadOperationsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4045$$Nest$fputcurrentUploadOperationsCount(FileLoader r0, int r1) {
        r0.currentUploadOperationsCount = r1;
    }

    /* renamed from: -$$Nest$fputcurrentUploadSmallOperationsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4046$$Nest$fputcurrentUploadSmallOperationsCount(FileLoader r0, int r1) {
        r0.currentUploadSmallOperationsCount = r1;
    }

    /* renamed from: -$$Nest$mcheckDownloadQueue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4047$$Nest$mcheckDownloadQueue(FileLoader r0, FileLoadOperation r1, FileLoaderPriorityQueue r2) {
        r0.checkDownloadQueue(r1, r2);
    }

    /* renamed from: -$$Nest$mcheckDownloadQueue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4048$$Nest$mcheckDownloadQueue(FileLoader r0, FileLoadOperation r1, FileLoaderPriorityQueue r2, long r3) {
        r0.checkDownloadQueue(r1, r2, r3);
    }

    /* renamed from: -$$Nest$sfgetfileLoaderQueue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DispatchQueue m4049$$Nest$sfgetfileLoaderQueue() {
        return fileLoaderQueue;
    }

    /* renamed from: -$$Nest$smisOwnMediaDocument, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m4050$$Nest$smisOwnMediaDocument(MessageObject r0, TLRPC.Document r1) {
        return isOwnMediaDocument(r0, r1);
    }

    static {
        fileLoaderQueue = new DispatchQueue("fileUploadQueue");
        mediaDirs = null;
        Instance = new FileLoader[16];
    }

    public FileLoader(int r9) {
        super(r9);
        this.smallFilesQueue = new FileLoaderPriorityQueue[5];
        this.largeFilesQueue = new FileLoaderPriorityQueue[5];
        this.uploadOperationQueue = new LinkedList();
        this.uploadSmallOperationQueue = new LinkedList();
        this.uploadOperationPaths = new ConcurrentHashMap();
        this.uploadOperationPathsEnc = new ConcurrentHashMap();
        this.currentUploadOperationsCount = 0;
        this.currentUploadSmallOperationsCount = 0;
        this.loadOperationPaths = new ConcurrentHashMap();
        this.loadOperationPathsUI = new ConcurrentHashMap(10, 1.0f, 2);
        this.uploadSizes = new HashMap();
        this.loadingVideos = new HashMap();
        this.delegate = null;
        this.parentObjectReferences = new ConcurrentHashMap();
        this.dumpFilesQueueRunnable = new FileLoader$$ExternalSyntheticLambda10(this);
        this.filePathDatabase = new FilePathDatabase(r9);
        int r1 = 0;
    L3:
        FileLoaderPriorityQueue[] r2 = this.smallFilesQueue;
        if (r1 >= r2.length) goto L6;
        StringBuilder r4 = new StringBuilder("smallFilesQueue dc");
        int r5 = r1 + 1;
        r4.append(r5);
        r2[r1] = new FileLoaderPriorityQueue(r9, r4.toString(), 0, fileLoaderQueue);
        this.largeFilesQueue[r1] = new FileLoaderPriorityQueue(r9, "largeFilesQueue dc" + r5, 1, fileLoaderQueue);
        r1 = r5;
        goto L3
    L6:
        dumpFilesQueue();
    }

    private void addOperationToQueue(FileLoadOperation r5, LinkedList<FileLoadOperation> r6) {
        int r4 = r5.getPriority();
        if (r4 <= 0) goto L12;
        int r0 = r6.size();
        int r1 = r6.size();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L10;
        if (r6.get(r2).getPriority() < r4) goto L8;
        r2 = r2 + 1;
        goto L5
    L8:
        r0 = r2;
    L10:
        r6.add(r0, r5);
        return;
    L12:
        r6.add(r5);
    }

    private void awaitFileLoadOperation(CountDownLatch r1, boolean r2) {
        r1.await();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    public static long bytesToLong(byte[] r5) {
        long r0 = 0;
        int r2 = 0;
    L4:
        if (r2 >= 8) goto L6;
        r0 = (r0 << 8) ^ (r5[r2] & UByte.MAX_VALUE);
        r2 = r2 + 1;
        goto L4
    L6:
        return r0;
    }

    public static boolean canSaveAsFile(Object r2) {
        if ((r2 instanceof MessageObject) == false) goto L13;
        MessageObject r22 = (MessageObject) r2;
        if (r22.isDocument() == false) goto L13;
        if (r22.isRoundVideo() == true) goto L13;
        if (r22.isVoice() == true) goto L13;
        return true;
    L13:
        return false;
    }

    private boolean canSaveToPublicStorage(Object r9) {
        if (BuildVars.NO_SCOPED_STORAGE == false) goto L5;
        return false;
    L5:
        FilePathDatabase.FileMeta r0 = getFileMetadataFromParent(this.currentAccount, r9);
        if (r0 == null) goto L39;
        long r2 = r0.dialogId;
        if (getMessagesController().isPeerNoForwards(r2) == true) goto L39;
        if (DialogObject.isEncryptedDialog(r2) == true) goto L39;
        int r5 = 2;
        if ((r9 instanceof MessageObject) == false) goto L23;
        MessageObject r92 = (MessageObject) r9;
        if (r92.isRoundVideo() == false) goto L17;
    L22:
        return false;
    L17:
        if (r92.isVoice() == true) goto L22;
        if (r92.isAnyKindOfSticker() == true) goto L22;
        if (r92.messageOwner.noforwards == true) goto L22;
    L31:
        if (r2 < 0) goto L34;
        r5 = 1;
    L37:
        if (SaveToGallerySettingsHelper.needSave(r5, r0, r92, this.currentAccount) == false) goto L39;
        return true;
    L34:
        if (ChatObject.isChannelAndNotMegaGroup(getMessagesController().getChat(Long.valueOf(-r2))) == false) goto L37;
        r5 = 4;
        goto L37
    L23:
        int r93 = r0.messageType;
        if (r93 == 5) goto L39;
        if (r93 == 13) goto L39;
        if (r93 == 2) goto L39;
        r92 = null;
    L39:
        return false;
    }

    private void cancelLoadFile(TLRPC.Document r1, SecureDocument r2, WebFile r3, TLRPC.FileLocation r4, String r5, String r6, boolean r7) {
        if (r4 != null) goto L9;
        if (r1 != null) goto L9;
        if (r3 != null) goto L9;
        if (r2 != null) goto L9;
        if (TextUtils.isEmpty(r6) == false) goto L9;
        return;
    L9:
        if (r4 == null) goto L11;
        r6 = getAttachFileName(r4, r5);
    L17:
        LoadOperationUIObject r22 = this.loadOperationPathsUI.remove(r6);
        if (r22 == null) goto L20;
        Runnable r32 = r22.loadInternalRunnable;
    L21:
        if (r22 == null) goto L23;
        boolean r23 = true;
    L24:
        if (r32 == null) goto L26;
        fileLoaderQueue.cancelRunnable(r32);
    L26:
        fileLoaderQueue.postRunnable(new FileLoader$$ExternalSyntheticLambda13(this, r6));
        if (r23 == false) goto L31;
        if (r1 == null) goto L32;
        AndroidUtilities.runOnUIThread(new FileLoader$$ExternalSyntheticLambda14(this));
        return;
    L32:
        return;
    L31:
        return;
    L23:
        r23 = false;
        goto L24
    L20:
        r32 = null;
        goto L21
    L11:
        if (r1 == null) goto L13;
        r6 = getAttachFileName(r1);
        goto L17
    L13:
        if (r2 == null) goto L15;
        r6 = getAttachFileName(r2);
        goto L17
    L15:
        if (r3 == null) goto L17;
        r6 = getAttachFileName(r3);
        goto L17
    }

    public static File checkDirectory(int r1) {
        return mediaDirs.get(r1);
    }

    private void checkDownloadQueue(FileLoadOperation r3, FileLoaderPriorityQueue r4) {
        checkDownloadQueue(r3, r4, 0);
    }

    private void checkDownloadQueue(FileLoadOperation r3, FileLoaderPriorityQueue r4, long r5) {
        fileLoaderQueue.postRunnable(new FileLoader$$ExternalSyntheticLambda15(this, r4, r3), r5);
    }

    public static boolean checkUploadFileSize(int r2, long r3) {
        boolean r22 = AccountInstance.getInstance(r2).getUserConfig().isPremium();
        if (r3 >= DEFAULT_MAX_FILE_SIZE) goto L5;
        return true;
    L5:
        if (r3 >= DEFAULT_MAX_FILE_SIZE_PREMIUM) goto L8;
        if (r22 == true) goto L13;
        return false;
    L13:
        return true;
    L8:
        return false;
    }

    public static boolean copyFile(InputStream r1, File r2) {
        return copyFile(r1, r2, -1);
    }

    public static boolean copyFile(InputStream r4, File r5, int r6) {
        FileOutputStream r0 = new FileOutputStream(r5);
        byte[] r52 = new byte[4096];
        int r2 = 0;
    L3:
        int r3 = r4.read(r52);
        if (r3 <= 0) goto L8;
        Thread.yield();
        r0.write(r52, 0, r3);
        r2 = r2 + r3;
        if (r6 <= 0) goto L3;
        if (r2 < r6) goto L3;
    L8:
        r0.getFD().sync();
        r0.close();
        return true;
    }

    public static String fixFileName(String r2) {
        if (r2 != null) goto L4;
        return r2;
    L4:
        return r2.replaceAll("[\u0001-\u001f<>\u202e:\"/\\\\|?*\u007f]+", _UrlKt.FRAGMENT_ENCODE_SET).trim();
    }

    public static String getAttachFileName(TLObject r1) {
        return getAttachFileName(r1, null);
    }

    public static String getAttachFileName(TLObject r1, String r2) {
        return getAttachFileName(r1, null, r2);
    }

    public static String getAttachFileName(TLObject r7, String r8, String r9) {
        boolean r0 = r7 instanceof TLRPC.Document;
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r0 == false) goto L18;
        TLRPC.Document r72 = (TLRPC.Document) r7;
        String r82 = getDocumentFileName(r72);
        int r92 = r82.lastIndexOf(46);
        if (r92 == (-1)) goto L9;
        r1 = r82.substring(r92);
    L9:
        if (r1.length() > 1) goto L11;
        r1 = getExtensionByMimeType(r72.mime_type);
    L11:
        int r83 = r1.length();
        int r02 = r72.dc_id;
        if (r83 <= 1) goto L16;
        return r02 + "_" + r72.id + r1;
    L16:
        return r02 + "_" + r72.id;
    L18:
        if ((r7 instanceof SecureDocument) == false) goto L22;
        SecureDocument r73 = (SecureDocument) r7;
        return r73.secureFile.dc_id + "_" + r73.secureFile.id + ".jpg";
    L22:
        if ((r7 instanceof TLRPC.TL_secureFile) == false) goto L26;
        TLRPC.TL_secureFile r74 = (TLRPC.TL_secureFile) r7;
        return r74.dc_id + "_" + r74.id + ".jpg";
    L26:
        if ((r7 instanceof WebFile) == false) goto L30;
        WebFile r75 = (WebFile) r7;
        return Utilities.MD5(r75.url) + "." + ImageLoader.getHttpUrlExtension(r75.url, getMimeTypePart(r75.mime_type));
    L30:
        if ((r7 instanceof TLRPC.PhotoSize) == false) goto L44;
        TLRPC.PhotoSize r76 = (TLRPC.PhotoSize) r7;
        TLRPC.FileLocation r84 = r76.location;
        if (r84 != null) goto L34;
    L42:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L34:
        if ((r84 instanceof TLRPC.TL_fileLocationUnavailable) == true) goto L42;
        StringBuilder r85 = new StringBuilder();
        r85.append(r76.location.volume_id);
        r85.append("_");
        r85.append(r76.location.local_id);
        r85.append(".");
        if (r9 != null) goto L40;
        r9 = "jpg";
    L40:
        r85.append(r9);
        return r85.toString();
    L44:
        if ((r7 instanceof TLRPC.TL_videoSize) == false) goto L58;
        TLRPC.TL_videoSize r77 = (TLRPC.TL_videoSize) r7;
        TLRPC.FileLocation r86 = r77.location;
        if (r86 != null) goto L48;
    L56:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L48:
        if ((r86 instanceof TLRPC.TL_fileLocationUnavailable) == true) goto L56;
        StringBuilder r87 = new StringBuilder();
        r87.append(r77.location.volume_id);
        r87.append("_");
        r87.append(r77.location.local_id);
        r87.append(".");
        if (r9 != null) goto L54;
        r9 = "mp4";
    L54:
        r87.append(r9);
        return r87.toString();
    L58:
        if ((r7 instanceof TLRPC.FileLocation) == false) goto L69;
        if ((r7 instanceof TLRPC.TL_fileLocationUnavailable) == false) goto L62;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L62:
        TLRPC.FileLocation r78 = (TLRPC.FileLocation) r7;
        StringBuilder r88 = new StringBuilder();
        r88.append(r78.volume_id);
        r88.append("_");
        r88.append(r78.local_id);
        r88.append(".");
        if (r9 != null) goto L66;
        r9 = "jpg";
    L66:
        r88.append(r9);
        return r88.toString();
    L69:
        if ((r7 instanceof TLRPC.UserProfilePhoto) == false) goto L87;
        if (r8 != null) goto L72;
        r8 = "s";
    L72:
        TLRPC.UserProfilePhoto r79 = (TLRPC.UserProfilePhoto) r7;
        if (r79.photo_small != null) goto L75;
        StringBuilder r03 = new StringBuilder();
        r03.append(r79.photo_id);
        r03.append("_");
        r03.append(r8);
        r03.append(".");
        if (r9 != null) goto L84;
        r9 = "jpg";
    L84:
        r03.append(r9);
        return r03.toString();
    L75:
        if ("s".equals(r8) == false) goto L79;
        return getAttachFileName(r79.photo_small, r9);
    L79:
        return getAttachFileName(r79.photo_big, r9);
    L87:
        if ((r7 instanceof TLRPC.ChatPhoto) == false) goto L102;
        TLRPC.ChatPhoto r710 = (TLRPC.ChatPhoto) r7;
        if (r710.photo_small != null) goto L91;
        StringBuilder r04 = new StringBuilder();
        r04.append(r710.photo_id);
        r04.append("_");
        r04.append(r8);
        r04.append(".");
        if (r9 != null) goto L100;
        r9 = "jpg";
    L100:
        r04.append(r9);
        return r04.toString();
    L91:
        if ("s".equals(r8) == false) goto L95;
        return getAttachFileName(r710.photo_small, r9);
    L95:
        return getAttachFileName(r710.photo_big, r9);
    L102:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static TLRPC.PhotoSize getClosestPhotoSizeWithSize(ArrayList<TLRPC.PhotoSize> r1, int r2) {
        return getClosestPhotoSizeWithSize(r1, r2, false);
    }

    public static TLRPC.PhotoSize getClosestPhotoSizeWithSize(ArrayList<TLRPC.PhotoSize> r2, int r3, boolean r4) {
        return getClosestPhotoSizeWithSize(r2, r3, r4, null, false);
    }

    public static TLRPC.PhotoSize getClosestPhotoSizeWithSize(ArrayList<TLRPC.PhotoSize> r8, int r9, boolean r10, TLRPC.PhotoSize r11, boolean r12) {
        TLRPC.PhotoSize r0 = null;
        if (r8 != null) goto L5;
    L48:
        return r0;
    L5:
        if (r8.isEmpty() == true) goto L48;
        int r1 = 0;
        int r2 = 0;
    L9:
        if (r1 >= r8.size()) goto L48;
        TLRPC.PhotoSize r3 = r8.get(r1);
        if (r3 == null) goto L47;
        if (r3 == r11) goto L47;
        if ((r3 instanceof TLRPC.TL_photoSizeEmpty) == true) goto L47;
        if ((r3 instanceof TLRPC.TL_photoPathSize) == true) goto L47;
        if (r12 == false) goto L22;
        if ((r3 instanceof TLRPC.TL_photoStrippedSize) == true) goto L47;
    L22:
        if (r10 == false) goto L35;
        int r6 = Math.min(r3.h, r3.w);
        if (r0 == null) goto L46;
        if (r9 <= 100) goto L31;
        TLRPC.FileLocation r5 = r0.location;
        if (r5 == null) goto L31;
        if (r5.dc_id == Integer.MIN_VALUE) goto L46;
    L31:
        if ((r3 instanceof TLRPC.TL_photoCachedSize) == true) goto L46;
        if (r9 <= r2) goto L47;
        if (r2 >= r6) goto L47;
    L46:
        r0 = r3;
        r2 = r6;
        goto L47
    L35:
        r6 = Math.max(r3.w, r3.h);
        if (r0 == null) goto L46;
        if (r9 <= 100) goto L43;
        TLRPC.FileLocation r52 = r0.location;
        if (r52 == null) goto L43;
        if (r52.dc_id == Integer.MIN_VALUE) goto L46;
    L43:
        if ((r3 instanceof TLRPC.TL_photoCachedSize) == true) goto L46;
        if (r6 > r9) goto L47;
        if (r2 < r6) goto L46;
    L47:
        r1 = r1 + 1;
        goto L9
    }

    public static TLRPC.VideoSize getClosestVideoSizeWithSize(ArrayList<TLRPC.VideoSize> r1, int r2) {
        return getClosestVideoSizeWithSize(r1, r2, false);
    }

    public static TLRPC.VideoSize getClosestVideoSizeWithSize(ArrayList<TLRPC.VideoSize> r1, int r2, boolean r3) {
        return getClosestVideoSizeWithSize(r1, r2, r3, false);
    }

    public static TLRPC.VideoSize getClosestVideoSizeWithSize(ArrayList<TLRPC.VideoSize> r7, int r8, boolean r9, boolean r10) {
        TLRPC.VideoSize r102 = null;
        if (r7 != null) goto L5;
    L40:
        return r102;
    L5:
        if (r7.isEmpty() == true) goto L40;
        int r0 = 0;
        int r1 = 0;
    L9:
        if (r0 >= r7.size()) goto L40;
        TLRPC.VideoSize r2 = r7.get(r0);
        if (r2 == null) goto L39;
        if ((r2 instanceof TLRPC.TL_videoSizeEmojiMarkup) == true) goto L39;
        if ((r2 instanceof TLRPC.TL_videoSizeStickerMarkup) == true) goto L39;
        if (r9 == false) goto L29;
        int r5 = Math.min(r2.h, r2.w);
        if (r102 == null) goto L38;
        if (r8 <= 100) goto L26;
        TLRPC.FileLocation r4 = r102.location;
        if (r4 == null) goto L26;
        if (r4.dc_id == Integer.MIN_VALUE) goto L38;
    L26:
        if (r8 <= r1) goto L39;
        if (r1 >= r5) goto L39;
    L38:
        r102 = r2;
        r1 = r5;
        goto L39
    L29:
        r5 = Math.max(r2.w, r2.h);
        if (r102 == null) goto L38;
        if (r8 <= 100) goto L36;
        TLRPC.FileLocation r42 = r102.location;
        if (r42 == null) goto L36;
        if (r42.dc_id == Integer.MIN_VALUE) goto L38;
    L36:
        if (r5 > r8) goto L39;
        if (r1 < r5) goto L38;
    L39:
        r0 = r0 + 1;
        goto L9
    }

    public static File getDirectory(int r2) {
        File r0 = mediaDirs.get(r2);
        if (r0 != null) goto L8;
        if (r2 == 4) goto L8;
        r0 = mediaDirs.get(4);
    L8:
        if (BuildVars.NO_SCOPED_STORAGE == false) goto L13;
        if (r0 == null) goto L13;
        if (r0.isDirectory() == true) goto L13;
        r0.mkdirs();     // Catch: Exception -> L14
    L13:
        return r0;
    }

    public static String getDocumentExtension(TLRPC.Document r3) {
        String r0 = getDocumentFileName(r3);
        int r1 = r0.lastIndexOf(46);
        if (r1 == (-1)) goto L5;
        String r02 = r0.substring(r1 + 1);
    L6:
        if (r02 != null) goto L8;
    L9:
        r02 = r3.mime_type;
    L10:
        if (r02 != null) goto L13;
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        return r02.toUpperCase();
    L8:
        if (r02.length() != 0) goto L10;
    L5:
        r02 = null;
        goto L6
    }

    public static String getDocumentFileName(TLRPC.Document r4) {
        String r0 = null;
        if (r4 != null) goto L5;
        return null;
    L5:
        String r1 = r4.file_name_fixed;
        if (r1 == null) goto L8;
        return r1;
    L8:
        String r12 = r4.file_name;
        if (r12 != null) goto L19;
        int r13 = 0;
    L13:
        if (r13 >= r4.attributes.size()) goto L18;
        TLRPC.DocumentAttribute r2 = r4.attributes.get(r13);
        if ((r2 instanceof TLRPC.TL_documentAttributeFilename) == false) goto L17;
        r0 = r2.file_name;
    L17:
        r13 = r13 + 1;
        goto L13
    L18:
        r12 = r0;
    L19:
        String r42 = fixFileName(r12);
        if (r42 == null) goto L22;
        return r42;
    L22:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static TLRPC.VideoSize getEmojiMarkup(ArrayList<TLRPC.VideoSize> r2) {
        int r0 = 0;
    L4:
        if (r0 >= r2.size()) goto L13;
        if ((r2.get(r0) instanceof TLRPC.TL_videoSizeEmojiMarkup) == true) goto L12;
        if ((r2.get(r0) instanceof TLRPC.TL_videoSizeStickerMarkup) == true) goto L12;
        r0 = r0 + 1;
    L12:
        return r2.get(r0);
    L13:
        return null;
    }

    public static String getExtensionByMimeType(String r2) {
        if (r2 == null) goto L26;
        char r1 = 65535;
        switch(r2.hashCode()) {
            case 187091926: goto L15;
            case 1331848029: goto L11;
            case 2039520277: goto L7;
            default: goto L18;
        };
    L18:
        switch(r1) {
            case 0: goto L24;
            case 1: goto L22;
            case 2: goto L20;
            default: goto L28;
        };
    L20:
        return ".mkv";
    L22:
        return ".mp4";
    L24:
        return ".ogg";
    L28:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        if (r2.equals("video/x-matroska") == false) goto L18;
        r1 = 2;
        goto L18
    L11:
        if (r2.equals("video/mp4") == false) goto L18;
        r1 = 1;
        goto L18
    L15:
        if (r2.equals("audio/ogg") == false) goto L18;
        r1 = 0;
        goto L18
    L26:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static String getFileExtension(File r1) {
        String r12 = r1.getName();
        return r12.substring(r12.lastIndexOf(46) + 1);
    L5:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static FilePathDatabase.FileMeta getFileMetadataFromParent(int r2, Object r3) {
        if ((r3 instanceof String) == false) goto L16;
        String r32 = (String) r3;
        if (r32.startsWith("sent_") == true) goto L7;
        return null;
    L7:
        if (sentPattern != null) goto L25;
        sentPattern = Pattern.compile("sent_.*_([0-9]+)_([0-9]+)_([0-9]+)_([0-9]+)");
    L25:
        Matcher r22 = sentPattern.matcher(r32);     // Catch: Exception -> L13
        if (r22.matches() == false) goto L27;
        FilePathDatabase.FileMeta r33 = new FilePathDatabase.FileMeta();     // Catch: Exception -> L13
        r33.messageId = Integer.parseInt(r22.group(1));     // Catch: Exception -> L13
        r33.dialogId = Long.parseLong(r22.group(2));     // Catch: Exception -> L13
        r33.messageType = Integer.parseInt(r22.group(3));     // Catch: Exception -> L13
        r33.messageSize = Long.parseLong(r22.group(4));     // Catch: Exception -> L13
        return r33;
    L27:
        return null;
    L13:
        e = move-exception;
        FileLog.e(e);
        return null;
    L16:
        if ((r3 instanceof MessageObject) == false) goto L20;
        MessageObject r34 = (MessageObject) r3;
        FilePathDatabase.FileMeta r23 = new FilePathDatabase.FileMeta();
        r23.messageId = r34.getId();
        r23.dialogId = r34.getDialogId();
        r23.messageType = r34.type;
        r23.messageSize = r34.getSize();
        return r23;
    L20:
        if ((r3 instanceof TL_stories.StoryItem) == false) goto L29;
        FilePathDatabase.FileMeta r24 = new FilePathDatabase.FileMeta();
        r24.dialogId = ((TL_stories.StoryItem) r3).dialogId;
        r24.messageType = 23;
        return r24;
    L29:
        return null;
    }

    public static FileLoader getInstance(int r3) {
        FileLoader[] r0 = Instance;
        FileLoader r1 = r0[r3];
        if (r1 == null) goto L5;
        return r1;
    L5:
        monitor-enter(FileLoader.class);
        FileLoader r12 = r0[r3];     // Catch: Throwable -> L9
        if (r12 != null) goto L11;
        r12 = new FileLoader(r3);     // Catch: Throwable -> L9
        r0[r3] = r12;     // Catch: Throwable -> L9
    L11:
        monitor-exit(FileLoader.class);     // Catch: Throwable -> L9
        return r12;
    L9:
        th = move-exception;
        throw th;
    }

    public static File getInternalCacheDir() {
        return ApplicationLoader.applicationContext.getCacheDir();
    }

    public static String getMessageFileName(TLRPC.Message r5) {
        if (r5 != null) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        if ((r5 instanceof TLRPC.TL_messageService) == false) goto L16;
        TLRPC.Photo r52 = r5.action.photo;
        if (r52 == null) goto L47;
        ArrayList<TLRPC.PhotoSize> r53 = r52.sizes;
        if (r53.size() <= 0) goto L47;
        TLRPC.PhotoSize r54 = getClosestPhotoSizeWithSize(r53, AndroidUtilities.getPhotoSize());
        if (r54 == null) goto L47;
        return getAttachFileName(r54);
    L47:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L16:
        if ((MessageObject.getMedia(r5) instanceof TLRPC.TL_messageMediaDocument) == false) goto L20;
        return getAttachFileName(MessageObject.getMedia(r5).document);
    L20:
        if ((MessageObject.getMedia(r5) instanceof TLRPC.TL_messageMediaPhoto) == false) goto L28;
        ArrayList<TLRPC.PhotoSize> r55 = MessageObject.getMedia(r5).photo.sizes;
        if (r55.size() <= 0) goto L47;
        TLRPC.PhotoSize r56 = getClosestPhotoSizeWithSize(r55, AndroidUtilities.getPhotoSize(true), false, null, true);
        if (r56 == null) goto L47;
        return getAttachFileName(r56);
    L28:
        if ((MessageObject.getMedia(r5) instanceof TLRPC.TL_messageMediaWebPage) == false) goto L42;
        if (MessageObject.getMedia(r5).webpage.document == null) goto L34;
        return getAttachFileName(MessageObject.getMedia(r5).webpage.document);
    L34:
        if (MessageObject.getMedia(r5).webpage.photo == null) goto L47;
        ArrayList<TLRPC.PhotoSize> r57 = MessageObject.getMedia(r5).webpage.photo.sizes;
        if (r57.size() <= 0) goto L47;
        TLRPC.PhotoSize r58 = getClosestPhotoSizeWithSize(r57, AndroidUtilities.getPhotoSize());
        if (r58 == null) goto L47;
        return getAttachFileName(r58);
    L42:
        if ((MessageObject.getMedia(r5) instanceof TLRPC.TL_messageMediaInvoice) == false) goto L47;
        TLRPC.WebDocument r59 = ((TLRPC.TL_messageMediaInvoice) MessageObject.getMedia(r5)).webPhoto;
        if (r59 == null) goto L47;
        return Utilities.MD5(r59.url) + "." + ImageLoader.getHttpUrlExtension(r59.url, getMimeTypePart(r59.mime_type));
    }

    public static String getMimeTypePart(String r2) {
        int r0 = r2.lastIndexOf(47);
        if (r0 != (-1)) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return r2.substring(r0 + 1);
    }

    public static TLRPC.TL_photoPathSize getPathPhotoSize(ArrayList<TLRPC.PhotoSize> r4) {
        if (r4 != null) goto L5;
    L15:
        return null;
    L5:
        if (r4.isEmpty() == true) goto L15;
        int r1 = 0;
    L9:
        if (r1 >= r4.size()) goto L15;
        TLRPC.PhotoSize r2 = r4.get(r1);
        if ((r2 instanceof TLRPC.TL_photoPathSize) == false) goto L14;
        r1 = r1 + 1;
        goto L9
    L14:
        return (TLRPC.TL_photoPathSize) r2;
    }

    public static long getPhotoId(TLObject r2) {
        if ((r2 instanceof TLRPC.Photo) == false) goto L7;
        return ((TLRPC.Photo) r2).id;
    L7:
        if ((r2 instanceof TLRPC.ChatPhoto) == false) goto L11;
        return ((TLRPC.ChatPhoto) r2).photo_id;
    L11:
        if ((r2 instanceof TLRPC.UserProfilePhoto) == true) goto L13;
        return 0;
    L13:
        return ((TLRPC.UserProfilePhoto) r2).photo_id;
    }

    private int getPriorityValue(int r2) {
        if (r2 != 4) goto L7;
        return Integer.MAX_VALUE;
    L7:
        if (r2 != 3) goto L12;
        int r22 = this.priorityIncreasePointer;
        this.priorityIncreasePointer = r22 + 1;
        int r1 = 1048577;
    L10:
        return r22 + r1;
    L12:
        if (r2 != 2) goto L15;
        r22 = this.priorityIncreasePointer;
        this.priorityIncreasePointer = r22 + 1;
        r1 = 65537;
        goto L10
    L15:
        if (r2 != 1) goto L18;
        return 65536;
    L18:
        return 0;
    }

    public static TLRPC.TL_photoStrippedSize getStrippedPhotoSize(ArrayList<TLRPC.PhotoSize> r4) {
        if (r4 != null) goto L5;
        return null;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= r4.size()) goto L13;
        TLRPC.PhotoSize r2 = r4.get(r1);
        if ((r2 instanceof TLRPC.TL_photoStrippedSize) == true) goto L11;
        r1 = r1 + 1;
        goto L7
    L11:
        return (TLRPC.TL_photoStrippedSize) r2;
    L13:
        return null;
    }

    public static TLRPC.VideoSize getVectorMarkupVideoSize(TLRPC.Photo r4) {
        if (r4 != null) goto L5;
    L17:
        return null;
    L5:
        if (r4.video_sizes == null) goto L17;
        int r1 = 0;
    L9:
        if (r1 >= r4.video_sizes.size()) goto L17;
        TLRPC.VideoSize r2 = r4.video_sizes.get(r1);
        if ((r2 instanceof TLRPC.TL_videoSizeEmojiMarkup) == true) goto L16;
        if ((r2 instanceof TLRPC.TL_videoSizeStickerMarkup) == true) goto L16;
        r1 = r1 + 1;
    L16:
        return r2;
    }

    private static boolean isOwnMediaDocument(MessageObject r8, TLRPC.Document r9) {
        if (r8 == null) goto L24;
        if (r9 == null) goto L24;
        TLRPC.Document r1 = r8.getDocumentFast();
        if (r1 != null) goto L9;
    L12:
        TLRPC.MessageMedia r82 = MessageObject.getMedia(r8.messageOwner);
        if (r82 != null) goto L15;
        return false;
    L15:
        int r12 = r82.alt_documents.size();
        int r2 = 0;
    L16:
        if (r2 >= r12) goto L24;
        TLRPC.Document r3 = r82.alt_documents.get(r2);
        if (r3 == null) goto L23;
        if (r3.id != r9.id) goto L23;
        return isVideoQualityDocument(r3);
    L23:
        r2 = r2 + 1;
        goto L16
    L9:
        if (r1.id != r9.id) goto L12;
        return true;
    L24:
        return false;
    }

    public static boolean isSamePhoto(TLObject r4, TLObject r5) {
        if (r4 != null) goto L5;
        if (r5 == null) goto L5;
    L7:
        return false;
    L5:
        if (r4 == null) goto L9;
        if (r5 == null) goto L7;
    L9:
        if (r4 != null) goto L13;
        if (r5 != null) goto L13;
        return true;
    L13:
        if (r4.getClass() == r5.getClass()) goto L16;
        return false;
    L16:
        if ((r4 instanceof TLRPC.UserProfilePhoto) == false) goto L22;
        if (((TLRPC.UserProfilePhoto) r4).photo_id != ((TLRPC.UserProfilePhoto) r5).photo_id) goto L20;
        return true;
    L20:
        return false;
    L22:
        if ((r4 instanceof TLRPC.ChatPhoto) == true) goto L24;
    L26:
        return false;
    L24:
        if (((TLRPC.ChatPhoto) r4).photo_id != ((TLRPC.ChatPhoto) r5).photo_id) goto L26;
        return true;
    }

    public static boolean isSamePhoto(TLRPC.FileLocation r8, TLRPC.Photo r9) {
        if (r8 != null) goto L5;
    L21:
        return false;
    L5:
        if ((r9 instanceof TLRPC.TL_photo) == false) goto L21;
        int r1 = r9.sizes.size();
        int r2 = 0;
    L9:
        if (r2 >= r1) goto L19;
        TLRPC.FileLocation r4 = r9.sizes.get(r2).location;
        if (r4 == null) goto L17;
        if (r4.local_id != r8.local_id) goto L17;
        if (r4.volume_id != r8.volume_id) goto L17;
        return true;
    L17:
        r2 = r2 + 1;
        goto L9
    L19:
        if ((-r8.volume_id) != r9.id) goto L21;
        return true;
    }

    public static boolean isVideoMimeType(String r1) {
        if ("video/mp4".equals(r1) == false) goto L5;
        return true;
    L5:
        if (SharedConfig.streamMkv == true) goto L7;
        return false;
    L7:
        if ("video/x-matroska".equals(r1) == true) goto L14;
        return false;
    L14:
        return true;
    }

    private static boolean isVideoQualityDocument(TLRPC.Document r4) {
        int r0 = r4.attributes.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if ((r4.attributes.get(r2) instanceof TLRPC.TL_documentAttributeVideo) == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    private /* synthetic */ void lambda$cancel$10() {
        getNotificationCenter().postNotificationName(NotificationCenter.onDownloadingFilesChanged, new Object[0]);
    }

    private /* synthetic */ void lambda$cancel$9(String r1) {
        FileLoadOperation r0 = this.loadOperationPaths.remove(r1);
        if (r0 == null) goto L6;
        r0.getQueue().cancel(r0);
        return;
    }

    private /* synthetic */ void lambda$cancelFileUpload$2(boolean r2, String r3) {
        if (r2 == true) goto L4;
        FileUploadOperation r22 = this.uploadOperationPaths.get(r3);
    L5:
        this.uploadSizes.remove(r3);
        if (r22 == null) goto L9;
        this.uploadOperationPathsEnc.remove(r3);
        this.uploadOperationQueue.remove(r22);
        this.uploadSmallOperationQueue.remove(r22);
        r22.cancel();
        return;
    L9:
        return;
    L4:
        r22 = this.uploadOperationPathsEnc.get(r3);
        goto L5
    }

    private /* synthetic */ void lambda$cancelLoadAllFiles$12(String r1) {
        FileLoadOperation r0 = this.loadOperationPaths.remove(r1);
        if (r0 == null) goto L6;
        r0.getQueue().cancel(r0);
        return;
    }

    private /* synthetic */ void lambda$cancelLoadFile$7(String r1) {
        FileLoadOperation r0 = this.loadOperationPaths.remove(r1);
        if (r0 == null) goto L6;
        r0.getQueue().cancel(r0);
        return;
    }

    private /* synthetic */ void lambda$cancelLoadFile$8() {
        getNotificationCenter().postNotificationName(NotificationCenter.onDownloadingFilesChanged, new Object[0]);
    }

    private /* synthetic */ void lambda$changePriority$11(String r3, int r4) {
        FileLoadOperation r0 = this.loadOperationPaths.get(r3);
        if (r0 == null) goto L9;
        int r42 = getPriorityValue(r4);
        if (r0.getPriority() == r42) goto L10;
        r0.setPriority(r42);
        FileLoaderPriorityQueue r43 = r0.getQueue();
        r43.remove(r0);
        r43.add(r0);
        r43.checkLoadingOperations();
        FileLog.d("update priority " + r3 + " position in queue " + r0.getPositionInQueue() + " account=" + this.currentAccount);
        return;
    L10:
        return;
    }

    private /* synthetic */ void lambda$checkCurrentDownloadsFiles$17(ArrayList r2) {
        getDownloadController().recentDownloadingFiles.removeAll(r2);
        getNotificationCenter().postNotificationName(NotificationCenter.onDownloadingFilesChanged, new Object[0]);
    }

    private /* synthetic */ void lambda$checkDownloadQueue$15(FileLoaderPriorityQueue r2, FileLoadOperation r3) {
        if (r2.remove(r3) == false) goto L6;
        this.loadOperationPaths.remove(r3.getFileName());
        r2.checkLoadingOperations(r3.isStory);
        return;
    }

    private /* synthetic */ void lambda$checkUploadNewDataAvailable$3(boolean r7, String r8, long r9, long r11, Float r13) {
        if (r7 == false) goto L5;
        FileUploadOperation r72 = this.uploadOperationPathsEnc.get(r8);
    L4:
        FileUploadOperation r0 = r72;
        if (r0 == null) goto L10;
        r0.checkNewDataAvailable(r9, r11, r13);
        return;
    L10:
        if (r11 == 0) goto L13;
        this.uploadSizes.put(r8, Long.valueOf(r11));
        return;
    L13:
        return;
    L5:
        r72 = this.uploadOperationPaths.get(r8);
        goto L4
    }

    private /* synthetic */ void lambda$loadFile$13(TLRPC.Document r17, SecureDocument r18, WebFile r19, TLRPC.TL_fileLocationToBeDeprecated r20, ImageLocation r21, Object r22, String r23, long r24, int r26, int r27) {
        loadFileInternal(r17, r18, r19, r20, r21, r22, r23, r24, r26, null, 0, false, r27);
    }

    private /* synthetic */ void lambda$loadStreamFile$14(FileLoadOperation[] r18, TLRPC.Document r19, ImageLocation r20, Object r21, int r22, FileLoadOperationStream r23, long r24, boolean r26, int r27, CountDownLatch r28) {
        String r0 = null;
        if (r19 != null) goto L6;
        if (r20 == null) goto L6;
        TLRPC.TL_fileLocationToBeDeprecated r5 = r20.location;
    L7:
        if (r19 != null) goto L10;
        if (r20 == null) goto L10;
        r0 = "mp4";
    L10:
        String r8 = r0;
        if (r19 != null) goto L15;
        if (r20 == null) goto L15;
        long r02 = r20.currentSize;
    L16:
        r18[0] = loadFileInternal(r19, null, null, r5, r20, r21, r8, r02, r22, r23, r24, r26, r27);
        r28.countDown();
        return;
    L15:
        r02 = 0;
    L6:
        r5 = null;
        goto L7
    }

    private /* synthetic */ void lambda$new$18() {
        int r0 = 0;
    L3:
        FileLoaderPriorityQueue[] r1 = this.smallFilesQueue;
        if (r0 >= r1.length) goto L11;
        if (r1[r0].getCount() <= 0) goto L8;
    L9:
        FileLog.d("download queue: dc" + (r0 + 1) + " account=" + this.currentAccount + " small_operations=" + this.smallFilesQueue[r0].getCount() + " large_operations=" + this.largeFilesQueue[r0].getCount());
    L10:
        r0 = r0 + 1;
        goto L3
    L8:
        if (this.largeFilesQueue[r0].getCount() <= 0) goto L10;
    L11:
        dumpFilesQueue();
    }

    private /* synthetic */ void lambda$onNetworkChanged$4(boolean r3) {
        Iterator<Map.Entry<String, FileUploadOperation>> r0 = this.uploadOperationPaths.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().getValue().onNetworkChanged(r3);
        goto L4
    L6:
        Iterator<Map.Entry<String, FileUploadOperation>> r2 = this.uploadOperationPathsEnc.entrySet().iterator();
    L8:
        if (r2.hasNext() == false) goto L10;
        r2.next().getValue().onNetworkChanged(r3);
        goto L8
    }

    private /* synthetic */ void lambda$removeLoadingVideo$1(TLRPC.Document r1, boolean r2) {
        removeLoadingVideoInternal(r1, r2);
    }

    private /* synthetic */ void lambda$setForceStreamLoadingFile$6(TLRPC.FileLocation r1, String r2) {
        String r12 = getAttachFileName(r1, r2);
        this.forceLoadingFile = r12;
        FileLoadOperation r13 = this.loadOperationPaths.get(r12);
        if (r13 != null) goto L5;
        return;
    L5:
        if (r13.isPreloadVideoOperation() == false) goto L7;
        r13.setIsPreloadVideoOperation(false);
    L7:
        r13.setForceRequest(true);
        r13.setPriority(getPriorityValue(4));
        r13.getQueue().remove(r13);
        r13.getQueue().add(r13);
        r13.getQueue().checkLoadingOperations();
    }

    private /* synthetic */ void lambda$setLoadingVideo$0(TLRPC.Document r1, boolean r2) {
        setLoadingVideoInternal(r1, r2);
    }

    private /* synthetic */ void lambda$uploadFile$19(NotificationCenter.NotificationCenterDelegate[] r5) {
        getNotificationCenter().removeObserver(r5[0], NotificationCenter.fileUploaded);
        getNotificationCenter().removeObserver(r5[0], NotificationCenter.fileUploadFailed);
    }

    private /* synthetic */ void lambda$uploadFile$5(boolean r11, String r12, long r13, int r15, boolean r16, boolean r17) {
        if (r11 == false) goto L8;
        if (this.uploadOperationPathsEnc.containsKey(r12) == false) goto L11;
        return;
    L11:
        if (r13 != 0) goto L13;
    L15:
        long r5 = r13;
    L16:
        FileUploadOperation r1 = new FileUploadOperation(this.currentAccount, r12, r11, r5, r15);
        FileLoaderDelegate r2 = this.delegate;
        if (r2 == null) goto L20;
        if (r13 == 0) goto L20;
        r2.fileUploadProgressChanged(r1, r12, 0, r13, r11);
        r1 = r1;
    L20:
        if (r11 == false) goto L22;
        this.uploadOperationPathsEnc.put(r12, r1);
    L23:
        if (r16 == false) goto L25;
        r1.setForceSmallFile();
    L25:
        r1.setDelegate(new AnonymousClass1(this, r11, r12, r17));
        if (r17 == false) goto L33;
        int r3 = this.currentUploadSmallOperationsCount;
        if (r3 >= 1) goto L31;
        this.currentUploadSmallOperationsCount = r3 + 1;
        r1.start();
        return;
    L31:
        this.uploadSmallOperationQueue.add(r1);
        return;
    L33:
        int r32 = this.currentUploadOperationsCount;
        if (r32 >= 1) goto L37;
        this.currentUploadOperationsCount = r32 + 1;
        r1.start();
        return;
    L37:
        this.uploadOperationQueue.add(r1);
        return;
    L22:
        this.uploadOperationPaths.put(r12, r1);
        goto L23
    L13:
        if (this.uploadSizes.get(r12) == null) goto L15;
        this.uploadSizes.remove(r12);
        r5 = 0;
        goto L16
    L8:
        if (this.uploadOperationPaths.containsKey(r12) == false) goto L11;
    }

    private void loadFile(TLRPC.Document r16, SecureDocument r17, WebFile r18, TLRPC.TL_fileLocationToBeDeprecated r19, ImageLocation r20, Object r21, String r22, long r23, int r25, int r26) {
        if (r19 == null) goto L6;
        String r8 = r22;
        String r0 = getAttachFileName(r19, r8);
    L5:
        String r14 = r0;
    L12:
        FileLoader$$ExternalSyntheticLambda6 r02 = new FileLoader$$ExternalSyntheticLambda6(this, r16, r17, r18, r19, r20, r21, r8, r23, r25, r26);
        if (r26 != 10) goto L15;
    L19:
        fileLoaderQueue.postRunnable(r02);
        return;
    L15:
        if (TextUtils.isEmpty(r14) == true) goto L19;
        if (r14.contains("-2147483648") == true) goto L19;
        LoadOperationUIObject r2 = new LoadOperationUIObject(null);
        r2.loadInternalRunnable = r02;
        this.loadOperationPathsUI.put(r14, r2);
        goto L19
    L6:
        r8 = r22;
        if (r16 == null) goto L9;
        r0 = getAttachFileName(r16);
        goto L5
    L9:
        if (r18 == null) goto L11;
        r0 = getAttachFileName(r18);
        goto L5
    L11:
        r14 = null;
        goto L12
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v3, types: [boolean, int] */
    private FileLoadOperation loadFileInternal(TLRPC.Document r31, SecureDocument r32, WebFile r33, TLRPC.TL_fileLocationToBeDeprecated r34, ImageLocation r35, Object r36, String r37, long r38, int r40, FileLoadOperationStream r41, long r42, boolean r44, int r45) {
        if (r34 == null) goto L6;
        String r7 = r37;
        String r8 = getAttachFileName(r34, r7);
    L5:
        String r15 = r8;
    L14:
        if (r15 != null) goto L16;
    L163:
        return null;
    L16:
        if (r15.contains("-2147483648") == true) goto L163;
        if (r15.startsWith("0_0") == false) goto L23;
        FileLog.e(new RuntimeException("cant get hash from " + r31));
        return null;
    L23:
        if (r45 != 10) goto L25;
    L29:
        if (r31 != null) goto L31;
    L37:
        FileLoadOperation r5 = this.loadOperationPaths.get(r15);
        int r82 = getPriorityValue(r40);
        if (r5 != null) goto L40;
        File r20 = getDirectory(4);
        if (r32 == null) goto L59;
        int r23 = r82;
        int r12 = 0;
        int r27 = 0;
        int r28 = 3;
        long r25 = 0;
        ?? r0 = 1;
        FileLoadOperation r52 = new FileLoadOperation(r32);
    L90:
        int r2 = Utilities.clamp(r52.getDatacenterId() - r0, 4, r12);
        boolean r4 = r36 instanceof TL_stories.StoryItem;
        if (r52.totalBytesCount > 20971520) goto L96;
        if (r4 == true) goto L96;
        FileLoaderPriorityQueue r22 = this.smallFilesQueue[r2];
    L95:
        FileLoaderPriorityQueue r18 = r22;
        if (r45 == 0) goto L107;
        if (r45 == 10) goto L107;
        if (r4 == true) goto L107;
        if (r45 != 2) goto L105;
        r52.setEncryptFile(r0);
    L105:
        String r21 = r15;
        File r19 = r20;
        long r10 = r25;
        int r9 = r28;
    L144:
        String r17 = r15;
        FileLoadOperation r152 = r52;
        r152.setPaths(this.currentAccount, r17, r18, r19, r20, r21);
        if (r45 != 10) goto L147;
        r152.setIsPreloadVideoOperation(r0);
    L147:
        boolean r24 = r0;
        r152.setDelegate(new AnonymousClass2(this, r36, r31, r17, r9));
        this.loadOperationPaths.put(r17, r152);
        int r02 = r23;
        r152.setPriority(r02);
        if (r41 != null) goto L150;
        FileStreamLoadOperation r26 = FileStreamLoadOperation.allStreams.get(Long.valueOf(r10));
    L151:
        if (r26 == null) goto L153;
        r152.setStream(r26, r44, r42);
    L153:
        r18.add(r152);
        if (r152.isStory == true) goto L156;
    L158:
        boolean r92 = r12;
    L159:
        r18.checkLoadingOperations(r92);
        if (BuildVars.LOGS_ENABLED == false) goto L162;
        FileLog.d("create load operation fileName=" + r17 + " documentName=" + getDocumentFileName(r31) + " size=" + AndroidUtilities.formatFileSize(r152.totalBytesCount) + " position in queue " + r152.getPositionInQueue() + " account=" + this.currentAccount + " cacheType=" + r45 + " priority=" + r152.getPriority() + " stream=" + r26);
    L162:
        return r152;
    L156:
        if (r02 < 1048576) goto L158;
        r92 = r24 ? 1 : 0;
        goto L159
    L150:
        r26 = r41;
    L107:
        if (r25 == 0) goto L143;
        String r72 = getFileDatabase().getPath(r25, r27, r28, true);
        r10 = r25;
        int r83 = r27;
        r9 = r28;
        if (r72 == null) goto L113;
        File r29 = new File(r72);
        if (r29.exists() == false) goto L113;
        String r73 = r29.getName();
        File r210 = r29.getParentFile();
        int r16 = r0;
    L114:
        if (r16 != 0) goto L142;
        r210 = getDirectory(r9);
        if (r4 == false) goto L121;
        File r43 = getDirectory(6);
        if (r43 == null) goto L139;
    L119:
        r210 = r43;
        int r46 = r0;
    L120:
        r73 = r15;
    L140:
        if (r46 == 0) goto L142;
        r52.pathSaveData = new FilePathDatabase.PathData(r10, r83, r9);
    L139:
        r46 = r12;
        goto L120
    L121:
        if (r9 == 0) goto L125;
        if (r9 == 2) goto L125;
    L132:
        if (TextUtils.isEmpty(getDocumentFileName(r31)) == true) goto L139;
        if (canSaveAsFile(r36) == false) goto L139;
        String r47 = getDocumentFileName(r31);
        File r74 = getDirectory(5);
        if (r74 == null) goto L138;
        r210 = r74;
        r73 = r47;
        r46 = r0;
        goto L140
    L138:
        r73 = r47;
        r46 = r12;
    L125:
        if (canSaveToPublicStorage(r36) == false) goto L132;
        if (r9 != 0) goto L128;
        r43 = getDirectory(100);
    L129:
        if (r43 == null) goto L139;
    L128:
        r43 = getDirectory(101);
    L142:
        r19 = r210;
        r21 = r73;
    L113:
        r16 = r12;
        r73 = r15;
        r210 = r20;
        goto L114
    L143:
        r10 = r25;
        r9 = r28;
        r19 = getDirectory(r9);
        r21 = r15;
    L96:
        r22 = this.largeFilesQueue[r2];
        goto L95
    L59:
        if (r34 == null) goto L61;
        long r102 = r34.volume_id;
        r23 = r82;
        r12 = 0;
        r0 = 1;
        r27 = r34.dc_id + (r34.local_id << 16);
        r52 = new FileLoadOperation(r35, r36, r7, r38);
        r25 = r102;
        r28 = 0;
        goto L90
    L61:
        r23 = r82;
        r12 = 0;
        r0 = 1;
        r0 = 1;
        r0 = 1;
        r0 = 1;
        FileLoadOperation r53 = r5;
        if (r31 == null) goto L76;
        FileLoadOperation r54 = new FileLoadOperation(r31, r36);
        if (MessageObject.isVoiceDocument(r31) == false) goto L66;
        int r93 = 1;
        int r211 = 0;
        long r103 = 0;
    L71:
        if (MessageObject.isRoundVideoDocument(r31) == false) goto L75;
        r28 = r93;
        FileLoadOperation r55 = r54;
    L73:
        r27 = 0;
        FileLoadOperation r56 = r55;
    L74:
        r25 = 0;
        r52 = r56;
        goto L90
    L75:
        r27 = r211;
        r28 = r93;
        r25 = r103;
        r52 = r54;
        goto L90
    L66:
        boolean r212 = MessageObject.isVideoDocument(r31);
        long r75 = r31.id;
        if (r212 == false) goto L69;
        r103 = r75;
        r211 = r31.dc_id;
        r93 = 2;
        goto L71
    L69:
        r103 = r75;
        r211 = r31.dc_id;
        r93 = 3;
        goto L71
    L76:
        if (r33 == null) goto L79;
        FileLoadOperation r57 = new FileLoadOperation(this.currentAccount, r33);
        r53 = r57;
        if (r33.location != null) goto L79;
        if (MessageObject.isVoiceWebDocument(r33) == false) goto L84;
        r28 = 1;
        r55 = r57;
        goto L73
    L84:
        if (MessageObject.isVideoWebDocument(r33) == false) goto L87;
        r27 = 0;
        r25 = 0;
        r28 = 2;
        r52 = r57;
        goto L90
    L87:
        if (MessageObject.isImageWebDocument(r33) == false) goto L89;
        r27 = 0;
        r28 = 0;
        r56 = r57;
        goto L74
    L89:
        r27 = 0;
        r28 = 3;
        r56 = r57;
    L79:
        r27 = 0;
        r25 = 0;
        r28 = 4;
        r52 = r53;
        goto L90
    L40:
        if (r45 != 10) goto L42;
    L44:
        boolean r03 = false;
    L45:
        if (r82 <= 0) goto L47;
        boolean r1 = true;
    L48:
        r5.setForceRequest(r1);
        r5.setStream(r41, r44, r42);
        if (r5.getPriority() == r82) goto L51;
        r5.setPriority(r82);
        boolean r94 = true;
    L52:
        r5.getQueue().add(r5);
        r5.updateProgress();
        if (r94 == false) goto L55;
        r5.getQueue().checkLoadingOperations();
    L55:
        return r5;
    L51:
        r94 = r03;
        goto L52
    L47:
        r1 = r03;
        goto L48
    L42:
        if (r5.isPreloadVideoOperation() == false) goto L44;
        r03 = false;
        r5.setIsPreloadVideoOperation(false);
        goto L45
    L31:
        if ((r36 instanceof MessageObject) == false) goto L37;
        MessageObject r58 = (MessageObject) r36;
        if (r58.putInDownloadsStore == false) goto L37;
        if (r58.isAnyKindOfSticker() == true) goto L37;
        getDownloadController().startDownloadFile(r31, r58);
        goto L37
    L25:
        if (TextUtils.isEmpty(r15) == true) goto L29;
        if (r15.contains("-2147483648") == true) goto L29;
        this.loadOperationPathsUI.put(r15, new LoadOperationUIObject(null));
        goto L29
    L6:
        r7 = r37;
        if (r32 == null) goto L9;
        r8 = getAttachFileName(r32);
        goto L5
    L9:
        if (r31 == null) goto L11;
        r8 = getAttachFileName(r31);
        goto L5
    L11:
        if (r33 == null) goto L13;
        r8 = getAttachFileName(r33);
        goto L5
    L13:
        r15 = null;
        goto L14
    }

    public static byte[] longToBytes(long r1) {
        ByteBuffer r0 = ByteBuffer.allocate(8);
        r0.putLong(r1);
        return r0.array();
    }

    private void removeLoadingVideoInternal(TLRPC.Document r2, boolean r3) {
        String r22 = getAttachFileName(r2);
        StringBuilder r0 = new StringBuilder();
        r0.append(r22);
        if (r3 == false) goto L5;
        String r32 = "p";
    L6:
        r0.append(r32);
        String r33 = r0.toString();
        if (this.loadingVideos.remove(r33) == null) goto L10;
        getNotificationCenter().postNotificationName(NotificationCenter.videoLoadingStateChanged, new Object[]{r22});
        return;
    L10:
        return;
    L5:
        r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    public static void setMediaDirs(SparseArray<File> r0) {
        mediaDirs = r0;
    }

    public void cancel(FileLoadOperation r4) {
        if (r4 == null) goto L18;
        String r42 = r4.getFileName();
        LoadOperationUIObject r0 = this.loadOperationPathsUI.remove(r42);
        if (r0 == null) goto L7;
        Runnable r1 = r0.loadInternalRunnable;
    L8:
        if (r0 == null) goto L10;
        boolean r02 = true;
    L11:
        if (r1 == null) goto L13;
        fileLoaderQueue.cancelRunnable(r1);
    L13:
        fileLoaderQueue.postRunnable(new FileLoader$$ExternalSyntheticLambda16(this, r42));
        if (r02 == false) goto L17;
        AndroidUtilities.runOnUIThread(new FileLoader$$ExternalSyntheticLambda17(this));
        return;
    L17:
        return;
    L10:
        r02 = false;
        goto L11
    L7:
        r1 = null;
        goto L8
    }

    public void cancelFileUpload(String r3, boolean r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        fileLoaderQueue.postRunnable(new FileLoader$$ExternalSyntheticLambda11(this, r4, r3));
    }

    public void cancelLoadAllFiles() {
        Iterator<String> r0 = this.loadOperationPathsUI.keySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L12;
        String r1 = r0.next();
        LoadOperationUIObject r2 = this.loadOperationPathsUI.get(r1);
        if (r2 == null) goto L8;
        Runnable r22 = r2.loadInternalRunnable;
    L9:
        if (r22 == null) goto L11;
        fileLoaderQueue.cancelRunnable(r22);
    L11:
        fileLoaderQueue.postRunnable(new FileLoader$$ExternalSyntheticLambda8(this, r1));
        goto L4
    L8:
        r22 = null;
        goto L9
    }

    public void cancelLoadFile(String r9) {
        cancelLoadFile(null, null, null, null, null, r9, true);
    }

    public void cancelLoadFile(SecureDocument r9) {
        cancelLoadFile(null, r9, null, null, null, null, false);
    }

    public void cancelLoadFile(WebFile r9) {
        cancelLoadFile(null, null, r9, null, null, null, false);
    }

    public void cancelLoadFile(TLRPC.Document r2) {
        cancelLoadFile(r2, false);
    }

    public void cancelLoadFile(TLRPC.Document r9, boolean r10) {
        cancelLoadFile(r9, null, null, null, null, null, r10);
    }

    public void cancelLoadFile(TLRPC.FileLocation r2, String r3) {
        cancelLoadFile(r2, r3, false);
    }

    public void cancelLoadFile(TLRPC.FileLocation r9, String r10, boolean r11) {
        cancelLoadFile(null, null, null, r9, r10, null, r11);
    }

    public void cancelLoadFile(TLRPC.PhotoSize r2) {
        cancelLoadFile(r2, false);
    }

    public void cancelLoadFile(TLRPC.PhotoSize r9, boolean r10) {
        cancelLoadFile(null, null, null, r9.location, null, null, r10);
    }

    public void cancelLoadFiles(ArrayList<String> r12) {
        int r0 = r12.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        cancelLoadFile(null, null, null, null, null, r12.get(r1), true);
        r1 = r1 + 1;
        goto L3
    }

    public void changePriority(int r2, TLRPC.Document r3, SecureDocument r4, WebFile r5, TLRPC.FileLocation r6, String r7, String r8) {
        if (r6 != null) goto L9;
        if (r3 != null) goto L9;
        if (r5 != null) goto L9;
        if (r4 != null) goto L9;
        if (TextUtils.isEmpty(r8) == false) goto L9;
        return;
    L9:
        if (r6 == null) goto L11;
        r8 = getAttachFileName(r6, r7);
    L17:
        fileLoaderQueue.postRunnable(new FileLoader$$ExternalSyntheticLambda5(this, r8, r2));
        return;
    L11:
        if (r3 == null) goto L13;
        r8 = getAttachFileName(r3);
        goto L17
    L13:
        if (r4 == null) goto L15;
        r8 = getAttachFileName(r4);
        goto L17
    L15:
        if (r5 == null) goto L17;
        r8 = getAttachFileName(r5);
        goto L17
    }

    public void checkCurrentDownloadsFiles() {
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList(getDownloadController().recentDownloadingFiles);
        int r2 = 0;
    L4:
        if (r2 >= r1.size()) goto L10;
        ((MessageObject) r1.get(r2)).checkMediaExistance();
        if (((MessageObject) r1.get(r2)).mediaExists == false) goto L8;
        r0.add((MessageObject) r1.get(r2));
    L8:
        r2 = r2 + 1;
        goto L4
    L10:
        if (r0.isEmpty() == true) goto L16;
        AndroidUtilities.runOnUIThread(new FileLoader$$ExternalSyntheticLambda3(this, r0));
        return;
    }

    public boolean checkLoadCaughtPremiumFloodWait(String r2) {
        if (r2 != null) goto L5;
        return false;
    L5:
        FileLoadOperation r1 = this.loadOperationPaths.get(r2);
        if (r1 != null) goto L8;
    L11:
        return false;
    L8:
        if (r1.caughtPremiumFloodWait == false) goto L11;
        r1.caughtPremiumFloodWait = false;
        return true;
    }

    public void checkMediaExistance(ArrayList<MessageObject> r1) {
        getFileDatabase().checkMediaExistance(r1);
    }

    public boolean checkUploadCaughtPremiumFloodWait(String r2) {
        if (r2 != null) goto L5;
        return false;
    L5:
        FileUploadOperation r1 = this.uploadOperationPaths.get(r2);
        if (r1 != null) goto L8;
    L11:
        return false;
    L8:
        if (r1.caughtPremiumFloodWait == false) goto L11;
        r1.caughtPremiumFloodWait = false;
        return true;
    }

    public void checkUploadNewDataAvailable(String r9, boolean r10, long r11, long r13) {
        checkUploadNewDataAvailable(r9, r10, r11, r13, null);
    }

    public void checkUploadNewDataAvailable(String r11, boolean r12, long r13, long r15, Float r17) {
        fileLoaderQueue.postRunnable(new FileLoader$$ExternalSyntheticLambda9(this, r12, r11, r13, r15, r17));
    }

    public void clearFilePaths() {
        this.filePathDatabase.clear();
    }

    public void clearRecentDownloadedFiles() {
        getDownloadController().clearRecentDownloadedFiles();
    }

    public void deleteFiles(ArrayList<File> r2, int r3) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isEmpty() == true) goto L9;
        fileLoaderQueue.postRunnable(new FileLoader$$ExternalSyntheticLambda4(r2, r3));
        return;
    }

    public void dumpFilesQueue() {
        if (BuildVars.LOGS_ENABLED == true) goto L5;
        return;
    L5:
        fileLoaderQueue.cancelRunnable(this.dumpFilesQueueRunnable);
        fileLoaderQueue.postRunnable(this.dumpFilesQueueRunnable, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
    }

    public FileLoadOperation findLoadOperationByRequestToken(int r4) {
        Iterator<FileLoadOperation> r3 = this.loadOperationPaths.values().iterator();
    L4:
        if (r3.hasNext() == false) goto L10;
        FileLoadOperation r0 = r3.next();
        if (r0 == null) goto L4;
        if (r0.uiRequestTokens.contains(Integer.valueOf(r4)) == false) goto L4;
        return r0;
    L10:
        return null;
    }

    public FileUploadOperation findUploadOperationByRequestToken(int r4) {
        Iterator<FileUploadOperation> r3 = this.uploadOperationPaths.values().iterator();
    L4:
        if (r3.hasNext() == false) goto L10;
        FileUploadOperation r0 = r3.next();
        if (r0 == null) goto L4;
        if (r0.uiRequestTokens.contains(Integer.valueOf(r4)) == false) goto L4;
        return r0;
    L10:
        return null;
    }

    public float getBufferedProgressFromPosition(float r3, String r4) {
        if (TextUtils.isEmpty(r4) == false) goto L5;
        return 0.0f;
    L5:
        FileLoadOperation r2 = this.loadOperationPaths.get(r4);
        if (r2 != null) goto L8;
        return 0.0f;
    L8:
        return r2.getDownloadedLengthFromOffset(r3);
    }

    public void getCurrentLoadingFiles(ArrayList<MessageObject> r3) {
        r3.clear();
        r3.addAll(getDownloadController().downloadingFiles);
        int r2 = 0;
    L4:
        if (r2 >= r3.size()) goto L6;
        r3.get(r2).isDownloadingFile = true;
        r2 = r2 + 1;
        goto L4
    }

    public FilePathDatabase getFileDatabase() {
        return this.filePathDatabase;
    }

    public DispatchQueue getFileLoaderQueue() {
        return fileLoaderQueue;
    }

    public int getFileReference(Object r4) {
        int r0 = this.lastReferenceId;
        this.lastReferenceId = r0 + 1;
        this.parentObjectReferences.put(Integer.valueOf(r0), new WeakReference(r4));
        return r0;
    }

    public File getLocalFile(ImageLocation r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        TLRPC.TL_fileLocationToBeDeprecated r0 = r3.location;
        if (r0 == null) goto L8;
        String r32 = getAttachFileName(r0, null);
    L15:
        if (r32 != null) goto L17;
        return null;
    L17:
        File r02 = new File(getDirectory(4), r32);
        if (r02.exists() == false) goto L20;
        return r02;
    L20:
        File r03 = new File(getDirectory(0), r32);
        if (r03.exists() == false) goto L23;
        return r03;
    L23:
        File r04 = new File(getDirectory(2), r32);
        if (r04.exists() == false) goto L26;
        return r04;
    L26:
        File r05 = new File(getDirectory(5), r32);
        if (r05.exists() == false) goto L29;
        return r05;
    L29:
        return null;
    L8:
        TLRPC.Document r06 = r3.document;
        if (r06 == null) goto L11;
        r32 = getAttachFileName(r06);
        goto L15
    L11:
        WebFile r33 = r3.webFile;
        if (r33 == null) goto L14;
        r32 = getAttachFileName(r33);
        goto L15
    L14:
        r32 = null;
        goto L15
    }

    public Object getParentObject(int r1) {
        WeakReference<Object> r0 = this.parentObjectReferences.get(Integer.valueOf(r1));
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.get();
    }

    public File getPathToAttach(TLObject r3) {
        return getPathToAttach(r3, null, false);
    }

    public File getPathToAttach(TLObject r10, String r11, String r12, boolean r13, boolean r14) {
        int r0 = 4;
        if (r13 == false) goto L7;
        File r9 = getDirectory(4);
    L5:
        long r4 = 0;
        int r6 = 0;
        int r7 = 0;
    L111:
        if (r9 != null) goto L115;
        return new File(_UrlKt.FRAGMENT_ENCODE_SET);
    L115:
        if (r4 == 0) goto L121;
        String r112 = getInstance(UserConfig.selectedAccount).getFileDatabase().getPath(r4, r6, r7, r14);
        if (r112 == null) goto L121;
        return new File(r112);
    L121:
        return new File(r9, getAttachFileName(r10, r12));
    L7:
        if ((r10 instanceof TLRPC.Document) == false) goto L25;
        TLRPC.Document r92 = (TLRPC.Document) r10;
        if (TextUtils.isEmpty(r92.localPath) == true) goto L13;
        return new File(r92.localPath);
    L13:
        if (r92.key == null) goto L16;
    L22:
        long r3 = r92.id;
        int r93 = r92.dc_id;
        File r113 = getDirectory(r0);
    L23:
        r6 = r93;
        r9 = r113;
        r7 = r0;
        r4 = r3;
        goto L111
    L16:
        if (MessageObject.isVoiceDocument(r92) == false) goto L19;
        r0 = 1;
        goto L22
    L19:
        if (MessageObject.isVideoDocument(r92) == false) goto L21;
        r0 = 2;
        goto L22
    L21:
        r0 = 3;
        goto L22
    L25:
        if ((r10 instanceof TLRPC.Photo) == false) goto L29;
        return getPathToAttach(getClosestPhotoSizeWithSize(((TLRPC.Photo) r10).sizes, AndroidUtilities.getPhotoSize(true)), r12, false, r14);
    L29:
        if ((r10 instanceof TLRPC.PhotoSize) == false) goto L53;
        TLRPC.PhotoSize r94 = (TLRPC.PhotoSize) r10;
        if ((r94 instanceof TLRPC.TL_photoStrippedSize) == false) goto L33;
    L49:
        r113 = null;
    L47:
        r0 = 0;
    L50:
        TLRPC.FileLocation r95 = r94.location;
        r3 = r95.volume_id;
        int r132 = r95.dc_id;
        int r96 = r95.local_id;
    L51:
        r93 = (r96 << 16) + r132;
        goto L23
    L33:
        if ((r94 instanceof TLRPC.TL_photoPathSize) == true) goto L49;
        TLRPC.FileLocation r114 = r94.location;
        if (r114 != null) goto L38;
    L48:
        r113 = getDirectory(4);
        goto L50
    L38:
        if (r114.key != null) goto L48;
        if (r114.volume_id != (-2147483648L)) goto L44;
        if (r114.local_id < 0) goto L48;
    L44:
        if (r94.size < 0) goto L48;
        r113 = getDirectory(0);
        goto L47
    L53:
        if ((r10 instanceof TLRPC.TL_videoSize) == false) goto L69;
        TLRPC.TL_videoSize r97 = (TLRPC.TL_videoSize) r10;
        TLRPC.FileLocation r115 = r97.location;
        if (r115 != null) goto L57;
    L66:
        r113 = getDirectory(4);
    L67:
        TLRPC.FileLocation r98 = r97.location;
        r3 = r98.volume_id;
        r132 = r98.dc_id;
        r96 = r98.local_id;
        goto L51
    L57:
        if (r115.key != null) goto L66;
        if (r115.volume_id != (-2147483648L)) goto L63;
        if (r115.local_id < 0) goto L66;
    L63:
        if (r97.size < 0) goto L66;
        r113 = getDirectory(0);
        r0 = 0;
        goto L67
    L69:
        if ((r10 instanceof TLRPC.FileLocation) == false) goto L81;
        TLRPC.FileLocation r99 = (TLRPC.FileLocation) r10;
        if (r99.key != null) goto L78;
        r4 = r99.volume_id;
        if (r4 == (-2147483648L)) goto L75;
    L77:
        int r116 = r99.dc_id + (r99.local_id << 16);
        r9 = getDirectory(0);
    L79:
        r6 = r116;
        r7 = 0;
        goto L111
    L75:
        if (r99.local_id >= 0) goto L77;
    L78:
        r9 = getDirectory(4);
        r4 = 0;
        r116 = 0;
        goto L79
    L81:
        if ((r10 instanceof TLRPC.UserProfilePhoto) == true) goto L105;
        if ((r10 instanceof TLRPC.ChatPhoto) == true) goto L105;
        if ((r10 instanceof WebFile) == false) goto L98;
        WebFile r910 = (WebFile) r10;
        if (r910.mime_type.startsWith("image/") == false) goto L91;
        r9 = getDirectory(0);
        goto L5
    L91:
        if (r910.mime_type.startsWith("audio/") == false) goto L94;
        r9 = getDirectory(1);
        goto L5
    L94:
        if (r910.mime_type.startsWith("video/") == false) goto L96;
        r9 = getDirectory(2);
        goto L5
    L96:
        r9 = getDirectory(3);
        goto L5
    L98:
        if ((r10 instanceof TLRPC.TL_secureFile) == false) goto L100;
    L103:
        r9 = getDirectory(4);
        goto L5
    L100:
        if ((r10 instanceof SecureDocument) == true) goto L103;
        r9 = null;
    L105:
        if (r11 != null) goto L108;
        r11 = "s";
    L108:
        if ("s".equals(r11) == false) goto L110;
        r9 = getDirectory(4);
        goto L5
    L110:
        r9 = getDirectory(0);
        goto L5
    }

    public File getPathToAttach(TLObject r7, String r8, boolean r9) {
        return getPathToAttach(r7, null, r8, r9, true);
    }

    public File getPathToAttach(TLObject r7, String r8, boolean r9, boolean r10) {
        return getPathToAttach(r7, null, r8, r9, r10);
    }

    public File getPathToAttach(TLObject r2, boolean r3) {
        return getPathToAttach(r2, null, r3);
    }

    public File getPathToMessage(TLRPC.Message r2) {
        return getPathToMessage(r2, true);
    }

    public File getPathToMessage(TLRPC.Message r2, boolean r3) {
        return getPathToMessage(r2, false, r3);
    }

    public File getPathToMessage(TLRPC.Message r7, boolean r8, boolean r9) {
        if (r7 != null) goto L7;
        return new File(_UrlKt.FRAGMENT_ENCODE_SET);
    L7:
        if ((r7 instanceof TLRPC.TL_messageService) == false) goto L16;
        TLRPC.Photo r72 = r7.action.photo;
        if (r72 == null) goto L56;
        ArrayList<TLRPC.PhotoSize> r73 = r72.sizes;
        if (r73.size() <= 0) goto L56;
        TLRPC.PhotoSize r74 = getClosestPhotoSizeWithSize(r73, AndroidUtilities.getPhotoSize());
        if (r74 == null) goto L56;
        return getPathToAttach(r74, null, r8, r9);
    L56:
        return new File(_UrlKt.FRAGMENT_ENCODE_SET);
    L16:
        boolean r3 = false;
        if ((MessageObject.getMedia(r7) instanceof TLRPC.TL_messageMediaDocument) == false) goto L26;
        TLRPC.Document r0 = MessageObject.getMedia(r7).document;
        if (r8 == false) goto L21;
    L22:
        r3 = true;
    L24:
        return getPathToAttach(r0, null, r3, r9);
    L21:
        if (MessageObject.getMedia(r7).ttl_seconds == 0) goto L24;
    L26:
        if ((MessageObject.getMedia(r7) instanceof TLRPC.TL_messageMediaPhoto) == false) goto L38;
        ArrayList<TLRPC.PhotoSize> r1 = MessageObject.getMedia(r7).photo.sizes;
        if (r1.size() <= 0) goto L56;
        TLRPC.PhotoSize r12 = getClosestPhotoSizeWithSize(r1, AndroidUtilities.getPhotoSize(true), false, null, true);
        if (r12 == null) goto L56;
        if (r8 == false) goto L33;
    L34:
        r3 = true;
    L36:
        return getPathToAttach(r12, null, r3, r9);
    L33:
        if (MessageObject.getMedia(r7).ttl_seconds == 0) goto L36;
    L38:
        if ((MessageObject.getMedia(r7) instanceof TLRPC.TL_messageMediaWebPage) == false) goto L52;
        if (MessageObject.getMedia(r7).webpage.document == null) goto L44;
        return getPathToAttach(MessageObject.getMedia(r7).webpage.document, null, r8, r9);
    L44:
        if (MessageObject.getMedia(r7).webpage.photo == null) goto L56;
        ArrayList<TLRPC.PhotoSize> r75 = MessageObject.getMedia(r7).webpage.photo.sizes;
        if (r75.size() <= 0) goto L56;
        TLRPC.PhotoSize r76 = getClosestPhotoSizeWithSize(r75, AndroidUtilities.getPhotoSize());
        if (r76 == null) goto L56;
        return getPathToAttach(r76, null, r8, r9);
    L52:
        if ((MessageObject.getMedia(r7) instanceof TLRPC.TL_messageMediaInvoice) == false) goto L56;
        return getPathToAttach(((TLRPC.TL_messageMediaInvoice) MessageObject.getMedia(r7)).photo, null, true, r9);
    }

    public void getRecentLoadingFiles(ArrayList<MessageObject> r3) {
        r3.clear();
        r3.addAll(getDownloadController().recentDownloadingFiles);
        int r2 = 0;
    L4:
        if (r2 >= r3.size()) goto L6;
        r3.get(r2).isDownloadingFile = true;
        r2 = r2 + 1;
        goto L4
    }

    public boolean isLoadingFile(String r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (this.loadOperationPathsUI.containsKey(r1) == false) goto L9;
        return true;
    L9:
        return false;
    }

    public boolean isLoadingVideo(TLRPC.Document r2, boolean r3) {
        if (r2 == null) goto L11;
        HashMap<String, Boolean> r1 = this.loadingVideos;
        StringBuilder r0 = new StringBuilder();
        r0.append(getAttachFileName(r2));
        if (r3 == false) goto L6;
        String r22 = "p";
    L7:
        r0.append(r22);
        if (r1.containsKey(r0.toString()) == false) goto L13;
        return true;
    L13:
        return false;
    L6:
        r22 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L7
    L11:
        return false;
    }

    public boolean isLoadingVideoAny(TLRPC.Document r4) {
        if (isLoadingVideo(r4, false) == false) goto L5;
    L8:
        return true;
    L5:
        if (isLoadingVideo(r4, true) == true) goto L8;
        return false;
    }

    public void loadFile(ImageLocation r13, Object r14, String r15, int r16, int r17) {
        if (r13 != null) goto L4;
        return;
    L4:
        if (r17 == 0) goto L6;
    L12:
        int r11 = r17;
    L13:
        loadFile(r13.document, r13.secureDocument, r13.webFile, r13.location, r13, r14, r15, r13.getSize(), r16, r11);
        return;
    L6:
        if (r13.isEncrypted() == false) goto L8;
    L11:
        r11 = 1;
        goto L13
    L8:
        if (r13.photoSize == null) goto L12;
        if (r13.getSize() != 0) goto L12;
        goto L11
    }

    public void loadFile(SecureDocument r13, int r14) {
        if (r13 != null) goto L4;
        return;
    L4:
        loadFile(null, r13, null, null, null, null, null, 0, r14, 1);
    }

    public void loadFile(WebFile r13, int r14, int r15) {
        loadFile(null, null, r13, null, null, null, null, 0, r14, r15);
    }

    public void loadFile(TLRPC.Document r13, Object r14, int r15, int r16) {
        if (r13 != null) goto L4;
        return;
    L4:
        if (r16 == 0) goto L6;
    L8:
        int r11 = r16;
    L9:
        loadFile(r13, null, null, null, null, r14, null, 0, r15, r11);
        return;
    L6:
        if (r13.key == null) goto L8;
        r11 = 1;
        goto L9
    }

    public FileLoadOperation loadStreamFile(FileLoadOperationStream r12, TLRPC.Document r13, ImageLocation r14, Object r15, long r16, boolean r18, int r19) {
        if (r13 != null) goto L5;
        int r0 = 1;
    L7:
        return loadStreamFile(r12, r13, r14, r15, r16, r18, r19, r0);
    L5:
        r0 = 0;
        goto L7
    }

    public FileLoadOperation loadStreamFile(FileLoadOperationStream r16, TLRPC.Document r17, ImageLocation r18, Object r19, long r20, boolean r22, int r23, int r24) {
        CountDownLatch r12 = new CountDownLatch(1);
        FileLoadOperation[] r2 = new FileLoadOperation[1];
        fileLoaderQueue.postRunnable(new FileLoader$$ExternalSyntheticLambda2(this, r2, r17, r18, r19, r23, r16, r20, r22, r24, r12));
        awaitFileLoadOperation(r12, true);
        return r2[0];
    }

    public void onNetworkChanged(boolean r3) {
        fileLoaderQueue.postRunnable(new FileLoader$$ExternalSyntheticLambda1(this, r3));
    }

    public void removeLoadingVideo(TLRPC.Document r1, boolean r2, boolean r3) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (r3 == false) goto L7;
        AndroidUtilities.runOnUIThread(new FileLoader$$ExternalSyntheticLambda20(this, r1, r2));
        return;
    L7:
        removeLoadingVideoInternal(r1, r2);
    }

    public void setDelegate(FileLoaderDelegate r1) {
        this.delegate = r1;
    }

    public void setForceStreamLoadingFile(TLRPC.FileLocation r3, String r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        fileLoaderQueue.postRunnable(new FileLoader$$ExternalSyntheticLambda0(this, r3, r4));
    }

    public void setLoadingVideo(TLRPC.Document r1, boolean r2, boolean r3) {
        if (r1 != null) goto L4;
        return;
    L4:
        if (r3 == false) goto L7;
        AndroidUtilities.runOnUIThread(new FileLoader$$ExternalSyntheticLambda12(this, r1, r2));
        return;
    L7:
        setLoadingVideoInternal(r1, r2);
    }

    public void setLoadingVideoForPlayer(TLRPC.Document r6, boolean r7) {
        if (r6 == null) goto L17;
        String r62 = getAttachFileName(r6);
        HashMap<String, Boolean> r0 = this.loadingVideos;
        StringBuilder r1 = new StringBuilder();
        r1.append(r62);
        String r2 = "p";
        if (r7 == false) goto L7;
        String r4 = _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        r1.append(r4);
        if (r0.containsKey(r1.toString()) == false) goto L16;
        HashMap<String, Boolean> r02 = this.loadingVideos;
        StringBuilder r12 = new StringBuilder();
        r12.append(r62);
        if (r7 == true) goto L14;
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
    L14:
        r12.append(r2);
        r02.put(r12.toString(), Boolean.TRUE);
        getNotificationCenter().postNotificationName(NotificationCenter.videoLoadingStateChanged, new Object[]{r62});
        return;
    L16:
        return;
    L7:
        r4 = "p";
        goto L8
    }

    public void setLoadingVideoInternal(TLRPC.Document r3, boolean r4) {
        String r32 = getAttachFileName(r3);
        StringBuilder r0 = new StringBuilder();
        r0.append(r32);
        if (r4 == false) goto L5;
        String r42 = "p";
    L6:
        r0.append(r42);
        String r43 = r0.toString();
        this.loadingVideos.put(r43, Boolean.TRUE);
        getNotificationCenter().postNotificationName(NotificationCenter.videoLoadingStateChanged, new Object[]{r32});
        return;
    L5:
        r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    public void setLocalPathTo(TLObject r18, String r19) {
        int r3 = 4;
        if ((r18 instanceof TLRPC.Document) == false) goto L17;
        TLRPC.Document r1 = (TLRPC.Document) r18;
        if (r1.key == null) goto L8;
    L6:
        int r8 = r3;
        long r5 = r1.id;
        int r7 = r1.dc_id;
        this.filePathDatabase.putPath(r5, r7, r8, 1, r19);
        return;
    L8:
        if (MessageObject.isVoiceDocument(r1) == false) goto L11;
        r3 = 1;
        goto L6
    L11:
        if (MessageObject.isVideoDocument(r1) == false) goto L13;
        r3 = 2;
        goto L6
    L13:
        r3 = 3;
        goto L6
    L17:
        if ((r18 instanceof TLRPC.PhotoSize) == false) goto L37;
        TLRPC.PhotoSize r12 = (TLRPC.PhotoSize) r18;
        if ((r12 instanceof TLRPC.TL_photoStrippedSize) == false) goto L21;
        return;
    L21:
        if ((r12 instanceof TLRPC.TL_photoPathSize) == true) goto L39;
        TLRPC.FileLocation r2 = r12.location;
        if (r2 != null) goto L26;
    L35:
        long r11 = r2.volume_id;
        int r13 = r2.dc_id + (r2.local_id << 16);
        FilePathDatabase r10 = this.filePathDatabase;
        r10.putPath(r11, r13, r3, 1, r19);
        return;
    L26:
        if (r2.key != null) goto L35;
        if (r2.volume_id != (-2147483648L)) goto L32;
        if (r2.local_id < 0) goto L35;
    L32:
        if (r12.size < 0) goto L35;
        r3 = 0;
        goto L35
    L39:
        return;
    }

    public void uploadFile(String r5, Utilities.Callback<TLRPC.InputFile> r6) {
        NotificationCenter.NotificationCenterDelegate[] r0 = new NotificationCenter.NotificationCenterDelegate[1];
        r0[0] = new FileLoader$$ExternalSyntheticLambda19(r5, r6, new FileLoader$$ExternalSyntheticLambda18(this, r0));
        getNotificationCenter().addObserver(r0[0], NotificationCenter.fileUploaded);
        getNotificationCenter().addObserver(r0[0], NotificationCenter.fileUploadFailed);
        uploadFile(r5, false, false, 67108864);
    }

    public void uploadFile(String r9, boolean r10, boolean r11, int r12) {
        uploadFile(r9, r10, r11, 0, r12, false);
    }

    public void uploadFile(String r11, boolean r12, boolean r13, long r14, int r16, boolean r17) {
        if (r11 != null) goto L4;
        return;
    L4:
        fileLoaderQueue.postRunnable(new FileLoader$$ExternalSyntheticLambda7(this, r12, r11, r14, r16, r17, r13));
    }
}
