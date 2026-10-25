package kotlin.io.path;

import j$.nio.file.FileVisitResult;
import j$.nio.file.Path;
import j$.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.functions.Function2;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "2.1")
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bw\u0018\u00002\u00020\u0001JG\u0010\f\u001a\u00020\u000b26\u0010\n\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0002H&¢\u0006\u0004\b\f\u0010\rJG\u0010\u000f\u001a\u00020\u000b26\u0010\n\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0002H&¢\u0006\u0004\b\u000f\u0010\rJG\u0010\u0012\u001a\u00020\u000b26\u0010\n\u001a2\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\t0\u0002H&¢\u0006\u0004\b\u0012\u0010\rJI\u0010\u0013\u001a\u00020\u000b28\u0010\n\u001a4\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0015\u0012\u0013\u0018\u00010\u0010¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\t0\u0002H&¢\u0006\u0004\b\u0013\u0010\r\u0082\u0001\u0001\u0014¨\u0006\u0015"}, d2 = {"Lkotlin/io/path/FileVisitorBuilder;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Function2;", "j$/nio/file/Path", "Lkotlin/ParameterName;", "name", "directory", "j$/nio/file/attribute/BasicFileAttributes", "attributes", "j$/nio/file/FileVisitResult", "function", _UrlKt.FRAGMENT_ENCODE_SET, "onPreVisitDirectory", "(Lkotlin/jvm/functions/Function2;)V", "file", "onVisitFile", "Ljava/io/IOException;", "exception", "onVisitFileFailed", "onPostVisitDirectory", "Lkotlin/io/path/FileVisitorBuilderImpl;", "kotlin-stdlib-jdk7"}, k = 1, mv = {2, 3, 0}, xi = 48)
@WasExperimental(markerClass = {ExperimentalPathApi.class})
/* loaded from: classes5.dex */
public interface FileVisitorBuilder {
    void onPostVisitDirectory(Function2<? super Path, ? super IOException, ? extends FileVisitResult> r1);

    void onPreVisitDirectory(Function2<? super Path, ? super BasicFileAttributes, ? extends FileVisitResult> r1);

    void onVisitFile(Function2<? super Path, ? super BasicFileAttributes, ? extends FileVisitResult> r1);

    void onVisitFileFailed(Function2<? super Path, ? super IOException, ? extends FileVisitResult> r1);
}
