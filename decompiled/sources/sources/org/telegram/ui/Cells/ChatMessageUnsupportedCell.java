package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.UnsupportedBlockDrawable;

/* loaded from: classes6.dex */
public class ChatMessageUnsupportedCell extends View implements Theme.Colorable {
    private ChatMessageCell.ChatMessageCellDelegate delegate;
    private int mParentH;
    private float mViewTop;
    public final Theme.ResourcesProvider resourcesProvider;
    public final UnsupportedBlockDrawable unsupportedBlockDrawable;
    private int unsupportedBlockHeight;
    private int unsupportedBlockWidth;

    public static /* synthetic */ void $r8$lambda$tdPKWKYhqvmojz26_QJwfZjXzMA(ChatMessageUnsupportedCell r0) {
        r0.lambda$new$0();
    }

    public ChatMessageUnsupportedCell(Context r1, Theme.ResourcesProvider r2) {
        super(r1);
        this.resourcesProvider = r2;
        UnsupportedBlockDrawable r12 = new UnsupportedBlockDrawable(r2);
        this.unsupportedBlockDrawable = r12;
        r12.setCallback(this);
        r12.setTitle(LocaleController.getString(R.string.UnsupportedMessageTitle));
        r12.setSubtitle(LocaleController.getString(R.string.UnsupportedMessageMessage));
        r12.setButtonText(LocaleController.getString(R.string.UnsupportedUpdate));
        r12.setOnClickListener(new ChatMessageUnsupportedCell$$ExternalSyntheticLambda0(this));
    }

    private /* synthetic */ void lambda$new$0() {
        ChatMessageCell.ChatMessageCellDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.didPressAppUpdateButton();
        return;
    }

    public void drawBackground(Canvas r20) {
        Theme.ResourcesProvider r1 = this.resourcesProvider;
        if (r1 == null) goto L5;
        r1.applyServiceShaderMatrix(getMeasuredWidth(), this.mParentH, 0.0f, this.mViewTop);
    L6:
        r20.drawRoundRect(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(18.0f) + this.unsupportedBlockWidth, AndroidUtilities.dp(6.0f) + this.unsupportedBlockHeight, AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f), getThemedPaint("paintChatActionBackground"));
        if (hasGradientService() == false) goto L10;
        r20.drawRoundRect(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(18.0f) + this.unsupportedBlockWidth, AndroidUtilities.dp(6.0f) + this.unsupportedBlockHeight, AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f), Theme.chat_actionBackgroundGradientDarkenPaint);
        return;
    L10:
        return;
    L5:
        Theme.applyServiceShaderMatrix(getMeasuredWidth(), this.mParentH, 0.0f, this.mViewTop);
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public Paint getThemedPaint(String r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L5;
        Paint r02 = r0.getPaint(r1);
    L6:
        if (r02 == null) goto L9;
        return r02;
    L9:
        return Theme.getThemePaint(r1);
    L5:
        r02 = null;
        goto L6
    }

    public boolean hasGradientService() {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L7;
        return r0.hasGradientService();
    L7:
        return Theme.hasGradientService();
    }

    @Override // android.view.View
    public void onDraw(Canvas r7) {
        this.unsupportedBlockDrawable.setBounds(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(18.0f) + this.unsupportedBlockWidth, AndroidUtilities.dp(6.0f) + this.unsupportedBlockHeight);
        this.unsupportedBlockDrawable.draw(r7);
    }

    @Override // android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        int r22 = View.MeasureSpec.getSize(r2);
        int r32 = r22 - AndroidUtilities.dp(36.0f);
        this.unsupportedBlockWidth = r32;
        int r33 = this.unsupportedBlockDrawable.measure(r32);
        this.unsupportedBlockHeight = r33;
        setMeasuredDimension(r22, r33 + AndroidUtilities.dp(12.0f));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r2) {
        return this.unsupportedBlockDrawable.onTouchEvent(this, r2);
    }

    public void setDelegate(ChatMessageCell.ChatMessageCellDelegate r1) {
        this.delegate = r1;
    }

    public void setVisiblePart(float r1, int r2) {
        this.mViewTop = r1;
        this.mParentH = r2;
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        this.unsupportedBlockDrawable.updateColors();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (super.verifyDrawable(r2) == false) goto L5;
        return true;
    L5:
        if (r2 == this.unsupportedBlockDrawable) goto L11;
        return false;
    L11:
        return true;
    }
}
