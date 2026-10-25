package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.ApiKey;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.zaab;
import com.google.android.gms.common.api.internal.zabk;
import com.google.android.gms.common.api.internal.zabq;
import com.google.android.gms.common.api.internal.zacm;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.AttributionSourceWrapper;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;

/* loaded from: classes4.dex */
public abstract class GoogleApi<O extends Api.ApiOptions> {
    protected final GoogleApiManager zaa;
    private final Context zab;
    private final String zac;
    private final AttributionSourceWrapper zad;
    private final Api zae;
    private final Api.ApiOptions zaf;
    private final ApiKey zag;
    private final Looper zah;
    private final int zai;
    private final GoogleApiClient zaj;
    private final StatusExceptionMapper zak;

    public static class Settings {
        public static final Settings DEFAULT_SETTINGS = null;
        public final StatusExceptionMapper zaa;
        public final Looper zab;

        public static class Builder {
            private StatusExceptionMapper zaa;
            private Looper zab;

            public Builder() {
            }

            public Settings build() {
                if (this.zaa != null) goto L6;
                this.zaa = new ApiExceptionMapper();
            L6:
                if (this.zab != null) goto L9;
                this.zab = Looper.getMainLooper();
            L9:
                return new Settings(this.zaa, null, this.zab, null);
            }

            public Builder setMapper(StatusExceptionMapper r2) {
                Preconditions.checkNotNull(r2, "StatusExceptionMapper must not be null.");
                this.zaa = r2;
                return this;
            }
        }

        static {
            DEFAULT_SETTINGS = new Builder().build();
        }

        private Settings(StatusExceptionMapper r1, Account r2, Looper r3) {
            this.zaa = r1;
            this.zab = r3;
        }

        public /* synthetic */ Settings(StatusExceptionMapper r1, Account r2, Looper r3, byte[] r4) {
            this(r1, null, r3);
        }
    }

    public GoogleApi(Activity r7, Api<O> r8, O r9, Settings r10) {
        this(r7, r7, r8, r9, r10);
    }

