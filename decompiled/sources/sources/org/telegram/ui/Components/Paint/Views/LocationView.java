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
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Paint.Views.EntityView;
import org.telegram.ui.Components.RectOld;

/* loaded from: classes7.dex */
public class LocationView extends EntityView {
    private int currentColor;
    private int currentType;
    private boolean hasColor;
    public TLRPC.MessageMedia location;
    public final LocationMarker marker;
    public TL_stories.MediaArea mediaArea;

    public class TextViewSelectionView extends EntityView.SelectionView {
        private final Paint clearPaint;
        private Path path;
        final /* synthetic */ LocationView this$0;

        public TextViewSelectionView(LocationView r1, Context r2) {
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

    public LocationView(Context r2, PointF r3, int r4, TLRPC.MessageMedia r5, TL_stories.MediaArea r6, float r7, int r8) {
        super(r2, r3);
        LocationMarker r32 = new LocationMarker(r2, 0, r7, 0);
        this.marker = r32;
        r32.setMaxWidth(r8);
        setLocation(r4, r5, r6);
        r32.setType(0, this.currentColor);
        addView(r32, LayoutHelper.createFrame(-2, -2, 51));
        setClipChildren(false);
        setClipToPadding(false);
        updatePosition();
    }

    private static String deg(double r10) {
        double r102 = Math.abs(r10);
        double r0 = Math.floor(r102);
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET + ((int) r0) + "°";
        double r103 = Math.floor((r102 - r0) * 60.0d);
        StringBuilder r4 = new StringBuilder();
        r4.append(r2);
        if (r103 > 0.0d) goto L5;
        String r22 = MVEL.VERSION_SUB;
    L6:
        r4.append(r22);
        if (r103 >= 10.0d) goto L9;
        String r23 = MVEL.VERSION_SUB;
    L10:
        r4.append(r23);
        r4.append((int) r103);
        r4.append("'");
        String r24 = r4.toString();
        double r104 = Math.floor(Math.floor(r103) * 60.0d);
        StringBuilder r02 = new StringBuilder();
        r02.append(r24);
        if (r104 > 0.0d) goto L13;
        String r1 = MVEL.VERSION_SUB;
    L14:
        r02.append(r1);
        if (r104 >= 10.0d) goto L17;
        r3 = MVEL.VERSION_SUB;
    L17:
        r02.append(r3);
        r02.append((int) r104);
        r02.append("\"");
        return r02.toString();
    L13:
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L14
    L9:
        r23 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L10
    L5:
        r22 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    public static String geo(double r3, double r5) {
        StringBuilder r0 = new StringBuilder();
        r0.append(deg(r3));
        if (r3 <= 0.0d) goto L5;
        String r32 = "N";
    L6:
        r0.append(r32);
        r0.append(" ");
        r0.append(deg(r5));
        if (r5 <= 0.0d) goto L9;
        String r33 = "E";
    L10:
        r0.append(r33);
        return r0.toString();
    L9:
        r33 = "W";
        goto L10
    L5:
        r32 = "S";
        goto L6
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

    public void setLocation(int r4, TLRPC.MessageMedia r5, TL_stories.MediaArea r6) {
        this.location = r5;
        this.mediaArea = r6;
        String r0 = null;
        if ((r5 instanceof TLRPC.TL_messageMediaGeo) == false) goto L6;
        TLRPC.GeoPoint r52 = r5.geo;
        String r53 = geo(r52.lat, r52._long);
    L9:
        this.marker.setCodeEmoji(r4, r0);
        this.marker.setText(r53);
        updateSelectionView();
        return;
    L6:
        if ((r5 instanceof TLRPC.TL_messageMediaVenue) == false) goto L8;
        String r62 = r5.title.toUpperCase();
        r0 = ((TLRPC.TL_messageMediaVenue) r5).emoji;
        r53 = r62;
        goto L9
    L8:
        r53 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L9
    }

    public void setMaxWidth(int r1) {
        this.marker.setMaxWidth(r1);
    }

    public void setType(int r2) {
        LocationMarker r0 = this.marker;
        this.currentType = r2;
        r0.setType(r2, this.currentColor);
    }
}
