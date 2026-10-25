package androidx.media3.exoplayer.dash.manifest;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class UrlTemplate {
    private final List<String> identifierFormatTags;
    private final List<Integer> identifiers;
    private final List<String> urlPieces;

    private UrlTemplate(List<String> r1, List<Integer> r2, List<String> r3) {
        this.urlPieces = r1;
        this.identifiers = r2;
        this.identifierFormatTags = r3;
    }

    public static UrlTemplate compile(String r3) {
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        ArrayList r2 = new ArrayList();
        parseTemplate(r3, r0, r1, r2);
        return new UrlTemplate(r0, r1, r2);
    }

    private static void parseTemplate(String r10, List<String> r11, List<Integer> r12, List<String> r13) {
        r11.add(_UrlKt.FRAGMENT_ENCODE_SET);
        int r2 = 0;
    L4:
        if (r2 >= r10.length()) goto L50;
        int r4 = r10.indexOf("$", r2);
        char r5 = 65535;
        if (r4 == (-1)) goto L7;
        if (r4 != r2) goto L9;
        if (r10.startsWith("$$", r2) == true) goto L12;
        r13.add(_UrlKt.FRAGMENT_ENCODE_SET);
        int r22 = r2 + 1;
        int r3 = r10.indexOf("$", r22);
        String r23 = r10.substring(r22, r3);
        if (r23.equals("RepresentationID") == false) goto L16;
        r12.add(1);
    L49:
        r11.add(_UrlKt.FRAGMENT_ENCODE_SET);
        r2 = r3 + 1;
        goto L4
    L16:
        int r42 = r23.indexOf("%0");
        if (r42 == (-1)) goto L26;
        String r7 = r23.substring(r42);
        if (r7.endsWith("d") == false) goto L21;
    L25:
        r23 = r23.substring(0, r42);
    L28:
        switch(r23.hashCode()) {
            case -1950496919: goto L39;
            case 2606829: goto L35;
            case 38199441: goto L31;
            default: goto L42;
        };
    L42:
        switch(r5) {
            case 0: goto L47;
            case 1: goto L46;
            case 2: goto L45;
            default: goto L43;
        };
    L45:
        r12.add(3);
    L48:
        r13.set(r12.size() - 1, r7);
        goto L49
    L46:
        r12.add(4);
        goto L48
    L47:
        r12.add(2);
        goto L48
    L43:
        f$$ExternalSyntheticBUOutline1.m("Invalid template: ".concat(r10));
        return;
    L31:
        if (r23.equals("Bandwidth") == false) goto L42;
        r5 = 2;
        goto L42
    L35:
        if (r23.equals("Time") == false) goto L42;
        r5 = 1;
        goto L42
    L39:
        if (r23.equals("Number") == false) goto L42;
        r5 = 0;
        goto L42
    L21:
        if (r7.endsWith("x") == true) goto L25;
        if (r7.endsWith("X") == true) goto L25;
        r7 = r7.concat("d");
        goto L25
    L26:
        r7 = "%01d";
        goto L28
    L12:
        r11.set(r12.size(), r11.get(r12.size()) + "$");
        r2 = r2 + 2;
        goto L4
    L9:
        r11.set(r12.size(), r11.get(r12.size()) + r10.substring(r2, r4));
        r2 = r4;
        goto L4
    L7:
        r11.set(r12.size(), r11.get(r12.size()) + r10.substring(r2));
        r2 = r10.length();
        goto L4
    }

    public String buildUri(String r6, long r7, int r9, long r10) {
        StringBuilder r0 = new StringBuilder();
        int r1 = 0;
    L3:
        int r2 = this.identifiers.size();
        List<String> r3 = this.urlPieces;
        if (r1 >= r2) goto L18;
        r0.append(r3.get(r1));
        if (this.identifiers.get(r1).intValue() != 1) goto L9;
        r0.append(r6);
    L17:
        r1 = r1 + 1;
        goto L3
    L9:
        if (this.identifiers.get(r1).intValue() != 2) goto L12;
        r0.append(String.format(Locale.US, this.identifierFormatTags.get(r1), new Object[]{Long.valueOf(r7)}));
        goto L17
    L12:
        if (this.identifiers.get(r1).intValue() != 3) goto L15;
        r0.append(String.format(Locale.US, this.identifierFormatTags.get(r1), new Object[]{Integer.valueOf(r9)}));
        goto L17
    L15:
        if (this.identifiers.get(r1).intValue() != 4) goto L17;
        r0.append(String.format(Locale.US, this.identifierFormatTags.get(r1), new Object[]{Long.valueOf(r10)}));
        goto L17
    L18:
        r0.append(r3.get(this.identifiers.size()));
        return r0.toString();
    }
}
