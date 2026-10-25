package org.telegram.messenger.chromecast;

import android.net.Uri;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import androidx.media3.datasource.AssetDataSource;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.FileDataSource;
import com.google.android.gms.cast.MediaMetadata;
import fi.iki.elonen.NanoHTTPD;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.UByte;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.chromecast.ChromecastMedia;
import org.telegram.messenger.secretmedia.ExtendedDefaultDataSourceFactory;

/* loaded from: classes5.dex */
public class ChromecastFileServer extends NanoHTTPD {
    public static final ChromecastMedia ASSET_FALLBACK_FILE = null;
    private static final ChromecastMedia[] ASSET_FILES = null;
    private static final HashMap<String, ChromecastMedia> ASSET_FILES_MAP = null;
    private final DataSource.Factory assetDataSourceFactory;
    private final HashMap<String, ChromecastMedia> castedFiles;
    private Pair<String, File> coverFile;
    private final DataSource.Factory fileDataSourceFactory;
    private final DataSource.Factory mediaDataSourceFactory;
    private final AtomicInteger reqId;
    private boolean started;

    public static class DataSourceInputStream extends InputStream implements InputStreamRetargetInterface {
        private long availableBytes;
        private final DataSource dataSource;
        private final byte[] tmpByte;

        public DataSourceInputStream(DataSource r2, DataSpec r3) {
            this.tmpByte = new byte[1];
            this.dataSource = r2;
            this.availableBytes = r2.open(r3);     // Catch: IOException -> L5
            return;
        L5:
            e = move-exception;
            HttpUrl$$ExternalSyntheticBUOutline0.m(e);
            throw null;
        }

        @Override // java.io.InputStream
        public int available() {
            return (int) this.availableBytes;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.dataSource.close();
        }

        @Override // java.io.InputStream
        public int read() {
            int r0 = this.dataSource.read(this.tmpByte, 0, 1);
            this.availableBytes--;
            if (r0 != (-1)) goto L6;
            return -1;
        L6:
            return this.tmpByte[0] & UByte.MAX_VALUE;
        }

        @Override // java.io.InputStream
        public int read(byte[] r3, int r4, int r5) {
            if (r5 != 0) goto L5;
            return 0;
        L5:
            int r32 = this.dataSource.read(r3, r4, r5);
            this.availableBytes -= r32;
            return r32;
        }

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public /* synthetic */ long transferTo(OutputStream r1) {
            return DesugarInputStream.transferTo(this, r1);
        }
    }

    public static class Range {
        final long end;
        final long start;

        public Range(long r1, long r3) {
            this.start = r1;
            this.end = r3;
        }
    }

    /* renamed from: $r8$lambda$RD0N-hA4v7zqKIfRfrKJ6IhXPzA, reason: not valid java name */
    public static /* synthetic */ DataSource m5143$r8$lambda$RD0NhA4v7zqKIfRfrKJ6IhXPzA() {
        return new AssetDataSource(ApplicationLoader.applicationContext);
    }

    static {
        ChromecastMedia r0 = ChromecastMedia.Builder.fromUri(Uri.parse("file:///android_asset/cast/default.png"), "/assets/default", "image/png").build();
        ASSET_FALLBACK_FILE = r0;
        ChromecastMedia[] r02 = {r0};
        ASSET_FILES = r02;
        ASSET_FILES_MAP = new HashMap();
        int r1 = r02.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        ChromecastMedia r3 = r02[r2];
        ASSET_FILES_MAP.put(r3.externalPath, r3);
        r2 = r2 + 1;
        goto L3
    }

    public ChromecastFileServer() {
        super(61578);
        this.castedFiles = new HashMap();
        this.coverFile = null;
        this.started = false;
        this.reqId = new AtomicInteger();
        this.assetDataSourceFactory = new ChromecastFileServer$$ExternalSyntheticLambda0();
        this.fileDataSourceFactory = new FileDataSource.Factory();
        this.mediaDataSourceFactory = new ExtendedDefaultDataSourceFactory(ApplicationLoader.applicationContext, "Mozilla/5.0 (X11; Linux x86_64; rv:10.0) Gecko/20150101 Firefox/47.0 (Chrome)");
    }

