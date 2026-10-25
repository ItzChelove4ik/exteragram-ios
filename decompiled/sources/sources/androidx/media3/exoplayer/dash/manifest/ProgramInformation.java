package androidx.media3.exoplayer.dash.manifest;

import java.util.Objects;

/* loaded from: classes4.dex */
public final class ProgramInformation {
    public final String copyright;
    public final String lang;
    public final String moreInformationURL;
    public final String source;
    public final String title;

    public ProgramInformation(String r1, String r2, String r3, String r4, String r5) {
        this.title = r1;
        this.source = r2;
        this.copyright = r3;
        this.moreInformationURL = r4;
        this.lang = r5;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof ProgramInformation) == true) goto L8;
        return false;
    L8:
        ProgramInformation r52 = (ProgramInformation) r5;
        if (Objects.equals(this.title, r52.title) == true) goto L11;
    L19:
        return false;
    L11:
        if (Objects.equals(this.source, r52.source) == false) goto L19;
        if (Objects.equals(this.copyright, r52.copyright) == false) goto L19;
        if (Objects.equals(this.moreInformationURL, r52.moreInformationURL) == false) goto L19;
        if (Objects.equals(this.lang, r52.lang) == false) goto L19;
        return true;
    }

    public int hashCode() {
        String r0 = this.title;
        int r1 = 0;
        if (r0 == null) goto L5;
        int r02 = r0.hashCode();
    L6:
        int r2 = (527 + r02) * 31;
        String r03 = this.source;
        if (r03 == null) goto L9;
        int r04 = r03.hashCode();
    L10:
        int r22 = (r2 + r04) * 31;
        String r05 = this.copyright;
        if (r05 == null) goto L13;
        int r06 = r05.hashCode();
    L14:
        int r23 = (r22 + r06) * 31;
        String r07 = this.moreInformationURL;
        if (r07 == null) goto L17;
        int r08 = r07.hashCode();
    L18:
        int r24 = (r23 + r08) * 31;
        String r3 = this.lang;
        if (r3 == null) goto L22;
        r1 = r3.hashCode();
    L22:
        return r24 + r1;
    L17:
        r08 = 0;
        goto L18
    L13:
        r06 = 0;
        goto L14
    L9:
        r04 = 0;
        goto L10
    L5:
        r02 = 0;
        goto L6
    }
}
