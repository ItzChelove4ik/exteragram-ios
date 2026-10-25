package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import com.exteragram.messenger.ExteraConfig;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumLockIconView;

/* loaded from: classes6.dex */
public class StickerCell extends FrameLayout {
    private static AccelerateInterpolator interpolator;
    private boolean clearsInputField;
    private BackupImageView imageView;
    private boolean isPremiumSticker;
    private long lastUpdateTime;
    private Object parentObject;
    private float premiumAlpha;
    private PremiumLockIconView premiumIconView;
    Theme.ResourcesProvider resourcesProvider;
    private float scale;
    private boolean scaled;
    private boolean showPremiumLock;
    private TLRPC.Document sticker;
    private long time;

    static {
        interpolator = new AccelerateInterpolator(0.5f);
    }

    public StickerCell(Context r11, Theme.ResourcesProvider r12) {
        super(r11);
        this.time = 0;
        this.premiumAlpha = 1.0f;
        this.resourcesProvider = r12;
        BackupImageView r122 = new BackupImageView(r11);
        this.imageView = r122;
        r122.setAspectFit(true);
        BackupImageView r123 = this.imageView;
        if (ExteraConfig.getStickerShape() != 0) goto L5;
        int r1 = 0;
    L6:
        r123.setRoundRadius(r1);
        this.imageView.setLayerNum(1);
        addView(this.imageView, LayoutHelper.createFrame(66, 66.0f, 1, 0.0f, 5.0f, 0.0f, 0.0f));
        setFocusable(true);
        PremiumLockIconView r124 = new PremiumLockIconView(r11, PremiumLockIconView.TYPE_STICKERS_PREMIUM_LOCKED);
        this.premiumIconView = r124;
        r124.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
        this.premiumIconView.setImageReceiver(this.imageView.getImageReceiver());
        addView(this.premiumIconView, LayoutHelper.createFrame(24, 24.0f, 81, 0.0f, 0.0f, 0.0f, 0.0f));
        return;
    L5:
        r1 = AndroidUtilities.dp(4.0f);
        goto L6
    }

