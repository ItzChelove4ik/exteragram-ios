package com.google.android.play.core.integrity;

import com.google.android.gms.tasks.Task;
import com.google.android.play.integrity.internal.as;
import java.util.Set;

/* loaded from: classes5.dex */
public interface StandardIntegrityManager {

    public static abstract class PrepareIntegrityTokenRequest {

        public static abstract class Builder {
            public Builder() {
            }

            public abstract PrepareIntegrityTokenRequest build();

            public abstract Builder setCloudProjectNumber(long r1);
        }

        public PrepareIntegrityTokenRequest() {
        }

        public static Builder builder() {
            c r0 = new c();
            r0.a(0);
            return r0;
        }

        public abstract int a();

        public abstract long b();
    }

    public static abstract class StandardIntegrityToken {
        public StandardIntegrityToken() {
        }

        public abstract String token();
    }

    public interface StandardIntegrityTokenProvider {
        Task<StandardIntegrityToken> request(StandardIntegrityTokenRequest r1);
    }

    public static abstract class StandardIntegrityTokenRequest {

        public static abstract class Builder {
            public Builder() {
            }

            public abstract StandardIntegrityTokenRequest build();

            public abstract Builder setRequestHash(String r1);
        }

        public StandardIntegrityTokenRequest() {
        }

        public static Builder builder() {
            f r0 = new f();
            r0.setVerdictOptOut(as.h());
            return r0;
        }

        public abstract String requestHash();

        public abstract Set<Integer> verdictOptOut();
    }

    Task<StandardIntegrityTokenProvider> prepareIntegrityToken(PrepareIntegrityTokenRequest r1);
}
