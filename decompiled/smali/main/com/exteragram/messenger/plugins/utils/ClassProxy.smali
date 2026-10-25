.class public Lcom/exteragram/messenger/plugins/utils/ClassProxy;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/exteragram/messenger/plugins/utils/ClassProxy$DexMakerHook;,
        Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;,
        Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldMethodSpec;,
        Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;,
        Lcom/exteragram/messenger/plugins/utils/ClassProxy$TypeHelper;
    }
.end annotation


# static fields
.field private static final EMPTY_ARGS:[Ljava/lang/Object;

.field private static final PYTHON_PEER_FIELD_NAME:Ljava/lang/String; = "__extera_internal_python_peer__"

.field private static final generatedClassLoaderLock:Ljava/lang/Object;

.field private static final mvelExpressionCache:Ljava/util/concurrent/ConcurrentHashMap;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/ConcurrentHashMap<",
            "Ljava/lang/String;",
            "Ljava/io/Serializable;",
            ">;"
        }
    .end annotation
.end field

.field private static volatile sharedGeneratedClassLoader:Ljava/lang/ClassLoader;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const/4 v0, 0x0

    .line 54
    new-array v0, v0, [Ljava/lang/Object;

    sput-object v0, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->EMPTY_ARGS:[Ljava/lang/Object;

    .line 55
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->mvelExpressionCache:Ljava/util/concurrent/ConcurrentHashMap;

    .line 56
    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    sput-object v0, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->generatedClassLoaderLock:Ljava/lang/Object;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .line 52
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private static buildMethodSignature(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/String;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "[",
            "Ljava/lang/Class<",
            "*>;)",
            "Ljava/lang/String;"
        }
    .end annotation

    .line 351
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0, p0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    if-eqz p1, :cond_0

    .line 353
    array-length p0, p1

    const/4 v1, 0x0

    :goto_0
    if-ge v1, p0, :cond_0

    aget-object v2, p1, v1

    const/16 v3, 0x3a

    .line 354
    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 357
    :cond_0
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static capitalize(Ljava/lang/String;)Ljava/lang/String;
    .locals 3

    .line 691
    const-string/jumbo v0, "int"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    const-string p0, "Int"

    return-object p0

    .line 692
    :cond_0
    const-string v0, "boolean"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    const-string p0, "Bool"

    return-object p0

    .line 693
    :cond_1
    const-string/jumbo v0, "short"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    const-string p0, "Short"

    return-object p0

    .line 694
    :cond_2
    const-string v0, "byte"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_3

    const-string p0, "Byte"

    return-object p0

    .line 695
    :cond_3
    const-string v0, "char"

    invoke-virtual {p0, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_4

    const-string p0, "Char"

    return-object p0

    .line 696
    :cond_4
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const/4 v1, 0x0

    const/4 v2, 0x1

    invoke-virtual {p0, v1, v2}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->toUpperCase()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0, v2}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static collectOverridableMethods(Ljava/lang/Class;Ljava/util/Map;)V
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "*>;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Method;",
            ">;)V"
        }
    .end annotation

    if-eqz p0, :cond_5

    .line 326
    const-class v0, Ljava/lang/Object;

    if-ne p0, v0, :cond_0

    goto :goto_2

    .line 330
    :cond_0
    invoke-virtual {p0}, Ljava/lang/Class;->getDeclaredMethods()[Ljava/lang/reflect/Method;

    move-result-object v0

    array-length v1, v0

    const/4 v2, 0x0

    move v3, v2

    :goto_0
    if-ge v3, v1, :cond_3

    aget-object v4, v0, v3

    .line 331
    invoke-virtual {v4}, Ljava/lang/reflect/Method;->getModifiers()I

    move-result v5

    .line 333
    invoke-static {v5}, Ljava/lang/reflect/Modifier;->isStatic(I)Z

    move-result v6

    if-nez v6, :cond_2

    invoke-static {v5}, Ljava/lang/reflect/Modifier;->isFinal(I)Z

    move-result v6

    if-nez v6, :cond_2

    .line 334
    invoke-static {v5}, Ljava/lang/reflect/Modifier;->isPublic(I)Z

    move-result v6

    if-nez v6, :cond_1

    invoke-static {v5}, Ljava/lang/reflect/Modifier;->isProtected(I)Z

    move-result v5

    if-eqz v5, :cond_2

    .line 336
    :cond_1
    invoke-virtual {v4}, Ljava/lang/reflect/Method;->getName()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4}, Ljava/lang/reflect/Method;->getParameterTypes()[Ljava/lang/Class;

    move-result-object v6

    invoke-static {v5, v6}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->buildMethodSignature(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/String;

    move-result-object v5

    .line 337
    invoke-interface {p1, v5}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v6

    if-nez v6, :cond_2

    .line 338
    invoke-interface {p1, v5, v4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_2
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 343
    :cond_3
    invoke-virtual {p0}, Ljava/lang/Class;->getInterfaces()[Ljava/lang/Class;

    move-result-object v0

    array-length v1, v0

    :goto_1
    if-ge v2, v1, :cond_4

    aget-object v3, v0, v2

    .line 344
    invoke-static {v3, p1}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->collectOverridableMethods(Ljava/lang/Class;Ljava/util/Map;)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    .line 347
    :cond_4
    invoke-virtual {p0}, Ljava/lang/Class;->getSuperclass()Ljava/lang/Class;

    move-result-object p0

    invoke-static {p0, p1}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->collectOverridableMethods(Ljava/lang/Class;Ljava/util/Map;)V

    :cond_5
    :goto_2
    return-void
.end method

.method public static createProxyClass(Ljava/lang/Class;Lorg/telegram/messenger/Utilities$Callback3Return;Lcom/exteragram/messenger/plugins/utils/ClassProxy$DexMakerHook;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)Ljava/lang/Class;
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "*>;",
            "Lorg/telegram/messenger/Utilities$Callback3Return<",
            "Ljava/lang/Object;",
            "Ljava/lang/String;",
            "[",
            "Ljava/lang/Object;",
            "Ljava/lang/Object;",
            ">;",
            "Lcom/exteragram/messenger/plugins/utils/ClassProxy$DexMakerHook;",
            "Ljava/util/List<",
            "Ljava/lang/Class<",
            "*>;>;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Method;",
            ">;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Constructor<",
            "*>;>;",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;",
            ">;",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;",
            ">;)",
            "Ljava/lang/Class<",
            "*>;"
        }
    .end annotation

    const/4 v8, 0x0

    const/4 v9, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move-object v4, p4

    move-object v5, p5

    move-object/from16 v6, p6

    move-object/from16 v7, p7

    .line 95
    invoke-static/range {v0 .. v9}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->createProxyClassInternal(Ljava/lang/Class;Lorg/telegram/messenger/Utilities$Callback3Return;Lcom/exteragram/messenger/plugins/utils/ClassProxy$DexMakerHook;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Z)Ljava/lang/Class;

    move-result-object p0

    return-object p0
.end method

.method public static createProxyClass(Ljava/lang/Class;Lorg/telegram/messenger/Utilities$Callback3Return;Lcom/exteragram/messenger/plugins/utils/ClassProxy$DexMakerHook;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;)Ljava/lang/Class;
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "*>;",
            "Lorg/telegram/messenger/Utilities$Callback3Return<",
            "Ljava/lang/Object;",
            "Ljava/lang/String;",
            "[",
            "Ljava/lang/Object;",
            "Ljava/lang/Object;",
            ">;",
            "Lcom/exteragram/messenger/plugins/utils/ClassProxy$DexMakerHook;",
            "Ljava/util/List<",
            "Ljava/lang/Class<",
            "*>;>;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Method;",
            ">;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Constructor<",
            "*>;>;",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;",
            ">;",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;",
            ">;",
            "Ljava/lang/String;",
            ")",
            "Ljava/lang/Class<",
            "*>;"
        }
    .end annotation

    const/4 v9, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    move-object v4, p4

    move-object v5, p5

    move-object/from16 v6, p6

    move-object/from16 v7, p7

    move-object/from16 v8, p8

    .line 100
    invoke-static/range {v0 .. v9}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->createProxyClassInternal(Ljava/lang/Class;Lorg/telegram/messenger/Utilities$Callback3Return;Lcom/exteragram/messenger/plugins/utils/ClassProxy$DexMakerHook;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Z)Ljava/lang/Class;

    move-result-object p0

    return-object p0
.end method

.method public static createProxyClass(Ljava/lang/Class;Lorg/telegram/messenger/Utilities$Callback3Return;Ljava/util/List;Ljava/util/List;)Ljava/lang/Class;
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "*>;",
            "Lorg/telegram/messenger/Utilities$Callback3Return<",
            "Ljava/lang/Object;",
            "Ljava/lang/String;",
            "[",
            "Ljava/lang/Object;",
            "Ljava/lang/Object;",
            ">;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Method;",
            ">;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Constructor<",
            "*>;>;)",
            "Ljava/lang/Class<",
            "*>;"
        }
    .end annotation

    const/4 v8, 0x0

    const/4 v9, 0x1

    const/4 v2, 0x0

    const/4 v3, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v4, p2

    move-object v5, p3

    .line 75
    invoke-static/range {v0 .. v9}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->createProxyClassInternal(Ljava/lang/Class;Lorg/telegram/messenger/Utilities$Callback3Return;Lcom/exteragram/messenger/plugins/utils/ClassProxy$DexMakerHook;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Z)Ljava/lang/Class;

    move-result-object p0

    return-object p0
.end method

.method public static createProxyClass(Ljava/lang/Class;Lorg/telegram/messenger/Utilities$Callback3Return;Ljava/util/List;Ljava/util/List;Ljava/lang/String;)Ljava/lang/Class;
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "*>;",
            "Lorg/telegram/messenger/Utilities$Callback3Return<",
            "Ljava/lang/Object;",
            "Ljava/lang/String;",
            "[",
            "Ljava/lang/Object;",
            "Ljava/lang/Object;",
            ">;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Method;",
            ">;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Constructor<",
            "*>;>;",
            "Ljava/lang/String;",
            ")",
            "Ljava/lang/Class<",
            "*>;"
        }
    .end annotation

    const/4 v7, 0x0

    const/4 v9, 0x1

    const/4 v2, 0x0

    const/4 v3, 0x0

    const/4 v6, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v4, p2

    move-object v5, p3

    move-object v8, p4

    .line 80
    invoke-static/range {v0 .. v9}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->createProxyClassInternal(Ljava/lang/Class;Lorg/telegram/messenger/Utilities$Callback3Return;Lcom/exteragram/messenger/plugins/utils/ClassProxy$DexMakerHook;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Z)Ljava/lang/Class;

    move-result-object p0

    return-object p0
.end method

.method public static createProxyClass(Ljava/lang/Class;Lorg/telegram/messenger/Utilities$Callback3Return;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)Ljava/lang/Class;
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "*>;",
            "Lorg/telegram/messenger/Utilities$Callback3Return<",
            "Ljava/lang/Object;",
            "Ljava/lang/String;",
            "[",
            "Ljava/lang/Object;",
            "Ljava/lang/Object;",
            ">;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Method;",
            ">;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Constructor<",
            "*>;>;",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;",
            ">;",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;",
            ">;)",
            "Ljava/lang/Class<",
            "*>;"
        }
    .end annotation

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v2, 0x0

    const/4 v3, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v4, p2

    move-object v5, p3

    move-object v6, p4

    move-object v7, p5

    .line 85
    invoke-static/range {v0 .. v9}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->createProxyClassInternal(Ljava/lang/Class;Lorg/telegram/messenger/Utilities$Callback3Return;Lcom/exteragram/messenger/plugins/utils/ClassProxy$DexMakerHook;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Z)Ljava/lang/Class;

    move-result-object p0

    return-object p0
