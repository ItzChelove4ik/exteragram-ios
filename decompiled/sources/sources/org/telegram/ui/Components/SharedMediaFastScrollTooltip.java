package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import java.util.Random;
import okhttp3.internal.ws.WebSocketProtocol;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class SharedMediaFastScrollTooltip extends FrameLayout {

    public class TooltipDrawableView extends View {
        Paint fadePaint;
        Paint fadePaintBack;
        float fromProgress;
        Paint paint;
        Paint paint2;
        float progress;
        Random random;
        final /* synthetic */ SharedMediaFastScrollTooltip this$0;
        float toProgress;

        public TooltipDrawableView(SharedMediaFastScrollTooltip r22, Context r23) {
            this.this$0 = r22;
            super(r23);
            this.random = new Random();
            this.paint = new Paint(1);
            this.paint2 = new Paint(1);
            this.progress = 1.0f;
            this.fromProgress = 0.0f;
            Paint r1 = this.paint;
            int r2 = Theme.key_chat_gifSaveHintText;
            r1.setColor(ColorUtils.setAlphaComponent(Theme.getColor(r2), 76));
            this.paint2.setColor(Theme.getColor(r2));
            this.fadePaint = new Paint();
            Shader.TileMode r20 = Shader.TileMode.CLAMP;
            this.fadePaint.setShader(new LinearGradient(0.0f, AndroidUtilities.dp(4.0f), 0.0f, 0.0f, new int[]{0, -1}, new float[]{0.0f, 1.0f}, r20));
            Paint r24 = this.fadePaint;
            PorterDuff.Mode r4 = PorterDuff.Mode.DST_OUT;
            r24.setXfermode(new PorterDuffXfermode(r4));
            this.fadePaintBack = new Paint();
            this.fadePaintBack.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(4.0f), new int[]{0, -1}, new float[]{0.0f, 1.0f}, r20));
            this.fadePaintBack.setXfermode(new PorterDuffXfermode(r4));
        }

        @Override // android.view.View
        public void onDraw(Canvas r17) {
            super.onDraw(r17);
            r17.saveLayerAlpha(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), 255, 31);
            int r8 = (getMeasuredWidth() / 2) - AndroidUtilities.dp(3.0f);
            int r3 = 7;
            int r2 = ((AndroidUtilities.dp(1.0f) + r8) * 7) + AndroidUtilities.dp(1.0f);
            CubicBezierInterpolator r4 = CubicBezierInterpolator.EASE_OUT;
            float r5 = this.progress;
            if (r5 <= 0.4f) goto L5;
            float r52 = (r5 - 0.4f) / 0.6f;
        L6:
            float r42 = r4.getInterpolation(r52);
            float r10 = (this.fromProgress * (1.0f - r42)) + (this.toProgress * r42);
            r17.save();
            r17.translate(0.0f, (-(r2 - (getMeasuredHeight() - AndroidUtilities.dp(4.0f)))) * r10);
            int r22 = 0;
        L7:
            if (r22 >= r3) goto L9;
            int r43 = AndroidUtilities.dp(3.0f) + ((AndroidUtilities.dp(1.0f) + r8) * r22);
            RectF r53 = AndroidUtilities.rectTmp;
            float r6 = r43;
            float r44 = r43 + r8;
            r53.set(0.0f, r6, r8, r44);
            r17.drawRoundRect(r53, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), this.paint);
            r53.set(AndroidUtilities.dp(1.0f) + r8, r6, (AndroidUtilities.dp(1.0f) + r8) + r8, r44);
            r17.drawRoundRect(r53, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), this.paint);
            r22 = r22 + 1;
            r3 = 7;
            goto L7
        L9:
            r17.restore();
            r17.drawRect(0.0f, 0.0f, getMeasuredWidth(), AndroidUtilities.dp(4.0f), this.fadePaint);
            r17.translate(0.0f, getMeasuredHeight() - AndroidUtilities.dp(4.0f));
            r17.drawRect(0.0f, 0.0f, getMeasuredWidth(), AndroidUtilities.dp(4.0f), this.fadePaintBack);
            r17.restore();
            float r23 = AndroidUtilities.dp(3.0f) + ((getMeasuredHeight() - AndroidUtilities.dp(21.0f)) * r10);
            RectF r32 = AndroidUtilities.rectTmp;
            r32.set(getMeasuredWidth() - AndroidUtilities.dp(3.0f), r23, getMeasuredWidth(), AndroidUtilities.dp(15.0f) + r23);
            r17.drawRoundRect(r32, AndroidUtilities.dp(1.5f), AndroidUtilities.dp(1.5f), this.paint2);
            float r24 = r32.centerY();
            float r45 = r8 + AndroidUtilities.dp(0.5f);
            r32.set(r45 - AndroidUtilities.dp(8.0f), r24 - AndroidUtilities.dp(3.0f), r45 + AndroidUtilities.dp(8.0f), r24 + AndroidUtilities.dp(3.0f));
            r17.drawRoundRect(r32, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), this.paint2);
            float r1 = this.progress + 0.016f;
            this.progress = r1;
            if (r1 <= 1.0f) goto L16;
            this.fromProgress = this.toProgress;
            float r12 = Math.abs(this.random.nextInt() % WebSocketProtocol.CLOSE_CLIENT_GOING_AWAY) / 1000.0f;
            this.toProgress = r12;
            if (r12 <= this.fromProgress) goto L14;
            this.toProgress = r12 + 0.3f;
        L15:
            this.toProgress = Math.max(0.0f, Math.min(1.0f, this.toProgress));
            this.progress = 0.0f;
            goto L16
        L14:
            this.toProgress = r12 - 0.3f;
        L16:
            invalidate();
            return;
        L5:
            r52 = 0.0f;
            goto L6
        }
    }

    public SharedMediaFastScrollTooltip(Context r10) {
        super(r10);
        TextView r0 = new TextView(r10);
        r0.setText(LocaleController.getString(R.string.SharedMediaFastScrollHint));
        r0.setTextSize(1, 14.0f);
        r0.setMaxLines(3);
        r0.setTextColor(Theme.getColor(Theme.key_chat_gifSaveHintText));
        setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(6.0f), Theme.getColor(Theme.key_chat_gifSaveHintBackground)));
        addView(r0, LayoutHelper.createFrame(-2, -2.0f, 16, 46.0f, 8.0f, 8.0f, 8.0f));
        addView(new TooltipDrawableView(this, r10), LayoutHelper.createFrame(29, 32.0f, 0, 8.0f, 8.0f, 8.0f, 8.0f));
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(300.0f), View.MeasureSpec.getSize(r3) - AndroidUtilities.dp(32.0f)), Integer.MIN_VALUE), r4);
    }
}
