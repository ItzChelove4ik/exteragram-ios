package androidx.car.app;

import android.util.Log;
import androidx.car.app.managers.Manager;
import androidx.car.app.model.TemplateWrapper;
import androidx.car.app.utils.ThreadUtils;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public class ScreenManager implements Manager {
    private final Lifecycle mAppLifecycle;
    private final CarContext mCarContext;
    private final Deque<Screen> mScreenStack;

    public class LifecycleObserverImpl implements DefaultLifecycleObserver {
        final /* synthetic */ ScreenManager this$0;

        public LifecycleObserverImpl(ScreenManager r1) {
            this.this$0 = r1;
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onCreate(LifecycleOwner r1) {
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onDestroy(LifecycleOwner r2) {
            this.this$0.destroyAndClearScreenStack();
            r2.getLifecycle().removeObserver(this);
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onPause(LifecycleOwner r1) {
            Screen r0 = this.this$0.getScreenStackInternal().peek();
            if (r0 != null) goto L6;
            Log.e("CarApp", "Screen stack was empty during lifecycle onPause");
            return;
        L6:
            r0.dispatchLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onResume(LifecycleOwner r1) {
            Screen r0 = this.this$0.getScreenStackInternal().peek();
            if (r0 != null) goto L6;
            Log.e("CarApp", "Screen stack was empty during lifecycle onResume");
            return;
        L6:
            r0.dispatchLifecycleEvent(Lifecycle.Event.ON_RESUME);
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onStart(LifecycleOwner r1) {
            Screen r0 = this.this$0.getScreenStackInternal().peek();
            if (r0 != null) goto L6;
            Log.e("CarApp", "Screen stack was empty during lifecycle onStart");
            return;
        L6:
            r0.dispatchLifecycleEvent(Lifecycle.Event.ON_START);
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver
        public void onStop(LifecycleOwner r1) {
            Screen r0 = this.this$0.getScreenStackInternal().peek();
            if (r0 != null) goto L6;
            Log.e("CarApp", "Screen stack was empty during lifecycle onStop");
            return;
        L6:
            r0.dispatchLifecycleEvent(Lifecycle.Event.ON_STOP);
        }
    }

    public ScreenManager(CarContext r2, Lifecycle r3) {
        this.mScreenStack = new ArrayDeque();
        this.mCarContext = r2;
        this.mAppLifecycle = r3;
        r3.addObserver(new LifecycleObserverImpl(this));
    }

    public static ScreenManager create(CarContext r1, Lifecycle r2) {
        return new ScreenManager(r1, r2);
    }

    private void moveToTop(Screen r3) {
        Screen r0 = this.mScreenStack.peek();
        if (r0 == null) goto L10;
        if (r0 == r3) goto L12;
        this.mScreenStack.remove(r3);
        pushAndStart(r3, false);
        stop(r0, false);
        if (this.mAppLifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED) == false) goto L11;
        r3.dispatchLifecycleEvent(Lifecycle.Event.ON_RESUME);
        return;
    L11:
        return;
    L12:
        return;
    }

    private void popInternal(List<Screen> r7) {
        Screen r0 = getTop();
        r0.setUseLastTemplateId(true);
        ((AppManager) this.mCarContext.getCarService(AppManager.class)).invalidate();
        if (this.mAppLifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED) == false) goto L5;
        r0.dispatchLifecycleEvent(Lifecycle.Event.ON_START);
    L5:
        Iterator<Screen> r72 = r7.iterator();
    L7:
        if (r72.hasNext() == false) goto L13;
        Screen r2 = r72.next();
        if (Log.isLoggable("CarApp", 3) == false) goto L11;
        Log.d("CarApp", "Popping screen " + r2 + " off the screen stack");
    L11:
        stop(r2, true);
        goto L7
    L13:
        if (Log.isLoggable("CarApp", 3) == false) goto L16;
        Log.d("CarApp", "Screen " + r0 + " is at the top of the screen stack");
    L16:
        if (this.mAppLifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED) == true) goto L18;
        return;
    L18:
        if (this.mScreenStack.contains(r0) == false) goto L25;
        r0.dispatchLifecycleEvent(Lifecycle.Event.ON_RESUME);
        return;
    }

    private void pushAndStart(Screen r2, boolean r3) {
        this.mScreenStack.push(r2);
        if (r3 == false) goto L8;
        if (this.mAppLifecycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED) == false) goto L8;
        r2.dispatchLifecycleEvent(Lifecycle.Event.ON_CREATE);
    L8:
        if (r2.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED) == true) goto L11;
        return;
    L11:
        if (this.mAppLifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED) == false) goto L14;
        ((AppManager) this.mCarContext.getCarService(AppManager.class)).invalidate();
        r2.dispatchLifecycleEvent(Lifecycle.Event.ON_START);
        return;
    }

    private void pushInternal(Screen r4) {
        if (Log.isLoggable("CarApp", 3) == false) goto L6;
        Log.d("CarApp", "Pushing screen " + r4 + " to the top of the screen stack");
    L6:
        if (this.mScreenStack.contains(r4) == false) goto L9;
        moveToTop(r4);
        return;
    L9:
        Screen r0 = this.mScreenStack.peek();
        pushAndStart(r4, true);
        if (this.mScreenStack.contains(r4) == false) goto L19;
        if (r0 == null) goto L15;
        stop(r0, false);
    L15:
        if (this.mAppLifecycle.getCurrentState().isAtLeast(Lifecycle.State.RESUMED) == false) goto L18;
        r4.dispatchLifecycleEvent(Lifecycle.Event.ON_RESUME);
        return;
    L18:
        return;
    }

    private void stop(Screen r2, boolean r3) {
        Lifecycle.State r1 = r2.getLifecycle().getCurrentState();
        if (r1.isAtLeast(Lifecycle.State.RESUMED) == false) goto L6;
        r2.dispatchLifecycleEvent(Lifecycle.Event.ON_PAUSE);
    L6:
        if (r1.isAtLeast(Lifecycle.State.STARTED) == false) goto L8;
        r2.dispatchLifecycleEvent(Lifecycle.Event.ON_STOP);
    L8:
        if (r3 == false) goto L11;
        r2.dispatchLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        return;
    }

    public void destroyAndClearScreenStack() {
        Iterator r0 = new ArrayDeque(this.mScreenStack).iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        stop((Screen) r0.next(), true);
        goto L4
    L6:
        this.mScreenStack.clear();
    }

    public Deque<Screen> getScreenStackInternal() {
        return this.mScreenStack;
    }

    public Screen getTop() {
        ThreadUtils.checkMainThread();
        Screen r0 = this.mScreenStack.peek();
        Objects.requireNonNull(r0);
        return r0;
    }

    public TemplateWrapper getTopTemplate() {
        ThreadUtils.checkMainThread();
        Screen r0 = getTop();
        if (Log.isLoggable("CarApp", 3) == false) goto L5;
        Log.d("CarApp", "Requesting template from Screen " + r0);
    L5:
        TemplateWrapper r02 = r0.getTemplateWrapper();
        ArrayList r1 = new ArrayList();
        Iterator<Screen> r4 = this.mScreenStack.iterator();
    L7:
        if (r4.hasNext() == false) goto L9;
        r1.add(r4.next().getLastTemplateInfo());
        goto L7
    L9:
        r02.setTemplateInfosForScreenStack(r1);
        return r02;
    }

    public void pop() {
        ThreadUtils.checkMainThread();
        if (this.mAppLifecycle.getCurrentState().equals(Lifecycle.State.DESTROYED) == false) goto L9;
        if (Log.isLoggable("CarApp", 3) == false) goto L12;
        Log.d("CarApp", "Popping screens after the DESTROYED state is a no-op");
        return;
    L12:
        return;
    L9:
        if (this.mScreenStack.size() <= 1) goto L13;
        popInternal(Collections.singletonList(this.mScreenStack.pop()));
        return;
    }

    public void push(Screen r3) {
        ThreadUtils.checkMainThread();
        if (this.mAppLifecycle.getCurrentState().equals(Lifecycle.State.DESTROYED) == true) goto L5;
        Objects.requireNonNull(r3);
        pushInternal(r3);
        return;
    L5:
        if (Log.isLoggable("CarApp", 3) == false) goto L10;
        Log.d("CarApp", "Pushing screens after the DESTROYED state is a no-op");
        return;
    }
}
