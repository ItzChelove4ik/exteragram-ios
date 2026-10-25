package androidx.camera.core.featuregroup.impl;

import androidx.camera.core.Logger;
import androidx.camera.core.SessionConfig;
import androidx.camera.core.featuregroup.GroupableFeature;
import androidx.camera.core.featuregroup.impl.resolver.DefaultFeatureGroupResolver;
import androidx.camera.core.featuregroup.impl.resolver.FeatureGroupResolutionResult;
import androidx.camera.core.featuregroup.impl.resolver.FeatureGroupResolver;
import androidx.camera.core.impl.CameraInfoInternal;
import c.f$$ExternalSyntheticBUOutline1;
import com.sun.jna.Library$Handler$$ExternalSyntheticBUOutline0;
import java.util.Set;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Landroidx/camera/core/featuregroup/impl/ResolvedFeatureGroup;", _UrlKt.FRAGMENT_ENCODE_SET, "features", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/featuregroup/GroupableFeature;", "<init>", "(Ljava/util/Set;)V", "getFeatures", "()Ljava/util/Set;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ResolvedFeatureGroup {
    public static final Companion Companion = null;
    private final Set<GroupableFeature> features;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/camera/core/featuregroup/impl/ResolvedFeatureGroup$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "resolveFeatureGroup", "Landroidx/camera/core/featuregroup/impl/ResolvedFeatureGroup;", "Landroidx/camera/core/SessionConfig;", "cameraInfoInternal", "Landroidx/camera/core/impl/CameraInfoInternal;", "resolver", "Landroidx/camera/core/featuregroup/impl/resolver/FeatureGroupResolver;", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static /* synthetic */ ResolvedFeatureGroup resolveFeatureGroup$default(Companion r0, SessionConfig r1, CameraInfoInternal r2, FeatureGroupResolver r3, int r4, Object r5) {
            if ((r4 & 2) == 0) goto L6;
            r3 = new DefaultFeatureGroupResolver(r2);
        L6:
            return r0.resolveFeatureGroup(r1, r2, r3);
        }

        @JvmStatic
        @JvmOverloads
        public final ResolvedFeatureGroup resolveFeatureGroup(SessionConfig r7, CameraInfoInternal r8) {
            return resolveFeatureGroup$default(this, r7, r8, null, 2, null);
        }

        @JvmStatic
        @JvmOverloads
        public final ResolvedFeatureGroup resolveFeatureGroup(SessionConfig r2, CameraInfoInternal r3, FeatureGroupResolver r4) {
            Logger.d("ResolvedFeatureGroup", "resolveFeatureGroup: sessionConfig = " + r2 + ", lensFacing = " + r3.getLensFacing());
            if (r2.getRequiredFeatureGroup().isEmpty() == true) goto L5;
        L7:
            FeatureGroupResolutionResult r1 = r4.resolveFeatureGroup(r2);
            if ((r1 instanceof FeatureGroupResolutionResult.Supported) == false) goto L12;
            ResolvedFeatureGroup r12 = ((FeatureGroupResolutionResult.Supported) r1).getResolvedFeatureGroup();
            Logger.d("ResolvedFeatureGroup", "resolvedFeatureGroup = " + r12);
            return r12;
        L12:
            if ((r1 instanceof FeatureGroupResolutionResult.Unsupported) == false) goto L14;
            f$$ExternalSyntheticBUOutline1.m("Feature group is not supported");
            return null;
        L14:
            if ((r1 instanceof FeatureGroupResolutionResult.UnsupportedUseCase) == true) goto L22;
            if ((r1 instanceof FeatureGroupResolutionResult.UseCaseMissing) == false) goto L19;
            FeatureGroupResolutionResult.UseCaseMissing r13 = (FeatureGroupResolutionResult.UseCaseMissing) r1;
            Library$Handler$$ExternalSyntheticBUOutline0.m(r13.getRequiredUseCases(), " must be added for ", r13.getFeatureRequiring());
            return null;
        L19:
            LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
            return null;
        L22:
            throw new IllegalArgumentException(((FeatureGroupResolutionResult.UnsupportedUseCase) r1).getUnsupportedUseCase() + " is not supported");
        L5:
            if (r2.getPreferredFeatureGroup().isEmpty() == false) goto L7;
            return null;
        }
    }

    static {
        Companion = new Companion(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ResolvedFeatureGroup(Set<? extends GroupableFeature> r1) {
        this.features = r1;
    }

    @JvmStatic
    @JvmOverloads
    public static final ResolvedFeatureGroup resolveFeatureGroup(SessionConfig r1, CameraInfoInternal r2) {
        return Companion.resolveFeatureGroup(r1, r2);
    }

    public final Set<GroupableFeature> getFeatures() {
        return this.features;
    }

    public String toString() {
        return "ResolvedFeatureGroup(features=" + this.features + ')';
    }
}
