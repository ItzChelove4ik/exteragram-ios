package org.telegram.ui;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.text.Layout;
import android.view.Choreographer;
import android.view.View;
import androidx.core.math.MathUtils;
import java.util.ArrayList;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes6.dex */
public final class MultiLayoutTypingAnimator implements Choreographer.FrameCallback {
    private static final LinearGradient GRADIENT = null;
    private static final Matrix GRAD_MTX = null;
    private static final Paint MASK_PAINT = null;
    private final ArrayList<Float> blockAlphas;
    private List<? extends Block> blocks;
    private final Choreographer choreo;
    private int curBlockIdx;
    private int curLineIdx;
    private boolean finished;
    private View invalidateTarget;
    private long lastFrameNs;
    private View lastInvalidatedView;
    private Runnable onFinishRunnable;
    private boolean running;
    private float speedPxPerSec;
    private float xPosition;

    public interface Block {
        Layout getLayout();

        View getParentView();
    }

    public interface Renderer {
        void draw(Canvas r1);
    }

    static {
        GRAD_MTX = new Matrix();
        Paint r0 = new Paint(1);
        MASK_PAINT = r0;
        r0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        LinearGradient r3 = new LinearGradient(0.0f, 0.0f, 1.0f, 0.0f, -1, 16777215, Shader.TileMode.CLAMP);
        GRADIENT = r3;
        r0.setShader(r3);
    }

    public MultiLayoutTypingAnimator() {
        this.choreo = Choreographer.getInstance();
        this.blocks = new ArrayList();
        this.blockAlphas = new ArrayList();
        this.curBlockIdx = 0;
        this.curLineIdx = 0;
        this.xPosition = 0.0f;
        this.running = false;
        this.finished = true;
        this.lastFrameNs = 0;
        this.speedPxPerSec = AndroidUtilities.dp(40.0f);
    }

    private void advance(float r8) {
        if (this.blocks.isEmpty() == false) goto L5;
    L42:
        this.finished = this.blocks.isEmpty();
        return;
    L5:
        if (r8 <= 0.0f) goto L42;
        float r1 = this.speedPxPerSec * r8;
    L9:
        if (r1 <= 0.0f) goto L40;
        if (this.curBlockIdx >= this.blocks.size()) goto L12;
        Layout r82 = this.blocks.get(this.curBlockIdx).getLayout();
        if (r82 == null) goto L39;
        if (r82.getLineCount() == 0) goto L39;
        if (this.curLineIdx < r82.getLineCount()) goto L21;
        int r2 = r82.getLineCount() - 1;
        this.curLineIdx = r2;
        this.xPosition = lineWidth(r82, r2);
    L21:
        float r22 = lineWidth(r82, this.curLineIdx);
        if (r22 <= 0.001f) goto L24;
        float r4 = this.xPosition;
        float r5 = r22 - r4;
        if (r5 <= 0.001f) goto L29;
        if (r1 >= r5) goto L34;
        r5 = r1;
    L34:
        float r42 = r4 + r5;
        this.xPosition = r42;
        r1 = r1 - r5;
        if ((r22 - r42) > 0.001f) goto L9;
        if (nextLineOrBlock(r82) == true) goto L9;
        r1 = 0.0f;
        goto L9
    L29:
        if (nextLineOrBlock(r82) == true) goto L40;
    L24:
        if (nextLineOrBlock(r82) == true) goto L40;
    L39:
        this.curBlockIdx++;
        this.curLineIdx = 0;
        this.xPosition = 0.0f;
        goto L9
    L12:
        this.finished = true;
    L40:
        this.finished = isAtAbsoluteEnd();
    }

