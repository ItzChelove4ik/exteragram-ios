package org.telegram.messenger.secretmedia;

import android.content.Context;
import android.net.Uri;
import android.util.LongSparseArray;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.datasource.TransferListener;

/* loaded from: classes3.dex */
public final class ExtendedDefaultDataSourceFactory implements DataSource.Factory {
    private final DataSource.Factory baseDataSourceFactory;
    private final Context context;
    private final TransferListener listener;
    private final LongSparseArray<Uri> mtprotoUris;

    public ExtendedDefaultDataSourceFactory(Context r2, TransferListener r3, DataSource.Factory r4) {
        this.mtprotoUris = new LongSparseArray();
        this.context = r2.getApplicationContext();
        this.listener = r3;
        this.baseDataSourceFactory = r4;
    }

    public ExtendedDefaultDataSourceFactory(Context r2, String r3) {
        this(r2, r3, null);
    }

    public ExtendedDefaultDataSourceFactory(Context r2, String r3, TransferListener r4) {
        this(r2, r4, new DefaultHttpDataSource.Factory().setUserAgent(r3).setTransferListener(r4).setAllowCrossProtocolRedirects(true));
    }

    @Override // androidx.media3.datasource.DataSource.Factory
    public /* bridge */ /* synthetic */ DataSource createDataSource() {
        return createDataSource();
    }

    @Override // androidx.media3.datasource.DataSource.Factory
    public ExtendedDefaultDataSource createDataSource() {
        return new ExtendedDefaultDataSource(this.context, this.listener, this.baseDataSourceFactory.createDataSource(), this.mtprotoUris);
    }

    public void putDocumentUri(long r1, Uri r3) {
        this.mtprotoUris.put(r1, r3);
    }
}
