package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Looper;
import android.view.View;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.internal.LifecycleActivity;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import com.google.android.gms.common.api.internal.zaaz;
import com.google.android.gms.common.api.internal.zak;
import com.google.android.gms.common.api.internal.zas;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.signin.SignInOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Deprecated
/* loaded from: classes4.dex */
public abstract class GoogleApiClient {
    private static final Set zaa = null;

    @Deprecated
    public static final class Builder {
        private Account zaa;
        private final Set zab;
        private final Set zac;
        private int zad;
        private View zae;
        private String zaf;
        private String zag;
        private final Map zah;
        private final Context zai;
        private final Map zaj;
        private LifecycleActivity zak;
        private int zal;
        private OnConnectionFailedListener zam;
        private Looper zan;
        private GoogleApiAvailability zao;
        private Api.AbstractClientBuilder zap;
        private final ArrayList zaq;
        private final ArrayList zar;

        public Builder(Context r2) {
            this.zab = new HashSet();
            this.zac = new HashSet();
            this.zah = new ArrayMap();
            this.zaj = new ArrayMap();
            this.zal = -1;
            this.zao = GoogleApiAvailability.getInstance();
            this.zap = com.google.android.gms.signin.zad.zac;
            this.zaq = new ArrayList();
            this.zar = new ArrayList();
            this.zai = r2;
            this.zan = r2.getMainLooper();
            this.zaf = r2.getPackageName();
            this.zag = r2.getClass().getName();
        }

        public Builder addApi(Api<Object> r3) {
            Preconditions.checkNotNull(r3, "Api must not be null");
            this.zaj.put(r3, null);
            List<Scope> r32 = ((Api.BaseClientBuilder) Preconditions.checkNotNull(r3.zaa(), "Base client builder must not be null")).getImpliedScopes(null);
            this.zac.addAll(r32);
            this.zab.addAll(r32);
            return this;
        }

        public Builder addConnectionCallbacks(ConnectionCallbacks r2) {
            Preconditions.checkNotNull(r2, "Listener must not be null");
            this.zaq.add(r2);
            return this;
        }

        public Builder addOnConnectionFailedListener(OnConnectionFailedListener r2) {
            Preconditions.checkNotNull(r2, "Listener must not be null");
            this.zar.add(r2);
            return this;
        }

