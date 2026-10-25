.class public final Lcom/exteragram/messenger/plugins/PluginsController;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/exteragram/messenger/plugins/hooks/PluginsHooks;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/plugins/PluginsController$Companion;,
        Lcom/exteragram/messenger/plugins/PluginsController$EngineHookCaller;,
        Lcom/exteragram/messenger/plugins/PluginsController$HookResult;,
        Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;,
        Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;,
        Lcom/exteragram/messenger/plugins/PluginsController$SingletonHolder;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u009e\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0002\n\u0002\u0008\u0015\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0010\n\u0002\u0018\u0002\n\u0002\u0008\t\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\n\n\u0002\u0018\u0002\n\u0002\u0008\u0010\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0007\u0018\u0000 \u00b9\u00012\u00020\u0001:\u000c\u00b4\u0001\u00b5\u0001\u00b6\u0001\u00b7\u0001\u00b8\u0001\u00b9\u0001B\t\u0008\u0002\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0012\u00107\u001a\u0004\u0018\u0001082\u0008\u00109\u001a\u0004\u0018\u00010\u0006J\u0006\u0010:\u001a\u00020;J\u0010\u0010:\u001a\u00020;2\u0008\u0010<\u001a\u0004\u0018\u000106J\u000e\u0010:\u001a\u00020;2\u0006\u0010=\u001a\u00020#J\u0018\u0010:\u001a\u00020;2\u0006\u0010=\u001a\u00020#2\u0008\u0010<\u001a\u0004\u0018\u000106J\u0006\u0010>\u001a\u00020;J\u0010\u0010?\u001a\u00020;2\u0008\u0010<\u001a\u0004\u0018\u000106J\u0006\u0010@\u001a\u00020;J\u000e\u0010@\u001a\u00020;2\u0006\u0010=\u001a\u00020#J\u0006\u0010A\u001a\u00020#J\u0017\u0010B\u001a\u00020#2\u0008\u00109\u001a\u0004\u0018\u00010\u0006H\u0000\u00a2\u0006\u0002\u0008CJ\u0017\u0010B\u001a\u00020#2\u0008\u0010D\u001a\u0004\u0018\u00010\u0007H\u0000\u00a2\u0006\u0002\u0008CJ\u001c\u0010E\u001a\u00020\u00172\u0008\u0010F\u001a\u0004\u0018\u00010\u00062\u0008\u0010G\u001a\u0004\u0018\u00010\u0019H\u0002J\u0012\u0010H\u001a\u00020;2\u0008\u0010F\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010I\u001a\u00020\u00172\u0008\u0010J\u001a\u0004\u0018\u00010\u0006H\u0002J\u0008\u0010K\u001a\u00020;H\u0002J\u0014\u0010L\u001a\u0004\u0018\u00010\u00062\u0008\u0010F\u001a\u0004\u0018\u00010\u0006H\u0002J\u0018\u0010M\u001a\n\u0012\u0004\u0012\u00020\u000c\u0018\u00010\u000b2\u0008\u00109\u001a\u0004\u0018\u00010\u0006J(\u0010N\u001a\u00020;2\u0008\u00109\u001a\u0004\u0018\u00010\u00062\u0006\u0010O\u001a\u00020#2\u000e\u0010P\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010QJ \u0010R\u001a\u00020;2\u0008\u00109\u001a\u0004\u0018\u00010\u00062\u000e\u0010P\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010QJ\u0010\u0010S\u001a\u00020;2\u0008\u00109\u001a\u0004\u0018\u00010\u0006J\u0012\u0010T\u001a\u0004\u0018\u00010\u00062\u0008\u0010U\u001a\u0004\u0018\u00010\u0006J\u001a\u0010V\u001a\u00020;2\u0008\u0010W\u001a\u0004\u0018\u00010X2\u0008\u0010Y\u001a\u0004\u0018\u00010ZJ\"\u0010V\u001a\u00020;2\u0008\u0010W\u001a\u0004\u0018\u00010X2\u0008\u0010[\u001a\u0004\u0018\u00010\u00062\u0006\u0010\\\u001a\u00020#J\u001c\u0010V\u001a\u00020;2\u0008\u0010W\u001a\u0004\u0018\u00010X2\u0008\u0010]\u001a\u0004\u0018\u00010^H\u0002J\u0006\u0010_\u001a\u00020;J\u0010\u0010_\u001a\u00020;2\u0008\u00109\u001a\u0004\u0018\u00010\u0006J\u0010\u0010`\u001a\u00020#2\u0008\u00109\u001a\u0004\u0018\u00010\u0006J\u0010\u0010a\u001a\u00020;2\u0008\u00109\u001a\u0004\u0018\u00010\u0006J\u001c\u0010b\u001a\u00020;2\u0008\u00109\u001a\u0004\u0018\u00010\u00062\u0008\u0008\u0002\u0010c\u001a\u00020#H\u0007J\u001c\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0002\u0008\u0003\u0018\u00010\u001f2\u0008\u00109\u001a\u0004\u0018\u00010\u0006J\u0010\u0010e\u001a\u00020#2\u0008\u00109\u001a\u0004\u0018\u00010\u0006J\"\u0010f\u001a\u00020#2\u0008\u00109\u001a\u0004\u0018\u00010\u00062\u0008\u0010g\u001a\u0004\u0018\u00010\u00062\u0006\u0010h\u001a\u00020#J&\u0010i\u001a\u0004\u0018\u00010\u00062\u0008\u00109\u001a\u0004\u0018\u00010\u00062\u0008\u0010g\u001a\u0004\u0018\u00010\u00062\u0008\u0010h\u001a\u0004\u0018\u00010\u0006J\"\u0010j\u001a\u00020\u00172\u0008\u00109\u001a\u0004\u0018\u00010\u00062\u0008\u0010g\u001a\u0004\u0018\u00010\u00062\u0006\u0010h\u001a\u00020\u0017J$\u0010k\u001a\u00020;2\u0008\u00109\u001a\u0004\u0018\u00010\u00062\u0008\u0010g\u001a\u0004\u0018\u00010\u00062\u0008\u0010l\u001a\u0004\u0018\u00010!J.\u0010m\u001a\u00020;2\u0008\u00109\u001a\u0004\u0018\u00010\u00062\u0008\u0010g\u001a\u0004\u0018\u00010\u00062\u0008\u0010l\u001a\u0004\u0018\u00010!2\u0008\u0010n\u001a\u0004\u0018\u00010oJ$\u0010p\u001a\u00020;2\u0008\u00109\u001a\u0004\u0018\u00010\u00062\u0008\u0010q\u001a\u0004\u0018\u00010\u00142\u0006\u0010r\u001a\u00020\u0006H\u0002J(\u0010s\u001a\u00020;2\u0008\u00109\u001a\u0004\u0018\u00010\u00062\u0006\u0010t\u001a\u00020\u00062\u0006\u0010u\u001a\u00020#2\u0006\u0010v\u001a\u00020\u0017J.\u0010w\u001a\u00020;2\u0008\u00109\u001a\u0004\u0018\u00010\u00062\u0012\u0010x\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020#0y2\u0006\u0010r\u001a\u00020\u0006H\u0002J\u0018\u0010z\u001a\u00020;2\u0008\u00109\u001a\u0004\u0018\u00010\u00062\u0006\u0010t\u001a\u00020\u0006J\u001e\u0010{\u001a\u00020;2\u0008\u00109\u001a\u0004\u0018\u00010\u00062\u000c\u0010|\u001a\u0008\u0018\u00010}R\u00020~J7\u0010\u007f\u001a\u00020;2\u0008\u00109\u001a\u0004\u0018\u00010\u00062%\u0010\u0080\u0001\u001a \u0012\u0008\u0012\u00060}R\u00020~\u0018\u00010\u0081\u0001j\u000f\u0012\u0008\u0012\u00060}R\u00020~\u0018\u0001`\u0082\u0001J\u001f\u0010\u0083\u0001\u001a\u00020;2\u0008\u00109\u001a\u0004\u0018\u00010\u00062\u000c\u0010|\u001a\u0008\u0018\u00010}R\u00020~J\u0011\u0010\u0084\u0001\u001a\u00020;2\u0008\u00109\u001a\u0004\u0018\u00010\u0006J\u001e\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u00062\u0008\u00109\u001a\u0004\u0018\u00010\u00062\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010oJ\u001c\u0010\u0087\u0001\u001a\u00020#2\u0008\u00109\u001a\u0004\u0018\u00010\u00062\t\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0006J\u0011\u0010\u0089\u0001\u001a\u00020;2\u0008\u00109\u001a\u0004\u0018\u00010\u0006J$\u0010\u008a\u0001\u001a\u0008\u0012\u0004\u0012\u00020\u000f0\u000b2\t\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u00062\n\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008d\u0001J/\u0010\u008a\u0001\u001a\u0008\u0012\u0004\u0012\u00020\u000f0\u000b2\t\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u00062\u0015\u0010\u008e\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020!\u0018\u00010\u001fJ\u0007\u0010\u008f\u0001\u001a\u00020;J\u0012\u0010\u0090\u0001\u001a\u00020;2\t\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u0006J\u001a\u0010\u0092\u0001\u001a\u0008\u0012\u0004\u0012\u00020\u00060\u000b2\t\u0010\u0093\u0001\u001a\u0004\u0018\u00010\u0006H\u0002J\t\u0010\u0094\u0001\u001a\u00020;H\u0002J\t\u0010\u0095\u0001\u001a\u00020;H\u0002J\t\u0010\u0096\u0001\u001a\u00020#H\u0002J\u0011\u0010\u0097\u0001\u001a\u00020;2\u0006\u00109\u001a\u00020\u0006H\u0002J\u0011\u0010\u0098\u0001\u001a\u00020;2\u0006\u00109\u001a\u00020\u0006H\u0002J\t\u0010\u0099\u0001\u001a\u00020;H\u0002J>\u0010\u009a\u0001\u001a\u0005\u0018\u0001H\u009b\u0001\"\u0005\u0008\u0000\u0010\u009b\u00012\u0006\u0010t\u001a\u00020\u00062\n\u0010\u009c\u0001\u001a\u0005\u0018\u0001H\u009b\u00012\u000f\u0010\u009d\u0001\u001a\n\u0012\u0005\u0012\u0003H\u009b\u00010\u009e\u0001H\u0002\u00a2\u0006\u0003\u0010\u009f\u0001J(\u0010\u00a0\u0001\u001a\u0005\u0018\u00010\u00a1\u00012\u0007\u0010\u00a2\u0001\u001a\u00020\u00062\u0007\u0010\u00a3\u0001\u001a\u00020\u00172\u0008\u0010\u00a4\u0001\u001a\u00030\u00a1\u0001H\u0016J4\u0010\u00a5\u0001\u001a\u00030\u00a6\u00012\u0007\u0010\u00a2\u0001\u001a\u00020\u00062\u0007\u0010\u00a3\u0001\u001a\u00020\u00172\n\u0010\u00a7\u0001\u001a\u0005\u0018\u00010\u00a1\u00012\n\u0010\u00a8\u0001\u001a\u0005\u0018\u00010\u00a9\u0001H\u0016J(\u0010\u00aa\u0001\u001a\u0005\u0018\u00010\u00ab\u00012\u0007\u0010\u00ac\u0001\u001a\u00020\u00062\u0007\u0010\u00a3\u0001\u001a\u00020\u00172\u0008\u0010\u00ad\u0001\u001a\u00030\u00ab\u0001H\u0016J(\u0010\u00ae\u0001\u001a\u0005\u0018\u00010\u00af\u00012\u0007\u0010\u00b0\u0001\u001a\u00020\u00062\u0007\u0010\u00a3\u0001\u001a\u00020\u00172\u0008\u0010\u00b1\u0001\u001a\u00030\u00af\u0001H\u0016J\u001e\u0010\u00b2\u0001\u001a\u0005\u0018\u00010\u00b3\u00012\u0007\u0010\u00a3\u0001\u001a\u00020\u00172\u0007\u0010]\u001a\u00030\u00b3\u0001H\u0016R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0008\u0010\tR#\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020\u000c0\u000b0\u0005\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\r\u0010\tR\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020\u000f0\u00110\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020\u00140\u00130\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020\u00060\u000b0\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00170\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00190\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\u0008\u0012\u0004\u0012\u00020\u001d0\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R \u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\u0008\u0012\u0004\u0012\u00020\u001d0\u000b0\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020#X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010%\u001a\u00020&X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\'\u0010(\"\u0004\u0008)\u0010*R\u001a\u0010+\u001a\u00020,X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008-\u0010.\"\u0004\u0008/\u00100R\u0011\u00101\u001a\u000202\u00a2\u0006\u0008\n\u0000\u001a\u0004\u00083\u00104R\u000e\u00105\u001a\u000206X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u00ba\u0001"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/PluginsController;",
        "Lcom/exteragram/messenger/plugins/hooks/PluginsHooks;",
        "<init>",
        "()V",
        "plugins",
        "Ljava/util/concurrent/ConcurrentHashMap;",
        "",
        "Lcom/exteragram/messenger/plugins/Plugin;",
        "getPlugins",
        "()Ljava/util/concurrent/ConcurrentHashMap;",
        "settings",
        "",
        "Lcom/exteragram/messenger/plugins/models/SettingItem;",
        "getSettings",
        "menuItemsById",
        "Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;",
        "menuItemsByMenuType",
        "Ljava/util/concurrent/CopyOnWriteArrayList;",
        "hooks",
        "",
        "Lcom/exteragram/messenger/plugins/hooks/HookRecord;",
        "interestedPluginsCache",
        "fileIconIdsByExtension",
        "",
        "fileIconDrawablesById",
        "Landroid/graphics/drawable/Drawable;",
        "nextFileIconId",
        "Ljava/util/concurrent/atomic/AtomicInteger;",
        "substringMatchEventHooksCache",
        "Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;",
        "exactMatchEventHooksCache",
        "",
        "hooksCacheLock",
        "",
        "hooksCacheDirty",
        "",
        "initialized",
        "pluginsDir",
        "Ljava/io/File;",
        "getPluginsDir",
        "()Ljava/io/File;",
        "setPluginsDir",
        "(Ljava/io/File;)V",
        "preferences",
        "Landroid/content/SharedPreferences;",
        "getPreferences",
        "()Landroid/content/SharedPreferences;",
        "setPreferences",
        "(Landroid/content/SharedPreferences;)V",
        "watchdog",
        "Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;",
        "getWatchdog",
        "()Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;",
        "updateNotificationRunnable",
        "Ljava/lang/Runnable;",
        "getPluginEngine",
        "Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;",
        "pluginId",
        "init",
        "",
        "onDone",
        "startWithSafeMode",
        "checkDevServers",
        "shutdown",
        "restart",
        "isInitialized",
        "isPluginActive",
        "isPluginActive$TMessagesProj",
        "plugin",
        "registerFileIconInternal",
        "extension",
        "drawable",
        "unregisterFileIconInternal",
        "getFileIconIdInternal",
        "fileName",
        "clearFileIconsInternal",
        "normalizeFileExtension",
        "getPluginSettingsList",
        "setPluginEnabled",
        "enabled",
        "callback",
        "Lorg/telegram/messenger/Utilities$Callback;",
        "deletePlugin",
        "cleanupPlugin",
        "getPluginPath",
        "id",
        "showInstallDialog",
        "fragment",
        "Lorg/telegram/ui/ActionBar/BaseFragment;",
        "messageObject",
        "Lorg/telegram/messenger/MessageObject;",
        "filePath",
        "trusted",
        "params",
        "Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;",
        "loadPluginSettings",
        "hasPluginSettings",
        "invalidatePluginSettings",
        "clearPluginSettingsPreferences",
        "clearEnabledState",
        "getPluginSettingsPreferences",
        "hasPluginSettingsPreferences",
        "getPluginSettingBoolean",
        "key",
        "defaultValue",
        "getPluginSettingString",
        "getPluginSettingInt",
        "setPluginSetting",
        "value",
        "setPluginSettingAndTriggerOnChange",
        "onChangeCallback",
        "Lcom/chaquo/python/PyObject;",
        "addHook",
        "newHook",
        "logMessage",
        "addEventHook",
        "hookName",
        "matchSubstring",
        "priority",
        "removeHook",
        "filter",
        "Lkotlin/Function1;",
        "removeEventHook",
        "addXposedHook",
        "unhook",
        "Lde/robv/android/xposed/XC_MethodHook$Unhook;",
        "Lde/robv/android/xposed/XC_MethodHook;",
        "addXposedHooks",
        "unhooks",
        "Ljava/util/ArrayList;",
        "Lkotlin/collections/ArrayList;",
        "removeXposedHook",
        "removeHooksByPluginId",
        "addMenuItem",
        "pyMenuItemData",
        "removeMenuItem",
        "itemId",
        "removeMenuItemsByPluginId",
        "getMenuItemsForLocation",
        "menuType",
        "builder",
        "Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;",
        "contextData",
        "notifyPluginsChanged",
        "executeOnAppEvent",
        "eventType",
        "getInterestedPluginIds",
        "eventName",
        "rebuildHooksCacheIfNeeded",
        "ensurePreferences",
        "isOnPluginsQueueThread",
        "notifyPluginSettingsRegistered",
        "notifyPluginSettingsUnregistered",
        "notifyMenuItemsUpdated",
        "executeGenericHook",
        "T",
        "initialObject",
        "caller",
        "Lcom/exteragram/messenger/plugins/PluginsController$EngineHookCaller;",
        "(Ljava/lang/String;Ljava/lang/Object;Lcom/exteragram/messenger/plugins/PluginsController$EngineHookCaller;)Ljava/lang/Object;",
        "executePreRequestHook",
        "Lorg/telegram/tgnet/TLObject;",
        "requestName",
        "account",
        "request",
        "executePostRequestHook",
        "Lcom/exteragram/messenger/plugins/hooks/PluginsHooks$PostRequestResult;",
        "response",
        "error",
        "Lorg/telegram/tgnet/TLRPC$TL_error;",
        "executeUpdateHook",
        "Lorg/telegram/tgnet/TLRPC$Update;",
        "updateName",
        "update",
        "executeUpdatesHook",
        "Lorg/telegram/tgnet/TLRPC$Updates;",
        "containerName",
        "updates",
        "executeSendMessageHook",
        "Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;",
        "EngineHookCaller",
        "HookResult",
        "PluginValidationResult",
        "PluginsEngine",
        "SingletonHolder",
        "Companion",
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
        "SMAP\nPluginsController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginsController.kt\ncom/exteragram/messenger/plugins/PluginsController\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1337:1\n1#2:1338\n41#3,12:1339\n41#3,12:1351\n1915#4,2:1363\n1915#4,2:1365\n*S KotlinDebug\n*F\n+ 1 PluginsController.kt\ncom/exteragram/messenger/plugins/PluginsController\n*L\n164#1:1339,12\n478#1:1351,12\n598#1:1363,2\n813#1:1365,2\n*E\n"
    }
