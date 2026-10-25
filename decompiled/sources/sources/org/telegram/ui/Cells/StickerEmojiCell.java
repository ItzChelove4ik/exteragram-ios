package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ListView.RecyclerListViewWithOverlayDraw;
import org.telegram.ui.Components.Premium.PremiumLockIconView;

/* loaded from: classes6.dex */
public class StickerEmojiCell extends FrameLayout implements NotificationCenter.NotificationCenterDelegate, RecyclerListViewWithOverlayDraw.OverlayView {
    private static AccelerateInterpolator interpolator;
    private float alpha;
    private boolean changingAlpha;
    private int currentAccount;
    private String currentEmoji;
    private boolean drawInParentView;
    public ImageView editModeIcon;
    private int editModeIconColor;
    private TextView emojiTextView;
    private boolean fromEmojiPanel;
    private ImageReceiver imageView;
    private boolean isPremiumSticker;
    private long lastUpdateTime;
    private Object parentObject;
    private float premiumAlpha;
    private PremiumLockIconView premiumIconView;
    private boolean recent;
    private final Theme.ResourcesProvider resourceProvider;
    private float scale;
    private boolean scaled;
    private boolean showPremiumLock;
    private TLRPC.Document sticker;
    private SendMessagesHelper.ImportingSticker stickerPath;
    private long time;

    /* renamed from: org.telegram.ui.Cells.StickerEmojiCell$1, reason: invalid class name */
    public class AnonymousClass1 extends ImageReceiver {
        final /* synthetic */ StickerEmojiCell this$0;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass1(StickerEmojiCell r1, Theme.ResourcesProvider r2) {
            this.this$0 = r1;
            this.val$resourcesProvider = r2;
        }