        public GoogleApiClient build() {
            Map r1 = this.zaj;
            Preconditions.checkArgument(!r1.isEmpty(), "must call addApi() to add at least one API");
            ClientSettings r8 = zaa();
            Map r2 = r8.zaa();
            ArrayMap r12 = new ArrayMap();
            ArrayMap r15 = new ArrayMap();
            ArrayList r4 = new ArrayList();
            Iterator r13 = r1.keySet().iterator();
            boolean r14 = false;
            Api r16 = null;
            boolean r17 = false;
        L4:
            if (r13.hasNext() == false) goto L20;
            Api r5 = (Api) r13.next();
            Object r9 = r1.get(r5);
            if (r2.get(r5) == null) goto L8;
            boolean r6 = true;
        L9:
            r12.put(r5, Boolean.valueOf(r6));
            zas r10 = new zas(r5, r6);
            r4.add(r10);
            Api.AbstractClientBuilder r62 = (Api.AbstractClientBuilder) Preconditions.checkNotNull(r5.zab());
            Api.Client r63 = r62.buildClient(this.zai, this.zan, r8, r9, r10, r10);
            r15.put(r5.zac(), r63);
            if (r62.getPriority() != 1) goto L15;
            if (r9 == null) goto L13;
            r17 = true;
            goto L15
        L13:
            r17 = false;
        L15:
            if (r63.providesSignIn() == false) goto L4;
            if (r16 != null) goto L18;
            r16 = r5;
            goto L4
        L18:
            String r18 = r5.zad();
            String r22 = r16.zad();
            StringBuilder r42 = new StringBuilder((String.valueOf(r18).length() + 21) + String.valueOf(r22).length());
            r42.append(r18);
            r42.append(" cannot be used with ");
            r42.append(r22);
            throw new IllegalStateException(r42.toString());
        L8:
            r6 = false;
            goto L9
        L20:
            if (r16 == null) goto L28;
            if (r17 == false) goto L23;
            String r19 = r16.zad();
            StringBuilder r3 = new StringBuilder(String.valueOf(r19).length() + 82);
            r3.append("With using ");
            r3.append(r19);
            r3.append(", GamesOptions can only be specified within GoogleSignInOptions.Builder");
            throw new IllegalStateException(r3.toString());
        L23:
            if (this.zaa != null) goto L25;
            r14 = true;
        L25:
            Preconditions.checkState(r14, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", new Object[]{r16.zad()});
            Preconditions.checkState(this.zab.equals(this.zac), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", new Object[]{r16.zad()});
        L28:
            zaaz r52 = new zaaz(this.zai, new ReentrantLock(), this.zan, r8, this.zao, this.zap, r12, this.zaq, this.zar, r15, this.zal, zaaz.zaf(r15.values(), true), r4);
            Set r110 = GoogleApiClient.zaq();
            monitor-enter(r110);
            GoogleApiClient.zaq().add(r52);     // Catch: Throwable -> L36
            monitor-exit(r110);     // Catch: Throwable -> L36
            if (this.zal < 0) goto L35;
            zak.zaa(this.zak).zab(this.zal, r52, this.zam);
        L35:
            return r52;
        L36:
            th = move-exception;
            throw th;
        }

        public final ClientSettings zaa() {
            SignInOptions r0 = SignInOptions.zaa;
            Map r1 = this.zaj;
            Api r2 = com.google.android.gms.signin.zad.zag;
            if (r1.containsKey(r2) == false) goto L5;
            r0 = (SignInOptions) r1.get(r2);
        L5:
            Account r22 = this.zaa;
            Set r3 = this.zab;
            Map r4 = this.zah;
            int r5 = this.zad;
            View r6 = this.zae;
            String r7 = this.zaf;
            String r8 = this.zag;
            return new ClientSettings(r22, r3, r4, r5, r6, r7, r8, r0, false);
        }
    }

    @Deprecated
    public interface ConnectionCallbacks extends com.google.android.gms.common.api.internal.ConnectionCallbacks {
    }

    @Deprecated
    public interface OnConnectionFailedListener extends com.google.android.gms.common.api.internal.OnConnectionFailedListener {
    }

    static {
        zaa = Collections.newSetFromMap(new WeakHashMap());
    }

    public GoogleApiClient() {
    }

    public static Set<GoogleApiClient> getAllClients() {
        Set<GoogleApiClient> r0 = zaa;
        monitor-enter(r0);
        monitor-exit(r0);     // Catch: Throwable -> L6
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public static /* synthetic */ Set zaq() {
        return zaa;
    }

    public abstract void connect();

    public abstract void disconnect();

    public abstract void dump(String r1, FileDescriptor r2, PrintWriter r3, String[] r4);

    public abstract <A extends Api.AnyClient, R extends Result, T extends BaseImplementation$ApiMethodImpl<R, A>> T enqueue(T r1);

    public abstract <A extends Api.AnyClient, T extends BaseImplementation$ApiMethodImpl<? extends Result, A>> T execute(T r1);

    public <C extends Api.Client> C getClient(Api.AnyClientKey<C> r1) {
        throw new UnsupportedOperationException();
    }

    public abstract Looper getLooper();

    public boolean maybeSignIn(SignInConnectionListener r1) {
        throw new UnsupportedOperationException();
    }

    public void maybeSignOut() {
        throw new UnsupportedOperationException();
    }

    public abstract void registerConnectionFailedListener(OnConnectionFailedListener r1);

    public abstract void unregisterConnectionFailedListener(OnConnectionFailedListener r1);
}
