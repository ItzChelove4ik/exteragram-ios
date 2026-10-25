package org.telegram.ui.Components.Paint;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import com.google.zxing.common.detector.MathUtils;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Components.Paint.Brush;
import org.telegram.ui.Components.Size;

/* loaded from: classes7.dex */
public class ShapeInput {
    private ArrayList<Point> allPoints;
    private Point center;
    private Paint centerPointPaint;
    private Paint centerPointStrokePaint;
    private Paint controlPointPaint;
    private Paint controlPointStrokePaint;
    private Runnable invalidate;
    private Matrix invertMatrix;
    private Paint linePaint;
    private Point movingPoint;
    private ArrayList<Point> movingPoints;
    private RenderView renderView;
    private Shape shape;
    private float[] tempPoint;
    private float touchOffsetX;
    private float touchOffsetY;

    /* renamed from: org.telegram.ui.Components.Paint.ShapeInput$1, reason: invalid class name */
    public class AnonymousClass1 extends Point {
        final /* synthetic */ ShapeInput this$0;

        public AnonymousClass1(ShapeInput r1) {
            this.this$0 = r1;
            super(r1);
        }

        @Override // org.telegram.ui.Components.Paint.ShapeInput.Point
        public void set() {
            double r0 = Math.atan2(ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY - ShapeInput.m11411$$Nest$fgetshape(this.this$0).middleY, ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerX - ShapeInput.m11411$$Nest$fgetshape(this.this$0).middleX) + 3.141592653589793d;
            double r2 = ShapeInput.m11411$$Nest$fgetshape(this.this$0).arrowTriangleLength / 5.5f;
            set(ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerX + ((float) (Math.cos(r0) * r2)), ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY + ((float) (Math.sin(r0) * r2)));
        }

