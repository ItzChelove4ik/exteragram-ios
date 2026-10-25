package com.exteragram.messenger.appicons;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.LruCache;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes4.dex */
public abstract class AppIconPreviewLoader {
    private static final Map<String, Integer> accents = null;
    private static LruCache<String, Bitmap> cache;
    private static final List<Integer> knownSizes = null;
    private static final Paint paint = null;
    private static final Map<String, List<Callback>> pending = null;

    /* renamed from: com.exteragram.messenger.appicons.AppIconPreviewLoader$1, reason: invalid class name */
    public class AnonymousClass1 extends LruCache<String, Bitmap> {
        public AnonymousClass1(int r1) {
            super(r1);
        }

        @Override // android.util.LruCache
        public /* bridge */ /* synthetic */ int sizeOf(String r1, Bitmap r2) {
            return sizeOf2(r1, r2);
        }

        /* renamed from: sizeOf, reason: avoid collision after fix types in other method */
        public int sizeOf2(String r1, Bitmap r2) {
            return r2.getByteCount();
        }
    }

    public interface Callback {
        void onPreviewReady(AppIcon r1, int r2, Bitmap r3);
    }

    public static /* synthetic */ void $r8$lambda$5pAaOXpJhOh8Ffl7ErwtmWPqaac(Bitmap r2, String r3, int r4, AppIcon r5, int r6) {
        if (r2 == null) goto L4;
        getCache().put(r3, r2);
    L4:
        if (r4 == 0) goto L8;
        Map<String, Integer> r0 = accents;
        if (r0.containsKey(r5.id) == true) goto L8;
        r0.put(r5.id, Integer.valueOf(r4));
    L8:
        List<Callback> r32 = pending.remove(r3);
        if (r32 == null) goto L16;
        if (r2 == null) goto L18;
        Iterator<Callback> r33 = r32.iterator();
    L14:
        if (r33.hasNext() == false) goto L19;
        r33.next().onPreviewReady(r5, r6, r2);
        goto L14
    L19:
        return;
    L18:
        return;
    }

    public static /* synthetic */ void $r8$lambda$xCNjtrhQGHVyqdjOo4Qfmhh5oYI(AppIcon r6, int r7, boolean r8, String r9) {
        Bitmap r1 = render(r6, r7);
        int r0 = 0;
        if (r1 == null) goto L10;
        if (r8 == false) goto L10;
        if (r6.isBackgroundColor() == false) goto L9;
        r0 = getBackgroundColor(r6);
    L9:
        r0 = extractAccent(r1, r0);
    L10:
        AndroidUtilities.runOnUIThread(new AppIconPreviewLoader$$ExternalSyntheticLambda1(r1, r9, r0, r6, r7));
    }

    static {
        paint = new Paint(3);
        pending = new HashMap();
        knownSizes = new ArrayList();
        accents = new HashMap();
    }

    private static int clampTone(float[] r9, float r10, float r11) {
        double[] r0 = new double[3];
        ColorUtils.colorToLAB(ColorUtils.HSLToColor(r9), r0);
        double r2 = r0[0];
        double r4 = r10;
        if (r2 < r4) goto L9;
        if (r2 > r11) goto L9;
        return ColorUtils.HSLToColor(r9);
    L9:
        if (r2 < r4) goto L12;
        r10 = r11;
    L12:
        float r112 = 0.0f;
        float r22 = 1.0f;
        int r3 = 0;
    L14:
        if (r3 >= 16) goto L20;
        r9[2] = (r112 + r22) / 2.0f;
        ColorUtils.colorToLAB(ColorUtils.HSLToColor(r9), r0);
        if (r0[0] >= r10) goto L18;
        r112 = r9[2];
    L19:
        r3 = r3 + 1;
        goto L14
    L18:
        r22 = r9[2];
        goto L19
    L20:
        r9[2] = (r112 + r22) / 2.0f;
        return ColorUtils.HSLToColor(r9);
    }

    private static void draw(Canvas r9, int r10, Rect r11) {
        if (r10 == 0) goto L26;
        Context r0 = ApplicationLoader.applicationContext;
        Resources r1 = r0.getResources();
        BitmapFactory.Options r2 = new BitmapFactory.Options();
        int r3 = 1;
        r2.inJustDecodeBounds = true;
        r2.inScaled = false;
        BitmapFactory.decodeResource(r1, r10, r2);
        if (r2.outWidth > 0) goto L7;
    L15:
        Drawable r102 = ContextCompat.getDrawable(r0, r10);
        if ((r102 instanceof BitmapDrawable) == false) goto L21;
        BitmapDrawable r02 = (BitmapDrawable) r102;
        if (r02.getBitmap() == null) goto L21;
        r9.drawBitmap(r02.getBitmap(), null, r11, paint);
        return;
    L21:
        if (r102 == null) goto L25;
        r102.setBounds(r11);
        r102.draw(r9);
        return;
    L25:
        return;
    L7:
        if (r2.outHeight <= 0) goto L15;
    L8:
        int r7 = r3 * 2;
        if ((r2.outWidth / r7) < r11.width()) goto L11;
        r3 = r7;
        goto L8
    L11:
        r2.inJustDecodeBounds = false;
        r2.inSampleSize = r3;
        r2.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap r12 = BitmapFactory.decodeResource(r1, r10, r2);
        if (r12 == null) goto L15;
        r9.drawBitmap(r12, null, r11, paint);
        return;
    }