.end annotation


# static fields
.field public static final Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

.field public static final PLUGIN_FILE_ICON_ID_START:I = 0x65

.field public static final PLUGIN_FILE_ICON_NONE:I = -0x1

.field public static final PREF_PLUGIN_ENABLED_KEY_PREFIX:Ljava/lang/String; = "plugin_enabled_"

.field private static final enginesMap:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private volatile exactMatchEventHooksCache:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "+",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;",
            ">;>;"
        }
    .end annotation
.end field

.field private final fileIconDrawablesById:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/Integer;",
            "Landroid/graphics/drawable/Drawable;",
            ">;"
        }
    .end annotation
.end field

.field private final fileIconIdsByExtension:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field private final hooks:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Ljava/util/Set<",
            "Lcom/exteragram/messenger/plugins/hooks/HookRecord;",
            ">;>;"
        }
    .end annotation
.end field

.field private volatile hooksCacheDirty:Z

.field private final hooksCacheLock:Ljava/lang/Object;

.field private volatile initialized:Z

.field private final interestedPluginsCache:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;>;"
        }
    .end annotation
.end field

.field private final menuItemsById:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;",
            ">;"
        }
    .end annotation
.end field

.field private final menuItemsByMenuType:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Ljava/util/concurrent/CopyOnWriteArrayList<",
            "Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;",
            ">;>;"
        }
    .end annotation
.end field

.field private final nextFileIconId:Ljava/util/concurrent/atomic/AtomicInteger;

.field private final plugins:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Lcom/exteragram/messenger/plugins/Plugin;",
            ">;"
        }
    .end annotation
.end field

.field private pluginsDir:Ljava/io/File;

.field private preferences:Landroid/content/SharedPreferences;

.field private final settings:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/models/SettingItem;",
            ">;>;"
        }
    .end annotation
.end field

.field private volatile substringMatchEventHooksCache:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;",
            ">;"
        }
    .end annotation
.end field

.field private final updateNotificationRunnable:Ljava/lang/Runnable;

.field private final watchdog:Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;


