package org.telegram.ui.Components;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLUtils;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Choreographer;
import android.view.TextureView;
import android.view.View;
import com.google.zxing.common.detector.MathUtils;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.EmuDetector;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Cells.BaseCell;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.ChatActivity;

/* loaded from: classes7.dex */
public class ThanosEffect extends TextureView {
    private static Boolean nothanos;
    public boolean destroyed;
    private DrawingThread drawThread;
    private final Choreographer.FrameCallback frameCallback;
    private final ArrayList<ToSet> toSet;
    private Runnable whenDone;

    /* renamed from: org.telegram.ui.Components.ThanosEffect$1, reason: invalid class name */
    public class AnonymousClass1 implements Choreographer.FrameCallback {
        final /* synthetic */ ThanosEffect this$0;

        public AnonymousClass1(ThanosEffect r1) {
            this.this$0 = r1;
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long r1) {
            if (ThanosEffect.m13217$$Nest$fgetdrawThread(this.this$0) == null) goto L8;
            ThanosEffect.m13217$$Nest$fgetdrawThread(this.this$0).requestDraw();
            if (ThanosEffect.m13217$$Nest$fgetdrawThread(this.this$0).running == false) goto L9;
            Choreographer.getInstance().postFrameCallback(this);
            return;
        L9:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ThanosEffect$2, reason: invalid class name */
    public class AnonymousClass2 implements TextureView.SurfaceTextureListener {
        final /* synthetic */ ThanosEffect this$0;

        public static /* synthetic */ void $r8$lambda$ZrOiJS0GhkZ4ftX0wMMR5oGEYnk(ThanosEffect r0) {
            ThanosEffect.m13223$$Nest$mdestroy(r0);
        }

        public AnonymousClass2(ThanosEffect r1) {
            this.this$0 = r1;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture r8, int r9, int r10) {
            if (ThanosEffect.m13217$$Nest$fgetdrawThread(this.this$0) == null) goto L5;
            ThanosEffect.m13217$$Nest$fgetdrawThread(this.this$0).kill();
            ThanosEffect.m13221$$Nest$fputdrawThread(this.this$0, null);
        L5:
            ThanosEffect.m13221$$Nest$fputdrawThread(this.this$0, new DrawingThread(r8, new ThanosEffect$2$$ExternalSyntheticLambda0(this.this$0), new ThanosEffect$2$$ExternalSyntheticLambda1(this.this$0), r9, r10));
            DrawingThread.m13245$$Nest$fputisEmulator(ThanosEffect.m13217$$Nest$fgetdrawThread(this.this$0), EmuDetector.with(this.this$0.getContext()).detect());
            if (ThanosEffect.m13219$$Nest$fgettoSet(this.this$0).isEmpty() == true) goto L24;
            int r82 = 0;
        L8:
            int r92 = ThanosEffect.m13219$$Nest$fgettoSet(this.this$0).size();
            ThanosEffect r102 = this.this$0;
            if (r82 >= r92) goto L18;
            ToSet r93 = (ToSet) ThanosEffect.m13219$$Nest$fgettoSet(r102).get(r82);
            if (r93.bitmap == null) goto L13;
            ThanosEffect.m13217$$Nest$fgetdrawThread(this.this$0).animate(r93.matrix, r93.bitmap, r93.startCallback, r93.doneCallback);
        L17:
            r82 = r82 + 1;
            goto L8
        L13:
            ArrayList<View> r103 = r93.views;
            ThanosEffect r0 = this.this$0;
            if (r103 == null) goto L16;
            ThanosEffect.m13217$$Nest$fgetdrawThread(r0).animateGroup(r93.views, r93.doneCallback);
            goto L17
        L16:
            ThanosEffect.m13217$$Nest$fgetdrawThread(r0).animate(r93.view, r93.durationMultiplier, r93.doneCallback);
            goto L17
        L18:
            ThanosEffect.m13219$$Nest$fgettoSet(r102).clear();
            Choreographer.getInstance().postFrameCallback(ThanosEffect.m13218$$Nest$fgetframeCallback(this.this$0));
            return;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture r2) {
            if (ThanosEffect.m13217$$Nest$fgetdrawThread(this.this$0) == null) goto L6;
            ThanosEffect.m13217$$Nest$fgetdrawThread(this.this$0).kill();
            ThanosEffect.m13221$$Nest$fputdrawThread(this.this$0, null);
        L6:
            if (ThanosEffect.m13220$$Nest$fgetwhenDone(this.this$0) == null) goto L10;
            Runnable r22 = ThanosEffect.m13220$$Nest$fgetwhenDone(this.this$0);
            ThanosEffect.m13222$$Nest$fputwhenDone(this.this$0, null);
            ThanosEffect.ensureRunOnUIThread(r22);
            return false;
        L10:
            return false;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture r1, int r2, int r3) {
            if (ThanosEffect.m13217$$Nest$fgetdrawThread(this.this$0) == null) goto L6;
            ThanosEffect.m13217$$Nest$fgetdrawThread(this.this$0).resize(r2, r3);
            return;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture r1) {
        }
    }

    public static class DrawingThread extends DispatchQueue {
        private AtomicBoolean alive;
        private int deltaTimeHandle;
        private int densityHandle;
        private Runnable destroy;
        private int drawProgram;
        private boolean drawnAnimations;
        private EGL10 egl;
        private EGLConfig eglConfig;
        private EGLContext eglContext;
        private EGLDisplay eglDisplay;
        private EGLSurface eglSurface;
        private int gridSizeHandle;
        private int height;
        private final Runnable invalidate;
        private boolean isEmulator;
        private int longevityHandle;
        private int matrixHandle;
        private int offsetHandle;
        private int particlesCountHandle;
        private final ArrayList<Animation> pendingAnimations;
        private int rectPosHandle;
        private int rectSizeHandle;
        private int resetHandle;
        public volatile boolean running;
        private int scaleHandle;
        private int seedHandle;
        private int sizeHandle;
        private final SurfaceTexture surfaceTexture;
        private int textureHandle;
        private int timeHandle;
        private final ArrayList<Animation> toAddAnimations;
        private final ArrayList<Animation> toRunStartCallback;
        private int uvOffsetHandle;
        private int width;

        public class Animation {
            private Bitmap bitmap;
            public final int[] buffer;
            public int currentBuffer;
            public boolean customMatrix;
            public final float density;
            public Runnable doneCallback;
            public boolean firstDraw;
            public final float[] glMatrixValues;
            public int gridHeight;
            public float gridSize;
            public int gridWidth;
            public boolean invalidateMatrix;
            private boolean isPhotoEditor;
            private long lastDrawTime;
            public float left;
            public float longevity;
            public final Matrix matrix;
            public final float[] matrixValues;
            public float offsetLeft;
            public float offsetTop;
            public int particlesCount;
            public volatile boolean ready;
            public final float seed;
            public Runnable startCallback;
            public final int[] texture;
            final /* synthetic */ DrawingThread this$0;
            public float time;
            public float timeScale;
            public float top;
            public int viewHeight;
            public int viewWidth;
            public ArrayList<View> views;

            /* renamed from: $r8$lambda$2tkTmZG2-4Jwa-ds3dcyziKMLlg, reason: not valid java name */
            public static /* synthetic */ void m13246$r8$lambda$2tkTmZG24Jwads3dcyziKMLlg(Animation r0) {
                r0.lambda$new$1();
            }

            /* renamed from: $r8$lambda$ffAyKhZF-HxyOgaQ4fNT2BZuSYc, reason: not valid java name */
            public static /* synthetic */ void m13247$r8$lambda$ffAyKhZFHxyOgaQ4fNT2BZuSYc(ArrayList r4) {
                int r1 = 0;
            L4:
                if (r1 >= r4.size()) goto L9;
                ((View) r4.get(r1)).setVisibility(8);
                if ((r4.get(r1) instanceof ChatMessageCell) == false) goto L8;
                ((ChatMessageCell) r4.get(r1)).setCheckBoxVisible(false, false);
                ((ChatMessageCell) r4.get(r1)).setChecked(false, false, false);
            L8:
                r1 = r1 + 1;
                goto L4
            }

            /* renamed from: -$$Nest$fgetbitmap, reason: not valid java name */
            public static /* bridge */ /* synthetic */ Bitmap m13248$$Nest$fgetbitmap(Animation r0) {
                return r0.bitmap;
            }

            /* renamed from: -$$Nest$fgetisPhotoEditor, reason: not valid java name */
            public static /* bridge */ /* synthetic */ boolean m13249$$Nest$fgetisPhotoEditor(Animation r0) {
                return r0.isPhotoEditor;
            }

            /* renamed from: -$$Nest$fputbitmap, reason: not valid java name */
            public static /* bridge */ /* synthetic */ void m13250$$Nest$fputbitmap(Animation r0, Bitmap r1) {
                r0.bitmap = r1;
            }

            public Animation(DrawingThread r9, Matrix r10, Bitmap r11, Runnable r12, Runnable r13) {
                this.this$0 = r9;
                this.views = new ArrayList();
                this.lastDrawTime = -1;
                this.time = 0.0f;
                this.firstDraw = true;
                this.offsetLeft = 0.0f;
                this.offsetTop = 0.0f;
                this.left = 0.0f;
                this.top = 0.0f;
                this.density = AndroidUtilities.density;
                this.longevity = 1.5f;
                this.timeScale = 1.15f;
                this.invalidateMatrix = true;
                this.customMatrix = false;
                this.glMatrixValues = new float[9];
                this.matrixValues = new float[9];
                Matrix r1 = new Matrix();
                this.matrix = r1;
                this.seed = (float) (Math.random() * 2.0d);
                this.texture = new int[1];
                this.buffer = new int[2];
                this.isPhotoEditor = true;
                float[] r3 = {0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f};
                r10.mapPoints(r3);
                this.left = r3[0];
                this.top = r3[1];
                this.viewWidth = (int) MathUtils.distance(r3[2], r3[3], r3[6], r3[7]);
                this.viewHeight = (int) MathUtils.distance(r3[4], r3[5], r3[6], r3[7]);
                this.customMatrix = true;
                r1.set(r10);
                retrieveMatrixValues();
                this.startCallback = r12;
                this.doneCallback = r13;
                this.longevity = 4.0f;
                this.time = -0.1f;
                this.bitmap = r11;
            }

            public Animation(DrawingThread r8, View r9, float r10, Runnable r11) {
                this.this$0 = r8;
                this.views = new ArrayList();
                this.lastDrawTime = -1;
                this.time = 0.0f;
                this.firstDraw = true;
                this.offsetLeft = 0.0f;
                this.offsetTop = 0.0f;
                this.left = 0.0f;
                this.top = 0.0f;
                this.density = AndroidUtilities.density;
                this.longevity = 1.5f;
                this.timeScale = 1.15f;
                this.invalidateMatrix = true;
                this.customMatrix = false;
                this.glMatrixValues = new float[9];
                this.matrixValues = new float[9];
                this.matrix = new Matrix();
                this.seed = (float) (Math.random() * 2.0d);
                this.texture = new int[1];
                this.buffer = new int[2];
                this.views.add(r9);
                this.viewWidth = r9.getWidth();
                this.viewHeight = r9.getHeight();
                this.top = r9.getY();
                this.left = 0.0f;
                if ((r9 instanceof BaseCell) == false) goto L5;
                BaseCell r2 = (BaseCell) r9;
                this.viewWidth = Math.max(1, r2.getBoundsRight() - r2.getBoundsLeft());
                this.left += r2.getBoundsLeft();
            L5:
                this.doneCallback = r11;
                this.startCallback = new ThanosEffect$DrawingThread$Animation$$ExternalSyntheticLambda1(this);
                this.longevity *= r10;
                this.timeScale /= ((r10 - 1.0f) / 3.0f) + 1.0f;
                this.bitmap = Bitmap.createBitmap(this.viewWidth, this.viewHeight, Bitmap.Config.ARGB_8888);
                Canvas r102 = new Canvas(this.bitmap);
                int r112 = r102.save();
                r102.translate(-this.left, 0.0f);
                boolean r22 = r9 instanceof ChatMessageCell;
                if (r22 == false) goto L8;
                ((ChatMessageCell) r9).drawingToBitmap = true;
            L8:
                boolean r3 = r9 instanceof ChatActionCell;
                if (r3 == false) goto L13;
                ChatActionCell r4 = (ChatActionCell) r9;
                if (r4.hasGradientService() == false) goto L13;
                r102.save();
                r102.translate(r4.sideMenuWidth / 2.0f, r9.getPaddingTop());
                r4.drawBackground(r102, true);
                r4.drawReactions(r102, true, null);
                r102.restore();
            L17:
                r9.draw(r102);
                if (r22 == false) goto L25;
                ChatMessageCell r0 = (ChatMessageCell) r9;
                ImageReceiver r42 = r0.getAvatarImage();
                if (r42 != null) goto L22;
            L24:
                r0.drawingToBitmap = false;
                goto L25
            L22:
                if (r42.getVisible() == false) goto L24;
                r102.save();
                r102.translate(0.0f, -r9.getY());
                r42.draw(r102);
                r102.restore();
            L25:
                if (r22 == false) goto L27;
                r102.save();
                r102.translate(0.0f, r9.getPaddingTop());
                ((ChatMessageCell) r9).drawOutboundsContent(r102);
                r102.restore();
            L35:
                r102.restoreToCount(r112);     // Catch: Exception -> L31
            L33:
                this.left += r9.getX();
                return;
            L31:
                e = move-exception;
                FileLog.e(e);
                goto L33
            L27:
                if (r3 == false) goto L35;
                ((ChatActionCell) r9).drawOutboundsContent(r102);
            L13:
                if (r22 == false) goto L17;
                ChatMessageCell r43 = (ChatMessageCell) r9;
                if (r43.drawBackgroundInParent() == false) goto L17;
                r102.save();
                r102.translate(0.0f, r9.getPaddingTop());
                r43.drawBackgroundInternal(r102, true);
                r102.restore();
                goto L17
            }

            /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, org.telegram.ui.Components.ThanosEffect$DrawingThread$Animation] */
            public Animation(DrawingThread r35, ArrayList<View> r36, Runnable r37) {
                r0.this$0 = r35;
                ?? r0 = new Object();
                r0.views = new ArrayList();
                r0.lastDrawTime = -1;
                r0.time = 0.0f;
                r0.firstDraw = true;
                r0.offsetLeft = 0.0f;
                r0.offsetTop = 0.0f;
                r0.left = 0.0f;
                r0.top = 0.0f;
                r0.density = AndroidUtilities.density;
                r0.longevity = 1.5f;
                r0.timeScale = 1.15f;
                r0.invalidateMatrix = true;
                r0.customMatrix = false;
                r0.glMatrixValues = new float[9];
                r0.matrixValues = new float[9];
                r0.matrix = new Matrix();
                r0.seed = (float) (Math.random() * 2.0d);
                r0.texture = new int[1];
                r0.buffer = new int[2];
                r0.views.addAll(r36);
                int r4 = Integer.MAX_VALUE;
                int r6 = Integer.MIN_VALUE;
                int r7 = Integer.MIN_VALUE;
                int r8 = 0;
                int r5 = Integer.MAX_VALUE;
            L4:
                if (r8 >= r36.size()) goto L6;
                View r11 = r36.get(r8);
                r5 = Math.min(r5, (int) r11.getX());
                r6 = Math.max(r6, ((int) r11.getX()) + r11.getWidth());
                r4 = Math.min(r4, (int) r11.getY());
                r7 = Math.max(r7, ((int) r11.getY()) + r11.getHeight());
                r8 = r8 + 1;
                goto L4
            L6:
                float r112 = r4;
                r0.top = r112;
                float r12 = r5;
                r0.left = r12;
                r0.viewWidth = r6 - r5;
                r0.viewHeight = r7 - r4;
                r0.doneCallback = r37;
                r0.startCallback = new ThanosEffect$DrawingThread$Animation$$ExternalSyntheticLambda0(r36);
                int r42 = 0;
            L8:
                if (r42 >= r36.size()) goto L13;
                if ((r36.get(r42) instanceof ChatMessageCell) == false) goto L12;
                ((ChatMessageCell) r36.get(r42)).drawingToBitmap = true;
            L12:
                r42 = r42 + 1;
                goto L8
            L13:
                r0.bitmap = Bitmap.createBitmap(r0.viewWidth, r0.viewHeight, Bitmap.Config.ARGB_8888);
                Canvas r14 = new Canvas(r0.bitmap);
                if (r36.size() > 0) goto L17;
                return;
            L17:
                if ((r36.get(0).getParent() instanceof RecyclerListView) == false) goto L280;
                RecyclerListView r43 = (RecyclerListView) r36.get(0).getParent();
                if ((r43.getParent() instanceof ChatActivity.ChatActivityFragmentView) == false) goto L281;
                ChatActivity.ChatActivityFragmentView r52 = (ChatActivity.ChatActivityFragmentView) r43.getParent();
                ChatActivity r62 = r52.getChatActivity();
                ArrayList r72 = new ArrayList(10);
                ArrayList r82 = new ArrayList();
                ArrayList r13 = new ArrayList();
                ArrayList r15 = new ArrayList();
                ArrayList r10 = new ArrayList();
                int r2 = r14.save();
                int r1 = 0;
                Animation r02 = r0;
            L24:
                if (r1 >= 3) goto L188;
                r72.clear();
                if (r1 == 2) goto L28;
            L31:
                int r372 = r2;
                int r3 = 0;
            L32:
                RecyclerListView r24 = r43;
                if (r3 >= r36.size()) goto L150;
                View r22 = r36.get(r3);
                if ((r22 instanceof ChatMessageCell) == false) goto L148;
                ChatMessageCell r44 = (ChatMessageCell) r22;
                if (r22.getY() > r24.getHeight()) goto L148;
                if ((r22.getY() + r22.getHeight()) < 0.0f) goto L148;
                int r16 = r3;
                if (r44.getVisibility() != 4) goto L43;
            L44:
                int r26 = r1;
            L45:
                ChatActivity.ChatActivityFragmentView r25 = r52;
            L46:
                float r27 = r112;
                float r28 = r12;
            L149:
                r3 = r16 + 1;
                r43 = r24;
                r52 = r25;
                r1 = r26;
                r112 = r27;
                r12 = r28;
                goto L32
            L43:
                if (r44.getVisibility() == 8) goto L44;
                MessageObject.GroupedMessages r23 = r44.getCurrentMessagesGroup();
                if (r23 != null) goto L50;
            L53:
                MessageObject.GroupedMessagePosition r32 = null;
            L54:
                r25 = r52;
                if (r1 != 0) goto L93;
                if (r32 == null) goto L58;
            L59:
                if (r32 != null) goto L61;
            L66:
                if (r32 != null) goto L68;
            L69:
                r82.add(r44);
            L70:
                if (r32 == null) goto L76;
                if (r32.minX == 0) goto L74;
            L78:
                if (r32 == null) goto L80;
            L83:
                if (r32 != null) goto L85;
            L86:
                r15.add(r44);
            L87:
                if (r32 == null) goto L92;
                int r33 = r32.flags;
                if ((r33 & 8) == 0) goto L93;
                if ((r33 & 1) == 0) goto L93;
            L92:
                r10.add(r44);
                goto L93
            L85:
                if ((r32.flags & r44.captionFlag()) == 0) goto L87;
            L80:
                if (r44.getTransitionParams().transformGroupToSingleMessage == true) goto L83;
                if (r44.getTransitionParams().animateBackgroundBoundsInner == false) goto L93;
            L74:
                if (r32.minY != 0) goto L78;
            L76:
                if (r44.hasNameLayout() == false) goto L78;
                r13.add(r44);
                goto L78
            L68:
                if (r32.last == false) goto L70;
            L61:
                if (r32.last == true) goto L66;
                if (r32.minX != 0) goto L78;
                if (r32.minY != 0) goto L78;
            L58:
                if (r44.getTransitionParams().animateBackgroundBoundsInner == true) goto L59;
            L93:
                if (r23 == null) goto L98;
                if (r1 != 0) goto L99;
                int r53 = 1;
                if (r23.messages.size() == 1) goto L98;
            L100:
                if (r1 == r53) goto L102;
            L104:
                if (r1 != 0) goto L108;
                if (r44.getMessageObject().deleted == true) goto L98;
            L108:
                if (r1 != 1) goto L113;
                if (r44.getMessageObject().deleted == false) goto L98;
            L113:
                if (r1 == 2) goto L115;
            L116:
                if (r1 == 2) goto L121;
                if (r44.willRemovedAfterAnimation() == true) goto L98;
            L121:
                if (r72.contains(r23) == true) goto L123;
                MessageObject.GroupedMessages.TransitionParams r54 = r23.transitionParams;
                r54.left = 0;
                r54.top = 0;
                r54.right = 0;
                r54.bottom = 0;
                r54.pinnedBotton = false;
                r54.pinnedTop = false;
                r54.cell = r44;
                r72.add(r23);
            L123:
                r23.transitionParams.pinnedTop = r44.isPinnedTop();
                r23.transitionParams.pinnedBotton = r44.isPinnedBottom();
                int r34 = r44.getLeft() + r44.getBackgroundDrawableLeft();
                int r55 = r44.getLeft() + r44.getBackgroundDrawableRight();
                int r19 = (r44.getTop() + r44.getPaddingTop()) + r44.getBackgroundDrawableTop();
                int r20 = (r44.getTop() + r44.getPaddingTop()) + r44.getBackgroundDrawableBottom();
                r26 = r1;
                if ((r44.getCurrentPosition().flags & 4) != 0) goto L126;
                r19 = r19 - AndroidUtilities.dp(10.0f);
            L126:
                r27 = r112;
                int r17 = r19;
                if ((r44.getCurrentPosition().flags & 8) != 0) goto L129;
                r20 = r20 + AndroidUtilities.dp(10.0f);
            L129:
                int r113 = r20;
                r28 = r12;
                if (r44.willRemovedAfterAnimation() == false) goto L132;
                r23.transitionParams.cell = r44;
            L132:
                MessageObject.GroupedMessages.TransitionParams r29 = r23.transitionParams;
                int r45 = r29.top;
                if (r45 == 0) goto L135;
                if (r17 < r45) goto L135;
            L136:
                int r18 = r29.bottom;
                if (r18 == 0) goto L139;
                if (r113 > r18) goto L139;
            L140:
                int r110 = r29.left;
                if (r110 == 0) goto L143;
                if (r34 < r110) goto L143;
            L144:
                int r111 = r29.right;
                if (r111 == 0) goto L147;
                if (r55 <= r111) goto L149;
            L147:
                r29.right = r55;
            L143:
                r29.left = r34;
            L139:
                r29.bottom = r113;
            L135:
                r29.top = r17;
                goto L136
            L115:
                if (r44.willRemovedAfterAnimation() == false) goto L98;
            L102:
                if (r23.transitionParams.drawBackgroundForDeletedItems == true) goto L104;
            L99:
                r53 = 1;
            L98:
                r26 = r1;
                goto L46
            L50:
                if (r23.positions == null) goto L53;
                r32 = r23.getPosition(r44.getMessageObject());
            L148:
                r26 = r1;
                r16 = r3;
                goto L45
            L150:
                int r262 = r1;
                ChatActivity.ChatActivityFragmentView r252 = r52;
                float r272 = r112;
                float r282 = r12;
                int r38 = 0;
                Animation r03 = r02;
            L152:
                if (r38 >= r72.size()) goto L187;
                MessageObject.GroupedMessages r114 = (MessageObject.GroupedMessages) r72.get(r38);
                float r210 = r114.transitionParams.cell.getNonAnimationTranslationX(true);
                MessageObject.GroupedMessages.TransitionParams r46 = r114.transitionParams;
                float r115 = (r46.left + r210) + r46.offsetLeft;
                float r122 = r46.top + r46.offsetTop;
                float r56 = (r46.right + r210) + r46.offsetRight;
                float r211 = r46.bottom + r46.offsetBottom;
                if (r46.backgroundChangeBounds == true) goto L156;
                r122 = r122 + r46.cell.getTranslationY();
                float r212 = r211 + r114.transitionParams.cell.getTranslationY();
            L157:
                float r162 = r212;
                if (r122 >= ((r62.chatListViewPaddingTop - r62.chatListViewPaddingVisibleOffset) - AndroidUtilities.dp(20.0f))) goto L161;
                r122 = (r62.chatListViewPaddingTop - r62.chatListViewPaddingVisibleOffset) - AndroidUtilities.dp(20.0f);
            L161:
                if (r162 <= (r24.getMeasuredHeight() + AndroidUtilities.dp(20.0f))) goto L163;
                float r213 = r24.getMeasuredHeight() + AndroidUtilities.dp(20.0f);
            L164:
                float r47 = r03.top;
                float r123 = r122 - r47;
                float r214 = r213 - r47;
                float r48 = r03.left;
                float r116 = r115 - r48;
                float r57 = r56 - r48;
                if (r114.transitionParams.cell.getScaleX() == 1.0f) goto L167;
            L170:
                boolean r49 = true;
            L172:
                if (r49 == false) goto L174;
                r14.save();
                int r30 = r38;
                boolean r31 = r49;
                ArrayList r322 = r72;
                r14.scale(r114.transitionParams.cell.getScaleX(), r114.transitionParams.cell.getScaleY(), r116 + ((r57 - r116) / 2.0f), r123 + ((r214 - r123) / 2.0f));
            L175:
                MessageObject.GroupedMessages.TransitionParams r04 = r114.transitionParams;
                ArrayList r39 = r13;
                ArrayList r410 = r15;
                r04.cell.drawBackground(r14, (int) r116, (int) r123, (int) r57, (int) r214, r04.pinnedTop, r04.pinnedBotton, false, r252.getKeyboardHeight());
                MessageObject.GroupedMessages.TransitionParams r05 = r114.transitionParams;
                r05.cell = null;
                r05.drawCaptionLayout = r114.hasCaption;
                if (r31 == false) goto L186;
                r14.restore();
                int r06 = 0;
            L179:
                if (r06 >= r36.size()) goto L186;
                View r73 = r36.get(r06);
                if ((r73 instanceof ChatMessageCell) == false) goto L185;
                ChatMessageCell r132 = (ChatMessageCell) r73;
                if (r132.getCurrentMessagesGroup() != r114) goto L185;
                int r152 = r132.getLeft();
                int r133 = r132.getTop();
                r73.setPivotX((r116 - r152) + ((r57 - r116) / 2.0f));
                r73.setPivotY((r123 - r133) + ((r214 - r123) / 2.0f));
            L185:
                r06 = r06 + 1;
            L186:
                r13 = r39;
                r15 = r410;
                r72 = r322;
                r38 = r30 + 1;
                r03 = this;
                goto L152
            L174:
                r30 = r38;
                r31 = r49;
                r322 = r72;
                goto L175
            L167:
                if (r114.transitionParams.cell.getScaleY() != 1.0f) goto L170;
                r49 = false;
                goto L172
            L163:
                r213 = r162;
                goto L164
            L156:
                r212 = r211;
            L187:
                r1 = r262 + 1;
                r02 = this;
                r2 = r372;
                r13 = r13;
                r15 = r15;
                r43 = r24;
                r52 = r252;
                r112 = r272;
                r12 = r282;
                r72 = r72;
                goto L24
            L28:
                if (r43.isFastScrollAnimationRunning() == true) goto L31;
                r262 = r1;
                r372 = r2;
                r24 = r43;
                r252 = r52;
                r272 = r112;
                r282 = r12;
                goto L187
            L188:
                int r373 = r2;
                RecyclerListView r242 = r43;
                float r273 = r112;
                float r283 = r12;
                ArrayList r310 = r13;
                ArrayList r411 = r15;
                int r07 = 0;
            L190:
                if (r07 >= r36.size()) goto L198;
                View r117 = r36.get(r07);
                r14.save();
                r14.translate(r117.getX() - r283, r117.getY() - r273);
                r117.draw(r14);
                if ((r117 instanceof ChatMessageCell) == false) goto L195;
                ((ChatMessageCell) r117).drawOutboundsContent(r14);
            L197:
                r14.restore();
                r07 = r07 + 1;
                goto L190
            L195:
                if ((r117 instanceof ChatActionCell) == false) goto L197;
                ((ChatActionCell) r117).drawOutboundsContent(r14);
                goto L197
            L198:
                float r08 = ((r242.getY() + r62.chatListViewPaddingTop) - r62.chatListViewPaddingVisibleOffset) - AndroidUtilities.dp(4.0f);
                int r118 = r82.size();
                if (r118 <= 0) goto L204;
                int r124 = 0;
            L201:
                if (r124 >= r118) goto L203;
                ChatMessageCell r58 = (ChatMessageCell) r82.get(r124);
                ChatActivity r215 = r62;
                Canvas r134 = r14;
                ArrayList r153 = r411;
                float r412 = r08;
                drawChildElement(r242, r215, r134, r412, r58, 0, r58.getX() - r283, r58.getY() - r273);
                r124 = r124 + 1;
                r118 = r118;
                r373 = r373;
                r14 = r134;
                r310 = r310;
                r62 = r215;
                r08 = r412;
                r82 = r82;
                r411 = r153;
                goto L201
            L203:
                Canvas r119 = r14;
                ArrayList r142 = r310;
                Canvas r311 = r119;
                int r1110 = r373;
                ArrayList r154 = r411;
                ChatActivity r216 = r62;
                RecyclerListView r120 = r242;
                float r413 = r08;
                r82.clear();
            L205:
                int r125 = r142.size();
                if (r125 <= 0) goto L211;
                int r135 = 0;
            L208:
                if (r135 >= r125) goto L210;
                ChatMessageCell r59 = (ChatMessageCell) r142.get(r135);
                drawChildElement(r120, r216, r311, r413, r59, 1, r59.getX() - r283, r59.getY() - r273);
                r135 = r135 + 1;
                goto L208
            L210:
                r142.clear();
            L211:
                int r126 = r154.size();
                if (r126 <= 0) goto L223;
                int r136 = 0;
            L214:
                if (r136 >= r126) goto L222;
                ChatMessageCell r510 = (ChatMessageCell) r154.get(r136);
                if (r510.getCurrentPosition() == null) goto L218;
            L220:
                drawChildElement(r120, r216, r311, r413, r510, 2, r510.getX() - r283, r510.getY() - r273);
            L221:
                r136 = r136 + 1;
                goto L214
            L218:
                if (r510.getTransitionParams().animateBackgroundBoundsInner == true) goto L220;
            L222:
                r154.clear();
            L223:
                int r127 = r10.size();
                if (r127 <= 0) goto L248;
                int r137 = 0;
            L226:
                if (r137 >= r127) goto L234;
                ChatMessageCell r511 = (ChatMessageCell) r10.get(r137);
                if (r511.getCurrentPosition() == null) goto L230;
            L232:
                drawChildElement(r120, r216, r311, r413, r511, 3, r511.getX() - r283, r511.getY() - r273);
            L233:
                r137 = r137 + 1;
                goto L226
            L230:
                if (r511.getTransitionParams().animateBackgroundBoundsInner == true) goto L232;
            L234:
                r10.clear();
            L248:
                r311.restoreToCount(r1110);     // Catch: Exception -> L237
            L239:
                int r312 = 0;
            L241:
                if (r312 >= r36.size()) goto L282;
                if ((r36.get(r312) instanceof ChatMessageCell) == false) goto L246;
                ((ChatMessageCell) r36.get(r312)).drawingToBitmap = false;
            L246:
                r312 = r312 + 1;
                goto L241
            L282:
                return;
            L237:
                e = move-exception;
                FileLog.e(e);
                goto L239
            L204:
                r142 = r310;
                r311 = r14;
                r1110 = r373;
                r154 = r411;
                r216 = r62;
                r120 = r242;
                r413 = r08;
                goto L205
            L281:
                return;
            }

            private void drawChildElement(View r1, ChatActivity r2, Canvas r3, float r4, ChatMessageCell r5, int r6, float r7, float r8) {
                r3.save();
                if (r5.shouldDrawAlphaLayer() == false) goto L5;
                float r0 = r5.getAlpha();
            L6:
                r3.translate(r7, r8);
                boolean r12 = true;
                r5.setInvalidatesParent(true);
                if (r6 != 0) goto L9;
                r5.drawTime(r3, r0, true);
            L25:
                r5.setInvalidatesParent(false);
                r3.restore();
                return;
            L9:
                if (r6 != 1) goto L12;
                r5.drawNamesLayout(r3, r0);
                goto L25
            L12:
                if (r6 != 2) goto L21;
                if (r5.getCurrentPosition() != null) goto L16;
            L18:
                r12 = false;
            L19:
                r5.drawCaptionLayout(r3, r12, r0);
                goto L25
            L16:
                if ((r5.getCurrentPosition().flags & 1) != 0) goto L18;
            L21:
                if (r5.getCurrentPosition() != null) goto L23;
            L24:
                r5.drawReactionsLayout(r3, r0, null);
                r5.drawCommentLayout(r3, r0);
                goto L25
            L23:
                if ((1 & r5.getCurrentPosition().flags) == 0) goto L25;
            L5:
                r0 = 1.0f;
                goto L6
            }

            private /* synthetic */ void lambda$new$1() {
                int r1 = 0;
            L4:
                if (r1 >= this.views.size()) goto L9;
                this.views.get(r1).setVisibility(8);
                if ((this.views.get(r1) instanceof ChatMessageCell) == false) goto L8;
                ((ChatMessageCell) this.views.get(r1)).setCheckBoxVisible(false, false);
                ((ChatMessageCell) this.views.get(r1)).setChecked(false, false, false);
            L8:
                r1 = r1 + 1;
                goto L4
            }

            private void retrieveMatrixValues() {
                this.matrix.getValues(this.matrixValues);
                float[] r0 = this.glMatrixValues;
                float[] r1 = this.matrixValues;
                r0[0] = r1[0];
                r0[1] = r1[3];
                r0[2] = r1[6];
                r0[3] = r1[1];
                r0[4] = r1[4];
                r0[5] = r1[7];
                r0[6] = r1[2];
                r0[7] = r1[5];
                r0[8] = r1[8];
                this.invalidateMatrix = false;
            }

            public void calcParticlesGrid(float r6) {
                int r0 = SharedConfig.getDevicePerformanceClass();
                int r2 = 120000;
                if (r0 == 1) goto L7;
                if (r0 == 2) goto L6;
                int r02 = 30000;
            L9:
                if (DrawingThread.m13231$$Nest$fgetisEmulator(this.this$0) == true) goto L13;
                r2 = r02;
            L13:
                if (this.isPhotoEditor == false) goto L15;
                r2 = r2 / 2;
            L15:
                float r03 = Math.max(AndroidUtilities.dpf2(0.4f), 1.0f);
                this.particlesCount = Utilities.clamp((int) ((this.viewWidth * this.viewHeight) / (r03 * r03)), (int) (r2 * r6), 10);
                float r04 = this.viewWidth / this.viewHeight;
                int r62 = (int) Math.round(Math.sqrt(r6 / r04));
                this.gridHeight = r62;
                this.gridWidth = Math.round(this.particlesCount / r62);
            L16:
                int r63 = this.gridWidth;
                int r1 = this.gridHeight;
                if ((r63 * r1) >= this.particlesCount) goto L22;
                if ((r63 / r1) < r04) goto L20;
                this.gridHeight = r1 + 1;
                goto L16
            L20:
                this.gridWidth = r63 + 1;
                goto L16
            L22:
                this.particlesCount = r63 * r1;
                this.gridSize = Math.max(this.viewWidth / r63, this.viewHeight / r1);
                int r05 = 0;
                GLES20.glGenBuffers(2, this.buffer, 0);
            L23:
                if (r05 >= 2) goto L25;
                GLES20.glBindBuffer(34962, this.buffer[r05]);
                GLES20.glBufferData(34962, this.particlesCount * 28, null, 35048);
                r05 = r05 + 1;
                goto L23
            L25:
                return;
            L6:
                r02 = 120000;
                goto L9
            L7:
                r02 = 60000;
                goto L9
            }

            public void done(boolean r4) {
                GLES20.glDeleteBuffers(2, this.buffer, 0);     // Catch: Exception -> L5
            L8:
                if (DrawingThread.m13229$$Nest$fgetdrawProgram(this.this$0) != 0) goto L25;
            L23:
                GLES20.glDeleteTextures(1, this.texture, 0);     // Catch: Exception -> L16
            L18:
                if (r4 == false) goto L29;
                Runnable r42 = this.doneCallback;
                if (r42 == null) goto L30;
                ThanosEffect.ensureRunOnUIThread(r42);
                this.doneCallback = null;
                return;
            L30:
                return;
            L29:
                return;
            L16:
                e = move-exception;
                FileLog.e(e);
                goto L18
            L25:
                GLES20.glDeleteProgram(DrawingThread.m13229$$Nest$fgetdrawProgram(this.this$0));     // Catch: Exception -> L11
            L13:
                DrawingThread.m13244$$Nest$fputdrawProgram(this.this$0, 0);
            L11:
                e = move-exception;
                FileLog.e(e);
            L5:
                e = move-exception;
                FileLog.e(e);
                goto L8
            }

            public void draw() {
                long r1 = System.nanoTime();
                if (this.lastDrawTime >= 0) goto L5;
                double r3 = 0.0d;
            L6:
                this.lastDrawTime = r1;
                if (this.invalidateMatrix == true) goto L9;
            L11:
                this.time = (float) (this.time + (this.timeScale * r3));
                GLES20.glUniformMatrix3fv(DrawingThread.m13233$$Nest$fgetmatrixHandle(this.this$0), 1, false, this.glMatrixValues, 0);
                int r12 = DrawingThread.m13238$$Nest$fgetresetHandle(this.this$0);
                float r8 = 1.0f;
                if (this.firstDraw == false) goto L14;
                float r2 = 1.0f;
            L15:
                GLES20.glUniform1f(r12, r2);
                GLES20.glUniform1f(DrawingThread.m13242$$Nest$fgettimeHandle(this.this$0), this.time);
                GLES20.glUniform1f(DrawingThread.m13227$$Nest$fgetdeltaTimeHandle(this.this$0), ((float) r3) * this.timeScale);
                GLES20.glUniform1f(DrawingThread.m13235$$Nest$fgetparticlesCountHandle(this.this$0), this.particlesCount);
                GLES20.glUniform3f(DrawingThread.m13230$$Nest$fgetgridSizeHandle(this.this$0), this.gridWidth, this.gridHeight, this.gridSize);
                GLES20.glUniform2f(DrawingThread.m13234$$Nest$fgetoffsetHandle(this.this$0), this.offsetLeft, this.offsetTop);
                int r13 = DrawingThread.m13239$$Nest$fgetscaleHandle(this.this$0);
                if (this.isPhotoEditor == false) goto L18;
                float r22 = 0.8f;
            L19:
                GLES20.glUniform1f(r13, r22);
                int r14 = DrawingThread.m13243$$Nest$fgetuvOffsetHandle(this.this$0);
                if (this.isPhotoEditor == true) goto L23;
                r8 = 0.6f;
            L23:
                GLES20.glUniform1f(r14, r8);
                GLES20.glUniform2f(DrawingThread.m13237$$Nest$fgetrectSizeHandle(this.this$0), this.viewWidth, this.viewHeight);
                GLES20.glUniform1f(DrawingThread.m13240$$Nest$fgetseedHandle(this.this$0), this.seed);
                GLES20.glUniform2f(DrawingThread.m13236$$Nest$fgetrectPosHandle(this.this$0), 0.0f, 0.0f);
                GLES20.glUniform1f(DrawingThread.m13228$$Nest$fgetdensityHandle(this.this$0), this.density);
                GLES20.glUniform1f(DrawingThread.m13232$$Nest$fgetlongevityHandle(this.this$0), this.longevity);
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, this.texture[0]);
                GLES20.glUniform1i(DrawingThread.m13241$$Nest$fgettextureHandle(this.this$0), 0);
                GLES20.glBindBuffer(34962, this.buffer[this.currentBuffer]);
                GLES20.glVertexAttribPointer(0, 2, 5126, false, 28, 0);
                GLES20.glEnableVertexAttribArray(0);
                GLES20.glVertexAttribPointer(1, 2, 5126, false, 28, 8);
                GLES20.glEnableVertexAttribArray(1);
                GLES20.glVertexAttribPointer(2, 2, 5126, false, 28, 16);
                GLES20.glEnableVertexAttribArray(2);
                GLES20.glVertexAttribPointer(3, 1, 5126, false, 28, 24);
                GLES20.glEnableVertexAttribArray(3);
                GLES30.glBindBufferBase(35982, 0, this.buffer[1 - this.currentBuffer]);
                GLES20.glVertexAttribPointer(0, 2, 5126, false, 28, 0);
                GLES20.glEnableVertexAttribArray(0);
                GLES20.glVertexAttribPointer(1, 2, 5126, false, 28, 8);
                GLES20.glEnableVertexAttribArray(1);
                GLES20.glVertexAttribPointer(2, 2, 5126, false, 28, 16);
                GLES20.glEnableVertexAttribArray(2);
                GLES20.glVertexAttribPointer(3, 1, 5126, false, 28, 24);
                GLES20.glEnableVertexAttribArray(3);
                GLES30.glBeginTransformFeedback(0);
                GLES20.glDrawArrays(0, 0, this.particlesCount);
                GLES30.glEndTransformFeedback();
                GLES20.glBindBuffer(34962, 0);
                GLES20.glBindBuffer(35982, 0);
                this.firstDraw = false;
                this.currentBuffer = 1 - this.currentBuffer;
                return;
            L18:
                r22 = 1.0f;
                goto L19
            L14:
                r2 = 0.0f;
                goto L15
            L9:
                if (this.customMatrix == true) goto L11;
                this.matrix.reset();
                this.matrix.postScale(this.viewWidth, this.viewHeight);
                this.matrix.postTranslate(this.left, this.top);
                retrieveMatrixValues();
                goto L11
            L5:
                r3 = (r1 - r3) / 1.0E9d;
                goto L6
            }

            public boolean isDead() {
                float r0 = this.time;
                float r1 = this.longevity;
                if (this.isPhotoEditor == false) goto L5;
                float r2 = 2.0f;
            L7:
                if (r0 <= (r1 + r2)) goto L10;
                return true;
            L10:
                return false;
            L5:
                r2 = 0.9f;
                goto L7
            }
        }

