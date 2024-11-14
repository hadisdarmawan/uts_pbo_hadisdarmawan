import java.util.ArrayList;
import java.util.Scanner;

// Membuat kelas buku
class Buku {
    String judul;
    String pengarang;
    int tahunTerbit;
    String ISBN;
    boolean status; // true = tersedia, false = dipinjam

    public Buku(String judul, String pengarang, int tahunTerbit, String ISBN) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
        this.ISBN = ISBN;
        this.status = true; // Buku baru akan tersedia saat sudah ditambahkan
    }

    public void pinjam() {
        this.status = false;
    }

    public void kembalikan() {
        this.status = true;
    }

    public String getStatus() {
        return status ? "Tersedia" : "Dipinjam";
    }

    @Override
    public String toString() {
        return "Judul: " + judul + ", Pengarang: " + pengarang + ", Tahun Terbit: " + tahunTerbit + ", ISBN: " + ISBN + ", Status: " + getStatus();
    }
}

// Membuat Kelas BukuDigital yang merupakan turunan dari kelas Buku
class BukuDigital extends Buku {
    private double ukuranFile; // ukuran file buku dalam MB
    private String formatFile; // contoh: PDF, EPUB, MOBI

    public BukuDigital(String judul, String pengarang, int tahunTerbit, String ISBN, double ukuranFile, String formatFile) {
        super(judul, pengarang, tahunTerbit, ISBN); // Memanggil konstruktor dari kelas Buku
        this.ukuranFile = ukuranFile;
        this.formatFile = formatFile;
    }

    public double getUkuranFile() {
        return ukuranFile;
    }

    public String getFormatFile() {
        return formatFile;
    }

    @Override
    public String toString() {
        // Memanggil metode toString dari kelas Buku dan menambahkan informasi tambahan
        return super.toString() + ", Ukuran File: " + ukuranFile + " MB, Format File: " + formatFile;
    }
}

// Membuat Kelas Anggota
class Anggota {
    String nama;
    String nomorAnggota;
    String alamat;
    Buku bukuDipinjam; // Buku yang dipinjam oleh anggota, null jika tidak meminjam

    public Anggota(String nama, String nomorAnggota, String alamat) {
        this.nama = nama;
        this.nomorAnggota = nomorAnggota;
        this.alamat = alamat;
        this.bukuDipinjam = null;
    }

    public void pinjamBuku(Buku buku) {
        if (bukuDipinjam == null && buku.status) {
            bukuDipinjam = buku;
            buku.pinjam();
            System.out.println(" * Buku berhasil dipinjam * ");
        } else {
            System.out.println("Anda sudah meminjam buku atau buku tidak tersedia.");
        }
    }

    public void kembalikanBuku() {
        if (bukuDipinjam != null) {
            bukuDipinjam.kembalikan();
            System.out.println("* Buku berhasil dikembalikan *");
            bukuDipinjam = null;
        } else {
            System.out.println("Anda tidak memiliki buku untuk dikembalikan.");
        }
    }

    @Override
    public String toString() {
        String bukuInfo = (bukuDipinjam != null) ? bukuDipinjam.judul : "Tidak ada buku yang dipinjam";
        return "Nama: " + nama + ", Nomor Anggota: " + nomorAnggota + ", Alamat: " + alamat + ", Buku Dipinjam: " + bukuInfo;
    }
}

// Membuat Kelas Perpustakaan
class Perpustakaan {
    ArrayList<Buku> daftarBuku;
    ArrayList<Anggota> daftarAnggota;

    public Perpustakaan() {
        daftarBuku = new ArrayList<>();
        daftarAnggota = new ArrayList<>();
    }

    public void tambahBuku(Buku buku) {
        daftarBuku.add(buku);
        System.out.println("* Buku berhasil ditambahkan *");
    }

    public void tambahAnggota(Anggota anggota) {
        daftarAnggota.add(anggota);
        System.out.println("* Anggota berhasil ditambahkan *");
    }

