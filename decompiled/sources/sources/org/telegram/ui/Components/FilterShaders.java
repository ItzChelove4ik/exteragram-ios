package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Stories.recorder.StoryEntry;

/* loaded from: classes7.dex */
public class FilterShaders {
    private int[] bitmapTextre;
    private BlurProgram blurProgram;
    private boolean blurTextureCreated;
    private int boostInputTexCoordHandle;
    private int boostPositionHandle;
    private int boostProgram;
    private int boostSourceImageHandle;
    private ByteBuffer calcBuffer;
    private ByteBuffer cdtBuffer;
    private int compositeCurveImageHandle;
    private int compositeInputImageHandle;
    private int compositeInputTexCoordHandle;
    private int compositeMatrixHandle;
    private int compositeMixtureHandle;
    private int compositePositionHandle;
    private int compositeProgram;
    private int compositeSourceImageHandle;
    private int compositeTexSizeHandle;
    private int contrastHandle;
    private int[] curveTextures;
    private int curvesImageHandle;
    private FilterShadersDelegate delegate;
    private int[] enhanceFrameBuffer;
    private int enhanceInputImageTexture2Handle;
    private int enhanceInputTexCoordHandle;
    private int enhanceIntensityHandle;
    private int enhancePositionHandle;
    private int enhanceShaderProgram;
    private int enhanceSourceImageHandle;
    private int[] enhanceTextures;
    private int exposureHandle;
    private int fadeAmountHandle;
    private int grainHandle;
    private int greenAndBlueChannelOverlayInputTexCoordHandle;
    private int greenAndBlueChannelOverlayMatrixHandle;
    private int greenAndBlueChannelOverlayPositionHandle;
    private int greenAndBlueChannelOverlayProgram;
    private int greenAndBlueChannelOverlaySourceImageHandle;
    private int greenAndBlueChannelOverlayTexSizeHandle;
    private StoryEntry.HDRInfo hdrInfo;
    private int heightHandle;
    private int highPassInputImageHandle;
    private int highPassInputTexCoordHandle;
    private int highPassPositionHandle;
    private int highPassProgram;
    private int highPassSourceImageHandle;
    private int highlightsHandle;
    private int highlightsTintColorHandle;
    private int highlightsTintIntensityHandle;
    private ByteBuffer hsvBuffer;
    private boolean hsvGenerated;
    private int inputTexCoordHandle;
    private boolean isVideo;
    private float lastRadius;
    private int linearBlurAngleHandle;
    private int linearBlurAspectRatioHandle;
    private int linearBlurExcludeBlurSizeHandle;
    private int linearBlurExcludePointHandle;
    private int linearBlurExcludeSizeHandle;
    private int linearBlurInputTexCoordHandle;
    private int linearBlurPositionHandle;
    private int linearBlurShaderProgram;
    private int linearBlurSourceImage2Handle;
    private int linearBlurSourceImageHandle;
    private boolean needUpdateBlurTexture;
    private boolean needUpdateSkinTexture;
    private int positionHandle;
    private int radialBlurAspectRatioHandle;
    private int radialBlurExcludeBlurSizeHandle;
    private int radialBlurExcludePointHandle;
    private int radialBlurExcludeSizeHandle;
    private int radialBlurInputTexCoordHandle;
    private int radialBlurPositionHandle;
    private int radialBlurShaderProgram;
    private int radialBlurSourceImage2Handle;
    private int radialBlurSourceImageHandle;
    private int renderBufferHeight;
    private int renderBufferWidth;
    private int[] renderFrameBuffer;
    private int[] renderTexture;
    private int[] rgbToHsvInputTexCoordHandle;
    private int rgbToHsvMatrixHandle;
    private int[] rgbToHsvPositionHandle;
    private int[] rgbToHsvShaderProgram;
    private int[] rgbToHsvSourceImageHandle;
    private int rgbToHsvTexSizeHandle;
    private int saturationHandle;
    public boolean scaleBitmap;
    private int shadowsHandle;
    private int shadowsTintColorHandle;
    private int shadowsTintIntensityHandle;
    private int sharpenHandle;
    private int sharpenHeightHandle;
    private int sharpenInputTexCoordHandle;
    private int sharpenPositionHandle;
    private int sharpenShaderProgram;
    private int sharpenSourceImageHandle;
    private int sharpenWidthHandle;
    private BlurProgram skinBlurProgram;
    private boolean skinPassDrawn;
    private boolean skinTextureCreated;
    private int skipToneHandle;
    private int sourceImageHandle;
    public FloatBuffer textureBuffer;
    private ToneCurve toneCurve;
    private int toolsShaderProgram;
    public FloatBuffer vertexBuffer;
    public FloatBuffer vertexInvertBuffer;
    private int videoHeight;
    private float[] videoMatrix;
    private int videoTexture;
    private int videoWidth;
    private int vignetteHandle;
    private int warmthHandle;
    private int widthHandle;

    /* renamed from: org.telegram.ui.Components.FilterShaders$1, reason: invalid class name */
    public class AnonymousClass1 implements FilterShadersDelegate {
        final /* synthetic */ MediaController.SavedFilterState val$lastState;

