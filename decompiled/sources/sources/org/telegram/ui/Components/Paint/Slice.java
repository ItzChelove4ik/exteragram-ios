package org.telegram.ui.Components.Paint;

import android.graphics.RectF;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;

/* loaded from: classes7.dex */
public class Slice {
    private final RectF bounds;
    private File file;
    private final int texture;

    public Slice(ByteBuffer r1, int r2, RectF r3, DispatchQueue r4) {
        this.bounds = r3;
        this.texture = r2;
        this.file = File.createTempFile("paint", ".bin", ApplicationLoader.applicationContext.getCacheDir());     // Catch: Exception -> L5
    L8:
        if (this.file != null) goto L10;
        return;
    L10:
        storeData(r1);
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    private void storeData(ByteBuffer r4) {
        byte[] r0 = r4.array();     // Catch: Exception -> L8
        FileOutputStream r1 = new FileOutputStream(this.file);     // Catch: Exception -> L8
        Deflater r3 = new Deflater(1, true);     // Catch: Exception -> L8
        r3.setInput(r0, r4.arrayOffset(), r4.remaining());     // Catch: Exception -> L8
        r3.finish();     // Catch: Exception -> L8
        byte[] r42 = new byte[1024];     // Catch: Exception -> L8
    L4:
        if (r3.finished() == true) goto L6;
        r1.write(r42, 0, r3.deflate(r42));     // Catch: Exception -> L8
        goto L4
    L6:
        r3.end();     // Catch: Exception -> L8
        r1.close();     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
    }

    public void cleanResources() {
        File r0 = this.file;
        if (r0 == null) goto L6;
        r0.delete();
        this.file = null;
        return;
    }

    public ByteBuffer getData() {
        byte[] r1 = new byte[1024];     // Catch: Exception -> L16
        byte[] r2 = new byte[1024];     // Catch: Exception -> L16
        FileInputStream r3 = new FileInputStream(this.file);     // Catch: Exception -> L16
        ByteArrayOutputStream r8 = new ByteArrayOutputStream();     // Catch: Exception -> L16
        Inflater r4 = new Inflater(true);     // Catch: Exception -> L16
    L4:
        int r5 = r3.read(r1);     // Catch: Exception -> L16
        if (r5 == (-1)) goto L7;
        r4.setInput(r1, 0, r5);     // Catch: Exception -> L16
    L7:
        int r52 = r4.inflate(r2, 0, 1024);     // Catch: Exception -> L16
        if (r52 == 0) goto L11;
        r8.write(r2, 0, r52);     // Catch: Exception -> L16
        goto L7
    L11:
        if (r4.finished() == true) goto L12;
        r4.needsInput();     // Catch: Exception -> L16
        goto L4
    L12:
        r4.end();     // Catch: Exception -> L16
        ByteBuffer r0 = ByteBuffer.wrap(r8.toByteArray(), 0, r8.size());     // Catch: Exception -> L16
        r8.close();     // Catch: Exception -> L16
        r3.close();     // Catch: Exception -> L16
        return r0;
    L16:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public int getHeight() {
        return (int) this.bounds.height();
    }

    public int getTexture() {
        return this.texture;
    }

    public int getWidth() {
        return (int) this.bounds.width();
    }

    public int getX() {
        return (int) this.bounds.left;
    }

    public int getY() {
        return (int) this.bounds.top;
    }
}
