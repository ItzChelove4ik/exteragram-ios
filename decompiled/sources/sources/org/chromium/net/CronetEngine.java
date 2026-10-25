package org.chromium.net;

import android.content.Context;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import c.f$$ExternalSyntheticBUOutline1;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import org.chromium.base.metrics.ScopedSysTraceEvent;
import org.chromium.net.CronetProvider;
import org.chromium.net.UrlRequest;
import org.chromium.net.impl.CronetLogger;
import org.chromium.net.impl.CronetLoggerFactory;
import org.json.JSONObject;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public abstract class CronetEngine {

    public static class Builder {
        protected final ICronetEngineBuilder mBuilderDelegate;
        private final List<Object> mExperimentalOptionsPatches;
        protected JSONObject mParsedExperimentalOptions;

        /* renamed from: org.chromium.net.CronetEngine$Builder$1, reason: invalid class name */
        public class AnonymousClass1 implements Comparator<CronetProvider.ProviderInfo> {
            public AnonymousClass1() {
            }

            @Override // java.util.Comparator
            public /* bridge */ /* synthetic */ int compare(CronetProvider.ProviderInfo r1, CronetProvider.ProviderInfo r2) {
                return compare2(r1, r2);
            }

            /* renamed from: compare, reason: avoid collision after fix types in other method */
            public int compare2(CronetProvider.ProviderInfo r2, CronetProvider.ProviderInfo r3) {
                if ("Fallback-Cronet-Provider".equals(r2.provider.getName()) == false) goto L7;
                return 1;
            L7:
                if ("Fallback-Cronet-Provider".equals(r3.provider.getName()) == false) goto L11;
                return -1;
            L11:
                return -Builder.compareVersions(r2.provider.getVersion(), r3.provider.getVersion());
            }
        }

        public Builder(Context r1) {
            this(createBuilderDelegate(r1));
        }

        public Builder(ICronetEngineBuilder r2) {
            this.mExperimentalOptionsPatches = new ArrayList();
            this.mBuilderDelegate = r2;
        }

        public static int compareVersions(String r4, String r5) {
            int r0 = 0;
            if (r4 == null) goto L21;
            if (r5 == null) goto L21;
            String[] r42 = r4.split("\\.");
            String[] r52 = r5.split("\\.");
        L7:
            if (r0 >= r42.length) goto L20;
            if (r0 >= r52.length) goto L20;
            int r1 = Integer.parseInt(r42[r0]);     // Catch: NumberFormatException -> L14
            int r2 = Integer.parseInt(r52[r0]);     // Catch: NumberFormatException -> L14
            if (r1 != r2) goto L12;
            r0 = r0 + 1;
            goto L7
        L12:
            return Integer.signum(r1 - r2);
        L14:
            e = move-exception;
            throw new IllegalArgumentException("Unable to convert version segments into integers: " + r42[r0] + " & " + r52[r0], e);
        L20:
            return Integer.signum(r42.length - r52.length);
        L21:
            f$$ExternalSyntheticBUOutline1.m("The input values cannot be null");
            return 0;
        }

        private static ICronetEngineBuilder createBuilderDelegate(Context r8) {
            ScopedSysTraceEvent r1 = ScopedSysTraceEvent.scoped("CronetEngine#createBuilderDelegate");
            long r2 = SystemClock.uptimeMillis();     // Catch: Throwable -> L17
            CronetProvider.ProviderInfo r4 = getEnabledCronetProviders(r8, new ArrayList(CronetProvider.getAllProviderInfos(r8))).get(0);     // Catch: Throwable -> L17
            CronetLogger r82 = CronetLoggerFactory.createLogger(r8, r4.logSource);     // Catch: Throwable -> L17
            CronetLogger.CronetEngineBuilderInitializedInfo r5 = new CronetLogger.CronetEngineBuilderInitializedInfo();     // Catch: Throwable -> L17
            r5.creationSuccessful = Boolean.FALSE;     // Catch: Throwable -> L7
            r5.author = CronetLogger.CronetEngineBuilderInitializedInfo.Author.API;     // Catch: Throwable -> L7
            r5.source = r4.logSource;     // Catch: Throwable -> L7
            r5.uid = Process.myUid();     // Catch: Throwable -> L7
            r5.apiVersion = new CronetLogger.CronetVersion(ApiVersion.getCronetVersion());     // Catch: Throwable -> L7
            if (Log.isLoggable("CronetEngine.Builder", 3) == false) goto L9;
            Log.d("CronetEngine.Builder", String.format("Using '%s' provider for creating CronetEngine.Builder.", new Object[]{r4.provider}));     // Catch: Throwable -> L7
        L9:
            ICronetEngineBuilder r0 = r4.provider.createBuilder().mBuilderDelegate;     // Catch: Throwable -> L7
            String r42 = getImplCronetVersion(r0);     // Catch: Throwable -> L7
            if (r42 == null) goto L12;
            r5.implVersion = new CronetLogger.CronetVersion(r42);     // Catch: Throwable -> L7
        L12:
            r5.cronetInitializationRef = r0.getLogCronetInitializationRef();     // Catch: Throwable -> L7
            r5.creationSuccessful = Boolean.TRUE;     // Catch: Throwable -> L7
            r5.engineBuilderCreatedLatencyMillis = (int) (SystemClock.uptimeMillis() - r2);     // Catch: Throwable -> L17
            r82.logCronetEngineBuilderInitializedInfo(r5);     // Catch: Throwable -> L17
            if (r1 == null) goto L16;
            r1.close();
        L16:
            return r0;
        L7:
            th = move-exception;
            r5.engineBuilderCreatedLatencyMillis = (int) (SystemClock.uptimeMillis() - r2);     // Catch: Throwable -> L17
            r82.logCronetEngineBuilderInitializedInfo(r5);     // Catch: Throwable -> L17
            throw th;     // Catch: Throwable -> L17
        L17:
            th = move-exception;
            if (r1 != null) goto L28;
        L26:
            throw th;
        L28:
            r1.close();     // Catch: Throwable -> L24
        L24:
            th = move-exception;
            th.addSuppressed(th);
            goto L26
        }

        public static List<CronetProvider.ProviderInfo> getEnabledCronetProviders(Context r2, List<CronetProvider.ProviderInfo> r3) {
            if (r3.isEmpty() == true) goto L16;
            Iterator<CronetProvider.ProviderInfo> r22 = r3.iterator();
        L6:
            if (r22.hasNext() == false) goto L11;
            if (r22.next().provider.isEnabled() == true) goto L6;
            r22.remove();
            goto L6
        L11:
            if (r3.isEmpty() == true) goto L14;
            Collections.sort(r3, new AnonymousClass1());
            return r3;
        L14:
            GlShader$$ExternalSyntheticBUOutline1.m("All available Cronet providers are disabled. A provider should be enabled before it can be used.");
            return null;
        L16:
            GlShader$$ExternalSyntheticBUOutline1.m("Unable to find any Cronet provider. Have you included all necessary jars?");
            return null;
        }

        private static int getImplApiLevel(ICronetEngineBuilder r1) {
            Method r12 = getImplVersionMethod(r1, "getApiLevel");     // Catch: ReflectiveOperationException -> L8
            if (r12 != null) goto L6;
            return -1;
        L6:
            return ((Integer) r12.invoke(null, null)).intValue();
        L8:
            e = move-exception;
            Make$Map$$ExternalSyntheticBUOutline0.m("Failed to retrieve Cronet impl API level", e);
            return 0;
        }

        private static String getImplCronetVersion(ICronetEngineBuilder r2) {
            Method r22 = getImplVersionMethod(r2, "getCronetVersion");     // Catch: ReflectiveOperationException -> L8
            if (r22 != null) goto L6;
            return null;
        L6:
            return (String) r22.invoke(null, null);
        L8:
            e = move-exception;
            Make$Map$$ExternalSyntheticBUOutline0.m("Failed to retrieve Cronet impl version", e);
            return null;
        }

        private static Method getImplVersionMethod(ICronetEngineBuilder r2, String r3) {
            return r2.getClass().getClassLoader().loadClass("org.chromium.net.impl.ImplVersion").getMethod(r3, null);
        L5:
            return null;
        }

        private int getMaximumApiLevel() {
            return ApiVersion.getMaximumAvailableApiLevel();
        }

        private void maybeSetExperimentalOptions() {
            JSONObject r0 = ExperimentalOptionsTranslator.applyJsonPatches(this.mParsedExperimentalOptions, this.mExperimentalOptionsPatches);
            if (r0 == null) goto L6;
            this.mBuilderDelegate.setExperimentalOptions(r0.toString());
            return;
        }

        public CronetEngine build() {
            return buildExperimental();
        }

        public ExperimentalCronetEngine buildExperimental() {
            int r0 = getImplApiLevel(this.mBuilderDelegate);
            if (r0 != (-1)) goto L5;
        L7:
            maybeSetExperimentalOptions();
            return this.mBuilderDelegate.build();
        L5:
            if (r0 >= getMaximumApiLevel()) goto L7;
            Log.w("CronetEngine.Builder", "The implementation version is lower than the API version. Calls to methods added in API " + (r0 + 1) + " and newer will likely have no effect.");
            goto L7
        }
    }

    static {
    }

    public CronetEngine() {
    }

    public abstract UrlRequest.Builder newUrlRequestBuilder(String r1, UrlRequest.Callback r2, Executor r3);
}
