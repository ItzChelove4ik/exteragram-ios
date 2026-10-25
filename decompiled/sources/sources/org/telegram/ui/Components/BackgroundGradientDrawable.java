package org.telegram.ui.Components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.Utilities;

/* loaded from: classes3.dex */
public class BackgroundGradientDrawable extends GradientDrawable {
    private final Paint bitmapPaint;
    private final ArrayMap<IntSize, Bitmap> bitmaps;
    private final int[] colors;
    private final ArrayMap<View, Disposable> disposables;
    private boolean disposed;
    private final List<Runnable[]> ditheringRunnables;
    private final ArrayMap<IntSize, Boolean> isForExactBounds;

    /* renamed from: org.telegram.ui.Components.BackgroundGradientDrawable$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends ListenerAdapter {
        final /* synthetic */ BackgroundGradientDrawable this$0;
        final /* synthetic */ View val$ownerView;

        public AnonymousClass1(BackgroundGradientDrawable r1, View r2) {
            this.this$0 = r1;
            this.val$ownerView = r2;
        }

        @Override // org.telegram.ui.Components.BackgroundGradientDrawable.ListenerAdapter, org.telegram.ui.Components.BackgroundGradientDrawable.Listener
        public void onAllSizesReady() {
            this.val$ownerView.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.BackgroundGradientDrawable$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$drawable$GradientDrawable$Orientation = null;

        static {
            int[] r0 = new int[GradientDrawable.Orientation.values().length];
            $SwitchMap$android$graphics$drawable$GradientDrawable$Orientation = r0;
            r0[GradientDrawable.Orientation.TOP_BOTTOM.ordinal()] = 1;     // Catch: NoSuchFieldError -> L11
        L18:
            $SwitchMap$android$graphics$drawable$GradientDrawable$Orientation[GradientDrawable.Orientation.TR_BL.ordinal()] = 2;     // Catch: NoSuchFieldError -> L12
        L22:
            $SwitchMap$android$graphics$drawable$GradientDrawable$Orientation[GradientDrawable.Orientation.RIGHT_LEFT.ordinal()] = 3;     // Catch: NoSuchFieldError -> L13
        L30:
            $SwitchMap$android$graphics$drawable$GradientDrawable$Orientation[GradientDrawable.Orientation.BR_TL.ordinal()] = 4;     // Catch: NoSuchFieldError -> L14
        L20:
            $SwitchMap$android$graphics$drawable$GradientDrawable$Orientation[GradientDrawable.Orientation.BOTTOM_TOP.ordinal()] = 5;     // Catch: NoSuchFieldError -> L15
        L24:
            $SwitchMap$android$graphics$drawable$GradientDrawable$Orientation[GradientDrawable.Orientation.BL_TR.ordinal()] = 6;     // Catch: NoSuchFieldError -> L16
        L26:
            $SwitchMap$android$graphics$drawable$GradientDrawable$Orientation[GradientDrawable.Orientation.LEFT_RIGHT.ordinal()] = 7;     // Catch: NoSuchFieldError -> L17
            return;
        }
    }

    /* loaded from: classes7.dex */
    public interface Disposable {
        void dispose();
    }

    /* loaded from: classes7.dex */
    public interface Listener {
        void onAllSizesReady();

        void onSizeReady(int r1, int r2);
    }

    /* loaded from: classes7.dex */
    public static class ListenerAdapter implements Listener {
        public ListenerAdapter() {
        }

        @Override // org.telegram.ui.Components.BackgroundGradientDrawable.Listener
        public void onAllSizesReady() {
        }

        @Override // org.telegram.ui.Components.BackgroundGradientDrawable.Listener
        public void onSizeReady(int r1, int r2) {
        }
    }

    /* loaded from: classes7.dex */
    public static class Sizes {
        private final IntSize[] arr;

        public enum Orientation extends Enum<Orientation> {
            private static final /* synthetic */ Orientation[] $VALUES = null;
            public static final Orientation BOTH = null;
            public static final Orientation LANDSCAPE = null;
            public static final Orientation PORTRAIT = null;

            private static /* synthetic */ Orientation[] $values() {
                return new Orientation[]{PORTRAIT, LANDSCAPE, BOTH};
            }

