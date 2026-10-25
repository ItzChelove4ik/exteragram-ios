package androidx.media3.extractor.text;

import androidx.media3.common.Format;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.dvb.DvbParser;
import androidx.media3.extractor.text.pgs.PgsParser;
import androidx.media3.extractor.text.ssa.SsaParser;
import androidx.media3.extractor.text.subrip.SubripParser;
import androidx.media3.extractor.text.ttml.TtmlParser;
import androidx.media3.extractor.text.tx3g.Tx3gParser;
import androidx.media3.extractor.text.vobsub.VobsubParser;
import androidx.media3.extractor.text.webvtt.Mp4WebvttParser;
import androidx.media3.extractor.text.webvtt.WebvttParser;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class DefaultSubtitleParserFactory implements SubtitleParser.Factory {
    public DefaultSubtitleParserFactory() {
    }

    @Override // androidx.media3.extractor.text.SubtitleParser.Factory
    public SubtitleParser create(Format r3) {
        String r2 = r3.sampleMimeType;
        if (r2 == null) goto L63;
        char r1 = 65535;
        switch(r2.hashCode()) {
            case -1351681404: goto L40;
            case -1248334819: goto L36;
            case -1026075066: goto L32;
            case -1004728940: goto L28;
            case 691401887: goto L24;
            case 822864842: goto L20;
            case 1157994102: goto L16;
            case 1668750253: goto L12;
            case 1693976202: goto L8;
            default: goto L43;
        };
    L43:
        switch(r1) {
            case 0: goto L62;
            case 1: goto L60;
            case 2: goto L58;
            case 3: goto L56;
            case 4: goto L54;
            case 5: goto L52;
            case 6: goto L50;
            case 7: goto L48;
            case 8: goto L46;
            default: goto L63;
        };
    L46:
        return new TtmlParser();
    L48:
        return new SubripParser();
    L50:
        return new VobsubParser(r3.initializationData);
    L52:
        return new SsaParser(r3.initializationData);
    L54:
        return new Tx3gParser(r3.initializationData);
    L56:
        return new WebvttParser();
    L58:
        return new Mp4WebvttParser();
    L60:
        return new PgsParser();
    L62:
        return new DvbParser(r3.initializationData);
    L8:
        if (r2.equals("application/ttml+xml") == false) goto L43;
        r1 = '\b';
        goto L43
    L12:
        if (r2.equals("application/x-subrip") == false) goto L43;
        r1 = 7;
        goto L43
    L16:
        if (r2.equals("application/vobsub") == false) goto L43;
        r1 = 6;
        goto L43
    L20:
        if (r2.equals("text/x-ssa") == false) goto L43;
        r1 = 5;
        goto L43
    L24:
        if (r2.equals("application/x-quicktime-tx3g") == false) goto L43;
        r1 = 4;
        goto L43
    L28:
        if (r2.equals("text/vtt") == false) goto L43;
        r1 = 3;
        goto L43
    L32:
        if (r2.equals("application/x-mp4-vtt") == false) goto L43;
        r1 = 2;
        goto L43
    L36:
        if (r2.equals("application/pgs") == false) goto L43;
        r1 = 1;
        goto L43
    L40:
        if (r2.equals("application/dvbsubs") == false) goto L43;
        r1 = 0;
    L63:
        Native$$ExternalSyntheticBUOutline5.m("Unsupported MIME type: ", r2);
        return null;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser.Factory
    public int getCueReplacementBehavior(Format r5) {
        String r4 = r5.sampleMimeType;
        if (r4 == null) goto L51;
        char r3 = 65535;
        switch(r4.hashCode()) {
            case -1351681404: goto L40;
            case -1248334819: goto L36;
            case -1026075066: goto L32;
            case -1004728940: goto L28;
            case 691401887: goto L24;
            case 822864842: goto L20;
            case 1157994102: goto L16;
            case 1668750253: goto L12;
            case 1693976202: goto L8;
            default: goto L43;
        };
    L43:
        switch(r3) {
            case 0: goto L50;
            case 1: goto L50;
            case 2: goto L50;
            case 3: goto L49;
            case 4: goto L48;
            case 5: goto L47;
            case 6: goto L46;
            case 7: goto L45;
            case 8: goto L45;
            default: goto L51;
        };
    L45:
        return 1;
    L46:
        return 2;
    L47:
        return 1;
    L48:
        return 2;
    L49:
        return 1;
    L50:
        return 2;
    L8:
        if (r4.equals("application/ttml+xml") == false) goto L43;
        r3 = '\b';
        goto L43
    L12:
        if (r4.equals("application/x-subrip") == false) goto L43;
        r3 = 7;
        goto L43
    L16:
        if (r4.equals("application/vobsub") == false) goto L43;
        r3 = 6;
        goto L43
    L20:
        if (r4.equals("text/x-ssa") == false) goto L43;
        r3 = 5;
        goto L43
    L24:
        if (r4.equals("application/x-quicktime-tx3g") == false) goto L43;
        r3 = 4;
        goto L43
    L28:
        if (r4.equals("text/vtt") == false) goto L43;
        r3 = 3;
        goto L43
    L32:
        if (r4.equals("application/x-mp4-vtt") == false) goto L43;
        r3 = 2;
        goto L43
    L36:
        if (r4.equals("application/pgs") == false) goto L43;
        r3 = 1;
        goto L43
    L40:
        if (r4.equals("application/dvbsubs") == false) goto L43;
        r3 = 0;
    L51:
        Native$$ExternalSyntheticBUOutline5.m("Unsupported MIME type: ", r4);
        return 0;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser.Factory
    public boolean supportsFormat(Format r1) {
        String r0 = r1.sampleMimeType;
        if (Objects.equals(r0, "text/x-ssa") == false) goto L5;
        return true;
    L5:
        if (Objects.equals(r0, "text/vtt") == false) goto L7;
        return true;
    L7:
        if (Objects.equals(r0, "application/x-mp4-vtt") == false) goto L9;
        return true;
    L9:
        if (Objects.equals(r0, "application/x-subrip") == false) goto L11;
        return true;
    L11:
        if (Objects.equals(r0, "application/x-quicktime-tx3g") == false) goto L13;
        return true;
    L13:
        if (Objects.equals(r0, "application/pgs") == false) goto L15;
        return true;
    L15:
        if (Objects.equals(r0, "application/vobsub") == false) goto L17;
        return true;
    L17:
        if (Objects.equals(r0, "application/dvbsubs") == false) goto L19;
        return true;
    L19:
        if (Objects.equals(r0, "application/ttml+xml") == true) goto L32;
        return false;
    L32:
        return true;
    }
}
