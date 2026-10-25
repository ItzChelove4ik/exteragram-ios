package androidx.media3.extractor.metadata.dvbsi;

import androidx.media3.common.Metadata;

/* loaded from: classes4.dex */
public final class AppInfoTable implements Metadata.Entry {
    public final int controlCode;
    public final String url;

    public AppInfoTable(int r1, String r2) {
        this.controlCode = r1;
        this.url = r2;
    }

    public String toString() {
        return "Ait(controlCode=" + this.controlCode + ",url=" + this.url + ")";
    }
}