        public AnonymousClass1(MediaController.SavedFilterState r1) {
            this.val$lastState = r1;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public ByteBuffer fillAndGetCurveBuffer() {
            this.val$lastState.curvesToolValue.fillBuffer();
            return this.val$lastState.curvesToolValue.curveBuffer;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public float getBlurAngle() {
            return this.val$lastState.blurAngle;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public float getBlurExcludeBlurSize() {
            return this.val$lastState.blurExcludeBlurSize;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public PointF getBlurExcludePoint() {
            return this.val$lastState.blurExcludePoint;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public float getBlurExcludeSize() {
            return this.val$lastState.blurExcludeSize;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public int getBlurType() {
            return this.val$lastState.blurType;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public float getContrastValue() {
            return ((this.val$lastState.contrastValue / 100.0f) * 0.3f) + 1.0f;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public float getEnhanceValue() {
            return this.val$lastState.enhanceValue / 100.0f;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public float getExposureValue() {
            return this.val$lastState.exposureValue / 100.0f;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public float getFadeValue() {
            return this.val$lastState.fadeValue / 100.0f;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public float getGrainValue() {
            return (this.val$lastState.grainValue / 100.0f) * 0.04f;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public float getHighlightsValue() {
            return ((this.val$lastState.highlightsValue * 0.75f) + 100.0f) / 100.0f;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public float getSaturationValue() {
            float r1 = this.val$lastState.saturationValue / 100.0f;
            if (r1 <= 0.0f) goto L6;
            r1 = r1 * 1.05f;
        L6:
            return r1 + 1.0f;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public float getShadowsValue() {
            return ((this.val$lastState.shadowsValue * 0.55f) + 100.0f) / 100.0f;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public float getSharpenValue() {
            return ((this.val$lastState.sharpenValue / 100.0f) * 0.6f) + 0.11f;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public float getSoftenSkinValue() {
            return this.val$lastState.softenSkinValue / 100.0f;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public int getTintHighlightsColor() {
            return this.val$lastState.tintHighlightsColor;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public float getTintHighlightsIntensityValue() {
            if (this.val$lastState.tintHighlightsColor != 0) goto L6;
            return 0.0f;
        L6:
            return 0.5f;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public int getTintShadowsColor() {
            return this.val$lastState.tintShadowsColor;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public float getTintShadowsIntensityValue() {
            if (this.val$lastState.tintShadowsColor != 0) goto L6;
            return 0.0f;
        L6:
            return 0.5f;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public float getVignetteValue() {
            return this.val$lastState.vignetteValue / 100.0f;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public float getWarmthValue() {
            return this.val$lastState.warmthValue / 100.0f;
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public boolean shouldDrawCurvesPass() {
            return !this.val$lastState.curvesToolValue.shouldBeSkipped();
        }

        @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
        public boolean shouldShowOriginal() {
            return false;
        }
    }

    public static class BlurProgram {
        public int blurHeightHandle;
        public int blurInputTexCoordHandle;
        public int blurPositionHandle;
        public int blurShaderProgram;
        public int blurSourceImageHandle;
        public int blurWidthHandle;
        private String fragmentShaderCode;
        private String vertexShaderCode;

        public BlurProgram(float r9, float r10, boolean r11) {
            if (r11 == false) goto L8;
            r10 = Math.round(r9);
            if (r10 < 1.0f) goto L7;
            double r0 = r10;
            int r92 = (int) Math.floor(Math.sqrt((Math.pow(r0, 2.0d) * (-2.0d)) * Math.log(0.00390625d * Math.sqrt(Math.pow(r0, 2.0d) * 6.283185307179586d))));
            int r93 = r92 + (r92 % 2);
        L9:
            this.fragmentShaderCode = FilterShaders.m10579$$Nest$smfragmentShaderForOptimizedBlurOfRadius(r93, r10);
            this.vertexShaderCode = FilterShaders.m10580$$Nest$smvertexShaderForOptimizedBlurOfRadius(r93, r10);
            return;
        L7:
            r93 = 0;
            goto L9
        L8:
            r93 = (int) r9;
            goto L9
        }

        public boolean create() {
            int r0 = FilterShaders.loadShader(35633, this.vertexShaderCode);
            int r1 = FilterShaders.loadShader(35632, this.fragmentShaderCode);
            if (r0 == 0) goto L11;
            if (r1 == 0) goto L11;
            int r3 = GLES20.glCreateProgram();
            this.blurShaderProgram = r3;
            GLES20.glAttachShader(r3, r0);
            GLES20.glAttachShader(this.blurShaderProgram, r1);
            GLES20.glBindAttribLocation(this.blurShaderProgram, 0, "position");
            GLES20.glBindAttribLocation(this.blurShaderProgram, 1, "inputTexCoord");
            GLES20.glLinkProgram(this.blurShaderProgram);
            int[] r02 = new int[1];
            GLES20.glGetProgramiv(this.blurShaderProgram, 35714, r02, 0);
            int r03 = r02[0];
            int r5 = this.blurShaderProgram;
            if (r03 != 0) goto L9;
            GLES20.glDeleteProgram(r5);
            this.blurShaderProgram = 0;
        L10:
            return true;
        L9:
            this.blurPositionHandle = GLES20.glGetAttribLocation(r5, "position");
            this.blurInputTexCoordHandle = GLES20.glGetAttribLocation(this.blurShaderProgram, "inputTexCoord");
            this.blurSourceImageHandle = GLES20.glGetUniformLocation(this.blurShaderProgram, "sTexture");
            this.blurWidthHandle = GLES20.glGetUniformLocation(this.blurShaderProgram, "texelWidthOffset");
            this.blurHeightHandle = GLES20.glGetUniformLocation(this.blurShaderProgram, "texelHeightOffset");
        L11:
            return false;
        }

        public void destroy() {
            int r0 = this.blurShaderProgram;
            if (r0 == 0) goto L6;
            GLES20.glDeleteProgram(r0);
            this.blurShaderProgram = 0;
            return;
        }
    }

    public interface FilterShadersDelegate {
        ByteBuffer fillAndGetCurveBuffer();

        float getBlurAngle();

        float getBlurExcludeBlurSize();

        PointF getBlurExcludePoint();

        float getBlurExcludeSize();

        int getBlurType();

        float getContrastValue();

        float getEnhanceValue();

        float getExposureValue();

        float getFadeValue();

        float getGrainValue();

        float getHighlightsValue();

        float getSaturationValue();

        float getShadowsValue();

        float getSharpenValue();

        float getSoftenSkinValue();

        int getTintHighlightsColor();

        float getTintHighlightsIntensityValue();

        int getTintShadowsColor();

        float getTintShadowsIntensityValue();

        float getVignetteValue();

        float getWarmthValue();

        boolean shouldDrawCurvesPass();

        boolean shouldShowOriginal();
    }

    public static class ToneCurve {
        private float[] blueCurve;
        private int[] curveTexture;
        private float[] greenCurve;
        private float[] redCurve;
        private float[] rgbCompositeCurve;

        public ToneCurve() {
            this.curveTexture = new int[1];
            ArrayList<PointF> r0 = new ArrayList();
            r0.add(new PointF(0.0f, 0.0f));
            r0.add(new PointF(0.5f, 0.5f));
            r0.add(new PointF(1.0f, 1.0f));
            ArrayList<PointF> r1 = new ArrayList();
            r1.add(new PointF(0.0f, 0.0f));
            r1.add(new PointF(0.47f, 0.57f));
            r1.add(new PointF(1.0f, 1.0f));
            this.rgbCompositeCurve = getPreparedSplineCurve(r1);
            float[] r02 = getPreparedSplineCurve(r0);
            this.blueCurve = r02;
            this.greenCurve = r02;
            this.redCurve = r02;
            updateToneCurveTexture();
        }

        private float[] getPreparedSplineCurve(ArrayList<PointF> r8) {
            int r0 = r8.size();
            int r1 = 0;
            int r2 = 0;
        L4:
            if (r2 >= r0) goto L6;
            PointF r4 = r8.get(r2);
            r4.x *= 255.0f;
            r4.y *= 255.0f;
            r2 = r2 + 1;
            goto L4
        L6:
            ArrayList<PointF> r7 = splineCurve(r8);
            float r82 = r7.get(0).x;
            if (r82 <= 0.0f) goto L11;
            int r83 = (int) r82;
        L9:
            if (r83 < 0) goto L11;
            r7.add(0, new PointF(r83, 0.0f));
            r83 = r83 - 1;
        L11:
            float r84 = r7.get(r7.size() - 1).x;
            if (r84 >= 255.0f) goto L17;
            int r85 = (int) r84;
        L14:
            r85 = r85 + 1;
            if (r85 > 255) goto L17;
            r7.add(new PointF(r85, 255.0f));
        L17:
            float[] r86 = new float[r7.size()];
            int r02 = r7.size();
        L18:
            if (r1 >= r02) goto L23;
            PointF r22 = r7.get(r1);
            float r3 = (float) Math.sqrt(Math.pow(r22.x - r22.y, 2.0d));
            if (r22.x <= r22.y) goto L22;
            r3 = -r3;
        L22:
            r86[r1] = r3;
            r1 = r1 + 1;
            goto L18
        L23:
            return r86;
        }

        private double[] secondDerivative(ArrayList<PointF> r26) {
            int r1 = r26.size();
            if (r1 <= 0) goto L21;
            int r2 = 1;
            if (r1 == 1) goto L27;
            char r3 = 2;
            int r5 = 0;
            double[][] r4 = (double[][]) Array.newInstance(Double.TYPE, new int[]{r1, 3});
            double[] r6 = new double[r1];
            double[] r7 = r4[0];
            double r8 = 1.0d;
            r7[1] = 1.0d;
            double r10 = 0.0d;
            r7[0] = 0.0d;
            r7[2] = 0.0d;
            int r72 = 1;
        L8:
            int r12 = r1 - 1;
            if (r72 >= r12) goto L11;
            PointF r122 = r26.get(r72 - 1);
            PointF r13 = r26.get(r72);
            int r14 = r72 + 1;
            PointF r15 = r26.get(r14);
            double[] r16 = r4[r72];
            int r25 = r2;
            float r22 = r13.x;
            char r17 = r3;
            float r32 = r122.x;
            int r18 = r5;
            double r19 = r8;
            r16[r18] = (r22 - r32) / 6.0d;
            float r52 = r15.x;
            r16[r25] = (r52 - r32) / 3.0d;
            r16[r17] = (r52 - r22) / 6.0d;
            float r82 = r15.y;
            float r9 = r13.y;
            r6[r72] = ((r82 - r9) / (r52 - r22)) - ((r9 - r122.y) / (r22 - r32));
            r2 = r25;
            r72 = r14;
            r10 = r10;
            r3 = r17;
            r5 = r18;
            r8 = r19;
            goto L8
        L11:
            int r252 = r2;
            char r172 = r3;
            int r182 = r5;
            double r152 = r10;
            r6[r182] = r152;
            r6[r12] = r152;
            double[] r0 = r4[r12];
            r0[r252] = r8;
            r0[r182] = r152;
            r0[r172] = r152;
            int r02 = r252;
        L12:
            if (r02 >= r1) goto L14;
            double[] r23 = r4[r02];
            double r73 = r23[r182];
            int r33 = r02 - 1;
            double[] r53 = r4[r33];
            double r74 = r73 / r53[r252];
            r23[r252] = r23[r252] - (r53[r172] * r74);
            r23[r182] = r152;
            r6[r02] = r6[r02] - (r74 * r6[r33]);
            r02 = r02 + 1;
            goto L12
        L14:
            int r03 = r1 - 2;
        L15:
            if (r03 < 0) goto L17;
            double[] r24 = r4[r03];
            double r75 = r24[r172];
            int r34 = r03 + 1;
            double[] r54 = r4[r34];
            double r76 = r75 / r54[r252];
            r24[r252] = r24[r252] - (r54[r182] * r76);
            r24[r172] = r152;
            r6[r03] = r6[r03] - (r76 * r6[r34]);
            r03 = r03 - 1;
            goto L15
        L17:
            double[] r04 = new double[r1];
            int r55 = r182;
        L18:
            if (r55 >= r1) goto L20;
            r04[r55] = r6[r55] / r4[r55][r252];
            r55 = r55 + 1;
            goto L18
        L20:
            return r04;
        L27:
            return null;
        L21:
            return null;
        }

        private ArrayList<PointF> splineCurve(ArrayList<PointF> r22) {
            double[] r1 = secondDerivative(r22);
            int r2 = r1.length;
            boolean r3 = true;
            if (r2 >= 1) goto L6;
            return null;
        L6:
            ArrayList<PointF> r4 = new ArrayList(r2 + 1);
            int r5 = 0;
        L8:
            if (r5 >= (r2 - 1)) goto L20;
            PointF r6 = r22.get(r5);
            int r7 = r5 + 1;
            PointF r8 = r22.get(r7);
            int r9 = (int) r6.x;
        L10:
            float r10 = r8.x;
            if (r9 >= ((int) r10)) goto L19;
            float r11 = r9;
            boolean r21 = r3;
            ArrayList<PointF> r16 = r4;
            double r13 = (r11 - r12) / (r10 - r12);
            double r32 = 1.0d - r13;
            float r102 = r10 - r6.x;
            double[] r12 = r1;
            int r15 = r2;
            double r14 = r102;
            float r17 = (float) (((r6.y * r32) + (r8.y * r13)) + (((r14 * r14) / 6.0d) * (((((r32 * r32) * r32) - r32) * r12[r5]) + ((((r13 * r13) * r13) - r13) * r12[r7]))));
            float r23 = 255.0f;
            if (r17 <= 255.0f) goto L15;
        L14:
            r17 = r23;
        L18:
            r16.add(new PointF(r11, r17));
            r9 = r9 + 1;
            r3 = r21;
            r4 = r16;
            r1 = r12;
            r2 = r15;
            goto L10
        L15:
            r23 = 0.0f;
            if (r17 >= 0.0f) goto L18;
        L19:
            r5 = r7;
            goto L8
        L20:
            ArrayList<PointF> r18 = r4;
            r18.add(r22.get(r22.size() - 1));
            return r18;
        }

        private void updateToneCurveTexture() {
            GLES20.glGenTextures(1, this.curveTexture, 0);
            GLES20.glBindTexture(3553, this.curveTexture[0]);
            GLES20.glTexParameteri(3553, 10241, 9729);
            GLES20.glTexParameteri(3553, 10240, 9729);
            GLES20.glTexParameteri(3553, 10242, 33071);
            GLES20.glTexParameteri(3553, 10243, 33071);
            ByteBuffer r11 = ByteBuffer.allocateDirect(1024);
            r11.order(ByteOrder.LITTLE_ENDIAN);
            if (this.redCurve.length >= 256) goto L5;
            return;
        L5:
            if (this.greenCurve.length >= 256) goto L7;
            return;
        L7:
            if (this.blueCurve.length >= 256) goto L9;
            return;
        L9:
            if (this.rgbCompositeCurve.length < 256) goto L19;
            int r0 = 0;
        L11:
            if (r0 >= 256) goto L13;
            float r3 = r0;
            int r4 = (int) Math.min(Math.max(this.redCurve[r0] + r3, 0.0f), 255.0f);
            int r7 = (int) Math.min(Math.max(this.greenCurve[r0] + r3, 0.0f), 255.0f);
            int r32 = (int) Math.min(Math.max(r3 + this.blueCurve[r0], 0.0f), 255.0f);
            r11.put((byte) Math.min(Math.max(r32 + this.rgbCompositeCurve[r32], 0.0f), 255.0f));
            r11.put((byte) Math.min(Math.max(r7 + this.rgbCompositeCurve[r7], 0.0f), 255.0f));
            r11.put((byte) Math.min(Math.max(r4 + this.rgbCompositeCurve[r4], 0.0f), 255.0f));
            r11.put((byte) -1);
            r0 = r0 + 1;
            goto L11
        L13:
            r11.position(0);
            GLES20.glTexImage2D(3553, 0, 6408, 256, 1, 0, 6408, 5121, r11);
            return;
        }

        public int getCurveTexture() {
            return this.curveTexture[0];
        }
    }

    /* renamed from: -$$Nest$smfragmentShaderForOptimizedBlurOfRadius, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m10579$$Nest$smfragmentShaderForOptimizedBlurOfRadius(int r0, float r1) {
        return fragmentShaderForOptimizedBlurOfRadius(r0, r1);
    }

    /* renamed from: -$$Nest$smvertexShaderForOptimizedBlurOfRadius, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m10580$$Nest$smvertexShaderForOptimizedBlurOfRadius(int r0, float r1) {
        return vertexShaderForOptimizedBlurOfRadius(r0, r1);
    }

    public FilterShaders(boolean r5, StoryEntry.HDRInfo r6) {
        this.needUpdateBlurTexture = true;
        this.needUpdateSkinTexture = true;
        this.rgbToHsvShaderProgram = new int[2];
        this.rgbToHsvPositionHandle = new int[2];
        this.rgbToHsvInputTexCoordHandle = new int[2];
        this.rgbToHsvSourceImageHandle = new int[2];
        this.enhanceTextures = new int[2];
        this.enhanceFrameBuffer = new int[1];
        this.renderTexture = new int[4];
        this.bitmapTextre = new int[1];
        this.curveTextures = new int[1];
        this.scaleBitmap = true;
        this.isVideo = r5;
        this.hdrInfo = r6;
        ByteBuffer r1 = ByteBuffer.allocateDirect(32);
        r1.order(ByteOrder.nativeOrder());
        FloatBuffer r12 = r1.asFloatBuffer();
        this.vertexBuffer = r12;
        r12.put(new float[]{-1.0f, 1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f});
        this.vertexBuffer.position(0);
        ByteBuffer r2 = ByteBuffer.allocateDirect(32);
        r2.order(ByteOrder.nativeOrder());
        FloatBuffer r22 = r2.asFloatBuffer();
        this.vertexInvertBuffer = r22;
        r22.put(new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f});
        this.vertexInvertBuffer.position(0);
        ByteBuffer r62 = ByteBuffer.allocateDirect(32);
        r62.order(ByteOrder.nativeOrder());
        FloatBuffer r63 = r62.asFloatBuffer();
        this.textureBuffer = r63;
        r63.put(new float[]{0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f});
        this.textureBuffer.position(0);
    }

    private Bitmap createBitmap(Bitmap r8, int r9, float r10) {
        Matrix r5 = new Matrix();
        r5.setScale(r10, r10);
        r5.postRotate(r9);
        return Bitmap.createBitmap(r8, 0, 0, r8.getWidth(), r8.getHeight(), r5, true);
    }

    private static String fragmentShaderForOptimizedBlurOfRadius(int r12, float r13) {
        float[] r0 = new float[(r12 * 2) + 1];
        float r1 = 0.0f;
        int r2 = 0;
        int r3 = 0;
    L3:
        int r4 = r12 + 1;
        if (r3 >= r4) goto L10;
        double r42 = r13;
        float r43 = (float) ((1.0d / Math.sqrt(Math.pow(r42, 2.0d) * 6.283185307179586d)) * Math.exp((-Math.pow(r3, 2.0d)) / (Math.pow(r42, 2.0d) * 2.0d)));
        r0[r3] = r43;
        if (r3 != 0) goto L8;
        r1 = r1 + r43;
    L9:
        r3 = r3 + 1;
        goto L3
    L8:
        r1 = (float) (r1 + (r43 * 2.0d));
        goto L9
    L10:
        int r132 = 0;
    L11:
        if (r132 >= r4) goto L13;
        r0[r132] = r0[r132] / r1;
        r132 = r132 + 1;
        goto L11
    L13:
        int r133 = (r12 / 2) + (r12 % 2);
        int r122 = Math.min(r133, 7);
        StringBuilder r14 = new StringBuilder("uniform sampler2D sTexture;\nuniform highp float texelWidthOffset;\nuniform highp float texelHeightOffset;\n");
        Locale r32 = Locale.US;
        r14.append(String.format(r32, "varying highp vec2 blurCoordinates[%d];\n", new Object[]{Integer.valueOf((r122 * 2) + 1)}));
        r14.append("void main()\n{\nlowp vec4 sum = vec4(0.0);\n");
        r14.append(String.format(r32, "sum += texture2D(sTexture, blurCoordinates[0]) * %f;\n", new Object[]{Float.valueOf(r0[0])}));
    L14:
        if (r2 >= r122) goto L16;
        int r33 = r2 * 2;
        int r44 = r33 + 1;
        int r34 = r33 + 2;
        float r5 = r0[r44] + r0[r34];
        Locale r6 = Locale.US;
        r14.append(String.format(r6, "sum += texture2D(sTexture, blurCoordinates[%d]) * %f;\n", new Object[]{Integer.valueOf(r44), Float.valueOf(r5)}));
        r14.append(String.format(r6, "sum += texture2D(sTexture, blurCoordinates[%d]) * %f;\n", new Object[]{Integer.valueOf(r34), Float.valueOf(r5)}));
        r2 = r2 + 1;
        goto L14
    L16:
        if (r133 <= r122) goto L20;
        r14.append("highp vec2 singleStepOffset = vec2(texelWidthOffset, texelHeightOffset);\n");
    L18:
        if (r122 >= r133) goto L20;
        int r22 = r122 * 2;
        int r35 = r22 + 1;
        float r45 = r0[r35];
        int r23 = r22 + 2;
        float r52 = r0[r23];
        float r62 = r45 + r52;
        float r46 = ((r45 * r35) + (r52 * r23)) / r62;
        Locale r24 = Locale.US;
        r14.append(String.format(r24, "sum += texture2D(sTexture, blurCoordinates[0] + singleStepOffset * %f) * %f;\n", new Object[]{Float.valueOf(r46), Float.valueOf(r62)}));
        r14.append(String.format(r24, "sum += texture2D(sTexture, blurCoordinates[0] - singleStepOffset * %f) * %f;\n", new Object[]{Float.valueOf(r46), Float.valueOf(r62)}));
        r122 = r122 + 1;
    L20:
        r14.append("gl_FragColor = sum;\n}\n");
        return r14.toString();
    }

    public static FilterShadersDelegate getFilterShadersDelegate(MediaController.SavedFilterState r1) {
        return new AnonymousClass1(r1);
    }

    public static int loadShader(int r3, String r4) {
        int r32 = GLES20.glCreateShader(r3);
        GLES20.glShaderSource(r32, r4);
        GLES20.glCompileShader(r32);
        int[] r0 = new int[1];
        GLES20.glGetShaderiv(r32, 35713, r0, 0);
        if (r0[0] == 0) goto L5;
        return r32;
    L5:
        if (BuildVars.LOGS_ENABLED == false) goto L7;
        FileLog.e(GLES20.glGetShaderInfoLog(r32));
        FileLog.e("shader code:\n " + r4);
    L7:
        GLES20.glDeleteShader(r32);
        return 0;
    }

    private void loadTexture(Bitmap r21, int r22, int r23, int r24) {
        Bitmap r1 = r21;
        this.renderBufferWidth = r23;
        this.renderBufferHeight = r24;
        int r4 = 0;
        if (this.renderFrameBuffer != null) goto L6;
        int[] r5 = new int[4];
        this.renderFrameBuffer = r5;
        GLES20.glGenFramebuffers(4, r5, 0);
        GLES20.glGenTextures(4, this.renderTexture, 0);
    L6:
        if (r1 == null) goto L35;
        if (r1.isRecycled() == true) goto L35;
        GLES20.glGenTextures(1, this.bitmapTextre, 0);
        float r11 = AndroidUtilities.getPhotoSize(true);
        boolean r12 = this.scaleBitmap;
        if (r12 == false) goto L16;
        if (this.renderBufferWidth <= r11) goto L14;
    L17:
        if (r12 == true) goto L19;
    L26:
        float r122 = 1.0f;
    L27:
        int r112 = r22 % 360;
        if (r112 != 90) goto L30;
    L31:
        int r113 = this.renderBufferWidth;
        this.renderBufferWidth = this.renderBufferHeight;
        this.renderBufferHeight = r113;
    L32:
        r1 = createBitmap(r1, r22, r122);
    L33:
        GLES20.glBindTexture(3553, this.bitmapTextre[0]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLUtils.texImage2D(3553, 0, r1, 0);
        goto L35
    L30:
        if (r112 != 270) goto L32;
    L19:
        if (this.renderBufferWidth <= r11) goto L21;
    L22:
        r122 = r11 / r1.getWidth();
        float r13 = r11 / r1.getHeight();
        if (r122 >= r13) goto L25;
        this.renderBufferWidth = (int) r11;
        this.renderBufferHeight = (int) (r1.getHeight() * r122);
        goto L27
    L25:
        this.renderBufferHeight = (int) r11;
        this.renderBufferWidth = (int) (r1.getWidth() * r13);
        r122 = r13;
        goto L27
    L21:
        if (this.renderBufferHeight <= r11) goto L26;
    L14:
        if (this.renderBufferHeight > r11) goto L17;
    L16:
        if ((r22 % 360) == 0) goto L33;
    L35:
        if (r4 >= 2) goto L37;
        GLES20.glBindTexture(3553, this.renderTexture[r4]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexImage2D(3553, 0, 6408, this.renderBufferWidth, this.renderBufferHeight, 0, 6408, 5121, null);
        r4 = r4 + 1;
        goto L35
    }

    private boolean setupExternalShaders() {
        StoryEntry.HDRInfo r1 = this.hdrInfo;
        if (r1 == null) goto L5;
        int r12 = r1.getHDRType();
    L6:
        int r3 = 2;
        int r4 = 1;
        if (r12 != 1) goto L9;
        String r6 = AndroidUtilities.readRes(R.raw.hdr2sdr_hlg);
    L12:
        boolean r7 = this.isVideo;
        if (r7 == false) goto L15;
        String r8 = "#extension GL_OES_EGL_image_external : require";
    L17:
        if (r7 == false) goto L19;
        String r72 = "samplerExternalOES";
    L20:
        int[] r10 = new int[1];
        int r11 = 0;
    L21:
        boolean r122 = this.isVideo;
        if (r122 == false) goto L24;
        int r13 = r3;
    L25:
        int r18 = r12;
        String r19 = r6;
        if (r11 >= r13) goto L52;
        if (r11 != 1) goto L38;
        if (r122 == false) goto L38;
        if (r18 == 0) goto L32;
        int r14 = loadShader(35633, "attribute vec4 position;uniform mat4 videoMatrix;attribute vec4 inputTexCoord;varying vec2 vTextureCoord;void main() {gl_Position = position;vTextureCoord = vec2(videoMatrix * inputTexCoord).xy;}");
        int r20 = r14;
        String r21 = "texSize";
        int r123 = loadShader(35632, String.format(Locale.US, "%1$s\nprecision highp float;varying vec2 vTextureCoord;vec3 rgb_to_hsv(vec3 c) {vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);vec4 p = c.g < c.b ? vec4(c.bg, K.wz) : vec4(c.gb, K.xy);vec4 q = c.r < p.x ? vec4(p.xyw, c.r) : vec4(c.r, p.yzx);float d = q.x - min(q.w, q.y);float e = 1.0e-10;return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);}void main() {vec4 texel = TEX(vTextureCoord);gl_FragColor = vec4(rgb_to_hsv(texel.rgb), texel.a);}", new Object[]{r19}));
    L33:
        if (r20 == 0) goto L37;
        if (r123 == 0) goto L37;
    L36:
        int r42 = r20;
        GLES20.glDeleteProgram(this.rgbToHsvShaderProgram[r11]);
        if (r42 == 0) goto L49;
        if (r123 == 0) goto L100;
        this.rgbToHsvShaderProgram[r11] = GLES20.glCreateProgram();
        GLES20.glAttachShader(this.rgbToHsvShaderProgram[r11], r42);
        GLES20.glAttachShader(this.rgbToHsvShaderProgram[r11], r123);
        GLES20.glBindAttribLocation(this.rgbToHsvShaderProgram[r11], 0, "position");
        GLES20.glBindAttribLocation(this.rgbToHsvShaderProgram[r11], 1, "inputTexCoord");
        GLES20.glLinkProgram(this.rgbToHsvShaderProgram[r11]);
        GLES20.glGetProgramiv(this.rgbToHsvShaderProgram[r11], 35714, r10, 0);
        if (r10[0] != 0) goto L45;
        GLES20.glDeleteProgram(this.rgbToHsvShaderProgram[r11]);
        this.rgbToHsvShaderProgram[r11] = 0;
    L48:
        r11 = r11 + 1;
        r12 = r18;
        r6 = r19;
        r3 = 2;
        r4 = 1;
        goto L21
    L45:
        this.rgbToHsvPositionHandle[r11] = GLES20.glGetAttribLocation(this.rgbToHsvShaderProgram[r11], "position");
        this.rgbToHsvInputTexCoordHandle[r11] = GLES20.glGetAttribLocation(this.rgbToHsvShaderProgram[r11], "inputTexCoord");
        this.rgbToHsvSourceImageHandle[r11] = GLES20.glGetUniformLocation(this.rgbToHsvShaderProgram[r11], "sTexture");
        if (r11 != 1) goto L48;
        this.rgbToHsvMatrixHandle = GLES20.glGetUniformLocation(this.rgbToHsvShaderProgram[r11], "videoMatrix");
        this.rgbToHsvTexSizeHandle = GLES20.glGetUniformLocation(this.rgbToHsvShaderProgram[r11], r21);
        goto L48
    L100:
        return false;
    L49:
        return false;
    L37:
        r20 = loadShader(35633, "attribute vec4 position;uniform mat4 videoMatrix;attribute vec4 inputTexCoord;varying vec2 vTextureCoord;void main() {gl_Position = position;vTextureCoord = vec2(videoMatrix * inputTexCoord).xy;}");
        r123 = loadShader(35632, String.format(Locale.US, "%1$s\nprecision highp float;varying vec2 vTextureCoord;uniform %2$s sTexture;vec3 rgb_to_hsv(vec3 c) {vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);vec4 p = c.g < c.b ? vec4(c.bg, K.wz) : vec4(c.gb, K.xy);vec4 q = c.r < p.x ? vec4(p.xyw, c.r) : vec4(c.r, p.yzx);float d = q.x - min(q.w, q.y);float e = 1.0e-10;return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);}void main() {vec4 texel = texture2D(sTexture, vTextureCoord);gl_FragColor = vec4(rgb_to_hsv(texel.rgb), texel.a);}", new Object[]{r8, r72}));
        goto L36
    L32:
        r21 = "texSize";
        r123 = 0;
        r20 = 0;
    L38:
        r21 = "texSize";
        r20 = loadShader(35633, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 vTextureCoord;void main() {gl_Position = position;vTextureCoord = inputTexCoord;}");
        r123 = loadShader(35632, String.format(Locale.US, "%1$s\nprecision highp float;varying vec2 vTextureCoord;uniform %2$s sTexture;vec3 rgb_to_hsv(vec3 c) {vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);vec4 p = c.g < c.b ? vec4(c.bg, K.wz) : vec4(c.gb, K.xy);vec4 q = c.r < p.x ? vec4(p.xyw, c.r) : vec4(c.r, p.yzx);float d = q.x - min(q.w, q.y);float e = 1.0e-10;return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);}void main() {vec4 texel = texture2D(sTexture, vTextureCoord);gl_FragColor = vec4(rgb_to_hsv(texel.rgb), texel.a);}", new Object[]{_UrlKt.FRAGMENT_ENCODE_SET, "sampler2D"}));
        goto L36
    L52:
        if (r122 == false) goto L59;
        if (r18 == 0) goto L55;
        int r112 = loadShader(35633, "attribute vec4 position;uniform mat4 videoMatrix;attribute vec4 inputTexCoord;varying vec2 vTextureCoord;void main() {gl_Position = position;vTextureCoord = vec2(videoMatrix * inputTexCoord).xy;}");
        int r9 = loadShader(35632, String.format(Locale.US, "%1$s\nprecision lowp float;varying highp vec2 vTextureCoord;void main() {vec4 inp = TEX(vTextureCoord);vec4 image = vec4(inp.rgb * pow(2.0, -1.0), inp.w);vec4 base = vec4(image.g, image.g, image.g, 1.0);vec4 overlay = vec4(image.b, image.b, image.b, 1.0);float ba = 2.0 * overlay.b * base.b + overlay.b * (1.0 - base.a) + base.b * (1.0 - overlay.a);gl_FragColor = vec4(ba,ba,ba,image.a);}", new Object[]{r19}));
    L56:
        if (r112 == 0) goto L58;
        if (r9 == 0) goto L58;
    L60:
        GLES20.glDeleteProgram(this.greenAndBlueChannelOverlayProgram);
        if (r112 == 0) goto L92;
        if (r9 == 0) goto L104;
        int r43 = GLES20.glCreateProgram();
        this.greenAndBlueChannelOverlayProgram = r43;
        GLES20.glAttachShader(r43, r112);
        GLES20.glAttachShader(this.greenAndBlueChannelOverlayProgram, r9);
        GLES20.glBindAttribLocation(this.greenAndBlueChannelOverlayProgram, 0, "position");
        GLES20.glBindAttribLocation(this.greenAndBlueChannelOverlayProgram, 1, "inputTexCoord");
        GLES20.glLinkProgram(this.greenAndBlueChannelOverlayProgram);
        GLES20.glGetProgramiv(this.greenAndBlueChannelOverlayProgram, 35714, r10, 0);
        int r44 = r10[0];
        int r92 = this.greenAndBlueChannelOverlayProgram;
        if (r44 != 0) goto L66;
        GLES20.glDeleteProgram(r92);
        this.greenAndBlueChannelOverlayProgram = 0;
    L70:
        if (this.isVideo == true) goto L72;
        int r113 = loadShader(35633, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 vTextureCoord;varying vec2 texCoord2;void main() {gl_Position = position;vTextureCoord = inputTexCoord;texCoord2 = inputTexCoord;}");
        int r93 = loadShader(35632, String.format(Locale.US, "%1$s\nprecision lowp float;varying highp vec2 vTextureCoord;varying highp vec2 texCoord2;uniform %2$s sTexture;uniform sampler2D toneCurveTexture;uniform sampler2D inputImageTexture3;uniform lowp float mixturePercent;void main() {vec4 image = texture2D(sTexture, vTextureCoord);vec4 mask = texture2D(inputImageTexture3, texCoord2);float redCurveValue = texture2D(toneCurveTexture, vec2(image.r, 0.0)).r;float greenCurveValue = texture2D(toneCurveTexture, vec2(image.g, 0.0)).g;float blueCurveValue = texture2D(toneCurveTexture, vec2(image.b, 0.0)).b;vec4 result = vec4(redCurveValue, greenCurveValue, blueCurveValue, image.a);vec4 tone = mix(image, result, mixturePercent);gl_FragColor = vec4(mix(image.rgb, tone.rgb, 1.0 - mask.b), 1.0);}", new Object[]{r8, r72}));
    L79:
        GLES20.glDeleteProgram(this.compositeProgram);
        if (r113 == 0) goto L90;
        if (r93 == 0) goto L103;
        int r45 = GLES20.glCreateProgram();
        this.compositeProgram = r45;
        GLES20.glAttachShader(r45, r113);
        GLES20.glAttachShader(this.compositeProgram, r93);
        GLES20.glBindAttribLocation(this.compositeProgram, 0, "position");
        GLES20.glBindAttribLocation(this.compositeProgram, 1, "inputTexCoord");
        GLES20.glLinkProgram(this.compositeProgram);
        GLES20.glGetProgramiv(this.compositeProgram, 35714, r10, 0);
        int r46 = r10[0];
        int r73 = this.compositeProgram;
        if (r46 != 0) goto L86;
        GLES20.glDeleteProgram(r73);
        this.compositeProgram = 0;
        return true;
    L86:
        this.compositePositionHandle = GLES20.glGetAttribLocation(r73, "position");
        this.compositeInputTexCoordHandle = GLES20.glGetAttribLocation(this.compositeProgram, "inputTexCoord");
        this.compositeSourceImageHandle = GLES20.glGetUniformLocation(this.compositeProgram, "sTexture");
        this.compositeInputImageHandle = GLES20.glGetUniformLocation(this.compositeProgram, "inputImageTexture3");
        this.compositeCurveImageHandle = GLES20.glGetUniformLocation(this.compositeProgram, "toneCurveTexture");
        this.compositeMixtureHandle = GLES20.glGetUniformLocation(this.compositeProgram, "mixturePercent");
        if (this.isVideo == false) goto L101;
        this.compositeMatrixHandle = GLES20.glGetUniformLocation(this.compositeProgram, "videoMatrix");
        this.compositeTexSizeHandle = GLES20.glGetUniformLocation(this.compositeProgram, "texSize");
        return true;
    L101:
        return true;
    L103:
        return false;
    L90:
        return false;
    L72:
        if (r18 == 0) goto L74;
        r113 = loadShader(35633, "attribute vec4 position;uniform mat4 videoMatrix;attribute vec4 inputTexCoord;varying vec2 vTextureCoord;varying vec2 texCoord2;void main() {gl_Position = position;vTextureCoord = vec2(videoMatrix * inputTexCoord).xy;texCoord2 = inputTexCoord.xy;}");
        r93 = loadShader(35632, String.format(Locale.US, "%1$s\nprecision lowp float;varying highp vec2 vTextureCoord;varying highp vec2 texCoord2;uniform sampler2D toneCurveTexture;uniform sampler2D inputImageTexture3;uniform lowp float mixturePercent;void main() {vec4 image = TEX(vTextureCoord);vec4 mask = texture2D(inputImageTexture3, texCoord2);float redCurveValue = texture2D(toneCurveTexture, vec2(image.r, 0.0)).r;float greenCurveValue = texture2D(toneCurveTexture, vec2(image.g, 0.0)).g;float blueCurveValue = texture2D(toneCurveTexture, vec2(image.b, 0.0)).b;vec4 result = vec4(redCurveValue, greenCurveValue, blueCurveValue, image.a);vec4 tone = mix(image, result, mixturePercent);gl_FragColor = vec4(mix(image.rgb, tone.rgb, 1.0 - mask.b), 1.0);}", new Object[]{r19}));
    L75:
        if (r113 == 0) goto L77;
        if (r93 != 0) goto L79;
    L77:
        r113 = loadShader(35633, "attribute vec4 position;uniform mat4 videoMatrix;attribute vec4 inputTexCoord;varying vec2 vTextureCoord;varying vec2 texCoord2;void main() {gl_Position = position;vTextureCoord = vec2(videoMatrix * inputTexCoord).xy;texCoord2 = inputTexCoord.xy;}");
        r93 = loadShader(35632, String.format(Locale.US, "%1$s\nprecision lowp float;varying highp vec2 vTextureCoord;varying highp vec2 texCoord2;uniform %2$s sTexture;uniform sampler2D toneCurveTexture;uniform sampler2D inputImageTexture3;uniform lowp float mixturePercent;void main() {vec4 image = texture2D(sTexture, vTextureCoord);vec4 mask = texture2D(inputImageTexture3, texCoord2);float redCurveValue = texture2D(toneCurveTexture, vec2(image.r, 0.0)).r;float greenCurveValue = texture2D(toneCurveTexture, vec2(image.g, 0.0)).g;float blueCurveValue = texture2D(toneCurveTexture, vec2(image.b, 0.0)).b;vec4 result = vec4(redCurveValue, greenCurveValue, blueCurveValue, image.a);vec4 tone = mix(image, result, mixturePercent);gl_FragColor = vec4(mix(image.rgb, tone.rgb, 1.0 - mask.b), 1.0);}", new Object[]{r8, r72}));
        goto L79
    L74:
        r93 = 0;
        r113 = 0;
        goto L75
    L66:
        this.greenAndBlueChannelOverlayPositionHandle = GLES20.glGetAttribLocation(r92, "position");
        this.greenAndBlueChannelOverlayInputTexCoordHandle = GLES20.glGetAttribLocation(this.greenAndBlueChannelOverlayProgram, "inputTexCoord");
        this.greenAndBlueChannelOverlaySourceImageHandle = GLES20.glGetUniformLocation(this.greenAndBlueChannelOverlayProgram, "sTexture");
        if (this.isVideo == false) goto L70;
        this.greenAndBlueChannelOverlayMatrixHandle = GLES20.glGetUniformLocation(this.greenAndBlueChannelOverlayProgram, "videoMatrix");
        this.greenAndBlueChannelOverlayTexSizeHandle = GLES20.glGetUniformLocation(this.greenAndBlueChannelOverlayProgram, "texSize");
        goto L70
    L104:
        return false;
    L92:
        return false;
    L58:
        r112 = loadShader(35633, "attribute vec4 position;uniform mat4 videoMatrix;attribute vec4 inputTexCoord;varying vec2 vTextureCoord;void main() {gl_Position = position;vTextureCoord = vec2(videoMatrix * inputTexCoord).xy;}");
        r9 = loadShader(35632, String.format(Locale.US, "%1$s\nprecision lowp float;varying highp vec2 vTextureCoord;uniform %2$s sTexture;void main() {vec4 inp = texture2D(sTexture, vTextureCoord);vec4 image = vec4(inp.rgb * pow(2.0, -1.0), inp.w);vec4 base = vec4(image.g, image.g, image.g, 1.0);vec4 overlay = vec4(image.b, image.b, image.b, 1.0);float ba = 2.0 * overlay.b * base.b + overlay.b * (1.0 - base.a) + base.b * (1.0 - overlay.a);gl_FragColor = vec4(ba,ba,ba,image.a);}", new Object[]{r8, r72}));
        goto L60
    L55:
        r9 = 0;
        r112 = 0;
        goto L56
    L59:
        r112 = loadShader(35633, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 vTextureCoord;void main() {gl_Position = position;vTextureCoord = inputTexCoord;}");
        r9 = loadShader(35632, String.format(Locale.US, "%1$s\nprecision lowp float;varying highp vec2 vTextureCoord;uniform %2$s sTexture;void main() {vec4 inp = texture2D(sTexture, vTextureCoord);vec4 image = vec4(inp.rgb * pow(2.0, -1.0), inp.w);vec4 base = vec4(image.g, image.g, image.g, 1.0);vec4 overlay = vec4(image.b, image.b, image.b, 1.0);float ba = 2.0 * overlay.b * base.b + overlay.b * (1.0 - base.a) + base.b * (1.0 - overlay.a);gl_FragColor = vec4(ba,ba,ba,image.a);}", new Object[]{r8, r72}));
        goto L60
    L24:
        r13 = r4;
        goto L25
    L19:
        r72 = "sampler2D";
        goto L20
    L15:
        r8 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L17
    L9:
        if (r12 != 2) goto L11;
        r6 = AndroidUtilities.readRes(R.raw.hdr2sdr_pq);
        goto L12
    L11:
        r6 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L12
    L5:
        r12 = 0;
        goto L6
    }

    private static String vertexShaderForOptimizedBlurOfRadius(int r12, float r13) {
        float[] r0 = new float[(r12 * 2) + 1];
        float r1 = 0.0f;
        int r2 = 0;
        int r3 = 0;
    L3:
        int r4 = r12 + 1;
        if (r3 >= r4) goto L10;
        double r42 = r13;
        float r43 = (float) ((1.0d / Math.sqrt(Math.pow(r42, 2.0d) * 6.283185307179586d)) * Math.exp((-Math.pow(r3, 2.0d)) / (Math.pow(r42, 2.0d) * 2.0d)));
        r0[r3] = r43;
        if (r3 != 0) goto L8;
        r1 = r1 + r43;
    L9:
        r3 = r3 + 1;
        goto L3
    L8:
        r1 = (float) (r1 + (r43 * 2.0d));
        goto L9
    L10:
        int r132 = 0;
    L11:
        if (r132 >= r4) goto L13;
        r0[r132] = r0[r132] / r1;
        r132 = r132 + 1;
        goto L11
    L13:
        int r122 = Math.min((r12 / 2) + (r12 % 2), 7);
        float[] r133 = new float[r122];
        int r14 = 0;
    L14:
        if (r14 >= r122) goto L16;
        int r32 = r14 * 2;
        int r44 = r32 + 1;
        float r5 = r0[r44];
        int r33 = r32 + 2;
        float r6 = r0[r33];
        r133[r14] = ((r5 * r44) + (r6 * r33)) / (r5 + r6);
        r14 = r14 + 1;
        goto L14
    L16:
        StringBuilder r02 = new StringBuilder("attribute vec4 position;\nattribute vec4 inputTexCoord;\nuniform float texelWidthOffset;\nuniform float texelHeightOffset;\n");
        r02.append(String.format(Locale.US, "varying vec2 blurCoordinates[%d];\n", new Object[]{Integer.valueOf((r122 * 2) + 1)}));
        r02.append("void main()\n{\ngl_Position = position;\nvec2 singleStepOffset = vec2(texelWidthOffset, texelHeightOffset);\nblurCoordinates[0] = inputTexCoord.xy;\n");
    L17:
        if (r2 >= r122) goto L19;
        int r34 = r2 * 2;
        r02.append(String.format(Locale.US, "blurCoordinates[%d] = inputTexCoord.xy + singleStepOffset * %f;\nblurCoordinates[%d] = inputTexCoord.xy - singleStepOffset * %f;\n", new Object[]{Integer.valueOf(r34 + 1), Float.valueOf(r133[r2]), Integer.valueOf(r34 + 2), Float.valueOf(r133[r2])}));
        r2 = r2 + 1;
        goto L17
    L19:
        r02.append("}");
        return r02.toString();
    }

    public boolean create() {
        GLES20.glGenTextures(1, this.curveTextures, 0);
        GLES20.glGenTextures(2, this.enhanceTextures, 0);
        GLES20.glGenFramebuffers(1, this.enhanceFrameBuffer, 0);
        GLES20.glBindTexture(3553, this.enhanceTextures[1]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glBindTexture(3553, this.curveTextures[0]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        int[] r1 = new int[1];
        int r6 = loadShader(35633, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 vTextureCoord;void main() {gl_Position = position;vTextureCoord = inputTexCoord;}");
        int r7 = loadShader(35632, "varying highp vec2 vTextureCoord;uniform sampler2D sTexture;uniform highp float width;uniform highp float height;uniform sampler2D curvesImage;uniform lowp float skipTone;uniform lowp float shadows;const mediump vec3 hsLuminanceWeighting = vec3(0.3, 0.3, 0.3);uniform lowp float highlights;uniform lowp float contrast;uniform lowp float fadeAmount;const mediump vec3 satLuminanceWeighting = vec3(0.2126, 0.7152, 0.0722);uniform lowp float saturation;uniform lowp float shadowsTintIntensity;uniform lowp float highlightsTintIntensity;uniform lowp vec3 shadowsTintColor;uniform lowp vec3 highlightsTintColor;uniform lowp float exposure;uniform lowp float warmth;uniform lowp float grain;const lowp float permTexUnit = 1.0 / 256.0;const lowp float permTexUnitHalf = 0.5 / 256.0;const lowp float grainsize = 2.3;uniform lowp float vignette;highp float getLuma(highp vec3 rgbP) {return (0.299 * rgbP.r) + (0.587 * rgbP.g) + (0.114 * rgbP.b);}lowp vec3 rgbToHsv(lowp vec3 c) {highp vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);highp vec4 p = c.g < c.b ? vec4(c.bg, K.wz) : vec4(c.gb, K.xy);highp vec4 q = c.r < p.x ? vec4(p.xyw, c.r) : vec4(c.r, p.yzx);highp float d = q.x - min(q.w, q.y);highp float e = 1.0e-10;return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);}lowp vec3 hsvToRgb(lowp vec3 c) {highp vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);highp vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);}highp vec3 rgbToHsl(highp vec3 color) {highp vec3 hsl;highp float fmin = min(min(color.r, color.g), color.b);highp float fmax = max(max(color.r, color.g), color.b);highp float delta = fmax - fmin;hsl.z = (fmax + fmin) / 2.0;if (delta == 0.0) {hsl.x = 0.0;hsl.y = 0.0;} else {if (hsl.z < 0.5) {hsl.y = delta / (fmax + fmin);} else {hsl.y = delta / (2.0 - fmax - fmin);}highp float deltaR = (((fmax - color.r) / 6.0) + (delta / 2.0)) / delta;highp float deltaG = (((fmax - color.g) / 6.0) + (delta / 2.0)) / delta;highp float deltaB = (((fmax - color.b) / 6.0) + (delta / 2.0)) / delta;if (color.r == fmax) {hsl.x = deltaB - deltaG;} else if (color.g == fmax) {hsl.x = (1.0 / 3.0) + deltaR - deltaB;} else if (color.b == fmax) {hsl.x = (2.0 / 3.0) + deltaG - deltaR;}if (hsl.x < 0.0) {hsl.x += 1.0;} else if (hsl.x > 1.0) {hsl.x -= 1.0;}}return hsl;}highp float hueToRgb(highp float f1, highp float f2, highp float hue) {if (hue < 0.0) {hue += 1.0;} else if (hue > 1.0) {hue -= 1.0;}highp float res;if ((6.0 * hue) < 1.0) {res = f1 + (f2 - f1) * 6.0 * hue;} else if ((2.0 * hue) < 1.0) {res = f2;} else if ((3.0 * hue) < 2.0) {res = f1 + (f2 - f1) * ((2.0 / 3.0) - hue) * 6.0;} else {res = f1;}return res;}highp vec3 hslToRgb(highp vec3 hsl) {if (hsl.y == 0.0) {return vec3(hsl.z);} else {highp float f2;if (hsl.z < 0.5) {f2 = hsl.z * (1.0 + hsl.y);} else {f2 = (hsl.z + hsl.y) - (hsl.y * hsl.z);}highp float f1 = 2.0 * hsl.z - f2;return vec3(hueToRgb(f1, f2, hsl.x + (1.0/3.0)), hueToRgb(f1, f2, hsl.x), hueToRgb(f1, f2, hsl.x - (1.0/3.0)));}}highp vec3 rgbToYuv(highp vec3 inP) {highp float luma = getLuma(inP);return vec3(luma, (1.0 / 1.772) * (inP.b - luma), (1.0 / 1.402) * (inP.r - luma));}lowp vec3 yuvToRgb(highp vec3 inP) {return vec3(1.402 * inP.b + inP.r, (inP.r - (0.299 * 1.402 / 0.587) * inP.b - (0.114 * 1.772 / 0.587) * inP.g), 1.772 * inP.g + inP.r);}lowp float easeInOutSigmoid(lowp float value, lowp float strength) {if (value > 0.5) {return 1.0 - pow(2.0 - 2.0 * value, 1.0 / (1.0 - strength)) * 0.5;} else {return pow(2.0 * value, 1.0 / (1.0 - strength)) * 0.5;}}lowp vec3 applyLuminanceCurve(lowp vec3 pixel) {highp float index = floor(clamp(pixel.z / (1.0 / 200.0), 0.0, 199.0));pixel.y = mix(0.0, pixel.y, smoothstep(0.0, 0.1, pixel.z) * (1.0 - smoothstep(0.8, 1.0, pixel.z)));pixel.z = texture2D(curvesImage, vec2(1.0 / 200.0 * index, 0)).a;return pixel;}lowp vec3 applyRGBCurve(lowp vec3 pixel) {highp float index = floor(clamp(pixel.r / (1.0 / 200.0), 0.0, 199.0));pixel.r = texture2D(curvesImage, vec2(1.0 / 200.0 * index, 0)).r;index = floor(clamp(pixel.g / (1.0 / 200.0), 0.0, 199.0));pixel.g = clamp(texture2D(curvesImage, vec2(1.0 / 200.0 * index, 0)).g, 0.0, 1.0);index = floor(clamp(pixel.b / (1.0 / 200.0), 0.0, 199.0));pixel.b = clamp(texture2D(curvesImage, vec2(1.0 / 200.0 * index, 0)).b, 0.0, 1.0);return pixel;}highp vec3 fadeAdjust(highp vec3 color, highp float fadeVal) {return (color * (1.0 - fadeVal)) + ((color + (vec3(-0.9772) * pow(vec3(color), vec3(3.0)) + vec3(1.708) * pow(vec3(color), vec3(2.0)) + vec3(-0.1603) * vec3(color) + vec3(0.2878) - color * vec3(0.9))) * fadeVal);}lowp vec3 tintRaiseShadowsCurve(lowp vec3 color) {return vec3(-0.003671) * pow(color, vec3(3.0)) + vec3(0.3842) * pow(color, vec3(2.0)) + vec3(0.3764) * color + vec3(0.2515);}lowp vec3 tintShadows(lowp vec3 texel, lowp vec3 tintColor, lowp float tintAmount) {return clamp(mix(texel, mix(texel, tintRaiseShadowsCurve(texel), tintColor), tintAmount), 0.0, 1.0);} lowp vec3 tintHighlights(lowp vec3 texel, lowp vec3 tintColor, lowp float tintAmount) {return clamp(mix(texel, mix(texel, vec3(1.0) - tintRaiseShadowsCurve(vec3(1.0) - texel), (vec3(1.0) - tintColor)), tintAmount), 0.0, 1.0);}highp vec4 rnm(in highp vec2 tc) {highp float noise = sin(dot(tc, vec2(12.9898, 78.233))) * 43758.5453;return vec4(fract(noise), fract(noise * 1.2154), fract(noise * 1.3453), fract(noise * 1.3647)) * 2.0 - 1.0;}highp float fade(in highp float t) {return t * t * t * (t * (t * 6.0 - 15.0) + 10.0);}highp float pnoise3D(in highp vec3 p) {highp vec3 pi = permTexUnit * floor(p) + permTexUnitHalf;highp vec3 pf = fract(p);highp float perm = rnm(pi.xy).a;highp float n000 = dot(rnm(vec2(perm, pi.z)).rgb * 4.0 - 1.0, pf);highp float n001 = dot(rnm(vec2(perm, pi.z + permTexUnit)).rgb * 4.0 - 1.0, pf - vec3(0.0, 0.0, 1.0));perm = rnm(pi.xy + vec2(0.0, permTexUnit)).a;highp float n010 = dot(rnm(vec2(perm, pi.z)).rgb * 4.0 - 1.0, pf - vec3(0.0, 1.0, 0.0));highp float n011 = dot(rnm(vec2(perm, pi.z + permTexUnit)).rgb * 4.0 - 1.0, pf - vec3(0.0, 1.0, 1.0));perm = rnm(pi.xy + vec2(permTexUnit, 0.0)).a;highp float n100 = dot(rnm(vec2(perm, pi.z)).rgb * 4.0 - 1.0, pf - vec3(1.0, 0.0, 0.0));highp float n101 = dot(rnm(vec2(perm, pi.z + permTexUnit)).rgb * 4.0 - 1.0, pf - vec3(1.0, 0.0, 1.0));perm = rnm(pi.xy + vec2(permTexUnit, permTexUnit)).a;highp float n110 = dot(rnm(vec2(perm, pi.z)).rgb * 4.0 - 1.0, pf - vec3(1.0, 1.0, 0.0));highp float n111 = dot(rnm(vec2(perm, pi.z + permTexUnit)).rgb * 4.0 - 1.0, pf - vec3(1.0, 1.0, 1.0));highp vec4 n_x = mix(vec4(n000, n001, n010, n011), vec4(n100, n101, n110, n111), fade(pf.x));highp vec2 n_xy = mix(n_x.xy, n_x.zw, fade(pf.y));return mix(n_xy.x, n_xy.y, fade(pf.z));}lowp vec2 coordRot(in lowp vec2 tc, in lowp float angle) {return vec2(((tc.x * 2.0 - 1.0) * cos(angle) - (tc.y * 2.0 - 1.0) * sin(angle)) * 0.5 + 0.5, ((tc.y * 2.0 - 1.0) * cos(angle) + (tc.x * 2.0 - 1.0) * sin(angle)) * 0.5 + 0.5);}void main() {lowp vec4 source = texture2D(sTexture, vTextureCoord);lowp vec4 result = source;const lowp float toolEpsilon = 0.005;if (skipTone < toolEpsilon) {result = vec4(applyRGBCurve(hslToRgb(applyLuminanceCurve(rgbToHsl(result.rgb)))), result.a);}mediump float hsLuminance = dot(result.rgb, hsLuminanceWeighting);mediump float shadow = clamp((pow(hsLuminance, 1.0 / shadows) + (-0.76) * pow(hsLuminance, 2.0 / shadows)) - hsLuminance, 0.0, 1.0);mediump float highlight = clamp((1.0 - (pow(1.0 - hsLuminance, 1.0 / (2.0 - highlights)) + (-0.8) * pow(1.0 - hsLuminance, 2.0 / (2.0 - highlights)))) - hsLuminance, -1.0, 0.0);lowp vec3 hsresult = vec3(0.0, 0.0, 0.0) + ((hsLuminance + shadow + highlight) - 0.0) * ((result.rgb - vec3(0.0, 0.0, 0.0)) / (hsLuminance - 0.0));mediump float contrastedLuminance = ((hsLuminance - 0.5) * 1.5) + 0.5;mediump float whiteInterp = contrastedLuminance * contrastedLuminance * contrastedLuminance;mediump float whiteTarget = clamp(highlights, 1.0, 2.0) - 1.0;hsresult = mix(hsresult, vec3(1.0), whiteInterp * whiteTarget);mediump float invContrastedLuminance = 1.0 - contrastedLuminance;mediump float blackInterp = invContrastedLuminance * invContrastedLuminance * invContrastedLuminance;mediump float blackTarget = 1.0 - clamp(shadows, 0.0, 1.0);hsresult = mix(hsresult, vec3(0.0), blackInterp * blackTarget);result = vec4(hsresult.rgb, result.a);result = vec4(clamp(((result.rgb - vec3(0.5)) * contrast + vec3(0.5)), 0.0, 1.0), result.a);if (abs(fadeAmount) > toolEpsilon) {result.rgb = fadeAdjust(result.rgb, fadeAmount);}lowp float satLuminance = dot(result.rgb, satLuminanceWeighting);lowp vec3 greyScaleColor = vec3(satLuminance);result = vec4(clamp(mix(greyScaleColor, result.rgb, saturation), 0.0, 1.0), result.a);if (abs(shadowsTintIntensity) > toolEpsilon) {result.rgb = tintShadows(result.rgb, shadowsTintColor, shadowsTintIntensity * 2.0);}if (abs(highlightsTintIntensity) > toolEpsilon) {result.rgb = tintHighlights(result.rgb, highlightsTintColor, highlightsTintIntensity * 2.0);}if (abs(exposure) > toolEpsilon) {mediump float mag = exposure * 1.045;mediump float exppower = 1.0 + abs(mag);if (mag < 0.0) {exppower = 1.0 / exppower;}result.r = 1.0 - pow((1.0 - result.r), exppower);result.g = 1.0 - pow((1.0 - result.g), exppower);result.b = 1.0 - pow((1.0 - result.b), exppower);}if (abs(warmth) > toolEpsilon) {highp vec3 yuvVec;if (warmth > 0.0 ) {yuvVec = vec3(0.1765, -0.1255, 0.0902);} else {yuvVec = -vec3(0.0588, 0.1569, -0.1255);}highp vec3 yuvColor = rgbToYuv(result.rgb);highp float luma = yuvColor.r;highp float curveScale = sin(luma * 3.14159);yuvColor += 0.375 * warmth * curveScale * yuvVec;result.rgb = yuvToRgb(yuvColor);}if (abs(grain) > toolEpsilon) {highp vec3 rotOffset = vec3(1.425, 3.892, 5.835);highp vec2 rotCoordsR = coordRot(vTextureCoord, rotOffset.x);highp vec3 noise = vec3(pnoise3D(vec3(rotCoordsR * vec2(width / grainsize, height / grainsize),0.0)));lowp vec3 lumcoeff = vec3(0.299,0.587,0.114);lowp float luminance = dot(result.rgb, lumcoeff);lowp float lum = smoothstep(0.2, 0.0, luminance);lum += luminance;noise = mix(noise,vec3(0.0),pow(lum,4.0));result.rgb = result.rgb + noise * grain;}if (abs(vignette) > toolEpsilon) {const lowp float midpoint = 0.7;const lowp float fuzziness = 0.62;lowp float radDist = length(vTextureCoord - 0.5) / sqrt(0.5);lowp float mag = easeInOutSigmoid(radDist * midpoint, fuzziness) * vignette * 0.645;result.rgb = mix(pow(result.rgb, vec3(1.0 / (1.0 - mag))), vec3(0.0), mag * mag);}gl_FragColor = result;}");
        if (r6 == 0) goto L59;
        if (r7 == 0) goto L59;
        int r9 = GLES20.glCreateProgram();
        this.toolsShaderProgram = r9;
        GLES20.glAttachShader(r9, r6);
        GLES20.glAttachShader(this.toolsShaderProgram, r7);
        GLES20.glBindAttribLocation(this.toolsShaderProgram, 0, "position");
        GLES20.glBindAttribLocation(this.toolsShaderProgram, 1, "inputTexCoord");
        GLES20.glLinkProgram(this.toolsShaderProgram);
        GLES20.glGetProgramiv(this.toolsShaderProgram, 35714, r1, 0);
        int r62 = r1[0];
        int r11 = this.toolsShaderProgram;
        if (r62 != 0) goto L8;
        GLES20.glDeleteProgram(r11);
        this.toolsShaderProgram = 0;
    L9:
        int r63 = loadShader(35633, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 vTextureCoord;uniform highp float inputWidth;uniform highp float inputHeight;varying vec2 leftTexCoord;varying vec2 rightTexCoord;varying vec2 topTexCoord;varying vec2 bottomTexCoord;void main() {gl_Position = position;vTextureCoord = inputTexCoord;highp vec2 widthStep = vec2(1.0 / inputWidth, 0.0);highp vec2 heightStep = vec2(0.0, 1.0 / inputHeight);leftTexCoord = inputTexCoord - widthStep;rightTexCoord = inputTexCoord + widthStep;topTexCoord = inputTexCoord + heightStep;bottomTexCoord = inputTexCoord - heightStep;}");
        int r112 = loadShader(35632, "precision highp float;varying vec2 vTextureCoord;varying vec2 leftTexCoord;varying vec2 rightTexCoord;varying vec2 topTexCoord;varying vec2 bottomTexCoord;uniform sampler2D sTexture;uniform float sharpen;void main() {vec4 result = texture2D(sTexture, vTextureCoord);vec3 leftTextureColor = texture2D(sTexture, leftTexCoord).rgb;vec3 rightTextureColor = texture2D(sTexture, rightTexCoord).rgb;vec3 topTextureColor = texture2D(sTexture, topTexCoord).rgb;vec3 bottomTextureColor = texture2D(sTexture, bottomTexCoord).rgb;result.rgb = result.rgb * (1.0 + 4.0 * sharpen) - (leftTextureColor + rightTextureColor + topTextureColor + bottomTextureColor) * sharpen;gl_FragColor = result;}");
        if (r63 == 0) goto L59;
        if (r112 == 0) goto L59;
        int r13 = GLES20.glCreateProgram();
        this.sharpenShaderProgram = r13;
        GLES20.glAttachShader(r13, r63);
        GLES20.glAttachShader(this.sharpenShaderProgram, r112);
        GLES20.glBindAttribLocation(this.sharpenShaderProgram, 0, "position");
        GLES20.glBindAttribLocation(this.sharpenShaderProgram, 1, "inputTexCoord");
        GLES20.glLinkProgram(this.sharpenShaderProgram);
        GLES20.glGetProgramiv(this.sharpenShaderProgram, 35714, r1, 0);
        int r64 = r1[0];
        int r113 = this.sharpenShaderProgram;
        if (r64 != 0) goto L15;
        GLES20.glDeleteProgram(r113);
        this.sharpenShaderProgram = 0;
    L16:
        BlurProgram r65 = new BlurProgram(8.0f, 3.0f, false);
        this.blurProgram = r65;
        if (r65.create() == true) goto L20;
        return false;
    L20:
        if (setupExternalShaders() == true) goto L22;
        return false;
    L22:
        int r66 = loadShader(35633, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 vTextureCoord;void main() {gl_Position = position;vTextureCoord = inputTexCoord;}");
        int r114 = loadShader(35632, "varying highp vec2 vTextureCoord;uniform sampler2D sTexture;uniform sampler2D inputImageTexture2;uniform lowp float excludeSize;uniform lowp vec2 excludePoint;uniform lowp float excludeBlurSize;uniform highp float angle;uniform highp float aspectRatio;void main() {lowp vec4 sharpImageColor = texture2D(sTexture, vTextureCoord);lowp vec4 blurredImageColor = texture2D(inputImageTexture2, vTextureCoord);highp vec2 texCoordToUse = vec2(vTextureCoord.x, (vTextureCoord.y * aspectRatio + 0.5 - 0.5 * aspectRatio));highp float distanceFromCenter = abs((texCoordToUse.x - excludePoint.x) * aspectRatio * cos(angle) + (texCoordToUse.y - excludePoint.y) * sin(angle));gl_FragColor = mix(sharpImageColor, blurredImageColor, smoothstep(excludeSize - excludeBlurSize, excludeSize, distanceFromCenter));}");
        if (r66 == 0) goto L59;
        if (r114 == 0) goto L59;
        int r132 = GLES20.glCreateProgram();
        this.linearBlurShaderProgram = r132;
        GLES20.glAttachShader(r132, r66);
        GLES20.glAttachShader(this.linearBlurShaderProgram, r114);
        GLES20.glBindAttribLocation(this.linearBlurShaderProgram, 0, "position");
        GLES20.glBindAttribLocation(this.linearBlurShaderProgram, 1, "inputTexCoord");
        GLES20.glLinkProgram(this.linearBlurShaderProgram);
        GLES20.glGetProgramiv(this.linearBlurShaderProgram, 35714, r1, 0);
        int r67 = r1[0];
        int r115 = this.linearBlurShaderProgram;
        if (r67 != 0) goto L28;
        GLES20.glDeleteProgram(r115);
        this.linearBlurShaderProgram = 0;
    L29:
        int r68 = loadShader(35633, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 vTextureCoord;void main() {gl_Position = position;vTextureCoord = inputTexCoord;}");
        int r116 = loadShader(35632, "varying highp vec2 vTextureCoord;uniform sampler2D sTexture;uniform sampler2D inputImageTexture2;uniform lowp float excludeSize;uniform lowp vec2 excludePoint;uniform lowp float excludeBlurSize;uniform highp float aspectRatio;void main() {lowp vec4 sharpImageColor = texture2D(sTexture, vTextureCoord);lowp vec4 blurredImageColor = texture2D(inputImageTexture2, vTextureCoord);highp vec2 texCoordToUse = vec2(vTextureCoord.x, (vTextureCoord.y * aspectRatio + 0.5 - 0.5 * aspectRatio));highp float distanceFromCenter = distance(excludePoint, texCoordToUse);gl_FragColor = mix(sharpImageColor, blurredImageColor, smoothstep(excludeSize - excludeBlurSize, excludeSize, distanceFromCenter));}");
        if (r68 == 0) goto L59;
        if (r116 == 0) goto L59;
        int r8 = GLES20.glCreateProgram();
        this.radialBlurShaderProgram = r8;
        GLES20.glAttachShader(r8, r68);
        GLES20.glAttachShader(this.radialBlurShaderProgram, r116);
        GLES20.glBindAttribLocation(this.radialBlurShaderProgram, 0, "position");
        GLES20.glBindAttribLocation(this.radialBlurShaderProgram, 1, "inputTexCoord");
        GLES20.glLinkProgram(this.radialBlurShaderProgram);
        GLES20.glGetProgramiv(this.radialBlurShaderProgram, 35714, r1, 0);
        int r69 = r1[0];
        int r82 = this.radialBlurShaderProgram;
        if (r69 != 0) goto L35;
        GLES20.glDeleteProgram(r82);
        this.radialBlurShaderProgram = 0;
    L36:
        int r610 = loadShader(35633, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 vTextureCoord;void main() {gl_Position = position;vTextureCoord = inputTexCoord;}");
        int r83 = loadShader(35632, "precision highp float;varying vec2 vTextureCoord;uniform sampler2D sTexture;uniform sampler2D inputImageTexture2;uniform float intensity;float enhance(float value) {const vec2 offset = vec2(0.001953125, 0.03125);value = value + offset.x;vec2 coord = (clamp(vTextureCoord, 0.125, 1.0 - 0.125001) - 0.125) * 4.0;vec2 frac = fract(coord);coord = floor(coord);float p00 = float(coord.y * 4.0 + coord.x) * 0.0625 + offset.y;float p01 = float(coord.y * 4.0 + coord.x + 1.0) * 0.0625 + offset.y;float p10 = float((coord.y + 1.0) * 4.0 + coord.x) * 0.0625 + offset.y;float p11 = float((coord.y + 1.0) * 4.0 + coord.x + 1.0) * 0.0625 + offset.y;vec3 c00 = texture2D(inputImageTexture2, vec2(value, p00)).rgb;vec3 c01 = texture2D(inputImageTexture2, vec2(value, p01)).rgb;vec3 c10 = texture2D(inputImageTexture2, vec2(value, p10)).rgb;vec3 c11 = texture2D(inputImageTexture2, vec2(value, p11)).rgb;float c1 = ((c00.r - c00.g) / (c00.b - c00.g));float c2 = ((c01.r - c01.g) / (c01.b - c01.g));float c3 = ((c10.r - c10.g) / (c10.b - c10.g));float c4 = ((c11.r - c11.g) / (c11.b - c11.g));float c1_2 = mix(c1, c2, frac.x);float c3_4 = mix(c3, c4, frac.x);return mix(c1_2, c3_4, frac.y);}vec3 hsv_to_rgb(vec3 c) {vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);}void main() {vec4 texel = texture2D(sTexture, vTextureCoord);vec4 hsv = texel;hsv.y = min(1.0, hsv.y * 1.2);hsv.z = min(1.0, enhance(hsv.z) * 1.1);gl_FragColor = vec4(hsv_to_rgb(mix(texel.xyz, hsv.xyz, intensity)), texel.w);}");
        if (r610 == 0) goto L59;
        if (r83 == 0) goto L59;
        int r10 = GLES20.glCreateProgram();
        this.enhanceShaderProgram = r10;
        GLES20.glAttachShader(r10, r610);
        GLES20.glAttachShader(this.enhanceShaderProgram, r83);
        GLES20.glBindAttribLocation(this.enhanceShaderProgram, 0, "position");
        GLES20.glBindAttribLocation(this.enhanceShaderProgram, 1, "inputTexCoord");
        GLES20.glLinkProgram(this.enhanceShaderProgram);
        GLES20.glGetProgramiv(this.enhanceShaderProgram, 35714, r1, 0);
        int r611 = r1[0];
        int r84 = this.enhanceShaderProgram;
        if (r611 != 0) goto L42;
        GLES20.glDeleteProgram(r84);
        this.enhanceShaderProgram = 0;
    L43:
        int r612 = loadShader(35632, "precision lowp float;varying highp vec2 vTextureCoord;varying highp vec2 texCoord2;uniform sampler2D sTexture;uniform sampler2D inputImageTexture2;void main() {vec4 image = texture2D(sTexture, vTextureCoord);vec4 blurredImage = texture2D(inputImageTexture2, texCoord2);gl_FragColor = vec4((image.rgb - blurredImage.rgb + vec3(0.5,0.5,0.5)), image.a);}");
        int r85 = loadShader(35633, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 vTextureCoord;varying vec2 texCoord2;void main() {gl_Position = position;vTextureCoord = inputTexCoord;texCoord2 = inputTexCoord;}");
        if (r85 == 0) goto L59;
        if (r612 == 0) goto L59;
        int r102 = GLES20.glCreateProgram();
        this.highPassProgram = r102;
        GLES20.glAttachShader(r102, r85);
        GLES20.glAttachShader(this.highPassProgram, r612);
        GLES20.glBindAttribLocation(this.highPassProgram, 0, "position");
        GLES20.glBindAttribLocation(this.highPassProgram, 1, "inputTexCoord");
        GLES20.glLinkProgram(this.highPassProgram);
        GLES20.glGetProgramiv(this.highPassProgram, 35714, r1, 0);
        int r613 = r1[0];
        int r86 = this.highPassProgram;
        if (r613 != 0) goto L49;
        GLES20.glDeleteProgram(r86);
        this.highPassProgram = 0;
    L50:
        int r2 = loadShader(35632, "precision lowp float;varying highp vec2 vTextureCoord;uniform sampler2D sTexture;void main() {vec4 color = texture2D(sTexture, vTextureCoord);float hardLightColor = color.b;for (int i = 0; i < 3; ++i){if (hardLightColor < 0.5) {hardLightColor = hardLightColor * hardLightColor * 2.0;} else {hardLightColor = 1.0 - (1.0 - hardLightColor) * (1.0 - hardLightColor) * 2.0;}}float k = 255.0 / (164.0 - 75.0);hardLightColor = (hardLightColor - 75.0 / 255.0) * k;gl_FragColor = vec4(vec3(hardLightColor), color.a);}");
        int r4 = loadShader(35633, "attribute vec4 position;attribute vec2 inputTexCoord;varying vec2 vTextureCoord;void main() {gl_Position = position;vTextureCoord = inputTexCoord;}");
        if (r4 == 0) goto L59;
        if (r2 == 0) goto L59;
        int r5 = GLES20.glCreateProgram();
        this.boostProgram = r5;
        GLES20.glAttachShader(r5, r4);
        GLES20.glAttachShader(this.boostProgram, r2);
        GLES20.glBindAttribLocation(this.boostProgram, 0, "position");
        GLES20.glBindAttribLocation(this.boostProgram, 1, "inputTexCoord");
        GLES20.glLinkProgram(this.boostProgram);
        GLES20.glGetProgramiv(this.boostProgram, 35714, r1, 0);
        int r12 = r1[0];
        int r22 = this.boostProgram;
        if (r12 != 0) goto L56;
        GLES20.glDeleteProgram(r22);
        this.boostProgram = 0;
    L57:
        this.toneCurve = new ToneCurve();
        return true;
    L56:
        this.boostPositionHandle = GLES20.glGetAttribLocation(r22, "position");
        this.boostInputTexCoordHandle = GLES20.glGetAttribLocation(this.boostProgram, "inputTexCoord");
        this.boostSourceImageHandle = GLES20.glGetUniformLocation(this.boostProgram, "sTexture");
        goto L57
    L49:
        this.highPassPositionHandle = GLES20.glGetAttribLocation(r86, "position");
        this.highPassInputTexCoordHandle = GLES20.glGetAttribLocation(this.highPassProgram, "inputTexCoord");
        this.highPassSourceImageHandle = GLES20.glGetUniformLocation(this.highPassProgram, "sTexture");
        this.highPassInputImageHandle = GLES20.glGetUniformLocation(this.highPassProgram, "inputImageTexture2");
        goto L50
    L42:
        this.enhancePositionHandle = GLES20.glGetAttribLocation(r84, "position");
        this.enhanceInputTexCoordHandle = GLES20.glGetAttribLocation(this.enhanceShaderProgram, "inputTexCoord");
        this.enhanceSourceImageHandle = GLES20.glGetUniformLocation(this.enhanceShaderProgram, "sTexture");
        this.enhanceIntensityHandle = GLES20.glGetUniformLocation(this.enhanceShaderProgram, "intensity");
        this.enhanceInputImageTexture2Handle = GLES20.glGetUniformLocation(this.enhanceShaderProgram, "inputImageTexture2");
        goto L43
    L35:
        this.radialBlurPositionHandle = GLES20.glGetAttribLocation(r82, "position");
        this.radialBlurInputTexCoordHandle = GLES20.glGetAttribLocation(this.radialBlurShaderProgram, "inputTexCoord");
        this.radialBlurSourceImageHandle = GLES20.glGetUniformLocation(this.radialBlurShaderProgram, "sTexture");
        this.radialBlurSourceImage2Handle = GLES20.glGetUniformLocation(this.radialBlurShaderProgram, "inputImageTexture2");
        this.radialBlurExcludeSizeHandle = GLES20.glGetUniformLocation(this.radialBlurShaderProgram, "excludeSize");
        this.radialBlurExcludePointHandle = GLES20.glGetUniformLocation(this.radialBlurShaderProgram, "excludePoint");
        this.radialBlurExcludeBlurSizeHandle = GLES20.glGetUniformLocation(this.radialBlurShaderProgram, "excludeBlurSize");
        this.radialBlurAspectRatioHandle = GLES20.glGetUniformLocation(this.radialBlurShaderProgram, "aspectRatio");
        goto L36
    L28:
        this.linearBlurPositionHandle = GLES20.glGetAttribLocation(r115, "position");
        this.linearBlurInputTexCoordHandle = GLES20.glGetAttribLocation(this.linearBlurShaderProgram, "inputTexCoord");
        this.linearBlurSourceImageHandle = GLES20.glGetUniformLocation(this.linearBlurShaderProgram, "sTexture");
        this.linearBlurSourceImage2Handle = GLES20.glGetUniformLocation(this.linearBlurShaderProgram, "inputImageTexture2");
        this.linearBlurExcludeSizeHandle = GLES20.glGetUniformLocation(this.linearBlurShaderProgram, "excludeSize");
        this.linearBlurExcludePointHandle = GLES20.glGetUniformLocation(this.linearBlurShaderProgram, "excludePoint");
        this.linearBlurExcludeBlurSizeHandle = GLES20.glGetUniformLocation(this.linearBlurShaderProgram, "excludeBlurSize");
        this.linearBlurAngleHandle = GLES20.glGetUniformLocation(this.linearBlurShaderProgram, "angle");
        this.linearBlurAspectRatioHandle = GLES20.glGetUniformLocation(this.linearBlurShaderProgram, "aspectRatio");
        goto L29
    L15:
        this.sharpenPositionHandle = GLES20.glGetAttribLocation(r113, "position");
        this.sharpenInputTexCoordHandle = GLES20.glGetAttribLocation(this.sharpenShaderProgram, "inputTexCoord");
        this.sharpenSourceImageHandle = GLES20.glGetUniformLocation(this.sharpenShaderProgram, "sTexture");
        this.sharpenWidthHandle = GLES20.glGetUniformLocation(this.sharpenShaderProgram, "inputWidth");
        this.sharpenHeightHandle = GLES20.glGetUniformLocation(this.sharpenShaderProgram, "inputHeight");
        this.sharpenHandle = GLES20.glGetUniformLocation(this.sharpenShaderProgram, "sharpen");
        goto L16
    L8:
        this.positionHandle = GLES20.glGetAttribLocation(r11, "position");
        this.inputTexCoordHandle = GLES20.glGetAttribLocation(this.toolsShaderProgram, "inputTexCoord");
        this.sourceImageHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "sTexture");
        this.shadowsHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "shadows");
        this.highlightsHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "highlights");
        this.exposureHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "exposure");
        this.contrastHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "contrast");
        this.saturationHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "saturation");
        this.warmthHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "warmth");
        this.vignetteHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "vignette");
        this.grainHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "grain");
        this.widthHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "width");
        this.heightHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "height");
        this.curvesImageHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "curvesImage");
        this.skipToneHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "skipTone");
        this.fadeAmountHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "fadeAmount");
        this.shadowsTintIntensityHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "shadowsTintIntensity");
        this.highlightsTintIntensityHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "highlightsTintIntensity");
        this.shadowsTintColorHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "shadowsTintColor");
        this.highlightsTintColorHandle = GLES20.glGetUniformLocation(this.toolsShaderProgram, "highlightsTintColor");
    L59:
        return false;
    }

    public boolean drawBlurPass() {
        FilterShadersDelegate r1 = this.delegate;
        if (r1 == null) goto L5;
        int r12 = r1.getBlurType();
    L7:
        if (this.isVideo == true) goto L27;
        FilterShadersDelegate r3 = this.delegate;
        if (r3 == null) goto L27;
        if (r3.shouldShowOriginal() == true) goto L27;
        if (r12 == 0) goto L27;
        if (this.needUpdateBlurTexture == true) goto L17;
    L20:
        GLES20.glBindFramebuffer(36160, this.renderFrameBuffer[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.renderTexture[0], 0);
        if (r12 != 1) goto L23;
        GLES20.glUseProgram(this.radialBlurShaderProgram);
        GLES20.glUniform1i(this.radialBlurSourceImageHandle, 0);
        GLES20.glUniform1i(this.radialBlurSourceImage2Handle, 1);
        GLES20.glUniform1f(this.radialBlurExcludeSizeHandle, this.delegate.getBlurExcludeSize());
        GLES20.glUniform1f(this.radialBlurExcludeBlurSizeHandle, this.delegate.getBlurExcludeBlurSize());
        PointF r13 = this.delegate.getBlurExcludePoint();
        GLES20.glUniform2f(this.radialBlurExcludePointHandle, r13.x, r13.y);
        GLES20.glUniform1f(this.radialBlurAspectRatioHandle, this.renderBufferHeight / this.renderBufferWidth);
        GLES20.glEnableVertexAttribArray(this.radialBlurInputTexCoordHandle);
        GLES20.glVertexAttribPointer(this.radialBlurInputTexCoordHandle, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(this.radialBlurPositionHandle);
        GLES20.glVertexAttribPointer(this.radialBlurPositionHandle, 2, 5126, false, 8, this.vertexInvertBuffer);
    L25:
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.renderTexture[1]);
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, this.renderTexture[2]);
        GLES20.glDrawArrays(5, 0, 4);
        return true;
    L23:
        if (r12 != 2) goto L25;
        GLES20.glUseProgram(this.linearBlurShaderProgram);
        GLES20.glUniform1i(this.linearBlurSourceImageHandle, 0);
        GLES20.glUniform1i(this.linearBlurSourceImage2Handle, 1);
        GLES20.glUniform1f(this.linearBlurExcludeSizeHandle, this.delegate.getBlurExcludeSize());
        GLES20.glUniform1f(this.linearBlurExcludeBlurSizeHandle, this.delegate.getBlurExcludeBlurSize());
        GLES20.glUniform1f(this.linearBlurAngleHandle, this.delegate.getBlurAngle());
        PointF r14 = this.delegate.getBlurExcludePoint();
        GLES20.glUniform2f(this.linearBlurExcludePointHandle, r14.x, r14.y);
        GLES20.glUniform1f(this.linearBlurAspectRatioHandle, this.renderBufferHeight / this.renderBufferWidth);
        GLES20.glEnableVertexAttribArray(this.linearBlurInputTexCoordHandle);
        GLES20.glVertexAttribPointer(this.linearBlurInputTexCoordHandle, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(this.linearBlurPositionHandle);
        GLES20.glVertexAttribPointer(this.linearBlurPositionHandle, 2, 5126, false, 8, this.vertexInvertBuffer);
        goto L25
    L17:
        if (this.blurTextureCreated == true) goto L19;
        GLES20.glBindTexture(3553, this.renderTexture[2]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexImage2D(3553, 0, 6408, this.renderBufferWidth, this.renderBufferHeight, 0, 6408, 5121, null);
        this.blurTextureCreated = true;
    L19:
        GLES20.glUseProgram(this.blurProgram.blurShaderProgram);
        GLES20.glUniform1i(this.blurProgram.blurSourceImageHandle, 0);
        GLES20.glEnableVertexAttribArray(this.blurProgram.blurInputTexCoordHandle);
        GLES20.glVertexAttribPointer(this.blurProgram.blurInputTexCoordHandle, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(this.blurProgram.blurPositionHandle);
        GLES20.glVertexAttribPointer(this.blurProgram.blurPositionHandle, 2, 5126, false, 8, this.vertexInvertBuffer);
        GLES20.glBindFramebuffer(36160, this.renderFrameBuffer[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.renderTexture[0], 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.renderTexture[1]);
        GLES20.glUniform1f(this.blurProgram.blurWidthHandle, 0.0f);
        GLES20.glUniform1f(this.blurProgram.blurHeightHandle, 1.0f / this.renderBufferHeight);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glBindFramebuffer(36160, this.renderFrameBuffer[2]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.renderTexture[2], 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.renderTexture[0]);
        GLES20.glUniform1f(this.blurProgram.blurWidthHandle, 1.0f / this.renderBufferWidth);
        GLES20.glUniform1f(this.blurProgram.blurHeightHandle, 0.0f);
        GLES20.glDrawArrays(5, 0, 4);
        this.needUpdateBlurTexture = false;
    L27:
        return false;
    L5:
        r12 = 0;
        goto L7
    }

    public void drawCustomParamsPass() {
        GLES20.glBindFramebuffer(36160, this.renderFrameBuffer[!this.isVideo ? 1 : 0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.renderTexture[!this.isVideo ? 1 : 0], 0);
        GLES20.glUseProgram(this.toolsShaderProgram);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.renderTexture[this.isVideo ? 1 : 0]);
        GLES20.glUniform1i(this.sourceImageHandle, 0);
        FilterShadersDelegate r0 = this.delegate;
        float r1 = 1.0f;
        if (r0 != null) goto L5;
    L13:
        GLES20.glUniform1f(this.shadowsHandle, 1.0f);
        GLES20.glUniform1f(this.highlightsHandle, 1.0f);
        GLES20.glUniform1f(this.exposureHandle, 0.0f);
        GLES20.glUniform1f(this.contrastHandle, 1.0f);
        GLES20.glUniform1f(this.saturationHandle, 1.0f);
        GLES20.glUniform1f(this.warmthHandle, 0.0f);
        GLES20.glUniform1f(this.vignetteHandle, 0.0f);
        GLES20.glUniform1f(this.grainHandle, 0.0f);
        GLES20.glUniform1f(this.fadeAmountHandle, 0.0f);
        GLES20.glUniform3f(this.highlightsTintColorHandle, 0.0f, 0.0f, 0.0f);
        GLES20.glUniform1f(this.highlightsTintIntensityHandle, 0.0f);
        GLES20.glUniform3f(this.shadowsTintColorHandle, 0.0f, 0.0f, 0.0f);
        GLES20.glUniform1f(this.shadowsTintIntensityHandle, 0.0f);
        GLES20.glUniform1f(this.skipToneHandle, 1.0f);
    L14:
        GLES20.glUniform1f(this.widthHandle, this.renderBufferWidth);
        GLES20.glUniform1f(this.heightHandle, this.renderBufferHeight);
        GLES20.glEnableVertexAttribArray(this.inputTexCoordHandle);
        GLES20.glVertexAttribPointer(this.inputTexCoordHandle, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(this.positionHandle);
        GLES20.glVertexAttribPointer(this.positionHandle, 2, 5126, false, 8, this.vertexInvertBuffer);
        GLES20.glDrawArrays(5, 0, 4);
        return;
    L5:
        if (r0.shouldShowOriginal() == true) goto L13;
        GLES20.glUniform1f(this.shadowsHandle, this.delegate.getShadowsValue());
        GLES20.glUniform1f(this.highlightsHandle, this.delegate.getHighlightsValue());
        GLES20.glUniform1f(this.exposureHandle, this.delegate.getExposureValue());
        GLES20.glUniform1f(this.contrastHandle, this.delegate.getContrastValue());
        GLES20.glUniform1f(this.saturationHandle, this.delegate.getSaturationValue());
        GLES20.glUniform1f(this.warmthHandle, this.delegate.getWarmthValue());
        GLES20.glUniform1f(this.vignetteHandle, this.delegate.getVignetteValue());
        GLES20.glUniform1f(this.grainHandle, this.delegate.getGrainValue());
        GLES20.glUniform1f(this.fadeAmountHandle, this.delegate.getFadeValue());
        int r02 = this.delegate.getTintHighlightsColor();
        int r6 = this.delegate.getTintShadowsColor();
        GLES20.glUniform3f(this.highlightsTintColorHandle, ((r02 >> 16) & 255) / 255.0f, ((r02 >> 8) & 255) / 255.0f, (r02 & 255) / 255.0f);
        GLES20.glUniform1f(this.highlightsTintIntensityHandle, this.delegate.getTintHighlightsIntensityValue());
        GLES20.glUniform3f(this.shadowsTintColorHandle, ((r6 >> 16) & 255) / 255.0f, ((r6 >> 8) & 255) / 255.0f, (r6 & 255) / 255.0f);
        GLES20.glUniform1f(this.shadowsTintIntensityHandle, this.delegate.getTintShadowsIntensityValue());
        boolean r03 = this.delegate.shouldDrawCurvesPass();
        int r62 = this.skipToneHandle;
        if (r03 == false) goto L10;
        r1 = 0.0f;
    L10:
        GLES20.glUniform1f(r62, r1);
        if (r03 == false) goto L14;
        ByteBuffer r14 = this.delegate.fillAndGetCurveBuffer();
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, this.curveTextures[0]);
        GLES20.glTexImage2D(3553, 0, 6408, 200, 1, 0, 6408, 5121, r14);
        GLES20.glUniform1i(this.curvesImageHandle, 1);
        goto L14
    }

    public void drawEnhancePass() {
        boolean r1 = this.isVideo;
        if (r1 == false) goto L5;
        boolean r3 = true;
    L7:
        if (r3 == false) goto L27;
        if (r1 == true) goto L10;
    L15:
        GLES20.glUseProgram(this.rgbToHsvShaderProgram[0]);
        GLES20.glActiveTexture(33984);
        if (this.skinPassDrawn == false) goto L18;
        int r12 = this.renderTexture[1];
    L19:
        GLES20.glBindTexture(3553, r12);
        char r13 = 0;
    L20:
        GLES20.glUniform1i(this.rgbToHsvSourceImageHandle[r13], 0);
        GLES20.glEnableVertexAttribArray(this.rgbToHsvInputTexCoordHandle[r13]);
        GLES20.glVertexAttribPointer(this.rgbToHsvInputTexCoordHandle[r13], 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(this.rgbToHsvPositionHandle[r13]);
        int r11 = this.rgbToHsvPositionHandle[r13];
        if (this.isVideo == false) goto L24;
        FloatBuffer r14 = this.vertexInvertBuffer;
    L25:
        GLES20.glVertexAttribPointer(r11, 2, 5126, false, 8, r14);
        GLES20.glBindFramebuffer(36160, this.enhanceFrameBuffer[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.enhanceTextures[0], 0);
        GLES20.glDrawArrays(5, 0, 4);
        goto L27
    L24:
        r14 = this.vertexBuffer;
        goto L25
    L18:
        r12 = this.bitmapTextre[0];
        goto L19
    L10:
        if (this.skinPassDrawn == true) goto L15;
        GLES20.glUseProgram(this.rgbToHsvShaderProgram[1]);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, this.videoTexture);
        GLES20.glUniformMatrix4fv(this.rgbToHsvMatrixHandle, 1, false, this.videoMatrix, 0);
        int r15 = this.rgbToHsvTexSizeHandle;
        if (r15 == (-1)) goto L14;
        GLES20.glUniform2f(r15, this.renderBufferWidth, this.renderBufferHeight);
    L14:
        r13 = 1;
    L27:
        if (this.hsvGenerated == true) goto L43;
        int r16 = (this.renderBufferWidth * this.renderBufferHeight) * 4;
        ByteBuffer r32 = this.hsvBuffer;
        if (r32 != null) goto L31;
    L32:
        this.hsvBuffer = ByteBuffer.allocateDirect(r16);
    L34:
        if (this.cdtBuffer != null) goto L37;
        this.cdtBuffer = ByteBuffer.allocateDirect(16384);
    L37:
        if (this.calcBuffer != null) goto L39;
        this.calcBuffer = ByteBuffer.allocateDirect(32896);
    L39:
        GLES20.glReadPixels(0, 0, this.renderBufferWidth, this.renderBufferHeight, 6408, 5121, this.hsvBuffer);
        Utilities.calcCDT(this.hsvBuffer, this.renderBufferWidth, this.renderBufferHeight, this.cdtBuffer, this.calcBuffer);
        GLES20.glBindTexture(3553, this.enhanceTextures[1]);
        GLES20.glTexImage2D(3553, 0, 6408, 256, 16, 0, 6408, 5121, this.cdtBuffer);
        if (this.isVideo == true) goto L42;
        this.hsvBuffer = null;
        this.cdtBuffer = null;
        this.calcBuffer = null;
    L42:
        this.hsvGenerated = true;
        goto L43
    L31:
        if (r16 <= r32.capacity()) goto L34;
    L43:
        GLES20.glBindFramebuffer(36160, this.renderFrameBuffer[1]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.renderTexture[1], 0);
        GLES20.glUseProgram(this.enhanceShaderProgram);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.enhanceTextures[0]);
        GLES20.glUniform1i(this.enhanceSourceImageHandle, 0);
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, this.enhanceTextures[1]);
        GLES20.glUniform1i(this.enhanceInputImageTexture2Handle, 1);
        FilterShadersDelegate r17 = this.delegate;
        if (r17 != null) goto L46;
    L49:
        GLES20.glUniform1f(this.enhanceIntensityHandle, 0.0f);
    L50:
        GLES20.glEnableVertexAttribArray(this.enhanceInputTexCoordHandle);
        GLES20.glVertexAttribPointer(this.enhanceInputTexCoordHandle, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(this.enhancePositionHandle);
        GLES20.glVertexAttribPointer(this.enhancePositionHandle, 2, 5126, false, 8, this.vertexBuffer);
        GLES20.glDrawArrays(5, 0, 4);
        return;
    L46:
        if (r17.shouldShowOriginal() == true) goto L49;
        GLES20.glUniform1f(this.enhanceIntensityHandle, this.delegate.getEnhanceValue());
        goto L50
    L5:
        r3 = !this.hsvGenerated;
        goto L7
    }

    public boolean drawOriginal() {
        FilterShadersDelegate r0 = this.delegate;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.shouldShowOriginal() == true) goto L11;
        return false;
    L11:
        return true;
    }

    public void drawSharpenPass() {
        if (this.isVideo == false) goto L5;
        return;
    L5:
        GLES20.glBindFramebuffer(36160, this.renderFrameBuffer[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.renderTexture[0], 0);
        GLES20.glUseProgram(this.sharpenShaderProgram);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.renderTexture[1]);
        GLES20.glUniform1i(this.sharpenSourceImageHandle, 0);
        FilterShadersDelegate r0 = this.delegate;
        if (r0 != null) goto L8;
    L11:
        GLES20.glUniform1f(this.sharpenHandle, 0.0f);
    L12:
        GLES20.glUniform1f(this.sharpenWidthHandle, this.renderBufferWidth);
        GLES20.glUniform1f(this.sharpenHeightHandle, this.renderBufferHeight);
        GLES20.glEnableVertexAttribArray(this.sharpenInputTexCoordHandle);
        GLES20.glVertexAttribPointer(this.sharpenInputTexCoordHandle, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(this.sharpenPositionHandle);
        GLES20.glVertexAttribPointer(this.sharpenPositionHandle, 2, 5126, false, 8, this.vertexInvertBuffer);
        GLES20.glDrawArrays(5, 0, 4);
        return;
    L8:
        if (r0.shouldShowOriginal() == true) goto L11;
        GLES20.glUniform1f(this.sharpenHandle, this.delegate.getSharpenValue());
        goto L12
    }

    public boolean drawSkinSmoothPass() {
        FilterShadersDelegate r1 = this.delegate;
        if (r1 == null) goto L50;
        if (r1.shouldShowOriginal() == true) goto L50;
        if (this.delegate.getSoftenSkinValue() <= 0.0f) goto L50;
        int r12 = this.renderBufferWidth;
        if (r12 <= 0) goto L50;
        if (this.renderBufferHeight <= 0) goto L50;
        if (this.needUpdateSkinTexture == false) goto L16;
    L17:
        float r13 = r12 * 0.006f;
        if (this.skinBlurProgram != null) goto L20;
    L21:
        BlurProgram r3 = this.skinBlurProgram;
        if (r3 == null) goto L24;
        r3.destroy();
    L24:
        this.lastRadius = r13;
        BlurProgram r32 = new BlurProgram(r13, 2.0f, true);
        this.skinBlurProgram = r32;
        r32.create();
    L26:
        if (this.skinTextureCreated == true) goto L28;
        GLES20.glBindTexture(3553, this.renderTexture[3]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexImage2D(3553, 0, 6408, this.renderBufferWidth, this.renderBufferHeight, 0, 6408, 5121, null);
        this.skinTextureCreated = true;
    L28:
        GLES20.glUseProgram(this.greenAndBlueChannelOverlayProgram);
        GLES20.glUniform1i(this.greenAndBlueChannelOverlaySourceImageHandle, 0);
        GLES20.glEnableVertexAttribArray(this.greenAndBlueChannelOverlayInputTexCoordHandle);
        GLES20.glVertexAttribPointer(this.greenAndBlueChannelOverlayInputTexCoordHandle, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(this.greenAndBlueChannelOverlayPositionHandle);
        if (this.isVideo == false) goto L33;
        GLES20.glUniformMatrix4fv(this.greenAndBlueChannelOverlayMatrixHandle, 1, false, this.videoMatrix, 0);
        int r14 = this.greenAndBlueChannelOverlayTexSizeHandle;
        if (r14 == (-1)) goto L33;
        GLES20.glUniform2f(r14, this.renderBufferWidth, this.renderBufferHeight);
    L33:
        GLES20.glVertexAttribPointer(this.greenAndBlueChannelOverlayPositionHandle, 2, 5126, false, 8, this.vertexInvertBuffer);
        GLES20.glBindFramebuffer(36160, this.renderFrameBuffer[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.renderTexture[0], 0);
        GLES20.glActiveTexture(33984);
        if (this.isVideo == false) goto L36;
        GLES20.glBindTexture(36197, this.videoTexture);
    L37:
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glUseProgram(this.skinBlurProgram.blurShaderProgram);
        GLES20.glUniform1i(this.skinBlurProgram.blurSourceImageHandle, 0);
        GLES20.glEnableVertexAttribArray(this.skinBlurProgram.blurInputTexCoordHandle);
        GLES20.glVertexAttribPointer(this.skinBlurProgram.blurInputTexCoordHandle, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(this.skinBlurProgram.blurPositionHandle);
        GLES20.glVertexAttribPointer(this.skinBlurProgram.blurPositionHandle, 2, 5126, false, 8, this.vertexInvertBuffer);
        GLES20.glBindFramebuffer(36160, this.renderFrameBuffer[1]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.renderTexture[1], 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.renderTexture[0]);
        GLES20.glUniform1f(this.skinBlurProgram.blurWidthHandle, 0.0f);
        GLES20.glUniform1f(this.skinBlurProgram.blurHeightHandle, 1.0f / this.renderBufferHeight);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glBindFramebuffer(36160, this.renderFrameBuffer[3]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.renderTexture[3], 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.renderTexture[1]);
        GLES20.glUniform1f(this.skinBlurProgram.blurWidthHandle, 1.0f / this.renderBufferWidth);
        GLES20.glUniform1f(this.skinBlurProgram.blurHeightHandle, 0.0f);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glUseProgram(this.highPassProgram);
        GLES20.glUniform1i(this.highPassSourceImageHandle, 0);
        GLES20.glUniform1i(this.highPassInputImageHandle, 1);
        GLES20.glEnableVertexAttribArray(this.highPassInputTexCoordHandle);
        GLES20.glVertexAttribPointer(this.highPassInputTexCoordHandle, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(this.highPassPositionHandle);
        GLES20.glVertexAttribPointer(this.highPassPositionHandle, 2, 5126, false, 8, this.vertexInvertBuffer);
        GLES20.glBindFramebuffer(36160, this.renderFrameBuffer[1]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.renderTexture[1], 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.renderTexture[0]);
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, this.renderTexture[3]);
        GLES20.glDrawArrays(5, 0, 4);
        GLES20.glUseProgram(this.boostProgram);
        GLES20.glUniform1i(this.boostSourceImageHandle, 0);
        GLES20.glEnableVertexAttribArray(this.boostInputTexCoordHandle);
        GLES20.glVertexAttribPointer(this.boostInputTexCoordHandle, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(this.boostPositionHandle);
        GLES20.glVertexAttribPointer(this.boostPositionHandle, 2, 5126, false, 8, this.vertexInvertBuffer);
        GLES20.glBindFramebuffer(36160, this.renderFrameBuffer[3]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.renderTexture[3], 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.renderTexture[1]);
        GLES20.glDrawArrays(5, 0, 4);
        this.needUpdateSkinTexture = false;
    L38:
        this.skinPassDrawn = true;
        this.hsvGenerated = false;
        GLES20.glUseProgram(this.compositeProgram);
        GLES20.glUniform1i(this.compositeSourceImageHandle, 0);
        GLES20.glUniform1i(this.compositeInputImageHandle, 1);
        GLES20.glUniform1i(this.compositeCurveImageHandle, 2);
        GLES20.glUniform1f(this.compositeMixtureHandle, this.delegate.getSoftenSkinValue());
        GLES20.glEnableVertexAttribArray(this.compositeInputTexCoordHandle);
        GLES20.glVertexAttribPointer(this.compositeInputTexCoordHandle, 2, 5126, false, 8, this.textureBuffer);
        GLES20.glEnableVertexAttribArray(this.compositePositionHandle);
        GLES20.glVertexAttribPointer(this.compositePositionHandle, 2, 5126, false, 8, this.vertexInvertBuffer);
        if (this.isVideo == false) goto L43;
        GLES20.glUniformMatrix4fv(this.compositeMatrixHandle, 1, false, this.videoMatrix, 0);
        int r15 = this.compositeTexSizeHandle;
        if (r15 == (-1)) goto L43;
        GLES20.glUniform2f(r15, this.renderBufferWidth, this.renderBufferHeight);
    L43:
        GLES20.glBindFramebuffer(36160, this.renderFrameBuffer[1]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.renderTexture[1], 0);
        GLES20.glActiveTexture(33984);
        if (this.isVideo == false) goto L46;
        GLES20.glBindTexture(36197, this.videoTexture);
    L47:
        GLES20.glActiveTexture(33985);
        GLES20.glBindTexture(3553, this.renderTexture[3]);
        GLES20.glActiveTexture(33986);
        GLES20.glBindTexture(3553, this.toneCurve.getCurveTexture());
        GLES20.glDrawArrays(5, 0, 4);
        return true;
    L46:
        GLES20.glBindTexture(3553, this.bitmapTextre[0]);
        goto L47
    L36:
        GLES20.glBindTexture(3553, this.bitmapTextre[0]);
        goto L37
    L20:
        if (Math.abs(this.lastRadius - r13) <= 1.0E-4d) goto L26;
    L16:
        if (this.isVideo == false) goto L38;
    L50:
        if (this.skinPassDrawn == false) goto L52;
        this.hsvGenerated = false;
        this.skinPassDrawn = false;
    L52:
        return false;
    }

    public int getRenderBufferHeight() {
        return this.renderBufferHeight;
    }

    public int getRenderBufferWidth() {
        return this.renderBufferWidth;
    }

    public int getRenderFrameBuffer() {
        int[] r0 = this.renderFrameBuffer;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0[!this.isVideo ? 1 : 0];
    }

    public int getRenderTexture(int r2) {
        boolean r0 = this.isVideo;
        int[] r1 = this.renderTexture;
        if (r0 == false) goto L10;
        if (r2 != 0) goto L6;
        char r22 = 1;
    L8:
        return r1[r22];
    L6:
        r22 = 0;
        goto L8
    L10:
        return r1[r2];
    }

    public FloatBuffer getTextureBuffer() {
        return this.textureBuffer;
    }

    public FloatBuffer getVertexBuffer() {
        return this.vertexBuffer;
    }

    public FloatBuffer getVertexInvertBuffer() {
        return this.vertexInvertBuffer;
    }

    public void onVideoFrameUpdate(float[] r1) {
        this.videoMatrix = r1;
        this.hsvGenerated = false;
    }

    public void requestUpdateBlurTexture() {
        this.needUpdateBlurTexture = true;
        this.needUpdateSkinTexture = true;
    }

    public void setDelegate(FilterShadersDelegate r1) {
        this.delegate = r1;
    }

    public void setRenderData(Bitmap r10, int r11, int r12, int r13, int r14) {
        loadTexture(r10, r11, r13, r14);
        this.videoTexture = r12;
        this.videoWidth = r13;
        this.videoHeight = r14;
        GLES20.glBindTexture(3553, this.enhanceTextures[0]);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexImage2D(3553, 0, 6408, this.renderBufferWidth, this.renderBufferHeight, 0, 6408, 5121, null);
    }

    public void setScaleBitmap(boolean r1) {
        this.scaleBitmap = r1;
    }

    public void updateHDRInfo(StoryEntry.HDRInfo r1) {
        this.hdrInfo = r1;
        setupExternalShaders();
    }
}
