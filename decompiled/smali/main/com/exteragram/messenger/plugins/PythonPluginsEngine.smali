.class public final Lcom/exteragram/messenger/plugins/PythonPluginsEngine;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;,
        Lcom/exteragram/messenger/plugins/PythonPluginsEngine$PyMethodCaller;,
        Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;
    }
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u00f4\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\n\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u000c\n\u0002\u0018\u0002\n\u0002\u0008\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0010\"\n\u0002\u0008\u000e\n\u0002\u0018\u0002\n\u0002\u0008\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0010$\n\u0002\u0008\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0008\u0018\u0000 \u009b\u00012\u00020\u0001:\u0006\u009b\u0001\u009c\u0001\u009d\u0001B\u0007\u00a2\u0006\u0004\u0008\u0002\u0010\u0003J\u0008\u0010\u001b\u001a\u00020\u001cH\u0002J\n\u0010\u001d\u001a\u0004\u0018\u00010\u0011H\u0002J\u0008\u0010\u001e\u001a\u00020\u001fH\u0002J\u001c\u0010 \u001a\u00020\u000f2\u0008\u0010!\u001a\u0004\u0018\u00010\"2\u0008\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0008\u0010%\u001a\u00020\u000fH\u0016J\u0008\u0010&\u001a\u00020\u0007H\u0002J\u0018\u0010\'\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020\"2\u0006\u0010)\u001a\u00020\u000fH\u0002J\u0008\u0010*\u001a\u00020\u000fH\u0002J\u0008\u0010+\u001a\u00020\u001fH\u0002J \u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\u00072\u0006\u0010!\u001a\u00020\"2\u0006\u0010.\u001a\u00020\u0006H\u0002J\u0010\u0010/\u001a\u00020\u001f2\u0006\u00100\u001a\u000201H\u0016J\u0008\u00102\u001a\u00020\u001fH\u0016J\u0008\u00103\u001a\u00020\u001fH\u0002J\u0008\u00104\u001a\u00020\u001fH\u0002J\u0010\u00105\u001a\u00020\u001f2\u0006\u00100\u001a\u000201H\u0016J\u0010\u00106\u001a\u00020\u001f2\u0008\u00100\u001a\u0004\u0018\u000101J\u0016\u00107\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u0006J \u00107\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00062\u0008\u0010:\u001a\u0004\u0018\u00010;J*\u00107\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00062\u0008\u0010:\u001a\u0004\u0018\u00010;2\u0008\u0010<\u001a\u0004\u0018\u00010=J\"\u0010>\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u0006\u0010?\u001a\u00020;2\u0008\u0010<\u001a\u0004\u0018\u00010=H\u0002J&\u0010@\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u0006\u0010A\u001a\u00020\u00062\u000c\u0010B\u001a\u0008\u0012\u0004\u0012\u00020\u00060CH\u0002J\u0010\u0010D\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u0006H\u0002J\u0008\u0010E\u001a\u00020\u001fH\u0002J\u0018\u0010F\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\u00072\u0006\u0010G\u001a\u00020\u0006H\u0002J\u0008\u0010H\u001a\u00020\u001fH\u0002J\u0018\u0010I\u001a\u00020\u001f2\u0006\u0010J\u001a\u00020\u00072\u0006\u0010G\u001a\u00020\u0006H\u0002J*\u0010K\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00062\u0006\u0010?\u001a\u00020;2\u0008\u0010<\u001a\u0004\u0018\u00010=H\u0002J\u000e\u0010L\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u0006J\u001a\u0010M\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u0008\u0010N\u001a\u0004\u0018\u00010\"H\u0002J\"\u0010O\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\u00072\u0006\u00108\u001a\u00020\u00062\u0008\u0010N\u001a\u0004\u0018\u00010\"H\u0002J(\u0010P\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u0006\u0010Q\u001a\u00020\u000f2\u000e\u00100\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010RH\u0016J \u0010S\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u000e\u00100\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010RH\u0016J\u0010\u0010T\u001a\u00020\u00062\u0006\u0010U\u001a\u00020\u0006H\u0016J\u0008\u0010V\u001a\u00020\u000fH\u0016J\u0010\u0010W\u001a\u00020\u001f2\u0006\u0010U\u001a\u00020\u0006H\u0016J\u0010\u0010X\u001a\u00020\u001f2\u0006\u0010U\u001a\u00020\u0006H\u0016J(\u0010Y\u001a\u00020\u001f2\u0006\u00109\u001a\u00020\u00062\u0008\u0010?\u001a\u0004\u0018\u00010;2\u000e\u00100\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010RJ4\u0010Y\u001a\u00020\u001f2\u0006\u00109\u001a\u00020\u00062\u0008\u0010?\u001a\u0004\u0018\u00010;2\u000e\u00100\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010R2\u0008\u0010<\u001a\u0004\u0018\u00010=H\u0016J\u0012\u0010Z\u001a\u0004\u0018\u00010\u00062\u0006\u00108\u001a\u00020\u0006H\u0002J\u000e\u0010[\u001a\u00020\\2\u0006\u00109\u001a\u00020\u0006J\u001a\u0010]\u001a\u0008\u0012\u0004\u0012\u00020_0^2\u000c\u0010`\u001a\u0008\u0012\u0004\u0012\u00020\u00070^J\u0018\u0010a\u001a\n\u0012\u0004\u0012\u00020_\u0018\u00010^2\u0006\u0010U\u001a\u00020\u0006H\u0016J\u0010\u0010b\u001a\u00020\u001f2\u0006\u0010c\u001a\u00020\u0006H\u0016J_\u0010d\u001a\u0008\u0012\u0004\u0012\u0002Hf0e\"\u0004\u0008\u0000\u0010f2\u0008\u0010g\u001a\u0004\u0018\u00010\u00072\u0008\u0010h\u001a\u0004\u0018\u0001Hf2\u000c\u0010i\u001a\u0008\u0012\u0004\u0012\u0002Hf0j2\u0006\u0010k\u001a\u00020\u00062\u000c\u0010l\u001a\u0008\u0012\u0004\u0012\u0002Hf0m2\u000c\u0010n\u001a\u0008\u0012\u0004\u0012\u00020o0RH\u0002\u00a2\u0006\u0002\u0010pJ]\u0010d\u001a\u0008\u0012\u0004\u0012\u0002Hf0e\"\u0004\u0008\u0000\u0010f2\u0006\u00108\u001a\u00020\u00062\u0008\u0010h\u001a\u0004\u0018\u0001Hf2\u000c\u0010i\u001a\u0008\u0012\u0004\u0012\u0002Hf0j2\u0006\u0010k\u001a\u00020\u00062\u000c\u0010l\u001a\u0008\u0012\u0004\u0012\u0002Hf0m2\u000c\u0010n\u001a\u0008\u0012\u0004\u0012\u00020o0RH\u0002\u00a2\u0006\u0002\u0010qJ0\u0010r\u001a\u0008\u0012\u0004\u0012\u00020s0e2\u0006\u0010t\u001a\u00020\u00062\u0006\u0010u\u001a\u00020v2\u0008\u0010w\u001a\u0004\u0018\u00010s2\u0006\u00108\u001a\u00020\u0006H\u0016J:\u0010x\u001a\u0008\u0012\u0004\u0012\u00020y0e2\u0006\u0010t\u001a\u00020\u00062\u0006\u0010u\u001a\u00020v2\u0008\u0010z\u001a\u0004\u0018\u00010s2\u0008\u0010{\u001a\u0004\u0018\u00010|2\u0008\u0010g\u001a\u0004\u0018\u00010\u0007J:\u0010x\u001a\u0008\u0012\u0004\u0012\u00020y0e2\u0006\u0010t\u001a\u00020\u00062\u0006\u0010u\u001a\u00020v2\u0008\u0010z\u001a\u0004\u0018\u00010s2\u0008\u0010{\u001a\u0004\u0018\u00010|2\u0006\u00108\u001a\u00020\u0006H\u0016J1\u0010}\u001a\u0008\u0012\u0004\u0012\u00020~0e2\u0006\u0010\u007f\u001a\u00020\u00062\u0006\u0010u\u001a\u00020v2\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010~2\u0006\u00108\u001a\u00020\u0006H\u0016J5\u0010\u0081\u0001\u001a\t\u0012\u0005\u0012\u00030\u0082\u00010e2\u0007\u0010\u0083\u0001\u001a\u00020\u00062\u0006\u0010u\u001a\u00020v2\n\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0082\u00012\u0006\u00108\u001a\u00020\u0006H\u0016J,\u0010\u0085\u0001\u001a\t\u0012\u0005\u0012\u00030\u0086\u00010e2\u0006\u0010u\u001a\u00020v2\n\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0086\u00012\u0006\u00108\u001a\u00020\u0006H\u0016J\u001c\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u00062\u0008\u00109\u001a\u0004\u0018\u00010\u00062\u0007\u0010\u0089\u0001\u001a\u00020\u0006J\u001e\u0010\u008a\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u008b\u00012\u0008\u00109\u001a\u0004\u0018\u00010\u0006J\'\u0010\u008c\u0001\u001a\u0004\u0018\u00010\u000b2\u0006\u00108\u001a\u00020\u00062\u0007\u0010\u008d\u0001\u001a\u00020\u00062\t\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u000bH\u0016J%\u0010\u008f\u0001\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u0007\u0010\u008d\u0001\u001a\u00020\u00062\t\u0010\u0090\u0001\u001a\u0004\u0018\u00010\u000bH\u0016J\u0011\u0010\u0091\u0001\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u0006H\u0016J\u001e\u0010\u0092\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0006\u0012\u0002\u0008\u0003\u0018\u00010\u008b\u00012\u0006\u00108\u001a\u00020\u0006H\u0016J\u001d\u0010\u0093\u0001\u001a\u00020\u001f2\u0008\u0010\u0094\u0001\u001a\u00030\u0095\u00012\u0008\u0010\u0087\u0001\u001a\u00030\u0096\u0001H\u0016J\u001b\u0010\u0097\u0001\u001a\u00020\u001f2\u0006\u0010U\u001a\u00020\u00062\u0008\u0010\u0094\u0001\u001a\u00030\u0095\u0001H\u0016J\u001c\u0010\u0097\u0001\u001a\u00020\u001f2\u0007\u0010\u0098\u0001\u001a\u00020;2\u0008\u0010\u0094\u0001\u001a\u00030\u0095\u0001H\u0016J%\u0010\u0099\u0001\u001a\u00020\u001f2\u0007\u0010\u0098\u0001\u001a\u00020;2\u0007\u0010\u009a\u0001\u001a\u00020\u00062\u0008\u0010\u0094\u0001\u001a\u00030\u0095\u0001H\u0016J$\u0010\u0099\u0001\u001a\u00020\u001f2\u0006\u00108\u001a\u00020\u00062\u0007\u0010\u009a\u0001\u001a\u00020\u00062\u0008\u0010\u0094\u0001\u001a\u00030\u0095\u0001H\u0016R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0008\u0010\tR(\u0010\n\u001a\u001c\u0012\u0004\u0012\u00020\u0006\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00050\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000RN\u0010\u000c\u001aB\u0012\u000c\u0012\n \u000e*\u0004\u0018\u00010\u00060\u0006\u0012\u000c\u0012\n \u000e*\u0004\u0018\u00010\u000f0\u000f \u000e* \u0012\u000c\u0012\n \u000e*\u0004\u0018\u00010\u00060\u0006\u0012\u000c\u0012\n \u000e*\u0004\u0018\u00010\u000f0\u000f\u0018\u00010\r0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u0014\u0010\u0015\"\u0004\u0008\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u0019\u0010\u0015\"\u0004\u0008\u001a\u0010\u0017\u00a8\u0006\u009e\u0001"
    }
    d2 = {
        "Lcom/exteragram/messenger/plugins/PythonPluginsEngine;",
        "Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;",
        "<init>",
        "()V",
        "pluginInstances",
        "Ljava/util/concurrent/ConcurrentHashMap;",
        "",
        "Lcom/chaquo/python/PyObject;",
        "getPluginInstances",
        "()Ljava/util/concurrent/ConcurrentHashMap;",
        "settingsCache",
        "",
        "dependencyPaths",
        "Ljava/util/concurrent/ConcurrentHashMap$KeySetView;",
        "kotlin.jvm.PlatformType",
        "",
        "python",
        "Lcom/chaquo/python/Python;",
        "devServerClass",
        "debuggerListener",
        "getDebuggerListener",
        "()Lcom/chaquo/python/PyObject;",
        "setDebuggerListener",
        "(Lcom/chaquo/python/PyObject;)V",
        "basePluginClass",
        "getBasePluginClass",
        "setBasePluginClass",
        "getPluginsController",
        "Lcom/exteragram/messenger/plugins/PluginsController;",
        "getPython",
        "initPython",
        "",
        "isPlugin",
        "file",
        "Ljava/io/File;",
        "messageObject",
        "Lorg/telegram/messenger/MessageObject;",
        "isEngineAvailable",
        "requireBasePluginClass",
        "installSdkArchive",
        "archiveFile",
        "fromApk",
        "initSdk",
        "stopAndUnloadSdk",
        "removeModulesRecursive",
        "sysModules",
        "prefix",
        "init",
        "callback",
        "Ljava/lang/Runnable;",
        "checkDevServer",
        "runDevServer",
        "stopDevServer",
        "shutdown",
        "loadPlugins",
        "loadPlugin",
        "pluginId",
        "filePath",
        "metadata",
        "Lcom/exteragram/messenger/plugins/Plugin;",
        "delegate",
        "Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;",
        "installPluginDependencies",
        "pluginMetadata",
        "disableShadowedPlugins",
        "dependencyName",
        "providedModules",
        "",
        "removePluginDependencies",
        "pruneDependencyPaths",
        "removeModulesUnderPath",
        "path",
        "removePluginPathsFromSysPath",
        "removeFromSysPath",
        "sysPath",
        "createPluginInstance",
        "unloadPlugin",
        "refreshImportCaches",
        "moduleDir",
        "evictPluginModule",
        "setPluginEnabled",
        "enabled",
        "Lorg/telegram/messenger/Utilities$Callback;",
        "deletePlugin",
        "getPluginPath",
        "id",
        "canOpenInExternalApp",
        "openInExternalApp",
        "sharePlugin",
        "loadPluginFromFile",
        "findModuleNameOwner",
        "validatePluginFromFile",
        "Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;",
        "parsePySettingDefinitions",
        "",
        "Lcom/exteragram/messenger/plugins/models/SettingItem;",
        "pyDefinitionsList",
        "loadPluginSettings",
        "executeOnAppEvent",
        "eventType",
        "executeHook",
        "Lcom/exteragram/messenger/plugins/PluginsController$HookResult;",
        "T",
        "pluginInstance",
        "initialValue",
        "valueClass",
        "Ljava/lang/Class;",
        "pyResultKey",
        "caller",
        "Lcom/exteragram/messenger/plugins/PythonPluginsEngine$PyMethodCaller;",
        "errorLogger",
        "",
        "(Lcom/chaquo/python/PyObject;Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$PyMethodCaller;Lorg/telegram/messenger/Utilities$Callback;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;",
        "(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$PyMethodCaller;Lorg/telegram/messenger/Utilities$Callback;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;",
        "executePreRequestHook",
        "Lorg/telegram/tgnet/TLObject;",
        "requestName",
        "account",
        "",
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
        "params",
        "fetchParameterValue",
        "parameterName",
        "parsePluginMetadata",
        "",
        "getPluginSetting",
        "key",
        "defaultValue",
        "setPluginSetting",
        "value",
        "clearPluginSettings",
        "getAllPluginSettings",
        "showInstallDialog",
        "fragment",
        "Lorg/telegram/ui/ActionBar/BaseFragment;",
        "Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;",
        "openPluginSettings",
        "plugin",
        "openPluginSetting",
        "linkAlias",
        "Companion",
        "PyMethodCaller",
        "Updater",
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
        "SMAP\nPythonPluginsEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PythonPluginsEngine.kt\ncom/exteragram/messenger/plugins/PythonPluginsEngine\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,2719:1\n1#2:2720\n41#3,12:2721\n41#3,12:2733\n41#3,12:2751\n41#3,12:2766\n41#3,12:2778\n41#3,12:2790\n41#3,12:2819\n1300#4,2:2745\n1315#4,4:2747\n777#4:2763\n873#4,2:2764\n1586#4:2802\n1661#4,3:2803\n296#4,2:2806\n1586#4:2808\n1661#4,3:2809\n777#4:2812\n873#4,2:2813\n1834#4,4:2815\n37#5,2:2831\n*S KotlinDebug\n*F\n+ 1 PythonPluginsEngine.kt\ncom/exteragram/messenger/plugins/PythonPluginsEngine\n*L\n497#1:2721,12\n734#1:2733,12\n795#1:2751,12\n1045#1:2766,12\n1050#1:2778,12\n1065#1:2790,12\n1231#1:2819,12\n755#1:2745,2\n755#1:2747,4\n817#1:2763\n817#1:2764,2\n1302#1:2802\n1302#1:2803,3\n1303#1:2806,2\n1421#1:2808\n1421#1:2809,3\n1422#1:2812\n1422#1:2813,2\n664#1:2815,4\n2135#1:2831,2\n*E\n"
    }
.end annotation


# static fields
.field public static final Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

.field private static final MAX_SDK_VERSION_BYTES:J = 0x10000L

.field private static final SAFE_MODE_ENABLE_ERROR:Ljava/lang/String; = "Plugins cannot be enabled while safe mode is active"

.field private static final SDK_ARCHIVE_OPTIONS:Lorg/simplifiles/archive/ArchiveExtractionOptions;

.field private static final SDK_ARCHIVE_POLICY:Lorg/simplifiles/archive/security/SecurityPolicy;

.field private static SDK_BETA:Z

.field private static SDK_DIR:Ljava/io/File;

.field private static final SDK_REQUIRED_MODULES:[Ljava/lang/String;

.field private static SDK_VERSION:Ljava/lang/String;

.field private static final VERSION_PATTERN:Ljava/util/regex/Pattern;

.field private static volatile sdkInitialized:Z


# instance fields
.field private volatile basePluginClass:Lcom/chaquo/python/PyObject;

.field private debuggerListener:Lcom/chaquo/python/PyObject;

.field private final dependencyPaths:Ljava/util/concurrent/ConcurrentHashMap$KeySetView;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap$KeySetView<",
            "Ljava/lang/String;",
            "Ljava/lang/Boolean;",
            ">;"
        }
    .end annotation
.end field

.field private devServerClass:Lcom/chaquo/python/PyObject;

.field private final pluginInstances:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Lcom/chaquo/python/PyObject;",
            ">;"
        }
    .end annotation
.end field

.field private volatile python:Lcom/chaquo/python/Python;

.field private final settingsCache:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Ljava/lang/Object;",
            ">;>;"
        }
    .end annotation
.end field


