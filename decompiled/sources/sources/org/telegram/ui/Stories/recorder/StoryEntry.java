package org.telegram.ui.Stories.recorder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.TextUtils;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.FileRefController;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.messenger.video.MediaCodecVideoConvertor;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedFileNative;
import org.telegram.ui.Components.PhotoFilterView;
import org.telegram.ui.Components.RLottieNative;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes7.dex */
public class StoryEntry {
    public HashSet<Integer> albums;
    public boolean allowScreenshots;
    public String audioAuthor;
    public TLRPC.InputDocument audioDocument;
    public long audioDuration;
    public float audioLeft;
    public long audioOffset;
    public String audioPath;
    public float audioRight;
    public String audioTitle;
    public float audioVolume;
    public long averageDuration;
    public Drawable backgroundDrawable;
    public File backgroundFile;
    public String backgroundWallpaperEmoticon;
    public long backgroundWallpaperPeerId;
    public Bitmap blurredVideoThumb;
    public long botId;
    public String botLang;
    public CharSequence caption;
    public boolean captionEntitiesAllowed;
    private int checkStickersReqId;
    public CollageLayout collage;
    public ArrayList<StoryEntry> collageContent;
    public long cover;
    public Bitmap coverBitmap;
    public boolean coverSet;
    public MediaController.CropState crop;
    public final int currentAccount;
    public long draftDate;
    public long draftId;
    public File draftThumbFile;
    public long duration;
    public long editDocumentId;
    public long editExpireDate;
    public long editPhotoId;
    public List<TLRPC.InputDocument> editStickers;
    public int editStoryId;
    public long editStoryPeerId;
    public boolean editedCaption;
    public boolean editedMedia;
    public ArrayList<TL_stories.MediaArea> editedMediaAreas;
    public boolean editedPrivacy;
    public TLRPC.InputMedia editingBotPreview;
    public TLRPC.Document editingCoverDocument;
    public TLRPC.TL_error error;
    public File file;
    public boolean fileDeletable;
    public double fileDuration;
    public File filterFile;
    public MediaController.SavedFilterState filterState;
    private boolean fromCamera;
    public int gradientBottomColor;
    public int gradientTopColor;
    public HDRInfo hdrInfo;
    public int height;
    public int invert;
    public boolean isDark;
    public boolean isDraft;
    public boolean isEdit;
    public boolean isEditSaved;
    public boolean isEditingCover;
    public boolean isError;
    public boolean isRepost;
    public boolean isRepostMessage;
    public boolean isShare;
    public boolean isVideo;
    public float left;
    public final Matrix matrix;
    public ArrayList<VideoEditedInfo.MediaEntity> mediaEntities;
    public File messageFile;
    public ArrayList<MessageObject> messageObjects;
    public File messageVideoMaskFile;
    public boolean muted;
    public int orientation;
    public File paintBlurFile;
    public File paintEntitiesFile;
    public File paintFile;
    public TLRPC.InputPeer peer;
    public int period;
    public boolean pinned;
    public StoryPrivacyBottomSheet.StoryPrivacy privacy;
    public final ArrayList<TLRPC.InputPrivacyRule> privacyRules;
    public String repostCaption;
    public TLRPC.MessageMedia repostMedia;
    public TLRPC.Peer repostPeer;
    public CharSequence repostPeerName;
    public int repostStoryId;
    public int resultHeight;
    public int resultWidth;
    public float right;
    public File round;
    public long roundDuration;
    public float roundLeft;
    public long roundOffset;
    public float roundRight;
    public String roundThumb;
    public float roundVolume;
    public int scheduleDate;
    public ArrayList<Long> shareUserIds;
    public boolean silent;
    public List<TLRPC.InputDocument> stickers;
    public Bitmap thumbBitmap;
    public String thumbPath;
    public Bitmap thumbPathBitmap;
    public Utilities.Callback<Utilities.Callback<TLRPC.Document>> updateDocumentRef;
    public File uploadThumbFile;
    public float videoLeft;
    public boolean videoLoop;
    public long videoOffset;
    public float videoRight;
    public float videoVolume;
    public int width;

    public interface DecodeBitmap {
        Bitmap decode(BitmapFactory.Options r1);
    }

    public static class HDRInfo {
        public int colorRange;
        public int colorStandard;
        public int colorTransfer;
        public float maxlum;
        public float minlum;

        public HDRInfo() {
        }

        public int getHDRType() {
            if (this.colorStandard != 6) goto L11;
            int r2 = this.colorTransfer;
            if (r2 != 7) goto L8;
            return 1;
        L8:
            if (r2 != 6) goto L13;
            return 2;
        L13:
            return 0;
        L11:
            return 0;
        }
    }

    public static /* synthetic */ void $r8$lambda$0lHsDlpwasNzfg6zwoDwh3qpVos(StoryEntry r0, Utilities.Callback r1) {
        r0.lambda$detectHDR$12(r1);
    }

    public static /* synthetic */ Bitmap $r8$lambda$1OOyTYUgvqNne_axFzTZTiCLD_E(File r0, BitmapFactory.Options r1) {
        return BitmapFactory.decodeFile(r0.getPath(), r1);
    }

