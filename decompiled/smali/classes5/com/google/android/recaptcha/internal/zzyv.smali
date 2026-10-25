.class final synthetic Lcom/google/android/recaptcha/internal/zzyv;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/recaptcha/internal/zzwa;


# static fields
.field static final synthetic zza:Lcom/google/android/recaptcha/internal/zzyv;


# direct methods
.method public static synthetic constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/recaptcha/internal/zzyv;

    invoke-direct {v0}, Lcom/google/android/recaptcha/internal/zzyv;-><init>()V

    sput-object v0, Lcom/google/android/recaptcha/internal/zzyv;->zza:Lcom/google/android/recaptcha/internal/zzyv;

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
    sget-object p0, Lcom/google/android/recaptcha/internal/zzzv;->zza:Ljava/math/BigInteger;

    .line 2
    new-instance p0, Lcom/google/android/recaptcha/internal/zzzs;

    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lcom/google/android/recaptcha/internal/zzzs;-><init>([B)V

    sget-object v0, Lcom/google/android/recaptcha/internal/zzzt;->zza:Lcom/google/android/recaptcha/internal/zzzt;

    .line 3
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzzs;->zzd(Lcom/google/android/recaptcha/internal/zzzt;)Lcom/google/android/recaptcha/internal/zzzs;

    const/16 v0, 0xc00

    .line 4
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzzs;->zza(I)Lcom/google/android/recaptcha/internal/zzzs;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzzv;->zza:Ljava/math/BigInteger;

    .line 5
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzzs;->zzb(Ljava/math/BigInteger;)Lcom/google/android/recaptcha/internal/zzzs;

    sget-object v0, Lcom/google/android/recaptcha/internal/zzzu;->zzd:Lcom/google/android/recaptcha/internal/zzzu;

    .line 6
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzzs;->zzc(Lcom/google/android/recaptcha/internal/zzzu;)Lcom/google/android/recaptcha/internal/zzzs;

    .line 7
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzzs;->zze()Lcom/google/android/recaptcha/internal/zzzv;

    move-result-object p0

    return-object p0
.end method
