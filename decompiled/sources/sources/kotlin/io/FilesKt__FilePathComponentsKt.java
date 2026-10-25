package kotlin.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0013\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0082\u0080\u0004¢\u0006\u0002\b\u0003\u001a\u000e\u0010\u000e\u001a\u00020\u000f*\u00020\u0005H\u0080\u0080\u0004\u001a\u001e\u0010\u0010\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0080\u0080\u0004\"\u0019\u0010\u0004\u001a\u00020\u0002*\u00020\u00058@X\u0080\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\"\u0019\u0010\b\u001a\u00020\u0005*\u00020\u00058@X\u0080\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\n\"\u0019\u0010\u000b\u001a\u00020\f*\u00020\u00058FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u000b\u0010\r¨\u0006\u0013"}, d2 = {"getRootLength", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "getRootLength$FilesKt__FilePathComponentsKt", "rootName", "Ljava/io/File;", "getRootName", "(Ljava/io/File;)Ljava/lang/String;", "root", "getRoot", "(Ljava/io/File;)Ljava/io/File;", "isRooted", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/io/File;)Z", "toComponents", "Lkotlin/io/FilePathComponents;", "subPath", "beginIndex", "endIndex", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/io/FilesKt")
@SourceDebugExtension({"SMAP\nFilePathComponents.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FilePathComponents.kt\nkotlin/io/FilesKt__FilePathComponentsKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,149:1\n1586#2:150\n1661#2,3:151\n*S KotlinDebug\n*F\n+ 1 FilePathComponents.kt\nkotlin/io/FilesKt__FilePathComponentsKt\n*L\n134#1:150\n134#1:151,3\n*E\n"})
/* loaded from: classes.dex */
class FilesKt__FilePathComponentsKt {
    public FilesKt__FilePathComponentsKt() {
    }

    public static final File getRoot(File r1) {
        return new File(getRootName(r1));
    }

    private static final int getRootLength$FilesKt__FilePathComponentsKt(String r7) {
        char r1 = File.separatorChar;
        int r72 = StringsKt.indexOf$default(r7, r1, 0, false, 4, null);
        if (r72 != 0) goto L18;
        if (r7.length() > 1) goto L7;
    L16:
        return 1;
    L7:
        if (r7.charAt(1) != r1) goto L16;
        int r73 = StringsKt.indexOf$default(r7, r1, 2, false, 4, null);
        if (r73 < 0) goto L16;
        int r74 = StringsKt.indexOf$default(r7, r1, r73 + 1, false, 4, null);
        if (r74 < 0) goto L15;
        return r74 + 1;
    L15:
        return r7.length();
    L18:
        if (r72 <= 0) goto L24;
        if (r7.charAt(r72 - 1) != ':') goto L24;
        return r72 + 1;
    L24:
        if (r72 == (-1)) goto L26;
    L29:
        return 0;
    L26:
        if (StringsKt.endsWith$default(r7, ':', false, 2, null) == false) goto L29;
        return r7.length();
    }

    public static final String getRootName(File r2) {
        return r2.getPath().substring(0, getRootLength$FilesKt__FilePathComponentsKt(r2.getPath()));
    }

    public static final boolean isRooted(File r0) {
        if (getRootLength$FilesKt__FilePathComponentsKt(r0.getPath()) <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public static final File subPath(File r0, int r1, int r2) {
        return toComponents(r0).subPath(r1, r2);
    }

    public static final FilePathComponents toComponents(File r9) {
        String r92 = r9.getPath();
        int r0 = getRootLength$FilesKt__FilePathComponentsKt(r92);
        String r2 = r92.substring(0, r0);
        String r3 = r92.substring(r0);
        if (r3.length() != 0) goto L5;
        List r93 = CollectionsKt.emptyList();
    L11:
        return new FilePathComponents(new File(r2), r93);
    L5:
        List r94 = StringsKt.split$default(r3, new char[]{File.separatorChar}, false, 0, 6, null);
        ArrayList r02 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r94, 10));
        Iterator r95 = r94.iterator();
    L7:
        if (r95.hasNext() == false) goto L9;
        r02.add(new File((String) r95.next()));
        goto L7
    L9:
        r93 = r02;
        goto L11
    }
}
