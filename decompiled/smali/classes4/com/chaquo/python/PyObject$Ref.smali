.class final Lcom/chaquo/python/PyObject$Ref;
.super Ljava/lang/ref/PhantomReference;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/chaquo/python/PyObject;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Ref"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/ref/PhantomReference<",
        "Lcom/chaquo/python/PyObject;",
        ">;"
    }
.end annotation


# static fields
.field private static final BATCH_SIZE:I = 0x400

.field private static head:Lcom/chaquo/python/PyObject$Ref;

.field private static final queue:Ljava/lang/ref/ReferenceQueue;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/ReferenceQueue<",
            "Lcom/chaquo/python/PyObject;",
            ">;"
        }
    .end annotation
.end field

.field private static releaser:Ljava/lang/Thread;


# instance fields
.field final addr:J

.field count:J

.field private next:Lcom/chaquo/python/PyObject$Ref;

.field private prev:Lcom/chaquo/python/PyObject$Ref;

.field final weak:Ljava/lang/ref/WeakReference;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/ref/WeakReference<",
            "Lcom/chaquo/python/PyObject;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 97
    new-instance v0, Ljava/lang/ref/ReferenceQueue;

    invoke-direct {v0}, Ljava/lang/ref/ReferenceQueue;-><init>()V

    sput-object v0, Lcom/chaquo/python/PyObject$Ref;->queue:Ljava/lang/ref/ReferenceQueue;

    return-void
.end method

.method public constructor <init>(Lcom/chaquo/python/PyObject;)V
    .locals 2

    .line 112
    sget-object v0, Lcom/chaquo/python/PyObject$Ref;->queue:Ljava/lang/ref/ReferenceQueue;

    invoke-direct {p0, p1, v0}, Ljava/lang/ref/PhantomReference;-><init>(Ljava/lang/Object;Ljava/lang/ref/ReferenceQueue;)V

    const-wide/16 v0, 0x1

    .line 108
    iput-wide v0, p0, Lcom/chaquo/python/PyObject$Ref;->count:J

    .line 113
    iget-wide v0, p1, Lcom/chaquo/python/PyObject;->addr:J

    iput-wide v0, p0, Lcom/chaquo/python/PyObject$Ref;->addr:J

    .line 114
    new-instance v0, Ljava/lang/ref/WeakReference;

    invoke-direct {v0, p1}, Ljava/lang/ref/WeakReference;-><init>(Ljava/lang/Object;)V

    iput-object v0, p0, Lcom/chaquo/python/PyObject$Ref;->weak:Ljava/lang/ref/WeakReference;

    return-void
.end method

.method public static synthetic access$000()V
    .locals 0

    .line 96
    invoke-static {}, Lcom/chaquo/python/PyObject$Ref;->releaseLoop()V

    return-void
.end method

