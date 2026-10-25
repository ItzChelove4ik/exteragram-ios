.class final Lcom/google/android/recaptcha/internal/zzqr;
.super Lcom/google/android/recaptcha/internal/zzqb;
.source "SourceFile"


# static fields
.field static final zza:Lcom/google/android/recaptcha/internal/zzqr;

.field private static final zzd:[Ljava/lang/Object;


# instance fields
.field final transient zzb:[Ljava/lang/Object;

.field final transient zzc:[Ljava/lang/Object;

.field private final transient zze:I

.field private final transient zzf:I

.field private final transient zzg:I


# direct methods
.method static constructor <clinit>()V
    .locals 7

    const/4 v0, 0x0

    .line 1
    new-array v2, v0, [Ljava/lang/Object;

    sput-object v2, Lcom/google/android/recaptcha/internal/zzqr;->zzd:[Ljava/lang/Object;

    new-instance v1, Lcom/google/android/recaptcha/internal/zzqr;

    const/4 v5, 0x0

    const/4 v6, 0x0

    const/4 v3, 0x0

    move-object v4, v2

    invoke-direct/range {v1 .. v6}, Lcom/google/android/recaptcha/internal/zzqr;-><init>([Ljava/lang/Object;I[Ljava/lang/Object;II)V

    sput-object v1, Lcom/google/android/recaptcha/internal/zzqr;->zza:Lcom/google/android/recaptcha/internal/zzqr;

    return-void
.end method

.method public constructor <init>([Ljava/lang/Object;I[Ljava/lang/Object;II)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/recaptcha/internal/zzqb;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzqr;->zzb:[Ljava/lang/Object;

    iput p2, p0, Lcom/google/android/recaptcha/internal/zzqr;->zze:I

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzqr;->zzc:[Ljava/lang/Object;

    iput p4, p0, Lcom/google/android/recaptcha/internal/zzqr;->zzf:I

    iput p5, p0, Lcom/google/android/recaptcha/internal/zzqr;->zzg:I

    return-void
.end method


# virtual methods
.method public final contains(Ljava/lang/Object;)Z
    .locals 4

    const/4 v0, 0x0

    if-eqz p1, :cond_3

    .line 1
    iget-object v1, p0, Lcom/google/android/recaptcha/internal/zzqr;->zzc:[Ljava/lang/Object;

    array-length v2, v1

    if-nez v2, :cond_0

    goto :goto_1

    .line 2
    :cond_0
    invoke-static {p1}, Lcom/google/android/recaptcha/internal/zzpn;->zzb(Ljava/lang/Object;)I

    move-result v2

    :goto_0
    iget v3, p0, Lcom/google/android/recaptcha/internal/zzqr;->zzf:I

    and-int/2addr v2, v3

    .line 3
    aget-object v3, v1, v2

    if-nez v3, :cond_1

    return v0

    .line 4
    :cond_1
    invoke-virtual {v3, p1}, Ljava/lang/Object;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_2

    const/4 p0, 0x1

    return p0

    :cond_2
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_3
    :goto_1
    return v0
.end method

.method public final hashCode()I
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzqr;->zze:I

    return p0
.end method

.method public final synthetic iterator()Ljava/util/Iterator;
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzqb;->zzj()Lcom/google/android/recaptcha/internal/zzpt;

    move-result-object p0

    const/4 v0, 0x0

    .line 2
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzpt;->zzj(I)Lcom/google/android/recaptcha/internal/zzqv;

    move-result-object p0

    return-object p0
.end method

.method public final size()I
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzqr;->zzg:I

    return p0
.end method

.method public final zza()Lcom/google/android/recaptcha/internal/zzqu;
    .locals 1

    .line 1
    invoke-virtual {p0}, Lcom/google/android/recaptcha/internal/zzqb;->zzj()Lcom/google/android/recaptcha/internal/zzpt;

    move-result-object p0

    const/4 v0, 0x0

    .line 2
    invoke-virtual {p0, v0}, Lcom/google/android/recaptcha/internal/zzpt;->zzj(I)Lcom/google/android/recaptcha/internal/zzqv;

    move-result-object p0

    return-object p0
.end method

.method public final zzb()[Ljava/lang/Object;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzqr;->zzb:[Ljava/lang/Object;

    return-object p0
.end method

.method public final zzc()I
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public final zzd()I
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzqr;->zzg:I

    return p0
.end method

.method public final zze()Z
    .locals 0

    const/4 p0, 0x0

    return p0
.end method

.method public final zzf([Ljava/lang/Object;I)I
    .locals 1

    .line 1
    iget-object p2, p0, Lcom/google/android/recaptcha/internal/zzqr;->zzb:[Ljava/lang/Object;

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzqr;->zzg:I

    const/4 v0, 0x0

    invoke-static {p2, v0, p1, v0, p0}, Ljava/lang/System;->arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V

    return p0
.end method

.method public final zzi()Z
    .locals 0

    const/4 p0, 0x1

    return p0
.end method

.method public final zzk()Lcom/google/android/recaptcha/internal/zzpt;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/google/android/recaptcha/internal/zzqr;->zzb:[Ljava/lang/Object;

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzqr;->zzg:I

    invoke-static {v0, p0}, Lcom/google/android/recaptcha/internal/zzpt;->zzi([Ljava/lang/Object;I)Lcom/google/android/recaptcha/internal/zzpt;

    move-result-object p0

    return-object p0
.end method
