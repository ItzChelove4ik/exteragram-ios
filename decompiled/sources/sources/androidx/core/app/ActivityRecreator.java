package androidx.core.app;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/* loaded from: classes3.dex */
abstract class ActivityRecreator {
    protected static final Class<?> activityThreadClass = null;
    private static final Handler mainHandler = null;
    protected static final Field mainThreadField = null;
    protected static final Method performStopActivity2ParamsMethod = null;
    protected static final Method performStopActivity3ParamsMethod = null;
    protected static final Method requestRelaunchActivityMethod = null;
    protected static final Field tokenField = null;

    /* renamed from: androidx.core.app.ActivityRecreator$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ LifecycleCheckCallbacks val$callbacks;
        final /* synthetic */ Object val$token;

        public AnonymousClass1(LifecycleCheckCallbacks r1, Object r2) {
            this.val$callbacks = r1;
            this.val$token = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            LifecycleCheckCallbacks r0 = this.val$callbacks;
            r0.currentlyRecreatingToken = this.val$token;
        }
    }

    /* renamed from: androidx.core.app.ActivityRecreator$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ Application val$application;
        final /* synthetic */ LifecycleCheckCallbacks val$callbacks;

        public AnonymousClass2(Application r1, LifecycleCheckCallbacks r2) {
            this.val$application = r1;
            this.val$callbacks = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.val$application.unregisterActivityLifecycleCallbacks(this.val$callbacks);
        }
    }

    /* renamed from: androidx.core.app.ActivityRecreator$3, reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ Object val$activityThread;
        final /* synthetic */ Object val$token;

        public AnonymousClass3(Object r1, Object r2) {
            this.val$activityThread = r1;
            this.val$token = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Method r0 = ActivityRecreator.performStopActivity3ParamsMethod;     // Catch: Throwable -> L8 RuntimeException -> L10
            if (r0 == null) goto L6;
            r0.invoke(this.val$activityThread, new Object[]{this.val$token, Boolean.FALSE, "AppCompat recreation"});     // Catch: Throwable -> L8 RuntimeException -> L10
            return;
        L6:
            ActivityRecreator.performStopActivity2ParamsMethod.invoke(this.val$activityThread, new Object[]{this.val$token, Boolean.FALSE});     // Catch: Throwable -> L8 RuntimeException -> L10
            return;
        L10:
            e = move-exception;
            if (e.getClass() == RuntimeException.class) goto L14;
            return;
        L14:
            if (e.getMessage() != null) goto L16;
            return;
        L16:
            if (e.getMessage().startsWith("Unable to stop") == false) goto L23;
            throw e;
        L23:
            return;
        L8:
            th = move-exception;
            Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th);
        }
    }

    public static final class LifecycleCheckCallbacks implements Application.ActivityLifecycleCallbacks {
        Object currentlyRecreatingToken;
        private Activity mActivity;
        private boolean mDestroyed;
        private final int mRecreatingHashCode;
        private boolean mStarted;
        private boolean mStopQueued;

        public LifecycleCheckCallbacks(Activity r2) {
            this.mStarted = false;
            this.mDestroyed = false;
            this.mStopQueued = false;
            this.mActivity = r2;
            this.mRecreatingHashCode = r2.hashCode();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity r1, Bundle r2) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity r2) {
            if (this.mActivity != r2) goto L6;
            this.mActivity = null;
            this.mDestroyed = true;
            return;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity r3) {
            if (this.mDestroyed == true) goto L5;
            return;
        L5:
            if (this.mStopQueued == false) goto L7;
            return;
        L7:
            if (this.mStarted == false) goto L9;
            return;
        L9:
            if (ActivityRecreator.queueOnStopIfNecessary(this.currentlyRecreatingToken, this.mRecreatingHashCode, r3) == false) goto L15;
            this.mStopQueued = true;
            this.currentlyRecreatingToken = null;
            return;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity r1) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity r1, Bundle r2) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity r2) {
            if (this.mActivity != r2) goto L6;
            this.mStarted = true;
            return;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity r1) {
        }
    }

    static {
        mainHandler = new Handler(Looper.getMainLooper());
        Class<?> r0 = getActivityThreadClass();
        activityThreadClass = r0;
        mainThreadField = getMainThreadField();
        tokenField = getTokenField();
        performStopActivity3ParamsMethod = getPerformStopActivity3Params(r0);
        performStopActivity2ParamsMethod = getPerformStopActivity2Params(r0);
        requestRelaunchActivityMethod = getRequestRelaunchActivityMethod(r0);
    }

    private static Class<?> getActivityThreadClass() {
        return Class.forName("android.app.ActivityThread");
    L4:
        return null;
    }

    private static Field getMainThreadField() {
        Field r0 = Activity.class.getDeclaredField("mMainThread");     // Catch: Throwable -> L4
        r0.setAccessible(true);     // Catch: Throwable -> L4
        return r0;
    L4:
        return null;
    }

    private static Method getPerformStopActivity2Params(Class<?> r4) {
        if (r4 != null) goto L8;
        return null;
    L8:
        Method r42 = r4.getDeclaredMethod("performStopActivity", new Class[]{IBinder.class, Boolean.TYPE});     // Catch: Throwable -> L7
        r42.setAccessible(true);     // Catch: Throwable -> L7
        return r42;
    L7:
        return null;
    }

    private static Method getPerformStopActivity3Params(Class<?> r5) {
        if (r5 != null) goto L8;
        return null;
    L8:
        Method r52 = r5.getDeclaredMethod("performStopActivity", new Class[]{IBinder.class, Boolean.TYPE, String.class});     // Catch: Throwable -> L7
        r52.setAccessible(true);     // Catch: Throwable -> L7
        return r52;
    L7:
        return null;
    }

    private static Method getRequestRelaunchActivityMethod(Class<?> r11) {
        if (needsRelaunchCall() == false) goto L8;
        if (r11 == null) goto L8;
        Class r5 = Integer.TYPE;     // Catch: Throwable -> L9
        Class r6 = Boolean.TYPE;     // Catch: Throwable -> L9
        Method r112 = r11.getDeclaredMethod("requestRelaunchActivity", new Class[]{IBinder.class, List.class, List.class, r5, r6, Configuration.class, Configuration.class, r6, r6});     // Catch: Throwable -> L9
        r112.setAccessible(true);     // Catch: Throwable -> L9
        return r112;
    L8:
        return null;
    }

    private static Field getTokenField() {
        Field r0 = Activity.class.getDeclaredField("mToken");     // Catch: Throwable -> L4
        r0.setAccessible(true);     // Catch: Throwable -> L4
        return r0;
    L4:
        return null;
    }

    private static boolean needsRelaunchCall() {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 != 26) goto L5;
        return true;
    L5:
        if (r0 == 27) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean queueOnStopIfNecessary(Object r2, int r3, Activity r4) {
        Object r1 = tokenField.get(r4);     // Catch: Throwable -> L11
        if (r1 == r2) goto L6;
    L13:
        return false;
    L6:
        if (r4.hashCode() != r3) goto L13;
        Object r22 = mainThreadField.get(r4);     // Catch: Throwable -> L11
        mainHandler.postAtFrontOfQueue(new AnonymousClass3(r22, r1));     // Catch: Throwable -> L11
        return true;
    L11:
        th = move-exception;
        Log.e("ActivityRecreator", "Exception while fetching field values", th);
        return false;
    }

    public static boolean recreate(Activity r15) {
        if (Build.VERSION.SDK_INT < 28) goto L7;
        r15.recreate();
        return true;
    L7:
        if (needsRelaunchCall() == false) goto L12;
        if (requestRelaunchActivityMethod != null) goto L12;
        return false;
    L12:
        if (performStopActivity2ParamsMethod == null) goto L14;
    L35:
        Object r3 = tokenField.get(r15);     // Catch: Throwable -> L33
        if (r3 != null) goto L19;
        return false;
    L19:
        Object r0 = mainThreadField.get(r15);     // Catch: Throwable -> L33
        if (r0 != null) goto L22;
        return false;
    L22:
        Application r12 = r15.getApplication();     // Catch: Throwable -> L33
        LifecycleCheckCallbacks r13 = new LifecycleCheckCallbacks(r15);     // Catch: Throwable -> L33
        r12.registerActivityLifecycleCallbacks(r13);     // Catch: Throwable -> L33
        Handler r14 = mainHandler;     // Catch: Throwable -> L33
        r14.post(new AnonymousClass1(r13, r3));     // Catch: Throwable -> L33
    L26:
        th = move-exception;
        mainHandler.post(new AnonymousClass2(r12, r13));     // Catch: Throwable -> L33
        throw th;     // Catch: Throwable -> L33
    L24:
        if (needsRelaunchCall() == false) goto L28;
        Method r152 = requestRelaunchActivityMethod;     // Catch: Throwable -> L26
        Boolean r7 = Boolean.FALSE;     // Catch: Throwable -> L26
        r152.invoke(r0, new Object[]{r3, null, null, 0, r7, null, null, r7, r7});     // Catch: Throwable -> L26
    L29:
        r14.post(new AnonymousClass2(r12, r13));     // Catch: Throwable -> L33
        return true;
    L28:
        r15.recreate();     // Catch: Throwable -> L26
    L33:
        return false;
    L14:
        if (performStopActivity3ParamsMethod != null) goto L35;
        return false;
    }
}
