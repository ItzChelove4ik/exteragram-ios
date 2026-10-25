.class public final Lcom/google/android/recaptcha/internal/zzdv;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzmu;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzhh;

.field private final zzc:Lcom/google/android/recaptcha/internal/zzhu;


# direct methods
.method public constructor <init>(Lcom/google/android/recaptcha/internal/zzmu;Lcom/google/android/recaptcha/internal/zzhh;Lcom/google/android/recaptcha/internal/zzhu;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzdv;->zza:Lcom/google/android/recaptcha/internal/zzmu;

    iput-object p2, p0, Lcom/google/android/recaptcha/internal/zzdv;->zzb:Lcom/google/android/recaptcha/internal/zzhh;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzdv;->zzc:Lcom/google/android/recaptcha/internal/zzhu;

    return-void
.end method

.method public static final synthetic zza(Lcom/google/android/recaptcha/internal/zzdv;Ljava/lang/Exception;)Lcom/google/android/recaptcha/internal/zzhg;
    .locals 7

    .line 1
    instance-of v0, p1, Lkotlinx/coroutines/TimeoutCancellationException;

    if-eqz v0, :cond_0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzdv;->zzb:Lcom/google/android/recaptcha/internal/zzhh;

    .line 2
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzc:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzb:Lcom/google/android/recaptcha/internal/zzhe;

    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x8

    const/4 v6, 0x0

    const/4 v4, 0x0

    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 3
    invoke-virtual {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzhh;->zza(Ljava/lang/Exception;Lcom/google/android/recaptcha/internal/zzhg;)Lcom/google/android/recaptcha/internal/zzhg;

    move-result-object p0

    return-object p0

    :cond_0
    instance-of v0, p1, Lcom/google/android/recaptcha/internal/zzhg;

    if-eqz v0, :cond_1

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzdv;->zzb:Lcom/google/android/recaptcha/internal/zzhh;

    .line 4
    move-object v0, p1

    check-cast v0, Lcom/google/android/recaptcha/internal/zzhg;

    invoke-virtual {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzhh;->zza(Ljava/lang/Exception;Lcom/google/android/recaptcha/internal/zzhg;)Lcom/google/android/recaptcha/internal/zzhg;

    move-result-object p0

    return-object p0

    :cond_1
    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzdv;->zzb:Lcom/google/android/recaptcha/internal/zzhh;

    .line 5
    new-instance v0, Lcom/google/android/recaptcha/internal/zzhg;

    sget-object v1, Lcom/google/android/recaptcha/internal/zzhf;->zzc:Lcom/google/android/recaptcha/internal/zzhf;

    sget-object v2, Lcom/google/android/recaptcha/internal/zzhe;->zzk:Lcom/google/android/recaptcha/internal/zzhe;

    .line 6
    invoke-virtual {p1}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v3

    const/16 v5, 0x8

    const/4 v6, 0x0

    const/4 v4, 0x0

    .line 5
    invoke-direct/range {v0 .. v6}, Lcom/google/android/recaptcha/internal/zzhg;-><init>(Lcom/google/android/recaptcha/internal/zzhf;Lcom/google/android/recaptcha/internal/zzhe;Ljava/lang/String;Ljava/lang/Exception;ILkotlin/jvm/internal/DefaultConstructorMarker;)V

    .line 7
    invoke-virtual {p0, p1, v0}, Lcom/google/android/recaptcha/internal/zzhh;->zza(Ljava/lang/Exception;Lcom/google/android/recaptcha/internal/zzhg;)Lcom/google/android/recaptcha/internal/zzhg;

    move-result-object p0

    return-object p0
.end method

.method public static final synthetic zzb(Lcom/google/android/recaptcha/internal/zzdv;)Lcom/google/android/recaptcha/internal/zzmu;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzdv;->zza:Lcom/google/android/recaptcha/internal/zzmu;

    return-object p0
.end method

.method public static final synthetic zzc(Lcom/google/android/recaptcha/internal/zzdv;)Lcom/google/android/recaptcha/internal/zzhu;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzdv;->zzc:Lcom/google/android/recaptcha/internal/zzhu;

    return-object p0
.end method
