package org.telegram.ui.Components.Crop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.BubbleActivity;
import org.telegram.ui.Components.Crop.CropAreaView;
import org.telegram.ui.Components.Crop.CropGestureDetector;
import org.telegram.ui.Components.PaintingOverlay;
import org.telegram.ui.Components.VideoEditTextureView;

/* loaded from: classes7.dex */
public class CropView extends FrameLayout implements CropAreaView.AreaViewListener, CropGestureDetector.CropGestureListener {
    private boolean animating;
    public CropAreaView areaView;
    private Bitmap bitmap;
    protected int bitmapRotation;
    public float bottomPadding;
    RectF cropRect;
    private CropTransform cropTransform;
    private CropGestureDetector detector;
    private boolean freeform;
    private boolean hasAspectRatioDialog;
    private ImageView imageView;
    private boolean inBubbleMode;
    private RectF initialAreaRect;
    private boolean isVisible;
    private CropViewListener listener;
    private Matrix overlayMatrix;
    private PaintingOverlay paintingOverlay;
    private RectF previousAreaRect;
    private float rotationStartScale;
    RectF sizeRect;
    public CropState state;
    private Matrix tempMatrix;
    private CropRectangle tempRect;
    public float topPadding;
    float[] values;
    private VideoEditTextureView videoEditTextureView;

    /* renamed from: org.telegram.ui.Components.Crop.CropView$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ CropView this$0;
        final /* synthetic */ int val$h;
        final /* synthetic */ MediaController.CropState val$restoreState;
        final /* synthetic */ int val$w;

