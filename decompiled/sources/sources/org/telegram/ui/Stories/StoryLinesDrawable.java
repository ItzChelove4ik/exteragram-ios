package org.telegram.ui.Stories;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Stories.PeerStoriesView;

/* loaded from: classes7.dex */
public class StoryLinesDrawable {
    float bufferingProgress;
    boolean incrementBuffering;
    int lastPosition;
    private final PeerStoriesView.SharedResources sharedResources;
    private final View view;
    private final StaticLayout zoomHintLayout;
    private final float zoomHintLayoutLeft;
    private final float zoomHintLayoutWidth;
    private final TextPaint zoomHintPaint;
    private final AnimatedFloat zoomT;

    public StoryLinesDrawable(View r11, PeerStoriesView.SharedResources r12) {
        this.view = r11;
        this.sharedResources = r12;
        this.zoomT = new AnimatedFloat(r11, 0, 360, CubicBezierInterpolator.EASE_OUT_QUINT);
        TextPaint r3 = new TextPaint(1);
        this.zoomHintPaint = r3;
        r3.setTextSize(AndroidUtilities.dp(14.0f));
        r3.setColor(-1);
        float r9 = 0.0f;
        r3.setShadowLayer(AndroidUtilities.dp(3.0f), 0.0f, AndroidUtilities.dp(1.0f), 805306368);
        StaticLayout r1 = new StaticLayout(LocaleController.getString(R.string.StorySeekHelp), r3, AndroidUtilities.displaySize.x, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        this.zoomHintLayout = r1;
        if (r1.getLineCount() <= 0) goto L5;
        float r112 = r1.getLineLeft(0);
    L6:
        this.zoomHintLayoutLeft = r112;
        if (r1.getLineCount() <= 0) goto L9;
        r9 = r1.getLineWidth(0);
    L9:
        this.zoomHintLayoutWidth = r9;
        return;
    L5:
        r112 = 0.0f;
        goto L6
    }

    public void draw(Canvas r28, int r29, int r30, float r31, int r32, float r33, float r34, boolean r35, boolean r36, float r37) {
        StoryLinesDrawable r0 = this;
        int r2 = r29;
        int r4 = r32;
        if (r4 > 0) goto L6;
        return;
    L6:
        if (r35 == false) goto L9;
        if (r36 == true) goto L9;
        boolean r8 = true;
    L11:
        if (r0.lastPosition == r30) goto L13;
        r0.bufferingProgress = 0.0f;
        r0.incrementBuffering = true;
    L13:
        r0.lastPosition = r30;
        PeerStoriesView.SharedResources r9 = r0.sharedResources;
        Paint r11 = r9.barPaint;
        Paint r92 = r9.selectedBarPaint;
        if (r4 <= 100) goto L17;
        int r12 = 1;
    L20:
        float r15 = ((r2 - AndroidUtilities.dp(10.0f)) - ((r4 - 1) * r12)) / r4;
        AndroidUtilities.dp(5.0f);
        float r13 = Math.min(r15 / 2.0f, AndroidUtilities.dp(1.0f));
        float r5 = r0.zoomT.set(r36);
        if (r5 <= 0.0f) goto L24;
        float r6 = AndroidUtilities.lerp(r31, r37, r5);
        r28.save();
        r0.zoomHintPaint.setAlpha((int) (r5 * 255.0f));
        r31 = r6;
        boolean r22 = r8;
        int r23 = r12;
        r0.zoomHintPaint.setShadowLayer(AndroidUtilities.dp(3.0f), 0.0f, AndroidUtilities.dp(1.0f), Theme.multAlpha(805306368, r5));
        r28.translate(((r2 - r0.zoomHintLayoutWidth) / 2.0f) - r0.zoomHintLayoutLeft, AndroidUtilities.lerp(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(16.0f), r5));
        r0.zoomHintLayout.draw(r28);
        r28.restore();
    L23:
        float r7 = r31;
        int r62 = 0;
    L26:
        if (r62 >= r4) goto L79;
        float r82 = ((AndroidUtilities.dp(5.0f) + (-0.0f)) + (r23 * r62)) + (r62 * r15);
        if (r82 > r2) goto L33;
        float r10 = r82 + r15;
        if (r10 >= 0.0f) goto L32;
        float r312 = r7;
        float r25 = r13;
    L74:
        r62 = r62 + 1;
        r0 = this;
        r2 = r29;
        r7 = r312;
        r4 = r32;
        r13 = r25;
        goto L26
    L32:
        if (r34 <= 0.0f) goto L33;
        float r122 = AndroidUtilities.lerp(r13, AndroidUtilities.dpf2(2.0f), r5);
        if (r62 > r30) goto L59;
        if (r62 != r30) goto L59;
        RectF r24 = AndroidUtilities.rectTmp;
        float r42 = AndroidUtilities.dpf2(2.0f);
        r312 = r7;
        float r72 = AndroidUtilities.dpf2(5.0f);
        r25 = r13;
        if (r30 != r62) goto L41;
        int r132 = 1;
    L42:
        r24.set(r82, 0.0f, r10, AndroidUtilities.lerp(r42, r72, r132 * r5));
        if (r22 == false) goto L54;
        boolean r43 = r0.incrementBuffering;
        float r73 = r0.bufferingProgress;
        if (r43 == false) goto L50;
        float r74 = r73 + 0.026666667f;
        r0.bufferingProgress = r74;
        if (r74 <= 0.5f) goto L53;
        r0.incrementBuffering = false;
    L53:
        int r75 = (int) (((51.0f * r34) * r33) * r0.bufferingProgress);
    L55:
        r11.setAlpha(((int) ((r34 * 85.0f) * r33)) + r75);
        if (r5 <= 0.0f) goto L58;
        int r133 = r62 - r30;
        r24.left = Utilities.clamp(AndroidUtilities.lerp(r24.left, (r133 * r29) + AndroidUtilities.dp(5.0f), r5), r29 - AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f));
        r24.right = Utilities.clamp(AndroidUtilities.lerp(r24.right, ((r133 + 1) * r29) - AndroidUtilities.dp(5.0f), r5), r29 - AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f));
    L58:
        r28.drawRoundRect(r24, r122, r122, r11);
        float r02 = r312;
    L60:
        RectF r26 = AndroidUtilities.rectTmp;
        float r44 = AndroidUtilities.dpf2(2.0f);
        float r76 = AndroidUtilities.dpf2(5.0f);
        if (r30 != r62) goto L63;
        int r134 = 1;
    L64:
        r26.set(r82, 0.0f, r10, AndroidUtilities.lerp(r44, r76, r134 * r5));
        if (r5 <= 0.0f) goto L68;
        int r83 = r62 - r30;
        r26.left = Utilities.clamp(AndroidUtilities.lerp(r26.left, (r83 * r29) + AndroidUtilities.dp(5.0f), r5), r29 - AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f));
        r26.right = Utilities.clamp(AndroidUtilities.lerp(r26.right, ((r83 + 1) * r29) - AndroidUtilities.dp(5.0f), r5), r29 - AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f));
    L68:
        r26.right = AndroidUtilities.lerp(r26.left, r26.right, r02);
        if (r62 > r30) goto L71;
        r92.setAlpha((int) ((r34 * 255.0f) * r33));
        Paint r03 = r92;
    L72:
        r28.drawRoundRect(r26, r122, r122, r03);
        goto L74
    L71:
        r11.setAlpha((int) ((85.0f * r34) * r33));
        r03 = r11;
        goto L72
    L63:
        r134 = 0;
        goto L64
    L50:
        float r77 = r73 - 0.026666667f;
        r0.bufferingProgress = r77;
        if (r77 >= (-0.5f)) goto L53;
        r0.incrementBuffering = true;
        goto L53
    L54:
        r75 = 0;
        goto L55
    L41:
        r132 = 0;
    L59:
        r312 = r7;
        r25 = r13;
        r02 = 1.0f;
    L33:
        r312 = r7;
        r25 = r13;
        goto L74
    L79:
        return;
    L24:
        r22 = r8;
        r23 = r12;
        goto L23
    L17:
        if (r4 < 50) goto L19;
        r12 = AndroidUtilities.dp(1.0f);
        goto L20
    L19:
        r12 = AndroidUtilities.dp(2.0f);
    L9:
        r8 = false;
        goto L11
    }
}
