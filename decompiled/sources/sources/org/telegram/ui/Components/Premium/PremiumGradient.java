package org.telegram.ui.Components.Premium;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class PremiumGradient {
    private static PremiumGradient instance;
    private final PremiumGradientTools goldGradient;
    public InternalDrawable goldenStarMenuDrawable;
    private int lastStarColor;
    Paint lockedPremiumPaint;
    private final PremiumGradientTools mainGradient;
    private final Paint mainGradientPaint;
    public Drawable premiumStarColoredDrawable;
    public Drawable premiumStarDrawableMini;
    public InternalDrawable premiumStarMenuDrawable;
    public InternalDrawable premiumStarMenuDrawable2;

    public static class InternalDrawable extends BitmapDrawable {
        public int[] colors;
        Drawable originDrawable;

        public InternalDrawable(Drawable r2, Bitmap r3, int[] r4) {
            super(ApplicationLoader.applicationContext.getResources(), r3);
            this.originDrawable = r2;
            int[] r22 = new int[r4.length];
            this.colors = r22;
            System.arraycopy(r4, 0, r22, 0, r4.length);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(int r1, PorterDuff.Mode r2) {
        }

        @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    public static class PremiumGradientTools {
        final int colorKey1;
        final int colorKey2;
        final int colorKey3;
        final int colorKey4;
        final int colorKey5;
        final int[] colors;
        public float cx;
        public float cy;
        public boolean darkColors;
        public boolean exactly;
        final Matrix matrix;
        public final Paint paint;
        private final Theme.ResourcesProvider resourcesProvider;
        Shader shader;
        public float x1;
        public float x2;
        public float y1;
        public float y2;

        /* renamed from: -$$Nest$mchekColors, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12039$$Nest$mchekColors(PremiumGradientTools r0) {
            r0.chekColors();
        }

        public PremiumGradientTools(int r7, int r8, int r9) {
            this(r7, r8, r9, -1, -1);
        }

        public PremiumGradientTools(int r7, int r8, int r9, int r10) {
            this(r7, r8, r9, r10, -1);
        }

        public PremiumGradientTools(int r8, int r9, int r10, int r11, int r12) {
            this(r8, r9, r10, r11, -1, null);
        }

        public PremiumGradientTools(int r3, int r4, int r5, int r6, int r7, Theme.ResourcesProvider r8) {
            this.cx = 0.5f;
            this.cy = 0.5f;
            this.matrix = new Matrix();
            this.paint = new Paint(1);
            this.colors = new int[5];
            this.x1 = 0.0f;
            this.y1 = 1.0f;
            this.x2 = 1.5f;
            this.y2 = 0.0f;
            this.resourcesProvider = r8;
            this.colorKey1 = r3;
            this.colorKey2 = r4;
            this.colorKey3 = r5;
            this.colorKey4 = r6;
            this.colorKey5 = r7;
        }

        private void chekColors() {
            int r1 = getColor(this.colorKey1);
            int r2 = getColor(this.colorKey2);
            int r3 = this.colorKey3;
            if (r3 >= 0) goto L5;
            int r32 = 0;
        L6:
            int r5 = this.colorKey4;
            if (r5 >= 0) goto L9;
            int r52 = 0;
        L10:
            int r6 = this.colorKey5;
            if (r6 >= 0) goto L13;
            int r62 = 0;
        L15:
            if (this.shader == null) goto L28;
            int[] r7 = this.colors;
            if (r7[0] != r1) goto L28;
            if (r7[1] != r2) goto L28;
            if (r7[2] != r32) goto L28;
            if (r7[3] != r52) goto L28;
            if (r7[4] != r62) goto L28;
            return;
        L28:
            int[] r72 = this.colors;
            r72[0] = r1;
            r72[1] = r2;
            r72[2] = r32;
            r72[3] = r52;
            r72[4] = r62;
            if (r32 != 0) goto L31;
            float r13 = this.x1 * 100.0f;
            float r14 = this.y1 * 100.0f;
            float r15 = this.x2 * 100.0f;
            float r16 = this.y2 * 100.0f;
            int[] r12 = this.colors;
            this.shader = new LinearGradient(r13, r14, r15, r16, new int[]{r12[0], r12[1]}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
        L37:
            this.shader.setLocalMatrix(this.matrix);
            this.paint.setShader(this.shader);
            return;
        L31:
            if (r52 != 0) goto L34;
            float r33 = this.x1 * 100.0f;
            float r53 = this.y1 * 100.0f;
            float r63 = this.x2 * 100.0f;
            float r73 = this.y2 * 100.0f;
            int[] r22 = this.colors;
            this.shader = new LinearGradient(r33, r53, r63, r73, new int[]{r22[0], r22[1], r22[2]}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP);
            goto L37
        L34:
            if (r62 != 0) goto L36;
            float r34 = this.x1 * 100.0f;
            float r54 = this.y1 * 100.0f;
            float r64 = this.x2 * 100.0f;
            float r74 = this.y2 * 100.0f;
            int[] r17 = this.colors;
            this.shader = new LinearGradient(r34, r54, r64, r74, new int[]{r17[0], r17[1], r17[2], r17[3]}, new float[]{0.0f, 0.5f, 0.78f, 1.0f}, Shader.TileMode.CLAMP);
            goto L37
        L36:
            float r23 = this.x1 * 100.0f;
            float r55 = this.y1 * 100.0f;
            float r65 = this.x2 * 100.0f;
            float r75 = this.y2 * 100.0f;
            int[] r18 = this.colors;
            this.shader = new LinearGradient(r23, r55, r65, r75, new int[]{r18[0], r18[1], r18[2], r18[3], r18[4]}, new float[]{0.0f, 0.425f, 0.655f, 0.78f, 1.0f}, Shader.TileMode.CLAMP);
            goto L37
        L13:
            r62 = getColor(r6);
            goto L15
        L9:
            r52 = getColor(r5);
            goto L10
        L5:
            r32 = getColor(r3);
            goto L6
        }

        private int getColor(int r3) {
            int r32 = getThemeColorByKey(r3);
            if (this.darkColors == true) goto L5;
            return r32;
        L5:
            return Color.argb((int) Color.alpha(r32), (int) (Color.red(r32) - 15), (int) (Color.green(r32) - 15), Color.blue(r32) - 15);
        }

        public int getThemeColorByKey(int r1) {
            return Theme.getColor(r1, this.resourcesProvider);
        }

        public void gradientMatrix(int r3, int r4, int r5, int r6, float r7, float r8) {
            chekColors();
            int r62 = r6 - r4;
            if (this.exactly == false) goto L5;
            this.matrix.reset();
            this.matrix.postScale((r5 - r3) / 100.0f, r62 / 100.0f, this.cx * 100.0f, this.cy * 100.0f);
            this.matrix.postTranslate(r7, r8);
        L6:
            Shader r32 = this.shader;
            if (r32 == null) goto L10;
            r32.setLocalMatrix(this.matrix);
            return;
        L10:
            return;
        L5:
            chekColors();
            this.matrix.reset();
            Matrix r52 = this.matrix;
            r52.postScale((r5 - r3) / 100.0f, (r62 + r62) / 100.0f, 75.0f, 50.0f);
            this.matrix.postTranslate(r7, (-r6) + r8);
            goto L6
        }

        public void gradientMatrix(Rect r8) {
            gradientMatrix(r8.left, r8.top, r8.right, r8.bottom, 0.0f, 0.0f);
        }

        public void gradientMatrix(RectF r9) {
            gradientMatrix((int) r9.left, (int) r9.top, (int) r9.right, (int) r9.bottom, 0.0f, 0.0f);
        }

        public void gradientMatrixLinear(float r4, float r5) {
            chekColors();
            this.matrix.reset();
            this.matrix.postScale(1.0f, r4 / 100.0f, 0.0f, 0.0f);
            this.matrix.postTranslate(0.0f, r5);
            this.shader.setLocalMatrix(this.matrix);
        }
    }

    private PremiumGradient() {
        PremiumGradientTools r0 = new PremiumGradientTools(Theme.key_premiumGradient1, Theme.key_premiumGradient2, Theme.key_premiumGradient3, Theme.key_premiumGradient4);
        this.mainGradient = r0;
        PremiumGradientTools r1 = new PremiumGradientTools(Theme.key_starsGradient1, Theme.key_starsGradient2, -1);
        this.goldGradient = r1;
        this.mainGradientPaint = r0.paint;
        this.premiumStarDrawableMini = ContextCompat.getDrawable(ApplicationLoader.applicationContext, R.drawable.msg_premium_liststar).mutate();
        this.premiumStarMenuDrawable = createGradientDrawable(ContextCompat.getDrawable(ApplicationLoader.applicationContext, R.drawable.msg_settings_premium));
        this.goldenStarMenuDrawable = createGradientDrawable(ContextCompat.getDrawable(ApplicationLoader.applicationContext, R.drawable.msg_settings_premium), r1);
        this.premiumStarMenuDrawable2 = createGradientDrawable(ContextCompat.getDrawable(ApplicationLoader.applicationContext, R.drawable.msg_premium_normal));
        this.premiumStarColoredDrawable = ContextCompat.getDrawable(ApplicationLoader.applicationContext, R.drawable.msg_premium_liststar).mutate();
        PremiumGradientTools.m12039$$Nest$mchekColors(r0);
        checkIconColors();
    }

    private InternalDrawable checkColors(InternalDrawable r5) {
        int[] r0 = this.mainGradient.colors;
        int r2 = r0[0];
        int[] r3 = r5.colors;
        if (r2 != r3[0]) goto L13;
        if (r0[1] != r3[1]) goto L13;
        if (r0[2] != r3[2]) goto L13;
        if (r0[3] != r3[3]) goto L13;
        return r5;
    L13:
        return createGradientDrawable(r5.originDrawable);
    }

    public static PremiumGradient getInstance() {
        if (instance != null) goto L6;
        instance = new PremiumGradient();
    L6:
        return instance;
    }

    public void checkIconColors() {
        int r0 = Theme.key_chats_verifiedBackground;
        if (Theme.getColor(r0) == this.lastStarColor) goto L5;
        this.lastStarColor = Theme.getColor(r0);
        this.premiumStarDrawableMini.setColorFilter(new PorterDuffColorFilter(this.lastStarColor, PorterDuff.Mode.MULTIPLY));
    L5:
        this.premiumStarMenuDrawable = checkColors(this.premiumStarMenuDrawable);
        this.premiumStarMenuDrawable2 = checkColors(this.premiumStarMenuDrawable2);
    }

    public InternalDrawable createGradientDrawable(Drawable r2) {
        return createGradientDrawable(r2, this.mainGradient);
    }

    public InternalDrawable createGradientDrawable(Drawable r17, PremiumGradientTools r18) {
        if (r17 != null) goto L5;
        return null;
    L5:
        int r4 = r17.getIntrinsicWidth();
        int r5 = r17.getMinimumHeight();
        Bitmap r9 = Bitmap.createBitmap(r4, r5, Bitmap.Config.ARGB_8888);
        Canvas r10 = new Canvas(r9);
        r17.setBounds(0, 0, r4, r5);
        r17.draw(r10);
        r18.paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        r18.gradientMatrix(0, 0, r4, r5, -r4, 0.0f);
        r10.drawRect(0.0f, 0.0f, r4, r5, r18.paint);
        r18.paint.setXfermode(null);
        return new InternalDrawable(r17, r9, r18.colors);
    }

    public Paint getMainGradientPaint() {
        if (MessagesController.getInstance(UserConfig.selectedAccount).premiumFeaturesBlocked() == false) goto L10;
        if (this.lockedPremiumPaint != null) goto L7;
        this.lockedPremiumPaint = new Paint(1);
    L7:
        this.lockedPremiumPaint.setColor(Theme.getColor(Theme.key_featuredStickers_addButton));
        return this.lockedPremiumPaint;
    L10:
        return this.mainGradientPaint;
    }

    public Paint getPremiumLocakedPaint() {
        if (this.lockedPremiumPaint != null) goto L5;
        this.lockedPremiumPaint = new Paint(1);
    L5:
        this.lockedPremiumPaint.setColor(Theme.getColor(Theme.key_featuredStickers_addButton));
        return this.lockedPremiumPaint;
    }

    public void updateMainGradientMatrix(int r1, int r2, int r3, int r4, float r5, float r6) {
        this.mainGradient.gradientMatrix(r1, r2, r3, r4, r5, r6);
    }
}
