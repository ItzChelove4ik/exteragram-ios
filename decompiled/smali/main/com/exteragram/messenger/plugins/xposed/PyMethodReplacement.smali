.class public final Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;
.super Lde/robv/android/xposed/XC_MethodReplacement;
.source "SourceFile"

# interfaces
.implements Ljava/lang/AutoCloseable;


# annotations
.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u0008\n\u0002\u0008\u0003\n\u0002\u0010\u000b\n\u0002\u0008\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003B\u001b\u0008\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0008\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\u0008\u0008\u0010\tB#\u0008\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0008\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0004\u0008\u0008\u0010\u000cJ\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0008\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;",
        "Lde/robv/android/xposed/XC_MethodReplacement;",
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
        "replaceHook",
        "disabled",
        "",
        "closed",
        "replaceHookedMethod",
        "",
        "param",
        "Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;",
        "close",
        "",
        "handleHookError",
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
        "SMAP\nPyMethodReplacement.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PyMethodReplacement.kt\ncom/exteragram/messenger/plugins/xposed/PyMethodReplacement\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,95:1\n1#2:96\n*E\n"
    }
.end annotation


# instance fields
.field private volatile closed:Z

.field private volatile disabled:Z

.field private final pluginId:Ljava/lang/String;

.field private final pythonCallback:Lcom/chaquo/python/PyObject;

.field private final replaceHook:Lcom/chaquo/python/PyObject;


