package org.telegram.ui.Components.Paint.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.ViewGroup;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Paint.Views.EntityView;
import org.telegram.ui.Components.RectOld;
import org.telegram.ui.Stories.recorder.Weather;

/* loaded from: classes7.dex */
public class WeatherView extends EntityView {
    private int currentColor;
    private int currentType;
    private boolean hasColor;
    public final LocationMarker marker;
    public Weather.State weather;

    public class TextViewSelectionView extends EntityView.SelectionView {
        private final Paint clearPaint;
        private Path path;
        final /* synthetic */ WeatherView this$0;

        public TextViewSelectionView(WeatherView r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
            Paint r12 = new Paint(1);
            this.clearPaint = r12;
            this.path = new Path();
            r12.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }

        @Override // android.view.View
        public void onDraw(Canvas r19) {
            super.onDraw(r19);
            int r8 = r19.getSaveCount();
            float r1 = getShowAlpha();
            if (r1 > 0.0f) goto L6;
            return;
        L6:
            if (r1 >= 1.0f) goto L8;
            int r6 = (int) (r1 * 255.0f);
            Canvas r12 = r19;
            r12.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), r6, 31);
        L9:
            float r3 = AndroidUtilities.dp(2.0f);
            float r10 = AndroidUtilities.dpf2(5.66f);
            float r11 = (r3 + r10) + AndroidUtilities.dp(15.0f);
            float r4 = r11 * 2.0f;
            float r32 = getMeasuredWidth() - r4;
            float r5 = getMeasuredHeight() - r4;
            RectF r42 = AndroidUtilities.rectTmp;
            float r122 = r11 + r32;
            float r13 = r11 + r5;
            r42.set(r11, r11, r122, r13);
            float r62 = AndroidUtilities.dp(12.0f);
            float r33 = Math.min(r62, r32 / 2.0f);
            float r52 = r5 / 2.0f;
            float r14 = Math.min(r62, r52);
            this.path.rewind();
            float r34 = r33 * 2.0f;
            float r63 = r11 + r34;
            float r2 = 2.0f * r14;
            float r7 = r11 + r2;
            r42.set(r11, r11, r63, r7);
            this.path.arcTo(r42, 180.0f, 90.0f);
            float r35 = r122 - r34;
            r42.set(r35, r11, r122, r7);
            this.path.arcTo(r42, 270.0f, 90.0f);
            r12.drawPath(this.path, this.paint);
            this.path.rewind();
            float r72 = r13 - r2;
            r42.set(r11, r72, r63, r13);
            this.path.arcTo(r42, 180.0f, -90.0f);
            r42.set(r35, r72, r122, r13);
            this.path.arcTo(r42, 90.0f, -90.0f);
            r12.drawPath(this.path, this.paint);
            float r9 = r11 + r52;
            r12.drawCircle(r11, r9, r10, this.dotStrokePaint);
            r12.drawCircle(r11, r9, (r10 - AndroidUtilities.dp(1.0f)) + 1.0f, this.dotPaint);
            r12.drawCircle(r122, r9, r10, this.dotStrokePaint);
            r12.drawCircle(r122, r9, (r10 - AndroidUtilities.dp(1.0f)) + 1.0f, this.dotPaint);
            r12.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
            float r36 = r11 + r14;
            float r53 = r13 - r14;
            r19.drawLine(r11, r36, r11, r53, this.paint);
            r19.drawLine(r122, r36, r122, r53, this.paint);
            r19.drawCircle(r122, r9, (AndroidUtilities.dp(1.0f) + r10) - 1.0f, this.clearPaint);
            r19.drawCircle(r11, r9, (r10 + AndroidUtilities.dp(1.0f)) - 1.0f, this.clearPaint);
            r19.restoreToCount(r8);
            return;
        L8:
            r12 = r19;
            goto L9
        }

        @Override // org.telegram.ui.Components.Paint.Views.EntityView.SelectionView
        public int pointInsideHandle(float r6, float r7) {
            float r0 = AndroidUtilities.dp(1.0f);
            float r1 = AndroidUtilities.dp(19.5f);
            float r02 = r0 + r1;
            float r4 = r02 * 2.0f;
            float r2 = getMeasuredWidth() - r4;
            float r5 = ((getMeasuredHeight() - r4) / 2.0f) + r02;
            if (r6 > (r02 - r1)) goto L5;
        L12:
            float r03 = r02 + r2;
            if (r6 > (r03 - r1)) goto L15;
            return 0;
        L15:
            if (r7 > (r5 - r1)) goto L17;
            return 0;
        L17:
            if (r6 < (r03 + r1)) goto L19;
            return 0;
        L19:
            if (r7 >= (r5 + r1)) goto L26;
            return 2;
        L26:
            return 0;
        L5:
            if (r7 <= (r5 - r1)) goto L12;
            if (r6 >= (r02 + r1)) goto L12;
            if (r7 >= (r5 + r1)) goto L12;
            return 1;
        }
    }

    public WeatherView(Context r3, PointF r4, int r5, Weather.State r6, float r7, int r8) {
        super(r3, r4);
        LocationMarker r42 = new LocationMarker(r3, 1, r7, 0);
        this.marker = r42;
        r42.setMaxWidth(r8);
        r42.setType(0, this.currentColor);
        setWeather(r5, r6);
        addView(r42, LayoutHelper.createFrame(-2, -2, 51));
        setClipChildren(false);
        setClipToPadding(false);
        updatePosition();
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public /* bridge */ /* synthetic */ EntityView.SelectionView createSelectionView() {
        return createSelectionView();
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public TextViewSelectionView createSelectionView() {
        return new TextViewSelectionView(this, getContext());
    }

    public int getColor() {
        return this.currentColor;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public float getMaxScale() {
        return 1.5f;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public RectOld getSelectionBounds() {
        ViewGroup r0 = (ViewGroup) getParent();
        if (r0 == null) goto L5;
        float r02 = r0.getScaleX();
        float r1 = (getMeasuredWidth() * getScale()) + (AndroidUtilities.dp(64.0f) / r02);
        float r3 = (getMeasuredHeight() * getScale()) + (AndroidUtilities.dp(64.0f) / r02);
        float r2 = (getPositionX() - (r1 / 2.0f)) * r02;
        return new RectOld(r2, (getPositionY() - (r3 / 2.0f)) * r02, ((r1 * r02) + r2) - r2, r3 * r02);
    L5:
        return new RectOld();
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public float getStickyPaddingBottom() {
        return this.marker.pady;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public float getStickyPaddingLeft() {
        return this.marker.padx;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public float getStickyPaddingRight() {
        return this.marker.padx;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public float getStickyPaddingTop() {
        return this.marker.pady;
    }

    public int getType() {
        return this.currentType;
    }

    public int getTypesCount() {
        return this.marker.getTypesCount() - (!this.hasColor ? 1 : 0);
    }

    public boolean hasColor() {
        return this.hasColor;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        updatePosition();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        updatePosition();
    }

    public void setColor(int r2) {
        this.hasColor = true;
        this.currentColor = r2;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public void setIsVideo(boolean r1) {
        this.marker.setIsVideo(true);
    }

    public void setMaxWidth(int r1) {
        this.marker.setMaxWidth(r1);
    }

    public void setType(int r2) {
        LocationMarker r0 = this.marker;
        this.currentType = r2;
        r0.setType(r2, this.currentColor);
    }

    public void setWeather(int r3, Weather.State r4) {
        this.weather = r4;
        String r0 = r4.getEmoji();
        String r42 = r4.getTemperature();
        this.marker.setCodeEmoji(r3, r0);
        this.marker.setText(r42);
        updateSelectionView();
    }
}
