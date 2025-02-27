package Controller;

import java.util.List;
import Model.*;

public class Controller {

    private List<User> users;

    public Controller(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String printData(String nama) {
        for (User user : users) {
            if (user.getNama().equalsIgnoreCase(nama)) {
                return "Nama : " + user.getNama()
                        + "\nStatus : " + user.getClass().getSimpleName()
                        + "\nAlamat : " + user.getAlamat()
                        + "\nTempat, Tanggal lahir : " + user.getTTL()
                        + "\nTelp : " + user.getTelp();
            }
        }
        return "Data Tidak ditemukan";
    }

    public String printNA(String NIM, String kodeMK) {

        double NA = 0;

        for (User user : users) {
            if (user instanceof Mahasiswa) {
                Mahasiswa mahasiswa = (Mahasiswa) user;

                if (mahasiswa.getNIM().equals(NIM)) {

                    if (mahasiswa instanceof MhsSarjana) {
                        MhsSarjana mhsSarjana = (MhsSarjana) mahasiswa;

                        for (ListMKdiambil matkulDiambil : mhsSarjana.getMatkulDiambil()) {

                            if (matkulDiambil.getKodeMK().equals(kodeMK)) {
                                NA = matkulDiambil.rataRata(matkulDiambil.getN1(), matkulDiambil.getN2(),
                                        matkulDiambil.getN3());
                                return "Nilai Akhir" + NA;
                            }

                        }

                    } else if (mahasiswa instanceof MhsMagister) {
                        MhsMagister mhsMagister = (MhsMagister) mahasiswa;

                        for (ListMKdiambil matkulDiambil : mhsMagister.getMatkulDiambil()) {

                            if (matkulDiambil.getKodeMK().equals(kodeMK)) {
                                NA = matkulDiambil.rataRata(matkulDiambil.getN1(), matkulDiambil.getN2(),
                                        matkulDiambil.getN3());
                                return "Nilai Akhir" + NA;

                            }

                        }
                    } else {
                        MhsDoktor mhsDoktor = (MhsDoktor) mahasiswa;

                        NA = (mhsDoktor.getNilaiSidang1() + mhsDoktor.getNilaiSidang2() + mhsDoktor.getNilaiSidang3())
                                / 3.0;
                        return "Nilai Akhir" + NA;

                    }
                }
                return "";
            }
        }
        return "Tidak ditemukan";
    }

    public String printrataRataNA(String kodeMK) {

        double totalNilai = 0;
        int jumlahMahasiswa = 0;

        for (User user : users) {
            Mahasiswa mahasiswa = (Mahasiswa) user;

            if (mahasiswa instanceof MhsSarjana) {
                MhsSarjana mahasiswaSarjana = (MhsSarjana) mahasiswa;

                for (ListMKdiambil matkulDiambil : mahasiswaSarjana.getMatkulDiambil()) {

                    if (matkulDiambil.getKodeMK().equals(kodeMK)) {
                        totalNilai += matkulDiambil.rataRata(matkulDiambil.getN1(), matkulDiambil.getN2(),
                                matkulDiambil.getN3());
                        jumlahMahasiswa++;
                    }

                }

            } else if (mahasiswa instanceof MhsMagister) {
                MhsMagister mahasiswaMagister = (MhsMagister) mahasiswa;

                for (ListMKdiambil matkulDiambil : mahasiswaMagister.getMatkulDiambil()) {

                    if (matkulDiambil.getKodeMK().equals(kodeMK)) {
                        totalNilai += matkulDiambil.rataRata(matkulDiambil.getN1(), matkulDiambil.getN2(),
                                matkulDiambil.getN3());
                        jumlahMahasiswa++;
                    }

                }

            } else if (mahasiswa instanceof MhsDoktor) {
                MhsDoktor mahasiswaDoktor = (MhsDoktor) mahasiswa;

                totalNilai += (mahasiswaDoktor.getNilaiSidang1() + mahasiswaDoktor.getNilaiSidang2()
                        + mahasiswaDoktor.getNilaiSidang3()) / 3.0;
                jumlahMahasiswa++;
            }

        }

        if (jumlahMahasiswa == 0) {
            return "Tidak ada mahasiswa";

        } else {
            return "Rata rata NA : " + totalNilai / jumlahMahasiswa;
        }
    }

    public String printJumlahTidakLulus(String kodeMK) {

        int jumlahTidakLulus = 0;
        int jumlahMahasiswa = 0;
        double NA = 0;
        String namaMK = "";

        for (User user : users) {

            if (user instanceof Mahasiswa) {
                Mahasiswa mahasiswa = (Mahasiswa) user;

                if (mahasiswa instanceof MhsSarjana) {
                    MhsSarjana mahasiswaSarjana = (MhsSarjana) mahasiswa;

                    for (ListMKdiambil matkulDiambil : mahasiswaSarjana.getMatkulDiambil()) {

                        if (matkulDiambil.getKodeMK().equals(kodeMK)) {
                            namaMK = matkulDiambil.getNamaMK();
                            NA = matkulDiambil.rataRata(matkulDiambil.getN1(), matkulDiambil.getN2(),
                                    matkulDiambil.getN3());

                            if (NA < 56) {
                                jumlahTidakLulus++;
                            }
                            jumlahMahasiswa++;

                        }

                    }
                } else if (mahasiswa instanceof MhsMagister) {
                    MhsMagister mahasiswaMagister = (MhsMagister) mahasiswa;

                    for (ListMKdiambil matkulDiambil : mahasiswaMagister.getMatkulDiambil()) {

                        if (matkulDiambil.getKodeMK().equals(kodeMK)) {
                            namaMK = matkulDiambil.getNamaMK();
                            NA = matkulDiambil.rataRata(matkulDiambil.getN1(), matkulDiambil.getN2(),
                                    matkulDiambil.getN3());

                            if (NA < 56) {
                                jumlahTidakLulus++;
                            }
                            jumlahMahasiswa++;

                        }

                    }
                } else if (mahasiswa instanceof MhsDoktor) {
                    MhsDoktor mahasiswaDoktor = (MhsDoktor) mahasiswa;

                    namaMK = "Sidang";
                    NA = (mahasiswaDoktor.getNilaiSidang1() + mahasiswaDoktor.getNilaiSidang2()
                            + mahasiswaDoktor.getNilaiSidang3()) / 3.0;

                    if (NA < 56) {
                        jumlahTidakLulus++;
                    }
                    jumlahMahasiswa++;

                }
            }
        }
        return "<" + jumlahTidakLulus + "> dari <" + jumlahMahasiswa + "> mahasiswa tidak lulus matakuliah " + namaMK;
    }

    public String printMatkulDiambilMhs(String NIM) {

        String MK = "MataKuliah yang diambil ";

        for (User user : users) {

            if (user instanceof Mahasiswa) {
                Mahasiswa mahasiswa = (Mahasiswa) user;

                if (mahasiswa instanceof MhsSarjana) {
                    MhsSarjana mahasiswaSarjana = (MhsSarjana) mahasiswa;

                    for (ListMKdiambil matkulDiambil : mahasiswaSarjana.getMatkulDiambil()) {
                        MK += matkulDiambil.getNamaMK() + " dan total presensinya " + matkulDiambil.getPresensi().size()
                                + "\n";
                        return MK;
                    }
                } else if (mahasiswa instanceof MhsMagister) {
                    MhsMagister mahasiswaMagister = (MhsMagister) mahasiswa;

                    for (ListMKdiambil matkulDiambil : mahasiswaMagister.getMatkulDiambil()) {
                        MK += matkulDiambil.getNamaMK() + " dan total presensinya " + matkulDiambil.getPresensi().size()
                                + "\n";
                        return MK;
                    }
                }
            }
        }

        return "Mahasiswa tidak ditemukan";

    }

    public String printJamDosen(String NIK) {

        int jamAjar = 0;

        for (User user : users) {

            if (user instanceof Staff) {
                Staff staff = (Staff) user;

                if (staff instanceof Dosen) {
                    Dosen dosen = (Dosen) staff;

                    if (dosen instanceof DosenTetap) {
                        DosenTetap dosenTetap = (DosenTetap) dosen;

                        for (ListMKdiajar matkulDiajar : dosenTetap.getMatkulDiajar()) {
                            for (PresensiStaff presensiStaff : dosenTetap.getPresensiStaff()) {
                                if (presensiStaff.getStatus() == Status.HADIR) {
                                    jamAjar += matkulDiajar.getSks();
                                }
                            }
                        }
                        return "Total jam yang diajar" + jamAjar;

                    } else if (dosen instanceof DosenHonorer) {
                        DosenHonorer DosenHonorer = (DosenHonorer) dosen;

                        for (ListMKdiajar matkulDiajar : DosenHonorer.getMatkulDiajar()) {
                            for (PresensiStaff presensiStaff : DosenHonorer.getPresensiStaff()) {
                                if (presensiStaff.getStatus() == Status.HADIR) {
                                    jamAjar += matkulDiajar.getSks();
                                }
                            }
                        }
                        return "Total jam yang diajar" + jamAjar;
                    }
                }
            }
        }

        return "Dosen tidak ditemukan";

    }

    public String printGajiStaff(String NIK) {

        double gajiStaff = 0;
        int unit = 0;

        for (User user : users) {
            if (user instanceof Staff) {
                Staff staff = (Staff) user;

                if (staff instanceof Karyawan) {
                    Karyawan karyawan = (Karyawan) staff;

                    if (karyawan.getNIK().equals(NIK)) {
                        unit = karyawan.getUnit();
                        gajiStaff = unit / 22 * karyawan.getSalary();
                        return "Gaji Karyawan : " + gajiStaff;
                    }
                }

                if (staff instanceof Dosen) {
                    Dosen dosen = (Dosen) staff;

                    if (dosen instanceof DosenTetap) {
                        DosenTetap dosenTetap = (DosenTetap) dosen;
                        unit = dosenTetap.getUnit();
                        gajiStaff = dosenTetap.getSalary() + (unit * 25000);
                        return "Gaji Dosen Tetap : " + gajiStaff;

                    } else if (dosen instanceof DosenHonorer) {
                        DosenHonorer dosenHonorer = (DosenHonorer) dosen;
                        unit = dosenHonorer.getUnit();
                        gajiStaff = unit * dosenHonorer.getHonorPerSKS();
                        return "Gaji Dosen Honorer : " + gajiStaff;
                    }
                }
            }
        }

        return "Karyawan tidak ditemukan";

    }

}
