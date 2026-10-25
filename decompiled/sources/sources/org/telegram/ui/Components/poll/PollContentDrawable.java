package org.telegram.ui.Components.poll;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.ViewGroup;
import java.util.Locale;
import me.vkryl.android.AnimatorUtils;
import me.vkryl.android.animator.BoolAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.WebFile;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ClipRoundedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.RadialProgress2;
import org.telegram.ui.Components.SeekBar;
import org.telegram.ui.Components.Text;

/* loaded from: classes7.dex */
public class PollContentDrawable extends Drawable implements DownloadController.FileDownloadProgressListener, SeekBar.SeekBarDelegate {
    private final int TAG;
    private int alpha;
    private final BoolAnimator animatorIsPlaying;
    private String attachFileName;
    private String attachPath;
    private CharSequence authorInfo;
    private Text authorInfoText;
    private final int currentAccount;
    private final Paint durationBackgroundPaint;
    private int fileButtonX;
    private int fileButtonY;
    private CharSequence fileInfo;
    private Text fileInfoText;
    private CharSequence fileName;
    private Text fileNameText;
    private FileState fileState;
    private boolean hasMedia;
    public final ImageReceiver imageReceiver;
    private final boolean isExplanation;
    private boolean isFile;
    private boolean isLocation;
    private boolean isMusic;
    private boolean isVideo;
    private int lastFileNameWidth;
    private int lastIcon;
    private int lastIconMini;
    int lastTime;
    private ClipRoundedDrawable locationLoadingThumb;
    private SvgHelper.SvgDrawable locationSvgThumb;
    private TLRPC.MessageMedia media;
    private int mediaHeight;
    private int mediaWidth;
    private MessageObject messageObject;
    private boolean miniButtonPressed;
    private double musicDuration;
    private final ViewGroup parent;
    private final RadialProgress2 radialProgress;
    private Drawable redLocationIcon;
    private final SeekBar seekBar;
    private float seekBarX;
    private float seekBarY;
    private int videoDuration;
    private Text videoDurationText;

    public PollContentDrawable(int r3, ViewGroup r4, Theme.ResourcesProvider r5, boolean r6) {
        this.durationBackgroundPaint = new Paint(1);
        this.lastFileNameWidth = 0;
        this.alpha = 255;
        ImageReceiver r0 = new ImageReceiver(r4);
        this.imageReceiver = r0;
        r0.setRoundRadius(AndroidUtilities.dp(6.0f));
        this.currentAccount = r3;
        this.isExplanation = r6;
        this.radialProgress = new RadialProgress2(r4, r5);
        this.parent = r4;
        SeekBar r52 = new SeekBar(r4);
        this.seekBar = r52;
        r52.setDelegate(this);
        this.animatorIsPlaying = new BoolAnimator(r4, AnimatorUtils.DECELERATE_INTERPOLATOR, 180);
        this.TAG = DownloadController.getInstance(r3).generateObserverTag();
    }

    private void checkFileTexts(boolean r6) {
        int r0 = getBounds().width();
        if (this.isExplanation == false) goto L5;
        float r1 = 64.0f;
    L6:
        int r02 = r0 - AndroidUtilities.dp(r1);
        if (this.lastFileNameWidth != r02) goto L9;
        if (r6 == true) goto L9;
        return;
    L9:
        this.lastFileNameWidth = r02;
        CharSequence r62 = this.fileName;
        if (r62 != null) goto L12;
    L15:
        CharSequence r63 = this.authorInfo;
        if (r63 != null) goto L18;
    L21:
        CharSequence r64 = this.fileInfo;
        if (r64 == null) goto L28;
        if (this.fileInfoText != null) goto L26;
        this.fileInfoText = new Text(r64, 12.0f);
    L26:
        Text r65 = this.fileInfoText;
        r65.setText(TextUtils.ellipsize(this.fileInfo, r65.paint, r02, TextUtils.TruncateAt.END));
    L28:
        if (this.isVideo == true) goto L30;
        return;
    L30:
        if (this.videoDurationText != null) goto L35;
        this.videoDurationText = new Text(AndroidUtilities.formatLongDuration(this.videoDuration), 12.0f);
        return;
    L35:
        return;
    L18:
        if (this.authorInfoText != null) goto L20;
        this.authorInfoText = new Text(r63, 14.0f);
    L20:
        Text r66 = this.authorInfoText;
        r66.setText(TextUtils.ellipsize(this.authorInfo, r66.paint, r02, TextUtils.TruncateAt.END));
        goto L21
    L12:
        if (this.fileNameText != null) goto L14;
        this.fileNameText = new Text(r62, 15.0f, AndroidUtilities.bold());
    L14:
        Text r67 = this.fileNameText;
        r67.setText(TextUtils.ellipsize(this.fileName, r67.paint, r02, TextUtils.TruncateAt.MIDDLE));
        goto L15
    L5:
        r1 = 72.0f;
        goto L6
    }

