package org.telegram.messenger.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.text.MeasuredText;
import android.os.Build;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes6.dex */
public class DebugRecordingCanvas extends Canvas {
    private final List<Command> mCommands;

    /* renamed from: org.telegram.messenger.utils.DebugRecordingCanvas$1, reason: invalid class name */
    public class AnonymousClass1 extends Command {
        final /* synthetic */ DebugRecordingCanvas this$0;
        final /* synthetic */ int[] val$colorsCopy;
        final /* synthetic */ int val$rh;
        final /* synthetic */ boolean val$rha;
        final /* synthetic */ int val$ro;
        final /* synthetic */ Paint val$rp;
        final /* synthetic */ int val$rs;
        final /* synthetic */ int val$rw;
        final /* synthetic */ float val$rx;
        final /* synthetic */ float val$ry;

        public AnonymousClass1(DebugRecordingCanvas r1, int[] r2, int r3, int r4, float r5, float r6, int r7, int r8, boolean r9, Paint r10) {
            this.this$0 = r1;
            this.val$colorsCopy = r2;
            this.val$ro = r3;
            this.val$rs = r4;
            this.val$rx = r5;
            this.val$ry = r6;
            this.val$rw = r7;
            this.val$rh = r8;
            this.val$rha = r9;
            this.val$rp = r10;
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r11) {
            r11.drawBitmap(this.val$colorsCopy, this.val$ro, this.val$rs, this.val$rx, this.val$ry, this.val$rw, this.val$rh, this.val$rha, this.val$rp);
        }

