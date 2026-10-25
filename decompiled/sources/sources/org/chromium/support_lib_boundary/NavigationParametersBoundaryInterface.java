package org.chromium.support_lib_boundary;

import java.util.Map;

/* loaded from: classes5.dex */
public interface NavigationParametersBoundaryInterface extends FeatureFlagHolderBoundaryInterface {
    Map<String, String> getAdditionalHeaders();

    boolean getShouldReplaceCurrentEntry();
}
