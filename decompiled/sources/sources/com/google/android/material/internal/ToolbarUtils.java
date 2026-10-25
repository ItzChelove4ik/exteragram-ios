package com.google.android.material.internal;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes5.dex */
public class ToolbarUtils {
    private static final Comparator<View> VIEW_TOP_COMPARATOR = null;

    /* renamed from: com.google.android.material.internal.ToolbarUtils$1, reason: invalid class name */
    public class AnonymousClass1 implements Comparator<View> {
        public AnonymousClass1() {
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(View r1, View r2) {
            return r1.getTop() - r2.getTop();
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(View r1, View r2) {
            return compare2(r1, r2);
        }
    }

    static {
        VIEW_TOP_COMPARATOR = new AnonymousClass1();
    }

    private ToolbarUtils() {
    }

    public static ActionMenuItemView getActionMenuItemView(Toolbar r3, int r4) {
        ActionMenuView r32 = getActionMenuView(r3);
        if (r32 == null) goto L13;
        int r0 = 0;
    L6:
        if (r0 >= r32.getChildCount()) goto L19;
        View r1 = r32.getChildAt(r0);
        if ((r1 instanceof ActionMenuItemView) == false) goto L12;
        ActionMenuItemView r12 = (ActionMenuItemView) r1;
        if (r12.getItemData().getItemId() != r4) goto L12;
        return r12;
    L12:
        r0 = r0 + 1;
        goto L6
    L19:
        return null;
    L13:
        return null;
    }

    public static ActionMenuView getActionMenuView(Toolbar r3) {
        int r0 = 0;
    L4:
        if (r0 >= r3.getChildCount()) goto L10;
        View r1 = r3.getChildAt(r0);
        if ((r1 instanceof ActionMenuView) == true) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return (ActionMenuView) r1;
    L10:
        return null;
    }

    private static ImageView getImageView(Toolbar r5, Drawable r6) {
        if (r6 != null) goto L5;
        return null;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= r5.getChildCount()) goto L18;
        View r2 = r5.getChildAt(r1);
        if ((r2 instanceof ImageView) == false) goto L17;
        ImageView r22 = (ImageView) r2;
        Drawable r3 = r22.getDrawable();
        if (r3 == null) goto L17;
        if (r3.getConstantState() == null) goto L17;
        if (r3.getConstantState().equals(r6.getConstantState()) == false) goto L17;
        return r22;
    L17:
        r1 = r1 + 1;
        goto L7
    L18:
        return null;
    }

    public static ImageView getLogoImageView(Toolbar r1) {
        return getImageView(r1, r1.getLogo());
    }

    public static ImageButton getNavigationIconButton(Toolbar r5) {
        Drawable r0 = r5.getNavigationIcon();
        if (r0 != null) goto L5;
        return null;
    L5:
        int r2 = 0;
    L7:
        if (r2 >= r5.getChildCount()) goto L14;
        View r3 = r5.getChildAt(r2);
        if ((r3 instanceof ImageButton) == false) goto L13;
        ImageButton r32 = (ImageButton) r3;
        if (r32.getDrawable() != r0) goto L13;
        return r32;
    L13:
        r2 = r2 + 1;
        goto L7
    L14:
        return null;
    }

    public static View getSecondaryActionMenuItemView(Toolbar r2) {
        ActionMenuView r22 = getActionMenuView(r2);
        if (r22 != null) goto L5;
        return null;
    L5:
        if (r22.getChildCount() > 1) goto L7;
        return null;
    L7:
        return r22.getChildAt(0);
    }

    public static TextView getSubtitleTextView(Toolbar r1) {
        List<TextView> r12 = getTextViewsWithText(r1, r1.getSubtitle());
        if (r12.isEmpty() == false) goto L7;
        return null;
    L7:
        return (TextView) Collections.max(r12, VIEW_TOP_COMPARATOR);
    }

    private static List<TextView> getTextViewsWithText(Toolbar r4, CharSequence r5) {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r4.getChildCount()) goto L11;
        View r2 = r4.getChildAt(r1);
        if ((r2 instanceof TextView) == false) goto L10;
        TextView r22 = (TextView) r2;
        if (TextUtils.equals(r22.getText(), r5) == false) goto L10;
        r0.add(r22);
    L10:
        r1 = r1 + 1;
        goto L4
    L11:
        return r0;
    }

    public static TextView getTitleTextView(Toolbar r1) {
        List<TextView> r12 = getTextViewsWithText(r1, r1.getTitle());
        if (r12.isEmpty() == false) goto L7;
        return null;
    L7:
        return (TextView) Collections.min(r12, VIEW_TOP_COMPARATOR);
    }
}
