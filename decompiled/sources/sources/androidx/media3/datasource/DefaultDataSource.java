package androidx.media3.datasource;

import android.content.Context;
import android.net.Uri;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultHttpDataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public final class DefaultDataSource implements DataSource {
    private DataSource assetDataSource;
    private final DataSource baseDataSource;
    private DataSource contentDataSource;
    private final Context context;
    private DataSource dataSchemeDataSource;
    private DataSource dataSource;
    private DataSource fileDataSource;
    private DataSource rawResourceDataSource;
    private DataSource rtmpDataSource;
    private final List<TransferListener> transferListeners;
    private DataSource udpDataSource;

    public static final class Factory implements DataSource.Factory {
        private final DataSource.Factory baseDataSourceFactory;
        private final Context context;
        private TransferListener transferListener;

        public Factory(Context r2) {
            this(r2, new DefaultHttpDataSource.Factory());
        }

        public Factory(Context r1, DataSource.Factory r2) {
            this.context = r1.getApplicationContext();
            this.baseDataSourceFactory = (DataSource.Factory) Assertions.checkNotNull(r2);
        }

        @Override // androidx.media3.datasource.DataSource.Factory
        public /* bridge */ /* synthetic */ DataSource createDataSource() {
            return createDataSource();
        }

        @Override // androidx.media3.datasource.DataSource.Factory
        public DefaultDataSource createDataSource() {
            DefaultDataSource r0 = new DefaultDataSource(this.context, this.baseDataSourceFactory.createDataSource());
            TransferListener r3 = this.transferListener;
            if (r3 == null) goto L5;
            r0.addTransferListener(r3);
        L5:
            return r0;
        }
    }

    public DefaultDataSource(Context r1, DataSource r2) {
        this.context = r1.getApplicationContext();
        this.baseDataSource = (DataSource) Assertions.checkNotNull(r2);
        this.transferListeners = new ArrayList();
    }

    private void addListenersToDataSource(DataSource r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.transferListeners.size()) goto L6;
        r3.addTransferListener(this.transferListeners.get(r0));
        r0 = r0 + 1;
        goto L4
    }

    private DataSource getAssetDataSource() {
        if (this.assetDataSource != null) goto L6;
        AssetDataSource r0 = new AssetDataSource(this.context);
        this.assetDataSource = r0;
        addListenersToDataSource(r0);
    L6:
        return this.assetDataSource;
    }

    private DataSource getContentDataSource() {
        if (this.contentDataSource != null) goto L6;
        ContentDataSource r0 = new ContentDataSource(this.context);
        this.contentDataSource = r0;
        addListenersToDataSource(r0);
    L6:
        return this.contentDataSource;
    }

    private DataSource getDataSchemeDataSource() {
        if (this.dataSchemeDataSource != null) goto L6;
        DataSchemeDataSource r0 = new DataSchemeDataSource();
        this.dataSchemeDataSource = r0;
        addListenersToDataSource(r0);
    L6:
        return this.dataSchemeDataSource;
    }

    private DataSource getFileDataSource() {
        if (this.fileDataSource != null) goto L6;
        FileDataSource r0 = new FileDataSource();
        this.fileDataSource = r0;
        addListenersToDataSource(r0);
    L6:
        return this.fileDataSource;
    }

    private DataSource getRawResourceDataSource() {
        if (this.rawResourceDataSource != null) goto L6;
        RawResourceDataSource r0 = new RawResourceDataSource(this.context);
        this.rawResourceDataSource = r0;
        addListenersToDataSource(r0);
    L6:
        return this.rawResourceDataSource;
    }

    private DataSource getRtmpDataSource() {
        if (this.rtmpDataSource != null) goto L15;
        DataSource r1 = (DataSource) Class.forName("androidx.media3.datasource.rtmp.RtmpDataSource").getConstructor(null).newInstance(null);     // Catch: Exception -> L7 ClassNotFoundException -> L10
        this.rtmpDataSource = r1;     // Catch: Exception -> L7 ClassNotFoundException -> L10
        addListenersToDataSource(r1);     // Catch: Exception -> L7 ClassNotFoundException -> L10
    L12:
        if (this.rtmpDataSource != null) goto L15;
        this.rtmpDataSource = this.baseDataSource;
    L10:
        Log.w("DefaultDataSource", "Attempting to play RTMP stream without depending on the RTMP extension");
    L7:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Error instantiating RTMP extension", e);
        return null;
    L15:
        return this.rtmpDataSource;
    }

    private DataSource getUdpDataSource() {
        if (this.udpDataSource != null) goto L6;
        UdpDataSource r0 = new UdpDataSource();
        this.udpDataSource = r0;
        addListenersToDataSource(r0);
    L6:
        return this.udpDataSource;
    }

    private void maybeAddListenerToDataSource(DataSource r1, TransferListener r2) {
        if (r1 == null) goto L5;
        r1.addTransferListener(r2);
        return;
    }

    @Override // androidx.media3.datasource.DataSource
    public void addTransferListener(TransferListener r2) {
        Assertions.checkNotNull(r2);
        this.baseDataSource.addTransferListener(r2);
        this.transferListeners.add(r2);
        maybeAddListenerToDataSource(this.fileDataSource, r2);
        maybeAddListenerToDataSource(this.assetDataSource, r2);
        maybeAddListenerToDataSource(this.contentDataSource, r2);
        maybeAddListenerToDataSource(this.rtmpDataSource, r2);
        maybeAddListenerToDataSource(this.udpDataSource, r2);
        maybeAddListenerToDataSource(this.dataSchemeDataSource, r2);
        maybeAddListenerToDataSource(this.rawResourceDataSource, r2);
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() {
        DataSource r0 = this.dataSource;
        if (r0 != null) goto L12;
        return;
    L12:
        r0.close();     // Catch: Throwable -> L8
        this.dataSource = null;
        return;
    L8:
        th = move-exception;
        this.dataSource = null;
        throw th;
    }

    @Override // androidx.media3.datasource.DataSource
    public Map<String, List<String>> getResponseHeaders() {
        DataSource r0 = this.dataSource;
        if (r0 != null) goto L7;
        return Collections.EMPTY_MAP;
    L7:
        return r0.getResponseHeaders();
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        DataSource r0 = this.dataSource;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.getUri();
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec r3) {
        if (this.dataSource != null) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        String r02 = r3.uri.getScheme();
        if (Util.isLocalFileUri(r3.uri) == false) goto L15;
        String r03 = r3.uri.getPath();
        if (r03 != null) goto L11;
    L13:
        this.dataSource = getFileDataSource();
    L37:
        return this.dataSource.open(r3);
    L11:
        if (r03.startsWith("/android_asset/") == false) goto L13;
        this.dataSource = getAssetDataSource();
        goto L37
    L15:
        if ("asset".equals(r02) == false) goto L18;
        this.dataSource = getAssetDataSource();
        goto L37
    L18:
        if ("content".equals(r02) == false) goto L21;
        this.dataSource = getContentDataSource();
        goto L37
    L21:
        if ("rtmp".equals(r02) == false) goto L24;
        this.dataSource = getRtmpDataSource();
        goto L37
    L24:
        if ("udp".equals(r02) == false) goto L27;
        this.dataSource = getUdpDataSource();
        goto L37
    L27:
        if ("data".equals(r02) == false) goto L30;
        this.dataSource = getDataSchemeDataSource();
        goto L37
    L30:
        if ("rawresource".equals(r02) == false) goto L32;
    L35:
        this.dataSource = getRawResourceDataSource();
        goto L37
    L32:
        if ("android.resource".equals(r02) == true) goto L35;
        this.dataSource = this.baseDataSource;
        goto L37
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] r1, int r2, int r3) {
        return ((DataSource) Assertions.checkNotNull(this.dataSource)).read(r1, r2, r3);
    }
}
