package org.telegram.messenger;

import java.util.ArrayList;
import java.util.Locale;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public class WebFile extends TLObject {
    public ArrayList<TLRPC.DocumentAttribute> attributes;
    public TLRPC.InputGeoPoint geo_point;
    public int h;
    public TLRPC.InputWebFileLocation location;
    public String mime_type;
    public int msg_id;
    public boolean noproxy;
    public TLRPC.InputPeer peer;
    public int scale;
    public int size;
    public String url;
    public int w;
    public int zoom;

    public WebFile() {
    }

    public static WebFile createWithGeoPoint(double r9, double r11, long r13, int r15, int r16, int r17, int r18) {
        WebFile r4 = new WebFile();
        TLRPC.TL_inputWebFileGeoPointLocation r5 = new TLRPC.TL_inputWebFileGeoPointLocation();
        r4.location = r5;
        TLRPC.TL_inputGeoPoint r6 = new TLRPC.TL_inputGeoPoint();
        r4.geo_point = r6;
        r5.geo_point = r6;
        r5.access_hash = r13;
        r6.lat = r9;
        r6._long = r11;
        r4.w = r15;
        r5.w = r15;
        r4.h = r16;
        r5.h = r16;
        r4.zoom = r17;
        r5.zoom = r17;
        r4.scale = r18;
        r5.scale = r18;
        r4.mime_type = "image/png";
        r4.url = String.format(Locale.US, "maps_%.6f_%.6f_%d_%d_%d_%d.png", new Object[]{Double.valueOf(r9), Double.valueOf(r11), Integer.valueOf(r15), Integer.valueOf(r16), Integer.valueOf(r17), Integer.valueOf(r18)});
        r4.attributes = new ArrayList();
        return r4;
    }

    public static WebFile createWithGeoPoint(TLRPC.GeoPoint r10, int r11, int r12, int r13, int r14) {
        return createWithGeoPoint(r10.lat, r10._long, r10.access_hash, r11, r12, r13, r14);
    }

    public static WebFile createWithWebDocument(TLRPC.WebDocument r5) {
        if ((r5 instanceof TLRPC.TL_webDocument) == false) goto L7;
        WebFile r0 = new WebFile();
        TLRPC.TL_webDocument r1 = (TLRPC.TL_webDocument) r5;
        TLRPC.TL_inputWebFileLocation r2 = new TLRPC.TL_inputWebFileLocation();
        r0.location = r2;
        String r52 = r5.url;
        r0.url = r52;
        r2.url = r52;
        r2.access_hash = r1.access_hash;
        r0.size = r1.size;
        r0.mime_type = r1.mime_type;
        r0.attributes = r1.attributes;
        return r0;
    L7:
        if ((r5 instanceof TLRPC.TL_webDocumentNoProxy) == false) goto L10;
        WebFile r02 = new WebFile();
        TLRPC.TL_webDocumentNoProxy r12 = (TLRPC.TL_webDocumentNoProxy) r5;
        TLRPC.TL_inputWebFileLocation r22 = new TLRPC.TL_inputWebFileLocation();
        r02.location = r22;
        String r53 = r5.url;
        r02.url = r53;
        r22.url = r53;
        r22.access_hash = r12.access_hash;
        r02.size = r12.size;
        r02.mime_type = r12.mime_type;
        r02.attributes = r12.attributes;
        r02.noproxy = true;
        return r02;
    L10:
        return null;
    }
}
