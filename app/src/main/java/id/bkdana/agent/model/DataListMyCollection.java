package id.bkdana.agent.model;

public class DataListMyCollection {

    private String idTransaksi;
    private String namaPeminjam;
    private String tenor;
    private String totalPinjam;
    private String sisaHutang;

    public DataListMyCollection(String idTransaksi, String namaPeminjam, String tenor, String totalPinjam, String sisaHutang) {
        this.idTransaksi = idTransaksi;
        this.namaPeminjam = namaPeminjam;
        this.tenor = tenor;
        this.totalPinjam = totalPinjam;
        this.sisaHutang = sisaHutang;
    }


    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getNamaPeminjam() {
        return namaPeminjam;
    }

    public void setNamaPeminjam(String namaPeminjam) {
        this.namaPeminjam = namaPeminjam;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    public String getTotalPinjam() {
        return totalPinjam;
    }

    public void setTotalPinjam(String totalPinjam) {
        this.totalPinjam = totalPinjam;
    }

    public String getSisaHutang() {
        return sisaHutang;
    }

    public void setSisaHutang(String sisaHutang) {
        this.sisaHutang = sisaHutang;
    }
}
