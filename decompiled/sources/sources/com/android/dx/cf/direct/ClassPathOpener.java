package com.android.dx.cf.direct;

import com.android.dex.util.FileUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.OneUIUtilities;

/* loaded from: classes4.dex */
public class ClassPathOpener {
    public static final FileNameFilter acceptAll = null;
    private final Consumer consumer;
    private FileNameFilter filter;
    private final String pathname;
    private final boolean sort;

    /* renamed from: com.android.dx.cf.direct.ClassPathOpener$1, reason: invalid class name */
    public static class AnonymousClass1 implements FileNameFilter {
        public AnonymousClass1() {
        }

        @Override // com.android.dx.cf.direct.ClassPathOpener.FileNameFilter
        public boolean accept(String r1) {
            return true;
        }
    }

    /* renamed from: com.android.dx.cf.direct.ClassPathOpener$2, reason: invalid class name */
    public class AnonymousClass2 implements Comparator<File> {
        final /* synthetic */ ClassPathOpener this$0;

        public AnonymousClass2(ClassPathOpener r1) {
            this.this$0 = r1;
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(File r1, File r2) {
            return ClassPathOpener.access$000(r1.getName(), r2.getName());
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(File r1, File r2) {
            return compare2(r1, r2);
        }
    }

    /* renamed from: com.android.dx.cf.direct.ClassPathOpener$3, reason: invalid class name */
    public class AnonymousClass3 implements Comparator<ZipEntry> {
        final /* synthetic */ ClassPathOpener this$0;

        public AnonymousClass3(ClassPathOpener r1) {
            this.this$0 = r1;
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(ZipEntry r1, ZipEntry r2) {
            return compare2(r1, r2);
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(ZipEntry r1, ZipEntry r2) {
            return ClassPathOpener.access$000(r1.getName(), r2.getName());
        }
    }

    public interface Consumer {
        void onException(Exception r1);

        void onProcessArchiveStart(File r1);

        boolean processFileBytes(String r1, long r2, byte[] r4);
    }

    public interface FileNameFilter {
        boolean accept(String r1);
    }

    static {
        acceptAll = new AnonymousClass1();
    }

    public ClassPathOpener(String r2, boolean r3, Consumer r4) {
        this(r2, r3, acceptAll, r4);
    }

    public ClassPathOpener(String r1, boolean r2, FileNameFilter r3, Consumer r4) {
        this.pathname = r1;
        this.sort = r2;
        this.consumer = r4;
        this.filter = r3;
    }

    public static /* synthetic */ int access$000(String r0, String r1) {
        return compareClassNames(r0, r1);
    }

    private static int compareClassNames(String r2, String r3) {
        String r22 = r2.replace(Typography.dollar, '0');
        String r32 = r3.replace(Typography.dollar, '0');
        return r22.replace("package-info", _UrlKt.FRAGMENT_ENCODE_SET).compareTo(r32.replace("package-info", _UrlKt.FRAGMENT_ENCODE_SET));
    }

    private boolean processArchive(File r14) {
        ZipFile r0 = new ZipFile(r14);
        ArrayList r1 = Collections.list(r0.entries());
        if (this.sort == false) goto L5;
        Collections.sort(r1, new AnonymousClass3(this));
    L5:
        this.consumer.onProcessArchiveStart(r14);
        ByteArrayOutputStream r142 = new ByteArrayOutputStream(OneUIUtilities.ONE_UI_4_0);
        byte[] r2 = new byte[20000];
        int r3 = r1.size();
        boolean r5 = false;
        int r6 = 0;
    L6:
        if (r6 >= r3) goto L17;
        Object r7 = r1.get(r6);
        r6 = r6 + 1;
        ZipEntry r72 = (ZipEntry) r7;
        boolean r8 = r72.isDirectory();
        String r9 = r72.getName();
        if (this.filter.accept(r9) == false) goto L6;
        if (r8 == true) goto L15;
        InputStream r82 = r0.getInputStream(r72);
        r142.reset();
    L11:
        int r10 = r82.read(r2);
        if (r10 == (-1)) goto L14;
        r142.write(r2, 0, r10);
        goto L11
    L14:
        r82.close();
        byte[] r83 = r142.toByteArray();
    L16:
        r5 = r5 | this.consumer.processFileBytes(r9, r72.getTime(), r83);
        goto L6
    L15:
        r83 = new byte[0];
        goto L16
    L17:
        r0.close();
        return r5;
    }

    private boolean processDirectory(File r5, boolean r6) {
        if (r6 == false) goto L4;
        r5 = new File(r5, ".");
    L4:
        File[] r52 = r5.listFiles();
        int r62 = r52.length;
        if (this.sort == false) goto L7;
        Arrays.sort(r52, new AnonymousClass2(this));
    L7:
        int r1 = 0;
        boolean r2 = false;
    L8:
        if (r1 >= r62) goto L10;
        r2 = r2 | processOne(r52[r1], false);
        r1 = r1 + 1;
        goto L8
    L10:
        return r2;
    }

    private boolean processOne(File r6, boolean r7) {
    L7:
        e = move-exception;
        this.consumer.onException(e);
        return false;
    L4:
        if (r6.isDirectory() == true) goto L6;
        String r72 = r6.getPath();     // Catch: Exception -> L7
        if (r72.endsWith(".zip") == true) goto L22;
        if (r72.endsWith(".jar") == true) goto L22;
        if (r72.endsWith(".apk") == true) goto L22;
        if (this.filter.accept(r72) == false) goto L20;
        byte[] r1 = FileUtils.readFile(r6);     // Catch: Exception -> L7
        return this.consumer.processFileBytes(r72, r6.lastModified(), r1);
    L20:
        return false;
    L22:
        return processArchive(r6);
    L6:
        return processDirectory(r6, r7);
    }

    public boolean process() {
        return processOne(new File(this.pathname), true);
    }
}
