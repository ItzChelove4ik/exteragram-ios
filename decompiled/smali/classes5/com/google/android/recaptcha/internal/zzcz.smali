.class public final Lcom/google/android/recaptcha/internal/zzcz;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field final synthetic zza:Lcom/google/android/recaptcha/internal/zzdo;

.field private final zzb:Ljava/lang/String;

.field private final zzc:Lkotlinx/coroutines/Deferred;

.field private final zzd:Lcom/google/android/recaptcha/internal/zzaka;

.field private zze:Lcom/google/android/recaptcha/internal/zzaka;

.field private zzf:Z


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzdo;Ljava/lang/String;Lkotlinx/coroutines/Deferred;Lcom/google/android/recaptcha/internal/zzaka;)V
    .locals 0

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzcz;->zza:Lcom/google/android/recaptcha/internal/zzdo;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzcz;->zzb:Ljava/lang/String;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzcz;->zzc:Lkotlinx/coroutines/Deferred;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzcz;->zzd:Lcom/google/android/recaptcha/internal/zzaka;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzcz;->zze:Lcom/google/android/recaptcha/internal/zzaka;

    const/4 p1, 0x1

    iput-boolean p1, p0, Lcom/google/android/recaptcha/internal/zzcz;->zzf:Z

    return-void
.end method

.method public static final synthetic zzb(Lcom/google/android/recaptcha/internal/zzcz;)Z
    .locals 0

    iget-boolean p0, p0, Lcom/google/android/recaptcha/internal/zzcz;->zzf:Z

    return p0
.end method

.method public static final synthetic zzc(Lcom/google/android/recaptcha/internal/zzcz;)Lkotlinx/coroutines/Deferred;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzcz;->zzc:Lkotlinx/coroutines/Deferred;

    return-object p0
.end method

.method public static final synthetic zzd(Lcom/google/android/recaptcha/internal/zzcz;Z)V
    .locals 0

    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/google/android/recaptcha/internal/zzcz;->zzf:Z

    return-void
.end method

.method public static final synthetic zze(Lcom/google/android/recaptcha/internal/zzcz;Lcom/google/android/recaptcha/internal/zzaka;)V
    .locals 0

    .line 1
    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzcz;->zze:Lcom/google/android/recaptcha/internal/zzaka;

    return-void
.end method

.method public static final synthetic zzf(Lcom/google/android/recaptcha/internal/zzcz;)Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzcz;->zzb:Ljava/lang/String;

    return-object p0
.end method

.method public static final synthetic zzg(Lcom/google/android/recaptcha/internal/zzcz;)Lcom/google/android/recaptcha/internal/zzaka;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzcz;->zze:Lcom/google/android/recaptcha/internal/zzaka;

    return-object p0
.end method


# virtual methods
.method public final zza(Ljava/lang/String;)Z
    .locals 0

    .line 1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzcz;->zze:Lcom/google/android/recaptcha/internal/zzaka;

    if-eqz p0, :cond_0

    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzaka;->zza()Ljava/lang/String;

    move-result-object p0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    :goto_0
    invoke-static {p0, p1}, Lkotlin/jvm/internal/Intrinsics;->areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p0

    return p0
.end method
