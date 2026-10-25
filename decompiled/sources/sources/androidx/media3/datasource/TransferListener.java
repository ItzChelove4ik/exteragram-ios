package androidx.media3.datasource;

/* loaded from: classes3.dex */
public interface TransferListener {
    void onBytesTransferred(DataSource r1, DataSpec r2, boolean r3, int r4);

    void onTransferEnd(DataSource r1, DataSpec r2, boolean r3);

    void onTransferInitializing(DataSource r1, DataSpec r2, boolean r3);

    void onTransferStart(DataSource r1, DataSpec r2, boolean r3);
}
