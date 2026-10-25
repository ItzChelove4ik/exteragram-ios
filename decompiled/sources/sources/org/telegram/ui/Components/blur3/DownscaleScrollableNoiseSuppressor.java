package org.telegram.ui.Components.blur3;

import android.graphics.Canvas;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.graphics.Shader;
import com.exteragram.messenger.utils.ui.WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0;
import java.util.ArrayList;
import java.util.List;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.pip.source.PipSourceSnapshot$$ExternalSyntheticApiModelOutline0;
import org.telegram.messenger.utils.RenderNodeEffects;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.utils.glass.positions.GlassPositionsArray;

/* loaded from: classes3.dex */
public class DownscaleScrollableNoiseSuppressor {
    public final boolean allowNoiseSuppress;
    private final Blur3HashImpl builder;
    public final boolean isLiquidGlassEnabled;
    private final int k;
    long lastHash;
    private int recordingIndex;
    private Rect recordingPos;
    private final ArrayList<SourcePart> rectRenderNodes;
    private int rectRenderNodesCount;
    private final RenderNode[] resultRenderNodes;
    private final boolean simpleMode;
    private final RectF tmpRectF;

    public class DownscaledRenderNode {
        long lastHash;
        private final RenderNode[] renderNodeDownsampled;
        private final RenderNode renderNodeOriginalWithOffset;
        private final RenderNode[] renderNodeRestored;
        private int scaleX;
        private int scaleY;
        private float scrollX;
        private float scrollY;
        private final boolean simpleMode;
        final /* synthetic */ DownscaleScrollableNoiseSuppressor this$0;

