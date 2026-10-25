package org.telegram.ui.ActionBar;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.ExteraConfig;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.SharedConfig;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.blur3.utils.NinePatchBuilder;

/* loaded from: classes6.dex */
public class MessageDrawable extends Drawable {
    public static MotionBackgroundDrawable[] motionBackground;
    private int alpha;
    private Drawable[][] backgroundDrawable;
    private int[][] backgroundDrawableColor;
    private Rect backupRect;
    private boolean botButtonsBottom;
    private int colorAlpha;
    private Bitmap crosfadeFromBitmap;
    private Shader crosfadeFromBitmapShader;
    public MessageDrawable crossfadeFromDrawable;
    public float crossfadeProgress;
    private boolean currentAnimateGradient;
    private int[][] currentBackgroundDrawableRadius;
    private int currentBackgroundHeight;
    private int currentColor;
    private int currentGradientColor1;
    private int currentGradientColor2;
    private int currentGradientColor3;
    private int[] currentShadowDrawableRadius;
    private int currentType;
    private boolean drawFullBubble;
    private boolean forceRemoveMessageTail;
    private Shader gradientShader;
    private boolean isBottomNear;
    public boolean isCrossfadeBackground;
    private final boolean isOut;
    public boolean isSelected;
    private boolean isTopNear;
    public boolean lastDrawWithShadow;
    private Matrix matrix;
    private int overrideRoundRadius;
    private float overrideRounding;
    private Paint paint;
    private Path path;
    PathDrawParams pathDrawCacheParams;
    private RectF rect;
    private Theme.ResourcesProvider resourcesProvider;
    private Paint selectedPaint;
    private Drawable[] shadowDrawable;
    private Bitmap[] shadowDrawableBitmap;
    private int[] shadowDrawableColor;
    public boolean themePreview;
    private int topY;
    Drawable transitionDrawable;
    int transitionDrawableColor;

    public static class PathDrawParams {
        boolean lastDrawFullBottom;
        boolean lastDrawFullTop;
        int lastNearRad;
        int lastRad;
        Rect lastRect;
        boolean lastRemoveMessageTail;
        int lastSmallRad;
        Path path;

        public PathDrawParams() {
            this.path = new Path();
            this.lastRect = new Rect();
        }

        public Path getPath() {
            return this.path;
        }

        public boolean invalidatePath(Rect r4, boolean r5, boolean r6, int r7, int r8, int r9, boolean r10) {
            if (this.lastRect.isEmpty() == true) goto L28;
            Rect r0 = this.lastRect;
            if (r0.top != r4.top) goto L28;
            if (r0.bottom != r4.bottom) goto L28;
            if (r0.right != r4.right) goto L28;
            if (r0.left != r4.left) goto L28;
            if (this.lastDrawFullTop != r6) goto L28;
            if (this.lastDrawFullBottom != r5) goto L28;
            if (this.lastRad != r7) goto L28;
            if (this.lastNearRad != r8) goto L28;
            if (this.lastSmallRad != r9) goto L28;
            if (this.lastRemoveMessageTail != r10) goto L28;
            if (r6 == false) goto L28;
            if (r5 == false) goto L28;
            boolean r02 = false;
        L29:
            this.lastDrawFullTop = r6;
            this.lastDrawFullBottom = r5;
            this.lastRad = r7;
            this.lastNearRad = r8;
            this.lastSmallRad = r9;
            this.lastRemoveMessageTail = r10;
            this.lastRect.set(r4);
            return r02;
        L28:
            r02 = true;
            goto L29
        }
    }

    static {
        motionBackground = new MotionBackgroundDrawable[3];
    }

    public MessageDrawable(int r2, boolean r3, boolean r4) {
        this(r2, r3, r4, null);
    }

    public MessageDrawable(int r9, boolean r10, boolean r11, Theme.ResourcesProvider r12) {
        this.paint = new Paint(1);
        this.colorAlpha = 255;
        this.rect = new RectF();
        this.matrix = new Matrix();
        this.backupRect = new Rect();
        this.currentShadowDrawableRadius = new int[]{-1, -1, -1, -1};
        this.shadowDrawableBitmap = new Bitmap[4];
        this.shadowDrawable = new Drawable[4];
        this.shadowDrawableColor = new int[]{-1, -1, -1, -1};
        this.currentBackgroundDrawableRadius = new int[][]{new int[]{-1, -1, -1, -1}, new int[]{-1, -1, -1, -1}, new int[]{-1, -1, -1, -1}, new int[]{-1, -1, -1, -1}};
        this.backgroundDrawable = (Drawable[][]) Array.newInstance(Drawable.class, new int[]{4, 4});
        this.backgroundDrawableColor = new int[][]{new int[]{-1, -1, -1, -1}, new int[]{-1, -1, -1, -1}, new int[]{-1, -1, -1, -1}, new int[]{-1, -1, -1, -1}};
        this.resourcesProvider = r12;
        this.isOut = r10;
        this.currentType = r9;
        this.isSelected = r11;
        this.path = new Path();
        this.selectedPaint = new Paint(1);
        this.alpha = 255;
    }

    private int dp(float r2) {
        if (this.currentType != 2) goto L7;
        return (int) Math.ceil(r2 * 3.0f);
    L7:
        return AndroidUtilities.dp(r2);
    }

