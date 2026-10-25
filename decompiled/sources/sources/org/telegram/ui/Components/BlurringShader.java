package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.opengl.GLES20;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import com.exteragram.messenger.utils.ui.WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotFullscreenButtons$$ExternalSyntheticApiModelOutline0;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public class BlurringShader {
    private Bitmap bitmap;
    private boolean bitmapAvailable;
    private final Object bitmapLock;
    private ByteBuffer buffer;
    private BlurManager currentManager;
    private final int[] framebuffer;
    private int gradientBottom;
    private int gradientTop;
    private int height;
    private final Matrix iMatrix;
    private final Runnable invalidateViews;
    private final float[] m3x3;
    private final float[] matrix;
    private final Object matrixLock;
    private FloatBuffer padPosBuffer;
    private int padding;
    private FilterGLThread parent;
    private FloatBuffer posBuffer;
    private Program[] program;
    private boolean setupTransform;
    private final int[] texture;
    private FloatBuffer uvBuffer;
    private int width;

    public static class BlurManager {
        private Object blurRenderNode;
        private EGLContext context;
        private final Object contextLock;
        private BlurringShader currentShader;
        private Bitmap fallbackBitmap;
        private final ArrayList<StoryBlurDrawer> holders;
        private int i;
        private final ArrayList<Runnable> invalidateHolders;
        public int padding;
        private final ArrayList<View> parents;
        private Object renderNode;
        private int renderNodeBackgroundColor;
        private View renderNodeView;
        private final Object textureLock;
        private final ThumbBlurer thumbBlurer;
        private final View view;

        /* renamed from: org.telegram.ui.Components.BlurringShader$BlurManager$1, reason: invalid class name */
        public class AnonymousClass1 implements View.OnAttachStateChangeListener {
            final /* synthetic */ BlurManager this$0;

            public AnonymousClass1(BlurManager r1) {
                this.this$0 = r1;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View r1) {
                BlurManager.m9019$$Nest$mupdateParents(this.this$0);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View r1) {
                BlurManager.m9014$$Nest$fgetparents(this.this$0).clear();
            }
        }

        public static /* synthetic */ void $r8$lambda$f1bzpFWCmQedn0D0akAdRfUDF0s(BlurManager r0) {
            r0.invalidateFallbackBlur();
        }

        /* renamed from: -$$Nest$fgetblurRenderNode, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Object m9013$$Nest$fgetblurRenderNode(BlurManager r0) {
            return r0.blurRenderNode;
        }

        /* renamed from: -$$Nest$fgetparents, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m9014$$Nest$fgetparents(BlurManager r0) {
            return r0.parents;
        }

        /* renamed from: -$$Nest$fgetrenderNode, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Object m9015$$Nest$fgetrenderNode(BlurManager r0) {
            return r0.renderNode;
        }

        /* renamed from: -$$Nest$fgetrenderNodeBackgroundColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m9016$$Nest$fgetrenderNodeBackgroundColor(BlurManager r0) {
            return r0.renderNodeBackgroundColor;
        }

        /* renamed from: -$$Nest$fgetrenderNodeView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m9017$$Nest$fgetrenderNodeView(BlurManager r0) {
            return r0.renderNodeView;
        }

        /* renamed from: -$$Nest$fgetview, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m9018$$Nest$fgetview(BlurManager r0) {
            return r0.view;
        }

        /* renamed from: -$$Nest$mupdateParents, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m9019$$Nest$mupdateParents(BlurManager r0) {
            r0.updateParents();
        }

        public BlurManager(View r4) {
            this.parents = new ArrayList();
            this.holders = new ArrayList();
            this.invalidateHolders = new ArrayList();
            this.contextLock = new Object();
            this.textureLock = new Object();
            this.thumbBlurer = new ThumbBlurer(0, new BlurringShader$BlurManager$$ExternalSyntheticLambda0(this));
            this.i = 0;
            this.view = r4;
            if (r4.isAttachedToWindow() == false) goto L5;
            updateParents();
        L5:
            r4.addOnAttachStateChangeListener(new AnonymousClass1(this));
        }

        private void invalidateFallbackBlur() {
            this.fallbackBitmap = ThumbBlurer.m9030$$Nest$fgetthumbBitmap(this.thumbBlurer);
            invalidate();
        }

        private void updateParents() {
            this.parents.clear();
            View r0 = this.view;
        L3:
            if (r0 == null) goto L8;
            this.parents.add(0, r0);
            if ((r0.getParent() instanceof View) == false) goto L11;
            r0 = (View) r0.getParent();
            goto L3
        L11:
            return;
        }

        public void acquiredContext(EGLContext r3) {
            Object r0 = this.contextLock;
            monitor-enter(r0);
        L7:
            th = move-exception;
            throw th;
        L5:
            if (this.context != null) goto L9;
            this.context = r3;     // Catch: Throwable -> L7
        L9:
            monitor-exit(r0);     // Catch: Throwable -> L7
        }

        public void attach(Runnable r1) {
            this.invalidateHolders.add(r1);
        }

        public void attach(StoryBlurDrawer r1) {
            this.holders.add(r1);
        }

        public void destroyedContext(EGLContext r3) {
            Object r0 = this.contextLock;
            monitor-enter(r0);
        L7:
            th = move-exception;
            throw th;
        L5:
            if (this.context != r3) goto L9;
            this.context = null;     // Catch: Throwable -> L7
        L9:
            monitor-exit(r0);     // Catch: Throwable -> L7
        }

        public void detach(Runnable r2) {
            this.invalidateHolders.remove(r2);
            if (this.invalidateHolders.isEmpty() == true) goto L5;
            return;
        L5:
            if (this.holders.isEmpty() == false) goto L9;
            this.thumbBlurer.destroy();
            return;
        }

        public void detach(StoryBlurDrawer r2) {
            this.holders.remove(r2);
            if (this.invalidateHolders.isEmpty() == true) goto L5;
            return;
        L5:
            if (this.holders.isEmpty() == false) goto L9;
            this.thumbBlurer.destroy();
            return;
        }

        public Bitmap getBitmap() {
            BlurringShader r0 = this.currentShader;
            if (r0 == null) goto L5;
            Bitmap r02 = r0.getBitmap();
            if (r02 == null) goto L9;
            return r02;
        L9:
            return this.fallbackBitmap;
        L5:
            return this.fallbackBitmap;
        }

        public EGLContext getParentContext() {
            Object r0 = this.contextLock;
            monitor-enter(r0);
            EGLContext r1 = this.context;     // Catch: Throwable -> L8
            if (r1 == null) goto L10;
            monitor-exit(r0);     // Catch: Throwable -> L8
            return r1;
        L10:
            EGLContext r12 = EGL10.EGL_NO_CONTEXT;     // Catch: Throwable -> L8
            monitor-exit(r0);     // Catch: Throwable -> L8
            return r12;
        L8:
            th = move-exception;
            throw th;
        }

        public int getTexture() {
            BlurringShader r0 = this.currentShader;
            if (r0 != null) goto L5;
            return -1;
        L5:
            return r0.getTexture();
        }

        public Object getTextureLock() {
            return this.textureLock;
        }

        public boolean hasRenderNode() {
            if (this.blurRenderNode == null) goto L6;
            return true;
        L6:
            return false;
        }

        public void invalidate() {
            ArrayList<StoryBlurDrawer> r0 = this.holders;
            int r1 = r0.size();
            int r2 = 0;
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L5;
            StoryBlurDrawer r4 = r0.get(r3);
            r3 = r3 + 1;
            StoryBlurDrawer.m9026$$Nest$fgetview(r4).invalidate();
            goto L3
        L5:
            ArrayList<Runnable> r5 = this.invalidateHolders;
            int r02 = r5.size();
        L6:
            if (r2 >= r02) goto L8;
            Runnable r12 = r5.get(r2);
            r2 = r2 + 1;
            r12.run();
            goto L6
        }

        public void resetBitmap() {
            BlurringShader r0 = this.currentShader;
            if (r0 == null) goto L6;
            r0.resetBitmap();
            return;
        }

        public void setFallbackBlur(Bitmap r2, int r3) {
            setFallbackBlur(r2, r3, false);
        }

        public void setFallbackBlur(Bitmap r7, int r8, boolean r9) {
            ThumbBlurer r0 = this.thumbBlurer;
            StringBuilder r1 = new StringBuilder(_UrlKt.FRAGMENT_ENCODE_SET);
            int r2 = this.i;
            this.i = r2 + 1;
            r1.append(r2);
            this.fallbackBitmap = r0.getBitmap(r7, r1.toString(), r8, 0, r9);
        }

        public void setRenderNode(View r4, Object r5, int r6) {
            this.renderNodeView = r4;
            this.renderNode = r5;
            this.renderNodeBackgroundColor = r6;
            if (r5 != null) goto L5;
        L8:
            this.blurRenderNode = null;
            return;
        L5:
            if (Build.VERSION.SDK_INT < 31) goto L8;
            RenderNode r42 = BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(r5);
            RenderNode r52 = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("blurRenderNode");
            r52.setRenderEffect(RenderEffect.createBlurEffect(AndroidUtilities.dp(35.0f), AndroidUtilities.dp(35.0f), Shader.TileMode.CLAMP));
            r52.setPosition(0, 0, r42.getWidth(), r42.getHeight());
            RecordingCanvas r0 = r52.beginRecording();
            r0.drawColor(r6);
            r0.drawRenderNode(r42);
            r52.endRecording();
            this.blurRenderNode = r52;
        }

        public void setShader(BlurringShader r2) {
            if (this.currentShader == r2) goto L10;
            this.currentShader = r2;
            if (r2 == null) goto L9;
            invalidate();
            return;
        L9:
            return;
        }
    }

    public static class Program {
        int flipyHandle;
        int gl;
        int gradientBottomHandle;
        int gradientTopHandle;
        int hasVideoMatrixHandle;
        int matrixHandle;
        int posHandle;
        int stepHandle;
        int szHandle;
        int texHandle;
        int texSzHandle;
        int uvHandle;
        int videoMatrixHandle;

        public Program(int r2) {
            this.gl = r2;
            this.posHandle = GLES20.glGetAttribLocation(r2, "p");
            this.uvHandle = GLES20.glGetAttribLocation(r2, "inputuv");
            this.matrixHandle = GLES20.glGetUniformLocation(r2, "matrix");
            this.texHandle = GLES20.glGetUniformLocation(r2, "tex");
            this.szHandle = GLES20.glGetUniformLocation(r2, "sz");
            this.texSzHandle = GLES20.glGetUniformLocation(r2, "texSz");
            this.gradientTopHandle = GLES20.glGetUniformLocation(r2, "gtop");
            this.gradientBottomHandle = GLES20.glGetUniformLocation(r2, "gbottom");
            this.stepHandle = GLES20.glGetUniformLocation(r2, "step");
            this.videoMatrixHandle = GLES20.glGetUniformLocation(r2, "videoMatrix");
            this.hasVideoMatrixHandle = GLES20.glGetUniformLocation(r2, "hasVideoMatrix");
            this.flipyHandle = GLES20.glGetUniformLocation(r2, "flipy");
        }
    }

    public static class StoryBlurDrawer {
        private boolean animateBitmapChange;
        private Integer bgColor;
        private BitmapShader bitmapShader;
        RectF bounds;
        private final Path clipPath;
        private int clipPathHeight;
        private int clipPathWidth;
        public final ColorMatrix colorMatrix;
        private ValueAnimator crossfadeAnimator;
        private boolean customOffset;
        private float customOffsetX;
        private float customOffsetY;
        private Bitmap lastBitmap;
        private final int[] loc1;
        private final int[] loc2;
        private final BlurManager manager;
        private final Matrix matrix;
        public Paint oldPaint;
        private float oldPaintAlpha;
        private boolean oldPaintSet;
        public Paint paint;
        private Paint[] tempPaints;
        private final int type;
        private final View view;
        private boolean wasDark;
        public boolean xfer;

        /* renamed from: org.telegram.ui.Components.BlurringShader$StoryBlurDrawer$1, reason: invalid class name */
        public class AnonymousClass1 implements View.OnAttachStateChangeListener {
            final /* synthetic */ StoryBlurDrawer this$0;
            final /* synthetic */ BlurManager val$manager;

            public AnonymousClass1(StoryBlurDrawer r1, BlurManager r2) {
                this.this$0 = r1;
                this.val$manager = r2;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View r1) {
                BlurManager r12 = this.val$manager;
                if (r12 == null) goto L6;
                r12.attach(this.this$0);
                return;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View r2) {
                BlurManager r22 = this.val$manager;
                if (r22 == null) goto L5;
                r22.detach(this.this$0);
            L5:
                StoryBlurDrawer.m9029$$Nest$mrecycle(this.this$0);
            }
        }

        /* renamed from: org.telegram.ui.Components.BlurringShader$StoryBlurDrawer$2, reason: invalid class name */
        public class AnonymousClass2 extends Drawable {
            float alpha;
            private final Path clipPath;
            private final Paint dimPaint;
            private final Rect rect;
            final /* synthetic */ StoryBlurDrawer this$0;
            final /* synthetic */ Drawable val$base;
            final /* synthetic */ float val$offsetX;
            final /* synthetic */ float val$offsetY;
            final /* synthetic */ float val$r;

            public AnonymousClass2(StoryBlurDrawer r1, float r2, float r3, Drawable r4, float r5) {
                this.this$0 = r1;
                this.val$offsetX = r2;
                this.val$offsetY = r3;
                this.val$base = r4;
                this.val$r = r5;
                this.alpha = 1.0f;
                this.dimPaint = new Paint(1);
                this.rect = new Rect();
                this.clipPath = new Path();
            }

            private Paint getPaint() {
                if (StoryBlurDrawer.m9024$$Nest$fgetmanager(this.this$0) != null) goto L5;
                return null;
            L5:
                Bitmap r0 = StoryBlurDrawer.m9024$$Nest$fgetmanager(this.this$0).getBitmap();
                if (r0 != null) goto L9;
                return null;
            L9:
                if (StoryBlurDrawer.m9020$$Nest$fgetbitmapShader(this.this$0) != null) goto L11;
            L12:
                StoryBlurDrawer r1 = this.this$0;
                StoryBlurDrawer.m9028$$Nest$fputlastBitmap(this.this$0, r0);
                Shader.TileMode r3 = Shader.TileMode.CLAMP;
                StoryBlurDrawer.m9027$$Nest$fputbitmapShader(r1, new BitmapShader(r0, r3, r3));
                StoryBlurDrawer r12 = this.this$0;
                r12.paint.setShader(StoryBlurDrawer.m9020$$Nest$fgetbitmapShader(r12));
            L13:
                StoryBlurDrawer.m9025$$Nest$fgetmatrix(this.this$0).reset();
                StoryBlurDrawer.m9025$$Nest$fgetmatrix(this.this$0).postTranslate((-StoryBlurDrawer.m9021$$Nest$fgetcustomOffsetX(this.this$0)) - this.val$offsetX, (-StoryBlurDrawer.m9022$$Nest$fgetcustomOffsetY(this.this$0)) - this.val$offsetY);
                if (BlurManager.m9018$$Nest$fgetview(StoryBlurDrawer.m9024$$Nest$fgetmanager(this.this$0)) == null) goto L16;
                StoryBlurDrawer.m9025$$Nest$fgetmatrix(this.this$0).preScale(BlurManager.m9018$$Nest$fgetview(StoryBlurDrawer.m9024$$Nest$fgetmanager(this.this$0)).getWidth() / r0.getWidth(), BlurManager.m9018$$Nest$fgetview(StoryBlurDrawer.m9024$$Nest$fgetmanager(this.this$0)).getHeight() / r0.getHeight());
            L16:
                StoryBlurDrawer.m9020$$Nest$fgetbitmapShader(this.this$0).setLocalMatrix(StoryBlurDrawer.m9025$$Nest$fgetmatrix(this.this$0));
                this.this$0.paint.setAlpha((int) (this.alpha * 255.0f));
                return this.this$0.paint;
            L11:
                if (StoryBlurDrawer.m9023$$Nest$fgetlastBitmap(this.this$0) == r0) goto L13;
                goto L12
            }

            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas r12) {
                Paint r8 = getPaint();
                Rect r9 = getBounds();
                if (r8 != null) goto L20;
                if (StoryBlurDrawer.m9024$$Nest$fgetmanager(this.this$0) != null) goto L7;
            L9:
                Drawable r1 = this.val$base;
                if (r1 == null) goto L13;
                r1.setBounds(r9);
                this.val$base.draw(r12);
                return;
            L13:
                this.dimPaint.setColor(-14145495);
                if (this.val$r <= 0.0f) goto L17;
                RectF r13 = AndroidUtilities.rectTmp;
                r13.set(r9);
                float r3 = this.val$r;
                r12.drawRoundRect(r13, r3, r3, this.dimPaint);
                return;
            L17:
                r12.drawRect(r9, this.dimPaint);
                return;
            L7:
                if (StoryBlurDrawer.m9024$$Nest$fgetmanager(this.this$0).hasRenderNode() == false) goto L9;
            L20:
                if (this.val$base == null) goto L30;
                r12.saveLayerAlpha(r9.left, r9.top, r9.right, r9.bottom, 255, 31);
                this.val$base.setBounds(r9);
                this.val$base.draw(r12);
                if (StoryBlurDrawer.m9024$$Nest$fgetmanager(this.this$0) != null) goto L24;
            L26:
                r12.drawRect(r9, r8);
            L27:
                r12.restore();
                getPadding(this.rect);
                RectF r14 = AndroidUtilities.rectTmp;
                int r32 = r9.left;
                Rect r4 = this.rect;
                r14.set(r32 + r4.left, r9.top + r4.top, r9.right - r4.right, r9.bottom - r4.bottom);
                this.dimPaint.setColor(1711276032);
                float r33 = this.val$r;
                r12.drawRoundRect(r14, r33, r33, this.dimPaint);
                return;
            L24:
                if (StoryBlurDrawer.m9024$$Nest$fgetmanager(this.this$0).hasRenderNode() == false) goto L26;
                r12.save();
                getPadding(this.rect);
                RectF r15 = AndroidUtilities.rectTmp;
                int r34 = r9.left;
                Rect r42 = this.rect;
                r15.set(r34 + r42.left, r9.top + r42.top, r9.right - r42.right, r9.bottom - r42.bottom);
                this.clipPath.rewind();
                Path r35 = this.clipPath;
                float r43 = this.val$r;
                r35.addRoundRect(r15, r43, r43, Path.Direction.CW);
                r12.clipPath(this.clipPath);
                this.this$0.drawRect(r12, 0.0f, 0.0f, 1.0f, false);
                r12.restore();
                goto L27
            L30:
                if (this.val$r <= 0.0f) goto L38;
                RectF r16 = AndroidUtilities.rectTmp;
                r16.set(r9);
                if (StoryBlurDrawer.m9024$$Nest$fgetmanager(this.this$0) != null) goto L34;
            L36:
                float r36 = this.val$r;
                r12.drawRoundRect(r16, r36, r36, r8);
            L43:
                this.dimPaint.setColor(1711276032);
                if (this.val$r <= 0.0f) goto L47;
                RectF r17 = AndroidUtilities.rectTmp;
                r17.set(r9);
                float r37 = this.val$r;
                r12.drawRoundRect(r17, r37, r37, this.dimPaint);
                return;
            L47:
                r12.drawRect(r9, this.dimPaint);
                return;
            L34:
                if (StoryBlurDrawer.m9024$$Nest$fgetmanager(this.this$0).hasRenderNode() == false) goto L36;
                r12.save();
                this.clipPath.rewind();
                Path r38 = this.clipPath;
                float r44 = this.val$r;
                r38.addRoundRect(r16, r44, r44, Path.Direction.CW);
                r12.clipPath(this.clipPath);
                this.this$0.drawRect(r12, 0.0f, 0.0f, 1.0f, false);
                r12.restore();
                goto L43
            L38:
                if (StoryBlurDrawer.m9024$$Nest$fgetmanager(this.this$0) != null) goto L40;
            L42:
                r12.drawRect(r9, r8);
                goto L43
            L40:
                if (StoryBlurDrawer.m9024$$Nest$fgetmanager(this.this$0).hasRenderNode() == false) goto L42;
                r12.save();
                r12.clipRect(r9);
                this.this$0.drawRect(r12, 0.0f, 0.0f, 1.0f, false);
                r12.restore();
                goto L43
            }

            @Override // android.graphics.drawable.Drawable
            public int getOpacity() {
                return -2;
            }

            @Override // android.graphics.drawable.Drawable
            public boolean getPadding(Rect r1) {
                Drawable r0 = this.val$base;
                if (r0 != null) goto L5;
                r1.set(0, 0, 0, 0);
                return true;
            L5:
                return r0.getPadding(r1);
            }

            @Override // android.graphics.drawable.Drawable
            public void setAlpha(int r2) {
                this.alpha = r2 / 255.0f;
            }

            @Override // android.graphics.drawable.Drawable
            public void setColorFilter(ColorFilter r1) {
            }
        }

        public static /* synthetic */ void $r8$lambda$Uwse0Yg2MaO49N319YcCESslWvw(StoryBlurDrawer r0, ValueAnimator r1) {
            r0.lambda$animateOldPaint$0(r1);
        }

        /* renamed from: -$$Nest$fgetbitmapShader, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BitmapShader m9020$$Nest$fgetbitmapShader(StoryBlurDrawer r0) {
            return r0.bitmapShader;
        }

        /* renamed from: -$$Nest$fgetcustomOffsetX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m9021$$Nest$fgetcustomOffsetX(StoryBlurDrawer r0) {
            return r0.customOffsetX;
        }

        /* renamed from: -$$Nest$fgetcustomOffsetY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m9022$$Nest$fgetcustomOffsetY(StoryBlurDrawer r0) {
            return r0.customOffsetY;
        }

        /* renamed from: -$$Nest$fgetlastBitmap, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Bitmap m9023$$Nest$fgetlastBitmap(StoryBlurDrawer r0) {
            return r0.lastBitmap;
        }

        /* renamed from: -$$Nest$fgetmanager, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BlurManager m9024$$Nest$fgetmanager(StoryBlurDrawer r0) {
            return r0.manager;
        }

        /* renamed from: -$$Nest$fgetmatrix, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Matrix m9025$$Nest$fgetmatrix(StoryBlurDrawer r0) {
            return r0.matrix;
        }

        /* renamed from: -$$Nest$fgetview, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m9026$$Nest$fgetview(StoryBlurDrawer r0) {
            return r0.view;
        }

        /* renamed from: -$$Nest$fputbitmapShader, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m9027$$Nest$fputbitmapShader(StoryBlurDrawer r0, BitmapShader r1) {
            r0.bitmapShader = r1;
        }

        /* renamed from: -$$Nest$fputlastBitmap, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m9028$$Nest$fputlastBitmap(StoryBlurDrawer r0, Bitmap r1) {
            r0.lastBitmap = r1;
        }

        /* renamed from: -$$Nest$mrecycle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m9029$$Nest$mrecycle(StoryBlurDrawer r0) {
            r0.recycle();
        }

        public StoryBlurDrawer(BlurManager r2, View r3, int r4) {
            this(r2, r3, r4, false);
        }

        public StoryBlurDrawer(BlurManager r8, View r9, int r10, boolean r11) {
            this.oldPaint = new Paint(3);
            this.paint = new Paint(3);
            this.clipPath = new Path();
            this.matrix = new Matrix();
            this.bounds = new RectF();
            this.wasDark = false;
            this.loc1 = new int[2];
            this.loc2 = new int[2];
            this.manager = r8;
            this.view = r9;
            this.type = r10;
            this.animateBitmapChange = r11;
            ColorMatrix r112 = new ColorMatrix();
            this.colorMatrix = r112;
            if (r10 != 0) goto L6;
            AndroidUtilities.adjustSaturationColorMatrix(r112, 0.45f);
        L43:
            this.paint.setColorFilter(new ColorMatrixColorFilter(r112));
            this.oldPaint.setColorFilter(new ColorMatrixColorFilter(r112));
            if (r9.isAttachedToWindow() == false) goto L47;
            if (r8 == null) goto L47;
            r8.attach(this);
        L47:
            r9.addOnAttachStateChangeListener(new AnonymousClass1(this, r8));
            return;
        L6:
            if (r10 != 5) goto L9;
            Paint r102 = this.paint;
            PorterDuff.Mode r1 = PorterDuff.Mode.SRC_IN;
            r102.setXfermode(new PorterDuffXfermode(r1));
            this.oldPaint.setXfermode(new PorterDuffXfermode(r1));
            AndroidUtilities.adjustSaturationColorMatrix(r112, 0.3f);
            this.xfer = true;
            goto L43
        L9:
            if (r10 != 2) goto L12;
            Paint r103 = this.paint;
            PorterDuff.Mode r12 = PorterDuff.Mode.SRC_IN;
            r103.setXfermode(new PorterDuffXfermode(r12));
            this.oldPaint.setXfermode(new PorterDuffXfermode(r12));
            AndroidUtilities.adjustBrightnessColorMatrix(r112, 0.4f);
            AndroidUtilities.adjustSaturationColorMatrix(r112, 0.3f);
            this.xfer = true;
            goto L43
        L12:
            if (r10 != 1) goto L15;
            AndroidUtilities.adjustSaturationColorMatrix(r112, 0.35f);
            AndroidUtilities.adjustBrightnessColorMatrix(r112, 0.7f);
            AndroidUtilities.multiplyBrightnessColorMatrix(r112, 1.5f);
            goto L43
        L15:
            if (r10 != 3) goto L18;
            AndroidUtilities.adjustSaturationColorMatrix(r112, 0.5f);
            goto L43
        L18:
            if (r10 != 4) goto L21;
            this.bgColor = -10329502;
            AndroidUtilities.adjustSaturationColorMatrix(r112, 0.6f);
            AndroidUtilities.adjustBrightnessColorMatrix(r112, 0.3f);
            AndroidUtilities.multiplyBrightnessColorMatrix(r112, 1.2f);
            goto L43
        L21:
            if (r10 != 6) goto L24;
            AndroidUtilities.adjustSaturationColorMatrix(r112, 0.4f);
            AndroidUtilities.multiplyBrightnessColorMatrix(r112, 0.35f);
            goto L43
        L24:
            if (r10 != 7) goto L27;
            AndroidUtilities.adjustSaturationColorMatrix(r112, 0.5f);
            AndroidUtilities.multiplyBrightnessColorMatrix(r112, 0.95f);
            goto L43
        L27:
            if (r10 != 8) goto L30;
            AndroidUtilities.adjustBrightnessColorMatrix(r112, -0.15f);
            AndroidUtilities.adjustSaturationColorMatrix(r112, 0.47f);
            goto L43
        L30:
            if (r10 != 9) goto L33;
            Paint r104 = this.paint;
            PorterDuff.Mode r13 = PorterDuff.Mode.SRC_IN;
            r104.setXfermode(new PorterDuffXfermode(r13));
            this.oldPaint.setXfermode(new PorterDuffXfermode(r13));
            AndroidUtilities.adjustBrightnessColorMatrix(r112, 0.4f);
            AndroidUtilities.adjustSaturationColorMatrix(r112, 0.45f);
            this.xfer = true;
            goto L43
        L33:
            if (r10 != 10) goto L43;
            r112.setSaturation(1.6f);
            if (this.wasDark == false) goto L37;
            float r105 = 0.97f;
        L38:
            AndroidUtilities.multiplyBrightnessColorMatrix(r112, r105);
            if (this.wasDark == false) goto L41;
            float r106 = 0.12f;
        L42:
            AndroidUtilities.adjustBrightnessColorMatrix(r112, r106);
            goto L43
        L41:
            r106 = -0.06f;
            goto L42
        L37:
            r105 = 0.92f;
            goto L38
        }

        private void animateOldPaint() {
            ValueAnimator r0 = this.crossfadeAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
            this.crossfadeAnimator = null;
        L5:
            this.oldPaintAlpha = 1.0f;
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
            this.crossfadeAnimator = r02;
            r02.addUpdateListener(new BlurringShader$StoryBlurDrawer$$ExternalSyntheticLambda0(this));
            this.crossfadeAnimator.start();
        }

        private int getBackgroundColor() {
            Integer r0 = this.bgColor;
            if (r0 == null) goto L7;
            return r0.intValue();
        L7:
            return BlurManager.m9016$$Nest$fgetrenderNodeBackgroundColor(this.manager);
        }

        private /* synthetic */ void lambda$animateOldPaint$0(ValueAnimator r1) {
            this.oldPaintAlpha = ((Float) r1.getAnimatedValue()).floatValue();
            this.view.invalidate();
        }

        private void recycle() {
            this.lastBitmap = null;
            Paint r1 = this.paint;
            this.bitmapShader = null;
            r1.setShader(null);
        }

        private boolean setupMatrix(int r9, int r10, boolean r11) {
            this.matrix.reset();
            BlurManager r0 = this.manager;
            if (r0 == null) goto L7;
            if (r11 == false) goto L6;
            View r112 = BlurManager.m9017$$Nest$fgetrenderNodeView(r0);
        L9:
            if (this.customOffset == false) goto L12;
            this.matrix.postTranslate(-this.customOffsetX, -this.customOffsetY);
        L33:
            if (r112 == null) goto L35;
            this.matrix.preScale(r112.getWidth() / r9, r112.getHeight() / r10);
        L35:
            return true;
        L12:
            if (this.manager == null) goto L33;
            View r02 = this.view;
        L14:
            this.matrix.preScale(1.0f / r02.getScaleX(), 1.0f / r02.getScaleY(), r02.getPivotX(), r02.getPivotY());
            this.matrix.preRotate(-r02.getRotation(), r02.getPivotX(), r02.getPivotY());
            this.matrix.preTranslate(-r02.getX(), -r02.getY());
            if ((r02.getParent() instanceof View) == false) goto L20;
            r02 = (View) r02.getParent();
            if (r02 == null) goto L20;
            if (BlurManager.m9014$$Nest$fgetparents(this.manager).contains(r02) == false) goto L14;
        L20:
            if (r112 == r02) goto L33;
            int r2 = BlurManager.m9014$$Nest$fgetparents(this.manager).indexOf(r02) + 1;
            if (r2 != 0) goto L26;
            View r3 = (View) BlurManager.m9014$$Nest$fgetparents(this.manager).get(r2);
            if (r3 == null) goto L26;
            r02.getLocationOnScreen(this.loc1);
            r3.getLocationOnScreen(this.loc2);
            Matrix r03 = this.matrix;
            int r5 = this.loc2[0];
            int[] r6 = this.loc1;
            r03.preTranslate(r5 - r6[0], r3[1] - r6[1]);
        L26:
            if (r2 < 0) goto L33;
            if (r2 >= BlurManager.m9014$$Nest$fgetparents(this.manager).size()) goto L33;
            View r04 = (View) BlurManager.m9014$$Nest$fgetparents(this.manager).get(r2);
            if (r04 == null) goto L26;
            this.matrix.preScale(r04.getScaleX(), r04.getScaleY(), r04.getPivotX(), r04.getPivotY());
            this.matrix.preRotate(r04.getRotation(), r04.getPivotX(), r04.getPivotY());
            this.matrix.preTranslate(r04.getX(), r04.getY());
            r2 = r2 + 1;
            goto L26
        L6:
            r112 = BlurManager.m9018$$Nest$fgetview(r0);
            goto L9
        L7:
            r112 = null;
            goto L9
        }

        private void updateBounds() {
            Bitmap r0 = this.manager.getBitmap();
            if (r0 != null) goto L6;
            return;
        L6:
            if (this.bitmapShader != null) goto L8;
        L9:
            this.lastBitmap = r0;
            Shader.TileMode r2 = Shader.TileMode.CLAMP;
            BitmapShader r1 = new BitmapShader(r0, r2, r2);
            this.bitmapShader = r1;
            this.paint.setShader(r1);
        L10:
            float r02 = this.bounds.width() / this.lastBitmap.getWidth();
            float r12 = this.bounds.height() / this.lastBitmap.getHeight();
            this.matrix.reset();
            Matrix r22 = this.matrix;
            RectF r3 = this.bounds;
            r22.postTranslate(r3.left, r3.top);
            this.matrix.preScale(r02, r12);
            this.bitmapShader.setLocalMatrix(this.matrix);
            return;
        L8:
            if (this.lastBitmap == r0) goto L10;
            goto L9
        }

        public StoryBlurDrawer adapt(boolean r3) {
            if (this.wasDark == r3) goto L15;
            this.wasDark = r3;
            if (this.type != 10) goto L15;
            ColorMatrix r32 = new ColorMatrix();
            r32.setSaturation(1.6f);
            if (this.wasDark == false) goto L9;
            float r0 = 0.97f;
        L10:
            AndroidUtilities.multiplyBrightnessColorMatrix(r32, r0);
            if (this.wasDark == false) goto L13;
            float r02 = 0.12f;
        L14:
            AndroidUtilities.adjustBrightnessColorMatrix(r32, r02);
            this.paint.setColorFilter(new ColorMatrixColorFilter(r32));
            this.oldPaint.setColorFilter(new ColorMatrixColorFilter(r32));
            goto L15
        L13:
            r02 = -0.06f;
            goto L14
        L9:
            r0 = 0.92f;
        L15:
            return this;
        }

        public void drawRect(Canvas r3) {
            drawRect(r3, 0.0f, 0.0f, 1.0f);
        }

        public void drawRect(Canvas r7, float r8, float r9, float r10) {
            drawRect(r7, r8, r9, r10, true);
        }

        public void drawRect(Canvas r6, float r7, float r8, float r9, boolean r10) {
            if (this.manager.hasRenderNode() == true) goto L5;
        L31:
            Paint r5 = getPaint(r9, r7, r8);
            if (r5 == null) goto L37;
            r6.drawPaint(r5);
            return;
        L37:
            return;
        L5:
            if (Build.VERSION.SDK_INT < 31) goto L31;
            if (r6.isHardwareAccelerated() == true) goto L10;
            r6.drawColor(getBackgroundColor());
            return;
        L10:
            RenderNode r0 = BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(BlurManager.m9013$$Nest$fgetblurRenderNode(this.manager));
            if (r0.hasDisplayList() == true) goto L14;
            RenderNode r1 = BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(BlurManager.m9015$$Nest$fgetrenderNode(this.manager));
            r0.setPosition(0, 0, r1.getWidth(), r1.getHeight());
            RecordingCanvas r2 = r0.beginRecording();
            r2.drawColor(getBackgroundColor());
            r2.drawRenderNode(r1);
            r0.endRecording();
        L14:
            if (r0.hasDisplayList() == true) goto L17;
            r6.drawColor(getBackgroundColor());
            return;
        L17:
            r6.drawColor(getBackgroundColor());
            if (setupMatrix(r0.getWidth(), r0.getHeight(), true) == true) goto L20;
            return;
        L20:
            if (r0.hasDisplayList() == false) goto L36;
            this.matrix.postTranslate(-r7, -r8);
            this.paint.setAlpha((int) (r9 * 255.0f));
            r6.saveLayer(null, this.paint);
            r6.concat(this.matrix);
            if (r10 == true) goto L24;
        L29:
            r6.drawRenderNode(r0);
            r6.restore();
            return;
        L24:
            if (this.clipPathWidth == r0.getWidth()) goto L26;
        L27:
            this.clipPath.rewind();
            RectF r72 = AndroidUtilities.rectTmp;
            int r82 = r0.getWidth();
            this.clipPathWidth = r82;
            int r92 = r0.getHeight();
            this.clipPathHeight = r92;
            r72.set(0.0f, 0.0f, r82, r92);
            this.clipPath.addRoundRect(r72, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), Path.Direction.CW);
        L28:
            r6.clipPath(this.clipPath);
            goto L29
        L26:
            if (this.clipPathHeight == r0.getHeight()) goto L28;
        }

        public Paint getPaint(float r2) {
            return getPaint(r2, 0.0f, 0.0f);
        }

        public Paint getPaint(float r5, float r6, float r7) {
            BlurManager r0 = this.manager;
            if (r0 != null) goto L5;
            return null;
        L5:
            Bitmap r02 = r0.getBitmap();
            if (r02 != null) goto L8;
            return null;
        L8:
            BitmapShader r2 = this.bitmapShader;
            if (r2 == null) goto L13;
            if (this.lastBitmap != r02) goto L13;
        L24:
            if (setupMatrix(r02.getWidth(), r02.getHeight(), false) == true) goto L26;
            return null;
        L26:
            this.matrix.postTranslate(-r6, -r7);
            this.bitmapShader.setLocalMatrix(this.matrix);
            this.paint.setAlpha((int) (r5 * 255.0f));
            return this.paint;
        L13:
            if (this.animateBitmapChange == false) goto L22;
            if (r2 == null) goto L22;
            Bitmap r22 = this.lastBitmap;
            if (r22 == null) goto L22;
            if (r22.isRecycled() == true) goto L22;
            if (r02.isRecycled() == true) goto L22;
            Paint r23 = this.paint;
            this.paint = this.oldPaint;
            this.oldPaint = r23;
            this.oldPaintSet = true;
            animateOldPaint();
        L22:
            this.lastBitmap = r02;
            Shader.TileMode r3 = Shader.TileMode.CLAMP;
            BitmapShader r24 = new BitmapShader(r02, r3, r3);
            this.bitmapShader = r24;
            this.paint.setShader(r24);
            goto L24
        }

        public Paint[] getPaints(float r4, float r5, float r6) {
            Paint r52 = getPaint(r4, r5, r6);
            boolean r62 = this.oldPaintSet;
            if (r62 == false) goto L5;
            Paint r0 = this.oldPaint;
        L7:
            if (r52 == null) goto L10;
            if (r62 == false) goto L10;
            r52.setAlpha((int) (((1.0f - this.oldPaintAlpha) * 255.0f) * r4));
        L10:
            if (r0 == null) goto L13;
            r0.setAlpha((int) (r4 * 255.0f));
        L13:
            if (this.tempPaints != null) goto L15;
            this.tempPaints = new Paint[2];
        L15:
            Paint[] r3 = this.tempPaints;
            r3[0] = r0;
            r3[1] = r52;
            return r3;
        L5:
            r0 = null;
            goto L7
        }

        public Drawable makeDrawable(float r7, float r8, Drawable r9, float r10) {
            return new AnonymousClass2(this, r7, r8, r9, r10);
        }

        public void setBounds(float r2, float r3, float r4, float r5) {
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(r2, r3, r4, r5);
            setBounds(r0);
        }

        public void setBounds(RectF r4) {
            RectF r0 = this.bounds;
            if (r0.top == r4.top) goto L5;
        L11:
            r0.set(r4);
            updateBounds();
            return;
        L5:
            if (r0.bottom != r4.bottom) goto L11;
            if (r0.left != r4.left) goto L11;
            if (r0.right != r4.right) goto L11;
        }
    }

    public static class ThumbBlurer {
        private final Paint clearPaint;
        private Runnable generate;
        private final Runnable invalidate;
        private final int padding;
        private Bitmap thumbBitmap;
        private String thumbKey;

        public static /* synthetic */ void $r8$lambda$OrHVDsAZ8dT1x1nPFZGhWxsDW9Y(ThumbBlurer r0, String r1, Bitmap r2, boolean r3, Bitmap r4) {
            r0.lambda$getBitmap$0(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$sCzQkgg34e7CnmI9RpuBG_CiEas(ThumbBlurer r0, Bitmap r1, int r2, int r3, String r4, boolean r5) {
            r0.lambda$getBitmap$1(r1, r2, r3, r4, r5);
        }

        /* renamed from: -$$Nest$fgetthumbBitmap, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Bitmap m9030$$Nest$fgetthumbBitmap(ThumbBlurer r0) {
            return r0.thumbBitmap;
        }

        public ThumbBlurer(int r3, Runnable r4) {
            Paint r0 = new Paint(1);
            this.clearPaint = r0;
            this.padding = r3;
            this.invalidate = r4;
            r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }

        private /* synthetic */ void lambda$getBitmap$0(String r2, Bitmap r3, boolean r4, Bitmap r5) {
            if (TextUtils.equals(this.thumbKey, r2) == false) goto L10;
            this.generate = null;
            Bitmap r22 = this.thumbBitmap;
            if (r22 == null) goto L7;
            r22.recycle();
        L7:
            this.thumbBitmap = r3;
            Runnable r1 = this.invalidate;
            if (r1 == null) goto L11;
            r1.run();
        L11:
            if (r4 == false) goto L14;
            r5.recycle();
            return;
        L14:
            return;
        L10:
            r3.recycle();
            goto L11
        }

        private /* synthetic */ void lambda$getBitmap$1(Bitmap r18, int r19, int r20, String r21, boolean r22) {
            if (r18 != null) goto L5;
            return;
        L5:
            if (r18.isRecycled() == true) goto L30;
            float r3 = r18.getWidth() / r18.getHeight();
            int r6 = (int) Math.round(Math.sqrt(r3 * 324.0f));
            int r32 = (int) Math.round(Math.sqrt(324.0f / r3));
            if (r19 != 90) goto L10;
        L13:
            int r4 = r32;
            int r7 = r6;
        L14:
            int r8 = this.padding;
            Bitmap r82 = Bitmap.createBitmap((r8 * 2) + r4, (r8 * 2) + r7, Bitmap.Config.ARGB_8888);
            Canvas r11 = new Canvas(r82);
            Rect r9 = new Rect(0, 0, r18.getWidth(), r18.getHeight());
            int r13 = this.padding;
            Rect r12 = new Rect(r13, r13, r13 + r6, r13 + r32);
            int r132 = this.padding;
            r11.translate(r132 + (r4 / 2.0f), r132 + (r7 / 2.0f));
            if (r20 != 1) goto L17;
            r11.scale(-1.0f, 1.0f);
        L19:
            r11.rotate(r19);
            int r0 = this.padding;
            r11.translate((-r0) - (r6 / 2.0f), (-r0) - (r32 / 2.0f));
            r11.drawBitmap(r18, r9, r12, null);     // Catch: Exception -> L26
        L21:
            Utilities.stackBlurBitmap(r82, 6);
            int r02 = this.padding;
            if (r02 <= 0) goto L24;
            r11.drawRect(0.0f, 0.0f, r6 + r02, r02, this.clearPaint);
            int r03 = this.padding;
            r11.drawRect(0.0f, r03, r03, r03 + r32, this.clearPaint);
            r11.drawRect(r0 + r6, this.padding, (r0 + r6) + r0, r0 + r32, this.clearPaint);
            int r04 = this.padding;
            r11.drawRect(0.0f, r04 + r32, (r6 + r04) + r04, (r32 + r04) + r04, this.clearPaint);
        L24:
            AndroidUtilities.runOnUIThread(new BlurringShader$ThumbBlurer$$ExternalSyntheticLambda1(this, r21, r82, r22, r18));
            return;
        L17:
            if (r20 != 2) goto L19;
            r11.scale(1.0f, -1.0f);
            goto L19
        L10:
            if (r19 == 270) goto L13;
            r7 = r32;
            r4 = r6;
            goto L14
        }

        public void destroy() {
            this.thumbKey = null;
            if (this.generate == null) goto L5;
            Utilities.globalQueue.cancelRunnable(this.generate);
        L5:
            Bitmap r1 = this.thumbBitmap;
            if (r1 != null) goto L8;
        L10:
            this.thumbBitmap = null;
            return;
        L8:
            if (r1.isRecycled() == true) goto L10;
            this.thumbBitmap.recycle();
            goto L10
        }

        public Bitmap getBitmap(Bitmap r9, String r10, int r11, int r12, boolean r13) {
            if (r9 != null) goto L5;
        L20:
            return null;
        L5:
            if (r9.isRecycled() == true) goto L20;
            if (TextUtils.equals(this.thumbKey, r10) == false) goto L16;
            Bitmap r1 = this.thumbBitmap;
            if (r1 == null) goto L13;
            return r1;
        L13:
            if (this.generate == null) goto L16;
            return null;
        L16:
            if (this.generate == null) goto L18;
            Utilities.globalQueue.cancelRunnable(this.generate);
        L18:
            this.thumbKey = r10;
            DispatchQueue r0 = Utilities.globalQueue;
            BlurringShader$ThumbBlurer$$ExternalSyntheticLambda0 r14 = new BlurringShader$ThumbBlurer$$ExternalSyntheticLambda0(this, r9, r11, r12, r10, r13);
            this.generate = r14;
            r0.postRunnable(r14);
            return this.thumbBitmap;
        }

        public Bitmap getBitmap(ImageReceiver.BitmapHolder r7) {
            if (r7 != null) goto L6;
            return null;
        L6:
            return getBitmap(r7.bitmap, r7.getKey(), r7.orientation, 0, false);
        }

        public Bitmap getBitmap(ImageReceiver r7) {
            if (r7 != null) goto L6;
            return null;
        L6:
            return getBitmap(r7.getBitmap(), r7.getImageKey(), r7.getOrientation(), r7.getInvert(), false);
        }
    }

    public static /* synthetic */ void $r8$lambda$8Ot07mOhkl5TO31vnwcBKvXUcqE(BlurringShader r0) {
        r0.lambda$new$0();
    }

    public BlurringShader() {
        this(null);
    }

    public BlurringShader(FilterGLThread r3) {
        this.width = 1;
        this.height = 1;
        this.padding = 0;
        this.program = new Program[2];
        this.m3x3 = new float[9];
        this.matrix = new float[16];
        this.matrixLock = new Object();
        this.bitmapLock = new Object();
        this.framebuffer = new int[3];
        this.texture = new int[3];
        this.invalidateViews = new BlurringShader$$ExternalSyntheticLambda0(this);
        this.iMatrix = new Matrix();
        this.parent = r3;
    }

    private /* synthetic */ void lambda$new$0() {
        BlurManager r0 = this.currentManager;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    public void draw(float[] r22, int r23, int r24, int r25) {
        if (r22 == null) goto L5;
        char r5 = 1;
    L6:
        Program r6 = this.program[r5];
        if (r6 == null) goto L77;
        GLES20.glBindFramebuffer(36160, this.framebuffer[0]);
        GLES20.glViewport(0, 0, this.width, this.height);
        GLES20.glClear(16384);
        GLES20.glUseProgram(r6.gl);
        GLES20.glUniform1i(r6.texHandle, 0);
        GLES20.glActiveTexture(33984);
        if (r5 == 0) goto L12;
        GLES20.glBindTexture(36197, r23);
    L13:
        GLES20.glEnableVertexAttribArray(r6.uvHandle);
        GLES20.glVertexAttribPointer(r6.uvHandle, 2, 5126, false, 8, this.uvBuffer);
        GLES20.glEnableVertexAttribArray(r6.posHandle);
        GLES20.glVertexAttribPointer(r6.posHandle, 2, 5126, false, 8, this.posBuffer);
        GLES20.glUniform2f(r6.szHandle, this.width, this.height);
        float r11 = r24;
        float r12 = r25;
        GLES20.glUniform2f(r6.texSzHandle, r11, r12);
        GLES20.glUniform1i(r6.stepHandle, 0);
        int r2 = r6.flipyHandle;
        float r13 = 1.0f;
        if (r5 == 0) goto L16;
        float r15 = 1.0f;
    L17:
        GLES20.glUniform1f(r2, r15);
        if (r5 == 0) goto L20;
        GLES20.glUniformMatrix4fv(r6.videoMatrixHandle, 1, false, r22, 0);
    L20:
        int r1 = r6.hasVideoMatrixHandle;
        if (r5 != 0) goto L24;
        r13 = 0.0f;
    L24:
        GLES20.glUniform1f(r1, r13);
        org.telegram.ui.Components.Paint.Shader.SetColorUniform(r6.gradientTopHandle, this.gradientTop);
        org.telegram.ui.Components.Paint.Shader.SetColorUniform(r6.gradientBottomHandle, this.gradientBottom);
        Object r14 = this.matrixLock;
        monitor-enter(r14);
        GLES20.glUniformMatrix4fv(r6.matrixHandle, 1, false, this.matrix, 0);     // Catch: Throwable -> L66
        monitor-exit(r14);     // Catch: Throwable -> L66
        GLES20.glDrawArrays(5, 0, 4);
        if (r5 == 0) goto L41;
        r6 = this.program[0];
        if (r6 != null) goto L33;
        return;
    L33:
        GLES20.glUseProgram(r6.gl);
        GLES20.glEnableVertexAttribArray(r6.uvHandle);
        GLES20.glVertexAttribPointer(r6.uvHandle, 2, 5126, false, 8, this.uvBuffer);
        GLES20.glEnableVertexAttribArray(r6.posHandle);
        GLES20.glVertexAttribPointer(r6.posHandle, 2, 5126, false, 8, this.posBuffer);
        GLES20.glUniform2f(r6.szHandle, this.width, this.height);
        GLES20.glUniform2f(r6.texSzHandle, r11, r12);
        GLES20.glUniform1i(r6.stepHandle, 0);
        org.telegram.ui.Components.Paint.Shader.SetColorUniform(r6.gradientTopHandle, this.gradientTop);
        org.telegram.ui.Components.Paint.Shader.SetColorUniform(r6.gradientBottomHandle, this.gradientBottom);
        GLES20.glUniform1f(r6.flipyHandle, 0.0f);
        Object r52 = this.matrixLock;
        monitor-enter(r52);
        GLES20.glUniformMatrix4fv(r6.matrixHandle, 1, false, this.matrix, 0);     // Catch: Throwable -> L38
        monitor-exit(r52);     // Catch: Throwable -> L38
    L38:
        th = move-exception;
        throw th;
    L41:
        GLES20.glBindFramebuffer(36160, this.framebuffer[1]);
        GLES20.glUniform1i(r6.stepHandle, 1);
        GLES20.glUniform1i(r6.texHandle, 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.texture[0]);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glBindFramebuffer(36160, this.framebuffer[2]);
        int r53 = this.width;
        int r122 = this.padding;
        GLES20.glViewport(0, 0, r53 + (r122 * 2), this.height + (r122 * 2));
        GLES20.glClear(16384);
        GLES20.glEnableVertexAttribArray(r6.posHandle);
        GLES20.glVertexAttribPointer(r6.posHandle, 2, 5126, false, 8, this.padPosBuffer);
        GLES20.glUniform1i(r6.stepHandle, 2);
        GLES20.glUniform1i(r6.texHandle, 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.texture[1]);
        BlurManager r54 = this.currentManager;
        if (r54 == null) goto L44;
        Object r55 = r54.getTextureLock();
    L45:
        if (r55 == null) goto L53;
        monitor-enter(r55);
        GLES20.glDrawArrays(5, 0, 4);     // Catch: Throwable -> L50
        monitor-exit(r55);     // Catch: Throwable -> L50
    L54:
        ByteBuffer r16 = this.buffer;
        if (r16 == null) goto L64;
        r16.rewind();
        int r17 = this.width;
        int r26 = this.padding;
        GLES20.glReadPixels(0, 0, r17 + (r26 * 2), this.height + (r26 * 2), 6408, 5121, this.buffer);
        Object r18 = this.bitmapLock;
        monitor-enter(r18);
        this.bitmap.copyPixelsFromBuffer(this.buffer);     // Catch: Throwable -> L61
        this.bitmapAvailable = true;     // Catch: Throwable -> L61
        monitor-exit(r18);     // Catch: Throwable -> L61
        GLES20.glBindFramebuffer(36160, 0);
    L61:
        th = move-exception;
        throw th;
    L64:
        AndroidUtilities.cancelRunOnUIThread(this.invalidateViews);
        AndroidUtilities.runOnUIThread(this.invalidateViews);
        return;
    L50:
        th = move-exception;
        throw th;
    L53:
        GLES20.glDrawArrays(5, 0, 4);
        goto L54
    L44:
        r55 = null;
    L66:
        th = move-exception;
        throw th;
    L16:
        r15 = 0.0f;
        goto L17
    L12:
        GLES20.glBindTexture(3553, r23);
        goto L13
    L77:
        return;
    L5:
        r5 = 0;
        goto L6
    }

    public Bitmap getBitmap() {
        Object r0 = this.bitmapLock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.bitmapAvailable == false) goto L7;
        Bitmap r2 = this.bitmap;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r2;
    L7:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return null;
    }

    public int getTexture() {
        return this.texture[2];
    }

    public void resetBitmap() {
        Object r0 = this.bitmapLock;
        monitor-enter(r0);
        this.bitmapAvailable = false;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public void setBlurManager(BlurManager r3) {
        BlurManager r0 = this.currentManager;
        if (r0 == null) goto L5;
        r0.setShader(null);
    L5:
        this.currentManager = r3;
        if (r3 == null) goto L9;
        r3.setShader(this);
        return;
    }

    public boolean setup(float r22, boolean r23, int r24) {
        this.width = (int) Math.round(Math.sqrt(r22 * 324.0f));
        this.height = (int) Math.round(Math.sqrt(324.0f / r22));
        this.padding = r24;
        if (this.setupTransform == true) goto L5;
        updateTransform(new Matrix(), 1, 1);
    L5:
        float[] r4 = new float[8];
        r4[0] = -1.0f;
        r4[1] = 1.0f;
        r4[2] = 1.0f;
        r4[3] = 1.0f;
        r4[4] = -1.0f;
        r4[5] = -1.0f;
        r4[6] = 1.0f;
        r4[7] = -1.0f;
        ByteBuffer r7 = ByteBuffer.allocateDirect(32);
        r7.order(ByteOrder.nativeOrder());
        FloatBuffer r72 = r7.asFloatBuffer();
        this.posBuffer = r72;
        r72.put(r4);
        this.posBuffer.position(0);
        int r73 = 0;
    L6:
        if (r73 >= 4) goto L8;
        int r11 = r73 * 2;
        r4[r11] = r4[r11] * ((r13 - r24) / this.width);
        int r112 = r11 + 1;
        r4[r112] = r4[r112] * ((r13 - r24) / this.height);
        r73 = r73 + 1;
        goto L6
    L8:
        ByteBuffer r74 = ByteBuffer.allocateDirect(32);
        r74.order(ByteOrder.nativeOrder());
        FloatBuffer r75 = r74.asFloatBuffer();
        this.padPosBuffer = r75;
        r75.put(r4);
        this.padPosBuffer.position(0);
        ByteBuffer r42 = ByteBuffer.allocateDirect(32);
        r42.order(ByteOrder.nativeOrder());
        FloatBuffer r43 = r42.asFloatBuffer();
        this.uvBuffer = r43;
        r43.put(new float[]{0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        this.uvBuffer.position(0);
        String r2 = AndroidUtilities.readRes(R.raw.blur_vrt);
        String r44 = AndroidUtilities.readRes(R.raw.blur_frg);
        if (r2 == null) goto L45;
        if (r44 == null) goto L45;
        int r6 = 0;
    L13:
        if (r6 >= 2) goto L26;
        if (r6 != 1) goto L16;
        r44 = "#extension GL_OES_EGL_image_external : require\n" + r44.replace("sampler2D tex", "samplerExternalOES tex");
    L16:
        int r76 = FilterShaders.loadShader(35633, r2);
        int r113 = FilterShaders.loadShader(35632, r44);
        if (r76 == 0) goto L25;
        if (r113 == 0) goto L25;
        int r12 = GLES20.glCreateProgram();
        GLES20.glAttachShader(r12, r76);
        GLES20.glAttachShader(r12, r113);
        GLES20.glBindAttribLocation(r12, 0, "p");
        GLES20.glBindAttribLocation(r12, 1, "inputuv");
        GLES20.glLinkProgram(r12);
        int[] r77 = new int[1];
        GLES20.glGetProgramiv(r12, 35714, r77, 0);
        if (r77[0] == 0) goto L22;
        this.program[r6] = new Program(r12);
        r6 = r6 + 1;
        goto L13
    L22:
        GLES20.glDeleteProgram(r12);
        return false;
    L25:
        return false;
    L26:
        GLES20.glGenFramebuffers(3, this.framebuffer, 0);
        GLES20.glGenTextures(3, this.texture, 0);
        int r25 = 0;
    L28:
        if (r25 >= 3) goto L41;
        GLES20.glBindTexture(3553, this.texture[r25]);
        int r62 = this.width;
        if (r25 != 2) goto L32;
        int r114 = r24 * 2;
    L33:
        int r15 = r62 + r114;
        int r63 = this.height;
        if (r25 != 2) goto L36;
        int r115 = r24 * 2;
    L37:
        GLES20.glTexImage2D(3553, 0, 6408, r15, r63 + r115, 0, 6408, 5121, null);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glBindFramebuffer(36160, this.framebuffer[r25]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.texture[r25], 0);
        if (GLES20.glCheckFramebufferStatus(36160) != 36053) goto L39;
        r25 = r25 + 1;
        goto L28
    L39:
        return false;
    L36:
        r115 = 0;
        goto L37
    L32:
        r114 = 0;
        goto L33
    L41:
        GLES20.glBindFramebuffer(36160, 0);
        if (r23 == false) goto L44;
        int r1 = r24 * 2;
        this.bitmap = Bitmap.createBitmap(this.width + r1, this.height + r1, Bitmap.Config.ARGB_8888);
        this.buffer = ByteBuffer.allocateDirect(((this.width + r1) * (r1 + this.height)) * 4);
    L44:
        return true;
    L45:
        return false;
    }

    public void updateGradient(int r1, int r2) {
        this.gradientTop = r1;
        this.gradientBottom = r2;
    }

    public void updateTransform(Matrix r8) {
        this.setupTransform = true;
        r8.getValues(this.m3x3);
        Object r82 = this.matrixLock;
        monitor-enter(r82);
        float[] r1 = this.matrix;     // Catch: Throwable -> L7
        float[] r7 = this.m3x3;     // Catch: Throwable -> L7
        r1[0] = r7[0];     // Catch: Throwable -> L7
        r1[1] = r7[3];     // Catch: Throwable -> L7
        r1[2] = 0.0f;     // Catch: Throwable -> L7
        r1[3] = r7[6];     // Catch: Throwable -> L7
        r1[4] = r7[1];     // Catch: Throwable -> L7
        r1[5] = r7[4];     // Catch: Throwable -> L7
        r1[6] = 0.0f;     // Catch: Throwable -> L7
        r1[7] = r7[7];     // Catch: Throwable -> L7
        r1[8] = 0.0f;     // Catch: Throwable -> L7
        r1[9] = 0.0f;     // Catch: Throwable -> L7
        r1[10] = 1.0f;     // Catch: Throwable -> L7
        r1[11] = 0.0f;     // Catch: Throwable -> L7
        r1[12] = r7[2];     // Catch: Throwable -> L7
        r1[13] = r7[5];     // Catch: Throwable -> L7
        r1[14] = 0.0f;     // Catch: Throwable -> L7
        r1[15] = r7[8];     // Catch: Throwable -> L7
        monitor-exit(r82);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void updateTransform(Matrix r2, int r3, int r4) {
        r2.invert(this.iMatrix);
        float r32 = r3;
        float r42 = r4;
        this.iMatrix.preScale(r32, r42);
        this.iMatrix.postScale(1.0f / r32, 1.0f / r42);
        updateTransform(this.iMatrix);
    }
}
