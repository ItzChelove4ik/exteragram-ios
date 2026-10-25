package org.telegram.ui;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import org.telegram.messenger.NotificationCenter;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes6.dex */
public class MessageEnterTransitionContainer extends View {
    private final int currentAccount;
    Runnable hideRunnable;
    private final ViewGroup parent;
    private ArrayList<Transition> transitions;

    public interface Transition {
        void onDraw(Canvas r1);
    }

    /* renamed from: $r8$lambda$-FQEq_0NpGXPkJU3UiS0BNCHEfo, reason: not valid java name */
    public static /* synthetic */ void m16108$r8$lambda$FQEq_0NpGXPkJU3UiS0BNCHEfo(MessageEnterTransitionContainer r0) {
        r0.lambda$new$0();
    }

    public MessageEnterTransitionContainer(ViewGroup r2, int r3) {
        super(r2.getContext());
        this.transitions = new ArrayList();
        this.hideRunnable = new MessageEnterTransitionContainer$$ExternalSyntheticLambda0(this);
        this.parent = r2;
        this.currentAccount = r3;
    }

    private void checkVisibility() {
        if (this.transitions.isEmpty() == false) goto L9;
        if (getVisibility() == 8) goto L9;
        NotificationCenter.getInstance(this.currentAccount).removeDelayed(this.hideRunnable);
        NotificationCenter.getInstance(this.currentAccount).doOnIdle(this.hideRunnable);
        return;
    L9:
        if (this.transitions.isEmpty() == false) goto L11;
        return;
    L11:
        if (getVisibility() == 0) goto L15;
        NotificationCenter.getInstance(this.currentAccount).removeDelayed(this.hideRunnable);
        setVisibility(0);
        return;
    }

    private /* synthetic */ void lambda$new$0() {
        setVisibility(8);
    }

    public void addTransition(Transition r2) {
        this.transitions.add(r2);
        checkVisibility();
        this.parent.invalidate();
    }

    public boolean isRunning() {
        if (this.transitions.size() <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.view.View
    public void onDraw(Canvas r3) {
        if (this.transitions.isEmpty() == true) goto L9;
        int r0 = 0;
    L7:
        if (r0 >= this.transitions.size()) goto L11;
        this.transitions.get(r0).onDraw(r3);
        r0 = r0 + 1;
        goto L7
    L11:
        return;
    }

    public void removeTransition(Transition r2) {
        this.transitions.remove(r2);
        checkVisibility();
        this.parent.invalidate();
    }
}