            static {
                PORTRAIT = new Orientation("PORTRAIT", 0);
                LANDSCAPE = new Orientation("LANDSCAPE", 1);
                BOTH = new Orientation("BOTH", 2);
                $VALUES = $values();
            }

            Orientation(String r1, int r2) {
            }

            public static Orientation valueOf(String r1) {
                return (Orientation) Enum.valueOf(Orientation.class, r1);
            }

            public static Orientation[] values() {
                return (Orientation[]) $VALUES.clone();
            }
        }

        /* renamed from: -$$Nest$fgetarr, reason: not valid java name */
        public static /* bridge */ /* synthetic */ IntSize[] m8988$$Nest$fgetarr(Sizes r0) {
            return r0.arr;
        }

        private Sizes(int r4, int r5, int... r6) {
            IntSize[] r0 = new IntSize[(r6.length / 2) + 1];
            this.arr = r0;
            IntSize r1 = new IntSize(r4, r5);
            int r42 = 0;
            r0[0] = r1;
        L4:
            if (r42 >= (r6.length / 2)) goto L6;
            int r02 = r42 + 1;
            int r43 = r42 * 2;
            this.arr[r02] = new IntSize(r6[r43], r6[r43 + 1]);
            r42 = r02;
            goto L4
        }

        public static Sizes of(int r1, int r2, int... r3) {
            return new Sizes(r1, r2, r3);
        }

        public static Sizes ofDeviceScreen() {
            return ofDeviceScreen(0.5f);
        }

        public static Sizes ofDeviceScreen(float r1) {
            return ofDeviceScreen(r1, Orientation.BOTH);
        }

        public static Sizes ofDeviceScreen(float r4, Orientation r5) {
            Point r0 = AndroidUtilities.displaySize;
            int r1 = (int) (r0.x * r4);
            int r42 = (int) (r0.y * r4);
            if (r1 != r42) goto L7;
            return of(r1, r42, new int[0]);
        L7:
            if (r5 == Orientation.BOTH) goto L9;
            boolean r3 = true;
            if (r5 != Orientation.PORTRAIT) goto L13;
            boolean r52 = true;
        L14:
            if (r1 < r42) goto L17;
            r3 = false;
        L17:
            if (r52 != r3) goto L21;
            return of(r1, r42, new int[0]);
        L21:
            return of(r42, r1, new int[0]);
        L13:
            r52 = false;
            goto L14
        L9:
            return of(r1, r42, new int[]{r42, r1});
        }

        public static Sizes ofDeviceScreen(Orientation r1) {
            return ofDeviceScreen(0.5f, r1);
        }
    }