    private void applyBlockAlphas(float r9) {
        int r0 = this.blocks.size();
        View r1 = null;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L38;
        Block r3 = this.blocks.get(r2);
        if (this.finished == false) goto L7;
    L10:
        float r4 = 1.0f;
    L12:
        if (r2 >= this.blockAlphas.size()) goto L14;
        float r6 = this.blockAlphas.get(r2).floatValue();
    L16:
        if (r6 != r4) goto L19;
        r4 = r6;
    L25:
        if (r2 < this.blockAlphas.size()) goto L27;
    L29:
        View r32 = r3.getParentView();
        if (r32 == null) goto L37;
        if (r32 == r1) goto L37;
        if (r32.getAlpha() == r4) goto L36;
        r32.setAlpha(r4);
    L36:
        r1 = r32;
    L37:
        r2 = r2 + 1;
        goto L3
    L27:
        if (r4 == r6) goto L29;
        this.blockAlphas.set(r2, Float.valueOf(r4));
        goto L29
    L19:
        if (r9 <= 0.0f) goto L25;
        float r5 = r9 / 0.2f;
        if (r4 <= r6) goto L23;
        r4 = Math.min(r4, r5 + r6);
        goto L25
    L23:
        r4 = Math.max(r4, r6 - r5);
        goto L25
    L14:
        r6 = r4;
        goto L16
    L7:
        if (r2 <= this.curBlockIdx) goto L10;
        r4 = 0.0f;
        goto L12
    }

    private float computeRemainingPixels() {
        float r1 = 0.0f;
        if (this.blocks.isEmpty() == false) goto L5;
        return 0.0f;
    L5:
        int r0 = this.curBlockIdx;
    L7:
        if (r0 >= this.blocks.size()) goto L28;
        Layout r2 = this.blocks.get(r0).getLayout();
        if (r2 == null) goto L27;
        int r4 = 0;
        if (r0 != this.curBlockIdx) goto L14;
        r4 = Math.min(Math.max(this.curLineIdx, 0), Math.max(0, r2.getLineCount() - 1));
    L14:
        int r3 = r4;
    L16:
        if (r3 >= r2.getLineCount()) goto L27;
        float r5 = lineWidth(r2, r3);
        if (r5 <= 0.001f) goto L26;
        if (r0 != this.curBlockIdx) goto L25;
        if (r3 != r4) goto L25;
        r5 = r5 - this.xPosition;
        if (r5 <= 0.001f) goto L26;
    L25:
        r1 = r1 + r5;
    L26:
        r3 = r3 + 1;
    L27:
        r0 = r0 + 1;
        goto L7
    L28:
        return r1;
    }

    public static void drawLayoutWithLastLineFade(Canvas r1, Layout r2, int r3, float r4) {
        drawLayoutWithLastLineFade(r1, r2, r3, r4, null);
    }

    public static void drawLayoutWithLastLineFade(Canvas r13, Layout r14, int r15, float r16, Renderer r17) {
        if (r14 == null) goto L31;
        int r0 = r14.getLineCount();
        if (r15 < 0) goto L30;
        if (r15 >= r0) goto L32;
        if (r17 == null) goto L10;
        Renderer r02 = r17;
    L11:
        int r1 = r14.getWidth();
        r14.getHeight();
        int r2 = r14.getLineTop(r15);
        int r3 = r14.getLineBottom(r15);
        if (r2 <= 0) goto L14;
        r13.save();
        r13.clipRect(0.0f, 0.0f, r1, r2);
        r02.draw(r13);
        r13.restore();
    L14:
        float r5 = r14.getLineLeft(r15);
        float r6 = r14.getLineRight(r15);
        float r8 = Math.min(r5, r6);
        float r10 = Math.max(r5, r6);
        if (r10 <= r8) goto L33;
        int r142 = r14.getParagraphDirection(r15);
        float r152 = r10 - r8;
        float r52 = MathUtils.clamp(r16, 0.0f, r152);
        float r62 = r52 / r152;
        if (r52 > 0.0f) goto L21;
        return;
    L21:
        if (r52 < r152) goto L24;
        r13.save();
        r13.clipRect(0.0f, r2, r1, r3);
        r02.draw(r13);
        r13.restore();
        return;
    L24:
        float r53 = AndroidUtilities.lerp(-AndroidUtilities.dp(50.0f), r152, r62);
        AndroidUtilities.dp(50.0f);
        float r9 = r2;
        float r11 = r3;
        int r22 = r13.saveLayer(r8, r9, r10, r11, null);
        r13.save();
        r13.clipRect(r8, r9, r10, r11);
        r02.draw(r13);
        r13.restore();
        Matrix r03 = GRAD_MTX;
        r03.reset();
        if (r142 < 0) goto L27;
        r03.setScale(AndroidUtilities.dp(50.0f), 1.0f);
        r03.postTranslate(r53, 0.0f);
    L28:
        GRADIENT.setLocalMatrix(r03);
        r13.drawRect(r8, r9, r10, r11, MASK_PAINT);
        r13.restoreToCount(r22);
        return;
    L27:
        r03.setScale(-AndroidUtilities.dp(50.0f), 1.0f);
        r03.postTranslate(r152 - r53, 0.0f);
        goto L28
    L33:
        return;
    L10:
        r02 = new MultiLayoutTypingAnimator$$ExternalSyntheticLambda0(r14);
        goto L11
    L32:
        return;
    L30:
        return;
    }

