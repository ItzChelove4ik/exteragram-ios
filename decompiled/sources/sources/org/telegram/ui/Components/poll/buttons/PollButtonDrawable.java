package org.telegram.ui.Components.poll.buttons;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import java.util.List;
import me.vkryl.android.animator.BoolAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.WebFile;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AvatarsListDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.PorterDuffColorFilterState;
import org.telegram.ui.Components.RadialProgress2;

/* loaded from: classes7.dex */
public class PollButtonDrawable extends Drawable implements DownloadController.FileDownloadProgressListener {
    private final int TAG;
    private final BoolAnimator animatorShowVoters;
    private String attachFileName;
    private String attachPath;
    private final int currentAccount;
    private final Paint darkenPaint;
    private boolean hasMedia;
    private boolean hasMediaPadding;
    private final ImageReceiver imageReceiver;
    private boolean isVideo;
    private boolean isWebPage;
    private boolean isWebPageWithPreview;
    private int lastIcon;
    private final AvatarsListDrawable lastVotersDrawable;
    private MessageObject messageObject;
    private boolean needDrawProgress;
    private final View parent;
    private final RadialProgress2 radialProgress;
    private int recentVotersCount;
    private final AnimatedTextView.AnimatedTextDrawable votersCountDrawable;
    private final Paint webPageBgPaint;
    private Drawable webPageDrawable;
    private final PorterDuffColorFilterState webPageLinkColorFilter;

    public PollButtonDrawable(int r10, View r11) {
        Paint r0 = new Paint(1);
        this.darkenPaint = r0;
        this.webPageBgPaint = new Paint(1);
        this.webPageLinkColorFilter = new PorterDuffColorFilterState();
        this.currentAccount = r10;
        this.parent = r11;
        this.animatorShowVoters = new BoolAnimator(r11, CubicBezierInterpolator.EASE_OUT_QUINT, 380);
        AnimatedTextView.AnimatedTextDrawable r1 = new AnimatedTextView.AnimatedTextDrawable();
        this.votersCountDrawable = r1;
        r1.setGravity(21);
        r1.setTextSize(AndroidUtilities.dp(11.0f));
        r1.setCallback(r11);
        this.lastVotersDrawable = new AvatarsListDrawable(r10, r11, AndroidUtilities.dp(18.0f), AndroidUtilities.dp(8.33f), AndroidUtilities.dpf2(1.0f));
        ImageReceiver r102 = new ImageReceiver(r11);
        this.imageReceiver = r102;
        r102.setRoundRadius(AndroidUtilities.dp(5.0f));
        r0.setColor(TLObject.FLAG_30);
        RadialProgress2 r103 = new RadialProgress2(r11);
        this.radialProgress = r103;
        r103.setCircleRadius(AndroidUtilities.dp(18.0f));
        r103.setProgressColor(-1);
        this.TAG = DownloadController.getInstance(r10).generateObserverTag();
    }

    private boolean applyPhotoToImageReceiver(TLRPC.Photo r18, Object r19) {
        boolean r1 = false;
        if (r18 != null) goto L5;
        return false;
    L5:
        TLRPC.PhotoSize r2 = FileLoader.getClosestPhotoSizeWithSize(r18.sizes, 40);
        TLRPC.PhotoSize r3 = FileLoader.getClosestPhotoSizeWithSize(r18.sizes, AndroidUtilities.dp(36.0f), false, r2, true);
        if (r3 == null) goto L8;
        TLRPC.PhotoSize r4 = r3;
    L9:
        if (r4 != null) goto L11;
        return false;
    L11:
        if (r2 == null) goto L14;
        if (r2 == r4) goto L14;
        r1 = true;
    L14:
        ImageReceiver r6 = this.imageReceiver;
        ImageLocation r7 = ImageLocation.getForObject(r4, r18);
        String r42 = null;
        if (r1 == false) goto L17;
        ImageLocation r9 = ImageLocation.getForObject(r2, r18);
    L18:
        if (r1 == false) goto L20;
        r42 = "36_36_b";
    L20:
        String r10 = r42;
        if (r3 == null) goto L24;
        long r0 = r3.size;
    L25:
        r6.setImage(r7, "36_36", r9, r10, null, r0, null, r19, 1);
        return true;
    L24:
        r0 = 0;
        goto L25
    L17:
        r9 = null;
        goto L18
    L8:
        r4 = r2;
        goto L9
    }

    private void checkIcon(boolean r3) {
        if (this.messageObject.isSending() == false) goto L5;
        return;
    L5:
        if (this.messageObject.isEditing() == false) goto L8;
        return;
    L8:
        if (TextUtils.isEmpty(this.attachFileName) == false) goto L10;
    L13:
        setIcon(getDefaultIcon(), r3);
        return;
    L10:
        if (FileLoader.getInstance(this.currentAccount).isLoadingFile(this.attachFileName) == false) goto L13;
        setIcon(3, r3);
    }