    private void generatePath(Path r18, Rect r19, int r20, int r21, int r22, int r23, int r24, boolean r25, boolean r26, boolean r27) {
        r18.rewind();
        int r3 = (r19.height() - r20) >> 1;
        int r5 = r21;
        if (r5 <= r3) goto L5;
        r5 = r3;
    L5:
        boolean r32 = this.isOut;
        boolean r6 = this.drawFullBubble;
        if (r32 == false) goto L71;
        if (r6 == true) goto L15;
        if (this.currentType == 2) goto L15;
        if (r27 == true) goto L15;
        if (r25 == true) goto L15;
        r18.moveTo(r19.right - dp(8.0f), (r24 - this.topY) + this.currentBackgroundHeight);
        r18.lineTo(r19.left + r20, (r24 - this.topY) + this.currentBackgroundHeight);
    L27:
        if (this.drawFullBubble == false) goto L29;
    L37:
        r18.lineTo(r19.left + r20, (r19.top + r20) + r5);
        RectF r33 = this.rect;
        int r62 = r19.left;
        int r12 = r19.top;
        int r14 = r5 * 2;
        r33.set(r62 + r20, r12 + r20, (r62 + r20) + r14, (r12 + r20) + r14);
        r18.arcTo(this.rect, 180.0f, 90.0f, false);
        if (this.isTopNear == false) goto L40;
        int r34 = r23;
    L41:
        int r63 = this.currentType;
        int r11 = r19.right;
        if (r63 != 1) goto L44;
        r18.lineTo((r11 - r20) - r34, r19.top + r20);
        RectF r64 = this.rect;
        int r112 = r19.right;
        int r35 = r34 * 2;
        int r13 = r19.top;
        r64.set((r112 - r20) - r35, r13 + r20, r112 - r20, (r13 + r20) + r35);
    L45:
        r18.arcTo(this.rect, 270.0f, 90.0f, false);
    L46:
        int r36 = this.currentType;
        if (r36 != 1) goto L57;
        if (r27 == true) goto L53;
        if (r25 == true) goto L53;
        r18.lineTo(r19.right - r20, (r24 - this.topY) + this.currentBackgroundHeight);
    L138:
        r18.close();
        return;
    L53:
        if (this.isBottomNear == false) goto L55;
        r5 = r23;
    L55:
        r18.lineTo(r19.right - r20, (r19.bottom - r20) - r5);
        RectF r37 = this.rect;
        int r4 = r19.right;
        int r52 = r5 * 2;
        int r2 = r19.bottom;
        r37.set((r4 - r20) - r52, (r2 - r20) - r52, r4 - r20, r2 - r20);
        r18.arcTo(this.rect, 0.0f, 90.0f, false);
        goto L138
    L57:
        if (this.drawFullBubble == true) goto L64;
        if (r36 == 2) goto L64;
        if (r27 == true) goto L64;
        if (r25 == true) goto L64;
        r18.lineTo(r19.right - dp(8.0f), (r24 - this.topY) + this.currentBackgroundHeight);
    L64:
        if (shouldRemoveMessageTail() == true) goto L66;
        r18.lineTo(r19.right - dp(8.0f), ((r19.bottom - r20) - r22) - dp(3.0f));
        int r65 = r22 * 2;
        this.rect.set(r19.right - dp(8.0f), ((r19.bottom - r20) - r65) - dp(9.0f), (r19.right - dp(7.0f)) + r65, (r19.bottom - r20) - dp(1.0f));
        r18.arcTo(this.rect, 180.0f, -83.0f, false);
        goto L138
    L66:
        if (this.isBottomNear == false) goto L68;
        r5 = r23;
    L68:
        r18.lineTo(r19.right - dp(8.0f), (r19.bottom - r20) - r5);
        int r53 = r5 * 2;
        this.rect.set((r19.right - dp(8.0f)) - r53, (r19.bottom - r20) - r53, r19.right - dp(8.0f), r19.bottom - r20);
        r18.arcTo(this.rect, 0.0f, 90.0f, false);
        goto L138
    L44:
        r18.lineTo((r11 - dp(8.0f)) - r34, r19.top + r20);
        int r38 = r34 * 2;
        this.rect.set((r19.right - dp(8.0f)) - r38, r19.top + r20, r19.right - dp(8.0f), (r19.top + r20) + r38);
        goto L45
    L40:
        r34 = r5;
        goto L41
    L29:
        if (this.currentType == 2) goto L37;
        if (r27 == true) goto L37;
        if (r26 == true) goto L37;
        r18.lineTo(r19.left + r20, (r24 - this.topY) - dp(2.0f));
        int r39 = this.currentType;
        int r66 = r19.right;
        if (r39 != 1) goto L36;
        r18.lineTo(r66 - r20, (r24 - this.topY) - dp(2.0f));
        goto L46
    L36:
        r18.lineTo(r66 - dp(8.0f), (r24 - this.topY) - dp(2.0f));
    L15:
        if (this.botButtonsBottom == false) goto L17;
        int r310 = r23;
    L19:
        if (this.currentType != 1) goto L21;
    L24:
        r18.moveTo((r19.right - dp(8.0f)) - r310, r19.bottom - r20);
    L25:
        r18.lineTo((r19.left + r20) + r310, r19.bottom - r20);
        RectF r67 = this.rect;
        int r122 = r19.left;
        int r113 = r19.bottom;
        int r311 = r310 * 2;
        r67.set(r122 + r20, (r113 - r20) - r311, (r122 + r20) + r311, r113 - r20);
        r18.arcTo(this.rect, 90.0f, 90.0f, false);
        goto L27
    L21:
        if (shouldRemoveMessageTail() == true) goto L24;
        r18.moveTo(r19.right - dp(2.6f), r19.bottom - r20);
        goto L25
    L17:
        r310 = r5;
        goto L19
    L71:
        if (r6 == true) goto L79;
        if (this.currentType == 2) goto L79;
        if (r27 == true) goto L79;
        if (r25 == true) goto L79;
        r18.moveTo(r19.left + dp(8.0f), (r24 - this.topY) + this.currentBackgroundHeight);
        r18.lineTo(r19.right - r20, (r24 - this.topY) + this.currentBackgroundHeight);
    L91:
        if (this.drawFullBubble == false) goto L93;
    L101:
        r18.lineTo(r19.right - r20, (r19.top + r20) + r5);
        RectF r68 = this.rect;
        int r7 = r19.right;
        int r123 = r5 * 2;
        int r132 = r19.top;
        r68.set((r7 - r20) - r123, r132 + r20, r7 - r20, (r132 + r20) + r123);
        r18.arcTo(this.rect, 0.0f, -90.0f, false);
        if (this.isTopNear == false) goto L104;
        int r69 = r23;
    L105:
        int r72 = this.currentType;
        int r114 = r19.left;
        if (r72 != 1) goto L108;
        r18.lineTo((r114 + r20) + r69, r19.top + r20);
        RectF r73 = this.rect;
        int r115 = r19.left;
        int r133 = r19.top;
        int r610 = r69 * 2;
        r73.set(r115 + r20, r133 + r20, (r115 + r20) + r610, (r133 + r20) + r610);
    L109:
        r18.arcTo(this.rect, 270.0f, -90.0f, false);
    L110:
        int r611 = this.currentType;
        if (r611 != 1) goto L123;
        if (r27 == true) goto L117;
        if (r25 == true) goto L117;
        r18.lineTo(r19.left + r20, (r24 - this.topY) + this.currentBackgroundHeight);
    L117:
        if (this.isBottomNear == false) goto L119;
    L120:
        r5 = r23;
    L121:
        r18.lineTo(r19.left + r20, (r19.bottom - r20) - r5);
        RectF r42 = this.rect;
        int r612 = r19.left;
        int r28 = r19.bottom;
        int r54 = r5 * 2;
        r42.set(r612 + r20, (r28 - r20) - r54, (r612 + r20) + r54, r28 - r20);
        r18.arcTo(this.rect, 180.0f, -90.0f, false);
        goto L138
    L119:
        if (this.botButtonsBottom == false) goto L121;
    L123:
        if (this.drawFullBubble == true) goto L130;
        if (r611 == 2) goto L130;
        if (r27 == true) goto L130;
        if (r25 == true) goto L130;
        r18.lineTo(r19.left + dp(8.0f), (r24 - this.topY) + this.currentBackgroundHeight);
    L130:
        if (shouldRemoveMessageTail() == true) goto L132;
        r18.lineTo(r19.left + dp(8.0f), ((r19.bottom - r20) - r22) - dp(3.0f));
        RectF r312 = this.rect;
        int r43 = r19.left + dp(7.0f);
        int r55 = r22 * 2;
        r312.set(r43 - r55, ((r19.bottom - r20) - r55) - dp(9.0f), r19.left + dp(8.0f), (r19.bottom - r20) - dp(1.0f));
        r18.arcTo(this.rect, 0.0f, 83.0f, false);
        goto L138
    L132:
        if (this.isBottomNear == false) goto L134;
    L135:
        r5 = r23;
    L136:
        r18.lineTo(r19.left + dp(8.0f), (r19.bottom - r20) - r5);
        int r56 = r5 * 2;
        this.rect.set(r19.left + dp(8.0f), (r19.bottom - r20) - r56, (r19.left + dp(8.0f)) + r56, r19.bottom - r20);
        r18.arcTo(this.rect, 180.0f, -90.0f, false);
        goto L138
    L134:
        if (this.botButtonsBottom == false) goto L136;
    L108:
        r18.lineTo((r114 + dp(8.0f)) + r69, r19.top + r20);
        int r613 = r69 * 2;
        this.rect.set(r19.left + dp(8.0f), r19.top + r20, (r19.left + dp(8.0f)) + r613, (r19.top + r20) + r613);
        goto L109
    L104:
        r69 = r5;
        goto L105
    L93:
        if (this.currentType == 2) goto L101;
        if (r27 == true) goto L101;
        if (r26 == true) goto L101;
        r18.lineTo(r19.right - r20, (r24 - this.topY) - dp(2.0f));
        int r614 = this.currentType;
        int r74 = r19.left;
        if (r614 != 1) goto L100;
        r18.lineTo(r74 + r20, (r24 - this.topY) - dp(2.0f));
        goto L110
    L100:
        r18.lineTo(r74 + dp(8.0f), (r24 - this.topY) - dp(2.0f));
    L79:
        if (this.botButtonsBottom == false) goto L81;
        int r615 = r23;
    L83:
        if (this.currentType != 1) goto L85;
    L88:
        r18.moveTo((r19.left + dp(8.0f)) + r615, r19.bottom - r20);
    L89:
        r18.lineTo((r19.right - r20) - r615, r19.bottom - r20);
        RectF r116 = this.rect;
        int r124 = r19.right;
        int r616 = r615 * 2;
        int r142 = r19.bottom;
        r116.set((r124 - r20) - r616, (r142 - r20) - r616, r124 - r20, r142 - r20);
        r18.arcTo(this.rect, 90.0f, -90.0f, false);
        goto L91
    L85:
        if (shouldRemoveMessageTail() == true) goto L88;
        r18.moveTo(r19.left + dp(2.6f), r19.bottom - r20);
        goto L89
    L81:
        r615 = r5;
        goto L83
    }

