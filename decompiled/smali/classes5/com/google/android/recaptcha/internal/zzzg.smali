.class final synthetic Lcom/google/android/recaptcha/internal/zzzg;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzwa;


# static fields
.field static final synthetic zza:Lcom/google/android/recaptcha/internal/zzzg;


# direct methods
.method public static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzzg;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzzg;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzzg;->zza:Lcom/google/android/recaptcha/internal/zzzg;

    return-void
.end method

.method private synthetic constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public final synthetic zza()Ljava/lang/Object;
    .locals 1

    sget-object p0, Lcom/google/android/recaptcha/internal/zzzn;->zza:Lcom/google/android/recaptcha/internal/zzxx;

    .line 1
    new-instance p0, Lcom/google/android/recaptcha/internal/zzxs;

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzxs;-><init>([B)V

    sget-object v0, Lcom/google/android/recaptcha/internal/zzxv;->zza:Lcom/google/android/recaptcha/internal/zzxv;

    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzxs;->zza(Lcom/google/android/recaptcha/internal/zzxv;)Lcom/google/android/recaptcha/internal/zzxs;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzxt;->zzb:Lcom/google/android/recaptcha/internal/zzxt;

    .line 2
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzxs;->zzb(Lcom/google/android/recaptcha/internal/zzxt;)Lcom/google/android/recaptcha/internal/zzxs;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzxu;->zzc:Lcom/google/android/recaptcha/internal/zzxu;

    .line 3
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzxs;->zzc(Lcom/google/android/recaptcha/internal/zzxu;)Lcom/google/android/recaptcha/internal/zzxs;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzxw;->zza:Lcom/google/android/recaptcha/internal/zzxw;

    .line 4
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzxs;->zzd(Lcom/google/android/recaptcha/internal/zzxw;)Lcom/google/android/recaptcha/internal/zzxs;

    .line 5
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzxs;->zze()Lcom/google/android/recaptcha/internal/zzxx;

    move-result-object p0

    return-object p0
.end method