.method private static releaseLoop()V
    .locals 10

    const/16 v0, 0x800

    .line 159
    new-array v1, v0, [J

    .line 162
    :catch_0
    :cond_0
    :goto_0
    :try_start_0
    sget-object v2, Lcom/chaquo/python/PyObject$Ref;->queue:Ljava/lang/ref/ReferenceQueue;

    invoke-virtual {v2}, Ljava/lang/ref/ReferenceQueue;->remove()Ljava/lang/ref/Reference;

    move-result-object v2

    .line 164
    invoke-static {}, Lcom/chaquo/python/PyObject;->access$100()Ljava/util/Map;

    move-result-object v3

    monitor-enter v3
    :try_end_0
    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    const/4 v4, 0x0

    .line 166
    :cond_1
    :try_start_1
    check-cast v2, Lcom/chaquo/python/PyObject$Ref;

    .line 167
    invoke-virtual {v2}, Lcom/chaquo/python/PyObject$Ref;->take()J

    move-result-wide v5

    const-wide/16 v7, 0x0

    cmp-long v7, v5, v7

    if-eqz v7, :cond_2

    add-int/lit8 v7, v4, 0x1

    .line 169
    iget-wide v8, v2, Lcom/chaquo/python/PyObject$Ref;->addr:J

    aput-wide v8, v1, v4

    add-int/lit8 v4, v4, 0x2

    .line 170
    aput-wide v5, v1, v7

    goto :goto_1

    :catchall_0
    move-exception v2

    goto :goto_2

    :cond_2
    :goto_1
    if-ge v4, v0, :cond_3

    .line 172
    sget-object v2, Lcom/chaquo/python/PyObject$Ref;->queue:Ljava/lang/ref/ReferenceQueue;

    invoke-virtual {v2}, Ljava/lang/ref/ReferenceQueue;->poll()Ljava/lang/ref/Reference;

    move-result-object v2

    if-nez v2, :cond_1

    .line 173
    :cond_3
    monitor-exit v3
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-eqz v4, :cond_0

    .line 175
    :try_start_2
    div-int/lit8 v4, v4, 0x2

    invoke-static {v1, v4}, Lcom/chaquo/python/PyObject;->access$200([JI)V
    :try_end_2
    .catch Ljava/lang/InterruptedException; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    goto :goto_0

    :catchall_1
    move-exception v2

    goto :goto_3

    .line 173
    :goto_2
    :try_start_3
    monitor-exit v3
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    :try_start_4
    throw v2
    :try_end_4
    .catch Ljava/lang/InterruptedException; {:try_start_4 .. :try_end_4} :catch_0
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    .line 180
    :goto_3
    invoke-virtual {v2}, Ljava/lang/Throwable;->printStackTrace()V

    goto :goto_0
.end method


# virtual methods
.method public add()V
    .locals 2

    .line 118
    sget-object v0, Lcom/chaquo/python/PyObject$Ref;->head:Lcom/chaquo/python/PyObject$Ref;

    iput-object v0, p0, Lcom/chaquo/python/PyObject$Ref;->next:Lcom/chaquo/python/PyObject$Ref;

    if-eqz v0, :cond_0

    .line 120
    iput-object p0, v0, Lcom/chaquo/python/PyObject$Ref;->prev:Lcom/chaquo/python/PyObject$Ref;

    .line 122
    :cond_0
    sput-object p0, Lcom/chaquo/python/PyObject$Ref;->head:Lcom/chaquo/python/PyObject$Ref;

    .line 124
    sget-object v0, Lcom/chaquo/python/PyObject$Ref;->releaser:Ljava/lang/Thread;

    if-nez v0, :cond_1

    .line 125
    new-instance v0, Ljava/lang/Thread;

    new-instance v1, Lcom/chaquo/python/PyObject$Ref$1;

    invoke-direct {v1, p0}, Lcom/chaquo/python/PyObject$Ref$1;-><init>(Lcom/chaquo/python/PyObject$Ref;)V

    const-string p0, "PyObject releaser"

    invoke-direct {v0, v1, p0}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;Ljava/lang/String;)V

    const/4 p0, 0x1

    .line 130
    invoke-virtual {v0, p0}, Ljava/lang/Thread;->setDaemon(Z)V

    .line 131
    invoke-virtual {v0}, Ljava/lang/Thread;->start()V

    .line 132
    sput-object v0, Lcom/chaquo/python/PyObject$Ref;->releaser:Ljava/lang/Thread;

    :cond_1
    return-void
.end method

.method public take()J
    .locals 5

    .line 138
    invoke-virtual {p0}, Ljava/lang/ref/Reference;->clear()V

    .line 139
    iget-wide v0, p0, Lcom/chaquo/python/PyObject$Ref;->count:J

    const-wide/16 v2, 0x0

    cmp-long v4, v0, v2

    if-eqz v4, :cond_2

    .line 141
    iput-wide v2, p0, Lcom/chaquo/python/PyObject$Ref;->count:J

    .line 142
    iget-object v2, p0, Lcom/chaquo/python/PyObject$Ref;->prev:Lcom/chaquo/python/PyObject$Ref;

    .line 145
    iget-object v3, p0, Lcom/chaquo/python/PyObject$Ref;->next:Lcom/chaquo/python/PyObject$Ref;

    if-eqz v2, :cond_0

    .line 143
    iput-object v3, v2, Lcom/chaquo/python/PyObject$Ref;->next:Lcom/chaquo/python/PyObject$Ref;

    goto :goto_0

    .line 145
    :cond_0
    sput-object v3, Lcom/chaquo/python/PyObject$Ref;->head:Lcom/chaquo/python/PyObject$Ref;

    .line 147
    :goto_0
    iget-object v3, p0, Lcom/chaquo/python/PyObject$Ref;->next:Lcom/chaquo/python/PyObject$Ref;

    if-eqz v3, :cond_1

    .line 148
    iput-object v2, v3, Lcom/chaquo/python/PyObject$Ref;->prev:Lcom/chaquo/python/PyObject$Ref;

    :cond_1
    const/4 v2, 0x0

    .line 150
    iput-object v2, p0, Lcom/chaquo/python/PyObject$Ref;->next:Lcom/chaquo/python/PyObject$Ref;

    iput-object v2, p0, Lcom/chaquo/python/PyObject$Ref;->prev:Lcom/chaquo/python/PyObject$Ref;

    .line 151
    invoke-static {}, Lcom/chaquo/python/PyObject;->access$100()Ljava/util/Map;

    move-result-object v2

    iget-wide v3, p0, Lcom/chaquo/python/PyObject$Ref;->addr:J

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v3

    invoke-interface {v2, v3}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    if-ne v2, p0, :cond_2

    .line 152
    invoke-static {}, Lcom/chaquo/python/PyObject;->access$100()Ljava/util/Map;

    move-result-object v2

    iget-wide v3, p0, Lcom/chaquo/python/PyObject$Ref;->addr:J

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p0

    invoke-interface {v2, p0}, Ljava/util/Map;->remove(Ljava/lang/Object;)Ljava/lang/Object;

    :cond_2
    return-wide v0
.end method
