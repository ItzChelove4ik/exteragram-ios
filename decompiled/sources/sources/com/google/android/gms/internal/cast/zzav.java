package com.google.android.gms.internal.cast;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.cast.framework.devicesuggestions.DeviceSuggestionsCallback;
import com.google.android.gms.cast.framework.devicesuggestions.DeviceSuggestionsClient;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
public final class zzav extends GoogleApi implements DeviceSuggestionsClient {
    private static final Api.ClientKey zza = null;
    private static final Api.AbstractClientBuilder zzb = null;
    private static final Api zzc = null;
    private static final Logger zzd = null;

    static {
        Api.ClientKey r0 = new Api.ClientKey();
        zza = r0;
        zzaj r1 = new zzaj();
        zzb = r1;
        zzc = new Api("DeviceSuggestions.API", r1, r0);
        zzd = new Logger("InternalDeviceSuggestionsClient");
    }

    public zzav(Activity r4) {
        super(r4, zzc, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public zzav(Context r4) {
        super(r4, zzc, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public static /* synthetic */ Logger zza() {
        return zzd;
    }

    @Override // com.google.android.gms.cast.framework.devicesuggestions.DeviceSuggestionsClient
    public final Task<Void> clearClientData() {
        return doWrite(TaskApiCall.builder().run(new zzat(this)).setFeatures(new Feature[]{com.google.android.gms.cast.zzaq.zzn}).setMethodKey(37604).build());
    }

    @Override // com.google.android.gms.cast.framework.devicesuggestions.DeviceSuggestionsClient
    public final Task<Void> registerCallback(DeviceSuggestionsCallback r4) {
        ListenerHolder r42 = registerListener(r4, "DeviceSuggestionsCallback");
        zzam r0 = new zzam(this, r42);
        zzau r1 = new zzau(this, r0);
        zzar r2 = new zzar(this, r0);
        return doRegisterEventListener(RegistrationMethods.builder().register(r1).unregister(r2).withHolder(r42).setFeatures(new Feature[]{com.google.android.gms.cast.zzaq.zzn}).setMethodKey(37601).build());
    }

    @Override // com.google.android.gms.cast.framework.devicesuggestions.DeviceSuggestionsClient
    public final Task<Void> requestDeviceSuggestions() {
        return doRead(TaskApiCall.builder().run(new zzas(this)).setFeatures(new Feature[]{com.google.android.gms.cast.zzaq.zzn}).setMethodKey(37603).build());
    }

    @Override // com.google.android.gms.cast.framework.devicesuggestions.DeviceSuggestionsClient
    public final Task<Boolean> unregisterCallback(DeviceSuggestionsCallback r2) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(r2, "DeviceSuggestionsCallback"), 37602);
    }
}
