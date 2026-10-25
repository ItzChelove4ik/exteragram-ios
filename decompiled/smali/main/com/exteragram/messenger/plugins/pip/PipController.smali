.class public final Lcom/exteragram/messenger/plugins/pip/PipController;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;,
        Lcom/exteragram/messenger/plugins/pip/PipController$MarkerParser;,
        Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;,
        Lcom/exteragram/messenger/plugins/pip/PipController$SizeLimitedInputStream;,
        Lcom/exteragram/messenger/plugins/pip/PipController$VersionComparator;,
        Lcom/exteragram/messenger/plugins/pip/PipController$WheelCandidate;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u00ae\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0010\t\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0002\u0008\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\"\n\u0002\u0008\u0006\n\u0002\u0010 \n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\t\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u0008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0016\n\u0002\u0010\u0008\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0007\u0008\u00c6\u0002\u0018\u00002\u00020\u0001:\u0006wxyz{|B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0010\u0010,\u001a\u00020\u00102\u0006\u0010-\u001a\u00020\u0010H\u0002J\u0008\u0010.\u001a\u00020/H\u0002J&\u00100\u001a \u0012\u0004\u0012\u00020\u0010\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020\u0010020101H\u0002J.\u00103\u001a\u00020/2$\u00104\u001a \u0012\u0004\u0012\u00020\u0010\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020\u0010020101H\u0002J\u0008\u00105\u001a\u00020/H\u0002J\u0006\u00106\u001a\u00020/J.\u00108\u001a\u0008\u0012\u0004\u0012\u00020\u0010092\u000c\u0010:\u001a\u0008\u0012\u0004\u0012\u00020\u0010092\u0006\u0010;\u001a\u00020\u00102\n\u0008\u0002\u0010<\u001a\u0004\u0018\u00010=JV\u0010>\u001a\u00020/2\u0006\u0010?\u001a\u00020\u00102\u0018\u0010@\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A092\u0018\u0010B\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A0\u00112\u0006\u0010;\u001a\u00020\u00102\u0008\u0010<\u001a\u0004\u0018\u00010=H\u0002J*\u0010C\u001a\u00020/2\u0006\u0010;\u001a\u00020\u00102\u0018\u0010D\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A02H\u0002J\u0008\u0010E\u001a\u00020/H\u0002J\u0008\u0010F\u001a\u00020/H\u0002J\u000c\u0010G\u001a\u0008\u0012\u0004\u0012\u00020\u001002J\u000e\u0010H\u001a\u00020/2\u0006\u0010;\u001a\u00020\u0010J4\u0010I\u001a\u00020\u00102\u0006\u0010?\u001a\u00020\u00102\u0018\u0010@\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A092\u0008\u0010<\u001a\u0004\u0018\u00010=H\u0002J\"\u0010J\u001a\u0004\u0018\u00010K2\u0006\u0010L\u001a\u00020\u00102\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020PH\u0002J\u0016\u0010Q\u001a\u0004\u0018\u00010\u0010*\u00020N2\u0006\u0010-\u001a\u00020\u0010H\u0002J\u001b\u0010R\u001a\u0004\u0018\u00010P*\u00020N2\u0006\u0010-\u001a\u00020\u0010H\u0002\u00a2\u0006\u0002\u0010SJ\u0010\u0010T\u001a\u00020P2\u0006\u0010U\u001a\u00020\u0010H\u0002J\u0010\u0010V\u001a\u00020P2\u0006\u0010W\u001a\u00020\u0010H\u0002J\u001a\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020[2\u0008\u0010<\u001a\u0004\u0018\u00010=H\u0002J,\u0010\\\u001a\u0004\u0018\u00010\u00102\u0006\u0010?\u001a\u00020\u00102\u0018\u0010@\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A09H\u0002J,\u0010]\u001a\u0004\u0018\u00010\u00102\u0006\u0010?\u001a\u00020\u00102\u0018\u0010@\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A09H\u0002J\u0018\u0010^\u001a\u00020\t2\u0006\u0010?\u001a\u00020\u00102\u0006\u0010L\u001a\u00020\u0010H\u0002J\u0018\u0010_\u001a\u00020/2\u0006\u0010?\u001a\u00020\u00102\u0006\u0010L\u001a\u00020\u0010H\u0002J.\u0010`\u001a \u0012\u0004\u0012\u00020\u0010\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A090A2\u0006\u0010a\u001a\u00020\u0010H\u0002J\"\u0010b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A092\u0006\u0010c\u001a\u00020\u0010H\u0002J\u0016\u0010d\u001a\u0008\u0012\u0004\u0012\u00020\u0010092\u0006\u0010e\u001a\u00020\tH\u0002J\u0010\u0010f\u001a\u00020P2\u0006\u0010g\u001a\u00020\u0010H\u0002J\u001a\u0010h\u001a\u0004\u0018\u00010\t2\u0006\u0010?\u001a\u00020\u00102\u0006\u0010L\u001a\u00020\u0010H\u0002J*\u0010i\u001a\u00020P2\u0006\u0010L\u001a\u00020\u00102\u0018\u0010@\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A09H\u0002J\u0010\u0010j\u001a\u00020P2\u0006\u0010k\u001a\u00020\u0010H\u0002J6\u0010l\u001a\u0008\u0012\u0004\u0012\u00020\u0010092\u000c\u0010m\u001a\u0008\u0012\u0004\u0012\u00020\u0010092\u0018\u0010@\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A09H\u0002J\"\u0010n\u001a\u00020P2\u0018\u0010@\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100A09H\u0002J\u0010\u0010o\u001a\u00020P2\u0006\u0010L\u001a\u00020\u0010H\u0002J\u0018\u0010p\u001a\u00020P2\u0006\u0010L\u001a\u00020\u00102\u0006\u0010k\u001a\u00020\u0010H\u0002J\u0016\u0010q\u001a\u0008\u0012\u0004\u0012\u00020r092\u0006\u0010L\u001a\u00020\u0010H\u0002J\u0010\u0010s\u001a\u00020\u00102\u0006\u0010t\u001a\u00020\tH\u0002J\u0010\u0010u\u001a\u00020v2\u0006\u0010L\u001a\u00020\u0010H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0008\u001a\u00020\t8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\u0008\n\u0010\u000bR\u0014\u0010\u000c\u001a\u00020\t8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\u0008\r\u0010\u000bR4\u0010\u000e\u001a(\u0012\u0004\u0012\u00020\u0010\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020\u00100\u00110\u000fj\u0002`\u00120\u000fj\u0002`\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u0018\u0010\u0019\"\u0004\u0008\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001dX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001dX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0010X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0010X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0010X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\'\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020$X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u00107\u001a\u0008\u0012\u0004\u0012\u00020\u001002X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006}"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/pip/PipController;",
        "",
        "<init>",
        "()V",
        "client",
        "Lokhttp3/OkHttpClient;",
        "gson",
        "Lcom/google/gson/Gson;",
        "libsDir",
        "Ljava/io/File;",
        "getLibsDir",
        "()Ljava/io/File;",
        "registryFile",
        "getRegistryFile",
        "registry",
        "Ljava/util/concurrent/ConcurrentHashMap;",
        "",
        "",
        "Lcom/exteragram/messenger/plugins/pip/VersionMap;",
        "Lcom/exteragram/messenger/plugins/pip/RegistryMap;",
        "installLocks",
        "wheelArchivePolicy",
        "Lorg/simplifiles/archive/security/SecurityPolicy;",
        "pythonVersion",
        "getPythonVersion",
        "()Ljava/lang/String;",
        "setPythonVersion",
        "(Ljava/lang/String;)V",
        "MAX_REGISTRY_BYTES",
        "",
        "MAX_METADATA_BYTES",
        "MAX_WHEEL_BYTES",
        "ENV_SYS_PLATFORM",
        "ENV_PLATFORM_SYSTEM",
        "ENV_OS_NAME",
        "REGEX_NORMALIZE",
        "Lkotlin/text/Regex;",
        "REGEX_REQ_PARSE",
        "REGEX_REQ_SPECS",
        "REGEX_REQ_EXTRA",
        "REGEX_REQ_PAREN",
        "REGEX_VERSION_SPLIT",
        "REGEX_MARKER_TOKEN",
        "REGEX_VERSION_WILDCARD",
        "normalizePackageName",
        "name",
        "loadRegistry",
        "",
        "snapshotRegistry",
        "",
        "",
        "restoreRegistry",
        "snapshot",
        "saveRegistry",
        "cleanup",
        "PREINSTALLED_PACKAGES",
        "installDependencies",
        "",
        "requirements",
        "pluginId",
        "delegate",
        "Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;",
        "resolveAndInstall",
        "pkg",
        "specs",
        "Lkotlin/Pair;",
        "installedAccumulator",
        "updateRegistryForPlugin",
        "currentlyNeeded",
        "cleanupInternal",
        "removeOrphanedDirectories",
        "activeLibraryPaths",
        "uninstallDependencies",
        "installPackage",
        "selectWheelCandidate",
        "Lcom/exteragram/messenger/plugins/pip/PipController$WheelCandidate;",
        "version",
        "artifact",
        "Lcom/google/gson/JsonObject;",
        "allowYanked",
        "",
        "getStringOrNull",
        "getBooleanOrNull",
        "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/Boolean;",
        "isPurePythonWheelCompatible",
        "filename",
        "isPythonTagCompatible",
        "tag",
        "executeWithRetry",
        "Lokhttp3/Response;",
        "request",
        "Lokhttp3/Request;",
        "findVersionOnDisk",
        "findInstalledVersion",
        "getLibPath",
        "deletePackage",
        "parseRequirement",
        "req",
        "parseSpecs",
        "specsString",
        "parseDependenciesFromMetadata",
        "metadataFile",
        "isMarkerCompatible",
        "marker",
        "findMetadataFile",
        "checkVersionSatisfies",
        "isWildcardVersionSpec",
        "spec",
        "filterPreReleases",
        "versions",
        "specsAllowPreRelease",
        "isPreReleaseVersion",
        "matchesVersionWildcard",
        "parseVersionReleaseParts",
        "",
        "calculateSha256",
        "file",
        "parseVersion",
        "Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;",
        "InstallerDelegate",
        "WheelCandidate",
        "ParsedVersion",
        "MarkerParser",
        "SizeLimitedInputStream",
        "VersionComparator",
        "TMessagesProj"
    }
    k = 0x1
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation

.annotation build Lkotlin/jvm/internal/SourceDebugExtension;
    value = {
        "SMAP\nPipController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PipController.kt\ncom/exteragram/messenger/plugins/pip/PipController\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 6 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,1115:1\n221#2:1116\n221#2,2:1117\n222#2:1119\n221#2:1131\n221#2,2:1132\n222#2:1134\n221#2:1138\n221#2,2:1139\n222#2:1141\n221#2:1144\n221#2,2:1145\n222#2:1147\n221#2:1150\n222#2:1153\n221#2:1156\n221#2,2:1157\n222#2:1159\n221#2:1160\n221#2,2:1161\n222#2:1163\n493#3:1120\n442#3:1121\n493#3:1124\n442#3:1125\n1266#4,2:1122\n1266#4,4:1126\n1269#4:1130\n296#4,2:1135\n1915#4,2:1142\n1915#4,2:1148\n1915#4,2:1151\n1915#4,2:1164\n777#4:1166\n873#4,2:1167\n1807#4,3:1169\n1807#4,3:1172\n777#4:1175\n873#4,2:1176\n832#4:1178\n862#4,2:1179\n1807#4,3:1181\n1807#4,3:1184\n1786#4,3:1187\n1642#4,10:1190\n1915#4:1200\n1916#4:1202\n1652#4:1203\n1586#4:1204\n1661#4,3:1205\n1#5:1137\n1#5:1201\n14048#6,2:1154\n*S KotlinDebug\n*F\n+ 1 PipController.kt\ncom/exteragram/messenger/plugins/pip/PipController\n*L\n118#1:1116\n121#1:1117,2\n118#1:1119\n152#1:1131\n154#1:1132,2\n152#1:1134\n304#1:1138\n305#1:1139,2\n304#1:1141\n328#1:1144\n329#1:1145,2\n328#1:1147\n349#1:1150\n349#1:1153\n374#1:1156\n375#1:1157,2\n374#1:1159\n392#1:1160\n393#1:1161,2\n392#1:1163\n144#1:1120\n144#1:1121\n145#1:1124\n145#1:1125\n144#1:1122,2\n145#1:1126,4\n144#1:1130\n236#1:1135,2\n316#1:1142,2\n336#1:1148,2\n350#1:1151,2\n403#1:1164,2\n447#1:1166\n447#1:1167,2\n451#1:1169,3\n591#1:1172,3\n645#1:1175\n645#1:1176,2\n960#1:1178\n960#1:1179,2\n965#1:1181,3\n969#1:1184,3\n998#1:1187,3\n1004#1:1190,10\n1004#1:1200\n1004#1:1202\n1004#1:1203\n178#1:1204\n178#1:1205,3\n1004#1:1201\n358#1:1154,2\n*E\n"
    }
.end annotation


# static fields
.field private static final ENV_OS_NAME:Ljava/lang/String; = "posix"

.field private static final ENV_PLATFORM_SYSTEM:Ljava/lang/String; = "Linux"

.field private static final ENV_SYS_PLATFORM:Ljava/lang/String; = "linux"

.field public static final INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

.field private static final MAX_METADATA_BYTES:J = 0x400000L

.field private static final MAX_REGISTRY_BYTES:J = 0x400000L

.field private static final MAX_WHEEL_BYTES:J = 0xfa00000L

.field private static final PREINSTALLED_PACKAGES:Ljava/util/Set;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private static final REGEX_MARKER_TOKEN:Lkotlin/text/Regex;

.field private static final REGEX_NORMALIZE:Lkotlin/text/Regex;

.field private static final REGEX_REQ_EXTRA:Lkotlin/text/Regex;

.field private static final REGEX_REQ_PAREN:Lkotlin/text/Regex;

.field private static final REGEX_REQ_PARSE:Lkotlin/text/Regex;

.field private static final REGEX_REQ_SPECS:Lkotlin/text/Regex;

.field private static final REGEX_VERSION_SPLIT:Lkotlin/text/Regex;

.field private static final REGEX_VERSION_WILDCARD:Lkotlin/text/Regex;

.field private static final client:Lokhttp3/OkHttpClient;

.field private static final gson:Lcom/google/gson/Gson;

.field private static final installLocks:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation
.end field

.field private static pythonVersion:Ljava/lang/String;

.field private static final registry:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;>;>;"
        }
    .end annotation
.end field

.field private static final wheelArchivePolicy:Lorg/simplifiles/archive/security/SecurityPolicy;


