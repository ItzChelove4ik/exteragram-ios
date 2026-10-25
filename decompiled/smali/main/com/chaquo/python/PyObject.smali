.class public Lcom/chaquo/python/PyObject;
.super Ljava/util/AbstractMap;
.source "SourceFile"

# interfaces
.implements Ljava/lang/AutoCloseable;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/chaquo/python/PyObject$Ref;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/util/AbstractMap<",
        "Ljava/lang/String;",
        "Lcom/chaquo/python/PyObject;",
        ">;",
        "Ljava/lang/AutoCloseable;"
    }
.end annotation


# static fields
.field private static final cache:Ljava/util/Map;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/Map<",
            "Ljava/lang/Long;",
            "Lcom/chaquo/python/PyObject$Ref;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field public addr:J

.field private final ref:Lcom/chaquo/python/PyObject$Ref;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 25
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    sput-object v0, Lcom/chaquo/python/PyObject;->cache:Ljava/util/Map;

    return-void
.end method

.method private constructor <init>(J)V
    .locals 0

    .line 57
    invoke-direct {p0}, Ljava/util/AbstractMap;-><init>()V

    .line 58
    iput-wide p1, p0, Lcom/chaquo/python/PyObject;->addr:J

    .line 59
    new-instance p1, Lcom/chaquo/python/PyObject$Ref;

    invoke-direct {p1, p0}, Lcom/chaquo/python/PyObject$Ref;-><init>(Lcom/chaquo/python/PyObject;)V

    iput-object p1, p0, Lcom/chaquo/python/PyObject;->ref:Lcom/chaquo/python/PyObject$Ref;

    return-void
.end method

