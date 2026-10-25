package org.telegram.ui.Components.Reactions;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.view.View;
import java.util.Objects;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;

/* loaded from: classes7.dex */
public class ReactionImageHolder {
    float alpha;
    public AnimatedEmojiDrawable animatedEmojiDrawable;
    private boolean attached;
    private final Rect bounds;
    ColorFilter colorFilter;
    private final int currentAccount;
    ReactionsLayoutInBubble.VisibleReaction currentReaction;
    final ImageReceiver imageReceiver;
    private boolean isStatic;
    int lastColorForFilter;
    private View parent;

    public ReactionImageHolder(View r2) {
        this.bounds = new Rect();
        this.currentAccount = UserConfig.selectedAccount;
        this.alpha = 1.0f;
        this.parent = r2;
        ImageReceiver r0 = new ImageReceiver(r2);
        this.imageReceiver = r0;
        r0.setAllowLoadingOnAttachedOnly(true);
    }

    public static void preload(int r4, ReactionsLayoutInBubble.VisibleReaction r5) {
        if (r5 != null) goto L5;
        return;
    L5:
        if (r5.emojicon == null) goto L10;
        TLRPC.TL_availableReaction r0 = MediaDataController.getInstance(r4).getReactionsMap().get(r5.emojicon);
        if (r0 == null) goto L12;
        FileLoader.getInstance(r4).loadFile(r0.select_animation, r5, 0, 0);
        return;
    L12:
        return;
    L10:
        new AnimatedEmojiDrawable(1, r4, r5.documentId).preload();
    }

    public void draw(Canvas r6) {
        AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
        if (r0 != null) goto L5;
        ImageReceiver r02 = this.imageReceiver;
        Rect r1 = this.bounds;
        r02.setImageCoords(r1.left, r1.top, r1.width(), this.bounds.height());
        this.imageReceiver.setAlpha(this.alpha);
        this.imageReceiver.draw(r6);
        return;
    L5:
        if (r0.getImageReceiver() == null) goto L7;
        this.animatedEmojiDrawable.getImageReceiver().setRoundRadius((int) (this.bounds.width() * 0.1f));
    L7:
        this.animatedEmojiDrawable.setColorFilter(this.colorFilter);
        this.animatedEmojiDrawable.setBounds(this.bounds);
        this.animatedEmojiDrawable.setAlpha((int) (this.alpha * 255.0f));
        this.animatedEmojiDrawable.draw(r6);
    }

    public boolean isLoaded() {
        AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
        if (r0 == null) goto L5;
        ImageReceiver r2 = r0.getImageReceiver();
    L7:
        if (r2 != null) goto L10;
        return false;
    L10:
        if (r2.hasImageSet() == true) goto L13;
        return false;
    L13:
        if (r2.hasImageLoaded() == true) goto L15;
        return false;
    L15:
        RLottieDrawable r22 = r2.getLottieAnimation();
        if (r22 != null) goto L18;
        return true;
    L18:
        if (r22.isGeneratingCache() == false) goto L22;
        return false;
    L22:
        return true;
    L5:
        r2 = this.imageReceiver;
        goto L7
    }

    public void onAttachedToWindow(boolean r2) {
        this.attached = r2;
        ImageReceiver r0 = this.imageReceiver;
        if (r2 == false) goto L8;
        r0.onAttachedToWindow();
        AnimatedEmojiDrawable r22 = this.animatedEmojiDrawable;
        if (r22 == null) goto L12;
        r22.addView(this.parent);
        return;
    L12:
        return;
    L8:
        r0.onDetachedFromWindow();
        AnimatedEmojiDrawable r23 = this.animatedEmojiDrawable;
        if (r23 == null) goto L13;
        r23.removeView(this.parent);
        return;
    }

    public void play() {
        this.imageReceiver.startAnimation();
    }

    public void setAlpha(float r1) {
        this.alpha = r1;
    }

    public void setBounds(Rect r1) {
        this.bounds.set(r1);
    }

    public void setColor(int r3) {
        if (this.lastColorForFilter == r3) goto L8;
        this.lastColorForFilter = r3;
        this.colorFilter = new PorterDuffColorFilter(this.lastColorForFilter, PorterDuff.Mode.SRC_ATOP);
        View r2 = this.parent;
        if (r2 == null) goto L9;
        r2.invalidate();
        return;
    L9:
        return;
    }

    public void setParent(View r2) {
        if (this.parent != r2) goto L6;
        return;
    L6:
        if (this.attached == false) goto L9;
        onAttachedToWindow(false);
        this.parent = r2;
        onAttachedToWindow(true);
        return;
    L9:
        this.parent = r2;
    }

    public void setStatic() {
        this.isStatic = true;
    }

    public void setVisibleReaction(ReactionsLayoutInBubble.VisibleReaction r14) {
        if (Objects.equals(this.currentReaction, r14) == true) goto L29;
        this.imageReceiver.clearImage();
        AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
        if (r0 == null) goto L8;
        r0.removeView(this.parent);
        this.animatedEmojiDrawable = null;
    L8:
        this.currentReaction = r14;
        boolean r02 = this.isStatic;
        if (r02 == false) goto L12;
        String r1 = "60_60_firstframe";
    L11:
        String r4 = r1;
        if (r14.emojicon == null) goto L20;
        TLRPC.TL_availableReaction r03 = MediaDataController.getInstance(this.currentAccount).getReactionsMap().get(r14.emojicon);
        if (r03 == null) goto L28;
        SvgHelper.SvgDrawable r7 = DocumentObject.getSvgThumb(r03.select_animation, Theme.key_windowBackgroundWhiteGrayIcon, 0.2f);
        this.imageReceiver.setImage(ImageLocation.getForDocument(r03.select_animation), r4, null, null, r7, 0, "tgs", r14, 0);
        return;
    L28:
        return;
    L20:
        if (r02 == false) goto L22;
        int r142 = 13;
    L23:
        AnimatedEmojiDrawable r04 = new AnimatedEmojiDrawable(r142, UserConfig.selectedAccount, r14.documentId);
        this.animatedEmojiDrawable = r04;
        if (this.attached == false) goto L26;
        r04.addView(this.parent);
    L26:
        AnimatedEmojiDrawable r143 = this.animatedEmojiDrawable;
        this.lastColorForFilter = -16777216;
        PorterDuffColorFilter r05 = new PorterDuffColorFilter(-16777216, PorterDuff.Mode.SRC_ATOP);
        this.colorFilter = r05;
        r143.setColorFilter(r05);
        return;
    L22:
        r142 = 1;
        goto L23
    L12:
        r1 = "60_60";
        goto L11
    }
}
