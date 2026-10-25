package androidx.media3.exoplayer.hls;

import android.net.Uri;
import androidx.media3.common.util.Assertions;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceInputStream;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.TransferListener;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
class Aes128DataSource implements DataSource {
    private CipherInputStream cipherInputStream;
    private final byte[] encryptionIv;
    private final byte[] encryptionKey;
    private final DataSource upstream;

    public Aes128DataSource(DataSource r1, byte[] r2, byte[] r3) {
        this.upstream = r1;
        this.encryptionKey = r2;
        this.encryptionIv = r3;
    }

    @Override // androidx.media3.datasource.DataSource
    public final void addTransferListener(TransferListener r1) {
        Assertions.checkNotNull(r1);
        this.upstream.addTransferListener(r1);
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() {
        if (this.cipherInputStream == null) goto L6;
        this.cipherInputStream = null;
        this.upstream.close();
        return;
    }

    public Cipher getCipherInstance() {
        return Cipher.getInstance("AES/CBC/PKCS7Padding");
    }

    @Override // androidx.media3.datasource.DataSource
    public final Map<String, List<String>> getResponseHeaders() {
        return this.upstream.getResponseHeaders();
    }

    @Override // androidx.media3.datasource.DataSource
    public final Uri getUri() {
        return this.upstream.getUri();
    }

    @Override // androidx.media3.datasource.DataSource
    public final long open(DataSpec r7) {
        Cipher r2 = getCipherInstance();     // Catch: NoSuchPaddingException -> L13 Throwable -> L15
        r2.init(2, new SecretKeySpec(this.encryptionKey, "AES"), new IvParameterSpec(this.encryptionIv));     // Catch: Throwable -> L8 InvalidKeyException -> L10
        DataSourceInputStream r0 = new DataSourceInputStream(this.upstream, r7);
        this.cipherInputStream = new CipherInputStream(r0, r2);
        r0.open();
        return -1;
    L8:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return 0;
    L15:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return 0;
    }

    @Override // androidx.media3.common.DataReader
    public final int read(byte[] r2, int r3, int r4) {
        Assertions.checkNotNull(this.cipherInputStream);
        int r1 = this.cipherInputStream.read(r2, r3, r4);
        if (r1 >= 0) goto L6;
        return -1;
    L6:
        return r1;
    }
}
