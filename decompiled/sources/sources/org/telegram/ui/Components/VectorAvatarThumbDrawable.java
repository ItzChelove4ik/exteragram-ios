package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.ColorUtils;
import java.util.HashSet;
import java.util.Iterator;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;

/* loaded from: classes3.dex */
public class VectorAvatarThumbDrawable extends Drawable implements AnimatedEmojiSpan.InvalidateHolder, AttachableDrawable, NotificationCenter.NotificationCenterDelegate {
    AnimatedEmojiDrawable animatedEmojiDrawable;
    final int currentAccount;
    ImageReceiver currentParent;
    public final GradientTools gradientTools;
    ImageReceiver imageReceiver;
    boolean imageSeted;
    boolean isPremium;
    HashSet<ImageReceiver> parents;
    float roundRadius;
    TLRPC.TL_videoSizeStickerMarkup sizeStickerMarkup;
    ImageReceiver stickerPreloadImageReceiver;
    private final int type;

    /* renamed from: org.telegram.ui.Components.VectorAvatarThumbDrawable$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends ImageReceiver {
        final /* synthetic */ VectorAvatarThumbDrawable this$0;

        public AnonymousClass1(VectorAvatarThumbDrawable r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.messenger.ImageReceiver, org.telegram.ui.Components.AnimatedEmojiSpan.InvalidateHolder
        public void invalidate() {
            this.this$0.invalidate();
        }
    }

    static {
    }

    public VectorAvatarThumbDrawable(TLRPC.VideoSize r11, boolean r12, int r13) {
        GradientTools r0 = new GradientTools();
        this.gradientTools = r0;
        this.parents = new HashSet();
        this.stickerPreloadImageReceiver = new ImageReceiver();
        this.currentAccount = UserConfig.selectedAccount;
        this.type = r13;
        this.isPremium = r12;
        int r2 = 0;
        int r1 = ColorUtils.setAlphaComponent(r11.background_colors.get(0).intValue(), 255);
        if (r11.background_colors.size() <= 1) goto L5;
        int r4 = ColorUtils.setAlphaComponent(r11.background_colors.get(1).intValue(), 255);
    L7:
        if (r11.background_colors.size() <= 2) goto L9;
        int r6 = ColorUtils.setAlphaComponent(r11.background_colors.get(2).intValue(), 255);
    L11:
        if (r11.background_colors.size() <= 3) goto L13;
        r2 = ColorUtils.setAlphaComponent(r11.background_colors.get(3).intValue(), 255);
    L13:
        r0.setColors(r1, r4, r6, r2);
        if ((r11 instanceof TLRPC.TL_videoSizeEmojiMarkup) == false) goto L25;
        TLRPC.TL_videoSizeEmojiMarkup r112 = (TLRPC.TL_videoSizeEmojiMarkup) r11;
        if (r13 != 1) goto L19;
        if (r12 == false) goto L19;
        int r122 = 7;
    L22:
        AnimatedEmojiDrawable r132 = new AnimatedEmojiDrawable(r122, UserConfig.selectedAccount, r112.emoji_id);
        this.animatedEmojiDrawable = r132;
        r132.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        return;
    L19:
        if (r13 != 2) goto L21;
        r122 = 15;
        goto L22
    L21:
        r122 = 8;
        goto L22
    L25:
        if ((r11 instanceof TLRPC.TL_videoSizeStickerMarkup) == false) goto L31;
        this.sizeStickerMarkup = (TLRPC.TL_videoSizeStickerMarkup) r11;
        AnonymousClass1 r113 = new AnonymousClass1(this);
        this.imageReceiver = r113;
        r113.setInvalidateAll(true);
        if (r13 != 1) goto L29;
        this.imageReceiver.setAutoRepeatCount(2);
    L29:
        setImage();
        return;
    L31:
        return;
    L9:
        r6 = 0;
        goto L11
    L5:
        r4 = 0;
        goto L7
    }