.method public static varargs _chaquopyCall(Lcom/chaquo/python/StaticProxy;Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    .locals 0

    .line 346
    :try_start_0
    invoke-static {p0}, Lcom/chaquo/python/PyObject;->fromJava(Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p0

    invoke-virtual {p0, p1, p2}, Lcom/chaquo/python/PyObject;->callAttrThrows(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/lang/Error; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-object p0

    :catchall_0
    move-exception p0

    .line 350
    new-instance p1, Ljava/lang/reflect/UndeclaredThrowableException;

    invoke-direct {p1, p0}, Ljava/lang/reflect/UndeclaredThrowableException;-><init>(Ljava/lang/Throwable;)V

    throw p1

    :catch_0
    move-exception p0

    .line 348
    throw p0
.end method

.method public static synthetic access$100()Ljava/util/Map;
    .locals 1

    .line 22
    sget-object v0, Lcom/chaquo/python/PyObject;->cache:Ljava/util/Map;

    return-object v0
.end method

.method public static synthetic access$200([JI)V
    .locals 0

    .line 22
    invoke-static {p0, p1}, Lcom/chaquo/python/PyObject;->releaseNative([JI)V

    return-void
.end method

.method public static synthetic access$300(Lcom/chaquo/python/PyObject;)Ljava/util/List;
    .locals 0

    .line 22
    invoke-direct {p0}, Lcom/chaquo/python/PyObject;->dir()Ljava/util/List;

    move-result-object p0

    return-object p0
.end method

.method private varargs native callAttrThrowsNative(Ljava/lang/String;[Ljava/lang/Object;)J
.end method

.method private varargs native callThrowsNative([Ljava/lang/Object;)J
.end method

.method private static native closeNative(JJ)V
.end method

.method private native containsKeyNative(Ljava/lang/String;)Z
.end method

.method private native dir()Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end method

.method public static fromJava(Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    .locals 2

    .line 194
    invoke-static {p0}, Lcom/chaquo/python/PyObject;->fromJavaNative(Ljava/lang/Object;)J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/chaquo/python/PyObject;->getInstance(J)Lcom/chaquo/python/PyObject;

    move-result-object p0

    return-object p0
.end method

.method private static native fromJavaNative(Ljava/lang/Object;)J
.end method

.method public static getInstance(J)Lcom/chaquo/python/PyObject;
    .locals 5

    const-wide/16 v0, 0x0

    cmp-long v0, p0, v0

    if-nez v0, :cond_0

    const/4 p0, 0x0

    return-object p0

    .line 35
    :cond_0
    sget-object v0, Lcom/chaquo/python/PyObject;->cache:Ljava/util/Map;

    monitor-enter v0

    .line 36
    :try_start_0
    invoke-static {p0, p1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-interface {v0, v1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/chaquo/python/PyObject$Ref;

    if-eqz v1, :cond_1

    .line 41
    iget-object v2, v1, Lcom/chaquo/python/PyObject$Ref;->weak:Ljava/lang/ref/WeakReference;

    invoke-virtual {v2}, Ljava/lang/ref/Reference;->get()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/chaquo/python/PyObject;

    if-eqz v2, :cond_1

    .line 46
    iget-wide p0, v1, Lcom/chaquo/python/PyObject$Ref;->count:J

    const-wide/16 v3, 0x1

    add-long/2addr p0, v3

    iput-wide p0, v1, Lcom/chaquo/python/PyObject$Ref;->count:J

    .line 47
    monitor-exit v0

    return-object v2

    :catchall_0
    move-exception p0

    goto :goto_0

    .line 50
    :cond_1
    new-instance v1, Lcom/chaquo/python/PyObject;

    invoke-direct {v1, p0, p1}, Lcom/chaquo/python/PyObject;-><init>(J)V

    .line 51
    invoke-static {p0, p1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p0

    iget-object p1, v1, Lcom/chaquo/python/PyObject;->ref:Lcom/chaquo/python/PyObject$Ref;

    invoke-interface {v0, p0, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 52
    iget-object p0, v1, Lcom/chaquo/python/PyObject;->ref:Lcom/chaquo/python/PyObject$Ref;

    invoke-virtual {p0}, Lcom/chaquo/python/PyObject$Ref;->add()V

    .line 53
    monitor-exit v0

    return-object v1

    .line 54
    :goto_0
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw p0
.end method

.method private native getNative(Ljava/lang/String;)J
.end method

.method private native putNative(Ljava/lang/String;Ljava/lang/Object;)J
.end method

.method private static native releaseNative([JI)V
.end method

.method private native removeNative(Ljava/lang/String;)J
.end method

.method private native typeNative()J
.end method


# virtual methods
.method public asList()Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/chaquo/python/PyObject;",
            ">;"
        }
    .end annotation

    .line 266
    new-instance v0, Lcom/chaquo/python/PyList;

    invoke-direct {v0, p0}, Lcom/chaquo/python/PyList;-><init>(Lcom/chaquo/python/PyObject;)V

    return-object v0
.end method

.method public asMap()Ljava/util/Map;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Map<",
            "Lcom/chaquo/python/PyObject;",
            "Lcom/chaquo/python/PyObject;",
            ">;"
        }
    .end annotation

    .line 280
    new-instance v0, Lcom/chaquo/python/PyMap;

    invoke-direct {v0, p0}, Lcom/chaquo/python/PyMap;-><init>(Lcom/chaquo/python/PyObject;)V

    return-object v0
.end method

.method public asSet()Ljava/util/Set;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Set<",
            "Lcom/chaquo/python/PyObject;",
            ">;"
        }
    .end annotation

    .line 290
    new-instance v0, Lcom/chaquo/python/PySet;

    invoke-direct {v0, p0}, Lcom/chaquo/python/PySet;-><init>(Lcom/chaquo/python/PyObject;)V

    return-object v0
.end method

.method public varargs call([Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    .locals 0

    .line 309
    :try_start_0
    invoke-virtual {p0, p1}, Lcom/chaquo/python/PyObject;->callThrows([Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p0
    :try_end_0
    .catch Lcom/chaquo/python/PyException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-object p0

    :catchall_0
    move-exception p0

    .line 313
    new-instance p1, Lcom/chaquo/python/PyException;

    invoke-direct {p1, p0}, Lcom/chaquo/python/PyException;-><init>(Ljava/lang/Throwable;)V

    throw p1

    :catch_0
    move-exception p0

    .line 311
    throw p0
.end method

.method public varargs callAttr(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    .locals 0

    .line 328
    :try_start_0
    invoke-virtual {p0, p1, p2}, Lcom/chaquo/python/PyObject;->callAttrThrows(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p0
    :try_end_0
    .catch Lcom/chaquo/python/PyException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-object p0

    :catchall_0
    move-exception p0

    .line 332
    new-instance p1, Lcom/chaquo/python/PyException;

    invoke-direct {p1, p0}, Lcom/chaquo/python/PyException;-><init>(Ljava/lang/Throwable;)V

    throw p1

    :catch_0
    move-exception p0

    .line 330
    throw p0
.end method

.method public varargs callAttrThrows(Ljava/lang/String;[Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    .locals 0

    .line 339
    invoke-direct {p0, p1, p2}, Lcom/chaquo/python/PyObject;->callAttrThrowsNative(Ljava/lang/String;[Ljava/lang/Object;)J

    move-result-wide p0

    invoke-static {p0, p1}, Lcom/chaquo/python/PyObject;->getInstance(J)Lcom/chaquo/python/PyObject;

    move-result-object p0

    return-object p0
.end method

.method public varargs callThrows([Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    .locals 0

    .line 320
    invoke-direct {p0, p1}, Lcom/chaquo/python/PyObject;->callThrowsNative([Ljava/lang/Object;)J

    move-result-wide p0

    invoke-static {p0, p1}, Lcom/chaquo/python/PyObject;->getInstance(J)Lcom/chaquo/python/PyObject;

    move-result-object p0

    return-object p0
.end method

.method public clear()V
    .locals 0

    .line 359
    invoke-super {p0}, Ljava/util/AbstractMap;->clear()V

    return-void
.end method

.method public close()V
    .locals 6

    .line 74
    iget-wide v0, p0, Lcom/chaquo/python/PyObject;->addr:J

    const-wide/16 v2, 0x0

    cmp-long v0, v0, v2

    if-nez v0, :cond_0

    return-void

    .line 76
    :cond_0
    sget-object v0, Lcom/chaquo/python/PyObject;->cache:Ljava/util/Map;

    monitor-enter v0

    .line 77
    :try_start_0
    iget-object v1, p0, Lcom/chaquo/python/PyObject;->ref:Lcom/chaquo/python/PyObject$Ref;

    invoke-virtual {v1}, Lcom/chaquo/python/PyObject$Ref;->take()J

    move-result-wide v4

    .line 78
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    cmp-long v0, v4, v2

    if-eqz v0, :cond_1

    .line 83
    iget-object v0, p0, Lcom/chaquo/python/PyObject;->ref:Lcom/chaquo/python/PyObject$Ref;

    iget-wide v0, v0, Lcom/chaquo/python/PyObject$Ref;->addr:J

    invoke-static {v0, v1, v4, v5}, Lcom/chaquo/python/PyObject;->closeNative(JJ)V

    .line 85
    :cond_1
    iput-wide v2, p0, Lcom/chaquo/python/PyObject;->addr:J

    return-void

    :catchall_0
    move-exception p0

    .line 78
    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p0
.end method

.method public containsKey(Ljava/lang/Object;)Z
    .locals 0

    .line 368
    check-cast p1, Ljava/lang/String;

    invoke-direct {p0, p1}, Lcom/chaquo/python/PyObject;->containsKeyNative(Ljava/lang/String;)Z

    move-result p0

    return p0
.end method

.method public containsValue(Ljava/lang/Object;)Z
    .locals 0

    .line 375
    invoke-static {p1}, Lcom/chaquo/python/PyObject;->fromJava(Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p1

    invoke-super {p0, p1}, Ljava/util/AbstractMap;->containsValue(Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public entrySet()Ljava/util/Set;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Set<",
            "Ljava/util/Map$Entry<",
            "Ljava/lang/String;",
            "Lcom/chaquo/python/PyObject;",
            ">;>;"
        }
    .end annotation

    .line 424
    new-instance v0, Lcom/chaquo/python/PyObject$1;

    invoke-direct {v0, p0}, Lcom/chaquo/python/PyObject$1;-><init>(Lcom/chaquo/python/PyObject;)V

    return-object v0
.end method

.method public native equals(Ljava/lang/Object;)Z
.end method

.method public get(Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    .locals 0

    .line 383
    check-cast p1, Ljava/lang/String;

    invoke-direct {p0, p1}, Lcom/chaquo/python/PyObject;->getNative(Ljava/lang/String;)J

    move-result-wide p0

    invoke-static {p0, p1}, Lcom/chaquo/python/PyObject;->getInstance(J)Lcom/chaquo/python/PyObject;

    move-result-object p0

    return-object p0
.end method

.method public bridge synthetic get(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 22
    invoke-virtual {p0, p1}, Lcom/chaquo/python/PyObject;->get(Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p0

    return-object p0
.end method

.method public native hashCode()I
.end method

.method public native id()J
.end method

.method public isEmpty()Z
    .locals 0

    .line 364
    invoke-super {p0}, Ljava/util/AbstractMap;->isEmpty()Z

    move-result p0

    return p0
.end method

.method public keySet()Ljava/util/Set;
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/Set<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 420
    invoke-super {p0}, Ljava/util/AbstractMap;->keySet()Ljava/util/Set;

    move-result-object p0

    return-object p0
.end method

.method public put(Ljava/lang/String;Lcom/chaquo/python/PyObject;)Lcom/chaquo/python/PyObject;
    .locals 0

    .line 389
    invoke-virtual {p0, p1, p2}, Lcom/chaquo/python/PyObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p0

    return-object p0
.end method

.method public put(Ljava/lang/String;Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    .locals 0

    .line 395
    invoke-direct {p0, p1, p2}, Lcom/chaquo/python/PyObject;->putNative(Ljava/lang/String;Ljava/lang/Object;)J

    move-result-wide p0

    invoke-static {p0, p1}, Lcom/chaquo/python/PyObject;->getInstance(J)Lcom/chaquo/python/PyObject;

    move-result-object p0

    return-object p0
.end method

.method public bridge synthetic put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 22
    check-cast p1, Ljava/lang/String;

    check-cast p2, Lcom/chaquo/python/PyObject;

    invoke-virtual {p0, p1, p2}, Lcom/chaquo/python/PyObject;->put(Ljava/lang/String;Lcom/chaquo/python/PyObject;)Lcom/chaquo/python/PyObject;

    move-result-object p0

    return-object p0
.end method

.method public remove(Ljava/lang/Object;)Lcom/chaquo/python/PyObject;
    .locals 0

    .line 406
    check-cast p1, Ljava/lang/String;

    invoke-direct {p0, p1}, Lcom/chaquo/python/PyObject;->removeNative(Ljava/lang/String;)J

    move-result-wide p0

    invoke-static {p0, p1}, Lcom/chaquo/python/PyObject;->getInstance(J)Lcom/chaquo/python/PyObject;

    move-result-object p0

    return-object p0
.end method

.method public bridge synthetic remove(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0

    .line 22
    invoke-virtual {p0, p1}, Lcom/chaquo/python/PyObject;->remove(Ljava/lang/Object;)Lcom/chaquo/python/PyObject;

    move-result-object p0

    return-object p0
.end method

.method public native repr()Ljava/lang/String;
.end method

.method public native toBoolean()Z
.end method

.method public native toByte()B
.end method

.method public native toChar()C
.end method

.method public native toDouble()D
.end method

.method public native toFloat()F
.end method

.method public native toInt()I
.end method

.method public native toJava(Ljava/lang/Class;)Ljava/lang/Object;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/lang/Class<",
            "TT;>;)TT;"
        }
    .end annotation
.end method

.method public native toLong()J
.end method

.method public native toShort()S
.end method

.method public native toString()Ljava/lang/String;
.end method

.method public type()Lcom/chaquo/python/PyObject;
    .locals 2

    .line 300
    invoke-direct {p0}, Lcom/chaquo/python/PyObject;->typeNative()J

    move-result-wide v0

    invoke-static {v0, v1}, Lcom/chaquo/python/PyObject;->getInstance(J)Lcom/chaquo/python/PyObject;

    move-result-object p0

    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    return-object p0
.end method
