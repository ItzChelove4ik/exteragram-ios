package org.telegram.messenger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.SurfaceTexture;
import android.text.TextUtils;
import android.view.View;
import java.io.File;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.video.MediaCodecPlayer;
import org.telegram.messenger.video.MediaCodecVideoConvertor;
import org.telegram.tgnet.AbstractSerializedData;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Components.AnimatedFileDrawable;
import org.telegram.ui.Components.Paint.PaintTypeface;
import org.telegram.ui.Components.Paint.Views.LinkPreview;
import org.telegram.ui.Components.PhotoFilterView;
import org.telegram.ui.Components.RLottieNative;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Stories.recorder.CollageLayout;
import org.telegram.ui.Stories.recorder.StoryEntry;
import org.telegram.ui.Stories.recorder.Weather;

/* loaded from: classes.dex */
public class VideoEditedInfo {
    public int account;
    public boolean alreadyScheduledConverting;
    public long avatarStartTime;
    public String backgroundPath;
    public int bitrate;
    public String blurPath;
    public boolean canceled;
    public CollageLayout collage;
    public ArrayList<Part> collageParts;
    public int compressQuality;
    public MediaController.CropState cropState;
    public TLRPC.InputEncryptedFile encryptedFile;
    public float end;
    public long endTime;
    public long estimatedDuration;
    public long estimatedSize;
    public TLRPC.InputFile file;
    public MediaController.SavedFilterState filterState;
    public boolean forceFragmenting;
    public int framerate;
    public boolean fromCamera;
    public Integer gradientBottomColor;
    public Integer gradientTopColor;
    public StoryEntry.HDRInfo hdrInfo;
    public boolean isDark;
    public boolean isPhoto;
    public boolean isSticker;
    public boolean isStory;
    public byte[] iv;
    public byte[] key;
    public ArrayList<MediaEntity> mediaEntities;
    public String messagePath;
    public String messageVideoMaskPath;
    public ArrayList<MediaCodecVideoConvertor.MixedSoundInfo> mixedSoundInfos;
    public boolean muted;
    public boolean needUpdateProgress;
    public boolean notReadyYet;
    public int originalBitrate;
    public long originalDuration;
    public int originalHeight;
    public String originalPath;
    public int originalWidth;
    public String paintPath;
    public int resultHeight;
    public int resultWidth;
    public int rotationValue;
    public boolean roundVideo;
    public boolean shouldLimitFps;
    public float start;
    public long startTime;
    public Bitmap thumb;
    public boolean videoConvertFirstWrite;
    public long videoOffset;
    public float volume;
    public long wallpaperPeerId;

    /* loaded from: classes5.dex */
    public static class EmojiEntity extends TLRPC.TL_messageEntityCustomEmoji {
        public String documentAbsolutePath;
        public MediaEntity entity;
        public byte subType;

        public EmojiEntity() {
        }

        @Override // org.telegram.tgnet.TLRPC.TL_messageEntityCustomEmoji, org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r2, boolean r3) {
            super.readParams(r2, r3);
            this.subType = r2.readByte(r3);
            if (r2.readBool(r3) == false) goto L6;
            this.documentAbsolutePath = r2.readString(r3);
        L6:
            if (TextUtils.isEmpty(this.documentAbsolutePath) == false) goto L9;
            this.documentAbsolutePath = null;
            return;
        }

