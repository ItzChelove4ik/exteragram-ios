.class public final Lcom/google/android/recaptcha/internal/zzur;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zzb:Lcom/google/android/recaptcha/internal/zztx;

.field private static final zzc:Lcom/google/android/recaptcha/internal/zzur;


# instance fields
.field private final zza:Ljava/util/Map;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 1
    sget-object v0, Lcom/google/android/recaptcha/internal/zzuq;->zza:Lcom/google/android/recaptcha/internal/zzuq;

    sput-object v0, Lcom/google/android/recaptcha/internal/zzur;->zzb:Lcom/google/android/recaptcha/internal/zztx;

    invoke-static {}, Lcom/google/android/recaptcha/internal/zzur;->zzc()Lcom/google/android/recaptcha/internal/zzur;

    move-result-object v0

    sput-object v0, Lcom/google/android/recaptcha/internal/zzur;->zzc:Lcom/google/android/recaptcha/internal/zzur;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzur;->zza:Ljava/util/Map;

    return-void
.end method

.method public static zza()Lcom/google/android/recaptcha/internal/zzur;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzur;->zzc:Lcom/google/android/recaptcha/internal/zzur;

    return-object v0
.end method

.method private static zzc()Lcom/google/android/recaptcha/internal/zzur;
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzur;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzur;-><init>()V

    :try_start_0
    sget-object v1, Lcom/google/android/recaptcha/internal/zzur;->zzb:Lcom/google/android/recaptcha/internal/zztx;

    const-class v2, Lcom/google/android/recaptcha/internal/zzuj;

    .line 2
    invoke-virtual {v0, v1, v2}, Lcom/google/android/recaptcha/internal/zzur;->zzb(Lcom/google/android/recaptcha/internal/zztx;Ljava/lang/Class;)V
    :try_end_0
    .catch Ljava/security/GeneralSecurityException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    move-exception v0

    const-string/jumbo v1, "unexpected error."

    .line 3
    invoke-static {v1, v0}, Lorg/mvel2/asm/Constants$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Throwable;)V

    const/4 v0, 0x0

    return-object v0
.end method


# virtual methods
.method public final declared-synchronized zzb(Lcom/google/android/recaptcha/internal/zztx;Ljava/lang/Class;)V
    .locals 2

    monitor-enter p0

    .line 1
    :try_start_0
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzur;->zza:Ljava/util/Map;

    invoke-interface {v0, p2}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lcom/google/android/recaptcha/internal/zztx;

    if-eqz v1, :cond_1

    .line 2
    invoke-virtual {v1, p1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_0

    .line 3
    :cond_0
    new-instance p1, Ljava/security/GeneralSecurityException;

    invoke-virtual {p2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result v0

    new-instance v1, Ljava/lang/StringBuilder;

    add-int/lit8 v0, v0, 0x3c

    invoke-direct {v1, v0}, Ljava/lang/StringBuilder;-><init>(I)V

    const-string v0, "Different key creator for parameters class "

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p2, " already inserted"

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    invoke-direct {p1, p2}, Ljava/security/GeneralSecurityException;-><init>(Ljava/lang/String;)V

    throw p1

    :catchall_0
    move-exception p1

    goto :goto_1

    .line 4
    :cond_1
    :goto_0
    invoke-interface {v0, p2, p1}, Ljava/util/Map;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-void

    :goto_1
    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method
