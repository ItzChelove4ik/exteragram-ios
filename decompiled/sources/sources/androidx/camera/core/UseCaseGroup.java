package androidx.camera.core;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public final class UseCaseGroup {
    private final List<CameraEffect> mEffects;
    private final List<UseCase> mUseCases;

    public static final class Builder {
        private static final List<Integer> SUPPORTED_TARGETS = null;
        private final List<CameraEffect> mEffects;
        private final List<UseCase> mUseCases;

        static {
            SUPPORTED_TARGETS = Arrays.asList(new Integer[]{1, 2, 4, 3, 7});
        }

        public Builder() {
            this.mUseCases = new ArrayList();
            this.mEffects = new ArrayList();
        }

        private void checkEffectTargets() {
            Iterator<CameraEffect> r1 = this.mEffects.iterator();
            if (r1.hasNext() == true) goto L5;
            return;
        L5:
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());
            throw null;
        }

        public Builder addUseCase(UseCase r2) {
            this.mUseCases.add(r2);
            return this;
        }

        public UseCaseGroup build() {
            Preconditions.checkArgument(!this.mUseCases.isEmpty(), "UseCase must not be empty.");
            checkEffectTargets();
            return new UseCaseGroup(null, this.mUseCases, this.mEffects);
        }
    }

    public UseCaseGroup(ViewPort r1, List<UseCase> r2, List<CameraEffect> r3) {
        this.mUseCases = r2;
        this.mEffects = r3;
    }

    public List<CameraEffect> getEffects() {
        return this.mEffects;
    }

    public List<UseCase> getUseCases() {
        return this.mUseCases;
    }

    public ViewPort getViewPort() {
        return null;
    }
}