    private GoogleApi(Context r5, Activity r6, Api r7, Api.ApiOptions r8, Settings r9) {
        Preconditions.checkNotNull(r5, "Null context is not permitted.");
        Preconditions.checkNotNull(r7, "Api must not be null.");
        Preconditions.checkNotNull(r9, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        Context r0 = (Context) Preconditions.checkNotNull(r5.getApplicationContext(), "The provided context did not have an application context.");
        this.zab = r0;
        int r1 = Build.VERSION.SDK_INT;
        if (r1 < 30) goto L5;
        String r2 = ContextCompat.getAttributionTag(r5);
    L6:
        this.zac = r2;
        if (r1 < 31) goto L9;
        AttributionSourceWrapper r12 = new AttributionSourceWrapper(r5.getAttributionSource());
    L10:
        this.zad = r12;
        this.zae = r7;
        this.zaf = r8;
        this.zah = r9.zab;
        ApiKey r52 = ApiKey.getSharedApiKey(r7, r8, r2);
        this.zag = r52;
        this.zaj = new zabq(this);
        GoogleApiManager r72 = GoogleApiManager.zaa(r0);
        this.zaa = r72;
        this.zai = r72.zac();
        this.zak = r9.zaa;
        if (r6 != null) goto L13;
    L17:
        r72.zad(this);
        return;
    L13:
        if ((r6 instanceof GoogleApiActivity) == true) goto L17;
        if (Looper.myLooper() != Looper.getMainLooper()) goto L17;
        zaab.zaa(r6, r72, r52);
        goto L17
    L9:
        r12 = null;
        goto L10
    L5:
        r2 = getApiFallbackAttributionTag(r5);
        goto L6
    }

    public GoogleApi(Context r7, Api<O> r8, O r9, Settings r10) {
        this(r7, null, r8, r9, r10);
    }

    @Deprecated
    public GoogleApi(Context r2, Api<O> r3, O r4, StatusExceptionMapper r5) {
        Settings.Builder r0 = new Settings.Builder();
        r0.setMapper(r5);
        this(r2, r3, r4, r0.build());
    }

    private final BaseImplementation$ApiMethodImpl zad(int r2, BaseImplementation$ApiMethodImpl r3) {
        r3.zak();
        this.zaa.zak(this, r2, r3);
        return r3;
    }

    private final Task zae(int r7, TaskApiCall r8) {
        TaskCompletionSource r4 = new TaskCompletionSource();
        StatusExceptionMapper r5 = this.zak;
        this.zaa.zal(this, r7, r8, r4, r5);
        return r4.getTask();
    }

    public GoogleApiClient asGoogleApiClient() {
        return this.zaj;
    }

    public ClientSettings.Builder createClientSettingsBuilder() {
        ClientSettings.Builder r0 = new ClientSettings.Builder();
        Api.ApiOptions r1 = this.zaf;
        if ((r1 instanceof Api.ApiOptions.HasAccountOptions) == false) goto L5;
        Account r12 = ((Api.ApiOptions.HasAccountOptions) r1).getAccount();
    L6:
        r0.zaa(r12);
        r0.zab(Collections.EMPTY_SET);
        Context r3 = this.zab;
        r0.zac(r3.getClass().getName());
        r0.setRealClientPackageName(r3.getPackageName());
        return r0;
    L5:
        r12 = null;
        goto L6
    }

    public Task<Boolean> disconnectService() {
        return this.zaa.zaj(this);
    }

    public <A extends Api.AnyClient, T extends BaseImplementation$ApiMethodImpl<? extends Result, A>> T doBestEffortWrite(T r2) {
        zad(2, r2);
        return r2;
    }

    public <TResult, A extends Api.AnyClient> Task<TResult> doBestEffortWrite(TaskApiCall<A, TResult> r2) {
        return zae(2, r2);
    }

    public <A extends Api.AnyClient, T extends BaseImplementation$ApiMethodImpl<? extends Result, A>> T doRead(T r2) {
        zad(0, r2);
        return r2;
    }

    public <TResult, A extends Api.AnyClient> Task<TResult> doRead(TaskApiCall<A, TResult> r2) {
        return zae(0, r2);
    }

    @Deprecated
    public <A extends Api.AnyClient, T extends RegisterListenerMethod<A, ?>, U extends UnregisterListenerMethod<A, ?>> Task<Void> doRegisterEventListener(T r3, U r4) {
        Preconditions.checkNotNull(r3);
        Preconditions.checkNotNull(r4);
        Preconditions.checkNotNull(r3.getListenerKey(), "Listener has already been released.");
        Preconditions.checkNotNull(r4.getListenerKey(), "Listener has already been released.");
        Preconditions.checkArgument(Objects.equal(r3.getListenerKey(), r4.getListenerKey()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
        zab r0 = zab.zaa;
        return this.zaa.zan(this, r3, r4, r0);
    }

    public <A extends Api.AnyClient> Task<Void> doRegisterEventListener(RegistrationMethods<A, ?> r4) {
        Preconditions.checkNotNull(r4);
        Preconditions.checkNotNull(r4.register.getListenerKey(), "Listener has already been released.");
        UnregisterListenerMethod r0 = r4.zaa;
        Preconditions.checkNotNull(r0.getListenerKey(), "Listener has already been released.");
        RegisterListenerMethod<A, ?> r1 = r4.register;
        Runnable r42 = r4.zab;
        return this.zaa.zan(this, r1, r0, r42);
    }

    public Task<Boolean> doUnregisterEventListener(ListenerHolder.ListenerKey<?> r2) {
        return doUnregisterEventListener(r2, 0);
    }

    public Task<Boolean> doUnregisterEventListener(ListenerHolder.ListenerKey<?> r2, int r3) {
        Preconditions.checkNotNull(r2, "Listener key cannot be null.");
        return this.zaa.zao(this, r2, r3);
    }

    public <A extends Api.AnyClient, T extends BaseImplementation$ApiMethodImpl<? extends Result, A>> T doWrite(T r2) {
        zad(1, r2);
        return r2;
    }

    public <TResult, A extends Api.AnyClient> Task<TResult> doWrite(TaskApiCall<A, TResult> r2) {
        return zae(1, r2);
    }

    public String getApiFallbackAttributionTag(Context r1) {
        return null;
    }

    public final ApiKey<O> getApiKey() {
        return this.zag;
    }

    public O getApiOptions() {
        return (O) this.zaf;
    }

    public Context getApplicationContext() {
        return this.zab;
    }

    public String getContextAttributionTag() {
        return this.zac;
    }

    @Deprecated
    public String getContextFeatureId() {
        return this.zac;
    }

    public Looper getLooper() {
        return this.zah;
    }

    public <L> ListenerHolder<L> registerListener(L r1, String r2) {
        return ListenerHolders.createListenerHolder(r1, this.zah, r2);
    }

    public final int zab() {
        return this.zai;
    }

    public final zacm zac(Context r2, Handler r3) {
        return new zacm(r2, r3, createClientSettingsBuilder().build());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Api.Client zaf(Looper r9, zabk r10) {
        ClientSettings r4 = createClientSettingsBuilder().build();
        Api.AbstractClientBuilder r1 = (Api.AbstractClientBuilder) Preconditions.checkNotNull(this.zae.zab());
        Api.ApiOptions r5 = this.zaf;
        Api.Client r92 = r1.buildClient(this.zab, r9, r4, r5, r10, r10);
        AttributionSourceWrapper r102 = this.zad;
        if (r102 != null) goto L5;
    L9:
        String r8 = getContextAttributionTag();
        if (r8 != null) goto L12;
    L14:
        return r92;
    L12:
        if ((r92 instanceof BaseGmsClient) == false) goto L14;
        ((BaseGmsClient) r92).setAttributionTag(r8);
        goto L14
    L5:
        if ((r92 instanceof BaseGmsClient) == false) goto L9;
        ((BaseGmsClient) r92).setAttributionSourceWrapper(r102);
        return r92;
    }
}