    private static int extractAccent(Bitmap r23, int r24) {
        boolean r1 = true;
        float r0 = (r23.getWidth() - 1) / 23.0f;
        float r3 = (r23.getHeight() - 1) / 23.0f;
        float[] r2 = new float[3];
        float[] r5 = new float[12];
        float[] r6 = new float[12];
        float[] r7 = new float[12];
        float[] r8 = new float[12];
        int r11 = 0;
        float r12 = 0.0f;
        float r13 = 0.0f;
        float r14 = 0.0f;
    L3:
        int r15 = 24;
        if (r11 >= 24) goto L19;
        boolean r17 = r1;
        int r16 = 0;
    L6:
        if (r16 >= r15) goto L18;
        int r9 = r23.getPixel((int) (r11 * r0), (int) (r16 * r3));
        if (Color.alpha(r9) < 200) goto L17;
        if (r9 == r24) goto L17;
        ColorUtils.colorToHSL(r9, r2);
        if (isNeutral(r2) == false) goto L15;
        r12 = r12 + 1.0f;
        r14 = r14 + r2[2];
        goto L17
    L15:
        int r92 = Math.min(11, (int) ((r2[0] / 360.0f) * 12.0f));
        float r10 = r2[r17 ? 1 : 0] * (1.0f - Math.abs((r2[2] * 2.0f) - 1.0f));
        r5[r92] = r5[r92] + r10;
        r6[r92] = r6[r92] + (r2[0] * r10);
        r7[r92] = r7[r92] + (r2[r17 ? 1 : 0] * r10);
        r8[r92] = r8[r92] + (r2[2] * r10);
        r13 = r13 + 1.0f;
    L17:
        r16 = r16 + 1;
        r15 = 24;
        goto L6
    L18:
        r11 = r11 + 1;
        r1 = r17 ? 1 : 0;
        goto L3
    L19:
        boolean r172 = r1;
        int r32 = -1;
        int r18 = 0;
    L20:
        if (r18 >= 12) goto L26;
        if (r32 != (-1)) goto L23;
    L24:
        r32 = r18;
    L25:
        r18 = r18 + 1;
        goto L20
    L23:
        if (r5[r18] <= r5[r32]) goto L25;
    L26:
        if (r32 == (-1)) goto L35;
        float r02 = r5[r32];
        if (r02 <= 0.0f) goto L35;
        if (r13 < (0.15f * r12)) goto L35;
        r2[0] = r6[r32] / r02;
        r2[r172 ? 1 : 0] = r7[r32] / r5[r32];
        r2[2] = r8[r32] / r5[r32];
        return ColorUtils.HSLToColor(r2);
    L35:
        if (r12 > 0.0f) goto L37;
        return 0;
    L37:
        r2[0] = 0.0f;
        r2[r172 ? 1 : 0] = 0.0f;
        r2[2] = r14 / r12;
        return ColorUtils.HSLToColor(r2);
    }

    public static int getAccent(AppIcon r5) {
        int r0 = r5.color;
        if (r0 == 0) goto L5;
        return r0;
    L5:
        Map<String, Integer> r02 = accents;
        Integer r1 = r02.get(r5.id);
        if (r1 == null) goto L10;
        return r1.intValue();
    L10:
        if (r5.isBackgroundColor() == false) goto L16;
        int r12 = getBackgroundColor(r5);
        Integer r3 = Integer.valueOf(r12);
        float[] r4 = new float[3];
        ColorUtils.colorToHSL(r12, r4);
        if (isNeutral(r4) == false) goto L14;
        return 0;
    L14:
        r02.put(r5.id, r3);
        return r12;
    L16:
        return 0;
    }

    public static int getAccentTextColor(AppIcon r2, int r3, Theme.ResourcesProvider r4) {
        if (r2 != null) goto L4;
        int r22 = 0;
    L5:
        if (r22 == 0) goto L15;
        float[] r0 = new float[3];
        ColorUtils.colorToHSL(r22, r0);
        if (r0[1] < 0.15f) goto L15;
        if (Theme.isCurrentThemeDark() == false) goto L12;
        r0[2] = Math.max(r0[2], 0.72f);
        return clampTone(r0, 70.0f, 100.0f);
    L12:
        r0[2] = Math.min(r0[2], 0.42f);
        return clampTone(r0, 0.0f, 42.0f);
    L15:
        return Theme.getColor(r3, r4);
    L4:
        r22 = getAccent(r2);
        goto L5
    }

