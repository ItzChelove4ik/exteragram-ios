.class public final Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;
.super Lde/robv/android/xposed/XC_MethodHook;
.source "SourceFile"

# interfaces
.implements Ljava/lang/AutoCloseable;


# annotations
.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0010\u000b\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0010\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u000c\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003B\u001b\u0008\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0008\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\u0008\u0008\u0010\tB#\u0008\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0008\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0004\u0008\u0008\u0010\u000cB+\u0008\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0008\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\u0008\u0008\u0010\u0010B3\u0008\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0008\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0004\u0008\u0008\u0010\u0011J\u001e\u0010\u001b\u001a\u00020\u001c2\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\u0008\u0012\u0004\u0012\u00020\u0016`\u0017J\u001e\u0010\u001d\u001a\u00020\u001c2\u0016\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\u0008\u0012\u0004\u0012\u00020\u0016`\u0017J\u0016\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\u0008\u0012\u0004\u0012\u00020\u0016`\u0017J\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\u0008\u0012\u0004\u0012\u00020\u0016`\u0017J\u0010\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"H\u0016J\u0008\u0010$\u001a\u00020\u001cH\u0016J0\u0010%\u001a\u00020\u000e2\u0016\u0010&\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\u0008\u0012\u0004\u0012\u00020\u0016`\u00172\u0006\u0010!\u001a\u00020\"2\u0006\u0010\'\u001a\u00020\u000eH\u0002J\"\u0010(\u001a\u0004\u0018\u00010\u00072\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u000eH\u0002J\u0018\u0010,\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020\u00052\u0006\u0010.\u001a\u00020/H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\u0008\u0012\u0004\u0012\u00020\u0016`\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\u0008\u0012\u0004\u0012\u00020\u0016`\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00060"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;",
        "Lde/robv/android/xposed/XC_MethodHook;",
        "Ljava/lang/AutoCloseable;",
        "Lkotlin/AutoCloseable;",
        "pluginId",
        "",
        "pythonCallback",
        "Lcom/chaquo/python/PyObject;",
        "<init>",
        "(Ljava/lang/String;Lcom/chaquo/python/PyObject;)V",
        "priority",
        "",
        "(Ljava/lang/String;Lcom/chaquo/python/PyObject;I)V",
        "hasBeforeHook",
        "",
        "hasAfterHook",
        "(Ljava/lang/String;Lcom/chaquo/python/PyObject;ZZ)V",
        "(Ljava/lang/String;Lcom/chaquo/python/PyObject;IZZ)V",
        "beforeHook",
        "afterHook",
        "beforeHookedFilters",
        "Ljava/util/ArrayList;",
        "Lcom/exteragram/messenger/plugins/hooks/HookFilter;",
        "Lkotlin/collections/ArrayList;",
        "afterHookedFilters",
        "disabled",
        "closed",
        "setBeforeHookedFilters",
        "",
        "setAfterHookedFilters",
        "getBeforeHookedFilters",
        "getAfterHookedFilters",
        "beforeHookedMethod",
        "param",
        "Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;",
        "afterHookedMethod",
        "close",
        "executeFilters",
        "filters",
        "isBefore",
        "getCallbackIfPresent",
        "callbackObject",
        "name",
        "enabled",
        "handleHookError",
        "hookMethodName",
        "t",
        "",
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
        "SMAP\nPyMethodHook.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PyMethodHook.kt\ncom/exteragram/messenger/plugins/xposed/PyMethodHook\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,185:1\n1#2:186\n*E\n"
    }
.end annotation


# instance fields
.field private final afterHook:Lcom/chaquo/python/PyObject;

.field private afterHookedFilters:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/plugins/hooks/HookFilter;",
            ">;"
        }
    .end annotation
.end field

.field private final beforeHook:Lcom/chaquo/python/PyObject;

.field private beforeHookedFilters:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/plugins/hooks/HookFilter;",
            ">;"
        }
    .end annotation
.end field

.field private volatile closed:Z

.field private volatile disabled:Z

.field private final pluginId:Ljava/lang/String;

.field private final pythonCallback:Lcom/chaquo/python/PyObject;


