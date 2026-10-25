package androidx.media3.datasource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BitmapLoader;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultDataSource;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Executors;

/* loaded from: classes3.dex */
public final class DataSourceBitmapLoader implements BitmapLoader {
    public static final Supplier<ListeningExecutorService> DEFAULT_EXECUTOR_SERVICE = null;
    private final DataSource.Factory dataSourceFactory;
    private final ListeningExecutorService listeningExecutorService;
    private final int maximumOutputDimension;
    private final BitmapFactory.Options options;

    /* renamed from: $r8$lambda$H42hWp0ryWsU1xY-Gn8RWcDN7j4, reason: not valid java name */
    public static /* synthetic */ Bitmap m760$r8$lambda$H42hWp0ryWsU1xYGn8RWcDN7j4(DataSourceBitmapLoader r2, byte[] r3) {
        r2.getClass();
        return BitmapUtil.decode(r3, r3.length, r2.options, r2.maximumOutputDimension);
    }

    public static /* synthetic */ ListeningExecutorService $r8$lambda$ctPCnFN_Nvj3nel8GNv7G1INN2M() {
        return MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
    }

    /* renamed from: $r8$lambda$wBXkEb6hTn31wBT_xYYREa2dG-8, reason: not valid java name */
    public static /* synthetic */ Bitmap m761$r8$lambda$wBXkEb6hTn31wBT_xYYREa2dG8(DataSourceBitmapLoader r2, Uri r3) {
        return load(r2.dataSourceFactory.createDataSource(), r3, r2.options, r2.maximumOutputDimension);
    }

    static {
        DEFAULT_EXECUTOR_SERVICE = Suppliers.memoize(new DataSourceBitmapLoader$$ExternalSyntheticLambda0());
    }

    public DataSourceBitmapLoader(Context r3) {
        this((ListeningExecutorService) Assertions.checkStateNotNull(DEFAULT_EXECUTOR_SERVICE.get()), new DefaultDataSource.Factory(r3));
    }

    public DataSourceBitmapLoader(ListeningExecutorService r2, DataSource.Factory r3) {
        this(r2, r3, null);
    }

    public DataSourceBitmapLoader(ListeningExecutorService r2, DataSource.Factory r3, BitmapFactory.Options r4) {
        this(r2, r3, r4, -1);
    }

    public DataSourceBitmapLoader(ListeningExecutorService r1, DataSource.Factory r2, BitmapFactory.Options r3, int r4) {
        this.listeningExecutorService = r1;
        this.dataSourceFactory = r2;
        this.options = r3;
        this.maximumOutputDimension = r4;
    }

    private static Bitmap load(DataSource r1, Uri r2, BitmapFactory.Options r3, int r4) {
        r1.open(new DataSpec(r2));     // Catch: Throwable -> L5
        byte[] r22 = DataSourceUtil.readToEnd(r1);     // Catch: Throwable -> L5
        Bitmap r23 = BitmapUtil.decode(r22, r22.length, r3, r4);     // Catch: Throwable -> L5
        r1.close();
        return r23;
    L5:
        th = move-exception;
        r1.close();
        throw th;
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public ListenableFuture<Bitmap> decodeBitmap(byte[] r3) {
        return this.listeningExecutorService.submit(new DataSourceBitmapLoader$$ExternalSyntheticLambda1(this, r3));
    }

    @Override // androidx.media3.common.util.BitmapLoader
    public ListenableFuture<Bitmap> loadBitmap(Uri r3) {
        return this.listeningExecutorService.submit(new DataSourceBitmapLoader$$ExternalSyntheticLambda2(this, r3));
    }
}