    private int getDefaultIcon() {
        if (this.isVideo == false) goto L6;
        return 0;
    L6:
        return 4;
    }

    private void setIcon(int r2, boolean r3) {
        if (this.lastIcon == r2) goto L6;
        this.lastIcon = r2;
        this.radialProgress.setIcon(r2, true, r3);
        return;
    }

    private boolean setMediaImpl(TLRPC.MessageMedia r13, Object r14, String r15) {
        if (r13 != null) goto L5;
    L69:
        return false;
    L5:
        if ((r13 instanceof TLRPC.TL_messageMediaEmpty) == true) goto L69;
        this.isWebPageWithPreview = false;
        this.isWebPage = false;
        this.isVideo = false;
        this.attachPath = r15;
        if ((r13 instanceof TLRPC.TL_messageMediaWebPage) == false) goto L17;
        TLRPC.WebPage r1 = ((TLRPC.TL_messageMediaWebPage) r13).webpage;
        this.isWebPage = true;
        TLRPC.Photo r12 = r1.photo;
        if (r12 != null) goto L12;
    L14:
        this.imageReceiver.clearImage();
    L15:
        return true;
    L12:
        if (applyPhotoToImageReceiver(r12, r14) == false) goto L14;
        this.isWebPageWithPreview = true;
        goto L15
    L17:
        if ((r13 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L28;
        if (applyPhotoToImageReceiver(((TLRPC.TL_messageMediaPhoto) r13).photo, r14) == true) goto L21;
        return false;
    L21:
        this.needDrawProgress = true;
        if (TextUtils.isEmpty(r15) == true) goto L24;
        String r16 = r15;
    L25:
        this.attachFileName = r16;
        return true;
    L24:
        r16 = MessageObject.getFileName(r13);
        goto L25
    L28:
        if ((r13 instanceof TLRPC.TL_messageMediaDocument) == false) goto L62;
        TLRPC.TL_messageMediaDocument r4 = (TLRPC.TL_messageMediaDocument) r13;
        TLRPC.Document r5 = r4.document;
        if (r5 != null) goto L33;
        return false;
    L33:
        if (TextUtils.isEmpty(r15) == true) goto L35;
        String r17 = r15;
    L36:
        this.attachFileName = r17;
        if (MessageObject.isVideoDocument(r4.document) == false) goto L45;
        TLRPC.PhotoSize r18 = FileLoader.getClosestPhotoSizeWithSize(r5.thumbs, 40);
        TLRPC.PhotoSize r2 = FileLoader.getClosestPhotoSizeWithSize(r5.thumbs, AndroidUtilities.dp(36.0f), false, r18, true);
        this.isVideo = true;
        this.needDrawProgress = true;
        ImageReceiver r0 = this.imageReceiver;
        ImageLocation r3 = ImageLocation.getForObject(r2, r5);
        ImageLocation r19 = ImageLocation.getForObject(r18, r5);
        if (r2 == null) goto L41;
        long r42 = r2.size;
    L42:
        r0.setImage(r3, "36_36", r19, "36_36_b", null, r42, null, r14, 1);
        return true;
    L41:
        r42 = 0;
        goto L42
    L45:
        if (MessageObject.isStickerDocument(r5) == false) goto L47;
    L50:
        boolean r110 = true;
    L51:
        boolean r32 = MessageObject.isAnimatedStickerDocument(r5, true);
        if (r110 == true) goto L54;
        if (r32 == false) goto L69;
    L54:
        SvgHelper.SvgDrawable r33 = DocumentObject.getSvgThumb(r5, Theme.key_chat_serviceBackground, 1.0f);
        ImageReceiver r02 = this.imageReceiver;
        boolean r22 = r110;
        ImageLocation r111 = ImageLocation.getForDocument(r5);
        long r43 = r5.size;
        if (r22 == false) goto L58;
        String r23 = "webp";
    L59:
        r02.setImage(r111, "36_36", r33, r43, r23, r14, 1);
        return true;
    L58:
        r23 = null;
        goto L59
    L47:
        if (MessageObject.isVideoSticker(r5) == true) goto L50;
        r110 = false;
        goto L51
    L35:
        r17 = MessageObject.getFileName(r13);
        goto L36
    L62:
        if ((r13 instanceof TLRPC.TL_messageMediaGeo) == false) goto L64;
    L65:
        TLRPC.GeoPoint r112 = r13.geo;
        if (r112 == null) goto L69;
        this.imageReceiver.setImage(ImageLocation.getForWebFile(WebFile.createWithGeoPoint(r112, 36, 36, 13, Math.min(2, (int) Math.ceil(AndroidUtilities.density)))), null, null, null, null, r14, 0);
        return true;
    L64:
        if ((r13 instanceof TLRPC.TL_messageMediaVenue) == false) goto L69;
        goto L65
    }

    public void attach() {
        this.lastVotersDrawable.attach();
        this.imageReceiver.onAttachedToWindow();
        this.radialProgress.onAttachedToWindow();
    }

    public void detach() {
        this.lastVotersDrawable.detach();
        this.imageReceiver.onDetachedFromWindow();
        this.radialProgress.onDetachedFromWindow();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r2) {
        draw(r2, null);
    }

    public void draw(Canvas r10, Paint r11) {
        Rect r0 = getBounds();
        if (this.hasMediaPadding == false) goto L5;
        float r1 = 56.33f;
    L6:
        int r12 = AndroidUtilities.dp(r1);
        if (this.animatorShowVoters.getFloatValue() <= 0.0f) goto L13;
        float r2 = this.lastVotersDrawable.getTotalVisibility();
        int r5 = (int) this.lastVotersDrawable.getAnimatedWidth();
        int r6 = (r0.right - r12) - AndroidUtilities.lerp(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(4.0f) + r5, r2);
        if (r2 <= 0.0f) goto L11;
        this.lastVotersDrawable.setAlpha((int) (this.animatorShowVoters.getFloatValue() * 255.0f));
        this.lastVotersDrawable.setBounds((r0.right - r12) - r5, r0.bottom - AndroidUtilities.dp(31.33f), r0.right - r12, r0.bottom);
        this.lastVotersDrawable.draw(r10, r11);
    L11:
        int r112 = r0.bottom - AndroidUtilities.dp(21.33f);
        this.votersCountDrawable.setAlpha((int) (this.animatorShowVoters.getFloatValue() * 255.0f));
        this.votersCountDrawable.setBounds(r0.left, AndroidUtilities.dp(15.0f) + r112, r6, r112 - AndroidUtilities.dp(15.0f));
        this.votersCountDrawable.draw(r10);
    L13:
        if (this.hasMedia == false) goto L52;
        int r113 = AndroidUtilities.dp(36.0f);
        Rect r13 = AndroidUtilities.rectTmp2;
        r13.set((r0.right - AndroidUtilities.dp(9.0f)) - r113, (r0.bottom - AndroidUtilities.dp(4.0f)) - r113, r0.right - AndroidUtilities.dp(9.0f), r0.bottom - AndroidUtilities.dp(4.0f));
        RectF r114 = AndroidUtilities.rectTmp;
        r114.set(r13);
        this.radialProgress.setProgressRect(r114.left, r114.top, r114.right, r114.bottom);
        this.imageReceiver.setImageCoords(r13);
        if (this.isWebPage == true) goto L17;
    L19:
        this.imageReceiver.draw(r10);
    L21:
        if (this.isVideo == true) goto L25;
        if (this.isWebPage == true) goto L25;
    L35:
        if (this.isWebPage == true) goto L37;
    L48:
        checkIcon(true);
        if (this.needDrawProgress == false) goto L53;
        this.radialProgress.draw(r10);
        return;
    L53:
        return;
    L37:
        if (this.webPageDrawable != null) goto L39;
        this.webPageDrawable = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.media_link_24).mutate();
    L39:
        Drawable r02 = this.webPageDrawable;
        PorterDuffColorFilterState r14 = this.webPageLinkColorFilter;
        if (this.isWebPageWithPreview == false) goto L43;
        int r22 = -1;
    L47:
        r02.setColorFilter(r14.get(r22, PorterDuff.Mode.SRC_IN));
        DrawableUtils.setBounds(this.webPageDrawable, r114.centerX(), r114.centerY(), AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f), 17);
        this.webPageDrawable.draw(r10);
        goto L48
    L43:
        if (this.messageObject.isOutOwner() == false) goto L45;
        int r23 = Theme.key_chat_outTimeText;
    L46:
        r22 = Theme.getColor(r23);
        goto L47
    L45:
        r23 = Theme.key_chat_inTimeText;
    L25:
        if (this.isWebPage == true) goto L27;
    L33:
        r10.drawRoundRect(r114, AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f), this.darkenPaint);
        goto L35
    L27:
        if (this.isWebPageWithPreview == true) goto L33;
        Paint r03 = this.webPageBgPaint;
        if (this.messageObject.isOutOwner() == false) goto L31;
        int r24 = Theme.key_chat_messageTextOut;
    L32:
        r03.setColor(ColorUtils.setAlphaComponent(Theme.getColor(r24), 16));
        r10.drawRoundRect(r114, AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f), this.webPageBgPaint);
        goto L35
    L31:
        r24 = Theme.key_chat_messageTextIn;
        goto L32
    L17:
        if (this.isWebPageWithPreview == true) goto L19;
    L52:
        return;
    L5:
        r1 = 19.0f;
        goto L6
    }

    public ImageReceiver getImageReceiver() {
        return this.imageReceiver;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public int getObserverTag() {
        return this.TAG;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public float getVotersCountAnimatedWidth(float r4) {
        return ((this.lastVotersDrawable.getAnimatedWidth() + this.votersCountDrawable.getCurrentWidth()) + ((AndroidUtilities.dp(4.0f) * this.lastVotersDrawable.getTotalVisibility()) * this.animatorShowVoters.getFloatValue())) + (r4 * this.animatorShowVoters.getFloatValue());
    }

    public float getVotersCountTargetWidth() {
        float r0 = this.votersCountDrawable.getAnimateToWidth();
        int r2 = this.recentVotersCount;
        if (r2 <= 0) goto L5;
        int r22 = AndroidUtilities.dp((r2 * 9.34f) + 8.66f);
    L7:
        return r0 + r22;
    L5:
        r22 = 0;
        goto L7
    }

    public boolean isHasMedia() {
        return this.hasMedia;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onFailedDownload(String r1, boolean r2) {
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressDownload(String r3, long r4, long r6) {
        if (r6 != 0) goto L5;
        float r32 = 0.0f;
    L6:
        this.radialProgress.setProgress(r32, true);
        if (r32 >= 1.0f) goto L9;
        int r33 = 3;
    L10:
        setIcon(r33, true);
        this.parent.invalidate();
        return;
    L9:
        r33 = getDefaultIcon();
        goto L10
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
        if (r32 >= 1.0f) goto L9;
        int r33 = 3;
    L10:
        setIcon(r33, true);
        this.parent.invalidate();
        return;
    L9:
        r33 = getDefaultIcon();
        goto L10
    L5:
        r32 = Math.min(1.0f, r4 / r6);
        goto L6
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onSuccessDownload(String r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setHasMediaPadding(boolean r1) {
        this.hasMediaPadding = r1;
    }

    public void setMedia(MessageObject r3, TLRPC.MessageMedia r4, Object r5, String r6, boolean r7) {
        this.messageObject = r3;
        String r32 = this.attachFileName;
        int r0 = 0;
        this.needDrawProgress = false;
        this.attachFileName = null;
        boolean r42 = setMediaImpl(r4, r5, r6);
        this.hasMedia = r42;
        if (r42 == true) goto L5;
        this.imageReceiver.clearImage();
    L5:
        RadialProgress2 r43 = this.radialProgress;
        if (this.isVideo == false) goto L8;
        int r52 = 0;
    L10:
        if (this.isVideo == true) goto L13;
        r0 = Theme.getColor(Theme.key_chat_mediaLoaderPhotoSelected);
    L13:
        r43.setColors(r52, r0, Theme.getColor(Theme.key_chat_mediaLoaderPhotoIcon), Theme.getColor(Theme.key_chat_mediaLoaderPhotoIconSelected));
        if (TextUtils.equals(r32, this.attachFileName) == false) goto L16;
    L21:
        checkIcon(r7);
        return;
    L16:
        if (TextUtils.isEmpty(r32) == true) goto L19;
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
    L19:
        if (TextUtils.isEmpty(this.attachFileName) == true) goto L21;
        DownloadController.getInstance(this.currentAccount).addLoadingFileObserver(this.attachFileName, this);
        goto L21
    L8:
        r52 = Theme.getColor(Theme.key_chat_mediaLoaderPhoto);
        goto L10
    }

    public void setRecentVoters(List<TLRPC.Peer> r2, boolean r3) {
        if (r2 == null) goto L4;
        int r0 = r2.size();
    L5:
        this.recentVotersCount = r0;
        this.lastVotersDrawable.set(r2, r3);
        return;
    L4:
        r0 = 0;
        goto L5
    }

    public void setVotersCount(int r2, int r3, boolean r4) {
        if (r2 > 0) goto L6;
        this.votersCountDrawable.setText(null, r4);
        return;
    L6:
        String r22 = LocaleController.formatShortNumber(r2, null);
        AnimatedTextView.AnimatedTextDrawable r1 = this.votersCountDrawable;
        if (r3 < 0) goto L10;
        r1.setText(r3 + "% · " + r22, r4);
        return;
    L10:
        r1.setText(r22, r4);
    }

    public void setVotersCountTextColor(int r1) {
        this.votersCountDrawable.setTextColor(r1);
    }

    public void setVotersVisible(boolean r1, boolean r2) {
        this.animatorShowVoters.setValue(r1, r2);
    }

    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this) goto L4;
        return true;
    L4:
        if (r2 != this.votersCountDrawable) goto L6;
        return true;
    L6:
        if (r2 == this.lastVotersDrawable) goto L13;
        return false;
    L13:
        return true;
    }
}
