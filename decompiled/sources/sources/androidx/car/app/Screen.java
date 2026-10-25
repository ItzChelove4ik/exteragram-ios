package androidx.car.app;

import android.util.Log;
import androidx.car.app.model.Template;
import androidx.car.app.model.TemplateInfo;
import androidx.car.app.model.TemplateWrapper;
import androidx.car.app.utils.ThreadUtils;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import java.util.Objects;

/* loaded from: classes3.dex */
public abstract class Screen implements LifecycleOwner {
    private final CarContext mCarContext;
    private final LifecycleRegistry mLifecycleRegistry;
    private OnScreenResultListener mOnScreenResultListener;
    private Object mResult;
    private TemplateWrapper mTemplateWrapper;
    private boolean mUseLastTemplateId;

    /* renamed from: $r8$lambda$1fy9bSyOIB5ckm4-u8XzevkC8wY, reason: not valid java name */
    public static /* synthetic */ void m637$r8$lambda$1fy9bSyOIB5ckm4u8XzevkC8wY(Screen r2, Lifecycle.Event r3) {
        if (r2.mLifecycleRegistry.getCurrentState().isAtLeast(Lifecycle.State.INITIALIZED) == true) goto L6;
        return;
    L6:
        if (r3 != Lifecycle.Event.ON_DESTROY) goto L8;
        r2.mOnScreenResultListener.onScreenResult(r2.mResult);
    L8:
        r2.mLifecycleRegistry.handleLifecycleEvent(r3);
    }

    public static /* synthetic */ void $r8$lambda$zxVt3BlwxQf66mOTwWveM9jjlDw(Object r0) {
    }

    public Screen(CarContext r2) {
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mOnScreenResultListener = new Screen$$ExternalSyntheticLambda0();
        Objects.requireNonNull(r2);
        this.mCarContext = r2;
    }

    private static TemplateInfo getLastTemplateInfo(TemplateWrapper r2) {
        return new TemplateInfo(r2.getTemplate().getClass(), r2.getId());
    }

    public void dispatchLifecycleEvent(Lifecycle.Event r2) {
        ThreadUtils.runOnMain(new Screen$$ExternalSyntheticLambda1(this, r2));
    }

    public final CarContext getCarContext() {
        return this.mCarContext;
    }

    public TemplateInfo getLastTemplateInfo() {
        if (this.mTemplateWrapper != null) goto L6;
        this.mTemplateWrapper = TemplateWrapper.wrap(onGetTemplate());
    L6:
        return new TemplateInfo(this.mTemplateWrapper.getTemplate().getClass(), this.mTemplateWrapper.getId());
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public final Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    public final ScreenManager getScreenManager() {
        return (ScreenManager) this.mCarContext.getCarService(ScreenManager.class);
    }

    public TemplateWrapper getTemplateWrapper() {
        Template r0 = onGetTemplate();
        if (this.mUseLastTemplateId == false) goto L7;
        TemplateWrapper r1 = this.mTemplateWrapper;
        if (r1 == null) goto L7;
        TemplateWrapper r12 = TemplateWrapper.wrap(r0, getLastTemplateInfo(r1).getTemplateId());
    L8:
        this.mUseLastTemplateId = false;
        this.mTemplateWrapper = r12;
        if (Log.isLoggable("CarApp", 3) == false) goto L11;
        Log.d("CarApp", "Returning " + r0 + " from screen " + this);
    L11:
        return r12;
    L7:
        r12 = TemplateWrapper.wrap(r0);
        goto L8
    }

    public final void invalidate() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED) == false) goto L6;
        ((AppManager) this.mCarContext.getCarService(AppManager.class)).invalidate();
        return;
    }

    public abstract Template onGetTemplate();

    public void setUseLastTemplateId(boolean r1) {
        this.mUseLastTemplateId = r1;
    }
}