    private void setImage() {
        int r2 = 0;
        TLRPC.TL_messages_stickerSet r0 = MediaDataController.getInstance(this.currentAccount).getStickerSet(this.sizeStickerMarkup.stickerset, false);
        if (r0 == null) goto L24;
        this.imageSeted = true;
    L6:
        if (r2 >= r0.documents.size()) goto L28;
        if (r0.documents.get(r2).id == this.sizeStickerMarkup.sticker_id) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        TLRPC.Document r11 = r0.documents.get(r2);
        if (this.isPremium == false) goto L16;
        if (this.type != 1) goto L16;
        String r02 = "50_50";
    L14:
        String r4 = r02;
        String r6 = "50_50_firstframe";
        TLRPC.Document r03 = r11;
    L19:
        this.imageReceiver.setImage(ImageLocation.getForDocument(r11), r4, ImageLocation.getForDocument(r03), r6, null, null, DocumentObject.getSvgThumb(r11, Theme.key_windowBackgroundWhiteGrayIcon, 0.2f), 0, "tgs", r11, 0);
        if (this.type != 3) goto L27;
        this.stickerPreloadImageReceiver.setImage(ImageLocation.getForDocument(r11), "100_100", null, null, null, 0, "tgs", r11, 0);
        return;
    L27:
        return;
    L16:
        if (this.type != 2) goto L18;
        r02 = "100_100";
        goto L14
    L18:
        r03 = null;
        r6 = null;
        r4 = "50_50_firstframe";
        goto L19
    L28:
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.groupStickersDidLoad) goto L5;
        return;
    L5:
        if (this.imageSeted == true) goto L9;
        setImage();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r10) {
        this.gradientTools.setBounds(getBounds().left, getBounds().top, getBounds().right, getBounds().bottom);
        if (this.currentParent == null) goto L5;
        this.roundRadius = r0.getRoundRadius()[0];
    L5:
        float r0 = this.roundRadius;
        if (r0 != 0.0f) goto L8;
        r10.drawRect(getBounds(), this.gradientTools.paint);
    L9:
        int r02 = getBounds().centerX();
        int r1 = getBounds().centerY();
        int r2 = ((int) (getBounds().width() * 0.7f)) >> 1;
        AnimatedEmojiDrawable r3 = this.animatedEmojiDrawable;
        if (r3 != null) goto L12;
    L15:
        ImageReceiver r32 = this.imageReceiver;
        if (r32 == null) goto L19;
        float r5 = r2 * 2;
        r32.setRoundRadius((int) (0.13f * r5));
        this.imageReceiver.setImageCoords(r02 - r2, r1 - r2, r5, r5);
        this.imageReceiver.draw(r10);
        return;
    L19:
        return;
    L12:
        if (r3.getImageReceiver() == null) goto L14;
        this.animatedEmojiDrawable.getImageReceiver().setRoundRadius((int) ((r2 * 2) * 0.13f));
    L14:
        this.animatedEmojiDrawable.setBounds(r02 - r2, r1 - r2, r02 + r2, r1 + r2);
        this.animatedEmojiDrawable.draw(r10);
        goto L15
    L8:
        GradientTools r12 = this.gradientTools;
        r10.drawRoundRect(r12.bounds, r0, r0, r12.paint);
        goto L9
    }

    public boolean equals(Object r7) {
        if (this != r7) goto L6;
        return true;
    L6:
        if (r7 != null) goto L8;
    L37:
        return false;
    L8:
        if (getClass() != r7.getClass()) goto L37;
        VectorAvatarThumbDrawable r72 = (VectorAvatarThumbDrawable) r7;
        if (this.type != r72.type) goto L37;
        GradientTools r2 = this.gradientTools;
        int r3 = r2.color1;
        GradientTools r4 = r72.gradientTools;
        if (r3 != r4.color1) goto L37;
        if (r2.color2 != r4.color2) goto L37;
        if (r2.color3 != r4.color3) goto L37;
        if (r2.color4 != r4.color4) goto L37;
        AnimatedEmojiDrawable r22 = this.animatedEmojiDrawable;
        if (r22 != null) goto L23;
    L28:
        TLRPC.TL_videoSizeStickerMarkup r6 = this.sizeStickerMarkup;
        if (r6 == null) goto L37;
        TLRPC.TL_videoSizeStickerMarkup r73 = r72.sizeStickerMarkup;
        if (r73 == null) goto L37;
        if (r6.stickerset.id != r73.stickerset.id) goto L37;
        if (r6.sticker_id != r73.sticker_id) goto L37;
        return true;
    L23:
        if (r72.animatedEmojiDrawable == null) goto L28;
        if (r22.getDocumentId() != r72.animatedEmojiDrawable.getDocumentId()) goto L27;
        return true;
    L27:
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // org.telegram.ui.Components.AnimatedEmojiSpan.InvalidateHolder
    public void invalidate() {
        Iterator<ImageReceiver> r1 = this.parents.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().invalidate();
        goto L4
    }

    @Override // org.telegram.ui.Components.AttachableDrawable
    public void onAttachedToWindow(ImageReceiver r3) {
        if (r3 == null) goto L20;
        this.roundRadius = r3.getRoundRadius()[0];
        if (this.parents.isEmpty() == false) goto L15;
        AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
        if (r0 == null) goto L9;
        r0.addView(this);
    L9:
        ImageReceiver r02 = this.imageReceiver;
        if (r02 == null) goto L12;
        r02.onAttachedToWindow();
    L12:
        ImageReceiver r03 = this.stickerPreloadImageReceiver;
        if (r03 == null) goto L15;
        r03.onAttachedToWindow();
    L15:
        this.parents.add(r3);
        if (this.sizeStickerMarkup == null) goto L19;
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.groupStickersDidLoad);
        return;
    L19:
        return;
    }

    @Override // org.telegram.ui.Components.AttachableDrawable
    public void onDetachedFromWindow(ImageReceiver r2) {
        this.parents.remove(r2);
        if (this.parents.isEmpty() == false) goto L14;
        AnimatedEmojiDrawable r22 = this.animatedEmojiDrawable;
        if (r22 == null) goto L7;
        r22.removeView(this);
    L7:
        ImageReceiver r23 = this.imageReceiver;
        if (r23 == null) goto L10;
        r23.onDetachedFromWindow();
    L10:
        ImageReceiver r24 = this.stickerPreloadImageReceiver;
        if (r24 == null) goto L14;
        r24.onDetachedFromWindow();
    L14:
        if (this.sizeStickerMarkup == null) goto L17;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.groupStickersDidLoad);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        this.gradientTools.paint.setAlpha(r2);
        AnimatedEmojiDrawable r1 = this.animatedEmojiDrawable;
        if (r1 == null) goto L6;
        r1.setAlpha(r2);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setParent(ImageReceiver r1) {
        this.currentParent = r1;
    }
}
