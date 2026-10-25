package org.telegram.ui.web;

import android.util.Base64InputStream;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public class MHTML {
    public final String boundary;
    public final ArrayList<Entry> entries;
    public final HashMap<String, Entry> entriesByLocation;
    public final File file;
    private final long[] filePos;
    public final HashMap<String, HeaderValue> headers;

    public static class BoundedInputStream extends FileInputStream implements InputStreamRetargetInterface {
        private long bytesRead;
        private final long endOffset;

        public BoundedInputStream(File r3, long r4, long r6) {
            super(r3);
            this.bytesRead = 0;
            this.endOffset = r6;
            if (r4 > 0) goto L5;
            return;
        L5:
            if (skip(r4) == r4) goto L10;
            GlShader$$ExternalSyntheticBUOutline1.m("BoundedInputStream failed to skip");
            throw null;
        }

        @Override // java.io.FileInputStream, java.io.InputStream
        public int read() {
            if (getChannel().position() < this.endOffset) goto L7;
            return -1;
        L7:
            return super.read();
        }

        @Override // java.io.FileInputStream, java.io.InputStream
        public int read(byte[] r5, int r6, int r7) {
            long r0 = getChannel().position();
            long r2 = this.endOffset;
            if (r0 < r2) goto L6;
            return -1;
        L6:
            long r22 = r2 - getChannel().position();
            if (r7 <= r22) goto L10;
            r7 = (int) r22;
        L10:
            return super.read(r5, r6, r7);
        }

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public /* synthetic */ long transferTo(OutputStream r1) {
            return DesugarInputStream.transferTo(this, r1);
        }
    }

    public static class Entry {
        public long end;
        public File file;
        public final HashMap<String, HeaderValue> headers;
        public long start;

        private Entry() {
            this.headers = new HashMap();
        }

        public /* synthetic */ Entry(MHTMLIA r1) {
            this();
        }

        public InputStream getInputStream() {
            BufferedInputStream r0 = new BufferedInputStream(getRawInputStream());
            if ("base64".equals(getTransferEncoding()) == false) goto L7;
            return new Base64InputStream(r0, 0);
        L7:
            if ("quoted-printable".equalsIgnoreCase(getTransferEncoding()) == true) goto L9;
            return r0;
        L9:
            return new QuotedPrintableInputStream(r0);
        }

        public String getLocation() {
            return HeaderValue.getValue(this.headers.get("content-location"));
        }

        public InputStream getRawInputStream() {
            return new BoundedInputStream(this.file, this.start, this.end);
        }

        public String getTransferEncoding() {
            return HeaderValue.getValue(this.headers.get("content-transfer-encoding"));
        }

        public String getType() {
            return HeaderValue.getValue(this.headers.get("content-type"));
        }
    }

    public static class HeaderValue {
        public final HashMap<String, String> props;
        public String value;

        private HeaderValue() {
            this.props = new HashMap();
        }

        public /* synthetic */ HeaderValue(MHTMLIA r1) {
            this();
        }

        public static String getProp(HeaderValue r0, String r1) {
            if (r0 != null) goto L6;
            return null;
        L6:
            return r0.props.get(r1);
        }

        public static String getValue(HeaderValue r0) {
            if (r0 != null) goto L6;
            return null;
        L6:
            return r0.value;
        }
    }

    public static class QuotedPrintableInputStream extends FilterInputStream implements InputStreamRetargetInterface {
        public QuotedPrintableInputStream(InputStream r1) {
            super(r1);
        }

        private int hexDigitToInt(int r2) {
            if (r2 < 48) goto L9;
            if (r2 > 57) goto L9;
            return r2 - 48;
        L9:
            if (r2 < 65) goto L15;
            if (r2 > 70) goto L15;
            return r2 - 55;
        L15:
            if (r2 >= 97) goto L17;
            return 0;
        L17:
            if (r2 <= 102) goto L19;
            return 0;
        L19:
            return r2 - 87;
        }

        private int hexToByte(int r1, int r2) {
            int r12 = hexDigitToInt(r1) << 4;
            return hexDigitToInt(r2) | r12;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() {
            int r0 = ((FilterInputStream) this).in.read();
            if (r0 != 61) goto L20;
            int r02 = ((FilterInputStream) this).in.read();
            int r1 = ((FilterInputStream) this).in.read();
            if (r02 == (-1)) goto L19;
            if (r1 == (-1)) goto L19;
            if (r02 != 13) goto L13;
            if (r1 != 10) goto L13;
            return read();
        L13:
            if (r02 == 10) goto L18;
            if (r1 == 10) goto L18;
            return hexToByte(r02, r1);
        L18:
            return r1;
        L19:
            return -1;
        L20:
            return r0;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] r5, int r6, int r7) {
            int r0 = 0;
            int r1 = 0;
        L3:
            if (r0 >= r7) goto L9;
            int r2 = read();
            if (r2 == (-1)) goto L6;
            r5[r6 + r0] = (byte) r2;
            r1 = r1 + 1;
            r0 = r0 + 1;
            goto L3
        L6:
            if (r1 != 0) goto L9;
            return -1;
        L9:
            return r1;
        }

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public /* synthetic */ long transferTo(OutputStream r1) {
            return DesugarInputStream.transferTo(this, r1);
        }
    }

    public MHTML(File r4) {
        HashMap<String, HeaderValue> r0 = new HashMap();
        this.headers = r0;
        this.entries = new ArrayList();
        this.entriesByLocation = new HashMap();
        this.filePos = new long[1];
        this.file = r4;
        FileInputStream r1 = new FileInputStream(r4);
        BufferedReader r42 = new BufferedReader(new InputStreamReader(r1));
        r0.putAll(parseHeaders(r42));
        String r02 = HeaderValue.getProp(r0.get("content-type"), "boundary");
        this.boundary = r02;
        if (r02 == null) goto L5;
        parseEntries(r42, r1);
    L5:
        r42.close();
    }

    private static void appendHeader(String r8, String r9, HashMap<String, HeaderValue> r10) {
        HeaderValue r0 = new HeaderValue(null);
        String[] r92 = r9.split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        int r2 = 0;
    L4:
        if (r2 >= r92.length) goto L22;
        String r3 = r92[r2].trim();
        if (r3.isEmpty() == true) goto L21;
        int r4 = r3.indexOf(61);
        if (r2 == 0) goto L20;
        if (r4 < 0) goto L20;
        String r5 = r3.substring(0, r4).trim();
        String r32 = r3.substring(r4 + 1).trim();
        if (r32.length() >= 2) goto L15;
    L19:
        r0.props.put(r5, r32);
        goto L21
    L15:
        if (r32.charAt(0) != '\"') goto L19;
        if (r32.charAt(r32.length() - 1) != '\"') goto L19;
        r32 = r32.substring(1, r32.length() - 1);
    L20:
        r0.value = r3;
    L21:
        r2 = r2 + 1;
        goto L4
    L22:
        r10.put(r8.trim().toLowerCase(), r0);
    }

    private void parseEntries(BufferedReader r11, FileInputStream r12) {
        int r122 = this.boundary.length() + 2;
        Entry r2 = null;
    L3:
        String r3 = r11.readLine();
        if (r3 == null) goto L12;
        long[] r4 = this.filePos;
        r4[0] = r4[0] + (r3.getBytes().length + 2);
        if (r3.length() != r122) goto L3;
        if (r3.substring(2).equals(this.boundary) == false) goto L3;
        if (r2 == null) goto L11;
        r2.end = (this.filePos[0] - r122) - 2;
        this.entries.add(r2);
        this.entriesByLocation.put(r2.getLocation(), r2);
    L11:
        r2 = new Entry(null);
        r2.file = this.file;
        r2.headers.putAll(parseHeaders(r11));
        r2.start = this.filePos[0];
        goto L3
    L12:
        if (r2 != null) goto L14;
        return;
    L14:
        if (r2.start != 0) goto L16;
        return;
    L16:
        if (r2.end == 0) goto L28;
        this.entries.add(r2);
        this.entriesByLocation.put(r2.getLocation(), r2);
        return;
    }

    private HashMap<String, HeaderValue> parseHeaders(BufferedReader r12) {
        HashMap<String, HeaderValue> r0 = new HashMap();
    L3:
        String r2 = null;
        StringBuilder r3 = null;
    L4:
        String r4 = r12.readLine();
        if (r4 == null) goto L22;
        long[] r5 = this.filePos;
        r5[0] = r5[0] + (r4.getBytes().length + 2);
        String r42 = r4.trim();
        if (r42.isEmpty() == true) goto L22;
        if (r2 == null) goto L15;
        if (r3 == null) goto L15;
        r3.append(r42);
        if (r42.endsWith(";") == true) goto L4;
        appendHeader(r2, r3.toString(), r0);
    L15:
        int r7 = r42.indexOf(58);
        if (r7 < 0) goto L4;
        String r6 = r42.substring(0, r7).trim();
        String r43 = r42.substring(r7 + 1).trim();
        if (r43.endsWith(";") == true) goto L20;
        appendHeader(r6, r43, r0);
        goto L4
    L20:
        StringBuilder r22 = new StringBuilder();
        r22.append(r43);
        r3 = r22;
        r2 = r6;
    L22:
        if (r2 == null) goto L25;
        if (r3 == null) goto L25;
        appendHeader(r2, r3.toString(), r0);
    L25:
        return r0;
    }
}
