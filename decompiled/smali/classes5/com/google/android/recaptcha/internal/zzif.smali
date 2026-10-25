.class public final Lcom/google/android/recaptcha/internal/zzif;
.super Ljava/lang/Object;
.source "SourceFile"


# static fields
.field private static final zzc:Ljava/lang/String; = "18.9.2"

.field private static final zzd:I


# instance fields
.field private final zza:Landroid/content/Context;

.field private final zzb:Lcom/google/android/recaptcha/internal/zzon;

.field private final zze:Lcom/google/android/recaptcha/internal/zzhr;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    sput v0, Lcom/google/android/recaptcha/internal/zzif;->zzd:I

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Lcom/google/android/recaptcha/internal/zzgp;Lcom/google/android/recaptcha/internal/zzhr;Lcom/google/android/recaptcha/internal/zzon;Z)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/google/android/recaptcha/internal/zzif;->zza:Landroid/content/Context;

    iput-object p3, p0, Lcom/google/android/recaptcha/internal/zzif;->zze:Lcom/google/android/recaptcha/internal/zzhr;

    iput-object p4, p0, Lcom/google/android/recaptcha/internal/zzif;->zzb:Lcom/google/android/recaptcha/internal/zzon;

    return-void
.end method

.method public static final synthetic zza()Ljava/lang/String;
    .locals 1

    sget-object v0, Lcom/google/android/recaptcha/internal/zzif;->zzc:Ljava/lang/String;

    return-object v0
.end method

.method public static final synthetic zzb()I
    .locals 1

    sget v0, Lcom/google/android/recaptcha/internal/zzif;->zzd:I

    return v0
.end method

.method public static final synthetic zzc(Lcom/google/android/recaptcha/internal/zzif;)Lcom/google/android/recaptcha/internal/zzon;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzif;->zzb:Lcom/google/android/recaptcha/internal/zzon;

    return-object p0
.end method

.method public static final synthetic zzd(Lcom/google/android/recaptcha/internal/zzif;)Landroid/content/Context;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzif;->zza:Landroid/content/Context;

    return-object p0
.end method

.method public static final synthetic zze(Lcom/google/android/recaptcha/internal/zzif;)Lcom/google/android/recaptcha/internal/zzhr;
    .locals 0

    iget-object p0, p0, Lcom/google/android/recaptcha/internal/zzif;->zze:Lcom/google/android/recaptcha/internal/zzhr;

    return-object p0
.end method