    public static /* synthetic */ void $r8$lambda$A9sxZUUHmHLjPWPbRi62NVzSiC0(BackgroundGradientDrawable r0, Runnable[] r1, Bitmap r2, IntSize r3, int r4, Listener[] r5) {
        r0.lambda$startDitheringInternal$1(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$YKvrYSy5yEZv0AaSd9RKbxZsNaI(BackgroundGradientDrawable r0, View r1, Disposable r2) {
        r0.lambda$drawExactBoundsSize$0(r1, r2);
    }

    /* renamed from: $r8$lambda$iEgbayAuFtM-_eTjPCHDo44idEQ, reason: not valid java name */
    public static /* synthetic */ void m8986$r8$lambda$iEgbayAuFtM_eTjPCHDo44idEQ(BackgroundGradientDrawable r0, IntSize r1, Runnable[] r2, int r3, Listener[] r4) {
        r0.lambda$startDitheringInternal$2(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$ojavXGr-v09lzoZrGRsLjmuJwg0, reason: not valid java name */
    public static /* synthetic */ void m8987$r8$lambda$ojavXGrv09lzoZrGRsLjmuJwg0(BackgroundGradientDrawable r0, Listener[] r1, Runnable[] r2, IntSize[] r3) {
        r0.lambda$startDitheringInternal$3(r1, r2, r3);
    }

    public BackgroundGradientDrawable(GradientDrawable.Orientation r3, int[] r4) {
        super(r3, r4);
        this.bitmaps = new ArrayMap();
        this.isForExactBounds = new ArrayMap();
        this.disposables = new ArrayMap();
        this.ditheringRunnables = new ArrayList();
        Paint r32 = new Paint(1);
        this.bitmapPaint = r32;
        this.disposed = false;
        setDither(true);
        this.colors = r4;
        r32.setDither(true);
    }

    private static Bitmap createDitheredGradientBitmap(GradientDrawable.Orientation r7, int[] r8, int r9, int r10) {
        Rect r72 = getGradientPoints(r7, r9, r10);
        Bitmap r1 = Bitmap.createBitmap(r9, r10, Bitmap.Config.ARGB_8888);
        Utilities.drawDitheredGradient(r1, r8, r72.left, r72.top, r72.right, r72.bottom);
        return r1;
    }

    public static BitmapDrawable createDitheredGradientBitmapDrawable(int r0, int[] r1, int r2, int r3) {
        return createDitheredGradientBitmapDrawable(getGradientOrientation(r0), r1, r2, r3);
    }

    public static BitmapDrawable createDitheredGradientBitmapDrawable(GradientDrawable.Orientation r2, int[] r3, int r4, int r5) {
        return new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), createDitheredGradientBitmap(r2, r3, r4, r5));
    }

    private Bitmap findBestBitmapForSize(int r12, int r13) {
        int r0 = this.bitmaps.size();
        Bitmap r1 = null;
        float r2 = Float.MAX_VALUE;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L14;
        IntSize r4 = this.bitmaps.keyAt(r3);
        float r5 = (float) Math.sqrt(Math.pow(r12 - r4.width, 2.0d) + Math.pow(r13 - r4.height, 2.0d));
        if (r5 >= r2) goto L13;
        Bitmap r6 = this.bitmaps.valueAt(r3);
        if (r6 == null) goto L13;
        Boolean r42 = this.isForExactBounds.get(r4);
        if (r42 != null) goto L11;
    L12:
        r2 = r5;
        r1 = r6;
        goto L13
    L11:
        if (r42.booleanValue() == false) goto L12;
    L13:
        r3 = r3 + 1;
        goto L3
    L14:
        return r1;
    }

    public static GradientDrawable.Orientation getGradientOrientation(int r1) {
        if (r1 == 0) goto L30;
        if (r1 == 90) goto L28;
        if (r1 == 135) goto L26;
        if (r1 == 180) goto L24;
        if (r1 == 225) goto L22;
        if (r1 == 270) goto L20;
        if (r1 == 315) goto L18;
        return GradientDrawable.Orientation.BL_TR;
    L18:
        return GradientDrawable.Orientation.BR_TL;
    L20:
        return GradientDrawable.Orientation.RIGHT_LEFT;
    L22:
        return GradientDrawable.Orientation.TR_BL;
    L24:
        return GradientDrawable.Orientation.TOP_BOTTOM;
    L26:
        return GradientDrawable.Orientation.TL_BR;
    L28:
        return GradientDrawable.Orientation.LEFT_RIGHT;
    L30:
        return GradientDrawable.Orientation.BOTTOM_TOP;
    }

    public static Rect getGradientPoints(int r0, int r1, int r2) {
        return getGradientPoints(getGradientOrientation(r0), r1, r2);
    }

    public static Rect getGradientPoints(GradientDrawable.Orientation r2, int r3, int r4) {
        Rect r0 = new Rect();
        switch(AnonymousClass2.$SwitchMap$android$graphics$drawable$GradientDrawable$Orientation[r2.ordinal()]) {
            case 1: goto L18;
            case 2: goto L16;
            case 3: goto L14;
            case 4: goto L12;
            case 5: goto L10;
            case 6: goto L8;
            case 7: goto L6;
            default: goto L4;
        };
    L4:
        r0.left = 0;
        r0.top = 0;
        r0.right = r3;
        r0.bottom = r4;
        return r0;
    L6:
        r0.left = 0;
        int r42 = r4 / 2;
        r0.top = r42;
        r0.right = r3;
        r0.bottom = r42;
        return r0;
    L8:
        r0.left = 0;
        r0.top = r4;
        r0.right = r3;
        r0.bottom = 0;
        return r0;
    L10:
        int r32 = r3 / 2;
        r0.left = r32;
        r0.top = r4;
        r0.right = r32;
        r0.bottom = 0;
        return r0;
    L12:
        r0.left = r3;
        r0.top = r4;
        r0.right = 0;
        r0.bottom = 0;
        return r0;
    L14:
        r0.left = r3;
        int r43 = r4 / 2;
        r0.top = r43;
        r0.right = 0;
        r0.bottom = r43;
        return r0;
    L16:
        r0.left = r3;
        r0.top = 0;
        r0.right = 0;
        r0.bottom = r4;
        return r0;
    L18:
        int r33 = r3 / 2;
        r0.left = r33;
        r0.top = 0;
        r0.right = r33;
        r0.bottom = r4;
        return r0;
    }

    private /* synthetic */ void lambda$drawExactBoundsSize$0(View r1, Disposable r2) {
        this.disposables.remove(r1);
        r2.dispose();
    }

    private /* synthetic */ void lambda$startDitheringInternal$1(Runnable[] r4, Bitmap r5, IntSize r6, int r7, Listener[] r8) {
        if (this.ditheringRunnables.contains(r4) == true) goto L7;
        if (r5 == null) goto L31;
        r5.recycle();
        return;
    L31:
        return;
    L7:
        ArrayMap<IntSize, Bitmap> r0 = this.bitmaps;
        if (r5 == null) goto L10;
        r0.put(r6, r5);
    L11:
        r4[r7] = null;
        boolean r02 = true;
        if (r4.length <= 1) goto L20;
        int r72 = 0;
    L15:
        if (r72 >= r4.length) goto L20;
        if (r4[r72] != null) goto L21;
        r72 = r72 + 1;
    L21:
        if (r02 == true) goto L23;
        this.ditheringRunnables.remove(r4);
    L23:
        Listener r3 = r8[0];
        if (r3 == null) goto L32;
        r3.onSizeReady(r6.width, r6.height);
        if (r02 == true) goto L33;
        r8[0].onAllSizesReady();
        r8[0] = null;
        return;
    L33:
        return;
    L32:
        return;
    L20:
        r02 = false;
        goto L21
    L10:
        r0.remove(r6);
        this.isForExactBounds.remove(r6);
        goto L11
    }

    private /* synthetic */ void lambda$startDitheringInternal$2(IntSize r9, Runnable[] r10, int r11, Listener[] r12) {
        AndroidUtilities.runOnUIThread(new BackgroundGradientDrawable$$ExternalSyntheticLambda3(this, r10, createDitheredGradientBitmap(getOrientation(), this.colors, r9.width, r9.height), r9, r11, r12));
        return;
    L5:
        th = move-exception;
        AndroidUtilities.runOnUIThread(new BackgroundGradientDrawable$$ExternalSyntheticLambda3(this, r10, null, r9, r11, r12));
        throw th;
    }

    private /* synthetic */ void lambda$startDitheringInternal$3(Listener[] r3, Runnable[] r4, IntSize[] r5) {
        int r1 = 0;
        r3[0] = null;
        if (this.ditheringRunnables.contains(r4) == false) goto L6;
        Utilities.globalQueue.cancelRunnables(r4);
        this.ditheringRunnables.remove(r4);
    L6:
        if (r1 >= r5.length) goto L11;
        IntSize r32 = r5[r1];
        Bitmap r42 = this.bitmaps.remove(r32);
        this.isForExactBounds.remove(r32);
        if (r42 == null) goto L10;
        r42.recycle();
    L10:
        r1 = r1 + 1;
        goto L6
    }

    private Disposable startDitheringInternal(IntSize[] r8, Listener r9, long r10) {
        if (r8.length != 0) goto L6;
        return null;
    L6:
        Listener[] r6 = {r9};
        Runnable[] r4 = new Runnable[r8.length];
        this.ditheringRunnables.add(r4);
        int r5 = 0;
    L8:
        if (r5 >= r8.length) goto L17;
        IntSize r3 = r8[r5];
        if (r3.width != 0) goto L12;
    L13:
        BackgroundGradientDrawable r2 = this;
    L15:
        r5 = r5 + 1;
        BackgroundGradientDrawable this = r2;
        goto L8
    L12:
        if (r3.height == 0) goto L13;
        DispatchQueue r92 = Utilities.globalQueue;
        r2 = this;
        BackgroundGradientDrawable$$ExternalSyntheticLambda1 r1 = new BackgroundGradientDrawable$$ExternalSyntheticLambda1(r2, r3, r4, r5, r6);
        r4[r5] = r1;
        r92.postRunnable(r1, r10);
        goto L15
    L17:
        return new BackgroundGradientDrawable$$ExternalSyntheticLambda2(this, r6, r4, r8);
    }

    public void dispose() {
        if (this.disposed == true) goto L19;
        int r0 = this.ditheringRunnables.size() - 1;
    L5:
        if (r0 < 0) goto L7;
        Utilities.globalQueue.cancelRunnables(this.ditheringRunnables.remove(r0));
        r0 = r0 - 1;
        goto L5
    L7:
        int r02 = this.bitmaps.size() - 1;
    L8:
        if (r02 < 0) goto L13;
        Bitmap r2 = this.bitmaps.removeAt(r02);
        if (r2 == null) goto L12;
        r2.recycle();
    L12:
        r02 = r02 - 1;
        goto L8
    L13:
        this.isForExactBounds.clear();
        this.disposables.clear();
        this.disposed = true;
        return;
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas r4) {
        if (this.disposed == false) goto L6;
        super.draw(r4);
        return;
    L6:
        Rect r0 = getBounds();
        Bitmap r1 = findBestBitmapForSize(r0.width(), r0.height());
        if (r1 == null) goto L10;
        r4.drawBitmap(r1, null, r0, this.bitmapPaint);
        return;
    L10:
        super.draw(r4);
    }

    public Disposable drawExactBoundsSize(Canvas r2, View r3) {
        return drawExactBoundsSize(r2, r3, 0.5f);
    }

    public Disposable drawExactBoundsSize(Canvas r8, View r9, float r10) {
        if (this.disposed == false) goto L6;
        super.draw(r8);
        return null;
    L6:
        Rect r0 = getBounds();
        int r2 = (int) (r0.width() * r10);
        int r102 = (int) (r0.height() * r10);
        int r3 = this.bitmaps.size();
        int r4 = 0;
    L7:
        if (r4 >= r3) goto L19;
        IntSize r5 = this.bitmaps.keyAt(r4);
        if (r5.width != r2) goto L18;
        if (r5.height != r102) goto L18;
        Bitmap r103 = this.bitmaps.valueAt(r4);
        if (r103 == null) goto L15;
        r8.drawBitmap(r103, null, r0, this.bitmapPaint);
    L17:
        return this.disposables.get(r9);
    L15:
        super.draw(r8);
    L18:
        r4 = r4 + 1;
        goto L7
    L19:
        Disposable r02 = this.disposables.remove(r9);
        if (r02 == null) goto L22;
        r02.dispose();
    L22:
        IntSize r03 = new IntSize(r2, r102);
        this.bitmaps.put(r03, null);
        this.isForExactBounds.put(r03, Boolean.TRUE);
        Disposable r92 = this.disposables.put(r9, new BackgroundGradientDrawable$$ExternalSyntheticLambda0(this, r9, startDitheringInternal(new IntSize[]{r03}, new AnonymousClass1(this, r9), 0)));
        super.draw(r8);
        return r92;
    }

    public void finalize() {
        dispose();     // Catch: Throwable -> L5
        super.finalize();
        return;
    L5:
        th = move-exception;
        super.finalize();
        throw th;
    }

    public int[] getColorsList() {
        return this.colors;
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        super.setAlpha(r1);
        this.bitmapPaint.setAlpha(r1);
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
        super.setColorFilter(r1);
        this.bitmapPaint.setColorFilter(r1);
    }

    public Disposable startDithering(Sizes r3, Listener r4) {
        return startDithering(r3, r4, 0);
    }

    public Disposable startDithering(Sizes r7, Listener r8, long r9) {
        if (this.disposed == false) goto L5;
        return null;
    L5:
        ArrayList r0 = new ArrayList(Sizes.m8988$$Nest$fgetarr(r7).length);
        int r3 = 0;
    L7:
        if (r3 >= Sizes.m8988$$Nest$fgetarr(r7).length) goto L13;
        IntSize r4 = Sizes.m8988$$Nest$fgetarr(r7)[r3];
        if (this.bitmaps.containsKey(r4) == true) goto L11;
        this.bitmaps.put(r4, null);
        r0.add(r4);
    L11:
        r3 = r3 + 1;
        goto L7
    L13:
        if (r0.isEmpty() == false) goto L16;
        return null;
    L16:
        return startDitheringInternal((IntSize[]) r0.toArray(new IntSize[0]), r8, r9);
    }
}
