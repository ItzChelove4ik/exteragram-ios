package org.telegram.ui.Stories.recorder;

import android.text.SpannableString;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.tgnet.AbstractSerializedData;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.Theme;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public class DraftsController {
    public final int currentAccount;
    public final ArrayList<StoryEntry> drafts;
    private File draftsFolder;
    private boolean loaded;
    private boolean loadedFailed;
    private boolean loading;
    private boolean loadingFailed;

    /* loaded from: classes7.dex */
    public static class StoryDraft {
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
        public TLRPC.InputMedia botEdit;
        public long botId;
        public String botLang;
        public String caption;
        public ArrayList<TLRPC.MessageEntity> captionEntities;
        public CollageLayout collage;
        public ArrayList<VideoEditedInfo.Part> collageParts;
        public MediaController.CropState crop;
        public long date;
        public long duration;
        public long editDocumentId;
        public long editExpireDate;
        public long editPhotoId;
        public int editStoryId;
        public long editStoryPeerId;
        public TLRPC.TL_error error;
        public String file;
        public boolean fileDeletable;
        private String filterFilePath;
        private MediaController.SavedFilterState filterState;
        public String fullThumb;
        public int gradientBottomColor;
        public int gradientTopColor;
        public int height;
        public long id;
        public int invert;
        public boolean isEdit;
        public boolean isError;
        public boolean isVideo;
        public long left;
        public final float[] matrixValues;
        public ArrayList<VideoEditedInfo.MediaEntity> mediaEntities;
        public boolean muted;
        public int orientation;
        public String paintEntitiesFilePath;
        public String paintFilePath;
        public TLRPC.InputPeer peer;
        private int period;
        public final ArrayList<TLRPC.InputPrivacyRule> privacyRules;
        public int resultHeight;
        public int resultWidth;
        public long right;
        public long roundDuration;
        public float roundLeft;
        public long roundOffset;
        public String roundPath;
        public float roundRight;
        public String roundThumb;
        public float roundVolume;
        public List<TLRPC.InputDocument> stickers;
        public String thumb;
        public float videoVolume;
        public int width;

        public StoryDraft(AbstractSerializedData r9, boolean r10) {
            this.matrixValues = new float[9];
            this.privacyRules = new ArrayList();
            this.audioRight = 1.0f;
            this.audioVolume = 1.0f;
            this.roundVolume = 1.0f;
            this.videoVolume = 1.0f;
            if (r9.readInt32(r10) == (-1318387531)) goto L8;
            if (r10 == false) goto L177;
            GlShader$$ExternalSyntheticBUOutline1.m("StoryDraft parse error");
            throw null;
        L177:
            return;
        L8:
            this.date = r9.readInt64(r10);
            String r0 = r9.readString(r10);
            this.thumb = r0;
            if (r0 != null) goto L11;
        L13:
            this.isVideo = r9.readBool(r10);
            String r02 = r9.readString(r10);
            this.file = r02;
            if (r02 != null) goto L16;
        L18:
            this.fileDeletable = r9.readBool(r10);
            this.muted = r9.readBool(r10);
            this.left = r9.readInt64(r10);
            this.right = r9.readInt64(r10);
            this.orientation = r9.readInt32(r10);
            this.invert = r9.readInt32(r10);
            this.width = r9.readInt32(r10);
            this.height = r9.readInt32(r10);
            this.resultWidth = r9.readInt32(r10);
            this.resultHeight = r9.readInt32(r10);
            this.duration = r9.readInt64(r10);
            int r03 = 0;
            int r1 = 0;
        L19:
            float[] r3 = this.matrixValues;
            if (r1 >= r3.length) goto L22;
            r3[r1] = r9.readFloat(r10);
            r1 = r1 + 1;
            goto L19
        L22:
            this.gradientTopColor = r9.readInt32(r10);
            this.gradientBottomColor = r9.readInt32(r10);
            String r12 = r9.readString(r10);
            this.caption = r12;
            if (r12 == null) goto L28;
            if (r12.length() != 0) goto L28;
            this.caption = null;
        L28:
            if (r9.readInt32(r10) == 481674261) goto L33;
            if (r10 == false) goto L178;
            GlShader$$ExternalSyntheticBUOutline1.m("Vector magic in StoryDraft parse error (1)");
            throw null;
        L178:
            return;
        L33:
            int r13 = r9.readInt32(r10);
            int r4 = 0;
        L34:
            if (r4 >= r13) goto L40;
            if (this.captionEntities != null) goto L38;
            this.captionEntities = new ArrayList();
        L38:
            this.captionEntities.add(TLRPC.MessageEntity.TLdeserialize(r9, r9.readInt32(r10), r10));
            r4 = r4 + 1;
            goto L34
        L40:
            if (r9.readInt32(r10) == 481674261) goto L45;
            if (r10 == false) goto L179;
            GlShader$$ExternalSyntheticBUOutline1.m("Vector magic in StoryDraft parse error (2)");
            throw null;
        L179:
            return;
        L45:
            int r14 = r9.readInt32(r10);
            this.privacyRules.clear();
            int r42 = 0;
        L46:
            if (r42 >= r14) goto L48;
            this.privacyRules.add(TLRPC.InputPrivacyRule.TLdeserialize(r9, r9.readInt32(r10), r10));
            r42 = r42 + 1;
            goto L46
        L48:
            r9.readBool(r10);
            String r15 = r9.readString(r10);
            this.paintFilePath = r15;
            if (r15 != null) goto L51;
        L53:
            this.averageDuration = r9.readInt64(r10);
            if (r9.readInt32(r10) == 481674261) goto L59;
            if (r10 == false) goto L180;
            GlShader$$ExternalSyntheticBUOutline1.m("Vector magic in StoryDraft parse error (3)");
            throw null;
        L180:
            return;
        L59:
            int r16 = r9.readInt32(r10);
            int r43 = 0;
        L60:
            if (r43 >= r16) goto L66;
            if (this.mediaEntities != null) goto L64;
            this.mediaEntities = new ArrayList();
        L64:
            this.mediaEntities.add(new VideoEditedInfo.MediaEntity(r9, true, r10));
            r43 = r43 + 1;
            goto L60
        L66:
            if (r9.readInt32(r10) == 481674261) goto L71;
            if (r10 == false) goto L181;
            GlShader$$ExternalSyntheticBUOutline1.m("Vector magic in StoryDraft parse error (4)");
            throw null;
        L181:
            return;
        L71:
            int r17 = r9.readInt32(r10);
            int r44 = 0;
        L72:
            if (r44 >= r17) goto L77;
            if (this.stickers != null) goto L76;
            this.stickers = new ArrayList();
        L76:
            this.stickers.add(TLRPC.InputDocument.TLdeserialize(r9, r9.readInt32(r10), r10));
            r44 = r44 + 1;
            goto L72
        L77:
            String r18 = r9.readString(r10);
            this.filterFilePath = r18;
            if (r18 != null) goto L80;
        L82:
            int r19 = r9.readInt32(r10);
            if (r19 != 1450380236) goto L86;
            this.filterState = null;
        L89:
            if (r9.remaining() < 4) goto L92;
            this.period = r9.readInt32(r10);
        L92:
            if (r9.remaining() <= 0) goto L101;
            if (r9.readInt32(r10) == 481674261) goto L99;
            if (r10 == false) goto L182;
            GlShader$$ExternalSyntheticBUOutline1.m("Vector magic in StoryDraft parse error (5)");
            throw null;
        L182:
            return;
        L99:
            r9.readInt32(r10);
        L101:
            if (r9.remaining() <= 0) goto L104;
            this.isEdit = r9.readBool(r10);
            this.editStoryId = r9.readInt32(r10);
            this.editStoryPeerId = r9.readInt64(r10);
            this.editExpireDate = r9.readInt64(r10);
            this.editPhotoId = r9.readInt64(r10);
            this.editDocumentId = r9.readInt64(r10);
        L104:
            if (r9.remaining() <= 0) goto L111;
            String r110 = r9.readString(r10);
            this.paintEntitiesFilePath = r110;
            if (r110 == null) goto L111;
            if (r110.length() != 0) goto L111;
            this.paintEntitiesFilePath = null;
        L111:
            if (r9.remaining() <= 0) goto L118;
            this.isError = r9.readBool(r10);
            int r111 = r9.readInt32(r10);
            if (r111 != 1450380236) goto L115;
            this.error = null;
        L116:
            this.fullThumb = r9.readString(r10);
            goto L118
        L115:
            this.error = TLRPC.TL_error.TLdeserialize(r9, r111, r10);
        L118:
            if (r9.remaining() <= 0) goto L129;
            if (r9.readInt32(r10) != (-1739392570)) goto L129;
            this.audioPath = r9.readString(r10);
            if (r9.readInt32(r10) != (-1222740358)) goto L125;
            this.audioAuthor = r9.readString(r10);
        L125:
            if (r9.readInt32(r10) != (-1222740358)) goto L127;
            this.audioTitle = r9.readString(r10);
        L127:
            this.audioDuration = r9.readInt64(r10);
            this.audioOffset = r9.readInt64(r10);
            this.audioLeft = r9.readFloat(r10);
            this.audioRight = r9.readFloat(r10);
            this.audioVolume = r9.readFloat(r10);
        L129:
            if (r9.remaining() <= 0) goto L132;
            this.peer = TLRPC.InputPeer.TLdeserialize(r9, r9.readInt32(r10), r10);
        L132:
            if (r9.remaining() <= 0) goto L137;
            if (r9.readInt32(r10) != 1137015880) goto L137;
            this.roundPath = r9.readString(r10);
            this.roundDuration = r9.readInt64(r10);
            this.roundOffset = r9.readInt64(r10);
            this.roundLeft = r9.readFloat(r10);
            this.roundRight = r9.readFloat(r10);
            this.roundVolume = r9.readFloat(r10);
        L137:
            if (r9.remaining() <= 0) goto L140;
            this.videoVolume = r9.readFloat(r10);
        L140:
            if (r9.remaining() <= 0) goto L145;
            this.botId = r9.readInt64(r10);
            this.botLang = r9.readString(r10);
            int r112 = r9.readInt32(r10);
            if (r112 == 1450380236) goto L145;
            this.botEdit = TLRPC.InputMedia.TLdeserialize(r9, r112, r10);
        L145:
            if (r9.remaining() <= 0) goto L153;
            if (r9.readInt32(r10) != (-559038737)) goto L153;
            this.collage = new CollageLayout(r9.readString(r10));
            this.collageParts = new ArrayList();
        L150:
            if (r03 >= this.collage.parts.size()) goto L153;
            VideoEditedInfo.Part r113 = new VideoEditedInfo.Part();
            r113.readParams(r9, r10);
            r113.part = this.collage.parts.get(r03);
            this.collageParts.add(r113);
            r03 = r03 + 1;
        L153:
            if (r9.remaining() <= 0) goto L158;
            if (r9.readInt32(r10) != 1151577037) goto L158;
            MediaController.CropState r04 = new MediaController.CropState();
            this.crop = r04;
            r04.readParams(r9, r10);
        L158:
            if (r9.remaining() <= 0) goto L175;
            int r05 = r9.readInt32(r10);
            if (r05 != 448771445) goto L176;
            this.audioDocument = TLRPC.InputDocument.TLdeserialize(r9, r05, r10);
            return;
        L176:
            return;
        L175:
            return;
        L86:
            if (r19 != (-1318387530)) goto L89;
            MediaController.SavedFilterState r114 = new MediaController.SavedFilterState();
            this.filterState = r114;
            r114.readParams(r9, r10);
            goto L89
        L80:
            if (r18.length() != 0) goto L82;
            this.filterFilePath = null;
            goto L82
        L51:
            if (r15.length() != 0) goto L53;
            this.paintFilePath = null;
            goto L53
        L16:
            if (r02.length() != 0) goto L18;
            this.file = null;
            goto L18
        L11:
            if (r0.length() != 0) goto L13;
            this.thumb = null;
            goto L13
        }

        public StoryDraft(StoryEntry r9) {
            float[] r0 = new float[9];
            this.matrixValues = r0;
            ArrayList<TLRPC.InputPrivacyRule> r1 = new ArrayList();
            this.privacyRules = r1;
            this.audioRight = 1.0f;
            this.audioVolume = 1.0f;
            this.roundVolume = 1.0f;
            this.videoVolume = 1.0f;
            this.id = r9.draftId;
            this.date = r9.draftDate;
            File r2 = r9.draftThumbFile;
            String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r2 != null) goto L5;
            String r22 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            this.thumb = r22;
            File r23 = r9.uploadThumbFile;
            if (r23 != null) goto L9;
            String r24 = _UrlKt.FRAGMENT_ENCODE_SET;
        L10:
            this.fullThumb = r24;
            this.isVideo = r9.isVideo;
            File r25 = r9.file;
            if (r25 != null) goto L13;
            String r26 = _UrlKt.FRAGMENT_ENCODE_SET;
        L14:
            this.file = r26;
            this.fileDeletable = r9.fileDeletable;
            this.muted = r9.muted;
            float r27 = r9.left;
            long r4 = r9.duration;
            this.left = (long) (r27 * r4);
            this.right = (long) (r9.right * r4);
            this.orientation = r9.orientation;
            this.invert = r9.invert;
            this.width = r9.width;
            this.height = r9.height;
            this.crop = r9.crop;
            this.resultWidth = r9.resultWidth;
            this.resultHeight = r9.resultHeight;
            this.duration = r4;
            r9.matrix.getValues(r0);
            this.gradientTopColor = r9.gradientTopColor;
            this.gradientBottomColor = r9.gradientBottomColor;
            CharSequence[] r42 = {r9.caption};
            String r6 = null;
            if (r9.captionEntitiesAllowed == false) goto L17;
            ArrayList<TLRPC.MessageEntity> r02 = MediaDataController.getInstance(r9.currentAccount).getEntities(r42, true);
        L18:
            this.captionEntities = r02;
            CharSequence r03 = r42[0];
            if (r03 != null) goto L21;
            String r04 = _UrlKt.FRAGMENT_ENCODE_SET;
        L22:
            this.caption = r04;
            r1.addAll(r9.privacyRules);
            File r05 = r9.paintFile;
            if (r05 != null) goto L25;
            String r06 = _UrlKt.FRAGMENT_ENCODE_SET;
        L26:
            this.paintFilePath = r06;
            File r07 = r9.paintEntitiesFile;
            if (r07 != null) goto L29;
            String r08 = _UrlKt.FRAGMENT_ENCODE_SET;
        L30:
            this.paintEntitiesFilePath = r08;
            this.averageDuration = r9.averageDuration;
            this.mediaEntities = r9.mediaEntities;
            this.stickers = r9.stickers;
            File r09 = r9.filterFile;
            if (r09 == null) goto L34;
            r3 = r09.toString();
        L34:
            this.filterFilePath = r3;
            this.filterState = r9.filterState;
            this.period = r9.period;
            this.isError = r9.isError;
            this.error = r9.error;
            this.audioPath = r9.audioPath;
            this.audioDocument = r9.audioDocument;
            this.audioAuthor = r9.audioAuthor;
            this.audioTitle = r9.audioTitle;
            this.audioDuration = r9.audioDuration;
            this.audioOffset = r9.audioOffset;
            this.audioLeft = r9.audioLeft;
            this.audioRight = r9.audioRight;
            this.audioVolume = r9.audioVolume;
            File r010 = r9.round;
            if (r010 == null) goto L38;
            r6 = r010.getAbsolutePath();
        L38:
            this.roundPath = r6;
            this.roundThumb = r9.roundThumb;
            this.roundDuration = r9.roundDuration;
            this.roundOffset = r9.roundOffset;
            this.roundLeft = r9.roundLeft;
            this.roundRight = r9.roundRight;
            this.roundVolume = r9.roundVolume;
            this.videoVolume = r9.videoVolume;
            this.peer = r9.peer;
            this.botId = r9.botId;
            this.botLang = r9.botLang;
            this.botEdit = r9.editingBotPreview;
            this.collage = r9.collage;
            this.collageParts = VideoEditedInfo.Part.toParts(r9);
            return;
        L29:
            r08 = r07.toString();
            goto L30
        L25:
            r06 = r05.toString();
            goto L26
        L21:
            r04 = r03.toString();
            goto L22
        L17:
            r02 = null;
            goto L18
        L13:
            r26 = r25.toString();
            goto L14
        L9:
            r24 = r23.toString();
            goto L10
        L5:
            r22 = r2.toString();
            goto L6
        }

        public int getObjectSize() {
            NativeByteBuffer r0 = new NativeByteBuffer(true);
            toStream(r0);
            return r0.length();
        }

        public StoryEntry toEntry() {
            StoryEntry r0 = new StoryEntry();
            r0.draftId = this.id;
            r0.isDraft = true;
            r0.draftDate = this.date;
            if (TextUtils.isEmpty(this.thumb) == true) goto L6;
            r0.draftThumbFile = new File(this.thumb);
        L6:
            if (TextUtils.isEmpty(this.fullThumb) == true) goto L8;
            r0.uploadThumbFile = new File(this.fullThumb);
        L8:
            r0.isVideo = this.isVideo;
            if (this.file == null) goto L11;
            r0.file = new File(this.file);
        L11:
            r0.fileDeletable = this.fileDeletable;
            r0.muted = this.muted;
            long r2 = this.duration;
            r0.duration = r2;
            if (r2 <= 0) goto L14;
            r0.left = this.left / r2;
            r0.right = this.right / r2;
        L15:
            r0.orientation = this.orientation;
            r0.invert = this.invert;
            r0.width = this.width;
            r0.height = this.height;
            r0.crop = this.crop;
            r0.resultWidth = this.resultWidth;
            r0.resultHeight = this.resultHeight;
            r0.matrix.setValues(this.matrixValues);
            r0.gradientTopColor = this.gradientTopColor;
            r0.gradientBottomColor = this.gradientBottomColor;
            if (this.caption == null) goto L21;
            SpannableString r22 = new SpannableString(this.caption);
            if (Theme.chat_msgTextPaint != null) goto L20;
            Theme.createCommonMessageResources();
        L20:
            CharSequence r4 = Emoji.replaceEmoji(r22, Theme.chat_msgTextPaint.getFontMetricsInt(), true);
            MessageObject.addEntitiesToText(r4, this.captionEntities, true, false, true, false);
            r0.caption = MessageObject.replaceAnimatedEmoji(r4, this.captionEntities, Theme.chat_msgTextPaint.getFontMetricsInt());
        L22:
            r0.privacyRules.clear();
            r0.privacyRules.addAll(this.privacyRules);
            if (this.paintFilePath == null) goto L26;
            r0.paintFile = new File(this.paintFilePath);
        L26:
            if (this.paintEntitiesFilePath == null) goto L28;
            r0.paintEntitiesFile = new File(this.paintEntitiesFilePath);
        L28:
            r0.averageDuration = this.averageDuration;
            r0.mediaEntities = this.mediaEntities;
            r0.stickers = this.stickers;
            if (this.filterFilePath == null) goto L31;
            r0.filterFile = new File(this.filterFilePath);
        L31:
            r0.filterState = this.filterState;
            r0.period = this.period;
            r0.isEdit = this.isEdit;
            r0.editStoryId = this.editStoryId;
            r0.editStoryPeerId = this.editStoryPeerId;
            r0.editExpireDate = this.editExpireDate;
            r0.editPhotoId = this.editPhotoId;
            r0.editDocumentId = this.editDocumentId;
            r0.isError = this.isError;
            r0.error = this.error;
            r0.audioPath = this.audioPath;
            r0.audioDocument = this.audioDocument;
            r0.audioAuthor = this.audioAuthor;
            r0.audioTitle = this.audioTitle;
            r0.audioDuration = this.audioDuration;
            r0.audioOffset = this.audioOffset;
            r0.audioLeft = this.audioLeft;
            r0.audioRight = this.audioRight;
            r0.audioVolume = this.audioVolume;
            if (this.roundPath == null) goto L34;
            r0.round = new File(this.roundPath);
        L34:
            r0.roundThumb = this.roundThumb;
            r0.roundDuration = this.roundDuration;
            r0.roundOffset = this.roundOffset;
            r0.roundLeft = this.roundLeft;
            r0.roundRight = this.roundRight;
            r0.roundVolume = this.roundVolume;
            r0.videoVolume = this.videoVolume;
            r0.peer = this.peer;
            r0.botId = this.botId;
            r0.botLang = this.botLang;
            r0.editingBotPreview = this.botEdit;
            r0.collage = this.collage;
            r0.collageContent = VideoEditedInfo.Part.toStoryEntries(this.collageParts);
            return r0;
        L21:
            r0.caption = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L22
        L14:
            r0.left = 0.0f;
            r0.right = 1.0f;
            goto L15
        }

        public void toStream(AbstractSerializedData r7) {
            r7.writeInt32(-1318387531);
            r7.writeInt64(this.date);
            r7.writeString(this.thumb);
            r7.writeBool(this.isVideo);
            r7.writeString(this.file);
            r7.writeBool(this.fileDeletable);
            r7.writeBool(this.muted);
            r7.writeInt64(this.left);
            r7.writeInt64(this.right);
            r7.writeInt32(this.orientation);
            r7.writeInt32(this.invert);
            r7.writeInt32(this.width);
            r7.writeInt32(this.height);
            r7.writeInt32(this.resultWidth);
            r7.writeInt32(this.resultHeight);
            r7.writeInt64(this.duration);
            int r0 = 0;
            int r1 = 0;
        L3:
            float[] r2 = this.matrixValues;
            if (r1 >= r2.length) goto L6;
            r7.writeFloat(r2[r1]);
            r1 = r1 + 1;
            goto L3
        L6:
            r7.writeInt32(this.gradientTopColor);
            r7.writeInt32(this.gradientBottomColor);
            r7.writeString(this.caption);
            r7.writeInt32(Vector.constructor);
            ArrayList<TLRPC.MessageEntity> r22 = this.captionEntities;
            if (r22 != null) goto L9;
            int r23 = 0;
        L10:
            r7.writeInt32(r23);
            if (this.captionEntities == null) goto L16;
            int r24 = 0;
        L14:
            if (r24 >= this.captionEntities.size()) goto L16;
            this.captionEntities.get(r24).serializeToStream(r7);
            r24 = r24 + 1;
        L16:
            r7.writeInt32(Vector.constructor);
            ArrayList<TLRPC.InputPrivacyRule> r25 = this.privacyRules;
            if (r25 != null) goto L19;
            int r26 = 0;
        L20:
            r7.writeInt32(r26);
            if (this.privacyRules == null) goto L26;
            int r27 = 0;
        L24:
            if (r27 >= this.privacyRules.size()) goto L26;
            this.privacyRules.get(r27).serializeToStream(r7);
            r27 = r27 + 1;
        L26:
            r7.writeBool(false);
            r7.writeString(this.paintFilePath);
            r7.writeInt64(this.averageDuration);
            r7.writeInt32(Vector.constructor);
            ArrayList<VideoEditedInfo.MediaEntity> r28 = this.mediaEntities;
            if (r28 != null) goto L29;
            int r29 = 0;
        L30:
            r7.writeInt32(r29);
            if (this.mediaEntities == null) goto L36;
            int r210 = 0;
        L34:
            if (r210 >= this.mediaEntities.size()) goto L36;
            this.mediaEntities.get(r210).serializeTo(r7, true);
            r210 = r210 + 1;
        L36:
            r7.writeInt32(Vector.constructor);
            List<TLRPC.InputDocument> r211 = this.stickers;
            if (r211 != null) goto L39;
            int r212 = 0;
        L40:
            r7.writeInt32(r212);
            if (this.stickers == null) goto L46;
            int r213 = 0;
        L44:
            if (r213 >= this.stickers.size()) goto L46;
            this.stickers.get(r213).serializeToStream(r7);
            r213 = r213 + 1;
        L46:
            String r214 = this.filterFilePath;
            String r4 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r214 != null) goto L49;
            r214 = _UrlKt.FRAGMENT_ENCODE_SET;
        L49:
            r7.writeString(r214);
            if (this.filterState != null) goto L52;
            r7.writeInt32(TLRPC.TL_null.constructor);
        L53:
            r7.writeInt32(this.period);
            r7.writeInt32(Vector.constructor);
            r7.writeInt32(0);
            r7.writeBool(this.isEdit);
            r7.writeInt32(this.editStoryId);
            r7.writeInt64(this.editStoryPeerId);
            r7.writeInt64(this.editExpireDate);
            r7.writeInt64(this.editPhotoId);
            r7.writeInt64(this.editDocumentId);
            r7.writeString(this.paintEntitiesFilePath);
            r7.writeBool(this.isError);
            TLRPC.TL_error r12 = this.error;
            if (r12 != null) goto L56;
            r7.writeInt32(TLRPC.TL_null.constructor);
        L57:
            r7.writeString(this.fullThumb);
            if (this.audioPath != null) goto L60;
            r7.writeInt32(TLRPC.TL_null.constructor);
        L69:
            TLRPC.InputPeer r13 = this.peer;
            if (r13 == null) goto L72;
            r13.serializeToStream(r7);
        L74:
            if (TextUtils.isEmpty(this.roundPath) == false) goto L76;
            r7.writeInt32(TLRPC.TL_null.constructor);
        L77:
            r7.writeFloat(this.videoVolume);
            r7.writeInt64(this.botId);
            String r14 = this.botLang;
            if (r14 == null) goto L81;
            r4 = r14;
        L81:
            r7.writeString(r4);
            TLRPC.InputMedia r15 = this.botEdit;
            if (r15 != null) goto L84;
            r7.writeInt32(TLRPC.TL_null.constructor);
        L85:
            CollageLayout r16 = this.collage;
            if (r16 != null) goto L88;
        L97:
            r7.writeInt32(TLRPC.TL_null.constructor);
        L98:
            MediaController.CropState r02 = this.crop;
            if (r02 != null) goto L101;
            r7.writeInt32(TLRPC.TL_null.constructor);
        L102:
            TLRPC.InputDocument r6 = this.audioDocument;
            if (r6 != null) goto L106;
            r7.writeInt32(TLRPC.TL_null.constructor);
            return;
        L106:
            r6.serializeToStream(r7);
            return;
        L101:
            r02.serializeToStream(r7);
            goto L102
        L88:
            if (r16.parts.size() <= 1) goto L97;
            ArrayList<VideoEditedInfo.Part> r17 = this.collageParts;
            if (r17 == null) goto L97;
            if (r17.size() <= 1) goto L97;
            r7.writeInt32(-559038737);
            r7.writeString(this.collage.toString());
            ArrayList<VideoEditedInfo.Part> r18 = this.collageParts;
            int r215 = r18.size();
        L95:
            if (r0 >= r215) goto L98;
            VideoEditedInfo.Part r3 = r18.get(r0);
            r0 = r0 + 1;
            r3.serializeToStream(r7);
            goto L95
        L84:
            r15.serializeToStream(r7);
            goto L85
        L76:
            r7.writeInt32(TLRPC.TL_documentAttributeVideo.constructor);
            r7.writeString(this.roundPath);
            r7.writeInt64(this.roundDuration);
            r7.writeInt64(this.roundOffset);
            r7.writeFloat(this.roundLeft);
            r7.writeFloat(this.roundRight);
            r7.writeFloat(this.roundVolume);
            goto L77
        L72:
            new TLRPC.TL_inputPeerSelf().serializeToStream(r7);
            goto L74
        L60:
            r7.writeInt32(TLRPC.TL_documentAttributeAudio.constructor);
            r7.writeString(this.audioPath);
            if (this.audioAuthor != null) goto L63;
            r7.writeInt32(TLRPC.TL_null.constructor);
        L65:
            if (this.audioTitle != null) goto L67;
            r7.writeInt32(TLRPC.TL_null.constructor);
        L68:
            r7.writeInt64(this.audioDuration);
            r7.writeInt64(this.audioOffset);
            r7.writeFloat(this.audioLeft);
            r7.writeFloat(this.audioRight);
            r7.writeFloat(this.audioVolume);
            goto L69
        L67:
            r7.writeInt32(TLRPC.TL_jsonString.constructor);
            r7.writeString(this.audioTitle);
            goto L68
        L63:
            r7.writeInt32(TLRPC.TL_jsonString.constructor);
            r7.writeString(this.audioAuthor);
            goto L65
        L56:
            r12.serializeToStream(r7);
            goto L57
        L52:
            r7.writeInt32(-1318387530);
            this.filterState.serializeToStream(r7);
            goto L53
        L39:
            r212 = r211.size();
            goto L40
        L29:
            r29 = r28.size();
            goto L30
        L19:
            r26 = r25.size();
            goto L20
        L9:
            r23 = r22.size();
            goto L10
        }
    }

    public static /* synthetic */ void $r8$lambda$4bqFG92252BDPlMBAIVVPuoFYXc(MessagesStorage r8, boolean r9, Utilities.Callback r10) {
        ArrayList r1 = new ArrayList();
        SQLiteCursor r2 = null;
        SQLiteDatabase r82 = r8.getDatabase();     // Catch: Throwable -> L9 Exception -> L11
        if (r82 != null) goto L6;
        return;
    L6:
        ArrayList r3 = new ArrayList();     // Catch: Throwable -> L9 Exception -> L11
        StringBuilder r4 = new StringBuilder("SELECT id, data, type FROM story_drafts WHERE type = ");     // Catch: Throwable -> L9 Exception -> L11
        if (r9 == false) goto L13;
        String r92 = "2";
    L14:
        r4.append(r92);     // Catch: Throwable -> L9 Exception -> L11
        r4.append(" ORDER BY date DESC");     // Catch: Throwable -> L9 Exception -> L11
        int r0 = 0;
        r2 = r82.queryFinalized(r4.toString(), new Object[0]);     // Catch: Throwable -> L9 Exception -> L11
    L16:
        if (r2.next() == false) goto L24;
        long r42 = r2.longValue(0);     // Catch: Throwable -> L9 Exception -> L11
        NativeByteBuffer r6 = r2.byteBufferValue(1);     // Catch: Throwable -> L9 Exception -> L11
        if (r6 == null) goto L16;
        StoryDraft r7 = new StoryDraft(r6, true);     // Catch: Throwable -> L9 Exception -> L21
        r7.id = r42;     // Catch: Throwable -> L9 Exception -> L21
        r1.add(r7);     // Catch: Throwable -> L9 Exception -> L21
    L23:
        r6.reuse();     // Catch: Throwable -> L9 Exception -> L11
    L21:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L9 Exception -> L11
        r3.add(Long.valueOf(r42));     // Catch: Throwable -> L9 Exception -> L11
        goto L23
    L24:
        r2.dispose();     // Catch: Throwable -> L9 Exception -> L11
        if (r3.size() <= 0) goto L30;
    L26:
        if (r0 >= r3.size()) goto L30;
        r82.executeFast("DELETE FROM story_drafts WHERE id = " + r3.get(r0)).stepThis().dispose();     // Catch: Throwable -> L9 Exception -> L11
        r0 = r0 + 1;
        goto L26
    L30:
        r2.dispose();
    L34:
        AndroidUtilities.runOnUIThread(new DraftsController$$ExternalSyntheticLambda4(r10, r1));
        return;
    L13:
        r92 = "0 OR type = 1";
    L9:
        th = move-exception;
        if (r2 == null) goto L38;
        r2.dispose();
    L38:
        throw th;
    L11:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L9
        if (r2 == null) goto L34;
        goto L34
    }

    public static /* synthetic */ void $r8$lambda$6IXZXV8p4lXLYDofSjSWK6WcFX8(MessagesStorage r5, StoryDraft r6) {
        SQLitePreparedStatement r0 = null;
        SQLiteDatabase r52 = r5.getDatabase();     // Catch: Throwable -> L16 Exception -> L18
        if (r52 == null) goto L29;
        r0 = r52.executeFast("INSERT INTO story_drafts VALUES (?, ?, ?, ?)");     // Catch: Throwable -> L16 Exception -> L18
        r0.requery();     // Catch: Throwable -> L16 Exception -> L18
        NativeByteBuffer r53 = new NativeByteBuffer(r6.getObjectSize());     // Catch: Throwable -> L16 Exception -> L18
        r6.toStream(r53);     // Catch: Throwable -> L16 Exception -> L18
        int r3 = 1;
        r0.bindLong(1, r6.id);     // Catch: Throwable -> L16 Exception -> L18
        r0.bindLong(2, r6.date);     // Catch: Throwable -> L16 Exception -> L18
        r0.bindByteBuffer(3, r53);     // Catch: Throwable -> L16 Exception -> L18
        if (r6.isEdit == false) goto L10;
    L13:
        r0.bindInteger(4, r3);     // Catch: Throwable -> L16 Exception -> L18
        r0.step();     // Catch: Throwable -> L16 Exception -> L18
        r53.reuse();     // Catch: Throwable -> L16 Exception -> L18
        r0.dispose();     // Catch: Throwable -> L16 Exception -> L18
        r0.dispose();
        return;
    L10:
        if (r6.isError == false) goto L12;
        r3 = 2;
        goto L13
    L12:
        r3 = 0;
        goto L13
    L29:
        return;
    L18:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L16
        if (r0 == null) goto L30;
        r0.dispose();
        return;
    L30:
        return;
    L16:
        th = move-exception;
        if (r0 == null) goto L25;
        r0.dispose();
    L25:
        throw th;
    }

    public static /* synthetic */ void $r8$lambda$7lUpiEDGEIx33GdseJFk8T5hmiA(MessagesStorage r2, ArrayList r3) {
        SQLiteDatabase r22 = r2.getDatabase();     // Catch: Exception -> L8
        if (r22 != null) goto L6;
        return;
    L6:
        r22.executeFast("DELETE FROM story_drafts WHERE id IN (" + TextUtils.join(", ", r3) + ")").stepThis().dispose();     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$RiC0JImjTCT65pJVD7o8arqUXPQ(MessagesStorage r5, StoryDraft r6) {
        SQLitePreparedStatement r0 = null;
        SQLiteDatabase r52 = r5.getDatabase();     // Catch: Throwable -> L16 Exception -> L18
        if (r52 == null) goto L29;
        r0 = r52.executeFast("REPLACE INTO story_drafts VALUES (?, ?, ?, ?)");     // Catch: Throwable -> L16 Exception -> L18
        r0.requery();     // Catch: Throwable -> L16 Exception -> L18
        NativeByteBuffer r53 = new NativeByteBuffer(r6.getObjectSize());     // Catch: Throwable -> L16 Exception -> L18
        r6.toStream(r53);     // Catch: Throwable -> L16 Exception -> L18
        int r3 = 1;
        r0.bindLong(1, r6.id);     // Catch: Throwable -> L16 Exception -> L18
        r0.bindLong(2, r6.date);     // Catch: Throwable -> L16 Exception -> L18
        r0.bindByteBuffer(3, r53);     // Catch: Throwable -> L16 Exception -> L18
        if (r6.isEdit == false) goto L10;
    L13:
        r0.bindInteger(4, r3);     // Catch: Throwable -> L16 Exception -> L18
        r0.step();     // Catch: Throwable -> L16 Exception -> L18
        r53.reuse();     // Catch: Throwable -> L16 Exception -> L18
        r0.dispose();     // Catch: Throwable -> L16 Exception -> L18
        r0.dispose();
        return;
    L10:
        if (r6.isError == false) goto L12;
        r3 = 2;
        goto L13
    L12:
        r3 = 0;
        goto L13
    L29:
        return;
    L18:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L16
        if (r0 == null) goto L30;
        r0.dispose();
        return;
    L30:
        return;
    L16:
        th = move-exception;
        if (r0 == null) goto L25;
        r0.dispose();
    L25:
        throw th;
    }

    /* renamed from: $r8$lambda$cPc-6C0DUYBr8SfzFz-cJy8hhHc, reason: not valid java name */
    public static /* synthetic */ void m19943$r8$lambda$cPc6C0DUYBr8SfzFzcJy8hhHc(Utilities.Callback r0, ArrayList r1) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$hlElkoLsV4BCJNNZiQ3HxBSs1fI(DraftsController r0, ArrayList r1) {
        r0.lambda$load$2(r1);
    }

    /* renamed from: $r8$lambda$o-tWKHnBYwDWea5I1BWhBljNOQk, reason: not valid java name */
    public static /* synthetic */ void m19944$r8$lambda$otWKHnBYwDWea5I1BWhBljNOQk(DraftsController r0, ArrayList r1) {
        r0.lambda$loadFailed$3(r1);
    }

    public DraftsController(int r2) {
        this.drafts = new ArrayList();
        this.currentAccount = r2;
        loadFailed();
    }

    private void append(StoryDraft r8) {
        MessagesStorage r0 = MessagesStorage.getInstance(this.currentAccount);
        StringBuilder r1 = new StringBuilder("StoryDraft append ");
        r1.append(r8.id);
        r1.append(" (edit=");
        r1.append(r8.isEdit);
        if (r8.isEdit == false) goto L10;
        StringBuilder r2 = new StringBuilder(", storyId=");
        r2.append(r8.editStoryId);
        r2.append(", ");
        if (r8.editDocumentId == 0) goto L8;
        StringBuilder r3 = new StringBuilder("documentId=");
        long r4 = r8.editDocumentId;
    L7:
        r3.append(r4);
        r2.append(r3.toString());
        r2.append(", expireDate=");
        r2.append(r8.editExpireDate);
        String r22 = r2.toString();
    L11:
        r1.append(r22);
        r1.append(", now=");
        r1.append(System.currentTimeMillis());
        r1.append(")");
        FileLog.d(r1.toString());
        r0.getStorageQueue().postRunnable(new DraftsController$$ExternalSyntheticLambda6(r0, r8));
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesDraftsUpdated, new Object[0]);
        return;
    L8:
        r3 = new StringBuilder("photoId=");
        r4 = r8.editPhotoId;
        goto L7
    L10:
        r22 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L11
    }

    private /* synthetic */ void lambda$load$2(ArrayList r12) {
        long r0 = System.currentTimeMillis();
        ArrayList r2 = new ArrayList();
        ArrayList<StoryEntry> r3 = new ArrayList();
        int r5 = 0;
    L4:
        if (r5 >= r12.size()) goto L24;
        StoryEntry r6 = ((StoryDraft) r12.get(r5)).toEntry();
        if (r6 == null) goto L23;
        if (r6.isCollage() == true) goto L15;
        File r7 = r6.file;
        if (r7 != null) goto L13;
    L21:
        r3.add(r6);
        goto L23
    L13:
        if (r7.exists() == false) goto L21;
    L15:
        if (r6.isEdit == false) goto L20;
        if (r0 > r6.editExpireDate) goto L21;
    L22:
        this.drafts.add(r6);
        r2.add(Long.valueOf(r6.draftId));
        goto L23
    L20:
        if ((r0 - r6.draftDate) <= 604800000) goto L22;
    L23:
        r5 = r5 + 1;
        goto L4
    L24:
        delete(r3);
        this.loading = false;
        this.loaded = true;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesDraftsUpdated, new Object[0]);
    }

    private /* synthetic */ void lambda$loadFailed$3(ArrayList r13) {
        long r0 = System.currentTimeMillis();
        ArrayList r2 = new ArrayList();
        ArrayList<StoryEntry> r3 = new ArrayList();
        ArrayList<StoryEntry> r4 = new ArrayList();
        int r6 = 0;
    L4:
        if (r6 >= r13.size()) goto L19;
        StoryEntry r7 = ((StoryDraft) r13.get(r6)).toEntry();
        if (r7 == null) goto L18;
        if (r7.isCollage() == true) goto L15;
        File r8 = r7.file;
        if (r8 != null) goto L13;
    L16:
        r3.add(r7);
        goto L18
    L13:
        if (r8.exists() == false) goto L16;
    L15:
        if ((r0 - r7.draftDate) > 604800000) goto L16;
        r4.add(r7);
        r2.add(Long.valueOf(r7.draftId));
    L18:
        r6 = r6 + 1;
        goto L4
    L19:
        delete(r3);
        this.loadingFailed = false;
        this.loadedFailed = true;
        MessagesController.getInstance(this.currentAccount).getStoriesController().putUploadingDrafts(r4);
    }

    private void loadFailed() {
        if (this.loadedFailed == false) goto L5;
        return;
    L5:
        if (this.loadingFailed == true) goto L10;
        this.loadingFailed = true;
        loadInternal(true, new DraftsController$$ExternalSyntheticLambda1(this));
        return;
    }

    private void loadInternal(boolean r3, Utilities.Callback<ArrayList<StoryDraft>> r4) {
        MessagesStorage r2 = MessagesStorage.getInstance(this.currentAccount);
        r2.getStorageQueue().postRunnable(new DraftsController$$ExternalSyntheticLambda2(r2, r3, r4));
    }

    private void prepare(StoryEntry r5) {
        if (r5 != null) goto L5;
        return;
    L5:
        if (r5.draftId != 0) goto L7;
        r5.draftId = Utilities.random.nextLong();
    L7:
        r5.draftDate = System.currentTimeMillis();
        r5.isDraft = true;
        boolean r1 = r5.fileDeletable;
        File r2 = r5.file;
        if (r1 == false) goto L10;
        r5.file = prepareFile(r2);
    L16:
        r5.filterFile = prepareFile(r5.filterFile);
        r5.paintFile = prepareFile(r5.paintFile);
        r5.draftThumbFile = prepareFile(r5.draftThumbFile);
        return;
    L10:
        if (r2 == null) goto L16;
        File r12 = StoryEntry.makeCacheFile(this.currentAccount, r5.isVideo);
        AndroidUtilities.copyFile(r5.file, r12);     // Catch: IOException -> L14
        r5.file = prepareFile(r12);     // Catch: IOException -> L14
        r5.fileDeletable = true;     // Catch: IOException -> L14
    L14:
        e = move-exception;
        FileLog.e(e);
        goto L16
    }

    private File prepareFile(File r4) {
        if (r4 != null) goto L6;
        return null;
    L6:
        if (this.draftsFolder != null) goto L11;
        File r0 = new File(FileLoader.getDirectory(4), "drafts");
        this.draftsFolder = r0;
        if (r0.exists() == true) goto L11;
        this.draftsFolder.mkdir();
    L11:
        if (r4.getAbsolutePath().startsWith(this.draftsFolder.getAbsolutePath()) == true) goto L15;
        File r02 = new File(this.draftsFolder, r4.getName());
        if (r4.renameTo(r02) == false) goto L15;
        return r02;
    L15:
        return r4;
    }

    public void append(StoryEntry r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (r4.isRepostMessage == true) goto L9;
        prepare(r4);
        r4.draftId = Utilities.random.nextLong();
        StoryDraft r0 = new StoryDraft(r4);
        this.drafts.remove(r4);
        this.drafts.add(0, r4);
        append(r0);
        return;
    }

    public void cleanup() {
        delete(this.drafts);
        this.loaded = false;
    }

    public void delete(ArrayList<StoryEntry> r11) {
        if (r11 == null) goto L28;
        ArrayList r0 = new ArrayList();
        int r2 = 0;
    L6:
        if (r2 >= r11.size()) goto L21;
        StoryEntry r3 = r11.get(r2);
        if (r3 == null) goto L19;
        StringBuilder r4 = new StringBuilder("StoryDraft delete ");
        r4.append(r3.draftId);
        r4.append(" (edit=");
        r4.append(r3.isEdit);
        if (r3.isEdit == false) goto L17;
        StringBuilder r5 = new StringBuilder(", storyId=");
        r5.append(r3.editStoryId);
        r5.append(", ");
        if (r3.editDocumentId == 0) goto L15;
        StringBuilder r6 = new StringBuilder("documentId=");
        long r7 = r3.editDocumentId;
    L14:
        r6.append(r7);
        r5.append(r6.toString());
        r5.append(", expireDate=");
        r5.append(r3.editExpireDate);
        String r52 = r5.toString();
    L18:
        r4.append(r52);
        r4.append(", now=");
        r4.append(System.currentTimeMillis());
        r4.append(")");
        FileLog.d(r4.toString());
        r0.add(Long.valueOf(r3.draftId));
        r3.destroy(true);
        goto L19
    L15:
        r6 = new StringBuilder("photoId=");
        r7 = r3.editPhotoId;
        goto L14
    L17:
        r52 = _UrlKt.FRAGMENT_ENCODE_SET;
    L19:
        r2 = r2 + 1;
        goto L6
    L21:
        if (r0.isEmpty() == false) goto L23;
        return;
    L23:
        this.drafts.removeAll(r11);
        MessagesStorage r112 = MessagesStorage.getInstance(this.currentAccount);
        r112.getStorageQueue().postRunnable(new DraftsController$$ExternalSyntheticLambda0(r112, r0));
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesDraftsUpdated, new Object[0]);
        return;
    }

    public void delete(StoryEntry r3) {
        ArrayList<StoryEntry> r0 = new ArrayList(1);
        r0.add(r3);
        delete(r0);
    }

    public void edit(StoryEntry r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        prepare(r5);
        this.drafts.remove(r5);
        if (r5.isError == true) goto L7;
        this.drafts.add(0, r5);
    L7:
        StoryDraft r0 = new StoryDraft(r5);
        MessagesStorage r52 = MessagesStorage.getInstance(this.currentAccount);
        r52.getStorageQueue().postRunnable(new DraftsController$$ExternalSyntheticLambda5(r52, r0));
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesDraftsUpdated, new Object[0]);
    }

    public StoryEntry getForEdit(long r10, TL_stories.StoryItem r12) {
        if (r12 != null) goto L5;
        return null;
    L5:
        ArrayList<StoryEntry> r9 = this.drafts;
        int r1 = r9.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L25;
        StoryEntry r3 = r9.get(r2);
        r2 = r2 + 1;
        StoryEntry r32 = r3;
        if (r32.isEdit == false) goto L6;
        if (r12.id != r32.editStoryId) goto L6;
        if (r10 != r32.editStoryPeerId) goto L6;
        TLRPC.MessageMedia r4 = r12.media;
        TLRPC.Document r5 = r4.document;
        if (r5 == null) goto L18;
        if (r5.id != r32.editDocumentId) goto L6;
    L18:
        TLRPC.Photo r42 = r4.photo;
        if (r42 == null) goto L23;
        if (r42.id != r32.editPhotoId) goto L6;
    L23:
        r32.isEditSaved = true;
        return r32;
    L25:
        return null;
    }

    public void load() {
        if (this.loaded == false) goto L5;
        return;
    L5:
        if (this.loading == true) goto L10;
        this.loading = true;
        loadInternal(false, new DraftsController$$ExternalSyntheticLambda3(this));
        return;
    }

    public void saveForEdit(StoryEntry r9, long r10, TL_stories.StoryItem r12) {
        if (r9 != null) goto L4;
        return;
    L4:
        if (r9.isRepostMessage == true) goto L32;
        if (r12 != null) goto L7;
        return;
    L7:
        if (r12.media == null) goto L34;
        ArrayList<StoryEntry> r0 = new ArrayList();
        ArrayList<StoryEntry> r1 = this.drafts;
        int r2 = r1.size();
        int r4 = 0;
    L10:
        if (r4 >= r2) goto L16;
        StoryEntry r5 = r1.get(r4);
        r4 = r4 + 1;
        StoryEntry r52 = r5;
        if (r52.isEdit == false) goto L10;
        if (r52.editStoryId != r12.id) goto L10;
        r0.add(r52);
        goto L10
    L16:
        delete(r0);
        prepare(r9);
        r9.draftId = Utilities.random.nextLong();
        StoryDraft r02 = new StoryDraft(r9);
        r9.isEdit = true;
        r02.isEdit = true;
        r9.editStoryPeerId = r10;
        r02.editStoryPeerId = r10;
        int r102 = r12.id;
        r9.editStoryId = r102;
        r02.editStoryId = r102;
        long r103 = r12.expire_date * 1000;
        r9.editExpireDate = r103;
        r02.editExpireDate = r103;
        TLRPC.MessageMedia r104 = r12.media;
        TLRPC.Document r11 = r104.document;
        if (r11 == null) goto L19;
        long r105 = r11.id;
        r9.editDocumentId = r105;
        r02.editDocumentId = r105;
    L22:
        StoryEntry r106 = r02.toEntry();
        this.drafts.remove(r9);
        this.drafts.add(0, r106);
        append(r02);
        return;
    L19:
        TLRPC.Photo r107 = r104.photo;
        if (r107 == null) goto L22;
        long r108 = r107.id;
        r9.editPhotoId = r108;
        r02.editPhotoId = r108;
        goto L22
    L34:
        return;
    }
}
