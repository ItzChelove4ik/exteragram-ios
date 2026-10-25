package androidx.camera.camera2.compat;

import android.hardware.camera2.params.DynamicRangeProfiles;
import android.util.Log;
import androidx.camera.camera2.compat.DynamicRangeProfilesCompat;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.internal.DynamicRangeConversions;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Options$Companion$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\f\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\bH\u0002¢\u0006\u0004\b\f\u0010\rJ#\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0016R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Landroidx/camera/camera2/compat/DynamicRangeProfilesCompatApi33Impl;", "Landroidx/camera/camera2/compat/DynamicRangeProfilesCompat$DynamicRangeProfilesCompatImpl;", "Landroid/hardware/camera2/params/DynamicRangeProfiles;", "dynamicRangeProfiles", "<init>", "(Landroid/hardware/camera2/params/DynamicRangeProfiles;)V", "Landroidx/camera/core/DynamicRange;", "dynamicRange", _UrlKt.FRAGMENT_ENCODE_SET, "dynamicRangeToFirstSupportedProfile", "(Landroidx/camera/core/DynamicRange;)Ljava/lang/Long;", "profile", "profileToDynamicRange", "(J)Landroidx/camera/core/DynamicRange;", _UrlKt.FRAGMENT_ENCODE_SET, "profileSet", "profileSetToDynamicRangeSet", "(Ljava/util/Set;)Ljava/util/Set;", "getDynamicRangeCaptureRequestConstraints", "(Landroidx/camera/core/DynamicRange;)Ljava/util/Set;", "unwrap", "()Landroid/hardware/camera2/params/DynamicRangeProfiles;", "Landroid/hardware/camera2/params/DynamicRangeProfiles;", "getSupportedDynamicRanges", "()Ljava/util/Set;", "supportedDynamicRanges", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDynamicRangeProfilesCompatApi33Impl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DynamicRangeProfilesCompatApi33Impl.kt\nandroidx/camera/camera2/compat/DynamicRangeProfilesCompatApi33Impl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,78:1\n1#2:79\n119#3,4:80\n*S KotlinDebug\n*F\n+ 1 DynamicRangeProfilesCompatApi33Impl.kt\nandroidx/camera/camera2/compat/DynamicRangeProfilesCompatApi33Impl\n*L\n60#1:80,4\n*E\n"})
/* loaded from: classes3.dex */
public final class DynamicRangeProfilesCompatApi33Impl implements DynamicRangeProfilesCompat.DynamicRangeProfilesCompatImpl {
    private final DynamicRangeProfiles dynamicRangeProfiles;

    public DynamicRangeProfilesCompatApi33Impl(DynamicRangeProfiles r1) {
        this.dynamicRangeProfiles = r1;
    }

    private final Long dynamicRangeToFirstSupportedProfile(DynamicRange r2) {
        return DynamicRangeConversions.INSTANCE.dynamicRangeToFirstSupportedProfile(r2, this.dynamicRangeProfiles);
    }

    private final Set<DynamicRange> profileSetToDynamicRangeSet(Set<Long> r4) {
        if (r4.isEmpty() == true) goto L5;
        LinkedHashSet r0 = new LinkedHashSet();
        Iterator<Long> r42 = r4.iterator();
    L8:
        if (r42.hasNext() == false) goto L13;
        DynamicRange r1 = profileToDynamicRange(r42.next().longValue());
        if (r1 == null) goto L8;
        r0.add(r1);
        goto L8
    L13:
        return Collections.unmodifiableSet(r0);
    L5:
        return SetsKt.emptySet();
    }

    private final DynamicRange profileToDynamicRange(long r4) {
        DynamicRange r3 = DynamicRangeConversions.INSTANCE.profileToDynamicRange(r4);
        if (r3 != null) goto L7;
        Camera2Logger r0 = Camera2Logger.INSTANCE;
        if (Logger.isWarnEnabled("CXCP") == false) goto L7;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Dynamic range profile cannot be converted to a DynamicRange object: " + r4);
    L7:
        return r3;
    }

    @Override // androidx.camera.camera2.compat.DynamicRangeProfilesCompat.DynamicRangeProfilesCompatImpl
    public Set<DynamicRange> getDynamicRangeCaptureRequestConstraints(DynamicRange r3) {
        Long r0 = dynamicRangeToFirstSupportedProfile(r3);
        if (r0 != null) goto L5;
        Options$Companion$$ExternalSyntheticBUOutline0.m("DynamicRange is not supported: ", r3);
        return null;
    L5:
        return profileSetToDynamicRangeSet(this.dynamicRangeProfiles.getProfileCaptureRequestConstraints(r0.longValue()));
    }

    @Override // androidx.camera.camera2.compat.DynamicRangeProfilesCompat.DynamicRangeProfilesCompatImpl
    public Set<DynamicRange> getSupportedDynamicRanges() {
        return profileSetToDynamicRangeSet(this.dynamicRangeProfiles.getSupportedProfiles());
    }

    @Override // androidx.camera.camera2.compat.DynamicRangeProfilesCompat.DynamicRangeProfilesCompatImpl
    public DynamicRangeProfiles unwrap() {
        return this.dynamicRangeProfiles;
    }
}
