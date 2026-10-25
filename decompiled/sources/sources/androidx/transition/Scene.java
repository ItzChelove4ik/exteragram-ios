package androidx.transition;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.view.ViewGroup;

/* loaded from: classes4.dex */
public abstract class Scene {
    public static Scene getCurrentScene(ViewGroup r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.getTag(R$id.transition_current_scene));
        return null;
    }

    public static void setCurrentScene(ViewGroup r1, Scene r2) {
        r1.setTag(R$id.transition_current_scene, r2);
    }
}
