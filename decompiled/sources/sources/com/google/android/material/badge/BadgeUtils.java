package com.google.android.material.badge;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.badge.BadgeState;
import com.google.android.material.internal.ParcelableSparseArray;
import com.google.android.material.internal.ToolbarUtils;

@ExperimentalBadgeUtils
/* loaded from: classes5.dex */
public class BadgeUtils {
    private static final String LOG_TAG = "BadgeUtils";

    /* renamed from: com.google.android.material.badge.BadgeUtils$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ BadgeDrawable val$badgeDrawable;
        final /* synthetic */ FrameLayout val$customBadgeParent;
        final /* synthetic */ int val$menuItemId;
        final /* synthetic */ Toolbar val$toolbar;

        public AnonymousClass1(Toolbar r1, int r2, BadgeDrawable r3, FrameLayout r4) {
            this.val$toolbar = r1;
            this.val$menuItemId = r2;
            this.val$badgeDrawable = r3;
            this.val$customBadgeParent = r4;
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionMenuItemView r0 = ToolbarUtils.getActionMenuItemView(this.val$toolbar, this.val$menuItemId);
            if (r0 == null) goto L6;
            BadgeUtils.setToolbarOffset(this.val$badgeDrawable, this.val$toolbar.getResources());
            BadgeUtils.attachBadgeDrawable(this.val$badgeDrawable, r0, this.val$customBadgeParent);
            BadgeUtils.access$000(this.val$badgeDrawable, r0);
            return;
        }
    }

    /* renamed from: com.google.android.material.badge.BadgeUtils$2, reason: invalid class name */
    public class AnonymousClass2 extends AccessibilityDelegateCompat {
        final /* synthetic */ BadgeDrawable val$badgeDrawable;
        final /* synthetic */ View val$view;

        public AnonymousClass2(View.AccessibilityDelegate r1, View r2, BadgeDrawable r3) {
            this.val$view = r2;
            this.val$badgeDrawable = r3;
            super(r1);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfoCompat r2) {
            super.onInitializeAccessibilityNodeInfo(r1, r2);
            r2.setContentDescription(BadgeUtils.access$100(this.val$view, this.val$badgeDrawable));
        }
    }

    /* renamed from: com.google.android.material.badge.BadgeUtils$3, reason: invalid class name */
    public class AnonymousClass3 extends AccessibilityDelegateCompat {
        final /* synthetic */ BadgeDrawable val$badgeDrawable;
        final /* synthetic */ View val$view;

        public AnonymousClass3(View r1, BadgeDrawable r2) {
            this.val$view = r1;
            this.val$badgeDrawable = r2;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfoCompat r2) {
            super.onInitializeAccessibilityNodeInfo(r1, r2);
            r2.setContentDescription(BadgeUtils.access$100(this.val$view, this.val$badgeDrawable));
        }
    }

    /* renamed from: com.google.android.material.badge.BadgeUtils$4, reason: invalid class name */
    public class AnonymousClass4 extends AccessibilityDelegateCompat {
        final /* synthetic */ View val$view;

        public AnonymousClass4(View.AccessibilityDelegate r1, View r2) {
            this.val$view = r2;
            super(r1);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfoCompat r2) {
            super.onInitializeAccessibilityNodeInfo(r1, r2);
            r2.setContentDescription(this.val$view.getContentDescription());
        }
    }

    private BadgeUtils() {
    }

    public static /* synthetic */ void access$000(BadgeDrawable r0, View r1) {
        attachBadgeContentDescription(r0, r1);
    }

    public static /* synthetic */ CharSequence access$100(View r0, BadgeDrawable r1) {
        return getBadgeAnchorContentDescription(r0, r1);
    }

    private static void attachBadgeContentDescription(BadgeDrawable r2, View r3) {
        if (Build.VERSION.SDK_INT >= 29) goto L5;
    L8:
        ViewCompat.setAccessibilityDelegate(r3, new AnonymousClass3(r3, r2));
        return;
    L5:
        if (ViewCompat.hasAccessibilityDelegate(r3) == false) goto L8;
        ViewCompat.setAccessibilityDelegate(r3, new AnonymousClass2(r3.getAccessibilityDelegate(), r3, r2));
    }

    public static void attachBadgeDrawable(BadgeDrawable r1, View r2) {
        attachBadgeDrawable(r1, r2, null);
    }

    public static void attachBadgeDrawable(BadgeDrawable r0, View r1, FrameLayout r2) {
        setBadgeDrawableBounds(r0, r1, r2);
        if (r0.getCustomBadgeParent() == null) goto L6;
        r0.getCustomBadgeParent().setForeground(r0);
        return;
    L6:
        r1.getOverlay().add(r0);
    }

    public static void attachBadgeDrawable(BadgeDrawable r1, Toolbar r2, int r3) {
        attachBadgeDrawable(r1, r2, r3, null);
    }

    public static void attachBadgeDrawable(BadgeDrawable r1, Toolbar r2, int r3, FrameLayout r4) {
        r2.post(new AnonymousClass1(r2, r3, r1, r4));
    }

    public static SparseArray<BadgeDrawable> createBadgeDrawablesFromSavedStates(Context r4, ParcelableSparseArray r5) {
        SparseArray<BadgeDrawable> r0 = new SparseArray(r5.size());
        int r1 = 0;
    L4:
        if (r1 >= r5.size()) goto L10;
        int r2 = r5.keyAt(r1);
        BadgeState.State r3 = (BadgeState.State) r5.valueAt(r1);
        if (r3 == null) goto L8;
        BadgeDrawable r32 = BadgeDrawable.createFromSavedState(r4, r3);
    L9:
        r0.put(r2, r32);
        r1 = r1 + 1;
        goto L4
    L8:
        r32 = null;
        goto L9
    L10:
        return r0;
    }

    public static ParcelableSparseArray createParcelableBadgeStates(SparseArray<BadgeDrawable> r4) {
        ParcelableSparseArray r0 = new ParcelableSparseArray();
        int r1 = 0;
    L4:
        if (r1 >= r4.size()) goto L10;
        int r2 = r4.keyAt(r1);
        BadgeDrawable r3 = r4.valueAt(r1);
        if (r3 == null) goto L8;
        BadgeState.State r32 = r3.getSavedState();
    L9:
        r0.put(r2, r32);
        r1 = r1 + 1;
        goto L4
    L8:
        r32 = null;
        goto L9
    L10:
        return r0;
    }

    private static void detachBadgeContentDescription(View r2) {
        if (Build.VERSION.SDK_INT >= 29) goto L5;
    L8:
        ViewCompat.setAccessibilityDelegate(r2, null);
        return;
    L5:
        if (ViewCompat.hasAccessibilityDelegate(r2) == false) goto L8;
        ViewCompat.setAccessibilityDelegate(r2, new AnonymousClass4(r2.getAccessibilityDelegate(), r2));
    }

    public static void detachBadgeDrawable(BadgeDrawable r1, View r2) {
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.getCustomBadgeParent() == null) goto L8;
        r1.getCustomBadgeParent().setForeground(null);
        return;
    L8:
        r2.getOverlay().remove(r1);
    }

    public static void detachBadgeDrawable(BadgeDrawable r0, Toolbar r1, int r2) {
        if (r0 != null) goto L4;
        return;
    L4:
        ActionMenuItemView r12 = ToolbarUtils.getActionMenuItemView(r1, r2);
        if (r12 == null) goto L8;
        removeToolbarOffset(r0);
        detachBadgeDrawable(r0, r12);
        detachBadgeContentDescription(r12);
        return;
    L8:
        Log.w(LOG_TAG, "Trying to remove badge from a null menuItemView: " + r2);
    }

    private static CharSequence getBadgeAnchorContentDescription(View r0, BadgeDrawable r1) {
        CharSequence r12 = r1.getContentDescription();
        if (r12 == null) goto L6;
        return r12;
    L6:
        return r0.getContentDescription();
    }

    public static void removeToolbarOffset(BadgeDrawable r1) {
        r1.setAdditionalHorizontalOffset(0);
        r1.setAdditionalVerticalOffset(0);
    }

    public static void setBadgeDrawableBounds(BadgeDrawable r1, View r2, FrameLayout r3) {
        Rect r0 = new Rect();
        r2.getDrawingRect(r0);
        r1.setBounds(r0);
        r1.updateBadgeCoordinates(r2, r3);
    }

    public static void setToolbarOffset(BadgeDrawable r1, Resources r2) {
        r1.setAdditionalHorizontalOffset(r2.getDimensionPixelOffset(R.dimen.mtrl_badge_toolbar_action_menu_item_horizontal_offset));
        r1.setAdditionalVerticalOffset(r2.getDimensionPixelOffset(R.dimen.mtrl_badge_toolbar_action_menu_item_vertical_offset));
    }

    public static void updateBadgeBounds(Rect r2, float r3, float r4, float r5, float r6) {
        r2.set((int) (r3 - r5), (int) (r4 - r6), (int) (r3 + r5), (int) (r4 + r6));
    }
}
