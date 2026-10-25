package org.telegram.messenger.wallpaper;

import android.graphics.Matrix;
import android.graphics.RectF;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.SvgHelper;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.xml.sax.Attributes;

/* loaded from: classes6.dex */
public class WallpaperGiftPatternPosition {
    public final Matrix matrix;
    public final RectF rect;

    private WallpaperGiftPatternPosition(RectF r1, Matrix r2) {
        this.rect = r1;
        this.matrix = r2;
    }

    public static WallpaperGiftPatternPosition create(Attributes r5, float r6) {
        float r0 = Float.parseFloat(r5.getValue("x"));     // Catch: Exception -> L4
        float r1 = Float.parseFloat(r5.getValue("y"));     // Catch: Exception -> L4
        float r2 = Float.parseFloat(r5.getValue("width"));     // Catch: Exception -> L4
        RectF r4 = new RectF(r0, r1, r2 + r0, Float.parseFloat(r5.getValue("height")) + r1);     // Catch: Exception -> L4
        Matrix r52 = SvgHelper.parseTransform(r5.getValue("transform"));     // Catch: Exception -> L4
        r52.postScale(r6, r6);     // Catch: Exception -> L4
        return new WallpaperGiftPatternPosition(r4, r52);
    L4:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static WallpaperGiftPatternPosition deserialize(InputSerializedData r15) {
        float r1 = r15.readFloat(true);
        float r2 = r15.readFloat(true);
        float r3 = r15.readFloat(true);
        float r4 = r15.readFloat(true);
        float[] r13 = {r15.readFloat(true), r15.readFloat(true), r15.readFloat(true), r15.readFloat(true), r15.readFloat(true), r15.readFloat(true), r15.readFloat(true), r15.readFloat(true), r15.readFloat(true)};
        RectF r152 = new RectF(r1, r2, r3 + r1, r4 + r2);
        Matrix r0 = new Matrix();
        r0.setValues(r13);
        return new WallpaperGiftPatternPosition(r152, r0);
    }

    public void serialize(OutputSerializedData r4) {
        r4.writeFloat(this.rect.left);
        r4.writeFloat(this.rect.top);
        r4.writeFloat(this.rect.width());
        r4.writeFloat(this.rect.height());
        float[] r1 = new float[9];
        this.matrix.getValues(r1);
        int r3 = 0;
    L3:
        if (r3 >= 9) goto L5;
        r4.writeFloat(r1[r3]);
        r3 = r3 + 1;
        goto L3
    }
}
