package androidx.media3.exoplayer.hls;

import androidx.media3.datasource.DataSource;

/* loaded from: classes4.dex */
public final class DefaultHlsDataSourceFactory implements HlsDataSourceFactory {
    private final DataSource.Factory dataSourceFactory;

    public DefaultHlsDataSourceFactory(DataSource.Factory r1) {
        this.dataSourceFactory = r1;
    }

    @Override // androidx.media3.exoplayer.hls.HlsDataSourceFactory
    public DataSource createDataSource(int r1) {
        return this.dataSourceFactory.createDataSource();
    }
}
