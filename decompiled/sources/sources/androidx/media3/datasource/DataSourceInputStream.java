package androidx.media3.datasource;

import androidx.media3.common.util.Assertions;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.UByte;

/* loaded from: classes3.dex */
public final class DataSourceInputStream extends InputStream implements InputStreamRetargetInterface {
    private boolean closed;
    private final DataSource dataSource;
    private final DataSpec dataSpec;
    private boolean opened;
    private final byte[] singleByteArray;
    private long totalBytesRead;

    public DataSourceInputStream(DataSource r2, DataSpec r3) {
        this.opened = false;
        this.closed = false;
        this.dataSource = r2;
        this.dataSpec = r3;
        this.singleByteArray = new byte[1];
    }

    private void checkOpened() {
        if (this.opened == true) goto L6;
        this.dataSource.open(this.dataSpec);
        this.opened = true;
        return;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed == true) goto L6;
        this.dataSource.close();
        this.closed = true;
        return;
    }

    public void open() {
        checkOpened();
    }

    @Override // java.io.InputStream
    public int read() {
        if (read(this.singleByteArray) != (-1)) goto L6;
        return -1;
    L6:
        return this.singleByteArray[0] & UByte.MAX_VALUE;
    }

    @Override // java.io.InputStream
    public int read(byte[] r3) {
        return read(r3, 0, r3.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] r3, int r4, int r5) {
        Assertions.checkState(!this.closed);
        checkOpened();
        int r32 = this.dataSource.read(r3, r4, r5);
        if (r32 != (-1)) goto L5;
        return -1;
    L5:
        this.totalBytesRead += r32;
        return r32;
    }

    @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
    public /* synthetic */ long transferTo(OutputStream r1) {
        return DesugarInputStream.transferTo(this, r1);
    }
}
