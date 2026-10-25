package org.telegram.ui.iv;

import android.graphics.BitmapFactory;
import java.io.File;
import java.util.ArrayList;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public class RichMediaConverter implements NotificationCenter.NotificationCenterDelegate {
    private boolean cancelled;
    private final int currentAccount;
    private final MediaController.PhotoEntry entry;
    private boolean finished;
    private VideoEditedInfo info;
    private final Listener listener;
    private MessageObject messageObject;
    private String outPath;
    private boolean started;

    public interface Listener {
        void onDone(String r1, int r2, int r3, int r4);

        void onError();

        void onProgress(float r1);
    }

    public RichMediaConverter(int r1, MediaController.PhotoEntry r2, Listener r3) {
        this.currentAccount = r1;
        this.entry = r2;
        this.listener = r3;
    }

    private static VideoEditedInfo buildVideoEditedInfo(MediaController.PhotoEntry r17) {
        int r1 = r17.width;
        int r2 = r17.height;
        if (r1 <= 0) goto L52;
        if (r2 <= 0) goto L52;
    L6:
        ArrayList<VideoEditedInfo.MediaEntity> r4 = null;
        if (r1 <= 0) goto L50;
        if (r2 <= 0) goto L50;
        int r5 = r17.orientation;
        if (r5 == 90) goto L13;
        if (r5 == 270) goto L13;
    L14:
        VideoEditedInfo r52 = new VideoEditedInfo();
        r52.startTime = 0;
        r52.start = 0.0f;
        r52.endTime = Math.min(3000, r17.averageDuration);
    L15:
        long r10 = r52.endTime;
        if (r10 <= 0) goto L21;
        if (r10 >= 1000) goto L21;
        r52.endTime = r10 * 2;
    L21:
        if (r10 > 0) goto L23;
        r52.endTime = 3000;
    L23:
        long r102 = r52.endTime;
        r52.end = r102;
        r52.compressQuality = 1;
        r52.rotationValue = 0;
        r52.originalPath = r17.path;
        r52.estimatedSize = (int) ((r102 / 1000.0f) * 115200.0f);
        r52.estimatedDuration = r102;
        r52.framerate = 30;
        r52.originalDuration = r102;
        r52.filterState = r17.savedFilterState;
        String r103 = r17.croppedPaintPath;
        if (r103 == null) goto L31;
        r52.paintPath = r103;
        ArrayList<VideoEditedInfo.MediaEntity> r104 = r17.croppedMediaEntities;
        if (r104 != null) goto L28;
    L30:
        r52.mediaEntities = r4;
    L32:
        r52.isPhoto = true;
        MediaController.CropState r0 = r17.cropState;
        if (r0 == null) goto L39;
        int r42 = r0.transformRotation;
        if (r42 == 90) goto L37;
        if (r42 == 270) goto L37;
    L38:
        r1 = (int) (r1 * r0.cropPw);
        r2 = (int) (r2 * r0.cropPh);
    L37:
        int r16 = r2;
        r2 = r1;
        r1 = r16;
    L39:
        float r02 = r1;
        float r22 = r2;
        float r12 = Math.max(r02 / 854.0f, r22 / 854.0f);
        if (r12 >= 1.0f) goto L42;
        r12 = 1.0f;
    L42:
        int r03 = (int) (r02 / r12);
        int r13 = (int) (r22 / r12);
        if ((r03 % 16) == 0) goto L46;
        r03 = Math.max(1, Math.round(r03 / 16.0f)) * 16;
    L46:
        if ((r13 % 16) == 0) goto L48;
        r13 = Math.max(1, Math.round(r13 / 16.0f)) * 16;
    L48:
        r52.resultWidth = r03;
        r52.originalWidth = r03;
        r52.resultHeight = r13;
        r52.originalHeight = r13;
        r52.bitrate = -1;
        r52.muted = true;
        r52.avatarStartTime = 0;
        return r52;
    L28:
        if (r104.isEmpty() == true) goto L30;
        r4 = r17.croppedMediaEntities;
        goto L30
    L31:
        r52.paintPath = r17.paintPath;
        r52.mediaEntities = r17.mediaEntities;
    L13:
        int r162 = r2;
        r2 = r1;
        r1 = r162;
    L50:
        return null;
    L52:
        BitmapFactory.Options r43 = new BitmapFactory.Options();     // Catch: Exception -> L51
        r43.inJustDecodeBounds = true;     // Catch: Exception -> L51
        BitmapFactory.decodeFile(r17.path, r43);     // Catch: Exception -> L51
        r1 = r43.outWidth;     // Catch: Exception -> L51
        r2 = r43.outHeight;     // Catch: Exception -> L51
        goto L6
    }

    private void fail() {
        if (this.finished == true) goto L10;
        this.finished = true;
        teardown();
        Listener r1 = this.listener;
        if (r1 == null) goto L9;
        r1.onError();
        return;
    L9:
        return;
    }

    public static boolean hasAnimatedMediaEntities(MediaController.PhotoEntry r6) {
        if (r6 != null) goto L5;
    L33:
        return false;
    L5:
        if (r6.isVideo == true) goto L33;
        ArrayList<VideoEditedInfo.MediaEntity> r1 = r6.croppedMediaEntities;
        if (r1 != null) goto L10;
    L12:
        ArrayList<VideoEditedInfo.MediaEntity> r62 = r6.mediaEntities;
    L13:
        if (r62 != null) goto L15;
        return false;
    L15:
        int r12 = r62.size();
        int r2 = 0;
    L16:
        if (r2 >= r12) goto L33;
        VideoEditedInfo.MediaEntity r3 = r62.get(r2);
        if (r3 == null) goto L32;
        if (r3.type != 0) goto L26;
        byte r4 = r3.subType;
        if ((r4 & 1) != 0) goto L30;
        if ((r4 & 4) == 0) goto L26;
        return true;
    L30:
        return true;
    L26:
        ArrayList<VideoEditedInfo.EmojiEntity> r32 = r3.entities;
        if (r32 == null) goto L32;
        if (r32.isEmpty() == true) goto L32;
        return true;
    L32:
        r2 = r2 + 1;
        goto L16
    L10:
        if (r1.isEmpty() == true) goto L12;
        r62 = r6.croppedMediaEntities;
        goto L13
    }

    private void teardown() {
        NotificationCenter r0 = NotificationCenter.getInstance(this.currentAccount);
        r0.removeObserver(this, NotificationCenter.filePreparingStarted);
        r0.removeObserver(this, NotificationCenter.fileNewChunkAvailable);
        r0.removeObserver(this, NotificationCenter.filePreparingFailed);
    }

    public void cancel() {
        if (this.finished == false) goto L5;
        return;
    L5:
        if (this.cancelled == true) goto L18;
        this.cancelled = true;
        if (this.messageObject != null) goto L10;
    L12:
        teardown();
        return;
    L10:
        if (this.info == null) goto L12;
        MediaController.getInstance().cancelVideoConvert(this.messageObject);     // Catch: Throwable -> L14
        goto L12
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r6, int r7, Object... r8) {
        if (this.cancelled == false) goto L5;
        return;
    L5:
        if (this.finished == false) goto L8;
        return;
    L8:
        if (r7 == this.currentAccount) goto L11;
        return;
    L11:
        if (r8.length != 0) goto L13;
        return;
    L13:
        if (r8[0] == this.messageObject) goto L16;
        return;
    L16:
        if (r6 != NotificationCenter.fileNewChunkAvailable) goto L27;
        long r62 = ((Long) r8[3]).longValue();
        float r82 = ((Float) r8[4]).floatValue();
        Listener r0 = this.listener;
        if (r0 == null) goto L21;
        r0.onProgress(r82);
    L21:
        if (r62 <= 0) goto L32;
        this.finished = true;
        teardown();
        Listener r63 = this.listener;
        if (r63 == null) goto L33;
        String r72 = this.outPath;
        VideoEditedInfo r5 = this.info;
        r63.onDone(r72, r5.resultWidth, r5.resultHeight, (int) Math.ceil(r5.estimatedDuration / 1000.0d));
        return;
    L33:
        return;
    L32:
        return;
    L27:
        if (r6 != NotificationCenter.filePreparingFailed) goto L34;
        fail();
        return;
    }

    public void start() {
        if (this.started == false) goto L5;
        return;
    L5:
        if (this.cancelled == false) goto L7;
        return;
    L7:
        if (this.finished == true) goto L20;
        this.started = true;
        VideoEditedInfo r1 = buildVideoEditedInfo(this.entry);
        this.info = r1;
        if (r1 != null) goto L12;
    L16:
        fail();
        return;
    L12:
        if (r1.needConvert() == false) goto L16;
        TLRPC.TL_message r4 = new TLRPC.TL_message();
        r4.id = 1;
        String r0 = new File(FileLoader.getDirectory(4), "rich_anim_" + SharedConfig.getLastLocalId() + ".mp4").getAbsolutePath();
        this.outPath = r0;
        r4.attachPath = r0;
        MessageObject r2 = new MessageObject(this.currentAccount, r4, null, false, false);
        this.messageObject = r2;
        r2.videoEditedInfo = this.info;
        NotificationCenter r02 = NotificationCenter.getInstance(this.currentAccount);
        r02.addObserver(this, NotificationCenter.filePreparingStarted);
        r02.addObserver(this, NotificationCenter.fileNewChunkAvailable);
        r02.addObserver(this, NotificationCenter.filePreparingFailed);
        MediaController.getInstance().scheduleVideoConvert(this.messageObject, false, false, false);
        return;
    }
}
