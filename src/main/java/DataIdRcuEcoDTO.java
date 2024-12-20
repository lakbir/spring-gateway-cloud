public class DataIdRcuEcoDTO {

    private String idrcu;
    private String ecosystem;

    public DataIdRcuEcoDTO() {
    }

    public DataIdRcuEcoDTO(String idrcu, String ecosystem) {
        this.idrcu = idrcu;
        this.ecosystem = ecosystem;
    }

    public String getIdrcu() {
        return idrcu;
    }

    public void setIdrcu(String idrcu) {
        this.idrcu = idrcu;
    }

    public String getEcosystem() {
        return ecosystem;
    }

    public void setEcosystem(String ecosystem) {
        this.ecosystem = ecosystem;
    }
}