        /* renamed from: -$$Nest$fgetrenderNodeRestored, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RenderNode[] m13658$$Nest$fgetrenderNodeRestored(DownscaledRenderNode r0) {
            return r0.renderNodeRestored;
        }

        public DownscaledRenderNode(DownscaleScrollableNoiseSuppressor r2, String r3, int r4) {
            this(r2, r3, r4, false);
        }

        public DownscaledRenderNode(DownscaleScrollableNoiseSuppressor r7, String r8, int r9, boolean r10) {
            this.this$0 = r7;
            this.renderNodeOriginalWithOffset = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m(null);
            int r0 = r9 + 1;
            this.renderNodeDownsampled = new RenderNode[r0];
            boolean r1 = false;
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L5;
            RenderNode[] r3 = this.renderNodeDownsampled;
            PipSourceSnapshot$$ExternalSyntheticApiModelOutline0.m();
            r3[r2] = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m(r8 + "_down_" + r9);
            r2 = r2 + 1;
            goto L3
        L5:
            if (r9 > 0) goto L9;
            if (r10 == true) goto L9;
            this.renderNodeRestored = this.renderNodeDownsampled;
        L13:
            if (this.renderNodeRestored != this.renderNodeDownsampled) goto L15;
            r1 = true;
        L15:
            this.simpleMode = r1;
            this.scaleY = 1;
            this.scaleX = 1;
            return;
        L9:
            this.renderNodeRestored = new RenderNode[r0];
            int r82 = 0;
        L10:
            if (r82 >= r0) goto L13;
            this.renderNodeRestored[r82] = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m(null);
            r82 = r82 + 1;
            goto L10
        }

        public void invalidateRenderNodes(RenderNode r18) {
            int r1 = r18.getWidth();
            int r2 = r18.getHeight();
            float r3 = r1;
            int r4 = Math.round((DownscaleScrollableNoiseSuppressor.m13655$$Nest$fgetk(this.this$0) * r3) / this.scaleX);
            float r5 = r2;
            int r6 = Math.round((DownscaleScrollableNoiseSuppressor.m13655$$Nest$fgetk(this.this$0) * r5) / this.scaleY);
            float r7 = r4;
            float r8 = r7 / r3;
            float r9 = r6;
            float r10 = r9 / r5;
            float r32 = (r3 * DownscaleScrollableNoiseSuppressor.m13655$$Nest$fgetk(this.this$0)) / r7;
            float r52 = (r5 * DownscaleScrollableNoiseSuppressor.m13655$$Nest$fgetk(this.this$0)) / r9;
            long r11 = MediaDataController.calcHash(MediaDataController.calcHash(MediaDataController.calcHash(MediaDataController.calcHash(MediaDataController.calcHash(0, r18.getUniqueId()), r4), r6), r1), r2);
            if (this.renderNodeOriginalWithOffset.hasDisplayList() == true) goto L5;
        L8:
            boolean r72 = true;
        L9:
            int r14 = 0;
        L11:
            if (r14 >= this.renderNodeDownsampled.length) goto L17;
            r72 = r72 | (!r15[r14].hasDisplayList());
            if (this.simpleMode == true) goto L15;
            r72 = r72 | (!this.renderNodeRestored[r14].hasDisplayList());
        L15:
            r14 = r14 + 1;
            goto L11
        L17:
            if (this.lastHash != r11) goto L20;
            if (r72 == true) goto L20;
            return;
        L20:
            this.lastHash = r11;
            this.renderNodeOriginalWithOffset.setPosition(0, 0, r1, r2);
            this.renderNodeOriginalWithOffset.beginRecording(r1, r2).drawRenderNode(r18);
            this.renderNodeOriginalWithOffset.endRecording();
            this.renderNodeDownsampled[0].setPosition(0, 0, r4, r6);
            RecordingCanvas r73 = this.renderNodeDownsampled[0].beginRecording(r4, r6);
            r73.scale(r8, r10);
            r73.drawRenderNode(this.renderNodeOriginalWithOffset);
            this.renderNodeDownsampled[0].endRecording();
            int r74 = 0;
        L21:
            RenderNode[] r92 = this.renderNodeDownsampled;
            if (r74 >= r92.length) goto L39;
            r92[r74].setPosition(0, 0, r4, r6);
            RecordingCanvas r93 = this.renderNodeDownsampled[r74].beginRecording(r4, r6);
            if (r74 <= 0) goto L26;
            r93.drawRenderNode(this.renderNodeDownsampled[0]);
        L27:
            this.renderNodeDownsampled[r74].endRecording();
            if (this.simpleMode == false) goto L30;
            this.renderNodeDownsampled[r74].setScaleX(r32);
            this.renderNodeDownsampled[r74].setScaleY(r52);
            this.renderNodeDownsampled[r74].setPivotX(0.0f);
            this.renderNodeDownsampled[r74].setPivotY(0.0f);
        L31:
            r74 = r74 + 1;
            goto L21
        L30:
            this.renderNodeRestored[r74].setPosition(0, 0, r1, r2);
            RecordingCanvas r94 = this.renderNodeRestored[r74].beginRecording(r1, r2);
            r94.scale(r32, r52);
            r94.drawRenderNode(this.renderNodeDownsampled[r74]);
            this.renderNodeRestored[r74].endRecording();
            goto L31
        L26:
            r93.scale(r8, r10);
            r93.drawRenderNode(this.renderNodeOriginalWithOffset);
            goto L27
        L39:
            return;
        L5:
            if (this.renderNodeDownsampled[0].hasDisplayList() == false) goto L8;
            r72 = false;
            goto L9
        }

        public void onScrolled(float r5, float r6) {
            int r0 = this.scaleX;
            float r1 = 0.0f;
            if (r0 < 2) goto L5;
            float r3 = (this.scrollX + r5) % r0;
        L6:
            this.scrollX = r3;
            int r52 = this.scaleY;
            if (r52 < 2) goto L9;
            r1 = (this.scrollY + r6) % r52;
        L9:
            this.scrollY = r1;
            if (this.this$0.allowNoiseSuppress == false) goto L14;
            this.renderNodeOriginalWithOffset.setTranslationX(r3);
            this.renderNodeOriginalWithOffset.setTranslationY(this.scrollY);
            RenderNode[] r53 = this.renderNodeRestored;
            int r62 = r53.length;
            int r02 = 0;
        L12:
            if (r02 >= r62) goto L16;
            RenderNode r12 = r53[r02];
            r12.setTranslationX(-this.scrollX);
            r12.setTranslationY(-this.scrollY);
            r02 = r02 + 1;
            goto L12
        L16:
            return;
        L14:
            return;
        L5:
            r3 = 0.0f;
            goto L6
        }

        public void setPrimaryEffect(RenderEffect r2) {
            this.renderNodeDownsampled[0].setRenderEffect(r2);
        }

        public void setPrimaryEffectBlur(float r3) {
            setPrimaryEffect(RenderEffect.createBlurEffect(DownscaleScrollableNoiseSuppressor.downscaleRadius(r3, this.scaleX), DownscaleScrollableNoiseSuppressor.downscaleRadius(r3, this.scaleY), Shader.TileMode.CLAMP));
        }

        public void setPrimaryEffectBlur(float r3, RenderEffect r4) {
            setPrimaryEffect(RenderEffect.createChainEffect(RenderEffect.createBlurEffect(DownscaleScrollableNoiseSuppressor.downscaleRadius(r3, this.scaleX), DownscaleScrollableNoiseSuppressor.downscaleRadius(r3, this.scaleY), Shader.TileMode.CLAMP), r4));
        }

        public void setScale(int r1, int r2) {
            this.scaleX = r1;
            this.scaleY = r2;
        }

        public void setSecondaryEffect(int r1, RenderEffect r2) {
            this.renderNodeDownsampled[r1 + 1].setRenderEffect(r2);
        }
    }

    public class SourcePart {
        long lastHash;
        final Rect position;
        final RenderNode renderNode;
        final DownscaledRenderNode renderNodesForBlur;
        final DownscaledRenderNode renderNodesForGlass;
        final /* synthetic */ DownscaleScrollableNoiseSuppressor this$0;

