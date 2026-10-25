package androidx.fragment.app;

import android.view.ViewGroup;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.lifecycle.Lifecycle;
import c.f$$ExternalSyntheticBUOutline1;
import com.sun.jna.Native$$ExternalSyntheticBUOutline3;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import kotlin.properties.NotNullVar$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public abstract class FragmentTransaction {
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    private final ClassLoader mClassLoader;
    ArrayList<Runnable> mCommitRunnables;
    int mEnterAnim;
    int mExitAnim;
    private final FragmentFactory mFragmentFactory;
    String mName;
    ArrayList<Op> mOps;
    int mPopEnterAnim;
    int mPopExitAnim;
    boolean mReorderingAllowed;
    ArrayList<String> mSharedElementSourceNames;
    ArrayList<String> mSharedElementTargetNames;
    int mTransition;

    public static final class Op {
        int mCmd;
        Lifecycle.State mCurrentMaxState;
        int mEnterAnim;
        int mExitAnim;
        Fragment mFragment;
        boolean mFromExpandedOp;
        Lifecycle.State mOldMaxState;
        int mPopEnterAnim;
        int mPopExitAnim;

        public Op() {
        }

        public Op(int r1, Fragment r2) {
            this.mCmd = r1;
            this.mFragment = r2;
            this.mFromExpandedOp = false;
            Lifecycle.State r12 = Lifecycle.State.RESUMED;
            this.mOldMaxState = r12;
            this.mCurrentMaxState = r12;
        }

        public Op(int r1, Fragment r2, boolean r3) {
            this.mCmd = r1;
            this.mFragment = r2;
            this.mFromExpandedOp = r3;
            Lifecycle.State r12 = Lifecycle.State.RESUMED;
            this.mOldMaxState = r12;
            this.mCurrentMaxState = r12;
        }
    }

    public FragmentTransaction(FragmentFactory r2, ClassLoader r3) {
        this.mOps = new ArrayList();
        this.mAllowAddToBackStack = true;
        this.mReorderingAllowed = false;
        this.mFragmentFactory = r2;
        this.mClassLoader = r3;
    }

    public FragmentTransaction add(int r2, Fragment r3, String r4) {
        doAddOp(r2, r3, r4, 1);
        return this;
    }

    public FragmentTransaction add(ViewGroup r1, Fragment r2, String r3) {
        r2.mContainer = r1;
        return add(r1.getId(), r2, r3);
    }

    public FragmentTransaction add(Fragment r3, String r4) {
        doAddOp(0, r3, r4, 1);
        return this;
    }

    public void addOp(Op r2) {
        this.mOps.add(r2);
        r2.mEnterAnim = this.mEnterAnim;
        r2.mExitAnim = this.mExitAnim;
        r2.mPopEnterAnim = this.mPopEnterAnim;
        r2.mPopExitAnim = this.mPopExitAnim;
    }

    public abstract int commit();

    public abstract int commitAllowingStateLoss();

    public abstract void commitNow();

    public abstract void commitNowAllowingStateLoss();

    public FragmentTransaction disallowAddToBackStack() {
        if (this.mAddToBackStack == true) goto L6;
        this.mAllowAddToBackStack = false;
        return this;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("This transaction is already being added to the back stack");
        return null;
    }

    public void doAddOp(int r4, Fragment r5, String r6, int r7) {
        String r0 = r5.mPreviousWho;
        if (r0 == null) goto L5;
        FragmentStrictMode.onFragmentReuse(r5, r0);
    L5:
        Class<?> r02 = r5.getClass();
        int r1 = r02.getModifiers();
        if (r02.isAnonymousClass() == false) goto L8;
    L37:
        NotNullVar$$ExternalSyntheticBUOutline0.m("Fragment ", r02.getCanonicalName(), " must be a public static class to be  properly recreated from instance state.");
        return;
    L8:
        if (Modifier.isPublic(r1) == false) goto L37;
        if (r02.isMemberClass() == false) goto L14;
        if (Modifier.isStatic(r1) == false) goto L37;
    L14:
        if (r6 == null) goto L23;
        String r2 = r5.mTag;
        if (r2 != null) goto L18;
    L22:
        r5.mTag = r6;
        goto L23
    L18:
        if (r6.equals(r2) == true) goto L22;
        StringBuilder r42 = new StringBuilder("Can't change tag of fragment ");
        r42.append(r5);
        String r52 = r5.mTag;
        r42.append(": was ");
        r42.append(r52);
        r42.append(" now ");
        r42.append(r6);
        throw new IllegalStateException(r42.toString());
    L23:
        if (r4 != 0) goto L25;
    L35:
        addOp(new Op(r7, r5));
        return;
    L25:
        if (r4 == (-1)) goto L33;
        int r62 = r5.mFragmentId;
        if (r62 == 0) goto L32;
        if (r62 == r4) goto L32;
        StringBuilder r63 = new StringBuilder("Can't change container ID of fragment ");
        r63.append(r5);
        int r53 = r5.mFragmentId;
        r63.append(": was ");
        r63.append(r53);
        r63.append(" now ");
        r63.append(r4);
        throw new IllegalStateException(r63.toString());
    L32:
        r5.mFragmentId = r4;
        r5.mContainerId = r4;
        goto L35
    L33:
        Native$$ExternalSyntheticBUOutline3.m("Can't add fragment ", r5, " with tag ", r6, " to container view with no id");
    }

    public FragmentTransaction remove(Fragment r3) {
        addOp(new Op(3, r3));
        return this;
    }

    public FragmentTransaction replace(int r2, Fragment r3, String r4) {
        if (r2 == 0) goto L5;
        doAddOp(r2, r3, r4, 2);
        return this;
    L5:
        f$$ExternalSyntheticBUOutline1.m("Must use non-zero containerViewId");
        return null;
    }

    public FragmentTransaction setReorderingAllowed(boolean r1) {
        this.mReorderingAllowed = r1;
        return this;
    }
}
