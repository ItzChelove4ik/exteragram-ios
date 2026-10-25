package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.BubbleActivity;

/* loaded from: classes7.dex */
public class PhotoFilterBlurControl extends FrameLayout {
    private static final float BlurInsetProximity = 0.0f;
    private static final float BlurViewCenterInset = 0.0f;
    private static final float BlurViewRadiusInset = 0.0f;
    private final int GestureStateBegan;
    private final int GestureStateCancelled;
    private final int GestureStateChanged;
    private final int GestureStateEnded;
    private final int GestureStateFailed;
    private BlurViewActiveControl activeControl;
    private Size actualAreaSize;
    private float angle;
    private Paint arcPaint;
    private RectF arcRect;
    private PointF centerPoint;
    private boolean checkForMoving;
    private boolean checkForZooming;
    private PhotoFilterLinearBlurControlDelegate delegate;
    private float falloff;
    private boolean inBubbleMode;
    private boolean isMoving;
    private boolean isZooming;
    private Paint paint;
    private float pointerScale;
    private float pointerStartX;
    private float pointerStartY;
    private float size;
    private PointF startCenterPoint;
    private float startDistance;
    private float startPointerDistance;
    private float startRadius;
    private int type;

    /* renamed from: org.telegram.ui.Components.PhotoFilterBlurControl$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$telegram$ui$Components$PhotoFilterBlurControl$BlurViewActiveControl = null;

        static {
            int[] r0 = new int[BlurViewActiveControl.values().length];
            $SwitchMap$org$telegram$ui$Components$PhotoFilterBlurControl$BlurViewActiveControl = r0;
            r0[BlurViewActiveControl.BlurViewActiveControlCenter.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$org$telegram$ui$Components$PhotoFilterBlurControl$BlurViewActiveControl[BlurViewActiveControl.BlurViewActiveControlInnerRadius.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L14:
            $SwitchMap$org$telegram$ui$Components$PhotoFilterBlurControl$BlurViewActiveControl[BlurViewActiveControl.BlurViewActiveControlOuterRadius.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L18:
            $SwitchMap$org$telegram$ui$Components$PhotoFilterBlurControl$BlurViewActiveControl[BlurViewActiveControl.BlurViewActiveControlRotation.ordinal()] = 4;     // Catch: NoSuchFieldError -> L11
            return;
        }
    }

    public enum BlurViewActiveControl extends Enum<BlurViewActiveControl> {
        private static final /* synthetic */ BlurViewActiveControl[] $VALUES = null;
        public static final BlurViewActiveControl BlurViewActiveControlCenter = null;
        public static final BlurViewActiveControl BlurViewActiveControlInnerRadius = null;
        public static final BlurViewActiveControl BlurViewActiveControlNone = null;
        public static final BlurViewActiveControl BlurViewActiveControlOuterRadius = null;
        public static final BlurViewActiveControl BlurViewActiveControlRotation = null;
        public static final BlurViewActiveControl BlurViewActiveControlWholeArea = null;

        private static /* synthetic */ BlurViewActiveControl[] $values() {
            return new BlurViewActiveControl[]{BlurViewActiveControlNone, BlurViewActiveControlCenter, BlurViewActiveControlInnerRadius, BlurViewActiveControlOuterRadius, BlurViewActiveControlWholeArea, BlurViewActiveControlRotation};
        }

        static {
            BlurViewActiveControlNone = new BlurViewActiveControl("BlurViewActiveControlNone", 0);
            BlurViewActiveControlCenter = new BlurViewActiveControl("BlurViewActiveControlCenter", 1);
            BlurViewActiveControlInnerRadius = new BlurViewActiveControl("BlurViewActiveControlInnerRadius", 2);
            BlurViewActiveControlOuterRadius = new BlurViewActiveControl("BlurViewActiveControlOuterRadius", 3);
            BlurViewActiveControlWholeArea = new BlurViewActiveControl("BlurViewActiveControlWholeArea", 4);
            BlurViewActiveControlRotation = new BlurViewActiveControl("BlurViewActiveControlRotation", 5);
            $VALUES = $values();
        }

        BlurViewActiveControl(String r1, int r2) {
        }

        public static BlurViewActiveControl valueOf(String r1) {
            return (BlurViewActiveControl) Enum.valueOf(BlurViewActiveControl.class, r1);
        }