        @Override // org.telegram.ui.Components.Paint.ShapeInput.Point
        public void update(float r12, float r13) {
            double r9 = Math.atan2(ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY - ShapeInput.m11411$$Nest$fgetshape(this.this$0).middleY, ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerX - ShapeInput.m11411$$Nest$fgetshape(this.this$0).middleX) + 1.5707963267948966d;
            float r0 = (MathUtils.distance(ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerX, ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY, ShapeInput.m11411$$Nest$fgetshape(this.this$0).middleX, ShapeInput.m11411$$Nest$fgetshape(this.this$0).middleY) * 5.5f) / 2.0f;
            Shape r2 = ShapeInput.m11411$$Nest$fgetshape(this.this$0);
            ShapeInput r4 = this.this$0;
            r2.arrowTriangleLength = Math.min(r0, Math.max(100.0f, (-ShapeInput.m11413$$Nest$mdistToLine(r4, r12, r13, ShapeInput.m11411$$Nest$fgetshape(r4).centerX, ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY, r9)) * 5.5f));
            set();
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.ShapeInput$2, reason: invalid class name */
    public class AnonymousClass2 extends Point {
        final /* synthetic */ ShapeInput this$0;
        final /* synthetic */ Point val$triangleLengthControl;

        public AnonymousClass2(ShapeInput r1, Point r2) {
            this.this$0 = r1;
            this.val$triangleLengthControl = r2;
            super(r1);
        }

        @Override // org.telegram.ui.Components.Paint.ShapeInput.Point
        public void set() {
            set(ShapeInput.m11411$$Nest$fgetshape(this.this$0).middleX, ShapeInput.m11411$$Nest$fgetshape(this.this$0).middleY);
        }

        @Override // org.telegram.ui.Components.Paint.ShapeInput.Point
        public void update(float r2, float r3) {
            ShapeInput.m11411$$Nest$fgetshape(this.this$0).middleX = r2;
            ShapeInput.m11411$$Nest$fgetshape(this.this$0).middleY = r3;
            super.update(r2, r3);
            this.val$triangleLengthControl.set();
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.ShapeInput$3, reason: invalid class name */
    public class AnonymousClass3 extends Point {
        final /* synthetic */ ShapeInput this$0;
        final /* synthetic */ Point val$triangleLengthControl;

        public AnonymousClass3(ShapeInput r1, Point r2) {
            this.this$0 = r1;
            this.val$triangleLengthControl = r2;
            super(r1);
        }

        @Override // org.telegram.ui.Components.Paint.ShapeInput.Point
        public void set() {
            set(ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusX, ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusY);
        }

        @Override // org.telegram.ui.Components.Paint.ShapeInput.Point
        public void update(float r2, float r3) {
            ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusX = r2;
            ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusY = r3;
            super.update(r2, r3);
            this.val$triangleLengthControl.set();
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.ShapeInput$4, reason: invalid class name */
    public class AnonymousClass4 extends Point {
        final /* synthetic */ ShapeInput this$0;

        public AnonymousClass4(ShapeInput r1) {
            this.this$0 = r1;
            super(r1);
        }

        @Override // org.telegram.ui.Components.Paint.ShapeInput.Point
        public void set() {
            set(ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerX + ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusX, ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY);
        }

        @Override // org.telegram.ui.Components.Paint.ShapeInput.Point
        public void update(float r4, float r5) {
            super.update(r4, r5);
            Shape r0 = ShapeInput.m11411$$Nest$fgetshape(this.this$0);
            Shape r1 = ShapeInput.m11411$$Nest$fgetshape(this.this$0);
            float r3 = MathUtils.distance(ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerX, ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY, r4, r5);
            r1.radiusY = r3;
            r0.radiusX = r3;
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.ShapeInput$5, reason: invalid class name */
    public class AnonymousClass5 extends Point {
        final int n;
        final /* synthetic */ ShapeInput this$0;

        public AnonymousClass5(ShapeInput r1) {
            this.this$0 = r1;
            super(r1);
            this.n = 5;
        }

        @Override // org.telegram.ui.Components.Paint.ShapeInput.Point
        public void set() {
            float r0 = Math.min(ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusX, ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusY);
            set(ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerX + (((float) Math.cos(-0.3141592653589793d)) * r0), ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY + (((float) Math.sin(-0.3141592653589793d)) * r0));
        }

        @Override // org.telegram.ui.Components.Paint.ShapeInput.Point
        public void update(float r6, float r7) {
            Shape r0 = ShapeInput.m11411$$Nest$fgetshape(this.this$0);
            Shape r1 = ShapeInput.m11411$$Nest$fgetshape(this.this$0);
            float r2 = MathUtils.distance(ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerX, ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY, r6, r7);
            r1.radiusY = r2;
            r0.radiusX = r2;
            ShapeInput.m11411$$Nest$fgetshape(this.this$0).rotation = (float) (r0.rotation + (((float) Math.atan2(ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY - r7, r6 - ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerX)) - 0.3141592653589793d));
            set();
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.ShapeInput$6, reason: invalid class name */
    public class AnonymousClass6 extends Point {
        final /* synthetic */ ShapeInput this$0;

        public AnonymousClass6(ShapeInput r1, boolean r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.Paint.ShapeInput.Point
        public void set() {
            set(ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerX, ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY - Math.abs(ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusY));
        }

        @Override // org.telegram.ui.Components.Paint.ShapeInput.Point
        public void update(float r6, float r7) {
            ShapeInput.m11411$$Nest$fgetshape(this.this$0).rotation = (float) (r0.rotation + (((float) Math.atan2(ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY - r7, r6 - ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerX)) - 1.5707963267948966d));
            int r62 = 0;
        L4:
            if (r62 >= ShapeInput.m11410$$Nest$fgetallPoints(this.this$0).size()) goto L9;
            Point r72 = (Point) ShapeInput.m11410$$Nest$fgetallPoints(this.this$0).get(r62);
            if ((r72 instanceof CornerPoint) == false) goto L8;
            r72.set();
        L8:
            r62 = r62 + 1;
            goto L4
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.ShapeInput$7, reason: invalid class name */
    public class AnonymousClass7 extends Point {
        final /* synthetic */ ShapeInput this$0;

        public AnonymousClass7(ShapeInput r1) {
            this.this$0 = r1;
            super(r1);
        }

        private void limit() {
            if (this.y <= (ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY - ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusY)) goto L17;
            if (this.y >= (ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY + ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusY)) goto L17;
            if (this.x > ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerX) goto L12;
            if (this.x <= (ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerX - ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusX)) goto L12;
            this.x = ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerX - ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusX;
        L12:
            if (this.x <= ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY) goto L17;
            if (this.x >= (ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerX + ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusX)) goto L17;
            this.x = ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerX + ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusX;
        L17:
            if (this.x > (ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerX - ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusX)) goto L19;
            return;
        L19:
            if (this.x < (ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerX + ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusX)) goto L21;
            return;
        L21:
            if (this.y > ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY) goto L27;
            if (this.y <= (ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY - ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusY)) goto L27;
            this.y = ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY - ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusY;
            return;
        L27:
            if (this.y > ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY) goto L29;
            return;
        L29:
            if (this.y >= (ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY + ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusY)) goto L35;
            this.y = ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY + ShapeInput.m11411$$Nest$fgetshape(this.this$0).radiusY;
            return;
        }

        @Override // org.telegram.ui.Components.Paint.ShapeInput.Point
        public void set() {
            set(ShapeInput.m11411$$Nest$fgetshape(this.this$0).middleX, ShapeInput.m11411$$Nest$fgetshape(this.this$0).middleY);
        }

        @Override // org.telegram.ui.Components.Paint.ShapeInput.Point
        public void set(float r2, float r3) {
            ShapeInput.m11411$$Nest$fgetshape(this.this$0).middleX = r2;
            ShapeInput.m11411$$Nest$fgetshape(this.this$0).middleY = r3;
            super.set(r2, r3);
        }

        @Override // org.telegram.ui.Components.Paint.ShapeInput.Point
        public void update(float r1, float r2) {
            set(r1, r2);
            limit();
            Shape r12 = ShapeInput.m11411$$Nest$fgetshape(this.this$0);
            r12.middleX = this.x;
            Shape r13 = ShapeInput.m11411$$Nest$fgetshape(this.this$0);
            r13.middleY = this.y;
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.ShapeInput$8, reason: invalid class name */
    public class AnonymousClass8 extends Point {
        final /* synthetic */ ShapeInput this$0;

        public AnonymousClass8(ShapeInput r1, boolean r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.Paint.ShapeInput.Point
        public void set() {
            this.x = ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerX;
            this.y = ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY;
        }

        @Override // org.telegram.ui.Components.Paint.ShapeInput.Point
        public void update(float r4, float r5) {
            int r0 = 0;
        L3:
            int r1 = ShapeInput.m11410$$Nest$fgetallPoints(this.this$0).size();
            ShapeInput r2 = this.this$0;
            if (r0 >= r1) goto L9;
            Point r12 = (Point) ShapeInput.m11410$$Nest$fgetallPoints(r2).get(r0);
            if (r12 == this) goto L8;
            r12.set();
        L8:
            r0 = r0 + 1;
            goto L3
        L9:
            ShapeInput.m11411$$Nest$fgetshape(r2).centerX = r4;
            ShapeInput.m11411$$Nest$fgetshape(this.this$0).centerY = r5;
            super.update(r4, r5);
        }
    }

    public class CornerPoint extends Point {
        public float rx;
        public float ry;
        public Shape shape;
        final /* synthetic */ ShapeInput this$0;

        public CornerPoint(ShapeInput r1, Shape r2, boolean r3, boolean r4) {
            this.this$0 = r1;
            super(r1);
            this.rotate = false;
            this.shape = r2;
            float r12 = 1.0f;
            if (r3 == false) goto L5;
            float r32 = -1.0f;
        L6:
            this.rx = r32;
            if (r4 == false) goto L9;
            r12 = -1.0f;
        L9:
            this.ry = r12;
            set();
            return;
        L5:
            r32 = 1.0f;
            goto L6
        }

        @Override // org.telegram.ui.Components.Paint.ShapeInput.Point
        public void set() {
            Shape r0 = this.shape;
            if (r0 == null) goto L6;
            float r1 = r0.centerX + (this.rx * r0.radiusX);
            float r2 = r0.centerY + (this.ry * r0.radiusY);
            ShapeInput.m11414$$Nest$mrotate(this.this$0, r1, r2, true);
            set(ShapeInput.m11412$$Nest$fgettempPoint(this.this$0)[0], ShapeInput.m11412$$Nest$fgettempPoint(this.this$0)[1]);
            return;
        }

        @Override // org.telegram.ui.Components.Paint.ShapeInput.Point
        public void update(float r11, float r12) {
            super.update(r11, r12);
            Shape r0 = this.shape;
            float r1 = r0.centerX + ((-this.rx) * r0.radiusX);
            float r2 = r0.centerY + ((-this.ry) * r0.radiusY);
            int r3 = 0;
            ShapeInput.m11414$$Nest$mrotate(this.this$0, r11, r12, false);
            ShapeInput.m11414$$Nest$mrotate(this.this$0, r1, r2, true);
            float r02 = ShapeInput.m11412$$Nest$fgettempPoint(this.this$0)[0];
            float r13 = ShapeInput.m11412$$Nest$fgettempPoint(this.this$0)[1];
            double r6 = (3.141592653589793d - Math.atan2(r12 - r13, r11 - r02)) - this.shape.rotation;
            double r4 = Math.cos(r6) * MathUtils.distance(r11, r12, r02, r13);
            double r62 = Math.sin(r6) * MathUtils.distance(r11, r12, r02, r13);
            this.shape.radiusX = ((float) Math.abs(r4)) / 2.0f;
            this.shape.radiusY = ((float) Math.abs(r62)) / 2.0f;
            Shape r22 = this.shape;
            r22.centerX = (r11 + r02) / 2.0f;
            r22.centerY = (r12 + r13) / 2.0f;
        L4:
            if (r3 >= ShapeInput.m11410$$Nest$fgetallPoints(this.this$0).size()) goto L6;
            ((Point) ShapeInput.m11410$$Nest$fgetallPoints(this.this$0).get(r3)).set();
            r3 = r3 + 1;
            goto L4
        }
    }

    public class Point {
        boolean draw;
        boolean green;
        boolean rotate;
        final /* synthetic */ ShapeInput this$0;
        float x;
        float y;

        public Point(ShapeInput r1) {
            this.this$0 = r1;
            this.rotate = true;
            this.draw = true;
            set();
        }

        public Point(ShapeInput r1, boolean r2) {
            this.this$0 = r1;
            this.rotate = true;
            this.draw = true;
            this.green = r2;
            set();
        }

        public abstract void set();

        public void set(float r1, float r2) {
            this.x = r1;
            this.y = r2;
        }

        public void update(float r1, float r2) {
            this.x = r1;
            this.y = r2;
        }
    }

    /* renamed from: -$$Nest$fgetallPoints, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m11410$$Nest$fgetallPoints(ShapeInput r0) {
        return r0.allPoints;
    }

    /* renamed from: -$$Nest$fgetshape, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Shape m11411$$Nest$fgetshape(ShapeInput r0) {
        return r0.shape;
    }

    /* renamed from: -$$Nest$fgettempPoint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m11412$$Nest$fgettempPoint(ShapeInput r0) {
        return r0.tempPoint;
    }

    /* renamed from: -$$Nest$mdistToLine, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11413$$Nest$mdistToLine(ShapeInput r0, float r1, float r2, float r3, float r4, double r5) {
        return r0.distToLine(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$mrotate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11414$$Nest$mrotate(ShapeInput r0, float r1, float r2, boolean r3) {
        r0.rotate(r1, r2, r3);
    }

    public ShapeInput(RenderView r6, Runnable r7) {
        this.linePaint = new Paint(1);
        this.centerPointPaint = new Paint(1);
        this.centerPointStrokePaint = new Paint(1);
        this.controlPointPaint = new Paint(1);
        this.controlPointStrokePaint = new Paint(1);
        this.allPoints = new ArrayList();
        this.movingPoints = new ArrayList();
        this.tempPoint = new float[2];
        this.renderView = r6;
        this.invalidate = r7;
        this.centerPointPaint.setColor(-13840296);
        Paint r62 = this.centerPointStrokePaint;
        Paint.Style r72 = Paint.Style.STROKE;
        r62.setStyle(r72);
        this.centerPointStrokePaint.setColor(-1);
        this.centerPointStrokePaint.setStrokeWidth(AndroidUtilities.dp(1.0f));
        this.controlPointPaint.setColor(-16745729);
        this.controlPointStrokePaint.setStyle(r72);
        this.controlPointStrokePaint.setColor(-1);
        this.controlPointStrokePaint.setStrokeWidth(AndroidUtilities.dp(1.0f));
        this.linePaint.setStyle(r72);
        this.linePaint.setColor(-1);
        this.linePaint.setStrokeWidth(AndroidUtilities.dp(0.8f));
        this.linePaint.setPathEffect(new DashPathEffect(new float[]{AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f)}, 0.0f));
        this.linePaint.setShadowLayer(4.0f, 0.0f, 1.5f, TLObject.FLAG_30);
    }

    private float distToLine(float r5, float r6, float r7, float r8, double r9) {
        return (float) ((Math.cos(r9) * (r8 - r6)) - (Math.sin(r9) * (r7 - r5)));
    }

    private float distToLine(float r3, float r4, float r5, float r6, float r7, float r8) {
        float r72 = r7 - r5;
        float r82 = r8 - r6;
        float r2 = Math.max(Math.min((((r3 - r5) * r72) + ((r4 - r6) * r82)) / ((r72 * r72) + (r82 * r82)), 1.0f), 0.0f);
        float r52 = (r5 + (r72 * r2)) - r3;
        float r62 = (r6 + (r2 * r82)) - r4;
        return (float) Math.sqrt((r52 * r52) + (r62 * r62));
    }

    private void drawPoint(Canvas r6, Size r7, Point r8) {
        float r0 = (r8.x / r7.width) * r6.getWidth();
        float r1 = (r8.y / r7.height) * r6.getHeight();
        float r3 = AndroidUtilities.dp(5.0f);
        if (r8.green == false) goto L5;
        Paint r4 = this.centerPointPaint;
    L6:
        r6.drawCircle(r0, r1, r3, r4);
        float r02 = (r8.x / r7.width) * r6.getWidth();
        float r12 = (r8.y / r7.height) * r6.getHeight();
        float r72 = AndroidUtilities.dp(5.0f);
        if (r8.green == false) goto L9;
        Paint r5 = this.centerPointStrokePaint;
    L10:
        r6.drawCircle(r02, r12, r72, r5);
        return;
    L9:
        r5 = this.controlPointStrokePaint;
        goto L10
    L5:
        r4 = this.controlPointPaint;
        goto L6
    }

    private boolean isInsideShape(float r21, float r22) {
        Shape r1 = this.shape;
        if (r1 != null) goto L6;
        return false;
    L6:
        if (r1.getType() == 0) goto L51;
        if (this.shape.getType() == 2) goto L51;
        if (this.shape.getType() != 1) goto L13;
    L21:
        Shape r12 = this.shape;
        float r5 = r12.centerX;
        float r6 = r12.radiusX;
        float r122 = r12.thickness;
        float r11 = (r5 - r6) - (r122 / 2.0f);
        float r13 = r12.centerY;
        float r14 = r12.radiusY;
        float r142 = (r13 - r14) - (r122 / 2.0f);
        float r52 = (r5 + r6) + (r122 / 2.0f);
        float r132 = (r13 + r14) + (r122 / 2.0f);
        float r62 = 0.0f;
        if (r22 <= r142) goto L34;
        if (r22 >= r132) goto L34;
        if (r21 >= r11) goto L31;
        r62 = r11 - r21;
    L28:
        boolean r19 = false;
        float r143 = 30.0f;
    L29:
        float r7 = r62;
        if (this.shape.getType() != 3) goto L48;
        Shape r15 = this.shape;
        r7 = Math.min(r7, distToLine(r21, r22, r15.centerX, r15.centerY, r15.middleX, r15.middleY));
    L48:
        if (r7 >= AndroidUtilities.dp(r143)) goto L50;
        return true;
    L50:
        return r19;
    L31:
        if (r21 <= r52) goto L28;
        r62 = r21 - r52;
    L34:
        if (r21 < r11) goto L36;
    L43:
        double r10 = r21 - r11;
        float r16 = r22 - r142;
        r143 = 30.0f;
        double r72 = r16;
        r19 = false;
        double r53 = r21 - r52;
        double r73 = Math.min(Math.pow(r10, 2.0d) + Math.pow(r72, 2.0d), Math.pow(r72, 2.0d) + Math.pow(r53, 2.0d));
        double r123 = r22 - r132;
        r62 = (float) Math.sqrt(Math.min(r73, Math.min(Math.pow(r10, 2.0d) + Math.pow(r123, 2.0d), Math.pow(r53, 2.0d) + Math.pow(r123, 2.0d))));
        goto L29
    L36:
        if (r21 <= r52) goto L43;
        if (r22 >= r142) goto L41;
        r62 = r142 - r22;
        goto L28
    L41:
        if (r22 <= r132) goto L28;
        r62 = r22 - r132;
        goto L28
    L13:
        if (this.shape.getType() == 3) goto L21;
        if (this.shape.getType() != 4) goto L20;
        Size r8 = this.renderView.getPainting().getSize();
        Shape r17 = this.shape;
        float r112 = distToLine(r21, r22, r17.centerX, r17.centerY, r17.middleX, r17.middleY);
        Shape r18 = this.shape;
        if ((Math.min(r112, distToLine(r21, r22, r18.radiusX, r18.radiusY, r18.middleX, r18.middleY)) - (this.shape.thickness / 2.0f)) >= (Math.min(r8.width, r8.height) * 0.1f)) goto L20;
        return true;
    L20:
        return false;
    L51:
        double r110 = Math.sqrt(Math.pow(r21 - this.shape.centerX, 2.0d) + Math.pow(r22 - this.shape.centerY, 2.0d));
        Shape r3 = this.shape;
        if (((r110 - Math.min(r3.radiusX, r3.radiusY)) - (this.shape.thickness / 2.0f)) >= AndroidUtilities.dp(30.0f)) goto L54;
        return true;
    L54:
        return false;
    }

    private void rotate(float r3, float r4, boolean r5) {
        float[] r0 = this.tempPoint;
        r0[0] = r3;
        r0[1] = r4;
        rotate(r5);
    }

    private void rotate(boolean r13) {
        Shape r0 = this.shape;
        if (r0 == null) goto L12;
        float r1 = r0.rotation;
        if (r1 == 0.0f) goto L13;
        float[] r2 = this.tempPoint;
        float r4 = r2[0] - r0.centerX;
        r2[0] = r4;
        r2[1] = r2[1] - r0.centerY;
        if (r13 == false) goto L9;
        int r132 = -1;
    L10:
        double r02 = r1 * r132;
        float r133 = (float) ((r4 * Math.cos(r02)) - (this.tempPoint[1] * Math.sin(r02)));
        float r03 = (float) ((this.tempPoint[0] * Math.sin(r02)) + (this.tempPoint[1] * Math.cos(r02)));
        float[] r12 = this.tempPoint;
        Shape r122 = this.shape;
        r12[0] = r133 + r122.centerX;
        r12[1] = r03 + r122.centerY;
        return;
    L9:
        r132 = 1;
        goto L10
    L13:
        return;
    }

    public void clear() {
        RenderView r0 = this.renderView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getPainting() != null) goto L7;
        return;
    L7:
        if (this.shape == null) goto L13;
        this.renderView.getPainting().clearShape();
        this.allPoints.clear();
        this.movingPoints.clear();
        this.shape = null;
        return;
    }

    public void dispatchDraw(Canvas r11) {
        RenderView r2 = this.renderView;
        if (r2 != null) goto L5;
        return;
    L5:
        if (r2.getPainting() == null) goto L52;
        Size r7 = this.renderView.getPainting().getSize();
        int r8 = 0;
        int r22 = 0;
    L9:
        if (r22 >= this.allPoints.size()) goto L16;
        Point r3 = this.allPoints.get(r22);
        if (r3.draw == false) goto L15;
        if (r3.rotate == true) goto L15;
        drawPoint(r11, r7, r3);
    L15:
        r22 = r22 + 1;
        goto L9
    L16:
        Shape r23 = this.shape;
        if (r23 != null) goto L19;
    L21:
        Shape r24 = this.shape;
        if (r24 == null) goto L27;
        if (r24.getType() != 4) goto L27;
        r11.drawLine((this.shape.centerX / r7.width) * r11.getWidth(), (this.shape.centerY / r7.height) * r11.getHeight(), (this.shape.middleX / r7.width) * r11.getWidth(), (this.shape.middleY / r7.height) * r11.getHeight(), this.linePaint);
        r11.drawLine(r11.getWidth() * (this.shape.radiusX / r7.width), r11.getHeight() * (this.shape.radiusY / r7.height), r11.getWidth() * (this.shape.middleX / r7.width), r11.getHeight() * (this.shape.middleY / r7.height), this.linePaint);
    L27:
        if (r8 >= this.allPoints.size()) goto L34;
        Point r25 = this.allPoints.get(r8);
        if (r25.draw == false) goto L33;
        if (r25.rotate == false) goto L33;
        drawPoint(r11, r7, r25);
    L33:
        r8 = r8 + 1;
        goto L27
    L34:
        Shape r0 = this.shape;
        if (r0 != null) goto L37;
        return;
    L37:
        if (r0.rotation == 0.0f) goto L51;
        r11.restore();
        return;
    L51:
        return;
    L19:
        if (r23.rotation == 0.0f) goto L21;
        r11.save();
        r11.rotate((float) (((-r2.rotation) / 3.141592653589793d) * 180.0d), (this.shape.centerX / r7.width) * r11.getWidth(), (this.shape.centerY / r7.height) * r11.getHeight());
        goto L21
    }

    public void onColorChange() {
        if (this.shape == null) goto L6;
        this.renderView.getPainting().paintShape(this.shape, null);
        return;
    }

    public void onWeightChange() {
        Shape r0 = this.shape;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.thickness == this.renderView.getCurrentWeight()) goto L9;
        Shape r02 = this.shape;
        r02.thickness = this.renderView.getCurrentWeight();
        this.renderView.getPainting().paintShape(this.shape, null);
        return;
    }

    public void process(MotionEvent r12, float r13) {
        RenderView r132 = this.renderView;
        if (r132 != null) goto L5;
        return;
    L5:
        if (r132.getPainting() != null) goto L7;
        return;
    L7:
        if (this.shape == null) goto L82;
        int r133 = r12.getActionMasked();
        float r0 = r12.getX();
        float r1 = this.renderView.getHeight() - r12.getY();
        float[] r122 = this.tempPoint;
        int r2 = 0;
        r122[0] = r0;
        r122[1] = r1;
        this.invertMatrix.mapPoints(r122);
        float[] r123 = this.tempPoint;
        float r14 = r123[0];
        float r124 = r123[1];
        this.invalidate.run();
        Point r3 = null;
        if (r133 != 0) goto L49;
        double r4 = Double.MAX_VALUE;
        int r134 = 0;
    L13:
        if (r134 >= this.allPoints.size()) goto L27;
        Point r6 = this.allPoints.get(r134);
        if (r6.draw == false) goto L26;
        float[] r7 = this.tempPoint;
        r7[0] = r14;
        r7[1] = r124;
        if (r6.rotate == false) goto L20;
        rotate(r14, r124, false);
    L20:
        float r72 = r6.x;
        float r8 = r6.y;
        float[] r9 = this.tempPoint;
        double r73 = MathUtils.distance(r72, r8, r9[0], r9[1]);
        if (r73 >= AndroidUtilities.dp(40.0f)) goto L26;
        if (r3 != null) goto L24;
    L25:
        r3 = r6;
        r4 = r73;
        goto L26
    L24:
        if (r73 < r4) goto L25;
    L26:
        r134 = r134 + 1;
        goto L13
    L27:
        float[] r135 = this.tempPoint;
        r135[0] = r14;
        r135[1] = r124;
        rotate(r14, r124, false);
        if (r3 == null) goto L30;
    L34:
        float[] r136 = this.tempPoint;
        r136[0] = r14;
        r136[1] = r124;
        this.movingPoint = r3;
        if (r3 != null) goto L37;
        Point r137 = this.center;
        if (r137 != null) goto L44;
        return;
    L44:
        if (r137.rotate == false) goto L46;
        rotate(r14, r124, false);
    L46:
        Point r125 = this.center;
        float r138 = r125.x;
        float[] r15 = this.tempPoint;
        this.touchOffsetX = r138 - r15[0];
        this.touchOffsetY = r125.y - r15[1];
        return;
    L37:
        if (r3.rotate == false) goto L39;
        rotate(r14, r124, false);
    L39:
        Point r126 = this.movingPoint;
        float r139 = r126.x;
        float[] r16 = this.tempPoint;
        this.touchOffsetX = r139 - r16[0];
        this.touchOffsetY = r126.y - r16[1];
        return;
    L30:
        if (isInsideShape(r14, r124) == true) goto L34;
        stop();
        return;
    L49:
        if (r133 != 2) goto L67;
        Point r1310 = this.movingPoint;
        if (r1310 != null) goto L62;
        Point r1311 = this.center;
        if (r1311 != null) goto L55;
    L65:
        this.renderView.getPainting().paintShape(this.shape, null);
        this.invalidate.run();
        return;
    L55:
        if (r1311.rotate == false) goto L57;
        rotate(r14, r124, false);
    L57:
        float[] r127 = this.tempPoint;
        float r1312 = r127[0] + this.touchOffsetX;
        Point r17 = this.center;
        float r1313 = r1312 - r17.x;
        float r128 = (r127[1] + this.touchOffsetY) - r17.y;
    L59:
        if (r2 >= this.movingPoints.size()) goto L65;
        Point r02 = this.movingPoints.get(r2);
        r02.update(r02.x + r1313, r02.y + r128);
        r2 = r2 + 1;
        goto L59
    L62:
        if (r1310.rotate == false) goto L64;
        rotate(false);
    L64:
        Point r129 = this.movingPoint;
        float[] r1314 = this.tempPoint;
        r129.update(r1314[0] + this.touchOffsetX, r1314[1] + this.touchOffsetY);
        goto L65
    L67:
        if (r133 != 1) goto L69;
    L70:
        this.movingPoint = null;
        return;
    L69:
        if (r133 == 3) goto L70;
        return;
    }

    public void setMatrix(Matrix r2) {
        Matrix r0 = new Matrix();
        this.invertMatrix = r0;
        r2.invert(r0);
    }

    public void start(int r7) {
        RenderView r0 = this.renderView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getPainting() == null) goto L30;
        this.allPoints.clear();
        this.movingPoints.clear();
        this.shape = new Shape(Brush.Shape.make(r7));
        Size r72 = this.renderView.getPainting().getSize();
        Shape r02 = this.shape;
        float r1 = r72.width;
        r02.centerX = r1 / 2.0f;
        float r3 = r72.height;
        r02.centerY = r3 / 2.0f;
        float r12 = Math.min(r1, r3) / 5.0f;
        r02.radiusY = r12;
        r02.radiusX = r12;
        this.shape.thickness = this.renderView.getCurrentWeight();
        this.shape.rounding = AndroidUtilities.dp(32.0f);
        this.shape.fill = PersistColorPalette.getInstance(UserConfig.selectedAccount).getFillShapes();
        if (this.shape.getType() != 4) goto L11;
        Shape r03 = this.shape;
        float r32 = r72.width / 2.0f;
        r03.middleX = r32;
        r03.radiusX = r32;
        r03.centerX = r32;
        r03.middleX = r32 + 1.0f;
        float r73 = r72.height;
        float r5 = (r73 / 3.0f) * 1.0f;
        r03.centerY = r5;
        float r4 = r73 / 2.0f;
        r03.middleY = r4;
        r03.radiusY = (r73 / 3.0f) * 2.0f;
        r03.arrowTriangleLength = Math.abs(r5 - r4);
        ArrayList<Point> r74 = this.allPoints;
        AnonymousClass1 r04 = new AnonymousClass1(this);
        r74.add(r04);
        ArrayList<Point> r75 = this.allPoints;
        AnonymousClass2 r2 = new AnonymousClass2(this, r04);
        r75.add(r2);
        this.movingPoints.add(r2);
        ArrayList<Point> r76 = this.allPoints;
        AnonymousClass3 r22 = new AnonymousClass3(this, r04);
        r76.add(r22);
        this.movingPoints.add(r22);
    L11:
        if (this.shape.getType() != 0) goto L14;
        this.allPoints.add(new AnonymousClass4(this));
    L14:
        if (this.shape.getType() != 2) goto L17;
        this.allPoints.add(new AnonymousClass5(this));
    L17:
        if (this.shape.getType() != 1) goto L19;
    L20:
        this.allPoints.add(new CornerPoint(this, this.shape, false, false));
        this.allPoints.add(new CornerPoint(this, this.shape, true, false));
        this.allPoints.add(new CornerPoint(this, this.shape, false, true));
        this.allPoints.add(new CornerPoint(this, this.shape, true, true));
        this.allPoints.add(new AnonymousClass6(this, true));
    L22:
        if (this.shape.getType() != 3) goto L24;
        Shape r77 = this.shape;
        r77.middleX = r77.centerX + (r77.radiusX * 0.8f);
        r77.middleY = (r77.centerY + (r77.radiusY * 1.2f)) + r77.thickness;
        ArrayList<Point> r78 = this.allPoints;
        AnonymousClass7 r05 = new AnonymousClass7(this);
        r78.add(r05);
        r05.rotate = false;
        this.movingPoints.add(r05);
    L24:
        this.center = new AnonymousClass8(this, true);
        if (this.shape.getType() == 4) goto L27;
        this.center.draw = false;
    L27:
        Point r79 = this.center;
        r79.rotate = false;
        this.movingPoints.add(r79);
        this.allPoints.add(this.center);
        this.renderView.getPainting().paintShape(this.shape, null);
        return;
    L19:
        if (this.shape.getType() != 3) goto L22;
    }

    public void stop() {
        RenderView r0 = this.renderView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getPainting() == null) goto L12;
        Shape r02 = this.shape;
        if (r02 == null) goto L13;
        r02.thickness = this.renderView.getCurrentWeight();
        this.renderView.getPainting().commitShape(this.shape, this.renderView.getCurrentColor());
        this.allPoints.clear();
        this.movingPoints.clear();
        this.shape = null;
        this.renderView.resetBrush();
        return;
    L13:
        return;
    }
}
