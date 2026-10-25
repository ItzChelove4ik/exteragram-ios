package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Pair;
import android.util.SparseArray;
import androidx.collection.LongSparseArray;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Components.VideoPlayer;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes.dex */
public class DownloadController extends BaseController implements NotificationCenter.NotificationCenterDelegate {
    public static final int AUTODOWNLOAD_TYPE_AUDIO = 2;
    public static final int AUTODOWNLOAD_TYPE_DOCUMENT = 8;
    public static final int AUTODOWNLOAD_TYPE_PHOTO = 1;
    public static final int AUTODOWNLOAD_TYPE_VIDEO = 4;
    private static final DownloadController[] Instance = null;
    public static final int PRESET_NUM_CHANNEL = 3;
    public static final int PRESET_NUM_CONTACT = 0;
    public static final int PRESET_NUM_GROUP = 2;
    public static final int PRESET_NUM_PM = 1;
    public static final int PRESET_SIZE_NUM_AUDIO = 3;
    public static final int PRESET_SIZE_NUM_DOCUMENT = 2;
    public static final int PRESET_SIZE_NUM_PHOTO = 0;
    public static final int PRESET_SIZE_NUM_VIDEO = 1;
    private final HashMap<String, FileDownloadProgressListener> addLaterArray;
    private final ArrayList<DownloadObject> audioDownloadQueue;
    Runnable clearUnviewedDownloadsRunnale;
    public int currentMobilePreset;
    public int currentRoamingPreset;
    public int currentWifiPreset;
    private final ArrayList<FileDownloadProgressListener> deleteLaterArray;
    private final ArrayList<DownloadObject> documentDownloadQueue;
    private final HashMap<String, DownloadObject> downloadQueueKeys;
    private final HashMap<Pair<Long, Integer>, DownloadObject> downloadQueuePairs;
    public final ArrayList<MessageObject> downloadingFiles;
    public Preset highPreset;
    private int lastCheckMask;
    private int lastTag;
    private boolean listenerInProgress;
    private boolean loadingAutoDownloadConfig;
    private final HashMap<String, ArrayList<WeakReference<MessageObject>>> loadingFileMessagesObservers;
    private final HashMap<String, ArrayList<WeakReference<FileDownloadProgressListener>>> loadingFileObservers;
    public Preset lowPreset;
    public Preset mediumPreset;
    public Preset mobilePreset;
    private final SparseArray<String> observersByTag;
    private final ArrayList<DownloadObject> photoDownloadQueue;
    public final ArrayList<MessageObject> recentDownloadingFiles;
    public Preset roamingPreset;
    private final LongSparseArray<Long> typingTimes;
    public final SparseArray<MessageObject> unviewedDownloads;
    private final ArrayList<DownloadObject> videoDownloadQueue;
    public Preset wifiPreset;

    /* renamed from: org.telegram.messenger.DownloadController$1, reason: invalid class name */
    public class AnonymousClass1 extends BroadcastReceiver {
        final /* synthetic */ DownloadController this$0;

        public AnonymousClass1(DownloadController r1) {
            this.this$0 = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r1, Intent r2) {
            this.this$0.checkAutodownloadSettings();
        }
    }

    public interface FileDownloadProgressListener {
        int getObserverTag();

        void onFailedDownload(String r1, boolean r2);

        void onProgressDownload(String r1, long r2, long r4);

        void onProgressUpload(String r1, long r2, long r4, boolean r6);

        void onSuccessDownload(String r1);
    }

    public static class Preset {
        public boolean enabled;
        public boolean lessCallData;
        public int[] mask;
        public int maxVideoBitrate;
        public boolean preloadMusic;
        public boolean preloadStories;
        public boolean preloadVideo;
        public long[] sizes;

        public Preset(String r11, String r12) {
            this.mask = new int[4];
            this.sizes = new long[4];
            String[] r112 = r11.split("_");
            if (r112.length < 11) goto L48;
            boolean r4 = false;
            this.mask[0] = Utilities.parseInt(r112[0]);
            this.mask[1] = Utilities.parseInt(r112[1]);
            this.mask[2] = Utilities.parseInt(r112[2]);
            this.mask[3] = Utilities.parseInt(r112[3]);
            this.sizes[0] = Utilities.parseInt(r112[4]);
            this.sizes[1] = Utilities.parseInt(r112[5]);
            this.sizes[2] = Utilities.parseInt(r112[6]);
            this.sizes[3] = Utilities.parseInt(r112[7]);
            if (Utilities.parseInt(r112[8]) != 1) goto L7;
            boolean r0 = true;
        L8:
            this.preloadVideo = r0;
            if (Utilities.parseInt(r112[9]) != 1) goto L11;
            boolean r02 = true;
        L12:
            this.preloadMusic = r02;
            if (Utilities.parseInt(r112[10]) != 1) goto L15;
            boolean r03 = true;
        L16:
            this.enabled = r03;
            if (r112.length >= 12) goto L19;
            String[] r04 = r12.split("_");
            if (Utilities.parseInt(r04[11]) != 1) goto L26;
            boolean r3 = true;
        L27:
            this.lessCallData = r3;
        L29:
            if (r112.length < 13) goto L31;
            this.maxVideoBitrate = Utilities.parseInt(r112[12]);
        L35:
            if (r112.length >= 14) goto L37;
            if (r04 != null) goto L44;
            r04 = r12.split("_");
        L44:
            if (Utilities.parseInt(r04[13]) != 1) goto L46;
            r4 = true;
        L46:
            this.preloadStories = r4;
            return;
        L37:
            if (Utilities.parseInt(r112[13]) != 1) goto L39;
            r4 = true;
        L39:
            this.preloadStories = r4;
            return;
        L31:
            if (r04 != null) goto L33;
            r04 = r12.split("_");
        L33:
            this.maxVideoBitrate = Utilities.parseInt(r04[12]);
            goto L35
        L26:
            r3 = false;
            goto L27
        L19:
            if (Utilities.parseInt(r112[11]) != 1) goto L21;
            boolean r05 = true;
        L22:
            this.lessCallData = r05;
            r04 = null;
            goto L29
        L21:
            r05 = false;
            goto L22
        L15:
            r03 = false;
            goto L16
        L11:
            r02 = false;
            goto L12
        L7:
            r0 = false;
            goto L8
        }

        public Preset(int[] r4, long r5, long r7, long r9, boolean r11, boolean r12, boolean r13, boolean r14, int r15, boolean r16) {
            int[] r1 = new int[4];
            this.mask = r1;
            this.sizes = new long[4];
            System.arraycopy(r4, 0, r1, 0, Math.max(r4.length, r1.length));
            long[] r42 = this.sizes;
            r42[0] = r5;
            r42[1] = r7;
            r42[2] = r9;
            r42[3] = 524288;
            this.preloadVideo = r11;
            this.preloadMusic = r12;
            this.lessCallData = r14;
            this.maxVideoBitrate = r15;
            this.enabled = r13;
            this.preloadStories = r16;
        }

        public boolean equals(Preset r11) {
            int[] r0 = this.mask;
            int r2 = r0[0];
            int[] r3 = r11.mask;
            if (r2 == r3[0]) goto L5;
        L27:
            return false;
        L5:
            if (r0[1] != r3[1]) goto L27;
            if (r0[2] != r3[2]) goto L27;
            if (r0[3] != r3[3]) goto L27;
            long[] r02 = this.sizes;
            long r6 = r02[0];
            long[] r32 = r11.sizes;
            if (r6 != r32[0]) goto L27;
            if (r02[1] != r32[1]) goto L27;
            if (r02[2] != r32[2]) goto L27;
            if (r02[3] != r32[3]) goto L27;
            if (this.preloadVideo != r11.preloadVideo) goto L27;
            if (this.preloadMusic != r11.preloadMusic) goto L27;
            if (this.maxVideoBitrate != r11.maxVideoBitrate) goto L27;
            if (this.preloadStories != r11.preloadStories) goto L27;
            return true;
        }

        public boolean isEnabled() {
            int[] r4 = this.mask;
            int r0 = r4.length;
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L9;
            if (r4[r2] != 0) goto L6;
            r2 = r2 + 1;
            goto L3
        L6:
            return true;
        L9:
            return false;
        }

        public void set(Preset r5) {
            int[] r0 = r5.mask;
            int[] r1 = this.mask;
            System.arraycopy(r0, 0, r1, 0, r1.length);
            long[] r02 = r5.sizes;
            long[] r12 = this.sizes;
            System.arraycopy(r02, 0, r12, 0, r12.length);
            this.preloadVideo = r5.preloadVideo;
            this.preloadMusic = r5.preloadMusic;
            this.lessCallData = r5.lessCallData;
            this.maxVideoBitrate = r5.maxVideoBitrate;
            this.preloadStories = r5.preloadStories;
        }

        public void set(TLRPC.TL_autoDownloadSettings r8) {
            this.preloadMusic = r8.audio_preload_next;
            this.preloadVideo = r8.video_preload_large;
            this.lessCallData = r8.phonecalls_less_data;
            this.maxVideoBitrate = r8.video_upload_maxbitrate;
            int r3 = 0;
            this.sizes[0] = Math.max(512000, r8.photo_size_max);
            this.sizes[1] = Math.max(512000, r8.video_size_max);
            this.sizes[2] = Math.max(512000, r8.file_size_max);
        L3:
            int[] r0 = this.mask;
            if (r3 >= r0.length) goto L24;
            if (r8.photo_size_max != 0) goto L8;
        L10:
            r0[r3] = r0[r3] & (-2);
        L12:
            if (r8.video_size_max != 0) goto L14;
        L16:
            r0[r3] = r0[r3] & (-5);
        L18:
            if (r8.file_size_max != 0) goto L20;
        L22:
            r0[r3] = r0[r3] & (-9);
        L23:
            r3 = r3 + 1;
            goto L3
        L20:
            if (r8.disabled == true) goto L22;
            r0[r3] = r0[r3] | 8;
            goto L23
        L14:
            if (r8.disabled == true) goto L16;
            r0[r3] = r0[r3] | 4;
            goto L18
        L8:
            if (r8.disabled == true) goto L10;
            r0[r3] = r0[r3] | 1;
            goto L12
        L24:
            this.preloadStories = true;
        }

        public String toString() {
            return this.mask[0] + "_" + this.mask[1] + "_" + this.mask[2] + "_" + this.mask[3] + "_" + this.sizes[0] + "_" + this.sizes[1] + "_" + this.sizes[2] + "_" + this.sizes[3] + "_" + (this.preloadVideo ? 1 : 0) + "_" + (this.preloadMusic ? 1 : 0) + "_" + (this.enabled ? 1 : 0) + "_" + (this.lessCallData ? 1 : 0) + "_" + this.maxVideoBitrate + "_" + (this.preloadStories ? 1 : 0);
        }
    }

