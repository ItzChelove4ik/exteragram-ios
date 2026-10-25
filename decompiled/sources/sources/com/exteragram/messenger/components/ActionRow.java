package com.exteragram.messenger.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public class ActionRow extends FrameLayout {
    private final int GAP_DP;
    private final int HORIZONTAL_PADDING_DP;
    private final int ITEM_SIZE_DP;
    private final FrameLayout buttonsView;
    private final List<ActionItem> currentItems;

    /* renamed from: com.exteragram.messenger.components.ActionRow$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ ActionRow this$0;

        public AnonymousClass1(ActionRow r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
            int r42 = 0;
        L4:
            if (r42 >= getChildCount()) goto L9;
            int r62 = (r7 - r5) - AndroidUtilities.dp((getChildCount() * 40) + 20);
            int r0 = 1;
            if ((getChildCount() - 1) <= 0) goto L8;
            r0 = getChildCount() - 1;
        L8:
            int r82 = AndroidUtilities.dp((r42 * 40) + 10) + ((r62 / r0) * r42);
            int r63 = AndroidUtilities.dp(8.0f);
            View r02 = getChildAt(r42);
            r02.layout(r82, r63, r02.getMeasuredWidth() + r82, r02.getMeasuredHeight() + r63);
            r42 = r42 + 1;
            goto L4
        }
    }

    /* renamed from: com.exteragram.messenger.components.ActionRow$2, reason: invalid class name */
    public class AnonymousClass2 extends ImageView {
        final /* synthetic */ ActionRow this$0;

        public AnonymousClass2(ActionRow r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void setEnabled(boolean r1) {
            super.setEnabled(r1);
            if (r1 == false) goto L5;
            float r12 = 1.0f;
        L6:
            setAlpha(r12);
            return;
        L5:
            r12 = 0.5f;
            goto L6
        }
    }

    public static class ActionItem {
        public View.OnClickListener action;
        public boolean enabled;
        public int icon;
        public View.OnLongClickListener longAction;

        public ActionItem(int r2, boolean r3, View.OnClickListener r4) {
            this(r2, r3, r4, null);
        }

        public ActionItem(int r1, boolean r2, View.OnClickListener r3, View.OnLongClickListener r4) {
            this.icon = r1;
            this.enabled = r2;
            this.action = r3;
            this.longAction = r4;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L13:
            return false;
        L8:
            if (getClass() != r5.getClass()) goto L13;
            if (this.icon != ((ActionItem) r5).icon) goto L13;
            return true;
        }

        public int hashCode() {
            return this.icon;
        }
    }

    public static /* synthetic */ void $r8$lambda$26zglbKraCw1z6R_7UEj7kIjLck(ImageView r2, ActionItem r3, int r4) {
        ViewPropertyAnimator r22 = r2.animate();
        if (r3.enabled == false) goto L5;
        float r32 = 1.0f;
    L6:
        r22.alpha(r32).translationX(0.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setStartDelay((r4 * 35) + 100).setDuration(400).start();
        return;
    L5:
        r32 = 0.5f;
        goto L6
    }

    public ActionRow(Context r2, Theme.ResourcesProvider r3, List<ActionItem> r4) {
        super(r2);
        this.HORIZONTAL_PADDING_DP = 10;
        this.ITEM_SIZE_DP = 40;
        this.GAP_DP = 8;
        this.currentItems = new ArrayList();
        AnonymousClass1 r0 = new AnonymousClass1(this, r2);
        this.buttonsView = r0;
        addView(r0, LayoutHelper.createLinear(-1, -1));
        updateItems(r4, r3);
    }

    private void addImageButton(Context r3, Theme.ResourcesProvider r4, FrameLayout r5, ActionItem r6, int r7) {
        AnonymousClass2 r0 = new AnonymousClass2(this, r3);
        ScaleStateListAnimator.apply(r0, 0.15f, 1.5f);
        r0.setScaleType(ImageView.ScaleType.CENTER);
        r0.setEnabled(r6.enabled);
        r0.setImageDrawable(ContextCompat.getDrawable(r3, r6.icon).mutate());
        r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_actionBarDefaultSubmenuItemIcon, r4), PorterDuff.Mode.MULTIPLY));
        r0.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_dialogButtonSelector, r4), 1, AndroidUtilities.dp(20.0f)));
        r0.setOnClickListener(r6.action);
        View.OnLongClickListener r2 = r6.longAction;
        if (r2 == null) goto L5;
        r0.setOnLongClickListener(r2);
    L5:
        r0.setTag(r6);
        r0.setAlpha(0.0f);
        r0.setTranslationX(AndroidUtilities.dp(12.0f));
        r5.addView(r0, LayoutHelper.createFrame(40, 40, 51));
        r0.post(new ActionRow$$ExternalSyntheticLambda0(r0, r6, r7));
    }

    public boolean isItemPresent(int r5) {
        int r1 = 0;
    L4:
        if (r1 >= this.buttonsView.getChildCount()) goto L14;
        View r2 = this.buttonsView.getChildAt(r1);
        if ((r2 instanceof ImageView) == false) goto L13;
        Object r22 = r2.getTag();
        if ((r22 instanceof ActionItem) == false) goto L13;
        if (((ActionItem) r22).icon != r5) goto L13;
        return true;
    L13:
        r1 = r1 + 1;
        goto L4
    L14:
        return false;
    }

    public void updateItems(List<ActionItem> r11, Theme.ResourcesProvider r12) {
        this.buttonsView.removeAllViews();
        this.currentItems.clear();
        ArrayList r6 = new ArrayList();
        Iterator<ActionItem> r7 = r11.iterator();
        int r8 = 0;
        int r5 = 0;
    L4:
        if (r7.hasNext() == false) goto L10;
        ActionItem r4 = r7.next();
        if (r4.enabled == false) goto L9;
        if (r5 >= 4) goto L9;
        addImageButton(getContext(), r12, this.buttonsView, r4, r5);
        this.currentItems.add(r4);
        r5 = r5 + 1;
    L9:
        r6.add(r4);
        goto L4
    L10:
        int r72 = r6.size();
    L11:
        if (r8 >= r72) goto L15;
        Object r1 = r6.get(r8);
        r8 = r8 + 1;
        ActionItem r42 = (ActionItem) r1;
        if (r5 >= 4) goto L25;
        addImageButton(getContext(), r12, this.buttonsView, r42, r5);
        this.currentItems.add(r42);
        r5 = r5 + 1;
        goto L11
    L25:
        return;
    }
}
