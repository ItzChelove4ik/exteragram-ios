package androidx.core.view;

import android.annotation.SuppressLint;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class MenuHostHelper {
    private final CopyOnWriteArrayList<MenuProvider> mMenuProviders;
    private final Runnable mOnInvalidateMenuCallback;
    private final Map<MenuProvider, LifecycleContainer> mProviderToLifecycleContainers;

    /* loaded from: classes3.dex */
    public static class LifecycleContainer {
        final Lifecycle mLifecycle;
        private LifecycleEventObserver mObserver;

        public LifecycleContainer(Lifecycle r1, LifecycleEventObserver r2) {
            this.mLifecycle = r1;
            this.mObserver = r2;
            r1.addObserver(r2);
        }

        public void clearObservers() {
            this.mLifecycle.removeObserver(this.mObserver);
            this.mObserver = null;
        }
    }

    public static /* synthetic */ void $r8$lambda$L4bp1I_Mq82BF4CTDbjIos9_dFE(MenuHostHelper r0, Lifecycle.State r1, MenuProvider r2, LifecycleOwner r3, Lifecycle.Event r4) {
        r0.getClass();
        if (r4 != Lifecycle.Event.upTo(r1)) goto L7;
        r0.addMenuProvider(r2);
        return;
    L7:
        if (r4 != Lifecycle.Event.ON_DESTROY) goto L11;
        r0.removeMenuProvider(r2);
        return;
    L11:
        if (r4 != Lifecycle.Event.downFrom(r1)) goto L14;
        r0.mMenuProviders.remove(r2);
        r0.mOnInvalidateMenuCallback.run();
        return;
    }

    /* renamed from: $r8$lambda$zr_Wn7n1nYo-eZCJzTn8vTFGOUc, reason: not valid java name */
    public static /* synthetic */ void m670$r8$lambda$zr_Wn7n1nYoeZCJzTn8vTFGOUc(MenuHostHelper r0, MenuProvider r1, LifecycleOwner r2, Lifecycle.Event r3) {
        r0.getClass();
        if (r3 != Lifecycle.Event.ON_DESTROY) goto L6;
        r0.removeMenuProvider(r1);
        return;
    }

    public MenuHostHelper(Runnable r2) {
        this.mMenuProviders = new CopyOnWriteArrayList();
        this.mProviderToLifecycleContainers = new HashMap();
        this.mOnInvalidateMenuCallback = r2;
    }

    public void addMenuProvider(MenuProvider r2) {
        this.mMenuProviders.add(r2);
        this.mOnInvalidateMenuCallback.run();
    }

    public void addMenuProvider(MenuProvider r3, LifecycleOwner r4) {
        addMenuProvider(r3);
        Lifecycle r42 = r4.getLifecycle();
        LifecycleContainer r0 = this.mProviderToLifecycleContainers.remove(r3);
        if (r0 == null) goto L5;
        r0.clearObservers();
    L5:
        MenuHostHelper$$ExternalSyntheticLambda1 r02 = new MenuHostHelper$$ExternalSyntheticLambda1(this, r3);
        this.mProviderToLifecycleContainers.put(r3, new LifecycleContainer(r42, r02));
    }

    @SuppressLint({"LambdaLast"})
    public void addMenuProvider(MenuProvider r2, LifecycleOwner r3, Lifecycle.State r4) {
        Lifecycle r32 = r3.getLifecycle();
        LifecycleContainer r0 = this.mProviderToLifecycleContainers.remove(r2);
        if (r0 == null) goto L5;
        r0.clearObservers();
    L5:
        MenuHostHelper$$ExternalSyntheticLambda0 r02 = new MenuHostHelper$$ExternalSyntheticLambda0(this, r4, r2);
        this.mProviderToLifecycleContainers.put(r2, new LifecycleContainer(r32, r02));
    }

    public void onCreateMenu(Menu r2, MenuInflater r3) {
        Iterator<MenuProvider> r1 = this.mMenuProviders.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().onCreateMenu(r2, r3);
        goto L4
    }

    public void onMenuClosed(Menu r2) {
        Iterator<MenuProvider> r1 = this.mMenuProviders.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().onMenuClosed(r2);
        goto L4
    }

    public boolean onMenuItemSelected(MenuItem r2) {
        Iterator<MenuProvider> r1 = this.mMenuProviders.iterator();
    L4:
        if (r1.hasNext() == false) goto L9;
        if (r1.next().onMenuItemSelected(r2) == false) goto L4;
        return true;
    L9:
        return false;
    }

    public void onPrepareMenu(Menu r2) {
        Iterator<MenuProvider> r1 = this.mMenuProviders.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().onPrepareMenu(r2);
        goto L4
    }

    public void removeMenuProvider(MenuProvider r2) {
        this.mMenuProviders.remove(r2);
        LifecycleContainer r22 = this.mProviderToLifecycleContainers.remove(r2);
        if (r22 == null) goto L5;
        r22.clearObservers();
    L5:
        this.mOnInvalidateMenuCallback.run();
    }
}
