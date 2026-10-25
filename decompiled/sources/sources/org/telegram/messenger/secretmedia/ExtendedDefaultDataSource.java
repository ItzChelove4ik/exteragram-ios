package org.telegram.messenger.secretmedia;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.util.LongSparseArray;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.AssetDataSource;
import androidx.media3.datasource.ContentDataSource;
import androidx.media3.datasource.DataSchemeDataSource;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.datasource.FileDataSource;
import androidx.media3.datasource.RawResourceDataSource;
import androidx.media3.datasource.TransferListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.FileStreamLoadOperation;

/* loaded from: classes3.dex */
public final class ExtendedDefaultDataSource implements DataSource {
    private static final String SCHEME_ASSET = "asset";
    private static final String SCHEME_CONTENT = "content";
    private static final String SCHEME_RAW = "rawresource";
    private static final String SCHEME_RTMP = "rtmp";
    private static final String TAG = "ExtendedDefaultDataSource";
    private DataSource assetDataSource;
    private final DataSource baseDataSource;
    private DataSource contentDataSource;
    private final Context context;
    private DataSource dataSchemeDataSource;
    private DataSource dataSource;
    private DataSource encryptedFileDataSource;
    private DataSource fileDataSource;
    private final LongSparseArray<Uri> mtprotoUris;
    private DataSource rawResourceDataSource;
    private DataSource rtmpDataSource;
    private FileStreamLoadOperation streamLoadOperation;
    private final List<TransferListener> transferListeners;

    public ExtendedDefaultDataSource(Context r1, DataSource r2, LongSparseArray<Uri> r3) {
        this.context = r1.getApplicationContext();
        this.baseDataSource = (DataSource) Assertions.checkNotNull(r2);
        this.transferListeners = new ArrayList();
        this.mtprotoUris = r3;
    }

    @Deprecated
    public ExtendedDefaultDataSource(Context r1, TransferListener r2, DataSource r3, LongSparseArray<Uri> r4) {
        this(r1, r3, r4);
        if (r2 == null) goto L6;
        this.transferListeners.add(r2);
        r3.addTransferListener(r2);
        return;
    }

    public ExtendedDefaultDataSource(Context r2, String r3, int r4, int r5, boolean r6) {
        this(r2, new DefaultHttpDataSource.Factory().setUserAgent(r3).setConnectTimeoutMs(r4).setReadTimeoutMs(r5).setAllowCrossProtocolRedirects(r6).createDataSource(), null);
    }

    public ExtendedDefaultDataSource(Context r7, String r8, boolean r9) {
        this(r7, r8, 8000, 8000, r9);
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

    private DataSource getEncryptedFileDataSource() {
        if (this.encryptedFileDataSource != null) goto L6;
        EncryptedFileDataSource r0 = new EncryptedFileDataSource();
        this.encryptedFileDataSource = r0;
        addListenersToDataSource(r0);
    L6:
        return this.encryptedFileDataSource;
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
        DataSource r1 = (DataSource) Class.forName("com.google.android.exoplayer2.ext.rtmp.RtmpDataSource").getConstructor(null).newInstance(null);     // Catch: Exception -> L7 ClassNotFoundException -> L10
        this.rtmpDataSource = r1;     // Catch: Exception -> L7 ClassNotFoundException -> L10
        addListenersToDataSource(r1);     // Catch: Exception -> L7 ClassNotFoundException -> L10
    L12:
        if (this.rtmpDataSource != null) goto L15;
        this.rtmpDataSource = this.baseDataSource;
    L10:
        Log.w(TAG, "Attempting to play RTMP stream without depending on the RTMP extension");
    L7:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Error instantiating RTMP extension", e);
        return null;
    L15:
        return this.rtmpDataSource;
    }

    private DataSource getStreamDataSource() {
        if (this.streamLoadOperation != null) goto L6;
        FileStreamLoadOperation r0 = new FileStreamLoadOperation();
        this.streamLoadOperation = r0;
        addListenersToDataSource(r0);
    L6:
        return this.streamLoadOperation;
    }

    private void maybeAddListenerToDataSource(DataSource r1, TransferListener r2) {
        if (r1 == null) goto L5;
        r1.addTransferListener(r2);
        return;
    }

    @Override // androidx.media3.datasource.DataSource
    public void addTransferListener(TransferListener r2) {
        this.baseDataSource.addTransferListener(r2);
        this.transferListeners.add(r2);
        maybeAddListenerToDataSource(this.fileDataSource, r2);
        maybeAddListenerToDataSource(this.assetDataSource, r2);
        maybeAddListenerToDataSource(this.contentDataSource, r2);
        maybeAddListenerToDataSource(this.rtmpDataSource, r2);
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
    public long open(DataSpec r4) {
        if (this.dataSource != null) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        Uri r02 = r4.uri;
        if ("mtproto".equals(r02.getScheme()) == false) goto L9;
        long r03 = Long.parseLong(r4.uri.toString().substring(8));
        r02 = this.mtprotoUris.get(r03);
        r4 = r4.buildUpon().setUri(r02).build();
    L9:
        String r1 = r02.getScheme();
        if (Util.isLocalFileUri(r02) == false) goto L21;
        String r12 = r02.getPath();
        if (r12 == null) goto L17;
        if (r12.startsWith("/android_asset/") == false) goto L17;
        this.dataSource = getAssetDataSource();
    L40:
        return this.dataSource.open(r4);
    L17:
        if (r02.getPath().endsWith(".enc") == false) goto L19;
        this.dataSource = getEncryptedFileDataSource();
        goto L40
    L19:
        this.dataSource = getFileDataSource();
        goto L40
    L21:
        if ("tg".equals(r1) == false) goto L24;
        this.dataSource = getStreamDataSource();
        goto L40
    L24:
        if (SCHEME_ASSET.equals(r1) == false) goto L27;
        this.dataSource = getAssetDataSource();
        goto L40
    L27:
        if (SCHEME_CONTENT.equals(r1) == false) goto L30;
        this.dataSource = getContentDataSource();
        goto L40
    L30:
        if (SCHEME_RTMP.equals(r1) == false) goto L33;
        this.dataSource = getRtmpDataSource();
        goto L40
    L33:
        if ("data".equals(r1) == false) goto L36;
        this.dataSource = getDataSchemeDataSource();
        goto L40
    L36:
        if (SCHEME_RAW.equals(r1) == false) goto L38;
        this.dataSource = getRawResourceDataSource();
        goto L40
    L38:
        this.dataSource = this.baseDataSource;
        goto L40
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] r1, int r2, int r3) {
        return ((DataSource) Assertions.checkNotNull(this.dataSource)).read(r1, r2, r3);
    }
}