        /* renamed from: -$$Nest$msetPosition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13659$$Nest$msetPosition(SourcePart r0, RectF r1) {
            r0.setPosition(r1);
        }

        private SourcePart(DownscaleScrollableNoiseSuppressor r8) {
            this.this$0 = r8;
            this.renderNode = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m(null);
            this.position = new Rect();
            int r5 = 8;
            if (r8.isLiquidGlassEnabled == false) goto L7;
            DownscaledRenderNode r0 = new DownscaledRenderNode(r8, "glass", 0, true);
            this.renderNodesForGlass = r0;
            r0.setScale(4, 4);
            r0.setPrimaryEffectBlur(AndroidUtilities.dpf2(6.0f), RenderNodeEffects.getSaturationX3RenderEffect());
            DownscaledRenderNode r02 = new DownscaledRenderNode(r8, "blur", 0);
            this.renderNodesForBlur = r02;
            r02.setScale(8, 8);
            r02.setPrimaryEffectBlur(AndroidUtilities.dpf2(38.34f));
            return;
        L7:
            if (DownscaleScrollableNoiseSuppressor.m13656$$Nest$fgetsimpleMode(r8) == false) goto L16;
            DownscaledRenderNode r1 = new DownscaledRenderNode(r8, "blur", 0);
            this.renderNodesForBlur = r1;
            boolean r82 = r8.allowNoiseSuppress;
            if (r82 == false) goto L11;
            int r3 = 16;
        L12:
            if (r82 == false) goto L14;
            r5 = 16;
        L14:
            r1.setScale(r3, r5);
            r1.setPrimaryEffectBlur(AndroidUtilities.dpf2(40.0f), RenderNodeEffects.getSaturationX3RenderEffect());
            this.renderNodesForGlass = null;
            return;
        L11:
            r3 = 8;
            goto L12
        L16:
            DownscaledRenderNode r12 = new DownscaledRenderNode(r8, "blur", 1);
            this.renderNodesForBlur = r12;
            r12.setScale(8, 8);
            r12.setPrimaryEffectBlur(AndroidUtilities.dpf2(40.0f));
            r12.setSecondaryEffect(0, RenderNodeEffects.getSaturationX3RenderEffect());
            this.renderNodesForGlass = null;
        }

        public /* synthetic */ SourcePart(DownscaleScrollableNoiseSuppressor r1, DownscaleScrollableNoiseSuppressorIA r2) {
            this(r1);
        }

