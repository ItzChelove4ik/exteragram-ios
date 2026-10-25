package androidx.camera.core.featuregroup.impl.resolver;

import androidx.camera.core.UseCase;
import androidx.camera.core.featuregroup.GroupableFeature;
import androidx.camera.core.featuregroup.impl.ResolvedFeatureGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u0082\u0001\u0004\u0006\u0007\b\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Landroidx/camera/core/featuregroup/impl/resolver/FeatureGroupResolutionResult;", _UrlKt.FRAGMENT_ENCODE_SET, "Supported", "UseCaseMissing", "UnsupportedUseCase", "Unsupported", "Landroidx/camera/core/featuregroup/impl/resolver/FeatureGroupResolutionResult$Supported;", "Landroidx/camera/core/featuregroup/impl/resolver/FeatureGroupResolutionResult$Unsupported;", "Landroidx/camera/core/featuregroup/impl/resolver/FeatureGroupResolutionResult$UnsupportedUseCase;", "Landroidx/camera/core/featuregroup/impl/resolver/FeatureGroupResolutionResult$UseCaseMissing;", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface FeatureGroupResolutionResult {

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fHÖ\u0003¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/camera/core/featuregroup/impl/resolver/FeatureGroupResolutionResult$Supported;", "Landroidx/camera/core/featuregroup/impl/resolver/FeatureGroupResolutionResult;", "Landroidx/camera/core/featuregroup/impl/ResolvedFeatureGroup;", "resolvedFeatureGroup", "<init>", "(Landroidx/camera/core/featuregroup/impl/ResolvedFeatureGroup;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/core/featuregroup/impl/ResolvedFeatureGroup;", "getResolvedFeatureGroup", "()Landroidx/camera/core/featuregroup/impl/ResolvedFeatureGroup;", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Supported implements FeatureGroupResolutionResult {
        private final ResolvedFeatureGroup resolvedFeatureGroup;

        public Supported(ResolvedFeatureGroup r1) {
            this.resolvedFeatureGroup = r1;
        }

        public boolean equals(Object r4) {
            if (this != r4) goto L6;
            return true;
        L6:
            if ((r4 instanceof Supported) == true) goto L9;
            return false;
        L9:
            if (Intrinsics.areEqual(this.resolvedFeatureGroup, ((Supported) r4).resolvedFeatureGroup) == true) goto L11;
            return false;
        L11:
            return true;
        }

        public final ResolvedFeatureGroup getResolvedFeatureGroup() {
            return this.resolvedFeatureGroup;
        }

        public int hashCode() {
            return this.resolvedFeatureGroup.hashCode();
        }

        public String toString() {
            return "Supported(resolvedFeatureGroup=" + this.resolvedFeatureGroup + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/camera/core/featuregroup/impl/resolver/FeatureGroupResolutionResult$Unsupported;", "Landroidx/camera/core/featuregroup/impl/resolver/FeatureGroupResolutionResult;", "<init>", "()V", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Unsupported implements FeatureGroupResolutionResult {
        public static final Unsupported INSTANCE = null;

        static {
            INSTANCE = new Unsupported();
        }

        private Unsupported() {
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fHÖ\u0003¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/camera/core/featuregroup/impl/resolver/FeatureGroupResolutionResult$UnsupportedUseCase;", "Landroidx/camera/core/featuregroup/impl/resolver/FeatureGroupResolutionResult;", "Landroidx/camera/core/UseCase;", "unsupportedUseCase", "<init>", "(Landroidx/camera/core/UseCase;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/core/UseCase;", "getUnsupportedUseCase", "()Landroidx/camera/core/UseCase;", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class UnsupportedUseCase implements FeatureGroupResolutionResult {
        private final UseCase unsupportedUseCase;

        public UnsupportedUseCase(UseCase r1) {
            this.unsupportedUseCase = r1;
        }

        public boolean equals(Object r4) {
            if (this != r4) goto L6;
            return true;
        L6:
            if ((r4 instanceof UnsupportedUseCase) == true) goto L9;
            return false;
        L9:
            if (Intrinsics.areEqual(this.unsupportedUseCase, ((UnsupportedUseCase) r4).unsupportedUseCase) == true) goto L11;
            return false;
        L11:
            return true;
        }

        public final UseCase getUnsupportedUseCase() {
            return this.unsupportedUseCase;
        }

        public int hashCode() {
            return this.unsupportedUseCase.hashCode();
        }

        public String toString() {
            return "UnsupportedUseCase(unsupportedUseCase=" + this.unsupportedUseCase + ')';
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\rHÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\tR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/camera/core/featuregroup/impl/resolver/FeatureGroupResolutionResult$UseCaseMissing;", "Landroidx/camera/core/featuregroup/impl/resolver/FeatureGroupResolutionResult;", _UrlKt.FRAGMENT_ENCODE_SET, "requiredUseCases", "Landroidx/camera/core/featuregroup/GroupableFeature;", "featureRequiring", "<init>", "(Ljava/lang/String;Landroidx/camera/core/featuregroup/GroupableFeature;)V", "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getRequiredUseCases", "Landroidx/camera/core/featuregroup/GroupableFeature;", "getFeatureRequiring", "()Landroidx/camera/core/featuregroup/GroupableFeature;", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class UseCaseMissing implements FeatureGroupResolutionResult {
        private final GroupableFeature featureRequiring;
        private final String requiredUseCases;

        public UseCaseMissing(String r1, GroupableFeature r2) {
            this.requiredUseCases = r1;
            this.featureRequiring = r2;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof UseCaseMissing) == true) goto L8;
            return false;
        L8:
            UseCaseMissing r52 = (UseCaseMissing) r5;
            if (Intrinsics.areEqual(this.requiredUseCases, r52.requiredUseCases) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.featureRequiring, r52.featureRequiring) == true) goto L14;
            return false;
        L14:
            return true;
        }

        public final GroupableFeature getFeatureRequiring() {
            return this.featureRequiring;
        }

        public final String getRequiredUseCases() {
            return this.requiredUseCases;
        }

        public int hashCode() {
            return (this.requiredUseCases.hashCode() * 31) + this.featureRequiring.hashCode();
        }

        public String toString() {
            return "UseCaseMissing(requiredUseCases=" + this.requiredUseCases + ", featureRequiring=" + this.featureRequiring + ')';
        }
    }
}
