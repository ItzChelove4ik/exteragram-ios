package org.telegram.messenger;

import android.content.SharedPreferences;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.gms.cast.HlsSegmentFormat;
import java.io.File;
import java.io.FileDescriptor;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public class FileUploadOperation {
    private static final int initialRequestsBoostCount = 14;
    private static final int initialRequestsCount = 8;
    private static final int initialRequestsSlowNetworkCount = 1;
    private static final int maxUploadingKBytes = 2048;
    private static final int maxUploadingSlowNetworkKBytes = 32;
    private static final int minUploadChunkBoostSize = 512;
    private static final int minUploadChunkSize = 128;
    private static final int minUploadChunkSlowNetworkSize = 32;
    private long availableSize;
    private SparseArray<UploadCachedResult> cachedResults;
    public volatile boolean caughtPremiumFloodWait;
    private int currentAccount;
    private long currentFileId;
    private int currentPartNum;
    private int currentType;
    private int currentUploadRequetsCount;
    private FileUploadOperationDelegate delegate;
    private long estimatedSize;
    private String fileKey;
    private int fingerprint;
    private boolean forceSmallFile;
    private ArrayList<byte[]> freeRequestIvs;
    private boolean isBigFile;
    private boolean isEncrypted;
    private boolean isLastPart;
    private byte[] iv;
    private byte[] ivChange;
    private byte[] key;
    protected long lastProgressUpdateTime;
    private int lastSavedPartNum;
    private int maxRequestsCount;
    private boolean nextPartFirst;
    private int operationGuid;
    private SharedPreferences preferences;
    private byte[] readBuffer;
    private long readBytesCount;
    private boolean[] recalculatedEstimatedSize;
    private int requestNum;
    public final SparseIntArray requestTokens;
    private int saveInfoTimes;
    private boolean slowNetwork;
    private boolean started;
    private int state;
    private RandomAccessFile stream;
    private long totalFileSize;
    private int totalPartsCount;
    public final ArrayList<Integer> uiRequestTokens;
    private int uploadChunkSize;
    private boolean uploadFirstPartLater;
    private int uploadStartTime;
    private long uploadedBytesCount;
    private String uploadingFilePath;

    /* loaded from: classes5.dex */
    public interface FileUploadOperationDelegate {
        void didChangedUploadProgress(FileUploadOperation r1, long r2, long r4);

        void didFailedUploadingFile(FileUploadOperation r1);

        void didFinishUploadingFile(FileUploadOperation r1, TLRPC.InputFile r2, TLRPC.InputEncryptedFile r3, byte[] r4, byte[] r5);
    }

    /* loaded from: classes5.dex */
    public static class UploadCachedResult {
        private long bytesOffset;
        private byte[] iv;

        /* renamed from: -$$Nest$fgetbytesOffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m4087$$Nest$fgetbytesOffset(UploadCachedResult r2) {
            return r2.bytesOffset;
        }

        /* renamed from: -$$Nest$fgetiv, reason: not valid java name */
        public static /* bridge */ /* synthetic */ byte[] m4088$$Nest$fgetiv(UploadCachedResult r0) {
            return r0.iv;
        }

        /* renamed from: -$$Nest$fputbytesOffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4089$$Nest$fputbytesOffset(UploadCachedResult r0, long r1) {
            r0.bytesOffset = r1;
        }

        /* renamed from: -$$Nest$fputiv, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4090$$Nest$fputiv(UploadCachedResult r0, byte[] r1) {
            r0.iv = r1;
        }

        private UploadCachedResult() {
        }

        public /* synthetic */ UploadCachedResult(FileUploadOperationIA r1) {
            this();
        }
    }

    public static /* synthetic */ void $r8$lambda$1XkVYZbEZ7gTmGzXsWv_ItEhusA(FileUploadOperation r0) {
        r0.lambda$onNetworkChanged$2();
    }

    public static /* synthetic */ void $r8$lambda$G5DQPne1QXyDmsAakl9Fzsg02eg(FileUploadOperation r0, boolean r1) {
        r0.lambda$onNetworkChanged$1(r1);
    }

    /* renamed from: $r8$lambda$YVn-M6o9IvX-lomQaag_MnDv9c0, reason: not valid java name */
    public static /* synthetic */ void m4083$r8$lambda$YVnM6o9IvXlomQaag_MnDv9c0(FileUploadOperation r0) {
        r0.lambda$start$0();
    }

    /* renamed from: $r8$lambda$cQCJ-fpVnyCHPhxFB74iIWo6YqU, reason: not valid java name */
    public static /* synthetic */ void m4084$r8$lambda$cQCJfpVnyCHPhxFB74iIWo6YqU(FileUploadOperation r0, Float r1, long r2, long r4) {
        r0.lambda$checkNewDataAvailable$4(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$hT1DOtLAmK5JBBO8OP_JkfxC5ng(FileUploadOperation r0, int r1, int[] r2, int r3, byte[] r4, int r5, int r6, int r7, long r8, TLObject r10, TLRPC.TL_error r11) {
        r0.lambda$startUploadRequest$6(r1, r2, r3, r4, r5, r6, r7, r8, r10, r11);
    }

    public static /* synthetic */ void $r8$lambda$lmAo6I4YePhZl5_mhh_Q9aXOlW0(FileUploadOperation r0, int[] r1) {
        r0.lambda$startUploadRequest$9(r1);
    }

    /* renamed from: $r8$lambda$sSKF9hRaulnMYleXVp2-MWEUGVc, reason: not valid java name */
    public static /* synthetic */ void m4085$r8$lambda$sSKF9hRaulnMYleXVp2MWEUGVc(FileUploadOperation r0) {
        r0.lambda$startUploadRequest$7();
    }

    public static /* synthetic */ void $r8$lambda$smBoTxybgTVNrSb18RD0jxWF0dw(FileUploadOperation r0) {
        r0.lambda$cancel$3();
    }

    /* renamed from: $r8$lambda$uk6bzCwwj84-U3S9L5FqwbsAaGc, reason: not valid java name */
    public static /* synthetic */ void m4086$r8$lambda$uk6bzCwwj84U3S9L5FqwbsAaGc(FileUploadOperation r0, int[] r1) {
        r0.lambda$startUploadRequest$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$ysxe4bfrWt7CISOkKU8I3fSMuFg(FileUploadOperation r0) {
        r0.lambda$startUploadRequest$8();
    }

    public FileUploadOperation(int r2, String r3, boolean r4, long r5, int r7) {
        this.uploadChunkSize = 65536;
        this.requestTokens = new SparseIntArray();
        this.uiRequestTokens = new ArrayList();
        this.cachedResults = new SparseArray();
        this.recalculatedEstimatedSize = new boolean[]{false, false};
        this.currentAccount = r2;
        this.uploadingFilePath = r3;
        this.isEncrypted = r4;
        this.estimatedSize = r5;
        this.currentType = r7;
        if (r5 == 0) goto L6;
        if (r4 == true) goto L6;
        boolean r22 = true;
    L7:
        this.uploadFirstPartLater = r22;
        return;
    L6:
        r22 = false;
        goto L7
    }

    private void calcTotalPartsCount() {
        if (this.uploadFirstPartLater == false) goto L10;
        boolean r0 = this.isBigFile;
        long r3 = this.totalFileSize;
        if (r0 == false) goto L8;
        int r02 = this.uploadChunkSize;
        this.totalPartsCount = ((int) ((((r3 - r02) + r02) - 1) / r02)) + 1;
        return;
    L8:
        int r03 = this.uploadChunkSize;
        this.totalPartsCount = ((int) ((((r3 - 1024) + r03) - 1) / r03)) + 1;
        return;
    L10:
        long r32 = this.totalFileSize;
        int r04 = this.uploadChunkSize;
        this.totalPartsCount = (int) (((r32 + r04) - 1) / r04);
    }

    private void cleanup() {
        if (this.preferences != null) goto L5;
        this.preferences = ApplicationLoader.applicationContext.getSharedPreferences("uploadinfo", 0);
    L5:
        this.preferences.edit().remove(this.fileKey + "_time").remove(this.fileKey + "_size").remove(this.fileKey + "_uploaded").remove(this.fileKey + "_id").remove(this.fileKey + "_iv").remove(this.fileKey + "_key").remove(this.fileKey + "_ivc").apply();
        RandomAccessFile r0 = this.stream;     // Catch: Exception -> L10
        if (r0 == null) goto L12;
        r0.close();     // Catch: Exception -> L10
        this.stream = null;     // Catch: Exception -> L10
    L12:
        AutoDeleteMediaTask.unlockFile(this.uploadingFilePath);
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
        goto L12
    }

    private /* synthetic */ void lambda$cancel$3() {
        int r0 = 0;
    L4:
        if (r0 >= this.requestTokens.size()) goto L6;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.requestTokens.valueAt(r0), true);
        r0 = r0 + 1;
        goto L4
    }

    private /* synthetic */ void lambda$checkNewDataAvailable$4(Float r7, long r8, long r10) {
        if (r7 == null) goto L22;
        if (this.estimatedSize == 0) goto L22;
        if (r8 != 0) goto L22;
        boolean r3 = false;
        boolean r4 = true;
        if (r7.floatValue() <= 0.75f) goto L14;
        boolean[] r2 = this.recalculatedEstimatedSize;
        if (r2[0] == true) goto L14;
        r2[0] = true;
        r3 = true;
    L14:
        if (r7.floatValue() <= 0.95f) goto L18;
        boolean[] r22 = this.recalculatedEstimatedSize;
        if (r22[1] == true) goto L18;
        r22[1] = true;
    L19:
        if (r4 == false) goto L22;
        this.estimatedSize = (long) (r10 / r7.floatValue());
    L18:
        r4 = r3;
    L22:
        if (this.estimatedSize == 0) goto L31;
        if (r8 == 0) goto L31;
        this.estimatedSize = 0;
        this.totalFileSize = r8;
        calcTotalPartsCount();
        if (this.uploadFirstPartLater == true) goto L31;
        if (this.started == false) goto L31;
        storeFileUploadInfo();
    L31:
        if (r8 > 0) goto L34;
        r8 = r10;
    L34:
        this.availableSize = r8;
        if (this.currentUploadRequetsCount >= this.maxRequestsCount) goto L38;
        startUploadRequest();
        return;
    }

    private /* synthetic */ void lambda$onNetworkChanged$1(boolean r5) {
        if (this.slowNetwork == r5) goto L23;
        this.slowNetwork = r5;
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.d("network changed to slow = " + this.slowNetwork);
    L7:
        int r52 = 0;
        int r0 = 0;
    L8:
        int r2 = 1;
        if (r0 >= this.requestTokens.size()) goto L11;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.requestTokens.valueAt(r0), true);
        r0 = r0 + 1;
        goto L8
    L11:
        this.requestTokens.clear();
        cleanup();
        this.isLastPart = false;
        this.nextPartFirst = false;
        this.requestNum = 0;
        this.currentPartNum = 0;
        this.readBytesCount = 0;
        this.uploadedBytesCount = 0;
        this.saveInfoTimes = 0;
        this.key = null;
        this.iv = null;
        this.ivChange = null;
        this.currentUploadRequetsCount = 0;
        this.lastSavedPartNum = 0;
        this.uploadFirstPartLater = false;
        this.cachedResults.clear();
        this.operationGuid++;
        if (this.slowNetwork == true) goto L18;
        if (ExteraConfig.getUploadSpeedBoost() == false) goto L17;
        r2 = 14;
        goto L18
    L17:
        r2 = 8;
    L18:
        if (r52 >= r2) goto L30;
        if (r52 == 0) goto L22;
        if (r52 < this.maxRequestsCount) goto L22;
        return;
    L22:
        startUploadRequest();
        r52 = r52 + 1;
        goto L18
    L30:
        return;
    }

    private /* synthetic */ void lambda$onNetworkChanged$2() {
        this.uiRequestTokens.clear();
    }

    private /* synthetic */ void lambda$start$0() {
        int r2 = 0;
        this.preferences = ApplicationLoader.applicationContext.getSharedPreferences("uploadinfo", 0);
        this.slowNetwork = ApplicationLoader.isConnectionSlow();
        if (BuildVars.LOGS_ENABLED == false) goto L6;
        FileLog.d("start upload on slow network = " + this.slowNetwork);
    L6:
        if (this.slowNetwork == false) goto L9;
        int r0 = 1;
    L12:
        if (r2 >= r0) goto L17;
        if (r2 == 0) goto L16;
        if (r2 < this.maxRequestsCount) goto L16;
        return;
    L16:
        startUploadRequest();
        r2 = r2 + 1;
        goto L12
    L17:
        return;
    L9:
        if (ExteraConfig.getUploadSpeedBoost() == false) goto L11;
        r0 = 14;
        goto L12
    L11:
        r0 = 8;
        goto L12
    }

    private /* synthetic */ void lambda$startUploadRequest$5(int[] r2) {
        this.uiRequestTokens.remove(Integer.valueOf(r2[0]));
    }

    private /* synthetic */ void lambda$startUploadRequest$6(int r19, int[] r20, int r21, byte[] r22, int r23, int r24, int r25, long r26, TLObject r28, TLRPC.TL_error r29) {
        byte[] r6 = r22;
        if (r19 == this.operationGuid) goto L6;
        return;
    L6:
        if (BuildVars.LOGS_ENABLED == false) goto L8;
        FileLog.d("debug_uploading:  response reqId " + r20[0] + " time" + this.uploadingFilePath);
    L8:
        if (r28 == null) goto L10;
        int r4 = r28.networkType;
    L11:
        int r5 = this.currentType;
        if (r5 != 50331648) goto L14;
        StatsController.getInstance(this.currentAccount).incrementSentBytesCount(r4, 3, r21);
    L29:
        if (r6 == null) goto L31;
        this.freeRequestIvs.add(r6);
    L31:
        this.requestTokens.delete(r23);
        AndroidUtilities.runOnUIThread(new FileUploadOperation$$ExternalSyntheticLambda3(this, r20));
        if ((r28 instanceof TLRPC.TL_boolTrue) == true) goto L34;
        this.state = 4;
        this.delegate.didFailedUploadingFile(this);
        cleanup();
        return;
    L34:
        if (this.state != 1) goto L120;
        this.uploadedBytesCount += r24;
        long r2 = this.estimatedSize;
        if (r2 == 0) goto L40;
        long r27 = Math.max(this.availableSize, r2);
    L41:
        this.delegate.didChangedUploadProgress(this, this.uploadedBytesCount, r27);
        int r0 = this.currentUploadRequetsCount - 1;
        this.currentUploadRequetsCount = r0;
        if (this.isLastPart == false) goto L80;
        if (r0 != 0) goto L80;
        if (this.state != 1) goto L80;
        this.state = 3;
        byte[] r02 = this.key;
        boolean r210 = this.isBigFile;
        if (r02 != null) goto L52;
        if (r210 == false) goto L50;
        TLRPC.InputFile r03 = new TLRPC.TL_inputFileBig();
    L51:
        r03.parts = this.currentPartNum;
        r03.id = this.currentFileId;
        String r211 = this.uploadingFilePath;
        r03.name = r211.substring(r211.lastIndexOf("/") + 1);
        this.delegate.didFinishUploadingFile(this, r03, null, null, null);
        cleanup();
    L56:
        int r04 = this.currentType;
        if (r04 != 50331648) goto L60;
        StatsController.getInstance(this.currentAccount).incrementSentItemsCount(ApplicationLoader.getCurrentNetworkType(), 3, 1);
        return;
    L60:
        if (r04 != 33554432) goto L64;
        StatsController.getInstance(this.currentAccount).incrementSentItemsCount(ApplicationLoader.getCurrentNetworkType(), 2, 1);
        return;
    L64:
        if (r04 != 16777216) goto L68;
        StatsController.getInstance(this.currentAccount).incrementSentItemsCount(ApplicationLoader.getCurrentNetworkType(), 4, 1);
        return;
    L68:
        if (r04 != 67108864) goto L117;
        String r05 = this.uploadingFilePath;
        if (r05 != null) goto L72;
    L77:
        StatsController.getInstance(this.currentAccount).incrementSentItemsCount(ApplicationLoader.getCurrentNetworkType(), 5, 1);
        return;
    L72:
        if (r05.toLowerCase().endsWith(HlsSegmentFormat.MP3) == false) goto L74;
    L75:
        StatsController.getInstance(this.currentAccount).incrementSentItemsCount(ApplicationLoader.getCurrentNetworkType(), 7, 1);
        return;
    L74:
        if (this.uploadingFilePath.toLowerCase().endsWith("m4a") == false) goto L77;
    L117:
        return;
    L50:
        r03 = new TLRPC.TL_inputFile();
        r03.md5_checksum = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L51
    L52:
        if (r210 == false) goto L54;
        TLRPC.InputEncryptedFile r06 = new TLRPC.TL_inputEncryptedFileBigUploaded();
    L55:
        r06.parts = this.currentPartNum;
        r06.id = this.currentFileId;
        r06.key_fingerprint = this.fingerprint;
        this.delegate.didFinishUploadingFile(this, null, r06, this.key, this.iv);
        cleanup();
        goto L56
    L54:
        r06 = new TLRPC.TL_inputEncryptedFileUploaded();
        r06.md5_checksum = _UrlKt.FRAGMENT_ENCODE_SET;
    L80:
        if (r0 < this.maxRequestsCount) goto L82;
        return;
    L82:
        if (this.estimatedSize == 0) goto L84;
    L112:
        startUploadRequest();
        return;
    L84:
        if (this.uploadFirstPartLater == true) goto L112;
        if (this.nextPartFirst == true) goto L112;
        if (this.saveInfoTimes < 4) goto L90;
        this.saveInfoTimes = 0;
    L90:
        int r07 = this.lastSavedPartNum;
        if (r25 != r07) goto L107;
        this.lastSavedPartNum = r07 + 1;
        long r212 = r26;
    L93:
        UploadCachedResult r08 = this.cachedResults.get(this.lastSavedPartNum);
        if (r08 == null) goto L96;
        r212 = UploadCachedResult.m4087$$Nest$fgetbytesOffset(r08);
        r6 = UploadCachedResult.m4088$$Nest$fgetiv(r08);
        this.cachedResults.remove(this.lastSavedPartNum);
        this.lastSavedPartNum++;
        goto L93
    L96:
        boolean r09 = this.isBigFile;
        if (r09 == true) goto L99;
    L100:
        if (r09 == false) goto L102;
    L111:
        this.saveInfoTimes++;
        goto L112
    L102:
        if (this.saveInfoTimes != 0) goto L111;
    L103:
        SharedPreferences.Editor r010 = this.preferences.edit();
        r010.putLong(this.fileKey + "_uploaded", r212);
        if (this.isEncrypted == false) goto L106;
        r010.putString(this.fileKey + "_ivc", Utilities.bytesToHex(r6));
    L106:
        r010.apply();
        goto L111
    L99:
        if ((r212 % 1048576) == 0) goto L103;
    L107:
        UploadCachedResult r011 = new UploadCachedResult(null);
        UploadCachedResult.m4089$$Nest$fputbytesOffset(r011, r26);
        if (r6 == null) goto L110;
        UploadCachedResult.m4090$$Nest$fputiv(r011, new byte[32]);
        System.arraycopy(r6, 0, UploadCachedResult.m4088$$Nest$fgetiv(r011), 0, 32);
    L110:
        this.cachedResults.put(r25, r011);
        goto L111
    L40:
        r27 = this.totalFileSize;
        goto L41
    L120:
        return;
    L14:
        if (r5 != 33554432) goto L17;
        StatsController.getInstance(this.currentAccount).incrementSentBytesCount(r4, 2, r21);
        goto L29
    L17:
        if (r5 != 16777216) goto L20;
        StatsController.getInstance(this.currentAccount).incrementSentBytesCount(r4, 4, r21);
        goto L29
    L20:
        if (r5 != 67108864) goto L29;
        String r52 = this.uploadingFilePath;
        if (r52 != null) goto L24;
    L28:
        StatsController.getInstance(this.currentAccount).incrementSentBytesCount(r4, 5, r21);
        goto L29
    L24:
        if (r52.toLowerCase().endsWith(HlsSegmentFormat.MP3) == false) goto L26;
    L27:
        StatsController.getInstance(this.currentAccount).incrementSentBytesCount(r4, 7, r21);
        goto L29
    L26:
        if (this.uploadingFilePath.toLowerCase().endsWith("m4a") == false) goto L28;
    L10:
        r4 = ApplicationLoader.getCurrentNetworkType();
        goto L11
    }

    private /* synthetic */ void lambda$startUploadRequest$7() {
        if (this.currentUploadRequetsCount >= this.maxRequestsCount) goto L6;
        startUploadRequest();
        return;
    }

    private /* synthetic */ void lambda$startUploadRequest$8() {
        Utilities.stageQueue.postRunnable(new FileUploadOperation$$ExternalSyntheticLambda4(this));
    }

    private /* synthetic */ void lambda$startUploadRequest$9(int[] r2) {
        this.uiRequestTokens.add(Integer.valueOf(r2[0]));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void startUploadRequest() {
        if (this.state != 1) goto L252;
        this.started = true;     // Catch: Exception -> L16
        if (this.stream != null) goto L163;
        File r8 = new File(this.uploadingFilePath);     // Catch: Exception -> L16
        this.stream = new RandomAccessFile(r8, "r");     // Catch: Exception -> L16
        boolean r0 = AndroidUtilities.isInternalUri(((Integer) FileDescriptor.class.getDeclaredMethod("getInt$", null).invoke(this.stream.getFD(), null)).intValue());     // Catch: Throwable -> L10
    L12:
        if (r0 == true) goto L162;
        long r9 = this.estimatedSize;     // Catch: Exception -> L16
        if (r9 == 0) goto L18;
        this.totalFileSize = r9;     // Catch: Exception -> L16
    L20:
        if (this.forceSmallFile == false) goto L22;
    L24:
        long r82 = MessagesController.getInstance(this.currentAccount).uploadMaxFileParts;     // Catch: Exception -> L16
        if (AccountInstance.getInstance(this.currentAccount).getUserConfig().isPremium() == false) goto L30;
        if (this.totalFileSize <= FileLoader.DEFAULT_MAX_FILE_SIZE) goto L30;
        r82 = MessagesController.getInstance(this.currentAccount).uploadMaxFilePartsPremium;     // Catch: Exception -> L16
    L30:
        if (this.slowNetwork == false) goto L33;
        long r13 = 32;
    L36:
        long r83 = r82 * 1024;     // Catch: Exception -> L16
        int r02 = (int) Math.max(r13, ((this.totalFileSize + r83) - 1) / r83);     // Catch: Exception -> L16
        this.uploadChunkSize = r02;     // Catch: Exception -> L16
        if ((1024 % r02) == 0) goto L44;
        int r03 = 64;
    L40:
        if (this.uploadChunkSize <= r03) goto L42;
        r03 = r03 * 2;     // Catch: Exception -> L16
        goto L40
    L42:
        this.uploadChunkSize = r03;     // Catch: Exception -> L16
    L44:
        if (this.slowNetwork == false) goto L46;
        int r04 = 32;
    L47:
        this.maxRequestsCount = Math.max(1, r04 / this.uploadChunkSize);     // Catch: Exception -> L16
        if (this.isEncrypted == false) goto L53;
        this.freeRequestIvs = new ArrayList(this.maxRequestsCount);     // Catch: Exception -> L16
        int r05 = 0;
    L51:
        if (r05 >= this.maxRequestsCount) goto L53;
        this.freeRequestIvs.add(new byte[32]);     // Catch: Exception -> L16
        r05 = r05 + 1;     // Catch: Exception -> L16
    L53:
        this.uploadChunkSize *= 1024;
        calcTotalPartsCount();     // Catch: Exception -> L16
        this.readBuffer = new byte[this.uploadChunkSize];     // Catch: Exception -> L16
        StringBuilder r06 = new StringBuilder();     // Catch: Exception -> L16
        r06.append(this.uploadingFilePath);     // Catch: Exception -> L16
        if (this.isEncrypted == false) goto L56;
        String r84 = "enc";
    L57:
        r06.append(r84);     // Catch: Exception -> L16
        this.fileKey = Utilities.MD5(r06.toString());     // Catch: Exception -> L16
        long r85 = this.preferences.getLong(this.fileKey + "_size", 0);     // Catch: Exception -> L16
        this.uploadStartTime = (int) (System.currentTimeMillis() / 1000);     // Catch: Exception -> L16
        if (this.uploadFirstPartLater == false) goto L60;
    L130:
        byte[] r10 = null;
    L132:
        boolean r11 = true;
    L133:
        if (r11 == false) goto L145;
        if (this.isEncrypted == false) goto L137;
        byte[] r07 = new byte[32];     // Catch: Exception -> L16
        this.iv = r07;     // Catch: Exception -> L16
        this.key = new byte[32];     // Catch: Exception -> L16
        this.ivChange = new byte[32];     // Catch: Exception -> L16
        Utilities.random.nextBytes(r07);     // Catch: Exception -> L16
        Utilities.random.nextBytes(this.key);     // Catch: Exception -> L16
        System.arraycopy(this.iv, 0, this.ivChange, 0, 32);     // Catch: Exception -> L16
    L137:
        this.currentFileId = Utilities.random.nextLong();     // Catch: Exception -> L16
        if (this.nextPartFirst == true) goto L145;
        if (this.uploadFirstPartLater == true) goto L145;
        if (this.estimatedSize != 0) goto L145;
        storeFileUploadInfo();     // Catch: Exception -> L16
    L145:
        if (this.isEncrypted == true) goto L240;
    L153:
        this.uploadedBytesCount = this.readBytesCount;     // Catch: Exception -> L16
        this.lastSavedPartNum = this.currentPartNum;     // Catch: Exception -> L16
        if (this.uploadFirstPartLater == false) goto L165;
        boolean r08 = this.isBigFile;     // Catch: Exception -> L16
        RandomAccessFile r6 = this.stream;
        if (r08 == false) goto L159;
        r6.seek(this.uploadChunkSize);     // Catch: Exception -> L16
        this.readBytesCount = this.uploadChunkSize;     // Catch: Exception -> L16
    L160:
        this.currentPartNum = 1;     // Catch: Exception -> L16
        goto L165
    L159:
        r6.seek(1024);     // Catch: Exception -> L16
        this.readBytesCount = 1024;     // Catch: Exception -> L16
    L165:
        if (this.estimatedSize != 0) goto L167;
    L169:
        boolean r09 = this.nextPartFirst;     // Catch: Exception -> L16
        RandomAccessFile r4 = this.stream;
        if (r09 == true) goto L173;
        int r010 = r4.read(this.readBuffer);     // Catch: Exception -> L16
    L181:
        if (r010 != (-1)) goto L184;
        return;
    L184:
        if (this.isEncrypted == true) goto L186;
    L188:
        int r42 = 0;
    L189:
        int r62 = r010 + r42;     // Catch: Exception -> L16
        NativeByteBuffer r5 = new NativeByteBuffer(r62);     // Catch: Exception -> L16
        if (this.nextPartFirst == true) goto L198;
        if (r010 != this.uploadChunkSize) goto L198;
        if (this.estimatedSize == 0) goto L196;
    L201:
        int r132 = 0;
        r5.writeBytes(this.readBuffer, 0, r010);     // Catch: Exception -> L16
        if (this.isEncrypted == false) goto L207;
        int r86 = 0;
    L204:
        if (r86 >= r42) goto L206;
        r5.writeByte(r132);     // Catch: Exception -> L16
        r86 = r86 + 1;     // Catch: Exception -> L16
        r132 = 0;
        goto L204
    L206:
        Utilities.aesIgeEncryption(r5.buffer, this.key, this.ivChange, true, true, 0, r62);     // Catch: Exception -> L16
        byte[] r43 = this.freeRequestIvs.get(0);     // Catch: Exception -> L16
        System.arraycopy(this.ivChange, 0, r43, 0, 32);     // Catch: Exception -> L16
        this.freeRequestIvs.remove(0);     // Catch: Exception -> L16
    L209:
        if (this.isBigFile == false) goto L216;
        TLRPC.TL_upload_saveBigFilePart r63 = new TLRPC.TL_upload_saveBigFilePart();     // Catch: Exception -> L16
        int r7 = this.currentPartNum;     // Catch: Exception -> L16
        r63.file_part = r7;     // Catch: Exception -> L16
        r63.file_id = this.currentFileId;     // Catch: Exception -> L16
        if (this.estimatedSize == 0) goto L213;
        r63.file_total_parts = -1;     // Catch: Exception -> L16
    L214:
        r63.bytes = r5;     // Catch: Exception -> L16
        TLRPC.TL_upload_saveFilePart r64 = r63;
    L215:
        TLRPC.TL_upload_saveFilePart r18 = r64;
        int r87 = r7;
        if (this.isLastPart == true) goto L220;
    L222:
        this.readBytesCount += r010;
        this.currentPartNum++;
        this.currentUploadRequetsCount++;
        int r65 = this.requestNum;
        this.requestNum = r65 + 1;
        long r92 = r87 + r010;
        int r3 = r18.getObjectSize() + 4;
        int r52 = this.operationGuid;
        if (this.slowNetwork == false) goto L226;
        int r24 = 4;
    L227:
        System.currentTimeMillis();
        int[] r2 = new int[1];
        ConnectionsManager r17 = ConnectionsManager.getInstance(this.currentAccount);
        FileUploadOperation$$ExternalSyntheticLambda5 r19 = new FileUploadOperation$$ExternalSyntheticLambda5(this, r52, r2, r3, r43, r65, r010, r87, r92);
        FileUploadOperation$$ExternalSyntheticLambda6 r22 = new FileUploadOperation$$ExternalSyntheticLambda6(this);
        if (this.forceSmallFile == false) goto L230;
        int r222 = 4;
    L231:
        r2[0] = r17.sendRequest(r18, r19, null, r22, r222, Integer.MAX_VALUE, r24, true);
        if (BuildVars.LOGS_ENABLED == false) goto L234;
        FileLog.d("debug_uploading:  send reqId " + r2[0] + " " + this.uploadingFilePath + " file_part=" + r87 + " isBig=" + this.isBigFile + " file_id=" + this.currentFileId);
    L234:
        this.requestTokens.put(r65, r2[0]);
        AndroidUtilities.runOnUIThread(new FileUploadOperation$$ExternalSyntheticLambda7(this, r2));
        return;
    L230:
        r222 = 0;
        goto L231
    L226:
        r24 = ((r65 % 4) << 16) | 4;
        goto L227
    L220:
        if (this.nextPartFirst == false) goto L222;
        this.nextPartFirst = false;     // Catch: Exception -> L16
        this.currentPartNum = this.totalPartsCount - 1;     // Catch: Exception -> L16
        this.stream.seek(this.totalFileSize);     // Catch: Exception -> L16
        goto L222
    L213:
        r63.file_total_parts = this.totalPartsCount;     // Catch: Exception -> L16
        goto L214
    L216:
        TLRPC.TL_upload_saveFilePart r66 = new TLRPC.TL_upload_saveFilePart();     // Catch: Exception -> L16
        r7 = this.currentPartNum;     // Catch: Exception -> L16
        r66.file_part = r7;     // Catch: Exception -> L16
        r66.file_id = this.currentFileId;     // Catch: Exception -> L16
        r66.bytes = r5;     // Catch: Exception -> L16
        r64 = r66;
        goto L215
    L207:
        r43 = r10;
        goto L209
    L196:
        if (this.totalPartsCount != (this.currentPartNum + 1)) goto L201;
    L198:
        if (this.uploadFirstPartLater == false) goto L200;
        this.nextPartFirst = true;     // Catch: Exception -> L16
        this.uploadFirstPartLater = false;     // Catch: Exception -> L16
        goto L201
    L200:
        this.isLastPart = true;     // Catch: Exception -> L16
        goto L201
    L186:
        if ((r010 % 16) == 0) goto L188;
        r42 = 16 - (r010 % 16);     // Catch: Exception -> L16
        goto L189
    L173:
        r4.seek(0);     // Catch: Exception -> L16
        boolean r011 = this.isBigFile;     // Catch: Exception -> L16
        RandomAccessFile r44 = this.stream;
        if (r011 == false) goto L177;
        r010 = r44.read(this.readBuffer);     // Catch: Exception -> L16
        int r133 = 0;
    L178:
        this.currentPartNum = r133;     // Catch: Exception -> L16
        goto L181
    L177:
        r133 = 0;
        r010 = r44.read(this.readBuffer, 0, 1024);     // Catch: Exception -> L16
        goto L178
    L167:
        if ((this.readBytesCount + this.uploadChunkSize) <= this.availableSize) goto L169;
        return;
    L240:
        MessageDigest r012 = MessageDigest.getInstance("MD5");     // Catch: Exception -> L151
        byte[] r67 = new byte[64];     // Catch: Exception -> L151
        System.arraycopy(this.key, 0, r67, 0, 32);     // Catch: Exception -> L151
        System.arraycopy(this.iv, 0, r67, 32, 32);     // Catch: Exception -> L151
        byte[] r013 = r012.digest(r67);     // Catch: Exception -> L151
        int r68 = 0;
    L148:
        if (r68 >= 4) goto L153;
        this.fingerprint |= ((r013[r68] ^ r013[r68 + 4]) & 255) << (r68 * 8);
        r68 = r68 + 1;
        goto L148
    L151:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L16
        goto L153
    L60:
        if (this.nextPartFirst == true) goto L130;
        if (this.estimatedSize != 0) goto L130;
        if (r85 != this.totalFileSize) goto L130;
        this.currentFileId = this.preferences.getLong(this.fileKey + "_id", 0);     // Catch: Exception -> L16
        int r014 = this.preferences.getInt(this.fileKey + "_time", 0);     // Catch: Exception -> L16
        long r88 = this.preferences.getLong(this.fileKey + "_uploaded", 0);     // Catch: Exception -> L16
        if (this.isEncrypted == false) goto L79;
        String r112 = this.preferences.getString(this.fileKey + "_iv", null);     // Catch: Exception -> L16
        String r12 = this.preferences.getString(this.fileKey + "_key", null);     // Catch: Exception -> L16
        if (r112 == null) goto L78;
        if (r12 == null) goto L78;
        this.key = Utilities.hexToBytes(r12);     // Catch: Exception -> L16
        byte[] r113 = Utilities.hexToBytes(r112);     // Catch: Exception -> L16
        this.iv = r113;     // Catch: Exception -> L16
        byte[] r122 = this.key;     // Catch: Exception -> L16
        if (r122 == null) goto L78;
        if (r113 == null) goto L78;
        if (r122.length != 32) goto L78;
        if (r113.length != 32) goto L78;
        byte[] r123 = new byte[32];     // Catch: Exception -> L16
        this.ivChange = r123;     // Catch: Exception -> L16
        System.arraycopy(r113, 0, r123, 0, 32);     // Catch: Exception -> L16
    L78:
        boolean r114 = true;
    L80:
        if (r114 == true) goto L130;
        if (r014 == 0) goto L130;
        boolean r124 = this.isBigFile;     // Catch: Exception -> L16
        if (r124 == true) goto L85;
    L87:
        if (r124 == false) goto L89;
    L91:
        if (r014 != 0) goto L93;
        boolean r20 = r114;
    L117:
        r10 = null;
    L127:
        r11 = r20;
        goto L133
    L93:
        if (r88 <= 0) goto L130;
        this.readBytesCount = r88;     // Catch: Exception -> L16
        this.currentPartNum = (int) (r88 / this.uploadChunkSize);     // Catch: Exception -> L16
        if (r124 == true) goto L118;
        int r015 = 0;
    L97:
        r20 = r114;
        if (r015 >= (this.readBytesCount / this.uploadChunkSize)) goto L117;
        int r89 = this.stream.read(this.readBuffer);     // Catch: Exception -> L16
        if (this.isEncrypted == true) goto L102;
    L104:
        int r93 = 0;
    L105:
        int r115 = r89 + r93;     // Catch: Exception -> L16
        NativeByteBuffer r102 = new NativeByteBuffer(r115);     // Catch: Exception -> L16
        if (r89 == this.uploadChunkSize) goto L108;
    L109:
        this.isLastPart = true;     // Catch: Exception -> L16
    L110:
        int r134 = 0;
        r102.writeBytes(this.readBuffer, 0, r89);     // Catch: Exception -> L16
        if (this.isEncrypted == false) goto L116;
        int r810 = 0;
    L113:
        if (r810 >= r93) goto L115;
        r102.writeByte(r134);     // Catch: Exception -> L16
        r810 = r810 + 1;     // Catch: Exception -> L16
        r134 = 0;
        goto L113
    L115:
        Utilities.aesIgeEncryption(r102.buffer, this.key, this.ivChange, true, true, 0, r115);     // Catch: Exception -> L16
    L116:
        r102.reuse();     // Catch: Exception -> L16
        r015 = r015 + 1;     // Catch: Exception -> L16
        r114 = r20;
        goto L97
    L108:
        if (this.totalPartsCount != (this.currentPartNum + 1)) goto L110;
    L102:
        if ((r89 % 16) == 0) goto L104;
        r93 = 16 - (r89 % 16);     // Catch: Exception -> L16
        goto L105
    L118:
        r20 = r114;
        this.stream.seek(r88);     // Catch: Exception -> L16
        if (this.isEncrypted == false) goto L117;
        r10 = null;
        String r016 = this.preferences.getString(this.fileKey + "_ivc", null);     // Catch: Exception -> L16
        if (r016 == null) goto L129;
        byte[] r017 = Utilities.hexToBytes(r016);     // Catch: Exception -> L16
        this.ivChange = r017;     // Catch: Exception -> L16
        if (r017 == null) goto L128;
        if (r017.length == 32) goto L127;
    L128:
        this.readBytesCount = 0;     // Catch: Exception -> L16
        this.currentPartNum = 0;     // Catch: Exception -> L16
        goto L132
    L129:
        this.readBytesCount = 0;     // Catch: Exception -> L16
        this.currentPartNum = 0;     // Catch: Exception -> L16
        goto L132
    L89:
        if (r014 >= (this.uploadStartTime - 5400.0f)) goto L91;
    L86:
        r014 = 0;
        goto L91
    L85:
        if (r014 >= (this.uploadStartTime - 86400)) goto L87;
    L79:
        r114 = false;
        goto L80
    L56:
        r84 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L57
    L46:
        r04 = 2048;
        goto L47
    L33:
        if (ExteraConfig.getUploadSpeedBoost() == false) goto L35;
        r13 = 512;
        goto L36
    L35:
        r13 = 128;
        goto L36
    L22:
        if (this.totalFileSize <= 10485760) goto L24;
        this.isBigFile = true;     // Catch: Exception -> L16
        goto L24
    L18:
        this.totalFileSize = r8.length();     // Catch: Exception -> L16
        goto L20
    L162:
        throw new Exception("trying to upload internal file");     // Catch: Exception -> L16
    L10:
        th = move-exception;
        FileLog.e(th);     // Catch: Exception -> L16
        r0 = false;
        goto L12
    L163:
        r10 = null;
    L16:
        e = move-exception;
        FileLog.e(e);
        this.state = 4;
        this.delegate.didFailedUploadingFile(this);
        cleanup();
        return;
    }

    private void storeFileUploadInfo() {
        SharedPreferences.Editor r0 = this.preferences.edit();
        r0.putInt(this.fileKey + "_time", this.uploadStartTime);
        r0.putLong(this.fileKey + "_size", this.totalFileSize);
        r0.putLong(this.fileKey + "_id", this.currentFileId);
        r0.remove(this.fileKey + "_uploaded");
        if (this.isEncrypted == false) goto L5;
        r0.putString(this.fileKey + "_iv", Utilities.bytesToHex(this.iv));
        r0.putString(this.fileKey + "_ivc", Utilities.bytesToHex(this.ivChange));
        r0.putString(this.fileKey + "_key", Utilities.bytesToHex(this.key));
    L5:
        r0.apply();
    }

    public void cancel() {
        if (this.state != 3) goto L5;
        return;
    L5:
        this.state = 2;
        Utilities.stageQueue.postRunnable(new FileUploadOperation$$ExternalSyntheticLambda2(this));
        AutoDeleteMediaTask.unlockFile(this.uploadingFilePath);
        this.delegate.didFailedUploadingFile(this);
        cleanup();
    }

    public void checkNewDataAvailable(long r9, long r11, Float r13) {
        Utilities.stageQueue.postRunnable(new FileUploadOperation$$ExternalSyntheticLambda0(this, r13, r11, r9));
    }

    public long getTotalFileSize() {
        return this.totalFileSize;
    }

    public void onNetworkChanged(boolean r3) {
        if (this.state == 1) goto L5;
        return;
    L5:
        Utilities.stageQueue.postRunnable(new FileUploadOperation$$ExternalSyntheticLambda8(this, r3));
        AndroidUtilities.runOnUIThread(new FileUploadOperation$$ExternalSyntheticLambda9(this));
    }

    public void setDelegate(FileUploadOperationDelegate r1) {
        this.delegate = r1;
    }

    public void setForceSmallFile() {
        this.forceSmallFile = true;
    }

    public void start() {
        if (this.state == 0) goto L5;
        return;
    L5:
        this.state = 1;
        AutoDeleteMediaTask.lockFile(this.uploadingFilePath);
        Utilities.stageQueue.postRunnable(new FileUploadOperation$$ExternalSyntheticLambda1(this));
    }
}