# direct methods
.method public constructor <init>(Ljava/lang/String;Lcom/chaquo/python/PyObject;)V
    .locals 1

    const/4 v0, 0x1

    .line 34
    invoke-direct {p0, p1, p2, v0, v0}, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;-><init>(Ljava/lang/String;Lcom/chaquo/python/PyObject;ZZ)V

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;Lcom/chaquo/python/PyObject;I)V
    .locals 6

    const/4 v4, 0x1

    const/4 v5, 0x1

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move v3, p3

    .line 41
    invoke-direct/range {v0 .. v5}, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;-><init>(Ljava/lang/String;Lcom/chaquo/python/PyObject;IZZ)V

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;Lcom/chaquo/python/PyObject;IZZ)V
    .locals 0

    .line 76
    invoke-direct {p0, p3}, Lde/robv/android/xposed/XC_MethodHook;-><init>(I)V

    .line 27
    new-instance p3, Ljava/util/ArrayList;

    invoke-direct {p3}, Ljava/util/ArrayList;-><init>()V

    iput-object p3, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->beforeHookedFilters:Ljava/util/ArrayList;

    .line 28
    new-instance p3, Ljava/util/ArrayList;

    invoke-direct {p3}, Ljava/util/ArrayList;-><init>()V

    iput-object p3, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->afterHookedFilters:Ljava/util/ArrayList;

    if-eqz p2, :cond_0

    .line 78
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->pluginId:Ljava/lang/String;

    .line 79
    iput-object p2, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->pythonCallback:Lcom/chaquo/python/PyObject;

    .line 82
    const-string p1, "before_hooked_method"

    .line 80
    invoke-direct {p0, p2, p1, p4}, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->getCallbackIfPresent(Lcom/chaquo/python/PyObject;Ljava/lang/String;Z)Lcom/chaquo/python/PyObject;

    move-result-object p1

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->beforeHook:Lcom/chaquo/python/PyObject;

    .line 87
    const-string p1, "after_hooked_method"

    .line 85
    invoke-direct {p0, p2, p1, p5}, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->getCallbackIfPresent(Lcom/chaquo/python/PyObject;Ljava/lang/String;Z)Lcom/chaquo/python/PyObject;

    move-result-object p1

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->afterHook:Lcom/chaquo/python/PyObject;

    return-void

    .line 77
    :cond_0
    const-string p0, "Python callback object cannot be null"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0
.end method

.method public constructor <init>(Ljava/lang/String;Lcom/chaquo/python/PyObject;ZZ)V
    .locals 1

    .line 54
    invoke-direct {p0}, Lde/robv/android/xposed/XC_MethodHook;-><init>()V

    .line 27
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->beforeHookedFilters:Ljava/util/ArrayList;

    .line 28
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->afterHookedFilters:Ljava/util/ArrayList;

    if-eqz p2, :cond_0

    .line 56
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->pluginId:Ljava/lang/String;

    .line 57
    iput-object p2, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->pythonCallback:Lcom/chaquo/python/PyObject;

    .line 60
    const-string p1, "before_hooked_method"

    .line 58
    invoke-direct {p0, p2, p1, p3}, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->getCallbackIfPresent(Lcom/chaquo/python/PyObject;Ljava/lang/String;Z)Lcom/chaquo/python/PyObject;

    move-result-object p1

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->beforeHook:Lcom/chaquo/python/PyObject;

    .line 65
    const-string p1, "after_hooked_method"

    .line 63
    invoke-direct {p0, p2, p1, p4}, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->getCallbackIfPresent(Lcom/chaquo/python/PyObject;Ljava/lang/String;Z)Lcom/chaquo/python/PyObject;

    move-result-object p1

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->afterHook:Lcom/chaquo/python/PyObject;

    return-void

    .line 55
    :cond_0
    const-string p0, "Python callback object cannot be null"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    throw p0
.end method

