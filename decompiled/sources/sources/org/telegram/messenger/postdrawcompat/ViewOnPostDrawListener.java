package org.telegram.messenger.postdrawcompat;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import org.telegram.messenger.R;

/* loaded from: classes3.dex */
public abstract class ViewOnPostDrawListener {

    public static final class RootState {
        final ViewOnPostDraw viewOnPostDraw;

        public RootState(ViewOnPostDraw r1) {
            this.viewOnPostDraw = r1;
        }
    }

    public static final class ViewState {
        final View.OnAttachStateChangeListener attachStateChangeListener;
        final ArrayList<OnPostDrawListener> listeners;
        RootState rootState;

        /* renamed from: org.telegram.messenger.postdrawcompat.ViewOnPostDrawListener$ViewState$1, reason: invalid class name */
        public class AnonymousClass1 implements View.OnAttachStateChangeListener {
            final /* synthetic */ ViewState this$0;

            public AnonymousClass1(ViewState r1) {
                this.this$0 = r1;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View r1) {
                ViewState r0 = ViewOnPostDrawListener.m5169$$Nest$smgetViewState(r1);
                if (r0 == null) goto L6;
                ViewOnPostDrawListener.m5167$$Nest$smattachAllListeners(r1, r0);
                return;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View r1) {
                ViewState r0 = ViewOnPostDrawListener.m5169$$Nest$smgetViewState(r1);
                if (r0 == null) goto L6;
                ViewOnPostDrawListener.m5168$$Nest$smdetachAllListeners(r0);
                return;
            }
        }

        private ViewState() {
            this.listeners = new ArrayList();
            this.attachStateChangeListener = new AnonymousClass1(this);
        }

        public /* synthetic */ ViewState(ViewOnPostDrawListenerIA r1) {
            this();
        }
    }

    /* renamed from: -$$Nest$smattachAllListeners, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5167$$Nest$smattachAllListeners(View r0, ViewState r1) {
        attachAllListeners(r0, r1);
    }

    /* renamed from: -$$Nest$smdetachAllListeners, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5168$$Nest$smdetachAllListeners(ViewState r0) {
        detachAllListeners(r0);
    }

    /* renamed from: -$$Nest$smgetViewState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewState m5169$$Nest$smgetViewState(View r0) {
        return getViewState(r0);
    }

    public static void addListener(View r2, OnPostDrawListener r3) {
        if (r2 == null) goto L24;
        if (r3 != null) goto L6;
        return;
    L6:
        if (r2.isAttachedToWindow() == true) goto L8;
    L12:
        ViewState r0 = getViewState(r2);
        if (r0 != null) goto L16;
        r0 = new ViewState(null);
        r2.setTag(R.id.tag_view_on_post_draw_state, r0);
        r2.addOnAttachStateChangeListener(r0.attachStateChangeListener);
    L16:
        if (r0.listeners.contains(r3) == true) goto L28;
        r0.listeners.add(r3);
        if (r2.isAttachedToWindow() == false) goto L25;
        RootState r22 = attachToRoot(r2, r0);
        if (r22 == null) goto L26;
        r22.viewOnPostDraw.addOnPostDrawListener(r3);
        return;
    L26:
        return;
    L25:
        return;
    L28:
        return;
    L8:
        if (r2 != r2.getRootView()) goto L12;
        f$$ExternalSyntheticBUOutline1.m("Cannot add OnPostDrawListener to root view");
        return;
    }

    private static void attachAllListeners(View r4, ViewState r5) {
        RootState r42 = attachToRoot(r4, r5);
        if (r42 == null) goto L8;
        ArrayList<OnPostDrawListener> r52 = r5.listeners;
        int r0 = r52.size();
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L10;
        OnPostDrawListener r2 = r52.get(r1);
        r1 = r1 + 1;
        ViewOnPostDraw r3 = r42.viewOnPostDraw;
        r3.addOnPostDrawListener(r2);
        goto L6
    L10:
        return;
    }

    private static RootState attachToRoot(View r5, ViewState r6) {
        RootState r0 = r6.rootState;
        if (r0 == null) goto L5;
        return r0;
    L5:
        View r02 = r5.getRootView();
        if (r5 != r02) goto L8;
        f$$ExternalSyntheticBUOutline1.m("Cannot add OnPostDrawListener to root view");
        return null;
    L8:
        if ((r02 instanceof ViewGroup) == true) goto L10;
        return null;
    L10:
        ViewGroup r03 = (ViewGroup) r02;
        RootState r52 = getRootState(r03);
        if (r52 != null) goto L17;
        ViewOnPostDraw r53 = new ViewOnPostDraw(r03.getContext());
        RootState r1 = new RootState(r53);
        r03.setTag(R.id.tag_view_on_post_draw_root_state, r1);
        if ((r03 instanceof FrameLayout) == false) goto L15;
        r03.addView(r53, new FrameLayout.LayoutParams(1, 1, 17));
    L16:
        r52 = r1;
        goto L17
    L15:
        r03.addView(r53, new ViewGroup.LayoutParams(1, 1));
    L17:
        r6.rootState = r52;
        return r52;
    }

    private static void detachAllListeners(ViewState r6) {
        RootState r0 = r6.rootState;
        if (r0 != null) goto L5;
        return;
    L5:
        ArrayList<OnPostDrawListener> r1 = r6.listeners;
        int r2 = r1.size();
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L8;
        OnPostDrawListener r4 = r1.get(r3);
        r3 = r3 + 1;
        ViewOnPostDraw r5 = r0.viewOnPostDraw;
        r5.removeOnPostDrawListener(r4);
        goto L6
    L8:
        r6.rootState = null;
    }

    private static RootState getRootState(ViewGroup r1) {
        return (RootState) r1.getTag(R.id.tag_view_on_post_draw_root_state);
    }

    private static ViewState getViewState(View r1) {
        return (ViewState) r1.getTag(R.id.tag_view_on_post_draw_state);
    }

    public static void removeListener(View r2, OnPostDrawListener r3) {
        if (r2 == null) goto L17;
        if (r3 == null) goto L20;
        ViewState r0 = getViewState(r2);
        if (r0 != null) goto L8;
        return;
    L8:
        if (r0.listeners.remove(r3) == false) goto L21;
        RootState r1 = r0.rootState;
        if (r1 == null) goto L14;
        r1.viewOnPostDraw.removeOnPostDrawListener(r3);
    L14:
        if (r0.listeners.isEmpty() == false) goto L19;
        r0.rootState = null;
        r2.removeOnAttachStateChangeListener(r0.attachStateChangeListener);
        r2.setTag(R.id.tag_view_on_post_draw_state, null);
        return;
    L19:
        return;
    L21:
        return;
    L20:
        return;
    }
}
