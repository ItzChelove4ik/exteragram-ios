package org.telegram.ui.Components.Paint;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.os.Build;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.telegram.messenger.FileLog;
import org.telegram.ui.Components.Size;

/* loaded from: classes7.dex */
public class Texture {
    private Bitmap bitmap;
    private int texture;

    public Texture(Bitmap r1) {
        this.bitmap = r1;
    }

    public static int generateTexture(int r13, int r14) {
        int[] r1 = new int[1];
        GLES20.glGenTextures(1, r1, 0);
        int r0 = r1[0];
        GLES20.glBindTexture(3553, r0);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexImage2D(3553, 0, 6408, r13, r14, 0, 6408, 5121, null);
        return r0;
    }

    public static int generateTexture(Size r1) {
        return generateTexture((int) r1.width, (int) r1.height);
    }

    public void cleanResources(boolean r4) {
        int r0 = this.texture;
        if (r0 == 0) goto L16;
        GLES20.glDeleteTextures(1, new int[]{r0}, 0);
        this.texture = 0;
        if (r4 == false) goto L13;
        Bitmap r42 = this.bitmap;
        if (r42 != null) goto L10;
        return;
    L10:
        if (r42.isRecycled() == true) goto L15;
        this.bitmap.recycle();
        return;
    L15:
        return;
    L13:
        return;
    }

    public int texture() {
        int r0 = this.texture;
        if (r0 == 0) goto L5;
        return r0;
    L5:
        Bitmap r02 = this.bitmap;
        if (r02 != null) goto L8;
    L25:
        return 0;
    L8:
        if (r02.isRecycled() == true) goto L25;
        int[] r2 = new int[1];
        GLES20.glGenTextures(1, r2, 0);
        int r03 = r2[0];
        this.texture = r03;
        GLES20.glBindTexture(3553, r03);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLUtils.texImage2D(3553, 0, 6408, this.bitmap, 5121, 0);     // Catch: Exception -> L13
    L19:
        if (this.bitmap.isRecycled() == false) goto L21;
    L23:
        Utils.HasGLError();
        return this.texture;
    L21:
        if (Build.VERSION.SDK_INT > 28) goto L23;
        int r04 = this.bitmap.getPixel(0, 0);
        ByteBuffer r11 = ByteBuffer.allocateDirect(4);
        r11.putInt(((r04 >> 16) & 255) | (((-16711936) & r04) | ((r04 & 255) << 16))).position(0);
        GLES20.glTexSubImage2D(3553, 0, 0, 0, 1, 1, 6408, 5121, r11);
    L13:
        e = move-exception;
        FileLog.e(e);
        int r6 = this.bitmap.getWidth();
        int r7 = this.bitmap.getHeight();
        int r05 = r6 * r7;
        int[] r4 = new int[r05];
        this.bitmap.getPixels(r4, 0, r6, 0, 0, r6, r7);
        int r3 = 0;
    L15:
        if (r3 >= r05) goto L17;
        int r5 = r4[r3];
        r4[r3] = ((r5 >> 16) & 255) | ((r5 & (-16711936)) | ((r5 & 255) << 16));
        r3 = r3 + 1;
        goto L15
    L17:
        GLES20.glTexImage2D(3553, 0, 6408, r6, r7, 0, 6408, 5121, IntBuffer.wrap(r4));
        goto L19
    }
}
