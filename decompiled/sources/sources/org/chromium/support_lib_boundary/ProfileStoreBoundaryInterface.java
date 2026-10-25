package org.chromium.support_lib_boundary;

import java.lang.reflect.InvocationHandler;
import java.util.List;

/* loaded from: classes5.dex */
public interface ProfileStoreBoundaryInterface {
    boolean deleteProfile(String r1);

    List<String> getAllProfileNames();

    InvocationHandler getOrCreateProfile(String r1);

    InvocationHandler getProfile(String r1);
}
