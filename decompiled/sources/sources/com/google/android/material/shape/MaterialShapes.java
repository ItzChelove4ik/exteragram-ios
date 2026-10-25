package com.google.android.material.shape;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import androidx.graphics.shapes.CornerRounding;
import androidx.graphics.shapes.RoundedPolygon;
import androidx.graphics.shapes.RoundedPolygonKt;
import androidx.graphics.shapes.ShapesKt;
import androidx.graphics.shapes.Shapes_androidKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public final class MaterialShapes {
    public static final RoundedPolygon ARCH = null;
    public static final RoundedPolygon ARROW = null;
    public static final RoundedPolygon BOOM = null;
    public static final RoundedPolygon BUN = null;
    public static final RoundedPolygon BURST = null;
    public static final RoundedPolygon CIRCLE = null;
    public static final RoundedPolygon CLAM_SHELL = null;
    public static final RoundedPolygon CLOVER_4 = null;
    public static final RoundedPolygon CLOVER_8 = null;
    public static final RoundedPolygon COOKIE_12 = null;
    public static final RoundedPolygon COOKIE_4 = null;
    public static final RoundedPolygon COOKIE_6 = null;
    public static final RoundedPolygon COOKIE_7 = null;
    public static final RoundedPolygon COOKIE_9 = null;
    private static final CornerRounding CORNER_ROUND_100 = null;
    private static final CornerRounding CORNER_ROUND_15 = null;
    private static final CornerRounding CORNER_ROUND_20 = null;
    private static final CornerRounding CORNER_ROUND_30 = null;
    private static final CornerRounding CORNER_ROUND_50 = null;
    public static final RoundedPolygon DIAMOND = null;
    public static final RoundedPolygon FAN = null;
    public static final RoundedPolygon FLOWER = null;
    public static final RoundedPolygon GEM = null;
    public static final RoundedPolygon GHOSTISH = null;
    public static final RoundedPolygon HEART = null;
    public static final RoundedPolygon OVAL = null;
    public static final RoundedPolygon PENTAGON = null;
    public static final RoundedPolygon PILL = null;
    public static final RoundedPolygon PIXEL_CIRCLE = null;
    public static final RoundedPolygon PIXEL_TRIANGLE = null;
    public static final RoundedPolygon PUFFY = null;
    public static final RoundedPolygon PUFFY_DIAMOND = null;
    public static final RoundedPolygon SEMI_CIRCLE = null;
    public static final RoundedPolygon SLANTED_SQUARE = null;
    public static final RoundedPolygon SOFT_BOOM = null;
    public static final RoundedPolygon SOFT_BURST = null;
    public static final RoundedPolygon SQUARE = null;
    public static final RoundedPolygon SUNNY = null;
    public static final RoundedPolygon TRIANGLE = null;
    public static final RoundedPolygon VERY_SUNNY = null;

    /* renamed from: com.google.android.material.shape.MaterialShapes$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class VertexAndRounding {
        private CornerRounding rounding;
        private PointF vertex;

        private VertexAndRounding(PointF r2) {
            this(r2, CornerRounding.Unrounded);
        }

        private VertexAndRounding(PointF r1, CornerRounding r2) {
            this.vertex = r1;
            this.rounding = r2;
        }

        public /* synthetic */ VertexAndRounding(PointF r1, CornerRounding r2, AnonymousClass1 r3) {
            this(r1, r2);
        }

        public /* synthetic */ VertexAndRounding(PointF r1, AnonymousClass1 r2) {
            this(r1);
        }

        public static /* synthetic */ PointF access$200(VertexAndRounding r0) {
            return r0.vertex;
        }

        public static /* synthetic */ CornerRounding access$300(VertexAndRounding r0) {
            return r0.rounding;
        }

        public static /* synthetic */ void access$400(VertexAndRounding r0, float r1, float r2) {
            r0.toRadial(r1, r2);
        }

        public static /* synthetic */ void access$500(VertexAndRounding r0, float r1, float r2) {
            r0.toCartesian(r1, r2);
        }

        private void toCartesian(float r6, float r7) {
            PointF r0 = this.vertex;
            float r62 = (float) ((r0.y * Math.cos(r0.x)) + r6);
            PointF r02 = this.vertex;
            float r72 = (float) ((r02.y * Math.sin(r02.x)) + r7);
            PointF r5 = this.vertex;
            r5.x = r62;
            r5.y = r72;
        }

        private void toRadial(float r5, float r6) {
            this.vertex.offset(-r5, -r6);
            PointF r52 = this.vertex;
            float r53 = (float) Math.atan2(r52.y, r52.x);
            PointF r62 = this.vertex;
            float r63 = (float) Math.hypot(r62.x, r62.y);
            PointF r4 = this.vertex;
            r4.x = r53;
            r4.y = r63;
        }
    }

    static {
        CORNER_ROUND_15 = new CornerRounding(0.15f, 0.0f);
        CORNER_ROUND_20 = new CornerRounding(0.2f, 0.0f);
        CORNER_ROUND_30 = new CornerRounding(0.3f, 0.0f);
        CORNER_ROUND_50 = new CornerRounding(0.5f, 0.0f);
        CORNER_ROUND_100 = new CornerRounding(1.0f, 0.0f);
        CIRCLE = normalize(getCircle(), true);
        SQUARE = normalize(getSquare(), true);
        SLANTED_SQUARE = normalize(getSlantedSquare(), true);
        ARCH = normalize(getArch(), true);
        FAN = normalize(getFan(), true);
        ARROW = normalize(getArrow(), true);
        SEMI_CIRCLE = normalize(getSemiCircle(), true);
        OVAL = normalize(getOval(-45.0f), true);
        PILL = normalize(getPill(), true);
        TRIANGLE = normalize(getTriangle(-90.0f), true);
        DIAMOND = normalize(getDiamond(), true);
        CLAM_SHELL = normalize(getClamShell(), true);
        PENTAGON = normalize(getPentagon(), true);
        GEM = normalize(getGem(-90.0f), true);
        SUNNY = normalize(getSunny(), true);
        VERY_SUNNY = normalize(getVerySunny(), true);
        COOKIE_4 = normalize(getCookie4(), true);
        COOKIE_6 = normalize(getCookie6(), true);
        COOKIE_7 = normalize(getCookie7(), true);
        COOKIE_9 = normalize(getCookie9(), true);
        COOKIE_12 = normalize(getCookie12(), true);
        GHOSTISH = normalize(getGhostish(), true);
        CLOVER_4 = normalize(getClover4(), true);
        CLOVER_8 = normalize(getClover8(), true);
        BURST = normalize(getBurst(), true);
        SOFT_BURST = normalize(getSoftBurst(), true);
        BOOM = normalize(getBoom(), true);
        SOFT_BOOM = normalize(getSoftBoom(), true);
        FLOWER = normalize(getFlower(), true);
        PUFFY = normalize(getPuffy(), true);
        PUFFY_DIAMOND = normalize(getPuffyDiamond(), true);
        PIXEL_CIRCLE = normalize(getPixelCircle(), true);
        PIXEL_TRIANGLE = normalize(getPixelTriangle(), true);
        BUN = normalize(getBun(), true);
        HEART = normalize(getHeart(), true);
    }

    private MaterialShapes() {
    }

    public static Matrix createRotationMatrix(float r1) {
        Matrix r0 = new Matrix();
        r0.setRotate(r1);
        return r0;
    }

    public static Matrix createScaleMatrix(float r1, float r2) {
        Matrix r0 = new Matrix();
        r0.setScale(r1, r2);
        return r0;
    }

    public static ShapeDrawable createShapeDrawable(RoundedPolygon r2) {
        return new ShapeDrawable(new PathShape(Shapes_androidKt.toPath(r2), 1.0f, 1.0f));
    }

    public static Matrix createSkewMatrix(float r1, float r2) {
        Matrix r0 = new Matrix();
        r0.setSkew(r1, r2);
        return r0;
    }

    private static RoundedPolygon customPolygon(List<VertexAndRounding> r6, int r7, float r8, float r9, boolean r10) {
        ArrayList r1 = new ArrayList();
        repeatAroundCenter(r6, r1, r7, r8, r9, r10);
        float[] r62 = toVerticesXyArray(r1);
        List<CornerRounding> r72 = toRoundingsList(r1);
        return RoundedPolygonKt.RoundedPolygon(r62, CornerRounding.Unrounded, r72, r8, r9);
    }

    private static RoundedPolygon getArch() {
        CornerRounding r4 = CornerRounding.Unrounded;
        CornerRounding r0 = CORNER_ROUND_100;
        CornerRounding r1 = CORNER_ROUND_20;
        return Shapes_androidKt.transformed(RoundedPolygonKt.RoundedPolygon(4, 1.0f, 0.0f, 0.0f, r4, Arrays.asList(new CornerRounding[]{r0, r0, r1, r1})), createRotationMatrix(-135.0f));
    }

    private static RoundedPolygon getArrow() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.5f, 0.892f), new CornerRounding(0.313f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(-0.216f, 1.05f), new CornerRounding(0.207f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.499f, -0.16f), new CornerRounding(0.215f, 1.0f), null));
        r0.add(new VertexAndRounding(new PointF(1.225f, 1.06f), new CornerRounding(0.211f, 0.0f), null));
        return customPolygon(r0, 1, 0.5f, 0.5f, false);
    }

    private static RoundedPolygon getBoom() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.457f, 0.296f), new CornerRounding(0.007f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.5f, -0.051f), new CornerRounding(0.007f, 0.0f), null));
        return customPolygon(r0, 15, 0.5f, 0.5f, false);
    }

    private static RoundedPolygon getBun() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.796f, 0.5f), null));
        PointF r2 = new PointF(0.853f, 0.518f);
        CornerRounding r5 = CORNER_ROUND_100;
        r0.add(new VertexAndRounding(r2, r5, null));
        r0.add(new VertexAndRounding(new PointF(0.992f, 0.631f), r5, null));
        r0.add(new VertexAndRounding(new PointF(0.968f, 1.0f), r5, null));
        return customPolygon(r0, 2, 0.5f, 0.5f, true);
    }

    private static RoundedPolygon getBurst() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.5f, -0.006f), new CornerRounding(0.006f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.592f, 0.158f), new CornerRounding(0.006f, 0.0f), null));
        return customPolygon(r0, 12, 0.5f, 0.5f, false);
    }

    private static RoundedPolygon getCircle() {
        return ShapesKt.circle(RoundedPolygon.Companion, 10);
    }

    private static RoundedPolygon getClamShell() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.171f, 0.841f), new CornerRounding(0.159f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(-0.02f, 0.5f), new CornerRounding(0.14f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.17f, 0.159f), new CornerRounding(0.159f, 0.0f), null));
        return customPolygon(r0, 2, 0.5f, 0.5f, false);
    }

    private static RoundedPolygon getClover4() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.5f, 0.074f), null));
        r0.add(new VertexAndRounding(new PointF(0.725f, -0.099f), new CornerRounding(0.476f, 0.0f), null));
        return customPolygon(r0, 4, 0.5f, 0.5f, true);
    }

    private static RoundedPolygon getClover8() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.5f, 0.036f), null));
        r0.add(new VertexAndRounding(new PointF(0.758f, -0.101f), new CornerRounding(0.209f, 0.0f), null));
        return customPolygon(r0, 8, 0.5f, 0.5f, false);
    }

    private static RoundedPolygon getCookie12() {
        return Shapes_androidKt.transformed(ShapesKt.star(RoundedPolygon.Companion, 12, 1.0f, 0.8f, CORNER_ROUND_50), createRotationMatrix(-90.0f));
    }

    private static RoundedPolygon getCookie4() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(1.237f, 1.236f), new CornerRounding(0.258f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.5f, 0.918f), new CornerRounding(0.233f, 0.0f), null));
        return customPolygon(r0, 4, 0.5f, 0.5f, false);
    }

    private static RoundedPolygon getCookie6() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.723f, 0.884f), new CornerRounding(0.394f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.5f, 1.099f), new CornerRounding(0.398f, 0.0f), null));
        return customPolygon(r0, 6, 0.5f, 0.5f, false);
    }

    private static RoundedPolygon getCookie7() {
        return Shapes_androidKt.transformed(ShapesKt.star(RoundedPolygon.Companion, 7, 1.0f, 0.75f, CORNER_ROUND_50), createRotationMatrix(-90.0f));
    }

    private static RoundedPolygon getCookie9() {
        return Shapes_androidKt.transformed(ShapesKt.star(RoundedPolygon.Companion, 9, 1.0f, 0.8f, CORNER_ROUND_50), createRotationMatrix(-90.0f));
    }

    private static RoundedPolygon getDiamond() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.5f, 1.096f), new CornerRounding(0.151f, 0.524f), null));
        r0.add(new VertexAndRounding(new PointF(0.04f, 0.5f), new CornerRounding(0.159f, 0.0f), null));
        return customPolygon(r0, 2, 0.5f, 0.5f, false);
    }

    private static RoundedPolygon getFan() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(1.0f, 1.0f), new CornerRounding(0.148f, 0.417f), null));
        r0.add(new VertexAndRounding(new PointF(0.0f, 1.0f), new CornerRounding(0.151f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.0f, 0.0f), new CornerRounding(0.148f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.978f, 0.02f), new CornerRounding(0.803f, 0.0f), null));
        return customPolygon(r0, 1, 0.5f, 0.5f, false);
    }

    private static RoundedPolygon getFlower() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.37f, 0.187f), null));
        r0.add(new VertexAndRounding(new PointF(0.416f, 0.049f), new CornerRounding(0.381f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.479f, 0.0f), new CornerRounding(0.095f, 0.0f), null));
        return customPolygon(r0, 8, 0.5f, 0.5f, true);
    }

    private static RoundedPolygon getGem() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.499f, 1.023f), new CornerRounding(0.241f, 0.778f), null));
        r0.add(new VertexAndRounding(new PointF(-0.005f, 0.792f), new CornerRounding(0.208f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.073f, 0.258f), new CornerRounding(0.228f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.433f, -0.0f), new CornerRounding(0.491f, 0.0f), null));
        return customPolygon(r0, 1, 0.5f, 0.5f, true);
    }

    private static RoundedPolygon getGem(float r1) {
        return Shapes_androidKt.transformed(getGem(), createRotationMatrix(r1));
    }

    private static RoundedPolygon getGhostish() {
        ArrayList r0 = new ArrayList();
        PointF r2 = new PointF(0.5f, 0.0f);
        CornerRounding r5 = CORNER_ROUND_100;
        r0.add(new VertexAndRounding(r2, r5, null));
        r0.add(new VertexAndRounding(new PointF(1.0f, 0.0f), r5, null));
        r0.add(new VertexAndRounding(new PointF(1.0f, 1.14f), new CornerRounding(0.254f, 0.106f), null));
        r0.add(new VertexAndRounding(new PointF(0.575f, 0.906f), new CornerRounding(0.253f, 0.0f), null));
        return customPolygon(r0, 1, 0.5f, 0.5f, true);
    }

    private static RoundedPolygon getHeart() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.5f, 0.268f), new CornerRounding(0.016f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.792f, -0.066f), new CornerRounding(0.958f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(1.064f, 0.276f), CORNER_ROUND_100, null));
        r0.add(new VertexAndRounding(new PointF(0.501f, 0.946f), new CornerRounding(0.129f, 0.0f), null));
        return customPolygon(r0, 1, 0.5f, 0.5f, true);
    }

    private static RoundedPolygon getOval() {
        return Shapes_androidKt.transformed(ShapesKt.circle(RoundedPolygon.Companion), createScaleMatrix(1.0f, 0.64f));
    }

    private static RoundedPolygon getOval(float r1) {
        return Shapes_androidKt.transformed(getOval(), createRotationMatrix(r1));
    }

    private static RoundedPolygon getPentagon() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.5f, -0.009f), new CornerRounding(0.172f, 0.0f), null));
        return customPolygon(r0, 5, 0.5f, 0.5f, false);
    }

    private static RoundedPolygon getPill() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.961f, 0.039f), new CornerRounding(0.426f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(1.001f, 0.428f), null));
        r0.add(new VertexAndRounding(new PointF(1.0f, 0.609f), CORNER_ROUND_100, null));
        return customPolygon(r0, 2, 0.5f, 0.5f, true);
    }

    private static RoundedPolygon getPixelCircle() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.5f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.704f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.704f, 0.065f), null));
        r0.add(new VertexAndRounding(new PointF(0.843f, 0.065f), null));
        r0.add(new VertexAndRounding(new PointF(0.843f, 0.148f), null));
        r0.add(new VertexAndRounding(new PointF(0.926f, 0.148f), null));
        r0.add(new VertexAndRounding(new PointF(0.926f, 0.296f), null));
        r0.add(new VertexAndRounding(new PointF(1.0f, 0.296f), null));
        return customPolygon(r0, 2, 0.5f, 0.5f, true);
    }

    private static RoundedPolygon getPixelTriangle() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.11f, 0.5f), null));
        r0.add(new VertexAndRounding(new PointF(0.113f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.287f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.287f, 0.087f), null));
        r0.add(new VertexAndRounding(new PointF(0.421f, 0.087f), null));
        r0.add(new VertexAndRounding(new PointF(0.421f, 0.17f), null));
        r0.add(new VertexAndRounding(new PointF(0.56f, 0.17f), null));
        r0.add(new VertexAndRounding(new PointF(0.56f, 0.265f), null));
        r0.add(new VertexAndRounding(new PointF(0.674f, 0.265f), null));
        r0.add(new VertexAndRounding(new PointF(0.675f, 0.344f), null));
        r0.add(new VertexAndRounding(new PointF(0.789f, 0.344f), null));
        r0.add(new VertexAndRounding(new PointF(0.789f, 0.439f), null));
        r0.add(new VertexAndRounding(new PointF(0.888f, 0.439f), null));
        return customPolygon(r0, 1, 0.5f, 0.5f, true);
    }

    private static RoundedPolygon getPuffy() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.5f, 0.053f), null));
        r0.add(new VertexAndRounding(new PointF(0.545f, -0.04f), new CornerRounding(0.405f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.67f, -0.035f), new CornerRounding(0.426f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.717f, 0.066f), new CornerRounding(0.574f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.722f, 0.128f), null));
        r0.add(new VertexAndRounding(new PointF(0.777f, 0.002f), new CornerRounding(0.36f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.914f, 0.149f), new CornerRounding(0.66f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.926f, 0.289f), new CornerRounding(0.66f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.881f, 0.346f), null));
        r0.add(new VertexAndRounding(new PointF(0.94f, 0.344f), new CornerRounding(0.126f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(1.003f, 0.437f), new CornerRounding(0.255f, 0.0f), null));
        return Shapes_androidKt.transformed(customPolygon(r0, 2, 0.5f, 0.5f, true), createScaleMatrix(1.0f, 0.742f));
    }

    private static RoundedPolygon getPuffyDiamond() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.87f, 0.13f), new CornerRounding(0.146f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.818f, 0.357f), null));
        r0.add(new VertexAndRounding(new PointF(1.0f, 0.332f), new CornerRounding(0.853f, 0.0f), null));
        return customPolygon(r0, 4, 0.5f, 0.5f, true);
    }

    private static RoundedPolygon getSemiCircle() {
        RoundedPolygon.Companion r0 = RoundedPolygon.Companion;
        CornerRounding r3 = CornerRounding.Unrounded;
        CornerRounding r1 = CORNER_ROUND_20;
        CornerRounding r2 = CORNER_ROUND_100;
        return ShapesKt.rectangle(r0, 1.6f, 1.0f, r3, Arrays.asList(new CornerRounding[]{r1, r1, r2, r2}), 0.0f, 0.0f);
    }

    private static RoundedPolygon getSlantedSquare() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.926f, 0.97f), new CornerRounding(0.189f, 0.811f), null));
        r0.add(new VertexAndRounding(new PointF(-0.021f, 0.967f), new CornerRounding(0.187f, 0.057f), null));
        return customPolygon(r0, 2, 0.5f, 0.5f, false);
    }

    private static RoundedPolygon getSoftBoom() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.733f, 0.454f), null));
        r0.add(new VertexAndRounding(new PointF(0.839f, 0.437f), new CornerRounding(0.532f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.949f, 0.449f), new CornerRounding(0.439f, 1.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.998f, 0.478f), new CornerRounding(0.174f, 0.0f), null));
        return customPolygon(r0, 16, 0.5f, 0.5f, true);
    }

    private static RoundedPolygon getSoftBurst() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.193f, 0.277f), new CornerRounding(0.053f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.176f, 0.055f), new CornerRounding(0.053f, 0.0f), null));
        return customPolygon(r0, 10, 0.5f, 0.5f, false);
    }

    private static RoundedPolygon getSquare() {
        return ShapesKt.rectangle(RoundedPolygon.Companion, 1.0f, 1.0f, CORNER_ROUND_30, null, 0.0f, 0.0f);
    }

    private static RoundedPolygon getSunny() {
        return ShapesKt.star(RoundedPolygon.Companion, 8, 1.0f, 0.8f, CORNER_ROUND_15);
    }

    private static RoundedPolygon getTriangle() {
        return RoundedPolygonKt.RoundedPolygon(3, 1.0f, 0.0f, 0.0f, CORNER_ROUND_20);
    }

    private static RoundedPolygon getTriangle(float r1) {
        return Shapes_androidKt.transformed(getTriangle(), createRotationMatrix(r1));
    }

    private static RoundedPolygon getVerySunny() {
        ArrayList r0 = new ArrayList();
        r0.add(new VertexAndRounding(new PointF(0.5f, 1.08f), new CornerRounding(0.085f, 0.0f), null));
        r0.add(new VertexAndRounding(new PointF(0.358f, 0.843f), new CornerRounding(0.085f, 0.0f), null));
        return customPolygon(r0, 8, 0.5f, 0.5f, false);
    }

    public static RoundedPolygon normalize(RoundedPolygon r3, boolean r4) {
        return normalize(r3, r4, new RectF(0.0f, 0.0f, 1.0f, 1.0f));
    }

    public static RoundedPolygon normalize(RoundedPolygon r5, boolean r6, RectF r7) {
        float[] r0 = new float[4];
        if (r6 == false) goto L5;
        r5.calculateMaxBounds(r0);
    L6:
        RectF r62 = new RectF(r0[0], r0[1], r0[2], r0[3]);
        float r02 = Math.min(r7.width() / r62.width(), r7.height() / r62.height());
        Matrix r03 = createScaleMatrix(r02, r02);
        r03.preTranslate(-r62.centerX(), -r62.centerY());
        r03.postTranslate(r7.centerX(), r7.centerY());
        return Shapes_androidKt.transformed(r5, r03);
    L5:
        r5.calculateBounds(r0);
        goto L6
    }

    private static void repeatAroundCenter(List<VertexAndRounding> r9, List<VertexAndRounding> r10, int r11, float r12, float r13, boolean r14) {
        r10.clear();
        toRadial(r9, r12, r13);
        float r0 = (float) (6.283185307179586d / r11);
        int r2 = 0;
        if (r14 == false) goto L26;
        int r112 = r11 * 2;
        float r02 = r0 / 2.0f;
        int r3 = 0;
    L5:
        if (r3 >= r112) goto L32;
        int r4 = 0;
    L8:
        if (r4 >= r9.size()) goto L25;
        if ((r3 % 2) == 0) goto L12;
        boolean r5 = true;
    L13:
        if (r5 == false) goto L15;
        int r7 = (r9.size() - 1) - r4;
    L16:
        VertexAndRounding r6 = r9.get(r7);
        if (r7 > 0) goto L19;
        if (r5 == false) goto L19;
    L24:
        r4 = r4 + 1;
    L19:
        float r72 = r3 * r02;
        if (r5 == false) goto L22;
        float r52 = (r02 - VertexAndRounding.access$200(r6).x) + (VertexAndRounding.access$200(r9.get(0)).x * 2.0f);
    L23:
        r10.add(new VertexAndRounding(new PointF(r72 + r52, VertexAndRounding.access$200(r6).y), VertexAndRounding.access$300(r6), null));
        goto L24
    L22:
        r52 = VertexAndRounding.access$200(r6).x;
        goto L23
    L15:
        r7 = r4;
        goto L16
    L12:
        r5 = false;
        goto L13
    L25:
        r3 = r3 + 1;
    L32:
        toCartesian(r10, r12, r13);
        return;
    L26:
        if (r2 >= r11) goto L32;
        Iterator<VertexAndRounding> r142 = r9.iterator();
    L29:
        if (r142.hasNext() == false) goto L31;
        VertexAndRounding r32 = r142.next();
        r10.add(new VertexAndRounding(new PointF((r2 * r0) + VertexAndRounding.access$200(r32).x, VertexAndRounding.access$200(r32).y), VertexAndRounding.access$300(r32), null));
        goto L29
    L31:
        r2 = r2 + 1;
        goto L26
    }

    private static void toCartesian(List<VertexAndRounding> r1, float r2, float r3) {
        Iterator<VertexAndRounding> r12 = r1.iterator();
    L4:
        if (r12.hasNext() == false) goto L6;
        VertexAndRounding.access$500(r12.next(), r2, r3);
        goto L4
    }

    private static void toRadial(List<VertexAndRounding> r1, float r2, float r3) {
        Iterator<VertexAndRounding> r12 = r1.iterator();
    L4:
        if (r12.hasNext() == false) goto L6;
        VertexAndRounding.access$400(r12.next(), r2, r3);
        goto L4
    }

    private static List<CornerRounding> toRoundingsList(List<VertexAndRounding> r3) {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r3.size()) goto L6;
        r0.add(VertexAndRounding.access$300(r3.get(r1)));
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    private static float[] toVerticesXyArray(List<VertexAndRounding> r4) {
        float[] r0 = new float[r4.size() * 2];
        int r1 = 0;
    L4:
        if (r1 >= r4.size()) goto L6;
        int r2 = r1 * 2;
        r0[r2] = VertexAndRounding.access$200(r4.get(r1)).x;
        r0[r2 + 1] = VertexAndRounding.access$200(r4.get(r1)).y;
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }
}
