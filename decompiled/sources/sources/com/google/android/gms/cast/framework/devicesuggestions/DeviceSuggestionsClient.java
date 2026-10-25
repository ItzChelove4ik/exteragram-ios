package com.google.android.gms.cast.framework.devicesuggestions;

import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
public interface DeviceSuggestionsClient {
    Task<Void> clearClientData();

    /* synthetic */ ApiKey getApiKey();

    Task<Void> registerCallback(DeviceSuggestionsCallback r1);

    Task<Void> requestDeviceSuggestions();

    Task<Boolean> unregisterCallback(DeviceSuggestionsCallback r1);
}