# direct methods
.method public static $r8$lambda$2rLdY-VQByxv_QUsag19aQTQmFQ(Ljava/lang/String;ILcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;Lorg/telegram/tgnet/TLRPC$Update;Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;
    .locals 0

    .line 996
    invoke-interface {p2, p0, p1, p3, p4}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->executeUpdateHook(Ljava/lang/String;ILorg/telegram/tgnet/TLRPC$Update;Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$8YQxS2Zi9C0fnqZ5SFsFuLuS-Ck(Lorg/telegram/ui/ActionBar/BaseFragment;)V
    .locals 1

    .line 392
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/PluginsActivity;

    invoke-direct {v0}, Lcom/exteragram/messenger/plugins/ui/PluginsActivity;-><init>()V

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void
.end method

.method public static $r8$lambda$8p8Kwk-k37JFIqGET_TR6NbwgbQ(Ljava/lang/Runnable;)V
    .locals 3

    .line 202
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getEngines()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentHashMap;->values()Ljava/util/Collection;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_0

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    .line 204
    :try_start_0
    invoke-interface {v1, p0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->init(Ljava/lang/Runnable;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception v1

    .line 206
    const-string v2, "Failed to initialize plugin engine"

    invoke-static {v2, v1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 207
    invoke-interface {p0}, Ljava/lang/Runnable;->run()V

    goto :goto_0

    :cond_0
    return-void
.end method

.method public static $r8$lambda$BZJAQnCz8fBF-5ISJW3SPVHCvis(Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;Ljava/lang/String;Ljava/lang/Integer;)Ljava/lang/Integer;
    .locals 0

    if-nez p2, :cond_0

    .line 816
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;->getPriority()I

    move-result p0

    :goto_0
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    return-object p0

    :cond_0
    invoke-virtual {p2}, Ljava/lang/Integer;->intValue()I

    move-result p1

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;->getPriority()I

    move-result p0

    invoke-static {p1, p0}, Ljava/lang/Math;->max(II)I

    move-result p0

    goto :goto_0
.end method

.method public static $r8$lambda$CYXYoZ0xzdvz-t3MkDUbIPdLop8()V
    .locals 2

    .line 174
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->getLastFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 176
    new-instance v1, Lcom/exteragram/messenger/plugins/ui/components/SafeModeBottomSheet;

    invoke-direct {v1, v0}, Lcom/exteragram/messenger/plugins/ui/components/SafeModeBottomSheet;-><init>(Lorg/telegram/ui/ActionBar/BaseFragment;)V

    invoke-virtual {v1}, Lorg/telegram/ui/ActionBar/BottomSheet;->show()V

    :cond_0
    return-void
.end method

.method public static $r8$lambda$DcwyhKBuZ-N-JhR0i98Zmug-Je0(Ljava/lang/String;)V
    .locals 2

    .line 916
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    .line 917
    sget v1, Lorg/telegram/messenger/NotificationCenter;->pluginSettingsUnregistered:I

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-virtual {v0, v1, p0}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    return-void
.end method

.method public static $r8$lambda$GRs7We6PFyg6apyapVWYinmnxH0(Lcom/exteragram/messenger/plugins/PluginsController;Ljava/lang/String;ZLorg/telegram/messenger/Utilities$Callback;)V
    .locals 1

    .line 332
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginEngine(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    move-result-object v0

    if-eqz v0, :cond_0

    if-eqz p1, :cond_0

    .line 334
    invoke-interface {v0, p1, p2, p3}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->setPluginEnabled(Ljava/lang/String;ZLorg/telegram/messenger/Utilities$Callback;)V

    .line 335
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->interestedPluginsCache:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p0}, Ljava/util/concurrent/ConcurrentHashMap;->clear()V

    :cond_0
    return-void
.end method

.method public static $r8$lambda$GqXcZtNTihu2o6QC3dyJOsjNgck(Lcom/exteragram/messenger/plugins/PluginsController;Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;)V
    .locals 0

    .line 342
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginEngine(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    move-result-object p0

    if-eqz p0, :cond_0

    if-eqz p1, :cond_0

    .line 344
    invoke-interface {p0, p1, p2}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->deletePlugin(Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;)V

    :cond_0
    return-void
.end method

.method public static $r8$lambda$IWnCzRTqaOHWXjeZmvg6EKw6nq0(Ljava/lang/String;ILcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;Lcom/exteragram/messenger/plugins/hooks/PluginsHooks$PostRequestResult;Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;
    .locals 8

    const/4 v0, 0x0

    if-eqz p3, :cond_0

    .line 986
    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/hooks/PluginsHooks$PostRequestResult;->getResponse()Lorg/telegram/tgnet/TLObject;

    move-result-object v1

    move-object v5, v1

    goto :goto_0

    :cond_0
    move-object v5, v0

    :goto_0
    if-eqz p3, :cond_1

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/hooks/PluginsHooks$PostRequestResult;->getError()Lorg/telegram/tgnet/TLRPC$TL_error;

    move-result-object v0

    :cond_1
    move-object v3, p0

    move v4, p1

    move-object v2, p2

    move-object v7, p4

    move-object v6, v0

    invoke-interface/range {v2 .. v7}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->executePostRequestHook(Ljava/lang/String;ILorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$JEt-MS2RwmV7tl-Tz-EeauQefL0()V
    .locals 3

    .line 923
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    .line 924
    sget v1, Lorg/telegram/messenger/NotificationCenter;->pluginMenuItemsUpdated:I

    const/4 v2, 0x0

    new-array v2, v2, [Ljava/lang/Object;

    invoke-virtual {v0, v1, v2}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    return-void
.end method

.method public static $r8$lambda$JqkXOllW4WPbvRAkDKW_90-FcXQ(Ljava/util/concurrent/atomic/AtomicInteger;Lcom/exteragram/messenger/plugins/PluginsController;Ljava/lang/Runnable;)V
    .locals 1

    .line 227
    invoke-virtual {p0}, Ljava/util/concurrent/atomic/AtomicInteger;->incrementAndGet()I

    move-result p0

    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getEngines()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentHashMap;->size()I

    move-result v0

    if-lt p0, v0, :cond_0

    .line 228
    iget-object p0, p1, Lcom/exteragram/messenger/plugins/PluginsController;->watchdog:Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->stop()V

    .line 229
    iget-object p0, p1, Lcom/exteragram/messenger/plugins/PluginsController;->plugins:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p0}, Ljava/util/concurrent/ConcurrentHashMap;->clear()V

    .line 230
    iget-object p0, p1, Lcom/exteragram/messenger/plugins/PluginsController;->settings:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p0}, Ljava/util/concurrent/ConcurrentHashMap;->clear()V

    const/4 p0, 0x0

    .line 231
    iput-boolean p0, p1, Lcom/exteragram/messenger/plugins/PluginsController;->initialized:Z

    .line 232
    const-string p0, "Plugin system shut down."

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    if-eqz p2, :cond_0

    .line 233
    invoke-interface {p2}, Ljava/lang/Runnable;->run()V

    :cond_0
    return-void
.end method

.method public static $r8$lambda$LvFWMJ2QaDesJqlG3S5k6tYhkXM(Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;)I
    .locals 0

    .line 684
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getPriority()I

    move-result p0

    return p0
.end method

.method public static $r8$lambda$MmT7vCRk4Nquw9rR9nIOFmGcmH0(ILcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;
    .locals 0

    .line 1018
    invoke-interface {p1, p0, p2, p3}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->executeSendMessageHook(ILorg/telegram/messenger/SendMessagesHelper$SendMessageParams;Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$NIummZv5ylJ7GWVYy9_HekCWNy0(Lcom/exteragram/messenger/plugins/PluginsController;Ljava/lang/String;)V
    .locals 3

    .line 419
    const-string v0, "Registered settings for plugin "

    .line 420
    :try_start_0
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginEngine(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    move-result-object v1

    if-nez v1, :cond_0

    return-void

    .line 421
    :cond_0
    invoke-interface {v1, p1}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->loadPluginSettings(Ljava/lang/String;)Ljava/util/List;

    move-result-object v1

    if-nez v1, :cond_1

    .line 423
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->invalidatePluginSettings(Ljava/lang/String;)V

    return-void

    :catchall_0
    move-exception v0

    goto :goto_0

    .line 427
    :cond_1
    iget-object v2, p0, Lcom/exteragram/messenger/plugins/PluginsController;->settings:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-interface {v2, p1, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 428
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    .line 429
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->notifyPluginSettingsRegistered(Ljava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-void

    .line 431
    :goto_0
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 432
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->invalidatePluginSettings(Ljava/lang/String;)V

    return-void
.end method

.method public static $r8$lambda$NrbUy-SgvJM8bTBfeR7YR1Brtxo(Ljava/util/concurrent/atomic/AtomicInteger;Lcom/exteragram/messenger/plugins/PluginsController;Ljava/lang/Runnable;)V
    .locals 1

    .line 195
    invoke-virtual {p0}, Ljava/util/concurrent/atomic/AtomicInteger;->incrementAndGet()I

    move-result p0

    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getEngines()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentHashMap;->size()I

    move-result v0

    if-lt p0, v0, :cond_0

    const/4 p0, 0x1

    .line 196
    iput-boolean p0, p1, Lcom/exteragram/messenger/plugins/PluginsController;->initialized:Z

    if-eqz p2, :cond_0

    .line 197
    invoke-interface {p2}, Ljava/lang/Runnable;->run()V

    :cond_0
    return-void
.end method

.method public static $r8$lambda$OFLyrLWoy1BqK-JytUTNBnTZ95Y(Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;)Z
    .locals 0

    .line 680
    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getItemId()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getItemId()Ljava/lang/String;

    move-result-object p0

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public static $r8$lambda$ObuTyILOrv8Lv83Lljknveq0jHI(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/concurrent/CopyOnWriteArrayList;
    .locals 0

    .line 675
    invoke-interface {p0, p1, p2}, Lkotlin/jvm/functions/Function2;->invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/util/concurrent/CopyOnWriteArrayList;

    return-object p0
.end method

.method public static $r8$lambda$PUoMq-cdhPvnEq59OGyG-EAdPqA(Ljava/util/Map$Entry;Ljava/util/Map$Entry;)I
    .locals 2

    .line 837
    invoke-interface {p1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Number;

    invoke-virtual {v0}, Ljava/lang/Number;->intValue()I

    move-result v0

    invoke-interface {p0}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Number;

    invoke-virtual {v1}, Ljava/lang/Number;->intValue()I

    move-result v1

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->compare(II)I

    move-result v0

    if-nez v0, :cond_0

    .line 839
    invoke-interface {p0}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/String;

    invoke-interface {p1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    invoke-virtual {p0, p1}, Ljava/lang/String;->compareTo(Ljava/lang/String;)I

    move-result p0

    return p0

    :cond_0
    return v0
.end method

.method public static $r8$lambda$PnMDHZ4aRveF9I0VgNHW2f1JUEU(Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;Ljava/lang/String;Ljava/util/concurrent/CopyOnWriteArrayList;)Ljava/util/concurrent/CopyOnWriteArrayList;
    .locals 0

    if-nez p2, :cond_0

    .line 677
    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    goto :goto_0

    .line 679
    :cond_0
    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1, p2}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    .line 680
    new-instance p2, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda33;

    invoke-direct {p2, p0}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda33;-><init>(Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;)V

    invoke-static {p1, p2}, Lkotlin/collections/CollectionsKt;->removeAll(Ljava/util/List;Lkotlin/jvm/functions/Function1;)Z

    .line 683
    :goto_0
    invoke-virtual {p1, p0}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 684
    new-instance p0, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda34;

    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda34;-><init>()V

    new-instance p2, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda35;

    invoke-direct {p2, p0}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda35;-><init>(Lkotlin/jvm/functions/Function1;)V

    invoke-static {p2}, Ljava/util/Comparator;->comparingInt(Ljava/util/function/ToIntFunction;)Ljava/util/Comparator;

    move-result-object p0

    .line 685
    invoke-interface {p0}, Ljava/util/Comparator;->reversed()Ljava/util/Comparator;

    move-result-object p0

    .line 684
    invoke-static {p1, p0}, Lkotlin/collections/CollectionsKt;->sortWith(Ljava/util/List;Ljava/util/Comparator;)V

    .line 686
    new-instance p0, Ljava/util/concurrent/CopyOnWriteArrayList;

    invoke-direct {p0, p1}, Ljava/util/concurrent/CopyOnWriteArrayList;-><init>(Ljava/util/Collection;)V

    return-object p0
.end method

.method public static $r8$lambda$QHTU7IyTiTCaSj6gAqHjOopsCdM(Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;Ljava/lang/String;Ljava/lang/Integer;)Ljava/lang/Integer;
    .locals 0

    if-nez p2, :cond_0

    .line 824
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;->getPriority()I

    move-result p0

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    return-object p0

    :cond_0
    invoke-virtual {p2}, Ljava/lang/Integer;->intValue()I

    move-result p1

    .line 826
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;->getPriority()I

    move-result p0

    invoke-static {p1, p0}, Ljava/lang/Math;->max(II)I

    move-result p0

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$RcBK-0orj8QEYVxVVjeONxndSuU(Ljava/lang/String;ILcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;Lorg/telegram/tgnet/TLRPC$Updates;Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;
    .locals 0

    .line 1006
    invoke-interface {p2, p0, p1, p3, p4}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->executeUpdatesHook(Ljava/lang/String;ILorg/telegram/tgnet/TLRPC$Updates;Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$SCSdz8X41keL06LCq_mlr4PCqFw(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Integer;
    .locals 0

    .line 823
    invoke-interface {p0, p1, p2}, Lkotlin/jvm/functions/Function2;->invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Integer;

    return-object p0
.end method

.method public static $r8$lambda$SzAjDsiXrUe0-Canxc1jh2pTN4A(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;)I
    .locals 0

    .line 684
    invoke-interface {p0, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Number;

    invoke-virtual {p0}, Ljava/lang/Number;->intValue()I

    move-result p0

    return p0
.end method

.method public static $r8$lambda$TRI271OAbh24WtM0hRmdWUfkDcA()V
    .locals 4

    .line 110
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    .line 111
    sget v1, Lorg/telegram/messenger/NotificationCenter;->pluginsUpdated:I

    const/4 v2, 0x0

    new-array v3, v2, [Ljava/lang/Object;

    invoke-virtual {v0, v1, v3}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    .line 112
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    .line 113
    sget v1, Lorg/telegram/messenger/NotificationCenter;->pluginMenuItemsUpdated:I

    new-array v2, v2, [Ljava/lang/Object;

    invoke-virtual {v0, v1, v2}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    return-void
.end method

.method public static $r8$lambda$TzwASltnFDcVN1Ktw3tUlrRKJck(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Integer;
    .locals 0

    .line 815
    invoke-interface {p0, p1, p2}, Lkotlin/jvm/functions/Function2;->invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Integer;

    return-object p0
.end method

.method public static $r8$lambda$V9DYDu372WRW1KDk_icvlxw3n-U(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;)Ljava/util/Set;
    .locals 0

    .line 565
    invoke-interface {p0, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/util/Set;

    return-object p0
.end method

.method public static $r8$lambda$WeWrzIubkO0GZO8e1wm7MHFmOjc(Ljava/lang/String;)Ljava/util/List;
    .locals 0

    .line 882
    new-instance p0, Ljava/util/ArrayList;

    invoke-direct {p0}, Ljava/util/ArrayList;-><init>()V

    return-object p0
.end method

.method public static $r8$lambda$Wj0eV-55jSQXjcbRqUevINmXVNM(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Ljava/lang/Object;)I
    .locals 0

    .line 836
    invoke-interface {p0, p1, p2}, Lkotlin/jvm/functions/Function2;->invoke(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Number;

    invoke-virtual {p0}, Ljava/lang/Number;->intValue()I

    move-result p0

    return p0
.end method

.method public static $r8$lambda$YLssiS3cthxbw0lcEiFr_hJJwFI(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;)Ljava/util/List;
    .locals 0

    .line 882
    invoke-interface {p0, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/util/List;

    return-object p0
.end method

.method public static $r8$lambda$ZPkFPPTQ3SLZXEDTuCogRsCGwUM(Lcom/exteragram/messenger/plugins/PluginsController;Z)V
    .locals 1

    .line 250
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsEngine()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 251
    new-instance v0, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda26;

    invoke-direct {v0}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda26;-><init>()V

    invoke-virtual {p0, p1, v0}, Lcom/exteragram/messenger/plugins/PluginsController;->init(ZLjava/lang/Runnable;)V

    :cond_0
    return-void
.end method

.method public static $r8$lambda$bFCGo4qve6jYoEhoW_CcYQyIBq8(Ljava/lang/String;)Ljava/util/Set;
    .locals 0

    .line 565
    new-instance p0, Ljava/util/concurrent/CopyOnWriteArraySet;

    invoke-direct {p0}, Ljava/util/concurrent/CopyOnWriteArraySet;-><init>()V

    return-object p0
.end method

.method public static $r8$lambda$eTddhbt-eklHVrAGcUqAfEmORgE(Ljava/lang/String;ILcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;Lorg/telegram/tgnet/TLObject;Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;
    .locals 0

    .line 972
    invoke-interface {p2, p0, p1, p3, p4}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->executePreRequestHook(Ljava/lang/String;ILorg/telegram/tgnet/TLObject;Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$emcHIMWtwJZR4u5gxpb7_ieRy4g(Ljava/lang/String;)V
    .locals 2

    .line 909
    invoke-static {}, Lorg/telegram/messenger/NotificationCenter;->getGlobalInstance()Lorg/telegram/messenger/NotificationCenter;

    move-result-object v0

    .line 910
    sget v1, Lorg/telegram/messenger/NotificationCenter;->pluginSettingsRegistered:I

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-virtual {v0, v1, p0}, Lorg/telegram/messenger/NotificationCenter;->postNotificationName(I[Ljava/lang/Object;)V

    return-void
.end method

.method public static $r8$lambda$htrrOUG8xUM_bIJfiVnNVxY_i18(Lde/robv/android/xposed/XC_MethodHook$Unhook;Lcom/exteragram/messenger/plugins/hooks/HookRecord;)Z
    .locals 1

    .line 638
    instance-of v0, p1, Lcom/exteragram/messenger/plugins/hooks/XposedHookRecord;

    if-eqz v0, :cond_0

    check-cast p1, Lcom/exteragram/messenger/plugins/hooks/XposedHookRecord;

    invoke-virtual {p1, p0}, Lcom/exteragram/messenger/plugins/hooks/XposedHookRecord;->matches(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public static $r8$lambda$irUSMfwTkkVsshZ-k4DQLFIDV2Q(Ljava/lang/String;Lcom/exteragram/messenger/plugins/hooks/HookRecord;)Z
    .locals 1

    .line 615
    instance-of v0, p1, Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;

    if-eqz v0, :cond_0

    check-cast p1, Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;->getHookName()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, p0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public static $r8$lambda$izbPeygnK0RqsbS2EM_K8BGUT8A(Lcom/exteragram/messenger/plugins/PluginsController;Ljava/lang/Runnable;)V
    .locals 2

    .line 225
    new-instance v0, Ljava/util/concurrent/atomic/AtomicInteger;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>(I)V

    .line 226
    new-instance v1, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda14;

    invoke-direct {v1, v0, p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda14;-><init>(Ljava/util/concurrent/atomic/AtomicInteger;Lcom/exteragram/messenger/plugins/PluginsController;Ljava/lang/Runnable;)V

    .line 237
    sget-object p0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getEngines()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object p0

    invoke-virtual {p0}, Ljava/util/concurrent/ConcurrentHashMap;->values()Ljava/util/Collection;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_0

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    .line 238
    invoke-interface {p1, v1}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->shutdown(Ljava/lang/Runnable;)V

    goto :goto_0

    :cond_0
    return-void
.end method

.method public static $r8$lambda$m3KeaGYDcYvARlD1kB8rCf1bUjY()V
    .locals 1

    .line 251
    const-string v0, "Plugins engine restarted."

    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    return-void
.end method

.method static constructor <clinit>()V
    .locals 3

    new-instance v0, Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    .line 1122
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    .line 1124
    new-instance v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;

    invoke-direct {v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;-><init>()V

    const-string/jumbo v2, "python"

    invoke-static {v2, v1}, Lkotlin/TuplesKt;->to(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;

    move-result-object v1

    .line 1123
    invoke-static {v1}, Lkotlin/collections/MapsKt;->mapOf(Lkotlin/Pair;)Ljava/util/Map;

    move-result-object v1

    .line 1122
    invoke-direct {v0, v1}, Ljava/util/concurrent/ConcurrentHashMap;-><init>(Ljava/util/Map;)V

    sput-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->enginesMap:Ljava/util/concurrent/ConcurrentHashMap;

    return-void
.end method

.method private constructor <init>()V
    .locals 3

    .line 68
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 70
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->plugins:Ljava/util/concurrent/ConcurrentHashMap;

    .line 72
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->settings:Ljava/util/concurrent/ConcurrentHashMap;

    .line 74
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->menuItemsById:Ljava/util/concurrent/ConcurrentHashMap;

    .line 76
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->menuItemsByMenuType:Ljava/util/concurrent/ConcurrentHashMap;

    .line 78
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->hooks:Ljava/util/concurrent/ConcurrentHashMap;

    .line 79
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->interestedPluginsCache:Ljava/util/concurrent/ConcurrentHashMap;

    .line 81
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->fileIconIdsByExtension:Ljava/util/concurrent/ConcurrentHashMap;

    .line 82
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->fileIconDrawablesById:Ljava/util/concurrent/ConcurrentHashMap;

    .line 83
    new-instance v0, Ljava/util/concurrent/atomic/AtomicInteger;

    const/16 v1, 0x65

    invoke-direct {v0, v1}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>(I)V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->nextFileIconId:Ljava/util/concurrent/atomic/AtomicInteger;

    .line 86
    invoke-static {}, Lkotlin/collections/CollectionsKt;->emptyList()Ljava/util/List;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->substringMatchEventHooksCache:Ljava/util/List;

    .line 89
    invoke-static {}, Lkotlin/collections/MapsKt;->emptyMap()Ljava/util/Map;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->exactMatchEventHooksCache:Ljava/util/Map;

    .line 91
    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->hooksCacheLock:Ljava/lang/Object;

    const/4 v0, 0x1

    .line 94
    iput-boolean v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->hooksCacheDirty:Z

    .line 99
    new-instance v0, Ljava/io/File;

    invoke-static {}, Lorg/telegram/messenger/ApplicationLoader;->getFilesDirFixed()Ljava/io/File;

    move-result-object v1

    const-string/jumbo v2, "plugins"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->pluginsDir:Ljava/io/File;

    .line 102
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    .line 103
    const-string/jumbo v1, "plugin_settings"

    const/4 v2, 0x0

    .line 102
    invoke-virtual {v0, v1, v2}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->preferences:Landroid/content/SharedPreferences;

    .line 107
    new-instance v0, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;-><init>(Lcom/exteragram/messenger/plugins/PluginsController;)V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->watchdog:Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    .line 109
    new-instance v0, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda15;

    invoke-direct {v0}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda15;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->updateNotificationRunnable:Ljava/lang/Runnable;

    return-void
.end method

.method public synthetic constructor <init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PluginsController;-><init>()V

    return-void
.end method

.method public static final synthetic access$clearFileIconsInternal(Lcom/exteragram/messenger/plugins/PluginsController;)V
    .locals 0

    .line 68
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->clearFileIconsInternal()V

    return-void
.end method

.method public static final synthetic access$getEnginesMap$cp()Ljava/util/concurrent/ConcurrentHashMap;
    .locals 1

    .line 68
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->enginesMap:Ljava/util/concurrent/ConcurrentHashMap;

    return-object v0
.end method

.method public static final synthetic access$getFileIconDrawablesById$p(Lcom/exteragram/messenger/plugins/PluginsController;)Ljava/util/concurrent/ConcurrentHashMap;
    .locals 0

    .line 68
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->fileIconDrawablesById:Ljava/util/concurrent/ConcurrentHashMap;

    return-object p0
.end method

.method public static final synthetic access$getFileIconIdInternal(Lcom/exteragram/messenger/plugins/PluginsController;Ljava/lang/String;)I
    .locals 0

    .line 68
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->getFileIconIdInternal(Ljava/lang/String;)I

    move-result p0

    return p0
.end method

.method public static final synthetic access$registerFileIconInternal(Lcom/exteragram/messenger/plugins/PluginsController;Ljava/lang/String;Landroid/graphics/drawable/Drawable;)I
    .locals 0

    .line 68
    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/plugins/PluginsController;->registerFileIconInternal(Ljava/lang/String;Landroid/graphics/drawable/Drawable;)I

    move-result p0

    return p0
.end method

.method public static final synthetic access$unregisterFileIconInternal(Lcom/exteragram/messenger/plugins/PluginsController;Ljava/lang/String;)V
    .locals 0

    .line 68
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->unregisterFileIconInternal(Ljava/lang/String;)V

    return-void
.end method

.method private final addHook(Ljava/lang/String;Lcom/exteragram/messenger/plugins/hooks/HookRecord;Ljava/lang/String;)V
    .locals 3

    if-eqz p1, :cond_2

    .line 562
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    if-nez p2, :cond_1

    goto :goto_0

    .line 565
    :cond_1
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->hooks:Ljava/util/concurrent/ConcurrentHashMap;

    new-instance v1, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda18;

    invoke-direct {v1}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda18;-><init>()V

    new-instance v2, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda19;

    invoke-direct {v2, v1}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda19;-><init>(Lkotlin/jvm/functions/Function1;)V

    invoke-virtual {v0, p1, v2}, Ljava/util/concurrent/ConcurrentHashMap;->computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/util/Set;

    .line 566
    invoke-interface {p1, p2}, Ljava/util/Set;->add(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_2

    .line 567
    invoke-static {p3}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    .line 568
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->interestedPluginsCache:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p1}, Ljava/util/concurrent/ConcurrentHashMap;->clear()V

    const/4 p1, 0x1

    .line 569
    iput-boolean p1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->hooksCacheDirty:Z

    :cond_2
    :goto_0
    return-void
.end method

.method public static final applyArtOpts()V
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->applyArtOpts()V

    return-void
.end method

.method public static final clearFileIcons()V
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->clearFileIcons()V

    return-void
.end method

.method private final clearFileIconsInternal()V
    .locals 1

    .line 308
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->fileIconIdsByExtension:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentHashMap;->clear()V

    .line 309
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->fileIconDrawablesById:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p0}, Ljava/util/concurrent/ConcurrentHashMap;->clear()V

    return-void
.end method

.method public static synthetic clearPluginSettingsPreferences$default(Lcom/exteragram/messenger/plugins/PluginsController;Ljava/lang/String;ZILjava/lang/Object;)V
    .locals 0

    and-int/lit8 p3, p3, 0x2

    if-eqz p3, :cond_0

    const/4 p2, 0x0

    .line 459
    :cond_0
    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/plugins/PluginsController;->clearPluginSettingsPreferences(Ljava/lang/String;Z)V

    return-void
.end method

.method private final ensurePreferences()V
    .locals 3

    .line 895
    sget-object v0, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    .line 896
    const-string/jumbo v1, "plugin_settings"

    const/4 v2, 0x0

    .line 895
    invoke-virtual {v0, v1, v2}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->preferences:Landroid/content/SharedPreferences;

    return-void
.end method

.method private final executeGenericHook(Ljava/lang/String;Ljava/lang/Object;Lcom/exteragram/messenger/plugins/PluginsController$EngineHookCaller;)Ljava/lang/Object;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/lang/String;",
            "TT;",
            "Lcom/exteragram/messenger/plugins/PluginsController$EngineHookCaller<",
            "TT;>;)TT;"
        }
    .end annotation

    .line 933
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->isPluginEngineAvailable()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 937
    :cond_0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->getInterestedPluginIds(Ljava/lang/String;)Ljava/util/List;

    move-result-object p1

    .line 938
    invoke-interface {p1}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_1

    :goto_0
    return-object p2

    .line 943
    :cond_1
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_2
    :goto_1
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_6

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 944
    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/plugins/PluginsController;->isPluginActive$TMessagesProj(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_3

    goto :goto_1

    .line 947
    :cond_3
    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginEngine(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    move-result-object v1

    if-eqz v1, :cond_2

    .line 949
    iget-object v2, p0, Lcom/exteragram/messenger/plugins/PluginsController;->watchdog:Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    invoke-virtual {v2, v0}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionStarted(Ljava/lang/String;)V

    .line 951
    :try_start_0
    invoke-interface {p3, v1, p2, v0}, Lcom/exteragram/messenger/plugins/PluginsController$EngineHookCaller;->call(Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;Ljava/lang/Object;Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;

    move-result-object p2

    .line 952
    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/PluginsController$HookResult;->getResult()Ljava/lang/Object;

    move-result-object v1

    .line 953
    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/PluginsController$HookResult;->getCancel()Z

    move-result v2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v2, :cond_4

    .line 959
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->watchdog:Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionFinished(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    .line 955
    :cond_4
    :try_start_1
    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/PluginsController$HookResult;->isFinal()Z

    move-result p2
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 959
    iget-object v2, p0, Lcom/exteragram/messenger/plugins/PluginsController;->watchdog:Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    if-eqz p2, :cond_5

    invoke-virtual {v2, v0}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionFinished(Ljava/lang/String;)V

    return-object v1

    :cond_5
    invoke-virtual {v2, v0}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionFinished(Ljava/lang/String;)V

    move-object p2, v1

    goto :goto_1

    :catchall_0
    move-exception p1

    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->watchdog:Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionFinished(Ljava/lang/String;)V

    throw p1

    :cond_6
    return-object p2
.end method

.method public static final getEngines()Ljava/util/concurrent/ConcurrentHashMap;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;",
            ">;"
        }
    .end annotation

    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getEngines()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v0

    return-object v0
.end method

.method public static final getFileIconId(Ljava/lang/String;)I
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getFileIconId(Ljava/lang/String;)I

    move-result p0

    return p0
.end method

.method private final getFileIconIdInternal(Ljava/lang/String;)I
    .locals 8

    const/4 v0, -0x1

    if-eqz p1, :cond_4

    .line 293
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v1

    if-nez v1, :cond_0

    goto :goto_1

    :cond_0
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->fileIconIdsByExtension:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v1}, Ljava/util/concurrent/ConcurrentHashMap;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_1

    goto :goto_1

    :cond_1
    const/4 v6, 0x6

    const/4 v7, 0x0

    const/16 v3, 0x2e

    const/4 v4, 0x0

    const/4 v5, 0x0

    move-object v2, p1

    .line 296
    invoke-static/range {v2 .. v7}, Lkotlin/text/StringsKt;->lastIndexOf$default(Ljava/lang/CharSequence;CIZILjava/lang/Object;)I

    move-result p1

    if-ltz p1, :cond_2

    add-int/lit8 p1, p1, 0x1

    .line 298
    invoke-virtual {v2, p1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_2
    move-object p1, v2

    .line 302
    :goto_0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->normalizeFileExtension(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    if-nez p1, :cond_3

    return v0

    .line 304
    :cond_3
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->fileIconIdsByExtension:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/Integer;

    if-eqz p0, :cond_4

    invoke-virtual {p0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    return p0

    :cond_4
    :goto_1
    return v0
.end method

.method public static final getInstance()Lcom/exteragram/messenger/plugins/PluginsController;
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    return-object v0
.end method

.method private final getInterestedPluginIds(Ljava/lang/String;)Ljava/util/List;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    if-eqz p1, :cond_c

    .line 804
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto/16 :goto_5

    .line 808
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->interestedPluginsCache:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/List;

    if-nez v0, :cond_a

    .line 810
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->rebuildHooksCacheIfNeeded()V

    .line 811
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    .line 813
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->exactMatchEventHooksCache:Ljava/util/Map;

    invoke-interface {v1, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/List;

    if-eqz v1, :cond_2

    check-cast v1, Ljava/lang/Iterable;

    .line 1915
    invoke-interface {v1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_2

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;

    .line 814
    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;->getPluginId()Ljava/lang/String;

    move-result-object v3

    if-nez v3, :cond_1

    goto :goto_0

    .line 815
    :cond_1
    new-instance v4, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda27;

    invoke-direct {v4, v2}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda27;-><init>(Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;)V

    new-instance v2, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda28;

    invoke-direct {v2, v4}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda28;-><init>(Lkotlin/jvm/functions/Function2;)V

    invoke-virtual {v0, v3, v2}, Ljava/util/HashMap;->compute(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;

    goto :goto_0

    .line 820
    :cond_2
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->substringMatchEventHooksCache:Ljava/util/List;

    invoke-interface {v1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_3
    :goto_1
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_5

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;

    .line 821
    invoke-virtual {v2, p1}, Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;->matches(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_3

    .line 822
    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;->getPluginId()Ljava/lang/String;

    move-result-object v3

    if-nez v3, :cond_4

    goto :goto_1

    .line 823
    :cond_4
    new-instance v4, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda29;

    invoke-direct {v4, v2}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda29;-><init>(Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;)V

    new-instance v2, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda30;

    invoke-direct {v2, v4}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda30;-><init>(Lkotlin/jvm/functions/Function2;)V

    invoke-virtual {v0, v3, v2}, Ljava/util/HashMap;->compute(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;

    goto :goto_1

    .line 832
    :cond_5
    invoke-virtual {v0}, Ljava/util/HashMap;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_6

    .line 833
    sget-object v0, Ljava/util/Collections;->EMPTY_LIST:Ljava/util/List;

    goto :goto_3

    .line 835
    :cond_6
    new-instance v1, Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/HashMap;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-direct {v1, v0}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    .line 836
    new-instance v0, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda31;

    invoke-direct {v0}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda31;-><init>()V

    new-instance v2, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda32;

    invoke-direct {v2, v0}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda32;-><init>(Lkotlin/jvm/functions/Function2;)V

    invoke-static {v1, v2}, Lkotlin/collections/CollectionsKt;->sortWith(Ljava/util/List;Ljava/util/Comparator;)V

    .line 845
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 846
    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_7
    :goto_2
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_8

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/Map$Entry;

    .line 847
    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    invoke-virtual {p0, v3}, Lcom/exteragram/messenger/plugins/PluginsController;->isPluginActive$TMessagesProj(Ljava/lang/String;)Z

    move-result v3

    if-eqz v3, :cond_7

    .line 848
    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_2

    .line 854
    :cond_8
    :goto_3
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->interestedPluginsCache:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-interface {p0, p1, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 855
    move-object p0, v0

    check-cast p0, Ljava/util/Collection;

    if-eqz p0, :cond_a

    invoke-interface {p0}, Ljava/util/Collection;->isEmpty()Z

    move-result p0

    if-eqz p0, :cond_9

    goto :goto_4

    .line 856
    :cond_9
    new-instance p0, Ljava/lang/StringBuilder;

    const-string v1, "Calculated and cached potential plugins for \'"

    invoke-direct {p0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "\': "

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    :cond_a
    :goto_4
    if-nez v0, :cond_b

    .line 860
    sget-object p0, Ljava/util/Collections;->EMPTY_LIST:Ljava/util/List;

    return-object p0

    :cond_b
    return-object v0

    .line 805
    :cond_c
    :goto_5
    sget-object p0, Ljava/util/Collections;->EMPTY_LIST:Ljava/util/List;

    return-object p0
.end method

.method public static final getPluginEngine(Ljava/io/File;)Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 0
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getPluginEngine(Ljava/io/File;)Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    move-result-object p0

    return-object p0
.end method

.method public static final getPluginFileIconDrawable(I)Landroid/graphics/drawable/Drawable;
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getPluginFileIconDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object p0

    return-object p0
.end method

.method private final isOnPluginsQueueThread()Z
    .locals 1

    .line 902
    sget-object p0, Lorg/telegram/messenger/Utilities;->pluginsQueue:Lorg/telegram/messenger/DispatchQueue;

    if-eqz p0, :cond_0

    .line 903
    sget-object p0, Lorg/telegram/messenger/Utilities;->pluginsQueue:Lorg/telegram/messenger/DispatchQueue;

    invoke-virtual {p0}, Lorg/telegram/messenger/DispatchQueue;->getHandler()Landroid/os/Handler;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 904
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object p0

    sget-object v0, Lorg/telegram/messenger/Utilities;->pluginsQueue:Lorg/telegram/messenger/DispatchQueue;

    invoke-virtual {v0}, Lorg/telegram/messenger/DispatchQueue;->getHandler()Landroid/os/Handler;

    move-result-object v0

    invoke-virtual {v0}, Landroid/os/Handler;->getLooper()Landroid/os/Looper;

    move-result-object v0

    invoke-virtual {v0}, Landroid/os/Looper;->getThread()Ljava/lang/Thread;

    move-result-object v0

    invoke-static {p0, v0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public static final isPlugin(Ljava/io/File;Lorg/telegram/messenger/MessageObject;)Z
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 0
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0, p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->isPlugin(Ljava/io/File;Lorg/telegram/messenger/MessageObject;)Z

    move-result p0

    return p0
.end method

.method public static final isPlugin(Lorg/telegram/messenger/MessageObject;)Z
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 0
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->isPlugin(Lorg/telegram/messenger/MessageObject;)Z

    move-result p0

    return p0
.end method

.method public static final isPluginEngineAvailable()Z
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->isPluginEngineAvailable()Z

    move-result v0

    return v0
.end method

.method public static final isPluginEngineSupported()Z
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->isPluginEngineSupported()Z

    move-result v0

    return v0
.end method

.method public static final isPluginFileIcon(I)Z
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->isPluginFileIcon(I)Z

    move-result p0

    return p0
.end method

.method public static final isPluginPinned(Ljava/lang/String;)Z
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->isPluginPinned(Ljava/lang/String;)Z

    move-result p0

    return p0
.end method

.method private final normalizeFileExtension(Ljava/lang/String;)Ljava/lang/String;
    .locals 0

    if-eqz p1, :cond_1

    .line 313
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result p0

    if-nez p0, :cond_0

    goto :goto_0

    .line 316
    :cond_0
    sget-object p0, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-virtual {p1, p0}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_1
    :goto_0
    const/4 p0, 0x0

    return-object p0
.end method

.method private final notifyMenuItemsUpdated()V
    .locals 0

    .line 922
    new-instance p0, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda11;

    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda11;-><init>()V

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private final notifyPluginSettingsRegistered(Ljava/lang/String;)V
    .locals 0

    .line 908
    new-instance p0, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda7;

    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda7;-><init>(Ljava/lang/String;)V

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method private final notifyPluginSettingsUnregistered(Ljava/lang/String;)V
    .locals 0

    .line 915
    new-instance p0, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda24;

    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda24;-><init>(Ljava/lang/String;)V

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static final openPluginSettings(Ljava/lang/String;)V
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 0
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->openPluginSettings(Ljava/lang/String;)V

    return-void
.end method

.method public static final openPluginSettings(Ljava/lang/String;Ljava/lang/String;)V
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    .line 0
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0, p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->openPluginSettings(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method private final rebuildHooksCacheIfNeeded()V
    .locals 9

    .line 864
    iget-boolean v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->hooksCacheDirty:Z

    if-nez v0, :cond_0

    return-void

    .line 867
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->hooksCacheLock:Ljava/lang/Object;

    monitor-enter v0

    .line 868
    :try_start_0
    iget-boolean v1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->hooksCacheDirty:Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-nez v1, :cond_1

    .line 869
    monitor-exit v0

    return-void

    .line 872
    :cond_1
    :try_start_1
    new-instance v1, Ljava/util/HashMap;

    invoke-direct {v1}, Ljava/util/HashMap;-><init>()V

    .line 873
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    .line 875
    iget-object v3, p0, Lcom/exteragram/messenger/plugins/PluginsController;->hooks:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v3}, Ljava/util/concurrent/ConcurrentHashMap;->values()Ljava/util/Collection;

    move-result-object v3

    invoke-interface {v3}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :cond_2
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_6

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/util/Set;

    .line 876
    invoke-interface {v4}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v4

    :cond_3
    :goto_0
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_2

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/exteragram/messenger/plugins/hooks/HookRecord;

    .line 877
    instance-of v6, v5, Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;

    if-eqz v6, :cond_3

    .line 878
    move-object v6, v5

    check-cast v6, Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;

    invoke-virtual {v6}, Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;->isMatchSubstring()Z

    move-result v6

    if-eqz v6, :cond_4

    .line 879
    invoke-virtual {v2, v5}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_1

    .line 881
    :cond_4
    move-object v6, v5

    check-cast v6, Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;

    invoke-virtual {v6}, Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;->getHookName()Ljava/lang/String;

    move-result-object v6

    if-nez v6, :cond_5

    goto :goto_0

    .line 882
    :cond_5
    new-instance v7, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda20;

    invoke-direct {v7}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda20;-><init>()V

    new-instance v8, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda21;

    invoke-direct {v8, v7}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda21;-><init>(Lkotlin/jvm/functions/Function1;)V

    invoke-virtual {v1, v6, v8}, Ljava/util/HashMap;->computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/util/List;

    invoke-interface {v6, v5}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 888
    :cond_6
    iput-object v1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->exactMatchEventHooksCache:Ljava/util/Map;

    .line 889
    iput-object v2, p0, Lcom/exteragram/messenger/plugins/PluginsController;->substringMatchEventHooksCache:Ljava/util/List;

    const/4 v1, 0x0

    .line 890
    iput-boolean v1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->hooksCacheDirty:Z

    .line 891
    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 867
    monitor-exit v0

    return-void

    :goto_1
    monitor-exit v0

    throw p0
.end method

.method public static final registerFileIcon(Ljava/lang/String;Landroid/graphics/drawable/Drawable;)I
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0, p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->registerFileIcon(Ljava/lang/String;Landroid/graphics/drawable/Drawable;)I

    move-result p0

    return p0
.end method

.method private final registerFileIconInternal(Ljava/lang/String;Landroid/graphics/drawable/Drawable;)I
    .locals 3

    .line 269
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->normalizeFileExtension(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    if-eqz p1, :cond_3

    if-nez p2, :cond_0

    goto :goto_0

    .line 274
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->fileIconIdsByExtension:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Integer;

    if-eqz v0, :cond_1

    .line 276
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->fileIconDrawablesById:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-interface {p0, v0, p2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 277
    invoke-virtual {v0}, Ljava/lang/Integer;->intValue()I

    move-result p0

    return p0

    .line 280
    :cond_1
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->nextFileIconId:Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-virtual {v0}, Ljava/util/concurrent/atomic/AtomicInteger;->getAndIncrement()I

    move-result v0

    .line 281
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->fileIconIdsByExtension:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v1, p1, v2}, Ljava/util/concurrent/ConcurrentHashMap;->putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    if-eqz p1, :cond_2

    invoke-virtual {p1}, Ljava/lang/Integer;->intValue()I

    move-result v0

    .line 282
    :cond_2
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->fileIconDrawablesById:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    invoke-interface {p0, p1, p2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return v0

    :cond_3
    :goto_0
    const/4 p0, -0x1

    return p0
.end method

.method private final removeHook(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Ljava/lang/String;)V
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Lkotlin/jvm/functions/Function1<",
            "-",
            "Lcom/exteragram/messenger/plugins/hooks/HookRecord;",
            "Ljava/lang/Boolean;",
            ">;",
            "Ljava/lang/String;",
            ")V"
        }
    .end annotation

    if-eqz p1, :cond_6

    .line 582
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto/16 :goto_3

    .line 585
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->hooks:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Set;

    if-eqz v0, :cond_6

    .line 586
    invoke-interface {v0}, Ljava/util/Collection;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_1

    goto :goto_3

    .line 587
    :cond_1
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 588
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    .line 589
    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_3

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/exteragram/messenger/plugins/hooks/HookRecord;

    .line 590
    invoke-interface {p2, v3}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/Boolean;

    invoke-virtual {v4}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v4

    if-eqz v4, :cond_2

    .line 591
    invoke-virtual {v2, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 593
    :cond_2
    invoke-virtual {v1, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 597
    :cond_3
    invoke-interface {v2}, Ljava/util/Collection;->isEmpty()Z

    move-result p2

    if-nez p2, :cond_6

    .line 1915
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result p2

    const/4 v0, 0x0

    :goto_1
    if-ge v0, p2, :cond_4

    invoke-virtual {v2, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v3

    add-int/lit8 v0, v0, 0x1

    check-cast v3, Lcom/exteragram/messenger/plugins/hooks/HookRecord;

    .line 598
    invoke-interface {v3}, Lcom/exteragram/messenger/plugins/hooks/HookRecord;->cleanup()V

    goto :goto_1

    .line 600
    :cond_4
    invoke-virtual {v1}, Ljava/util/ArrayList;->isEmpty()Z

    move-result p2

    .line 603
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->hooks:Ljava/util/concurrent/ConcurrentHashMap;

    if-eqz p2, :cond_5

    .line 601
    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_2

    .line 603
    :cond_5
    new-instance p2, Ljava/util/concurrent/CopyOnWriteArraySet;

    invoke-direct {p2, v1}, Ljava/util/concurrent/CopyOnWriteArraySet;-><init>(Ljava/util/Collection;)V

    invoke-interface {v0, p1, p2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 606
    :goto_2
    invoke-static {p3}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    .line 607
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->interestedPluginsCache:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p1}, Ljava/util/concurrent/ConcurrentHashMap;->clear()V

    const/4 p1, 0x1

    .line 608
    iput-boolean p1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->hooksCacheDirty:Z

    :cond_6
    :goto_3
    return-void
.end method

.method public static final runOnPluginsQueue(Ljava/lang/Runnable;)V
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->runOnPluginsQueue(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static final setPluginPinned(Ljava/lang/String;Z)V
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0, p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->setPluginPinned(Ljava/lang/String;Z)V

    return-void
.end method

.method private final showInstallDialog(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;)V
    .locals 7

    if-eqz p1, :cond_4

    .line 380
    invoke-virtual {p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->isActivityRunning(Landroid/app/Activity;)Z

    move-result p0

    if-eqz p0, :cond_4

    if-eqz p2, :cond_0

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getFilePath()Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    if-eqz p0, :cond_4

    invoke-interface {p0}, Ljava/lang/CharSequence;->length()I

    move-result p0

    if-nez p0, :cond_1

    goto :goto_1

    .line 384
    :cond_1
    new-instance p0, Ljava/io/File;

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getFilePath()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 385
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsEngine()Z

    move-result v0

    if-nez v0, :cond_2

    .line 386
    invoke-static {p1}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object v1

    .line 388
    sget v2, Lorg/telegram/messenger/R$raw;->error:I

    .line 389
    sget p2, Lorg/telegram/messenger/R$string;->PluginNotEnabled:I

    invoke-virtual {p0}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object p0

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-static {p2, p0}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    .line 390
    sget p0, Lorg/telegram/messenger/R$string;->Enable:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    .line 387
    new-instance v6, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda9;

    invoke-direct {v6, p1}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda9;-><init>(Lorg/telegram/ui/ActionBar/BaseFragment;)V

    const/16 v5, 0xabe

    invoke-virtual/range {v1 .. v6}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/Runnable;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 393
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void

    .line 397
    :cond_2
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getPluginEngine(Ljava/io/File;)Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    move-result-object p0

    if-nez p0, :cond_3

    goto :goto_1

    .line 398
    :cond_3
    invoke-interface {p0, p1, p2}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->showInstallDialog(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;)V

    :cond_4
    :goto_1
    return-void
.end method

.method public static final unregisterFileIcon(Ljava/lang/String;)V
    .locals 1
    .annotation runtime Lkotlin/jvm/JvmStatic;
    .end annotation

    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->unregisterFileIcon(Ljava/lang/String;)V

    return-void
.end method

.method private final unregisterFileIconInternal(Ljava/lang/String;)V
    .locals 1

    .line 287
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->normalizeFileExtension(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    if-nez p1, :cond_0

    goto :goto_0

    .line 288
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->fileIconIdsByExtension:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Integer;

    if-eqz p1, :cond_1

    .line 289
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->fileIconDrawablesById:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_1
    :goto_0
    return-void
.end method


# virtual methods
.method public final addEventHook(Ljava/lang/String;Ljava/lang/String;ZI)V
    .locals 1

    .line 576
    new-instance v0, Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;

    invoke-direct {v0, p1, p2, p3, p4}, Lcom/exteragram/messenger/plugins/hooks/EventHookRecord;-><init>(Ljava/lang/String;Ljava/lang/String;ZI)V

    .line 577
    new-instance p3, Ljava/lang/StringBuilder;

    const-string p4, "Added event hook \'"

    invoke-direct {p3, p4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p2, "\' for plugin "

    invoke-virtual {p3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    .line 574
    invoke-direct {p0, p1, v0, p2}, Lcom/exteragram/messenger/plugins/PluginsController;->addHook(Ljava/lang/String;Lcom/exteragram/messenger/plugins/hooks/HookRecord;Ljava/lang/String;)V

    return-void
.end method

.method public final addMenuItem(Ljava/lang/String;Lcom/chaquo/python/PyObject;)Ljava/lang/String;
    .locals 6

    .line 657
    const-string v0, "Added menu item: "

    const-string v1, "Plugin "

    sget-object v2, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->isPluginEngineAvailable()Z

    move-result v2

    const/4 v3, 0x0

    if-eqz v2, :cond_5

    if-eqz p2, :cond_5

    if-eqz p1, :cond_5

    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v2

    if-nez v2, :cond_0

    goto/16 :goto_0

    .line 661
    :cond_0
    :try_start_0
    new-instance v2, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;

    invoke-direct {v2, p1, p2}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;-><init>(Ljava/lang/String;Lcom/chaquo/python/PyObject;)V

    .line 662
    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getMenuType()Ljava/lang/String;

    move-result-object p2

    if-nez p2, :cond_1

    return-object v3

    .line 664
    :cond_1
    iget-object v4, p0, Lcom/exteragram/messenger/plugins/PluginsController;->menuItemsById:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getItemId()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;

    if-eqz v4, :cond_2

    .line 665
    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getPluginId()Ljava/lang/String;

    move-result-object v5

    invoke-static {v5, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v5

    if-nez v5, :cond_2

    .line 666
    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getItemId()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getPluginId()Ljava/lang/String;

    move-result-object p2

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " tried to add a menu item: "

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ", which is already used by plugin "

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->w(Ljava/lang/String;)V

    return-object v3

    :cond_2
    if-eqz v4, :cond_4

    .line 670
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->menuItemsByMenuType:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getMenuType()Ljava/lang/String;

    move-result-object v5

    invoke-interface {v1, v5}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/concurrent/CopyOnWriteArrayList;

    if-eqz v1, :cond_3

    invoke-virtual {v1, v4}, Ljava/util/concurrent/CopyOnWriteArrayList;->remove(Ljava/lang/Object;)Z

    .line 671
    :cond_3
    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->markRemoved()V

    .line 674
    :cond_4
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->menuItemsById:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getItemId()Ljava/lang/String;

    move-result-object v4

    invoke-interface {v1, v4, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 675
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->menuItemsByMenuType:Ljava/util/concurrent/ConcurrentHashMap;

    new-instance v4, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda3;

    invoke-direct {v4, v2}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda3;-><init>(Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;)V

    new-instance v5, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda4;

    invoke-direct {v5, v4}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda4;-><init>(Lkotlin/jvm/functions/Function2;)V

    invoke-virtual {v1, p2, v5}, Ljava/util/concurrent/ConcurrentHashMap;->compute(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;

    .line 688
    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getItemId()Ljava/lang/String;

    move-result-object v1

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, " for plugin "

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " in type "

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    .line 689
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->notifyMenuItemsUpdated()V

    .line 690
    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getItemId()Ljava/lang/String;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    :cond_5
    :goto_0
    return-object v3
.end method

.method public final addXposedHook(Ljava/lang/String;Lde/robv/android/xposed/XC_MethodHook$Unhook;)V
    .locals 2

    if-eqz p2, :cond_0

    .line 622
    new-instance v0, Lcom/exteragram/messenger/plugins/hooks/XposedHookRecord;

    invoke-direct {v0, p2}, Lcom/exteragram/messenger/plugins/hooks/XposedHookRecord;-><init>(Lde/robv/android/xposed/XC_MethodHook$Unhook;)V

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    .line 623
    :goto_0
    new-instance p2, Ljava/lang/StringBuilder;

    const-string v1, "Added Xposed hook for plugin "

    invoke-direct {p2, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    .line 620
    invoke-direct {p0, p1, v0, p2}, Lcom/exteragram/messenger/plugins/PluginsController;->addHook(Ljava/lang/String;Lcom/exteragram/messenger/plugins/hooks/HookRecord;Ljava/lang/String;)V

    return-void
.end method

.method public final addXposedHooks(Ljava/lang/String;Ljava/util/ArrayList;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/ArrayList<",
            "Lde/robv/android/xposed/XC_MethodHook$Unhook;",
            ">;)V"
        }
    .end annotation

    if-nez p2, :cond_0

    goto :goto_1

    .line 631
    :cond_0
    invoke-virtual {p2}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object p2

    :goto_0
    invoke-interface {p2}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {p2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lde/robv/android/xposed/XC_MethodHook$Unhook;

    .line 632
    invoke-virtual {p0, p1, v0}, Lcom/exteragram/messenger/plugins/PluginsController;->addXposedHook(Ljava/lang/String;Lde/robv/android/xposed/XC_MethodHook$Unhook;)V

    goto :goto_0

    :cond_1
    :goto_1
    return-void
.end method

.method public final checkDevServers()V
    .locals 1

    .line 214
    sget-object p0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getEngines()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object p0

    invoke-virtual {p0}, Ljava/util/concurrent/ConcurrentHashMap;->values()Ljava/util/Collection;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    .line 215
    invoke-interface {v0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->checkDevServer()V

    goto :goto_0

    :cond_0
    return-void
.end method

.method public final cleanupPlugin(Ljava/lang/String;)V
    .locals 0

    .line 350
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->removeHooksByPluginId(Ljava/lang/String;)V

    .line 351
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->invalidatePluginSettings(Ljava/lang/String;)V

    .line 352
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->removeMenuItemsByPluginId(Ljava/lang/String;)V

    return-void
.end method

.method public final clearPluginSettingsPreferences(Ljava/lang/String;)V
    .locals 3
    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    .line 0
    const/4 v0, 0x2

    const/4 v1, 0x0

    const/4 v2, 0x0

    invoke-static {p0, p1, v2, v0, v1}, Lcom/exteragram/messenger/plugins/PluginsController;->clearPluginSettingsPreferences$default(Lcom/exteragram/messenger/plugins/PluginsController;Ljava/lang/String;ZILjava/lang/Object;)V

    return-void
.end method

.method public final clearPluginSettingsPreferences(Ljava/lang/String;Z)V
    .locals 2
    .annotation build Lkotlin/jvm/JvmOverloads;
    .end annotation

    if-eqz p1, :cond_3

    .line 461
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_2

    .line 465
    :cond_0
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginEngine(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 467
    invoke-interface {v0, p1}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->clearPluginSettings(Ljava/lang/String;)V

    goto :goto_1

    .line 469
    :cond_1
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getEngines()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentHashMap;->values()Ljava/util/Collection;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    .line 470
    invoke-interface {v1, p1}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->clearPluginSettings(Ljava/lang/String;)V

    goto :goto_0

    .line 474
    :cond_2
    :goto_1
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->ensurePreferences()V

    if-eqz p2, :cond_3

    .line 476
    new-instance p2, Ljava/lang/StringBuilder;

    const-string/jumbo v0, "plugin_enabled_"

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 477
    iget-object p2, p0, Lcom/exteragram/messenger/plugins/PluginsController;->preferences:Landroid/content/SharedPreferences;

    invoke-interface {p2, p1}, Landroid/content/SharedPreferences;->contains(Ljava/lang/String;)Z

    move-result p2

    if-eqz p2, :cond_3

    .line 478
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->preferences:Landroid/content/SharedPreferences;

    .line 45
    invoke-interface {p0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p0

    .line 478
    invoke-interface {p0, p1}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 50
    invoke-interface {p0}, Landroid/content/SharedPreferences$Editor;->apply()V

    :cond_3
    :goto_2
    return-void
.end method

.method public final deletePlugin(Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Lorg/telegram/messenger/Utilities$Callback<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    .line 341
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    new-instance v1, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda36;

    invoke-direct {v1, p0, p1, p2}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda36;-><init>(Lcom/exteragram/messenger/plugins/PluginsController;Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;)V

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->runOnPluginsQueue(Ljava/lang/Runnable;)V

    return-void
.end method

.method public final executeOnAppEvent(Ljava/lang/String;)V
    .locals 1

    .line 793
    iget-boolean p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->initialized:Z

    if-eqz p0, :cond_1

    sget-object p0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->isPluginEngineAvailable()Z

    move-result v0

    if-eqz v0, :cond_1

    if-nez p1, :cond_0

    goto :goto_1

    .line 797
    :cond_0
    const-string v0, "Execute scripts on app event "

    invoke-virtual {v0, p1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    .line 798
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getEngines()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object p0

    invoke-virtual {p0}, Ljava/util/concurrent/ConcurrentHashMap;->values()Ljava/util/Collection;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    .line 799
    invoke-interface {v0, p1}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->executeOnAppEvent(Ljava/lang/String;)V

    goto :goto_0

    :cond_1
    :goto_1
    return-void
.end method

.method public executePostRequestHook(Ljava/lang/String;ILorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)Lcom/exteragram/messenger/plugins/hooks/PluginsHooks$PostRequestResult;
    .locals 2

    .line 984
    new-instance v0, Lcom/exteragram/messenger/plugins/hooks/PluginsHooks$PostRequestResult;

    invoke-direct {v0, p3, p4}, Lcom/exteragram/messenger/plugins/hooks/PluginsHooks$PostRequestResult;-><init>(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    .line 982
    new-instance v1, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda8;

    invoke-direct {v1, p1, p2}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda8;-><init>(Ljava/lang/String;I)V

    invoke-direct {p0, p1, v0, v1}, Lcom/exteragram/messenger/plugins/PluginsController;->executeGenericHook(Ljava/lang/String;Ljava/lang/Object;Lcom/exteragram/messenger/plugins/PluginsController$EngineHookCaller;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/plugins/hooks/PluginsHooks$PostRequestResult;

    if-nez p0, :cond_0

    .line 987
    new-instance p0, Lcom/exteragram/messenger/plugins/hooks/PluginsHooks$PostRequestResult;

    invoke-direct {p0, p3, p4}, Lcom/exteragram/messenger/plugins/hooks/PluginsHooks$PostRequestResult;-><init>(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    :cond_0
    return-object p0
.end method

.method public executePreRequestHook(Ljava/lang/String;ILorg/telegram/tgnet/TLObject;)Lorg/telegram/tgnet/TLObject;
    .locals 1

    .line 971
    new-instance v0, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda22;

    invoke-direct {v0, p1, p2}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda22;-><init>(Ljava/lang/String;I)V

    invoke-direct {p0, p1, p3, v0}, Lcom/exteragram/messenger/plugins/PluginsController;->executeGenericHook(Ljava/lang/String;Ljava/lang/Object;Lcom/exteragram/messenger/plugins/PluginsController$EngineHookCaller;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/telegram/tgnet/TLObject;

    return-object p0
.end method

.method public executeSendMessageHook(ILorg/telegram/messenger/SendMessagesHelper$SendMessageParams;)Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;
    .locals 1

    .line 1014
    new-instance v0, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda16;

    invoke-direct {v0, p1}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda16;-><init>(I)V

    const-string/jumbo p1, "send_message_hook"

    invoke-direct {p0, p1, p2, v0}, Lcom/exteragram/messenger/plugins/PluginsController;->executeGenericHook(Ljava/lang/String;Ljava/lang/Object;Lcom/exteragram/messenger/plugins/PluginsController$EngineHookCaller;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;

    return-object p0
.end method

.method public executeUpdateHook(Ljava/lang/String;ILorg/telegram/tgnet/TLRPC$Update;)Lorg/telegram/tgnet/TLRPC$Update;
    .locals 1

    .line 995
    new-instance v0, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda13;

    invoke-direct {v0, p1, p2}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda13;-><init>(Ljava/lang/String;I)V

    invoke-direct {p0, p1, p3, v0}, Lcom/exteragram/messenger/plugins/PluginsController;->executeGenericHook(Ljava/lang/String;Ljava/lang/Object;Lcom/exteragram/messenger/plugins/PluginsController$EngineHookCaller;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/telegram/tgnet/TLRPC$Update;

    return-object p0
.end method

.method public executeUpdatesHook(Ljava/lang/String;ILorg/telegram/tgnet/TLRPC$Updates;)Lorg/telegram/tgnet/TLRPC$Updates;
    .locals 1

    .line 1005
    new-instance v0, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda25;

    invoke-direct {v0, p1, p2}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda25;-><init>(Ljava/lang/String;I)V

    invoke-direct {p0, p1, p3, v0}, Lcom/exteragram/messenger/plugins/PluginsController;->executeGenericHook(Ljava/lang/String;Ljava/lang/Object;Lcom/exteragram/messenger/plugins/PluginsController$EngineHookCaller;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lorg/telegram/tgnet/TLRPC$Updates;

    return-object p0
.end method

.method public final getMenuItemsForLocation(Ljava/lang/String;Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;)Ljava/util/List;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;",
            ")",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;",
            ">;"
        }
    .end annotation

    if-nez p2, :cond_0

    .line 740
    new-instance p2, Ljava/util/HashMap;

    invoke-direct {p2}, Ljava/util/HashMap;-><init>()V

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/plugins/PluginsController;->getMenuItemsForLocation(Ljava/lang/String;Ljava/util/Map;)Ljava/util/List;

    move-result-object p0

    return-object p0

    .line 742
    :cond_0
    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;->build()Ljava/util/Map;

    move-result-object p2

    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/plugins/PluginsController;->getMenuItemsForLocation(Ljava/lang/String;Ljava/util/Map;)Ljava/util/List;

    move-result-object p0

    return-object p0
.end method

.method public final getMenuItemsForLocation(Ljava/lang/String;Ljava/util/Map;)Ljava/util/List;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "+",
            "Ljava/lang/Object;",
            ">;)",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;",
            ">;"
        }
    .end annotation

    .line 750
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->isPluginEngineAvailable()Z

    move-result v0

    if-eqz v0, :cond_9

    if-eqz p1, :cond_9

    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto/16 :goto_5

    .line 754
    :cond_0
    new-instance v0, Ljava/util/LinkedHashSet;

    invoke-direct {v0}, Ljava/util/LinkedHashSet;-><init>()V

    .line 755
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->menuItemsByMenuType:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v1, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/concurrent/CopyOnWriteArrayList;

    if-eqz v1, :cond_2

    .line 756
    invoke-interface {v1}, Ljava/util/Collection;->isEmpty()Z

    move-result v2

    if-eqz v2, :cond_1

    goto :goto_0

    .line 757
    :cond_1
    invoke-virtual {v0, v1}, Ljava/util/AbstractCollection;->addAll(Ljava/util/Collection;)Z

    .line 760
    :cond_2
    :goto_0
    const-string/jumbo v1, "main_menu"

    invoke-static {v1, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_4

    .line 761
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->menuItemsByMenuType:Ljava/util/concurrent/ConcurrentHashMap;

    const-string v1, "drawer_menu"

    invoke-virtual {p1, v1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/util/concurrent/CopyOnWriteArrayList;

    if-eqz p1, :cond_4

    .line 762
    invoke-interface {p1}, Ljava/util/Collection;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_3

    goto :goto_1

    .line 763
    :cond_3
    invoke-virtual {v0, p1}, Ljava/util/AbstractCollection;->addAll(Ljava/util/Collection;)Z

    .line 767
    :cond_4
    :goto_1
    invoke-virtual {v0}, Ljava/util/AbstractCollection;->isEmpty()Z

    move-result p1

    if-eqz p1, :cond_5

    .line 768
    sget-object p0, Ljava/util/Collections;->EMPTY_LIST:Ljava/util/List;

    return-object p0

    .line 771
    :cond_5
    new-instance p1, Ljava/util/ArrayList;

    invoke-direct {p1}, Ljava/util/ArrayList;-><init>()V

    .line 772
    invoke-virtual {v0}, Ljava/util/AbstractCollection;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_6
    :goto_2
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_8

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;

    .line 773
    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getPluginId()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p0, v2}, Lcom/exteragram/messenger/plugins/PluginsController;->isPluginActive$TMessagesProj(Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_6

    .line 774
    iget-object v2, p0, Lcom/exteragram/messenger/plugins/PluginsController;->watchdog:Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getPluginId()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionStarted(Ljava/lang/String;)V

    .line 776
    :try_start_0
    invoke-virtual {v1, p2}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->checkCondition(Ljava/util/Map;)Z

    move-result v2

    if-eqz v2, :cond_7

    .line 777
    invoke-virtual {p1, v1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_3

    :catchall_0
    move-exception p1

    goto :goto_4

    .line 780
    :cond_7
    :goto_3
    iget-object v2, p0, Lcom/exteragram/messenger/plugins/PluginsController;->watchdog:Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getPluginId()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v2, v1}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionFinished(Ljava/lang/String;)V

    goto :goto_2

    :goto_4
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->watchdog:Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getPluginId()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p0, p2}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionFinished(Ljava/lang/String;)V

    throw p1

    :cond_8
    return-object p1

    .line 751
    :cond_9
    :goto_5
    sget-object p0, Ljava/util/Collections;->EMPTY_LIST:Ljava/util/List;

    return-object p0
.end method

.method public final getPluginEngine(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;
    .locals 2

    const/4 v0, 0x0

    if-eqz p1, :cond_5

    .line 117
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v1

    if-nez v1, :cond_0

    goto :goto_0

    .line 120
    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->plugins:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/plugins/Plugin;

    if-nez p0, :cond_1

    return-object v0

    .line 121
    :cond_1
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/Plugin;->getCachedEngine()Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    move-result-object p1

    if-eqz p1, :cond_2

    return-object p1

    .line 123
    :cond_2
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/Plugin;->getEngine()Ljava/lang/String;

    move-result-object p1

    if-nez p1, :cond_3

    return-object v0

    .line 124
    :cond_3
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getEngines()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    if-eqz p1, :cond_4

    .line 126
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/Plugin;->setCachedEngine(Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;)V

    :cond_4
    return-object p1

    :cond_5
    :goto_0
    return-object v0
.end method

.method public final getPluginPath(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    const/4 v0, 0x0

    if-eqz p1, :cond_1

    .line 356
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v1

    if-nez v1, :cond_0

    goto :goto_0

    .line 359
    :cond_0
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginEngine(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    move-result-object p0

    if-eqz p0, :cond_1

    invoke-interface {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->getPluginPath(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_1
    :goto_0
    return-object v0
.end method

.method public final getPluginSettingBoolean(Ljava/lang/String;Ljava/lang/String;Z)Z
    .locals 1

    if-eqz p1, :cond_2

    .line 492
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    if-eqz p2, :cond_2

    invoke-interface {p2}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_1

    goto :goto_0

    .line 495
    :cond_1
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginEngine(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    move-result-object p0

    if-eqz p0, :cond_2

    .line 497
    invoke-static {p3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    invoke-interface {p0, p1, p2, v0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->getPluginSetting(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 498
    instance-of p1, p0, Ljava/lang/Boolean;

    if-eqz p1, :cond_2

    .line 499
    check-cast p0, Ljava/lang/Boolean;

    invoke-virtual {p0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result p0

    return p0

    :cond_2
    :goto_0
    return p3
.end method

.method public final getPluginSettingInt(Ljava/lang/String;Ljava/lang/String;I)I
    .locals 1

    if-eqz p1, :cond_2

    .line 520
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    if-eqz p2, :cond_2

    invoke-interface {p2}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_1

    goto :goto_0

    .line 523
    :cond_1
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginEngine(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    move-result-object p0

    if-eqz p0, :cond_2

    .line 525
    invoke-static {p3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-interface {p0, p1, p2, v0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->getPluginSetting(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 526
    instance-of p1, p0, Ljava/lang/Number;

    if-eqz p1, :cond_2

    .line 527
    check-cast p0, Ljava/lang/Number;

    invoke-virtual {p0}, Ljava/lang/Number;->intValue()I

    move-result p0

    return p0

    :cond_2
    :goto_0
    return p3
.end method

.method public final getPluginSettingString(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    if-eqz p1, :cond_2

    .line 506
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    if-eqz p2, :cond_2

    invoke-interface {p2}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_1

    goto :goto_0

    .line 509
    :cond_1
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginEngine(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    move-result-object p0

    if-eqz p0, :cond_2

    .line 511
    invoke-interface {p0, p1, p2, p3}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->getPluginSetting(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    if-eqz p0, :cond_2

    .line 513
    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_2
    :goto_0
    return-object p3
.end method

.method public final getPluginSettingsList(Ljava/lang/String;)Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/models/SettingItem;",
            ">;"
        }
    .end annotation

    if-eqz p1, :cond_1

    .line 320
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 323
    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->settings:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/util/List;

    return-object p0

    :cond_1
    :goto_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public final getPluginSettingsPreferences(Ljava/lang/String;)Ljava/util/Map;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "*>;"
        }
    .end annotation

    .line 484
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginEngine(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    move-result-object p0

    if-eqz p0, :cond_0

    invoke-interface {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->getAllPluginSettings(Ljava/lang/String;)Ljava/util/Map;

    move-result-object p0

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method public final getPlugins()Ljava/util/concurrent/ConcurrentHashMap;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Lcom/exteragram/messenger/plugins/Plugin;",
            ">;"
        }
    .end annotation

    .line 70
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->plugins:Ljava/util/concurrent/ConcurrentHashMap;

    return-object p0
.end method

.method public final getPluginsDir()Ljava/io/File;
    .locals 0

    .line 99
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->pluginsDir:Ljava/io/File;

    return-object p0
.end method

.method public final getPreferences()Landroid/content/SharedPreferences;
    .locals 0

    .line 101
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->preferences:Landroid/content/SharedPreferences;

    return-object p0
.end method

.method public final getSettings()Ljava/util/concurrent/ConcurrentHashMap;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/models/SettingItem;",
            ">;>;"
        }
    .end annotation

    .line 72
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->settings:Ljava/util/concurrent/ConcurrentHashMap;

    return-object p0
.end method

.method public final getWatchdog()Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;
    .locals 0

    .line 107
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->watchdog:Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    return-object p0
.end method

.method public final hasPluginSettings(Ljava/lang/String;)Z
    .locals 1

    if-eqz p1, :cond_1

    .line 444
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->settings:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->containsKey(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_1

    const/4 p0, 0x1

    return p0

    :cond_1
    :goto_0
    const/4 p0, 0x0

    return p0
.end method

.method public final hasPluginSettingsPreferences(Ljava/lang/String;)Z
    .locals 0

    .line 488
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginSettingsPreferences(Ljava/lang/String;)Ljava/util/Map;

    move-result-object p0

    const/4 p1, 0x1

    if-eqz p0, :cond_1

    invoke-interface {p0}, Ljava/util/Map;->isEmpty()Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    goto :goto_1

    :cond_1
    :goto_0
    move p0, p1

    :goto_1
    xor-int/2addr p0, p1

    return p0
.end method

.method public final init()V
    .locals 2

    const/4 v0, 0x0

    const/4 v1, 0x0

    .line 132
    invoke-virtual {p0, v0, v1}, Lcom/exteragram/messenger/plugins/PluginsController;->init(ZLjava/lang/Runnable;)V

    return-void
.end method

.method public final init(Ljava/lang/Runnable;)V
    .locals 1

    const/4 v0, 0x0

    .line 136
    invoke-virtual {p0, v0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->init(ZLjava/lang/Runnable;)V

    return-void
.end method

.method public final init(Z)V
    .locals 1

    const/4 v0, 0x0

    .line 140
    invoke-virtual {p0, p1, v0}, Lcom/exteragram/messenger/plugins/PluginsController;->init(ZLjava/lang/Runnable;)V

    return-void
.end method

.method public final init(ZLjava/lang/Runnable;)V
    .locals 9

    .line 144
    const-string v0, "crashed_plugin_id"

    const-string/jumbo v1, "had_crash"

    const-string/jumbo v2, "plugin_enabled_"

    sget-object v3, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v3}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->isPluginEngineSupported()Z

    move-result v3

    const/4 v4, 0x0

    if-eqz v3, :cond_8

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsEngine()Z

    move-result v3

    if-nez v3, :cond_0

    goto/16 :goto_3

    .line 150
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/plugins/utils/NativeCrashHandler;->checkAndHandleNativeCrash()V

    .line 151
    iget-object v3, p0, Lcom/exteragram/messenger/plugins/PluginsController;->watchdog:Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    invoke-virtual {v3}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->start()V

    .line 152
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->ensurePreferences()V

    .line 155
    :try_start_0
    iget-object v3, p0, Lcom/exteragram/messenger/plugins/PluginsController;->preferences:Landroid/content/SharedPreferences;

    invoke-interface {v3, v1, v4}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v3

    .line 156
    iget-object v5, p0, Lcom/exteragram/messenger/plugins/PluginsController;->preferences:Landroid/content/SharedPreferences;

    const/4 v6, 0x0

    invoke-interface {v5, v0, v6}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    const/4 v6, 0x1

    if-eqz v5, :cond_1

    .line 158
    const-string/jumbo v7, "manual!"

    invoke-static {v5, v7}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v7

    if-nez v7, :cond_2

    :cond_1
    if-eqz p1, :cond_3

    :cond_2
    move v7, v6

    goto :goto_0

    :cond_3
    move v7, v4

    .line 160
    :goto_0
    iget-object v8, p0, Lcom/exteragram/messenger/plugins/PluginsController;->preferences:Landroid/content/SharedPreferences;

    invoke-interface {v8}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v8

    invoke-interface {v8, v1}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v1

    invoke-interface {v1, v0}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 162
    const-string/jumbo v0, "pluginsSafeMode"

    if-eqz v3, :cond_5

    if-eqz v5, :cond_4

    if-nez v7, :cond_4

    .line 164
    :try_start_1
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->preferences:Landroid/content/SharedPreferences;

    .line 45
    invoke-interface {p1}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p1

    .line 165
    invoke-virtual {v2, v5}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-interface {p1, v0, v4}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 50
    invoke-interface {p1}, Landroid/content/SharedPreferences$Editor;->apply()V

    goto :goto_1

    .line 168
    :cond_4
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object p1

    .line 170
    invoke-static {v6}, Lcom/exteragram/messenger/ExteraConfig;->setPluginsSafeMode(Z)V

    sget-object v1, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    .line 168
    invoke-interface {p1, v0, v6}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object p1

    .line 170
    invoke-interface {p1}, Landroid/content/SharedPreferences$Editor;->apply()V

    :goto_1
    if-nez v7, :cond_6

    .line 173
    new-instance p1, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda0;

    invoke-direct {p1}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda0;-><init>()V

    const-wide/16 v0, 0x320

    invoke-static {p1, v0, v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    goto :goto_2

    .line 181
    :cond_5
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getEditor()Landroid/content/SharedPreferences$Editor;

    move-result-object v1

    .line 183
    invoke-static {p1}, Lcom/exteragram/messenger/ExteraConfig;->setPluginsSafeMode(Z)V

    sget-object v2, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    .line 181
    invoke-interface {v1, v0, p1}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    move-result-object p1

    .line 183
    invoke-interface {p1}, Landroid/content/SharedPreferences$Editor;->apply()V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    .line 188
    :catch_0
    :cond_6
    :goto_2
    new-instance p1, Ljava/io/File;

    invoke-static {}, Lorg/telegram/messenger/ApplicationLoader;->getFilesDirFixed()Ljava/io/File;

    move-result-object v0

    const-string/jumbo v1, "plugins"

    invoke-direct {p1, v0, v1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->pluginsDir:Ljava/io/File;

    .line 189
    invoke-virtual {p1}, Ljava/io/File;->exists()Z

    move-result p1

    if-nez p1, :cond_7

    .line 190
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->pluginsDir:Ljava/io/File;

    invoke-static {p1}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object p1

    invoke-virtual {p1}, Lorg/simplifiles/files/SimpliDirectory;->create()Lorg/simplifiles/files/SimpliDirectory;

    .line 193
    :cond_7
    new-instance p1, Ljava/util/concurrent/atomic/AtomicInteger;

    invoke-direct {p1, v4}, Ljava/util/concurrent/atomic/AtomicInteger;-><init>(I)V

    .line 194
    new-instance v0, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda1;

    invoke-direct {v0, p1, p0, p2}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda1;-><init>(Ljava/util/concurrent/atomic/AtomicInteger;Lcom/exteragram/messenger/plugins/PluginsController;Ljava/lang/Runnable;)V

    .line 201
    sget-object p0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    new-instance p1, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda2;

    invoke-direct {p1, v0}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda2;-><init>(Ljava/lang/Runnable;)V

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->runOnPluginsQueue(Ljava/lang/Runnable;)V

    return-void

    .line 145
    :cond_8
    :goto_3
    iput-boolean v4, p0, Lcom/exteragram/messenger/plugins/PluginsController;->initialized:Z

    if-eqz p2, :cond_9

    .line 146
    invoke-interface {p2}, Ljava/lang/Runnable;->run()V

    :cond_9
    return-void
.end method

.method public final invalidatePluginSettings(Ljava/lang/String;)V
    .locals 2

    if-eqz p1, :cond_2

    .line 448
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_1

    .line 449
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->settings:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/List;

    if-eqz v0, :cond_2

    .line 451
    invoke-interface {v0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/plugins/models/SettingItem;

    .line 452
    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/models/SettingItem;->cleanup()V

    goto :goto_0

    .line 454
    :cond_1
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->notifyPluginSettingsUnregistered(Ljava/lang/String;)V

    :cond_2
    :goto_1
    return-void
.end method

.method public final isInitialized()Z
    .locals 0

    .line 257
    iget-boolean p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->initialized:Z

    return p0
.end method

.method public final isPluginActive$TMessagesProj(Lcom/exteragram/messenger/plugins/Plugin;)Z
    .locals 1

    if-eqz p1, :cond_0

    .line 265
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->plugins:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    if-ne p0, p1, :cond_0

    invoke-virtual {p1}, Lcom/exteragram/messenger/plugins/Plugin;->isEnabled()Z

    move-result p0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public final isPluginActive$TMessagesProj(Ljava/lang/String;)Z
    .locals 1

    if-eqz p1, :cond_1

    .line 261
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->plugins:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/exteragram/messenger/plugins/Plugin;

    if-eqz p0, :cond_1

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/Plugin;->isEnabled()Z

    move-result p0

    const/4 p1, 0x1

    if-ne p0, p1, :cond_1

    return p1

    :cond_1
    :goto_0
    const/4 p0, 0x0

    return p0
.end method

.method public final loadPluginSettings()V
    .locals 1

    const/4 v0, 0x0

    .line 402
    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/plugins/PluginsController;->loadPluginSettings(Ljava/lang/String;)V

    return-void
.end method

.method public final loadPluginSettings(Ljava/lang/String;)V
    .locals 3

    if-eqz p1, :cond_2

    .line 406
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 418
    :cond_0
    new-instance v0, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda12;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda12;-><init>(Lcom/exteragram/messenger/plugins/PluginsController;Ljava/lang/String;)V

    .line 436
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->isOnPluginsQueueThread()Z

    move-result p0

    if-eqz p0, :cond_1

    .line 437
    invoke-interface {v0}, Ljava/lang/Runnable;->run()V

    return-void

    .line 439
    :cond_1
    sget-object p0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->runOnPluginsQueue(Ljava/lang/Runnable;)V

    return-void

    .line 407
    :cond_2
    :goto_0
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->plugins:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p1}, Ljava/util/concurrent/ConcurrentHashMap;->keySet()Ljava/util/Set;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :cond_3
    :goto_1
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_5

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 408
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->plugins:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v1, v0}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/plugins/Plugin;

    .line 409
    invoke-virtual {p0, v1}, Lcom/exteragram/messenger/plugins/PluginsController;->isPluginActive$TMessagesProj(Lcom/exteragram/messenger/plugins/Plugin;)Z

    move-result v2

    if-eqz v2, :cond_4

    .line 410
    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/plugins/PluginsController;->loadPluginSettings(Ljava/lang/String;)V

    goto :goto_1

    :cond_4
    if-eqz v1, :cond_3

    .line 412
    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/plugins/PluginsController;->invalidatePluginSettings(Ljava/lang/String;)V

    goto :goto_1

    :cond_5
    return-void
.end method

.method public final notifyPluginsChanged()V
    .locals 2

    .line 788
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->updateNotificationRunnable:Ljava/lang/Runnable;

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    .line 789
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->updateNotificationRunnable:Ljava/lang/Runnable;

    const-wide/16 v0, 0x96

    invoke-static {p0, v0, v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    return-void
.end method

.method public final removeEventHook(Ljava/lang/String;Ljava/lang/String;)V
    .locals 3

    .line 614
    new-instance v0, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda17;

    invoke-direct {v0, p2}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda17;-><init>(Ljava/lang/String;)V

    .line 616
    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Removed event hook(s) matching name \'"

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p2, "\' for plugin "

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    .line 614
    invoke-direct {p0, p1, v0, p2}, Lcom/exteragram/messenger/plugins/PluginsController;->removeHook(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Ljava/lang/String;)V

    return-void
.end method

.method public final removeHooksByPluginId(Ljava/lang/String;)V
    .locals 3

    if-eqz p1, :cond_2

    .line 643
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_1

    .line 644
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->hooks:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Set;

    if-eqz v0, :cond_2

    .line 646
    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/exteragram/messenger/plugins/hooks/HookRecord;

    .line 647
    invoke-interface {v2}, Lcom/exteragram/messenger/plugins/hooks/HookRecord;->cleanup()V

    goto :goto_0

    .line 649
    :cond_1
    invoke-interface {v0}, Ljava/util/Set;->size()I

    move-result v0

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Removed all ("

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, ") hooks for plugin "

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    .line 650
    iget-object p1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->interestedPluginsCache:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {p1}, Ljava/util/concurrent/ConcurrentHashMap;->clear()V

    const/4 p1, 0x1

    .line 651
    iput-boolean p1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->hooksCacheDirty:Z

    :cond_2
    :goto_1
    return-void
.end method

.method public final removeMenuItem(Ljava/lang/String;Ljava/lang/String;)Z
    .locals 3

    const/4 v0, 0x0

    if-eqz p2, :cond_3

    .line 697
    invoke-interface {p2}, Ljava/lang/CharSequence;->length()I

    move-result v1

    if-nez v1, :cond_0

    goto :goto_0

    .line 701
    :cond_0
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->menuItemsById:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v1, p2}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;

    if-eqz v1, :cond_3

    .line 702
    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getMenuType()Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_3

    .line 703
    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getPluginId()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_1

    .line 704
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->menuItemsById:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-interface {p0, p2, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return v0

    .line 707
    :cond_1
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->menuItemsByMenuType:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getMenuType()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/concurrent/CopyOnWriteArrayList;

    if-eqz v0, :cond_2

    invoke-virtual {v0, v1}, Ljava/util/concurrent/CopyOnWriteArrayList;->remove(Ljava/lang/Object;)Z

    .line 708
    :cond_2
    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->markRemoved()V

    .line 709
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Removed menu item: "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p2, " for plugin "

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    .line 710
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->notifyMenuItemsUpdated()V

    const/4 p0, 0x1

    return p0

    :cond_3
    :goto_0
    return v0
.end method

.method public final removeMenuItemsByPluginId(Ljava/lang/String;)V
    .locals 4

    if-eqz p1, :cond_5

    .line 717
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_2

    .line 720
    :cond_0
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    .line 721
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->menuItemsById:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v1}, Ljava/util/concurrent/ConcurrentHashMap;->values()Ljava/util/Collection;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_1
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_2

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;

    .line 722
    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getPluginId()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 723
    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/hooks/MenuItemRecord;->getItemId()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 726
    :cond_2
    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_3

    return-void

    .line 729
    :cond_3
    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_1
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_4

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    .line 730
    invoke-virtual {p0, p1, v1}, Lcom/exteragram/messenger/plugins/PluginsController;->removeMenuItem(Ljava/lang/String;Ljava/lang/String;)Z

    goto :goto_1

    .line 732
    :cond_4
    new-instance p0, Ljava/lang/StringBuilder;

    const-string v0, "Removed all menu items for plugin: "

    invoke-direct {p0, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    :cond_5
    :goto_2
    return-void
.end method

.method public final removeXposedHook(Ljava/lang/String;Lde/robv/android/xposed/XC_MethodHook$Unhook;)V
    .locals 2

    .line 637
    new-instance v0, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda6;

    invoke-direct {v0, p2}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda6;-><init>(Lde/robv/android/xposed/XC_MethodHook$Unhook;)V

    .line 639
    new-instance p2, Ljava/lang/StringBuilder;

    const-string v1, "Removed Xposed hook for plugin "

    invoke-direct {p2, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    .line 637
    invoke-direct {p0, p1, v0, p2}, Lcom/exteragram/messenger/plugins/PluginsController;->removeHook(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Ljava/lang/String;)V

    return-void
.end method

.method public final restart()V
    .locals 1

    .line 244
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsSafeMode()Z

    move-result v0

    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/plugins/PluginsController;->restart(Z)V

    return-void
.end method

.method public final restart(Z)V
    .locals 1

    .line 248
    const-string v0, "Restarting plugins engine..."

    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    .line 249
    new-instance v0, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda5;

    invoke-direct {v0, p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda5;-><init>(Lcom/exteragram/messenger/plugins/PluginsController;Z)V

    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/plugins/PluginsController;->shutdown(Ljava/lang/Runnable;)V

    return-void
.end method

.method public final setPluginEnabled(Ljava/lang/String;ZLorg/telegram/messenger/Utilities$Callback;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Z",
            "Lorg/telegram/messenger/Utilities$Callback<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    .line 331
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    new-instance v1, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda10;

    invoke-direct {v1, p0, p1, p2, p3}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda10;-><init>(Lcom/exteragram/messenger/plugins/PluginsController;Ljava/lang/String;ZLorg/telegram/messenger/Utilities$Callback;)V

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->runOnPluginsQueue(Ljava/lang/Runnable;)V

    return-void
.end method

.method public final setPluginSetting(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
    .locals 1

    const/4 v0, 0x0

    .line 534
    invoke-virtual {p0, p1, p2, p3, v0}, Lcom/exteragram/messenger/plugins/PluginsController;->setPluginSettingAndTriggerOnChange(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Lcom/chaquo/python/PyObject;)V

    return-void
.end method

.method public final setPluginSettingAndTriggerOnChange(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Lcom/chaquo/python/PyObject;)V
    .locals 1

    if-eqz p1, :cond_4

    .line 543
    invoke-interface {p1}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_1

    :cond_0
    if-eqz p2, :cond_4

    invoke-interface {p2}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_1

    goto :goto_1

    .line 546
    :cond_1
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->isPluginActive$TMessagesProj(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_2

    goto :goto_1

    .line 549
    :cond_2
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginEngine(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;

    move-result-object v0

    if-eqz v0, :cond_4

    .line 551
    invoke-interface {v0, p1, p2, p3}, Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;->setPluginSetting(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V

    if-eqz p4, :cond_3

    .line 553
    :try_start_0
    filled-new-array {p3}, [Ljava/lang/Object;

    move-result-object p3

    invoke-virtual {p4, p3}, Lcom/chaquo/python/PyObject;->call([Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p3

    .line 555
    new-instance p4, Ljava/lang/StringBuilder;

    const-string v0, "Error executing on_change callback for "

    invoke-direct {p4, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, "/"

    invoke-virtual {p4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p4, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-static {p2, p3}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 557
    :cond_3
    :goto_0
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->loadPluginSettings(Ljava/lang/String;)V

    :cond_4
    :goto_1
    return-void
.end method

.method public final setPluginsDir(Ljava/io/File;)V
    .locals 0

    .line 99
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->pluginsDir:Ljava/io/File;

    return-void
.end method

.method public final setPreferences(Landroid/content/SharedPreferences;)V
    .locals 0

    .line 101
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/PluginsController;->preferences:Landroid/content/SharedPreferences;

    return-void
.end method

.method public final showInstallDialog(Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/lang/String;Z)V
    .locals 1

    if-eqz p2, :cond_1

    .line 370
    invoke-interface {p2}, Ljava/lang/CharSequence;->length()I

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 373
    :cond_0
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;

    invoke-direct {v0, p2, p3}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;-><init>(Ljava/lang/String;Z)V

    invoke-direct {p0, p1, v0}, Lcom/exteragram/messenger/plugins/PluginsController;->showInstallDialog(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;)V

    :cond_1
    :goto_0
    return-void
.end method

.method public final showInstallDialog(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/messenger/MessageObject;)V
    .locals 1

    if-nez p2, :cond_0

    return-void

    .line 366
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->Companion:Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams$Companion;

    invoke-virtual {v0, p2}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams$Companion;->of(Lorg/telegram/messenger/MessageObject;)Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;

    move-result-object p2

    invoke-direct {p0, p1, p2}, Lcom/exteragram/messenger/plugins/PluginsController;->showInstallDialog(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;)V

    return-void
.end method

.method public final shutdown(Ljava/lang/Runnable;)V
    .locals 2

    .line 220
    iget-boolean v0, p0, Lcom/exteragram/messenger/plugins/PluginsController;->initialized:Z

    if-nez v0, :cond_1

    if-eqz p1, :cond_0

    .line 221
    invoke-interface {p1}, Ljava/lang/Runnable;->run()V

    :cond_0
    return-void

    .line 224
    :cond_1
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    new-instance v1, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda23;

    invoke-direct {v1, p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController$$ExternalSyntheticLambda23;-><init>(Lcom/exteragram/messenger/plugins/PluginsController;Ljava/lang/Runnable;)V

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->runOnPluginsQueue(Ljava/lang/Runnable;)V

    return-void
.end method
