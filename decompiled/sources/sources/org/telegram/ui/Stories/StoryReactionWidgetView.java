package org.telegram.ui.Stories;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.Reactions.ReactionImageHolder;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.Reactions.ReactionsUtils;
import org.telegram.ui.EmojiAnimationsOverlay;
import org.telegram.ui.Stories.StoryMediaAreasView;

/* loaded from: classes7.dex */
public class StoryReactionWidgetView extends StoryMediaAreasView.AreaView {
    AnimatedTextView.AnimatedTextDrawable animatedTextDrawable;
    boolean hasCounter;
    ReactionImageHolder holder;
    ImageReceiver preloadSmallReaction;
    AnimatedFloat progressToCount;
    StoryReactionWidgetBackground storyReactionWidgetBackground;
    private final ReactionsLayoutInBubble.VisibleReaction visibleReaction;

    public StoryReactionWidgetView(Context r8, View r9, TL_stories.TL_mediaAreaSuggestedReaction r10, EmojiAnimationsOverlay r11) {
        super(r8, r9, r10);
        this.storyReactionWidgetBackground = new StoryReactionWidgetBackground(this);
        this.holder = new ReactionImageHolder(this);
        this.preloadSmallReaction = new ImageReceiver(this);
        this.progressToCount = new AnimatedFloat(this);
        this.animatedTextDrawable = new AnimatedTextView.AnimatedTextDrawable();
        ReactionsLayoutInBubble.VisibleReaction r82 = ReactionsLayoutInBubble.VisibleReaction.fromTL(r10.reaction);
        this.visibleReaction = r82;
        if (r10.flipped == false) goto L5;
        this.storyReactionWidgetBackground.setMirror(true, false);
    L5:
        this.storyReactionWidgetBackground.updateShadowLayer(getScaleX());
        this.holder.setVisibleReaction(r82);
        r11.preload(r82);
        if (r82.emojicon == null) goto L10;
        TLRPC.TL_availableReaction r5 = MediaDataController.getInstance(UserConfig.selectedAccount).getReactionsMap().get(r82.emojicon);
        if (r5 == null) goto L10;
        this.preloadSmallReaction.setImage(ImageLocation.getForDocument(r5.center_icon), "40_40_lastreactframe", null, "webp", r5, 1);
    L10:
        this.animatedTextDrawable.setGravity(17);
        this.animatedTextDrawable.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_CONDENSED_BOLD));
        this.animatedTextDrawable.setTextSize(AndroidUtilities.dp(18.0f));
        this.animatedTextDrawable.setOverrideFullWidth(AndroidUtilities.displaySize.x);
        if (r10.dark == false) goto L14;
        this.storyReactionWidgetBackground.nextStyle();
        this.animatedTextDrawable.setTextColor(-1);
        return;
    }

    @Override // org.telegram.ui.Stories.StoryMediaAreasView.AreaView
    public void customDraw(Canvas r9) {
        this.storyReactionWidgetBackground.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        this.storyReactionWidgetBackground.draw(r9);
        float r0 = ((int) (getMeasuredWidth() * 0.61f)) / 2.0f;
        float r1 = this.storyReactionWidgetBackground.getBounds().centerX() - r0;
        float r2 = this.storyReactionWidgetBackground.getBounds().centerY() - r0;
        float r3 = this.storyReactionWidgetBackground.getBounds().centerX() + r0;
        float r4 = this.storyReactionWidgetBackground.getBounds().centerY() + r0;
        float r5 = this.storyReactionWidgetBackground.getBounds().top + (this.storyReactionWidgetBackground.getBounds().height() * 0.427f);
        float r6 = r5 - r0;
        float r52 = r5 + r0;
        AnimatedFloat r02 = this.progressToCount;
        if (this.hasCounter == false) goto L5;
        float r7 = 1.0f;
    L6:
        float r03 = r02.set(r7);
        Rect r72 = AndroidUtilities.rectTmp2;
        r72.set((int) r1, (int) AndroidUtilities.lerp(r2, r6, r03), (int) r3, (int) AndroidUtilities.lerp(r4, r52, r03));
        ReactionImageHolder r12 = this.holder;
        if (this.storyReactionWidgetBackground.isDarkStyle() == false) goto L9;
        int r22 = -1;
    L10:
        r12.setColor(r22);
        this.holder.setBounds(r72);
        this.holder.draw(r9);
        float r13 = this.storyReactionWidgetBackground.getBounds().top + (this.storyReactionWidgetBackground.getBounds().height() * 0.839f);
        this.animatedTextDrawable.setBounds(this.storyReactionWidgetBackground.getBounds().left, (int) (r13 - AndroidUtilities.dp(10.0f)), this.storyReactionWidgetBackground.getBounds().right, (int) (AndroidUtilities.dp(10.0f) + r13));
        r9.save();
        r9.scale(r03, r03, this.storyReactionWidgetBackground.getBounds().centerX(), r13);
        this.animatedTextDrawable.draw(r9);
        r9.restore();
        return;
    L9:
        r22 = -16777216;
        goto L10
    L5:
        r7 = 0.0f;
        goto L6
    }

    public AnimatedEmojiDrawable getAnimatedEmojiDrawable() {
        return this.holder.animatedEmojiDrawable;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        if ((getParent() instanceof View) == false) goto L6;
        ((View) getParent()).invalidate();
        return;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.holder.onAttachedToWindow(true);
        this.preloadSmallReaction.onAttachedToWindow();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.holder.onAttachedToWindow(false);
        this.preloadSmallReaction.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, r3);
        this.animatedTextDrawable.setTextSize(Math.min(AndroidUtilities.dp(18.0f), getMeasuredHeight() * 0.156f));
    }

    public void playAnimation() {
        this.holder.play();
    }

    @Override // android.view.View
    public void setScaleX(float r2) {
        if (getScaleX() == r2) goto L6;
        this.storyReactionWidgetBackground.updateShadowLayer(r2);
        super.setScaleX(r2);
        return;
    }

    public void setViews(TL_stories.StoryViews r8, boolean r9) {
        float r0 = 0.0f;
        if (r8 == null) goto L26;
        int r4 = 0;
    L6:
        if (r4 >= r8.reactions.size()) goto L26;
        if (ReactionsUtils.compare(r8.reactions.get(r4).reaction, this.visibleReaction) == true) goto L9;
        r4 = r4 + 1;
        goto L6
    L9:
        if (r9 == true) goto L11;
    L13:
        boolean r5 = false;
    L15:
        if (r8.reactions.get(r4).count <= 0) goto L17;
        boolean r6 = true;
    L18:
        this.hasCounter = r6;
        this.animatedTextDrawable.setText(AndroidUtilities.formatWholeNumber(r8.reactions.get(r4).count, 0), r5);
        if (r9 == true) goto L35;
        AnimatedFloat r82 = this.progressToCount;
        if (this.hasCounter == false) goto L23;
        r0 = 1.0f;
    L23:
        r82.set(r0, true);
        return;
    L35:
        return;
    L17:
        r6 = false;
        goto L18
    L11:
        if (this.hasCounter == false) goto L13;
        r5 = true;
    L26:
        this.hasCounter = false;
        invalidate();
        if (r9 == true) goto L36;
        AnimatedFloat r83 = this.progressToCount;
        if (this.hasCounter == false) goto L31;
        r0 = 1.0f;
    L31:
        r83.set(r0, true);
        return;
    }
}
