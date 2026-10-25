package androidx.mediarouter.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.mediarouter.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes4.dex */
final class MediaRouteDialogHelper {
    private MediaRouteDialogHelper() {
    }

    public static int getDialogHeight(Context r1) {
        if (r1.getResources().getBoolean(R.bool.is_tablet) == true) goto L6;
        return -1;
    L6:
        return -2;
    }

    public static int getDialogWidth(Context r4) {
        DisplayMetrics r0 = r4.getResources().getDisplayMetrics();
        if (r0.widthPixels >= r0.heightPixels) goto L5;
        boolean r1 = true;
    L6:
        TypedValue r2 = new TypedValue();
        Resources r42 = r4.getResources();
        if (r1 == false) goto L9;
        int r12 = R.dimen.mr_dialog_fixed_width_minor;
    L10:
        r42.getValue(r12, r2, true);
        int r43 = r2.type;
        if (r43 != 5) goto L16;
        float r44 = r2.getDimension(r0);
    L14:
        return (int) r44;
    L16:
        if (r43 != 6) goto L18;
        int r45 = r0.widthPixels;
        r44 = r2.getFraction(r45, r45);
        goto L14
    L18:
        return -2;
    L9:
        r12 = R.dimen.mr_dialog_fixed_width_major;
        goto L10
    L5:
        r1 = false;
        goto L6
    }

    public static int getDialogWidthForDynamicGroup(Context r2) {
        if (r2.getResources().getBoolean(R.bool.is_tablet) == true) goto L7;
        return -1;
    L7:
        return getDialogWidth(r2);
    }

    public static <E> HashMap<E, BitmapDrawable> getItemBitmapMap(Context r5, ListView r6, ArrayAdapter<E> r7) {
        HashMap<E, BitmapDrawable> r0 = new HashMap();
        int r1 = r6.getFirstVisiblePosition();
        int r2 = 0;
    L4:
        if (r2 >= r6.getChildCount()) goto L6;
        r0.put(r7.getItem(r1 + r2), getViewBitmap(r5, r6.getChildAt(r2)));
        r2 = r2 + 1;
        goto L4
    L6:
        return r0;
    }

    public static <E> HashMap<E, Rect> getItemBoundMap(ListView r9, ArrayAdapter<E> r10) {
        HashMap<E, Rect> r0 = new HashMap();
        int r1 = r9.getFirstVisiblePosition();
        int r2 = 0;
    L4:
        if (r2 >= r9.getChildCount()) goto L6;
        E r3 = r10.getItem(r1 + r2);
        View r4 = r9.getChildAt(r2);
        r0.put(r3, new Rect(r4.getLeft(), r4.getTop(), r4.getRight(), r4.getBottom()));
        r2 = r2 + 1;
        goto L4
    L6:
        return r0;
    }

    public static <E> Set<E> getItemsAdded(List<E> r1, List<E> r2) {
        HashSet r0 = new HashSet(r2);
        r0.removeAll(r1);
        return r0;
    }

    public static <E> Set<E> getItemsRemoved(List<E> r1, List<E> r2) {
        HashSet r0 = new HashSet(r1);
        r0.removeAll(r2);
        return r0;
    }

    private static BitmapDrawable getViewBitmap(Context r3, View r4) {
        Bitmap r0 = Bitmap.createBitmap(r4.getWidth(), r4.getHeight(), Bitmap.Config.ARGB_8888);
        r4.draw(new Canvas(r0));
        return new BitmapDrawable(r3.getResources(), r0);
    }

    public static <E> boolean listUnorderedEquals(List<E> r1, List<E> r2) {
        return new HashSet(r1).equals(new HashSet(r2));
    }
}
