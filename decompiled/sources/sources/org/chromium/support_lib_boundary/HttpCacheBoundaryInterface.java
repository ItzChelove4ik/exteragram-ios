package org.chromium.support_lib_boundary;

/* loaded from: classes5.dex */
public interface HttpCacheBoundaryInterface {
    long getDefaultQuotaBytes();

    long getQuotaBytes();

    boolean isUsingDefaultQuota();

    void setQuotaBytes(long r1);

    void useDefaultQuota();
}
