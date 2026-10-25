package androidx.camera.camera2.impl;

import androidx.camera.camera2.pipe.Metadata;
import androidx.camera.core.impl.TagBundle;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0004¨\u0006\b"}, d2 = {"CAMERAX_TAG_BUNDLE", "Landroidx/camera/camera2/pipe/Metadata$Key;", "Landroidx/camera/core/impl/TagBundle;", "getCAMERAX_TAG_BUNDLE", "()Landroidx/camera/camera2/pipe/Metadata$Key;", "USE_CASE_CAMERA_STATE_CUSTOM_TAG", _UrlKt.FRAGMENT_ENCODE_SET, "getUSE_CASE_CAMERA_STATE_CUSTOM_TAG", "camera-camera2"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTags.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Tags.kt\nandroidx/camera/camera2/impl/TagsKt\n+ 2 Metadata.kt\nandroidx/camera/camera2/pipe/Metadata$Key$Companion\n*L\n1#1,27:1\n47#2:28\n47#2:29\n*S KotlinDebug\n*F\n+ 1 Tags.kt\nandroidx/camera/camera2/impl/TagsKt\n*L\n24#1:28\n26#1:29\n*E\n"})
/* loaded from: classes3.dex */
public abstract class TagsKt {
    private static final Metadata.Key<TagBundle> CAMERAX_TAG_BUNDLE = null;
    private static final Metadata.Key<Integer> USE_CASE_CAMERA_STATE_CUSTOM_TAG = null;

    static {
        Metadata.Key.Companion r0 = Metadata.Key.Companion;
        CAMERAX_TAG_BUNDLE = r0.create("camerax.tag_bundle", Reflection.getOrCreateKotlinClass(TagBundle.class));
        USE_CASE_CAMERA_STATE_CUSTOM_TAG = r0.create("use_case_camera_state.tag", Reflection.getOrCreateKotlinClass(Integer.class));
    }

    public static final Metadata.Key<TagBundle> getCAMERAX_TAG_BUNDLE() {
        return CAMERAX_TAG_BUNDLE;
    }

    public static final Metadata.Key<Integer> getUSE_CASE_CAMERA_STATE_CUSTOM_TAG() {
        return USE_CASE_CAMERA_STATE_CUSTOM_TAG;
    }
}
