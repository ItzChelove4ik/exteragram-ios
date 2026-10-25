package org.telegram.ui.Components.blur3;

import android.graphics.Color;
import android.graphics.RenderEffect;
import android.graphics.RenderNode;
import android.graphics.RuntimeShader;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.ui.Components.MotionBackgroundPaint$AgslImpl$RuntimeShaderState$$ExternalSyntheticApiModelOutline0;
import org.telegram.ui.Components.MotionBackgroundPaint$AgslImpl$RuntimeShaderState$$ExternalSyntheticApiModelOutline1;

/* loaded from: classes7.dex */
public class LiquidGlassEffect {
    private static String shaderCode;
    private float centerX;
    private float centerY;
    private RenderEffect effect;
    private int foregroundColor;
    private float index;
    private float intensity;
    private final RenderNode node;
    private float radiusLeftBottom;
    private float radiusLeftTop;
    private float radiusRightBottom;
    private float radiusRightTop;
    private float resolutionX;
    private float resolutionY;
    private final RuntimeShader shader;
    private float sizeX;
    private float sizeY;
    private float thickness;

    public LiquidGlassEffect(RenderNode r3) {
        this.node = r3;
        if (shaderCode != null) goto L5;
        shaderCode = AndroidUtilities.readRes(R.raw.liquid_glass_shader);
    L5:
        MotionBackgroundPaint$AgslImpl$RuntimeShaderState$$ExternalSyntheticApiModelOutline1.m();
        RuntimeShader r0 = MotionBackgroundPaint$AgslImpl$RuntimeShaderState$$ExternalSyntheticApiModelOutline0.m(shaderCode);
        this.shader = r0;
        RenderEffect r02 = RenderEffect.createRuntimeShaderEffect(r0, "img");
        this.effect = r02;
        r3.setRenderEffect(r02);
    }

    public void update(float r18, float r19, float r20, float r21, float r22, float r23, float r24, float r25, float r26, float r27, float r28, int r29) {
        float r5 = this.node.getWidth();
        float r6 = this.node.getHeight();
        float r7 = (r18 + r20) / 2.0f;
        float r9 = (r19 + r21) / 2.0f;
        float r11 = r21 - r19;
        float r10 = (r20 - r18) / 2.0f;
        float r8 = r11 / 2.0f;
        float r12 = r22 + r25;
        if (r12 <= r11) goto L5;
        float r122 = r22 / r12;
        float r13 = r11 * r122;
        float r123 = (1.0f - r122) * r11;
    L6:
        float r15 = r23 + r24;
        if (r15 <= r11) goto L9;
        float r152 = r23 / r15;
        float r14 = r11 * (1.0f - r152);
        float r112 = r11 * r152;
    L11:
        if (Math.abs(this.resolutionX - r5) <= 0.1f) goto L13;
    L40:
        this.foregroundColor = r29;
        float r153 = Color.alpha(r29) / 255.0f;
        RuntimeShader r4 = this.shader;
        this.resolutionX = r5;
        this.resolutionY = r6;
        r4.setFloatUniform("resolution", r5, r6);
        RuntimeShader r42 = this.shader;
        this.centerX = r7;
        this.centerY = r9;
        r42.setFloatUniform("center", r7, r9);
        RuntimeShader r43 = this.shader;
        this.sizeX = r10;
        this.sizeY = r8;
        r43.setFloatUniform("size", r10, r8);
        RuntimeShader r44 = this.shader;
        this.radiusRightBottom = r14;
        this.radiusRightTop = r112;
        this.radiusLeftBottom = r123;
        this.radiusLeftTop = r13;
        r44.setFloatUniform("radius", r14, r112, r123, r13);
        RuntimeShader r45 = this.shader;
        this.thickness = r26;
        r45.setFloatUniform("thickness", r26);
        RuntimeShader r1 = this.shader;
        this.intensity = r27;
        r1.setFloatUniform("refract_intensity", r27);
        RuntimeShader r16 = this.shader;
        this.index = r28;
        r16.setFloatUniform("refract_index", r28);
        this.shader.setFloatUniform("foreground_color_premultiplied", (Color.red(r29) / 255.0f) * r153, (Color.green(r29) / 255.0f) * r153, (Color.blue(r29) / 255.0f) * r153, r153);
        RenderNode r17 = this.node;
        RenderEffect r2 = RenderEffect.createRuntimeShaderEffect(this.shader, "img");
        this.effect = r2;
        r17.setRenderEffect(r2);
        return;
    L13:
        if (Math.abs(this.resolutionY - r6) > 0.1f) goto L40;
        if (Math.abs(this.centerX - r7) > 0.1f) goto L40;
        if (Math.abs(this.centerY - r9) > 0.1f) goto L40;
        if (Math.abs(this.sizeX - r10) > 0.1f) goto L40;
        if (Math.abs(this.sizeY - r8) > 0.1f) goto L40;
        if (Math.abs(this.radiusLeftTop - r13) > 0.1f) goto L40;
        if (Math.abs(this.radiusRightTop - r112) > 0.1f) goto L40;
        if (Math.abs(this.radiusRightBottom - r14) > 0.1f) goto L40;
        if (Math.abs(this.radiusLeftBottom - r123) > 0.1f) goto L40;
        if (Math.abs(this.thickness - r26) > 0.1f) goto L40;
        if (Math.abs(this.intensity - r27) > 0.1f) goto L40;
        if (Math.abs(this.index - r28) > 0.1f) goto L40;
        if (this.foregroundColor != r29) goto L40;
        return;
    L9:
        r112 = r23;
        r14 = r24;
        goto L11
    L5:
        r13 = r22;
        r123 = r25;
        goto L6
    }
}