    private void invalidate() {
        int r0 = this.curBlockIdx;
        if (r0 >= 0) goto L5;
    L9:
        View r02 = null;
    L10:
        if (r02 == null) goto L17;
        r02.invalidate();
        View r1 = this.lastInvalidatedView;
        if (r1 == null) goto L15;
        if (r1 == r02) goto L15;
        r1.invalidate();
    L15:
        this.lastInvalidatedView = r02;
        return;
    L17:
        View r2 = this.invalidateTarget;
        if (r2 == null) goto L21;
        r2.invalidate();
        return;
    L21:
        return;
    L5:
        if (r0 >= this.blocks.size()) goto L9;
        Block r03 = this.blocks.get(this.curBlockIdx);
        if (r03 == null) goto L9;
        r02 = r03.getParentView();
        goto L10
    }

    private boolean isAtAbsoluteEnd() {
        if (this.blocks.isEmpty() == false) goto L5;
        return true;
    L5:
        int r0 = this.blocks.size() - 1;
        Layout r2 = null;
    L6:
        if (r0 < 0) goto L13;
        r2 = this.blocks.get(r0).getLayout();
        if (r2 == null) goto L12;
        if (r2.getLineCount() > 0) goto L13;
    L12:
        r0 = r0 - 1;
    L13:
        if (r0 < 0) goto L28;
        if (r2 == null) goto L28;
        int r3 = this.curBlockIdx;
        if (r3 >= r0) goto L19;
        return false;
    L19:
        if (r3 <= r0) goto L21;
        return true;
    L21:
        int r02 = r2.getLineCount() - 1;
        float r22 = lineWidth(r2, r02);
        if (this.curLineIdx >= r02) goto L25;
        return false;
    L25:
        if (this.xPosition < (r22 - 0.001f)) goto L27;
        return true;
    L27:
        return false;
    L28:
        return true;
    }

    private float lineWidth(Layout r1, int r2) {
        float r0 = r1.getLineRight(r2) - r1.getLineLeft(r2);
        if (r0 < 0.0f) goto L6;
        return r0;
    L6:
        return -r0;
    }

    private boolean nextLineOrBlock(Layout r5) {
        int r0 = this.curLineIdx + 1;
        this.curLineIdx = r0;
        this.xPosition = 0.0f;
        if (r0 < r5.getLineCount()) goto L7;
        int r52 = this.curBlockIdx + 1;
        this.curBlockIdx = r52;
        this.curLineIdx = 0;
        this.xPosition = 0.0f;
        if (r52 < this.blocks.size()) goto L7;
        return true;
    L7:
        return false;
    }

    private void recalcSpeed() {
        float r0 = computeRemainingPixels();
        float r1 = AndroidUtilities.dp(40.0f);
        if (r0 > 0.001f) goto L6;
        this.speedPxPerSec = r1;
        return;
    L6:
        this.speedPxPerSec = Math.max(r1, r0 / 1.05f);
    }

