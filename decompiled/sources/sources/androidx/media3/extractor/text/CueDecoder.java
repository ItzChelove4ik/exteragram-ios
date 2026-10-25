package androidx.media3.extractor.text;

import android.os.Bundle;
import android.os.Parcel;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BundleCollectionUtil;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class CueDecoder {
    public CueDecoder() {
    }

    public CuesWithTiming decode(long r7, byte[] r9, int r10, int r11) {
        Parcel r6 = Parcel.obtain();
        r6.unmarshall(r9, r10, r11);
        r6.setDataPosition(0);
        Bundle r92 = r6.readBundle(Bundle.class.getClassLoader());
        r6.recycle();
        ArrayList r62 = (ArrayList) Assertions.checkNotNull(r92.getParcelableArrayList("c"));
        return new CuesWithTiming(BundleCollectionUtil.fromBundleList(new CueDecoder$$ExternalSyntheticLambda0(), r62), r7, r92.getLong("d"));
    }
}
