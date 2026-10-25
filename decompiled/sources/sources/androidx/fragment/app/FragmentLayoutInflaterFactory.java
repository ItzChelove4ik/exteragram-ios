package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.R$styleable;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.lifecycle.LiveData$$ExternalSyntheticBUOutline0;
import com.sun.jna.Library$Handler$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
class FragmentLayoutInflaterFactory implements LayoutInflater.Factory2 {
    final FragmentManager mFragmentManager;

    /* renamed from: androidx.fragment.app.FragmentLayoutInflaterFactory$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ FragmentLayoutInflaterFactory this$0;
        final /* synthetic */ FragmentStateManager val$fragmentStateManager;

        public AnonymousClass1(FragmentLayoutInflaterFactory r1, FragmentStateManager r2) {
            this.this$0 = r1;
            this.val$fragmentStateManager = r2;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r2) {
            Fragment r22 = this.val$fragmentStateManager.getFragment();
            this.val$fragmentStateManager.moveToExpectedState();
            SpecialEffectsController.getOrCreateController((ViewGroup) r22.mView.getParent(), this.this$0.mFragmentManager).forceCompleteAllOperations();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
        }
    }

    public FragmentLayoutInflaterFactory(FragmentManager r1) {
        this.mFragmentManager = r1;
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View r10, String r11, Context r12, AttributeSet r13) {
        if (FragmentContainerView.class.getName().equals(r11) == false) goto L7;
        return new FragmentContainerView(r12, r13, this.mFragmentManager);
    L7:
        if ("fragment".equals(r11) == true) goto L9;
        return null;
    L9:
        String r112 = r13.getAttributeValue(null, "class");
        TypedArray r1 = r12.obtainStyledAttributes(r13, R$styleable.Fragment);
        if (r112 != null) goto L12;
        r112 = r1.getString(R$styleable.Fragment_android_name);
    L12:
        int r2 = r1.getResourceId(R$styleable.Fragment_android_id, -1);
        String r4 = r1.getString(R$styleable.Fragment_android_tag);
        r1.recycle();
        if (r112 != null) goto L15;
    L62:
        return null;
    L15:
        if (FragmentFactory.isFragmentClass(r12.getClassLoader(), r112) == false) goto L62;
        if (r10 == null) goto L19;
        int r14 = r10.getId();
    L20:
        if (r14 != (-1)) goto L26;
        if (r2 != (-1)) goto L26;
        if (r4 != null) goto L26;
        Library$Handler$$ExternalSyntheticBUOutline0.m(r13.getPositionDescription(), ": Must specify unique android:id, android:tag, or have a parent with an id for ", r112);
        return null;
    L26:
        if (r2 == (-1)) goto L28;
        Fragment r5 = this.mFragmentManager.findFragmentById(r2);
    L29:
        if (r5 != null) goto L32;
        if (r4 == null) goto L32;
        r5 = this.mFragmentManager.findFragmentByTag(r4);
    L32:
        if (r5 != null) goto L36;
        if (r14 == (-1)) goto L36;
        r5 = this.mFragmentManager.findFragmentById(r14);
    L36:
        if (r5 != null) goto L45;
        r5 = this.mFragmentManager.getFragmentFactory().instantiate(r12.getClassLoader(), r112);
        r5.mFromLayout = true;
        if (r2 == 0) goto L40;
        int r122 = r2;
    L41:
        r5.mFragmentId = r122;
        r5.mContainerId = r14;
        r5.mTag = r4;
        r5.mInLayout = true;
        FragmentManager r123 = this.mFragmentManager;
        r5.mFragmentManager = r123;
        r5.mHost = r123.getHost();
        r5.onInflate(this.mFragmentManager.getHost().getContext(), r13, r5.mSavedFragmentState);
        FragmentStateManager r124 = this.mFragmentManager.addFragment(r5);
        if (FragmentManager.isLoggingEnabled(2) == false) goto L49;
        Log.v("FragmentManager", "Fragment " + r5 + " has been inflated via the <fragment> tag: id=0x" + Integer.toHexString(r2));
    L49:
        ViewGroup r102 = (ViewGroup) r10;
        FragmentStrictMode.onFragmentTagUsage(r5, r102);
        r5.mContainer = r102;
        r124.moveToExpectedState();
        r124.ensureInflatedView();
        View r103 = r5.mView;
        if (r103 == null) goto L58;
        if (r2 == 0) goto L54;
        r103.setId(r2);
    L54:
        if (r5.mView.getTag() != null) goto L56;
        r5.mView.setTag(r4);
    L56:
        r5.mView.addOnAttachStateChangeListener(new AnonymousClass1(this, r124));
        return r5.mView;
    L58:
        LiveData$$ExternalSyntheticBUOutline0.m("Fragment ", r112, " did not create a view.");
        return null;
    L40:
        r122 = r14;
        goto L41
    L45:
        if (r5.mInLayout == true) goto L61;
        r5.mInLayout = true;
        FragmentManager r125 = this.mFragmentManager;
        r5.mFragmentManager = r125;
        r5.mHost = r125.getHost();
        r5.onInflate(this.mFragmentManager.getHost().getContext(), r13, r5.mSavedFragmentState);
        r124 = this.mFragmentManager.createOrGetFragmentStateManager(r5);
        if (FragmentManager.isLoggingEnabled(2) == false) goto L49;
        Log.v("FragmentManager", "Retained Fragment " + r5 + " has been re-attached via the <fragment> tag: id=0x" + Integer.toHexString(r2));
        goto L49
    L61:
        throw new IllegalArgumentException(r13.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(r2) + ", tag " + r4 + ", or parent id 0x" + Integer.toHexString(r14) + " with another fragment for " + r112);
    L28:
        r5 = null;
        goto L29
    L19:
        r14 = 0;
        goto L20
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String r2, Context r3, AttributeSet r4) {
        return onCreateView(null, r2, r3, r4);
    }
}
