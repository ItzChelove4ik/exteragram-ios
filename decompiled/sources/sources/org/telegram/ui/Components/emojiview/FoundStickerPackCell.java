package org.telegram.ui.Components.emojiview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.StickerEmojiCell;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class FoundStickerPackCell extends FrameLayout implements FactorAnimator.Target, Theme.Colorable {
    private Drawable bgSelected;
    private final BoolAnimator isSelected;
    private final Theme.ResourcesProvider resourcesProvider;
    private final StickerEmojiCell stickerView;
    private final TextView textView;

    public FoundStickerPackCell(Context r11, Theme.ResourcesProvider r12) {
        super(r11);
        this.isSelected = new BoolAnimator(0, this, CubicBezierInterpolator.EASE_OUT_QUINT, 380);
        this.resourcesProvider = r12;
        StickerEmojiCell r10 = new StickerEmojiCell(r11, false, r12);
        this.stickerView = r10;
        addView(r10, LayoutHelper.createFrame(45, 45.0f, 49, 0.0f, 8.0f, 0.0f, 0.0f));
        TextView r102 = new TextView(r11);
        this.textView = r102;
        r102.setTextSize(1, 10.0f);
        r102.setGravity(17);
        r102.setEllipsize(TextUtils.TruncateAt.END);
        r102.setSingleLine();
        addView(r102, LayoutHelper.createFrame(-1, -2.0f, 80, 6.0f, 0.0f, 6.0f, 5.0f));
        updateColors();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r5) {
        if (this.bgSelected != null) goto L5;
    L7:
        super.dispatchDraw(r5);
        return;
    L5:
        if (this.isSelected.getFloatValue() <= 0.0f) goto L7;
        this.bgSelected.setBounds(0, 0, getWidth(), getHeight());
        DrawableUtils.drawWithScale(r5, this.bgSelected, AndroidUtilities.lerp(0.9f, 1.0f, this.isSelected.getFloatValue()));
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    @Override // android.view.View
    public boolean isSelected() {
        return this.isSelected.getValue();
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        Drawable r12 = this.bgSelected;
        if (r12 == null) goto L5;
        r12.setAlpha((int) (r2 * 255.0f));
    L5:
        invalidate();
    }

    public void setPack(TLRPC.StickerSetCovered r8, TLRPC.Document r9) {
        this.textView.setText(r8.set.short_name);
        this.stickerView.setSticker(r9, null, null, null, false);
    }

    public void setPack(TLRPC.TL_messages_stickerSet r9) {
        this.textView.setText(r9.set.short_name);
        StickerEmojiCell r2 = this.stickerView;
        if (r9.documents.isEmpty() == true) goto L6;
        TLRPC.Document r8 = r9.documents.get(0);
    L7:
        r2.setSticker(r8, null, null, null, false);
        return;
    L6:
        r8 = null;
        goto L7
    }

    public void setSelected(boolean r4, boolean r5) {
        if (r4 == false) goto L7;
        if (this.bgSelected != null) goto L7;
        this.bgSelected = Theme.createRoundRectDrawable(AndroidUtilities.dp(10.0f), ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_glass_defaultIcon, this.resourcesProvider), 25));
    L7:
        if (this.isSelected.getValue() != r4) goto L10;
        if (r5 == true) goto L10;
        return;
    L10:
        this.isSelected.setValue(r4, r5);
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        if (this.bgSelected == null) goto L5;
        ShapeDrawable r0 = Theme.createRoundRectDrawable(AndroidUtilities.dp(10.0f), ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_glass_defaultIcon, this.resourcesProvider), 25));
        this.bgSelected = r0;
        r0.setAlpha((int) (this.isSelected.getFloatValue() * 255.0f));
    L5:
        this.textView.setTextColor(ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_glass_defaultIcon, this.resourcesProvider), TLRPC.LAYER));
    }
}