.end method

.method public static createProxyClass(Ljava/lang/Class;Lorg/telegram/messenger/Utilities$Callback3Return;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;)Ljava/lang/Class;
    .locals 10
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "*>;",
            "Lorg/telegram/messenger/Utilities$Callback3Return<",
            "Ljava/lang/Object;",
            "Ljava/lang/String;",
            "[",
            "Ljava/lang/Object;",
            "Ljava/lang/Object;",
            ">;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Method;",
            ">;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Constructor<",
            "*>;>;",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;",
            ">;",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;",
            ">;",
            "Ljava/lang/String;",
            ")",
            "Ljava/lang/Class<",
            "*>;"
        }
    .end annotation

    const/4 v3, 0x0

    const/4 v9, 0x0

    const/4 v2, 0x0

    move-object v0, p0

    move-object v1, p1

    move-object v4, p2

    move-object v5, p3

    move-object v6, p4

    move-object v7, p5

    move-object/from16 v8, p6

    .line 90
    invoke-static/range {v0 .. v9}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->createProxyClassInternal(Ljava/lang/Class;Lorg/telegram/messenger/Utilities$Callback3Return;Lcom/exteragram/messenger/plugins/utils/ClassProxy$DexMakerHook;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Z)Ljava/lang/Class;

    move-result-object p0

    return-object p0
.end method

