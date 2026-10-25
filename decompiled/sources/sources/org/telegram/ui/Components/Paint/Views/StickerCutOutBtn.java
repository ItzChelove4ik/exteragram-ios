package org.telegram.ui.Components.Paint.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.text.SpannableStringBuilder;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public abstract class StickerCutOutBtn extends ButtonWithCounterView {
    protected final BlurringShader.StoryBlurDrawer blurDrawer;
    protected final RectF bounds;
    public int rad;
    private final Theme.ResourcesProvider resourcesProvider;
    private int state;
    private final StickerMakerView stickerMakerView;
    private boolean wrapContent;

    public StickerCutOutBtn(StickerMakerView r2, Context r3, Theme.ResourcesProvider r4, BlurringShader.BlurManager r5) {
        super(r3, false, r4);
        this.bounds = new RectF();
        this.rad = 8;
        this.resourcesProvider = r4;
        this.stickerMakerView = r2;
        this.blurDrawer = new BlurringShader.StoryBlurDrawer(r5, this, 0, true);
        setWillNotDraw(false);
        setTextColor(-1);
        setFlickeringLoading(true);
        this.text.setTypeface(AndroidUtilities.bold());
        disableRippleView();
        setForeground(Theme.createRadSelectorDrawable(Theme.multAlpha(-1, 0.08f), 8, 8));
        setPadding(AndroidUtilities.dp(24.0f), 0, AndroidUtilities.dp(24.0f), 0);
    }

    public void clean() {
        setCutOutState(false);
    }

    public void invalidateBlur() {
        invalidate();
    }

    public boolean isCancelState() {
        if (this.state != 2) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isCutOutState() {
        if (this.state != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isUndoCutState() {
        if (this.state != 1) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // org.telegram.ui.Stories.recorder.ButtonWithCounterView, android.view.View
    public void onDraw(Canvas r7) {
        if (this.wrapContentDynamic == false) goto L5;
        float r0 = (this.text.getCurrentWidth() + getPaddingLeft()) + getPaddingRight();
        this.bounds.set((getMeasuredWidth() - r0) / 2.0f, 0.0f, (getMeasuredWidth() + r0) / 2.0f, getMeasuredHeight());
    L6:
        super.onDraw(r7);
        return;
    L5:
        this.bounds.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        goto L6
    }

    @Override // org.telegram.ui.Stories.recorder.ButtonWithCounterView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        if (this.wrapContent == false) goto L5;
        r2 = View.MeasureSpec.makeMeasureSpec((getPaddingLeft() + ((int) this.text.getCurrentWidth())) + getPaddingRight(), TLObject.FLAG_30);
    L5:
        super.onMeasure(r2, r3);
    }

    @Override // android.view.View
    public void setAlpha(float r2) {
        if (this.stickerMakerView.hasSegmentedBitmap() == true) goto L5;
        r2 = 0.0f;
    L5:
        super.setAlpha(r2);
    }

    public void setCancelState(boolean r2) {
        this.state = 2;
        setText(LocaleController.getString(R.string.Cancel), r2);
    }

    public void setCutOutState(boolean r5) {
        this.state = 0;
        SpannableStringBuilder r1 = new SpannableStringBuilder("d");
        ColoredImageSpan r2 = new ColoredImageSpan(R.drawable.media_magic_cut);
        r2.setSize(AndroidUtilities.dp(22.0f));
        r2.setTranslateX(AndroidUtilities.dp(1.0f));
        r2.setTranslateY(AndroidUtilities.dp(2.0f));
        r2.spaceScaleX = 1.2f;
        r1.setSpan(r2, 0, 1, 0);
        r1.append(" ").append(LocaleController.getString(R.string.SegmentationCutObject));
        setText(r1, r5);
    }

    public void setEraseState(boolean r5) {
        this.state = 3;
        SpannableStringBuilder r0 = new SpannableStringBuilder("d");
        ColoredImageSpan r1 = new ColoredImageSpan(R.drawable.media_button_erase);
        r1.setSize(AndroidUtilities.dp(20.0f));
        r1.setTranslateX(AndroidUtilities.dp(-3.0f));
        r0.setSpan(r1, 0, 1, 0);
        r0.append(" ").append(LocaleController.getString(R.string.SegmentationErase));
        setText(r0, r5);
    }

    public void setOutlineState(boolean r5) {
        this.state = 6;
        SpannableStringBuilder r0 = new SpannableStringBuilder("d");
        ColoredImageSpan r1 = new ColoredImageSpan(R.drawable.media_sticker_stroke);
        r1.setSize(AndroidUtilities.dp(20.0f));
        r1.setTranslateX(AndroidUtilities.dp(-3.0f));
        r0.setSpan(r1, 0, 1, 0);
        r0.append(" ").append(LocaleController.getString(R.string.SegmentationOutline));
        setText(r0, r5);
    }

    public void setRad(int r3) {
        this.rad = r3;
        setForeground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, this.resourcesProvider), r3, r3));
    }

    public void setRestoreState(boolean r5) {
        this.state = 4;
        SpannableStringBuilder r0 = new SpannableStringBuilder("d");
        ColoredImageSpan r1 = new ColoredImageSpan(R.drawable.media_button_restore);
        r1.setSize(AndroidUtilities.dp(20.0f));
        r1.setTranslateX(AndroidUtilities.dp(-3.0f));
        r0.setSpan(r1, 0, 1, 0);
        r0.append(" ").append(LocaleController.getString(R.string.SegmentationRestore));
        setText(r0, r5);
    }

    public void setUndoCutState(boolean r1) {
        this.state = 1;
    }

    public void setUndoState(boolean r5) {
        this.state = 5;
        SpannableStringBuilder r0 = new SpannableStringBuilder("d");
        ColoredImageSpan r1 = new ColoredImageSpan(R.drawable.photo_undo2);
        r1.setSize(AndroidUtilities.dp(20.0f));
        r1.setTranslateX(AndroidUtilities.dp(-3.0f));
        r0.setSpan(r1, 0, 1, 0);
        r0.append(" ").append(LocaleController.getString(R.string.SegmentationUndo));
        setText(r0, r5);
    }

    @Override // android.view.View
    public void setVisibility(int r1) {
        super.setVisibility(r1);
    }

    public void wrapContent() {
        this.wrapContent = true;
    }
}
