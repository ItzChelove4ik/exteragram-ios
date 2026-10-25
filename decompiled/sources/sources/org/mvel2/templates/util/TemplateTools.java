package org.mvel2.templates.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.mvel2.templates.TemplateError;
import org.mvel2.templates.res.Node;
import org.mvel2.templates.res.TerminalNode;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class TemplateTools {
    public TemplateTools() {
    }

    public static int captureToEOS(char[] r3, int r4) {
        int r0 = r3.length;
    L3:
        if (r4 == r0) goto L17;
        char r1 = r3[r4];
        if (r1 != '(') goto L7;
    L15:
        r4 = ParseTools.balancedCapture(r3, r4, r1);
    L16:
        r4 = r4 + 1;
        goto L3
    L7:
        if (r1 == ';') goto L17;
        if (r1 == '[') goto L15;
        if (r1 == '{') goto L15;
        if (r1 != '}') goto L16;
    L17:
        return r4;
    }

    public static Node getLastNode(Node r1) {
    L3:
        if ((r1.getNext() instanceof TerminalNode) == true) goto L4;
        r1 = r1.getNext();
        goto L3
    L4:
        return r1;
    }

    public static String readInFile(File r5) {
        FileChannel r0 = new FileInputStream(r5).getChannel();     // Catch: IOException -> L8 FileNotFoundException -> L14
        ByteBuffer r1 = ByteBuffer.allocateDirect(10);     // Catch: IOException -> L8 FileNotFoundException -> L14
        StringBuilder r2 = new StringBuilder();     // Catch: IOException -> L8 FileNotFoundException -> L14
    L3:
        ByteBuffer r3 = (ByteBuffer) r1.rewind();     // Catch: IOException -> L8 FileNotFoundException -> L14
        int r32 = r0.read(r1);     // Catch: IOException -> L8 FileNotFoundException -> L14
        if (r32 == (-1)) goto L10;
        ByteBuffer r4 = (ByteBuffer) r1.rewind();     // Catch: IOException -> L8 FileNotFoundException -> L14
    L6:
        if (r32 == 0) goto L3;
        r2.append((char) r1.get());     // Catch: IOException -> L8 FileNotFoundException -> L14
        r32 = r32 - 1;
        goto L6
    L10:
        r0.close();     // Catch: IOException -> L8 FileNotFoundException -> L14
        return r2.toString();
    L14:
        TemplateTools$$ExternalSyntheticBUOutline0.m("cannot include template '", r5.getName(), "': file not found.");
        return null;
    L8:
        e = move-exception;
        throw new TemplateError("unknown I/O exception while including '" + r5.getName() + "' (stacktrace nested)", e);
    }

    public static String readInFile(String r1) {
        return readInFile(new File(r1));
    }

    public static String readStream(InputStream r5) {
        byte[] r0 = new byte[10];     // Catch: NullPointerException -> L9 IOException -> L13
        StringBuilder r1 = new StringBuilder();     // Catch: NullPointerException -> L9 IOException -> L13
    L4:
        int r2 = r5.read(r0);     // Catch: NullPointerException -> L9 IOException -> L13
        if (r2 == (-1)) goto L11;
        int r3 = 0;
    L7:
        if (r3 >= r2) goto L4;
        r1.append((char) r0[r3]);     // Catch: NullPointerException -> L9 IOException -> L13
        r3 = r3 + 1;     // Catch: NullPointerException -> L9 IOException -> L13
        goto L7
    L11:
        return r1.toString();
    L13:
        e = move-exception;
        throw new TemplateError("unknown I/O exception while including (stacktrace nested)", e);
    L9:
        e = move-exception;
        if (r5 == null) goto L18;
        throw e;
    L18:
        throw new TemplateError("null input stream", e);
    }
}
