package androidx.car.app.utils;

import android.graphics.Rect;
import android.os.RemoteException;
import android.util.Log;
import androidx.car.app.FailureResponse;
import androidx.car.app.HostException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.ISurfaceCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.SurfaceCallback;
import androidx.car.app.SurfaceContainer;
import androidx.car.app.serialization.Bundleable;
import androidx.car.app.serialization.BundlerException;
import androidx.lifecycle.Lifecycle;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public abstract class RemoteUtils {

    /* renamed from: androidx.car.app.utils.RemoteUtils$1, reason: invalid class name */
    public class AnonymousClass1 extends IOnDoneCallback.Stub {
        final /* synthetic */ OnDoneCallback val$callback;

        public AnonymousClass1(OnDoneCallback r1) {
        }

        @Override // androidx.car.app.IOnDoneCallback
        public void onFailure(Bundleable r1) {
            throw null;
        }

        @Override // androidx.car.app.IOnDoneCallback
        public void onSuccess(Bundleable r1) {
            throw null;
        }
    }

    public interface HostCall {
        Object dispatch();
    }

    public interface RemoteCall<ReturnT> {
        ReturnT call();
    }

    public static class SurfaceCallbackStub extends ISurfaceCallback.Stub {
        private final Lifecycle mLifecycle;
        private final SurfaceCallback mSurfaceCallback;

        /* renamed from: $r8$lambda$6OgX2XQs0-QDCABjDrkCGawgkFA, reason: not valid java name */
        public static /* synthetic */ Object m651$r8$lambda$6OgX2XQs0QDCABjDrkCGawgkFA(SurfaceCallbackStub r0, Rect r1) {
            r0.getClass();
            throw null;
        }

        /* renamed from: $r8$lambda$7I2Zivv0E-6goRdBlvK60jyTbP8, reason: not valid java name */
        public static /* synthetic */ Object m652$r8$lambda$7I2Zivv0E6goRdBlvK60jyTbP8(SurfaceCallbackStub r0, float r1, float r2) {
            r0.getClass();
            throw null;
        }

        public static /* synthetic */ Object $r8$lambda$EN7iiik0xJGNxAoKLGOEz1hsg78(SurfaceCallbackStub r0, Bundleable r1) {
            r0.getClass();
            SurfaceContainer r02 = (SurfaceContainer) r1.get();
            throw null;
        }

        /* renamed from: $r8$lambda$IUFGA-u6lp2tlrKusT1H9PKOCac, reason: not valid java name */
        public static /* synthetic */ Object m653$r8$lambda$IUFGAu6lp2tlrKusT1H9PKOCac(SurfaceCallbackStub r0, float r1, float r2) {
            r0.getClass();
            throw null;
        }

        /* renamed from: $r8$lambda$MzK6fExmBHGOgqo-9TpejdgnwIM, reason: not valid java name */
        public static /* synthetic */ Object m654$r8$lambda$MzK6fExmBHGOgqo9TpejdgnwIM(SurfaceCallbackStub r0, float r1, float r2) {
            r0.getClass();
            throw null;
        }

        /* renamed from: $r8$lambda$sYTQYPXw0DtzoyltSLR4B-cyKuY, reason: not valid java name */
        public static /* synthetic */ Object m655$r8$lambda$sYTQYPXw0DtzoyltSLR4BcyKuY(SurfaceCallbackStub r0, float r1, float r2, float r3) {
            r0.getClass();
            throw null;
        }

        /* renamed from: $r8$lambda$vopP5bi72VxlTE6zvu-FxmsgSuw, reason: not valid java name */
        public static /* synthetic */ Object m656$r8$lambda$vopP5bi72VxlTE6zvuFxmsgSuw(SurfaceCallbackStub r0, Bundleable r1) {
            r0.getClass();
            SurfaceContainer r02 = (SurfaceContainer) r1.get();
            throw null;
        }

        /* renamed from: $r8$lambda$weX9liOQmXme7_5iEcnBgJa-QiI, reason: not valid java name */
        public static /* synthetic */ Object m657$r8$lambda$weX9liOQmXme7_5iEcnBgJaQiI(SurfaceCallbackStub r0, Rect r1) {
            r0.getClass();
            throw null;
        }

        public SurfaceCallbackStub(Lifecycle r1, SurfaceCallback r2) {
            this.mLifecycle = r1;
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onClick(float r3, float r4) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, "onClick", new RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda1(this, r3, r4));
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onFling(float r3, float r4) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, "onFling", new RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda5(this, r3, r4));
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onScale(float r3, float r4, float r5) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, "onScale", new RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda6(this, r3, r4, r5));
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onScroll(float r3, float r4) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, "onScroll", new RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda4(this, r3, r4));
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onStableAreaChanged(Rect r3, IOnDoneCallback r4) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, r4, "onStableAreaChanged", new RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda7(this, r3));
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onSurfaceAvailable(Bundleable r3, IOnDoneCallback r4) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, r4, "onSurfaceAvailable", new RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda3(this, r3));
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onSurfaceDestroyed(Bundleable r3, IOnDoneCallback r4) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, r4, "onSurfaceDestroyed", new RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda0(this, r3));
        }

        @Override // androidx.car.app.ISurfaceCallback
        public void onVisibleAreaChanged(Rect r3, IOnDoneCallback r4) {
            RemoteUtils.dispatchCallFromHost(this.mLifecycle, r4, "onVisibleAreaChanged", new RemoteUtils$SurfaceCallbackStub$$ExternalSyntheticLambda2(this, r3));
        }
    }

    /* renamed from: $r8$lambda$1ADQp-Fpr9d0_tX7AcIVXhvtU7o, reason: not valid java name */
    public static /* synthetic */ void m648$r8$lambda$1ADQpFpr9d0_tX7AcIVXhvtU7o(Lifecycle r2, IOnDoneCallback r3, String r4, HostCall r5) {
        if (r2 != null) goto L4;
    L8:
        sendFailureResponseToHost(r3, r4, new IllegalStateException("Lifecycle is not at least created when dispatching " + r5));
        return;
    L4:
        if (r2.getCurrentState().isAtLeast(Lifecycle.State.CREATED) == false) goto L8;
        dispatchCallFromHost(r3, r4, r5);
    }

    /* renamed from: $r8$lambda$Nik9_MASBYx-4cvWP3LEmxjuGes, reason: not valid java name */
    public static /* synthetic */ Object m649$r8$lambda$Nik9_MASBYx4cvWP3LEmxjuGes(IOnDoneCallback r1, Throwable r2, String r3) {
        r1.onFailure(Bundleable.create(new FailureResponse(r2)));     // Catch: BundlerException -> L4
        return null;
    L4:
        e = move-exception;
        Log.e("CarApp.Dispatch", "Serialization failure in " + r3, e);
        return null;
    }

    /* renamed from: $r8$lambda$U6p5YSQvLJKecNuPicYtfyl-6Fc, reason: not valid java name */
    public static /* synthetic */ void m650$r8$lambda$U6p5YSQvLJKecNuPicYtfyl6Fc(Lifecycle r3, HostCall r4, String r5) {
        if (r3 != null) goto L15;
    L11:
        Log.w("CarApp.Dispatch", "Lifecycle is not at least created when dispatching " + r4);     // Catch: BundlerException -> L9
        return;
    L15:
    L9:
        e = move-exception;
        Log.e("CarApp.Dispatch", "Serialization failure in " + r5, e);
        return;
    L5:
        if (r3.getCurrentState().isAtLeast(Lifecycle.State.CREATED) == false) goto L11;
        r4.dispatch();     // Catch: BundlerException -> L9
    }

    public static /* synthetic */ void $r8$lambda$kZaNPbfuaKBY2Ftzn027n80YsOA(IOnDoneCallback r0, String r1, HostCall r2) {
        sendSuccessResponseToHost(r0, r1, r2.dispatch());     // Catch: BundlerException -> L4 RuntimeException -> L6
        return;
    L4:
        e = move-exception;
        sendFailureResponseToHost(r0, r1, e);
        return;
    L6:
        e = move-exception;
        sendFailureResponseToHost(r0, r1, e);
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }

    public static /* synthetic */ Object $r8$lambda$z9ri9Ugtm70byNiId8lWSRX4Qco(IOnDoneCallback r1, Object r2, String r3) {
        if (r2 != null) goto L11;
        Bundleable r22 = null;
    L6:
        r1.onSuccess(r22);     // Catch: BundlerException -> L8
        return null;
    L8:
        e = move-exception;
        sendFailureResponseToHost(r1, r3, e);
        return null;
    L11:
        r22 = Bundleable.create(r2);     // Catch: BundlerException -> L8
        goto L6
    }

    public static IOnDoneCallback createOnDoneCallbackStub(OnDoneCallback r1) {
        return new AnonymousClass1(r1);
    }

    public static void dispatchCallFromHost(IOnDoneCallback r1, String r2, HostCall r3) {
        ThreadUtils.runOnMain(new RemoteUtils$$ExternalSyntheticLambda3(r1, r2, r3));
    }

    public static void dispatchCallFromHost(Lifecycle r1, IOnDoneCallback r2, String r3, HostCall r4) {
        ThreadUtils.runOnMain(new RemoteUtils$$ExternalSyntheticLambda4(r1, r2, r3, r4));
    }

    public static void dispatchCallFromHost(Lifecycle r1, String r2, HostCall r3) {
        ThreadUtils.runOnMain(new RemoteUtils$$ExternalSyntheticLambda2(r1, r3, r2));
    }

    public static void dispatchCallToHost(String r2, RemoteCall<?> r3) {
        dispatchCallToHostForResult(r2, r3);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        Log.e("CarApp.Dispatch", "Host unresponsive when dispatching call " + r2, e);
    }

    public static <ReturnT> ReturnT dispatchCallToHostForResult(String r3, RemoteCall<ReturnT> r4) {
        /*  JADX ERROR: Simple mode code generation failed
            java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
            	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
            	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
            	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
            	at java.base/java.util.Objects.checkIndex(Objects.java:385)
            	at java.base/java.util.ArrayList.get(ArrayList.java:427)
            	at jadx.core.codegen.MethodGen.generateSimpleCode(MethodGen.java:361)
            	at jadx.core.codegen.MethodGen.addSimpleMethodCode(MethodGen.java:323)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:286)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            */
        /*
            java.lang.String r0 = "CarApp"
            java.lang.String r1 = "Dispatching call "
            r2 = 3
            boolean r2 = android.util.Log.isLoggable(r0, r2)     // Catch: java.lang.RuntimeException -> L20 java.lang.SecurityException -> L40
            if (r2 == 0) goto L22
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.RuntimeException -> L20 java.lang.SecurityException -> L40
            r2.<init>(r1)     // Catch: java.lang.RuntimeException -> L20 java.lang.SecurityException -> L40
            r2.append(r3)     // Catch: java.lang.RuntimeException -> L20 java.lang.SecurityException -> L40
            java.lang.String r1 = " to host"
            r2.append(r1)     // Catch: java.lang.RuntimeException -> L20 java.lang.SecurityException -> L40
            java.lang.String r1 = r2.toString()     // Catch: java.lang.RuntimeException -> L20 java.lang.SecurityException -> L40
            android.util.Log.d(r0, r1)     // Catch: java.lang.RuntimeException -> L20 java.lang.SecurityException -> L40
            goto L22
        L20:
            r4 = move-exception
            goto L27
        L22:
            java.lang.Object r3 = r4.call()     // Catch: java.lang.RuntimeException -> L20 java.lang.SecurityException -> L40
            return r3
        L27:
            androidx.car.app.HostException r0 = new androidx.car.app.HostException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Remote "
            r1.<init>(r2)
            r1.append(r3)
            java.lang.String r3 = " call failed"
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            r0.<init>(r3, r4)
            throw r0
        L40:
            r3 = move-exception
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.car.app.utils.RemoteUtils.dispatchCallToHostForResult(java.lang.String, androidx.car.app.utils.RemoteUtils$RemoteCall):java.lang.Object");
    }

    public static void sendFailureResponseToHost(IOnDoneCallback r2, String r3, Throwable r4) {
        dispatchCallToHost(r3 + " onFailure", new RemoteUtils$$ExternalSyntheticLambda1(r2, r4, r3));
    }

    public static void sendSuccessResponseToHost(IOnDoneCallback r2, String r3, Object r4) {
        dispatchCallToHost(r3 + " onSuccess", new RemoteUtils$$ExternalSyntheticLambda0(r2, r4, r3));
    }
}
