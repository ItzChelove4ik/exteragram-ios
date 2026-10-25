package org.telegram.ui.Components.Paint.Views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.zxing.common.detector.MathUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.RectOld;

/* loaded from: classes7.dex */
public abstract class EntityView extends FrameLayout {
    private static final List<Integer> STICKY_ANGLES = null;
    private float angle;
    private ValueAnimator angleAnimator;
    private boolean announcedDrag;
    private boolean announcedMultitouchDrag;
    private boolean announcedSelection;
    private boolean announcedTrash;
    private ButtonBounce bounce;
    private int currentStickyAngle;
    private final float[] cxy;
    private EntityViewDelegate delegate;
    private ValueAnimator fromStickyAngleAnimator;
    private float fromStickyAnimatedAngle;
    private float fromStickyToAngle;
    private boolean hadMultitouch;
    public boolean hasPanned;
    public boolean hasReleased;
    private boolean hasStickyAngle;
    private boolean hasTransformed;
    private boolean lastIsMultitouch;
    private ViewGroup lastSelectionContainer;
    private final Runnable longPressRunnable;
    private PointF position;
    private float previousLocationCX;
    private float previousLocationCY;
    private float previousLocationX;
    private float previousLocationX2;
    private float previousLocationY;
    private float previousLocationY2;
    private boolean recognizedLongPress;
    private float scale;
    private ValueAnimator selectAnimator;
    private float selectT;
    private boolean selecting;
    public SelectionView selectionView;
    private Runnable setStickyAngleRunnable;
    private Runnable setStickyXRunnable;
    private Runnable setStickyYRunnable;
    private int stickyAngleRunnableValue;
    private float stickyAnimatedAngle;
    private int stickyX;
    private ValueAnimator stickyXAnimator;
    private int stickyXRunnableValue;
    private int stickyY;
    private ValueAnimator stickyYAnimator;
    private int stickyYRunnableValue;
    private ValueAnimator trashAnimator;
    private float trashScale;
    private UUID uuid;
    private final float[] xy;
    private final float[] xy2;

    /* renamed from: org.telegram.ui.Components.Paint.Views.EntityView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ EntityView this$0;

        public AnonymousClass1(EntityView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2 != EntityView.m11430$$Nest$fgetstickyXAnimator(this.this$0)) goto L6;
            EntityView.m11446$$Nest$fputstickyXAnimator(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.EntityView$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ EntityView this$0;

        public AnonymousClass2(EntityView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2 != EntityView.m11431$$Nest$fgetstickyYAnimator(this.this$0)) goto L6;
            EntityView.m11447$$Nest$fputstickyYAnimator(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.EntityView$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ EntityView this$0;

        public AnonymousClass3(EntityView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2 != EntityView.m11420$$Nest$fgetangleAnimator(this.this$0)) goto L6;
            EntityView.m11434$$Nest$fputangleAnimator(this.this$0, null);
            EntityView.m11445$$Nest$fputstickyAnimatedAngle(this.this$0, 0.0f);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.EntityView$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ EntityView this$0;

        public AnonymousClass4(EntityView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2 != EntityView.m11423$$Nest$fgetfromStickyAngleAnimator(this.this$0)) goto L6;
            EntityView.m11435$$Nest$fputfromStickyAngleAnimator(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.EntityView$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ EntityView this$0;

        public AnonymousClass5(EntityView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (EntityView.m11429$$Nest$fgetselecting(this.this$0) == true) goto L6;
            AndroidUtilities.removeFromParent(this.this$0.selectionView);
            this.this$0.selectionView = null;
            return;
        }
    }

    public interface EntityViewDelegate {
        boolean allowInteraction(EntityView r1);

        int[] getCenterLocation(EntityView r1);

        void getTransformedTouch(float r1, float r2, float[] r3);

        default boolean isEntityDeletable() {
            return true;
        }

        default void onEntityDragEnd(boolean r1) {
        }

        default void onEntityDragMultitouchEnd() {
        }

        default void onEntityDragMultitouchStart() {
        }

        default void onEntityDragStart() {
        }

        default void onEntityDragTrash(boolean r1) {
        }

        default void onEntityDraggedBottom(boolean r1) {
        }

        default void onEntityDraggedTop(boolean r1) {
        }

        default void onEntityHandleTouched() {
        }

        boolean onEntityLongClicked(EntityView r1);

        boolean onEntitySelected(EntityView r1);
    }

    public class SelectionView extends FrameLayout {
        private int currentHandle;
        protected Paint dotPaint;
        protected Paint dotStrokePaint;
        protected Paint paint;
        private final AnimatedFloat showAlpha;
        private boolean shown;
        final /* synthetic */ EntityView this$0;

        public SelectionView(EntityView r8, Context r9) {
            this.this$0 = r8;
            super(r9);
            this.paint = new Paint(1);
            this.dotPaint = new Paint(1);
            this.dotStrokePaint = new Paint(1);
            this.showAlpha = new AnimatedFloat(this, 0, 250, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.shown = true;
            setWillNotDraw(false);
            this.paint.setColor(-1);
            Paint r82 = this.paint;
            Paint.Style r2 = Paint.Style.STROKE;
            r82.setStyle(r2);
            this.paint.setStrokeWidth(AndroidUtilities.dp(2.0f));
            this.paint.setStrokeCap(Paint.Cap.ROUND);
            this.paint.setPathEffect(new DashPathEffect(new float[]{AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f)}, 0.5f));
            this.paint.setShadowLayer(AndroidUtilities.dpf2(0.75f), 0.0f, 0.0f, 1342177280);
            this.dotPaint.setColor(-15033089);
            this.dotStrokePaint.setColor(-1);
            this.dotStrokePaint.setStyle(r2);
            this.dotStrokePaint.setStrokeWidth(AndroidUtilities.dpf2(2.66f));
            this.dotStrokePaint.setShadowLayer(AndroidUtilities.dpf2(0.75f), 0.0f, 0.0f, 1342177280);
        }

        public float getShowAlpha() {
            return this.showAlpha.set(this.shown);
        }

