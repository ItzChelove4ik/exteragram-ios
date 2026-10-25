package androidx.camera.camera2.pipe;

import androidx.camera.camera2.pipe.CameraStream;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001:\u0001\fR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0005R\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/InputStream;", _UrlKt.FRAGMENT_ENCODE_SET, "id", "Landroidx/camera/camera2/pipe/InputStreamId;", "getId-m1bwn9M", "()I", "maxImages", _UrlKt.FRAGMENT_ENCODE_SET, "getMaxImages", "format", "Landroidx/camera/camera2/pipe/StreamFormat;", "getFormat-8FPWQzE", "Config", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface InputStream {

    @kotlin.Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\r\u001a\u0004\b\u0010\u0010\u000f\"\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/camera/camera2/pipe/InputStream$Config;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraStream$Config;", "stream", _UrlKt.FRAGMENT_ENCODE_SET, "maxImages", "Landroidx/camera/camera2/pipe/StreamFormat;", "streamFormat", "<init>", "(Landroidx/camera/camera2/pipe/CameraStream$Config;IILkotlin/jvm/internal/DefaultConstructorMarker;)V", "Landroidx/camera/camera2/pipe/CameraStream$Config;", "getStream", "()Landroidx/camera/camera2/pipe/CameraStream$Config;", "I", "getMaxImages", "()I", "getStreamFormat-8FPWQzE", "setStreamFormat-hNQ4ISI", "(I)V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Config {
        private final int maxImages;
        private final CameraStream.Config stream;
        private int streamFormat;

        private Config(CameraStream.Config r1, int r2, int r3) {
            this.stream = r1;
            this.maxImages = r2;
            this.streamFormat = r3;
        }

        public /* synthetic */ Config(CameraStream.Config r1, int r2, int r3, DefaultConstructorMarker r4) {
            this(r1, r2, r3);
        }

        public final int getMaxImages() {
            return this.maxImages;
        }

        public final CameraStream.Config getStream() {
            return this.stream;
        }

        /* renamed from: getStreamFormat-8FPWQzE, reason: not valid java name */
        public final int m277getStreamFormat8FPWQzE() {
            return this.streamFormat;
        }
    }

    /* renamed from: getFormat-8FPWQzE, reason: not valid java name */
    int mo275getFormat8FPWQzE();

    /* renamed from: getId-m1bwn9M, reason: not valid java name */
    int mo276getIdm1bwn9M();

    int getMaxImages();
}