    /* renamed from: $r8$lambda$10jjL0qkvQ1YrnSSnEfw9-_JRF0, reason: not valid java name */
    public static /* synthetic */ void m3985$r8$lambda$10jjL0qkvQ1YrnSSnEfw9_JRF0(DownloadController r0, TLRPC.Document r1, MessageObject r2) {
        r0.lambda$startDownloadFile$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$2z_UqcSoORmmKbTIqwXZQ3uAg4c(DownloadController r0, MessageObject r1) {
        r0.lambda$onDownloadFail$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$3GGbg2S_wPdBs_f6jSuHiQG_loo(DownloadController r0, TLRPC.Document r1, int r2) {
        r0.lambda$onDownloadFail$8(r1, r2);
    }

    /* renamed from: $r8$lambda$5oaMnZd-VvLCFz7CwjXFClR40Ao, reason: not valid java name */
    public static /* synthetic */ void m3986$r8$lambda$5oaMnZdVvLCFz7CwjXFClR40Ao(DownloadController r0) {
        r0.lambda$clearRecentDownloadedFiles$13();
    }

    public static /* synthetic */ void $r8$lambda$6g00gBdl5NJiczAax9SuTVva_bs(DownloadController r0, MessageObject r1) {
        r0.lambda$startDownloadFile$4(r1);
    }

    /* renamed from: $r8$lambda$7rr2OU-8puZU8ZjTT2L07ONsxFQ, reason: not valid java name */
    public static /* synthetic */ void m3987$r8$lambda$7rr2OU8puZU8ZjTT2L07ONsxFQ(DownloadController r0, TLObject r1) {
        r0.lambda$loadAutoDownloadConfig$1(r1);
    }

    /* renamed from: $r8$lambda$IYQRqApkUTBKO-2jrjEGRVgIOis, reason: not valid java name */
    public static /* synthetic */ void m3988$r8$lambda$IYQRqApkUTBKO2jrjEGRVgIOis(DownloadController r0, TLRPC.Document r1, MessageObject r2) {
        r0.lambda$onDownloadComplete$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$YaS5NgYTd0WOnRJXgz8me5CLR78(DownloadController r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$a932j3A0ioLRfL9x8R0cRYMLIoM(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: $r8$lambda$hhopf-xlAxpasHFYwSWDmWCGDgQ, reason: not valid java name */
    public static /* synthetic */ void m3989$r8$lambda$hhopfxlAxpasHFYwSWDmWCGDgQ(DownloadController r0) {
        r0.lambda$new$10();
    }

    public static /* synthetic */ void $r8$lambda$iwhqayt38eFajhG5AnnOanYOjP0(DownloadController r0) {
        r0.lambda$loadDownloadingFiles$12();
    }

    public static /* synthetic */ void $r8$lambda$jpYzvKXM1it8VnJLO7qce1eCXAA(DownloadController r0, MessageObject r1) {
        r0.lambda$onDownloadComplete$6(r1);
    }

    /* renamed from: $r8$lambda$nJlUZi_J-RPGbE_9Mg_JOjPdpJI, reason: not valid java name */
    public static /* synthetic */ void m3990$r8$lambda$nJlUZi_JRPGbE_9Mg_JOjPdpJI(DownloadController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadAutoDownloadConfig$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$suCahNnWu0gjVh6si48muXqVTVs(DownloadController r0, ArrayList r1) {
        r0.lambda$deleteRecentFiles$14(r1);
    }

    /* renamed from: $r8$lambda$u8Z-IqQi1XAa9kja9iiY6Foq-I0, reason: not valid java name */
    public static /* synthetic */ void m3991$r8$lambda$u8ZIqQi1XAa9kja9iiY6FoqI0(DownloadController r0, ArrayList r1, ArrayList r2) {
        r0.lambda$loadDownloadingFiles$11(r1, r2);
    }

    static {
        Instance = new DownloadController[16];
    }

    public DownloadController(int r35) {
        super(r35);
        this.lastCheckMask = 0;
        this.photoDownloadQueue = new ArrayList();
        this.audioDownloadQueue = new ArrayList();
        this.documentDownloadQueue = new ArrayList();
        this.videoDownloadQueue = new ArrayList();
        this.downloadQueueKeys = new HashMap();
        this.downloadQueuePairs = new HashMap();
        this.loadingFileObservers = new HashMap();
        this.loadingFileMessagesObservers = new HashMap();
        this.observersByTag = new SparseArray();
        this.listenerInProgress = false;
        this.addLaterArray = new HashMap();
        this.deleteLaterArray = new ArrayList();
        this.lastTag = 0;
        this.typingTimes = new LongSparseArray();
        this.downloadingFiles = new ArrayList();
        this.recentDownloadingFiles = new ArrayList();
        this.unviewedDownloads = new SparseArray();
        this.clearUnviewedDownloadsRunnale = new DownloadController$$ExternalSyntheticLambda2(this);
        SharedPreferences r2 = MessagesController.getMainSettings(this.currentAccount);
        Preset r3 = new Preset(r2.getString("preset0", "1_1_1_1_1048576_512000_512000_524288_0_0_1_1_50_0"), "1_1_1_1_1048576_512000_512000_524288_0_0_1_1_50_0");
        this.lowPreset = r3;
        r3.preloadStories = false;
        this.mediumPreset = new Preset(r2.getString("preset1", "13_13_13_13_1048576_10485760_1048576_524288_1_1_1_0_100_1"), "13_13_13_13_1048576_10485760_1048576_524288_1_1_1_0_100_1");
        this.highPreset = new Preset(r2.getString("preset2", "13_13_13_13_1048576_15728640_3145728_524288_1_1_1_0_100_1"), "13_13_13_13_1048576_15728640_3145728_524288_1_1_1_0_100_1");
        boolean r4 = r2.contains("newConfig");
        int r14 = 4;
        if (r4 == false) goto L5;
    L30:
        this.mobilePreset = new Preset(r2.getString("mobilePreset", "13_13_13_13_1048576_10485760_1048576_524288_1_1_1_0_100_1"), "13_13_13_13_1048576_10485760_1048576_524288_1_1_1_0_100_1");
        this.wifiPreset = new Preset(r2.getString("wifiPreset", "13_13_13_13_1048576_15728640_3145728_524288_1_1_1_0_100_1"), "13_13_13_13_1048576_15728640_3145728_524288_1_1_1_0_100_1");
        this.roamingPreset = new Preset(r2.getString("roamingPreset", "1_1_1_1_1048576_512000_512000_524288_0_0_1_1_50_0"), "1_1_1_1_1048576_512000_512000_524288_0_0_1_1_50_0");
        this.currentMobilePreset = r2.getInt("currentMobilePreset", 3);
        this.currentWifiPreset = r2.getInt("currentWifiPreset", 3);
        this.currentRoamingPreset = r2.getInt("currentRoamingPreset", 3);
        if (r4 == true) goto L33;
        r2.edit().putBoolean("newConfig", true).apply();
    L33:
        AndroidUtilities.runOnUIThread(new DownloadController$$ExternalSyntheticLambda3(this));
        AnonymousClass1 r1 = new AnonymousClass1(this);
        IntentFilter r22 = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        if (Build.VERSION.SDK_INT < 33) goto L36;
        ApplicationLoader.applicationContext.registerReceiver(r1, r22, 4);
    L38:
        if (getUserConfig().isClientActivated() == false) goto L44;
        checkAutodownloadSettings();
        return;
    L44:
        return;
    L36:
        ApplicationLoader.applicationContext.registerReceiver(r1, r22);
        goto L38
    L5:
        if (getUserConfig().isClientActivated() == false) goto L30;
        int[] r42 = new int[4];
        int[] r5 = new int[4];
        int[] r6 = new int[4];
        long[] r12 = new long[7];
        long[] r15 = new long[7];
        long[] r7 = new long[7];
        int r13 = 0;
    L8:
        if (r13 >= r14) goto L29;
        int r17 = r13;
        StringBuilder r142 = new StringBuilder("mobileDataDownloadMask");
        Object r16 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r17 != 0) goto L12;
        Object r19 = _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        r142.append(r16);
        String r18 = r142.toString();
        if (r17 != 0) goto L16;
    L19:
        r42[r17] = r2.getInt(r18, 13);
        StringBuilder r110 = new StringBuilder("wifiDownloadMask");
        if (r17 != 0) goto L22;
        Object r143 = r19;
    L23:
        r110.append(r143);
        r5[r17] = r2.getInt(r110.toString(), 13);
        StringBuilder r111 = new StringBuilder("roamingDownloadMask");
        if (r17 != 0) goto L26;
        Object r144 = r19;
    L27:
        r111.append(r144);
        r6[r17] = r2.getInt(r111.toString(), 1);
    L28:
        r13 = r17 + 1;
        r14 = 4;
        goto L8
    L26:
        r144 = Integer.valueOf(r17);
        goto L27
    L22:
        r143 = Integer.valueOf(r17);
        goto L23
    L16:
        if (r2.contains(r18) == true) goto L19;
        r42[r17] = r42[0];
        r5[r17] = r5[0];
        r6[r17] = r6[0];
        goto L28
    L12:
        Integer r182 = Integer.valueOf(r17);
        r19 = _UrlKt.FRAGMENT_ENCODE_SET;
        r16 = r182;
        goto L13
    L29:
        r12[2] = r2.getLong("mobileMaxDownloadSize2", this.mediumPreset.sizes[1]);
        r12[3] = r2.getLong("mobileMaxDownloadSize3", this.mediumPreset.sizes[2]);
        r15[2] = r2.getLong("wifiMaxDownloadSize2", this.highPreset.sizes[1]);
        r15[3] = r2.getLong("wifiMaxDownloadSize3", this.highPreset.sizes[2]);
        r7[2] = r2.getLong("roamingMaxDownloadSize2", this.lowPreset.sizes[1]);
        r7[3] = r2.getLong("roamingMaxDownloadSize3", this.lowPreset.sizes[2]);
        boolean r26 = r2.getBoolean("globalAutodownloadEnabled", true);
        this.mobilePreset = new Preset(r42, this.mediumPreset.sizes[0], r12[2], r12[3], true, true, r26, false, 100, false);
        this.wifiPreset = new Preset(r5, this.highPreset.sizes[0], r15[2], r15[3], true, true, r26, false, 100, true);
        this.roamingPreset = new Preset(r6, this.lowPreset.sizes[0], r7[2], r7[3], false, false, r26, true, 50, true);
        SharedPreferences.Editor r112 = r2.edit();
        r112.putBoolean("newConfig", true);
        r112.putString("mobilePreset", this.mobilePreset.toString());
        r112.putString("wifiPreset", this.wifiPreset.toString());
        r112.putString("roamingPreset", this.roamingPreset.toString());
        this.currentMobilePreset = 3;
        r112.putInt("currentMobilePreset", 3);
        this.currentWifiPreset = 3;
        r112.putInt("currentWifiPreset", 3);
        this.currentRoamingPreset = 3;
        r112.putInt("currentRoamingPreset", 3);
        r112.apply();
        goto L33
    }

    private int canDownloadMediaInternal(MessageObject r15) {
        if (r15 == null) goto L116;
        TLRPC.Message r1 = r15.messageOwner;
        if (r1 == null) goto L116;
        if ((r1.media instanceof TLRPC.TL_messageMediaStory) == true) goto L10;
        boolean r2 = MessageObject.isVideoMessage(r1);
        if (r2 == false) goto L16;
    L37:
        int r5 = 4;
    L38:
        TLRPC.Peer r6 = r1.peer_id;
        if (r6 != null) goto L41;
    L45:
        char r62 = 1;
    L68:
        int r9 = ApplicationLoader.getAutodownloadNetworkType();
        if (r9 == 1) goto L71;
        if (r9 != 2) goto L80;
        if (this.roamingPreset.enabled == true) goto L78;
        return 0;
    L78:
        Preset r14 = getCurrentRoamingPreset();
    L83:
        int r63 = r14.mask[r62];
        long[] r92 = r14.sizes;
        if (r5 != 2) goto L86;
        long r93 = Math.max(524288, r92[typeToIndex(r5)]);
    L87:
        VideoPlayer.VideoUri r11 = r15.highestQuality;
        if (r11 == null) goto L90;
        long r112 = r11.document.size;
    L94:
        if (r2 == true) goto L96;
    L105:
        if (r5 != 1) goto L107;
    L110:
        if (r5 != 2) goto L112;
    L115:
        return 1;
    L112:
        if ((r63 & r5) != 0) goto L115;
    L114:
        return 0;
    L107:
        if (r112 == 0) goto L114;
        if (r112 > r93) goto L114;
    L96:
        if (r14.preloadVideo == false) goto L105;
        if (r112 <= r93) goto L105;
        if (r93 <= 2097152) goto L105;
        if ((r63 & r5) == 0) goto L104;
        return 2;
    L104:
        return 0;
    L90:
        VideoPlayer.VideoUri r152 = r15.thumbQuality;
        if (r152 == null) goto L93;
        r112 = r152.document.size;
        goto L94
    L93:
        r112 = MessageObject.getMessageSize(r1);
        goto L94
    L86:
        r93 = r92[typeToIndex(r5)];
        goto L87
    L80:
        if (this.mobilePreset.enabled == true) goto L82;
        return 0;
    L82:
        r14 = getCurrentMobilePreset();
        goto L83
    L71:
        if (this.wifiPreset.enabled == true) goto L73;
        return 0;
    L73:
        r14 = getCurrentWiFiPreset();
        goto L83
    L41:
        if (r6.user_id == 0) goto L47;
        if (getContactsController().contactsDict.containsKey(Long.valueOf(r6.user_id)) == false) goto L45;
    L44:
        r62 = 0;
        goto L68
    L47:
        if (r6.chat_id == 0) goto L55;
        if ((r1.from_id instanceof TLRPC.TL_peerUser) == true) goto L51;
    L53:
        r62 = 2;
        goto L68
    L51:
        if (getContactsController().contactsDict.containsKey(Long.valueOf(r1.from_id.user_id)) == false) goto L53;
    L55:
        if (r6.channel_id == 0) goto L57;
        TLRPC.Chat r64 = getMessagesController().getChat(Long.valueOf(r1.peer_id.channel_id));
    L59:
        if (ChatObject.isChannel(r64) == true) goto L61;
    L67:
        r62 = 3;
        goto L68
    L61:
        if (r64.megagroup == false) goto L67;
        if ((r1.from_id instanceof TLRPC.TL_peerUser) == false) goto L53;
        if (getContactsController().contactsDict.containsKey(Long.valueOf(r1.from_id.user_id)) == false) goto L53;
    L57:
        r64 = null;
        goto L59
    L16:
        if (MessageObject.isGifMessage(r1) == true) goto L37;
        if (MessageObject.isRoundVideoMessage(r1) == true) goto L37;
        if (MessageObject.isGameMessage(r1) == true) goto L37;
        if (MessageObject.isVoiceMessage(r1) == false) goto L26;
        r5 = 2;
        goto L38
    L26:
        if (MessageObject.isPhoto(r1) == false) goto L28;
    L36:
        r5 = 1;
        goto L38
    L28:
        if (MessageObject.isStickerMessage(r1) == true) goto L36;
        if (MessageObject.isAnimatedStickerMessage(r1) == true) goto L36;
        if (MessageObject.getDocument(r1) == null) goto L35;
        r5 = 8;
        goto L38
    L35:
        return 0;
    L10:
        if (canPreloadStories() == false) goto L12;
        return 2;
    L12:
        return 0;
    L116:
        return 0;
    }

    private int canDownloadMediaInternal(MessageObject r13, long r14) {
        if (r13 == null) goto L109;
        TLRPC.Message r132 = r13.messageOwner;
        if (r132 == null) goto L109;
        if ((r132.media instanceof TLRPC.TL_messageMediaStory) == true) goto L10;
        boolean r1 = MessageObject.isVideoMessage(r132);
        if (r1 == false) goto L16;
    L37:
        int r4 = 4;
    L38:
        TLRPC.Peer r5 = r132.peer_id;
        if (r5 != null) goto L41;
    L45:
        char r133 = 1;
    L68:
        int r52 = ApplicationLoader.getAutodownloadNetworkType();
        if (r52 == 1) goto L71;
        if (r52 != 2) goto L80;
        if (this.roamingPreset.enabled == true) goto L78;
        return 0;
    L78:
        Preset r12 = getCurrentRoamingPreset();
    L83:
        int r134 = r12.mask[r133];
        long[] r53 = r12.sizes;
        if (r4 != 2) goto L86;
        long r8 = Math.max(524288, r53[typeToIndex(r4)]);
    L87:
        if (r1 == true) goto L89;
    L98:
        if (r4 != 1) goto L100;
    L103:
        if (r4 != 2) goto L105;
    L108:
        return 1;
    L105:
        if ((r134 & r4) != 0) goto L108;
    L107:
        return 0;
    L100:
        if (r14 == 0) goto L107;
        if (r14 > r8) goto L107;
    L89:
        if (r12.preloadVideo == false) goto L98;
        if (r14 <= r8) goto L98;
        if (r8 <= 2097152) goto L98;
        if ((r134 & r4) == 0) goto L97;
        return 2;
    L97:
        return 0;
    L86:
        r8 = r53[typeToIndex(r4)];
        goto L87
    L80:
        if (this.mobilePreset.enabled == true) goto L82;
        return 0;
    L82:
        r12 = getCurrentMobilePreset();
        goto L83
    L71:
        if (this.wifiPreset.enabled == true) goto L73;
        return 0;
    L73:
        r12 = getCurrentWiFiPreset();
        goto L83
    L41:
        if (r5.user_id == 0) goto L47;
        if (getContactsController().contactsDict.containsKey(Long.valueOf(r5.user_id)) == false) goto L45;
    L44:
        r133 = 0;
        goto L68
    L47:
        if (r5.chat_id == 0) goto L55;
        if ((r132.from_id instanceof TLRPC.TL_peerUser) == true) goto L51;
    L53:
        r133 = 2;
        goto L68
    L51:
        if (getContactsController().contactsDict.containsKey(Long.valueOf(r132.from_id.user_id)) == false) goto L53;
    L55:
        if (r5.channel_id == 0) goto L57;
        TLRPC.Chat r54 = getMessagesController().getChat(Long.valueOf(r132.peer_id.channel_id));
    L59:
        if (ChatObject.isChannel(r54) == true) goto L61;
    L67:
        r133 = 3;
        goto L68
    L61:
        if (r54.megagroup == false) goto L67;
        if ((r132.from_id instanceof TLRPC.TL_peerUser) == false) goto L53;
        if (getContactsController().contactsDict.containsKey(Long.valueOf(r132.from_id.user_id)) == false) goto L53;
    L57:
        r54 = null;
        goto L59
    L16:
        if (MessageObject.isGifMessage(r132) == true) goto L37;
        if (MessageObject.isRoundVideoMessage(r132) == true) goto L37;
        if (MessageObject.isGameMessage(r132) == true) goto L37;
        if (MessageObject.isVoiceMessage(r132) == false) goto L26;
        r4 = 2;
        goto L38
    L26:
        if (MessageObject.isPhoto(r132) == false) goto L28;
    L36:
        r4 = 1;
        goto L38
    L28:
        if (MessageObject.isStickerMessage(r132) == true) goto L36;
        if (MessageObject.isAnimatedStickerMessage(r132) == true) goto L36;
        if (MessageObject.getDocument(r132) == null) goto L35;
        r4 = 8;
        goto L38
    L35:
        return 0;
    L10:
        if (canPreloadStories() == false) goto L12;
        return 2;
    L12:
        return 0;
    L109:
        return 0;
    }

    private void checkDownloadFinished(String r6, int r7) {
        DownloadObject r0 = this.downloadQueueKeys.get(r6);
        if (r0 == null) goto L31;
        this.downloadQueueKeys.remove(r6);
        this.downloadQueuePairs.remove(new Pair(Long.valueOf(r0.id), Integer.valueOf(r0.type)));
        if (r7 == 0) goto L7;
        if (r7 == 2) goto L7;
    L8:
        int r72 = r0.type;
        if (r72 != 1) goto L14;
        this.photoDownloadQueue.remove(r0);
        if (this.photoDownloadQueue.isEmpty() == false) goto L32;
        newDownloadObjectsAvailable(1);
        return;
    L32:
        return;
    L14:
        if (r72 != 2) goto L20;
        this.audioDownloadQueue.remove(r0);
        if (this.audioDownloadQueue.isEmpty() == false) goto L33;
        newDownloadObjectsAvailable(2);
        return;
    L33:
        return;
    L20:
        if (r72 != 4) goto L26;
        this.videoDownloadQueue.remove(r0);
        if (this.videoDownloadQueue.isEmpty() == false) goto L34;
        newDownloadObjectsAvailable(4);
        return;
    L34:
        return;
    L26:
        if (r72 != 8) goto L35;
        this.documentDownloadQueue.remove(r0);
        if (this.documentDownloadQueue.isEmpty() == false) goto L36;
        newDownloadObjectsAvailable(8);
        return;
    L36:
        return;
    L35:
        return;
    L7:
        getMessagesStorage().removeFromDownloadQueue(r0.id, r0.type, false);
        goto L8
    }

    public static DownloadController getInstance(int r3) {
        DownloadController[] r0 = Instance;
        DownloadController r1 = r0[r3];
        if (r1 == null) goto L5;
        return r1;
    L5:
        monitor-enter(DownloadController.class);
        DownloadController r12 = r0[r3];     // Catch: Throwable -> L9
        if (r12 != null) goto L11;
        r12 = new DownloadController(r3);     // Catch: Throwable -> L9
        r0[r3] = r12;     // Catch: Throwable -> L9
    L11:
        monitor-exit(DownloadController.class);     // Catch: Throwable -> L9
        return r12;
    L9:
        th = move-exception;
        throw th;
    }

    public static float getProgress(long[] r4) {
        if (r4 != null) goto L4;
        return 0.0f;
    L4:
        if (r4.length < 2) goto L12;
        long r0 = r4[1];
        if (r0 != 0) goto L9;
        return 0.0f;
    L9:
        return Math.min(1.0f, r4[0] / r0);
    L12:
        return 0.0f;
    }

    private /* synthetic */ void lambda$clearRecentDownloadedFiles$13() {
        getMessagesStorage().getDatabase().executeFast("DELETE FROM downloading_documents WHERE state = 1").stepThis().dispose();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$deleteRecentFiles$14(ArrayList r6) {
        SQLitePreparedStatement r0 = getMessagesStorage().getDatabase().executeFast("DELETE FROM downloading_documents WHERE hash = ? AND id = ?");     // Catch: Exception -> L13
        int r1 = 0;
    L4:
        if (r1 >= r6.size()) goto L11;
        r0.requery();     // Catch: Exception -> L13
        r0.bindInteger(1, ((MessageObject) r6.get(r1)).getDocument().dc_id);     // Catch: Exception -> L13
        r0.bindLong(2, ((MessageObject) r6.get(r1)).getDocument().id);     // Catch: Exception -> L13
        r0.step();     // Catch: Exception -> L13
        FileLoader.getInstance(this.currentAccount).getPathToMessage(((MessageObject) r6.get(r1)).messageOwner).delete();     // Catch: Exception -> L8
    L10:
        r1 = r1 + 1;     // Catch: Exception -> L13
    L8:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L13
        goto L10
    L11:
        r0.dispose();     // Catch: Exception -> L13
        return;
    L13:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$loadAutoDownloadConfig$1(TLObject r5) {
        this.loadingAutoDownloadConfig = false;
        getUserConfig().autoDownloadConfigLoadTime = System.currentTimeMillis();
        getUserConfig().saveConfig(false);
        if (r5 == null) goto L30;
        TL_account.autoDownloadSettings r52 = (TL_account.autoDownloadSettings) r5;
        this.lowPreset.set(r52.low);
        this.lowPreset.preloadStories = false;
        this.mediumPreset.set(r52.medium);
        this.highPreset.set(r52.high);
        int r1 = 0;
    L6:
        if (r1 >= 3) goto L23;
        if (r1 != 0) goto L10;
        Preset r2 = this.mobilePreset;
    L14:
        if (r2.equals(this.lowPreset) == false) goto L17;
        r2.set(r52.low);
        r2.preloadStories = false;
    L22:
        r1 = r1 + 1;
        goto L6
    L17:
        if (r2.equals(this.mediumPreset) == false) goto L20;
        r2.set(r52.medium);
        goto L22
    L20:
        if (r2.equals(this.highPreset) == false) goto L22;
        r2.set(r52.high);
        goto L22
    L10:
        if (r1 != 1) goto L12;
        r2 = this.wifiPreset;
        goto L14
    L12:
        r2 = this.roamingPreset;
        goto L14
    L23:
        SharedPreferences.Editor r53 = MessagesController.getMainSettings(this.currentAccount).edit();
        r53.putString("mobilePreset", this.mobilePreset.toString());
        r53.putString("wifiPreset", this.wifiPreset.toString());
        r53.putString("roamingPreset", this.roamingPreset.toString());
        r53.putString("preset0", this.lowPreset.toString());
        r53.putString("preset1", this.mediumPreset.toString());
        r53.putString("preset2", this.highPreset.toString());
        r53.apply();
        checkAutodownloadSettings();
        return;
    }

    private /* synthetic */ void lambda$loadAutoDownloadConfig$2(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new DownloadController$$ExternalSyntheticLambda10(this, r1));
    }

    private /* synthetic */ void lambda$loadDownloadingFiles$11(ArrayList r2, ArrayList r3) {
        this.downloadingFiles.clear();
        this.downloadingFiles.addAll(r2);
        this.recentDownloadingFiles.clear();
        this.recentDownloadingFiles.addAll(r3);
    }

    private /* synthetic */ void lambda$loadDownloadingFiles$12() {
        ArrayList<MessageObject> r0 = new ArrayList();
        ArrayList<MessageObject> r1 = new ArrayList();
        ArrayList r2 = new ArrayList();
        getMessagesStorage().getDatabase().executeFast("DELETE FROM downloading_documents WHERE state = 1 AND rowid NOT IN (SELECT rowid FROM downloading_documents WHERE state = 1 ORDER BY date DESC LIMIT 100)").stepThis().dispose();     // Catch: Exception -> L13
        SQLiteCursor r3 = getMessagesStorage().getDatabase().queryFinalized("SELECT data, state FROM downloading_documents ORDER BY date DESC", new Object[0]);     // Catch: Exception -> L13
    L5:
        if (r3.next() == false) goto L17;
        NativeByteBuffer r4 = r3.byteBufferValue(0);     // Catch: Exception -> L13
        int r6 = r3.intValue(1);     // Catch: Exception -> L13
        if (r4 == null) goto L5;
        TLRPC.Message r7 = TLRPC.Message.TLdeserialize(r4, r4.readInt32(false), false);     // Catch: Exception -> L13
        if (r7 == null) goto L16;
        r7.readAttachPath(r4, UserConfig.getInstance(this.currentAccount).clientUserId);     // Catch: Exception -> L13
        MessageObject r8 = new MessageObject(this.currentAccount, r7, false, false);     // Catch: Exception -> L13
        r2.add(r8);     // Catch: Exception -> L13
        if (r6 != 0) goto L15;
        r0.add(r8);     // Catch: Exception -> L13
        goto L16
    L15:
        r1.add(r8);     // Catch: Exception -> L13
    L16:
        r4.reuse();     // Catch: Exception -> L13
        goto L5
    L17:
        r3.dispose();     // Catch: Exception -> L13
    L20:
        getFileLoader().checkMediaExistance(r0);
        getFileLoader().checkMediaExistance(r1);
        AndroidUtilities.runOnUIThread(new DownloadController$$ExternalSyntheticLambda9(this, r0, r1));
        return;
    L13:
        e = move-exception;
        FileLog.e(e);
        goto L20
    }

    private /* synthetic */ void lambda$new$0() {
        getNotificationCenter().addObserver(this, NotificationCenter.fileLoadFailed);
        getNotificationCenter().addObserver(this, NotificationCenter.fileLoaded);
        getNotificationCenter().addObserver(this, NotificationCenter.fileLoadProgressChanged);
        getNotificationCenter().addObserver(this, NotificationCenter.fileUploadProgressChanged);
        getNotificationCenter().addObserver(this, NotificationCenter.httpFileDidLoad);
        getNotificationCenter().addObserver(this, NotificationCenter.httpFileDidFailedLoad);
        loadAutoDownloadConfig(false);
    }

    private /* synthetic */ void lambda$new$10() {
        clearUnviewedDownloads();
        getNotificationCenter().postNotificationName(NotificationCenter.onDownloadingFilesChanged, new Object[0]);
    }

    private /* synthetic */ void lambda$onDownloadComplete$6(MessageObject r7) {
        String r72 = String.format(Locale.ENGLISH, "UPDATE downloading_documents SET state = 1, date = %d WHERE hash = %d AND id = %d", new Object[]{Long.valueOf(System.currentTimeMillis()), Integer.valueOf(r7.getDocument().dc_id), Long.valueOf(r7.getDocument().id)});     // Catch: Exception -> L4
        getMessagesStorage().getDatabase().executeFast(r72).stepThis().dispose();     // Catch: Exception -> L4
        getMessagesStorage().getDatabase().executeFast("DELETE FROM downloading_documents WHERE state = 1 AND rowid NOT IN (SELECT rowid FROM downloading_documents WHERE state = 1 ORDER BY date DESC LIMIT 100)").stepThis().dispose();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$onDownloadComplete$7(TLRPC.Document r7, MessageObject r8) {
        int r1 = 0;
    L4:
        if (r1 >= this.downloadingFiles.size()) goto L22;
        if (this.downloadingFiles.get(r1).getDocument() == null) goto L21;
        if (this.downloadingFiles.get(r1).getDocument().id != r7.id) goto L21;
        this.downloadingFiles.remove(r1);
        int r12 = 0;
    L10:
        int r2 = this.recentDownloadingFiles.size();
        ArrayList<MessageObject> r3 = this.recentDownloadingFiles;
        if (r12 >= r2) goto L18;
        if (r3.get(r12).getDocument() == null) goto L17;
        if (this.recentDownloadingFiles.get(r12).getDocument().id != r7.id) goto L17;
    L19:
        getNotificationCenter().postNotificationName(NotificationCenter.onDownloadingFilesChanged, new Object[0]);
        getMessagesStorage().getStorageQueue().postRunnable(new DownloadController$$ExternalSyntheticLambda6(this, r8));
        return;
    L17:
        r12 = r12 + 1;
        goto L10
    L18:
        r3.add(0, r8);
        putToUnviewedDownloads(r8);
    L21:
        r1 = r1 + 1;
        goto L4
    }

    private /* synthetic */ void lambda$onDownloadFail$8(TLRPC.Document r7, int r8) {
        int r1 = 0;
    L4:
        if (r1 >= this.downloadingFiles.size()) goto L23;
        TLRPC.Document r2 = this.downloadingFiles.get(r1).getDocument();
        if (r2 == null) goto L17;
        if (r2.id != r7.id) goto L17;
        this.downloadingFiles.remove(r1);
        getNotificationCenter().postNotificationName(NotificationCenter.onDownloadingFilesChanged, new Object[0]);
        if (r8 != 0) goto L14;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.showBulletin, new Object[]{1, LocaleController.formatString("MessageNotFound", R.string.MessageNotFound, new Object[0])});
        return;
    L14:
        if (r8 != (-1)) goto L18;
        LaunchActivity.checkFreeDiscSpaceStatic(2);
        return;
    L18:
        return;
    L17:
        r1 = r1 + 1;
        goto L4
    }

    private /* synthetic */ void lambda$onDownloadFail$9(MessageObject r3) {
        SQLitePreparedStatement r2 = getMessagesStorage().getDatabase().executeFast("DELETE FROM downloading_documents WHERE hash = ? AND id = ?");     // Catch: Exception -> L4
        r2.bindInteger(1, r3.getDocument().dc_id);     // Catch: Exception -> L4
        r2.bindLong(2, r3.getDocument().id);     // Catch: Exception -> L4
        r2.step();     // Catch: Exception -> L4
        r2.dispose();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$startDownloadFile$4(MessageObject r4) {
        NativeByteBuffer r0 = new NativeByteBuffer(r4.messageOwner.getObjectSize());     // Catch: Exception -> L4
        r4.messageOwner.serializeToStream(r0);     // Catch: Exception -> L4
        SQLitePreparedStatement r3 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO downloading_documents VALUES(?, ?, ?, ?, ?)");     // Catch: Exception -> L4
        r3.bindByteBuffer(1, r0);     // Catch: Exception -> L4
        r3.bindInteger(2, r4.getDocument().dc_id);     // Catch: Exception -> L4
        r3.bindLong(3, r4.getDocument().id);     // Catch: Exception -> L4
        r3.bindInteger(4, 0);     // Catch: Exception -> L4
        r3.bindLong(5, System.currentTimeMillis());     // Catch: Exception -> L4
        r3.step();     // Catch: Exception -> L4
        r3.dispose();     // Catch: Exception -> L4
        r0.reuse();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$startDownloadFile$5(TLRPC.Document r9, MessageObject r10) {
        if (r9 != null) goto L4;
        return;
    L4:
        int r1 = 0;
    L5:
        boolean r3 = true;
        if (r1 >= this.recentDownloadingFiles.size()) goto L16;
        MessageObject r2 = this.recentDownloadingFiles.get(r1);
        if (r2 == null) goto L15;
        TLRPC.Document r22 = r2.getDocument();
        if (r22 == null) goto L15;
        if (r22.id != r9.id) goto L15;
        boolean r12 = true;
    L17:
        if (r12 == true) goto L30;
        int r23 = 0;
    L20:
        if (r23 >= this.downloadingFiles.size()) goto L30;
        MessageObject r4 = this.downloadingFiles.get(r23);
        if (r4 == null) goto L29;
        TLRPC.Document r42 = r4.getDocument();
        if (r42 == null) goto L29;
        if (r42.id != r9.id) goto L29;
    L31:
        if (r3 == true) goto L33;
        this.downloadingFiles.add(0, r10);
        getMessagesStorage().getStorageQueue().postRunnable(new DownloadController$$ExternalSyntheticLambda12(this, r10));
    L33:
        getNotificationCenter().postNotificationName(NotificationCenter.onDownloadingFilesChanged, new Object[0]);
        return;
    L29:
        r23 = r23 + 1;
    L30:
        r3 = r12;
    L15:
        r1 = r1 + 1;
        goto L5
    L16:
        r12 = false;
        goto L17
    }

    private void processLaterArrays() {
        Iterator<Map.Entry<String, FileDownloadProgressListener>> r0 = this.addLaterArray.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        Map.Entry<String, FileDownloadProgressListener> r1 = r0.next();
        addLoadingFileObserver(r1.getKey(), r1.getValue());
        goto L4
    L6:
        this.addLaterArray.clear();
        ArrayList<FileDownloadProgressListener> r02 = this.deleteLaterArray;
        int r12 = r02.size();
        int r2 = 0;
    L7:
        if (r2 >= r12) goto L9;
        FileDownloadProgressListener r3 = r02.get(r2);
        r2 = r2 + 1;
        removeLoadingFileObserver(r3);
        goto L7
    L9:
        this.deleteLaterArray.clear();
    }

    private void putToUnviewedDownloads(MessageObject r3) {
        this.unviewedDownloads.put(r3.getId(), r3);
        AndroidUtilities.cancelRunOnUIThread(this.clearUnviewedDownloadsRunnale);
        AndroidUtilities.runOnUIThread(this.clearUnviewedDownloadsRunnale, 60000);
    }

    public static int typeToIndex(int r4) {
        if (r4 != 1) goto L6;
        return 0;
    L6:
        if (r4 != 2) goto L9;
        return 2;
    L9:
        if (r4 != 4) goto L12;
        return 1;
    L12:
        if (r4 != 8) goto L14;
        return 2;
    L14:
        return 0;
    }

    public void addLoadingFileObserver(String r2, FileDownloadProgressListener r3) {
        addLoadingFileObserver(r2, null, r3);
    }

    public void addLoadingFileObserver(String r3, MessageObject r4, FileDownloadProgressListener r5) {
        if (this.listenerInProgress == false) goto L6;
        this.addLaterArray.put(r3, r5);
        return;
    L6:
        removeLoadingFileObserver(r5);
        ArrayList<WeakReference<FileDownloadProgressListener>> r0 = this.loadingFileObservers.get(r3);
        if (r0 != null) goto L9;
        r0 = new ArrayList();
        this.loadingFileObservers.put(r3, r0);
    L9:
        r0.add(new WeakReference(r5));
        if (r4 == null) goto L15;
        ArrayList<WeakReference<MessageObject>> r02 = this.loadingFileMessagesObservers.get(r3);
        if (r02 != null) goto L14;
        r02 = new ArrayList();
        this.loadingFileMessagesObservers.put(r3, r02);
    L14:
        r02.add(new WeakReference(r4));
    L15:
        this.observersByTag.put(r5.getObserverTag(), r3);
    }

    public int canDownloadMedia(TLRPC.Message r14) {
        if (r14 == null) goto L105;
        if ((r14.media instanceof TLRPC.TL_messageMediaStory) == true) goto L105;
        boolean r2 = MessageObject.isVideoMessage(r14);
        if (r2 == false) goto L10;
    L31:
        int r4 = 4;
    L32:
        TLRPC.Peer r5 = r14.peer_id;
        if (r5 != null) goto L35;
    L39:
        char r52 = 1;
    L62:
        int r8 = ApplicationLoader.getAutodownloadNetworkType();
        if (r8 == 1) goto L65;
        if (r8 != 2) goto L74;
        if (this.roamingPreset.enabled == true) goto L72;
        return 0;
    L72:
        Preset r13 = getCurrentRoamingPreset();
    L77:
        int r53 = r13.mask[r52];
        long[] r82 = r13.sizes;
        if (r4 != 2) goto L80;
        long r83 = Math.max(524288, r82[typeToIndex(r4)]);
    L81:
        long r10 = MessageObject.getMessageSize(r14);
        if (r2 == true) goto L84;
    L93:
        if (r4 != 1) goto L95;
    L98:
        if (r4 != 2) goto L100;
    L103:
        return 1;
    L100:
        if ((r53 & r4) != 0) goto L103;
    L102:
        return 0;
    L95:
        if (r10 == 0) goto L102;
        if (r10 > r83) goto L102;
    L84:
        if (r13.preloadVideo == false) goto L93;
        if (r10 <= r83) goto L93;
        if (r83 <= 2097152) goto L93;
        if ((r53 & r4) == 0) goto L92;
        return 2;
    L92:
        return 0;
    L80:
        r83 = r82[typeToIndex(r4)];
        goto L81
    L74:
        if (this.mobilePreset.enabled == true) goto L76;
        return 0;
    L76:
        r13 = getCurrentMobilePreset();
        goto L77
    L65:
        if (this.wifiPreset.enabled == true) goto L67;
        return 0;
    L67:
        r13 = getCurrentWiFiPreset();
        goto L77
    L35:
        if (r5.user_id == 0) goto L41;
        if (getContactsController().contactsDict.containsKey(Long.valueOf(r5.user_id)) == false) goto L39;
    L38:
        r52 = 0;
        goto L62
    L41:
        if (r5.chat_id == 0) goto L49;
        if ((r14.from_id instanceof TLRPC.TL_peerUser) == true) goto L45;
    L47:
        r52 = 2;
        goto L62
    L45:
        if (getContactsController().contactsDict.containsKey(Long.valueOf(r14.from_id.user_id)) == false) goto L47;
    L49:
        if (r5.channel_id == 0) goto L51;
        TLRPC.Chat r54 = getMessagesController().getChat(Long.valueOf(r14.peer_id.channel_id));
    L53:
        if (ChatObject.isChannel(r54) == true) goto L55;
    L61:
        r52 = 3;
        goto L62
    L55:
        if (r54.megagroup == false) goto L61;
        if ((r14.from_id instanceof TLRPC.TL_peerUser) == false) goto L47;
        if (getContactsController().contactsDict.containsKey(Long.valueOf(r14.from_id.user_id)) == false) goto L47;
    L51:
        r54 = null;
        goto L53
    L10:
        if (MessageObject.isGifMessage(r14) == true) goto L31;
        if (MessageObject.isRoundVideoMessage(r14) == true) goto L31;
        if (MessageObject.isGameMessage(r14) == true) goto L31;
        if (MessageObject.isVoiceMessage(r14) == false) goto L20;
        r4 = 2;
        goto L32
    L20:
        if (MessageObject.isPhoto(r14) == false) goto L22;
    L30:
        r4 = 1;
        goto L32
    L22:
        if (MessageObject.isStickerMessage(r14) == true) goto L30;
        if (MessageObject.isAnimatedStickerMessage(r14) == true) goto L30;
        if (MessageObject.getDocument(r14) == null) goto L29;
        r4 = 8;
        goto L32
    L29:
        return 0;
    L105:
        if (canPreloadStories() == false) goto L107;
        return 2;
    L107:
        return 0;
    }

    public int canDownloadMedia(TLRPC.Message r13, TLRPC.MessageMedia r14) {
        if (r13 == null) goto L94;
        if ((r14 instanceof TLRPC.TL_messageMediaStory) == true) goto L94;
        if (MessageObject.isVideoDocument(r14.document) == false) goto L11;
        int r2 = 4;
        boolean r4 = true;
    L20:
        TLRPC.Peer r5 = r13.peer_id;
        if (r5 != null) goto L23;
    L27:
        char r132 = 1;
    L50:
        int r52 = ApplicationLoader.getAutodownloadNetworkType();
        if (r52 == 1) goto L53;
        if (r52 != 2) goto L62;
        if (this.roamingPreset.enabled == true) goto L60;
        return 0;
    L60:
        Preset r12 = getCurrentRoamingPreset();
    L65:
        int r133 = r12.mask[r132];
        long[] r53 = r12.sizes;
        if (r2 != 2) goto L68;
        long r8 = Math.max(524288, r53[typeToIndex(r2)]);
    L69:
        long r10 = MessageObject.getMediaSize(r14);
        if (r4 == true) goto L72;
    L81:
        if (r2 != 1) goto L83;
    L86:
        if (r2 != 2) goto L88;
    L91:
        return 1;
    L88:
        if ((r133 & r2) != 0) goto L91;
    L90:
        return 0;
    L83:
        if (r10 == 0) goto L90;
        if (r10 > r8) goto L90;
    L72:
        if (r12.preloadVideo == false) goto L81;
        if (r10 <= r8) goto L81;
        if (r8 <= 2097152) goto L81;
        if ((r133 & r2) == 0) goto L80;
        return 2;
    L80:
        return 0;
    L68:
        r8 = r53[typeToIndex(r2)];
        goto L69
    L62:
        if (this.mobilePreset.enabled == true) goto L64;
        return 0;
    L64:
        r12 = getCurrentMobilePreset();
        goto L65
    L53:
        if (this.wifiPreset.enabled == true) goto L55;
        return 0;
    L55:
        r12 = getCurrentWiFiPreset();
        goto L65
    L23:
        if (r5.user_id == 0) goto L29;
        if (getContactsController().contactsDict.containsKey(Long.valueOf(r5.user_id)) == false) goto L27;
    L26:
        r132 = 0;
        goto L50
    L29:
        if (r5.chat_id == 0) goto L37;
        if ((r13.from_id instanceof TLRPC.TL_peerUser) == true) goto L33;
    L35:
        r132 = 2;
        goto L50
    L33:
        if (getContactsController().contactsDict.containsKey(Long.valueOf(r13.from_id.user_id)) == false) goto L35;
    L37:
        if (r5.channel_id == 0) goto L39;
        TLRPC.Chat r54 = getMessagesController().getChat(Long.valueOf(r13.peer_id.channel_id));
    L41:
        if (ChatObject.isChannel(r54) == true) goto L43;
    L49:
        r132 = 3;
        goto L50
    L43:
        if (r54.megagroup == false) goto L49;
        if ((r13.from_id instanceof TLRPC.TL_peerUser) == false) goto L35;
        if (getContactsController().contactsDict.containsKey(Long.valueOf(r13.from_id.user_id)) == false) goto L35;
    L39:
        r54 = null;
        goto L41
    L11:
        if (MessageObject.isVoiceDocument(r14.document) == false) goto L15;
        r2 = 2;
    L13:
        r4 = false;
        goto L20
    L15:
        if ((r14 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L18;
        r4 = false;
        r2 = 1;
        goto L20
    L18:
        if (r14.document == null) goto L92;
        r2 = 8;
        goto L13
    L92:
        return 0;
    L94:
        if (canPreloadStories() == false) goto L96;
        return 2;
    L96:
        return 0;
    }

    public boolean canDownloadMedia(int r9, long r10) {
        int r0 = ApplicationLoader.getAutodownloadNetworkType();
        if (r0 == 1) goto L5;
        if (r0 != 2) goto L14;
        if (this.roamingPreset.enabled == true) goto L12;
        return false;
    L12:
        Preset r8 = getCurrentRoamingPreset();
    L17:
        int r02 = r8.mask[1];
        long r4 = r8.sizes[typeToIndex(r9)];
        if (r9 != 1) goto L20;
    L23:
        if (r9 != 2) goto L25;
    L28:
        return true;
    L25:
        if ((r02 & r9) != 0) goto L28;
    L27:
        return false;
    L20:
        if (r10 == 0) goto L27;
        if (r10 > r4) goto L27;
    L14:
        if (this.mobilePreset.enabled == true) goto L16;
        return false;
    L16:
        r8 = getCurrentMobilePreset();
        goto L17
    L5:
        if (this.wifiPreset.enabled == true) goto L7;
        return false;
    L7:
        r8 = getCurrentWiFiPreset();
        goto L17
    }

    public boolean canDownloadMedia(MessageObject r5) {
        if (r5.type != 23) goto L19;
        if (SharedConfig.isAutoplayVideo() == true) goto L7;
        return false;
    L7:
        TL_stories.StoryItem r4 = ((TLRPC.TL_messageMediaStory) MessageObject.getMedia(r5)).storyItem;
        if (r4 == null) goto L17;
        TLRPC.MessageMedia r52 = r4.media;
        if (r52 == null) goto L17;
        if (r52.document == null) goto L17;
        if (r4.isPublic == false) goto L17;
        return true;
    L17:
        return false;
    L19:
        if (r5.sponsoredMedia == null) goto L22;
        return true;
    L22:
        if (r5.isHiddenSensitive() == false) goto L25;
        return false;
    L25:
        if (canDownloadMediaInternal(r5) != 1) goto L27;
        return true;
    L27:
        return false;
    }

    public int canDownloadMediaType(MessageObject r5) {
        if (r5.type != 23) goto L19;
        if (SharedConfig.isAutoplayVideo() == true) goto L7;
        return 0;
    L7:
        TL_stories.StoryItem r4 = ((TLRPC.TL_messageMediaStory) MessageObject.getMedia(r5)).storyItem;
        if (r4 == null) goto L17;
        TLRPC.MessageMedia r52 = r4.media;
        if (r52 == null) goto L17;
        if (r52.document == null) goto L17;
        if (r4.isPublic == false) goto L17;
        return 2;
    L17:
        return 0;
    L19:
        if (r5.sponsoredMedia == null) goto L22;
        return 2;
    L22:
        if (r5.isHiddenSensitive() == false) goto L25;
        return 0;
    L25:
        return canDownloadMediaInternal(r5);
    }

    public int canDownloadMediaType(MessageObject r5, long r6) {
        if (r5.type != 23) goto L19;
        if (SharedConfig.isAutoplayVideo() == true) goto L7;
        return 0;
    L7:
        TL_stories.StoryItem r4 = ((TLRPC.TL_messageMediaStory) MessageObject.getMedia(r5)).storyItem;
        if (r4 == null) goto L17;
        TLRPC.MessageMedia r52 = r4.media;
        if (r52 == null) goto L17;
        if (r52.document == null) goto L17;
        if (r4.isPublic == false) goto L17;
        return 2;
    L17:
        return 0;
    L19:
        if (r5.sponsoredMedia == null) goto L22;
        return 2;
    L22:
        if (r5.isHiddenSensitive() == false) goto L25;
        return 0;
    L25:
        return canDownloadMediaInternal(r5, r6);
    }

    public boolean canDownloadNextTrack() {
        int r0 = ApplicationLoader.getAutodownloadNetworkType();
        if (r0 != 1) goto L11;
        if (this.wifiPreset.enabled == true) goto L7;
    L9:
        return false;
    L7:
        if (getCurrentWiFiPreset().preloadMusic == false) goto L9;
        return true;
    L11:
        if (r0 != 2) goto L19;
        if (this.roamingPreset.enabled == true) goto L15;
    L17:
        return false;
    L15:
        if (getCurrentRoamingPreset().preloadMusic == false) goto L17;
        return true;
    L19:
        if (this.mobilePreset.enabled == true) goto L21;
    L23:
        return false;
    L21:
        if (getCurrentMobilePreset().preloadMusic == false) goto L23;
        return true;
    }

    public boolean canPreloadStories() {
        int r0 = ApplicationLoader.getAutodownloadNetworkType();
        if (r0 != 1) goto L9;
        if (this.wifiPreset.enabled == true) goto L7;
        return false;
    L7:
        Preset r3 = getCurrentWiFiPreset();
    L19:
        return r3.preloadStories;
    L9:
        if (r0 != 2) goto L15;
        if (this.roamingPreset.enabled == true) goto L13;
        return false;
    L13:
        r3 = getCurrentRoamingPreset();
        goto L19
    L15:
        if (this.mobilePreset.enabled == true) goto L17;
        return false;
    L17:
        r3 = getCurrentMobilePreset();
        goto L19
    }

    public void cancelDownloading(ArrayList<Pair<Long, Integer>> r6) {
        int r0 = r6.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L16;
        Pair<Long, Integer> r2 = r6.get(r1);
        DownloadObject r22 = this.downloadQueuePairs.get(r2);
        if (r22 == null) goto L15;
        TLObject r23 = r22.object;
        if ((r23 instanceof TLRPC.Document) == false) goto L11;
        FileLoader r3 = getFileLoader();
        r3.cancelLoadFile((TLRPC.Document) r23, true);
        goto L15
    L11:
        if ((r23 instanceof TLRPC.Photo) == false) goto L15;
        TLRPC.PhotoSize r24 = FileLoader.getClosestPhotoSizeWithSize(((TLRPC.Photo) r23).sizes, AndroidUtilities.getPhotoSize());
        if (r24 == null) goto L15;
        getFileLoader().cancelLoadFile(r24, true);
    L15:
        r1 = r1 + 1;
        goto L3
    }

    public void checkAutodownloadSettings() {
        int r0 = getCurrentDownloadMask();
        if (r0 == this.lastCheckMask) goto L76;
        this.lastCheckMask = r0;
        if ((r0 & 1) != 0) goto L8;
        int r1 = 0;
    L11:
        int r4 = this.photoDownloadQueue.size();
        ArrayList<DownloadObject> r5 = this.photoDownloadQueue;
        if (r1 >= r4) goto L20;
        DownloadObject r42 = r5.get(r1);
        TLObject r52 = r42.object;
        if ((r52 instanceof TLRPC.Photo) == false) goto L17;
        getFileLoader().cancelLoadFile(FileLoader.getClosestPhotoSizeWithSize(((TLRPC.Photo) r52).sizes, AndroidUtilities.getPhotoSize()));
    L19:
        r1 = r1 + 1;
        goto L11
    L17:
        if ((r52 instanceof TLRPC.Document) == false) goto L19;
        getFileLoader().cancelLoadFile((TLRPC.Document) r42.object);
        goto L19
    L20:
        r5.clear();
    L22:
        if ((r0 & 2) != 0) goto L24;
        int r12 = 0;
    L27:
        int r53 = this.audioDownloadQueue.size();
        ArrayList<DownloadObject> r6 = this.audioDownloadQueue;
        if (r12 >= r53) goto L30;
        getFileLoader().cancelLoadFile((TLRPC.Document) r6.get(r12).object);
        r12 = r12 + 1;
        goto L27
    L30:
        r6.clear();
    L32:
        if ((r0 & 8) != 0) goto L34;
        int r13 = 0;
    L37:
        int r62 = this.documentDownloadQueue.size();
        ArrayList<DownloadObject> r7 = this.documentDownloadQueue;
        if (r13 >= r62) goto L40;
        getFileLoader().cancelLoadFile((TLRPC.Document) r7.get(r13).object);
        r13 = r13 + 1;
        goto L37
    L40:
        r7.clear();
    L42:
        if ((r0 & 4) != 0) goto L44;
        int r02 = 0;
    L47:
        int r63 = this.videoDownloadQueue.size();
        ArrayList<DownloadObject> r72 = this.videoDownloadQueue;
        if (r02 >= r63) goto L50;
        getFileLoader().cancelLoadFile((TLRPC.Document) r72.get(r02).object);
        r02 = r02 + 1;
        goto L47
    L50:
        r72.clear();
    L51:
        int r03 = getAutodownloadMaskAll();
        if (r03 != 0) goto L56;
        getMessagesStorage().clearDownloadQueue(0);
        return;
    L56:
        if ((r03 & 1) != 0) goto L59;
        getMessagesStorage().clearDownloadQueue(1);
    L59:
        if ((r03 & 2) != 0) goto L62;
        getMessagesStorage().clearDownloadQueue(2);
    L62:
        if ((r03 & 4) != 0) goto L65;
        getMessagesStorage().clearDownloadQueue(4);
    L65:
        if ((r03 & 8) != 0) goto L75;
        getMessagesStorage().clearDownloadQueue(8);
        return;
    L75:
        return;
    L44:
        if (this.videoDownloadQueue.isEmpty() == false) goto L51;
        newDownloadObjectsAvailable(4);
        goto L51
    L34:
        if (this.documentDownloadQueue.isEmpty() == false) goto L42;
        newDownloadObjectsAvailable(8);
        goto L42
    L24:
        if (this.audioDownloadQueue.isEmpty() == false) goto L32;
        newDownloadObjectsAvailable(2);
        goto L32
    L8:
        if (this.photoDownloadQueue.isEmpty() == false) goto L22;
        newDownloadObjectsAvailable(1);
        goto L22
    }

    public void checkUnviewedDownloads(int r3, long r4) {
        MessageObject r0 = this.unviewedDownloads.get(r3);
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getDialogId() != r4) goto L11;
        this.unviewedDownloads.remove(r3);
        if (this.unviewedDownloads.size() != 0) goto L12;
        getNotificationCenter().postNotificationName(NotificationCenter.onDownloadingFilesChanged, new Object[0]);
        return;
    L12:
        return;
    }

    public void cleanup() {
        this.photoDownloadQueue.clear();
        this.audioDownloadQueue.clear();
        this.documentDownloadQueue.clear();
        this.videoDownloadQueue.clear();
        this.downloadQueueKeys.clear();
        this.downloadQueuePairs.clear();
        this.typingTimes.clear();
    }

    public void clearRecentDownloadedFiles() {
        this.recentDownloadingFiles.clear();
        getNotificationCenter().postNotificationName(NotificationCenter.onDownloadingFilesChanged, new Object[0]);
        getMessagesStorage().getStorageQueue().postRunnable(new DownloadController$$ExternalSyntheticLambda0(this));
    }

    public void clearUnviewedDownloads() {
        this.unviewedDownloads.clear();
    }

    public void deleteRecentFiles(ArrayList<MessageObject> r8) {
        int r1 = 0;
    L4:
        if (r1 >= r8.size()) goto L24;
        int r2 = 0;
    L7:
        if (r2 >= this.recentDownloadingFiles.size()) goto L14;
        if (r8.get(r1).getId() != this.recentDownloadingFiles.get(r2).getId()) goto L13;
        if (this.recentDownloadingFiles.get(r2).getDialogId() != r8.get(r1).getDialogId()) goto L13;
        this.recentDownloadingFiles.remove(r2);
    L23:
        r8.get(r1).putInDownloadsStore = false;
        FileLoader.getInstance(this.currentAccount).loadFile(r8.get(r1).getDocument(), r8.get(r1), 0, 0);
        FileLoader.getInstance(this.currentAccount).cancelLoadFile(r8.get(r1).getDocument(), true);
        r1 = r1 + 1;
    L13:
        r2 = r2 + 1;
        goto L7
    L14:
        int r22 = 0;
    L16:
        if (r22 >= this.downloadingFiles.size()) goto L23;
        if (r8.get(r1).getId() != this.downloadingFiles.get(r22).getId()) goto L22;
        if (this.downloadingFiles.get(r22).getDialogId() != r8.get(r1).getDialogId()) goto L22;
        this.downloadingFiles.remove(r22);
    L22:
        r22 = r22 + 1;
        goto L16
    L24:
        getNotificationCenter().postNotificationName(NotificationCenter.onDownloadingFilesChanged, new Object[0]);
        getMessagesStorage().getStorageQueue().postRunnable(new DownloadController$$ExternalSyntheticLambda5(this, r8));
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r13, int r14, Object... r15) {
        int r0 = 0;
        if (r13 != NotificationCenter.fileLoadFailed) goto L5;
    L100:
        String r132 = (String) r15[0];
        Integer r142 = (Integer) r15[1];
        this.listenerInProgress = true;
        ArrayList<WeakReference<FileDownloadProgressListener>> r152 = this.loadingFileObservers.get(r132);
        if (r152 == null) goto L117;
        int r2 = r152.size();
        int r3 = 0;
    L103:
        if (r3 >= r2) goto L115;
        WeakReference<FileDownloadProgressListener> r4 = r152.get(r3);
        if (r4.get() == null) goto L113;
        FileDownloadProgressListener r5 = r4.get();
        if (r142.intValue() != 1) goto L109;
        boolean r6 = true;
    L110:
        r5.onFailedDownload(r132, r6);
        if (r142.intValue() == 1) goto L113;
        this.observersByTag.remove(r4.get().getObserverTag());
        goto L113
    L109:
        r6 = false;
    L113:
        r3 = r3 + 1;
        goto L103
    L115:
        if (r142.intValue() == 1) goto L117;
        this.loadingFileObservers.remove(r132);
    L117:
        this.listenerInProgress = false;
        processLaterArrays();
        checkDownloadFinished(r132, r142.intValue());
        return;
    L5:
        if (r13 == NotificationCenter.httpFileDidFailedLoad) goto L100;
        if (r13 != NotificationCenter.fileLoaded) goto L10;
    L80:
        this.listenerInProgress = true;
        String r133 = (String) r15[0];
        ArrayList<WeakReference<MessageObject>> r143 = this.loadingFileMessagesObservers.get(r133);
        if (r143 == null) goto L89;
        int r153 = r143.size();
        int r22 = 0;
    L83:
        if (r22 >= r153) goto L88;
        MessageObject r32 = r143.get(r22).get();
        if (r32 == null) goto L87;
        r32.mediaExists = true;
    L87:
        r22 = r22 + 1;
        goto L83
    L88:
        this.loadingFileMessagesObservers.remove(r133);
    L89:
        ArrayList<WeakReference<FileDownloadProgressListener>> r144 = this.loadingFileObservers.get(r133);
        if (r144 == null) goto L98;
        int r154 = r144.size();
        int r1 = 0;
    L92:
        if (r1 >= r154) goto L97;
        WeakReference<FileDownloadProgressListener> r23 = r144.get(r1);
        if (r23.get() == null) goto L96;
        r23.get().onSuccessDownload(r133);
        this.observersByTag.remove(r23.get().getObserverTag());
    L96:
        r1 = r1 + 1;
        goto L92
    L97:
        this.loadingFileObservers.remove(r133);
    L98:
        this.listenerInProgress = false;
        processLaterArrays();
        checkDownloadFinished(r133, 0);
        return;
    L10:
        if (r13 == NotificationCenter.httpFileDidLoad) goto L80;
        if (r13 != NotificationCenter.fileLoadProgressChanged) goto L25;
        this.listenerInProgress = true;
        String r42 = (String) r15[0];
        ArrayList<WeakReference<FileDownloadProgressListener>> r134 = this.loadingFileObservers.get(r42);
        if (r134 == null) goto L22;
        Long r145 = (Long) r15[1];
        Long r155 = (Long) r15[2];
        int r12 = r134.size();
        int r24 = 0;
    L17:
        if (r24 >= r12) goto L22;
        WeakReference<FileDownloadProgressListener> r33 = r134.get(r24);
        if (r33.get() == null) goto L21;
        r33.get().onProgressDownload(r42, r145.longValue(), r155.longValue());
    L21:
        r24 = r24 + 1;
    L22:
        this.listenerInProgress = false;
        processLaterArrays();
        return;
    L25:
        if (r13 != NotificationCenter.fileUploadProgressChanged) goto L143;
        this.listenerInProgress = true;
        String r43 = (String) r15[0];
        ArrayList<WeakReference<FileDownloadProgressListener>> r135 = this.loadingFileObservers.get(r43);
        if (r135 == null) goto L34;
        Long r146 = (Long) r15[1];
        Long r16 = (Long) r15[2];
        Boolean r156 = (Boolean) r15[3];
        int r25 = r135.size();
        int r10 = 0;
    L29:
        if (r10 >= r25) goto L34;
        WeakReference<FileDownloadProgressListener> r34 = r135.get(r10);
        if (r34.get() == null) goto L33;
        r34.get().onProgressUpload(r43, r146.longValue(), r16.longValue(), r156.booleanValue());
    L33:
        r10 = r10 + 1;
    L34:
        this.listenerInProgress = false;
        processLaterArrays();
        ArrayList<SendMessagesHelper.DelayedMessage> r136 = getSendMessagesHelper().getDelayedMessages(r43);     // Catch: Exception -> L77
        if (r136 == null) goto L144;
    L37:
        if (r0 >= r136.size()) goto L145;
        SendMessagesHelper.DelayedMessage r147 = r136.get(r0);     // Catch: Exception -> L77
        if (r147.encryptedChat != null) goto L76;
        long r62 = r147.peer;     // Catch: Exception -> L77
        int r157 = r147.topMessageId;     // Catch: Exception -> L77
        Long r17 = this.typingTimes.get(r62);     // Catch: Exception -> L77
        if (r147.type != 4) goto L57;
        if (r17 != null) goto L45;
    L46:
        MessageObject r148 = (MessageObject) r147.extraHashMap.get(r43 + "_i");     // Catch: Exception -> L77
        if (r148 != null) goto L49;
    L51:
        if (r148 != null) goto L53;
    L55:
        getMessagesController().sendTyping(r62, r157, 4, 0);     // Catch: Exception -> L77
    L56:
        this.typingTimes.put(r62, Long.valueOf(System.currentTimeMillis()));     // Catch: Exception -> L77
        goto L76
    L53:
        if (r148.getDocument() == null) goto L55;
        getMessagesController().sendTyping(r62, r157, 3, 0);     // Catch: Exception -> L77
        goto L56
    L49:
        if (r148.isVideo() == false) goto L51;
        getMessagesController().sendTyping(r62, r157, 5, 0);     // Catch: Exception -> L77
        goto L56
    L45:
        if ((r17.longValue() + 4000) >= System.currentTimeMillis()) goto L76;
    L57:
        if (r17 == null) goto L61;
        if ((r17.longValue() + 4000) >= System.currentTimeMillis()) goto L76;
    L61:
        if (r147.obj.isRoundVideo() == false) goto L64;
        getMessagesController().sendTyping(r62, r157, 8, 0);     // Catch: Exception -> L77
    L75:
        this.typingTimes.put(r62, Long.valueOf(System.currentTimeMillis()));     // Catch: Exception -> L77
        goto L76
    L64:
        if (r147.obj.isVideo() == false) goto L67;
        getMessagesController().sendTyping(r62, r157, 5, 0);     // Catch: Exception -> L77
        goto L75
    L67:
        if (r147.obj.isVoice() == false) goto L70;
        getMessagesController().sendTyping(r62, r157, 9, 0);     // Catch: Exception -> L77
        goto L75
    L70:
        if (r147.obj.getDocument() == null) goto L73;
        getMessagesController().sendTyping(r62, r157, 3, 0);     // Catch: Exception -> L77
        goto L75
    L73:
        if (r147.photoSize == null) goto L75;
        getMessagesController().sendTyping(r62, r157, 4, 0);     // Catch: Exception -> L77
    L76:
        r0 = r0 + 1;
        goto L37
    L145:
        return;
    L144:
        return;
    L77:
        e = move-exception;
        FileLog.e(e);
        return;
    }

    public int generateObserverTag() {
        int r0 = this.lastTag;
        this.lastTag = r0 + 1;
        return r0;
    }

    public int getAutodownloadMask() {
        int r0 = ApplicationLoader.getAutodownloadNetworkType();
        if (r0 != 1) goto L9;
        if (this.wifiPreset.enabled == true) goto L7;
        return 0;
    L7:
        int[] r7 = getCurrentWiFiPreset().mask;
    L18:
        int r02 = 0;
        int r3 = 0;
    L20:
        if (r02 >= r7.length) goto L35;
        int r4 = r7[r02];
        if ((r4 & 1) == 0) goto L24;
        int r5 = 1;
    L26:
        if ((r4 & 2) == 0) goto L29;
        r5 = r5 | 2;
    L29:
        if ((r4 & 4) == 0) goto L32;
        r5 = r5 | 4;
    L32:
        if ((r4 & 8) == 0) goto L34;
        r5 = r5 | 8;
    L34:
        r3 = r3 | (r5 << (r02 * 8));
        r02 = r02 + 1;
        goto L20
    L24:
        r5 = 0;
        goto L26
    L35:
        return r3;
    L9:
        if (r0 != 2) goto L15;
        if (this.roamingPreset.enabled == true) goto L13;
        return 0;
    L13:
        r7 = getCurrentRoamingPreset().mask;
        goto L18
    L15:
        if (this.mobilePreset.enabled == true) goto L17;
        return 0;
    L17:
        r7 = getCurrentMobilePreset().mask;
        goto L18
    }

    public int getAutodownloadMaskAll() {
        int r1 = 0;
        if (this.mobilePreset.enabled == false) goto L5;
    L9:
        int r0 = 0;
    L11:
        if (r1 >= 4) goto L41;
        if ((getCurrentMobilePreset().mask[r1] & 1) == 0) goto L15;
    L18:
        r0 = r0 | 1;
    L20:
        if ((getCurrentMobilePreset().mask[r1] & 2) == 0) goto L22;
    L25:
        r0 = r0 | 2;
    L27:
        if ((getCurrentMobilePreset().mask[r1] & 4) == 0) goto L29;
    L32:
        r0 = r0 | 4;
    L34:
        if ((getCurrentMobilePreset().mask[r1] & 8) == 0) goto L36;
    L39:
        r0 = r0 | 8;
    L40:
        r1 = r1 + 1;
        goto L11
    L36:
        if ((getCurrentWiFiPreset().mask[r1] & 8) != 0) goto L39;
        if ((getCurrentRoamingPreset().mask[r1] & 8) == 0) goto L40;
    L29:
        if ((getCurrentWiFiPreset().mask[r1] & 4) != 0) goto L32;
        if ((4 & getCurrentRoamingPreset().mask[r1]) == 0) goto L34;
    L22:
        if ((getCurrentWiFiPreset().mask[r1] & 2) != 0) goto L25;
        if ((getCurrentRoamingPreset().mask[r1] & 2) == 0) goto L27;
    L15:
        if ((getCurrentWiFiPreset().mask[r1] & 1) != 0) goto L18;
        if ((getCurrentRoamingPreset().mask[r1] & 1) == 0) goto L20;
    L41:
        return r0;
    L5:
        if (this.roamingPreset.enabled == true) goto L9;
        if (this.wifiPreset.enabled == true) goto L9;
        return 0;
    }

    public int getCurrentDownloadMask() {
        int r0 = ApplicationLoader.getAutodownloadNetworkType();
        int r3 = 0;
        if (r0 != 1) goto L12;
        if (this.wifiPreset.enabled == true) goto L7;
        return 0;
    L7:
        int r02 = 0;
    L8:
        if (r3 >= 4) goto L10;
        r02 = r02 | getCurrentWiFiPreset().mask[r3];
        r3 = r3 + 1;
        goto L8
    L10:
        return r02;
    L12:
        if (r0 != 2) goto L21;
        if (this.roamingPreset.enabled == true) goto L16;
        return 0;
    L16:
        int r03 = 0;
    L17:
        if (r3 >= 4) goto L19;
        r03 = r03 | getCurrentRoamingPreset().mask[r3];
        r3 = r3 + 1;
        goto L17
    L19:
        return r03;
    L21:
        if (this.mobilePreset.enabled == true) goto L23;
        return 0;
    L23:
        int r04 = 0;
    L24:
        if (r3 >= 4) goto L26;
        r04 = r04 | getCurrentMobilePreset().mask[r3];
        r3 = r3 + 1;
        goto L24
    L26:
        return r04;
    }

    public Preset getCurrentMobilePreset() {
        int r0 = this.currentMobilePreset;
        if (r0 != 0) goto L7;
        return this.lowPreset;
    L7:
        if (r0 != 1) goto L11;
        return this.mediumPreset;
    L11:
        if (r0 != 2) goto L15;
        return this.highPreset;
    L15:
        return this.mobilePreset;
    }

    public Preset getCurrentRoamingPreset() {
        int r0 = this.currentRoamingPreset;
        if (r0 != 0) goto L7;
        return this.lowPreset;
    L7:
        if (r0 != 1) goto L11;
        return this.mediumPreset;
    L11:
        if (r0 != 2) goto L15;
        return this.highPreset;
    L15:
        return this.roamingPreset;
    }

    public Preset getCurrentWiFiPreset() {
        int r0 = this.currentWifiPreset;
        if (r0 != 0) goto L7;
        return this.lowPreset;
    L7:
        if (r0 != 1) goto L11;
        return this.mediumPreset;
    L11:
        if (r0 != 2) goto L15;
        return this.highPreset;
    L15:
        return this.wifiPreset;
    }

    public int getMaxVideoBitrate() {
        int r0 = ApplicationLoader.getAutodownloadNetworkType();
        if (r0 != 1) goto L7;
        return getCurrentWiFiPreset().maxVideoBitrate;
    L7:
        if (r0 != 2) goto L11;
        return getCurrentRoamingPreset().maxVideoBitrate;
    L11:
        return getCurrentMobilePreset().maxVideoBitrate;
    }

    public boolean hasUnviewedDownloads() {
        if (this.unviewedDownloads.size() <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isDownloading(int r4) {
        int r1 = 0;
    L4:
        if (r1 >= this.downloadingFiles.size()) goto L10;
        if (this.downloadingFiles.get(r1).messageOwner.id == r4) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    public void loadAutoDownloadConfig(boolean r5) {
        if (this.loadingAutoDownloadConfig == true) goto L10;
        if (r5 == false) goto L6;
    L8:
        this.loadingAutoDownloadConfig = true;
        TL_account.getAutoDownloadSettings r52 = new TL_account.getAutoDownloadSettings();
        getConnectionsManager().sendRequest(r52, new DownloadController$$ExternalSyntheticLambda8(this));
        return;
    L6:
        if (Math.abs(System.currentTimeMillis() - getUserConfig().autoDownloadConfigLoadTime) >= DurationKt.MILLIS_IN_DAY) goto L8;
        return;
    }

    public void loadDownloadingFiles() {
        getMessagesStorage().getStorageQueue().postRunnable(new DownloadController$$ExternalSyntheticLambda4(this));
    }

    public void newDownloadObjectsAvailable(int r4) {
        int r0 = getCurrentDownloadMask();
        if ((r0 & 1) == 0) goto L10;
        if ((r4 & 1) == 0) goto L10;
        if (this.photoDownloadQueue.isEmpty() == false) goto L10;
        getMessagesStorage().getDownloadQueue(1);
    L10:
        if ((r0 & 2) == 0) goto L17;
        if ((r4 & 2) == 0) goto L17;
        if (this.audioDownloadQueue.isEmpty() == false) goto L17;
        getMessagesStorage().getDownloadQueue(2);
    L17:
        if ((r0 & 4) == 0) goto L24;
        if ((r4 & 4) == 0) goto L24;
        if (this.videoDownloadQueue.isEmpty() == false) goto L24;
        getMessagesStorage().getDownloadQueue(4);
    L24:
        if ((r0 & 8) != 0) goto L26;
        return;
    L26:
        if ((r4 & 8) != 0) goto L28;
        return;
    L28:
        if (this.documentDownloadQueue.isEmpty() == false) goto L33;
        getMessagesStorage().getDownloadQueue(8);
        return;
    }

    public void onDownloadComplete(MessageObject r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.getDocument() == null) goto L9;
        AndroidUtilities.runOnUIThread(new DownloadController$$ExternalSyntheticLambda1(this, r3.getDocument(), r3));
        return;
    }

    public void onDownloadFail(MessageObject r3, int r4) {
        if (r3 == null) goto L9;
        TLRPC.Document r0 = r3.getDocument();
        if (r0 != null) goto L7;
        return;
    L7:
        AndroidUtilities.runOnUIThread(new DownloadController$$ExternalSyntheticLambda13(this, r0, r4));
        getMessagesStorage().getStorageQueue().postRunnable(new DownloadController$$ExternalSyntheticLambda14(this, r3));
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v13, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.lang.String] */
    public void processDownloadObjects(int r20, ArrayList<DownloadObject> r21) {
        if (r21.isEmpty() == false) goto L6;
        return;
    L6:
        if (r20 != 1) goto L8;
        ArrayList<DownloadObject> r1 = this.photoDownloadQueue;
    L14:
        int r5 = 0;
    L16:
        if (r5 >= r21.size()) goto L54;
        DownloadObject r7 = r21.get(r5);
        TLObject r8 = r7.object;
        TLRPC.PhotoSize r10 = null;
        if ((r8 instanceof TLRPC.Document) == false) goto L22;
        TLRPC.PhotoSize r82 = FileLoader.getAttachFileName((TLRPC.Document) r8);
    L20:
        TLRPC.PhotoSize r18 = r10;
        r10 = r82;
        TLRPC.PhotoSize r83 = r18;
    L25:
        if (r10 == null) goto L47;
        if (this.downloadQueueKeys.containsKey(r10) == true) goto L47;
        TLObject r9 = r7.object;
        if (r83 == null) goto L40;
        TLRPC.Photo r92 = (TLRPC.Photo) r9;
        if (r7.secret == false) goto L35;
        int r17 = 2;
    L38:
        getFileLoader().loadFile(ImageLocation.getForPhoto(r83, r92), r7.parent, null, 0, r17);
    L46:
        r1.add(r7);
        this.downloadQueueKeys.put(r10, r7);
        this.downloadQueuePairs.put(new Pair(Long.valueOf(r7.id), Integer.valueOf(r7.type)), r7);
        goto L47
    L35:
        if (r7.forceCache == false) goto L37;
        r17 = 1;
        goto L38
    L37:
        r17 = 0;
        goto L38
    L40:
        if ((r9 instanceof TLRPC.Document) == false) goto L47;
        TLRPC.Document r93 = (TLRPC.Document) r9;
        FileLoader r84 = getFileLoader();
        String r11 = r7.parent;
        if (r7.secret == false) goto L44;
        int r12 = 2;
    L45:
        r84.loadFile(r93, r11, 0, r12);
        goto L46
    L44:
        r12 = 0;
    L47:
        r5 = r5 + 1;
        goto L16
    L22:
        if ((r8 instanceof TLRPC.Photo) == false) goto L24;
        r10 = FileLoader.getClosestPhotoSizeWithSize(((TLRPC.Photo) r8).sizes, AndroidUtilities.getPhotoSize());
        r82 = FileLoader.getAttachFileName(r10);
        goto L20
    L24:
        r83 = null;
        goto L25
    L54:
        return;
    L8:
        if (r20 != 2) goto L11;
        r1 = this.audioDownloadQueue;
        goto L14
    L11:
        if (r20 != 4) goto L13;
        r1 = this.videoDownloadQueue;
        goto L14
    L13:
        r1 = this.documentDownloadQueue;
        goto L14
    }

    public void removeLoadingFileObserver(FileDownloadProgressListener r6) {
        if (this.listenerInProgress == false) goto L6;
        this.deleteLaterArray.add(r6);
        return;
    L6:
        String r0 = this.observersByTag.get(r6.getObserverTag());
        if (r0 == null) goto L27;
        ArrayList<WeakReference<FileDownloadProgressListener>> r1 = this.loadingFileObservers.get(r0);
        if (r1 == null) goto L22;
        int r2 = 0;
    L12:
        if (r2 >= r1.size()) goto L20;
        WeakReference<FileDownloadProgressListener> r3 = r1.get(r2);
        if (r3.get() != null) goto L16;
    L17:
        r1.remove(r2);
        r2 = r2 - 1;
    L18:
        r2 = r2 + 1;
        goto L12
    L16:
        if (r3.get() != r6) goto L18;
    L20:
        if (r1.isEmpty() == false) goto L22;
        this.loadingFileObservers.remove(r0);
    L22:
        this.observersByTag.remove(r6.getObserverTag());
        return;
    }

    public void savePresetToServer(int r10) {
        TL_account.saveAutoDownloadSettings r0 = new TL_account.saveAutoDownloadSettings();
        if (r10 != 0) goto L5;
        Preset r102 = getCurrentMobilePreset();
        boolean r2 = this.mobilePreset.enabled;
    L8:
        TLRPC.TL_autoDownloadSettings r3 = new TLRPC.TL_autoDownloadSettings();
        r0.settings = r3;
        r3.audio_preload_next = r102.preloadMusic;
        r3.video_preload_large = r102.preloadVideo;
        r3.phonecalls_less_data = r102.lessCallData;
        r3.video_upload_maxbitrate = r102.maxVideoBitrate;
        r3.disabled = !r2;
        int r22 = 0;
        int r32 = 0;
        boolean r4 = false;
        boolean r5 = false;
        boolean r6 = false;
    L9:
        int[] r7 = r102.mask;
        if (r32 >= r7.length) goto L25;
        int r72 = r7[r32];
        if ((r72 & 1) == 0) goto L15;
        r4 = true;
    L15:
        if ((r72 & 4) == 0) goto L18;
        r5 = true;
    L18:
        if ((r72 & 8) == 0) goto L20;
        r6 = true;
    L20:
        if (r4 == false) goto L24;
        if (r5 == false) goto L24;
        if (r6 == true) goto L25;
    L24:
        r32 = r32 + 1;
    L25:
        TLRPC.TL_autoDownloadSettings r33 = r0.settings;
        if (r4 == false) goto L28;
        r22 = (int) r102.sizes[0];
    L28:
        r33.photo_size_max = r22;
        long r73 = 0;
        if (r5 == false) goto L31;
        long r1 = r102.sizes[1];
    L32:
        r33.video_size_max = r1;
        if (r6 == false) goto L35;
        r73 = r102.sizes[2];
    L35:
        r33.file_size_max = r73;
        getConnectionsManager().sendRequest(r0, new DownloadController$$ExternalSyntheticLambda11());
        return;
    L31:
        r1 = 0;
        goto L32
    L5:
        if (r10 != 1) goto L7;
        r102 = getCurrentWiFiPreset();
        r2 = this.wifiPreset.enabled;
        goto L8
    L7:
        r102 = getCurrentRoamingPreset();
        r2 = this.roamingPreset.enabled;
        goto L8
    }

    public void startDownloadFile(TLRPC.Document r2, MessageObject r3) {
        if (r3 == null) goto L9;
        TLRPC.Document r22 = r3.getDocument();
        if (r22 != null) goto L7;
        return;
    L7:
        AndroidUtilities.runOnUIThread(new DownloadController$$ExternalSyntheticLambda7(this, r22, r3));
        return;
    }

    public void swapLoadingPriority(MessageObject r4, MessageObject r5) {
        int r0 = this.downloadingFiles.indexOf(r4);
        int r1 = this.downloadingFiles.indexOf(r5);
        if (r0 < 0) goto L6;
        if (r1 < 0) goto L6;
        this.downloadingFiles.set(r0, r5);
        this.downloadingFiles.set(r1, r4);
    L6:
        updateFilesLoadingPriority();
    }

    public void updateFilesLoadingPriority() {
        int r0 = this.downloadingFiles.size() - 1;
    L3:
        if (r0 < 0) goto L8;
        if (getFileLoader().isLoadingFile(this.downloadingFiles.get(r0).getFileName()) == false) goto L7;
        getFileLoader().loadFile(this.downloadingFiles.get(r0).getDocument(), this.downloadingFiles.get(r0), 2, 0);
    L7:
        r0 = r0 - 1;
        goto L3
    }
}
