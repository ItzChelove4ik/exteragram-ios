.class public abstract Lcom/google/android/recaptcha/internal/zzafv;
.super Lcom/google/android/recaptcha/internal/zzaec;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<MessageType:",
        "Lcom/google/android/recaptcha/internal/zzafv<",
        "TMessageType;TBuilderType;>;BuilderType:",
        "Lcom/google/android/recaptcha/internal/zzafq<",
        "TMessageType;TBuilderType;>;>",
        "Lcom/google/android/recaptcha/internal/zzaec<",
        "TMessageType;TBuilderType;>;"
    }
.end annotation


# static fields
.field private static final zzd:Ljava/util/Map;


# instance fields
.field private zza:I

.field protected zzc:Lcom/google/android/recaptcha/internal/zzaia;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 1
    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzafv;->zzd:Ljava/util/Map;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzaec;-><init>()V

    const/4 v0, -0x1

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzafv;->zza:I

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzaia;->zza()Lcom/google/android/recaptcha/internal/zzaia;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzafv;->zzc:Lcom/google/android/recaptcha/internal/zzaia;

    return-void
.end method

.method public static zzK(Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzafv;
    .locals 5

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzafv;->zzd:Ljava/util/Map;

    invoke-interface {v0, p0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzafv;

    const/4 v2, 0x0

    if-nez v1, :cond_0

    .line 2
    :try_start_0
    invoke-virtual {p0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {p0}, Ljava/lang/Class;->getClassLoader()Ljava/lang/ClassLoader;

    move-result-object v3

    const/4 v4, 0x1

    invoke-static {v1, v4, v3}, Ljava/lang/Class;->forName(Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class;
    :try_end_0
    .catch Ljava/lang/ClassNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    .line 4
    invoke-interface {v0, p0}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzafv;

    goto :goto_0

    :catch_0
    move-exception p0

    .line 8
    const-string v0, "Class initialization cannot fail."

    .line 3
    invoke-static {v0, p0}, Lorg/mvel2/asm/Constants$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-object v2

    :cond_0
    :goto_0
    if-nez v1, :cond_2

    .line 5
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaig;->zza(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzafv;

    const/4 v3, 0x6

    .line 6
    invoke-virtual {v1, v3, v2, v2}, Lcom/google/android/recaptcha/internal/zzafv;->zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/recaptcha/internal/zzafv;

    if-eqz v1, :cond_1

    .line 8
    invoke-interface {v0, p0, v1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-object v1

    .line 7
    :cond_1
    invoke-static {}, Lorg/mvel2/asm/MethodWriter$$ExternalSyntheticBUOutline0;->m()V

    return-object v2

    :cond_2
    return-object v1
.end method

.method public static zzL(Ljava/lang/Class;Lcom/google/android/recaptcha/internal/zzafv;)V
    .locals 1

    .line 1
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzafv;->zzA()V

    sget-object v0, Lcom/google/android/recaptcha/internal/zzafv;->zzd:Ljava/util/Map;

    .line 2
    invoke-interface {v0, p0, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method

.method public static zzM(Lcom/google/android/recaptcha/internal/zzagx;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzahi;

    invoke-direct {v0, p0, p1, p2}, Lcom/google/android/recaptcha/internal/zzahi;-><init>(Lcom/google/android/recaptcha/internal/zzagx;Ljava/lang/String;[Ljava/lang/Object;)V

    return-object v0
.end method

.method public static varargs zzN(Ljava/lang/reflect/Method;Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1

    const/4 v0, 0x0

    .line 1
    :try_start_0
    invoke-virtual {p0, p1, p2}, Ljava/lang/reflect/Method;->invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/IllegalAccessException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/reflect/InvocationTargetException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception p0

    .line 2
    invoke-virtual {p0}, Ljava/lang/reflect/InvocationTargetException;->getCause()Ljava/lang/Throwable;

    move-result-object p0

    .line 3
    instance-of p1, p0, Ljava/lang/RuntimeException;

    if-nez p1, :cond_1

    .line 5
    instance-of p1, p0, Ljava/lang/Error;

    if-nez p1, :cond_0

    .line 4
    const-string p1, "Unexpected exception thrown by generated accessor method."

    .line 7
    invoke-static {p1, p0}, Lorg/mvel2/util/Make$Map$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-object v0

    .line 6
    :cond_0
    check-cast p0, Ljava/lang/Error;

    throw p0

    .line 4
    :cond_1
    check-cast p0, Ljava/lang/RuntimeException;

    throw p0

    :catch_1
    move-exception p0

    .line 1
    const-string p1, "Couldn\'t use Java reflection to implement protocol message reflection."

    .line 8
    invoke-static {p1, p0}, Lorg/mvel2/util/Make$Map$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-object v0
.end method

.method public static zzO()Lcom/google/android/recaptcha/internal/zzagc;
    .locals 1

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzafw;->zzd()Lcom/google/android/recaptcha/internal/zzafw;

    move-result-object v0

    return-object v0
.end method

.method public static zzP(Lcom/google/android/recaptcha/internal/zzagc;)Lcom/google/android/recaptcha/internal/zzagc;
    .locals 1

    .line 1
    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result v0

    add-int/2addr v0, v0

    .line 2
    invoke-interface {p0, v0}, Lcom/google/android/recaptcha/internal/zzagc;->zze(I)Lcom/google/android/recaptcha/internal/zzagc;

    move-result-object p0

    return-object p0
.end method

.method public static zzQ()Lcom/google/android/recaptcha/internal/zzagd;
    .locals 1

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzagq;->zzd()Lcom/google/android/recaptcha/internal/zzagq;

    move-result-object v0

    return-object v0
.end method

.method public static zzR()Lcom/google/android/recaptcha/internal/zzage;
    .locals 1

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzahh;->zzd()Lcom/google/android/recaptcha/internal/zzahh;

    move-result-object v0

    return-object v0
.end method

.method public static zzS(Lcom/google/android/recaptcha/internal/zzage;)Lcom/google/android/recaptcha/internal/zzage;
    .locals 1

    .line 1
    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result v0

    add-int/2addr v0, v0

    .line 2
    invoke-interface {p0, v0}, Lcom/google/android/recaptcha/internal/zzage;->zzg(I)Lcom/google/android/recaptcha/internal/zzage;

    move-result-object p0

    return-object p0
.end method

.method public static zzT(Lcom/google/android/recaptcha/internal/zzafv;Lcom/google/android/recaptcha/internal/zzaer;)Lcom/google/android/recaptcha/internal/zzafv;
    .locals 3

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzafh;->zza:Lcom/google/android/recaptcha/internal/zzafh;

    .line 2
    sget v0, Lcom/google/android/recaptcha/internal/zzaef;->$r8$clinit:I

    sget-object v0, Lcom/google/android/recaptcha/internal/zzafh;->zza:Lcom/google/android/recaptcha/internal/zzafh;

    .line 3
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzl()Lcom/google/android/recaptcha/internal/zzaev;

    move-result-object p1

    .line 4
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzC()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    .line 5
    :try_start_0
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzahg;->zza()Lcom/google/android/recaptcha/internal/zzahg;

    move-result-object v1

    .line 6
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v2

    invoke-virtual {v1, v2}, Lcom/google/android/recaptcha/internal/zzahg;->zzb(Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v1

    .line 7
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzaew;->zza(Lcom/google/android/recaptcha/internal/zzaev;)Lcom/google/android/recaptcha/internal/zzaew;

    move-result-object v2

    invoke-interface {v1, p0, v2, v0}, Lcom/google/android/recaptcha/internal/zzahn;->zzg(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzaew;Lcom/google/android/recaptcha/internal/zzafh;)V

    .line 8
    invoke-interface {v1, p0}, Lcom/google/android/recaptcha/internal/zzahn;->zzk(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzagk; {:try_start_0 .. :try_end_0} :catch_3
    .catch Lcom/google/android/recaptcha/internal/zzahy; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    const/4 v0, 0x0

    .line 18
    invoke-virtual {p1, v0}, Lcom/google/android/recaptcha/internal/zzaev;->zzb(I)V

    .line 20
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzd(Lcom/google/android/recaptcha/internal/zzafv;)Lcom/google/android/recaptcha/internal/zzafv;

    .line 21
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzd(Lcom/google/android/recaptcha/internal/zzafv;)Lcom/google/android/recaptcha/internal/zzafv;

    return-object p0

    :catch_0
    move-exception p0

    .line 9
    invoke-virtual {p0}, Ljava/lang/Throwable;->getCause()Ljava/lang/Throwable;

    move-result-object p1

    instance-of p1, p1, Lcom/google/android/recaptcha/internal/zzagk;

    if-eqz p1, :cond_0

    .line 10
    invoke-virtual {p0}, Ljava/lang/Throwable;->getCause()Ljava/lang/Throwable;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzagk;

    throw p0

    .line 11
    :cond_0
    throw p0

    :catch_1
    move-exception p0

    .line 12
    invoke-virtual {p0}, Ljava/lang/Throwable;->getCause()Ljava/lang/Throwable;

    move-result-object p1

    instance-of p1, p1, Lcom/google/android/recaptcha/internal/zzagk;

    if-eqz p1, :cond_1

    .line 13
    invoke-virtual {p0}, Ljava/lang/Throwable;->getCause()Ljava/lang/Throwable;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzagk;

    throw p0

    .line 17
    :cond_1
    new-instance p1, Lcom/google/android/recaptcha/internal/zzagk;

    .line 14
    invoke-direct {p1, p0}, Lcom/google/android/recaptcha/internal/zzagk;-><init>(Ljava/io/IOException;)V

    throw p1

    :catch_2
    move-exception p0

    .line 15
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzahy;->zza()Lcom/google/android/recaptcha/internal/zzagk;

    move-result-object p0

    throw p0

    :catch_3
    move-exception p0

    .line 21
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzagk;->zzb()Z

    move-result p1

    if-eqz p1, :cond_2

    new-instance p1, Lcom/google/android/recaptcha/internal/zzagk;

    .line 16
    invoke-direct {p1, p0}, Lcom/google/android/recaptcha/internal/zzagk;-><init>(Ljava/io/IOException;)V

    .line 17
    throw p1

    :cond_2
    throw p0
.end method

.method public static zzU(Lcom/google/android/recaptcha/internal/zzafv;Lcom/google/android/recaptcha/internal/zzaer;Lcom/google/android/recaptcha/internal/zzafh;)Lcom/google/android/recaptcha/internal/zzafv;
    .locals 2

    .line 1
    invoke-virtual {p1}, Lcom/google/android/recaptcha/internal/zzaer;->zzl()Lcom/google/android/recaptcha/internal/zzaev;

    move-result-object p1

    .line 2
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzC()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    .line 3
    :try_start_0
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzahg;->zza()Lcom/google/android/recaptcha/internal/zzahg;

    move-result-object v0

    .line 4
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzahg;->zzb(Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v0

    .line 5
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzaew;->zza(Lcom/google/android/recaptcha/internal/zzaev;)Lcom/google/android/recaptcha/internal/zzaew;

    move-result-object v1

    invoke-interface {v0, p0, v1, p2}, Lcom/google/android/recaptcha/internal/zzahn;->zzg(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzaew;Lcom/google/android/recaptcha/internal/zzafh;)V

    .line 6
    invoke-interface {v0, p0}, Lcom/google/android/recaptcha/internal/zzahn;->zzk(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzagk; {:try_start_0 .. :try_end_0} :catch_3
    .catch Lcom/google/android/recaptcha/internal/zzahy; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    const/4 p2, 0x0

    .line 16
    invoke-virtual {p1, p2}, Lcom/google/android/recaptcha/internal/zzaev;->zzb(I)V

    .line 18
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzd(Lcom/google/android/recaptcha/internal/zzafv;)Lcom/google/android/recaptcha/internal/zzafv;

    return-object p0

    :catch_0
    move-exception p0

    .line 7
    invoke-virtual {p0}, Ljava/lang/Throwable;->getCause()Ljava/lang/Throwable;

    move-result-object p1

    instance-of p1, p1, Lcom/google/android/recaptcha/internal/zzagk;

    if-eqz p1, :cond_0

    .line 8
    invoke-virtual {p0}, Ljava/lang/Throwable;->getCause()Ljava/lang/Throwable;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzagk;

    throw p0

    .line 9
    :cond_0
    throw p0

    :catch_1
    move-exception p0

    .line 10
    invoke-virtual {p0}, Ljava/lang/Throwable;->getCause()Ljava/lang/Throwable;

    move-result-object p1

    instance-of p1, p1, Lcom/google/android/recaptcha/internal/zzagk;

    if-eqz p1, :cond_1

    .line 11
    invoke-virtual {p0}, Ljava/lang/Throwable;->getCause()Ljava/lang/Throwable;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzagk;

    throw p0

    .line 15
    :cond_1
    new-instance p1, Lcom/google/android/recaptcha/internal/zzagk;

    .line 12
    invoke-direct {p1, p0}, Lcom/google/android/recaptcha/internal/zzagk;-><init>(Ljava/io/IOException;)V

    throw p1

    :catch_2
    move-exception p0

    .line 13
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzahy;->zza()Lcom/google/android/recaptcha/internal/zzagk;

    move-result-object p0

    throw p0

    :catch_3
    move-exception p0

    .line 18
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzagk;->zzb()Z

    move-result p1

    if-eqz p1, :cond_2

    new-instance p1, Lcom/google/android/recaptcha/internal/zzagk;

    .line 14
    invoke-direct {p1, p0}, Lcom/google/android/recaptcha/internal/zzagk;-><init>(Ljava/io/IOException;)V

    .line 15
    throw p1

    :cond_2
    throw p0
.end method

.method public static zzV(Lcom/google/android/recaptcha/internal/zzafv;[B)Lcom/google/android/recaptcha/internal/zzafv;
    .locals 3

    .line 1
    array-length v0, p1

    .line 2
    sget-object v1, Lcom/google/android/recaptcha/internal/zzafh;->zza:Lcom/google/android/recaptcha/internal/zzafh;

    .line 3
    sget v1, Lcom/google/android/recaptcha/internal/zzaef;->$r8$clinit:I

    sget-object v1, Lcom/google/android/recaptcha/internal/zzafh;->zza:Lcom/google/android/recaptcha/internal/zzafh;

    const/4 v2, 0x0

    .line 4
    invoke-static {p0, p1, v2, v0, v1}, Lcom/google/android/recaptcha/internal/zzafv;->zzc(Lcom/google/android/recaptcha/internal/zzafv;[BIILcom/google/android/recaptcha/internal/zzafh;)Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    .line 1
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzd(Lcom/google/android/recaptcha/internal/zzafv;)Lcom/google/android/recaptcha/internal/zzafv;

    return-object p0
.end method

.method public static zzW(Lcom/google/android/recaptcha/internal/zzafv;[BLcom/google/android/recaptcha/internal/zzafh;)Lcom/google/android/recaptcha/internal/zzafv;
    .locals 2

    const/4 v0, 0x0

    .line 1
    array-length v1, p1

    .line 2
    invoke-static {p0, p1, v0, v1, p2}, Lcom/google/android/recaptcha/internal/zzafv;->zzc(Lcom/google/android/recaptcha/internal/zzafv;[BIILcom/google/android/recaptcha/internal/zzafh;)Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    .line 1
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzd(Lcom/google/android/recaptcha/internal/zzafv;)Lcom/google/android/recaptcha/internal/zzafv;

    return-object p0
.end method

.method public static synthetic zzY(Lcom/google/android/recaptcha/internal/zzafv;Z)Z
    .locals 0

    const/4 p1, 0x0

    invoke-static {p0, p1}, Lcom/google/android/recaptcha/internal/zzafv;->zzb(Lcom/google/android/recaptcha/internal/zzafv;Z)Z

    move-result p0

    return p0
.end method

.method public static synthetic zzZ(Lcom/google/android/recaptcha/internal/zzafv;[BIILcom/google/android/recaptcha/internal/zzafh;)Lcom/google/android/recaptcha/internal/zzafv;
    .locals 0

    const/4 p2, 0x0

    invoke-static {p0, p1, p2, p3, p4}, Lcom/google/android/recaptcha/internal/zzafv;->zzc(Lcom/google/android/recaptcha/internal/zzafv;[BIILcom/google/android/recaptcha/internal/zzafh;)Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object p0

    return-object p0
.end method

.method private final zza(Lcom/google/android/recaptcha/internal/zzahn;)I
    .locals 1

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzahg;->zza()Lcom/google/android/recaptcha/internal/zzahg;

    move-result-object p1

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    .line 2
    invoke-virtual {p1, v0}, Lcom/google/android/recaptcha/internal/zzahg;->zzb(Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object p1

    .line 1
    invoke-interface {p1, p0}, Lcom/google/android/recaptcha/internal/zzahn;->zze(Ljava/lang/Object;)I

    move-result p0

    return p0
.end method

.method private static final zzb(Lcom/google/android/recaptcha/internal/zzafv;Z)Z
    .locals 4

    const/4 v0, 0x1

    const/4 v1, 0x0

    .line 1
    invoke-virtual {p0, v0, v1, v1}, Lcom/google/android/recaptcha/internal/zzafv;->zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/Byte;

    invoke-virtual {v2}, Ljava/lang/Byte;->byteValue()B

    move-result v2

    if-ne v2, v0, :cond_0

    return v0

    :cond_0
    if-nez v2, :cond_1

    const/4 p0, 0x0

    return p0

    .line 2
    :cond_1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzahg;->zza()Lcom/google/android/recaptcha/internal/zzahg;

    move-result-object v2

    .line 3
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v3

    invoke-virtual {v2, v3}, Lcom/google/android/recaptcha/internal/zzahg;->zzb(Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v2

    .line 2
    invoke-interface {v2, p0}, Lcom/google/android/recaptcha/internal/zzahn;->zzl(Ljava/lang/Object;)Z

    move-result v2

    if-eqz p1, :cond_3

    if-eq v0, v2, :cond_2

    move-object p1, v1

    goto :goto_0

    :cond_2
    move-object p1, p0

    :goto_0
    const/4 v0, 0x2

    .line 4
    invoke-virtual {p0, v0, p1, v1}, Lcom/google/android/recaptcha/internal/zzafv;->zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    :cond_3
    return v2
.end method

.method private static zzc(Lcom/google/android/recaptcha/internal/zzafv;[BIILcom/google/android/recaptcha/internal/zzafh;)Lcom/google/android/recaptcha/internal/zzafv;
    .locals 6

    if-nez p3, :cond_0

    return-object p0

    .line 1
    :cond_0
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzC()Lcom/google/android/recaptcha/internal/zzafv;

    move-result-object v1

    .line 2
    :try_start_0
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzahg;->zza()Lcom/google/android/recaptcha/internal/zzahg;

    move-result-object p0

    .line 3
    invoke-virtual {v1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p2

    invoke-virtual {p0, p2}, Lcom/google/android/recaptcha/internal/zzahg;->zzb(Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v0

    new-instance v5, Lcom/google/android/recaptcha/internal/zzaeg;

    .line 4
    invoke-direct {v5, p4}, Lcom/google/android/recaptcha/internal/zzaeg;-><init>(Lcom/google/android/recaptcha/internal/zzafh;)V

    const/4 v3, 0x0

    move-object v2, p1

    move v4, p3

    invoke-interface/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzahn;->zzj(Ljava/lang/Object;[BIILcom/google/android/recaptcha/internal/zzaeg;)V

    .line 5
    invoke-interface {v0, v1}, Lcom/google/android/recaptcha/internal/zzahn;->zzk(Ljava/lang/Object;)V
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzagk; {:try_start_0 .. :try_end_0} :catch_3
    .catch Lcom/google/android/recaptcha/internal/zzahy; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/IndexOutOfBoundsException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v1

    .line 9
    :catch_0
    const-string p0, "While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length."

    .line 7
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0

    :catch_1
    move-exception v0

    move-object p0, v0

    .line 8
    invoke-virtual {p0}, Ljava/lang/Throwable;->getCause()Ljava/lang/Throwable;

    move-result-object p1

    instance-of p1, p1, Lcom/google/android/recaptcha/internal/zzagk;

    if-eqz p1, :cond_1

    .line 9
    invoke-virtual {p0}, Ljava/lang/Throwable;->getCause()Ljava/lang/Throwable;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzagk;

    throw p0

    .line 11
    :cond_1
    new-instance p1, Lcom/google/android/recaptcha/internal/zzagk;

    .line 10
    invoke-direct {p1, p0}, Lcom/google/android/recaptcha/internal/zzagk;-><init>(Ljava/io/IOException;)V

    throw p1

    :catch_2
    move-exception v0

    move-object p0, v0

    .line 11
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzahy;->zza()Lcom/google/android/recaptcha/internal/zzagk;

    move-result-object p0

    throw p0

    :catch_3
    move-exception v0

    move-object p0, v0

    .line 5
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzagk;->zzb()Z

    move-result p1

    if-eqz p1, :cond_2

    new-instance p1, Lcom/google/android/recaptcha/internal/zzagk;

    .line 12
    invoke-direct {p1, p0}, Lcom/google/android/recaptcha/internal/zzagk;-><init>(Ljava/io/IOException;)V

    .line 13
    throw p1

    :cond_2
    throw p0
.end method

.method private static zzd(Lcom/google/android/recaptcha/internal/zzafv;)Lcom/google/android/recaptcha/internal/zzafv;
    .locals 1

    if-eqz p0, :cond_1

    const/4 v0, 0x1

    .line 1
    invoke-static {p0, v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzb(Lcom/google/android/recaptcha/internal/zzafv;Z)Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_0

    :cond_0
    new-instance v0, Lcom/google/android/recaptcha/internal/zzahy;

    .line 2
    invoke-direct {v0, p0}, Lcom/google/android/recaptcha/internal/zzahy;-><init>(Lcom/google/android/recaptcha/internal/zzagx;)V

    .line 3
    invoke-virtual {v0}, Lcom/google/android/recaptcha/internal/zzahy;->zza()Lcom/google/android/recaptcha/internal/zzagk;

    move-result-object p0

    .line 4
    throw p0

    :cond_1
    :goto_0
    return-object p0
.end method


# virtual methods
.method public final equals(Ljava/lang/Object;)Z
    .locals 3

    if-ne p0, p1, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 v0, 0x0

    if-nez p1, :cond_1

    return v0

    .line 1
    :cond_1
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v2

    if-eq v1, v2, :cond_2

    return v0

    :cond_2
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzahg;->zza()Lcom/google/android/recaptcha/internal/zzahg;

    move-result-object v1

    .line 2
    invoke-virtual {v1, v0}, Lcom/google/android/recaptcha/internal/zzahg;->zzb(Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v0

    .line 1
    check-cast p1, Lcom/google/android/recaptcha/internal/zzafv;

    invoke-interface {v0, p0, p1}, Lcom/google/android/recaptcha/internal/zzahn;->zzb(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method

.method public final hashCode()I
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzz()Z

    move-result v0

    if-nez v0, :cond_1

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaec;->zzb:I

    if-nez v0, :cond_0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzD()I

    move-result v0

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzaec;->zzb:I

    :cond_0
    return v0

    .line 2
    :cond_1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzD()I

    move-result p0

    return p0
.end method

.method public final toString()Ljava/lang/String;
    .locals 1

    .line 1
    invoke-super {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {p0, v0}, Lcom/google/android/recaptcha/internal/zzagz;->zza(Lcom/google/android/recaptcha/internal/zzagx;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public final zzA()V
    .locals 2

    iget v0, p0, Lcom/google/android/recaptcha/internal/zzafv;->zza:I

    const v1, 0x7fffffff

    and-int/2addr v0, v1

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzafv;->zza:I

    return-void
.end method

.method public final zzB()Lcom/google/android/recaptcha/internal/zzahe;
    .locals 2

    const/4 v0, 0x7

    const/4 v1, 0x0

    .line 1
    invoke-virtual {p0, v0, v1, v1}, Lcom/google/android/recaptcha/internal/zzafv;->zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzahe;

    return-object p0
.end method

.method public final zzC()Lcom/google/android/recaptcha/internal/zzafv;
    .locals 2

    const/4 v0, 0x4

    const/4 v1, 0x0

    .line 1
    invoke-virtual {p0, v0, v1, v1}, Lcom/google/android/recaptcha/internal/zzafv;->zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzafv;

    return-object p0
.end method

.method public final zzD()I
    .locals 2

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzahg;->zza()Lcom/google/android/recaptcha/internal/zzahg;

    move-result-object v0

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    .line 2
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzahg;->zzb(Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v0

    .line 1
    invoke-interface {v0, p0}, Lcom/google/android/recaptcha/internal/zzahn;->zzc(Ljava/lang/Object;)I

    move-result p0

    return p0
.end method

.method public final zzE()V
    .locals 2

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzahg;->zza()Lcom/google/android/recaptcha/internal/zzahg;

    move-result-object v0

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    .line 2
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzahg;->zzb(Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v0

    .line 1
    invoke-interface {v0, p0}, Lcom/google/android/recaptcha/internal/zzahn;->zzk(Ljava/lang/Object;)V

    .line 3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzA()V

    return-void
.end method

.method public final zzF()Lcom/google/android/recaptcha/internal/zzafq;
    .locals 2

    const/4 v0, 0x5

    const/4 v1, 0x0

    .line 1
    invoke-virtual {p0, v0, v1, v1}, Lcom/google/android/recaptcha/internal/zzafv;->zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzafq;

    return-object p0
.end method

.method public final zzG()Lcom/google/android/recaptcha/internal/zzafq;
    .locals 2

    const/4 v0, 0x5

    const/4 v1, 0x0

    .line 1
    invoke-virtual {p0, v0, v1, v1}, Lcom/google/android/recaptcha/internal/zzafv;->zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lcom/google/android/recaptcha/internal/zzafq;

    .line 2
    invoke-virtual {v0, p0}, Lcom/google/android/recaptcha/internal/zzafq;->zzq(Lcom/google/android/recaptcha/internal/zzafv;)Lcom/google/android/recaptcha/internal/zzafq;

    return-object v0
.end method

.method public final zzH(I)V
    .locals 1

    iget p1, p0, Lcom/google/android/recaptcha/internal/zzafv;->zza:I

    const/high16 v0, -0x80000000

    and-int/2addr p1, v0

    const v0, 0x7fffffff

    or-int/2addr p1, v0

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzafv;->zza:I

    return-void
.end method

.method public final zzI(Lcom/google/android/recaptcha/internal/zzafa;)V
    .locals 2

    .line 1
    invoke-static {}, Lcom/google/android/recaptcha/internal/zzahg;->zza()Lcom/google/android/recaptcha/internal/zzahg;

    move-result-object v0

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v1

    .line 2
    invoke-virtual {v0, v1}, Lcom/google/android/recaptcha/internal/zzahg;->zzb(Ljava/lang/Class;)Lcom/google/android/recaptcha/internal/zzahn;

    move-result-object v0

    .line 3
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzafb;->zza(Lcom/google/android/recaptcha/internal/zzafa;)Lcom/google/android/recaptcha/internal/zzafb;

    move-result-object p1

    invoke-interface {v0, p0, p1}, Lcom/google/android/recaptcha/internal/zzahn;->zzf(Ljava/lang/Object;Lcom/google/android/recaptcha/internal/zzafb;)V

    return-void
.end method

.method public final zzJ()I
    .locals 3

    .line 3
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzz()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_1

    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzafv;->zza(Lcom/google/android/recaptcha/internal/zzahn;)I

    move-result p0

    if-ltz p0, :cond_0

    return p0

    .line 4
    :cond_0
    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v0

    add-int/lit8 v0, v0, 0x2a

    invoke-static {v0, p0}, Lcom/google/android/gms/internal/cast/zzyd$$ExternalSyntheticBUOutline0;->m(II)V

    :goto_0
    const/4 p0, 0x0

    return p0

    :cond_1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzafv;->zza:I

    const v2, 0x7fffffff

    and-int/2addr v0, v2

    if-eq v0, v2, :cond_2

    return v0

    .line 1
    :cond_2
    invoke-direct {p0, v1}, Lcom/google/android/recaptcha/internal/zzafv;->zza(Lcom/google/android/recaptcha/internal/zzahn;)I

    move-result v0

    if-ltz v0, :cond_3

    .line 2
    iget v1, p0, Lcom/google/android/recaptcha/internal/zzafv;->zza:I

    const/high16 v2, -0x80000000

    and-int/2addr v1, v2

    or-int/2addr v1, v0

    iput v1, p0, Lcom/google/android/recaptcha/internal/zzafv;->zza:I

    return v0

    :cond_3
    invoke-static {v0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p0

    add-int/lit8 p0, p0, 0x2a

    invoke-static {p0, v0}, Lcom/google/android/gms/internal/cast/zzyd$$ExternalSyntheticBUOutline0;->m(II)V

    goto :goto_0
.end method

.method public final synthetic zzX()Lcom/google/android/recaptcha/internal/zzagw;
    .locals 2

    const/4 v0, 0x5

    const/4 v1, 0x0

    .line 1
    invoke-virtual {p0, v0, v1, v1}, Lcom/google/android/recaptcha/internal/zzafv;->zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzafq;

    return-object p0
.end method

.method public final zzab()Z
    .locals 1

    const/4 v0, 0x1

    .line 1
    invoke-static {p0, v0}, Lcom/google/android/recaptcha/internal/zzafv;->zzb(Lcom/google/android/recaptcha/internal/zzafv;Z)Z

    move-result p0

    return p0
.end method

.method public final synthetic zzac()Lcom/google/android/recaptcha/internal/zzagx;
    .locals 2

    const/4 v0, 0x6

    const/4 v1, 0x0

    .line 1
    invoke-virtual {p0, v0, v1, v1}, Lcom/google/android/recaptcha/internal/zzafv;->zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzafv;

    return-object p0
.end method

.method public abstract zzf(ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
.end method

.method public final zzx(Lcom/google/android/recaptcha/internal/zzahn;)I
    .locals 2

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzafv;->zzz()Z

    move-result v0

    if-eqz v0, :cond_1

    invoke-interface {p1, p0}, Lcom/google/android/recaptcha/internal/zzahn;->zze(Ljava/lang/Object;)I

    move-result p0

    if-ltz p0, :cond_0

    return p0

    .line 2
    :cond_0
    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result p1

    add-int/lit8 p1, p1, 0x2a

    invoke-static {p1, p0}, Lcom/google/android/gms/internal/cast/zzyd$$ExternalSyntheticBUOutline0;->m(II)V

    :goto_0
    const/4 p0, 0x0

    return p0

    :cond_1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzafv;->zza:I

    const v1, 0x7fffffff

    and-int/2addr v0, v1

    if-ne v0, v1, :cond_3

    .line 3
    invoke-interface {p1, p0}, Lcom/google/android/recaptcha/internal/zzahn;->zze(Ljava/lang/Object;)I

    move-result p1

    if-ltz p1, :cond_2

    .line 4
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzafv;->zza:I

    const/high16 v1, -0x80000000

    and-int/2addr v0, v1

    or-int/2addr v0, p1

    iput v0, p0, Lcom/google/android/recaptcha/internal/zzafv;->zza:I

    return p1

    :cond_2
    invoke-static {p1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p0

    add-int/lit8 p0, p0, 0x2a

    invoke-static {p0, p1}, Lcom/google/android/gms/internal/cast/zzyd$$ExternalSyntheticBUOutline0;->m(II)V

    goto :goto_0

    :cond_3
    return v0
.end method

.method public final zzz()Z
    .locals 1

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzafv;->zza:I

    const/high16 v0, -0x80000000

    and-int/2addr p0, v0

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method
