package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.view.View;
import androidx.collection.ArraySet;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.signin.SignInOptions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public final class ClientSettings {
    private final Account zaa;
    private final Set zab;
    private final Set zac;
    private final Map zad;
    private final int zae;
    private final View zaf;
    private final String zag;
    private final String zah;
    private final SignInOptions zai;
    private Integer zaj;

    public static final class Builder {
        private Account zaa;
        private ArraySet zab;
        private String zac;
        private String zad;
        private final SignInOptions zae;

        public Builder() {
            this.zae = SignInOptions.zaa;
        }

        public ClientSettings build() {
            return new ClientSettings(this.zaa, this.zab, null, 0, null, this.zac, this.zad, this.zae, false);
        }

        public Builder setRealClientPackageName(String r1) {
            this.zac = r1;
            return this;
        }

        public final Builder zaa(Account r1) {
            this.zaa = r1;
            return this;
        }

        public final Builder zab(Collection r2) {
            if (this.zab != null) goto L5;
            this.zab = new ArraySet();
        L5:
            this.zab.addAll(r2);
            return this;
        }

        public final Builder zac(String r1) {
            this.zad = r1;
            return this;
        }
    }

    public ClientSettings(Account r1, Set r2, Map r3, int r4, View r5, String r6, String r7, SignInOptions r8, boolean r9) {
        this.zaa = r1;
        if (r2 != null) goto L5;
        Set r12 = Collections.EMPTY_SET;
    L6:
        this.zab = r12;
        if (r3 != null) goto L9;
        r3 = Collections.EMPTY_MAP;
    L9:
        this.zad = r3;
        this.zaf = r5;
        this.zae = r4;
        this.zag = r6;
        this.zah = r7;
        if (r8 != null) goto L12;
        r8 = SignInOptions.zaa;
    L12:
        this.zai = r8;
        HashSet r22 = new HashSet(r12);
        Iterator r13 = r3.values().iterator();
        if (r13.hasNext() == true) goto L16;
        this.zac = Collections.unmodifiableSet(r22);
        return;
    L16:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r13.next());
        throw null;
    L5:
        r12 = Collections.unmodifiableSet(r2);
        goto L6
    }

    public Account getAccount() {
        return this.zaa;
    }

    @Deprecated
    public String getAccountName() {
        Account r0 = this.zaa;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.name;
    }

    public Account getAccountOrDefault() {
        Account r2 = this.zaa;
        if (r2 == null) goto L6;
        return r2;
    L6:
        return new Account(BaseGmsClient.DEFAULT_ACCOUNT, "com.google");
    }

    public Set<Scope> getAllRequestedScopes() {
        return this.zac;
    }

    public Set<Scope> getApplicableScopes(Api<?> r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.zad.get(r2));
        return this.zab;
    }

    public String getRealClientPackageName() {
        return this.zag;
    }

    public Set<Scope> getRequiredScopes() {
        return this.zab;
    }

    public final Map zaa() {
        return this.zad;
    }

    public final String zab() {
        return this.zah;
    }

    public final SignInOptions zac() {
        return this.zai;
    }

    public final Integer zad() {
        return this.zaj;
    }

    public final void zae(Integer r1) {
        this.zaj = r1;
    }
}