        @Override // org.telegram.messenger.ImageReceiver
        public boolean setImageBitmapByKey(Drawable r4, String r5, int r6, boolean r7, int r8) {
            if ((r4 instanceof BitmapDrawable) == false) goto L13;
            if (StickerEmojiCell.m7048$$Nest$fgeteditModeIconColor(this.this$0) != 0) goto L13;
            Bitmap r0 = ((BitmapDrawable) r4).getBitmap();
            StickerEmojiCell.m7049$$Nest$fputeditModeIconColor(this.this$0, AndroidUtilities.getDominantColor(r0));
            if (StickerEmojiCell.m7048$$Nest$fgeteditModeIconColor(this.this$0) != (-1)) goto L9;
        L10:
            StickerEmojiCell.m7049$$Nest$fputeditModeIconColor(this.this$0, Theme.getColor(Theme.key_dialogTextGray2, this.val$resourcesProvider));
        L11:
            this.this$0.editModeIcon.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(12.0f), StickerEmojiCell.m7048$$Nest$fgeteditModeIconColor(this.this$0)));
            invalidate();
            goto L13
        L9:
            if (StickerEmojiCell.m7048$$Nest$fgeteditModeIconColor(this.this$0) != 0) goto L11;
        L13:
            return super.setImageBitmapByKey(r4, r5, r6, r7, r8);
        }
    }

    /* renamed from: -$$Nest$fgeteditModeIconColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7048$$Nest$fgeteditModeIconColor(StickerEmojiCell r0) {
        return r0.editModeIconColor;
    }

    /* renamed from: -$$Nest$fputeditModeIconColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7049$$Nest$fputeditModeIconColor(StickerEmojiCell r0, int r1) {
        r0.editModeIconColor = r1;
    }

    static {
        interpolator = new AccelerateInterpolator(0.5f);
    }

    public StickerEmojiCell(Context r9, boolean r10, Theme.ResourcesProvider r11) {
        super(r9);
        this.alpha = 1.0f;
        this.currentAccount = UserConfig.selectedAccount;
        this.premiumAlpha = 1.0f;
        this.resourceProvider = r11;
        this.fromEmojiPanel = r10;
        AnonymousClass1 r102 = new AnonymousClass1(this, r11);
        this.imageView = r102;
        r102.setAspectFit(true);
        this.imageView.setAllowLoadingOnAttachedOnly(true);
        ImageReceiver r103 = this.imageView;
        if (ExteraConfig.getStickerShape() != 0) goto L5;
        int r1 = 0;
    L6:
        r103.setRoundRadius(r1);
        this.imageView.setLayerNum(1);
        TextView r104 = new TextView(r9);
        this.emojiTextView = r104;
        r104.setTextSize(1, 16.0f);
        new Paint(1).setColor(Theme.getColor(Theme.key_featuredStickers_addButton));
        PremiumLockIconView r105 = new PremiumLockIconView(r9, PremiumLockIconView.TYPE_STICKERS_PREMIUM_LOCKED);
        this.premiumIconView = r105;
        r105.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
        this.premiumIconView.setImageReceiver(this.imageView);
        addView(this.premiumIconView, LayoutHelper.createFrame(24, 24.0f, 81, 0.0f, 0.0f, 0.0f, 0.0f));
        ImageView r106 = new ImageView(r9);
        this.editModeIcon = r106;
        r106.setImageResource(R.drawable.mini_more_dots);
        this.editModeIcon.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f));
        this.editModeIcon.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(12.0f), Theme.getColor(Theme.key_dialogTextGray2, r11)));
        this.editModeIcon.setAlpha(0.0f);
        addView(this.editModeIcon, LayoutHelper.createFrame(-2, -2, 5));
        setFocusable(true);
        return;
    L5:
        r1 = AndroidUtilities.dp(4.0f);
        goto L6
    }

    private void drawInternal(View r8, Canvas r9) {
        if (this.changingAlpha == true) goto L11;
        boolean r0 = this.scaled;
        if (r0 == true) goto L7;
    L8:
        if (r0 == false) goto L10;
    L31:
        int r82 = Math.min(AndroidUtilities.dp(66.0f), Math.min(getMeasuredHeight(), getMeasuredWidth()));
        int r02 = getMeasuredWidth() >> 1;
        float r03 = r02;
        float r83 = r82;
        float r4 = r83 / 2.0f;
        float r1 = getMeasuredHeight() >> 1;
        this.imageView.setImageCoords(r03 - r4, r1 - r4, r83, r83);
        this.imageView.setAlpha(this.alpha * this.premiumAlpha);
        if (this.scale == 1.0f) goto L35;
        r9.save();
        float r84 = this.scale;
        r9.scale(r84, r84, r03, r1);
        this.imageView.draw(r9);
        r9.restore();
        return;
    L35:
        this.imageView.draw(r9);
        return;
    L10:
        if (this.scale == 1.0f) goto L31;
    L7:
        if (this.scale == 0.8f) goto L8;
    L11:
        long r3 = System.currentTimeMillis();
        long r5 = r3 - this.lastUpdateTime;
        this.lastUpdateTime = r3;
        if (this.changingAlpha == false) goto L21;
        long r04 = this.time + r5;
        this.time = r04;
        if (r04 <= 1050) goto L16;
        this.time = 1050;
    L16:
        float r05 = (interpolator.getInterpolation(this.time / 150.0f) * 0.5f) + 0.5f;
        this.alpha = r05;
        if (r05 < 1.0f) goto L19;
        this.changingAlpha = false;
        this.alpha = 1.0f;
    L19:
        this.imageView.setAlpha(this.alpha * this.premiumAlpha);
    L30:
        r8.invalidate();
        goto L31
    L21:
        if (this.scaled == false) goto L27;
        float r06 = this.scale;
        if (r06 == 0.8f) goto L27;
        float r07 = r06 - (r5 / 400.0f);
        this.scale = r07;
        if (r07 >= 0.8f) goto L30;
        this.scale = 0.8f;
    L27:
        float r08 = this.scale + (r5 / 400.0f);
        this.scale = r08;
        if (r08 <= 1.0f) goto L30;
        this.scale = 1.0f;
        goto L30
    }

    private void updatePremiumStatus(boolean r7) {
        if (this.isPremiumSticker == false) goto L5;
        this.showPremiumLock = true;
    L6:
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.premiumIconView.getLayoutParams();
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L9;
        int r3 = AndroidUtilities.dp(24.0f);
        r0.width = r3;
        r0.height = r3;
        r0.gravity = 81;
        r0.rightMargin = 0;
        r0.bottomMargin = AndroidUtilities.dp(8.0f);
        this.premiumIconView.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
    L10:
        this.premiumIconView.setLocked(!UserConfig.getInstance(this.currentAccount).isPremium());
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

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.currentUserPremiumStatusChanged) goto L6;
        updatePremiumStatus(true);
        return;
    }

    public void disable() {
        this.changingAlpha = true;
        this.alpha = 0.5f;
        this.time = 0;
        this.imageView.setAlpha(0.5f * this.premiumAlpha);
        this.imageView.invalidate();
        this.lastUpdateTime = System.currentTimeMillis();
        invalidate();
    }

    public void disableEditMode(boolean r3) {
        ImageView r2 = this.editModeIcon;
        if (r3 == false) goto L6;
        r2.animate().alpha(0.0f).scaleX(0.4f).scaleY(0.4f).setDuration(200).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        return;
    L6:
        r2.setAlpha(0.0f);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r2) {
        if (this.drawInParentView == true) goto L5;
        drawInternal(this, r2);
    L5:
        super.dispatchDraw(r2);
    }

    public void enableEditMode(boolean r3) {
        ImageView r0 = this.editModeIcon;
        if (r3 == false) goto L6;
        r0.setAlpha(0.0f);
        this.editModeIcon.setScaleX(0.4f);
        this.editModeIcon.setScaleY(0.4f);
        this.editModeIcon.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(200).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        return;
    L6:
        r0.setAlpha(1.0f);
        this.editModeIcon.setScaleX(1.0f);
        this.editModeIcon.setScaleY(1.0f);
    }

    public String getEmoji() {
        return this.currentEmoji;
    }

    public ImageReceiver getImageView() {
        return this.imageView;
    }

    public Object getParentObject() {
        return this.parentObject;
    }

    public MessageObject.SendAnimationData getSendAnimationData() {
        ImageReceiver r0 = this.imageView;
        if (r0.hasNotThumb() == true) goto L6;
        return null;
    L6:
        MessageObject.SendAnimationData r1 = new MessageObject.SendAnimationData();
        getLocationInWindow(new int[2]);
        r1.x = r0.getCenterX() + r2[0];
        r1.y = r0.getCenterY() + r2[1];
        r1.width = r0.getImageWidth();
        r1.height = r0.getImageHeight();
        return r1;
    }

    public TLRPC.Document getSticker() {
        return this.sticker;
    }

    public SendMessagesHelper.ImportingSticker getStickerPath() {
        SendMessagesHelper.ImportingSticker r1 = this.stickerPath;
        if (r1 != null) goto L5;
        return null;
    L5:
        if (r1.validated == false) goto L9;
        return r1;
    L9:
        return null;
    }

    @Override // android.view.View
    public void invalidate() {
        if (this.drawInParentView == true) goto L5;
    L7:
        this.emojiTextView.invalidate();
        super.invalidate();
        return;
    L5:
        if (getParent() == null) goto L7;
        ((View) getParent()).invalidate();
        goto L7
    }

    public boolean isDisabled() {
        return this.changingAlpha;
    }

    public boolean isRecent() {
        return this.recent;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        boolean r0 = this.drawInParentView;
        ImageReceiver r1 = this.imageView;
        if (r0 == false) goto L5;
        r1.setInvalidateAll(true);
        this.imageView.setParentView((View) getParent());
    L6:
        this.imageView.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
        return;
    L5:
        r1.setParentView(this);
        goto L6
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.imageView.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r6) {
        super.onInitializeAccessibilityNodeInfo(r6);
        String r0 = LocaleController.getString(R.string.AttachSticker);
        if (this.sticker == null) goto L15;
        int r2 = 0;
    L6:
        if (r2 >= this.sticker.attributes.size()) goto L15;
        TLRPC.DocumentAttribute r3 = this.sticker.attributes.get(r2);
        if ((r3 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        String r22 = r3.alt;
        if (r22 == null) goto L15;
        if (r22.length() <= 0) goto L15;
        TextView r5 = this.emojiTextView;
        r5.setText(Emoji.replaceEmoji(r3.alt, r5.getPaint().getFontMetricsInt(), false));
        r0 = r3.alt + " " + r0;
    L15:
        r6.setContentDescription(r0);
        r6.setEnabled(true);
    }

    @Override // org.telegram.ui.Components.ListView.RecyclerListViewWithOverlayDraw.OverlayView
    public void preDraw(View r2, Canvas r3) {
        if (this.drawInParentView == false) goto L6;
        drawInternal(r2, r3);
        return;
    }

    public void setRecent(boolean r1) {
        this.recent = r1;
    }

    public void setScaled(boolean r3) {
        this.scaled = r3;
        this.lastUpdateTime = System.currentTimeMillis();
        invalidate();
    }

    public void setSticker(SendMessagesHelper.ImportingSticker r8) {
        String r4 = r8.emoji;
        if (r4 == null) goto L6;
        boolean r0 = true;
    L7:
        setSticker(null, r8, null, r4, r0, false);
        return;
    L6:
        r0 = false;
        goto L7
    }

    public void setSticker(TLRPC.Document r8, Object r9, boolean r10) {
        setSticker(r8, null, r9, null, r10, false);
    }

    public void setSticker(TLRPC.Document r8, SendMessagesHelper.ImportingSticker r9, Object r10, String r11, boolean r12) {
        setSticker(r8, r9, r10, r11, r12, false);
    }

    public void setSticker(TLRPC.Document r26, SendMessagesHelper.ImportingSticker r27, Object r28, String r29, boolean r30, boolean r31) {
        this.currentEmoji = r29;
        this.isPremiumSticker = MessageObject.isPremiumSticker(r26);
        this.drawInParentView = false;
        this.imageView.setColorFilter(null);
        this.editModeIconColor = 0;
        this.editModeIcon.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(12.0f), Theme.getColor(Theme.key_dialogTextGray2)));
        if (r31 == false) goto L5;
        enableEditMode(false);
    L7:
        if (this.isPremiumSticker == false) goto L10;
        this.premiumIconView.setColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.premiumIconView.setWaitingImage();
    L10:
        if (r27 == null) goto L27;
        this.stickerPath = r27;
        boolean r1 = r27.validated;
        ImageReceiver r5 = this.imageView;
        if (r1 == false) goto L18;
        ImageLocation r12 = ImageLocation.getForPath(r27.path);
        SvgHelper.SvgDrawable r8 = DocumentObject.getSvgRectThumb(Theme.key_dialogBackgroundGray, 1.0f);
        if (r27.animated == false) goto L16;
        String r13 = "tgs";
    L17:
        boolean r2 = true;
        int r14 = 4;
        r5.setImage(r12, "80_80", null, null, r8, 0, r13, 0, 1);
    L23:
        TextView r52 = this.emojiTextView;
        if (r29 == null) goto L26;
        r52.setText(Emoji.replaceEmoji(r29, r52.getPaint().getFontMetricsInt(), false));
        this.emojiTextView.setVisibility(0);
    L82:
        updatePremiumStatus(false);
        this.imageView.setAlpha(this.alpha * this.premiumAlpha);
        boolean r15 = this.drawInParentView;
        ImageReceiver r3 = this.imageView;
        if (r15 == false) goto L86;
        r3.setInvalidateAll(r2);
        this.imageView.setParentView((View) getParent());
        return;
    L86:
        r3.setParentView(this);
        return;
    L26:
        r52.setVisibility(r14);
        goto L82
    L16:
        r13 = null;
        goto L17
    L18:
        r14 = 4;
        SvgHelper.SvgDrawable r82 = DocumentObject.getSvgRectThumb(Theme.key_dialogBackgroundGray, 1.0f);
        if (r27.animated == false) goto L21;
        String r132 = "tgs";
    L22:
        r2 = true;
        r5.setImage(null, null, null, null, r82, 0, r132, 0, 1);
        goto L23
    L21:
        r132 = null;
        goto L22
    L27:
        r2 = true;
        if (r26 == null) goto L82;
        this.sticker = r26;
        this.parentObject = r28;
        TLRPC.PhotoSize r53 = FileLoader.getClosestPhotoSizeWithSize(r26.thumbs, 90);
        boolean r6 = this.fromEmojiPanel;
        if (r6 == false) goto L32;
        int r7 = Theme.key_emptyListPlaceholder;
    L33:
        if (r6 == false) goto L35;
        float r62 = 0.2f;
    L36:
        SvgHelper.SvgDrawable r142 = DocumentObject.getSvgThumb(r26, r7, r62, 1.0f, this.resourceProvider);
        if (this.fromEmojiPanel == false) goto L40;
        String r63 = "66_66_pcache_compress";
    L39:
        String r17 = r63;
        if (MessageObject.isTextColorEmoji(r26) == false) goto L45;
        this.imageView.setColorFilter(Theme.getAnimatedEmojiColorFilter(this.resourceProvider));
    L45:
        if (MessageObject.canAutoplayAnimatedSticker(r26) == false) goto L56;
        if (this.fromEmojiPanel == false) goto L49;
        this.drawInParentView = true;
    L49:
        if (r142 == null) goto L51;
        this.imageView.setImage(ImageLocation.getForDocument(r26), r17, ImageLocation.getForDocument(r53, r26), null, null, null, r142, 0, null, this.parentObject, 1);
    L65:
        if (r29 == null) goto L67;
        TextView r16 = this.emojiTextView;
        r16.setText(Emoji.replaceEmoji(r29, r16.getPaint().getFontMetricsInt(), false));
        this.emojiTextView.setVisibility(0);
        goto L82
    L67:
        if (r30 == false) goto L81;
        int r32 = 0;
    L70:
        if (r32 >= r26.attributes.size()) goto L79;
        TLRPC.DocumentAttribute r54 = r26.attributes.get(r32);
        if ((r54 instanceof TLRPC.TL_documentAttributeSticker) == true) goto L73;
        r32 = r32 + 1;
        goto L70
    L73:
        String r18 = r54.alt;
        if (r18 == null) goto L79;
        if (r18.length() <= 0) goto L79;
        TextView r19 = this.emojiTextView;
        r19.setText(Emoji.replaceEmoji(r54.alt, r19.getPaint().getFontMetricsInt(), false));
    L80:
        this.emojiTextView.setVisibility(0);
    L79:
        this.emojiTextView.setText(Emoji.replaceEmoji(MediaDataController.getInstance(this.currentAccount).getEmojiForSticker(this.sticker.id), this.emojiTextView.getPaint().getFontMetricsInt(), false));
        goto L80
    L81:
        this.emojiTextView.setVisibility(4);
        goto L82
    L51:
        ImageReceiver r152 = this.imageView;
        if (r53 == null) goto L54;
        r152.setImage(ImageLocation.getForDocument(r26), r17, ImageLocation.getForDocument(r53, r26), null, null, this.parentObject, 1);
        goto L65
    L54:
        r152.setImage(ImageLocation.getForDocument(r26), r17, null, null, this.parentObject, 1);
        goto L65
    L56:
        if (r142 == null) goto L61;
        ImageReceiver r11 = this.imageView;
        if (r53 == null) goto L60;
        r11.setImage(ImageLocation.getForDocument(r53, r26), r17, r142, "webp", this.parentObject, 1);
        goto L65
    L60:
        r11.setImage(ImageLocation.getForDocument(r26), r17, r142, "webp", this.parentObject, 1);
        goto L65
    L61:
        ImageReceiver r153 = this.imageView;
        if (r53 == null) goto L64;
        r153.setImage(ImageLocation.getForDocument(r53, r26), r17, null, "webp", this.parentObject, 1);
        goto L65
    L64:
        r153.setImage(ImageLocation.getForDocument(r26), r17, null, "webp", this.parentObject, 1);
        goto L65
    L40:
        r63 = "66_66";
        goto L39
    L35:
        r62 = 1.0f;
        goto L36
    L32:
        r7 = Theme.key_windowBackgroundGray;
        goto L33
    L5:
        disableEditMode(false);
        goto L7
    }

    public boolean showingBitmap() {
        return this.imageView.hasNotThumb();
    }
}
