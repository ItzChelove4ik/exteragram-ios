package com.google.firebase.crashlytics.internal;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.StaticSessionData;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public final class CrashlyticsNativeComponentDeferredProxy implements CrashlyticsNativeComponent {
    private static final NativeSessionFileProvider MISSING_NATIVE_SESSION_FILE_PROVIDER = null;
    private final AtomicReference<CrashlyticsNativeComponent> availableNativeComponent;
    private final Deferred<CrashlyticsNativeComponent> deferredNativeComponent;

    /* renamed from: com.google.firebase.crashlytics.internal.CrashlyticsNativeComponentDeferredProxy$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class MissingNativeSessionFileProvider implements NativeSessionFileProvider {
        private MissingNativeSessionFileProvider() {
        }

        public /* synthetic */ MissingNativeSessionFileProvider(AnonymousClass1 r1) {
            this();
        }

        @Override // com.google.firebase.crashlytics.internal.NativeSessionFileProvider
        public File getAppFile() {
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.NativeSessionFileProvider
        public CrashlyticsReport.ApplicationExitInfo getApplicationExitInto() {
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.NativeSessionFileProvider
        public File getDeviceFile() {
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.NativeSessionFileProvider
        public File getMetadataFile() {
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.NativeSessionFileProvider
        public File getMinidumpFile() {
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.NativeSessionFileProvider
        public File getOsFile() {
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.NativeSessionFileProvider
        public File getSessionFile() {
            return null;
        }
    }

    /* renamed from: $r8$lambda$ABui6-NqCWHawGe888TdFdiMYTk, reason: not valid java name */
    public static /* synthetic */ void m1931$r8$lambda$ABui6NqCWHawGe888TdFdiMYTk(String r6, String r7, long r8, StaticSessionData r10, Provider r11) {
        ((CrashlyticsNativeComponent) r11.get()).prepareNativeSession(r6, r7, r8, r10);
    }

    public static /* synthetic */ void $r8$lambda$aFyqACfbzgBCqUDIOJCUOrXTRjw(CrashlyticsNativeComponentDeferredProxy r2, Provider r3) {
        r2.getClass();
        Logger.getLogger().d("Crashlytics native component now available.");
        r2.availableNativeComponent.set((CrashlyticsNativeComponent) r3.get());
    }

    static {
        MISSING_NATIVE_SESSION_FILE_PROVIDER = new MissingNativeSessionFileProvider(null);
    }

    public CrashlyticsNativeComponentDeferredProxy(Deferred<CrashlyticsNativeComponent> r3) {
        this.availableNativeComponent = new AtomicReference(null);
        this.deferredNativeComponent = r3;
        r3.whenAvailable(new CrashlyticsNativeComponentDeferredProxy$$ExternalSyntheticLambda0(this));
    }

    @Override // com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent
    public NativeSessionFileProvider getSessionFileProvider(String r1) {
        CrashlyticsNativeComponent r0 = this.availableNativeComponent.get();
        if (r0 != null) goto L7;
        return MISSING_NATIVE_SESSION_FILE_PROVIDER;
    L7:
        return r0.getSessionFileProvider(r1);
    }

    @Override // com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent
    public boolean hasCrashDataForCurrentSession() {
        CrashlyticsNativeComponent r0 = this.availableNativeComponent.get();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.hasCrashDataForCurrentSession() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent
    public boolean hasCrashDataForSession(String r1) {
        CrashlyticsNativeComponent r0 = this.availableNativeComponent.get();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.hasCrashDataForSession(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent
    public void prepareNativeSession(String r7, String r8, long r9, StaticSessionData r11) {
        Logger.getLogger().v("Deferring native open session: " + r7);
        this.deferredNativeComponent.whenAvailable(new CrashlyticsNativeComponentDeferredProxy$$ExternalSyntheticLambda1(r7, r8, r9, r11));
    }
}
