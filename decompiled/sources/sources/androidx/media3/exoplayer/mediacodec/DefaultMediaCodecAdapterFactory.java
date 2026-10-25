package androidx.media3.exoplayer.mediacodec;

import android.content.Context;
import android.os.Build;
import android.os.HandlerThread;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import androidx.media3.exoplayer.mediacodec.SynchronousMediaCodecAdapter;
import com.google.common.base.Supplier;

/* loaded from: classes4.dex */
public final class DefaultMediaCodecAdapterFactory implements MediaCodecAdapter.Factory {
    private boolean asyncCryptoFlagEnabled;
    private int asynchronousMode;
    private final Supplier<HandlerThread> callbackThreadSupplier;
    private final Context context;
    private final Supplier<HandlerThread> queueingThreadSupplier;

    @Deprecated
    public DefaultMediaCodecAdapterFactory() {
        this.asynchronousMode = 0;
        this.asyncCryptoFlagEnabled = false;
        this.context = null;
        this.callbackThreadSupplier = null;
        this.queueingThreadSupplier = null;
    }

    public DefaultMediaCodecAdapterFactory(Context r2) {
        this(r2, null, null);
    }

    public DefaultMediaCodecAdapterFactory(Context r1, Supplier<HandlerThread> r2, Supplier<HandlerThread> r3) {
        this.context = r1;
        this.asynchronousMode = 0;
        this.asyncCryptoFlagEnabled = false;
        this.callbackThreadSupplier = r2;
        this.queueingThreadSupplier = r3;
    }

    private boolean shouldUseAsynchronousAdapterInDefaultMode() {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 31) goto L5;
        return true;
    L5:
        Context r3 = this.context;
        if (r3 != null) goto L8;
        return false;
    L8:
        if (r0 >= 28) goto L10;
        return false;
    L10:
        if (r3.getPackageManager().hasSystemFeature("com.amazon.hardware.tv_screen") == false) goto L15;
        return true;
    L15:
        return false;
    }

    @Override // androidx.media3.exoplayer.mediacodec.MediaCodecAdapter.Factory
    public MediaCodecAdapter createAdapter(MediaCodecAdapter.Configuration r4) {
        int r0 = this.asynchronousMode;
        if (r0 == 1) goto L10;
        if (r0 != 0) goto L9;
        if (shouldUseAsynchronousAdapterInDefaultMode() == true) goto L10;
    L9:
        return new SynchronousMediaCodecAdapter.Factory().createAdapter(r4);
    L10:
        int r02 = MimeTypes.getTrackType(r4.format.sampleMimeType);
        Log.i("DMCodecAdapterFactory", "Creating an asynchronous MediaCodec adapter for track type " + Util.getTrackTypeString(r02));
        Supplier<HandlerThread> r1 = this.callbackThreadSupplier;
        if (r1 == null) goto L15;
        Supplier<HandlerThread> r2 = this.queueingThreadSupplier;
        if (r2 == null) goto L15;
        AsynchronousMediaCodecAdapter.Factory r03 = new AsynchronousMediaCodecAdapter.Factory(r1, r2);
    L16:
        r03.experimentalSetAsyncCryptoFlagEnabled(this.asyncCryptoFlagEnabled);
        return r03.createAdapter(r4);
    L15:
        r03 = new AsynchronousMediaCodecAdapter.Factory(r02);
        goto L16
    }
}
