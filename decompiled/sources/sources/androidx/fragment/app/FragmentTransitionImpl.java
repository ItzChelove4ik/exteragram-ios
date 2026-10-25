package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressLint({"UnknownNullness"})
/* loaded from: classes3.dex */
public abstract class FragmentTransitionImpl {

    /* renamed from: androidx.fragment.app.FragmentTransitionImpl$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ FragmentTransitionImpl this$0;
        final /* synthetic */ ArrayList val$inNames;
        final /* synthetic */ int val$numSharedElements;
        final /* synthetic */ ArrayList val$outNames;
        final /* synthetic */ ArrayList val$sharedElementsIn;
        final /* synthetic */ ArrayList val$sharedElementsOut;

        public AnonymousClass1(FragmentTransitionImpl r1, int r2, ArrayList r3, ArrayList r4, ArrayList r5, ArrayList r6) {
            this.this$0 = r1;
            this.val$numSharedElements = r2;
            this.val$sharedElementsIn = r3;
            this.val$inNames = r4;
            this.val$sharedElementsOut = r5;
            this.val$outNames = r6;
        }

        @Override // java.lang.Runnable
        public void run() {
            int r0 = 0;
        L4:
            if (r0 >= this.val$numSharedElements) goto L6;
            ViewCompat.setTransitionName((View) this.val$sharedElementsIn.get(r0), (String) this.val$inNames.get(r0));
            ViewCompat.setTransitionName((View) this.val$sharedElementsOut.get(r0), (String) this.val$outNames.get(r0));
            r0 = r0 + 1;
            goto L4
        }
    }

    public FragmentTransitionImpl() {
    }

    public static void bfsAddViewChildren(List<View> r6, View r7) {
        int r0 = r6.size();
        if (containedBeforeIndex(r6, r7, r0) == false) goto L6;
        return;
    L6:
        if (ViewCompat.getTransitionName(r7) == null) goto L8;
        r6.add(r7);
    L8:
        int r72 = r0;
    L10:
        if (r72 >= r6.size()) goto L29;
        View r1 = r6.get(r72);
        if ((r1 instanceof ViewGroup) == false) goto L21;
        ViewGroup r12 = (ViewGroup) r1;
        int r2 = r12.getChildCount();
        int r3 = 0;
    L14:
        if (r3 >= r2) goto L21;
        View r4 = r12.getChildAt(r3);
        if (containedBeforeIndex(r6, r4, r0) == true) goto L20;
        if (ViewCompat.getTransitionName(r4) == null) goto L20;
        r6.add(r4);
    L20:
        r3 = r3 + 1;
    L21:
        r72 = r72 + 1;
        goto L10
    }

    private static boolean containedBeforeIndex(List<View> r3, View r4, int r5) {
        int r1 = 0;
    L3:
        if (r1 >= r5) goto L9;
        if (r3.get(r1) == r4) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    public static boolean isNullOrEmpty(List r0) {
        if (r0 != null) goto L4;
        return true;
    L4:
        if (r0.isEmpty() == true) goto L10;
        return false;
    L10:
        return true;
    }

    public abstract void addTarget(Object r1, View r2);

    public abstract void addTargets(Object r1, ArrayList<View> r2);

    public abstract void beginDelayedTransition(ViewGroup r1, Object r2);

    public abstract boolean canHandle(Object r1);

    public abstract Object cloneTransition(Object r1);

    public void getBoundsOnScreen(View r4, Rect r5) {
        if (ViewCompat.isAttachedToWindow(r4) == true) goto L5;
        return;
    L5:
        RectF r3 = new RectF();
        r3.set(0.0f, 0.0f, r4.getWidth(), r4.getHeight());
        r4.getMatrix().mapRect(r3);
        r3.offset(r4.getLeft(), r4.getTop());
        Object r0 = r4.getParent();
    L7:
        if ((r0 instanceof View) == false) goto L9;
        View r02 = (View) r0;
        r3.offset(-r02.getScrollX(), -r02.getScrollY());
        r02.getMatrix().mapRect(r3);
        r3.offset(r02.getLeft(), r02.getTop());
        r0 = r02.getParent();
        goto L7
    L9:
        r4.getRootView().getLocationOnScreen(new int[2]);
        r3.offset(r0[0], r0[1]);
        r5.set(Math.round(r3.left), Math.round(r3.top), Math.round(r3.right), Math.round(r3.bottom));
    }

    public abstract Object mergeTransitionsInSequence(Object r1, Object r2, Object r3);

    public abstract Object mergeTransitionsTogether(Object r1, Object r2, Object r3);

    public ArrayList<String> prepareSetNameOverridesReordered(ArrayList<View> r5) {
        ArrayList<String> r4 = new ArrayList();
        int r0 = r5.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        View r2 = r5.get(r1);
        r4.add(ViewCompat.getTransitionName(r2));
        ViewCompat.setTransitionName(r2, null);
        r1 = r1 + 1;
        goto L3
    L5:
        return r4;
    }

    public abstract void scheduleHideFragmentView(Object r1, View r2, ArrayList<View> r3);

    public abstract void scheduleRemoveTargets(Object r1, Object r2, ArrayList<View> r3, Object r4, ArrayList<View> r5, Object r6, ArrayList<View> r7);

    public abstract void setEpicenter(Object r1, Rect r2);

    public abstract void setEpicenter(Object r1, View r2);

    public abstract void setListenerForTransitionEnd(Fragment r1, Object r2, CancellationSignal r3, Runnable r4);

    public void setNameOverridesReordered(View r9, ArrayList<View> r10, ArrayList<View> r11, ArrayList<String> r12, Map<String, String> r13) {
        int r2 = r11.size();
        ArrayList r6 = new ArrayList();
        int r1 = 0;
    L3:
        if (r1 >= r2) goto L14;
        View r3 = r10.get(r1);
        String r4 = ViewCompat.getTransitionName(r3);
        r6.add(r4);
        if (r4 == null) goto L13;
        ViewCompat.setTransitionName(r3, null);
        String r32 = r13.get(r4);
        int r5 = 0;
    L8:
        if (r5 >= r2) goto L13;
        if (r32.equals(r12.get(r5)) == true) goto L11;
        r5 = r5 + 1;
        goto L8
    L11:
        ViewCompat.setTransitionName(r11.get(r5), r4);
    L13:
        r1 = r1 + 1;
        goto L3
    L14:
        OneShotPreDrawListener.add(r9, new AnonymousClass1(this, r2, r11, r12, r10, r6));
    }

    public abstract void setSharedElementTargets(Object r1, View r2, ArrayList<View> r3);

    public abstract void swapSharedElementTargets(Object r1, ArrayList<View> r2, ArrayList<View> r3);

    public abstract Object wrapTransitionInSet(Object r1);
}
