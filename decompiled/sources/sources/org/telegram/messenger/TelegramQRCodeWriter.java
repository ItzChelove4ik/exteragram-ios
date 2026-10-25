package org.telegram.messenger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.GradientDrawable;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import java.util.Arrays;
import java.util.Map;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class TelegramQRCodeWriter {
    private static final int QUIET_ZONE_SIZE = 4;
    private int imageBlockX;
    private int imageBloks;
    private int imageSize;
    public boolean includeSideQuads;
    private ByteMatrix input;
    private float[] radii;
    private int sideQuadSize;

    public TelegramQRCodeWriter() {
        this.radii = new float[8];
        this.includeSideQuads = true;
    }

    public static void drawSideQuads(Canvas r11, float r12, float r13, Paint r14, float r15, float r16, int r17, float r18, float r19, float[] r20, boolean r21) {
        Path r1 = new Path();
        int r2 = 0;
    L4:
        if (r2 >= 3) goto L18;
        if (r2 != 0) goto L8;
        float r3 = r17;
        float r4 = r3;
    L11:
        float r32 = r3 + r12;
        float r42 = r4 + r13;
        if (r21 == false) goto L14;
        RectF r6 = AndroidUtilities.rectTmp;
        float r9 = (r15 - 1.0f) * r16;
        r6.set(r32 + r16, r42 + r16, r32 + r9, r9 + r42);
        float r7 = ((r15 * r16) / 4.0f) * r19;
        r1.reset();
        r1.addRoundRect(r6, r7, r7, Path.Direction.CW);
        r1.close();
        r11.save();
        r11.clipPath(r1, Region.Op.DIFFERENCE);
    L14:
        float r62 = r15 * r16;
        float r72 = (r62 / 3.0f) * r19;
        RectF r8 = AndroidUtilities.rectTmp;
        r8.set(r32, r42, r32 + r62, r62 + r42);
        r11.drawRoundRect(r8, r72, r72, r14);
        if (r21 == false) goto L17;
        r11.restore();
    L17:
        float r73 = (r15 - 2.0f) * r16;
        float r5 = (r73 / 4.0f) * r19;
        float r63 = 2.0f * r16;
        r8.set(r32 + r63, r63 + r42, r32 + r73, r42 + r73);
        r11.drawRoundRect(r8, r5, r5, r14);
        r2 = r2 + 1;
        goto L4
    L8:
        if (r2 != 1) goto L10;
        r4 = r17;
        r3 = (r18 - (r15 * r16)) - r4;
        goto L11
    L10:
        r3 = r17;
        r4 = (r18 - (r15 * r16)) - r3;
        goto L11
    }

    public static void drawSideQuadsGradient(Canvas r19, Paint r20, GradientDrawable r21, float r22, float r23, int r24, float r25, float r26, float[] r27, int r28, int r29) {
        if (Color.alpha(r28) != 0) goto L5;
        boolean r11 = true;
    L6:
        r21.setShape(0);
        r21.setCornerRadii(r27);
        Path r12 = new Path();
        RectF r13 = new RectF();
        int r14 = 0;
    L8:
        if (r14 >= 3) goto L26;
        if (r14 != 0) goto L11;
        float r1 = r24;
        float r15 = r1;
    L16:
        if (r11 == false) goto L18;
        float r5 = (r22 - 1.0f) * r23;
        float r17 = 1.0f;
        r13.set(r15 + r23, r1 + r23, r15 + r5, r5 + r1);
        float r2 = ((r22 * r23) / 4.0f) * r26;
        r12.reset();
        r12.addRoundRect(r13, r2, r2, Path.Direction.CW);
        r12.close();
        r19.save();
        r19.clipPath(r12, Region.Op.DIFFERENCE);
    L19:
        float r18 = r22 * r23;
        Arrays.fill(r27, (r18 / 3.0f) * r26);
        r21.setColor(r29);
        r21.setBounds((int) r15, (int) r1, (int) (r15 + r18), (int) (r1 + r18));
        r21.draw(r19);
        float r210 = r1;
        float r16 = r15 + r23;
        float r211 = r210 + r23;
        float r4 = (r22 - r17) * r23;
        float r3 = r15 + r4;
        float r42 = r4 + r210;
        r19.drawRect(r16, r211, r3, r42, r20);
        if (r11 == false) goto L22;
        r19.restore();
    L22:
        if (r11 == true) goto L25;
        Arrays.fill(r27, (r18 / 4.0f) * r26);
        r21.setColor(r28);
        r21.setBounds((int) r16, (int) r211, (int) r3, (int) r42);
        r21.draw(r19);
    L25:
        float r212 = (r22 - 2.0f) * r23;
        Arrays.fill(r27, (r212 / 4.0f) * r26);
        r21.setColor(r29);
        float r110 = 2.0f * r23;
        r21.setBounds((int) (r15 + r110), (int) (r210 + r110), (int) (r15 + r212), (int) (r210 + r212));
        r21.draw(r19);
        r14 = r14 + 1;
        goto L8
    L18:
        r17 = 1.0f;
        goto L19
    L11:
        if (r14 != 1) goto L14;
        float r213 = r24;
        float r111 = (r25 - (r22 * r23)) - r213;
    L13:
        r15 = r111;
        r1 = r213;
        goto L16
    L14:
        r111 = r24;
        r213 = (r25 - (r22 * r23)) - r111;
        goto L13
    L26:
        return;
    L5:
        r11 = false;
        goto L6
    }

    private boolean has(int r5, int r6) {
        int r0 = this.imageBlockX;
        if (r5 < r0) goto L11;
        int r2 = this.imageBloks;
        if (r5 >= (r0 + r2)) goto L11;
        if (r6 < r0) goto L11;
        if (r6 >= (r0 + r2)) goto L11;
        return false;
    L11:
        if (r5 < this.sideQuadSize) goto L15;
        if (r5 >= (this.input.getWidth() - this.sideQuadSize)) goto L15;
    L18:
        if (r5 < this.sideQuadSize) goto L20;
    L22:
        if (r5 < 0) goto L31;
        if (r6 < 0) goto L31;
        if (r5 >= this.input.getWidth()) goto L31;
        if (r6 >= this.input.getHeight()) goto L31;
        if (this.input.get(r5, r6) != 1) goto L31;
        return true;
    L31:
        return false;
    L20:
        if (r6 < (this.input.getHeight() - this.sideQuadSize)) goto L22;
        return false;
    L15:
        if (r6 >= this.sideQuadSize) goto L18;
        return false;
    }

    public Bitmap encode(String r10, int r11, int r12, Map<EncodeHintType, ?> r13, Bitmap r14) {
        return encode(r10, r11, r12, r13, r14, 1.0f, -1, -16777216);
    }

    public Bitmap encode(String r11, int r12, int r13, Map<EncodeHintType, ?> r14, Bitmap r15, float r16, int r17, int r18) {
        return encode(r11, r12, r13, r14, r15, r16, r17, r18, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Bitmap encode(String r30, int r31, int r32, Map<EncodeHintType, ?> r33, Bitmap r34, float r35, int r36, int r37, boolean r38) {
        if (r30.isEmpty() == true) goto L110;
        if (r31 < 0) goto L109;
        if (r32 < 0) goto L109;
        ErrorCorrectionLevel r4 = ErrorCorrectionLevel.L;
        if (r33 == null) goto L14;
        EncodeHintType r5 = EncodeHintType.ERROR_CORRECTION;
        if (r33.containsKey(r5) == false) goto L11;
        r4 = ErrorCorrectionLevel.valueOf(r33.get(r5).toString());
    L11:
        EncodeHintType r52 = EncodeHintType.MARGIN;
        if (r33.containsKey(r52) == false) goto L14;
        int r53 = Integer.parseInt(r33.get(r52).toString());
        String r6 = r30;
    L15:
        ByteMatrix r3 = Encoder.encode(r6, r4, r33).getMatrix();
        this.input = r3;
        if (r3 == null) goto L106;
        int r14 = r3.getWidth();
        int r15 = this.input.getHeight();
        int r42 = 0;
    L19:
        if (r42 >= r14) goto L23;
        if (has(r42, 0) == false) goto L23;
        this.sideQuadSize++;
        r42 = r42 + 1;
    L23:
        int r54 = r53 * 2;
        int r43 = r14 + r54;
        int r55 = r54 + r15;
        int r1 = Math.min(Math.max(r31, r43) / r43, Math.max(r32, r55) / r55);
        int r2 = r1 * r14;
        int r44 = r2 + 32;
        if (r34 != null) goto L26;
    L29:
        Bitmap r56 = Bitmap.createBitmap(r44, r44, Bitmap.Config.ARGB_8888);
    L30:
        Canvas r7 = new Canvas(r56);
        r7.drawColor(r36);
        Paint r8 = new Paint(1);
        r8.setColor(r37);
        GradientDrawable r9 = new GradientDrawable();
        r9.setShape(0);
        r9.setCornerRadii(this.radii);
        if (r38 == true) goto L33;
        int r22 = 0;
    L34:
        this.imageBloks = r22;
        if ((r22 % 2) == (r14 % 2)) goto L37;
        this.imageBloks = r22 + 1;
    L37:
        int r23 = this.imageBloks;
        this.imageBlockX = (r14 - r23) / 2;
        int r24 = (r23 * r1) - 24;
        this.imageSize = r24;
        int r25 = (r44 - r24) / 2;
        int r62 = 16;
        if (this.includeSideQuads == false) goto L40;
        r8.setColor(r37);
        Canvas r12 = r7;
        GradientDrawable r39 = r9;
        int r21 = r25;
        Paint r26 = r8;
        Bitmap r20 = r56;
        int r13 = r1;
        int r18 = 0;
        char r19 = 4;
        int r17 = 1;
        drawSideQuadsGradient(r12, r26, r39, this.sideQuadSize, r1, 16, r44, r35, this.radii, r36, r37);
    L42:
        if (Color.alpha(r36) != 0) goto L44;
        int r45 = r17;
    L45:
        float r57 = (r13 / 2.0f) * r35;
        int r82 = 16;
        int r72 = r18;
    L46:
        if (r72 >= r15) goto L100;
        int r92 = r18;
    L48:
        if (r92 >= r14) goto L99;
        boolean r222 = has(r92, r72);
        float[] r122 = this.radii;
        Paint r302 = r26;
        if (r222 == false) goto L64;
        Arrays.fill(r122, r57);
        if (has(r92, r72 - 1) == false) goto L55;
        float[] r123 = this.radii;
        r123[r17] = 0.0f;
        r123[r18] = 0.0f;
        r123[3] = 0.0f;
        r123[2] = 0.0f;
    L55:
        if (has(r92, r72 + 1) == false) goto L58;
        float[] r124 = this.radii;
        r124[7] = 0.0f;
        r124[6] = 0.0f;
        r124[5] = 0.0f;
        r124[r19] = 0.0f;
    L58:
        if (has(r92 - 1, r72) == false) goto L61;
        float[] r125 = this.radii;
        r125[r17] = 0.0f;
        r125[r18] = 0.0f;
        r125[7] = 0.0f;
        r125[6] = 0.0f;
    L61:
        if (has(r92 + 1, r72) == false) goto L63;
        float[] r126 = this.radii;
        r126[3] = 0.0f;
        r126[2] = 0.0f;
        r126[5] = 0.0f;
        r126[r19] = 0.0f;
    L63:
        r39.setColor(r37);
        r39.setBounds(r62, r82, r62 + r13, r82 + r13);
        r39.draw(r12);
        Paint r127 = r302;
        Canvas r27 = r12;
        int r272 = r45;
    L98:
        r92 = r92 + 1;
        r62 = r62 + r13;
        r12 = r27;
        r26 = r127;
        r45 = r272;
        goto L48
    L64:
        Arrays.fill(r122, 0.0f);
        int r28 = r92 - 1;
        int r128 = r72 - 1;
        if (has(r28, r128) == true) goto L67;
    L71:
        Canvas r223 = r12;
        int r16 = r18;
    L72:
        int r312 = r16;
        int r110 = r92 + 1;
        if (has(r110, r128) == true) goto L75;
    L79:
        int r129 = r312;
    L80:
        r272 = r45;
        int r46 = r72 + 1;
        if (has(r28, r46) == false) goto L88;
        if (has(r28, r72) == false) goto L88;
        if (has(r92, r46) == false) goto L88;
        float[] r29 = this.radii;
        r29[7] = r57;
        r29[6] = r57;
        r129 = r17;
    L88:
        if (has(r110, r46) == true) goto L90;
    L94:
        if (r129 == 0) goto L97;
        if (r272 != 0) goto L97;
        int r47 = r62 + r13;
        int r111 = r82 + r13;
        Canvas r303 = r223;
        r303.drawRect(r62, r82, r47, r111, r302);
        r27 = r303;
        r127 = r302;
        r39.setColor(r36);
        r39.setBounds(r62, r82, r47, r111);
        r39.draw(r27);
    L97:
        r127 = r302;
        r27 = r223;
        goto L98
    L90:
        if (has(r110, r72) == false) goto L94;
        if (has(r92, r46) == false) goto L94;
        float[] r112 = this.radii;
        r112[5] = r57;
        r112[r19] = r57;
        r129 = r17;
        goto L94
    L75:
        if (has(r110, r72) == false) goto L79;
        if (has(r92, r128) == false) goto L79;
        float[] r1210 = this.radii;
        r1210[3] = r57;
        r1210[2] = r57;
        r129 = r17;
        goto L80
    L67:
        if (has(r28, r72) == false) goto L71;
        if (has(r92, r128) == false) goto L71;
        r223 = r12;
        float[] r113 = this.radii;
        r113[r17] = r57;
        r113[r18] = r57;
        r16 = r17;
        goto L72
    L99:
        r72 = r72 + 1;
        r82 = r82 + r13;
        r26 = r26;
        r62 = 16;
        goto L46
    L100:
        Canvas r210 = r12;
        if (r38 == false) goto L103;
        Bitmap r310 = null;
        String r114 = AndroidUtilities.readRes(null, R.raw.qr_logo);
        int r0 = this.imageSize;
        Bitmap r02 = SvgHelper.getBitmap(r114, r0, r0, r18);
        float r115 = r21;
        r210.drawBitmap(r02, r115, r115, null);
        r02.recycle();
    L104:
        r210.setBitmap(r310);
        return r20;
    L103:
        r310 = null;
        goto L104
    L44:
        r45 = r18;
        goto L45
    L40:
        r17 = 1;
        r13 = r1;
        r21 = r25;
        r20 = r56;
        r12 = r7;
        r26 = r8;
        r39 = r9;
        r18 = 0;
        r19 = 4;
        goto L42
    L33:
        r22 = Math.round((r2 / 4.65f) / r1);
        goto L34
    L26:
        if (r34.getWidth() != r44) goto L29;
        r56 = r34;
        goto L30
    L106:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    L14:
        r6 = r30;
        r53 = 4;
    L109:
        throw new IllegalArgumentException("Requested dimensions are too small: " + r31 + 'x' + r32);
    L110:
        f$$ExternalSyntheticBUOutline1.m("Found empty contents");
        return null;
    }

    public int getImageSize() {
        return this.imageSize;
    }

    public int getSideSize() {
        return this.sideQuadSize;
    }
}
