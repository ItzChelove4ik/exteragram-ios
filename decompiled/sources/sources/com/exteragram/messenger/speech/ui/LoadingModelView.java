package com.exteragram.messenger.speech.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.math.MathUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.StickerImageView;

/* loaded from: classes4.dex */
public class LoadingModelView extends FrameLayout {
    StickerImageView imageView;
    AnimatedTextView percentsTextView;
    ProgressView progressView;
    TextView subtitle;
    TextView title;

    public static class ProgressView extends View {
        Paint in;
        Paint out;
        float progress;
        AnimatedFloat progressT;

        public ProgressView(Context r4) {
            super(r4);
            this.in = new Paint(1);
            this.out = new Paint(1);
            this.progressT = new AnimatedFloat(this, 350, CubicBezierInterpolator.EASE_OUT);
            Paint r42 = this.in;
            int r0 = Theme.key_switchTrackChecked;
            r42.setColor(Theme.getColor(r0));
            this.out.setColor(Theme.multAlpha(Theme.getColor(r0), 0.2f));
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            super.onDraw(r7);
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            r7.drawRoundRect(r0, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), this.out);
            r0.set(0.0f, 0.0f, getMeasuredWidth() * this.progressT.set(this.progress), getMeasuredHeight());
            r7.drawRoundRect(r0, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), this.in);
        }

        public void setProgress(float r1) {
            this.progress = r1;
            invalidate();
        }
    }

    public LoadingModelView(Context r11) {
        super(r11);
        StickerImageView r0 = new StickerImageView(r11, UserConfig.selectedAccount);
        this.imageView = r0;
        r0.getImageReceiver().setAutoRepeat(1);
        this.imageView.setStickerPackName("UtyaDuck");
        this.imageView.setStickerNum(16);
        addView(this.imageView, LayoutHelper.createFrame(150, 150.0f, 49, 0.0f, 16.0f, 0.0f, 0.0f));
        AnimatedTextView r3 = new AnimatedTextView(r11, false, true, true);
        this.percentsTextView = r3;
        r3.setAnimationProperties(0.35f, 0, 120, CubicBezierInterpolator.EASE_OUT);
        this.percentsTextView.setGravity(1);
        AnimatedTextView r02 = this.percentsTextView;
        int r2 = Theme.key_dialogTextBlack;
        r02.setTextColor(Theme.getColor(r2));
        this.percentsTextView.setTextSize(AndroidUtilities.dp(24.0f));
        this.percentsTextView.setTypeface(AndroidUtilities.bold());
        addView(this.percentsTextView, LayoutHelper.createFrame(-1, 32.0f, 49, 0.0f, 176.0f, 0.0f, 0.0f));
        ProgressView r03 = new ProgressView(r11);
        this.progressView = r03;
        addView(r03, LayoutHelper.createFrame(240, 5.0f, 49, 0.0f, 226.0f, 0.0f, 0.0f));
        TextView r04 = new TextView(r11);
        this.title = r04;
        r04.setGravity(1);
        this.title.setTextColor(Theme.getColor(r2));
        this.title.setTextSize(1, 16.0f);
        this.title.setTypeface(AndroidUtilities.bold());
        this.title.setText(LocaleController.getString(R.string.DownloadingModel));
        addView(this.title, LayoutHelper.createFrame(-1, -2.0f, 49, 0.0f, 261.0f, 0.0f, 0.0f));
        TextView r05 = new TextView(r11);
        this.subtitle = r05;
        r05.setGravity(1);
        this.subtitle.setTextColor(Theme.getColor(r2));
        this.subtitle.setTextSize(1, 14.0f);
        this.subtitle.setText(LocaleController.getString(R.string.DownloadingModelInfo));
        addView(this.subtitle, LayoutHelper.createFrame(240, -2.0f, 49, 0.0f, 289.0f, 0.0f, 0.0f));
        setProgress(0.0f);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(350.0f), TLObject.FLAG_30));
    }

    public void setProgress(float r4) {
        int r0 = (int) Math.ceil(MathUtils.clamp(r4, 0.0f, 1.0f) * 100.0f);
        this.percentsTextView.cancelAnimation();
        AnimatedTextView r1 = this.percentsTextView;
        if (r0 != 100) goto L5;
        String r02 = LocaleController.getString(R.string.ModelUnzipping);
    L6:
        r1.setText(r02, !LocaleController.isRTL);
        this.progressView.setProgress(r4);
        return;
    L5:
        r02 = String.format("%s%%", new Object[]{Integer.valueOf(r0)});
        goto L6
    }
}