    public void tampilkanDaftarBuku() {
        System.out.println("Daftar Buku di Perpustakaan:");
        for (Buku buku : daftarBuku) {
            System.out.println(buku);
        }
    }

    public void tampilkanDaftarAnggota() {
        System.out.println("Daftar Anggota Perpustakaan:");
        for (Anggota anggota : daftarAnggota) {
            System.out.println(anggota);
        }
    }

    public Anggota cariAnggota(String nomorAnggota) {
        for (Anggota anggota : daftarAnggota) {
            if (anggota.nomorAnggota.equals(nomorAnggota)) {
                return anggota;
            }
        }
        return null;
    }

    public Buku cariBuku(String judul) {
        for (Buku buku : daftarBuku) {
            if (buku.judul.equalsIgnoreCase(judul) && buku.status) {
                return buku;
            }
        }
        return null;
    }
}

// Kelas utama untuk menjalankan fitur aplikasi
public class Main {
    public static void main(String[] args) {
        Perpustakaan perpustakaan = new Perpustakaan();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n WELCOME TO SIMPLE LIBRARY ");
            System.out.println("    -FITUR APLIKASI- ");
            System.out.println("1. TAMBAH BUKU");
            System.out.println("2. TAMBAH ANGGOTA ");
            System.out.println("3. TAMPILKAN DAFTAR BUKU");
            System.out.println("4. PINJAM BUKU");
            System.out.println("5. KEMBALIKAN BUKU");
            System.out.println("6. TAMPILKAN INFORMASI ANGGOTA");
            System.out.println("7. Keluar");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Konsumsi newline agar tidak ke skip

            switch (pilihan) {
                case 1:
                    System.out.print("Judul Buku: ");
                    String judul = scanner.nextLine();
                    System.out.print("Pengarang Buku: ");
                    String pengarang = scanner.nextLine();
                    System.out.print("Tahun Terbit Buku: ");
                    int tahunTerbit = scanner.nextInt();
                    scanner.nextLine(); // Konsumsi newline
                    System.out.print("ISBN Buku: ");
                    String ISBN = scanner.nextLine();
                    Buku buku = new Buku(judul, pengarang, tahunTerbit, ISBN);
                    perpustakaan.tambahBuku(buku);
                    break;
                case 2:
                    System.out.print("Nama Anggota: ");
                    String nama = scanner.nextLine();
                    System.out.print("Nomor Anggota: ");
                    String nomorAnggota = scanner.nextLine();
                    System.out.print("Alamat Anggota: ");
                    String alamat = scanner.nextLine();
                    Anggota anggota = new Anggota(nama, nomorAnggota, alamat);
                    perpustakaan.tambahAnggota(anggota);
                    break;
                case 3:
                    perpustakaan.tampilkanDaftarBuku();
                    break;
                case 4:
                    System.out.print("Masukkan Nomor Anggota: ");
                    nomorAnggota = scanner.nextLine();
                    Anggota peminjam = perpustakaan.cariAnggota(nomorAnggota);
                    if (peminjam != null) {
                        System.out.print("Masukkan Judul Buku yang ingin dipinjam: ");
                        judul = scanner.nextLine();
                        Buku bukuDipinjam = perpustakaan.cariBuku(judul);
                        if (bukuDipinjam != null) {
                            peminjam.pinjamBuku(bukuDipinjam);
                        } else {
                            System.out.println("Buku tidak tersedia atau tidak ditemukan.");
                        }
                    } else {
                        System.out.println("Anggota tidak ditemukan.");
                    }
                    break;
                case 5:
                    System.out.print("Masukkan Nomor Anggota: ");
                    nomorAnggota = scanner.nextLine();
                    Anggota pengembali = perpustakaan.cariAnggota(nomorAnggota);
                    if (pengembali != null) {
                        pengembali.kembalikanBuku();
                    } else {
                        System.out.println("Anggota tidak ditemukan.");
                    }
                    break;
                case 6:
                    perpustakaan.tampilkanDaftarAnggota();
                    break;
                case 7:
                    System.out.println("Terima kasih telah menggunakan aplikasi perpustakaan.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
