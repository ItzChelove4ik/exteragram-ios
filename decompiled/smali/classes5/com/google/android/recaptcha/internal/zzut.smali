.class public final Lcom/google/android/recaptcha/internal/zzut;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zzb:Lcom/google/android/recaptcha/internal/zzut;


# instance fields
.field private final zza:Ljava/util/concurrent/ConcurrentMap;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzut;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzut;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzut;->zzb:Lcom/google/android/recaptcha/internal/zzut;

    return-void
.end method

.method public constructor <init>()V
    .locals 1

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    new-instance v0, Ljava/util/concurrent/ConcurrentHashMap;

    invoke-direct {v0}, Ljava/util/concurrent/ConcurrentHashMap;-><init>()V

    iput-object v0, p0, Lcom/google/android/recaptcha/internal/zzut;->zza:Ljava/util/concurrent/ConcurrentMap;

    return-void
.end method

.method public static zza()Lcom/google/android/recaptcha/internal/zzut;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzut;->zzb:Lcom/google/android/recaptcha/internal/zzut;

    return-object v0
.end method


# virtual methods
.method public final zzb(Lcom/google/android/recaptcha/internal/zzus;Ljava/lang/Class;)V
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzut;->zza:Ljava/util/concurrent/ConcurrentMap;

    invoke-interface {p0, p2, p1}, Ljava/util/concurrent/ConcurrentMap;->putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/google/android/recaptcha/internal/zzus;

    if-eqz p0, :cond_1

    .line 2
    invoke-virtual {p0, p1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result p0

    if-eqz p0, :cond_0

    goto :goto_0

    .line 3
    :cond_0
    const-string p0, "Different key creator for parameters class already inserted"

    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzsj$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    :cond_1
    :goto_0
    return-void
.end method