    private static ByteBuffer getByteBuffer(int r9, int r10, int r11, int r12, int r13) {
        return NinePatchBuilder.createNinePatchChunk(r9, r10, r11, r12, 0, 0, 0, 0, r13);
    }

    private boolean shouldRemoveMessageTail() {
        if (this.forceRemoveMessageTail == false) goto L5;
        return true;
    L5:
        if (ExteraConfig.getRemoveMessageTail() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public void applyMatrixScale() {
        if ((this.gradientShader instanceof BitmapShader) == false) goto L22;
        char r2 = 0;
        char r4 = 2;
        if (this.isCrossfadeBackground == false) goto L14;
        if (this.crosfadeFromBitmap == null) goto L14;
        if (this.currentType != 2) goto L11;
        r2 = 1;
    L11:
        float r1 = 1.0f / Math.min(r0.getWidth() / motionBackground[r2].getBounds().width(), this.crosfadeFromBitmap.getHeight() / motionBackground[r2].getBounds().height());
        this.matrix.postScale(r1, r1);
        return;
    L14:
        if (this.themePreview == false) goto L17;
    L20:
        Bitmap r0 = motionBackground[r4].getBitmap();
        float r12 = 1.0f / Math.min(r0.getWidth() / motionBackground[r4].getBounds().width(), r0.getHeight() / motionBackground[r4].getBounds().height());
        this.matrix.postScale(r12, r12);
        return;
    L17:
        if (this.currentType != 2) goto L19;
        r2 = 1;
    L19:
        r4 = r2;
        goto L20
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r4) {
        MessageDrawable r0 = this.crossfadeFromDrawable;
        if (r0 == null) goto L6;
        r0.draw(r4, this.forceRemoveMessageTail);
        setAlpha((int) (this.crossfadeProgress * 255.0f));
        draw(r4, null);
        setAlpha(255);
        return;
    L6:
        draw(r4, null);
    }

    public void draw(Canvas r17, Paint r18) {
        Rect r2 = getBounds();
        if (shouldRemoveMessageTail() == true) goto L15;
        if (r18 != null) goto L15;
        if (this.gradientShader != null) goto L15;
        if (this.overrideRoundRadius != 0) goto L15;
        if (this.overrideRounding > 0.0f) goto L15;
        Drawable r1 = getBackgroundDrawable();
        if (r1 == null) goto L15;
        r1.setBounds(r2);
        r1.draw(r17);
        return;
    L15:
        int r9 = dp(2.0f);
        int r12 = this.overrideRoundRadius;
        if (r12 == 0) goto L19;
        int r5 = r12;
        int r6 = r5;
    L26:
        int r7 = dp(6.0f);
        if (r18 != null) goto L29;
        Paint r122 = this.paint;
    L30:
        if (r18 == null) goto L32;
    L34:
        boolean r10 = false;
        int r13 = Math.max(r2.top, 0);
        if (this.pathDrawCacheParams == null) goto L37;
        r2.height();
        int r3 = this.currentBackgroundHeight;
    L37:
        PathDrawParams r14 = this.pathDrawCacheParams;
        if (r14 == null) goto L40;
        Path r15 = r14.path;
        boolean r16 = r14.invalidatePath(r2, true, true, r5, r6, r7, shouldRemoveMessageTail());
        boolean r8 = true;
        int r32 = r9;
        boolean r92 = true;
        int r4 = r5;
        int r52 = r7;
    L41:
        if (r16 == false) goto L43;
    L46:
        if (r18 == null) goto L48;
        r10 = true;
    L48:
        Path r19 = r15;
        generatePath(r19, r2, r32, r4, r52, r6, r13, r8, r92, r10);
    L49:
        if (r18 == null) goto L51;
    L53:
        r17.drawPath(r19, r122);
    L55:
        if (this.gradientShader != null) goto L57;
        return;
    L57:
        if (this.isSelected == false) goto L62;
        if (r18 != null) goto L63;
        this.selectedPaint.setColor(ColorUtils.setAlphaComponent(getColor(Theme.key_chat_outBubbleGradientSelectedOverlay), (int) ((Color.alpha(r2) * this.alpha) / 255.0f)));
        r17.drawPath(r19, this.selectedPaint);
        return;
    L63:
        return;
    L62:
        return;
    L51:
        if (this.gradientShader != null) goto L53;
        int r22 = this.paint.getAlpha();
        this.paint.setAlpha((this.colorAlpha * this.alpha) / 255);
        r17.drawPath(r19, this.paint);
        this.paint.setAlpha(r22);
        goto L55
    L43:
        if (this.overrideRoundRadius != 0) goto L46;
        r19 = r15;
        goto L49
    L40:
        r8 = true;
        r32 = r9;
        r92 = true;
        r4 = r5;
        r52 = r7;
        r15 = this.path;
        r16 = true;
        goto L41
    L32:
        if (this.gradientShader == null) goto L34;
        this.matrix.reset();
        applyMatrixScale();
        this.matrix.postTranslate(0.0f, -this.topY);
        this.gradientShader.setLocalMatrix(this.matrix);
        goto L34
    L29:
        r122 = r18;
        goto L30
    L19:
        if (this.overrideRounding <= 0.0f) goto L23;
        int r110 = AndroidUtilities.lerp(dp(SharedConfig.bubbleRadius), Math.min(r2.width(), r2.height()) / 2, this.overrideRounding);
        int r53 = AndroidUtilities.lerp(dp(Math.min(6, SharedConfig.bubbleRadius)), Math.min(r2.width(), r2.height()) / 2, this.overrideRounding);
    L21:
        r6 = r53;
        r5 = r110;
        goto L26
    L23:
        if (this.currentType != 2) goto L25;
        r110 = dp(6.0f);
        r53 = dp(6.0f);
        goto L21
    L25:
        r110 = dp(SharedConfig.bubbleRadius);
        r53 = dp(Math.min(6, SharedConfig.bubbleRadius));
        goto L21
    }

    public void draw(Canvas r1, boolean r2) {
        this.forceRemoveMessageTail = r2;
        draw(r1);     // Catch: Throwable -> L6
        this.forceRemoveMessageTail = false;
        return;
    L6:
        th = move-exception;
        this.forceRemoveMessageTail = false;
        throw th;
    }

    public void drawCached(Canvas r2, PathDrawParams r3) {
        drawCached(r2, r3, null);
    }

    public void drawCached(Canvas r2, PathDrawParams r3, Paint r4) {
        this.pathDrawCacheParams = r3;
        MessageDrawable r0 = this.crossfadeFromDrawable;
        if (r0 == null) goto L5;
        r0.pathDrawCacheParams = r3;
    L5:
        draw(r2, r4);
        this.pathDrawCacheParams = null;
        MessageDrawable r1 = this.crossfadeFromDrawable;
        if (r1 == null) goto L9;
        r1.pathDrawCacheParams = null;
        return;
    }

    public void drawCached(Canvas r1, PathDrawParams r2, Paint r3, boolean r4) {
        this.forceRemoveMessageTail = r4;
        drawCached(r1, r2, r3);     // Catch: Throwable -> L6
        this.forceRemoveMessageTail = false;
        return;
    L6:
        th = move-exception;
        this.forceRemoveMessageTail = false;
        throw th;
    }

    public void drawCached(Canvas r2, PathDrawParams r3, boolean r4) {
        drawCached(r2, r3, null, r4);
    }

    public void finalize() {
        super.finalize();
        Bitmap[] r0 = this.shadowDrawableBitmap;
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L8;
        Bitmap r3 = r0[r2];
        if (r3 == null) goto L7;
        r3.recycle();
    L7:
        r2 = r2 + 1;
        goto L3
    L8:
        Arrays.fill(this.shadowDrawableBitmap, null);
        Arrays.fill(this.shadowDrawable, null);
        Arrays.fill(this.currentShadowDrawableRadius, -1);
    }

    public Drawable getBackgroundDrawable() {
        int r0 = this.overrideRoundRadius;
        if (r0 == 0) goto L6;
    L9:
        boolean r2 = this.isTopNear;
        char r3 = 3;
        if (r2 == true) goto L12;
    L14:
        if (r2 == false) goto L17;
        char r22 = 2;
    L20:
        boolean r6 = this.isSelected;
        if (r6 == true) goto L23;
    L25:
        if (r6 == false) goto L28;
        r3 = 1;
    L31:
        boolean r7 = this.isOut;
        if (r6 == false) goto L37;
        if (r7 == false) goto L35;
        int r62 = Theme.key_chat_outBubbleSelected;
    L36:
        int r63 = getColor(r62);
    L42:
        if (this.isOut == false) goto L44;
        int r72 = Theme.key_chat_outBubbleShadow;
    L45:
        getColor(r72);
        if (this.lastDrawWithShadow == false) goto L48;
    L51:
        this.currentBackgroundDrawableRadius[r3][r22] = r0;
        Bitmap r02 = Bitmap.createBitmap(dp(50.0f), dp(40.0f), Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L55
        Canvas r73 = new Canvas(r02);     // Catch: Throwable -> L55
        this.backupRect.set(getBounds());     // Catch: Throwable -> L55
        Paint r8 = new Paint(1);     // Catch: Throwable -> L55
        r8.setColor(r63);     // Catch: Throwable -> L55
        setBounds(0, 0, r02.getWidth(), r02.getHeight());     // Catch: Throwable -> L55
        draw(r73, r8);     // Catch: Throwable -> L55
        this.backgroundDrawable[r3][r22] = new NinePatchDrawable(r02, getByteBuffer((r02.getWidth() / 2) - 1, (r02.getWidth() / 2) + 1, (r02.getHeight() / 2) - 1, (r02.getHeight() / 2) + 1, r63).array(), new Rect(), null);     // Catch: Throwable -> L55
        setBounds(this.backupRect);     // Catch: Throwable -> L55
    L53:
        this.lastDrawWithShadow = false;
        this.backgroundDrawableColor[r3][r22] = r63;
        return this.backgroundDrawable[r3][r22];
    L48:
        if (this.currentBackgroundDrawableRadius[r3][r22] != r0) goto L51;
        if (this.backgroundDrawableColor[r3][r22] == r63) goto L53;
    L44:
        r72 = Theme.key_chat_inBubbleShadow;
        goto L45
    L35:
        r62 = Theme.key_chat_inBubbleSelected;
        goto L36
    L37:
        if (r7 == false) goto L39;
        int r64 = Theme.key_chat_outBubble;
    L40:
        r63 = getColor(r64);
        goto L42
    L39:
        r64 = Theme.key_chat_inBubble;
        goto L40
    L28:
        if (this.botButtonsBottom == false) goto L30;
        r3 = 2;
        goto L31
    L30:
        r3 = 0;
        goto L31
    L23:
        if (this.botButtonsBottom == false) goto L25;
    L17:
        if (this.isBottomNear == false) goto L19;
        r22 = 1;
        goto L20
    L19:
        r22 = 0;
        goto L20
    L12:
        if (this.isBottomNear == false) goto L14;
        r22 = 3;
        goto L20
    L6:
        if (this.overrideRounding <= 0.0f) goto L8;
        r0 = 0;
        goto L9
    L8:
        r0 = dp(SharedConfig.bubbleRadius);
        goto L9
    }

    public int getColor(int r3) {
        if (this.currentType == 2) goto L5;
        Theme.ResourcesProvider r2 = this.resourcesProvider;
        if (r2 == null) goto L11;
        return r2.getColor(r3);
    L11:
        return Theme.getColor(r3);
    L5:
        return Theme.getColor(r3);
    }

    public int getCurrentColor(int r3) {
        if (this.currentType == 2) goto L5;
        Theme.ResourcesProvider r2 = this.resourcesProvider;
        if (r2 == null) goto L11;
        return r2.getCurrentColor(r3);
    L11:
        return Theme.getCurrentColor(r3);
    L5:
        return Theme.getColor(r3);
    }

    public Shader getGradientShader() {
        return this.gradientShader;
    }

    public Matrix getMatrix() {
        return this.matrix;
    }

    public MotionBackgroundDrawable getMotionBackgroundDrawable() {
        if (this.themePreview == true) goto L5;
        MotionBackgroundDrawable[] r0 = motionBackground;
        if (this.currentType != 2) goto L9;
        char r2 = 1;
    L11:
        return r0[r2];
    L9:
        r2 = 0;
        goto L11
    L5:
        return motionBackground[2];
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public Paint getPaint() {
        return this.paint;
    }

    public Drawable getShadowDrawable() {
        return null;
    }

    public Drawable[] getShadowDrawables() {
        return this.shadowDrawable;
    }

    public int getTopY() {
        return this.topY;
    }

    public Drawable getTransitionDrawable(int r9) {
        if (this.transitionDrawable != null) goto L6;
        Bitmap r0 = Bitmap.createBitmap(dp(50.0f), dp(40.0f), Bitmap.Config.ARGB_8888);
        Canvas r1 = new Canvas(r0);
        this.backupRect.set(getBounds());
        Paint r2 = new Paint(1);
        r2.setColor(-1);
        setBounds(0, 0, r0.getWidth(), r0.getHeight());
        draw(r1, r2);
        this.transitionDrawable = new NinePatchDrawable(r0, getByteBuffer((r0.getWidth() / 2) - 1, (r0.getWidth() / 2) + 1, (r0.getHeight() / 2) - 1, (r0.getHeight() / 2) + 1, -1).array(), new Rect(), null);
        setBounds(this.backupRect);
    L6:
        if (this.transitionDrawableColor == r9) goto L9;
        this.transitionDrawableColor = r9;
        this.transitionDrawable.setColorFilter(new PorterDuffColorFilter(r9, PorterDuff.Mode.MULTIPLY));
    L9:
        return this.transitionDrawable;
    }

    public boolean hasGradient() {
        if (this.gradientShader != null) goto L5;
        return false;
    L5:
        if (Theme.shouldDrawGradientIcons == false) goto L10;
        return true;
    L10:
        return false;
    }

    public Path makePath() {
        return makePath(this.pathDrawCacheParams);
    }

    public Path makePath(PathDrawParams r13) {
        Rect r1 = getBounds();
        int r8 = dp(2.0f);
        int r0 = this.overrideRoundRadius;
        if (r0 == 0) goto L6;
        int r4 = r0;
        int r5 = r4;
    L13:
        int r6 = dp(6.0f);
        boolean r2 = false;
        int r9 = Math.max(r1.top, 0);
        boolean r02 = true;
        if (r13 != null) goto L16;
    L18:
        int r3 = this.currentType;
        int r7 = this.topY;
        if (r3 != 1) goto L25;
        if (((r7 + r1.bottom) - (r6 * 2)) >= this.currentBackgroundHeight) goto L23;
    L22:
        boolean r32 = true;
    L28:
        if ((this.topY + (r4 * 2)) < 0) goto L30;
        r2 = true;
    L30:
        boolean r11 = r32;
        boolean r33 = r2;
        boolean r22 = r11;
    L31:
        if (r13 == null) goto L33;
        Path r10 = r13.path;
        r02 = r13.invalidatePath(r1, r22, r33, r4, r5, r6, shouldRemoveMessageTail());
    L34:
        if (r02 == false) goto L36;
    L37:
        Path r12 = r10;
        generatePath(r12, r1, r8, r4, r6, r5, r9, r22, r33, true);
        return r12;
    L36:
        if (this.overrideRoundRadius != 0) goto L37;
        return r10;
    L33:
        r10 = this.path;
    L23:
        r32 = false;
        goto L28
    L25:
        if (((r7 + r1.bottom) - r4) >= this.currentBackgroundHeight) goto L23;
    L16:
        if (r1.height() >= this.currentBackgroundHeight) goto L18;
        r22 = true;
        r33 = true;
        goto L31
    L6:
        if (this.overrideRounding <= 0.0f) goto L10;
        int r03 = AndroidUtilities.lerp(dp(SharedConfig.bubbleRadius), Math.min(r1.width(), r1.height()) / 2, this.overrideRounding);
        int r34 = AndroidUtilities.lerp(dp(Math.min(6, SharedConfig.bubbleRadius)), Math.min(r1.width(), r1.height()) / 2, this.overrideRounding);
    L8:
        r4 = r03;
        r5 = r34;
        goto L13
    L10:
        if (this.currentType != 2) goto L12;
        r03 = dp(6.0f);
        r34 = dp(6.0f);
        goto L8
    L12:
        r03 = dp(SharedConfig.bubbleRadius);
        r34 = dp(Math.min(6, SharedConfig.bubbleRadius));
        goto L8
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r5) {
        if (this.alpha == r5) goto L5;
    L6:
        this.alpha = r5;
        this.paint.setAlpha(r5);
        if (this.isOut == false) goto L10;
        this.selectedPaint.setAlpha((int) (Color.alpha(getColor(Theme.key_chat_outBubbleGradientSelectedOverlay)) * (r5 / 255.0f)));
    L10:
        if (this.gradientShader != null) goto L15;
        Drawable r4 = getBackgroundDrawable();
        if (r4.getAlpha() == r5) goto L16;
        r4.setAlpha(r5);
        return;
    L16:
        return;
    L15:
        return;
    L5:
        if (this.paint.getAlpha() == r5) goto L10;
        goto L6
    }

    public void setBotButtonsBottom(boolean r1) {
        this.botButtonsBottom = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int r1, int r2, int r3, int r4) {
        super.setBounds(r1, r2, r3, r4);
        MessageDrawable r0 = this.crossfadeFromDrawable;
        if (r0 == null) goto L6;
        r0.setBounds(r1, r2, r3, r4);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(int r1, PorterDuff.Mode r2) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setDrawFullBubble(boolean r1) {
        this.drawFullBubble = r1;
    }

    public void setResourceProvider(Theme.ResourcesProvider r1) {
        this.resourcesProvider = r1;
    }

    public void setRoundRadius(int r1) {
        this.overrideRoundRadius = r1;
    }

    public void setRoundingRadius(float r1) {
        this.overrideRounding = r1;
    }

    public void setTop(int r23, int r24, int r25, int r26, int r27, int r28, boolean r29, boolean r30) {
        MessageDrawable r1 = this.crossfadeFromDrawable;
        if (r1 == null) goto L5;
        int r4 = r25;
        int r6 = r27;
        r1.setTop(r23, r24, r4, r26, r6, r28, r29, r30);
    L6:
        boolean r12 = this.isOut;
        boolean r2 = this.isSelected;
        int r5 = 0;
        if (r12 == false) goto L16;
        if (r2 == false) goto L10;
        int r13 = Theme.key_chat_outBubbleSelected;
    L11:
        int r14 = getColor(r13);
        int r22 = getCurrentColor(Theme.key_chat_outBubbleGradient1);
        int r7 = getCurrentColor(Theme.key_chat_outBubbleGradient2);
        int r8 = getCurrentColor(Theme.key_chat_outBubbleGradient3);
        if (getCurrentColor(Theme.key_chat_outBubbleGradientAnimated) == 0) goto L14;
        boolean r9 = true;
    L15:
        boolean r10 = r9;
        int r92 = r22;
        boolean r210 = r10;
        int r102 = r7;
        int r11 = r8;
    L20:
        if (r92 == 0) goto L22;
        r14 = getColor(Theme.key_chat_outBubble);
    L22:
        int r82 = r14;
        if (this.themePreview == false) goto L26;
        char r15 = 2;
    L30:
        if (this.isCrossfadeBackground == true) goto L37;
        if (r102 == 0) goto L37;
        if (r210 == false) goto L37;
        MotionBackgroundDrawable r122 = motionBackground[r15];
        if (r122 == null) goto L37;
        int[] r123 = r122.getColors();
        this.currentColor = r123[0];
        this.currentGradientColor1 = r123[1];
        this.currentGradientColor2 = r123[2];
        this.currentGradientColor3 = r123[3];
    L37:
        if (this.isCrossfadeBackground == false) goto L65;
        if (r102 == 0) goto L65;
        if (r210 == false) goto L65;
        if (r4 != this.currentBackgroundHeight) goto L55;
        if (this.crosfadeFromBitmapShader == null) goto L55;
        if (this.currentColor != r82) goto L55;
        if (this.currentGradientColor1 != r92) goto L55;
        if (this.currentGradientColor2 != r102) goto L55;
        if (this.currentGradientColor3 != r11) goto L55;
        if (this.currentAnimateGradient != r210) goto L55;
    L64:
        Shader r3 = this.crosfadeFromBitmapShader;
        this.gradientShader = r3;
        this.paint.setShader(r3);
        this.paint.setColor(-1);
        this.currentColor = r82;
        this.currentAnimateGradient = r210;
        this.currentGradientColor1 = r92;
        this.currentGradientColor2 = r102;
        this.currentGradientColor3 = r11;
    L101:
        if ((this.gradientShader instanceof BitmapShader) == false) goto L103;
        motionBackground[r15].setBounds(0, r6, r24, r4 - r26);
    L103:
        this.currentBackgroundHeight = r4;
        if ((this.gradientShader instanceof BitmapShader) == false) goto L106;
        r5 = r26;
    L106:
        this.topY = r23 - r5;
        this.isTopNear = r29;
        this.isBottomNear = r30;
        return;
    L55:
        if (this.crosfadeFromBitmap != null) goto L57;
        Bitmap r124 = Bitmap.createBitmap(60, 80, Bitmap.Config.ARGB_8888);
        this.crosfadeFromBitmap = r124;
        r124.setHasAlpha(false);
        Bitmap r132 = this.crosfadeFromBitmap;
        Shader.TileMode r152 = Shader.TileMode.CLAMP;
        this.crosfadeFromBitmapShader = new BitmapShader(r132, r152, r152);
    L57:
        MotionBackgroundDrawable[] r125 = motionBackground;
        if (r125[r15] != null) goto L63;
        r125[r15] = new MotionBackgroundDrawable();
        if (this.currentType == 2) goto L62;
        motionBackground[r15].setPostInvalidateParent(true);
    L62:
        motionBackground[r15].setRoundRadius(dp(1.0f));
    L63:
        motionBackground[r15].setColors(r82, r92, r102, r11, this.crosfadeFromBitmap);
        this.crosfadeFromBitmapShader.setLocalMatrix(this.matrix);
    L65:
        if (r92 != 0) goto L67;
    L95:
        if (r92 != 0) goto L101;
        if (this.gradientShader == null) goto L99;
        this.gradientShader = null;
        this.paint.setShader(null);
    L99:
        this.colorAlpha = Color.alpha(r82);
        this.paint.setColor(r82);
        goto L101
    L67:
        if (this.gradientShader != null) goto L69;
    L80:
        if (r102 == 0) goto L89;
        if (r210 == false) goto L89;
        MotionBackgroundDrawable[] r126 = motionBackground;
        if (r126[r15] != null) goto L88;
        r126[r15] = new MotionBackgroundDrawable();
        if (this.currentType == 2) goto L87;
        motionBackground[r15].setPostInvalidateParent(true);
    L87:
        motionBackground[r15].setRoundRadius(dp(1.0f));
    L88:
        motionBackground[r15].setColors(r82, r92, r102, r11);
        this.gradientShader = motionBackground[r15].getBitmapShader();
    L94:
        this.paint.setShader(this.gradientShader);
        this.currentColor = r82;
        this.currentAnimateGradient = r210;
        this.currentGradientColor1 = r92;
        this.currentGradientColor2 = r102;
        this.currentGradientColor3 = r11;
        this.paint.setColor(-1);
    L89:
        if (r102 == 0) goto L93;
        if (r11 == 0) goto L92;
        this.gradientShader = new LinearGradient(0.0f, r6, 0.0f, r4, new int[]{r11, r102, r92, r82}, null, Shader.TileMode.CLAMP);
        goto L94
    L92:
        this.gradientShader = new LinearGradient(0.0f, r6, 0.0f, r4, new int[]{r102, r92, r82}, null, Shader.TileMode.CLAMP);
        goto L94
    L93:
        this.gradientShader = new LinearGradient(0.0f, r6, 0.0f, r4, new int[]{r92, r82}, null, Shader.TileMode.CLAMP);
        goto L94
    L69:
        if (r4 != this.currentBackgroundHeight) goto L80;
        if (this.currentColor != r82) goto L80;
        if (this.currentGradientColor1 != r92) goto L80;
        if (this.currentGradientColor2 != r102) goto L80;
        if (this.currentGradientColor3 != r11) goto L80;
        if (this.currentAnimateGradient == r210) goto L95;
    L26:
        if (this.currentType != 2) goto L28;
        r15 = 1;
        goto L30
    L28:
        r15 = 0;
        goto L30
    L14:
        r9 = false;
        goto L15
    L10:
        r13 = Theme.key_chat_outBubble;
        goto L11
    L16:
        if (r2 == false) goto L18;
        int r16 = Theme.key_chat_inBubbleSelected;
    L19:
        r14 = getColor(r16);
        r210 = false;
        r92 = 0;
        r102 = 0;
        r11 = 0;
        goto L20
    L18:
        r16 = Theme.key_chat_inBubble;
        goto L19
    L5:
        r4 = r25;
        r6 = r27;
        goto L6
    }

    public void setTop(int r10, int r11, int r12, boolean r13, boolean r14) {
        setTop(r10, r11, r12, r12, 0, 0, r13, r14);
    }
}