        public void hide(boolean r1) {
            this.shown = !r1;
            invalidate();
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r18) {
            int r2 = r18.getActionMasked();
            EntityView.m11422$$Nest$fgetdelegate(this.this$0).getTransformedTouch(r18.getRawX(), r18.getRawY(), EntityView.m11432$$Nest$fgetxy(this.this$0));
            if (r18.getPointerCount() > 1) goto L5;
        L7:
            boolean r3 = false;
        L8:
            if (r3 == true) goto L10;
        L13:
            boolean r14 = r3;
        L14:
            EntityView r32 = this.this$0;
            if (r14 == false) goto L17;
            EntityView.m11421$$Nest$fgetcxy(r32)[0] = (EntityView.m11432$$Nest$fgetxy(this.this$0)[0] + EntityView.m11433$$Nest$fgetxy2(this.this$0)[0]) / 2.0f;
            EntityView.m11421$$Nest$fgetcxy(this.this$0)[1] = (EntityView.m11432$$Nest$fgetxy(this.this$0)[1] + EntityView.m11433$$Nest$fgetxy2(this.this$0)[1]) / 2.0f;
        L19:
            if (EntityView.m11425$$Nest$fgetlastIsMultitouch(this.this$0) == r14) goto L21;
            EntityView r33 = this.this$0;
            EntityView.m11441$$Nest$fputpreviousLocationX(r33, EntityView.m11432$$Nest$fgetxy(r33)[0]);
            EntityView r34 = this.this$0;
            EntityView.m11443$$Nest$fputpreviousLocationY(r34, EntityView.m11432$$Nest$fgetxy(r34)[1]);
            EntityView r35 = this.this$0;
            EntityView.m11442$$Nest$fputpreviousLocationX2(r35, EntityView.m11433$$Nest$fgetxy2(r35)[0]);
            EntityView r36 = this.this$0;
            EntityView.m11444$$Nest$fputpreviousLocationY2(r36, EntityView.m11433$$Nest$fgetxy2(r36)[1]);
            EntityView r37 = this.this$0;
            EntityView.m11439$$Nest$fputpreviousLocationCX(r37, EntityView.m11421$$Nest$fgetcxy(r37)[0]);
            EntityView r38 = this.this$0;
            EntityView.m11440$$Nest$fputpreviousLocationCY(r38, EntityView.m11421$$Nest$fgetcxy(r38)[1]);
            hide(r14);
        L21:
            EntityView.m11438$$Nest$fputlastIsMultitouch(this.this$0, r14);
            float r39 = EntityView.m11421$$Nest$fgetcxy(this.this$0)[0];
            float r8 = EntityView.m11421$$Nest$fgetcxy(this.this$0)[1];
            if (r2 == 0) goto L60;
            if (r2 != 1) goto L25;
        L55:
            EntityView r310 = this.this$0;
            if (r2 != 3) goto L58;
            boolean r22 = true;
        L59:
            EntityView.m11449$$Nest$monTouchUp(r310, r22);
            this.currentHandle = 0;
            hide(false);
        L53:
            boolean r23 = true;
        L69:
            EntityView.m11436$$Nest$fputhadMultitouch(this.this$0, r14);
            if (super.onTouchEvent(r18) == true) goto L74;
            if (r23 == true) goto L74;
            return false;
        L74:
            return true;
        L58:
            r22 = false;
            goto L59
        L25:
            if (r2 == 2) goto L28;
            if (r2 == 3) goto L55;
        L54:
            r23 = false;
            goto L69
        L28:
            int r24 = this.currentHandle;
            if (r24 != 3) goto L31;
            EntityView r11 = this.this$0;
            r23 = EntityView.m11448$$Nest$monTouchMove(r11, EntityView.m11432$$Nest$fgetxy(r11)[0], EntityView.m11432$$Nest$fgetxy(this.this$0)[1], r14, EntityView.m11433$$Nest$fgetxy2(this.this$0)[0], EntityView.m11433$$Nest$fgetxy2(this.this$0)[1]);
            goto L69
        L31:
            if (r24 == 0) goto L54;
            float r25 = r39 - EntityView.m11427$$Nest$fgetpreviousLocationX(this.this$0);
            float r4 = r8 - EntityView.m11428$$Nest$fgetpreviousLocationY(this.this$0);
            if (EntityView.m11424$$Nest$fgethasTransformed(this.this$0) == true) goto L39;
            if (Math.abs(r25) > AndroidUtilities.dp(2.0f)) goto L39;
            if (Math.abs(r4) <= AndroidUtilities.dp(2.0f)) goto L53;
        L39:
            if (EntityView.m11424$$Nest$fgethasTransformed(this.this$0) == false) goto L41;
        L43:
            EntityView.m11437$$Nest$fputhasTransformed(this.this$0, true);
            AndroidUtilities.cancelRunOnUIThread(EntityView.m11426$$Nest$fgetlongPressRunnable(this.this$0));
            int[] r26 = EntityView.m11422$$Nest$fgetdelegate(this.this$0).getCenterLocation(this.this$0);
            float r42 = MathUtils.distance(r26[0], r26[1], EntityView.m11427$$Nest$fgetpreviousLocationX(this.this$0), EntityView.m11428$$Nest$fgetpreviousLocationY(this.this$0));
            float r7 = MathUtils.distance(r26[0], r26[1], r39, r8);
            float r10 = 0.0f;
            if (r42 <= 0.0f) goto L46;
            this.this$0.scale(r7 / r42);
        L46:
            int r43 = this.currentHandle;
            if (r43 != 1) goto L50;
            double r9 = Math.atan2(r26[1] - r8, r26[0] - r39);
        L49:
            r10 = (float) r9;
        L52:
            this.this$0.rotate((float) Math.toDegrees(r10));
            EntityView.m11441$$Nest$fputpreviousLocationX(this.this$0, r39);
            EntityView.m11443$$Nest$fputpreviousLocationY(this.this$0, r8);
            goto L53
        L50:
            if (r43 != 2) goto L52;
            r9 = Math.atan2(r8 - r26[1], r39 - r26[0]);
            goto L49
        L41:
            if (EntityView.m11422$$Nest$fgetdelegate(this.this$0) == null) goto L43;
            EntityView.m11422$$Nest$fgetdelegate(this.this$0).onEntityHandleTouched();
            goto L43
        L60:
            EntityView.m11436$$Nest$fputhadMultitouch(this.this$0, false);
            int r27 = pointInsideHandle(r18.getX(), r18.getY());
            if (r27 == 0) goto L54;
            this.currentHandle = r27;
            EntityView r72 = this.this$0;
            EntityView.m11441$$Nest$fputpreviousLocationX(r72, EntityView.m11432$$Nest$fgetxy(r72)[0]);
            EntityView r73 = this.this$0;
            EntityView.m11443$$Nest$fputpreviousLocationY(r73, EntityView.m11432$$Nest$fgetxy(r73)[1]);
            EntityView.m11439$$Nest$fputpreviousLocationCX(this.this$0, r39);
            EntityView.m11440$$Nest$fputpreviousLocationCY(this.this$0, r8);
            this.this$0.hasReleased = false;
            if ((getParent() instanceof EntitiesContainerView) == false) goto L65;
            ((EntitiesContainerView) getParent()).invalidate();
        L65:
            if (r27 != 3) goto L53;
            if (this.this$0.allowLongPressOnSelected() == false) goto L53;
            AndroidUtilities.runOnUIThread(EntityView.m11426$$Nest$fgetlongPressRunnable(this.this$0), ViewConfiguration.getLongPressTimeout());
            goto L53
        L17:
            EntityView.m11421$$Nest$fgetcxy(r32)[0] = EntityView.m11432$$Nest$fgetxy(this.this$0)[0];
            EntityView.m11421$$Nest$fgetcxy(this.this$0)[1] = EntityView.m11432$$Nest$fgetxy(this.this$0)[1];
            goto L19
        L10:
            if (Build.VERSION.SDK_INT < 29) goto L12;
            EntityView.m11422$$Nest$fgetdelegate(this.this$0).getTransformedTouch(r18.getRawX(1), r18.getRawY(1), EntityView.m11433$$Nest$fgetxy2(this.this$0));
            goto L13
        L12:
            r14 = false;
            goto L14
        L5:
            if (this.currentHandle != 3) goto L7;
            r3 = true;
            goto L8
        }