    public static /* synthetic */ void $r8$lambda$Df5nUUKU459Irbgo7aRJ8MjlGSQ(StoryEntry r0, Utilities.Callback r1) {
        r0.lambda$detectHDR$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$EQIL8HLkR5Ib2V8_VJ65WfcrGeA(StoryEntry r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$checkStickers$15(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$EUeGHoSOs8zXahaxowCLuq3i09o(String r2, int[][] r3, Runnable r4) {
        AnimatedFileNative.getVideoInfo(r2, r3[0], 0);
        AndroidUtilities.runOnUIThread(r4);
    }

    public static /* synthetic */ void $r8$lambda$H6dmbGYF3l9IaDiz6ctKYYobTL8(String[] r5, int[][] r6, Runnable r7) {
        int r0 = 0;
    L4:
        if (r0 >= r5.length) goto L9;
        String r1 = r5[r0];
        if (r1 == null) goto L8;
        AnimatedFileNative.getVideoInfo(r1, r6[r0], 0);
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        AndroidUtilities.runOnUIThread(r7);
    }

    public static /* synthetic */ Bitmap $r8$lambda$Klgwcc8fQhmE5uS_lc9VVADBoYE(StoryEntry r0, BitmapFactory.Options r1) {
        return r0.lambda$buildBitmap$0(r1);
    }

    public static /* synthetic */ Bitmap $r8$lambda$SbUDXY5QNABAspZOaSY6ifNgL7U(StoryEntry r0, BitmapFactory.Options r1) {
        return r0.lambda$buildBitmap$4(r1);
    }

    /* renamed from: $r8$lambda$TfXWb30yaMwIV9-A0NVWEXA1big, reason: not valid java name */
    public static /* synthetic */ void m20221$r8$lambda$TfXWb30yaMwIV9A0NVWEXA1big(StoryEntry r0, String r1, int[][] r2, Utilities.Callback r3) {
        r0.lambda$getVideoEditedInfo$9(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$bvswIjj-V3LLf8HgjteCCiB0704, reason: not valid java name */
    public static /* synthetic */ void m20222$r8$lambda$bvswIjjV3LLf8HgjteCCiB0704(StoryEntry r0, TLObject r1) {
        r0.lambda$checkStickers$14(r1);
    }

    public static /* synthetic */ void $r8$lambda$edfXDqIw8U2HeVEu61pa8a3EWO4(StoryEntry r0, Bitmap r1, Runnable r2, int[] r3) {
        r0.lambda$setupGradient$7(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$jXSjE_J0ErOz3kbdSbCDtSQB3XU(StoryEntry r0, Bitmap r1, boolean r2, Runnable r3) {
        r0.lambda$updateFilter$6(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$pQ596iYBXSzuhrUdQBpnfXGkQUI(StoryEntry r0, Runnable r1, int[] r2) {
        r0.lambda$setupGradient$8(r1, r2);
    }

    /* renamed from: $r8$lambda$pcM-EH4HZOE6SMaRISfD1iFMn8A, reason: not valid java name */
    public static /* synthetic */ Bitmap m20223$r8$lambda$pcMEH4HZOE6SMaRISfD1iFMn8A(StoryEntry r0, BitmapFactory.Options r1) {
        return r0.lambda$buildBitmap$3(r1);
    }

    /* renamed from: $r8$lambda$qDr-FM-3QRDmUaVcieDPw5Jzl-Q, reason: not valid java name */
    public static /* synthetic */ Bitmap m20224$r8$lambda$qDrFM3QRDmUaVcieDPw5JzlQ(File r0, BitmapFactory.Options r1) {
        return BitmapFactory.decodeFile(r0.getPath(), r1);
    }

    public static /* synthetic */ void $r8$lambda$qX16IuxE6lDY1hROj4iTNW2Xqh4(StoryEntry r0, TL_stories.StoryItem r1, TLRPC.TL_messages_getAttachedStickers r2, RequestDelegate r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$checkStickers$16(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$r3Be54PZLVX-qXgE1zaOQQXUDFs, reason: not valid java name */
    public static /* synthetic */ Bitmap m20225$r8$lambda$r3Be54PZLVXqXgE1zaOQQXUDFs(StoryEntry r0, BitmapFactory.Options r1) {
        return r0.lambda$buildBitmap$5(r1);
    }

    public StoryEntry() {
        this.currentAccount = UserConfig.selectedAccount;
        this.fileDuration = -1.0d;
        this.audioRight = 1.0f;
        this.audioVolume = 1.0f;
        this.videoVolume = 1.0f;
        this.videoLoop = false;
        this.videoLeft = 0.0f;
        this.videoRight = 1.0f;
        this.right = 1.0f;
        this.cover = -1;
        this.resultWidth = 720;
        this.resultHeight = 1280;
        this.matrix = new Matrix();
        this.roundRight = 1.0f;
        this.roundVolume = 1.0f;
        this.isDark = Theme.isCurrentThemeDark();
        this.backgroundWallpaperPeerId = Long.MIN_VALUE;
        this.captionEntitiesAllowed = true;
        this.privacyRules = new ArrayList();
        this.pinned = true;
        this.period = 86400;
        this.botLang = _UrlKt.FRAGMENT_ENCODE_SET;
        this.averageDuration = 5000;
        this.checkStickersReqId = 0;
    }

    public static StoryEntry asCollage(CollageLayout r5, ArrayList<StoryEntry> r6) {
        StoryEntry r0 = new StoryEntry();
        r0.collage = r5;
        r0.collageContent = r6;
        int r52 = r6.size();
        int r1 = 0;
    L3:
        if (r1 >= r52) goto L8;
        StoryEntry r2 = r6.get(r1);
        r1 = r1 + 1;
        StoryEntry r22 = r2;
        if (r22.isVideo == false) goto L3;
        r0.isVideo = true;
        r22.videoLeft = 0.0f;
        r22.videoRight = Math.min(1.0f, 59000.0f / r22.duration);
        goto L3
    L8:
        if (r0.isVideo == false) goto L10;
        r0.width = 720;
        r0.height = 1280;
        r0.resultWidth = 720;
        r0.resultHeight = 1280;
    L11:
        r0.setupMatrix();
        return r0;
    L10:
        r0.width = 1080;
        r0.height = 1920;
        r0.resultWidth = 1080;
        r0.resultHeight = 1920;
        goto L11
    }

    public static int calculateInSampleSize(BitmapFactory.Options r6, int r7, int r8) {
        int r0 = r6.outHeight;
        int r62 = r6.outWidth;
        if (r0 > r8) goto L7;
        if (r62 > r7) goto L7;
        int r63 = 1;
    L8:
        double r64 = r63;
        return Math.max(1, (int) Math.pow(r64, Math.floor(Math.log(r64) / Math.log(2.0d))));
    L7:
        r63 = Math.min((int) Math.ceil(r0 / r8), (int) Math.ceil(r62 / r7));
        goto L8
    }

    public static boolean canRepostMessage(MessageObject r10) {
        if (r10 != null) goto L5;
    L45:
        return false;
    L5:
        if (r10.isSponsored() == true) goto L45;
        TLRPC.Message r1 = r10.messageOwner;
        if (r1 != null) goto L10;
    L12:
        int r12 = r10.type;
        if (r12 == 17) goto L45;
        if (r12 == 12) goto L45;
        long r13 = r10.getDialogId();
        TLRPC.Chat r3 = MessagesController.getInstance(r10.currentAccount).getChat(Long.valueOf(-r13));
        if (r3 == null) goto L23;
        if (r3.noforwards == false) goto L23;
        return false;
    L23:
        if (r13 < 0) goto L25;
    L28:
        TLRPC.MessageFwdHeader r14 = r10.messageOwner.fwd_from;
        if (r14 == null) goto L45;
        TLRPC.Peer r32 = r14.from_id;
        if (r32 == null) goto L45;
        if ((r14.flags & 4) == 0) goto L45;
        long r6 = DialogObject.getPeerDialogId(r32);
        TLRPC.Chat r102 = MessagesController.getInstance(r10.currentAccount).getChat(Long.valueOf(-r6));
        if (r6 >= 0) goto L45;
        if (r102 == null) goto L40;
        if (r102.noforwards == true) goto L45;
    L40:
        if (ChatObject.isChannelAndNotMegaGroup(r102) == false) goto L45;
        if (ChatObject.isPublic(r102) == false) goto L45;
        return true;
    L25:
        if (ChatObject.isChannelAndNotMegaGroup(r3) == false) goto L28;
        return true;
    L10:
        if (r1.noforwards == false) goto L12;
        return false;
    }

    public static void drawBackgroundDrawable(Canvas r5, Drawable r6, int r7, int r8) {
        if (r6 != null) goto L4;
        return;
    L4:
        Rect r0 = new Rect(r6.getBounds());
        Drawable.Callback r1 = r6.getCallback();
        r6.setCallback(null);
        if ((r6 instanceof BitmapDrawable) == false) goto L7;
        BitmapDrawable r2 = (BitmapDrawable) r6;
        float r4 = r2.getBitmap().getWidth();
        float r22 = r2.getBitmap().getHeight();
        float r72 = Math.max(r7 / r4, r8 / r22);
        r6.setBounds(0, 0, (int) (r4 * r72), (int) (r22 * r72));
        r6.draw(r5);
    L8:
        r6.setBounds(r0);
        r6.setCallback(r1);
        return;
    L7:
        r6.setBounds(0, 0, r7, r8);
        r6.draw(r5);
        goto L8
    }

    private String ext(File r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        String r22 = r2.getPath();
        int r0 = r22.lastIndexOf(46);
        if (r0 > 0) goto L8;
        return null;
    L8:
        return r22.substring(r0 + 1);
    }

    public static StoryEntry fromMedia(ArrayList<SendMessagesHelper.SendingMediaInfo> r3) {
        ArrayList<MediaController.PhotoEntry> r32 = ChatActivity.createEntriesFromMedia(r3, false, null);
        if (r32.isEmpty() == false) goto L6;
        return null;
    L6:
        return fromPhotoEntry(r32.get(0));
    }

    public static StoryEntry fromPhotoEntry(MediaController.PhotoEntry r5) {
        StoryEntry r0 = new StoryEntry();
        r0.file = new File(r5.path);
        r0.orientation = r5.orientation;
        r0.invert = r5.invert;
        if (r5.isLivePhoto() == false) goto L5;
    L7:
        boolean r1 = false;
    L8:
        r0.isVideo = r1;
        r0.thumbPath = r5.thumbPath;
        long r12 = r5.duration * 1000;
        r0.duration = r12;
        r0.left = 0.0f;
        r0.right = Math.min(1.0f, 59000.0f / r12);
        if (r0.isVideo == true) goto L11;
    L13:
        r0.gradientTopColor = r5.gradientTopColor;
        r0.gradientBottomColor = r5.gradientBottomColor;
        r0.decodeBounds(r0.file.getAbsolutePath());
        int r13 = r5.width;
        if (r13 <= 0) goto L18;
        int r52 = r5.height;
        if (r52 <= 0) goto L18;
        r0.width = r13;
        r0.height = r52;
    L18:
        r0.setupMatrix();
        return r0;
    L11:
        if (r0.thumbPath != null) goto L13;
        r0.thumbPath = "vthumb://" + r5.imageId;
        goto L13
    L5:
        if (r5.isVideo == false) goto L7;
        r1 = true;
        goto L8
    }

    public static StoryEntry fromPhotoShoot(File r2, int r3) {
        StoryEntry r0 = new StoryEntry();
        r0.file = r2;
        r0.fileDeletable = true;
        r0.orientation = r3;
        r0.invert = 0;
        r0.isVideo = false;
        if (r2 == null) goto L5;
        r0.decodeBounds(r2.getAbsolutePath());
    L5:
        r0.setupMatrix();
        return r0;
    }

    public static StoryEntry fromStoryItem(File r9, TL_stories.StoryItem r10) {
        StoryEntry r0 = new StoryEntry();
        r0.isEdit = true;
        r0.editStoryId = r10.id;
        r0.file = r9;
        int r2 = 0;
        r0.fileDeletable = false;
        r0.width = 720;
        r0.height = 1280;
        TLRPC.MessageMedia r3 = r10.media;
        if ((r3 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L8;
        r0.isVideo = false;
        if (r9 == null) goto L38;
        r0.decodeBounds(r9.getAbsolutePath());
    L38:
        r0.privacyRules.clear();
        r0.privacyRules.addAll(StoryPrivacyBottomSheet.StoryPrivacy.toInput(r0.currentAccount, r10.privacy));
        r0.period = r10.expire_date - r10.date;
        CharSequence r32 = Emoji.replaceEmoji(new SpannableString(r10.caption), Theme.chat_msgTextPaint.getFontMetricsInt(), true);     // Catch: Exception -> L42
        MessageObject.addEntitiesToText(r32, r10.entities, true, false, true, false);     // Catch: Exception -> L42
        r0.caption = MessageObject.replaceAnimatedEmoji(r32, r10.entities, Theme.chat_msgTextPaint.getFontMetricsInt());     // Catch: Exception -> L42
    L40:
        r0.setupMatrix();
        r0.checkStickers(r10);
        r0.editedMediaAreas = r10.media_areas;
        r0.peer = MessagesController.getInstance(r0.currentAccount).getInputPeer(r10.dialogId);
        return r0;
    L8:
        if ((r3 instanceof TLRPC.TL_messageMediaDocument) == false) goto L38;
        r0.isVideo = true;
        TLRPC.Document r92 = r3.document;
        if (r92 != null) goto L12;
    L20:
        TLRPC.Document r93 = r10.media.document;
        if (r93 == null) goto L38;
        String r33 = r10.firstFramePath;
        if (r33 == null) goto L26;
        r0.thumbPath = r33;
        goto L38
    L26:
        if (r93.thumbs == null) goto L38;
    L28:
        if (r2 >= r10.media.document.thumbs.size()) goto L38;
        TLRPC.PhotoSize r94 = r10.media.document.thumbs.get(r2);
        if ((r94 instanceof TLRPC.TL_photoStrippedSize) == true) goto L31;
        File r95 = FileLoader.getInstance(r0.currentAccount).getPathToAttach(r94, true);
        if (r95 == null) goto L37;
        if (r95.exists() == false) goto L37;
        r0.thumbPath = r95.getAbsolutePath();
    L37:
        r2 = r2 + 1;
        goto L28
    L31:
        r0.thumbPathBitmap = ImageLoader.getStrippedPhotoBitmap(r94.bytes, null);
        goto L38
    L12:
        if (r92.attributes == null) goto L20;
        int r96 = 0;
    L15:
        if (r96 >= r10.media.document.attributes.size()) goto L20;
        TLRPC.DocumentAttribute r34 = r10.media.document.attributes.get(r96);
        if ((r34 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L18;
        r96 = r96 + 1;
        goto L15
    L18:
        r0.width = r34.w;
        r0.height = r34.h;
        r0.fileDuration = r34.duration;
        goto L20
    }

    public static StoryEntry fromVideoShoot(File r2, String r3, long r4) {
        StoryEntry r0 = new StoryEntry();
        r0.fromCamera = true;
        r0.file = r2;
        r0.fileDeletable = true;
        r0.orientation = 0;
        r0.invert = 0;
        r0.isVideo = true;
        r0.duration = r4;
        r0.thumbPath = r3;
        r0.left = 0.0f;
        r0.right = Math.min(1.0f, 59500.0f / r4);
        return r0;
    }

    public static long getCoverTime(TL_stories.StoryItem r4) {
        if (r4 != null) goto L5;
        return 0;
    L5:
        TLRPC.MessageMedia r42 = r4.media;
        if (r42 == null) goto L24;
        TLRPC.Document r43 = r42.document;
        if (r43 == null) goto L25;
        int r2 = 0;
    L12:
        if (r2 >= r43.attributes.size()) goto L17;
        if ((r43.attributes.get(r2) instanceof TLRPC.TL_documentAttributeVideo) == true) goto L15;
        r2 = r2 + 1;
        goto L12
    L15:
        TLRPC.TL_documentAttributeVideo r44 = (TLRPC.TL_documentAttributeVideo) r43.attributes.get(r2);
    L18:
        if (r44 != null) goto L21;
        return 0;
    L21:
        return (long) (r44.video_start_ts * 1000.0d);
    L17:
        r44 = null;
        goto L18
    L25:
        return 0;
    L24:
        return 0;
    }

    public static long getRepostDialogId(MessageObject r2) {
        Boolean r0 = useForwardForRepost(r2);
        if (r0 != null) goto L7;
        return 0;
    L7:
        if (r0.booleanValue() == false) goto L11;
        return DialogObject.getPeerDialogId(r2.messageOwner.fwd_from.from_id);
    L11:
        return r2.getDialogId();
    }

    public static int getRepostMessageId(MessageObject r1) {
        Boolean r0 = useForwardForRepost(r1);
        if (r0 != null) goto L7;
        return 0;
    L7:
        if (r0.booleanValue() == false) goto L11;
        return r1.messageOwner.fwd_from.channel_post;
    L11:
        return r1.getId();
    }

    public static Bitmap getScaledBitmap(DecodeBitmap r16, int r17, int r18, int r19, boolean r20, boolean r21) {
        if (r19 != 90) goto L5;
    L8:
        int r2 = r17;
        int r1 = r18;
    L9:
        BitmapFactory.Options r3 = new BitmapFactory.Options();
        r3.inJustDecodeBounds = true;
        r16.decode(r3);
        r3.inJustDecodeBounds = false;
        r3.inScaled = false;
        Runtime r6 = Runtime.getRuntime();
        long r7 = r6.maxMemory() - (r6.totalMemory() - r6.freeMemory());
        int r62 = r3.outWidth;
        int r9 = r3.outHeight;
        if (((((r62 * r9) * 4) + ((r1 * r2) * 4)) * 1.1d) > r7) goto L12;
        boolean r72 = true;
    L13:
        if (r62 > r1) goto L17;
        if (r9 > r2) goto L17;
        return r16.decode(r3);
    L17:
        if (r21 == false) goto L23;
        if (r72 == false) goto L23;
        if (SharedConfig.getDevicePerformanceClass() < 1) goto L23;
        Bitmap r0 = r16.decode(r3);
        float r12 = Math.max(r1 / r0.getWidth(), r2 / r0.getHeight());
        Bitmap r22 = Bitmap.createBitmap((int) (r0.getWidth() * r12), (int) (r0.getHeight() * r12), Bitmap.Config.ARGB_8888);
        Utilities.libyuvARGBSaleBitmap(r0, r22, Utilities.libyuv_ScaleFilter.Box);
        Utilities.clamp(Math.round(1.0f / r12), 8, 0);
        return r22;
    L23:
        r3.inScaled = true;
        int r5 = r3.outWidth;
        float r4 = r1 / r5;
        int r73 = r3.outHeight;
        if (r4 <= (r2 / r73)) goto L26;
        r3.inDensity = r5;
        r3.inTargetDensity = r1;
    L28:
        return r16.decode(r3);
    L26:
        r3.inDensity = r73;
        r3.inTargetDensity = r2;
        goto L28
    L12:
        r72 = false;
        goto L13
    L5:
        if (r19 == 270) goto L8;
        r1 = r17;
        r2 = r18;
        goto L9
    }

    public static Bitmap getScaledBitmap(DecodeBitmap r6, int r7, int r8, boolean r9, boolean r10) {
        return getScaledBitmap(r6, r7, r8, 0, r9, r10);
    }

    public static boolean isAnimated(TLRPC.Document r4, String r5) {
        if (r4 != null) goto L4;
        return false;
    L4:
        if ("video/webm".equals(r4.mime_type) == false) goto L6;
    L11:
        return true;
    L6:
        if ("video/mp4".equals(r4.mime_type) == true) goto L11;
        if (MessageObject.isAnimatedStickerDocument(r4, true) == true) goto L10;
        return false;
    L10:
        if (RLottieNative.getFramesCount(r5, null) > 1) goto L11;
        return false;
    }

    private /* synthetic */ Bitmap lambda$buildBitmap$0(BitmapFactory.Options r1) {
        return BitmapFactory.decodeFile(this.backgroundFile.getPath(), r1);
    }

    private /* synthetic */ Bitmap lambda$buildBitmap$3(BitmapFactory.Options r1) {
        return BitmapFactory.decodeFile(this.paintFile.getPath(), r1);
    }

    private /* synthetic */ Bitmap lambda$buildBitmap$4(BitmapFactory.Options r1) {
        return BitmapFactory.decodeFile(this.messageFile.getPath(), r1);
    }

    private /* synthetic */ Bitmap lambda$buildBitmap$5(BitmapFactory.Options r1) {
        return BitmapFactory.decodeFile(this.paintEntitiesFile.getPath(), r1);
    }

    private /* synthetic */ void lambda$checkStickers$14(TLObject r7) {
        this.checkStickersReqId = 0;
        if ((r7 instanceof Vector) == false) goto L21;
        this.editStickers = new ArrayList();
        Vector r72 = (Vector) r7;
        int r1 = 0;
    L6:
        if (r1 >= r72.objects.size()) goto L25;
        TLRPC.StickerSetCovered r2 = (TLRPC.StickerSetCovered) r72.objects.get(r1);
        TLRPC.Document r3 = r2.cover;
        if (r3 == null) goto L10;
    L12:
        if (r3 == null) goto L14;
    L18:
        if (r3 == null) goto L20;
        TLRPC.TL_inputDocument r22 = new TLRPC.TL_inputDocument();
        r22.id = r3.id;
        r22.access_hash = r3.access_hash;
        r22.file_reference = r3.file_reference;
        this.editStickers.add(r22);
    L20:
        r1 = r1 + 1;
        goto L6
    L14:
        if ((r2 instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L18;
        TLRPC.TL_stickerSetFullCovered r23 = (TLRPC.TL_stickerSetFullCovered) r2;
        if (r23.documents.isEmpty() == true) goto L18;
        r3 = r23.documents.get(0);
        goto L18
    L10:
        if (r2.covers.isEmpty() == true) goto L12;
        r3 = r2.covers.get(0);
        goto L12
    L25:
        return;
    }

    private /* synthetic */ void lambda$checkStickers$15(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new StoryEntry$$ExternalSyntheticLambda16(this, r1));
    }

    private /* synthetic */ void lambda$checkStickers$16(TL_stories.StoryItem r2, TLRPC.TL_messages_getAttachedStickers r3, RequestDelegate r4, TLObject r5, TLRPC.TL_error r6) {
        if (r6 != null) goto L4;
    L8:
        r4.run(r5, r6);
        return;
    L4:
        if (FileRefController.isFileRefError(r6.text) == false) goto L8;
        if (r2 == null) goto L8;
        FileRefController.getInstance(this.currentAccount).requestReference(r2, new Object[]{r3, r4});
    }

    private /* synthetic */ void lambda$detectHDR$12(Utilities.Callback r1) {
        r1.run(this.hdrInfo);
    }

    private /* synthetic */ void lambda$detectHDR$13(Utilities.Callback r7) {
        HDRInfo r3 = this.hdrInfo;     // Catch: Throwable -> L6 Exception -> L8
        if (r3 != null) goto L10;
        r3 = new HDRInfo();     // Catch: Throwable -> L6 Exception -> L8
        this.hdrInfo = r3;     // Catch: Throwable -> L6 Exception -> L8
        r3.maxlum = 1000.0f;     // Catch: Throwable -> L6 Exception -> L8
        r3.minlum = 0.001f;     // Catch: Throwable -> L6 Exception -> L8
    L10:
        MediaExtractor r4 = new MediaExtractor();     // Catch: Throwable -> L6 Exception -> L8
        r4.setDataSource(this.file.getAbsolutePath());     // Catch: Throwable -> L6 Exception -> L8
        int r5 = MediaController.findTrack(r4, false);     // Catch: Throwable -> L6 Exception -> L8
        r4.selectTrack(r5);     // Catch: Throwable -> L6 Exception -> L8
        MediaFormat r42 = r4.getTrackFormat(r5);     // Catch: Throwable -> L6 Exception -> L8
        if (r42.containsKey("color-transfer") == false) goto L14;
        r3.colorTransfer = r42.getInteger("color-transfer");     // Catch: Throwable -> L6 Exception -> L8
    L14:
        if (r42.containsKey("color-standard") == false) goto L17;
        r3.colorStandard = r42.getInteger("color-standard");     // Catch: Throwable -> L6 Exception -> L8
    L17:
        if (r42.containsKey("color-range") == false) goto L19;
        r3.colorRange = r42.getInteger("color-range");     // Catch: Throwable -> L6 Exception -> L8
    L19:
        this.hdrInfo = this.hdrInfo;
        StoryEntry$$ExternalSyntheticLambda10 r0 = new StoryEntry$$ExternalSyntheticLambda10(this, r7);
    L20:
        AndroidUtilities.runOnUIThread(r0);
        return;
    L8:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L6
        this.hdrInfo = this.hdrInfo;
        r0 = new StoryEntry$$ExternalSyntheticLambda10(this, r7);
    L6:
        th = move-exception;
        this.hdrInfo = this.hdrInfo;
        AndroidUtilities.runOnUIThread(new StoryEntry$$ExternalSyntheticLambda10(this, r7));
        throw th;
    }

    private /* synthetic */ void lambda$getVideoEditedInfo$9(String r24, int[][] r25, Utilities.Callback r26) {
        VideoEditedInfo r2 = new VideoEditedInfo();
        r2.isStory = true;
        r2.fromCamera = this.fromCamera;
        r2.originalWidth = this.width;
        r2.originalHeight = this.height;
        r2.resultWidth = this.resultWidth;
        r2.resultHeight = this.resultHeight;
        File r4 = this.paintFile;
        if (r4 != null) goto L5;
        String r42 = null;
    L6:
        r2.paintPath = r42;
        File r43 = this.messageFile;
        if (r43 != null) goto L9;
        String r44 = null;
    L10:
        r2.messagePath = r44;
        File r45 = this.messageVideoMaskFile;
        if (r45 != null) goto L13;
        String r46 = null;
    L14:
        r2.messageVideoMaskPath = r46;
        File r47 = this.backgroundFile;
        if (r47 != null) goto L17;
        String r48 = null;
    L18:
        r2.backgroundPath = r48;
        int r49 = MediaController.extractRealEncoderBitrate(r2.resultWidth, r2.resultHeight, r2.bitrate, true);
        int r15 = 0;
        if (this.isVideo == false) goto L42;
        if (r24 == null) goto L42;
        if (isCollage() == true) goto L42;
        r2.originalPath = r24;
        r2.isPhoto = false;
        r2.framerate = Math.min(59, r25[0][7]);
        int r1 = MediaController.getVideoBitrate(r24);
        if (r1 != (-1)) goto L26;
        r1 = r25[0][3];
    L26:
        r2.originalBitrate = r1;
        if (r1 >= 1000000) goto L33;
        ArrayList<VideoEditedInfo.MediaEntity> r12 = this.mediaEntities;
        if (r12 == null) goto L33;
        if (r12.isEmpty() == true) goto L33;
        r2.bitrate = 2000000;
        r2.originalBitrate = -1;
    L37:
        FileLog.d("story bitrate, original = " + r2.originalBitrate + " => " + r2.bitrate);
        int r6 = r25[0][4];
        long r8 = (long) r6;
        this.duration = r8;
        r2.originalDuration = r8 * 1000;
        long r122 = ((long) (this.left * r8)) * 1000;
        r2.startTime = r122;
        long r82 = ((long) (this.right * r8)) * 1000;
        r2.endTime = r82;
        r2.estimatedDuration = r82 - r122;
        r2.volume = this.videoVolume;
        r2.muted = this.muted;
        r2.estimatedSize = (long) (r1[5] + (((r6 / 1000.0f) * r49) / 8.0f));
        r2.estimatedSize = Math.max(this.file.length(), r2.estimatedSize);
        r2.filterState = this.filterState;
        File r13 = this.paintBlurFile;
        if (r13 != null) goto L40;
        String r5 = null;
    L41:
        r2.blurPath = r5;
        long r21 = 0;
    L86:
        r2.account = this.currentAccount;
        r2.wallpaperPeerId = this.backgroundWallpaperPeerId;
        r2.isDark = this.isDark;
        r2.avatarStartTime = -1;
        MediaController.CropState r14 = this.crop;
        if (r14 == null) goto L89;
        r2.cropState = r14.m4357clone();
    L90:
        r2.cropState.useMatrix = new Matrix();
        r2.cropState.useMatrix.set(this.matrix);
        r2.mediaEntities = this.mediaEntities;
        r2.gradientTopColor = Integer.valueOf(this.gradientTopColor);
        r2.gradientBottomColor = Integer.valueOf(this.gradientBottomColor);
        r2.forceFragmenting = true;
        r2.hdrInfo = this.hdrInfo;
        r2.mixedSoundInfos.clear();
        if (isCollage() == true) goto L93;
    L103:
        File r16 = this.round;
        if (r16 == null) goto L113;
        MediaCodecVideoConvertor.MixedSoundInfo r3 = new MediaCodecVideoConvertor.MixedSoundInfo(r16.getAbsolutePath());
        r3.volume = this.roundVolume;
        float r17 = this.roundLeft;
        long r410 = this.roundDuration;
        long r62 = ((long) (r410 * r17)) * 1000;
        r3.audioOffset = r62;
        if (this.isVideo == false) goto L108;
        r3.startTime = ((long) (this.roundOffset - (this.left * this.duration))) * 1000;
        long r83 = 0;
    L109:
        long r10 = r3.startTime + r21;
        r3.startTime = r10;
        if (r10 >= r83) goto L112;
        r3.audioOffset = r62 - r10;
        r3.startTime = r83;
    L112:
        r3.duration = ((long) ((this.roundRight - r17) * r410)) * 1000;
        r2.mixedSoundInfos.add(r3);
        goto L113
    L108:
        r83 = 0;
        r3.startTime = 0;
    L113:
        String r18 = this.audioPath;
        if (r18 == null) goto L123;
        MediaCodecVideoConvertor.MixedSoundInfo r32 = new MediaCodecVideoConvertor.MixedSoundInfo(r18);
        r32.volume = this.audioVolume;
        float r19 = this.audioLeft;
        long r411 = this.audioDuration;
        long r63 = ((long) (r411 * r19)) * 1000;
        r32.audioOffset = r63;
        if (this.isVideo == false) goto L118;
        r32.startTime = ((long) (this.audioOffset - (this.left * this.duration))) * 1000;
        long r84 = 0;
    L119:
        long r102 = r32.startTime + r21;
        r32.startTime = r102;
        if (r102 >= r84) goto L122;
        r32.audioOffset = r63 - r102;
        r32.startTime = r84;
    L122:
        r32.duration = ((long) ((this.audioRight - r19) * r411)) * 1000;
        r2.mixedSoundInfos.add(r32);
        goto L123
    L118:
        r84 = 0;
        r32.startTime = 0;
    L123:
        r26.run(r2);
        return;
    L93:
        if (this.muted == true) goto L103;
        ArrayList<VideoEditedInfo.Part> r110 = r2.collageParts;
        int r33 = r110.size();
    L95:
        if (r15 >= r33) goto L103;
        VideoEditedInfo.Part r412 = r110.get(r15);
        r15 = r15 + 1;
        VideoEditedInfo.Part r413 = r412;
        if (r413.isVideo == false) goto L95;
        if (r413.volume <= 0.0f) goto L95;
        if (r413.muted == true) goto L95;
        MediaCodecVideoConvertor.MixedSoundInfo r52 = new MediaCodecVideoConvertor.MixedSoundInfo(r413.path);
        r52.volume = r413.volume;
        float r64 = r413.left;
        long r7 = r413.duration;
        r52.audioOffset = ((long) (r7 * r64)) * 1000;
        r52.startTime = r413.offset * 1000;
        r52.duration = ((long) ((r413.right - r64) * r7)) * 1000;
        r2.mixedSoundInfos.add(r52);
        goto L95
    L89:
        r2.cropState = new MediaController.CropState();
        goto L90
    L40:
        r5 = r13.getPath();
    L33:
        int r111 = r2.originalBitrate;
        if (r111 >= 500000) goto L36;
        r2.bitrate = 2500000;
        r2.originalBitrate = -1;
        goto L37
    L36:
        r2.bitrate = Utilities.clamp(r111, MediaController.VIDEO_BITRATE_360, 500000);
    L42:
        float r72 = 1000.0f;
        File r65 = this.filterFile;
        if (r65 == null) goto L45;
        r2.originalPath = r65.getAbsolutePath();
    L46:
        r2.isPhoto = true;
        r2.collage = this.collage;
        if (isCollage() == false) goto L77;
        int r112 = 0;
        boolean r66 = false;
    L50:
        if (r112 >= this.collageContent.size()) goto L55;
        StoryEntry r85 = this.collageContent.get(r112);
        if (r85.isVideo == false) goto L54;
        r85.width = Math.max(r85.width, r25[r112][1]);
        r85.height = Math.max(r85.height, r25[r112][2]);
        r85.duration = Math.max(r85.duration, r25[r112][4]);
        r66 = true;
    L54:
        r112 = r112 + 1;
        goto L50
    L55:
        ArrayList<VideoEditedInfo.Part> r113 = VideoEditedInfo.Part.toParts(this);
        r2.collageParts = r113;
        if (r66 == true) goto L58;
        long r53 = this.averageDuration;
        this.duration = r53;
        r2.originalDuration = r53;
        r2.estimatedDuration = r53;
        float r192 = 1000.0f;
    L84:
        r21 = 0;
    L85:
        r2.startTime = -1;
        r2.endTime = -1;
        r2.muted = true;
        r2.originalBitrate = -1;
        r2.volume = 1.0f;
        r2.bitrate = -1;
        r2.framerate = 30;
        r2.estimatedSize = (long) (((this.duration / r192) * r49) / 8.0f);
        r2.filterState = null;
        goto L86
    L58:
        int r54 = r113.size();
        int r132 = 0;
        VideoEditedInfo.Part r67 = null;
        long r86 = 0;
    L59:
        if (r132 >= r54) goto L66;
        VideoEditedInfo.Part r142 = r113.get(r132);
        int r133 = r132 + 1;
        VideoEditedInfo.Part r143 = r142;
        float r193 = r72;
        if (r143.isVideo == false) goto L65;
        long r123 = r143.duration;
        if (r123 <= r86) goto L65;
        r86 = r123;
        r67 = r143;
    L65:
        r132 = r133;
        r72 = r193;
        goto L59
    L66:
        r192 = r72;
        if (r67 == null) goto L84;
        long r87 = r67.duration;
        float r55 = r67.right;
        float r124 = r67.left;
        long r134 = (long) (r87 * (r55 - r124));
        this.duration = r134;
        r2.originalDuration = r134;
        r2.estimatedDuration = r134;
        long r88 = -(r67.offset + ((long) (r124 * r87)));
        r67.offset = r88;
        ArrayList<VideoEditedInfo.Part> r114 = r2.collageParts;
        int r56 = r114.size();
        int r125 = 0;
    L69:
        if (r125 >= r56) goto L76;
        VideoEditedInfo.Part r135 = r114.get(r125);
        r125 = r125 + 1;
        VideoEditedInfo.Part r136 = r135;
        if (r136.isVideo == false) goto L74;
        if (r136 == r67) goto L74;
        long r212 = r88;
        r136.offset += r212;
    L75:
        r88 = r212;
    L74:
        r212 = r88;
        goto L75
    L76:
        r21 = r88;
        goto L85
    L77:
        r192 = 1000.0f;
        if (this.round == null) goto L81;
        long r57 = (long) ((this.roundRight - this.roundLeft) * this.roundDuration);
        this.duration = r57;
        r2.originalDuration = r57;
        r2.estimatedDuration = r57;
        goto L84
    L81:
        if (this.audioPath == null) goto L83;
        long r58 = (long) ((this.audioRight - this.audioLeft) * this.audioDuration);
        this.duration = r58;
        r2.originalDuration = r58;
        r2.estimatedDuration = r58;
        goto L84
    L83:
        long r59 = this.averageDuration;
        this.duration = r59;
        r2.originalDuration = r59;
        r2.estimatedDuration = r59;
        goto L84
    L45:
        r2.originalPath = r24;
        goto L46
    L17:
        r48 = r47.getPath();
        goto L18
    L13:
        r46 = r45.getPath();
        goto L14
    L9:
        r44 = r43.getPath();
        goto L10
    L5:
        r42 = r4.getPath();
        goto L6
    }

    private /* synthetic */ void lambda$setupGradient$7(Bitmap r2, Runnable r3, int[] r4) {
        this.gradientTopColor = r4[0];
        this.gradientBottomColor = r4[1];
        r2.recycle();
        if (r3 == null) goto L6;
        r3.run();
        return;
    }

    private /* synthetic */ void lambda$setupGradient$8(Runnable r2, int[] r3) {
        this.gradientTopColor = r3[0];
        this.gradientBottomColor = r3[1];
        if (r2 == null) goto L6;
        r2.run();
        return;
    }

    private /* synthetic */ void lambda$updateFilter$6(Bitmap r4, boolean r5, Runnable r6) {
        FileOutputStream r1 = new FileOutputStream(this.filterFile);     // Catch: Exception -> L6
        if (r5 == false) goto L8;
        Bitmap.CompressFormat r2 = Bitmap.CompressFormat.WEBP;     // Catch: Exception -> L6
    L9:
        r4.compress(r2, 90, r1);     // Catch: Exception -> L6
    L17:
        r4.recycle();
        AndroidUtilities.runOnUIThread(r6);
        return;
    L8:
        r2 = Bitmap.CompressFormat.JPEG;     // Catch: Exception -> L6
    L6:
        e = move-exception;
        FileLog.e(e);
        if (r5 == false) goto L17;
        FileOutputStream r52 = new FileOutputStream(this.filterFile);     // Catch: Exception -> L15
        r4.compress(Bitmap.CompressFormat.PNG, 90, r52);     // Catch: Exception -> L15
    L15:
        e = move-exception;
        FileLog.e(e);
        goto L17
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11, types: [org.telegram.tgnet.TLRPC$PhotoSize, org.telegram.tgnet.TLRPC$TL_photoSize_layer127] */
    public static File makeCacheFile(int r3, String r4) {
        TLRPC.TL_fileLocationToBeDeprecated r0 = new TLRPC.TL_fileLocationToBeDeprecated();
        r0.volume_id = -2147483648L;
        r0.dc_id = Integer.MIN_VALUE;
        r0.local_id = SharedConfig.getLastLocalId();
        r0.file_reference = new byte[0];
        if ("mp4".equals(r4) == false) goto L5;
    L8:
        TLRPC.TL_videoSize_layer127 r1 = new TLRPC.TL_videoSize_layer127();
        r1.location = r0;
        TLRPC.TL_videoSize_layer127 r12 = r1;
    L10:
        return FileLoader.getInstance(r3).getPathToAttach(r12, r4, true);
    L5:
        if ("webm".equals(r4) == true) goto L8;
        ?? r13 = new TLRPC.TL_photoSize_layer127();
        r13.location = r0;
        r12 = r13;
        goto L10
    }

    public static File makeCacheFile(int r0, boolean r1) {
        if (r1 == false) goto L4;
        String r12 = "mp4";
    L6:
        return makeCacheFile(r0, r12);
    L4:
        r12 = "jpg";
        goto L6
    }

    public static StoryEntry repostMessage(ArrayList<MessageObject> r5) {
        StoryEntry r0 = new StoryEntry();
        r0.isRepostMessage = true;
        r0.messageObjects = r5;
        r0.resultWidth = 1080;
        r0.resultHeight = 1920;
        r0.backgroundWallpaperPeerId = getRepostDialogId(r5.get(0));
        VideoEditedInfo.MediaEntity r3 = new VideoEditedInfo.MediaEntity();
        r3.type = 6;
        r3.x = 0.5f;
        r3.y = 0.5f;
        ArrayList<VideoEditedInfo.MediaEntity> r4 = new ArrayList();
        r0.mediaEntities = r4;
        r4.add(r3);
        if (r5.size() != 1) goto L29;
        MessageObject r52 = r5.get(0);
        if (r52 == null) goto L29;
        int r32 = r52.type;
        if (r32 != 8) goto L9;
    L12:
        TLRPC.Message r33 = r52.messageOwner;
        if (r33 != null) goto L15;
    L17:
        File r34 = r0.file;
        if (r34 != null) goto L20;
    L21:
        r0.file = FileLoader.getInstance(r0.currentAccount).getPathToMessage(r52.messageOwner);
    L22:
        File r35 = r0.file;
        if (r35 != null) goto L25;
    L28:
        r0.file = null;
        goto L29
    L25:
        if (r35.exists() == false) goto L28;
        r0.isVideo = true;
        r0.fileDeletable = false;
        long r1 = (long) (r52.getDuration() * 1000.0d);
        r0.duration = r1;
        r0.left = 0.0f;
        r0.right = Math.min(1.0f, 59500.0f / r1);
        return r0;
    L20:
        if (r34.exists() == true) goto L22;
    L15:
        if (r33.attachPath == null) goto L17;
        r0.file = new File(r52.messageOwner.attachPath);
        goto L17
    L9:
        if (r32 == 3) goto L12;
        if (r32 == 5) goto L12;
    L29:
        return r0;
    }

    public static StoryEntry repostStoryItem(File r5, TL_stories.StoryItem r6) {
        StoryEntry r0 = new StoryEntry();
        r0.isRepost = true;
        r0.repostMedia = r6.media;
        r0.repostPeer = MessagesController.getInstance(r0.currentAccount).getPeer(r6.dialogId);
        r0.repostStoryId = r6.id;
        r0.repostCaption = r6.caption;
        r0.file = r5;
        int r2 = 0;
        r0.fileDeletable = false;
        r0.width = 720;
        r0.height = 1280;
        TLRPC.MessageMedia r3 = r6.media;
        if ((r3 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L8;
        r0.isVideo = false;
        if (r5 == null) goto L38;
        r0.decodeBounds(r5.getAbsolutePath());
    L38:
        r0.setupMatrix();
        r0.checkStickers(r6);
        return r0;
    L8:
        if ((r3 instanceof TLRPC.TL_messageMediaDocument) == false) goto L38;
        r0.isVideo = true;
        TLRPC.Document r52 = r3.document;
        if (r52 != null) goto L12;
    L20:
        TLRPC.Document r53 = r6.media.document;
        if (r53 == null) goto L38;
        String r32 = r6.firstFramePath;
        if (r32 == null) goto L26;
        r0.thumbPath = r32;
        goto L38
    L26:
        if (r53.thumbs == null) goto L38;
    L28:
        if (r2 >= r6.media.document.thumbs.size()) goto L38;
        TLRPC.PhotoSize r54 = r6.media.document.thumbs.get(r2);
        if ((r54 instanceof TLRPC.TL_photoStrippedSize) == false) goto L32;
        r0.thumbPathBitmap = ImageLoader.getStrippedPhotoBitmap(r54.bytes, null);
    L37:
        r2 = r2 + 1;
        goto L28
    L32:
        File r55 = FileLoader.getInstance(r0.currentAccount).getPathToAttach(r54, true);
        if (r55 == null) goto L37;
        if (r55.exists() == false) goto L37;
        r0.thumbPath = r55.getAbsolutePath();
        goto L37
    L12:
        if (r52.attributes == null) goto L20;
        int r56 = 0;
    L15:
        if (r56 >= r6.media.document.attributes.size()) goto L20;
        TLRPC.DocumentAttribute r33 = r6.media.document.attributes.get(r56);
        if ((r33 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L18;
        r56 = r56 + 1;
        goto L15
    L18:
        r0.width = r33.w;
        r0.height = r33.h;
        r0.fileDuration = r33.duration;
        goto L20
    }

    public static void setupScale(BitmapFactory.Options r7, int r8, int r9) {
        Runtime r92 = Runtime.getRuntime();
        long r0 = r92.maxMemory() - (r92.totalMemory() - r92.freeMemory());
        int r93 = r7.outWidth;
        int r2 = r7.outHeight;
        if (((r93 * r2) * 8) <= r0) goto L5;
    L10:
        r7.inScaled = true;
        r7.inDensity = r7.outWidth;
        r7.inTargetDensity = r8;
        return;
    L5:
        if (Math.max(r93, r2) > 4200) goto L10;
        if (SharedConfig.getDevicePerformanceClass() <= 0) goto L10;
    }

    public static Boolean useForwardForRepost(MessageObject r5) {
        if (r5 == null) goto L32;
        TLRPC.Message r1 = r5.messageOwner;
        if (r1 == null) goto L32;
        long r12 = DialogObject.getPeerDialogId(r1.peer_id);
        TLRPC.Chat r13 = MessagesController.getInstance(r5.currentAccount).getChat(Long.valueOf(-r12));
        if (r13 == null) goto L12;
        if (r13.noforwards == false) goto L12;
    L13:
        TLRPC.MessageFwdHeader r14 = r5.messageOwner.fwd_from;
        if (r14 == null) goto L29;
        TLRPC.Peer r2 = r14.from_id;
        if (r2 == null) goto L29;
        if ((r14.flags & 4) == 0) goto L29;
        long r15 = DialogObject.getPeerDialogId(r2);
        TLRPC.Chat r52 = MessagesController.getInstance(r5.currentAccount).getChat(Long.valueOf(-r15));
        if (r15 >= 0) goto L29;
        if (r52 == null) goto L25;
        if (r52.noforwards == true) goto L29;
    L25:
        if (ChatObject.isChannelAndNotMegaGroup(r52) == false) goto L29;
        return Boolean.TRUE;
    L29:
        return null;
    L12:
        if (ChatObject.isChannelAndNotMegaGroup(r13) == false) goto L13;
        return Boolean.FALSE;
    L32:
        return null;
    }

    public Bitmap buildBitmap(float r28, Bitmap r29) {
        Matrix r4 = new Matrix();
        Paint r5 = new Paint(7);
        int r7 = (int) (this.resultWidth * r28);
        int r8 = (int) (this.resultHeight * r28);
        Bitmap r12 = Bitmap.createBitmap(r7, r8, Bitmap.Config.ARGB_8888);
        Canvas r13 = new Canvas(r12);
        char r6 = 2;
        Object r9 = null;
        boolean r11 = true;
        if (this.backgroundFile != null) goto L115;
        String r0 = this.backgroundWallpaperEmoticon;
        if (r0 == null) goto L14;
        Drawable r10 = this.backgroundDrawable;
        if (r10 != null) goto L13;
        r10 = PreviewView.getBackgroundDrawableFromTheme(this.currentAccount, r0, this.isDark);
    L13:
        drawBackgroundDrawable(r13, r10, r13.getWidth(), r13.getHeight());
    L5:
        float r102 = 0.0f;
    L21:
        r4.set(this.matrix);
        if (r29 == null) goto L25;
        float r02 = this.width / r29.getWidth();
        r4.preScale(r02, r02);
        r4.postScale(r28, r28);
        r13.drawBitmap(r29, r4, r5);
    L98:
        return r12;
    L25:
        if (isCollage() == false) goto L65;
        int r3 = 0;
    L28:
        if (r3 >= this.collageContent.size()) goto L64;
        StoryEntry r03 = this.collageContent.get(r3);
        File r14 = r03.filterFile;
        if (r14 != null) goto L33;
        r14 = r03.file;
    L33:
        if (r14 != null) goto L121;
        int r20 = r3;
        char r18 = r6;
        int r22 = r7;
        Object r32 = r9;
    L63:
        r9 = r32;
        r6 = r18;
        r7 = r22;
        r11 = true;
        r3 = r20 + 1;
        goto L28
    L121:
        Bitmap r04 = getScaledBitmap(new StoryEntry$$ExternalSyntheticLambda5(r14), r7, r8, r11, r11);     // Catch: Exception -> L59
        r13.save();     // Catch: Exception -> L59
        RectF r15 = new RectF();     // Catch: Exception -> L59
        int r16 = r04.getWidth();     // Catch: Exception -> L59
        int r17 = r04.getHeight();     // Catch: Exception -> L59
        r18 = r6;
        if (((((Integer) AndroidUtilities.getImageOrientation(r14).first).intValue() / 90) % 2) != r11) goto L39;
        r16 = r04.getHeight();     // Catch: Exception -> L40
        r17 = r04.getWidth();     // Catch: Exception -> L40
    L39:
        int r62 = r16;
        int r112 = r17;
        r20 = r3;
        this.collage.parts.get(r3).bounds(r15, r7, r8);     // Catch: Exception -> L55
        r13.translate(r15.centerX(), r15.centerY());     // Catch: Exception -> L55
        r22 = r7;
        r13.clipRect((-r15.width()) / 2.0f, (-r15.height()) / 2.0f, r15.width() / 2.0f, r15.height() / 2.0f);     // Catch: Exception -> L53
        float r33 = Math.max(r15.width() / r62, r15.height() / r112);     // Catch: Exception -> L53
        r13.scale(r33, r33);     // Catch: Exception -> L53
        r13.rotate(((Integer) r14.first).intValue());     // Catch: Exception -> L53
        r13.translate((-r04.getWidth()) / 2.0f, (-r04.getHeight()) / 2.0f);     // Catch: Exception -> L53
        r32 = null;
        r102 = 0.0f;
        r13.drawBitmap(r04, 0.0f, 0.0f, null);     // Catch: Exception -> L51
        r13.restore();     // Catch: Exception -> L51
    L51:
        e = e;
    L61:
        FileLog.e(e);
    L53:
        e = e;
    L54:
        r32 = null;
        r102 = 0.0f;
    L55:
        e = e;
        r22 = r7;
    L57:
        e = e;
        r20 = r3;
        r22 = r7;
        r32 = null;
    L40:
        e = e;
        r20 = r3;
    L42:
        r22 = r7;
        r32 = r9;
    L59:
        e = e;
        r20 = r3;
        r18 = r6;
        goto L42
    L64:
        float r152 = r102;
        boolean r142 = r11;
        boolean r34 = false;
    L81:
        if (this.paintFile == null) goto L87;
        Bitmap r05 = getScaledBitmap(new StoryEntry$$ExternalSyntheticLambda7(this), r7, r8, r34, r142);     // Catch: Exception -> L84
        r13.save();     // Catch: Exception -> L84
        float r63 = this.resultWidth / r05.getWidth();     // Catch: Exception -> L84
        r13.scale(r63, r63);     // Catch: Exception -> L84
        r4.postScale(r28, r28);     // Catch: Exception -> L84
        r13.drawBitmap(r05, r152, r152, r5);     // Catch: Exception -> L84
        r13.restore();     // Catch: Exception -> L84
        r05.recycle();     // Catch: Exception -> L84
    L84:
        e = move-exception;
        FileLog.e(e);
    L87:
        if (this.messageFile == null) goto L93;
        Bitmap r06 = getScaledBitmap(new StoryEntry$$ExternalSyntheticLambda8(this), r7, r8, r34, r142);     // Catch: Exception -> L90
        r13.save();     // Catch: Exception -> L90
        float r64 = this.resultWidth / r06.getWidth();     // Catch: Exception -> L90
        r13.scale(r64, r64);     // Catch: Exception -> L90
        r4.postScale(r28, r28);     // Catch: Exception -> L90
        r13.drawBitmap(r06, r152, r152, r5);     // Catch: Exception -> L90
        r13.restore();     // Catch: Exception -> L90
        r06.recycle();     // Catch: Exception -> L90
    L90:
        e = move-exception;
        FileLog.e(e);
    L93:
        if (this.paintEntitiesFile == null) goto L98;
        Bitmap r07 = getScaledBitmap(new StoryEntry$$ExternalSyntheticLambda9(this), r7, r8, r34, r142);     // Catch: Exception -> L96
        r13.save();     // Catch: Exception -> L96
        float r1 = this.resultWidth / r07.getWidth();     // Catch: Exception -> L96
        r13.scale(r1, r1);     // Catch: Exception -> L96
        r4.postScale(r28, r28);     // Catch: Exception -> L96
        r13.drawBitmap(r07, r152, r152, r5);     // Catch: Exception -> L96
        r13.restore();     // Catch: Exception -> L96
        r07.recycle();     // Catch: Exception -> L96
    L96:
        e = move-exception;
        FileLog.e(e);
        goto L98
    L65:
        File r08 = this.filterFile;
        if (r08 != null) goto L69;
        r08 = this.file;
    L69:
        if (r08 != null) goto L107;
        r152 = r102;
        r7 = r7;
        r34 = false;
        r142 = true;
        goto L81
    L107:
        r152 = r102;
        r7 = r7;
        r34 = false;
        r142 = true;
        Bitmap r09 = getScaledBitmap(new StoryEntry$$ExternalSyntheticLambda6(r08), r7, r8, this.orientation, true, true);     // Catch: Exception -> L74
        float r65 = this.width / r09.getWidth();     // Catch: Exception -> L74
        r4.preScale(r65, r65);     // Catch: Exception -> L74
        r4.postScale(r28, r28);     // Catch: Exception -> L74
        r13.drawBitmap(r09, r4, r5);     // Catch: Exception -> L74
        r09.recycle();     // Catch: Exception -> L74
    L74:
        e = e;
    L78:
        FileLog.e(e);
    L76:
        e = e;
        r152 = r102;
        r7 = r7;
        r34 = false;
        r142 = true;
        goto L78
    L14:
        long r143 = this.backgroundWallpaperPeerId;
        if (r143 == Long.MIN_VALUE) goto L20;
        Drawable r010 = this.backgroundDrawable;
        if (r010 != null) goto L19;
        r010 = PreviewView.getBackgroundDrawable(null, this.currentAccount, r143, this.isDark);
    L19:
        drawBackgroundDrawable(r13, r010, r13.getWidth(), r13.getHeight());
        r102 = 0.0f;
        goto L21
    L20:
        Paint r011 = new Paint(1);
        r011.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, r13.getHeight(), new int[]{this.gradientTopColor, this.gradientBottomColor}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
        r102 = 0.0f;
        r13.drawRect(0.0f, 0.0f, r13.getWidth(), r13.getHeight(), r011);
        goto L21
    L115:
        Bitmap r012 = getScaledBitmap(new StoryEntry$$ExternalSyntheticLambda4(this), r7, r8, false, true);     // Catch: Exception -> L6
        r13.save();     // Catch: Exception -> L6
        float r153 = this.resultWidth / r012.getWidth();     // Catch: Exception -> L6
        r13.scale(r153, r153);     // Catch: Exception -> L6
        r4.postScale(r28, r28);     // Catch: Exception -> L6
        r13.drawBitmap(r012, 0.0f, 0.0f, r5);     // Catch: Exception -> L6
        r13.restore();     // Catch: Exception -> L6
        r012.recycle();     // Catch: Exception -> L6
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L5
    }

    public void buildPhoto(File r5) {
        Bitmap r0 = buildBitmap(1.0f, null);
        Bitmap r2 = this.thumbBitmap;
        if (r2 == null) goto L5;
        r2.recycle();
        this.thumbBitmap = null;
    L5:
        this.thumbBitmap = Bitmap.createScaledBitmap(r0, 40, 22, true);
        FileOutputStream r4 = new FileOutputStream(r5);     // Catch: Exception -> L8
        r0.compress(Bitmap.CompressFormat.JPEG, 95, r4);     // Catch: Exception -> L8
        r4.close();     // Catch: Exception -> L8
    L10:
        r0.recycle();
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L10
    }

    public void cancelCheckStickers() {
        if (this.checkStickersReqId == 0) goto L6;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.checkStickersReqId, true);
        return;
    }

    public void checkStickers(TL_stories.StoryItem r8) {
        if (r8 != null) goto L4;
        return;
    L4:
        if (r8.media == null) goto L28;
        TLRPC.TL_messages_getAttachedStickers r0 = new TLRPC.TL_messages_getAttachedStickers();
        TLRPC.MessageMedia r1 = r8.media;
        TLRPC.Photo r2 = r1.photo;
        if (r2 != null) goto L9;
        TLRPC.Document r12 = r1.document;
        if (r12 != null) goto L18;
        return;
    L18:
        if (MessageObject.isDocumentHasAttachedStickers(r12) == false) goto L30;
        TLRPC.TL_inputStickeredMediaDocument r22 = new TLRPC.TL_inputStickeredMediaDocument();
        TLRPC.TL_inputDocument r4 = new TLRPC.TL_inputDocument();
        r22.id = r4;
        r4.id = r12.id;
        r4.access_hash = r12.access_hash;
        byte[] r13 = r12.file_reference;
        r4.file_reference = r13;
        if (r13 != null) goto L23;
        r4.file_reference = new byte[0];
    L23:
        r0.media = r22;
    L24:
        StoryEntry$$ExternalSyntheticLambda14 r14 = new StoryEntry$$ExternalSyntheticLambda14(this);
        this.checkStickersReqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StoryEntry$$ExternalSyntheticLambda15(this, r8, r0, r14));
        return;
    L30:
        return;
    L9:
        if (r2.has_stickers == false) goto L29;
        TLRPC.TL_inputStickeredMediaPhoto r15 = new TLRPC.TL_inputStickeredMediaPhoto();
        TLRPC.TL_inputPhoto r42 = new TLRPC.TL_inputPhoto();
        r15.id = r42;
        r42.id = r2.id;
        r42.access_hash = r2.access_hash;
        byte[] r23 = r2.file_reference;
        r42.file_reference = r23;
        if (r23 != null) goto L14;
        r42.file_reference = new byte[0];
    L14:
        r0.media = r15;
        goto L24
    L29:
        return;
    }

    public void clearFilter() {
        File r0 = this.filterFile;
        if (r0 == null) goto L6;
        r0.delete();
        this.filterFile = null;
        return;
    }

    public void clearPaint() {
        File r0 = this.paintFile;
        if (r0 == null) goto L5;
        r0.delete();
        this.paintFile = null;
    L5:
        File r02 = this.backgroundFile;
        if (r02 == null) goto L8;
        r02.delete();
        this.backgroundFile = null;
    L8:
        File r03 = this.messageFile;
        if (r03 == null) goto L11;
        r03.delete();
        this.messageFile = null;
    L11:
        File r04 = this.messageVideoMaskFile;
        if (r04 == null) goto L14;
        r04.delete();
        this.messageVideoMaskFile = null;
    L14:
        File r05 = this.paintEntitiesFile;
        if (r05 == null) goto L18;
        r05.delete();
        this.paintEntitiesFile = null;
        return;
    }

    public StoryEntry copy() {
        return copy(false);
    }

    public StoryEntry copy(boolean r4) {
        StoryEntry r42 = new StoryEntry();
        r42.draftId = this.draftId;
        r42.isDraft = this.isDraft;
        r42.draftDate = this.draftDate;
        r42.editStoryPeerId = this.editStoryPeerId;
        r42.editStoryId = this.editStoryId;
        r42.isEdit = this.isEdit;
        r42.isEditSaved = this.isEditSaved;
        r42.fileDuration = this.fileDuration;
        r42.editedMedia = this.editedMedia;
        r42.editedCaption = this.editedCaption;
        r42.editedPrivacy = this.editedPrivacy;
        r42.editedMediaAreas = this.editedMediaAreas;
        r42.isError = this.isError;
        r42.error = this.error;
        r42.audioPath = this.audioPath;
        r42.audioDocument = this.audioDocument;
        r42.audioAuthor = this.audioAuthor;
        r42.audioTitle = this.audioTitle;
        r42.audioDuration = this.audioDuration;
        r42.audioOffset = this.audioOffset;
        r42.audioLeft = this.audioLeft;
        r42.audioRight = this.audioRight;
        r42.audioVolume = this.audioVolume;
        r42.editDocumentId = this.editDocumentId;
        r42.editPhotoId = this.editPhotoId;
        r42.editExpireDate = this.editExpireDate;
        r42.isVideo = this.isVideo;
        r42.file = this.file;
        r42.fileDeletable = this.fileDeletable;
        if (this.fileDeletable == false) goto L5;
        File r0 = makeCacheFile(this.currentAccount, ext(this.file));
        r42.file = r0;
        AndroidUtilities.copyFileSafe(this.file, r0);
    L5:
        r42.thumbPath = this.thumbPath;
        r42.muted = this.muted;
        r42.left = this.left;
        r42.right = this.right;
        r42.duration = this.duration;
        r42.width = this.width;
        r42.height = this.height;
        r42.resultWidth = this.resultWidth;
        r42.resultHeight = this.resultHeight;
        r42.peer = this.peer;
        r42.invert = this.invert;
        r42.matrix.set(this.matrix);
        r42.gradientTopColor = this.gradientTopColor;
        r42.gradientBottomColor = this.gradientBottomColor;
        r42.caption = this.caption;
        r42.captionEntitiesAllowed = this.captionEntitiesAllowed;
        r42.privacy = this.privacy;
        r42.privacyRules.clear();
        r42.privacyRules.addAll(this.privacyRules);
        r42.pinned = this.pinned;
        r42.allowScreenshots = this.allowScreenshots;
        r42.period = this.period;
        r42.shareUserIds = this.shareUserIds;
        r42.silent = this.silent;
        r42.scheduleDate = this.scheduleDate;
        r42.blurredVideoThumb = this.blurredVideoThumb;
        r42.uploadThumbFile = this.uploadThumbFile;
        r42.albums = this.albums;
        File r02 = this.uploadThumbFile;
        if (r02 != null) goto L8;
    L10:
        r42.draftThumbFile = this.draftThumbFile;
        File r03 = this.draftThumbFile;
        if (r03 != null) goto L13;
    L15:
        r42.paintFile = this.paintFile;
        File r04 = this.paintFile;
        if (r04 != null) goto L18;
    L20:
        r42.messageFile = this.messageFile;
        File r05 = this.messageFile;
        if (r05 != null) goto L23;
    L25:
        r42.backgroundFile = this.backgroundFile;
        File r06 = this.backgroundFile;
        if (r06 != null) goto L28;
    L30:
        r42.paintBlurFile = this.paintBlurFile;
        File r07 = this.paintBlurFile;
        if (r07 != null) goto L33;
    L35:
        r42.paintEntitiesFile = this.paintEntitiesFile;
        File r08 = this.paintEntitiesFile;
        if (r08 != null) goto L38;
    L40:
        r42.averageDuration = this.averageDuration;
        r42.mediaEntities = new ArrayList();
        if (this.mediaEntities == null) goto L46;
        int r09 = 0;
    L44:
        if (r09 >= this.mediaEntities.size()) goto L46;
        r42.mediaEntities.add(this.mediaEntities.get(r09).copy());
        r09 = r09 + 1;
    L46:
        r42.stickers = this.stickers;
        r42.editStickers = this.editStickers;
        r42.filterFile = this.filterFile;
        File r010 = this.filterFile;
        if (r010 != null) goto L49;
    L51:
        r42.filterState = this.filterState;
        r42.thumbBitmap = this.thumbBitmap;
        r42.fromCamera = this.fromCamera;
        r42.thumbPathBitmap = this.thumbPathBitmap;
        r42.isRepost = this.isRepost;
        r42.isShare = this.isShare;
        r42.round = this.round;
        r42.roundLeft = this.roundLeft;
        r42.roundRight = this.roundRight;
        r42.roundDuration = this.roundDuration;
        r42.roundThumb = this.roundThumb;
        r42.roundOffset = this.roundOffset;
        r42.roundVolume = this.roundVolume;
        r42.isEditingCover = this.isEditingCover;
        r42.botId = this.botId;
        r42.botLang = this.botLang;
        r42.editingBotPreview = this.editingBotPreview;
        r42.cover = this.cover;
        r42.collageContent = this.collageContent;
        r42.collage = this.collage;
        r42.videoLoop = this.videoLoop;
        r42.videoOffset = this.videoOffset;
        r42.videoVolume = this.videoVolume;
        return r42;
    L49:
        if (r010.exists() == false) goto L51;
        File r011 = makeCacheFile(this.currentAccount, ext(this.filterFile));
        r42.filterFile = r011;
        AndroidUtilities.copyFileSafe(this.filterFile, r011);
        goto L51
    L38:
        if (r08.exists() == false) goto L40;
        File r012 = makeCacheFile(this.currentAccount, ext(this.paintEntitiesFile));
        r42.paintEntitiesFile = r012;
        AndroidUtilities.copyFileSafe(this.paintEntitiesFile, r012);
        goto L40
    L33:
        if (r07.exists() == false) goto L35;
        File r013 = makeCacheFile(this.currentAccount, ext(this.paintBlurFile));
        r42.paintBlurFile = r013;
        AndroidUtilities.copyFileSafe(this.paintBlurFile, r013);
        goto L35
    L28:
        if (r06.exists() == false) goto L30;
        File r014 = makeCacheFile(this.currentAccount, ext(this.backgroundFile));
        r42.backgroundFile = r014;
        AndroidUtilities.copyFileSafe(this.backgroundFile, r014);
        goto L30
    L23:
        if (r05.exists() == false) goto L25;
        File r015 = makeCacheFile(this.currentAccount, ext(this.messageFile));
        r42.messageFile = r015;
        AndroidUtilities.copyFileSafe(this.messageFile, r015);
        goto L25
    L18:
        if (r04.exists() == false) goto L20;
        File r016 = makeCacheFile(this.currentAccount, ext(this.paintFile));
        r42.paintFile = r016;
        AndroidUtilities.copyFileSafe(this.paintFile, r016);
        goto L20
    L13:
        if (r03.exists() == false) goto L15;
        File r017 = makeCacheFile(this.currentAccount, ext(this.draftThumbFile));
        r42.draftThumbFile = r017;
        AndroidUtilities.copyFileSafe(this.draftThumbFile, r017);
        goto L15
    L8:
        if (r02.exists() == false) goto L10;
        File r018 = makeCacheFile(this.currentAccount, ext(this.uploadThumbFile));
        r42.uploadThumbFile = r018;
        AndroidUtilities.copyFileSafe(this.uploadThumbFile, r018);
        goto L10
    }

    public ArrayList<StoryEntry> cutIntoEntries() {
        if (this.isVideo == true) goto L5;
    L24:
        return null;
    L5:
        if (isCollage() == true) goto L24;
        if (this.isEdit == true) goto L24;
        long r2 = this.duration;
        if (r2 <= 0) goto L24;
        if (this.isRepost == true) goto L24;
        long r22 = (long) ((this.right - this.left) * r2);
        if (r22 >= 68999) goto L16;
        return null;
    L16:
        ArrayList<StoryEntry> r0 = new ArrayList();
        this.right = this.left + (59000.0f / this.duration);
        r0.add(this);
        long r6 = 59000;
    L18:
        if (r6 >= r22) goto L23;
        if (Math.min(59000, r22 - r6) < 1000) goto L23;
        StoryEntry r1 = copy(true);
        float r10 = this.left;
        long r12 = this.duration;
        r1.left = r10 + (r6 / r12);
        r1.right = this.left + ((r8 + r6) / r12);
        r1.caption = _UrlKt.FRAGMENT_ENCODE_SET;
        r6 = r6 + 59000;
        r0.add(r1);
    L23:
        return r0;
    }

    public void decodeBounds(String r3) {
        if (r3 == null) goto L5;
        BitmapFactory.Options r0 = new BitmapFactory.Options();     // Catch: Exception -> L11
        r0.inJustDecodeBounds = true;     // Catch: Exception -> L11
        BitmapFactory.decodeFile(r3, r0);     // Catch: Exception -> L11
        this.width = r0.outWidth;     // Catch: Exception -> L11
        this.height = r0.outHeight;     // Catch: Exception -> L11
    L5:
        if (this.isVideo == false) goto L7;
        return;
    L7:
        if (((int) Math.max(this.width, (this.height / 16.0f) * 9.0f)) > 900) goto L9;
        this.resultWidth = 720;
        this.resultHeight = 1280;
        return;
    L9:
        this.resultWidth = 1080;
        this.resultHeight = 1920;
    }

    public void destroy(boolean r9) {
        if (this.blurredVideoThumb == null) goto L5;
        this.blurredVideoThumb = null;
    L5:
        File r0 = this.uploadThumbFile;
        if (r0 == null) goto L8;
        r0.delete();
        this.uploadThumbFile = null;
    L8:
        int r02 = 0;
        if (r9 == true) goto L54;
        clearPaint();
        clearFilter();
        File r2 = this.file;
        if (r2 == null) goto L21;
        if (this.fileDeletable == true) goto L15;
    L19:
        this.file = null;
        goto L21
    L15:
        if (this.isEdit == true) goto L17;
    L18:
        r2.delete();
        goto L19
    L17:
        if (this.editedMedia == false) goto L19;
    L21:
        if (this.thumbPath != null) goto L23;
    L26:
        ArrayList<VideoEditedInfo.MediaEntity> r22 = this.mediaEntities;
        if (r22 == null) goto L39;
        int r3 = r22.size();
        int r4 = 0;
    L29:
        if (r4 >= r3) goto L39;
        VideoEditedInfo.MediaEntity r5 = r22.get(r4);
        r4 = r4 + 1;
        VideoEditedInfo.MediaEntity r52 = r5;
        if (r52.type != 2) goto L29;
        if (TextUtils.isEmpty(r52.segmentedPath) == true) goto L29;
        new File(r52.segmentedPath).delete();     // Catch: Exception -> L36
    L38:
        r52.segmentedPath = _UrlKt.FRAGMENT_ENCODE_SET;
    L36:
        e = move-exception;
        FileLog.e(e);
    L39:
        File r23 = this.round;
        if (r23 == null) goto L47;
        if (this.isEdit == true) goto L44;
    L45:
        r23.delete();
        this.round = null;
        goto L47
    L44:
        if (this.editedMedia == true) goto L45;
    L47:
        if (this.roundThumb == null) goto L54;
        if (this.isEdit == true) goto L51;
    L62:
        new File(this.roundThumb).delete();     // Catch: Exception -> L61
    L53:
        this.roundThumb = null;
        goto L54
    L51:
        if (this.editedMedia == false) goto L54;
    L23:
        if (this.fileDeletable == false) goto L25;
        new File(this.thumbPath).delete();
    L25:
        this.thumbPath = null;
    L54:
        this.thumbPathBitmap = null;
        if (this.collageContent != null) goto L57;
    L59:
        cancelCheckStickers();
        return;
    L57:
        if (r02 >= this.collageContent.size()) goto L59;
        this.collageContent.get(r02).destroy(r9);
        r02 = r02 + 1;
        goto L57
    }

    public void detectHDR(Utilities.Callback<HDRInfo> r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        HDRInfo r0 = this.hdrInfo;
        if (r0 == null) goto L9;
        r3.run(r0);
        return;
    L9:
        if (this.isVideo == false) goto L12;
        Utilities.globalQueue.postRunnable(new StoryEntry$$ExternalSyntheticLambda3(this, r3));
        return;
    L12:
        HDRInfo r02 = new HDRInfo();
        this.hdrInfo = r02;
        r3.run(r02);
    }

    public File getOriginalFile() {
        File r0 = this.filterFile;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return this.file;
    }

    public int getTotalCount() {
        if (this.isVideo == true) goto L5;
    L18:
        return 1;
    L5:
        if (isCollage() == true) goto L18;
        if (this.isEdit == true) goto L18;
        long r2 = this.duration;
        if (r2 <= 0) goto L18;
        if (this.isRepost == true) goto L18;
        if (((long) ((this.right - this.left) * r2)) >= 68999) goto L17;
        return 1;
    L17:
        return (int) Math.ceil(r2 / 59000.0f);
    }

    public void getVideoEditedInfo(Utilities.Callback<VideoEditedInfo> r8) {
        if (wouldBeVideo() == true) goto L7;
        r8.run(null);
        return;
    L7:
        if (this.isVideo == true) goto L13;
        int r0 = this.resultWidth;
        if (r0 <= 720) goto L11;
    L12:
        float r4 = 720.0f / r0;
        this.matrix.postScale(r4, r4, 0.0f, 0.0f);
        this.resultWidth = 720;
        this.resultHeight = 1280;
        goto L13
    L11:
        if (this.resultHeight > 1280) goto L12;
    L13:
        File r02 = this.file;
        if (r02 != null) goto L16;
        String r03 = null;
    L17:
        int r3 = 0;
        if (isCollage() == false) goto L20;
        int r2 = this.collageContent.size();
    L21:
        int[][] r22 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{Math.max(1, r2), 11});
        r22[0] = new int[11];
        StoryEntry$$ExternalSyntheticLambda11 r42 = new StoryEntry$$ExternalSyntheticLambda11(this, r03, r22, r8);
        if (isCollage() == false) goto L34;
        String[] r82 = new String[this.collageContent.size()];
    L25:
        if (r3 >= this.collageContent.size()) goto L31;
        if (this.collageContent.get(r3).file != null) goto L29;
        String r04 = null;
    L30:
        r82[r3] = r04;
        r22[r3] = new int[11];
        r3 = r3 + 1;
        goto L25
    L29:
        r04 = this.collageContent.get(r3).file.getAbsolutePath();
        goto L30
    L31:
        Utilities.globalQueue.postRunnable(new StoryEntry$$ExternalSyntheticLambda12(r82, r22, r42));
        return;
    L34:
        if (this.file != null) goto L37;
        r42.run();
        return;
    L37:
        Utilities.globalQueue.postRunnable(new StoryEntry$$ExternalSyntheticLambda13(r03, r22, r42));
        return;
    L20:
        r2 = 0;
        goto L21
    L16:
        r03 = r02.getAbsolutePath();
        goto L17
    }

    public boolean hasVideo() {
        if (isCollage() == true) goto L5;
        return false;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= this.collageContent.size()) goto L13;
        if (this.collageContent.get(r0).isVideo == true) goto L10;
        r0 = r0 + 1;
        goto L7
    L10:
        return true;
    L13:
        return false;
    }

    public boolean isCollage() {
        if (this.collage != null) goto L5;
        return false;
    L5:
        if (this.collageContent == null) goto L10;
        return true;
    L10:
        return false;
    }

    public void setupGradient(Runnable r8) {
        if (this.isVideo == true) goto L5;
        return;
    L5:
        if (this.gradientTopColor == 0) goto L7;
        return;
    L7:
        if (this.gradientBottomColor == 0) goto L9;
        return;
    L9:
        if (this.thumbPath != null) goto L23;
        Bitmap r0 = this.thumbPathBitmap;
        if (r0 == null) goto L30;
        DominantColors.getColors(true, r0, true, new StoryEntry$$ExternalSyntheticLambda2(this, r8));
        return;
    L30:
        return;
    L23:
        BitmapFactory.Options r02 = new BitmapFactory.Options();     // Catch: Exception -> L15
        if (this.thumbPath.startsWith("vthumb://") == false) goto L13;
        long r5 = Integer.parseInt(this.thumbPath.substring(9));     // Catch: Exception -> L15
        r02.inJustDecodeBounds = true;     // Catch: Exception -> L15
        MediaStore.Video.Thumbnails.getThumbnail(ApplicationLoader.applicationContext.getContentResolver(), r5, 1, r02);     // Catch: Exception -> L15
        r02.inSampleSize = calculateInSampleSize(r02, 240, 240);     // Catch: Exception -> L15
        r02.inJustDecodeBounds = false;     // Catch: Exception -> L15
        r02.inPreferredConfig = Bitmap.Config.RGB_565;     // Catch: Exception -> L15
        r02.inDither = true;     // Catch: Exception -> L15
        Bitmap r03 = MediaStore.Video.Thumbnails.getThumbnail(ApplicationLoader.applicationContext.getContentResolver(), r5, 1, r02);     // Catch: Exception -> L15
    L22:
    L16:
        if (r03 == null) goto L28;
        DominantColors.getColors(true, r03, true, new StoryEntry$$ExternalSyntheticLambda1(this, r03, r8));
        return;
    L28:
        return;
    L13:
        r02.inJustDecodeBounds = true;     // Catch: Exception -> L15
        BitmapFactory.decodeFile(this.thumbPath);     // Catch: Exception -> L15
        r02.inSampleSize = calculateInSampleSize(r02, 240, 240);     // Catch: Exception -> L15
        r02.inJustDecodeBounds = false;     // Catch: Exception -> L15
        r02.inPreferredConfig = Bitmap.Config.RGB_565;     // Catch: Exception -> L15
        r02.inDither = true;     // Catch: Exception -> L15
        r03 = BitmapFactory.decodeFile(this.thumbPath);     // Catch: Exception -> L15
    L15:
        r03 = null;
        goto L16
    }

    public void setupMatrix() {
        setupMatrix(this.matrix, 0);
    }

    public void setupMatrix(Matrix r10, int r11) {
        r10.reset();
        int r0 = this.width;
        int r1 = this.height;
        int r2 = this.orientation + r11;
        int r112 = this.invert;
        float r4 = 1.0f;
        if (r112 != 1) goto L5;
        float r3 = -1.0f;
    L7:
        if (r112 != 2) goto L9;
        r4 = -1.0f;
    L9:
        r10.postScale(r3, r4, r0 / 2.0f, r1 / 2.0f);
        if (r2 == 0) goto L17;
        r10.postTranslate((-r0) / 2.0f, (-r1) / 2.0f);
        r10.postRotate(r2);
        if (r2 != 90) goto L14;
    L15:
        r1 = r0;
        r0 = r1;
    L16:
        r10.postTranslate(r0 / 2.0f, r1 / 2.0f);
        goto L17
    L14:
        if (r2 != 270) goto L16;
    L17:
        float r02 = r0;
        float r113 = this.resultWidth / r02;
        if (this.botId == 0) goto L20;
        r113 = Math.min(r113, this.resultHeight / r1);
    L23:
        r10.postScale(r113, r113);
        r10.postTranslate((this.resultWidth - (r02 * r113)) / 2.0f, (this.resultHeight - (r1 * r113)) / 2.0f);
        return;
    L20:
        float r22 = r1;
        if ((r22 / r02) <= 1.29f) goto L23;
        r113 = Math.max(r113, this.resultHeight / r22);
        goto L23
    L5:
        r3 = 1.0f;
        goto L7
    }

    public void setupMultipleStoriesSelector() {
        if (this.isVideo == true) goto L5;
        return;
    L5:
        if (isCollage() == false) goto L7;
        return;
    L7:
        if (this.isEdit == false) goto L9;
        return;
    L9:
        if (this.isRepost == false) goto L11;
        return;
    L11:
        if (this.duration > 69000) goto L13;
        return;
    L13:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L28;
        long r0 = this.duration;
        if ((r0 - 59000) <= NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L17;
        long r02 = Math.min(59000, r0 - 59000) + 59000;
    L18:
        long r4 = this.duration;
        if ((r4 - r02) <= NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L21;
        r02 = r02 + Math.min(59000, r4 - r02);
    L21:
        this.right = Math.min(1.0f, r02 / this.duration);
        return;
    L17:
        r02 = 59000;
        goto L18
    }

    public void updateFilter(PhotoFilterView r10, Runnable r11) {
        clearFilter();
        MediaController.SavedFilterState r0 = r10.getSavedFilterState();
        this.filterState = r0;
        if (this.isVideo == false) goto L5;
        if (r11 == null) goto L58;
        r11.run();
        return;
    L58:
        return;
    L5:
        if (r0.isEmpty() == false) goto L9;
        if (r11 == null) goto L54;
        r11.run();
        return;
    L54:
        return;
    L9:
        Bitmap r1 = r10.getBitmap();
        if (r1 != null) goto L14;
        if (r11 == null) goto L55;
        r11.run();
        return;
    L55:
        return;
    L14:
        Matrix r6 = new Matrix();
        int r102 = this.invert;
        float r02 = 1.0f;
        boolean r8 = true;
        if (r102 != 1) goto L17;
        float r3 = -1.0f;
    L19:
        if (r102 != 2) goto L21;
        r02 = -1.0f;
    L21:
        r6.postScale(r3, r02, this.width / 2.0f, this.height / 2.0f);
        r6.postRotate(-this.orientation);
        Bitmap r103 = Bitmap.createBitmap(r1, 0, 0, r1.getWidth(), r1.getHeight(), r6, true);
        this.matrix.preScale(this.width / r103.getWidth(), this.height / r103.getHeight());
        this.width = r103.getWidth();
        this.height = r103.getHeight();
        r1.recycle();
        File r03 = this.filterFile;
        if (r03 != null) goto L24;
    L26:
        String r04 = ext(this.file);
        String r2 = "webp";
        if ("png".equals(r04) == false) goto L29;
    L32:
        int r05 = this.currentAccount;
        if (r8 == true) goto L36;
        r2 = "jpg";
    L36:
        this.filterFile = makeCacheFile(r05, r2);
        if (r11 == null) goto L52;
        Utilities.themeQueue.postRunnable(new StoryEntry$$ExternalSyntheticLambda0(this, r103, r8, r11));
        return;
    L52:
        FileOutputStream r112 = new FileOutputStream(this.filterFile);     // Catch: Exception -> L41
        if (r8 == false) goto L43;
        Bitmap.CompressFormat r9 = Bitmap.CompressFormat.WEBP;     // Catch: Exception -> L41
    L44:
        r103.compress(r9, 90, r112);     // Catch: Exception -> L41
    L47:
        r103.recycle();
        return;
    L43:
        r9 = Bitmap.CompressFormat.JPEG;     // Catch: Exception -> L41
    L41:
        e = move-exception;
        FileLog.e(e);
        goto L47
    L29:
        if ("webp".equals(r04) == true) goto L32;
        r8 = false;
        goto L32
    L24:
        if (r03.exists() == false) goto L26;
        this.filterFile.delete();
        goto L26
    L17:
        r3 = 1.0f;
        goto L19
    }

    public boolean wouldBeVideo() {
        return wouldBeVideo(this.mediaEntities);
    }

    public boolean wouldBeVideo(ArrayList<VideoEditedInfo.MediaEntity> r7) {
        if (this.isVideo == false) goto L6;
        return true;
    L6:
        if (this.audioPath == null) goto L9;
        return true;
    L9:
        if (this.round == null) goto L11;
        return true;
    L11:
        ArrayList<MessageObject> r0 = this.messageObjects;
        if (r0 != null) goto L14;
    L22:
        if (r7 != null) goto L24;
    L46:
        return false;
    L24:
        if (r7.isEmpty() == true) goto L46;
        int r6 = 0;
    L27:
        if (r6 >= r7.size()) goto L46;
        VideoEditedInfo.MediaEntity r02 = r7.get(r6);
        byte r3 = r02.type;
        if (r3 == 0) goto L31;
        if (r3 != 1) goto L45;
        ArrayList<VideoEditedInfo.EmojiEntity> r32 = r02.entities;
        if (r32 == null) goto L45;
        if (r32.isEmpty() == true) goto L45;
        int r33 = 0;
    L40:
        if (r33 >= r02.entities.size()) goto L45;
        VideoEditedInfo.EmojiEntity r4 = r02.entities.get(r33);
        if (isAnimated(r4.document, r4.documentAbsolutePath) == true) goto L43;
        r33 = r33 + 1;
        goto L40
    L43:
        return true;
    L45:
        r6 = r6 + 1;
        goto L27
    L31:
        if (isAnimated(r02.document, r02.text) == false) goto L45;
        return true;
    L14:
        if (r0.size() != 1) goto L22;
        MessageObject r62 = this.messageObjects.get(0);
        if (r62 == null) goto L22;
        TLRPC.Message r63 = r62.messageOwner;
        if (r63 == null) goto L22;
        if ((r63.action instanceof TLRPC.TL_messageActionStarGiftUnique) == false) goto L22;
        return true;
    }
}