        /* renamed from: $r8$lambda$33gw-jZyvrViSWSsm3D1G3vECaY, reason: not valid java name */
        public static /* synthetic */ void m13225$r8$lambda$33gwjZyvrViSWSsm3D1G3vECaY() {
            SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
            ThanosEffect.m13224$$Nest$sfputnothanos(Boolean.TRUE);
            r0.putBoolean("nothanos", true).apply();
        }

        /* renamed from: $r8$lambda$LZe6jOiLycQht-peORUpDh-17wQ, reason: not valid java name */
        public static /* synthetic */ void m13226$r8$lambda$LZe6jOiLycQhtpeORUpDh17wQ(Runnable r0, Runnable r1) {
            ThanosEffect.ensureRunOnUIThread(r0);
            if (r1 == null) goto L6;
            AndroidUtilities.runOnUIThread(r1);
            return;
        }

        public static /* synthetic */ void $r8$lambda$TbyjyVRKe6bwfAKuU3JT4HeMVbc(DrawingThread r0, Animation r1) {
            r0.lambda$animate$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$cziJfzGkCFyVEPHp8NdpNqH4Wrw() {
            SharedPreferences.Editor r0 = MessagesController.getGlobalMainSettings().edit();
            ThanosEffect.m13224$$Nest$sfputnothanos(Boolean.TRUE);
            r0.putBoolean("nothanos", true).apply();
        }

        public static /* synthetic */ void $r8$lambda$k_Szf4PnHy0DswGItghJyyWezyM(DrawingThread r0, Animation r1) {
            r0.lambda$animateGroup$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$v7BVgehMgYJfEDUY2aKMBedw8yw(DrawingThread r0, Animation r1) {
            r0.lambda$animate$5(r1);
        }

        /* renamed from: -$$Nest$fgetdeltaTimeHandle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13227$$Nest$fgetdeltaTimeHandle(DrawingThread r0) {
            return r0.deltaTimeHandle;
        }

        /* renamed from: -$$Nest$fgetdensityHandle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13228$$Nest$fgetdensityHandle(DrawingThread r0) {
            return r0.densityHandle;
        }

        /* renamed from: -$$Nest$fgetdrawProgram, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13229$$Nest$fgetdrawProgram(DrawingThread r0) {
            return r0.drawProgram;
        }

        /* renamed from: -$$Nest$fgetgridSizeHandle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13230$$Nest$fgetgridSizeHandle(DrawingThread r0) {
            return r0.gridSizeHandle;
        }

        /* renamed from: -$$Nest$fgetisEmulator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m13231$$Nest$fgetisEmulator(DrawingThread r0) {
            return r0.isEmulator;
        }

        /* renamed from: -$$Nest$fgetlongevityHandle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13232$$Nest$fgetlongevityHandle(DrawingThread r0) {
            return r0.longevityHandle;
        }

        /* renamed from: -$$Nest$fgetmatrixHandle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13233$$Nest$fgetmatrixHandle(DrawingThread r0) {
            return r0.matrixHandle;
        }

        /* renamed from: -$$Nest$fgetoffsetHandle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13234$$Nest$fgetoffsetHandle(DrawingThread r0) {
            return r0.offsetHandle;
        }

        /* renamed from: -$$Nest$fgetparticlesCountHandle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13235$$Nest$fgetparticlesCountHandle(DrawingThread r0) {
            return r0.particlesCountHandle;
        }

        /* renamed from: -$$Nest$fgetrectPosHandle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13236$$Nest$fgetrectPosHandle(DrawingThread r0) {
            return r0.rectPosHandle;
        }

        /* renamed from: -$$Nest$fgetrectSizeHandle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13237$$Nest$fgetrectSizeHandle(DrawingThread r0) {
            return r0.rectSizeHandle;
        }

        /* renamed from: -$$Nest$fgetresetHandle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13238$$Nest$fgetresetHandle(DrawingThread r0) {
            return r0.resetHandle;
        }

        /* renamed from: -$$Nest$fgetscaleHandle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13239$$Nest$fgetscaleHandle(DrawingThread r0) {
            return r0.scaleHandle;
        }

        /* renamed from: -$$Nest$fgetseedHandle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13240$$Nest$fgetseedHandle(DrawingThread r0) {
            return r0.seedHandle;
        }

        /* renamed from: -$$Nest$fgettextureHandle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13241$$Nest$fgettextureHandle(DrawingThread r0) {
            return r0.textureHandle;
        }

        /* renamed from: -$$Nest$fgettimeHandle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13242$$Nest$fgettimeHandle(DrawingThread r0) {
            return r0.timeHandle;
        }

        /* renamed from: -$$Nest$fgetuvOffsetHandle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13243$$Nest$fgetuvOffsetHandle(DrawingThread r0) {
            return r0.uvOffsetHandle;
        }

        /* renamed from: -$$Nest$fputdrawProgram, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13244$$Nest$fputdrawProgram(DrawingThread r0, int r1) {
            r0.drawProgram = r1;
        }

        /* renamed from: -$$Nest$fputisEmulator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13245$$Nest$fputisEmulator(DrawingThread r0, boolean r1) {
            r0.isEmulator = r1;
        }

        public DrawingThread(SurfaceTexture r4, Runnable r5, Runnable r6, int r7, int r8) {
            super("ThanosEffect.DrawingThread", false);
            this.alive = new AtomicBoolean(true);
            this.pendingAnimations = new ArrayList();
            this.toRunStartCallback = new ArrayList();
            this.drawnAnimations = false;
            this.toAddAnimations = new ArrayList();
            this.surfaceTexture = r4;
            this.invalidate = r5;
            this.destroy = r6;
            this.width = r7;
            this.height = r8;
            start();
        }

        private void addAnimationInternal(Animation r6) {
            int r2 = 0;
            GLES20.glGenTextures(1, r6.texture, 0);
            GLES20.glBindTexture(3553, r6.texture[0]);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameteri(3553, 10240, 9729);
            GLES20.glTexParameteri(3553, 10242, 33071);
            GLES20.glTexParameteri(3553, 10243, 33071);
            GLUtils.texImage2D(3553, 0, Animation.m13248$$Nest$fgetbitmap(r6), 0);
            GLES20.glBindTexture(3553, 0);
            Animation.m13248$$Nest$fgetbitmap(r6).recycle();
            Animation.m13250$$Nest$fputbitmap(r6, null);
            if (Animation.m13249$$Nest$fgetisPhotoEditor(r6) == false) goto L8;
            ArrayList<Animation> r0 = this.pendingAnimations;
            int r3 = r0.size();
        L5:
            if (r2 >= r3) goto L7;
            Animation r4 = r0.get(r2);
            r2 = r2 + 1;
            r4.done(true);
            goto L5
        L7:
            this.pendingAnimations.clear();
        L8:
            this.pendingAnimations.add(r6);
            this.running = true;
            r6.ready = true;
        }

        private float animationHeightPart(Animation r4) {
            int r0 = 0;
            int r1 = 0;
        L4:
            if (r0 >= this.pendingAnimations.size()) goto L7;
            r1 = r1 + this.pendingAnimations.get(r0).viewHeight;
            r0 = r0 + 1;
            goto L4
        L7:
            return r4.viewHeight / r1;
        }

        private void cancelAnimationInternal(View r5) {
            int r0 = 0;
        L4:
            if (r0 >= this.pendingAnimations.size()) goto L9;
            Animation r1 = this.pendingAnimations.get(r0);
            if (r1.views.contains(r5) == false) goto L8;
            r1.done(true);
            this.pendingAnimations.remove(r0);
            r0 = r0 - 1;
        L8:
            r0 = r0 + 1;
            goto L4
        }

        private void checkGlErrors() {
        L2:
            int r2 = GLES20.glGetError();
            if (r2 == 0) goto L5;
            FileLog.e("thanos gles error " + r2);
            goto L2
        }

        private void draw() {
            if (this.alive.get() == false) goto L49;
            GLES20.glClear(16384);
            int r0 = 0;
            int r1 = 0;
        L7:
            if (r1 >= this.pendingAnimations.size()) goto L17;
            Animation r2 = this.pendingAnimations.get(r1);
            if (r2.firstDraw == false) goto L13;
            r2.calcParticlesGrid(animationHeightPart(r2));
            if (r2.startCallback == null) goto L13;
            this.toRunStartCallback.add(r2);
        L13:
            this.drawnAnimations = true;
            r2.draw();
            if (r2.isDead() == false) goto L16;
            r2.done(true);
            this.pendingAnimations.remove(r1);
            this.running = !this.pendingAnimations.isEmpty();
            r1 = r1 - 1;
        L16:
            r1 = r1 + 1;
            goto L7
        L17:
            checkGlErrors();
            this.egl.eglSwapBuffers(this.eglDisplay, this.eglSurface);     // Catch: Exception -> L28
        L19:
            int r12 = this.toRunStartCallback.size();
            ArrayList<Animation> r22 = this.toRunStartCallback;
            if (r0 >= r12) goto L22;
            AndroidUtilities.runOnUIThread(r22.get(r0).startCallback);
            r0 = r0 + 1;
            goto L19
        L22:
            r22.clear();
            if (this.pendingAnimations.isEmpty() == true) goto L25;
            return;
        L25:
            if (this.drawnAnimations == false) goto L48;
            killInternal();
            return;
        L48:
            return;
        L28:
            e = move-exception;
            FileLog.e(e);
            int r13 = 0;
        L30:
            int r23 = this.toRunStartCallback.size();
            ArrayList<Animation> r4 = this.toRunStartCallback;
            if (r13 >= r23) goto L33;
            AndroidUtilities.runOnUIThread(r4.get(r13).startCallback);
            r13 = r13 + 1;
            goto L30
        L33:
            r4.clear();
        L34:
            int r14 = this.pendingAnimations.size();
            ArrayList<Animation> r24 = this.pendingAnimations;
            if (r0 >= r14) goto L37;
            r24.get(r0).done(true);
            r0 = r0 + 1;
            goto L34
        L37:
            r24.clear();
            AndroidUtilities.runOnUIThread(new ThanosEffect$DrawingThread$$ExternalSyntheticLambda2());
            killInternal();
            return;
        }

        private void init() {
            EGL10 r0 = (EGL10) EGLContext.getEGL();
            this.egl = r0;
            EGLDisplay r02 = r0.eglGetDisplay(0);
            this.eglDisplay = r02;
            EGL10 r2 = this.egl;
            if (r02 != EGL10.EGL_NO_DISPLAY) goto L7;
            FileLog.e("ThanosEffect: eglDisplay == egl.EGL_NO_DISPLAY");
            killInternal();
            return;
        L7:
            if (r2.eglInitialize(r02, new int[2]) == true) goto L10;
            FileLog.e("ThanosEffect: failed eglInitialize");
            killInternal();
            return;
        L10:
            EGLConfig[] r5 = new EGLConfig[1];
            if (this.egl.eglChooseConfig(this.eglDisplay, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 64, 12344}, r5, 1, new int[1]) == true) goto L14;
            FileLog.e("ThanosEffect: failed eglChooseConfig");
            kill();
            return;
        L14:
            EGLConfig r22 = r5[0];
            this.eglConfig = r22;
            EGLContext r23 = this.egl.eglCreateContext(this.eglDisplay, r22, EGL10.EGL_NO_CONTEXT, new int[]{12440, 3, 12344});
            this.eglContext = r23;
            if (r23 != null) goto L18;
            FileLog.e("ThanosEffect: eglContext == null");
            killInternal();
            return;
        L18:
            EGLSurface r24 = this.egl.eglCreateWindowSurface(this.eglDisplay, this.eglConfig, this.surfaceTexture, null);
            this.eglSurface = r24;
            if (r24 != null) goto L23;
            FileLog.e("ThanosEffect: eglSurface == null");
            killInternal();
            return;
        L23:
            if (this.egl.eglMakeCurrent(this.eglDisplay, r24, r24, this.eglContext) == true) goto L26;
            FileLog.e("ThanosEffect: failed eglMakeCurrent");
            killInternal();
            return;
        L26:
            int r25 = GLES20.glCreateShader(35633);
            int r3 = GLES20.glCreateShader(35632);
            if (r25 == 0) goto L48;
            if (r3 == 0) goto L48;
            GLES20.glShaderSource(r25, AndroidUtilities.readRes(R.raw.thanos_vertex));
            GLES20.glCompileShader(r25);
            int[] r4 = new int[1];
            GLES20.glGetShaderiv(r25, 35713, r4, 0);
            if (r4[0] == 1) goto L34;
            FileLog.e("ThanosEffect, compile vertex shader error: " + GLES20.glGetShaderInfoLog(r25));
            GLES20.glDeleteShader(r25);
            killInternal();
            return;
        L34:
            GLES20.glShaderSource(r3, AndroidUtilities.readRes(R.raw.thanos_fragment));
            GLES20.glCompileShader(r3);
            GLES20.glGetShaderiv(r3, 35713, r4, 0);
            if (r4[0] == 1) goto L38;
            FileLog.e("ThanosEffect, compile fragment shader error: " + GLES20.glGetShaderInfoLog(r3));
            GLES20.glDeleteShader(r3);
            killInternal();
            return;
        L38:
            int r52 = GLES20.glCreateProgram();
            this.drawProgram = r52;
            if (r52 != 0) goto L42;
            FileLog.e("ThanosEffect: drawProgram == 0");
            killInternal();
            return;
        L42:
            GLES20.glAttachShader(r52, r25);
            GLES20.glAttachShader(this.drawProgram, r3);
            GLES30.glTransformFeedbackVaryings(this.drawProgram, new String[]{"outUV", "outPosition", "outVelocity", "outTime"}, 35980);
            GLES20.glLinkProgram(this.drawProgram);
            GLES20.glGetProgramiv(this.drawProgram, 35714, r4, 0);
            int r26 = r4[0];
            int r32 = this.drawProgram;
            if (r26 == 1) goto L46;
            FileLog.e("ThanosEffect, link program error: " + GLES20.glGetProgramInfoLog(r32));
            killInternal();
            return;
        L46:
            this.matrixHandle = GLES20.glGetUniformLocation(r32, "matrix");
            this.rectSizeHandle = GLES20.glGetUniformLocation(this.drawProgram, "rectSize");
            this.rectPosHandle = GLES20.glGetUniformLocation(this.drawProgram, "rectPos");
            this.resetHandle = GLES20.glGetUniformLocation(this.drawProgram, "reset");
            this.timeHandle = GLES20.glGetUniformLocation(this.drawProgram, "time");
            this.deltaTimeHandle = GLES20.glGetUniformLocation(this.drawProgram, "deltaTime");
            this.particlesCountHandle = GLES20.glGetUniformLocation(this.drawProgram, "particlesCount");
            this.sizeHandle = GLES20.glGetUniformLocation(this.drawProgram, "size");
            this.gridSizeHandle = GLES20.glGetUniformLocation(this.drawProgram, "gridSize");
            this.textureHandle = GLES20.glGetUniformLocation(this.drawProgram, "tex");
            this.seedHandle = GLES20.glGetUniformLocation(this.drawProgram, "seed");
            this.densityHandle = GLES20.glGetUniformLocation(this.drawProgram, "dp");
            this.longevityHandle = GLES20.glGetUniformLocation(this.drawProgram, "longevity");
            this.offsetHandle = GLES20.glGetUniformLocation(this.drawProgram, "offset");
            this.scaleHandle = GLES20.glGetUniformLocation(this.drawProgram, "scale");
            this.uvOffsetHandle = GLES20.glGetUniformLocation(this.drawProgram, "uvOffset");
            GLES20.glViewport(0, 0, this.width, this.height);
            GLES20.glDisable(3042);
            GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            GLES20.glUseProgram(this.drawProgram);
            GLES20.glUniform2f(this.sizeHandle, this.width, this.height);
            return;
        L48:
            FileLog.e("ThanosEffect: vertexShader == 0 || fragmentShader == 0");
            killInternal();
        }

        private void killInternal() {
            if (this.alive.get() == true) goto L6;
            FileLog.d("ThanosEffect: killInternal failed, already dead");
            return;
        L6:
            FileLog.d("ThanosEffect: killInternal");
            this.alive.set(false);
            int r0 = 0;
        L7:
            int r2 = this.pendingAnimations.size();
            ArrayList<Animation> r3 = this.pendingAnimations;
            if (r0 >= r2) goto L10;
            r3.get(r0).done(true);
            r0 = r0 + 1;
            goto L7
        L10:
            r3.clear();
            if (this.egl != null) goto L13;
        L28:
            SurfaceTexture r02 = this.surfaceTexture;
            if (r02 == null) goto L31;
            r02.release();
        L31:
            ThanosEffect.ensureRunOnUIThread(this.destroy);
            this.destroy = null;
            Looper r5 = Looper.myLooper();
            if (r5 == null) goto L38;
            r5.quit();
            return;
        L38:
            return;
        L13:
            if (this.eglDisplay == null) goto L28;
            int r03 = this.drawProgram;
            if (r03 != 0) goto L35;
        L21:
            EGL10 r04 = this.egl;
            EGLDisplay r1 = this.eglDisplay;
            EGLSurface r32 = EGL10.EGL_NO_SURFACE;
            r04.eglMakeCurrent(r1, r32, r32, EGL10.EGL_NO_CONTEXT);
            EGLSurface r05 = this.eglSurface;
            if (r05 == null) goto L24;
            this.egl.eglDestroySurface(this.eglDisplay, r05);
            this.eglSurface = null;
        L24:
            EGLContext r06 = this.eglContext;
            if (r06 == null) goto L27;
            this.egl.eglDestroyContext(this.eglDisplay, r06);
            this.eglContext = null;
        L27:
            this.egl.eglTerminate(this.eglDisplay);
            this.eglDisplay = null;
            goto L28
        L35:
            GLES20.glDeleteProgram(r03);     // Catch: Exception -> L18
        L20:
            this.drawProgram = 0;
        L18:
            e = move-exception;
            FileLog.e(e);
            goto L20
        }

        private /* synthetic */ void lambda$animate$3(Animation r1) {
            addAnimationInternal(r1);
        }

        private /* synthetic */ void lambda$animate$5(Animation r1) {
            addAnimationInternal(r1);
        }

        private /* synthetic */ void lambda$animateGroup$2(Animation r1) {
            addAnimationInternal(r1);
        }

        private void resizeInternal(int r2, int r3) {
            if (this.alive.get() == true) goto L5;
            return;
        L5:
            this.width = r2;
            this.height = r3;
            GLES20.glViewport(0, 0, r2, r3);
            GLES20.glUniform2f(this.sizeHandle, r2, r3);
        }

        public void animate(Matrix r7, Bitmap r8, Runnable r9, Runnable r10) {
            if (this.alive.get() == true) goto L6;
            AndroidUtilities.runOnUIThread(new ThanosEffect$DrawingThread$$ExternalSyntheticLambda3(r9, r10));
            ThanosEffect.ensureRunOnUIThread(this.destroy);
            this.destroy = null;
            return;
        L6:
            Animation r0 = new Animation(this, r7, r8, r9, r10);
            getHandler();
            this.running = true;
            postRunnable(new ThanosEffect$DrawingThread$$ExternalSyntheticLambda4(this, r0));
        }

        public void animate(View r2, float r3, Runnable r4) {
            if (this.alive.get() == true) goto L12;
            if (r2 == null) goto L6;
            r2.setVisibility(8);
        L6:
            if (r4 == null) goto L8;
            AndroidUtilities.runOnUIThread(r4);
        L8:
            Runnable r22 = this.destroy;
            if (r22 == null) goto L14;
            AndroidUtilities.runOnUIThread(r22);
            this.destroy = null;
            return;
        L14:
            return;
        L12:
            Animation r0 = new Animation(this, r2, r3, r4);
            getHandler();
            this.running = true;
            postRunnable(new ThanosEffect$DrawingThread$$ExternalSyntheticLambda5(this, r0));
        }

        public void animateGroup(ArrayList<View> r4, Runnable r5) {
            if (this.alive.get() == true) goto L14;
            int r0 = 0;
        L6:
            if (r0 >= r4.size()) goto L8;
            r4.get(r0).setVisibility(8);
            r0 = r0 + 1;
            goto L6
        L8:
            if (r5 == null) goto L10;
            AndroidUtilities.runOnUIThread(r5);
        L10:
            Runnable r42 = this.destroy;
            if (r42 == null) goto L17;
            AndroidUtilities.runOnUIThread(r42);
            this.destroy = null;
            return;
        L17:
            return;
        L14:
            Animation r02 = new Animation(this, r4, r5);
            this.running = true;
            postRunnable(new ThanosEffect$DrawingThread$$ExternalSyntheticLambda0(this, r02));
        }

        public void cancel(View r4) {
            if (this.alive.get() == false) goto L17;
            Handler r0 = getHandler();
            if (r0 != null) goto L18;
            int r02 = 0;
        L9:
            if (r02 >= this.toAddAnimations.size()) goto L23;
            Animation r1 = this.toAddAnimations.get(r02);
            if (r1.views.contains(r4) == false) goto L16;
            Runnable r2 = r1.doneCallback;
            if (r2 == null) goto L15;
            ThanosEffect.ensureRunOnUIThread(r2);
            r1.doneCallback = null;
        L15:
            this.toAddAnimations.remove(r02);
            r02 = r02 - 1;
        L16:
            r02 = r02 + 1;
            goto L9
        L23:
            return;
        L18:
            r0.sendMessage(r0.obtainMessage(5, r4));
            return;
        }

        @Override // org.telegram.messenger.DispatchQueue
        public void handleMessage(Message r5) {
            int r0 = r5.what;
            if (r0 != 0) goto L5;
            draw();
            return;
        L5:
            if (r0 != 1) goto L7;
            resizeInternal(r5.arg1, r5.arg2);
            draw();
            return;
        L7:
            if (r0 != 2) goto L9;
            killInternal();
            return;
        L9:
            if (r0 != 3) goto L11;
            addAnimationInternal((Animation) r5.obj);
            return;
        L11:
            if (r0 != 4) goto L13;
            int r02 = 0;
        L19:
            if (r02 >= this.pendingAnimations.size()) goto L31;
            Animation r1 = this.pendingAnimations.get(r02);
            r1.offsetLeft += r5.arg1;
            r1.offsetTop += r5.arg2;
            r02 = r02 + 1;
            goto L19
        L31:
            return;
        L13:
            if (r0 != 5) goto L21;
            cancelAnimationInternal((View) r5.obj);
            return;
        }

        public void kill() {
            if (this.alive.get() == true) goto L6;
            FileLog.d("ThanosEffect: kill failed, already dead");
            return;
        L6:
            FileLog.d("ThanosEffect: kill");
            Handler r1 = getHandler();     // Catch: Exception -> L11
            if (r1 == null) goto L14;
            r1.sendMessage(r1.obtainMessage(2));     // Catch: Exception -> L11
            return;
        L14:
            return;
        }

        public void requestDraw() {
            Handler r0 = getHandler();
            if (r0 != null) goto L5;
            return;
        L5:
            if (this.alive.get() == false) goto L9;
            r0.sendMessage(r0.obtainMessage(0));
            return;
        }

        public void resize(int r2, int r3) {
            Handler r0 = getHandler();
            if (r0 != null) goto L5;
            return;
        L5:
            if (this.alive.get() == false) goto L9;
            r0.sendMessage(r0.obtainMessage(1, r2, r3));
            return;
        }

        @Override // org.telegram.messenger.DispatchQueue, java.lang.Thread, java.lang.Runnable
        public void run() {
            int r0 = 0;
            init();     // Catch: Exception -> L12
            if (this.toAddAnimations.isEmpty() == true) goto L10;
        L6:
            int r1 = this.toAddAnimations.size();
            ArrayList<Animation> r2 = this.toAddAnimations;
            if (r0 >= r1) goto L9;
            addAnimationInternal(r2.get(r0));
            r0 = r0 + 1;
            goto L6
        L9:
            r2.clear();
        L10:
            super.run();
            return;
        L12:
            e = move-exception;
            FileLog.e(e);
        L14:
            int r12 = this.toAddAnimations.size();
            ArrayList<Animation> r22 = this.toAddAnimations;
            if (r0 >= r12) goto L20;
            Animation r13 = r22.get(r0);
            Runnable r23 = r13.startCallback;
            if (r23 == null) goto L19;
            AndroidUtilities.runOnUIThread(r23);
        L19:
            r13.done(true);
            r0 = r0 + 1;
            goto L14
        L20:
            r22.clear();
            AndroidUtilities.runOnUIThread(new ThanosEffect$DrawingThread$$ExternalSyntheticLambda1());
            killInternal();
        }
    }

    public static class ToSet {
        public final Bitmap bitmap;
        public Runnable doneCallback;
        public float durationMultiplier;
        public final Matrix matrix;
        public Runnable startCallback;
        public final View view;
        public final ArrayList<View> views;

        public ToSet(Matrix r2, Bitmap r3, Runnable r4, Runnable r5) {
            this.durationMultiplier = 1.0f;
            this.view = null;
            this.views = null;
            this.startCallback = r4;
            this.doneCallback = r5;
            this.matrix = r2;
            this.bitmap = r3;
        }

        public ToSet(View r2, Runnable r3) {
            this.durationMultiplier = 1.0f;
            this.view = r2;
            this.views = null;
            this.startCallback = null;
            this.doneCallback = r3;
            this.bitmap = null;
            this.matrix = null;
        }

        public ToSet(ArrayList<View> r2, Runnable r3) {
            this.durationMultiplier = 1.0f;
            this.view = null;
            this.views = r2;
            this.startCallback = null;
            this.doneCallback = r3;
            this.bitmap = null;
            this.matrix = null;
        }
    }

    /* renamed from: -$$Nest$fgetdrawThread, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DrawingThread m13217$$Nest$fgetdrawThread(ThanosEffect r0) {
        return r0.drawThread;
    }

    /* renamed from: -$$Nest$fgetframeCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Choreographer.FrameCallback m13218$$Nest$fgetframeCallback(ThanosEffect r0) {
        return r0.frameCallback;
    }

    /* renamed from: -$$Nest$fgettoSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m13219$$Nest$fgettoSet(ThanosEffect r0) {
        return r0.toSet;
    }

    /* renamed from: -$$Nest$fgetwhenDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m13220$$Nest$fgetwhenDone(ThanosEffect r0) {
        return r0.whenDone;
    }

    /* renamed from: -$$Nest$fputdrawThread, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13221$$Nest$fputdrawThread(ThanosEffect r0, DrawingThread r1) {
        r0.drawThread = r1;
    }

    /* renamed from: -$$Nest$fputwhenDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13222$$Nest$fputwhenDone(ThanosEffect r0, Runnable r1) {
        r0.whenDone = r1;
    }

    /* renamed from: -$$Nest$mdestroy, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13223$$Nest$mdestroy(ThanosEffect r0) {
        r0.destroy();
    }

    /* renamed from: -$$Nest$sfputnothanos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13224$$Nest$sfputnothanos(Boolean r0) {
        nothanos = r0;
    }

    static {
    }

    public ThanosEffect(Context r1, Runnable r2) {
        super(r1);
        this.frameCallback = new AnonymousClass1(this);
        this.toSet = new ArrayList();
        this.whenDone = r2;
        setOpaque(false);
        setSurfaceTextureListener(new AnonymousClass2(this));
    }

    private void destroy() {
        Runnable r0 = this.whenDone;
        if (r0 == null) goto L6;
        this.destroyed = true;
        this.whenDone = null;
        ensureRunOnUIThread(r0);
        return;
    }

    public static void ensureRunOnUIThread(Runnable r2) {
        if (r2 != null) goto L5;
        return;
    L5:
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) goto L8;
        AndroidUtilities.runOnUIThread(r2);
        return;
    L8:
        r2.run();
    }

    public static boolean supports() {
        if (nothanos != null) goto L5;
        nothanos = Boolean.valueOf(MessagesController.getGlobalMainSettings().getBoolean("nothanos", false));
    L5:
        Boolean r0 = nothanos;
        if (r0 != null) goto L8;
        return true;
    L8:
        if (r0.booleanValue() == false) goto L13;
        return false;
    L13:
        return true;
    }

    public void animate(Matrix r2, Bitmap r3, Runnable r4, Runnable r5) {
        DrawingThread r0 = this.drawThread;
        if (r0 == null) goto L6;
        r0.animate(r2, r3, r4, r5);
        Choreographer.getInstance().postFrameCallback(this.frameCallback);
        return;
    L6:
        this.toSet.add(new ToSet(r2, r3, r4, r5));
    }

    public void animate(View r2, float r3, Runnable r4) {
        DrawingThread r0 = this.drawThread;
        if (r0 == null) goto L6;
        r0.animate(r2, r3, r4);
        Choreographer.getInstance().postFrameCallback(this.frameCallback);
        return;
    L6:
        ToSet r02 = new ToSet(r2, r4);
        r02.durationMultiplier = r3;
        this.toSet.add(r02);
    }

    public void animate(View r3, Runnable r4) {
        DrawingThread r0 = this.drawThread;
        if (r0 == null) goto L6;
        r0.animate(r3, 1.0f, r4);
        Choreographer.getInstance().postFrameCallback(this.frameCallback);
        return;
    L6:
        this.toSet.add(new ToSet(r3, r4));
    }

    public void animateGroup(ArrayList<View> r2, Runnable r3) {
        DrawingThread r0 = this.drawThread;
        if (r0 == null) goto L6;
        r0.animateGroup(r2, r3);
        Choreographer.getInstance().postFrameCallback(this.frameCallback);
        return;
    L6:
        this.toSet.add(new ToSet(r2, r3));
    }

    public void cancel(View r6) {
        int r0 = 0;
        boolean r1 = false;
    L4:
        if (r0 >= this.toSet.size()) goto L12;
        ToSet r2 = this.toSet.get(r0);
        if (r2.view != r6) goto L11;
        Runnable r12 = r2.doneCallback;
        if (r12 == null) goto L10;
        ensureRunOnUIThread(r12);
        r2.doneCallback = null;
    L10:
        this.toSet.remove(r0);
        r0 = r0 - 1;
        r1 = true;
    L11:
        r0 = r0 + 1;
        goto L4
    L12:
        if (r1 == true) goto L18;
        this.drawThread.cancel(r6);
        return;
    }

    public void kill() {
        if (this.destroyed == true) goto L24;
        this.destroyed = true;
        ArrayList<ToSet> r0 = this.toSet;
        int r1 = r0.size();
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L11;
        ToSet r4 = r0.get(r2);
        r2 = r2 + 1;
        ToSet r42 = r4;
        Runnable r5 = r42.doneCallback;
        if (r5 == null) goto L7;
        ensureRunOnUIThread(r5);
        r42.doneCallback = null;
        goto L7
    L11:
        this.toSet.clear();
        DrawingThread r02 = this.drawThread;
        if (r02 == null) goto L14;
        r02.kill();
    L14:
        Runnable r03 = this.whenDone;
        if (r03 == null) goto L23;
        this.whenDone = null;
        ensureRunOnUIThread(r03);
        return;
    L23:
        return;
    }

    public void scroll(int r1, int r2) {
        DrawingThread r0 = this.drawThread;
        if (r0 == null) goto L6;
        boolean r02 = r0.running;
        return;
    }
}
