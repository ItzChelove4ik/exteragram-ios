package androidx.media3.datasource;

import android.net.Uri;
import androidx.media3.common.util.Assertions;
import com.google.android.gms.cast.CastStatusCodes;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;

/* loaded from: classes3.dex */
public final class UdpDataSource extends BaseDataSource {
    private InetAddress address;
    private MulticastSocket multicastSocket;
    private boolean opened;
    private final DatagramPacket packet;
    private final byte[] packetBuffer;
    private int packetRemaining;
    private DatagramSocket socket;
    private final int socketTimeoutMillis;
    private Uri uri;

    public static final class UdpDataSourceException extends DataSourceException {
        public UdpDataSourceException(Throwable r1, int r2) {
            super(r1, r2);
        }
    }

    public UdpDataSource() {
        this(CastStatusCodes.AUTHENTICATION_FAILED);
    }

    public UdpDataSource(int r2) {
        this(r2, 8000);
    }

    public UdpDataSource(int r3, int r4) {
        super(true);
        this.socketTimeoutMillis = r4;
        byte[] r42 = new byte[r3];
        this.packetBuffer = r42;
        this.packet = new DatagramPacket(r42, 0, r3);
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() {
        this.uri = null;
        MulticastSocket r1 = this.multicastSocket;
        if (r1 != null) goto L14;
    L6:
        DatagramSocket r12 = this.socket;
        if (r12 == null) goto L9;
        r12.close();
        this.socket = null;
    L9:
        this.address = null;
        this.packetRemaining = 0;
        if (this.opened == false) goto L16;
        this.opened = false;
        transferEnded();
        return;
    L16:
        return;
    L14:
        r1.leaveGroup((InetAddress) Assertions.checkNotNull(this.address));     // Catch: IOException -> L13
    L5:
        this.multicastSocket = null;
        goto L6
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        return this.uri;
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec r4) {
        Uri r0 = r4.uri;
        this.uri = r0;
        String r02 = (String) Assertions.checkNotNull(r0.getHost());
        int r1 = this.uri.getPort();
        transferInitializing(r4);
        this.address = InetAddress.getByName(r02);     // Catch: IOException -> L10 SecurityException -> L13
        InetSocketAddress r03 = new InetSocketAddress(this.address, r1);     // Catch: IOException -> L10 SecurityException -> L13
        if (this.address.isMulticastAddress() == false) goto L6;
        MulticastSocket r12 = new MulticastSocket(r03);     // Catch: IOException -> L10 SecurityException -> L13
        this.multicastSocket = r12;     // Catch: IOException -> L10 SecurityException -> L13
        r12.joinGroup(this.address);     // Catch: IOException -> L10 SecurityException -> L13
        this.socket = this.multicastSocket;     // Catch: IOException -> L10 SecurityException -> L13
    L7:
        this.socket.setSoTimeout(this.socketTimeoutMillis);     // Catch: IOException -> L10 SecurityException -> L13
        this.opened = true;
        transferStarted(r4);
        return -1;
    L6:
        this.socket = new DatagramSocket(r03);     // Catch: IOException -> L10 SecurityException -> L13
    L10:
        e = move-exception;
        throw new UdpDataSourceException(e, CastStatusCodes.INVALID_REQUEST);
    L13:
        e = move-exception;
        throw new UdpDataSourceException(e, CastStatusCodes.MESSAGE_TOO_LARGE);
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] r3, int r4, int r5) {
        if (r5 != 0) goto L6;
        return 0;
    L6:
        if (this.packetRemaining == 0) goto L17;
    L15:
        int r0 = this.packet.getLength();
        int r1 = this.packetRemaining;
        int r52 = Math.min(r1, r5);
        System.arraycopy(this.packetBuffer, r0 - r1, r3, r4, r52);
        this.packetRemaining -= r52;
        return r52;
    L17:
        ((DatagramSocket) Assertions.checkNotNull(this.socket)).receive(this.packet);     // Catch: IOException -> L9 SocketTimeoutException -> L12
        int r02 = this.packet.getLength();
        this.packetRemaining = r02;
        bytesTransferred(r02);
        goto L15
    L12:
        e = move-exception;
        throw new UdpDataSourceException(e, CastStatusCodes.CANCELED);
    L9:
        e = move-exception;
        throw new UdpDataSourceException(e, CastStatusCodes.INVALID_REQUEST);
    }
}