    private int getCurrentPlayingProgress() {
        if (isCurrentPlayingMessageMusic() == true) goto L5;
        return 0;
    L5:
        return MediaController.getInstance().getPlayingMessageObject().audioProgressSec;
    }

    private int getDefaultIcon() {
        if (this.isMusic == false) goto L9;
        if (this.animatorIsPlaying.getValue() == false) goto L9;
        return !MediaController.getInstance().isMessagePaused() ? 1 : 0;
    L9:
        if (this.isVideo == false) goto L11;
        return 0;
    L11:
        if (this.isMusic == false) goto L14;
        return 0;
    L14:
        if (this.isFile == false) goto L23;
        FileState r1 = this.fileState;
        if (r1 != null) goto L18;
        return 2;
    L18:
        if (r1.isExists() == false) goto L27;
        return 5;
    L27:
        return 2;
    L23:
        return 4;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1);
    }

    private boolean isCurrentPlayingMessageMusic() {
        if (this.isMusic == true) goto L6;
        return false;
    L6:
        if (MediaController.getInstance().isPlayingMessage(this.messageObject) == true) goto L8;
    L13:
        return false;
    L8:
        if (MediaController.getInstance().getPlayingMessageObject() == null) goto L13;
        if (MediaController.getInstance().getPlayingMessageObject().isPlayingExplanationObject != this.isExplanation) goto L13;
        return true;
    }

    private void setIcon(int r2, boolean r3) {
        if (this.lastIcon == r2) goto L6;
        this.lastIcon = r2;
        this.radialProgress.setIcon(r2, true, r3);
        return;
    }

    private void setIconMini(int r2, boolean r3) {
        if (this.lastIconMini == r2) goto L6;
        this.lastIconMini = r2;
        this.radialProgress.setMiniIcon(r2, true, r3);
        return;
    }

    private boolean setMediaImpl(TLRPC.MessageMedia r34, Object r35, int r36, String r37) {
        if (r34 != null) goto L5;
    L80:
        return false;
    L5:
        if ((r34 instanceof TLRPC.TL_messageMediaEmpty) == true) goto L80;
        if ((r34 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L19;
        TLRPC.Photo r2 = ((TLRPC.TL_messageMediaPhoto) r34).photo;
        TLRPC.PhotoSize r4 = FileLoader.getClosestPhotoSizeWithSize(r2.sizes, 40);
        TLRPC.PhotoSize r6 = FileLoader.getClosestPhotoSizeWithSize(r2.sizes, AndroidUtilities.getPhotoSize(), true, r4, true);
        if (r6 != null) goto L12;
        return false;
    L12:
        int r3 = r6.w;
        this.mediaWidth = r3;
        int r7 = r6.h;
        this.mediaHeight = r7;
        String r11 = String.format(Locale.US, "%d_%d", new Object[]{Integer.valueOf((int) (r3 / AndroidUtilities.density)), Integer.valueOf((int) (r7 / AndroidUtilities.density))});
        String r13 = r11.concat("_b");
        if (TextUtils.isEmpty(r37) == true) goto L15;
        String r1 = r37;
    L16:
        this.attachFileName = r1;
        this.imageReceiver.setImage(ImageLocation.getForObject(r6, r2), r11, ImageLocation.getForObject(r4, r2), r13, null, r6.size, null, r35, 1);
        return true;
    L15:
        r1 = MessageObject.getFileName(r34);
        goto L16
    L19:
        if ((r34 instanceof TLRPC.TL_messageMediaGeo) == true) goto L67;
        if ((r34 instanceof TLRPC.TL_messageMediaVenue) == true) goto L67;
        if ((r34 instanceof TLRPC.TL_messageMediaDocument) == false) goto L80;
        TLRPC.Document r42 = ((TLRPC.TL_messageMediaDocument) r34).document;
        if (r42 != null) goto L28;
        return false;
    L28:
        this.fileState = new FileState(this.currentAccount, this.messageObject, r42, r37);
        if (TextUtils.isEmpty(r37) == true) goto L31;
        String r12 = r37;
    L32:
        this.attachFileName = r12;
        if (MessageObject.isMusicDocument(r42) == false) goto L51;
        this.isMusic = true;
        this.fileName = MessageObject.getMusicTitle(r42, true);
        this.authorInfo = MessageObject.getMusicAuthor(r42, true);
        int r14 = 0;
    L36:
        if (r14 >= r42.attributes.size()) goto L41;
        TLRPC.DocumentAttribute r22 = r42.attributes.get(r14);
        if ((r22 instanceof TLRPC.TL_documentAttributeAudio) == true) goto L39;
        r14 = r14 + 1;
        goto L36
    L39:
        double r15 = r22.duration;
    L43:
        if (MessageObject.isDocumentHasThumb(r42) == false) goto L45;
        TLRPC.PhotoSize r32 = FileLoader.getClosestPhotoSizeWithSize(r42.thumbs, AndroidUtilities.dp(22.0f), true, null, false);
        this.radialProgress.setImageOverlay(FileLoader.getClosestPhotoSizeWithSize(r42.thumbs, AndroidUtilities.dp(44.0f), true, r32, true), r32, r42, this.messageObject);
    L49:
        this.musicDuration = r15;
        this.fileInfo = AndroidUtilities.formatShortDuration(getCurrentPlayingProgress(), (int) this.musicDuration);
    L64:
        checkFileTexts(true);
        return true;
    L45:
        String r33 = MessageObject.getArtworkUrl(r42, true);
        boolean r43 = TextUtils.isEmpty(r33);
        RadialProgress2 r72 = this.radialProgress;
        if (r43 == true) goto L48;
        r72.setImageOverlay(r33);
        goto L49
    L48:
        r72.setImageOverlay(null, null, null);
        goto L49
    L41:
        r15 = 0.0d;
        goto L43
    L51:
        if (MessageObject.isVideoDocument(r42) == false) goto L63;
        this.videoDuration = (int) Math.max(1, Math.round(MessageObject.getDocumentDuration(r42)));
        this.isVideo = true;
        TLRPC.PhotoSize r16 = FileLoader.getClosestPhotoSizeWithSize(r42.thumbs, AndroidUtilities.getPhotoSize(), true, null, true);
        float r23 = r36;
        TLRPC.PhotoSize r38 = FileLoader.getClosestPhotoSizeWithSize(r42.thumbs, (int) (r23 / AndroidUtilities.density), false, r16, false);
        ImageLocation r232 = ImageLocation.getForDocument(r16, r42);
        ImageLocation r25 = ImageLocation.getForDocument(r38, r42);
        int r24 = (int) (r23 / AndroidUtilities.density);
        if (r16 == null) goto L57;
        int r39 = r16.w;
        this.mediaWidth = r39;
        int r17 = r16.h;
        this.mediaHeight = r17;
        if (r39 == 0) goto L61;
        int r18 = (r17 * r24) / r39;
    L62:
        String r222 = r24 + "_" + r18;
        this.imageReceiver.setImage(null, r222, r232, r222, r25, r222, null, 0, null, r35, 0);
    L61:
        r18 = r24;
        goto L62
    L57:
        if (r38 == null) goto L61;
        int r19 = r38.w;
        this.mediaWidth = r19;
        int r310 = r38.h;
        this.mediaHeight = r310;
        if (r19 == 0) goto L61;
        r18 = (r310 * r24) / r19;
        goto L62
    L63:
        this.isFile = true;
        this.fileName = FileLoader.getDocumentFileName(r42);
        String r110 = AndroidUtilities.formatFileSize(r42.size) + " " + FileLoader.getDocumentExtension(r42);
        this.fileInfo = r110;
        this.authorInfo = r110;
        goto L64
    L31:
        r12 = MessageObject.getFileName(r34);
    L67:
        if (r34.geo == null) goto L80;
        if (this.locationSvgThumb != null) goto L76;
        int r311 = R.raw.map_placeholder;
        int r44 = Theme.key_chat_outLocationIcon;
        if (Theme.isCurrentThemeDark() == false) goto L73;
        int r62 = 3;
    L74:
        SvgHelper.SvgDrawable r312 = DocumentObject.getSvgThumb(r311, r44, r62 * 0.12f);
        this.locationSvgThumb = r312;
        r312.setAspectCenter(true);
        this.locationLoadingThumb = new ClipRoundedDrawable(this.locationSvgThumb);
        goto L76
    L73:
        r62 = 6;
    L76:
        if (this.redLocationIcon != null) goto L78;
        this.redLocationIcon = this.parent.getContext().getResources().getDrawable(R.drawable.map_pin).mutate();
    L78:
        this.isLocation = true;
        this.mediaWidth = r36;
        int r313 = (r36 * 9) / 16;
        this.mediaHeight = r313;
        TLRPC.GeoPoint r111 = r34.geo;
        float r45 = AndroidUtilities.density;
        this.imageReceiver.setImage(ImageLocation.getForWebFile(WebFile.createWithGeoPoint(r111, (int) (r36 / r45), (int) (r313 / r45), 15, Math.min(2, (int) Math.ceil(r45)))), null, null, null, this.locationLoadingThumb, r35, 0);
        return true;
    }

    private void updatePlayingMessageProgress(boolean r4) {
        if (this.isMusic == true) goto L5;
        return;
    L5:
        if (this.messageObject == null) goto L21;
        MessageObject r0 = MediaController.getInstance().getPlayingMessageObject();
        boolean r1 = isCurrentPlayingMessageMusic();
        this.animatorIsPlaying.setValue(r1, r4);
        if (r0 == null) goto L14;
        if (r1 == false) goto L14;
        int r42 = r0.audioProgressSec;
        if (this.seekBar.isDragging() == true) goto L13;
        this.seekBar.setProgress(r0.audioProgress);
        this.seekBar.setBufferedProgress(r0.bufferedProgress);
    L13:
        this.seekBar.updateTimestamps(r0, null);
    L16:
        if (this.lastTime == r42) goto L20;
        this.lastTime = r42;
        this.fileInfo = AndroidUtilities.formatShortDuration(r42, (int) this.musicDuration);
        checkFileTexts(true);
        this.parent.invalidate();
        return;
    L20:
        return;
    L14:
        r42 = 0;
        goto L16
    }

    public void attach() {
        this.imageReceiver.onAttachedToWindow();
        this.radialProgress.onAttachedToWindow();
    }

    public void checkColors(boolean r11) {
        Text r0 = this.fileNameText;
        if (r0 == null) goto L8;
        if (r11 == false) goto L6;
        int r1 = Theme.key_chat_outFileNameText;
    L7:
        r0.setColor(Theme.getColor(r1));
        goto L8
    L6:
        r1 = Theme.key_chat_inFileNameText;
    L8:
        this.durationBackgroundPaint.setColor(1711276032);
        Text r02 = this.videoDurationText;
        if (r02 == null) goto L11;
        r02.setColor(-1);
    L11:
        SvgHelper.SvgDrawable r03 = this.locationSvgThumb;
        if (r03 == null) goto L18;
        if (r11 == false) goto L15;
        int r12 = Theme.key_chat_outLocationIcon;
    L16:
        r03.setColorKey(r12);
        goto L18
    L15:
        r12 = Theme.key_chat_inLocationIcon;
    L18:
        if (this.isMusic == false) goto L20;
    L24:
        if (r11 == false) goto L27;
        SeekBar r3 = this.seekBar;
        int r4 = getThemedColor(Theme.key_chat_outAudioSeekbar);
        int r5 = getThemedColor(Theme.key_chat_outAudioCacheSeekbar);
        int r112 = Theme.key_chat_outAudioSeekbarFill;
        r3.setColors(r4, r5, getThemedColor(r112), getThemedColor(r112), getThemedColor(Theme.key_chat_outAudioSeekbarSelected));
        this.radialProgress.setColorKeys(Theme.key_chat_outLoader, Theme.key_chat_outLoaderSelected, Theme.key_chat_outMediaIcon, Theme.key_chat_outMediaIconSelected);
        return;
    L27:
        this.radialProgress.setColorKeys(Theme.key_chat_inLoader, Theme.key_chat_inLoaderSelected, Theme.key_chat_inMediaIcon, Theme.key_chat_inMediaIconSelected);
        SeekBar r42 = this.seekBar;
        int r52 = getThemedColor(Theme.key_chat_inAudioSeekbar);
        int r6 = getThemedColor(Theme.key_chat_inAudioCacheSeekbar);
        int r113 = Theme.key_chat_inAudioSeekbarFill;
        r42.setColors(r52, r6, getThemedColor(r113), getThemedColor(r113), getThemedColor(Theme.key_chat_inAudioSeekbarSelected));
        return;
    L20:
        if (this.isFile == true) goto L24;
        this.radialProgress.setColorKeys(Theme.key_chat_mediaLoaderPhoto, Theme.key_chat_mediaLoaderPhotoSelected, Theme.key_chat_mediaLoaderPhotoIcon, Theme.key_chat_mediaLoaderPhotoIconSelected);
    }

    public void checkFileState() {
        FileState r0 = this.fileState;
        if (r0 == null) goto L5;
        r0.checkState();
    L5:
        this.parent.invalidate();
    }

    public void detach() {
        this.imageReceiver.onDetachedFromWindow();
        this.radialProgress.onDetachedFromWindow();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r21) {
        Rect r9 = getBounds();
        if (this.alpha != 0) goto L5;
        return;
    L5:
        if (r9.isEmpty() == true) goto L92;
        int r2 = 0;
        checkFileTexts(false);
        int r10 = 2;
        if (this.isFile == false) goto L10;
    L22:
        int r3 = r9.left;
        if (this.isExplanation == false) goto L25;
        int r7 = 0;
    L26:
        int r32 = r3 + r7;
        int r72 = r9.top;
        if (this.isExplanation == false) goto L29;
        int r8 = 0;
    L30:
        int r73 = r72 + r8;
        if (this.isMusic == true) goto L33;
        r2 = AndroidUtilities.dp(3.0f);
    L33:
        Text r82 = this.fileNameText;
        if (r82 == null) goto L37;
        r82.draw(r21, AndroidUtilities.dp(56.0f) + r32, (r73 + r2) + AndroidUtilities.dp(15.0f));
    L37:
        if (this.isMusic == false) goto L47;
        float r83 = this.animatorIsPlaying.getFloatValue();
        if (this.authorInfoText != null) goto L41;
    L43:
        float r18 = 5.0f;
    L45:
        if (r83 <= 0.0f) goto L48;
        this.seekBar.setAlpha(r83);
        this.seekBar.setSize(r9.right - (AndroidUtilities.dp(56.0f) + r32), AndroidUtilities.dp(30.0f));
        r21.save();
        float r4 = AndroidUtilities.dp(45.0f) + r32;
        this.seekBarX = r4;
        float r5 = (r73 + r2) + AndroidUtilities.dp(21.0f);
        this.seekBarY = r5;
        r21.translate(r4, r5);
        this.seekBar.draw(r21);
        r21.restore();
    L48:
        Text r42 = this.fileInfoText;
        if (r42 == null) goto L55;
        float r52 = AndroidUtilities.dp(56.0f) + r32;
        int r22 = r2 + r73;
        if (this.isMusic == false) goto L53;
        int r84 = 20;
    L54:
        r42.draw(r21, r52, r22 + AndroidUtilities.dp(r84 + 34));
        goto L55
    L53:
        r84 = 2;
    L55:
        RadialProgress2 r23 = this.radialProgress;
        int r43 = AndroidUtilities.dp(2.0f) + r32;
        this.fileButtonX = r43;
        int r53 = AndroidUtilities.dp(r18) + r73;
        this.fileButtonY = r53;
        r23.setProgressRect(r43, r53, (r32 + AndroidUtilities.dp(2.0f)) + AndroidUtilities.dp(44.0f), (r73 + AndroidUtilities.dp(r18)) + AndroidUtilities.dp(44.0f));
    L57:
        if (this.isLocation == true) goto L91;
        MessageObject r24 = this.messageObject;
        if (r24 != null) goto L61;
    L68:
        FileState r25 = this.fileState;
        if (r25 == null) goto L77;
        if (r25.isLoading() == false) goto L77;
        if (this.isMusic == false) goto L75;
        setIconMini(3, true);
    L86:
        if (this.isMusic == false) goto L88;
        setIcon(getDefaultIcon(), true);
    L88:
        this.radialProgress.draw(r21);
        return;
    L75:
        setIcon(3, true);
    L77:
        if (this.isMusic == false) goto L84;
        FileState r26 = this.fileState;
        if (r26 != null) goto L81;
    L83:
        setIconMini(r10, true);
        goto L86
    L81:
        if (r26.isExists() == false) goto L83;
        r10 = 4;
        goto L83
    L84:
        setIcon(getDefaultIcon(), true);
        goto L86
    L61:
        if (r24.isSending() == false) goto L68;
        if (ImageLoader.getInstance().getFileProgressSizes(this.attachPath) != null) goto L86;
        this.radialProgress.setProgress(1.0f, true);
        if (this.isMusic == false) goto L67;
        setIconMini(6, true);
        goto L86
    L67:
        setIcon(6, true);
        goto L86
    L91:
        return;
    L41:
        if (r83 >= 1.0f) goto L43;
        r21.save();
        float r11 = 1.0f - r83;
        int r15 = r73 + r2;
        r18 = 5.0f;
        r21.scale(r11, r11, AndroidUtilities.dp(56.0f) + r32, r15 + AndroidUtilities.dp(35.0f));
        this.authorInfoText.setAlpha((int) (r11 * 255.0f));
        this.authorInfoText.draw(r21, AndroidUtilities.dp(56.0f) + r32, r15 + AndroidUtilities.dp(35.0f));
        r21.restore();
        goto L45
    L47:
        r18 = 5.0f;
        goto L48
    L29:
        r8 = AndroidUtilities.dp(3.0f);
        goto L30
    L25:
        r7 = AndroidUtilities.dp(8.0f);
        goto L26
    L10:
        if (this.isMusic == true) goto L22;
        this.imageReceiver.setAlpha(this.alpha / 255.0f);
        this.imageReceiver.setImageCoords(r9);
        this.imageReceiver.draw(r21);
        if (this.isLocation == true) goto L15;
    L17:
        this.radialProgress.setProgressRect(r9.centerX() - AndroidUtilities.dp(22.0f), r9.centerY() - AndroidUtilities.dp(22.0f), r9.centerX() + AndroidUtilities.dp(22.0f), r9.centerY() + AndroidUtilities.dp(22.0f));
        if (this.isVideo == false) goto L57;
        if (this.videoDurationText == null) goto L57;
        r21.drawRoundRect(r9.left + AndroidUtilities.dp(6.0f), r9.top + AndroidUtilities.dp(6.0f), (r9.left + this.videoDurationText.getCurrentWidth()) + AndroidUtilities.dp(18.0f), r9.top + AndroidUtilities.dp(23.0f), AndroidUtilities.dp(8.5f), AndroidUtilities.dp(8.5f), this.durationBackgroundPaint);
        this.videoDurationText.draw(r21, r9.left + AndroidUtilities.dp(12.0f), r9.top + AndroidUtilities.dp(15.0f));
        goto L57
    L15:
        if (this.redLocationIcon == null) goto L17;
        int r27 = (int) (r2.getIntrinsicWidth() * 0.8f);
        int r33 = (int) (this.redLocationIcon.getIntrinsicHeight() * 0.8f);
        int r74 = (int) (this.imageReceiver.getImageX() + ((this.imageReceiver.getImageWidth() - r27) / 2.0f));
        int r6 = (int) ((this.imageReceiver.getImageY() + ((this.imageReceiver.getImageHeight() / 2.0f) - r33)) - (AndroidUtilities.dp(16.0f) * (1.0f - CubicBezierInterpolator.EASE_OUT_BACK.getInterpolation(this.imageReceiver.getCurrentAlpha()))));
        this.redLocationIcon.setAlpha((int) ((Math.min(1.0f, this.imageReceiver.getCurrentAlpha() * 5.0f) * 255.0f) * this.imageReceiver.getAlpha()));
        this.redLocationIcon.setBounds(r74, r6, r27 + r74, r33 + r6);
        this.redLocationIcon.draw(r21);
        goto L17
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.alpha;
    }

    public int getHeightForWidth(int r4) {
        if (this.isMusic == false) goto L7;
        return AndroidUtilities.dp(63.0f);
    L7:
        if (this.isFile == true) goto L9;
        int r0 = this.mediaWidth;
        if (r0 == 0) goto L13;
        int r02 = Math.round(this.mediaHeight * (r4 / r0));
        if (this.isExplanation == false) goto L17;
        int r42 = (r4 * 4) / 5;
    L19:
        return Math.min(r02, r42);
    L17:
        r42 = (r4 * 5) / 4;
        goto L19
    L13:
        return AndroidUtilities.dp(100.0f);
    L9:
        return AndroidUtilities.dp(56.0f);
    }

    public ImageReceiver getImageReceiver() {
        return this.imageReceiver;
    }

    public TLRPC.MessageMedia getMedia() {
        return this.media;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public int getObserverTag() {
        return this.TAG;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public boolean isDraggingSeekBar() {
        return this.seekBar.isDragging();
    }

    public boolean isFile() {
        return this.isFile;
    }

    public boolean isHasMedia() {
        return this.hasMedia;
    }

    public boolean isMusic() {
        return this.isMusic;
    }

    public boolean isPlaying() {
        return this.animatorIsPlaying.getValue();
    }

    public boolean miniButtonOnTouch(int r7, float r8, float r9) {
        if (this.isMusic == true) goto L5;
    L38:
        return false;
    L5:
        if (this.lastIconMini == 4) goto L38;
        if (r7 != 0) goto L19;
        int r2 = AndroidUtilities.dp(36.0f);
        int r3 = AndroidUtilities.dp(27.0f);
        int r4 = this.fileButtonX;
        if (r8 < (r4 + r3)) goto L19;
        if (r8 > ((r4 + r3) + r2)) goto L19;
        int r82 = this.fileButtonY;
        if (r9 < (r82 + r3)) goto L19;
        if (r9 > ((r82 + r3) + r2)) goto L19;
        this.miniButtonPressed = true;
        return true;
    L19:
        boolean r83 = this.miniButtonPressed;
        if (r83 == false) goto L37;
        if (r7 != 1) goto L34;
        FileState r72 = this.fileState;
        if (r72 == null) goto L31;
        boolean r73 = r72.isLoading();
        FileState r84 = this.fileState;
        if (r73 == false) goto L28;
        r84.downloadCancel();
    L30:
        checkFileState();
        goto L31
    L28:
        if (r84.isExists() == true) goto L30;
        this.fileState.downloadStart();
    L31:
        this.miniButtonPressed = false;
        return true;
    L34:
        if (r7 != 3) goto L37;
        this.miniButtonPressed = false;
        return true;
    L37:
        return r83;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onFailedDownload(String r1, boolean r2) {
        checkFileState();
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressDownload(String r3, long r4, long r6) {
        if (r6 != 0) goto L5;
        float r32 = 0.0f;
    L6:
        this.radialProgress.setProgress(r32, true);
        FileState r42 = this.fileState;
        if (r42 == null) goto L9;
        r42.checkState();
    L9:
        int r62 = 3;
        if (this.isMusic == false) goto L17;
        if (r32 < 1.0f) goto L15;
        r62 = 4;
    L15:
        setIconMini(r62, true);
    L21:
        this.parent.invalidate();
        return;
    L17:
        if (r32 < 1.0f) goto L20;
        r62 = getDefaultIcon();
    L20:
        setIcon(r62, true);
        goto L21
    L5:
        r32 = Math.min(1.0f, r4 / r6);
        goto L6
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressUpload(String r3, long r4, long r6, boolean r8) {
        if (r6 != 0) goto L5;
        float r32 = 0.0f;
    L6:
        this.radialProgress.setProgress(r32, true);
        FileState r42 = this.fileState;
        if (r42 == null) goto L9;
        r42.checkState();
    L9:
        int r62 = 3;
        if (this.isMusic == false) goto L17;
        if (r32 < 1.0f) goto L15;
        r62 = 4;
    L15:
        setIconMini(r62, true);
    L21:
        this.parent.invalidate();
        return;
    L17:
        if (r32 < 1.0f) goto L20;
        r62 = getDefaultIcon();
    L20:
        setIcon(r62, true);
        goto L21
    L5:
        r32 = Math.min(1.0f, r4 / r6);
        goto L6
    }

    @Override // org.telegram.ui.Components.SeekBar.SeekBarDelegate
    public void onSeekBarContinuousDrag(float r6) {
        if (isCurrentPlayingMessageMusic() == false) goto L6;
        MessageObject r0 = MediaController.getInstance().getPlayingMessageObject();
        r0.audioProgress = r6;
        r0.audioProgressSec = (int) (r0.getDuration() * r6);
        updatePlayingMessageProgress();
        return;
    }

    @Override // org.telegram.ui.Components.SeekBar.SeekBarDelegate
    public void onSeekBarDrag(float r3) {
        if (isCurrentPlayingMessageMusic() == false) goto L6;
        MessageObject r0 = MediaController.getInstance().getPlayingMessageObject();
        MediaController.getInstance().seekToProgress(r0, r3);
        updatePlayingMessageProgress();
        return;
    }

    @Override // org.telegram.ui.Components.SeekBar.SeekBarDelegate
    public void onSeekBarPressed() {
        this.parent.requestDisallowInterceptTouchEvent(true);
    }

    @Override // org.telegram.ui.Components.SeekBar.SeekBarDelegate
    public void onSeekBarReleased() {
        this.parent.requestDisallowInterceptTouchEvent(false);
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onSuccessDownload(String r1) {
        checkFileState();
    }

    public boolean seekBarOnTouch(int r3, float r4, float r5) {
        return this.seekBar.onTouch(r3, r4 - this.seekBarX, r5 - this.seekBarY);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.alpha = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setColors(int r1, int r2, int r3, int r4) {
        Text r12 = this.authorInfoText;
        if (r12 == null) goto L5;
        r12.setColor(r2);
    L5:
        Text r0 = this.fileInfoText;
        if (r0 == null) goto L9;
        r0.setColor(r3);
        return;
    }

    public void setMedia(MessageObject r5, TLRPC.MessageMedia r6, Object r7, int r8, String r9, boolean r10) {
        String r0 = this.attachFileName;
        this.mediaWidth = 0;
        this.mediaHeight = 0;
        this.messageObject = r5;
        this.media = r6;
        this.isFile = false;
        this.isMusic = false;
        this.isVideo = false;
        this.isLocation = false;
        this.musicDuration = 0.0d;
        this.videoDuration = 0;
        this.attachPath = r9;
        this.attachFileName = null;
        this.fileState = null;
        boolean r62 = setMediaImpl(r6, r7, r8, r9);
        this.hasMedia = r62;
        if (r62 == true) goto L6;
        this.imageReceiver.clearImage();
    L6:
        if (TextUtils.equals(r0, this.attachFileName) == true) goto L14;
        if (TextUtils.isEmpty(r0) == true) goto L11;
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
    L11:
        if (TextUtils.isEmpty(this.attachFileName) == true) goto L14;
        DownloadController.getInstance(this.currentAccount).addLoadingFileObserver(this.attachFileName, this);
    L14:
        if (this.isMusic == true) goto L16;
        this.radialProgress.setImageOverlay(null, null, null);
        setIconMini(4, false);
    L16:
        updatePlayingMessageProgress(r10);
    }

    public void updatePlayingMessageProgress() {
        updatePlayingMessageProgress(true);
    }
}
