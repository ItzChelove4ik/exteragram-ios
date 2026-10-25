.class public final Lcom/google/android/recaptcha/internal/zzsi;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zzf:Lcom/google/android/recaptcha/internal/zzsh;


# instance fields
.field private final zza:Lcom/google/android/recaptcha/internal/zzse;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzsg;

.field private final zzc:I

.field private final zzd:Z

.field private final zze:Z

.field private final zzg:I


# direct methods
.method static constructor <clinit>()V
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzsh;->zza:Lcom/google/android/recaptcha/internal/zzsh;

    sput-object v0, Lcom/google/android/recaptcha/internal/zzsi;->zzf:Lcom/google/android/recaptcha/internal/zzsh;

    return-void
.end method

.method public synthetic constructor <init>(Lcom/google/android/recaptcha/internal/zzse;IIZZLcom/google/android/recaptcha/internal/zzsh;[B)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzsi;->zza:Lcom/google/android/recaptcha/internal/zzse;

    iput p2, p0, Lcom/google/android/recaptcha/internal/zzsi;->zzg:I

    add-int/lit8 p2, p2, -0x2

    const/4 p1, 0x1

    if-eq p2, p1, :cond_1

    const/4 p1, 0x3

    if-eq p2, p1, :cond_0

    sget-object p1, Lcom/google/android/recaptcha/internal/zzsg;->zzb:Lcom/google/android/recaptcha/internal/zzsg;

    goto :goto_0

    :cond_0
    sget-object p1, Lcom/google/android/recaptcha/internal/zzsg;->zzc:Lcom/google/android/recaptcha/internal/zzsg;

    goto :goto_0

    :cond_1
    sget-object p1, Lcom/google/android/recaptcha/internal/zzsg;->zza:Lcom/google/android/recaptcha/internal/zzsg;

    :goto_0
    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzsi;->zzb:Lcom/google/android/recaptcha/internal/zzsg;

    iput p3, p0, Lcom/google/android/recaptcha/internal/zzsi;->zzc:I

    iput-boolean p4, p0, Lcom/google/android/recaptcha/internal/zzsi;->zzd:Z

    iput-boolean p5, p0, Lcom/google/android/recaptcha/internal/zzsi;->zze:Z

    return-void
.end method

.method public static synthetic zzg()Lcom/google/android/recaptcha/internal/zzsh;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzsi;->zzf:Lcom/google/android/recaptcha/internal/zzsh;

    return-object v0
.end method


# virtual methods
.method public final zza()Lcom/google/android/recaptcha/internal/zzse;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzsi;->zza:Lcom/google/android/recaptcha/internal/zzse;

    return-object p0
.end method

.method public final zzb()Lcom/google/android/recaptcha/internal/zzsg;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzsi;->zzb:Lcom/google/android/recaptcha/internal/zzsg;

    return-object p0
.end method

.method public final zzc()I
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzsi;->zzc:I

    return p0
.end method

.method public final zzd()Z
    .locals 0

    iget-boolean p0, p0, Lcom/google/android/recaptcha/internal/zzsi;->zzd:Z

    return p0
.end method

.method public final synthetic zze()Lcom/google/android/recaptcha/internal/zzse;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzsi;->zza:Lcom/google/android/recaptcha/internal/zzse;

    return-object p0
.end method

.method public final synthetic zzf()Z
    .locals 0

    iget-boolean p0, p0, Lcom/google/android/recaptcha/internal/zzsi;->zze:Z

    return p0
.end method

.method public final synthetic zzh()I
    .locals 0

    iget p0, p0, Lcom/google/android/recaptcha/internal/zzsi;->zzg:I

    return p0
.end method
