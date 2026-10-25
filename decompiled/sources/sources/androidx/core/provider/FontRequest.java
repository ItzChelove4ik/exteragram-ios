package androidx.core.provider;

import android.util.Base64;
import androidx.core.util.Preconditions;
import java.util.List;

/* loaded from: classes.dex */
public final class FontRequest {
    private final List<List<byte[]>> mCertificates;
    private final int mCertificatesArray;
    private final String mIdentifier;
    private final String mProviderAuthority;
    private final String mProviderPackage;
    private final String mQuery;
    private final String mSystemFont;
    private final String mVariationSettings;

    public FontRequest(String r8, String r9, String r10, List<List<byte[]>> r11) {
        this(r8, r9, r10, r11, null, null);
    }

    public FontRequest(String r7, String r8, String r9, List<List<byte[]>> r10, String r11, String r12) {
        this.mProviderAuthority = (String) Preconditions.checkNotNull(r7);
        this.mProviderPackage = (String) Preconditions.checkNotNull(r8);
        this.mQuery = (String) Preconditions.checkNotNull(r9);
        this.mCertificates = (List) Preconditions.checkNotNull(r10);
        this.mCertificatesArray = 0;
        this.mSystemFont = r11;
        this.mVariationSettings = r12;
        this.mIdentifier = createIdentifier(r7, r8, r9, r11, r12);
    }

    private String createIdentifier(String r1, String r2, String r3, String r4, String r5) {
        return r1 + "-" + r2 + "-" + r3 + "-" + r4 + "-" + r5;
    }

    public List<List<byte[]>> getCertificates() {
        return this.mCertificates;
    }

    public int getCertificatesArrayResId() {
        return this.mCertificatesArray;
    }

    public String getId() {
        return this.mIdentifier;
    }

    public String getProviderAuthority() {
        return this.mProviderAuthority;
    }

    public String getProviderPackage() {
        return this.mProviderPackage;
    }

    public String getQuery() {
        return this.mQuery;
    }

    public String getSystemFont() {
        return this.mSystemFont;
    }

    public String getVariationSettings() {
        return this.mVariationSettings;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        r0.append("FontRequest {mProviderAuthority: " + this.mProviderAuthority + ", mProviderPackage: " + this.mProviderPackage + ", mQuery: " + this.mQuery + ", mSystemFont: " + this.mSystemFont + ", mVariationSettings: " + this.mVariationSettings + ", mCertificates:");
        int r2 = 0;
    L4:
        if (r2 >= this.mCertificates.size()) goto L10;
        r0.append(" [");
        List<byte[]> r3 = this.mCertificates.get(r2);
        int r4 = 0;
    L7:
        if (r4 >= r3.size()) goto L9;
        r0.append(" \"");
        r0.append(Base64.encodeToString(r3.get(r4), 0));
        r0.append("\"");
        r4 = r4 + 1;
        goto L7
    L9:
        r0.append(" ]");
        r2 = r2 + 1;
        goto L4
    L10:
        r0.append("}");
        r0.append("mCertificatesArray: " + this.mCertificatesArray);
        return r0.toString();
    }
}
