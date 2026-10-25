package com.exteragram.messenger.plugins.pip;

import com.android.dx.rop.code.RegisterSpec;
import com.chaquo.python.internal.Common;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.utils.network.ExteraHttpClient;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.jna.Native$$ExternalSyntheticBUOutline1;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.url._UrlKt;
import okio.ZipFileSystem$$ExternalSyntheticBUOutline0;
import org.mvel2.MVEL;
import org.mvel2.asm.signature.SignatureVisitor;
import org.simplifiles.SimpliFiles;
import org.simplifiles.archive.ArchiveExtractionOptions;
import org.simplifiles.archive.ExtractionTargetPolicy;
import org.simplifiles.archive.security.SecurityPolicy;
import org.simplifiles.files.OverwritePolicy;
import org.simplifiles.files.SimpliFile;
import org.telegram.messenger.FileLog;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0006wxyz{|B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u0010H\u0002J\b\u0010.\u001a\u00020/H\u0002J&\u00100\u001a \u0012\u0004\u0012\u00020\u0010\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u0010020101H\u0002J.\u00103\u001a\u00020/2$\u00104\u001a \u0012\u0004\u0012\u00020\u0010\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u0010020101H\u0002J\b\u00105\u001a\u00020/H\u0002J\u0006\u00106\u001a\u00020/J.\u00108\u001a\b\u0012\u0004\u0012\u00020\u0010092\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u0010092\u0006\u0010;\u001a\u00020\u00102\n\b\u0002\u0010<\u001a\u0004\u0018\u00010=JV\u0010>\u001a\u00020/2\u0006\u0010?\u001a\u00020\u00102\u0018\u0010@\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A092\u0018\u0010B\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A0\u00112\u0006\u0010;\u001a\u00020\u00102\b\u0010<\u001a\u0004\u0018\u00010=H\u0002J*\u0010C\u001a\u00020/2\u0006\u0010;\u001a\u00020\u00102\u0018\u0010D\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A02H\u0002J\b\u0010E\u001a\u00020/H\u0002J\b\u0010F\u001a\u00020/H\u0002J\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u001002J\u000e\u0010H\u001a\u00020/2\u0006\u0010;\u001a\u00020\u0010J4\u0010I\u001a\u00020\u00102\u0006\u0010?\u001a\u00020\u00102\u0018\u0010@\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A092\b\u0010<\u001a\u0004\u0018\u00010=H\u0002J\"\u0010J\u001a\u0004\u0018\u00010K2\u0006\u0010L\u001a\u00020\u00102\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020PH\u0002J\u0016\u0010Q\u001a\u0004\u0018\u00010\u0010*\u00020N2\u0006\u0010-\u001a\u00020\u0010H\u0002J\u001b\u0010R\u001a\u0004\u0018\u00010P*\u00020N2\u0006\u0010-\u001a\u00020\u0010H\u0002¢\u0006\u0002\u0010SJ\u0010\u0010T\u001a\u00020P2\u0006\u0010U\u001a\u00020\u0010H\u0002J\u0010\u0010V\u001a\u00020P2\u0006\u0010W\u001a\u00020\u0010H\u0002J\u001a\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020[2\b\u0010<\u001a\u0004\u0018\u00010=H\u0002J,\u0010\\\u001a\u0004\u0018\u00010\u00102\u0006\u0010?\u001a\u00020\u00102\u0018\u0010@\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A09H\u0002J,\u0010]\u001a\u0004\u0018\u00010\u00102\u0006\u0010?\u001a\u00020\u00102\u0018\u0010@\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A09H\u0002J\u0018\u0010^\u001a\u00020\t2\u0006\u0010?\u001a\u00020\u00102\u0006\u0010L\u001a\u00020\u0010H\u0002J\u0018\u0010_\u001a\u00020/2\u0006\u0010?\u001a\u00020\u00102\u0006\u0010L\u001a\u00020\u0010H\u0002J.\u0010`\u001a \u0012\u0004\u0012\u00020\u0010\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A090A2\u0006\u0010a\u001a\u00020\u0010H\u0002J\"\u0010b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A092\u0006\u0010c\u001a\u00020\u0010H\u0002J\u0016\u0010d\u001a\b\u0012\u0004\u0012\u00020\u0010092\u0006\u0010e\u001a\u00020\tH\u0002J\u0010\u0010f\u001a\u00020P2\u0006\u0010g\u001a\u00020\u0010H\u0002J\u001a\u0010h\u001a\u0004\u0018\u00010\t2\u0006\u0010?\u001a\u00020\u00102\u0006\u0010L\u001a\u00020\u0010H\u0002J*\u0010i\u001a\u00020P2\u0006\u0010L\u001a\u00020\u00102\u0018\u0010@\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A09H\u0002J\u0010\u0010j\u001a\u00020P2\u0006\u0010k\u001a\u00020\u0010H\u0002J6\u0010l\u001a\b\u0012\u0004\u0012\u00020\u0010092\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\u0010092\u0018\u0010@\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A09H\u0002J\"\u0010n\u001a\u00020P2\u0018\u0010@\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A09H\u0002J\u0010\u0010o\u001a\u00020P2\u0006\u0010L\u001a\u00020\u0010H\u0002J\u0018\u0010p\u001a\u00020P2\u0006\u0010L\u001a\u00020\u00102\u0006\u0010k\u001a\u00020\u0010H\u0002J\u0016\u0010q\u001a\b\u0012\u0004\u0012\u00020r092\u0006\u0010L\u001a\u00020\u0010H\u0002J\u0010\u0010s\u001a\u00020\u00102\u0006\u0010t\u001a\u00020\tH\u0002J\u0010\u0010u\u001a\u00020v2\u0006\u0010L\u001a\u00020\u0010H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR4\u0010\u000e\u001a(\u0012\u0004\u0012\u00020\u0010\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00110\u000fj\u0002`\u00120\u000fj\u0002`\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001dX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001dX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00107\u001a\b\u0012\u0004\u0012\u00020\u001002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006}"}, d2 = {"Lcom/exteragram/messenger/plugins/pip/PipController;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "client", "Lokhttp3/OkHttpClient;", "gson", "Lcom/google/gson/Gson;", "libsDir", "Ljava/io/File;", "getLibsDir", "()Ljava/io/File;", "registryFile", "getRegistryFile", "registry", "Ljava/util/concurrent/ConcurrentHashMap;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/pip/VersionMap;", "Lcom/exteragram/messenger/plugins/pip/RegistryMap;", "installLocks", "wheelArchivePolicy", "Lorg/simplifiles/archive/security/SecurityPolicy;", "pythonVersion", "getPythonVersion", "()Ljava/lang/String;", "setPythonVersion", "(Ljava/lang/String;)V", "MAX_REGISTRY_BYTES", _UrlKt.FRAGMENT_ENCODE_SET, "MAX_METADATA_BYTES", "MAX_WHEEL_BYTES", "ENV_SYS_PLATFORM", "ENV_PLATFORM_SYSTEM", "ENV_OS_NAME", "REGEX_NORMALIZE", "Lkotlin/text/Regex;", "REGEX_REQ_PARSE", "REGEX_REQ_SPECS", "REGEX_REQ_EXTRA", "REGEX_REQ_PAREN", "REGEX_VERSION_SPLIT", "REGEX_MARKER_TOKEN", "REGEX_VERSION_WILDCARD", "normalizePackageName", "name", "loadRegistry", _UrlKt.FRAGMENT_ENCODE_SET, "snapshotRegistry", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "restoreRegistry", "snapshot", "saveRegistry", "cleanup", "PREINSTALLED_PACKAGES", "installDependencies", _UrlKt.FRAGMENT_ENCODE_SET, Common.ASSET_REQUIREMENTS, "pluginId", "delegate", "Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;", "resolveAndInstall", "pkg", "specs", "Lkotlin/Pair;", "installedAccumulator", "updateRegistryForPlugin", "currentlyNeeded", "cleanupInternal", "removeOrphanedDirectories", "activeLibraryPaths", "uninstallDependencies", "installPackage", "selectWheelCandidate", "Lcom/exteragram/messenger/plugins/pip/PipController$WheelCandidate;", "version", "artifact", "Lcom/google/gson/JsonObject;", "allowYanked", _UrlKt.FRAGMENT_ENCODE_SET, "getStringOrNull", "getBooleanOrNull", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/Boolean;", "isPurePythonWheelCompatible", "filename", "isPythonTagCompatible", "tag", "executeWithRetry", "Lokhttp3/Response;", PluginsConstants.REQUEST, "Lokhttp3/Request;", "findVersionOnDisk", "findInstalledVersion", "getLibPath", "deletePackage", "parseRequirement", "req", "parseSpecs", "specsString", "parseDependenciesFromMetadata", "metadataFile", "isMarkerCompatible", "marker", "findMetadataFile", "checkVersionSatisfies", "isWildcardVersionSpec", "spec", "filterPreReleases", "versions", "specsAllowPreRelease", "isPreReleaseVersion", "matchesVersionWildcard", "parseVersionReleaseParts", _UrlKt.FRAGMENT_ENCODE_SET, "calculateSha256", "file", "parseVersion", "Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;", "InstallerDelegate", "WheelCandidate", "ParsedVersion", "MarkerParser", "SizeLimitedInputStream", "VersionComparator", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPipController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PipController.kt\ncom/exteragram/messenger/plugins/pip/PipController\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 6 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,1115:1\n221#2:1116\n221#2,2:1117\n222#2:1119\n221#2:1131\n221#2,2:1132\n222#2:1134\n221#2:1138\n221#2,2:1139\n222#2:1141\n221#2:1144\n221#2,2:1145\n222#2:1147\n221#2:1150\n222#2:1153\n221#2:1156\n221#2,2:1157\n222#2:1159\n221#2:1160\n221#2,2:1161\n222#2:1163\n493#3:1120\n442#3:1121\n493#3:1124\n442#3:1125\n1266#4,2:1122\n1266#4,4:1126\n1269#4:1130\n296#4,2:1135\n1915#4,2:1142\n1915#4,2:1148\n1915#4,2:1151\n1915#4,2:1164\n777#4:1166\n873#4,2:1167\n1807#4,3:1169\n1807#4,3:1172\n777#4:1175\n873#4,2:1176\n832#4:1178\n862#4,2:1179\n1807#4,3:1181\n1807#4,3:1184\n1786#4,3:1187\n1642#4,10:1190\n1915#4:1200\n1916#4:1202\n1652#4:1203\n1586#4:1204\n1661#4,3:1205\n1#5:1137\n1#5:1201\n14048#6,2:1154\n*S KotlinDebug\n*F\n+ 1 PipController.kt\ncom/exteragram/messenger/plugins/pip/PipController\n*L\n118#1:1116\n121#1:1117,2\n118#1:1119\n152#1:1131\n154#1:1132,2\n152#1:1134\n304#1:1138\n305#1:1139,2\n304#1:1141\n328#1:1144\n329#1:1145,2\n328#1:1147\n349#1:1150\n349#1:1153\n374#1:1156\n375#1:1157,2\n374#1:1159\n392#1:1160\n393#1:1161,2\n392#1:1163\n144#1:1120\n144#1:1121\n145#1:1124\n145#1:1125\n144#1:1122,2\n145#1:1126,4\n144#1:1130\n236#1:1135,2\n316#1:1142,2\n336#1:1148,2\n350#1:1151,2\n403#1:1164,2\n447#1:1166\n447#1:1167,2\n451#1:1169,3\n591#1:1172,3\n645#1:1175\n645#1:1176,2\n960#1:1178\n960#1:1179,2\n965#1:1181,3\n969#1:1184,3\n998#1:1187,3\n1004#1:1190,10\n1004#1:1200\n1004#1:1202\n1004#1:1203\n178#1:1204\n178#1:1205,3\n1004#1:1201\n358#1:1154,2\n*E\n"})
/* loaded from: classes.dex */
public final class PipController {
    private static final String ENV_OS_NAME = "posix";
    private static final String ENV_PLATFORM_SYSTEM = "Linux";
    private static final String ENV_SYS_PLATFORM = "linux";
    public static final PipController INSTANCE = null;
    private static final long MAX_METADATA_BYTES = 4194304;
    private static final long MAX_REGISTRY_BYTES = 4194304;
    private static final long MAX_WHEEL_BYTES = 262144000;
    private static final Set<String> PREINSTALLED_PACKAGES = null;
    private static final Regex REGEX_MARKER_TOKEN = null;
    private static final Regex REGEX_NORMALIZE = null;
    private static final Regex REGEX_REQ_EXTRA = null;
    private static final Regex REGEX_REQ_PAREN = null;
    private static final Regex REGEX_REQ_PARSE = null;
    private static final Regex REGEX_REQ_SPECS = null;
    private static final Regex REGEX_VERSION_SPLIT = null;
    private static final Regex REGEX_VERSION_WILDCARD = null;
    private static final OkHttpClient client = null;
    private static final Gson gson = null;
    private static final ConcurrentHashMap<String, Object> installLocks = null;
    private static String pythonVersion;
    private static final ConcurrentHashMap<String, ConcurrentHashMap<String, Set<String>>> registry = null;
    private static final SecurityPolicy wheelArchivePolicy = null;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;", _UrlKt.FRAGMENT_ENCODE_SET, "onProgress", _UrlKt.FRAGMENT_ENCODE_SET, "text", _UrlKt.FRAGMENT_ENCODE_SET, "isCancelled", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface InstallerDelegate {
        boolean isCancelled();

        void onProgress(String r1);
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010\f\u001a\u00020\u000bH\u0002J\b\u0010\r\u001a\u00020\u000bH\u0002J\b\u0010\u000e\u001a\u00020\u000bH\u0002J\b\u0010\u000f\u001a\u00020\u000bH\u0002J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0003H\u0002J \u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003H\u0002J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0016\u001a\u00020\u0003H\u0002J\u0014\u0010\u0017\u001a\u0004\u0018\u00010\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u0002J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003H\u0002J\u0010\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0003H\u0002J\u0010\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u0003H\u0002J\u0010\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u0003H\u0002J\n\u0010\u001f\u001a\u0004\u0018\u00010\u0003H\u0002J\u0014\u0010 \u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010!\u001a\u00020\tH\u0002R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/exteragram/messenger/plugins/pip/PipController$MarkerParser;", _UrlKt.FRAGMENT_ENCODE_SET, "marker", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;)V", "tokens", _UrlKt.FRAGMENT_ENCODE_SET, "position", _UrlKt.FRAGMENT_ENCODE_SET, "parse", _UrlKt.FRAGMENT_ENCODE_SET, "parseOr", "parseAnd", "parseFactor", "parseComparison", "nextOperator", "evaluateComparison", "left", "operator", "right", "markerVariableName", "token", "markerValue", "name", "invertVersionOperator", "unquote", "value", "match", "expected", "matchKeyword", "next", "peek", "offset", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class MarkerParser {
        private int position;
        private final List<String> tokens;

        public static String $r8$lambda$2nxjO8cTPCazwOsauAZxX68kyv8(MatchResult r0) {
            return r0.getValue();
        }

        public MarkerParser(String r5) {
            this.tokens = SequencesKt.toList(SequencesKt.map(Regex.findAll$default(PipController.access$getREGEX_MARKER_TOKEN$p(), r5, 0, 2, null), new PipController$MarkerParser$$ExternalSyntheticLambda0()));
        }

        private final boolean evaluateComparison(String r8, String r9, String r10) {
            String r0 = markerVariableName(r8);
            String r1 = markerVariableName(r10);
            String r2 = markerValue(r0);
            if (r2 != null) goto L5;
            r2 = unquote(r8);
        L5:
            String r82 = markerValue(r1);
            if (r82 != null) goto L9;
            r82 = unquote(r10);
        L9:
            if (r0 != null) goto L11;
        L13:
            if (r1 == null) goto L18;
            if (markerValue(r1) != null) goto L18;
            return true;
        L18:
            if (Intrinsics.areEqual(r0, "python_version") == false) goto L20;
        L23:
            boolean r02 = true;
        L25:
            if (Intrinsics.areEqual(r1, "python_version") == false) goto L27;
        L30:
            boolean r12 = true;
        L31:
            if (r02 == false) goto L35;
            if (r12 == true) goto L35;
            return PipController.access$checkVersionSatisfies(PipController.INSTANCE, r2, CollectionsKt.listOf(TuplesKt.to(r9, r82)));
        L35:
            if (r12 == false) goto L39;
            if (r02 == true) goto L39;
            return PipController.access$checkVersionSatisfies(PipController.INSTANCE, r82, CollectionsKt.listOf(TuplesKt.to(invertVersionOperator(r9), r2)));
        L39:
            int r7 = r9.hashCode();
            if (r7 == (-1039759982)) goto L71;
            if (r7 == 1084) goto L64;
            if (r7 == 1952) goto L59;
            if (r7 == 3365) goto L54;
            if (r7 == 60573) goto L51;
        L72:
            return true;
        L51:
            if (r9.equals("===") == false) goto L72;
        L62:
            return StringsKt.equals(r2, r82, true);
        L54:
            if (r9.equals("in") == false) goto L72;
            return StringsKt.contains(r82, r2, true);
        L59:
            if (r9.equals("==") == true) goto L62;
        L64:
            if (r9.equals("!=") == false) goto L72;
            if (StringsKt.equals(r2, r82, true) == true) goto L69;
            return true;
        L69:
            return false;
        L71:
            if (r9.equals("not in") == false) goto L72;
            if (StringsKt.contains(r82, r2, true) == true) goto L76;
            return true;
        L76:
            return false;
        L27:
            if (Intrinsics.areEqual(r1, "python_full_version") == true) goto L30;
            r12 = false;
            goto L31
        L20:
            if (Intrinsics.areEqual(r0, "python_full_version") == true) goto L23;
            r02 = false;
            goto L25
        L11:
            if (markerValue(r0) != null) goto L13;
            return true;
        }

        private final String invertVersionOperator(String r4) {
            int r3 = r4.hashCode();
            if (r3 == 60) goto L24;
            if (r3 == 62) goto L20;
            if (r3 == 1921) goto L16;
            if (r3 == 1983) goto L12;
        L25:
            return r4;
        L12:
            if (r4.equals(">=") == false) goto L25;
            return "<=";
        L16:
            if (r4.equals("<=") == false) goto L25;
            return ">=";
        L20:
            if (r4.equals(">") == false) goto L25;
            return "<";
        L24:
            if (r4.equals("<") == false) goto L25;
            return ">";
        }

        private final String markerValue(String r10) {
            if (r10 != null) goto L4;
            return null;
        L4:
            switch(r10.hashCode()) {
                case -1182845946: goto L42;
                case -920496011: goto L37;
                case -193632853: goto L32;
                case 96965648: goto L27;
                case 117846437: goto L22;
                case 178915064: goto L17;
                case 1653682043: goto L12;
                case 1746933417: goto L7;
                default: goto L48;
            };
        L48:
            return null;
        L7:
            if (r10.equals("platform_python_implementation") == false) goto L49;
            return "CPython";
        L49:
            return null;
        L12:
            if (r10.equals("platform_system") == false) goto L50;
            return PipController.ENV_PLATFORM_SYSTEM;
        L50:
            return null;
        L17:
            if (r10.equals("implementation_name") == false) goto L51;
            return "cpython";
        L51:
            return null;
        L22:
            if (r10.equals("sys_platform") == false) goto L52;
            return PipController.ENV_SYS_PLATFORM;
        L52:
            return null;
        L27:
            if (r10.equals("extra") == false) goto L53;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L53:
            return null;
        L32:
            if (r10.equals("python_full_version") == true) goto L35;
            return null;
        L35:
            return PipController.INSTANCE.getPythonVersion();
        L37:
            if (r10.equals("python_version") == true) goto L40;
            return null;
        L40:
            return CollectionsKt.joinToString$default(CollectionsKt.take(StringsKt.split$default(PipController.INSTANCE.getPythonVersion(), new String[]{"."}, false, 0, 6, null), 2), ".", null, null, 0, null, null, 62, null);
        L42:
            if (r10.equals("os_name") == false) goto L56;
            return PipController.ENV_OS_NAME;
        L56:
            return null;
        }

        private final String markerVariableName(String r4) {
            if (StringsKt.startsWith$default(r4, "'", false, 2, null) == false) goto L5;
        L10:
            return null;
        L5:
            if (StringsKt.startsWith$default(r4, "\"", false, 2, null) == true) goto L10;
            String r3 = r4.toLowerCase(Locale.ROOT);
            if (new Regex("[a-z_][a-z0-9_]*").matches(r3) == false) goto L10;
            return r3;
        }

        private final boolean match(String r4) {
            if (Intrinsics.areEqual(peek$default(this, 0, 1, null), r4) == true) goto L5;
            return false;
        L5:
            this.position++;
            return true;
        }

        private final boolean matchKeyword(String r5) {
            String r2 = null;
            String r3 = peek$default(this, 0, 1, null);
            if (r3 == null) goto L6;
            r2 = r3.toLowerCase(Locale.ROOT);
        L6:
            if (Intrinsics.areEqual(r2, r5) == true) goto L8;
            return false;
        L8:
            this.position++;
            return true;
        }

        private final String next() {
            String r0 = peek$default(this, 0, 1, null);
            if (r0 != null) goto L5;
            return null;
        L5:
            this.position++;
            return r0;
        }

        private final String nextOperator() {
            String r0 = peek$default(this, 0, 1, null);
            if (r0 != null) goto L5;
            return null;
        L5:
            Locale r3 = Locale.ROOT;
            String r02 = r0.toLowerCase(r3);
            if (Intrinsics.areEqual(r02, "not") == false) goto L15;
            String r4 = peek(1);
            if (r4 == null) goto L10;
            String r32 = r4.toLowerCase(r3);
        L12:
            if (Intrinsics.areEqual(r32, "in") == false) goto L15;
            this.position += 2;
            return "not in";
        L10:
            r32 = null;
        L15:
            int r33 = r02.hashCode();
            if (r33 == 60) goto L59;
            if (r33 == 62) goto L56;
            if (r33 == 1084) goto L53;
            if (r33 == 1921) goto L50;
            if (r33 == 1952) goto L47;
            if (r33 == 1983) goto L44;
            if (r33 == 3365) goto L41;
            if (r33 == 3967) goto L38;
            if (r33 == 60573) goto L35;
        L60:
            return null;
        L35:
            if (r02.equals("===") == false) goto L60;
        L61:
            this.position++;
            return r02;
        L38:
            if (r02.equals("~=") == false) goto L60;
        L41:
            if (r02.equals("in") == true) goto L61;
        L44:
            if (r02.equals(">=") == true) goto L61;
        L47:
            if (r02.equals("==") == true) goto L61;
        L50:
            if (r02.equals("<=") == true) goto L61;
        L53:
            if (r02.equals("!=") == true) goto L61;
        L56:
            if (r02.equals(">") == true) goto L61;
        L59:
            if (r02.equals("<") == true) goto L61;
            goto L60
        }

        private final boolean parseAnd() {
            boolean r0 = parseFactor();
        L4:
            if (matchKeyword("and") == false) goto L10;
            boolean r1 = parseFactor();
            if (r0 == false) goto L9;
            if (r1 == false) goto L9;
            r0 = true;
        L9:
            r0 = false;
            goto L4
        L10:
            return r0;
        }

        private final boolean parseComparison() {
            String r0 = next();
            if (r0 != null) goto L5;
            return true;
        L5:
            String r2 = nextOperator();
            if (r2 != null) goto L8;
            return true;
        L8:
            String r3 = next();
            if (r3 != null) goto L12;
            return true;
        L12:
            return evaluateComparison(r0, r2, r3);
        }

        private final boolean parseFactor() {
            if (match("(") == false) goto L7;
            boolean r0 = parseOr();
            match(")");
            return r0;
        L7:
            return parseComparison();
        }

        private final boolean parseOr() {
            boolean r0 = parseAnd();
        L4:
            if (matchKeyword(PluginsConstants.HookFilterTypes.OR) == false) goto L11;
            boolean r1 = parseAnd();
            if (r0 == true) goto L10;
            if (r1 == true) goto L10;
            r0 = false;
        L10:
            r0 = true;
            goto L4
        L11:
            return r0;
        }

        private final String peek(int r2) {
            return (String) CollectionsKt.getOrNull(this.tokens, this.position + r2);
        }

        public static /* synthetic */ String peek$default(MarkerParser r0, int r1, int r2, Object r3) {
            if ((r2 & 1) == 0) goto L6;
            r1 = 0;
        L6:
            return r0.peek(r1);
        }

        private final String unquote(String r3) {
            if (r3.length() < 2) goto L12;
            char r2 = StringsKt.first(r3);
            char r0 = StringsKt.last(r3);
            if (r2 != '\'') goto L8;
            if (r0 != '\'') goto L8;
        L11:
            return r3.substring(1, r3.length() - 1);
        L8:
            if (r2 != '\"') goto L12;
            if (r0 == '\"') goto L11;
        L12:
            return r3;
        }

        public final boolean parse() {
            if (this.tokens.isEmpty() == false) goto L10;
            return true;
        L10:
            return parseOr();
        L7:
            e = move-exception;
            FileLog.e("PipController: Failed to evaluate marker: " + CollectionsKt.joinToString$default(this.tokens, " ", null, null, 0, null, null, 62, null), e);
            return true;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0003J-\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;", _UrlKt.FRAGMENT_ENCODE_SET, "epoch", _UrlKt.FRAGMENT_ENCODE_SET, "publicVersion", _UrlKt.FRAGMENT_ENCODE_SET, "parts", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(ILjava/lang/String;Ljava/util/List;)V", "getEpoch", "()I", "getPublicVersion", "()Ljava/lang/String;", "getParts", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "hashCode", "toString", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ParsedVersion {
        private final int epoch;
        private final List<String> parts;
        private final String publicVersion;

        public ParsedVersion(int r1, String r2, List<String> r3) {
            this.epoch = r1;
            this.publicVersion = r2;
            this.parts = r3;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ParsedVersion copy$default(ParsedVersion r0, int r1, String r2, List r3, int r4, Object r5) {
            if ((r4 & 1) == 0) goto L6;
            r1 = r0.epoch;
        L6:
            if ((r4 & 2) == 0) goto L9;
            r2 = r0.publicVersion;
        L9:
            if ((r4 & 4) == 0) goto L12;
            r3 = r0.parts;
        L12:
            return r0.copy(r1, r2, r3);
        }

        public final int component1() {
            return this.epoch;
        }

        public final String component2() {
            return this.publicVersion;
        }

        public final List<String> component3() {
            return this.parts;
        }

        public final ParsedVersion copy(int r1, String r2, List<String> r3) {
            return new ParsedVersion(r1, r2, r3);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof ParsedVersion) == true) goto L8;
            return false;
        L8:
            ParsedVersion r52 = (ParsedVersion) r5;
            if (this.epoch == r52.epoch) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.publicVersion, r52.publicVersion) == true) goto L15;
            return false;
        L15:
            if (Intrinsics.areEqual(this.parts, r52.parts) == true) goto L17;
            return false;
        L17:
            return true;
        }

        public final int getEpoch() {
            return this.epoch;
        }

        public final List<String> getParts() {
            return this.parts;
        }

        public final String getPublicVersion() {
            return this.publicVersion;
        }

        public int hashCode() {
            return (((Integer.hashCode(this.epoch) * 31) + this.publicVersion.hashCode()) * 31) + this.parts.hashCode();
        }

        public String toString() {
            return "ParsedVersion(epoch=" + this.epoch + ", publicVersion=" + this.publicVersion + ", parts=" + this.parts + ")";
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\tH\u0016J \u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\tH\u0016J\b\u0010\u000f\u001a\u00020\tH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/exteragram/messenger/plugins/pip/PipController$SizeLimitedInputStream;", "Ljava/io/InputStream;", "delegate", "maxBytes", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/io/InputStream;J)V", "total", "track", _UrlKt.FRAGMENT_ENCODE_SET, "read", "b", _UrlKt.FRAGMENT_ENCODE_SET, "off", "len", "available", "close", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class SizeLimitedInputStream extends InputStream implements InputStreamRetargetInterface {
        private final InputStream delegate;
        private final long maxBytes;
        private long total;

        public SizeLimitedInputStream(InputStream r1, long r2) {
            this.delegate = r1;
            this.maxBytes = r2;
        }

        private final int track(int r5) {
            if (r5 <= 0) goto L8;
            long r0 = this.total + r5;
            this.total = r0;
            long r2 = this.maxBytes;
            if (r0 <= r2) goto L8;
            throw new IOException("Download exceeds maximum allowed size of " + r2 + " bytes");
        L8:
            return r5;
        }

        @Override // java.io.InputStream
        public int available() {
            return this.delegate.available();
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.delegate.close();
        }

        @Override // java.io.InputStream
        public int read() {
            int r0 = this.delegate.read();
            if (r0 == (-1)) goto L5;
            track(1);
        L5:
            return r0;
        }

        @Override // java.io.InputStream
        public int read(byte[] r2, int r3, int r4) {
            return track(this.delegate.read(r2, r3, r4));
        }

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public /* synthetic */ long transferTo(OutputStream r1) {
            return DesugarInputStream.transferTo(this, r1);
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0002H\u0002¨\u0006\f"}, d2 = {"Lcom/exteragram/messenger/plugins/pip/PipController$VersionComparator;", "Ljava/util/Comparator;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/Comparator;", "<init>", "()V", "compare", _UrlKt.FRAGMENT_ENCODE_SET, "v1", "v2", "getWeight", "s", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class VersionComparator implements Comparator<String> {
        public static final VersionComparator INSTANCE = null;

        static {
            INSTANCE = new VersionComparator();
        }

        private VersionComparator() {
        }

        private final int getWeight(String r1) {
            if (Intrinsics.areEqual(r1, "post") == false) goto L7;
            return 110;
        L7:
            if (r1.length() != 0) goto L11;
            return 100;
        L11:
            if (Intrinsics.areEqual(r1, "rc") == false) goto L13;
            return 80;
        L13:
            if (Intrinsics.areEqual(r1, "c") == false) goto L16;
            return 80;
        L16:
            if (Intrinsics.areEqual(r1, "beta") == false) goto L18;
            return 70;
        L18:
            if (Intrinsics.areEqual(r1, "b") == false) goto L21;
            return 70;
        L21:
            if (Intrinsics.areEqual(r1, "alpha") == false) goto L23;
            return 60;
        L23:
            if (Intrinsics.areEqual(r1, "a") == false) goto L26;
            return 60;
        L26:
            if (Intrinsics.areEqual(r1, "dev") == false) goto L29;
            return 50;
        L29:
            return 0;
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(String r1, String r2) {
            return compare2(r1, r2);
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(String r9, String r10) {
            PipController r0 = PipController.INSTANCE;
            ParsedVersion r92 = PipController.access$parseVersion(r0, r9);
            ParsedVersion r102 = PipController.access$parseVersion(r0, r10);
            int r02 = Intrinsics.compare(r92.getEpoch(), r102.getEpoch());
            if (r02 == 0) goto L5;
            return r02;
        L5:
            List<String> r93 = r92.getParts();
            List<String> r103 = r102.getParts();
            int r03 = Math.max(r93.size(), r103.size());
            int r2 = 0;
        L6:
            if (r2 >= r03) goto L46;
            String r3 = (String) CollectionsKt.getOrNull(r93, r2);
            String r4 = (String) CollectionsKt.getOrNull(r103, r2);
            Integer r6 = null;
            if (r3 != null) goto L14;
            if (r4 == null) goto L11;
            Integer r7 = StringsKt.toIntOrNull(r4);
        L12:
            if (r7 == null) goto L14;
            r3 = MVEL.VERSION_SUB;
            goto L14
        L11:
            r7 = null;
        L14:
            if (r4 != null) goto L20;
            if (r3 == null) goto L17;
            r6 = StringsKt.toIntOrNull(r3);
        L17:
            if (r6 == null) goto L20;
            r4 = MVEL.VERSION_SUB;
        L20:
            if (r3 != null) goto L22;
            r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        L22:
            if (r4 != null) goto L25;
            r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        L25:
            if (Intrinsics.areEqual(r3, r4) == true) goto L45;
            Integer r5 = StringsKt.toIntOrNull(r3);
            Integer r62 = StringsKt.toIntOrNull(r4);
            if (r5 == null) goto L32;
            if (r62 == null) goto L32;
            int r32 = Intrinsics.compare(r5.intValue(), r62.intValue());
            if (r32 == 0) goto L45;
            return r32;
        L32:
            if (r5 != null) goto L33;
            if (r62 != null) goto L36;
            int r52 = getWeight(r3);
            int r63 = getWeight(r4);
            if (r52 != r63) goto L41;
            int r33 = r3.compareTo(r4);
            if (r33 == 0) goto L45;
            return r33;
        L41:
            return Intrinsics.compare(r52, r63);
        L36:
            return -1;
        L33:
            return 1;
        L45:
            r2 = r2 + 1;
            goto L6
        L46:
            return 0;
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J)\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/exteragram/messenger/plugins/pip/PipController$WheelCandidate;", _UrlKt.FRAGMENT_ENCODE_SET, "version", _UrlKt.FRAGMENT_ENCODE_SET, "downloadUrl", "expectedSha256", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getVersion", "()Ljava/lang/String;", "getDownloadUrl", "getExpectedSha256", "component1", "component2", "component3", "copy", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class WheelCandidate {
        private final String downloadUrl;
        private final String expectedSha256;
        private final String version;

        public WheelCandidate(String r1, String r2, String r3) {
            this.version = r1;
            this.downloadUrl = r2;
            this.expectedSha256 = r3;
        }

        public static /* synthetic */ WheelCandidate copy$default(WheelCandidate r0, String r1, String r2, String r3, int r4, Object r5) {
            if ((r4 & 1) == 0) goto L6;
            r1 = r0.version;
        L6:
            if ((r4 & 2) == 0) goto L9;
            r2 = r0.downloadUrl;
        L9:
            if ((r4 & 4) == 0) goto L12;
            r3 = r0.expectedSha256;
        L12:
            return r0.copy(r1, r2, r3);
        }

        public final String component1() {
            return this.version;
        }

        public final String component2() {
            return this.downloadUrl;
        }

        public final String component3() {
            return this.expectedSha256;
        }

        public final WheelCandidate copy(String r1, String r2, String r3) {
            return new WheelCandidate(r1, r2, r3);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof WheelCandidate) == true) goto L8;
            return false;
        L8:
            WheelCandidate r52 = (WheelCandidate) r5;
            if (Intrinsics.areEqual(this.version, r52.version) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.downloadUrl, r52.downloadUrl) == true) goto L15;
            return false;
        L15:
            if (Intrinsics.areEqual(this.expectedSha256, r52.expectedSha256) == true) goto L17;
            return false;
        L17:
            return true;
        }

        public final String getDownloadUrl() {
            return this.downloadUrl;
        }

        public final String getExpectedSha256() {
            return this.expectedSha256;
        }

        public final String getVersion() {
            return this.version;
        }

        public int hashCode() {
            int r0 = ((this.version.hashCode() * 31) + this.downloadUrl.hashCode()) * 31;
            String r2 = this.expectedSha256;
            if (r2 != null) goto L5;
            int r22 = 0;
        L7:
            return r0 + r22;
        L5:
            r22 = r2.hashCode();
            goto L7
        }

        public String toString() {
            return "WheelCandidate(version=" + this.version + ", downloadUrl=" + this.downloadUrl + ", expectedSha256=" + this.expectedSha256 + ")";
        }
    }

    public static String $r8$lambda$0mfbTyemDpEHIa7P75KKYIf2bNM(String r0, File r1) {
        return r1.getName().substring(r0.length());
    }

    public static String $r8$lambda$5c3UmP4e7kXEHH1ee35GxSUgUI8(MatchResult r0) {
        return r0.getValue();
    }

    public static boolean $r8$lambda$87_LTqo6sTR2itQ72BHILUyS0HU(InstallerDelegate r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.isCancelled() != true) goto L8;
        return true;
    L8:
        return false;
    }

    /* renamed from: $r8$lambda$At-2XbthCX74hISQxR9yeGC0zjE, reason: not valid java name */
    public static Object m1573$r8$lambda$At2XbthCX74hISQxR9yeGC0zjE(Function1 r0, Object r1) {
        return r0.invoke(r1);
    }

    public static ConcurrentHashMap $r8$lambda$IvRbWk_j0OoVSagzIQZr5iKkP6Y(String r0) {
        return new ConcurrentHashMap();
    }

    /* renamed from: $r8$lambda$JhKqr6gk7BdDf-6m5Jv_9fE-bq4, reason: not valid java name */
    public static ConcurrentHashMap m1574$r8$lambda$JhKqr6gk7BdDf6m5Jv_9fEbq4(Function1 r0, Object r1) {
        return (ConcurrentHashMap) r0.invoke(r1);
    }

    /* renamed from: $r8$lambda$M-1jcRh-Bc9GolubPg-ab7fZDV0, reason: not valid java name */
    public static CharSequence m1575$r8$lambda$M1jcRhBc9GolubPgab7fZDV0(byte r1) {
        return String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(r1)}, 1));
    }

    public static Object $r8$lambda$My19ZFqHHYc3LTTYQWK8VSr8Yp8(String r0) {
        return new Object();
    }

    public static CharSequence $r8$lambda$OyGnRfS2Ihkf6SpXamhns954ElA(Pair r2) {
        Object r0 = r2.getFirst();
        Object r22 = r2.getSecond();
        StringBuilder r1 = new StringBuilder();
        r1.append(r0);
        r1.append(r22);
        return r1.toString();
    }

    public static int $r8$lambda$VxzsFzBw4QdNHGlEwqCZa6Nfg_Q(String r1, String r2) {
        return VersionComparator.INSTANCE.compare2(r2, r1);
    }

    public static Unit $r8$lambda$ZwvmxzcEiQc_SHP6_WmH7LntvAU(List r10, String r11) {
        if (StringsKt.startsWith$default(r11, "Requires-Dist:", false, 2, null) == false) goto L13;
        String r4 = StringsKt.trim(r11.substring(14)).toString();
        if (StringsKt.contains$default(r4, ";", false, 2, null) == false) goto L11;
        List r112 = StringsKt.split$default(r4, new String[]{";"}, false, 2, 2, null);
        String r0 = StringsKt.trim((String) r112.get(1)).toString();
        if (INSTANCE.isMarkerCompatible(r0) == false) goto L9;
        r10.add(StringsKt.trim((String) r112.get(0)).toString());
        goto L13
    L9:
        return Unit.INSTANCE;
    L11:
        r10.add(r4);
    L13:
        return Unit.INSTANCE;
    }

    public static boolean $r8$lambda$bMMjhw95D3_CwhOi0sSL2GNaxCY(File r3, String r4) {
        return StringsKt.endsWith$default(r4, ".dist-info", false, 2, null);
    }

    public static Set $r8$lambda$bWBVHEv1r6s8zQPAybPu6a75EO8(Function1 r0, Object r1) {
        return (Set) r0.invoke(r1);
    }

    /* renamed from: $r8$lambda$gKjDvklOC_11lmtswn-GXaUHOkM, reason: not valid java name */
    public static Set m1576$r8$lambda$gKjDvklOC_11lmtswnGXaUHOkM(String r0) {
        return Collections.newSetFromMap(new ConcurrentHashMap());
    }

    public static CharSequence $r8$lambda$l28neSHvSXC8INUr7OGeqMEdE3U(Pair r2) {
        Object r0 = r2.getFirst();
        Object r22 = r2.getSecond();
        StringBuilder r1 = new StringBuilder();
        r1.append(r0);
        r1.append(r22);
        return r1.toString();
    }

    public static boolean $r8$lambda$lnghLPpHuq6yVd8pzrPPZYi11Tc(String r3, File r4) {
        if (r4.isDirectory() == true) goto L5;
    L8:
        return false;
    L5:
        if (StringsKt.startsWith$default(r4.getName(), r3, false, 2, null) == false) goto L8;
        return true;
    }

    /* renamed from: $r8$lambda$trhzkYfiRI0w9rVRwyVifG-bzTw, reason: not valid java name */
    public static boolean m1577$r8$lambda$trhzkYfiRI0w9rVRwyVifGbzTw(List r1, String r2) {
        if (r2.length() > 0) goto L5;
        return false;
    L5:
        if (INSTANCE.checkVersionSatisfies(r2, r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    static {
        PipController r0 = new PipController();
        INSTANCE = r0;
        client = ExteraHttpClient.INSTANCE.getClient();
        gson = new Gson();
        registry = new ConcurrentHashMap();
        installLocks = new ConcurrentHashMap();
        wheelArchivePolicy = SecurityPolicy.Companion.builder().maxEntries(50000).maxTotalUncompressedSize(524288000).maxSingleFileSize(MAX_WHEEL_BYTES).maxCompressionRatio(500.0d).build();
        pythonVersion = "3.11.10";
        REGEX_NORMALIZE = new Regex("[-_.]+");
        REGEX_REQ_PARSE = new Regex("^([a-zA-Z0-9_.-]+)(.*)");
        REGEX_REQ_SPECS = new Regex("^(===|==|>=|<=|>|<|~=|!=)\\s*(.*)");
        REGEX_REQ_EXTRA = new Regex("\\[.*?]");
        REGEX_REQ_PAREN = new Regex("[()]");
        REGEX_VERSION_SPLIT = new Regex("([0-9]+)|([a-zA-Z]+)");
        REGEX_MARKER_TOKEN = new Regex("(\"[^\"]*\"|'[^']*'|===|==|!=|>=|<=|~=|>|<|\\(|\\)|\\b(?:and|or|in|not)\\b|[A-Za-z_][A-Za-z0-9_]*|\\S+)");
        REGEX_VERSION_WILDCARD = new Regex("(?i)(?:^|[.\\-_])\\*$");
        r0.loadRegistry();
        Set r02 = SetsKt.setOf(new String[]{"beautifulsoup4", "debugpy", "lxml", "packaging", "pillow", "requests", "pyyaml"});
        ArrayList r1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r02, 10));
        Iterator r03 = r02.iterator();
    L4:
        if (r03.hasNext() == false) goto L6;
        String r2 = (String) r03.next();
        r1.add(INSTANCE.normalizePackageName(r2));
        goto L4
    L6:
        PREINSTALLED_PACKAGES = CollectionsKt.toSet(r1);
    }

    private PipController() {
    }

    public static final /* synthetic */ boolean access$checkVersionSatisfies(PipController r0, String r1, List r2) {
        return r0.checkVersionSatisfies(r1, r2);
    }

    public static final /* synthetic */ Regex access$getREGEX_MARKER_TOKEN$p() {
        return REGEX_MARKER_TOKEN;
    }

    public static final /* synthetic */ ParsedVersion access$parseVersion(PipController r0, String r1) {
        return r0.parseVersion(r1);
    }

    private final String calculateSha256(File r12) {
        MessageDigest r11 = MessageDigest.getInstance("SHA-256");
        FileInputStream r1 = new FileInputStream(r12);
        byte[] r122 = new byte[8192];     // Catch: Throwable -> L7
    L4:
        int r0 = r1.read(r122);     // Catch: Throwable -> L7
        if (r0 == (-1)) goto L9;
        r11.update(r122, 0, r0);     // Catch: Throwable -> L7
        goto L4
    L9:
        Unit r123 = Unit.INSTANCE;     // Catch: Throwable -> L7
        CloseableKt.closeFinally(r1, null);
        return ArraysKt.joinToString$default(r11.digest(), _UrlKt.FRAGMENT_ENCODE_SET, null, null, 0, null, new PipController$$ExternalSyntheticLambda12(), 30, null);
    L7:
        th = move-exception;
        throw th;     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        CloseableKt.closeFinally(r1, th);
        throw th;
    }

    private final boolean checkVersionSatisfies(String r23, List<Pair<String, String>> r24) {
        if (r24.isEmpty() == false) goto L5;
        return true;
    L5:
        Iterator<Pair<String, String>> r2 = r24.iterator();
    L7:
        if (r2.hasNext() == false) goto L81;
        Pair<String, String> r4 = r2.next();
        String r5 = r4.component1();
        String r6 = r4.component2();
        VersionComparator r42 = VersionComparator.INSTANCE;
        int r7 = r42.compare2(r23, r6);
        int r8 = r5.hashCode();
        if (r8 == 60) goto L75;
        if (r8 == 62) goto L70;
        if (r8 == 1084) goto L60;
        if (r8 == 1921) goto L55;
        if (r8 == 1952) goto L47;
        if (r8 == 1983) goto L42;
        if (r8 == 3967) goto L30;
        if (r8 == 60573) goto L26;
    L40:
        boolean r43 = true;
    L79:
        if (r43 == true) goto L7;
        return false;
    L26:
        if (r5.equals("===") == false) goto L40;
        r43 = Intrinsics.areEqual(r23, r6);
        goto L79
    L30:
        if (r5.equals("~=") == false) goto L40;
        if (r7 >= 0) goto L34;
    L33:
        r43 = false;
        goto L79
    L34:
        List r52 = CollectionsKt.toMutableList(StringsKt.split$default(r6, new String[]{"."}, false, 0, 6, null));
        if (r52.size() < 2) goto L40;
        r52.remove(CollectionsKt.getLastIndex(r52));
        int r62 = CollectionsKt.getLastIndex(r52);
        Integer r72 = StringsKt.toIntOrNull((String) r52.get(r62));
        if (r72 == null) goto L40;
        r52.set(r62, String.valueOf(r72.intValue() + 1));
        if (r42.compare2(r23, CollectionsKt.joinToString$default(r52, ".", null, null, 0, null, null, 62, null)) >= 0) goto L33;
    L42:
        if (r5.equals(">=") == false) goto L40;
        if (r7 < 0) goto L33;
    L47:
        if (r5.equals("==") == false) goto L40;
        if (isWildcardVersionSpec(r6) == false) goto L52;
        r43 = matchesVersionWildcard(r23, r6);
        goto L79
    L52:
        if (r7 != 0) goto L33;
    L55:
        if (r5.equals("<=") == false) goto L40;
        if (r7 > 0) goto L33;
    L60:
        if (r5.equals("!=") == false) goto L40;
        if (isWildcardVersionSpec(r6) == true) goto L65;
        if (r7 == 0) goto L33;
    L65:
        if (matchesVersionWildcard(r23, r6) == true) goto L33;
    L70:
        if (r5.equals(">") == false) goto L40;
        if (r7 <= 0) goto L33;
    L75:
        if (r5.equals("<") == false) goto L40;
        if (r7 >= 0) goto L33;
    L81:
        return true;
    }

    private final void cleanupInternal() {
        ArrayList r0 = new ArrayList();
        Iterator<Map.Entry<String, ConcurrentHashMap<String, Set<String>>>> r1 = registry.entrySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L11;
        Map.Entry<String, ConcurrentHashMap<String, Set<String>>> r2 = r1.next();
        String r3 = r2.getKey();
        Iterator<Map.Entry<String, Set<String>>> r22 = r2.getValue().entrySet().iterator();
    L7:
        if (r22.hasNext() == false) goto L4;
        Map.Entry<String, Set<String>> r4 = r22.next();
        String r5 = r4.getKey();
        if (r4.getValue().isEmpty() == false) goto L7;
        r0.add(TuplesKt.to(r3, r5));
        goto L7
    L11:
        int r12 = r0.size();
        int r23 = 0;
    L12:
        if (r23 >= r12) goto L22;
        Object r32 = r0.get(r23);
        r23 = r23 + 1;
        Pair r33 = (Pair) r32;
        String r42 = (String) r33.component1();
        String r34 = (String) r33.component2();
        ConcurrentHashMap<String, ConcurrentHashMap<String, Set<String>>> r52 = registry;
        ConcurrentHashMap<String, Set<String>> r6 = r52.get(r42);
        if (r6 == null) goto L16;
        Set<String> r62 = r6.remove(r34);
    L16:
        ConcurrentHashMap<String, Set<String>> r63 = r52.get(r42);
        if (r63 == null) goto L21;
        if (r63.isEmpty() != true) goto L21;
        r52.remove(r42);
    L21:
        INSTANCE.deletePackage(r42, r34);
        goto L12
    L22:
        removeOrphanedDirectories();
    }

    private final void deletePackage(String r2, String r3) {
        File r1 = getLibPath(r2, r3);
        if (r1.exists() == false) goto L14;
        Result.Companion r22 = Result.Companion;     // Catch: Throwable -> L6
        SimpliFiles.directory(r1).deleteRecursively();     // Catch: Throwable -> L6
        FileLog.d("PipController: Deleted package " + r1.getName());     // Catch: Throwable -> L6
        Object r23 = Result.m2018constructorimpl(Unit.INSTANCE);     // Catch: Throwable -> L6
    L8:
        Throwable r24 = Result.m2021exceptionOrNullimpl(r23);
        if (r24 == null) goto L15;
        FileLog.e("PipController: Failed to delete package " + r1.getName(), r24);
        return;
    L15:
        return;
    L6:
        th = move-exception;
        Result.Companion r32 = Result.Companion;
        r23 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L8
    }

    private final Response executeWithRetry(Request r7, InstallerDelegate r8) {
        int r6 = 0;
        IOException e = null;
    L4:
        if (r6 >= 3) goto L17;
        if (r8 != null) goto L7;
    L30:
        return client.newCall(r7).execute();
    L13:
        e = e;
        r6 = r6 + 1;
        FileLog.w("PipController: Network error, retrying (" + r6 + "/3): " + e.getMessage());
        Thread.sleep(r6 * 1000);     // Catch: InterruptedException -> L27
        goto L4
    L7:
        if (r8.isCancelled() != true) goto L30;
        Model$$ExternalSyntheticBUOutline0.m("Installation cancelled");
        return null;
    L17:
        if (r8 != null) goto L19;
    L23:
        if (e == null) goto L26;
        throw e;
    L26:
        throw new IOException("Unknown network error");
    L19:
        if (r8.isCancelled() != true) goto L23;
        Model$$ExternalSyntheticBUOutline0.m("Installation cancelled");
        return null;
    }

    private final List<String> filterPreReleases(List<String> r4, List<Pair<String, String>> r5) {
        if (r4.isEmpty() == false) goto L5;
        return r4;
    L5:
        if (specsAllowPreRelease(r5) == true) goto L25;
        ArrayList r52 = new ArrayList();
        Iterator<T> r3 = r4.iterator();
    L9:
        if (r3.hasNext() == false) goto L14;
        Object r0 = r3.next();
        PipController r2 = INSTANCE;
        if (r2.isPreReleaseVersion((String) r0) == true) goto L9;
        r52.add(r0);
        goto L9
    L14:
        if (r52.isEmpty() == true) goto L18;
        r4 = r52;
    L18:
        return r4;
    L25:
        return r4;
    }

    private final String findInstalledVersion(String r5, List<Pair<String, String>> r6) {
        ConcurrentHashMap<String, Set<String>> r52 = registry.get(r5);
        if (r52 != null) goto L6;
        return null;
    L6:
        Set<String> r53 = r52.keySet();
        ArrayList r0 = new ArrayList();
        Iterator<T> r54 = r53.iterator();
    L8:
        if (r54.hasNext() == false) goto L13;
        Object r1 = r54.next();
        PipController r3 = INSTANCE;
        if (r3.checkVersionSatisfies((String) r1, r6) == false) goto L8;
        r0.add(r1);
        goto L8
    L13:
        return (String) CollectionsKt.maxWithOrNull(filterPreReleases(r0, r6), VersionComparator.INSTANCE);
    }

    private final File findMetadataFile(String r1, String r2) {
        File[] r0 = getLibPath(r1, r2).listFiles(new PipController$$ExternalSyntheticLambda11());
        if (r0 == null) goto L5;
        File r02 = (File) ArraysKt.firstOrNull(r0);
    L6:
        if (r02 != null) goto L8;
        return null;
    L8:
        return new File(r02, "METADATA");
    L5:
        r02 = null;
        goto L6
    }

    private final String findVersionOnDisk(String r3, List<Pair<String, String>> r4) {
        String r32 = r3 + "-";
        File[] r0 = getLibsDir().listFiles();
        if (r0 == null) goto L16;
        Sequence r02 = ArraysKt.asSequence(r0);
        if (r02 == null) goto L18;
        Sequence r03 = SequencesKt.filter(r02, new PipController$$ExternalSyntheticLambda14(r32));
        if (r03 == null) goto L19;
        Sequence r33 = SequencesKt.map(r03, new PipController$$ExternalSyntheticLambda15(r32));
        if (r33 == null) goto L20;
        Sequence r34 = SequencesKt.filter(r33, new PipController$$ExternalSyntheticLambda16(r4));
        if (r34 == null) goto L21;
        List<String> r35 = SequencesKt.toList(r34);
        if (r35 != null) goto L15;
        return null;
    L15:
        return (String) CollectionsKt.maxWithOrNull(filterPreReleases(r35, r4), VersionComparator.INSTANCE);
    L21:
        return null;
    L20:
        return null;
    L19:
        return null;
    L18:
        return null;
    L16:
        return null;
    }

    private final Boolean getBooleanOrNull(JsonObject r2, String r3) {
        Object r0 = null;
        if (r2.has(r3) == true) goto L5;
        return null;
    L5:
        JsonElement r1 = r2.get(r3);
        if (r1 != null) goto L8;
        return null;
    L8:
        if (r1.isJsonNull() == true) goto L23;
        Result.Companion r22 = Result.Companion;     // Catch: Throwable -> L12
        Object r12 = Result.m2018constructorimpl(Boolean.valueOf(r1.getAsBoolean()));     // Catch: Throwable -> L12
    L15:
        if (Result.m2024isFailureimpl(r12) == true) goto L19;
        r0 = r12;
    L19:
        return (Boolean) r0;
    L12:
        th = move-exception;
        Result.Companion r23 = Result.Companion;
        r12 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L15
    L23:
        return null;
    }

    private final File getLibPath(String r5, String r6) {
        if (StringsKt.contains$default(r6, "/", false, 2, null) == false) goto L5;
    L10:
        ZipFileSystem$$ExternalSyntheticBUOutline0.m("Invalid version: ", r6);
        return null;
    L5:
        if (StringsKt.contains$default(r6, "\\", false, 2, null) == true) goto L10;
        if (StringsKt.contains$default(r6, "..", false, 2, null) == true) goto L10;
        return new File(getLibsDir(), r5 + "-" + r6);
    }

    private final File getLibsDir() {
        return SimpliFiles.directory(new File(PluginsController.Companion.getInstance().getPluginsDir(), "libs")).create().getFile();
    }

    private final File getRegistryFile() {
        return new File(getLibsDir(), "registry.json");
    }

    private final String getStringOrNull(JsonObject r2, String r3) {
        if (r2.has(r3) == true) goto L5;
        return null;
    L5:
        JsonElement r1 = r2.get(r3);
        if (r1 != null) goto L8;
    L12:
        return null;
    L8:
        if (r1.isJsonNull() == true) goto L12;
        return r1.getAsString();
    }

    public static /* synthetic */ List installDependencies$default(PipController r0, List r1, String r2, InstallerDelegate r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r3 = null;
    L6:
        return r0.installDependencies(r1, r2, r3);
    }

    private final String installPackage(String r25, List<Pair<String, String>> r26, InstallerDelegate r27) {
        if (r26.isEmpty() == true) goto L5;
        String r7 = CollectionsKt.joinToString$default(r26, ",", null, null, 0, null, new PipController$$ExternalSyntheticLambda0(), 30, null);
    L6:
        FileLog.d("PipController: Installing " + r25 + " " + r7);
        if (r27 == null) goto L9;
        r27.onProgress("Resolving " + r25 + "...");
    L9:
        Response r8 = executeWithRetry(new Request.Builder().url("https://pypi.org/pypi/" + r25 + "/json").build(), r27);
    L17:
        th = move-exception;
        throw th;     // Catch: Throwable -> L135
    L135:
        th = move-exception;
        CloseableKt.closeFinally(r8, th);
        throw th;
    L12:
        if (r8.isSuccessful() == false) goto L14;
        JsonObject r6 = (JsonObject) gson.fromJson(r8.body().string(), JsonObject.class);     // Catch: Throwable -> L17
        if (r6 == null) goto L133;
        JsonObject r5 = r6.getAsJsonObject("releases");     // Catch: Throwable -> L17
        if (r5 == null) goto L131;
        List r4 = CollectionsKt.toList(r5.keySet());     // Catch: Throwable -> L17
        PipController r10 = INSTANCE;     // Catch: Throwable -> L17
        ArrayList r12 = new ArrayList();     // Catch: Throwable -> L17
        Iterator r42 = r4.iterator();     // Catch: Throwable -> L17
    L27:
        if (r42.hasNext() == false) goto L31;
        Object r13 = r42.next();     // Catch: Throwable -> L17
        if (INSTANCE.checkVersionSatisfies((String) r13, r26) == false) goto L27;
        r12.add(r13);     // Catch: Throwable -> L17
        goto L27
    L31:
        List r43 = CollectionsKt.sortedWith(r10.filterPreReleases(r12, r26), new PipController$$ExternalSyntheticLambda1());     // Catch: Throwable -> L17
        List<Pair<String, String>> r2 = r26;     // Catch: Throwable -> L17
        boolean r132 = false;
        if ((r2 instanceof Collection) == true) goto L34;
    L36:
        Iterator<T> r22 = r2.iterator();     // Catch: Throwable -> L17
    L38:
        if (r22.hasNext() == false) goto L44;
        Pair r102 = (Pair) r22.next();     // Catch: Throwable -> L17
        if (Intrinsics.areEqual(r102.getFirst(), "==") == true) goto L43;
        if (Intrinsics.areEqual(r102.getFirst(), "===") == false) goto L38;
    L43:
        r132 = true;
    L44:
        Iterator r23 = r43.iterator();     // Catch: Throwable -> L17
        WheelCandidate r103 = null;
    L46:
        if (r23.hasNext() == false) goto L58;
        String r14 = (String) r23.next();     // Catch: Throwable -> L17
        JsonArray r15 = r5.getAsJsonArray(r14);     // Catch: Throwable -> L17
        if (r15 == null) goto L46;
        Iterator<JsonElement> r152 = r15.iterator();     // Catch: Throwable -> L17
    L52:
        if (r152.hasNext() == false) goto L57;
        WheelCandidate r44 = INSTANCE.selectWheelCandidate(r14, r152.next().getAsJsonObject(), r132);     // Catch: Throwable -> L17
        if (r44 == null) goto L52;
        r103 = r44;
    L57:
        if (r103 == null) goto L46;
    L58:
        if (r103 != null) goto L69;
        JsonObject r24 = r6.getAsJsonObject("info");     // Catch: Throwable -> L17
        if (r24 == null) goto L62;
        String r28 = INSTANCE.getStringOrNull(r24, "requires_python");     // Catch: Throwable -> L17
    L63:
        if (r28 == null) goto L69;
        PipController r45 = INSTANCE;     // Catch: Throwable -> L17
        if (r45.checkVersionSatisfies(pythonVersion, r45.parseSpecs(r28)) == true) goto L69;
        throw new IOException("Package " + r25 + " requires Python " + r28 + ", but current is " + pythonVersion);     // Catch: Throwable -> L17
    L62:
        r28 = null;
    L69:
        Unit r29 = Unit.INSTANCE;     // Catch: Throwable -> L17
        CloseableKt.closeFinally(r8, null);
        if (r103 == null) goto L128;
        File r210 = getLibPath(r25, r103.getVersion());
        File r46 = SimpliFiles.directory(new File(getLibsDir(), "tmp_" + r25 + "_" + System.currentTimeMillis())).create().getFile();
        File r52 = new File(r46, "extracted");
        if (r27 != null) goto L140;
    L79:
        File r82 = new File(r46, "package.whl");     // Catch: Throwable -> L75 Exception -> L77
        Response r11 = executeWithRetry(new Request.Builder().url(r103.getDownloadUrl()).build(), r27);     // Catch: Throwable -> L75 Exception -> L77
    L108:
        th = move-exception;
        throw th;     // Catch: Throwable -> L115
    L115:
        th = move-exception;
        CloseableKt.closeFinally(r11, th);     // Catch: Throwable -> L75 Exception -> L77
        throw th;     // Catch: Throwable -> L75 Exception -> L77
    L81:
        if (r11.isSuccessful() == false) goto L113;
        long r122 = r11.body().contentLength();     // Catch: Throwable -> L108
        if (r122 > MAX_WHEEL_BYTES) goto L111;
        SimpliFile.writeFrom$default(SimpliFiles.file(r82), new SizeLimitedInputStream(r11.body().byteStream(), MAX_WHEEL_BYTES), 0, 2, null);     // Catch: Throwable -> L108
        CloseableKt.closeFinally(r11, null);     // Catch: Throwable -> L75 Exception -> L77
        if (r103.getExpectedSha256() != null) goto L89;
    L93:
        if (r27 == null) goto L95;
        r27.onProgress("Extracting " + r25 + " " + r103.getVersion() + "...");     // Catch: Throwable -> L75 Exception -> L77
    L95:
        SimpliFiles.archive(r82).withPolicy(wheelArchivePolicy).extractToDirectory(r52, ArchiveExtractionOptions.Companion.builder().cancellationToken(new PipController$$ExternalSyntheticLambda2(r27)).targetPolicy(ExtractionTargetPolicy.REPLACE).build());     // Catch: Throwable -> L75 Exception -> L77
        if (r210.exists() == false) goto L98;
        SimpliFiles.directory(r210).deleteRecursively();     // Catch: Throwable -> L75 Exception -> L77
    L98:
        SimpliFiles.directory(r52).moveTo(r210, OverwritePolicy.REPLACE);     // Catch: Throwable -> L75 Exception -> L77
        Result.Companion r0 = Result.Companion;     // Catch: Throwable -> L101
        Object r02 = Result.m2018constructorimpl(Boolean.valueOf(SimpliFiles.directory(r46).deleteRecursively()));     // Catch: Throwable -> L101
    L103:
        Throwable r03 = Result.m2021exceptionOrNullimpl(r02);
        if (r03 == null) goto L107;
        FileLog.e("PipController: Failed to delete temp package directory", r03);
    L107:
        return r103.getVersion();
    L101:
        th = move-exception;
        Result.Companion r1 = Result.Companion;
        r02 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L103
    L89:
        if (StringsKt.equals(calculateSha256(r82), r103.getExpectedSha256(), true) == true) goto L93;
        throw new IOException("Checksum mismatch");     // Catch: Throwable -> L75 Exception -> L77
    L111:
        throw new IOException("Wheel for " + r25 + " is too large: " + r122 + " bytes (limit 262144000)");     // Catch: Throwable -> L108
    L113:
        throw new IOException("Download failed: " + r11.code());     // Catch: Throwable -> L108
    L140:
        r27.onProgress("Downloading " + r25 + " " + r103.getVersion() + "...");     // Catch: Throwable -> L75 Exception -> L77
    L77:
        e = move-exception;
        FileLog.e("PipController: Failed to download/install " + r25, e);     // Catch: Throwable -> L75
        throw e;     // Catch: Throwable -> L75
    L75:
        th = move-exception;
        Result.Companion r04 = Result.Companion;     // Catch: Throwable -> L122
        Object r05 = Result.m2018constructorimpl(Boolean.valueOf(SimpliFiles.directory(r46).deleteRecursively()));     // Catch: Throwable -> L122
    L124:
        Throwable r06 = Result.m2021exceptionOrNullimpl(r05);
        if (r06 == null) goto L168;
        FileLog.e("PipController: Failed to delete temp package directory", r06);
        throw th;
    L168:
        throw th;
    L122:
        th = move-exception;
        Result.Companion r211 = Result.Companion;
        r05 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L124
    L128:
        Native$$ExternalSyntheticBUOutline1.m("No pure-Python wheel found for ", r25, ". Binary packages are not supported.");
        return null;
    L34:
        if (r2.isEmpty() == false) goto L36;
    L131:
        throw new IOException("No releases found for " + r25 + " on PyPI");     // Catch: Throwable -> L17
    L133:
        throw new IOException("Invalid metadata response from PyPI for " + r25);     // Catch: Throwable -> L17
    L14:
        if (r8.code() != 404) goto L20;
        throw new IOException("Package " + r25 + " not found on PyPI");     // Catch: Throwable -> L17
    L20:
        throw new IOException("Failed to fetch metadata for " + r25 + ": " + r8.code());     // Catch: Throwable -> L17
    L5:
        r7 = "(latest)";
        goto L6
    }

    private final boolean isMarkerCompatible(String r1) {
        return new MarkerParser(r1).parse();
    }

    private final boolean isPreReleaseVersion(String r3) {
        List<String> r2 = parseVersion(r3).getParts();
        if ((r2 instanceof Collection) == true) goto L5;
    L7:
        Iterator<T> r22 = r2.iterator();
    L9:
        if (r22.hasNext() == false) goto L36;
        String r32 = (String) r22.next();
        switch(r32.hashCode()) {
            case 97: goto L32;
            case 98: goto L29;
            case 99: goto L26;
            case 3633: goto L23;
            case 99349: goto L20;
            case 3020272: goto L17;
            case 92909918: goto L14;
            default: goto L9;
        };
    L14:
        if (r32.equals("alpha") == false) goto L9;
        return true;
    L17:
        if (r32.equals("beta") == false) goto L9;
        return true;
    L20:
        if (r32.equals("dev") == false) goto L9;
        return true;
    L23:
        if (r32.equals("rc") == false) goto L9;
        return true;
    L26:
        if (r32.equals("c") == false) goto L9;
        return true;
    L29:
        if (r32.equals("b") == false) goto L9;
        return true;
    L32:
        if (r32.equals("a") == false) goto L9;
        return true;
    L36:
        return false;
    L5:
        if (r2.isEmpty() == false) goto L7;
        return false;
    }

    private final boolean isPurePythonWheelCompatible(String r10) {
        if (StringsKt.endsWith$default(r10, ".whl", false, 2, null) == true) goto L5;
        return false;
    L5:
        List r9 = StringsKt.split$default(StringsKt.removeSuffix(r10, ".whl"), new String[]{"-"}, false, 0, 6, null);
        if (r9.size() >= 5) goto L8;
        return false;
    L8:
        String r3 = (String) r9.get(r9.size() - 3);
        String r102 = (String) r9.get(r9.size() - 2);
        String r92 = (String) r9.get(r9.size() - 1);
        if (Intrinsics.areEqual(r102, "none") == true) goto L11;
    L24:
        return false;
    L11:
        if (Intrinsics.areEqual(r92, "any") == false) goto L24;
        List r93 = StringsKt.split$default(r3, new String[]{"."}, false, 0, 6, null);
        if ((r93 instanceof Collection) == true) goto L16;
    L18:
        Iterator r94 = r93.iterator();
    L20:
        if (r94.hasNext() == false) goto L24;
        if (INSTANCE.isPythonTagCompatible((String) r94.next()) == false) goto L20;
        return true;
    L16:
        if (r93.isEmpty() == false) goto L18;
        return false;
    }

    private final boolean isPythonTagCompatible(String r7) {
        List r6 = StringsKt.split$default(pythonVersion, new String[]{"."}, false, 0, 6, null);
        String r1 = (String) CollectionsKt.getOrNull(r6, 0);
        if (r1 == null) goto L21;
        Integer r12 = StringsKt.toIntOrNull(r1);
        if (r12 == null) goto L21;
        int r13 = r12.intValue();
        String r62 = (String) CollectionsKt.getOrNull(r6, 1);
        if (r62 == null) goto L21;
        Integer r63 = StringsKt.toIntOrNull(r62);
        if (r63 == null) goto L21;
        int r64 = r63.intValue();
        String r72 = r7.toLowerCase(Locale.ROOT);
        if (Intrinsics.areEqual(r72, "py" + r13) == false) goto L14;
        return true;
    L14:
        if (Intrinsics.areEqual(r72, "cp" + r13 + r64) == false) goto L16;
        return true;
    L16:
        Integer r73 = StringsKt.toIntOrNull(StringsKt.removePrefix(r72, "py" + r13));
        if (r73 == null) goto L21;
        if (r73.intValue() > r64) goto L21;
        return true;
    L21:
        return false;
    }

    private final boolean isWildcardVersionSpec(String r1) {
        return REGEX_VERSION_WILDCARD.containsMatchIn(StringsKt.trim(r1).toString());
    }

    private final synchronized void loadRegistry() {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (getRegistryFile().exists() == true) goto L36;
        registry.clear();     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L36:
        Type r0 = new PipController$loadRegistry$type$1().getType();     // Catch: Throwable -> L8 Exception -> L17
        Map r02 = (Map) gson.fromJson(SimpliFile.readText$default(SimpliFiles.file(getRegistryFile()), 4194304, null, 2, null), r0);     // Catch: Throwable -> L8 Exception -> L17
        registry.clear();     // Catch: Throwable -> L8 Exception -> L17
        Iterator r03 = r02.entrySet().iterator();     // Catch: Throwable -> L8 Exception -> L17
    L11:
        if (r03.hasNext() == false) goto L29;
        Map.Entry r1 = (Map.Entry) r03.next();     // Catch: Throwable -> L8 Exception -> L17
        String r2 = (String) r1.getKey();     // Catch: Throwable -> L8 Exception -> L17
        Map r12 = (Map) r1.getValue();     // Catch: Throwable -> L8 Exception -> L17
        String r22 = INSTANCE.normalizePackageName(r2);     // Catch: Throwable -> L8 Exception -> L17
        ConcurrentHashMap<String, Set<String>> r3 = new ConcurrentHashMap();     // Catch: Throwable -> L8 Exception -> L17
        Iterator r13 = r12.entrySet().iterator();     // Catch: Throwable -> L8 Exception -> L17
    L15:
        if (r13.hasNext() == false) goto L19;
        Map.Entry r4 = (Map.Entry) r13.next();     // Catch: Throwable -> L8 Exception -> L17
        String r5 = (String) r4.getKey();     // Catch: Throwable -> L8 Exception -> L17
        Set r42 = (Set) r4.getValue();     // Catch: Throwable -> L8 Exception -> L17
        ConcurrentHashMap.KeySetView r6 = ConcurrentHashMap.newKeySet();     // Catch: Throwable -> L8 Exception -> L17
        r6.addAll(r42);     // Catch: Throwable -> L8 Exception -> L17
        r3.put(r5, r6);     // Catch: Throwable -> L8 Exception -> L17
        goto L15
    L19:
        registry.put(r22, r3);     // Catch: Throwable -> L8 Exception -> L17
    L29:
        monitor-exit(this);
        return;
    L17:
        e = move-exception;
        FileLog.e("PipController: Failed to load registry", e);     // Catch: Throwable -> L8
        registry.clear();     // Catch: Throwable -> L8
        Result.Companion r04 = Result.Companion;     // Catch: Throwable -> L24
        Object r05 = Result.m2018constructorimpl(SimpliFiles.file(getRegistryFile()).moveTo(new File(getLibsDir(), "registry.json.bak"), OverwritePolicy.REPLACE));     // Catch: Throwable -> L24
    L26:
        Throwable r06 = Result.m2021exceptionOrNullimpl(r05);     // Catch: Throwable -> L8
        if (r06 == null) goto L29;
        FileLog.e("PipController: Failed to backup invalid registry", r06);     // Catch: Throwable -> L8
        goto L29
    L24:
        th = move-exception;
        Result.Companion r14 = Result.Companion;     // Catch: Throwable -> L8
        r05 = Result.m2018constructorimpl(ResultKt.createFailure(th));     // Catch: Throwable -> L8
        goto L26
    }

    private final boolean matchesVersionWildcard(String r7, String r8) {
        String r82 = StringsKt.trimEnd(StringsKt.removeSuffix(StringsKt.removeSuffix(StringsKt.removeSuffix(StringsKt.substringBefore$default(StringsKt.trim(r8).toString(), "+", null, 2, null), ".*"), "-*"), "_*"), new char[]{'.', SignatureVisitor.SUPER, '_'});
        ParsedVersion r0 = parseVersion(r82);
        ParsedVersion r3 = parseVersion(r7);
        if (StringsKt.contains$default(r82, "!", false, 2, null) == true) goto L5;
    L7:
        List<Integer> r83 = parseVersionReleaseParts(r82);
        if (r83.isEmpty() == false) goto L10;
        return true;
    L10:
        List<Integer> r6 = parseVersionReleaseParts(r7);
        if (r6.size() >= r83.size()) goto L13;
        return false;
    L13:
        Iterable r72 = CollectionsKt.getIndices(r83);
        if ((r72 instanceof Collection) == true) goto L16;
    L18:
        Iterator r73 = r72.iterator();
    L20:
        if (r73.hasNext() == false) goto L25;
        int r02 = ((IntIterator) r73).nextInt();
        if (r6.get(r02).intValue() == r83.get(r02).intValue()) goto L20;
        return false;
    L25:
        return true;
    L16:
        if (((Collection) r72).isEmpty() == false) goto L18;
        return true;
    L5:
        if (r0.getEpoch() == r3.getEpoch()) goto L7;
        return false;
    }

    private final String normalizePackageName(String r2) {
        String r1 = r2.toLowerCase(Locale.ROOT);
        return REGEX_NORMALIZE.replace(r1, "-");
    }

    private final List<String> parseDependenciesFromMetadata(File r8) {
        ArrayList r7 = new ArrayList();
        SimpliFile.forEachLine$default(SimpliFiles.file(r8), 4194304, null, new PipController$$ExternalSyntheticLambda10(r7), 2, null);     // Catch: Exception -> L5
        return r7;
    L5:
        e = move-exception;
        FileLog.e("PipController: Failed to parse metadata", e);
        return r7;
    }

    private final Pair<String, List<Pair<String, String>>> parseRequirement(String r8) {
        String r82 = StringsKt.trim((String) StringsKt.split$default(r8, new String[]{";"}, false, 0, 6, null).get(0)).toString();
        String r83 = StringsKt.trim(REGEX_REQ_EXTRA.replace(r82, _UrlKt.FRAGMENT_ENCODE_SET)).toString();
        String r84 = StringsKt.trim(REGEX_REQ_PAREN.replace(r83, _UrlKt.FRAGMENT_ENCODE_SET)).toString();
        MatchResult r0 = Regex.find$default(REGEX_REQ_PARSE, r84, 0, 2, null);
        if (r0 == null) goto L5;
        MatchResult.Destructured r85 = r0.getDestructured();
        return TuplesKt.to(r85.getMatch().getGroupValues().get(1), parseSpecs(r85.getMatch().getGroupValues().get(2)));
    L5:
        return TuplesKt.to(r84, CollectionsKt.emptyList());
    }

    private final List<Pair<String, String>> parseSpecs(String r8) {
        ArrayList r7 = new ArrayList();
        if (StringsKt.isBlank(r8) == true) goto L10;
        Iterator r82 = StringsKt.split$default(r8, new String[]{","}, false, 0, 6, null).iterator();
    L6:
        if (r82.hasNext() == false) goto L10;
        String r0 = StringsKt.trim((String) r82.next()).toString();
        MatchResult r02 = Regex.find$default(REGEX_REQ_SPECS, r0, 0, 2, null);
        if (r02 == null) goto L6;
        MatchResult.Destructured r03 = r02.getDestructured();
        r7.add(TuplesKt.to(r03.getMatch().getGroupValues().get(1), StringsKt.trim(r03.getMatch().getGroupValues().get(2)).toString()));
    L10:
        return r7;
    }

    private final ParsedVersion parseVersion(String r9) {
        List r8 = StringsKt.split$default(StringsKt.substringBefore$default(StringsKt.removePrefix(StringsKt.trim(r9).toString().toLowerCase(Locale.ROOT), RegisterSpec.PREFIX), "+", null, 2, null), new String[]{"!"}, false, 2, 2, null);
        if (r8.size() != 2) goto L7;
        Integer r92 = StringsKt.toIntOrNull((String) r8.get(0));
        if (r92 == null) goto L7;
        int r93 = r92.intValue();
    L9:
        if (r8.size() != 2) goto L12;
        Object r82 = r8.get(1);
    L11:
        String r83 = (String) r82;
        return new ParsedVersion(r93, r83, SequencesKt.toList(SequencesKt.map(Regex.findAll$default(REGEX_VERSION_SPLIT, r83, 0, 2, null), new PipController$$ExternalSyntheticLambda13())));
    L12:
        r82 = r8.get(0);
    L7:
        r93 = 0;
        goto L9
    }

    private final List<Integer> parseVersionReleaseParts(String r2) {
        String r1 = parseVersion(r2).getPublicVersion();
        List<String> r12 = new Regex("[.\\-_]").split(r1, 0);
        ArrayList r22 = new ArrayList();
        Iterator<T> r13 = r12.iterator();
    L4:
        if (r13.hasNext() == false) goto L8;
        Integer r0 = StringsKt.toIntOrNull((String) r13.next());
        if (r0 == null) goto L4;
        r22.add(r0);
        goto L4
    L8:
        return r22;
    }

    private final void removeOrphanedDirectories() {
        LinkedHashSet r0 = new LinkedHashSet();
        Iterator<Map.Entry<String, ConcurrentHashMap<String, Set<String>>>> r1 = registry.entrySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L11;
        Map.Entry<String, ConcurrentHashMap<String, Set<String>>> r2 = r1.next();
        String r3 = r2.getKey();
        Iterator<T> r22 = r2.getValue().keySet().iterator();
    L7:
        if (r22.hasNext() == false) goto L4;
        r0.add(INSTANCE.getLibPath(r3, (String) r22.next()).getCanonicalPath());     // Catch: IOException -> L25
        goto L7
    L11:
        File[] r7 = getLibsDir().listFiles();
        if (r7 == null) goto L24;
        int r12 = r7.length;
        int r23 = 0;
    L14:
        if (r23 >= r12) goto L39;
        File r32 = r7[r23];
        if (r32.isDirectory() == false) goto L23;
        if (r0.contains(r32.getCanonicalPath()) == true) goto L23;
        FileLog.d("PipController: Removing orphaned library: " + r32.getName());     // Catch: Exception -> L21
        SimpliFiles.directory(r32).deleteRecursively();     // Catch: Exception -> L21
    L21:
        e = move-exception;
        FileLog.e(e);
    L23:
        r23 = r23 + 1;
        goto L14
    L39:
        return;
    }

    private final void resolveAndInstall(String r22, List<Pair<String, String>> r23, Set<Pair<String, String>> r24, String r25, InstallerDelegate r26) {
        PipController r0 = this;
        Set<Pair<String, String>> r3 = r24;
        String r4 = r25;
        InstallerDelegate r5 = r26;
        if (r5 != null) goto L5;
    L9:
        Object r7 = installLocks.computeIfAbsent(r22, new PipController$$ExternalSyntheticLambda4(new PipController$$ExternalSyntheticLambda3()));
        monitor-enter(r7);
        if (r5 != null) goto L80;
    L19:
        ConcurrentHashMap<String, Set<String>> r8 = registry.get(r22);     // Catch: Throwable -> L17
        Object r9 = null;
        if (r8 == null) goto L31;
        Set<Map.Entry<String, Set<String>>> r82 = r8.entrySet();     // Catch: Throwable -> L17
        if (r82 == null) goto L31;
        Iterator<T> r83 = r82.iterator();     // Catch: Throwable -> L17
    L25:
        if (r83.hasNext() == false) goto L29;
        Object r10 = r83.next();     // Catch: Throwable -> L17
        if (((Collection) ((Map.Entry) r10).getValue()).isEmpty() == true) goto L25;
    L30:
        Map.Entry r102 = (Map.Entry) r10;     // Catch: Throwable -> L17
    L32:
        if (r102 == null) goto L42;
        String r84 = (String) r102.getKey();     // Catch: Throwable -> L17
        String r103 = CollectionsKt.joinToString$default((Iterable) r102.getValue(), ", ", null, null, 0, null, null, 62, null);     // Catch: Throwable -> L17
        if (INSTANCE.checkVersionSatisfies(r84, r23) == false) goto L37;
        FileLog.d("PipController: Reusing active version " + r84 + " of " + r22 + " (used by " + r103 + ") for " + r4);     // Catch: Throwable -> L17
    L50:
        monitor-exit(r7);
        Iterator<T> r2 = r3.iterator();
    L53:
        if (r2.hasNext() == false) goto L57;
        Object r72 = r2.next();
        if (Intrinsics.areEqual(((Pair) r72).getFirst(), r22) == false) goto L53;
        r9 = r72;
    L57:
        Pair r92 = (Pair) r9;
        if (r92 == null) goto L62;
        String r27 = (String) r92.getSecond();
        if (VersionComparator.INSTANCE.compare2(r84, r27) <= 0) goto L77;
        FileLog.d("PipController: Upgrading " + r22 + " from " + r27 + " to " + r84 + " for " + r4);
        r3.remove(r92);
        goto L62
    L77:
        return;
    L62:
        registry.computeIfAbsent(r22, new PipController$$ExternalSyntheticLambda7(new PipController$$ExternalSyntheticLambda6())).computeIfAbsent(r84, new PipController$$ExternalSyntheticLambda9(new PipController$$ExternalSyntheticLambda8())).add(r4);
        r3.add(TuplesKt.to(r22, r84));
        File r1 = r0.findMetadataFile(r22, r84);
        if (r1 != null) goto L65;
        return;
    L65:
        if (r1.exists() == false) goto L92;
        Iterator<String> r73 = r0.parseDependenciesFromMetadata(r1).iterator();
    L68:
        if (r73.hasNext() == false) goto L93;
        String r12 = r73.next();
        if (r5 == null) goto L76;
        if (r5.isCancelled() != true) goto L76;
        Model$$ExternalSyntheticBUOutline0.m("Installation cancelled");
        return;
    L76:
        Pair<String, List<Pair<String, String>>> r13 = r0.parseRequirement(r12);
        String r28 = r13.component1();
        r0.resolveAndInstall(r0.normalizePackageName(r28), r13.component2(), r3, r4, r5);
        r0 = this;
        r3 = r24;
        r4 = r25;
        r5 = r26;
        goto L68
    L93:
        return;
    L92:
        return;
    L37:
        if (r23.isEmpty() == true) goto L39;
        String r02 = CollectionsKt.joinToString$default(r23, ",", null, null, 0, null, new PipController$$ExternalSyntheticLambda5(), 30, null);     // Catch: Throwable -> L17
    L41:
        throw new IOException("Dependency conflict for '" + r22 + "': Active version " + r84 + " (used by " + r103 + ") does not satisfy requirement '" + r02 + "'.");     // Catch: Throwable -> L17
    L39:
        r02 = "(latest)";
        goto L41
    L42:
        PipController r85 = INSTANCE;     // Catch: Throwable -> L17
        String r104 = r85.findInstalledVersion(r22, r23);     // Catch: Throwable -> L17
        if (r104 != null) goto L47;
        r104 = r85.findVersionOnDisk(r22, r23);     // Catch: Throwable -> L17
        if (r104 == null) goto L49;
        FileLog.d("PipController: Found " + r22 + " " + r104 + " on disk, adopting.");     // Catch: Throwable -> L17
        goto L47
    L49:
        r84 = r85.installPackage(r22, r23, r5);     // Catch: Throwable -> L17
    L47:
        r84 = r104;
        goto L50
    L29:
        r10 = null;
    L31:
        r102 = null;
        goto L32
    L80:
    L17:
        th = move-exception;
        throw th;
    L13:
        if (r5.isCancelled() != true) goto L19;
        throw new IOException("Installation cancelled");     // Catch: Throwable -> L17
    L5:
        if (r5.isCancelled() != true) goto L9;
        Model$$ExternalSyntheticBUOutline0.m("Installation cancelled");
    }

    private final synchronized void restoreRegistry(Map<String, ? extends Map<String, ? extends Set<String>>> r7) {
        monitor-enter(this);
        registry.clear();     // Catch: Throwable -> L10
        Iterator<Map.Entry<String, ? extends Map<String, ? extends Set<String>>>> r72 = r7.entrySet().iterator();     // Catch: Throwable -> L10
    L4:
        if (r72.hasNext() == false) goto L14;
        Map.Entry<String, ? extends Map<String, ? extends Set<String>>> r0 = r72.next();     // Catch: Throwable -> L10
        String r1 = r0.getKey();     // Catch: Throwable -> L10
        Map<String, ? extends Set<String>> r02 = r0.getValue();     // Catch: Throwable -> L10
        ConcurrentHashMap<String, Set<String>> r2 = new ConcurrentHashMap();     // Catch: Throwable -> L10
        Iterator<Map.Entry<String, ? extends Set<String>>> r03 = r02.entrySet().iterator();     // Catch: Throwable -> L10
    L8:
        if (r03.hasNext() == false) goto L12;
        Map.Entry<String, ? extends Set<String>> r3 = r03.next();     // Catch: Throwable -> L10
        String r4 = r3.getKey();     // Catch: Throwable -> L10
        Set<String> r32 = r3.getValue();     // Catch: Throwable -> L10
        ConcurrentHashMap.KeySetView r5 = ConcurrentHashMap.newKeySet();     // Catch: Throwable -> L10
        r5.addAll(r32);     // Catch: Throwable -> L10
        r2.put(r4, r5);     // Catch: Throwable -> L10
        goto L8
    L12:
        registry.put(r1, r2);     // Catch: Throwable -> L10
        goto L4
    L14:
        monitor-exit(this);
        return;
    L10:
        th = move-exception;
        throw th;
    }

    private final synchronized void saveRegistry() {
        monitor-enter(this);
        SimpliFile.writeTextAtomic$default(SimpliFiles.file(getRegistryFile()), gson.toJson(snapshotRegistry()), null, 2, null);     // Catch: Throwable -> L5 Exception -> L7
    L9:
        monitor-exit(this);
        return;
    L7:
        e = move-exception;
        FileLog.e("PipController: Failed to save registry", e);     // Catch: Throwable -> L5
    L5:
        th = move-exception;
        throw th;
    }

    private final WheelCandidate selectWheelCandidate(String r4, JsonObject r5, boolean r6) {
        String r0 = getStringOrNull(r5, "packagetype");
        String r1 = null;
        if (r0 != null) goto L6;
        return null;
    L6:
        if (Intrinsics.areEqual(r0, "bdist_wheel") == true) goto L8;
        return null;
    L8:
        if (r6 == false) goto L10;
    L12:
        String r62 = getStringOrNull(r5, "filename");
        if (r62 != null) goto L16;
        return null;
    L16:
        if (isPurePythonWheelCompatible(r62) == true) goto L18;
        return null;
    L18:
        String r63 = getStringOrNull(r5, "requires_python");
        if (r63 != null) goto L21;
    L23:
        String r64 = getStringOrNull(r5, "url");
        if (r64 != null) goto L26;
        return null;
    L26:
        JsonObject r52 = r5.getAsJsonObject("digests");
        if (r52 == null) goto L30;
        r1 = getStringOrNull(r52, "sha256");
    L30:
        return new WheelCandidate(r4, r64, r1);
    L21:
        if (checkVersionSatisfies(pythonVersion, parseSpecs(r63)) == true) goto L23;
        return null;
    L10:
        if (Intrinsics.areEqual(getBooleanOrNull(r5, "yanked"), Boolean.TRUE) == false) goto L12;
        return null;
    }

    private final synchronized Map<String, Map<String, Set<String>>> snapshotRegistry() {
        monitor-enter(this);
        ConcurrentHashMap<String, ConcurrentHashMap<String, Set<String>>> r0 = registry;     // Catch: Throwable -> L10
        LinkedHashMap r1 = new LinkedHashMap(MapsKt.mapCapacity(r0.size()));     // Catch: Throwable -> L10
        Iterator<T> r02 = r0.entrySet().iterator();     // Catch: Throwable -> L10
    L4:
        if (r02.hasNext() == false) goto L14;
        Object r2 = r02.next();     // Catch: Throwable -> L10
        Object r3 = ((Map.Entry) r2).getKey();     // Catch: Throwable -> L10
        ConcurrentHashMap r22 = (ConcurrentHashMap) ((Map.Entry) r2).getValue();     // Catch: Throwable -> L10
        LinkedHashMap r4 = new LinkedHashMap(MapsKt.mapCapacity(r22.size()));     // Catch: Throwable -> L10
        Iterator r23 = r22.entrySet().iterator();     // Catch: Throwable -> L10
    L8:
        if (r23.hasNext() == false) goto L12;
        Object r5 = r23.next();     // Catch: Throwable -> L10
        r4.put(((Map.Entry) r5).getKey(), CollectionsKt.toSet((Set) ((Map.Entry) r5).getValue()));     // Catch: Throwable -> L10
        goto L8
    L12:
        r1.put(r3, r4);     // Catch: Throwable -> L10
        goto L4
    L14:
        monitor-exit(this);
        return r1;
    L10:
        th = move-exception;
        throw th;
    }

    private final boolean specsAllowPreRelease(List<Pair<String, String>> r3) {
        List<Pair<String, String>> r32 = r3;
        if ((r32 instanceof Collection) == true) goto L5;
    L7:
        Iterator<T> r2 = r32.iterator();
    L9:
        if (r2.hasNext() == false) goto L14;
        String r33 = (String) ((Pair) r2.next()).component2();
        if (INSTANCE.isPreReleaseVersion(r33) == false) goto L9;
        return true;
    L14:
        return false;
    L5:
        if (r32.isEmpty() == false) goto L7;
        return false;
    }

    private final void updateRegistryForPlugin(String r7, Set<Pair<String, String>> r8) {
        ArrayList r6 = new ArrayList();
        Iterator<Map.Entry<String, ConcurrentHashMap<String, Set<String>>>> r0 = registry.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L15;
        Map.Entry<String, ConcurrentHashMap<String, Set<String>>> r1 = r0.next();
        String r2 = r1.getKey();
        Iterator<Map.Entry<String, Set<String>>> r12 = r1.getValue().entrySet().iterator();
    L7:
        if (r12.hasNext() == false) goto L4;
        Map.Entry<String, Set<String>> r3 = r12.next();
        String r4 = r3.getKey();
        Set<String> r32 = r3.getValue();
        Pair r42 = TuplesKt.to(r2, r4);
        if (r32.contains(r7) == false) goto L7;
        if (r8.contains(r42) == true) goto L7;
        r32.remove(r7);
        if (r32.isEmpty() == false) goto L7;
        r6.add(r42);
        goto L7
    L15:
        int r72 = r6.size();
        int r82 = 0;
    L16:
        if (r82 >= r72) goto L26;
        Object r02 = r6.get(r82);
        r82 = r82 + 1;
        Pair r03 = (Pair) r02;
        String r13 = (String) r03.component1();
        String r04 = (String) r03.component2();
        ConcurrentHashMap<String, ConcurrentHashMap<String, Set<String>>> r22 = registry;
        ConcurrentHashMap<String, Set<String>> r33 = r22.get(r13);
        if (r33 == null) goto L20;
        Set<String> r34 = r33.remove(r04);
    L20:
        ConcurrentHashMap<String, Set<String>> r35 = r22.get(r13);
        if (r35 == null) goto L25;
        if (r35.isEmpty() != true) goto L25;
        r22.remove(r13);
    L25:
        INSTANCE.deletePackage(r13, r04);
        goto L16
    }

    public final Set<String> activeLibraryPaths() {
        LinkedHashSet r7 = new LinkedHashSet();
        Iterator<Map.Entry<String, ConcurrentHashMap<String, Set<String>>>> r0 = registry.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L14;
        Map.Entry<String, ConcurrentHashMap<String, Set<String>>> r1 = r0.next();
        String r2 = r1.getKey();
        Iterator<Map.Entry<String, Set<String>>> r12 = r1.getValue().entrySet().iterator();
    L7:
        if (r12.hasNext() == false) goto L4;
        Map.Entry<String, Set<String>> r3 = r12.next();
        String r4 = r3.getKey();
        if (r3.getValue().isEmpty() == true) goto L7;
        r7.add(INSTANCE.getLibPath(r2, r4).getAbsolutePath());     // Catch: IOException -> L12
    L12:
        e = move-exception;
        FileLog.e("PipController: Failed to resolve path for " + r2 + " " + r4, e);
        goto L7
    L14:
        return r7;
    }

    public final void cleanup() {
        cleanupInternal();
    }

    public final String getPythonVersion() {
        return pythonVersion;
    }

    public final List<String> installDependencies(List<String> r10, String r11, InstallerDelegate r12) {
        ArrayList r0 = new ArrayList();
        LinkedHashSet r4 = new LinkedHashSet();
        Map<String, Map<String, Set<String>>> r7 = snapshotRegistry();
        Iterator<String> r8 = r10.iterator();     // Catch: Exception -> L24 Throwable -> L35
    L4:
        if (r8.hasNext() == false) goto L26;
        String r1 = r8.next();     // Catch: Exception -> L24 Throwable -> L35
        if (r12 == null) goto L16;
    L13:
        e = move-exception;
        Exception r122 = e;
        PipController r13 = this;
        String r5 = r11;
    L33:
        FileLog.e("PipController: Failed to install dependencies for " + r5 + ": " + r10, r122);     // Catch: Throwable -> L35
        r13.restoreRegistry(r7);     // Catch: Throwable -> L35
        r13.removeOrphanedDirectories();     // Catch: Throwable -> L35
        throw r122;     // Catch: Throwable -> L35
    L9:
        if (r12.isCancelled() != true) goto L16;
        throw new IOException("Installation cancelled");     // Catch: Exception -> L13 Throwable -> L35
    L16:
        if (StringsKt.isBlank(r1) == true) goto L4;
        Pair<String, List<Pair<String, String>>> r14 = this.parseRequirement(r1);     // Catch: Exception -> L24 Throwable -> L35
        String r2 = r14.component1();     // Catch: Exception -> L24 Throwable -> L35
        List<Pair<String, String>> r3 = r14.component2();     // Catch: Exception -> L24 Throwable -> L35
        String r22 = this.normalizePackageName(r2);     // Catch: Exception -> L24 Throwable -> L35
        if (PREINSTALLED_PACKAGES.contains(r22) == true) goto L4;
        r13 = this;
        r5 = r11;
        InstallerDelegate r6 = r12;
        r13.resolveAndInstall(r22, r3, r4, r5, r6);     // Catch: Exception -> L22 Throwable -> L35
        PipController this = r13;
        r11 = r5;
        r12 = r6;
    L22:
        e = e;
    L23:
        r122 = e;
        goto L33
    L26:
        r13 = this;
        r5 = r11;
        r13.updateRegistryForPlugin(r5, r4);     // Catch: Exception -> L22 Throwable -> L35
        Iterator<Pair<String, String>> r9 = r4.iterator();     // Catch: Exception -> L22 Throwable -> L35
    L28:
        if (r9.hasNext() == false) goto L30;
        Pair<String, String> r112 = r9.next();     // Catch: Exception -> L22 Throwable -> L35
        r0.add(r13.getLibPath(r112.component1(), r112.component2()).getAbsolutePath());     // Catch: Exception -> L22 Throwable -> L35
        goto L28
    L30:
        r13.saveRegistry();     // Catch: Exception -> L22 Throwable -> L35
        installLocks.clear();
        return r0;
    L35:
        th = move-exception;
        installLocks.clear();
        throw th;
    L24:
        e = e;
        r13 = this;
        r5 = r11;
        goto L23
    }

    public final void setPythonVersion(String r1) {
        pythonVersion = r1;
    }

    public final void uninstallDependencies(String r11) {
        ArrayList r0 = new ArrayList();
        Iterator<Map.Entry<String, ConcurrentHashMap<String, Set<String>>>> r1 = registry.entrySet().iterator();
        int r2 = 0;
        boolean r3 = false;
    L4:
        if (r1.hasNext() == false) goto L14;
        Map.Entry<String, ConcurrentHashMap<String, Set<String>>> r4 = r1.next();
        String r6 = r4.getKey();
        Iterator<Map.Entry<String, Set<String>>> r42 = r4.getValue().entrySet().iterator();
    L7:
        if (r42.hasNext() == false) goto L4;
        Map.Entry<String, Set<String>> r7 = r42.next();
        String r8 = r7.getKey();
        Set<String> r72 = r7.getValue();
        if (r72.remove(r11) == false) goto L7;
        if (r72.isEmpty() == false) goto L13;
        r0.add(TuplesKt.to(r6, r8));
    L13:
        r3 = true;
        goto L7
    L14:
        int r112 = r0.size();
    L15:
        if (r2 >= r112) goto L25;
        Object r12 = r0.get(r2);
        r2 = r2 + 1;
        Pair r13 = (Pair) r12;
        String r43 = (String) r13.component1();
        String r14 = (String) r13.component2();
        ConcurrentHashMap<String, ConcurrentHashMap<String, Set<String>>> r62 = registry;
        ConcurrentHashMap<String, Set<String>> r73 = r62.get(r43);
        if (r73 == null) goto L19;
        Set<String> r74 = r73.remove(r14);
    L19:
        ConcurrentHashMap<String, Set<String>> r75 = r62.get(r43);
        if (r75 == null) goto L24;
        if (r75.isEmpty() != true) goto L24;
        r62.remove(r43);
    L24:
        INSTANCE.deletePackage(r43, r14);
        goto L15
    L25:
        if (r3 == false) goto L38;
        saveRegistry();
        return;
    }
}
