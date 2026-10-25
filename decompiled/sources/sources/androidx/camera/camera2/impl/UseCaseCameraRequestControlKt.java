package androidx.camera.camera2.impl;

import androidx.camera.core.impl.TagBundle;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0003\u001a\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0000¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Landroidx/camera/core/impl/TagBundle;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "toMap", "(Landroidx/camera/core/impl/TagBundle;)Ljava/util/Map;", "camera-camera2"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUseCaseCameraRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,742:1\n1869#2,2:743\n*S KotlinDebug\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlKt\n*L\n740#1:743,2\n*E\n"})
/* loaded from: classes3.dex */
public abstract class UseCaseCameraRequestControlKt {
    public static final Map<String, Object> toMap(TagBundle r4) {
        LinkedHashMap r0 = new LinkedHashMap();
        Iterator<T> r1 = r4.listKeys().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        String r2 = (String) r1.next();
        r0.put(r2, r4.getTag(r2));
        goto L4
    L6:
        return r0;
    }
}