    private void resetBlockAlphas() {
        int r0 = this.blockAlphas.size();
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r2 >= r0) goto L6;
        this.blockAlphas.set(r2, Float.valueOf(1.0f));
        r2 = r2 + 1;
        goto L4
    L6:
        int r02 = this.blocks.size();
        View r22 = null;
    L7:
        if (r1 >= r02) goto L17;
        View r4 = this.blocks.get(r1).getParentView();
        if (r4 == null) goto L16;
        if (r4 == r22) goto L16;
        if (r4.getAlpha() == 1.0f) goto L15;
        r4.setAlpha(1.0f);
    L15:
        r22 = r4;
    L16:
        r1 = r1 + 1;
        goto L7
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long r5) {
        if (this.running == true) goto L6;
        return;
    L6:
        if (this.lastFrameNs == 0) goto L8;
        float r0 = (r5 - r0) * 1.0E-9f;
        advance(r0);
    L9:
        this.lastFrameNs = r5;
        applyBlockAlphas(r0);
        invalidate();
        if (this.finished == false) goto L15;
        this.running = false;
        resetBlockAlphas();
        Runnable r52 = this.onFinishRunnable;
        if (r52 == null) goto L17;
        r52.run();
        this.onFinishRunnable = null;
        return;
    L17:
        return;
    L15:
        this.choreo.postFrameCallback(this);
        return;
    L8:
        r0 = 0.0f;
        goto L9
    }

    public float getBlockAlpha(Block r2) {
        int r22 = indexOf(r2);
        if (r22 >= 0) goto L5;
        return 1.0f;
    L5:
        if (r22 < this.blockAlphas.size()) goto L8;
        return 1.0f;
    L8:
        return this.blockAlphas.get(r22).floatValue();
    }

    public int getFadeLineIndex(Block r1) {
        if (isFadeBlock(r1) == true) goto L5;
        return -1;
    L5:
        return this.curLineIdx;
    }

    public float getFadeXPosition(Block r1) {
        if (isFadeBlock(r1) == true) goto L5;
        return 0.0f;
    L5:
        return this.xPosition;
    }

    public int indexOf(Block r4) {
        int r0 = this.blocks.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        if (this.blocks.get(r1) == r4) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r1;
    L8:
        return -1;
    }

    public boolean isFadeBlock(Block r4) {
        if (indexOf(r4) == this.curBlockIdx) goto L5;
        return false;
    L5:
        Layout r42 = r4.getLayout();
        if (r42 != null) goto L8;
    L12:
        return false;
    L8:
        if (this.curLineIdx >= r42.getLineCount()) goto L12;
        return true;
    }

    public boolean isRunning() {
        return this.running;
    }

    public boolean needDraw(Block r3) {
        int r32 = indexOf(r3);
        if (r32 >= 0) goto L5;
    L13:
        return false;
    L5:
        if (this.blocks.isEmpty() == true) goto L13;
        int r2 = this.curBlockIdx;
        if (r32 >= r2) goto L10;
        return true;
    L10:
        if (r32 <= r2) goto L12;
        return false;
    L12:
        return true;
    }

    public void setBlocks(List<? extends Block> r5) {
        if (this.blocks.isEmpty() == false) goto L5;
    L15:
        if (r5 != null) goto L18;
        r5 = new ArrayList();
    L18:
        this.blocks = r5;
        recalcSpeed();
        this.finished = isAtAbsoluteEnd();
    L19:
        int r52 = this.blockAlphas.size();
        int r0 = this.blocks.size();
        ArrayList<Float> r2 = this.blockAlphas;
        if (r52 <= r0) goto L22;
        r2.remove(r2.size() - 1);
        goto L19
    L22:
        int r53 = r2.size();
    L24:
        if (r53 >= this.blocks.size()) goto L34;
        ArrayList<Float> r02 = this.blockAlphas;
        if (this.finished == false) goto L28;
    L31:
        float r22 = 1.0f;
    L32:
        r02.add(Float.valueOf(r22));
        r53 = r53 + 1;
        goto L24
    L28:
        if (r53 <= this.curBlockIdx) goto L31;
        r22 = 0.0f;
        goto L32
    L34:
        if (this.finished == false) goto L36;
    L38:
        applyBlockAlphas(0.0f);
        invalidate();
        return;
    L36:
        if (this.running == true) goto L38;
        start();
        goto L38
    L5:
        if (this.curBlockIdx < this.blocks.size()) goto L15;
        int r03 = this.blocks.size() - 1;
        this.curBlockIdx = r03;
        Layout r04 = this.blocks.get(r03).getLayout();
        if (r04 != null) goto L9;
        int r3 = 0;
    L10:
        int r23 = Math.max(0, r3);
        this.curLineIdx = r23;
        if (r04 != null) goto L13;
        float r05 = 0.0f;
    L14:
        this.xPosition = r05;
        goto L15
    L13:
        r05 = r04.getLineWidth(r23);
        goto L14
    L9:
        r3 = r04.getLineCount() - 1;
        goto L10
    }

    public void setOnFinishListener(Runnable r1) {
        this.onFinishRunnable = r1;
    }

    public void start() {
        if (this.running == false) goto L5;
        return;
    L5:
        this.running = true;
        if (isAtAbsoluteEnd() == false) goto L8;
        this.finished = true;
    L8:
        this.lastFrameNs = 0;
        this.choreo.postFrameCallback(this);
    }
}