    public static int getAccentTint(AppIcon r3) {
        if (r3 != null) goto L5;
        int r32 = 0;
    L6:
        if (r32 != 0) goto L8;
        return 0;
    L8:
        float[] r0 = new float[3];
        ColorUtils.colorToHSL(r32, r0);
        r0[1] = Math.min(r0[1], 0.6f);
        if (Theme.isCurrentThemeDark() == false) goto L12;
        r0[2] = 0.22f;
        return clampTone(r0, 20.0f, 100.0f);
    L12:
        r0[2] = 0.86f;
        return clampTone(r0, 0.0f, 88.0f);
    L5:
        r32 = getAccent(r3);
        goto L6
    }

    public static Bitmap getAnyCached(AppIcon r5) {
        Bitmap r0 = null;
        int r1 = 0;
    L3:
        List<Integer> r2 = knownSizes;
        if (r1 >= r2.size()) goto L12;
        Bitmap r22 = getCache().get(key(r5, r2.get(r1).intValue()));
        if (r22 == null) goto L11;
        if (r0 != null) goto L9;
    L10:
        r0 = r22;
        goto L11
    L9:
        if (r22.getWidth() > r0.getWidth()) goto L10;
    L11:
        r1 = r1 + 1;
        goto L3
    L12:
        return r0;
    }

    private static int getBackgroundColor(AppIcon r1) {
        return ContextCompat.getColor(ApplicationLoader.applicationContext, r1.getBackground());
    L4:
        th = move-exception;
        FileLog.e(th);
        return 0;
    }

    private static LruCache<String, Bitmap> getCache() {
        if (cache != null) goto L6;
        cache = new AnonymousClass1((int) Math.min(12582912, Runtime.getRuntime().maxMemory() / 16));
    L6:
        return cache;
    }

    public static Bitmap getCached(AppIcon r1, int r2) {
        return getCache().get(key(r1, r2));
    }

    private static boolean isNeutral(float[] r3) {
        if (r3[1] < 0.15f) goto L11;
        float r32 = r3[2];
        if (r32 < 0.06f) goto L11;
        if (r32 > 0.94f) goto L11;
        return false;
    L11:
        return true;
    }

    private static String key(AppIcon r1, int r2) {
        return r1.id + "@" + r2;
    }

    public static void load(AppIcon r3, int r4, Callback r5) {
        if (r4 > 0) goto L4;
        return;
    L4:
        List<Integer> r0 = knownSizes;
        if (r0.contains(Integer.valueOf(r4)) == true) goto L7;
        r0.add(Integer.valueOf(r4));
    L7:
        String r02 = key(r3, r4);
        Bitmap r1 = getCache().get(r02);
        if (r1 == null) goto L11;
        r5.onPreviewReady(r3, r4, r1);
        return;
    L11:
        Map<String, List<Callback>> r12 = pending;
        List<Callback> r2 = r12.get(r02);
        if (r2 == null) goto L15;
        r2.add(r5);
        return;
    L15:
        ArrayList r22 = new ArrayList();
        r22.add(r5);
        r12.put(r02, r22);
        if (accents.containsKey(r3.id) == false) goto L18;
    L20:
        boolean r52 = false;
    L21:
        Utilities.globalQueue.postRunnable(new AppIconPreviewLoader$$ExternalSyntheticLambda0(r3, r4, r52, r02));
        return;
    L18:
        if (getAccent(r3) != 0) goto L20;
        r52 = true;
        goto L21
    }

    private static Bitmap render(AppIcon r5, int r6) {
        Bitmap r0 = Bitmap.createBitmap(r6, r6, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L8
        Canvas r1 = new Canvas(r0);     // Catch: Throwable -> L8
        int r2 = r6 / 4;     // Catch: Throwable -> L8
        int r4 = -r2;
        int r62 = r6 + r2;     // Catch: Throwable -> L8
        Rect r3 = new Rect(r4, r4, r62, r62);     // Catch: Throwable -> L8
        if (r5.isBackgroundColor() == false) goto L5;
        r1.drawColor(ContextCompat.getColor(ApplicationLoader.applicationContext, r5.getBackground()));     // Catch: Throwable -> L8
    L6:
        draw(r1, r5.getForeground(), r3);     // Catch: Throwable -> L8
        return r0;
    L5:
        draw(r1, r5.getBackground(), r3);     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        FileLog.e(th);
        return null;
    }

    public static void trimAbove(int r3) {
        Iterator<Map.Entry<String, Bitmap>> r0 = getCache().snapshot().entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        Map.Entry<String, Bitmap> r1 = r0.next();
        if (r1.getValue().getWidth() < r3) goto L4;
        getCache().remove(r1.getKey());
        goto L4
    }
}
