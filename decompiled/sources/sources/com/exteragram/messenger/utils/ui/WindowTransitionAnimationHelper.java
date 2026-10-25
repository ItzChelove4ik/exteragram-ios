package com.exteragram.messenger.utils.ui;

import android.graphics.Canvas;
import android.graphics.RecordingCanvas;
import android.graphics.RenderNode;
import android.provider.Settings;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes4.dex */
public abstract class WindowTransitionAnimationHelper {

    public static final class WindowEdgeExtension {
        private boolean captured;
        private final RenderNode node;

        public WindowEdgeExtension() {
            this.node = WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline0.m("windowEdgeExtension");
        }

        public boolean capture(View r6, boolean r7) {
            int r0 = r6.getWidth();
            int r1 = r6.getHeight();
            if (r0 <= 0) goto L16;
            if (r1 <= 0) goto L16;
            this.node.setPosition(0, 0, 1, r1);
            this.node.setPivotX(0.0f);
            this.node.setPivotY(0.0f);
            RecordingCanvas r12 = this.node.beginRecording(1, r1);
            if (r7 == false) goto L9;
            float r72 = 0.0f;
        L17:
            r12.translate(r72, 0.0f);     // Catch: Throwable -> L13
            r6.draw(r12);     // Catch: Throwable -> L13
            this.node.endRecording();
            this.node.setUseCompositingLayer(true, null);
            this.captured = true;
            return true;
        L13:
            th = move-exception;
            this.node.endRecording();
            throw th;
        L9:
            r72 = -(r0 - 1);
        L16:
            return false;
        }

        public void draw(Canvas r3, float r4, float r5, float r6) {
            if (this.captured == true) goto L5;
            return;
        L5:
            if (r5 > 0.0f) goto L7;
            return;
        L7:
            if (r6 > 0.0f) goto L9;
            return;
        L9:
            if (WindowTransitionAnimationHelper$WindowEdgeExtension$$ExternalSyntheticApiModelOutline1.m(r3) == false) goto L16;
            this.node.setScaleX(r5 / 1.0f);
            this.node.setTranslationX(r4);
            this.node.setAlpha(r6);
            r3.drawRenderNode(this.node);
            return;
        }

        public boolean isCaptured() {
            return this.captured;
        }

        public void release() {
            if (this.captured == true) goto L5;
            return;
        L5:
            this.captured = false;
            this.node.setUseCompositingLayer(false, null);
            this.node.discardDisplayList();
        }
    }

    public static void apply(View r4, View r5, boolean r6, float r7) {
        float r72 = Utilities.clamp01(r7);
        float r0 = AndroidUtilities.dp(96.0f);
        float r1 = Math.round(CubicBezierInterpolator.Emphasized.getInterpolation(r72) * r0);
        float r3 = 1.0f;
        if (r4 == null) goto L12;
        if (r6 == false) goto L6;
        float r02 = r0 - r1;
    L7:
        r4.setTranslationX(r02);
        if (r6 == false) goto L10;
        float r03 = fade(r72, 50.0f, 83.0f);
    L11:
        r4.setAlpha(r03);
        goto L12
    L10:
        r03 = 1.0f;
        goto L11
    L6:
        r02 = r1 - r0;
    L12:
        if (r5 == null) goto L21;
        if (r6 == false) goto L15;
        r1 = -r1;
    L15:
        r5.setTranslationX(r1);
        if (r6 == true) goto L19;
        r3 = 1.0f - fade(r72, 35.0f, 83.0f);
    L19:
        r5.setAlpha(r3);
        return;
    }

    private static float fade(float r1, float r2, float r3) {
        return Utilities.clamp01(((r1 * 450.0f) - r2) / r3);
    }

    public static float getDuration() {
        float r1 = Settings.Global.getFloat(ApplicationLoader.applicationContext.getContentResolver(), "transition_animation_scale", 1.0f);     // Catch: Exception -> L5
    L7:
        return Math.max(1.0f, r1 * 450.0f);
    L5:
        r1 = 1.0f;
        goto L7
    }

    public static float getSlideDistance() {
        return AndroidUtilities.dp(96.0f);
    }
}
