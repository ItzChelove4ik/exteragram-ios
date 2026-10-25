package com.caverock.androidsvg;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.Base64;
import android.util.Log;
import c.f$$ExternalSyntheticBUOutline2;
import com.caverock.androidsvg.CSSParser;
import com.caverock.androidsvg.PreserveAspectRatio;
import com.caverock.androidsvg.SVG;
import com.google.android.gms.cast.MediaStatus;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Stack;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
class SVGAndroidRenderer {
    private static HashSet<String> supportedFeatures;
    private Canvas canvas;
    private SVG document;
    private float dpi;
    private Stack<Matrix> matrixStack;
    private Stack<SVG.SvgContainer> parentStack;
    private CSSParser.RuleMatchContext ruleMatchContext;
    private RendererState state;
    private Stack<RendererState> stateStack;

    /* renamed from: com.caverock.androidsvg.SVGAndroidRenderer$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment = null;
        static final /* synthetic */ int[] $SwitchMap$com$caverock$androidsvg$SVG$Style$LineCap = null;
        static final /* synthetic */ int[] $SwitchMap$com$caverock$androidsvg$SVG$Style$LineJoin = null;

        static {
            int[] r0 = new int[SVG.Style.LineJoin.values().length];
            $SwitchMap$com$caverock$androidsvg$SVG$Style$LineJoin = r0;
            r0[SVG.Style.LineJoin.Miter.ordinal()] = 1;     // Catch: NoSuchFieldError -> L22
        L54:
            $SwitchMap$com$caverock$androidsvg$SVG$Style$LineJoin[SVG.Style.LineJoin.Round.ordinal()] = 2;     // Catch: NoSuchFieldError -> L23
        L42:
            $SwitchMap$com$caverock$androidsvg$SVG$Style$LineJoin[SVG.Style.LineJoin.Bevel.ordinal()] = 3;     // Catch: NoSuchFieldError -> L24
        L8:
            int[] r3 = new int[SVG.Style.LineCap.values().length];
            $SwitchMap$com$caverock$androidsvg$SVG$Style$LineCap = r3;
            r3[SVG.Style.LineCap.Butt.ordinal()] = 1;     // Catch: NoSuchFieldError -> L25
        L62:
            $SwitchMap$com$caverock$androidsvg$SVG$Style$LineCap[SVG.Style.LineCap.Round.ordinal()] = 2;     // Catch: NoSuchFieldError -> L26
        L36:
            $SwitchMap$com$caverock$androidsvg$SVG$Style$LineCap[SVG.Style.LineCap.Square.ordinal()] = 3;     // Catch: NoSuchFieldError -> L27
        L12:
            int[] r32 = new int[PreserveAspectRatio.Alignment.values().length];
            $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment = r32;
            r32[PreserveAspectRatio.Alignment.xMidYMin.ordinal()] = 1;     // Catch: NoSuchFieldError -> L28
        L40:
            $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment[PreserveAspectRatio.Alignment.xMidYMid.ordinal()] = 2;     // Catch: NoSuchFieldError -> L29
        L46:
            $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment[PreserveAspectRatio.Alignment.xMidYMax.ordinal()] = 3;     // Catch: NoSuchFieldError -> L30
        L52:
            $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment[PreserveAspectRatio.Alignment.xMaxYMin.ordinal()] = 4;     // Catch: NoSuchFieldError -> L31
        L38:
            $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment[PreserveAspectRatio.Alignment.xMaxYMid.ordinal()] = 5;     // Catch: NoSuchFieldError -> L32
        L50:
            $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment[PreserveAspectRatio.Alignment.xMaxYMax.ordinal()] = 6;     // Catch: NoSuchFieldError -> L33
        L56:
            $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment[PreserveAspectRatio.Alignment.xMinYMid.ordinal()] = 7;     // Catch: NoSuchFieldError -> L34
        L44:
            $SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment[PreserveAspectRatio.Alignment.xMinYMax.ordinal()] = 8;     // Catch: NoSuchFieldError -> L35
            return;
        }
    }

    public class MarkerPositionCalculator implements SVG.PathInterface {
        private boolean closepathReAdjustPending;
        private MarkerVector lastPos;
        private List<MarkerVector> markers;
        private boolean normalCubic;
        private boolean startArc;
        private float startX;
        private float startY;
        private int subpathStartIndex;
        final /* synthetic */ SVGAndroidRenderer this$0;

        public MarkerPositionCalculator(SVGAndroidRenderer r3, SVG.PathDefinition r4) {
            this.this$0 = r3;
            this.markers = new ArrayList();
            this.lastPos = null;
            this.startArc = false;
            this.normalCubic = true;
            this.subpathStartIndex = -1;
            if (r4 == null) goto L13;
            r4.enumeratePath(this);
            if (this.closepathReAdjustPending == false) goto L8;
            this.lastPos.add(this.markers.get(this.subpathStartIndex));
            this.markers.set(this.subpathStartIndex, this.lastPos);
            this.closepathReAdjustPending = false;
        L8:
            MarkerVector r32 = this.lastPos;
            if (r32 == null) goto L12;
            this.markers.add(r32);
            return;
        L12:
            return;
        }

        @Override // com.caverock.androidsvg.SVG.PathInterface
        public void arcTo(float r13, float r14, float r15, boolean r16, boolean r17, float r18, float r19) {
            this.startArc = true;
            this.normalCubic = false;
            MarkerVector r0 = this.lastPos;
            SVGAndroidRenderer.access$700(r0.x, r0.y, r13, r14, r15, r16, r17, r18, r19, this);
            this.normalCubic = true;
            this.closepathReAdjustPending = false;
        }

        @Override // com.caverock.androidsvg.SVG.PathInterface
        public void close() {
            this.markers.add(this.lastPos);
            lineTo(this.startX, this.startY);
            this.closepathReAdjustPending = true;
        }

        @Override // com.caverock.androidsvg.SVG.PathInterface
        public void cubicTo(float r9, float r10, float r11, float r12, float r13, float r14) {
            if (this.normalCubic == false) goto L5;
        L6:
            this.lastPos.add(r9, r10);
            this.markers.add(this.lastPos);
            this.startArc = false;
        L7:
            this.lastPos = new MarkerVector(this.this$0, r13, r14, r13 - r11, r14 - r12);
            this.closepathReAdjustPending = false;
            return;
        L5:
            if (this.startArc == false) goto L7;
            goto L6
        }

        public List<MarkerVector> getMarkers() {
            return this.markers;
        }

        @Override // com.caverock.androidsvg.SVG.PathInterface
        public void lineTo(float r9, float r10) {
            this.lastPos.add(r9, r10);
            this.markers.add(this.lastPos);
            SVGAndroidRenderer r3 = this.this$0;
            MarkerVector r0 = this.lastPos;
            this.lastPos = new MarkerVector(r3, r9, r10, r9 - r0.x, r10 - r0.y);
            this.closepathReAdjustPending = false;
        }

        @Override // com.caverock.androidsvg.SVG.PathInterface
        public void moveTo(float r9, float r10) {
            if (this.closepathReAdjustPending == false) goto L5;
            this.lastPos.add(this.markers.get(this.subpathStartIndex));
            this.markers.set(this.subpathStartIndex, this.lastPos);
            this.closepathReAdjustPending = false;
        L5:
            MarkerVector r0 = this.lastPos;
            if (r0 == null) goto L8;
            this.markers.add(r0);
        L8:
            this.startX = r9;
            this.startY = r10;
            this.lastPos = new MarkerVector(this.this$0, r9, r10, 0.0f, 0.0f);
            this.subpathStartIndex = this.markers.size();
        }

        @Override // com.caverock.androidsvg.SVG.PathInterface
        public void quadTo(float r9, float r10, float r11, float r12) {
            this.lastPos.add(r9, r10);
            this.markers.add(this.lastPos);
            this.lastPos = new MarkerVector(this.this$0, r11, r12, r11 - r9, r12 - r10);
            this.closepathReAdjustPending = false;
        }
    }

    public class MarkerVector {
        float dx;
        float dy;
        boolean isAmbiguous;
        final /* synthetic */ SVGAndroidRenderer this$0;
        float x;
        float y;

        public MarkerVector(SVGAndroidRenderer r3, float r4, float r5, float r6, float r7) {
            this.this$0 = r3;
            this.dx = 0.0f;
            this.dy = 0.0f;
            this.isAmbiguous = false;
            this.x = r4;
            this.y = r5;
            double r32 = Math.sqrt((r6 * r6) + (r7 * r7));
            if (r32 == 0.0d) goto L6;
            this.dx = (float) (r6 / r32);
            this.dy = (float) (r7 / r32);
            return;
        }

        public void add(float r5, float r6) {
            float r52 = r5 - this.x;
            float r62 = r6 - this.y;
            double r0 = Math.sqrt((r52 * r52) + (r62 * r62));
            if (r0 == 0.0d) goto L5;
            r52 = (float) (r52 / r0);
            r62 = (float) (r62 / r0);
        L5:
            float r02 = this.dx;
            if (r52 == (-r02)) goto L8;
        L11:
            this.dx = r02 + r52;
            this.dy += r62;
            return;
        L8:
            if (r62 != (-this.dy)) goto L11;
            this.isAmbiguous = true;
            this.dx = -r62;
            this.dy = r52;
        }

        public void add(MarkerVector r5) {
            float r0 = r5.dx;
            float r1 = this.dx;
            if (r0 != (-r1)) goto L8;
            float r2 = r5.dy;
            if (r2 != (-this.dy)) goto L8;
            this.isAmbiguous = true;
            this.dx = -r2;
            this.dy = r5.dx;
            return;
        L8:
            this.dx = r1 + r0;
            this.dy += r5.dy;
        }

        public String toString() {
            return "(" + this.x + "," + this.y + " " + this.dx + "," + this.dy + ")";
        }
    }

    public class PathConverter implements SVG.PathInterface {
        float lastX;
        float lastY;
        Path path;
        final /* synthetic */ SVGAndroidRenderer this$0;

        public PathConverter(SVGAndroidRenderer r1, SVG.PathDefinition r2) {
            this.this$0 = r1;
            this.path = new Path();
            if (r2 != null) goto L5;
            return;
        L5:
            r2.enumeratePath(this);
        }

        @Override // com.caverock.androidsvg.SVG.PathInterface
        public void arcTo(float r11, float r12, float r13, boolean r14, boolean r15, float r16, float r17) {
            SVGAndroidRenderer.access$700(this.lastX, this.lastY, r11, r12, r13, r14, r15, r16, r17, this);
            this.lastX = r16;
            this.lastY = r17;
        }

        @Override // com.caverock.androidsvg.SVG.PathInterface
        public void close() {
            this.path.close();
        }

        @Override // com.caverock.androidsvg.SVG.PathInterface
        public void cubicTo(float r8, float r9, float r10, float r11, float r12, float r13) {
            this.path.cubicTo(r8, r9, r10, r11, r12, r13);
            this.lastX = r12;
            this.lastY = r13;
        }

        public Path getPath() {
            return this.path;
        }

        @Override // com.caverock.androidsvg.SVG.PathInterface
        public void lineTo(float r2, float r3) {
            this.path.lineTo(r2, r3);
            this.lastX = r2;
            this.lastY = r3;
        }

        @Override // com.caverock.androidsvg.SVG.PathInterface
        public void moveTo(float r2, float r3) {
            this.path.moveTo(r2, r3);
            this.lastX = r2;
            this.lastY = r3;
        }

        @Override // com.caverock.androidsvg.SVG.PathInterface
        public void quadTo(float r2, float r3, float r4, float r5) {
            this.path.quadTo(r2, r3, r4, r5);
            this.lastX = r4;
            this.lastY = r5;
        }
    }

    public class PathTextDrawer extends PlainTextDrawer {
        private Path path;
        final /* synthetic */ SVGAndroidRenderer this$0;

        public PathTextDrawer(SVGAndroidRenderer r1, Path r2, float r3, float r4) {
            this.this$0 = r1;
            super(r1, r3, r4);
            this.path = r2;
        }

        @Override // com.caverock.androidsvg.SVGAndroidRenderer.PlainTextDrawer, com.caverock.androidsvg.SVGAndroidRenderer.TextProcessor
        public void processText(String r14) {
            if (SVGAndroidRenderer.access$200(this.this$0) == true) goto L5;
            String r2 = r14;
        L12:
            this.x += SVGAndroidRenderer.access$300(this.this$0).fillPaint.measureText(r2);
            return;
        L5:
            if (SVGAndroidRenderer.access$300(this.this$0).hasFill == false) goto L7;
            r2 = r14;
            SVGAndroidRenderer.access$400(this.this$0).drawTextOnPath(r2, this.path, this.x, this.y, SVGAndroidRenderer.access$300(this.this$0).fillPaint);
        L9:
            if (SVGAndroidRenderer.access$300(this.this$0).hasStroke == false) goto L12;
            SVGAndroidRenderer.access$400(this.this$0).drawTextOnPath(r2, this.path, this.x, this.y, SVGAndroidRenderer.access$300(this.this$0).strokePaint);
            goto L12
        L7:
            r2 = r14;
            goto L9
        }
    }

    public class PlainTextDrawer extends TextProcessor {
        final /* synthetic */ SVGAndroidRenderer this$0;
        float x;
        float y;

        public PlainTextDrawer(SVGAndroidRenderer r2, float r3, float r4) {
            this.this$0 = r2;
            super(r2, null);
            this.x = r3;
            this.y = r4;
        }

        @Override // com.caverock.androidsvg.SVGAndroidRenderer.TextProcessor
        public void processText(String r5) {
            SVGAndroidRenderer.access$100("TextSequence render", new Object[0]);
            if (SVGAndroidRenderer.access$200(this.this$0) == true) goto L5;
        L10:
            this.x += SVGAndroidRenderer.access$300(this.this$0).fillPaint.measureText(r5);
            return;
        L5:
            if (SVGAndroidRenderer.access$300(this.this$0).hasFill == false) goto L8;
            SVGAndroidRenderer.access$400(this.this$0).drawText(r5, this.x, this.y, SVGAndroidRenderer.access$300(this.this$0).fillPaint);
        L8:
            if (SVGAndroidRenderer.access$300(this.this$0).hasStroke == false) goto L10;
            SVGAndroidRenderer.access$400(this.this$0).drawText(r5, this.x, this.y, SVGAndroidRenderer.access$300(this.this$0).strokePaint);
            goto L10
        }
    }

    public class PlainTextToPath extends TextProcessor {
        Path textAsPath;
        final /* synthetic */ SVGAndroidRenderer this$0;
        float x;
        float y;

        public PlainTextToPath(SVGAndroidRenderer r2, float r3, float r4, Path r5) {
            this.this$0 = r2;
            super(r2, null);
            this.x = r3;
            this.y = r4;
            this.textAsPath = r5;
        }

        @Override // com.caverock.androidsvg.SVGAndroidRenderer.TextProcessor
        public boolean doTextContainer(SVG.TextContainer r2) {
            if ((r2 instanceof SVG.TextPath) == false) goto L6;
            SVGAndroidRenderer.access$800("Using <textPath> elements in a clip path is not supported.", new Object[0]);
            return false;
        L6:
            return true;
        }

        @Override // com.caverock.androidsvg.SVGAndroidRenderer.TextProcessor
        public void processText(String r9) {
            if (SVGAndroidRenderer.access$200(this.this$0) == false) goto L5;
            Path r7 = new Path();
            String r2 = r9;
            SVGAndroidRenderer.access$300(this.this$0).fillPaint.getTextPath(r2, 0, r9.length(), this.x, this.y, r7);
            this.textAsPath.addPath(r7);
        L6:
            this.x += SVGAndroidRenderer.access$300(this.this$0).fillPaint.measureText(r2);
            return;
        L5:
            r2 = r9;
            goto L6
        }
    }

    public class RendererState {
        Paint fillPaint;
        boolean hasFill;
        boolean hasStroke;
        boolean spacePreserve;
        Paint strokePaint;
        SVG.Style style;
        final /* synthetic */ SVGAndroidRenderer this$0;
        SVG.Box viewBox;
        SVG.Box viewPort;

        public RendererState(SVGAndroidRenderer r4) {
            this.this$0 = r4;
            Paint r42 = new Paint();
            this.fillPaint = r42;
            r42.setFlags(193);
            this.fillPaint.setHinting(0);
            this.fillPaint.setStyle(Paint.Style.FILL);
            Paint r43 = this.fillPaint;
            Typeface r2 = Typeface.DEFAULT;
            r43.setTypeface(r2);
            Paint r44 = new Paint();
            this.strokePaint = r44;
            r44.setFlags(193);
            this.strokePaint.setHinting(0);
            this.strokePaint.setStyle(Paint.Style.STROKE);
            this.strokePaint.setTypeface(r2);
            this.style = SVG.Style.getDefaultStyle();
        }

        public RendererState(SVGAndroidRenderer r2, RendererState r3) {
            this.this$0 = r2;
            this.hasFill = r3.hasFill;
            this.hasStroke = r3.hasStroke;
            this.fillPaint = new Paint(r3.fillPaint);
            this.strokePaint = new Paint(r3.strokePaint);
            SVG.Box r22 = r3.viewPort;
            if (r22 == null) goto L5;
            this.viewPort = new SVG.Box(r22);
        L5:
            SVG.Box r23 = r3.viewBox;
            if (r23 == null) goto L8;
            this.viewBox = new SVG.Box(r23);
        L8:
            this.spacePreserve = r3.spacePreserve;
            this.style = (SVG.Style) r3.style.clone();     // Catch: CloneNotSupportedException -> L11
            return;
        L11:
            e = move-exception;
            Log.e("SVGAndroidRenderer", "Unexpected clone error", e);
            this.style = SVG.Style.getDefaultStyle();
        }
    }

    public class TextBoundsCalculator extends TextProcessor {
        RectF bbox;
        final /* synthetic */ SVGAndroidRenderer this$0;
        float x;
        float y;

        public TextBoundsCalculator(SVGAndroidRenderer r2, float r3, float r4) {
            this.this$0 = r2;
            super(r2, null);
            this.bbox = new RectF();
            this.x = r3;
            this.y = r4;
        }

        @Override // com.caverock.androidsvg.SVGAndroidRenderer.TextProcessor
        public boolean doTextContainer(SVG.TextContainer r6) {
            if ((r6 instanceof SVG.TextPath) == false) goto L13;
            SVG.TextPath r0 = (SVG.TextPath) r6;
            SVG.SvgObject r62 = r6.document.resolveIRI(r0.href);
            if (r62 != null) goto L8;
            SVGAndroidRenderer.access$600("TextPath path reference '%s' not found", new Object[]{r0.href});
            return false;
        L8:
            SVG.Path r63 = (SVG.Path) r62;
            Path r02 = new PathConverter(this.this$0, r63.d).getPath();
            Matrix r64 = r63.transform;
            if (r64 == null) goto L11;
            r02.transform(r64);
        L11:
            RectF r65 = new RectF();
            r02.computeBounds(r65, true);
            this.bbox.union(r65);
            return false;
        L13:
            return true;
        }

        @Override // com.caverock.androidsvg.SVGAndroidRenderer.TextProcessor
        public void processText(String r5) {
            if (SVGAndroidRenderer.access$200(this.this$0) == false) goto L5;
            Rect r0 = new Rect();
            SVGAndroidRenderer.access$300(this.this$0).fillPaint.getTextBounds(r5, 0, r5.length(), r0);
            RectF r1 = new RectF(r0);
            r1.offset(this.x, this.y);
            this.bbox.union(r1);
        L5:
            this.x += SVGAndroidRenderer.access$300(this.this$0).fillPaint.measureText(r5);
        }
    }

    public abstract class TextProcessor {
        final /* synthetic */ SVGAndroidRenderer this$0;

        private TextProcessor(SVGAndroidRenderer r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ TextProcessor(SVGAndroidRenderer r1, AnonymousClass1 r2) {
            this(r1);
        }

        public boolean doTextContainer(SVG.TextContainer r1) {
            return true;
        }

        public abstract void processText(String r1);
    }

    public class TextWidthCalculator extends TextProcessor {
        final /* synthetic */ SVGAndroidRenderer this$0;
        float x;

        private TextWidthCalculator(SVGAndroidRenderer r2) {
            this.this$0 = r2;
            super(r2, null);
            this.x = 0.0f;
        }

        public /* synthetic */ TextWidthCalculator(SVGAndroidRenderer r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.caverock.androidsvg.SVGAndroidRenderer.TextProcessor
        public void processText(String r3) {
            this.x += SVGAndroidRenderer.access$300(this.this$0).fillPaint.measureText(r3);
        }
    }

    static {
    }

    public SVGAndroidRenderer(Canvas r2, float r3) {
        this.ruleMatchContext = null;
        this.canvas = r2;
        this.dpi = r3;
    }

    public static /* synthetic */ void access$100(String r0, Object[] r1) {
        debug(r0, r1);
    }

    public static /* synthetic */ boolean access$200(SVGAndroidRenderer r0) {
        return r0.visible();
    }

    public static /* synthetic */ RendererState access$300(SVGAndroidRenderer r0) {
        return r0.state;
    }

    public static /* synthetic */ Canvas access$400(SVGAndroidRenderer r0) {
        return r0.canvas;
    }

    public static /* synthetic */ void access$600(String r0, Object[] r1) {
        error(r0, r1);
    }

    public static /* synthetic */ void access$700(float r0, float r1, float r2, float r3, float r4, boolean r5, boolean r6, float r7, float r8, SVG.PathInterface r9) {
        arcTo(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void access$800(String r0, Object[] r1) {
        warn(r0, r1);
    }

    private static void arcTo(float r36, float r37, float r38, float r39, float r40, boolean r41, boolean r42, float r43, float r44, SVG.PathInterface r45) {
        if (r36 != r43) goto L8;
        if (r37 != r44) goto L8;
        return;
    L8:
        if (r38 == 0.0f) goto L43;
        if (r39 == 0.0f) goto L43;
        float r4 = Math.abs(r38);
        float r5 = Math.abs(r39);
        double r6 = Math.toRadians(r40 % 360.0d);
        double r8 = Math.cos(r6);
        double r62 = Math.sin(r6);
        double r10 = (r36 - r43) / 2.0d;
        double r14 = (r37 - r44) / 2.0d;
        double r12 = (r8 * r10) + (r62 * r14);
        double r82 = ((-r62) * r10) + (r8 * r14);
        double r102 = r4 * r4;
        double r142 = r5 * r5;
        double r18 = r12 * r12;
        double r20 = r82 * r82;
        double r22 = (r18 / r102) + (r20 / r142);
        if (r22 <= 0.99999d) goto L15;
        double r103 = Math.sqrt(r22) * 1.00001d;
        r4 = (float) (r4 * r103);
        r5 = (float) (r103 * r5);
        r102 = r4 * r4;
        r142 = r5 * r5;
    L15:
        double r222 = 1.0d;
        if (r41 != r42) goto L18;
        double r63 = -1.0d;
    L19:
        double r28 = r102 * r142;
        double r104 = r102 * r20;
        double r143 = r142 * r18;
        double r282 = ((r28 - r104) - r143) / (r104 + r143);
        if (r282 >= 0.0d) goto L22;
        r282 = 0.0d;
    L22:
        double r64 = r63 * Math.sqrt(r282);
        double r144 = r4;
        double r105 = r5;
        double r182 = ((r144 * r82) / r105) * r64;
        double r65 = (-((r105 * r12) / r144)) * r64;
        double r66 = ((r36 + r43) / 2.0d) + ((r8 * r182) - (r62 * r65));
        double r106 = ((r37 + r44) / 2.0d) + ((r62 * r182) + (r8 * r65));
        double r26 = (r12 - r182) / r144;
        double r30 = (r82 - r65) / r105;
        double r122 = ((-r12) - r182) / r144;
        double r83 = ((-r82) - r65) / r105;
        double r145 = (r26 * r26) + (r30 * r30);
        double r16 = Math.sqrt(r145);
        if (r30 >= 0.0d) goto L25;
        double r183 = -1.0d;
    L26:
        double r184 = r183 * Math.acos(r26 / r16);
        double r146 = Math.sqrt(r145 * ((r122 * r122) + (r83 * r83)));
        double r162 = (r26 * r122) + (r30 * r83);
        if (((r26 * r83) - (r30 * r122)) >= 0.0d) goto L29;
        r222 = -1.0d;
    L29:
        double r223 = r222 * checkedArcCos(r162 / r146);
        if (r42 == false) goto L32;
    L34:
        if (r42 == true) goto L36;
    L38:
        float[] r1 = arcToBeziers(r184 % 6.283185307179586d, r223 % 6.283185307179586d);
        Matrix r84 = new Matrix();
        r84.postScale(r4, r5);
        r84.postRotate(r40);
        r84.postTranslate((float) r66, (float) r106);
        r84.mapPoints(r1);
        r1[r1.length - 2] = r43;
        r1[r1.length - 1] = r44;
        int r0 = 0;
    L40:
        if (r0 >= r1.length) goto L46;
        r45.cubicTo(r1[r0], r1[r0 + 1], r1[r0 + 2], r1[r0 + 3], r1[r0 + 4], r1[r0 + 5]);
        r0 = r0 + 6;
        goto L40
    L46:
        return;
    L36:
        if (r223 >= 0.0d) goto L38;
        r223 = r223 + 6.283185307179586d;
        goto L38
    L32:
        if (r223 <= 0.0d) goto L34;
        r223 = r223 - 6.283185307179586d;
        goto L38
    L25:
        r183 = 1.0d;
        goto L26
    L18:
        r63 = 1.0d;
    L43:
        r45.lineTo(r43, r44);
    }

    private static float[] arcToBeziers(double r18, double r20) {
        int r0 = (int) Math.ceil((Math.abs(r20) * 2.0d) / 3.141592653589793d);
        double r4 = r20 / r0;
        double r1 = r4 / 2.0d;
        double r6 = (Math.sin(r1) * 1.3333333333333333d) / (Math.cos(r1) + 1.0d);
        float[] r12 = new float[r0 * 6];
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r2 >= r0) goto L6;
        double r8 = r18 + (r2 * r4);
        double r10 = Math.cos(r8);
        double r122 = Math.sin(r8);
        float[] r202 = r12;
        r202[r3] = (float) (r10 - (r6 * r122));
        r202[r3 + 1] = (float) (r122 + (r10 * r6));
        double r82 = r8 + r4;
        double r102 = Math.cos(r82);
        double r83 = Math.sin(r82);
        r202[r3 + 2] = (float) ((r6 * r83) + r102);
        r202[r3 + 3] = (float) (r83 - (r6 * r102));
        int r13 = r3 + 5;
        r202[r3 + 4] = (float) r102;
        r3 = r3 + 6;
        r202[r13] = (float) r83;
        r2 = r2 + 1;
        r12 = r202;
        r0 = r0;
        goto L3
    L6:
        return r12;
    }

    @TargetApi(19)
    private Path calculateClipPath(SVG.SvgElement r6, SVG.Box r7) {
        SVG.SvgObject r62 = r6.document.resolveIRI(this.state.style.clipPath);
        if (r62 != null) goto L6;
        error("ClipPath reference '%s' not found", new Object[]{this.state.style.clipPath});
        return null;
    L6:
        SVG.ClipPath r63 = (SVG.ClipPath) r62;
        this.stateStack.push(this.state);
        this.state = findInheritFromAncestorState(r63);
        Boolean r0 = r63.clipPathUnitsAreUser;
        if (r0 != null) goto L9;
    L12:
        boolean r02 = true;
    L13:
        Matrix r2 = new Matrix();
        if (r02 == true) goto L16;
        r2.preTranslate(r7.minX, r7.minY);
        r2.preScale(r7.width, r7.height);
    L16:
        Matrix r72 = r63.transform;
        if (r72 == null) goto L19;
        r2.preConcat(r72);
    L19:
        Path r73 = new Path();
        Iterator<SVG.SvgObject> r03 = r63.children.iterator();
    L21:
        if (r03.hasNext() == false) goto L29;
        SVG.SvgObject r3 = r03.next();
        if ((r3 instanceof SVG.SvgElement) == false) goto L21;
        Path r32 = objectToPath((SVG.SvgElement) r3, true);
        if (r32 == null) goto L21;
        r73.op(r32, Path.Op.UNION);
        goto L21
    L29:
        if (this.state.style.clipPath != null) goto L31;
    L36:
        r73.transform(r2);
        this.state = this.stateStack.pop();
        return r73;
    L31:
        if (r63.boundingBox != null) goto L33;
        r63.boundingBox = calculatePathBounds(r73);
    L33:
        Path r64 = calculateClipPath(r63, r63.boundingBox);
        if (r64 == null) goto L36;
        r73.op(r64, Path.Op.INTERSECT);
        goto L36
    L9:
        if (r0.booleanValue() == true) goto L12;
        r02 = false;
        goto L13
    }

    private List<MarkerVector> calculateMarkerPositions(SVG.Line r13) {
        SVG.Length r0 = r13.x1;
        float r1 = 0.0f;
        if (r0 == null) goto L5;
        float r4 = r0.floatValueX(this);
    L6:
        SVG.Length r02 = r13.y1;
        if (r02 == null) goto L9;
        float r5 = r02.floatValueY(this);
    L10:
        SVG.Length r03 = r13.x2;
        if (r03 == null) goto L13;
        float r8 = r03.floatValueX(this);
    L14:
        SVG.Length r132 = r13.y2;
        if (r132 == null) goto L17;
        r1 = r132.floatValueY(this);
    L17:
        float r9 = r1;
        ArrayList r133 = new ArrayList(2);
        float r6 = r8 - r4;
        float r7 = r9 - r5;
        r133.add(new MarkerVector(this, r4, r5, r6, r7));
        r133.add(new MarkerVector(this, r8, r9, r6, r7));
        return r133;
    L13:
        r8 = 0.0f;
        goto L14
    L9:
        r5 = 0.0f;
        goto L10
    L5:
        r4 = 0.0f;
        goto L6
    }

    private List<MarkerVector> calculateMarkerPositions(SVG.PolyLine r19) {
        int r1 = r19.points.length;
        int r2 = 2;
        if (r1 >= 2) goto L6;
        return null;
    L6:
        ArrayList r3 = new ArrayList();
        float[] r5 = r19.points;
        MarkerVector r4 = new MarkerVector(this, r5[0], r5[1], 0.0f, 0.0f);
        float r52 = 0.0f;
        float r6 = 0.0f;
    L7:
        if (r2 >= r1) goto L10;
        float[] r53 = r19.points;
        float r14 = r53[r2];
        float r15 = r53[r2 + 1];
        r4.add(r14, r15);
        r3.add(r4);
        r2 = r2 + 2;
        r4 = new MarkerVector(this, r14, r15, r14 - r4.x, r15 - r4.y);
        r52 = r14;
        r6 = r15;
        goto L7
    L10:
        if ((r19 instanceof SVG.Polygon) == false) goto L17;
        float[] r0 = r19.points;
        float r142 = r0[0];
        if (r52 == r142) goto L16;
        float r152 = r0[1];
        if (r6 == r152) goto L16;
        r4.add(r142, r152);
        r3.add(r4);
        MarkerVector r12 = new MarkerVector(this, r142, r152, r142 - r4.x, r152 - r4.y);
        r12.add((MarkerVector) r3.get(0));
        r3.add(r12);
        r3.set(0, r12);
    L16:
        return r3;
    L17:
        r3.add(r4);
        return r3;
    }

    private SVG.Box calculatePathBounds(Path r4) {
        RectF r3 = new RectF();
        r4.computeBounds(r3, true);
        return new SVG.Box(r3.left, r3.top, r3.width(), r3.height());
    }

    private float calculateTextWidth(SVG.TextContainer r3) {
        TextWidthCalculator r0 = new TextWidthCalculator(this, null);
        enumerateTextSpans(r3, r0);
        return r0.x;
    }

    private Matrix calculateViewBoxTransform(SVG.Box r9, SVG.Box r10, PreserveAspectRatio r11) {
        Matrix r8 = new Matrix();
        if (r11 != null) goto L5;
    L38:
        return r8;
    L5:
        if (r11.getAlignment() == null) goto L38;
        float r0 = r9.width / r10.width;
        float r1 = r9.height / r10.height;
        float r2 = -r10.minX;
        float r3 = -r10.minY;
        if (r11.equals(PreserveAspectRatio.STRETCH) == false) goto L12;
        r8.preTranslate(r9.minX, r9.minY);
        r8.preScale(r0, r1);
        r8.preTranslate(r2, r3);
        return r8;
    L12:
        if (r11.getScale() != PreserveAspectRatio.Scale.slice) goto L14;
        float r02 = Math.max(r0, r1);
    L15:
        float r12 = r9.width / r02;
        float r4 = r9.height / r02;
        int[] r5 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment;
        switch(r5[r11.getAlignment().ordinal()]) {
            case 1: goto L20;
            case 2: goto L20;
            case 3: goto L20;
            case 4: goto L18;
            case 5: goto L18;
            case 6: goto L18;
            default: goto L21;
        };
    L18:
        float r6 = r10.width - r12;
    L19:
        r2 = r2 - r6;
        goto L21
    L20:
        r6 = (r10.width - r12) / 2.0f;
    L21:
        int r112 = r5[r11.getAlignment().ordinal()];
        if (r112 != 2) goto L24;
    L36:
        float r102 = (r10.height - r4) / 2.0f;
    L35:
        r3 = r3 - r102;
    L37:
        r8.preTranslate(r9.minX, r9.minY);
        r8.preScale(r02, r02);
        r8.preTranslate(r2, r3);
        goto L38
    L24:
        if (r112 != 3) goto L26;
    L34:
        r102 = r10.height - r4;
        goto L35
    L26:
        if (r112 == 5) goto L36;
        if (r112 == 6) goto L34;
        if (r112 == 7) goto L36;
        if (r112 == 8) goto L34;
    L14:
        r02 = Math.min(r0, r1);
        goto L15
    }

    private void checkForClipPath(SVG.SvgElement r2) {
        checkForClipPath(r2, r2.boundingBox);
    }

    private void checkForClipPath(SVG.SvgElement r2, SVG.Box r3) {
        if (this.state.style.clipPath == null) goto L10;
        Path r22 = calculateClipPath(r2, r3);
        if (r22 == null) goto L9;
        this.canvas.clipPath(r22);
        return;
    L9:
        return;
    }

    private void checkForGradientsAndPatterns(SVG.SvgElement r4) {
        SVG.SvgPaint r0 = this.state.style.fill;
        if ((r0 instanceof SVG.PaintReference) == false) goto L5;
        decodePaintReference(true, r4.boundingBox, (SVG.PaintReference) r0);
    L5:
        SVG.SvgPaint r02 = this.state.style.stroke;
        if ((r02 instanceof SVG.PaintReference) == false) goto L9;
        decodePaintReference(false, r4.boundingBox, (SVG.PaintReference) r02);
        return;
    }

    private Bitmap checkForImageDataURL(String r4) {
        if (r4.startsWith("data:") == true) goto L6;
        return null;
    L6:
        if (r4.length() >= 14) goto L8;
        return null;
    L8:
        int r3 = r4.indexOf(44);
        if (r3 >= 12) goto L12;
        return null;
    L12:
        if (";base64".equals(r4.substring(r3 - 7, r3)) == true) goto L20;
        return null;
    L20:
        byte[] r32 = Base64.decode(r4.substring(r3 + 1), 0);     // Catch: Exception -> L17
        return BitmapFactory.decodeByteArray(r32, 0, r32.length);
    L17:
        e = move-exception;
        Log.e("SVGAndroidRenderer", "Could not decode bad Data URL", e);
        return null;
    }

    private Typeface checkGenericFont(String r5, Integer r6, SVG.Style.FontStyle r7) {
        char r0 = 0;
        if (r7 != SVG.Style.FontStyle.Italic) goto L5;
        boolean r4 = true;
    L7:
        if (r6.intValue() <= 500) goto L11;
        if (r4 == false) goto L10;
        int r42 = 3;
    L14:
        r5.getClass();
        switch(r5.hashCode()) {
            case -1536685117: goto L34;
            case -1431958525: goto L30;
            case -1081737434: goto L26;
            case 109326717: goto L22;
            case 1126973893: goto L18;
            default: goto L16;
        };
    L16:
        r0 = 65535;
    L36:
        switch(r0) {
            case 0: goto L48;
            case 1: goto L46;
            case 2: goto L44;
            case 3: goto L42;
            case 4: goto L40;
            default: goto L37;
        };
    L37:
        return null;
    L40:
        return Typeface.create(Typeface.SANS_SERIF, r42);
    L42:
        return Typeface.create(Typeface.SERIF, r42);
    L44:
        return Typeface.create(Typeface.SANS_SERIF, r42);
    L46:
        return Typeface.create(Typeface.MONOSPACE, r42);
    L48:
        return Typeface.create(Typeface.SANS_SERIF, r42);
    L18:
        if (r5.equals("cursive") == false) goto L16;
        r0 = 4;
        goto L36
    L22:
        if (r5.equals("serif") == false) goto L16;
        r0 = 3;
        goto L36
    L26:
        if (r5.equals("fantasy") == false) goto L16;
        r0 = 2;
        goto L36
    L30:
        if (r5.equals("monospace") == false) goto L16;
        r0 = 1;
        goto L36
    L34:
        if (r5.equals("sans-serif") == true) goto L36;
    L10:
        r42 = 1;
        goto L14
    L11:
        if (r4 == false) goto L13;
        r42 = 2;
        goto L14
    L13:
        r42 = 0;
        goto L14
    L5:
        r4 = false;
        goto L7
    }

    private void checkXMLSpaceAttribute(SVG.SvgObject r2) {
        if ((r2 instanceof SVG.SvgElementBase) == false) goto L10;
        Boolean r22 = ((SVG.SvgElementBase) r2).spacePreserve;
        if (r22 == null) goto L9;
        RendererState r1 = this.state;
        r1.spacePreserve = r22.booleanValue();
        return;
    L9:
        return;
    }

    private static double checkedArcCos(double r2) {
        if (r2 >= (-1.0d)) goto L7;
        return 3.141592653589793d;
    L7:
        if (r2 <= 1.0d) goto L11;
        return 0.0d;
    L11:
        return Math.acos(r2);
    }

    private static int clamp255(float r1) {
        int r12 = (int) (r1 * 256.0f);
        if (r12 >= 0) goto L7;
        return 0;
    L7:
        if (r12 <= 255) goto L9;
        return 255;
    L9:
        return r12;
    }

    private static int colourWithOpacity(int r2, float r3) {
        int r1 = 255;
        int r32 = Math.round(((r2 >> 24) & 255) * r3);
        if (r32 >= 0) goto L5;
        r1 = 0;
    L9:
        return (r2 & 16777215) | (r1 << 24);
    L5:
        if (r32 > 255) goto L9;
        r1 = r32;
        goto L9
    }

    private static void debug(String r0, Object... r1) {
    }

    private void decodePaintReference(boolean r3, SVG.Box r4, SVG.PaintReference r5) {
        SVG.SvgObject r0 = this.document.resolveIRI(r5.href);
        if (r0 != null) goto L18;
        if (r3 == false) goto L6;
        String r42 = "Fill";
    L7:
        error("%s reference '%s' not found", new Object[]{r42, r5.href});
        SVG.SvgPaint r43 = r5.fallback;
        if (r43 == null) goto L11;
        setPaintColour(this.state, r3, r43);
        return;
    L11:
        RendererState r2 = this.state;
        if (r3 == false) goto L15;
        r2.hasFill = false;
        return;
    L15:
        r2.hasStroke = false;
        return;
    L6:
        r42 = "Stroke";
        goto L7
    L18:
        if ((r0 instanceof SVG.SvgLinearGradient) == false) goto L22;
        makeLinearGradient(r3, r4, (SVG.SvgLinearGradient) r0);
        return;
    L22:
        if ((r0 instanceof SVG.SvgRadialGradient) == false) goto L26;
        makeRadialGradient(r3, r4, (SVG.SvgRadialGradient) r0);
        return;
    L26:
        if ((r0 instanceof SVG.SolidColor) == false) goto L29;
        setSolidColor(r3, (SVG.SolidColor) r0);
        return;
    }

    private boolean display() {
        Boolean r0 = this.state.style.display;
        if (r0 != null) goto L5;
        return true;
    L5:
        return r0.booleanValue();
    }

    private void doFilledPath(SVG.SvgElement r3, Path r4) {
        SVG.SvgPaint r0 = this.state.style.fill;
        if ((r0 instanceof SVG.PaintReference) == false) goto L8;
        SVG.SvgObject r02 = this.document.resolveIRI(((SVG.PaintReference) r0).href);
        if ((r02 instanceof SVG.Pattern) == false) goto L8;
        fillWithPattern(r3, r4, (SVG.Pattern) r02);
        return;
    L8:
        this.canvas.drawPath(r4, this.state.fillPaint);
    }

    private void doStroke(Path r6) {
        RendererState r0 = this.state;
        SVG.Style.VectorEffect r1 = r0.style.vectorEffect;
        SVG.Style.VectorEffect r2 = SVG.Style.VectorEffect.NonScalingStroke;
        Canvas r3 = this.canvas;
        if (r1 != r2) goto L11;
        Matrix r02 = r3.getMatrix();
        Path r12 = new Path();
        r6.transform(r02, r12);
        this.canvas.setMatrix(new Matrix());
        Shader r62 = this.state.strokePaint.getShader();
        Matrix r22 = new Matrix();
        if (r62 == null) goto L7;
        r62.getLocalMatrix(r22);
        Matrix r32 = new Matrix(r22);
        r32.postConcat(r02);
        r62.setLocalMatrix(r32);
    L7:
        this.canvas.drawPath(r12, this.state.strokePaint);
        this.canvas.setMatrix(r02);
        if (r62 == null) goto L13;
        r62.setLocalMatrix(r22);
        return;
    L13:
        return;
    L11:
        r3.drawPath(r6, r0.strokePaint);
    }

    private float dotProduct(float r1, float r2, float r3, float r4) {
        return (r1 * r3) + (r2 * r4);
    }

    private void enumerateTextSpans(SVG.TextContainer r5, TextProcessor r6) {
        if (display() == false) goto L13;
        Iterator<SVG.SvgObject> r52 = r5.children.iterator();
        boolean r1 = true;
    L7:
        if (r52.hasNext() == false) goto L17;
        SVG.SvgObject r2 = r52.next();
        if ((r2 instanceof SVG.TextSequence) == false) goto L11;
        r6.processText(textXMLSpaceTransform(((SVG.TextSequence) r2).text, r1, !r52.hasNext()));
    L12:
        r1 = false;
        goto L7
    L11:
        processTextChild(r2, r6);
        goto L12
    L17:
        return;
    }

    private static void error(String r1, Object... r2) {
        Log.e("SVGAndroidRenderer", String.format(r1, r2));
    }

    private void extractRawText(SVG.TextContainer r5, StringBuilder r6) {
        Iterator<SVG.SvgObject> r52 = r5.children.iterator();
        boolean r1 = true;
    L4:
        if (r52.hasNext() == false) goto L12;
        SVG.SvgObject r2 = r52.next();
        if ((r2 instanceof SVG.TextContainer) == false) goto L9;
        extractRawText((SVG.TextContainer) r2, r6);
    L11:
        r1 = false;
        goto L4
    L9:
        if ((r2 instanceof SVG.TextSequence) == false) goto L11;
        r6.append(textXMLSpaceTransform(((SVG.TextSequence) r2).text, r1, !r52.hasNext()));
        goto L11
    }

    private void fillInChainedGradientFields(SVG.GradientElement r3, String r4) {
        SVG.SvgObject r0 = r3.document.resolveIRI(r4);
        if (r0 != null) goto L7;
        warn("Gradient reference '%s' not found", new Object[]{r4});
        return;
    L7:
        if ((r0 instanceof SVG.GradientElement) == true) goto L10;
        error("Gradient href attributes must point to other gradient elements", new Object[0]);
        return;
    L10:
        if (r0 != r3) goto L13;
        error("Circular reference in gradient href attribute '%s'", new Object[]{r4});
        return;
    L13:
        SVG.GradientElement r42 = (SVG.GradientElement) r0;
        if (r3.gradientUnitsAreUser != null) goto L17;
        r3.gradientUnitsAreUser = r42.gradientUnitsAreUser;
    L17:
        if (r3.gradientTransform != null) goto L20;
        r3.gradientTransform = r42.gradientTransform;
    L20:
        if (r3.spreadMethod != null) goto L23;
        r3.spreadMethod = r42.spreadMethod;
    L23:
        if (r3.children.isEmpty() == false) goto L34;
        r3.children = r42.children;
    L34:
        if ((r3 instanceof SVG.SvgLinearGradient) == false) goto L28;
        fillInChainedGradientFields((SVG.SvgLinearGradient) r3, (SVG.SvgLinearGradient) r0);     // Catch: ClassCastException -> L33
        goto L29
    L28:
        fillInChainedGradientFields((SVG.SvgRadialGradient) r3, (SVG.SvgRadialGradient) r0);     // Catch: ClassCastException -> L33
    L29:
        String r43 = r42.href;
        if (r43 == null) goto L35;
        fillInChainedGradientFields(r3, r43);
        return;
    }

    private void fillInChainedGradientFields(SVG.SvgLinearGradient r1, SVG.SvgLinearGradient r2) {
        if (r1.x1 != null) goto L6;
        r1.x1 = r2.x1;
    L6:
        if (r1.y1 != null) goto L9;
        r1.y1 = r2.y1;
    L9:
        if (r1.x2 != null) goto L12;
        r1.x2 = r2.x2;
    L12:
        if (r1.y2 != null) goto L15;
        r1.y2 = r2.y2;
        return;
    }

    private void fillInChainedGradientFields(SVG.SvgRadialGradient r1, SVG.SvgRadialGradient r2) {
        if (r1.cx != null) goto L6;
        r1.cx = r2.cx;
    L6:
        if (r1.cy != null) goto L9;
        r1.cy = r2.cy;
    L9:
        if (r1.r != null) goto L12;
        r1.r = r2.r;
    L12:
        if (r1.fx != null) goto L15;
        r1.fx = r2.fx;
    L15:
        if (r1.fy != null) goto L18;
        r1.fy = r2.fy;
        return;
    }

    private void fillInChainedPatternFields(SVG.Pattern r3, String r4) {
        SVG.SvgObject r0 = r3.document.resolveIRI(r4);
        if (r0 != null) goto L7;
        warn("Pattern reference '%s' not found", new Object[]{r4});
        return;
    L7:
        if ((r0 instanceof SVG.Pattern) == true) goto L10;
        error("Pattern href attributes must point to other pattern elements", new Object[0]);
        return;
    L10:
        if (r0 != r3) goto L13;
        error("Circular reference in pattern href attribute '%s'", new Object[]{r4});
        return;
    L13:
        SVG.Pattern r02 = (SVG.Pattern) r0;
        if (r3.patternUnitsAreUser != null) goto L17;
        r3.patternUnitsAreUser = r02.patternUnitsAreUser;
    L17:
        if (r3.patternContentUnitsAreUser != null) goto L20;
        r3.patternContentUnitsAreUser = r02.patternContentUnitsAreUser;
    L20:
        if (r3.patternTransform != null) goto L23;
        r3.patternTransform = r02.patternTransform;
    L23:
        if (r3.x != null) goto L26;
        r3.x = r02.x;
    L26:
        if (r3.y != null) goto L29;
        r3.y = r02.y;
    L29:
        if (r3.width != null) goto L32;
        r3.width = r02.width;
    L32:
        if (r3.height != null) goto L35;
        r3.height = r02.height;
    L35:
        if (r3.children.isEmpty() == false) goto L38;
        r3.children = r02.children;
    L38:
        if (r3.viewBox != null) goto L41;
        r3.viewBox = r02.viewBox;
    L41:
        if (r3.preserveAspectRatio != null) goto L43;
        r3.preserveAspectRatio = r02.preserveAspectRatio;
    L43:
        String r42 = r02.href;
        if (r42 == null) goto L47;
        fillInChainedPatternFields(r3, r42);
        return;
    }

    private void fillWithPattern(SVG.SvgElement r21, Path r22, SVG.Pattern r23) {
        Boolean r3 = r23.patternUnitsAreUser;
        if (r3 != null) goto L5;
    L7:
        boolean r32 = false;
    L8:
        String r6 = r23.href;
        if (r6 == null) goto L11;
        fillInChainedPatternFields(r23, r6);
    L11:
        SVG.Length r62 = r23.x;
        if (r32 == false) goto L29;
        if (r62 == null) goto L15;
        float r33 = r62.floatValueX(this);
    L16:
        SVG.Length r63 = r23.y;
        if (r63 == null) goto L19;
        float r64 = r63.floatValueY(this);
    L20:
        SVG.Length r8 = r23.width;
        if (r8 == null) goto L23;
        float r82 = r8.floatValueX(this);
    L24:
        SVG.Length r9 = r23.height;
        if (r9 == null) goto L27;
        float r92 = r9.floatValueY(this);
    L46:
        if (r82 != 0.0f) goto L48;
        return;
    L48:
        if (r92 == 0.0f) goto L115;
        PreserveAspectRatio r10 = r23.preserveAspectRatio;
        if (r10 != null) goto L54;
        r10 = PreserveAspectRatio.LETTERBOX;
    L54:
        statePush();
        this.canvas.clipPath(r22);
        RendererState r11 = new RendererState(this);
        updateStyle(r11, SVG.Style.getDefaultStyle());
        r11.style.overflow = Boolean.FALSE;
        this.state = findInheritFromAncestorState(r23, r11);
        SVG.Box r112 = r21.boundingBox;
        Matrix r12 = r23.patternTransform;
        if (r12 == null) goto L74;
        this.canvas.concat(r12);
        Matrix r122 = new Matrix();
        if (r23.patternTransform.invert(r122) == false) goto L74;
        SVG.Box r113 = r21.boundingBox;
        float r13 = r113.minX;
        float r14 = r113.minY;
        float r114 = r113.maxX();
        SVG.Box r15 = r21.boundingBox;
        boolean r16 = false;
        float r4 = r15.minY;
        float r152 = r15.maxX();
        boolean r17 = true;
        float r5 = r21.boundingBox.maxY();
        SVG.Box r7 = r21.boundingBox;
        float r18 = r33;
        int r132 = 2;
        float[] r34 = {r13, r14, r114, r4, r152, r5, r7.minX, r7.maxY()};
        r122.mapPoints(r34);
        float r72 = r34[0];
        float r115 = r34[1];
        RectF r52 = new RectF(r72, r115, r72, r115);
    L59:
        if (r132 > 6) goto L73;
        float r73 = r34[r132];
        if (r73 >= r52.left) goto L64;
        r52.left = r73;
    L64:
        if (r73 <= r52.right) goto L66;
        r52.right = r73;
    L66:
        float r74 = r34[r132 + 1];
        if (r74 >= r52.top) goto L70;
        r52.top = r74;
    L70:
        if (r74 <= r52.bottom) goto L72;
        r52.bottom = r74;
    L72:
        r132 = r132 + 2;
        goto L59
    L73:
        float r35 = r52.left;
        float r42 = r52.top;
        r112 = new SVG.Box(r35, r42, r52.right - r35, r52.bottom - r42);
    L75:
        float r36 = r18 + (((float) Math.floor((r112.minX - r18) / r82)) * r82);
        float r65 = r64 + (((float) Math.floor((r112.minY - r64) / r92)) * r92);
        float r43 = r112.maxX();
        float r53 = r112.maxY();
        SVG.Box r75 = new SVG.Box(0.0f, 0.0f, r82, r92);
        boolean r116 = pushLayer();
    L77:
        if (r65 >= r53) goto L104;
        float r123 = r36;
    L80:
        if (r123 >= r43) goto L103;
        r75.minX = r123;
        r75.minY = r65;
        statePush();
        if (this.state.style.overflow.booleanValue() == true) goto L84;
        float r222 = r36;
        setClipRect(r75.minX, r75.minY, r75.width, r75.height);
    L85:
        SVG.Box r37 = r23.viewBox;
        if (r37 == null) goto L88;
        this.canvas.concat(calculateViewBoxTransform(r75, r37, r10));
    L98:
        Iterator<SVG.SvgObject> r38 = r23.children.iterator();
    L100:
        if (r38.hasNext() == false) goto L102;
        render(r38.next());
        goto L100
    L102:
        statePop();
        r123 = r123 + r82;
        r36 = r222;
        goto L80
    L88:
        Boolean r39 = r23.patternContentUnitsAreUser;
        if (r39 != null) goto L91;
    L94:
        boolean r310 = r17;
    L95:
        this.canvas.translate(r123, r65);
        if (r310 == true) goto L98;
        Canvas r311 = this.canvas;
        SVG.Box r133 = r21.boundingBox;
        r311.scale(r133.width, r133.height);
        goto L98
    L91:
        if (r39.booleanValue() == true) goto L94;
        r310 = r16;
        goto L95
    L84:
        r222 = r36;
        goto L85
    L103:
        r65 = r65 + r92;
        goto L77
    L104:
        if (r116 == false) goto L106;
        popLayer(r23);
    L106:
        statePop();
        return;
    L74:
        r18 = r33;
        r16 = false;
        r17 = true;
        goto L75
    L115:
        return;
    L27:
        r92 = 0.0f;
        goto L46
    L23:
        r82 = 0.0f;
        goto L24
    L19:
        r64 = 0.0f;
        goto L20
    L15:
        r33 = 0.0f;
        goto L16
    L29:
        if (r62 == null) goto L31;
        float r66 = r62.floatValue(this, 1.0f);
    L32:
        SVG.Length r83 = r23.y;
        if (r83 == null) goto L35;
        float r84 = r83.floatValue(this, 1.0f);
    L36:
        SVG.Length r93 = r23.width;
        if (r93 == null) goto L39;
        float r94 = r93.floatValue(this, 1.0f);
    L40:
        SVG.Length r102 = r23.height;
        if (r102 == null) goto L43;
        float r312 = r102.floatValue(this, 1.0f);
    L44:
        SVG.Box r103 = r21.boundingBox;
        float r117 = r103.minX;
        float r124 = r103.width;
        float r67 = (r66 * r124) + r117;
        float r118 = r103.minY;
        float r104 = r103.height;
        float r95 = r94 * r124;
        r92 = r312 * r104;
        r33 = r67;
        r64 = (r84 * r104) + r118;
        r82 = r95;
        goto L46
    L43:
        r312 = 0.0f;
        goto L44
    L39:
        r94 = 0.0f;
        goto L40
    L35:
        r84 = 0.0f;
        goto L36
    L31:
        r66 = 0.0f;
        goto L32
    L5:
        if (r3.booleanValue() == false) goto L7;
        r32 = true;
        goto L8
    }

    private RendererState findInheritFromAncestorState(SVG.SvgObject r3) {
        RendererState r0 = new RendererState(this);
        updateStyle(r0, SVG.Style.getDefaultStyle());
        return findInheritFromAncestorState(r3, r0);
    }

    private RendererState findInheritFromAncestorState(SVG.SvgObject r4, RendererState r5) {
        ArrayList r0 = new ArrayList();
    L3:
        int r2 = 0;
        if ((r4 instanceof SVG.SvgElementBase) == false) goto L6;
        r0.add(0, (SVG.SvgElementBase) r4);
    L6:
        Object r42 = r4.parent;
        if (r42 == null) goto L8;
        r4 = (SVG.SvgObject) r42;
        goto L3
    L8:
        int r43 = r0.size();
    L9:
        if (r2 >= r43) goto L11;
        Object r1 = r0.get(r2);
        r2 = r2 + 1;
        updateStyleForElement(r5, (SVG.SvgElementBase) r1);
        goto L9
    L11:
        RendererState r3 = this.state;
        r5.viewBox = r3.viewBox;
        r5.viewPort = r3.viewPort;
        return r5;
    }

    private SVG.Style.TextAnchor getAnchorPosition() {
        SVG.Style r2 = this.state.style;
        if (r2.direction == SVG.Style.TextDirection.LTR) goto L12;
        SVG.Style.TextAnchor r0 = r2.textAnchor;
        if (r0 == SVG.Style.TextAnchor.Middle) goto L12;
        SVG.Style.TextAnchor r22 = SVG.Style.TextAnchor.Start;
        if (r0 == r22) goto L10;
        return r22;
    L10:
        return SVG.Style.TextAnchor.End;
    L12:
        return r2.textAnchor;
    }

    private Path.FillType getClipRuleFromState() {
        SVG.Style.FillRule r1 = this.state.style.clipRule;
        if (r1 == null) goto L9;
        if (r1 != SVG.Style.FillRule.EvenOdd) goto L9;
        return Path.FillType.EVEN_ODD;
    L9:
        return Path.FillType.WINDING;
    }

    private Path.FillType getFillTypeFromState() {
        SVG.Style.FillRule r1 = this.state.style.fillRule;
        if (r1 == null) goto L9;
        if (r1 != SVG.Style.FillRule.EvenOdd) goto L9;
        return Path.FillType.EVEN_ODD;
    L9:
        return Path.FillType.WINDING;
    }

    private static synchronized void initialiseSupportedFeaturesMap() {
        monitor-enter(SVGAndroidRenderer.class);
        HashSet<String> r1 = new HashSet();     // Catch: Throwable -> L7
        supportedFeatures = r1;     // Catch: Throwable -> L7
        r1.add("Structure");     // Catch: Throwable -> L7
        supportedFeatures.add("BasicStructure");     // Catch: Throwable -> L7
        supportedFeatures.add("ConditionalProcessing");     // Catch: Throwable -> L7
        supportedFeatures.add("Image");     // Catch: Throwable -> L7
        supportedFeatures.add("Style");     // Catch: Throwable -> L7
        supportedFeatures.add("ViewportAttribute");     // Catch: Throwable -> L7
        supportedFeatures.add("Shape");     // Catch: Throwable -> L7
        supportedFeatures.add("BasicText");     // Catch: Throwable -> L7
        supportedFeatures.add("PaintAttribute");     // Catch: Throwable -> L7
        supportedFeatures.add("BasicPaintAttribute");     // Catch: Throwable -> L7
        supportedFeatures.add("OpacityAttribute");     // Catch: Throwable -> L7
        supportedFeatures.add("BasicGraphicsAttribute");     // Catch: Throwable -> L7
        supportedFeatures.add("Marker");     // Catch: Throwable -> L7
        supportedFeatures.add("Gradient");     // Catch: Throwable -> L7
        supportedFeatures.add("Pattern");     // Catch: Throwable -> L7
        supportedFeatures.add("Clip");     // Catch: Throwable -> L7
        supportedFeatures.add("BasicClip");     // Catch: Throwable -> L7
        supportedFeatures.add("Mask");     // Catch: Throwable -> L7
        supportedFeatures.add("View");     // Catch: Throwable -> L7
        monitor-exit(SVGAndroidRenderer.class);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private boolean isSpecified(SVG.Style r1, long r2) {
        if ((r1.specifiedFlags & r2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private void makeLinearGradient(boolean r21, SVG.Box r22, SVG.SvgLinearGradient r23) {
        String r3 = r23.href;
        if (r3 == null) goto L5;
        fillInChainedGradientFields(r23, r3);
    L5:
        Boolean r32 = r23.gradientUnitsAreUser;
        int r4 = 0;
        if (r32 != null) goto L8;
    L10:
        boolean r33 = false;
    L11:
        RendererState r6 = this.state;
        if (r21 == false) goto L14;
        Paint r62 = r6.fillPaint;
    L15:
        if (r33 == false) goto L34;
        SVG.Box r8 = getCurrentViewPortInUserUnits();
        SVG.Length r9 = r23.x1;
        if (r9 == null) goto L19;
        float r92 = r9.floatValueX(this);
    L20:
        SVG.Length r10 = r23.y1;
        if (r10 == null) goto L23;
        float r102 = r10.floatValueY(this);
    L24:
        SVG.Length r11 = r23.x2;
        if (r11 == null) goto L27;
        float r82 = r11.floatValueX(this);
    L28:
        SVG.Length r112 = r23.y2;
        if (r112 == null) goto L31;
        float r113 = r112.floatValueY(this);
    L32:
        float r15 = r82;
        float r13 = r92;
        float r16 = r113;
    L33:
        float r14 = r102;
        statePush();
        this.state = findInheritFromAncestorState(r23);
        Matrix r83 = new Matrix();
        if (r33 == true) goto L54;
        r83.preTranslate(r22.minX, r22.minY);
        r83.preScale(r22.width, r22.height);
    L54:
        Matrix r1 = r23.gradientTransform;
        if (r1 == null) goto L57;
        r83.preConcat(r1);
    L57:
        int r12 = r23.children.size();
        if (r12 != 0) goto L65;
        statePop();
        RendererState r0 = this.state;
        if (r21 == false) goto L63;
        r0.hasFill = false;
        return;
    L63:
        r0.hasStroke = false;
        return;
    L65:
        int[] r34 = new int[r12];
        float[] r93 = new float[r12];
        Iterator<SVG.SvgObject> r103 = r23.children.iterator();
        float r114 = -1.0f;
    L67:
        if (r103.hasNext() == false) goto L83;
        SVG.Stop r122 = (SVG.Stop) r103.next();
        Float r7 = r122.offset;
        if (r7 == null) goto L71;
        float r72 = r7.floatValue();
    L72:
        if (r4 != 0) goto L74;
    L77:
        r93[r4] = r72;
        r114 = r72;
    L78:
        statePush();
        updateStyleForElement(this.state, r122);
        SVG.Style r73 = this.state.style;
        SVG.Colour r123 = (SVG.Colour) r73.stopColor;
        if (r123 != null) goto L81;
        r123 = SVG.Colour.BLACK;
    L81:
        r34[r4] = colourWithOpacity(r123.colour, r73.stopOpacity.floatValue());
        r4 = r4 + 1;
        statePop();
        goto L67
    L74:
        if (r72 >= r114) goto L77;
        r93[r4] = r114;
        goto L78
    L71:
        r72 = 0.0f;
        goto L72
    L83:
        if (r13 == r15) goto L85;
    L86:
        if (r12 != 1) goto L89;
    L87:
        statePop();
        r62.setColor(r34[r12 - 1]);
        return;
    L89:
        Shader.TileMode r17 = Shader.TileMode.CLAMP;
        SVG.GradientSpread r2 = r23.spreadMethod;
        if (r2 != null) goto L92;
    L94:
        Shader.TileMode r19 = r17;
        statePop();
        LinearGradient r124 = new LinearGradient(r13, r14, r15, r16, r34, r93, r19);
        r124.setLocalMatrix(r83);
        r62.setShader(r124);
        r62.setAlpha(clamp255(this.state.style.fillOpacity.floatValue()));
        return;
    L92:
        if (r2 != SVG.GradientSpread.reflect) goto L96;
        r17 = Shader.TileMode.MIRROR;
        goto L94
    L96:
        if (r2 != SVG.GradientSpread.repeat) goto L94;
        r17 = Shader.TileMode.REPEAT;
        goto L94
    L85:
        if (r14 == r16) goto L87;
    L31:
        r113 = 0.0f;
        goto L32
    L27:
        r82 = r8.width;
        goto L28
    L23:
        r102 = 0.0f;
        goto L24
    L19:
        r92 = 0.0f;
        goto L20
    L34:
        SVG.Length r84 = r23.x1;
        if (r84 == null) goto L37;
        float r85 = r84.floatValue(this, 1.0f);
    L38:
        SVG.Length r104 = r23.y1;
        if (r104 == null) goto L41;
        r102 = r104.floatValue(this, 1.0f);
    L42:
        SVG.Length r115 = r23.x2;
        if (r115 == null) goto L45;
        float r116 = r115.floatValue(this, 1.0f);
    L46:
        SVG.Length r125 = r23.y2;
        if (r125 == null) goto L49;
        float r94 = r125.floatValue(this, 1.0f);
    L50:
        r13 = r85;
        r16 = r94;
        r15 = r116;
        goto L33
    L49:
        r94 = 0.0f;
        goto L50
    L45:
        r116 = 1.0f;
        goto L46
    L41:
        r102 = 0.0f;
        goto L42
    L37:
        r85 = 0.0f;
        goto L38
    L14:
        r62 = r6.strokePaint;
        goto L15
    L8:
        if (r32.booleanValue() == false) goto L10;
        r33 = true;
        goto L11
    }

    private Path makePathAndBoundingBox(SVG.Circle r19) {
        SVG.Length r2 = r19.cx;
        float r3 = 0.0f;
        if (r2 == null) goto L5;
        float r9 = r2.floatValueX(this);
    L6:
        SVG.Length r22 = r19.cy;
        if (r22 == null) goto L9;
        r3 = r22.floatValueY(this);
    L9:
        float r16 = r3;
        float r0 = r19.r.floatValue(this);
        float r23 = r9 - r0;
        float r8 = r16 - r0;
        float r5 = r9 + r0;
        float r32 = r16 + r0;
        if (r19.boundingBox != null) goto L12;
        float r6 = 2.0f * r0;
        r19.boundingBox = new SVG.Box(r23, r8, r6, r6);
    L12:
        float r02 = r0 * 0.5522848f;
        Path r4 = new Path();
        r4.moveTo(r9, r8);
        float r7 = r9 + r02;
        float r14 = r16 - r02;
        r4.cubicTo(r7, r8, r5, r14, r5, r16);
        float r142 = r16 + r02;
        r4.cubicTo(r5, r142, r7, r32, r9, r32);
        float r72 = r9 - r02;
        r4.cubicTo(r72, r32, r23, r142, r23, r16);
        r4.cubicTo(r23, r14, r72, r8, r9, r8);
        r4.close();
        return r4;
    L5:
        r9 = 0.0f;
        goto L6
    }

    private Path makePathAndBoundingBox(SVG.Ellipse r19) {
        SVG.Length r2 = r19.cx;
        float r3 = 0.0f;
        if (r2 == null) goto L5;
        float r9 = r2.floatValueX(this);
    L6:
        SVG.Length r22 = r19.cy;
        if (r22 == null) goto L9;
        r3 = r22.floatValueY(this);
    L9:
        float r16 = r3;
        float r23 = r19.rx.floatValueX(this);
        float r0 = r19.ry.floatValueY(this);
        float r32 = r9 - r23;
        float r8 = r16 - r0;
        float r5 = r9 + r23;
        float r4 = r16 + r0;
        if (r19.boundingBox != null) goto L12;
        r19.boundingBox = new SVG.Box(r32, r8, r23 * 2.0f, 2.0f * r0);
    L12:
        float r24 = r23 * 0.5522848f;
        float r02 = r0 * 0.5522848f;
        Path r10 = new Path();
        r10.moveTo(r9, r8);
        float r7 = r9 + r24;
        float r14 = r16 - r02;
        r10.cubicTo(r7, r8, r5, r14, r5, r16);
        float r142 = r16 + r02;
        r10.cubicTo(r5, r142, r7, r4, r9, r4);
        float r72 = r9 - r24;
        r10.cubicTo(r72, r4, r32, r142, r32, r16);
        r10.cubicTo(r32, r14, r72, r8, r9, r8);
        r10.close();
        return r10;
    L5:
        r9 = 0.0f;
        goto L6
    }

    private Path makePathAndBoundingBox(SVG.Line r9) {
        SVG.Length r0 = r9.x1;
        float r1 = 0.0f;
        if (r0 != null) goto L5;
        float r02 = 0.0f;
    L6:
        SVG.Length r2 = r9.y1;
        if (r2 != null) goto L9;
        float r22 = 0.0f;
    L10:
        SVG.Length r3 = r9.x2;
        if (r3 != null) goto L13;
        float r32 = 0.0f;
    L14:
        SVG.Length r4 = r9.y2;
        if (r4 == null) goto L19;
        r1 = r4.floatValueY(this);
    L19:
        if (r9.boundingBox != null) goto L21;
        r9.boundingBox = new SVG.Box(Math.min(r02, r32), Math.min(r22, r1), Math.abs(r32 - r02), Math.abs(r1 - r22));
    L21:
        Path r8 = new Path();
        r8.moveTo(r02, r22);
        r8.lineTo(r32, r1);
        return r8;
    L13:
        r32 = r3.floatValueX(this);
        goto L14
    L9:
        r22 = r2.floatValueY(this);
        goto L10
    L5:
        r02 = r0.floatValueX(this);
        goto L6
    }

    private Path makePathAndBoundingBox(SVG.PolyLine r6) {
        Path r0 = new Path();
        float[] r1 = r6.points;
        r0.moveTo(r1[0], r1[1]);
        int r12 = 2;
    L3:
        float[] r2 = r6.points;
        if (r12 >= r2.length) goto L7;
        r0.lineTo(r2[r12], r2[r12 + 1]);
        r12 = r12 + 2;
        goto L3
    L7:
        if ((r6 instanceof SVG.Polygon) == false) goto L10;
        r0.close();
    L10:
        if (r6.boundingBox != null) goto L12;
        r6.boundingBox = calculatePathBounds(r0);
    L12:
        return r0;
    }

    private Path makePathAndBoundingBox(SVG.Rect r20) {
        SVG.Length r2 = r20.rx;
        if (r2 == null) goto L5;
    L8:
        SVG.Length r4 = r20.ry;
        if (r2 != null) goto L11;
        float r22 = r4.floatValueY(this);
    L7:
        float r42 = r22;
    L14:
        float r23 = Math.min(r22, r20.width.floatValueX(this) / 2.0f);
        float r43 = Math.min(r42, r20.height.floatValueY(this) / 2.0f);
        SVG.Length r5 = r20.x;
        if (r5 == null) goto L17;
        float r7 = r5.floatValueX(this);
    L18:
        SVG.Length r52 = r20.y;
        if (r52 == null) goto L21;
        float r10 = r52.floatValueY(this);
    L22:
        float r53 = r20.width.floatValueX(this);
        float r0 = r20.height.floatValueY(this);
        if (r20.boundingBox != null) goto L25;
        r20.boundingBox = new SVG.Box(r7, r10, r53, r0);
    L25:
        float r54 = r53 + r7;
        float r15 = r10 + r0;
        Path r6 = new Path();
        if (r23 == 0.0f) goto L31;
        if (r43 == 0.0f) goto L31;
        float r1 = r23 * 0.5522848f;
        float r02 = 0.5522848f * r43;
        float r14 = r10 + r43;
        r6.moveTo(r7, r14);
        float r8 = r14 - r02;
        float r11 = r7 + r23;
        float r9 = r11 - r1;
        r6.cubicTo(r7, r8, r9, r10, r11, r10);
        float r24 = r54 - r23;
        r6.lineTo(r24, r10);
        float r92 = r24 + r1;
        r6.cubicTo(r92, r10, r54, r8, r54, r14);
        float r44 = r15 - r43;
        r6.lineTo(r54, r44);
        float r102 = r44 + r02;
        r6.cubicTo(r54, r102, r92, r15, r24, r15);
        r6.lineTo(r11, r15);
        float r93 = r7;
        r6.cubicTo(r9, r15, r93, r102, r7, r44);
        r6.lineTo(r93, r14);
    L32:
        r6.close();
        return r6;
    L31:
        r6.moveTo(r7, r10);
        r6.lineTo(r54, r10);
        r6.lineTo(r54, r15);
        r6.lineTo(r7, r15);
        r6.lineTo(r7, r10);
        goto L32
    L21:
        r10 = 0.0f;
        goto L22
    L17:
        r7 = 0.0f;
        goto L18
    L11:
        if (r4 != null) goto L13;
        r22 = r2.floatValueX(this);
        goto L7
    L13:
        r22 = r2.floatValueX(this);
        r42 = r20.ry.floatValueY(this);
        goto L14
    L5:
        if (r20.ry != null) goto L8;
        r22 = 0.0f;
        goto L7
    }

    private Path makePathAndBoundingBox(SVG.Text r10) {
        List<SVG.Length> r0 = r10.x;
        float r1 = 0.0f;
        if (r0 != null) goto L5;
    L8:
        float r02 = 0.0f;
    L9:
        List<SVG.Length> r3 = r10.y;
        if (r3 != null) goto L12;
    L15:
        float r32 = 0.0f;
    L16:
        List<SVG.Length> r4 = r10.dx;
        if (r4 != null) goto L19;
    L22:
        float r42 = 0.0f;
    L23:
        List<SVG.Length> r5 = r10.dy;
        if (r5 == null) goto L30;
        if (r5.size() == 0) goto L30;
        r1 = r10.dy.get(0).floatValueY(this);
    L30:
        if (this.state.style.textAnchor == SVG.Style.TextAnchor.Start) goto L36;
        float r2 = calculateTextWidth(r10);
        if (this.state.style.textAnchor != SVG.Style.TextAnchor.Middle) goto L34;
        r2 = r2 / 2.0f;
    L34:
        r02 = r02 - r2;
    L36:
        if (r10.boundingBox != null) goto L38;
        TextBoundsCalculator r22 = new TextBoundsCalculator(this, r02, r32);
        enumerateTextSpans(r10, r22);
        RectF r6 = r22.bbox;
        r10.boundingBox = new SVG.Box(r6.left, r6.top, r6.width(), r22.bbox.height());
    L38:
        Path r23 = new Path();
        enumerateTextSpans(r10, new PlainTextToPath(this, r02 + r42, r32 + r1, r23));
        return r23;
    L19:
        if (r4.size() == 0) goto L22;
        r42 = r10.dx.get(0).floatValueX(this);
        goto L23
    L12:
        if (r3.size() == 0) goto L15;
        r32 = r10.y.get(0).floatValueY(this);
        goto L16
    L5:
        if (r0.size() == 0) goto L8;
        r02 = r10.x.get(0).floatValueX(this);
        goto L9
    }

    private void makeRadialGradient(boolean r19, SVG.Box r20, SVG.SvgRadialGradient r21) {
        String r3 = r21.href;
        if (r3 == null) goto L5;
        fillInChainedGradientFields(r21, r3);
    L5:
        Boolean r32 = r21.gradientUnitsAreUser;
        int r4 = 0;
        if (r32 != null) goto L8;
    L10:
        boolean r33 = false;
    L11:
        RendererState r6 = this.state;
        if (r19 == false) goto L14;
        Paint r62 = r6.fillPaint;
    L15:
        if (r33 == false) goto L29;
        SVG.Length r7 = new SVG.Length(50.0f, SVG.Unit.percent);
        SVG.Length r8 = r21.cx;
        if (r8 == null) goto L19;
        float r82 = r8.floatValueX(this);
    L20:
        SVG.Length r9 = r21.cy;
        if (r9 == null) goto L23;
        float r92 = r9.floatValueY(this);
    L24:
        SVG.Length r10 = r21.r;
        if (r10 == null) goto L27;
        float r72 = r10.floatValue(this);
    L28:
        float r14 = r72;
        float r12 = r82;
        float r13 = r92;
    L41:
        statePush();
        this.state = findInheritFromAncestorState(r21);
        Matrix r73 = new Matrix();
        if (r33 == true) goto L44;
        r73.preTranslate(r20.minX, r20.minY);
        r73.preScale(r20.width, r20.height);
    L44:
        Matrix r1 = r21.gradientTransform;
        if (r1 == null) goto L47;
        r73.preConcat(r1);
    L47:
        int r15 = r21.children.size();
        if (r15 != 0) goto L55;
        statePop();
        RendererState r0 = this.state;
        if (r19 == false) goto L53;
        r0.hasFill = false;
        return;
    L53:
        r0.hasStroke = false;
        return;
    L55:
        int[] r152 = new int[r15];
        float[] r34 = new float[r15];
        Iterator<SVG.SvgObject> r83 = r21.children.iterator();
        float r93 = -1.0f;
    L57:
        if (r83.hasNext() == false) goto L73;
        SVG.Stop r102 = (SVG.Stop) r83.next();
        Float r11 = r102.offset;
        if (r11 == null) goto L61;
        float r112 = r11.floatValue();
    L62:
        if (r4 != 0) goto L64;
    L67:
        r34[r4] = r112;
        r93 = r112;
    L68:
        statePush();
        updateStyleForElement(this.state, r102);
        SVG.Style r103 = this.state.style;
        SVG.Colour r113 = (SVG.Colour) r103.stopColor;
        if (r113 != null) goto L71;
        r113 = SVG.Colour.BLACK;
    L71:
        r152[r4] = colourWithOpacity(r113.colour, r103.stopOpacity.floatValue());
        r4 = r4 + 1;
        statePop();
        goto L57
    L64:
        if (r112 >= r93) goto L67;
        r34[r4] = r93;
        goto L68
    L61:
        r112 = 0.0f;
        goto L62
    L73:
        if (r14 == 0.0f) goto L87;
        if (r15 == 1) goto L87;
        Shader.TileMode r16 = Shader.TileMode.CLAMP;
        SVG.GradientSpread r2 = r21.spreadMethod;
        if (r2 != null) goto L79;
    L81:
        Shader.TileMode r17 = r16;
        statePop();
        RadialGradient r114 = new RadialGradient(r12, r13, r14, r152, r34, r17);
        r114.setLocalMatrix(r73);
        r62.setShader(r114);
        r62.setAlpha(clamp255(this.state.style.fillOpacity.floatValue()));
        return;
    L79:
        if (r2 != SVG.GradientSpread.reflect) goto L83;
        r16 = Shader.TileMode.MIRROR;
        goto L81
    L83:
        if (r2 != SVG.GradientSpread.repeat) goto L81;
        r16 = Shader.TileMode.REPEAT;
    L87:
        statePop();
        r62.setColor(r152[r15 - 1]);
        return;
    L27:
        r72 = r7.floatValue(this);
        goto L28
    L23:
        r92 = r7.floatValueY(this);
        goto L24
    L19:
        r82 = r7.floatValueX(this);
        goto L20
    L29:
        SVG.Length r74 = r21.cx;
        float r84 = 0.5f;
        if (r74 == null) goto L32;
        float r75 = r74.floatValue(this, 1.0f);
    L33:
        SVG.Length r104 = r21.cy;
        if (r104 == null) goto L36;
        float r105 = r104.floatValue(this, 1.0f);
    L37:
        SVG.Length r115 = r21.r;
        if (r115 == null) goto L40;
        r84 = r115.floatValue(this, 1.0f);
    L40:
        r12 = r75;
        r14 = r84;
        r13 = r105;
        goto L41
    L36:
        r105 = 0.5f;
        goto L37
    L32:
        r75 = 0.5f;
        goto L33
    L14:
        r62 = r6.strokePaint;
        goto L15
    L8:
        if (r32.booleanValue() == false) goto L10;
        r33 = true;
        goto L11
    }

    private SVG.Box makeViewPort(SVG.Length r2, SVG.Length r3, SVG.Length r4, SVG.Length r5) {
        float r0 = 0.0f;
        if (r2 == null) goto L5;
        float r22 = r2.floatValueX(this);
    L6:
        if (r3 == null) goto L8;
        r0 = r3.floatValueY(this);
    L8:
        SVG.Box r32 = getCurrentViewPortInUserUnits();
        if (r4 == null) goto L11;
        float r42 = r4.floatValueX(this);
    L12:
        if (r5 == null) goto L14;
        float r1 = r5.floatValueY(this);
    L16:
        return new SVG.Box(r22, r0, r42, r1);
    L14:
        r1 = r32.height;
        goto L16
    L11:
        r42 = r32.width;
        goto L12
    L5:
        r22 = 0.0f;
        goto L6
    }

    @TargetApi(19)
    private Path objectToPath(SVG.SvgElement r5, boolean r6) {
        this.stateStack.push(this.state);
        RendererState r0 = new RendererState(this, this.state);
        this.state = r0;
        updateStyleForElement(r0, r5);
        if (display() == true) goto L5;
    L76:
        this.state = this.stateStack.pop();
        return null;
    L5:
        if (visible() == false) goto L76;
        if ((r5 instanceof SVG.Use) == false) goto L30;
        if (r6 == true) goto L12;
        error("<use> elements inside a <clipPath> cannot reference another <use>", new Object[0]);
    L12:
        SVG.Use r62 = (SVG.Use) r5;
        SVG.SvgObject r2 = r5.document.resolveIRI(r62.href);
        if (r2 != null) goto L17;
        error("Use reference '%s' not found", new Object[]{r62.href});
        this.state = this.stateStack.pop();
        return null;
    L17:
        if ((r2 instanceof SVG.SvgElement) == true) goto L20;
        this.state = this.stateStack.pop();
        return null;
    L20:
        Path r02 = objectToPath((SVG.SvgElement) r2, false);
        if (r02 != null) goto L24;
        return null;
    L24:
        if (r62.boundingBox != null) goto L26;
        r62.boundingBox = calculatePathBounds(r02);
    L26:
        Matrix r63 = r62.transform;
        if (r63 == null) goto L68;
        r02.transform(r63);
    L68:
        if (this.state.style.clipPath == null) goto L72;
        Path r52 = calculateClipPath(r5, r5.boundingBox);
        if (r52 == null) goto L72;
        r02.op(r52, Path.Op.INTERSECT);
    L72:
        this.state = this.stateStack.pop();
        return r02;
    L30:
        if ((r5 instanceof SVG.GraphicsElement) == false) goto L59;
        SVG.GraphicsElement r64 = (SVG.GraphicsElement) r5;
        if ((r5 instanceof SVG.Path) == false) goto L37;
        r02 = new PathConverter(this, ((SVG.Path) r5).d).getPath();
        if (r5.boundingBox != null) goto L49;
        r5.boundingBox = calculatePathBounds(r02);
    L49:
        if (r02 != null) goto L52;
        return null;
    L52:
        if (r64.boundingBox != null) goto L54;
        r64.boundingBox = calculatePathBounds(r02);
    L54:
        Matrix r65 = r64.transform;
        if (r65 == null) goto L57;
        r02.transform(r65);
    L57:
        r02.setFillType(getClipRuleFromState());
        goto L68
    L37:
        if ((r5 instanceof SVG.Rect) == false) goto L40;
        r02 = makePathAndBoundingBox((SVG.Rect) r5);
        goto L49
    L40:
        if ((r5 instanceof SVG.Circle) == false) goto L43;
        r02 = makePathAndBoundingBox((SVG.Circle) r5);
        goto L49
    L43:
        if ((r5 instanceof SVG.Ellipse) == false) goto L46;
        r02 = makePathAndBoundingBox((SVG.Ellipse) r5);
        goto L49
    L46:
        if ((r5 instanceof SVG.PolyLine) == false) goto L48;
        r02 = makePathAndBoundingBox((SVG.PolyLine) r5);
        goto L49
    L48:
        r02 = null;
        goto L49
    L59:
        if ((r5 instanceof SVG.Text) == false) goto L74;
        SVG.Text r66 = (SVG.Text) r5;
        r02 = makePathAndBoundingBox(r66);
        if (r02 != null) goto L63;
        return null;
    L63:
        Matrix r67 = r66.transform;
        if (r67 == null) goto L66;
        r02.transform(r67);
    L66:
        r02.setFillType(getClipRuleFromState());
        goto L68
    L74:
        error("Invalid %s element found in clipPath definition", new Object[]{r5.getNodeName()});
        return null;
    }

    private void parentPop() {
        this.parentStack.pop();
        this.matrixStack.pop();
    }

    private void parentPush(SVG.SvgContainer r2) {
        this.parentStack.push(r2);
        this.matrixStack.push(this.canvas.getMatrix());
    }

    private void popLayer(SVG.SvgElement r2) {
        popLayer(r2, r2.boundingBox);
    }

    private void popLayer(SVG.SvgElement r7, SVG.Box r8) {
        if (this.state.style.mask == null) goto L5;
        Paint r0 = new Paint();
        PorterDuff.Mode r2 = PorterDuff.Mode.DST_IN;
        r0.setXfermode(new PorterDuffXfermode(r2));
        this.canvas.saveLayer(null, r0, 31);
        Paint r02 = new Paint();
        r02.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.2127f, 0.7151f, 0.0722f, 0.0f, 0.0f})));
        this.canvas.saveLayer(null, r02, 31);
        SVG.Mask r03 = (SVG.Mask) this.document.resolveIRI(this.state.style.mask);
        renderMask(r03, r7, r8);
        this.canvas.restore();
        Paint r1 = new Paint();
        r1.setXfermode(new PorterDuffXfermode(r2));
        this.canvas.saveLayer(null, r1, 31);
        renderMask(r03, r7, r8);
        this.canvas.restore();
        this.canvas.restore();
    L5:
        statePop();
    }

    private void processTextChild(SVG.SvgObject r9, TextProcessor r10) {
        if (r10.doTextContainer((SVG.TextContainer) r9) == true) goto L6;
        return;
    L6:
        if ((r9 instanceof SVG.TextPath) == false) goto L10;
        statePush();
        renderTextPath((SVG.TextPath) r9);
        statePop();
        return;
    L10:
        if ((r9 instanceof SVG.TSpan) == false) goto L62;
        debug("TSpan render", new Object[0]);
        statePush();
        SVG.TSpan r92 = (SVG.TSpan) r9;
        updateStyleForElement(this.state, r92);
        if (display() == false) goto L59;
        List<SVG.Length> r0 = r92.x;
        if (r0 != null) goto L16;
    L18:
        boolean r02 = false;
    L19:
        boolean r2 = r10 instanceof PlainTextDrawer;
        float r3 = 0.0f;
        if (r2 == false) goto L45;
        if (r02 == true) goto L23;
        float r4 = ((PlainTextDrawer) r10).x;
    L24:
        List<SVG.Length> r5 = r92.y;
        if (r5 != null) goto L27;
    L30:
        float r52 = ((PlainTextDrawer) r10).y;
    L31:
        List<SVG.Length> r6 = r92.dx;
        if (r6 != null) goto L34;
    L37:
        float r62 = 0.0f;
    L38:
        List<SVG.Length> r7 = r92.dy;
        if (r7 != null) goto L41;
    L44:
        float r1 = r3;
        r3 = r4;
    L46:
        if (r02 == false) goto L53;
        SVG.Style.TextAnchor r03 = getAnchorPosition();
        if (r03 == SVG.Style.TextAnchor.Start) goto L53;
        float r42 = calculateTextWidth(r92);
        if (r03 != SVG.Style.TextAnchor.Middle) goto L52;
        r42 = r42 / 2.0f;
    L52:
        r3 = r3 - r42;
    L53:
        checkForGradientsAndPatterns((SVG.SvgElement) r92.getTextRoot());
        if (r2 == false) goto L56;
        PlainTextDrawer r04 = (PlainTextDrawer) r10;
        r04.x = r3 + r62;
        r04.y = r52 + r1;
    L56:
        boolean r05 = pushLayer();
        enumerateTextSpans(r92, r10);
        if (r05 == false) goto L59;
        popLayer(r92);
        goto L59
    L41:
        if (r7.size() == 0) goto L44;
        r3 = r92.dy.get(0).floatValueY(this);
        goto L44
    L34:
        if (r6.size() == 0) goto L37;
        r62 = r92.dx.get(0).floatValueX(this);
        goto L38
    L27:
        if (r5.size() == 0) goto L30;
        r52 = r92.y.get(0).floatValueY(this);
        goto L31
    L23:
        r4 = r92.x.get(0).floatValueX(this);
        goto L24
    L45:
        r1 = 0.0f;
        r52 = 0.0f;
        r62 = 0.0f;
        goto L46
    L16:
        if (r0.size() <= 0) goto L18;
        r02 = true;
    L59:
        statePop();
        return;
    L62:
        if ((r9 instanceof SVG.TRef) == false) goto L75;
        statePush();
        SVG.TRef r06 = (SVG.TRef) r9;
        updateStyleForElement(this.state, r06);
        if (display() == false) goto L73;
        checkForGradientsAndPatterns((SVG.SvgElement) r06.getTextRoot());
        SVG.SvgObject r93 = r9.document.resolveIRI(r06.href);
        if (r93 != null) goto L68;
    L72:
        error("Tref reference '%s' not found", new Object[]{r06.href});
        goto L73
    L68:
        if ((r93 instanceof SVG.TextContainer) == false) goto L72;
        StringBuilder r07 = new StringBuilder();
        extractRawText((SVG.TextContainer) r93, r07);
        if (r07.length() <= 0) goto L73;
        r10.processText(r07.toString());
    L73:
        statePop();
        return;
    }

    private boolean pushLayer() {
        if (requiresCompositing() == true) goto L6;
        return false;
    L6:
        this.canvas.saveLayerAlpha(null, clamp255(this.state.style.opacity.floatValue()), 31);
        this.stateStack.push(this.state);
        RendererState r0 = new RendererState(this, this.state);
        this.state = r0;
        String r02 = r0.style.mask;
        if (r02 == null) goto L13;
        SVG.SvgObject r03 = this.document.resolveIRI(r02);
        if (r03 != null) goto L11;
    L12:
        error("Mask reference '%s' not found", new Object[]{this.state.style.mask});
        this.state.style.mask = null;
        goto L13
    L11:
        if ((r03 instanceof SVG.Mask) == false) goto L12;
    L13:
        return true;
    }

    private MarkerVector realignMarkerMid(MarkerVector r5, MarkerVector r6, MarkerVector r7) {
        float r52 = dotProduct(r6.dx, r6.dy, r6.x - r5.x, r6.y - r5.y);
        if (r52 != 0.0f) goto L6;
        r52 = dotProduct(r6.dx, r6.dy, r7.x - r6.x, r7.y - r6.y);
    L6:
        if (r52 > 0.0f) goto L13;
        if (r52 == 0.0f) goto L10;
    L14:
        r6.dx = -r6.dx;
        r6.dy = -r6.dy;
        return r6;
    L10:
        if (r6.dx > 0.0f) goto L13;
        if (r6.dy < 0.0f) goto L14;
    L13:
        return r6;
    }

    private void render(SVG.Circle r4) {
        debug("Circle render", new Object[0]);
        SVG.Length r0 = r4.r;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isZero() == true) goto L27;
        updateStyleForElement(this.state, r4);
        if (display() == true) goto L11;
        return;
    L11:
        if (visible() == false) goto L29;
        Matrix r02 = r4.transform;
        if (r02 == null) goto L16;
        this.canvas.concat(r02);
    L16:
        Path r03 = makePathAndBoundingBox(r4);
        updateParentBoundingBox(r4);
        checkForGradientsAndPatterns(r4);
        checkForClipPath(r4);
        boolean r1 = pushLayer();
        if (this.state.hasFill == false) goto L20;
        doFilledPath(r4, r03);
    L20:
        if (this.state.hasStroke == false) goto L22;
        doStroke(r03);
    L22:
        if (r1 == false) goto L26;
        popLayer(r4);
        return;
    L26:
        return;
    L29:
        return;
    }

    private void render(SVG.Ellipse r4) {
        debug("Ellipse render", new Object[0]);
        SVG.Length r0 = r4.rx;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r4.ry != null) goto L7;
        return;
    L7:
        if (r0.isZero() == false) goto L9;
        return;
    L9:
        if (r4.ry.isZero() == true) goto L33;
        updateStyleForElement(this.state, r4);
        if (display() == true) goto L15;
        return;
    L15:
        if (visible() == false) goto L35;
        Matrix r02 = r4.transform;
        if (r02 == null) goto L20;
        this.canvas.concat(r02);
    L20:
        Path r03 = makePathAndBoundingBox(r4);
        updateParentBoundingBox(r4);
        checkForGradientsAndPatterns(r4);
        checkForClipPath(r4);
        boolean r1 = pushLayer();
        if (this.state.hasFill == false) goto L24;
        doFilledPath(r4, r03);
    L24:
        if (this.state.hasStroke == false) goto L26;
        doStroke(r03);
    L26:
        if (r1 == false) goto L32;
        popLayer(r4);
        return;
    L32:
        return;
    L35:
        return;
    }

    private void render(SVG.Group r3) {
        debug("Group render", new Object[0]);
        updateStyleForElement(this.state, r3);
        if (display() == true) goto L5;
        return;
    L5:
        Matrix r0 = r3.transform;
        if (r0 == null) goto L8;
        this.canvas.concat(r0);
    L8:
        checkForClipPath(r3);
        boolean r02 = pushLayer();
        renderChildren(r3, true);
        if (r02 == false) goto L11;
        popLayer(r3);
    L11:
        updateParentBoundingBox(r3);
    }

    private void render(SVG.Image r12) {
        int r0 = 0;
        debug("Image render", new Object[0]);
        SVG.Length r1 = r12.width;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.isZero() == true) goto L51;
        SVG.Length r13 = r12.height;
        if (r13 != null) goto L9;
        return;
    L9:
        if (r13.isZero() == true) goto L54;
        String r14 = r12.href;
        if (r14 == null) goto L55;
        PreserveAspectRatio r2 = r12.preserveAspectRatio;
        if (r2 != null) goto L18;
        r2 = PreserveAspectRatio.LETTERBOX;
    L18:
        Bitmap r15 = checkForImageDataURL(r14);
        if (r15 != null) goto L22;
        SVG.getFileResolver();
        return;
    L22:
        SVG.Box r3 = new SVG.Box(0.0f, 0.0f, r15.getWidth(), r15.getHeight());
        updateStyleForElement(this.state, r12);
        if (display() == true) goto L26;
        return;
    L26:
        if (visible() == false) goto L57;
        Matrix r4 = r12.transform;
        if (r4 == null) goto L31;
        this.canvas.concat(r4);
    L31:
        SVG.Length r42 = r12.x;
        if (r42 == null) goto L34;
        float r43 = r42.floatValueX(this);
    L35:
        SVG.Length r5 = r12.y;
        if (r5 == null) goto L38;
        float r52 = r5.floatValueY(this);
    L39:
        float r7 = r12.width.floatValueX(this);
        float r8 = r12.height.floatValueX(this);
        this.state.viewPort = new SVG.Box(r43, r52, r7, r8);
        if (this.state.style.overflow.booleanValue() == true) goto L42;
        SVG.Box r44 = this.state.viewPort;
        setClipRect(r44.minX, r44.minY, r44.width, r44.height);
    L42:
        r12.boundingBox = this.state.viewPort;
        updateParentBoundingBox(r12);
        checkForClipPath(r12);
        boolean r45 = pushLayer();
        viewportFill();
        this.canvas.save();
        this.canvas.concat(calculateViewBoxTransform(this.state.viewPort, r3, r2));
        if (this.state.style.imageRendering == SVG.Style.RenderQuality.optimizeSpeed) goto L46;
        r0 = 2;
    L46:
        this.canvas.drawBitmap(r15, 0.0f, 0.0f, new Paint(r0));
        this.canvas.restore();
        if (r45 == false) goto L53;
        popLayer(r12);
        return;
    L53:
        return;
    L38:
        r52 = 0.0f;
        goto L39
    L34:
        r43 = 0.0f;
        goto L35
    L57:
        return;
    L55:
        return;
    L54:
        return;
    }

    private void render(SVG.Line r3) {
        debug("Line render", new Object[0]);
        updateStyleForElement(this.state, r3);
        if (display() == true) goto L6;
        return;
    L6:
        if (visible() == true) goto L9;
        return;
    L9:
        if (this.state.hasStroke == false) goto L21;
        Matrix r0 = r3.transform;
        if (r0 == null) goto L14;
        this.canvas.concat(r0);
    L14:
        Path r02 = makePathAndBoundingBox(r3);
        updateParentBoundingBox(r3);
        checkForGradientsAndPatterns(r3);
        checkForClipPath(r3);
        boolean r1 = pushLayer();
        doStroke(r02);
        renderMarkers(r3);
        if (r1 == false) goto L18;
        popLayer(r3);
        return;
    L18:
        return;
    }

    private void render(SVG.Path r4) {
        debug("Path render", new Object[0]);
        if (r4.d == null) goto L33;
        updateStyleForElement(this.state, r4);
        if (display() == true) goto L9;
        return;
    L9:
        if (visible() == false) goto L35;
        RendererState r0 = this.state;
        if (r0.hasStroke == false) goto L14;
    L16:
        Matrix r02 = r4.transform;
        if (r02 == null) goto L19;
        this.canvas.concat(r02);
    L19:
        Path r03 = new PathConverter(this, r4.d).getPath();
        if (r4.boundingBox != null) goto L22;
        r4.boundingBox = calculatePathBounds(r03);
    L22:
        updateParentBoundingBox(r4);
        checkForGradientsAndPatterns(r4);
        checkForClipPath(r4);
        boolean r1 = pushLayer();
        if (this.state.hasFill == false) goto L26;
        r03.setFillType(getFillTypeFromState());
        doFilledPath(r4, r03);
    L26:
        if (this.state.hasStroke == false) goto L28;
        doStroke(r03);
    L28:
        renderMarkers(r4);
        if (r1 == false) goto L32;
        popLayer(r4);
        return;
    L32:
        return;
    L14:
        if (r0.hasFill == true) goto L16;
        return;
    L35:
        return;
    }

    private void render(SVG.PolyLine r4) {
        debug("PolyLine render", new Object[0]);
        updateStyleForElement(this.state, r4);
        if (display() == true) goto L6;
        return;
    L6:
        if (visible() == false) goto L31;
        RendererState r0 = this.state;
        if (r0.hasStroke == false) goto L11;
    L13:
        Matrix r02 = r4.transform;
        if (r02 == null) goto L17;
        this.canvas.concat(r02);
    L17:
        if (r4.points.length < 2) goto L33;
        Path r03 = makePathAndBoundingBox(r4);
        updateParentBoundingBox(r4);
        r03.setFillType(getFillTypeFromState());
        checkForGradientsAndPatterns(r4);
        checkForClipPath(r4);
        boolean r1 = pushLayer();
        if (this.state.hasFill == false) goto L23;
        doFilledPath(r4, r03);
    L23:
        if (this.state.hasStroke == false) goto L25;
        doStroke(r03);
    L25:
        renderMarkers(r4);
        if (r1 == false) goto L29;
        popLayer(r4);
        return;
    L29:
        return;
    L33:
        return;
    L11:
        if (r0.hasFill == true) goto L13;
        return;
    }

    private void render(SVG.Polygon r4) {
        debug("Polygon render", new Object[0]);
        updateStyleForElement(this.state, r4);
        if (display() == true) goto L6;
        return;
    L6:
        if (visible() == false) goto L31;
        RendererState r0 = this.state;
        if (r0.hasStroke == false) goto L11;
    L13:
        Matrix r02 = r4.transform;
        if (r02 == null) goto L17;
        this.canvas.concat(r02);
    L17:
        if (r4.points.length < 2) goto L33;
        Path r03 = makePathAndBoundingBox(r4);
        updateParentBoundingBox(r4);
        checkForGradientsAndPatterns(r4);
        checkForClipPath(r4);
        boolean r1 = pushLayer();
        if (this.state.hasFill == false) goto L23;
        doFilledPath(r4, r03);
    L23:
        if (this.state.hasStroke == false) goto L25;
        doStroke(r03);
    L25:
        renderMarkers(r4);
        if (r1 == false) goto L29;
        popLayer(r4);
        return;
    L29:
        return;
    L33:
        return;
    L11:
        if (r0.hasFill == true) goto L13;
        return;
    }

    private void render(SVG.Rect r4) {
        debug("Rect render", new Object[0]);
        SVG.Length r0 = r4.width;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r4.height != null) goto L7;
        return;
    L7:
        if (r0.isZero() == false) goto L9;
        return;
    L9:
        if (r4.height.isZero() == true) goto L33;
        updateStyleForElement(this.state, r4);
        if (display() == true) goto L15;
        return;
    L15:
        if (visible() == false) goto L35;
        Matrix r02 = r4.transform;
        if (r02 == null) goto L20;
        this.canvas.concat(r02);
    L20:
        Path r03 = makePathAndBoundingBox(r4);
        updateParentBoundingBox(r4);
        checkForGradientsAndPatterns(r4);
        checkForClipPath(r4);
        boolean r1 = pushLayer();
        if (this.state.hasFill == false) goto L24;
        doFilledPath(r4, r03);
    L24:
        if (this.state.hasStroke == false) goto L26;
        doStroke(r03);
    L26:
        if (r1 == false) goto L32;
        popLayer(r4);
        return;
    L32:
        return;
    L35:
        return;
    }

    private void render(SVG.Svg r5) {
        render(r5, makeViewPort(r5.x, r5.y, r5.width, r5.height), r5.viewBox, r5.preserveAspectRatio);
    }

    private void render(SVG.Svg r3, SVG.Box r4) {
        render(r3, r4, r3.viewBox, r3.preserveAspectRatio);
    }

    private void render(SVG.Svg r4, SVG.Box r5, SVG.Box r6, PreserveAspectRatio r7) {
        debug("Svg render", new Object[0]);
        if (r5.width != 0.0f) goto L5;
        return;
    L5:
        if (r5.height == 0.0f) goto L28;
        if (r7 != null) goto L12;
        r7 = r4.preserveAspectRatio;
        if (r7 != null) goto L12;
        r7 = PreserveAspectRatio.LETTERBOX;
    L12:
        updateStyleForElement(this.state, r4);
        if (display() == false) goto L29;
        RendererState r0 = this.state;
        r0.viewPort = r5;
        if (r0.style.overflow.booleanValue() == true) goto L18;
        SVG.Box r52 = this.state.viewPort;
        setClipRect(r52.minX, r52.minY, r52.width, r52.height);
    L18:
        checkForClipPath(r4, this.state.viewPort);
        Canvas r53 = this.canvas;
        if (r6 == null) goto L21;
        r53.concat(calculateViewBoxTransform(this.state.viewPort, r6, r7));
        RendererState r54 = this.state;
        r54.viewBox = r4.viewBox;
    L22:
        boolean r55 = pushLayer();
        viewportFill();
        renderChildren(r4, true);
        if (r55 == false) goto L25;
        popLayer(r4);
    L25:
        updateParentBoundingBox(r4);
        return;
    L21:
        SVG.Box r62 = this.state.viewPort;
        r53.translate(r62.minX, r62.minY);
        goto L22
    L29:
        return;
    }

    private void render(SVG.SvgObject r2) {
        if ((r2 instanceof SVG.NotDirectlyRendered) == false) goto L5;
        return;
    L5:
        statePush();
        checkXMLSpaceAttribute(r2);
        if ((r2 instanceof SVG.Svg) == false) goto L9;
        render((SVG.Svg) r2);
    L44:
        statePop();
        return;
    L9:
        if ((r2 instanceof SVG.Use) == false) goto L12;
        render((SVG.Use) r2);
        goto L44
    L12:
        if ((r2 instanceof SVG.Switch) == false) goto L15;
        render((SVG.Switch) r2);
        goto L44
    L15:
        if ((r2 instanceof SVG.Group) == false) goto L18;
        render((SVG.Group) r2);
        goto L44
    L18:
        if ((r2 instanceof SVG.Image) == false) goto L21;
        render((SVG.Image) r2);
        goto L44
    L21:
        if ((r2 instanceof SVG.Path) == false) goto L24;
        render((SVG.Path) r2);
        goto L44
    L24:
        if ((r2 instanceof SVG.Rect) == false) goto L27;
        render((SVG.Rect) r2);
        goto L44
    L27:
        if ((r2 instanceof SVG.Circle) == false) goto L30;
        render((SVG.Circle) r2);
        goto L44
    L30:
        if ((r2 instanceof SVG.Ellipse) == false) goto L33;
        render((SVG.Ellipse) r2);
        goto L44
    L33:
        if ((r2 instanceof SVG.Line) == false) goto L36;
        render((SVG.Line) r2);
        goto L44
    L36:
        if ((r2 instanceof SVG.Polygon) == false) goto L39;
        render((SVG.Polygon) r2);
        goto L44
    L39:
        if ((r2 instanceof SVG.PolyLine) == false) goto L42;
        render((SVG.PolyLine) r2);
        goto L44
    L42:
        if ((r2 instanceof SVG.Text) == false) goto L44;
        render((SVG.Text) r2);
        goto L44
    }

    private void render(SVG.Switch r3) {
        debug("Switch render", new Object[0]);
        updateStyleForElement(this.state, r3);
        if (display() == true) goto L5;
        return;
    L5:
        Matrix r0 = r3.transform;
        if (r0 == null) goto L8;
        this.canvas.concat(r0);
    L8:
        checkForClipPath(r3);
        boolean r02 = pushLayer();
        renderSwitchChild(r3);
        if (r02 == false) goto L11;
        popLayer(r3);
    L11:
        updateParentBoundingBox(r3);
    }

    private void render(SVG.Symbol r5, SVG.Box r6) {
        debug("Symbol render", new Object[0]);
        if (r6.width != 0.0f) goto L5;
        return;
    L5:
        if (r6.height == 0.0f) goto L24;
        PreserveAspectRatio r0 = r5.preserveAspectRatio;
        if (r0 != null) goto L11;
        r0 = PreserveAspectRatio.LETTERBOX;
    L11:
        updateStyleForElement(this.state, r5);
        RendererState r1 = this.state;
        r1.viewPort = r6;
        if (r1.style.overflow.booleanValue() == true) goto L14;
        SVG.Box r62 = this.state.viewPort;
        setClipRect(r62.minX, r62.minY, r62.width, r62.height);
    L14:
        SVG.Box r63 = r5.viewBox;
        Canvas r12 = this.canvas;
        if (r63 == null) goto L17;
        r12.concat(calculateViewBoxTransform(this.state.viewPort, r63, r0));
        RendererState r64 = this.state;
        r64.viewBox = r5.viewBox;
    L18:
        boolean r65 = pushLayer();
        renderChildren(r5, true);
        if (r65 == false) goto L21;
        popLayer(r5);
    L21:
        updateParentBoundingBox(r5);
        return;
    L17:
        SVG.Box r66 = this.state.viewPort;
        r12.translate(r66.minX, r66.minY);
        goto L18
    }

    private void render(SVG.Text r10) {
        debug("Text render", new Object[0]);
        updateStyleForElement(this.state, r10);
        if (display() == false) goto L49;
        Matrix r1 = r10.transform;
        if (r1 == null) goto L8;
        this.canvas.concat(r1);
    L8:
        List<SVG.Length> r12 = r10.x;
        float r2 = 0.0f;
        if (r12 != null) goto L11;
    L14:
        float r13 = 0.0f;
    L15:
        List<SVG.Length> r3 = r10.y;
        if (r3 != null) goto L18;
    L21:
        float r32 = 0.0f;
    L22:
        List<SVG.Length> r4 = r10.dx;
        if (r4 != null) goto L25;
    L28:
        float r42 = 0.0f;
    L29:
        List<SVG.Length> r5 = r10.dy;
        if (r5 != null) goto L32;
    L35:
        SVG.Style.TextAnchor r0 = getAnchorPosition();
        if (r0 == SVG.Style.TextAnchor.Start) goto L42;
        float r52 = calculateTextWidth(r10);
        if (r0 != SVG.Style.TextAnchor.Middle) goto L40;
        r52 = r52 / 2.0f;
    L40:
        r13 = r13 - r52;
    L42:
        if (r10.boundingBox != null) goto L44;
        TextBoundsCalculator r02 = new TextBoundsCalculator(this, r13, r32);
        enumerateTextSpans(r10, r02);
        RectF r6 = r02.bbox;
        r10.boundingBox = new SVG.Box(r6.left, r6.top, r6.width(), r02.bbox.height());
    L44:
        updateParentBoundingBox(r10);
        checkForGradientsAndPatterns(r10);
        checkForClipPath(r10);
        boolean r03 = pushLayer();
        enumerateTextSpans(r10, new PlainTextDrawer(this, r13 + r42, r32 + r2));
        if (r03 == false) goto L48;
        popLayer(r10);
        return;
    L48:
        return;
    L32:
        if (r5.size() == 0) goto L35;
        r2 = r10.dy.get(0).floatValueY(this);
        goto L35
    L25:
        if (r4.size() == 0) goto L28;
        r42 = r10.dx.get(0).floatValueX(this);
        goto L29
    L18:
        if (r3.size() == 0) goto L21;
        r32 = r10.y.get(0).floatValueY(this);
        goto L22
    L11:
        if (r12.size() == 0) goto L14;
        r13 = r10.x.get(0).floatValueX(this);
        goto L15
    }

    private void render(SVG.Use r8) {
        debug("Use render", new Object[0]);
        SVG.Length r0 = r8.width;
        if (r0 != null) goto L5;
    L6:
        SVG.Length r02 = r8.height;
        if (r02 != null) goto L9;
    L11:
        updateStyleForElement(this.state, r8);
        if (display() == false) goto L48;
        SVG.SvgObject r03 = r8.document.resolveIRI(r8.href);
        if (r03 != null) goto L18;
        error("Use reference '%s' not found", new Object[]{r8.href});
        return;
    L18:
        Matrix r1 = r8.transform;
        if (r1 == null) goto L21;
        this.canvas.concat(r1);
    L21:
        SVG.Length r12 = r8.x;
        float r2 = 0.0f;
        if (r12 == null) goto L24;
        float r13 = r12.floatValueX(this);
    L25:
        SVG.Length r3 = r8.y;
        if (r3 == null) goto L28;
        r2 = r3.floatValueY(this);
    L28:
        this.canvas.translate(r13, r2);
        checkForClipPath(r8);
        boolean r14 = pushLayer();
        parentPush(r8);
        if ((r03 instanceof SVG.Svg) == false) goto L32;
        SVG.Box r22 = makeViewPort(null, null, r8.width, r8.height);
        statePush();
        render((SVG.Svg) r03, r22);
        statePop();
    L43:
        parentPop();
        if (r14 == false) goto L46;
        popLayer(r8);
    L46:
        updateParentBoundingBox(r8);
        return;
    L32:
        if ((r03 instanceof SVG.Symbol) == false) goto L42;
        SVG.Length r23 = r8.width;
        if (r23 != null) goto L37;
        r23 = new SVG.Length(100.0f, SVG.Unit.percent);
    L37:
        SVG.Length r5 = r8.height;
        if (r5 != null) goto L41;
        r5 = new SVG.Length(100.0f, SVG.Unit.percent);
    L41:
        SVG.Box r24 = makeViewPort(null, null, r23, r5);
        statePush();
        render((SVG.Symbol) r03, r24);
        statePop();
        goto L43
    L42:
        render(r03);
        goto L43
    L24:
        r13 = 0.0f;
        goto L25
    L48:
        return;
    L9:
        if (r02.isZero() == false) goto L11;
        return;
    L5:
        if (r0.isZero() == false) goto L6;
    }

    private void renderChildren(SVG.SvgContainer r2, boolean r3) {
        if (r3 == false) goto L4;
        parentPush(r2);
    L4:
        Iterator<SVG.SvgObject> r22 = r2.getChildren().iterator();
    L6:
        if (r22.hasNext() == false) goto L8;
        render(r22.next());
        goto L6
    L8:
        if (r3 == false) goto L12;
        parentPop();
        return;
    }

    private void renderMarker(SVG.Marker r12, MarkerVector r13) {
        statePush();
        Float r0 = r12.orient;
        float r1 = 0.0f;
        if (r0 != null) goto L5;
    L12:
        float r02 = 0.0f;
    L14:
        if (r12.markerUnitsAreUser == false) goto L16;
        float r2 = 1.0f;
    L17:
        this.state = findInheritFromAncestorState(r12);
        Matrix r3 = new Matrix();
        r3.preTranslate(r13.x, r13.y);
        r3.preRotate(r02);
        r3.preScale(r2, r2);
        SVG.Length r132 = r12.refX;
        if (r132 == null) goto L20;
        float r133 = r132.floatValueX(this);
    L21:
        SVG.Length r03 = r12.refY;
        if (r03 == null) goto L24;
        float r04 = r03.floatValueY(this);
    L25:
        SVG.Length r22 = r12.markerWidth;
        float r4 = 3.0f;
        if (r22 == null) goto L28;
        float r23 = r22.floatValueX(this);
    L29:
        SVG.Length r5 = r12.markerHeight;
        if (r5 == null) goto L32;
        r4 = r5.floatValueY(this);
    L32:
        SVG.Box r52 = r12.viewBox;
        if (r52 == null) goto L72;
        float r6 = r23 / r52.width;
        float r53 = r4 / r52.height;
        PreserveAspectRatio r7 = r12.preserveAspectRatio;
        if (r7 != null) goto L39;
        r7 = PreserveAspectRatio.LETTERBOX;
    L39:
        if (r7.equals(PreserveAspectRatio.STRETCH) == false) goto L41;
    L46:
        r3.preTranslate((-r133) * r6, (-r04) * r53);
        this.canvas.concat(r3);
        SVG.Box r134 = r12.viewBox;
        float r05 = r134.width * r6;
        float r135 = r134.height * r53;
        int[] r8 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$PreserveAspectRatio$Alignment;
        switch(r8[r7.getAlignment().ordinal()]) {
            case 1: goto L51;
            case 2: goto L51;
            case 3: goto L51;
            case 4: goto L49;
            case 5: goto L49;
            case 6: goto L49;
            default: goto L48;
        };
    L48:
        float r06 = 0.0f;
    L52:
        int r72 = r8[r7.getAlignment().ordinal()];
        if (r72 != 2) goto L55;
    L67:
        float r136 = (r4 - r135) / 2.0f;
    L66:
        r1 = 0.0f - r136;
    L69:
        if (this.state.style.overflow.booleanValue() == true) goto L71;
        setClipRect(r06, r1, r23, r4);
    L71:
        r3.reset();
        r3.preScale(r6, r53);
        this.canvas.concat(r3);
    L75:
        boolean r137 = pushLayer();
        renderChildren(r12, false);
        if (r137 == false) goto L78;
        popLayer(r12);
    L78:
        statePop();
        return;
    L55:
        if (r72 != 3) goto L57;
    L65:
        r136 = r4 - r135;
        goto L66
    L57:
        if (r72 == 5) goto L67;
        if (r72 == 6) goto L65;
        if (r72 == 7) goto L67;
        if (r72 == 8) goto L65;
    L49:
        float r07 = r23 - r05;
    L50:
        r06 = 0.0f - r07;
        goto L52
    L51:
        r07 = (r23 - r05) / 2.0f;
        goto L50
    L41:
        if (r7.getScale() != PreserveAspectRatio.Scale.slice) goto L44;
        float r54 = Math.max(r6, r53);
    L43:
        r6 = r54;
        r53 = r6;
        goto L46
    L44:
        r54 = Math.min(r6, r53);
        goto L43
    L72:
        r3.preTranslate(-r133, -r04);
        this.canvas.concat(r3);
        if (this.state.style.overflow.booleanValue() == true) goto L75;
        setClipRect(0.0f, 0.0f, r23, r4);
        goto L75
    L28:
        r23 = 3.0f;
        goto L29
    L24:
        r04 = 0.0f;
        goto L25
    L20:
        r133 = 0.0f;
        goto L21
    L16:
        r2 = this.state.style.strokeWidth.floatValue(this.dpi);
        goto L17
    L5:
        if (Float.isNaN(r0.floatValue()) == false) goto L11;
        float r08 = r13.dx;
        if (r08 == 0.0f) goto L9;
    L10:
        r02 = (float) Math.toDegrees(Math.atan2(r13.dy, r08));
        goto L14
    L9:
        if (r13.dy == 0.0f) goto L12;
    L11:
        r02 = r12.orient.floatValue();
        goto L14
    }

    private void renderMarkers(SVG.GraphicsElement r10) {
        SVG.Style r0 = this.state.style;
        String r1 = r0.markerStart;
        if (r1 != null) goto L10;
        if (r0.markerMid != null) goto L10;
        if (r0.markerEnd != null) goto L10;
        return;
    L10:
        if (r1 == null) goto L15;
        SVG.SvgObject r12 = r10.document.resolveIRI(r1);
        if (r12 == null) goto L14;
        SVG.Marker r13 = (SVG.Marker) r12;
    L16:
        String r3 = this.state.style.markerMid;
        if (r3 == null) goto L22;
        SVG.SvgObject r32 = r10.document.resolveIRI(r3);
        if (r32 == null) goto L21;
        SVG.Marker r33 = (SVG.Marker) r32;
    L23:
        String r4 = this.state.style.markerEnd;
        if (r4 == null) goto L29;
        SVG.SvgObject r42 = r10.document.resolveIRI(r4);
        if (r42 == null) goto L28;
        SVG.Marker r43 = (SVG.Marker) r42;
    L31:
        if ((r10 instanceof SVG.Path) == false) goto L34;
        List<MarkerVector> r102 = new MarkerPositionCalculator(this, ((SVG.Path) r10).d).getMarkers();
    L37:
        if (r102 == null) goto L65;
        int r02 = r102.size();
        if (r02 == 0) goto L66;
        SVG.Style r5 = this.state.style;
        r5.markerEnd = null;
        r5.markerMid = null;
        r5.markerStart = null;
        if (r13 == null) goto L46;
        renderMarker(r13, r102.get(0));
    L46:
        if (r33 != null) goto L48;
    L57:
        if (r43 == null) goto L63;
        renderMarker(r43, r102.get(r02 - 1));
        return;
    L63:
        return;
    L48:
        if (r102.size() <= 2) goto L57;
        MarkerVector r2 = r102.get(0);
        MarkerVector r52 = r102.get(1);
        int r6 = 1;
    L51:
        if (r6 >= (r02 - 1)) goto L57;
        r6 = r6 + 1;
        MarkerVector r7 = r102.get(r6);
        if (r52.isAmbiguous == false) goto L55;
        r2 = realignMarkerMid(r2, r52, r7);
    L56:
        renderMarker(r33, r2);
        r52 = r7;
        goto L51
    L55:
        r2 = r52;
        goto L56
    L66:
        return;
    L65:
        return;
    L34:
        if ((r10 instanceof SVG.Line) == false) goto L36;
        r102 = calculateMarkerPositions((SVG.Line) r10);
        goto L37
    L36:
        r102 = calculateMarkerPositions((SVG.PolyLine) r10);
        goto L37
    L28:
        error("Marker reference '%s' not found", new Object[]{this.state.style.markerEnd});
    L29:
        r43 = null;
        goto L31
    L21:
        error("Marker reference '%s' not found", new Object[]{this.state.style.markerMid});
    L22:
        r33 = null;
        goto L23
    L14:
        error("Marker reference '%s' not found", new Object[]{this.state.style.markerStart});
    L15:
        r13 = null;
        goto L16
    }

    private void renderMask(SVG.Mask r6, SVG.SvgElement r7, SVG.Box r8) {
        debug("Mask render", new Object[0]);
        Boolean r1 = r6.maskUnitsAreUser;
        if (r1 != null) goto L5;
    L14:
        SVG.Length r12 = r6.width;
        float r3 = 1.2f;
        if (r12 == null) goto L17;
        float r13 = r12.floatValue(this, 1.0f);
    L18:
        SVG.Length r4 = r6.height;
        if (r4 == null) goto L21;
        r3 = r4.floatValue(this, 1.0f);
    L21:
        float r14 = r13 * r8.width;
        float r32 = r3 * r8.height;
    L23:
        if (r14 != 0.0f) goto L25;
        return;
    L25:
        if (r32 == 0.0f) goto L39;
        statePush();
        RendererState r15 = findInheritFromAncestorState(r6);
        this.state = r15;
        r15.style.opacity = Float.valueOf(1.0f);
        boolean r16 = pushLayer();
        this.canvas.save();
        Boolean r2 = r6.maskContentUnitsAreUser;
        if (r2 != null) goto L30;
    L33:
        renderChildren(r6, false);
        this.canvas.restore();
        if (r16 == false) goto L36;
        popLayer(r7, r8);
    L36:
        statePop();
        return;
    L30:
        if (r2.booleanValue() == true) goto L33;
        this.canvas.translate(r8.minX, r8.minY);
        this.canvas.scale(r8.width, r8.height);
        goto L33
    L39:
        return;
    L17:
        r13 = 1.2f;
        goto L18
    L5:
        if (r1.booleanValue() == false) goto L14;
        SVG.Length r17 = r6.width;
        if (r17 == null) goto L9;
        r14 = r17.floatValueX(this);
    L10:
        SVG.Length r33 = r6.height;
        if (r33 == null) goto L13;
        r32 = r33.floatValueY(this);
        goto L23
    L13:
        r32 = r8.height;
        goto L23
    L9:
        r14 = r8.width;
        goto L10
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void renderSwitchChild(SVG.Switch r6) {
        String r0 = Locale.getDefault().getLanguage();
        SVG.getFileResolver();
        Iterator<SVG.SvgObject> r62 = r6.getChildren().iterator();
    L4:
        if (r62.hasNext() == false) goto L57;
        SVG.SvgObject r1 = r62.next();
        if ((r1 instanceof SVG.SvgConditional) == false) goto L4;
        SVG.SvgConditional r2 = (SVG.SvgConditional) r1;
        if (r2.getRequiredExtensions() != null) goto L4;
        Set<String> r3 = r2.getSystemLanguage();
        if (r3 == null) goto L18;
        if (r3.isEmpty() == true) goto L4;
        if (r3.contains(r0) == false) goto L4;
    L18:
        Set<String> r32 = r2.getRequiredFeatures();
        if (r32 == null) goto L28;
        if (supportedFeatures != null) goto L24;
        initialiseSupportedFeaturesMap();
    L24:
        if (r32.isEmpty() == true) goto L4;
        if (supportedFeatures.containsAll(r32) == false) goto L4;
    L28:
        Set<String> r33 = r2.getRequiredFormats();
        if (r33 != null) goto L30;
        Set<String> r22 = r2.getRequiredFonts();
        if (r22 == null) goto L34;
        r22.isEmpty();
        goto L4
    L34:
        render(r1);
        return;
    L30:
        r33.isEmpty();
        goto L4
    }

    private void renderTextPath(SVG.TextPath r7) {
        debug("TextPath render", new Object[0]);
        updateStyleForElement(this.state, r7);
        if (display() == true) goto L6;
        return;
    L6:
        if (visible() == false) goto L31;
        SVG.SvgObject r1 = r7.document.resolveIRI(r7.href);
        if (r1 != null) goto L12;
        error("TextPath reference '%s' not found", new Object[]{r7.href});
        return;
    L12:
        SVG.Path r12 = (SVG.Path) r1;
        Path r2 = new PathConverter(this, r12.d).getPath();
        Matrix r13 = r12.transform;
        if (r13 == null) goto L15;
        r2.transform(r13);
    L15:
        PathMeasure r14 = new PathMeasure(r2, false);
        SVG.Length r0 = r7.startOffset;
        if (r0 == null) goto L18;
        float r02 = r0.floatValue(this, r14.getLength());
    L19:
        SVG.Style.TextAnchor r15 = getAnchorPosition();
        if (r15 == SVG.Style.TextAnchor.Start) goto L25;
        float r4 = calculateTextWidth(r7);
        if (r15 != SVG.Style.TextAnchor.Middle) goto L24;
        r4 = r4 / 2.0f;
    L24:
        r02 = r02 - r4;
    L25:
        checkForGradientsAndPatterns((SVG.SvgElement) r7.getTextRoot());
        boolean r16 = pushLayer();
        enumerateTextSpans(r7, new PathTextDrawer(this, r2, r02, 0.0f));
        if (r16 == false) goto L29;
        popLayer(r7);
        return;
    L29:
        return;
    L18:
        r02 = 0.0f;
        goto L19
    }

    private boolean requiresCompositing() {
        if (this.state.style.opacity.floatValue() >= 1.0f) goto L5;
        return true;
    L5:
        if (this.state.style.mask != null) goto L11;
        return false;
    L11:
        return true;
    }

    private void resetState() {
        this.state = new RendererState(this);
        this.stateStack = new Stack();
        updateStyle(this.state, SVG.Style.getDefaultStyle());
        RendererState r0 = this.state;
        r0.viewPort = null;
        r0.spacePreserve = false;
        this.stateStack.push(new RendererState(this, r0));
        this.matrixStack = new Stack();
        this.parentStack = new Stack();
    }

    private void setClipRect(float r2, float r3, float r4, float r5) {
        float r42 = r4 + r2;
        float r52 = r5 + r3;
        SVG.CSSClipRect r0 = this.state.style.clip;
        if (r0 == null) goto L5;
        r2 = r2 + r0.left.floatValueX(this);
        r3 = r3 + this.state.style.clip.top.floatValueY(this);
        r42 = r42 - this.state.style.clip.right.floatValueX(this);
        r52 = r52 - this.state.style.clip.bottom.floatValueY(this);
    L5:
        this.canvas.clipRect(r2, r3, r42, r52);
    }

    private void setPaintColour(RendererState r2, boolean r3, SVG.SvgPaint r4) {
        SVG.Style r1 = r2.style;
        if (r3 == false) goto L5;
        Float r12 = r1.fillOpacity;
    L6:
        float r13 = r12.floatValue();
        if ((r4 instanceof SVG.Colour) == false) goto L10;
        int r42 = ((SVG.Colour) r4).colour;
    L12:
        int r14 = colourWithOpacity(r42, r13);
        if (r3 == false) goto L16;
        r2.fillPaint.setColor(r14);
        return;
    L16:
        r2.strokePaint.setColor(r14);
        return;
    L10:
        if ((r4 instanceof SVG.CurrentColor) == false) goto L18;
        r42 = r2.style.color.colour;
        goto L12
    L18:
        return;
    L5:
        r12 = r1.strokeOpacity;
        goto L6
    }

    private void setSolidColor(boolean r10, SVG.SolidColor r11) {
        boolean r4 = false;
        if (r10 == false) goto L18;
        if (isSpecified(r11.baseStyle, 2147483648L) == false) goto L11;
        RendererState r6 = this.state;
        SVG.Style r7 = r6.style;
        SVG.SvgPaint r8 = r11.baseStyle.solidColor;
        r7.fill = r8;
        if (r8 == null) goto L9;
        r4 = true;
    L9:
        r6.hasFill = r4;
    L11:
        if (isSpecified(r11.baseStyle, 4294967296L) == false) goto L14;
        this.state.style.fillOpacity = r11.baseStyle.solidOpacity;
    L14:
        if (isSpecified(r11.baseStyle, 6442450944L) == false) goto L30;
        RendererState r112 = this.state;
        setPaintColour(r112, r10, r112.style.fill);
        return;
    L30:
        return;
    L18:
        if (isSpecified(r11.baseStyle, 2147483648L) == false) goto L24;
        RendererState r62 = this.state;
        SVG.Style r72 = r62.style;
        SVG.SvgPaint r82 = r11.baseStyle.solidColor;
        r72.stroke = r82;
        if (r82 == null) goto L22;
        r4 = true;
    L22:
        r62.hasStroke = r4;
    L24:
        if (isSpecified(r11.baseStyle, 4294967296L) == false) goto L27;
        this.state.style.strokeOpacity = r11.baseStyle.solidOpacity;
    L27:
        if (isSpecified(r11.baseStyle, 6442450944L) == false) goto L31;
        RendererState r113 = this.state;
        setPaintColour(r113, r10, r113.style.stroke);
        return;
    }

    private void statePop() {
        this.canvas.restore();
        this.state = this.stateStack.pop();
    }

    private void statePush() {
        this.canvas.save();
        this.stateStack.push(this.state);
        this.state = new RendererState(this, this.state);
    }

    private String textXMLSpaceTransform(String r3, boolean r4, boolean r5) {
        if (this.state.spacePreserve == true) goto L5;
        String r2 = r3.replaceAll("\\n", _UrlKt.FRAGMENT_ENCODE_SET).replaceAll("\\t", " ");
        if (r4 == false) goto L9;
        r2 = r2.replaceAll("^\\s+", _UrlKt.FRAGMENT_ENCODE_SET);
    L9:
        if (r5 == false) goto L12;
        r2 = r2.replaceAll("\\s+$", _UrlKt.FRAGMENT_ENCODE_SET);
    L12:
        return r2.replaceAll("\\s{2,}", " ");
    L5:
        return r3.replaceAll("[\\n\\t]", " ");
    }

    private void updateParentBoundingBox(SVG.SvgElement r11) {
        if (r11.parent != null) goto L6;
        return;
    L6:
        if (r11.boundingBox == null) goto L36;
        Matrix r0 = new Matrix();
        if (this.matrixStack.peek().invert(r0) == false) goto L34;
        SVG.Box r1 = r11.boundingBox;
        float r2 = r1.minX;
        float r3 = r1.minY;
        float r12 = r1.maxX();
        SVG.Box r4 = r11.boundingBox;
        float r5 = r4.minY;
        float r42 = r4.maxX();
        float r6 = r11.boundingBox.maxY();
        SVG.Box r112 = r11.boundingBox;
        int r32 = 2;
        float[] r8 = {r2, r3, r12, r5, r42, r6, r112.minX, r112.maxY()};
        r0.preConcat(this.canvas.getMatrix());
        r0.mapPoints(r8);
        float r02 = r8[0];
        float r22 = r8[1];
        RectF r113 = new RectF(r02, r22, r02, r22);
    L11:
        if (r32 > 6) goto L25;
        float r03 = r8[r32];
        if (r03 >= r113.left) goto L16;
        r113.left = r03;
    L16:
        if (r03 <= r113.right) goto L18;
        r113.right = r03;
    L18:
        float r04 = r8[r32 + 1];
        if (r04 >= r113.top) goto L22;
        r113.top = r04;
    L22:
        if (r04 <= r113.bottom) goto L24;
        r113.bottom = r04;
    L24:
        r32 = r32 + 2;
        goto L11
    L25:
        SVG.SvgElement r10 = (SVG.SvgElement) this.parentStack.peek();
        SVG.Box r05 = r10.boundingBox;
        float r13 = r113.left;
        if (r05 != null) goto L29;
        r10.boundingBox = SVG.Box.fromLimits(r13, r113.top, r113.right, r113.bottom);
        return;
    L29:
        r05.union(SVG.Box.fromLimits(r13, r113.top, r113.right, r113.bottom));
        return;
    L34:
        return;
    }

    private void updateStyle(RendererState r12, SVG.Style r13) {
        if (isSpecified(r13, MediaStatus.COMMAND_EDIT_TRACKS) == false) goto L6;
        r12.style.color = r13.color;
    L6:
        if (isSpecified(r13, MediaStatus.COMMAND_QUEUE_REPEAT_ONE) == false) goto L8;
        r12.style.opacity = r13.opacity;
    L8:
        boolean r1 = false;
        if (isSpecified(r13, 1) == false) goto L18;
        r12.style.fill = r13.fill;
        SVG.SvgPaint r0 = r13.fill;
        if (r0 != null) goto L13;
    L15:
        boolean r02 = false;
    L16:
        r12.hasFill = r02;
        goto L18
    L13:
        if (r0 == SVG.Colour.TRANSPARENT) goto L15;
        r02 = true;
    L18:
        if (isSpecified(r13, 4) == false) goto L21;
        r12.style.fillOpacity = r13.fillOpacity;
    L21:
        if (isSpecified(r13, 6149) == false) goto L24;
        setPaintColour(r12, true, r12.style.fill);
    L24:
        if (isSpecified(r13, 2) == false) goto L27;
        r12.style.fillRule = r13.fillRule;
    L27:
        if (isSpecified(r13, 8) == false) goto L36;
        r12.style.stroke = r13.stroke;
        SVG.SvgPaint r03 = r13.stroke;
        if (r03 != null) goto L31;
    L33:
        boolean r04 = false;
    L34:
        r12.hasStroke = r04;
        goto L36
    L31:
        if (r03 == SVG.Colour.TRANSPARENT) goto L33;
        r04 = true;
    L36:
        if (isSpecified(r13, 16) == false) goto L39;
        r12.style.strokeOpacity = r13.strokeOpacity;
    L39:
        if (isSpecified(r13, 6168) == false) goto L42;
        setPaintColour(r12, false, r12.style.stroke);
    L42:
        if (isSpecified(r13, 34359738368L) == false) goto L45;
        r12.style.vectorEffect = r13.vectorEffect;
    L45:
        if (isSpecified(r13, 32) == false) goto L48;
        SVG.Style r05 = r12.style;
        SVG.Length r3 = r13.strokeWidth;
        r05.strokeWidth = r3;
        r12.strokePaint.setStrokeWidth(r3.floatValue(this));
    L48:
        if (isSpecified(r13, 64) == false) goto L58;
        r12.style.strokeLineCap = r13.strokeLineCap;
        int r06 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVG$Style$LineCap[r13.strokeLineCap.ordinal()];
        if (r06 == 1) goto L56;
        if (r06 == 2) goto L55;
        if (r06 != 3) goto L58;
        r12.strokePaint.setStrokeCap(Paint.Cap.SQUARE);
        goto L58
    L55:
        r12.strokePaint.setStrokeCap(Paint.Cap.ROUND);
        goto L58
    L56:
        r12.strokePaint.setStrokeCap(Paint.Cap.BUTT);
    L58:
        if (isSpecified(r13, 128) == false) goto L68;
        r12.style.strokeLineJoin = r13.strokeLineJoin;
        int r07 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVG$Style$LineJoin[r13.strokeLineJoin.ordinal()];
        if (r07 == 1) goto L66;
        if (r07 == 2) goto L65;
        if (r07 != 3) goto L68;
        r12.strokePaint.setStrokeJoin(Paint.Join.BEVEL);
        goto L68
    L65:
        r12.strokePaint.setStrokeJoin(Paint.Join.ROUND);
        goto L68
    L66:
        r12.strokePaint.setStrokeJoin(Paint.Join.MITER);
    L68:
        if (isSpecified(r13, 256) == false) goto L71;
        r12.style.strokeMiterLimit = r13.strokeMiterLimit;
        r12.strokePaint.setStrokeMiter(r13.strokeMiterLimit.floatValue());
    L71:
        if (isSpecified(r13, 512) == false) goto L74;
        r12.style.strokeDashArray = r13.strokeDashArray;
    L74:
        if (isSpecified(r13, 1024) == false) goto L76;
        r12.style.strokeDashOffset = r13.strokeDashOffset;
    L76:
        Typeface r32 = null;
        if (isSpecified(r13, 1536) == false) goto L96;
        SVG.Length[] r08 = r12.style.strokeDashArray;
        if (r08 != null) goto L81;
        r12.strokePaint.setPathEffect(null);
        goto L96
    L81:
        int r09 = r08.length;
        if ((r09 % 2) != 0) goto L84;
        int r4 = r09;
    L85:
        float[] r5 = new float[r4];
        int r7 = 0;
        float r8 = 0.0f;
    L86:
        if (r7 >= r4) goto L89;
        float r9 = r12.style.strokeDashArray[r7 % r09].floatValue(this);
        r5[r7] = r9;
        r8 = r8 + r9;
        r7 = r7 + 1;
        goto L86
    L89:
        if (r8 != 0.0f) goto L91;
        r12.strokePaint.setPathEffect(null);
        goto L96
    L91:
        float r010 = r12.style.strokeDashOffset.floatValue(this);
        if (r010 >= 0.0f) goto L94;
        r010 = (r010 % r8) + r8;
    L94:
        r12.strokePaint.setPathEffect(new DashPathEffect(r5, r010));
        goto L96
    L84:
        r4 = r09 * 2;
    L96:
        if (isSpecified(r13, 16384) == false) goto L99;
        float r011 = getCurrentFontSize();
        r12.style.fontSize = r13.fontSize;
        r12.fillPaint.setTextSize(r13.fontSize.floatValue(this, r011));
        r12.strokePaint.setTextSize(r13.fontSize.floatValue(this, r011));
    L99:
        if (isSpecified(r13, MediaStatus.COMMAND_PLAYBACK_RATE) == false) goto L102;
        r12.style.fontFamily = r13.fontFamily;
    L102:
        if (isSpecified(r13, MediaStatus.COMMAND_DISLIKE) == false) goto L115;
        if (r13.fontWeight.intValue() != (-1)) goto L109;
        if (r12.style.fontWeight.intValue() <= 100) goto L109;
        SVG.Style r012 = r12.style;
        r012.fontWeight = Integer.valueOf(r012.fontWeight.intValue() - 100);
    L109:
        if (r13.fontWeight.intValue() == 1) goto L111;
    L113:
        r12.style.fontWeight = r13.fontWeight;
        goto L115
    L111:
        if (r12.style.fontWeight.intValue() >= 900) goto L113;
        SVG.Style r013 = r12.style;
        r013.fontWeight = Integer.valueOf(r013.fontWeight.intValue() + 100);
    L115:
        if (isSpecified(r13, MediaStatus.COMMAND_FOLLOW) == false) goto L118;
        r12.style.fontStyle = r13.fontStyle;
    L118:
        if (isSpecified(r13, 106496) == false) goto L132;
        if (r12.style.fontFamily != null) goto L122;
    L128:
        if (r32 != null) goto L130;
        SVG.Style r014 = r12.style;
        r32 = checkGenericFont("serif", r014.fontWeight, r014.fontStyle);
    L130:
        r12.fillPaint.setTypeface(r32);
        r12.strokePaint.setTypeface(r32);
        goto L132
    L122:
        if (this.document == null) goto L128;
        SVG.getFileResolver();
        Iterator<String> r015 = r12.style.fontFamily.iterator();
    L125:
        if (r015.hasNext() == false) goto L128;
        String r33 = r015.next();
        SVG.Style r42 = r12.style;
        r32 = checkGenericFont(r33, r42.fontWeight, r42.fontStyle);
        if (r32 == null) goto L125;
    L132:
        if (isSpecified(r13, MediaStatus.COMMAND_UNFOLLOW) == false) goto L150;
        r12.style.textDecoration = r13.textDecoration;
        Paint r016 = r12.fillPaint;
        SVG.Style.TextDecoration r34 = r13.textDecoration;
        SVG.Style.TextDecoration r43 = SVG.Style.TextDecoration.LineThrough;
        if (r34 != r43) goto L136;
        boolean r35 = true;
    L137:
        r016.setStrikeThruText(r35);
        Paint r017 = r12.fillPaint;
        SVG.Style.TextDecoration r36 = r13.textDecoration;
        SVG.Style.TextDecoration r52 = SVG.Style.TextDecoration.Underline;
        if (r36 != r52) goto L140;
        boolean r37 = true;
    L141:
        r017.setUnderlineText(r37);
        Paint r018 = r12.strokePaint;
        if (r13.textDecoration != r43) goto L144;
        boolean r38 = true;
    L145:
        r018.setStrikeThruText(r38);
        Paint r019 = r12.strokePaint;
        if (r13.textDecoration != r52) goto L148;
        r1 = true;
    L148:
        r019.setUnderlineText(r1);
        goto L150
    L144:
        r38 = false;
        goto L145
    L140:
        r37 = false;
        goto L141
    L136:
        r35 = false;
    L150:
        if (isSpecified(r13, 68719476736L) == false) goto L153;
        r12.style.direction = r13.direction;
    L153:
        if (isSpecified(r13, MediaStatus.COMMAND_STREAM_TRANSFER) == false) goto L156;
        r12.style.textAnchor = r13.textAnchor;
    L156:
        if (isSpecified(r13, 524288) == false) goto L159;
        r12.style.overflow = r13.overflow;
    L159:
        if (isSpecified(r13, 2097152) == false) goto L162;
        r12.style.markerStart = r13.markerStart;
    L162:
        if (isSpecified(r13, 4194304) == false) goto L165;
        r12.style.markerMid = r13.markerMid;
    L165:
        if (isSpecified(r13, 8388608) == false) goto L168;
        r12.style.markerEnd = r13.markerEnd;
    L168:
        if (isSpecified(r13, 16777216) == false) goto L171;
        r12.style.display = r13.display;
    L171:
        if (isSpecified(r13, 33554432) == false) goto L174;
        r12.style.visibility = r13.visibility;
    L174:
        if (isSpecified(r13, 1048576) == false) goto L177;
        r12.style.clip = r13.clip;
    L177:
        if (isSpecified(r13, 268435456) == false) goto L180;
        r12.style.clipPath = r13.clipPath;
    L180:
        if (isSpecified(r13, 536870912) == false) goto L183;
        r12.style.clipRule = r13.clipRule;
    L183:
        if (isSpecified(r13, 1073741824) == false) goto L186;
        r12.style.mask = r13.mask;
    L186:
        if (isSpecified(r13, 67108864) == false) goto L189;
        r12.style.stopColor = r13.stopColor;
    L189:
        if (isSpecified(r13, 134217728) == false) goto L192;
        r12.style.stopOpacity = r13.stopOpacity;
    L192:
        if (isSpecified(r13, 8589934592L) == false) goto L195;
        r12.style.viewportFill = r13.viewportFill;
    L195:
        if (isSpecified(r13, 17179869184L) == false) goto L198;
        r12.style.viewportFillOpacity = r13.viewportFillOpacity;
    L198:
        if (isSpecified(r13, 137438953472L) == false) goto L205;
        r12.style.imageRendering = r13.imageRendering;
        return;
    }

    private void updateStyleForElement(RendererState r5, SVG.SvgElementBase r6) {
        if (r6.parent != null) goto L5;
        boolean r0 = true;
    L6:
        r5.style.resetNonInheritingProperties(r0);
        SVG.Style r02 = r6.baseStyle;
        if (r02 == null) goto L10;
        updateStyle(r5, r02);
    L10:
        if (this.document.hasCSSRules() == false) goto L17;
        Iterator<CSSParser.Rule> r03 = this.document.getCSSRules().iterator();
    L13:
        if (r03.hasNext() == false) goto L17;
        CSSParser.Rule r1 = r03.next();
        if (CSSParser.ruleMatch(this.ruleMatchContext, r1.selector, r6) == false) goto L13;
        updateStyle(r5, r1.style);
    L17:
        SVG.Style r62 = r6.style;
        if (r62 == null) goto L26;
        updateStyle(r5, r62);
        return;
    L26:
        return;
    L5:
        r0 = false;
        goto L6
    }

    private void viewportFill() {
        SVG.Style r0 = this.state.style;
        SVG.SvgPaint r1 = r0.viewportFill;
        if ((r1 instanceof SVG.Colour) == false) goto L6;
        int r12 = ((SVG.Colour) r1).colour;
    L8:
        Float r02 = r0.viewportFillOpacity;
        if (r02 == null) goto L11;
        r12 = colourWithOpacity(r12, r02.floatValue());
    L11:
        this.canvas.drawColor(r12);
        return;
    L6:
        if ((r1 instanceof SVG.CurrentColor) == false) goto L13;
        r12 = r0.color.colour;
        goto L8
    }

    private boolean visible() {
        Boolean r0 = this.state.style.visibility;
        if (r0 != null) goto L5;
        return true;
    L5:
        return r0.booleanValue();
    }

    private static void warn(String r1, Object... r2) {
        Log.w("SVGAndroidRenderer", String.format(r1, r2));
    }

    public float getCurrentFontSize() {
        return this.state.fillPaint.getTextSize();
    }

    public float getCurrentFontXHeight() {
        return this.state.fillPaint.getTextSize() / 2.0f;
    }

    public SVG.Box getCurrentViewPortInUserUnits() {
        RendererState r1 = this.state;
        SVG.Box r0 = r1.viewBox;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return r1.viewPort;
    }

    public float getDPI() {
        return this.dpi;
    }

    public void renderDocument(SVG r7, RenderOptions r8) {
        if (r8 == null) goto L46;
        this.document = r7;
        SVG.Svg r0 = r7.getRootElement();
        if (r0 != null) goto L8;
        warn("Nothing to render. Document is empty.", new Object[0]);
        return;
    L8:
        if (r8.hasView() == false) goto L22;
        SVG.SvgElementBase r1 = this.document.getElementById(r8.viewId);
        if (r1 != null) goto L12;
    L19:
        Log.w("SVGAndroidRenderer", String.format("View element with id \"%s\" not found.", new Object[]{r8.viewId}));
        return;
    L12:
        if ((r1 instanceof SVG.View) == false) goto L19;
        SVG.View r12 = (SVG.View) r1;
        SVG.Box r3 = r12.viewBox;
        if (r3 != null) goto L18;
        Log.w("SVGAndroidRenderer", String.format("View element with id \"%s\" is missing a viewBox attribute.", new Object[]{r8.viewId}));
        return;
    L18:
        PreserveAspectRatio r13 = r12.preserveAspectRatio;
    L31:
        if (r8.hasCss() == false) goto L34;
        r7.addCSSRules(r8.css);
    L34:
        if (r8.hasTarget() == false) goto L36;
        CSSParser.RuleMatchContext r2 = new CSSParser.RuleMatchContext();
        this.ruleMatchContext = r2;
        r2.targetElement = r7.getElementById(r8.targetId);
    L36:
        resetState();
        checkXMLSpaceAttribute(r0);
        statePush();
        SVG.Box r22 = new SVG.Box(r8.viewPort);
        SVG.Length r4 = r0.width;
        if (r4 == null) goto L39;
        r22.width = r4.floatValue(this, r22.width);
    L39:
        SVG.Length r42 = r0.height;
        if (r42 == null) goto L42;
        r22.height = r42.floatValue(this, r22.height);
    L42:
        render(r0, r22, r3, r13);
        statePop();
        if (r8.hasCss() == false) goto L48;
        r7.clearRenderCSSRules();
        return;
    L48:
        return;
    L22:
        if (r8.hasViewBox() == false) goto L25;
        SVG.Box r14 = r8.viewBox;
    L24:
        r3 = r14;
        if (r8.hasPreserveAspectRatio() == false) goto L29;
        r13 = r8.preserveAspectRatio;
        goto L31
    L29:
        r13 = r0.preserveAspectRatio;
        goto L31
    L25:
        r14 = r0.viewBox;
        goto L24
    L46:
        f$$ExternalSyntheticBUOutline2.m("renderOptions shouldn't be null");
    }
}