    private void updatePremiumStatus(boolean r7) {
        if (this.isPremiumSticker == false) goto L5;
        this.showPremiumLock = true;
    L6:
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.premiumIconView.getLayoutParams();
        if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == true) goto L9;
        int r3 = AndroidUtilities.dp(24.0f);
        r0.width = r3;
        r0.height = r3;
        r0.gravity = 81;
        r0.rightMargin = 0;
        r0.bottomMargin = 0;
        this.premiumIconView.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
    L10:
        this.premiumIconView.setLocked(!UserConfig.getInstance(UserConfig.selectedAccount).isPremium());
        AndroidUtilities.updateViewVisibilityAnimated(this.premiumIconView, this.showPremiumLock, 0.9f, r7);
        invalidate();
        return;
    L9:
        int r1 = AndroidUtilities.dp(16.0f);
        r0.width = r1;
        r0.height = r1;
        r0.gravity = 85;
        r0.bottomMargin = AndroidUtilities.dp(8.0f);
        r0.rightMargin = AndroidUtilities.dp(8.0f);
        this.premiumIconView.setPadding(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f));
        goto L10
    L5:
        this.showPremiumLock = false;
        goto L6
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r5, View r6, long r7) {
        boolean r52 = super.drawChild(r5, r6, r7);
        if (r6 != this.imageView) goto L22;
        boolean r62 = this.scaled;
        if (r62 == true) goto L7;
    L8:
        if (r62 == true) goto L22;
        if (this.scale == 1.0f) goto L22;
    L11:
        long r0 = System.currentTimeMillis();
        long r2 = r0 - this.lastUpdateTime;
        this.lastUpdateTime = r0;
        if (this.scaled == false) goto L18;
        float r63 = this.scale;
        if (r63 == 0.8f) goto L18;
        float r64 = r63 - (r2 / 400.0f);
        this.scale = r64;
        if (r64 >= 0.8f) goto L21;
        this.scale = 0.8f;
    L21:
        this.imageView.setScaleX(this.scale);
        this.imageView.setScaleY(this.scale);
        this.imageView.invalidate();
        invalidate();
    L18:
        float r65 = this.scale + (r2 / 400.0f);
        this.scale = r65;
        if (r65 <= 1.0f) goto L21;
        this.scale = 1.0f;
        goto L21
    L7:
        if (this.scale != 0.8f) goto L11;
    L22:
        return r52;
    }

    public Object getParentObject() {
        return this.parentObject;
    }

    public MessageObject.SendAnimationData getSendAnimationData() {
        ImageReceiver r0 = this.imageView.getImageReceiver();
        if (r0.hasNotThumb() == true) goto L6;
        return null;
    L6:
        MessageObject.SendAnimationData r1 = new MessageObject.SendAnimationData();
        this.imageView.getLocationInWindow(new int[2]);
        r1.x = r0.getCenterX() + r2[0];
        r1.y = r0.getCenterY() + r2[1];
        r1.width = r0.getImageWidth();
        r1.height = r0.getImageHeight();
        return r1;
    }

    public TLRPC.Document getSticker() {
        return this.sticker;
    }

    public boolean isClearsInputField() {
        return this.clearsInputField;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r6) {
        super.onInitializeAccessibilityNodeInfo(r6);
        if (this.sticker != null) goto L5;
        return;
    L5:
        int r1 = 0;
        String r2 = null;
    L7:
        if (r1 >= this.sticker.attributes.size()) goto L17;
        TLRPC.DocumentAttribute r3 = this.sticker.attributes.get(r1);
        if ((r3 instanceof TLRPC.TL_documentAttributeSticker) == false) goto L16;
        String r22 = r3.alt;
        if (r22 != null) goto L13;
    L15:
        r2 = null;
        goto L16
    L13:
        if (r22.length() <= 0) goto L15;
        r2 = r3.alt;
    L16:
        r1 = r1 + 1;
        goto L7
    L17:
        if (r2 == null) goto L19;
        r6.setText(r2 + " " + LocaleController.getString(R.string.AttachSticker));
    L20:
        r6.setEnabled(true);
        return;
    L19:
        r6.setText(LocaleController.getString(R.string.AttachSticker));
        goto L20
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec((AndroidUtilities.dp(76.0f) + getPaddingLeft()) + getPaddingRight(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(78.0f), TLObject.FLAG_30));
    }

    public void setClearsInputField(boolean r1) {
        this.clearsInputField = r1;
    }

    @Override // android.view.View
    public void setPressed(boolean r2) {
        if (this.imageView.getImageReceiver().getPressed() == r2) goto L5;
        this.imageView.getImageReceiver().setPressed(r2 ? 1 : 0);
        this.imageView.invalidate();
    L5:
        super.setPressed(r2);
    }

    public void setScaled(boolean r3) {
        this.scaled = r3;
        this.lastUpdateTime = System.currentTimeMillis();
        invalidate();
    }

    public void setSticker(TLRPC.Document r20, Object r21) {
        this.parentObject = r21;
        boolean r2 = MessageObject.isPremiumSticker(r20);
        this.isPremiumSticker = r2;
        if (r2 == false) goto L5;
        this.premiumIconView.setColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.premiumIconView.setWaitingImage();
    L5:
        if (r20 == null) goto L20;
        TLRPC.PhotoSize r22 = FileLoader.getClosestPhotoSizeWithSize(r20.thumbs, 90);
        SvgHelper.SvgDrawable r10 = DocumentObject.getSvgThumb(r20, Theme.key_windowBackgroundGray, 1.0f, 1.0f, this.resourcesProvider);
        if (MessageObject.canAutoplayAnimatedSticker(r20) == false) goto L14;
        if (r10 == null) goto L10;
        this.imageView.setImage(ImageLocation.getForDocument(r20), "80_80", null, r10, this.parentObject);
        goto L20
    L10:
        BackupImageView r12 = this.imageView;
        if (r22 == null) goto L13;
        r12.setImage(ImageLocation.getForDocument(r20), "80_80", ImageLocation.getForDocument(r22, r20), null, 0, this.parentObject);
        goto L20
    L13:
        r12.setImage(ImageLocation.getForDocument(r20), "80_80", null, null, this.parentObject);
        goto L20
    L14:
        if (r10 == null) goto L19;
        BackupImageView r6 = this.imageView;
        if (r22 == null) goto L18;
        r6.setImage(ImageLocation.getForDocument(r22, r20), null, "webp", r10, this.parentObject);
        goto L20
    L18:
        r6.setImage(ImageLocation.getForDocument(r20), null, "webp", r10, this.parentObject);
        goto L20
    L19:
        this.imageView.setImage(ImageLocation.getForDocument(r22, r20), null, "webp", null, this.parentObject);
    L20:
        this.sticker = r20;
        Drawable r1 = getBackground();
        if (r1 == null) goto L23;
        r1.setAlpha(230);
        r1.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_stickersHintPanel), PorterDuff.Mode.MULTIPLY));
    L23:
        updatePremiumStatus(false);
    }

    public boolean showingBitmap() {
        if (this.imageView.getImageReceiver().getBitmap() == null) goto L6;
        return true;
    L6:
        return false;
    }
}
