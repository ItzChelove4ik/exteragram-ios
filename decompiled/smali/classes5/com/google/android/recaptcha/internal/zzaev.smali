.class public abstract Lcom/google/android/recaptcha/internal/zzaev;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field public static final synthetic $r8$clinit:I = 0x0

.field private static volatile zzf:I = 0x64


# instance fields
.field zza:I

.field zzb:I

.field final zzc:I

.field zzd:Ljava/lang/Object;


# direct methods
.method static constructor <clinit>()V
    .locals 0

    return-void
.end method

.method public synthetic constructor <init>([B)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    sget p1, Lcom/google/android/recaptcha/internal/zzaev;->zzf:I

    iput p1, p0, Lcom/google/android/recaptcha/internal/zzaev;->zzc:I

    return-void
.end method

.method public static zzE([BIIZ)Lcom/google/android/recaptcha/internal/zzaev;
    .locals 6

    .line 1
    new-instance v0, Lcom/google/android/recaptcha/internal/zzaet;

    const/4 v5, 0x0

    move-object v1, p0

    move v2, p1

    move v3, p2

    move v4, p3

    invoke-direct/range {v0 .. v5}, Lcom/google/android/recaptcha/internal/zzaet;-><init>([BIIZ[B)V

    .line 2
    :try_start_0
    invoke-virtual {v0, v3}, Lcom/google/android/recaptcha/internal/zzaes;->zzA(I)I
    :try_end_0
    .catch Lcom/google/android/recaptcha/internal/zzagk; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    move-exception v0

    move-object p0, v0

    .line 3
    invoke-static {p0}, Landroidx/core/app/NavUtils$$ExternalSyntheticBUOutline0;->m(Ljava/lang/Throwable;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public static zzG(I)I
    .locals 1

    and-int/lit8 v0, p0, 0x1

    ushr-int/lit8 p0, p0, 0x1

    neg-int v0, v0

    xor-int/2addr p0, v0

    return p0
.end method

.method public static zzH(J)J
    .locals 3

    const-wide/16 v0, 0x1

    and-long/2addr v0, p0

    const/4 v2, 0x1

    ushr-long/2addr p0, v2

    neg-long v0, v0

    xor-long/2addr p0, v0

    return-wide p0
.end method


# virtual methods
.method public abstract zzA(I)I
.end method

.method public abstract zzB(I)V
.end method

.method public abstract zzC()Z
.end method

.method public abstract zzD()I
.end method

.method public final zzF()V
    .locals 2

    .line 1
    iget v0, p0, Lcom/google/android/recaptcha/internal/zzaev;->zza:I

    iget v1, p0, Lcom/google/android/recaptcha/internal/zzaev;->zzb:I

    add-int/2addr v0, v1

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzaev;->zzc:I

    if-ge v0, p0, :cond_0

    return-void

    :cond_0
    const-string p0, "Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit."

    .line 2
    invoke-static {p0}, Lcom/google/android/recaptcha/internal/zzaeh$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void
.end method

.method public abstract zza()I
.end method

.method public abstract zzb(I)V
.end method

.method public abstract zzc()D
.end method

.method public abstract zzd()F
.end method

.method public abstract zze()J
.end method

.method public abstract zzf()J
.end method

.method public abstract zzg()I
.end method

.method public abstract zzh()J
.end method

.method public abstract zzi()I
.end method

.method public abstract zzj()Z
.end method

.method public abstract zzk()Ljava/lang/String;
.end method

.method public abstract zzl()Ljava/lang/String;
.end method

.method public abstract zzm()Lcom/google/android/recaptcha/internal/zzaer;
.end method

.method public abstract zzn()I
.end method

.method public abstract zzo()I
.end method

.method public abstract zzp()I
.end method

.method public abstract zzq()J
.end method

.method public abstract zzr()I
.end method

.method public abstract zzs()J
.end method
