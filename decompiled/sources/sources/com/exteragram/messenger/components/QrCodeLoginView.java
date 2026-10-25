package com.exteragram.messenger.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.HashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.TelegramQRCodeWriter;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.SlideView;

/* loaded from: classes4.dex */
public abstract class QrCodeLoginView extends SlideView {
    private final QrRenderView qrRenderView;
    private final TextView subtitleView;
    private final TextView titleView;

    public static class QrRenderView extends View {
        private final Path clipPath;
        private Bitmap contentBitmap;
        private final AnimatedFloat contentBitmapAlpha;
        private final Paint crossfadeFromPaint;
        private final Paint crossfadeToPaint;
        private final int crossfadeWidthDp;
        private boolean firstPrepare;
        private Integer hadHeight;
        private String hadLink;
        private Integer hadWidth;
        private String link;
        private RLottieDrawable loadingMatrix;
        private boolean loadingVisible;
        private Bitmap oldContentBitmap;
        private final Paint paint;
        private final Path qrAreaClipPath;
        private Bitmap qrLogo;
        private int qrLogoSize;
        private int transitionDirection;

        public static /* synthetic */ void $r8$lambda$F5YHgfTkh_FddpiWcVmxxkJEtTs(QrRenderView r0, String r1) {
            r0.lambda$prepareContent$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$OtD0Br4h_dfjAMOj5URxAWtSgsI(QrRenderView r0, int r1, int r2, String r3) {
            r0.lambda$setData$0(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$cj53kdjRHBRut8U4vorr9Nn5q0k(QrRenderView r0, String r1, Bitmap r2, int r3, int r4) {
            r0.lambda$prepareContent$3(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$ztnz0V2CxDH1CrJQ2g6fBBc8l6c(QrRenderView r0, Bitmap r1) {
            r0.lambda$prepareContent$2(r1);
        }

        public QrRenderView(Context r24) {
            super(r24);
            this.crossfadeWidthDp = 140;
            this.paint = new Paint(1);
            this.contentBitmapAlpha = new AnimatedFloat(1.0f, this, 0, 2000, CubicBezierInterpolator.EASE_OUT_QUINT);
            Paint r0 = new Paint(1);
            this.crossfadeFromPaint = r0;
            Paint r1 = new Paint(1);
            this.crossfadeToPaint = r1;
            this.clipPath = new Path();
            this.qrAreaClipPath = new Path();
            this.firstPrepare = true;
            this.loadingVisible = true;
            this.transitionDirection = 0;
            Shader.TileMode r22 = Shader.TileMode.CLAMP;
            r0.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(140.0f), new int[]{-1, 0}, new float[]{0.0f, 1.0f}, r22));
            PorterDuff.Mode r5 = PorterDuff.Mode.DST_OUT;
            r0.setXfermode(new PorterDuffXfermode(r5));
            r1.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(140.0f), new int[]{0, -1}, new float[]{0.0f, 1.0f}, r22));
            r1.setXfermode(new PorterDuffXfermode(r5));
            clear();
        }

        private void drawFinderPatterns(Canvas r28, int r29, int r30) {
            float r2 = r30;
            float r3 = 2.0f * r2;
            float r4 = 7.0f * r2;
            float r12 = (r4 / 3.0f) * 0.75f;
            float r18 = (r4 / 4.0f) * 0.75f;
            float r24 = ((5.0f * r2) / 4.0f) * 0.75f;
            int r5 = 0;
        L4:
            if (r5 >= 3) goto L13;
            if (r5 != 0) goto L9;
            float r8 = 16.0f;
            float r9 = 16.0f;
        L12:
            this.paint.setColor(-16777216);
            float r10 = r8 + r4;
            float r11 = r9 + r4;
            r28.drawRoundRect(r8, r9, r10, r11, r12, r12, this.paint);
            this.paint.setColor(-1);
            float r7 = 6.0f * r2;
            r28.drawRoundRect(r8 + r2, r9 + r2, r8 + r7, r9 + r7, r18, r18, this.paint);
            this.paint.setColor(-16777216);
            r28.drawRoundRect(r8 + r3, r9 + r3, r10 - r3, r11 - r3, r24, r24, this.paint);
            r5 = r5 + 1;
            goto L4
        L9:
            if (r5 != 1) goto L11;
            r9 = 16.0f;
            r8 = (r29 - r4) - 16.0f;
            goto L12
        L11:
            r8 = 16.0f;
            r9 = (r29 - r4) - 16.0f;
            goto L12
        }

        private void drawLoading(Canvas r12, int r13, int r14, float r15) {
            RLottieDrawable r5 = this.loadingMatrix;
            if (r5 != null) goto L6;
            RLottieDrawable r52 = new RLottieDrawable(R.raw.qr_matrix, AndroidUtilities.dp(200.0f), AndroidUtilities.dp(200.0f));
            this.loadingMatrix = r52;
            r52.setMasterParent(this);
            this.loadingMatrix.setAutoRepeat(1);
            this.loadingMatrix.setColorFilter(-16777216, PorterDuff.Mode.MULTIPLY);
            this.loadingMatrix.start();
        L8:
            int r53 = getWidth();
            int r7 = Math.round(16.0f * r15);
            int r54 = r53 - r7;
            this.loadingMatrix.setBounds(r7, r7, r54, r54);
            this.loadingMatrix.setAlpha(255);
            this.loadingMatrix.draw(r12);
            float r72 = r13;
            int r55 = Math.round(((r14 - 32) / 4.65f) / r72);
            if ((r55 % 2) == 1) goto L11;
            r55 = r55 + 1;
        L11:
            int r56 = r55 * r13;
            int r9 = r56 - 24;
            int r6 = (r14 - r56) / 2;
            int r10 = (r14 - r9) / 2;
            r12.save();
            r12.scale(r15, r15);
            drawFinderPatterns(r12, r14, r13);
            this.paint.setColor(-1);
            float r73 = ((r72 * 7.0f) / 4.0f) * 0.75f;
            float r2 = r6;
            float r4 = r6 + r56;
            r12.drawRoundRect(r2, r2, r4, r4, r73, r73, this.paint);
            Bitmap r22 = this.qrLogo;
            if (r22 != null) goto L15;
            this.qrLogo = SvgHelper.getBitmap(AndroidUtilities.readRes(null, R.raw.qr_logo), r9, r9, false);
            this.qrLogoSize = r9;
        L17:
            Bitmap r0 = this.qrLogo;
            if (r0 == null) goto L20;
            float r23 = r10;
            r12.drawBitmap(r0, r23, r23, null);
        L20:
            r12.restore();
            return;
        L15:
            if (this.qrLogoSize == r9) goto L17;
            r22.recycle();
            this.qrLogo = SvgHelper.getBitmap(AndroidUtilities.readRes(null, R.raw.qr_logo), r9, r9, false);
            this.qrLogoSize = r9;
            goto L17
        L6:
            if (r5.isRunning() == true) goto L8;
            this.loadingMatrix.start();
            goto L8
        }

        private Paint getNewLayerMaskPaint() {
            if (this.transitionDirection != 1) goto L7;
            return this.crossfadeToPaint;
        L7:
            return this.crossfadeFromPaint;
        }

        private Paint getOldLayerMaskPaint() {
            if (this.transitionDirection != 1) goto L7;
            return this.crossfadeFromPaint;
        L7:
            return this.crossfadeToPaint;
        }

        private float getScanLineY(float r2, int r3, float r4) {
            if (this.transitionDirection != 1) goto L7;
            float r1 = -r4;
            float r32 = (r3 + r4) * r2;
        L6:
            return r1 + r32;
        L7:
            r1 = -r4;
            r32 = (r3 + r4) * (1.0f - r2);
            goto L6
        }

        private /* synthetic */ void lambda$prepareContent$1(String r4) {
            if (TextUtils.equals(this.link, r4) == false) goto L13;
            this.firstPrepare = false;
            Bitmap r42 = this.contentBitmap;
            if (r42 == null) goto L12;
            this.contentBitmap = null;
            this.contentBitmapAlpha.set(0.0f, true);
            Bitmap r0 = this.oldContentBitmap;
            if (r0 == null) goto L10;
            r0.recycle();
        L10:
            this.oldContentBitmap = r42;
            invalidate();
            return;
        L12:
            return;
        }

        private /* synthetic */ void lambda$prepareContent$2(Bitmap r6) {
            boolean r0 = this.firstPrepare;
            if (r0 == true) goto L5;
        L7:
            boolean r3 = false;
        L8:
            Bitmap r4 = this.contentBitmap;
            this.contentBitmap = r6;
            if (r0 == false) goto L11;
            if (r3 == true) goto L11;
        L12:
            this.firstPrepare = false;
            Bitmap r62 = this.oldContentBitmap;
            if (r62 == null) goto L15;
            r62.recycle();
        L15:
            this.oldContentBitmap = r4;
            this.loadingVisible = r3;
            invalidate();
            return;
        L11:
            this.transitionDirection = 0;
            this.contentBitmapAlpha.set(0.0f, true);
            goto L12
        L5:
            if (this.loadingVisible == false) goto L7;
            r3 = true;
            goto L8
        }

        private /* synthetic */ void lambda$prepareContent$3(String r2, Bitmap r3, int r4, int r5) {
            if (TextUtils.equals(this.link, r2) == true) goto L9;
            if (r3 != null) goto L6;
            return;
        L6:
            if (r3.isRecycled() == true) goto L12;
            r3.recycle();
            return;
        L12:
            return;
        L9:
            this.hadWidth = Integer.valueOf(r4);
            this.hadHeight = Integer.valueOf(r5);
            this.hadLink = r2;
            new QrCodeLoginView$QrRenderView$$ExternalSyntheticLambda3(this, r3).run();
        }

        private /* synthetic */ void lambda$setData$0(int r1, int r2, String r3) {
            prepareContent(r1, r2, r3);
        }

        private void prepareContent(int r11, int r12, String r13) {
            if (r11 == 0) goto L31;
            if (r12 != 0) goto L6;
            return;
        L6:
            if (TextUtils.isEmpty(r13) == false) goto L10;
            AndroidUtilities.runOnUIThread(new QrCodeLoginView$QrRenderView$$ExternalSyntheticLambda1(this, r13));
            return;
        L10:
            if (TextUtils.equals(r13, this.hadLink) == false) goto L20;
            Integer r0 = this.hadWidth;
            if (r0 == null) goto L20;
            if (this.hadHeight == null) goto L20;
            if (r0.intValue() != r11) goto L20;
            if (this.hadHeight.intValue() != r12) goto L20;
            return;
        L20:
            HashMap r5 = new HashMap();
            r5.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            r5.put(EncodeHintType.MARGIN, 0);
            int r3 = (Math.max(1, r11 / 37) * 37) + 32;
            Bitmap r132 = new TelegramQRCodeWriter().encode(r13, r3, r3, r5, null, 0.75f, 0, -16777216);     // Catch: Exception -> L23
        L22:
            Bitmap r32 = r132;
            if (r32 == null) goto L34;
            AndroidUtilities.runOnUIThread(new QrCodeLoginView$QrRenderView$$ExternalSyntheticLambda2(this, r13, r32, r11, r12));
            return;
        L34:
            return;
        L23:
            e = move-exception;
            FileLog.e(e);
            r132 = null;
            goto L22
        }

        public void clear() {
            clear(true);
        }

        public void clear(boolean r5) {
            this.link = null;
            this.hadLink = null;
            this.hadWidth = null;
            this.hadHeight = null;
            this.firstPrepare = true;
            Bitmap r2 = this.oldContentBitmap;
            if (r2 == null) goto L5;
            r2.recycle();
            this.oldContentBitmap = null;
        L5:
            Bitmap r22 = this.contentBitmap;
            if (r22 == null) goto L9;
            if (r5 == false) goto L9;
            this.oldContentBitmap = r22;
            this.contentBitmap = null;
            this.loadingVisible = true;
            this.transitionDirection = 1;
            this.contentBitmapAlpha.set(0.0f, true);
            this.contentBitmapAlpha.set(1.0f);
        L12:
            if (r5 == true) goto L18;
            RLottieDrawable r52 = this.loadingMatrix;
            if (r52 == null) goto L18;
            if (r52.isRunning() == false) goto L18;
            this.loadingMatrix.stop();
        L18:
            invalidate();
            return;
        L9:
            if (r22 == null) goto L11;
            r22.recycle();
            this.contentBitmap = null;
        L11:
            this.loadingVisible = r5;
            this.contentBitmapAlpha.set(1.0f, true);
            goto L12
        }

        public void dispose() {
            RLottieDrawable r0 = this.loadingMatrix;
            if (r0 == null) goto L5;
            r0.stop();
            this.loadingMatrix.recycle(false);
            this.loadingMatrix = null;
        L5:
            Bitmap r02 = this.qrLogo;
            if (r02 == null) goto L8;
            r02.recycle();
            this.qrLogo = null;
            this.qrLogoSize = 0;
        L8:
            Bitmap r03 = this.contentBitmap;
            if (r03 == null) goto L11;
            r03.recycle();
            this.contentBitmap = null;
        L11:
            Bitmap r04 = this.oldContentBitmap;
            if (r04 == null) goto L15;
            r04.recycle();
            this.oldContentBitmap = null;
            return;
        }

        @Override // android.view.View
        public void onDraw(Canvas r25) {
            super.onDraw(r25);
            this.paint.setColor(-1);
            r25.drawRoundRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), this.paint);
            this.clipPath.reset();
            Path r10 = this.clipPath;
            float r13 = getMeasuredWidth();
            float r14 = getMeasuredHeight();
            float r15 = AndroidUtilities.dp(14.0f);
            float r2 = AndroidUtilities.dp(14.0f);
            Path.Direction r17 = Path.Direction.CW;
            r10.addRoundRect(0.0f, 0.0f, r13, r14, r15, r2, r17);
            r25.save();
            r25.clipPath(this.clipPath);
            int r7 = getWidth();
            int r8 = Math.max(1, r7 / 37);
            int r9 = (r8 * 37) + 32;
            float r4 = r7;
            float r102 = r4 / r9;
            float r21 = (((r8 * 7.0f) / 4.0f) * 0.75f) * r102;
            this.qrAreaClipPath.reset();
            this.qrAreaClipPath.addRoundRect(0.0f, 0.0f, r4, r4, r21, r21, r17);
            r25.save();
            r25.clipPath(this.qrAreaClipPath);
            float r12 = this.contentBitmapAlpha.set(1.0f);
            float r132 = 0.0f;
            if (r12 > 0.0f) goto L5;
        L7:
            boolean r16 = false;
        L9:
            if (this.transitionDirection != 1) goto L11;
            boolean r172 = true;
        L13:
            if (r12 >= 1.0f) goto L28;
            if (r16 == false) goto L17;
            RectF r22 = AndroidUtilities.rectTmp;
            r22.set(0.0f, 0.0f, r4, r4);
            r25.saveLayerAlpha(r22, 255, 31);
        L17:
            if (this.oldContentBitmap == null) goto L20;
            r25.save();
            r25.scale(r102, r102);
            r25.drawBitmap(this.oldContentBitmap, 0.0f, 0.0f, null);
            r25.restore();
        L22:
            if (r16 == false) goto L28;
            float r23 = AndroidUtilities.dp(140.0f);
            r25.save();
            r25.translate(0.0f, getScanLineY(r12, r7, r23));
            if (r172 == false) goto L26;
            float r3 = (-r23) - r4;
        L27:
            float r222 = 1.0f;
            int r152 = 255;
            int r11 = 31;
            r25.drawRect(0.0f, r3, r4, r23 + r4, getOldLayerMaskPaint());
            r25.restore();
            r25.restore();
        L29:
            if (r12 <= 0.0f) goto L44;
            if (r16 == false) goto L33;
            RectF r24 = AndroidUtilities.rectTmp;
            r24.set(0.0f, 0.0f, r4, r4);
            r25.saveLayerAlpha(r24, r152, r11);
        L33:
            if (this.contentBitmap == null) goto L35;
            r25.save();
            r25.scale(r102, r102);
            Bitmap r112 = null;
            r25.drawBitmap(this.contentBitmap, 0.0f, 0.0f, null);
            r25.restore();
        L38:
            if (r16 == false) goto L45;
            float r26 = AndroidUtilities.dp(140.0f);
            r25.save();
            r25.translate(0.0f, getScanLineY(r12, r7, r26));
            if (r172 == true) goto L43;
            r132 = (-r26) - r4;
        L43:
            r25.drawRect(0.0f, r132, r4, r4 + r26, getNewLayerMaskPaint());
            r25.restore();
            r25.restore();
        L45:
            r25.restore();
            if (this.loadingVisible == false) goto L55;
            if (this.contentBitmap == null) goto L55;
            if (this.contentBitmapAlpha.isInProgress() == true) goto L55;
            if (r12 < r222) goto L55;
            this.loadingVisible = false;
            invalidate();
        L55:
            if (this.oldContentBitmap == null) goto L64;
            if (this.contentBitmap != null) goto L64;
            if (this.contentBitmapAlpha.isInProgress() == true) goto L64;
            if (r12 < r222) goto L64;
            this.oldContentBitmap.recycle();
            this.oldContentBitmap = r112;
            invalidate();
        L64:
            if (this.loadingVisible == true) goto L70;
            RLottieDrawable r1 = this.loadingMatrix;
            if (r1 == null) goto L70;
            if (r1.isRunning() == false) goto L70;
            this.loadingMatrix.stop();
        L70:
            r25.restore();
            return;
        L35:
            r112 = null;
            if (this.loadingVisible == false) goto L38;
            drawLoading(r25, r8, r9, r102);
            goto L38
        L44:
            r112 = null;
            goto L45
        L26:
            r3 = 0.0f;
            goto L27
        L20:
            if (this.loadingVisible == false) goto L22;
            drawLoading(r25, r8, r9, r102);
        L28:
            r152 = 255;
            r222 = 1.0f;
            r11 = 31;
            goto L29
        L11:
            r172 = false;
            goto L13
        L5:
            if (r12 >= 1.0f) goto L7;
            r16 = true;
            goto L9
        }