.method private final executeFilters(Ljava/util/ArrayList;Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;Z)Z
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/plugins/hooks/HookFilter;",
            ">;",
            "Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;",
            "Z)Z"
        }
    .end annotation

    .line 157
    invoke-virtual {p1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_1

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/exteragram/messenger/plugins/hooks/HookFilter;

    .line 158
    invoke-virtual {p1, p2, p3}, Lcom/exteragram/messenger/plugins/hooks/HookFilter;->execute(Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;Z)Z

    move-result p1

    if-nez p1, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_1
    const/4 p0, 0x1

    return p0
.end method

.method private final getCallbackIfPresent(Lcom/chaquo/python/PyObject;Ljava/lang/String;Z)Lcom/chaquo/python/PyObject;
    .locals 0

    if-eqz p3, :cond_0

    .line 170
    invoke-virtual {p1, p2}, Lcom/chaquo/python/PyObject;->containsKey(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    .line 171
    invoke-virtual {p1, p2}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/chaquo/python/PyObject;

    return-object p0

    :cond_0
    const/4 p0, 0x0

    return-object p0
.end method

.method private final handleHookError(Ljava/lang/String;Ljava/lang/Throwable;)V
    .locals 5

    .line 178
    instance-of v0, p2, Lcom/chaquo/python/PyException;

    if-eqz v0, :cond_0

    invoke-virtual {p2}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {p2}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x2

    const/4 v2, 0x0

    const-string v3, "closed"

    const/4 v4, 0x0

    invoke-static {v0, v3, v4, v1, v2}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 p1, 0x1

    .line 179
    iput-boolean p1, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->disabled:Z

    .line 180
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->pluginId:Ljava/lang/String;

    new-instance p1, Ljava/lang/StringBuilder;

    const-string p2, "Attempted to call a closed PyObject callback in "

    invoke-direct {p1, p2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;)V

    return-void

    .line 183
    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->pluginId:Ljava/lang/String;

    invoke-virtual {p2}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Plugin \'"

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "\' crashed in "

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ": "

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, p2}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method


# virtual methods
.method public afterHookedMethod(Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;)V
    .locals 3

    .line 124
    iget-boolean v0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->disabled:Z

    if-eqz v0, :cond_0

    goto :goto_0

    .line 128
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->afterHook:Lcom/chaquo/python/PyObject;

    if-nez v0, :cond_1

    goto :goto_0

    .line 129
    :cond_1
    sget-object v1, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v1

    iget-object v2, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->pluginId:Ljava/lang/String;

    invoke-virtual {v1, v2}, Lcom/exteragram/messenger/plugins/PluginsController;->isPluginActive$TMessagesProj(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_2

    goto :goto_0

    .line 133
    :cond_2
    :try_start_0
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->afterHookedFilters:Ljava/util/ArrayList;

    const/4 v2, 0x0

    invoke-direct {p0, v1, p1, v2}, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->executeFilters(Ljava/util/ArrayList;Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;Z)Z

    move-result v1

    if-nez v1, :cond_3

    :goto_0
    return-void

    .line 136
    :cond_3
    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p1

    invoke-virtual {v0, p1}, Lcom/chaquo/python/PyObject;->call([Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-void

    :catchall_0
    move-exception p1

    .line 138
    const-string v0, "afterHookedMethod"

    invoke-direct {p0, v0, p1}, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->handleHookError(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method public beforeHookedMethod(Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;)V
    .locals 3

    .line 105
    iget-boolean v0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->disabled:Z

    if-eqz v0, :cond_0

    goto :goto_0

    .line 109
    :cond_0
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->beforeHook:Lcom/chaquo/python/PyObject;

    if-nez v0, :cond_1

    goto :goto_0

    .line 110
    :cond_1
    sget-object v1, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v1

    iget-object v2, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->pluginId:Ljava/lang/String;

    invoke-virtual {v1, v2}, Lcom/exteragram/messenger/plugins/PluginsController;->isPluginActive$TMessagesProj(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_2

    goto :goto_0

    .line 114
    :cond_2
    :try_start_0
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->beforeHookedFilters:Ljava/util/ArrayList;

    const/4 v2, 0x1

    invoke-direct {p0, v1, p1, v2}, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->executeFilters(Ljava/util/ArrayList;Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;Z)Z

    move-result v1

    if-nez v1, :cond_3

    :goto_0
    return-void

    .line 117
    :cond_3
    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p1

    invoke-virtual {v0, p1}, Lcom/chaquo/python/PyObject;->call([Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-void

    :catchall_0
    move-exception p1

    .line 119
    const-string v0, "beforeHookedMethod"

    invoke-direct {p0, v0, p1}, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->handleHookError(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method public close()V
    .locals 1

    .line 143
    iget-boolean v0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->closed:Z

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x1

    .line 146
    iput-boolean v0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->closed:Z

    .line 147
    iput-boolean v0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->disabled:Z

    .line 148
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->beforeHook:Lcom/chaquo/python/PyObject;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/chaquo/python/PyObject;->close()V

    .line 149
    :cond_1
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->afterHook:Lcom/chaquo/python/PyObject;

    if-eqz p0, :cond_2

    invoke-virtual {p0}, Lcom/chaquo/python/PyObject;->close()V

    :cond_2
    :goto_0
    return-void
.end method

.method public final getAfterHookedFilters()Ljava/util/ArrayList;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/plugins/hooks/HookFilter;",
            ">;"
        }
    .end annotation

    .line 102
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->afterHookedFilters:Ljava/util/ArrayList;

    return-object p0
.end method

.method public final getBeforeHookedFilters()Ljava/util/ArrayList;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/plugins/hooks/HookFilter;",
            ">;"
        }
    .end annotation

    .line 100
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->beforeHookedFilters:Ljava/util/ArrayList;

    return-object p0
.end method

.method public final setAfterHookedFilters(Ljava/util/ArrayList;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/plugins/hooks/HookFilter;",
            ">;)V"
        }
    .end annotation

    .line 97
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->afterHookedFilters:Ljava/util/ArrayList;

    return-void
.end method

.method public final setBeforeHookedFilters(Ljava/util/ArrayList;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Lcom/exteragram/messenger/plugins/hooks/HookFilter;",
            ">;)V"
        }
    .end annotation

    .line 93
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodHook;->beforeHookedFilters:Ljava/util/ArrayList;

    return-void
.end method