# direct methods
.method public static $r8$lambda$0mfbTyemDpEHIa7P75KKYIf2bNM(Ljava/lang/String;Ljava/io/File;)Ljava/lang/String;
    .locals 0

    .line 632
    invoke-virtual {p1}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p0

    invoke-virtual {p1, p0}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$5c3UmP4e7kXEHH1ee35GxSUgUI8(Lkotlin/text/MatchResult;)Ljava/lang/String;
    .locals 0

    .line 1054
    invoke-interface {p0}, Lkotlin/text/MatchResult;->getValue()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$87_LTqo6sTR2itQ72BHILUyS0HU(Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)Z
    .locals 1

    if-eqz p0, :cond_0

    .line 518
    invoke-interface {p0}, Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;->isCancelled()Z

    move-result p0

    const/4 v0, 0x1

    if-ne p0, v0, :cond_0

    return v0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public static $r8$lambda$At-2XbthCX74hISQxR9yeGC0zjE(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 231
    invoke-interface {p0, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$IvRbWk_j0OoVSagzIQZr5iKkP6Y(Ljava/lang/String;)Ljava/util/concurrent/ConcurrentHashMap;
    .locals 0

    .line 276
    new-instance p0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {p0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    return-object p0
.end method

.method public static $r8$lambda$JhKqr6gk7BdDf-6m5Jv_9fE-bq4(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;)Ljava/util/concurrent/ConcurrentHashMap;
    .locals 0

    .line 276
    invoke-interface {p0, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/util/concurrent/ConcurrentHashMap;

    return-object p0
.end method

.method public static $r8$lambda$M-1jcRh-Bc9GolubPg-ab7fZDV0(B)Ljava/lang/CharSequence;
    .locals 1

    .line 1045
    invoke-static {p0}, Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;

    move-result-object p0

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    const/4 v0, 0x1

    invoke-static {p0, v0}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object p0

    const-string v0, "%02x"

    invoke-static {v0, p0}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$My19ZFqHHYc3LTTYQWK8VSr8Yp8(Ljava/lang/String;)Ljava/lang/Object;
    .locals 0

    .line 231
    new-instance p0, Ljava/lang/Object;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-object p0
.end method

.method public static $r8$lambda$OyGnRfS2Ihkf6SpXamhns954ElA(Lkotlin/Pair;)Ljava/lang/CharSequence;
    .locals 2

    .line 246
    invoke-virtual {p0}, Lkotlin/Pair;->getFirst()Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {p0}, Lkotlin/Pair;->getSecond()Ljava/lang/Object;

    move-result-object p0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$VxzsFzBw4QdNHGlEwqCZa6Nfg_Q(Ljava/lang/String;Ljava/lang/String;)I
    .locals 1

    .line 449
    sget-object v0, Lcom/exteragram/messenger/plugins/pip/PipController$VersionComparator;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController$VersionComparator;

    invoke-virtual {v0, p1, p0}, Lcom/exteragram/messenger/plugins/pip/PipController$VersionComparator;->compare(Ljava/lang/String;Ljava/lang/String;)I

    move-result p0

    return p0
.end method

.method public static $r8$lambda$ZwvmxzcEiQc_SHP6_WmH7LntvAU(Ljava/util/List;Ljava/lang/String;)Lkotlin/Unit;
    .locals 10

    .line 701
    const-string v0, "Requires-Dist:"

    const/4 v1, 0x0

    const/4 v2, 0x2

    const/4 v3, 0x0

    invoke-static {p1, v0, v1, v2, v3}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    const/16 v0, 0xe

    .line 702
    invoke-virtual {p1, v0}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v4

    .line 704
    const-string p1, ";"

    invoke-static {v4, p1, v1, v2, v3}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 705
    filled-new-array {p1}, [Ljava/lang/String;

    move-result-object v5

    const/4 v8, 0x2

    const/4 v9, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x2

    invoke-static/range {v4 .. v9}, Lkotlin/text/StringsKt;->split$default(Ljava/lang/CharSequence;[Ljava/lang/String;ZIILjava/lang/Object;)Ljava/util/List;

    move-result-object p1

    const/4 v0, 0x1

    .line 706
    invoke-interface {p1, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    invoke-static {v0}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    .line 708
    sget-object v2, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-direct {v2, v0}, Lcom/exteragram/messenger/plugins/pip/PipController;->isMarkerCompatible(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 709
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0

    .line 711
    :cond_0
    invoke-interface {p1, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    invoke-static {p1}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-interface {p0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 713
    :cond_1
    invoke-interface {p0, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 716
    :cond_2
    :goto_0
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method public static $r8$lambda$bMMjhw95D3_CwhOi0sSL2GNaxCY(Ljava/io/File;Ljava/lang/String;)Z
    .locals 3

    const/4 p0, 0x2

    const/4 v0, 0x0

    .line 729
    const-string v1, ".dist-info"

    const/4 v2, 0x0

    invoke-static {p1, v1, v2, p0, v0}, Lkotlin/text/StringsKt;->endsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public static $r8$lambda$bWBVHEv1r6s8zQPAybPu6a75EO8(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;)Ljava/util/Set;
    .locals 0

    .line 277
    invoke-interface {p0, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/util/Set;

    return-object p0
.end method

.method public static $r8$lambda$gKjDvklOC_11lmtswn-GXaUHOkM(Ljava/lang/String;)Ljava/util/Set;
    .locals 0

    .line 277
    new-instance p0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {p0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    invoke-static {p0}, Ljava/util/Collections;->newSetFromMap(Ljava/util/Map;)Ljava/util/Set;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$l28neSHvSXC8INUr7OGeqMEdE3U(Lkotlin/Pair;)Ljava/lang/CharSequence;
    .locals 2

    .line 422
    invoke-virtual {p0}, Lkotlin/Pair;->getFirst()Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {p0}, Lkotlin/Pair;->getSecond()Ljava/lang/Object;

    move-result-object p0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$lnghLPpHuq6yVd8pzrPPZYi11Tc(Ljava/lang/String;Ljava/io/File;)Z
    .locals 3

    .line 631
    invoke-virtual {p1}, Ljava/io/File;->isDirectory()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    invoke-virtual {p1}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object p1

    const/4 v0, 0x2

    const/4 v2, 0x0

    invoke-static {p1, p0, v1, v0, v2}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    return v1
.end method

.method public static $r8$lambda$trhzkYfiRI0w9rVRwyVifG-bzTw(Ljava/util/List;Ljava/lang/String;)Z
    .locals 1

    .line 634
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-lez v0, :cond_0

    sget-object v0, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-direct {v0, p1, p0}, Lcom/exteragram/messenger/plugins/pip/PipController;->checkVersionSatisfies(Ljava/lang/String;Ljava/util/List;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method static constructor <clinit>()V
    .locals 10

    new-instance v0, Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-direct {v0}, Lcom/exteragram/messenger/plugins/pip/PipController;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    .line 45
    sget-object v1, Lcom/exteragram/messenger/utils/network/ExteraHttpClient;->INSTANCE:Lcom/exteragram/messenger/utils/network/ExteraHttpClient;

    invoke-virtual {v1}, Lcom/exteragram/messenger/utils/network/ExteraHttpClient;->getClient()Lokhttp3/OkHttpClient;

    move-result-object v1

    sput-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->client:Lokhttp3/OkHttpClient;

    .line 46
    new-instance v1, Lcom/google/gson/Gson;

    invoke-direct {v1}, Lcom/google/gson/Gson;-><init>()V

    sput-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->gson:Lcom/google/gson/Gson;

    .line 56
    new-instance v1, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v1}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    sput-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->registry:Ljava/util/concurrent/ConcurrentHashMap;

    .line 57
    new-instance v1, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v1}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    sput-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->installLocks:Ljava/util/concurrent/ConcurrentHashMap;

    .line 58
    sget-object v1, Lorg/simplifiles/archive/security/SecurityPolicy;->Companion:Lorg/simplifiles/archive/security/SecurityPolicy$Companion;

    invoke-virtual {v1}, Lorg/simplifiles/archive/security/SecurityPolicy$Companion;->builder()Lorg/simplifiles/archive/security/SecurityPolicy$Builder;

    move-result-object v1

    const-wide/32 v2, 0xc350

    .line 59
    invoke-virtual {v1, v2, v3}, Lorg/simplifiles/archive/security/SecurityPolicy$Builder;->maxEntries(J)Lorg/simplifiles/archive/security/SecurityPolicy$Builder;

    move-result-object v1

    const-wide/32 v2, 0x1f400000

    .line 60
    invoke-virtual {v1, v2, v3}, Lorg/simplifiles/archive/security/SecurityPolicy$Builder;->maxTotalUncompressedSize(J)Lorg/simplifiles/archive/security/SecurityPolicy$Builder;

    move-result-object v1

    const-wide/32 v2, 0xfa00000

    .line 61
    invoke-virtual {v1, v2, v3}, Lorg/simplifiles/archive/security/SecurityPolicy$Builder;->maxSingleFileSize(J)Lorg/simplifiles/archive/security/SecurityPolicy$Builder;

    move-result-object v1

    const-wide v2, 0x407f400000000000L    # 500.0

    .line 62
    invoke-virtual {v1, v2, v3}, Lorg/simplifiles/archive/security/SecurityPolicy$Builder;->maxCompressionRatio(D)Lorg/simplifiles/archive/security/SecurityPolicy$Builder;

    move-result-object v1

    .line 63
    invoke-virtual {v1}, Lorg/simplifiles/archive/security/SecurityPolicy$Builder;->build()Lorg/simplifiles/archive/security/SecurityPolicy;

    move-result-object v1

    sput-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->wheelArchivePolicy:Lorg/simplifiles/archive/security/SecurityPolicy;

    .line 65
    const-string v1, "3.11.10"

    sput-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->pythonVersion:Ljava/lang/String;

    .line 75
    new-instance v1, Lkotlin/text/Regex;

    const-string v2, "[-_.]+"

    invoke-direct {v1, v2}, Lkotlin/text/Regex;-><init>(Ljava/lang/String;)V

    sput-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->REGEX_NORMALIZE:Lkotlin/text/Regex;

    .line 76
    new-instance v1, Lkotlin/text/Regex;

    const-string v2, "^([a-zA-Z0-9_.-]+)(.*)"

    invoke-direct {v1, v2}, Lkotlin/text/Regex;-><init>(Ljava/lang/String;)V

    sput-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->REGEX_REQ_PARSE:Lkotlin/text/Regex;

    .line 77
    new-instance v1, Lkotlin/text/Regex;

    const-string v2, "^(===|==|>=|<=|>|<|~=|!=)\\s*(.*)"

    invoke-direct {v1, v2}, Lkotlin/text/Regex;-><init>(Ljava/lang/String;)V

    sput-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->REGEX_REQ_SPECS:Lkotlin/text/Regex;

    .line 78
    new-instance v1, Lkotlin/text/Regex;

    const-string v2, "\\[.*?]"

    invoke-direct {v1, v2}, Lkotlin/text/Regex;-><init>(Ljava/lang/String;)V

    sput-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->REGEX_REQ_EXTRA:Lkotlin/text/Regex;

    .line 79
    new-instance v1, Lkotlin/text/Regex;

    const-string v2, "[()]"

    invoke-direct {v1, v2}, Lkotlin/text/Regex;-><init>(Ljava/lang/String;)V

    sput-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->REGEX_REQ_PAREN:Lkotlin/text/Regex;

    .line 80
    new-instance v1, Lkotlin/text/Regex;

    const-string v2, "([0-9]+)|([a-zA-Z]+)"

    invoke-direct {v1, v2}, Lkotlin/text/Regex;-><init>(Ljava/lang/String;)V

    sput-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->REGEX_VERSION_SPLIT:Lkotlin/text/Regex;

    .line 82
    new-instance v1, Lkotlin/text/Regex;

    const-string v2, "(\"[^\"]*\"|\'[^\']*\'|===|==|!=|>=|<=|~=|>|<|\\(|\\)|\\b(?:and|or|in|not)\\b|[A-Za-z_][A-Za-z0-9_]*|\\S+)"

    invoke-direct {v1, v2}, Lkotlin/text/Regex;-><init>(Ljava/lang/String;)V

    sput-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->REGEX_MARKER_TOKEN:Lkotlin/text/Regex;

    .line 83
    new-instance v1, Lkotlin/text/Regex;

    const-string v2, "(?i)(?:^|[.\\-_])\\*$"

    invoke-direct {v1, v2}, Lkotlin/text/Regex;-><init>(Ljava/lang/String;)V

    sput-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->REGEX_VERSION_WILDCARD:Lkotlin/text/Regex;

    .line 98
    invoke-direct {v0}, Lcom/exteragram/messenger/plugins/pip/PipController;->loadRegistry()V

    .line 177
    const-string/jumbo v8, "requests"

    const-string/jumbo v9, "pyyaml"

    const-string v3, "beautifulsoup4"

    const-string v4, "debugpy"

    const-string/jumbo v5, "lxml"

    const-string/jumbo v6, "packaging"

    const-string/jumbo v7, "pillow"

    filled-new-array/range {v3 .. v9}, [Ljava/lang/String;

    move-result-object v0

    .line 176
    invoke-static {v0}, Lkotlin/collections/SetsKt;->setOf([Ljava/lang/Object;)Ljava/util/Set;

    move-result-object v0

    .line 1586
    new-instance v1, Ljava/util/ArrayList;

    const/16 v2, 0xa

    invoke-static {v0, v2}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v2

    invoke-direct {v1, v2}, Ljava/util/ArrayList;-><init>(I)V

    .line 1661
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    .line 1662
    check-cast v2, Ljava/lang/String;

    .line 178
    sget-object v3, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-direct {v3, v2}, Lcom/exteragram/messenger/plugins/pip/PipController;->normalizePackageName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 1662
    invoke-interface {v1, v2}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 178
    :cond_0
    invoke-static {v1}, Lkotlin/collections/CollectionsKt;->toSet(Ljava/lang/Iterable;)Ljava/util/Set;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/plugins/pip/PipController;->PREINSTALLED_PACKAGES:Ljava/util/Set;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 38
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static final synthetic access$checkVersionSatisfies(Lcom/exteragram/messenger/plugins/pip/PipController;Ljava/lang/String;Ljava/util/List;)Z
    .locals 0

    .line 38
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/plugins/pip/PipController;->checkVersionSatisfies(Ljava/lang/String;Ljava/util/List;)Z

    move-result p0

    return p0
.end method

.method public static final synthetic access$getREGEX_MARKER_TOKEN$p()Lkotlin/text/Regex;
    .locals 1

    .line 38
    sget-object v0, Lcom/exteragram/messenger/plugins/pip/PipController;->REGEX_MARKER_TOKEN:Lkotlin/text/Regex;

    return-object v0
.end method

.method public static final synthetic access$parseVersion(Lcom/exteragram/messenger/plugins/pip/PipController;Ljava/lang/String;)Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;
    .locals 0

    .line 38
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/pip/PipController;->parseVersion(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;

    move-result-object p0

    return-object p0
.end method

.method private final calculateSha256(Ljava/io/File;)Ljava/lang/String;
    .locals 11

    .line 1037
    const-string p0, "SHA-256"

    invoke-static {p0}, Ljava/security/MessageDigest;->getInstance(Ljava/lang/String;)Ljava/security/MessageDigest;

    move-result-object p0

    new-instance v1, Ljava/io/FileInputStream;

    .line 1038
    invoke-direct {v1, p1}, Ljava/io/FileInputStream;-><init>(Ljava/io/File;)V

    const/16 p1, 0x2000

    .line 1039
    :try_start_0
    new-array p1, p1, [B

    .line 1041
    :goto_0
    invoke-virtual {v1, p1}, Ljava/io/FileInputStream;->read([B)I

    move-result v0

    const/4 v2, -0x1

    if-eq v0, v2, :cond_0

    const/4 v2, 0x0

    .line 1042
    invoke-virtual {p0, p1, v2, v0}, Ljava/security/MessageDigest;->update([BII)V

    goto :goto_0

    :catchall_0
    move-exception v0

    move-object p0, v0

    goto :goto_1

    .line 1044
    :cond_0
    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    const/4 p1, 0x0

    .line 1038
    invoke-static {v1, p1}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    .line 1045
    invoke-virtual {p0}, Ljava/security/MessageDigest;->digest()[B

    move-result-object v2

    new-instance v8, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda12;

    invoke-direct {v8}, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda12;-><init>()V

    const/16 v9, 0x1e

    const/4 v10, 0x0

    const-string v3, ""

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    invoke-static/range {v2 .. v10}, Lkotlin/collections/ArraysKt;->joinToString$default([BLjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 1038
    :goto_1
    :try_start_1
    throw p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    :catchall_1
    move-exception v0

    move-object p1, v0

    invoke-static {v1, p0}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    throw p1
.end method

.method private final checkVersionSatisfies(Ljava/lang/String;Ljava/util/List;)Z
    .locals 22
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Lkotlin/Pair<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;>;)Z"
        }
    .end annotation

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    .line 898
    invoke-interface/range {p2 .. p2}, Ljava/util/List;->isEmpty()Z

    move-result v2

    const/4 v3, 0x1

    if-eqz v2, :cond_0

    return v3

    .line 899
    :cond_0
    invoke-interface/range {p2 .. p2}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_1
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_17

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lkotlin/Pair;

    invoke-virtual {v4}, Lkotlin/Pair;->component1()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/String;

    invoke-virtual {v4}, Lkotlin/Pair;->component2()Ljava/lang/Object;

    move-result-object v4

    move-object v6, v4

    check-cast v6, Ljava/lang/String;

    .line 900
    sget-object v4, Lcom/exteragram/messenger/plugins/pip/PipController$VersionComparator;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController$VersionComparator;

    invoke-virtual {v4, v1, v6}, Lcom/exteragram/messenger/plugins/pip/PipController$VersionComparator;->compare(Ljava/lang/String;Ljava/lang/String;)I

    move-result v7

    .line 901
    invoke-virtual {v5}, Ljava/lang/String;->hashCode()I

    move-result v8

    const/16 v9, 0x3c

    const/4 v12, 0x0

    if-eq v8, v9, :cond_15

    const/16 v9, 0x3e

    if-eq v8, v9, :cond_13

    const/16 v9, 0x43c

    if-eq v8, v9, :cond_10

    const/16 v9, 0x781

    if-eq v8, v9, :cond_e

    const/16 v9, 0x7a0

    if-eq v8, v9, :cond_b

    const/16 v9, 0x7bf

    if-eq v8, v9, :cond_9

    const/16 v9, 0xf7f

    if-eq v8, v9, :cond_4

    const v4, 0xec9d

    if-eq v8, v4, :cond_2

    goto/16 :goto_1

    :cond_2
    const-string v4, "==="

    invoke-virtual {v5, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_3

    goto/16 :goto_1

    .line 902
    :cond_3
    invoke-static {v1, v6}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v4

    goto/16 :goto_2

    .line 901
    :cond_4
    const-string/jumbo v8, "~="

    invoke-virtual {v5, v8}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v5

    if-nez v5, :cond_5

    goto/16 :goto_1

    :cond_5
    if-gez v7, :cond_7

    :cond_6
    move v4, v12

    goto/16 :goto_2

    .line 914
    :cond_7
    const-string v5, "."

    filled-new-array {v5}, [Ljava/lang/String;

    move-result-object v7

    const/4 v10, 0x6

    const/4 v11, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x0

    invoke-static/range {v6 .. v11}, Lkotlin/text/StringsKt;->split$default(Ljava/lang/CharSequence;[Ljava/lang/String;ZIILjava/lang/Object;)Ljava/util/List;

    move-result-object v5

    check-cast v5, Ljava/util/Collection;

    invoke-static {v5}, Lkotlin/collections/CollectionsKt;->toMutableList(Ljava/util/Collection;)Ljava/util/List;

    move-result-object v5

    .line 916
    invoke-interface {v5}, Ljava/util/List;->size()I

    move-result v6

    const/4 v7, 0x2

    if-lt v6, v7, :cond_8

    .line 917
    invoke-static {v5}, Lkotlin/collections/CollectionsKt;->getLastIndex(Ljava/util/List;)I

    move-result v6

    invoke-interface {v5, v6}, Ljava/util/List;->remove(I)Ljava/lang/Object;

    .line 919
    invoke-static {v5}, Lkotlin/collections/CollectionsKt;->getLastIndex(Ljava/util/List;)I

    move-result v6

    .line 920
    invoke-interface {v5, v6}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    .line 922
    invoke-static {v7}, Lkotlin/text/StringsKt;->toIntOrNull(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v7

    if-eqz v7, :cond_8

    .line 925
    invoke-virtual {v7}, Ljava/lang/Integer;->intValue()I

    move-result v7

    add-int/2addr v7, v3

    invoke-static {v7}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v7

    invoke-interface {v5, v6, v7}, Ljava/util/List;->set(ILjava/lang/Object;)Ljava/lang/Object;

    .line 926
    move-object v13, v5

    check-cast v13, Ljava/lang/Iterable;

    const/16 v20, 0x3e

    const/16 v21, 0x0

    const-string v14, "."

    const/4 v15, 0x0

    const/16 v16, 0x0

    const/16 v17, 0x0

    const/16 v18, 0x0

    const/16 v19, 0x0

    invoke-static/range {v13 .. v21}, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v5

    .line 927
    invoke-virtual {v4, v1, v5}, Lcom/exteragram/messenger/plugins/pip/PipController$VersionComparator;->compare(Ljava/lang/String;Ljava/lang/String;)I

    move-result v4

    if-gez v4, :cond_6

    :cond_8
    :goto_0
    move v4, v3

    goto/16 :goto_2

    .line 901
    :cond_9
    const-string v4, ">="

    invoke-virtual {v5, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_a

    goto :goto_1

    :cond_a
    if-ltz v7, :cond_6

    goto :goto_0

    :cond_b
    const-string v4, "=="

    invoke-virtual {v5, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_c

    goto :goto_1

    .line 904
    :cond_c
    invoke-direct {v0, v6}, Lcom/exteragram/messenger/plugins/pip/PipController;->isWildcardVersionSpec(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_d

    invoke-direct {v0, v1, v6}, Lcom/exteragram/messenger/plugins/pip/PipController;->matchesVersionWildcard(Ljava/lang/String;Ljava/lang/String;)Z

    move-result v4

    goto :goto_2

    :cond_d
    if-nez v7, :cond_6

    goto :goto_0

    .line 901
    :cond_e
    const-string v4, "<="

    invoke-virtual {v5, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_f

    goto :goto_1

    :cond_f
    if-gtz v7, :cond_6

    goto :goto_0

    :cond_10
    const-string v4, "!="

    invoke-virtual {v5, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_11

    goto :goto_1

    .line 938
    :cond_11
    invoke-direct {v0, v6}, Lcom/exteragram/messenger/plugins/pip/PipController;->isWildcardVersionSpec(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_12

    invoke-direct {v0, v1, v6}, Lcom/exteragram/messenger/plugins/pip/PipController;->matchesVersionWildcard(Ljava/lang/String;Ljava/lang/String;)Z

    move-result v4

    if-nez v4, :cond_6

    goto :goto_0

    :cond_12
    if-eqz v7, :cond_6

    goto :goto_0

    .line 901
    :cond_13
    const-string v4, ">"

    invoke-virtual {v5, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_14

    goto :goto_1

    :cond_14
    if-lez v7, :cond_6

    goto :goto_0

    :cond_15
    const-string v4, "<"

    invoke-virtual {v5, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_16

    :goto_1
    goto :goto_0

    :cond_16
    if-gez v7, :cond_6

    goto :goto_0

    :goto_2
    if-nez v4, :cond_1

    return v12

    :cond_17
    return v3
.end method

.method private final cleanupInternal()V
    .locals 8

    .line 326
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 328
    sget-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->registry:Ljava/util/concurrent/ConcurrentHashMap;

    .line 221
    invoke-interface {v1}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_2

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/Map$Entry;

    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/concurrent/ConcurrentHashMap;

    .line 221
    invoke-interface {v2}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v2

    invoke-interface {v2}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_1
    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_0

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/util/Map$Entry;

    invoke-interface {v4}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/String;

    invoke-interface {v4}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/util/Set;

    .line 330
    invoke-interface {v4}, Ljava/util/Set;->isEmpty()Z

    move-result v4

    if-eqz v4, :cond_1

    .line 331
    invoke-static {v3, v5}, Lkotlin/TuplesKt;->to(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;

    move-result-object v4

    invoke-interface {v0, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 1915
    :cond_2
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    const/4 v2, 0x0

    :goto_1
    if-ge v2, v1, :cond_5

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    add-int/lit8 v2, v2, 0x1

    check-cast v3, Lkotlin/Pair;

    invoke-virtual {v3}, Lkotlin/Pair;->component1()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    invoke-virtual {v3}, Lkotlin/Pair;->component2()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    .line 337
    sget-object v5, Lcom/exteragram/messenger/plugins/pip/PipController;->registry:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v5, v4}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/util/concurrent/ConcurrentHashMap;

    if-eqz v6, :cond_3

    invoke-virtual {v6, v3}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/util/Set;

    .line 338
    :cond_3
    invoke-virtual {v5, v4}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/util/concurrent/ConcurrentHashMap;

    if-eqz v6, :cond_4

    invoke-virtual {v6}, Ljava/util/concurrent/ConcurrentHashMap;->isEmpty()Z

    move-result v6

    const/4 v7, 0x1

    if-ne v6, v7, :cond_4

    .line 339
    invoke-virtual {v5, v4}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 341
    :cond_4
    sget-object v5, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-direct {v5, v4, v3}, Lcom/exteragram/messenger/plugins/pip/PipController;->deletePackage(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_1

    .line 344
    :cond_5
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/pip/PipController;->removeOrphanedDirectories()V

    return-void
.end method

.method private final deletePackage(Ljava/lang/String;Ljava/lang/String;)V
    .locals 1

    .line 659
    const-string v0, "PipController: Deleted package "

    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/plugins/pip/PipController;->getLibPath(Ljava/lang/String;Ljava/lang/String;)Ljava/io/File;

    move-result-object p0

    .line 660
    invoke-virtual {p0}, Ljava/io/File;->exists()Z

    move-result p1

    if-eqz p1, :cond_0

    .line 661
    :try_start_0
    sget-object p1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 662
    invoke-static {p0}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object p1

    invoke-virtual {p1}, Lorg/simplifiles/files/SimpliDirectory;->deleteRecursively()Z

    .line 663
    invoke-virtual {p0}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object p1

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    .line 664
    sget-object p1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    .line 661
    invoke-static {p1}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception p1

    sget-object p2, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {p1}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p1

    invoke-static {p1}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    .line 664
    :goto_0
    invoke-static {p1}, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 665
    invoke-virtual {p0}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object p0

    new-instance p2, Ljava/lang/StringBuilder;

    const-string v0, "PipController: Failed to delete package "

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_0
    return-void
.end method

.method private final executeWithRetry(Lokhttp3/Request;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)Lokhttp3/Response;
    .locals 6

    const/4 p0, 0x0

    const/4 v0, 0x0

    move-object v1, v0

    :catch_0
    :goto_0
    const/4 v2, 0x3

    .line 610
    const-string v3, "Installation cancelled"

    const/4 v4, 0x1

    if-ge p0, v2, :cond_2

    if-eqz p2, :cond_1

    .line 611
    invoke-interface {p2}, Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;->isCancelled()Z

    move-result v1

    if-eq v1, v4, :cond_0

    goto :goto_1

    :cond_0
    invoke-static {v3}, Lorg/vosk/Model$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-object v0

    .line 613
    :cond_1
    :goto_1
    :try_start_0
    sget-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->client:Lokhttp3/OkHttpClient;

    invoke-virtual {v1, p1}, Lokhttp3/OkHttpClient;->newCall(Lokhttp3/Request;)Lokhttp3/Call;

    move-result-object v1

    invoke-interface {v1}, Lokhttp3/Call;->execute()Lokhttp3/Response;

    move-result-object p0
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1

    return-object p0

    :catch_1
    move-exception v1

    add-int/lit8 p0, p0, 0x1

    .line 617
    invoke-virtual {v1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v2

    new-instance v3, Ljava/lang/StringBuilder;

    const-string v4, "PipController: Network error, retrying ("

    invoke-direct {v3, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v4, "/3): "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lorg/telegram/messenger/FileLog;->w(Ljava/lang/String;)V

    const-wide/16 v2, 0x3e8

    int-to-long v4, p0

    mul-long/2addr v4, v2

    .line 619
    :try_start_1
    invoke-static {v4, v5}, Ljava/lang/Thread;->sleep(J)V
    :try_end_1
    .catch Ljava/lang/InterruptedException; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_0

    :cond_2
    if-eqz p2, :cond_4

    .line 624
    invoke-interface {p2}, Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;->isCancelled()Z

    move-result p0

    if-eq p0, v4, :cond_3

    goto :goto_2

    :cond_3
    invoke-static {v3}, Lorg/vosk/Model$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-object v0

    :cond_4
    :goto_2
    if-eqz v1, :cond_5

    goto :goto_3

    .line 625
    :cond_5
    new-instance v1, Ljava/io/IOException;

    const-string p0, "Unknown network error"

    invoke-direct {v1, p0}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    :goto_3
    throw v1
.end method

.method private final filterPreReleases(Ljava/util/List;Ljava/util/List;)Ljava/util/List;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;",
            "Ljava/util/List<",
            "Lkotlin/Pair<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;>;)",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 956
    invoke-interface {p1}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_4

    invoke-direct {p0, p2}, Lcom/exteragram/messenger/plugins/pip/PipController;->specsAllowPreRelease(Ljava/util/List;)Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_2

    .line 960
    :cond_0
    move-object p0, p1

    check-cast p0, Ljava/lang/Iterable;

    .line 832
    new-instance p2, Ljava/util/ArrayList;

    invoke-direct {p2}, Ljava/util/ArrayList;-><init>()V

    .line 862
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_1
    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    move-object v1, v0

    check-cast v1, Ljava/lang/String;

    .line 960
    sget-object v2, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-direct {v2, v1}, Lcom/exteragram/messenger/plugins/pip/PipController;->isPreReleaseVersion(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_1

    .line 862
    invoke-interface {p2, v0}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 961
    :cond_2
    invoke-interface {p2}, Ljava/util/Collection;->isEmpty()Z

    move-result p0

    if-eqz p0, :cond_3

    goto :goto_1

    :cond_3
    move-object p1, p2

    :goto_1
    check-cast p1, Ljava/util/List;

    :cond_4
    :goto_2
    return-object p1
.end method

.method private final findInstalledVersion(Ljava/lang/String;Ljava/util/List;)Ljava/lang/String;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Lkotlin/Pair<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;>;)",
            "Ljava/lang/String;"
        }
    .end annotation

    .line 643
    sget-object v0, Lcom/exteragram/messenger/plugins/pip/PipController;->registry:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/util/concurrent/ConcurrentHashMap;

    if-nez p1, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 645
    :cond_0
    invoke-virtual {p1}, Ljava/util/concurrent/ConcurrentHashMap;->keySet()Ljava/util/Set;

    move-result-object p1

    .line 777
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 873
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_1
    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    move-object v2, v1

    check-cast v2, Ljava/lang/String;

    .line 645
    sget-object v3, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-direct {v3, v2, p2}, Lcom/exteragram/messenger/plugins/pip/PipController;->checkVersionSatisfies(Ljava/lang/String;Ljava/util/List;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 873
    invoke-interface {v0, v1}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 644
    :cond_2
    invoke-direct {p0, v0, p2}, Lcom/exteragram/messenger/plugins/pip/PipController;->filterPreReleases(Ljava/util/List;Ljava/util/List;)Ljava/util/List;

    move-result-object p0

    check-cast p0, Ljava/lang/Iterable;

    .line 648
    sget-object p1, Lcom/exteragram/messenger/plugins/pip/PipController$VersionComparator;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController$VersionComparator;

    invoke-static {p0, p1}, Lkotlin/collections/CollectionsKt;->maxWithOrNull(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/String;

    return-object p0
.end method

.method private final findMetadataFile(Ljava/lang/String;Ljava/lang/String;)Ljava/io/File;
    .locals 0

    .line 728
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/plugins/pip/PipController;->getLibPath(Ljava/lang/String;Ljava/lang/String;)Ljava/io/File;

    move-result-object p0

    .line 729
    new-instance p1, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda11;

    invoke-direct {p1}, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda11;-><init>()V

    invoke-virtual {p0, p1}, Ljava/io/File;->listFiles(Ljava/io/FilenameFilter;)[Ljava/io/File;

    move-result-object p0

    const/4 p1, 0x0

    if-eqz p0, :cond_0

    invoke-static {p0}, Lkotlin/collections/ArraysKt;->firstOrNull([Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/io/File;

    goto :goto_0

    :cond_0
    move-object p0, p1

    :goto_0
    if-eqz p0, :cond_1

    .line 730
    new-instance p1, Ljava/io/File;

    const-string p2, "METADATA"

    invoke-direct {p1, p0, p2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    :cond_1
    return-object p1
.end method

.method private final findVersionOnDisk(Ljava/lang/String;Ljava/util/List;)Ljava/lang/String;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Lkotlin/Pair<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;>;)",
            "Ljava/lang/String;"
        }
    .end annotation

    .line 629
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "-"

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 630
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/pip/PipController;->getLibsDir()Ljava/io/File;

    move-result-object v0

    invoke-virtual {v0}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-static {v0}, Lkotlin/collections/ArraysKt;->asSequence([Ljava/lang/Object;)Lkotlin/sequences/Sequence;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 631
    new-instance v1, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda14;

    invoke-direct {v1, p1}, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda14;-><init>(Ljava/lang/String;)V

    invoke-static {v0, v1}, Lkotlin/sequences/SequencesKt;->filter(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 632
    new-instance v1, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda15;

    invoke-direct {v1, p1}, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda15;-><init>(Ljava/lang/String;)V

    invoke-static {v0, v1}, Lkotlin/sequences/SequencesKt;->map(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 633
    new-instance v0, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda16;

    invoke-direct {v0, p2}, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda16;-><init>(Ljava/util/List;)V

    invoke-static {p1, v0}, Lkotlin/sequences/SequencesKt;->filter(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 636
    invoke-static {p1}, Lkotlin/sequences/SequencesKt;->toList(Lkotlin/sequences/Sequence;)Ljava/util/List;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 639
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/plugins/pip/PipController;->filterPreReleases(Ljava/util/List;Ljava/util/List;)Ljava/util/List;

    move-result-object p0

    check-cast p0, Ljava/lang/Iterable;

    sget-object p1, Lcom/exteragram/messenger/plugins/pip/PipController$VersionComparator;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController$VersionComparator;

    invoke-static {p0, p1}, Lkotlin/collections/CollectionsKt;->maxWithOrNull(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/String;

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method private final getBooleanOrNull(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/Boolean;
    .locals 1

    .line 574
    invoke-virtual {p1, p2}, Lcom/google/gson/JsonObject;->has(Ljava/lang/String;)Z

    move-result p0

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return-object v0

    .line 575
    :cond_0
    invoke-virtual {p1, p2}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object p0

    if-eqz p0, :cond_3

    .line 576
    invoke-virtual {p0}, Lcom/google/gson/JsonElement;->isJsonNull()Z

    move-result p1

    if-eqz p1, :cond_1

    goto :goto_2

    .line 577
    :cond_1
    :try_start_0
    sget-object p1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-virtual {p0}, Lcom/google/gson/JsonElement;->getAsBoolean()Z

    move-result p0

    invoke-static {p0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception p0

    sget-object p1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {p0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    :goto_0
    invoke-static {p0}, Lkotlin/Result;->isFailure-impl(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_2

    goto :goto_1

    :cond_2
    move-object v0, p0

    :goto_1
    check-cast v0, Ljava/lang/Boolean;

    :cond_3
    :goto_2
    return-object v0
.end method

.method private final getLibPath(Ljava/lang/String;Ljava/lang/String;)Ljava/io/File;
    .locals 4

    .line 652
    const-string v0, "/"

    const/4 v1, 0x0

    const/4 v2, 0x2

    const/4 v3, 0x0

    invoke-static {p2, v0, v1, v2, v3}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    const-string v0, "\\"

    invoke-static {p2, v0, v1, v2, v3}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    const-string v0, ".."

    invoke-static {p2, v0, v1, v2, v3}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 655
    new-instance v0, Ljava/io/File;

    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/pip/PipController;->getLibsDir()Ljava/io/File;

    move-result-object p0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "-"

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {v0, p0, p1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    return-object v0

    .line 653
    :cond_0
    const-string p0, "Invalid version: "

    invoke-static {p0, p2}, Lokio/ZipFileSystem$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Object;)V

    return-object v3
.end method

.method private final getLibsDir()Ljava/io/File;
    .locals 2

    .line 50
    new-instance p0, Ljava/io/File;

    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginsDir()Ljava/io/File;

    move-result-object v0

    const-string/jumbo v1, "libs"

    invoke-direct {p0, v0, v1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 49
    invoke-static {p0}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object p0

    .line 51
    invoke-virtual {p0}, Lorg/simplifiles/files/SimpliDirectory;->create()Lorg/simplifiles/files/SimpliDirectory;

    move-result-object p0

    invoke-virtual {p0}, Lorg/simplifiles/files/SimpliDirectory;->getFile()Ljava/io/File;

    move-result-object p0

    return-object p0
.end method

.method private final getRegistryFile()Ljava/io/File;
    .locals 2

    .line 54
    new-instance v0, Ljava/io/File;

    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/pip/PipController;->getLibsDir()Ljava/io/File;

    move-result-object p0

    const-string/jumbo v1, "registry.json"

    invoke-direct {v0, p0, v1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    return-object v0
.end method

.method private final getStringOrNull(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    .line 567
    invoke-virtual {p1, p2}, Lcom/google/gson/JsonObject;->has(Ljava/lang/String;)Z

    move-result p0

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return-object v0

    .line 568
    :cond_0
    invoke-virtual {p1, p2}, Lcom/google/gson/JsonObject;->get(Ljava/lang/String;)Lcom/google/gson/JsonElement;

    move-result-object p0

    if-eqz p0, :cond_2

    .line 569
    invoke-virtual {p0}, Lcom/google/gson/JsonElement;->isJsonNull()Z

    move-result p1

    if-eqz p1, :cond_1

    goto :goto_0

    .line 570
    :cond_1
    invoke-virtual {p0}, Lcom/google/gson/JsonElement;->getAsString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_2
    :goto_0
    return-object v0
.end method

.method public static synthetic installDependencies$default(Lcom/exteragram/messenger/plugins/pip/PipController;Ljava/util/List;Ljava/lang/String;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;ILjava/lang/Object;)Ljava/util/List;
    .locals 0

    and-int/lit8 p4, p4, 0x4

    if-eqz p4, :cond_0

    const/4 p3, 0x0

    .line 180
    :cond_0
    invoke-virtual {p0, p1, p2, p3}, Lcom/exteragram/messenger/plugins/pip/PipController;->installDependencies(Ljava/util/List;Ljava/lang/String;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)Ljava/util/List;

    move-result-object p0

    return-object p0
.end method

.method private final installPackage(Ljava/lang/String;Ljava/util/List;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)Ljava/lang/String;
    .locals 24
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Lkotlin/Pair<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;>;",
            "Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;",
            ")",
            "Ljava/lang/String;"
        }
    .end annotation

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    move-object/from16 v3, p3

    .line 422
    const-string v4, "No releases found for "

    const-string v5, "Invalid metadata response from PyPI for "

    const-string v6, "Failed to fetch metadata for "

    move-object v7, v2

    check-cast v7, Ljava/util/Collection;

    invoke-interface {v7}, Ljava/util/Collection;->isEmpty()Z

    move-result v7

    if-nez v7, :cond_0

    move-object v8, v2

    check-cast v8, Ljava/lang/Iterable;

    new-instance v14, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda0;

    invoke-direct {v14}, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda0;-><init>()V

    const/16 v15, 0x1e

    const/16 v16, 0x0

    const-string v9, ","

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v12, 0x0

    const/4 v13, 0x0

    invoke-static/range {v8 .. v16}, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v7

    goto :goto_0

    :cond_0
    const-string v7, "(latest)"

    .line 424
    :goto_0
    new-instance v8, Ljava/lang/StringBuilder;

    const-string v9, "PipController: Installing "

    invoke-direct {v8, v9}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v8, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v9, " "

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-static {v7}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    .line 425
    const-string v7, "..."

    if-eqz v3, :cond_1

    new-instance v8, Ljava/lang/StringBuilder;

    const-string v10, "Resolving "

    invoke-direct {v8, v10}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v8, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-interface {v3, v8}, Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;->onProgress(Ljava/lang/String;)V

    .line 427
    :cond_1
    new-instance v8, Ljava/lang/StringBuilder;

    const-string/jumbo v10, "https://pypi.org/pypi/"

    invoke-direct {v8, v10}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v8, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v10, "/json"

    invoke-virtual {v8, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    .line 428
    new-instance v10, Lokhttp3/Request$Builder;

    invoke-direct {v10}, Lokhttp3/Request$Builder;-><init>()V

    invoke-virtual {v10, v8}, Lokhttp3/Request$Builder;->url(Ljava/lang/String;)Lokhttp3/Request$Builder;

    move-result-object v8

    invoke-virtual {v8}, Lokhttp3/Request$Builder;->build()Lokhttp3/Request;

    move-result-object v8

    .line 432
    invoke-direct {v0, v8, v3}, Lcom/exteragram/messenger/plugins/pip/PipController;->executeWithRetry(Lokhttp3/Request;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)Lokhttp3/Response;

    move-result-object v8

    .line 433
    :try_start_0
    invoke-virtual {v8}, Lokhttp3/Response;->isSuccessful()Z

    move-result v10
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    const-string v11, "Package "

    if-nez v10, :cond_3

    .line 434
    :try_start_1
    invoke-virtual {v8}, Lokhttp3/Response;->code()I

    move-result v0

    const/16 v2, 0x194

    if-ne v0, v2, :cond_2

    new-instance v0, Ljava/io/IOException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v11}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " not found on PyPI"

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0

    :catchall_0
    move-exception v0

    move-object v1, v0

    goto/16 :goto_e

    .line 435
    :cond_2
    new-instance v0, Ljava/io/IOException;

    invoke-virtual {v8}, Lokhttp3/Response;->code()I

    move-result v2

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3, v6}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ": "

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 438
    :cond_3
    invoke-virtual {v8}, Lokhttp3/Response;->body()Lokhttp3/ResponseBody;

    move-result-object v6

    invoke-virtual {v6}, Lokhttp3/ResponseBody;->string()Ljava/lang/String;

    move-result-object v6

    .line 439
    sget-object v10, Lcom/exteragram/messenger/plugins/pip/PipController;->gson:Lcom/google/gson/Gson;

    const-class v12, Lcom/google/gson/JsonObject;

    invoke-virtual {v10, v6, v12}, Lcom/google/gson/Gson;->fromJson(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lcom/google/gson/JsonObject;

    if-eqz v6, :cond_1d

    .line 442
    const-string/jumbo v5, "releases"

    invoke-virtual {v6, v5}, Lcom/google/gson/JsonObject;->getAsJsonObject(Ljava/lang/String;)Lcom/google/gson/JsonObject;

    move-result-object v5

    if-eqz v5, :cond_1c

    .line 445
    invoke-virtual {v5}, Lcom/google/gson/JsonObject;->keySet()Ljava/util/Set;

    move-result-object v4

    invoke-static {v4}, Lkotlin/collections/CollectionsKt;->toList(Ljava/lang/Iterable;)Ljava/util/List;

    move-result-object v4

    .line 446
    sget-object v10, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    .line 447
    check-cast v4, Ljava/lang/Iterable;

    .line 777
    new-instance v12, Ljava/util/ArrayList;

    invoke-direct {v12}, Ljava/util/ArrayList;-><init>()V

    .line 873
    invoke-interface {v4}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v4

    :cond_4
    :goto_1
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v13

    if-eqz v13, :cond_5

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v13

    move-object v14, v13

    check-cast v14, Ljava/lang/String;

    .line 447
    sget-object v15, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-direct {v15, v14, v2}, Lcom/exteragram/messenger/plugins/pip/PipController;->checkVersionSatisfies(Ljava/lang/String;Ljava/util/List;)Z

    move-result v14

    if-eqz v14, :cond_4

    .line 873
    invoke-interface {v12, v13}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_1

    .line 446
    :cond_5
    invoke-direct {v10, v12, v2}, Lcom/exteragram/messenger/plugins/pip/PipController;->filterPreReleases(Ljava/util/List;Ljava/util/List;)Ljava/util/List;

    move-result-object v4

    check-cast v4, Ljava/lang/Iterable;

    .line 449
    new-instance v10, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda1;

    invoke-direct {v10}, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda1;-><init>()V

    invoke-static {v4, v10}, Lkotlin/collections/CollectionsKt;->sortedWith(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/util/List;

    move-result-object v4

    .line 451
    check-cast v2, Ljava/lang/Iterable;

    .line 1807
    instance-of v10, v2, Ljava/util/Collection;

    const/4 v13, 0x0

    if-eqz v10, :cond_6

    move-object v10, v2

    check-cast v10, Ljava/util/Collection;

    invoke-interface {v10}, Ljava/util/Collection;->isEmpty()Z

    move-result v10

    if-eqz v10, :cond_6

    goto :goto_2

    .line 1808
    :cond_6
    invoke-interface {v2}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_7
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v10

    if-eqz v10, :cond_9

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Lkotlin/Pair;

    .line 451
    invoke-virtual {v10}, Lkotlin/Pair;->getFirst()Ljava/lang/Object;

    move-result-object v14

    const-string v15, "=="

    invoke-static {v14, v15}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v14

    if-nez v14, :cond_8

    invoke-virtual {v10}, Lkotlin/Pair;->getFirst()Ljava/lang/Object;

    move-result-object v10

    const-string v14, "==="

    invoke-static {v10, v14}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v10

    if-eqz v10, :cond_7

    :cond_8
    const/4 v13, 0x1

    .line 453
    :cond_9
    :goto_2
    invoke-interface {v4}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v2

    const/4 v10, 0x0

    :cond_a
    :goto_3
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v14

    if-eqz v14, :cond_e

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v14

    check-cast v14, Ljava/lang/String;

    .line 454
    invoke-virtual {v5, v14}, Lcom/google/gson/JsonObject;->getAsJsonArray(Ljava/lang/String;)Lcom/google/gson/JsonArray;

    move-result-object v15

    if-nez v15, :cond_b

    goto :goto_3

    .line 456
    :cond_b
    invoke-virtual {v15}, Lcom/google/gson/JsonArray;->iterator()Ljava/util/Iterator;

    move-result-object v15

    :goto_4
    invoke-interface {v15}, Ljava/util/Iterator;->hasNext()Z

    move-result v16

    if-eqz v16, :cond_d

    invoke-interface {v15}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v16

    check-cast v16, Lcom/google/gson/JsonElement;

    .line 457
    invoke-virtual/range {v16 .. v16}, Lcom/google/gson/JsonElement;->getAsJsonObject()Lcom/google/gson/JsonObject;

    move-result-object v12

    .line 458
    sget-object v4, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-direct {v4, v14, v12, v13}, Lcom/exteragram/messenger/plugins/pip/PipController;->selectWheelCandidate(Ljava/lang/String;Lcom/google/gson/JsonObject;Z)Lcom/exteragram/messenger/plugins/pip/PipController$WheelCandidate;

    move-result-object v4

    if-nez v4, :cond_c

    goto :goto_4

    :cond_c
    move-object v10, v4

    :cond_d
    if-eqz v10, :cond_a

    :cond_e
    if-nez v10, :cond_11

    .line 469
    const-string/jumbo v2, "info"

    invoke-virtual {v6, v2}, Lcom/google/gson/JsonObject;->getAsJsonObject(Ljava/lang/String;)Lcom/google/gson/JsonObject;

    move-result-object v2

    if-eqz v2, :cond_f

    .line 470
    sget-object v4, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    const-string/jumbo v5, "requires_python"

    invoke-direct {v4, v2, v5}, Lcom/exteragram/messenger/plugins/pip/PipController;->getStringOrNull(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    goto :goto_5

    :cond_f
    const/4 v2, 0x0

    :goto_5
    if-eqz v2, :cond_11

    .line 472
    sget-object v4, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-direct {v4, v2}, Lcom/exteragram/messenger/plugins/pip/PipController;->parseSpecs(Ljava/lang/String;)Ljava/util/List;

    move-result-object v5

    .line 473
    sget-object v6, Lcom/exteragram/messenger/plugins/pip/PipController;->pythonVersion:Ljava/lang/String;

    invoke-direct {v4, v6, v5}, Lcom/exteragram/messenger/plugins/pip/PipController;->checkVersionSatisfies(Ljava/lang/String;Ljava/util/List;)Z

    move-result v4

    if-eqz v4, :cond_10

    goto :goto_6

    .line 474
    :cond_10
    new-instance v0, Ljava/io/IOException;

    .line 475
    sget-object v3, Lcom/exteragram/messenger/plugins/pip/PipController;->pythonVersion:Ljava/lang/String;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " requires Python "

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", but current is "

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 474
    invoke-direct {v0, v1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 480
    :cond_11
    :goto_6
    sget-object v2, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    const/4 v2, 0x0

    .line 432
    invoke-static {v8, v2}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    if-eqz v10, :cond_1b

    .line 485
    invoke-virtual {v10}, Lcom/exteragram/messenger/plugins/pip/PipController$WheelCandidate;->getVersion()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/plugins/pip/PipController;->getLibPath(Ljava/lang/String;Ljava/lang/String;)Ljava/io/File;

    move-result-object v2

    .line 487
    new-instance v4, Ljava/io/File;

    invoke-direct {v0}, Lcom/exteragram/messenger/plugins/pip/PipController;->getLibsDir()Ljava/io/File;

    move-result-object v5

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v11

    new-instance v6, Ljava/lang/StringBuilder;

    const-string/jumbo v8, "tmp_"

    invoke-direct {v6, v8}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v6, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v8, "_"

    invoke-virtual {v6, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v11, v12}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-direct {v4, v5, v6}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 486
    invoke-static {v4}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object v4

    .line 488
    invoke-virtual {v4}, Lorg/simplifiles/files/SimpliDirectory;->create()Lorg/simplifiles/files/SimpliDirectory;

    move-result-object v4

    invoke-virtual {v4}, Lorg/simplifiles/files/SimpliDirectory;->getFile()Ljava/io/File;

    move-result-object v4

    .line 489
    new-instance v5, Ljava/io/File;

    const-string v6, "extracted"

    invoke-direct {v5, v4, v6}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 492
    const-string v6, "PipController: Failed to delete temp package directory"

    if-eqz v3, :cond_12

    :try_start_2
    invoke-virtual {v10}, Lcom/exteragram/messenger/plugins/pip/PipController$WheelCandidate;->getVersion()Ljava/lang/String;

    move-result-object v8

    new-instance v11, Ljava/lang/StringBuilder;

    invoke-direct {v11}, Ljava/lang/StringBuilder;-><init>()V

    const-string v12, "Downloading "

    invoke-virtual {v11, v12}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v11, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v11, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v11, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v11, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v11}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-interface {v3, v8}, Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;->onProgress(Ljava/lang/String;)V

    goto :goto_7

    :catchall_1
    move-exception v0

    move-object v1, v0

    goto/16 :goto_c

    :catch_0
    move-exception v0

    goto/16 :goto_b

    .line 494
    :cond_12
    :goto_7
    new-instance v8, Ljava/io/File;

    const-string/jumbo v11, "package.whl"

    invoke-direct {v8, v4, v11}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 496
    new-instance v11, Lokhttp3/Request$Builder;

    invoke-direct {v11}, Lokhttp3/Request$Builder;-><init>()V

    invoke-virtual {v10}, Lcom/exteragram/messenger/plugins/pip/PipController$WheelCandidate;->getDownloadUrl()Ljava/lang/String;

    move-result-object v12

    invoke-virtual {v11, v12}, Lokhttp3/Request$Builder;->url(Ljava/lang/String;)Lokhttp3/Request$Builder;

    move-result-object v11

    invoke-virtual {v11}, Lokhttp3/Request$Builder;->build()Lokhttp3/Request;

    move-result-object v11

    .line 497
    invoke-direct {v0, v11, v3}, Lcom/exteragram/messenger/plugins/pip/PipController;->executeWithRetry(Lokhttp3/Request;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)Lokhttp3/Response;

    move-result-object v11
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    .line 498
    :try_start_3
    invoke-virtual {v11}, Lokhttp3/Response;->isSuccessful()Z

    move-result v12

    if-eqz v12, :cond_19

    .line 500
    invoke-virtual {v11}, Lokhttp3/Response;->body()Lokhttp3/ResponseBody;

    move-result-object v12

    invoke-virtual {v12}, Lokhttp3/ResponseBody;->contentLength()J

    move-result-wide v12

    const-wide/32 v14, 0xfa00000

    cmp-long v17, v12, v14

    if-gtz v17, :cond_18

    .line 505
    invoke-static {v8}, Lorg/simplifiles/SimpliFiles;->file(Ljava/io/File;)Lorg/simplifiles/files/SimpliFile;

    move-result-object v18

    .line 506
    new-instance v12, Lcom/exteragram/messenger/plugins/pip/PipController$SizeLimitedInputStream;

    invoke-virtual {v11}, Lokhttp3/Response;->body()Lokhttp3/ResponseBody;

    move-result-object v13

    invoke-virtual {v13}, Lokhttp3/ResponseBody;->byteStream()Ljava/io/InputStream;

    move-result-object v13

    invoke-direct {v12, v13, v14, v15}, Lcom/exteragram/messenger/plugins/pip/PipController$SizeLimitedInputStream;-><init>(Ljava/io/InputStream;J)V

    const/16 v22, 0x2

    const/16 v23, 0x0

    const-wide/16 v20, 0x0

    move-object/from16 v19, v12

    invoke-static/range {v18 .. v23}, Lorg/simplifiles/files/SimpliFile;->writeFrom$default(Lorg/simplifiles/files/SimpliFile;Ljava/io/InputStream;JILjava/lang/Object;)Lorg/simplifiles/files/SimpliFile;
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_3

    const/4 v12, 0x0

    .line 497
    :try_start_4
    invoke-static {v11, v12}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    .line 509
    invoke-virtual {v10}, Lcom/exteragram/messenger/plugins/pip/PipController$WheelCandidate;->getExpectedSha256()Ljava/lang/String;

    move-result-object v11

    if-eqz v11, :cond_14

    .line 510
    invoke-direct {v0, v8}, Lcom/exteragram/messenger/plugins/pip/PipController;->calculateSha256(Ljava/io/File;)Ljava/lang/String;

    move-result-object v0

    .line 511
    invoke-virtual {v10}, Lcom/exteragram/messenger/plugins/pip/PipController$WheelCandidate;->getExpectedSha256()Ljava/lang/String;

    move-result-object v11

    const/4 v12, 0x1

    invoke-static {v0, v11, v12}, Lkotlin/text/StringsKt;->equals(Ljava/lang/String;Ljava/lang/String;Z)Z

    move-result v0

    if-eqz v0, :cond_13

    goto :goto_8

    .line 512
    :cond_13
    new-instance v0, Ljava/io/IOException;

    const-string v2, "Checksum mismatch"

    invoke-direct {v0, v2}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_14
    :goto_8
    if-eqz v3, :cond_15

    .line 516
    invoke-virtual {v10}, Lcom/exteragram/messenger/plugins/pip/PipController$WheelCandidate;->getVersion()Ljava/lang/String;

    move-result-object v0

    new-instance v11, Ljava/lang/StringBuilder;

    invoke-direct {v11}, Ljava/lang/StringBuilder;-><init>()V

    const-string v12, "Extracting "

    invoke-virtual {v11, v12}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v11, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v11, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v11, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v11, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v11}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-interface {v3, v0}, Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;->onProgress(Ljava/lang/String;)V

    .line 517
    :cond_15
    sget-object v0, Lorg/simplifiles/archive/ArchiveExtractionOptions;->Companion:Lorg/simplifiles/archive/ArchiveExtractionOptions$Companion;

    invoke-virtual {v0}, Lorg/simplifiles/archive/ArchiveExtractionOptions$Companion;->builder()Lorg/simplifiles/archive/ArchiveExtractionOptions$Builder;

    move-result-object v0

    .line 518
    new-instance v7, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda2;

    invoke-direct {v7, v3}, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda2;-><init>(Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)V

    invoke-virtual {v0, v7}, Lorg/simplifiles/archive/ArchiveExtractionOptions$Builder;->cancellationToken(Lorg/simplifiles/archive/CancellationToken;)Lorg/simplifiles/archive/ArchiveExtractionOptions$Builder;

    move-result-object v0

    .line 519
    sget-object v3, Lorg/simplifiles/archive/ExtractionTargetPolicy;->REPLACE:Lorg/simplifiles/archive/ExtractionTargetPolicy;

    invoke-virtual {v0, v3}, Lorg/simplifiles/archive/ArchiveExtractionOptions$Builder;->targetPolicy(Lorg/simplifiles/archive/ExtractionTargetPolicy;)Lorg/simplifiles/archive/ArchiveExtractionOptions$Builder;

    move-result-object v0

    .line 520
    invoke-virtual {v0}, Lorg/simplifiles/archive/ArchiveExtractionOptions$Builder;->build()Lorg/simplifiles/archive/ArchiveExtractionOptions;

    move-result-object v0

    .line 521
    invoke-static {v8}, Lorg/simplifiles/SimpliFiles;->archive(Ljava/io/File;)Lorg/simplifiles/archive/ArchiveSource;

    move-result-object v3

    .line 522
    sget-object v7, Lcom/exteragram/messenger/plugins/pip/PipController;->wheelArchivePolicy:Lorg/simplifiles/archive/security/SecurityPolicy;

    invoke-virtual {v3, v7}, Lorg/simplifiles/archive/ArchiveSource;->withPolicy(Lorg/simplifiles/archive/security/SecurityPolicy;)Lorg/simplifiles/archive/ArchiveSource;

    move-result-object v3

    .line 523
    invoke-virtual {v3, v5, v0}, Lorg/simplifiles/archive/ArchiveSource;->extractToDirectory(Ljava/io/File;Lorg/simplifiles/archive/ArchiveExtractionOptions;)Lorg/simplifiles/files/SimpliDirectory;

    .line 524
    invoke-virtual {v2}, Ljava/io/File;->exists()Z

    move-result v0

    if-eqz v0, :cond_16

    .line 525
    invoke-static {v2}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object v0

    invoke-virtual {v0}, Lorg/simplifiles/files/SimpliDirectory;->deleteRecursively()Z

    .line 527
    :cond_16
    invoke-static {v5}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object v0

    sget-object v3, Lorg/simplifiles/files/OverwritePolicy;->REPLACE:Lorg/simplifiles/files/OverwritePolicy;

    invoke-virtual {v0, v2, v3}, Lorg/simplifiles/files/SimpliDirectory;->moveTo(Ljava/io/File;Lorg/simplifiles/files/OverwritePolicy;)Lorg/simplifiles/files/SimpliDirectory;
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_0
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    .line 533
    :try_start_5
    sget-object v0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 534
    invoke-static {v4}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object v0

    invoke-virtual {v0}, Lorg/simplifiles/files/SimpliDirectory;->deleteRecursively()Z

    move-result v0

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    .line 533
    invoke-static {v0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_2

    goto :goto_9

    :catchall_2
    move-exception v0

    sget-object v1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {v0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object v0

    invoke-static {v0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 535
    :goto_9
    invoke-static {v0}, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;

    move-result-object v0

    if-eqz v0, :cond_17

    .line 536
    invoke-static {v6, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 540
    :cond_17
    invoke-virtual {v10}, Lcom/exteragram/messenger/plugins/pip/PipController$WheelCandidate;->getVersion()Ljava/lang/String;

    move-result-object v0

    return-object v0

    :catchall_3
    move-exception v0

    move-object v2, v0

    goto :goto_a

    .line 502
    :cond_18
    :try_start_6
    new-instance v0, Ljava/io/IOException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Wheel for "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, " is too large: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v12, v13}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v3, " bytes (limit 262144000)"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v0, v2}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 498
    :cond_19
    new-instance v0, Ljava/io/IOException;

    invoke-virtual {v11}, Lokhttp3/Response;->code()I

    move-result v2

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "Download failed: "

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v0, v2}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_3

    .line 497
    :goto_a
    :try_start_7
    throw v2
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_4

    :catchall_4
    move-exception v0

    :try_start_8
    invoke-static {v11, v2}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    throw v0
    :try_end_8
    .catch Ljava/lang/Exception; {:try_start_8 .. :try_end_8} :catch_0
    .catchall {:try_start_8 .. :try_end_8} :catchall_1

    .line 530
    :goto_b
    :try_start_9
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "PipController: Failed to download/install "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 531
    throw v0
    :try_end_9
    .catchall {:try_start_9 .. :try_end_9} :catchall_1

    .line 533
    :goto_c
    :try_start_a
    sget-object v0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 534
    invoke-static {v4}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object v0

    invoke-virtual {v0}, Lorg/simplifiles/files/SimpliDirectory;->deleteRecursively()Z

    move-result v0

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    .line 533
    invoke-static {v0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0
    :try_end_a
    .catchall {:try_start_a .. :try_end_a} :catchall_5

    goto :goto_d

    :catchall_5
    move-exception v0

    sget-object v2, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {v0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object v0

    invoke-static {v0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 535
    :goto_d
    invoke-static {v0}, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;

    move-result-object v0

    if-eqz v0, :cond_1a

    .line 536
    invoke-static {v6, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 535
    :cond_1a
    throw v1

    .line 483
    :cond_1b
    const-string v0, "No pure-Python wheel found for "

    const-string v2, ". Binary packages are not supported."

    invoke-static {v0, v1, v2}, Lcom/sun/jna/Native$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V

    const/16 v16, 0x0

    return-object v16

    .line 443
    :cond_1c
    :try_start_b
    new-instance v0, Ljava/io/IOException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " on PyPI"

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 440
    :cond_1d
    new-instance v0, Ljava/io/IOException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_b
    .catchall {:try_start_b .. :try_end_b} :catchall_0

    .line 432
    :goto_e
    :try_start_c
    throw v1
    :try_end_c
    .catchall {:try_start_c .. :try_end_c} :catchall_6

    :catchall_6
    move-exception v0

    invoke-static {v8, v1}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    throw v0
.end method

.method private final isMarkerCompatible(Ljava/lang/String;)Z
    .locals 0

    .line 724
    new-instance p0, Lcom/exteragram/messenger/plugins/pip/PipController$MarkerParser;

    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/pip/PipController$MarkerParser;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/pip/PipController$MarkerParser;->parse()Z

    move-result p0

    return p0
.end method

.method private final isPreReleaseVersion(Ljava/lang/String;)Z
    .locals 2

    .line 969
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/pip/PipController;->parseVersion(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->getParts()Ljava/util/List;

    move-result-object p0

    check-cast p0, Ljava/lang/Iterable;

    .line 1807
    instance-of p1, p0, Ljava/util/Collection;

    const/4 v0, 0x0

    if-eqz p1, :cond_0

    move-object p1, p0

    check-cast p1, Ljava/util/Collection;

    invoke-interface {p1}, Ljava/util/Collection;->isEmpty()Z

    move-result p1

    if-eqz p1, :cond_0

    return v0

    .line 1808
    :cond_0
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_1
    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_3

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    .line 970
    invoke-virtual {p1}, Ljava/lang/String;->hashCode()I

    move-result v1

    sparse-switch v1, :sswitch_data_0

    goto :goto_0

    :sswitch_0
    const-string v1, "alpha"

    invoke-virtual {p1, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_2

    goto :goto_0

    :sswitch_1
    const-string v1, "beta"

    invoke-virtual {p1, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_1

    goto :goto_1

    :sswitch_2
    const-string v1, "dev"

    invoke-virtual {p1, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_2

    goto :goto_0

    :sswitch_3
    const-string/jumbo v1, "rc"

    invoke-virtual {p1, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_2

    goto :goto_0

    :sswitch_4
    const-string v1, "c"

    invoke-virtual {p1, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_2

    goto :goto_0

    :sswitch_5
    const-string v1, "b"

    invoke-virtual {p1, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_2

    goto :goto_0

    :sswitch_6
    const-string v1, "a"

    invoke-virtual {p1, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-nez p1, :cond_2

    goto :goto_0

    :cond_2
    :goto_1
    const/4 p0, 0x1

    return p0

    :cond_3
    return v0

    :sswitch_data_0
    .sparse-switch
        0x61 -> :sswitch_6
        0x62 -> :sswitch_5
        0x63 -> :sswitch_4
        0xe31 -> :sswitch_3
        0x18415 -> :sswitch_2
        0x2e15f0 -> :sswitch_1
        0x589b15e -> :sswitch_0
    .end sparse-switch
.end method

.method private final isPurePythonWheelCompatible(Ljava/lang/String;)Z
    .locals 9

    const/4 p0, 0x0

    .line 581
    const-string v0, ".whl"

    const/4 v1, 0x0

    const/4 v2, 0x2

    invoke-static {p1, v0, v1, v2, p0}, Lkotlin/text/StringsKt;->endsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_0

    return v1

    .line 583
    :cond_0
    invoke-static {p1, v0}, Lkotlin/text/StringsKt;->removeSuffix(Ljava/lang/String;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v3

    const-string p0, "-"

    filled-new-array {p0}, [Ljava/lang/String;

    move-result-object v4

    const/4 v7, 0x6

    const/4 v8, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    invoke-static/range {v3 .. v8}, Lkotlin/text/StringsKt;->split$default(Ljava/lang/CharSequence;[Ljava/lang/String;ZIILjava/lang/Object;)Ljava/util/List;

    move-result-object p0

    .line 584
    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result p1

    const/4 v0, 0x5

    if-ge p1, v0, :cond_1

    return v1

    .line 586
    :cond_1
    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result p1

    add-int/lit8 p1, p1, -0x3

    invoke-interface {p0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    move-object v3, p1

    check-cast v3, Ljava/lang/String;

    .line 587
    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result p1

    sub-int/2addr p1, v2

    invoke-interface {p0, p1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    .line 588
    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result v0

    const/4 v2, 0x1

    sub-int/2addr v0, v2

    invoke-interface {p0, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/String;

    .line 590
    const-string/jumbo v0, "none"

    invoke-static {p1, v0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_5

    const-string p1, "any"

    invoke-static {p0, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_2

    goto :goto_0

    .line 591
    :cond_2
    const-string p0, "."

    filled-new-array {p0}, [Ljava/lang/String;

    move-result-object v4

    const/4 v7, 0x6

    const/4 v8, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    invoke-static/range {v3 .. v8}, Lkotlin/text/StringsKt;->split$default(Ljava/lang/CharSequence;[Ljava/lang/String;ZIILjava/lang/Object;)Ljava/util/List;

    move-result-object p0

    check-cast p0, Ljava/lang/Iterable;

    .line 1807
    instance-of p1, p0, Ljava/util/Collection;

    if-eqz p1, :cond_3

    move-object p1, p0

    check-cast p1, Ljava/util/Collection;

    invoke-interface {p1}, Ljava/util/Collection;->isEmpty()Z

    move-result p1

    if-eqz p1, :cond_3

    return v1

    .line 1808
    :cond_3
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_4
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_5

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    .line 591
    sget-object v0, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-direct {v0, p1}, Lcom/exteragram/messenger/plugins/pip/PipController;->isPythonTagCompatible(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_4

    return v2

    :cond_5
    :goto_0
    return v1
.end method

.method private final isPythonTagCompatible(Ljava/lang/String;)Z
    .locals 6

    .line 595
    sget-object v0, Lcom/exteragram/messenger/plugins/pip/PipController;->pythonVersion:Ljava/lang/String;

    const-string p0, "."

    filled-new-array {p0}, [Ljava/lang/String;

    move-result-object v1

    const/4 v4, 0x6

    const/4 v5, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-static/range {v0 .. v5}, Lkotlin/text/StringsKt;->split$default(Ljava/lang/CharSequence;[Ljava/lang/String;ZIILjava/lang/Object;)Ljava/util/List;

    move-result-object p0

    const/4 v0, 0x0

    .line 596
    invoke-static {p0, v0}, Lkotlin/collections/CollectionsKt;->getOrNull(Ljava/util/List;I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    if-eqz v1, :cond_2

    invoke-static {v1}, Lkotlin/text/StringsKt;->toIntOrNull(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v1

    if-eqz v1, :cond_2

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    const/4 v2, 0x1

    .line 597
    invoke-static {p0, v2}, Lkotlin/collections/CollectionsKt;->getOrNull(Ljava/util/List;I)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/String;

    if-eqz p0, :cond_2

    invoke-static {p0}, Lkotlin/text/StringsKt;->toIntOrNull(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object p0

    if-eqz p0, :cond_2

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    .line 598
    sget-object v3, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-virtual {p1, v3}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p1

    .line 600
    new-instance v3, Ljava/lang/StringBuilder;

    const-string/jumbo v4, "py"

    invoke-direct {v3, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {p1, v3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_0

    return v2

    .line 601
    :cond_0
    new-instance v3, Ljava/lang/StringBuilder;

    const-string v5, "cp"

    invoke-direct {v3, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {p1, v3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    return v2

    .line 603
    :cond_1
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {p1, v1}, Lkotlin/text/StringsKt;->removePrefix(Ljava/lang/String;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lkotlin/text/StringsKt;->toIntOrNull(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object p1

    if-eqz p1, :cond_2

    .line 604
    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    if-gt p1, p0, :cond_2

    return v2

    :cond_2
    return v0
.end method

.method private final isWildcardVersionSpec(Ljava/lang/String;)Z
    .locals 0

    .line 949
    sget-object p0, Lcom/exteragram/messenger/plugins/pip/PipController;->REGEX_VERSION_WILDCARD:Lkotlin/text/Regex;

    invoke-static {p1}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lkotlin/text/Regex;->containsMatchIn(Ljava/lang/CharSequence;)Z

    move-result p0

    return p0
.end method

.method private final declared-synchronized loadRegistry()V
    .locals 9

    monitor-enter p0

    .line 107
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/pip/PipController;->getRegistryFile()Ljava/io/File;

    move-result-object v0

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v0

    if-nez v0, :cond_0

    .line 108
    sget-object v0, Lcom/exteragram/messenger/plugins/pip/PipController;->registry:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentHashMap;->clear()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 109
    monitor-exit p0

    return-void

    :catchall_0
    move-exception v0

    goto/16 :goto_4

    .line 113
    :cond_0
    :try_start_1
    new-instance v0, Lcom/exteragram/messenger/plugins/pip/PipController$loadRegistry$type$1;

    invoke-direct {v0}, Lcom/exteragram/messenger/plugins/pip/PipController$loadRegistry$type$1;-><init>()V

    invoke-virtual {v0}, Lcom/google/gson/reflect/TypeToken;->getType()Ljava/lang/reflect/Type;

    move-result-object v0

    .line 115
    sget-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->gson:Lcom/google/gson/Gson;

    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/pip/PipController;->getRegistryFile()Ljava/io/File;

    move-result-object v2

    invoke-static {v2}, Lorg/simplifiles/SimpliFiles;->file(Ljava/io/File;)Lorg/simplifiles/files/SimpliFile;

    move-result-object v3

    const/4 v7, 0x2

    const/4 v8, 0x0

    const-wide/32 v4, 0x400000

    const/4 v6, 0x0

    invoke-static/range {v3 .. v8}, Lorg/simplifiles/files/SimpliFile;->readText$default(Lorg/simplifiles/files/SimpliFile;JLjava/nio/charset/Charset;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2, v0}, Lcom/google/gson/Gson;->fromJson(Ljava/lang/String;Ljava/lang/reflect/Type;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Map;

    .line 117
    sget-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->registry:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v1}, Ljava/util/concurrent/ConcurrentHashMap;->clear()V

    .line 221
    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map;

    .line 119
    sget-object v3, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-direct {v3, v2}, Lcom/exteragram/messenger/plugins/pip/PipController;->normalizePackageName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 120
    new-instance v3, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v3}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    .line 221
    invoke-interface {v1}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_1
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/util/Map$Entry;

    invoke-interface {v4}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/String;

    invoke-interface {v4}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/util/Set;

    .line 122
    invoke-static {}, Ljava/util/concurrent/ConcurrentHashMap;->newKeySet()Ljava/util/concurrent/ConcurrentHashMap$KeySetView;

    move-result-object v6

    .line 123
    invoke-virtual {v6, v4}, Ljava/util/concurrent/ConcurrentHashMap$KeySetView;->addAll(Ljava/util/Collection;)Z

    .line 122
    invoke-interface {v3, v5, v6}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_1

    :catch_0
    move-exception v0

    goto :goto_2

    .line 126
    :cond_1
    sget-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->registry:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-interface {v1, v2, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_0

    .line 129
    :goto_2
    :try_start_2
    const-string v1, "PipController: Failed to load registry"

    invoke-static {v1, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 130
    sget-object v0, Lcom/exteragram/messenger/plugins/pip/PipController;->registry:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentHashMap;->clear()V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 131
    :try_start_3
    sget-object v0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 132
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/pip/PipController;->getRegistryFile()Ljava/io/File;

    move-result-object v0

    invoke-static {v0}, Lorg/simplifiles/SimpliFiles;->file(Ljava/io/File;)Lorg/simplifiles/files/SimpliFile;

    move-result-object v0

    .line 133
    new-instance v1, Ljava/io/File;

    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/pip/PipController;->getLibsDir()Ljava/io/File;

    move-result-object v2

    const-string/jumbo v3, "registry.json.bak"

    invoke-direct {v1, v2, v3}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 134
    sget-object v2, Lorg/simplifiles/files/OverwritePolicy;->REPLACE:Lorg/simplifiles/files/OverwritePolicy;

    .line 132
    invoke-virtual {v0, v1, v2}, Lorg/simplifiles/files/SimpliFile;->moveTo(Ljava/io/File;Lorg/simplifiles/files/OverwritePolicy;)Lorg/simplifiles/files/SimpliFile;

    move-result-object v0

    .line 131
    invoke-static {v0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    goto :goto_3

    :catchall_1
    move-exception v0

    :try_start_4
    sget-object v1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {v0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object v0

    invoke-static {v0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    .line 136
    :goto_3
    invoke-static {v0}, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;

    move-result-object v0

    if-eqz v0, :cond_2

    .line 137
    const-string v1, "PipController: Failed to backup invalid registry"

    invoke-static {v1, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    .line 140
    :cond_2
    monitor-exit p0

    return-void

    :goto_4
    :try_start_5
    monitor-exit p0
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    throw v0
.end method

.method private final matchesVersionWildcard(Ljava/lang/String;Ljava/lang/String;)Z
    .locals 6

    .line 978
    invoke-static {p2}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p2

    .line 979
    const-string v0, "+"

    const/4 v1, 0x0

    const/4 v2, 0x2

    invoke-static {p2, v0, v1, v2, v1}, Lkotlin/text/StringsKt;->substringBefore$default(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object p2

    .line 980
    const-string v0, ".*"

    invoke-static {p2, v0}, Lkotlin/text/StringsKt;->removeSuffix(Ljava/lang/String;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p2

    .line 981
    const-string v0, "-*"

    invoke-static {p2, v0}, Lkotlin/text/StringsKt;->removeSuffix(Ljava/lang/String;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p2

    .line 982
    const-string v0, "_*"

    invoke-static {p2, v0}, Lkotlin/text/StringsKt;->removeSuffix(Ljava/lang/String;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p2

    const/4 v0, 0x3

    .line 983
    new-array v0, v0, [C

    fill-array-data v0, :array_0

    invoke-static {p2, v0}, Lkotlin/text/StringsKt;->trimEnd(Ljava/lang/String;[C)Ljava/lang/String;

    move-result-object p2

    .line 984
    invoke-direct {p0, p2}, Lcom/exteragram/messenger/plugins/pip/PipController;->parseVersion(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;

    move-result-object v0

    .line 985
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/pip/PipController;->parseVersion(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;

    move-result-object v3

    .line 986
    const-string v4, "!"

    const/4 v5, 0x0

    invoke-static {p2, v4, v5, v2, v1}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->getEpoch()I

    move-result v0

    invoke-virtual {v3}, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->getEpoch()I

    move-result v1

    if-eq v0, v1, :cond_0

    return v5

    .line 990
    :cond_0
    invoke-direct {p0, p2}, Lcom/exteragram/messenger/plugins/pip/PipController;->parseVersionReleaseParts(Ljava/lang/String;)Ljava/util/List;

    move-result-object p2

    .line 991
    invoke-interface {p2}, Ljava/util/List;->isEmpty()Z

    move-result v0

    const/4 v1, 0x1

    if-eqz v0, :cond_1

    return v1

    .line 993
    :cond_1
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/pip/PipController;->parseVersionReleaseParts(Ljava/lang/String;)Ljava/util/List;

    move-result-object p0

    .line 994
    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result p1

    invoke-interface {p2}, Ljava/util/List;->size()I

    move-result v0

    if-ge p1, v0, :cond_2

    return v5

    .line 998
    :cond_2
    move-object p1, p2

    check-cast p1, Ljava/util/Collection;

    invoke-static {p1}, Lkotlin/collections/CollectionsKt;->getIndices(Ljava/util/Collection;)Lkotlin/ranges/IntRange;

    move-result-object p1

    .line 1786
    instance-of v0, p1, Ljava/util/Collection;

    if-eqz v0, :cond_3

    move-object v0, p1

    check-cast v0, Ljava/util/Collection;

    invoke-interface {v0}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_3

    return v1

    .line 1787
    :cond_3
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_5

    move-object v0, p1

    check-cast v0, Lkotlin/collections/IntIterator;

    invoke-virtual {v0}, Lkotlin/collections/IntIterator;->nextInt()I

    move-result v0

    .line 998
    invoke-interface {p0, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Number;

    invoke-virtual {v2}, Ljava/lang/Number;->intValue()I

    move-result v2

    invoke-interface {p2, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Number;

    invoke-virtual {v0}, Ljava/lang/Number;->intValue()I

    move-result v0

    if-ne v2, v0, :cond_4

    goto :goto_0

    :cond_4
    return v5

    :cond_5
    return v1

    :array_0
    .array-data 2
        0x2es
        0x2ds
        0x5fs
    .end array-data
.end method

.method private final normalizePackageName(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    .line 102
    sget-object p0, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-virtual {p1, p0}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p0

    sget-object p1, Lcom/exteragram/messenger/plugins/pip/PipController;->REGEX_NORMALIZE:Lkotlin/text/Regex;

    const-string v0, "-"

    invoke-virtual {p1, p0, v0}, Lkotlin/text/Regex;->replace(Ljava/lang/CharSequence;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private final parseDependenciesFromMetadata(Ljava/io/File;)Ljava/util/List;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/io/File;",
            ")",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 698
    new-instance p0, Ljava/util/ArrayList;

    invoke-direct {p0}, Ljava/util/ArrayList;-><init>()V

    .line 700
    :try_start_0
    invoke-static {p1}, Lorg/simplifiles/SimpliFiles;->file(Ljava/io/File;)Lorg/simplifiles/files/SimpliFile;

    move-result-object v0

    new-instance v4, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda10;

    invoke-direct {v4, p0}, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda10;-><init>(Ljava/util/List;)V

    const/4 v5, 0x2

    const/4 v6, 0x0

    const-wide/32 v1, 0x400000

    const/4 v3, 0x0

    invoke-static/range {v0 .. v6}, Lorg/simplifiles/files/SimpliFile;->forEachLine$default(Lorg/simplifiles/files/SimpliFile;JLjava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception v0

    move-object p1, v0

    .line 718
    const-string v0, "PipController: Failed to parse metadata"

    invoke-static {v0, p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-object p0
.end method

.method private final parseRequirement(Ljava/lang/String;)Lkotlin/Pair;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")",
            "Lkotlin/Pair<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Lkotlin/Pair<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;>;>;"
        }
    .end annotation

    .line 671
    const-string v0, ";"

    filled-new-array {v0}, [Ljava/lang/String;

    move-result-object v2

    const/4 v5, 0x6

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    move-object v1, p1

    invoke-static/range {v1 .. v6}, Lkotlin/text/StringsKt;->split$default(Ljava/lang/CharSequence;[Ljava/lang/String;ZIILjava/lang/Object;)Ljava/util/List;

    move-result-object p1

    const/4 v0, 0x0

    invoke-interface {p1, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    invoke-static {p1}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    .line 672
    sget-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->REGEX_REQ_EXTRA:Lkotlin/text/Regex;

    const-string v2, ""

    invoke-virtual {v1, p1, v2}, Lkotlin/text/Regex;->replace(Ljava/lang/CharSequence;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    .line 673
    sget-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->REGEX_REQ_PAREN:Lkotlin/text/Regex;

    invoke-virtual {v1, p1, v2}, Lkotlin/text/Regex;->replace(Ljava/lang/CharSequence;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p1

    .line 675
    sget-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->REGEX_REQ_PARSE:Lkotlin/text/Regex;

    const/4 v2, 0x0

    const/4 v3, 0x2

    invoke-static {v1, p1, v0, v3, v2}, Lkotlin/text/Regex;->find$default(Lkotlin/text/Regex;Ljava/lang/CharSequence;IILjava/lang/Object;)Lkotlin/text/MatchResult;

    move-result-object v0

    if-nez v0, :cond_0

    invoke-static {}, Lkotlin/collections/CollectionsKt;->emptyList()Ljava/util/List;

    move-result-object p0

    invoke-static {p1, p0}, Lkotlin/TuplesKt;->to(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;

    move-result-object p0

    return-object p0

    .line 676
    :cond_0
    invoke-interface {v0}, Lkotlin/text/MatchResult;->getDestructured()Lkotlin/text/MatchResult$Destructured;

    move-result-object p1

    invoke-virtual {p1}, Lkotlin/text/MatchResult$Destructured;->getMatch()Lkotlin/text/MatchResult;

    move-result-object v0

    invoke-interface {v0}, Lkotlin/text/MatchResult;->getGroupValues()Ljava/util/List;

    move-result-object v0

    const/4 v1, 0x1

    invoke-interface {v0, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    invoke-virtual {p1}, Lkotlin/text/MatchResult$Destructured;->getMatch()Lkotlin/text/MatchResult;

    move-result-object p1

    invoke-interface {p1}, Lkotlin/text/MatchResult;->getGroupValues()Ljava/util/List;

    move-result-object p1

    invoke-interface {p1, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    .line 678
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/pip/PipController;->parseSpecs(Ljava/lang/String;)Ljava/util/List;

    move-result-object p0

    invoke-static {v0, p0}, Lkotlin/TuplesKt;->to(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;

    move-result-object p0

    return-object p0
.end method

.method private final parseSpecs(Ljava/lang/String;)Ljava/util/List;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")",
            "Ljava/util/List<",
            "Lkotlin/Pair<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;>;"
        }
    .end annotation

    .line 682
    new-instance p0, Ljava/util/ArrayList;

    invoke-direct {p0}, Ljava/util/ArrayList;-><init>()V

    .line 683
    invoke-static {p1}, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 684
    const-string v0, ","

    filled-new-array {v0}, [Ljava/lang/String;

    move-result-object v2

    const/4 v5, 0x6

    const/4 v6, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    move-object v1, p1

    invoke-static/range {v1 .. v6}, Lkotlin/text/StringsKt;->split$default(Ljava/lang/CharSequence;[Ljava/lang/String;ZIILjava/lang/Object;)Ljava/util/List;

    move-result-object p1

    .line 685
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_0
    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 686
    invoke-static {v0}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    .line 687
    sget-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->REGEX_REQ_SPECS:Lkotlin/text/Regex;

    const/4 v2, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x2

    invoke-static {v1, v0, v2, v4, v3}, Lkotlin/text/Regex;->find$default(Lkotlin/text/Regex;Ljava/lang/CharSequence;IILjava/lang/Object;)Lkotlin/text/MatchResult;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 689
    invoke-interface {v0}, Lkotlin/text/MatchResult;->getDestructured()Lkotlin/text/MatchResult$Destructured;

    move-result-object v0

    invoke-virtual {v0}, Lkotlin/text/MatchResult$Destructured;->getMatch()Lkotlin/text/MatchResult;

    move-result-object v1

    invoke-interface {v1}, Lkotlin/text/MatchResult;->getGroupValues()Ljava/util/List;

    move-result-object v1

    const/4 v2, 0x1

    invoke-interface {v1, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    invoke-virtual {v0}, Lkotlin/text/MatchResult$Destructured;->getMatch()Lkotlin/text/MatchResult;

    move-result-object v0

    invoke-interface {v0}, Lkotlin/text/MatchResult;->getGroupValues()Ljava/util/List;

    move-result-object v0

    invoke-interface {v0, v4}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 690
    invoke-static {v0}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v1, v0}, Lkotlin/TuplesKt;->to(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;

    move-result-object v0

    invoke-interface {p0, v0}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_1
    return-object p0
.end method

.method private final parseVersion(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;
    .locals 8

    .line 1049
    invoke-static {p1}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    sget-object p1, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-virtual {p0, p1}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p0

    const-string/jumbo p1, "v"

    invoke-static {p0, p1}, Lkotlin/text/StringsKt;->removePrefix(Ljava/lang/String;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p0

    .line 1050
    const-string p1, "+"

    const/4 v0, 0x0

    const/4 v1, 0x2

    invoke-static {p0, p1, v0, v1, v0}, Lkotlin/text/StringsKt;->substringBefore$default(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    .line 1051
    const-string p0, "!"

    filled-new-array {p0}, [Ljava/lang/String;

    move-result-object v3

    const/4 v6, 0x2

    const/4 v7, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x2

    invoke-static/range {v2 .. v7}, Lkotlin/text/StringsKt;->split$default(Ljava/lang/CharSequence;[Ljava/lang/String;ZIILjava/lang/Object;)Ljava/util/List;

    move-result-object p0

    .line 1052
    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result p1

    const/4 v2, 0x0

    if-ne p1, v1, :cond_0

    invoke-interface {p0, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    invoke-static {p1}, Lkotlin/text/StringsKt;->toIntOrNull(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object p1

    if-eqz p1, :cond_0

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result p1

    goto :goto_0

    :cond_0
    move p1, v2

    .line 1053
    :goto_0
    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result v3

    if-ne v3, v1, :cond_1

    const/4 v3, 0x1

    invoke-interface {p0, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    :goto_1
    check-cast p0, Ljava/lang/String;

    goto :goto_2

    :cond_1
    invoke-interface {p0, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object p0

    goto :goto_1

    .line 1054
    :goto_2
    sget-object v3, Lcom/exteragram/messenger/plugins/pip/PipController;->REGEX_VERSION_SPLIT:Lkotlin/text/Regex;

    invoke-static {v3, p0, v2, v1, v0}, Lkotlin/text/Regex;->findAll$default(Lkotlin/text/Regex;Ljava/lang/CharSequence;IILjava/lang/Object;)Lkotlin/sequences/Sequence;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda13;

    invoke-direct {v1}, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda13;-><init>()V

    invoke-static {v0, v1}, Lkotlin/sequences/SequencesKt;->map(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;

    move-result-object v0

    invoke-static {v0}, Lkotlin/sequences/SequencesKt;->toList(Lkotlin/sequences/Sequence;)Ljava/util/List;

    move-result-object v0

    .line 1055
    new-instance v1, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;

    invoke-direct {v1, p1, p0, v0}, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;-><init>(ILjava/lang/String;Ljava/util/List;)V

    return-object v1
.end method

.method private final parseVersionReleaseParts(Ljava/lang/String;)Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")",
            "Ljava/util/List<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation

    .line 1002
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/pip/PipController;->parseVersion(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/pip/PipController$ParsedVersion;->getPublicVersion()Ljava/lang/String;

    move-result-object p0

    .line 1003
    new-instance p1, Lkotlin/text/Regex;

    const-string v0, "[.\\-_]"

    invoke-direct {p1, v0}, Lkotlin/text/Regex;-><init>(Ljava/lang/String;)V

    const/4 v0, 0x0

    invoke-virtual {p1, p0, v0}, Lkotlin/text/Regex;->split(Ljava/lang/CharSequence;I)Ljava/util/List;

    move-result-object p0

    check-cast p0, Ljava/lang/Iterable;

    .line 1642
    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    .line 1915
    invoke-interface {p0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_0
    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    .line 1651
    check-cast v0, Ljava/lang/String;

    .line 1004
    invoke-static {v0}, Lkotlin/text/StringsKt;->toIntOrNull(Ljava/lang/String;)Ljava/lang/Integer;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 1651
    invoke-interface {p1, v0}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :cond_1
    return-object p1
.end method

.method private final removeOrphanedDirectories()V
    .locals 7

    .line 348
    new-instance v0, Ljava/util/LinkedHashSet;

    invoke-direct {v0}, Ljava/util/LinkedHashSet;-><init>()V

    .line 349
    sget-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->registry:Ljava/util/concurrent/ConcurrentHashMap;

    .line 221
    invoke-interface {v1}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/Map$Entry;

    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/concurrent/ConcurrentHashMap;

    .line 350
    invoke-virtual {v2}, Ljava/util/concurrent/ConcurrentHashMap;->keySet()Ljava/util/Set;

    move-result-object v2

    .line 1915
    invoke-interface {v2}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :catch_0
    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_0

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    .line 352
    :try_start_0
    sget-object v5, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-direct {v5, v3, v4}, Lcom/exteragram/messenger/plugins/pip/PipController;->getLibPath(Ljava/lang/String;Ljava/lang/String;)Ljava/io/File;

    move-result-object v4

    invoke-virtual {v4}, Ljava/io/File;->getCanonicalPath()Ljava/lang/String;

    move-result-object v4

    invoke-interface {v0, v4}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 358
    :cond_1
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/pip/PipController;->getLibsDir()Ljava/io/File;

    move-result-object p0

    invoke-virtual {p0}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object p0

    if-eqz p0, :cond_3

    .line 14048
    array-length v1, p0

    const/4 v2, 0x0

    :goto_1
    if-ge v2, v1, :cond_3

    aget-object v3, p0, v2

    .line 359
    invoke-virtual {v3}, Ljava/io/File;->isDirectory()Z

    move-result v4

    if-eqz v4, :cond_2

    .line 361
    :try_start_1
    invoke-virtual {v3}, Ljava/io/File;->getCanonicalPath()Ljava/lang/String;

    move-result-object v4

    invoke-interface {v0, v4}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_2

    .line 362
    invoke-virtual {v3}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v4

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "PipController: Removing orphaned library: "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v4}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    .line 363
    invoke-static {v3}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object v3

    invoke-virtual {v3}, Lorg/simplifiles/files/SimpliDirectory;->deleteRecursively()Z
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_2

    :catch_1
    move-exception v3

    .line 366
    invoke-static {v3}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    :cond_2
    :goto_2
    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    :cond_3
    return-void
.end method

.method private final resolveAndInstall(Ljava/lang/String;Ljava/util/List;Ljava/util/Set;Ljava/lang/String;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)V
    .locals 21
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Lkotlin/Pair<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;>;",
            "Ljava/util/Set<",
            "Lkotlin/Pair<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;>;",
            "Ljava/lang/String;",
            "Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;",
            ")V"
        }
    .end annotation

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    move-object/from16 v3, p3

    move-object/from16 v4, p4

    move-object/from16 v5, p5

    const/4 v6, 0x1

    if-eqz v5, :cond_1

    .line 229
    invoke-interface {v5}, Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;->isCancelled()Z

    move-result v7

    if-eq v7, v6, :cond_0

    goto :goto_0

    :cond_0
    const-string v0, "Installation cancelled"

    invoke-static {v0}, Lorg/vosk/Model$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void

    .line 231
    :cond_1
    :goto_0
    sget-object v7, Lcom/exteragram/messenger/plugins/pip/PipController;->installLocks:Ljava/util/concurrent/ConcurrentHashMap;

    new-instance v8, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda3;

    invoke-direct {v8}, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda3;-><init>()V

    new-instance v9, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda4;

    invoke-direct {v9, v8}, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda4;-><init>(Lkotlin/jvm/functions/Function1;)V

    invoke-virtual {v7, v1, v9}, Ljava/util/concurrent/ConcurrentHashMap;->computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;

    move-result-object v7

    .line 233
    monitor-enter v7

    if-eqz v5, :cond_3

    .line 234
    :try_start_0
    invoke-interface {v5}, Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;->isCancelled()Z

    move-result v8

    if-eq v8, v6, :cond_2

    goto :goto_1

    :cond_2
    new-instance v0, Ljava/io/IOException;

    const-string v1, "Installation cancelled"

    invoke-direct {v0, v1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0

    :catchall_0
    move-exception v0

    goto/16 :goto_8

    .line 236
    :cond_3
    :goto_1
    sget-object v8, Lcom/exteragram/messenger/plugins/pip/PipController;->registry:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v8, v1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Ljava/util/concurrent/ConcurrentHashMap;

    const/4 v9, 0x0

    if-eqz v8, :cond_6

    invoke-virtual {v8}, Ljava/util/concurrent/ConcurrentHashMap;->entrySet()Ljava/util/Set;

    move-result-object v8

    if-eqz v8, :cond_6

    .line 296
    invoke-interface {v8}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v8

    :cond_4
    invoke-interface {v8}, Ljava/util/Iterator;->hasNext()Z

    move-result v10

    if-eqz v10, :cond_5

    invoke-interface {v8}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v10

    move-object v11, v10

    check-cast v11, Ljava/util/Map$Entry;

    .line 236
    invoke-interface {v11}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v11

    check-cast v11, Ljava/util/Collection;

    invoke-interface {v11}, Ljava/util/Collection;->isEmpty()Z

    move-result v11

    if-nez v11, :cond_4

    goto :goto_2

    :cond_5
    move-object v10, v9

    .line 297
    :goto_2
    check-cast v10, Ljava/util/Map$Entry;

    goto :goto_3

    :cond_6
    move-object v10, v9

    :goto_3
    if-eqz v10, :cond_9

    .line 238
    invoke-interface {v10}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Ljava/lang/String;

    .line 239
    invoke-interface {v10}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v10

    move-object v11, v10

    check-cast v11, Ljava/lang/Iterable;

    const-string v12, ", "

    const/16 v18, 0x3e

    const/16 v19, 0x0

    const/4 v13, 0x0

    const/4 v14, 0x0

    const/4 v15, 0x0

    const/16 v16, 0x0

    const/16 v17, 0x0

    invoke-static/range {v11 .. v19}, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v10

    .line 241
    sget-object v11, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-direct {v11, v8, v2}, Lcom/exteragram/messenger/plugins/pip/PipController;->checkVersionSatisfies(Ljava/lang/String;Ljava/util/List;)Z

    move-result v11

    if-eqz v11, :cond_7

    .line 242
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v11, "PipController: Reusing active version "

    invoke-virtual {v2, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v11, " of "

    invoke-virtual {v2, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v11, " (used by "

    invoke-virtual {v2, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v10, ") for "

    invoke-virtual {v2, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    goto/16 :goto_5

    .line 246
    :cond_7
    move-object v0, v2

    check-cast v0, Ljava/util/Collection;

    invoke-interface {v0}, Ljava/util/Collection;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_8

    move-object v11, v2

    check-cast v11, Ljava/lang/Iterable;

    const-string v12, ","

    new-instance v17, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda5;

    invoke-direct/range {v17 .. v17}, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda5;-><init>()V

    const/16 v18, 0x1e

    const/16 v19, 0x0

    const/4 v13, 0x0

    const/4 v14, 0x0

    const/4 v15, 0x0

    const/16 v16, 0x0

    invoke-static/range {v11 .. v19}, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    goto :goto_4

    :cond_8
    const-string v0, "(latest)"

    .line 247
    :goto_4
    new-instance v2, Ljava/io/IOException;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "Dependency conflict for \'"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "\': Active version "

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " (used by "

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ") does not satisfy requirement \'"

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, "\'."

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {v2, v0}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v2

    .line 251
    :cond_9
    sget-object v8, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-direct {v8, v1, v2}, Lcom/exteragram/messenger/plugins/pip/PipController;->findInstalledVersion(Ljava/lang/String;Ljava/util/List;)Ljava/lang/String;

    move-result-object v10

    if-nez v10, :cond_a

    .line 254
    invoke-direct {v8, v1, v2}, Lcom/exteragram/messenger/plugins/pip/PipController;->findVersionOnDisk(Ljava/lang/String;Ljava/util/List;)Ljava/lang/String;

    move-result-object v10

    if-eqz v10, :cond_b

    .line 257
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v8, "PipController: Found "

    invoke-virtual {v2, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v8, " "

    invoke-virtual {v2, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v8, " on disk, adopting."

    invoke-virtual {v2, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    :cond_a
    move-object v8, v10

    goto :goto_5

    .line 259
    :cond_b
    invoke-direct {v8, v1, v2, v5}, Lcom/exteragram/messenger/plugins/pip/PipController;->installPackage(Ljava/lang/String;Ljava/util/List;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)Ljava/lang/String;

    move-result-object v2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    move-object v8, v2

    .line 233
    :goto_5
    monitor-exit v7

    .line 265
    invoke-interface {v3}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_c
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v7

    if-eqz v7, :cond_d

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v7

    move-object v10, v7

    check-cast v10, Lkotlin/Pair;

    invoke-virtual {v10}, Lkotlin/Pair;->getFirst()Ljava/lang/Object;

    move-result-object v10

    invoke-static {v10, v1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v10

    if-eqz v10, :cond_c

    move-object v9, v7

    :cond_d
    check-cast v9, Lkotlin/Pair;

    if-eqz v9, :cond_e

    .line 267
    invoke-virtual {v9}, Lkotlin/Pair;->getSecond()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    .line 268
    sget-object v7, Lcom/exteragram/messenger/plugins/pip/PipController$VersionComparator;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController$VersionComparator;

    invoke-virtual {v7, v8, v2}, Lcom/exteragram/messenger/plugins/pip/PipController$VersionComparator;->compare(Ljava/lang/String;Ljava/lang/String;)I

    move-result v7

    if-lez v7, :cond_11

    .line 269
    new-instance v7, Ljava/lang/StringBuilder;

    const-string v10, "PipController: Upgrading "

    invoke-direct {v7, v10}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v7, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v10, " from "

    invoke-virtual {v7, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, " to "

    invoke-virtual {v7, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, " for "

    invoke-virtual {v7, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    .line 270
    invoke-interface {v3, v9}, Ljava/util/Set;->remove(Ljava/lang/Object;)Z

    .line 276
    :cond_e
    sget-object v2, Lcom/exteragram/messenger/plugins/pip/PipController;->registry:Ljava/util/concurrent/ConcurrentHashMap;

    new-instance v7, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda6;

    invoke-direct {v7}, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda6;-><init>()V

    new-instance v9, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda7;

    invoke-direct {v9, v7}, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda7;-><init>(Lkotlin/jvm/functions/Function1;)V

    invoke-virtual {v2, v1, v9}, Ljava/util/concurrent/ConcurrentHashMap;->computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/concurrent/ConcurrentHashMap;

    .line 277
    new-instance v7, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda8;

    invoke-direct {v7}, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda8;-><init>()V

    new-instance v9, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda9;

    invoke-direct {v9, v7}, Lcom/exteragram/messenger/plugins/pip/PipController$$ExternalSyntheticLambda9;-><init>(Lkotlin/jvm/functions/Function1;)V

    invoke-virtual {v2, v8, v9}, Ljava/util/concurrent/ConcurrentHashMap;->computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/Set;

    .line 278
    invoke-interface {v2, v4}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    .line 280
    invoke-static {v1, v8}, Lkotlin/TuplesKt;->to(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;

    move-result-object v2

    invoke-interface {v3, v2}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    .line 282
    invoke-direct {v0, v1, v8}, Lcom/exteragram/messenger/plugins/pip/PipController;->findMetadataFile(Ljava/lang/String;Ljava/lang/String;)Ljava/io/File;

    move-result-object v1

    if-eqz v1, :cond_11

    .line 283
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v2

    if-eqz v2, :cond_11

    .line 284
    invoke-direct {v0, v1}, Lcom/exteragram/messenger/plugins/pip/PipController;->parseDependenciesFromMetadata(Ljava/io/File;)Ljava/util/List;

    move-result-object v1

    .line 285
    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v7

    :goto_6
    invoke-interface {v7}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_11

    invoke-interface {v7}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    if-eqz v5, :cond_10

    .line 286
    invoke-interface {v5}, Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;->isCancelled()Z

    move-result v2

    if-eq v2, v6, :cond_f

    goto :goto_7

    :cond_f
    const-string v0, "Installation cancelled"

    invoke-static {v0}, Lorg/vosk/Model$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void

    .line 287
    :cond_10
    :goto_7
    invoke-direct {v0, v1}, Lcom/exteragram/messenger/plugins/pip/PipController;->parseRequirement(Ljava/lang/String;)Lkotlin/Pair;

    move-result-object v1

    invoke-virtual {v1}, Lkotlin/Pair;->component1()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-virtual {v1}, Lkotlin/Pair;->component2()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/List;

    .line 289
    invoke-direct {v0, v2}, Lcom/exteragram/messenger/plugins/pip/PipController;->normalizePackageName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    move-object/from16 v20, v2

    move-object v2, v1

    move-object/from16 v1, v20

    .line 288
    invoke-direct/range {v0 .. v5}, Lcom/exteragram/messenger/plugins/pip/PipController;->resolveAndInstall(Ljava/lang/String;Ljava/util/List;Ljava/util/Set;Ljava/lang/String;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)V

    move-object/from16 v0, p0

    move-object/from16 v3, p3

    move-object/from16 v4, p4

    move-object/from16 v5, p5

    goto :goto_6

    :cond_11
    return-void

    .line 233
    :goto_8
    monitor-exit v7

    throw v0
.end method

.method private final declared-synchronized restoreRegistry(Ljava/util/Map;)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "+",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "+",
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;>;>;)V"
        }
    .end annotation

    monitor-enter p0

    .line 151
    :try_start_0
    sget-object v0, Lcom/exteragram/messenger/plugins/pip/PipController;->registry:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentHashMap;->clear()V

    .line 221
    invoke-interface {p1}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Map$Entry;

    invoke-interface {v0}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    invoke-interface {v0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Map;

    .line 153
    new-instance v2, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v2}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    .line 221
    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_1
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/util/Map$Entry;

    invoke-interface {v3}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    invoke-interface {v3}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/util/Set;

    .line 155
    invoke-static {}, Ljava/util/concurrent/ConcurrentHashMap;->newKeySet()Ljava/util/concurrent/ConcurrentHashMap$KeySetView;

    move-result-object v5

    .line 156
    invoke-virtual {v5, v3}, Ljava/util/concurrent/ConcurrentHashMap$KeySetView;->addAll(Ljava/util/Collection;)Z

    .line 155
    invoke-interface {v2, v4, v5}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_1

    :catchall_0
    move-exception p1

    goto :goto_2

    .line 159
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/plugins/pip/PipController;->registry:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-interface {v0, v1, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    .line 161
    :cond_1
    monitor-exit p0

    return-void

    :goto_2
    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method

.method private final declared-synchronized saveRegistry()V
    .locals 4

    monitor-enter p0

    .line 166
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/pip/PipController;->getRegistryFile()Ljava/io/File;

    move-result-object v0

    invoke-static {v0}, Lorg/simplifiles/SimpliFiles;->file(Ljava/io/File;)Lorg/simplifiles/files/SimpliFile;

    move-result-object v0

    sget-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->gson:Lcom/google/gson/Gson;

    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/pip/PipController;->snapshotRegistry()Ljava/util/Map;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/google/gson/Gson;->toJson(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x2

    const/4 v3, 0x0

    invoke-static {v0, v1, v3, v2, v3}, Lorg/simplifiles/files/SimpliFile;->writeTextAtomic$default(Lorg/simplifiles/files/SimpliFile;Ljava/lang/String;Ljava/nio/charset/Charset;ILjava/lang/Object;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception v0

    goto :goto_1

    :catch_0
    move-exception v0

    .line 168
    :try_start_1
    const-string v1, "PipController: Failed to save registry"

    invoke-static {v1, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 170
    :goto_0
    monitor-exit p0

    return-void

    :goto_1
    :try_start_2
    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw v0
.end method

.method private final selectWheelCandidate(Ljava/lang/String;Lcom/google/gson/JsonObject;Z)Lcom/exteragram/messenger/plugins/pip/PipController$WheelCandidate;
    .locals 3

    .line 548
    const-string/jumbo v0, "packagetype"

    invoke-direct {p0, p2, v0}, Lcom/exteragram/messenger/plugins/pip/PipController;->getStringOrNull(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return-object v1

    .line 549
    :cond_0
    const-string v2, "bdist_wheel"

    invoke-static {v0, v2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    return-object v1

    :cond_1
    if-nez p3, :cond_2

    .line 551
    const-string/jumbo p3, "yanked"

    invoke-direct {p0, p2, p3}, Lcom/exteragram/messenger/plugins/pip/PipController;->getBooleanOrNull(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/Boolean;

    move-result-object p3

    sget-object v0, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    invoke-static {p3, v0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p3

    if-eqz p3, :cond_2

    return-object v1

    .line 553
    :cond_2
    const-string p3, "filename"

    invoke-direct {p0, p2, p3}, Lcom/exteragram/messenger/plugins/pip/PipController;->getStringOrNull(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p3

    if-nez p3, :cond_3

    return-object v1

    .line 554
    :cond_3
    invoke-direct {p0, p3}, Lcom/exteragram/messenger/plugins/pip/PipController;->isPurePythonWheelCompatible(Ljava/lang/String;)Z

    move-result p3

    if-nez p3, :cond_4

    return-object v1

    .line 556
    :cond_4
    const-string/jumbo p3, "requires_python"

    invoke-direct {p0, p2, p3}, Lcom/exteragram/messenger/plugins/pip/PipController;->getStringOrNull(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p3

    if-eqz p3, :cond_5

    .line 557
    sget-object v0, Lcom/exteragram/messenger/plugins/pip/PipController;->pythonVersion:Ljava/lang/String;

    invoke-direct {p0, p3}, Lcom/exteragram/messenger/plugins/pip/PipController;->parseSpecs(Ljava/lang/String;)Ljava/util/List;

    move-result-object p3

    invoke-direct {p0, v0, p3}, Lcom/exteragram/messenger/plugins/pip/PipController;->checkVersionSatisfies(Ljava/lang/String;Ljava/util/List;)Z

    move-result p3

    if-nez p3, :cond_5

    return-object v1

    .line 561
    :cond_5
    const-string/jumbo p3, "url"

    invoke-direct {p0, p2, p3}, Lcom/exteragram/messenger/plugins/pip/PipController;->getStringOrNull(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p3

    if-nez p3, :cond_6

    return-object v1

    .line 562
    :cond_6
    const-string v0, "digests"

    invoke-virtual {p2, v0}, Lcom/google/gson/JsonObject;->getAsJsonObject(Ljava/lang/String;)Lcom/google/gson/JsonObject;

    move-result-object p2

    if-eqz p2, :cond_7

    const-string/jumbo v0, "sha256"

    invoke-direct {p0, p2, v0}, Lcom/exteragram/messenger/plugins/pip/PipController;->getStringOrNull(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 563
    :cond_7
    new-instance p0, Lcom/exteragram/messenger/plugins/pip/PipController$WheelCandidate;

    invoke-direct {p0, p1, p3, v1}, Lcom/exteragram/messenger/plugins/pip/PipController$WheelCandidate;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    return-object p0
.end method

.method private final declared-synchronized snapshotRegistry()Ljava/util/Map;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;>;>;"
        }
    .end annotation

    monitor-enter p0

    .line 144
    :try_start_0
    sget-object v0, Lcom/exteragram/messenger/plugins/pip/PipController;->registry:Ljava/util/concurrent/ConcurrentHashMap;

    .line 493
    new-instance v1, Ljava/util/LinkedHashMap;

    invoke-interface {v0}, Ljava/util/Map;->size()I

    move-result v2

    invoke-static {v2}, Lkotlin/collections/MapsKt;->mapCapacity(I)I

    move-result v2

    invoke-direct {v1, v2}, Ljava/util/LinkedHashMap;-><init>(I)V

    .line 442
    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    .line 1266
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    .line 1267
    move-object v3, v2

    check-cast v3, Ljava/util/Map$Entry;

    .line 442
    invoke-interface {v3}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    .line 1267
    check-cast v2, Ljava/util/Map$Entry;

    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/concurrent/ConcurrentHashMap;

    .line 493
    new-instance v4, Ljava/util/LinkedHashMap;

    invoke-interface {v2}, Ljava/util/Map;->size()I

    move-result v5

    invoke-static {v5}, Lkotlin/collections/MapsKt;->mapCapacity(I)I

    move-result v5

    invoke-direct {v4, v5}, Ljava/util/LinkedHashMap;-><init>(I)V

    .line 442
    invoke-interface {v2}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v2

    .line 1266
    invoke-interface {v2}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :goto_1
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_0

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    .line 1267
    move-object v6, v5

    check-cast v6, Ljava/util/Map$Entry;

    .line 442
    invoke-interface {v6}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v6

    .line 1267
    check-cast v5, Ljava/util/Map$Entry;

    invoke-interface {v5}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/util/Set;

    .line 145
    invoke-static {v5}, Lkotlin/collections/CollectionsKt;->toSet(Ljava/lang/Iterable;)Ljava/util/Set;

    move-result-object v5

    .line 1267
    invoke-interface {v4, v6, v5}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_1

    :catchall_0
    move-exception v0

    goto :goto_2

    .line 1267
    :cond_0
    invoke-interface {v1, v3, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    .line 144
    :cond_1
    monitor-exit p0

    return-object v1

    :goto_2
    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method private final specsAllowPreRelease(Ljava/util/List;)Z
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lkotlin/Pair<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;>;)Z"
        }
    .end annotation

    .line 965
    check-cast p1, Ljava/lang/Iterable;

    .line 1807
    instance-of p0, p1, Ljava/util/Collection;

    const/4 v0, 0x0

    if-eqz p0, :cond_0

    move-object p0, p1

    check-cast p0, Ljava/util/Collection;

    invoke-interface {p0}, Ljava/util/Collection;->isEmpty()Z

    move-result p0

    if-eqz p0, :cond_0

    return v0

    .line 1808
    :cond_0
    invoke-interface {p1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_1
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_2

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lkotlin/Pair;

    invoke-virtual {p1}, Lkotlin/Pair;->component2()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    .line 965
    sget-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-direct {v1, p1}, Lcom/exteragram/messenger/plugins/pip/PipController;->isPreReleaseVersion(Ljava/lang/String;)Z

    move-result p1

    if-eqz p1, :cond_1

    const/4 p0, 0x1

    return p0

    :cond_2
    return v0
.end method

.method private final updateRegistryForPlugin(Ljava/lang/String;Ljava/util/Set;)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/Set<",
            "Lkotlin/Pair<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;>;)V"
        }
    .end annotation

    .line 303
    new-instance p0, Ljava/util/ArrayList;

    invoke-direct {p0}, Ljava/util/ArrayList;-><init>()V

    .line 304
    sget-object v0, Lcom/exteragram/messenger/plugins/pip/PipController;->registry:Ljava/util/concurrent/ConcurrentHashMap;

    .line 221
    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/concurrent/ConcurrentHashMap;

    .line 221
    invoke-interface {v1}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_1
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/util/Map$Entry;

    invoke-interface {v3}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    invoke-interface {v3}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/util/Set;

    .line 306
    invoke-static {v2, v4}, Lkotlin/TuplesKt;->to(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;

    move-result-object v4

    .line 307
    invoke-interface {v3, p1}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v5

    if-eqz v5, :cond_1

    invoke-interface {p2, v4}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v5

    if-nez v5, :cond_1

    .line 308
    invoke-interface {v3, p1}, Ljava/util/Set;->remove(Ljava/lang/Object;)Z

    .line 309
    invoke-interface {v3}, Ljava/util/Set;->isEmpty()Z

    move-result v3

    if-eqz v3, :cond_1

    .line 310
    invoke-interface {p0, v4}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 1915
    :cond_2
    invoke-virtual {p0}, Ljava/util/ArrayList;->size()I

    move-result p1

    const/4 p2, 0x0

    :goto_1
    if-ge p2, p1, :cond_5

    invoke-virtual {p0, p2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    add-int/lit8 p2, p2, 0x1

    check-cast v0, Lkotlin/Pair;

    invoke-virtual {v0}, Lkotlin/Pair;->component1()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    invoke-virtual {v0}, Lkotlin/Pair;->component2()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 317
    sget-object v2, Lcom/exteragram/messenger/plugins/pip/PipController;->registry:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v2, v1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/util/concurrent/ConcurrentHashMap;

    if-eqz v3, :cond_3

    invoke-virtual {v3, v0}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/util/Set;

    .line 318
    :cond_3
    invoke-virtual {v2, v1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/util/concurrent/ConcurrentHashMap;

    if-eqz v3, :cond_4

    invoke-virtual {v3}, Ljava/util/concurrent/ConcurrentHashMap;->isEmpty()Z

    move-result v3

    const/4 v4, 0x1

    if-ne v3, v4, :cond_4

    .line 319
    invoke-virtual {v2, v1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 321
    :cond_4
    sget-object v2, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-direct {v2, v1, v0}, Lcom/exteragram/messenger/plugins/pip/PipController;->deletePackage(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_1

    :cond_5
    return-void
.end method


# virtual methods
.method public final activeLibraryPaths()Ljava/util/Set;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 373
    new-instance p0, Ljava/util/LinkedHashSet;

    invoke-direct {p0}, Ljava/util/LinkedHashSet;-><init>()V

    .line 374
    sget-object v0, Lcom/exteragram/messenger/plugins/pip/PipController;->registry:Ljava/util/concurrent/ConcurrentHashMap;

    .line 221
    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/concurrent/ConcurrentHashMap;

    .line 221
    invoke-interface {v1}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_1
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_0

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/util/Map$Entry;

    invoke-interface {v3}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    invoke-interface {v3}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/util/Set;

    .line 376
    invoke-interface {v3}, Ljava/util/Collection;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_1

    .line 378
    :try_start_0
    sget-object v3, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-direct {v3, v2, v4}, Lcom/exteragram/messenger/plugins/pip/PipController;->getLibPath(Ljava/lang/String;Ljava/lang/String;)Ljava/io/File;

    move-result-object v3

    invoke-virtual {v3}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v3

    invoke-interface {p0, v3}, Ljava/util/Set;->add(Ljava/lang/Object;)Z
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v3

    .line 380
    new-instance v5, Ljava/lang/StringBuilder;

    const-string v6, "PipController: Failed to resolve path for "

    invoke-direct {v5, v6}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v5, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, " "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v4, v3}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_0

    :cond_2
    return-object p0
.end method

.method public final cleanup()V
    .locals 0

    .line 173
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/pip/PipController;->cleanupInternal()V

    return-void
.end method

.method public final getPythonVersion()Ljava/lang/String;
    .locals 0

    .line 65
    sget-object p0, Lcom/exteragram/messenger/plugins/pip/PipController;->pythonVersion:Ljava/lang/String;

    return-object p0
.end method

.method public final installDependencies(Ljava/util/List;Ljava/lang/String;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)Ljava/util/List;
    .locals 9
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;",
            "Ljava/lang/String;",
            "Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;",
            ")",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 185
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 186
    new-instance v4, Ljava/util/LinkedHashSet;

    invoke-direct {v4}, Ljava/util/LinkedHashSet;-><init>()V

    .line 187
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/pip/PipController;->snapshotRegistry()Ljava/util/Map;

    move-result-object v7

    .line 190
    :try_start_0
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v8

    :cond_0
    :goto_0
    invoke-interface {v8}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_4

    invoke-interface {v8}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_2
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz p3, :cond_2

    .line 191
    :try_start_1
    invoke-interface {p3}, Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;->isCancelled()Z

    move-result v2

    const/4 v3, 0x1

    if-eq v2, v3, :cond_1

    goto :goto_1

    :cond_1
    new-instance p3, Ljava/io/IOException;

    const-string v0, "Installation cancelled"

    invoke-direct {p3, v0}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw p3
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    :catch_0
    move-exception v0

    move-object p3, v0

    move-object v1, p0

    move-object v5, p2

    goto/16 :goto_4

    .line 192
    :cond_2
    :goto_1
    :try_start_2
    invoke-static {v1}, Lkotlin/text/StringsKt;->isBlank(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_0

    .line 193
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/plugins/pip/PipController;->parseRequirement(Ljava/lang/String;)Lkotlin/Pair;

    move-result-object v1

    invoke-virtual {v1}, Lkotlin/Pair;->component1()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    invoke-virtual {v1}, Lkotlin/Pair;->component2()Ljava/lang/Object;

    move-result-object v1

    move-object v3, v1

    check-cast v3, Ljava/util/List;

    .line 194
    invoke-direct {p0, v2}, Lcom/exteragram/messenger/plugins/pip/PipController;->normalizePackageName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 196
    sget-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->PREINSTALLED_PACKAGES:Ljava/util/Set;

    invoke-interface {v1, v2}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v1
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    if-eqz v1, :cond_3

    goto :goto_0

    :cond_3
    move-object v1, p0

    move-object v5, p2

    move-object v6, p3

    .line 200
    :try_start_3
    invoke-direct/range {v1 .. v6}, Lcom/exteragram/messenger/plugins/pip/PipController;->resolveAndInstall(Ljava/lang/String;Ljava/util/List;Ljava/util/Set;Ljava/lang/String;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)V

    move-object p0, v1

    move-object p2, v5

    move-object p3, v6

    goto :goto_0

    :catch_1
    move-exception v0

    :goto_2
    move-object p3, v0

    goto :goto_4

    :catch_2
    move-exception v0

    move-object v1, p0

    move-object v5, p2

    goto :goto_2

    :cond_4
    move-object v1, p0

    move-object v5, p2

    .line 203
    invoke-direct {v1, v5, v4}, Lcom/exteragram/messenger/plugins/pip/PipController;->updateRegistryForPlugin(Ljava/lang/String;Ljava/util/Set;)V

    .line 205
    invoke-interface {v4}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_3
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p2

    if-eqz p2, :cond_5

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lkotlin/Pair;

    invoke-virtual {p2}, Lkotlin/Pair;->component1()Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Ljava/lang/String;

    invoke-virtual {p2}, Lkotlin/Pair;->component2()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/String;

    .line 206
    invoke-direct {v1, p3, p2}, Lcom/exteragram/messenger/plugins/pip/PipController;->getLibPath(Ljava/lang/String;Ljava/lang/String;)Ljava/io/File;

    move-result-object p2

    invoke-virtual {p2}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p2

    invoke-interface {v0, p2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_3

    .line 209
    :cond_5
    invoke-direct {v1}, Lcom/exteragram/messenger/plugins/pip/PipController;->saveRegistry()V
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_1
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 216
    sget-object p0, Lcom/exteragram/messenger/plugins/pip/PipController;->installLocks:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p0}, Ljava/util/concurrent/ConcurrentHashMap;->clear()V

    return-object v0

    .line 211
    :goto_4
    :try_start_4
    new-instance p0, Ljava/lang/StringBuilder;

    invoke-direct {p0}, Ljava/lang/StringBuilder;-><init>()V

    const-string p2, "PipController: Failed to install dependencies for "

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p2, ": "

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, p3}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 212
    invoke-direct {v1, v7}, Lcom/exteragram/messenger/plugins/pip/PipController;->restoreRegistry(Ljava/util/Map;)V

    .line 213
    invoke-direct {v1}, Lcom/exteragram/messenger/plugins/pip/PipController;->removeOrphanedDirectories()V

    .line 214
    throw p3
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    :catchall_0
    move-exception v0

    move-object p0, v0

    .line 216
    sget-object p1, Lcom/exteragram/messenger/plugins/pip/PipController;->installLocks:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p1}, Ljava/util/concurrent/ConcurrentHashMap;->clear()V

    throw p0
.end method

.method public final setPythonVersion(Ljava/lang/String;)V
    .locals 0

    .line 65
    sput-object p1, Lcom/exteragram/messenger/plugins/pip/PipController;->pythonVersion:Ljava/lang/String;

    return-void
.end method

.method public final uninstallDependencies(Ljava/lang/String;)V
    .locals 10

    .line 390
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 392
    sget-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->registry:Ljava/util/concurrent/ConcurrentHashMap;

    .line 221
    invoke-interface {v1}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    const/4 v2, 0x0

    move v3, v2

    :cond_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    const/4 v5, 0x1

    if-eqz v4, :cond_3

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/util/Map$Entry;

    invoke-interface {v4}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    invoke-interface {v4}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/util/concurrent/ConcurrentHashMap;

    .line 221
    invoke-interface {v4}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v4

    invoke-interface {v4}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v4

    :cond_1
    :goto_0
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v7

    if-eqz v7, :cond_0

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/Map$Entry;

    invoke-interface {v7}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v8

    check-cast v8, Ljava/lang/String;

    invoke-interface {v7}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/Set;

    .line 394
    invoke-interface {v7, p1}, Ljava/util/Set;->remove(Ljava/lang/Object;)Z

    move-result v9

    if-eqz v9, :cond_1

    .line 396
    invoke-interface {v7}, Ljava/util/Set;->isEmpty()Z

    move-result v3

    if-eqz v3, :cond_2

    .line 397
    invoke-static {v6, v8}, Lkotlin/TuplesKt;->to(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;

    move-result-object v3

    invoke-interface {v0, v3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :cond_2
    move v3, v5

    goto :goto_0

    .line 1915
    :cond_3
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result p1

    :goto_1
    if-ge v2, p1, :cond_6

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    add-int/lit8 v2, v2, 0x1

    check-cast v1, Lkotlin/Pair;

    invoke-virtual {v1}, Lkotlin/Pair;->component1()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    invoke-virtual {v1}, Lkotlin/Pair;->component2()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    .line 404
    sget-object v6, Lcom/exteragram/messenger/plugins/pip/PipController;->registry:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v6, v4}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/concurrent/ConcurrentHashMap;

    if-eqz v7, :cond_4

    invoke-virtual {v7, v1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/Set;

    .line 405
    :cond_4
    invoke-virtual {v6, v4}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/util/concurrent/ConcurrentHashMap;

    if-eqz v7, :cond_5

    invoke-virtual {v7}, Ljava/util/concurrent/ConcurrentHashMap;->isEmpty()Z

    move-result v7

    if-ne v7, v5, :cond_5

    .line 406
    invoke-virtual {v6, v4}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 408
    :cond_5
    sget-object v6, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-direct {v6, v4, v1}, Lcom/exteragram/messenger/plugins/pip/PipController;->deletePackage(Ljava/lang/String;Ljava/lang/String;)V

    goto :goto_1

    :cond_6
    if-eqz v3, :cond_7

    .line 412
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/pip/PipController;->saveRegistry()V

    :cond_7
    return-void
.end method