        public void setData(String r5) {
            this.link = r5;
            if (TextUtils.isEmpty(r5) == false) goto L5;
        L7:
            int r0 = getWidth();
            int r1 = getHeight();
            Utilities.themeQueue.postRunnable(new QrCodeLoginView$QrRenderView$$ExternalSyntheticLambda0(this, r0, r1, r5));
            invalidate();
            return;
        L5:
            if (this.contentBitmap != null) goto L7;
            this.loadingVisible = true;
            goto L7
        }

        public void updateColors() {
            invalidate();
        }
    }

    public QrCodeLoginView(Context r13) {
        super(r13);
        setOrientation(1);
        setGravity(17);
        TextView r2 = new TextView(r13);
        this.titleView = r2;
        r2.setTextSize(1, 18.0f);
        r2.setTypeface(AndroidUtilities.bold());
        r2.setText(LocaleController.getString(R.string.LoginQrTitle));
        r2.setGravity(17);
        r2.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        addView(r2, LayoutHelper.createLinear(-1, -2, 1, 32, 16, 32, 0));
        TextView r22 = new TextView(r13);
        this.subtitleView = r22;
        r22.setTextSize(1, 14.0f);
        r22.setGravity(1);
        r22.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        r22.setText(LocaleController.getString(R.string.LoginQrSubtitle));
        addView(r22, LayoutHelper.createLinear(-2, -2, 1, 12, 8, 12, 0));
        QrRenderView r0 = new QrRenderView(r13);
        this.qrRenderView = r0;
        addView(r0, LayoutHelper.createLinear(280, 280, 1, 30, 30, 30, 30));
    }

    public void clear() {
        this.qrRenderView.clear();
    }

    public void clear(boolean r1) {
        this.qrRenderView.clear(r1);
    }

    @Override // org.telegram.ui.Components.SlideView
    public String getHeaderName() {
        return LocaleController.getString(R.string.LoginQrTitle);
    }

    @Override // org.telegram.ui.Components.SlideView
    public boolean needBackButton() {
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.qrRenderView.dispose();
    }

    public void setData(String r1) {
        this.qrRenderView.setData(r1);
    }

    @Override // org.telegram.ui.Components.SlideView
    public void updateColors() {
        this.titleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.subtitleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
        this.qrRenderView.updateColors();
    }
}
