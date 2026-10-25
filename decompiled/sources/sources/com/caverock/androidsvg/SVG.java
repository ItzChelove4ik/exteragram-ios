package com.caverock.androidsvg;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import c.f$$ExternalSyntheticBUOutline1;
import com.caverock.androidsvg.CSSParser;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.MediaError;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public class SVG {
    private static boolean enableInternalEntities = true;
    private CSSParser.Ruleset cssRules;
    private String desc;
    private Map<String, SvgElementBase> idToElementMap;
    private float renderDPI;
    private Svg rootElement;
    private String title;

    /* renamed from: com.caverock.androidsvg.SVG$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$caverock$androidsvg$SVG$Unit = null;

        static {
            int[] r0 = new int[Unit.values().length];
            $SwitchMap$com$caverock$androidsvg$SVG$Unit = r0;
            r0[Unit.px.ordinal()] = 1;     // Catch: NoSuchFieldError -> L13
        L24:
            $SwitchMap$com$caverock$androidsvg$SVG$Unit[Unit.em.ordinal()] = 2;     // Catch: NoSuchFieldError -> L14
        L28:
            $SwitchMap$com$caverock$androidsvg$SVG$Unit[Unit.ex.ordinal()] = 3;     // Catch: NoSuchFieldError -> L15
        L38:
            $SwitchMap$com$caverock$androidsvg$SVG$Unit[Unit.in.ordinal()] = 4;     // Catch: NoSuchFieldError -> L16
        L26:
            $SwitchMap$com$caverock$androidsvg$SVG$Unit[Unit.cm.ordinal()] = 5;     // Catch: NoSuchFieldError -> L17
        L30:
            $SwitchMap$com$caverock$androidsvg$SVG$Unit[Unit.mm.ordinal()] = 6;     // Catch: NoSuchFieldError -> L18
        L32:
            $SwitchMap$com$caverock$androidsvg$SVG$Unit[Unit.pt.ordinal()] = 7;     // Catch: NoSuchFieldError -> L19
        L34:
            $SwitchMap$com$caverock$androidsvg$SVG$Unit[Unit.pc.ordinal()] = 8;     // Catch: NoSuchFieldError -> L20
        L22:
            $SwitchMap$com$caverock$androidsvg$SVG$Unit[Unit.percent.ordinal()] = 9;     // Catch: NoSuchFieldError -> L21
            return;
        }
    }

    /* loaded from: classes4.dex */
    public static class Box {
        float height;
        float minX;
        float minY;
        float width;

        public Box(float r1, float r2, float r3, float r4) {
            this.minX = r1;
            this.minY = r2;
            this.width = r3;
            this.height = r4;
        }

        public Box(Box r2) {
            this.minX = r2.minX;
            this.minY = r2.minY;
            this.width = r2.width;
            this.height = r2.height;
        }

        public static Box fromLimits(float r1, float r2, float r3, float r4) {
            return new Box(r1, r2, r3 - r1, r4 - r2);
        }

        public float maxX() {
            return this.minX + this.width;
        }

        public float maxY() {
            return this.minY + this.height;
        }

        public RectF toRectF() {
            return new RectF(this.minX, this.minY, maxX(), maxY());
        }

        public String toString() {
            return "[" + this.minX + " " + this.minY + " " + this.width + " " + this.height + "]";
        }

        public void union(Box r3) {
            float r0 = r3.minX;
            if (r0 >= this.minX) goto L5;
            this.minX = r0;
        L5:
            float r02 = r3.minY;
            if (r02 >= this.minY) goto L9;
            this.minY = r02;
        L9:
            if (r3.maxX() <= maxX()) goto L12;
            this.width = r3.maxX() - this.minX;
        L12:
            if (r3.maxY() <= maxY()) goto L15;
            this.height = r3.maxY() - this.minY;
            return;
        }
    }

    /* loaded from: classes4.dex */
    public static class CSSClipRect {
        Length bottom;
        Length left;
        Length right;
        Length top;

        public CSSClipRect(Length r1, Length r2, Length r3, Length r4) {
            this.top = r1;
            this.right = r2;
            this.bottom = r3;
            this.left = r4;
        }
    }

    /* loaded from: classes4.dex */
    public static class Circle extends GraphicsElement {
        Length cx;
        Length cy;
        Length r;

        public Circle() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "circle";
        }
    }

    /* loaded from: classes4.dex */
    public static class ClipPath extends Group implements NotDirectlyRendered {
        Boolean clipPathUnitsAreUser;

        public ClipPath() {
        }

        @Override // com.caverock.androidsvg.SVG.Group, com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "clipPath";
        }
    }

    /* loaded from: classes4.dex */
    public static class Colour extends SvgPaint {
        static final Colour BLACK = null;
        static final Colour TRANSPARENT = null;
        int colour;

        static {
            BLACK = new Colour(-16777216);
            TRANSPARENT = new Colour(0);
        }

        public Colour(int r1) {
            this.colour = r1;
        }

        public String toString() {
            return String.format("#%08x", new Object[]{Integer.valueOf(this.colour)});
        }
    }

    /* loaded from: classes4.dex */
    public static class CurrentColor extends SvgPaint {
        private static CurrentColor instance;

        static {
            instance = new CurrentColor();
        }

        private CurrentColor() {
        }

        public static CurrentColor getInstance() {
            return instance;
        }
    }

    /* loaded from: classes4.dex */
    public static class Defs extends Group implements NotDirectlyRendered {
        public Defs() {
        }

        @Override // com.caverock.androidsvg.SVG.Group, com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "defs";
        }
    }

    /* loaded from: classes4.dex */
    public static class Ellipse extends GraphicsElement {
        Length cx;
        Length cy;
        Length rx;
        Length ry;

        public Ellipse() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "ellipse";
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class GradientElement extends SvgElementBase implements SvgContainer {
        List<SvgObject> children;
        Matrix gradientTransform;
        Boolean gradientUnitsAreUser;
        String href;
        GradientSpread spreadMethod;

        public GradientElement() {
            this.children = new ArrayList();
        }

        @Override // com.caverock.androidsvg.SVG.SvgContainer
        public void addChild(SvgObject r2) {
            if ((r2 instanceof Stop) == false) goto L6;
            this.children.add(r2);
            return;
        L6:
            SVGParser$$ExternalSyntheticBUOutline2.m("Gradient elements cannot contain ", r2, " elements.");
        }

        @Override // com.caverock.androidsvg.SVG.SvgContainer
        public List<SvgObject> getChildren() {
            return this.children;
        }
    }

    /* loaded from: classes4.dex */
    public enum GradientSpread extends Enum<GradientSpread> {
        private static final /* synthetic */ GradientSpread[] $VALUES = null;
        public static final GradientSpread pad = null;
        public static final GradientSpread reflect = null;
        public static final GradientSpread repeat = null;

        static {
            GradientSpread r0 = new GradientSpread("pad", 0);
            pad = r0;
            GradientSpread r1 = new GradientSpread("reflect", 1);
            reflect = r1;
            GradientSpread r2 = new GradientSpread("repeat", 2);
            repeat = r2;
            $VALUES = new GradientSpread[]{r0, r1, r2};
        }

        GradientSpread(String r1, int r2) {
        }

        public static GradientSpread valueOf(String r1) {
            return (GradientSpread) Enum.valueOf(GradientSpread.class, r1);
        }

        public static GradientSpread[] values() {
            return (GradientSpread[]) $VALUES.clone();
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class GraphicsElement extends SvgConditionalElement implements HasTransform {
        Matrix transform;

        public GraphicsElement() {
        }

        @Override // com.caverock.androidsvg.SVG.HasTransform
        public void setTransform(Matrix r1) {
            this.transform = r1;
        }
    }

    /* loaded from: classes4.dex */
    public static class Group extends SvgConditionalContainer implements HasTransform {
        Matrix transform;

        public Group() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "group";
        }

        @Override // com.caverock.androidsvg.SVG.HasTransform
        public void setTransform(Matrix r1) {
            this.transform = r1;
        }
    }

    /* loaded from: classes4.dex */
    public interface HasTransform {
        void setTransform(Matrix r1);
    }

    /* loaded from: classes4.dex */
    public static class Image extends SvgPreserveAspectRatioContainer implements HasTransform {
        Length height;
        String href;
        Matrix transform;
        Length width;
        Length x;
        Length y;

        public Image() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "image";
        }

        @Override // com.caverock.androidsvg.SVG.HasTransform
        public void setTransform(Matrix r1) {
            this.transform = r1;
        }
    }

    /* loaded from: classes4.dex */
    public static class Length implements Cloneable {
        Unit unit;
        float value;

        public Length(float r1) {
            this.value = r1;
            this.unit = Unit.px;
        }

        public Length(float r1, Unit r2) {
            this.value = r1;
            this.unit = r2;
        }

        public float floatValue() {
            return this.value;
        }

        public float floatValue(float r3) {
            int r0 = AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVG$Unit[this.unit.ordinal()];
            if (r0 == 1) goto L18;
            switch(r0) {
                case 4: goto L16;
                case 5: goto L14;
                case 6: goto L12;
                case 7: goto L10;
                case 8: goto L8;
                default: goto L6;
            };
        L6:
            return this.value;
        L8:
            return (this.value * r3) / 6.0f;
        L10:
            return (this.value * r3) / 72.0f;
        L12:
            return (this.value * r3) / 25.4f;
        L14:
            return (this.value * r3) / 2.54f;
        L16:
            return this.value * r3;
        L18:
            return this.value;
        }

        public float floatValue(SVGAndroidRenderer r6) {
            if (this.unit != Unit.percent) goto L15;
            Box r62 = r6.getCurrentViewPortInUserUnits();
            if (r62 == null) goto L7;
            float r0 = r62.width;
            if (r0 != r62.height) goto L13;
            float r5 = this.value * r0;
        L12:
            return r5 / 100.0f;
        L13:
            r5 = this.value * ((float) (Math.sqrt((r0 * r0) + (r6 * r6)) / 1.414213562373095d));
            goto L12
        L7:
            return this.value;
        L15:
            return floatValueX(r6);
        }

        public float floatValue(SVGAndroidRenderer r3, float r4) {
            if (this.unit != Unit.percent) goto L7;
            return (this.value * r4) / 100.0f;
        L7:
            return floatValueX(r3);
        }

        public float floatValueX(SVGAndroidRenderer r3) {
            switch(AnonymousClass1.$SwitchMap$com$caverock$androidsvg$SVG$Unit[this.unit.ordinal()]) {
                case 1: goto L26;
                case 2: goto L24;
                case 3: goto L22;
                case 4: goto L20;
                case 5: goto L18;
                case 6: goto L16;
                case 7: goto L14;
                case 8: goto L12;
                case 9: goto L6;
                default: goto L5;
            };
        L6:
            Box r32 = r3.getCurrentViewPortInUserUnits();
            float r2 = this.value;
            if (r32 != null) goto L10;
            return r2;
        L10:
            return (r2 * r32.width) / 100.0f;
        L5:
            return this.value;
        L12:
            return (this.value * r3.getDPI()) / 6.0f;
        L14:
            return (this.value * r3.getDPI()) / 72.0f;
        L16:
            return (this.value * r3.getDPI()) / 25.4f;
        L18:
            return (this.value * r3.getDPI()) / 2.54f;
        L20:
            return this.value * r3.getDPI();
        L22:
            return this.value * r3.getCurrentFontXHeight();
        L24:
            return this.value * r3.getCurrentFontSize();
        L26:
            return this.value;
        }

        public float floatValueY(SVGAndroidRenderer r3) {
            if (this.unit != Unit.percent) goto L10;
            Box r32 = r3.getCurrentViewPortInUserUnits();
            float r2 = this.value;
            if (r32 != null) goto L8;
            return r2;
        L8:
            return (r2 * r32.height) / 100.0f;
        L10:
            return floatValueX(r3);
        }

        public boolean isNegative() {
            if (this.value >= 0.0f) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean isZero() {
            if (this.value != 0.0f) goto L6;
            return true;
        L6:
            return false;
        }

        public String toString() {
            return String.valueOf(this.value) + this.unit;
        }
    }

    /* loaded from: classes4.dex */
    public static class Line extends GraphicsElement {
        Length x1;
        Length x2;
        Length y1;
        Length y2;

        public Line() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "line";
        }
    }

    /* loaded from: classes4.dex */
    public static class Marker extends SvgViewBoxContainer implements NotDirectlyRendered {
        Length markerHeight;
        boolean markerUnitsAreUser;
        Length markerWidth;
        Float orient;
        Length refX;
        Length refY;

        public Marker() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "marker";
        }
    }

    /* loaded from: classes4.dex */
    public static class Mask extends SvgConditionalContainer implements NotDirectlyRendered {
        Length height;
        Boolean maskContentUnitsAreUser;
        Boolean maskUnitsAreUser;
        Length width;
        Length x;
        Length y;

        public Mask() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return PluginsConstants.Settings.MASK;
        }
    }

    /* loaded from: classes4.dex */
    public interface NotDirectlyRendered {
    }

    /* loaded from: classes4.dex */
    public static class PaintReference extends SvgPaint {
        SvgPaint fallback;
        String href;

        public PaintReference(String r1, SvgPaint r2) {
            this.href = r1;
            this.fallback = r2;
        }

        public String toString() {
            return this.href + " " + this.fallback;
        }
    }

    /* loaded from: classes4.dex */
    public static class Path extends GraphicsElement {
        PathDefinition d;
        Float pathLength;

        public Path() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "path";
        }
    }

    /* loaded from: classes4.dex */
    public static class PathDefinition implements PathInterface {
        private byte[] commands;
        private int commandsLength;
        private float[] coords;
        private int coordsLength;

        public PathDefinition() {
            this.commandsLength = 0;
            this.coordsLength = 0;
            this.commands = new byte[8];
            this.coords = new float[16];
        }

        private void addCommand(byte r5) {
            int r0 = this.commandsLength;
            byte[] r1 = this.commands;
            if (r0 != r1.length) goto L5;
            byte[] r02 = new byte[r1.length * 2];
            System.arraycopy(r1, 0, r02, 0, r1.length);
            this.commands = r02;
        L5:
            byte[] r03 = this.commands;
            int r12 = this.commandsLength;
            this.commandsLength = r12 + 1;
            r03[r12] = r5;
        }

        private void coordsEnsure(int r4) {
            float[] r0 = this.coords;
            if (r0.length >= (this.coordsLength + r4)) goto L6;
            float[] r42 = new float[r0.length * 2];
            System.arraycopy(r0, 0, r42, 0, r0.length);
            this.coords = r42;
            return;
        }

        @Override // com.caverock.androidsvg.SVG.PathInterface
        public void arcTo(float r3, float r4, float r5, boolean r6, boolean r7, float r8, float r9) {
            if (r6 == false) goto L4;
            int r62 = 2;
        L5:
            addCommand((byte) ((r62 | 4) | (r7 ? 1 : 0)));
            coordsEnsure(5);
            float[] r72 = this.coords;
            int r0 = this.coordsLength;
            int r1 = r0 + 1;
            this.coordsLength = r1;
            r72[r0] = r3;
            int r32 = r0 + 2;
            this.coordsLength = r32;
            r72[r1] = r4;
            int r42 = r0 + 3;
            this.coordsLength = r42;
            r72[r32] = r5;
            int r33 = r0 + 4;
            this.coordsLength = r33;
            r72[r42] = r8;
            this.coordsLength = r0 + 5;
            r72[r33] = r9;
            return;
        L4:
            r62 = 0;
            goto L5
        }

        @Override // com.caverock.androidsvg.SVG.PathInterface
        public void close() {
            addCommand((byte) 8);
        }

        @Override // com.caverock.androidsvg.SVG.PathInterface
        public void cubicTo(float r5, float r6, float r7, float r8, float r9, float r10) {
            addCommand((byte) 2);
            coordsEnsure(6);
            float[] r1 = this.coords;
            int r2 = this.coordsLength;
            int r3 = r2 + 1;
            this.coordsLength = r3;
            r1[r2] = r5;
            int r52 = r2 + 2;
            this.coordsLength = r52;
            r1[r3] = r6;
            int r62 = r2 + 3;
            this.coordsLength = r62;
            r1[r52] = r7;
            int r53 = r2 + 4;
            this.coordsLength = r53;
            r1[r62] = r8;
            int r63 = r2 + 5;
            this.coordsLength = r63;
            r1[r53] = r9;
            this.coordsLength = r2 + 6;
            r1[r63] = r10;
        }

        public void enumeratePath(PathInterface r13) {
            int r0 = 0;
            int r9 = 0;
        L4:
            if (r9 >= this.commandsLength) goto L30;
            byte r1 = this.commands[r9];
            if (r1 != 0) goto L8;
            float[] r2 = this.coords;
            int r3 = r0 + 1;
            float r4 = r2[r0];
            r0 = r0 + 2;
            r13.moveTo(r4, r2[r3]);
        L29:
            r9 = r9 + 1;
            goto L4
        L8:
            if (r1 != 1) goto L10;
            float[] r22 = this.coords;
            int r32 = r0 + 1;
            float r42 = r22[r0];
            r0 = r0 + 2;
            r13.lineTo(r42, r22[r32]);
            goto L29
        L10:
            if (r1 != 2) goto L12;
            float[] r23 = this.coords;
            r13.cubicTo(r23[r0], r23[r0 + 1], r23[r0 + 2], r23[r0 + 3], r23[r0 + 4], r23[r0 + 5]);
            r0 = r0 + 6;
            goto L29
        L12:
            if (r1 != 3) goto L14;
            float[] r24 = this.coords;
            float r43 = r24[r0];
            float r33 = r24[r0 + 1];
            int r6 = r0 + 3;
            float r5 = r24[r0 + 2];
            r0 = r0 + 4;
            r13.quadTo(r43, r33, r5, r24[r6]);
            goto L29
        L14:
            if (r1 != 8) goto L16;
            r13.close();
            goto L29
        L16:
            if ((r1 & 2) == 0) goto L18;
            boolean r44 = true;
        L20:
            if ((r1 & 1) == 0) goto L22;
            boolean r52 = true;
        L23:
            float[] r12 = this.coords;
            r13.arcTo(r12[r0], r12[r0 + 1], r12[r0 + 2], r44, r52, r12[r0 + 3], r12[r0 + 4]);
            r0 = r0 + 5;
            goto L29
        L22:
            r52 = false;
            goto L23
        L18:
            r44 = false;
            goto L20
        }

        public boolean isEmpty() {
            if (this.commandsLength != 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // com.caverock.androidsvg.SVG.PathInterface
        public void lineTo(float r5, float r6) {
            addCommand((byte) 1);
            coordsEnsure(2);
            float[] r1 = this.coords;
            int r2 = this.coordsLength;
            int r3 = r2 + 1;
            this.coordsLength = r3;
            r1[r2] = r5;
            this.coordsLength = r2 + 2;
            r1[r3] = r6;
        }

        @Override // com.caverock.androidsvg.SVG.PathInterface
        public void moveTo(float r5, float r6) {
            addCommand((byte) 0);
            coordsEnsure(2);
            float[] r1 = this.coords;
            int r2 = this.coordsLength;
            int r3 = r2 + 1;
            this.coordsLength = r3;
            r1[r2] = r5;
            this.coordsLength = r2 + 2;
            r1[r3] = r6;
        }

        @Override // com.caverock.androidsvg.SVG.PathInterface
        public void quadTo(float r5, float r6, float r7, float r8) {
            addCommand((byte) 3);
            coordsEnsure(4);
            float[] r1 = this.coords;
            int r2 = this.coordsLength;
            int r3 = r2 + 1;
            this.coordsLength = r3;
            r1[r2] = r5;
            int r52 = r2 + 2;
            this.coordsLength = r52;
            r1[r3] = r6;
            int r62 = r2 + 3;
            this.coordsLength = r62;
            r1[r52] = r7;
            this.coordsLength = r2 + 4;
            r1[r62] = r8;
        }
    }

    /* loaded from: classes4.dex */
    public interface PathInterface {
        void arcTo(float r1, float r2, float r3, boolean r4, boolean r5, float r6, float r7);

        void close();

        void cubicTo(float r1, float r2, float r3, float r4, float r5, float r6);

        void lineTo(float r1, float r2);

        void moveTo(float r1, float r2);

        void quadTo(float r1, float r2, float r3, float r4);
    }

    /* loaded from: classes4.dex */
    public static class Pattern extends SvgViewBoxContainer implements NotDirectlyRendered {
        Length height;
        String href;
        Boolean patternContentUnitsAreUser;
        Matrix patternTransform;
        Boolean patternUnitsAreUser;
        Length width;
        Length x;
        Length y;

        public Pattern() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "pattern";
        }
    }

    /* loaded from: classes4.dex */
    public static class PolyLine extends GraphicsElement {
        float[] points;

        public PolyLine() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "polyline";
        }
    }

    /* loaded from: classes4.dex */
    public static class Polygon extends PolyLine {
        public Polygon() {
        }

        @Override // com.caverock.androidsvg.SVG.PolyLine, com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "polygon";
        }
    }

    /* loaded from: classes4.dex */
    public static class Rect extends GraphicsElement {
        Length height;
        Length rx;
        Length ry;
        Length width;
        Length x;
        Length y;

        public Rect() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "rect";
        }
    }

    /* loaded from: classes4.dex */
    public static class SolidColor extends SvgElementBase implements SvgContainer {
        public SolidColor() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgContainer
        public void addChild(SvgObject r1) {
        }

        @Override // com.caverock.androidsvg.SVG.SvgContainer
        public List<SvgObject> getChildren() {
            return Collections.EMPTY_LIST;
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "solidColor";
        }
    }

    /* loaded from: classes4.dex */
    public static class Stop extends SvgElementBase implements SvgContainer {
        Float offset;

        public Stop() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgContainer
        public void addChild(SvgObject r1) {
        }

        @Override // com.caverock.androidsvg.SVG.SvgContainer
        public List<SvgObject> getChildren() {
            return Collections.EMPTY_LIST;
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "stop";
        }
    }

    /* loaded from: classes4.dex */
    public static class Style implements Cloneable {
        CSSClipRect clip;
        String clipPath;
        FillRule clipRule;
        Colour color;
        TextDirection direction;
        Boolean display;
        SvgPaint fill;
        Float fillOpacity;
        FillRule fillRule;
        List<String> fontFamily;
        Length fontSize;
        FontStyle fontStyle;
        Integer fontWeight;
        RenderQuality imageRendering;
        String markerEnd;
        String markerMid;
        String markerStart;
        String mask;
        Float opacity;
        Boolean overflow;
        SvgPaint solidColor;
        Float solidOpacity;
        long specifiedFlags;
        SvgPaint stopColor;
        Float stopOpacity;
        SvgPaint stroke;
        Length[] strokeDashArray;
        Length strokeDashOffset;
        LineCap strokeLineCap;
        LineJoin strokeLineJoin;
        Float strokeMiterLimit;
        Float strokeOpacity;
        Length strokeWidth;
        TextAnchor textAnchor;
        TextDecoration textDecoration;
        VectorEffect vectorEffect;
        SvgPaint viewportFill;
        Float viewportFillOpacity;
        Boolean visibility;

        public enum FillRule extends Enum<FillRule> {
            private static final /* synthetic */ FillRule[] $VALUES = null;
            public static final FillRule EvenOdd = null;
            public static final FillRule NonZero = null;

            static {
                FillRule r0 = new FillRule("NonZero", 0);
                NonZero = r0;
                FillRule r1 = new FillRule("EvenOdd", 1);
                EvenOdd = r1;
                $VALUES = new FillRule[]{r0, r1};
            }

            FillRule(String r1, int r2) {
            }

            public static FillRule valueOf(String r1) {
                return (FillRule) Enum.valueOf(FillRule.class, r1);
            }

            public static FillRule[] values() {
                return (FillRule[]) $VALUES.clone();
            }
        }

        public enum FontStyle extends Enum<FontStyle> {
            private static final /* synthetic */ FontStyle[] $VALUES = null;
            public static final FontStyle Italic = null;
            public static final FontStyle Normal = null;
            public static final FontStyle Oblique = null;

            static {
                FontStyle r0 = new FontStyle("Normal", 0);
                Normal = r0;
                FontStyle r1 = new FontStyle("Italic", 1);
                Italic = r1;
                FontStyle r2 = new FontStyle("Oblique", 2);
                Oblique = r2;
                $VALUES = new FontStyle[]{r0, r1, r2};
            }

            FontStyle(String r1, int r2) {
            }

            public static FontStyle valueOf(String r1) {
                return (FontStyle) Enum.valueOf(FontStyle.class, r1);
            }

            public static FontStyle[] values() {
                return (FontStyle[]) $VALUES.clone();
            }
        }

        public enum LineCap extends Enum<LineCap> {
            private static final /* synthetic */ LineCap[] $VALUES = null;
            public static final LineCap Butt = null;
            public static final LineCap Round = null;
            public static final LineCap Square = null;

            static {
                LineCap r0 = new LineCap("Butt", 0);
                Butt = r0;
                LineCap r1 = new LineCap("Round", 1);
                Round = r1;
                LineCap r2 = new LineCap("Square", 2);
                Square = r2;
                $VALUES = new LineCap[]{r0, r1, r2};
            }

            LineCap(String r1, int r2) {
            }

            public static LineCap valueOf(String r1) {
                return (LineCap) Enum.valueOf(LineCap.class, r1);
            }

            public static LineCap[] values() {
                return (LineCap[]) $VALUES.clone();
            }
        }

        public enum LineJoin extends Enum<LineJoin> {
            private static final /* synthetic */ LineJoin[] $VALUES = null;
            public static final LineJoin Bevel = null;
            public static final LineJoin Miter = null;
            public static final LineJoin Round = null;

            static {
                LineJoin r0 = new LineJoin("Miter", 0);
                Miter = r0;
                LineJoin r1 = new LineJoin("Round", 1);
                Round = r1;
                LineJoin r2 = new LineJoin("Bevel", 2);
                Bevel = r2;
                $VALUES = new LineJoin[]{r0, r1, r2};
            }

            LineJoin(String r1, int r2) {
            }

            public static LineJoin valueOf(String r1) {
                return (LineJoin) Enum.valueOf(LineJoin.class, r1);
            }

            public static LineJoin[] values() {
                return (LineJoin[]) $VALUES.clone();
            }
        }

        public enum RenderQuality extends Enum<RenderQuality> {
            private static final /* synthetic */ RenderQuality[] $VALUES = null;
            public static final RenderQuality auto = null;
            public static final RenderQuality optimizeQuality = null;
            public static final RenderQuality optimizeSpeed = null;

            static {
                RenderQuality r0 = new RenderQuality("auto", 0);
                auto = r0;
                RenderQuality r1 = new RenderQuality("optimizeQuality", 1);
                optimizeQuality = r1;
                RenderQuality r2 = new RenderQuality("optimizeSpeed", 2);
                optimizeSpeed = r2;
                $VALUES = new RenderQuality[]{r0, r1, r2};
            }

            RenderQuality(String r1, int r2) {
            }

            public static RenderQuality valueOf(String r1) {
                return (RenderQuality) Enum.valueOf(RenderQuality.class, r1);
            }

            public static RenderQuality[] values() {
                return (RenderQuality[]) $VALUES.clone();
            }
        }

        public enum TextAnchor extends Enum<TextAnchor> {
            private static final /* synthetic */ TextAnchor[] $VALUES = null;
            public static final TextAnchor End = null;
            public static final TextAnchor Middle = null;
            public static final TextAnchor Start = null;

            static {
                TextAnchor r0 = new TextAnchor("Start", 0);
                Start = r0;
                TextAnchor r1 = new TextAnchor("Middle", 1);
                Middle = r1;
                TextAnchor r2 = new TextAnchor("End", 2);
                End = r2;
                $VALUES = new TextAnchor[]{r0, r1, r2};
            }

            TextAnchor(String r1, int r2) {
            }

            public static TextAnchor valueOf(String r1) {
                return (TextAnchor) Enum.valueOf(TextAnchor.class, r1);
            }

            public static TextAnchor[] values() {
                return (TextAnchor[]) $VALUES.clone();
            }
        }

        public enum TextDecoration extends Enum<TextDecoration> {
            private static final /* synthetic */ TextDecoration[] $VALUES = null;
            public static final TextDecoration Blink = null;
            public static final TextDecoration LineThrough = null;
            public static final TextDecoration None = null;
            public static final TextDecoration Overline = null;
            public static final TextDecoration Underline = null;

            static {
                TextDecoration r0 = new TextDecoration("None", 0);
                None = r0;
                TextDecoration r1 = new TextDecoration("Underline", 1);
                Underline = r1;
                TextDecoration r2 = new TextDecoration("Overline", 2);
                Overline = r2;
                TextDecoration r3 = new TextDecoration("LineThrough", 3);
                LineThrough = r3;
                TextDecoration r4 = new TextDecoration("Blink", 4);
                Blink = r4;
                $VALUES = new TextDecoration[]{r0, r1, r2, r3, r4};
            }

            TextDecoration(String r1, int r2) {
            }

            public static TextDecoration valueOf(String r1) {
                return (TextDecoration) Enum.valueOf(TextDecoration.class, r1);
            }

            public static TextDecoration[] values() {
                return (TextDecoration[]) $VALUES.clone();
            }
        }

        public enum TextDirection extends Enum<TextDirection> {
            private static final /* synthetic */ TextDirection[] $VALUES = null;
            public static final TextDirection LTR = null;
            public static final TextDirection RTL = null;

            static {
                TextDirection r0 = new TextDirection("LTR", 0);
                LTR = r0;
                TextDirection r1 = new TextDirection("RTL", 1);
                RTL = r1;
                $VALUES = new TextDirection[]{r0, r1};
            }

            TextDirection(String r1, int r2) {
            }

            public static TextDirection valueOf(String r1) {
                return (TextDirection) Enum.valueOf(TextDirection.class, r1);
            }

            public static TextDirection[] values() {
                return (TextDirection[]) $VALUES.clone();
            }
        }

        public enum VectorEffect extends Enum<VectorEffect> {
            private static final /* synthetic */ VectorEffect[] $VALUES = null;
            public static final VectorEffect NonScalingStroke = null;
            public static final VectorEffect None = null;

            static {
                VectorEffect r0 = new VectorEffect("None", 0);
                None = r0;
                VectorEffect r1 = new VectorEffect("NonScalingStroke", 1);
                NonScalingStroke = r1;
                $VALUES = new VectorEffect[]{r0, r1};
            }

            VectorEffect(String r1, int r2) {
            }

            public static VectorEffect valueOf(String r1) {
                return (VectorEffect) Enum.valueOf(VectorEffect.class, r1);
            }

            public static VectorEffect[] values() {
                return (VectorEffect[]) $VALUES.clone();
            }
        }

        public Style() {
            this.specifiedFlags = 0;
        }

        public static Style getDefaultStyle() {
            Style r0 = new Style();
            r0.specifiedFlags = -1;
            Colour r1 = Colour.BLACK;
            r0.fill = r1;
            FillRule r2 = FillRule.NonZero;
            r0.fillRule = r2;
            Float r4 = Float.valueOf(1.0f);
            r0.fillOpacity = r4;
            r0.stroke = null;
            r0.strokeOpacity = r4;
            r0.strokeWidth = new Length(1.0f);
            r0.strokeLineCap = LineCap.Butt;
            r0.strokeLineJoin = LineJoin.Miter;
            r0.strokeMiterLimit = Float.valueOf(4.0f);
            r0.strokeDashArray = null;
            r0.strokeDashOffset = new Length(0.0f);
            r0.opacity = r4;
            r0.color = r1;
            r0.fontFamily = null;
            r0.fontSize = new Length(12.0f, Unit.pt);
            r0.fontWeight = Integer.valueOf(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN);
            r0.fontStyle = FontStyle.Normal;
            r0.textDecoration = TextDecoration.None;
            r0.direction = TextDirection.LTR;
            r0.textAnchor = TextAnchor.Start;
            Boolean r3 = Boolean.TRUE;
            r0.overflow = r3;
            r0.clip = null;
            r0.markerStart = null;
            r0.markerMid = null;
            r0.markerEnd = null;
            r0.display = r3;
            r0.visibility = r3;
            r0.stopColor = r1;
            r0.stopOpacity = r4;
            r0.clipPath = null;
            r0.clipRule = r2;
            r0.mask = null;
            r0.solidColor = null;
            r0.solidOpacity = r4;
            r0.viewportFill = null;
            r0.viewportFillOpacity = r4;
            r0.vectorEffect = VectorEffect.None;
            r0.imageRendering = RenderQuality.auto;
            return r0;
        }

        public Object clone() {
            Style r0 = (Style) super.clone();
            Length[] r1 = this.strokeDashArray;
            if (r1 == null) goto L5;
            r0.strokeDashArray = (Length[]) r1.clone();
        L5:
            return r0;
        }

        public void resetNonInheritingProperties(boolean r3) {
            Float r0 = Float.valueOf(1.0f);
            Boolean r1 = Boolean.TRUE;
            this.display = r1;
            if (r3 == true) goto L6;
            r1 = Boolean.FALSE;
        L6:
            this.overflow = r1;
            this.clip = null;
            this.clipPath = null;
            this.opacity = r0;
            this.stopColor = Colour.BLACK;
            this.stopOpacity = r0;
            this.mask = null;
            this.solidColor = null;
            this.solidOpacity = r0;
            this.viewportFill = null;
            this.viewportFillOpacity = r0;
            this.vectorEffect = VectorEffect.None;
        }
    }

    /* loaded from: classes4.dex */
    public static class Svg extends SvgViewBoxContainer {
        Length height;
        public String version;
        Length width;
        Length x;
        Length y;

        public Svg() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "svg";
        }
    }

    /* loaded from: classes4.dex */
    public interface SvgConditional {
        String getRequiredExtensions();

        Set<String> getRequiredFeatures();

        Set<String> getRequiredFonts();

        Set<String> getRequiredFormats();

        Set<String> getSystemLanguage();

        void setRequiredExtensions(String r1);

        void setRequiredFeatures(Set<String> r1);

        void setRequiredFonts(Set<String> r1);

        void setRequiredFormats(Set<String> r1);

        void setSystemLanguage(Set<String> r1);
    }

    /* loaded from: classes4.dex */
    public static abstract class SvgConditionalContainer extends SvgElement implements SvgContainer, SvgConditional {
        List<SvgObject> children;
        String requiredExtensions;
        Set<String> requiredFeatures;
        Set<String> requiredFonts;
        Set<String> requiredFormats;
        Set<String> systemLanguage;

        public SvgConditionalContainer() {
            this.children = new ArrayList();
            this.requiredFeatures = null;
            this.requiredExtensions = null;
            this.systemLanguage = null;
            this.requiredFormats = null;
            this.requiredFonts = null;
        }

        @Override // com.caverock.androidsvg.SVG.SvgContainer
        public void addChild(SvgObject r1) {
            this.children.add(r1);
        }

        @Override // com.caverock.androidsvg.SVG.SvgContainer
        public List<SvgObject> getChildren() {
            return this.children;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public String getRequiredExtensions() {
            return this.requiredExtensions;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public Set<String> getRequiredFeatures() {
            return this.requiredFeatures;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public Set<String> getRequiredFonts() {
            return this.requiredFonts;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public Set<String> getRequiredFormats() {
            return this.requiredFormats;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public Set<String> getSystemLanguage() {
            return null;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public void setRequiredExtensions(String r1) {
            this.requiredExtensions = r1;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public void setRequiredFeatures(Set<String> r1) {
            this.requiredFeatures = r1;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public void setRequiredFonts(Set<String> r1) {
            this.requiredFonts = r1;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public void setRequiredFormats(Set<String> r1) {
            this.requiredFormats = r1;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public void setSystemLanguage(Set<String> r1) {
            this.systemLanguage = r1;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class SvgConditionalElement extends SvgElement implements SvgConditional {
        String requiredExtensions;
        Set<String> requiredFeatures;
        Set<String> requiredFonts;
        Set<String> requiredFormats;
        Set<String> systemLanguage;

        public SvgConditionalElement() {
            this.requiredFeatures = null;
            this.requiredExtensions = null;
            this.systemLanguage = null;
            this.requiredFormats = null;
            this.requiredFonts = null;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public String getRequiredExtensions() {
            return this.requiredExtensions;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public Set<String> getRequiredFeatures() {
            return this.requiredFeatures;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public Set<String> getRequiredFonts() {
            return this.requiredFonts;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public Set<String> getRequiredFormats() {
            return this.requiredFormats;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public Set<String> getSystemLanguage() {
            return this.systemLanguage;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public void setRequiredExtensions(String r1) {
            this.requiredExtensions = r1;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public void setRequiredFeatures(Set<String> r1) {
            this.requiredFeatures = r1;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public void setRequiredFonts(Set<String> r1) {
            this.requiredFonts = r1;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public void setRequiredFormats(Set<String> r1) {
            this.requiredFormats = r1;
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditional
        public void setSystemLanguage(Set<String> r1) {
            this.systemLanguage = r1;
        }
    }

    /* loaded from: classes4.dex */
    public interface SvgContainer {
        void addChild(SvgObject r1);

        List<SvgObject> getChildren();
    }

    /* loaded from: classes4.dex */
    public static abstract class SvgElement extends SvgElementBase {
        Box boundingBox;

        public SvgElement() {
            this.boundingBox = null;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class SvgElementBase extends SvgObject {
        Style baseStyle;
        List<String> classNames;
        String id;
        Boolean spacePreserve;
        Style style;

        public SvgElementBase() {
            this.id = null;
            this.spacePreserve = null;
            this.baseStyle = null;
            this.style = null;
            this.classNames = null;
        }

        public String toString() {
            return getNodeName();
        }
    }

    /* loaded from: classes4.dex */
    public static class SvgLinearGradient extends GradientElement {
        Length x1;
        Length x2;
        Length y1;
        Length y2;

        public SvgLinearGradient() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "linearGradient";
        }
    }

    /* loaded from: classes4.dex */
    public static class SvgObject {
        SVG document;
        SvgContainer parent;

        public SvgObject() {
        }

        public abstract String getNodeName();
    }

    /* loaded from: classes4.dex */
    public static abstract class SvgPaint implements Cloneable {
        public SvgPaint() {
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class SvgPreserveAspectRatioContainer extends SvgConditionalContainer {
        PreserveAspectRatio preserveAspectRatio;

        public SvgPreserveAspectRatioContainer() {
            this.preserveAspectRatio = null;
        }
    }

    /* loaded from: classes4.dex */
    public static class SvgRadialGradient extends GradientElement {
        Length cx;
        Length cy;
        Length fx;
        Length fy;
        Length r;

        public SvgRadialGradient() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "radialGradient";
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class SvgViewBoxContainer extends SvgPreserveAspectRatioContainer {
        Box viewBox;

        public SvgViewBoxContainer() {
        }
    }

    /* loaded from: classes4.dex */
    public static class Switch extends Group {
        public Switch() {
        }

        @Override // com.caverock.androidsvg.SVG.Group, com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return PluginsConstants.Settings.TYPE_SWITCH;
        }
    }

    /* loaded from: classes4.dex */
    public static class Symbol extends SvgViewBoxContainer implements NotDirectlyRendered {
        public Symbol() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "symbol";
        }
    }

    /* loaded from: classes4.dex */
    public static class TRef extends TextContainer implements TextChild {
        String href;
        private TextRoot textRoot;

        public TRef() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "tref";
        }

        @Override // com.caverock.androidsvg.SVG.TextChild
        public TextRoot getTextRoot() {
            return this.textRoot;
        }

        public void setTextRoot(TextRoot r1) {
            this.textRoot = r1;
        }
    }

    /* loaded from: classes4.dex */
    public static class TSpan extends TextPositionedContainer implements TextChild {
        private TextRoot textRoot;

        public TSpan() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "tspan";
        }

        @Override // com.caverock.androidsvg.SVG.TextChild
        public TextRoot getTextRoot() {
            return this.textRoot;
        }

        public void setTextRoot(TextRoot r1) {
            this.textRoot = r1;
        }
    }

    /* loaded from: classes4.dex */
    public static class Text extends TextPositionedContainer implements TextRoot, HasTransform {
        Matrix transform;

        public Text() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "text";
        }

        @Override // com.caverock.androidsvg.SVG.HasTransform
        public void setTransform(Matrix r1) {
            this.transform = r1;
        }
    }

    /* loaded from: classes4.dex */
    public interface TextChild {
        TextRoot getTextRoot();
    }

    /* loaded from: classes4.dex */
    public static abstract class TextContainer extends SvgConditionalContainer {
        public TextContainer() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgConditionalContainer, com.caverock.androidsvg.SVG.SvgContainer
        public void addChild(SvgObject r2) {
            if ((r2 instanceof TextChild) == false) goto L6;
            this.children.add(r2);
            return;
        L6:
            SVGParser$$ExternalSyntheticBUOutline2.m("Text content elements cannot contain ", r2, " elements.");
        }
    }

    /* loaded from: classes4.dex */
    public static class TextPath extends TextContainer implements TextChild {
        String href;
        Length startOffset;
        private TextRoot textRoot;

        public TextPath() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "textPath";
        }

        @Override // com.caverock.androidsvg.SVG.TextChild
        public TextRoot getTextRoot() {
            return this.textRoot;
        }

        public void setTextRoot(TextRoot r1) {
            this.textRoot = r1;
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class TextPositionedContainer extends TextContainer {
        List<Length> dx;
        List<Length> dy;
        List<Length> x;
        List<Length> y;

        public TextPositionedContainer() {
        }
    }

    /* loaded from: classes4.dex */
    public interface TextRoot {
    }

    /* loaded from: classes4.dex */
    public static class TextSequence extends SvgObject implements TextChild {
        String text;
        private TextRoot textRoot;

        public TextSequence(String r1) {
            this.text = r1;
        }

        @Override // com.caverock.androidsvg.SVG.TextChild
        public TextRoot getTextRoot() {
            return this.textRoot;
        }

        public String toString() {
            return "TextChild: '" + this.text + "'";
        }
    }

    /* loaded from: classes4.dex */
    public enum Unit extends Enum<Unit> {
        private static final /* synthetic */ Unit[] $VALUES = null;
        public static final Unit cm = null;
        public static final Unit em = null;
        public static final Unit ex = null;
        public static final Unit in = null;
        public static final Unit mm = null;
        public static final Unit pc = null;
        public static final Unit percent = null;
        public static final Unit pt = null;
        public static final Unit px = null;

        static {
            Unit r0 = new Unit("px", 0);
            px = r0;
            Unit r1 = new Unit("em", 1);
            em = r1;
            Unit r2 = new Unit("ex", 2);
            ex = r2;
            Unit r3 = new Unit("in", 3);
            in = r3;
            Unit r4 = new Unit("cm", 4);
            cm = r4;
            Unit r5 = new Unit("mm", 5);
            mm = r5;
            Unit r6 = new Unit("pt", 6);
            pt = r6;
            Unit r7 = new Unit("pc", 7);
            pc = r7;
            Unit r8 = new Unit("percent", 8);
            percent = r8;
            $VALUES = new Unit[]{r0, r1, r2, r3, r4, r5, r6, r7, r8};
        }

        Unit(String r1, int r2) {
        }

        public static Unit valueOf(String r1) {
            return (Unit) Enum.valueOf(Unit.class, r1);
        }

        public static Unit[] values() {
            return (Unit[]) $VALUES.clone();
        }
    }

    /* loaded from: classes4.dex */
    public static class Use extends Group {
        Length height;
        String href;
        Length width;
        Length x;
        Length y;

        public Use() {
        }

        @Override // com.caverock.androidsvg.SVG.Group, com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return "use";
        }
    }

    /* loaded from: classes4.dex */
    public static class View extends SvgViewBoxContainer implements NotDirectlyRendered {
        public View() {
        }

        @Override // com.caverock.androidsvg.SVG.SvgObject
        public String getNodeName() {
            return PluginsConstants.Settings.VIEW;
        }
    }

    static {
    }

    public SVG() {
        this.rootElement = null;
        this.title = _UrlKt.FRAGMENT_ENCODE_SET;
        this.desc = _UrlKt.FRAGMENT_ENCODE_SET;
        this.renderDPI = 96.0f;
        this.cssRules = new CSSParser.Ruleset();
        this.idToElementMap = new HashMap();
    }

    private String cssQuotedString(String r3) {
        if (r3.startsWith("\"") == false) goto L8;
        if (r3.endsWith("\"") == false) goto L8;
        r3 = r3.substring(1, r3.length() - 1).replace("\\\"", "\"");
    L13:
        return r3.replace("\\\n", _UrlKt.FRAGMENT_ENCODE_SET).replace("\\A", "\n");
    L8:
        if (r3.startsWith("'") == false) goto L13;
        if (r3.endsWith("'") == false) goto L13;
        r3 = r3.substring(1, r3.length() - 1).replace("\\'", "'");
        goto L13
    }

    private Box getDocumentDimensions(float r8) {
        Svg r0 = this.rootElement;
        Length r1 = r0.width;
        Length r02 = r0.height;
        if (r1 == null) goto L32;
        if (r1.isZero() == true) goto L32;
        Unit r3 = r1.unit;
        Unit r4 = Unit.percent;
        if (r3 == r4) goto L32;
        Unit r5 = Unit.em;
        if (r3 == r5) goto L32;
        Unit r6 = Unit.ex;
        if (r3 == r6) goto L32;
        float r12 = r1.floatValue(r8);
        if (r02 != null) goto L16;
        Box r7 = this.rootElement.viewBox;
        if (r7 == null) goto L28;
        float r72 = (r7.height * r12) / r7.width;
    L30:
        return new Box(0.0f, 0.0f, r12, r72);
    L28:
        r72 = r12;
        goto L30
    L16:
        if (r02.isZero() == true) goto L24;
        Unit r73 = r02.unit;
        if (r73 == r4) goto L24;
        if (r73 == r5) goto L24;
        if (r73 == r6) goto L24;
        r72 = r02.floatValue(r8);
    L24:
        return new Box(-1.0f, -1.0f, -1.0f, -1.0f);
    L32:
        return new Box(-1.0f, -1.0f, -1.0f, -1.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private SvgElementBase getElementById(SvgContainer r4, String r5) {
        SvgElementBase r0 = (SvgElementBase) r4;
        if (r5.equals(r0.id) == false) goto L5;
        return r0;
    L5:
        Iterator<SvgObject> r42 = r4.getChildren().iterator();
    L7:
        if (r42.hasNext() == false) goto L19;
        Object r02 = (SvgObject) r42.next();
        if ((r02 instanceof SvgElementBase) == false) goto L7;
        SvgElementBase r1 = (SvgElementBase) r02;
        if (r5.equals(r1.id) == true) goto L13;
        if ((r02 instanceof SvgContainer) == false) goto L7;
        SvgElementBase r03 = getElementById((SvgContainer) r02, r5);
        if (r03 == null) goto L7;
        return r03;
    L13:
        return r1;
    L19:
        return null;
    }

    public static SVGExternalFileResolver getFileResolver() {
        return null;
    }

    public static SVG getFromInputStream(InputStream r2) {
        return new SVGParser().parse(r2, enableInternalEntities);
    }

    public void addCSSRules(CSSParser.Ruleset r1) {
        this.cssRules.addAll(r1);
    }

    public void clearRenderCSSRules() {
        this.cssRules.removeFromSource(CSSParser.Source.RenderOptions);
    }

    public List<CSSParser.Rule> getCSSRules() {
        return this.cssRules.getRules();
    }

    public float getDocumentHeight() {
        if (this.rootElement != null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("SVG document is empty");
        return 0.0f;
    L5:
        return getDocumentDimensions(this.renderDPI).height;
    }

    public RectF getDocumentViewBox() {
        Svg r0 = this.rootElement;
        if (r0 == null) goto L10;
        Box r02 = r0.viewBox;
        if (r02 != null) goto L9;
        return null;
    L9:
        return r02.toRectF();
    L10:
        f$$ExternalSyntheticBUOutline1.m("SVG document is empty");
        return null;
    }

    public float getDocumentWidth() {
        if (this.rootElement != null) goto L5;
        f$$ExternalSyntheticBUOutline1.m("SVG document is empty");
        return 0.0f;
    L5:
        return getDocumentDimensions(this.renderDPI).width;
    }

    public SvgElementBase getElementById(String r2) {
        if (r2 != null) goto L4;
        return null;
    L4:
        if (r2.length() != 0) goto L7;
        return null;
    L7:
        if (r2.equals(this.rootElement.id) == false) goto L11;
        return this.rootElement;
    L11:
        if (this.idToElementMap.containsKey(r2) == true) goto L13;
        SvgElementBase r0 = getElementById(this.rootElement, r2);
        this.idToElementMap.put(r2, r0);
        return r0;
    L13:
        return this.idToElementMap.get(r2);
    }

    public Svg getRootElement() {
        return this.rootElement;
    }

    public boolean hasCSSRules() {
        return !this.cssRules.isEmpty();
    }

    public void renderToCanvas(Canvas r2) {
        renderToCanvas(r2, null);
    }

    public void renderToCanvas(Canvas r4, RenderOptions r5) {
        if (r5 != null) goto L5;
        r5 = new RenderOptions();
    L5:
        if (r5.hasViewPort() == true) goto L7;
        r5.viewPort(0.0f, 0.0f, r4.getWidth(), r4.getHeight());
    L7:
        new SVGAndroidRenderer(r4, this.renderDPI).renderDocument(this, r5);
    }

    public SvgObject resolveIRI(String r4) {
        if (r4 != null) goto L5;
        return null;
    L5:
        String r42 = cssQuotedString(r4);
        if (r42.length() > 1) goto L8;
    L11:
        return null;
    L8:
        if (r42.startsWith("#") == false) goto L11;
        return getElementById(r42.substring(1));
    }

    public void setDesc(String r1) {
        this.desc = r1;
    }

    public void setDocumentHeight(float r2) {
        Svg r1 = this.rootElement;
        if (r1 == null) goto L6;
        r1.height = new Length(r2);
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("SVG document is empty");
    }

    public void setDocumentWidth(float r2) {
        Svg r1 = this.rootElement;
        if (r1 == null) goto L6;
        r1.width = new Length(r2);
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("SVG document is empty");
    }

    public void setRootElement(Svg r1) {
        this.rootElement = r1;
    }

    public void setTitle(String r1) {
        this.title = r1;
    }
}