        public static BlurViewActiveControl[] values() {
            return (BlurViewActiveControl[]) $VALUES.clone();
        }
    }

    public interface PhotoFilterLinearBlurControlDelegate {
        void valueChanged(PointF r1, float r2, float r3, float r4);
    }

    static {
        BlurInsetProximity = AndroidUtilities.dp(20.0f);
        BlurViewCenterInset = AndroidUtilities.dp(30.0f);
        BlurViewRadiusInset = AndroidUtilities.dp(30.0f);
    }

    public PhotoFilterBlurControl(Context r4) {
        super(r4);
        this.GestureStateBegan = 1;
        this.GestureStateChanged = 2;
        this.GestureStateEnded = 3;
        this.GestureStateCancelled = 4;
        this.GestureStateFailed = 5;
        this.startCenterPoint = new PointF();
        this.actualAreaSize = new Size();
        this.centerPoint = new PointF(0.5f, 0.5f);
        this.falloff = 0.15f;
        this.size = 0.35f;
        this.arcRect = new RectF();
        this.pointerScale = 1.0f;
        this.checkForMoving = true;
        this.paint = new Paint(1);
        this.arcPaint = new Paint(1);
        setWillNotDraw(false);
        this.paint.setColor(-1);
        this.arcPaint.setColor(-1);
        this.arcPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
        this.arcPaint.setStyle(Paint.Style.STROKE);
        this.inBubbleMode = r4 instanceof BubbleActivity;
    }

    private float degreesToRadians(float r1) {
        return (r1 * 3.1415927f) / 180.0f;
    }

    private PointF getActualCenterPoint() {
        float r0 = getWidth();
        float r1 = this.actualAreaSize.width;
        float r02 = ((r0 - r1) / 2.0f) + (this.centerPoint.x * r1);
        if (this.inBubbleMode == true) goto L5;
        int r12 = AndroidUtilities.statusBarHeight;
    L6:
        float r3 = getHeight();
        Size r4 = this.actualAreaSize;
        float r5 = r4.height;
        float r13 = r12 + ((r3 - r5) / 2.0f);
        float r32 = r4.width;
        return new PointF(r02, (r13 - ((r32 - r5) / 2.0f)) + (this.centerPoint.y * r32));
    L5:
        r12 = 0;
        goto L6
    }

    private float getActualInnerRadius() {
        Size r0 = this.actualAreaSize;
        return Math.min(r0.width, r0.height) * this.falloff;
    }

    private float getActualOuterRadius() {
        Size r0 = this.actualAreaSize;
        return Math.min(r0.width, r0.height) * this.size;
    }

    private float getDistance(MotionEvent r4) {
        if (r4.getPointerCount() == 2) goto L6;
        return 0.0f;
    L6:
        float r0 = r4.getX(0);
        float r3 = r4.getY(0);
        float r02 = r0 - r4.getX(1);
        float r32 = r3 - r4.getY(1);
        return (float) Math.sqrt((r02 * r02) + (r32 * r32));
    }

    private void handlePan(int r18, MotionEvent r19) {
        float r2 = r19.getX();
        float r3 = r19.getY();
        PointF r4 = getActualCenterPoint();
        float r5 = r2 - r4.x;
        float r6 = r3 - r4.y;
        float r7 = (float) Math.sqrt((r5 * r5) + (r6 * r6));
        Size r8 = this.actualAreaSize;
        float r82 = Math.min(r8.width, r8.height);
        float r9 = this.falloff * r82;
        float r10 = this.size * r82;
        float r52 = (float) Math.abs((r5 * Math.cos(degreesToRadians(this.angle) + 1.5707963267948966d)) + (r6 * Math.sin(degreesToRadians(this.angle) + 1.5707963267948966d)));
        int r62 = 0;
        float r11 = 0.0f;
        if (r18 != 1) goto L5;
        this.pointerStartX = r19.getX();
        this.pointerStartY = r19.getY();
        if (Math.abs(r10 - r9) >= BlurInsetProximity) goto L93;
        r62 = 1;
    L93:
        if (r62 == 0) goto L95;
        float r1 = 0.0f;
    L96:
        if (r62 != 0) goto L99;
        r11 = BlurViewRadiusInset;
    L99:
        int r22 = this.type;
        if (r22 == 0) goto L102;
        if (r22 == 1) goto L121;
    L133:
        setSelected(true, true);
        return;
    L121:
        if (r7 >= BlurViewCenterInset) goto L123;
        this.activeControl = BlurViewActiveControl.BlurViewActiveControlCenter;
        this.startCenterPoint = r4;
        goto L133
    L123:
        float r23 = BlurViewRadiusInset;
        if (r7 <= (r9 - r23)) goto L129;
        if (r7 >= (r1 + r9)) goto L129;
        this.activeControl = BlurViewActiveControl.BlurViewActiveControlInnerRadius;
        this.startDistance = r7;
        this.startRadius = r9;
    L129:
        if (r7 <= (r10 - r11)) goto L133;
        if (r7 >= (r23 + r10)) goto L133;
        this.activeControl = BlurViewActiveControl.BlurViewActiveControlOuterRadius;
        this.startDistance = r7;
        this.startRadius = r10;
        goto L133
    L102:
        if (r7 >= BlurViewCenterInset) goto L104;
        this.activeControl = BlurViewActiveControl.BlurViewActiveControlCenter;
        this.startCenterPoint = r4;
        goto L133
    L104:
        float r24 = BlurViewRadiusInset;
        if (r52 <= (r9 - r24)) goto L110;
        if (r52 >= (r1 + r9)) goto L110;
        this.activeControl = BlurViewActiveControl.BlurViewActiveControlInnerRadius;
        this.startDistance = r52;
        this.startRadius = r9;
    L110:
        if (r52 <= (r10 - r11)) goto L115;
        if (r52 >= (r10 + r24)) goto L115;
        this.activeControl = BlurViewActiveControl.BlurViewActiveControlOuterRadius;
        this.startDistance = r52;
        this.startRadius = r10;
    L115:
        if (r52 > (r9 - r24)) goto L117;
    L118:
        this.activeControl = BlurViewActiveControl.BlurViewActiveControlRotation;
        goto L133
    L117:
        if (r52 < (r10 + r24)) goto L133;
    L95:
        r1 = BlurViewRadiusInset;
        goto L96
    L5:
        if (r18 == 2) goto L13;
        if (r18 == 3) goto L11;
        if (r18 == 4) goto L11;
        if (r18 == 5) goto L11;
        return;
    L11:
        this.activeControl = BlurViewActiveControl.BlurViewActiveControlNone;
        setSelected(false, true);
        return;
    L13:
        int r12 = this.type;
        if (r12 != 0) goto L74;
        int r13 = AnonymousClass1.$SwitchMap$org$telegram$ui$Components$PhotoFilterBlurControl$BlurViewActiveControl[this.activeControl.ordinal()];
        if (r13 == 1) goto L70;
        if (r13 == 2) goto L69;
        if (r13 == 3) goto L68;
        if (r13 != 4) goto L86;
        float r14 = r2 - this.pointerStartX;
        float r53 = r3 - this.pointerStartY;
        if (r2 <= r4.x) goto L24;
        boolean r72 = true;
    L26:
        if (r3 <= r4.y) goto L28;
        boolean r42 = true;
    L30:
        if (Math.abs(r53) <= Math.abs(r14)) goto L32;
        boolean r83 = true;
    L33:
        if (r72 == true) goto L42;
        if (r42 == true) goto L42;
        if (r83 == false) goto L40;
        if (r53 >= 0.0f) goto L67;
    L38:
        r62 = 1;
    L67:
        this.angle += ((((float) Math.sqrt((r14 * r14) + (r53 * r53))) * ((r62 * 2) - 1)) / 3.1415927f) / 1.15f;
        this.pointerStartX = r2;
        this.pointerStartY = r3;
        goto L86
    L40:
        if (r14 <= 0.0f) goto L67;
    L42:
        if (r72 == false) goto L51;
        if (r42 == true) goto L51;
        if (r83 == false) goto L49;
        if (r53 <= 0.0f) goto L67;
    L49:
        if (r14 <= 0.0f) goto L67;
    L51:
        if (r72 == false) goto L60;
        if (r42 == false) goto L60;
        if (r83 == false) goto L58;
        if (r53 <= 0.0f) goto L67;
    L58:
        if (r14 >= 0.0f) goto L67;
    L60:
        if (r83 == false) goto L65;
        if (r53 >= 0.0f) goto L67;
    L65:
        if (r14 >= 0.0f) goto L67;
    L32:
        r83 = false;
        goto L33
    L28:
        r42 = false;
        goto L30
    L24:
        r72 = false;
    L86:
        invalidate();
        PhotoFilterLinearBlurControlDelegate r15 = this.delegate;
        if (r15 == null) goto L135;
        r15.valueChanged(this.centerPoint, this.falloff, this.size, degreesToRadians(this.angle) + 1.5707964f);
        return;
    L135:
        return;
    L68:
        this.size = Math.max(this.falloff + 0.02f, (this.startRadius + (r52 - this.startDistance)) / r82);
        goto L86
    L69:
        this.falloff = Math.min(Math.max(0.1f, (this.startRadius + (r52 - this.startDistance)) / r82), this.size - 0.02f);
        goto L86
    L70:
        float r25 = r2 - this.pointerStartX;
        float r32 = r3 - this.pointerStartY;
        float r43 = (getWidth() - this.actualAreaSize.width) / 2.0f;
        if (this.inBubbleMode == true) goto L73;
        r62 = AndroidUtilities.statusBarHeight;
    L73:
        float r54 = r62;
        float r63 = getHeight();
        Size r73 = this.actualAreaSize;
        float r84 = r73.height;
        RectOld r16 = new RectOld(r43, r54 + ((r63 - r84) / 2.0f), r73.width, r84);
        float r44 = r16.x;
        float r26 = Math.max(r44, Math.min(r16.width + r44, this.startCenterPoint.x + r25));
        float r45 = r16.y;
        PointF r46 = new PointF(r26, Math.max(r45, Math.min(r16.height + r45, this.startCenterPoint.y + r32)));
        float r27 = r46.x - r16.x;
        Size r33 = this.actualAreaSize;
        float r55 = r33.width;
        this.centerPoint = new PointF(r27 / r55, ((r46.y - r16.y) + ((r55 - r33.height) / 2.0f)) / r55);
        goto L86
    L74:
        if (r12 != 1) goto L86;
        int r17 = AnonymousClass1.$SwitchMap$org$telegram$ui$Components$PhotoFilterBlurControl$BlurViewActiveControl[this.activeControl.ordinal()];
        if (r17 == 1) goto L82;
        if (r17 == 2) goto L81;
        if (r17 != 3) goto L86;
        this.size = Math.max(this.falloff + 0.02f, (this.startRadius + (r7 - this.startDistance)) / r82);
        goto L86
    L81:
        this.falloff = Math.min(Math.max(0.1f, (this.startRadius + (r7 - this.startDistance)) / r82), this.size - 0.02f);
        goto L86
    L82:
        float r28 = r2 - this.pointerStartX;
        float r34 = r3 - this.pointerStartY;
        float r47 = (getWidth() - this.actualAreaSize.width) / 2.0f;
        if (this.inBubbleMode == true) goto L85;
        r62 = AndroidUtilities.statusBarHeight;
    L85:
        float r56 = r62;
        float r64 = getHeight();
        Size r74 = this.actualAreaSize;
        float r85 = r74.height;
        RectOld r110 = new RectOld(r47, r56 + ((r64 - r85) / 2.0f), r74.width, r85);
        float r48 = r110.x;
        float r29 = Math.max(r48, Math.min(r110.width + r48, this.startCenterPoint.x + r28));
        float r49 = r110.y;
        PointF r410 = new PointF(r29, Math.max(r49, Math.min(r110.height + r49, this.startCenterPoint.y + r34)));
        float r210 = r410.x - r110.x;
        Size r35 = this.actualAreaSize;
        float r57 = r35.width;
        this.centerPoint = new PointF(r210 / r57, ((r410.y - r110.y) + ((r57 - r35.height) / 2.0f)) / r57);
        goto L86
    }

    private void handlePinch(int r4, MotionEvent r5) {
        if (r4 != 1) goto L5;
        this.startPointerDistance = getDistance(r5);
        this.pointerScale = 1.0f;
        this.activeControl = BlurViewActiveControl.BlurViewActiveControlWholeArea;
        setSelected(true, true);
    L16:
        float r42 = getDistance(r5);
        float r52 = this.pointerScale + (((r42 - this.startPointerDistance) / AndroidUtilities.density) * 0.01f);
        this.pointerScale = r52;
        float r53 = Math.max(0.1f, this.falloff * r52);
        this.falloff = r53;
        this.size = Math.max(r53 + 0.02f, this.size * this.pointerScale);
        this.pointerScale = 1.0f;
        this.startPointerDistance = r42;
        invalidate();
        PhotoFilterLinearBlurControlDelegate r43 = this.delegate;
        if (r43 == null) goto L20;
        r43.valueChanged(this.centerPoint, this.falloff, this.size, degreesToRadians(this.angle) + 1.5707964f);
        return;
    L20:
        return;
    L5:
        if (r4 == 2) goto L16;
        if (r4 != 3) goto L9;
    L13:
        this.activeControl = BlurViewActiveControl.BlurViewActiveControlNone;
        setSelected(false, true);
        return;
    L9:
        if (r4 == 4) goto L13;
        if (r4 == 5) goto L13;
    }

    private void setSelected(boolean r1, boolean r2) {
    }

    @Override // android.view.View
    public void onDraw(Canvas r21) {
        Canvas r1 = r21;
        super.onDraw(r21);
        PointF r2 = getActualCenterPoint();
        float r7 = getActualInnerRadius();
        float r8 = getActualOuterRadius();
        r1.translate(r2.x, r2.y);
        int r22 = this.type;
        int r10 = 0;
        if (r22 != 0) goto L13;
        r1.rotate(this.angle);
        float r12 = AndroidUtilities.dp(6.0f);
        float r13 = AndroidUtilities.dp(12.0f);
        float r14 = AndroidUtilities.dp(1.5f);
        int r15 = 0;
    L6:
        if (r15 >= 30) goto L8;
        float r16 = r13 + r12;
        float r23 = r15 * r16;
        float r3 = -r7;
        float r4 = r23 + r13;
        float r5 = r14 - r7;
        r1.drawRect(r23, r3, r4, r5, this.paint);
        float r42 = ((-r15) * r16) - r12;
        float r24 = r42 - r13;
        r1 = r21;
        r1.drawRect(r24, r3, r42, r5, this.paint);
        float r52 = r14 + r7;
        r1.drawRect(r23, r7, r4, r52, this.paint);
        r1.drawRect(r24, r7, r42, r52, this.paint);
        r15 = r15 + 1;
        goto L6
    L8:
        float r72 = AndroidUtilities.dp(6.0f);
    L9:
        if (r10 >= 64) goto L21;
        float r11 = r72 + r12;
        float r25 = r10 * r11;
        float r32 = -r8;
        float r43 = r72 + r25;
        float r53 = r14 - r8;
        r21.drawRect(r25, r32, r43, r53, this.paint);
        float r44 = ((-r10) * r11) - r12;
        float r26 = r44 - r72;
        r21.drawRect(r26, r32, r44, r53, this.paint);
        float r54 = r14 + r8;
        r21.drawRect(r25, r8, r43, r54, this.paint);
        r21.drawRect(r26, r8, r44, r54, this.paint);
        r10 = r10 + 1;
    L21:
        r21.drawCircle(0.0f, 0.0f, AndroidUtilities.dp(8.0f), this.paint);
        return;
    L13:
        if (r22 != 1) goto L21;
        float r27 = -r7;
        this.arcRect.set(r27, r27, r7, r7);
        int r73 = 0;
    L15:
        RectF r28 = this.arcRect;
        if (r73 >= 22) goto L18;
        r21.drawArc(r28, 16.35f * r73, 10.2f, false, this.arcPaint);
        r73 = r73 + 1;
        goto L15
    L18:
        float r17 = -r8;
        r28.set(r17, r17, r8, r8);
    L19:
        if (r10 >= 64) goto L21;
        r21.drawArc(this.arcRect, 5.62f * r10, 3.6f, false, this.arcPaint);
        r10 = r10 + 1;
        goto L19
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r18) {
        int r2 = r18.getActionMasked();
        if (r2 == 0) goto L26;
        if (r2 == 1) goto L19;
        if (r2 == 2) goto L13;
        if (r2 == 3) goto L19;
        if (r2 == 5) goto L26;
        if (r2 == 6) goto L19;
    L96:
        return true;
    L13:
        if (this.isMoving == false) goto L16;
        handlePan(2, r18);
        goto L96
    L16:
        if (this.isZooming == false) goto L96;
        handlePinch(2, r18);
    L19:
        if (this.isMoving == false) goto L22;
        handlePan(3, r18);
        this.isMoving = false;
    L24:
        this.checkForMoving = true;
        this.checkForZooming = true;
        goto L96
    L22:
        if (this.isZooming == false) goto L24;
        handlePinch(3, r18);
        this.isZooming = false;
    L26:
        if (r18.getPointerCount() != 1) goto L85;
        if (this.checkForMoving == false) goto L96;
        if (this.isMoving == true) goto L96;
        float r22 = r18.getX();
        float r3 = r18.getY();
        PointF r4 = getActualCenterPoint();
        PointF r42 = new PointF(r22 - r4.x, r3 - r4.y);
        float r23 = r42.x;
        float r32 = r42.y;
        float r24 = (float) Math.sqrt((r23 * r23) + (r32 * r32));
        float r33 = getActualInnerRadius();
        float r7 = getActualOuterRadius();
        if (Math.abs(r7 - r33) >= BlurInsetProximity) goto L34;
        boolean r8 = true;
    L35:
        float r9 = 0.0f;
        if (r8 == false) goto L38;
        float r10 = 0.0f;
    L39:
        if (r8 == true) goto L42;
        r9 = BlurViewRadiusInset;
    L42:
        int r82 = this.type;
        if (r82 != 0) goto L66;
        float r43 = (float) Math.abs((r42.x * Math.cos(degreesToRadians(this.angle) + 1.5707963267948966d)) + (r42.y * Math.sin(degreesToRadians(this.angle) + 1.5707963267948966d)));
        if (r24 >= BlurViewCenterInset) goto L48;
        boolean r25 = true;
        this.isMoving = true;
    L47:
        ?? r5 = r25;
    L81:
        this.checkForMoving = false;
        if (this.isMoving == false) goto L97;
        handlePan(r5, r18);
        return r5;
    L97:
        return r5;
    L48:
        r25 = true;
        float r52 = BlurViewRadiusInset;
        if (r43 <= (r33 - r52)) goto L54;
        if (r43 >= (r10 + r33)) goto L54;
        this.isMoving = true;
    L54:
        if (r43 <= (r7 - r9)) goto L59;
        if (r43 >= (r7 + r52)) goto L59;
        this.isMoving = true;
    L59:
        if (r43 > (r33 - r52)) goto L61;
    L62:
        r5 = 1;
        this.isMoving = true;
        goto L81
    L61:
        if (r43 >= (r7 + r52)) goto L62;
        r5 = 1;
        goto L81
    L66:
        boolean r53 = true;
        r5 = r53;
        if (r82 != 1) goto L81;
        if (r24 >= BlurViewCenterInset) goto L71;
        this.isMoving = true;
        r5 = r53;
        goto L81
    L71:
        float r44 = BlurViewRadiusInset;
        if (r24 > (r33 - r44)) goto L74;
    L76:
        r5 = r53;
        if (r24 <= (r7 - r9)) goto L81;
        r5 = r53;
        if (r24 >= (r7 + r44)) goto L81;
        this.isMoving = true;
        r5 = r53;
        goto L81
    L74:
        if (r24 >= (r33 + r10)) goto L76;
        this.isMoving = true;
        r5 = r53;
        goto L81
    L38:
        r10 = BlurViewRadiusInset;
        goto L39
    L34:
        r8 = false;
        goto L35
    L85:
        if (this.isMoving == false) goto L88;
        handlePan(3, r18);
        this.checkForMoving = true;
        this.isMoving = false;
    L88:
        if (r18.getPointerCount() == 2) goto L90;
        handlePinch(3, r18);
        this.checkForZooming = true;
        this.isZooming = false;
        return true;
    L90:
        if (this.checkForZooming == true) goto L92;
        return true;
    L92:
        if (this.isZooming == true) goto L100;
        handlePinch(1, r18);
        this.isZooming = true;
        return true;
    L100:
        return true;
    }

    public void setActualAreaSize(float r1, float r2) {
        Size r0 = this.actualAreaSize;
        r0.width = r1;
        r0.height = r2;
    }

    public void setDelegate(PhotoFilterLinearBlurControlDelegate r1) {
        this.delegate = r1;
    }

    public void setType(int r1) {
        this.type = r1;
        invalidate();
    }
}
