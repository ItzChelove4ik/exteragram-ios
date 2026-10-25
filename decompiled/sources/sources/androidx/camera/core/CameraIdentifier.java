package androidx.camera.core;

import androidx.camera.core.impl.AdapterCameraInfo;
import androidx.camera.core.impl.Identifier;
import androidx.core.util.Preconditions;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\u0018\u00002\u00020\u0001:\u0001\u001aB!\b\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0007¢\u0006\f\n\u0004\b\u0004\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0007¢\u0006\f\n\u0004\b\u0006\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0019\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0011¨\u0006\u001b"}, d2 = {"Landroidx/camera/core/CameraIdentifier;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "cameraIds", "Landroidx/camera/core/impl/Identifier;", "compatibilityId", "<init>", "(Ljava/util/List;Landroidx/camera/core/impl/Identifier;)V", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "toString", "()Ljava/lang/String;", "Ljava/util/List;", "getCameraIds", "()Ljava/util/List;", "Landroidx/camera/core/impl/Identifier;", "getCompatibilityId", "()Landroidx/camera/core/impl/Identifier;", "getInternalId", "internalId", "Factory", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraIdentifier.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraIdentifier.kt\nandroidx/camera/core/CameraIdentifier\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,254:1\n1#2:255\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraIdentifier {
    private final List<String> cameraIds;
    private final Identifier compatibilityId;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J(\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J\u001a\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0007¨\u0006\u0011"}, d2 = {"Landroidx/camera/core/CameraIdentifier$Factory;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "create", "Landroidx/camera/core/CameraIdentifier;", "cameraIds", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "compatibilityId", "Landroidx/camera/core/impl/Identifier;", "primaryCameraId", "secondaryCameraId", "fromAdapterInfos", "primaryInfo", "Landroidx/camera/core/impl/AdapterCameraInfo;", "secondaryInfo", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCameraIdentifier.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraIdentifier.kt\nandroidx/camera/core/CameraIdentifier$Factory\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,254:1\n1#2:255\n*E\n"})
    public static final class Factory {
        public static final Factory INSTANCE = null;

        static {
            INSTANCE = new Factory();
        }

        private Factory() {
        }

        @JvmStatic
        @JvmOverloads
        public static final CameraIdentifier create(String r2) {
            return create$default(r2, null, null, 6, null);
        }

        @JvmStatic
        @JvmOverloads
        public static final CameraIdentifier create(String r0, String r1, Identifier r2) {
            List r02 = CollectionsKt.mutableListOf(new String[]{r0});
            if (r1 == null) goto L6;
            r02.add(r1);
        L6:
            return create(r02, r2);
        }

        @JvmStatic
        @JvmOverloads
        public static final CameraIdentifier create(List<String> r2, Identifier r3) {
            return new CameraIdentifier(r2, r3, null);
        }

        public static /* synthetic */ CameraIdentifier create$default(String r1, String r2, Identifier r3, int r4, Object r5) {
            if ((r4 & 2) == 0) goto L6;
            r2 = null;
        L6:
            if ((r4 & 4) == 0) goto L9;
            r3 = null;
        L9:
            return create(r1, r2, r3);
        }

        @JvmStatic
        public static final CameraIdentifier fromAdapterInfos(AdapterCameraInfo r1, AdapterCameraInfo r2) {
            if (r2 == null) goto L4;
            String r22 = r2.getCameraId();
        L5:
            Identifier r0 = r1.getCameraConfig().getCompatibilityId();
            return create(r1.getCameraId(), r22, r0);
        L4:
            r22 = null;
            goto L5
        }
    }

    private CameraIdentifier(List<String> r1, Identifier r2) {
        this.cameraIds = r1;
        this.compatibilityId = r2;
        Preconditions.checkArgument(!r1.isEmpty(), "Camera ID set cannot be empty.");
    }

    public /* synthetic */ CameraIdentifier(List r1, Identifier r2, DefaultConstructorMarker r3) {
        this(r1, r2);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof CameraIdentifier) == true) goto L8;
        return false;
    L8:
        CameraIdentifier r52 = (CameraIdentifier) r5;
        if (Intrinsics.areEqual(this.cameraIds, r52.cameraIds) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.compatibilityId, r52.compatibilityId) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public final List<String> getCameraIds() {
        return this.cameraIds;
    }

    public final String getInternalId() {
        boolean r1 = true;
        if (this.cameraIds.size() == 1) goto L6;
        r1 = false;
    L6:
        Preconditions.checkState(r1, "getInternalId() is only available for single-camera identifiers.");
        return (String) CollectionsKt.first(this.cameraIds);
    }

    public int hashCode() {
        int r0 = this.cameraIds.hashCode() * 31;
        Identifier r1 = this.compatibilityId;
        if (r1 == null) goto L5;
        int r12 = r1.hashCode();
    L7:
        return r0 + r12;
    L5:
        r12 = 0;
        goto L7
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("CameraIdentifier{cameraIds=");
        r0.append(CollectionsKt.joinToString$default(this.cameraIds, ",", null, null, 0, null, null, 62, null));
        Identifier r11 = this.compatibilityId;
        if (r11 == null) goto L5;
        String r112 = ", compatId=" + r11;
    L6:
        r0.append(r112);
        r0.append('}');
        return r0.toString();
    L5:
        r112 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }
}
