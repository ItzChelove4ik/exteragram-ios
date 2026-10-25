package org.telegram.ui.Components.inset;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Iterator;
import java.util.List;
import me.vkryl.core.BitwiseUtils;
import me.vkryl.core.reference.ReferenceList;
import org.telegram.ui.Components.chat.ViewPositionWatcher;

/* loaded from: classes3.dex */
public class WindowAnimatedInsetsProvider extends WindowInsetsAnimationCompat.Callback {
    private static final PointF tmpPointF = null;
    private static final Rect tmpRect = null;
    private static final RectF tmpRectF = null;
    private int activeAnimationsCounter;
    private final ReferenceList<Listener> listeners;
    private final ViewGroup root;

    public interface Listener {
        View getAnimatedInsetsTargetView();

        void onAnimatedInsetsChanged(View r1, WindowInsetsCompat r2);

        default void onAnimatedInsetsFinished() {
        }

        default void onAnimatedInsetsStarted() {
        }
    }

    static {
        tmpPointF = new PointF();
        tmpRectF = new RectF();
        tmpRect = new Rect();
    }

    public WindowAnimatedInsetsProvider(ViewGroup r2) {
        super(0);
        this.listeners = new ReferenceList();
        this.root = r2;
        ViewCompat.setWindowInsetsAnimationCallback(r2, this);
    }

    public static WindowInsetsCompat calculateWindowInsets(View r2) {
        return calculateWindowInsets(ViewCompat.getRootWindowInsets(r2), r2, r2.getRootView());
    }

    public static WindowInsetsCompat calculateWindowInsets(WindowInsetsCompat r4, View r5, View r6) {
        if (r5 == null) goto L16;
        if (r6 == null) goto L18;
        if (r4 == null) goto L19;
        RectF r0 = tmpRectF;
        if (ViewPositionWatcher.computeRectInParent(r5, r6, r0) == false) goto L20;
        Rect r52 = tmpRect;
        r0.round(r52);
        int r02 = r52.left;
        int r1 = r52.top;
        int r2 = r6.getWidth() - r52.right;
        int r62 = r6.getHeight() - r52.bottom;
        if (r02 != 0) goto L15;
        if (r1 != 0) goto L15;
        if (r2 != 0) goto L15;
        if (r62 != 0) goto L15;
        return r4;
    L15:
        return r4.inset(Math.max(0, r02), Math.max(0, r1), Math.max(0, r2), Math.max(0, r62));
    L20:
        return null;
    L19:
        return null;
    L18:
        return null;
    L16:
        return null;
    }

    private void dispatchWindowInsetsAnimationChange(WindowInsetsCompat r5) {
        Iterator<Listener> r0 = this.listeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        Listener r1 = r0.next();
        View r2 = r1.getAnimatedInsetsTargetView();
        WindowInsetsCompat r3 = calculateWindowInsets(r5, r2, this.root);
        if (r3 == null) goto L4;
        r1.onAnimatedInsetsChanged(r2, r3);
        goto L4
    }

    private void dispatchWindowInsetsAnimationFinish() {
        Iterator<Listener> r1 = this.listeners.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().onAnimatedInsetsFinished();
        goto L4
    }

    private void dispatchWindowInsetsAnimationStart() {
        Iterator<Listener> r1 = this.listeners.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().onAnimatedInsetsStarted();
        goto L4
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public void onEnd(WindowInsetsAnimationCompat r1) {
        super.onEnd(r1);
        int r12 = this.activeAnimationsCounter - 1;
        this.activeAnimationsCounter = r12;
        if (r12 != 0) goto L6;
        dispatchWindowInsetsAnimationFinish();
        return;
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public WindowInsetsCompat onProgress(WindowInsetsCompat r3, List<WindowInsetsAnimationCompat> r4) {
        Iterator<WindowInsetsAnimationCompat> r42 = r4.iterator();
        int r0 = 0;
    L4:
        if (r42.hasNext() == false) goto L7;
        r0 = r0 | r42.next().getTypeMask();
        goto L4
    L7:
        if (BitwiseUtils.hasFlag(r0, WindowInsetsCompat.Type.ime()) == false) goto L9;
        dispatchWindowInsetsAnimationChange(r3);
    L9:
        return r3;
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public WindowInsetsAnimationCompat.BoundsCompat onStart(WindowInsetsAnimationCompat r2, WindowInsetsAnimationCompat.BoundsCompat r3) {
        if (this.activeAnimationsCounter != 0) goto L5;
        dispatchWindowInsetsAnimationStart();
    L5:
        this.activeAnimationsCounter++;
        return super.onStart(r2, r3);
    }

    public void subscribeToWindowInsetsAnimation(Listener r1) {
        this.listeners.add(r1);
    }
}
