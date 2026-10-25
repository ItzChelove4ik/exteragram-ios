package org.telegram.messenger;

import com.exteragram.messenger.ExteraConfig;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.google.android.gms.cast.MediaError;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipException;
import kotlin.UByte;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.FilePathDatabase;
import org.telegram.messenger.utils.ImmutableByteArrayOutputStream;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Storage.CacheModel;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public class FileLoadOperation {
    private static final int FINISH_CODE_DEFAULT = 0;
    private static final int FINISH_CODE_FILE_ALREADY_EXIST = 1;
    public static volatile DispatchQueue filesQueue = null;
    public static ImmutableByteArrayOutputStream filesQueueByteBuffer = null;
    private static int globalRequestPointer = 0;
    private static final Object lockObject = null;
    private static final int preloadMaxBytes = 2097152;
    private static final int stateCanceled = 4;
    private static final int stateCancelling = 5;
    private static final int stateDownloading = 1;
    private static final int stateFailed = 2;
    private static final int stateFinished = 3;
    private static final int stateIdle = 0;
    private final boolean FULL_LOGS;
    private boolean allowDisordererFileSave;
    private int bigFileSizeFrom;
    private long bytesCountPadding;
    private File cacheFileFinal;
    private boolean cacheFileFinalReady;
    private File cacheFileGzipTemp;
    private File cacheFileParts;
    private File cacheFilePreload;
    private File cacheFileTemp;
    private File cacheIvTemp;
    private final Runnable cancelAfterNoStreamListeners;
    private ArrayList<RequestInfo> cancelledRequestInfos;
    public volatile boolean caughtPremiumFloodWait;
    private byte[] cdnCheckBytes;
    private int cdnChunkCheckSize;
    private int cdnDatacenterId;
    private HashMap<Long, TLRPC.TL_fileHash> cdnHashes;
    private byte[] cdnIv;
    private byte[] cdnKey;
    private byte[] cdnToken;
    private volatile boolean closeFilePartsStreamOnWriteEnd;
    public int currentAccount;
    private int currentDownloadChunkSize;
    private int currentMaxDownloadRequests;
    private int currentType;
    private int datacenterId;
    private ArrayList<RequestInfo> delayedRequestInfos;
    private FileLoadOperationDelegate delegate;
    private long documentId;
    private int downloadChunkSize;
    private int downloadChunkSizeAnimation;
    private int downloadChunkSizeBig;
    private long downloadedBytes;
    private boolean encryptFile;
    private byte[] encryptIv;
    private byte[] encryptKey;
    private String ext;
    private FilePathDatabase.FileMeta fileMetadata;
    private String fileName;
    private RandomAccessFile fileOutputStream;
    private RandomAccessFile filePartsStream;
    private RandomAccessFile fileReadStream;
    private Runnable fileWriteRunnable;
    private RandomAccessFile fiv;
    private boolean forceSmallChunk;
    private long foundMoovSize;
    private int initialDatacenterId;
    private boolean isCdn;
    private boolean isForceRequest;
    private boolean isPreloadVideoOperation;
    public boolean isStory;
    private boolean isStream;
    private byte[] iv;
    private byte[] key;
    protected long lastProgressUpdateTime;
    protected TLRPC.InputFileLocation location;
    private int maxCdnParts;
    private int maxDownloadRequests;
    private int maxDownloadRequestsAnimation;
    private int maxDownloadRequestsBig;
    private int moovFound;
    private long nextAtomOffset;
    private boolean nextPartWasPreloaded;
    private long nextPreloadDownloadOffset;
    private ArrayList<Range> notCheckedCdnRanges;
    private ArrayList<Range> notLoadedBytesRanges;
    private volatile ArrayList<Range> notLoadedBytesRangesCopy;
    private ArrayList<Range> notRequestedBytesRanges;
    public Object parentObject;
    public FilePathDatabase.PathData pathSaveData;
    private volatile boolean paused;
    public boolean preFinished;
    private boolean preloadFinished;
    private long preloadNotRequestedBytesCount;
    private int preloadPrefixSize;
    private RandomAccessFile preloadStream;
    private int preloadStreamFileOffset;
    private byte[] preloadTempBuffer;
    private int preloadTempBufferCount;
    private HashMap<Long, PreloadRange> preloadedBytesRanges;
    private int priority;
    private FileLoaderPriorityQueue priorityQueue;
    private RequestInfo priorityRequestInfo;
    private int renameRetryCount;
    public ArrayList<RequestInfo> requestInfos;
    private long requestedBytesCount;
    private HashMap<Long, Integer> requestedPreloadedBytesRanges;
    private boolean requestedReference;
    private boolean requestingCdnOffsets;
    protected boolean requestingReference;
    private int requestsCount;
    private boolean reuploadingCdn;
    private long startTime;
    private boolean started;
    private volatile int state;
    private String storeFileName;
    private File storePath;
    FileLoadOperationStream stream;
    private ArrayList<FileLoadOperationStream> streamListeners;
    long streamOffset;
    boolean streamPriority;
    private long streamPriorityStartOffset;
    private long streamStartOffset;
    private boolean supportsPreloading;
    private File tempPath;
    public long totalBytesCount;
    private int totalPreloadedBytes;
    long totalTime;
    public final ArrayList<Integer> uiRequestTokens;
    private boolean ungzip;
    private WebFile webFile;
    private TLRPC.InputWebFileLocation webLocation;
    private volatile boolean writingToFilePartsStream;

    public interface FileLoadOperationDelegate {
        void didChangedLoadProgress(FileLoadOperation r1, long r2, long r4);

        void didFailedLoadingFile(FileLoadOperation r1, int r2);

        void didFinishLoadingFile(FileLoadOperation r1, File r2);

        void didPreFinishLoading(FileLoadOperation r1, File r2);

        boolean hasAnotherRefOnFile(String r1);

        boolean isLocallyCreatedFile(String r1);

        void saveFilePath(FilePathDatabase.PathData r1, File r2);
    }

    public static class PreloadRange {
        private long fileOffset;
        private long length;

        /* renamed from: -$$Nest$fgetfileOffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m4016$$Nest$fgetfileOffset(PreloadRange r2) {
            return r2.fileOffset;
        }

        /* renamed from: -$$Nest$fgetlength, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m4017$$Nest$fgetlength(PreloadRange r2) {
            return r2.length;
        }

        private PreloadRange(long r1, long r3) {
            this.fileOffset = r1;
            this.length = r3;
        }

        public /* synthetic */ PreloadRange(long r1, long r3, FileLoadOperationIA r5) {
            this(r1, r3);
        }
    }

    public static class Range {
        private long end;
        private long start;

        /* renamed from: -$$Nest$fgetend, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m4018$$Nest$fgetend(Range r2) {
            return r2.end;
        }

        /* renamed from: -$$Nest$fgetstart, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m4019$$Nest$fgetstart(Range r2) {
            return r2.start;
        }

        /* renamed from: -$$Nest$fputend, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4020$$Nest$fputend(Range r0, long r1) {
            r0.end = r1;
        }

        /* renamed from: -$$Nest$fputstart, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4021$$Nest$fputstart(Range r0, long r1) {
            r0.start = r1;
        }

        private Range(long r1, long r3) {
            this.start = r1;
            this.end = r3;
        }

        public /* synthetic */ Range(long r1, long r3, FileLoadOperationIA r5) {
            this(r1, r3);
        }

        public String toString() {
            return "Range{start=" + this.start + ", end=" + this.end + '}';
        }
    }

    public static class RequestInfo {
        public boolean cancelled;
        public boolean cancelling;
        public int chunkSize;
        public int connectionType;
        private boolean forceSmallChunk;
        private long offset;
        public long requestStartTime;
        public int requestToken;
        private TLRPC.TL_upload_file response;
        private TLRPC.TL_upload_cdnFile responseCdn;
        private TLRPC.TL_upload_webFile responseWeb;
        public Runnable whenCancelled;

        /* renamed from: -$$Nest$fgetforceSmallChunk, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m4022$$Nest$fgetforceSmallChunk(RequestInfo r0) {
            return r0.forceSmallChunk;
        }

        /* renamed from: -$$Nest$fgetoffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m4023$$Nest$fgetoffset(RequestInfo r2) {
            return r2.offset;
        }

        /* renamed from: -$$Nest$fgetresponse, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.TL_upload_file m4024$$Nest$fgetresponse(RequestInfo r0) {
            return r0.response;
        }

        /* renamed from: -$$Nest$fgetresponseCdn, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.TL_upload_cdnFile m4025$$Nest$fgetresponseCdn(RequestInfo r0) {
            return r0.responseCdn;
        }

        /* renamed from: -$$Nest$fgetresponseWeb, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.TL_upload_webFile m4026$$Nest$fgetresponseWeb(RequestInfo r0) {
            return r0.responseWeb;
        }

        /* renamed from: -$$Nest$fputforceSmallChunk, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4027$$Nest$fputforceSmallChunk(RequestInfo r0, boolean r1) {
            r0.forceSmallChunk = r1;
        }

        /* renamed from: -$$Nest$fputoffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4028$$Nest$fputoffset(RequestInfo r0, long r1) {
            r0.offset = r1;
        }

        /* renamed from: -$$Nest$fputresponse, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4029$$Nest$fputresponse(RequestInfo r0, TLRPC.TL_upload_file r1) {
            r0.response = r1;
        }

        /* renamed from: -$$Nest$fputresponseCdn, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4030$$Nest$fputresponseCdn(RequestInfo r0, TLRPC.TL_upload_cdnFile r1) {
            r0.responseCdn = r1;
        }

        /* renamed from: -$$Nest$fputresponseWeb, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4031$$Nest$fputresponseWeb(RequestInfo r0, TLRPC.TL_upload_webFile r1) {
            r0.responseWeb = r1;
        }

        public RequestInfo() {
        }
    }

    public static /* synthetic */ void $r8$lambda$5Yp6LUmPZW4miIyK5BwBm9D72Xo(FileLoadOperation r0) {
        r0.lambda$clearOperation$26();
    }

    public static /* synthetic */ void $r8$lambda$9_79pifGm___VQ_5_0QjO_OHR_U(FileLoadOperation r0, FileLoadOperationStream r1) {
        r0.lambda$setStream$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$BCAhGT9xkszqkO4gBgMos4WJ75k(FileLoadOperation r0, RequestInfo r1, int r2, int r3, TLObject r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$startDownloadRequest$29(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$BCQ_sDye5_sxIvTLX22a74fgWfo(FileLoadOperation r0, long[] r1, long r2, long r4, CountDownLatch r6) {
        r0.lambda$getDownloadedLengthFromOffset$4(r1, r2, r4, r6);
    }

    /* renamed from: $r8$lambda$CDuAUjvsTX7Jq3JXybb-Y9NKqJ4, reason: not valid java name */
    public static /* synthetic */ void m4004$r8$lambda$CDuAUjvsTX7Jq3JXybbY9NKqJ4(RequestInfo r0) {
        Runnable r02 = r0.whenCancelled;
        if (r02 == null) goto L6;
        r02.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$DfvQlsgruMEVOteDUzHI3SAwV7Q(FileLoadOperation r0, File[] r1, CountDownLatch r2) {
        r0.lambda$getCurrentFile$3(r1, r2);
    }

    /* renamed from: $r8$lambda$F6mUHo-IrdZDhekZElByLY1Iazw, reason: not valid java name */
    public static /* synthetic */ void m4005$r8$lambda$F6mUHoIrdZDhekZElByLY1Iazw(FileLoadOperation r0) {
        r0.lambda$cancelOnStage$14();
    }

    public static /* synthetic */ void $r8$lambda$FviLTr17YUxfcmzNmGh4Zt77jgc(FileLoadOperation r0, ArrayList r1) {
        r0.lambda$addPart$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$GPHn1x9XnVVvrH5NbfzkuvdUkwM(FileLoadOperation r0, boolean[] r1) {
        r0.lambda$start$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$HdF2FumHvhEjW0IkY8FKcW7f8nI(FileLoadOperation r0, int r1) {
        r0.lambda$start$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$HtswjgU2uwBF0JkstCryj4_6cvM(FileLoadOperation r0, boolean r1) {
        r0.lambda$cancel$13(r1);
    }

    /* renamed from: $r8$lambda$MxbGMWd0E5MRFs_wgEbZGPXU3-o, reason: not valid java name */
    public static /* synthetic */ void m4006$r8$lambda$MxbGMWd0E5MRFs_wgEbZGPXU3o(FileLoadOperation r0) {
        r0.lambda$onFinishLoadingFile$18();
    }

    /* renamed from: $r8$lambda$OOP-x532BI9Nl3Vitkmk386KtBI, reason: not valid java name */
    public static /* synthetic */ void m4007$r8$lambda$OOPx532BI9Nl3Vitkmk386KtBI(FileLoadOperation r0, int r1) {
        r0.lambda$startDownloadRequest$30(r1);
    }

    /* renamed from: $r8$lambda$PGmBHKfUhzxTmozPlzOM9C4_-e0, reason: not valid java name */
    public static /* synthetic */ void m4008$r8$lambda$PGmBHKfUhzxTmozPlzOM9C4_e0(FileLoadOperation r0, int r1, RequestInfo r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$startDownloadRequest$28(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$QVgcEqNFAchv8ty9aKKDmZvGEZQ(FileLoadOperation r0, RequestInfo r1) {
        r0.lambda$startDownloadRequest$27(r1);
    }

    /* renamed from: $r8$lambda$TGQV-EtA-dtjFzjEiQPgoRA5miQ, reason: not valid java name */
    public static /* synthetic */ void m4009$r8$lambda$TGQVEtAdtjFzjEiQPgoRA5miQ(FileLoadOperation r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestFileOffsets$21(r1, r2);
    }

    /* renamed from: $r8$lambda$UMEiL64yLBxiZo-WwD875wZEhnk, reason: not valid java name */
    public static /* synthetic */ void m4010$r8$lambda$UMEiL64yLBxiZoWwD875wZEhnk(FileLoadOperation r0, boolean r1) {
        r0.lambda$onFinishLoadingFile$19(r1);
    }

    public static /* synthetic */ void $r8$lambda$VAK7asO5IaR0_9jikOXjFAUFrbc(FileLoadOperation r0, boolean r1, long r2, FileLoadOperationStream r4, boolean r5) {
        r0.lambda$start$9(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$WZILNyIhrBQAst1rN6aS3FVcWXI(RequestInfo r0) {
        Runnable r02 = r0.whenCancelled;
        if (r02 == null) goto L6;
        r02.run();
        return;
    }

    /* renamed from: $r8$lambda$ZDQNyp6R-lBR8vFMAcLTM_GUMKM, reason: not valid java name */
    public static /* synthetic */ void m4011$r8$lambda$ZDQNyp6RlBR8vFMAcLTM_GUMKM(FileLoadOperation r0, int r1) {
        r0.lambda$processRequestResult$22(r1);
    }

    public static /* synthetic */ void $r8$lambda$ZyVMiccceG_QMcvDL6U8bKmR8OA(FileLoadOperation r0, int r1) {
        r0.lambda$onFail$23(r1);
    }

    /* renamed from: $r8$lambda$_xZO_IMKEeNZr1zDFwN-Y_979-Q, reason: not valid java name */
    public static /* synthetic */ void m4012$r8$lambda$_xZO_IMKEeNZr1zDFwNY_979Q(FileLoadOperation r0, RequestInfo r1) {
        r0.lambda$clearOperation$24(r1);
    }

    public static /* synthetic */ void $r8$lambda$eSEv4l4p8cnKuvXfdMFPxZqlRRg(FileLoadOperation r0, boolean r1) {
        r0.lambda$onFinishLoadingFile$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$eZ7TbeNWuW3eS7VpC3eua52XoHw(FileLoadOperation r0, FileLoadOperationStream r1) {
        r0.lambda$removeStreamListener$5(r1);
    }

    /* renamed from: $r8$lambda$jrrmjRfBdFxX5rOyA6qI-6qziWE, reason: not valid java name */
    public static /* synthetic */ int m4013$r8$lambda$jrrmjRfBdFxX5rOyA6qI6qziWE(Range r4, Range r5) {
        if (Range.m4019$$Nest$fgetstart(r4) <= Range.m4019$$Nest$fgetstart(r5)) goto L7;
        return 1;
    L7:
        if (Range.m4019$$Nest$fgetstart(r4) >= Range.m4019$$Nest$fgetstart(r5)) goto L10;
        return -1;
    L10:
        return 0;
    }

    public static /* synthetic */ void $r8$lambda$mWYnwWasfLVbyebzBRj_sWZjxA0(FileLoadOperation r0) {
        r0.lambda$start$10();
    }

    /* renamed from: $r8$lambda$nGf2vg6_qrzCPXWCMNer3l8-Da0, reason: not valid java name */
    public static /* synthetic */ void m4014$r8$lambda$nGf2vg6_qrzCPXWCMNer3l8Da0(RequestInfo r2, int[] r3, Runnable r4) {
        r2.whenCancelled = null;
        r2.cancelled = true;
        int r1 = r3[0] - 1;
        r3[0] = r1;
        if (r1 != 0) goto L6;
        r4.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$nMYFsAJPPhN3O8EjMop7S7Ljdto(FileLoadOperation r0) {
        r0.lambda$pause$7();
    }

    public static /* synthetic */ void $r8$lambda$oMvwZmg1siIhBg4SBEoLoGUZpk0(FileLoadOperation r0) {
        r0.lambda$new$6();
    }

    /* renamed from: $r8$lambda$wR-7Q9FvnlT4HmKWbxBuG3Qa7ew, reason: not valid java name */
    public static /* synthetic */ void m4015$r8$lambda$wR7Q9FvnlT4HmKWbxBuG3Qa7ew(FileLoadOperation r0, File r1, File r2, File r3, File r4, boolean r5) {
        r0.lambda$onFinishLoadingFile$20(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$yMxZmIT7s_xmIN20lCdqN3FdWZQ(FileLoadOperation r0, boolean r1) {
        r0.lambda$setIsPreloadVideoOperation$12(r1);
    }

    static {
        filesQueue = new DispatchQueue("writeFileQueue");
        lockObject = new Object();
    }

    public FileLoadOperation(int r8, WebFile r9) {
        this.FULL_LOGS = false;
        this.downloadChunkSize = 32768;
        this.downloadChunkSizeBig = 131072;
        this.cdnChunkCheckSize = 131072;
        this.maxDownloadRequests = 4;
        this.maxDownloadRequestsBig = 4;
        this.bigFileSizeFrom = 10485760;
        this.maxCdnParts = (int) (FileLoader.DEFAULT_MAX_FILE_SIZE / 131072);
        this.downloadChunkSizeAnimation = 131072;
        this.maxDownloadRequestsAnimation = 4;
        this.preloadTempBuffer = new byte[24];
        this.state = 0;
        this.uiRequestTokens = new ArrayList();
        this.cancelAfterNoStreamListeners = new FileLoadOperation$$ExternalSyntheticLambda6(this);
        updateParams();
        this.currentAccount = r8;
        this.webFile = r9;
        this.webLocation = r9.location;
        this.totalBytesCount = r9.size;
        int r82 = MessagesController.getInstance(r8).webFileDatacenterId;
        this.datacenterId = r82;
        this.initialDatacenterId = r82;
        String r83 = FileLoader.getMimeTypePart(r9.mime_type);
        if (r9.mime_type.startsWith("image/") == false) goto L6;
        this.currentType = 16777216;
    L12:
        this.allowDisordererFileSave = true;
        this.ext = ImageLoader.getHttpUrlExtension(r9.url, r83);
        return;
    L6:
        if (r9.mime_type.equals("audio/ogg") == false) goto L9;
        this.currentType = ConnectionsManager.FileTypeAudio;
        goto L12
    L9:
        if (r9.mime_type.startsWith("video/") == false) goto L11;
        this.currentType = 33554432;
        goto L12
    L11:
        this.currentType = 67108864;
        goto L12
    }

    public FileLoadOperation(ImageLocation r8, Object r9, String r10, long r11) {
        boolean r0 = false;
        this.FULL_LOGS = false;
        this.downloadChunkSize = 32768;
        this.downloadChunkSizeBig = 131072;
        this.cdnChunkCheckSize = 131072;
        this.maxDownloadRequests = 4;
        this.maxDownloadRequestsBig = 4;
        this.bigFileSizeFrom = 10485760;
        this.maxCdnParts = (int) (FileLoader.DEFAULT_MAX_FILE_SIZE / 131072);
        this.downloadChunkSizeAnimation = 131072;
        this.maxDownloadRequestsAnimation = 4;
        this.preloadTempBuffer = new byte[24];
        this.state = 0;
        this.uiRequestTokens = new ArrayList();
        this.cancelAfterNoStreamListeners = new FileLoadOperation$$ExternalSyntheticLambda6(this);
        updateParams();
        this.parentObject = r9;
        this.isStory = r9 instanceof TL_stories.TL_storyItem;
        this.fileMetadata = FileLoader.getFileMetadataFromParent(this.currentAccount, r9);
        if (r8.imageType != 2) goto L5;
        boolean r92 = true;
    L6:
        this.isStream = r92;
        if (r8.isEncrypted() == false) goto L10;
        TLRPC.TL_inputEncryptedFileLocation r93 = new TLRPC.TL_inputEncryptedFileLocation();
        this.location = r93;
        TLRPC.TL_fileLocationToBeDeprecated r2 = r8.location;
        long r3 = r2.volume_id;
        r93.id = r3;
        r93.volume_id = r3;
        r93.local_id = r2.local_id;
        r93.access_hash = r8.access_hash;
        byte[] r94 = new byte[32];
        this.iv = r94;
        System.arraycopy(r8.iv, 0, r94, 0, r94.length);
        this.key = r8.key;
    L34:
        int r95 = r8.imageType;
        if (r95 != 1) goto L37;
    L38:
        r0 = true;
    L39:
        this.ungzip = r0;
        int r82 = r8.dc_id;
        this.datacenterId = r82;
        this.initialDatacenterId = r82;
        this.currentType = 16777216;
        this.totalBytesCount = r11;
        if (r10 != null) goto L43;
        r10 = "jpg";
    L43:
        this.ext = r10;
        return;
    L37:
        if (r95 != 3) goto L39;
    L10:
        if (r8.photoPeer == null) goto L17;
        TLRPC.TL_inputPeerPhotoFileLocation r96 = new TLRPC.TL_inputPeerPhotoFileLocation();
        TLRPC.TL_fileLocationToBeDeprecated r22 = r8.location;
        long r32 = r22.volume_id;
        r96.id = r32;
        r96.volume_id = r32;
        r96.local_id = r22.local_id;
        r96.photo_id = r8.photoId;
        if (r8.photoPeerType != 0) goto L14;
        boolean r23 = true;
    L15:
        r96.big = r23;
        r96.peer = r8.photoPeer;
        this.location = r96;
        goto L34
    L14:
        r23 = false;
        goto L15
    L17:
        if (r8.stickerSet == null) goto L20;
        TLRPC.TL_inputStickerSetThumb r97 = new TLRPC.TL_inputStickerSetThumb();
        TLRPC.TL_fileLocationToBeDeprecated r24 = r8.location;
        long r33 = r24.volume_id;
        r97.id = r33;
        r97.volume_id = r33;
        r97.local_id = r24.local_id;
        r97.thumb_version = r8.thumbVersion;
        r97.stickerset = r8.stickerSet;
        this.location = r97;
        goto L34
    L20:
        if (r8.thumbSize != null) goto L22;
        TLRPC.TL_inputFileLocation r98 = new TLRPC.TL_inputFileLocation();
        this.location = r98;
        TLRPC.TL_fileLocationToBeDeprecated r25 = r8.location;
        r98.volume_id = r25.volume_id;
        r98.local_id = r25.local_id;
        r98.secret = r8.access_hash;
        byte[] r26 = r8.file_reference;
        r98.file_reference = r26;
        if (r26 != null) goto L33;
        r98.file_reference = new byte[0];
    L33:
        this.allowDisordererFileSave = true;
        goto L34
    L22:
        if (r8.photoId == 0) goto L26;
        TLRPC.TL_inputPhotoFileLocation r99 = new TLRPC.TL_inputPhotoFileLocation();
        this.location = r99;
        r99.id = r8.photoId;
        TLRPC.TL_fileLocationToBeDeprecated r34 = r8.location;
        r99.volume_id = r34.volume_id;
        r99.local_id = r34.local_id;
        r99.access_hash = r8.access_hash;
        r99.file_reference = r8.file_reference;
        r99.thumb_size = r8.thumbSize;
        if (r8.imageType != 2) goto L27;
        this.allowDisordererFileSave = true;
    L27:
        TLRPC.InputFileLocation r910 = this.location;
        if (r910.file_reference != null) goto L34;
        r910.file_reference = new byte[0];
        goto L34
    L26:
        TLRPC.TL_inputDocumentFileLocation r911 = new TLRPC.TL_inputDocumentFileLocation();
        this.location = r911;
        long r27 = r8.documentId;
        r911.id = r27;
        this.documentId = r27;
        TLRPC.TL_fileLocationToBeDeprecated r28 = r8.location;
        r911.volume_id = r28.volume_id;
        r911.local_id = r28.local_id;
        r911.access_hash = r8.access_hash;
        r911.file_reference = r8.file_reference;
        r911.thumb_size = r8.thumbSize;
        goto L27
    L5:
        r92 = false;
        goto L6
    }

    public FileLoadOperation(SecureDocument r8) {
        this.FULL_LOGS = false;
        this.downloadChunkSize = 32768;
        this.downloadChunkSizeBig = 131072;
        this.cdnChunkCheckSize = 131072;
        this.maxDownloadRequests = 4;
        this.maxDownloadRequestsBig = 4;
        this.bigFileSizeFrom = 10485760;
        this.maxCdnParts = (int) (FileLoader.DEFAULT_MAX_FILE_SIZE / 131072);
        this.downloadChunkSizeAnimation = 131072;
        this.maxDownloadRequestsAnimation = 4;
        this.preloadTempBuffer = new byte[24];
        this.state = 0;
        this.uiRequestTokens = new ArrayList();
        this.cancelAfterNoStreamListeners = new FileLoadOperation$$ExternalSyntheticLambda6(this);
        updateParams();
        TLRPC.TL_inputSecureFileLocation r0 = new TLRPC.TL_inputSecureFileLocation();
        this.location = r0;
        TLRPC.TL_secureFile r82 = r8.secureFile;
        r0.id = r82.id;
        r0.access_hash = r82.access_hash;
        this.datacenterId = r82.dc_id;
        this.totalBytesCount = r82.size;
        this.allowDisordererFileSave = true;
        this.currentType = 67108864;
        this.ext = ".jpg";
    }

    public FileLoadOperation(TLRPC.Document r12, Object r13) {
        this.FULL_LOGS = false;
        this.downloadChunkSize = 32768;
        this.downloadChunkSizeBig = 131072;
        this.cdnChunkCheckSize = 131072;
        this.maxDownloadRequests = 4;
        this.maxDownloadRequestsBig = 4;
        this.bigFileSizeFrom = 10485760;
        this.maxCdnParts = (int) (FileLoader.DEFAULT_MAX_FILE_SIZE / 131072);
        this.downloadChunkSizeAnimation = 131072;
        this.maxDownloadRequestsAnimation = 4;
        this.preloadTempBuffer = new byte[24];
        this.state = 0;
        this.uiRequestTokens = new ArrayList();
        this.cancelAfterNoStreamListeners = new FileLoadOperation$$ExternalSyntheticLambda6(this);
        updateParams();
        this.parentObject = r13;     // Catch: Exception -> L7
        this.isStory = r13 instanceof TL_stories.TL_storyItem;     // Catch: Exception -> L7
        this.fileMetadata = FileLoader.getFileMetadataFromParent(this.currentAccount, r13);     // Catch: Exception -> L7
        if ((r12 instanceof TLRPC.TL_documentEncrypted) == false) goto L10;
        TLRPC.TL_inputEncryptedFileLocation r132 = new TLRPC.TL_inputEncryptedFileLocation();     // Catch: Exception -> L7
        this.location = r132;     // Catch: Exception -> L7
        r132.id = r12.id;     // Catch: Exception -> L7
        r132.access_hash = r12.access_hash;     // Catch: Exception -> L7
        int r133 = r12.dc_id;     // Catch: Exception -> L7
        this.datacenterId = r133;     // Catch: Exception -> L7
        this.initialDatacenterId = r133;     // Catch: Exception -> L7
        byte[] r134 = new byte[32];     // Catch: Exception -> L7
        this.iv = r134;     // Catch: Exception -> L7
        System.arraycopy(r12.iv, 0, r134, 0, r134.length);     // Catch: Exception -> L7
        this.key = r12.key;     // Catch: Exception -> L7
    L21:
        if ("application/x-tgsticker".equals(r12.mime_type) == false) goto L23;
    L26:
        boolean r135 = true;
    L27:
        this.ungzip = r135;     // Catch: Exception -> L7
        long r3 = r12.size;     // Catch: Exception -> L7
        this.totalBytesCount = r3;     // Catch: Exception -> L7
        if (this.key != null) goto L30;
    L32:
        String r136 = FileLoader.getDocumentFileName(r12);     // Catch: Exception -> L7
        this.ext = r136;     // Catch: Exception -> L7
        if (r136 == null) goto L38;
        int r137 = r136.lastIndexOf(46);     // Catch: Exception -> L7
        if (r137 == (-1)) goto L38;
        this.ext = this.ext.substring(r137);     // Catch: Exception -> L7
    L40:
        if ("audio/ogg".equals(r12.mime_type) == false) goto L43;
        this.currentType = ConnectionsManager.FileTypeAudio;     // Catch: Exception -> L7
    L47:
        if (this.ext.length() > 1) goto L56;
        this.ext = FileLoader.getExtensionByMimeType(r12.mime_type);     // Catch: Exception -> L7
        return;
    L56:
        return;
    L43:
        if (FileLoader.isVideoMimeType(r12.mime_type) == false) goto L45;
        this.currentType = 33554432;     // Catch: Exception -> L7
        goto L47
    L45:
        this.currentType = 67108864;     // Catch: Exception -> L7
    L38:
        this.ext = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Exception -> L7
        goto L40
    L30:
        if ((r3 % 16) == 0) goto L32;
        long r5 = 16 - (r3 % 16);     // Catch: Exception -> L7
        this.bytesCountPadding = r5;     // Catch: Exception -> L7
        this.totalBytesCount = r3 + r5;     // Catch: Exception -> L7
        goto L32
    L23:
        if ("application/x-tgwallpattern".equals(r12.mime_type) == true) goto L26;
        r135 = false;
        goto L27
    L10:
        if ((r12 instanceof TLRPC.TL_document) == false) goto L21;
        TLRPC.TL_inputDocumentFileLocation r138 = new TLRPC.TL_inputDocumentFileLocation();     // Catch: Exception -> L7
        this.location = r138;     // Catch: Exception -> L7
        long r32 = r12.id;     // Catch: Exception -> L7
        r138.id = r32;     // Catch: Exception -> L7
        this.documentId = r32;     // Catch: Exception -> L7
        r138.access_hash = r12.access_hash;     // Catch: Exception -> L7
        byte[] r33 = r12.file_reference;     // Catch: Exception -> L7
        r138.file_reference = r33;     // Catch: Exception -> L7
        r138.thumb_size = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Exception -> L7
        if (r33 != null) goto L14;
        r138.file_reference = new byte[0];     // Catch: Exception -> L7
    L14:
        int r139 = r12.dc_id;     // Catch: Exception -> L7
        this.datacenterId = r139;     // Catch: Exception -> L7
        this.initialDatacenterId = r139;     // Catch: Exception -> L7
        this.allowDisordererFileSave = true;     // Catch: Exception -> L7
        int r1310 = r12.attributes.size();     // Catch: Exception -> L7
        int r34 = 0;
    L15:
        if (r34 >= r1310) goto L21;
        if ((r12.attributes.get(r34) instanceof TLRPC.TL_documentAttributeVideo) == true) goto L18;
        r34 = r34 + 1;     // Catch: Exception -> L7
        goto L15
    L18:
        this.supportsPreloading = true;     // Catch: Exception -> L7
        this.preloadPrefixSize = r12.attributes.get(r34).preload_prefix_size;     // Catch: Exception -> L7
    L7:
        e = move-exception;
        FileLog.e(e);
        onFail(true, 0);
    }

    private void addPart(ArrayList<Range> r15, long r16, long r18, boolean r20) {
        if (r15 != null) goto L5;
        return;
    L5:
        if (r18 < r16) goto L58;
        int r1 = r15.size();
        boolean r9 = false;
        int r2 = 0;
    L9:
        if (r2 >= r1) goto L28;
        Range r11 = r15.get(r2);
        if (r16 > Range.m4019$$Nest$fgetstart(r11)) goto L22;
        if (r18 >= Range.m4018$$Nest$fgetend(r11)) goto L14;
        if (r18 > Range.m4019$$Nest$fgetstart(r11)) goto L19;
    L27:
        r2 = r2 + 1;
        goto L9
    L19:
        Range.m4021$$Nest$fputstart(r11, r18);
    L15:
        long r4 = r16;
    L16:
        r9 = true;
    L29:
        if (r20 == false) goto L56;
        if (r9 == false) goto L43;
        ArrayList r12 = new ArrayList(r15);
        if (this.fileWriteRunnable == null) goto L34;
        filesQueue.cancelRunnable(this.fileWriteRunnable);
    L34:
        monitor-enter(this);
        this.writingToFilePartsStream = true;     // Catch: Throwable -> L39
        monitor-exit(this);     // Catch: Throwable -> L39
        DispatchQueue r0 = filesQueue;
        FileLoadOperation$$ExternalSyntheticLambda25 r22 = new FileLoadOperation$$ExternalSyntheticLambda25(this, r12);
        this.fileWriteRunnable = r22;
        r0.postRunnable(r22);
        notifyStreamListeners();
        return;
    L39:
        th = move-exception;
        throw th;
    L43:
        if (BuildVars.LOGS_ENABLED == false) goto L57;
        FileLog.e(this.cacheFileFinal + " downloaded duplicate file part " + r4 + " - " + r18);
        return;
    L57:
        return;
    L56:
        return;
    L14:
        r15.remove(r2);
        goto L15
    L22:
        if (r18 < Range.m4018$$Nest$fgetend(r11)) goto L23;
        r4 = r16;
        if (r4 >= Range.m4018$$Nest$fgetend(r11)) goto L27;
        Range.m4020$$Nest$fputend(r11, r4);
        goto L16
    L23:
        r4 = r16;
        r15.add(0, new Range(Range.m4019$$Nest$fgetstart(r11), r4, null));
        Range.m4021$$Nest$fputstart(r11, r18);
        goto L16
    L28:
        r4 = r16;
        goto L29
    }

    private boolean canFinishPreload() {
        if (this.isStory == true) goto L5;
        return false;
    L5:
        if (this.priority >= 3) goto L10;
        return true;
    L10:
        return false;
    }

    private void cancel(boolean r3) {
        Utilities.stageQueue.postRunnable(new FileLoadOperation$$ExternalSyntheticLambda24(this, r3));
    }

    private void cancelOnStage(boolean r3) {
        if (this.state != 3) goto L5;
    L7:
        if (r3 == false) goto L59;
        File r32 = this.cacheFileFinal;
        if (r32 != null) goto L55;
    L16:
        File r33 = this.cacheFileTemp;
        if (r33 != null) goto L51;
    L24:
        File r34 = this.cacheFileParts;
        if (r34 != null) goto L49;
    L32:
        File r35 = this.cacheIvTemp;
        if (r35 != null) goto L57;
    L40:
        File r36 = this.cacheFilePreload;
        if (r36 == null) goto L60;
    L46:
        e = move-exception;
        FileLog.e(e);
        return;
    L43:
        if (r36.delete() == true) goto L61;
        this.cacheFilePreload.deleteOnExit();     // Catch: Exception -> L46
        return;
    L61:
        return;
    L60:
        return;
    L57:
    L38:
        e = move-exception;
        FileLog.e(e);
        goto L40
    L35:
        if (r35.delete() == true) goto L40;
        this.cacheIvTemp.deleteOnExit();     // Catch: Exception -> L38
        goto L40
    L49:
    L30:
        e = move-exception;
        FileLog.e(e);
        goto L32
    L27:
        if (r34.delete() == true) goto L32;
        this.cacheFileParts.deleteOnExit();     // Catch: Exception -> L30
        goto L32
    L51:
    L22:
        e = move-exception;
        FileLog.e(e);
        goto L24
    L19:
        if (r33.delete() == true) goto L24;
        this.cacheFileTemp.deleteOnExit();     // Catch: Exception -> L22
        goto L24
    L55:
    L14:
        e = move-exception;
        FileLog.e(e);
        goto L16
    L11:
        if (r32.delete() == true) goto L16;
        this.cacheFileFinal.deleteOnExit();     // Catch: Exception -> L14
        goto L16
    L59:
        return;
    L5:
        if (this.state == 2) goto L7;
        this.state = 5;
        cancelRequests(new FileLoadOperation$$ExternalSyntheticLambda4(this));
        goto L7
    }

    private void cancelRequests(Runnable r12) {
        if (r12 == null) goto L5;
        String r1 = " with callback";
    L6:
        FileLog.d("cancelRequests".concat(r1));
        int[] r2 = new int[1];
        if (this.requestInfos == null) goto L36;
        int[] r5 = new int[2];
        int r6 = 0;
    L10:
        if (r6 >= this.requestInfos.size()) goto L23;
        RequestInfo r7 = this.requestInfos.get(r6);
        if (r7.requestToken == 0) goto L22;
        r7.cancelling = true;
        if (r12 != null) goto L16;
        r7.cancelled = true;
        FileLog.d("cancelRequests cancel " + r7.requestToken);
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(r7.requestToken, true);
    L18:
        if (r7.connectionType != 2) goto L20;
        char r8 = 0;
    L21:
        r5[r8] = r5[r8] + r7.chunkSize;
        goto L22
    L20:
        r8 = 1;
        goto L21
    L16:
        r7.whenCancelled = new FileLoadOperation$$ExternalSyntheticLambda7(r7, r2, r12);
        r2[0] = r2[0] + 1;
        FileLog.d("cancelRequests cancel " + r7.requestToken + " with callback");
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(r7.requestToken, true, new FileLoadOperation$$ExternalSyntheticLambda8(r7));
    L22:
        r6 = r6 + 1;
        goto L10
    L23:
        int r0 = 0;
    L24:
        if (r0 >= 2) goto L36;
        if (r0 != 0) goto L27;
        int r13 = 2;
    L29:
        if (r5[r0] <= 1048576) goto L35;
        if (this.isCdn == false) goto L33;
        int r62 = this.cdnDatacenterId;
    L34:
        ConnectionsManager.getInstance(this.currentAccount).discardConnection(r62, r13);
        goto L35
    L33:
        r62 = this.datacenterId;
    L35:
        r0 = r0 + 1;
        goto L24
    L27:
        r13 = 65538;
    L36:
        if (r12 != null) goto L38;
        return;
    L38:
        if (r2[0] != 0) goto L48;
        r12.run();
        return;
    L48:
        return;
    L5:
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    private void cleanup() {
        RandomAccessFile r1 = this.fileOutputStream;     // Catch: Exception -> L11
        if (r1 == null) goto L90;
        r1.getChannel().close();     // Catch: Exception -> L7
    L9:
        this.fileOutputStream.close();     // Catch: Exception -> L11
        this.fileOutputStream = null;     // Catch: Exception -> L11
        goto L90
    L7:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L11
    L90:
        RandomAccessFile r12 = this.preloadStream;     // Catch: Exception -> L21
        if (r12 == null) goto L92;
        r12.getChannel().close();     // Catch: Exception -> L17
    L19:
        this.preloadStream.close();     // Catch: Exception -> L21
        this.preloadStream = null;     // Catch: Exception -> L21
        goto L92
    L17:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L21
    L92:
        RandomAccessFile r13 = this.fileReadStream;     // Catch: Exception -> L31
        if (r13 != null) goto L88;
    L75:
    L50:
        e = move-exception;
        FileLog.e(e);
    L78:
        RandomAccessFile r14 = this.fiv;     // Catch: Exception -> L56
        if (r14 == null) goto L59;
        r14.close();     // Catch: Exception -> L56
        this.fiv = null;     // Catch: Exception -> L56
    L59:
        if (this.delayedRequestInfos == null) goto L99;
        int r15 = 0;
    L61:
        int r2 = this.delayedRequestInfos.size();
        ArrayList<RequestInfo> r3 = this.delayedRequestInfos;
        if (r15 >= r2) goto L73;
        RequestInfo r22 = r3.get(r15);
        if (RequestInfo.m4024$$Nest$fgetresponse(r22) == null) goto L67;
        RequestInfo.m4024$$Nest$fgetresponse(r22).disableFree = false;
        RequestInfo.m4024$$Nest$fgetresponse(r22).freeResources();
    L72:
        r15 = r15 + 1;
        goto L61
    L67:
        if (RequestInfo.m4026$$Nest$fgetresponseWeb(r22) == null) goto L70;
        RequestInfo.m4026$$Nest$fgetresponseWeb(r22).disableFree = false;
        RequestInfo.m4026$$Nest$fgetresponseWeb(r22).freeResources();
        goto L72
    L70:
        if (RequestInfo.m4025$$Nest$fgetresponseCdn(r22) == null) goto L72;
        RequestInfo.m4025$$Nest$fgetresponseCdn(r22).disableFree = false;
        RequestInfo.m4025$$Nest$fgetresponseCdn(r22).freeResources();
        goto L72
    L73:
        r3.clear();
        return;
    L99:
        return;
    L56:
        e = move-exception;
        FileLog.e(e);
        goto L59
    L34:
        if (this.filePartsStream == null) goto L78;
        monitor-enter(this);     // Catch: Exception -> L50
    L40:
        th = move-exception;
        throw th;     // Catch: Exception -> L50
    L37:
        if (this.writingToFilePartsStream == false) goto L80;
        this.closeFilePartsStreamOnWriteEnd = true;     // Catch: Throwable -> L40
    L46:
        monitor-exit(this);     // Catch: Throwable -> L40
    L80:
        this.filePartsStream.getChannel().close();     // Catch: Throwable -> L40 Exception -> L42
    L44:
        this.filePartsStream.close();     // Catch: Throwable -> L40
        this.filePartsStream = null;     // Catch: Throwable -> L40
        goto L46
    L42:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L40
        goto L44
    L88:
        r13.getChannel().close();     // Catch: Exception -> L27
    L29:
        this.fileReadStream.close();     // Catch: Exception -> L31
        this.fileReadStream = null;     // Catch: Exception -> L31
        goto L75
    L27:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L31
    L31:
        e = move-exception;
        FileLog.e(e);
    L21:
        e = move-exception;
        FileLog.e(e);
    L11:
        e = move-exception;
        FileLog.e(e);
        goto L90
    }

    private void clearOperation(RequestInfo r16, boolean r17, boolean r18) {
        int[] r7 = new int[2];
        long r1 = LongCompanionObject.MAX_VALUE;
        int r9 = 0;
    L4:
        if (r9 >= this.requestInfos.size()) goto L19;
        RequestInfo r10 = this.requestInfos.get(r9);
        long r11 = Math.min(RequestInfo.m4023$$Nest$fgetoffset(r10), r1);
        if (this.isPreloadVideoOperation == false) goto L9;
        this.requestedPreloadedBytesRanges.remove(Long.valueOf(RequestInfo.m4023$$Nest$fgetoffset(r10)));
    L10:
        if (r16 == r10) goto L18;
        if (r10.requestToken == 0) goto L18;
        r10.cancelling = true;
        if (r18 == false) goto L17;
        this.cancelledRequestInfos.add(r10);
        r10.whenCancelled = new FileLoadOperation$$ExternalSyntheticLambda9(this, r10);
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(r10.requestToken, true, new FileLoadOperation$$ExternalSyntheticLambda10(r10));
        goto L18
    L17:
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(r10.requestToken, true);
        r10.cancelled = true;
    L18:
        r9 = r9 + 1;
        r1 = r11;
        goto L4
    L9:
        removePart(this.notRequestedBytesRanges, RequestInfo.m4023$$Nest$fgetoffset(r10), RequestInfo.m4023$$Nest$fgetoffset(r10) + r10.chunkSize);
        goto L10
    L19:
        int r3 = 0;
    L20:
        if (r3 >= 2) goto L32;
        if (r3 != 0) goto L23;
        int r4 = 2;
    L25:
        if (r7[r3] <= 1048576) goto L31;
        if (this.isCdn == false) goto L29;
        int r5 = this.cdnDatacenterId;
    L30:
        ConnectionsManager.getInstance(this.currentAccount).discardConnection(r5, r4);
        goto L31
    L29:
        r5 = this.datacenterId;
    L31:
        r3 = r3 + 1;
        goto L20
    L23:
        r4 = 65538;
        goto L25
    L32:
        this.requestInfos.clear();
        AndroidUtilities.runOnUIThread(new FileLoadOperation$$ExternalSyntheticLambda11(this));
        long r6 = r1;
        int r92 = 0;
    L33:
        int r12 = this.delayedRequestInfos.size();
        ArrayList<RequestInfo> r2 = this.delayedRequestInfos;
        if (r92 >= r12) goto L49;
        RequestInfo r102 = r2.get(r92);
        if (this.isPreloadVideoOperation == false) goto L38;
        this.requestedPreloadedBytesRanges.remove(Long.valueOf(RequestInfo.m4023$$Nest$fgetoffset(r102)));
    L40:
        if (RequestInfo.m4024$$Nest$fgetresponse(r102) == null) goto L43;
        RequestInfo.m4024$$Nest$fgetresponse(r102).disableFree = false;
        RequestInfo.m4024$$Nest$fgetresponse(r102).freeResources();
    L48:
        r6 = Math.min(RequestInfo.m4023$$Nest$fgetoffset(r102), r6);
        r92 = r92 + 1;
        goto L33
    L43:
        if (RequestInfo.m4026$$Nest$fgetresponseWeb(r102) == null) goto L46;
        RequestInfo.m4026$$Nest$fgetresponseWeb(r102).disableFree = false;
        RequestInfo.m4026$$Nest$fgetresponseWeb(r102).freeResources();
        goto L48
    L46:
        if (RequestInfo.m4025$$Nest$fgetresponseCdn(r102) == null) goto L48;
        RequestInfo.m4025$$Nest$fgetresponseCdn(r102).disableFree = false;
        RequestInfo.m4025$$Nest$fgetresponseCdn(r102).freeResources();
        goto L48
    L38:
        removePart(this.notRequestedBytesRanges, RequestInfo.m4023$$Nest$fgetoffset(r102), RequestInfo.m4023$$Nest$fgetoffset(r102) + r102.chunkSize);
        goto L40
    L49:
        r2.clear();
        this.requestsCount = 0;
        if (r17 == true) goto L56;
        if (this.isPreloadVideoOperation == false) goto L56;
        this.requestedBytesCount = this.totalPreloadedBytes;
        return;
    L56:
        if (this.notLoadedBytesRanges != null) goto L72;
        this.downloadedBytes = r6;
        this.requestedBytesCount = r6;
        return;
    }

    private void copyNotLoadedRanges() {
        if (this.notLoadedBytesRanges != null) goto L5;
        return;
    L5:
        this.notLoadedBytesRangesCopy = new ArrayList(this.notLoadedBytesRanges);
    }

    private void delayRequestInfo(RequestInfo r2) {
        this.delayedRequestInfos.add(r2);
        if (RequestInfo.m4024$$Nest$fgetresponse(r2) == null) goto L7;
        RequestInfo.m4024$$Nest$fgetresponse(r2).disableFree = true;
        return;
    L7:
        if (RequestInfo.m4026$$Nest$fgetresponseWeb(r2) == null) goto L11;
        RequestInfo.m4026$$Nest$fgetresponseWeb(r2).disableFree = true;
        return;
    L11:
        if (RequestInfo.m4025$$Nest$fgetresponseCdn(r2) == null) goto L14;
        RequestInfo.m4025$$Nest$fgetresponseCdn(r2).disableFree = true;
        return;
    }

    private long findNextPreloadDownloadOffset(long r16, long r18, NativeByteBuffer r20) {
        int r2 = r20.limit();
        long r3 = r16;
    L4:
        if (this.preloadTempBuffer == null) goto L6;
        int r5 = 16;
    L8:
        if (r3 < (r18 - r5)) goto L54;
        long r10 = r18 + r2;
        if (r3 >= r10) goto L54;
        if (r3 >= (r10 - 16)) goto L14;
        if (this.preloadTempBufferCount == 0) goto L23;
        r20.position(0);
        byte[] r52 = this.preloadTempBuffer;
        int r12 = this.preloadTempBufferCount;
        r20.readBytes(r52, r12, 16 - r12, false);
        this.preloadTempBufferCount = 0;
        long r162 = 0;
    L26:
        byte[] r53 = this.preloadTempBuffer;
        int r7 = ((((r53[0] & UByte.MAX_VALUE) << 24) + ((r53[1] & UByte.MAX_VALUE) << 16)) + ((r53[2] & UByte.MAX_VALUE) << 8)) + (r53[3] & UByte.MAX_VALUE);
        if (r7 == 0) goto L28;
        if (r7 != 1) goto L32;
        r7 = ((((r53[12] & UByte.MAX_VALUE) << 24) + ((r53[13] & UByte.MAX_VALUE) << 16)) + ((r53[14] & UByte.MAX_VALUE) << 8)) + (r53[15] & UByte.MAX_VALUE);
    L32:
        if (r53[4] == 109) goto L34;
    L41:
        r3 = r3 + r7;
        if (r3 < r10) goto L4;
        return r3;
    L34:
        if (r53[5] != 111) goto L41;
        if (r53[6] != 111) goto L41;
        if (r53[7] != 118) goto L41;
        return -r7;
    L28:
        return r162;
    L23:
        r162 = 0;
        long r8 = r3 - r18;
        if (r8 > 2147483647L) goto L44;
        r20.position((int) r8);
        r20.readBytes(this.preloadTempBuffer, 0, 16, false);
        goto L26
    L44:
        GlShader$$ExternalSyntheticBUOutline1.m("!!!");
        return 0;
    L14:
        long r22 = r10 - r3;
        if (r22 > 2147483647L) goto L18;
        this.preloadTempBufferCount = (int) r22;
        r20.position(r20.limit() - this.preloadTempBufferCount);
        r20.readBytes(this.preloadTempBuffer, 0, this.preloadTempBufferCount, false);
        return r10;
    L18:
        GlShader$$ExternalSyntheticBUOutline1.m("!!!");
        return 0;
    L54:
        return 0;
    L6:
        r5 = 0;
        goto L8
    }

    public static long floorDiv(long r6, long r8) {
        long r0 = r6 / r8;
        if ((r6 ^ r8) < 0) goto L5;
        return r0;
    L5:
        if ((r8 * r0) != r6) goto L7;
        return r0;
    L7:
        return r0 - 1;
    }

    private long getDownloadedLengthFromOffsetInternal(ArrayList<Range> r11, long r12, long r14) {
        if (r11 == null) goto L33;
        if (this.state == 3) goto L33;
        if (r11.isEmpty() == true) goto L33;
        int r0 = r11.size();
        Range r3 = null;
        int r4 = 0;
    L10:
        if (r4 >= r0) goto L23;
        Range r5 = r11.get(r4);
        if (r12 > Range.m4019$$Nest$fgetstart(r5)) goto L18;
        if (r3 != null) goto L15;
    L16:
        r3 = r5;
        goto L18
    L15:
        if (Range.m4019$$Nest$fgetstart(r5) < Range.m4019$$Nest$fgetstart(r3)) goto L16;
    L18:
        if (Range.m4019$$Nest$fgetstart(r5) > r12) goto L22;
        if (Range.m4018$$Nest$fgetend(r5) <= r12) goto L22;
        long r42 = 0;
    L25:
        if (r42 != 0) goto L27;
        return 0;
    L27:
        if (r3 == null) goto L31;
        return Math.min(r14, Range.m4019$$Nest$fgetstart(r3) - r12);
    L31:
        return Math.min(r14, Math.max(this.totalBytesCount - r12, 0));
    L22:
        r4 = r4 + 1;
        goto L10
    L23:
        r42 = r14;
    L33:
        if (this.state != 3) goto L35;
        return r14;
    L35:
        long r10 = this.downloadedBytes;
        if (r10 != 0) goto L39;
        return 0;
    L39:
        return Math.min(r14, Math.max(r10 - r12, 0));
    }

    private /* synthetic */ void lambda$addPart$2(ArrayList r11) {
        long r0 = System.currentTimeMillis();
    L9:
        e = move-exception;
        FileLog.e(e);
        if (AndroidUtilities.isENOSPC(e) == false) goto L37;
        LaunchActivity.checkFreeDiscSpaceStatic(1);
    L39:
        this.totalTime += System.currentTimeMillis() - r0;
        return;
    L37:
        if (AndroidUtilities.isEROFS(e) == false) goto L39;
        SharedConfig.checkSdCard(this.cacheFileFinal);
        goto L39
    L4:
        if (this.filePartsStream != null) goto L6;
        return;
    L6:
        int r2 = r11.size();     // Catch: Exception -> L9
        int r3 = (r2 * 16) + 4;     // Catch: Exception -> L9
        ImmutableByteArrayOutputStream r4 = filesQueueByteBuffer;     // Catch: Exception -> L9
        if (r4 != null) goto L11;
        filesQueueByteBuffer = new ImmutableByteArrayOutputStream(r3);     // Catch: Exception -> L9
    L12:
        filesQueueByteBuffer.writeInt(r2);     // Catch: Exception -> L9
        int r5 = 0;
    L13:
        if (r5 >= r2) goto L15;
        Range r6 = (Range) r11.get(r5);     // Catch: Exception -> L9
        filesQueueByteBuffer.writeLong(Range.m4019$$Nest$fgetstart(r6));     // Catch: Exception -> L9
        filesQueueByteBuffer.writeLong(Range.m4018$$Nest$fgetend(r6));     // Catch: Exception -> L9
        r5 = r5 + 1;     // Catch: Exception -> L9
        goto L13
    L15:
        monitor-enter(this);     // Catch: Exception -> L9
        RandomAccessFile r112 = this.filePartsStream;     // Catch: Throwable -> L20
        if (r112 != null) goto L22;
        monitor-exit(this);     // Catch: Throwable -> L20
        return;
    L22:
        r112.seek(0);     // Catch: Throwable -> L20
        this.filePartsStream.write(filesQueueByteBuffer.buf, 0, r3);     // Catch: Throwable -> L20
        this.writingToFilePartsStream = false;     // Catch: Throwable -> L20
        if (this.closeFilePartsStreamOnWriteEnd == true) goto L41;
    L29:
        monitor-exit(this);     // Catch: Throwable -> L20
        goto L39
    L41:
        this.filePartsStream.getChannel().close();     // Catch: Throwable -> L20 Exception -> L26
    L28:
        this.filePartsStream.close();     // Catch: Throwable -> L20
        this.filePartsStream = null;     // Catch: Throwable -> L20
        goto L29
    L26:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        throw th;     // Catch: Exception -> L9
    L11:
        r4.reset();     // Catch: Exception -> L9
        goto L12
    }

    private /* synthetic */ void lambda$cancel$13(boolean r1) {
        cancelOnStage(r1);
    }

    private /* synthetic */ void lambda$cancelOnStage$14() {
        if (this.state != 5) goto L6;
        onFail(false, 1);
        return;
    }

    private /* synthetic */ void lambda$clearOperation$24(RequestInfo r2) {
        r2.whenCancelled = null;
        this.cancelledRequestInfos.remove(r2);
        r2.cancelled = true;
    }

    private /* synthetic */ void lambda$clearOperation$26() {
        this.uiRequestTokens.clear();
    }

    private /* synthetic */ void lambda$getCurrentFile$3(File[] r4, CountDownLatch r5) {
        if (this.state == 3) goto L5;
    L7:
        r4[0] = this.cacheFileTemp;
    L8:
        r5.countDown();
        return;
    L5:
        if (this.preloadFinished == true) goto L7;
        r4[0] = this.cacheFileFinal;
        goto L8
    }

    private /* synthetic */ void lambda$getDownloadedLengthFromOffset$4(long[] r9, long r10, long r12, CountDownLatch r14) {
        FileLoadOperation r2 = this;
        r9[0] = r2.getDownloadedLengthFromOffsetInternal(this.notLoadedBytesRanges, r10, r12);     // Catch: Throwable -> L7
    L13:
        if (r2.state != 3) goto L16;
        r9[1] = 1;
    L18:
        r14.countDown();
        return;
    L16:
        if (r2.state != 4) goto L18;
        r9[1] = -1;
    L7:
        th = th;
    L11:
        FileLog.e(th);
        r9[0] = 0;
    L9:
        th = th;
        r2 = this;
        goto L11
    }

    private /* synthetic */ void lambda$new$6() {
        pause();
        FileLoader.getInstance(this.currentAccount).cancelLoadFile(getFileName());
    }

    private /* synthetic */ void lambda$onFail$23(int r2) {
        FileLoadOperationDelegate r0 = this.delegate;
        if (r0 == null) goto L5;
        r0.didFailedLoadingFile(this, r2);
    L5:
        notifyStreamListeners();
    }

    private /* synthetic */ void lambda$onFinishLoadingFile$17(boolean r2) {
        onFinishLoadingFile(r2, 0, false);     // Catch: Exception -> L5
        return;
    L5:
        onFail(false, 0);
    }

    private /* synthetic */ void lambda$onFinishLoadingFile$18() {
        onFail(false, 0);
    }

    private /* synthetic */ void lambda$onFinishLoadingFile$19(boolean r6) {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("finished downloading file to " + this.cacheFileFinal + " time = " + (System.currentTimeMillis() - this.startTime) + " dc = " + this.datacenterId + " size = " + AndroidUtilities.formatFileSize(this.totalBytesCount));
    L5:
        if (r6 == false) goto L25;
        int r62 = this.currentType;
        if (r62 != 50331648) goto L10;
        StatsController.getInstance(this.currentAccount).incrementReceivedItemsCount(ApplicationLoader.getCurrentNetworkType(), 3, 1);
        goto L25
    L10:
        if (r62 != 33554432) goto L13;
        StatsController.getInstance(this.currentAccount).incrementReceivedItemsCount(ApplicationLoader.getCurrentNetworkType(), 2, 1);
        goto L25
    L13:
        if (r62 != 16777216) goto L16;
        StatsController.getInstance(this.currentAccount).incrementReceivedItemsCount(ApplicationLoader.getCurrentNetworkType(), 4, 1);
        goto L25
    L16:
        if (r62 != 67108864) goto L25;
        String r63 = this.ext;
        if (r63 != null) goto L20;
    L24:
        StatsController.getInstance(this.currentAccount).incrementReceivedItemsCount(ApplicationLoader.getCurrentNetworkType(), 5, 1);
        goto L25
    L20:
        if (r63.toLowerCase().endsWith(HlsSegmentFormat.MP3) == false) goto L22;
    L23:
        StatsController.getInstance(this.currentAccount).incrementReceivedItemsCount(ApplicationLoader.getCurrentNetworkType(), 7, 1);
        goto L25
    L22:
        if (this.ext.toLowerCase().endsWith("m4a") == false) goto L24;
    L25:
        this.delegate.didFinishLoadingFile(this, this.cacheFileFinal);
    }

    private /* synthetic */ void lambda$onFinishLoadingFile$20(File r5, File r6, File r7, File r8, boolean r9) {
        if (r5 == null) goto L4;
        r5.delete();
    L4:
        if (r6 == null) goto L6;
        r6.delete();
    L6:
        if (r7 == null) goto L8;
        r7.delete();
    L8:
        if (r8 != null) goto L10;
    L81:
        Utilities.stageQueue.postRunnable(new FileLoadOperation$$ExternalSyntheticLambda30(this, r9));
        return;
    L10:
        if (this.ungzip == false) goto L24;
        GZIPInputStream r52 = new GZIPInputStream(new FileInputStream(r8));     // Catch: Throwable -> L17 ZipException -> L83
        FileLoader.copyFile(r52, this.cacheFileGzipTemp, 2097152);     // Catch: Throwable -> L17 ZipException -> L83
        r52.close();     // Catch: Throwable -> L17 ZipException -> L83
        r8.delete();     // Catch: Throwable -> L17 ZipException -> L83
        File r53 = this.cacheFileGzipTemp;     // Catch: Throwable -> L17 ZipException -> L83
        this.ungzip = false;     // Catch: Throwable -> L14 ZipException -> L16
    L13:
        r8 = r53;
        goto L24
    L16:
        r8 = r53;
    L14:
        th = th;
    L19:
        FileLog.e(th);
        if (BuildVars.LOGS_ENABLED == false) goto L13;
        FileLog.e("unable to ungzip temp = " + r8 + " to final = " + this.cacheFileFinal);
        goto L13
    L17:
        th = th;
        r53 = r8;
    L22:
        this.ungzip = false;
    L24:
        if (this.ungzip == false) goto L26;
        Utilities.stageQueue.postRunnable(new FileLoadOperation$$ExternalSyntheticLambda29(this));
        return;
    L26:
        if ((this.parentObject instanceof TLRPC.TL_theme) == true) goto L89;
    L50:
        e = move-exception;
        FileLog.e(e);
    L31:
        boolean r54 = false;
    L56:
        if (r54 == false) goto L58;
    L65:
        if (r54 == false) goto L67;
        this.cacheFileFinalReady = true;
        if (this.pathSaveData == null) goto L81;
        if (this.cacheFileFinal.exists() == false) goto L81;
        this.delegate.saveFilePath(this.pathSaveData, this.cacheFileFinal);
        goto L81
    L67:
        if (BuildVars.LOGS_ENABLED == false) goto L69;
        FileLog.e("unable to rename temp = " + r8 + " to final = " + this.cacheFileFinal + " retry = " + this.renameRetryCount);
    L69:
        int r55 = this.renameRetryCount + 1;
        this.renameRetryCount = r55;
        if (r55 >= 3) goto L73;
        this.state = 1;
        Utilities.stageQueue.postRunnable(new FileLoadOperation$$ExternalSyntheticLambda28(this, r9), 200);
        return;
    L73:
        this.cacheFileFinal = r8;
        this.cacheFileFinalReady = false;
        goto L81
    L58:
        if (this.renameRetryCount != 3) goto L65;
        r54 = AndroidUtilities.copyFile(r8, this.cacheFileFinal);     // Catch: Throwable -> L63
        if (r54 == false) goto L65;
        this.cacheFileFinal.delete();     // Catch: Throwable -> L63
    L63:
        th = move-exception;
        FileLog.e(th);
        goto L65
    L33:
        if (this.pathSaveData == null) goto L52;
        Object r56 = lockObject;     // Catch: Exception -> L50
        monitor-enter(r56);     // Catch: Exception -> L50
        this.cacheFileFinal = new File(this.storePath, this.storeFileName);     // Catch: Throwable -> L42
        int r0 = 1;
    L38:
        if (this.cacheFileFinal.exists() == false) goto L46;
        int r1 = this.storeFileName.lastIndexOf(46);     // Catch: Throwable -> L42
        if (r1 <= 0) goto L44;
        String r12 = this.storeFileName.substring(0, r1) + " (" + r0 + ")" + this.storeFileName.substring(r1);     // Catch: Throwable -> L42
    L45:
        this.cacheFileFinal = new File(this.storePath, r12);     // Catch: Throwable -> L42
        r0 = r0 + 1;     // Catch: Throwable -> L42
        goto L38
    L44:
        r12 = this.storeFileName + " (" + r0 + ")";     // Catch: Throwable -> L42
        goto L45
    L46:
        monitor-exit(r56);     // Catch: Throwable -> L42
    L42:
        th = move-exception;
        throw th;     // Catch: Exception -> L50
    L52:
        r54 = r8.renameTo(this.cacheFileFinal);     // Catch: Exception -> L50
        goto L56
    L89:
        r54 = AndroidUtilities.copyFile(r8, this.cacheFileFinal);     // Catch: Exception -> L29
    L29:
        e = move-exception;
        FileLog.e(e);
        goto L31
    }

    private /* synthetic */ void lambda$pause$7() {
        int r1 = 0;
        if (this.isStory == false) goto L10;
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.d("debug_loading: " + this.cacheFileFinal.getName() + " pause operation, clear requests");
    L7:
        clearOperation(null, false, true);
        return;
    L10:
        if (r1 >= this.requestInfos.size()) goto L12;
        ConnectionsManager.getInstance(this.currentAccount).failNotRunningRequest(this.requestInfos.get(r1).requestToken);
        r1 = r1 + 1;
        goto L10
    }

    private /* synthetic */ void lambda$processRequestResult$22(int r1) {
        this.uiRequestTokens.remove(Integer.valueOf(r1));
    }

    private /* synthetic */ void lambda$removeStreamListener$5(FileLoadOperationStream r3) {
        if (this.streamListeners != null) goto L5;
        return;
    L5:
        FileLog.e("FileLoadOperation " + getFileName() + " removing stream listener " + r3);
        this.streamListeners.remove(r3);
    }

    private /* synthetic */ void lambda$requestFileOffsets$21(TLObject r6, TLRPC.TL_error r7) {
        if (r7 == null) goto L7;
        onFail(false, 0);
        return;
    L7:
        if ((r6 instanceof Vector) == false) goto L44;
        this.requestingCdnOffsets = false;
        Vector r62 = (Vector) r6;
        if (r62.objects.isEmpty() == false) goto L11;
    L17:
        int r63 = 0;
    L19:
        if (r63 >= this.delayedRequestInfos.size()) goto L47;
        RequestInfo r72 = this.delayedRequestInfos.get(r63);
        if (this.notLoadedBytesRanges != null) goto L26;
        if (this.downloadedBytes == RequestInfo.m4023$$Nest$fgetoffset(r72)) goto L26;
        r63 = r63 + 1;
    L26:
        this.delayedRequestInfos.remove(r63);
        if (processRequestResult(r72, null) == false) goto L29;
        return;
    L29:
        if (RequestInfo.m4024$$Nest$fgetresponse(r72) == null) goto L33;
        RequestInfo.m4024$$Nest$fgetresponse(r72).disableFree = false;
        RequestInfo.m4024$$Nest$fgetresponse(r72).freeResources();
        return;
    L33:
        if (RequestInfo.m4026$$Nest$fgetresponseWeb(r72) == null) goto L37;
        RequestInfo.m4026$$Nest$fgetresponseWeb(r72).disableFree = false;
        RequestInfo.m4026$$Nest$fgetresponseWeb(r72).freeResources();
        return;
    L37:
        if (RequestInfo.m4025$$Nest$fgetresponseCdn(r72) == null) goto L46;
        RequestInfo.m4025$$Nest$fgetresponseCdn(r72).disableFree = false;
        RequestInfo.m4025$$Nest$fgetresponseCdn(r72).freeResources();
        return;
    L46:
        return;
    L47:
        return;
    L11:
        if (this.cdnHashes != null) goto L13;
        this.cdnHashes = new HashMap();
    L13:
        int r73 = 0;
    L15:
        if (r73 >= r62.objects.size()) goto L17;
        TLRPC.TL_fileHash r1 = (TLRPC.TL_fileHash) r62.objects.get(r73);
        this.cdnHashes.put(Long.valueOf(r1.offset), r1);
        r73 = r73 + 1;
        goto L15
    }

    private /* synthetic */ void lambda$setIsPreloadVideoOperation$12(boolean r3) {
        this.requestedBytesCount = 0;
        clearOperation(null, true, true);
        this.isPreloadVideoOperation = r3;
        startDownloadRequest(-1);
    }

    private /* synthetic */ void lambda$setStream$0(FileLoadOperationStream r3) {
        if (this.streamListeners != null) goto L5;
        this.streamListeners = new ArrayList();
    L5:
        if (r3 == null) goto L10;
        if (this.streamListeners.contains(r3) == true) goto L10;
        this.streamListeners.add(r3);
    L10:
        if (this.streamListeners.isEmpty() == true) goto L12;
        Utilities.stageQueue.cancelRunnable(this.cancelAfterNoStreamListeners);
    L12:
        if (r3 != null) goto L14;
        return;
    L14:
        if (this.state != 1) goto L16;
        return;
    L16:
        if (this.state == 0) goto L21;
        r3.newDataAvailable();
        return;
    }

    private /* synthetic */ void lambda$start$10() {
        startDownloadRequest(-1);
    }

    private /* synthetic */ void lambda$start$11(boolean[] r8) {
        if (this.isPreloadVideoOperation == true) goto L5;
    L7:
        boolean r82 = false;
    L8:
        int r0 = this.preloadPrefixSize;
        if (r0 > 0) goto L11;
    L15:
        boolean r02 = false;
    L16:
        long r3 = this.totalBytesCount;
        if (r3 == 0) goto L26;
        if (r82 == false) goto L20;
    L28:
        onFinishLoadingFile(false, 1, true);     // Catch: Exception -> L24
        return;
    L24:
        onFail(true, 0);
        return;
    L20:
        if (this.downloadedBytes == r3) goto L28;
        if (r02 == true) goto L28;
    L26:
        startDownloadRequest(-1);
        return;
    L11:
        if (this.downloadedBytes < r0) goto L15;
        if (canFinishPreload() == false) goto L15;
        r02 = true;
        goto L16
    L5:
        if (r8[0] == false) goto L7;
        r82 = true;
        goto L8
    }

    private /* synthetic */ void lambda$start$8(int r1) {
        this.uiRequestTokens.remove(Integer.valueOf(r1));
    }

    private /* synthetic */ void lambda$start$9(boolean r13, long r14, FileLoadOperationStream r16, boolean r17) {
        if (this.streamListeners != null) goto L5;
        this.streamListeners = new ArrayList();
    L5:
        int r1 = this.currentDownloadChunkSize;
        if (r13 == false) goto L21;
        long r8 = (r14 / r1) * r1;
        RequestInfo r12 = this.priorityRequestInfo;
        if (r12 == null) goto L19;
        if (RequestInfo.m4023$$Nest$fgetoffset(r12) == r8) goto L19;
        RequestInfo r15 = this.priorityRequestInfo;
        int r2 = r15.requestToken;
        this.requestInfos.remove(r15);
        AndroidUtilities.runOnUIThread(new FileLoadOperation$$ExternalSyntheticLambda20(this, r2));
        this.requestedBytesCount -= this.currentDownloadChunkSize;
        removePart(this.notRequestedBytesRanges, RequestInfo.m4023$$Nest$fgetoffset(this.priorityRequestInfo), RequestInfo.m4023$$Nest$fgetoffset(this.priorityRequestInfo) + this.currentDownloadChunkSize);
        if (this.priorityRequestInfo.requestToken == 0) goto L15;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.priorityRequestInfo.requestToken, true);
        this.requestsCount--;
    L15:
        if (BuildVars.DEBUG_VERSION == false) goto L17;
        FileLog.d("frame get cancel request at offset " + RequestInfo.m4023$$Nest$fgetoffset(this.priorityRequestInfo));
    L17:
        this.priorityRequestInfo = null;
    L19:
        if (this.priorityRequestInfo != null) goto L23;
        this.streamPriorityStartOffset = r8;
    L23:
        if (this.streamListeners.contains(r16) == true) goto L26;
        this.streamListeners.add(r16);
        FileLog.e("FileLoadOperation " + getFileName() + " start, adding stream " + r16);
    L26:
        if (this.streamListeners.isEmpty() == true) goto L28;
        Utilities.stageQueue.cancelRunnable(this.cancelAfterNoStreamListeners);
    L28:
        if (r17 == false) goto L38;
        if (this.preloadedBytesRanges != null) goto L32;
    L36:
        startDownloadRequest(-1);
        this.nextPartWasPreloaded = false;
        goto L38
    L32:
        if (getDownloadedLengthFromOffsetInternal(this.notLoadedBytesRanges, this.streamStartOffset, 1) != 0) goto L36;
        if (this.preloadedBytesRanges.get(Long.valueOf(this.streamStartOffset)) == null) goto L36;
        this.nextPartWasPreloaded = true;
    L38:
        if (this.notLoadedBytesRanges == null) goto L41;
        notifyStreamListeners();
        return;
    L41:
        return;
    L21:
        this.streamStartOffset = (r14 / r1) * r1;
        goto L23
    }

    private /* synthetic */ void lambda$startDownloadRequest$27(RequestInfo r2) {
        processRequestResult(r2, null);
        RequestInfo.m4024$$Nest$fgetresponse(r2).freeResources();
    }

    private /* synthetic */ void lambda$startDownloadRequest$28(int r4, RequestInfo r5, TLObject r6, TLRPC.TL_error r7) {
        int r0 = 0;
        this.reuploadingCdn = false;
        if ((r6 instanceof Vector) == false) goto L14;
        Vector r62 = (Vector) r6;
        if (r62.objects.isEmpty() == false) goto L7;
    L12:
        startDownloadRequest(r4);
        return;
    L7:
        if (this.cdnHashes != null) goto L10;
        this.cdnHashes = new HashMap();
    L10:
        if (r0 >= r62.objects.size()) goto L12;
        TLRPC.TL_fileHash r52 = (TLRPC.TL_fileHash) r62.objects.get(r0);
        this.cdnHashes.put(Long.valueOf(r52.offset), r52);
        r0 = r0 + 1;
        goto L10
    L14:
        if (r7 != null) goto L16;
        return;
    L16:
        if (r7.text.equals("FILE_TOKEN_INVALID") == false) goto L18;
    L22:
        this.isCdn = false;
        clearOperation(r5, false, false);
        startDownloadRequest(r4);
        return;
    L18:
        if (r7.text.equals("REQUEST_TOKEN_INVALID") == true) goto L22;
        onFail(false, 0);
    }

    private /* synthetic */ void lambda$startDownloadRequest$29(RequestInfo r10, int r11, int r12, TLObject r13, TLObject r14, TLRPC.TL_error r15) {
        if (r10.cancelled == false) goto L7;
        FileLog.e("received chunk but definitely cancelled offset=" + RequestInfo.m4023$$Nest$fgetoffset(r10) + " size=" + r10.chunkSize + " token=" + r10.requestToken);
        return;
    L7:
        if (r10.cancelling == false) goto L10;
        FileLog.e("received cancelled chunk after cancelRequests! offset=" + RequestInfo.m4023$$Nest$fgetoffset(r10) + " size=" + r10.chunkSize + " token=" + r10.requestToken);
    L10:
        if (this.requestInfos.contains(r10) == false) goto L12;
    L29:
        int r0 = 0;
    L31:
        if (r0 >= this.cancelledRequestInfos.size()) goto L42;
        RequestInfo r3 = this.cancelledRequestInfos.get(r0);
        if (r3 == null) goto L40;
        if (r3 == r10) goto L40;
        if (RequestInfo.m4023$$Nest$fgetoffset(r3) != RequestInfo.m4023$$Nest$fgetoffset(r10)) goto L40;
        if (r3.chunkSize != r10.chunkSize) goto L40;
        FileLog.e("received new chunk faster than cancelled one! received=" + r10.requestToken + " cancelled=" + r3.requestToken);
        this.cancelledRequestInfos.remove(r0);
        r0 = r0 + (-1);
    L40:
        r0 = r0 + 1;
        goto L31
    L42:
        if (BuildVars.LOGS_ENABLED == false) goto L45;
        FileLog.d("debug_loading: " + this.cacheFileFinal.getName() + " time=" + (System.currentTimeMillis() - r10.requestStartTime) + " dcId=" + r11 + " cdn=" + this.isCdn + " conType=" + r12 + " reqId" + r10.requestToken);
    L45:
        if (r10 == this.priorityRequestInfo) goto L47;
    L50:
        if (r15 == null) goto L68;
        Runnable r112 = r10.whenCancelled;
        if (r112 == null) goto L55;
        r112.run();
    L55:
        if (r15.code != (-2000)) goto L58;
        this.requestInfos.remove(r10);
        this.requestedBytesCount -= r10.chunkSize;
        removePart(this.notRequestedBytesRanges, RequestInfo.m4023$$Nest$fgetoffset(r10), RequestInfo.m4023$$Nest$fgetoffset(r10) + r10.chunkSize);
        return;
    L58:
        FileLoadOperation r02 = this;
        if (FileRefController.isFileRefError(r15.text) == false) goto L63;
        r02.requestReference(r10);
        return;
    L63:
        if ((r13 instanceof TLRPC.TL_upload_getCdnFile) == false) goto L70;
        if (r15.text.equals("FILE_TOKEN_INVALID") == false) goto L70;
        r02.isCdn = false;
        r02.clearOperation(r10, false, false);
        r02.startDownloadRequest(r12);
        return;
    L70:
        if ((r14 instanceof TLRPC.TL_upload_fileCdnRedirect) == false) goto L100;
        TLRPC.TL_upload_fileCdnRedirect r142 = (TLRPC.TL_upload_fileCdnRedirect) r14;
        if (r142.file_hashes.isEmpty() == false) goto L74;
    L80:
        byte[] r9 = r142.encryption_iv;
        if (r9 == null) goto L94;
        byte[] r113 = r142.encryption_key;
        if (r113 == null) goto L94;
        if (r9.length != 16) goto L94;
        if (r113.length != 32) goto L94;
        r02.isCdn = true;
        if (r02.notCheckedCdnRanges != null) goto L92;
        ArrayList<Range> r92 = new ArrayList();
        r02.notCheckedCdnRanges = r92;
        r92.add(new Range(0, r02.maxCdnParts, null));
    L92:
        r02.cdnDatacenterId = r142.dc_id;
        r02.cdnIv = r142.encryption_iv;
        r02.cdnKey = r142.encryption_key;
        r02.cdnToken = r142.file_token;
        r02.clearOperation(r10, false, false);
        r02.startDownloadRequest(r12);
        return;
    L94:
        Runnable r93 = r10.whenCancelled;
        if (r93 == null) goto L97;
        r93.run();
    L97:
        TLRPC.TL_error r94 = new TLRPC.TL_error();
        r94.text = "bad redirect response";
        r94.code = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
        r02.processRequestResult(r10, r94);
        return;
    L74:
        if (r02.cdnHashes != null) goto L76;
        r02.cdnHashes = new HashMap();
    L76:
        int r95 = 0;
    L78:
        if (r95 >= r142.file_hashes.size()) goto L80;
        TLRPC.TL_fileHash r114 = r142.file_hashes.get(r95);
        r02.cdnHashes.put(Long.valueOf(r114.offset), r114);
        r95 = r95 + 1;
        goto L78
    L100:
        if ((r14 instanceof TLRPC.TL_upload_cdnFileReuploadNeeded) == false) goto L106;
        if (r02.reuploadingCdn == true) goto L154;
        r02.clearOperation(r10, false, false);
        r02.reuploadingCdn = true;
        TLRPC.TL_upload_reuploadCdnFile r1 = new TLRPC.TL_upload_reuploadCdnFile();
        r1.file_token = r02.cdnToken;
        r1.request_token = ((TLRPC.TL_upload_cdnFileReuploadNeeded) r14).request_token;
        ConnectionsManager.getInstance(r02.currentAccount).sendRequest(r1, new FileLoadOperation$$ExternalSyntheticLambda5(r02, r12, r10), null, null, 0, r02.datacenterId, 1, true);
        return;
    L154:
        return;
    L106:
        if ((r14 instanceof TLRPC.TL_upload_file) == false) goto L109;
        RequestInfo.m4029$$Nest$fputresponse(r10, (TLRPC.TL_upload_file) r14);
    L116:
        if (r14 == null) goto L136;
        int r96 = r02.currentType;
        if (r96 != 50331648) goto L121;
        StatsController.getInstance(r02.currentAccount).incrementReceivedBytesCount(r14.networkType, 3, r14.getObjectSize() + 4);
        goto L136
    L121:
        if (r96 != 33554432) goto L124;
        StatsController.getInstance(r02.currentAccount).incrementReceivedBytesCount(r14.networkType, 2, r14.getObjectSize() + 4);
        goto L136
    L124:
        if (r96 != 16777216) goto L127;
        StatsController.getInstance(r02.currentAccount).incrementReceivedBytesCount(r14.networkType, 4, r14.getObjectSize() + 4);
        goto L136
    L127:
        if (r96 != 67108864) goto L136;
        String r97 = r02.ext;
        if (r97 != null) goto L131;
    L135:
        StatsController.getInstance(r02.currentAccount).incrementReceivedBytesCount(r14.networkType, 5, r14.getObjectSize() + 4);
        goto L136
    L131:
        if (r97.toLowerCase().endsWith(HlsSegmentFormat.MP3) == false) goto L133;
    L134:
        StatsController.getInstance(r02.currentAccount).incrementReceivedBytesCount(r14.networkType, 7, r14.getObjectSize() + 4);
        goto L136
    L133:
        if (r02.ext.toLowerCase().endsWith("m4a") == false) goto L135;
    L136:
        r02.processRequestResult(r10, r15);
        Runnable r98 = r10.whenCancelled;
        if (r98 == null) goto L155;
        r98.run();
        return;
    L155:
        return;
    L109:
        if ((r14 instanceof TLRPC.TL_upload_webFile) == false) goto L115;
        RequestInfo.m4031$$Nest$fputresponseWeb(r10, (TLRPC.TL_upload_webFile) r14);
        if (r02.totalBytesCount != 0) goto L116;
        if (RequestInfo.m4026$$Nest$fgetresponseWeb(r10).size == 0) goto L116;
        r02.totalBytesCount = RequestInfo.m4026$$Nest$fgetresponseWeb(r10).size;
        goto L116
    L115:
        RequestInfo.m4030$$Nest$fputresponseCdn(r10, (TLRPC.TL_upload_cdnFile) r14);
        goto L116
    L68:
        r02 = this;
        goto L70
    L47:
        if (BuildVars.DEBUG_VERSION == false) goto L49;
        FileLog.d("frame get request completed " + RequestInfo.m4023$$Nest$fgetoffset(this.priorityRequestInfo));
    L49:
        this.priorityRequestInfo = null;
        goto L50
    L12:
        if (this.cancelledRequestInfos.contains(r10) == false) goto L156;
        int r03 = 0;
        boolean r32 = false;
    L16:
        if (r03 >= this.requestInfos.size()) goto L29;
        RequestInfo r4 = this.requestInfos.get(r03);
        if (r4 == null) goto L28;
        if (r4 == r10) goto L28;
        if (RequestInfo.m4023$$Nest$fgetoffset(r4) != RequestInfo.m4023$$Nest$fgetoffset(r10)) goto L28;
        if (r4.chunkSize != r10.chunkSize) goto L28;
        FileLog.e("received cancelled chunk faster than new one! received=" + r10.requestToken + " new=" + r4.requestToken);
        ArrayList<RequestInfo> r42 = this.requestInfos;
        if (r32 == true) goto L27;
        r42.set(r03, r10);
        r32 = true;
        goto L28
    L27:
        r42.remove(r03);
        r03 = r03 - 1;
    L28:
        r03 = r03 + 1;
        goto L16
    }

    private /* synthetic */ void lambda$startDownloadRequest$30(int r1) {
        this.uiRequestTokens.add(Integer.valueOf(r1));
    }

    private void notifyStreamListeners() {
        ArrayList<FileLoadOperationStream> r0 = this.streamListeners;
        if (r0 == null) goto L7;
        int r02 = r0.size();
        int r1 = 0;
    L5:
        if (r1 >= r02) goto L9;
        this.streamListeners.get(r1).newDataAvailable();
        r1 = r1 + 1;
        goto L5
    L9:
        return;
    }

    private void onFinishLoadingFile(boolean r10, int r11, boolean r12) {
        if (this.state != 1) goto L5;
    L7:
        this.state = 3;
        notifyStreamListeners();
        cleanup();
        if (this.isPreloadVideoOperation == true) goto L13;
        if (r12 == true) goto L13;
        filesQueue.postRunnable(new FileLoadOperation$$ExternalSyntheticLambda15(this, this.cacheIvTemp, this.cacheFileParts, this.cacheFilePreload, this.cacheFileTemp, r10));
        this.cacheIvTemp = null;
        this.cacheFileParts = null;
        this.cacheFilePreload = null;
        this.delegate.didPreFinishLoading(this, this.cacheFileFinal);
        return;
    L13:
        this.preloadFinished = true;
        if (BuildVars.DEBUG_VERSION == false) goto L20;
        File r9 = this.cacheFileTemp;
        if (r11 != 1) goto L18;
        FileLog.d("file already exist " + r9);
        goto L20
    L18:
        FileLog.d("finished preloading file to " + r9 + " loaded " + this.downloadedBytes + " of " + this.totalBytesCount + " prefSize=" + this.preloadPrefixSize);
    L20:
        if (this.fileMetadata != null) goto L22;
    L27:
        this.delegate.didPreFinishLoading(this, this.cacheFileFinal);
        this.delegate.didFinishLoadingFile(this, this.cacheFileFinal);
        return;
    L22:
        if (this.cacheFileTemp == null) goto L25;
        FileLoader.getInstance(this.currentAccount).getFileDatabase().removeFiles(Collections.singletonList(new CacheModel.FileInfo(this.cacheFileTemp)));
    L25:
        if (this.cacheFileParts == null) goto L27;
        FileLoader.getInstance(this.currentAccount).getFileDatabase().removeFiles(Collections.singletonList(new CacheModel.FileInfo(this.cacheFileParts)));
        goto L27
    L5:
        if (this.state == 5) goto L7;
    }

    private void removePart(ArrayList<Range> r10, long r11, long r13) {
        if (r10 != null) goto L4;
        return;
    L4:
        if (r13 < r11) goto L36;
        int r9 = r10.size();
        int r0 = 0;
        int r1 = 0;
    L8:
        if (r1 >= r9) goto L17;
        Range r3 = r10.get(r1);
        if (r11 == Range.m4018$$Nest$fgetend(r3)) goto L11;
        if (r13 == Range.m4019$$Nest$fgetstart(r3)) goto L15;
        r1 = r1 + 1;
        goto L8
    L15:
        Range.m4021$$Nest$fputstart(r3, r11);
    L12:
        boolean r92 = true;
    L18:
        Collections.sort(r10, new FileLoadOperation$$ExternalSyntheticLambda21());
    L20:
        if (r0 >= (r10.size() - 1)) goto L25;
        Range r12 = r10.get(r0);
        int r32 = r0 + 1;
        Range r4 = r10.get(r32);
        if (Range.m4018$$Nest$fgetend(r12) != Range.m4019$$Nest$fgetstart(r4)) goto L24;
        Range.m4020$$Nest$fputend(r12, Range.m4018$$Nest$fgetend(r4));
        r10.remove(r32);
        r0 = r0 - 1;
    L24:
        r0 = r0 + 1;
        goto L20
    L25:
        if (r92 == true) goto L35;
        r10.add(new Range(r11, r13, null));
        return;
    L35:
        return;
    L11:
        Range.m4020$$Nest$fputend(r3, r13);
        goto L12
    L17:
        r92 = false;
        goto L18
    }

    private void requestFileOffsets(long r11) {
        if (this.requestingCdnOffsets == false) goto L5;
        return;
    L5:
        this.requestingCdnOffsets = true;
        TLRPC.TL_upload_getCdnFileHashes r2 = new TLRPC.TL_upload_getCdnFileHashes();
        r2.file_token = this.cdnToken;
        r2.offset = r11;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r2, new FileLoadOperation$$ExternalSyntheticLambda22(this), null, null, 0, this.datacenterId, 1, true);
    }

    private void requestReference(RequestInfo r4) {
        if (this.requestingReference == false) goto L5;
        return;
    L5:
        clearOperation(null, false, false);
        this.requestingReference = true;
        this.requestedReference = true;
        Object r0 = this.parentObject;
        if ((r0 instanceof MessageObject) == false) goto L17;
        MessageObject r02 = (MessageObject) r0;
        if (r02.getId() >= 0) goto L17;
        TLRPC.Message r03 = r02.messageOwner;
        if (r03 == null) goto L17;
        TLRPC.MessageMedia r04 = r03.media;
        if (r04 == null) goto L17;
        TLRPC.WebPage r05 = r04.webpage;
        if (r05 == null) goto L17;
        this.parentObject = r05;
        this.isStory = false;
    L17:
        if (BuildVars.LOGS_ENABLED == false) goto L19;
        FileLog.d("debug_loading: " + this.cacheFileFinal.getName() + " file reference expired ");
    L19:
        FileRefController.getInstance(this.currentAccount).requestReference(this.parentObject, new Object[]{this.location, this, r4});
    }

    private void updateParams() {
        if (ExteraConfig.getDownloadSpeedBoost() != 2) goto L6;
        this.downloadChunkSizeBig = 1048576;
        this.maxDownloadRequestsBig = 12;
        this.maxDownloadRequests = 12;
    L15:
        this.maxCdnParts = (int) (FileLoader.DEFAULT_MAX_FILE_SIZE / this.downloadChunkSizeBig);
        return;
    L6:
        if (this.preloadPrefixSize > 0) goto L12;
        if (ExteraConfig.getDownloadSpeedBoost() == 1) goto L12;
        if (MessagesController.getInstance(this.currentAccount).getfileExperimentalParams == true) goto L12;
    L14:
        this.downloadChunkSizeBig = 131072;
        this.maxDownloadRequestsBig = 4;
        this.maxDownloadRequests = 4;
    L12:
        if (this.forceSmallChunk == true) goto L14;
        this.downloadChunkSizeBig = 524288;
        this.maxDownloadRequestsBig = 8;
        this.maxDownloadRequests = 8;
        goto L15
    }

    public void cancel() {
        cancel(false);
    }

    public boolean checkPrefixPreloadFinished() {
        int r0 = this.preloadPrefixSize;
        if (r0 > 0) goto L5;
    L21:
        return false;
    L5:
        if (this.downloadedBytes <= r0) goto L21;
        ArrayList<Range> r02 = this.notLoadedBytesRanges;
        if (r02 != null) goto L9;
        return true;
    L9:
        long r3 = LongCompanionObject.MAX_VALUE;
        int r5 = 0;
    L22:
        if (r5 >= r02.size()) goto L17;
        r3 = Math.min(r3, Range.m4019$$Nest$fgetstart(r02.get(r5)));     // Catch: Throwable -> L14
        r5 = r5 + 1;
        goto L22
    L17:
        if (r3 <= this.preloadPrefixSize) goto L21;
        return true;
    L14:
        th = move-exception;
        FileLog.e(th);
        return true;
    }

    public File getCacheFileFinal() {
        return this.cacheFileFinal;
    }

    public File getCurrentFile() {
        CountDownLatch r0 = new CountDownLatch(1);
        File[] r1 = new File[1];
        Utilities.stageQueue.postRunnable(new FileLoadOperation$$ExternalSyntheticLambda16(this, r1, r0));
        r0.await();     // Catch: Exception -> L5
    L8:
        return r1[0];
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    public File getCurrentFileFast() {
        if (this.state != 3) goto L11;
        if (this.preloadFinished == true) goto L11;
        if (this.cacheFileFinalReady == false) goto L11;
        return this.cacheFileFinal;
    L11:
        return this.cacheFileTemp;
    }

    public int getCurrentType() {
        return this.currentType;
    }

    public int getDatacenterId() {
        return this.initialDatacenterId;
    }

    public long getDocumentId() {
        return this.documentId;
    }

    public float getDownloadedLengthFromOffset(float r7) {
        ArrayList<Range> r1 = this.notLoadedBytesRangesCopy;
        if (this.totalBytesCount == 0) goto L8;
        if (r1 != null) goto L7;
        return 0.0f;
    L7:
        return r7 + (getDownloadedLengthFromOffsetInternal(r1, (int) (r4 * r7), r4) / this.totalBytesCount);
    L8:
        return 0.0f;
    }

    public long[] getDownloadedLengthFromOffset(long r10, long r12) {
        CountDownLatch r7 = new CountDownLatch(1);
        long[] r2 = new long[2];
        Utilities.stageQueue.postRunnable(new FileLoadOperation$$ExternalSyntheticLambda23(this, r2, r10, r12, r7));
        r7.await();     // Catch: Exception -> L5
    L4:
        return r2;
    }

    public String getFileName() {
        return this.fileName;
    }

    public int getPositionInQueue() {
        return getQueue().getPosition(this);
    }

    public int getPriority() {
        return this.priority;
    }

    public FileLoaderPriorityQueue getQueue() {
        return this.priorityQueue;
    }

    public boolean isFinished() {
        if (this.state != 3) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isForceRequest() {
        return this.isForceRequest;
    }

    public boolean isPaused() {
        return this.paused;
    }

    public boolean isPreloadFinished() {
        return this.preloadFinished;
    }

    public boolean isPreloadVideoOperation() {
        return this.isPreloadVideoOperation;
    }

    public void onFail(boolean r9, int r10) {
        cleanup();
        if (r10 != 1) goto L5;
        int r1 = 4;
    L6:
        this.state = r1;
        if (this.delegate != null) goto L9;
    L18:
        if (r9 == false) goto L21;
        Utilities.stageQueue.postRunnable(new FileLoadOperation$$ExternalSyntheticLambda0(this, r10));
        return;
    L21:
        FileLoadOperationDelegate r92 = this.delegate;
        if (r92 == null) goto L24;
        r92.didFailedLoadingFile(this, r10);
    L24:
        notifyStreamListeners();
        return;
    L9:
        if (BuildVars.LOGS_ENABLED == false) goto L18;
        long r3 = 0;
        if (this.startTime == 0) goto L14;
        r3 = System.currentTimeMillis() - this.startTime;
    L14:
        File r12 = this.cacheFileFinal;
        if (r10 != 1) goto L17;
        FileLog.d("cancel downloading file to " + r12 + " time = " + r3 + " dc = " + this.datacenterId + " size = " + AndroidUtilities.formatFileSize(this.totalBytesCount));
        goto L18
    L17:
        FileLog.d("failed downloading file to " + r12 + " reason = " + r10 + " time = " + r3 + " dc = " + this.datacenterId + " size = " + AndroidUtilities.formatFileSize(this.totalBytesCount));
        goto L18
    L5:
        r1 = 2;
        goto L6
    }

    public void pause() {
        if (this.state == 1) goto L5;
        return;
    L5:
        this.paused = true;
        Utilities.stageQueue.postRunnable(new FileLoadOperation$$ExternalSyntheticLambda27(this));
    }

    public boolean processRequestResult(RequestInfo r46, TLRPC.TL_error r47) {
        if (this.state != 1) goto L5;
    L12:
        int r5 = r46.requestToken;
        this.requestInfos.remove(r46);
        AndroidUtilities.runOnUIThread(new FileLoadOperation$$ExternalSyntheticLambda12(this, r5));
        if (r47 != null) goto L251;
    L20:
        e = e;
    L243:
        FileLog.e(e);
        if (AndroidUtilities.isENOSPC(e) == false) goto L247;
        onFail(false, -1);
    L311:
        return false;
    L247:
        if (AndroidUtilities.isEROFS(e) == false) goto L249;
        SharedConfig.checkSdCard(this.cacheFileFinal);
        onFail(true, -1);
        goto L311
    L249:
        onFail(false, 0);
        goto L311
    L15:
        if (this.notLoadedBytesRanges != null) goto L22;
        long r17 = 0;
        if (this.downloadedBytes == RequestInfo.m4023$$Nest$fgetoffset(r46)) goto L24;
        delayRequestInfo(r46);     // Catch: Exception -> L20
        return false;
    L24:
        if (RequestInfo.m4024$$Nest$fgetresponse(r46) == null) goto L27;
        NativeByteBuffer r0 = RequestInfo.m4024$$Nest$fgetresponse(r46).bytes;     // Catch: Exception -> L20
    L33:
        if (r0 == null) goto L241;
        if (r0.limit() == 0) goto L241;
        int r7 = r0.limit();     // Catch: Exception -> L20
        if (this.isCdn == true) goto L300;
        int r21 = 1;
        String r22 = " id = ";
    L52:
        if (RequestInfo.m4025$$Nest$fgetresponseCdn(r46) == null) goto L54;
        long r29 = RequestInfo.m4023$$Nest$fgetoffset(r46) / 16;     // Catch: Exception -> L20
        byte[] r8 = this.cdnIv;     // Catch: Exception -> L20
        char r472 = '\r';
        char r31 = '\b';
        r8[15] = (byte) (r29 & 255);     // Catch: Exception -> L20
        r8[14] = (byte) ((r29 >> 8) & 255);     // Catch: Exception -> L20
        r8[13] = (byte) ((r29 >> 16) & 255);     // Catch: Exception -> L20
        r8[12] = (byte) ((r29 >> 24) & 255);     // Catch: Exception -> L20
        char r292 = '\f';
        Utilities.aesCtrDecryption(r0.buffer, this.cdnKey, r8, 0, r0.limit());     // Catch: Exception -> L20
    L56:
        if (this.isPreloadVideoOperation == false) goto L95;
        this.preloadStream.writeLong(RequestInfo.m4023$$Nest$fgetoffset(r46));     // Catch: Exception -> L20
        long r82 = r7;
        this.preloadStream.writeLong(r82);     // Catch: Exception -> L20
        this.preloadStreamFileOffset += 16;
        this.preloadStream.getChannel().write(r0.buffer);     // Catch: Exception -> L20
        if (BuildVars.DEBUG_VERSION == false) goto L61;
        FileLog.d("save preload file part " + this.cacheFilePreload + " offset " + RequestInfo.m4023$$Nest$fgetoffset(r46) + " size " + r7);     // Catch: Exception -> L20
    L61:
        if (this.preloadedBytesRanges != null) goto L63;
        this.preloadedBytesRanges = new HashMap();     // Catch: Exception -> L20
    L63:
        this.preloadedBytesRanges.put(Long.valueOf(RequestInfo.m4023$$Nest$fgetoffset(r46)), new PreloadRange(this.preloadStreamFileOffset, r82, null));     // Catch: Exception -> L20
        this.totalPreloadedBytes += r7;
        this.preloadStreamFileOffset += r7;
        if (this.moovFound != 0) goto L76;
        long r2 = findNextPreloadDownloadOffset(this.nextAtomOffset, RequestInfo.m4023$$Nest$fgetoffset(r46), r0);     // Catch: Exception -> L20
        long r4 = this.nextPreloadDownloadOffset;
        if (r2 >= r17) goto L74;
        r2 = r2 * (-1);
        long r42 = r4 + this.currentDownloadChunkSize;     // Catch: Exception -> L20
        this.nextPreloadDownloadOffset = r42;     // Catch: Exception -> L20
        if (r42 >= (this.totalBytesCount / 2)) goto L72;
        long r43 = 1048576 + r2;     // Catch: Exception -> L20
        this.foundMoovSize = r43;     // Catch: Exception -> L20
        this.preloadNotRequestedBytesCount = r43;     // Catch: Exception -> L20
        this.moovFound = r21;     // Catch: Exception -> L20
    L73:
        this.nextPreloadDownloadOffset = -1;     // Catch: Exception -> L20
    L75:
        this.nextAtomOffset = r2;     // Catch: Exception -> L20
        goto L76
    L72:
        this.foundMoovSize = 2097152;     // Catch: Exception -> L20
        this.preloadNotRequestedBytesCount = 2097152;     // Catch: Exception -> L20
        this.moovFound = 2;     // Catch: Exception -> L20
        goto L73
    L74:
        this.nextPreloadDownloadOffset = r4 + this.currentDownloadChunkSize;     // Catch: Exception -> L20
    L76:
        this.preloadStream.writeLong(this.foundMoovSize);     // Catch: Exception -> L20
        this.preloadStream.writeLong(this.nextPreloadDownloadOffset);     // Catch: Exception -> L20
        this.preloadStream.writeLong(this.nextAtomOffset);     // Catch: Exception -> L20
        this.preloadStreamFileOffset += 24;
        long r23 = this.nextPreloadDownloadOffset;     // Catch: Exception -> L20
        if (r23 != r17) goto L79;
    L88:
        boolean r02 = true;
    L89:
        if (r02 == false) goto L92;
        this.preloadStream.seek(r17);     // Catch: Exception -> L20
        this.preloadStream.write(1);     // Catch: Exception -> L20
    L94:
        boolean r9 = false;
    L213:
        int r24 = 0;
    L215:
        if (r24 >= this.delayedRequestInfos.size()) goto L233;
        RequestInfo r3 = this.delayedRequestInfos.get(r24);     // Catch: Exception -> L20
        if (this.notLoadedBytesRanges != null) goto L222;
        if (this.downloadedBytes == RequestInfo.m4023$$Nest$fgetoffset(r3)) goto L222;
        r24 = r24 + 1;     // Catch: Exception -> L20
    L222:
        this.delayedRequestInfos.remove(r24);     // Catch: Exception -> L20
        if (processRequestResult(r3, null) == true) goto L233;
        if (RequestInfo.m4024$$Nest$fgetresponse(r3) == null) goto L228;
        RequestInfo.m4024$$Nest$fgetresponse(r3).disableFree = false;     // Catch: Exception -> L20
        RequestInfo.m4024$$Nest$fgetresponse(r3).freeResources();     // Catch: Exception -> L20
        goto L233
    L228:
        if (RequestInfo.m4026$$Nest$fgetresponseWeb(r3) == null) goto L231;
        RequestInfo.m4026$$Nest$fgetresponseWeb(r3).disableFree = false;     // Catch: Exception -> L20
        RequestInfo.m4026$$Nest$fgetresponseWeb(r3).freeResources();     // Catch: Exception -> L20
        goto L233
    L231:
        if (RequestInfo.m4025$$Nest$fgetresponseCdn(r3) == null) goto L233;
        RequestInfo.m4025$$Nest$fgetresponseCdn(r3).disableFree = false;     // Catch: Exception -> L20
        RequestInfo.m4025$$Nest$fgetresponseCdn(r3).freeResources();     // Catch: Exception -> L20
    L233:
        if (r02 == false) goto L236;
        onFinishLoadingFile(true, 0, r9);     // Catch: Exception -> L20
        goto L311
    L236:
        if (this.state == 4) goto L311;
        if (this.state == 5) goto L311;
        startDownloadRequest(r46.connectionType);     // Catch: Exception -> L20
        goto L311
    L92:
        if (this.moovFound == 0) goto L94;
        this.foundMoovSize -= this.currentDownloadChunkSize;
        goto L94
    L79:
        if (this.moovFound == 0) goto L83;
        if (this.foundMoovSize < r17) goto L88;
    L83:
        if (this.totalPreloadedBytes > 2097152) goto L88;
        if (r23 >= this.totalBytesCount) goto L88;
        r02 = false;
        goto L89
    L95:
        NativeByteBuffer r6 = r0;
        long r11 = r7;
        long r83 = this.downloadedBytes + r11;     // Catch: Exception -> L20
        this.downloadedBytes = r83;     // Catch: Exception -> L20
        long r10 = this.totalBytesCount;     // Catch: Exception -> L20
        if (r10 > 0) goto L98;
        int r03 = this.currentDownloadChunkSize;     // Catch: Exception -> L20
        if (r7 == r03) goto L117;
        String r72 = " local_id = ";
    L129:
        boolean r04 = true;
    L130:
        boolean r84 = r04;
    L113:
        r9 = false;
    L131:
        boolean r05 = BuildVars.DEBUG_VERSION;     // Catch: Exception -> L20
        byte[] r06 = this.key;     // Catch: Exception -> L20
        if (r06 == null) goto L146;
        Utilities.aesIgeEncryption(r6.buffer, r06, this.iv, false, true, 0, r6.limit());     // Catch: Exception -> L20
        if (r84 == false) goto L146;
        long r172 = 0;
        if (this.bytesCountPadding == 0) goto L145;
        long r34 = 2147483647L;
        long r44 = r6.limit() - this.bytesCountPadding;     // Catch: Exception -> L20
        if (BuildVars.DEBUG_VERSION == true) goto L140;
    L144:
        r6.limit((int) r44);     // Catch: Exception -> L20
    L148:
        if (this.encryptFile == false) goto L151;
        long r32 = RequestInfo.m4023$$Nest$fgetoffset(r46) / 16;     // Catch: Exception -> L20
        byte[] r07 = this.encryptIv;     // Catch: Exception -> L20
        r07[15] = (byte) (r32 & 255);     // Catch: Exception -> L20
        r07[14] = (byte) ((r32 >> r31) & 255);     // Catch: Exception -> L20
        r07[r472] = (byte) ((r32 >> 16) & 255);     // Catch: Exception -> L20
        r07[r292] = (byte) ((r32 >> 24) & 255);     // Catch: Exception -> L20
        Utilities.aesCtrDecryption(r6.buffer, this.encryptKey, r07, 0, r6.limit());     // Catch: Exception -> L20
    L151:
        if (this.notLoadedBytesRanges == null) goto L155;
        this.fileOutputStream.seek(RequestInfo.m4023$$Nest$fgetoffset(r46));     // Catch: Exception -> L20
        if (BuildVars.DEBUG_VERSION == false) goto L155;
        FileLog.d("save file part " + this.fileName + " offset=" + RequestInfo.m4023$$Nest$fgetoffset(r46) + " chunk_size=" + this.currentDownloadChunkSize + " isCdn=" + this.isCdn);     // Catch: Exception -> L20
    L155:
        this.fileOutputStream.getChannel().write(r6.buffer);     // Catch: Exception -> L20
        String r12 = r72;
        long r102 = r172;
        addPart(this.notLoadedBytesRanges, RequestInfo.m4023$$Nest$fgetoffset(r46), RequestInfo.m4023$$Nest$fgetoffset(r46) + r11, true);     // Catch: Exception -> L20
        if (this.isCdn == false) goto L202;
        long r173 = RequestInfo.m4023$$Nest$fgetoffset(r46) / this.cdnChunkCheckSize;     // Catch: Exception -> L20
        int r08 = this.notCheckedCdnRanges.size();     // Catch: Exception -> L20
        int r25 = 0;
    L158:
        if (r25 >= r08) goto L202;
        Range r33 = this.notCheckedCdnRanges.get(r25);     // Catch: Exception -> L20
        if (Range.m4019$$Nest$fgetstart(r33) > r173) goto L203;
        if (r173 > Range.m4018$$Nest$fgetend(r33)) goto L203;
        int r09 = this.cdnChunkCheckSize;     // Catch: Exception -> L20
        long r35 = r173 * r09;     // Catch: Exception -> L20
        long r52 = getDownloadedLengthFromOffsetInternal(this.notLoadedBytesRanges, r35, r09);     // Catch: Exception -> L20
        if (r52 == r102) goto L202;
        long r322 = r102;
        if (r52 == this.cdnChunkCheckSize) goto L174;
        long r103 = this.totalBytesCount;     // Catch: Exception -> L20
        if (r103 <= r322) goto L172;
        if (r52 == (r103 - r35)) goto L174;
    L172:
        if (r103 > r322) goto L204;
        if (r84 == true) goto L174;
    L204:
        RandomAccessFile r010 = this.fiv;     // Catch: Exception -> L20
        if (r010 == null) goto L208;
        r010.seek(r322);     // Catch: Exception -> L20
        this.fiv.write(this.iv);     // Catch: Exception -> L20
    L208:
        if (this.totalBytesCount > 0) goto L210;
    L212:
        r02 = r84;
        goto L213
    L210:
        if (this.state != 1) goto L212;
        copyNotLoadedRanges();     // Catch: Exception -> L20
        this.delegate.didChangedLoadProgress(this, this.downloadedBytes, this.totalBytesCount);     // Catch: Exception -> L20
    L174:
        TLRPC.TL_fileHash r011 = this.cdnHashes.get(Long.valueOf(r35));     // Catch: Exception -> L20
        if (this.fileReadStream != null) goto L177;
        this.cdnCheckBytes = new byte[this.cdnChunkCheckSize];     // Catch: Exception -> L20
        this.fileReadStream = new RandomAccessFile(this.cacheFileTemp, "r");     // Catch: Exception -> L20
    L177:
        this.fileReadStream.seek(r35);     // Catch: Exception -> L20
        if (BuildVars.DEBUG_VERSION == true) goto L180;
    L184:
        this.fileReadStream.readFully(this.cdnCheckBytes, 0, (int) r52);     // Catch: Exception -> L20
        if (this.encryptFile == false) goto L187;
        long r104 = r35 / 16;     // Catch: Exception -> L20
        byte[] r26 = this.encryptIv;     // Catch: Exception -> L20
        long r432 = r35;
        r26[15] = (byte) (r104 & 255);     // Catch: Exception -> L20
        r26[14] = (byte) ((r104 >> r31) & 255);     // Catch: Exception -> L20
        r26[r472] = (byte) ((r104 >> 16) & 255);     // Catch: Exception -> L20
        r26[r292] = (byte) ((r104 >> 24) & 255);     // Catch: Exception -> L20
        Utilities.aesCtrDecryptionByteArray(this.cdnCheckBytes, this.encryptKey, r26, 0, r52, 0);     // Catch: Exception -> L20
        long r27 = r52;
    L189:
        if (Arrays.equals(Utilities.computeSHA256(this.cdnCheckBytes, 0, r27), r011.hash) == false) goto L191;
        this.cdnHashes.remove(Long.valueOf(r432));     // Catch: Exception -> L20
        addPart(this.notCheckedCdnRanges, r173, r173 + 1, false);     // Catch: Exception -> L20
        goto L204
    L191:
        if (BuildVars.LOGS_ENABLED == false) goto L199;
        if (this.location == null) goto L196;
        FileLog.e("invalid cdn hash " + this.location + r22 + this.location.id + r12 + this.location.local_id + " access_hash = " + this.location.access_hash + " volume_id = " + this.location.volume_id + " secret = " + this.location.secret);     // Catch: Exception -> L20
        goto L199
    L196:
        String r36 = r22;
        if (this.webLocation == null) goto L199;
        FileLog.e("invalid cdn hash  " + this.webLocation + r36 + this.fileName);     // Catch: Exception -> L20
    L199:
        onFail(false, 0);     // Catch: Exception -> L20
        this.cacheFileTemp.delete();     // Catch: Exception -> L20
        return false;
    L187:
        r432 = r35;
        r27 = r52;
        goto L189
    L180:
        if (r52 <= r34) goto L184;
        throw new RuntimeException("!!!");     // Catch: Exception -> L20
    L203:
        r25 = r25 + 1;     // Catch: Exception -> L20
        r22 = r22;
        r173 = r173;
        r12 = r12;
        r102 = r102;
    L202:
        r322 = r102;
        goto L204
    L140:
        if (r44 <= 2147483647L) goto L144;
        throw new RuntimeException("Out of limit" + r44);     // Catch: Exception -> L20
    L145:
        r34 = 2147483647L;
    L146:
        r172 = 0;
        goto L145
    L117:
        if (r10 == r83) goto L121;
        r72 = " local_id = ";
        long r174 = 0;
        if ((r83 % r03) != 0) goto L123;
    L127:
        r04 = false;
    L123:
        if (r10 <= r174) goto L129;
        if (r10 > r83) goto L127;
    L121:
        r72 = " local_id = ";
        r174 = 0;
        goto L123
    L98:
        if (r83 >= r10) goto L109;
        int r012 = this.preloadPrefixSize;     // Catch: Exception -> L20
        if (r012 > 0) goto L102;
    L108:
        boolean r013 = false;
    L110:
        r84 = r013;
        r72 = " local_id = ";
        if (this.downloadedBytes >= this.totalBytesCount) goto L113;
        r9 = true;
        goto L131
    L102:
        if (r83 < r012) goto L108;
        if (canFinishPreload() == false) goto L108;
        if (this.requestInfos.isEmpty() == false) goto L108;
    L109:
        r013 = true;
        goto L110
    L54:
        r292 = '\f';
        r472 = '\r';
        r31 = '\b';
        goto L56
    L300:
        long r19 = RequestInfo.m4023$$Nest$fgetoffset(r46);     // Catch: Exception -> L48
        int r85 = this.cdnChunkCheckSize;     // Catch: Exception -> L48
        r21 = 1;
        r22 = " id = ";
        long r112 = r85 * (r19 / r85);     // Catch: Exception -> L20
        HashMap<Long, TLRPC.TL_fileHash> r86 = this.cdnHashes;     // Catch: Exception -> L20
        if (r86 == null) goto L44;
        TLRPC.TL_fileHash r87 = r86.get(Long.valueOf(r112));     // Catch: Exception -> L20
    L45:
        if (r87 != null) goto L52;
        delayRequestInfo(r46);     // Catch: Exception -> L20
        requestFileOffsets(r112);     // Catch: Exception -> L20
        return true;
    L44:
        r87 = null;
    L48:
        e = e;
    L241:
        onFinishLoadingFile(true, 0, false);     // Catch: Exception -> L20
        return false;
    L27:
        if (RequestInfo.m4026$$Nest$fgetresponseWeb(r46) == null) goto L30;
        r0 = RequestInfo.m4026$$Nest$fgetresponseWeb(r46).bytes;     // Catch: Exception -> L20
        goto L33
    L30:
        if (RequestInfo.m4025$$Nest$fgetresponseCdn(r46) == null) goto L32;
        r0 = RequestInfo.m4025$$Nest$fgetresponseCdn(r46).bytes;     // Catch: Exception -> L20
        goto L33
    L32:
        r0 = null;
        goto L33
    L22:
        r17 = 0;
        goto L24
    L251:
        if (r47.text.contains("LIMIT_INVALID") == true) goto L253;
    L261:
        boolean r45 = r47.text.contains("FILE_MIGRATE_");
        String r62 = r47.text;
        if (r45 == false) goto L271;
        Scanner r48 = new Scanner(r62.replace("FILE_MIGRATE_", _UrlKt.FRAGMENT_ENCODE_SET));
        r48.useDelimiter(_UrlKt.FRAGMENT_ENCODE_SET);
        Integer r73 = Integer.valueOf(r48.nextInt());     // Catch: Exception -> L266
    L267:
        if (r73 != null) goto L269;
        onFail(false, 0);
        goto L311
    L269:
        this.datacenterId = r73.intValue();
        this.downloadedBytes = 0;
        this.requestedBytesCount = 0;
        startDownloadRequest(r46.connectionType);
    L266:
        r73 = null;
        goto L267
    L271:
        if (r62.contains("OFFSET_INVALID") == false) goto L281;
        if ((this.downloadedBytes % this.currentDownloadChunkSize) == 0) goto L302;
        onFail(false, 0);
        return false;
    L302:
        onFinishLoadingFile(true, 0, false);     // Catch: Exception -> L277
        return false;
    L277:
        e = move-exception;
        FileLog.e(e);
        onFail(false, 0);
        return false;
    L281:
        if (r47.text.contains("RETRY_LIMIT") == false) goto L284;
        onFail(false, 2);
        return false;
    L284:
        if (BuildVars.LOGS_ENABLED == false) goto L295;
        TLRPC.InputFileLocation r28 = this.location;
        if (r28 == null) goto L293;
        boolean r210 = r28 instanceof TLRPC.TL_inputPeerPhotoFileLocation;
        String r014 = r47.text;
        if (r210 == false) goto L291;
        FileLog.e(r014 + " " + this.location + " peer_did = " + DialogObject.getPeerDialogId(((TLRPC.TL_inputPeerPhotoFileLocation) this.location).peer) + " peer_access_hash=" + ((TLRPC.TL_inputPeerPhotoFileLocation) this.location).peer.access_hash + " photo_id=" + ((TLRPC.TL_inputPeerPhotoFileLocation) this.location).photo_id + " big=" + ((TLRPC.TL_inputPeerPhotoFileLocation) this.location).big);
        goto L295
    L291:
        FileLog.e(r014 + " " + this.location + " id = " + this.location.id + " local_id = " + this.location.local_id + " access_hash = " + this.location.access_hash + " volume_id = " + this.location.volume_id + " secret = " + this.location.secret);
        goto L295
    L293:
        if (this.webLocation == null) goto L295;
        FileLog.e(r47.text + " " + this.webLocation + " id = " + this.fileName);
    L295:
        onFail(false, 0);
        return false;
    L253:
        if (RequestInfo.m4022$$Nest$fgetforceSmallChunk(r46) == true) goto L261;
        Runnable r015 = r46.whenCancelled;
        if (r015 == null) goto L257;
        r015.run();
    L257:
        removePart(this.notRequestedBytesRanges, RequestInfo.m4023$$Nest$fgetoffset(r46), RequestInfo.m4023$$Nest$fgetoffset(r46) + r46.chunkSize);
        if (this.forceSmallChunk == true) goto L260;
        this.forceSmallChunk = true;
        this.currentDownloadChunkSize = 32768;
        this.currentMaxDownloadRequests = 4;
    L260:
        startDownloadRequest(r46.connectionType);
        goto L311
    L5:
        if (this.state == 5) goto L12;
        if (BuildVars.DEBUG_VERSION == true) goto L9;
    L11:
        return false;
    L9:
        if (this.state != 3) goto L11;
        FileLog.e(new FileLog.IgnoreSentException("trying to write to finished file " + this.fileName + " offset " + RequestInfo.m4023$$Nest$fgetoffset(r46) + " " + this.totalBytesCount + " reqToken=" + r46.requestToken + " (state=" + this.state + ")"));
        goto L11
    }

    public void removeStreamListener(FileLoadOperationStream r3) {
        Utilities.stageQueue.postRunnable(new FileLoadOperation$$ExternalSyntheticLambda14(this, r3));
    }

    public void setDelegate(FileLoadOperationDelegate r1) {
        this.delegate = r1;
    }

    public void setEncryptFile(boolean r1) {
        this.encryptFile = r1;
        if (r1 == false) goto L6;
        this.allowDisordererFileSave = false;
        return;
    }

    public void setForceRequest(boolean r1) {
        this.isForceRequest = r1;
    }

    public void setIsPreloadVideoOperation(boolean r5) {
        if (this.isPreloadVideoOperation == r5) goto L24;
        if (r5 == true) goto L6;
    L8:
        FileLog.e("setIsPreloadVideoOperation " + r5 + " file=" + this.fileName);
        if (r5 == false) goto L11;
    L22:
        this.isPreloadVideoOperation = r5;
        return;
    L11:
        if (this.isPreloadVideoOperation == false) goto L22;
        if (this.state != 3) goto L17;
        this.isPreloadVideoOperation = r5;
        this.state = 0;
        this.preloadFinished = false;
        start();
        return;
    L17:
        if (this.state != 1) goto L20;
        Utilities.stageQueue.postRunnable(new FileLoadOperation$$ExternalSyntheticLambda13(this, r5));
        return;
    L20:
        this.isPreloadVideoOperation = r5;
        return;
    L6:
        if (this.totalBytesCount > 2097152) goto L8;
        return;
    }

    public void setPaths(int r1, String r2, FileLoaderPriorityQueue r3, File r4, File r5, String r6) {
        this.storePath = r4;
        this.tempPath = r5;
        this.currentAccount = r1;
        this.fileName = r2;
        this.storeFileName = r6;
        this.priorityQueue = r3;
    }

    public void setPriority(int r1) {
        this.priority = r1;
    }

    public void setStream(FileLoadOperationStream r1, boolean r2, long r3) {
        this.stream = r1;
        this.streamOffset = r3;
        this.streamPriority = r2;
        Utilities.stageQueue.postRunnable(new FileLoadOperation$$ExternalSyntheticLambda26(this, r1));
    }

    public boolean start() {
        return start(this.stream, this.streamOffset, this.streamPriority);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r30v0, types: [org.telegram.messenger.FileLoadOperation] */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v29, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v44 */
    /* JADX WARN: Type inference failed for: r3v45, types: [int] */
    /* JADX WARN: Type inference failed for: r3v49 */
    /* JADX WARN: Type inference failed for: r3v53 */
    /* JADX WARN: Type inference failed for: r3v54 */
    /* JADX WARN: Type inference failed for: r3v55 */
    /* JADX WARN: Type inference failed for: r3v56 */
    /* JADX WARN: Type inference failed for: r3v57 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v5, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r7v6 */
    public boolean start(FileLoadOperationStream r31, long r32, boolean r34) {
        this.startTime = System.currentTimeMillis();
        updateParams();
        boolean r8 = true;
        if (this.currentDownloadChunkSize != 0) goto L29;
        if (this.forceSmallChunk == false) goto L11;
        if (BuildVars.LOGS_ENABLED == false) goto L9;
        FileLog.d("debug_loading: restart with small chunk");
    L9:
        this.currentDownloadChunkSize = 32768;
        this.currentMaxDownloadRequests = 4;
        goto L29
    L11:
        if (this.isStory == false) goto L14;
        this.currentDownloadChunkSize = this.downloadChunkSizeBig;
        this.currentMaxDownloadRequests = this.maxDownloadRequestsBig;
        goto L29
    L14:
        if (this.isStream == false) goto L17;
        this.currentDownloadChunkSize = this.downloadChunkSizeAnimation;
        this.currentMaxDownloadRequests = this.maxDownloadRequestsAnimation;
        goto L29
    L17:
        if (this.totalBytesCount < this.bigFileSizeFrom) goto L19;
        boolean r0 = true;
    L20:
        if (r0 == false) goto L22;
        int r2 = this.downloadChunkSizeBig;
    L23:
        this.currentDownloadChunkSize = r2;
        if (r0 == false) goto L26;
        int r02 = this.maxDownloadRequestsBig;
    L27:
        this.currentMaxDownloadRequests = r02;
        goto L29
    L26:
        r02 = this.maxDownloadRequests;
        goto L27
    L22:
        r2 = this.downloadChunkSize;
        goto L23
    L19:
        r0 = false;
    L29:
        if (this.state == 0) goto L31;
        boolean r6 = true;
    L32:
        boolean r9 = this.paused;
        this.paused = false;
        if (r31 == null) goto L35;
        Utilities.stageQueue.postRunnable(new FileLoadOperation$$ExternalSyntheticLambda17(this, r34, r32, r31, r6));
    L37:
        if (r6 == false) goto L40;
        return r9;
    L40:
        if (this.location == null) goto L42;
    L48:
        int r03 = this.currentDownloadChunkSize;
        this.streamStartOffset = (r32 / r03) * r03;
        if (this.allowDisordererFileSave == false) goto L56;
        long r5 = this.totalBytesCount;
        if (r5 <= 0) goto L56;
        if (r5 <= r03) goto L56;
        this.notLoadedBytesRanges = new ArrayList();
        this.notRequestedBytesRanges = new ArrayList();
    L56:
        if (this.webLocation == null) goto L69;
        String r04 = Utilities.MD5(this.webFile.url);
        if (this.encryptFile == false) goto L63;
        String r22 = r04 + ".temp.enc";
        String r52 = r04 + "." + this.ext + ".enc";
        if (this.key == null) goto L62;
        String r05 = r04 + "_64.iv.enc";
    L67:
        String r312 = r22;
        String r23 = r05;
        String r06 = r312;
        long r313 = 0;
    L68:
        String r3 = null;
        String r4 = null;
    L116:
        this.requestInfos = new ArrayList(this.currentMaxDownloadRequests);
        this.cancelledRequestInfos = new ArrayList();
        this.delayedRequestInfos = new ArrayList(this.currentMaxDownloadRequests - 1);
        this.state = 1;
        Object r62 = this.parentObject;
        if ((r62 instanceof TLRPC.TL_theme) == false) goto L120;
        this.cacheFileFinal = new File(ApplicationLoader.getFilesDirFixed(), "remote" + ((TLRPC.TL_theme) r62).id + ".attheme");
    L123:
        boolean r63 = this.cacheFileFinal.exists();
        this.cacheFileFinalReady = r63;
        if (r63 == true) goto L126;
    L142:
        if (r63 == true) goto L352;
        this.cacheFileTemp = new File(this.tempPath, r06);
        if (this.ungzip == false) goto L147;
        this.cacheFileGzipTemp = new File(this.tempPath, r06.concat(".gz"));
    L147:
        if (this.encryptFile == false) goto L172;
        File r07 = new File(FileLoader.getInternalCacheDir(), r52.concat(".key"));
        RandomAccessFile r53 = new RandomAccessFile(r07, "rws");     // Catch: Exception -> L154
        long r10 = r07.length();     // Catch: Exception -> L154
        byte[] r12 = new byte[32];     // Catch: Exception -> L154
        this.encryptKey = r12;     // Catch: Exception -> L154
        this.encryptIv = new byte[16];     // Catch: Exception -> L154
        if (r10 > r313) goto L152;
    L156:
        Utilities.random.nextBytes(r12);     // Catch: Exception -> L154
        Utilities.random.nextBytes(this.encryptIv);     // Catch: Exception -> L154
        r53.write(this.encryptKey);     // Catch: Exception -> L154
        r53.write(this.encryptIv);     // Catch: Exception -> L154
        boolean r102 = true;
    L382:
        r53.getChannel().close();     // Catch: Exception -> L160
    L162:
        r53.close();     // Catch: Exception -> L164
        boolean r103 = r102;
    L173:
        boolean[] r54 = {false};
        long r13 = 8;
        if (this.supportsPreloading == false) goto L251;
        if (r3 == null) goto L251;
        this.cacheFilePreload = new File(this.tempPath, r3);
        RandomAccessFile r08 = new RandomAccessFile(this.cacheFilePreload, "rws");     // Catch: Exception -> L231
        this.preloadStream = r08;     // Catch: Exception -> L231
        long r15 = r08.length();     // Catch: Exception -> L231
        this.preloadStreamFileOffset = 1;     // Catch: Exception -> L231
        long r17 = 1;
        if (r15 > 1) goto L180;
    L188:
        boolean r33 = r8;
        long r172 = r13;
        long r21 = 2;
    L233:
        this.preloadStream.seek(this.preloadStreamFileOffset);     // Catch: Exception -> L217
        boolean r35 = r33;
    L236:
        ?? r36 = r35;
        if (this.isPreloadVideoOperation == true) goto L252;
        r36 = r35;
        if (this.preloadedBytesRanges != null) goto L252;
        this.cacheFilePreload = null;
        RandomAccessFile r09 = this.preloadStream;     // Catch: Exception -> L249
        r36 = r35;
        if (r09 == null) goto L252;
        r09.getChannel().close();     // Catch: Exception -> L245
    L247:
        this.preloadStream.close();     // Catch: Exception -> L249
        this.preloadStream = null;     // Catch: Exception -> L249
        r36 = r35;
        goto L252
    L245:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L249
    L249:
        e = move-exception;
        FileLog.e(e);
        r36 = r35;
    L252:
        if (r4 == null) goto L267;
        this.cacheFileParts = new File(this.tempPath, r4);
        if (this.cacheFileTemp.exists() == true) goto L372;
        this.cacheFileParts.delete();
    L372:
        RandomAccessFile r010 = new RandomAccessFile(this.cacheFileParts, "rws");     // Catch: Exception -> L264
        this.filePartsStream = r010;     // Catch: Exception -> L264
        long r7 = r010.length();     // Catch: Exception -> L264
        if ((r7 % r172) != 4) goto L267;
        int r011 = this.filePartsStream.readInt();     // Catch: Exception -> L264
        if (r011 > ((r7 - 4) / r21)) goto L267;
        int r42 = 0;
    L261:
        if (r42 >= r011) goto L267;
        long r122 = this.filePartsStream.readLong();     // Catch: Exception -> L264
        long r14 = this.filePartsStream.readLong();     // Catch: Exception -> L264
        this.notLoadedBytesRanges.add(new Range(r122, r14, null));     // Catch: Exception -> L264
        this.notRequestedBytesRanges.add(new Range(r122, r14, null));     // Catch: Exception -> L264
        r42 = r42 + 1;
    L264:
        e = move-exception;
        FileLog.e(e);
    L267:
        if (this.fileMetadata == null) goto L270;
        FileLoader.getInstance(this.currentAccount).getFileDatabase().saveFileDialogId(this.cacheFileParts, this.fileMetadata);
        FileLoader.getInstance(this.currentAccount).getFileDatabase().saveFileDialogId(this.cacheFileTemp, this.fileMetadata);
    L270:
        if (this.cacheFileTemp.exists() == false) goto L285;
        File r012 = this.cacheFileTemp;
        if (r103 == false) goto L274;
        r012.delete();
    L290:
        ArrayList<Range> r013 = this.notLoadedBytesRanges;
        if (r013 == null) goto L297;
        this.downloadedBytes = this.totalBytesCount;
        int r014 = r013.size();
        int r43 = 0;
    L293:
        if (r43 >= r014) goto L295;
        Range r72 = this.notLoadedBytesRanges.get(r43);
        this.downloadedBytes -= Range.m4018$$Nest$fgetend(r72) - Range.m4019$$Nest$fgetstart(r72);
        r43 = r43 + 1;
        goto L293
    L295:
        this.requestedBytesCount = this.downloadedBytes;
    L297:
        if (BuildVars.LOGS_ENABLED == false) goto L302;
        boolean r015 = this.isPreloadVideoOperation;
        File r44 = this.cacheFileTemp;
        if (r015 == false) goto L301;
        FileLog.d("start preloading file to temp = " + r44);
        goto L302
    L301:
        FileLog.d("start loading file to temp = " + r44 + " final = " + this.cacheFileFinal + " priority" + this.priority);
    L302:
        if (r23 == null) goto L325;
        this.cacheIvTemp = new File(this.tempPath, r23);
        this.fiv = new RandomAccessFile(this.cacheIvTemp, "rws");     // Catch: Exception -> L312
        long r11 = 0;
        if (this.downloadedBytes == 0) goto L385;
        if (r103 == true) goto L385;
        long r73 = this.cacheIvTemp.length();     // Catch: Exception -> L312
        if (r73 > 0) goto L310;
    L315:
        this.downloadedBytes = r11;     // Catch: Exception -> L312
        this.requestedBytesCount = r11;     // Catch: Exception -> L312
        goto L385
    L310:
        if ((r73 % 64) != 0) goto L314;
        this.fiv.read(this.iv, 0, 64);     // Catch: Exception -> L312
        goto L385
    L314:
        r11 = 0;
    L385:
    L312:
        e = move-exception;
        this.downloadedBytes = 0;
        this.requestedBytesCount = 0;
        if (AndroidUtilities.isENOSPC(e) == false) goto L321;
        LaunchActivity.checkFreeDiscSpaceStatic(r36);
        FileLog.e(e);
        goto L325
    L321:
        if (AndroidUtilities.isEROFS(e) == false) goto L323;
        SharedConfig.checkSdCard(this.cacheFileFinal);
        FileLog.e(e);
        goto L325
    L323:
        FileLog.e(e);
    L325:
        if (this.isPreloadVideoOperation == false) goto L327;
    L331:
        updateProgress();
        RandomAccessFile r016 = new RandomAccessFile(this.cacheFileTemp, "rws");     // Catch: Exception -> L336
        this.fileOutputStream = r016;     // Catch: Exception -> L336
        long r64 = this.downloadedBytes;     // Catch: Exception -> L336
        if (r64 == 0) goto L338;
        r016.seek(r64);     // Catch: Exception -> L336
    L338:
        ?? r74 = 0;
    L348:
        if (this.fileOutputStream != null) goto L351;
        onFail(r36, r74);
        return r74;
    L351:
        this.started = r36;
        Utilities.stageQueue.postRunnable(new FileLoadOperation$$ExternalSyntheticLambda19(this, r54));
        return r36;
    L336:
        e = move-exception;
        FileLog.e(e);
        if (AndroidUtilities.isENOSPC(e) == false) goto L343;
        LaunchActivity.checkFreeDiscSpaceStatic(r36);
        onFail(r36, -1);
        return false;
    L343:
        r74 = 0;
        if (AndroidUtilities.isEROFS(e) == false) goto L348;
        SharedConfig.checkSdCard(this.cacheFileFinal);
        FileLog.e(e);
        onFail(r36, -1);
        return false;
    L327:
        if (this.downloadedBytes == 0) goto L331;
        if (this.totalBytesCount <= 0) goto L331;
        copyNotLoadedRanges();
        goto L331
    L274:
        long r75 = r012.length();
        if (r23 != null) goto L277;
    L279:
        long r76 = floorDiv(this.cacheFileTemp.length(), this.currentDownloadChunkSize) * this.currentDownloadChunkSize;
        this.downloadedBytes = r76;
        this.requestedBytesCount = r76;
    L280:
        ArrayList<Range> r017 = this.notLoadedBytesRanges;
        if (r017 == null) goto L290;
        if (r017.isEmpty() == false) goto L290;
        this.notLoadedBytesRanges.add(new Range(this.downloadedBytes, this.totalBytesCount, null));
        this.notRequestedBytesRanges.add(new Range(this.downloadedBytes, this.totalBytesCount, null));
        goto L290
    L277:
        if ((r75 % this.currentDownloadChunkSize) == r313) goto L279;
        this.requestedBytesCount = r313;
        goto L280
    L285:
        ArrayList<Range> r018 = this.notLoadedBytesRanges;
        if (r018 == null) goto L290;
        if (r018.isEmpty() == false) goto L290;
        this.notLoadedBytesRanges.add(new Range(0, this.totalBytesCount, null));
        this.notRequestedBytesRanges.add(new Range(0, this.totalBytesCount, null));
    L217:
        e = e;
        boolean r37 = r33;
    L235:
        FileLog.e(e);
        r35 = r37;
        goto L236
    L180:
        if (this.preloadStream.readByte() == 0) goto L182;
        boolean r019 = true;
    L183:
        r54[0] = r019;     // Catch: Exception -> L231
    L185:
        if (r17 >= r15) goto L188;
        if ((r15 - r17) < r13) goto L188;
        long r19 = this.preloadStream.readLong();     // Catch: Exception -> L231
        if ((r15 - (r17 + r13)) < r13) goto L188;
        if (r19 < r313) goto L188;
        r21 = 2;
        if (r19 > this.totalBytesCount) goto L197;
        long r26 = this.preloadStream.readLong();     // Catch: Exception -> L229
        long r24 = r17 + 16;     // Catch: Exception -> L229
        if ((r15 - r24) < r26) goto L197;
        if (r26 > this.currentDownloadChunkSize) goto L197;
        PreloadRange r232 = new PreloadRange(r24, r26, null);     // Catch: Exception -> L229
        long r112 = r24 + r26;     // Catch: Exception -> L229
        this.preloadStream.seek(r112);     // Catch: Exception -> L229
        if ((r15 - r112) < 24) goto L197;
        r172 = r13;
        long r132 = this.preloadStream.readLong();     // Catch: Exception -> L227
        this.foundMoovSize = r132;     // Catch: Exception -> L227
        if (r132 == r313) goto L219;
        r33 = r8;
        if (this.nextPreloadDownloadOffset <= (this.totalBytesCount / 2)) goto L215;
        int r77 = 2;
    L216:
        this.moovFound = r77;     // Catch: Exception -> L217
        this.preloadNotRequestedBytesCount = r132;     // Catch: Exception -> L217
        ?? r38 = r33;
    L220:
        this.nextPreloadDownloadOffset = this.preloadStream.readLong();     // Catch: Exception -> L217
        this.nextAtomOffset = this.preloadStream.readLong();     // Catch: Exception -> L217
        long r78 = r112 + 24;     // Catch: Exception -> L217
        if (this.preloadedBytesRanges != null) goto L224;
        this.preloadedBytesRanges = new HashMap();     // Catch: Exception -> L217
    L224:
        if (this.requestedPreloadedBytesRanges != null) goto L226;
        this.requestedPreloadedBytesRanges = new HashMap();     // Catch: Exception -> L217
    L226:
        this.preloadedBytesRanges.put(Long.valueOf(r19), r232);     // Catch: Exception -> L217
        this.requestedPreloadedBytesRanges.put(Long.valueOf(r19), Integer.valueOf(r38));     // Catch: Exception -> L217
        this.totalPreloadedBytes = (int) (this.totalPreloadedBytes + r26);     // Catch: Exception -> L217
        this.preloadStreamFileOffset = (int) (this.preloadStreamFileOffset + (r26 + 36));     // Catch: Exception -> L217
        r13 = r172;
        r17 = r78;
        r8 = r38;
        goto L185
    L215:
        r77 = r33 ? 1 : 0;
        goto L216
    L219:
        r38 = r8;
    L227:
        e = e;
        r37 = r8;
    L197:
        r33 = r8;
        r172 = r13;
    L229:
        e = e;
        r37 = r8;
        r172 = r13;
        goto L235
    L182:
        r019 = false;
    L231:
        e = e;
        r37 = r8;
        r172 = r13;
        r21 = 2;
    L251:
        r36 = 1;
        r172 = 8;
        r21 = 2;
        goto L252
    L160:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L164
    L164:
        e = e;
    L166:
        if (AndroidUtilities.isENOSPC(e) == false) goto L169;
        LaunchActivity.checkFreeDiscSpaceStatic(1);
        FileLog.e(e);
        r103 = r102;
        goto L173
    L169:
        if (AndroidUtilities.isEROFS(e) == false) goto L171;
        SharedConfig.checkSdCard(this.cacheFileFinal);
        FileLog.e(e);
        r103 = r102;
        goto L173
    L171:
        FileLog.e(e);
        r103 = r102;
        goto L173
    L152:
        if ((r10 % 48) != r313) goto L156;
        r53.read(r12, 0, 32);     // Catch: Exception -> L154
        r53.read(this.encryptIv, 0, 16);     // Catch: Exception -> L154
        r102 = false;
    L154:
        e = e;
        r102 = false;
        goto L166
    L172:
        r103 = false;
        goto L173
    L352:
        this.started = true;
        onFinishLoadingFile(false, 1, false);     // Catch: Exception -> L357
        FilePathDatabase.PathData r020 = this.pathSaveData;     // Catch: Exception -> L357
        if (r020 == null) goto L359;
        this.delegate.saveFilePath(r020, this.cacheFileFinal);     // Catch: Exception -> L357
    L359:
        return true;
    L357:
        e = move-exception;
        FileLog.e(e);
        if (AndroidUtilities.isENOSPC(e) == false) goto L363;
        boolean r39 = true;
        LaunchActivity.checkFreeDiscSpaceStatic(1);
        int r25 = -1;
        onFail(true, -1);
    L365:
        if (AndroidUtilities.isEROFS(e) == false) goto L368;
        SharedConfig.checkSdCard(this.cacheFileFinal);
        onFail(r39, r25);
        return false;
    L368:
        onFail(r39, 0);
        return r39;
    L363:
        r25 = -1;
        r39 = true;
        goto L365
    L126:
        if ((this.parentObject instanceof TLRPC.TL_theme) == true) goto L134;
        long r92 = this.totalBytesCount;
        if (r92 == r313) goto L142;
        if (this.ungzip == true) goto L142;
        if (r92 == this.cacheFileFinal.length()) goto L142;
    L134:
        if (this.delegate.isLocallyCreatedFile(this.cacheFileFinal.toString()) == true) goto L142;
        if (BuildVars.LOGS_ENABLED == false) goto L139;
        FileLog.d("debug_loading: delete existing file cause file size mismatch " + this.cacheFileFinal.getName() + " totalSize=" + this.totalBytesCount + " existingFileSize=" + this.cacheFileFinal.length());
    L139:
        if (this.delegate.hasAnotherRefOnFile(this.cacheFileFinal.toString()) == true) goto L141;
        this.cacheFileFinal.delete();
    L141:
        r63 = false;
        goto L142
    L120:
        if (this.encryptFile == true) goto L122;
        this.cacheFileFinal = new File(this.storePath, this.storeFileName);
        goto L123
    L122:
        this.cacheFileFinal = new File(this.storePath, r52);
    L62:
        r05 = null;
        goto L67
    L63:
        String r55 = r04 + ".temp";
        String r65 = r04 + "." + this.ext;
        if (this.key == null) goto L66;
        r05 = r04 + "_64.iv";
        r22 = r55;
        r52 = r65;
        goto L67
    L66:
        r22 = r55;
        r52 = r65;
        goto L62
    L69:
        TLRPC.InputFileLocation r021 = this.location;
        long r133 = r021.volume_id;
        r313 = 0;
        if (r133 == 0) goto L98;
        if (r021.local_id == 0) goto L98;
        int r022 = this.datacenterId;
        if (r022 != Integer.MIN_VALUE) goto L76;
    L95:
        onFail(true, 0);
        return false;
    L76:
        if (r133 == (-2147483648L)) goto L95;
        if (r022 == 0) goto L95;
        boolean r023 = this.encryptFile;
        TLRPC.InputFileLocation r134 = this.location;
        if (r023 == false) goto L86;
        String r27 = r134.volume_id + "_" + this.location.local_id + ".temp.enc";
        r52 = this.location.volume_id + "_" + this.location.local_id + "." + this.ext + ".enc";
        if (this.key == null) goto L85;
        String r024 = this.location.volume_id + "_" + this.location.local_id + "_64.iv.enc";
    L84:
        String r310 = r27;
        r23 = r024;
        r06 = r310;
    L85:
        r06 = r27;
        r23 = null;
        goto L68
    L86:
        r06 = r134.volume_id + "_" + this.location.local_id + ".temp";
        r52 = this.location.volume_id + "_" + this.location.local_id + "." + this.ext;
        if (this.key == null) goto L89;
        r23 = this.location.volume_id + "_" + this.location.local_id + "_64.iv";
    L91:
        if (this.notLoadedBytesRanges == null) goto L93;
        r4 = this.location.volume_id + "_" + this.location.local_id + "_64.pt";
    L94:
        r3 = this.location.volume_id + "_" + this.location.local_id + "_64.preload";
        goto L116
    L93:
        r4 = null;
        goto L94
    L89:
        r23 = null;
    L98:
        if (this.datacenterId == 0) goto L370;
        if (r021.id == 0) goto L370;
        boolean r025 = this.encryptFile;
        int r113 = this.datacenterId;
        if (r025 == false) goto L107;
        r27 = r113 + "_" + this.location.id + ".temp.enc";
        r52 = this.datacenterId + "_" + this.location.id + this.ext + ".enc";
        if (this.key == null) goto L85;
        r024 = this.datacenterId + "_" + this.location.id + "_64.iv.enc";
        goto L84
    L107:
        r06 = r113 + "_" + this.location.id + ".temp";
        r52 = this.datacenterId + "_" + this.location.id + this.ext;
        if (this.key == null) goto L110;
        r23 = this.datacenterId + "_" + this.location.id + "_64.iv";
    L112:
        if (this.notLoadedBytesRanges == null) goto L114;
        r4 = this.datacenterId + "_" + this.location.id + "_64.pt";
    L115:
        r3 = this.datacenterId + "_" + this.location.id + "_64.preload";
        goto L116
    L114:
        r4 = null;
        goto L115
    L110:
        r23 = null;
    L370:
        onFail(true, 0);
        return false;
    L42:
        if (this.webLocation != null) goto L48;
        if (BuildVars.DEBUG_VERSION == false) goto L46;
        FileLog.d("loadOperation: no location, failing");
    L46:
        onFail(true, 0);
        return false;
    L35:
        if (r6 == false) goto L37;
        Utilities.stageQueue.postRunnable(new FileLoadOperation$$ExternalSyntheticLambda18(this));
        goto L37
    L31:
        r6 = false;
        goto L32
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void startDownloadRequest(int r31) {
        FileLoadOperation r0 = this;
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L14;
        if (Utilities.stageQueue == null) goto L14;
        if (Utilities.stageQueue.getHandler() == null) goto L14;
        if (Thread.currentThread() == Utilities.stageQueue.getHandler().getLooper().getThread()) goto L14;
        GlShader$$ExternalSyntheticBUOutline1.m("Wrong thread!!!");
        return;
    L14:
        if (r0.state != 5) goto L17;
        r0.state = 1;
    L17:
        if (r0.paused == false) goto L19;
        return;
    L19:
        if (r0.reuploadingCdn == false) goto L21;
        return;
    L21:
        if (r0.state == 1) goto L23;
        return;
    L23:
        if (r0.requestingReference == true) goto L253;
        long r8 = 0;
        if (r0.isStory == true) goto L33;
        if (r0.streamPriorityStartOffset != 0) goto L33;
        if (r0.nextPartWasPreloaded == true) goto L33;
        if ((r0.requestInfos.size() + r0.delayedRequestInfos.size()) < r0.currentMaxDownloadRequests) goto L33;
        return;
    L33:
        if (r0.isPreloadVideoOperation == false) goto L42;
        if (r0.requestedBytesCount <= 2097152) goto L37;
        return;
    L37:
        if (r0.moovFound == 0) goto L42;
        if (r0.requestInfos.size() <= 0) goto L42;
        return;
    L42:
        if (r0.isStory == false) goto L46;
        int r1 = Math.max(0, r0.currentMaxDownloadRequests - r0.requestInfos.size());
    L44:
        int r11 = r1;
    L58:
        if (r0.requestedReference == false) goto L60;
    L62:
        int r12 = 0;
    L63:
        if (r12 >= r11) goto L260;
        int r13 = 2;
        if (r0.isPreloadVideoOperation == true) goto L67;
        ArrayList<Range> r14 = r0.notRequestedBytesRanges;
        if (r14 == null) goto L131;
        long r2 = r0.streamPriorityStartOffset;
        if (r2 != r8) goto L105;
        r2 = r0.streamStartOffset;
    L105:
        int r15 = r14.size();
        int r6 = 0;
        long r4 = LongCompanionObject.MAX_VALUE;
        long r142 = LongCompanionObject.MAX_VALUE;
    L106:
        int r18 = r13;
        if (r6 >= r15) goto L122;
        Range r132 = r0.notRequestedBytesRanges.get(r6);
        if (r2 == r8) goto L121;
        if (Range.m4019$$Nest$fgetstart(r132) > r2) goto L117;
        if (Range.m4018$$Nest$fgetend(r132) <= r2) goto L117;
        r4 = Long.MAX_VALUE;
    L115:
        long r19 = r8;
        if (r2 == LongCompanionObject.MAX_VALUE) goto L127;
        long r16 = r2;
    L129:
        long r22 = r16;
    L132:
        int r17 = r0.preloadPrefixSize;
        if (r17 > 0) goto L135;
    L139:
        long r42 = r0.totalBytesCount;
        if (r42 <= r19) goto L147;
        if (r22 <= r19) goto L147;
        if (r22 < r42) goto L147;
        boolean r02 = BuildVars.DEBUG_VERSION;
        return;
    L147:
        if (r0.isPreloadVideoOperation == true) goto L151;
        ArrayList<Range> r110 = r0.notRequestedBytesRanges;
        if (r110 == null) goto L151;
        r0.addPart(r110, r22, r0.currentDownloadChunkSize + r22, false);
        boolean r111 = BuildVars.DEBUG_VERSION;
    L151:
        long r43 = r0.totalBytesCount;
        if (r43 > r19) goto L154;
    L161:
        boolean r29 = true;
    L163:
        if (r31 == (-1)) goto L165;
        int r44 = r31;
    L171:
        if (r0.isForceRequest == false) goto L173;
        int r112 = 32;
    L175:
        if (r0.isCdn == false) goto L178;
        TLRPC.TL_upload_getCdnFile r5 = new TLRPC.TL_upload_getCdnFile();
        r5.file_token = r0.cdnToken;
        r5.offset = r22;
        r5.limit = r0.currentDownloadChunkSize;
        r112 = r112 | 1;
        TLRPC.TL_upload_getFile r52 = r5;
    L181:
        r0.requestedBytesCount += r0.currentDownloadChunkSize;
        RequestInfo r82 = new RequestInfo();
        r0.requestInfos.add(r82);
        RequestInfo.m4028$$Nest$fputoffset(r82, r22);
        r82.chunkSize = r0.currentDownloadChunkSize;
        RequestInfo.m4027$$Nest$fputforceSmallChunk(r82, r0.forceSmallChunk);
        r82.connectionType = r44;
        if (r0.isPreloadVideoOperation == true) goto L202;
        if (r0.supportsPreloading == false) goto L202;
        if (r0.preloadStream == null) goto L202;
        HashMap<Long, PreloadRange> r23 = r0.preloadedBytesRanges;
        if (r23 == null) goto L202;
        PreloadRange r24 = r23.get(Long.valueOf(RequestInfo.m4023$$Nest$fgetoffset(r82)));
        if (r24 == null) goto L202;
        RequestInfo.m4029$$Nest$fputresponse(r82, new TLRPC.TL_upload_file());
        if (BuildVars.DEBUG_VERSION == true) goto L195;
    L199:
        NativeByteBuffer r3 = new NativeByteBuffer((int) PreloadRange.m4017$$Nest$fgetlength(r24));     // Catch: Exception -> L227
        r0.preloadStream.seek(PreloadRange.m4016$$Nest$fgetfileOffset(r24));     // Catch: Exception -> L227
        r0.preloadStream.getChannel().read(r3.buffer);     // Catch: Exception -> L227
        r3.buffer.position(0);     // Catch: Exception -> L227
        RequestInfo.m4024$$Nest$fgetresponse(r82).bytes = r3;     // Catch: Exception -> L227
        Utilities.stageQueue.postRunnable(new FileLoadOperation$$ExternalSyntheticLambda1(r0, r82));     // Catch: Exception -> L227
        long r133 = r19;
    L225:
        r12 = r12 + 1;
        r8 = r133;
        goto L63
    L195:
        if (PreloadRange.m4017$$Nest$fgetlength(r24) <= 2147483647L) goto L199;
        throw new RuntimeException("cast long to integer");     // Catch: Exception -> L227
    L202:
        if (r0.streamPriorityStartOffset != r19) goto L204;
        r133 = r19;
    L208:
        TLRPC.InputFileLocation r25 = r0.location;
        if ((r25 instanceof TLRPC.TL_inputPeerPhotoFileLocation) == true) goto L211;
    L213:
        RequestInfo.m4027$$Nest$fputforceSmallChunk(r82, r0.forceSmallChunk);
        if (BuildVars.LOGS_ENABLED == false) goto L216;
        r82.requestStartTime = System.currentTimeMillis();
    L216:
        int r9 = r112 | 2048;
        if (r0.isCdn == false) goto L220;
        int r113 = r0.cdnDatacenterId;
    L219:
        int r32 = r113;
        ConnectionsManager r21 = ConnectionsManager.getInstance(r0.currentAccount);
        FileLoadOperation r114 = r0;
        r0 = r114;
        int r115 = r21.sendRequestSync(r52, new FileLoadOperation$$ExternalSyntheticLambda2(r114, r82, r32, r44, r52), null, null, r9, r32, r44, r29);
        r82.requestToken = r115;
        if (BuildVars.LOGS_ENABLED == false) goto L224;
        FileLog.d("debug_loading: " + r0.cacheFileFinal.getName() + " dc=" + r32 + " send reqId " + r82.requestToken + " offset=" + RequestInfo.m4023$$Nest$fgetoffset(r82) + " conType=" + r44 + " priority=" + r0.priority);
    L224:
        AndroidUtilities.runOnUIThread(new FileLoadOperation$$ExternalSyntheticLambda3(r0, r115));
        r0.requestsCount++;
        goto L225
    L220:
        r113 = r0.datacenterId;
        goto L219
    L211:
        if (((TLRPC.TL_inputPeerPhotoFileLocation) r25).photo_id != r133) goto L213;
        r0.requestReference(r82);
        goto L225
    L204:
        if (BuildVars.DEBUG_VERSION == false) goto L206;
        FileLog.d("frame get offset = " + r0.streamPriorityStartOffset);
    L206:
        r133 = r19;
        r0.streamPriorityStartOffset = r133;
        r0.priorityRequestInfo = r82;
        goto L208
    L178:
        if (r0.webLocation == null) goto L180;
        TLRPC.TL_upload_getWebFile r53 = new TLRPC.TL_upload_getWebFile();
        r53.location = r0.webLocation;
        r53.offset = (int) r22;
        r53.limit = r0.currentDownloadChunkSize;
        r52 = r53;
        goto L181
    L180:
        TLRPC.TL_upload_getFile r54 = new TLRPC.TL_upload_getFile();
        r54.location = r0.location;
        r54.offset = r22;
        r54.limit = r0.currentDownloadChunkSize;
        r54.cdn_supported = true;
        r52 = r54;
        goto L181
    L173:
        r112 = 0;
        goto L175
    L165:
        if ((r0.requestsCount % 2) != 0) goto L167;
        int r134 = r18;
    L168:
        r44 = r134;
        goto L171
    L167:
        r134 = 65538;
        goto L168
    L154:
        if (r12 == (r11 - 1)) goto L161;
        if (r43 > r19) goto L158;
    L160:
        r29 = false;
        goto L163
    L158:
        if ((r0.currentDownloadChunkSize + r22) < r43) goto L160;
    L135:
        if (r22 < r17) goto L139;
        if (r0.canFinishPreload() == false) goto L139;
        boolean r03 = BuildVars.DEBUG_VERSION;
        return;
    L127:
        if (r4 == LongCompanionObject.MAX_VALUE) goto L130;
        r16 = r4;
        goto L129
    L130:
        boolean r04 = BuildVars.DEBUG_VERSION;
        return;
    L117:
        if (r2 >= Range.m4019$$Nest$fgetstart(r132)) goto L121;
        if (Range.m4019$$Nest$fgetstart(r132) >= r142) goto L121;
        r142 = Range.m4019$$Nest$fgetstart(r132);
    L121:
        r4 = Math.min(r4, Range.m4019$$Nest$fgetstart(r132));
        r6 = r6 + 1;
        r13 = r18;
        r8 = r8;
        goto L106
    L122:
        r2 = r142;
        goto L115
    L131:
        r19 = r8;
        r18 = 2;
        r16 = r0.requestedBytesCount;
        goto L129
    L67:
        if (r0.moovFound != 0) goto L69;
    L72:
        long r116 = r0.nextPreloadDownloadOffset;
        if (r116 != (-1)) goto L93;
        int r117 = (2097152 / r0.currentDownloadChunkSize) + 2;
        long r26 = r8;
    L75:
        if (r117 == 0) goto L81;
        if (r0.requestedPreloadedBytesRanges.containsKey(Long.valueOf(r26)) == false) goto L78;
        int r45 = r0.currentDownloadChunkSize;
        r26 = r26 + r45;
        long r55 = r0.totalBytesCount;
        if (r26 > r55) goto L81;
        if (r0.moovFound != 2) goto L87;
        if (r26 != (r45 * 8)) goto L87;
        r26 = ((r55 - 1048576) / r45) * r45;
    L87:
        r117 = r117 - 1;
        goto L75
    L78:
        r116 = r26;
        boolean r33 = true;
    L88:
        if (r33 == true) goto L93;
        if (r0.requestInfos.isEmpty() == false) goto L93;
        r0.onFinishLoadingFile(false, 0, false);
    L81:
        r116 = r26;
        r33 = false;
    L93:
        if (r0.requestedPreloadedBytesRanges != null) goto L95;
        r0.requestedPreloadedBytesRanges = new HashMap();
    L95:
        r0.requestedPreloadedBytesRanges.put(Long.valueOf(r116), 1);
        if (BuildVars.DEBUG_VERSION == false) goto L98;
        FileLog.d("start next preload from " + r116 + " size " + r0.totalBytesCount + " for " + r0.cacheFilePreload);
    L98:
        r0.preloadNotRequestedBytesCount -= r0.currentDownloadChunkSize;
        r22 = r116;
        r19 = r8;
        r18 = 2;
        goto L132
    L69:
        if (r0.preloadNotRequestedBytesCount > r8) goto L72;
        boolean r05 = BuildVars.DEBUG_VERSION;
        return;
    L260:
        return;
    L60:
        if (FileRefController.getInstance(r0.currentAccount).applyCachedFileReference(r0.parentObject, new Object[]{r0.location, r0}) == false) goto L62;
        FileLog.d(r0.fileName + " before download updated file ref from file ref cache!");
        goto L62
    L46:
        if (r0.streamPriorityStartOffset == 0) goto L48;
    L56:
        r11 = 1;
        goto L58
    L48:
        if (r0.nextPartWasPreloaded == true) goto L56;
        if (r0.isPreloadVideoOperation == false) goto L54;
        if (r0.moovFound == 0) goto L56;
    L54:
        if (r0.totalBytesCount <= 0) goto L56;
        r1 = Math.max(0, r0.currentMaxDownloadRequests - r0.requestInfos.size());
        goto L44
    }

    public void updateProgress() {
        FileLoadOperationDelegate r0 = this.delegate;
        if (r0 == null) goto L10;
        long r2 = this.downloadedBytes;
        long r4 = this.totalBytesCount;
        if (r2 != r4) goto L7;
        return;
    L7:
        if (r4 <= 0) goto L12;
        r0.didChangedLoadProgress(this, r2, r4);
        return;
    L12:
        return;
    }

    public boolean wasStarted() {
        if (this.started == true) goto L5;
        return false;
    L5:
        if (this.paused == true) goto L10;
        return true;
    L10:
        return false;
    }
}