        public String toString() {
            return DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.val$rp) + DebugRecordingCanvas.m5182$$Nest$smcoordWarn(new float[]{this.val$rx, this.val$ry}) + "drawBitmap(int[] " + this.val$rw + "x" + this.val$rh + " x=" + this.val$rx + " y=" + this.val$ry + " " + DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.val$rp) + ")";
        }
    }

    /* renamed from: org.telegram.messenger.utils.DebugRecordingCanvas$2, reason: invalid class name */
    public class AnonymousClass2 extends Command {
        final /* synthetic */ DebugRecordingCanvas this$0;
        final /* synthetic */ int[] val$colorsCopy;
        final /* synthetic */ int val$rh;
        final /* synthetic */ boolean val$rha;
        final /* synthetic */ int val$ro;
        final /* synthetic */ Paint val$rp;
        final /* synthetic */ int val$rs;
        final /* synthetic */ int val$rw;
        final /* synthetic */ int val$rx;
        final /* synthetic */ int val$ry;

        public AnonymousClass2(DebugRecordingCanvas r1, int[] r2, int r3, int r4, int r5, int r6, int r7, int r8, boolean r9, Paint r10) {
            this.this$0 = r1;
            this.val$colorsCopy = r2;
            this.val$ro = r3;
            this.val$rs = r4;
            this.val$rx = r5;
            this.val$ry = r6;
            this.val$rw = r7;
            this.val$rh = r8;
            this.val$rha = r9;
            this.val$rp = r10;
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r11) {
            r11.drawBitmap(this.val$colorsCopy, this.val$ro, this.val$rs, this.val$rx, this.val$ry, this.val$rw, this.val$rh, this.val$rha, this.val$rp);
        }

        public String toString() {
            return DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.val$rp) + DebugRecordingCanvas.m5182$$Nest$smcoordWarn(new float[]{this.val$rx, this.val$ry}) + "drawBitmap(int[] " + this.val$rw + "x" + this.val$rh + " x=" + this.val$rx + " y=" + this.val$ry + " " + DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.val$rp) + ")";
        }
    }

    /* renamed from: org.telegram.messenger.utils.DebugRecordingCanvas$3, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$org$telegram$messenger$utils$DebugRecordingCanvas$QuickRejectCmd$Kind = null;

        static {
            int[] r0 = new int[QuickRejectCmd.Kind.values().length];
            $SwitchMap$org$telegram$messenger$utils$DebugRecordingCanvas$QuickRejectCmd$Kind = r0;
            r0[QuickRejectCmd.Kind.RECT_F.ordinal()] = 1;     // Catch: NoSuchFieldError -> L10
        L16:
            $SwitchMap$org$telegram$messenger$utils$DebugRecordingCanvas$QuickRejectCmd$Kind[QuickRejectCmd.Kind.RECT_F_EDGE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L11
        L20:
            $SwitchMap$org$telegram$messenger$utils$DebugRecordingCanvas$QuickRejectCmd$Kind[QuickRejectCmd.Kind.COORDS.ordinal()] = 3;     // Catch: NoSuchFieldError -> L12
        L26:
            $SwitchMap$org$telegram$messenger$utils$DebugRecordingCanvas$QuickRejectCmd$Kind[QuickRejectCmd.Kind.COORDS_EDGE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L13
        L18:
            $SwitchMap$org$telegram$messenger$utils$DebugRecordingCanvas$QuickRejectCmd$Kind[QuickRejectCmd.Kind.PATH.ordinal()] = 5;     // Catch: NoSuchFieldError -> L14
        L22:
            $SwitchMap$org$telegram$messenger$utils$DebugRecordingCanvas$QuickRejectCmd$Kind[QuickRejectCmd.Kind.PATH_EDGE.ordinal()] = 6;     // Catch: NoSuchFieldError -> L15
            return;
        }
    }

    public static final class ClipPathCmd extends Command {
        final boolean hasOp;
        final Region.Op op;
        final Path path;

        public ClipPathCmd(Path r2, Region.Op r3) {
            this.path = new Path(r2);
            this.op = r3;
            this.hasOp = true;
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r3) {
            boolean r0 = this.hasOp;
            Path r1 = this.path;
            if (r0 == false) goto L6;
            r3.clipPath(r1, this.op);
            return;
        L6:
            r3.clipPath(r1);
        }

        public String toString() {
            if (this.hasOp == false) goto L5;
            String r0 = " op=" + this.op;
        L7:
            return DebugRecordingCanvas.m5186$$Nest$smpathWarn(this.path) + "clipPath(" + r0 + ")";
        L5:
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L7
        }
    }

    public static final class ClipRectCmd extends Command {
        final boolean hasOp;
        final Region.Op op;
        final RectF rect;

        public ClipRectCmd(float r2, float r3, float r4, float r5) {
            this.rect = new RectF(r2, r3, r4, r5);
            this.op = null;
            this.hasOp = false;
        }

        public ClipRectCmd(float r2, float r3, float r4, float r5, Region.Op r6) {
            this(new RectF(r2, r3, r4, r5), r6);
        }

        public ClipRectCmd(RectF r2, Region.Op r3) {
            this.rect = new RectF(r2);
            this.op = r3;
            if (r3 == null) goto L5;
            boolean r22 = true;
        L6:
            this.hasOp = r22;
            return;
        L5:
            r22 = false;
            goto L6
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r3) {
            boolean r0 = this.hasOp;
            RectF r1 = this.rect;
            if (r0 == false) goto L6;
            r3.clipRect(r1, this.op);
            return;
        L6:
            r3.clipRect(r1);
        }

        public String toString() {
            if (this.hasOp == false) goto L5;
            String r0 = " op=" + this.op;
        L6:
            StringBuilder r1 = new StringBuilder();
            RectF r2 = this.rect;
            r1.append(DebugRecordingCanvas.m5182$$Nest$smcoordWarn(new float[]{r2.left, r2.top, r2.right, r2.bottom}));
            r1.append("clipRect(");
            r1.append(DebugRecordingCanvas.m5187$$Nest$smrectInfo(this.rect));
            r1.append(r0);
            r1.append(")");
            return r1.toString();
        L5:
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L6
        }
    }

    public static abstract class Command {
        public Command() {
        }

        public abstract void replay(Canvas r1);
    }

    public static final class ConcatCmd extends Command {
        final Matrix matrix;

        public ConcatCmd(Matrix r2) {
            if (r2 == null) goto L5;
            Matrix r0 = new Matrix(r2);
        L6:
            this.matrix = r0;
            return;
        L5:
            r0 = null;
            goto L6
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r1) {
            r1.concat(this.matrix);
        }

        public String toString() {
            return "concat(" + DebugRecordingCanvas.m5183$$Nest$smmatrixInfo(this.matrix) + ")";
        }
    }

    public static final class DrawArcCmd extends Command {
        final RectF oval;
        final Paint paint;
        final float startAngle;
        final float sweepAngle;
        final boolean useCenter;

        public DrawArcCmd(RectF r2, float r3, float r4, boolean r5, Paint r6) {
            this.oval = new RectF(r2);
            this.startAngle = r3;
            this.sweepAngle = r4;
            this.useCenter = r5;
            this.paint = new Paint(r6);
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r7) {
            r7.drawArc(this.oval, this.startAngle, this.sweepAngle, this.useCenter, this.paint);
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder();
            r0.append(DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint));
            RectF r1 = this.oval;
            r0.append(DebugRecordingCanvas.m5182$$Nest$smcoordWarn(new float[]{r1.left, r1.top, r1.right, r1.bottom}));
            r0.append("drawArc(");
            r0.append(DebugRecordingCanvas.m5187$$Nest$smrectInfo(this.oval));
            r0.append(" start=");
            r0.append(this.startAngle);
            r0.append(" sweep=");
            r0.append(this.sweepAngle);
            r0.append(" center=");
            r0.append(this.useCenter);
            r0.append(" ");
            r0.append(DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint));
            r0.append(")");
            return r0.toString();
        }
    }

    public static final class DrawBitmapCmd extends Command {
        final Bitmap bitmap;
        final float left;
        final Paint paint;
        final float top;

        public DrawBitmapCmd(Bitmap r3, float r4, float r5, Paint r6) {
            if (r3.getConfig() == null) goto L5;
            Bitmap.Config r0 = r3.getConfig();
        L6:
            this.bitmap = r3.copy(r0, false);
            this.left = r4;
            this.top = r5;
            if (r6 == null) goto L9;
            Paint r32 = new Paint(r6);
        L10:
            this.paint = r32;
            return;
        L9:
            r32 = null;
            goto L10
        L5:
            r0 = Bitmap.Config.ARGB_8888;
            goto L6
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r4) {
            r4.drawBitmap(this.bitmap, this.left, this.top, this.paint);
        }

        public String toString() {
            return DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint) + DebugRecordingCanvas.m5182$$Nest$smcoordWarn(new float[]{this.left, this.top}) + "drawBitmap(" + DebugRecordingCanvas.m5181$$Nest$smbitmapInfo(this.bitmap) + " x=" + this.left + " y=" + this.top + " " + DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint) + ")";
        }
    }

    public static final class DrawBitmapMatrixCmd extends Command {
        final Bitmap bitmap;
        final Matrix matrix;
        final Paint paint;

        public DrawBitmapMatrixCmd(Bitmap r3, Matrix r4, Paint r5) {
            if (r3.getConfig() == null) goto L5;
            Bitmap.Config r0 = r3.getConfig();
        L6:
            this.bitmap = r3.copy(r0, false);
            this.matrix = new Matrix(r4);
            if (r5 == null) goto L9;
            Paint r32 = new Paint(r5);
        L10:
            this.paint = r32;
            return;
        L9:
            r32 = null;
            goto L10
        L5:
            r0 = Bitmap.Config.ARGB_8888;
            goto L6
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r3) {
            r3.drawBitmap(this.bitmap, this.matrix, this.paint);
        }

        public String toString() {
            return DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint) + "drawBitmap(" + DebugRecordingCanvas.m5181$$Nest$smbitmapInfo(this.bitmap) + " " + DebugRecordingCanvas.m5183$$Nest$smmatrixInfo(this.matrix) + " " + DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint) + ")";
        }
    }

    public static final class DrawBitmapMeshCmd extends Command {
        final Bitmap bitmap;
        final int colorOffset;
        final int[] colors;
        final int meshHeight;
        final int meshWidth;
        final Paint paint;
        final int vertOffset;
        final float[] verts;

        public DrawBitmapMeshCmd(Bitmap r3, int r4, int r5, float[] r6, int r7, int[] r8, int r9, Paint r10) {
            if (r3.getConfig() == null) goto L5;
            Bitmap.Config r0 = r3.getConfig();
        L6:
            this.bitmap = r3.copy(r0, false);
            this.meshWidth = r4;
            this.meshHeight = r5;
            this.verts = Arrays.copyOf(r6, r6.length);
            this.vertOffset = r7;
            Paint r32 = null;
            if (r8 == null) goto L9;
            int[] r42 = Arrays.copyOf(r8, r8.length);
        L10:
            this.colors = r42;
            this.colorOffset = r9;
            if (r10 == null) goto L13;
            r32 = new Paint(r10);
        L13:
            this.paint = r32;
            return;
        L9:
            r42 = null;
            goto L10
        L5:
            r0 = Bitmap.Config.ARGB_8888;
            goto L6
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r10) {
            r10.drawBitmapMesh(this.bitmap, this.meshWidth, this.meshHeight, this.verts, this.vertOffset, this.colors, this.colorOffset, this.paint);
        }

        public String toString() {
            return DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint) + "drawBitmapMesh(" + DebugRecordingCanvas.m5181$$Nest$smbitmapInfo(this.bitmap) + " mesh=" + this.meshWidth + "x" + this.meshHeight + " " + DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint) + ")";
        }
    }

    public static final class DrawBitmapSrcDstCmd extends Command {
        final Bitmap bitmap;
        final Rect dst;
        final Paint paint;
        final Rect src;

        public DrawBitmapSrcDstCmd(Bitmap r3, Rect r4, Rect r5, Paint r6) {
            if (r3.getConfig() == null) goto L5;
            Bitmap.Config r0 = r3.getConfig();
        L6:
            this.bitmap = r3.copy(r0, false);
            Paint r32 = null;
            if (r4 == null) goto L9;
            Rect r02 = new Rect(r4);
        L10:
            this.src = r02;
            this.dst = new Rect(r5);
            if (r6 == null) goto L13;
            r32 = new Paint(r6);
        L13:
            this.paint = r32;
            return;
        L9:
            r02 = null;
            goto L10
        L5:
            r0 = Bitmap.Config.ARGB_8888;
            goto L6
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r4) {
            r4.drawBitmap(this.bitmap, this.src, this.dst, this.paint);
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder();
            r0.append(DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint));
            Rect r1 = this.dst;
            r0.append(DebugRecordingCanvas.m5182$$Nest$smcoordWarn(new float[]{r1.left, r1.top, r1.right, r1.bottom}));
            r0.append("drawBitmap(");
            r0.append(DebugRecordingCanvas.m5181$$Nest$smbitmapInfo(this.bitmap));
            r0.append(" src=");
            r0.append(this.src);
            r0.append(" dst=");
            r0.append(this.dst);
            r0.append(" ");
            r0.append(DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint));
            r0.append(")");
            return r0.toString();
        }
    }

    public static final class DrawBitmapSrcDstFCmd extends Command {
        final Bitmap bitmap;
        final RectF dst;
        final Paint paint;
        final Rect src;

        public DrawBitmapSrcDstFCmd(Bitmap r3, Rect r4, RectF r5, Paint r6) {
            if (r3.getConfig() == null) goto L5;
            Bitmap.Config r0 = r3.getConfig();
        L6:
            this.bitmap = r3.copy(r0, false);
            Paint r32 = null;
            if (r4 == null) goto L9;
            Rect r02 = new Rect(r4);
        L10:
            this.src = r02;
            this.dst = new RectF(r5);
            if (r6 == null) goto L13;
            r32 = new Paint(r6);
        L13:
            this.paint = r32;
            return;
        L9:
            r02 = null;
            goto L10
        L5:
            r0 = Bitmap.Config.ARGB_8888;
            goto L6
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r4) {
            r4.drawBitmap(this.bitmap, this.src, this.dst, this.paint);
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder();
            r0.append(DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint));
            RectF r1 = this.dst;
            r0.append(DebugRecordingCanvas.m5182$$Nest$smcoordWarn(new float[]{r1.left, r1.top, r1.right, r1.bottom}));
            r0.append("drawBitmap(");
            r0.append(DebugRecordingCanvas.m5181$$Nest$smbitmapInfo(this.bitmap));
            r0.append(" src=");
            r0.append(this.src);
            r0.append(" dst=");
            r0.append(DebugRecordingCanvas.m5187$$Nest$smrectInfo(this.dst));
            r0.append(" ");
            r0.append(DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint));
            r0.append(")");
            return r0.toString();
        }
    }

    public static final class DrawCircleCmd extends Command {
        final float cx;
        final float cy;
        final Paint paint;
        final float radius;

        public DrawCircleCmd(float r1, float r2, float r3, Paint r4) {
            this.cx = r1;
            this.cy = r2;
            this.radius = r3;
            this.paint = new Paint(r4);
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r4) {
            r4.drawCircle(this.cx, this.cy, this.radius, this.paint);
        }

        public String toString() {
            return DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint) + DebugRecordingCanvas.m5182$$Nest$smcoordWarn(new float[]{this.cx, this.cy}) + "drawCircle(cx=" + this.cx + " cy=" + this.cy + " r=" + this.radius + " " + DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint) + ")";
        }
    }

    public static final class DrawColorCmd extends Command {
        final int color;
        final boolean hasMode;
        final PorterDuff.Mode mode;

        public DrawColorCmd(int r1) {
            this.color = r1;
            this.mode = null;
            this.hasMode = false;
        }

        public DrawColorCmd(int r1, PorterDuff.Mode r2) {
            this.color = r1;
            this.mode = r2;
            this.hasMode = true;
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r3) {
            boolean r0 = this.hasMode;
            int r1 = this.color;
            if (r0 == false) goto L6;
            r3.drawColor(r1, this.mode);
            return;
        L6:
            r3.drawColor(r1);
        }

        public String toString() {
            int r0 = this.color >>> 24;
            String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r0 > 0) goto L5;
            String r02 = "⚠ PAINT_ALPHA_ZERO ";
        L7:
            if (this.hasMode == false) goto L10;
            r1 = " mode=" + this.mode;
        L10:
            return r02 + "drawColor(0x" + Integer.toHexString(this.color) + r1 + ")";
        L5:
            r02 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L7
        }
    }

    public static final class DrawLineCmd extends Command {
        final Paint paint;
        final float startX;
        final float startY;
        final float stopX;
        final float stopY;

        public DrawLineCmd(float r1, float r2, float r3, float r4, Paint r5) {
            this.startX = r1;
            this.startY = r2;
            this.stopX = r3;
            this.stopY = r4;
            this.paint = new Paint(r5);
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r7) {
            r7.drawLine(this.startX, this.startY, this.stopX, this.stopY, this.paint);
        }

        public String toString() {
            return DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint) + DebugRecordingCanvas.m5182$$Nest$smcoordWarn(new float[]{this.startX, this.startY, this.stopX, this.stopY}) + "drawLine(" + this.startX + "," + this.startY + " → " + this.stopX + "," + this.stopY + " " + DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint) + ")";
        }
    }

    public static final class DrawLinesCmd extends Command {
        final int count;
        final int offset;
        final Paint paint;
        final float[] pts;

        public DrawLinesCmd(float[] r2, int r3, int r4, Paint r5) {
            this.pts = Arrays.copyOf(r2, r2.length);
            this.offset = r3;
            this.count = r4;
            this.paint = new Paint(r5);
        }

        public DrawLinesCmd(float[] r3, Paint r4) {
            this(r3, 0, r3.length, r4);
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r4) {
            r4.drawLines(this.pts, this.offset, this.count, this.paint);
        }

        public String toString() {
            return DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint) + "drawLines(count=" + this.count + " " + DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint) + ")";
        }
    }

    public static final class DrawOvalCmd extends Command {
        final RectF oval;
        final Paint paint;

        public DrawOvalCmd(RectF r2, Paint r3) {
            this.oval = new RectF(r2);
            this.paint = new Paint(r3);
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r2) {
            r2.drawOval(this.oval, this.paint);
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder();
            r0.append(DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint));
            RectF r1 = this.oval;
            r0.append(DebugRecordingCanvas.m5182$$Nest$smcoordWarn(new float[]{r1.left, r1.top, r1.right, r1.bottom}));
            r0.append("drawOval(");
            r0.append(DebugRecordingCanvas.m5187$$Nest$smrectInfo(this.oval));
            r0.append(" ");
            r0.append(DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint));
            r0.append(")");
            return r0.toString();
        }
    }

    public static final class DrawPaintCmd extends Command {
        final Paint paint;

        public DrawPaintCmd(Paint r2) {
            this.paint = new Paint(r2);
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r1) {
            r1.drawPaint(this.paint);
        }

        public String toString() {
            return DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint) + "drawPaint(" + DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint) + ")";
        }
    }

    public static final class DrawPatchCmd extends Command {
        final Rect dst;
        final RectF dstF;
        final Paint paint;
        final NinePatch patch;

        public DrawPatchCmd(NinePatch r1, Rect r2, Paint r3) {
            this.patch = copyPatch(r1);
            this.dst = new Rect(r2);
            Paint r12 = null;
            this.dstF = null;
            if (r3 == null) goto L5;
            r12 = new Paint(r3);
        L5:
            this.paint = r12;
        }

        public DrawPatchCmd(NinePatch r2, RectF r3, Paint r4) {
            this.patch = copyPatch(r2);
            Paint r22 = null;
            this.dst = null;
            this.dstF = new RectF(r3);
            if (r4 == null) goto L5;
            r22 = new Paint(r4);
        L5:
            this.paint = r22;
        }

        private static NinePatch copyPatch(NinePatch r0) {
            return r0;
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r3) {
            RectF r0 = this.dstF;
            NinePatch r1 = this.patch;
            if (r0 == null) goto L6;
            r3.drawPatch(r1, r0, this.paint);
            return;
        L6:
            r3.drawPatch(r1, this.dst, this.paint);
        }

        public String toString() {
            RectF r0 = this.dstF;
            if (r0 == null) goto L5;
            String r02 = DebugRecordingCanvas.m5187$$Nest$smrectInfo(r0);
        L7:
            return DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint) + "drawPatch(dst=" + r02 + " " + DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint) + ")";
        L5:
            r02 = String.valueOf(this.dst);
            goto L7
        }
    }

    public static final class DrawPathCmd extends Command {
        final Paint paint;
        final Path path;

        public DrawPathCmd(Path r2, Paint r3) {
            this.path = new Path(r2);
            this.paint = new Paint(r3);
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r2) {
            r2.drawPath(this.path, this.paint);
        }

        public String toString() {
            return DebugRecordingCanvas.m5186$$Nest$smpathWarn(this.path) + DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint) + "drawPath(" + DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint) + ")";
        }
    }

    public static final class DrawPictureCmd extends Command {
        final RectF dst;
        final boolean hasDst;
        final Picture picture;

        public DrawPictureCmd(Picture r1) {
            this.picture = r1;
            this.dst = null;
            this.hasDst = false;
        }

        public DrawPictureCmd(Picture r1, Rect r2) {
            this.picture = r1;
            this.dst = new RectF(r2);
            this.hasDst = true;
        }

        public DrawPictureCmd(Picture r1, RectF r2) {
            this.picture = r1;
            this.dst = new RectF(r2);
            this.hasDst = true;
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r3) {
            boolean r0 = this.hasDst;
            Picture r1 = this.picture;
            if (r0 == false) goto L6;
            r3.drawPicture(r1, this.dst);
            return;
        L6:
            r3.drawPicture(r1);
        }

        public String toString() {
            if (this.hasDst == false) goto L5;
            String r0 = " dst=" + DebugRecordingCanvas.m5187$$Nest$smrectInfo(this.dst);
        L7:
            return "drawPicture(size=" + this.picture.getWidth() + "x" + this.picture.getHeight() + r0 + ")";
        L5:
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L7
        }
    }

    public static final class DrawPointCmd extends Command {
        final Paint paint;
        final float x;
        final float y;

        public DrawPointCmd(float r1, float r2, Paint r3) {
            this.x = r1;
            this.y = r2;
            this.paint = new Paint(r3);
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r3) {
            r3.drawPoint(this.x, this.y, this.paint);
        }

        public String toString() {
            return DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint) + DebugRecordingCanvas.m5182$$Nest$smcoordWarn(new float[]{this.x, this.y}) + "drawPoint(" + this.x + "," + this.y + " " + DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint) + ")";
        }
    }

    public static final class DrawPointsCmd extends Command {
        final int count;
        final int offset;
        final Paint paint;
        final float[] pts;

        public DrawPointsCmd(float[] r2, int r3, int r4, Paint r5) {
            this.pts = Arrays.copyOf(r2, r2.length);
            this.offset = r3;
            this.count = r4;
            this.paint = new Paint(r5);
        }

        public DrawPointsCmd(float[] r3, Paint r4) {
            this(r3, 0, r3.length, r4);
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r4) {
            r4.drawPoints(this.pts, this.offset, this.count, this.paint);
        }

        public String toString() {
            return DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint) + "drawPoints(count=" + this.count + " " + DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint) + ")";
        }
    }

    public static final class DrawRectCmd extends Command {
        final Paint paint;
        final RectF rect;

        public DrawRectCmd(float r2, float r3, float r4, float r5, Paint r6) {
            this(new RectF(r2, r3, r4, r5), r6);
        }

        public DrawRectCmd(RectF r2, Paint r3) {
            this.rect = new RectF(r2);
            this.paint = new Paint(r3);
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r2) {
            r2.drawRect(this.rect, this.paint);
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder();
            r0.append(DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint));
            RectF r1 = this.rect;
            r0.append(DebugRecordingCanvas.m5182$$Nest$smcoordWarn(new float[]{r1.left, r1.top, r1.right, r1.bottom}));
            r0.append("drawRect(");
            r0.append(DebugRecordingCanvas.m5187$$Nest$smrectInfo(this.rect));
            r0.append(" ");
            r0.append(DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint));
            r0.append(")");
            return r0.toString();
        }
    }

    public static final class DrawRoundRectCmd extends Command {
        final Paint paint;
        final RectF rect;
        final float rx;
        final float ry;

        public DrawRoundRectCmd(RectF r2, float r3, float r4, Paint r5) {
            this.rect = new RectF(r2);
            this.rx = r3;
            this.ry = r4;
            this.paint = new Paint(r5);
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r4) {
            r4.drawRoundRect(this.rect, this.rx, this.ry, this.paint);
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder();
            r0.append(DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint));
            RectF r1 = this.rect;
            r0.append(DebugRecordingCanvas.m5182$$Nest$smcoordWarn(new float[]{r1.left, r1.top, r1.right, r1.bottom}));
            r0.append("drawRoundRect(");
            r0.append(DebugRecordingCanvas.m5187$$Nest$smrectInfo(this.rect));
            r0.append(" rx=");
            r0.append(this.rx);
            r0.append(" ry=");
            r0.append(this.ry);
            r0.append(" ");
            r0.append(DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint));
            r0.append(")");
            return r0.toString();
        }
    }

    public static final class DrawTextCmd extends Command {
        final int charArrayCount;
        final int charArrayOffset;
        final int end;
        final boolean isCharArray;
        final boolean isSpanned;
        final Paint paint;
        final int start;
        final CharSequence text;
        final float x;
        final float y;

        public DrawTextCmd(CharSequence r1, int r2, int r3, float r4, float r5, Paint r6) {
            this.text = r1.toString().substring(r2, r3);
            this.start = 0;
            this.end = r3 - r2;
            this.x = r4;
            this.y = r5;
            this.paint = new Paint(r6);
            this.charArrayOffset = 0;
            this.charArrayCount = 0;
            this.isCharArray = false;
            this.isSpanned = true;
        }

        public DrawTextCmd(String r2, float r3, float r4, Paint r5) {
            this.text = r2;
            this.start = 0;
            this.end = r2.length();
            this.x = r3;
            this.y = r4;
            this.paint = new Paint(r5);
            this.charArrayOffset = 0;
            this.charArrayCount = 0;
            this.isCharArray = false;
            this.isSpanned = false;
        }

        public DrawTextCmd(String r1, int r2, int r3, float r4, float r5, Paint r6) {
            this.text = r1.substring(r2, r3);
            this.start = 0;
            this.end = r3 - r2;
            this.x = r4;
            this.y = r5;
            this.paint = new Paint(r6);
            this.charArrayOffset = 0;
            this.charArrayCount = 0;
            this.isCharArray = false;
            this.isSpanned = false;
        }

        public DrawTextCmd(char[] r2, int r3, int r4, float r5, float r6, Paint r7) {
            this.text = new String(r2, r3, r4);
            this.start = 0;
            this.end = r4;
            this.x = r5;
            this.y = r6;
            this.paint = new Paint(r7);
            this.charArrayOffset = r3;
            this.charArrayCount = r4;
            this.isCharArray = true;
            this.isSpanned = false;
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r9) {
            r9.drawText(this.text.toString(), 0, this.text.length(), this.x, this.y, this.paint);
        }

        public String toString() {
            return DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint) + DebugRecordingCanvas.m5182$$Nest$smcoordWarn(new float[]{this.x, this.y}) + "drawText(\"" + this.text + "\" x=" + this.x + " y=" + this.y + " " + DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint) + ")";
        }
    }

    public static final class DrawTextOnPathCmd extends Command {
        final float hOffset;
        final Paint paint;
        final Path path;
        final String text;
        final float vOffset;

        public DrawTextOnPathCmd(String r1, Path r2, float r3, float r4, Paint r5) {
            this.text = r1;
            this.path = new Path(r2);
            this.hOffset = r3;
            this.vOffset = r4;
            this.paint = new Paint(r5);
        }

        public DrawTextOnPathCmd(char[] r2, int r3, int r4, Path r5, float r6, float r7, Paint r8) {
            this.text = new String(r2, r3, r4);
            this.path = new Path(r5);
            this.hOffset = r6;
            this.vOffset = r7;
            this.paint = new Paint(r8);
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r7) {
            r7.drawTextOnPath(this.text, this.path, this.hOffset, this.vOffset, this.paint);
        }

        public String toString() {
            return DebugRecordingCanvas.m5186$$Nest$smpathWarn(this.path) + DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint) + "drawTextOnPath(\"" + this.text + "\" h=" + this.hOffset + " v=" + this.vOffset + " " + DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint) + ")";
        }
    }

    public static final class DrawTextRunCmd extends Command {
        final char[] chars;
        final int contextCount;
        final int contextIndex;
        final int count;
        final int end;
        final int index;
        final boolean isCharArray;
        final boolean isRtl;
        final Paint paint;
        final int start;
        final String str;
        final float x;
        final float y;

        public DrawTextRunCmd(CharSequence r2, int r3, int r4, int r5, int r6, float r7, float r8, boolean r9, Paint r10) {
            this.chars = null;
            this.str = r2.toString();
            this.index = 0;
            this.count = 0;
            this.contextIndex = r5;
            this.contextCount = r6;
            this.start = r3;
            this.end = r4;
            this.x = r7;
            this.y = r8;
            this.isRtl = r9;
            this.isCharArray = false;
            this.paint = new Paint(r10);
        }

        public DrawTextRunCmd(char[] r2, int r3, int r4, int r5, int r6, float r7, float r8, boolean r9, Paint r10) {
            this.chars = Arrays.copyOf(r2, r2.length);
            this.str = null;
            this.index = r3;
            this.count = r4;
            this.contextIndex = r5;
            this.contextCount = r6;
            this.start = 0;
            this.end = 0;
            this.x = r7;
            this.y = r8;
            this.isRtl = r9;
            this.isCharArray = true;
            this.paint = new Paint(r10);
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r23) {
            if (this.isCharArray == false) goto L6;
            r23.drawTextRun(this.chars, this.index, this.count, this.contextIndex, this.contextCount, this.x, this.y, this.isRtl, this.paint);
            return;
        L6:
            r23.drawTextRun(this.str, this.start, this.end, this.contextIndex, this.contextCount, this.x, this.y, this.isRtl, this.paint);
        }

        public String toString() {
            if (this.isCharArray == false) goto L5;
            String r0 = new String(this.chars, this.index, this.count);
        L7:
            return DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint) + DebugRecordingCanvas.m5182$$Nest$smcoordWarn(new float[]{this.x, this.y}) + "drawTextRun(\"" + r0 + "\" x=" + this.x + " y=" + this.y + " rtl=" + this.isRtl + " " + DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint) + ")";
        L5:
            r0 = this.str;
            goto L7
        }
    }

    public static final class DrawTextRunMeasuredCmd extends Command {
        final int contextEnd;
        final int contextStart;
        final int end;
        final boolean isRtl;
        final Paint paint;
        final int start;
        final MeasuredText text;
        final float x;
        final float y;

        public DrawTextRunMeasuredCmd(MeasuredText r1, int r2, int r3, int r4, int r5, float r6, float r7, boolean r8, Paint r9) {
            this.text = r1;
            this.start = r2;
            this.end = r3;
            this.contextStart = r4;
            this.contextEnd = r5;
            this.x = r6;
            this.y = r7;
            this.isRtl = r8;
            this.paint = new Paint(r9);
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r13) {
            if (Build.VERSION.SDK_INT < 29) goto L6;
            r13.drawTextRun(this.text, this.start, this.end, this.contextStart, this.contextEnd, this.x, this.y, this.isRtl, this.paint);
            return;
        }

        public String toString() {
            return DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint) + DebugRecordingCanvas.m5182$$Nest$smcoordWarn(new float[]{this.x, this.y}) + "drawTextRun(MeasuredText start=" + this.start + " end=" + this.end + " x=" + this.x + " y=" + this.y + " rtl=" + this.isRtl + " " + DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint) + ")";
        }
    }

    public static final class DrawVerticesCmd extends Command {
        final int colorOffset;
        final int[] colors;
        final int indexCount;
        final int indexOffset;
        final short[] indices;
        final Canvas.VertexMode mode;
        final Paint paint;
        final int texOffset;
        final float[] texs;
        final int vertOffset;
        final int vertexCount;
        final float[] verts;

        public DrawVerticesCmd(Canvas.VertexMode r1, int r2, float[] r3, int r4, float[] r5, int r6, int[] r7, int r8, short[] r9, int r10, int r11, Paint r12) {
            this.mode = r1;
            this.vertexCount = r2;
            this.verts = Arrays.copyOf(r3, r3.length);
            this.vertOffset = r4;
            short[] r13 = null;
            if (r5 == null) goto L5;
            float[] r22 = Arrays.copyOf(r5, r5.length);
        L6:
            this.texs = r22;
            this.texOffset = r6;
            if (r7 == null) goto L9;
            int[] r23 = Arrays.copyOf(r7, r7.length);
        L10:
            this.colors = r23;
            this.colorOffset = r8;
            if (r9 == null) goto L13;
            r13 = Arrays.copyOf(r9, r9.length);
        L13:
            this.indices = r13;
            this.indexOffset = r10;
            this.indexCount = r11;
            this.paint = new Paint(r12);
            return;
        L9:
            r23 = null;
            goto L10
        L5:
            r22 = null;
            goto L6
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r14) {
            r14.drawVertices(this.mode, this.vertexCount, this.verts, this.vertOffset, this.texs, this.texOffset, this.colors, this.colorOffset, this.indices, this.indexOffset, this.indexCount, this.paint);
        }

        public String toString() {
            return DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint) + "drawVertices(mode=" + this.mode + " vertexCount=" + this.vertexCount + " " + DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint) + ")";
        }
    }

    public static final class GetClipBoundsCmd extends Command {
        final Rect bounds;
        final boolean nonEmpty;

        public GetClipBoundsCmd(Rect r2, boolean r3) {
            this.bounds = new Rect(r2);
            this.nonEmpty = r3;
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r1) {
            r1.getClipBounds(new Rect());
        }

        public String toString() {
            if (this.nonEmpty == true) goto L5;
            String r0 = "⚠ CLIP_EMPTY ";
        L7:
            return r0 + "getClipBounds(Rect) -> nonEmpty=" + this.nonEmpty + " bounds=" + this.bounds.toShortString();
        L5:
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L7
        }
    }

    public static final class QuickRejectCmd extends Command {
        final float bottom;
        final String edgeType;
        final Kind kind;
        final float left;
        final Path path;
        final RectF rectF;
        final boolean rejected;
        final float right;
        final float top;

        public enum Kind extends Enum<Kind> {
            private static final /* synthetic */ Kind[] $VALUES = null;
            public static final Kind COORDS = null;
            public static final Kind COORDS_EDGE = null;
            public static final Kind PATH = null;
            public static final Kind PATH_EDGE = null;
            public static final Kind RECT_F = null;
            public static final Kind RECT_F_EDGE = null;

            private static /* synthetic */ Kind[] $values() {
                return new Kind[]{RECT_F, RECT_F_EDGE, COORDS, COORDS_EDGE, PATH, PATH_EDGE};
            }

            static {
                RECT_F = new Kind("RECT_F", 0);
                RECT_F_EDGE = new Kind("RECT_F_EDGE", 1);
                COORDS = new Kind("COORDS", 2);
                COORDS_EDGE = new Kind("COORDS_EDGE", 3);
                PATH = new Kind("PATH", 4);
                PATH_EDGE = new Kind("PATH_EDGE", 5);
                $VALUES = $values();
            }

            Kind(String r1, int r2) {
            }

            public static Kind valueOf(String r1) {
                return (Kind) Enum.valueOf(Kind.class, r1);
            }

            public static Kind[] values() {
                return (Kind[]) $VALUES.clone();
            }
        }

        public QuickRejectCmd(float r2, float r3, float r4, float r5, Canvas.EdgeType r6, boolean r7) {
            this.kind = Kind.COORDS_EDGE;
            this.rectF = null;
            this.path = null;
            this.edgeType = r6.name();
            this.left = r2;
            this.top = r3;
            this.right = r4;
            this.bottom = r5;
            this.rejected = r7;
        }

        public QuickRejectCmd(float r2, float r3, float r4, float r5, boolean r6) {
            this.kind = Kind.COORDS;
            this.rectF = null;
            this.path = null;
            this.edgeType = null;
            this.left = r2;
            this.top = r3;
            this.right = r4;
            this.bottom = r5;
            this.rejected = r6;
        }

        public QuickRejectCmd(Path r2, Canvas.EdgeType r3, boolean r4) {
            this.kind = Kind.PATH_EDGE;
            this.path = new Path(r2);
            this.rectF = null;
            this.edgeType = r3.name();
            this.bottom = 0.0f;
            this.right = 0.0f;
            this.top = 0.0f;
            this.left = 0.0f;
            this.rejected = r4;
        }

        public QuickRejectCmd(Path r2, boolean r3) {
            this.kind = Kind.PATH;
            this.path = new Path(r2);
            this.rectF = null;
            this.edgeType = null;
            this.bottom = 0.0f;
            this.right = 0.0f;
            this.top = 0.0f;
            this.left = 0.0f;
            this.rejected = r3;
        }

        public QuickRejectCmd(RectF r2, Canvas.EdgeType r3, boolean r4) {
            this.kind = Kind.RECT_F_EDGE;
            this.rectF = new RectF(r2);
            this.bottom = 0.0f;
            this.right = 0.0f;
            this.top = 0.0f;
            this.left = 0.0f;
            this.path = null;
            this.edgeType = r3.name();
            this.rejected = r4;
        }

        public QuickRejectCmd(RectF r2, boolean r3) {
            this.kind = Kind.RECT_F;
            this.rectF = new RectF(r2);
            this.bottom = 0.0f;
            this.right = 0.0f;
            this.top = 0.0f;
            this.left = 0.0f;
            this.path = null;
            this.edgeType = null;
            this.rejected = r3;
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r7) {
            switch(AnonymousClass3.$SwitchMap$org$telegram$messenger$utils$DebugRecordingCanvas$QuickRejectCmd$Kind[this.kind.ordinal()]) {
                case 1: goto L20;
                case 2: goto L17;
                case 3: goto L14;
                case 4: goto L11;
                case 5: goto L8;
                case 6: goto L5;
                default: goto L26;
            };
        L5:
            r7.quickReject(this.path, Canvas.EdgeType.valueOf(this.edgeType));
            return;
        L11:
            r7.quickReject(this.left, this.top, this.right, this.bottom, Canvas.EdgeType.valueOf(this.edgeType));
            return;
        L17:
            r7.quickReject(this.rectF, Canvas.EdgeType.valueOf(this.edgeType));
            return;
        L26:
            return;
        L8:
            if (Build.VERSION.SDK_INT < 30) goto L23;
            r7.quickReject(this.path);
            return;
        L23:
            return;
        L14:
            if (Build.VERSION.SDK_INT < 30) goto L24;
            r7.quickReject(this.left, this.top, this.right, this.bottom);
            return;
        L24:
            return;
        L20:
            if (Build.VERSION.SDK_INT < 30) goto L25;
            r7.quickReject(this.rectF);
            return;
        }

        public String toString() {
            String r0 = " -> " + this.rejected;
            if (this.edgeType == null) goto L5;
            String r1 = " edgeType=" + this.edgeType;
        L7:
            switch(AnonymousClass3.$SwitchMap$org$telegram$messenger$utils$DebugRecordingCanvas$QuickRejectCmd$Kind[this.kind.ordinal()]) {
                case 1: goto L14;
                case 2: goto L14;
                case 3: goto L13;
                case 4: goto L13;
                case 5: goto L11;
                case 6: goto L11;
                default: goto L9;
            };
        L14:
            StringBuilder r2 = new StringBuilder();
            RectF r10 = this.rectF;
            r2.append(DebugRecordingCanvas.m5182$$Nest$smcoordWarn(new float[]{r10.left, r10.top, r10.right, r10.bottom}));
            r2.append("quickReject(");
            r2.append(DebugRecordingCanvas.m5187$$Nest$smrectInfo(this.rectF));
            r2.append(r1);
            r2.append(r0);
            r2.append(")");
            return r2.toString();
        L9:
            return "quickReject(?" + r0 + ")";
        L11:
            return DebugRecordingCanvas.m5186$$Nest$smpathWarn(this.path) + "quickReject(path" + r1 + r0 + ")";
        L13:
            return DebugRecordingCanvas.m5182$$Nest$smcoordWarn(new float[]{this.left, this.top, this.right, this.bottom}) + "quickReject(" + this.left + "," + this.top + "," + this.right + "," + this.bottom + r1 + r0 + ")";
        L5:
            r1 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L7
        }
    }

    public static final class RestoreCmd extends Command {
        public RestoreCmd() {
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r1) {
            r1.restore();
        }

        public String toString() {
            return "restore()";
        }
    }

    public static final class RestoreToCountCmd extends Command {
        final int count;

        public RestoreToCountCmd(int r1) {
            this.count = r1;
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r1) {
            r1.restoreToCount(this.count);
        }

        public String toString() {
            return "restoreToCount(" + this.count + ")";
        }
    }

    public static final class RotateCmd extends Command {
        final float deg;

        public RotateCmd(float r1) {
            this.deg = r1;
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r1) {
            r1.rotate(this.deg);
        }

        public String toString() {
            return "rotate(" + this.deg + ")";
        }
    }

    public static final class SaveCmd extends Command {
        public SaveCmd() {
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r1) {
            r1.save();
        }

        public String toString() {
            return "save()";
        }
    }

    public static final class SaveLayerAlphaCmd extends Command {
        final int alpha;
        final RectF bounds;

        public SaveLayerAlphaCmd(RectF r2, int r3) {
            if (r2 == null) goto L5;
            RectF r0 = new RectF(r2);
        L6:
            this.bounds = r0;
            this.alpha = r3;
            return;
        L5:
            r0 = null;
            goto L6
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r2) {
            r2.saveLayerAlpha(this.bounds, this.alpha);
        }

        public String toString() {
            int r0 = this.alpha;
            if (r0 > 0) goto L5;
        L8:
            String r02 = "⚠ ALPHA_SUSPICIOUS(" + this.alpha + ") ";
        L10:
            return r02 + "saveLayerAlpha(alpha=" + this.alpha + " " + DebugRecordingCanvas.m5187$$Nest$smrectInfo(this.bounds) + ")";
        L5:
            if (r0 >= 255) goto L8;
            r02 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L10
        }
    }

    public static final class SaveLayerCmd extends Command {
        final RectF bounds;
        final Paint paint;
        final int saveFlags;

        public SaveLayerCmd(RectF r3, Paint r4) {
            Paint r0 = null;
            if (r3 == null) goto L5;
            RectF r1 = new RectF(r3);
        L6:
            this.bounds = r1;
            if (r4 == null) goto L9;
            r0 = new Paint(r4);
        L9:
            this.paint = r0;
            this.saveFlags = 0;
            return;
        L5:
            r1 = null;
            goto L6
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r2) {
            r2.saveLayer(this.bounds, this.paint);
        }

        public String toString() {
            return DebugRecordingCanvas.m5185$$Nest$smpaintWarn(this.paint) + "saveLayer(" + DebugRecordingCanvas.m5187$$Nest$smrectInfo(this.bounds) + " " + DebugRecordingCanvas.m5184$$Nest$smpaintInfo(this.paint) + ")";
        }
    }

    public static final class ScaleCmd extends Command {
        final float sx;
        final float sy;

        public ScaleCmd(float r1, float r2) {
            this.sx = r1;
            this.sy = r2;
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r2) {
            r2.scale(this.sx, this.sy);
        }

        public String toString() {
            return "scale(" + this.sx + "," + this.sy + ")";
        }
    }

    public static final class SetDrawFilterCmd extends Command {
        final DrawFilter filter;

        public SetDrawFilterCmd(DrawFilter r1) {
            this.filter = r1;
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r1) {
            r1.setDrawFilter(this.filter);
        }

        public String toString() {
            return "setDrawFilter(" + this.filter + ")";
        }
    }

    public static final class SetMatrixCmd extends Command {
        final Matrix matrix;

        public SetMatrixCmd(Matrix r2) {
            if (r2 == null) goto L5;
            Matrix r0 = new Matrix(r2);
        L6:
            this.matrix = r0;
            return;
        L5:
            r0 = null;
            goto L6
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r1) {
            r1.setMatrix(this.matrix);
        }

        public String toString() {
            return "setMatrix(" + DebugRecordingCanvas.m5183$$Nest$smmatrixInfo(this.matrix) + ")";
        }
    }

    public static final class SkewCmd extends Command {
        final float sx;
        final float sy;

        public SkewCmd(float r1, float r2) {
            this.sx = r1;
            this.sy = r2;
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r2) {
            r2.skew(this.sx, this.sy);
        }

        public String toString() {
            return "skew(" + this.sx + "," + this.sy + ")";
        }
    }

    public static final class TranslateCmd extends Command {
        final float dx;
        final float dy;

        public TranslateCmd(float r1, float r2) {
            this.dx = r1;
            this.dy = r2;
        }

        @Override // org.telegram.messenger.utils.DebugRecordingCanvas.Command
        public void replay(Canvas r2) {
            r2.translate(this.dx, this.dy);
        }

        public String toString() {
            return DebugRecordingCanvas.m5182$$Nest$smcoordWarn(new float[]{this.dx, this.dy}) + "translate(" + this.dx + "," + this.dy + ")";
        }
    }

    /* renamed from: -$$Nest$smbitmapInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m5181$$Nest$smbitmapInfo(Bitmap r0) {
        return bitmapInfo(r0);
    }

    /* renamed from: -$$Nest$smcoordWarn, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m5182$$Nest$smcoordWarn(float[] r0) {
        return coordWarn(r0);
    }

    /* renamed from: -$$Nest$smmatrixInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m5183$$Nest$smmatrixInfo(Matrix r0) {
        return matrixInfo(r0);
    }

    /* renamed from: -$$Nest$smpaintInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m5184$$Nest$smpaintInfo(Paint r0) {
        return paintInfo(r0);
    }

    /* renamed from: -$$Nest$smpaintWarn, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m5185$$Nest$smpaintWarn(Paint r0) {
        return paintWarn(r0);
    }

    /* renamed from: -$$Nest$smpathWarn, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m5186$$Nest$smpathWarn(Path r0) {
        return pathWarn(r0);
    }

    /* renamed from: -$$Nest$smrectInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m5187$$Nest$smrectInfo(RectF r0) {
        return rectInfo(r0);
    }

    public DebugRecordingCanvas(Bitmap r1) {
        super(r1);
        this.mCommands = new ArrayList();
    }

    private static String bitmapInfo(Bitmap r2) {
        if (r2 != null) goto L6;
        return "bitmap=null";
    L6:
        return "bitmap(" + r2.getWidth() + "x" + r2.getHeight() + ")";
    }

    private static String coordWarn(float... r3) {
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Math.abs(r3[r1]);
        r1 = r1 + 1;
        goto L3
    L5:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private static String matrixInfo(Matrix r10) {
        if (r10 != null) goto L5;
        return "matrix=null";
    L5:
        float[] r0 = new float[9];
        r10.getValues(r0);
        return String.format("matrix([%.2f,%.2f,%.2f][%.2f,%.2f,%.2f][%.2f,%.2f,%.2f])", new Object[]{Float.valueOf(r0[0]), Float.valueOf(r0[1]), Float.valueOf(r0[2]), Float.valueOf(r0[3]), Float.valueOf(r0[4]), Float.valueOf(r0[5]), Float.valueOf(r0[6]), Float.valueOf(r0[7]), Float.valueOf(r0[8])});
    }

    private static String paintInfo(Paint r4) {
        if (r4 != null) goto L5;
        return "paint=null";
    L5:
        StringBuilder r0 = new StringBuilder("paint(alpha=");
        r0.append(r4.getAlpha());
        if (Build.VERSION.SDK_INT < 29) goto L10;
        float r1 = r4.getShadowLayerRadius();
        if (r1 <= 0.0f) goto L10;
        r0.append(" shadow(r=");
        r0.append(r1);
        r0.append(" dx=");
        r0.append(r4.getShadowLayerDx());
        r0.append(" dy=");
        r0.append(r4.getShadowLayerDy());
        r0.append(" color=0x");
        r0.append(Integer.toHexString(r4.getShadowLayerColor()));
        r0.append(")");
    L10:
        r0.append(")");
        return r0.toString();
    }

    private static String paintWarn(Paint r3) {
        if (r3 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        StringBuilder r0 = new StringBuilder();
        if (r3.getAlpha() > 0) goto L9;
        r0.append("⚠ PAINT_ALPHA_ZERO ");
    L9:
        if (Build.VERSION.SDK_INT < 29) goto L14;
        if (r3.getShadowLayerRadius() <= 0.0f) goto L14;
        r0.append("⚠ PAINT_HAS_SHADOW_LAYER ");
    L14:
        return r0.toString();
    }

    private static String pathWarn(Path r1) {
        if (r1.isEmpty() == false) goto L6;
        return "⚠ PATH_IS_EMPTY ";
    L6:
        r1.isConvex();
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private void record(Command r1) {
        validate(r1);
        this.mCommands.add(r1);
    }

    private static String rectInfo(RectF r3) {
        if (r3 != null) goto L6;
        return "rect=null";
    L6:
        return "rect(" + r3.left + "," + r3.top + "," + r3.right + "," + r3.bottom + ")";
    }

    private void validate(Command r3) {
        String r32 = r3.toString();
        if (r32.contains("⚠") == false) goto L6;
        Log.w("DebugRecordingCanvas", "recorded [" + this.mCommands.size() + "] " + r32);
        return;
    }

    @Override // android.graphics.Canvas
    public boolean clipPath(Path r1) {
        return super.clipPath(r1);
    }

    @Override // android.graphics.Canvas
    public boolean clipPath(Path r2, Region.Op r3) {
        record(new ClipPathCmd(r2, r3));
        return super.clipPath(r2, r3);
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(float r2, float r3, float r4, float r5) {
        record(new ClipRectCmd(r2, r3, r4, r5));
        return super.clipRect(r2, r3, r4, r5);
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(float r7, float r8, float r9, float r10, Region.Op r11) {
        record(new ClipRectCmd(r7, r8, r9, r10, r11));
        return super.clipRect(r7, r8, r9, r10, r11);
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(int r6, int r7, int r8, int r9) {
        record(new ClipRectCmd(r6, r7, r8, r9));
        return super.clipRect(r6, r7, r8, r9);
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(Rect r4) {
        record(new ClipRectCmd(new RectF(r4), null));
        return super.clipRect(r4);
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(Rect r3, Region.Op r4) {
        record(new ClipRectCmd(new RectF(r3), r4));
        return super.clipRect(r3, r4);
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(RectF r3) {
        record(new ClipRectCmd(r3, null));
        return super.clipRect(r3);
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(RectF r2, Region.Op r3) {
        record(new ClipRectCmd(r2, r3));
        return super.clipRect(r2, r3);
    }

    @Override // android.graphics.Canvas
    public void concat(Matrix r2) {
        record(new ConcatCmd(r2));
        super.concat(r2);
    }

    @Override // android.graphics.Canvas
    public void drawArc(float r7, float r8, float r9, float r10, float r11, float r12, boolean r13, Paint r14) {
        record(new DrawArcCmd(new RectF(r7, r8, r9, r10), r11, r12, r13, r14));
        super.drawArc(r7, r8, r9, r10, r11, r12, r13, r14);
    }

    @Override // android.graphics.Canvas
    public void drawArc(RectF r7, float r8, float r9, boolean r10, Paint r11) {
        record(new DrawArcCmd(r7, r8, r9, r10, r11));
        super.drawArc(r7, r8, r9, r10, r11);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap r2, float r3, float r4, Paint r5) {
        record(new DrawBitmapCmd(r2, r3, r4, r5));
        super.drawBitmap(r2, r3, r4, r5);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap r2, Matrix r3, Paint r4) {
        record(new DrawBitmapMatrixCmd(r2, r3, r4));
        super.drawBitmap(r2, r3, r4);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap r2, Rect r3, Rect r4, Paint r5) {
        record(new DrawBitmapSrcDstCmd(r2, r3, r4, r5));
        super.drawBitmap(r2, r3, r4, r5);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap r2, Rect r3, RectF r4, Paint r5) {
        record(new DrawBitmapSrcDstFCmd(r2, r3, r4, r5));
        super.drawBitmap(r2, r3, r4, r5);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(int[] r14, int r15, int r16, float r17, float r18, int r19, int r20, boolean r21, Paint r22) {
        int[] r4 = Arrays.copyOf(r14, r14.length);
        if (r22 == null) goto L6;
        Paint r1 = new Paint(r22);
    L7:
        record(new AnonymousClass1(this, r4, r15, r16, r17, r18, r19, r20, r21, r1));
        super.drawBitmap(r14, r15, r16, r17, r18, r19, r20, r21, r22);
        return;
    L6:
        r1 = null;
        goto L7
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(int[] r14, int r15, int r16, int r17, int r18, int r19, int r20, boolean r21, Paint r22) {
        int[] r4 = Arrays.copyOf(r14, r14.length);
        if (r22 == null) goto L6;
        Paint r1 = new Paint(r22);
    L7:
        record(new AnonymousClass2(this, r4, r15, r16, r17, r18, r19, r20, r21, r1));
        super.drawBitmap(r14, r15, r16, r17, r18, r19, r20, r21, r22);
        return;
    L6:
        r1 = null;
        goto L7
    }

    @Override // android.graphics.Canvas
    public void drawBitmapMesh(Bitmap r10, int r11, int r12, float[] r13, int r14, int[] r15, int r16, Paint r17) {
        record(new DrawBitmapMeshCmd(r10, r11, r12, r13, r14, r15, r16, r17));
        super.drawBitmapMesh(r10, r11, r12, r13, r14, r15, r16, r17);
    }

    @Override // android.graphics.Canvas
    public void drawCircle(float r2, float r3, float r4, Paint r5) {
        record(new DrawCircleCmd(r2, r3, r4, r5));
        super.drawCircle(r2, r3, r4, r5);
    }

    @Override // android.graphics.Canvas
    public void drawColor(int r2) {
        record(new DrawColorCmd(r2));
        super.drawColor(r2);
    }

    @Override // android.graphics.Canvas
    public void drawColor(int r2, PorterDuff.Mode r3) {
        record(new DrawColorCmd(r2, r3));
        super.drawColor(r2, r3);
    }

    @Override // android.graphics.Canvas
    public void drawLine(float r7, float r8, float r9, float r10, Paint r11) {
        record(new DrawLineCmd(r7, r8, r9, r10, r11));
        super.drawLine(r7, r8, r9, r10, r11);
    }

    @Override // android.graphics.Canvas
    public void drawLines(float[] r2, int r3, int r4, Paint r5) {
        record(new DrawLinesCmd(r2, r3, r4, r5));
        super.drawLines(r2, r3, r4, r5);
    }

    @Override // android.graphics.Canvas
    public void drawLines(float[] r2, Paint r3) {
        record(new DrawLinesCmd(r2, r3));
        super.drawLines(r2, r3);
    }

    @Override // android.graphics.Canvas
    public void drawOval(float r3, float r4, float r5, float r6, Paint r7) {
        record(new DrawOvalCmd(new RectF(r3, r4, r5, r6), r7));
        super.drawOval(r3, r4, r5, r6, r7);
    }

    @Override // android.graphics.Canvas
    public void drawOval(RectF r2, Paint r3) {
        record(new DrawOvalCmd(r2, r3));
        super.drawOval(r2, r3);
    }

    @Override // android.graphics.Canvas
    public void drawPaint(Paint r2) {
        record(new DrawPaintCmd(r2));
        super.drawPaint(r2);
    }

    @Override // android.graphics.Canvas
    public void drawPatch(NinePatch r2, Rect r3, Paint r4) {
        record(new DrawPatchCmd(r2, r3, r4));
        super.drawPatch(r2, r3, r4);
    }

    @Override // android.graphics.Canvas
    public void drawPatch(NinePatch r2, RectF r3, Paint r4) {
        record(new DrawPatchCmd(r2, r3, r4));
        super.drawPatch(r2, r3, r4);
    }

    @Override // android.graphics.Canvas
    public void drawPath(Path r2, Paint r3) {
        record(new DrawPathCmd(r2, r3));
        super.drawPath(r2, r3);
    }

    @Override // android.graphics.Canvas
    public void drawPicture(Picture r2) {
        record(new DrawPictureCmd(r2));
        super.drawPicture(r2);
    }

    @Override // android.graphics.Canvas
    public void drawPicture(Picture r2, Rect r3) {
        record(new DrawPictureCmd(r2, r3));
        super.drawPicture(r2, r3);
    }

    @Override // android.graphics.Canvas
    public void drawPicture(Picture r2, RectF r3) {
        record(new DrawPictureCmd(r2, r3));
        super.drawPicture(r2, r3);
    }

    @Override // android.graphics.Canvas
    public void drawPoint(float r2, float r3, Paint r4) {
        record(new DrawPointCmd(r2, r3, r4));
        super.drawPoint(r2, r3, r4);
    }

    @Override // android.graphics.Canvas
    public void drawPoints(float[] r2, int r3, int r4, Paint r5) {
        record(new DrawPointsCmd(r2, r3, r4, r5));
        super.drawPoints(r2, r3, r4, r5);
    }

    @Override // android.graphics.Canvas
    public void drawPoints(float[] r2, Paint r3) {
        record(new DrawPointsCmd(r2, r3));
        super.drawPoints(r2, r3);
    }

    @Override // android.graphics.Canvas
    public void drawRect(float r7, float r8, float r9, float r10, Paint r11) {
        record(new DrawRectCmd(r7, r8, r9, r10, r11));
        super.drawRect(r7, r8, r9, r10, r11);
    }

    @Override // android.graphics.Canvas
    public void drawRect(Rect r1, Paint r2) {
        super.drawRect(r1, r2);
    }

    @Override // android.graphics.Canvas
    public void drawRect(RectF r2, Paint r3) {
        record(new DrawRectCmd(r2, r3));
        super.drawRect(r2, r3);
    }

    @Override // android.graphics.Canvas
    public void drawRoundRect(float r3, float r4, float r5, float r6, float r7, float r8, Paint r9) {
        record(new DrawRoundRectCmd(new RectF(r3, r4, r5, r6), r7, r8, r9));
        super.drawRoundRect(r3, r4, r5, r6, r7, r8, r9);
    }

    @Override // android.graphics.Canvas
    public void drawRoundRect(RectF r1, float r2, float r3, Paint r4) {
        super.drawRoundRect(r1, r2, r3, r4);
    }

    @Override // android.graphics.Canvas
    public void drawText(CharSequence r8, int r9, int r10, float r11, float r12, Paint r13) {
        record(new DrawTextCmd(r8, r9, r10, r11, r12, r13));
        super.drawText(r8, r9, r10, r11, r12, r13);
    }

    @Override // android.graphics.Canvas
    public void drawText(String r2, float r3, float r4, Paint r5) {
        record(new DrawTextCmd(r2, r3, r4, r5));
        super.drawText(r2, r3, r4, r5);
    }

    @Override // android.graphics.Canvas
    public void drawText(String r8, int r9, int r10, float r11, float r12, Paint r13) {
        record(new DrawTextCmd(r8, r9, r10, r11, r12, r13));
        super.drawText(r8, r9, r10, r11, r12, r13);
    }

    @Override // android.graphics.Canvas
    public void drawText(char[] r8, int r9, int r10, float r11, float r12, Paint r13) {
        record(new DrawTextCmd(r8, r9, r10, r11, r12, r13));
        super.drawText(r8, r9, r10, r11, r12, r13);
    }

    @Override // android.graphics.Canvas
    public void drawTextOnPath(String r7, Path r8, float r9, float r10, Paint r11) {
        record(new DrawTextOnPathCmd(r7, r8, r9, r10, r11));
        super.drawTextOnPath(r7, r8, r9, r10, r11);
    }

    @Override // android.graphics.Canvas
    public void drawTextOnPath(char[] r9, int r10, int r11, Path r12, float r13, float r14, Paint r15) {
        record(new DrawTextOnPathCmd(r9, r10, r11, r12, r13, r14, r15));
        super.drawTextOnPath(r9, r10, r11, r12, r13, r14, r15);
    }

    @Override // android.graphics.Canvas
    public void drawTextRun(MeasuredText r11, int r12, int r13, int r14, int r15, float r16, float r17, boolean r18, Paint r19) {
        record(new DrawTextRunMeasuredCmd(r11, r12, r13, r14, r15, r16, r17, r18, r19));
        super.drawTextRun(r11, r12, r13, r14, r15, r16, r17, r18, r19);
    }

    @Override // android.graphics.Canvas
    public void drawTextRun(CharSequence r11, int r12, int r13, int r14, int r15, float r16, float r17, boolean r18, Paint r19) {
        record(new DrawTextRunCmd(r11, r12, r13, r14, r15, r16, r17, r18, r19));
        super.drawTextRun(r11, r12, r13, r14, r15, r16, r17, r18, r19);
    }

    @Override // android.graphics.Canvas
    public void drawTextRun(char[] r11, int r12, int r13, int r14, int r15, float r16, float r17, boolean r18, Paint r19) {
        record(new DrawTextRunCmd(r11, r12, r13, r14, r15, r16, r17, r18, r19));
        super.drawTextRun(r11, r12, r13, r14, r15, r16, r17, r18, r19);
    }

    @Override // android.graphics.Canvas
    public void drawVertices(Canvas.VertexMode r14, int r15, float[] r16, int r17, float[] r18, int r19, int[] r20, int r21, short[] r22, int r23, int r24, Paint r25) {
        record(new DrawVerticesCmd(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25));
        super.drawVertices(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25);
    }

    @Override // android.graphics.Canvas
    public boolean getClipBounds(Rect r3) {
        boolean r0 = super.getClipBounds(r3);
        record(new GetClipBoundsCmd(r3, r0));
        return r0;
    }

    public int getCommandCount() {
        return this.mCommands.size();
    }

    public void logCommands() {
        Log.i("DebugRecordingCanvas", "=== DebugRecordingCanvas: " + this.mCommands.size() + " command(s) ===");
        int r0 = 0;
    L4:
        if (r0 >= this.mCommands.size()) goto L10;
        String r2 = "[" + r0 + "] " + this.mCommands.get(r0);
        if (r2.contains("⚠") == false) goto L8;
        Log.w("DebugRecordingCanvas", r2);
    L9:
        r0 = r0 + 1;
        goto L4
    L8:
        Log.i("DebugRecordingCanvas", r2);
        goto L9
    L10:
        Log.i("DebugRecordingCanvas", "=== end ===");
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(float r7, float r8, float r9, float r10) {
        boolean r5 = super.quickReject(r7, r8, r9, r10);
        record(new QuickRejectCmd(r7, r8, r9, r10, r5));
        return r5;
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(float r8, float r9, float r10, float r11, Canvas.EdgeType r12) {
        boolean r6 = super.quickReject(r8, r9, r10, r11, r12);
        record(new QuickRejectCmd(r8, r9, r10, r11, r12, r6));
        return r6;
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(Path r3) {
        boolean r0 = super.quickReject(r3);
        record(new QuickRejectCmd(r3, r0));
        return r0;
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(Path r3, Canvas.EdgeType r4) {
        boolean r0 = super.quickReject(r3, r4);
        record(new QuickRejectCmd(r3, r4, r0));
        return r0;
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(RectF r3) {
        boolean r0 = super.quickReject(r3);
        record(new QuickRejectCmd(r3, r0));
        return r0;
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(RectF r3, Canvas.EdgeType r4) {
        boolean r0 = super.quickReject(r3, r4);
        record(new QuickRejectCmd(r3, r4, r0));
        return r0;
    }

    public void replayAll(Canvas r2) {
        replayCommands(r2, this.mCommands.size());
    }

    public void replayCommands(Canvas r4, int r5) {
        int r52 = Math.min(r5, this.mCommands.size());
        int r0 = r4.getSaveCount();
        int r1 = 0;
    L3:
        if (r1 >= r52) goto L6;
        this.mCommands.get(r1).replay(r4);
        r1 = r1 + 1;
        goto L3
    L6:
        if ((r4.getSaveCount() - r0) <= 0) goto L10;
        r4.restoreToCount(r0);
        return;
    }

    @Override // android.graphics.Canvas
    public void restore() {
        record(new RestoreCmd());
        super.restore();
    }

    @Override // android.graphics.Canvas
    public void restoreToCount(int r2) {
        record(new RestoreToCountCmd(r2));
        super.restoreToCount(r2);
    }

    @Override // android.graphics.Canvas
    public void rotate(float r2) {
        record(new RotateCmd(r2));
        super.rotate(r2);
    }

    @Override // android.graphics.Canvas
    public int save() {
        record(new SaveCmd());
        return super.save();
    }

    @Override // android.graphics.Canvas
    public int saveLayer(float r1, float r2, float r3, float r4, Paint r5) {
        return super.saveLayer(r1, r2, r3, r4, r5);
    }

    @Override // android.graphics.Canvas
    public int saveLayer(float r3, float r4, float r5, float r6, Paint r7, int r8) {
        record(new SaveLayerCmd(new RectF(r3, r4, r5, r6), r7));
        return super.saveLayer(r3, r4, r5, r6, r7, r8);
    }

    @Override // android.graphics.Canvas
    public int saveLayer(RectF r1, Paint r2) {
        return super.saveLayer(r1, r2);
    }

    @Override // android.graphics.Canvas
    public int saveLayer(RectF r1, Paint r2, int r3) {
        return super.saveLayer(r1, r2, r3);
    }

    @Override // android.graphics.Canvas
    public int saveLayerAlpha(float r1, float r2, float r3, float r4, int r5) {
        return super.saveLayerAlpha(r1, r2, r3, r4, r5);
    }

    @Override // android.graphics.Canvas
    public int saveLayerAlpha(float r3, float r4, float r5, float r6, int r7, int r8) {
        record(new SaveLayerAlphaCmd(new RectF(r3, r4, r5, r6), r7));
        return super.saveLayerAlpha(r3, r4, r5, r6, r7, r8);
    }

    @Override // android.graphics.Canvas
    public int saveLayerAlpha(RectF r1, int r2) {
        return super.saveLayerAlpha(r1, r2);
    }

    @Override // android.graphics.Canvas
    public int saveLayerAlpha(RectF r1, int r2, int r3) {
        return super.saveLayerAlpha(r1, r2, r3);
    }

    @Override // android.graphics.Canvas
    public void scale(float r2, float r3) {
        record(new ScaleCmd(r2, r3));
        super.scale(r2, r3);
    }

    @Override // android.graphics.Canvas
    public void setDrawFilter(DrawFilter r2) {
        record(new SetDrawFilterCmd(r2));
        super.setDrawFilter(r2);
    }

    @Override // android.graphics.Canvas
    public void setMatrix(Matrix r2) {
        record(new SetMatrixCmd(r2));
        super.setMatrix(r2);
    }

    @Override // android.graphics.Canvas
    public void skew(float r2, float r3) {
        record(new SkewCmd(r2, r3));
        super.skew(r2, r3);
    }

    @Override // android.graphics.Canvas
    public void translate(float r2, float r3) {
        record(new TranslateCmd(r2, r3));
        super.translate(r2, r3);
    }
}
