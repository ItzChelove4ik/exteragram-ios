package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;

/* loaded from: classes3.dex */
public abstract class BatchParticlesDrawHelper {

    public static class BatchParticlesBuffer {
        protected final int[] batchColors;
        protected final float[] batchCordTexture;
        protected final float[] batchCordVertex;
        protected final short[] batchIdx;
        public final int vertexCount;

        public BatchParticlesBuffer(int r8) {
            this.vertexCount = r8;
            int r0 = r8 * 8;
            this.batchCordVertex = new float[r0];
            this.batchCordTexture = new float[r0];
            this.batchIdx = new short[r8 * 6];
            this.batchColors = new int[r8 * 4];
            short r02 = 0;
        L3:
            if (r02 >= r8) goto L5;
            int r1 = r02 * 6;
            int r2 = r02 * 4;
            short[] r3 = this.batchIdx;
            short r4 = (short) r2;
            r3[r1] = r4;
            r3[r1 + 1] = (short) (r2 + 1);
            short r6 = (short) (r2 + 2);
            r3[r1 + 2] = r6;
            r3[r1 + 3] = r6;
            r3[r1 + 4] = (short) (r2 + 3);
            r3[r1 + 5] = r4;
            r02 = (short) (r02 + 1);
            goto L3
        }

        private static void bufferVertexSet(float[] r1, int r2, float r3, float r4, float r5, float r6) {
            int r22 = r2 * 8;
            r1[r22] = r3;
            r1[r22 + 1] = r4;
            r1[r22 + 2] = r5;
            r1[r22 + 3] = r4;
            r1[r22 + 4] = r5;
            r1[r22 + 5] = r6;
            r1[r22 + 6] = r3;
            r1[r22 + 7] = r6;
        }

        public void fillParticleTextureCords(float r8, float r9, float r10, float r11) {
            int r2 = 0;
        L4:
            if (r2 >= this.vertexCount) goto L6;
            setParticleTextureCords(r2, r8, r9, r10, r11);
            r2 = r2 + 1;
            goto L4
        }

        public void setParticleColor(int r2, int r3) {
            int[] r1 = this.batchColors;
            int r22 = r2 * 4;
            r1[r22] = r3;
            r1[r22 + 1] = r3;
            r1[r22 + 2] = r3;
            r1[r22 + 3] = r3;
        }

        public void setParticleTextureCords(int r1, float r2, float r3, float r4, float r5) {
            bufferVertexSet(this.batchCordTexture, r1, r2, r3, r4, r5);
        }

        public void setParticleVertexCords(int r1, float r2, float r3, float r4, float r5) {
            bufferVertexSet(this.batchCordVertex, r1, r2, r3, r4, r5);
        }
    }

    public static Paint createBatchParticlesPaint(Bitmap r2) {
        Shader.TileMode r1 = Shader.TileMode.CLAMP;
        BitmapShader r0 = new BitmapShader(r2, r1, r1);
        if (Build.VERSION.SDK_INT < 33) goto L5;
        r0.setFilterMode(2);
    L5:
        Paint r22 = new Paint(1);
        r22.setShader(r0);
        r22.setFilterBitmap(true);
        return r22;
    }

    public static void draw(Canvas r13, BatchParticlesBuffer r14, int r15, int r16, Paint r17) {
        int r4 = r16 * 8;
        r13.drawVertices(Canvas.VertexMode.TRIANGLES, r15 * 8, r14.batchCordVertex, r4, r14.batchCordTexture, r4, r14.batchColors, r16 * 4, r14.batchIdx, r16 * 6, r15 * 6, r17);
    }

    public static void draw(Canvas r1, BatchParticlesBuffer r2, int r3, Paint r4) {
        draw(r1, r2, r3, 0, r4);
    }

    public static boolean isAvailable() {
        if (Build.VERSION.SDK_INT < 29) goto L6;
        return true;
    L6:
        return false;
    }
}