        private void setPosition(RectF r4) {
            Rect r0 = this.position;
            r0.left = DownscaleScrollableNoiseSuppressor.m13657$$Nest$smroundDown(r4.left, 16);
            Rect r02 = this.position;
            r02.top = DownscaleScrollableNoiseSuppressor.m13657$$Nest$smroundDown(r4.top, 16);
            Rect r03 = this.position;
            r03.right = DownscaleScrollableNoiseSuppressor.roundUp(r4.right, 16);
            Rect r3 = this.position;
            r3.bottom = DownscaleScrollableNoiseSuppressor.roundUp(r4.bottom, 16);
        }

        public void invalidate() {
            DownscaledRenderNode r0 = this.renderNodesForGlass;
            if (r0 == null) goto L6;
            r0.invalidateRenderNodes(this.renderNode);
            this.renderNodesForBlur.invalidateRenderNodes(DownscaledRenderNode.m13658$$Nest$fgetrenderNodeRestored(this.renderNodesForGlass)[0]);
            return;
        L6:
            this.renderNodesForBlur.invalidateRenderNodes(this.renderNode);
        }
    }

    /* renamed from: -$$Nest$fgetk, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13655$$Nest$fgetk(DownscaleScrollableNoiseSuppressor r0) {
        return r0.k;
    }

    /* renamed from: -$$Nest$fgetsimpleMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13656$$Nest$fgetsimpleMode(DownscaleScrollableNoiseSuppressor r0) {
        return r0.simpleMode;
    }

    /* renamed from: -$$Nest$smroundDown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13657$$Nest$smroundDown(float r0, int r1) {
        return roundDown(r0, r1);
    }

    public DownscaleScrollableNoiseSuppressor() {
        this(true, false);
    }

    public DownscaleScrollableNoiseSuppressor(boolean r4, boolean r5) {
        this.tmpRectF = new RectF();
        this.builder = new Blur3HashImpl();
        this.rectRenderNodes = new ArrayList();
        boolean r0 = LiteMode.isEnabled(262144);
        this.isLiquidGlassEnabled = r0;
        this.simpleMode = r4;
        int r1 = 1;
        if (r0 == true) goto L7;
        if (r5 == true) goto L7;
        int r2 = 8;
    L8:
        this.k = r2;
        this.allowNoiseSuppress = r5;
        if (r0 == true) goto L11;
        if (r4 == false) goto L11;
    L12:
        this.resultRenderNodes = new RenderNode[r1];
        int r42 = 0;
    L13:
        RenderNode[] r52 = this.resultRenderNodes;
        if (r42 >= r52.length) goto L16;
        r52[r42] = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m(null);
        r42 = r42 + 1;
        goto L13
    L16:
        return;
    L11:
        r1 = 2;
    L7:
        r2 = 1;
        goto L8
    }

    private RecordingCanvas beginRecordingRect(int r5) {
        if (this.recordingPos != null) goto L6;
        SourcePart r0 = this.rectRenderNodes.get(r5);
        Rect r1 = r0.position;
        this.recordingPos = r1;
        this.recordingIndex = r5;
        int r52 = r1.width() / this.k;
        int r12 = r1.height() / this.k;
        r0.renderNode.setPosition(0, 0, r52, r12);
        RecordingCanvas r53 = r0.renderNode.beginRecording(r52, r12);
        int r4 = this.k;
        r53.scale(1.0f / r4, 1.0f / r4);
        return r53;
    L6:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    public static float convertRadiusToSigma(float r2) {
        if (r2 > 0.0f) goto L5;
        return 0.0f;
    L5:
        return (r2 * 0.57735f) + 0.5f;
    }

    public static float convertSigmaToRadius(float r2) {
        if (r2 > 0.5f) goto L5;
        return 0.0f;
    L5:
        return (r2 - 0.5f) / 0.57735f;
    }

    public static float downscaleRadius(float r0, float r1) {
        return Math.max(1.0f, convertSigmaToRadius(convertRadiusToSigma(r0) / r1));
    }

    private void endRecordingRect() {
        if (this.recordingPos == null) goto L6;
        SourcePart r0 = this.rectRenderNodes.get(this.recordingIndex);
        r0.renderNode.endRecording();
        r0.invalidate();
        this.recordingPos = null;
        return;
    L6:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }

    private RenderNode getRenderNode(int r2, int r3) {
        SourcePart r32 = this.rectRenderNodes.get(r3);
        if (this.isLiquidGlassEnabled == false) goto L13;
        DownscaledRenderNode r1 = r32.renderNodesForGlass;
        if (r1 == null) goto L13;
        if (r2 != 0) goto L11;
        return DownscaledRenderNode.m13658$$Nest$fgetrenderNodeRestored(r1)[0];
    L11:
        return DownscaledRenderNode.m13658$$Nest$fgetrenderNodeRestored(r32.renderNodesForBlur)[0];
    L13:
        return DownscaledRenderNode.m13658$$Nest$fgetrenderNodeRestored(r32.renderNodesForBlur)[Math.min(r2, DownscaledRenderNode.m13658$$Nest$fgetrenderNodeRestored(r32.renderNodesForBlur).length - 1)];
    }

    private boolean invalidateResultRenderNodes(int r13, int r14) {
        long r0 = MediaDataController.calcHash(MediaDataController.calcHash(0, r13), r14);
        int r3 = 0;
        boolean r4 = false;
    L3:
        RenderNode[] r5 = this.resultRenderNodes;
        if (r3 >= r5.length) goto L14;
        RenderNode r52 = r5[r3];
        r0 = MediaDataController.calcHash(r0, r52.getUniqueId());
        int r6 = 0;
    L7:
        if (r6 >= this.rectRenderNodesCount) goto L10;
        SourcePart r8 = this.rectRenderNodes.get(r6);
        r0 = MediaDataController.calcHash(MediaDataController.calcHash(MediaDataController.calcHash(MediaDataController.calcHash(MediaDataController.calcHash(r0, r8.position.left), r8.position.top), r8.position.right), r8.position.bottom), getRenderNode(r3, r6).getUniqueId());
        r6 = r6 + 1;
        goto L7
    L10:
        if (r52.hasDisplayList() == true) goto L12;
        r4 = true;
    L12:
        r3 = r3 + 1;
        goto L3
    L14:
        if (r0 != this.lastHash) goto L17;
        if (r4 == true) goto L17;
        return false;
    L17:
        this.lastHash = r0;
        int r02 = 0;
    L18:
        RenderNode[] r1 = this.resultRenderNodes;
        if (r02 >= r1.length) goto L25;
        RenderNode r12 = r1[r02];
        r12.setPosition(0, 0, r13, r14);
        RecordingCanvas r32 = r12.beginRecording(r13, r14);
        int r42 = 0;
    L22:
        if (r42 >= this.rectRenderNodesCount) goto L24;
        SourcePart r53 = this.rectRenderNodes.get(r42);
        r32.save();
        Rect r54 = r53.position;
        r32.translate(r54.left, r54.top);
        r32.drawRenderNode(getRenderNode(r02, r42));
        r32.restore();
        r42 = r42 + 1;
        goto L22
    L24:
        r12.endRecording();
        r02 = r02 + 1;
        goto L18
    L25:
        return true;
    }

    private static int roundDown(float r0, int r1) {
        return Math.round(r0 - (r0 % r1));
    }

    public static int roundUp(float r1, int r2) {
        float r22 = r2;
        return Math.round(r1 + (r22 - (r1 % r22)));
    }

    public void draw(Canvas r5, int r6) {
        if (r5.isHardwareAccelerated() == false) goto L22;
        boolean r0 = this.isLiquidGlassEnabled;
        if (r0 == true) goto L11;
        if (this.simpleMode == false) goto L11;
        r5.drawRenderNode(this.resultRenderNodes[0]);
        return;
    L11:
        if (r6 != (-2)) goto L15;
        r5.drawRenderNode(this.resultRenderNodes[!r0 ? 1 : 0]);
        return;
    L15:
        if (r6 != (-4)) goto L19;
        r5.drawRenderNode(this.resultRenderNodes[0]);
        return;
    L19:
        if (r6 != (-3)) goto L24;
        r5.drawRenderNode(this.resultRenderNodes[1]);
        return;
    L24:
        return;
    L22:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }

    public void drawInline(Canvas r7, int r8) {
        boolean r0 = this.isLiquidGlassEnabled;
        int r1 = 0;
        if (r0 == false) goto L5;
    L7:
        int r3 = 1;
        if (r8 != (-2)) goto L11;
        r3 = 1 ^ (r0 ? 1 : 0);
    L16:
        if (r1 >= this.rectRenderNodesCount) goto L27;
        SourcePart r82 = this.rectRenderNodes.get(r1);
        Rect r02 = r82.position;
        if (r7.quickReject(r02.left, r02.top, r02.right, r02.bottom) == true) goto L21;
        r7.save();
        Rect r83 = r82.position;
        r7.translate(r83.left, r83.top);
        r7.drawRenderNode(getRenderNode(r3, r1));
        r7.restore();
    L21:
        r1 = r1 + 1;
        goto L16
    L27:
        return;
    L11:
        if (r8 != (-4)) goto L14;
    L6:
        r3 = 0;
        goto L16
    L14:
        if (r8 == (-3)) goto L16;
        return;
    L5:
        if (this.simpleMode == false) goto L7;
        goto L6
    }

    public int getRenderNodesCount() {
        return this.rectRenderNodesCount;
    }

    public boolean invalidateResultRenderNodes(IBlur3Capture r10, int r11, int r12) {
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= this.rectRenderNodesCount) goto L14;
        SourcePart r3 = this.rectRenderNodes.get(r1);
        this.tmpRectF.set(r3.position);
        this.builder.start();
        r10.captureCalculateHash(this.builder, this.tmpRectF);
        long r5 = this.builder.get();
        if (this.builder.isUnsupported() == false) goto L8;
    L12:
        r3.lastHash = r5;
        RecordingCanvas r32 = beginRecordingRect(r1);
        r32.save();
        r32.translate(-r4.left, -r4.top);
        r10.capture(r32, this.tmpRectF);
        r32.restore();
        endRecordingRect();
        r2 = r2 + 1;
    L13:
        r1 = r1 + 1;
        goto L4
    L8:
        if (r3.lastHash != r5) goto L12;
        if (r3.renderNode.hasDisplayList() == false) goto L12;
    L14:
        if (r2 > 0) goto L16;
        return false;
    L16:
        return invalidateResultRenderNodes(r11, r12);
    }

    public void onScrolled(float r4, float r5) {
        int r0 = 0;
    L4:
        if (r0 >= this.rectRenderNodesCount) goto L9;
        SourcePart r1 = this.rectRenderNodes.get(r0);
        r1.renderNodesForBlur.onScrolled(r4, r5);
        DownscaledRenderNode r12 = r1.renderNodesForGlass;
        if (r12 == null) goto L8;
        r12.onScrolled(r4, r5);
    L8:
        r0 = r0 + 1;
        goto L4
    }

    public void setupRenderNodes(List<RectF> r3, int r4) {
        this.rectRenderNodesCount = r4;
    L4:
        if (this.rectRenderNodesCount <= this.rectRenderNodes.size()) goto L6;
        this.rectRenderNodes.add(new SourcePart(this, null));
        goto L4
    L6:
        int r42 = 0;
    L8:
        if (r42 >= this.rectRenderNodesCount) goto L10;
        SourcePart.m13659$$Nest$msetPosition(this.rectRenderNodes.get(r42), r3.get(r42));
        r42 = r42 + 1;
        goto L8
    }

    public void setupRenderNodes(GlassPositionsArray r4) {
        this.rectRenderNodesCount = r4.size();
    L4:
        if (this.rectRenderNodesCount <= this.rectRenderNodes.size()) goto L6;
        this.rectRenderNodes.add(new SourcePart(this, null));
        goto L4
    L6:
        int r0 = 0;
    L8:
        if (r0 >= this.rectRenderNodesCount) goto L10;
        SourcePart.m13659$$Nest$msetPosition(this.rectRenderNodes.get(r0), r4.get(r0));
        r0 = r0 + 1;
        goto L8
    }
}