.method private static createProxyClassInternal(Ljava/lang/Class;Lorg/telegram/messenger/Utilities$Callback3Return;Lcom/exteragram/messenger/plugins/utils/ClassProxy$DexMakerHook;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Z)Ljava/lang/Class;
    .locals 19
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "*>;",
            "Lorg/telegram/messenger/Utilities$Callback3Return<",
            "Ljava/lang/Object;",
            "Ljava/lang/String;",
            "[",
            "Ljava/lang/Object;",
            "Ljava/lang/Object;",
            ">;",
            "Lcom/exteragram/messenger/plugins/utils/ClassProxy$DexMakerHook;",
            "Ljava/util/List<",
            "Ljava/lang/Class<",
            "*>;>;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Method;",
            ">;",
            "Ljava/util/List<",
            "Ljava/lang/reflect/Constructor<",
            "*>;>;",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;",
            ">;",
            "Ljava/util/List<",
            "Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;",
            ">;",
            "Ljava/lang/String;",
            "Z)",
            "Ljava/lang/Class<",
            "*>;"
        }
    .end annotation

    move-object/from16 v0, p2

    move-object/from16 v1, p5

    .line 105
    new-instance v2, Lcom/android/dx/DexMaker;

    invoke-direct {v2}, Lcom/android/dx/DexMaker;-><init>()V

    .line 106
    invoke-virtual/range {p0 .. p0}, Ljava/lang/Class;->getSimpleName()Ljava/lang/String;

    move-result-object v3

    .line 107
    invoke-virtual {v3}, Ljava/lang/String;->isEmpty()Z

    move-result v4

    if-eqz v4, :cond_0

    const-string v3, "Anonymous"

    .line 109
    :cond_0
    invoke-static/range {p8 .. p8}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->sanitizeProxyClassSegment(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 110
    new-instance v5, Ljava/lang/StringBuilder;

    const-string v6, "com.exteragram.messenger.plugins.Proxy_"

    invoke-direct {v5, v6}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    if-eqz v4, :cond_1

    .line 111
    const-string v3, "_"

    invoke-virtual {v3, v4}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    goto :goto_0

    :cond_1
    const-string v3, ""

    :goto_0
    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, "_"

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 112
    invoke-virtual/range {p0 .. p0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/String;->hashCode()I

    move-result v3

    invoke-static {v3}, Ljava/lang/Math;->abs(I)I

    move-result v3

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v3, "_"

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    new-instance v3, Ljava/util/Random;

    invoke-direct {v3}, Ljava/util/Random;-><init>()V

    invoke-virtual {v3}, Ljava/util/Random;->nextInt()I

    move-result v3

    invoke-static {v3}, Ljava/lang/Math;->abs(I)I

    move-result v3

    invoke-virtual {v5, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v9

    .line 113
    new-instance v3, Ljava/lang/StringBuilder;

    const-string v4, "L"

    invoke-direct {v3, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    const/16 v4, 0x2e

    const/16 v5, 0x2f

    invoke-virtual {v9, v4, v5}, Ljava/lang/String;->replace(CC)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, ";"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    .line 115
    invoke-static {v3}, Lcom/android/dx/TypeId;->get(Ljava/lang/String;)Lcom/android/dx/TypeId;

    move-result-object v3

    .line 116
    invoke-static/range {p0 .. p0}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v6

    .line 118
    const-class v4, Lorg/telegram/messenger/Utilities$Callback3Return;

    invoke-static {v4}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v4

    .line 119
    const-string/jumbo v5, "staticHandler"

    invoke-virtual {v3, v4, v5}, Lcom/android/dx/TypeId;->getField(Lcom/android/dx/TypeId;Ljava/lang/String;)Lcom/android/dx/FieldId;

    move-result-object v10

    .line 121
    invoke-static/range {p3 .. p3}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->toInterfaceTypeIds(Ljava/util/List;)[Lcom/android/dx/TypeId;

    move-result-object v7

    .line 122
    new-instance v11, Ljava/util/ArrayList;

    invoke-direct {v11}, Ljava/util/ArrayList;-><init>()V

    .line 123
    invoke-static {v11, v7}, Ljava/util/Collections;->addAll(Ljava/util/Collection;[Ljava/lang/Object;)Z

    .line 125
    const-string v4, "GeneratedByExteraProxy.java"

    const/4 v5, 0x1

    invoke-virtual/range {v2 .. v7}, Lcom/android/dx/DexMaker;->declare(Lcom/android/dx/TypeId;Ljava/lang/String;ILcom/android/dx/TypeId;[Lcom/android/dx/TypeId;)V

    move-object v12, v6

    const/16 v4, 0x9

    const/4 v13, 0x0

    .line 126
    invoke-virtual {v2, v10, v4, v13}, Lcom/android/dx/DexMaker;->declare(Lcom/android/dx/FieldId;ILjava/lang/Object;)V

    if-eqz p7, :cond_7

    .line 129
    invoke-interface/range {p7 .. p7}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v4

    :cond_2
    :goto_1
    invoke-interface {v4}, Ljava/util/Iterator;->hasNext()Z

    move-result v5

    if-eqz v5, :cond_7

    invoke-interface {v4}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;

    if-eqz v5, :cond_2

    .line 130
    invoke-static {v5}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;->-$$Nest$fgetname(Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;)Ljava/lang/String;

    move-result-object v6

    if-eqz v6, :cond_2

    invoke-static {v5}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;->-$$Nest$fgettype(Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;)Ljava/lang/Class;

    move-result-object v6

    if-nez v6, :cond_3

    goto :goto_1

    .line 134
    :cond_3
    invoke-static {v5}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;->-$$Nest$fgettype(Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;)Ljava/lang/Class;

    move-result-object v6

    invoke-static {v6}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v6

    invoke-static {v5}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;->-$$Nest$fgetname(Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;)Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v3, v6, v7}, Lcom/android/dx/TypeId;->getField(Lcom/android/dx/TypeId;Ljava/lang/String;)Lcom/android/dx/FieldId;

    move-result-object v6

    .line 135
    invoke-static {v5}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;->-$$Nest$fgetmodifiers(Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;)I

    move-result v7

    invoke-static {v7}, Ljava/lang/reflect/Modifier;->isStatic(I)Z

    move-result v7

    if-eqz v7, :cond_4

    invoke-static {v5}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;->-$$Nest$fgettype(Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;)Ljava/lang/Class;

    move-result-object v7

    invoke-static {v7}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->defaultValueForType(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v7

    goto :goto_2

    :cond_4
    move-object v7, v13

    .line 136
    :goto_2
    invoke-static {v5}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;->-$$Nest$fgetmodifiers(Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;)I

    move-result v8

    invoke-virtual {v2, v6, v8, v7}, Lcom/android/dx/DexMaker;->declare(Lcom/android/dx/FieldId;ILjava/lang/Object;)V

    .line 138
    invoke-static {v5}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;->-$$Nest$fgetmethods(Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;)Ljava/util/List;

    move-result-object v6

    if-eqz v6, :cond_2

    .line 139
    invoke-static {v5}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;->-$$Nest$fgetmethods(Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;)Ljava/util/List;

    move-result-object v6

    invoke-interface {v6}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v6

    :cond_5
    :goto_3
    invoke-interface {v6}, Ljava/util/Iterator;->hasNext()Z

    move-result v7

    if-eqz v7, :cond_2

    invoke-interface {v6}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldMethodSpec;

    if-eqz v7, :cond_5

    .line 140
    invoke-static {v7}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldMethodSpec;->-$$Nest$fgetname(Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldMethodSpec;)Ljava/lang/String;

    move-result-object v8

    if-nez v8, :cond_6

    goto :goto_3

    .line 143
    :cond_6
    invoke-static {v2, v3, v5, v7}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->generateFieldAccessorMethod(Lcom/android/dx/DexMaker;Lcom/android/dx/TypeId;Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldMethodSpec;)V

    goto :goto_3

    :cond_7
    const/4 v14, 0x0

    if-eqz v1, :cond_8

    .line 149
    new-array v4, v14, [Ljava/lang/reflect/Constructor;

    invoke-interface {v1, v4}, Ljava/util/List;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object v1

    check-cast v1, [Ljava/lang/reflect/Constructor;

    goto :goto_4

    :cond_8
    invoke-virtual/range {p0 .. p0}, Ljava/lang/Class;->getDeclaredConstructors()[Ljava/lang/reflect/Constructor;

    move-result-object v1

    .line 150
    :goto_4
    array-length v4, v1

    move v5, v14

    :goto_5
    if-ge v5, v4, :cond_a

    aget-object v6, v1, v5

    .line 151
    invoke-virtual {v6}, Ljava/lang/reflect/Constructor;->getModifiers()I

    move-result v7

    invoke-static {v7}, Ljava/lang/reflect/Modifier;->isPrivate(I)Z

    move-result v7

    if-eqz v7, :cond_9

    goto :goto_6

    .line 152
    :cond_9
    invoke-static {v2, v3, v12, v6, v10}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->generateConstructor(Lcom/android/dx/DexMaker;Lcom/android/dx/TypeId;Lcom/android/dx/TypeId;Ljava/lang/reflect/Constructor;Lcom/android/dx/FieldId;)V

    :goto_6
    add-int/lit8 v5, v5, 0x1

    goto :goto_5

    :cond_a
    move-object/from16 v5, p0

    move-object/from16 v6, p3

    .line 155
    invoke-static {v5, v6}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->getAllOverridableMethods(Ljava/lang/Class;Ljava/util/List;)Ljava/util/Map;

    move-result-object v1

    .line 156
    new-instance v4, Ljava/util/LinkedHashMap;

    invoke-direct {v4}, Ljava/util/LinkedHashMap;-><init>()V

    if-eqz p9, :cond_c

    if-nez p4, :cond_c

    if-eqz p6, :cond_b

    .line 157
    invoke-interface/range {p6 .. p6}, Ljava/util/List;->isEmpty()Z

    move-result v5

    if-eqz v5, :cond_c

    .line 158
    :cond_b
    invoke-interface {v4, v1}, Ljava/util/Map;->putAll(Ljava/util/Map;)V

    :cond_c
    if-eqz p4, :cond_e

    .line 161
    invoke-interface/range {p4 .. p4}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v5

    :goto_7
    invoke-interface {v5}, Ljava/util/Iterator;->hasNext()Z

    move-result v6

    if-eqz v6, :cond_e

    invoke-interface {v5}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/reflect/Method;

    if-nez v6, :cond_d

    goto :goto_7

    .line 163
    :cond_d
    invoke-virtual {v6}, Ljava/lang/reflect/Method;->getName()Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v6}, Ljava/lang/reflect/Method;->getParameterTypes()[Ljava/lang/Class;

    move-result-object v8

    invoke-static {v7, v8}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->buildMethodSignature(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/String;

    move-result-object v7

    invoke-interface {v4, v7, v6}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_7

    .line 167
    :cond_e
    new-instance v15, Ljava/util/ArrayList;

    invoke-direct {v15}, Ljava/util/ArrayList;-><init>()V

    .line 168
    new-instance v5, Ljava/util/LinkedHashMap;

    invoke-direct {v5}, Ljava/util/LinkedHashMap;-><init>()V

    if-eqz p6, :cond_17

    .line 170
    invoke-interface/range {p6 .. p6}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v6

    :cond_f
    :goto_8
    invoke-interface {v6}, Ljava/util/Iterator;->hasNext()Z

    move-result v7

    if-eqz v7, :cond_17

    invoke-interface {v6}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;

    if-eqz v7, :cond_f

    .line 171
    invoke-static {v7}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetname(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)Ljava/lang/String;

    move-result-object v8

    if-nez v8, :cond_10

    goto :goto_8

    .line 173
    :cond_10
    invoke-static {v7}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetoverrideExisting(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)Z

    move-result v8

    if-eqz v8, :cond_15

    .line 174
    invoke-static {v7}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetparameterTypes(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)[Ljava/lang/Class;

    move-result-object v8

    if-nez v8, :cond_13

    .line 175
    invoke-interface {v1}, Ljava/util/Map;->values()Ljava/util/Collection;

    move-result-object v8

    invoke-interface {v8}, Ljava/util/Collection;->iterator()Ljava/util/Iterator;

    move-result-object v8

    :goto_9
    invoke-interface {v8}, Ljava/util/Iterator;->hasNext()Z

    move-result v16

    if-eqz v16, :cond_12

    invoke-interface {v8}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v16

    move-object/from16 v13, v16

    check-cast v13, Ljava/lang/reflect/Method;

    .line 176
    invoke-static {v7}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetname(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)Ljava/lang/String;

    move-result-object v14

    move-object/from16 p0, v6

    invoke-virtual {v13}, Ljava/lang/reflect/Method;->getName()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v14, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v6

    if-eqz v6, :cond_11

    .line 177
    invoke-virtual {v13}, Ljava/lang/reflect/Method;->getName()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v13}, Ljava/lang/reflect/Method;->getParameterTypes()[Ljava/lang/Class;

    move-result-object v14

    invoke-static {v6, v14}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->buildMethodSignature(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/String;

    move-result-object v6

    .line 178
    invoke-interface {v4, v6, v13}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 179
    invoke-virtual {v7}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->isMvel()Z

    move-result v13

    if-eqz v13, :cond_11

    .line 180
    invoke-interface {v5, v6, v7}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_11
    move-object/from16 v6, p0

    const/4 v13, 0x0

    const/4 v14, 0x0

    goto :goto_9

    :cond_12
    move-object/from16 p0, v6

    goto :goto_a

    :cond_13
    move-object/from16 p0, v6

    .line 185
    invoke-static {v7}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetname(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)Ljava/lang/String;

    move-result-object v6

    invoke-static {v7}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetparameterTypes(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)[Ljava/lang/Class;

    move-result-object v8

    invoke-static {v6, v8}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->buildMethodSignature(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/String;

    move-result-object v6

    invoke-interface {v1, v6}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Ljava/lang/reflect/Method;

    if-eqz v6, :cond_14

    .line 189
    invoke-virtual {v6}, Ljava/lang/reflect/Method;->getName()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v6}, Ljava/lang/reflect/Method;->getParameterTypes()[Ljava/lang/Class;

    move-result-object v13

    invoke-static {v8, v13}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->buildMethodSignature(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/String;

    move-result-object v8

    .line 190
    invoke-interface {v4, v8, v6}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 191
    invoke-virtual {v7}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->isMvel()Z

    move-result v6

    if-eqz v6, :cond_16

    .line 192
    invoke-interface {v5, v8, v7}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    goto :goto_a

    .line 187
    :cond_14
    new-instance v0, Ljava/lang/NoSuchMethodException;

    const-string v1, "Unable to resolve overridable method "

    invoke-static {v7}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetname(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)Ljava/lang/String;

    move-result-object v2

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/NoSuchMethodException;-><init>(Ljava/lang/String;)V

    throw v0

    :cond_15
    move-object/from16 p0, v6

    .line 196
    invoke-interface {v15, v7}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :cond_16
    :goto_a
    move-object/from16 v6, p0

    const/4 v13, 0x0

    const/4 v14, 0x0

    goto/16 :goto_8

    .line 201
    :cond_17
    invoke-interface {v4}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object v1

    invoke-interface {v1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v13

    :goto_b
    invoke-interface {v13}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    const/4 v14, 0x1

    if-eqz v1, :cond_19

    invoke-interface {v13}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    .line 202
    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/reflect/Method;

    .line 203
    invoke-static {v2, v3, v4, v10, v14}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->generateProxyMethod(Lcom/android/dx/DexMaker;Lcom/android/dx/TypeId;Ljava/lang/reflect/Method;Lcom/android/dx/FieldId;Z)V

    .line 204
    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v1

    invoke-interface {v5, v1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;

    if-eqz v1, :cond_18

    .line 205
    invoke-virtual {v1}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->isMvel()Z

    move-result v6

    if-eqz v6, :cond_18

    move-object v6, v1

    move-object v1, v2

    move-object v2, v3

    .line 209
    invoke-virtual {v4}, Ljava/lang/reflect/Method;->getName()Ljava/lang/String;

    move-result-object v3

    move-object v7, v4

    .line 210
    invoke-virtual {v7}, Ljava/lang/reflect/Method;->getReturnType()Ljava/lang/Class;

    move-result-object v4

    move-object v8, v5

    .line 211
    invoke-virtual {v7}, Ljava/lang/reflect/Method;->getParameterTypes()[Ljava/lang/Class;

    move-result-object v5

    invoke-static {v6}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetmvelCode(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)Ljava/lang/String;

    move-result-object v7

    invoke-static {v6}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetargumentNames(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)Ljava/util/List;

    move-result-object v6

    move-object v14, v8

    move-object v8, v6

    const/4 v6, 0x1

    move-object/from16 v17, v14

    .line 206
    invoke-static/range {v1 .. v8}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->generateMvelMethod(Lcom/android/dx/DexMaker;Lcom/android/dx/TypeId;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;ILjava/lang/String;Ljava/util/List;)V

    goto :goto_c

    :cond_18
    move-object v1, v2

    move-object v2, v3

    move-object v7, v4

    move-object/from16 v17, v5

    const/4 v3, 0x0

    .line 217
    invoke-static {v1, v2, v7, v10, v3}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->generateProxyMethod(Lcom/android/dx/DexMaker;Lcom/android/dx/TypeId;Ljava/lang/reflect/Method;Lcom/android/dx/FieldId;Z)V

    :goto_c
    move-object v3, v2

    move-object/from16 v5, v17

    move-object v2, v1

    goto :goto_b

    :cond_19
    move-object v1, v2

    move-object v2, v3

    .line 221
    invoke-virtual {v15}, Ljava/util/ArrayList;->size()I

    move-result v13

    const/4 v3, 0x0

    :goto_d
    if-ge v3, v13, :cond_21

    invoke-virtual {v15, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v4

    add-int/lit8 v17, v3, 0x1

    check-cast v4, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;

    .line 222
    invoke-virtual {v4}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->isMvel()Z

    move-result v3

    if-eqz v3, :cond_1d

    .line 223
    invoke-static {v4}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetname(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)Ljava/lang/String;

    move-result-object v3

    .line 227
    invoke-static {v4}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetreturnType(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)Ljava/lang/Class;

    move-result-object v5

    if-eqz v5, :cond_1a

    invoke-static {v4}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetreturnType(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)Ljava/lang/Class;

    move-result-object v5

    goto :goto_e

    :cond_1a
    sget-object v5, Ljava/lang/Void;->TYPE:Ljava/lang/Class;

    .line 228
    :goto_e
    invoke-static {v4}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetparameterTypes(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)[Ljava/lang/Class;

    move-result-object v6

    if-eqz v6, :cond_1b

    invoke-static {v4}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetparameterTypes(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)[Ljava/lang/Class;

    move-result-object v6

    goto :goto_f

    :cond_1b
    const/4 v6, 0x0

    new-array v7, v6, [Ljava/lang/Class;

    move-object v6, v7

    .line 229
    :goto_f
    invoke-static {v4}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetmodifiers(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)I

    move-result v7

    if-eqz v7, :cond_1c

    invoke-static {v4}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetmodifiers(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)I

    move-result v7

    goto :goto_10

    :cond_1c
    move v7, v14

    :goto_10
    invoke-static {v4}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetmvelCode(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)Ljava/lang/String;

    move-result-object v8

    move-object/from16 v18, v8

    move-object v8, v4

    move-object v4, v5

    move-object v5, v6

    move v6, v7

    move-object/from16 v7, v18

    invoke-static {v8}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetargumentNames(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)Ljava/util/List;

    move-result-object v8

    .line 223
    invoke-static/range {v1 .. v8}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->generateMvelMethod(Lcom/android/dx/DexMaker;Lcom/android/dx/TypeId;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;ILjava/lang/String;Ljava/util/List;)V

    move-object v3, v10

    const/4 v6, 0x0

    goto :goto_15

    :cond_1d
    move-object v8, v4

    .line 234
    invoke-static {v8}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetname(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)Ljava/lang/String;

    move-result-object v3

    .line 238
    invoke-static {v8}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetreturnType(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)Ljava/lang/Class;

    move-result-object v4

    if-eqz v4, :cond_1e

    invoke-static {v8}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetreturnType(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)Ljava/lang/Class;

    move-result-object v4

    goto :goto_11

    :cond_1e
    sget-object v4, Ljava/lang/Void;->TYPE:Ljava/lang/Class;

    .line 239
    :goto_11
    invoke-static {v8}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetparameterTypes(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)[Ljava/lang/Class;

    move-result-object v5

    if-eqz v5, :cond_1f

    invoke-static {v8}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetparameterTypes(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)[Ljava/lang/Class;

    move-result-object v5

    const/4 v6, 0x0

    goto :goto_12

    :cond_1f
    const/4 v6, 0x0

    new-array v5, v6, [Ljava/lang/Class;

    .line 240
    :goto_12
    invoke-static {v8}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetmodifiers(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)I

    move-result v7

    if-eqz v7, :cond_20

    invoke-static {v8}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;->-$$Nest$fgetmodifiers(Lcom/exteragram/messenger/plugins/utils/ClassProxy$ProxyMethodSpec;)I

    move-result v7

    move/from16 p8, v7

    :goto_13
    move-object/from16 p3, v1

    move-object/from16 p4, v2

    move-object/from16 p5, v3

    move-object/from16 p6, v4

    move-object/from16 p7, v5

    move-object/from16 p9, v10

    goto :goto_14

    :cond_20
    move/from16 p8, v14

    goto :goto_13

    .line 234
    :goto_14
    invoke-static/range {p3 .. p9}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->generateHandlerMethod(Lcom/android/dx/DexMaker;Lcom/android/dx/TypeId;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;ILcom/android/dx/FieldId;)V

    move-object/from16 v1, p3

    move-object/from16 v2, p4

    move-object/from16 v3, p9

    :goto_15
    move-object v10, v3

    move/from16 v3, v17

    goto/16 :goto_d

    :cond_21
    if-eqz v0, :cond_22

    .line 247
    invoke-interface {v0, v1, v2, v12, v11}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$DexMakerHook;->apply(Lcom/android/dx/DexMaker;Lcom/android/dx/TypeId;Lcom/android/dx/TypeId;Ljava/util/List;)V

    .line 250
    :cond_22
    invoke-virtual {v1}, Lcom/android/dx/DexMaker;->generate()[B

    move-result-object v0

    .line 252
    sget-object v1, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->generatedClassLoaderLock:Ljava/lang/Object;

    monitor-enter v1

    .line 253
    :try_start_0
    sget-object v2, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->sharedGeneratedClassLoader:Ljava/lang/ClassLoader;

    if-nez v2, :cond_23

    .line 255
    sget-object v2, Lorg/telegram/messenger/ApplicationLoader;->applicationContext:Landroid/content/Context;

    invoke-virtual {v2}, Landroid/content/Context;->getClassLoader()Ljava/lang/ClassLoader;

    move-result-object v2

    goto :goto_16

    :catchall_0
    move-exception v0

    goto :goto_17

    .line 258
    :cond_23
    :goto_16
    invoke-static {}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$$ExternalSyntheticApiModelOutline1;->m()V

    .line 259
    invoke-static {v0}, Ljava/nio/ByteBuffer;->wrap([B)Ljava/nio/ByteBuffer;

    move-result-object v0

    invoke-static {v0, v2}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$$ExternalSyntheticApiModelOutline0;->m(Ljava/nio/ByteBuffer;Ljava/lang/ClassLoader;)Ldalvik/system/InMemoryDexClassLoader;

    move-result-object v0

    .line 263
    invoke-virtual {v0, v9}, Ldalvik/system/InMemoryDexClassLoader;->loadClass(Ljava/lang/String;)Ljava/lang/Class;

    move-result-object v2

    .line 264
    sput-object v0, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->sharedGeneratedClassLoader:Ljava/lang/ClassLoader;

    .line 265
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 267
    const-string/jumbo v0, "staticHandler"

    invoke-virtual {v2, v0}, Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;

    move-result-object v0

    .line 268
    invoke-virtual {v0, v14}, Ljava/lang/reflect/AccessibleObject;->setAccessible(Z)V

    move-object/from16 v1, p1

    const/4 v3, 0x0

    .line 269
    invoke-virtual {v0, v3, v1}, Ljava/lang/reflect/Field;->set(Ljava/lang/Object;Ljava/lang/Object;)V

    return-object v2

    .line 265
    :goto_17
    :try_start_1
    monitor-exit v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method private static defaultValueForType(Ljava/lang/Class;)Ljava/lang/Object;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "*>;)",
            "Ljava/lang/Object;"
        }
    .end annotation

    .line 361
    invoke-virtual {p0}, Ljava/lang/Class;->isPrimitive()Z

    move-result v0

    if-nez v0, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 362
    :cond_0
    sget-object v0, Ljava/lang/Boolean;->TYPE:Ljava/lang/Class;

    if-ne p0, v0, :cond_1

    sget-object p0, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;

    return-object p0

    .line 363
    :cond_1
    sget-object v0, Ljava/lang/Long;->TYPE:Ljava/lang/Class;

    if-ne p0, v0, :cond_2

    const-wide/16 v0, 0x0

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p0

    return-object p0

    .line 364
    :cond_2
    sget-object v0, Ljava/lang/Double;->TYPE:Ljava/lang/Class;

    if-ne p0, v0, :cond_3

    const-wide/16 v0, 0x0

    invoke-static {v0, v1}, Ljava/lang/Double;->valueOf(D)Ljava/lang/Double;

    move-result-object p0

    return-object p0

    .line 365
    :cond_3
    sget-object v0, Ljava/lang/Float;->TYPE:Ljava/lang/Class;

    if-ne p0, v0, :cond_4

    const/4 p0, 0x0

    invoke-static {p0}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object p0

    return-object p0

    .line 366
    :cond_4
    sget-object v0, Ljava/lang/Short;->TYPE:Ljava/lang/Class;

    const/4 v1, 0x0

    if-ne p0, v0, :cond_5

    invoke-static {v1}, Ljava/lang/Short;->valueOf(S)Ljava/lang/Short;

    move-result-object p0

    return-object p0

    .line 367
    :cond_5
    sget-object v0, Ljava/lang/Byte;->TYPE:Ljava/lang/Class;

    if-ne p0, v0, :cond_6

    invoke-static {v1}, Ljava/lang/Byte;->valueOf(B)Ljava/lang/Byte;

    move-result-object p0

    return-object p0

    .line 368
    :cond_6
    sget-object v0, Ljava/lang/Character;->TYPE:Ljava/lang/Class;

    if-ne p0, v0, :cond_7

    invoke-static {v1}, Ljava/lang/Character;->valueOf(C)Ljava/lang/Character;

    move-result-object p0

    return-object p0

    .line 369
    :cond_7
    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    return-object p0
.end method

.method public static executeMvelMethod(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;
    .locals 4

    .line 664
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    const/4 v1, 0x0

    if-eqz p4, :cond_0

    goto :goto_0

    .line 665
    :cond_0
    new-array p4, v1, [Ljava/lang/Object;

    .line 667
    :goto_0
    const-string/jumbo v2, "java"

    invoke-interface {v0, v2, p0}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 668
    const-string/jumbo v2, "python"

    invoke-interface {v0, v2, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 669
    const-string/jumbo v2, "py"

    invoke-interface {v0, v2, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 670
    const-string/jumbo v2, "self"

    invoke-interface {v0, v2, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 671
    const-string p1, "args"

    invoke-interface {v0, p1, p4}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 672
    array-length p1, p4

    invoke-static {p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p1

    const-string v2, "argc"

    invoke-interface {v0, v2, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 674
    :goto_1
    array-length p1, p4

    if-ge v1, p1, :cond_2

    .line 675
    aget-object p1, p4, v1

    .line 676
    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "arg"

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-interface {v0, v2, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    if-eqz p3, :cond_1

    .line 678
    array-length v2, p3

    if-ge v1, v2, :cond_1

    .line 679
    aget-object v2, p3, v1

    if-eqz v2, :cond_1

    .line 680
    invoke-virtual {v2}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_1

    .line 681
    invoke-interface {v0, v2, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_1

    .line 686
    :cond_2
    sget-object p1, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->mvelExpressionCache:Ljava/util/concurrent/ConcurrentHashMap;

    new-instance p3, Lcom/exteragram/messenger/plugins/utils/ClassProxy$$ExternalSyntheticLambda2;

    invoke-direct {p3}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$$ExternalSyntheticLambda2;-><init>()V

    invoke-virtual {p1, p2, p3}, Ljava/util/concurrent/ConcurrentHashMap;->computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/io/Serializable;

    .line 687
    invoke-static {p1, p0, v0}, Lorg/mvel2/MVEL;->executeExpression(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Map;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method private static generateConstructor(Lcom/android/dx/DexMaker;Lcom/android/dx/TypeId;Lcom/android/dx/TypeId;Ljava/lang/reflect/Constructor;Lcom/android/dx/FieldId;)V
    .locals 18
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/android/dx/DexMaker;",
            "Lcom/android/dx/TypeId<",
            "*>;",
            "Lcom/android/dx/TypeId<",
            "*>;",
            "Ljava/lang/reflect/Constructor<",
            "*>;",
            "Lcom/android/dx/FieldId;",
            ")V"
        }
    .end annotation

    move-object/from16 v0, p1

    .line 373
    invoke-virtual/range {p3 .. p3}, Ljava/lang/reflect/Constructor;->getParameterTypes()[Ljava/lang/Class;

    move-result-object v1

    .line 374
    array-length v2, v1

    new-array v2, v2, [Lcom/android/dx/TypeId;

    const/4 v3, 0x0

    .line 401
    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v4

    move v5, v3

    .line 375
    :goto_0
    array-length v6, v1

    if-ge v5, v6, :cond_0

    aget-object v6, v1, v5

    invoke-static {v6}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v6

    aput-object v6, v2, v5

    add-int/lit8 v5, v5, 0x1

    goto :goto_0

    .line 377
    :cond_0
    invoke-virtual {v0, v2}, Lcom/android/dx/TypeId;->getConstructor([Lcom/android/dx/TypeId;)Lcom/android/dx/MethodId;

    move-result-object v5

    const/4 v6, 0x1

    move-object/from16 v7, p0

    .line 378
    invoke-virtual {v7, v5, v6}, Lcom/android/dx/DexMaker;->declare(Lcom/android/dx/MethodId;I)Lcom/android/dx/Code;

    move-result-object v5

    .line 380
    const-class v6, Lorg/telegram/messenger/Utilities$Callback3Return;

    invoke-static {v6}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v6

    .line 381
    sget-object v7, Lcom/android/dx/TypeId;->OBJECT:Lcom/android/dx/TypeId;

    const-string/jumbo v8, "run"

    filled-new-array {v7, v7, v7}, [Lcom/android/dx/TypeId;

    move-result-object v9

    invoke-virtual {v6, v7, v8, v9}, Lcom/android/dx/TypeId;->getMethod(Lcom/android/dx/TypeId;Ljava/lang/String;[Lcom/android/dx/TypeId;)Lcom/android/dx/MethodId;

    move-result-object v8

    .line 383
    invoke-virtual {v5, v0}, Lcom/android/dx/Code;->getThis(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v0

    .line 384
    invoke-virtual {v5, v6}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v6

    .line 385
    sget-object v9, Lcom/android/dx/TypeId;->STRING:Lcom/android/dx/TypeId;

    invoke-virtual {v5, v9}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v9

    .line 386
    const-class v10, [Ljava/lang/Object;

    invoke-static {v10}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v11

    invoke-virtual {v5, v11}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v11

    .line 387
    invoke-virtual {v5, v7}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v12

    .line 388
    invoke-virtual {v5, v7}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v13

    .line 389
    invoke-static {v10}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v10

    invoke-virtual {v5, v10}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v10

    .line 390
    sget-object v14, Lcom/android/dx/TypeId;->INT:Lcom/android/dx/TypeId;

    invoke-virtual {v5, v14}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v15

    .line 391
    invoke-virtual {v5, v14}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v14

    .line 392
    array-length v3, v1

    new-array v3, v3, [Lcom/android/dx/Local;

    .line 393
    invoke-virtual {v5, v7}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v7

    move-object/from16 p1, v0

    move-object/from16 p0, v3

    const/4 v3, 0x0

    .line 394
    :goto_1
    array-length v0, v1

    if-ge v3, v0, :cond_1

    sget-object v0, Lcom/android/dx/TypeId;->OBJECT:Lcom/android/dx/TypeId;

    invoke-virtual {v5, v0}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v0

    aput-object v0, p0, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_1

    .line 396
    :cond_1
    sget-object v0, Lcom/android/dx/TypeId;->INT:Lcom/android/dx/TypeId;

    invoke-virtual {v5, v0}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v0

    .line 398
    array-length v3, v1

    new-array v3, v3, [Lcom/android/dx/Local;

    move-object/from16 v17, v2

    move-object/from16 v16, v3

    const/4 v3, 0x0

    .line 399
    :goto_2
    array-length v2, v1

    if-ge v3, v2, :cond_2

    aget-object v2, v17, v3

    invoke-virtual {v5, v2}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v2

    aput-object v2, v16, v3

    add-int/lit8 v3, v3, 0x1

    goto :goto_2

    .line 401
    :cond_2
    invoke-virtual {v5, v0, v4}, Lcom/android/dx/Code;->loadConstant(Lcom/android/dx/Local;Ljava/lang/Object;)V

    .line 402
    invoke-virtual {v5, v11, v0}, Lcom/android/dx/Code;->newArray(Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    .line 403
    invoke-virtual {v5, v10, v11}, Lcom/android/dx/Code;->move(Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    move-object/from16 v0, p4

    .line 404
    invoke-virtual {v5, v0, v6}, Lcom/android/dx/Code;->sget(Lcom/android/dx/FieldId;Lcom/android/dx/Local;)V

    .line 405
    const-string v0, "<constructor>"

    invoke-static {v0, v1}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->buildMethodSignature(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v5, v9, v0}, Lcom/android/dx/Code;->loadConstant(Lcom/android/dx/Local;Ljava/lang/Object;)V

    const/4 v0, 0x0

    .line 406
    invoke-virtual {v5, v12, v0}, Lcom/android/dx/Code;->loadConstant(Lcom/android/dx/Local;Ljava/lang/Object;)V

    .line 407
    invoke-virtual {v5, v14, v4}, Lcom/android/dx/Code;->loadConstant(Lcom/android/dx/Local;Ljava/lang/Object;)V

    .line 408
    invoke-virtual {v5, v7, v0}, Lcom/android/dx/Code;->loadConstant(Lcom/android/dx/Local;Ljava/lang/Object;)V

    const/4 v2, 0x0

    .line 410
    :goto_3
    array-length v3, v1

    if-ge v2, v3, :cond_3

    .line 411
    aget-object v3, v16, v2

    aget-object v4, v17, v2

    invoke-virtual {v5, v2, v4}, Lcom/android/dx/Code;->getParameter(ILcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v4

    invoke-virtual {v5, v3, v4}, Lcom/android/dx/Code;->move(Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_3

    .line 414
    :cond_3
    array-length v2, v1

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v5, v15, v2}, Lcom/android/dx/Code;->loadConstant(Lcom/android/dx/Local;Ljava/lang/Object;)V

    .line 415
    invoke-virtual {v5, v11, v15}, Lcom/android/dx/Code;->newArray(Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    const/4 v2, 0x0

    .line 417
    :goto_4
    array-length v3, v1

    const-class v4, Lcom/exteragram/messenger/plugins/utils/ClassProxy$TypeHelper;

    if-ge v2, v3, :cond_5

    .line 418
    invoke-static {v4}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v3

    sget-object v4, Lcom/android/dx/TypeId;->OBJECT:Lcom/android/dx/TypeId;

    aget-object v15, v1, v2

    invoke-virtual {v15}, Ljava/lang/Class;->isPrimitive()Z

    move-result v15

    if-eqz v15, :cond_4

    aget-object v15, v17, v2

    goto :goto_5

    :cond_4
    move-object v15, v4

    :goto_5
    filled-new-array {v15}, [Lcom/android/dx/TypeId;

    move-result-object v15

    const-string v0, "box"

    invoke-virtual {v3, v4, v0, v15}, Lcom/android/dx/TypeId;->getMethod(Lcom/android/dx/TypeId;Ljava/lang/String;[Lcom/android/dx/TypeId;)Lcom/android/dx/MethodId;

    move-result-object v0

    .line 419
    aget-object v3, p0, v2

    aget-object v4, v16, v2

    filled-new-array {v4}, [Lcom/android/dx/Local;

    move-result-object v4

    invoke-virtual {v5, v0, v3, v4}, Lcom/android/dx/Code;->invokeStatic(Lcom/android/dx/MethodId;Lcom/android/dx/Local;[Lcom/android/dx/Local;)V

    .line 420
    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {v5, v14, v0}, Lcom/android/dx/Code;->loadConstant(Lcom/android/dx/Local;Ljava/lang/Object;)V

    .line 421
    aget-object v0, p0, v2

    invoke-virtual {v5, v11, v14, v0}, Lcom/android/dx/Code;->aput(Lcom/android/dx/Local;Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    add-int/lit8 v2, v2, 0x1

    const/4 v0, 0x0

    goto :goto_4

    .line 424
    :cond_5
    filled-new-array {v12, v9, v11}, [Lcom/android/dx/Local;

    move-result-object v0

    invoke-virtual {v5, v8, v13, v6, v0}, Lcom/android/dx/Code;->invokeInterface(Lcom/android/dx/MethodId;Lcom/android/dx/Local;Lcom/android/dx/Local;[Lcom/android/dx/Local;)V

    .line 426
    new-instance v0, Lcom/android/dx/Label;

    invoke-direct {v0}, Lcom/android/dx/Label;-><init>()V

    .line 427
    sget-object v2, Lcom/android/dx/Comparison;->EQ:Lcom/android/dx/Comparison;

    invoke-virtual {v5, v2, v0, v13, v12}, Lcom/android/dx/Code;->compare(Lcom/android/dx/Comparison;Lcom/android/dx/Label;Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    .line 429
    invoke-virtual {v5, v10, v13}, Lcom/android/dx/Code;->cast(Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    const/4 v3, 0x0

    .line 430
    :goto_6
    array-length v2, v1

    if-ge v3, v2, :cond_7

    .line 431
    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    invoke-virtual {v5, v14, v2}, Lcom/android/dx/Code;->loadConstant(Lcom/android/dx/Local;Ljava/lang/Object;)V

    .line 432
    invoke-virtual {v5, v7, v10, v14}, Lcom/android/dx/Code;->aget(Lcom/android/dx/Local;Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    .line 434
    aget-object v2, v1, v3

    invoke-virtual {v2}, Ljava/lang/Class;->isPrimitive()Z

    move-result v2

    if-eqz v2, :cond_6

    .line 435
    new-instance v2, Ljava/lang/StringBuilder;

    const-string/jumbo v12, "unbox"

    invoke-direct {v2, v12}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    aget-object v12, v1, v3

    invoke-virtual {v12}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v12

    invoke-static {v12}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->capitalize(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v12

    invoke-virtual {v2, v12}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    .line 436
    invoke-static {v4}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v12

    aget-object v13, v17, v3

    sget-object v15, Lcom/android/dx/TypeId;->OBJECT:Lcom/android/dx/TypeId;

    filled-new-array {v15}, [Lcom/android/dx/TypeId;

    move-result-object v15

    invoke-virtual {v12, v13, v2, v15}, Lcom/android/dx/TypeId;->getMethod(Lcom/android/dx/TypeId;Ljava/lang/String;[Lcom/android/dx/TypeId;)Lcom/android/dx/MethodId;

    move-result-object v2

    .line 437
    aget-object v12, v16, v3

    filled-new-array {v7}, [Lcom/android/dx/Local;

    move-result-object v13

    invoke-virtual {v5, v2, v12, v13}, Lcom/android/dx/Code;->invokeStatic(Lcom/android/dx/MethodId;Lcom/android/dx/Local;[Lcom/android/dx/Local;)V

    goto :goto_7

    .line 439
    :cond_6
    aget-object v2, v16, v3

    invoke-virtual {v5, v2, v7}, Lcom/android/dx/Code;->cast(Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    :goto_7
    add-int/lit8 v3, v3, 0x1

    goto :goto_6

    .line 443
    :cond_7
    invoke-virtual {v5, v0}, Lcom/android/dx/Code;->mark(Lcom/android/dx/Label;)V

    move-object/from16 v0, p2

    move-object/from16 v1, v17

    .line 445
    invoke-virtual {v0, v1}, Lcom/android/dx/TypeId;->getConstructor([Lcom/android/dx/TypeId;)Lcom/android/dx/MethodId;

    move-result-object v0

    move-object/from16 v1, p1

    move-object/from16 v2, v16

    const/4 v3, 0x0

    invoke-virtual {v5, v0, v3, v1, v2}, Lcom/android/dx/Code;->invokeDirect(Lcom/android/dx/MethodId;Lcom/android/dx/Local;Lcom/android/dx/Local;[Lcom/android/dx/Local;)V

    .line 446
    filled-new-array {v1, v9, v11}, [Lcom/android/dx/Local;

    move-result-object v0

    invoke-virtual {v5, v8, v3, v6, v0}, Lcom/android/dx/Code;->invokeInterface(Lcom/android/dx/MethodId;Lcom/android/dx/Local;Lcom/android/dx/Local;[Lcom/android/dx/Local;)V

    .line 448
    invoke-virtual {v5}, Lcom/android/dx/Code;->returnVoid()V

    return-void
.end method

.method private static generateFieldAccessorMethod(Lcom/android/dx/DexMaker;Lcom/android/dx/TypeId;Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldMethodSpec;)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/android/dx/DexMaker;",
            "Lcom/android/dx/TypeId<",
            "*>;",
            "Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;",
            "Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldMethodSpec;",
            ")V"
        }
    .end annotation

    .line 628
    invoke-static {p2}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;->-$$Nest$fgettype(Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;)Ljava/lang/Class;

    move-result-object v0

    invoke-static {v0}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v0

    .line 629
    invoke-static {p2}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;->-$$Nest$fgetname(Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p1, v0, v1}, Lcom/android/dx/TypeId;->getField(Lcom/android/dx/TypeId;Ljava/lang/String;)Lcom/android/dx/FieldId;

    move-result-object v1

    .line 630
    invoke-static {p2}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;->-$$Nest$fgetmodifiers(Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldSpec;)I

    move-result p2

    invoke-static {p2}, Ljava/lang/reflect/Modifier;->isStatic(I)Z

    move-result p2

    .line 631
    invoke-static {p3}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldMethodSpec;->-$$Nest$fgetmodifiers(Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldMethodSpec;)I

    move-result v2

    if-eqz v2, :cond_0

    invoke-static {p3}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldMethodSpec;->-$$Nest$fgetmodifiers(Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldMethodSpec;)I

    move-result v2

    goto :goto_0

    :cond_0
    const/4 v2, 0x1

    .line 633
    :goto_0
    invoke-static {p3}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldMethodSpec;->-$$Nest$fgetgetter(Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldMethodSpec;)Z

    move-result v3

    const/4 v4, 0x0

    if-eqz v3, :cond_2

    .line 634
    invoke-static {p3}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldMethodSpec;->-$$Nest$fgetname(Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldMethodSpec;)Ljava/lang/String;

    move-result-object p3

    new-array v3, v4, [Lcom/android/dx/TypeId;

    invoke-virtual {p1, v0, p3, v3}, Lcom/android/dx/TypeId;->getMethod(Lcom/android/dx/TypeId;Ljava/lang/String;[Lcom/android/dx/TypeId;)Lcom/android/dx/MethodId;

    move-result-object p3

    .line 635
    invoke-virtual {p0, p3, v2}, Lcom/android/dx/DexMaker;->declare(Lcom/android/dx/MethodId;I)Lcom/android/dx/Code;

    move-result-object p0

    .line 636
    invoke-virtual {p0, v0}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object p3

    if-eqz p2, :cond_1

    .line 639
    invoke-virtual {p0, v1, p3}, Lcom/android/dx/Code;->sget(Lcom/android/dx/FieldId;Lcom/android/dx/Local;)V

    goto :goto_1

    .line 641
    :cond_1
    invoke-virtual {p0, p1}, Lcom/android/dx/Code;->getThis(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object p1

    .line 642
    invoke-virtual {p0, v1, p3, p1}, Lcom/android/dx/Code;->iget(Lcom/android/dx/FieldId;Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    .line 645
    :goto_1
    invoke-virtual {p0, p3}, Lcom/android/dx/Code;->returnValue(Lcom/android/dx/Local;)V

    return-void

    .line 649
    :cond_2
    sget-object v3, Lcom/android/dx/TypeId;->VOID:Lcom/android/dx/TypeId;

    invoke-static {p3}, Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldMethodSpec;->-$$Nest$fgetname(Lcom/exteragram/messenger/plugins/utils/ClassProxy$FieldMethodSpec;)Ljava/lang/String;

    move-result-object p3

    filled-new-array {v0}, [Lcom/android/dx/TypeId;

    move-result-object v5

    invoke-virtual {p1, v3, p3, v5}, Lcom/android/dx/TypeId;->getMethod(Lcom/android/dx/TypeId;Ljava/lang/String;[Lcom/android/dx/TypeId;)Lcom/android/dx/MethodId;

    move-result-object p3

    .line 650
    invoke-virtual {p0, p3, v2}, Lcom/android/dx/DexMaker;->declare(Lcom/android/dx/MethodId;I)Lcom/android/dx/Code;

    move-result-object p0

    .line 651
    invoke-virtual {p0, v4, v0}, Lcom/android/dx/Code;->getParameter(ILcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object p3

    if-eqz p2, :cond_3

    .line 654
    invoke-virtual {p0, v1, p3}, Lcom/android/dx/Code;->sput(Lcom/android/dx/FieldId;Lcom/android/dx/Local;)V

    goto :goto_2

    .line 656
    :cond_3
    invoke-virtual {p0, p1}, Lcom/android/dx/Code;->getThis(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object p1

    .line 657
    invoke-virtual {p0, v1, p1, p3}, Lcom/android/dx/Code;->iput(Lcom/android/dx/FieldId;Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    .line 660
    :goto_2
    invoke-virtual {p0}, Lcom/android/dx/Code;->returnVoid()V

    return-void
.end method

.method private static generateHandlerMethod(Lcom/android/dx/DexMaker;Lcom/android/dx/TypeId;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;ILcom/android/dx/FieldId;)V
    .locals 18
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/android/dx/DexMaker;",
            "Lcom/android/dx/TypeId<",
            "*>;",
            "Ljava/lang/String;",
            "Ljava/lang/Class<",
            "*>;[",
            "Ljava/lang/Class<",
            "*>;I",
            "Lcom/android/dx/FieldId;",
            ")V"
        }
    .end annotation

    move-object/from16 v0, p1

    move-object/from16 v1, p2

    move-object/from16 v2, p4

    .line 478
    array-length v3, v2

    new-array v3, v3, [Lcom/android/dx/TypeId;

    const/4 v5, 0x0

    .line 479
    :goto_0
    array-length v6, v2

    if-ge v5, v6, :cond_0

    aget-object v6, v2, v5

    invoke-static {v6}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v6

    aput-object v6, v3, v5

    add-int/lit8 v5, v5, 0x1

    goto :goto_0

    .line 481
    :cond_0
    const-class v5, Lorg/telegram/messenger/Utilities$Callback3Return;

    invoke-static {v5}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v6

    .line 482
    sget-object v7, Lcom/android/dx/TypeId;->OBJECT:Lcom/android/dx/TypeId;

    const-string/jumbo v8, "run"

    filled-new-array {v7, v7, v7}, [Lcom/android/dx/TypeId;

    move-result-object v9

    invoke-virtual {v6, v7, v8, v9}, Lcom/android/dx/TypeId;->getMethod(Lcom/android/dx/TypeId;Ljava/lang/String;[Lcom/android/dx/TypeId;)Lcom/android/dx/MethodId;

    move-result-object v6

    .line 490
    invoke-static/range {p3 .. p3}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v8

    .line 492
    invoke-virtual {v0, v8, v1, v3}, Lcom/android/dx/TypeId;->getMethod(Lcom/android/dx/TypeId;Ljava/lang/String;[Lcom/android/dx/TypeId;)Lcom/android/dx/MethodId;

    move-result-object v9

    move-object/from16 v10, p0

    move/from16 v11, p5

    .line 493
    invoke-virtual {v10, v9, v11}, Lcom/android/dx/DexMaker;->declare(Lcom/android/dx/MethodId;I)Lcom/android/dx/Code;

    move-result-object v9

    .line 495
    invoke-virtual {v9, v0}, Lcom/android/dx/Code;->getThis(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v0

    .line 497
    sget-object v10, Lcom/android/dx/TypeId;->VOID:Lcom/android/dx/TypeId;

    invoke-virtual {v8, v10}, Lcom/android/dx/TypeId;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-nez v10, :cond_1

    invoke-virtual {v9, v8}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v10

    goto :goto_1

    :cond_1
    const/4 v10, 0x0

    .line 499
    :goto_1
    invoke-static {v5}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v5

    invoke-virtual {v9, v5}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v5

    .line 500
    sget-object v11, Lcom/android/dx/TypeId;->STRING:Lcom/android/dx/TypeId;

    invoke-virtual {v9, v11}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v11

    .line 501
    const-class v12, [Ljava/lang/Object;

    invoke-static {v12}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v12

    invoke-virtual {v9, v12}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v12

    .line 502
    sget-object v13, Lcom/android/dx/TypeId;->INT:Lcom/android/dx/TypeId;

    invoke-virtual {v9, v13}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v14

    .line 503
    invoke-virtual {v9, v7}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v7

    .line 504
    invoke-virtual {v9, v13}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v13

    .line 506
    array-length v15, v2

    new-array v15, v15, [Lcom/android/dx/Local;

    move-object/from16 v17, v3

    const/4 v4, 0x0

    .line 507
    :goto_2
    array-length v3, v2

    if-ge v4, v3, :cond_2

    .line 508
    sget-object v3, Lcom/android/dx/TypeId;->OBJECT:Lcom/android/dx/TypeId;

    invoke-virtual {v9, v3}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v3

    aput-object v3, v15, v4

    add-int/lit8 v4, v4, 0x1

    goto :goto_2

    :cond_2
    move-object/from16 v3, p6

    .line 511
    invoke-virtual {v9, v3, v5}, Lcom/android/dx/Code;->sget(Lcom/android/dx/FieldId;Lcom/android/dx/Local;)V

    .line 512
    invoke-static {v1, v2}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->buildMethodSignature(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v9, v11, v1}, Lcom/android/dx/Code;->loadConstant(Lcom/android/dx/Local;Ljava/lang/Object;)V

    .line 514
    array-length v1, v2

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v9, v14, v1}, Lcom/android/dx/Code;->loadConstant(Lcom/android/dx/Local;Ljava/lang/Object;)V

    .line 515
    invoke-virtual {v9, v12, v14}, Lcom/android/dx/Code;->newArray(Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    const/4 v4, 0x0

    .line 516
    :goto_3
    array-length v1, v2

    const-class v3, Lcom/exteragram/messenger/plugins/utils/ClassProxy$TypeHelper;

    if-ge v4, v1, :cond_4

    .line 517
    aget-object v1, v17, v4

    invoke-virtual {v9, v4, v1}, Lcom/android/dx/Code;->getParameter(ILcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v1

    .line 518
    invoke-static {v3}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v3

    sget-object v14, Lcom/android/dx/TypeId;->OBJECT:Lcom/android/dx/TypeId;

    aget-object v16, v2, v4

    invoke-virtual/range {v16 .. v16}, Ljava/lang/Class;->isPrimitive()Z

    move-result v16

    if-eqz v16, :cond_3

    aget-object v16, v17, v4

    :goto_4
    move-object/from16 p0, v1

    goto :goto_5

    :cond_3
    move-object/from16 v16, v14

    goto :goto_4

    :goto_5
    filled-new-array/range {v16 .. v16}, [Lcom/android/dx/TypeId;

    move-result-object v1

    const-string v2, "box"

    invoke-virtual {v3, v14, v2, v1}, Lcom/android/dx/TypeId;->getMethod(Lcom/android/dx/TypeId;Ljava/lang/String;[Lcom/android/dx/TypeId;)Lcom/android/dx/MethodId;

    move-result-object v1

    .line 519
    aget-object v2, v15, v4

    filled-new-array/range {p0 .. p0}, [Lcom/android/dx/Local;

    move-result-object v3

    invoke-virtual {v9, v1, v2, v3}, Lcom/android/dx/Code;->invokeStatic(Lcom/android/dx/MethodId;Lcom/android/dx/Local;[Lcom/android/dx/Local;)V

    .line 520
    invoke-static {v4}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v9, v13, v1}, Lcom/android/dx/Code;->loadConstant(Lcom/android/dx/Local;Ljava/lang/Object;)V

    .line 521
    aget-object v1, v15, v4

    invoke-virtual {v9, v12, v13, v1}, Lcom/android/dx/Code;->aput(Lcom/android/dx/Local;Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    add-int/lit8 v4, v4, 0x1

    move-object/from16 v2, p4

    goto :goto_3

    .line 524
    :cond_4
    filled-new-array {v0, v11, v12}, [Lcom/android/dx/Local;

    move-result-object v0

    invoke-virtual {v9, v6, v7, v5, v0}, Lcom/android/dx/Code;->invokeInterface(Lcom/android/dx/MethodId;Lcom/android/dx/Local;Lcom/android/dx/Local;[Lcom/android/dx/Local;)V

    .line 526
    sget-object v0, Lcom/android/dx/TypeId;->VOID:Lcom/android/dx/TypeId;

    invoke-virtual {v8, v0}, Lcom/android/dx/TypeId;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_5

    .line 527
    invoke-virtual {v9}, Lcom/android/dx/Code;->returnVoid()V

    return-void

    .line 529
    :cond_5
    invoke-virtual/range {p3 .. p3}, Ljava/lang/Class;->isPrimitive()Z

    move-result v0

    if-eqz v0, :cond_6

    .line 530
    new-instance v0, Ljava/lang/StringBuilder;

    const-string/jumbo v1, "unbox"

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual/range {p3 .. p3}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->capitalize(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 531
    invoke-static {v3}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v1

    sget-object v2, Lcom/android/dx/TypeId;->OBJECT:Lcom/android/dx/TypeId;

    filled-new-array {v2}, [Lcom/android/dx/TypeId;

    move-result-object v2

    invoke-virtual {v1, v8, v0, v2}, Lcom/android/dx/TypeId;->getMethod(Lcom/android/dx/TypeId;Ljava/lang/String;[Lcom/android/dx/TypeId;)Lcom/android/dx/MethodId;

    move-result-object v0

    .line 532
    filled-new-array {v7}, [Lcom/android/dx/Local;

    move-result-object v1

    invoke-virtual {v9, v0, v10, v1}, Lcom/android/dx/Code;->invokeStatic(Lcom/android/dx/MethodId;Lcom/android/dx/Local;[Lcom/android/dx/Local;)V

    goto :goto_6

    .line 534
    :cond_6
    invoke-virtual {v9, v10, v7}, Lcom/android/dx/Code;->cast(Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    .line 536
    :goto_6
    invoke-virtual {v9, v10}, Lcom/android/dx/Code;->returnValue(Lcom/android/dx/Local;)V

    return-void
.end method

.method private static generateMvelMethod(Lcom/android/dx/DexMaker;Lcom/android/dx/TypeId;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;ILjava/lang/String;Ljava/util/List;)V
    .locals 23
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/android/dx/DexMaker;",
            "Lcom/android/dx/TypeId<",
            "*>;",
            "Ljava/lang/String;",
            "Ljava/lang/Class<",
            "*>;[",
            "Ljava/lang/Class<",
            "*>;I",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    move-object/from16 v0, p1

    move-object/from16 v1, p4

    move-object/from16 v2, p7

    .line 541
    array-length v3, v1

    new-array v3, v3, [Lcom/android/dx/TypeId;

    const/4 v5, 0x0

    .line 542
    :goto_0
    array-length v6, v1

    if-ge v5, v6, :cond_0

    aget-object v6, v1, v5

    invoke-static {v6}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v6

    aput-object v6, v3, v5

    add-int/lit8 v5, v5, 0x1

    goto :goto_0

    .line 544
    :cond_0
    invoke-static/range {p3 .. p3}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v5

    move-object/from16 v6, p2

    .line 545
    invoke-virtual {v0, v5, v6, v3}, Lcom/android/dx/TypeId;->getMethod(Lcom/android/dx/TypeId;Ljava/lang/String;[Lcom/android/dx/TypeId;)Lcom/android/dx/MethodId;

    move-result-object v6

    move-object/from16 v7, p0

    move/from16 v8, p5

    .line 546
    invoke-virtual {v7, v6, v8}, Lcom/android/dx/DexMaker;->declare(Lcom/android/dx/MethodId;I)Lcom/android/dx/Code;

    move-result-object v6

    .line 548
    invoke-static {v8}, Ljava/lang/reflect/Modifier;->isStatic(I)Z

    move-result v7

    if-eqz v7, :cond_1

    const/4 v7, 0x0

    goto :goto_1

    :cond_1
    invoke-virtual {v6, v0}, Lcom/android/dx/Code;->getThis(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v7

    .line 549
    :goto_1
    sget-object v10, Lcom/android/dx/TypeId;->OBJECT:Lcom/android/dx/TypeId;

    invoke-virtual {v6, v10}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v11

    .line 550
    invoke-virtual {v6, v10}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v12

    .line 551
    invoke-virtual {v6, v10}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v13

    .line 552
    sget-object v14, Lcom/android/dx/TypeId;->STRING:Lcom/android/dx/TypeId;

    invoke-virtual {v6, v14}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v15

    .line 553
    const-class v16, [Ljava/lang/Object;

    invoke-static/range {v16 .. v16}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v4

    invoke-virtual {v6, v4}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v4

    .line 554
    const-class v17, [Ljava/lang/String;

    invoke-static/range {v17 .. v17}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v9

    invoke-virtual {v6, v9}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v9

    move-object/from16 v18, v3

    .line 555
    sget-object v3, Lcom/android/dx/TypeId;->INT:Lcom/android/dx/TypeId;

    invoke-virtual {v6, v3}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v8

    .line 556
    invoke-virtual {v6, v3}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v3

    .line 557
    invoke-virtual {v6, v10}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v10

    move-object/from16 p2, v13

    .line 558
    invoke-static/range {v17 .. v17}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v13

    invoke-virtual {v6, v13}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v13

    .line 559
    invoke-virtual {v6, v14}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v14

    move-object/from16 v19, v13

    .line 560
    sget-object v13, Lcom/android/dx/TypeId;->VOID:Lcom/android/dx/TypeId;

    invoke-virtual {v5, v13}, Lcom/android/dx/TypeId;->equals(Ljava/lang/Object;)Z

    move-result v13

    if-eqz v13, :cond_2

    const/16 v20, 0x0

    goto :goto_2

    :cond_2
    invoke-virtual {v6, v5}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v13

    move-object/from16 v20, v13

    .line 561
    :goto_2
    array-length v13, v1

    new-array v13, v13, [Lcom/android/dx/Local;

    move-object/from16 v22, v5

    move-object/from16 v21, v13

    const/4 v13, 0x0

    .line 562
    :goto_3
    array-length v5, v1

    if-ge v13, v5, :cond_3

    .line 563
    sget-object v5, Lcom/android/dx/TypeId;->OBJECT:Lcom/android/dx/TypeId;

    invoke-virtual {v6, v5}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v5

    aput-object v5, v21, v13

    add-int/lit8 v13, v13, 0x1

    goto :goto_3

    :cond_3
    const/4 v5, 0x0

    .line 566
    invoke-virtual {v6, v10, v5}, Lcom/android/dx/Code;->loadConstant(Lcom/android/dx/Local;Ljava/lang/Object;)V

    .line 567
    invoke-virtual {v6, v11, v10}, Lcom/android/dx/Code;->move(Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    .line 568
    invoke-virtual {v6, v12, v10}, Lcom/android/dx/Code;->move(Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    .line 570
    invoke-static/range {p5 .. p5}, Ljava/lang/reflect/Modifier;->isStatic(I)Z

    move-result v5

    if-nez v5, :cond_4

    .line 571
    sget-object v5, Lcom/android/dx/TypeId;->OBJECT:Lcom/android/dx/TypeId;

    const-string v10, "__extera_internal_python_peer__"

    invoke-virtual {v0, v5, v10}, Lcom/android/dx/TypeId;->getField(Lcom/android/dx/TypeId;Ljava/lang/String;)Lcom/android/dx/FieldId;

    move-result-object v0

    .line 572
    invoke-virtual {v6, v11, v7}, Lcom/android/dx/Code;->cast(Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    .line 573
    invoke-virtual {v6, v0, v12, v7}, Lcom/android/dx/Code;->iget(Lcom/android/dx/FieldId;Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    :cond_4
    move-object/from16 v0, p6

    .line 576
    invoke-virtual {v6, v15, v0}, Lcom/android/dx/Code;->loadConstant(Lcom/android/dx/Local;Ljava/lang/Object;)V

    .line 577
    array-length v0, v1

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {v6, v8, v0}, Lcom/android/dx/Code;->loadConstant(Lcom/android/dx/Local;Ljava/lang/Object;)V

    .line 578
    invoke-virtual {v6, v4, v8}, Lcom/android/dx/Code;->newArray(Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    const/4 v0, 0x0

    .line 580
    :goto_4
    array-length v5, v1

    const-class v7, Lcom/exteragram/messenger/plugins/utils/ClassProxy$TypeHelper;

    if-ge v0, v5, :cond_6

    .line 581
    aget-object v5, v18, v0

    invoke-virtual {v6, v0, v5}, Lcom/android/dx/Code;->getParameter(ILcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v5

    .line 582
    invoke-static {v7}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v7

    sget-object v10, Lcom/android/dx/TypeId;->OBJECT:Lcom/android/dx/TypeId;

    aget-object v13, v1, v0

    invoke-virtual {v13}, Ljava/lang/Class;->isPrimitive()Z

    move-result v13

    if-eqz v13, :cond_5

    aget-object v13, v18, v0

    goto :goto_5

    :cond_5
    move-object v13, v10

    :goto_5
    filled-new-array {v13}, [Lcom/android/dx/TypeId;

    move-result-object v13

    move/from16 p1, v0

    const-string v0, "box"

    invoke-virtual {v7, v10, v0, v13}, Lcom/android/dx/TypeId;->getMethod(Lcom/android/dx/TypeId;Ljava/lang/String;[Lcom/android/dx/TypeId;)Lcom/android/dx/MethodId;

    move-result-object v0

    .line 583
    aget-object v7, v21, p1

    filled-new-array {v5}, [Lcom/android/dx/Local;

    move-result-object v5

    invoke-virtual {v6, v0, v7, v5}, Lcom/android/dx/Code;->invokeStatic(Lcom/android/dx/MethodId;Lcom/android/dx/Local;[Lcom/android/dx/Local;)V

    .line 584
    invoke-static/range {p1 .. p1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {v6, v3, v0}, Lcom/android/dx/Code;->loadConstant(Lcom/android/dx/Local;Ljava/lang/Object;)V

    .line 585
    aget-object v0, v21, p1

    invoke-virtual {v6, v4, v3, v0}, Lcom/android/dx/Code;->aput(Lcom/android/dx/Local;Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    add-int/lit8 v0, p1, 0x1

    goto :goto_4

    :cond_6
    if-eqz v2, :cond_7

    .line 588
    invoke-interface {v2}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_7

    .line 589
    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    invoke-virtual {v6, v8, v0}, Lcom/android/dx/Code;->loadConstant(Lcom/android/dx/Local;Ljava/lang/Object;)V

    .line 590
    invoke-virtual {v6, v9, v8}, Lcom/android/dx/Code;->newArray(Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    const/4 v0, 0x0

    .line 592
    :goto_6
    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v1

    if-ge v0, v1, :cond_8

    .line 593
    invoke-interface {v2, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    invoke-virtual {v6, v14, v1}, Lcom/android/dx/Code;->loadConstant(Lcom/android/dx/Local;Ljava/lang/Object;)V

    .line 594
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {v6, v3, v1}, Lcom/android/dx/Code;->loadConstant(Lcom/android/dx/Local;Ljava/lang/Object;)V

    .line 595
    invoke-virtual {v6, v9, v3, v14}, Lcom/android/dx/Code;->aput(Lcom/android/dx/Local;Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    add-int/lit8 v0, v0, 0x1

    goto :goto_6

    :cond_7
    move-object/from16 v0, v19

    const/4 v5, 0x0

    .line 598
    invoke-virtual {v6, v0, v5}, Lcom/android/dx/Code;->loadConstant(Lcom/android/dx/Local;Ljava/lang/Object;)V

    .line 599
    invoke-virtual {v6, v9, v0}, Lcom/android/dx/Code;->move(Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    .line 602
    :cond_8
    const-class v0, Lcom/exteragram/messenger/plugins/utils/ClassProxy;

    invoke-static {v0}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v0

    sget-object v1, Lcom/android/dx/TypeId;->OBJECT:Lcom/android/dx/TypeId;

    sget-object v2, Lcom/android/dx/TypeId;->STRING:Lcom/android/dx/TypeId;

    .line 608
    invoke-static/range {v17 .. v17}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v3

    .line 609
    invoke-static/range {v16 .. v16}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v5

    filled-new-array {v1, v1, v2, v3, v5}, [Lcom/android/dx/TypeId;

    move-result-object v2

    .line 602
    const-string v3, "executeMvelMethod"

    invoke-virtual {v0, v1, v3, v2}, Lcom/android/dx/TypeId;->getMethod(Lcom/android/dx/TypeId;Ljava/lang/String;[Lcom/android/dx/TypeId;)Lcom/android/dx/MethodId;

    move-result-object v0

    .line 611
    filled-new-array {v11, v12, v15, v9, v4}, [Lcom/android/dx/Local;

    move-result-object v2

    move-object/from16 v3, p2

    invoke-virtual {v6, v0, v3, v2}, Lcom/android/dx/Code;->invokeStatic(Lcom/android/dx/MethodId;Lcom/android/dx/Local;[Lcom/android/dx/Local;)V

    .line 613
    sget-object v0, Lcom/android/dx/TypeId;->VOID:Lcom/android/dx/TypeId;

    move-object/from16 v2, v22

    invoke-virtual {v2, v0}, Lcom/android/dx/TypeId;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_9

    .line 614
    invoke-virtual {v6}, Lcom/android/dx/Code;->returnVoid()V

    return-void

    .line 616
    :cond_9
    invoke-virtual/range {p3 .. p3}, Ljava/lang/Class;->isPrimitive()Z

    move-result v0

    if-eqz v0, :cond_a

    .line 617
    new-instance v0, Ljava/lang/StringBuilder;

    const-string/jumbo v4, "unbox"

    invoke-direct {v0, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual/range {p3 .. p3}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v4

    invoke-static {v4}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->capitalize(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 618
    invoke-static {v7}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v4

    filled-new-array {v1}, [Lcom/android/dx/TypeId;

    move-result-object v1

    invoke-virtual {v4, v2, v0, v1}, Lcom/android/dx/TypeId;->getMethod(Lcom/android/dx/TypeId;Ljava/lang/String;[Lcom/android/dx/TypeId;)Lcom/android/dx/MethodId;

    move-result-object v0

    .line 619
    filled-new-array {v3}, [Lcom/android/dx/Local;

    move-result-object v1

    move-object/from16 v13, v20

    invoke-virtual {v6, v0, v13, v1}, Lcom/android/dx/Code;->invokeStatic(Lcom/android/dx/MethodId;Lcom/android/dx/Local;[Lcom/android/dx/Local;)V

    goto :goto_7

    :cond_a
    move-object/from16 v13, v20

    .line 621
    invoke-virtual {v6, v13, v3}, Lcom/android/dx/Code;->cast(Lcom/android/dx/Local;Lcom/android/dx/Local;)V

    .line 623
    :goto_7
    invoke-virtual {v6, v13}, Lcom/android/dx/Code;->returnValue(Lcom/android/dx/Local;)V

    return-void
.end method

.method private static generateProxyMethod(Lcom/android/dx/DexMaker;Lcom/android/dx/TypeId;Ljava/lang/reflect/Method;Lcom/android/dx/FieldId;Z)V
    .locals 7
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lcom/android/dx/DexMaker;",
            "Lcom/android/dx/TypeId<",
            "*>;",
            "Ljava/lang/reflect/Method;",
            "Lcom/android/dx/FieldId;",
            "Z)V"
        }
    .end annotation

    .line 452
    invoke-virtual {p2}, Ljava/lang/reflect/Method;->getParameterTypes()[Ljava/lang/Class;

    move-result-object v4

    .line 453
    array-length v0, v4

    new-array v0, v0, [Lcom/android/dx/TypeId;

    const/4 v1, 0x0

    move v2, v1

    .line 454
    :goto_0
    array-length v3, v4

    if-ge v2, v3, :cond_0

    aget-object v3, v4, v2

    invoke-static {v3}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v3

    aput-object v3, v0, v2

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 456
    :cond_0
    invoke-virtual {p2}, Ljava/lang/reflect/Method;->getReturnType()Ljava/lang/Class;

    move-result-object v2

    invoke-static {v2}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v2

    if-eqz p4, :cond_1

    .line 457
    new-instance v3, Ljava/lang/StringBuilder;

    const-string v5, "SUPER_"

    invoke-direct {v3, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2}, Ljava/lang/reflect/Method;->getName()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    goto :goto_1

    :cond_1
    invoke-virtual {p2}, Ljava/lang/reflect/Method;->getName()Ljava/lang/String;

    move-result-object v3

    :goto_1
    if-eqz p4, :cond_5

    .line 460
    invoke-virtual {p1, v2, v3, v0}, Lcom/android/dx/TypeId;->getMethod(Lcom/android/dx/TypeId;Ljava/lang/String;[Lcom/android/dx/TypeId;)Lcom/android/dx/MethodId;

    move-result-object p3

    const/4 p4, 0x1

    .line 461
    invoke-virtual {p0, p3, p4}, Lcom/android/dx/DexMaker;->declare(Lcom/android/dx/MethodId;I)Lcom/android/dx/Code;

    move-result-object p0

    .line 463
    invoke-virtual {p0, p1}, Lcom/android/dx/Code;->getThis(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object p1

    .line 464
    sget-object p3, Lcom/android/dx/TypeId;->VOID:Lcom/android/dx/TypeId;

    invoke-virtual {v2, p3}, Lcom/android/dx/TypeId;->equals(Ljava/lang/Object;)Z

    move-result p3

    if-eqz p3, :cond_2

    const/4 p3, 0x0

    goto :goto_2

    :cond_2
    invoke-virtual {p0, v2}, Lcom/android/dx/Code;->newLocal(Lcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object p3

    .line 465
    :goto_2
    array-length p4, v4

    new-array p4, p4, [Lcom/android/dx/Local;

    .line 466
    :goto_3
    array-length v3, v4

    if-ge v1, v3, :cond_3

    aget-object v3, v0, v1

    invoke-virtual {p0, v1, v3}, Lcom/android/dx/Code;->getParameter(ILcom/android/dx/TypeId;)Lcom/android/dx/Local;

    move-result-object v3

    aput-object v3, p4, v1

    add-int/lit8 v1, v1, 0x1

    goto :goto_3

    .line 467
    :cond_3
    invoke-virtual {p2}, Ljava/lang/reflect/Method;->getDeclaringClass()Ljava/lang/Class;

    move-result-object v1

    invoke-static {v1}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v1

    invoke-virtual {p2}, Ljava/lang/reflect/Method;->getName()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {v1, v2, p2, v0}, Lcom/android/dx/TypeId;->getMethod(Lcom/android/dx/TypeId;Ljava/lang/String;[Lcom/android/dx/TypeId;)Lcom/android/dx/MethodId;

    move-result-object p2

    .line 468
    invoke-virtual {p0, p2, p3, p1, p4}, Lcom/android/dx/Code;->invokeSuper(Lcom/android/dx/MethodId;Lcom/android/dx/Local;Lcom/android/dx/Local;[Lcom/android/dx/Local;)V

    .line 469
    sget-object p1, Lcom/android/dx/TypeId;->VOID:Lcom/android/dx/TypeId;

    invoke-virtual {v2, p1}, Lcom/android/dx/TypeId;->equals(Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_4

    invoke-virtual {p0}, Lcom/android/dx/Code;->returnVoid()V

    return-void

    .line 470
    :cond_4
    invoke-virtual {p0, p3}, Lcom/android/dx/Code;->returnValue(Lcom/android/dx/Local;)V

    return-void

    .line 474
    :cond_5
    invoke-virtual {p2}, Ljava/lang/reflect/Method;->getReturnType()Ljava/lang/Class;

    move-result-object p2

    const/4 v5, 0x1

    move-object v0, p0

    move-object v1, p1

    move-object v6, p3

    move-object v2, v3

    move-object v3, p2

    invoke-static/range {v0 .. v6}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->generateHandlerMethod(Lcom/android/dx/DexMaker;Lcom/android/dx/TypeId;Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Class;ILcom/android/dx/FieldId;)V

    return-void
.end method

.method private static getAllOverridableMethods(Ljava/lang/Class;Ljava/util/List;)Ljava/util/Map;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "*>;",
            "Ljava/util/List<",
            "Ljava/lang/Class<",
            "*>;>;)",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/reflect/Method;",
            ">;"
        }
    .end annotation

    .line 315
    new-instance v0, Ljava/util/LinkedHashMap;

    invoke-direct {v0}, Ljava/util/LinkedHashMap;-><init>()V

    .line 316
    invoke-static {p0, v0}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->collectOverridableMethods(Ljava/lang/Class;Ljava/util/Map;)V

    if-eqz p1, :cond_0

    .line 318
    invoke-interface {p1}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result p1

    if-eqz p1, :cond_0

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/Class;

    .line 319
    invoke-static {p1, v0}, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->collectOverridableMethods(Ljava/lang/Class;Ljava/util/Map;)V

    goto :goto_0

    :cond_0
    return-object v0
.end method

.method public static runPythonRunnable(Lcom/chaquo/python/PyObject;)V
    .locals 1
    .annotation build Landroidx/annotation/Keep;
    .end annotation

    if-nez p0, :cond_0

    .line 62
    const-string p0, "Runnable callback is null"

    invoke-static {p0}, Lcom/exteragram/messenger/utils/AppUtils;->log(Ljava/lang/String;)V

    return-void

    .line 67
    :cond_0
    :try_start_0
    sget-object v0, Lcom/exteragram/messenger/plugins/utils/ClassProxy;->EMPTY_ARGS:[Ljava/lang/Object;

    invoke-virtual {p0, v0}, Lcom/chaquo/python/PyObject;->call([Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 69
    const-string v0, "Error in runnable"

    invoke-static {v0, p0}, Lcom/exteragram/messenger/utils/AppUtils;->log(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-void
.end method

.method private static sanitizeProxyClassSegment(Ljava/lang/String;)Ljava/lang/String;
    .locals 4

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return-object v0

    .line 279
    :cond_0
    invoke-virtual {p0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object p0

    .line 280
    invoke-virtual {p0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_1

    return-object v0

    .line 284
    :cond_1
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v1

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    const/4 v1, 0x0

    .line 285
    :goto_0
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v2

    if-ge v1, v2, :cond_4

    .line 286
    invoke-virtual {p0, v1}, Ljava/lang/String;->charAt(I)C

    move-result v2

    .line 287
    invoke-static {v2}, Ljava/lang/Character;->isLetterOrDigit(C)Z

    move-result v3

    if-nez v3, :cond_3

    const/16 v3, 0x5f

    if-ne v2, v3, :cond_2

    goto :goto_1

    .line 290
    :cond_2
    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    goto :goto_2

    .line 288
    :cond_3
    :goto_1
    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    :goto_2
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 293
    :cond_4
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static toInterfaceTypeIds(Ljava/util/List;)[Lcom/android/dx/TypeId;
    .locals 4
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Ljava/lang/Class<",
            "*>;>;)[",
            "Lcom/android/dx/TypeId<",
            "*>;"
        }
    .end annotation

    const/4 v0, 0x0

    if-eqz p0, :cond_4

    .line 297
    invoke-interface {p0}, Ljava/util/List;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_1

    .line 301
    :cond_0
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 302
    invoke-interface {p0}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p0

    :goto_0
    invoke-interface {p0}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_3

    invoke-interface {p0}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Class;

    if-nez v2, :cond_1

    goto :goto_0

    .line 306
    :cond_1
    invoke-virtual {v2}, Ljava/lang/Class;->isInterface()Z

    move-result v3

    if-eqz v3, :cond_2

    .line 309
    invoke-static {v2}, Lcom/android/dx/TypeId;->get(Ljava/lang/Class;)Lcom/android/dx/TypeId;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    goto :goto_0

    .line 307
    :cond_2
    invoke-virtual {v2}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p0

    const-string v0, "Expected interface, got "

    invoke-virtual {v0, p0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    .line 311
    :cond_3
    new-array p0, v0, [Lcom/android/dx/TypeId;

    invoke-virtual {v1, p0}, Ljava/util/ArrayList;->toArray([Ljava/lang/Object;)[Ljava/lang/Object;

    move-result-object p0

    check-cast p0, [Lcom/android/dx/TypeId;

    return-object p0

    .line 298
    :cond_4
    :goto_1
    new-array p0, v0, [Lcom/android/dx/TypeId;

    return-object p0
.end method
