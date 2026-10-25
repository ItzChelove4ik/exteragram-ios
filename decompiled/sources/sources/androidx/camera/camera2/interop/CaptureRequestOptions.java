package androidx.camera.camera2.interop;

import androidx.camera.core.ExtendableBuilder;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.ReadableConfig;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0017\u0018\u00002\u00020\u0001:\u0001\fB\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\bJ\u000f\u0010\t\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000b¨\u0006\r"}, d2 = {"Landroidx/camera/camera2/interop/CaptureRequestOptions;", "Landroidx/camera/core/impl/ReadableConfig;", "Landroidx/camera/core/impl/Config;", "config", _UrlKt.FRAGMENT_ENCODE_SET, "unused", "<init>", "(Landroidx/camera/core/impl/Config;Z)V", "(Landroidx/camera/core/impl/Config;)V", "getConfig", "()Landroidx/camera/core/impl/Config;", "Landroidx/camera/core/impl/Config;", "Builder", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class CaptureRequestOptions implements ReadableConfig {
    private final Config config;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \r2\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001:\u0001\rB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0017¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Landroidx/camera/camera2/interop/CaptureRequestOptions$Builder;", "Landroidx/camera/core/ExtendableBuilder;", "Landroidx/camera/camera2/interop/CaptureRequestOptions;", "<init>", "()V", "Landroidx/camera/core/impl/MutableConfig;", "getMutableConfig", "()Landroidx/camera/core/impl/MutableConfig;", "build", "()Landroidx/camera/camera2/interop/CaptureRequestOptions;", "Landroidx/camera/core/impl/MutableOptionsBundle;", "mutableOptionsBundle", "Landroidx/camera/core/impl/MutableOptionsBundle;", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Builder implements ExtendableBuilder<CaptureRequestOptions> {
        public static final Companion Companion = null;
        private final MutableOptionsBundle mutableOptionsBundle;

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/interop/CaptureRequestOptions$Builder$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "from", "Landroidx/camera/camera2/interop/CaptureRequestOptions$Builder;", "config", "Landroidx/camera/core/impl/Config;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            /* renamed from: $r8$lambda$Tp-TyfsqJK_4iZRe960dG48bmbo, reason: not valid java name */
            public static boolean m108$r8$lambda$TpTyfsqJK_4iZRe960dG48bmbo(Builder r1, Config r2, Config.Option r3) {
                r1.getMutableConfig().insertOption(r3, r2.getOptionPriority(r3), r2.retrieveOption(r3));
                return true;
            }

            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            @JvmStatic
            public final Builder from(Config r3) {
                Builder r2 = new Builder();
                r3.findOptions("camera2.captureRequest.option.", new CaptureRequestOptions$Builder$Companion$$ExternalSyntheticLambda0(r2, r3));
                return r2;
            }
        }

        static {
            Companion = new Companion(null);
        }

        public Builder() {
            this.mutableOptionsBundle = MutableOptionsBundle.create();
        }

        public CaptureRequestOptions build() {
            return new CaptureRequestOptions(OptionsBundle.from(this.mutableOptionsBundle));
        }

        @Override // androidx.camera.core.ExtendableBuilder
        public MutableConfig getMutableConfig() {
            return this.mutableOptionsBundle;
        }
    }

    public CaptureRequestOptions(Config r2) {
        this(r2, false);
    }

    private CaptureRequestOptions(Config r1, boolean r2) {
        this.config = r1;
    }

    @Override // androidx.camera.core.impl.ReadableConfig
    public Config getConfig() {
        return this.config;
    }
}
