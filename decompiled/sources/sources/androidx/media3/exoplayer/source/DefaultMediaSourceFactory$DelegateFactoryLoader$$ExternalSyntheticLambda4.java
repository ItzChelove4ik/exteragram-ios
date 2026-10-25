package androidx.media3.exoplayer.source;

import androidx.media3.datasource.DataSource;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import com.google.common.base.Supplier;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda4 implements Supplier {
    public final /* synthetic */ DefaultMediaSourceFactory.DelegateFactoryLoader f$0;
    public final /* synthetic */ DataSource.Factory f$1;

    public /* synthetic */ DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda4(DefaultMediaSourceFactory.DelegateFactoryLoader r1, DataSource.Factory r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.common.base.Supplier
    public final Object get() {
        return DefaultMediaSourceFactory.DelegateFactoryLoader.m836$r8$lambda$E_0PBF3HPPlg0UUubJ9obL998(this.f$0, this.f$1);
    }
}