        public abstract int pointInsideHandle(float r1, float r2);

        public void updatePosition() {
            RectOld r0 = this.this$0.getSelectionBounds();
            FrameLayout.LayoutParams r1 = (FrameLayout.LayoutParams) getLayoutParams();
            r1.leftMargin = (int) r0.x;
            r1.topMargin = (int) r0.y;
            r1.width = (int) r0.width;
            r1.height = (int) r0.height;
            setLayoutParams(r1);
            setRotation(this.this$0.getRotation());
        }
    }

    public static /* synthetic */ void $r8$lambda$1cp5lFRyXYkvy5aXyfAzcuDzOvo(EntityView r0) {
        r0.updateStickyY();
    }

    /* renamed from: $r8$lambda$1shj3mYw7BO-mAFO-kd8u8MQTUE, reason: not valid java name */
    public static /* synthetic */ void m11416$r8$lambda$1shj3mYw7BOmAFOkd8u8MQTUE(EntityView r0, ValueAnimator r1) {
        r0.lambda$rotate$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$2gzyS6m825f2_uoH1pfU6prjLA8(EntityView r0) {
        r0.updateStickyX();
    }

    public static /* synthetic */ void $r8$lambda$B0Zq2HQi06Y23cP9_iwBhLTpTeA(EntityView r0, ValueAnimator r1) {
        r0.lambda$runStickyXAnimator$1(r1);
    }

    /* renamed from: $r8$lambda$GYegdnw5j7dydNN4H-FnNsQideU, reason: not valid java name */
    public static /* synthetic */ void m11417$r8$lambda$GYegdnw5j7dydNN4HFnNsQideU(EntityView r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$MFduqLyG3Dxf0XZlp2xmMGkbJr4(EntityView r0, ValueAnimator r1) {
        r0.lambda$updateTrash$7(r1);
    }

    /* renamed from: $r8$lambda$R_vFuqVNXNEdXiP1K-Wa50PILT8, reason: not valid java name */
    public static /* synthetic */ void m11418$r8$lambda$R_vFuqVNXNEdXiP1KWa50PILT8(EntityView r0, ValueAnimator r1) {
        r0.lambda$runStickyYAnimator$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$exya5FK8lLJisf8HPnnYk9yV9cE(EntityView r0, ValueAnimator r1) {
        r0.lambda$rotate$3(r1);
    }

    /* renamed from: $r8$lambda$uob5a4MhD9ixuOInKZG0z09-Agw, reason: not valid java name */
    public static /* synthetic */ void m11419$r8$lambda$uob5a4MhD9ixuOInKZG0z09Agw(EntityView r0, ValueAnimator r1) {
        r0.lambda$updateSelect$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$yp16Pe_vx6bLZDCUSkZGvXPhHxA(EntityView r0, int r1) {
        r0.lambda$rotate$4(r1);
    }

    /* renamed from: -$$Nest$fgetangleAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m11420$$Nest$fgetangleAnimator(EntityView r0) {
        return r0.angleAnimator;
    }

    /* renamed from: -$$Nest$fgetcxy, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m11421$$Nest$fgetcxy(EntityView r0) {
        return r0.cxy;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EntityViewDelegate m11422$$Nest$fgetdelegate(EntityView r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetfromStickyAngleAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m11423$$Nest$fgetfromStickyAngleAnimator(EntityView r0) {
        return r0.fromStickyAngleAnimator;
    }

    /* renamed from: -$$Nest$fgethasTransformed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11424$$Nest$fgethasTransformed(EntityView r0) {
        return r0.hasTransformed;
    }

    /* renamed from: -$$Nest$fgetlastIsMultitouch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11425$$Nest$fgetlastIsMultitouch(EntityView r0) {
        return r0.lastIsMultitouch;
    }

    /* renamed from: -$$Nest$fgetlongPressRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m11426$$Nest$fgetlongPressRunnable(EntityView r0) {
        return r0.longPressRunnable;
    }

    /* renamed from: -$$Nest$fgetpreviousLocationX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11427$$Nest$fgetpreviousLocationX(EntityView r0) {
        return r0.previousLocationX;
    }

    /* renamed from: -$$Nest$fgetpreviousLocationY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11428$$Nest$fgetpreviousLocationY(EntityView r0) {
        return r0.previousLocationY;
    }

    /* renamed from: -$$Nest$fgetselecting, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11429$$Nest$fgetselecting(EntityView r0) {
        return r0.selecting;
    }

    /* renamed from: -$$Nest$fgetstickyXAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m11430$$Nest$fgetstickyXAnimator(EntityView r0) {
        return r0.stickyXAnimator;
    }

    /* renamed from: -$$Nest$fgetstickyYAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m11431$$Nest$fgetstickyYAnimator(EntityView r0) {
        return r0.stickyYAnimator;
    }

    /* renamed from: -$$Nest$fgetxy, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m11432$$Nest$fgetxy(EntityView r0) {
        return r0.xy;
    }

    /* renamed from: -$$Nest$fgetxy2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m11433$$Nest$fgetxy2(EntityView r0) {
        return r0.xy2;
    }

    /* renamed from: -$$Nest$fputangleAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11434$$Nest$fputangleAnimator(EntityView r0, ValueAnimator r1) {
        r0.angleAnimator = r1;
    }

    /* renamed from: -$$Nest$fputfromStickyAngleAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11435$$Nest$fputfromStickyAngleAnimator(EntityView r0, ValueAnimator r1) {
        r0.fromStickyAngleAnimator = r1;
    }

    /* renamed from: -$$Nest$fputhadMultitouch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11436$$Nest$fputhadMultitouch(EntityView r0, boolean r1) {
        r0.hadMultitouch = r1;
    }

    /* renamed from: -$$Nest$fputhasTransformed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11437$$Nest$fputhasTransformed(EntityView r0, boolean r1) {
        r0.hasTransformed = r1;
    }

    /* renamed from: -$$Nest$fputlastIsMultitouch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11438$$Nest$fputlastIsMultitouch(EntityView r0, boolean r1) {
        r0.lastIsMultitouch = r1;
    }

    /* renamed from: -$$Nest$fputpreviousLocationCX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11439$$Nest$fputpreviousLocationCX(EntityView r0, float r1) {
        r0.previousLocationCX = r1;
    }

    /* renamed from: -$$Nest$fputpreviousLocationCY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11440$$Nest$fputpreviousLocationCY(EntityView r0, float r1) {
        r0.previousLocationCY = r1;
    }

    /* renamed from: -$$Nest$fputpreviousLocationX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11441$$Nest$fputpreviousLocationX(EntityView r0, float r1) {
        r0.previousLocationX = r1;
    }

    /* renamed from: -$$Nest$fputpreviousLocationX2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11442$$Nest$fputpreviousLocationX2(EntityView r0, float r1) {
        r0.previousLocationX2 = r1;
    }

    /* renamed from: -$$Nest$fputpreviousLocationY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11443$$Nest$fputpreviousLocationY(EntityView r0, float r1) {
        r0.previousLocationY = r1;
    }

    /* renamed from: -$$Nest$fputpreviousLocationY2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11444$$Nest$fputpreviousLocationY2(EntityView r0, float r1) {
        r0.previousLocationY2 = r1;
    }

    /* renamed from: -$$Nest$fputstickyAnimatedAngle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11445$$Nest$fputstickyAnimatedAngle(EntityView r0, float r1) {
        r0.stickyAnimatedAngle = r1;
    }

    /* renamed from: -$$Nest$fputstickyXAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11446$$Nest$fputstickyXAnimator(EntityView r0, ValueAnimator r1) {
        r0.stickyXAnimator = r1;
    }

    /* renamed from: -$$Nest$fputstickyYAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11447$$Nest$fputstickyYAnimator(EntityView r0, ValueAnimator r1) {
        r0.stickyYAnimator = r1;
    }

    /* renamed from: -$$Nest$monTouchMove, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11448$$Nest$monTouchMove(EntityView r0, float r1, float r2, boolean r3, float r4, float r5) {
        return r0.onTouchMove(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$monTouchUp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11449$$Nest$monTouchUp(EntityView r0, boolean r1) {
        r0.onTouchUp(r1);
    }

    static {
        STICKY_ANGLES = Arrays.asList(new Integer[]{-90, 0, 90, 180});
    }

    public EntityView(Context r3, PointF r4) {
        super(r3);
        this.bounce = new ButtonBounce(this);
        this.hasPanned = false;
        this.hasReleased = false;
        this.hasTransformed = false;
        this.announcedDrag = false;
        this.announcedMultitouchDrag = false;
        this.announcedSelection = false;
        this.announcedTrash = false;
        this.recognizedLongPress = false;
        this.longPressRunnable = new EntityView$$ExternalSyntheticLambda0(this);
        this.hasStickyAngle = true;
        this.currentStickyAngle = 0;
        this.stickyAngleRunnableValue = -1;
        this.stickyX = 0;
        this.stickyY = 0;
        this.setStickyXRunnable = new EntityView$$ExternalSyntheticLambda1(this);
        this.setStickyYRunnable = new EntityView$$ExternalSyntheticLambda2(this);
        this.xy = new float[2];
        this.xy2 = new float[2];
        this.cxy = new float[2];
        this.scale = 1.0f;
        this.selecting = false;
        this.trashScale = 1.0f;
        this.uuid = UUID.randomUUID();
        this.position = r4;
    }

    private float height() {
        return (float) ((Math.abs(Math.cos((getRotation() / 180.0f) * 3.141592653589793d)) * getMeasuredHeight()) + (Math.abs(Math.sin((getRotation() / 180.0f) * 3.141592653589793d)) * getMeasuredWidth()));
    }

    private /* synthetic */ void lambda$new$0() {
        this.recognizedLongPress = true;
        if (this.delegate != null) goto L9;
        return;
    L9:
        performHapticFeedback(0);     // Catch: Exception -> L8
    L6:
        this.delegate.onEntityLongClicked(this);
    }

    private /* synthetic */ void lambda$rotate$3(ValueAnimator r3) {
        float r32 = AndroidUtilities.lerpAngle(this.angle, this.currentStickyAngle, r3.getAnimatedFraction());
        this.stickyAnimatedAngle = r32;
        rotateInternal(r32);
    }

    private /* synthetic */ void lambda$rotate$4(int r3) {
        this.currentStickyAngle = r3;
        this.hasStickyAngle = true;
        performHapticFeedback(3, 2);     // Catch: Exception -> L12
    L4:
        ValueAnimator r32 = this.angleAnimator;
        if (r32 == null) goto L7;
        r32.cancel();
    L7:
        ValueAnimator r33 = this.fromStickyAngleAnimator;
        if (r33 == null) goto L10;
        r33.cancel();
    L10:
        ValueAnimator r34 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(150);
        this.angleAnimator = r34;
        r34.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.angleAnimator.addUpdateListener(new EntityView$$ExternalSyntheticLambda9(this));
        this.angleAnimator.addListener(new AnonymousClass3(this));
        this.angleAnimator.start();
    }

    private /* synthetic */ void lambda$rotate$5(ValueAnimator r3) {
        rotateInternal(AndroidUtilities.lerpAngle(this.currentStickyAngle, this.angle, this.fromStickyAngleAnimator.getAnimatedFraction()));
    }

    private /* synthetic */ void lambda$runStickyXAnimator$1(ValueAnimator r1) {
        updatePosition();
    }

    private /* synthetic */ void lambda$runStickyYAnimator$2(ValueAnimator r1) {
        updatePosition();
    }

    private /* synthetic */ void lambda$updateSelect$6(ValueAnimator r7) {
        float r72 = ((Float) r7.getAnimatedValue()).floatValue();
        this.selectT = r72;
        SelectionView r0 = this.selectionView;
        if (r0 == null) goto L6;
        r0.setScaleX(AndroidUtilities.lerp(0.9f, 1.0f, r72) * Utilities.clamp(this.trashScale * 1.25f, 1.0f, 0.0f));
        this.selectionView.setScaleY(AndroidUtilities.lerp(0.9f, 1.0f, this.selectT) * Utilities.clamp(this.trashScale * 1.25f, 1.0f, 0.0f));
        this.selectionView.setAlpha((this.selectT * Math.max(0.0f, this.trashScale - 0.8f)) * 5.0f);
        return;
    }

    private /* synthetic */ void lambda$updateTrash$7(ValueAnimator r7) {
        float r72 = ((Float) r7.getAnimatedValue()).floatValue();
        this.trashScale = r72;
        setAlpha(r72);
        SelectionView r73 = this.selectionView;
        if (r73 == null) goto L5;
        r73.setScaleX(AndroidUtilities.lerp(0.9f, 1.0f, this.selectT) * Utilities.clamp(this.trashScale * 1.25f, 1.0f, 0.0f));
        this.selectionView.setScaleY(AndroidUtilities.lerp(0.9f, 1.0f, this.selectT) * Utilities.clamp(this.trashScale * 1.25f, 1.0f, 0.0f));
        this.selectionView.setAlpha((this.selectT * Math.max(0.0f, this.trashScale - 0.8f)) * 5.0f);
    L5:
        invalidate();
    }

    private boolean onTouchMove(float r17, float r18, boolean r19, float r20, float r21) {
        if (getParent() != null) goto L5;
        return false;
    L5:
        float r5 = ((View) getParent()).getScaleX();
        if (r19 == false) goto L8;
        float r8 = (r17 + r20) / 2.0f;
    L9:
        if (r19 == false) goto L11;
        float r9 = (r18 + r21) / 2.0f;
    L12:
        float r10 = (r8 - this.previousLocationCX) / r5;
        float r11 = (r9 - this.previousLocationCY) / r5;
        float r12 = (float) Math.hypot(r10, r11);
        if (this.hasPanned == false) goto L15;
        float r13 = 6.0f;
    L17:
        if (r12 > r13) goto L21;
        if (r19 == true) goto L21;
        return false;
    L21:
        AndroidUtilities.cancelRunOnUIThread(this.longPressRunnable);
        pan(r10, r11);
        if (r19 == false) goto L27;
        float r102 = MathUtils.distance(r17, r18, r20, r21);
        float r112 = MathUtils.distance(this.previousLocationX, this.previousLocationY, this.previousLocationX2, this.previousLocationY2);
        if (r112 <= 0.0f) goto L26;
        scale(r102 / r112);
    L26:
        rotate(this.angle + ((float) Math.toDegrees(Math.atan2(r18 - r21, r17 - r20) - Math.atan2(this.previousLocationY - this.previousLocationY2, this.previousLocationX - this.previousLocationX2))));
    L27:
        this.previousLocationX = r17;
        this.previousLocationY = r18;
        this.previousLocationCX = r8;
        this.previousLocationCY = r9;
        if (r19 == false) goto L30;
        this.previousLocationX2 = r20;
        this.previousLocationY2 = r21;
    L30:
        this.hasPanned = true;
        if ((getParent() instanceof EntitiesContainerView) == false) goto L38;
        if (this.stickyX == 0) goto L35;
    L36:
        ((EntitiesContainerView) getParent()).invalidate();
        goto L38
    L35:
        if (this.stickyY != 0) goto L36;
    L38:
        if (this.announcedDrag == true) goto L43;
        EntityViewDelegate r2 = this.delegate;
        if (r2 == null) goto L43;
        this.announcedDrag = true;
        r2.onEntityDragStart();
    L43:
        if (this.announcedMultitouchDrag == true) goto L49;
        if (r19 == false) goto L49;
        EntityViewDelegate r22 = this.delegate;
        if (r22 == null) goto L49;
        this.announcedMultitouchDrag = true;
        r22.onEntityDragMultitouchStart();
    L49:
        if (this.announcedMultitouchDrag == false) goto L55;
        if (r19 == true) goto L55;
        EntityViewDelegate r23 = this.delegate;
        if (r23 == null) goto L55;
        this.announcedMultitouchDrag = false;
        r23.onEntityDragMultitouchEnd();
    L55:
        if (isSelected() == false) goto L57;
    L61:
        EntityViewDelegate r24 = this.delegate;
        if (r24 != null) goto L64;
    L72:
        EntityViewDelegate r25 = this.delegate;
        if (r25 != null) goto L75;
    L76:
        if (r19 == false) goto L78;
    L80:
        boolean r26 = false;
    L81:
        updateTrash(r26);
        this.bounce.setPressed(false);
        return true;
    L78:
        if (MathUtils.distance(r8, r9, ((View) getParent()).getWidth() / 2.0f, ((View) getParent()).getHeight() - AndroidUtilities.dp(76.0f)) >= AndroidUtilities.dp(32.0f)) goto L80;
        r26 = true;
        goto L81
    L75:
        if (r25.isEntityDeletable() == false) goto L80;
    L64:
        if ((this.position.y - ((getHeight() / 2.0f) * r5)) >= AndroidUtilities.dp(66.0f)) goto L66;
        boolean r3 = true;
    L67:
        r24.onEntityDraggedTop(r3);
        EntityViewDelegate r27 = this.delegate;
        if ((this.position.y + ((getHeight() / 2.0f) * r5)) <= (((View) getParent()).getHeight() - AndroidUtilities.dp(114.0f))) goto L70;
        boolean r32 = true;
    L71:
        r27.onEntityDraggedBottom(r32);
        goto L72
    L70:
        r32 = false;
        goto L71
    L66:
        r3 = false;
        goto L67
    L57:
        if (this.announcedSelection == true) goto L61;
        EntityViewDelegate r28 = this.delegate;
        if (r28 == null) goto L61;
        r28.onEntitySelected(this);
        this.announcedSelection = true;
        goto L61
    L15:
        r13 = 16.0f;
        goto L17
    L11:
        r9 = r18;
        goto L12
    L8:
        r8 = r17;
        goto L9
    }

    private void onTouchUp(boolean r4) {
        if (this.announcedDrag == false) goto L5;
        this.delegate.onEntityDragEnd(this.announcedTrash);
        this.announcedDrag = false;
    L5:
        this.announcedMultitouchDrag = false;
        if (r4 == true) goto L19;
        if (this.recognizedLongPress == true) goto L19;
        if (this.hasPanned == true) goto L19;
        if (this.hasTransformed == true) goto L19;
        if (this.announcedSelection == true) goto L19;
        EntityViewDelegate r42 = this.delegate;
        if (r42 == null) goto L19;
        r42.onEntitySelected(this);
    L19:
        if (this.hasPanned == false) goto L23;
        EntityViewDelegate r43 = this.delegate;
        if (r43 == null) goto L23;
        r43.onEntityDraggedTop(false);
        this.delegate.onEntityDraggedBottom(false);
    L23:
        AndroidUtilities.cancelRunOnUIThread(this.longPressRunnable);
        this.recognizedLongPress = false;
        this.hasPanned = false;
        this.hasTransformed = false;
        this.hasReleased = true;
        this.announcedSelection = false;
        this.stickyAngleRunnableValue = this.currentStickyAngle;
        Runnable r44 = this.setStickyAngleRunnable;
        if (r44 == null) goto L26;
        AndroidUtilities.cancelRunOnUIThread(r44);
        this.setStickyAngleRunnable = null;
    L26:
        this.stickyXRunnableValue = this.stickyX;
        AndroidUtilities.cancelRunOnUIThread(this.setStickyXRunnable);
        this.stickyYRunnableValue = this.stickyY;
        AndroidUtilities.cancelRunOnUIThread(this.setStickyYRunnable);
        if ((getParent() instanceof EntitiesContainerView) == false) goto L30;
        ((EntitiesContainerView) getParent()).invalidate();
        return;
    }

    private void rotateInternal(float r1) {
        setRotation(r1);
        if (this.stickyX == 0) goto L5;
    L6:
        updatePosition();
    L7:
        updateSelectionView();
        return;
    L5:
        if (this.stickyY == 0) goto L7;
        goto L6
    }

    private void runStickyXAnimator(float... r3) {
        ValueAnimator r0 = this.stickyXAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        ValueAnimator r32 = ValueAnimator.ofFloat(r3).setDuration(150);
        this.stickyXAnimator = r32;
        r32.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.stickyXAnimator.addUpdateListener(new EntityView$$ExternalSyntheticLambda7(this));
        this.stickyXAnimator.addListener(new AnonymousClass1(this));
        this.stickyXAnimator.start();
    }

    private void runStickyYAnimator(float... r3) {
        ValueAnimator r0 = this.stickyYAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        ValueAnimator r32 = ValueAnimator.ofFloat(r3).setDuration(150);
        this.stickyYAnimator = r32;
        r32.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.stickyYAnimator.addUpdateListener(new EntityView$$ExternalSyntheticLambda8(this));
        this.stickyYAnimator.addListener(new AnonymousClass2(this));
        this.stickyYAnimator.start();
    }

    private void updateSelect(ViewGroup r4, boolean r5) {
        if (this.selecting == r5) goto L18;
        this.selecting = r5;
        ValueAnimator r0 = this.selectAnimator;
        if (r0 == null) goto L7;
        r0.cancel();
        this.selectAnimator = null;
    L7:
        float r1 = 0.0f;
        if (this.selectionView != null) goto L13;
        if (r5 == true) goto L12;
        if (r4 != null) goto L12;
        return;
    L12:
        SelectionView r02 = createSelectionView();
        this.selectionView = r02;
        r02.hide(this.lastIsMultitouch);
        r4.addView(this.selectionView);
        this.selectT = 0.0f;
    L13:
        this.selectionView.updatePosition();
        float r42 = this.selectT;
        if (r5 == false) goto L16;
        r1 = 1.0f;
    L16:
        ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{r42, r1});
        this.selectAnimator = r43;
        r43.addUpdateListener(new EntityView$$ExternalSyntheticLambda6(this));
        this.selectAnimator.addListener(new AnonymousClass5(this));
        this.selectAnimator.setDuration(280);
        this.selectAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.selectAnimator.start();
        return;
    }

    private void updateStickyX() {
        AndroidUtilities.cancelRunOnUIThread(this.setStickyXRunnable);
        int r0 = this.stickyX;
        int r1 = this.stickyXRunnableValue;
        if (r0 != r1) goto L5;
        return;
    L5:
        this.stickyX = r1;
        if ((getParent() instanceof EntitiesContainerView) == false) goto L8;
        ((EntitiesContainerView) getParent()).invalidate();
    L8:
        ValueAnimator r02 = this.stickyXAnimator;
        if (r02 == null) goto L12;
        r02.cancel();
    L12:
        if (this.stickyXRunnableValue != 0) goto L20;
        runStickyXAnimator(new float[]{1.0f, 0.0f});
        return;
    L20:
        performHapticFeedback(3, 2);     // Catch: Exception -> L19
    L17:
        runStickyXAnimator(new float[]{0.0f, 1.0f});
    }

    private void updateStickyY() {
        AndroidUtilities.cancelRunOnUIThread(this.setStickyYRunnable);
        int r0 = this.stickyY;
        int r1 = this.stickyYRunnableValue;
        if (r0 != r1) goto L5;
        return;
    L5:
        this.stickyY = r1;
        if ((getParent() instanceof EntitiesContainerView) == false) goto L8;
        ((EntitiesContainerView) getParent()).invalidate();
    L8:
        ValueAnimator r02 = this.stickyYAnimator;
        if (r02 == null) goto L12;
        r02.cancel();
    L12:
        if (this.stickyYRunnableValue != 0) goto L20;
        runStickyYAnimator(new float[]{1.0f, 0.0f});
        return;
    L20:
        performHapticFeedback(3, 2);     // Catch: Exception -> L19
    L17:
        runStickyYAnimator(new float[]{0.0f, 1.0f});
    }

    private void updateTrash(boolean r5) {
        if (this.announcedTrash == r5) goto L15;
        ValueAnimator r0 = this.trashAnimator;
        if (r0 == null) goto L7;
        r0.cancel();
        this.trashAnimator = null;
    L7:
        float r02 = this.trashScale;
        if (r5 == false) goto L10;
        float r1 = 0.5f;
    L11:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r1});
        this.trashAnimator = r03;
        r03.addUpdateListener(new EntityView$$ExternalSyntheticLambda5(this));
        this.trashAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.trashAnimator.setDuration(280);
        this.trashAnimator.start();
        this.announcedTrash = r5;
        EntityViewDelegate r4 = this.delegate;
        if (r4 == null) goto L16;
        r4.onEntityDragTrash(r5);
        return;
    L16:
        return;
    L10:
        r1 = 1.0f;
        goto L11
    }

    private float width() {
        return (float) ((Math.abs(Math.cos((getRotation() / 180.0f) * 3.141592653589793d)) * getMeasuredWidth()) + (Math.abs(Math.sin((getRotation() / 180.0f) * 3.141592653589793d)) * getMeasuredHeight()));
    }

    public boolean allowHaptic() {
        return true;
    }

    public boolean allowLongPressOnSelected() {
        return false;
    }

    public abstract SelectionView createSelectionView();

    public void deselect() {
        updateSelect(this.lastSelectionContainer, false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r5) {
        float r0 = this.bounce.getScale(getBounceScale());
        r5.save();
        r5.scale(r0, r0, getWidth() / 2.0f, getHeight() / 2.0f);
        if ((getParent() instanceof View) == false) goto L8;
        View r02 = (View) getParent();
        if (trashCenter() == false) goto L7;
        float r03 = this.trashScale;
        r5.scale(r03, r03, getWidth() / 2.0f, getHeight() / 2.0f);
        goto L8
    L7:
        float r1 = (r02.getWidth() / 2.0f) - getX();
        float r04 = (r02.getHeight() - AndroidUtilities.dp(76.0f)) - getY();
        float r2 = this.trashScale;
        r5.scale(r2, r2, r1, r04);
    L8:
        super.dispatchDraw(r5);
        r5.restore();
    }

    public float getBounceScale() {
        return 0.04f;
    }

    public float getMaxScale() {
        return 100.0f;
    }

    public float getMinScale() {
        return 0.0f;
    }

    public PointF getPosition() {
        return this.position;
    }

    public float getPositionX() {
        float r0 = this.position.x;
        if (getParent() == null) goto L21;
        View r1 = (View) getParent();
        int r2 = this.stickyX;
        if (r2 != 1) goto L8;
        float r12 = AndroidUtilities.dp(8.0f) + (((width() / 2.0f) - getStickyPaddingLeft()) * getScaleX());
    L14:
        ValueAnimator r6 = this.stickyXAnimator;
        if (r6 == null) goto L19;
        return AndroidUtilities.lerp(r0, r12, ((Float) r6.getAnimatedValue()).floatValue());
    L19:
        if (r12 == 0.0f) goto L21;
        return r12;
    L8:
        if (r2 != 2) goto L11;
        r12 = r1.getMeasuredWidth() / 2.0f;
        goto L14
    L11:
        if (r2 != 3) goto L13;
        r12 = (r1.getMeasuredWidth() - AndroidUtilities.dp(8.0f)) - (((width() / 2.0f) + getStickyPaddingRight()) * getScaleX());
        goto L14
    L13:
        r12 = r0;
    L21:
        return r0;
    }

    public float getPositionY() {
        float r0 = this.position.y;
        if (getParent() == null) goto L21;
        View r1 = (View) getParent();
        int r2 = this.stickyY;
        if (r2 != 1) goto L8;
        float r12 = AndroidUtilities.dp(64.0f) + (((height() / 2.0f) - getStickyPaddingTop()) * getScaleY());
    L14:
        ValueAnimator r6 = this.stickyYAnimator;
        if (r6 == null) goto L19;
        return AndroidUtilities.lerp(r0, r12, ((Float) r6.getAnimatedValue()).floatValue());
    L19:
        if (r12 == 0.0f) goto L21;
        return r12;
    L8:
        if (r2 != 2) goto L11;
        r12 = r1.getMeasuredHeight() / 2.0f;
        goto L14
    L11:
        if (r2 != 3) goto L13;
        r12 = (r1.getMeasuredHeight() - AndroidUtilities.dp(64.0f)) - (((height() / 2.0f) + getStickyPaddingBottom()) * getScaleY());
        goto L14
    L13:
        r12 = r0;
    L21:
        return r0;
    }

    public float getScale() {
        return getScaleX();
    }

    public RectOld getSelectionBounds() {
        return new RectOld(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public float getStickyPaddingBottom() {
        return 0.0f;
    }

    public float getStickyPaddingLeft() {
        return 0.0f;
    }

    public float getStickyPaddingRight() {
        return 0.0f;
    }

    public float getStickyPaddingTop() {
        return 0.0f;
    }

    public final int getStickyX() {
        return this.stickyX;
    }

    public final int getStickyY() {
        return this.stickyY;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public boolean hadMultitouch() {
        return this.hadMultitouch;
    }

    public boolean hasPanned() {
        return this.hasPanned;
    }

    public final boolean hasTouchDown() {
        return !this.hasReleased;
    }

    @Override // android.view.View
    public boolean isSelected() {
        return this.selecting;
    }

    public boolean isSelectedProgress() {
        if (isSelected() == false) goto L5;
        return true;
    L5:
        if (this.selectT > 0.0f) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r1) {
        return this.delegate.allowInteraction(this);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r14) {
        if (this.delegate.allowInteraction(this) == true) goto L5;
        return false;
    L5:
        this.delegate.getTransformedTouch(r14.getRawX(), r14.getRawY(), this.xy);
        if (r14.getPointerCount() <= 1) goto L8;
        boolean r0 = true;
    L9:
        if (r0 == true) goto L11;
    L14:
        boolean r10 = r0;
    L15:
        float[] r02 = this.cxy;
        if (r10 == false) goto L18;
        float[] r3 = this.xy;
        float r4 = r3[0];
        float[] r5 = this.xy2;
        r02[0] = (r4 + r5[0]) / 2.0f;
        r02[1] = (r3[1] + r5[1]) / 2.0f;
    L20:
        if (this.lastIsMultitouch == r10) goto L24;
        float[] r03 = this.xy;
        this.previousLocationX = r03[0];
        this.previousLocationY = r03[1];
        float[] r04 = this.xy2;
        this.previousLocationX2 = r04[0];
        this.previousLocationY2 = r04[1];
        float[] r05 = this.cxy;
        this.previousLocationCX = r05[0];
        this.previousLocationCY = r05[1];
        SelectionView r06 = this.selectionView;
        if (r06 == null) goto L24;
        r06.hide(r10);
    L24:
        this.lastIsMultitouch = r10;
        float[] r07 = this.cxy;
        float r32 = r07[0];
        float r08 = r07[1];
        int r42 = r14.getActionMasked();
        if (r42 != 0) goto L27;
        EntityView r7 = this;
        r7.hadMultitouch = false;
        float[] r13 = r7.xy;
        r7.previousLocationX = r13[0];
        r7.previousLocationY = r13[1];
        r7.previousLocationCX = r32;
        r7.previousLocationCY = r08;
        r7.hasReleased = false;
        if ((r7.getParent() instanceof EntitiesContainerView) == true) goto L44;
    L48:
        r7.bounce.setPressed(true);
        AndroidUtilities.cancelRunOnUIThread(r7.longPressRunnable);
        if (r10 == false) goto L50;
    L40:
        boolean r132 = true;
    L51:
        r7.hadMultitouch = r10;
        if (super.onTouchEvent(r14) == true) goto L56;
        if (r132 == true) goto L56;
        return false;
    L56:
        return true;
    L50:
        AndroidUtilities.runOnUIThread(r7.longPressRunnable, ViewConfiguration.getLongPressTimeout());
        goto L40
    L44:
        if (r7.stickyX == 0) goto L46;
    L47:
        ((EntitiesContainerView) r7.getParent()).invalidate();
        goto L48
    L46:
        if (r7.stickyY == 0) goto L48;
    L27:
        if (r42 != 1) goto L29;
    L32:
        r7 = this;
        if (r42 != 3) goto L36;
        boolean r133 = true;
    L37:
        r7.onTouchUp(r133);
        r7.bounce.setPressed(false);
        SelectionView r134 = r7.selectionView;
        if (r134 == null) goto L40;
        r134.hide(false);
        goto L40
    L36:
        r133 = false;
        goto L37
    L29:
        if (r42 == 2) goto L33;
        if (r42 == 3) goto L32;
        r7 = this;
        r132 = false;
        goto L51
    L33:
        float[] r09 = this.xy;
        float r8 = r09[0];
        float r9 = r09[1];
        float[] r010 = this.xy2;
        r7 = this;
        r132 = r7.onTouchMove(r8, r9, r10, r010[0], r010[1]);
        goto L51
    L18:
        float[] r33 = this.xy;
        r02[0] = r33[0];
        r02[1] = r33[1];
        goto L20
    L11:
        if (Build.VERSION.SDK_INT < 29) goto L13;
        this.delegate.getTransformedTouch(r14.getRawX(1), r14.getRawY(1), this.xy2);
        goto L14
    L13:
        r10 = false;
        goto L15
    L8:
        r0 = false;
        goto L9
    }

    public void pan(float r10, float r11) {
        PointF r0 = this.position;
        r0.x += r10;
        r0.y += r11;
        if (((View) getParent()) == null) goto L42;
        int r02 = 3;
        if (this.lastIsMultitouch == false) goto L7;
    L17:
        int r112 = 0;
    L19:
        if (this.stickyXRunnableValue == r112) goto L25;
        this.stickyXRunnableValue = r112;
        if (r112 != 0) goto L23;
        updateStickyX();
        goto L25
    L23:
        AndroidUtilities.runOnUIThread(this.setStickyXRunnable, 250);
    L25:
        if (this.lastIsMultitouch == false) goto L27;
    L35:
        r02 = 0;
    L37:
        if (this.stickyYRunnableValue == r02) goto L42;
        this.stickyYRunnableValue = r02;
        if (r02 != 0) goto L41;
        updateStickyY();
        goto L42
    L41:
        AndroidUtilities.runOnUIThread(this.setStickyYRunnable, 250);
        goto L42
    L27:
        if (Math.abs(this.position.y - (r10.getMeasuredHeight() / 2.0f)) > AndroidUtilities.dp(12.0f)) goto L30;
        r02 = 2;
        goto L37
    L30:
        if (Math.abs((this.position.y - (((height() / 2.0f) + getStickyPaddingTop()) * getScaleY())) - AndroidUtilities.dp(64.0f)) > AndroidUtilities.dp(12.0f)) goto L33;
        r02 = 1;
        goto L37
    L33:
        if (Math.abs((this.position.y + (((height() / 2.0f) - getStickyPaddingBottom()) * getScaleY())) - (r10.getMeasuredHeight() - AndroidUtilities.dp(64.0f))) > AndroidUtilities.dp(12.0f)) goto L35;
    L7:
        if (Math.abs(this.position.x - (r10.getMeasuredWidth() / 2.0f)) > AndroidUtilities.dp(12.0f)) goto L12;
        if (this.position.y >= (r10.getMeasuredHeight() - AndroidUtilities.dp(112.0f))) goto L12;
        r112 = 2;
    L12:
        if (Math.abs((this.position.x - (((width() / 2.0f) + getStickyPaddingLeft()) * getScaleX())) - AndroidUtilities.dp(8.0f)) > AndroidUtilities.dp(12.0f)) goto L15;
        r112 = 1;
        goto L19
    L15:
        if (Math.abs((this.position.x + (((width() / 2.0f) - getStickyPaddingRight()) * getScaleX())) - (r10.getMeasuredWidth() - AndroidUtilities.dp(8.0f))) > AndroidUtilities.dp(12.0f)) goto L17;
        r112 = 3;
    L42:
        updatePosition();
    }

    public void rotate(float r5) {
        if (this.stickyX == 0) goto L6;
        this.stickyXRunnableValue = 0;
        updateStickyX();
    L6:
        if (this.stickyY == 0) goto L8;
        this.stickyYRunnableValue = 0;
        updateStickyY();
    L8:
        this.angle = r5;
        boolean r0 = this.hasStickyAngle;
        if (r0 == false) goto L11;
    L23:
        if (r0 == true) goto L25;
    L43:
        ValueAnimator r02 = this.fromStickyAngleAnimator;
        if (r02 == null) goto L46;
        this.fromStickyToAngle = r5;
        r5 = AndroidUtilities.lerpAngle(this.fromStickyAnimatedAngle, r5, r02.getAnimatedFraction());
    L46:
        rotateInternal(r5);
        return;
    L25:
        if (Math.abs(this.currentStickyAngle - r5) < 12.0f) goto L27;
    L33:
        this.stickyAngleRunnableValue = -1;
        Runnable r03 = this.setStickyAngleRunnable;
        if (r03 == null) goto L36;
        AndroidUtilities.cancelRunOnUIThread(r03);
        this.setStickyAngleRunnable = null;
    L36:
        ValueAnimator r04 = this.angleAnimator;
        if (r04 == null) goto L39;
        r04.cancel();
    L39:
        ValueAnimator r05 = this.fromStickyAngleAnimator;
        if (r05 == null) goto L42;
        r05.cancel();
    L42:
        ValueAnimator r06 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(150);
        this.fromStickyAngleAnimator = r06;
        r06.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.fromStickyAngleAnimator.addUpdateListener(new EntityView$$ExternalSyntheticLambda4(this));
        this.fromStickyAngleAnimator.addListener(new AnonymousClass4(this));
        this.fromStickyAngleAnimator.start();
        this.hasStickyAngle = false;
        goto L43
    L27:
        if (this.lastIsMultitouch == true) goto L33;
        if (this.angleAnimator == null) goto L32;
        r5 = this.stickyAnimatedAngle;
        goto L43
    L32:
        r5 = this.currentStickyAngle;
        goto L43
    L11:
        if (this.lastIsMultitouch == true) goto L23;
        Iterator<Integer> r07 = STICKY_ANGLES.iterator();
    L14:
        if (r07.hasNext() == false) goto L43;
        int r1 = r07.next().intValue();
        if (Math.abs(r1 - r5) >= 4.0f) goto L14;
        if (this.stickyAngleRunnableValue == r1) goto L43;
        this.stickyAngleRunnableValue = r1;
        Runnable r08 = this.setStickyAngleRunnable;
        if (r08 == null) goto L22;
        AndroidUtilities.cancelRunOnUIThread(r08);
    L22:
        EntityView$$ExternalSyntheticLambda3 r09 = new EntityView$$ExternalSyntheticLambda3(this, r1);
        this.setStickyAngleRunnable = r09;
        AndroidUtilities.runOnUIThread(r09, 250);
        goto L43
    }

    public void scale(float r6) {
        float r0 = this.scale;
        float r62 = r6 * r0;
        this.scale = r62;
        float r63 = Utilities.clamp(Math.max(r62, 0.1f), getMaxScale(), getMinScale());
        if (allowHaptic() == false) goto L19;
        boolean r2 = false;
        if (r63 < getMaxScale()) goto L7;
    L10:
        boolean r1 = true;
    L12:
        if (r0 < getMaxScale()) goto L14;
    L15:
        r2 = true;
    L16:
        if (r1 == r2) goto L19;
        performHapticFeedback(3, 1);     // Catch: Exception -> L21
        goto L19
    L14:
        if (r0 > getMinScale()) goto L16;
    L7:
        if (r63 <= getMinScale()) goto L10;
        r1 = false;
    L19:
        setScaleX(r63);
        setScaleY(r63);
    }

    public void select(ViewGroup r2) {
        this.lastSelectionContainer = r2;
        updateSelect(r2, true);
    }

    public void setDelegate(EntityViewDelegate r1) {
        this.delegate = r1;
    }

    public void setIsVideo(boolean r1) {
    }

    public void setPosition(PointF r1) {
        this.position = r1;
        updatePosition();
    }

    public void setScale(float r1) {
        this.scale = r1;
        setScaleX(r1);
        setScaleY(r1);
    }

    public void setSelectionVisibility(boolean r1) {
        SelectionView r0 = this.selectionView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r1 == false) goto L7;
        int r12 = 0;
    L8:
        r0.setVisibility(r12);
        return;
    L7:
        r12 = 8;
        goto L8
    }

    public void setStickyX(int r1) {
        this.stickyXRunnableValue = r1;
        this.stickyX = r1;
    }

    public void setStickyY(int r1) {
        this.stickyYRunnableValue = r1;
        this.stickyY = r1;
    }

    public boolean trashCenter() {
        return false;
    }

    public void updatePosition() {
        setX(getPositionX() - (getMeasuredWidth() / 2.0f));
        setY(getPositionY() - (getMeasuredHeight() / 2.0f));
        updateSelectionView();
    }

    public void updateSelectionView() {
        SelectionView r0 = this.selectionView;
        if (r0 == null) goto L6;
        r0.updatePosition();
        return;
    }
}
