package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* loaded from: classes4.dex */
public final class Api<O extends ApiOptions> {
    private final AbstractClientBuilder zaa;
    private final ClientKey zab;
    private final String zac;

    public static abstract class AbstractClientBuilder<T extends Client, O> extends BaseClientBuilder<T, O> {
        public AbstractClientBuilder() {
        }

        @Deprecated
        public T buildClient(Context r1, Looper r2, ClientSettings r3, O r4, GoogleApiClient.ConnectionCallbacks r5, GoogleApiClient.OnConnectionFailedListener r6) {
            return (T) buildClient(r1, r2, r3, r4, r5, r6);
        }

        public T buildClient(Context r1, Looper r2, ClientSettings r3, O r4, ConnectionCallbacks r5, OnConnectionFailedListener r6) {
            throw new UnsupportedOperationException("buildClient must be implemented");
        }
    }

    public interface AnyClient {
    }

    public static class AnyClientKey<C extends AnyClient> {
        public AnyClientKey() {
        }
    }

    public interface ApiOptions {
        public static final NoOptions NO_OPTIONS = null;

        public interface HasAccountOptions extends ApiOptions {
            Account getAccount();
        }

        public static final class NoOptions implements ApiOptions {
            public /* synthetic */ NoOptions(byte[] r1) {
            }
        }

        static {
            NO_OPTIONS = new NoOptions(null);
        }
    }

    public static abstract class BaseClientBuilder<T extends AnyClient, O> {
        public static final int API_PRIORITY_GAMES = 1;
        public static final int API_PRIORITY_OTHER = Integer.MAX_VALUE;
        public static final int API_PRIORITY_PLUS = 2;

        public BaseClientBuilder() {
        }

        public List<Scope> getImpliedScopes(O r1) {
            return Collections.EMPTY_LIST;
        }

        public int getPriority() {
            return Integer.MAX_VALUE;
        }
    }

    public interface Client extends AnyClient {
        void connect(BaseGmsClient.ConnectionProgressReportCallbacks r1);

        void disconnect();

        void disconnect(String r1);

        void dump(String r1, FileDescriptor r2, PrintWriter r3, String[] r4);

        Feature[] getAvailableFeatures();

        String getEndpointPackageName();

        String getLastDisconnectMessage();

        int getMinApkVersion();

        void getRemoteService(IAccountAccessor r1, Set<Scope> r2);

        Set<Scope> getScopesForConnectionlessNonSignIn();

        Intent getSignInIntent();

        boolean isConnected();

        boolean isConnecting();

        void onUserSignOut(BaseGmsClient.SignOutCallbacks r1);

        boolean providesSignIn();

        boolean requiresGooglePlayServices();

        boolean requiresSignIn();
    }

    public static final class ClientKey<C extends Client> extends AnyClientKey<C> {
        public ClientKey() {
        }
    }

    public <C extends Client> Api(String r2, AbstractClientBuilder<C, O> r3, ClientKey<C> r4) {
        Preconditions.checkNotNull(r3, "Cannot construct an Api with a null ClientBuilder");
        Preconditions.checkNotNull(r4, "Cannot construct an Api with a null ClientKey");
        this.zac = r2;
        this.zaa = r3;
        this.zab = r4;
    }

    public final BaseClientBuilder zaa() {
        return this.zaa;
    }

    public final AbstractClientBuilder zab() {
        return this.zaa;
    }

    public final AnyClientKey zac() {
        return this.zab;
    }

    public final String zad() {
        return this.zac;
    }
}