    private static NanoHTTPD.Response addCorsHeaders(NanoHTTPD.Response r3) {
        r3.addHeader("Access-Control-Allow-Origin", "*");
        r3.addHeader("Access-Control-Max-Age", "3628800");
        r3.addHeader("Access-Control-Allow-Methods", "*");
        r3.addHeader("Access-Control-Allow-Headers", "*");
        return r3;
    }

    private void check() {
        boolean r0 = this.castedFiles.isEmpty();
        boolean r1 = this.started;
        if (r0 == false) goto L7;
        if (r1 == false) goto L16;
        stop();
        this.started = false;
        return;
    L16:
        return;
    L7:
        if (r1 == false) goto L14;
        return;
    L14:
        start(5000, true);     // Catch: IOException -> L11
        this.started = true;     // Catch: IOException -> L11
        return;
    L11:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }

    private static String fixHlsManifest(String r1, String r2) {
        return r1.replaceAll("mtproto:", getUrlToSource(r2, "/mtproto_"));
    }

    private static String formatIp4(int r3) {
        return String.valueOf(r3 & 255) + '.' + ((r3 >> 8) & 255) + '.' + ((r3 >> 16) & 255) + '.' + ((r3 >> 24) & 255);
    }

    private DataSource.Factory getDataSourceFactory(ChromecastMedia r3) {
        if (r3.internalUri.toString().startsWith("file://") == false) goto L11;
        if (r3.internalUri.toString().startsWith("file:///android_asset/") == false) goto L9;
        return this.assetDataSourceFactory;
    L9:
        return this.fileDataSourceFactory;
    L11:
        return this.mediaDataSourceFactory;
    }

    private ChromecastMedia getFile(String r2) {
        ChromecastMedia r0 = ASSET_FILES_MAP.get(r2);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return this.castedFiles.get(r2);
    }

    public static String getHost() {
        return formatIp4(getMyLocalIp()) + ":61578";
    }

    private static int getMyLocalIp() {
        int r0 = ((WifiManager) ApplicationLoader.applicationContext.getSystemService("wifi")).getConnectionInfo().getIpAddress();
        if (r0 == 0) goto L19;
    L18:
        return r0;
    L19:
        Enumeration<NetworkInterface> r1 = NetworkInterface.getNetworkInterfaces();     // Catch: SocketException -> L14
    L5:
        if (r1.hasMoreElements() == false) goto L16;
        Enumeration<InetAddress> r2 = r1.nextElement().getInetAddresses();     // Catch: SocketException -> L14
    L9:
        if (r2.hasMoreElements() == false) goto L5;
        InetAddress r3 = r2.nextElement();     // Catch: SocketException -> L14
        if (r3.isSiteLocalAddress() == false) goto L9;
        byte[] r32 = r3.getAddress();     // Catch: SocketException -> L14
        int r4 = (((r32[0] + 256) % 256) + (((r32[1] + 256) % 256) << 8)) + (((r32[2] + 256) % 256) << 16);     // Catch: SocketException -> L14
        r0 = (((r32[3] + 256) % 256) << 24) + r4;
        goto L9
    L16:
        return r0;
    L14:
        e = move-exception;
        FileLog.e(e);
        goto L18
    }

    public static String getUrlToSource(String r2, String r3) {
        return "http://" + r2 + r3;
    }

