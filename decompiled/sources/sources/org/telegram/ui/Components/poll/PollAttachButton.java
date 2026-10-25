package org.telegram.ui.Components.poll;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import me.vkryl.android.animator.BoolAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class PollAttachButton extends View {
    private final BoolAnimator animatorHasMedia;
    public final Drawable attachDrawable;
    private PollAttachedMedia attachedMedia;
    private final Theme.ResourcesProvider resourcesProvider;
    private final int size;

    public PollAttachButton(Context r2, Theme.ResourcesProvider r3) {
        this(r2, r3, 38);
    }

    public PollAttachButton(Context r5, Theme.ResourcesProvider r6, int r7) {
        super(r5);
        this.animatorHasMedia = new BoolAnimator(this, CubicBezierInterpolator.EASE_OUT_QUINT, 380);
        this.resourcesProvider = r6;
        this.size = r7;
        Drawable r52 = r5.getResources().getDrawable(R.drawable.outline_poll_attach_24).mutate();
        this.attachDrawable = r52;
        r52.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_pollCreateIcons), PorterDuff.Mode.SRC_IN));
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        PollAttachedMedia r0 = this.attachedMedia;
        if (r0 == null) goto L6;
        r0.attach(this);
        return;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        PollAttachedMedia r0 = this.attachedMedia;
        if (r0 == null) goto L6;
        r0.detach();
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r7) {
        super.onDraw(r7);
        float r0 = getWidth() / 2.0f;
        float r2 = getHeight() / 2.0f;
        float r3 = this.animatorHasMedia.getFloatValue();
        if (r3 >= 1.0f) goto L6;
        r7.save();
        float r4 = 1.0f - r3;
        r7.scale(r4, r4, r0, r2);
        this.attachDrawable.draw(r7);
        r7.restore();
    L6:
        if (r3 <= 0.0f) goto L12;
        int r02 = AndroidUtilities.dp(this.size);
        int r22 = (getWidth() - r02) / 2;
        int r42 = (getHeight() - r02) / 2;
        r7.save();
        r7.translate(r22, r42);
        r7.scale(r3, r3, AndroidUtilities.dp(this.size) / 2.0f, AndroidUtilities.dp(this.size) / 2.0f);
        PollAttachedMedia r03 = this.attachedMedia;
        if (r03 == null) goto L10;
        r03.draw(r7, AndroidUtilities.dp(this.size), AndroidUtilities.dp(this.size));
    L10:
        r7.restore();
        return;
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        int r32 = AndroidUtilities.dp(24.0f);
        int r12 = (r1 - r32) / 2;
        int r22 = (r2 - r32) / 2;
        this.attachDrawable.setBounds(r12, r22, r12 + r32, r32 + r22);
    }

    public void setAttachedMedia(PollAttachedMedia r3, boolean r4) {
        BoolAnimator r0 = this.animatorHasMedia;
        if (r3 == null) goto L5;
        boolean r1 = true;
    L6:
        r0.setValue(r1, r4);
        if (isAttachedToWindow() == false) goto L11;
        PollAttachedMedia r42 = this.attachedMedia;
        if (r42 == null) goto L11;
        r42.detach();
    L11:
        this.attachedMedia = r3;
        if (isAttachedToWindow() == false) goto L17;
        PollAttachedMedia r32 = this.attachedMedia;
        if (r32 == null) goto L18;
        r32.attach(this);
        return;
    L18:
        return;
    L17:
        return;
    L5:
        r1 = false;
        goto L6
    }
}
