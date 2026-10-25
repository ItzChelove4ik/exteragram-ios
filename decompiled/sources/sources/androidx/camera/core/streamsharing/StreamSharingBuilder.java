package androidx.camera.core.streamsharing;

import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.internal.TargetConfig;
import java.util.UUID;
import retrofit2.Utils$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
class StreamSharingBuilder implements UseCaseConfig.Builder<StreamSharing, StreamSharingConfig, StreamSharingBuilder> {
    private final MutableOptionsBundle mMutableConfig;

    public StreamSharingBuilder() {
        this(MutableOptionsBundle.create());
    }

    public StreamSharingBuilder(MutableOptionsBundle r4) {
        this.mMutableConfig = r4;
        Class r42 = (Class) r4.retrieveOption(TargetConfig.OPTION_TARGET_CLASS, null);
        if (r42 != null) goto L5;
    L9:
        setCaptureType(UseCaseConfigFactory.CaptureType.STREAM_SHARING);
        setTargetClass(StreamSharing.class);
        return;
    L5:
        if (r42.equals(StreamSharing.class) == true) goto L9;
        Utils$$ExternalSyntheticBUOutline2.m("Invalid target class configuration for ", this, ": ", r42);
        throw null;
    }

    @Override // androidx.camera.core.ExtendableBuilder
    public MutableConfig getMutableConfig() {
        return this.mMutableConfig;
    }

    @Override // androidx.camera.core.impl.UseCaseConfig.Builder
    public /* bridge */ /* synthetic */ UseCaseConfig getUseCaseConfig() {
        return getUseCaseConfig();
    }

    @Override // androidx.camera.core.impl.UseCaseConfig.Builder
    public StreamSharingConfig getUseCaseConfig() {
        return new StreamSharingConfig(OptionsBundle.from(this.mMutableConfig));
    }

    public StreamSharingBuilder setCaptureType(UseCaseConfigFactory.CaptureType r3) {
        getMutableConfig().insertOption(UseCaseConfig.OPTION_CAPTURE_TYPE, r3);
        return this;
    }

    public StreamSharingBuilder setTargetClass(Class<StreamSharing> r4) {
        getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_CLASS, r4);
        if (getMutableConfig().retrieveOption(TargetConfig.OPTION_TARGET_NAME, null) != null) goto L5;
        setTargetName(r4.getCanonicalName() + "-" + UUID.randomUUID());
    L5:
        return this;
    }

    public StreamSharingBuilder setTargetName(String r3) {
        getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_NAME, r3);
        return this;
    }
}
