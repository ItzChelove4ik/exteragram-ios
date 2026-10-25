package androidx.camera.core.processing.util;

import androidx.camera.core.processing.util.AutoValue_GraphicDeviceInfo;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public abstract class GraphicDeviceInfo {

    public static abstract class Builder {
        public Builder() {
        }

        public abstract GraphicDeviceInfo build();

        public abstract Builder setEglExtensions(String r1);

        public abstract Builder setEglVersion(String r1);

        public abstract Builder setGlExtensions(String r1);

        public abstract Builder setGlVersion(String r1);
    }

    public GraphicDeviceInfo() {
    }

    public static Builder builder() {
        return new AutoValue_GraphicDeviceInfo.Builder().setGlVersion("0.0").setEglVersion("0.0").setGlExtensions(_UrlKt.FRAGMENT_ENCODE_SET).setEglExtensions(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    public abstract String getEglExtensions();

    public abstract String getEglVersion();

    public abstract String getGlExtensions();

    public abstract String getGlVersion();
}