# direct methods
.method public static $r8$lambda$-0E5o7fqS78X1iCQH_4FWQg9Gbs(Ljava/lang/String;ILcom/chaquo/python/PyObject;Lorg/telegram/tgnet/TLRPC$Update;)Lcom/chaquo/python/PyObject;
    .locals 0

    .line 1893
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    .line 1894
    filled-new-array {p0, p1, p3}, [Ljava/lang/Object;

    move-result-object p0

    .line 1890
    const-string/jumbo p1, "on_update_hook"

    invoke-virtual {p2, p1, p0}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$5rj7AbfcOjfun3xF4Yfzy6NHuw4(Ljava/lang/String;Ljava/lang/Throwable;)V
    .locals 2

    .line 1899
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Failed to execute on_update_hook for "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 1898
    invoke-static {p0, p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method public static $r8$lambda$6O-J6o-SaqdBOobToMF6pnkn4OM(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;)Ljava/util/concurrent/ConcurrentHashMap;
    .locals 0

    .line 2036
    invoke-interface {p0, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/util/concurrent/ConcurrentHashMap;

    return-object p0
.end method

.method public static $r8$lambda$7feSxZkai0u2wy1FkRT-3cJE8Us(Ljava/lang/String;Ljava/lang/Throwable;)V
    .locals 2

    .line 1927
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Failed to execute on_updates_hook for "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 1926
    invoke-static {p0, p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method public static $r8$lambda$9hTMkDvZ_f5UE5Nq3UiVjEBnuis(Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;Lorg/telegram/ui/ActionBar/BaseFragment;)V
    .locals 0

    .line 2099
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;->getError()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->addToClipboard(Ljava/lang/CharSequence;)Z

    move-result p0

    if-eqz p0, :cond_0

    .line 2100
    invoke-static {p1}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p0

    .line 2101
    sget p1, Lorg/telegram/messenger/R$string;->TextCopied:I

    invoke-static {p1}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/ui/Components/BulletinFactory;->createCopyBulletin(Ljava/lang/String;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 2102
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    :cond_0
    return-void
.end method

.method public static $r8$lambda$AXaNKDWBBrxpVO4xVsgtXiGwau4(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/Plugin;)V
    .locals 3

    .line 2118
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;

    const/4 v1, 0x0

    const/4 v2, 0x2

    invoke-direct {v0, p1, v1, v2, v1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    invoke-virtual {p0, v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void
.end method

.method public static $r8$lambda$CLAasDeXOmfcISlYKM6qtJ2i2Yc(Lorg/telegram/messenger/Utilities$Callback;)V
    .locals 1

    const/4 v0, 0x0

    .line 1217
    invoke-interface {p0, v0}, Lorg/telegram/messenger/Utilities$Callback;->run(Ljava/lang/Object;)V

    return-void
.end method

.method public static $r8$lambda$FbLcqOJupzGwHMPwidTBuyt6HPM(Ljava/io/File;Ljava/lang/String;)Z
    .locals 3

    .line 614
    sget-object p0, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-virtual {p1, p0}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p0

    const/4 p1, 0x2

    const/4 v0, 0x0

    const-string v1, ".py"

    const/4 v2, 0x0

    invoke-static {p0, v1, v2, p1, v0}, Lkotlin/text/StringsKt;->endsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public static $r8$lambda$Huxv3v3qvzipy9seRBzlP2cJyQA(Lorg/telegram/messenger/Utilities$Callback;)V
    .locals 1

    const/4 v0, 0x0

    .line 1095
    invoke-interface {p0, v0}, Lorg/telegram/messenger/Utilities$Callback;->run(Ljava/lang/Object;)V

    return-void
.end method

.method public static $r8$lambda$HwG9F-Db6KK4BeK6Uwi_5nVHUrA(Lcom/exteragram/messenger/plugins/PythonPluginsEngine;Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;Lorg/telegram/ui/ActionBar/BaseFragment;)V
    .locals 18

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    move-object/from16 v8, p3

    .line 2124
    invoke-direct/range {p0 .. p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/plugins/PluginsController;->isPluginActive$TMessagesProj(Lcom/exteragram/messenger/plugins/Plugin;)Z

    move-result v0

    if-nez v0, :cond_0

    goto/16 :goto_5

    .line 2128
    :cond_0
    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v0

    new-instance v3, Ljava/lang/StringBuilder;

    const-string v4, "Opening plugin setting: "

    invoke-direct {v3, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, "/"

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    const/4 v0, 0x2

    .line 2129
    const-string v3, ":"

    const/4 v9, 0x0

    const/4 v10, 0x0

    invoke-static {v2, v3, v9, v0, v10}, Lkotlin/text/StringsKt;->contains$default(Ljava/lang/CharSequence;Ljava/lang/CharSequence;ZILjava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    .line 2130
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;

    invoke-direct {v0, v1, v2}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V

    goto/16 :goto_4

    .line 2133
    :cond_1
    invoke-direct/range {p0 .. p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController;->getSettings()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v0

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v0, v4}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/List;

    if-nez v0, :cond_2

    goto/16 :goto_5

    .line 2135
    :cond_2
    filled-new-array {v3}, [Ljava/lang/String;

    move-result-object v3

    const/4 v6, 0x6

    const/4 v7, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    invoke-static/range {v2 .. v7}, Lkotlin/text/StringsKt;->split$default(Ljava/lang/CharSequence;[Ljava/lang/String;ZIILjava/lang/Object;)Ljava/util/List;

    move-result-object v2

    check-cast v2, Ljava/util/Collection;

    .line 38
    new-array v3, v9, [Ljava/lang/String;

    invoke-interface {v2, v3}, Ljava/util/Collection;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object v2

    .line 2135
    move-object v6, v2

    check-cast v6, [Ljava/lang/String;

    .line 2136
    array-length v2, v6

    add-int/lit8 v2, v2, -0x1

    move-object v3, v0

    move v0, v9

    move-object v4, v10

    :goto_0
    if-ge v0, v2, :cond_8

    .line 2137
    aget-object v5, v6, v0

    .line 2138
    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v7

    :goto_1
    invoke-interface {v7}, Ljava/util/Iterator;->hasNext()Z

    move-result v11

    if-eqz v11, :cond_6

    invoke-interface {v7}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v11

    check-cast v11, Lcom/exteragram/messenger/plugins/models/SettingItem;

    .line 2139
    instance-of v12, v11, Lcom/exteragram/messenger/plugins/models/TextSetting;

    if-eqz v12, :cond_5

    invoke-virtual {v11}, Lcom/exteragram/messenger/plugins/models/SettingItem;->getLinkAlias()Ljava/lang/String;

    move-result-object v12

    invoke-static {v5, v12}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v12

    if-eqz v12, :cond_5

    .line 2140
    move-object v4, v11

    check-cast v4, Lcom/exteragram/messenger/plugins/models/TextSetting;

    .line 2142
    :try_start_0
    invoke-direct/range {p0 .. p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v5

    invoke-virtual {v5, v1}, Lcom/exteragram/messenger/plugins/PluginsController;->isPluginActive$TMessagesProj(Lcom/exteragram/messenger/plugins/Plugin;)Z

    move-result v5

    if-nez v5, :cond_3

    goto/16 :goto_5

    .line 2145
    :cond_3
    check-cast v11, Lcom/exteragram/messenger/plugins/models/TextSetting;

    invoke-virtual {v11}, Lcom/exteragram/messenger/plugins/models/TextSetting;->getCreateSubFragmentCallback()Lcom/chaquo/python/PyObject;

    move-result-object v5

    if-eqz v5, :cond_4

    new-array v7, v9, [Ljava/lang/Object;

    invoke-virtual {v5, v7}, Lcom/chaquo/python/PyObject;->call([Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object v5

    goto :goto_2

    :cond_4
    move-object v5, v10

    :goto_2
    if-eqz v5, :cond_6

    .line 2147
    invoke-virtual {v5}, Lcom/chaquo/python/PyObject;->asList()Ljava/util/List;

    move-result-object v5
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    move-object/from16 v11, p0

    :try_start_1
    invoke-virtual {v11, v5}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->parsePySettingDefinitions(Ljava/util/List;)Ljava/util/List;

    move-result-object v3
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1

    goto :goto_3

    :cond_5
    move-object/from16 v11, p0

    goto :goto_1

    :catch_0
    :cond_6
    move-object/from16 v11, p0

    :catch_1
    :goto_3
    if-nez v4, :cond_7

    .line 2154
    invoke-interface {v3}, Ljava/util/List;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_7

    .line 2155
    invoke-static {}, Lcom/exteragram/messenger/preferences/utils/SettingsRegistry;->getInstance()Lcom/exteragram/messenger/preferences/utils/SettingsRegistry;

    move-result-object v0

    invoke-virtual {v0, v8}, Lcom/exteragram/messenger/preferences/utils/SettingsRegistry;->onSettingNotFound(Lorg/telegram/ui/ActionBar/BaseFragment;)V

    goto :goto_5

    :cond_7
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_8
    if-nez v4, :cond_9

    goto :goto_5

    .line 2160
    :cond_9
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;

    .line 2162
    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/models/TextSetting;->getText()Ljava/lang/String;

    move-result-object v2

    .line 2164
    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/models/TextSetting;->getCreateSubFragmentCallback()Lcom/chaquo/python/PyObject;

    move-result-object v4

    .line 2165
    array-length v5, v6

    add-int/lit8 v5, v5, -0x1

    aget-object v5, v6, v5

    .line 2160
    invoke-direct/range {v0 .. v5}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;Ljava/util/List;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V

    .line 2166
    array-length v1, v6

    add-int/lit8 v1, v1, -0x1

    invoke-static {v6, v1}, Ljava/util/Arrays;->copyOf([Ljava/lang/Object;I)[Ljava/lang/Object;

    move-result-object v9

    const/16 v16, 0x3e

    const/16 v17, 0x0

    const-string v10, ":"

    const/4 v11, 0x0

    const/4 v12, 0x0

    const/4 v13, 0x0

    const/4 v14, 0x0

    const/4 v15, 0x0

    invoke-static/range {v9 .. v17}, Lkotlin/collections/ArraysKt;->joinToString$default([Ljava/lang/Object;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;->setSettingsLinkPrefix(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;

    move-result-object v0

    .line 2168
    :goto_4
    new-instance v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda2;

    invoke-direct {v1, v8, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda2;-><init>(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;)V

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    :goto_5
    return-void
.end method

.method public static $r8$lambda$JSPzY6YTUQGoel99NiGV2zLLX4Q(Lorg/telegram/messenger/Utilities$Callback;Ljava/lang/Throwable;)V
    .locals 0

    .line 1267
    invoke-static {p1}, Lcom/exteragram/messenger/utils/AppUtils;->stackTraceToString(Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object p1

    invoke-interface {p0, p1}, Lorg/telegram/messenger/Utilities$Callback;->run(Ljava/lang/Object;)V

    return-void
.end method

.method public static $r8$lambda$NGT_LVDpZf-x1FtJ6x4iJPoreg0(Lcom/exteragram/messenger/plugins/Plugin;Lcom/exteragram/messenger/plugins/PythonPluginsEngine;Ljava/lang/String;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;Lorg/telegram/messenger/Utilities$Callback;)V
    .locals 16

    move-object/from16 v1, p1

    move-object/from16 v2, p2

    move-object/from16 v3, p4

    .line 1169
    const-string v4, "Failed to reload original plugin after update failure for "

    .line 1176
    const-string/jumbo v5, "plugin_enabled_"

    const/4 v6, 0x0

    const/4 v7, 0x0

    if-nez p0, :cond_2

    .line 1177
    :try_start_0
    invoke-virtual/range {p1 .. p2}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->validatePluginFromFile(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;

    move-result-object v0

    .line 1178
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;->getError()Ljava/lang/String;

    move-result-object v8

    if-nez v8, :cond_1

    .line 1181
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;->getPlugin()Lcom/exteragram/messenger/plugins/Plugin;

    move-result-object v0

    if-eqz v0, :cond_0

    goto :goto_2

    :cond_0
    const-string v0, "Required value was null."

    new-instance v8, Ljava/lang/IllegalArgumentException;

    invoke-direct {v8, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v8

    :catchall_0
    move-exception v0

    move-object v8, v6

    move-object v10, v8

    :goto_0
    move-object v12, v10

    move v9, v7

    move v11, v9

    :goto_1
    move-object v6, v0

    goto/16 :goto_5

    .line 1179
    :cond_1
    new-instance v8, Ljava/lang/Exception;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;->getError()Ljava/lang/String;

    move-result-object v0

    invoke-direct {v8, v0}, Ljava/lang/Exception;-><init>(Ljava/lang/String;)V

    throw v8

    :cond_2
    move-object/from16 v0, p0

    .line 1186
    :goto_2
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v8
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 1187
    :try_start_1
    invoke-direct {v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v9

    invoke-virtual {v9}, Lcom/exteragram/messenger/plugins/PluginsController;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v9

    .line 1188
    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v10, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    .line 1187
    invoke-interface {v9, v10, v7}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v9
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_5

    .line 1191
    :try_start_2
    new-instance v10, Ljava/io/File;

    invoke-direct {v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v11

    invoke-virtual {v11}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginsDir()Ljava/io/File;

    move-result-object v11

    new-instance v12, Ljava/lang/StringBuilder;

    invoke-direct {v12}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v12, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v13, ".py"

    invoke-virtual {v12, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v12}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v12

    invoke-direct {v10, v11, v12}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_4

    .line 1193
    :try_start_3
    invoke-virtual {v10}, Ljava/io/File;->exists()Z

    move-result v11
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_3

    if-eqz v11, :cond_3

    .line 1196
    :try_start_4
    invoke-virtual {v1, v8}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->unloadPlugin(Ljava/lang/String;)V

    .line 1197
    new-instance v12, Ljava/io/File;

    invoke-direct {v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v13

    invoke-virtual {v13}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginsDir()Ljava/io/File;

    move-result-object v13

    new-instance v14, Ljava/lang/StringBuilder;

    invoke-direct {v14}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v14, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v15, ".py.bak"

    invoke-virtual {v14, v15}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v14}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v14

    invoke-direct {v12, v13, v14}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_2

    .line 1199
    :try_start_5
    sget-object v6, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-static {v6, v12}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$deleteFileIfExists(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/io/File;)V

    .line 1200
    invoke-static {v10}, Lorg/simplifiles/SimpliFiles;->file(Ljava/io/File;)Lorg/simplifiles/files/SimpliFile;

    move-result-object v6

    sget-object v13, Lorg/simplifiles/files/OverwritePolicy;->ERROR:Lorg/simplifiles/files/OverwritePolicy;

    invoke-virtual {v6, v12, v13}, Lorg/simplifiles/files/SimpliFile;->moveTo(Ljava/io/File;Lorg/simplifiles/files/OverwritePolicy;)Lorg/simplifiles/files/SimpliFile;

    .line 1201
    invoke-direct {v1, v8}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->removePluginDependencies(Ljava/lang/String;)V
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_1

    move-object v6, v12

    goto :goto_3

    :catchall_1
    move-exception v0

    goto/16 :goto_1

    :catchall_2
    move-exception v0

    move-object v12, v6

    goto/16 :goto_1

    .line 1204
    :cond_3
    :goto_3
    :try_start_6
    new-instance v12, Ljava/io/File;

    invoke-direct {v12, v2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v12}, Ljava/io/File;->exists()Z

    move-result v12

    if-eqz v12, :cond_4

    .line 1208
    invoke-static {v2}, Lorg/simplifiles/SimpliFiles;->file(Ljava/lang/String;)Lorg/simplifiles/files/SimpliFile;

    move-result-object v12

    sget-object v13, Lorg/simplifiles/files/OverwritePolicy;->REPLACE:Lorg/simplifiles/files/OverwritePolicy;

    invoke-virtual {v12, v10, v13}, Lorg/simplifiles/files/SimpliFile;->copyTo(Ljava/io/File;Lorg/simplifiles/files/OverwritePolicy;)Lorg/simplifiles/files/SimpliFile;

    .line 1210
    invoke-virtual {v10}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v12

    move-object/from16 v13, p3

    invoke-virtual {v1, v8, v12, v0, v13}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->loadPlugin(Ljava/lang/String;Ljava/lang/String;Lcom/exteragram/messenger/plugins/Plugin;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)V

    .line 1213
    sget-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-static {v0, v6}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$deleteFileIfExists(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/io/File;)V

    .line 1215
    invoke-direct {v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController;->notifyPluginsChanged()V

    if-eqz v3, :cond_9

    .line 1217
    new-instance v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda18;

    invoke-direct {v0, v3}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda18;-><init>(Lorg/telegram/messenger/Utilities$Callback;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    .line 1205
    :cond_4
    new-instance v0, Ljava/lang/Exception;

    const-string v12, "Plugin file is no longer available. It may have been moved or deleted."

    invoke-direct {v0, v12}, Ljava/lang/Exception;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_2

    :catchall_3
    move-exception v0

    move-object v12, v6

    :goto_4
    move v11, v7

    goto/16 :goto_1

    :catchall_4
    move-exception v0

    move-object v10, v6

    move-object v12, v10

    goto :goto_4

    :catchall_5
    move-exception v0

    move-object v10, v6

    goto/16 :goto_0

    .line 1220
    :goto_5
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v13, "Unexpected error loading plugin from file: "

    invoke-direct {v0, v13}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0, v6}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    if-eqz v8, :cond_8

    .line 1224
    sget-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-static {v0, v10}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$deleteFileIfExists(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/io/File;)V

    const/4 v2, 0x1

    if-eqz v11, :cond_6

    if-eqz v12, :cond_6

    .line 1227
    invoke-virtual {v12}, Ljava/io/File;->exists()Z

    move-result v0

    if-eqz v0, :cond_6

    if-eqz v10, :cond_6

    .line 1229
    :try_start_7
    invoke-static {v12}, Lorg/simplifiles/SimpliFiles;->file(Ljava/io/File;)Lorg/simplifiles/files/SimpliFile;

    move-result-object v0

    sget-object v11, Lorg/simplifiles/files/OverwritePolicy;->REPLACE:Lorg/simplifiles/files/OverwritePolicy;

    invoke-virtual {v0, v10, v11}, Lorg/simplifiles/files/SimpliFile;->moveTo(Ljava/io/File;Lorg/simplifiles/files/OverwritePolicy;)Lorg/simplifiles/files/SimpliFile;
    :try_end_7
    .catch Ljava/lang/Exception; {:try_start_7 .. :try_end_7} :catch_2

    .line 1231
    :try_start_8
    invoke-direct {v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v0

    .line 45
    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    .line 1233
    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    .line 1232
    invoke-interface {v0, v5, v9}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 50
    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V
    :try_end_8
    .catch Ljava/lang/Exception; {:try_start_8 .. :try_end_8} :catch_1

    .line 1238
    :try_start_9
    invoke-virtual {v10}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v8, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->loadPlugin(Ljava/lang/String;Ljava/lang/String;)V

    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_9
    .catch Ljava/lang/Exception; {:try_start_9 .. :try_end_9} :catch_0

    goto :goto_6

    :catch_0
    move-exception v0

    .line 1241
    :try_start_a
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v5, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    .line 1240
    invoke-static {v4, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 1244
    invoke-direct {v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v4

    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v4

    invoke-virtual {v4, v8}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/exteragram/messenger/plugins/Plugin;

    if-eqz v4, :cond_5

    invoke-virtual {v4, v0}, Lcom/exteragram/messenger/plugins/Plugin;->setError(Ljava/lang/Throwable;)V

    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_a
    .catch Ljava/lang/Exception; {:try_start_a .. :try_end_a} :catch_1

    goto :goto_6

    :catch_1
    move-exception v0

    move v7, v2

    goto :goto_7

    :cond_5
    :goto_6
    move v7, v2

    goto :goto_8

    :catch_2
    move-exception v0

    .line 1247
    :goto_7
    new-instance v4, Ljava/lang/StringBuilder;

    const-string v5, "Failed to restore backup for plugin "

    invoke-direct {v4, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v4, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v4, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_6
    :goto_8
    if-nez v7, :cond_8

    .line 1252
    invoke-direct {v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    invoke-virtual {v0, v8}, Lcom/exteragram/messenger/plugins/PluginsController;->cleanupPlugin(Ljava/lang/String;)V

    .line 1254
    :try_start_b
    invoke-direct {v1, v8}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->removePluginDependencies(Ljava/lang/String;)V
    :try_end_b
    .catch Ljava/lang/Exception; {:try_start_b .. :try_end_b} :catch_3

    goto :goto_9

    :catch_3
    move-exception v0

    .line 1256
    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 1258
    :goto_9
    iget-object v0, v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->pluginInstances:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, v8}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/chaquo/python/PyObject;

    if-eqz v0, :cond_7

    .line 1259
    invoke-virtual {v0}, Lcom/chaquo/python/PyObject;->close()V

    .line 1260
    :cond_7
    invoke-direct {v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    invoke-virtual {v0, v8, v2}, Lcom/exteragram/messenger/plugins/PluginsController;->clearPluginSettingsPreferences(Ljava/lang/String;Z)V

    .line 1261
    invoke-direct {v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v0

    invoke-virtual {v0, v8}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 1262
    sget-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-static {v0, v10}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$deleteFileIfExists(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/io/File;)V

    .line 1265
    :cond_8
    invoke-direct {v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController;->notifyPluginsChanged()V

    if-eqz v3, :cond_9

    .line 1267
    new-instance v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda19;

    invoke-direct {v0, v3, v6}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda19;-><init>(Lorg/telegram/messenger/Utilities$Callback;Ljava/lang/Throwable;)V

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    :cond_9
    return-void
.end method

.method public static $r8$lambda$R9eA3kAiq8LxF0pb-ZLXEwqc7Mw(Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/lang/String;Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;)V
    .locals 4

    .line 2094
    invoke-static {p0}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object v0

    .line 2095
    sget v1, Lorg/telegram/messenger/R$raw;->error:I

    .line 2096
    sget v2, Lorg/telegram/messenger/R$string;->PluginInstallError:I

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object p1

    invoke-static {v2, p1}, Lorg/telegram/messenger/LocaleController;->formatString(I[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p1

    .line 2097
    invoke-static {p0}, Lcom/exteragram/messenger/utils/text/LocaleUtils;->createCopySpan(Lorg/telegram/ui/ActionBar/BaseFragment;)Landroid/text/Spannable;

    move-result-object v2

    .line 2094
    new-instance v3, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda7;

    invoke-direct {v3, p2, p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda7;-><init>(Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;Lorg/telegram/ui/ActionBar/BaseFragment;)V

    invoke-virtual {v0, v1, p1, v2, v3}, Lorg/telegram/ui/Components/BulletinFactory;->createSimpleBulletin(ILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/Runnable;)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    .line 2104
    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    return-void
.end method

.method public static $r8$lambda$ViM9rtYG_B9-ookEK5EXHR-GFio(Ljava/lang/String;ILcom/chaquo/python/PyObject;Lorg/telegram/tgnet/TLObject;)Lcom/chaquo/python/PyObject;
    .locals 0

    .line 1787
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    .line 1788
    filled-new-array {p0, p1, p3}, [Ljava/lang/Object;

    move-result-object p0

    .line 1784
    const-string/jumbo p1, "pre_request_hook"

    invoke-virtual {p2, p1, p0}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$XvNtpK1Osqm6XpkwzFDoVJPVJ9E(Lorg/telegram/messenger/Utilities$Callback;)V
    .locals 1

    const/4 v0, 0x0

    .line 1058
    invoke-interface {p0, v0}, Lorg/telegram/messenger/Utilities$Callback;->run(Ljava/lang/Object;)V

    return-void
.end method

.method public static $r8$lambda$Yu6Vo0jC7uBHzV8I4HTCUhN5Nfk(Lorg/telegram/messenger/Utilities$Callback;Ljava/lang/Throwable;)V
    .locals 0

    .line 1070
    invoke-static {p1}, Lcom/exteragram/messenger/utils/AppUtils;->stackTraceToString(Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object p1

    invoke-interface {p0, p1}, Lorg/telegram/messenger/Utilities$Callback;->run(Ljava/lang/Object;)V

    return-void
.end method

.method public static $r8$lambda$ZHZbkOYG7xmxl2kV0ImdQjtKwC4(Lcom/exteragram/messenger/plugins/PythonPluginsEngine;Ljava/lang/Runnable;)V
    .locals 10

    .line 588
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPython()Lcom/chaquo/python/Python;

    move-result-object v0

    if-nez v0, :cond_0

    if-eqz p1, :cond_a

    .line 591
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    .line 597
    :cond_0
    :try_start_0
    const-string/jumbo v1, "sys"

    invoke-virtual {v0, v1}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object v0

    .line 598
    const-string/jumbo v1, "path"

    invoke-virtual {v0, v1}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/chaquo/python/PyObject;

    .line 599
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v1

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginsDir()Ljava/io/File;

    move-result-object v1

    invoke-virtual {v1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v1

    .line 600
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsSafeMode()Z

    move-result v2

    if-nez v2, :cond_1

    if-eqz v0, :cond_1

    .line 601
    const-string v2, "__contains__"

    filled-new-array {v1}, [Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v0, v2, v3}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object v2

    invoke-virtual {v2}, Lcom/chaquo/python/PyObject;->toBoolean()Z

    move-result v2

    if-nez v2, :cond_1

    .line 603
    const-string v2, "append"

    filled-new-array {v1}, [Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {v0, v2, v1}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    :try_end_0
    .catch Lcom/chaquo/python/PyException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p0

    goto/16 :goto_6

    .line 613
    :cond_1
    :goto_0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginsDir()Ljava/io/File;

    move-result-object v0

    new-instance v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda6;

    invoke-direct {v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda6;-><init>()V

    invoke-virtual {v0, v1}, Ljava/io/File;->listFiles(Ljava/io/FilenameFilter;)[Ljava/io/File;

    move-result-object v0

    if-nez v0, :cond_2

    .line 617
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->notifyPluginsChanged()V

    if-eqz p1, :cond_a

    .line 619
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    .line 624
    :cond_2
    invoke-static {v0}, Lkotlin/jvm/internal/ArrayIteratorKt;->iterator([Ljava/lang/Object;)Ljava/util/Iterator;

    move-result-object v0

    :goto_1
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    const/4 v2, 0x0

    if-eqz v1, :cond_6

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/io/File;

    .line 625
    invoke-virtual {v1}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/String;->length()I

    move-result v4

    add-int/lit8 v4, v4, -0x3

    invoke-virtual {v3, v2, v4}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v3

    const/4 v4, 0x0

    .line 628
    :try_start_1
    invoke-virtual {v1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {p0, v5}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->validatePluginFromFile(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;

    move-result-object v5
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    .line 629
    :try_start_2
    invoke-virtual {v5}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;->getError()Ljava/lang/String;

    move-result-object v6

    if-nez v6, :cond_3

    .line 632
    invoke-virtual {v1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;->getPlugin()Lcom/exteragram/messenger/plugins/Plugin;

    move-result-object v7

    invoke-virtual {p0, v3, v6, v7}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->loadPlugin(Ljava/lang/String;Ljava/lang/String;Lcom/exteragram/messenger/plugins/Plugin;)V

    goto :goto_1

    :catchall_0
    move-exception v6

    goto :goto_2

    .line 630
    :cond_3
    new-instance v6, Ljava/lang/Exception;

    invoke-virtual {v5}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;->getError()Ljava/lang/String;

    move-result-object v7

    invoke-direct {v6, v7}, Ljava/lang/Exception;-><init>(Ljava/lang/String;)V

    throw v6
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    :catchall_1
    move-exception v6

    move-object v5, v4

    .line 634
    :goto_2
    invoke-virtual {v1}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v6}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v7

    new-instance v8, Ljava/lang/StringBuilder;

    const-string v9, "Failed to load plugin "

    invoke-direct {v8, v9}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v8, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ". Reason: "

    invoke-virtual {v8, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1, v6}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    if-eqz v5, :cond_4

    .line 636
    invoke-virtual {v5}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;->getPlugin()Lcom/exteragram/messenger/plugins/Plugin;

    move-result-object v4

    :cond_4
    if-eqz v4, :cond_5

    .line 638
    new-instance v1, Lcom/exteragram/messenger/plugins/Plugin;

    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/Plugin;->getName()Ljava/lang/String;

    move-result-object v5

    invoke-direct {v1, v3, v5}, Lcom/exteragram/messenger/plugins/Plugin;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    .line 639
    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/Plugin;->getAuthor()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v1, v5}, Lcom/exteragram/messenger/plugins/Plugin;->setAuthor(Ljava/lang/String;)V

    .line 640
    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/Plugin;->getDescription()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v1, v5}, Lcom/exteragram/messenger/plugins/Plugin;->setDescription(Ljava/lang/String;)V

    .line 641
    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/Plugin;->getIcon()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v1, v5}, Lcom/exteragram/messenger/plugins/Plugin;->setIcon(Ljava/lang/String;)V

    .line 642
    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/Plugin;->getVersion()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v1, v5}, Lcom/exteragram/messenger/plugins/Plugin;->setVersion(Ljava/lang/String;)V

    .line 643
    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/Plugin;->getAppVersion()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v1, v5}, Lcom/exteragram/messenger/plugins/Plugin;->setAppVersion(Ljava/lang/String;)V

    .line 644
    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/Plugin;->getSdkVersion()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v1, v5}, Lcom/exteragram/messenger/plugins/Plugin;->setSdkVersion(Ljava/lang/String;)V

    .line 645
    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/Plugin;->getRequirements()Ljava/util/List;

    move-result-object v5

    invoke-virtual {v1, v5}, Lcom/exteragram/messenger/plugins/Plugin;->setRequirements(Ljava/util/List;)V

    .line 646
    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/Plugin;->getEngine()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Lcom/exteragram/messenger/plugins/Plugin;->setEngine(Ljava/lang/String;)V

    goto :goto_3

    .line 649
    :cond_5
    new-instance v1, Lcom/exteragram/messenger/plugins/Plugin;

    invoke-direct {v1, v3, v3}, Lcom/exteragram/messenger/plugins/Plugin;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    .line 650
    sget v4, Lorg/telegram/messenger/R$string;->PluginNoAuthor:I

    invoke-static {v4}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Lcom/exteragram/messenger/plugins/Plugin;->setAuthor(Ljava/lang/String;)V

    .line 651
    const-string v4, "1.0"

    invoke-virtual {v1, v4}, Lcom/exteragram/messenger/plugins/Plugin;->setVersion(Ljava/lang/String;)V

    .line 652
    const-string/jumbo v4, "python"

    invoke-virtual {v1, v4}, Lcom/exteragram/messenger/plugins/Plugin;->setEngine(Ljava/lang/String;)V

    .line 656
    :goto_3
    invoke-virtual {v1, v6}, Lcom/exteragram/messenger/plugins/Plugin;->setError(Ljava/lang/Throwable;)V

    .line 657
    invoke-virtual {v1, v2}, Lcom/exteragram/messenger/plugins/Plugin;->setEnabled(Z)V

    .line 658
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v2

    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v2

    invoke-interface {v2, v3, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto/16 :goto_1

    .line 662
    :cond_6
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController;->notifyPluginsChanged()V

    .line 664
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentHashMap;->values()Ljava/util/Collection;

    move-result-object v0

    check-cast v0, Ljava/lang/Iterable;

    .line 1834
    move-object v1, v0

    check-cast v1, Ljava/util/Collection;

    invoke-interface {v1}, Ljava/util/Collection;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_7

    goto :goto_5

    .line 1836
    :cond_7
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_8
    :goto_4
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_9

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/plugins/Plugin;

    .line 664
    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/Plugin;->isEnabled()Z

    move-result v1

    if-eqz v1, :cond_8

    add-int/lit8 v2, v2, 0x1

    if-gez v2, :cond_8

    .line 1836
    invoke-static {}, Lkotlin/collections/CollectionsKt;->throwCountOverflow()V

    goto :goto_4

    .line 665
    :cond_9
    :goto_5
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object p0

    invoke-virtual {p0}, Ljava/util/concurrent/ConcurrentHashMap;->size()I

    move-result p0

    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Python plugin system initialized. Total: "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, ", Enabled: "

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    if-eqz p1, :cond_a

    .line 667
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    goto :goto_7

    .line 606
    :goto_6
    const-string v0, "Failed to setup Python environment for plugins"

    invoke-static {v0, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    if-eqz p1, :cond_a

    .line 608
    invoke-static {p1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    :cond_a
    :goto_7
    return-void
.end method

.method public static $r8$lambda$b_SWPjLcoxAUmuhmOu3Q7ftR5Fo(Ljava/lang/String;)Ljava/util/concurrent/ConcurrentHashMap;
    .locals 0

    .line 2036
    new-instance p0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {p0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    return-object p0
.end method

.method public static $r8$lambda$cwkMqqP-Ork5Iq87zec6AWD3pb0(ILcom/chaquo/python/PyObject;Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;)Lcom/chaquo/python/PyObject;
    .locals 0

    .line 1947
    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    .line 1948
    filled-new-array {p0, p2}, [Ljava/lang/Object;

    move-result-object p0

    .line 1945
    const-string/jumbo p2, "on_send_message_hook"

    invoke-virtual {p1, p2, p0}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$eeXz2PiC7JLWvOZ286cT7LVckQE(Ljava/lang/String;Ljava/lang/Throwable;)V
    .locals 2

    .line 1953
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Failed to execute on_send_message_hook for "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 1952
    invoke-static {p0, p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method public static $r8$lambda$kKVWL1eL2TCAIGWICttRb9fqocc(Lorg/telegram/messenger/Utilities$Callback;)V
    .locals 1

    .line 1011
    const-string v0, "Plugins cannot be enabled while safe mode is active"

    invoke-interface {p0, v0}, Lorg/telegram/messenger/Utilities$Callback;->run(Ljava/lang/Object;)V

    return-void
.end method

.method public static $r8$lambda$nvRtZjLfjaRHKshQM4MzuvmjaU8(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;)Ljava/util/concurrent/ConcurrentHashMap;
    .locals 0

    .line 2027
    invoke-interface {p0, p1}, Lkotlin/jvm/functions/Function1;->invoke(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/util/concurrent/ConcurrentHashMap;

    return-object p0
.end method

.method public static $r8$lambda$oSwq0KS9WeQUq_sw29d6K3u_EmA(Ljava/lang/String;ILcom/chaquo/python/PyObject;Lorg/telegram/tgnet/TLRPC$Updates;)Lcom/chaquo/python/PyObject;
    .locals 0

    .line 1921
    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    .line 1922
    filled-new-array {p0, p1, p3}, [Ljava/lang/Object;

    move-result-object p0

    .line 1918
    const-string/jumbo p1, "on_updates_hook"

    invoke-virtual {p2, p1, p0}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p0

    return-object p0
.end method

.method public static $r8$lambda$ojQv3mpXW-y1kA7sYxb1icPtjFU(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/ui/PluginSettingsActivity;)V
    .locals 0

    .line 2168
    invoke-virtual {p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->presentFragment(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    return-void
.end method

.method public static $r8$lambda$w3FOi-q-YECzQmx8rtkflkZAy88(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    .locals 2

    .line 1793
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Failed to execute pre_request_hook in "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, " for "

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 1792
    invoke-static {p0, p2}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method public static $r8$lambda$z4QgCtHpe2floOOtUUiTr6P9Yi8(Ljava/lang/String;)Ljava/util/concurrent/ConcurrentHashMap;
    .locals 0

    .line 2027
    new-instance p0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {p0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    return-object p0
.end method

.method static constructor <clinit>()V
    .locals 3

    new-instance v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    const/4 v1, 0x0

    invoke-direct {v0, v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;-><init>(Lkotlin/jvm/internal/DefaultConstructorMarker;)V

    sput-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    .line 93
    const-string v0, "^(>=|<=|==|>|<)(.+)$"

    invoke-static {v0}, Ljava/util/regex/Pattern;->compile(Ljava/lang/String;)Ljava/util/regex/Pattern;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->VERSION_PATTERN:Ljava/util/regex/Pattern;

    .line 96
    const-string v0, "base_plugin"

    .line 97
    const-string/jumbo v1, "plugin_settings"

    const-string v2, "_sdk_version"

    filled-new-array {v2, v0, v1}, [Ljava/lang/String;

    move-result-object v0

    .line 94
    sput-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_REQUIRED_MODULES:[Ljava/lang/String;

    .line 99
    sget-object v0, Lorg/simplifiles/archive/security/SecurityPolicy;->Companion:Lorg/simplifiles/archive/security/SecurityPolicy$Companion;

    invoke-virtual {v0}, Lorg/simplifiles/archive/security/SecurityPolicy$Companion;->builder()Lorg/simplifiles/archive/security/SecurityPolicy$Builder;

    move-result-object v0

    const-wide/32 v1, 0x186a0

    .line 100
    invoke-virtual {v0, v1, v2}, Lorg/simplifiles/archive/security/SecurityPolicy$Builder;->maxEntries(J)Lorg/simplifiles/archive/security/SecurityPolicy$Builder;

    move-result-object v0

    const-wide v1, 0x80000000L

    .line 101
    invoke-virtual {v0, v1, v2}, Lorg/simplifiles/archive/security/SecurityPolicy$Builder;->maxTotalUncompressedSize(J)Lorg/simplifiles/archive/security/SecurityPolicy$Builder;

    move-result-object v0

    const-wide/32 v1, 0x20000000

    .line 102
    invoke-virtual {v0, v1, v2}, Lorg/simplifiles/archive/security/SecurityPolicy$Builder;->maxSingleFileSize(J)Lorg/simplifiles/archive/security/SecurityPolicy$Builder;

    move-result-object v0

    const-wide v1, 0x407f400000000000L    # 500.0

    .line 103
    invoke-virtual {v0, v1, v2}, Lorg/simplifiles/archive/security/SecurityPolicy$Builder;->maxCompressionRatio(D)Lorg/simplifiles/archive/security/SecurityPolicy$Builder;

    move-result-object v0

    .line 104
    invoke-virtual {v0}, Lorg/simplifiles/archive/security/SecurityPolicy$Builder;->build()Lorg/simplifiles/archive/security/SecurityPolicy;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_ARCHIVE_POLICY:Lorg/simplifiles/archive/security/SecurityPolicy;

    .line 105
    sget-object v0, Lorg/simplifiles/archive/ArchiveExtractionOptions;->Companion:Lorg/simplifiles/archive/ArchiveExtractionOptions$Companion;

    invoke-virtual {v0}, Lorg/simplifiles/archive/ArchiveExtractionOptions$Companion;->builder()Lorg/simplifiles/archive/ArchiveExtractionOptions$Builder;

    move-result-object v0

    .line 106
    sget-object v1, Lorg/simplifiles/archive/ExtractionTargetPolicy;->REPLACE:Lorg/simplifiles/archive/ExtractionTargetPolicy;

    invoke-virtual {v0, v1}, Lorg/simplifiles/archive/ArchiveExtractionOptions$Builder;->targetPolicy(Lorg/simplifiles/archive/ExtractionTargetPolicy;)Lorg/simplifiles/archive/ArchiveExtractionOptions$Builder;

    move-result-object v0

    .line 107
    invoke-virtual {v0}, Lorg/simplifiles/archive/ArchiveExtractionOptions$Builder;->build()Lorg/simplifiles/archive/ArchiveExtractionOptions;

    move-result-object v0

    sput-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_ARCHIVE_OPTIONS:Lorg/simplifiles/archive/ArchiveExtractionOptions;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 90
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 176
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->pluginInstances:Ljava/util/concurrent/ConcurrentHashMap;

    .line 178
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->settingsCache:Ljava/util/concurrent/ConcurrentHashMap;

    .line 180
    invoke-static {}, Ljava/util/concurrent/ConcurrentHashMap;->newKeySet()Ljava/util/concurrent/ConcurrentHashMap$KeySetView;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->dependencyPaths:Ljava/util/concurrent/ConcurrentHashMap$KeySetView;

    return-void
.end method

.method public static final synthetic access$getSDK_BETA$cp()Z
    .locals 1

    .line 90
    sget-boolean v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_BETA:Z

    return v0
.end method

.method public static final synthetic access$getSDK_DIR$cp()Ljava/io/File;
    .locals 1

    .line 90
    sget-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_DIR:Ljava/io/File;

    return-object v0
.end method

.method public static final synthetic access$getSDK_REQUIRED_MODULES$cp()[Ljava/lang/String;
    .locals 1

    .line 90
    sget-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_REQUIRED_MODULES:[Ljava/lang/String;

    return-object v0
.end method

.method public static final synthetic access$getSDK_VERSION$cp()Ljava/lang/String;
    .locals 1

    .line 90
    sget-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_VERSION:Ljava/lang/String;

    return-object v0
.end method

.method public static final synthetic access$getSdkInitialized$cp()Z
    .locals 1

    .line 90
    sget-boolean v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->sdkInitialized:Z

    return v0
.end method

.method public static final synthetic access$setSDK_BETA$cp(Z)V
    .locals 0

    .line 90
    sput-boolean p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_BETA:Z

    return-void
.end method

.method public static final synthetic access$setSDK_VERSION$cp(Ljava/lang/String;)V
    .locals 0

    .line 90
    sput-object p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_VERSION:Ljava/lang/String;

    return-void
.end method

.method private final createPluginInstance(Ljava/lang/String;Ljava/lang/String;Lcom/exteragram/messenger/plugins/Plugin;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)V
    .locals 5

    .line 891
    const-string v0, "Failed to import plugin module: "

    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->requireBasePluginClass()Lcom/chaquo/python/PyObject;

    move-result-object v1

    .line 893
    invoke-direct {p0, p1, p3, p4}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->installPluginDependencies(Ljava/lang/String;Lcom/exteragram/messenger/plugins/Plugin;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)V

    .line 895
    new-instance p4, Ljava/io/File;

    invoke-direct {p4, p2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {p4}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object p4

    invoke-direct {p0, p1, p4}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->refreshImportCaches(Ljava/lang/String;Ljava/io/File;)V

    .line 897
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p4

    invoke-virtual {p4}, Lcom/exteragram/messenger/plugins/PluginsController;->getWatchdog()Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    move-result-object p4

    invoke-virtual {p4, p1}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionStarted(Ljava/lang/String;)V

    .line 899
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPython()Lcom/chaquo/python/Python;

    move-result-object p4

    if-eqz p4, :cond_4

    invoke-virtual {p4, p1}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object p4
    :try_end_0
    .catch Lcom/chaquo/python/PyException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz p4, :cond_4

    .line 904
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController;->getWatchdog()Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionFinished(Ljava/lang/String;)V

    .line 907
    const-string v0, "__file__"

    invoke-virtual {p4, v0}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/chaquo/python/PyObject;

    const/4 v2, 0x0

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lcom/chaquo/python/PyObject;->toString()Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    :cond_0
    move-object v0, v2

    .line 908
    :goto_0
    sget-object v3, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-static {v3, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$canonicalPathOrNull(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, p2}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$canonicalPathOrNull(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p2

    invoke-static {v4, p2}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p2

    if-nez p2, :cond_2

    .line 909
    new-instance p0, Ljava/lang/Exception;

    if-nez v0, :cond_1

    .line 910
    const-string v0, "built into the runtime"

    :cond_1
    new-instance p2, Ljava/lang/StringBuilder;

    const-string p3, "Plugin id \'"

    invoke-direct {p2, p3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "\' is already taken by the Python module "

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, ". Change \'__id__\' to a name that no other module uses."

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 909
    invoke-direct {p0, p1}, Ljava/lang/Exception;-><init>(Ljava/lang/String;)V

    throw p0

    .line 915
    :cond_2
    const-string p2, "_findPluginClass"

    filled-new-array {p4}, [Ljava/lang/Object;

    move-result-object p4

    invoke-virtual {v1, p2, p4}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p2

    if-eqz p2, :cond_3

    const/4 p4, 0x0

    .line 918
    new-array p4, p4, [Ljava/lang/Object;

    invoke-virtual {p2, p4}, Lcom/chaquo/python/PyObject;->call([Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p2

    .line 919
    const-string/jumbo p4, "id"

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, p4, v0}, Lcom/chaquo/python/PyObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    .line 920
    const-string/jumbo p4, "name"

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/Plugin;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, p4, v0}, Lcom/chaquo/python/PyObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    .line 921
    const-string p4, "description"

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/Plugin;->getDescription()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, p4, v0}, Lcom/chaquo/python/PyObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    .line 922
    const-string p4, "author"

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/Plugin;->getAuthor()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, p4, v0}, Lcom/chaquo/python/PyObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    .line 923
    const-string/jumbo p4, "version"

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/Plugin;->getVersion()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, p4, v0}, Lcom/chaquo/python/PyObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    .line 924
    const-string/jumbo p4, "icon"

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/Plugin;->getIcon()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, p4, v0}, Lcom/chaquo/python/PyObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    .line 925
    const-string p4, "app_version"

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/Plugin;->getAppVersion()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, p4, v0}, Lcom/chaquo/python/PyObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    .line 926
    const-string/jumbo p4, "sdk_version"

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/Plugin;->getSdkVersion()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p2, p4, v0}, Lcom/chaquo/python/PyObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    .line 927
    const-string/jumbo p4, "requirements"

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/Plugin;->getRequirements()Ljava/util/List;

    move-result-object p3

    invoke-virtual {p2, p4, p3}, Lcom/chaquo/python/PyObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    .line 928
    sget-object p3, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    const-string p4, "enabled"

    invoke-virtual {p2, p4, p3}, Lcom/chaquo/python/PyObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    .line 929
    const-string/jumbo p4, "initialized"

    invoke-virtual {p2, p4, p3}, Lcom/chaquo/python/PyObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    .line 930
    const-string p3, "error_message"

    invoke-interface {p2, p3, v2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 932
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->pluginInstances:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-interface {p0, p1, p2}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-void

    .line 916
    :cond_3
    new-instance p0, Ljava/lang/Exception;

    new-instance p2, Ljava/lang/StringBuilder;

    const-string p3, "Could not find a class inheriting from BasePlugin in "

    invoke-direct {p2, p3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, ".py. Make sure your main plugin class extends BasePlugin."

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/lang/Exception;-><init>(Ljava/lang/String;)V

    throw p0

    :catchall_0
    move-exception p2

    goto :goto_2

    :catch_0
    move-exception p2

    goto :goto_1

    .line 900
    :cond_4
    :try_start_1
    new-instance p2, Ljava/lang/Exception;

    new-instance p3, Ljava/lang/StringBuilder;

    invoke-direct {p3, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p3

    invoke-direct {p2, p3}, Ljava/lang/Exception;-><init>(Ljava/lang/String;)V

    throw p2
    :try_end_1
    .catch Lcom/chaquo/python/PyException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 902
    :goto_1
    :try_start_2
    new-instance p3, Ljava/lang/Exception;

    invoke-virtual {p2}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p4

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p4

    invoke-direct {p3, p4, p2}, Ljava/lang/Exception;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw p3
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 904
    :goto_2
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->getWatchdog()Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    move-result-object p0

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionFinished(Ljava/lang/String;)V

    throw p2
.end method

.method private final disableShadowedPlugins(Ljava/lang/String;Ljava/lang/String;Ljava/util/Set;)V
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    .line 786
    invoke-interface {p3}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p3

    :cond_0
    :goto_0
    invoke-interface {p3}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_2

    invoke-interface {p3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 787
    invoke-static {v0, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_0

    .line 788
    new-instance v1, Ljava/io/File;

    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v2

    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginsDir()Ljava/io/File;

    move-result-object v2

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, ".py"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v1, v2, v3}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v1

    if-nez v1, :cond_1

    goto :goto_0

    .line 793
    :cond_1
    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Disabling plugin \'"

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "\': dependency "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, " of "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, " now owns that module name"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lorg/telegram/messenger/FileLog;->w(Ljava/lang/String;)V

    .line 794
    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->unloadPlugin(Ljava/lang/String;)V

    .line 795
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v1

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v1

    .line 45
    invoke-interface {v1}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v1

    .line 796
    new-instance v2, Ljava/lang/StringBuilder;

    const-string/jumbo v3, "plugin_enabled_"

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    const/4 v3, 0x0

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 50
    invoke-interface {v1}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 798
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v1

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v1

    invoke-virtual {v1, v0}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/plugins/Plugin;

    if-eqz v1, :cond_0

    .line 799
    new-instance v2, Ljava/lang/Exception;

    .line 800
    new-instance v3, Ljava/lang/StringBuilder;

    const-string v4, "Plugin id \'"

    invoke-direct {v3, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, "\' is now provided by "

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", a dependency of \'"

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, "\'. Rename or delete one of them."

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 799
    invoke-direct {v2, v0}, Ljava/lang/Exception;-><init>(Ljava/lang/String;)V

    .line 798
    invoke-virtual {v1, v2}, Lcom/exteragram/messenger/plugins/Plugin;->setError(Ljava/lang/Throwable;)V

    goto/16 :goto_0

    :cond_2
    return-void
.end method

.method private final evictPluginModule(Lcom/chaquo/python/PyObject;Ljava/lang/String;Ljava/io/File;)V
    .locals 5

    .line 986
    const-string p0, "get"

    filled-new-array {p2}, [Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {p1, p0, v0}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p0

    if-nez p0, :cond_0

    return-void

    .line 987
    :cond_0
    const-string v0, "__file__"

    invoke-virtual {p0, v0}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/chaquo/python/PyObject;

    const/4 v0, 0x0

    if-eqz p0, :cond_1

    invoke-virtual {p0}, Lcom/chaquo/python/PyObject;->toString()Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    :cond_1
    move-object p0, v0

    :goto_0
    if-eqz p3, :cond_2

    .line 989
    sget-object v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    new-instance v2, Ljava/io/File;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, ".py"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v2, p3, v3}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-virtual {v2}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p3

    invoke-static {v1, p3}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$canonicalPathOrNull(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p3

    goto :goto_1

    :cond_2
    move-object p3, v0

    :goto_1
    if-eqz p3, :cond_4

    .line 993
    sget-object v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-static {v1, p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$canonicalPathOrNull(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1, p3}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p3

    if-nez p3, :cond_3

    goto :goto_2

    .line 997
    :cond_3
    const-string/jumbo p0, "pop"

    filled-new-array {p2, v0}, [Ljava/lang/Object;

    move-result-object p2

    invoke-virtual {p1, p0, p2}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    return-void

    :cond_4
    :goto_2
    if-nez p0, :cond_5

    .line 994
    const-string/jumbo p0, "the Python runtime"

    :cond_5
    new-instance p1, Ljava/lang/StringBuilder;

    const-string p3, "Keeping module \'"

    invoke-direct {p1, p3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p2, "\' loaded: it belongs to "

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ", not to a plugin"

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->w(Ljava/lang/String;)V

    return-void
.end method

.method private final executeHook(Lcom/chaquo/python/PyObject;Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$PyMethodCaller;Lorg/telegram/messenger/Utilities$Callback;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;
    .locals 5
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Lcom/chaquo/python/PyObject;",
            "TT;",
            "Ljava/lang/Class<",
            "TT;>;",
            "Ljava/lang/String;",
            "Lcom/exteragram/messenger/plugins/PythonPluginsEngine$PyMethodCaller<",
            "TT;>;",
            "Lorg/telegram/messenger/Utilities$Callback<",
            "Ljava/lang/Throwable;",
            ">;)",
            "Lcom/exteragram/messenger/plugins/PluginsController$HookResult<",
            "TT;>;"
        }
    .end annotation

    const/4 p0, 0x0

    if-eqz p1, :cond_4

    .line 1713
    :try_start_0
    invoke-interface {p5, p1, p2}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$PyMethodCaller;->call(Lcom/chaquo/python/PyObject;Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz p1, :cond_4

    .line 1718
    :try_start_1
    const-string/jumbo p5, "strategy"

    .line 1719
    const-string v0, "DEFAULT"

    .line 1716
    invoke-static {p1, p5, v0}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getString(Lcom/chaquo/python/PyObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p5

    const/4 v0, 0x1

    const/4 v1, 0x2

    const/4 v2, 0x0

    if-eqz p5, :cond_0

    .line 1721
    const-string v3, "CANCEL"

    invoke-static {p5, v3, p0, v1, v2}, Lkotlin/text/StringsKt;->endsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_0

    .line 1722
    new-instance p3, Lcom/exteragram/messenger/plugins/PluginsController$HookResult;

    invoke-direct {p3, v2, v0, p0}, Lcom/exteragram/messenger/plugins/PluginsController$HookResult;-><init>(Ljava/lang/Object;ZZ)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    .line 1744
    :try_start_2
    invoke-static {p1}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->closeSafely(Lcom/chaquo/python/PyObject;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    return-object p3

    :catchall_0
    move-exception p1

    goto :goto_2

    :catchall_1
    move-exception p3

    goto :goto_1

    :cond_0
    if-eqz p5, :cond_3

    .line 1723
    :try_start_3
    const-string v3, "MODIFY"

    invoke-static {p5, v3, p0, v1, v2}, Lkotlin/text/StringsKt;->endsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v3
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    const-string v4, "MODIFY_FINAL"

    if-nez v3, :cond_1

    :try_start_4
    invoke-static {p5, v4, p0, v1, v2}, Lkotlin/text/StringsKt;->endsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_3

    .line 1727
    :cond_1
    invoke-virtual {p1, p4}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p4

    check-cast p4, Lcom/chaquo/python/PyObject;
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    if-eqz p4, :cond_2

    .line 1730
    :try_start_5
    invoke-virtual {p4, p3}, Lcom/chaquo/python/PyObject;->toJava(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p2
    :try_end_5
    .catch Ljava/lang/ClassCastException; {:try_start_5 .. :try_end_5} :catch_0
    .catchall {:try_start_5 .. :try_end_5} :catchall_1

    goto :goto_0

    :catch_0
    move-exception p3

    .line 1732
    :try_start_6
    invoke-interface {p6, p3}, Lorg/telegram/messenger/Utilities$Callback;->run(Ljava/lang/Object;)V

    .line 1735
    :cond_2
    :goto_0
    invoke-static {p5, v4, p0, v1, v2}, Lkotlin/text/StringsKt;->endsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p3

    if-eqz p3, :cond_3

    .line 1736
    new-instance p3, Lcom/exteragram/messenger/plugins/PluginsController$HookResult;

    invoke-direct {p3, p2, p0, v0}, Lcom/exteragram/messenger/plugins/PluginsController$HookResult;-><init>(Ljava/lang/Object;ZZ)V
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_1

    .line 1744
    :try_start_7
    invoke-static {p1}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->closeSafely(Lcom/chaquo/python/PyObject;)V

    return-object p3

    :cond_3
    invoke-static {p1}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->closeSafely(Lcom/chaquo/python/PyObject;)V

    goto :goto_3

    :goto_1
    invoke-static {p1}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->closeSafely(Lcom/chaquo/python/PyObject;)V

    throw p3
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_0

    .line 1748
    :goto_2
    invoke-interface {p6, p1}, Lorg/telegram/messenger/Utilities$Callback;->run(Ljava/lang/Object;)V

    .line 1751
    :cond_4
    :goto_3
    new-instance p1, Lcom/exteragram/messenger/plugins/PluginsController$HookResult;

    invoke-direct {p1, p2, p0, p0}, Lcom/exteragram/messenger/plugins/PluginsController$HookResult;-><init>(Ljava/lang/Object;ZZ)V

    return-object p1
.end method

.method private final executeHook(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$PyMethodCaller;Lorg/telegram/messenger/Utilities$Callback;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/lang/String;",
            "TT;",
            "Ljava/lang/Class<",
            "TT;>;",
            "Ljava/lang/String;",
            "Lcom/exteragram/messenger/plugins/PythonPluginsEngine$PyMethodCaller<",
            "TT;>;",
            "Lorg/telegram/messenger/Utilities$Callback<",
            "Ljava/lang/Throwable;",
            ">;)",
            "Lcom/exteragram/messenger/plugins/PluginsController$HookResult<",
            "TT;>;"
        }
    .end annotation

    .line 1763
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->pluginInstances:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    move-object v1, p1

    check-cast v1, Lcom/chaquo/python/PyObject;

    move-object v0, p0

    move-object v2, p2

    move-object v3, p3

    move-object v4, p4

    move-object v5, p5

    move-object v6, p6

    .line 1762
    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->executeHook(Lcom/chaquo/python/PyObject;Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$PyMethodCaller;Lorg/telegram/messenger/Utilities$Callback;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;

    move-result-object p0

    return-object p0
.end method

.method private final findModuleNameOwner(Ljava/lang/String;)Ljava/lang/String;
    .locals 8

    .line 1274
    const-string v0, "another Python module"

    sget-object v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    sget-object v2, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_DIR:Ljava/io/File;

    invoke-static {v1, v2}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$topLevelModuleNames(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/io/File;)Ljava/util/Set;

    move-result-object v1

    invoke-interface {v1, p1}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 1275
    const-string/jumbo p0, "the plugins SDK"

    return-object p0

    .line 1278
    :cond_0
    sget-object v1, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/pip/PipController;->activeLibraryPaths()Ljava/util/Set;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_1
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_2

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    .line 1279
    new-instance v3, Ljava/io/File;

    invoke-direct {v3, v2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 1280
    sget-object v2, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-static {v2, v3}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$topLevelModuleNames(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/io/File;)Ljava/util/Set;

    move-result-object v2

    invoke-interface {v2, p1}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 1281
    invoke-virtual {v3}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object p0

    new-instance p1, Ljava/lang/StringBuilder;

    const-string/jumbo v0, "the installed dependency "

    invoke-direct {p1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 1285
    :cond_2
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPython()Lcom/chaquo/python/Python;

    move-result-object v1

    const/4 v2, 0x0

    if-nez v1, :cond_3

    return-object v2

    .line 1287
    :cond_3
    :try_start_0
    const-string/jumbo v3, "importlib.util"

    invoke-virtual {v1, v3}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object v3

    const-string v4, "find_spec"

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object v5

    invoke-virtual {v3, v4, v5}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object v3

    if-nez v3, :cond_4

    return-object v2

    .line 1289
    :cond_4
    const-string/jumbo v4, "origin"

    invoke-virtual {v3, v4}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/chaquo/python/PyObject;

    if-eqz v4, :cond_5

    invoke-virtual {v4}, Lcom/chaquo/python/PyObject;->toString()Ljava/lang/String;

    move-result-object v4

    goto :goto_0

    :catchall_0
    move-exception p0

    goto/16 :goto_3

    :catch_0
    move-exception p0

    goto/16 :goto_4

    :cond_5
    move-object v4, v2

    :goto_0
    if-eqz v4, :cond_7

    .line 1291
    sget-object v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    .line 1292
    new-instance v3, Ljava/io/File;

    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginsDir()Ljava/io/File;

    move-result-object p0

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, ".py"

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-direct {v3, p0, v5}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-virtual {v3}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p0

    .line 1291
    invoke-static {v1, p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$canonicalPathOrNull(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 1294
    invoke-static {v1, v4}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$canonicalPathOrNull(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-static {v1, p0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_6

    return-object v2

    :cond_6
    return-object v4

    .line 1297
    :cond_7
    const-string/jumbo v4, "submodule_search_locations"

    invoke-virtual {v3, v4}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/chaquo/python/PyObject;

    if-nez v3, :cond_8

    return-object v0

    .line 1298
    :cond_8
    sget-object v4, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    .line 1299
    new-instance v5, Ljava/io/File;

    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginsDir()Ljava/io/File;

    move-result-object p0

    invoke-direct {v5, p0, p1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-virtual {v5}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p0

    .line 1298
    invoke-static {v4, p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$canonicalPathOrNull(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 1301
    invoke-virtual {v1}, Lcom/chaquo/python/Python;->getBuiltins()Lcom/chaquo/python/PyObject;

    move-result-object v1

    const-string/jumbo v4, "list"

    filled-new-array {v3}, [Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v1, v4, v3}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object v1

    invoke-virtual {v1}, Lcom/chaquo/python/PyObject;->asList()Ljava/util/List;

    move-result-object v1

    check-cast v1, Ljava/lang/Iterable;

    .line 1586
    new-instance v3, Ljava/util/ArrayList;

    const/16 v4, 0xa

    invoke-static {v1, v4}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v4

    invoke-direct {v3, v4}, Ljava/util/ArrayList;-><init>(I)V

    .line 1661
    invoke-interface {v1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_1
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_9

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    .line 1662
    check-cast v4, Lcom/chaquo/python/PyObject;

    .line 1302
    invoke-virtual {v4}, Lcom/chaquo/python/PyObject;->toString()Ljava/lang/String;

    move-result-object v4

    .line 1662
    invoke-interface {v3, v4}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_1

    .line 296
    :cond_9
    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v1

    const/4 v4, 0x0

    :cond_a
    if-ge v4, v1, :cond_b

    invoke-virtual {v3, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v5

    add-int/lit8 v4, v4, 0x1

    move-object v6, v5

    check-cast v6, Ljava/lang/String;

    .line 1303
    sget-object v7, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-static {v7, v6}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$canonicalPathOrNull(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-static {v6, p0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v6

    if-nez v6, :cond_a

    goto :goto_2

    :cond_b
    move-object v5, v2

    .line 297
    :goto_2
    check-cast v5, Ljava/lang/String;
    :try_end_0
    .catch Lcom/chaquo/python/PyException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-object v5

    .line 1308
    :goto_3
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Failed to check whether plugin id \'"

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "\' is free"

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    move-object v0, v2

    goto :goto_5

    .line 1305
    :goto_4
    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p0

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Treating plugin id \'"

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "\' as taken: "

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->w(Ljava/lang/String;)V

    :goto_5
    return-object v0
.end method

.method private final getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;
    .locals 0

    .line 193
    sget-object p0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->getInstance()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p0

    return-object p0
.end method

.method private final declared-synchronized getPython()Lcom/chaquo/python/Python;
    .locals 1

    monitor-enter p0

    .line 198
    :try_start_0
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->python:Lcom/chaquo/python/Python;

    if-nez v0, :cond_0

    .line 199
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->initPython()V

    .line 200
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->python:Lcom/chaquo/python/Python;

    if-nez v0, :cond_0

    .line 201
    const-string v0, "Python initialization failed, unable to proceed."

    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 202
    monitor-exit p0

    const/4 p0, 0x0

    return-object p0

    :catchall_0
    move-exception v0

    goto :goto_0

    .line 205
    :cond_0
    :try_start_1
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->python:Lcom/chaquo/python/Python;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    monitor-exit p0

    return-object v0

    :goto_0
    :try_start_2
    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw v0
.end method

.method private final initPython()V
    .locals 2

    .line 210
    :try_start_0
    invoke-static {}, Lcom/chaquo/python/Python;->isStarted()Z

    move-result v0

    if-nez v0, :cond_0

    .line 211
    new-instance v0, Lcom/chaquo/python/android/AndroidPlatform;

    sget-object v1, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-direct {v0, v1}, Lcom/chaquo/python/android/AndroidPlatform;-><init>(Landroid/content/Context;)V

    invoke-static {v0}, Lcom/chaquo/python/Python;->start(Lcom/chaquo/python/Python$Platform;)V

    .line 213
    :cond_0
    invoke-static {}, Lcom/chaquo/python/Python;->getInstance()Lcom/chaquo/python/Python;

    move-result-object v0

    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->python:Lcom/chaquo/python/Python;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 215
    const-string v0, "Failed to initialize Python"

    invoke-static {v0, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method private final initSdk()Z
    .locals 15

    const/4 v1, 0x0

    .line 278
    sput-boolean v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->sdkInitialized:Z

    .line 279
    iget-object v2, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->python:Lcom/chaquo/python/Python;

    if-nez v2, :cond_0

    return v1

    .line 281
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_DIR:Ljava/io/File;

    const-string v3, "Required value was null."

    if-nez v0, :cond_2

    .line 282
    new-instance v0, Ljava/io/File;

    .line 283
    new-instance v4, Ljava/io/File;

    invoke-static {}, Lorg/telegram/messenger/ApplicationLoader;->getFilesDirFixed()Ljava/io/File;

    move-result-object v5

    const-string v6, "chaquopy"

    invoke-direct {v4, v5, v6}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 284
    const-string/jumbo v5, "plugins-sdk"

    .line 282
    invoke-direct {v0, v4, v5}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    sput-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_DIR:Ljava/io/File;

    .line 286
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v0

    if-nez v0, :cond_2

    .line 287
    sget-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_DIR:Ljava/io/File;

    if-eqz v0, :cond_1

    invoke-static {v0}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object v0

    invoke-virtual {v0}, Lorg/simplifiles/files/SimpliDirectory;->create()Lorg/simplifiles/files/SimpliDirectory;

    goto :goto_0

    :cond_1
    invoke-static {v3}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return v1

    .line 291
    :cond_2
    :goto_0
    sget-object v4, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_DIR:Ljava/io/File;

    if-eqz v4, :cond_13

    .line 292
    sget-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->requestSdkFromApkFile()Ljava/io/File;

    move-result-object v3

    .line 293
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->getPythonSdkUpdateFile()Ljava/io/File;

    move-result-object v5

    .line 294
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->getPythonCurrentSdkFile()Ljava/io/File;

    move-result-object v6

    .line 296
    invoke-virtual {v3}, Ljava/io/File;->exists()Z

    move-result v7

    if-eqz v7, :cond_3

    .line 298
    invoke-static {v4}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object v8

    invoke-virtual {v8}, Lorg/simplifiles/files/SimpliDirectory;->clean()Lorg/simplifiles/files/SimpliDirectory;

    .line 299
    sget-object v8, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-static {v8, v3}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$deleteFileIfExists(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/io/File;)V

    :cond_3
    const/4 v3, 0x1

    if-nez v7, :cond_5

    .line 302
    invoke-virtual {v5}, Ljava/io/File;->exists()Z

    move-result v8

    if-eqz v8, :cond_5

    .line 303
    invoke-virtual {v0, v5}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->isPendingUpdateVerified(Ljava/io/File;)Z

    move-result v8

    if-nez v8, :cond_4

    .line 304
    const-string v5, "Discarding Python SDK update that failed signature verification"

    invoke-static {v5}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;)V

    .line 305
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->deleteSdkUpdateFile()V

    goto :goto_1

    .line 308
    :cond_4
    :try_start_0
    invoke-static {v5}, Lorg/simplifiles/SimpliFiles;->file(Ljava/io/File;)Lorg/simplifiles/files/SimpliFile;

    move-result-object v0

    sget-object v5, Lorg/simplifiles/files/OverwritePolicy;->REPLACE:Lorg/simplifiles/files/OverwritePolicy;

    invoke-virtual {v0, v6, v5}, Lorg/simplifiles/files/SimpliFile;->copyTo(Ljava/io/File;Lorg/simplifiles/files/OverwritePolicy;)Lorg/simplifiles/files/SimpliFile;

    .line 309
    invoke-direct {p0, v6, v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->installSdkArchive(Ljava/io/File;Z)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_1

    :catch_0
    move-exception v0

    .line 311
    const-string v5, "Failed to install updated Python SDK archive"

    invoke-static {v5, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    move v7, v3

    .line 317
    :cond_5
    :goto_1
    new-instance v0, Ljava/io/File;

    const-string/jumbo v5, "v.txt"

    invoke-direct {v0, v4, v5}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 318
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v5

    const/4 v8, 0x0

    if-eqz v5, :cond_9

    .line 319
    invoke-static {v0}, Lorg/simplifiles/SimpliFiles;->file(Ljava/io/File;)Lorg/simplifiles/files/SimpliFile;

    move-result-object v9

    const/4 v13, 0x2

    const/4 v14, 0x0

    const-wide/32 v10, 0x10000

    const/4 v12, 0x0

    invoke-static/range {v9 .. v14}, Lorg/simplifiles/files/SimpliFile;->readText$default(Lorg/simplifiles/files/SimpliFile;JLjava/nio/charset/Charset;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    .line 320
    const-string/jumbo v5, "|1"

    const/4 v9, 0x2

    invoke-static {v0, v5, v1, v9, v8}, Lkotlin/text/StringsKt;->endsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v5

    const/16 v10, 0x7c

    .line 321
    invoke-static {v0, v10, v8, v9, v8}, Lkotlin/text/StringsKt;->substringBefore$default(Ljava/lang/String;CLjava/lang/String;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    .line 324
    :try_start_1
    sget-object v9, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {v9}, Landroid/content/Context;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v9

    const-string/jumbo v10, "plugins_pysdk/v.txt"

    invoke-virtual {v9, v10}, Landroid/content/res/AssetManager;->open(Ljava/lang/String;)Ljava/io/InputStream;

    move-result-object v9
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_2

    .line 326
    :try_start_2
    new-instance v10, Ljava/io/BufferedReader;

    .line 327
    new-instance v11, Ljava/io/InputStreamReader;

    .line 329
    sget-object v12, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    .line 327
    invoke-direct {v11, v9, v12}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V

    .line 326
    invoke-direct {v10, v11}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    .line 332
    :try_start_3
    new-instance v11, Ljava/lang/StringBuilder;

    invoke-direct {v11}, Ljava/lang/StringBuilder;-><init>()V

    .line 334
    :goto_2
    invoke-virtual {v10}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v12

    if-nez v12, :cond_8

    .line 337
    invoke-virtual {v11}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v11

    invoke-static {v11}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object v11

    invoke-virtual {v11}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v11
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    if-eqz v5, :cond_6

    .line 340
    :try_start_4
    sget-object v5, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;

    invoke-virtual {v5}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->isSdkFromCurrentApk()Z

    move-result v5

    if-nez v5, :cond_6

    const-string v5, ">="

    goto :goto_3

    :catchall_0
    move-exception v0

    move-object p0, v0

    goto :goto_7

    :catch_1
    move-exception v0

    goto :goto_5

    :cond_6
    const-string v5, ">"

    .line 339
    :goto_3
    invoke-static {v5, v11, v0}, Lcom/exteragram/messenger/utils/AppUtils;->compareVersions(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z

    move-result v0
    :try_end_4
    .catch Ljava/lang/NumberFormatException; {:try_start_4 .. :try_end_4} :catch_1
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    if-eqz v0, :cond_7

    :goto_4
    move v7, v3

    goto :goto_6

    .line 348
    :goto_5
    :try_start_5
    const-string v5, "Invalid Python SDK version file. Restoring SDK from APK."

    invoke-static {v5, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_4

    .line 351
    :cond_7
    :goto_6
    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    .line 331
    :try_start_6
    invoke-static {v10, v8}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_1

    .line 325
    :try_start_7
    invoke-static {v9, v8}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    :try_end_7
    .catch Ljava/io/IOException; {:try_start_7 .. :try_end_7} :catch_2

    goto :goto_a

    :catch_2
    move-exception v0

    move-object p0, v0

    goto :goto_9

    :catchall_1
    move-exception v0

    move-object p0, v0

    goto :goto_8

    .line 335
    :cond_8
    :try_start_8
    invoke-virtual {v11, v12}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v12, 0xa

    invoke-virtual {v11, v12}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;
    :try_end_8
    .catchall {:try_start_8 .. :try_end_8} :catchall_0

    goto :goto_2

    .line 331
    :goto_7
    :try_start_9
    throw p0
    :try_end_9
    .catchall {:try_start_9 .. :try_end_9} :catchall_2

    :catchall_2
    move-exception v0

    :try_start_a
    invoke-static {v10, p0}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    throw v0
    :try_end_a
    .catchall {:try_start_a .. :try_end_a} :catchall_1

    .line 325
    :goto_8
    :try_start_b
    throw p0
    :try_end_b
    .catchall {:try_start_b .. :try_end_b} :catchall_3

    :catchall_3
    move-exception v0

    :try_start_c
    invoke-static {v9, p0}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    throw v0
    :try_end_c
    .catch Ljava/io/IOException; {:try_start_c .. :try_end_c} :catch_2

    .line 355
    :goto_9
    const-string v0, "Failed to read Python SDK version (v.txt) from APK assets"

    .line 354
    invoke-static {v0, p0}, Lorg/mvel2/util/Make$Map$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Throwable;)V

    return v1

    :cond_9
    :goto_a
    if-nez v7, :cond_a

    .line 361
    sget-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-static {v0, v4}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$isSdkDirValid(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/io/File;)Z

    move-result v0

    if-nez v0, :cond_c

    :cond_a
    if-nez v7, :cond_b

    .line 363
    const-string v0, "Python SDK directory is missing required files. Restoring SDK from APK."

    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->w(Ljava/lang/String;)V

    .line 365
    :cond_b
    invoke-static {v4}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object v0

    invoke-virtual {v0}, Lorg/simplifiles/files/SimpliDirectory;->clean()Lorg/simplifiles/files/SimpliDirectory;

    .line 367
    :try_start_d
    sget-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->sdkFromApk()Ljava/io/InputStream;

    move-result-object v10
    :try_end_d
    .catch Ljava/io/IOException; {:try_start_d .. :try_end_d} :catch_4

    .line 368
    :try_start_e
    invoke-static {v6}, Lorg/simplifiles/SimpliFiles;->file(Ljava/io/File;)Lorg/simplifiles/files/SimpliFile;

    move-result-object v9

    const/4 v13, 0x2

    const/4 v14, 0x0

    const-wide/16 v11, 0x0

    invoke-static/range {v9 .. v14}, Lorg/simplifiles/files/SimpliFile;->writeFromAtomic$default(Lorg/simplifiles/files/SimpliFile;Ljava/io/InputStream;JILjava/lang/Object;)Lorg/simplifiles/files/SimpliFile;
    :try_end_e
    .catchall {:try_start_e .. :try_end_e} :catchall_6

    .line 367
    :try_start_f
    invoke-static {v10, v8}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    .line 370
    invoke-direct {p0, v6, v3}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->installSdkArchive(Ljava/io/File;Z)V
    :try_end_f
    .catch Ljava/io/IOException; {:try_start_f .. :try_end_f} :catch_4

    .line 375
    :cond_c
    sget-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->deleteSdkUpdateFile()V

    .line 377
    const-string/jumbo v0, "sys"

    invoke-virtual {v2, v0}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object v0

    .line 378
    const-string/jumbo v5, "path"

    invoke-virtual {v0, v5}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/chaquo/python/PyObject;

    if-eqz v0, :cond_d

    invoke-virtual {v4}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v4

    filled-new-array {v4}, [Ljava/lang/Object;

    move-result-object v4

    const-string v5, "append"

    invoke-virtual {v0, v5, v4}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    .line 381
    :cond_d
    :try_start_10
    const-string v0, "_sdk_version"

    invoke-virtual {v2, v0}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object v0

    .line 382
    const-string v2, "__start__"

    new-array v4, v1, [Ljava/lang/Object;

    invoke-virtual {v0, v2, v4}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object v2

    if-eqz v2, :cond_e

    invoke-virtual {v2}, Lcom/chaquo/python/PyObject;->toBoolean()Z

    move-result v2

    if-ne v2, v3, :cond_e

    goto :goto_b

    :cond_e
    move v3, v1

    goto :goto_b

    :catchall_4
    move-exception v0

    move-object p0, v0

    goto :goto_e

    :goto_b
    sput-boolean v3, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->sdkInitialized:Z

    .line 383
    const-string v2, "__version__"

    invoke-virtual {v0, v2}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/chaquo/python/PyObject;

    if-eqz v2, :cond_f

    .line 384
    const-class v3, Ljava/lang/String;

    invoke-virtual {v2, v3}, Lcom/chaquo/python/PyObject;->toJava(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    if-nez v2, :cond_10

    :cond_f
    sget-object v2, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_VERSION:Ljava/lang/String;

    :cond_10
    sput-object v2, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_VERSION:Ljava/lang/String;

    .line 385
    const-string v2, "__beta__"

    invoke-virtual {v0, v2}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/chaquo/python/PyObject;

    if-eqz v0, :cond_11

    .line 386
    sget-object v2, Ljava/lang/Boolean;->TYPE:Ljava/lang/Class;

    invoke-virtual {v0, v2}, Lcom/chaquo/python/PyObject;->toJava(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/Boolean;

    if-eqz v0, :cond_11

    invoke-virtual {v0}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v0

    goto :goto_c

    :cond_11
    sget-boolean v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_BETA:Z

    :goto_c
    sput-boolean v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_BETA:Z
    :try_end_10
    .catchall {:try_start_10 .. :try_end_10} :catchall_4

    .line 397
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->basePluginClass:Lcom/chaquo/python/PyObject;

    if-nez v0, :cond_12

    sget-boolean v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->sdkInitialized:Z

    if-eqz v0, :cond_12

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsSafeMode()Z

    move-result v0

    if-nez v0, :cond_12

    .line 399
    :try_start_11
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->requireBasePluginClass()Lcom/chaquo/python/PyObject;
    :try_end_11
    .catch Ljava/lang/Exception; {:try_start_11 .. :try_end_11} :catch_3

    goto :goto_d

    :catch_3
    move-exception v0

    move-object p0, v0

    .line 401
    const-string v0, "Failed to load BasePlugin class"

    invoke-static {v0, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 405
    :cond_12
    :goto_d
    sget-boolean p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->sdkInitialized:Z

    return p0

    .line 388
    :goto_e
    const-string v0, "Failed to initialize Python SDK bootstrap"

    invoke-static {v0, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 390
    :try_start_12
    sget-object p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->restoreSdkFromApk()V
    :try_end_12
    .catchall {:try_start_12 .. :try_end_12} :catchall_5

    goto :goto_f

    :catchall_5
    move-exception v0

    move-object p0, v0

    .line 392
    const-string v0, "Failed to schedule Python SDK restore from APK"

    invoke-static {v0, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    :goto_f
    return v1

    :catch_4
    move-exception v0

    move-object p0, v0

    goto :goto_10

    :catchall_6
    move-exception v0

    move-object p0, v0

    .line 367
    :try_start_13
    throw p0
    :try_end_13
    .catchall {:try_start_13 .. :try_end_13} :catchall_7

    :catchall_7
    move-exception v0

    :try_start_14
    invoke-static {v10, p0}, Lkotlin/io/CloseableKt;->closeFinally(Ljava/io/Closeable;Ljava/lang/Throwable;)V

    throw v0
    :try_end_14
    .catch Ljava/io/IOException; {:try_start_14 .. :try_end_14} :catch_4

    .line 372
    :goto_10
    const-string v0, "Failed to install Python SDK from APK"

    invoke-static {v0, p0}, Lorg/mvel2/util/Make$Map$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Throwable;)V

    return v1

    .line 291
    :cond_13
    invoke-static {v3}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return v1
.end method

.method private final installPluginDependencies(Ljava/lang/String;Lcom/exteragram/messenger/plugins/Plugin;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)V
    .locals 17

    move-object/from16 v0, p0

    move-object/from16 v1, p1

    .line 749
    invoke-virtual/range {p2 .. p2}, Lcom/exteragram/messenger/plugins/Plugin;->getRequirements()Ljava/util/List;

    move-result-object v2

    .line 750
    move-object v3, v2

    check-cast v3, Ljava/util/Collection;

    if-eqz v3, :cond_6

    invoke-interface {v3}, Ljava/util/Collection;->isEmpty()Z

    move-result v3

    if-eqz v3, :cond_0

    goto/16 :goto_5

    .line 753
    :cond_0
    sget-object v3, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    move-object/from16 v4, p3

    invoke-virtual {v3, v2, v1, v4}, Lcom/exteragram/messenger/plugins/pip/PipController;->installDependencies(Ljava/util/List;Ljava/lang/String;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)Ljava/util/List;

    move-result-object v2

    .line 755
    move-object v3, v2

    check-cast v3, Ljava/lang/Iterable;

    .line 1300
    new-instance v4, Ljava/util/LinkedHashMap;

    const/16 v5, 0xa

    invoke-static {v3, v5}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v5

    invoke-static {v5}, Lkotlin/collections/MapsKt;->mapCapacity(I)I

    move-result v5

    const/16 v6, 0x10

    invoke-static {v5, v6}, Lkotlin/ranges/RangesKt;->coerceAtLeast(II)I

    move-result v5

    invoke-direct {v4, v5}, Ljava/util/LinkedHashMap;-><init>(I)V

    .line 1315
    invoke-interface {v3}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :goto_0
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_1

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    .line 1316
    move-object v6, v5

    check-cast v6, Ljava/lang/String;

    .line 755
    sget-object v7, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    new-instance v8, Ljava/io/File;

    invoke-direct {v8, v6}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-static {v7, v8}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$topLevelModuleNames(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/io/File;)Ljava/util/Set;

    move-result-object v6

    .line 1316
    invoke-interface {v4, v5, v6}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_0

    .line 756
    :cond_1
    sget-object v3, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    sget-object v5, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_DIR:Ljava/io/File;

    invoke-static {v3, v5}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$topLevelModuleNames(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/io/File;)Ljava/util/Set;

    move-result-object v3

    .line 757
    invoke-interface {v4}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v5

    invoke-interface {v5}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v5

    :goto_1
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    move-result v6

    if-eqz v6, :cond_3

    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/util/Map$Entry;

    invoke-interface {v6}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Ljava/lang/String;

    invoke-interface {v6}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/util/Set;

    .line 758
    invoke-static {v6, v3}, Lkotlin/collections/CollectionsKt;->intersect(Ljava/lang/Iterable;Ljava/lang/Iterable;)Ljava/util/Set;

    move-result-object v8

    .line 759
    invoke-interface {v8}, Ljava/util/Collection;->isEmpty()Z

    move-result v6

    if-eqz v6, :cond_2

    goto :goto_1

    .line 760
    :cond_2
    new-instance v0, Ljava/lang/Exception;

    .line 761
    new-instance v2, Ljava/io/File;

    invoke-direct {v2, v7}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v2

    const/16 v15, 0x3f

    const/16 v16, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v12, 0x0

    const/4 v13, 0x0

    const/4 v14, 0x0

    invoke-static/range {v8 .. v16}, Lkotlin/collections/CollectionsKt;->joinToString$default(Ljava/lang/Iterable;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;Lkotlin/jvm/functions/Function1;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    new-instance v4, Ljava/lang/StringBuilder;

    const-string v5, "Dependency "

    invoke-direct {v4, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, " of "

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, " provides "

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", which would replace the plugins SDK module of the same name."

    invoke-virtual {v4, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 760
    invoke-direct {v0, v1}, Ljava/lang/Exception;-><init>(Ljava/lang/String;)V

    throw v0

    .line 766
    :cond_3
    invoke-interface {v4}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v3

    invoke-interface {v3}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v3

    :goto_2
    invoke-interface {v3}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_4

    invoke-interface {v3}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/util/Map$Entry;

    invoke-interface {v4}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/String;

    invoke-interface {v4}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/util/Set;

    .line 767
    new-instance v6, Ljava/io/File;

    invoke-direct {v6, v5}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {v6}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v5

    invoke-direct {v0, v1, v5, v4}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->disableShadowedPlugins(Ljava/lang/String;Ljava/lang/String;Ljava/util/Set;)V

    goto :goto_2

    .line 770
    :cond_4
    invoke-direct {v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPython()Lcom/chaquo/python/Python;

    move-result-object v1

    if-eqz v1, :cond_5

    const-string/jumbo v3, "sys"

    invoke-virtual {v1, v3}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object v1

    if-eqz v1, :cond_5

    const-string/jumbo v3, "path"

    invoke-virtual {v1, v3}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/chaquo/python/PyObject;

    goto :goto_3

    :cond_5
    const/4 v1, 0x0

    :goto_3
    if-eqz v1, :cond_6

    .line 772
    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v3

    add-int/lit8 v3, v3, -0x1

    :goto_4
    const/4 v4, -0x1

    if-ge v4, v3, :cond_6

    .line 773
    invoke-interface {v2, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    .line 774
    invoke-direct {v0, v1, v4}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->removeFromSysPath(Lcom/chaquo/python/PyObject;Ljava/lang/String;)V

    const/4 v5, 0x0

    .line 775
    invoke-static {v5}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v5

    filled-new-array {v5, v4}, [Ljava/lang/Object;

    move-result-object v5

    const-string/jumbo v6, "insert"

    invoke-virtual {v1, v6, v5}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    .line 776
    iget-object v5, v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->dependencyPaths:Ljava/util/concurrent/ConcurrentHashMap$KeySetView;

    invoke-virtual {v5, v4}, Ljava/util/concurrent/ConcurrentHashMap$KeySetView;->add(Ljava/lang/Object;)Z

    add-int/lit8 v3, v3, -0x1

    goto :goto_4

    :cond_6
    :goto_5
    return-void
.end method

.method private final installSdkArchive(Ljava/io/File;Z)V
    .locals 3

    .line 250
    sget-object p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_DIR:Ljava/io/File;

    if-eqz p0, :cond_5

    .line 251
    new-instance v0, Ljava/io/File;

    invoke-virtual {p0}, Ljava/io/File;->getParentFile()Ljava/io/File;

    move-result-object v1

    const-string/jumbo v2, "plugins-sdk.tmp"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 254
    :try_start_0
    invoke-static {p1}, Lorg/simplifiles/SimpliFiles;->archive(Ljava/io/File;)Lorg/simplifiles/archive/ArchiveSource;

    move-result-object p1

    .line 255
    sget-object v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_ARCHIVE_POLICY:Lorg/simplifiles/archive/security/SecurityPolicy;

    invoke-virtual {p1, v1}, Lorg/simplifiles/archive/ArchiveSource;->withPolicy(Lorg/simplifiles/archive/security/SecurityPolicy;)Lorg/simplifiles/archive/ArchiveSource;

    move-result-object p1

    .line 256
    sget-object v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_ARCHIVE_OPTIONS:Lorg/simplifiles/archive/ArchiveExtractionOptions;

    invoke-virtual {p1, v0, v1}, Lorg/simplifiles/archive/ArchiveSource;->extractToDirectory(Ljava/io/File;Lorg/simplifiles/archive/ArchiveExtractionOptions;)Lorg/simplifiles/files/SimpliDirectory;

    .line 257
    sget-object p1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-static {p1, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$isSdkDirValid(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/io/File;)Z

    move-result p1

    if-eqz p1, :cond_2

    .line 261
    invoke-static {v0}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object p1

    sget-object v1, Lorg/simplifiles/files/OverwritePolicy;->REPLACE:Lorg/simplifiles/files/OverwritePolicy;

    invoke-virtual {p1, p0, v1}, Lorg/simplifiles/files/SimpliDirectory;->moveTo(Ljava/io/File;Lorg/simplifiles/files/OverwritePolicy;)Lorg/simplifiles/files/SimpliDirectory;

    .line 262
    sget-object p1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;

    invoke-virtual {p1, p2}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;->setBuildFromApk(Z)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 264
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result p1

    if-eqz p1, :cond_0

    .line 265
    :try_start_1
    sget-object p1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 266
    invoke-static {v0}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object p1

    invoke-virtual {p1}, Lorg/simplifiles/files/SimpliDirectory;->deleteRecursively()Z

    move-result p1

    invoke-static {p1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p1

    .line 265
    invoke-static {p1}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception p1

    sget-object p2, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {p1}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p1

    invoke-static {p1}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    .line 267
    :goto_0
    invoke-static {p1}, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 268
    invoke-static {p1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 271
    :cond_0
    invoke-virtual {p0}, Ljava/io/File;->exists()Z

    move-result p1

    if-nez p1, :cond_1

    .line 272
    invoke-static {p0}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object p0

    invoke-virtual {p0}, Lorg/simplifiles/files/SimpliDirectory;->create()Lorg/simplifiles/files/SimpliDirectory;

    :cond_1
    return-void

    :catchall_1
    move-exception p1

    goto :goto_1

    .line 258
    :cond_2
    :try_start_2
    new-instance p1, Ljava/io/IOException;

    const-string p2, "Python SDK archive unpacked with missing required files"

    invoke-direct {p1, p2}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw p1
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    .line 264
    :goto_1
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result p2

    if-eqz p2, :cond_3

    .line 265
    :try_start_3
    sget-object p2, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    .line 266
    invoke-static {v0}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object p2

    invoke-virtual {p2}, Lorg/simplifiles/files/SimpliDirectory;->deleteRecursively()Z

    move-result p2

    invoke-static {p2}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p2

    .line 265
    invoke-static {p2}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_2

    goto :goto_2

    :catchall_2
    move-exception p2

    sget-object v0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {p2}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p2

    invoke-static {p2}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    .line 267
    :goto_2
    invoke-static {p2}, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;

    move-result-object p2

    if-eqz p2, :cond_3

    .line 268
    invoke-static {p2}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 271
    :cond_3
    invoke-virtual {p0}, Ljava/io/File;->exists()Z

    move-result p2

    if-nez p2, :cond_4

    .line 272
    invoke-static {p0}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object p0

    invoke-virtual {p0}, Lorg/simplifiles/files/SimpliDirectory;->create()Lorg/simplifiles/files/SimpliDirectory;

    :cond_4
    throw p1

    .line 250
    :cond_5
    const-string p0, "Required value was null."

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method

.method private final pruneDependencyPaths()V
    .locals 11

    .line 813
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->dependencyPaths:Ljava/util/concurrent/ConcurrentHashMap$KeySetView;

    invoke-virtual {v0}, Ljava/util/concurrent/ConcurrentHashMap$KeySetView;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    goto/16 :goto_6

    .line 816
    :cond_0
    sget-object v0, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/pip/PipController;->activeLibraryPaths()Ljava/util/Set;

    move-result-object v0

    .line 817
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->dependencyPaths:Ljava/util/concurrent/ConcurrentHashMap$KeySetView;

    .line 777
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    .line 873
    invoke-interface {v1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_1
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_2

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    move-object v4, v3

    check-cast v4, Ljava/lang/String;

    .line 817
    invoke-interface {v0, v4}, Ljava/util/Set;->contains(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_1

    .line 873
    invoke-interface {v2, v3}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 818
    :cond_2
    invoke-interface {v2}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_3

    goto/16 :goto_6

    .line 823
    :cond_3
    :try_start_0
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->python:Lcom/chaquo/python/Python;

    const/4 v1, 0x0

    if-eqz v0, :cond_4

    const-string/jumbo v3, "sys"

    invoke-virtual {v0, v3}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object v0

    goto :goto_1

    :cond_4
    move-object v0, v1

    :goto_1
    if-eqz v0, :cond_5

    .line 824
    const-string/jumbo v3, "path"

    invoke-virtual {v0, v3}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/chaquo/python/PyObject;

    goto :goto_2

    :cond_5
    move-object v3, v1

    :goto_2
    if-eqz v0, :cond_6

    .line 825
    const-string/jumbo v4, "modules"

    invoke-virtual {v0, v4}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/chaquo/python/PyObject;

    goto :goto_3

    :cond_6
    move-object v4, v1

    :goto_3
    if-eqz v0, :cond_7

    .line 826
    const-string/jumbo v5, "path_importer_cache"

    invoke-virtual {v0, v5}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/chaquo/python/PyObject;

    goto :goto_4

    :cond_7
    move-object v0, v1

    .line 827
    :goto_4
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v5

    const/4 v6, 0x0

    move v7, v6

    :goto_5
    if-ge v7, v5, :cond_b

    invoke-virtual {v2, v7}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v8

    add-int/lit8 v7, v7, 0x1

    check-cast v8, Ljava/lang/String;

    if-eqz v3, :cond_8

    .line 829
    invoke-direct {p0, v3, v8}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->removeFromSysPath(Lcom/chaquo/python/PyObject;Ljava/lang/String;)V

    :cond_8
    if-eqz v0, :cond_9

    .line 831
    const-string/jumbo v9, "pop"

    filled-new-array {v8, v1}, [Ljava/lang/Object;

    move-result-object v10

    invoke-virtual {v0, v9, v10}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    :cond_9
    if-eqz v4, :cond_a

    .line 833
    invoke-direct {p0, v4, v8}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->removeModulesUnderPath(Lcom/chaquo/python/PyObject;Ljava/lang/String;)V

    .line 835
    :cond_a
    iget-object v9, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->dependencyPaths:Ljava/util/concurrent/ConcurrentHashMap$KeySetView;

    invoke-virtual {v9, v8}, Ljava/util/concurrent/ConcurrentHashMap$KeySetView;->remove(Ljava/lang/Object;)Z

    goto :goto_5

    .line 837
    :cond_b
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->python:Lcom/chaquo/python/Python;

    if-eqz p0, :cond_c

    const-string/jumbo v0, "importlib"

    invoke-virtual {p0, v0}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object p0

    if-eqz p0, :cond_c

    const-string/jumbo v0, "invalidate_caches"

    new-array v1, v6, [Ljava/lang/Object;

    invoke-virtual {p0, v0, v1}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    :try_end_0
    .catch Lcom/chaquo/python/PyException; {:try_start_0 .. :try_end_0} :catch_0

    :cond_c
    :goto_6
    return-void

    :catch_0
    move-exception p0

    .line 839
    const-string v0, "Failed to drop unused dependency paths from the Python environment"

    invoke-static {v0, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method private final refreshImportCaches(Ljava/lang/String;Ljava/io/File;)V
    .locals 3

    .line 966
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->python:Lcom/chaquo/python/Python;

    if-nez v0, :cond_0

    return-void

    .line 968
    :cond_0
    :try_start_0
    const-string/jumbo v1, "sys"

    invoke-virtual {v0, v1}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object v1

    .line 969
    const-string/jumbo v2, "modules"

    invoke-virtual {v1, v2}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/chaquo/python/PyObject;

    if-eqz v2, :cond_1

    .line 971
    invoke-direct {p0, v2, p1, p2}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->evictPluginModule(Lcom/chaquo/python/PyObject;Ljava/lang/String;Ljava/io/File;)V

    goto :goto_0

    :catch_0
    move-exception p0

    goto :goto_1

    .line 974
    :cond_1
    :goto_0
    const-string/jumbo p0, "path_importer_cache"

    invoke-virtual {v1, p0}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/chaquo/python/PyObject;

    if-eqz p2, :cond_2

    if-eqz p0, :cond_2

    .line 976
    const-string/jumbo v1, "pop"

    invoke-virtual {p2}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p2

    const/4 v2, 0x0

    filled-new-array {p2, v2}, [Ljava/lang/Object;

    move-result-object p2

    invoke-virtual {p0, v1, p2}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    .line 979
    :cond_2
    const-string/jumbo p0, "importlib"

    invoke-virtual {v0, p0}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object p0

    const-string/jumbo p2, "invalidate_caches"

    const/4 v0, 0x0

    new-array v0, v0, [Ljava/lang/Object;

    invoke-virtual {p0, p2, v0}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    :try_end_0
    .catch Lcom/chaquo/python/PyException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    .line 981
    :goto_1
    new-instance p2, Ljava/lang/StringBuilder;

    const-string v0, "Failed to refresh import caches for "

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method private final removeFromSysPath(Lcom/chaquo/python/PyObject;Ljava/lang/String;)V
    .locals 1

    .line 879
    :goto_0
    const-string p0, "__contains__"

    filled-new-array {p2}, [Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {p1, p0, v0}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p0

    invoke-virtual {p0}, Lcom/chaquo/python/PyObject;->toBoolean()Z

    move-result p0

    if-eqz p0, :cond_0

    .line 880
    const-string/jumbo p0, "remove"

    filled-new-array {p2}, [Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {p1, p0, v0}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    goto :goto_0

    :cond_0
    return-void
.end method

.method private final removeModulesRecursive(Lcom/chaquo/python/PyObject;Ljava/io/File;Ljava/lang/String;)V
    .locals 6

    .line 438
    const-string/jumbo v0, "plugins-sdk."

    invoke-static {p3, v0}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 439
    const-string p3, ""

    .line 441
    :cond_0
    invoke-virtual {p2}, Ljava/io/File;->isDirectory()Z

    move-result v0

    const-string/jumbo v1, "pop"

    const-string v2, "__contains__"

    if-eqz v0, :cond_1

    invoke-virtual {p2}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v0

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    filled-new-array {v0}, [Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {p1, v2, v0}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object v0

    .line 442
    invoke-virtual {v0}, Lcom/chaquo/python/PyObject;->toBoolean()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 444
    invoke-virtual {p2}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v0

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    filled-new-array {v0}, [Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {p1, v1, v0}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    .line 447
    :cond_1
    invoke-virtual {p2}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object v0

    if-eqz v0, :cond_2

    .line 449
    invoke-static {v0}, Lkotlin/jvm/internal/ArrayIteratorKt;->iterator([Ljava/lang/Object;)Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_2

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/io/File;

    .line 450
    invoke-virtual {p2}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v4

    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, "."

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-direct {p0, p1, v3, v4}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->removeModulesRecursive(Lcom/chaquo/python/PyObject;Ljava/io/File;Ljava/lang/String;)V

    goto :goto_0

    .line 454
    :cond_2
    invoke-virtual {p2}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object p0

    const/16 v0, 0x2e

    const/4 v3, 0x2

    const/4 v4, 0x0

    invoke-static {p0, v0, v4, v3, v4}, Lkotlin/text/StringsKt;->substringBefore$default(Ljava/lang/String;CLjava/lang/String;ILjava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    .line 455
    invoke-virtual {p2}, Ljava/io/File;->isDirectory()Z

    move-result p2

    if-nez p2, :cond_3

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    filled-new-array {p2}, [Ljava/lang/Object;

    move-result-object p2

    invoke-virtual {p1, v2, p2}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p2

    .line 456
    invoke-virtual {p2}, Lcom/chaquo/python/PyObject;->toBoolean()Z

    move-result p2

    if-eqz p2, :cond_3

    .line 458
    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p2, p3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    filled-new-array {p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-virtual {p1, v1, p0}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    :cond_3
    return-void
.end method

.method private final removeModulesUnderPath(Lcom/chaquo/python/PyObject;Ljava/lang/String;)V
    .locals 9

    .line 844
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->python:Lcom/chaquo/python/Python;

    if-nez p0, :cond_0

    goto/16 :goto_6

    .line 845
    :cond_0
    sget-object v0, Ljava/io/File;->separator:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 846
    sget-object v2, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-static {v2, p2}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$canonicalPathOrNull(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_1

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    goto :goto_0

    :cond_1
    move-object v0, v1

    .line 847
    :goto_0
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    .line 848
    invoke-virtual {p0}, Lcom/chaquo/python/Python;->getBuiltins()Lcom/chaquo/python/PyObject;

    move-result-object p0

    const-string/jumbo v3, "list"

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object v4

    invoke-virtual {p0, v3, v4}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p0

    invoke-virtual {p0}, Lcom/chaquo/python/PyObject;->asList()Ljava/util/List;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :cond_2
    :goto_1
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    const/4 v4, 0x0

    if-eqz v3, :cond_a

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/chaquo/python/PyObject;

    if-eqz v3, :cond_2

    .line 849
    invoke-virtual {v3}, Lcom/chaquo/python/PyObject;->toString()Ljava/lang/String;

    move-result-object v3

    if-nez v3, :cond_3

    goto :goto_1

    .line 850
    :cond_3
    const-string v5, "get"

    filled-new-array {v3}, [Ljava/lang/Object;

    move-result-object v6

    invoke-virtual {p1, v5, v6}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object v5

    if-nez v5, :cond_4

    goto :goto_1

    .line 851
    :cond_4
    const-string v6, "__file__"

    invoke-virtual {v5, v6}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lcom/chaquo/python/PyObject;

    if-eqz v6, :cond_5

    invoke-virtual {v6}, Lcom/chaquo/python/PyObject;->toString()Ljava/lang/String;

    move-result-object v6

    if-nez v6, :cond_8

    .line 852
    :cond_5
    :try_start_0
    sget-object v6, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    const-string v6, "__path__"

    invoke-virtual {v5, v6}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/chaquo/python/PyObject;

    if-eqz v5, :cond_6

    invoke-virtual {v5}, Lcom/chaquo/python/PyObject;->asList()Ljava/util/List;

    move-result-object v5

    if-eqz v5, :cond_6

    invoke-static {v5}, Lkotlin/collections/CollectionsKt;->firstOrNull(Ljava/util/List;)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/chaquo/python/PyObject;

    if-eqz v5, :cond_6

    invoke-virtual {v5}, Lcom/chaquo/python/PyObject;->toString()Ljava/lang/String;

    move-result-object v5

    goto :goto_2

    :catchall_0
    move-exception v5

    goto :goto_3

    :cond_6
    move-object v5, v4

    :goto_2
    invoke-static {v5}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v5
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_4

    :goto_3
    sget-object v6, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {v5}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object v5

    invoke-static {v5}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v5

    :goto_4
    invoke-static {v5}, Lkotlin/Result;->isFailure-impl(Ljava/lang/Object;)Z

    move-result v6

    if-eqz v6, :cond_7

    move-object v5, v4

    :cond_7
    move-object v6, v5

    check-cast v6, Ljava/lang/String;

    if-nez v6, :cond_8

    goto :goto_1

    :cond_8
    const/4 v5, 0x0

    const/4 v7, 0x2

    .line 854
    invoke-static {v6, v1, v5, v7, v4}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v8

    if-nez v8, :cond_9

    .line 855
    sget-object v8, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-static {v8, v6}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$canonicalPathOrNull(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    if-eqz v6, :cond_2

    invoke-static {v6, v0, v5, v7, v4}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v4

    const/4 v5, 0x1

    if-ne v4, v5, :cond_2

    .line 857
    :cond_9
    invoke-virtual {v2, v3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_1

    .line 860
    :cond_a
    invoke-virtual {v2}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_5
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-eqz v0, :cond_b

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 861
    const-string/jumbo v1, "pop"

    filled-new-array {v0, v4}, [Ljava/lang/Object;

    move-result-object v0

    invoke-virtual {p1, v1, v0}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    goto :goto_5

    .line 863
    :cond_b
    invoke-interface {v2}, Ljava/util/Collection;->isEmpty()Z

    move-result p0

    if-nez p0, :cond_c

    .line 864
    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result p0

    new-instance p1, Ljava/io/File;

    invoke-direct {p1, p2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object p1

    new-instance p2, Ljava/lang/StringBuilder;

    const-string v0, "Unloaded "

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, " module(s) belonging to removed dependency "

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    :cond_c
    :goto_6
    return-void
.end method

.method private final removePluginDependencies(Ljava/lang/String;)V
    .locals 1

    .line 808
    sget-object v0, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/plugins/pip/PipController;->uninstallDependencies(Ljava/lang/String;)V

    .line 809
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->pruneDependencyPaths()V

    return-void
.end method

.method private final removePluginPathsFromSysPath()V
    .locals 3

    .line 869
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->python:Lcom/chaquo/python/Python;

    if-eqz v0, :cond_2

    const-string/jumbo v1, "sys"

    invoke-virtual {v0, v1}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object v0

    if-eqz v0, :cond_2

    const-string/jumbo v1, "path"

    invoke-virtual {v0, v1}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/chaquo/python/PyObject;

    if-nez v0, :cond_0

    goto :goto_1

    .line 870
    :cond_0
    new-instance v1, Ljava/util/ArrayList;

    iget-object v2, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->dependencyPaths:Ljava/util/concurrent/ConcurrentHashMap$KeySetView;

    invoke-direct {v1, v2}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    .line 871
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v2

    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginsDir()Ljava/io/File;

    move-result-object v2

    invoke-virtual {v2}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 872
    invoke-virtual {v1}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    .line 873
    invoke-direct {p0, v0, v2}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->removeFromSysPath(Lcom/chaquo/python/PyObject;Ljava/lang/String;)V

    goto :goto_0

    .line 875
    :cond_1
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->dependencyPaths:Ljava/util/concurrent/ConcurrentHashMap$KeySetView;

    invoke-virtual {p0}, Ljava/util/concurrent/ConcurrentHashMap$KeySetView;->clear()V

    :cond_2
    :goto_1
    return-void
.end method

.method private final declared-synchronized requireBasePluginClass()Lcom/chaquo/python/PyObject;
    .locals 3

    monitor-enter p0

    .line 230
    :try_start_0
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsSafeMode()Z

    move-result v0

    if-nez v0, :cond_4

    .line 233
    sget-boolean v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->sdkInitialized:Z

    if-eqz v0, :cond_3

    .line 236
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->basePluginClass:Lcom/chaquo/python/PyObject;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    if-eqz v0, :cond_0

    monitor-exit p0

    return-object v0

    .line 238
    :cond_0
    :try_start_1
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->python:Lcom/chaquo/python/Python;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    if-eqz v0, :cond_2

    .line 240
    :try_start_2
    const-string v1, "base_plugin"

    invoke-virtual {v0, v1}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object v0

    const-string v1, "BasePlugin"

    invoke-virtual {v0, v1}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/chaquo/python/PyObject;

    if-eqz v0, :cond_1

    .line 241
    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->basePluginClass:Lcom/chaquo/python/PyObject;
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 239
    monitor-exit p0

    return-object v0

    :catchall_0
    move-exception v0

    goto :goto_0

    .line 242
    :cond_1
    :try_start_3
    new-instance v0, Ljava/lang/Exception;

    const-string v1, "BasePlugin class is missing from the Python SDK"

    invoke-direct {v0, v1}, Ljava/lang/Exception;-><init>(Ljava/lang/String;)V

    throw v0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 244
    :goto_0
    :try_start_4
    new-instance v1, Ljava/lang/Exception;

    const-string v2, "Failed to initialize Python plugin runtime"

    invoke-direct {v1, v2, v0}, Ljava/lang/Exception;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw v1

    :catchall_1
    move-exception v0

    goto :goto_1

    .line 238
    :cond_2
    new-instance v0, Ljava/lang/Exception;

    const-string v1, "Python interpreter is not initialized"

    invoke-direct {v0, v1}, Ljava/lang/Exception;-><init>(Ljava/lang/String;)V

    throw v0

    .line 234
    :cond_3
    new-instance v0, Ljava/lang/Exception;

    const-string v1, "Python plugin SDK is not initialized"

    invoke-direct {v0, v1}, Ljava/lang/Exception;-><init>(Ljava/lang/String;)V

    throw v0

    .line 231
    :cond_4
    new-instance v0, Ljava/lang/Exception;

    const-string v1, "Plugins cannot be enabled while safe mode is active"

    invoke-direct {v0, v1}, Ljava/lang/Exception;-><init>(Ljava/lang/String;)V

    throw v0

    .line 244
    :goto_1
    monitor-exit p0
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    throw v0
.end method

.method private final runDevServer()V
    .locals 3

    .line 525
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsSafeMode()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    .line 527
    :cond_0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPython()Lcom/chaquo/python/Python;

    move-result-object v0

    if-nez v0, :cond_1

    goto :goto_0

    .line 528
    :cond_1
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->devServerClass:Lcom/chaquo/python/PyObject;

    if-eqz v1, :cond_2

    .line 529
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->stopDevServer()V

    .line 534
    :cond_2
    :try_start_0
    const-string v1, "dev_server"

    invoke-virtual {v0, v1}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object v0

    const-string v1, "DevServer"

    invoke-virtual {v0, v1}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/chaquo/python/PyObject;

    .line 533
    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->devServerClass:Lcom/chaquo/python/PyObject;

    if-nez v0, :cond_3

    :goto_0
    return-void

    :cond_3
    if-eqz v0, :cond_4

    .line 538
    const-string/jumbo v1, "start_server"

    const/4 v2, 0x0

    new-array v2, v2, [Ljava/lang/Object;

    invoke-virtual {v0, v1, v2}, Lcom/chaquo/python/PyObject;->callAttrThrows(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    goto :goto_1

    :catchall_0
    move-exception v0

    goto :goto_2

    .line 539
    :cond_4
    :goto_1
    const-string v0, "Dev server started successfully."

    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-void

    .line 541
    :goto_2
    const-string v1, "Failed to initialize dev server"

    invoke-static {v1, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    const/4 v0, 0x0

    .line 542
    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->devServerClass:Lcom/chaquo/python/PyObject;

    return-void
.end method

.method private final stopAndUnloadSdk()V
    .locals 6

    .line 409
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->python:Lcom/chaquo/python/Python;

    if-nez v0, :cond_0

    goto :goto_1

    :cond_0
    const/4 v1, 0x0

    .line 410
    iput-object v1, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->basePluginClass:Lcom/chaquo/python/PyObject;

    .line 411
    const-string/jumbo v1, "sys"

    invoke-virtual {v0, v1}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object v0

    .line 412
    const-string/jumbo v1, "modules"

    invoke-virtual {v0, v1}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/chaquo/python/PyObject;

    const/4 v2, 0x0

    if-eqz v1, :cond_1

    .line 414
    :try_start_0
    const-string v3, "get"

    const-string v4, "_sdk_version"

    filled-new-array {v4}, [Ljava/lang/Object;

    move-result-object v4

    invoke-virtual {v1, v3, v4}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object v3

    if-eqz v3, :cond_1

    const-string v4, "__stop__"

    new-array v5, v2, [Ljava/lang/Object;

    invoke-virtual {v3, v4, v5}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception v3

    .line 416
    const-string v4, "Failed to stop Python SDK bootstrap"

    invoke-static {v4, v3}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 419
    :cond_1
    :goto_0
    sput-boolean v2, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->sdkInitialized:Z

    .line 421
    sget-object v2, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_DIR:Ljava/io/File;

    .line 422
    const-string/jumbo v3, "path"

    invoke-virtual {v0, v3}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/chaquo/python/PyObject;

    if-eqz v2, :cond_2

    if-eqz v0, :cond_2

    .line 425
    invoke-virtual {v2}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v3

    filled-new-array {v3}, [Ljava/lang/Object;

    move-result-object v3

    .line 423
    const-string v4, "__contains__"

    invoke-virtual {v0, v4, v3}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object v3

    .line 426
    invoke-virtual {v3}, Lcom/chaquo/python/PyObject;->toBoolean()Z

    move-result v3

    if-eqz v3, :cond_2

    .line 428
    invoke-virtual {v2}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v3

    filled-new-array {v3}, [Ljava/lang/Object;

    move-result-object v3

    const-string/jumbo v4, "remove"

    invoke-virtual {v0, v4, v3}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    :cond_2
    if-eqz v2, :cond_3

    if-eqz v1, :cond_3

    .line 432
    const-string v0, ""

    invoke-direct {p0, v1, v2, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->removeModulesRecursive(Lcom/chaquo/python/PyObject;Ljava/io/File;Ljava/lang/String;)V

    :cond_3
    :goto_1
    return-void
.end method

.method private final stopDevServer()V
    .locals 4

    .line 547
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->devServerClass:Lcom/chaquo/python/PyObject;

    if-nez v0, :cond_0

    return-void

    :cond_0
    const/4 v1, 0x0

    .line 549
    :try_start_0
    const-string/jumbo v2, "stop_server"

    const/4 v3, 0x0

    new-array v3, v3, [Ljava/lang/Object;

    invoke-virtual {v0, v2, v3}, Lcom/chaquo/python/PyObject;->callAttrThrows(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    .line 550
    const-string v0, "Dev server stopped successfully."

    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 554
    iput-object v1, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->devServerClass:Lcom/chaquo/python/PyObject;

    return-void

    :catchall_0
    move-exception v0

    .line 552
    :try_start_1
    const-string v2, "Failed to stop dev server"

    invoke-static {v2, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    .line 554
    iput-object v1, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->devServerClass:Lcom/chaquo/python/PyObject;

    return-void

    :catchall_1
    move-exception v0

    iput-object v1, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->devServerClass:Lcom/chaquo/python/PyObject;

    throw v0
.end method


# virtual methods
.method public canOpenInExternalApp()Z
    .locals 0

    const/4 p0, 0x1

    return p0
.end method

.method public checkDevServer()V
    .locals 1

    .line 515
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsSafeMode()Z

    move-result v0

    if-eqz v0, :cond_0

    return-void

    .line 517
    :cond_0
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsDevMode()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 518
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->runDevServer()V

    return-void

    .line 520
    :cond_1
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->stopDevServer()V

    return-void
.end method

.method public clearPluginSettings(Ljava/lang/String;)V
    .locals 2

    .line 2047
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->settingsCache:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 2048
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPython()Lcom/chaquo/python/Python;

    move-result-object p0

    if-nez p0, :cond_0

    return-void

    .line 2050
    :cond_0
    :try_start_0
    const-string/jumbo v0, "plugin_settings"

    invoke-virtual {p0, v0}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object p0

    .line 2051
    const-string v0, "clear_settings"

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {p0, v0, v1}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    :try_end_0
    .catch Lcom/chaquo/python/PyException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 2053
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Failed to clear plugin settings for "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method public deletePlugin(Ljava/lang/String;Lorg/telegram/messenger/Utilities$Callback;)V
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Lorg/telegram/messenger/Utilities$Callback<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    .line 1076
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->unloadPlugin(Ljava/lang/String;)V

    .line 1079
    :try_start_0
    invoke-direct {p0, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->removePluginDependencies(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    .line 1081
    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Failed to uninstall dependencies for "

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 1084
    :goto_0
    new-instance v0, Ljava/io/File;

    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v1

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginsDir()Ljava/io/File;

    move-result-object v1

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, ".py"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 1085
    sget-object v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-static {v1, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$deleteFileIfExists(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/io/File;)V

    .line 1087
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->isPluginPinned(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_0

    const/4 v1, 0x0

    .line 1088
    invoke-virtual {v0, p1, v1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->setPluginPinned(Ljava/lang/String;Z)V

    .line 1090
    :cond_0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    const/4 v1, 0x1

    invoke-virtual {v0, p1, v1}, Lcom/exteragram/messenger/plugins/PluginsController;->clearPluginSettingsPreferences(Ljava/lang/String;Z)V

    .line 1091
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 1092
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->notifyPluginsChanged()V

    if-eqz p2, :cond_1

    .line 1095
    new-instance p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda13;

    invoke-direct {p0, p2}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda13;-><init>(Lorg/telegram/messenger/Utilities$Callback;)V

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    :cond_1
    return-void
.end method

.method public executeOnAppEvent(Ljava/lang/String;)V
    .locals 7

    .line 1665
    sget-boolean v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->sdkInitialized:Z

    if-eqz v0, :cond_5

    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsSafeMode()Z

    move-result v0

    if-eqz v0, :cond_0

    goto/16 :goto_6

    .line 1668
    :cond_0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPython()Lcom/chaquo/python/Python;

    move-result-object v0

    if-eqz v0, :cond_5

    const-string v1, "base_plugin"

    invoke-virtual {v0, v1}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object v0

    if-eqz v0, :cond_5

    const-string v1, "AppEvent"

    invoke-virtual {v0, v1}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/chaquo/python/PyObject;

    if-nez v0, :cond_1

    goto/16 :goto_6

    .line 1669
    :cond_1
    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/chaquo/python/PyObject;->call([Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object v0

    .line 1671
    :try_start_0
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->debuggerListener:Lcom/chaquo/python/PyObject;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 1672
    const-string/jumbo v2, "on_app_event"

    if-eqz v1, :cond_2

    .line 1674
    :try_start_1
    filled-new-array {v0}, [Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v1, v2, v3}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    :try_end_1
    .catch Lcom/chaquo/python/PyException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    goto :goto_0

    :catchall_0
    move-exception p0

    goto/16 :goto_5

    :catch_0
    move-exception v1

    .line 1676
    :try_start_2
    const-string v3, "Failed to execute app event for debugger listener"

    invoke-static {v3, v1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 1680
    :cond_2
    :goto_0
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->pluginInstances:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v1}, Ljava/util/concurrent/ConcurrentHashMap;->entrySet()Ljava/util/Set;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_3
    :goto_1
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_4

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/util/Map$Entry;

    invoke-interface {v3}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    invoke-interface {v3}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/chaquo/python/PyObject;

    .line 1681
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v5

    invoke-virtual {v5, v4}, Lcom/exteragram/messenger/plugins/PluginsController;->isPluginActive$TMessagesProj(Ljava/lang/String;)Z

    move-result v5

    if-eqz v5, :cond_3

    .line 1682
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v5

    invoke-virtual {v5}, Lcom/exteragram/messenger/plugins/PluginsController;->getWatchdog()Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    move-result-object v5

    invoke-virtual {v5, v4}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionStarted(Ljava/lang/String;)V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 1684
    :try_start_3
    filled-new-array {v0}, [Ljava/lang/Object;

    move-result-object v5

    invoke-virtual {v3, v2, v5}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    :try_end_3
    .catch Lcom/chaquo/python/PyException; {:try_start_3 .. :try_end_3} :catch_1
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    .line 1688
    :try_start_4
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v3

    :goto_2
    invoke-virtual {v3}, Lcom/exteragram/messenger/plugins/PluginsController;->getWatchdog()Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    move-result-object v3
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    goto :goto_3

    :catchall_1
    move-exception p1

    goto :goto_4

    :catch_1
    move-exception v3

    .line 1686
    :try_start_5
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "Failed to execute app "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v6, " for "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v5, v3}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_1

    .line 1688
    :try_start_6
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v3

    goto :goto_2

    :goto_3
    invoke-virtual {v3, v4}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionFinished(Ljava/lang/String;)V

    goto :goto_1

    :goto_4
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->getWatchdog()Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    move-result-object p0

    invoke-virtual {p0, v4}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionFinished(Ljava/lang/String;)V

    throw p1
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_0

    .line 1693
    :cond_4
    invoke-static {v0}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->closeSafely(Lcom/chaquo/python/PyObject;)V

    return-void

    :goto_5
    invoke-static {v0}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->closeSafely(Lcom/chaquo/python/PyObject;)V

    throw p0

    :cond_5
    :goto_6
    return-void
.end method

.method public final executePostRequestHook(Ljava/lang/String;ILorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;Lcom/chaquo/python/PyObject;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "I",
            "Lorg/telegram/tgnet/TLObject;",
            "Lorg/telegram/tgnet/TLRPC$TL_error;",
            "Lcom/chaquo/python/PyObject;",
            ")",
            "Lcom/exteragram/messenger/plugins/PluginsController$HookResult<",
            "Lcom/exteragram/messenger/plugins/hooks/PluginsHooks$PostRequestResult;",
            ">;"
        }
    .end annotation

    const-string p0, "Bad error type from post_request_hook for "

    const-string v0, "Bad response type from post_request_hook for "

    const/4 v1, 0x0

    if-eqz p5, :cond_4

    .line 1813
    :try_start_0
    const-string/jumbo v2, "post_request_hook"

    .line 1815
    invoke-static {p2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p2

    .line 1817
    filled-new-array {p1, p2, p3, p4}, [Ljava/lang/Object;

    move-result-object p2

    .line 1812
    invoke-virtual {p5, v2, p2}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    if-eqz p2, :cond_4

    .line 1821
    :try_start_1
    const-string/jumbo p5, "strategy"

    const-string v2, ""

    invoke-static {p2, p5, v2}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getString(Lcom/chaquo/python/PyObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p5

    if-eqz p5, :cond_3

    .line 1822
    const-string v2, "MODIFY"

    const/4 v3, 0x0

    const/4 v4, 0x2

    invoke-static {p5, v2, v1, v4, v3}, Lkotlin/text/StringsKt;->endsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v2
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    const-string v5, "MODIFY_FINAL"

    if-nez v2, :cond_0

    :try_start_2
    invoke-static {p5, v5, v1, v4, v3}, Lkotlin/text/StringsKt;->endsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_3

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_3

    .line 1826
    :cond_0
    :goto_0
    const-string/jumbo v2, "response"

    invoke-virtual {p2, v2}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/chaquo/python/PyObject;
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    if-eqz v2, :cond_1

    .line 1829
    :try_start_3
    const-class v6, Lorg/telegram/tgnet/TLObject;

    invoke-virtual {v2, v6}, Lcom/chaquo/python/PyObject;->toJava(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lorg/telegram/tgnet/TLObject;
    :try_end_3
    .catch Ljava/lang/ClassCastException; {:try_start_3 .. :try_end_3} :catch_0
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    move-object p3, v2

    goto :goto_1

    :catch_0
    move-exception v2

    .line 1831
    :try_start_4
    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v6, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0, v2}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 1834
    :cond_1
    :goto_1
    const-string v0, "error"

    invoke-virtual {p2, v0}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/chaquo/python/PyObject;
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    if-eqz v0, :cond_2

    .line 1837
    :try_start_5
    const-class v2, Lorg/telegram/tgnet/TLRPC$TL_error;

    invoke-virtual {v0, v2}, Lcom/chaquo/python/PyObject;->toJava(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/tgnet/TLRPC$TL_error;
    :try_end_5
    .catch Ljava/lang/ClassCastException; {:try_start_5 .. :try_end_5} :catch_1
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    move-object p4, v0

    goto :goto_2

    :catch_1
    move-exception v0

    .line 1839
    :try_start_6
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, p0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 1842
    :cond_2
    :goto_2
    invoke-static {p5, v5, v1, v4, v3}, Lkotlin/text/StringsKt;->endsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_3

    .line 1843
    new-instance p0, Lcom/exteragram/messenger/plugins/PluginsController$HookResult;

    .line 1844
    new-instance p5, Lcom/exteragram/messenger/plugins/hooks/PluginsHooks$PostRequestResult;

    invoke-direct {p5, p3, p4}, Lcom/exteragram/messenger/plugins/hooks/PluginsHooks$PostRequestResult;-><init>(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    const/4 v0, 0x1

    .line 1843
    invoke-direct {p0, p5, v1, v0}, Lcom/exteragram/messenger/plugins/PluginsController$HookResult;-><init>(Ljava/lang/Object;ZZ)V
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_0

    .line 1852
    :try_start_7
    invoke-static {p2}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->closeSafely(Lcom/chaquo/python/PyObject;)V

    return-object p0

    :catchall_1
    move-exception p0

    goto :goto_4

    :cond_3
    invoke-static {p2}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->closeSafely(Lcom/chaquo/python/PyObject;)V

    goto :goto_5

    :goto_3
    invoke-static {p2}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->closeSafely(Lcom/chaquo/python/PyObject;)V

    throw p0
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_1

    .line 1856
    :goto_4
    new-instance p2, Ljava/lang/StringBuilder;

    const-string p5, "Failed to execute post_request_hook for "

    invoke-direct {p2, p5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 1860
    :cond_4
    :goto_5
    new-instance p0, Lcom/exteragram/messenger/plugins/PluginsController$HookResult;

    .line 1861
    new-instance p1, Lcom/exteragram/messenger/plugins/hooks/PluginsHooks$PostRequestResult;

    invoke-direct {p1, p3, p4}, Lcom/exteragram/messenger/plugins/hooks/PluginsHooks$PostRequestResult;-><init>(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V

    .line 1860
    invoke-direct {p0, p1, v1, v1}, Lcom/exteragram/messenger/plugins/PluginsController$HookResult;-><init>(Ljava/lang/Object;ZZ)V

    return-object p0
.end method

.method public executePostRequestHook(Ljava/lang/String;ILorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "I",
            "Lorg/telegram/tgnet/TLObject;",
            "Lorg/telegram/tgnet/TLRPC$TL_error;",
            "Ljava/lang/String;",
            ")",
            "Lcom/exteragram/messenger/plugins/PluginsController$HookResult<",
            "Lcom/exteragram/messenger/plugins/hooks/PluginsHooks$PostRequestResult;",
            ">;"
        }
    .end annotation

    .line 1875
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->pluginInstances:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p5}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p5

    move-object v5, p5

    check-cast v5, Lcom/chaquo/python/PyObject;

    move-object v0, p0

    move-object v1, p1

    move v2, p2

    move-object v3, p3

    move-object v4, p4

    invoke-virtual/range {v0 .. v5}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->executePostRequestHook(Ljava/lang/String;ILorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;Lcom/chaquo/python/PyObject;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;

    move-result-object p0

    return-object p0
.end method

.method public executePreRequestHook(Ljava/lang/String;ILorg/telegram/tgnet/TLObject;Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "I",
            "Lorg/telegram/tgnet/TLObject;",
            "Ljava/lang/String;",
            ")",
            "Lcom/exteragram/messenger/plugins/PluginsController$HookResult<",
            "Lorg/telegram/tgnet/TLObject;",
            ">;"
        }
    .end annotation

    .line 1778
    new-instance v5, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda23;

    invoke-direct {v5, p1, p2}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda23;-><init>(Ljava/lang/String;I)V

    new-instance v6, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda24;

    invoke-direct {v6, p4, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda24;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    const-class v3, Lorg/telegram/tgnet/TLObject;

    const-string/jumbo v4, "request"

    move-object v0, p0

    move-object v2, p3

    move-object v1, p4

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->executeHook(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$PyMethodCaller;Lorg/telegram/messenger/Utilities$Callback;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;

    move-result-object p0

    return-object p0
.end method

.method public executeSendMessageHook(ILorg/telegram/messenger/SendMessagesHelper$SendMessageParams;Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(I",
            "Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;",
            "Ljava/lang/String;",
            ")",
            "Lcom/exteragram/messenger/plugins/PluginsController$HookResult<",
            "Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;",
            ">;"
        }
    .end annotation

    .line 1939
    new-instance v5, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda20;

    invoke-direct {v5, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda20;-><init>(I)V

    new-instance v6, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda21;

    invoke-direct {v6, p3}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda21;-><init>(Ljava/lang/String;)V

    const-class v3, Lorg/telegram/messenger/SendMessagesHelper$SendMessageParams;

    const-string/jumbo v4, "params"

    move-object v0, p0

    move-object v2, p2

    move-object v1, p3

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->executeHook(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$PyMethodCaller;Lorg/telegram/messenger/Utilities$Callback;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;

    move-result-object p0

    return-object p0
.end method

.method public executeUpdateHook(Ljava/lang/String;ILorg/telegram/tgnet/TLRPC$Update;Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "I",
            "Lorg/telegram/tgnet/TLRPC$Update;",
            "Ljava/lang/String;",
            ")",
            "Lcom/exteragram/messenger/plugins/PluginsController$HookResult<",
            "Lorg/telegram/tgnet/TLRPC$Update;",
            ">;"
        }
    .end annotation

    .line 1884
    new-instance v5, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda3;

    invoke-direct {v5, p1, p2}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda3;-><init>(Ljava/lang/String;I)V

    new-instance v6, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda4;

    invoke-direct {v6, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda4;-><init>(Ljava/lang/String;)V

    const-class v3, Lorg/telegram/tgnet/TLRPC$Update;

    const-string/jumbo v4, "update"

    move-object v0, p0

    move-object v2, p3

    move-object v1, p4

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->executeHook(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$PyMethodCaller;Lorg/telegram/messenger/Utilities$Callback;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;

    move-result-object p0

    return-object p0
.end method

.method public executeUpdatesHook(Ljava/lang/String;ILorg/telegram/tgnet/TLRPC$Updates;Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "I",
            "Lorg/telegram/tgnet/TLRPC$Updates;",
            "Ljava/lang/String;",
            ")",
            "Lcom/exteragram/messenger/plugins/PluginsController$HookResult<",
            "Lorg/telegram/tgnet/TLRPC$Updates;",
            ">;"
        }
    .end annotation

    .line 1912
    new-instance v5, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda10;

    invoke-direct {v5, p1, p2}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda10;-><init>(Ljava/lang/String;I)V

    new-instance v6, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda11;

    invoke-direct {v6, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda11;-><init>(Ljava/lang/String;)V

    const-class v3, Lorg/telegram/tgnet/TLRPC$Updates;

    const-string/jumbo v4, "updates"

    move-object v0, p0

    move-object v2, p3

    move-object v1, p4

    invoke-direct/range {v0 .. v6}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->executeHook(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/String;Lcom/exteragram/messenger/plugins/PythonPluginsEngine$PyMethodCaller;Lorg/telegram/messenger/Utilities$Callback;)Lcom/exteragram/messenger/plugins/PluginsController$HookResult;

    move-result-object p0

    return-object p0
.end method

.method public final fetchParameterValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 3

    const/4 v0, 0x0

    if-nez p1, :cond_0

    return-object v0

    .line 1965
    :cond_0
    :try_start_0
    new-instance v1, Ljava/io/File;

    invoke-direct {v1, p1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 1966
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v2

    if-eqz v2, :cond_2

    invoke-virtual {v1}, Ljava/io/File;->isFile()Z

    move-result v1

    if-nez v1, :cond_1

    goto :goto_0

    .line 1969
    :cond_1
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->parsePluginMetadata(Ljava/lang/String;)Ljava/util/Map;

    move-result-object p0

    invoke-interface {p0, p2}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/lang/String;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    :cond_2
    :goto_0
    return-object v0
.end method

.method public getAllPluginSettings(Ljava/lang/String;)Ljava/util/Map;
    .locals 6
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

    .line 2058
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPython()Lcom/chaquo/python/Python;

    move-result-object v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return-object v1

    .line 2060
    :cond_0
    :try_start_0
    const-string/jumbo v2, "plugin_settings"

    invoke-virtual {v0, v2}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object v0

    .line 2061
    const-string/jumbo v2, "get_all_settings"

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object v3

    invoke-virtual {v0, v2, v3}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object v0

    if-eqz v0, :cond_4

    .line 2063
    new-instance v2, Ljava/util/HashMap;

    invoke-direct {v2}, Ljava/util/HashMap;-><init>()V

    .line 2064
    invoke-virtual {v0}, Lcom/chaquo/python/PyObject;->asMap()Ljava/util/Map;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :cond_1
    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_3

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/util/Map$Entry;

    invoke-interface {v3}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/chaquo/python/PyObject;

    invoke-interface {v3}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/chaquo/python/PyObject;

    if-eqz v4, :cond_1

    .line 2066
    invoke-virtual {v4}, Lcom/chaquo/python/PyObject;->toString()Ljava/lang/String;

    move-result-object v4

    if-eqz v3, :cond_2

    const-class v5, Ljava/lang/Object;

    invoke-virtual {v3, v5}, Lcom/chaquo/python/PyObject;->toJava(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v3

    goto :goto_1

    :catch_0
    move-exception p0

    goto :goto_2

    :cond_2
    move-object v3, v1

    :goto_1
    invoke-interface {v2, v4, v3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_0

    .line 2069
    :cond_3
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->settingsCache:Ljava/util/concurrent/ConcurrentHashMap;

    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0, v2}, Ljava/util/concurrent/ConcurrentHashMap;-><init>(Ljava/util/Map;)V

    invoke-interface {p0, p1, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Lcom/chaquo/python/PyException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v2

    .line 2073
    :goto_2
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v2, "Failed to get all plugin settings for "

    invoke-direct {v0, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_4
    return-object v1
.end method

.method public final getBasePluginClass()Lcom/chaquo/python/PyObject;
    .locals 0

    .line 189
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->basePluginClass:Lcom/chaquo/python/PyObject;

    return-object p0
.end method

.method public final getDebuggerListener()Lcom/chaquo/python/PyObject;
    .locals 0

    .line 187
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->debuggerListener:Lcom/chaquo/python/PyObject;

    return-object p0
.end method

.method public final getPluginInstances()Ljava/util/concurrent/ConcurrentHashMap;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Lcom/chaquo/python/PyObject;",
            ">;"
        }
    .end annotation

    .line 176
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->pluginInstances:Ljava/util/concurrent/ConcurrentHashMap;

    return-object p0
.end method

.method public getPluginPath(Ljava/lang/String;)Ljava/lang/String;
    .locals 2

    .line 1100
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginsDir()Ljava/io/File;

    move-result-object p0

    invoke-virtual {p0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object p0

    sget-object v0, Ljava/io/File;->separator:Ljava/lang/String;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ".py"

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public getPluginSetting(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 3

    .line 2008
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->settingsCache:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/concurrent/ConcurrentHashMap;

    if-eqz v0, :cond_0

    .line 2009
    invoke-virtual {v0, p2}, Ljava/util/concurrent/ConcurrentHashMap;->containsKey(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 2010
    invoke-virtual {v0, p2}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0

    .line 2013
    :cond_0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPython()Lcom/chaquo/python/Python;

    move-result-object v0

    if-nez v0, :cond_1

    goto :goto_0

    .line 2015
    :cond_1
    :try_start_0
    const-string/jumbo v1, "plugin_settings"

    invoke-virtual {v0, v1}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object v0

    .line 2016
    const-string/jumbo v1, "get_setting"

    filled-new-array {p1, p2, p3}, [Ljava/lang/Object;

    move-result-object v2

    invoke-virtual {v0, v1, v2}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object v0

    if-nez v0, :cond_2

    :goto_0
    return-object p3

    .line 2019
    :cond_2
    instance-of v1, p3, Ljava/lang/Boolean;

    if-eqz v1, :cond_3

    invoke-virtual {v0}, Lcom/chaquo/python/PyObject;->toBoolean()Z

    move-result v0

    invoke-static {v0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v0

    goto :goto_1

    :catch_0
    move-exception p0

    goto :goto_2

    .line 2020
    :cond_3
    instance-of v1, p3, Ljava/lang/Integer;

    if-eqz v1, :cond_4

    invoke-virtual {v0}, Lcom/chaquo/python/PyObject;->toInt()I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    goto :goto_1

    .line 2021
    :cond_4
    instance-of v1, p3, Ljava/lang/String;

    if-eqz v1, :cond_5

    invoke-virtual {v0}, Lcom/chaquo/python/PyObject;->toString()Ljava/lang/String;

    move-result-object v0

    goto :goto_1

    .line 2022
    :cond_5
    instance-of v1, p3, Ljava/lang/Float;

    if-eqz v1, :cond_6

    invoke-virtual {v0}, Lcom/chaquo/python/PyObject;->toFloat()F

    move-result v0

    invoke-static {v0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v0

    goto :goto_1

    .line 2023
    :cond_6
    instance-of v1, p3, Ljava/lang/Long;

    if-eqz v1, :cond_7

    invoke-virtual {v0}, Lcom/chaquo/python/PyObject;->toLong()J

    move-result-wide v0

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    goto :goto_1

    :cond_7
    if-nez p3, :cond_8

    .line 2024
    const-class v1, Ljava/lang/Object;

    invoke-virtual {v0, v1}, Lcom/chaquo/python/PyObject;->toJava(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v0

    goto :goto_1

    .line 2025
    :cond_8
    invoke-virtual {p3}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/chaquo/python/PyObject;->toJava(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v0

    .line 2027
    :goto_1
    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->settingsCache:Ljava/util/concurrent/ConcurrentHashMap;

    new-instance v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda25;

    invoke-direct {v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda25;-><init>()V

    new-instance v2, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda26;

    invoke-direct {v2, v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda26;-><init>(Lkotlin/jvm/functions/Function1;)V

    invoke-virtual {p0, p1, v2}, Ljava/util/concurrent/ConcurrentHashMap;->computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Ljava/util/Map;

    invoke-interface {p0, p2, v0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Lcom/chaquo/python/PyException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    .line 2030
    :goto_2
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Failed to get plugin setting "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "/"

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-object p3
.end method

.method public init(Ljava/lang/Runnable;)V
    .locals 5

    .line 463
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPython()Lcom/chaquo/python/Python;

    move-result-object v0

    if-nez v0, :cond_0

    .line 464
    invoke-interface {p1}, Ljava/lang/Runnable;->run()V

    return-void

    .line 468
    :cond_0
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->initSdk()Z

    move-result v0

    if-nez v0, :cond_1

    .line 469
    invoke-interface {p1}, Ljava/lang/Runnable;->run()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-void

    :catchall_0
    move-exception p0

    goto/16 :goto_4

    .line 477
    :cond_1
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsSafeMode()Z

    move-result v0

    if-nez v0, :cond_2

    .line 478
    sget-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;

    new-instance v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda8;

    invoke-direct {v1, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda8;-><init>(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Updater$Companion;)V

    const-wide/16 v2, 0x1388

    invoke-static {v1, v2, v3}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    .line 481
    :cond_2
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->python:Lcom/chaquo/python/Python;

    if-nez v0, :cond_3

    .line 482
    invoke-interface {p1}, Ljava/lang/Runnable;->run()V

    return-void

    .line 486
    :cond_3
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsSafeMode()Z

    move-result v1

    if-nez v1, :cond_6

    .line 488
    :try_start_1
    const-string/jumbo v1, "plugin_settings"

    invoke-virtual {v0, v1}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object v0

    .line 489
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v1

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v1

    invoke-interface {v1}, Landroid/content/SharedPreferences;->getAll()Ljava/util/Map;

    move-result-object v1

    .line 491
    const-string/jumbo v2, "init"

    .line 492
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v3

    invoke-virtual {v3}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginsDir()Ljava/io/File;

    move-result-object v3

    invoke-virtual {v3}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v3

    .line 493
    filled-new-array {v3, v1}, [Ljava/lang/Object;

    move-result-object v1

    .line 490
    invoke-virtual {v0, v2, v1}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object v0

    .line 495
    const-class v1, [Ljava/lang/String;

    invoke-virtual {v0, v1}, Lcom/chaquo/python/PyObject;->toJava(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Ljava/lang/String;

    .line 496
    array-length v1, v0

    const/4 v2, 0x0

    if-nez v1, :cond_4

    const/4 v1, 0x1

    goto :goto_0

    :cond_4
    move v1, v2

    :goto_0
    if-nez v1, :cond_6

    .line 497
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v1

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v1

    .line 45
    invoke-interface {v1}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v1

    .line 498
    array-length v3, v0

    :goto_1
    if-ge v2, v3, :cond_5

    aget-object v4, v0, v2

    .line 499
    invoke-interface {v1, v4}, Landroid/content/SharedPreferences$Editor;->remove(Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    :catch_0
    move-exception v0

    goto :goto_2

    .line 50
    :cond_5
    invoke-interface {v1}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 502
    array-length v0, v0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Migrated "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, " plugin settings from SharedPreferences to JSON."

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V
    :try_end_1
    .catch Lcom/chaquo/python/PyException; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_3

    .line 505
    :goto_2
    const-string v1, "Failed to initialize plugin_settings module"

    invoke-static {v1, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 509
    :cond_6
    :goto_3
    sget-object v0, Lcom/exteragram/messenger/plugins/pip/PipController;->INSTANCE:Lcom/exteragram/messenger/plugins/pip/PipController;

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/pip/PipController;->cleanup()V

    .line 510
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->loadPlugins(Ljava/lang/Runnable;)V

    .line 511
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->checkDevServer()V

    return-void

    .line 473
    :goto_4
    const-string v0, "Failed to initialize Python plugins engine"

    invoke-static {v0, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 474
    invoke-interface {p1}, Ljava/lang/Runnable;->run()V

    return-void
.end method

.method public isEngineAvailable()Z
    .locals 1

    .line 224
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->python:Lcom/chaquo/python/Python;

    if-eqz v0, :cond_0

    sget-boolean v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->sdkInitialized:Z

    if-eqz v0, :cond_0

    iget-object p0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->basePluginClass:Lcom/chaquo/python/PyObject;

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public isPlugin(Ljava/io/File;Lorg/telegram/messenger/MessageObject;)Z
    .locals 2

    const/4 p0, 0x0

    if-eqz p1, :cond_0

    .line 220
    invoke-virtual {p1}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object p1

    sget-object p2, Ljava/util/Locale;->ROOT:Ljava/util/Locale;

    invoke-virtual {p1, p2}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object p1

    const/4 p2, 0x2

    const/4 v0, 0x0

    const-string v1, ".plugin"

    invoke-static {p1, v1, p0, p2, v0}, Lkotlin/text/StringsKt;->endsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_0

    const/4 p0, 0x1

    :cond_0
    return p0
.end method

.method public final loadPlugin(Ljava/lang/String;Ljava/lang/String;)V
    .locals 1

    const/4 v0, 0x0

    .line 674
    invoke-virtual {p0, p1, p2, v0, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->loadPlugin(Ljava/lang/String;Ljava/lang/String;Lcom/exteragram/messenger/plugins/Plugin;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)V

    return-void
.end method

.method public final loadPlugin(Ljava/lang/String;Ljava/lang/String;Lcom/exteragram/messenger/plugins/Plugin;)V
    .locals 1

    const/4 v0, 0x0

    .line 679
    invoke-virtual {p0, p1, p2, p3, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->loadPlugin(Ljava/lang/String;Ljava/lang/String;Lcom/exteragram/messenger/plugins/Plugin;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)V

    return-void
.end method

.method public final loadPlugin(Ljava/lang/String;Ljava/lang/String;Lcom/exteragram/messenger/plugins/Plugin;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)V
    .locals 5

    .line 689
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v0

    .line 690
    new-instance v1, Ljava/lang/StringBuilder;

    const-string/jumbo v2, "plugin_enabled_"

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    const/4 v3, 0x0

    .line 689
    invoke-interface {v0, v1, v3}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    .line 693
    new-instance v1, Ljava/io/File;

    invoke-direct {v1, p2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 694
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v4

    if-eqz v4, :cond_8

    invoke-virtual {v1}, Ljava/io/File;->isFile()Z

    move-result v1

    if-eqz v1, :cond_8

    if-nez p3, :cond_1

    .line 700
    invoke-virtual {p0, p2}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->validatePluginFromFile(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;

    move-result-object p3

    .line 701
    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;->getError()Ljava/lang/String;

    move-result-object v1

    if-nez v1, :cond_0

    .line 704
    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;->getPlugin()Lcom/exteragram/messenger/plugins/Plugin;

    move-result-object p3

    goto :goto_0

    .line 702
    :cond_0
    new-instance p0, Ljava/lang/Exception;

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;->getError()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/lang/Exception;-><init>(Ljava/lang/String;)V

    throw p0

    :cond_1
    :goto_0
    if-eqz p3, :cond_7

    .line 708
    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object v1

    invoke-static {p1, v1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_6

    .line 712
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->pluginInstances:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v1, p1}, Ljava/util/concurrent/ConcurrentHashMap;->containsKey(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 713
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->unloadPlugin(Ljava/lang/String;)V

    .line 716
    :cond_2
    invoke-virtual {p3, v3}, Lcom/exteragram/messenger/plugins/Plugin;->setEnabled(Z)V

    const/4 v1, 0x0

    .line 717
    invoke-virtual {p3, v1}, Lcom/exteragram/messenger/plugins/Plugin;->setError(Ljava/lang/Throwable;)V

    .line 718
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v4

    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v4

    invoke-interface {v4, p1, p3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 720
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsSafeMode()Z

    move-result v4

    if-eqz v4, :cond_3

    goto :goto_1

    :cond_3
    if-nez v0, :cond_5

    if-eqz p4, :cond_4

    .line 726
    invoke-direct {p0, p1, p3, p4}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->installPluginDependencies(Ljava/lang/String;Lcom/exteragram/messenger/plugins/Plugin;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)V

    :cond_4
    :goto_1
    return-void

    .line 732
    :cond_5
    :try_start_0
    invoke-direct {p0, p1, p2, p3, p4}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->createPluginInstance(Ljava/lang/String;Ljava/lang/String;Lcom/exteragram/messenger/plugins/Plugin;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    const/4 p2, 0x1

    .line 740
    invoke-virtual {p0, p1, p2, v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->setPluginEnabled(Ljava/lang/String;ZLorg/telegram/messenger/Utilities$Callback;)V

    return-void

    :catchall_0
    move-exception p2

    .line 734
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p3

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/PluginsController;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object p3

    .line 45
    invoke-interface {p3}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p3

    .line 735
    new-instance p4, Ljava/lang/StringBuilder;

    invoke-direct {p4, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p4

    invoke-interface {p3, p4, v3}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 50
    invoke-interface {p3}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 737
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->unloadPlugin(Ljava/lang/String;)V

    .line 738
    throw p2

    .line 709
    :cond_6
    new-instance p0, Ljava/lang/Exception;

    invoke-virtual {p3}, Lcom/exteragram/messenger/plugins/Plugin;->getId()Ljava/lang/String;

    move-result-object p2

    new-instance p3, Ljava/lang/StringBuilder;

    const-string p4, "Plugin ID mismatch. Expected: "

    invoke-direct {p3, p4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, ", but found: "

    invoke-virtual {p3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " in metadata."

    invoke-virtual {p3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/lang/Exception;-><init>(Ljava/lang/String;)V

    throw p0

    .line 707
    :cond_7
    const-string p0, "Required value was null."

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 695
    :cond_8
    new-instance p0, Ljava/lang/Exception;

    new-instance p1, Ljava/lang/StringBuilder;

    const-string p3, "Plugin file not found: "

    invoke-direct {p1, p3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/lang/Exception;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public final loadPluginFromFile(Ljava/lang/String;Lcom/exteragram/messenger/plugins/Plugin;Lorg/telegram/messenger/Utilities$Callback;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Lcom/exteragram/messenger/plugins/Plugin;",
            "Lorg/telegram/messenger/Utilities$Callback<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    const/4 v0, 0x0

    .line 1159
    invoke-virtual {p0, p1, p2, p3, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->loadPluginFromFile(Ljava/lang/String;Lcom/exteragram/messenger/plugins/Plugin;Lorg/telegram/messenger/Utilities$Callback;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)V

    return-void
.end method

.method public loadPluginFromFile(Ljava/lang/String;Lcom/exteragram/messenger/plugins/Plugin;Lorg/telegram/messenger/Utilities$Callback;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)V
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Lcom/exteragram/messenger/plugins/Plugin;",
            "Lorg/telegram/messenger/Utilities$Callback<",
            "Ljava/lang/String;",
            ">;",
            "Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;",
            ")V"
        }
    .end annotation

    .line 1168
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    new-instance v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda9;

    move-object v3, p0

    move-object v4, p1

    move-object v2, p2

    move-object v6, p3

    move-object v5, p4

    invoke-direct/range {v1 .. v6}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda9;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Lcom/exteragram/messenger/plugins/PythonPluginsEngine;Ljava/lang/String;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;Lorg/telegram/messenger/Utilities$Callback;)V

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->runOnPluginsQueue(Ljava/lang/Runnable;)V

    return-void
.end method

.method public loadPluginSettings(Ljava/lang/String;)Ljava/util/List;
    .locals 4
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

    const/4 v0, 0x0

    .line 1644
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v1

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/plugins/Plugin;

    .line 1645
    iget-object v2, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->pluginInstances:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v2, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/chaquo/python/PyObject;

    .line 1646
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v3

    invoke-virtual {v3, v1}, Lcom/exteragram/messenger/plugins/PluginsController;->isPluginActive$TMessagesProj(Lcom/exteragram/messenger/plugins/Plugin;)Z

    move-result v1

    if-eqz v1, :cond_3

    if-nez v2, :cond_0

    goto :goto_0

    .line 1652
    :cond_0
    const-string p1, "create_settings"

    const/4 v1, 0x0

    new-array v1, v1, [Ljava/lang/Object;

    invoke-virtual {v2, p1, v1}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p1

    if-nez p1, :cond_1

    return-object v0

    .line 1653
    :cond_1
    invoke-virtual {p1}, Lcom/chaquo/python/PyObject;->asList()Ljava/util/List;

    move-result-object p1

    .line 1654
    invoke-interface {p1}, Ljava/util/List;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_2

    return-object v0

    .line 1657
    :cond_2
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->parsePySettingDefinitions(Ljava/util/List;)Ljava/util/List;

    move-result-object p0

    return-object p0

    :catch_0
    move-exception p0

    goto :goto_1

    .line 1647
    :cond_3
    :goto_0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p0

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->invalidatePluginSettings(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    .line 1659
    :goto_1
    const-string p1, "Failed to load plugin settings"

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-object v0
.end method

.method public final loadPlugins(Ljava/lang/Runnable;)V
    .locals 2

    .line 587
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    new-instance v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda22;

    invoke-direct {v1, p0, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda22;-><init>(Lcom/exteragram/messenger/plugins/PythonPluginsEngine;Ljava/lang/Runnable;)V

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->runOnPluginsQueue(Ljava/lang/Runnable;)V

    return-void
.end method

.method public openInExternalApp(Ljava/lang/String;)V
    .locals 3

    .line 1106
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->getSafeLastFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 1107
    :cond_0
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginPath(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 1108
    new-instance v1, Ljava/io/File;

    invoke-direct {v1, p0}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 1109
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result p0

    if-eqz p0, :cond_1

    .line 1110
    sget-object p0, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;->INSTANCE:Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, ".plugin"

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, v0, v1, p1}, Lcom/exteragram/messenger/plugins/ui/components/PluginFileViewer;->open(Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/io/File;Ljava/lang/String;)Z

    :cond_1
    :goto_0
    return-void
.end method

.method public openPluginSetting(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;Lorg/telegram/ui/ActionBar/BaseFragment;)V
    .locals 2

    .line 2123
    sget-object v0, Lcom/exteragram/messenger/plugins/PluginsController;->Companion:Lcom/exteragram/messenger/plugins/PluginsController$Companion;

    new-instance v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda12;

    invoke-direct {v1, p0, p1, p2, p3}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda12;-><init>(Lcom/exteragram/messenger/plugins/PythonPluginsEngine;Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;Lorg/telegram/ui/ActionBar/BaseFragment;)V

    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/plugins/PluginsController$Companion;->runOnPluginsQueue(Ljava/lang/Runnable;)V

    return-void
.end method

.method public openPluginSetting(Ljava/lang/String;Ljava/lang/String;Lorg/telegram/ui/ActionBar/BaseFragment;)V
    .locals 1

    .line 2173
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/exteragram/messenger/plugins/Plugin;

    if-eqz p1, :cond_0

    .line 2175
    invoke-virtual {p0, p1, p2, p3}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->openPluginSetting(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;Lorg/telegram/ui/ActionBar/BaseFragment;)V

    :cond_0
    return-void
.end method

.method public openPluginSettings(Lcom/exteragram/messenger/plugins/Plugin;Lorg/telegram/ui/ActionBar/BaseFragment;)V
    .locals 0

    .line 2117
    new-instance p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda5;

    invoke-direct {p0, p2, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda5;-><init>(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/Plugin;)V

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public openPluginSettings(Ljava/lang/String;Lorg/telegram/ui/ActionBar/BaseFragment;)V
    .locals 1

    .line 2110
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Lcom/exteragram/messenger/plugins/Plugin;

    if-eqz p1, :cond_0

    .line 2112
    invoke-virtual {p0, p1, p2}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->openPluginSettings(Lcom/exteragram/messenger/plugins/Plugin;Lorg/telegram/ui/ActionBar/BaseFragment;)V

    :cond_0
    return-void
.end method

.method public final parsePluginMetadata(Ljava/lang/String;)Ljava/util/Map;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 1976
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    if-nez p1, :cond_0

    goto/16 :goto_1

    .line 1981
    :cond_0
    new-instance v1, Ljava/io/File;

    invoke-direct {v1, p1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 1982
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v2

    if-eqz v2, :cond_3

    invoke-virtual {v1}, Ljava/io/File;->isFile()Z

    move-result v1

    if-nez v1, :cond_1

    goto :goto_1

    .line 1986
    :cond_1
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPython()Lcom/chaquo/python/Python;

    move-result-object p0

    if-nez p0, :cond_2

    .line 1988
    const-string p0, "Python engine not initialized, cannot parse metadata for "

    invoke-virtual {p0, p1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;)V

    return-object v0

    .line 1993
    :cond_2
    :try_start_0
    const-string v1, "extera_utils.metadata_parser"

    invoke-virtual {p0, v1}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object p0

    .line 1994
    const-string/jumbo v1, "get_metadata"

    filled-new-array {p1}, [Ljava/lang/Object;

    move-result-object v2

    invoke-virtual {p0, v1, v2}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p0

    if-eqz p0, :cond_3

    .line 1996
    invoke-virtual {p0}, Lcom/chaquo/python/PyObject;->asMap()Ljava/util/Map;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object p0

    invoke-interface {p0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_3

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/chaquo/python/PyObject;

    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/chaquo/python/PyObject;

    .line 1997
    invoke-virtual {v2}, Lcom/chaquo/python/PyObject;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1}, Lcom/chaquo/python/PyObject;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-interface {v0, v2, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catch Lcom/chaquo/python/PyException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    :catch_0
    move-exception p0

    .line 2001
    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Failed to parse metadata from "

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, ". Error: "

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 2002
    throw p0

    :cond_3
    :goto_1
    return-object v0
.end method

.method public final parsePySettingDefinitions(Ljava/util/List;)Ljava/util/List;
    .locals 21
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "+",
            "Lcom/chaquo/python/PyObject;",
            ">;)",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/models/SettingItem;",
            ">;"
        }
    .end annotation

    .line 1442
    new-instance v0, Ljava/util/ArrayList;

    invoke-interface/range {p1 .. p1}, Ljava/util/List;->size()I

    move-result v1

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(I)V

    .line 1443
    invoke-interface/range {p1 .. p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_15

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/chaquo/python/PyObject;

    .line 1444
    const-string/jumbo v3, "type"

    const/4 v4, 0x0

    invoke-static {v2, v3, v4}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getString(Lcom/chaquo/python/PyObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    if-nez v3, :cond_1

    .line 1446
    const-string v2, "A setting item in a plugin is missing its \'type\'. Skipping."

    invoke-static {v2}, Lorg/telegram/messenger/FileLog;->w(Ljava/lang/String;)V

    goto :goto_0

    .line 1450
    :cond_1
    const-string/jumbo v5, "key"

    invoke-static {v2, v5, v4}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getString(Lcom/chaquo/python/PyObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    .line 1451
    const-string/jumbo v5, "text"

    invoke-static {v2, v5, v4}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getString(Lcom/chaquo/python/PyObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    .line 1453
    const-string/jumbo v6, "subtext"

    invoke-static {v2, v6, v4}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getString(Lcom/chaquo/python/PyObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v10

    .line 1454
    const-string/jumbo v6, "icon"

    invoke-static {v2, v6, v4}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getString(Lcom/chaquo/python/PyObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v11

    .line 1455
    const-string/jumbo v6, "on_change"

    invoke-virtual {v2, v6}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    move-object v12, v6

    check-cast v12, Lcom/chaquo/python/PyObject;

    .line 1456
    const-string/jumbo v6, "on_long_click"

    invoke-virtual {v2, v6}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    move-object/from16 v17, v6

    check-cast v17, Lcom/chaquo/python/PyObject;

    .line 1458
    const-string/jumbo v6, "link_alias"

    invoke-static {v2, v6, v4}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getString(Lcom/chaquo/python/PyObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v14

    .line 1459
    const-string v6, "default"

    invoke-virtual {v2, v6}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lcom/chaquo/python/PyObject;

    .line 1460
    const-string/jumbo v9, "on_click"

    invoke-virtual {v2, v9}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v9

    move-object v15, v9

    check-cast v15, Lcom/chaquo/python/PyObject;

    .line 1461
    const-string v9, "create_sub_fragment"

    invoke-virtual {v2, v9}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v9

    move-object/from16 v16, v9

    check-cast v16, Lcom/chaquo/python/PyObject;

    .line 1463
    invoke-virtual {v3}, Ljava/lang/String;->hashCode()I

    move-result v9

    const-string v13, ""

    const/4 v4, 0x0

    sparse-switch v9, :sswitch_data_0

    goto/16 :goto_6

    :sswitch_0
    const-string v2, "divider"

    invoke-virtual {v3, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_2

    goto/16 :goto_6

    .line 1564
    :cond_2
    new-instance v4, Lcom/exteragram/messenger/plugins/models/DividerSetting;

    invoke-direct {v4, v8}, Lcom/exteragram/messenger/plugins/models/DividerSetting;-><init>(Ljava/lang/String;)V

    goto/16 :goto_a

    .line 1463
    :sswitch_1
    const-string/jumbo v4, "selector"

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_3

    goto/16 :goto_6

    .line 1487
    :cond_3
    const-string/jumbo v3, "items"

    const/4 v4, 0x0

    invoke-static {v2, v3, v4}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getStringArray(Lcom/chaquo/python/PyObject;Ljava/lang/String;[Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v10

    if-eqz v7, :cond_11

    if-eqz v8, :cond_11

    if-eqz v10, :cond_11

    .line 1488
    array-length v2, v10

    if-nez v2, :cond_4

    goto/16 :goto_6

    :cond_4
    if-eqz v6, :cond_11

    move-object v5, v6

    .line 1489
    new-instance v6, Lcom/exteragram/messenger/plugins/models/SelectorSetting;

    .line 1492
    invoke-virtual {v5}, Lcom/chaquo/python/PyObject;->toInt()I

    move-result v9

    move-object/from16 v13, v17

    .line 1489
    invoke-direct/range {v6 .. v14}, Lcom/exteragram/messenger/plugins/models/SelectorSetting;-><init>(Ljava/lang/String;Ljava/lang/String;I[Ljava/lang/String;Ljava/lang/String;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V

    :cond_5
    :goto_1
    move-object v4, v6

    goto/16 :goto_a

    :sswitch_2
    move-object v5, v6

    .line 1463
    const-string/jumbo v2, "input"

    invoke-virtual {v3, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_6

    goto/16 :goto_6

    :cond_6
    if-eqz v7, :cond_11

    if-eqz v8, :cond_11

    .line 1506
    new-instance v6, Lcom/exteragram/messenger/plugins/models/InputSetting;

    if-eqz v5, :cond_8

    .line 1509
    invoke-virtual {v5}, Lcom/chaquo/python/PyObject;->toString()Ljava/lang/String;

    move-result-object v2

    if-nez v2, :cond_7

    goto :goto_3

    :cond_7
    move-object v9, v2

    :goto_2
    move-object/from16 v13, v17

    goto :goto_4

    :cond_8
    :goto_3
    move-object v9, v13

    goto :goto_2

    .line 1506
    :goto_4
    invoke-direct/range {v6 .. v14}, Lcom/exteragram/messenger/plugins/models/InputSetting;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V

    goto :goto_1

    .line 1463
    :sswitch_3
    invoke-virtual {v3, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_9

    goto/16 :goto_6

    .line 1545
    :cond_9
    const-string v3, "accent"

    invoke-static {v2, v3, v4}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getBoolean(Lcom/chaquo/python/PyObject;Ljava/lang/String;Z)Z

    move-result v12

    .line 1546
    const-string/jumbo v3, "red"

    invoke-static {v2, v3, v4}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getBoolean(Lcom/chaquo/python/PyObject;Ljava/lang/String;Z)Z

    move-result v13

    if-eqz v8, :cond_11

    move-object v9, v8

    .line 1548
    new-instance v8, Lcom/exteragram/messenger/plugins/models/TextSetting;

    move-object/from16 v20, v17

    move-object/from16 v17, v14

    move-object v14, v15

    move-object/from16 v15, v16

    move-object/from16 v16, v20

    invoke-direct/range {v8 .. v17}, Lcom/exteragram/messenger/plugins/models/TextSetting;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V

    move-object v4, v8

    goto/16 :goto_a

    :sswitch_4
    move-object v5, v6

    .line 1463
    const-string/jumbo v2, "switch"

    invoke-virtual {v3, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_a

    goto/16 :goto_6

    :cond_a
    if-eqz v7, :cond_11

    if-eqz v8, :cond_11

    if-eqz v5, :cond_11

    .line 1470
    new-instance v6, Lcom/exteragram/messenger/plugins/models/SwitchSetting;

    .line 1473
    invoke-virtual {v5}, Lcom/chaquo/python/PyObject;->toBoolean()Z

    move-result v9

    move-object/from16 v13, v17

    .line 1470
    invoke-direct/range {v6 .. v14}, Lcom/exteragram/messenger/plugins/models/SwitchSetting;-><init>(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V

    goto :goto_1

    .line 1463
    :sswitch_5
    const-string/jumbo v2, "header"

    invoke-virtual {v3, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_b

    goto/16 :goto_6

    :cond_b
    if-eqz v8, :cond_11

    .line 1465
    new-instance v4, Lcom/exteragram/messenger/plugins/models/HeaderSetting;

    invoke-direct {v4, v8}, Lcom/exteragram/messenger/plugins/models/HeaderSetting;-><init>(Ljava/lang/String;)V

    goto/16 :goto_a

    .line 1463
    :sswitch_6
    const-string v4, "custom"

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_c

    goto/16 :goto_6

    .line 1566
    :cond_c
    const-string/jumbo v3, "view"

    invoke-virtual {v2, v3}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/chaquo/python/PyObject;

    .line 1567
    const-string/jumbo v4, "item"

    invoke-virtual {v2, v4}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/chaquo/python/PyObject;

    .line 1568
    const-string v5, "factory"

    invoke-virtual {v2, v5}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/chaquo/python/PyObject;

    .line 1569
    const-string v6, "factory_args"

    invoke-virtual {v2, v6}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/chaquo/python/PyObject;

    if-eqz v5, :cond_f

    .line 1574
    const-class v3, Lcom/exteragram/messenger/plugins/models/CustomSetting$Factory;

    .line 1572
    invoke-static {v5, v3}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->toJavaCompat(Lcom/chaquo/python/PyObject;Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/exteragram/messenger/plugins/models/CustomSetting$Factory;

    if-nez v3, :cond_d

    goto :goto_6

    :cond_d
    if-nez v2, :cond_e

    .line 1579
    new-instance v13, Lcom/exteragram/messenger/plugins/models/CustomSetting;

    move-object/from16 v18, v14

    move-object v14, v3

    invoke-direct/range {v13 .. v18}, Lcom/exteragram/messenger/plugins/models/CustomSetting;-><init>(Lcom/exteragram/messenger/plugins/models/CustomSetting$Factory;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V

    :goto_5
    move-object v4, v13

    goto/16 :goto_a

    .line 1587
    :cond_e
    new-instance v13, Lcom/exteragram/messenger/plugins/models/CustomSetting;

    move-object/from16 v19, v14

    move-object/from16 v18, v17

    move-object v14, v3

    move-object/from16 v17, v16

    move-object/from16 v16, v15

    move-object v15, v2

    invoke-direct/range {v13 .. v19}, Lcom/exteragram/messenger/plugins/models/CustomSetting;-><init>(Lcom/exteragram/messenger/plugins/models/CustomSetting$Factory;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V

    goto :goto_5

    :cond_f
    if-eqz v4, :cond_10

    .line 1599
    const-class v2, Lorg/telegram/ui/Components/UItem;

    invoke-static {v4, v2}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->toJavaCompat(Lcom/chaquo/python/PyObject;Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lorg/telegram/ui/Components/UItem;

    if-eqz v2, :cond_11

    .line 1601
    new-instance v13, Lcom/exteragram/messenger/plugins/models/CustomSetting;

    move-object/from16 v18, v14

    move-object v14, v2

    invoke-direct/range {v13 .. v18}, Lcom/exteragram/messenger/plugins/models/CustomSetting;-><init>(Lorg/telegram/ui/Components/UItem;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V

    goto :goto_5

    :cond_10
    if-eqz v3, :cond_11

    .line 1614
    const-class v2, Landroid/view/View;

    invoke-static {v3, v2}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->toJavaCompat(Lcom/chaquo/python/PyObject;Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Landroid/view/View;

    if-eqz v2, :cond_11

    .line 1616
    new-instance v13, Lcom/exteragram/messenger/plugins/models/CustomSetting;

    move-object/from16 v18, v14

    move-object v14, v2

    invoke-direct/range {v13 .. v18}, Lcom/exteragram/messenger/plugins/models/CustomSetting;-><init>(Landroid/view/View;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Lcom/chaquo/python/PyObject;Ljava/lang/String;)V

    goto :goto_5

    :sswitch_7
    move-object v5, v6

    .line 1463
    const-string v6, "edit_text"

    invoke-virtual {v3, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_12

    :cond_11
    :goto_6
    const/4 v4, 0x0

    goto :goto_a

    .line 1522
    :cond_12
    const-string/jumbo v3, "hint"

    const/4 v6, 0x0

    invoke-static {v2, v3, v6}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getString(Lcom/chaquo/python/PyObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    .line 1524
    const-string/jumbo v3, "multiline"

    invoke-static {v2, v3, v4}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getBoolean(Lcom/chaquo/python/PyObject;Ljava/lang/String;Z)Z

    move-result v10

    .line 1526
    const-string/jumbo v3, "max_length"

    const/16 v4, 0x100

    invoke-static {v2, v3, v4}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getInt(Lcom/chaquo/python/PyObject;Ljava/lang/String;I)I

    move-result v11

    .line 1527
    const-string/jumbo v3, "mask"

    invoke-static {v2, v3, v6}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getString(Lcom/chaquo/python/PyObject;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    if-eqz v7, :cond_5

    if-eqz v8, :cond_5

    .line 1529
    new-instance v6, Lcom/exteragram/messenger/plugins/models/EditTextSetting;

    if-eqz v5, :cond_14

    .line 1532
    invoke-virtual {v5}, Lcom/chaquo/python/PyObject;->toString()Ljava/lang/String;

    move-result-object v3

    if-nez v3, :cond_13

    goto :goto_8

    :cond_13
    move-object v9, v3

    :goto_7
    move-object v13, v12

    move-object v12, v2

    goto :goto_9

    :cond_14
    :goto_8
    move-object v9, v13

    goto :goto_7

    .line 1529
    :goto_9
    invoke-direct/range {v6 .. v13}, Lcom/exteragram/messenger/plugins/models/EditTextSetting;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZILjava/lang/String;Lcom/chaquo/python/PyObject;)V

    goto/16 :goto_1

    :goto_a
    if-eqz v4, :cond_0

    .line 1636
    invoke-virtual {v0, v4}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto/16 :goto_0

    :cond_15
    return-object v0

    nop

    :sswitch_data_0
    .sparse-switch
        -0x6f3939be -> :sswitch_7
        -0x5069748f -> :sswitch_6
        -0x48cb1d73 -> :sswitch_5
        -0x350448cc -> :sswitch_4
        0x36452d -> :sswitch_3
        0x5fb57ca -> :sswitch_2
        0x4705f3df -> :sswitch_1
        0x63cc1319 -> :sswitch_0
    .end sparse-switch
.end method

.method public final setBasePluginClass(Lcom/chaquo/python/PyObject;)V
    .locals 0

    .line 189
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->basePluginClass:Lcom/chaquo/python/PyObject;

    return-void
.end method

.method public final setDebuggerListener(Lcom/chaquo/python/PyObject;)V
    .locals 0

    .line 187
    iput-object p1, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->debuggerListener:Lcom/chaquo/python/PyObject;

    return-void
.end method

.method public setPluginEnabled(Ljava/lang/String;ZLorg/telegram/messenger/Utilities$Callback;)V
    .locals 9
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

    const-string/jumbo v0, "plugin_enabled_"

    const-string v1, "Failed to create plugin instance: "

    const-string v2, "Plugin not found: "

    const/4 v3, 0x0

    .line 1006
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v4

    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v4

    invoke-virtual {v4, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/exteragram/messenger/plugins/Plugin;

    if-eqz v4, :cond_7

    if-eqz p2, :cond_0

    .line 1008
    invoke-static {}, Lcom/exteragram/messenger/ExteraConfig;->getPluginsSafeMode()Z

    move-result v2

    if-eqz v2, :cond_0

    if-eqz p3, :cond_9

    .line 1010
    new-instance v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda14;

    invoke-direct {v1, p3}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda14;-><init>(Lorg/telegram/messenger/Utilities$Callback;)V

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    :catchall_0
    move-exception v1

    goto/16 :goto_3

    .line 1017
    :cond_0
    iget-object v2, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->pluginInstances:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v2, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/chaquo/python/PyObject;

    const/4 v5, 0x0

    if-eqz p2, :cond_2

    if-nez v2, :cond_2

    .line 1019
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginPath(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-direct {p0, p1, v2, v4, v5}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->createPluginInstance(Ljava/lang/String;Ljava/lang/String;Lcom/exteragram/messenger/plugins/Plugin;Lcom/exteragram/messenger/plugins/pip/PipController$InstallerDelegate;)V

    .line 1020
    iget-object v2, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->pluginInstances:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v2, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/chaquo/python/PyObject;

    if-eqz v2, :cond_1

    goto :goto_0

    .line 1021
    :cond_1
    new-instance v2, Ljava/lang/Exception;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v2, v1}, Ljava/lang/Exception;-><init>(Ljava/lang/String;)V

    throw v2
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 1024
    :cond_2
    :goto_0
    const-string/jumbo v1, "initialized"

    const/4 v6, 0x1

    if-eqz v2, :cond_3

    .line 1025
    :try_start_1
    invoke-static {v2, v1, v3}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getBoolean(Lcom/chaquo/python/PyObject;Ljava/lang/String;Z)Z

    move-result v7

    if-eqz v7, :cond_3

    move v7, v6

    goto :goto_1

    :cond_3
    move v7, v3

    :goto_1
    if-ne v7, p2, :cond_4

    .line 1026
    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/Plugin;->hasError()Z

    move-result v7

    if-nez v7, :cond_4

    if-eqz p3, :cond_9

    .line 1027
    invoke-interface {p3, v5}, Lorg/telegram/messenger/Utilities$Callback;->run(Ljava/lang/Object;)V

    return-void

    :cond_4
    if-eqz p2, :cond_6

    if-eqz v2, :cond_5

    .line 1033
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v7

    invoke-virtual {v7, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->cleanupPlugin(Ljava/lang/String;)V

    .line 1034
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v7

    invoke-virtual {v7}, Lcom/exteragram/messenger/plugins/PluginsController;->getWatchdog()Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    move-result-object v7

    invoke-virtual {v7, p1}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionStarted(Ljava/lang/String;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 1036
    :try_start_2
    const-string/jumbo v7, "on_plugin_load"

    new-array v8, v3, [Ljava/lang/Object;

    invoke-virtual {v2, v7, v8}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    .line 1038
    :try_start_3
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v7

    invoke-virtual {v7}, Lcom/exteragram/messenger/plugins/PluginsController;->getWatchdog()Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    move-result-object v7

    invoke-virtual {v7, p1}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionFinished(Ljava/lang/String;)V

    .line 1040
    sget-object v7, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;

    invoke-virtual {v2, v1, v7}, Lcom/chaquo/python/PyObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    .line 1041
    const-string v1, "error_message"

    invoke-interface {v2, v1, v5}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 1042
    invoke-virtual {v4, v5}, Lcom/exteragram/messenger/plugins/Plugin;->setError(Ljava/lang/Throwable;)V

    .line 1043
    const-string v1, "enabled"

    invoke-virtual {v2, v1, v7}, Lcom/chaquo/python/PyObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    .line 1044
    invoke-virtual {v4, v6}, Lcom/exteragram/messenger/plugins/Plugin;->setEnabled(Z)V

    .line 1045
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v1

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v1

    .line 45
    invoke-interface {v1}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v1

    .line 1046
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-interface {v1, v2, v6}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 50
    invoke-interface {v1}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 1048
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v1

    invoke-virtual {v1, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->loadPluginSettings(Ljava/lang/String;)V

    goto :goto_2

    :catchall_1
    move-exception v1

    .line 1038
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v2

    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/PluginsController;->getWatchdog()Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    move-result-object v2

    invoke-virtual {v2, p1}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionFinished(Ljava/lang/String;)V

    throw v1

    .line 1032
    :cond_5
    const-string v1, "Required value was null."

    new-instance v2, Ljava/lang/IllegalArgumentException;

    invoke-direct {v2, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v2

    .line 1050
    :cond_6
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v1

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/PluginsController;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object v1

    .line 45
    invoke-interface {v1}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v1

    .line 1051
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 50
    invoke-interface {v1}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 1053
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->unloadPlugin(Ljava/lang/String;)V

    .line 1056
    :goto_2
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v1

    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/PluginsController;->notifyPluginsChanged()V

    if-eqz p3, :cond_9

    .line 1058
    new-instance v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda15;

    invoke-direct {v1, p3}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda15;-><init>(Lorg/telegram/messenger/Utilities$Callback;)V

    invoke-static {v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void

    .line 1007
    :cond_7
    new-instance v1, Ljava/lang/Exception;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v1, v2}, Ljava/lang/Exception;-><init>(Ljava/lang/String;)V

    throw v1
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 1061
    :goto_3
    new-instance v2, Ljava/lang/StringBuilder;

    const-string v4, "Unexpected error setting enabled state for "

    invoke-direct {v2, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2, v1}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    if-eqz p2, :cond_8

    .line 1063
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p2

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object p2

    invoke-virtual {p2, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lcom/exteragram/messenger/plugins/Plugin;

    if-eqz p2, :cond_8

    invoke-virtual {p2, v1}, Lcom/exteragram/messenger/plugins/Plugin;->setError(Ljava/lang/Throwable;)V

    .line 1065
    :cond_8
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p2

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/PluginsController;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object p2

    .line 45
    invoke-interface {p2}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object p2

    .line 1066
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-interface {p2, v0, v3}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 50
    invoke-interface {p2}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 1068
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->unloadPlugin(Ljava/lang/String;)V

    if-eqz p3, :cond_9

    .line 1070
    new-instance p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda16;

    invoke-direct {p0, p3, v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda16;-><init>(Lorg/telegram/messenger/Utilities$Callback;Ljava/lang/Throwable;)V

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    :cond_9
    return-void
.end method

.method public setPluginSetting(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V
    .locals 3

    .line 2036
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->settingsCache:Ljava/util/concurrent/ConcurrentHashMap;

    new-instance v1, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda0;

    invoke-direct {v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda0;-><init>()V

    new-instance v2, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda1;

    invoke-direct {v2, v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda1;-><init>(Lkotlin/jvm/functions/Function1;)V

    invoke-virtual {v0, p1, v2}, Ljava/util/concurrent/ConcurrentHashMap;->computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/util/Map;

    invoke-interface {v0, p2, p3}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 2037
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPython()Lcom/chaquo/python/Python;

    move-result-object p0

    if-nez p0, :cond_0

    return-void

    .line 2039
    :cond_0
    :try_start_0
    const-string/jumbo v0, "plugin_settings"

    invoke-virtual {p0, v0}, Lcom/chaquo/python/Python;->getModule(Ljava/lang/String;)Lcom/chaquo/python/PyObject;

    move-result-object p0

    .line 2040
    const-string/jumbo v0, "set_setting"

    filled-new-array {p1, p2, p3}, [Ljava/lang/Object;

    move-result-object p3

    invoke-virtual {p0, v0, p3}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    :try_end_0
    .catch Lcom/chaquo/python/PyException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 2042
    new-instance p3, Ljava/lang/StringBuilder;

    const-string v0, "Failed to set plugin setting "

    invoke-direct {p3, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, "/"

    invoke-virtual {p3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method public sharePlugin(Ljava/lang/String;)V
    .locals 5

    .line 1115
    invoke-static {}, Lorg/telegram/ui/LaunchActivity;->getSafeLastFragment()Lorg/telegram/ui/ActionBar/BaseFragment;

    move-result-object v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 1116
    :cond_0
    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getParentActivity()Landroid/app/Activity;

    move-result-object v1

    if-nez v1, :cond_1

    :goto_0
    return-void

    .line 1117
    :cond_1
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginPath(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    .line 1118
    new-instance v2, Ljava/io/File;

    invoke-static {}, Lorg/telegram/messenger/ApplicationLoader;->getFilesDirFixed()Ljava/io/File;

    move-result-object v3

    const-string/jumbo v4, "temp"

    invoke-direct {v2, v3, v4}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 1119
    invoke-static {v2}, Lorg/simplifiles/SimpliFiles;->directory(Ljava/io/File;)Lorg/simplifiles/files/SimpliDirectory;

    move-result-object v3

    invoke-virtual {v3}, Lorg/simplifiles/files/SimpliDirectory;->create()Lorg/simplifiles/files/SimpliDirectory;

    .line 1121
    new-instance v3, Ljava/io/File;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, ".plugin"

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-direct {v3, v2, p1}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 1123
    :try_start_0
    invoke-static {p0}, Lorg/simplifiles/SimpliFiles;->file(Ljava/lang/String;)Lorg/simplifiles/files/SimpliFile;

    move-result-object p0

    sget-object p1, Lorg/simplifiles/files/OverwritePolicy;->REPLACE:Lorg/simplifiles/files/OverwritePolicy;

    invoke-virtual {p0, v3, p1}, Lorg/simplifiles/files/SimpliFile;->copyTo(Ljava/io/File;Lorg/simplifiles/files/OverwritePolicy;)Lorg/simplifiles/files/SimpliFile;

    .line 1126
    invoke-static {}, Lorg/telegram/messenger/ApplicationLoader;->getApplicationId()Ljava/lang/String;

    move-result-object p0

    new-instance p1, Ljava/lang/StringBuilder;

    invoke-direct {p1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ".provider"

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 1124
    invoke-static {v1, p0, v3}, Landroidx/core/content/FileProvider;->getUriForFile(Landroid/content/Context;Ljava/lang/String;Ljava/io/File;)Landroid/net/Uri;

    move-result-object p0
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 1140
    new-instance p1, Landroid/content/Intent;

    const-string v1, "android.intent.action.SEND"

    invoke-direct {p1, v1}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    const/4 v1, 0x1

    .line 1141
    invoke-virtual {p1, v1}, Landroid/content/Intent;->setFlags(I)Landroid/content/Intent;

    .line 1142
    const-string v1, "android.intent.extra.STREAM"

    invoke-virtual {p1, v1, p0}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;

    .line 1143
    const-string p0, "application/x-plugin"

    invoke-virtual {p1, p0}, Landroid/content/Intent;->setType(Ljava/lang/String;)Landroid/content/Intent;

    .line 1148
    sget p0, Lorg/telegram/messenger/R$string;->ShareFile:I

    invoke-static {p0}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object p0

    .line 1146
    invoke-static {p1, p0}, Landroid/content/Intent;->createChooser(Landroid/content/Intent;Ljava/lang/CharSequence;)Landroid/content/Intent;

    move-result-object p0

    const/16 p1, 0x1f4

    .line 1145
    invoke-virtual {v0, p0, p1}, Lorg/telegram/ui/ActionBar/BaseFragment;->startActivityForResult(Landroid/content/Intent;I)V

    .line 1151
    invoke-virtual {v3}, Ljava/io/File;->deleteOnExit()V

    return-void

    :catch_0
    move-exception p0

    .line 1136
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void

    :catch_1
    move-exception p0

    .line 1133
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void

    :catch_2
    move-exception p0

    .line 1130
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    return-void
.end method

.method public showInstallDialog(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;)V
    .locals 3

    .line 2082
    new-instance v0, Ljava/io/File;

    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getFilePath()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 2083
    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getFilePath()Ljava/lang/String;

    move-result-object v1

    const-string/jumbo v2, "name"

    invoke-virtual {p0, v1, v2}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->fetchParameterValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 2084
    invoke-static {v1}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_0

    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v2

    if-eqz v2, :cond_0

    .line 2085
    invoke-virtual {v0}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v1

    .line 2088
    :cond_0
    invoke-virtual {p2}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;->getFilePath()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->validatePluginFromFile(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;

    move-result-object p0

    .line 2089
    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;->getPlugin()Lcom/exteragram/messenger/plugins/Plugin;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 2090
    new-instance v0, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;

    invoke-direct {v0, p1, p0, p2}, Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet;-><init>(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;Lcom/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$PluginInstallParams;)V

    invoke-virtual {v0}, Lorg/telegram/ui/ActionBar/BottomSheet;->show()V

    return-void

    .line 2093
    :cond_1
    new-instance p2, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda17;

    invoke-direct {p2, p1, v1, p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$$ExternalSyntheticLambda17;-><init>(Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/lang/String;Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;)V

    invoke-static {p2}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public shutdown(Ljava/lang/Runnable;)V
    .locals 2

    .line 559
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPython()Lcom/chaquo/python/Python;

    move-result-object v0

    if-nez v0, :cond_0

    .line 560
    invoke-interface {p1}, Ljava/lang/Runnable;->run()V

    return-void

    .line 565
    :cond_0
    :try_start_0
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->stopDevServer()V

    .line 566
    new-instance v0, Ljava/util/ArrayList;

    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->pluginInstances:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v1}, Ljava/util/concurrent/ConcurrentHashMap;->keySet()Ljava/util/Set;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/util/ArrayList;-><init>(Ljava/util/Collection;)V

    invoke-virtual {v0}, Ljava/util/ArrayList;->iterator()Ljava/util/Iterator;

    move-result-object v0

    :goto_0
    invoke-interface {v0}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {v0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    .line 567
    invoke-virtual {p0, v1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->unloadPlugin(Ljava/lang/String;)V

    goto :goto_0

    :catch_0
    move-exception p0

    goto :goto_1

    .line 570
    :cond_1
    iget-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->debuggerListener:Lcom/chaquo/python/PyObject;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lcom/chaquo/python/PyObject;->close()V

    :cond_2
    const/4 v0, 0x0

    .line 571
    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->debuggerListener:Lcom/chaquo/python/PyObject;

    .line 572
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->pluginInstances:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v1}, Ljava/util/concurrent/ConcurrentHashMap;->clear()V

    .line 573
    monitor-enter p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 574
    :try_start_1
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->removePluginPathsFromSysPath()V

    .line 575
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->stopAndUnloadSdk()V

    .line 576
    iput-object v0, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->python:Lcom/chaquo/python/Python;

    const/4 v0, 0x0

    .line 577
    sput-boolean v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->sdkInitialized:Z

    .line 578
    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 573
    :try_start_2
    monitor-exit p0

    .line 579
    const-string p0, "Python plugin engine shut down."

    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->d(Ljava/lang/String;)V

    goto :goto_2

    :catchall_0
    move-exception v0

    .line 573
    monitor-exit p0

    throw v0
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    .line 581
    :goto_1
    invoke-static {p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/Throwable;)V

    .line 583
    :goto_2
    invoke-interface {p1}, Ljava/lang/Runnable;->run()V

    return-void
.end method

.method public final unloadPlugin(Ljava/lang/String;)V
    .locals 11

    const-string v0, "Error during on_plugin_unload for "

    .line 936
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->settingsCache:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v1, p1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    .line 937
    iget-object v1, p0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->pluginInstances:Ljava/util/concurrent/ConcurrentHashMap;

    invoke-virtual {v1, p1}, Ljava/util/concurrent/ConcurrentHashMap;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/chaquo/python/PyObject;

    .line 938
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v2

    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/PluginsController;->getPlugins()Ljava/util/concurrent/ConcurrentHashMap;

    move-result-object v2

    invoke-virtual {v2, p1}, Ljava/util/concurrent/ConcurrentHashMap;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/exteragram/messenger/plugins/Plugin;

    const/4 v3, 0x0

    if-eqz v2, :cond_0

    invoke-virtual {v2, v3}, Lcom/exteragram/messenger/plugins/Plugin;->setEnabled(Z)V

    .line 940
    :cond_0
    const-string/jumbo v2, "initialized"

    const-string v4, "Failed to close plugin instance "

    const/4 v5, 0x0

    if-eqz v1, :cond_1

    :try_start_0
    invoke-static {v1, v2, v3}, Lcom/exteragram/messenger/plugins/utils/PyObjectUtils;->getBoolean(Lcom/chaquo/python/PyObject;Ljava/lang/String;Z)Z

    move-result v6

    if-eqz v6, :cond_1

    .line 941
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v6

    invoke-virtual {v6}, Lcom/exteragram/messenger/plugins/PluginsController;->getWatchdog()Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    move-result-object v6

    invoke-virtual {v6, p1}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionStarted(Ljava/lang/String;)V
    :try_end_0
    .catch Lcom/chaquo/python/PyException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 943
    :try_start_1
    const-string/jumbo v6, "on_plugin_unload"

    new-array v7, v3, [Ljava/lang/Object;

    invoke-virtual {v1, v6, v7}, Lcom/chaquo/python/PyObject;->callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    .line 947
    :try_start_2
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    :goto_0
    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController;->getWatchdog()Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    move-result-object v0

    invoke-virtual {v0, p1}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionFinished(Ljava/lang/String;)V
    :try_end_2
    .catch Lcom/chaquo/python/PyException; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    goto :goto_1

    :catchall_0
    move-exception v0

    goto/16 :goto_e

    :catch_0
    move-exception v0

    goto/16 :goto_9

    :catchall_1
    move-exception v6

    .line 945
    :try_start_3
    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v7, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0, v6}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_2

    .line 947
    :try_start_4
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    goto :goto_0

    :catchall_2
    move-exception v0

    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v2

    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/PluginsController;->getWatchdog()Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;

    move-result-object v2

    invoke-virtual {v2, p1}, Lcom/exteragram/messenger/plugins/utils/PluginsWatchdog;->onPluginExecutionFinished(Ljava/lang/String;)V

    throw v0

    :cond_1
    :goto_1
    if-eqz v1, :cond_2

    .line 950
    sget-object v0, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    invoke-virtual {v1, v2, v0}, Lcom/chaquo/python/PyObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    :cond_2
    if-eqz v1, :cond_3

    .line 951
    const-string v0, "enabled"

    sget-object v2, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    invoke-virtual {v1, v0, v2}, Lcom/chaquo/python/PyObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    .line 952
    :cond_3
    new-instance v0, Ljava/io/File;

    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v2

    invoke-virtual {v2}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginsDir()Ljava/io/File;

    move-result-object v2

    const-string v6, "__pycache__"

    invoke-direct {v0, v2, v6}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 953
    invoke-virtual {v0}, Ljava/io/File;->exists()Z

    move-result v2

    if-eqz v2, :cond_6

    invoke-virtual {v0}, Ljava/io/File;->isDirectory()Z

    move-result v2

    if-nez v2, :cond_4

    goto :goto_3

    :cond_4
    invoke-virtual {v0}, Ljava/io/File;->listFiles()[Ljava/io/File;

    move-result-object v0

    if-eqz v0, :cond_6

    array-length v2, v0

    move v6, v3

    :goto_2
    if-ge v6, v2, :cond_6

    aget-object v7, v0, v6

    invoke-virtual {v7}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v8

    new-instance v9, Ljava/lang/StringBuilder;

    invoke-direct {v9}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v9, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v10, ".cpython-"

    invoke-virtual {v9, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v9}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v9

    const/4 v10, 0x2

    invoke-static {v8, v9, v3, v10, v5}, Lkotlin/text/StringsKt;->startsWith$default(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/Object;)Z

    move-result v8

    if-eqz v8, :cond_5

    goto :goto_4

    :cond_5
    add-int/lit8 v6, v6, 0x1

    goto :goto_2

    :cond_6
    :goto_3
    move-object v7, v5

    :goto_4
    if-eqz v7, :cond_7

    .line 954
    sget-object v0, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->Companion:Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;

    invoke-static {v0, v7}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;->access$deleteFileIfExists(Lcom/exteragram/messenger/plugins/PythonPluginsEngine$Companion;Ljava/io/File;)V

    .line 955
    :cond_7
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object v0

    invoke-virtual {v0}, Lcom/exteragram/messenger/plugins/PluginsController;->getPluginsDir()Ljava/io/File;

    move-result-object v0

    invoke-direct {p0, p1, v0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->refreshImportCaches(Ljava/lang/String;Ljava/io/File;)V
    :try_end_4
    .catch Lcom/chaquo/python/PyException; {:try_start_4 .. :try_end_4} :catch_0
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    .line 959
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p0

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->cleanupPlugin(Ljava/lang/String;)V

    .line 960
    :try_start_5
    sget-object p0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    if-eqz v1, :cond_8

    invoke-virtual {v1}, Lcom/chaquo/python/PyObject;->close()V

    sget-object v5, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    goto :goto_5

    :catchall_3
    move-exception p0

    goto :goto_6

    :cond_8
    :goto_5
    invoke-static {v5}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_5
    .catchall {:try_start_5 .. :try_end_5} :catchall_3

    goto :goto_7

    :goto_6
    sget-object v0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {p0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 961
    :goto_7
    invoke-static {p0}, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;

    move-result-object p0

    if-eqz p0, :cond_a

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    :goto_8
    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    goto :goto_d

    .line 957
    :goto_9
    :try_start_6
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Failed to unload plugin "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2, v0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V
    :try_end_6
    .catchall {:try_start_6 .. :try_end_6} :catchall_0

    .line 959
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p0

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->cleanupPlugin(Ljava/lang/String;)V

    .line 960
    :try_start_7
    sget-object p0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    if-eqz v1, :cond_9

    invoke-virtual {v1}, Lcom/chaquo/python/PyObject;->close()V

    sget-object v5, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    goto :goto_a

    :catchall_4
    move-exception p0

    goto :goto_b

    :cond_9
    :goto_a
    invoke-static {v5}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_4

    goto :goto_c

    :goto_b
    sget-object v0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {p0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 961
    :goto_c
    invoke-static {p0}, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;

    move-result-object p0

    if-eqz p0, :cond_a

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    goto :goto_8

    :cond_a
    :goto_d
    return-void

    .line 959
    :goto_e
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p0

    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PluginsController;->cleanupPlugin(Ljava/lang/String;)V

    .line 960
    :try_start_8
    sget-object p0, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    if-eqz v1, :cond_b

    invoke-virtual {v1}, Lcom/chaquo/python/PyObject;->close()V

    sget-object v5, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    goto :goto_f

    :catchall_5
    move-exception p0

    goto :goto_10

    :cond_b
    :goto_f
    invoke-static {v5}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_8
    .catchall {:try_start_8 .. :try_end_8} :catchall_5

    goto :goto_11

    :goto_10
    sget-object v1, Lkotlin/Result;->Companion:Lkotlin/Result$Companion;

    invoke-static {p0}, Lkotlin/ResultKt;->createFailure(Ljava/lang/Throwable;)Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Lkotlin/Result;->constructor-impl(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    .line 961
    :goto_11
    invoke-static {p0}, Lkotlin/Result;->exceptionOrNull-impl(Ljava/lang/Object;)Ljava/lang/Throwable;

    move-result-object p0

    if-eqz p0, :cond_c

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_c
    throw v0
.end method

.method public final validatePluginFromFile(Ljava/lang/String;)Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;
    .locals 14

    const-string v0, "Plugin requires sdk version "

    const-string v1, "Plugin requires app version "

    const-string v2, "Plugin \'__id__\' is already used as a Python module name by "

    .line 1314
    new-instance v3, Ljava/io/File;

    invoke-direct {v3, p1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 1315
    invoke-virtual {v3}, Ljava/io/File;->exists()Z

    move-result v3

    const/4 v4, 0x0

    if-nez v3, :cond_0

    .line 1316
    new-instance p0, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;

    const-string p1, "Plugin file not found."

    invoke-direct {p0, v4, p1}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V

    return-object p0

    .line 1320
    :cond_0
    :try_start_0
    invoke-virtual {p0, p1}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->parsePluginMetadata(Ljava/lang/String;)Ljava/util/Map;

    move-result-object v3

    .line 1321
    const-string/jumbo v5, "id"

    invoke-interface {v3, v5}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/String;

    .line 1322
    const-string/jumbo v6, "name"

    invoke-interface {v3, v6}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    .line 1324
    invoke-static {v5}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v7

    if-nez v7, :cond_18

    invoke-static {v6}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v7
    :try_end_0
    .catch Lcom/chaquo/python/PyException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v7, :cond_1

    goto/16 :goto_5

    .line 1331
    :cond_1
    const-string v7, "Required value was null."

    if-eqz v5, :cond_17

    :try_start_1
    new-instance v8, Lkotlin/text/Regex;

    const-string v9, "^[a-zA-Z][a-zA-Z0-9_-]{1,31}$"

    invoke-direct {v8, v9}, Lkotlin/text/Regex;-><init>(Ljava/lang/String;)V

    invoke-virtual {v8, v5}, Lkotlin/text/Regex;->matches(Ljava/lang/CharSequence;)Z

    move-result v8

    if-nez v8, :cond_2

    .line 1332
    new-instance p0, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;

    .line 1334
    const-string v0, "Plugin \'__id__\' must be 2-32 characters long, start with a letter, and contain only latin letters, numbers, dashes and underscores."

    .line 1332
    invoke-direct {p0, v4, v0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V

    return-object p0

    :catchall_0
    move-exception p0

    goto/16 :goto_6

    :catch_0
    move-exception p0

    goto/16 :goto_7

    .line 1338
    :cond_2
    invoke-direct {p0, v5}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->findModuleNameOwner(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    if-eqz v8, :cond_3

    .line 1340
    new-instance p0, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;

    .line 1342
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ". Choose a different \'__id__\'."

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 1340
    invoke-direct {p0, v4, v0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V

    return-object p0

    .line 1346
    :cond_3
    const-string v2, "app_version"

    invoke-interface {v3, v2}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;
    :try_end_1
    .catch Lcom/chaquo/python/PyException; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 1347
    const-string v8, ", but current is "

    const/4 v9, 0x2

    const/4 v10, 0x1

    if-eqz v2, :cond_8

    .line 1348
    :try_start_2
    sget-object v11, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->VERSION_PATTERN:Ljava/util/regex/Pattern;

    invoke-virtual {v11, v2}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object v11

    .line 1349
    invoke-virtual {v11}, Ljava/util/regex/Matcher;->matches()Z

    move-result v12
    :try_end_2
    .catch Lcom/chaquo/python/PyException; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    const-string v13, "Invalid appVersion: "

    if-nez v12, :cond_4

    .line 1350
    :try_start_3
    new-instance p0, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;

    .line 1352
    invoke-virtual {v13, v2}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 1350
    invoke-direct {p0, v4, v0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V

    return-object p0

    .line 1355
    :cond_4
    invoke-virtual {v11, v10}, Ljava/util/regex/Matcher;->group(I)Ljava/lang/String;

    move-result-object v12

    if-nez v12, :cond_5

    new-instance p0, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;

    .line 1357
    invoke-virtual {v13, v2}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 1355
    invoke-direct {p0, v4, v0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V

    return-object p0

    .line 1359
    :cond_5
    invoke-virtual {v11, v9}, Ljava/util/regex/Matcher;->group(I)Ljava/lang/String;

    move-result-object v11

    if-eqz v11, :cond_7

    invoke-static {v11}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object v11

    invoke-virtual {v11}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v11

    if-nez v11, :cond_6

    goto :goto_0

    .line 1366
    :cond_6
    sget-object v13, Lorg/telegram/messenger/BuildVars;->BUILD_VERSION_STRING:Ljava/lang/String;

    .line 1364
    invoke-static {v12, v13, v11}, Lcom/exteragram/messenger/utils/AppUtils;->compareVersions(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z

    move-result v11

    if-nez v11, :cond_8

    .line 1370
    new-instance p0, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;

    .line 1372
    sget-object v0, Lorg/telegram/messenger/BuildVars;->BUILD_VERSION_STRING:Ljava/lang/String;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 1370
    invoke-direct {p0, v4, v0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V

    return-object p0

    .line 1360
    :cond_7
    :goto_0
    new-instance p0, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;

    .line 1362
    invoke-virtual {v13, v2}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 1360
    invoke-direct {p0, v4, v0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V

    return-object p0

    .line 1377
    :cond_8
    const-string/jumbo v1, "sdk_version"

    invoke-interface {v3, v1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    if-eqz v1, :cond_d

    .line 1379
    sget-object v11, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->VERSION_PATTERN:Ljava/util/regex/Pattern;

    invoke-virtual {v11, v1}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object v11

    .line 1380
    invoke-virtual {v11}, Ljava/util/regex/Matcher;->matches()Z

    move-result v12
    :try_end_3
    .catch Lcom/chaquo/python/PyException; {:try_start_3 .. :try_end_3} :catch_0
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    const-string v13, "Invalid sdkVersion: "

    if-nez v12, :cond_9

    .line 1381
    :try_start_4
    new-instance p0, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;

    .line 1383
    invoke-virtual {v13, v1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 1381
    invoke-direct {p0, v4, v0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V

    return-object p0

    .line 1386
    :cond_9
    invoke-virtual {v11, v10}, Ljava/util/regex/Matcher;->group(I)Ljava/lang/String;

    move-result-object v10

    if-nez v10, :cond_a

    new-instance p0, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;

    .line 1388
    invoke-virtual {v13, v1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 1386
    invoke-direct {p0, v4, v0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V

    return-object p0

    .line 1390
    :cond_a
    invoke-virtual {v11, v9}, Ljava/util/regex/Matcher;->group(I)Ljava/lang/String;

    move-result-object v9

    if-eqz v9, :cond_c

    invoke-static {v9}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object v9

    invoke-virtual {v9}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v9

    if-nez v9, :cond_b

    goto :goto_1

    .line 1395
    :cond_b
    sget-object v11, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_VERSION:Ljava/lang/String;

    invoke-static {v10, v11, v9}, Lcom/exteragram/messenger/utils/AppUtils;->compareVersions(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z

    move-result v9

    if-nez v9, :cond_d

    .line 1396
    new-instance p0, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;

    .line 1398
    sget-object v2, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->SDK_VERSION:Ljava/lang/String;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 1396
    invoke-direct {p0, v4, v0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V

    return-object p0

    .line 1391
    :cond_c
    :goto_1
    new-instance p0, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;

    .line 1393
    invoke-virtual {v13, v1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 1391
    invoke-direct {p0, v4, v0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V

    return-object p0

    .line 1403
    :cond_d
    new-instance v0, Lcom/exteragram/messenger/plugins/Plugin;

    if-eqz v6, :cond_16

    invoke-direct {v0, v5, v6}, Lcom/exteragram/messenger/plugins/Plugin;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    .line 1404
    const-string/jumbo v6, "python"

    invoke-virtual {v0, v6}, Lcom/exteragram/messenger/plugins/Plugin;->setEngine(Ljava/lang/String;)V

    .line 1406
    const-string v6, "author"

    invoke-interface {v3, v6}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    if-nez v6, :cond_e

    sget v6, Lorg/telegram/messenger/R$string;->PluginNoAuthor:I

    invoke-static {v6}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v6

    .line 1405
    :cond_e
    invoke-virtual {v0, v6}, Lcom/exteragram/messenger/plugins/Plugin;->setAuthor(Ljava/lang/String;)V

    .line 1409
    const-string v6, "description"

    invoke-interface {v3, v6}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    if-nez v6, :cond_f

    .line 1410
    sget v6, Lorg/telegram/messenger/R$string;->PluginNoDescription:I

    invoke-static {v6}, Lorg/telegram/messenger/LocaleController;->getString(I)Ljava/lang/String;

    move-result-object v6

    .line 1408
    :cond_f
    invoke-virtual {v0, v6}, Lcom/exteragram/messenger/plugins/Plugin;->setDescription(Ljava/lang/String;)V

    .line 1412
    const-string/jumbo v6, "icon"

    invoke-interface {v3, v6}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    invoke-virtual {v0, v6}, Lcom/exteragram/messenger/plugins/Plugin;->setIcon(Ljava/lang/String;)V

    .line 1413
    const-string/jumbo v6, "version"

    invoke-interface {v3, v6}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/String;

    if-nez v6, :cond_10

    const-string v6, "1.0"

    :cond_10
    invoke-virtual {v0, v6}, Lcom/exteragram/messenger/plugins/Plugin;->setVersion(Ljava/lang/String;)V

    .line 1414
    invoke-virtual {v0, v2}, Lcom/exteragram/messenger/plugins/Plugin;->setAppVersion(Ljava/lang/String;)V

    .line 1415
    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/plugins/Plugin;->setSdkVersion(Ljava/lang/String;)V

    .line 1417
    const-string/jumbo v1, "requirements"

    invoke-interface {v3, v1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    const/4 v2, 0x0

    if-eqz v1, :cond_15

    .line 1418
    invoke-interface {v1}, Ljava/lang/CharSequence;->length()I

    move-result v3

    if-nez v3, :cond_11

    goto :goto_4

    .line 1420
    :cond_11
    new-instance v3, Lkotlin/text/Regex;

    const-string v6, ",(?!\\s*[<>=!~(])"

    invoke-direct {v3, v6}, Lkotlin/text/Regex;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v1, v2}, Lkotlin/text/Regex;->split(Ljava/lang/CharSequence;I)Ljava/util/List;

    move-result-object v1

    check-cast v1, Ljava/lang/Iterable;

    .line 1586
    new-instance v3, Ljava/util/ArrayList;

    const/16 v6, 0xa

    invoke-static {v1, v6}, Lkotlin/collections/CollectionsKt;->collectionSizeOrDefault(Ljava/lang/Iterable;I)I

    move-result v6

    invoke-direct {v3, v6}, Ljava/util/ArrayList;-><init>(I)V

    .line 1661
    invoke-interface {v1}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_2
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v6

    if-eqz v6, :cond_12

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v6

    .line 1662
    check-cast v6, Ljava/lang/String;

    .line 1421
    invoke-static {v6}, Lkotlin/text/StringsKt;->trim(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v6

    .line 1662
    invoke-interface {v3, v6}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_2

    .line 777
    :cond_12
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 873
    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v6

    move v7, v2

    :cond_13
    :goto_3
    if-ge v7, v6, :cond_14

    invoke-virtual {v3, v7}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v8

    add-int/lit8 v7, v7, 0x1

    move-object v9, v8

    check-cast v9, Ljava/lang/String;

    .line 1422
    invoke-interface {v9}, Ljava/lang/CharSequence;->length()I

    move-result v9

    if-lez v9, :cond_13

    .line 873
    invoke-interface {v1, v8}, Ljava/util/Collection;->add(Ljava/lang/Object;)Z

    goto :goto_3

    .line 1419
    :cond_14
    invoke-virtual {v0, v1}, Lcom/exteragram/messenger/plugins/Plugin;->setRequirements(Ljava/util/List;)V

    .line 1426
    :cond_15
    :goto_4
    invoke-direct {p0}, Lcom/exteragram/messenger/plugins/PythonPluginsEngine;->getPluginsController()Lcom/exteragram/messenger/plugins/PluginsController;

    move-result-object p0

    invoke-virtual {p0}, Lcom/exteragram/messenger/plugins/PluginsController;->getPreferences()Landroid/content/SharedPreferences;

    move-result-object p0

    .line 1427
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string/jumbo v3, "plugin_enabled_"

    invoke-virtual {v1, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 1426
    invoke-interface {p0, v1, v2}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result p0

    .line 1425
    invoke-virtual {v0, p0}, Lcom/exteragram/messenger/plugins/Plugin;->setEnabled(Z)V

    .line 1431
    new-instance p0, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;

    invoke-direct {p0, v0, v4}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V

    return-object p0

    .line 1403
    :cond_16
    new-instance p0, Ljava/lang/IllegalArgumentException;

    invoke-direct {p0, v7}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 1331
    :cond_17
    new-instance p0, Ljava/lang/IllegalArgumentException;

    invoke-direct {p0, v7}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 1325
    :cond_18
    :goto_5
    new-instance p0, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;

    .line 1327
    const-string v0, "Plugin metadata must contain non-empty \'__id__\' and \'__name__\'."

    .line 1325
    invoke-direct {p0, v4, v0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V
    :try_end_4
    .catch Lcom/chaquo/python/PyException; {:try_start_4 .. :try_end_4} :catch_0
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    return-object p0

    .line 1436
    :goto_6
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Unexpected error validating plugin "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 1437
    new-instance p1, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;

    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p0

    invoke-direct {p1, v4, p0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V

    return-object p1

    .line 1433
    :goto_7
    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "Failed to parse metadata from "

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, ". Error: "

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1, p0}, Lorg/telegram/messenger/FileLog;->e(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 1434
    new-instance p1, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;

    invoke-virtual {p0}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object p0

    invoke-direct {p1, v4, p0}, Lcom/exteragram/messenger/plugins/PluginsController$PluginValidationResult;-><init>(Lcom/exteragram/messenger/plugins/Plugin;Ljava/lang/String;)V

    return-object p1
.end method