        public AnonymousClass1(CropView r1, MediaController.CropState r2, int r3, int r4) {
            this.this$0 = r1;
            this.val$restoreState = r2;
            this.val$h = r3;
            this.val$w = r4;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.this$0.reset();
            MediaController.CropState r0 = this.val$restoreState;
            if (r0 == null) goto L29;
            float r02 = r0.lockedAspectRatio;
            boolean r3 = true;
            if (r02 <= 1.0E-4f) goto L9;
            this.this$0.areaView.setLockedAspectRatio(r02);
            if (CropView.m10073$$Nest$fgetlistener(this.this$0) == null) goto L9;
            CropView.m10073$$Nest$fgetlistener(this.this$0).onAspectLock(true);
        L9:
            this.this$0.setFreeform(this.val$restoreState.freeform);
            float r03 = this.this$0.areaView.getAspectRatio();
            int r2 = this.val$restoreState.transformRotation;
            if (r2 != 90) goto L12;
        L15:
            r03 = 1.0f / r03;
            CropState r4 = this.this$0.state;
            float r6 = r4.height;
            float r42 = r4.width;
            int r7 = this.val$h;
            int r8 = this.val$w;
        L16:
            CropView.m10072$$Nest$fgetfreeform(this.this$0);
            if (CropView.m10072$$Nest$fgetfreeform(this.this$0) == true) goto L19;
        L21:
            CropView r5 = this.this$0;
            CropAreaView r9 = r5.areaView;
            int r52 = r5.getCurrentWidth();
            int r11 = this.this$0.getCurrentHeight();
            if (((r2 + CropState.m10077$$Nest$mgetBaseRotation(this.this$0.state)) % 180.0f) != 0.0f) goto L25;
            r3 = false;
        L25:
            r9.setBitmap(r52, r11, r3, CropView.m10072$$Nest$fgetfreeform(this.this$0));
        L26:
            CropState.m10092$$Nest$mreset(this.this$0.state, r2);
            CropAreaView r22 = this.this$0.areaView;
            MediaController.CropState r32 = this.val$restoreState;
            r22.setActualRect((r03 * r32.cropPw) / r32.cropPh);
            CropState r04 = this.this$0.state;
            MediaController.CropState r23 = this.val$restoreState;
            r04.mirrored = r23.mirrored;
            CropState.m10093$$Nest$mrotate(r04, r23.cropRotate, 0.0f, 0.0f);
            CropState r05 = this.this$0.state;
            MediaController.CropState r24 = this.val$restoreState;
            float r33 = r24.cropPx * r7;
            float r53 = r05.minimumScale;
            CropState.m10095$$Nest$mtranslate(r05, r33 * r53, (r24.cropPy * r8) * r53);
            float r06 = Math.max(this.this$0.areaView.getCropWidth() / r6, this.this$0.areaView.getCropHeight() / r42);
            CropState r25 = this.this$0.state;
            CropState.m10094$$Nest$mscale(r25, this.val$restoreState.cropScale * (r06 / r25.minimumScale), 0.0f, 0.0f);
            this.this$0.updateMatrix();
            if (CropView.m10073$$Nest$fgetlistener(this.this$0) == null) goto L29;
            CropView.m10073$$Nest$fgetlistener(this.this$0).onChange(false);
            goto L29
        L19:
            if (this.this$0.areaView.getLockAspectRatio() <= 0.0f) goto L21;
            CropAreaView r34 = this.this$0.areaView;
            r34.setLockedAspectRatio(1.0f / r34.getLockAspectRatio());
            CropAreaView r35 = this.this$0.areaView;
            r35.setActualRect(r35.getLockAspectRatio());
            goto L26
        L12:
            if (r2 == 270) goto L15;
            CropState r43 = this.this$0.state;
            r6 = r43.width;
            r42 = r43.height;
            r7 = this.val$w;
            r8 = this.val$h;
        L29:
            this.this$0.areaView.getViewTreeObserver().removeOnPreDrawListener(this);
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.Crop.CropView$2, reason: invalid class name */
    public class AnonymousClass2 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ CropView this$0;
        final /* synthetic */ int val$h;
        final /* synthetic */ MediaController.CropState val$restoreState;
        final /* synthetic */ int val$w;

        public AnonymousClass2(CropView r1, MediaController.CropState r2, int r3, int r4) {
            this.this$0 = r1;
            this.val$restoreState = r2;
            this.val$h = r3;
            this.val$w = r4;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.this$0.reset();
            MediaController.CropState r0 = this.val$restoreState;
            if (r0 == null) goto L29;
            float r02 = r0.lockedAspectRatio;
            boolean r3 = true;
            if (r02 <= 1.0E-4f) goto L9;
            this.this$0.areaView.setLockedAspectRatio(r02);
            if (CropView.m10073$$Nest$fgetlistener(this.this$0) == null) goto L9;
            CropView.m10073$$Nest$fgetlistener(this.this$0).onAspectLock(true);
        L9:
            this.this$0.setFreeform(this.val$restoreState.freeform);
            float r03 = this.this$0.areaView.getAspectRatio();
            int r2 = this.val$restoreState.transformRotation;
            if (r2 != 90) goto L12;
        L15:
            r03 = 1.0f / r03;
            CropState r4 = this.this$0.state;
            float r6 = r4.height;
            float r42 = r4.width;
            int r7 = this.val$h;
            int r8 = this.val$w;
        L16:
            CropView.m10072$$Nest$fgetfreeform(this.this$0);
            if (CropView.m10072$$Nest$fgetfreeform(this.this$0) == true) goto L19;
        L21:
            CropView r5 = this.this$0;
            CropAreaView r9 = r5.areaView;
            int r52 = r5.getCurrentWidth();
            int r11 = this.this$0.getCurrentHeight();
            if (((r2 + CropState.m10077$$Nest$mgetBaseRotation(this.this$0.state)) % 180.0f) != 0.0f) goto L25;
            r3 = false;
        L25:
            r9.setBitmap(r52, r11, r3, CropView.m10072$$Nest$fgetfreeform(this.this$0));
        L26:
            CropState.m10092$$Nest$mreset(this.this$0.state, r2);
            CropAreaView r22 = this.this$0.areaView;
            MediaController.CropState r32 = this.val$restoreState;
            r22.setActualRect((r03 * r32.cropPw) / r32.cropPh);
            CropState r04 = this.this$0.state;
            MediaController.CropState r23 = this.val$restoreState;
            r04.mirrored = r23.mirrored;
            CropState.m10093$$Nest$mrotate(r04, r23.cropRotate, 0.0f, 0.0f);
            CropState r05 = this.this$0.state;
            MediaController.CropState r24 = this.val$restoreState;
            float r33 = r24.cropPx * r7;
            float r53 = r05.minimumScale;
            CropState.m10095$$Nest$mtranslate(r05, r33 * r53, (r24.cropPy * r8) * r53);
            float r06 = Math.max(this.this$0.areaView.getCropWidth() / r6, this.this$0.areaView.getCropHeight() / r42);
            CropState r25 = this.this$0.state;
            CropState.m10094$$Nest$mscale(r25, this.val$restoreState.cropScale * (r06 / r25.minimumScale), 0.0f, 0.0f);
            this.this$0.updateMatrix();
            if (CropView.m10073$$Nest$fgetlistener(this.this$0) == null) goto L29;
            CropView.m10073$$Nest$fgetlistener(this.this$0).onChange(false);
            goto L29
        L19:
            if (this.this$0.areaView.getLockAspectRatio() <= 0.0f) goto L21;
            CropAreaView r34 = this.this$0.areaView;
            r34.setLockedAspectRatio(1.0f / r34.getLockAspectRatio());
            CropAreaView r35 = this.this$0.areaView;
            r35.setActualRect(r35.getLockAspectRatio());
            goto L26
        L12:
            if (r2 == 270) goto L15;
            CropState r43 = this.this$0.state;
            r6 = r43.width;
            r42 = r43.height;
            r7 = this.val$w;
            r8 = this.val$h;
        L29:
            this.this$0.areaView.getViewTreeObserver().removeOnPreDrawListener(this);
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.Crop.CropView$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ CropView this$0;
        final /* synthetic */ boolean val$animEnsureFit;

        public AnonymousClass3(CropView r1, boolean r2) {
            this.this$0 = r1;
            this.val$animEnsureFit = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (this.val$animEnsureFit == false) goto L6;
            CropView.m10075$$Nest$mfitContentInBounds(this.this$0, false, false, true);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Crop.CropView$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ CropView this$0;
        final /* synthetic */ boolean val$allowScale;
        final /* synthetic */ boolean val$animated;
        final /* synthetic */ boolean val$fast;
        final /* synthetic */ boolean val$maximize;

        public AnonymousClass4(CropView r1, boolean r2, boolean r3, boolean r4, boolean r5) {
            this.this$0 = r1;
            this.val$fast = r2;
            this.val$allowScale = r3;
            this.val$maximize = r4;
            this.val$animated = r5;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            CropView.m10074$$Nest$fputanimating(this.this$0, false);
            if (this.val$fast == true) goto L6;
            CropView.m10076$$Nest$mfitContentInBounds(this.this$0, this.val$allowScale, this.val$maximize, this.val$animated, true);
            return;
        }
    }

    public static class CropRectangle {
        float[] coords;

        public CropRectangle() {
            this.coords = new float[8];
        }

        public void applyMatrix(Matrix r1) {
            r1.mapPoints(this.coords);
        }

        public void getRect(RectF r5) {
            float[] r4 = this.coords;
            r5.set(r4[0], r4[1], r4[2], r4[7]);
        }

        public void setRect(RectF r5) {
            float[] r4 = this.coords;
            float r0 = r5.left;
            r4[0] = r0;
            float r1 = r5.top;
            r4[1] = r1;
            float r2 = r5.right;
            r4[2] = r2;
            r4[3] = r1;
            r4[4] = r2;
            float r52 = r5.bottom;
            r4[5] = r52;
            r4[6] = r0;
            r4[7] = r52;
        }
    }

    public class CropState {
        public float baseRotation;
        public float height;
        public Matrix matrix;
        public float minimumScale;
        public boolean mirrored;
        public float orientation;
        public float rotation;
        public float scale;
        final /* synthetic */ CropView this$0;
        public float width;
        public float x;
        public float y;

        /* renamed from: -$$Nest$mgetBaseRotation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m10077$$Nest$mgetBaseRotation(CropState r0) {
            return r0.getBaseRotation();
        }

        /* renamed from: -$$Nest$mgetConcatMatrix, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10078$$Nest$mgetConcatMatrix(CropState r0, Matrix r1) {
            r0.getConcatMatrix(r1);
        }

        /* renamed from: -$$Nest$mgetHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m10079$$Nest$mgetHeight(CropState r0) {
            return r0.getHeight();
        }

        /* renamed from: -$$Nest$mgetMatrix, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Matrix m10080$$Nest$mgetMatrix(CropState r0) {
            return r0.getMatrix();
        }

        /* renamed from: -$$Nest$mgetOrientation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m10081$$Nest$mgetOrientation(CropState r0) {
            return r0.getOrientation();
        }

        /* renamed from: -$$Nest$mgetOrientationOnly, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m10082$$Nest$mgetOrientationOnly(CropState r0) {
            return r0.getOrientationOnly();
        }

        /* renamed from: -$$Nest$mgetOrientedHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m10083$$Nest$mgetOrientedHeight(CropState r0) {
            return r0.getOrientedHeight();
        }

        /* renamed from: -$$Nest$mgetOrientedWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m10084$$Nest$mgetOrientedWidth(CropState r0) {
            return r0.getOrientedWidth();
        }

        /* renamed from: -$$Nest$mgetRotation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m10085$$Nest$mgetRotation(CropState r0) {
            return r0.getRotation();
        }

        /* renamed from: -$$Nest$mgetScale, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m10086$$Nest$mgetScale(CropState r0) {
            return r0.getScale();
        }

        /* renamed from: -$$Nest$mgetWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m10087$$Nest$mgetWidth(CropState r0) {
            return r0.getWidth();
        }

        /* renamed from: -$$Nest$mgetX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m10088$$Nest$mgetX(CropState r0) {
            return r0.getX();
        }

        /* renamed from: -$$Nest$mgetY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m10089$$Nest$mgetY(CropState r0) {
            return r0.getY();
        }

        /* renamed from: -$$Nest$mhasChanges, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m10090$$Nest$mhasChanges(CropState r0) {
            return r0.hasChanges();
        }

        /* renamed from: -$$Nest$mmirror, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10091$$Nest$mmirror(CropState r0) {
            r0.mirror();
        }

        /* renamed from: -$$Nest$mreset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10092$$Nest$mreset(CropState r0, float r1) {
            r0.reset(r1);
        }

        /* renamed from: -$$Nest$mrotate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10093$$Nest$mrotate(CropState r0, float r1, float r2, float r3) {
            r0.rotate(r1, r2, r3);
        }

        /* renamed from: -$$Nest$mscale, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10094$$Nest$mscale(CropState r0, float r1, float r2, float r3) {
            r0.scale(r1, r2, r3);
        }

        /* renamed from: -$$Nest$mtranslate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10095$$Nest$mtranslate(CropState r0, float r1, float r2) {
            r0.translate(r1, r2);
        }

        /* renamed from: -$$Nest$mupdate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10096$$Nest$mupdate(CropState r0, int r1, int r2, int r3) {
            r0.update(r1, r2, r3);
        }

        private CropState(CropView r1, int r2, int r3, int r4) {
            this.this$0 = r1;
            this.width = r2;
            this.height = r3;
            this.x = 0.0f;
            this.y = 0.0f;
            this.scale = 1.0f;
            this.baseRotation = r4;
            this.rotation = 0.0f;
            this.matrix = new Matrix();
        }

        public /* synthetic */ CropState(CropView r1, int r2, int r3, int r4, CropViewIA r5) {
            this(r1, r2, r3, r4);
        }

        private float getBaseRotation() {
            return this.baseRotation;
        }

        private void getConcatMatrix(Matrix r1) {
            r1.postConcat(this.matrix);
        }

        private float getHeight() {
            return this.height;
        }

        private Matrix getMatrix() {
            Matrix r0 = new Matrix();
            r0.set(this.matrix);
            return r0;
        }

        private float getOrientation() {
            return this.orientation + this.baseRotation;
        }

        private int getOrientationOnly() {
            return (int) this.orientation;
        }

        private float getOrientedHeight() {
            if (((this.orientation + this.baseRotation) % 180.0f) == 0.0f) goto L7;
            return this.width;
        L7:
            return this.height;
        }

        private float getOrientedWidth() {
            if (((this.orientation + this.baseRotation) % 180.0f) == 0.0f) goto L7;
            return this.height;
        L7:
            return this.width;
        }

        private float getRotation() {
            return this.rotation;
        }

        private float getScale() {
            return this.scale;
        }

        private float getWidth() {
            return this.width;
        }

        private float getX() {
            return this.x;
        }

        private float getY() {
            return this.y;
        }

        private boolean hasChanges() {
            if (Math.abs(this.x) <= 1.0E-5f) goto L5;
            return true;
        L5:
            if (Math.abs(this.y) <= 1.0E-5f) goto L7;
            return true;
        L7:
            if (Math.abs(this.scale - this.minimumScale) <= 1.0E-5f) goto L9;
            return true;
        L9:
            if (Math.abs(this.rotation) <= 1.0E-5f) goto L11;
            return true;
        L11:
            if (Math.abs(this.orientation) > 1.0E-5f) goto L20;
            return false;
        L20:
            return true;
        }

        private void mirror() {
            this.mirrored = !this.mirrored;
        }

        private void reset(float r2) {
            this.matrix.reset();
            this.x = 0.0f;
            this.y = 0.0f;
            this.rotation = 0.0f;
            this.orientation = r2;
            updateMinimumScale();
            float r22 = this.minimumScale;
            this.scale = r22;
            this.matrix.postScale(r22, r22);
        }

        private void rotate(float r2, float r3, float r4) {
            this.rotation += r2;
            this.matrix.postRotate(r2, r3, r4);
        }

        private void scale(float r2, float r3, float r4) {
            this.scale *= r2;
            this.matrix.postScale(r2, r2, r3, r4);
        }

        private void translate(float r2, float r3) {
            this.x += r2;
            this.y += r3;
            this.matrix.postTranslate(r2, r3);
        }

        private void update(int r2, int r3, int r4) {
            float r22 = r2;
            this.scale *= this.width / r22;
            this.width = r22;
            this.height = r3;
            updateMinimumScale();
            this.matrix.getValues(this.this$0.values);
            this.matrix.reset();
            Matrix r23 = this.matrix;
            float r32 = this.scale;
            r23.postScale(r32, r32);
            Matrix r24 = this.matrix;
            float[] r33 = this.this$0.values;
            r24.postTranslate(r33[2], r33[5]);
            this.this$0.updateMatrix();
        }

        private void updateMinimumScale() {
            float r0 = this.orientation;
            float r1 = this.baseRotation;
            if (((r0 + r1) % 180.0f) == 0.0f) goto L5;
            float r2 = this.height;
        L7:
            if (((r0 + r1) % 180.0f) == 0.0f) goto L9;
            float r02 = this.width;
        L10:
            boolean r12 = CropView.m10072$$Nest$fgetfreeform(this.this$0);
            CropView r3 = this.this$0;
            if (r12 == false) goto L14;
            this.minimumScale = r3.areaView.getCropWidth() / r2;
            return;
        L14:
            this.minimumScale = Math.max(r3.areaView.getCropWidth() / r2, this.this$0.areaView.getCropHeight() / r02);
            return;
        L9:
            r02 = this.height;
            goto L10
        L5:
            r2 = this.width;
            goto L7
        }
    }

    public interface CropViewListener {
        void onAspectLock(boolean r1);

        void onChange(boolean r1);

        void onTapUp();

        void onUpdate();
    }

    public static /* synthetic */ void $r8$lambda$61hhOaH3oUOv76EiOlrGko4yR9w(CropView r0, Integer[][] r1, DialogInterface r2, int r3) {
        r0.lambda$showAspectRatioDialog$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$MGQeRtzV3TGSPGLGL4aibAouXXI(CropView r0, DialogInterface r1) {
        r0.lambda$showAspectRatioDialog$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$Xe3zfnDdO5peErya27RwbcmZ64A(CropView r0, float r1, float[] r2, float r3, float r4, ValueAnimator r5) {
        r0.lambda$fitContentInBounds$1(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$fLpZCcINvMUUGo-4WACq6okahyY, reason: not valid java name */
    public static /* synthetic */ void m10071$r8$lambda$fLpZCcINvMUUGo4WACq6okahyY(CropView r0, RectF r1, RectF r2, float r3, float r4, float r5, float r6, float r7, ValueAnimator r8) {
        r0.lambda$maximize$2(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$xQPZNuqbVsF22dXkxlEM98ow1HQ(CropView r0, float r1, float[] r2, float r3, float r4, ValueAnimator r5) {
        r0.lambda$fillAreaView$0(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$fgetfreeform, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10072$$Nest$fgetfreeform(CropView r0) {
        return r0.freeform;
    }

    /* renamed from: -$$Nest$fgetlistener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CropViewListener m10073$$Nest$fgetlistener(CropView r0) {
        return r0.listener;
    }

    /* renamed from: -$$Nest$fputanimating, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10074$$Nest$fputanimating(CropView r0, boolean r1) {
        r0.animating = r1;
    }

    /* renamed from: -$$Nest$mfitContentInBounds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10075$$Nest$mfitContentInBounds(CropView r0, boolean r1, boolean r2, boolean r3) {
        r0.fitContentInBounds(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mfitContentInBounds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10076$$Nest$mfitContentInBounds(CropView r0, boolean r1, boolean r2, boolean r3, boolean r4) {
        r0.fitContentInBounds(r1, r2, r3, r4);
    }

    public CropView(Context r4) {
        super(r4);
        this.values = new float[9];
        this.cropRect = new RectF();
        this.sizeRect = new RectF(0.0f, 0.0f, 1280.0f, 1280.0f);
        this.inBubbleMode = r4 instanceof BubbleActivity;
        this.previousAreaRect = new RectF();
        this.initialAreaRect = new RectF();
        this.overlayMatrix = new Matrix();
        this.tempRect = new CropRectangle();
        this.tempMatrix = new Matrix();
        this.animating = false;
        ImageView r0 = new ImageView(r4);
        this.imageView = r0;
        r0.setScaleType(ImageView.ScaleType.MATRIX);
        addView(this.imageView);
        CropGestureDetector r02 = new CropGestureDetector(r4);
        this.detector = r02;
        r02.setOnGestureListener(this);
        CropAreaView r03 = new CropAreaView(r4);
        this.areaView = r03;
        r03.setListener(this);
        addView(this.areaView);
    }

    public static void editBitmap(Context r17, String r18, Bitmap r19, Canvas r20, Bitmap r21, Bitmap.CompressFormat r22, Matrix r23, int r24, int r25, float r26, float r27, float r28, float r29, boolean r30, ArrayList<VideoEditedInfo.MediaEntity> r31, boolean r32) {
        Bitmap r0 = r21;
        int r1 = r24;
        boolean r6 = false;
        if (r32 == true) goto L34;
    L5:
        if (r19 != null) goto L7;
        Bitmap r7 = BitmapFactory.decodeFile(r18);     // Catch: Throwable -> L31
    L8:
        float r8 = Math.max(r7.getWidth(), r7.getHeight()) / Math.max(r24, r25);     // Catch: Throwable -> L31
        Matrix r9 = new Matrix();     // Catch: Throwable -> L31
        char r11 = 2;
        r9.postTranslate((-r7.getWidth()) / 2, (-r7.getHeight()) / 2);     // Catch: Throwable -> L31
        if (r30 == false) goto L11;
        r9.postScale(-1.0f, 1.0f);     // Catch: Throwable -> L31
    L11:
        float r10 = 1.0f / r8;     // Catch: Throwable -> L31
        r9.postScale(r10, r10);     // Catch: Throwable -> L31
        r9.postRotate(r28);     // Catch: Throwable -> L31
        r9.postConcat(r23);     // Catch: Throwable -> L31
        r9.postScale(r29, r29);     // Catch: Throwable -> L31
        r9.postTranslate(r0.getWidth() / 2, r0.getHeight() / 2);     // Catch: Throwable -> L31
        r20.drawBitmap(r7, r9, new Paint(2));     // Catch: Throwable -> L31
        FileOutputStream r82 = new FileOutputStream(new File(r18));     // Catch: Throwable -> L31
        r0.compress(r22, 87, r82);     // Catch: Throwable -> L31
        r82.close();     // Catch: Throwable -> L31
        if (r31 != null) goto L14;
    L29:
        r7.recycle();     // Catch: Throwable -> L31
        return;
    L31:
        th = move-exception;
        FileLog.e(th);
        return;
    L14:
        if (r31.isEmpty() == true) goto L29;
        int r83 = r31.size();     // Catch: Throwable -> L31
        int r102 = 0;
    L16:
        if (r102 >= r83) goto L29;
        VideoEditedInfo.MediaEntity r12 = r31.get(r102);     // Catch: Throwable -> L31
        float r13 = (r12.x + (r12.width / 2.0f)) * r7.getWidth();     // Catch: Throwable -> L31
        boolean r172 = r6;
        float r14 = (r12.y + (r12.height / 2.0f)) * r7.getHeight();     // Catch: Throwable -> L31
        char r192 = r11;
        float r62 = r12.textViewX * r7.getWidth();     // Catch: Throwable -> L31
        float r112 = r12.textViewY * r7.getHeight();     // Catch: Throwable -> L31
        float[] r15 = new float[4];     // Catch: Throwable -> L31
        r15[r172 ? 1 : 0] = r13;     // Catch: Throwable -> L31
        r15[1] = r14;     // Catch: Throwable -> L31
        r15[r192] = r62;     // Catch: Throwable -> L31
        r15[3] = r112;     // Catch: Throwable -> L31
        r9.mapPoints(r15);     // Catch: Throwable -> L31
        int r113 = r7.getWidth();     // Catch: Throwable -> L31
        int r142 = r7.getHeight();     // Catch: Throwable -> L31
        if (r28 != 90.0f) goto L20;
    L23:
        r113 = r7.getHeight();     // Catch: Throwable -> L31
        r142 = r7.getWidth();     // Catch: Throwable -> L31
    L25:
        if (r12.type != 1) goto L27;
        r12.width = (((r12.width * r1) / r0.getWidth()) * r29) * r26;     // Catch: Throwable -> L31
        r12.height = (((r12.height * r25) / r0.getHeight()) * r29) * r26;     // Catch: Throwable -> L31
        char r222 = 1;
    L28:
        r12.x = (r15[r172 ? 1 : 0] / r21.getWidth()) - (r12.width / 2.0f);     // Catch: Throwable -> L31
        r12.y = (r15[r222] / r21.getHeight()) - (r12.height / 2.0f);     // Catch: Throwable -> L31
        r12.textViewX = r15[r192] / r21.getWidth();     // Catch: Throwable -> L31
        r12.textViewY = r15[3] / r21.getHeight();     // Catch: Throwable -> L31
        r12.rotation = (float) (r12.rotation - ((r27 + r28) * 0.017453292519943295d));     // Catch: Throwable -> L31
        r102 = r102 + 1;     // Catch: Throwable -> L31
        r6 = r172 ? 1 : 0;
        r11 = r192;
        r0 = r21;
        r1 = r24;
        goto L16
    L27:
        r222 = 1;
        float r132 = r1;
        float r114 = r113;
        r12.viewWidth = (int) ((r12.viewWidth / r132) * r114);     // Catch: Throwable -> L31
        float r02 = r25;
        float r143 = r142;
        r12.viewHeight = (int) ((r12.viewHeight / r02) * r143);     // Catch: Throwable -> L31
        r12.width = (((r12.width * r132) / r114) * r29) * r26;     // Catch: Throwable -> L31
        r12.height = (((r12.height * r02) / r143) * r29) * r26;     // Catch: Throwable -> L31
        goto L28
    L20:
        if (r28 != 270.0f) goto L25;
    L7:
        r7 = r19;
        goto L8
    L34:
        r0.eraseColor(0);     // Catch: Throwable -> L31
        goto L5
    }

    private void fillAreaView(RectF r10, boolean r11) {
        if (this.state != null) goto L5;
        return;
    L5:
        float[] r3 = {1.0f};
        float r1 = Math.max(r10.width() / this.areaView.getCropWidth(), r10.height() / this.areaView.getCropHeight());
        if ((CropState.m10086$$Nest$mgetScale(this.state) * r1) <= 30.0f) goto L9;
        r1 = 30.0f / CropState.m10086$$Nest$mgetScale(this.state);
        boolean r6 = true;
    L8:
        float r2 = r1;
        if (this.inBubbleMode == true) goto L13;
        float r0 = AndroidUtilities.statusBarHeight;
    L14:
        float r4 = CropState.m10084$$Nest$mgetOrientedWidth(this.state) * ((r10.centerX() - (this.imageView.getWidth() / 2)) / this.areaView.getCropWidth());
        float r12 = ((r10.centerY() - ((((this.imageView.getHeight() - this.bottomPadding) + r0) + this.topPadding) / 2.0f)) / this.areaView.getCropHeight()) * CropState.m10083$$Nest$mgetOrientedHeight(this.state);
        ValueAnimator r7 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r7.addUpdateListener(new CropView$$ExternalSyntheticLambda0(this, r2, r3, r4, r12));
        r7.addListener(new AnonymousClass3(this, r6));
        this.areaView.fill(r10, r7, true);
        this.initialAreaRect.set(r10);
        return;
    L13:
        r0 = 0.0f;
        goto L14
    L9:
        r6 = false;
        goto L8
    }

    private void fitContentInBounds(boolean r2, boolean r3, boolean r4) {
        fitContentInBounds(r2, r3, r4, false);
    }

    private void fitContentInBounds(boolean r15, boolean r16, boolean r17, boolean r18) {
        if (this.state == null) goto L40;
        float r0 = this.areaView.getCropWidth();
        float r2 = this.areaView.getCropHeight();
        float r3 = CropState.m10084$$Nest$mgetOrientedWidth(this.state);
        float r4 = CropState.m10083$$Nest$mgetOrientedHeight(this.state);
        float r5 = CropState.m10085$$Nest$mgetRotation(this.state);
        float r6 = (float) Math.toRadians(r5);
        RectF r7 = calculateBoundingBox(r0, r2, r5);
        RectF r8 = new RectF(0.0f, 0.0f, r3, r4);
        float r11 = CropState.m10086$$Nest$mgetScale(this.state);
        this.tempRect.setRect(r8);
        Matrix r12 = CropState.m10080$$Nest$mgetMatrix(this.state);
        r12.preTranslate(((r0 - r3) / 2.0f) / r11, ((r2 - r4) / 2.0f) / r11);
        this.tempMatrix.reset();
        this.tempMatrix.setTranslate(r8.centerX(), r8.centerY());
        Matrix r02 = this.tempMatrix;
        r02.setConcat(r02, r12);
        this.tempMatrix.preTranslate(-r8.centerX(), -r8.centerY());
        this.tempRect.applyMatrix(this.tempMatrix);
        this.tempMatrix.reset();
        this.tempMatrix.preRotate(-r5, r3 / 2.0f, r4 / 2.0f);
        this.tempRect.applyMatrix(this.tempMatrix);
        this.tempRect.getRect(r8);
        PointF r03 = new PointF(CropState.m10088$$Nest$mgetX(this.state), CropState.m10089$$Nest$mgetY(this.state));
        if (r8.contains(r7) == true) goto L15;
        if (r15 == true) goto L9;
    L13:
        float r22 = r11;
    L14:
        fitTranslation(r8, r7, r03, r6);
    L23:
        float r42 = r03.x - CropState.m10088$$Nest$mgetX(this.state);
        float r04 = r03.y - CropState.m10089$$Nest$mgetY(this.state);
        if (r17 == false) goto L38;
        float r52 = r22 / r11;
        if (Math.abs(r52 - 1.0f) < 1.0E-5f) goto L28;
    L32:
        this.animating = true;
        ValueAnimator r62 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r62.addUpdateListener(new CropView$$ExternalSyntheticLambda1(this, r42, new float[]{1.0f, 0.0f, 0.0f}, r04, r52));
        r62.addListener(new AnonymousClass4(this, r18, r15, r16, r17));
        r62.setInterpolator(this.areaView.getInterpolator());
        if (r18 == false) goto L35;
        long r05 = 100;
    L36:
        r62.setDuration(r05);
        r62.start();
        return;
    L35:
        r05 = 200;
        goto L36
    L28:
        if (Math.abs(r42) >= 1.0E-5f) goto L32;
        if (Math.abs(r04) >= 1.0E-5f) goto L32;
        return;
    L38:
        CropState.m10095$$Nest$mtranslate(this.state, r42, r04);
        CropState.m10094$$Nest$mscale(this.state, r22 / r11, 0.0f, 0.0f);
        updateMatrix();
        return;
    L9:
        if (r7.width() <= r8.width()) goto L11;
    L12:
        r22 = fitScale(r8, r11, r7.width() / scaleWidthToMaxSize(r7, r8));
        goto L14
    L11:
        if (r7.height() <= r8.height()) goto L13;
    L15:
        if (r16 == true) goto L17;
    L22:
        r22 = r11;
        goto L23
    L17:
        if (this.rotationStartScale <= 0.0f) goto L22;
        float r23 = r7.width() / scaleWidthToMaxSize(r7, r8);
        if ((CropState.m10086$$Nest$mgetScale(this.state) * r23) >= this.rotationStartScale) goto L21;
        r23 = 1.0f;
    L21:
        r22 = fitScale(r8, r11, r23);
        fitTranslation(r8, r7, r03, r6);
        goto L23
    }

    private float fitScale(RectF r8, float r9, float r10) {
        float r7 = r8.width() * r10;
        float r0 = r8.height() * r10;
        float r1 = (r8.width() - r7) / 2.0f;
        float r3 = (r8.height() - r0) / 2.0f;
        float r2 = r8.left;
        float r5 = r8.top;
        r8.set(r2 + r1, r5 + r3, (r2 + r1) + r7, (r5 + r3) + r0);
        return r9 * r10;
    }

    private void fitTranslation(RectF r9, RectF r10, PointF r11, float r12) {
        float r8 = r10.left;
        float r0 = r10.top;
        float r1 = r10.right;
        float r2 = r10.bottom;
        float r3 = r9.left;
        if (r3 <= r8) goto L5;
        r1 = r1 + (r3 - r8);
        r8 = r3;
    L5:
        float r32 = r9.top;
        if (r32 <= r0) goto L8;
        r2 = r2 + (r32 - r0);
        r0 = r32;
    L8:
        float r33 = r9.right;
        if (r33 >= r1) goto L11;
        r8 = r8 + (r33 - r1);
    L11:
        float r92 = r9.bottom;
        if (r92 >= r2) goto L14;
        r0 = r0 + (r92 - r2);
    L14:
        float r93 = r10.centerX() - (r8 + (r10.width() / 2.0f));
        float r82 = r10.centerY() - (r0 + (r10.height() / 2.0f));
        double r02 = r12;
        double r4 = 1.5707963267948966d - r02;
        double r94 = r93;
        float r122 = (float) (Math.sin(r4) * r94);
        float r95 = (float) (Math.cos(r4) * r94);
        double r03 = r02 + 1.5707963267948966d;
        double r42 = r82;
        r11.set((r11.x + r122) + ((float) (Math.cos(r03) * r42)), (r11.y + r95) + ((float) (Math.sin(r03) * r42)));
    }

    public static String getCopy(String r4) {
        File r0 = new File(FileLoader.getDirectory(4), SharedConfig.getLastLocalId() + "_temp.jpg");
        AndroidUtilities.copyFile(new File(r4), r0);     // Catch: Exception -> L5
    L8:
        return r0.getAbsolutePath();
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    private /* synthetic */ void lambda$fillAreaView$0(float r2, float[] r3, float r4, float r5, ValueAnimator r6) {
        float r22 = r2 - 1.0f;
        float r23 = (r22 * ((Float) r6.getAnimatedValue()).floatValue()) + 1.0f;
        float r0 = r3[0];
        float r24 = r23 / r0;
        r3[0] = r0 * r24;
        CropState.m10094$$Nest$mscale(this.state, r24, r4, r5);
        updateMatrix();
    }

    private /* synthetic */ void lambda$fitContentInBounds$1(float r4, float[] r5, float r6, float r7, ValueAnimator r8) {
        float r82 = ((Float) r8.getAnimatedValue()).floatValue();
        float r1 = r5[1];
        float r42 = (r4 * r82) - r1;
        r5[1] = r1 + r42;
        float r12 = r5[2];
        float r62 = (r6 * r82) - r12;
        r5[2] = r12 + r62;
        CropState r0 = this.state;
        float r2 = r5[0];
        CropState.m10095$$Nest$mtranslate(r0, r42 * r2, r62 * r2);
        float r43 = r5[0];
        float r72 = (((r7 - 1.0f) * r82) + 1.0f) / r43;
        r5[0] = r43 * r72;
        CropState.m10094$$Nest$mscale(this.state, r72, 0.0f, 0.0f);
        updateMatrix();
    }

    private /* synthetic */ void lambda$maximize$2(RectF r2, RectF r3, float r4, float r5, float r6, float r7, float r8, ValueAnimator r9) {
        if (this.state != null) goto L5;
        return;
    L5:
        float r92 = ((Float) r9.getAnimatedValue()).floatValue();
        AndroidUtilities.lerp(r2, this.initialAreaRect, r92, r3);
        this.areaView.setActualRect(r3);
        CropState r22 = this.state;
        float r0 = 1.0f - r92;
        float r32 = r22.x - (r4 * r0);
        float r42 = r22.y - (r5 * r0);
        float r23 = r22.rotation - (r6 * r0);
        float r52 = AndroidUtilities.lerp(r7, r8, r92);
        CropState r62 = this.state;
        float r53 = r52 / r62.scale;
        CropState.m10095$$Nest$mtranslate(r62, -r32, -r42);
        CropState.m10094$$Nest$mscale(this.state, r53, 0.0f, 0.0f);
        CropState.m10093$$Nest$mrotate(this.state, -r23, 0.0f, 0.0f);
        fitContentInBounds(true, false, false);
    }

    private /* synthetic */ void lambda$showAspectRatioDialog$3(Integer[][] r3, DialogInterface r4, int r5) {
        this.hasAspectRatioDialog = false;
        if (r5 != 0) goto L5;
        float r32 = CropState.m10077$$Nest$mgetBaseRotation(this.state) % 180.0f;
        CropState r0 = this.state;
        if (r32 == 0.0f) goto L17;
        float r33 = CropState.m10079$$Nest$mgetHeight(r0);
    L18:
        float r02 = CropState.m10077$$Nest$mgetBaseRotation(this.state) % 180.0f;
        CropState r52 = this.state;
        if (r02 == 0.0f) goto L21;
        float r42 = CropState.m10087$$Nest$mgetWidth(r52);
    L22:
        setLockedAspectRatio(r33 / r42);
        return;
    L21:
        r42 = CropState.m10079$$Nest$mgetHeight(r52);
        goto L22
    L17:
        r33 = CropState.m10087$$Nest$mgetWidth(r0);
        goto L18
    L5:
        if (r5 == 1) goto L12;
        Integer[] r34 = r3[r5 - 2];
        if (this.areaView.getAspectRatio() <= 1.0f) goto L10;
        setLockedAspectRatio(r34[0].intValue() / r34[1].intValue());
        return;
    L10:
        setLockedAspectRatio(r34[1].intValue() / r34[0].intValue());
        return;
    L12:
        setLockedAspectRatio(1.0f);
    }

    private /* synthetic */ void lambda$showAspectRatioDialog$4(DialogInterface r1) {
        this.hasAspectRatioDialog = false;
    }

    private void resetRotationStartScale() {
        this.rotationStartScale = 0.0f;
    }

    private void setLockedAspectRatio(float r3) {
        this.areaView.setLockedAspectRatio(r3);
        RectF r0 = new RectF();
        this.areaView.calculateRect(r0, r3);
        fillAreaView(r0, true);
        CropViewListener r02 = this.listener;
        if (r02 == null) goto L6;
        r02.onChange(false);
        this.listener.onAspectLock(true);
        return;
    }

    private void updateCropTransform() {
        if (this.cropTransform != null) goto L5;
        return;
    L5:
        if (this.state == null) goto L43;
        this.areaView.getCropRect(this.cropRect);
        int r1 = (int) Math.ceil(scaleWidthToMaxSize(this.cropRect, this.sizeRect));
        int r3 = (int) Math.ceil(r2 / this.areaView.getAspectRatio());
        float r2 = r1 / this.areaView.getCropWidth();
        this.state.matrix.getValues(this.values);
        CropState r4 = this.state;
        float r5 = r4.minimumScale * r2;
        int r22 = CropState.m10082$$Nest$mgetOrientationOnly(r4);
    L8:
        if (r22 >= 0) goto L11;
        r22 = r22 + 360;
        goto L8
    L11:
        if (r22 != 90) goto L13;
    L17:
        CropState r23 = this.state;
        int r42 = (int) r23.height;
        float r24 = r23.width;
    L16:
        int r25 = (int) r24;
        double r6 = r1;
        float r12 = r42;
        float r43 = (float) (r6 / Math.ceil(r12 * r5));
        float r26 = r25;
        float r32 = (float) (r3 / Math.ceil(r5 * r26));
        if (r43 <= 1.0f) goto L21;
    L24:
        float r52 = Math.max(r43, r32);
        r43 = r43 / r52;
        r32 = r32 / r52;
    L23:
        float r15 = r32;
        float r14 = r43;
        RectF r33 = this.areaView.getTargetRectToFill(r12 / r26);
        if (this.freeform == false) goto L28;
        float r34 = r33.width() / r12;
    L29:
        CropState r44 = this.state;
        float r53 = r44.scale;
        float r11 = r53 / r34;
        float r122 = r53 / r44.minimumScale;
        float[] r62 = this.values;
        float r7 = (r62[2] / r12) / r53;
        float r8 = (r62[5] / r26) / r53;
        float r9 = r44.rotation;
        RectF r13 = this.areaView.getTargetRectToFill();
        float r16 = this.areaView.getCropCenterX() - r13.centerX();
        float r17 = this.areaView.getCropCenterY() - r13.centerY();
        CropTransform r54 = this.cropTransform;
        CropState r18 = this.state;
        if (r18.mirrored == false) goto L32;
    L38:
        boolean r19 = true;
    L37:
        boolean r63 = r19;
        int r10 = CropState.m10082$$Nest$mgetOrientationOnly(this.state);
        CropState r0 = this.state;
        r54.setViewTransform(r63, r7, r8, r9, r10, r11, r122, r0.minimumScale / r34, r14, r15, r16, r17, r0.mirrored);
        return;
    L32:
        if (CropState.m10090$$Nest$mhasChanges(r18) == true) goto L38;
        if (CropState.m10077$$Nest$mgetBaseRotation(this.state) >= 1.0E-5f) goto L38;
        r19 = false;
        goto L37
    L28:
        r34 = Math.max(r33.width() / r12, r33.height() / r26);
        goto L29
    L21:
        if (r32 <= 1.0f) goto L23;
    L13:
        if (r22 == 270) goto L17;
        CropState r27 = this.state;
        r42 = (int) r27.width;
        r24 = r27.height;
        goto L16
    }

    public void applyToCropState(MediaController.CropState r11) {
        this.areaView.getCropRect(this.cropRect);
        int r0 = (int) Math.ceil(scaleWidthToMaxSize(this.cropRect, this.sizeRect));
        int r2 = (int) Math.ceil(r1 / this.areaView.getAspectRatio());
        float r1 = r0 / this.areaView.getCropWidth();
        this.state.matrix.getValues(this.values);
        CropState r3 = this.state;
        float r4 = r3.minimumScale * r1;
        r11.transformRotation = CropState.m10082$$Nest$mgetOrientationOnly(r3);
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("set transformRotation = " + r11.transformRotation);
    L5:
        int r32 = r11.transformRotation;
        if (r32 >= 0) goto L9;
        r11.transformRotation = r32 + 360;
        goto L5
    L9:
        if (r32 != 90) goto L11;
    L15:
        CropState r33 = this.state;
        int r5 = (int) r33.height;
        float r34 = r33.width;
    L14:
        int r35 = (int) r34;
        float r52 = r5;
        r11.cropPw = (float) (r0 / Math.ceil(r52 * r4));
        float r36 = r35;
        float r42 = (float) (r2 / Math.ceil(r4 * r36));
        r11.cropPh = r42;
        float r6 = r11.cropPw;
        if (r6 <= 1.0f) goto L19;
    L20:
        float r43 = Math.max(r6, r42);
        r11.cropPw /= r43;
        r11.cropPh /= r43;
    L21:
        r11.cropScale = this.state.scale * Math.min(r52 / this.areaView.getCropWidth(), r36 / this.areaView.getCropHeight());
        float[] r44 = this.values;
        float r62 = r44[2] / r52;
        CropState r53 = this.state;
        float r7 = r53.scale;
        r11.cropPx = r62 / r7;
        r11.cropPy = (r44[5] / r36) / r7;
        r11.cropRotate = r53.rotation;
        r11.stateScale = r7;
        r11.mirrored = r53.mirrored;
        r11.scale = r1;
        r11.matrix = r53.matrix;
        r11.width = r0;
        r11.height = r2;
        r11.freeform = this.freeform;
        r11.lockedAspectRatio = this.areaView.getLockAspectRatio();
        r11.initied = true;
        return;
    L19:
        if (r42 <= 1.0f) goto L21;
    L11:
        if (r32 == 270) goto L15;
        CropState r37 = this.state;
        r5 = (int) r37.width;
        r34 = r37.height;
        goto L14
    }

    public RectF calculateBoundingBox(float r3, float r4, float r5) {
        RectF r2 = new RectF(0.0f, 0.0f, r3, r4);
        Matrix r0 = new Matrix();
        r0.postRotate(r5, r3 / 2.0f, r4 / 2.0f);
        r0.mapRect(r2);
        return r2;
    }

    public RectF getActualRect() {
        this.areaView.getCropRect(this.cropRect);
        return this.cropRect;
    }

    public float getCropHeight() {
        return this.areaView.getCropHeight();
    }

    public float getCropLeft() {
        return this.areaView.getCropLeft();
    }

    public float getCropTop() {
        return this.areaView.getCropTop();
    }

    public float getCropWidth() {
        return this.areaView.getCropWidth();
    }

    public int getCurrentHeight() {
        VideoEditTextureView r0 = this.videoEditTextureView;
        if (r0 != null) goto L5;
        Bitmap r02 = this.bitmap;
        if (r02 != null) goto L10;
        return 1;
    L10:
        int r2 = this.bitmapRotation;
        if (r2 == 90) goto L18;
        if (r2 == 270) goto L18;
        return r02.getHeight();
    L18:
        return r02.getWidth();
    L5:
        return r0.getVideoHeight();
    }

    public int getCurrentWidth() {
        VideoEditTextureView r0 = this.videoEditTextureView;
        if (r0 != null) goto L5;
        Bitmap r02 = this.bitmap;
        if (r02 != null) goto L10;
        return 1;
    L10:
        int r2 = this.bitmapRotation;
        if (r2 == 90) goto L18;
        if (r2 == 270) goto L18;
        return r02.getWidth();
    L18:
        return r02.getHeight();
    L5:
        return r0.getVideoWidth();
    }

    public float getStateFullOrientation() {
        CropState r1 = this.state;
        if (r1 != null) goto L7;
        return 0.0f;
    L7:
        return r1.baseRotation + r1.orientation;
    }

    public boolean getStateMirror() {
        CropState r0 = this.state;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.mirrored == false) goto L10;
        return true;
    L10:
        return false;
    }

    public float getStateOrientation() {
        CropState r0 = this.state;
        if (r0 != null) goto L7;
        return 0.0f;
    L7:
        return r0.orientation;
    }

    public void hide() {
        this.imageView.setVisibility(4);
        this.areaView.setDimVisibility(false);
        this.areaView.setFrameVisibility(false, false);
        this.areaView.invalidate();
    }

    public boolean isMirrored() {
        CropState r0 = this.state;
        if (r0 != null) goto L7;
        return false;
    L7:
        return r0.mirrored;
    }

    public boolean isReady() {
        if (this.detector.isScaling() == false) goto L5;
        return false;
    L5:
        if (this.detector.isDragging() == false) goto L7;
        return false;
    L7:
        if (this.areaView.isDragging() == true) goto L13;
        return true;
    L13:
        return false;
    }

    public void makeCrop(MediaController.MediaEditState r23) {
        if (this.state != null) goto L5;
        return;
    L5:
        this.areaView.getCropRect(this.cropRect);
        int r2 = (int) Math.ceil(scaleWidthToMaxSize(this.cropRect, this.sizeRect));
        int r4 = (int) Math.ceil(r3 / this.areaView.getAspectRatio());
        float r18 = r2 / this.areaView.getCropWidth();
        if (r23.paintPath == null) goto L20;
        Bitmap r10 = Bitmap.createBitmap(r2, r4, Bitmap.Config.ARGB_8888);
        Canvas r9 = new Canvas(r10);
        String r7 = getCopy(r23.paintPath);
        if (r23.croppedPaintPath == null) goto L10;
        new File(r23.croppedPaintPath).delete();
        r23.croppedPaintPath = null;
    L10:
        r23.croppedPaintPath = r7;
        ArrayList<VideoEditedInfo.MediaEntity> r22 = r23.mediaEntities;
        if (r22 != null) goto L13;
    L17:
        r23.croppedMediaEntities = null;
    L18:
        Context r6 = getContext();
        Bitmap.CompressFormat r11 = Bitmap.CompressFormat.PNG;
        Matrix r12 = this.state.matrix;
        int r13 = getCurrentWidth();
        int r14 = getCurrentHeight();
        CropState r24 = this.state;
        editBitmap(r6, r7, null, r9, r10, r11, r12, r13, r14, r24.scale, r24.rotation, CropState.m10082$$Nest$mgetOrientationOnly(r24), r18, false, r23.croppedMediaEntities, false);
        goto L20
    L13:
        if (r22.isEmpty() == true) goto L17;
        r23.croppedMediaEntities = new ArrayList(r23.mediaEntities.size());
        int r25 = r23.mediaEntities.size();
        int r3 = 0;
    L15:
        if (r3 >= r25) goto L18;
        r23.croppedMediaEntities.add(r23.mediaEntities.get(r3).copy());
        r3 = r3 + 1;
    L20:
        if (r23.cropState != null) goto L22;
        r23.cropState = new MediaController.CropState();
    L22:
        applyToCropState(r23.cropState);
    }

    public void maximize(boolean r11) {
        CropState r0 = this.state;
        if (r0 != null) goto L5;
        return;
    L5:
        float r9 = r0.minimumScale;
        this.areaView.resetAnimator();
        if ((CropState.m10081$$Nest$mgetOrientation(this.state) % 180.0f) == 0.0f) goto L9;
        float r02 = getCurrentHeight();
        int r2 = getCurrentWidth();
    L8:
        float r03 = r02 / r2;
        float r3 = 1.0f;
        if (this.freeform == true) goto L13;
        r03 = 1.0f;
    L13:
        this.areaView.calculateRect(this.initialAreaRect, r03);
        CropAreaView r04 = this.areaView;
        if (this.freeform == false) goto L16;
        r3 = 0.0f;
    L16:
        r04.setLockedAspectRatio(r3);
        resetRotationStartScale();
        if (r11 == false) goto L20;
        ValueAnimator r112 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        RectF r32 = new RectF();
        RectF r4 = new RectF();
        this.areaView.getCropRect(r32);
        CropState r05 = this.state;
        r112.addUpdateListener(new CropView$$ExternalSyntheticLambda2(this, r32, r4, r05.x, r05.y, r05.rotation, r05.scale, r9));
        r112.setInterpolator(this.areaView.getInterpolator());
        r112.setDuration(250);
        r112.start();
        return;
    L20:
        this.areaView.setActualRect(this.initialAreaRect);
        CropState r10 = this.state;
        CropState.m10095$$Nest$mtranslate(r10, -r10.x, -r10.y);
        CropState r102 = this.state;
        CropState.m10094$$Nest$mscale(r102, r102.minimumScale / r102.scale, 0.0f, 0.0f);
        CropState r103 = this.state;
        CropState.m10093$$Nest$mrotate(r103, -r103.rotation, 0.0f, 0.0f);
        updateMatrix();
        resetRotationStartScale();
        return;
    L9:
        r02 = getCurrentWidth();
        r2 = getCurrentHeight();
        goto L8
    }

    public boolean mirror() {
        CropState r0 = this.state;
        boolean r1 = false;
        if (r0 != null) goto L5;
        return false;
    L5:
        CropState.m10091$$Nest$mmirror(r0);
        updateMatrix();
        if (this.listener == null) goto L18;
        float r02 = (CropState.m10081$$Nest$mgetOrientation(this.state) - CropState.m10077$$Nest$mgetBaseRotation(this.state)) % 360.0f;
        CropViewListener r2 = this.listener;
        if (CropState.m10090$$Nest$mhasChanges(this.state) == false) goto L10;
    L16:
        r2.onChange(r1);
        goto L18
    L10:
        if (r02 != 0.0f) goto L16;
        if (this.areaView.getLockAspectRatio() != 0.0f) goto L16;
        if (this.state.mirrored == true) goto L16;
        r1 = true;
    L18:
        return this.state.mirrored;
    }

    @Override // org.telegram.ui.Components.Crop.CropAreaView.AreaViewListener
    public void onAreaChange() {
        this.areaView.setGridType(CropAreaView.GridType.MAJOR, false);
        float r0 = this.previousAreaRect.centerX() - this.areaView.getCropCenterX();
        float r1 = this.previousAreaRect.centerY() - this.areaView.getCropCenterY();
        CropState r3 = this.state;
        if (r3 == null) goto L5;
        CropState.m10095$$Nest$mtranslate(r3, r0, r1);
    L5:
        updateMatrix();
        this.areaView.getCropRect(this.previousAreaRect);
        fitContentInBounds(true, false, false);
    }

    @Override // org.telegram.ui.Components.Crop.CropAreaView.AreaViewListener
    public void onAreaChangeBegan() {
        this.areaView.getCropRect(this.previousAreaRect);
        resetRotationStartScale();
        CropViewListener r2 = this.listener;
        if (r2 == null) goto L6;
        r2.onChange(false);
        return;
    }

    @Override // org.telegram.ui.Components.Crop.CropAreaView.AreaViewListener
    public void onAreaChangeEnded() {
        this.areaView.setGridType(CropAreaView.GridType.NONE, true);
        fillAreaView(this.areaView.getTargetRectToFill(), false);
    }

    @Override // org.telegram.ui.Components.Crop.CropGestureDetector.CropGestureListener
    public void onDrag(float r2, float r3) {
        if (this.animating == false) goto L5;
        return;
    L5:
        CropState.m10095$$Nest$mtranslate(this.state, r2, r3);
        updateMatrix();
    }

    @Override // org.telegram.ui.Components.Crop.CropGestureDetector.CropGestureListener
    public void onFling(float r1, float r2, float r3, float r4) {
    }

    public void onHide() {
        this.videoEditTextureView = null;
        this.paintingOverlay = null;
        this.isVisible = false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r1) {
        return true;
    }

    public void onRotationBegan() {
        this.areaView.setGridType(CropAreaView.GridType.MINOR, false);
        if (this.rotationStartScale >= 1.0E-5f) goto L6;
        this.rotationStartScale = CropState.m10086$$Nest$mgetScale(this.state);
        return;
    }

    public void onRotationEnded() {
        this.areaView.setGridType(CropAreaView.GridType.NONE, true);
    }

    @Override // org.telegram.ui.Components.Crop.CropGestureDetector.CropGestureListener
    public void onScale(float r4, float r5, float r6) {
        if (this.animating == false) goto L6;
        return;
    L6:
        if ((CropState.m10086$$Nest$mgetScale(this.state) * r4) <= 30.0f) goto L9;
        r4 = 30.0f / CropState.m10086$$Nest$mgetScale(this.state);
    L9:
        if (this.inBubbleMode == true) goto L11;
        float r0 = AndroidUtilities.statusBarHeight;
    L12:
        CropState.m10094$$Nest$mscale(this.state, r4, ((r5 - (this.imageView.getWidth() / 2)) / this.areaView.getCropWidth()) * CropState.m10084$$Nest$mgetOrientedWidth(this.state), ((r6 - ((((this.imageView.getHeight() - this.bottomPadding) - r0) - this.topPadding) / 2.0f)) / this.areaView.getCropHeight()) * CropState.m10083$$Nest$mgetOrientedHeight(this.state));
        updateMatrix();
        return;
    L11:
        r0 = 0.0f;
        goto L12
    }

    public void onScrollChangeBegan() {
        if (this.animating == true) goto L10;
        this.areaView.setGridType(CropAreaView.GridType.MAJOR, true);
        resetRotationStartScale();
        CropViewListener r3 = this.listener;
        if (r3 == null) goto L9;
        r3.onChange(false);
        return;
    L9:
        return;
    }

    public void onScrollChangeEnded() {
        this.areaView.setGridType(CropAreaView.GridType.NONE, true);
        fitContentInBounds(true, false, true);
    }

    public void onShow() {
        this.isVisible = true;
    }

    @Override // org.telegram.ui.Components.Crop.CropGestureDetector.CropGestureListener
    public void onTapUp() {
        CropViewListener r0 = this.listener;
        if (r0 == null) goto L6;
        r0.onTapUp();
        return;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r3) {
        if (this.animating == false) goto L6;
        return true;
    L6:
        if (this.areaView.onTouchEvent(r3) == false) goto L8;
        return true;
    L8:
        int r0 = r3.getAction();
        if (r0 == 0) goto L15;
        if (r0 != 1) goto L12;
    L14:
        onScrollChangeEnded();
    L20:
        return this.detector.onTouchEvent(r3);
    L18:
        return false;
    L12:
        if (r0 == 3) goto L14;
    L15:
        onScrollChangeBegan();
        goto L20
    }

    public void reset() {
        reset(false);
    }

    public void reset(boolean r9) {
        this.areaView.resetAnimator();
        CropAreaView r0 = this.areaView;
        int r1 = getCurrentWidth();
        int r2 = getCurrentHeight();
        CropState r3 = this.state;
        if (r3 != null) goto L5;
    L7:
        boolean r32 = false;
    L8:
        r0.setBitmap(r1, r2, r32, this.freeform);
        CropAreaView r02 = this.areaView;
        if (this.freeform == false) goto L11;
        float r12 = 0.0f;
    L12:
        r02.setLockedAspectRatio(r12);
        CropState r03 = this.state;
        if (r03 == null) goto L15;
        CropState.m10092$$Nest$mreset(r03, 0.0f);
        this.state.mirrored = false;
    L15:
        this.areaView.getCropRect(this.initialAreaRect);
        updateMatrix(r9);
        resetRotationStartScale();
        CropViewListener r92 = this.listener;
        if (r92 == null) goto L19;
        r92.onChange(true);
        this.listener.onAspectLock(false);
        return;
    L19:
        return;
    L11:
        r12 = 1.0f;
        goto L12
    L5:
        if ((CropState.m10077$$Nest$mgetBaseRotation(r3) % 180.0f) == 0.0f) goto L7;
        r32 = true;
        goto L8
    }

    public boolean rotate(float r9) {
        if (this.state != null) goto L5;
        return false;
    L5:
        this.areaView.resetAnimator();
        resetRotationStartScale();
        float r0 = ((CropState.m10081$$Nest$mgetOrientation(this.state) - CropState.m10077$$Nest$mgetBaseRotation(this.state)) + r9) % 360.0f;
        if (this.freeform == true) goto L8;
    L10:
        CropAreaView r92 = this.areaView;
        int r4 = getCurrentWidth();
        int r5 = getCurrentHeight();
        if (((CropState.m10077$$Nest$mgetBaseRotation(this.state) + r0) % 180.0f) == 0.0f) goto L13;
        boolean r6 = true;
    L14:
        r92.setBitmap(r4, r5, r6, this.freeform);
    L15:
        CropState.m10092$$Nest$mreset(this.state, r0);
        updateMatrix();
        fitContentInBounds(true, false, false);
        CropViewListener r93 = this.listener;
        if (r93 == null) goto L27;
        if (r0 == 0.0f) goto L20;
    L24:
        boolean r02 = false;
    L25:
        r93.onChange(r02);
        goto L27
    L20:
        if (this.areaView.getLockAspectRatio() != 0.0f) goto L24;
        if (this.state.mirrored == true) goto L24;
        r02 = true;
    L27:
        if (CropState.m10082$$Nest$mgetOrientationOnly(this.state) == 0) goto L29;
        return true;
    L29:
        return false;
    L13:
        r6 = false;
        goto L14
    L8:
        if (this.areaView.getLockAspectRatio() <= 0.0f) goto L10;
        CropAreaView r94 = this.areaView;
        r94.setLockedAspectRatio(1.0f / r94.getLockAspectRatio());
        CropAreaView r95 = this.areaView;
        r95.setActualRect(r95.getLockAspectRatio());
        goto L15
    }

    public float scaleWidthToMaxSize(RectF r3, RectF r4) {
        float r2 = r4.width();
        if (((float) Math.floor((r3.height() * r2) / r3.width())) > r4.height()) goto L5;
        return r2;
    L5:
        return (float) Math.floor((r4.height() * r3.width()) / r3.height());
    }

    public void setAspectRatio(float r1) {
        this.areaView.setActualRect(r1);
    }

    public void setBitmap(Bitmap r7, int r8, boolean r9, boolean r10, PaintingOverlay r11, CropTransform r12, VideoEditTextureView r13, MediaController.CropState r14) {
        this.freeform = r9;
        this.paintingOverlay = r11;
        this.videoEditTextureView = r13;
        this.cropTransform = r12;
        this.bitmapRotation = r8;
        this.bitmap = r7;
        CropAreaView r92 = this.areaView;
        if (r13 == null) goto L5;
        boolean r112 = true;
    L6:
        r92.setIsVideo(r112);
        Bitmap r93 = null;
        if (r7 != null) goto L11;
        if (r13 != null) goto L11;
        this.state = null;
        this.imageView.setImageDrawable(null);
        return;
    L11:
        int r2 = getCurrentWidth();
        int r3 = getCurrentHeight();
        CropState r72 = this.state;
        if (r72 == null) goto L16;
        if (r10 == false) goto L16;
        CropState.m10096$$Nest$mupdate(r72, r2, r3, r8);
        CropView r1 = this;
    L17:
        ImageView r6 = r1.imageView;
        if (r13 != null) goto L20;
        r93 = r1.bitmap;
    L20:
        r6.setImageBitmap(r93);
        return;
    L16:
        r1 = this;
        r1.state = new CropState(r1, r2, r3, 0, null);
        r1.areaView.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass2(r1, r14, r3, r2));
        goto L17
    L5:
        r112 = false;
        goto L6
    }

    public void setBottomPadding(float r1) {
        this.bottomPadding = r1;
        this.areaView.setBottomPadding(r1);
    }

    public void setFreeform(boolean r2) {
        this.areaView.setFreeform(r2);
        this.freeform = r2;
    }

    public void setListener(CropViewListener r1) {
        this.listener = r1;
    }

    @Override // android.view.View
    public void setRotation(float r3) {
        float r32 = r3 - CropState.m10085$$Nest$mgetRotation(this.state);
        CropState.m10093$$Nest$mrotate(this.state, r32, 0.0f, 0.0f);
        fitContentInBounds(true, true, false);
    }

    public void setSubtitle(String r1) {
        this.areaView.setSubtitle(r1);
    }

    public void setTopPadding(float r1) {
        this.topPadding = r1;
        this.areaView.setTopPadding(r1);
    }

    public void show() {
        updateCropTransform();
        this.areaView.setDimVisibility(true);
        this.areaView.setFrameVisibility(true, true);
        this.areaView.invalidate();
    }

    public void showAspectRatioDialog() {
        if (this.state != null) goto L6;
        return;
    L6:
        if (this.hasAspectRatioDialog == false) goto L8;
        return;
    L8:
        this.hasAspectRatioDialog = true;
        String[] r4 = new String[8];
        int r5 = 2;
        Integer[][] r0 = {new Integer[]{3, 2}, new Integer[]{5, 3}, new Integer[]{4, 3}, new Integer[]{5, 4}, new Integer[]{7, 5}, new Integer[]{16, 9}};
        r4[0] = LocaleController.getString(R.string.CropOriginal);
        r4[1] = LocaleController.getString(R.string.CropSquare);
        int r1 = 0;
    L10:
        if (r1 >= 6) goto L16;
        Integer[] r6 = r0[r1];
        if (this.areaView.getAspectRatio() <= 1.0f) goto L14;
        r4[r5] = String.format("%d:%d", new Object[]{r6[0], r6[1]});
    L15:
        r5 = r5 + 1;
        r1 = r1 + 1;
        goto L10
    L14:
        r4[r5] = String.format("%d:%d", new Object[]{r6[1], r6[0]});
        goto L15
    L16:
        AlertDialog r02 = new AlertDialog.Builder(getContext()).setItems(r4, new CropView$$ExternalSyntheticLambda3(this, r0)).create();
        r02.setCanceledOnTouchOutside(true);
        r02.setOnCancelListener(new CropView$$ExternalSyntheticLambda4(this));
        r02.show();
    }

    public void start(int r7, boolean r8, boolean r9, CropTransform r10, MediaController.CropState r11) {
        this.freeform = r8;
        this.paintingOverlay = null;
        this.videoEditTextureView = null;
        this.cropTransform = r10;
        this.bitmapRotation = r7;
        this.bitmap = null;
        this.areaView.setIsVideo(false);
        int r2 = getCurrentWidth();
        int r3 = getCurrentHeight();
        CropState r82 = this.state;
        if (r82 == null) goto L8;
        if (r9 == false) goto L8;
        CropState.m10096$$Nest$mupdate(r82, r2, r3, r7);
        return;
    L8:
        this.state = new CropState(this, r2, r3, 0, null);
        this.areaView.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass1(this, r11, r3, r2));
    }

    public void stop() {
        this.state = null;
    }

    public void updateLayout() {
        float r0 = this.areaView.getCropWidth();
        if (r0 == 0.0f) goto L10;
        CropState r2 = this.state;
        if (r2 == null) goto L9;
        this.areaView.calculateRect(this.initialAreaRect, CropState.m10087$$Nest$mgetWidth(r2) / CropState.m10079$$Nest$mgetHeight(this.state));
        CropAreaView r22 = this.areaView;
        r22.setActualRect(r22.getAspectRatio());
        this.areaView.getCropRect(this.previousAreaRect);
        float r23 = this.areaView.getCropWidth() / r0;
        CropState.m10094$$Nest$mscale(this.state, r23, 0.0f, 0.0f);
        updateMatrix();
        return;
    L9:
        return;
    }

    public void updateMatrix() {
        updateMatrix(false);
    }

    public void updateMatrix(boolean r5) {
        if (this.state != null) goto L5;
        return;
    L5:
        this.overlayMatrix.reset();
        if (CropState.m10077$$Nest$mgetBaseRotation(this.state) != 90.0f) goto L8;
    L11:
        this.overlayMatrix.postTranslate((-CropState.m10079$$Nest$mgetHeight(this.state)) / 2.0f, (-CropState.m10087$$Nest$mgetWidth(this.state)) / 2.0f);
    L12:
        this.overlayMatrix.postRotate(CropState.m10082$$Nest$mgetOrientationOnly(this.state));
        CropState.m10078$$Nest$mgetConcatMatrix(this.state, this.overlayMatrix);
        this.overlayMatrix.postTranslate(this.areaView.getCropCenterX(), this.areaView.getCropCenterY());
        if (this.freeform == true) goto L15;
    L17:
        updateCropTransform();
        this.listener.onUpdate();
    L18:
        invalidate();
        return;
    L15:
        if (this.isVisible == true) goto L17;
        if (r5 == false) goto L18;
    L8:
        if (CropState.m10077$$Nest$mgetBaseRotation(this.state) == 270.0f) goto L11;
        this.overlayMatrix.postTranslate((-CropState.m10087$$Nest$mgetWidth(this.state)) / 2.0f, (-CropState.m10079$$Nest$mgetHeight(this.state)) / 2.0f);
        goto L12
    }

    public void willShow() {
        this.areaView.setFrameVisibility(true, false);
        this.areaView.setDimVisibility(true);
        this.areaView.invalidate();
    }
}
