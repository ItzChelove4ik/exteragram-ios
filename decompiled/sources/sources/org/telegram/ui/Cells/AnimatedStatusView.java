package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.Reactions.AnimatedEmojiEffect;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;

/* loaded from: classes3.dex */
public class AnimatedStatusView extends View {
    private int animationUniq;
    private ArrayList<Object> animations;
    private Integer color;
    private int effectsSize;
    private int renderedEffectsSize;
    private int stateSize;
    private float y1;
    private float y2;

    public AnimatedStatusView(Context r1, int r2, int r3) {
        super(r1);
        this.animations = new ArrayList();
        this.stateSize = r2;
        this.effectsSize = r3;
        this.renderedEffectsSize = r3;
    }

    private void detach() {
        if (this.animations.isEmpty() == true) goto L12;
        ArrayList<Object> r0 = this.animations;
        int r1 = r0.size();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L12;
        Object r3 = r0.get(r2);
        r2 = r2 + 1;
        if ((r3 instanceof ImageReceiver) == true) goto L8;
        if ((r3 instanceof AnimatedEmojiEffect) == false) goto L5;
        ((AnimatedEmojiEffect) r3).removeView(this);
        goto L5
    L8:
        ((ImageReceiver) r3).onDetachedFromWindow();
    L12:
        this.animations.clear();
    }

    public void animateChange(ReactionsLayoutInBubble.VisibleReaction r11) {
        if (r11 != null) goto L5;
        detach();
        return;
    L5:
        TLRPC.Document r1 = null;
        if (r11.emojicon == null) goto L8;
        TLRPC.TL_availableReaction r0 = MediaDataController.getInstance(UserConfig.selectedAccount).getReactionsMap().get(r11.emojicon);
    L9:
        if (r0 != null) goto L16;
        TLRPC.Document r2 = AnimatedEmojiDrawable.findDocument(UserConfig.selectedAccount, r11.documentId);
        if (r2 == null) goto L15;
        String r12 = MessageObject.findAnimatedEmojiEmoticon(r2, null);
        if (r12 == null) goto L15;
        r0 = MediaDataController.getInstance(UserConfig.selectedAccount).getReactionsMap().get(r12);
    L15:
        TLRPC.TL_availableReaction r8 = r0;
        r1 = r2;
    L18:
        if (r1 != null) goto L23;
        if (r8 == null) goto L23;
        ImageReceiver r3 = new ImageReceiver();
        r3.setParentView(this);
        int r112 = this.animationUniq;
        this.animationUniq = r112 + 1;
        r3.setUniqKeyPrefix(Integer.toString(r112));
        r3.setImage(ImageLocation.getForDocument(r8.around_animation), this.effectsSize + "_" + this.effectsSize + "_nolimit", null, "tgs", r8, 1);
        r3.setAutoRepeat(0);
        r3.onAttachedToWindow();
        this.animations.add(r3);
        invalidate();
        return;
    L23:
        if (r1 != null) goto L25;
        AnimatedEmojiDrawable r113 = AnimatedEmojiDrawable.make(2, UserConfig.selectedAccount, r11.documentId);
    L27:
        if (this.color == null) goto L29;
        r113.setColorFilter(new PorterDuffColorFilter(this.color.intValue(), PorterDuff.Mode.MULTIPLY));
    L29:
        AnimatedEmojiEffect r114 = AnimatedEmojiEffect.createFrom(r113, false, !r113.canOverrideColor());
        r114.setView(this);
        this.animations.add(r114);
        invalidate();
        return;
    L25:
        r113 = AnimatedEmojiDrawable.make(2, UserConfig.selectedAccount, r1);
        goto L27
    L16:
        r8 = r0;
        goto L18
    L8:
        r0 = null;
        goto L9
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r10) {
        int r0 = AndroidUtilities.dp(this.renderedEffectsSize);
        int r1 = AndroidUtilities.dp(this.effectsSize);
        int r2 = 0;
    L4:
        if (r2 >= this.animations.size()) goto L14;
        Object r3 = this.animations.get(r2);
        if ((r3 instanceof ImageReceiver) == false) goto L9;
        ImageReceiver r32 = (ImageReceiver) r3;
        float r5 = r1;
        r32.setImageCoords((getMeasuredWidth() - r1) / 2.0f, (getMeasuredHeight() - r1) / 2.0f, r5, r5);
        r32.draw(r10);
    L13:
        r2 = r2 + 1;
        goto L4
    L9:
        if ((r3 instanceof AnimatedEmojiEffect) == false) goto L13;
        AnimatedEmojiEffect r33 = (AnimatedEmojiEffect) r3;
        r33.setBounds((int) ((getMeasuredWidth() - r0) / 2.0f), (int) ((getMeasuredHeight() - r0) / 2.0f), (int) ((getMeasuredWidth() + r0) / 2.0f), (int) ((getMeasuredHeight() + r0) / 2.0f));
        r33.draw(r10);
        if (r33.isDone() == false) goto L13;
        r33.removeView(this);
        this.animations.remove(r33);
        goto L13
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        detach();
    }

    @Override // android.view.View
    public void onMeasure(int r4, int r5) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(Math.max(this.renderedEffectsSize, Math.max(this.stateSize, this.effectsSize))), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(Math.max(this.renderedEffectsSize, Math.max(this.stateSize, this.effectsSize))), TLObject.FLAG_30));
    }

    public void setColor(int r5) {
        this.color = Integer.valueOf(r5);
        PorterDuffColorFilter r0 = new PorterDuffColorFilter(r5, PorterDuff.Mode.MULTIPLY);
        PorterDuffColorFilter r1 = new PorterDuffColorFilter(r5, PorterDuff.Mode.SRC_IN);
        int r52 = 0;
    L4:
        if (r52 >= this.animations.size()) goto L12;
        Object r2 = this.animations.get(r52);
        if ((r2 instanceof ImageReceiver) == false) goto L9;
        ((ImageReceiver) r2).setColorFilter(r0);
    L11:
        r52 = r52 + 1;
        goto L4
    L9:
        if ((r2 instanceof AnimatedEmojiEffect) == false) goto L11;
        ((AnimatedEmojiEffect) r2).animatedEmojiDrawable.setColorFilter(r1);
        goto L11
    }

    public void translate(float r3, float r4) {
        setTranslationX(r3 - (getMeasuredWidth() / 2.0f));
        float r42 = r4 - (getMeasuredHeight() / 2.0f);
        this.y1 = r42;
        setTranslationY(r42 + this.y2);
    }

    public void translateY2(float r2) {
        float r0 = this.y1;
        this.y2 = r2;
        setTranslationY(r0 + r2);
    }
}