    private static Range parseRangeHeader(String r7, long r8) {
        if (TextUtils.isEmpty(r7) == false) goto L6;
        return null;
    L6:
        String r72 = r7.trim().substring(6);
        if (r72.startsWith("-") == false) goto L9;
        long r0 = r8 - 1;
        long r5 = r0 - Long.parseLong(r72.substring(1));
    L13:
        long r82 = r8 - 1;
        if (r0 <= r82) goto L17;
        r0 = r82;
    L17:
        return new Range(r5, r0);
    L9:
        String[] r73 = r72.split("-");
        r5 = Long.parseLong(r73[0]);
        if (r73.length <= 1) goto L12;
        r0 = Long.parseLong(r73[1]);
        goto L13
    L12:
        r0 = r8 - 1;
        goto L13
    }

    private NanoHTTPD.Response serveAvailableRoutes(String r8) {
        StringBuilder r0 = new StringBuilder();
        if (this.coverFile != null) goto L5;
    L8:
        int r1 = 0;
    L10:
        if (r1 >= 2) goto L30;
        if (r1 != 0) goto L13;
        HashMap<String, ChromecastMedia> r3 = ASSET_FILES_MAP;
    L14:
        Iterator<Map.Entry<String, ChromecastMedia>> r32 = r3.entrySet().iterator();
    L16:
        if (r32.hasNext() == false) goto L28;
        Map.Entry<String, ChromecastMedia> r4 = r32.next();
        if (r0.length() <= 0) goto L20;
        r0.append('\n');
    L20:
        r0.append(getUrlToSource(r8, r4.getKey()));
        MediaMetadata r42 = r4.getValue().mediaMetadata;
        if (r42 == null) goto L16;
        String r5 = r42.getString(MediaMetadata.KEY_TITLE);
        String r43 = r42.getString(MediaMetadata.KEY_SUBTITLE);
        if (r5 == null) goto L26;
        r0.append(' ');
        r0.append(r5);
    L26:
        if (r43 == null) goto L16;
        r0.append(" [");
        r0.append(r43);
        r0.append(']');
        goto L16
    L28:
        r1 = r1 + 1;
        goto L10
    L13:
        r3 = this.castedFiles;
        goto L14
    L30:
        return NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.OK, "text/plain", r0.toString());
    L5:
        if (r0.length() <= 0) goto L7;
        r0.append('\n');
    L7:
        r0.append(getUrlToSource(r8, (String) this.coverFile.first));
        goto L8
    }

    private NanoHTTPD.Response serveFileImpl(NanoHTTPD.IHTTPSession r3, File r4) {
        return NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.OK, "image/jpeg", new BufferedInputStream(new FileInputStream(r4)), r4.length());
    }

    private NanoHTTPD.Response serveFileImpl(NanoHTTPD.IHTTPSession r11, ChromecastMedia r12) {
        String r0 = r11.getHeaders().get("host");
        if (r12.internalUri.toString().startsWith("data:application/x-mpegurl;base64,") == true) goto L5;
        DataSource r10 = getDataSourceFactory(r12).createDataSource();
        DataSpec.Builder r1 = new DataSpec.Builder().setUri(r12.internalUri);
        long r3 = r10.open(r1.build());
        r10.close();
        boolean r5 = TextUtils.equals(r12.mimeType, "application/x-mpegURL");
        if (r5 == true) goto L9;
        Range r112 = parseRangeHeader(r11.getHeaders().get("range"), r3);
    L10:
        if (r112 == null) goto L12;
        long r6 = (r112.end - r112.start) + 1;
    L13:
        if (r112 == null) goto L15;
        r1.setPosition(r112.start);
        r1.setLength(r6);
    L15:
        if (r5 == false) goto L19;
        int r113 = (int) r6;
        byte[] r32 = new byte[r113];
        r10.open(r1.build());
        r10.read(r32, 0, r113);
        r10.close();
        return NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.OK, r12.mimeType, fixHlsManifest(new String(r32), r0));
    L19:
        if (r6 == 0) goto L25;
        DataSourceInputStream r02 = new DataSourceInputStream(r10, r1.build());
        if (r112 == null) goto L23;
        NanoHTTPD.Response.Status r102 = NanoHTTPD.Response.Status.PARTIAL_CONTENT;
    L24:
        NanoHTTPD.Response r103 = NanoHTTPD.newFixedLengthResponse(r102, r12.mimeType, r02, r6);
    L26:
        if (r112 == null) goto L28;
        r103.addHeader("Content-Range", "bytes " + r112.start + "-" + r112.end + "/" + r3);
    L28:
        return r103;
    L23:
        r102 = NanoHTTPD.Response.Status.OK;
        goto L24
    L25:
        r103 = NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.NO_CONTENT, r12.mimeType, _UrlKt.FRAGMENT_ENCODE_SET);
        goto L26
    L12:
        r6 = r3;
        goto L13
    L9:
        r112 = null;
        goto L10
    L5:
        return NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.OK, r12.mimeType, fixHlsManifest(new String(Base64.decode(r12.internalUri.toString().substring(34), 0)), r0));
    }

    private NanoHTTPD.Response serveImpl(NanoHTTPD.IHTTPSession r5) {
        String r0 = r5.getHeaders().get("host");
        String r1 = Uri.parse("http://" + r0 + r5.getUri()).getPath();
        if (NanoHTTPD.Method.OPTIONS.equals(r5.getMethod()) == false) goto L7;
        return NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.OK, "text/plain", _UrlKt.FRAGMENT_ENCODE_SET);
    L7:
        if (TextUtils.equals(r1, "/") == true) goto L9;
        ChromecastMedia r02 = getFile(r1);
        if (r02 != null) goto L13;
        Pair<String, File> r03 = this.coverFile;
        if (r03 != null) goto L17;
    L19:
        File r04 = null;
    L20:
        if (r04 == null) goto L24;
        return serveFileImpl(r5, r04);
    L24:
        return NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.NOT_FOUND, "text/plain", "file not found");
    L17:
        if (((String) r03.first).equalsIgnoreCase(r1) == false) goto L19;
        r04 = (File) this.coverFile.second;
        goto L20
    L13:
        return serveFileImpl(r5, r02);
    L9:
        return serveAvailableRoutes(r0);
    }

    public void addFileToCast(ChromecastMedia r3) {
        this.castedFiles.put(r3.externalPath, r3);
        check();
    }

    public File getCoverFile() {
        Pair<String, File> r0 = this.coverFile;
        if (r0 != null) goto L7;
        return null;
    L7:
        return (File) r0.second;
    }

    public String getCoverPath() {
        Pair<String, File> r0 = this.coverFile;
        if (r0 != null) goto L7;
        return null;
    L7:
        return (String) r0.first;
    }

    public void removeFileFromCast(ChromecastMedia r2) {
        this.castedFiles.remove(r2.externalPath);
        check();
    }

    @Override // fi.iki.elonen.NanoHTTPD
    public NanoHTTPD.Response serve(NanoHTTPD.IHTTPSession r5) {
        int r0 = this.reqId.incrementAndGet();
        Log.d("CAST_SERVER", "Request " + r0 + " " + r5.getMethod() + " " + r5.getUri() + " " + r5.getHeaders().get("range"));
        return addCorsHeaders(serveImpl(r5));
    L5:
        Log.d("CAST_SERVER", "Error " + r0);
        return addCorsHeaders(NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.INTERNAL_ERROR, "text/plain", "Error reading file"));
    }

    public void setCoverFile(String r2, File r3) {
        if (r2 == null) goto L6;
        if (r3 == null) goto L6;
        this.coverFile = new Pair(r2, r3);
    L12:
        check();
        return;
    L6:
        Pair<String, File> r22 = this.coverFile;
        if (r22 != null) goto L9;
    L11:
        this.coverFile = null;
        goto L12
    L9:
        if (((File) r22.second).exists() == false) goto L11;
        ((File) this.coverFile.second).delete();     // Catch: Exception -> L14
        goto L11
    }
}