# direct methods
.method public constructor <init>(Ljava/lang/String;Lcom/chaquo/python/PyObject;)V
    .locals 3

    .line 33
    invoke-direct {p0}, Lde/robv/android/xposed/XC_MethodReplacement;-><init>()V

    const/4 v0, 0x0

    if-eqz p2, :cond_2

    .line 35
    const-string/jumbo v1, "replace_hooked_method"

    invoke-virtual {p2, v1}, Lcom/chaquo/python/PyObject;->containsKey(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 38
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;->pluginId:Ljava/lang/String;

    .line 39
    iput-object p2, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;->pythonCallback:Lcom/chaquo/python/PyObject;

    .line 40
    invoke-virtual {p2, v1}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    if-eqz p1, :cond_0

    check-cast p1, Lcom/chaquo/python/PyObject;

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;->replaceHook:Lcom/chaquo/python/PyObject;

    return-void

    :cond_0
    const-string p0, "Required value was null."

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw v0

    .line 36
    :cond_1
    const-string p0, "Python callback object must contain a method named \'replaceHookedMethod\'"

    .line 35
    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw v0

    .line 34
    :cond_2
    const-string p0, "Python callback object cannot be null"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw v0
.end method

.method public constructor <init>(Ljava/lang/String;Lcom/chaquo/python/PyObject;I)V
    .locals 2

    .line 43
    invoke-direct {p0, p3}, Lde/robv/android/xposed/XC_MethodReplacement;-><init>(I)V

    const/4 p3, 0x0

    if-eqz p2, :cond_2

    .line 45
    const-string/jumbo v0, "replace_hooked_method"

    invoke-virtual {p2, v0}, Lcom/chaquo/python/PyObject;->containsKey(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 48
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;->pluginId:Ljava/lang/String;

    .line 49
    iput-object p2, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;->pythonCallback:Lcom/chaquo/python/PyObject;

    .line 50
    invoke-virtual {p2, v0}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    if-eqz p1, :cond_0

    check-cast p1, Lcom/chaquo/python/PyObject;

    iput-object p1, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;->replaceHook:Lcom/chaquo/python/PyObject;

    return-void

    :cond_0
    const-string p0, "Required value was null."

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw p3

    .line 46
    :cond_1
    const-string p0, "Python callback object must contain a method named \'replaceHookedMethod\'"

    .line 45
    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw p3

    .line 44
    :cond_2
    const-string p0, "Python callback object cannot be null"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    throw p3
.end method

.method private final handleHookError(Ljava/lang/Throwable;)V
    .locals 5

    .line 88
    instance-of v0, p1, Lcom/chaquo/python/PyException;

    if-eqz v0, :cond_0

    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    const/4 v1, 0x2

    const/4 v2, 0x0

    const-string v3, "closed"

    const/4 v4, 0x0

    invoke-static {v0, v3, v4, v1, v2}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 p1, 0x1

    .line 89
    iput-boolean p1, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;->disabled:Z

    .line 90
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;->pluginId:Ljava/lang/String;

    new-instance p1, Ljava/lang/StringBuilder;

    const-string v0, "Attempted to call a closed PyObject callback in "

    invoke-direct {p1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;)V

    return-void

    .line 93
    :cond_0
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;->pluginId:Ljava/lang/String;

    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Plugin \'"

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "\' crashed in replaceHookedMethod: "

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method


# virtual methods
.method public close()V
    .locals 1

    .line 79
    iget-boolean v0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;->closed:Z

    if-eqz v0, :cond_0

    return-void

    :cond_0
    const/4 v0, 0x1

    .line 82
    iput-boolean v0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;->closed:Z

    .line 83
    iput-boolean v0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;->disabled:Z

    .line 84
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;->replaceHook:Lcom/chaquo/python/PyObject;

    invoke-virtual {p0}, Lcom/chaquo/python/PyObject;->close()V

    return-void
.end method

.method public replaceHookedMethod(Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;)Ljava/lang/Object;
    .locals 2

    .line 54
    iget-boolean v0, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;->disabled:Z

    if-nez v0, :cond_5

    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    iget-object v1, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;->pluginId:Ljava/lang/String;

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/plugins/PluginsController;->isPluginActive$TMessagesProj(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_2

    :cond_0
    const/4 v0, 0x0

    .line 65
    :try_start_0
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;->replaceHook:Lcom/chaquo/python/PyObject;

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p1

    invoke-virtual {v1, p1}, Lcom/chaquo/python/PyObject;->call([Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    if-eqz p1, :cond_1

    .line 66
    :try_start_1
    const-class v1, Ljava/lang/Object;

    invoke-virtual {p1, v1}, Lcom/chaquo/python/PyObject;->toJava(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception v1

    goto :goto_1

    .line 72
    :cond_1
    :goto_0
    invoke-static {v0}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->isSharedValue(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_2

    .line 73
    invoke-static {p1}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->closeSafely(Lcom/chaquo/python/PyObject;)V

    :cond_2
    return-object v0

    :catchall_1
    move-exception v1

    move-object p1, v0

    .line 69
    :goto_1
    :try_start_2
    invoke-direct {p0, v1}, Lcom/exteragram/messenger/plugins/xposed/PyMethodReplacement;->handleHookError(Ljava/lang/Throwable;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_2

    .line 72
    invoke-static {v0}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->isSharedValue(Ljava/lang/Object;)Z

    move-result p0

    if-nez p0, :cond_3

    .line 73
    invoke-static {p1}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->closeSafely(Lcom/chaquo/python/PyObject;)V

    :cond_3
    return-object v0

    :catchall_2
    move-exception p0

    .line 72
    invoke-static {v0}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->isSharedValue(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_4

    .line 73
    invoke-static {p1}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->closeSafely(Lcom/chaquo/python/PyObject;)V

    :cond_4
    throw p0

    .line 56
    :cond_5
    :goto_2
    :try_start_3
    iget-object p0, p1, Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;->method:Ljava/lang/reflect/Member;

    iget-object v0, p1, Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;->thisObject:Ljava/lang/Object;

    iget-object p1, p1, Lde/robv/android/xposed/XC_MethodHook$MethodHookParam;->args:[Ljava/lang/Object;

    invoke-static {p0, v0, p1}, Lde/robv/android/xposed/XposedBridge;->invokeOriginalMethod(Ljava/lang/reflect/Member;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_3
    .catch Ljava/lang/reflect/InvocationTargetException; {:try_start_3 .. :try_end_3} :catch_0

    return-object p0

    :catch_0
    move-exception p0

    .line 58
    invoke-virtual {p0}, Ljava/lang/reflect/InvocationTargetException;->getTargetException()Ljava/lang/Throwable;

    move-result-object p1

    if-nez p1, :cond_6

    goto :goto_3

    :cond_6
    move-object p0, p1

    :goto_3
    throw p0
.end method
