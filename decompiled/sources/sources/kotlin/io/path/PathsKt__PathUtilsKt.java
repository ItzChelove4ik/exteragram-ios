package kotlin.io.path;

import j$.nio.file.CopyOption;
import j$.nio.file.FileStore;
import j$.nio.file.FileVisitOption;
import j$.nio.file.FileVisitor;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.Path;
import j$.nio.file.Paths;
import j$.nio.file.StandardCopyOption;
import j$.nio.file.attribute.BasicFileAttributes;
import j$.nio.file.attribute.FileAttribute;
import j$.nio.file.attribute.FileAttributeView;
import j$.nio.file.attribute.FileTime;
import j$.nio.file.attribute.PosixFilePermission;
import j$.nio.file.attribute.UserPrincipal;
import java.net.URI;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.WasExperimental;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.internal.InlineOnly;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000¨\u0001\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\b\u0010\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\b\u0007\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0000\n\u0002\b\u0005\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\u001a\u0014\u0010\u0001\u001a\u00020\u0000*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0001\u0010\u0002\u001a\u0014\u0010\u0004\u001a\u00020\u0003*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001b\u0010\u0007\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a\u001b\u0010\t\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\t\u0010\b\u001a\u001d\u0010\n\u001a\u0004\u0018\u00010\u0000*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\n\u0010\b\u001a&\u0010\u000e\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\r\u001a\u00020\fH\u0087\b¢\u0006\u0004\b\u000e\u0010\u000f\u001a0\u0010\u000e\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u0010\"\u00020\u0011H\u0087\b¢\u0006\u0004\b\u000e\u0010\u0013\u001a(\u0010\u0015\u001a\u00020\f*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\b\u0015\u0010\u0016\u001a(\u0010\u0017\u001a\u00020\f*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\b\u0017\u0010\u0016\u001a(\u0010\u0018\u001a\u00020\f*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\b\u0018\u0010\u0016\u001a(\u0010\u0019\u001a\u00020\f*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\b\u0019\u0010\u0016\u001a\u0014\u0010\u001a\u001a\u00020\f*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u0014\u0010\u001c\u001a\u00020\f*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u001c\u0010\u001b\u001a\u0014\u0010\u001d\u001a\u00020\f*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u001d\u0010\u001b\u001a\u0014\u0010\u001e\u001a\u00020\f*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u001e\u0010\u001b\u001a\u0014\u0010\u001f\u001a\u00020\f*\u00020\u0000H\u0087\b¢\u0006\u0004\b\u001f\u0010\u001b\u001a\u001c\u0010!\u001a\u00020\f*\u00020\u00002\u0006\u0010 \u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b!\u0010\"\u001a#\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00000$*\u00020\u00002\b\b\u0002\u0010#\u001a\u00020\u0003H\u0007¢\u0006\u0004\b%\u0010&\u001aA\u0010+\u001a\u00028\u0000\"\u0004\b\u0000\u0010'*\u00020\u00002\b\b\u0002\u0010#\u001a\u00020\u00032\u0018\u0010*\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00000)\u0012\u0004\u0012\u00028\u00000(H\u0087\bø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a5\u0010/\u001a\u00020-*\u00020\u00002\b\b\u0002\u0010#\u001a\u00020\u00032\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020-0(H\u0087\bø\u0001\u0000¢\u0006\u0004\b/\u00100\u001a\u0014\u00102\u001a\u000201*\u00020\u0000H\u0087\b¢\u0006\u0004\b2\u00103\u001a\u0014\u00104\u001a\u00020-*\u00020\u0000H\u0087\b¢\u0006\u0004\b4\u00105\u001a\u0014\u00106\u001a\u00020\f*\u00020\u0000H\u0087\b¢\u0006\u0004\b6\u0010\u001b\u001a0\u00109\u001a\u00020\u0000*\u00020\u00002\u001a\u00108\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u0003070\u0010\"\u0006\u0012\u0002\b\u000307H\u0087\b¢\u0006\u0004\b9\u0010:\u001a0\u0010;\u001a\u00020\u0000*\u00020\u00002\u001a\u00108\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u0003070\u0010\"\u0006\u0012\u0002\b\u000307H\u0087\b¢\u0006\u0004\b;\u0010:\u001a/\u0010<\u001a\u00020\u0000*\u00020\u00002\u001a\u00108\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u0003070\u0010\"\u0006\u0012\u0002\b\u000307H\u0007¢\u0006\u0004\b<\u0010:\u001a0\u0010=\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u0010\"\u00020\u0011H\u0087\b¢\u0006\u0004\b=\u0010\u0013\u001a&\u0010=\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\r\u001a\u00020\fH\u0087\b¢\u0006\u0004\b=\u0010\u000f\u001a\u0014\u0010?\u001a\u00020>*\u00020\u0000H\u0087\b¢\u0006\u0004\b?\u0010@\u001a2\u0010C\u001a\u0004\u0018\u00010B*\u00020\u00002\u0006\u0010A\u001a\u00020\u00032\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\bC\u0010D\u001a:\u0010F\u001a\u00020\u0000*\u00020\u00002\u0006\u0010A\u001a\u00020\u00032\b\u0010E\u001a\u0004\u0018\u00010B2\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\bF\u0010G\u001a6\u0010J\u001a\u0004\u0018\u00018\u0000\"\n\b\u0000\u0010I\u0018\u0001*\u00020H*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\bJ\u0010K\u001a4\u0010L\u001a\u00028\u0000\"\n\b\u0000\u0010I\u0018\u0001*\u00020H*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\bL\u0010K\u001a#\u0010Q\u001a\u00020P2\u0006\u0010M\u001a\u00020\u00002\n\u0010O\u001a\u0006\u0012\u0002\b\u00030NH\u0001¢\u0006\u0004\bQ\u0010R\u001a4\u0010U\u001a\u00028\u0000\"\n\b\u0000\u0010T\u0018\u0001*\u00020S*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\bU\u0010V\u001a>\u0010U\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010B0W*\u00020\u00002\u0006\u00108\u001a\u00020\u00032\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\bU\u0010X\u001a(\u0010Z\u001a\u00020Y*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\bZ\u0010[\u001a\u001c\u0010\\\u001a\u00020\u0000*\u00020\u00002\u0006\u0010E\u001a\u00020YH\u0087\b¢\u0006\u0004\b\\\u0010]\u001a*\u0010_\u001a\u0004\u0018\u00010^*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\b_\u0010`\u001a\u001c\u0010a\u001a\u00020\u0000*\u00020\u00002\u0006\u0010E\u001a\u00020^H\u0087\b¢\u0006\u0004\ba\u0010b\u001a.\u0010e\u001a\b\u0012\u0004\u0012\u00020d0c*\u00020\u00002\u0012\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00140\u0010\"\u00020\u0014H\u0087\b¢\u0006\u0004\be\u0010f\u001a\"\u0010g\u001a\u00020\u0000*\u00020\u00002\f\u0010E\u001a\b\u0012\u0004\u0012\u00020d0cH\u0087\b¢\u0006\u0004\bg\u0010h\u001a\u001c\u0010i\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\bi\u0010\b\u001a8\u0010j\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u00002\u001a\u00108\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u0003070\u0010\"\u0006\u0012\u0002\b\u000307H\u0087\b¢\u0006\u0004\bj\u0010k\u001a\u0014\u0010l\u001a\u00020\u0000*\u00020\u0000H\u0087\b¢\u0006\u0004\bl\u0010\u0002\u001a0\u0010m\u001a\u00020\u0000*\u00020\u00002\u001a\u00108\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u0003070\u0010\"\u0006\u0012\u0002\b\u000307H\u0087\b¢\u0006\u0004\bm\u0010:\u001aD\u0010p\u001a\u00020\u00002\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010o\u001a\u0004\u0018\u00010\u00032\u001a\u00108\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u0003070\u0010\"\u0006\u0012\u0002\b\u000307H\u0087\b¢\u0006\u0004\bp\u0010q\u001aM\u0010p\u001a\u00020\u00002\b\u0010r\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010o\u001a\u0004\u0018\u00010\u00032\u001a\u00108\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u0003070\u0010\"\u0006\u0012\u0002\b\u000307H\u0007¢\u0006\u0004\bp\u0010s\u001a8\u0010t\u001a\u00020\u00002\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u00032\u001a\u00108\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u0003070\u0010\"\u0006\u0012\u0002\b\u000307H\u0087\b¢\u0006\u0004\bt\u0010u\u001aA\u0010t\u001a\u00020\u00002\b\u0010r\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u00032\u001a\u00108\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u0003070\u0010\"\u0006\u0012\u0002\b\u000307H\u0007¢\u0006\u0004\bt\u0010v\u001a\u001c\u0010w\u001a\u00020\u0000*\u00020\u00002\u0006\u0010 \u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bw\u0010\b\u001a\u001c\u0010w\u001a\u00020\u0000*\u00020\u00002\u0006\u0010 \u001a\u00020\u0003H\u0087\n¢\u0006\u0004\bw\u0010x\u001a\u0018\u0010y\u001a\u00020\u00002\u0006\u0010M\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\by\u0010z\u001a,\u0010y\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00032\u0012\u0010{\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0010\"\u00020\u0003H\u0087\b¢\u0006\u0004\by\u0010|\u001a\u0014\u0010~\u001a\u00020\u0000*\u00020}H\u0087\b¢\u0006\u0004\b~\u0010\u007f\u001a2\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020\u00000)*\u00020\u00002\u0014\u0010\u0012\u001a\u000b\u0012\u0007\b\u0001\u0012\u00030\u0080\u00010\u0010\"\u00030\u0080\u0001H\u0007¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001\u001a=\u0010\u0088\u0001\u001a\u00020-*\u00020\u00002\u000e\u0010\u0084\u0001\u001a\t\u0012\u0004\u0012\u00020\u00000\u0083\u00012\n\b\u0002\u0010\u0086\u0001\u001a\u00030\u0085\u00012\t\b\u0002\u0010\u0087\u0001\u001a\u00020\fH\u0007¢\u0006\u0006\b\u0088\u0001\u0010\u0089\u0001\u001aV\u0010\u0088\u0001\u001a\u00020-*\u00020\u00002\n\b\u0002\u0010\u0086\u0001\u001a\u00030\u0085\u00012\t\b\u0002\u0010\u0087\u0001\u001a\u00020\f2\u001a\u0010\u008c\u0001\u001a\u0015\u0012\u0005\u0012\u00030\u008a\u0001\u0012\u0004\u0012\u00020-0(¢\u0006\u0003\b\u008b\u0001H\u0007\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001¢\u0006\u0006\b\u0088\u0001\u0010\u008d\u0001\u001aB\u0010\u008e\u0001\u001a\t\u0012\u0004\u0012\u00020\u00000\u0083\u00012\u001a\u0010\u008c\u0001\u001a\u0015\u0012\u0005\u0012\u00030\u008a\u0001\u0012\u0004\u0012\u00020-0(¢\u0006\u0003\b\u008b\u0001H\u0007\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001\"!\u0010\u0092\u0001\u001a\u00020\u0003*\u00020\u00008FX\u0087\u0004¢\u0006\u000e\u0012\u0005\b\u0091\u0001\u00105\u001a\u0005\b\u0090\u0001\u0010\u0005\"!\u0010\u0095\u0001\u001a\u00020\u0003*\u00020\u00008FX\u0087\u0004¢\u0006\u000e\u0012\u0005\b\u0094\u0001\u00105\u001a\u0005\b\u0093\u0001\u0010\u0005\"!\u0010\u0098\u0001\u001a\u00020\u0003*\u00020\u00008FX\u0087\u0004¢\u0006\u000e\u0012\u0005\b\u0097\u0001\u00105\u001a\u0005\b\u0096\u0001\u0010\u0005\"\"\u0010\u009b\u0001\u001a\u00020\u0003*\u00020\u00008Æ\u0002X\u0087\u0004¢\u0006\u000e\u0012\u0005\b\u009a\u0001\u00105\u001a\u0005\b\u0099\u0001\u0010\u0005\"!\u0010\u009e\u0001\u001a\u00020\u0003*\u00020\u00008FX\u0087\u0004¢\u0006\u000e\u0012\u0005\b\u009d\u0001\u00105\u001a\u0005\b\u009c\u0001\u0010\u0005\"\"\u0010¡\u0001\u001a\u00020\u0003*\u00020\u00008Æ\u0002X\u0087\u0004¢\u0006\u000e\u0012\u0005\b \u0001\u00105\u001a\u0005\b\u009f\u0001\u0010\u0005\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006¢\u0001"}, d2 = {"j$/nio/file/Path", "absolute", "(Lj$/nio/file/Path;)Lj$/nio/file/Path;", _UrlKt.FRAGMENT_ENCODE_SET, "absolutePathString", "(Lj$/nio/file/Path;)Ljava/lang/String;", "base", "relativeTo", "(Lj$/nio/file/Path;Lj$/nio/file/Path;)Lj$/nio/file/Path;", "relativeToOrSelf", "relativeToOrNull", "target", _UrlKt.FRAGMENT_ENCODE_SET, "overwrite", "copyTo", "(Lj$/nio/file/Path;Lj$/nio/file/Path;Z)Lj$/nio/file/Path;", _UrlKt.FRAGMENT_ENCODE_SET, "j$/nio/file/CopyOption", "options", "(Lj$/nio/file/Path;Lj$/nio/file/Path;[Lj$/nio/file/CopyOption;)Lj$/nio/file/Path;", "j$/nio/file/LinkOption", "exists", "(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Z", "notExists", "isRegularFile", "isDirectory", "isSymbolicLink", "(Lj$/nio/file/Path;)Z", "isExecutable", "isHidden", "isReadable", "isWritable", "other", "isSameFileAs", "(Lj$/nio/file/Path;Lj$/nio/file/Path;)Z", "glob", _UrlKt.FRAGMENT_ENCODE_SET, "listDirectoryEntries", "(Lj$/nio/file/Path;Ljava/lang/String;)Ljava/util/List;", "T", "Lkotlin/Function1;", "Lkotlin/sequences/Sequence;", "block", "useDirectoryEntries", "(Lj$/nio/file/Path;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "action", "forEachDirectoryEntry", "(Lj$/nio/file/Path;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", _UrlKt.FRAGMENT_ENCODE_SET, "fileSize", "(Lj$/nio/file/Path;)J", "deleteExisting", "(Lj$/nio/file/Path;)V", "deleteIfExists", "j$/nio/file/attribute/FileAttribute", "attributes", "createDirectory", "(Lj$/nio/file/Path;[Lj$/nio/file/attribute/FileAttribute;)Lj$/nio/file/Path;", "createDirectories", "createParentDirectories", "moveTo", "j$/nio/file/FileStore", "fileStore", "(Lj$/nio/file/Path;)Lj$/nio/file/FileStore;", "attribute", _UrlKt.FRAGMENT_ENCODE_SET, "getAttribute", "(Lj$/nio/file/Path;Ljava/lang/String;[Lj$/nio/file/LinkOption;)Ljava/lang/Object;", "value", "setAttribute", "(Lj$/nio/file/Path;Ljava/lang/String;Ljava/lang/Object;[Lj$/nio/file/LinkOption;)Lj$/nio/file/Path;", "j$/nio/file/attribute/FileAttributeView", "V", "fileAttributesViewOrNull", "(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Lj$/nio/file/attribute/FileAttributeView;", "fileAttributesView", "path", "Ljava/lang/Class;", "attributeViewClass", _UrlKt.FRAGMENT_ENCODE_SET, "fileAttributeViewNotAvailable", "(Lj$/nio/file/Path;Ljava/lang/Class;)Ljava/lang/Void;", "j$/nio/file/attribute/BasicFileAttributes", "A", "readAttributes", "(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Lj$/nio/file/attribute/BasicFileAttributes;", _UrlKt.FRAGMENT_ENCODE_SET, "(Lj$/nio/file/Path;Ljava/lang/String;[Lj$/nio/file/LinkOption;)Ljava/util/Map;", "j$/nio/file/attribute/FileTime", "getLastModifiedTime", "(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Lj$/nio/file/attribute/FileTime;", "setLastModifiedTime", "(Lj$/nio/file/Path;Lj$/nio/file/attribute/FileTime;)Lj$/nio/file/Path;", "j$/nio/file/attribute/UserPrincipal", "getOwner", "(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Lj$/nio/file/attribute/UserPrincipal;", "setOwner", "(Lj$/nio/file/Path;Lj$/nio/file/attribute/UserPrincipal;)Lj$/nio/file/Path;", _UrlKt.FRAGMENT_ENCODE_SET, "j$/nio/file/attribute/PosixFilePermission", "getPosixFilePermissions", "(Lj$/nio/file/Path;[Lj$/nio/file/LinkOption;)Ljava/util/Set;", "setPosixFilePermissions", "(Lj$/nio/file/Path;Ljava/util/Set;)Lj$/nio/file/Path;", "createLinkPointingTo", "createSymbolicLinkPointingTo", "(Lj$/nio/file/Path;Lj$/nio/file/Path;[Lj$/nio/file/attribute/FileAttribute;)Lj$/nio/file/Path;", "readSymbolicLink", "createFile", "prefix", "suffix", "createTempFile", "(Ljava/lang/String;Ljava/lang/String;[Lj$/nio/file/attribute/FileAttribute;)Lj$/nio/file/Path;", "directory", "(Lj$/nio/file/Path;Ljava/lang/String;Ljava/lang/String;[Lj$/nio/file/attribute/FileAttribute;)Lj$/nio/file/Path;", "createTempDirectory", "(Ljava/lang/String;[Lj$/nio/file/attribute/FileAttribute;)Lj$/nio/file/Path;", "(Lj$/nio/file/Path;Ljava/lang/String;[Lj$/nio/file/attribute/FileAttribute;)Lj$/nio/file/Path;", "div", "(Lj$/nio/file/Path;Ljava/lang/String;)Lj$/nio/file/Path;", "Path", "(Ljava/lang/String;)Lj$/nio/file/Path;", "subpaths", "(Ljava/lang/String;[Ljava/lang/String;)Lj$/nio/file/Path;", "Ljava/net/URI;", "toPath", "(Ljava/net/URI;)Lj$/nio/file/Path;", "Lkotlin/io/path/PathWalkOption;", "walk", "(Lj$/nio/file/Path;[Lkotlin/io/path/PathWalkOption;)Lkotlin/sequences/Sequence;", "j$/nio/file/FileVisitor", "visitor", _UrlKt.FRAGMENT_ENCODE_SET, "maxDepth", "followLinks", "visitFileTree", "(Lj$/nio/file/Path;Lj$/nio/file/FileVisitor;IZ)V", "Lkotlin/io/path/FileVisitorBuilder;", "Lkotlin/ExtensionFunctionType;", "builderAction", "(Lj$/nio/file/Path;IZLkotlin/jvm/functions/Function1;)V", "fileVisitor", "(Lkotlin/jvm/functions/Function1;)Lj$/nio/file/FileVisitor;", "getName", "getName$annotations", "name", "getNameWithoutExtension", "getNameWithoutExtension$annotations", "nameWithoutExtension", "getExtension", "getExtension$annotations", "extension", "getPathString", "getPathString$annotations", "pathString", "getInvariantSeparatorsPathString", "getInvariantSeparatorsPathString$annotations", "invariantSeparatorsPathString", "getInvariantSeparatorsPath", "getInvariantSeparatorsPath$annotations", "invariantSeparatorsPath", "kotlin-stdlib-jdk7"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/io/path/PathsKt")
@SourceDebugExtension({"SMAP\nPathUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathUtils.kt\nkotlin/io/path/PathsKt__PathUtilsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1218:1\n1#2:1219\n1915#3,2:1220\n*S KotlinDebug\n*F\n+ 1 PathUtils.kt\nkotlin/io/path/PathsKt__PathUtilsKt\n*L\n418#1:1220,2\n*E\n"})
/* loaded from: classes5.dex */
class PathsKt__PathUtilsKt extends PathsKt__PathRecursiveFunctionsKt {
    public PathsKt__PathUtilsKt() {
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final Path Path(String r1) {
        return Paths.get(r1, new String[0]);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final Path Path(String r1, String... r2) {
        return Paths.get(r1, (String[]) Arrays.copyOf(r2, r2.length));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final Path absolute(Path r0) {
        return r0.toAbsolutePath();
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final String absolutePathString(Path r0) {
        return r0.toAbsolutePath().toString();
    }

    @SinceKotlin(version = "1.5")
    @IgnorableReturnValue
    @InlineOnly
    private static final Path copyTo(Path r2, Path r3, boolean r4) {
        if (r4 == false) goto L5;
        CopyOption[] r42 = {StandardCopyOption.REPLACE_EXISTING};
    L7:
        return Files.copy(r2, r3, (CopyOption[]) Arrays.copyOf(r42, r42.length));
    L5:
        r42 = new CopyOption[0];
        goto L7
    }

    @SinceKotlin(version = "1.5")
    @IgnorableReturnValue
    @InlineOnly
    private static final Path copyTo(Path r1, Path r2, CopyOption... r3) {
        return Files.copy(r1, r2, (CopyOption[]) Arrays.copyOf(r3, r3.length));
    }

    public static /* synthetic */ Path copyTo$default(Path r0, Path r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = false;
    L5:
        if (r2 == false) goto L7;
        CopyOption[] r22 = {StandardCopyOption.REPLACE_EXISTING};
    L9:
        return Files.copy(r0, r1, (CopyOption[]) Arrays.copyOf(r22, r22.length));
    L7:
        r22 = new CopyOption[0];
        goto L9
    }

    @SinceKotlin(version = "1.5")
    @IgnorableReturnValue
    @InlineOnly
    private static final Path createDirectories(Path r1, FileAttribute<?>... r2) {
        return Files.createDirectories(r1, (FileAttribute[]) Arrays.copyOf(r2, r2.length));
    }

    @SinceKotlin(version = "1.5")
    @IgnorableReturnValue
    @InlineOnly
    private static final Path createDirectory(Path r1, FileAttribute<?>... r2) {
        return Files.createDirectory(r1, (FileAttribute[]) Arrays.copyOf(r2, r2.length));
    }

    @SinceKotlin(version = "1.5")
    @IgnorableReturnValue
    @InlineOnly
    private static final Path createFile(Path r1, FileAttribute<?>... r2) {
        return Files.createFile(r1, (FileAttribute[]) Arrays.copyOf(r2, r2.length));
    }

    @SinceKotlin(version = "1.5")
    @IgnorableReturnValue
    @InlineOnly
    private static final Path createLinkPointingTo(Path r0, Path r1) {
        return Files.createLink(r0, r1);
    }

    @SinceKotlin(version = "1.9")
    @IgnorableReturnValue
    public static final Path createParentDirectories(Path r3, FileAttribute<?>... r4) {
        Path r0 = r3.getParent();
        if (r0 != null) goto L5;
    L13:
        return r3;
    L5:
        if (Files.isDirectory(r0, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0)) == true) goto L13;
        FileAttribute[] r42 = (FileAttribute[]) Arrays.copyOf(r4, r4.length);     // Catch: FileAlreadyExistsException -> L8
        Files.createDirectories(r0, (FileAttribute[]) Arrays.copyOf(r42, r42.length));     // Catch: FileAlreadyExistsException -> L8
        return r3;
    L8:
        e = move-exception;
        if (Files.isDirectory(r0, (LinkOption[]) Arrays.copyOf(new LinkOption[0], 0)) == true) goto L13;
        throw e;
    }

    @SinceKotlin(version = "1.5")
    @IgnorableReturnValue
    @InlineOnly
    private static final Path createSymbolicLinkPointingTo(Path r1, Path r2, FileAttribute<?>... r3) {
        return Files.createSymbolicLink(r1, r2, (FileAttribute[]) Arrays.copyOf(r3, r3.length));
    }

    @SinceKotlin(version = "1.5")
    public static final Path createTempDirectory(Path r1, String r2, FileAttribute<?>... r3) {
        if (r1 == null) goto L6;
        return Files.createTempDirectory(r1, r2, (FileAttribute[]) Arrays.copyOf(r3, r3.length));
    L6:
        return Files.createTempDirectory(r2, (FileAttribute[]) Arrays.copyOf(r3, r3.length));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final Path createTempDirectory(String r1, FileAttribute<?>... r2) {
        return Files.createTempDirectory(r1, (FileAttribute[]) Arrays.copyOf(r2, r2.length));
    }

    public static /* synthetic */ Path createTempDirectory$default(Path r0, String r1, FileAttribute[] r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r1 = null;
    L6:
        return createTempDirectory(r0, r1, r2);
    }

    public static /* synthetic */ Path createTempDirectory$default(String r0, FileAttribute[] r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r0 = null;
    L6:
        return Files.createTempDirectory(r0, (FileAttribute[]) Arrays.copyOf(r1, r1.length));
    }

    @SinceKotlin(version = "1.5")
    public static final Path createTempFile(Path r1, String r2, String r3, FileAttribute<?>... r4) {
        if (r1 == null) goto L6;
        return Files.createTempFile(r1, r2, r3, (FileAttribute[]) Arrays.copyOf(r4, r4.length));
    L6:
        return Files.createTempFile(r2, r3, (FileAttribute[]) Arrays.copyOf(r4, r4.length));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final Path createTempFile(String r1, String r2, FileAttribute<?>... r3) {
        return Files.createTempFile(r1, r2, (FileAttribute[]) Arrays.copyOf(r3, r3.length));
    }

    public static /* synthetic */ Path createTempFile$default(Path r1, String r2, String r3, FileAttribute[] r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r2 = null;
    L6:
        if ((r5 & 4) == 0) goto L9;
        r3 = null;
    L9:
        return createTempFile(r1, r2, r3, r4);
    }

    public static /* synthetic */ Path createTempFile$default(String r1, String r2, FileAttribute[] r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = null;
    L6:
        if ((r4 & 2) == 0) goto L9;
        r2 = null;
    L9:
        return Files.createTempFile(r1, r2, (FileAttribute[]) Arrays.copyOf(r3, r3.length));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final void deleteExisting(Path r0) {
        Files.delete(r0);
    }

    @SinceKotlin(version = "1.5")
    @IgnorableReturnValue
    @InlineOnly
    private static final boolean deleteIfExists(Path r0) {
        return Files.deleteIfExists(r0);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final Path div(Path r0, Path r1) {
        return r0.resolve(r1);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final Path div(Path r0, String r1) {
        return r0.resolve(r1);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final boolean exists(Path r1, LinkOption... r2) {
        return Files.exists(r1, (LinkOption[]) Arrays.copyOf(r2, r2.length));
    }

    @PublishedApi
    public static final Void fileAttributeViewNotAvailable(Path r3, Class<?> r4) {
        throw new UnsupportedOperationException("The desired attribute view type " + r4 + " is not available for the file " + r3 + '.');
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final /* synthetic */ <V extends FileAttributeView> V fileAttributesView(Path r3, LinkOption... r4) {
        Intrinsics.reifiedOperationMarker(4, "V");
        V r42 = (V) Files.getFileAttributeView(r3, FileAttributeView.class, (LinkOption[]) Arrays.copyOf(r4, r4.length));
        if (r42 == null) goto L5;
        return r42;
    L5:
        Intrinsics.reifiedOperationMarker(4, "V");
        fileAttributeViewNotAvailable(r3, FileAttributeView.class);
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final /* synthetic */ <V extends FileAttributeView> V fileAttributesViewOrNull(Path r2, LinkOption... r3) {
        Intrinsics.reifiedOperationMarker(4, "V");
        return (V) Files.getFileAttributeView(r2, FileAttributeView.class, (LinkOption[]) Arrays.copyOf(r3, r3.length));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final long fileSize(Path r2) {
        return Files.size(r2);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final FileStore fileStore(Path r0) {
        return Files.getFileStore(r0);
    }

    @SinceKotlin(version = "2.1")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final FileVisitor<Path> fileVisitor(Function1<? super FileVisitorBuilder, Unit> r1) {
        FileVisitorBuilderImpl r0 = new FileVisitorBuilderImpl();
        r1.invoke(r0);
        return r0.build();
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final void forEachDirectoryEntry(Path r2, String r3, Function1<? super Path, Unit> r4) {
        DirectoryStream<Path> r22 = Files.newDirectoryStream(r2, r3);
        Iterator<Path> r0 = r22.iterator();     // Catch: Throwable -> L7
    L5:
        if (r0.hasNext() == false) goto L9;
        r4.invoke(r0.next());     // Catch: Throwable -> L7
        goto L5
    L9:
        Unit r42 = Unit.INSTANCE;     // Catch: Throwable -> L7
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r22, null);
        InlineMarker.finallyEnd(1);
        return;
    L7:
        th = move-exception;
        throw th;     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r22, th);
        InlineMarker.finallyEnd(1);
        throw th;
    }

    public static /* synthetic */ void forEachDirectoryEntry$default(Path r0, String r1, Function1 r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L5;
        r1 = "*";
    L5:
        DirectoryStream<Path> r02 = Files.newDirectoryStream(r0, r1);
        Iterator<Path> r12 = r02.iterator();     // Catch: Throwable -> L10
    L8:
        if (r12.hasNext() == false) goto L12;
        r2.invoke(r12.next());     // Catch: Throwable -> L10
        goto L8
    L12:
        Unit r13 = Unit.INSTANCE;     // Catch: Throwable -> L10
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r02, null);
        InlineMarker.finallyEnd(1);
        return;
    L10:
        th = move-exception;
        throw th;     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r02, th);
        InlineMarker.finallyEnd(1);
        throw th;
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final Object getAttribute(Path r1, String r2, LinkOption... r3) {
        return Files.getAttribute(r1, r2, (LinkOption[]) Arrays.copyOf(r3, r3.length));
    }

    public static final String getExtension(Path r2) {
        Path r22 = r2.getFileName();
        if (r22 == null) goto L10;
        String r23 = r22.toString();
        if (r23 == null) goto L10;
        String r24 = StringsKt.substringAfterLast(r23, '.', _UrlKt.FRAGMENT_ENCODE_SET);
        if (r24 == null) goto L10;
        return r24;
    L10:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    @SinceKotlin(version = "1.5")
    public static /* synthetic */ void getExtension$annotations(Path r0) {
    }

    private static final String getInvariantSeparatorsPath(Path r0) {
        return getInvariantSeparatorsPathString(r0);
    }

    @SinceKotlin(version = "1.4")
    @InlineOnly
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use invariantSeparatorsPathString property instead.", replaceWith = @ReplaceWith(expression = "invariantSeparatorsPathString", imports = {}))
    @ExperimentalPathApi
    public static /* synthetic */ void getInvariantSeparatorsPath$annotations(Path r0) {
    }

    public static final String getInvariantSeparatorsPathString(Path r7) {
        String r2 = r7.getFileSystem().getSeparator();
        if (Intrinsics.areEqual(r2, "/") == true) goto L7;
        return StringsKt.replace$default(r7.toString(), r2, "/", false, 4, null);
    L7:
        return r7.toString();
    }

    @SinceKotlin(version = "1.5")
    public static /* synthetic */ void getInvariantSeparatorsPathString$annotations(Path r0) {
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final FileTime getLastModifiedTime(Path r1, LinkOption... r2) {
        return Files.getLastModifiedTime(r1, (LinkOption[]) Arrays.copyOf(r2, r2.length));
    }

    public static final String getName(Path r0) {
        Path r02 = r0.getFileName();
        if (r02 == null) goto L5;
        String r03 = r02.toString();
    L6:
        if (r03 != null) goto L9;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        return r03;
    L5:
        r03 = null;
        goto L6
    }

    @SinceKotlin(version = "1.5")
    public static /* synthetic */ void getName$annotations(Path r0) {
    }

    public static final String getNameWithoutExtension(Path r3) {
        Path r32 = r3.getFileName();
        if (r32 == null) goto L10;
        String r33 = r32.toString();
        if (r33 == null) goto L12;
        String r34 = StringsKt.substringBeforeLast$default(r33, ".", null, 2, null);
        if (r34 == null) goto L13;
        return r34;
    L13:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    @SinceKotlin(version = "1.5")
    public static /* synthetic */ void getNameWithoutExtension$annotations(Path r0) {
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final UserPrincipal getOwner(Path r1, LinkOption... r2) {
        return Files.getOwner(r1, (LinkOption[]) Arrays.copyOf(r2, r2.length));
    }

    private static final String getPathString(Path r0) {
        return r0.toString();
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    public static /* synthetic */ void getPathString$annotations(Path r0) {
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final Set<PosixFilePermission> getPosixFilePermissions(Path r1, LinkOption... r2) {
        return Files.getPosixFilePermissions(r1, (LinkOption[]) Arrays.copyOf(r2, r2.length));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final boolean isDirectory(Path r1, LinkOption... r2) {
        return Files.isDirectory(r1, (LinkOption[]) Arrays.copyOf(r2, r2.length));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final boolean isExecutable(Path r0) {
        return Files.isExecutable(r0);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final boolean isHidden(Path r0) {
        return Files.isHidden(r0);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final boolean isReadable(Path r0) {
        return Files.isReadable(r0);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final boolean isRegularFile(Path r1, LinkOption... r2) {
        return Files.isRegularFile(r1, (LinkOption[]) Arrays.copyOf(r2, r2.length));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final boolean isSameFileAs(Path r0, Path r1) {
        return Files.isSameFile(r0, r1);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final boolean isSymbolicLink(Path r0) {
        return Files.isSymbolicLink(r0);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final boolean isWritable(Path r0) {
        return Files.isWritable(r0);
    }

    @SinceKotlin(version = "1.5")
    public static final List<Path> listDirectoryEntries(Path r1, String r2) {
        DirectoryStream<Path> r12 = Files.newDirectoryStream(r1, r2);
        List<Path> r22 = CollectionsKt.toList(r12);     // Catch: Throwable -> L6
        CloseableKt.closeFinally(r12, null);
        return r22;
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        CloseableKt.closeFinally(r12, th);
        throw th;
    }

    public static /* synthetic */ List listDirectoryEntries$default(Path r0, String r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = "*";
    L6:
        return listDirectoryEntries(r0, r1);
    }

    @SinceKotlin(version = "1.5")
    @IgnorableReturnValue
    @InlineOnly
    private static final Path moveTo(Path r2, Path r3, boolean r4) {
        if (r4 == false) goto L5;
        CopyOption[] r42 = {StandardCopyOption.REPLACE_EXISTING};
    L7:
        return Files.move(r2, r3, (CopyOption[]) Arrays.copyOf(r42, r42.length));
    L5:
        r42 = new CopyOption[0];
        goto L7
    }

    @SinceKotlin(version = "1.5")
    @IgnorableReturnValue
    @InlineOnly
    private static final Path moveTo(Path r1, Path r2, CopyOption... r3) {
        return Files.move(r1, r2, (CopyOption[]) Arrays.copyOf(r3, r3.length));
    }

    public static /* synthetic */ Path moveTo$default(Path r0, Path r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = false;
    L5:
        if (r2 == false) goto L7;
        CopyOption[] r22 = {StandardCopyOption.REPLACE_EXISTING};
    L9:
        return Files.move(r0, r1, (CopyOption[]) Arrays.copyOf(r22, r22.length));
    L7:
        r22 = new CopyOption[0];
        goto L9
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final boolean notExists(Path r1, LinkOption... r2) {
        return Files.notExists(r1, (LinkOption[]) Arrays.copyOf(r2, r2.length));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final /* synthetic */ <A extends BasicFileAttributes> A readAttributes(Path r2, LinkOption... r3) {
        Intrinsics.reifiedOperationMarker(4, "A");
        return (A) Files.readAttributes(r2, BasicFileAttributes.class, (LinkOption[]) Arrays.copyOf(r3, r3.length));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final Map<String, Object> readAttributes(Path r1, String r2, LinkOption... r3) {
        return Files.readAttributes(r1, r2, (LinkOption[]) Arrays.copyOf(r3, r3.length));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final Path readSymbolicLink(Path r0) {
        return Files.readSymbolicLink(r0);
    }

    @SinceKotlin(version = "1.5")
    public static final Path relativeTo(Path r4, Path r5) {
        return PathRelativizer.INSTANCE.tryRelativeTo(r4, r5);
    L4:
        e = move-exception;
        throw new IllegalArgumentException(e.getMessage() + "\nthis path: " + r4 + "\nbase path: " + r5, e);
    }

    @SinceKotlin(version = "1.5")
    public static final Path relativeToOrNull(Path r1, Path r2) {
        return PathRelativizer.INSTANCE.tryRelativeTo(r1, r2);
    L4:
        return null;
    }

    @SinceKotlin(version = "1.5")
    public static final Path relativeToOrSelf(Path r0, Path r1) {
        Path r12 = relativeToOrNull(r0, r1);
        if (r12 != null) goto L5;
        return r0;
    L5:
        return r12;
    }

    @SinceKotlin(version = "1.5")
    @IgnorableReturnValue
    @InlineOnly
    private static final Path setAttribute(Path r1, String r2, Object r3, LinkOption... r4) {
        return Files.setAttribute(r1, r2, r3, (LinkOption[]) Arrays.copyOf(r4, r4.length));
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final Path setLastModifiedTime(Path r0, FileTime r1) {
        return Files.setLastModifiedTime(r0, r1);
    }

    @SinceKotlin(version = "1.5")
    @IgnorableReturnValue
    @InlineOnly
    private static final Path setOwner(Path r0, UserPrincipal r1) {
        return Files.setOwner(r0, r1);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final Path setPosixFilePermissions(Path r0, Set<? extends PosixFilePermission> r1) {
        return Files.setPosixFilePermissions(r0, r1);
    }

    @SinceKotlin(version = "1.5")
    @InlineOnly
    private static final Path toPath(URI r0) {
        return Paths.get(r0);
    }

    @SinceKotlin(version = "1.5")
    @IgnorableReturnValue
    @InlineOnly
    private static final <T> T useDirectoryEntries(Path r1, String r2, Function1<? super Sequence<? extends Path>, ? extends T> r3) {
        DirectoryStream<Path> r12 = Files.newDirectoryStream(r1, r2);
        T r32 = r3.invoke(CollectionsKt.asSequence(r12));     // Catch: Throwable -> L6
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r12, null);
        InlineMarker.finallyEnd(1);
        return r32;
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r12, th);
        InlineMarker.finallyEnd(1);
        throw th;
    }

    public static /* synthetic */ Object useDirectoryEntries$default(Path r0, String r1, Function1 r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L5;
        r1 = "*";
    L5:
        DirectoryStream<Path> r02 = Files.newDirectoryStream(r0, r1);
        Object r12 = r2.invoke(CollectionsKt.asSequence(r02));     // Catch: Throwable -> L9
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r02, null);
        InlineMarker.finallyEnd(1);
        return r12;
    L9:
        th = move-exception;
        throw th;     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        InlineMarker.finallyStart(1);
        CloseableKt.closeFinally(r02, th);
        InlineMarker.finallyEnd(1);
        throw th;
    }

    @SinceKotlin(version = "2.1")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final void visitFileTree(Path r0, int r1, boolean r2, Function1<? super FileVisitorBuilder, Unit> r3) {
        visitFileTree(r0, fileVisitor(r3), r1, r2);
    }

    @SinceKotlin(version = "2.1")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final void visitFileTree(Path r0, FileVisitor<Path> r1, int r2, boolean r3) {
        if (r3 == false) goto L4;
        Set r32 = SetsKt.setOf(FileVisitOption.FOLLOW_LINKS);
    L5:
        Files.walkFileTree(r0, r32, r2, r1);
        return;
    L4:
        r32 = SetsKt.emptySet();
        goto L5
    }

    public static /* synthetic */ void visitFileTree$default(Path r0, int r1, boolean r2, Function1 r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = Integer.MAX_VALUE;
    L6:
        if ((r4 & 2) == 0) goto L8;
        r2 = false;
    L8:
        visitFileTree(r0, r1, r2, r3);
    }

    public static /* synthetic */ void visitFileTree$default(Path r0, FileVisitor r1, int r2, boolean r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = Integer.MAX_VALUE;
    L6:
        if ((r4 & 4) == 0) goto L8;
        r3 = false;
    L8:
        visitFileTree(r0, r1, r2, r3);
    }

    @SinceKotlin(version = "2.1")
    @WasExperimental(markerClass = {ExperimentalPathApi.class})
    public static final Sequence<Path> walk(Path r1, PathWalkOption... r2) {
        return new PathTreeWalk(r1, r2);
    }
}
