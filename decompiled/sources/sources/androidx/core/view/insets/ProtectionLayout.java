package androidx.core.view.insets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.R$id;
import androidx.core.graphics.Insets;
import androidx.core.view.insets.Protection;
import com.sun.jna.IntegerType$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class ProtectionLayout extends FrameLayout {
    private static final Object PROTECTION_VIEW = null;
    private ProtectionGroup mGroup;
    private final List<Protection> mProtections;

    /* renamed from: androidx.core.view.insets.ProtectionLayout$1, reason: invalid class name */
    public class AnonymousClass1 implements Protection.Attributes.Callback {
        final /* synthetic */ ProtectionLayout this$0;
        final /* synthetic */ FrameLayout.LayoutParams val$params;
        final /* synthetic */ View val$view;

        public AnonymousClass1(ProtectionLayout r1, FrameLayout.LayoutParams r2, View r3) {
            this.this$0 = r1;
            this.val$params = r2;
            this.val$view = r3;
        }

        @Override // androidx.core.view.insets.Protection.Attributes.Callback
        public void onAlphaChanged(float r1) {
            this.val$view.setAlpha(r1);
        }

        @Override // androidx.core.view.insets.Protection.Attributes.Callback
        public void onDrawableChanged(Drawable r1) {
            this.val$view.setBackground(r1);
        }

        @Override // androidx.core.view.insets.Protection.Attributes.Callback
        public void onHeightChanged(int r2) {
            FrameLayout.LayoutParams r0 = this.val$params;
            r0.height = r2;
            this.val$view.setLayoutParams(r0);
        }

        @Override // androidx.core.view.insets.Protection.Attributes.Callback
        public void onMarginChanged(Insets r3) {
            FrameLayout.LayoutParams r0 = this.val$params;
            r0.leftMargin = r3.left;
            r0.topMargin = r3.top;
            r0.rightMargin = r3.right;
            r0.bottomMargin = r3.bottom;
            this.val$view.setLayoutParams(r0);
        }

        @Override // androidx.core.view.insets.Protection.Attributes.Callback
        public void onTranslationXChanged(float r1) {
            this.val$view.setTranslationX(r1);
        }

        @Override // androidx.core.view.insets.Protection.Attributes.Callback
        public void onTranslationYChanged(float r1) {
            this.val$view.setTranslationY(r1);
        }

        @Override // androidx.core.view.insets.Protection.Attributes.Callback
        public void onVisibilityChanged(boolean r1) {
            View r0 = this.val$view;
            if (r1 == false) goto L5;
            int r12 = 0;
        L6:
            r0.setVisibility(r12);
            return;
        L5:
            r12 = 8;
            goto L6
        }

        @Override // androidx.core.view.insets.Protection.Attributes.Callback
        public void onWidthChanged(int r2) {
            FrameLayout.LayoutParams r0 = this.val$params;
            r0.width = r2;
            this.val$view.setLayoutParams(r0);
        }
    }

    static {
        PROTECTION_VIEW = new Object();
    }

    public ProtectionLayout(Context r1) {
        super(r1);
        this.mProtections = new ArrayList();
    }

    public ProtectionLayout(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public ProtectionLayout(Context r2, AttributeSet r3, int r4) {
        this(r2, r3, r4, 0);
    }

    public ProtectionLayout(Context r1, AttributeSet r2, int r3, int r4) {
        super(r1, r2, r3, r4);
        this.mProtections = new ArrayList();
    }

    public ProtectionLayout(Context r1, List<Protection> r2) {
        super(r1);
        this.mProtections = new ArrayList();
        setProtections(r2);
    }

    private void addProtectionView(Context r7, int r8, Protection r9) {
        Protection.Attributes r0 = r9.getAttributes();
        int r1 = r9.getSide();
        int r3 = 8;
        int r4 = -1;
        if (r1 != 1) goto L5;
        int r92 = r0.getWidth();
        int r12 = 3;
    L13:
        r4 = r92;
        int r93 = -1;
    L16:
        FrameLayout.LayoutParams r2 = new FrameLayout.LayoutParams(r4, r93, r12);
        Insets r94 = r0.getMargin();
        r2.leftMargin = r94.left;
        r2.topMargin = r94.top;
        r2.rightMargin = r94.right;
        r2.bottomMargin = r94.bottom;
        View r95 = new View(r7);
        r95.setTag(PROTECTION_VIEW);
        r95.setTranslationX(r0.getTranslationX());
        r95.setTranslationY(r0.getTranslationY());
        r95.setAlpha(r0.getAlpha());
        if (r0.isVisible() == false) goto L19;
        r3 = 0;
    L19:
        r95.setVisibility(r3);
        r95.setBackground(r0.getDrawable());
        r0.setCallback(new AnonymousClass1(this, r2, r95));
        addView(r95, r8, r2);
        return;
    L5:
        if (r1 != 2) goto L7;
        r93 = r0.getHeight();
        r12 = 48;
        goto L16
    L7:
        if (r1 == 4) goto L12;
        if (r1 != 8) goto L10;
        r93 = r0.getHeight();
        r12 = 80;
        goto L16
    L10:
        IntegerType$$ExternalSyntheticBUOutline0.m("Unexpected side: ", r9.getSide());
        return;
    L12:
        r92 = r0.getWidth();
        r12 = 5;
        goto L13
    }

    private void addProtectionViews() {
        if (this.mProtections.isEmpty() == false) goto L6;
        removeProtectionViews();
        return;
    L6:
        SystemBarStateMonitor r0 = getOrInstallSystemBarStateMonitor();
        removeProtectionViews();
        this.mGroup = new ProtectionGroup(r0, this.mProtections);
        int r02 = getChildCount();
        int r1 = this.mGroup.size();
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L9;
        Protection r3 = this.mGroup.getProtection(r2);
        addProtectionView(getContext(), r2 + r02, r3);
        r2 = r2 + 1;
        goto L7
    }

    private SystemBarStateMonitor getOrInstallSystemBarStateMonitor() {
        ViewGroup r2 = (ViewGroup) getRootView();
        Object r0 = r2.getTag(R$id.tag_system_bar_state_monitor);
        if ((r0 instanceof SystemBarStateMonitor) == true) goto L5;
        SystemBarStateMonitor r02 = new SystemBarStateMonitor(r2);
        r2.setTag(R$id.tag_system_bar_state_monitor, r02);
        return r02;
    L5:
        return (SystemBarStateMonitor) r0;
    }

    private void maybeUninstallSystemBarStateMonitor() {
        ViewGroup r2 = (ViewGroup) getRootView();
        Object r0 = r2.getTag(R$id.tag_system_bar_state_monitor);
        if ((r0 instanceof SystemBarStateMonitor) == false) goto L10;
        SystemBarStateMonitor r02 = (SystemBarStateMonitor) r0;
        if (r02.hasCallback() == false) goto L8;
        return;
    L8:
        r02.detachFromWindow();
        r2.setTag(R$id.tag_system_bar_state_monitor, null);
        return;
    }

    private void removeProtectionViews() {
        if (this.mGroup == null) goto L11;
        removeViews(getChildCount() - this.mGroup.size(), this.mGroup.size());
        int r0 = this.mGroup.size();
        int r1 = 0;
    L5:
        ProtectionGroup r2 = this.mGroup;
        if (r1 >= r0) goto L8;
        r2.getProtection(r1).getAttributes().setCallback(null);
        r1 = r1 + 1;
        goto L5
    L8:
        r2.dispose();
        this.mGroup = null;
        return;
    }

    @Override // android.view.ViewGroup
    public void addView(View r3, int r4, ViewGroup.LayoutParams r5) {
        if (r3 != null) goto L4;
    L13:
        super.addView(r3, r4, r5);
        return;
    L4:
        if (r3.getTag() == PROTECTION_VIEW) goto L13;
        ProtectionGroup r0 = this.mGroup;
        if (r0 == null) goto L8;
        int r02 = r0.size();
    L9:
        int r1 = getChildCount() - r02;
        if (r4 > r1) goto L12;
        if (r4 >= 0) goto L13;
    L12:
        r4 = r1;
        goto L13
    L8:
        r02 = 0;
        goto L9
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        addProtectionViews();
        requestApplyInsets();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeProtectionViews();
        maybeUninstallSystemBarStateMonitor();
    }

    public void setProtections(List<Protection> r2) {
        this.mProtections.clear();
        this.mProtections.addAll(r2);
        if (isAttachedToWindow() == false) goto L6;
        addProtectionViews();
        requestApplyInsets();
        return;
    }
}
