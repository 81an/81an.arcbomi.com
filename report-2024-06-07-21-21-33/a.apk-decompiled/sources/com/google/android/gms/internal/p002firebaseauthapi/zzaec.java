package com.google.android.gms.internal.p002firebaseauthapi;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzaec implements zzaex {
    private static final zzaei zza = new zzaea();
    private final zzaei zzb;

    public zzaec() {
        zzaei zzaeiVar;
        zzaei[] zzaeiVarArr = new zzaei[2];
        zzaeiVarArr[0] = zzada.zza();
        try {
            zzaeiVar = (zzaei) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            zzaeiVar = zza;
        }
        zzaeiVarArr[1] = zzaeiVar;
        zzaeb zzaebVar = new zzaeb(zzaeiVarArr);
        zzadl.zzf(zzaebVar, "messageInfoFactory");
        this.zzb = zzaebVar;
    }

    private static boolean zzb(zzaeh zzaehVar) {
        return zzaehVar.zzc() == 1;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaex
    public final zzaew zza(Class cls) {
        zzaey.zzG(cls);
        zzaeh zzb = this.zzb.zzb(cls);
        if (!zzb.zzb()) {
            if (zzadf.class.isAssignableFrom(cls)) {
                if (zzb(zzb)) {
                    return zzaen.zzl(cls, zzb, zzaeq.zzb(), zzady.zze(), zzaey.zzB(), zzacv.zzb(), zzaeg.zzb());
                }
                return zzaen.zzl(cls, zzb, zzaeq.zzb(), zzady.zze(), zzaey.zzB(), null, zzaeg.zzb());
            }
            if (zzb(zzb)) {
                return zzaen.zzl(cls, zzb, zzaeq.zza(), zzady.zzd(), zzaey.zzz(), zzacv.zza(), zzaeg.zza());
            }
            return zzaen.zzl(cls, zzb, zzaeq.zza(), zzady.zzd(), zzaey.zzA(), null, zzaeg.zza());
        }
        if (zzadf.class.isAssignableFrom(cls)) {
            return zzaeo.zzc(zzaey.zzB(), zzacv.zzb(), zzb.zza());
        }
        return zzaeo.zzc(zzaey.zzz(), zzacv.zza(), zzb.zza());
    }
}
