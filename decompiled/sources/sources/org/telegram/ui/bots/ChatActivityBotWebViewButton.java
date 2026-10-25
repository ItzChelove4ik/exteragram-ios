package org.telegram.ui.bots;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.SimpleFloatPropertyCompat;

/* loaded from: classes7.dex */
public class ChatActivityBotWebViewButton extends FrameLayout {
    public static final SimpleFloatPropertyCompat<ChatActivityBotWebViewButton> PROGRESS_PROPERTY = null;
    private int backgroundColor;
    private int buttonColor;
    private BotCommandsMenuView menuButton;
    private int menuButtonWidth;
    private Path path;
    private float progress;
    private RadialProgressView progressView;
    private View rippleView;
    private TextView textView;

    public static /* synthetic */ float $r8$lambda$14NfatQ8oNO8KFcgazX_qdb2tfY(ChatActivityBotWebViewButton r0) {
        return r0.progress;
    }

    static {
        PROGRESS_PROPERTY = new SimpleFloatPropertyCompat("progress", new ChatActivityBotWebViewButton$$ExternalSyntheticLambda0(), new ChatActivityBotWebViewButton$$ExternalSyntheticLambda1()).setMultiplier(100.0f);
    }

    public ChatActivityBotWebViewButton(Context r10) {
        super(r10);
        this.path = new Path();
        this.buttonColor = Theme.getColor(Theme.key_featuredStickers_addButton);
        TextView r0 = new TextView(r10);
        this.textView = r0;
        r0.setTextSize(1, 14.0f);
        this.textView.setSingleLine();
        this.textView.setAlpha(0.0f);
        this.textView.setGravity(17);
        this.textView.setTypeface(AndroidUtilities.bold());
        addView(this.textView, LayoutHelper.createFrame(-1, -1.0f, 3, 0.0f, 0.0f, 0.0f, 0.0f));
        RadialProgressView r02 = new RadialProgressView(r10);
        this.progressView = r02;
        r02.setSize(AndroidUtilities.dp(18.0f));
        this.progressView.setAlpha(0.0f);
        this.progressView.setScaleX(0.0f);
        this.progressView.setScaleY(0.0f);
        addView(this.progressView, LayoutHelper.createFrame(28, 28.0f, 21, 0.0f, 0.0f, 12.0f, 0.0f));
        View r03 = new View(r10);
        this.rippleView = r03;
        r03.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_featuredStickers_addButtonPressed), 2));
        addView(this.rippleView, LayoutHelper.createFrame(-1, -1.0f, 3, 0.0f, 0.0f, 0.0f, 0.0f));
        setWillNotDraw(false);
    }

    @Override // android.view.View
    public void draw(Canvas r10) {
        r10.save();
        float r0 = (getHeight() - AndroidUtilities.dp(32.0f)) / 2.0f;
        float r1 = Math.max((getWidth() - this.menuButtonWidth) - AndroidUtilities.dp(4.0f), getHeight()) * this.progress;
        float r3 = AndroidUtilities.dp(16.0f) + r1;
        RectF r4 = AndroidUtilities.rectTmp;
        r4.set(AndroidUtilities.dp(14.0f) - r1, (AndroidUtilities.dp(4.0f) + r0) - r1, (AndroidUtilities.dp(6.0f) + this.menuButtonWidth) + r1, (getHeight() - AndroidUtilities.dp(12.0f)) + r1);
        this.path.rewind();
        this.path.addRoundRect(r4, r3, r3, Path.Direction.CW);
        r10.clipPath(this.path);
        r10.drawColor(this.backgroundColor);
        r10.saveLayerAlpha(r4, (int) ((1.0f - (Math.min(0.5f, this.progress) / 0.5f)) * 255.0f), 31);
        r10.translate(AndroidUtilities.dp(10.0f), r0);
        BotCommandsMenuView r02 = this.menuButton;
        if (r02 == null) goto L5;
        r02.setDrawBackgroundDrawable(false);
        this.menuButton.draw(r10);
        this.menuButton.setDrawBackgroundDrawable(true);
    L5:
        r10.restore();
        r10.translate((-AndroidUtilities.dp(8.0f)) * (1.0f - this.progress), 0.0f);
        super.draw(r10);
        r10.restore();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        int r0 = View.MeasureSpec.getSize(r4);
        if ((getParent() instanceof View) == false) goto L5;
        int r1 = ((View) getParent()).getHeight();
    L6:
        if (r1 <= 0) goto L8;
        r0 = Math.min(r0, r1);
    L8:
        super.onMeasure(r3, View.MeasureSpec.makeMeasureSpec(r0, View.MeasureSpec.getMode(r4)));
        return;
    L5:
        r1 = 0;
        goto L6
    }

    public void setBotMenuButton(BotCommandsMenuView r1) {
        this.menuButton = r1;
        invalidate();
    }

    public void setMeasuredButtonWidth(int r1) {
        this.menuButtonWidth = r1;
        invalidate();
    }

    public void setProgress(float r3) {
        this.progress = r3;
        this.backgroundColor = ColorUtils.blendARGB(Theme.getColor(Theme.key_chat_messagePanelVoiceBackground), this.buttonColor, r3);
        int r0 = 0;
    L4:
        if (r0 >= getChildCount()) goto L6;
        getChildAt(r0).setAlpha(r3);
        r0 = r0 + 1;
        goto L4
    L6:
        invalidate();
    }
}
