package id.bkdana.agent.model;

public class DataListSurvey {

    private String nama;
    private String no_reg;
    private String tenor;
    private String total_pinjaman;

    public DataListSurvey(String nama, String no_reg, String tenor, String total_pinjaman) {
        this.nama = nama;
        this.no_reg = no_reg;
        this.tenor = tenor;
        this.total_pinjaman = total_pinjaman;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_reg() {
        return no_reg;
    }

    public void setNo_reg(String no_reg) {
        this.no_reg = no_reg;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    public String getTotal_pinjaman() {
        return total_pinjaman;
    }

    public void setTotal_pinjaman(String total_pinjaman) {
        this.total_pinjaman = total_pinjaman;
    }
}
