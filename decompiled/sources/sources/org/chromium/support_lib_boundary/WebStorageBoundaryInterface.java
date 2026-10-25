package org.chromium.support_lib_boundary;

import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public interface WebStorageBoundaryInterface {
    void deleteBrowsingData(Executor r1, Runnable r2);

    String deleteBrowsingDataForSite(String r1, Executor r2, Runnable r3);
}