        @Override // org.telegram.tgnet.TLRPC.TL_messageEntityCustomEmoji, org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r2) {
            super.serializeToStream(r2);
            r2.writeByte(this.subType);
            r2.writeBool(!TextUtils.isEmpty(this.documentAbsolutePath));
            if (TextUtils.isEmpty(this.documentAbsolutePath) == true) goto L6;
            r2.writeString(this.documentAbsolutePath);
            return;
        }
    }

    /* loaded from: classes5.dex */
    public static class MediaEntity {
        public static final byte TYPE_LINK = 7;
        public static final byte TYPE_LOCATION = 3;
        public static final byte TYPE_MESSAGE = 6;
        public static final byte TYPE_PHOTO = 2;
        public static final byte TYPE_REACTION = 4;
        public static final byte TYPE_ROUND = 5;
        public static final byte TYPE_STICKER = 0;
        public static final byte TYPE_TEXT = 1;
        public static final byte TYPE_VIDEO = 9;
        public static final byte TYPE_WEATHER = 8;
        public int H;
        public int W;
        public float additionalHeight;
        public float additionalWidth;
        public AnimatedFileDrawable animatedFileDrawable;
        public Bitmap bitmap;
        public Canvas canvas;
        public int color;
        public MediaController.CropState crop;
        public float currentFrame;
        public boolean customTextView;
        public float density;
        public TLRPC.Document document;
        public ArrayList<EmojiEntity> entities;
        public boolean firstSeek;
        public int fontSize;
        public float framesPerDraw;
        public float height;
        public LinkPreview.WebPagePreview linkSettings;
        public boolean looped;
        public RLottieNative lottieNative;
        public Matrix matrix;
        public TLRPC.MessageMedia media;
        public TL_stories.MediaArea mediaArea;
        public Object parentObject;
        public float rotation;
        public long roundDuration;
        public long roundLeft;
        public long roundOffset;
        public float roundRadius;
        public Canvas roundRadiusCanvas;
        public long roundRight;
        public float scale;
        public String segmentedPath;
        public byte subType;
        public String text;
        public int textAlign;
        public PaintTypeface textTypeface;
        public String textTypefaceKey;
        public float textViewHeight;
        public float textViewWidth;
        public float textViewX;
        public float textViewY;
        public byte type;
        public View view;
        public int viewHeight;
        public int viewWidth;
        public ReactionsLayoutInBubble.VisibleReaction visibleReaction;
        public Weather.State weather;
        public float width;
        public float x;
        public float y;

        public MediaEntity() {
            this.text = _UrlKt.FRAGMENT_ENCODE_SET;
            this.entities = new ArrayList();
            this.segmentedPath = _UrlKt.FRAGMENT_ENCODE_SET;
            this.scale = 1.0f;
        }

        public MediaEntity(AbstractSerializedData r2, boolean r3) {
            this(r2, r3, false);
        }

        public MediaEntity(AbstractSerializedData r5, boolean r6, boolean r7) {
            this.text = _UrlKt.FRAGMENT_ENCODE_SET;
            this.entities = new ArrayList();
            this.segmentedPath = _UrlKt.FRAGMENT_ENCODE_SET;
            this.scale = 1.0f;
            this.type = r5.readByte(r7);
            this.subType = r5.readByte(r7);
            this.x = r5.readFloat(r7);
            this.y = r5.readFloat(r7);
            this.rotation = r5.readFloat(r7);
            this.width = r5.readFloat(r7);
            this.height = r5.readFloat(r7);
            this.text = r5.readString(r7);
            int r0 = r5.readInt32(r7);
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            EmojiEntity r2 = new EmojiEntity();
            r5.readInt32(r7);
            r2.readParams(r5, r7);
            this.entities.add(r2);
            r1 = r1 + 1;
            goto L3
        L5:
            this.color = r5.readInt32(r7);
            this.fontSize = r5.readInt32(r7);
            this.viewWidth = r5.readInt32(r7);
            this.viewHeight = r5.readInt32(r7);
            this.textAlign = r5.readInt32(r7);
            String r02 = r5.readString(r7);
            this.textTypefaceKey = r02;
            this.textTypeface = PaintTypeface.find(r02);
            this.scale = r5.readFloat(r7);
            this.textViewWidth = r5.readFloat(r7);
            this.textViewHeight = r5.readFloat(r7);
            this.textViewX = r5.readFloat(r7);
            this.textViewY = r5.readFloat(r7);
            if (r6 == false) goto L11;
            int r62 = r5.readInt32(r7);
            if (r62 != 1450380236) goto L10;
            this.document = null;
            goto L11
        L10:
            this.document = TLRPC.Document.TLdeserialize(r5, r62, r7);
        L11:
            byte r63 = this.type;
            if (r63 != 3) goto L22;
            this.density = r5.readFloat(r7);
            this.mediaArea = TL_stories.MediaArea.TLdeserialize(r5, r5.readInt32(r7), r7);
            this.media = TLRPC.MessageMedia.TLdeserialize(r5, r5.readInt32(r7), r7);
            if (r5.remaining() > 0) goto L16;
            return;
        L16:
            if (r5.readInt32(r7) != (-559038737)) goto L47;
            String r52 = r5.readString(r7);
            TLRPC.MessageMedia r4 = this.media;
            if ((r4 instanceof TLRPC.TL_messageMediaVenue) == false) goto L48;
            ((TLRPC.TL_messageMediaVenue) r4).emoji = r52;
            return;
        L48:
            return;
        L47:
            return;
        L22:
            if (r63 != 7) goto L26;
            this.density = r5.readFloat(r7);
            this.mediaArea = TL_stories.MediaArea.TLdeserialize(r5, r5.readInt32(r7), r7);
            this.linkSettings = LinkPreview.WebPagePreview.TLdeserialize(r5, r5.readInt32(r7), r7);
            return;
        L26:
            if (r63 != 4) goto L30;
            this.mediaArea = TL_stories.MediaArea.TLdeserialize(r5, r5.readInt32(r7), r7);
            return;
        L30:
            if (r63 != 5) goto L34;
            this.roundOffset = r5.readInt64(r7);
            this.roundLeft = r5.readInt64(r7);
            this.roundRight = r5.readInt64(r7);
            this.roundDuration = r5.readInt64(r7);
            return;
        L34:
            if (r63 != 2) goto L40;
            this.segmentedPath = r5.readString(r7);
            if (r5.readInt32(r7) != 1151577037) goto L49;
            MediaController.CropState r64 = new MediaController.CropState();
            this.crop = r64;
            r64.readParams(r5, r7);
            return;
        L49:
            return;
        L40:
            if (r63 == 8) goto L42;
            return;
        L42:
            if (r5.readInt32(r7) != 132805945) goto L51;
            this.weather = Weather.State.TLdeserialize(r5);
            return;
        }

        public MediaEntity copy() {
            MediaEntity r0 = new MediaEntity();
            r0.type = this.type;
            r0.subType = this.subType;
            r0.x = this.x;
            r0.y = this.y;
            r0.rotation = this.rotation;
            r0.width = this.width;
            r0.height = this.height;
            r0.additionalHeight = this.additionalHeight;
            r0.text = this.text;
            if (this.entities == null) goto L5;
            ArrayList<EmojiEntity> r1 = new ArrayList();
            r0.entities = r1;
            r1.addAll(this.entities);
        L5:
            r0.color = this.color;
            r0.fontSize = this.fontSize;
            r0.textTypeface = this.textTypeface;
            r0.textTypefaceKey = this.textTypefaceKey;
            r0.textAlign = this.textAlign;
            r0.viewWidth = this.viewWidth;
            r0.viewHeight = this.viewHeight;
            r0.roundRadius = this.roundRadius;
            r0.scale = this.scale;
            r0.textViewWidth = this.textViewWidth;
            r0.textViewHeight = this.textViewHeight;
            r0.textViewX = this.textViewX;
            r0.textViewY = this.textViewY;
            r0.document = this.document;
            r0.parentObject = this.parentObject;
            r0.lottieNative = this.lottieNative;
            r0.currentFrame = this.currentFrame;
            r0.framesPerDraw = this.framesPerDraw;
            r0.bitmap = this.bitmap;
            r0.view = this.view;
            r0.canvas = this.canvas;
            r0.animatedFileDrawable = this.animatedFileDrawable;
            r0.roundRadiusCanvas = this.roundRadiusCanvas;
            r0.mediaArea = this.mediaArea;
            r0.media = this.media;
            r0.density = this.density;
            r0.W = this.W;
            r0.H = this.H;
            r0.visibleReaction = this.visibleReaction;
            r0.roundOffset = this.roundOffset;
            r0.roundDuration = this.roundDuration;
            r0.roundLeft = this.roundLeft;
            r0.roundRight = this.roundRight;
            r0.linkSettings = this.linkSettings;
            r0.weather = this.weather;
            return r0;
        }

        public void serializeTo(AbstractSerializedData r5, boolean r6) {
            r5.writeByte(this.type);
            r5.writeByte(this.subType);
            r5.writeFloat(this.x);
            r5.writeFloat(this.y);
            r5.writeFloat(this.rotation);
            r5.writeFloat(this.width);
            r5.writeFloat(this.height);
            r5.writeString(this.text);
            r5.writeInt32(this.entities.size());
            int r0 = 0;
        L4:
            if (r0 >= this.entities.size()) goto L6;
            this.entities.get(r0).serializeToStream(r5);
            r0 = r0 + 1;
            goto L4
        L6:
            r5.writeInt32(this.color);
            r5.writeInt32(this.fontSize);
            r5.writeInt32(this.viewWidth);
            r5.writeInt32(this.viewHeight);
            r5.writeInt32(this.textAlign);
            PaintTypeface r02 = this.textTypeface;
            if (r02 != null) goto L11;
            String r03 = this.textTypefaceKey;
            if (r03 != null) goto L12;
            r03 = _UrlKt.FRAGMENT_ENCODE_SET;
        L12:
            r5.writeString(r03);
            r5.writeFloat(this.scale);
            r5.writeFloat(this.textViewWidth);
            r5.writeFloat(this.textViewHeight);
            r5.writeFloat(this.textViewX);
            r5.writeFloat(this.textViewY);
            if (r6 == false) goto L18;
            TLRPC.Document r62 = this.document;
            if (r62 != null) goto L17;
            r5.writeInt32(TLRPC.TL_null.constructor);
            goto L18
        L17:
            r62.serializeToStream(r5);
        L18:
            byte r63 = this.type;
            if (r63 != 3) goto L38;
            r5.writeFloat(this.density);
            this.mediaArea.serializeToStream(r5);
            TLRPC.MessageMedia r64 = this.media;
            if (r64.provider != null) goto L24;
            r64.provider = _UrlKt.FRAGMENT_ENCODE_SET;
        L24:
            if (r64.venue_id != null) goto L27;
            r64.venue_id = _UrlKt.FRAGMENT_ENCODE_SET;
        L27:
            if (r64.venue_type != null) goto L29;
            r64.venue_type = _UrlKt.FRAGMENT_ENCODE_SET;
        L29:
            r64.serializeToStream(r5);
            TLRPC.MessageMedia r65 = this.media;
            if ((r65 instanceof TLRPC.TL_messageMediaVenue) == true) goto L32;
        L35:
            r5.writeInt32(TLRPC.TL_null.constructor);
            return;
        L32:
            if (((TLRPC.TL_messageMediaVenue) r65).emoji == null) goto L35;
            r5.writeInt32(-559038737);
            r5.writeString(((TLRPC.TL_messageMediaVenue) this.media).emoji);
            return;
        L38:
            if (r63 != 7) goto L42;
            r5.writeFloat(this.density);
            this.mediaArea.serializeToStream(r5);
            this.linkSettings.serializeToStream(r5);
            return;
        L42:
            if (r63 != 4) goto L46;
            this.mediaArea.serializeToStream(r5);
            return;
        L46:
            if (r63 != 5) goto L50;
            r5.writeInt64(this.roundOffset);
            r5.writeInt64(this.roundLeft);
            r5.writeInt64(this.roundRight);
            r5.writeInt64(this.roundDuration);
            return;
        L50:
            if (r63 != 2) goto L58;
            r5.writeString(this.segmentedPath);
            MediaController.CropState r4 = this.crop;
            if (r4 == null) goto L55;
            r4.serializeToStream(r5);
            return;
        L55:
            r5.writeInt32(TLRPC.TL_null.constructor);
            return;
        L58:
            if (r63 == 8) goto L60;
            return;
        L60:
            if (this.weather != null) goto L63;
            r5.writeInt32(-559038737);
            return;
        L63:
            r5.writeInt32(132805945);
            this.weather.serializeToStream(r5);
            return;
        L11:
            r03 = r02.getKey();
            goto L12
        }
    }

    /* loaded from: classes5.dex */
    public static class Part extends TLObject {
        public AnimatedFileDrawable animatedFileDrawable;
        public float currentFrame;
        public long duration;
        public int flags;
        public float framesPerDraw;
        public int height;
        public boolean isVideo;
        public float left;
        public boolean loop;
        public float msPerFrame;
        public boolean muted;
        public long offset;
        public CollageLayout.Part part;
        public String path;
        public MediaCodecPlayer player;
        public FloatBuffer posBuffer;
        public float right;
        public SurfaceTexture surfaceTexture;
        public FloatBuffer uvBuffer;
        public float volume;
        public int width;

        public Part() {
            this.volume = 1.0f;
            this.offset = 0;
            this.loop = true;
        }

        public Part(StoryEntry r3) {
            this.volume = 1.0f;
            this.offset = 0;
            this.loop = true;
            this.isVideo = r3.isVideo;
            this.muted = r3.muted;
            this.path = r3.file.getAbsolutePath();
            this.volume = r3.videoVolume;
            this.loop = r3.videoLoop;
            this.offset = r3.videoOffset;
            this.left = r3.videoLeft;
            this.right = r3.videoRight;
            this.width = r3.width;
            this.height = r3.height;
            this.duration = r3.duration;
        }

        public static ArrayList<Part> toParts(StoryEntry r4) {
            if (r4 != null) goto L4;
            return null;
        L4:
            if (r4.collageContent == null) goto L14;
            ArrayList<Part> r0 = new ArrayList();
            int r1 = 0;
        L8:
            if (r1 >= r4.collageContent.size()) goto L10;
            Part r3 = new Part(r4.collageContent.get(r1));
            r3.part = r4.collage.parts.get(r1);
            r0.add(r3);
            r1 = r1 + 1;
            goto L8
        L10:
            return r0;
        L14:
            return null;
        }

        public static ArrayList<StoryEntry> toStoryEntries(ArrayList<Part> r7) {
            if (r7 != null) goto L5;
            return null;
        L5:
            ArrayList<StoryEntry> r0 = new ArrayList();
            int r1 = r7.size();
            int r2 = 0;
        L6:
            if (r2 >= r1) goto L8;
            Part r3 = r7.get(r2);
            r2 = r2 + 1;
            Part r32 = r3;
            StoryEntry r4 = new StoryEntry();
            r4.isVideo = r32.isVideo;
            r4.muted = r32.muted;
            r4.file = new File(r32.path);
            r4.videoVolume = r32.volume;
            r4.videoLoop = r32.loop;
            r4.videoOffset = r32.offset;
            r4.videoLeft = r32.left;
            r4.videoRight = r32.right;
            r4.width = r32.width;
            r4.height = r32.height;
            r4.duration = r32.duration;
            r0.add(r4);
            goto L6
        L8:
            return r0;
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            int r0 = r5.readInt32(r6);
            this.flags = r0;
            boolean r2 = false;
            if ((r0 & 1) == 0) goto L5;
            boolean r1 = true;
        L6:
            this.isVideo = r1;
            if ((r0 & 2) == 0) goto L9;
            boolean r12 = true;
        L10:
            this.loop = r12;
            if ((r0 & 4) == 0) goto L13;
            r2 = true;
        L13:
            this.muted = r2;
            this.path = r5.readString(r6);
            this.volume = r5.readFloat(r6);
            this.offset = r5.readInt64(r6);
            this.left = r5.readFloat(r6);
            this.right = r5.readFloat(r6);
            this.width = r5.readInt32(r6);
            this.height = r5.readInt32(r6);
            this.duration = r5.readInt64(r6);
            return;
        L9:
            r12 = false;
            goto L10
        L5:
            r1 = false;
            goto L6
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            boolean r0 = this.isVideo;
            int r1 = this.flags;
            if (r0 == false) goto L5;
            int r02 = r1 | 1;
        L6:
            this.flags = r02;
            if (this.loop == false) goto L9;
            int r03 = r02 | 2;
        L10:
            this.flags = r03;
            if (this.muted == false) goto L13;
            int r04 = r03 | 4;
        L14:
            this.flags = r04;
            r3.writeInt32(r04);
            r3.writeString(this.path);
            r3.writeFloat(this.volume);
            r3.writeInt64(this.offset);
            r3.writeFloat(this.left);
            r3.writeFloat(this.right);
            r3.writeInt32(this.width);
            r3.writeInt32(this.height);
            r3.writeInt64(this.duration);
            return;
        L13:
            r04 = r03 & (-5);
            goto L14
        L9:
            r03 = r02 & (-3);
            goto L10
        L5:
            r02 = r1 & (-2);
            goto L6
        }
    }

    public VideoEditedInfo() {
        this.avatarStartTime = -1;
        this.framerate = 24;
        this.volume = 1.0f;
        this.wallpaperPeerId = Long.MIN_VALUE;
        this.needUpdateProgress = false;
        this.shouldLimitFps = true;
        this.mixedSoundInfos = new ArrayList();
    }

    public boolean canAutoPlaySourceVideo() {
        return this.roundVideo;
    }

    public String getString() {
        if (this.avatarStartTime != (-1)) goto L20;
        if (this.filterState != null) goto L20;
        if (this.paintPath != null) goto L20;
        if (this.blurPath != null) goto L20;
        ArrayList<MediaEntity> r1 = this.mediaEntities;
        if (r1 == null) goto L15;
        if (r1.isEmpty() == false) goto L20;
    L15:
        if (this.cropState != null) goto L20;
        String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
    L84:
        return String.format(Locale.US, "-1_%d_%d_%d_%d_%d_%d_%d_%d_%d_%d_%d_-%s_%s", new Object[]{Long.valueOf(this.startTime), Long.valueOf(this.endTime), Integer.valueOf(this.rotationValue), Integer.valueOf(this.originalWidth), Integer.valueOf(this.originalHeight), Integer.valueOf(this.bitrate), Integer.valueOf(this.resultWidth), Integer.valueOf(this.resultHeight), Long.valueOf(this.originalDuration), Integer.valueOf(this.framerate), Long.valueOf(this.videoOffset), r12, this.originalPath});
    L20:
        if (this.filterState == null) goto L22;
        int r13 = 170;
    L23:
        String r2 = this.paintPath;
        byte[] r3 = null;
        if (r2 == null) goto L26;
        byte[] r22 = r2.getBytes();
        r13 = r13 + r22.length;
    L27:
        String r4 = this.blurPath;
        if (r4 == null) goto L30;
        r3 = r4.getBytes();
        r13 = r13 + r3.length;
    L30:
        SerializedData r42 = new SerializedData(r13);
        r42.writeInt32(11);
        r42.writeInt64(this.avatarStartTime);
        r42.writeInt32(this.originalBitrate);
        int r5 = 0;
        if (this.filterState == null) goto L48;
        r42.writeByte(1);
        r42.writeFloat(this.filterState.enhanceValue);
        r42.writeFloat(this.filterState.softenSkinValue);
        r42.writeFloat(this.filterState.exposureValue);
        r42.writeFloat(this.filterState.contrastValue);
        r42.writeFloat(this.filterState.warmthValue);
        r42.writeFloat(this.filterState.saturationValue);
        r42.writeFloat(this.filterState.fadeValue);
        r42.writeInt32(this.filterState.tintShadowsColor);
        r42.writeInt32(this.filterState.tintHighlightsColor);
        r42.writeFloat(this.filterState.highlightsValue);
        r42.writeFloat(this.filterState.shadowsValue);
        r42.writeFloat(this.filterState.vignetteValue);
        r42.writeFloat(this.filterState.grainValue);
        r42.writeInt32(this.filterState.blurType);
        r42.writeFloat(this.filterState.sharpenValue);
        r42.writeFloat(this.filterState.blurExcludeSize);
        PointF r14 = this.filterState.blurExcludePoint;
        if (r14 == null) goto L35;
        r42.writeFloat(r14.x);
        r42.writeFloat(this.filterState.blurExcludePoint.y);
    L36:
        r42.writeFloat(this.filterState.blurExcludeBlurSize);
        r42.writeFloat(this.filterState.blurAngle);
        int r15 = 0;
    L38:
        if (r15 >= 4) goto L49;
        if (r15 != 0) goto L41;
        PhotoFilterView.CurvesValue r7 = this.filterState.curvesToolValue.luminanceCurve;
    L47:
        r42.writeFloat(r7.blacksLevel);
        r42.writeFloat(r7.shadowsLevel);
        r42.writeFloat(r7.midtonesLevel);
        r42.writeFloat(r7.highlightsLevel);
        r42.writeFloat(r7.whitesLevel);
        r15 = r15 + 1;
        goto L38
    L41:
        if (r15 != 1) goto L43;
        r7 = this.filterState.curvesToolValue.redCurve;
        goto L47
    L43:
        MediaController.SavedFilterState r72 = this.filterState;
        if (r15 != 2) goto L46;
        r7 = r72.curvesToolValue.greenCurve;
        goto L47
    L46:
        r7 = r72.curvesToolValue.blueCurve;
    L49:
        if (r22 == null) goto L51;
        r42.writeByte(1);
        r42.writeByteArray(r22);
    L52:
        ArrayList<MediaEntity> r16 = this.mediaEntities;
        if (r16 != null) goto L55;
    L60:
        r42.writeByte(0);
    L62:
        if (this.cropState == null) goto L64;
        r42.writeByte(1);
        r42.writeFloat(this.cropState.cropPx);
        r42.writeFloat(this.cropState.cropPy);
        r42.writeFloat(this.cropState.cropPw);
        r42.writeFloat(this.cropState.cropPh);
        r42.writeFloat(this.cropState.cropScale);
        r42.writeFloat(this.cropState.cropRotate);
        r42.writeInt32(this.cropState.transformWidth);
        r42.writeInt32(this.cropState.transformHeight);
        r42.writeInt32(this.cropState.transformRotation);
        r42.writeBool(this.cropState.mirrored);
    L65:
        r42.writeInt32(0);
        r42.writeBool(this.isStory);
        r42.writeBool(this.fromCamera);
        if (r3 == null) goto L68;
        r42.writeByte(1);
        r42.writeByteArray(r3);
    L69:
        r42.writeFloat(this.volume);
        r42.writeBool(this.isSticker);
        CollageLayout r17 = this.collage;
        if (r17 != null) goto L72;
    L81:
        r42.writeInt32(TLRPC.TL_null.constructor);
    L82:
        r12 = Utilities.bytesToHex(r42.toByteArray());
        r42.cleanup();
        goto L84
    L72:
        if (this.collageParts == null) goto L81;
        if (r17.parts.size() <= 1) goto L81;
        if (this.collageParts.isEmpty() == true) goto L81;
        r42.writeInt32(-559038737);
        r42.writeString(this.collage.toString());
    L79:
        if (r5 >= this.collageParts.size()) goto L82;
        this.collageParts.get(r5).serializeToStream(r42);
        r5 = r5 + 1;
        goto L79
    L68:
        r42.writeByte(0);
        goto L69
    L64:
        r42.writeByte(0);
        goto L65
    L55:
        if (r16.isEmpty() == true) goto L60;
        r42.writeByte(1);
        r42.writeInt32(this.mediaEntities.size());
        int r18 = this.mediaEntities.size();
        int r23 = 0;
    L57:
        if (r23 >= r18) goto L59;
        this.mediaEntities.get(r23).serializeTo(r42, false);
        r23 = r23 + 1;
        goto L57
    L59:
        r42.writeByte(this.isPhoto ? 1 : 0);
        goto L62
    L51:
        r42.writeByte(0);
        goto L52
    L35:
        r42.writeFloat(0.0f);
        r42.writeFloat(0.0f);
        goto L36
    L48:
        r42.writeByte(0);
        goto L49
    L26:
        r22 = null;
        goto L27
    L22:
        r13 = 10;
        goto L23
    }

    public boolean needConvert() {
        if (this.bitrate != (-2)) goto L6;
        return false;
    L6:
        if (this.isStory == false) goto L38;
        if (this.fromCamera == true) goto L11;
        return true;
    L11:
        if (this.mixedSoundInfos.isEmpty() == true) goto L13;
    L36:
        return true;
    L13:
        if (this.mediaEntities != null) goto L36;
        if (this.paintPath != null) goto L36;
        if (this.blurPath != null) goto L36;
        if (this.filterState != null) goto L36;
        MediaController.CropState r0 = this.cropState;
        if (r0 == null) goto L25;
        if (r0.isEmpty() == false) goto L36;
    L25:
        if (this.startTime > 0) goto L36;
        long r5 = this.endTime;
        if (r5 == (-1)) goto L31;
        if (r5 != this.estimatedDuration) goto L36;
    L31:
        if (this.originalHeight != this.resultHeight) goto L36;
        if (this.originalWidth != this.resultWidth) goto L36;
        return false;
    L38:
        if (this.mixedSoundInfos.isEmpty() == true) goto L40;
    L59:
        return true;
    L40:
        if (this.mediaEntities != null) goto L59;
        if (this.paintPath != null) goto L59;
        if (this.blurPath != null) goto L59;
        if (this.filterState != null) goto L59;
        if (this.cropState != null) goto L59;
        if (this.roundVideo == false) goto L59;
        if (this.startTime > 0) goto L59;
        long r52 = this.endTime;
        if (r52 != (-1)) goto L56;
    L58:
        return false;
    L56:
        if (r52 == this.estimatedDuration) goto L58;
        goto L58
    }

    public boolean parseString(String r18) {
        if (r18.length() >= 6) goto L92;
        return false;
    L92:
        String[] r2 = r18.split("_");     // Catch: Exception -> L19
        int r6 = 12;
        if (r2.length < 12) goto L89;
        this.startTime = Long.parseLong(r2[1]);     // Catch: Exception -> L19
        this.endTime = Long.parseLong(r2[2]);     // Catch: Exception -> L19
        this.rotationValue = Integer.parseInt(r2[3]);     // Catch: Exception -> L19
        this.originalWidth = Integer.parseInt(r2[4]);     // Catch: Exception -> L19
        this.originalHeight = Integer.parseInt(r2[5]);     // Catch: Exception -> L19
        this.bitrate = Integer.parseInt(r2[6]);     // Catch: Exception -> L19
        this.resultWidth = Integer.parseInt(r2[7]);     // Catch: Exception -> L19
        this.resultHeight = Integer.parseInt(r2[8]);     // Catch: Exception -> L19
        this.originalDuration = Long.parseLong(r2[9]);     // Catch: Exception -> L19
        this.framerate = Integer.parseInt(r2[10]);     // Catch: Exception -> L19
        this.videoOffset = Long.parseLong(r2[11]);     // Catch: Exception -> L19
        if (this.bitrate != (-1)) goto L11;
        boolean r14 = true;
    L12:
        this.muted = r14;     // Catch: Exception -> L19
        if (r2[12].startsWith("-") == false) goto L82;
        String r62 = r2[12].substring(1);     // Catch: Exception -> L19
        if (r62.length() <= 0) goto L81;
        SerializedData r142 = new SerializedData(Utilities.hexToBytes(r62));     // Catch: Exception -> L19
        int r63 = r142.readInt32(false);     // Catch: Exception -> L19
        if (r63 < 3) goto L22;
        this.avatarStartTime = r142.readInt64(false);     // Catch: Exception -> L19
        this.originalBitrate = r142.readInt32(false);     // Catch: Exception -> L19
    L22:
        if (r142.readByte(false) == 0) goto L39;
        MediaController.SavedFilterState r12 = new MediaController.SavedFilterState();     // Catch: Exception -> L19
        this.filterState = r12;     // Catch: Exception -> L19
        r12.enhanceValue = r142.readFloat(false);     // Catch: Exception -> L19
        if (r63 < 5) goto L26;
        this.filterState.softenSkinValue = r142.readFloat(false);     // Catch: Exception -> L19
    L26:
        this.filterState.exposureValue = r142.readFloat(false);     // Catch: Exception -> L19
        this.filterState.contrastValue = r142.readFloat(false);     // Catch: Exception -> L19
        this.filterState.warmthValue = r142.readFloat(false);     // Catch: Exception -> L19
        this.filterState.saturationValue = r142.readFloat(false);     // Catch: Exception -> L19
        this.filterState.fadeValue = r142.readFloat(false);     // Catch: Exception -> L19
        this.filterState.tintShadowsColor = r142.readInt32(false);     // Catch: Exception -> L19
        this.filterState.tintHighlightsColor = r142.readInt32(false);     // Catch: Exception -> L19
        this.filterState.highlightsValue = r142.readFloat(false);     // Catch: Exception -> L19
        this.filterState.shadowsValue = r142.readFloat(false);     // Catch: Exception -> L19
        this.filterState.vignetteValue = r142.readFloat(false);     // Catch: Exception -> L19
        this.filterState.grainValue = r142.readFloat(false);     // Catch: Exception -> L19
        this.filterState.blurType = r142.readInt32(false);     // Catch: Exception -> L19
        this.filterState.sharpenValue = r142.readFloat(false);     // Catch: Exception -> L19
        this.filterState.blurExcludeSize = r142.readFloat(false);     // Catch: Exception -> L19
        float r10 = r142.readFloat(false);     // Catch: Exception -> L19
        float r122 = r142.readFloat(false);     // Catch: Exception -> L19
        this.filterState.blurExcludePoint = new PointF(r10, r122);     // Catch: Exception -> L19
        this.filterState.blurExcludeBlurSize = r142.readFloat(false);     // Catch: Exception -> L19
        this.filterState.blurAngle = r142.readFloat(false);     // Catch: Exception -> L19
        int r8 = 0;
    L27:
        if (r8 >= 4) goto L39;
        if (r8 != 0) goto L30;
        PhotoFilterView.CurvesValue r102 = this.filterState.curvesToolValue.luminanceCurve;     // Catch: Exception -> L19
    L37:
        r102.blacksLevel = r142.readFloat(false);     // Catch: Exception -> L19
        r102.shadowsLevel = r142.readFloat(false);     // Catch: Exception -> L19
        r102.midtonesLevel = r142.readFloat(false);     // Catch: Exception -> L19
        r102.highlightsLevel = r142.readFloat(false);     // Catch: Exception -> L19
        r102.whitesLevel = r142.readFloat(false);     // Catch: Exception -> L19
        r8 = r8 + 1;     // Catch: Exception -> L19
        goto L27
    L30:
        if (r8 != 1) goto L33;
        r102 = this.filterState.curvesToolValue.redCurve;     // Catch: Exception -> L19
        goto L37
    L33:
        MediaController.SavedFilterState r103 = this.filterState;
        if (r8 != 2) goto L36;
        r102 = r103.curvesToolValue.greenCurve;     // Catch: Exception -> L19
        goto L37
    L36:
        r102 = r103.curvesToolValue.blueCurve;     // Catch: Exception -> L19
    L39:
        if (r142.readByte(false) == 0) goto L42;
        this.paintPath = new String(r142.readByteArray(false));     // Catch: Exception -> L19
    L42:
        if (r142.readByte(false) == 0) goto L51;
        int r82 = r142.readInt32(false);     // Catch: Exception -> L19
        this.mediaEntities = new ArrayList(r82);     // Catch: Exception -> L19
        int r104 = 0;
    L44:
        if (r104 >= r82) goto L47;
        this.mediaEntities.add(new MediaEntity(r142, false));     // Catch: Exception -> L19
        r104 = r104 + 1;     // Catch: Exception -> L19
        goto L44
    L47:
        if (r142.readByte(false) != 1) goto L49;
        boolean r83 = true;
    L50:
        this.isPhoto = r83;     // Catch: Exception -> L19
        goto L51
    L49:
        r83 = false;
    L51:
        if (r63 >= 2) goto L53;
    L57:
        if (r63 < 6) goto L59;
        r142.readInt32(false);     // Catch: Exception -> L19
    L59:
        if (r63 < 7) goto L62;
        this.isStory = r142.readBool(false);     // Catch: Exception -> L19
        this.fromCamera = r142.readBool(false);     // Catch: Exception -> L19
    L62:
        if (r63 < 8) goto L67;
        if (r142.readByte(false) == 0) goto L67;
        this.blurPath = new String(r142.readByteArray(false));     // Catch: Exception -> L19
    L67:
        if (r63 < 9) goto L70;
        this.volume = r142.readFloat(false);     // Catch: Exception -> L19
    L70:
        if (r63 < 10) goto L73;
        this.isSticker = r142.readBool(false);     // Catch: Exception -> L19
    L73:
        if (r63 >= 11) goto L75;
    L80:
        r142.cleanup();     // Catch: Exception -> L19
        goto L81
    L75:
        if (r142.readInt32(false) != (-559038737)) goto L80;
        this.collage = new CollageLayout(r142.readString(false));     // Catch: Exception -> L19
        this.collageParts = new ArrayList();     // Catch: Exception -> L19
        int r4 = 0;
    L78:
        if (r4 >= this.collage.parts.size()) goto L80;
        Part r5 = new Part();     // Catch: Exception -> L19
        r5.part = this.collage.parts.get(r4);     // Catch: Exception -> L19
        r5.readParams(r142, false);     // Catch: Exception -> L19
        this.collageParts.add(r5);     // Catch: Exception -> L19
        r4 = r4 + 1;     // Catch: Exception -> L19
        goto L78
    L53:
        if (r142.readByte(false) == 0) goto L57;
        MediaController.CropState r52 = new MediaController.CropState();     // Catch: Exception -> L19
        this.cropState = r52;     // Catch: Exception -> L19
        r52.cropPx = r142.readFloat(false);     // Catch: Exception -> L19
        this.cropState.cropPy = r142.readFloat(false);     // Catch: Exception -> L19
        this.cropState.cropPw = r142.readFloat(false);     // Catch: Exception -> L19
        this.cropState.cropPh = r142.readFloat(false);     // Catch: Exception -> L19
        this.cropState.cropScale = r142.readFloat(false);     // Catch: Exception -> L19
        this.cropState.cropRotate = r142.readFloat(false);     // Catch: Exception -> L19
        this.cropState.transformWidth = r142.readInt32(false);     // Catch: Exception -> L19
        this.cropState.transformHeight = r142.readInt32(false);     // Catch: Exception -> L19
        this.cropState.transformRotation = r142.readInt32(false);     // Catch: Exception -> L19
        if (r63 < 4) goto L57;
        this.cropState.mirrored = r142.readBool(false);     // Catch: Exception -> L19
    L81:
        r6 = 13;
    L82:
        if (r6 >= r2.length) goto L89;
        if (this.originalPath != null) goto L87;
        this.originalPath = r2[r6];     // Catch: Exception -> L19
    L88:
        r6 = r6 + 1;
        goto L82
    L87:
        this.originalPath += "_" + r2[r6];
        goto L88
    L11:
        r14 = false;
    L89:
        return true;
    L19:
        e = move-exception;
        FileLog.e(e);
        return false;
    }
}
