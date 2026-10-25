package org.chromium.support_lib_boundary;

import android.os.CancellationSignal;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.ServiceWorkerController;
import android.webkit.WebStorage;
import java.lang.reflect.InvocationHandler;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public interface ProfileBoundaryInterface {
    void addOriginMatchedHeader(String r1, String r2, Set<String> r3);

    void addQuicHints(Set<String> r1);

    void clearAllOriginMatchedHeaders();

    void clearMaxPrefetches();

    void clearMaxPrerenders();

    @Deprecated
    void clearOriginMatchedHeader(String r1);

    void clearOriginMatchedHeader(String r1, String r2);

    void clearPrefetch(String r1, Executor r2, InvocationHandler r3);

    void clearPrefetchTtl();

    void enqueuePreconnect(String r1);

    CookieManager getCookieManager();

    GeolocationPermissions getGeoLocationPermissions();

    InvocationHandler getHttpCache();

    int getMaxPrefetches();

    int getMaxPrerenders();

    String getName();

    List<InvocationHandler> getOriginMatchedHeaders(String r1, String r2);

    int getPrefetchTtlSeconds();

    ServiceWorkerController getServiceWorkerController();

    WebStorage getWebStorage();

    boolean hasOriginMatchedHeader(String r1);

    void preconnect(String r1);

    void prefetchUrl(String r1, CancellationSignal r2, Executor r3, InvocationHandler r4);

    void prefetchUrl(String r1, CancellationSignal r2, Executor r3, InvocationHandler r4, InvocationHandler r5);

    void setMaxPrefetches(int r1);

    void setMaxPrefetches(Integer r1);

    void setMaxPrerenders(int r1);

    void setMaxPrerenders(Integer r1);

    @Deprecated
    void setOriginMatchedHeader(String r1, String r2, Set<String> r3);

    void setPrefetchTtlSeconds(int r1);

    void setPrefetchTtlSeconds(Integer r1);

    void setSpeculativeLoadingConfig(InvocationHandler r1);

    void warmUpRendererProcess();
}
