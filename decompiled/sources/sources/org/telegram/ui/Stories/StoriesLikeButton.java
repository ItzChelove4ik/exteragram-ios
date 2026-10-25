package org.telegram.ui.Stories;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Stories.PeerStoriesView;

/* loaded from: classes7.dex */
public class StoriesLikeButton extends View {
    private boolean allowDrawReaction;
    ImageReceiver animateReactionImageReceiver;
    private boolean attachedToWindow;
    ReactionsLayoutInBubble.VisibleReaction currentReaction;
    private boolean drawAnimateImageReciever;
    AnimatedEmojiDrawable emojiDrawable;
    private boolean isLike;
    boolean liked;
    AnimatedFloat progressToLiked;
    ImageReceiver reactionImageReceiver;
    PeerStoriesView.SharedResources sharedResources;

    public StoriesLikeButton(Context r1, PeerStoriesView.SharedResources r2) {
        super(r1);
        this.progressToLiked = new AnimatedFloat(this);
        this.reactionImageReceiver = new ImageReceiver(this);
        this.animateReactionImageReceiver = new ImageReceiver(this);
        this.allowDrawReaction = true;
        this.sharedResources = r2;
        this.reactionImageReceiver.setAllowLoadingOnAttachedOnly(true);
        this.reactionImageReceiver.ignoreNotifications = true;
    }

    public void animateVisibleReaction() {
        this.drawAnimateImageReciever = true;
        if (this.animateReactionImageReceiver.getLottieAnimation() == null) goto L6;
        this.animateReactionImageReceiver.getLottieAnimation().setCurrentFrame(0, false, true);
        return;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.reactionImageReceiver.onAttachedToWindow();
        this.animateReactionImageReceiver.onAttachedToWindow();
        this.attachedToWindow = true;
        AnimatedEmojiDrawable r0 = this.emojiDrawable;
        if (r0 == null) goto L6;
        r0.addView(this);
        return;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.reactionImageReceiver.onDetachedFromWindow();
        this.animateReactionImageReceiver.onDetachedFromWindow();
        this.attachedToWindow = false;
        AnimatedEmojiDrawable r0 = this.emojiDrawable;
        if (r0 == null) goto L6;
        r0.removeView(this);
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        if (this.isLike == false) goto L16;
        AnimatedFloat r0 = this.progressToLiked;
        if (this.liked == false) goto L7;
        float r1 = 1.0f;
    L8:
        float r02 = r0.set(r1);
        if (r02 >= 1.0f) goto L12;
        this.sharedResources.likeDrawable.setBounds(getPaddingLeft(), getPaddingTop(), getMeasuredWidth() - getPaddingRight(), getMeasuredHeight() - getPaddingBottom());
        this.sharedResources.likeDrawable.setAlpha(255);
        this.sharedResources.likeDrawable.draw(r9);
    L12:
        if (r02 <= 0.0f) goto L35;
        this.sharedResources.likeDrawableFilled.setBounds(getPaddingLeft(), getPaddingTop(), getMeasuredWidth() - getPaddingRight(), getMeasuredHeight() - getPaddingBottom());
        this.sharedResources.likeDrawableFilled.setAlpha((int) (r02 * 255.0f));
        this.sharedResources.likeDrawableFilled.draw(r9);
        return;
    L35:
        return;
    L7:
        r1 = 0.0f;
        goto L8
    L16:
        if (this.allowDrawReaction == false) goto L36;
        AnimatedEmojiDrawable r03 = this.emojiDrawable;
        if (r03 == null) goto L20;
        ImageReceiver r04 = r03.getImageReceiver();
    L22:
        if (this.drawAnimateImageReciever == true) goto L24;
    L30:
        if (r04 == null) goto L32;
        r04.setImageCoords(getPaddingLeft(), getPaddingTop(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
    L32:
        if (r04 == null) goto L37;
        r04.draw(r9);
        return;
    L37:
        return;
    L24:
        if (this.animateReactionImageReceiver.getBitmap() == null) goto L30;
        r04 = this.animateReactionImageReceiver;
        int r12 = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        float r3 = r12 / 2.0f;
        float r13 = r12 * 2;
        r04.setImageCoords(getPaddingLeft() - r3, getPaddingTop() - r3, r13, r13);
        if (this.animateReactionImageReceiver.getLottieAnimation() == null) goto L32;
        if (this.animateReactionImageReceiver.getLottieAnimation().isLastFrame() == false) goto L32;
        this.drawAnimateImageReciever = false;
        this.reactionImageReceiver.setCrossfadeAlpha((byte) 0);
        goto L32
    L20:
        r04 = this.reactionImageReceiver;
        goto L22
    }

    public void prepareAnimateReaction(ReactionsLayoutInBubble.VisibleReaction r8) {
        if (r8.documentId != 0) goto L8;
        TLRPC.TL_availableReaction r5 = MediaDataController.getInstance(UserConfig.selectedAccount).getReactionsMap().get(r8.emojicon);
        if (r5 == null) goto L9;
        this.animateReactionImageReceiver.setImage(ImageLocation.getForDocument(r5.center_icon), "40_40_nolimit", null, "tgs", r5, 1);
        this.animateReactionImageReceiver.setAutoRepeat(0);
        return;
    L9:
        return;
    }

    public void setAllowDrawReaction(boolean r2) {
        if (this.allowDrawReaction != r2) goto L5;
        return;
    L5:
        this.allowDrawReaction = r2;
        invalidate();
    }

    public void setReaction(ReactionsLayoutInBubble.VisibleReaction r8) {
        if (r8 == null) goto L10;
        String r3 = r8.emojicon;
        if (r3 != null) goto L7;
    L9:
        boolean r32 = false;
    L11:
        this.isLike = r32;
        if (r8 == null) goto L18;
        String r33 = r8.emojicon;
        if (r33 == null) goto L18;
        if (r33.equals("❤") == false) goto L18;
        this.liked = true;
    L19:
        this.currentReaction = r8;
        AnimatedEmojiDrawable r0 = this.emojiDrawable;
        if (r0 == null) goto L22;
        r0.removeView(this);
    L22:
        this.emojiDrawable = null;
        if (r8 != null) goto L25;
    L32:
        invalidate();
        return;
    L25:
        if (r8.documentId == 0) goto L29;
        AnimatedEmojiDrawable r02 = new AnimatedEmojiDrawable(3, UserConfig.selectedAccount, r8.documentId);
        this.emojiDrawable = r02;
        if (this.attachedToWindow == false) goto L32;
        r02.addView(this);
        goto L32
    L29:
        TLRPC.TL_availableReaction r5 = MediaDataController.getInstance(UserConfig.selectedAccount).getReactionsMap().get(r8.emojicon);
        if (r5 == null) goto L32;
        this.reactionImageReceiver.setImage(ImageLocation.getForDocument(r5.center_icon), "40_40_lastreactframe", DocumentObject.getSvgThumb(r5.static_icon, Theme.key_windowBackgroundGray, 1.0f), "webp", r5, 1);
    L18:
        this.liked = false;
        goto L19
    L7:
        if (r3.equals("❤") == false) goto L9;
    L10:
        r32 = true;
        goto L11
    }
}
