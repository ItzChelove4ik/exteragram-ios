package androidx.activity.result;

import androidx.activity.result.contract.ActivityResultContracts$PickVisualMedia;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\nB\u0007\b\u0000¢\u0006\u0002\u0010\u0002R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Landroidx/activity/result/PickVisualMediaRequest;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "<set-?>", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;", "mediaType", "getMediaType", "()Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;", "setMediaType$activity_release", "(Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;)V", "Builder", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PickVisualMediaRequest {
    private ActivityResultContracts$PickVisualMedia.VisualMediaType mediaType;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/activity/result/PickVisualMediaRequest$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "mediaType", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;", "build", "Landroidx/activity/result/PickVisualMediaRequest;", "setMediaType", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        private ActivityResultContracts$PickVisualMedia.VisualMediaType mediaType;

        public Builder() {
            this.mediaType = ActivityResultContracts$PickVisualMedia.ImageAndVideo.INSTANCE;
        }

        public final PickVisualMediaRequest build() {
            PickVisualMediaRequest r0 = new PickVisualMediaRequest();
            r0.setMediaType$activity_release(this.mediaType);
            return r0;
        }

        public final Builder setMediaType(ActivityResultContracts$PickVisualMedia.VisualMediaType r1) {
            this.mediaType = r1;
            return this;
        }
    }

    public PickVisualMediaRequest() {
        this.mediaType = ActivityResultContracts$PickVisualMedia.ImageAndVideo.INSTANCE;
    }

    public final ActivityResultContracts$PickVisualMedia.VisualMediaType getMediaType() {
        return this.mediaType;
    }

    public final void setMediaType$activity_release(ActivityResultContracts$PickVisualMedia.VisualMediaType r1) {
        this.mediaType = r1;
    }
}
