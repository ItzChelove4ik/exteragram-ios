package org.telegram.ui.Components;

import androidx.media3.datasource.DataSource;

/* loaded from: classes7.dex */
public final /* synthetic */ class VideoPlayer$$ExternalSyntheticLambda1 implements DataSource.Factory {
    public final /* synthetic */ VideoPlayer f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ VideoPlayer$$ExternalSyntheticLambda1(VideoPlayer r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.datasource.DataSource.Factory
    public final DataSource createDataSource() {
        return VideoPlayer.$r8$lambda$jWh9mJDcbrkxazS0DTsXGT26cNw(this.f$0, this.f$1);
    }
}
