package androidx.fragment.app;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.R$styleable;
import androidx.lifecycle.LifecycleRegistry$$ExternalSyntheticBUOutline0;
import androidx.lifecycle.LiveData$$ExternalSyntheticBUOutline0;
import com.android.dx.rop.code.RegisterSpec;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.NotificationBadge;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001<B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B#\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB\u001f\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0011H\u0002J\"\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0017J\u0010\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\"H\u0014J \u0010#\u001a\u00020\u00132\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010$\u001a\u00020%H\u0014J\u0010\u0010&\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u0011H\u0016J\u0017\u0010(\u001a\u0002H)\"\n\b\u0000\u0010)*\u0004\u0018\u00010*¢\u0006\u0002\u0010+J\u0010\u0010,\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0017J\b\u0010-\u001a\u00020\u0016H\u0016J\u0010\u0010.\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u0011H\u0016J\u0010\u0010/\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\bH\u0016J\u0010\u00100\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u0011H\u0016J\u0018\u00101\u001a\u00020\u00162\u0006\u00102\u001a\u00020\b2\u0006\u00103\u001a\u00020\bH\u0016J\u0018\u00104\u001a\u00020\u00162\u0006\u00102\u001a\u00020\b2\u0006\u00103\u001a\u00020\bH\u0016J\u0010\u00105\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0013H\u0001J\u0012\u00106\u001a\u00020\u00162\b\u00107\u001a\u0004\u0018\u000108H\u0016J\u0010\u00109\u001a\u00020\u00162\u0006\u0010:\u001a\u00020\u000eH\u0016J\u0010\u0010;\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u0011H\u0016R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Landroidx/fragment/app/FragmentContainerView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", _UrlKt.FRAGMENT_ENCODE_SET, "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "fm", "Landroidx/fragment/app/FragmentManager;", "(Landroid/content/Context;Landroid/util/AttributeSet;Landroidx/fragment/app/FragmentManager;)V", "applyWindowInsetsListener", "Landroid/view/View$OnApplyWindowInsetsListener;", "disappearingFragmentChildren", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/view/View;", "drawDisappearingViewsFirst", _UrlKt.FRAGMENT_ENCODE_SET, "transitioningFragmentViews", "addDisappearingFragmentView", _UrlKt.FRAGMENT_ENCODE_SET, RegisterSpec.PREFIX, "addView", "child", "index", PluginsConstants.PARAMS, "Landroid/view/ViewGroup$LayoutParams;", "dispatchApplyWindowInsets", "Landroid/view/WindowInsets;", "insets", "dispatchDraw", "canvas", "Landroid/graphics/Canvas;", "drawChild", "drawingTime", _UrlKt.FRAGMENT_ENCODE_SET, "endViewTransition", PluginsConstants.Settings.VIEW, "getFragment", "F", "Landroidx/fragment/app/Fragment;", "()Landroidx/fragment/app/Fragment;", "onApplyWindowInsets", "removeAllViewsInLayout", "removeView", "removeViewAt", "removeViewInLayout", "removeViews", "start", NotificationBadge.NewHtcHomeBadger.COUNT, "removeViewsInLayout", "setDrawDisappearingViewsLast", "setLayoutTransition", "transition", "Landroid/animation/LayoutTransition;", "setOnApplyWindowInsetsListener", "listener", "startViewTransition", "Api20Impl", "fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FragmentContainerView extends FrameLayout {
    private View.OnApplyWindowInsetsListener applyWindowInsetsListener;
    private final List<View> disappearingFragmentChildren;
    private boolean drawDisappearingViewsFirst;
    private final List<View> transitioningFragmentViews;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004¨\u0006\n"}, d2 = {"Landroidx/fragment/app/FragmentContainerView$Api20Impl;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "onApplyWindowInsets", "Landroid/view/WindowInsets;", "onApplyWindowInsetsListener", "Landroid/view/View$OnApplyWindowInsetsListener;", RegisterSpec.PREFIX, "Landroid/view/View;", "insets", "fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Api20Impl {
        public static final Api20Impl INSTANCE = null;

        static {
            INSTANCE = new Api20Impl();
        }

        private Api20Impl() {
        }

        public final WindowInsets onApplyWindowInsets(View.OnApplyWindowInsetsListener r1, View r2, WindowInsets r3) {
            return r1.onApplyWindowInsets(r2, r3);
        }
    }

    public FragmentContainerView(Context r1) {
        super(r1);
        this.disappearingFragmentChildren = new ArrayList();
        this.transitioningFragmentViews = new ArrayList();
        this.drawDisappearingViewsFirst = true;
    }

    @JvmOverloads
    public FragmentContainerView(Context r7, AttributeSet r8) {
        this(r7, r8, 0, 4, null);
    }

    @JvmOverloads
    public FragmentContainerView(Context r3, AttributeSet r4, int r5) {
        super(r3, r4, r5);
        this.disappearingFragmentChildren = new ArrayList();
        this.transitioningFragmentViews = new ArrayList();
        this.drawDisappearingViewsFirst = true;
        if (r4 == null) goto L15;
        String r52 = r4.getClassAttribute();
        TypedArray r32 = r3.obtainStyledAttributes(r4, R$styleable.FragmentContainerView, 0, 0);
        if (r52 != null) goto L7;
        r52 = r32.getString(R$styleable.FragmentContainerView_android_name);
        String r42 = "android:name";
    L8:
        r32.recycle();
        if (r52 != null) goto L11;
        return;
    L11:
        if (isInEditMode() == false) goto L14;
        return;
    L14:
        throw new UnsupportedOperationException("FragmentContainerView must be within a FragmentActivity to use " + r42 + "=\"" + r52 + Typography.quote);
    L7:
        r42 = "class";
        goto L8
    }

    public /* synthetic */ FragmentContainerView(Context r1, AttributeSet r2, int r3, int r4, DefaultConstructorMarker r5) {
        if ((r4 & 4) == 0) goto L5;
        r3 = 0;
    L5:
        this(r1, r2, r3);
    }

    public FragmentContainerView(Context r7, AttributeSet r8, FragmentManager r9) {
        super(r7, r8);
        this.disappearingFragmentChildren = new ArrayList();
        this.transitioningFragmentViews = new ArrayList();
        this.drawDisappearingViewsFirst = true;
        String r1 = r8.getClassAttribute();
        TypedArray r2 = r7.obtainStyledAttributes(r8, R$styleable.FragmentContainerView, 0, 0);
        if (r1 != null) goto L5;
        r1 = r2.getString(R$styleable.FragmentContainerView_android_name);
    L5:
        String r3 = r2.getString(R$styleable.FragmentContainerView_android_tag);
        r2.recycle();
        int r22 = getId();
        Fragment r4 = r9.findFragmentById(r22);
        if (r1 == null) goto L16;
        if (r4 != null) goto L16;
        if (r22 != (-1)) goto L15;
        if (r3 == null) goto L12;
        String r6 = " with tag ".concat(r3);
    L13:
        LiveData$$ExternalSyntheticBUOutline0.m("FragmentContainerView must have an android:id to add Fragment ", r1, r6);
        throw null;
    L12:
        r6 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L13
    L15:
        Fragment r12 = r9.getFragmentFactory().instantiate(r7.getClassLoader(), r1);
        r12.onInflate(r7, r8, null);
        r9.beginTransaction().setReorderingAllowed(true).add(this, r12, r3).commitNowAllowingStateLoss();
    L16:
        r9.onContainerAvailable(this);
    }

    private final void addDisappearingFragmentView(View r2) {
        if (this.transitioningFragmentViews.contains(r2) == false) goto L6;
        this.disappearingFragmentChildren.add(r2);
        return;
    }

    @Override // android.view.ViewGroup
    public void addView(View r2, int r3, ViewGroup.LayoutParams r4) {
        if (FragmentManager.getViewFragment(r2) == null) goto L6;
        super.addView(r2, r3, r4);
        return;
    L6:
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("Views added to a FragmentContainerView must be associated with a Fragment. View ", r2, " is not associated with a Fragment.");
    }

    @Override // android.view.ViewGroup, android.view.View
    public WindowInsets dispatchApplyWindowInsets(WindowInsets r5) {
        WindowInsetsCompat r0 = WindowInsetsCompat.toWindowInsetsCompat(r5);
        View.OnApplyWindowInsetsListener r1 = this.applyWindowInsetsListener;
        if (r1 == null) goto L5;
        WindowInsetsCompat r02 = WindowInsetsCompat.toWindowInsetsCompat(Api20Impl.INSTANCE.onApplyWindowInsets(r1, this, r5));
    L7:
        if (r02.isConsumed() == true) goto L11;
        int r12 = getChildCount();
        int r2 = 0;
    L9:
        if (r2 >= r12) goto L11;
        ViewCompat.dispatchApplyWindowInsets(getChildAt(r2), r02);
        r2 = r2 + 1;
    L11:
        return r5;
    L5:
        r02 = ViewCompat.onApplyWindowInsets(this, r0);
        goto L7
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r5) {
        if (this.drawDisappearingViewsFirst == false) goto L8;
        Iterator<T> r0 = this.disappearingFragmentChildren.iterator();
    L6:
        if (r0.hasNext() == false) goto L8;
        super.drawChild(r5, (View) r0.next(), getDrawingTime());
    L8:
        super.dispatchDraw(r5);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r2, View r3, long r4) {
        if (this.drawDisappearingViewsFirst == false) goto L11;
        if (this.disappearingFragmentChildren.isEmpty() == true) goto L11;
        if (this.disappearingFragmentChildren.contains(r3) == false) goto L11;
        return false;
    L11:
        return super.drawChild(r2, r3, r4);
    }

    @Override // android.view.ViewGroup
    public void endViewTransition(View r2) {
        this.transitioningFragmentViews.remove(r2);
        if (this.disappearingFragmentChildren.remove(r2) == false) goto L5;
        this.drawDisappearingViewsFirst = true;
    L5:
        super.endViewTransition(r2);
    }

    public final <F extends Fragment> F getFragment() {
        return (F) FragmentManager.findFragmentManager(this).findFragmentById(getId());
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets r1) {
        return r1;
    }

    @Override // android.view.ViewGroup
    public void removeAllViewsInLayout() {
        int r0 = getChildCount();
    L3:
        r0 = r0 - 1;
        if ((-1) >= r0) goto L6;
        addDisappearingFragmentView(getChildAt(r0));
        goto L3
    L6:
        super.removeAllViewsInLayout();
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View r1) {
        addDisappearingFragmentView(r1);
        super.removeView(r1);
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int r2) {
        addDisappearingFragmentView(getChildAt(r2));
        super.removeViewAt(r2);
    }

    @Override // android.view.ViewGroup
    public void removeViewInLayout(View r1) {
        addDisappearingFragmentView(r1);
        super.removeViewInLayout(r1);
    }

    @Override // android.view.ViewGroup
    public void removeViews(int r4, int r5) {
        int r0 = r4 + r5;
        int r1 = r4;
    L3:
        if (r1 >= r0) goto L5;
        addDisappearingFragmentView(getChildAt(r1));
        r1 = r1 + 1;
        goto L3
    L5:
        super.removeViews(r4, r5);
    }

    @Override // android.view.ViewGroup
    public void removeViewsInLayout(int r4, int r5) {
        int r0 = r4 + r5;
        int r1 = r4;
    L3:
        if (r1 >= r0) goto L5;
        addDisappearingFragmentView(getChildAt(r1));
        r1 = r1 + 1;
        goto L3
    L5:
        super.removeViewsInLayout(r4, r5);
    }

    @JvmName(name = "setDrawDisappearingViewsLast")
    public final void setDrawDisappearingViewsLast(boolean r1) {
        this.drawDisappearingViewsFirst = r1;
    }

    @Override // android.view.ViewGroup
    public void setLayoutTransition(LayoutTransition r1) {
        throw new UnsupportedOperationException("FragmentContainerView does not support Layout Transitions or animateLayoutChanges=\"true\".");
    }

    @Override // android.view.View
    public void setOnApplyWindowInsetsListener(View.OnApplyWindowInsetsListener r1) {
        this.applyWindowInsetsListener = r1;
    }

    @Override // android.view.ViewGroup
    public void startViewTransition(View r2) {
        if (r2.getParent() != this) goto L5;
        this.transitioningFragmentViews.add(r2);
    L5:
        super.startViewTransition(r2);
    }
}
